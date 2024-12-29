// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome.province;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome;

public class EventOutcome_Province_Population_Capital extends EventOutcome
{
    public int value;
    
    public EventOutcome_Province_Population_Capital(final int value) {
        this.value = value;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            Game.getProvince(Game.getCiv(iCivID).getCapitalProvinceID()).setPopulationOfCivID(iCivID, Game.getProvince(Game.getCiv(iCivID).getCapitalProvinceID()).getPopulationOfCivID(iCivID) + this.value);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.get("Population") + ": ";
    }
    
    @Override
    public String getStringRight() {
        return ((this.value > 0) ? "+" : "") + CFG.getPrecision2((float)this.value, 100);
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
        return Images.population;
    }
}
