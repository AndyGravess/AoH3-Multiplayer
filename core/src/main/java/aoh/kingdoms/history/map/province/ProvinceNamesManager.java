// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.province;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapProvinceNamePoints;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.Pixmap;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.FBO.FBOProvinceNames;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import aoc.kingdoms.lukasz.jakowski.zOther.Point_XY;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoc.kingdoms.lukasz.jakowski.GlyphLayout_Game;
import aoc.kingdoms.lukasz.map.civilization.CivilizationRegion;
import aoc.kingdoms.lukasz.jakowski.SaveLoad.SaveManager;
import aoc.kingdoms.lukasz.jakowski.CFG;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Vector2;
import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.ArrayList;
import java.util.List;

public class ProvinceNamesManager
{
    public static final int FONT_ID = 0;
    public static int NULL_INDICATOR;
    public static List<ProvinceNameData> provinceNames;
    public static DrawProvinceNames drawProvinceNames;
    
    public static final void buildProvNamePoints(final int i) {
        final ProvinceNameData nameData = new ProvinceNameData();
        float maxWidth = 0.0f;
        final List<Boolean> was = new ArrayList<Boolean>();
        for (int j = 0; j < Game.getProvince(i).getPointsSize(); ++j) {
            was.add(false);
        }
        int checkedWidthNum = 0;
        while (checkedWidthNum < Game.getProvince(i).getPointsSize() - 2 && checkedWidthNum < 299) {
            int iID = 0;
            int jID = 1;
            checkedWidthNum += 2;
            maxWidth = 0.0f;
            for (int k = 0; k < Game.getProvince(i).getPointsSize() - 1; ++k) {
                if (!was.get(k)) {
                    for (int l = k + 1; l < Game.getProvince(i).getPointsSize(); ++l) {
                        if (!was.get(l)) {
                            final float tWidth = (float)Math.ceil(Math.sqrt((Game.getProvince(i).getPointsX(l) - Game.getProvince(i).getPointsX(k)) * (Game.getProvince(i).getPointsX(l) - Game.getProvince(i).getPointsX(k)) + (Game.getProvince(i).getPointsY(k) - Game.getProvince(i).getPointsY(l)) * (Game.getProvince(i).getPointsY(k) - Game.getProvince(i).getPointsY(l))));
                            if (tWidth > maxWidth) {
                                maxWidth = tWidth;
                                nameData.fX = (float)Game.getProvince(i).getPointsX(k);
                                nameData.fX2 = (float)Game.getProvince(i).getPointsX(l);
                                nameData.fY = (float)Game.getProvince(i).getPointsY(k);
                                nameData.fY2 = (float)Game.getProvince(i).getPointsY(l);
                                nameData.fCenterX = (float)Game.getProvince(i).getCenterX();
                                nameData.fCenterY = (float)Game.getProvince(i).getCenterY();
                                iID = k;
                                jID = l;
                            }
                        }
                    }
                }
            }
            was.set(iID, true);
            was.set(jID, true);
            if (nameData.fX2 < nameData.fX) {
                float tSw = nameData.fX;
                nameData.fX = nameData.fX2;
                nameData.fX2 = tSw;
                tSw = nameData.fY;
                nameData.fY = nameData.fY2;
                nameData.fY2 = tSw;
            }
            final float tfX = nameData.fX + (Game.getProvince(i).getCenterX() - nameData.fX) * 0.4f;
            final float tfY = nameData.fY + (Game.getProvince(i).getCenterY() - nameData.fY) * 0.4f;
            final float tfX2 = nameData.fX2 + (Game.getProvince(i).getCenterX() - nameData.fX2) * 0.4f;
            final float tfY2 = nameData.fY2 + (Game.getProvince(i).getCenterY() - nameData.fY2) * 0.4f;
            final int iPrecision = Game.getProvince(i).getProvinceName().length() * 4;
            final Vector2[] vPoints = new Vector2[iPrecision];
            final Vector2[] dataSet = { new Vector2(tfX, tfY), new Vector2(tfX, tfY), new Vector2((float)Game.getProvince(i).getCenterX(), (float)Game.getProvince(i).getCenterY()), new Vector2(tfX2, tfY2), new Vector2(tfX2, tfY2) };
            boolean isInProvince = true;
            final CatmullRomSpline<Vector2> oCatmull = (CatmullRomSpline<Vector2>)new CatmullRomSpline((Vector[])dataSet, false);
            for (int m = 0; m < iPrecision; ++m) {
                oCatmull.valueAt((Vector)(vPoints[m] = new Vector2()), m / (iPrecision - 1.0f));
            }
            for (int m = vPoints.length - 1; m >= 0; --m) {
                if (Game.setProvinceID_Point((int)vPoints[m].x, (int)vPoints[m].y) != i || Game.setProvinceID_Point((int)vPoints[m].x + CFG.PADDING, (int)vPoints[m].y) != i || Game.setProvinceID_Point((int)vPoints[m].x - CFG.PADDING, (int)vPoints[m].y) != i || Game.setProvinceID_Point((int)vPoints[m].x, (int)vPoints[m].y + CFG.PADDING) != i || Game.setProvinceID_Point((int)vPoints[m].x, (int)vPoints[m].y - CFG.PADDING) != i) {
                    isInProvince = false;
                    break;
                }
            }
            if (isInProvince) {
                checkedWidthNum = -1;
                break;
            }
        }
        if (checkedWidthNum > 0) {
            ProvinceNamesManager.provinceNames.add(null);
        }
        else {
            ProvinceNamesManager.provinceNames.add(nameData);
        }
    }
    
