// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.civilization.stats;

public class CivilizationEventsData3
{
    public int a;
    public int p;
    public int w;
    
    public CivilizationEventsData3() {
        this.a = 0;
        this.p = 0;
        this.w = 0;
    }
    
    public int getRecruitedAdvisors() {
        return this.a;
    }
    
    public void addRecruitedAdvisors(final int recruitedAdvisors) {
        this.a += recruitedAdvisors;
    }
    
    public int getConqueredProvinces() {
        return this.p;
    }
    
    public void addConqueredProvinces(final int conquered_provinces) {
        this.p += conquered_provinces;
    }
    
    public int getNumOfWars() {
        return this.w;
    }
    
    public void addNumOfWars(final int wars) {
        this.w += wars;
    }
}
