package me.drex.instantfeedback.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.Block;

public class PalePumpkinBlock extends Block {
    public static final MapCodec<PalePumpkinBlock> CODEC = simpleCodec(PalePumpkinBlock::new);

    @Override
    public MapCodec<PalePumpkinBlock> codec() {
        return CODEC;
    }

    public PalePumpkinBlock(Properties properties) {
        super(properties);
    }
}
