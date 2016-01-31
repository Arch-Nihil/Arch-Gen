package com.arch.archgen.blocks.stone;

import com.arch.archgen.lib.G;

public class MetamorphicStone extends BasicStone {
	protected static final String subtype = "Metamorphic";
	protected static String texName = G.texName + type + subtype;
	
	public MetamorphicStone(String genericName, String rDrop, String rWeight, float hard, float res, int lvl) {
		super(genericName, texName, rDrop, rWeight, hard, res, lvl);	
    }
}