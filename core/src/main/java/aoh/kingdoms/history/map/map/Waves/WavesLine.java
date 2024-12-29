// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.map.Waves;

import space.earlygrey.shapedrawer.JoinType;
import com.badlogic.gdx.graphics.Color;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.CatmullRomSpline;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Touch;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.CFG;
import java.util.ArrayList;
import com.badlogic.gdx.math.Vector2;
import aoc.kingdoms.lukasz.jakowski.zOther.Point_XY;
import java.util.List;

public class WavesLine
{
    public List<Point_XY> points;
    public int pointsSize;
    public Vector2[] vPoints;
    public List<Integer> width;
    public final float ANIMATION_TIME = 5000.0f;
    public long time;
    public boolean direction;
    
    public WavesLine() {
        this.points = new ArrayList<Point_XY>();
        this.pointsSize = 0;
        this.width = new ArrayList<Integer>();
        this.time = 0L;
        this.direction = false;
        this.time = CFG.currentTimeMillis;
    }
    
    public void addNewPoint() {
        this.points.add(new Point_XY((int)(Game.mapCoords.getPosX() - Touch.getMousePosX() / Game.mapScale.getCurrentScale()) * -1, (int)(Game.mapCoords.getPosY() - Touch.getMousePosY() / Game.mapScale.getCurrentScale()) * -1));
        this.pointsSize = this.points.size();
        this.buildData();
    }
    
    public void removePoint() {
        if (this.pointsSize > 0) {
            this.points.remove(this.pointsSize - 1);
            this.pointsSize = this.points.size();
        }
        this.buildData();
    }
    
    public void addNewPoint_Just(final int nX, final int nY) {
        this.points.add(new Point_XY(nX, nY));
        this.pointsSize = this.points.size();
    }
    
    public final void buildData() {
        if (this.pointsSize > 2) {
            this.width.clear();
            this.vPoints = new Vector2[GameValues.ships.SHIP_LINE_PRECISION];
            final Vector2[] dataSet = new Vector2[this.pointsSize + 2];
            dataSet[0] = new Vector2((float)this.points.get(0).getPosX(), (float)this.points.get(0).getPosY());
            for (int i = 0; i < this.pointsSize; ++i) {
                dataSet[i + 1] = new Vector2((float)this.points.get(i).getPosX(), (float)this.points.get(i).getPosY());
            }
            dataSet[this.pointsSize + 1] = new Vector2((float)this.points.get(this.pointsSize - 1).getPosX(), (float)this.points.get(this.pointsSize - 1).getPosY());
            final CatmullRomSpline<Vector2> oCatmull = (CatmullRomSpline<Vector2>)new CatmullRomSpline((Vector[])dataSet, false);
            for (int j = 0; j < GameValues.ships.SHIP_LINE_PRECISION; ++j) {
                oCatmull.valueAt((Vector)(this.vPoints[j] = new Vector2()), j / (GameValues.ships.SHIP_LINE_PRECISION - 1.0f));
            }
            for (int j = 0; j < GameValues.ships.SHIP_LINE_PRECISION - 1; ++j) {
                this.width.add((int)Math.ceil(Math.sqrt((this.vPoints[j + 1].x - this.vPoints[j].x) * (this.vPoints[j + 1].x - this.vPoints[j].x) + (this.vPoints[j].y - this.vPoints[j + 1].y) * (this.vPoints[j].y - this.vPoints[j + 1].y))));
            }
            this.width.add(1);
        }
    }
    
    public void draw(final SpriteBatch oSB) {
        try {
            if (this.pointsSize > 2) {
                float perc = (CFG.currentTimeMillis - this.time) / 5000.0f;
                if (perc >= 1.0f) {
                    this.time = CFG.currentTimeMillis;
                    this.direction = !this.direction;
                    if (this.direction) {
                        perc = 1.0f;
                    }
                    else {
                        perc = 1.0f;
                    }
                }
                if (!this.direction) {
                    perc = 1.0f - perc;
                }
                final Array<Vector2> nPath = (Array<Vector2>)new Array();
                for (int j = 0; j < GameValues.ships.SHIP_LINE_PRECISION; ++j) {
                    nPath.add((Object)new Vector2((this.vPoints[j].x + Game.mapCoords.getPosX()) * Game.mapScale.getCurrentScale(), (-this.vPoints[j].y - Game.mapCoords.getPosY()) * Game.mapScale.getCurrentScale()));
                }
                Renderer.shapeDrawer.setColor(new Color(1.0f, 1.0f, 1.0f, 0.025f + 0.125f * perc));
                Renderer.shapeDrawer.path((Iterable)nPath, 0.1f + 0.85f * perc, JoinType.SMOOTH, true);
                Renderer.oSBBorder.end();
                Renderer.oSBBorder.begin();
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
}
