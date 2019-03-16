package tv.dotstart.basedefense.common.block.entity

import tv.dotstart.basedefense.api.device.SecurityController
import tv.dotstart.basedefense.api.device.impl.SecurityDeviceBlockEntity

/**
 * Provides a controller block which provides network components with power and manages the current
 * state as well as security aspects of the network.
 */
class ControllerBlockEntity : SecurityDeviceBlockEntity(), SecurityController
