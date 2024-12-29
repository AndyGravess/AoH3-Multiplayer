// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome.province;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome;

public class EventOutcome_Province_Religion_Capital extends EventOutcome
{
    public int value;
    
    public EventOutcome_Province_Religion_Capital(final int value) {
        this.value = value;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            Game.getProvince(Game.getCiv(iCivID).getCapitalProvinceID()).setReligion(this.value);
            Game.getProvince(Game.getCiv(iCivID).getCapitalProvinceID()).updateAfterReligionConversion();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.get("Religion") + ": ";
    }
    
    @Override
    public String getStringRight() {
        try {
            return Game.religionManager.getReligion(this.value).Name;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            return "";
        }
    }
    
    @Override
    public String getStringRight2(final int bonus_duration) {
        try {
            return Game.getProvince(Game.getCiv(Game.player.iCivID).getCapitalProvinceID()).getProvinceName();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            return null;
        }
    }
    
    @Override
    public int getImage() {
        return Images.religion;
    }
}
