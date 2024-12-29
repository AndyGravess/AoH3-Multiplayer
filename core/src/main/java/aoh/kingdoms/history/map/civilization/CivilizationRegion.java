// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.civilization;

import aoc.kingdoms.lukasz.jakowski.GlyphLayout_Game;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Vector2;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoc.kingdoms.lukasz.jakowski.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import com.badlogic.gdx.math.Matrix4;
import aoc.kingdoms.lukasz.jakowski.zOther.Point_XY;
import java.util.List;

public class CivilizationRegion
{
    private int iRegionID;
    private List<Integer> lProvinces;
    private int iProvincesSize;
    protected List<Integer> lCoastlineProvinces;
    private List<Integer> shortestLine;
    private int iMinX;
    private int iMaxX;
    private int iMinY;
    private int iMaxY;
    public int iAveragePointPosX;
    public int iAveragePointPosY;
    private float fontScale;
    private float fontScale2;
    private float fAngle;
    private float fAngle_Low;
    private int iCharMaxWidth;
    private int iCharMaxHeight;
    public List<Point_XY> lPoints;
    public List<Matrix4> drawMatrix4;
    public Point_XY centerCharXY;
    protected boolean drawName;
    private List<Boolean> triedToUse;
    private int numOfTries;
    
    public CivilizationRegion() {
        this.lProvinces = new ArrayList<Integer>();
        this.lCoastlineProvinces = new ArrayList<Integer>();
        this.shortestLine = new ArrayList<Integer>();
        this.iMinX = 0;
        this.iMaxX = 0;
        this.iMinY = 0;
        this.iMaxY = 0;
        this.iAveragePointPosX = 0;
        this.iAveragePointPosY = 0;
        this.fontScale = 1.0f;
        this.fontScale2 = 1.0f;
        this.fAngle = 0.0f;
        this.fAngle_Low = 0.0f;
        this.iCharMaxWidth = 0;
        this.iCharMaxHeight = 0;
        this.lPoints = new ArrayList<Point_XY>();
        this.drawMatrix4 = new ArrayList<Matrix4>();
        this.drawName = true;
        this.triedToUse = new ArrayList<Boolean>();
        this.numOfTries = 0;
    }
    
    public CivilizationRegion(final int nProvinceID, final int iRegionID) {
        this.lProvinces = new ArrayList<Integer>();
        this.lCoastlineProvinces = new ArrayList<Integer>();
        this.shortestLine = new ArrayList<Integer>();
        this.iMinX = 0;
        this.iMaxX = 0;
        this.iMinY = 0;
        this.iMaxY = 0;
        this.iAveragePointPosX = 0;
        this.iAveragePointPosY = 0;
        this.fontScale = 1.0f;
        this.fontScale2 = 1.0f;
        this.fAngle = 0.0f;
        this.fAngle_Low = 0.0f;
        this.iCharMaxWidth = 0;
        this.iCharMaxHeight = 0;
        this.lPoints = new ArrayList<Point_XY>();
        this.drawMatrix4 = new ArrayList<Matrix4>();
        this.drawName = true;
        this.triedToUse = new ArrayList<Boolean>();
        this.numOfTries = 0;
        this.iRegionID = iRegionID;
        this.addProvince(nProvinceID);
    }
    
    public final void drawCivRegion(final SpriteBatch oSB) {
        if (this.drawName && this.getFontScale() * Game.mapScale.getCurrentScale() > Game.settingsManager.CIV_NAMES_MIN_SCALE_OF_FONT) {
            final List<Integer> shortestPath = this.getShortestPath();
            if (shortestPath.size() > 1) {
                if (Game.mapCoords.getSecondSideOfMap()) {
                    this.drawCivilizationName(oSB, 0, this.getProvince(shortestPath.get(0)), this.getFontScale());
                    this.drawCivilizationName_SecondSideOfMap(oSB, 0, this.getProvince(shortestPath.get(0)), this.getFontScale());
                }
                else {
                    this.drawCivilizationName(oSB, 0, this.getProvince(shortestPath.get(0)), this.getFontScale());
                }
            }
        }
    }
    
    public final void drawCivRegion_Low(final SpriteBatch oSB) {
        if (this.drawName && this.getFontScale() * Game.mapScale.getCurrentScale() > Game.settingsManager.CIV_NAMES_MIN_SCALE_OF_FONT) {
            final List<Integer> shortestPath = this.getShortestPath();
            if (shortestPath.size() > 1) {
                if (Game.mapCoords.getSecondSideOfMap()) {
                    this.drawCivilizationName_Low(oSB, 0, this.getProvince(shortestPath.get(0)), this.fontScale2);
                }
                else {
                    this.drawCivilizationName_Low(oSB, 0, this.getProvince(shortestPath.get(0)), this.fontScale2);
                }
            }
        }
    }
    
    public final synchronized void drawCivilizationName(final SpriteBatch oSB, final int nFontID, final int fromProvinceID, final float fontScale) {
        Renderer.fontBorder.get(nFontID).getData().setScale(fontScale);
        for (int i = 0; i < Game.getCiv(Game.getProvince(fromProvinceID).getCivID()).getCivNameLength(); ++i) {
            Renderer.drawTextRotatedBorder(oSB, nFontID, Game.getCiv(Game.getProvince(fromProvinceID).getCivID()).getCivNameCharacter(i), Game.mapCoords.getPosX() + this.lPoints.get(i).getPosX() - this.centerCharXY.getPosX(), Game.mapCoords.getPosY() + this.lPoints.get(i).getPosY() - this.centerCharXY.getPosY(), this.drawMatrix4.get(i));
        }
    }
    
