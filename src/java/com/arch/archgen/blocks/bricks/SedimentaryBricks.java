package com.arch.archgen.blocks.bricks;

import com.arch.archgen.lib.G;

public class SedimentaryBricks extends BasicBricks {
	protected static final String subtype = "Sedimentary";
	protected static String texName = G.texName + type + subtype;
	
	public SedimentaryBricks(String genericName, float hard, float res, int lvl) {
		super(genericName, subtype, texName, hard, res, lvl);
    }
}