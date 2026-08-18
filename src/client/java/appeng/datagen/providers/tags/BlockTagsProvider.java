/*
 * This file is part of Applied Energistics 2.
 * Copyright (c) 2021, TeamAppliedEnergistics, All rights reserved.
 *
 * Applied Energistics 2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Applied Energistics 2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Applied Energistics 2.  If not, see <http://www.gnu.org/licenses/lgpl>.
 */

package appeng.datagen.providers.tags;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;

import appeng.api.ids.AETags;
import appeng.core.AppEng;
import appeng.core.ConventionTags;
import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.BlockDefinition;
import appeng.datagen.providers.IAE2DataProvider;

public class BlockTagsProvider extends net.neoforged.neoforge.common.data.BlockTagsProvider implements IAE2DataProvider {
    public BlockTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
        super(packOutput, registries, AppEng.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        // Black- and whitelist tags
        tag(AETags.SPATIAL_BLACKLIST)
                .add(blockKey(Blocks.BEDROCK))
                .addOptionalTag(ConventionTags.IMMOVABLE_BLOCKS);
        tag(AETags.ANNIHILATION_PLANE_BLOCK_BLACKLIST);
        tag(AETags.FACADE_BLOCK_WHITELIST)
                .add(blockKey(AEBlocks.QUARTZ_GLASS.block()), blockKey(AEBlocks.QUARTZ_VIBRANT_GLASS.block()),
                        blockKey(Blocks.CHISELED_BOOKSHELF), blockKey(Blocks.JUKEBOX), blockKey(Blocks.FURNACE), blockKey(Blocks.BLAST_FURNACE), blockKey(Blocks.DROPPER),
                        blockKey(Blocks.DISPENSER), blockKey(Blocks.CRAFTER), blockKey(Blocks.BARREL), blockKey(Blocks.BEE_NEST), blockKey(Blocks.BEEHIVE),
                        blockKey(Blocks.SCULK_CATALYST), blockKey(Blocks.SOUL_SAND), blockKey(Blocks.HONEY_BLOCK),
                        blockKey(AEBlocks.CONTROLLER.block()), blockKey(AEBlocks.CRAFTING_STORAGE_1K.block()),
                        blockKey(AEBlocks.CRAFTING_STORAGE_4K.block()), blockKey(AEBlocks.CRAFTING_STORAGE_16K.block()),
                        blockKey(AEBlocks.CRAFTING_STORAGE_64K.block()), blockKey(AEBlocks.CRAFTING_STORAGE_256K.block()),
                        blockKey(AEBlocks.CRAFTING_MONITOR.block()), blockKey(AEBlocks.CRAFTING_UNIT.block()),
                        blockKey(AEBlocks.CRAFTING_ACCELERATOR.block()))
                .addOptionalTag(ConventionTags.GLASS_BLOCK);
        tag(AETags.GROWTH_ACCELERATABLE)
                // TODO: Should all be in some conventional tag
                .add(blockKey(Blocks.BAMBOO_SAPLING), blockKey(Blocks.BAMBOO), blockKey(Blocks.SUGAR_CANE), blockKey(Blocks.VINE),
                        blockKey(Blocks.TWISTING_VINES), blockKey(Blocks.WEEPING_VINES), blockKey(Blocks.CAVE_VINES), blockKey(Blocks.SWEET_BERRY_BUSH),
                        blockKey(Blocks.NETHER_WART), blockKey(Blocks.KELP), blockKey(Blocks.COCOA))
                .addOptionalTag(ConventionTags.CROPS)
                .addOptionalTag(ConventionTags.SAPLINGS)
                .addTag(ConventionTags.BUDDING_BLOCKS_BLOCKS);

        tag(ConventionTags.BUDDING_BLOCKS_BLOCKS)
                .add(blockKey(AEBlocks.FLAWLESS_BUDDING_QUARTZ.block()))
                .add(blockKey(AEBlocks.FLAWED_BUDDING_QUARTZ.block()))
                .add(blockKey(AEBlocks.CHIPPED_BUDDING_QUARTZ.block()))
                .add(blockKey(AEBlocks.DAMAGED_BUDDING_QUARTZ.block()));
        tag(ConventionTags.BUDS_BLOCKS)
                .add(blockKey(AEBlocks.SMALL_QUARTZ_BUD.block()))
                .add(blockKey(AEBlocks.MEDIUM_QUARTZ_BUD.block()))
                .add(blockKey(AEBlocks.LARGE_QUARTZ_BUD.block()));
        tag(ConventionTags.CLUSTERS_BLOCKS)
                .add(blockKey(AEBlocks.QUARTZ_CLUSTER.block()));

        tag(ConventionTags.CERTUS_QUARTZ_STORAGE_BLOCK_BLOCK)
                .add(blockKey(AEBlocks.QUARTZ_BLOCK.block()));
        tag(Tags.Blocks.STORAGE_BLOCKS)
                .addTag(ConventionTags.CERTUS_QUARTZ_STORAGE_BLOCK_BLOCK);

        // Special behavior is associated with this tag, so our walls need to be added to it
        tag(BlockTags.WALLS).add(
                blockKey(AEBlocks.SKY_STONE_WALL.block()),
                blockKey(AEBlocks.SMOOTH_SKY_STONE_WALL.block()),
                blockKey(AEBlocks.SKY_STONE_BRICK_WALL.block()),
                blockKey(AEBlocks.SKY_STONE_SMALL_BRICK_WALL.block()),
                blockKey(AEBlocks.FLUIX_WALL.block()),
                blockKey(AEBlocks.QUARTZ_WALL.block()),
                blockKey(AEBlocks.CUT_QUARTZ_WALL.block()),
                blockKey(AEBlocks.SMOOTH_QUARTZ_WALL.block()),
                blockKey(AEBlocks.QUARTZ_BRICK_WALL.block()),
                blockKey(AEBlocks.CHISELED_QUARTZ_WALL.block()),
                blockKey(AEBlocks.QUARTZ_PILLAR_WALL.block()));

        tag(Tags.Blocks.CHESTS).add(blockKey(AEBlocks.SKY_STONE_CHEST.block()), blockKey(AEBlocks.SMOOTH_SKY_STONE_CHEST.block()));
        tag(ConventionTags.GLASS_BLOCK).add(blockKey(AEBlocks.QUARTZ_GLASS.block()), blockKey(AEBlocks.QUARTZ_VIBRANT_GLASS.block()));

        // Fixtures should cause walls to have posts
        tag(BlockTags.WALL_POST_OVERRIDE).add(blockKey(AEBlocks.QUARTZ_FIXTURE.block()), blockKey(AEBlocks.LIGHT_DETECTOR.block()));

        addEffectiveTools();
    }

