// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_CivReligionIs extends EventTrigger_Value
{
    public int value;
    
    public EventTrigger_CivReligionIs(final int value) {
        this.value = value;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        return Game.getCiv(iCivID).getReligionID() == this.value;
    }
    
    @Override
    public String getText() {
        return Game.lang.get("Religion") + " == ";
    }
    
    @Override
    public String getText2() {
        return "" + Game.religionManager.getReligion(this.value).Name;
    }
    
    @Override
    public String getText3() {
        return " [" + Game.lang.get("Currently") + ": " + Game.religionManager.getReligion(Game.getCiv(Game.player.iCivID).getReligionID()).Name + "]";
    }
    
    @Override
    public int getImage() {
        return Images.religion;
    }
}
