package tv.dotstart.basedefense.api.network.event

/**
 * Represents an event which notifies its receivers about the conflict state of a network (e.g. when
 * two controllers are connected to the network).
 */
class ConflictEvent(val state: Boolean) : Event
