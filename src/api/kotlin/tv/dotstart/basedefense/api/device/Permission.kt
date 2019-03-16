package tv.dotstart.basedefense.api.device

/**
 * Provides a list of recognized ACL permission types.
 */
enum class Permission {

  /**
   * Permits a player to connect arbitrary components to the network.
   */
  CONNECT,

  /**
   * Permits a player to disconnect arbitrary components from the network.
   */
  DISCONNECT,

  /**
   * Permits a player to read data on the network (such as the alarm state).
   */
  READ,

  /**
   * Permits a player to write data to the network.
   */
  WRITE
}
