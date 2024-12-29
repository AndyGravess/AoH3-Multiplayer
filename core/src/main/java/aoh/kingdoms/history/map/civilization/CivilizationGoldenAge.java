// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.civilization;

public class CivilizationGoldenAge
{
    private int p;
    private int m;
    private int s;
    
    public CivilizationGoldenAge() {
        this.p = 0;
        this.m = 0;
        this.s = 0;
    }
    
    public int getGoldenAgeProsperity() {
        return this.p;
    }
    
    public void setGoldenAgeProsperity(final int golden_age_prosperity) {
        this.p = golden_age_prosperity;
    }
    
    public int getGoldenAgeMilitary() {
        return this.m;
    }
    
    public void setGoldenAgeMilitary(final int golden_age_military) {
        this.m = golden_age_military;
    }
    
    public int getGoldenAgeScience() {
        return this.s;
    }
    
    public void setGoldenAgeScience(final int golden_age_science) {
        this.s = golden_age_science;
    }
}
