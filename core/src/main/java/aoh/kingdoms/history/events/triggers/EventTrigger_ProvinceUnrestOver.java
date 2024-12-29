// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_ProvinceUnrestOver extends EventTrigger_Value
{
    public int provID;
    public float value;
    
    public EventTrigger_ProvinceUnrestOver(final int provID, final float value) {
        this.provID = provID;
        this.value = value;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        return this.provID >= 0 && Game.getProvince(this.provID).getRevulutionaryRisk() > this.value;
    }
    
    @Override
    public String getText() {
        return Game.getProvince(this.provID).getProvinceName() + ", " + Game.lang.get("Unrest") + " > ";
    }
    
    @Override
    public String getText2() {
        return "" + CFG.getPrecision2(this.value, 100);
    }
    
    @Override
    public String getText3() {
        return " [" + Game.lang.get("Currently") + ": " + CFG.getPrecision2(Game.getProvince(this.provID).getRevulutionaryRisk(), 100) + "%]";
    }
    
    @Override
    public int getImage() {
        return Images.revolutionRisk;
    }
}
