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
package tv.dotstart.basedefense

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.Logger
import tv.dotstart.basedefense.common.BlockEntityRegistrar

const val modId = "basedefense"
const val version = "3.0"

/**
 * Handles the initialization steps for this mod implementation.
 *
 * Note that this mod is introduced through the Forgelin Kotlin adapter in order to fully support
 * Kotlin Objects (e.g. true singletons).
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
@Mod(modid = modId, version = version,
    modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter")
object BaseDefense {

  lateinit var logger: Logger
    private set

  @Mod.EventHandler
  fun onPreInit(event: FMLPreInitializationEvent) {
    this.logger = event.modLog
    this.logger.info("BaseDefense is in startup")

    BlockEntityRegistrar.register()
  }

  @Mod.EventHandler
  @Suppress("UNUSED_PARAMETER") // must pass type via parameter
  fun onPostInit(event: FMLPostInitializationEvent) {
    this.logger.info("BaseDefense has completed its startup procedures")
  }
}
