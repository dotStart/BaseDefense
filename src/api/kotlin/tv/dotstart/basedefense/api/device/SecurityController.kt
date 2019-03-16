package tv.dotstart.basedefense.api.device

import java.util.*

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

  /**
   * Evaluates whether a given player is permitted to perform an action on the network.
   *
   * The default implementation of this method only permits access to the network when they are
   * performed by the owner.
   */
  fun hasPermission(playerId: UUID, permission: Permission) = playerId == this.owner.id
}
