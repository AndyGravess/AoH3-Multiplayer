// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.outcome.province;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome;

public class EventOutcome_Province_TaxEfficiency_All extends EventOutcome
{
    public float value;
    
    public EventOutcome_Province_TaxEfficiency_All(final float value) {
        this.value = value;
    }
    
    @Override
    public void updateCiv(final int iCivID, final int bonus_duration) {
        try {
            for (int i = 0; i < Game.getCiv(iCivID).getNumOfProvinces(); ++i) {
                Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).setTaxEfficiency(Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).getTaxEfficiency() + this.value);
                Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).updateProvinceIncome();
            }
            Game.getCiv(iCivID).updateTotalIncomePerMonth();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public String getStringLeft() {
        return Game.lang.get("TaxEfficiency") + ": ";
    }
    
    @Override
    public String getStringRight() {
        return ((this.value > 0.0f) ? "+" : "") + CFG.getPrecision2(this.value, 100);
    }
    
    @Override
    public String getStringRight2(final int bonus_duration) {
        return Game.lang.get("AllProvinces");
    }
    
    @Override
    public int getImage() {
        return Images.tax;
    }
}
