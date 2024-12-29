// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski;

import java.util.ArrayList;
import java.util.List;

public class Region
{
    private List<Integer> lProvinces;
    private int iProvincesSize;
    private int iMinX;
    private int iMaxX;
    private int iMinY;
    private int iMaxY;
    private boolean belowZero;
    
    public Region() {
        this.iProvincesSize = 0;
        this.belowZero = false;
        this.lProvinces = new ArrayList<Integer>();
    }
    
    public final void addProvince(final int nProvinceID) {
        this.lProvinces.add(nProvinceID);
    }
    
    public final void removeProvince(final int i) {
        this.lProvinces.remove(i);
        this.iProvincesSize = this.lProvinces.size();
    }
    
    public final void buildRegionBounds() {
        if (this.lProvinces.size() > 0) {
            this.iMinX = Game.getProvince(this.lProvinces.get(0)).getMinX();
            this.iMaxX = Game.getProvince(this.lProvinces.get(0)).getMaxX();
            this.iMinY = Game.getProvince(this.lProvinces.get(0)).getMinY();
            this.iMaxY = Game.getProvince(this.lProvinces.get(0)).getMaxY();
            this.iProvincesSize = this.lProvinces.size();
            for (int i = 1; i < this.iProvincesSize; ++i) {
                if (Game.getProvince(this.lProvinces.get(i)).getMinX() < this.iMinX) {
                    this.iMinX = Game.getProvince(this.lProvinces.get(i)).getMinX();
                }
                if (Game.getProvince(this.lProvinces.get(i)).getMaxX() > this.iMaxX) {
                    this.iMaxX = Game.getProvince(this.lProvinces.get(i)).getMaxX();
                }
                if (Game.getProvince(this.lProvinces.get(i)).getMinY() < this.iMinY) {
                    this.iMinY = Game.getProvince(this.lProvinces.get(i)).getMinY();
                }
                if (Game.getProvince(this.lProvinces.get(i)).getMaxY() > this.iMaxY) {
                    this.iMaxY = Game.getProvince(this.lProvinces.get(i)).getMaxY();
                }
            }
            this.belowZero = (this.iMinX < 0);
        }
    }
    
    public final int getProvince(final int i) {
        return this.lProvinces.get(i);
    }
    
    public final int getProvincesSize() {
        return this.iProvincesSize;
    }
    
    public final int getProvincesSize2() {
        return this.lProvinces.size();
    }
    
    public final int getMinX() {
        return this.iMinX;
    }
    
    public final int getMaxX() {
        return this.iMaxX;
    }
    
    public final int getMinY() {
        return this.iMinY;
    }
    
    public final int getMaxY() {
        return this.iMaxY;
    }
    
    public final boolean getBelowZero() {
        return this.belowZero;
    }
}
