// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventOutcome_Manpower extends EventOutcome
{
    public int value;
    
    public EventOutcome_Manpower(final int value) {
        this.value = value;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            Game.getCiv(iCivID).setManpower(Game.getCiv(iCivID).fManpower + this.value);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.get("Manpower") + ": ";
    }
    
    @Override
    public String getStringRight() {
        return ((this.value > 0) ? "+" : "") + CFG.getPrecision2((float)this.value, 1);
    }
    
    @Override
    public int getImage() {
        return Game_Calendar.IMG_MANPOWER;
    }
}
