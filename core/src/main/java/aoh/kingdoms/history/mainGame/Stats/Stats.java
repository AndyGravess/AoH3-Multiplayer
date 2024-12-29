// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.Stats;

public class Stats
{
    public String tg;
    public int ga;
    public int tr;
    public int lp;
    public int li;
    public int nw;
    public int cp;
    public int rr;
    public int bc;
    public int rg;
    public int ra;
    public int rf;
    
    public Stats() {
        this.ga = 0;
        this.tr = 0;
        this.lp = 0;
        this.li = 0;
        this.nw = 0;
        this.cp = 0;
        this.rr = 0;
        this.bc = 0;
        this.rg = 0;
        this.ra = 0;
        this.rf = 0;
    }
    
    public Stats(final String tag) {
        this.ga = 0;
        this.tr = 0;
        this.lp = 0;
        this.li = 0;
        this.nw = 0;
        this.cp = 0;
        this.rr = 0;
        this.bc = 0;
        this.rg = 0;
        this.ra = 0;
        this.rf = 0;
        this.tg = tag;
        this.ga = 1;
    }
}
