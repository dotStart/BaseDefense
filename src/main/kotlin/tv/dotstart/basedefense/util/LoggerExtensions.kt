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
package tv.dotstart.basedefense.util

import org.apache.logging.log4j.Logger
import kotlin.system.measureNanoTime

/**
 * Performs a basic benchmark for an initialization section.
 */
fun Logger.benchmark(stage: String, block: () -> Unit) {
  this.info("Beginning $stage")
  val time = measureNanoTime {
    block()
  }
  this.info("$stage took %.4f ms".format(time / 1000000.0))
}
