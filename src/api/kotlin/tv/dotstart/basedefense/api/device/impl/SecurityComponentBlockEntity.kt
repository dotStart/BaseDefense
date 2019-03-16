package tv.dotstart.basedefense.api.device.impl

import net.minecraft.util.EnumFacing
import tv.dotstart.basedefense.api.device.SecurityComponent
import tv.dotstart.basedefense.api.network.Network

/**
 * Provides a simple base implementation for standard security components which establish a
 * connection to established networks in adjacent entities.
 */
abstract class SecurityComponentBlockEntity : BaseSecurityComponentBlockEntity() {

  override var network: Network? = null
    protected set

  override fun onInitialize() {
    this.updateConnections()
  }

  override fun onLoad() {
    if (!this.initialized) {
      return
    }

    this.updateConnections()
  }

  /**
   * Searches for new components in the adjacent blocks and establishes a connection to the network
   * if permitted.
   *
   * Note that this method should be invoked whenever a security device is placed or initialized
   * in a neighboring block.
   */
  fun updateConnections() {
    if (this.network != null) {
      // already connected to another network, cannot connect to anybody else right now
      return
    }

    for (face in EnumFacing.values()) {
      val position = this.pos.offset(face)
      val entity = this.world.getTileEntity(position)
      if (entity !is SecurityComponent || !entity.initialized) {
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
}
