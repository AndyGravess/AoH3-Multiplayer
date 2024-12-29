// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_ProvinceIsOccupied extends EventTrigger_Value
{
    public int provID;
    
    public EventTrigger_ProvinceIsOccupied(final int provID) {
        this.provID = provID;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        return this.provID >= 0 && Game.getProvince(this.provID).isOccupied();
    }
    
    @Override
    public String getText() {
        return Game.getProvince(this.provID).getProvinceName() + ", " + Game.lang.get("Occupied");
    }
    
    @Override
    public String getText2() {
        return "";
    }
    
    @Override
    public String getText3() {
        return "";
    }
    
    @Override
    public int getImage() {
        return Images.provinces;
    }
}
