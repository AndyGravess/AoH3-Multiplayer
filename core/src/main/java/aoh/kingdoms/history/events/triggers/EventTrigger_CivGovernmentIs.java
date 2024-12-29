// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_CivGovernmentIs extends EventTrigger_Value
{
    public int value;
    
    public EventTrigger_CivGovernmentIs(final int value) {
        this.value = value;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        return Game.getCiv(iCivID).getReligionID() == this.value;
    }
    
    @Override
    public String getText() {
        return Game.lang.get("Government") + " == ";
    }
    
    @Override
    public String getText2() {
        return "" + Game.ideologiesManager.getIdeology(this.value).Name;
    }
    
    @Override
    public String getText3() {
        return " [" + Game.lang.get("Currently") + ": " + Game.ideologiesManager.getIdeology(Game.getCiv(Game.player.iCivID).getIdeologyID()).Name + "]";
    }
    
    @Override
    public int getImage() {
        return Images.government;
    }
}
