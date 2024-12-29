// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.map.province.data;

public class ProvinceData3
{
    private float t;
    private float m;
    
    public ProvinceData3() {
        this.t = 1.0f;
        this.m = 1.0f;
    }
    
    public float getTaxEfficiency() {
        return this.t;
    }
    
    public void setTaxEfficiency(final float tax) {
        this.t = tax;
    }
    
    public float getManpower() {
        return this.m;
    }
    
    public void setManpower(final float ma) {
        this.m = ma;
    }
}
