// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventOutcome_Inflation extends EventOutcome
{
    public float value;
    
    public EventOutcome_Inflation(final float value) {
        this.value = value;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            Game.getCiv(iCivID).setInflation(Game.getCiv(iCivID).getInflation() + this.value / 100.0f);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.get("Inflation") + ": ";
    }
    
    @Override
    public String getStringRight() {
        return ((this.value > 0.0f) ? "+" : "") + CFG.getPrecision2(this.value, 100);
    }
    
    @Override
    public int getImage() {
        return Images.inflation;
    }
}
