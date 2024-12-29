// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.Player.More;

import aoc.kingdoms.lukasz.jakowski.Game;

public class PlayerStats
{
    public long startingPopulation;
    public float startingEconomy;
    public int startingProvinces;
    public int numOfWars;
    public int recruitedRegiments;
    public int recruitedGenerals;
    
    public PlayerStats() {
        this.startingPopulation = 0L;
        this.startingEconomy = 0.0f;
        this.startingProvinces = 0;
        this.numOfWars = 0;
        this.recruitedRegiments = 0;
        this.recruitedGenerals = 0;
    }
    
    public final void initStartingData() {
        this.startingPopulation = Game.getCiv(Game.player.iCivID).getPopulationTotal();
        this.startingEconomy = Game.getCiv(Game.player.iCivID).getEconomyTotal();
        this.startingProvinces = Game.getCiv(Game.player.iCivID).getNumOfProvinces();
    }
}
