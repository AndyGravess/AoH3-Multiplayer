// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.civilization.save;

public class CivData3
{
    public int a;
    public float w;
    public float e;
    public float t;
    public float c;
    
    public CivData3() {
        this.a = 0;
        this.w = 0.0f;
        this.e = 0.0f;
        this.t = 0.0f;
        this.c = 0.0f;
    }
    
    public float getInflation() {
        return this.t;
    }
    
    public void setInflation(final float fInflation) {
        this.t = fInflation;
    }
    
    public float getCorruption() {
        return this.c;
    }
    
    public void setCorruption(final float fCorruption) {
        this.c = fCorruption;
    }
}
