// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_CivCapital_ReligionIs extends EventTrigger_Value
{
    public int value;
    
    public EventTrigger_CivCapital_ReligionIs(final int value) {
        this.value = value;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        try {
            if (Game.getCiv(iCivID).getCapitalProvinceID() >= 0 && Game.getProvince(Game.getCiv(iCivID).getCapitalProvinceID()).getCivID() == iCivID) {
                return Game.getProvince(Game.getCiv(iCivID).getCapitalProvinceID()).getReligion() == this.value;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return false;
    }
    
    @Override
    public String getText() {
        return Game.lang.get("Capital") + ", " + Game.lang.get("Religion") + ": ";
    }
    
    @Override
    public String getText2() {
        return "" + Game.religionManager.getReligion(this.value).Name;
    }
    
    @Override
    public String getText3() {
        return " [" + Game.lang.get("Currently") + ": " + Game.religionManager.getReligion(Game.getProvince(Game.getCiv(Game.player.iCivID).getCapitalProvinceID()).getReligion()) + "]";
    }
    
    @Override
    public int getImage() {
        return Images.religion;
    }
}
