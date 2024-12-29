// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.map.civilization.CivilizationGeneralsPool;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventOutcome_AddGeneral extends EventOutcome
{
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            Game.getCiv(iCivID).addGeneral(CivilizationGeneralsPool.getGeneral_Random(iCivID));
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.get("RecruitGeneral");
    }
    
    @Override
    public String getStringRight() {
        return "";
    }
    
    @Override
    public int getImage() {
        return Images.general;
    }
}
