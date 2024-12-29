// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_ProvinceControlledByCivNot extends EventTrigger_Value
{
    public int provID;
    public String civA;
    
    public EventTrigger_ProvinceControlledByCivNot(final int provID, final String civA) {
        this.provID = provID;
        this.civA = civA;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        final int idA = Game.getCivID(this.civA);
        return idA > 0 && Game.getProvince(this.provID).getCivID() != idA;
    }
    
    @Override
    public String getText() {
        return Game.getProvince(this.provID).getProvinceName() + ", " + Game.lang.get("ControlledBy") + ", " + Game.lang.get("NOT") + ": ";
    }
    
    @Override
    public String getText2() {
        final int idA = Game.getCivID(this.civA);
        if (idA > 0) {
            return Game.getCiv(idA).getCivName();
        }
        return " -- ";
    }
    
    @Override
    public String getText3() {
        return " [" + Game.lang.get("Currently") + ": " + Game.getCiv(Game.getProvince(this.provID).getCivID()).getCivName() + "]";
    }
    
    @Override
    public int getImage() {
        return Images.provinces;
    }
}