    /**
     * All sky-stone related blocks should be minable with iron-pickaxes and up.
     */
    private static final BlockDefinition<?>[] SKY_STONE_BLOCKS = {
            AEBlocks.SKY_STONE_BLOCK,
            AEBlocks.SMOOTH_SKY_STONE_BLOCK,
            AEBlocks.SKY_STONE_BRICK,
            AEBlocks.SKY_STONE_SMALL_BRICK,
            AEBlocks.SKY_STONE_CHEST,
            AEBlocks.SMOOTH_SKY_STONE_CHEST,
            AEBlocks.SKY_STONE_STAIRS,
            AEBlocks.SMOOTH_SKY_STONE_STAIRS,
            AEBlocks.SKY_STONE_BRICK_STAIRS,
            AEBlocks.SKY_STONE_SMALL_BRICK_STAIRS,
            AEBlocks.SKY_STONE_WALL,
            AEBlocks.SMOOTH_SKY_STONE_WALL,
            AEBlocks.SKY_STONE_BRICK_WALL,
            AEBlocks.SKY_STONE_SMALL_BRICK_WALL,
            AEBlocks.SKY_STONE_SLAB,
            AEBlocks.SMOOTH_SKY_STONE_SLAB,
            AEBlocks.SKY_STONE_BRICK_SLAB,
            AEBlocks.SKY_STONE_SMALL_BRICK_SLAB
    };

    private static ResourceKey<Block> blockKey(Block block) {
        return block.builtInRegistryHolder().key();
    }

    private void addEffectiveTools() {
        Map<BlockDefinition<?>, List<TagKey<Block>>> specialTags = new HashMap<>();
        for (var skyStoneBlock : SKY_STONE_BLOCKS) {
            specialTags.put(skyStoneBlock, List.of(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL));
        }
        var defaultTags = List.of(BlockTags.MINEABLE_WITH_PICKAXE);

        for (var block : AEBlocks.getBlocks()) {
            for (var desiredTag : specialTags.getOrDefault(block, defaultTags)) {
                tag(desiredTag).add(blockKey(block.block()));
            }
        }

    }
}
