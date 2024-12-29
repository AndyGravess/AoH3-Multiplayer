// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.map.rebels;

public class OccupiedProvince
{
    public int p;
    public int sinceTurnID;
    
    public OccupiedProvince() {
    }
    
    public OccupiedProvince(final int provinceID, final int sinceTurnID) {
        this.p = provinceID;
        this.sinceTurnID = sinceTurnID;
    }
}
