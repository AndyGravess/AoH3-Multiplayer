// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_CivGoldOver_MaxAmountOfGold extends EventTrigger_Value
{
    public boolean value;
    
    public EventTrigger_CivGoldOver_MaxAmountOfGold(final boolean value) {
        this.value = value;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        if (this.value) {
            return Game.getCiv(iCivID).fGold >= Game.getMaxAmountOfGold(iCivID);
        }
        return Game.getCiv(iCivID).fGold < Game.getMaxAmountOfGold(iCivID);
    }
    
    @Override
    public String getText() {
        return Game.lang.get("Gold") + (this.value ? " >= " : " < ");
    }
    
    @Override
    public String getText2() {
        return "" + Game.lang.get("MaximumAmountOfGold");
    }
    
    @Override
    public String getText3() {
        return " [" + Game.lang.get("Currently") + ": " + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fGold, 1) + " | " + Game.getMaxAmountOfGold(Game.player.iCivID) + "]";
    }
    
    @Override
    public int getImage() {
        return Images.gold;
    }
}
