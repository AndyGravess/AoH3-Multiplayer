// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_IncreasedTaxEfficiencyBelow extends EventTrigger_Value
{
    public int value;
    
    public EventTrigger_IncreasedTaxEfficiencyBelow(final int value) {
        this.value = value * 100;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        return Game.getCiv(iCivID).eventsData.getIncreasedTaxEfficiency() < this.value;
    }
    
    @Override
    public String getText() {
        return Game.lang.get("IncreasedTaxEfficiency") + " < ";
    }
    
    @Override
    public String getText2() {
        return "" + CFG.getPrecision2(this.value / 100.0f, 100);
    }
    
    @Override
    public String getText3() {
        return " [" + Game.lang.get("Currently") + ": " + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).eventsData.getIncreasedTaxEfficiency() / 100.0f, 100) + "]";
    }
    
    @Override
    public int getImage() {
        return Images.tax;
    }
}
