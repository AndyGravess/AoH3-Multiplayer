// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_ProvinceReligionIsNot extends EventTrigger_Value
{
    public int provID;
    public int value;
    
    public EventTrigger_ProvinceReligionIsNot(final int provID, final int value) {
        this.provID = provID;
        this.value = value;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        return this.provID >= 0 && Game.getProvince(this.provID).getReligion() != this.value;
    }
    
    @Override
    public String getText() {
        return Game.getProvince(this.provID).getProvinceName() + ", " + Game.lang.get("Religion") + ", " + Game.lang.get("NOT") + ": ";
    }
    
    @Override
    public String getText2() {
        return "" + CFG.getPrecision2((float)this.value, 100);
    }
    
    @Override
    public String getText3() {
        return " [" + Game.lang.get("Currently") + ": " + Game.religionManager.getReligion(Game.getProvince(this.provID).getReligion()).Name + "]";
    }
    
    @Override
    public int getImage() {
        return Images.religion;
    }
}
