// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.map.province.data;

import aoh.kingdoms.history.mainGame.Game;

public class ProvinceData
{
    public int c;
    public int o;
    public int w;
    
    public ProvinceData() {
        this.c = 0;
        this.o = 0;
        this.w = -1;
    }
    
    public int getCivID() {
        return this.c;
    }
    
    public void setCivID(final int iCivID) {
        this.c = iCivID;
    }
    
    public int getOccupiedByCivID() {
        return this.o;
    }
    
    public void setOccupiedByCivID(final int provinceID, final int iOccupiedByCivID) {
        this.o = iOccupiedByCivID;
        if (iOccupiedByCivID != 0) {
            Game.getCiv(Game.getProvince(provinceID).getCivID()).addOccupiedProvince(provinceID);
        }
        else {
            Game.getCiv(Game.getProvince(provinceID).getCivID()).removeOccupiedProvince(provinceID);
        }
    }
    
    public int getWastelandLevel() {
        return this.w;
    }
    
    public void setWastelandLevel(final int wa) {
        this.w = wa;
    }
}
