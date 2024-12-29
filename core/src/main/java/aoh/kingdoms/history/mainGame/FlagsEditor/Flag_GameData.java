// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.FlagsEditor;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Color;
import java.util.List;

public class Flag_GameData
{
    public int iDivisionID;
    public List<Color> lDivisionColors;
    public List<Flag_Overlay_GameData> lOverlays;
    
    public Flag_GameData() {
        this.iDivisionID = 0;
        this.lDivisionColors = new ArrayList<Color>();
        this.lOverlays = new ArrayList<Flag_Overlay_GameData>();
    }
}
