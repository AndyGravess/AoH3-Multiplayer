// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_CivIncomeEconomyBelow extends EventTrigger_Value
{
    public float value;
    
    public EventTrigger_CivIncomeEconomyBelow(final float value) {
        this.value = value;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        return Game.getCiv(iCivID).getIncomeEconomy() < this.value;
    }
    
    @Override
    public String getText() {
        return Game.lang.get("MonthlyIncomeEconomy") + " < ";
    }
    
    @Override
    public String getText2() {
        return "" + CFG.getPrecision2(this.value, 100);
    }
    
    @Override
    public String getText3() {
        return " [" + Game.lang.get("Currently") + ": " + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).getIncomeEconomy(), 100) + "]";
    }
    
    @Override
    public int getImage() {
        return Images.gold;
    }
}
