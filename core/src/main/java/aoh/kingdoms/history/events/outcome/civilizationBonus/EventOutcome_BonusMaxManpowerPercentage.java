// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome.civilizationBonus;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.map.civilization.CivilizationBonuses;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome;

public class EventOutcome_BonusMaxManpowerPercentage extends EventOutcome
{
    public float value;
    
    public EventOutcome_BonusMaxManpowerPercentage(final float value) {
        this.value = value;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            final CivilizationBonuses nCivBonus = new CivilizationBonuses();
            nCivBonus.MaxManpower_Percentage = this.value / 100.0f;
            nCivBonus.TempTurnID = Game_Calendar.TURN_ID + bonus_duration * 365;
            Game.getCiv(iCivID).addCivilizationBonus_Temporary(nCivBonus);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.get("MaximumManpower") + ": ";
    }
    
    @Override
    public String getStringRight() {
        return ((this.value > 0.0f) ? "+" : "") + CFG.getPrecision2(this.value, 100) + "%";
    }
    
    @Override
    public String getStringRight2(final int bonus_duration) {
        return Game.lang.get("YearsX", bonus_duration);
    }
    
    @Override
    public int getImage() {
        return Game_Calendar.IMG_MANPOWER;
    }
}
