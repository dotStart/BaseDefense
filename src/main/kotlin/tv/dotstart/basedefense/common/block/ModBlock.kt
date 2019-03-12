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
package tv.dotstart.basedefense.common.block

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import tv.dotstart.basedefense.common.ModCreativeTab
import tv.dotstart.basedefense.util.modResource

/**
 * Provides a utility extension to the block specification which significantly simplifies the
 * creation of standardized mod blocks.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
abstract class ModBlock(name: String, material: Material, technical: Boolean = false) :
    Block(material) {

  init {
    unlocalizedName = name
    registryName = modResource(name)

    if (!technical) {
      this.setCreativeTab(ModCreativeTab)
    }
  }
}
