// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.map.war.WarManager;
import aoc.kingdoms.lukasz.map.war.War;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_IsAtWar_DaysOver extends EventTrigger_Value
{
    public int value;
    
    public EventTrigger_IsAtWar_DaysOver(final int value) {
        this.value = value;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        try {
            for (int i = 0; i < Game.getCiv(iCivID).diplomacy.iWarsSize; ++i) {
                if (Game_Calendar.TURN_ID - this.value > WarManager.lWars.get(Game.getCiv(iCivID).diplomacy.lWars.get(i)).iWarTurnID) {
                    return true;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return false;
    }
    
    @Override
    public String getText() {
        return Game.lang.get("IaAtWar") + ": ";
    }
    
    @Override
    public String getText2() {
        return Game.lang.get("DaysX", this.value);
    }
    
    @Override
    public String getText3() {
        return "";
    }
    
    @Override
    public int getImage() {
        return Images.war;
    }
}
