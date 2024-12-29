// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_HasVariable extends EventTrigger_Value
{
    public String value;
    
    public EventTrigger_HasVariable(final String value) {
        this.value = value;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        return Game.getCiv(iCivID).eventsDataVariables.hasVariable(this.value);
    }
    
    @Override
    public String getText() {
        return Game.lang.get("HasVariable") + ": ";
    }
    
    @Override
    public String getText2() {
        return "" + Game.lang.get(this.value).replace("_", " ");
    }
    
    @Override
    public String getText3() {
        return "";
    }
}
