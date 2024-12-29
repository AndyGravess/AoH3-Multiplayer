// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.province;

import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoc.kingdoms.lukasz.jakowski.GlyphLayout_Game;
import java.util.List;
import aoc.kingdoms.lukasz.jakowski.zOther.Point_XY;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.map.army.ArmyManager;
import aoc.kingdoms.lukasz.map.army.ArmyRegiment;
import aoc.kingdoms.lukasz.map.army.ArmyDivision;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.textures.Image;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.map.army.ArmyDivision_Shadow;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import com.badlogic.gdx.graphics.Color;

public class ProvinceDrawArmy
{
    public static final Color COLOR_ARMY;
    public static final Color COLOR_ARMY2;
    public static final Color COLOR_ARMY_OLD;
    public static int ARMY_HEIGHT;
    public static final int HOVER_PADDING = 4;
    public static final int FLAG_PADDING_Y = 2;
    public static final int FLAG_PADDING_X = 3;
    public static final int FLAG_PADDING_X_RIGHT = 2;
    public static final int FLAG_PADDING_X_SUM = 5;
    public static final int ARMY_PADDING_X = 5;
    public static final float DRAW_ARMY_DEFAULT_ALPHA = 1.0f;
    public static boolean drawHideAnimation;
    public static float DRAW_ARMY_ALPHA;
    public static long DRAW_ARMY_TIME;
    public static long DRAW_ARMY_TIME_HIDE;
    public static float DRAW_ARMY_MIN_SCALE_ANIMATION;
    public static boolean drawCitiesHideAnimation;
    public static float DRAW_CITIES_ALPHA;
    public static long DRAW_CITIES_TIME;
    public static long DRAW_CITIES_TIME_HIDE;
    public static boolean drawProvinceNamesHideAnimation;
    public static float DRAW_PROVINCE_NAMES_ALPHA;
    public static long DRAW_PROVINCE_NAMES_TIME;
    public static long DRAW_PROVINCE_NAMES_TIME_HIDE;
    public static boolean drawOccupiedHideAnimation;
    public static float DRAW_OCCUPIED_ALPHA;
    public static long DRAW_OCCUPIED_TIME;
    public static long DRAW_OCCUPIED_TIME_HIDE;
    public static Color moraleBG;
    public static Color moraleGreen;
    public static Color drawProvinceArmyColorAlpha;
    public static Color drawProvinceArmyTextColor;
    public static int armyFlagPosX;
    public static int armyFlagPosY;
    public static int armyFlagPosWidth;
    public static int armyFlagPosHeight;
    
