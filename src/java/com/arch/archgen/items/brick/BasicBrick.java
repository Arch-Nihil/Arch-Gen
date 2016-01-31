package com.arch.archgen.items.brick;

import com.arch.archgen.items.BasicItems;
import com.arch.archgen.lib.G;
import com.arch.archgen.tabs.Tabs;

public class BasicBrick extends BasicItems {
	protected static final String type = "Brick";
	
	public BasicBrick(String name, String tName) {
		super(Tabs.tabBrick, type, name + type, tName + name);
		
		G.brickArray.add(this);
	}
}
