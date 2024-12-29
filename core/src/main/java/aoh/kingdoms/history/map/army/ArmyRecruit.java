// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.army;

public class ArmyRecruit
{
    public int provinceID;
    public int unitID;
    public int armyID;
    public int timeLeft;
    public int cost;
    public String toArmyKey;
    
    public ArmyRecruit() {
        this.toArmyKey = null;
    }
    
    public ArmyRecruit(final int iProvinceID, final int iUnitID, final int iArmyID, final String assignToArmyKey) {
        this.toArmyKey = null;
        this.provinceID = iProvinceID;
        this.unitID = iUnitID;
        this.armyID = iArmyID;
        this.toArmyKey = assignToArmyKey;
    }
}