    public static final void buildProvNameData() {
        boolean saveData = false;
        int i = 0;
        int iPNamesSize = ProvinceNamesManager.provinceNames.size();
        while (i < Game.getProvincesSize()) {
            if (iPNamesSize <= i) {
                buildProvNamePoints(i);
                iPNamesSize = ProvinceNamesManager.provinceNames.size();
                saveData = true;
            }
            buildProvNameData(i, false);
            ++i;
        }
        if (saveData) {
            SaveManager.saveProvinceNamesPoints();
        }
    }
    
    public static void clearProvNameData(final int i) {
        if (ProvinceNamesManager.provinceNames.get(i) != null) {
            ProvinceNamesManager.provinceNames.get(i).drawPoints.clear();
            ProvinceNamesManager.provinceNames.get(i).drawMatrix4.clear();
            ProvinceNamesManager.provinceNames.get(i).fontScale = 1.0f;
            ProvinceNamesManager.provinceNames.get(i).drawAngleLow = 0.0f;
        }
    }
    
    public static final void buildProvNameData(final int i, final boolean rebuild) {
        if (ProvinceNamesManager.provinceNames.get(i) != null) {
            if (rebuild) {
                ProvinceNamesManager.provinceNames.get(i).drawPoints.clear();
                ProvinceNamesManager.provinceNames.get(i).drawMatrix4.clear();
                ProvinceNamesManager.provinceNames.get(i).fontScale = 1.0f;
                ProvinceNamesManager.provinceNames.get(0).drawAngleLow = 0.0f;
            }
            final float maxWidth = CivilizationRegion.getLineWidth3(ProvinceNamesManager.provinceNames.get(i).fX, ProvinceNamesManager.provinceNames.get(i).fY, ProvinceNamesManager.provinceNames.get(i).fX2, ProvinceNamesManager.provinceNames.get(i).fY2);
            final float tfX = ProvinceNamesManager.provinceNames.get(i).fX + (ProvinceNamesManager.provinceNames.get(i).fCenterX - ProvinceNamesManager.provinceNames.get(i).fX) * 0.4f;
            final float tfY = ProvinceNamesManager.provinceNames.get(i).fY + (ProvinceNamesManager.provinceNames.get(i).fCenterY - ProvinceNamesManager.provinceNames.get(i).fY) * 0.4f;
            final float tfX2 = ProvinceNamesManager.provinceNames.get(i).fX2 + (ProvinceNamesManager.provinceNames.get(i).fCenterX - ProvinceNamesManager.provinceNames.get(i).fX2) * 0.4f;
            final float tfY2 = ProvinceNamesManager.provinceNames.get(i).fY2 + (ProvinceNamesManager.provinceNames.get(i).fCenterY - ProvinceNamesManager.provinceNames.get(i).fY2) * 0.4f;
            final int iPrecision = Game.getProvince(i).getProvinceName().length() * 8;
            final Vector2[] vPoints = new Vector2[iPrecision];
            final Vector2[] dataSet = { new Vector2(tfX, tfY), new Vector2(tfX, tfY), new Vector2(ProvinceNamesManager.provinceNames.get(i).fCenterX, ProvinceNamesManager.provinceNames.get(i).fCenterY), new Vector2(tfX2, tfY2), new Vector2(tfX2, tfY2) };
            final CatmullRomSpline<Vector2> oCatmull = (CatmullRomSpline<Vector2>)new CatmullRomSpline((Vector[])dataSet, false);
            for (int j = 0; j < iPrecision; ++j) {
                oCatmull.valueAt((Vector)(vPoints[j] = new Vector2()), j / (iPrecision - 1.0f));
            }
            float tempPrecisionWidth = 0.0f;
            for (int k = 0; k < iPrecision - 1; ++k) {
                tempPrecisionWidth += CivilizationRegion.getLineWidth3(vPoints[k].x, vPoints[k].y, vPoints[k + 1].x, vPoints[k + 1].y);
            }
            float acceptableWidth = 0.0f;
            try {
                acceptableWidth = tempPrecisionWidth / (Game.getProvince(i).getProvinceName().length() - 1);
            }
            catch (final ArithmeticException ex) {
                CFG.exceptionStack(ex);
            }
            final List<Vector2> tempPoints = new ArrayList<Vector2>();
            tempPoints.add(new Vector2(vPoints[0].x, vPoints[0].y));
            float currentPointsWidth = 0.0f;
            int l = 1;
            int startPrecision = 0;
            while (l < Game.getProvince(i).getProvinceName().length()) {
                while (startPrecision < iPrecision - 1) {
                    final float tempPrecisionWidth2 = CivilizationRegion.getLineWidth3(vPoints[startPrecision].x, vPoints[startPrecision].y, vPoints[startPrecision + 1].x, vPoints[startPrecision + 1].y);
                    if (currentPointsWidth + tempPrecisionWidth2 >= acceptableWidth) {
                        tempPoints.add(new Vector2(vPoints[startPrecision].x, vPoints[startPrecision].y));
                        currentPointsWidth = acceptableWidth - (currentPointsWidth + tempPrecisionWidth2);
                        break;
                    }
                    currentPointsWidth += tempPrecisionWidth2;
                    ++startPrecision;
                }
                ++l;
            }
            tempPoints.add(new Vector2(vPoints[vPoints.length - 1].x, vPoints[vPoints.length - 1].y));
            final List<Float> lPointsAngle = new ArrayList<Float>();
            final float fAngle = (float)(Math.atan2(tempPoints.get(0).y - tempPoints.get(1).y, -tempPoints.get(0).x + tempPoints.get(1).y) * 180.0 / 3.141592653589793);
            for (int m = 0, jSize = Math.min(tempPoints.size(), Game.getProvince(i).getProvinceName().length()); m < jSize; ++m) {
                try {
                    float tempPointsAngle;
                    if (m < Game.getProvince(i).getProvinceName().length() - 1) {
                        tempPointsAngle = CivilizationRegion.getLinesAngle2(tempPoints.get(m).x, tempPoints.get(m).y, tempPoints.get(m + 1).x, tempPoints.get(m + 1).y);
                    }
                    else {
                        tempPointsAngle = CivilizationRegion.getLinesAngle2(tempPoints.get(m - 1).x, tempPoints.get(m - 1).y, tempPoints.get(m).x, tempPoints.get(m).y);
                    }
                    lPointsAngle.add(tempPointsAngle);
                }
                catch (final Exception ex2) {
                    if (m == 0) {
                        try {
                            lPointsAngle.add(CivilizationRegion.getLinesAngle2(tempPoints.get(m).x, tempPoints.get(m).y, tempPoints.get(m + 1).x, tempPoints.get(m + 1).y));
                        }
                        catch (final IndexOutOfBoundsException e) {
                            lPointsAngle.add(fAngle);
                        }
                    }
                    else {
                        try {
                            lPointsAngle.add(CivilizationRegion.getLinesAngle2(tempPoints.get(m - 1).x, tempPoints.get(m - 1).y, tempPoints.get(m).x, tempPoints.get(m).y));
                        }
                        catch (final IndexOutOfBoundsException e) {
                            lPointsAngle.add(fAngle);
                        }
                    }
                }
            }
            final float iDistance = maxWidth * 0.8f;
            final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
            synchronized (ProvinceNamesManager.class) {
                Renderer.fontBorder.get(0).getData().setScale(1.0f);
                glyphLayout.setText(Renderer.fontBorder.get(0), Game.getProvince(i).getProvinceNameUpperCase());
                int tempNumOfIterations = 0;
                float tempScale = Math.max(0.1f, iDistance / glyphLayout.width);
                if (glyphLayout.width > 0.1f) {
                    Renderer.fontBorder.get(0).getData().setScale(tempScale);
                }
                try {
                    Label_1741: {
                        if (iDistance > 0.0f) {
                            do {
                                if (iDistance > glyphLayout.width) {
                                    tempScale += 0.025f;
                                    Renderer.fontBorder.get(0).getData().setScale(Math.max(0.001f, tempScale));
                                    glyphLayout.setText(Renderer.fontBorder.get(0), Game.getProvince(i).getProvinceNameUpperCase());
                                    if (iDistance < glyphLayout.width) {
                                        ProvinceNamesManager.provinceNames.get(i).fontScale = Math.max(1.0E-4f, tempScale - 0.0125f);
                                        break Label_1741;
                                    }
                                    continue;
                                }
                                else {
                                    tempScale -= 0.025f;
                                    Renderer.fontBorder.get(0).getData().setScale(Math.max(0.001f, tempScale));
                                    glyphLayout.setText(Renderer.fontBorder.get(0), Game.getProvince(i).getProvinceNameUpperCase());
                                    if (iDistance > glyphLayout.width) {
                                        ProvinceNamesManager.provinceNames.get(i).fontScale = Math.max(1.0E-4f, tempScale + 0.0125f);
                                        break Label_1741;
                                    }
                                    continue;
                                }
                            } while (tempNumOfIterations++ <= 999);
                            CFG.LOG("ProvinceNamesManager: tempNumOfIterations: " + tempNumOfIterations);
                            ProvinceNamesManager.provinceNames.get(i).fontScale = 1.0E-4f;
                        }
                    }
                }
                catch (final Exception ex3) {
                    ProvinceNamesManager.provinceNames.get(i).fontScale = 1.0E-4f;
                }
            }
            for (int j2 = 0, jSize2 = tempPoints.size(); j2 < jSize2; ++j2) {
                ProvinceNamesManager.provinceNames.get(i).drawPoints.add(new Point_XY((int)tempPoints.get(j2).x, (int)tempPoints.get(j2).y));
            }
            for (int j2 = 0, jSize2 = lPointsAngle.size(); j2 < jSize2; ++j2) {
                ProvinceNamesManager.provinceNames.get(i).drawMatrix4.add(new Matrix4().rotate(Renderer.textRotatedVector3, (float)lPointsAngle.get(j2)));
            }
            ProvinceNamesManager.provinceNames.get(i).drawAngleLow = CivilizationRegion.getLinesAngle2(tempPoints.get(0).x, tempPoints.get(0).y, tempPoints.get(tempPoints.size() - 1).x, tempPoints.get(tempPoints.size() - 1).y);
            for (int a = ProvinceNamesManager.provinceNames.get(i).drawMatrix4.size(); a < Game.getProvince(i).getProvinceNameUpperCase().length(); ++a) {
                ProvinceNamesManager.provinceNames.get(i).drawMatrix4.add(new Matrix4().rotate(Renderer.textRotatedVector3, 0.0f));
            }
            ProvinceNamesManager.provinceNames.get(i).drawMatrix4.add(new Matrix4().rotate(Renderer.textRotatedVector3, ProvinceNamesManager.provinceNames.get(i).drawAngleLow));
        }
    }
    
