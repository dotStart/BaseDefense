package tv.dotstart.basedefense.api.network

import tv.dotstart.basedefense.api.device.SecurityComponent
import tv.dotstart.basedefense.api.device.SecurityController
import tv.dotstart.basedefense.api.network.event.Event
import tv.dotstart.basedefense.api.util.PlayerReference

/**
 * Provides a standard network implementation.
 */
open class NetworkImpl(override val owner: PlayerReference) : Network {

  override var controller: SecurityController? = null
    protected set

  override var active = false
  override val components: MutableSet<SecurityComponent> = mutableSetOf()

  override fun plusAssign(component: SecurityComponent) {
    this.components += component
    // TODO: Event
  }

  override fun plusAssign(network: Network) {
    TODO("not implemented")
  }

  override fun minusAssign(component: SecurityComponent) {
    this.components -= component
    // TODO: Event
  }

  override fun postEvent(event: Event) {
    TODO("not implemented")
  }

  override fun canConnect(device: SecurityComponent): Boolean {
    return this.owner == device.owner
  }
}
