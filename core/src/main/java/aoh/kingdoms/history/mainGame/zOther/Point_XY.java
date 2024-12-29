// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.zOther;

public class Point_XY
{
    private int iPosX;
    private int iPosY;
    
    public Point_XY(final int nPosX, final int nPosY) {
        this.iPosX = nPosX;
        this.iPosY = nPosY;
    }
    
    public final int getPosX() {
        return this.iPosX;
    }
    
    public final void setPosX(final int iPosX) {
        this.iPosX = iPosX;
    }
    
    public final int getPosY() {
        return this.iPosY;
    }
    
    public final void setPosY(final int iPosY) {
        this.iPosY = iPosY;
    }
}
