// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventOutcome_AddVariable_Civ extends EventOutcome
{
    public String addToCiv;
    public String value;
    
    public EventOutcome_AddVariable_Civ(final String addToCiv, final String value) {
        this.addToCiv = addToCiv;
        this.value = value;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            final int toCivID = Game.getCivID(this.addToCiv);
            if (toCivID > 0) {
                Game.getCiv(toCivID).eventsDataVariables.addVariable(this.value);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
}
