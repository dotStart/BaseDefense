/*
 * Copyright 2019 Johannes Donath <johannesd@torchmind.com>
 * and other copyright owners as documented in the project's IP log.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tv.dotstart.basedefense.api.network

import tv.dotstart.basedefense.api.device.SecurityComponent
import tv.dotstart.basedefense.api.device.SecurityController
import tv.dotstart.basedefense.api.network.event.Event
import tv.dotstart.basedefense.api.util.PlayerReference

/**
 * Represents a network consisting of one or more complex security devices (and typically a
 * controller).
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
interface Network {

  /**
   * Identification of the player who created this network.
   *
   * This value is used in order to prevent accidental pairing between networks created by different
   * players even when the world is restored from an unloaded state. It is backed into the state of
   * each respective security component and cannot be changed once the block is placed inside the
   * world.
   *
   * Note that this identification method may not be safe when running in offline mode as player ids
   * are generated based on their chosen names. Proceed with caution in these cases.
   */
  val owner: PlayerReference

  /**
   * Identifies the controller device which provides power to the network, manages ACLs and
   * dispatches alerts.
   *
   * When the controller is absent (e.g. the network is currently unpowered and without its control
   * information), null is returned instead.
   */
  val controller: SecurityController?

  /**
   * Identifies whether this network is currently active (e.g. its controller device is inside a
   * loaded chunk and provided with sufficient power).
   *
   * Networks may also be marked disabled due to other causes (for instance, when entering a generic
   * maintenance mode where changes to the network are temporarily permitted).
   */
  val active: Boolean

  /**
   * Retrieves a complete list of all components which are currently connected to the network.
   *
   * Note that components which currently reside inside of unloaded chunks are typically not part
   * of this list as they automatically de-register from the network during unload and are treated
   * as if they didn't even exist as of that point.
   */
  val components: Set<SecurityComponent>

  /**
   * Registers a new component with this network.
   *
   * Note that this method does not evaluate whether the placer actually has sufficient permissions
   * to perform this action.
   */
  operator fun plusAssign(component: SecurityComponent)

  /**
   * Joins all components within the given network into this network.
   *
   * This method is invoked when two independent networks merge together (for instance, when
   * unloaded chunks between two currently separated networks are loaded and re-connect the two
   * halves).
   *
   * Note that the passed network becomes invalid as of that point (as all of its components will
   * immediately leave its scope).
   */
  operator fun plusAssign(network: Network)

  /**
   * Un-Registers an existing component from this network.
   *
   * Note that this method does not evaluate whether the destruction is a valid permitted action or
   * expected to raise an alarm within the network.
   */
  operator fun minusAssign(component: SecurityComponent)

  /**
   * Transmits an arbitrary event to all registered components within this network.
   */
  fun postEvent(event: Event)

  /**
   * Evaluates whether a given security component is theoretically permitted to establish a
   * connection with this network.
   *
   * Typically this method will simply compare the owners of components and the network in order to
   * prevent access by unknown players. However, higher tier controllers may permit the
   * configuration of ACLs for network components.
   */
  fun canConnect(device: SecurityComponent): Boolean
}
