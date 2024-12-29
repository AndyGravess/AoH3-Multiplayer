// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.FlagsEditor;

import com.badlogic.gdx.graphics.Color;

public class Flag_Overlay_GameData
{
    public int iOverlayID;
    public Color oColor;
    public int iPosX;
    public int iPosY;
    public int iWidth;
    public int iHeight;
    
    protected Flag_Overlay_GameData(final int iOverlayID) {
        this.iOverlayID = 0;
        this.oColor = Color.WHITE;
        this.iPosX = 0;
        this.iPosY = 0;
        this.iWidth = 0;
        this.iHeight = 0;
        this.iOverlayID = iOverlayID;
    }
}