    public static final void updateDrawProvinceNames() {
        if (Game.settingsManager.SETTINGS_PROVINCE_NAMES > 1) {
            if (Game.settingsManager.SETTINGS_PROVINCE_NAMES == 2) {
                ProvinceNamesManager.drawProvinceNames = new DrawProvinceNames() {
                    @Override
                    public void drawProvNames(final SpriteBatch oSB) {
                        if (Game.mapScale.getCurrentScale() >= Game.DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE) {
                            if (Game.settingsManager.FBO_PROVINCE_NAMES) {
                                ProvinceNamesManager.drawProvNames_Just_Medium(oSB);
                            }
                            else {
                                ProvinceNamesManager.drawProvNames_Just_Medium_Default(oSB);
                            }
                        }
                        else if (ProvinceDrawArmy.drawCitiesHideAnimation) {
                            ProvinceNamesManager.drawProvNames_Just_Medium_Default(oSB);
                        }
                    }
                };
            }
            else {
                ProvinceNamesManager.drawProvinceNames = new DrawProvinceNames() {
                    @Override
                    public void drawProvNames(final SpriteBatch oSB) {
                        if (Game.mapScale.getCurrentScale() >= Game.DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE) {
                            if (Game.settingsManager.FBO_PROVINCE_NAMES) {
                                ProvinceNamesManager.drawProvNames_Just(oSB);
                            }
                            else {
                                ProvinceNamesManager.drawProvNames_Just_Default(oSB);
                            }
                        }
                        else if (ProvinceDrawArmy.drawCitiesHideAnimation) {
                            ProvinceNamesManager.drawProvNames_Just_Default(oSB);
                        }
                    }
                };
            }
        }
        else {
            ProvinceNamesManager.drawProvinceNames = new DrawProvinceNames() {
                @Override
                public void drawProvNames(final SpriteBatch oSB) {
                }
            };
        }
    }
    
