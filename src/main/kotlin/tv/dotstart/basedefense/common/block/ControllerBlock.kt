package tv.dotstart.basedefense.common.block

import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import tv.dotstart.basedefense.api.util.PlayerReference
import tv.dotstart.basedefense.common.block.entity.ControllerBlockEntity

object ControllerBlock : ModBlock("controller_block", Material.IRON), ITileEntityProvider {

  override fun createNewTileEntity(worldIn: World, meta: Int) = ControllerBlockEntity()

  override fun onBlockPlacedBy(worldIn: World, pos: BlockPos, state: IBlockState,
      placer: EntityLivingBase, stack: ItemStack) {
    if (worldIn.isRemote) {
      return
    }

    val entity = worldIn.getTileEntity(pos) as? ControllerBlockEntity ?: return
    val player = placer as? EntityPlayer ?: return

    entity.initialize(PlayerReference(player))
  }
}
