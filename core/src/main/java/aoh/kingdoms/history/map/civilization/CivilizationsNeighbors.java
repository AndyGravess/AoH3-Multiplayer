// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.civilization;

import aoc.kingdoms.lukasz.map.province.Province;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.ArrayList;
import java.util.List;

public class CivilizationsNeighbors
{
    public List<CivNeighbor> civs;
    public int civsSize;
    
    public CivilizationsNeighbors() {
        this.civs = new ArrayList<CivNeighbor>();
        this.civsSize = 0;
    }
    
    public final void buildNeighbors(final int civID) {
        this.civs.clear();
        this.civsSize = 0;
        Game.getCiv(civID).haveAccessSea = false;
        try {
            for (int i = 0; i < Game.getCiv(civID).getNumOfProvinces(); ++i) {
                final int provinceID = Game.getCiv(civID).getProvinceID(i);
                if (provinceID >= 0) {
                    this.addLandNeighbors(Game.getProvince(provinceID), civID);
                    this.addSeaNeighbors(Game.getProvince(provinceID), civID);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        this.civsSize = this.civs.size();
    }
    
    private void addLandNeighbors(final Province province, final int civID) {
        for (int numNeighbors = province.getNeighboringProvincesSize(), j = 0; j < numNeighbors; ++j) {
            final int neighborCivID = Game.getProvince(province.getNeighboringProvinces(j)).getCivID();
            if (neighborCivID > 0 && neighborCivID != civID) {
                this.addCiv(neighborCivID, true);
            }
        }
    }
    
    private void addSeaNeighbors(final Province province, final int civID) {
        for (int numSeaNeighbors = province.getNeighboringSeaProvincesSize(), j = 0; j < numSeaNeighbors; ++j) {
            final int seaNeighborID = province.getNeighboringSeaProvinces(j);
            final Province seaProvince = Game.getProvince(seaNeighborID);
            if (seaProvince.getLevelOfPort() == -2) {
                Game.getCiv(civID).haveAccessSea = true;
            }
            for (int numLandNeighbors = seaProvince.getNeighboringProvincesSize(), k = 0; k < numLandNeighbors; ++k) {
                final int landNeighborID = seaProvince.getNeighboringProvinces(k);
                final int landNeighborCivID = Game.getProvince(landNeighborID).getCivID();
                if (landNeighborCivID > 0 && landNeighborCivID != civID && !Game.getProvince(landNeighborID).getSeaProvince()) {
                    this.addCiv(landNeighborCivID, false);
                }
            }
        }
    }
    
    public boolean isNeighbor(final int civID) {
        return this.civs.contains(civID);
    }
    
    public void addCiv(final int civID, final boolean byLand) {
        for (int i = this.civs.size() - 1; i >= 0; --i) {
            if (this.civs.get(i).civID == civID) {
                this.civs.get(i).byLand = (this.civs.get(i).byLand || byLand);
                return;
            }
        }
        this.civs.add(new CivNeighbor(civID, byLand));
    }
    
    public void logCivNeighbors(final int civID) {
        CFG.LOG("CivNeighbors: " + Game.getCiv(civID).getCivName() + " -> " + this.civsSize);
        for (int i = 0; i < this.civsSize; ++i) {
            CFG.LOG("Neighbor: " + Game.getCiv(this.civs.get(i).civID).getCivName());
        }
    }
    
    public class CivNeighbor
    {
        public int civID;
        public boolean byLand;
        
        public CivNeighbor() {
        }
        
        public CivNeighbor(final int civID, final boolean byLand) {
            this.civID = civID;
            this.byLand = byLand;
        }
    }
}
