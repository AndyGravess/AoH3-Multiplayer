// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome.province;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome;

public class EventOutcome_Province_Religion_ID extends EventOutcome
{
    public int provID;
    public int value;
    
    public EventOutcome_Province_Religion_ID(final int provID, final int value) {
        this.provID = provID;
        this.value = value;
    }
    
    @Override
    public void updateProvince(final int iProvinceID) {
        try {
            Game.getProvince(this.provID).setReligion(this.value);
            Game.getProvince(this.provID).updateAfterReligionConversion();
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
            return Game.getProvince(this.provID).getProvinceName();
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
