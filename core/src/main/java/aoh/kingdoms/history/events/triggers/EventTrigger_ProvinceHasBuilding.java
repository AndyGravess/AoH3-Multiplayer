// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.map.BuildingsManager;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_ProvinceHasBuilding extends EventTrigger_Value
{
    public int provID;
    public int building;
    public int buildingID;
    
    public EventTrigger_ProvinceHasBuilding(final int provID, final int building, final int buildingID) {
        this.provID = provID;
        this.building = building;
        this.buildingID = buildingID;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        return this.provID >= 0 && Game.getProvince(this.provID).buildingBuilt(this.building, this.buildingID);
    }
    
    @Override
    public String getText() {
        return Game.getProvince(this.provID).getProvinceName() + ", " + Game.lang.get("BuildingConstructed") + ": ";
    }
    
    @Override
    public String getText2() {
        return "" + BuildingsManager.buildings.get(this.building).Name[this.buildingID];
    }
    
    @Override
    public String getText3() {
        return " [" + Game.lang.get("Currently") + ": " + Game.getProvince(this.provID).buildingBuilt(this.building, this.buildingID) + "]";
    }
    
    @Override
    public int getImage() {
        return Images.buildings;
    }
}
