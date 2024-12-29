// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.events.EventsManager;

public class EventOutcome_RunEvent extends EventOutcome
{
    public String eventID;
    
    public EventOutcome_RunEvent(final String eventID) {
        this.eventID = eventID;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            EventsManager.runEvent.add(this.eventID);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.get("Event");
    }
    
    @Override
    public String getStringRight() {
        return "";
    }
    
    @Override
    public int getImage() {
        return Images.time;
    }
}