    public static final void updateArmyImgID() {
        for (int i = 0; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).updateArmyImgID();
        }
    }
    
    public static final void updateDrawArmyAlpha() {
        if (Game.mapScale.getCurrentScale() >= Game.DRAW_ARMY_MIN_SCALE) {
            updateTime();
            ProvinceDrawArmy.drawHideAnimation = true;
            ProvinceDrawArmy.DRAW_ARMY_TIME_HIDE = 0L;
        }
        else if (ProvinceDrawArmy.drawHideAnimation) {
            updateTimeHide();
        }
        else {
            ProvinceDrawArmy.DRAW_ARMY_TIME = 0L;
        }
        if (Game.mapScale.getCurrentScale() >= Game.DRAW_CITIES_MIN_SCALE) {
            updateCitiesTime();
            ProvinceDrawArmy.drawCitiesHideAnimation = true;
            ProvinceDrawArmy.DRAW_CITIES_TIME_HIDE = 0L;
        }
        else if (ProvinceDrawArmy.drawCitiesHideAnimation) {
            updateCitiesTimeHide();
        }
        else {
            ProvinceDrawArmy.DRAW_CITIES_TIME = 0L;
        }
        if (Game.mapScale.getCurrentScale() >= Game.DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE) {
            updateProvinceNamesTime();
            ProvinceDrawArmy.drawProvinceNamesHideAnimation = true;
            ProvinceDrawArmy.DRAW_PROVINCE_NAMES_TIME_HIDE = 0L;
        }
        else if (ProvinceDrawArmy.drawProvinceNamesHideAnimation) {
            updateProvinceNamesTimeHide();
        }
        else {
            ProvinceDrawArmy.DRAW_PROVINCE_NAMES_TIME = 0L;
        }
        if (Game.mapScale.getCurrentScale() >= Game.DRAW_OCCUPIED_PROVINCES_MIN_SCALE) {
            updateOccupiedTime();
            ProvinceDrawArmy.drawOccupiedHideAnimation = true;
            ProvinceDrawArmy.DRAW_OCCUPIED_TIME_HIDE = 0L;
        }
        else if (ProvinceDrawArmy.drawOccupiedHideAnimation) {
            updateOccupiedTimeHide();
        }
        else {
            ProvinceDrawArmy.DRAW_OCCUPIED_TIME = 0L;
        }
    }
    
    public static final void updateTime() {
        if (ProvinceDrawArmy.DRAW_ARMY_TIME == 0L) {
            ProvinceDrawArmy.DRAW_ARMY_TIME = CFG.currentTimeMillis;
            ProvinceDrawArmy.DRAW_ARMY_ALPHA = 0.0f;
        }
        else if (ProvinceDrawArmy.DRAW_ARMY_ALPHA < 1.0f) {
            ProvinceDrawArmy.DRAW_ARMY_ALPHA = 1.0f * ((CFG.currentTimeMillis - ProvinceDrawArmy.DRAW_ARMY_TIME) / (float)Game.settingsManager.CIVILIZATIONS_NAMES_INTERVAL);
            if (ProvinceDrawArmy.DRAW_ARMY_ALPHA > 1.0f) {
                ProvinceDrawArmy.DRAW_ARMY_ALPHA = 1.0f;
            }
        }
    }
    
    public static final void updateCitiesTime() {
        if (ProvinceDrawArmy.DRAW_CITIES_TIME == 0L) {
            ProvinceDrawArmy.DRAW_CITIES_TIME = CFG.currentTimeMillis;
            ProvinceDrawArmy.DRAW_CITIES_ALPHA = 0.0f;
        }
        else if (ProvinceDrawArmy.DRAW_CITIES_ALPHA < 1.0f) {
            ProvinceDrawArmy.DRAW_CITIES_ALPHA = 1.0f * ((CFG.currentTimeMillis - ProvinceDrawArmy.DRAW_CITIES_TIME) / (float)Game.settingsManager.CIVILIZATIONS_NAMES_INTERVAL);
            if (ProvinceDrawArmy.DRAW_CITIES_ALPHA > 1.0f) {
                ProvinceDrawArmy.DRAW_CITIES_ALPHA = 1.0f;
            }
        }
    }
    
    public static final void updateTimeHide() {
        if (ProvinceDrawArmy.DRAW_ARMY_TIME_HIDE == 0L) {
            ProvinceDrawArmy.DRAW_ARMY_TIME_HIDE = CFG.currentTimeMillis;
            ProvinceDrawArmy.DRAW_ARMY_ALPHA = 1.0f;
        }
        else {
            ProvinceDrawArmy.DRAW_ARMY_ALPHA = 1.0f - 1.0f * ((CFG.currentTimeMillis - ProvinceDrawArmy.DRAW_ARMY_TIME_HIDE) / (Game.settingsManager.CIVILIZATIONS_NAMES_INTERVAL / 2.0f));
            if (ProvinceDrawArmy.DRAW_ARMY_ALPHA <= 0.0f) {
                ProvinceDrawArmy.DRAW_ARMY_ALPHA = 0.0f;
                ProvinceDrawArmy.drawHideAnimation = false;
            }
        }
    }
    
    public static final void updateCitiesTimeHide() {
        if (ProvinceDrawArmy.DRAW_CITIES_TIME_HIDE == 0L) {
            ProvinceDrawArmy.DRAW_CITIES_TIME_HIDE = CFG.currentTimeMillis;
            ProvinceDrawArmy.DRAW_CITIES_ALPHA = 1.0f;
        }
        else {
            ProvinceDrawArmy.DRAW_CITIES_ALPHA = 1.0f - 1.0f * ((CFG.currentTimeMillis - ProvinceDrawArmy.DRAW_CITIES_TIME_HIDE) / (Game.settingsManager.CIVILIZATIONS_NAMES_INTERVAL / 2.0f));
            if (ProvinceDrawArmy.DRAW_CITIES_ALPHA <= 0.0f) {
                ProvinceDrawArmy.DRAW_CITIES_ALPHA = 0.0f;
                ProvinceDrawArmy.drawCitiesHideAnimation = false;
            }
        }
    }
    
    public static final void updateProvinceNamesTimeHide() {
        if (ProvinceDrawArmy.DRAW_PROVINCE_NAMES_TIME_HIDE == 0L) {
            ProvinceDrawArmy.DRAW_PROVINCE_NAMES_TIME_HIDE = CFG.currentTimeMillis;
            ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA = 1.0f;
        }
        else {
            ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA = 1.0f - 1.0f * ((CFG.currentTimeMillis - ProvinceDrawArmy.DRAW_PROVINCE_NAMES_TIME_HIDE) / (Game.settingsManager.CIVILIZATIONS_NAMES_INTERVAL / 2.0f));
            if (ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA <= 0.0f) {
                ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA = 0.0f;
                ProvinceDrawArmy.drawProvinceNamesHideAnimation = false;
            }
        }
    }
    
    public static final void updateProvinceNamesTime() {
        if (ProvinceDrawArmy.DRAW_PROVINCE_NAMES_TIME == 0L) {
            ProvinceDrawArmy.DRAW_PROVINCE_NAMES_TIME = CFG.currentTimeMillis;
            ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA = 0.0f;
        }
        else if (ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA < 1.0f) {
            ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA = 1.0f * ((CFG.currentTimeMillis - ProvinceDrawArmy.DRAW_PROVINCE_NAMES_TIME) / (float)Game.settingsManager.CIVILIZATIONS_NAMES_INTERVAL);
            if (ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA > 1.0f) {
                ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA = 1.0f;
            }
        }
    }
    
    public static final void updateOccupiedTime() {
        if (ProvinceDrawArmy.DRAW_OCCUPIED_TIME == 0L) {
            ProvinceDrawArmy.DRAW_OCCUPIED_TIME = CFG.currentTimeMillis;
            ProvinceDrawArmy.DRAW_OCCUPIED_ALPHA = 0.0f;
        }
        else if (ProvinceDrawArmy.DRAW_OCCUPIED_ALPHA < 1.0f) {
            ProvinceDrawArmy.DRAW_OCCUPIED_ALPHA = 1.0f * ((CFG.currentTimeMillis - ProvinceDrawArmy.DRAW_OCCUPIED_TIME) / (float)Game.settingsManager.CIVILIZATIONS_NAMES_INTERVAL);
            if (ProvinceDrawArmy.DRAW_OCCUPIED_ALPHA > 1.0f) {
                ProvinceDrawArmy.DRAW_OCCUPIED_ALPHA = 1.0f;
            }
        }
    }
    
    public static final void updateOccupiedTimeHide() {
        if (ProvinceDrawArmy.DRAW_OCCUPIED_TIME_HIDE == 0L) {
            ProvinceDrawArmy.DRAW_OCCUPIED_TIME_HIDE = CFG.currentTimeMillis;
            ProvinceDrawArmy.DRAW_OCCUPIED_ALPHA = 1.0f;
        }
        else {
            ProvinceDrawArmy.DRAW_OCCUPIED_ALPHA = 1.0f - 1.0f * ((CFG.currentTimeMillis - ProvinceDrawArmy.DRAW_OCCUPIED_TIME_HIDE) / (Game.settingsManager.CIVILIZATIONS_NAMES_INTERVAL / 2.0f));
            if (ProvinceDrawArmy.DRAW_OCCUPIED_ALPHA <= 0.0f) {
                ProvinceDrawArmy.DRAW_OCCUPIED_ALPHA = 0.0f;
                ProvinceDrawArmy.drawOccupiedHideAnimation = false;
            }
        }
    }
    
    public static final void drawProvincesArmy(final SpriteBatch oSB) {
        if (Game.mapScale.getCurrentScale() >= Game.DRAW_ARMY_MIN_SCALE) {
            drawProvincesArmy_Just(oSB, ProvinceDrawArmy.DRAW_ARMY_ALPHA);
        }
        else if (Game.mapTouchManager.selectMode && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_PEACE_VIEW && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_INVEST_IN_ECONOMY && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_INCREASE_TAX_EFFICIENCY && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_DEVELOP_INFRASTRUCTURE && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_INCREASE_GROWTH_RATE && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_INCREASE_MANPOWER && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_CONVERT_RELIGION && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_CORE && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_BUILDING) {
            drawProvincesArmy_Just(oSB, 1.0f);
        }
        else if (ProvinceDrawArmy.drawHideAnimation && Game.mapScale.getCurrentScale() >= ProvinceDrawArmy.DRAW_ARMY_MIN_SCALE_ANIMATION) {
            drawProvincesArmy_Just(oSB, ProvinceDrawArmy.DRAW_ARMY_ALPHA);
        }
    }
    
    private static final void drawProvincesArmy_Just(final SpriteBatch oSB, final float fAlpha) {
        try {
            try {
                for (int i = 0; i < Game.activeArmySize; ++i) {
                    if (Game.getProvince(Game.activeArmy.get(i).iProvinceID).getDrawProvince()) {
                        Game.activeArmy.get(i).iArmyID = Game.getProvince(Game.activeArmy.get(i).iProvinceID).getArmyKeyID(Game.activeArmy.get(i).key);
                        if (Game.activeArmy.get(i).iArmyID < 0) {
                            Game.removeActiveArmy(i);
                        }
                        else if (!Game.getProvince(Game.activeArmy.get(i).iProvinceID).getArmy(Game.activeArmy.get(i).iArmyID).inBattle) {
                            drawProvinceArmyActive(oSB, Game.activeArmy.get(i).iProvinceID, Game.activeArmy.get(i).iArmyID, fAlpha);
                        }
                    }
                }
            }
            catch (final Exception ex2) {}
            try {
                if (Game.hoveredArmy.iProvinceID >= 0) {
                    Game.checkHoveredArmy_Fog();
                    if (Game.getProvince(Game.hoveredArmy.iProvinceID).getDrawProvince()) {
                        Game.hoveredArmy.iArmyID = Game.getProvince(Game.hoveredArmy.iProvinceID).getArmyKeyID(Game.hoveredArmy.key);
                        if (!Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).inBattle && Game.hoveredArmy.iProvinceID >= 0 && Game.hoveredArmy.iArmyID >= 0) {
                            drawProvinceArmyHover(oSB, Game.hoveredArmy.iProvinceID, Game.hoveredArmy.iArmyID, fAlpha);
                        }
                    }
                }
            }
            catch (final Exception ex3) {}
            ProvinceDrawArmy.drawProvinceArmyColorAlpha = new Color(1.0f, 1.0f, 1.0f, fAlpha);
            ProvinceDrawArmy.drawProvinceArmyTextColor = new Color(ProvinceDrawArmy.COLOR_ARMY.r, ProvinceDrawArmy.COLOR_ARMY.g, ProvinceDrawArmy.COLOR_ARMY.b, fAlpha);
            oSB.setColor(ProvinceDrawArmy.drawProvinceArmyColorAlpha);
            try {
                for (int i = 0; i < Game.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
                    Game.getProvince(Game.getWastelandProvinceInViewID(i)).drawArmy(oSB, fAlpha);
                }
                for (int i = 0; i < Game.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
                    Game.getProvince(Game.getSeaProvinceInViewID(i)).drawArmy(oSB, fAlpha);
                }
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    Game.getProvince(Game.getProvinceInViewID(i)).drawArmy(oSB, fAlpha);
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).drawArmy(oSB, fAlpha);
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
            oSB.setColor(Color.WHITE);
            try {
                if (Game.regroupArmyShadows.size() > 0) {
                    for (int i = 0; i < Game.regroupArmyShadows.size(); ++i) {
                        drawProvinceArmyWithFlag_Shadow(oSB, Game.regroupArmyShadows.get(i).iProvinceID, Color.WHITE, 0.5f, Game.regroupArmyShadows.get(i).sArmy, Game.regroupArmyShadows.get(i).iArmyWidth, Game.regroupArmyShadows.get(i).extraY);
                    }
                }
            }
            catch (final Exception exr) {
                CFG.exceptionStack(exr);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        oSB.setColor(Color.WHITE);
    }
    
    public static final void drawMapModeDetails(final SpriteBatch oSB) {
        if (Game.mapScale.getCurrentScale() >= Game.DRAW_ARMY_MIN_SCALE) {
            for (int i = 0; i < Game.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
                Game.getProvince(Game.getSeaProvinceInViewID(i)).drawDetails(oSB);
            }
            for (int i = 0; i < Game.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
                Game.getProvince(Game.getSeaProvinceInViewID(i)).drawDetailsSea(oSB);
            }
            for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                Game.getProvince(Game.getExtraProvinceInViewID(i)).drawDetails(oSB);
            }
            for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                Game.getProvince(Game.getProvinceInViewID(i)).drawDetails(oSB);
            }
        }
    }
    
    public static final ProvinceDrawArmyINT updateDrawArmy(final int nProvinceID) {
        if (Game.getProvince(nProvinceID).getFogDrawArmy()) {
            return new ProvinceDrawArmyINT() {
                @Override
                public void drawArmy(final SpriteBatch oSB, final int nProvinceID, final int nArmyID) {
                    if (!Game.getProvince(nProvinceID).getArmy(nArmyID).inBattle) {
                        ProvinceDrawArmy.drawProvinceArmyWithFlag(oSB, nProvinceID, nArmyID);
                    }
                }
            };
        }
        return new ProvinceDrawArmyINT() {
            @Override
            public void drawArmy(final SpriteBatch oSB, final int nProvinceID, final int nArmyID) {
            }
        };
    }
    
    public static final int getDetailsPosX(final int nProvinceID) {
        return (int)((Game.getProvince(nProvinceID).iCenterShiftX + Game.getProvince(nProvinceID).getTranslateProvincePosX()) * Game.mapScale.getCurrentScale()) + Game.getProvince(nProvinceID).drawDetails.iShiftX;
    }
    
    public static final int getDetailsPosX_2(final int nProvinceID) {
        return (int)((Game.getProvince(nProvinceID).iCenterShiftX + Game.getProvince(nProvinceID).getTranslateProvincePosX()) * Game.mapScale.getCurrentScale());
    }
    
    public static final int getDetailsPosY(final int nProvinceID) {
        return (int)((Game.getProvince(nProvinceID).iCenterShiftY + Game.mapCoords.getPosY()) * Game.mapScale.getCurrentScale()) + Game.getProvince(nProvinceID).drawDetails.iShiftY;
    }
    
    public static final int getDetailsPosY_2(final int nProvinceID) {
        return (int)((Game.getProvince(nProvinceID).iCenterShiftY + Game.mapCoords.getPosY()) * Game.mapScale.getCurrentScale());
    }
    
    public static final int getArmyPosX(final int nProvinceID, final int nArmyID) {
        return (int)((Game.getProvince(nProvinceID).iCenterShiftX + Game.getProvince(nProvinceID).getTranslateProvincePosX() + Game.getProvince(nProvinceID).getArmy(nArmyID).iShiftX_Scaled) * Game.mapScale.getCurrentScale()) + Game.getProvince(nProvinceID).getArmy(nArmyID).iShiftX;
    }
    
    public static final int getArmyPosY(final int nProvinceID, final int nArmyID) {
        return (int)((Game.getProvince(nProvinceID).iCenterShiftY + Game.mapCoords.getPosY() + Game.getProvince(nProvinceID).getArmy(nArmyID).iShiftY_Scaled) * Game.mapScale.getCurrentScale()) + Game.getProvince(nProvinceID).getArmy(nArmyID).iShiftY;
    }
    
    public static final int getArmyPosX_Shadow(final int nProvinceID) {
        return (int)((Game.getProvince(nProvinceID).iCenterShiftX + Game.getProvince(nProvinceID).getTranslateProvincePosX()) * Game.mapScale.getCurrentScale());
    }
    
    public static final int getArmyPosY_Shadow(final int nProvinceID) {
        return (int)((Game.getProvince(nProvinceID).iCenterShiftY + Game.mapCoords.getPosY()) * Game.mapScale.getCurrentScale());
    }
    
    public static final int getArmyWidth(final int nArmyWidth) {
        return Images.armyLeft3_Width + nArmyWidth + 10;
    }
    
    public static final int getArmyHeight() {
        return ImageManager.getImage(Images.armyLeft).getHeight();
    }
    
    public static final void drawProvinceArmyWithFlag(final SpriteBatch oSB, final int nProvinceID, final int nArmyID) {
        int nPosX = getArmyPosX(nProvinceID, nArmyID);
        final int nPosY = getArmyPosY(nProvinceID, nArmyID);
        final Province province = Game.getProvince(nProvinceID);
        final ArmyDivision armyDivision = province.getArmy(nArmyID);
        drawProvinceArmyFlag_2(oSB, nPosX, nPosY, armyDivision.civID);
        Images.armyLeft3.get(armyDivision.fMoraleDraw).draw(oSB, nPosX, nPosY);
        nPosX += Images.armyLeft3_Width;
        drawProvinceArmy(oSB, nPosX, nPosY, armyDivision.iArmyWidth + 10, Game.getCiv(armyDivision.civID).iArmyImgID, armyDivision.inRetreat);
        Renderer.drawText(oSB, CFG.FONT_ARMY, armyDivision.sArmy, nPosX + armyDivision.iArmyExtraPosX + 5, nPosY + (int)(ImageManager.getImage(Images.armyLeft).getHeight() / 2.0f) - (int)(ProvinceDrawArmy.ARMY_HEIGHT / 2.0f), ProvinceDrawArmy.drawProvinceArmyTextColor);
    }
    
    private static final void drawProvinceArmy(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final int ImageID, final boolean inRetreat) {
        ImageManager.getImage(ImageID).draw2(oSB, nPosX, nPosY, nWidth, ImageManager.getImage(ImageID).getHeight(), true, false);
        if (inRetreat) {
            ImageManager.getImage(Images.armyRetreat).draw(oSB, nPosX + nWidth, nPosY + ImageManager.getImage(Images.armyLeft).getHeight() / 2 - ImageManager.getImage(Images.armyRetreat).getHeight() / 2);
        }
    }
    
    private static final void drawProvinceArmyFlag(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nCivID, final int ImageID) {
        ImageManager.getImage(ImageID).draw2(oSB, nPosX, nPosY, ImageManager.getImage(Images.armyFlag).getWidth() + 5, ImageManager.getImage(ImageID).getHeight());
        if (nCivID < 0) {
            ImageManager.getImage(Images.rebelsFlag).draw(oSB, nPosX + 3, nPosY + 2, ImageManager.getImage(Images.armyFlag).getWidth(), ImageManager.getImage(Images.armyFlag).getHeight());
        }
        else {
            Game.getCiv(nCivID).getFlag().draw(oSB, nPosX + 3, nPosY + 2, ImageManager.getImage(Images.armyFlag).getWidth(), ImageManager.getImage(Images.armyFlag).getHeight());
        }
        ImageManager.getImage(Images.armyFlag).draw(oSB, nPosX + 3, nPosY + 2);
    }
    
    private static final void drawProvinceArmyFlag_2(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nCivID) {
        if (nCivID < 0) {
            ImageManager.getImage(Images.rebelsFlag).draw(oSB, nPosX + ProvinceDrawArmy.armyFlagPosX, nPosY + ProvinceDrawArmy.armyFlagPosY, ProvinceDrawArmy.armyFlagPosWidth, ProvinceDrawArmy.armyFlagPosHeight);
        }
        else {
            Game.getCiv(nCivID).getFlag().draw(oSB, nPosX + ProvinceDrawArmy.armyFlagPosX, nPosY + ProvinceDrawArmy.armyFlagPosY, ProvinceDrawArmy.armyFlagPosWidth, ProvinceDrawArmy.armyFlagPosHeight);
        }
    }
    
    public static final void drawProvinceArmy_Units(final SpriteBatch oSB, final int nProvinceID, final int nArmyID, final Color armyColor, final float fAlpha) {
        try {
            int nPosX = getArmyPosX(nProvinceID, nArmyID);
            final int nPosY = getArmyPosY(nProvinceID, nArmyID);
            final Province province = Game.getProvince(nProvinceID);
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, fAlpha));
            int uID = province.getArmy(nArmyID).lArmyRegiment.get(0).uID;
            int aID = province.getArmy(nArmyID).lArmyRegiment.get(0).aID;
            int numOfUnits = province.getArmy(nArmyID).lArmyRegiment.get(0).num;
            for (int i = 1; i < province.getArmy(nArmyID).iArmyRegimentSize; ++i) {
                if (uID == province.getArmy(nArmyID).lArmyRegiment.get(i).uID && aID == province.getArmy(nArmyID).lArmyRegiment.get(i).aID) {
                    numOfUnits += province.getArmy(nArmyID).lArmyRegiment.get(i).num;
                }
                else {
                    try {
                        Renderer.clipView_Start(oSB, nPosX, nPosY, ImageManager.getImage(Images.unitsFrameBattle).getWidth(), -ImageManager.getImage(Images.unitsFrameBattle).getHeight());
                        ArmyManager.armyImages.get(ArmyManager.lArmy.get(uID).get(aID).ImageID).draw(oSB, nPosX, nPosY, ImageManager.getImage(Images.unitsFrameBattle).getHeight());
                        Renderer.clipView_End(oSB);
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                    ImageManager.getImage(Images.unitsFrameBattle).draw(oSB, nPosX, nPosY);
                    nPosX += ImageManager.getImage(Images.unitsFrameBattle).getWidth() + CFG.PADDING / 2;
                    uID = province.getArmy(nArmyID).lArmyRegiment.get(i).uID;
                    aID = province.getArmy(nArmyID).lArmyRegiment.get(i).aID;
                    numOfUnits = province.getArmy(nArmyID).lArmyRegiment.get(i).num;
                }
            }
            oSB.setColor(Color.WHITE);
        }
        catch (final Exception ex2) {}
    }
    
    public static final void drawProvinceArmyWithFlag_Shadow(final SpriteBatch oSB, final int nProvinceID, final Color armyColor, final float fAlpha, final String sArmy, final int iArmyWidth, final int extraY) {
        try {
            final int nPosX = getArmyPosX_Shadow(nProvinceID) - (ImageManager.getImage(Images.armyMorale).getWidth() + ImageManager.getImage(Images.armyFlag).getWidth() + 5 + iArmyWidth + 10) / 2;
            final int nPosY = getArmyPosY_Shadow(nProvinceID) + extraY * (getArmyHeight() + 2);
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, fAlpha));
            drawProvinceArmyFlag(oSB, nPosX, nPosY, Game.player.iCivID, Images.armyLeft);
            oSB.setColor(new Color(ProvinceDrawArmy.moraleBG.r, ProvinceDrawArmy.moraleBG.g, ProvinceDrawArmy.moraleBG.b, fAlpha));
            Images.pix.draw2(oSB, nPosX + ImageManager.getImage(Images.armyFlag).getWidth() + 5, nPosY, ImageManager.getImage(Images.armyMorale).getWidth(), ImageManager.getImage(Images.armyMorale).getHeight());
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, fAlpha));
            drawProvinceArmy(oSB, nPosX + ImageManager.getImage(Images.armyMorale).getWidth() + ImageManager.getImage(Images.armyFlag).getWidth() + 5, nPosY, iArmyWidth + 10, Images.army, false);
            Renderer.drawText(oSB, CFG.FONT_ARMY, sArmy, nPosX + ImageManager.getImage(Images.armyMorale).getWidth() + ImageManager.getImage(Images.armyFlag).getWidth() + 5 + 5, nPosY + (int)(ImageManager.getImage(Images.armyLeft).getHeight() / 2.0f) - (int)(ProvinceDrawArmy.ARMY_HEIGHT / 2.0f), new Color(armyColor.r, armyColor.g, armyColor.b, armyColor.a * fAlpha));
            oSB.setColor(Color.WHITE);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void drawProvinceArmyHover(final SpriteBatch oSB, final int nProvinceID, final int nArmyID, final float fAlpha) {
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, Game.animationHover.getAlpha() / 255.0f * fAlpha));
        drawProvinceArmyHover(oSB, getArmyPosX(nProvinceID, nArmyID), getArmyPosY(nProvinceID, nArmyID), getArmyWidth(Game.getProvince(nProvinceID).getArmy(nArmyID).iArmyWidth));
        oSB.setColor(Color.WHITE);
    }
    
    public static final void drawProvinceArmyHover_WithUnitsImages(final SpriteBatch oSB, final int nProvinceID, final int nArmyID, final float fAlpha) {
        final int nPosX = getArmyPosX(nProvinceID, nArmyID);
        final int nPosY = getArmyPosY(nProvinceID, nArmyID);
        final List<Point_XY> tIDs = new ArrayList<Point_XY>();
        for (int i = 0; i < Game.getProvince(nProvinceID).getArmy(nArmyID).iArmyRegimentSize; ++i) {
            boolean add = true;
            for (int j = 0; j < tIDs.size(); ++j) {
                if (tIDs.get(j).getPosX() == Game.getProvince(nProvinceID).getArmy(nArmyID).lArmyRegiment.get(i).uID && tIDs.get(j).getPosY() == Game.getProvince(nProvinceID).getArmy(nArmyID).lArmyRegiment.get(i).aID) {
                    add = false;
                    break;
                }
            }
            if (add) {
                tIDs.add(new Point_XY(Game.getProvince(nProvinceID).getArmy(nArmyID).lArmyRegiment.get(i).uID, Game.getProvince(nProvinceID).getArmy(nArmyID).lArmyRegiment.get(i).aID));
            }
        }
        final int nWidth = getArmyWidth(Game.getProvince(nProvinceID).getArmy(nArmyID).iArmyWidth);
        final int extraWidth = ImageManager.getImage(Images.armyUnitsFrame).getWidth() * tIDs.size();
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, Game.animationHover.getAlpha() / 255.0f * fAlpha));
        drawProvinceArmyHover_Units(oSB, nPosX, nPosY, nWidth + extraWidth);
        oSB.setColor(Color.WHITE);
        drawProvinceArmy(oSB, nPosX - 5 + nWidth, nPosY, ImageManager.getImage(Images.armyUnitsFrame).getWidth(), Images.armyAlly, Game.getProvince(nProvinceID).getArmy(nArmyID).inRetreat);
        for (int j = 0; j < tIDs.size(); ++j) {
            Renderer.clipView_Start(oSB, nPosX + nWidth + ImageManager.getImage(Images.armyUnitsFrame).getWidth() * j, CFG.GAME_HEIGHT - nPosY, ImageManager.getImage(Images.armyUnitsFrame).getWidth(), -ImageManager.getImage(Images.armyUnitsFrame).getHeight());
            final int unitImgWidth = (int)(ArmyManager.armyImages.get(ArmyManager.lArmy.get(tIDs.get(j).getPosX()).get(tIDs.get(j).getPosY()).ImageID).getWidth() * (ImageManager.getImage(Images.armyUnitsFrame).getHeight() / (float)ArmyManager.armyImages.get(ArmyManager.lArmy.get(tIDs.get(j).getPosX()).get(tIDs.get(j).getPosY()).ImageID).getHeight()));
            ArmyManager.armyImages.get(ArmyManager.lArmy.get(tIDs.get(j).getPosX()).get(tIDs.get(j).getPosY()).ImageID).draw(oSB, nPosX + ImageManager.getImage(Images.armyUnitsFrame).getWidth() / 2 - unitImgWidth / 2 + nWidth + ImageManager.getImage(Images.armyUnitsFrame).getWidth() * j, nPosY, unitImgWidth, ImageManager.getImage(Images.armyUnitsFrame).getHeight());
            Renderer.clipView_End(oSB);
            ImageManager.getImage(Images.armyUnitsFrame).draw(oSB, nPosX + nWidth + ImageManager.getImage(Images.armyUnitsFrame).getWidth() * j, nPosY);
        }
    }
    
    public static final void drawProvinceArmyActive(final SpriteBatch oSB, final int nProvinceID, final int nArmyID, final float fAlpha) {
        try {
            if (nArmyID >= 0 && nArmyID < Game.getProvince(nProvinceID).getArmySize()) {
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, Game.animationHover.getAlpha() / 255.0f * fAlpha));
                drawProvinceArmyActive(oSB, getArmyPosX(nProvinceID, nArmyID), getArmyPosY(nProvinceID, nArmyID), getArmyWidth(Game.getProvince(nProvinceID).getArmy(nArmyID).iArmyWidth));
                oSB.setColor(Color.WHITE);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void drawProvinceArmyHover(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth) {
        ImageManager.getImage(Images.armyHover).draw2(oSB, nPosX - 4, nPosY - 4, nWidth + 8 - ImageManager.getImage(Images.armyHover).getWidth(), ImageManager.getImage(Images.armyHover).getHeight());
        ImageManager.getImage(Images.armyHover).draw2(oSB, nPosX + nWidth + 4 - ImageManager.getImage(Images.armyHover).getWidth(), nPosY - 4, ImageManager.getImage(Images.armyHover).getWidth(), ImageManager.getImage(Images.armyHover).getHeight(), true, false);
    }
    
    public static final void drawProvinceArmyHover_Units(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth) {
        ImageManager.getImage(Images.armyHover).draw2(oSB, nPosX - 4, nPosY - 4, nWidth + 8 - ImageManager.getImage(Images.armyHoverRight).getWidth(), ImageManager.getImage(Images.armyHover).getHeight());
        ImageManager.getImage(Images.armyHoverRight).draw2(oSB, nPosX + nWidth + 4 - ImageManager.getImage(Images.armyHoverRight).getWidth(), nPosY - 4, ImageManager.getImage(Images.armyHoverRight).getWidth(), ImageManager.getImage(Images.armyHoverRight).getHeight());
    }
    
    private static final void drawProvinceArmyActive(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth) {
        ImageManager.getImage(Images.armyActive).draw2(oSB, nPosX - 4, nPosY - 4, nWidth + 8 - ImageManager.getImage(Images.armyActive).getWidth(), ImageManager.getImage(Images.armyActive).getHeight());
        ImageManager.getImage(Images.armyActive).draw2(oSB, nPosX + nWidth + 4 - ImageManager.getImage(Images.armyActive).getWidth(), nPosY - 4, ImageManager.getImage(Images.armyActive).getWidth(), ImageManager.getImage(Images.armyActive).getHeight(), true, false);
    }
    
    public static final void updateArmyHeight() {
        final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_ARMY), "0123456789");
        ProvinceDrawArmy.ARMY_HEIGHT = (int)glyphLayout.height;
    }
    
    static {
        COLOR_ARMY = new Color(0.9019608f, 0.88235295f, 0.84313726f, 1.0f);
        COLOR_ARMY2 = new Color(1.0f, 0.9607843f, 0.92156863f, 1.0f);
        COLOR_ARMY_OLD = new Color(0.8627451f, 0.78431374f, 0.27450982f, 1.0f);
        ProvinceDrawArmy.ARMY_HEIGHT = 1;
        ProvinceDrawArmy.drawHideAnimation = false;
        ProvinceDrawArmy.DRAW_ARMY_ALPHA = 0.1f;
        ProvinceDrawArmy.DRAW_ARMY_TIME = 0L;
        ProvinceDrawArmy.DRAW_ARMY_TIME_HIDE = 0L;
        ProvinceDrawArmy.DRAW_ARMY_MIN_SCALE_ANIMATION = 0.3f;
        ProvinceDrawArmy.drawCitiesHideAnimation = false;
        ProvinceDrawArmy.DRAW_CITIES_ALPHA = 0.1f;
        ProvinceDrawArmy.DRAW_CITIES_TIME = 0L;
        ProvinceDrawArmy.DRAW_CITIES_TIME_HIDE = 0L;
        ProvinceDrawArmy.drawProvinceNamesHideAnimation = false;
        ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA = 0.1f;
        ProvinceDrawArmy.DRAW_PROVINCE_NAMES_TIME = 0L;
        ProvinceDrawArmy.DRAW_PROVINCE_NAMES_TIME_HIDE = 0L;
        ProvinceDrawArmy.drawOccupiedHideAnimation = false;
        ProvinceDrawArmy.DRAW_OCCUPIED_ALPHA = 0.1f;
        ProvinceDrawArmy.DRAW_OCCUPIED_TIME = 0L;
        ProvinceDrawArmy.DRAW_OCCUPIED_TIME_HIDE = 0L;
        ProvinceDrawArmy.moraleBG = new Color(0.0627451f, 0.0627451f, 0.101960786f, 1.0f);
        ProvinceDrawArmy.moraleGreen = new Color(0.11764706f, 0.50980395f, 0.101960786f, 1.0f);
        ProvinceDrawArmy.drawProvinceArmyColorAlpha = Color.WHITE;
        ProvinceDrawArmy.drawProvinceArmyTextColor = Color.WHITE;
    }
    
    public static class ProvinceDrawDetailsINT
    {
        public String sText;
        public int iTextWidth;
        public int iShiftX;
        public int iShiftY;
        public int iWidth;
        public int iHeight;
        
        public ProvinceDrawDetailsINT() {
            this.sText = "";
            this.iTextWidth = 0;
            this.iShiftX = 0;
            this.iShiftY = 0;
            this.iWidth = 0;
            this.iHeight = 0;
        }
        
        public void drawDetails(final SpriteBatch oSB, final int nProvinceID) {
        }
        
        public void drawDetailsSea(final SpriteBatch oSB, final int nProvinceID) {
        }
        
        public MenuElement_Hover buildHover(final int nProvinceID) {
            return null;
        }
    }
    
    public interface ProvinceDrawArmyINT
    {
        void drawArmy(final SpriteBatch p0, final int p1, final int p2);
    }
}
