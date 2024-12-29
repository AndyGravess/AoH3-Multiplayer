// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_IsPlayer extends EventTrigger_Value
{
    public String value;
    
    public EventTrigger_IsPlayer(final String value) {
        this.value = value;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        try {
            return Game.player.iCivID == Game.getCivID(this.value);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            return false;
        }
    }
    
    @Override
    public String getText() {
        return Game.lang.get("Player") + " -> ";
    }
    
    @Override
    public String getText2() {
        return "" + Game.lang.getCiv(this.value);
    }
    
    @Override
    public String getText3() {
        return "";
    }
}
