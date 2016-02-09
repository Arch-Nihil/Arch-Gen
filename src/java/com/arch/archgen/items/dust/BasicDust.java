package com.arch.archgen.items.dust;

import com.arch.archgen.items.BasicItems;
import com.arch.archgen.lib.G;
import com.arch.archgen.tabs.Tabs;

public class BasicDust extends BasicItems {
	protected static final String type = "Dust";
	
	public BasicDust(String name, String sType, String tName) {
		super(Tabs.tabDust, type, sType, name + type, tName + name);
		
		G.dustArray.add(this);
	}
}
