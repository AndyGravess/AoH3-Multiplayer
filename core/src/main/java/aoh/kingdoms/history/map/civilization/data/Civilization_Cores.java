// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.civilization.data;

import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.ArrayList;
import java.util.List;

public class Civilization_Cores
{
    public List<Integer> provinces;
    public int provincesSize;
    
    public Civilization_Cores() {
        this.provinces = new ArrayList<Integer>();
        this.provincesSize = 0;
    }
    
    public final void buildProvincesNonCore(final int civID) {
        this.provinces.clear();
        this.provincesSize = 0;
        for (int i = 0; i < Game.getCiv(civID).getNumOfProvinces(); ++i) {
            if (!Game.getProvince(Game.getCiv(civID).getProvinceID(i)).haveACore && Game.getProvince(Game.getCiv(civID).getProvinceID(i)).coreCreation == null) {
                this.provinces.add(Game.getCiv(civID).getProvinceID(i));
            }
        }
        this.provincesSize = this.provinces.size();
    }
    
    public final void addProvince(final int provinceID) {
        if (this.provinces.contains(provinceID)) {
            return;
        }
        this.provinces.add(provinceID);
        this.provincesSize = this.provinces.size();
    }
    
    public final void removeProvince(final int provinceID) {
        for (int i = 0; i < this.provincesSize; ++i) {
            if (this.provinces.get(i) == provinceID) {
                this.provinces.remove(i);
                this.provincesSize = this.provinces.size();
                return;
            }
        }
    }
    
    public final void checkProvince(final int provinceID, final int civID) {
        if (Game.getProvince(provinceID).getCivID() != civID) {
            this.removeProvince(provinceID);
            return;
        }
        if (!Game.getProvince(provinceID).haveACore) {
            if (this.provinces.contains(provinceID)) {
                return;
            }
            this.provinces.add(provinceID);
            this.provincesSize = this.provinces.size();
        }
        else {
            this.removeProvince(provinceID);
        }
    }
    
    public final void checkProvince_Full(final int provinceID, final int civID) {
        if (Game.getProvince(provinceID).getCivID() != civID) {
            this.removeProvince(provinceID);
            return;
        }
        if (!Game.getProvince(provinceID).haveACore(civID)) {
            if (this.provinces.contains(provinceID)) {
                return;
            }
            this.provinces.add(provinceID);
            this.provincesSize = this.provinces.size();
        }
        else {
            this.removeProvince(provinceID);
        }
    }
}
