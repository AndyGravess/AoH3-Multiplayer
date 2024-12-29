// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.FBO;

import aoc.kingdoms.lukasz.jakowski.CFG;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoc.kingdoms.lukasz.jakowski.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;

public class FBOProvincesBG
{
    public static FrameBuffer fboProvince_PBG;
    public static Texture textureProvince_PBG;
    public static int fboNumToGenerate_PB;
    public static int lastPosX;
    public static int lastPosY;
    public static int fboPosX;
    public static int fboPosY;
    
    public static final void redrawnProvinces() {
        FBOProvincesBG.lastPosX = -9194519;
    }
    
    public static final void disposeProvincesFBO() {
        if (FBOProvincesBG.fboProvince_PBG != null) {
            FBOProvincesBG.fboProvince_PBG.dispose();
            FBOProvincesBG.fboProvince_PBG = null;
        }
    }
    
    public static final void disposeProvincesTexture() {
        if (FBOProvincesBG.textureProvince_PBG != null) {
            FBOProvincesBG.textureProvince_PBG.dispose();
            FBOProvincesBG.textureProvince_PBG = null;
        }
    }
    
    public static final void updateFBO() {
        if (FBOProvincesBG.lastPosX == Game.mapCoords.getPosX() && FBOProvincesBG.lastPosY == Game.mapCoords.getPosY()) {
            ++FBOProvincesBG.fboNumToGenerate_PB;
        }
        else {
            FBOProvincesBG.lastPosX = Game.mapCoords.getPosX();
            FBOProvincesBG.lastPosY = Game.mapCoords.getPosY();
            FBOProvincesBG.fboNumToGenerate_PB = 0;
        }
    }
    
    public static final void drawPBG(final SpriteBatch oSB, final float fAlpha) {
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, fAlpha));
        draw(oSB, 0, 0, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, 1.0f, 1.0f, 0.0f, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, false, true);
        oSB.setColor(Color.WHITE);
    }
    
    public static final void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final int originX, final int originY, final int nWidth, final int nHeight, final float scaleX, final float scaleY, final float rotation, final int srcX, final int srcY, final int srcWidth, final int srcHeight, final boolean flipX, final boolean flipY) {
        oSB.draw(FBOProvincesBG.textureProvince_PBG, (float)nPosX, (float)(-nPosY - CFG.GAME_HEIGHT), (float)originX, (float)originY, (float)nWidth, (float)nHeight, scaleX, scaleY, rotation, srcX, srcY, srcWidth, srcHeight, flipX, flipY);
    }
    
    static {
        FBOProvincesBG.fboProvince_PBG = null;
        FBOProvincesBG.textureProvince_PBG = null;
        FBOProvincesBG.fboNumToGenerate_PB = 0;
        FBOProvincesBG.lastPosX = -1;
        FBOProvincesBG.lastPosY = -1;
        FBOProvincesBG.fboPosX = -1;
        FBOProvincesBG.fboPosY = -1;
    }
}
