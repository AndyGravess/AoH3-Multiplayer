// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.province;

public class ProvincePopulation
{
    private int c;
    private int n;
    
    public ProvincePopulation() {
    }
    
    public ProvincePopulation(final int iCivID, final int iPopulation) {
        this.c = iCivID;
        this.n = iPopulation;
    }
    
    public final int getCivID() {
        return this.c;
    }
    
    public final int getPopulation() {
        return this.n;
    }
    
    public final void setPopulation(final int iPopulation) {
        this.n = iPopulation;
    }
}
