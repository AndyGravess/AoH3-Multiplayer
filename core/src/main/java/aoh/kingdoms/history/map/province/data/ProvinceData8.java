// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.map.province.data;

public class ProvinceData8
{
    public boolean w;
    public float r;
    
    public ProvinceData8() {
        this.w = false;
        this.r = 0.0f;
    }
    
    public boolean isWonderBuilt() {
        return this.w;
    }
    
    public void setWonderBuilt(final boolean wo) {
        this.w = wo;
    }
    
    public float getRevolutionaryRisk() {
        return this.r;
    }
    
    public void setRevolutionaryRisk(final float rr) {
        this.r = rr;
    }
}
