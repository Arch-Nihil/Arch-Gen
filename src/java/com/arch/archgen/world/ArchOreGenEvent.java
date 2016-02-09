package com.arch.archgen.world;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.OreGenEvent;

public class ArchOreGenEvent extends OreGenEvent {
	int chunkX;
	int chunkZ;
	World w;
	
	public ArchOreGenEvent(World w, Random r, int chunkX, int chunkZ) {
		super(w, r, chunkX, chunkZ);
		this.chunkX = chunkX;
		this.chunkZ = chunkZ;
		this.w = w;
	}
}
