// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.map.province.data;

import java.util.ArrayList;
import aoh.kingdoms.history.map.province.ProvincePopulation;
import java.util.List;

public class ProvinceData_Population
{
    private List<ProvincePopulation> p;
    
    public ProvinceData_Population() {
        this.p = new ArrayList<ProvincePopulation>();
    }
    
    public List<ProvincePopulation> getPopulation() {
        return this.p;
    }
    
    public ProvincePopulation getPopulation(final int i) {
        return this.p.get(i);
    }
}
