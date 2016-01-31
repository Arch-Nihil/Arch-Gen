package com.arch.archgen.tabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class BasicTab extends CreativeTabs {
	private Item tI;
	
	public BasicTab(String tabName) {	 
        super(CreativeTabs.getNextID(), tabName);
        this.setNoScrollbar();
    }
	
	public void setTabIcon(Item tIcon) {
		tI = tIcon;
	}
	
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        return tI;
    }
 
    public boolean hasSearchBar() {
        return true;
    }
}
