package tv.dotstart.basedefense.api.device.impl

import tv.dotstart.basedefense.api.device.SecurityDevice
import tv.dotstart.basedefense.api.network.Network
import tv.dotstart.basedefense.api.network.NetworkImpl

/**
 * Provides an abstract base type for complex security devices.
 *
 * The purpose of this abstraction is to enable the automatic creation and joining of networks upon
 * entity initialization.
 */
abstract class SecurityDeviceBlockEntity : BaseSecurityComponentBlockEntity(), SecurityDevice {

  override val initialized: Boolean
    get() = super.initialized && this::network.isInitialized

  override lateinit var network: Network
    protected set

  override fun onInitialize() {
    this.initializeNetwork()
  }

  override fun onLoad() {
    if (!super.initialized) {
      return
    }

    this.initializeNetwork()
  }

  /**
   * Updates the internally cached network.
   *
   * This method should be invoked whenever the device's owner is changed in order to create a
   * completely new network which matches the owner (and potentially to disconnect gracefully and
   * trigger the correct events).
   */
  private fun initializeNetwork() {
    if (!this::network.isInitialized || this.network.owner != this.owner) {
      if (this::network.isInitialized) {
        this.network -= this
      }

      val network = NetworkImpl(this.owner)

      this.network = network
      this.network += this

      this.onCreateNetwork(network)
    }
  }

  protected open fun onCreateNetwork(network: NetworkImpl) {
  }

  override fun onJoin(network: Network) {
    this.network = network
  }
}
