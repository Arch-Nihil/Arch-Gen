package com.arch.archgen.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldgenSubstitute extends WorldGenerator {
	private Block block;
    private Block target;

    public WorldgenSubstitute(Block block, Block target) {
    	this.block = block;
        this.target = target;
    }
    
    public WorldgenSubstitute(Block block) {
        this(block, Blocks.stone);
    }
	
	@Override
	public boolean generate(World w, Random r, int x, int y, int z) {
		if (w.getBlock(x, y, z).equals(target)) {
			w.setBlock(x, y, z, this.block, w.rand.nextInt(3), 2);
		}
		
		return true;
	};
}