// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.moveUnits.other;

import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.CatmullRomSpline;
import space.earlygrey.shapedrawer.JoinType;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.menu.Colors;
import java.util.ArrayList;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;
import java.util.List;

public class MoveUnits_RegroupLine
{
    private List<Integer> lRoute;
    public int iRouteSize;
    public Color ColorLine;
    public Color ColorLine2;
    private int iPrecision;
    private Vector2[] vPoints;
    public static final int PRECISION = 15;
    
    public MoveUnits_RegroupLine(final List<Integer> nProvinces) {
        this.lRoute = new ArrayList<Integer>();
        this.iRouteSize = 0;
        this.ColorLine = new Color(0.57254905f, 0.50980395f, 0.4509804f, 1.0f);
        this.ColorLine2 = new Color(0.04f, 0.04f, 0.04f, 1.0f);
        this.setPath(nProvinces);
        if (this.iRouteSize > 1) {
            this.buildMoveUnitsLine(true);
            this.ColorLine2 = CFG.getColorStep(Colors.HOVER_GOLD, Game.getCiv(Game.getProvince(this.lRoute.get(0)).getCivID()).getColor(1.0f), 50, 100, 0.4f);
        }
    }
    
    protected final void setPath(final List<Integer> lPath) {
        for (int i = 0; i < lPath.size(); ++i) {
            this.lRoute.add(lPath.get(i));
        }
        this.iRouteSize = this.lRoute.size();
    }
    
    public void draw(final SpriteBatch oSB, final float nScale) {
        try {
            if (this.iRouteSize > 0) {
                final Array<Vector2> nPath = (Array<Vector2>)new Array();
                if (Game.getProvince(this.lRoute.get(0)).getDrawProvince()) {
                    for (int j = 0; j < this.iPrecision - 2; ++j) {
                        nPath.add((Object)new Vector2((this.vPoints[j].x + Game.getProvince(this.lRoute.get(0)).getTranslateProvincePosX()) * nScale, (this.vPoints[j].y - Game.mapCoords.getPosY()) * nScale));
                    }
                }
                else {
                    for (int j = 0; j < this.iPrecision; ++j) {
                        nPath.add((Object)new Vector2((this.vPoints[j].x + Game.mapCoords.getPosX()) * nScale, (this.vPoints[j].y - Game.mapCoords.getPosY()) * nScale));
                    }
                }
                Renderer.shapeDrawer.setColor(new Color(this.ColorLine2.r, this.ColorLine2.g, this.ColorLine2.b, 0.4f));
                Renderer.shapeDrawer.path((Iterable)nPath, 3.0f, JoinType.SMOOTH, true);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void buildMoveUnitsLine(final boolean updateAnimation) {
        this.iPrecision = 15 * this.iRouteSize;
        this.vPoints = new Vector2[this.iPrecision];
        final Vector2[] dataSet = new Vector2[this.iRouteSize + 2];
        for (int i = 0; i < this.iRouteSize; ++i) {
            if (Game.getProvince(this.lRoute.get(i)).getCitiesSize() > 0) {
                dataSet[i + 1] = new Vector2((float)Game.getProvince(this.lRoute.get(i)).getCity(0).getPosX(), (float)(-Game.getProvince(this.lRoute.get(i)).getCity(0).getPosY()));
            }
            else {
                dataSet[i + 1] = new Vector2((float)Game.getProvince(this.lRoute.get(i)).iCenterShiftX, (float)(-Game.getProvince(this.lRoute.get(i)).iCenterShiftY));
            }
        }
        if (Game.getProvince(this.lRoute.get(0)).getCitiesSize() > 0) {
            dataSet[0] = new Vector2((float)(Game.getProvince(this.lRoute.get(0)).getCity(0).getPosX() - 15 + Game.oR.nextInt(31)), (float)(-(Game.getProvince(this.lRoute.get(0)).getCity(0).getPosY() - 15 + Game.oR.nextInt(31))));
        }
        else {
            dataSet[0] = new Vector2((float)(Game.getProvince(this.lRoute.get(0)).iCenterShiftX - 15 + Game.oR.nextInt(31)), (float)(-(Game.getProvince(this.lRoute.get(0)).iCenterShiftY - 15 + Game.oR.nextInt(31))));
        }
        if (Game.getProvince(this.lRoute.get(this.iRouteSize - 1)).getCitiesSize() > 0) {
            dataSet[this.iRouteSize + 1] = new Vector2((float)Game.getProvince(this.lRoute.get(this.iRouteSize - 1)).getCity(0).getPosX(), (float)(-Game.getProvince(this.lRoute.get(this.iRouteSize - 1)).getCity(0).getPosY()));
        }
        else {
            dataSet[this.iRouteSize + 1] = new Vector2((float)Game.getProvince(this.lRoute.get(this.iRouteSize - 1)).iCenterShiftX, (float)(-Game.getProvince(this.lRoute.get(this.iRouteSize - 1)).iCenterShiftY));
        }
        final CatmullRomSpline<Vector2> oCatmull = (CatmullRomSpline<Vector2>)new CatmullRomSpline((Vector[])dataSet, false);
        for (int j = 0; j < this.iPrecision; ++j) {
            oCatmull.valueAt((Vector)(this.vPoints[j] = new Vector2()), j / (this.iPrecision - 1.0f));
        }
    }
    
    public int getFromProvinceID() {
        return this.lRoute.get(0);
    }
    
    public int getToProvinceID() {
        return this.lRoute.get(1);
    }
    
    public int getToProvinceLastID() {
        return this.lRoute.get(this.iRouteSize - 1);
    }
}
