package tv.dotstart.basedefense.api.network

import com.google.common.eventbus.EventBus
import tv.dotstart.basedefense.api.device.SecurityComponent
import tv.dotstart.basedefense.api.device.SecurityController
import tv.dotstart.basedefense.api.network.event.ConflictEvent
import tv.dotstart.basedefense.api.network.event.ControllerDemotionEvent
import tv.dotstart.basedefense.api.network.event.ControllerPromotionEvent
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

  private val bus = EventBus()

  protected fun promoteController(controller: SecurityController?) {
    this.controller = controller

    // TODO: Events
  }

  override fun plusAssign(component: SecurityComponent) {
    this.components += component

    if (component is SecurityController) {
      if (this.controller == null) {
        this.promoteController(component)
      } else {
        // TODO: Switch to invalid state
      }
    }

    // TODO: Event

    this.bus.register(component)
  }

  override fun plusAssign(network: Network) {
    TODO("not implemented")
  }

  override fun minusAssign(component: SecurityComponent) {
    this.components -= component
    this.bus.unregister(component)

    if (component == this.controller) {
      val controller = this.components
          .find { it is SecurityController }
          as SecurityController

      this.promoteController(controller)
    }

    // TODO: Event
  }

  override fun postEvent(event: Event) = this.bus.post(event)

  override fun canConnect(device: SecurityComponent): Boolean {
    return this.owner == device.owner
  }
}
