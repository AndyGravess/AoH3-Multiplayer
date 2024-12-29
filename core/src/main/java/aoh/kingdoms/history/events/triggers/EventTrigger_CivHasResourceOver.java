// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.map.ResourcesManager;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_CivHasResourceOver extends EventTrigger_Value
{
    public int value;
    public float value_over;
    
    public EventTrigger_CivHasResourceOver(final int value, final float value_over) {
        this.value = value;
        this.value_over = value_over;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        try {
            return Game.getCiv(iCivID).getGoodsProduced(this.value) > this.value_over * 100.0f;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            return false;
        }
    }
    
    @Override
    public String getText() {
        return Game.lang.get("HasTheResource") + ": ";
    }
    
    @Override
    public String getText2() {
        if (this.value >= 0 && this.value < ResourcesManager.iResourcesSize) {
            return ResourcesManager.getResourceName(this.value) + ", " + Game.lang.get("Production") + " > " + CFG.getPrecision2(this.value_over, 100);
        }
        return Game.lang.get("None");
    }
    
    @Override
    public String getText3() {
        float tCurrent = 0.0f;
        for (int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
            if (Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).getResourceID() == this.value) {
                tCurrent += ResourcesManager.getProducedGoods(Game.getCiv(Game.player.iCivID).getProvinceID(i));
            }
        }
        return " [" + Game.lang.get("Currently") + ": " + CFG.getPrecision2(tCurrent, 100) + "]";
    }
    
    @Override
    public int getImage() {
        return Images.goods;
    }
}
