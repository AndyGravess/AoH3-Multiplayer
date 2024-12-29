// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_ProvinceIsUnderSiege extends EventTrigger_Value
{
    public int provID;
    
    public EventTrigger_ProvinceIsUnderSiege(final int provID) {
        this.provID = provID;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        return this.provID >= 0 && Game.getProvinceData4(this.provID).isUnderSiege();
    }
    
    @Override
    public String getText() {
        return Game.getProvince(this.provID).getProvinceName() + ", " + Game.lang.get("UnderSiege");
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
        return Game_Calendar.IMG_FORT_DEFENSE;
    }
}
