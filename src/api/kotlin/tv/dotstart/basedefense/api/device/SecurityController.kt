package tv.dotstart.basedefense.api.device

/**
 * Represents a security controller which handles the evaluation of permissions and generally
 * handles all player facing aspects of the network.
 */
interface SecurityController : SecurityDevice {

  /**
   * Evaluates whether this controller considers the network to be active (e.g. powered and ready
   * for operation).
   */
  val networkActive: Boolean
    get() = true
}
