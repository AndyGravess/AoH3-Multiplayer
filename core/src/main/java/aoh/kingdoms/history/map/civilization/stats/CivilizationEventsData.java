// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.civilization.stats;

public class CivilizationEventsData
{
    public int e;
    public int g;
    public int t;
    public int m;
    public int d;
    
    public CivilizationEventsData() {
        this.e = 0;
        this.g = 0;
        this.t = 0;
        this.m = 0;
        this.d = 0;
    }
    
    public int getInvestedInEconomy() {
        return this.e;
    }
    
    public void setInvestedInEconomy(final int invested_in_economy) {
        this.e = invested_in_economy;
    }
    
    public void addInvestedInEconomy(final int invested_in_economy) {
        this.e += invested_in_economy;
    }
    
    public int getIncreasedGrowthRate() {
        return this.g;
    }
    
    public void setIncreasedGrowthRate(final int increased_growth_rate) {
        this.g = increased_growth_rate;
    }
    
    public void addIncreasedGrowthRate(final int increased_growth_rate) {
        this.g += increased_growth_rate;
    }
    
    public int getIncreasedTaxEfficiency() {
        return this.t;
    }
    
    public void setIncreasedTaxEfficiency(final int increased_tax_efficiency) {
        this.t = increased_tax_efficiency;
    }
    
    public void addIncreasedTaxEfficiency(final int increased_tax_efficiency) {
        this.t += increased_tax_efficiency;
    }
    
    public int getDevelopedInfrastructure() {
        return this.d;
    }
    
    public void setDevelopedInfrastructure(final int developed_infrastructure) {
        this.d = developed_infrastructure;
    }
    
    public void addDevelopedInfrastructure(final int developed_infrastructure) {
        this.d += developed_infrastructure;
    }
    
    public int getIncreasedManpower() {
        return this.m;
    }
    
    public void setIncreasedManpower(final int increased_manpower) {
        this.m = increased_manpower;
    }
    
    public void addIncreasedManpower(final int increased_manpower) {
        this.m += increased_manpower;
    }
}
