// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventOutcome_SetCiv2 extends EventOutcome
{
    public String fromCivTAG;
    public String toCivTag;
    
    public EventOutcome_SetCiv2(final String fromCivTAG, final String toCivTag) {
        this.fromCivTAG = fromCivTAG;
        this.toCivTag = toCivTag;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            final int fromCivID = Game.getCivID(this.fromCivTAG);
            if (fromCivID > 0) {
                Game.getCiv(fromCivID).setCivTag(this.toCivTag);
                Game.addSimpleTask(new Game.SimpleTask(this.toCivTag, fromCivID) {
                    @Override
                    public void update() {
                        final Game.LoadCivilizationData civData = Game.loadCivilization(this.taskKey);
                        Game.getCiv(this.id).updateCivilizationTAG(this.taskKey, civData.iR, civData.iG, civData.iB);
                    }
                });
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.getCiv(this.fromCivTAG) + " -> ";
    }
    
    @Override
    public String getStringRight() {
        return Game.lang.getCiv(this.toCivTag);
    }
    
    @Override
    public int getImage() {
        return Images.government;
    }
}
