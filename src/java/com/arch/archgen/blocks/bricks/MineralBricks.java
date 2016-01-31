package com.arch.archgen.blocks.bricks;

import com.arch.archgen.lib.G;

public class MineralBricks extends BasicBricks {
	protected static final String subtype = "Mineral";
	protected static String texName = G.texName + type + subtype;
	
	public MineralBricks(String genericName, float hard, float res, int lvl) {
		super(genericName, texName, hard, res, lvl);
    }
}