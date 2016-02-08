package com.arch.archgen.items;

import com.arch.archgen.config.Config;
import com.arch.archgen.items.brick.IgneousBrick;
import com.arch.archgen.items.brick.MineralBrick;
import com.arch.archgen.items.brick.SedimentaryBrick;
import com.arch.archgen.items.chunk.IgneousChunk;
import com.arch.archgen.items.chunk.MineralChunk;
import com.arch.archgen.items.chunk.SedimentaryChunk;
import com.arch.archgen.items.dust.IgneousDust;
import com.arch.archgen.items.dust.MineralDust;
import com.arch.archgen.items.dust.SedimentaryDust;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemsRegistry {

	public static Item graniteDust, basaltDust, gabbroDust, dioriteDust, peridotiteDust, rhyoliteDust;
	public static Item siltstoneDust, limestoneDust, sandstoneDust;
	public static Item quartzDust, alkaliFeldsparDust, plagioclaseDust, hornblendeDust, calciteDust, olivineDust, enstatiteDust, diopsideDust;
	
	public static Item graniteChunk, basaltChunk, gabbroChunk, dioriteChunk, peridotiteChunk, rhyoliteChunk;
	public static Item siltstoneChunk, limestoneChunk, sandstoneChunk;
	public static Item quartzChunk, alkaliFeldsparChunk, plagioclaseChunk, hornblendeChunk, calciteChunk, olivineChunk, enstatiteChunk, diopsideChunk;
	
	public static Item graniteBrick, basaltBrick, gabbroBrick, dioriteBrick, peridotiteBrick, rhyoliteBrick;
	public static Item siltstoneBrick, limestoneBrick, sandstoneBrick;
	public static Item quartzBrick, alkaliFeldsparBrick, plagioclaseBrick, hornblendeBrick, calciteBrick, olivineBrick, enstatiteBrick, diopsideBrick;
	
	public static void init() {
		GameRegistry.registerItem(graniteDust = new IgneousDust("Granite"), "GraniteDust");
		GameRegistry.registerItem(basaltDust = new IgneousDust("Basalt"), "BasaltDust");
		GameRegistry.registerItem(gabbroDust = new IgneousDust("Gabbro"), "GabbroDust");
		GameRegistry.registerItem(dioriteDust = new IgneousDust("Diorite"), "DioriteDust");
		GameRegistry.registerItem(peridotiteDust = new IgneousDust("Peridotite"), "PeridotiteDust");
		GameRegistry.registerItem(rhyoliteDust = new IgneousDust("Rhyolite"), "RhyoliteDust");
		
		GameRegistry.registerItem(siltstoneDust = new SedimentaryDust("Siltstone"), "SiltstoneDust");
		GameRegistry.registerItem(limestoneDust = new SedimentaryDust("Limestone"), "LimestoneDust");
		GameRegistry.registerItem(sandstoneDust = new SedimentaryDust("Sandstone"), "SandstoneDust");
		
		GameRegistry.registerItem(quartzDust = new MineralDust("Quartz"), "QuartzDust");
		GameRegistry.registerItem(alkaliFeldsparDust = new MineralDust("AlkaliFeldspar"), "AlkaliFeldsparDust");
		GameRegistry.registerItem(plagioclaseDust = new MineralDust("Plagioclase"), "PlagioclaseDust");
		GameRegistry.registerItem(hornblendeDust = new MineralDust("Hornblende"), "HornblendeDust");
		GameRegistry.registerItem(calciteDust = new MineralDust("Calcite"), "CalciteDust");
		GameRegistry.registerItem(olivineDust = new MineralDust("Olivine"), "OlivineDust");
		GameRegistry.registerItem(enstatiteDust = new MineralDust("Enstatite"), "EnstatiteDust");
		GameRegistry.registerItem(diopsideDust = new MineralDust("Diopside"), "DiopsideDust");
		
		GameRegistry.registerItem(graniteChunk = new IgneousChunk("Granite"), "GraniteChunk");
		GameRegistry.registerItem(basaltChunk = new IgneousChunk("Basalt"), "BasaltChunk");
		GameRegistry.registerItem(gabbroChunk = new IgneousChunk("Gabbro"), "GabbroChunk");
		GameRegistry.registerItem(dioriteChunk = new IgneousChunk("Diorite"), "DioriteChunk");
		GameRegistry.registerItem(peridotiteChunk = new IgneousChunk("Peridotite"), "PeridotiteChunk");
		GameRegistry.registerItem(rhyoliteChunk = new IgneousChunk("Rhyolite"), "RhyoliteChunk");
		
		GameRegistry.registerItem(siltstoneChunk = new SedimentaryChunk("Siltstone"), "SiltstoneChunk");
		GameRegistry.registerItem(limestoneChunk = new SedimentaryChunk("Limestone"), "LimestoneChunk");
		GameRegistry.registerItem(sandstoneChunk = new SedimentaryChunk("Sandstone"), "SandstoneChunk");
		
		GameRegistry.registerItem(quartzChunk = new MineralChunk("Quartz"), "QuartzChunk");
		GameRegistry.registerItem(alkaliFeldsparChunk = new MineralChunk("AlkaliFeldspar"), "AlkaliFeldsparChunk");
		GameRegistry.registerItem(plagioclaseChunk = new MineralChunk("Plagioclase"), "PlagioclaseChunk");
		GameRegistry.registerItem(hornblendeChunk = new MineralChunk("Hornblende"), "HornblendeChunk");
		GameRegistry.registerItem(calciteChunk = new MineralChunk("Calcite"), "CalciteChunk");
		GameRegistry.registerItem(olivineChunk = new MineralChunk("Olivine"), "OlivineChunk");
		GameRegistry.registerItem(enstatiteChunk = new MineralChunk("Enstatite"), "EnstatiteChunk");
		GameRegistry.registerItem(diopsideChunk = new MineralChunk("Diopside"), "DiopsideChunk");
		
		if (Config.doGenBricks) {
			GameRegistry.registerItem(graniteBrick = new IgneousBrick("Granite"), "GraniteBrick");
			GameRegistry.registerItem(basaltBrick = new IgneousBrick("Basalt"), "BasaltBrick");
			GameRegistry.registerItem(gabbroBrick = new IgneousBrick("Gabbro"), "GabbroBrick");
			GameRegistry.registerItem(dioriteBrick = new IgneousBrick("Diorite"), "DioriteBrick");
			GameRegistry.registerItem(peridotiteBrick = new IgneousBrick("Peridotite"), "PeridotiteBrick");
			GameRegistry.registerItem(rhyoliteBrick = new IgneousBrick("Rhyolite"), "RhyoliteBrick");
			
			GameRegistry.registerItem(siltstoneBrick = new SedimentaryBrick("Siltstone"), "SiltstoneBrick");
			GameRegistry.registerItem(limestoneBrick = new SedimentaryBrick("Limestone"), "LimestoneBrick");
			GameRegistry.registerItem(sandstoneBrick = new SedimentaryBrick("Sandstone"), "SandstoneBrick");
			
			GameRegistry.registerItem(quartzBrick = new MineralBrick("Quartz"), "QuartzBrick");
			GameRegistry.registerItem(alkaliFeldsparBrick = new MineralBrick("AlkaliFeldspar"), "AlkaliFeldsparBrick");
			GameRegistry.registerItem(plagioclaseBrick = new MineralBrick("Plagioclase"), "PlagioclaseBrick");
			GameRegistry.registerItem(hornblendeBrick = new MineralBrick("Hornblende"), "HornblendeBrick");
			GameRegistry.registerItem(calciteBrick = new MineralBrick("Calcite"), "CalciteBrick");
			GameRegistry.registerItem(olivineBrick = new MineralBrick("Olivine"), "OlivineBrick");
			GameRegistry.registerItem(enstatiteBrick = new MineralBrick("Enstatite"), "EnstatiteBrick");
			GameRegistry.registerItem(diopsideBrick = new MineralBrick("Diopside"), "DiopsideBrick");
		}
	}
}
