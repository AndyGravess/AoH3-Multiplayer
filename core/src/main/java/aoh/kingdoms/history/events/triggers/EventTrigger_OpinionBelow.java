// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_OpinionBelow extends EventTrigger_Value
{
    public String civA;
    public String civB;
    public float value;
    
    public EventTrigger_OpinionBelow(final String civA, final String civB, final float value) {
        this.civA = civA;
        this.civB = civB;
        this.value = value;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        final int idA = Game.getCivID(this.civA);
        final int idB = Game.getCivID(this.civB);
        return idA > 0 && idB > 0 && Game.getCiv(idA).diplomacy.getRelation(idB) < this.value;
    }
    
    @Override
    public String getText() {
        return Game.lang.get("Opinion") + ": ";
    }
    
    @Override
    public String getText2() {
        return Game.lang.getCiv(this.civA) + " - " + Game.lang.getCiv(this.civB) + " < " + this.value;
    }
    
    @Override
    public String getText3() {
        final int idA = Game.getCivID(this.civA);
        final int idB = Game.getCivID(this.civB);
        if (idA > 0 && idB > 0) {
            return " [" + Game.lang.get("Currently") + ": " + CFG.getPrecision2(Game.getCiv(idA).diplomacy.getRelation(idB), 1) + "]";
        }
        return "";
    }
    
    @Override
    public int getImage() {
        return Images.relations;
    }
}
