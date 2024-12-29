// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.army;

public class ArmyPosition
{
    public String key;
    public int provinceID;
    
    public ArmyPosition() {
    }
    
    public ArmyPosition(final int provinceID, final String key) {
        this.key = key;
        this.provinceID = provinceID;
    }
}
