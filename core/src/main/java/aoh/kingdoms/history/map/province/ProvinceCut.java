// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.province;

import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.List;

public class ProvinceCut
{
    private List<Short> lPointsX;
    private List<Short> lPointsY;
    public int iMinX;
    public int iMaxX;
    public int iMinY;
    public int iMaxY;
    private int iPointsSize;
    
    public ProvinceCut(final List<Short> nPointsX, final List<Short> nPointsY) {
        this.iMinX = 0;
        this.iMaxX = 0;
        this.iMinY = 0;
        this.iMaxY = 0;
        this.lPointsX = nPointsX;
        this.lPointsY = nPointsY;
        this.iPointsSize = this.lPointsX.size();
        if (this.iPointsSize > 0) {
            this.iMinX = this.lPointsX.get(0);
            this.iMaxX = this.lPointsX.get(0);
            this.iMinY = this.lPointsY.get(0);
            this.iMaxY = this.lPointsY.get(0);
            for (int i = 1; i < this.iPointsSize; ++i) {
                if (this.lPointsX.get(i) > this.iMaxX) {
                    this.iMaxX = this.lPointsX.get(i);
                }
                if (this.lPointsX.get(i) < this.iMinX) {
                    this.iMinX = this.lPointsX.get(i);
                }
                if (this.lPointsY.get(i) > this.iMaxY) {
                    this.iMaxY = this.lPointsY.get(i);
                }
                if (this.lPointsY.get(i) < this.iMinY) {
                    this.iMinY = this.lPointsY.get(i);
                }
            }
        }
    }
    
    public final int getPointsX(final int i) {
        return this.lPointsX.get(i) * Game.mapBG.iMapScale;
    }
    
    public final int getPointsY(final int i) {
        return this.lPointsY.get(i) * Game.mapBG.iMapScale;
    }
    
    public final int getPointsSize() {
        return this.iPointsSize;
    }
}