    protected final synchronized void drawCivilizationName_SecondSideOfMap(final SpriteBatch oSB, final int nFontID, final int fromProvinceID, final float fontScale) {
        if (Game.getProvince(fromProvinceID).getTranslateProvincePosX() > 0) {
            Renderer.fontBorder.get(nFontID).getData().setScale(fontScale);
            for (int i = 0; i < Game.getCiv(Game.getProvince(fromProvinceID).getCivID()).getCivNameLength(); ++i) {
                Renderer.drawTextRotatedBorder(oSB, nFontID, Game.getCiv(Game.getProvince(fromProvinceID).getCivID()).getCivNameCharacter(i), Game.mapCoords.getSecondSideOfMap_MoveX() + Game.mapCoords.getPosX() + this.lPoints.get(i).getPosX() - this.centerCharXY.getPosX(), Game.mapCoords.getPosY() + this.lPoints.get(i).getPosY() - this.centerCharXY.getPosY(), this.drawMatrix4.get(i));
            }
        }
    }
    
    public final synchronized void drawCivilizationName_Low(final SpriteBatch oSB, final int nFontID, final int fromProvinceID, final float fontScale) {
        Renderer.fontBorder.get(nFontID).getData().setScale(fontScale);
        Renderer.drawTextRotatedBorder_2(oSB, nFontID, Game.getCiv(Game.getProvince(fromProvinceID).getCivID()).sCivName_UpperCase, Game.mapCoords.getPosX() + this.lPoints.get(0).getPosX() - this.centerCharXY.getPosX(), Game.mapCoords.getPosY() + this.lPoints.get(0).getPosY() - this.centerCharXY.getPosY(), this.fAngle_Low);
    }
    
    public final void addProvince(final int nProvinceID) {
        this.lProvinces.add(nProvinceID);
        this.iProvincesSize = this.lProvinces.size();
        for (int i = 0; i < Game.getProvince(nProvinceID).getNeighboringSeaProvincesSize(); ++i) {
            if (Game.getProvince(Game.getProvince(nProvinceID).getNeighboringSeaProvinces(i)).getLevelOfPort() == -2) {
                this.lCoastlineProvinces.add(nProvinceID);
                break;
            }
        }
        Game.getProvince(nProvinceID).setCivRegionID(this.iRegionID);
    }
    
    public final void addProvince_Just(final int nProvinceID) {
        this.lProvinces.add(nProvinceID);
        this.iProvincesSize = this.lProvinces.size();
    }
    
    public final void removeProvinceID(final int nProvinceID) {
        for (int i = 0; i < this.iProvincesSize; ++i) {
            if (this.lProvinces.get(i) == nProvinceID) {
                this.lProvinces.remove(i);
                this.iProvincesSize = this.lProvinces.size();
                for (int j = 0; j < this.lCoastlineProvinces.size(); ++j) {
                    if (this.lCoastlineProvinces.get(j) == nProvinceID) {
                        this.lCoastlineProvinces.remove(j);
                        break;
                    }
                }
                Game.getProvince(nProvinceID).setCivRegionID(-1);
                break;
            }
        }
    }
    
    public final void removeProvince(final int i) {
        Game.getProvince(this.lProvinces.get(i)).setCivRegionID(-1);
        for (int j = 0; j < this.lCoastlineProvinces.size(); ++j) {
            if (this.lCoastlineProvinces.get(j) == this.lProvinces.get(i)) {
                this.lCoastlineProvinces.remove(j);
                break;
            }
        }
        this.lProvinces.remove(i);
        this.iProvincesSize = this.lProvinces.size();
    }
    
    protected final boolean containsProvince(final int nProvinceID) {
        for (int i = 0; i < this.iProvincesSize; ++i) {
            if (this.lProvinces.get(i) == nProvinceID) {
                return true;
            }
        }
        return false;
    }
    
    public final void buildRegionPath_TriedToUse() {
        this.triedToUse.clear();
        for (int i = 0; i < this.iProvincesSize; ++i) {
            this.triedToUse.add(false);
        }
        for (int i = 0; i < this.iProvincesSize; ++i) {
            if (Game.getProvince(this.lProvinces.get(i)).getBelowZero()) {
                this.triedToUse.set(i, true);
            }
        }
        this.numOfTries = 0;
    }
    
