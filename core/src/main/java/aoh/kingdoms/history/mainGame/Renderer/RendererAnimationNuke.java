// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.Renderer;

import com.badlogic.gdx.graphics.Color;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.SoundsManager;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.CFG;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Game;

public class RendererAnimationNuke
{
    public int iProvinceID;
    public int iPosX;
    public int iPosY;
    public long lTime;
    public int currentIMG;
    public int TURN_ID;
    public boolean remove;
    public static long lastTimeNukePlayed;
    
    public RendererAnimationNuke(final int iProvinceID) {
        this.iProvinceID = 0;
        this.iPosX = 0;
        this.iPosY = 0;
        this.lTime = 0L;
        this.currentIMG = 0;
        this.TURN_ID = 0;
        this.remove = false;
        this.iProvinceID = iProvinceID;
        if (Game.getProvince(iProvinceID).getCitiesSize() > 0) {
            this.iPosX = Game.getProvince(iProvinceID).getCity(0).getPosX();
            this.iPosY = Game.getProvince(iProvinceID).getCity(0).getPosY();
        }
        else {
            this.iPosX = Game.getProvince(iProvinceID).iCenterShiftX;
            this.iPosY = Game.getProvince(iProvinceID).iCenterShiftY;
        }
        this.TURN_ID = Game_Calendar.TURN_ID;
    }
    
    public void draw(final SpriteBatch oSB) {
        if (Game_Calendar.TURN_ID - this.TURN_ID > 60) {
            this.remove = true;
        }
        if (Game.getProvince(this.iProvinceID).getDrawProvince() && !this.remove) {
            if (this.lTime == 0L) {
                this.lTime = CFG.currentTimeMillis;
                if (RendererAnimationNuke.lastTimeNukePlayed + GameValues.atomic.ATOMIC_BOMB_SOUND_EFFECT_LOCK_TIME < CFG.currentTimeMillis) {
                    RendererAnimationNuke.lastTimeNukePlayed = CFG.currentTimeMillis;
                    Game.soundsManager.playSound(SoundsManager.SOUND_NUKE, Game.soundsManager.getSoundsVolumeMaster());
                }
            }
            final float fProgress = 0.1f + 0.9f * Math.min(1.0f, (CFG.currentTimeMillis - this.lTime) / GameValues.atomic.ATOMIC_BOMB_ANIMATION_TIME);
            final int currentW = (int)(ImageManager.getImage(Images.nukeImg.get(0)).getWidth() * Game.mapScale.getCurrentScale());
            final int currentH = (int)(ImageManager.getImage(Images.nukeImg.get(0)).getHeight() * Game.mapScale.getCurrentScale());
            final int nPosX = (int)((this.iPosX + Game.getProvince(this.iProvinceID).getTranslateProvincePosX()) * Game.mapScale.getCurrentScale());
            final int nPosY = (int)((this.iPosY + Game.mapCoords.getPosY()) * Game.mapScale.getCurrentScale());
            final float fProgress2 = 1.0f - 0.9f * Math.min(1.0f, this.currentIMG / (float)Images.nukeIMGSize);
            oSB.setColor(new Color(Colors.HOVER_YELLOW.r, Colors.HOVER_YELLOW.g, Colors.HOVER_YELLOW.b, 0.2f * fProgress2));
            Images.gradientXY.draw(oSB, nPosX - currentW, nPosY - currentH, currentW * 2, currentH);
            Images.gradientXY.draw(oSB, nPosX - currentW, nPosY + currentH - currentH, currentW * 2, currentH, false, true);
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
            ImageManager.getImage(Images.nukeImg.get(this.currentIMG)).draw(oSB, nPosX - currentW / 2, nPosY - currentH / 2, currentW, currentH);
            if (fProgress >= 1.0f) {
                ++this.currentIMG;
                this.lTime = CFG.currentTimeMillis;
                if (this.currentIMG >= Images.nukeIMGSize) {
                    this.currentIMG = 0;
                    this.remove = true;
                }
            }
            oSB.setColor(Color.WHITE);
        }
    }
    
    static {
        RendererAnimationNuke.lastTimeNukePlayed = 0L;
    }
}
