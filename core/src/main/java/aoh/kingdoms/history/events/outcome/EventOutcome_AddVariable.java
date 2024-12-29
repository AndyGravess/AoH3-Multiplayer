// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventOutcome_AddVariable extends EventOutcome
{
    public String value;
    
    public EventOutcome_AddVariable(final String value) {
        this.value = value;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            Game.getCiv(iCivID).eventsDataVariables.addVariable(this.value);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
}
