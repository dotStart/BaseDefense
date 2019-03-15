package tv.dotstart.basedefense.api.device.impl

import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import tv.dotstart.basedefense.api.device.SecurityComponent
import tv.dotstart.basedefense.api.network.Network
import tv.dotstart.basedefense.api.util.PlayerReference

/**
 * Provides a simple base implementation for standard security components which establish a
 * connection to established networks in adjacent entities.
 */
abstract class SecurityComponentBlockEntity : TileEntity(), SecurityComponent {

  override lateinit var owner: PlayerReference
    protected set
  override var network: Network? = null
    protected set

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
    this.markDirty()
  }

  /**
   * Searches for new components in the adjacent blocks and establishes a connection to the network
   * if permitted.
   *
   * Note that this method should be invoked whenever a security device is placed or initialized
   * in a neighboring block.
   */
  fun updateConnections(origin: EnumFacing?) {
    if (this.network != null) {
      // already connected to another network, cannot connect to anybody else right now
      return
    }

    for (face in EnumFacing.values()) {
      val position = this.pos.offset(face)
      val entity = this.world.getTileEntity(position)
      if (entity !is SecurityComponent) {
        continue
      }

      val network = entity.network ?: continue

      // security check - prevent connecting to components owned by other users as this could be an
      // attempt to hijack the network
      if (!network.canConnect(this)) {
        continue
      }

      this.network = network
      network += this
    }

    if (this.network != null) {
      this.markDirty()

      val state = this.world.getBlockState(this.pos)
      this.world.notifyBlockUpdate(this.pos, state, state, 3) // TODO: only push neighbor updates?
    }
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
