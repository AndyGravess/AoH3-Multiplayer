// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.menusInGame.InGame_Sandbox;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventOutcome_PlayerChangeCiv extends EventOutcome
{
    public String value;
    
    public EventOutcome_PlayerChangeCiv(final String value) {
        this.value = value;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            final int tID = Game.getCivID(this.value);
            if (tID > 0 && iCivID != tID && Game.getCiv(tID).getNumOfProvinces() > 0) {
                InGame_Sandbox.setPlayerCiv(tID);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.get("Player") + ", " + Game.lang.get("Civilization") + ": ";
    }
    
    @Override
    public String getStringRight() {
        final int tID = Game.getCivID(this.value);
        if (tID > 0) {
            return Game.getCiv(tID).getCivName();
        }
        return " -- ";
    }
    
    @Override
    public int getImage() {
        return Images.play;
    }
}