    public static final void drawProvNames(final SpriteBatch oSB) {
        try {
            ProvinceNamesManager.drawProvinceNames.drawProvNames(oSB);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final synchronized void drawProvNames_Just_Default(final SpriteBatch oSB) {
        final Matrix4 oldTransformMatrix = oSB.getTransformMatrix().cpy();
        Renderer.fontBorder.get(0).setColor(new Color(1.0f, 1.0f, 1.0f, Game.settingsManager.PROVINCE_NAMES_ALPHA * ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA));
        drawProvNames_Just_Default_Inner(oSB);
        oSB.setTransformMatrix(oldTransformMatrix);
    }
    
    public static final void drawProvNames_Just_Default_Inner(final SpriteBatch oSB) {
        try {
            for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                drawProvName(oSB, Game.getProvinceInViewID(i), 0);
            }
            for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                drawProvName(oSB, Game.getExtraProvinceInViewID(i), Game.mapBG.getWidth());
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final synchronized void drawProvNames_Just(final SpriteBatch oSB) {
        try {
            if (FBOProvinceNames.textureProvince_Names != null) {
                if (FBOProvinceNames.lastPosX == Game.mapCoords.getPosX() && FBOProvinceNames.lastPosY == Game.mapCoords.getPosY()) {
                    FBOProvinceNames.drawProvinceNames(oSB);
                }
                else {
                    FBOProvinceNames.disposeProvinceNamesTexture();
                    FBOProvinceNames.disposeProvinceNamesFBO();
                    drawProvNames_Just_Default(oSB);
                }
            }
            else {
                FBOProvinceNames.updateFBO();
                if (FBOProvinceNames.fboNumToGenerate_Names >= GameValues.value.FBO_NUM_TO_GENERATE_NAMES) {
                    FBOProvinceNames.fboNumToGenerate_Names = 0;
                    oSB.end();
                    FBOProvinceNames.disposeProvinceNamesFBO();
                    FBOProvinceNames.disposeProvinceNamesTexture();
                    (FBOProvinceNames.fboProvince_Names = new FrameBuffer(Pixmap.Format.RGBA8888, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, false)).begin();
                    oSB.begin();
                    final Matrix4 oldTransformMatrix = oSB.getTransformMatrix().cpy();
                    Renderer.fontBorder.get(0).setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
                    drawProvNames_Just_Default_Inner(oSB);
                    oSB.setTransformMatrix(oldTransformMatrix);
                    oSB.end();
                    FBOProvinceNames.fboProvince_Names.end();
                    FBOProvinceNames.textureProvince_Names = (Texture)FBOProvinceNames.fboProvince_Names.getColorBufferTexture();
                    FBOProvinceNames.fboPosX = Game.mapCoords.getPosX();
                    FBOProvinceNames.fboPosY = Game.mapCoords.getPosY();
                    oSB.begin();
                    if (FBOProvinceNames.textureProvince_Names != null) {
                        FBOProvinceNames.drawProvinceNames(oSB);
                    }
                }
                else {
                    drawProvNames_Just_Default(oSB);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final synchronized void drawProvNames_Just_Medium_Default(final SpriteBatch oSB) {
        final Matrix4 oldTransformMatrix = oSB.getTransformMatrix().cpy();
        Renderer.fontBorder.get(0).setColor(new Color(1.0f, 1.0f, 1.0f, Game.settingsManager.PROVINCE_NAMES_ALPHA * ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA));
        drawProvNames_Just_Medium_Default_Inner(oSB);
        oSB.setTransformMatrix(oldTransformMatrix);
    }
    
    public static final void drawProvNames_Just_Medium_Default_Inner(final SpriteBatch oSB) {
        try {
            for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                drawProvName_Medium(oSB, Game.getProvinceInViewID(i), 0);
            }
            for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                drawProvName_Medium(oSB, Game.getExtraProvinceInViewID(i), Game.mapBG.getWidth());
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final synchronized void drawProvNames_Just_Medium(final SpriteBatch oSB) {
        try {
            if (FBOProvinceNames.textureProvince_Names != null) {
                if (FBOProvinceNames.lastPosX == Game.mapCoords.getPosX() && FBOProvinceNames.lastPosY == Game.mapCoords.getPosY()) {
                    FBOProvinceNames.drawProvinceNames(oSB);
                }
                else {
                    FBOProvinceNames.disposeProvinceNamesTexture();
                    FBOProvinceNames.disposeProvinceNamesFBO();
                    drawProvNames_Just_Medium_Default(oSB);
                }
            }
            else {
                FBOProvinceNames.updateFBO();
                if (FBOProvinceNames.fboNumToGenerate_Names >= GameValues.value.FBO_NUM_TO_GENERATE_NAMES) {
                    FBOProvinceNames.fboNumToGenerate_Names = 0;
                    oSB.end();
                    FBOProvinceNames.disposeProvinceNamesFBO();
                    FBOProvinceNames.disposeProvinceNamesTexture();
                    (FBOProvinceNames.fboProvince_Names = new FrameBuffer(Pixmap.Format.RGBA8888, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, false)).begin();
                    oSB.begin();
                    final Matrix4 oldTransformMatrix = oSB.getTransformMatrix().cpy();
                    Renderer.fontBorder.get(0).setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
                    drawProvNames_Just_Medium_Default_Inner(oSB);
                    oSB.setTransformMatrix(oldTransformMatrix);
                    oSB.end();
                    FBOProvinceNames.fboProvince_Names.end();
                    FBOProvinceNames.textureProvince_Names = (Texture)FBOProvinceNames.fboProvince_Names.getColorBufferTexture();
                    FBOProvinceNames.fboPosX = Game.mapCoords.getPosX();
                    FBOProvinceNames.fboPosY = Game.mapCoords.getPosY();
                    oSB.begin();
                    if (FBOProvinceNames.textureProvince_Names != null) {
                        FBOProvinceNames.drawProvinceNames(oSB);
                    }
                }
                else {
                    drawProvNames_Just_Medium_Default(oSB);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final synchronized void drawProvName_Old(final SpriteBatch oSB, final int i, int extraX) {
        try {
            if (!Game.getProvince(i).getSeaProvince() && ProvinceNamesManager.provinceNames.get(i) != null) {
                Renderer.fontBorder.get(0).getData().setScale(ProvinceNamesManager.provinceNames.get(i).fontScale * Game.mapScale.getCurrentScale());
                extraX += Game.getProvince(i).getTranslateProvincePosX();
                for (int j = Game.getProvince(i).iProvinceNameLength_Minus1; j >= 0; --j) {
                    try {
                        Renderer.drawTextRotatedBorder(oSB, 0, "" + Game.getProvince(i).getProvinceNameUpperCase().charAt(j), (int)((extraX + ProvinceNamesManager.provinceNames.get(i).drawPoints.get(j).getPosX()) * Game.mapScale.getCurrentScale()), (int)((Game.mapCoords.getPosY() + ProvinceNamesManager.provinceNames.get(i).drawPoints.get(j).getPosY()) * Game.mapScale.getCurrentScale()), ProvinceNamesManager.provinceNames.get(i).drawMatrix4.get(j));
                    }
                    catch (final Exception ex) {}
                }
            }
        }
        catch (final Exception ex2) {}
    }
    
    public static final synchronized void drawProvName(final SpriteBatch oSB, final int i, int extraX) {
        if (ProvinceNamesManager.provinceNames.get(i) == null) {
            return;
        }
        try {
            final ProvinceNameData provinceName = ProvinceNamesManager.provinceNames.get(i);
            final float fontScale = provinceName.fontScale * Game.mapScale.getCurrentScale();
            if (fontScale > Game.settingsManager.PROVINCE_NAMES_SCALE) {
                Renderer.fontBorder.get(0).getData().setScale(fontScale);
                extraX += Game.getProvince(i).getTranslateProvincePosX();
                for (int j = Game.getProvince(i).iProvinceNameLength_Minus1; j >= 0; --j) {
                    Renderer.drawTextRotatedBorder(oSB, 0, String.valueOf(Game.getProvince(i).getProvinceNameUpperCase().charAt(j)), (int)((extraX + provinceName.drawPoints.get(j).getPosX()) * Game.mapScale.getCurrentScale()), (int)((Game.mapCoords.getPosY() + provinceName.drawPoints.get(j).getPosY()) * Game.mapScale.getCurrentScale()), provinceName.drawMatrix4.get(j));
                }
            }
        }
        catch (final Exception ex) {}
    }
    
    public static final synchronized void drawProvName_Medium(final SpriteBatch oSB, final int i, final int extraX) {
        if (ProvinceNamesManager.provinceNames.get(i) == null) {
            return;
        }
        final float fontScale = ProvinceNamesManager.provinceNames.get(i).fontScale * Game.mapScale.getCurrentScale();
        if (fontScale > Game.settingsManager.PROVINCE_NAMES_SCALE) {
            Renderer.fontBorder.get(0).getData().setScale(fontScale);
            Renderer.drawTextRotatedBorder_2(oSB, 0, "" + Game.getProvince(i).getProvinceNameUpperCase(), (int)((Game.getProvince(i).getTranslateProvincePosX() + extraX + ProvinceNamesManager.provinceNames.get(i).drawPoints.get(0).getPosX()) * Game.mapScale.getCurrentScale()), (int)((Game.mapCoords.getPosY() + ProvinceNamesManager.provinceNames.get(i).drawPoints.get(0).getPosY()) * Game.mapScale.getCurrentScale()), ProvinceNamesManager.provinceNames.get(i).drawAngleLow);
        }
    }
    
    public static final void drawProvNamePoints(final SpriteBatch oSB, final int i) {
        if (ProvinceNamesManager.provinceNames.get(i) != null) {
            if (EditorMapProvinceNamePoints.firstPoint && !EditorMapProvinceNamePoints.centerPoint) {
                oSB.setColor(Color.RED);
            }
            Images.pix.draw(oSB, (int)(Game.mapCoords.getPosX() + ProvinceNamesManager.provinceNames.get(i).fX) - 1, (int)(Game.mapCoords.getPosY() + ProvinceNamesManager.provinceNames.get(i).fY) - 1, 3, 3);
            oSB.setColor(Color.WHITE);
            if (EditorMapProvinceNamePoints.centerPoint) {
                oSB.setColor(Color.RED);
            }
            Images.pix.draw(oSB, (int)(Game.mapCoords.getPosX() + ProvinceNamesManager.provinceNames.get(i).fCenterX) - 1, (int)(Game.mapCoords.getPosY() + ProvinceNamesManager.provinceNames.get(i).fCenterY) - 1, 3, 3);
            oSB.setColor(Color.WHITE);
            if (!EditorMapProvinceNamePoints.firstPoint && !EditorMapProvinceNamePoints.centerPoint) {
                oSB.setColor(Color.RED);
            }
            Images.pix.draw(oSB, (int)(Game.mapCoords.getPosX() + ProvinceNamesManager.provinceNames.get(i).fX2) - 1, (int)(Game.mapCoords.getPosY() + ProvinceNamesManager.provinceNames.get(i).fY2) - 1, 3, 3);
            oSB.setColor(Color.WHITE);
        }
    }
    
    static {
        ProvinceNamesManager.NULL_INDICATOR = 666;
        ProvinceNamesManager.provinceNames = new ArrayList<ProvinceNameData>();
        ProvinceNamesManager.drawProvinceNames = new DrawProvinceNames() {
            @Override
            public void drawProvNames(final SpriteBatch oSB) {
            }
        };
    }
    
    public interface DrawProvinceNames
    {
        void drawProvNames(final SpriteBatch p0);
    }
}
