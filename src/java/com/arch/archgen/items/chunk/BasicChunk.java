package com.arch.archgen.items.chunk;

import com.arch.archgen.items.BasicItems;
import com.arch.archgen.lib.G;
import com.arch.archgen.tabs.Tabs;

public class BasicChunk extends BasicItems {
	protected static final String type = "Chunk";
	
	public BasicChunk(String name, String sType, String tName) {
		super(Tabs.tabChunk, type, sType, name + type, tName + name);
		
		G.chunkArray.add(this);
	}
}
