package tv.dotstart.basedefense.api.network.event

import tv.dotstart.basedefense.api.device.SecurityComponent

/**
 * Represents an event which notifies its receivers about the de-registration of a component within
 * the network.
 */
class ComponentDeRegistrationEvent(override val component: SecurityComponent) : ComponentEvent
