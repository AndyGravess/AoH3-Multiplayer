// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.province;

import java.util.ArrayList;
import com.badlogic.gdx.math.Matrix4;
import aoc.kingdoms.lukasz.jakowski.zOther.Point_XY;
import java.util.List;

public class ProvinceNameData
{
    public float fX;
    public float fY;
    public float fX2;
    public float fY2;
    public float fCenterX;
    public float fCenterY;
    public List<Point_XY> drawPoints;
    public float drawAngleLow;
    public List<Matrix4> drawMatrix4;
    public float fontScale;
    
    public ProvinceNameData() {
        this.drawPoints = new ArrayList<Point_XY>();
        this.drawAngleLow = 0.0f;
        this.drawMatrix4 = new ArrayList<Matrix4>();
        this.fontScale = 1.0f;
    }
}
