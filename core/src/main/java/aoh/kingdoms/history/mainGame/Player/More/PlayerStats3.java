// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.Player.More;

import aoc.kingdoms.lukasz.map.civilization.CivilizationRanking;
import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.ArrayList;
import java.util.List;

public class PlayerStats3
{
    public List<Integer> prestige;
    public List<Integer> population;
    
    public PlayerStats3() {
        this.prestige = new ArrayList<Integer>();
        this.population = new ArrayList<Integer>();
    }
    
    public final void updateData() {
        this.addPrestige();
        this.addPopulation();
    }
    
    public void addPopulation() {
        this.population.add((int)Math.ceil((double)Game.getCiv(Game.player.iCivID).getPopulationTotal()));
    }
    
    public void addPrestige() {
        this.prestige.add((int)Math.ceil(CivilizationRanking.getCivilizationRanking_INFO(Game.player.iCivID)));
    }
}
