// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events;

import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.CFG;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome;
import java.util.List;

public class EventOption
{
    public String name;
    public int bonus_duration;
    public float ai;
    public List<EventOutcome> outcome;
    
    public EventOption() {
        this.name = "";
        this.bonus_duration = 1;
        this.ai = 1.0f;
        this.outcome = new ArrayList<EventOutcome>();
    }
    
    public final void executeOutcome() {
        try {
            for (int i = 0; i < this.outcome.size(); ++i) {
                this.outcome.get(i).update();
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void executeOutcome(final int iCivID) {
        try {
            if (Game.getCiv(iCivID).eventProvinceID >= 0) {
                for (int i = 0; i < this.outcome.size(); ++i) {
                    this.outcome.get(i).updateProvince(Game.getCiv(iCivID).eventProvinceID);
                }
            }
            for (int i = 0; i < this.outcome.size(); ++i) {
                this.outcome.get(i).updateCiv(iCivID, this.bonus_duration);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
}
