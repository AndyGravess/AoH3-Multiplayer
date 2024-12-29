// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventOutcome_AI_Aggression extends EventOutcome
{
    public int value;
    
    public EventOutcome_AI_Aggression(final int value) {
        this.value = value;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            Game.getCiv(iCivID).setExtraAggressiveness(Game.getCiv(iCivID).getExtraAggressiveness() + this.value);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.get("AI");
    }
    
    @Override
    public String getStringRight() {
        return "";
    }
    
    @Override
    public int getImage() {
        return Images.ai;
    }
}
