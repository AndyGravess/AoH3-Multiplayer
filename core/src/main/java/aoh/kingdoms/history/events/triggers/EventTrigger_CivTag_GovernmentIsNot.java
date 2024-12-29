// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_CivTag_GovernmentIsNot extends EventTrigger_Value
{
    public String civA;
    public int value;
    
    public EventTrigger_CivTag_GovernmentIsNot(final String civA, final int value) {
        this.civA = civA;
        this.value = value;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        final int idA = Game.getCivID(this.civA);
        return idA > 0 && Game.getCiv(idA).getIdeologyID() != this.value;
    }
    
    @Override
    public String getText() {
        final int idA = Game.getCivID(this.civA);
        if (idA > 0) {
            return Game.getCiv(idA).getCivName() + ", " + Game.lang.get("Government") + ", " + Game.lang.get("NOT") + ": ";
        }
        return Game.lang.get("Government") + " == ";
    }
    
    @Override
    public String getText2() {
        return "" + Game.ideologiesManager.getIdeology(this.value).Name;
    }
    
    @Override
    public String getText3() {
        return "";
    }
    
    @Override
    public int getImage() {
        return Images.government;
    }
}
