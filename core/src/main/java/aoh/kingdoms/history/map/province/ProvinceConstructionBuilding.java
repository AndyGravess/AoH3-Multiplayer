// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.province;

public class ProvinceConstructionBuilding
{
    private int building;
    private int buildingID;
    private int iConstructionTime;
    public int iConstructionTimeLeft;
    
    public ProvinceConstructionBuilding() {
    }
    
    public ProvinceConstructionBuilding(final int building, final int buildingID, final int iConstructionTime, final int iConstructionTimeLeft) {
        this.building = building;
        this.buildingID = buildingID;
        this.iConstructionTime = iConstructionTime;
        this.iConstructionTimeLeft = iConstructionTimeLeft;
    }
    
    public int getBuilding() {
        return this.building;
    }
    
    public void setBuilding(final int building) {
        this.building = building;
    }
    
    public int getBuildingID() {
        return this.buildingID;
    }
    
    public void setBuildingID(final int buildingID) {
        this.buildingID = buildingID;
    }
    
    public int getConstructionTime() {
        return this.iConstructionTime;
    }
    
    public void setConstructionTime(final int iConstructionTime) {
        this.iConstructionTime = iConstructionTime;
    }
    
    public int getConstructionTimeLeft() {
        return this.iConstructionTimeLeft;
    }
    
    public void setConstructionTimeLeft(final int iConstructionTimeLeft) {
        this.iConstructionTimeLeft = iConstructionTimeLeft;
    }
}
