package tv.dotstart.basedefense.api.network.event

import tv.dotstart.basedefense.api.device.SecurityController

/**
 * Represents an event which references the network's controller.
 */
interface ControllerEvent : Event {

  /**
   * Identifies the controller which is referenced in this particular event.
   */
  val controller: SecurityController?
}
