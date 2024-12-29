// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventOutcome_SetCiv extends EventOutcome
{
    public String value;
    
    public EventOutcome_SetCiv(final String value) {
        this.value = value;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            Game.getCiv(iCivID).setCivTag(this.value);
            Game.addSimpleTask(new Game.SimpleTask(this.value, iCivID) {
                @Override
                public void update() {
                    final Game.LoadCivilizationData civData = Game.loadCivilization(this.taskKey);
                    Game.getCiv(this.id).updateCivilizationTAG(this.taskKey, civData.iR, civData.iG, civData.iB);
                }
            });
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.get("FormCivilization") + ": ";
    }
    
    @Override
    public String getStringRight() {
        return Game.lang.getCiv(this.value);
    }
    
    @Override
    public int getImage() {
        return Images.government;
    }
}
