// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_CivUnlockedTechnologiesOver extends EventTrigger_Value
{
    public int value;
    
    public EventTrigger_CivUnlockedTechnologiesOver(final int value) {
        this.value = value;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        return Game.getCiv(iCivID).getResearchedTechnologies() > this.value;
    }
    
    @Override
    public String getText() {
        return Game.lang.get("UnlockedTechnologies") + " > ";
    }
    
    @Override
    public String getText2() {
        return "" + CFG.getPrecision2((float)this.value, 1);
    }
    
    @Override
    public String getText3() {
        return " [" + Game.lang.get("Currently") + ": " + CFG.getPrecision2((float)Game.getCiv(Game.player.iCivID).getResearchedTechnologies(), 1) + "]";
    }
    
    @Override
    public int getImage() {
        return Game_Calendar.IMG_TECHNOLOGY;
    }
}
