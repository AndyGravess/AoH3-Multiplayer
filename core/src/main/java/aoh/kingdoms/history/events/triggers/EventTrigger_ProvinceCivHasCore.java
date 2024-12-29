// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_ProvinceCivHasCore extends EventTrigger_Value
{
    public int provID;
    public String civA;
    
    public EventTrigger_ProvinceCivHasCore(final int provID, final String civA) {
        this.provID = provID;
        this.civA = civA;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        final int idA = Game.getCivID(this.civA);
        return idA > 0 && Game.getProvince(this.provID).haveACore(idA);
    }
    
    @Override
    public String getText() {
        final int idA = Game.getCivID(this.civA);
        if (idA > 0) {
            return Game.getCiv(idA).getCivName() + ", " + Game.lang.get("HaveACore") + ": ";
        }
        return " -- ";
    }
    
    @Override
    public String getText2() {
        return Game.getProvince(this.provID).getProvinceName();
    }
    
    @Override
    public String getText3() {
        final int idA = Game.getCivID(this.civA);
        if (idA > 0) {
            return " [" + Game.lang.get("Currently") + ": " + Game.getProvince(this.provID).haveACore(idA) + "]";
        }
        return " --";
    }
    
    @Override
    public int getImage() {
        return Images.core;
    }
}
