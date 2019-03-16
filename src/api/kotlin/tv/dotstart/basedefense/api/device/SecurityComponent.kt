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
package tv.dotstart.basedefense.api.device

import tv.dotstart.basedefense.api.network.Network
import tv.dotstart.basedefense.api.util.PlayerReference

/**
 * Represents a security device which may be connected to the network and communicate with various
 * other components such as cameras, sensors and alarms.
 *
 * Basic security components (like cables or other plain communication methods) will not establish
 * their own network automatically but instead rely on adjacent connected devices to create a
 * network and notify them.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
interface SecurityComponent {

  /**
   * Evaluates whether this component has been initialized and may be interacted with.
   *
   * This value typically exposes the internal initialization state of the component (e.g. when
   * lateinit variables are used by the implementation). Callers are expected to not interact with
   * components unless this val has been set to true.
   */
  val initialized: Boolean

  /**
   * Identifies the network with which this component is currently communicating.
   *
   * When no network (e.g. null) is returned by this property, then the component has yet to
   * establish a connection with an identity bearing component.
   */
  val network: Network?

  /**
   * Identifies the person who initially placed this particular component.
   *
   * This value is persisted for all components even when they have yet to join a network as this
   * prevents the deliberate hijacking of security networks by abusing chunk loading behavior (e.g.
   * the fact that components cannot act while outside of a loaded chunk).
   *
   * When re-connecting to neighboring blocks, this value should be evaluate against the identifiers
   * within candidates.
   */
  val owner: PlayerReference

  /**
   * Event callback which is invoked when the component registration has completed.
   */
  fun onRegister() {
  }

  /**
   * Event callback which is invoked when two networks are joined together (e.g. this component is
   * shifted into a different network).
   *
   * Note that the previous network is destroyed once the join of all components completes.
   */
  fun onJoin(network: Network) {
  }

  /**
   * Event callback which is invoked when the component de-registration has completed.
   */
  fun onDeRegister() {
  }

  fun isConnectedTo(
      component: SecurityComponent) = this.initialized && component.initialized && this.network == component.network
}
