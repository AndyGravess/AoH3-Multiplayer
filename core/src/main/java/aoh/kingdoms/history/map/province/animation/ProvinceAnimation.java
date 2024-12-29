// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.map.province.animation;

import aoh.kingdoms.history.mainGame.CFG;

public class ProvinceAnimation
{
    public final int START_PROVINCE_ALPHA = 30;
    public final int START_PROVINCE_BORDER_ALPHA = 255;
    public final int TIME_UPDATE = 45;
    public long lTime;
    public float fAlpha;
    public int iColorStepID;
    public int iStepID;
    public boolean backAnimation;
    public long lTimeBorder;
    public int iStepIDBorder;
    public int iBorderAlpha;
    public boolean backAnimationBorder;
    
    public ProvinceAnimation() {
        this.lTime = 0L;
        this.fAlpha = 30.0f;
        this.iStepID = 0;
        this.backAnimation = false;
        this.lTimeBorder = 0L;
        this.iStepIDBorder = 0;
        this.iBorderAlpha = 255;
        this.backAnimationBorder = false;
    }
    
    public final void update() {
        if (this.lTime < CFG.currentTimeMillis - 45L) {
            ++this.iStepID;
            if (this.backAnimation) {
                this.fAlpha += 1.25f;
                this.iBorderAlpha += 6;
                --this.iColorStepID;
            }
            else {
                this.fAlpha -= 1.25f;
                this.iBorderAlpha -= 6;
                ++this.iColorStepID;
            }
            this.lTime = CFG.currentTimeMillis;
            if (this.iStepID == 20) {
                if (this.backAnimation) {
                    this.fAlpha = 30.0f;
                    this.iBorderAlpha = 255;
                }
                this.iStepID = 0;
                this.backAnimation = !this.backAnimation;
                this.backAnimationBorder = !this.backAnimationBorder;
                this.lTime += (this.backAnimation ? 450L : 600L);
            }
        }
    }
    
    public final void resetAnimationData() {
        this.lTime = 0L;
        this.fAlpha = 30.0f;
        this.iStepID = 0;
        this.backAnimation = false;
        this.iColorStepID = 0;
        this.lTimeBorder = CFG.currentTimeMillis + 200L;
        this.iStepIDBorder = 0;
        this.iBorderAlpha = 255;
        this.backAnimationBorder = false;
    }
    
    public final float getAlpha() {
        return this.fAlpha;
    }
    
    public final int getBorderAlpha() {
        return this.iBorderAlpha;
    }
    
    public final boolean getBackAnimation() {
        return this.backAnimation;
    }
    
    public final int getStepID() {
        return this.iStepID;
    }
    
    public final int getColorStepID() {
        return this.iColorStepID;
    }
}
