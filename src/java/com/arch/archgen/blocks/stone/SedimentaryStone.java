package com.arch.archgen.blocks.stone;

import com.arch.archgen.lib.G;

public class SedimentaryStone extends BasicStone {
	protected static final String subtype = "Sedimentary";
	protected static String texName = G.texName + type + subtype;
	
	public SedimentaryStone(String genericName, String rDrop, String rWeight, float hard, float res, int lvl) {
		super(genericName, subtype, texName, rDrop, rWeight, hard, res, lvl);	
    }
}