package tv.dotstart.basedefense.api.network.event

import tv.dotstart.basedefense.api.device.SecurityComponent

/**
 * Represents an event which relates to a given component within the network (such as
 * de-registrations, state changes, etc).
 */
interface ComponentEvent : Event {

  val component: SecurityComponent
}
