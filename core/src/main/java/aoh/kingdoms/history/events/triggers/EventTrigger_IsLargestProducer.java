// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.map.ResourcesManager;

public class EventTrigger_IsLargestProducer extends EventTrigger_Value
{
    public int resourceID;
    
    public EventTrigger_IsLargestProducer(final int resourceID) {
        this.resourceID = resourceID;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        try {
            return ResourcesManager.worldResources_LargestProducer.get(this.resourceID) == iCivID;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            return false;
        }
    }
    
    @Override
    public String getText() {
        return ResourcesManager.getResourceName(this.resourceID) + ": " + Game.lang.get("LargestProducer");
    }
    
    @Override
    public String getText2() {
        return "";
    }
    
    @Override
    public String getText3() {
        return " [" + Game.lang.get("Currently") + ": " + Game.getCiv(ResourcesManager.worldResources_LargestProducer.get(this.resourceID)).getCivName() + "]";
    }
    
    @Override
    public int getImage() {
        return Images.goods;
    }
}
