// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome;

public class EventOutcome_Legacy_Monthly extends EventOutcome
{
    public float value;
    
    public EventOutcome_Legacy_Monthly(final float value) {
        this.value = value;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            Game.getCiv(iCivID).addLegacy(Game.getCiv(iCivID).getLegacyPerMonth() * this.value);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.get("Legacy") + ": ";
    }
    
    @Override
    public String getStringRight() {
        return ((this.value > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).getLegacyPerMonth() * this.value, 100);
    }
    
    @Override
    public int getImage() {
        return Images.legacy;
    }
}
