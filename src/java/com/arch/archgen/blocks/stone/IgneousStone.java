package com.arch.archgen.blocks.stone;

import com.arch.archgen.lib.G;

public class IgneousStone extends BasicStone {
	protected static final String subtype = "Igneous";
	protected static String texName = G.texName + type + subtype;
	
	public IgneousStone(String genericName, String rDrop, String rWeight, float hard, float res, int lvl) {
		super(genericName, subtype, texName, rDrop, rWeight, hard, res, lvl);	
    }
}