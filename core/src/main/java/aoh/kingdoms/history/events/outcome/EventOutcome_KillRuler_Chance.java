// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.map.RulersManager;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventOutcome_KillRuler_Chance extends EventOutcome
{
    public float value;
    
    public EventOutcome_KillRuler_Chance(final float value) {
        this.value = value;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            if (Game.oR.nextInt(1000) / 10.0f < this.value) {
                RulersManager.deathOfRuler(iCivID);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.get("EventChance") + ": " + CFG.getPrecision2(this.value, 10) + "%, " + Game.lang.get("DeathOfALeader") + ": ";
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
