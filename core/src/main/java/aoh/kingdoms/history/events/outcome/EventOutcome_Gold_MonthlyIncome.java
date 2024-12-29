// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventOutcome_Gold_MonthlyIncome extends EventOutcome
{
    public float value;
    
    public EventOutcome_Gold_MonthlyIncome(final float value) {
        this.value = value;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            final Civilization civ = Game.getCiv(iCivID);
            civ.fGold += Game.getCiv(iCivID).fTotalIncomePerMonth * this.value;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.get("Gold") + ": ";
    }
    
    @Override
    public String getStringRight() {
        return ((this.value > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fTotalIncomePerMonth * this.value, 100);
    }
    
    @Override
    public int getImage() {
        return Images.gold;
    }
}
