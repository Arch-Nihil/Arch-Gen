package com.arch.archgen.blocks.bricks;

import com.arch.archgen.lib.G;

public class IgneousBricks extends BasicBricks {
	protected static final String subtype = "Igneous";
	protected static String texName = G.texName + type + subtype;
	
	public IgneousBricks(String genericName, float hard, float res, int lvl) {
		super(genericName, texName, hard, res, lvl);
    }
}