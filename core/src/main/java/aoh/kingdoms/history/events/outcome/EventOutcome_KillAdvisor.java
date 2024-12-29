// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.map.advisors.AdvisorManager;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventOutcome_KillAdvisor extends EventOutcome
{
    public int value;
    
    public EventOutcome_KillAdvisor(final int value) {
        this.value = value;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            Game.advisorManager.advisorDied(iCivID, this.value);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.get("AdvisorWillDie") + ": ";
    }
    
    @Override
    public String getStringRight() {
        final AdvisorManager advisorManager = Game.advisorManager;
        return AdvisorManager.getAdvisorGroupName(this.value);
    }
    
    @Override
    public int getImage() {
        return Images.council;
    }
}
