// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.map.province.data;

public class ProvinceData9
{
    public int s;
    public int e;
    
    public ProvinceData9() {
        this.s = -1;
        this.e = 0;
    }
    
    public int getColonizationGrowthRateExtra() {
        return this.e;
    }
    
    public void setColonizationGrowthRateExtra(final int colonizationGrowthRateExtra) {
        this.e = colonizationGrowthRateExtra;
    }
    
    public void setColonizationStartedTurnID(final int colonizationStartedTurnID) {
        this.s = colonizationStartedTurnID;
    }
    
    public int getColonizationStartedTurnID() {
        return this.s;
    }
}
