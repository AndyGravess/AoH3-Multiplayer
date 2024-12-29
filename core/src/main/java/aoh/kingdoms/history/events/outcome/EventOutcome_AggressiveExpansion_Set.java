// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventOutcome_AggressiveExpansion_Set extends EventOutcome
{
    public float value;
    
    public EventOutcome_AggressiveExpansion_Set(final float value) {
        this.value = value;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            Game.getCiv(iCivID).setAggressiveExpansion(this.value);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.get("AggressiveExpansion") + ": ";
    }
    
    @Override
    public String getStringRight() {
        return "" + CFG.getPrecision2(this.value, 1);
    }
    
    @Override
    public int getImage() {
        return Images.aggressiveExpansion;
    }
}
