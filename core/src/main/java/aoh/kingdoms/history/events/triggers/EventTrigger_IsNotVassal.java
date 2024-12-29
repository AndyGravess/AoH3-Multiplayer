// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_IsNotVassal extends EventTrigger_Value
{
    public String value;
    
    public EventTrigger_IsNotVassal(final String value) {
        this.value = value;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        try {
            for (int i = 1; i < Game.getCivsSize(); ++i) {
                if (Game.getCiv(i).getNumOfProvinces() > 0 && Game.getCiv(i).getCivTag().indexOf(this.value) >= 0 && Game.getCiv(i).getCivTag().equals(this.value)) {
                    return Game.getCiv(i).getPuppetOfCivID() != i;
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
        return "[" + Game.lang.get("NOT") + "] " + Game.lang.get("IsAVassal") + ": ";
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
