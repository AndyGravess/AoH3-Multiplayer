// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_CivCapital_IsUnderSiege extends EventTrigger_Value
{
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        try {
            if (Game.getCiv(iCivID).getCapitalProvinceID() >= 0 && Game.getProvince(Game.getCiv(iCivID).getCapitalProvinceID()).getCivID() == iCivID) {
                return Game.getProvinceData4(Game.getCiv(iCivID).getCapitalProvinceID()).isUnderSiege();
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return false;
    }
    
    @Override
    public String getText() {
        return Game.lang.get("Capital") + ", " + Game.lang.get("UnderSiege");
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
