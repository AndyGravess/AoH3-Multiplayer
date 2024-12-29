// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.FBO;

import aoc.kingdoms.lukasz.jakowski.CFG;
import com.badlogic.gdx.graphics.Color;
import aoc.kingdoms.lukasz.map.province.ProvinceDrawArmy;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoc.kingdoms.lukasz.jakowski.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;

public class FBOProvinceNames
{
    public static FrameBuffer fboProvince_Names;
    public static Texture textureProvince_Names;
    public static int fboNumToGenerate_Names;
    public static int lastPosX;
    public static int lastPosY;
    public static int fboPosX;
    public static int fboPosY;
    
    public static final void redrawnProvinceNames() {
        FBOProvinceNames.lastPosX = -9194519;
    }
    
    public static final void disposeProvinceNamesFBO() {
        if (FBOProvinceNames.fboProvince_Names != null) {
            FBOProvinceNames.fboProvince_Names.dispose();
            FBOProvinceNames.fboProvince_Names = null;
        }
    }
    
    public static final void disposeProvinceNamesTexture() {
        if (FBOProvinceNames.textureProvince_Names != null) {
            FBOProvinceNames.textureProvince_Names.dispose();
            FBOProvinceNames.textureProvince_Names = null;
        }
    }
    
    public static final void updateFBO() {
        if (FBOProvinceNames.lastPosX == Game.mapCoords.getPosX() && FBOProvinceNames.lastPosY == Game.mapCoords.getPosY()) {
            ++FBOProvinceNames.fboNumToGenerate_Names;
        }
        else {
            FBOProvinceNames.lastPosX = Game.mapCoords.getPosX();
            FBOProvinceNames.lastPosY = Game.mapCoords.getPosY();
            FBOProvinceNames.fboNumToGenerate_Names = 0;
        }
    }
    
    public static final void drawProvinceNames(final SpriteBatch oSB) {
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, Game.settingsManager.PROVINCE_NAMES_ALPHA * ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA));
        draw(oSB, 0, 0, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, 1.0f, 1.0f, 0.0f, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, false, true);
        oSB.setColor(Color.WHITE);
    }
    
    public static final void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final int originX, final int originY, final int nWidth, final int nHeight, final float scaleX, final float scaleY, final float rotation, final int srcX, final int srcY, final int srcWidth, final int srcHeight, final boolean flipX, final boolean flipY) {
        oSB.draw(FBOProvinceNames.textureProvince_Names, (float)nPosX, (float)(-nPosY - CFG.GAME_HEIGHT), (float)originX, (float)originY, (float)nWidth, (float)nHeight, scaleX, scaleY, rotation, srcX, srcY, srcWidth, srcHeight, flipX, flipY);
    }
    
    static {
        FBOProvinceNames.fboProvince_Names = null;
        FBOProvinceNames.textureProvince_Names = null;
        FBOProvinceNames.fboNumToGenerate_Names = 0;
        FBOProvinceNames.lastPosX = -1;
        FBOProvinceNames.lastPosY = -1;
        FBOProvinceNames.fboPosX = -1;
        FBOProvinceNames.fboPosY = -1;
    }
}
