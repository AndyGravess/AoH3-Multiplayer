// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events.triggers;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.map.BuildingsManager;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;

public class EventTrigger_CivCapital_HasBuilding extends EventTrigger_Value
{
    public int building;
    public int buildingID;
    
    public EventTrigger_CivCapital_HasBuilding(final int building, final int buildingID) {
        this.building = building;
        this.buildingID = buildingID;
    }
    
    @Override
    public boolean outCondition(final int iCivID, final int iProvinceID) {
        try {
            if (Game.getCiv(iCivID).getCapitalProvinceID() >= 0 && Game.getProvince(Game.getCiv(iCivID).getCapitalProvinceID()).getCivID() == iCivID) {
                return Game.getProvince(Game.getCiv(iCivID).getCapitalProvinceID()).buildingBuilt(this.building, this.buildingID);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return false;
    }
    
    @Override
    public String getText() {
        return Game.lang.get("Capital") + ", " + Game.lang.get("BuildingConstructed") + ": ";
    }
    
    @Override
    public String getText2() {
        return "" + BuildingsManager.buildings.get(this.building).Name[this.buildingID];
    }
    
    @Override
    public String getText3() {
        return " [" + Game.lang.get("Currently") + ": " + Game.getProvince(Game.getCiv(Game.player.iCivID).getCapitalProvinceID()).buildingBuilt(this.building, this.buildingID) + "]";
    }
    
    @Override
    public int getImage() {
        return Images.buildings;
    }
}
