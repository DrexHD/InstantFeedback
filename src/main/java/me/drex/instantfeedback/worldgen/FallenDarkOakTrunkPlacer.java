package me.drex.instantfeedback.worldgen;

import com.google.common.collect.Lists;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.drex.instantfeedback.InstantFeedback;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class FallenDarkOakTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<FallenDarkOakTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(
        instance -> trunkPlacerParts(instance).apply(instance, FallenDarkOakTrunkPlacer::new)
    );
    public FallenDarkOakTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return InstantFeedback.FALLEN_DARK_OAK_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(
        LevelSimulatedReader levelSimulatedReader,
        BiConsumer<BlockPos, BlockState> biConsumer,
        RandomSource randomSource,
        int maxHeight,
        BlockPos origin,
        TreeConfiguration treeConfiguration
    ) {
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
        Direction fallDirection = Direction.values()[randomSource.nextInt(4) + 2];

        BlockPos below = origin.below();

//        setDirtAt(levelSimulatedReader, biConsumer, randomSource, below, treeConfiguration);
//        setDirtAt(levelSimulatedReader, biConsumer, randomSource, below.east(), treeConfiguration);
//        setDirtAt(levelSimulatedReader, biConsumer, randomSource, below.south(), treeConfiguration);
//        setDirtAt(levelSimulatedReader, biConsumer, randomSource, below.south().east(), treeConfiguration);

        BiConsumer<BlockPos, BlockState> transformedBiConsumer = (pos, state) -> {
            biConsumer.accept(pos, state.setValue(RotatedPillarBlock.AXIS, fallDirection.getAxis()));
        };

        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(randomSource);
        int height = maxHeight - randomSource.nextInt(4);
        int k = 2 - randomSource.nextInt(3);
        int originX = origin.getX();
        int originY = origin.getY();
        int originZ = origin.getZ();
        int x = originX;
        int z = originZ;
        int topY = originY + maxHeight - 1;

        for (int dy = 0; dy < maxHeight; dy++) {
            if (dy >= height && k > 0) {
                x += direction.getStepX();
                z += direction.getStepZ();
                k--;
            }

            int y = originY + dy;
            BlockPos pos = new BlockPos(x, y, z);
            if (TreeFeature.isAirOrLeaves(levelSimulatedReader, transform(pos, fallDirection, origin))) {
                this.placeLog(levelSimulatedReader, transformedBiConsumer, randomSource, transform(pos, fallDirection, origin), treeConfiguration);
                this.placeLog(levelSimulatedReader, transformedBiConsumer, randomSource, transform(pos.east(), fallDirection, origin), treeConfiguration);
                this.placeLog(levelSimulatedReader, transformedBiConsumer, randomSource, transform(pos.south(), fallDirection, origin), treeConfiguration);
                this.placeLog(levelSimulatedReader, transformedBiConsumer, randomSource, transform(pos.east().south(), fallDirection, origin), treeConfiguration);
            }
        }

        list.add(new FoliagePlacer.FoliageAttachment(transformed(x, topY, z, fallDirection, origin), 0, true));

        for (int dx = -1; dx <= 2; dx++) {
            for (int dz = -1; dz <= 2; dz++) {
                if ((dx < 0 || dx > 1 || dz < 0 || dz > 1) && randomSource.nextInt(3) <= 0) {
                    int t = randomSource.nextInt(3) + 2;

                    for (int u = 0; u < t; u++) {
                        this.placeLog(levelSimulatedReader, transformedBiConsumer, randomSource, transformed(originX + dx, topY - u - 1, originZ + dz, fallDirection, origin), treeConfiguration);
                    }

                    list.add(new FoliagePlacer.FoliageAttachment(transformed(x + dx, topY, z + dz, fallDirection, origin), 0, false));
                }
            }
        }

        return list;
    }

    public static BlockPos transformed(int x, int y, int z, Direction direction, BlockPos origin) {
        return transform(new BlockPos(x, y, z), direction, origin);
    }

    public static BlockPos transform(BlockPos blockPos, Direction direction, BlockPos origin) {
        int x = blockPos.getX();
        int y = blockPos.getY();
        int z = blockPos.getZ();

        int originX = origin.getX();
        int originY = origin.getY();
        int originZ = origin.getZ();
        return switch (direction) {
            case NORTH -> new BlockPos(x, originY + z - originZ, originZ - y + originY);
            case SOUTH -> new BlockPos(x, originY - z + originZ + 1, originZ + y - originY);
            case EAST -> new BlockPos(originX + y - originY, originY + x - originX, z);
            case WEST -> new BlockPos(originX - y + originY, originY + originX - x + 1, z);
            default -> throw new IllegalStateException();
        };
    }


}
