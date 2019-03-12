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
package tv.dotstart.basedefense.common

import net.minecraft.item.Item
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import tv.dotstart.basedefense.BaseDefense
import tv.dotstart.basedefense.common.item.CableBlockItem
import tv.dotstart.basedefense.util.benchmark

/**
 * Performs the registration of mod specific item implementations during startup.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
@Mod.EventBusSubscriber
object ItemRegistrar {

  @JvmStatic
  @SubscribeEvent
  fun register(event: RegistryEvent.Register<Item>) {
    val registry = event.registry

    BaseDefense.logger.benchmark("Item Registration") {
      registry.registerAll(
          CableBlockItem
      )
    }
  }
}
