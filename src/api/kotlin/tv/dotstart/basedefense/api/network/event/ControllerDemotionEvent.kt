package tv.dotstart.basedefense.api.network.event

import tv.dotstart.basedefense.api.device.SecurityController

/**
 * Represents an event which notifies all of its subscribers about a controller demotion (e.g. the
 * current controller device has left the network and was thus demoted without a replacement).
 *
 * When a replacement is supplied, ControllerPromotionEvent will be broadcast instead.
 */
class ControllerDemotionEvent(override val controller: SecurityController) : ControllerEvent
