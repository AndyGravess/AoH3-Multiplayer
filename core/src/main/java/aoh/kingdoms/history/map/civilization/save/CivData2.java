// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.civilization.save;

import aoc.kingdoms.lukasz.jakowski.Game;

public class CivData2
{
    public int g;
    public int l;
    public int m;
    public int d;
    
    public CivData2() {
        this.g = 0;
        this.l = 0;
        this.m = 0;
        this.d = 0;
    }
    
    public CivData2(final int civID) {
        this.g = 0;
        this.l = 0;
        this.m = 0;
        this.d = 0;
        this.g = (int)(Game.getCiv(civID).fGold * 100.0f);
        this.l = (int)(Game.getCiv(civID).fLegacy * 100.0f);
        this.m = (int)(Game.getCiv(civID).fManpower * 100.0);
        this.d = (int)(Game.getCiv(civID).fDiplomacy * 100.0f);
    }
    
    public void update(final int civID) {
        Game.getCiv(civID).fGold = this.g / 100.0f;
        Game.getCiv(civID).fLegacy = this.l / 100.0f;
        Game.getCiv(civID).fManpower = this.m / 100.0f;
        Game.getCiv(civID).fDiplomacy = this.d / 100.0f;
    }
}
