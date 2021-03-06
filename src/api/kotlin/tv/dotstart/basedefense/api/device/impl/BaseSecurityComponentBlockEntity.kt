package tv.dotstart.basedefense.api.device.impl

import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import tv.dotstart.basedefense.api.device.SecurityComponent
import tv.dotstart.basedefense.api.util.PlayerReference

/**
 * Provides a rudimentary base implementation for security components and devices.
 *
 * This implementation is necessary to prevent collisions between the fundamental differneces of
 * component and device implementations.
 */
abstract class BaseSecurityComponentBlockEntity : TileEntity(), SecurityComponent {

  override val initialized: Boolean
    get() = this::owner.isInitialized

  /**
   * Permanently stores a reference to the owner of this device.
   *
   * Note that this value is populated after initialization due to a restriction in the way that
   * blocks are constructed in Minecraft.
   */
  override lateinit var owner: PlayerReference
    protected set

  /**
   * Retrieves a list of faces which are currently interacting with one or more network components.
   */
  val connections: Set<EnumFacing>
    get() = EnumFacing.values()
        .filter { this.hasConnectionOnSide(it) }
        .toSet()

  /**
   * Performs the initialization of this particular component.
   *
   * This method is to be invoked immediately after it has been placed in the world in order to
   * update its respective owner object. Note that all calls (including persistence related
   * invocations) are ignored until this method is invoked.
   *
   * This method causes an entity invalidation.
   */
  fun initialize(owner: PlayerReference) {
    this.owner = owner
    this.onInitialize()
    this.markDirty()
  }

  /**
   * Extension point which is invoked during first entity initialization before its invalidation.
   */
  protected open fun onInitialize() {
  }

  /**
   * Evaluates whether this component has a connection on the given side.
   */
  fun hasConnectionOnSide(face: EnumFacing): Boolean {
    if (!this.initialized) {
      return false
    }

    val component = this.world.getTileEntity(this.pos.offset(face)) as? SecurityComponent
        ?: return false

    return this.isConnectedTo(component)
  }

  override fun readFromNBT(compound: NBTTagCompound) {
    super.readFromNBT(compound)

    if (!compound.hasKey("security")) {
      // TODO: Log warning
      return
    }

    val security = compound.getCompoundTag("security")
    this.owner = PlayerReference(security.getCompoundTag("owner"))
  }

  override fun writeToNBT(compound: NBTTagCompound): NBTTagCompound {
    if (this::owner.isInitialized) {
      val security = NBTTagCompound()
      security.setTag("owner", this.owner.nbt)

      compound.setTag("security", security)
    }

    return super.writeToNBT(compound)
  }
}
