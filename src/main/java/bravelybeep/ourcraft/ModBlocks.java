package bravelybeep.ourcraft;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
	public static void initialize() {
	}

	public static Block register(Block block, String name) {
		Identifier id = Identifier.of(Ourcraft.MOD_ID, name);
		return Registry.register(Registries.BLOCK, id, block);
	}

	public static final Block SOFT_BEDROCK = register(
			new Block(AbstractBlock.Settings.copy(Blocks.BEDROCK).requiresTool()),
			"soft_bedrock");
}
