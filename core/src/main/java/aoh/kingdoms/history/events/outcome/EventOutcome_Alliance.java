// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventOutcome_Alliance extends EventOutcome
{
    public String fromCivTAG;
    public String toCivTag;
    
    public EventOutcome_Alliance(final String fromCivTAG, final String toCivTag) {
        this.fromCivTAG = fromCivTAG;
        this.toCivTag = toCivTag;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            final int fromCivID = Game.getCivID(this.fromCivTAG);
            final int toCivID = Game.getCivID(this.toCivTag);
            if (fromCivID > 0 && toCivID > 0 && !DiplomacyManager.isAtWar(fromCivID, toCivID)) {
                DiplomacyManager.addAlliance(fromCivID, toCivID);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.get("Alliance") + ": ";
    }
    
    @Override
    public String getStringRight() {
        return Game.lang.getCiv(this.fromCivTAG) + " - " + Game.lang.getCiv(this.toCivTag);
    }
    
    @Override
    public int getImage() {
        return Images.alliance;
    }
}
