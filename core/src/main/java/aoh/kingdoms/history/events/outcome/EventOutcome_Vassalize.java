// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.LanguageManager;
import aoc.kingdoms.lukasz.jakowski.Game_Ages;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventOutcome_Vassalize extends EventOutcome
{
    public String lordCivTag;
    public String vassalCivTag;
    
    public EventOutcome_Vassalize(final String lordCivTag, final String vassalCivTag) {
        this.lordCivTag = lordCivTag;
        this.vassalCivTag = vassalCivTag;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            final int lordCivID = Game.getCivID(this.lordCivTag);
            final int vassalCivID = Game.getCivID(this.vassalCivTag);
            if (lordCivID > 0 && vassalCivID > 0) {
                Game.getCiv(vassalCivID).setPuppetOfCivID(lordCivID);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        final int lordCivID = Game.getCivID(this.lordCivTag);
        if (lordCivID > 0) {
            return Game.getCiv(lordCivID).getCivName() + ": ";
        }
        return "";
    }
    
    @Override
    public String getStringRight() {
        final int vassalCivID = Game.getCivID(this.vassalCivTag);
        final StringBuilder sb = new StringBuilder();
        final LanguageManager lang = Game.lang;
        final Game_Ages gameAges = Game.gameAges;
        return sb.append(lang.get(Game_Ages.getVassal())).append((vassalCivID > 0) ? (": " + Game.getCiv(vassalCivID).getCivName()) : "?").toString();
    }
    
    @Override
    public int getImage() {
        return Images.vassal;
    }
}
