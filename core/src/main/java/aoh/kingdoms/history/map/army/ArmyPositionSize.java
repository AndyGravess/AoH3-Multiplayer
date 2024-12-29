// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.army;

public class ArmyPositionSize
{
    public String key;
    public int provinceID;
    public int armySize;
    
    public ArmyPositionSize(final int provinceID, final String key, final int armySize) {
        this.key = key;
        this.provinceID = provinceID;
        this.armySize = armySize;
    }
}
