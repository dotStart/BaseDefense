package tv.dotstart.basedefense.api.network.event

import tv.dotstart.basedefense.api.device.SecurityComponent

/**
 * Represents an event which notifies its receivers about a new device which has just been added to
 * the network.
 */
class ComponentRegistrationEvent(override val component: SecurityComponent) : ComponentEvent
