// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;

public class EventTrigger_PlayingTimeOver extends EventTrigger_Value
{
    public int value;
    
    public EventTrigger_PlayingTimeOver(final int value) {
        this.value = value;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        return Game_Calendar.TURN_ID > this.value;
    }
    
    @Override
    public String getText() {
        return Game.lang.get("PlayingTime") + " > ";
    }
    
    @Override
    public String getText2() {
        return "" + Game.lang.get("DaysX", this.value);
    }
    
    @Override
    public String getText3() {
        return " [" + Game.lang.get("Currently") + ": " + CFG.getPrecision2((float)Game_Calendar.TURN_ID, 1) + "]";
    }
    
    @Override
    public int getImage() {
        return Images.time;
    }
}
