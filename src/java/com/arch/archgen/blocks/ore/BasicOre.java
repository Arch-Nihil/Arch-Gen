package com.arch.archgen.blocks.ore;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BasicOre extends Block {

	public BasicOre() {
        super(Material.rock);
        this.setStepSound(soundTypeStone);
	}

}
