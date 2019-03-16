package tv.dotstart.basedefense.api.network.event

import tv.dotstart.basedefense.api.device.SecurityController

/**
 * Represents an event which notifies its subscribers about a controller promotion (e.g. a new
 * device has been promoted to be the network's main controller).
 */
class ControllerPromotionEvent(override val controller: SecurityController) : ControllerEvent
