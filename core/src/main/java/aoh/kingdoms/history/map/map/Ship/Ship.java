// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.map.Ship;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.CatmullRomSpline;
import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.math.Vector2;

public class Ship
{
    public float posX;
    public float posY;
    public float moveToX;
    public float moveToY;
    public float angle;
    public Vector2[] vPoints;
    public List<Integer> width;
    public float speed;
    public float currentWidth;
    public int iPrecision;
    int tID;
    
    public Ship(final int nX, final int nY, final int moveToX, final int moveToY, final int catX, final int catY) {
        this.width = new ArrayList<Integer>();
        this.speed = 2.0f;
        this.currentWidth = 0.0f;
        this.iPrecision = 200;
        this.tID = 0;
        this.posX = (float)nX;
        this.posY = (float)nY;
        this.moveToX = (float)moveToX;
        this.moveToY = (float)moveToY;
        this.vPoints = new Vector2[this.iPrecision];
        final Vector2[] dataSet = { new Vector2((float)nX, (float)nY), new Vector2((float)nX, (float)nY), new Vector2((float)catX, (float)catY), new Vector2((float)moveToX, (float)moveToY), new Vector2((float)moveToX, (float)moveToY) };
        final CatmullRomSpline<Vector2> oCatmull = (CatmullRomSpline<Vector2>)new CatmullRomSpline((Vector[])dataSet, false);
        for (int j = 0; j < this.iPrecision; ++j) {
            oCatmull.valueAt((Vector)(this.vPoints[j] = new Vector2()), j / (this.iPrecision - 1.0f));
        }
        for (int j = 0; j < this.iPrecision - 1; ++j) {
            this.width.add((int)Math.ceil(Math.sqrt((this.vPoints[j + 1].x - this.vPoints[j].x) * (this.vPoints[j + 1].x - this.vPoints[j].x) + (this.vPoints[j].y - this.vPoints[j + 1].y) * (this.vPoints[j].y - this.vPoints[j + 1].y))));
        }
    }
    
    public void drawCurrentScale(final SpriteBatch oSB) {
        this.currentWidth += this.speed;
        if (this.currentWidth >= this.width.get(this.tID)) {
            this.currentWidth -= this.width.get(this.tID);
            if (++this.tID > 198) {
                this.tID = 0;
            }
        }
        this.posX = this.vPoints[this.tID].x + (this.vPoints[this.tID + 1].x - this.vPoints[this.tID].x) * (this.currentWidth / this.width.get(this.tID));
        this.posY = this.vPoints[this.tID].y + (this.vPoints[this.tID + 1].y - this.vPoints[this.tID].y) * (this.currentWidth / this.width.get(this.tID));
        this.angle = (float)((int)Math.abs(360.0 + Math.atan2(this.vPoints[this.tID].y - this.vPoints[this.tID + 1].y, -this.vPoints[this.tID].x + this.vPoints[this.tID + 1].x) * 180.0 / 3.141592653589793) % 360);
    }
    
    public void draw(final SpriteBatch oSB) {
        this.currentWidth += this.speed;
        if (this.currentWidth >= this.width.get(this.tID)) {
            this.currentWidth -= this.width.get(this.tID);
            if (this.tID++ > 198) {
                this.tID = 0;
            }
        }
        this.posX = this.vPoints[this.tID].x + (this.vPoints[this.tID + 1].x - this.vPoints[this.tID].x) * (this.currentWidth / this.width.get(this.tID));
        this.posY = this.vPoints[this.tID].y + (this.vPoints[this.tID + 1].y - this.vPoints[this.tID].y) * (this.currentWidth / this.width.get(this.tID));
        this.angle = (float)((int)Math.abs(360.0 + Math.atan2(this.vPoints[this.tID].y - this.vPoints[this.tID + 1].y, -this.vPoints[this.tID].x + this.vPoints[this.tID + 1].x) * 180.0 / 3.141592653589793) % 360);
    }
}
