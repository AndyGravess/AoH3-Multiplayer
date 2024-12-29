// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu;

import aoh.kingdoms.history.map.province.ProvinceDraw;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Game;
import java.util.ArrayList;
import java.util.List;

public class ClickAnimation
{
    public int iX;
    public int iY;
    public int iWMax;
    public int iHMax;
    public static final float ANIMATION_DURATION = 400.0f;
    public long animationTime;
    public float fPerc;
    public float extraSpeed;
    public List<ClickXY> clickXY;
    public int clickXYSize;
    
    public ClickAnimation(final int iX, final int iY, final int iWMax, final int iHMax) {
        this.animationTime = 0L;
        this.fPerc = 1.0f;
        this.clickXY = new ArrayList<ClickXY>();
        this.clickXYSize = 0;
        this.iX = iX;
        this.iY = iY;
        this.iWMax = iWMax;
        this.iHMax = iHMax;
        this.extraSpeed = 1.35f + Game.oR.nextInt(50) / 100.0f;
        this.animationTime = CFG.currentTimeMillis;
        for (int i = GameValues.clickAnim.CLICK_ANIM_X.length - 1; i >= 0; --i) {
            this.clickXY.add(new ClickXY(iWMax * GameValues.clickAnim.CLICK_ANIM_X[i], iHMax * GameValues.clickAnim.CLICK_ANIM_Y[i], GameValues.clickAnim.CLICK_ANIM_SPEED_X[i], GameValues.clickAnim.CLICK_ANIM_SPEED_Y[i]));
        }
        this.clickXYSize = this.clickXY.size();
    }
    
    public boolean draw(final SpriteBatch oSB) {
        boolean out = false;
        this.fPerc -= (CFG.currentTimeMillis - this.animationTime) / 400.0f;
        this.animationTime = CFG.currentTimeMillis;
        if (this.fPerc <= 0.0f) {
            this.fPerc = 0.0f;
            out = true;
        }
        Renderer.shapeDrawer.setColor(new Color(this.getColor().r, this.getColor().g, this.getColor().b, 0.65f * this.fPerc));
        for (int i = 0; i < this.clickXYSize; ++i) {
            Renderer.shapeDrawer.filledCircle((float)(this.iX + (int)this.clickXY.get(i).fX), (float)(-this.iY + (int)this.clickXY.get(i).fY), this.clickXY.get(i).width * this.fPerc);
            final ClickXY clickXY = this.clickXY.get(i);
            clickXY.fX += this.clickXY.get(i).speedX * this.extraSpeed;
            final ClickXY clickXY2 = this.clickXY.get(i);
            clickXY2.fY += this.clickXY.get(i).speedY * this.extraSpeed * (0.75f + 0.25f * this.fPerc);
        }
        return out;
    }
    
    public Color getColor() {
        return ProvinceDraw.progressBar;
    }
    
    public static class ClickXY
    {
        public float fX;
        public float fY;
        public float speedX;
        public float speedY;
        public float width;
        
        public ClickXY(final float fX, final float fY, final float speedX, final float speedY) {
            this.fX = fX;
            this.fY = fY;
            this.speedX = speedX;
            this.speedY = speedY;
            this.width = GameValues.clickAnim.CLICK_WIDTH;
            if (GameValues.clickAnim.CLICK_WIDTH_RANDOM >= 2.0f) {
                this.width += Game.oR.nextInt((int)GameValues.clickAnim.CLICK_WIDTH_RANDOM);
            }
        }
    }
}
