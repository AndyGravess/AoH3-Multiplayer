// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome.province;

import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome;

public class EventOutcome_Province_Manpower_All extends EventOutcome
{
    public float value;
    
    public EventOutcome_Province_Manpower_All(final float value) {
        this.value = value;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            for (int i = 0; i < Game.getCiv(iCivID).getNumOfProvinces(); ++i) {
                Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).setManpower(Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).getManpower() + this.value);
            }
            Game.gameThreadTurns.addCivUpdateMaxManpower(iCivID);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.get("LocalManpower") + ": ";
    }
    
    @Override
    public String getStringRight() {
        return ((this.value > 0.0f) ? "+" : "") + CFG.getPrecision2(this.value, 100);
    }
    
    @Override
    public String getStringRight2(final int bonus_duration) {
        return Game.lang.get("AllProvinces");
    }
    
    @Override
    public int getImage() {
        return Game_Calendar.IMG_MANPOWER;
    }
}
