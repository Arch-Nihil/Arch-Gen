package com.arch.archgen.blocks.soil;

import com.arch.archgen.lib.G;

public class BasicSand extends BasicSoil {
	protected static final String subtype = "Sand";
	protected static String texName = G.texName + type;
	
	public BasicSand(String genericName, String rDrop, String rWeight, float hard, float res) {
		super(genericName, subtype, texName, rDrop, rWeight, hard, res);
		
		this.setStepSound(soundTypeSand);
    }
}
