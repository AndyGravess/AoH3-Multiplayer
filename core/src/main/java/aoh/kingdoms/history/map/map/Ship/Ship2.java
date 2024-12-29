// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.map.Ship;

import aoc.kingdoms.lukasz.jakowski.CFG;
import java.util.List;
import aoc.kingdoms.lukasz.textures.Image;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game;

public class Ship2
{
    public static final int IMG_WH = 32;
    public float posX;
    public float posY;
    public int angle;
    public int shipLineID;
    public int shipIMGID;
    public float speed;
    public float currentWidth;
    public int tID;
    public boolean movingBack;
    public boolean isInView;
    public boolean remove;
    
    public Ship2(final int nShipLineID) {
        this.currentWidth = 0.0f;
        this.tID = 0;
        this.movingBack = false;
        this.remove = false;
        this.shipLineID = nShipLineID;
        this.randomize();
    }
    
    public final void randomize() {
        this.shipIMGID = Game.oR.nextInt(GameValues.ships.SHIP_IMAGES);
        this.speed = GameValues.ships.SHIP_SPEED_MIN + Game.oR.nextInt(GameValues.ships.SHIP_SPEED_RANDOM) / 100.0f;
    }
    
    public final void update() {
        this.currentWidth += this.speed;
        if (this.movingBack) {
            if (this.currentWidth >= ShipManager.shipLines.get(this.shipLineID).width.get(this.tID - 1)) {
                if (--this.tID < 1) {
                    this.tID = 0;
                    this.movingBack = false;
                    this.randomize();
                    this.currentWidth = 0.0f;
                    this.remove = true;
                    return;
                }
                this.currentWidth -= ShipManager.shipLines.get(this.shipLineID).width.get(this.tID);
            }
            this.posX = ShipManager.shipLines.get(this.shipLineID).vPoints[this.tID].x + (ShipManager.shipLines.get(this.shipLineID).vPoints[this.tID - 1].x - ShipManager.shipLines.get(this.shipLineID).vPoints[this.tID].x) * (this.currentWidth / ShipManager.shipLines.get(this.shipLineID).width.get(this.tID));
            this.posY = ShipManager.shipLines.get(this.shipLineID).vPoints[this.tID].y + (ShipManager.shipLines.get(this.shipLineID).vPoints[this.tID - 1].y - ShipManager.shipLines.get(this.shipLineID).vPoints[this.tID].y) * (this.currentWidth / ShipManager.shipLines.get(this.shipLineID).width.get(this.tID));
            this.updateIsInView();
            if (this.isInView) {
                this.angle = (int)Math.abs(360.0 + Math.atan2(ShipManager.shipLines.get(this.shipLineID).vPoints[this.tID].y - ShipManager.shipLines.get(this.shipLineID).vPoints[this.tID - 1].y, -ShipManager.shipLines.get(this.shipLineID).vPoints[this.tID].x + ShipManager.shipLines.get(this.shipLineID).vPoints[this.tID - 1].x) * 180.0 / 3.141592653589793) % 360;
            }
        }
        else {
            if (this.currentWidth >= ShipManager.shipLines.get(this.shipLineID).width.get(this.tID)) {
                if (++this.tID > GameValues.ships.SHIP_LINE_PRECISION - 2) {
                    this.tID = GameValues.ships.SHIP_LINE_PRECISION - 1;
                    this.movingBack = true;
                    this.randomize();
                    this.currentWidth = 0.0f;
                    this.remove = true;
                    return;
                }
                this.currentWidth -= ShipManager.shipLines.get(this.shipLineID).width.get(this.tID);
            }
            this.posX = ShipManager.shipLines.get(this.shipLineID).vPoints[this.tID].x + (ShipManager.shipLines.get(this.shipLineID).vPoints[this.tID + 1].x - ShipManager.shipLines.get(this.shipLineID).vPoints[this.tID].x) * (this.currentWidth / ShipManager.shipLines.get(this.shipLineID).width.get(this.tID));
            this.posY = ShipManager.shipLines.get(this.shipLineID).vPoints[this.tID].y + (ShipManager.shipLines.get(this.shipLineID).vPoints[this.tID + 1].y - ShipManager.shipLines.get(this.shipLineID).vPoints[this.tID].y) * (this.currentWidth / ShipManager.shipLines.get(this.shipLineID).width.get(this.tID));
            this.updateIsInView();
            if (this.isInView) {
                this.angle = (int)Math.abs(360.0 + Math.atan2(ShipManager.shipLines.get(this.shipLineID).vPoints[this.tID].y - ShipManager.shipLines.get(this.shipLineID).vPoints[this.tID + 1].y, -ShipManager.shipLines.get(this.shipLineID).vPoints[this.tID].x + ShipManager.shipLines.get(this.shipLineID).vPoints[this.tID + 1].x) * 180.0 / 3.141592653589793) % 360;
            }
        }
    }
    
    public void drawCurrentScale(final SpriteBatch oSB, final int ageGroup) {
        if (this.isInView) {
            ShipManager.shipImg.get(ageGroup).get(this.shipIMGID).draw2(oSB, (int)(this.posX + Game.mapCoords.getPosX()) + CFG.rotateXMoveUnits[this.angle], (int)(this.posY + Game.mapCoords.getPosY()) + CFG.rotateYMoveUnits[this.angle], 32, 32, (float)this.angle, true, false);
        }
    }
    
    public void draw(final SpriteBatch oSB, final int ageGroup) {
        if (this.isInView) {
            ShipManager.shipImg.get(ageGroup).get(this.shipIMGID).draw2(oSB, (int)((this.posX + Game.mapCoords.getPosX()) * Game.mapScale.getCurrentScale()) + CFG.rotateXMoveUnits[this.angle], (int)((this.posY + Game.mapCoords.getPosY()) * Game.mapScale.getCurrentScale()) + CFG.rotateYMoveUnits[this.angle], 32, 32, (float)this.angle, true, false);
        }
    }
    
    public final void updateIsInView() {
        if (this.inViewY() && this.inViewX()) {
            this.isInView = true;
            return;
        }
        this.isInView = false;
    }
    
    public final boolean inViewY() {
        return Game.inViewY_CordsY_Height + 32 >= this.posY && Game.inViewY_CordsY - 32 <= this.posY + 32.0f;
    }
    
    public final boolean inViewX() {
        return Game.inViewX_CordsX_Width + 32 >= this.posX && Game.inViewX_CordsX - 32 <= this.posX + 32.0f;
    }
    
    public final boolean inViewX2() {
        return Game.inViewX_CordsX_Width + 32 >= this.posX + Game.mapBG.getWidth() && Game.inViewX_CordsX - 32 <= this.posX + Game.mapBG.getWidth() + 32.0f;
    }
}
