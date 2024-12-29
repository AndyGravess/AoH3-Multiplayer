// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.map.RulersManager;

public class EventOutcome_KillRuler extends EventOutcome
{
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            RulersManager.deathOfRuler(iCivID);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.get("DeathOfALeader") + ": ";
    }
    
    @Override
    public String getStringRight() {
        return Game.getCiv(Game.player.iCivID).ruler.Name;
    }
    
    @Override
    public int getImage() {
        return Images.council;
    }
}
