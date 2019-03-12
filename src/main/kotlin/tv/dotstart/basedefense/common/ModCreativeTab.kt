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

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import tv.dotstart.basedefense.modId

/**
 * Provides a creative-menu tab in which all relevant items will be made available to players.
 *
 * Note that this tab should never contain technical items (e.g. items or blocks which should not be
 * immediately accessible and merely exist to simplify the implementation of a given functionality).
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
object ModCreativeTab : CreativeTabs(modId) {

  override fun getTabIconItem() = ItemStack(Blocks.STONE)
}
