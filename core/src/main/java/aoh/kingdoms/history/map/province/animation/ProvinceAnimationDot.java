// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.map.province.animation;

import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.Color;

public class ProvinceAnimationDot
{
    public int iProvinceID;
    public Color dotColor;
    public int posX;
    public int posY;
    public static final float ANIMATION_DURATION = 1250.0f;
    public long animationTime;
    public float fPerc;
    
    public ProvinceAnimationDot(final int nProvinceID, final Color nColor) {
        this.animationTime = 0L;
        this.fPerc = 1.0f;
        this.iProvinceID = nProvinceID;
        this.dotColor = nColor;
        this.animationTime = CFG.currentTimeMillis;
        if (Game.getProvince(nProvinceID).getCitiesSize() > 0) {
            this.posX = Game.getProvince(nProvinceID).getCity(0).getPosX();
            this.posY = -Game.getProvince(nProvinceID).getCity(0).getPosY();
        }
        else {
            this.posX = Game.getProvince(nProvinceID).getCenterX();
            this.posY = -Game.getProvince(nProvinceID).getCenterY();
        }
    }
    
    public boolean draw(final SpriteBatch oSB, final float nScale) {
        boolean out = false;
        this.fPerc -= (CFG.currentTimeMillis - this.animationTime) / 1250.0f;
        this.animationTime = CFG.currentTimeMillis;
        if (this.fPerc <= 0.0f) {
            this.fPerc = 0.0f;
            out = true;
        }
        if (Game.getProvince(this.iProvinceID).getDrawProvince()) {
            Renderer.shapeDrawer.setColor(new Color(this.dotColor.r, this.dotColor.g, this.dotColor.b, 0.15f * this.fPerc));
            Renderer.shapeDrawer.filledCircle((this.posX + Game.getProvince(this.iProvinceID).getTranslateProvincePosX()) * nScale, (this.posY - Game.mapCoords.getPosY()) * nScale, 20.0f * nScale * this.fPerc);
            Renderer.shapeDrawer.setColor(new Color(this.dotColor.r, this.dotColor.g, this.dotColor.b, 0.45f * this.fPerc));
            Renderer.shapeDrawer.filledCircle((this.posX + Game.getProvince(this.iProvinceID).getTranslateProvincePosX()) * nScale, (this.posY - Game.mapCoords.getPosY()) * nScale, 14.0f * nScale * this.fPerc);
        }
        return out;
    }
}
