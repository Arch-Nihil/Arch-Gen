package com.arch.archgen.items.chunk;

import com.arch.archgen.lib.G;

public class IgneousChunk extends BasicChunk {
	protected static final String subtype = "Igneous";
	protected static String texName = G.texName + type + subtype;
	
	public IgneousChunk(String name) {
		super(name, texName);
	}
}
