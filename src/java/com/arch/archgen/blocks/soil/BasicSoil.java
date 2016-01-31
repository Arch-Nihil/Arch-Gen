package com.arch.archgen.blocks.soil;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BasicSoil extends Block {

	public BasicSoil() {
        super(Material.grass);
        this.setStepSound(soundTypeGrass);
	}

}
