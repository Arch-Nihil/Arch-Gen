package com.arch.archgen.items.chunk;

import com.arch.archgen.lib.G;

public class MineralChunk extends BasicChunk {
	protected static final String subtype = "Mineral";
	protected static String texName = G.texName + type + subtype;
	
	public MineralChunk(String name) {
		super(name, texName);
	}
}
