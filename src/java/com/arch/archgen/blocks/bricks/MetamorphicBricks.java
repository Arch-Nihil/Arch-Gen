package com.arch.archgen.blocks.bricks;

import com.arch.archgen.lib.G;

public class MetamorphicBricks extends BasicBricks {
	protected static final String subtype = "Metamorphic";
	protected static String texName = G.texName + type + subtype;
	
	public MetamorphicBricks(String genericName, float hard, float res, int lvl) {
		super(genericName, subtype, texName, hard, res, lvl);
    }
}