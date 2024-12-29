// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_HaveMilitaryAccess extends EventTrigger_Value
{
    public String civA;
    public String civB;
    
    public EventTrigger_HaveMilitaryAccess(final String civA, final String civB) {
        this.civA = civA;
        this.civB = civB;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        final int idA = Game.getCivID(this.civA);
        final int idB = Game.getCivID(this.civB);
        return idA > 0 && idB > 0 && Game.getCiv(idA).diplomacy.haveMilitaryAccess(idB);
    }
    
    @Override
    public String getText() {
        return Game.lang.get("MilitaryAccess");
    }
    
    @Override
    public String getText2() {
        return Game.lang.getCiv(this.civA) + " -> " + Game.lang.getCiv(this.civB);
    }
    
    @Override
    public String getText3() {
        return "";
    }
    
    @Override
    public int getImage() {
        return Images.militaryAccess;
    }
}
