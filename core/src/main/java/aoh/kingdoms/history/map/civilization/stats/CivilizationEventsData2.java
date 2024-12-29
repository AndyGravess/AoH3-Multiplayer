// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.civilization.stats;

public class CivilizationEventsData2
{
    public int b;
    public int a;
    public int e;
    public int m;
    public int c;
    
    public CivilizationEventsData2() {
        this.b = 0;
        this.a = 0;
        this.e = 0;
        this.m = 0;
        this.c = 0;
    }
    
    public int getBuildingsConstructed() {
        return this.b;
    }
    
    public void addBuildingsConstructed(final int buildings_constructed) {
        this.b += buildings_constructed;
    }
    
    public int getAdministrativeBuildingsConstructed() {
        return this.a;
    }
    
    public void addAdministrativeBuildingsConstructed(final int administrative_buildings_constructed) {
        this.a += administrative_buildings_constructed;
    }
    
    public int getEconomyBuildingsConstructed() {
        return this.e;
    }
    
    public void addEconomyBuildingsConstructed(final int economy_buildings_constructed) {
        this.e += economy_buildings_constructed;
    }
    
    public int getMilitaryBuildingsConstructed() {
        return this.m;
    }
    
    public void addMilitaryBuildingsConstructed(final int military_buildings_constructed) {
        this.m += military_buildings_constructed;
    }
    
    public int getCapitalBuildingsConstructed() {
        return this.c;
    }
    
    public void addCapitalBuildingsConstructed(final int capital_buildings_constructed) {
        this.c += capital_buildings_constructed;
    }
}
