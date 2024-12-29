// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.map.civilization.CivilizationsNeighbors;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_CivsAreNeighbors extends EventTrigger_Value
{
    public String civA;
    public String civB;
    
    public EventTrigger_CivsAreNeighbors(final String civA, final String civB) {
        this.civA = civA;
        this.civB = civB;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        final int idA = Game.getCivID(this.civA);
        final int idB = Game.getCivID(this.civB);
        if (idA > 0 && idB > 0) {
            for (int i = 0; i < Game.getCiv(idA).civNeighbors.civsSize; ++i) {
                if (Game.getCiv(idA).civNeighbors.civs.get(i).civID == idB && Game.getCiv(idA).civNeighbors.civs.get(i).byLand) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public String getText() {
        return Game.lang.get("Neighbors") + ": ";
    }
    
    @Override
    public String getText2() {
        return "" + Game.lang.getCiv(this.civA) + " - " + Game.lang.getCiv(this.civB);
    }
    
    @Override
    public String getText3() {
        return "";
    }
    
    @Override
    public int getImage() {
        return Images.frontLine;
    }
}
