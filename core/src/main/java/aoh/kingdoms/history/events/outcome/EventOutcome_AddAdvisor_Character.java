// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.map.advisors.AdvisorManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.CharactersManager;

public class EventOutcome_AddAdvisor_Character extends EventOutcome
{
    public String sName;
    public int advisorTypeID;
    
    public EventOutcome_AddAdvisor_Character(final String sName, final int advisorTypeID) {
        this.sName = sName;
        this.advisorTypeID = advisorTypeID;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            CharactersManager.loadAdvisor(iCivID, this.sName, this.advisorTypeID);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.get("HireAdvisor") + ": ";
    }
    
    @Override
    public String getStringRight() {
        return "" + this.sName + " - " + AdvisorManager.getAdvisorGroupName(this.advisorTypeID);
    }
    
    @Override
    public int getImage() {
        return Images.council;
    }
}
