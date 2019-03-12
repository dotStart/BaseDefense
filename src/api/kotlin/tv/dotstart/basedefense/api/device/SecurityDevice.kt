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

import net.minecraft.client.resources.I18n
import tv.dotstart.basedefense.api.network.Network

/**
 * Represents a complex security component (e.g. a device) which's main purpose is not to establish
 * communication but perform a certain action.
 *
 * Devices will always create a new network when none is found within any of the adjacent
 * components, in order to bootstrap the network (even when a controller has yet to be added to the
 * network).
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
interface SecurityDevice : SecurityComponent {

  /**
   * Provides a human readable name for this particular device.
   *
   * This name may show up in the output of GUIs and items in order to inform players about the
   * active devices within their network.
   *
   * This value will default to the translated version of [unlocalizedName] but may be overridden if
   * parameterized or otherwise dynamic naming is desired.
   */
  val name: String
    get() = this.unlocalizedName?.let { I18n.format(it) } ?: "<unnamed>"

  /**
   * Provides a localization key with which the [name] of this particular device is resolved.
   */
  val unlocalizedName: String?
    get() = null

  override val network: Network
}
