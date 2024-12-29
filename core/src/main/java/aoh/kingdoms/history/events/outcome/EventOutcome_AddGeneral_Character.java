// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.CharactersManager;

public class EventOutcome_AddGeneral_Character extends EventOutcome
{
    public String sGeneral;
    
    public EventOutcome_AddGeneral_Character(final String sGeneral) {
        this.sGeneral = sGeneral;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            CharactersManager.loadGeneral(iCivID, this.sGeneral, -99, -99);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.get("RecruitGeneral") + ": ";
    }
    
    @Override
    public String getStringRight() {
        return "" + this.sGeneral;
    }
    
    @Override
    public int getImage() {
        return Images.general;
    }
}
