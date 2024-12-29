// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.map.province.data;

public class ProvinceData7
{
    private int n;
    private float g;
    
    public ProvinceData7() {
        this.g = 0.0f;
    }
    
    public int getReligionID() {
        return this.n;
    }
    
    public void setReligionID(final int rel) {
        this.n = rel;
    }
    
    public float getIncreasedGrowthRate() {
        return this.g;
    }
    
    public void setIncreasedGrowthRate(final float ig) {
        this.g = ig;
    }
}
