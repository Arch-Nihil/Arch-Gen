package com.arch.archgen.items.chunk;

import com.arch.archgen.lib.G;

public class MetamorphicChunk extends BasicChunk {
	protected static final String subtype = "Metamorphic";
	protected static String texName = G.texName + type + subtype;
	
	public MetamorphicChunk(String name) {
		super(name, texName);
	}
}
