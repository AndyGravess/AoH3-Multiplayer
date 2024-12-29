// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome.province;

import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome;

public class EventOutcome_Province_Economy_Capital extends EventOutcome
{
    public float value;
    
    public EventOutcome_Province_Economy_Capital(final float value) {
        this.value = value;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            Game.getProvince(Game.getCiv(iCivID).getCapitalProvinceID()).setEconomy(Game.getProvince(Game.getCiv(iCivID).getCapitalProvinceID()).getEconomy() + this.value);
            Game.getProvince(Game.getCiv(iCivID).getCapitalProvinceID()).updateAfterEconomyChange();
            Game.getCiv(iCivID).updateTotalIncomePerMonth();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.get("Economy") + ": ";
    }
    
    @Override
    public String getStringRight() {
        return ((this.value > 0.0f) ? "+" : "") + CFG.getPrecision2(this.value, 100);
    }
    
    @Override
    public String getStringRight2(final int bonus_duration) {
        try {
            return Game.getProvince(Game.getCiv(Game.player.iCivID).getCapitalProvinceID()).getProvinceName();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            return null;
        }
    }
    
    @Override
    public int getImage() {
        return Game_Calendar.IMG_ECONOMY;
    }
}