    public final boolean buildRegionPath() {
        try {
            this.drawName = false;
            this.buildMinMaxBounds();
            if (this.lProvinces.size() == 1) {
                return false;
            }
            if (this.lProvinces.size() > 1) {
                if (!Game.settingsManager.DRAW_CIVILIZATIONS_NAMES_OVER_PROVINCES_IN_GAME) {
                    return false;
                }
                int startID = -1;
                for (int i = 0; i < this.iProvincesSize; ++i) {
                    if (!this.triedToUse.get(i)) {
                        startID = i;
                        break;
                    }
                }
                if (startID == -1) {
                    return false;
                }
                int fromProvinceID_LEFTRIGHT = startID;
                int toProvinceID_LEFTRIGHT = startID;
                int fromProvinceID_RIGHTLEFT = startID;
                int toProvinceID_RIGHTLEFT = startID;
                int fromProvinceID_BOTTOM = startID;
                int toProvinceID_TOP = startID;
                int fromProvinceID_LR = startID;
                int toProvinceID_LR = startID;
                int leftBottomDistance = (int)Math.sqrt(Math.pow(this.iMinX - Game.getProvince(this.lProvinces.get(fromProvinceID_LEFTRIGHT)).getCenterX(), 2.0) + Math.pow(this.iMaxY - Game.getProvince(this.lProvinces.get(fromProvinceID_LEFTRIGHT)).getCenterY(), 2.0));
                int rightTopDistance = (int)Math.sqrt(Math.pow(this.iMaxX - Game.getProvince(this.lProvinces.get(fromProvinceID_LEFTRIGHT)).getCenterX(), 2.0) + Math.pow(this.iMinY - Game.getProvince(this.lProvinces.get(fromProvinceID_LEFTRIGHT)).getCenterY(), 2.0));
                int rightBottomDistance = (int)Math.sqrt(Math.pow(this.iMaxX - Game.getProvince(this.lProvinces.get(fromProvinceID_LEFTRIGHT)).getCenterX(), 2.0) + Math.pow(this.iMaxY - Game.getProvince(this.lProvinces.get(fromProvinceID_LEFTRIGHT)).getCenterY(), 2.0));
                int leftTopDistance = (int)Math.sqrt(Math.pow(this.iMinX - Game.getProvince(this.lProvinces.get(fromProvinceID_LEFTRIGHT)).getCenterX(), 2.0) + Math.pow(this.iMinY - Game.getProvince(this.lProvinces.get(fromProvinceID_LEFTRIGHT)).getCenterY(), 2.0));
                this.triedToUse.add(true);
                for (int j = startID + 1; j < this.iProvincesSize; ++j) {
                    if (!this.triedToUse.get(j)) {
                        final int toPosX = Game.getProvince(this.lProvinces.get(j)).iCenterShiftX;
                        final int toPosY = Game.getProvince(this.lProvinces.get(j)).iCenterShiftY;
                        int tempDistance = getLineWidth(this.iMinX, this.iMaxY, toPosX, toPosY);
                        if (tempDistance < leftBottomDistance) {
                            leftBottomDistance = tempDistance;
                            fromProvinceID_LEFTRIGHT = j;
                        }
                        tempDistance = getLineWidth(this.iMaxX, this.iMinY, toPosX, toPosY);
                        if (tempDistance < rightTopDistance) {
                            rightTopDistance = tempDistance;
                            toProvinceID_LEFTRIGHT = j;
                        }
                        tempDistance = getLineWidth(this.iMaxX, this.iMaxY, toPosX, toPosY);
                        if (tempDistance < rightBottomDistance) {
                            rightBottomDistance = tempDistance;
                            fromProvinceID_RIGHTLEFT = j;
                        }
                        tempDistance = getLineWidth(this.iMinX, this.iMinY, toPosX, toPosY);
                        if (tempDistance < leftTopDistance) {
                            leftTopDistance = tempDistance;
                            toProvinceID_RIGHTLEFT = j;
                        }
                        if (Game.getProvince(this.lProvinces.get(fromProvinceID_BOTTOM)).iCenterShiftY < toPosY) {
                            fromProvinceID_BOTTOM = j;
                        }
                        if (Game.getProvince(this.lProvinces.get(toProvinceID_TOP)).iCenterShiftY > toPosY) {
                            toProvinceID_TOP = j;
                        }
                        if (Game.getProvince(this.lProvinces.get(fromProvinceID_LR)).iCenterShiftX > toPosX && toPosY >= this.iMinY + (this.iMaxY - this.iMinY) / 2) {
                            fromProvinceID_LR = j;
                        }
                        if (Game.getProvince(this.lProvinces.get(toProvinceID_LR)).iCenterShiftX < toPosX && toPosY <= this.iMinY + (this.iMaxY - this.iMinY) / 2) {
                            toProvinceID_LR = j;
                        }
                    }
                }
                if (this.getLineWidth(fromProvinceID_LEFTRIGHT, toProvinceID_LEFTRIGHT) > this.getLineWidth(fromProvinceID_RIGHTLEFT, toProvinceID_RIGHTLEFT)) {
                    if (this.getLineWidth(fromProvinceID_LEFTRIGHT, toProvinceID_LEFTRIGHT) > this.getLineWidth(fromProvinceID_BOTTOM, toProvinceID_TOP)) {
                        if (this.getLineWidth(fromProvinceID_LEFTRIGHT, toProvinceID_LEFTRIGHT) > this.getLineWidth(fromProvinceID_LR, toProvinceID_LR)) {
                            this.shortestLine.add(fromProvinceID_LEFTRIGHT);
                            this.shortestLine.add(toProvinceID_LEFTRIGHT);
                        }
                        else {
                            this.shortestLine.add(fromProvinceID_LR);
                            this.shortestLine.add(toProvinceID_LR);
                        }
                    }
                    else if (this.getLineWidth(fromProvinceID_BOTTOM, toProvinceID_TOP) > this.getLineWidth(fromProvinceID_LR, toProvinceID_LR)) {
                        this.shortestLine.add(fromProvinceID_BOTTOM);
                        this.shortestLine.add(toProvinceID_TOP);
                    }
                    else {
                        this.shortestLine.add(fromProvinceID_LR);
                        this.shortestLine.add(toProvinceID_LR);
                    }
                }
                else if (this.getLineWidth(fromProvinceID_RIGHTLEFT, toProvinceID_RIGHTLEFT) > this.getLineWidth(fromProvinceID_BOTTOM, toProvinceID_TOP)) {
                    if (this.getLineWidth(fromProvinceID_RIGHTLEFT, toProvinceID_RIGHTLEFT) > this.getLineWidth(fromProvinceID_LR, toProvinceID_LR)) {
                        this.shortestLine.add(fromProvinceID_RIGHTLEFT);
                        this.shortestLine.add(toProvinceID_RIGHTLEFT);
                    }
                    else {
                        this.shortestLine.add(fromProvinceID_LR);
                        this.shortestLine.add(toProvinceID_LR);
                    }
                }
                else if (this.getLineWidth(fromProvinceID_BOTTOM, toProvinceID_TOP) > this.getLineWidth(fromProvinceID_LR, toProvinceID_LR)) {
                    this.shortestLine.add(fromProvinceID_BOTTOM);
                    this.shortestLine.add(toProvinceID_TOP);
                }
                else {
                    this.shortestLine.add(fromProvinceID_LR);
                    this.shortestLine.add(toProvinceID_LR);
                }
                if (Game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCenterX() > Game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getCenterX()) {
                    final int tempS = this.shortestLine.get(0);
                    this.shortestLine.set(0, this.shortestLine.get(1));
                    this.shortestLine.set(1, tempS);
                }
                if (this.shortestLine.size() == 0 || this.shortestLine.get(0) == this.shortestLine.get(1)) {
                    this.shortestLine.clear();
                    this.triedToUse.clear();
                    return false;
                }
                Point_XY tD = this.canDrawTextProperly(this.lProvinces.get(this.shortestLine.get(0)), this.lProvinces.get(this.shortestLine.get(1)));
                if (tD != null) {
                    if (getLineWidth(tD.getPosX(), tD.getPosY(), Game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).iCenterShiftX, Game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).iCenterShiftY) < getLineWidth(tD.getPosX(), tD.getPosY(), Game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).iCenterShiftX, Game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).iCenterShiftY)) {
                        this.triedToUse.set(this.shortestLine.get(0), true);
                    }
                    else {
                        this.triedToUse.set(this.shortestLine.get(1), true);
                    }
                    this.shortestLine.clear();
                    return this.numOfTries++ < 100 && this.buildRegionPath();
                }
                tD = null;
                this.triedToUse.clear();
            }
            this.updateDrawRegionName();
            return true;
        }
        catch (final StackOverflowError ex) {
            CFG.exceptionStack(ex);
            return false;
        }
    }
    
    private final void buildMinMaxBounds() {
        try {
            this.iMinX = Game.getProvince(this.lProvinces.get(0)).getMinX();
            this.iMaxX = Game.getProvince(this.lProvinces.get(0)).getMaxX();
            this.iMinY = Game.getProvince(this.lProvinces.get(0)).getMinY();
            this.iMaxY = Game.getProvince(this.lProvinces.get(0)).getMaxY();
            for (int i = 1; i < this.iProvincesSize; ++i) {
                if (Game.getProvince(this.lProvinces.get(i)).getMinX() < this.iMinX) {
                    this.iMinX = Game.getProvince(this.lProvinces.get(i)).getMinX();
                }
                if (Game.getProvince(this.lProvinces.get(i)).getMaxX() > this.iMaxX) {
                    this.iMaxX = Game.getProvince(this.lProvinces.get(i)).getMaxX();
                }
                if (Game.getProvince(this.lProvinces.get(i)).getMinY() < this.iMinY) {
                    this.iMinY = Game.getProvince(this.lProvinces.get(i)).getMinY();
                }
                if (Game.getProvince(this.lProvinces.get(i)).getMaxY() > this.iMaxY) {
                    this.iMaxY = Game.getProvince(this.lProvinces.get(i)).getMaxY();
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    private final Point_XY canDrawTextProperly(final int fromProvinceID, final int toProvinceID) {
        this.buildAveragePoint();
        final List<Point_XY> tempPoints = new ArrayList<Point_XY>();
        final int tX = Game.getProvince(fromProvinceID).iCenterShiftX;
        final int tX2 = Game.getProvince(toProvinceID).iCenterShiftX;
        final int extra10X = tX + (int)Math.abs((tX2 - tX) * 0.15f) * ((tX > tX2) ? -1 : 1);
        final int extra10X2 = tX2 + (int)Math.abs((tX2 - tX) * 0.15f) * ((tX2 > tX) ? -1 : 1);
        final int tY = Game.getProvince(fromProvinceID).iCenterShiftY;
        final int tY2 = Game.getProvince(toProvinceID).iCenterShiftY;
        final int extra10Y = tY + (int)Math.abs((tY2 - tY) * 0.15f) * ((tY > tY2) ? -1 : 1);
        final int extra10Y2 = tY2 + (int)Math.abs((tY2 - tY) * 0.15f) * ((tY2 > tY) ? -1 : 1);
        final int iPrecision = Game.getCiv(Game.getProvince(fromProvinceID).getCivID()).getCivNameLength() * 10;
        final Vector2[] vPoints = new Vector2[iPrecision];
        final Vector2[] dataSet = { new Vector2((float)extra10X, (float)extra10Y), new Vector2((float)extra10X, (float)extra10Y), new Vector2((float)this.iAveragePointPosX, (float)this.iAveragePointPosY), new Vector2((float)extra10X2, (float)extra10Y2), new Vector2((float)extra10X2, (float)extra10Y2) };
        final CatmullRomSpline<Vector2> oCatmull = (CatmullRomSpline<Vector2>)new CatmullRomSpline((Vector[])dataSet, false);
        for (int i = 0; i < iPrecision; ++i) {
            oCatmull.valueAt((Vector)(vPoints[i] = new Vector2()), i / (iPrecision - 1.0f));
        }
        float tempPrecisionWidth = 0.0f;
        for (int j = 0; j < iPrecision - 1; ++j) {
            tempPrecisionWidth += getLineWidth2((int)vPoints[j].x, (int)vPoints[j].y, (int)vPoints[j + 1].x, (int)vPoints[j + 1].y);
        }
        tempPoints.add(new Point_XY((int)vPoints[0].x, (int)vPoints[0].y));
        float acceptableWidth = 0.0f;
        try {
            acceptableWidth = tempPrecisionWidth / (Game.getCiv(Game.getProvince(fromProvinceID).getCivID()).getCivNameLength() - 1);
        }
        catch (final ArithmeticException ex) {
            CFG.exceptionStack(ex);
        }
        float currentPointsWidth = 0.0f;
        int k = 1;
        int startPrecision = 0;
        while (k < Game.getCiv(Game.getProvince(fromProvinceID).getCivID()).getCivNameLength()) {
            while (startPrecision < iPrecision - 1) {
                final float tempPrecisionWidth2 = getLineWidth2((int)vPoints[startPrecision].x, (int)vPoints[startPrecision].y, (int)vPoints[startPrecision + 1].x, (int)vPoints[startPrecision + 1].y);
                if (currentPointsWidth + tempPrecisionWidth2 >= acceptableWidth) {
                    tempPoints.add(new Point_XY((int)vPoints[startPrecision].x, (int)vPoints[startPrecision].y));
                    currentPointsWidth = acceptableWidth - (currentPointsWidth + tempPrecisionWidth2);
                    break;
                }
                currentPointsWidth += tempPrecisionWidth2;
                ++startPrecision;
            }
            ++k;
        }
        tempPoints.add(new Point_XY((int)vPoints[vPoints.length - 1].x, (int)vPoints[vPoints.length - 1].y));
        for (k = tempPoints.size() - 1; k >= 0; --k) {
            final int nNewChosenProvinceID = Game.setProvinceID_Point(tempPoints.get(k).getPosX(), tempPoints.get(k).getPosY());
            if (nNewChosenProvinceID >= 0 && Game.getProvince(fromProvinceID).getCivID() != Game.getProvince(nNewChosenProvinceID).getCivID()) {
                return tempPoints.get(k);
            }
        }
        final int tTextH = (int)this.buildScaleOfText(0);
        for (int l = tempPoints.size() - 1; l >= 0; --l) {
            int nNewChosenProvinceID2 = Game.setProvinceID_Point(tempPoints.get(l).getPosX() + tTextH, tempPoints.get(l).getPosY());
            if (nNewChosenProvinceID2 >= 0 && Game.getProvince(fromProvinceID).getCivID() != Game.getProvince(nNewChosenProvinceID2).getCivID()) {
                return tempPoints.get(l);
            }
            nNewChosenProvinceID2 = Game.setProvinceID_Point(tempPoints.get(l).getPosX() - tTextH, tempPoints.get(l).getPosY());
            if (nNewChosenProvinceID2 >= 0 && Game.getProvince(fromProvinceID).getCivID() != Game.getProvince(nNewChosenProvinceID2).getCivID()) {
                return tempPoints.get(l);
            }
            nNewChosenProvinceID2 = Game.setProvinceID_Point(tempPoints.get(l).getPosX(), tempPoints.get(l).getPosY() + tTextH);
            if (nNewChosenProvinceID2 >= 0 && Game.getProvince(fromProvinceID).getCivID() != Game.getProvince(nNewChosenProvinceID2).getCivID()) {
                return tempPoints.get(l);
            }
            nNewChosenProvinceID2 = Game.setProvinceID_Point(tempPoints.get(l).getPosX(), tempPoints.get(l).getPosY() - tTextH);
            if (nNewChosenProvinceID2 >= 0 && Game.getProvince(fromProvinceID).getCivID() != Game.getProvince(nNewChosenProvinceID2).getCivID()) {
                return tempPoints.get(l);
            }
            nNewChosenProvinceID2 = Game.setProvinceID_Point(tempPoints.get(l).getPosX() - tTextH, tempPoints.get(l).getPosY() + tTextH);
            if (nNewChosenProvinceID2 >= 0 && Game.getProvince(fromProvinceID).getCivID() != Game.getProvince(nNewChosenProvinceID2).getCivID()) {
                return tempPoints.get(l);
            }
            nNewChosenProvinceID2 = Game.setProvinceID_Point(tempPoints.get(l).getPosX() + tTextH, tempPoints.get(l).getPosY() + tTextH);
            if (nNewChosenProvinceID2 >= 0 && Game.getProvince(fromProvinceID).getCivID() != Game.getProvince(nNewChosenProvinceID2).getCivID()) {
                return tempPoints.get(l);
            }
            nNewChosenProvinceID2 = Game.setProvinceID_Point(tempPoints.get(l).getPosX() - tTextH, tempPoints.get(l).getPosY() - tTextH);
            if (nNewChosenProvinceID2 >= 0 && Game.getProvince(fromProvinceID).getCivID() != Game.getProvince(nNewChosenProvinceID2).getCivID()) {
                return tempPoints.get(l);
            }
            nNewChosenProvinceID2 = Game.setProvinceID_Point(tempPoints.get(l).getPosX() + tTextH, tempPoints.get(l).getPosY() - tTextH);
            if (nNewChosenProvinceID2 >= 0 && Game.getProvince(fromProvinceID).getCivID() != Game.getProvince(nNewChosenProvinceID2).getCivID()) {
                return tempPoints.get(l);
            }
        }
        return null;
    }
    
    private final void buildAveragePoint() {
        long lAverageX = 0L;
        long lAverageY = 0L;
        int tempMinX = Game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getMinX();
        int tempMaxX = Game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getMaxX();
        int tempMinY = Game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getMinY();
        int tempMaxY = Game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getMaxY();
        if (Game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getMinX() < tempMinX) {
            tempMinX = Game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getMinX();
        }
        if (Game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getMaxX() > tempMaxX) {
            tempMaxX = Game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getMaxX();
        }
        if (Game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getMinY() < tempMinY) {
            tempMinY = Game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getMinY();
        }
        if (Game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getMaxY() > tempMaxY) {
            tempMaxY = Game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getMaxY();
        }
        int tSize = 0;
        for (int i = 0; i < this.getProvincesSize(); ++i) {
            if (Game.getProvince(this.getProvince(i)).iCenterShiftX >= tempMinX && Game.getProvince(this.getProvince(i)).iCenterShiftX <= tempMaxX) {
                if (Game.getProvince(this.getProvince(i)).iCenterShiftY >= tempMinY && Game.getProvince(this.getProvince(i)).iCenterShiftY <= tempMaxY) {
                    lAverageX += Game.getProvince(this.getProvince(i)).iCenterShiftX;
                    lAverageY += Game.getProvince(this.getProvince(i)).iCenterShiftY;
                    ++tSize;
                }
            }
            else if (((Game.getProvince(this.getProvince(i)).getMinX() > tempMinX && Game.getProvince(this.getProvince(i)).getMinX() <= tempMaxX) || (Game.getProvince(this.getProvince(i)).getMaxX() > tempMinX && Game.getProvince(this.getProvince(i)).getMaxX() <= tempMaxX)) && ((Game.getProvince(this.getProvince(i)).getMinY() >= tempMinY && Game.getProvince(this.getProvince(i)).getMinY() <= tempMaxY) || (Game.getProvince(this.getProvince(i)).getMaxY() >= tempMinY && Game.getProvince(this.getProvince(i)).getMaxY() <= tempMaxY))) {
                lAverageX += Game.getProvince(this.getProvince(i)).iCenterShiftX;
                lAverageY += Game.getProvince(this.getProvince(i)).iCenterShiftY;
                ++tSize;
            }
        }
        if (tSize == 0) {
            tSize = 1;
        }
        this.iAveragePointPosX = (int)(lAverageX / tSize);
        this.iAveragePointPosY = (int)(lAverageY / tSize);
        final int tAveX = (Game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCenterX() + Game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getCenterX()) / 2;
        final int tAveY = (Game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCenterY() + Game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getCenterY()) / 2;
        this.iAveragePointPosX = (int)(tAveX + (tAveX - this.iAveragePointPosX) * 0.6f);
        this.iAveragePointPosY = (int)(tAveY + (tAveY - this.iAveragePointPosY) * 0.6f);
    }
    
    protected final float buildScaleOfText(final int nFontID) {
        float outTextH = 1.0f;
        try {
            if (this.shortestLine.size() > 1) {
                float iDistance = (float)Math.sqrt(Math.pow(Game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).iCenterShiftX - Game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).iCenterShiftX, 2.0) + Math.pow(Game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).iCenterShiftY - Game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).iCenterShiftY, 2.0));
                iDistance *= 0.95f;
                final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
                synchronized (this) {
                    glyphLayout.setText(Renderer.fontBorder.get(nFontID), Game.getCiv(Game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCivID()).sCivName_UpperCase);
                    int tempNumOfIterations = 0;
                    float tempScale = this.fontScale;
                    Label_0622: {
                        try {
                            do {
                                if (iDistance > glyphLayout.width) {
                                    tempScale += 0.05f;
                                    Renderer.fontBorder.get(nFontID).getData().setScale(tempScale);
                                    glyphLayout.setText(Renderer.fontBorder.get(nFontID), Game.getCiv(Game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCivID()).sCivName_UpperCase);
                                    outTextH = glyphLayout.height;
                                    if (iDistance >= glyphLayout.width) {
                                        continue;
                                    }
                                    this.fontScale = tempScale - 0.05f;
                                }
                                else {
                                    tempScale -= 0.05f;
                                    Renderer.fontBorder.get(nFontID).getData().setScale(tempScale);
                                    glyphLayout.setText(Renderer.fontBorder.get(nFontID), Game.getCiv(Game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCivID()).sCivName_UpperCase);
                                    outTextH = glyphLayout.height;
                                    if (iDistance <= glyphLayout.width) {
                                        continue;
                                    }
                                    this.fontScale = tempScale + 0.05f;
                                }
                                break Label_0622;
                            } while (tempNumOfIterations++ <= 999);
                            this.fontScale = 1.0E-4f;
                        }
                        catch (final IndexOutOfBoundsException ex) {
                            this.fontScale = 1.0E-4f;
                        }
                        catch (final NullPointerException ex2) {
                            this.fontScale = 1.0E-4f;
                            CFG.exceptionStack(ex2);
                            try {
                                Game.getCiv(Game.getProvince(this.lProvinces.get(0)).getCivID()).setUpdateRegions(true);
                            }
                            catch (final Exception ex4) {}
                        }
                        catch (final IllegalStateException ex3) {
                            this.fontScale = 1.0E-4f;
                            CFG.exceptionStack(ex3);
                        }
                    }
                }
                this.buildAveragePoint();
                this.buildDrawData(nFontID);
            }
        }
        catch (final NullPointerException exr) {
            this.fontScale = 1.0E-4f;
            try {
                Game.getCiv(Game.getProvince(this.lProvinces.get(0)).getCivID()).setUpdateRegions(true);
            }
            catch (final Exception ex5) {}
        }
        this.fontScale2 = this.fontScale * 0.875f;
        return outTextH;
    }
    
    public final void buildDrawData(final int nFontID) {
        synchronized (this) {
            Renderer.fontBorder.get(nFontID).getData().setScale(this.fontScale);
            this.iCharMaxWidth = 1;
            this.iCharMaxHeight = 1;
            try {
                for (int i = 0; i < Game.getCiv(Game.getProvince(this.shortestLine.get(0)).getCivID()).getCivNameLength(); ++i) {
                    final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
                    glyphLayout.setText(Renderer.fontBorder.get(nFontID), "" + Game.getCiv(Game.getProvince(this.shortestLine.get(0)).getCivID()).getCivNameCharacter(i));
                    if (glyphLayout.width > this.iCharMaxWidth) {
                        this.iCharMaxWidth = (int)glyphLayout.width;
                    }
                    if (glyphLayout.height > this.iCharMaxWidth) {
                        this.iCharMaxHeight = (int)glyphLayout.height;
                    }
                }
            }
            catch (final IndexOutOfBoundsException ex) {
                CFG.exceptionStack(ex);
            }
            catch (final NullPointerException ex2) {
                CFG.exceptionStack(ex2);
                try {
                    Game.getCiv(Game.getProvince(this.lProvinces.get(0)).getCivID()).setUpdateRegions(true);
                }
                catch (final Exception ex11) {}
            }
            catch (final IllegalStateException ex3) {
                CFG.exceptionStack(ex3);
            }
        }
        this.fAngle = (float)(Math.atan2(Game.getProvince(this.getProvince(this.shortestLine.get(0))).iCenterShiftY - Game.getProvince(this.getProvince(this.shortestLine.get(1))).iCenterShiftY, -Game.getProvince(this.getProvince(this.shortestLine.get(0))).iCenterShiftX + Game.getProvince(this.getProvince(this.shortestLine.get(1))).iCenterShiftX) * 180.0 / 3.141592653589793);
        this.fAngle_Low = (float)(Math.atan2(Game.getProvince(this.getProvince(this.shortestLine.get(0))).iCenterShiftY - Game.getProvince(this.getProvince(this.shortestLine.get(this.shortestLine.size() - 1))).iCenterShiftY, -Game.getProvince(this.getProvince(this.shortestLine.get(0))).iCenterShiftX + Game.getProvince(this.getProvince(this.shortestLine.get(this.shortestLine.size() - 1))).iCenterShiftX) * 180.0 / 3.141592653589793);
        this.lPoints.clear();
        this.drawMatrix4.clear();
        final List<Float> lPointsAngle = new ArrayList<Float>();
        try {
            final int fromProvinceID = this.lProvinces.get(this.shortestLine.get(0));
            final int toProvinceID = this.lProvinces.get(this.shortestLine.get(1));
            final int tX = Game.getProvince(fromProvinceID).iCenterShiftX;
            final int tX2 = Game.getProvince(toProvinceID).iCenterShiftX;
            final int extra10X = tX + (int)Math.abs((tX2 - tX) * 0.15f) * ((tX > tX2) ? -1 : 1);
            final int extra10X2 = tX2 + (int)Math.abs((tX2 - tX) * 0.15f) * ((tX2 > tX) ? -1 : 1);
            final int tY = Game.getProvince(fromProvinceID).iCenterShiftY;
            final int tY2 = Game.getProvince(toProvinceID).iCenterShiftY;
            final int extra10Y = tY + (int)Math.abs((tY2 - tY) * 0.15f) * ((tY > tY2) ? -1 : 1);
            final int extra10Y2 = tY2 + (int)Math.abs((tY2 - tY) * 0.15f) * ((tY2 > tY) ? -1 : 1);
            final int iPrecision = Math.max(3, Game.getCiv(Game.getProvince(fromProvinceID).getCivID()).getCivNameLength()) * 100;
            final Vector2[] vPoints = new Vector2[iPrecision];
            final Vector2[] dataSet = { new Vector2((float)extra10X, (float)extra10Y), new Vector2((float)extra10X, (float)extra10Y), new Vector2((float)this.iAveragePointPosX, (float)this.iAveragePointPosY), new Vector2((float)extra10X2, (float)extra10Y2), new Vector2((float)extra10X2, (float)extra10Y2) };
            final CatmullRomSpline<Vector2> oCatmull = (CatmullRomSpline<Vector2>)new CatmullRomSpline((Vector[])dataSet, false);
            for (int j = 0; j < iPrecision; ++j) {
                oCatmull.valueAt((Vector)(vPoints[j] = new Vector2()), j / (iPrecision - 1.0f));
            }
            float tempPrecissionWidth = 0.0f;
            for (int k = 0; k < iPrecision - 1; ++k) {
                tempPrecissionWidth += getLineWidth2((int)vPoints[k].x, (int)vPoints[k].y, (int)vPoints[k + 1].x, (int)vPoints[k + 1].y);
            }
            this.lPoints.add(new Point_XY((int)vPoints[0].x, (int)vPoints[0].y));
            float acceptableWidth = 0.0f;
            try {
                acceptableWidth = tempPrecissionWidth / (Game.getCiv(Game.getProvince(fromProvinceID).getCivID()).getCivNameLength() - 1);
            }
            catch (final ArithmeticException ex4) {
                CFG.exceptionStack(ex4);
            }
            float currentPointsWidth = 0.0f;
            int l = 1;
            int startPrecision = 0;
            while (l < Game.getCiv(Game.getProvince(fromProvinceID).getCivID()).getCivNameLength()) {
                while (startPrecision < iPrecision - 1) {
                    final float tempPrecisionWidth = getLineWidth2((int)vPoints[startPrecision].x, (int)vPoints[startPrecision].y, (int)vPoints[startPrecision + 1].x, (int)vPoints[startPrecision + 1].y);
                    if (currentPointsWidth + tempPrecisionWidth >= acceptableWidth) {
                        this.lPoints.add(new Point_XY((int)vPoints[startPrecision].x, (int)vPoints[startPrecision].y));
                        currentPointsWidth = acceptableWidth - (currentPointsWidth + tempPrecisionWidth);
                        break;
                    }
                    currentPointsWidth += tempPrecisionWidth;
                    ++startPrecision;
                }
                ++l;
            }
            this.lPoints.add(new Point_XY((int)vPoints[vPoints.length - 1].x, (int)vPoints[vPoints.length - 1].y));
            try {
                for (l = 0; l < Game.getCiv(Game.getProvince(fromProvinceID).getCivID()).getCivNameLength(); ++l) {
                    float tempPointsAngle = 0.0f;
                    try {
                        if (l < Game.getCiv(Game.getProvince(fromProvinceID).getCivID()).getCivNameLength() - 1) {
                            tempPointsAngle = getLinesAngle(this.lPoints.get(l).getPosX(), this.lPoints.get(l).getPosY(), this.lPoints.get(l + 1).getPosX(), this.lPoints.get(l + 1).getPosY());
                        }
                        else if (l - 1 >= 0) {
                            tempPointsAngle = getLinesAngle(this.lPoints.get(l - 1).getPosX(), this.lPoints.get(l - 1).getPosY(), this.lPoints.get(l).getPosX(), this.lPoints.get(l).getPosY());
                        }
                        lPointsAngle.add(tempPointsAngle);
                    }
                    catch (final IndexOutOfBoundsException ex5) {
                        if (l == 0) {
                            try {
                                lPointsAngle.add(getLinesAngle(this.lPoints.get(l).getPosX(), this.lPoints.get(l).getPosY(), this.lPoints.get(l + 1).getPosX(), this.lPoints.get(l + 1).getPosY()));
                            }
                            catch (final IndexOutOfBoundsException e) {
                                lPointsAngle.add(this.fAngle);
                            }
                        }
                        else {
                            try {
                                lPointsAngle.add(getLinesAngle(this.lPoints.get(l - 1).getPosX(), this.lPoints.get(l - 1).getPosY(), this.lPoints.get(l).getPosX(), this.lPoints.get(l).getPosY()));
                            }
                            catch (final IndexOutOfBoundsException e) {
                                lPointsAngle.add(this.fAngle);
                            }
                        }
                    }
                    catch (final NullPointerException ex6) {
                        if (l == 0) {
                            try {
                                lPointsAngle.add(getLinesAngle(this.lPoints.get(l).getPosX(), this.lPoints.get(l).getPosY(), this.lPoints.get(l + 1).getPosX(), this.lPoints.get(l + 1).getPosY()));
                            }
                            catch (final IndexOutOfBoundsException e) {
                                lPointsAngle.add(this.fAngle);
                            }
                        }
                        else {
                            try {
                                lPointsAngle.add(getLinesAngle(this.lPoints.get(l - 1).getPosX(), this.lPoints.get(l - 1).getPosY(), this.lPoints.get(l).getPosX(), this.lPoints.get(l).getPosY()));
                            }
                            catch (final IndexOutOfBoundsException e) {
                                lPointsAngle.add(this.fAngle);
                            }
                        }
                        try {
                            Game.getCiv(Game.getProvince(this.lProvinces.get(0)).getCivID()).setUpdateRegions(true);
                        }
                        catch (final IndexOutOfBoundsException ex12) {}
                        catch (final NullPointerException ex13) {}
                    }
                }
            }
            catch (final IndexOutOfBoundsException ex7) {
                CFG.exceptionStack(ex7);
            }
            catch (final NullPointerException ex8) {
                CFG.exceptionStack(ex8);
                try {
                    Game.getCiv(Game.getProvince(this.lProvinces.get(0)).getCivID()).setUpdateRegions(true);
                }
                catch (final IndexOutOfBoundsException ex14) {}
                catch (final NullPointerException ex15) {}
            }
            catch (final IllegalStateException ex9) {
                CFG.exceptionStack(ex9);
            }
            float tempAngle = 0.0f;
            for (int m = 0, iSize = lPointsAngle.size(); m < iSize; ++m) {
                tempAngle += lPointsAngle.get(m);
            }
            tempAngle /= lPointsAngle.size();
            this.centerCharXY = new Point_XY((int)(this.getCharMaxWidth() / 2 * (1.0f - (90.0f - Math.min(Math.abs(tempAngle), 90.0f)) / 90.0f)), (int)(this.getCharMaxHeight() / 2 * ((90.0f - Math.min(Math.abs(tempAngle), 90.0f)) / 90.0f)));
        }
        catch (final Exception ex10) {
            CFG.exceptionStack(ex10);
        }
        for (int i = 0, iSize2 = lPointsAngle.size(); i < iSize2; ++i) {
            this.drawMatrix4.add(new Matrix4().rotate(Renderer.textRotatedVector3, (float)lPointsAngle.get(i)));
        }
    }
    
    protected final void updateDrawRegionName() {
        this.drawName = true;
    }
    
    public static float getLinesAngle(final int fromPosX, final int fromPosY, final int toPosX, final int toPosY) {
        return (float)(Math.atan2(fromPosY - toPosY, -fromPosX + toPosX) * 180.0 / 3.141592653589793);
    }
    
    public static float getLinesAngle2(final float fromPosX, final float fromPosY, final float toPosX, final float toPosY) {
        return (float)(Math.atan2(fromPosY - toPosY, -fromPosX + toPosX) * 180.0 / 3.141592653589793);
    }
    
    protected int getLineWidth(final int fromCenterPosProvinceID, final int toCenterPosProvinceID) {
        return getLineWidth(Game.getProvince(this.lProvinces.get(fromCenterPosProvinceID)).iCenterShiftX, Game.getProvince(this.lProvinces.get(fromCenterPosProvinceID)).iCenterShiftY, Game.getProvince(this.lProvinces.get(toCenterPosProvinceID)).iCenterShiftX, Game.getProvince(this.lProvinces.get(toCenterPosProvinceID)).iCenterShiftY);
    }
    
    public static int getLineWidth(final int fromPosX, final int fromPosY, final int toPosX, final int toPosY) {
        return (int)Math.sqrt(Math.pow(fromPosX - toPosX, 2.0) + Math.pow(fromPosY - toPosY, 2.0));
    }
    
    public static float getLineWidth2(final int fromPosX, final int fromPosY, final int toPosX, final int toPosY) {
        return (float)Math.sqrt(Math.pow(fromPosX - toPosX, 2.0) + Math.pow(fromPosY - toPosY, 2.0));
    }
    
    public static float getLineWidth3(final float fromPosX, final float fromPosY, final float toPosX, final float toPosY) {
        return (float)Math.sqrt(Math.pow(fromPosX - toPosX, 2.0) + Math.pow(fromPosY - toPosY, 2.0));
    }
    
    public final int getProvince(final int i) {
        return this.lProvinces.get(i);
    }
    
    public final int getProvincesSize() {
        return this.iProvincesSize;
    }
    
    public final List<Integer> getShortestPath() {
        return this.shortestLine;
    }
    
    public final float getFontScale() {
        return this.fontScale;
    }
    
    public final float getAngle() {
        return this.fAngle;
    }
    
    public final int getCharMaxWidth() {
        return this.iCharMaxWidth;
    }
    
    public final int getCharMaxHeight() {
        return this.iCharMaxHeight;
    }
}
