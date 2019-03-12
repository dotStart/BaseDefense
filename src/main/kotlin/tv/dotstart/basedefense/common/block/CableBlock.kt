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

import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyBool
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import tv.dotstart.basedefense.api.device.SecurityComponent
import tv.dotstart.basedefense.common.block.CableBlock.Properties.downConnected
import tv.dotstart.basedefense.common.block.CableBlock.Properties.eastConnected
import tv.dotstart.basedefense.common.block.CableBlock.Properties.northConnected
import tv.dotstart.basedefense.common.block.CableBlock.Properties.southConnected
import tv.dotstart.basedefense.common.block.CableBlock.Properties.upConnected
import tv.dotstart.basedefense.common.block.CableBlock.Properties.westConnected

/**
 * Provides a standard communication and power cable which connects various security components with
 * each other.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
abstract class CableBlock(suffix: String? = null) :
    ModBlock("""cable_block${suffix?.let { "_$it" } ?: ""}""", Material.CLOTH) {

  object Properties {
    val northConnected: PropertyBool = PropertyBool.create("north")
    val eastConnected: PropertyBool = PropertyBool.create("east")
    val southConnected: PropertyBool = PropertyBool.create("south")
    val westConnected: PropertyBool = PropertyBool.create("west")
    val upConnected: PropertyBool = PropertyBool.create("up")
    val downConnected: PropertyBool = PropertyBool.create("down")
  }

  override fun canBeConnectedTo(world: IBlockAccess, pos: BlockPos, facing: EnumFacing): Boolean {
    val target = pos.offset(facing)
    return world.getBlockState(target).block == this || world.getTileEntity(
        pos) is SecurityComponent
  }

  override fun createBlockState() = BlockStateContainer(this,
      northConnected, eastConnected, southConnected,
      westConnected, upConnected, downConnected
  )

  override fun getActualState(state: IBlockState, worldIn: IBlockAccess, pos: BlockPos) = state
      .withProperty(northConnected, this.canBeConnectedTo(worldIn, pos, EnumFacing.NORTH))
      .withProperty(eastConnected, this.canBeConnectedTo(worldIn, pos, EnumFacing.EAST))
      .withProperty(southConnected, this.canBeConnectedTo(worldIn, pos, EnumFacing.SOUTH))
      .withProperty(westConnected, this.canBeConnectedTo(worldIn, pos, EnumFacing.WEST))
      .withProperty(upConnected, this.canBeConnectedTo(worldIn, pos, EnumFacing.UP))
      .withProperty(downConnected, this.canBeConnectedTo(worldIn, pos, EnumFacing.DOWN))

  override fun getMetaFromState(state: IBlockState) = 0

  override fun isOpaqueCube(state: IBlockState) = false
  override fun isFullCube(state: IBlockState) = false
  override fun isPassable(worldIn: IBlockAccess, pos: BlockPos) = false
  override fun shouldSideBeRendered(blockState: IBlockState, blockAccess: IBlockAccess,
      pos: BlockPos, side: EnumFacing) = true

  object Inactive : CableBlock()

  object Active : CableBlock("active") {

    init {
      this.lightValue = 8
    }
  }
}
