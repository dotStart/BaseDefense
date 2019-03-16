package tv.dotstart.basedefense.api.device.impl

import tv.dotstart.basedefense.api.network.Network
import tv.dotstart.basedefense.api.network.NetworkImpl

/**
 * Provides an abstract base type for complex security devices.
 *
 * The purpose of this abstraction is to enable the automatic creation and joining of networks upon
 * entity initialization.
 */
abstract class SecurityDeviceBlockEntity : BaseSecurityComponentBlockEntity() {

  override lateinit var network: Network
    protected set

  /**
   * Updates the internally cached network.
   *
   * This method should be invoked whenever the device's owner is changed in order to create a
   * completely new network which matches the owner (and potentially to disconnect gracefully and
   * trigger the correct events).
   */
  protected fun updateNetwork() {
    if (!this::network.isInitialized || this.network.owner != this.owner) {
      if (this::network.isInitialized) {
        this.network -= this
      }

      this.network = NetworkImpl(this.owner)
    }
  }
}
