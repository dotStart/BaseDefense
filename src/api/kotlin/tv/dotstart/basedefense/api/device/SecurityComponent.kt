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
   * Identifies the network with which this component is currently communicating.
   *
   * When no network (e.g. null) is returned by this property, then the component has yet to
   * establish a connection with an identity bearing component.
   */
  val network: Network?
}
