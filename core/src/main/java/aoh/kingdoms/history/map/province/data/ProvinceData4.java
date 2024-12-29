// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.map.province.data;

import aoh.kingdoms.history.mainGame.Game;

public class ProvinceData4
{
    public boolean u;
    public float s;
    
    public ProvinceData4() {
        this.u = false;
        this.s = 0.0f;
    }
    
    public boolean isUnderSiege() {
        return this.u;
    }
    
    public void setIsUnderSiege(final int provinceID, final boolean isUnderSiege) {
        this.u = isUnderSiege;
        if (isUnderSiege) {
            Game.getCiv(Game.getProvince(provinceID).getCivID()).addUnderSiege(provinceID);
        }
        else {
            Game.getCiv(Game.getProvince(provinceID).getCivID()).removeUnderSiege(provinceID);
        }
    }
    
    public void setIsUnderSiege_Just(final boolean isUnderSiege) {
        this.u = isUnderSiege;
    }
    
    public float getSiegeProgress() {
        return this.s;
    }
    
    public void setSiegeProgress(final float siegeProgress) {
        this.s = siegeProgress;
    }
}
