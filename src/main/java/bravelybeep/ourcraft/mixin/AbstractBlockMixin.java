package bravelybeep.ourcraft.mixin;

import bravelybeep.ourcraft.ModBlocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(AbstractBlock.class)
public class AbstractBlockMixin {
	@Inject(method = "calcBlockBreakingDelta", at = @At(value = "JUMP", opcode = Opcodes.IFNE, shift = At.Shift.AFTER), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
	public void inject_calcBlockBreakingDelta(BlockState state, PlayerEntity playerEntity, BlockView world,
			BlockPos pos, CallbackInfoReturnable<Float> cir, float hardness) {

		if (hardness == -1.0F && playerEntity.getMainHandStack().getItem() == Items.NETHERITE_PICKAXE) {
			int effective = playerEntity.canHarvest(state) ? 30 : 100;
			if (state.getBlock() == ModBlocks.SOFT_BEDROCK) {
				cir.setReturnValue(playerEntity.getBlockBreakingSpeed(state) / 50F / effective);
				return;
			}
		}
	}
}