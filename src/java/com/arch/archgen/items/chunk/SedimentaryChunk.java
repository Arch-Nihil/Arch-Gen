package com.arch.archgen.items.chunk;

import com.arch.archgen.lib.G;

public class SedimentaryChunk extends BasicChunk {
	protected static final String subtype = "Sedimentary";
	protected static String texName = G.texName + type + subtype;
	
	public SedimentaryChunk(String name) {
		super(name, subtype, texName);
	}
}
