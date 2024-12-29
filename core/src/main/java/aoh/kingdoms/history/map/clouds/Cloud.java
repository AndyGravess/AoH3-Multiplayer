// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.clouds;

import aoc.kingdoms.lukasz.textures.Image;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;

public class Cloud
{
    private boolean animationStart;
    public int iCloudImageID;
    public boolean isInView;
    public float iPosX;
    public int iPosX_SecondSideOfMap;
    public float iPosY;
    public float fScale;
    public int iRotate;
    public float fAlpha;
    public float fFinalAlpha;
    public int iShadowX;
    public int iShadowY;
    private long lTime;
    public int iMax_WidthHeight;
    public int iHeight_RemoveCloud;
    
    public Cloud(final int iCloudImageID, final int nPosX, final int nPosY, final float fScale, final int iRotate) {
        this.animationStart = true;
        this.iMax_WidthHeight = 0;
        this.iHeight_RemoveCloud = 0;
        this.init(iCloudImageID, nPosX, nPosY, fScale, iRotate);
    }
    
    public final void init(final int iCloudImageID, final int nPosX, final int nPosY, final float fScale, final int iRotate) {
        this.iCloudImageID = iCloudImageID;
        this.iRotate = iRotate;
        this.iPosX = (float)nPosX;
        this.iPosY = (float)nPosY;
        this.fScale = fScale;
        this.fFinalAlpha = Game.cloudsAnimation.cloudsSettings.minAlpha + Game.oR.nextInt(Math.max(1, (int)(Game.cloudsAnimation.cloudsSettings.randomAlpha * 1000.0f))) / 1000.0f;
        this.fAlpha = 0.0f;
        this.iShadowX = (int)(Game.cloudsAnimation.cloudsSettings.shadowX * Math.max(1.0f, fScale));
        this.iShadowY = (int)(Game.cloudsAnimation.cloudsSettings.shadowY * Math.max(1.0f, fScale));
        this.lTime = CFG.currentTimeMillis;
        this.iMax_WidthHeight = (int)(Game.cloudsAnimation.imageCloudMaxDimension.get(iCloudImageID) * fScale);
        this.iHeight_RemoveCloud = (int)(-Game.cloudsAnimation.imageCloud.get(iCloudImageID).getHeight() * fScale);
    }
    
    public final void update() {
        this.iPosX += Game.cloudsAnimation.cloudsSettings.moveSpeedX;
        this.iPosY -= Game.cloudsAnimation.cloudsSettings.moveSpeedY;
        if (this.iPosX + Game.cloudsAnimation.imageCloud.get(this.iCloudImageID).getWidth() * this.fScale > Game.mapBG.getWidth()) {
            this.iPosX -= Game.mapBG.getWidth();
        }
        if (this.animationStart) {
            this.fAlpha = Math.min(this.fFinalAlpha * ((CFG.currentTimeMillis - this.lTime) / (float)Game.cloudsAnimation.cloudsSettings.spawnAnimationTime), this.fFinalAlpha);
            if (CFG.currentTimeMillis - this.lTime > Game.cloudsAnimation.cloudsSettings.spawnAnimationTime) {
                this.animationStart = false;
                this.fAlpha = this.fFinalAlpha;
            }
        }
    }
    
    public final void updateIsInView() {
        if (this.inViewY()) {
            if (this.inViewX()) {
                this.isInView = true;
                this.iPosX_SecondSideOfMap = 0;
                return;
            }
            if (Game.mapCoords.getSecondSideOfMap() && this.inViewX2()) {
                this.isInView = true;
                this.iPosX_SecondSideOfMap = Game.mapBG.getWidth();
                return;
            }
        }
        this.isInView = false;
    }
    
    public final boolean inViewY() {
        return Game.inViewY_CordsY_Height + this.iMax_WidthHeight >= this.iPosY && Game.inViewY_CordsY - this.iMax_WidthHeight <= this.iPosY + this.iMax_WidthHeight;
    }
    
    public final boolean inViewX() {
        return Game.inViewX_CordsX_Width + this.iMax_WidthHeight >= this.iPosX && Game.inViewX_CordsX - this.iMax_WidthHeight <= this.iPosX + this.iMax_WidthHeight;
    }
    
    public final boolean inViewX2() {
        return Game.inViewX_CordsX_Width + this.iMax_WidthHeight >= this.iPosX + Game.mapBG.getWidth() && Game.inViewX_CordsX - this.iMax_WidthHeight <= this.iPosX + Game.mapBG.getWidth() + this.iMax_WidthHeight;
    }
    
    public final boolean removeCloud() {
        return this.iPosY < this.iHeight_RemoveCloud;
    }
}
