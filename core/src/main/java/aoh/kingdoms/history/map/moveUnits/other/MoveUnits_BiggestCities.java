// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.moveUnits.other;

import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.CatmullRomSpline;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import space.earlygrey.shapedrawer.JoinType;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.menu.Colors;
import java.util.ArrayList;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;
import aoc.kingdoms.lukasz.map.moveUnits.MoveUnits;
import java.util.List;

public class MoveUnits_BiggestCities
{
    public List<Integer> lRoute;
    public int iRouteSize;
    public long lMovingTime;
    public float fMovingPercentage;
    MoveUnits.LittleAnimation littleAnimationMainLine;
    public Color ColorLine;
    public Color ColorLine2;
    public int iPrecision;
    public Vector2[] vPoints;
    public static final int PRECISION = 15;
    
    public MoveUnits_BiggestCities(final int nCivID, final int iFromProvinceID, final int iToProvinceID) {
        this.lRoute = new ArrayList<Integer>();
        this.iRouteSize = 0;
        this.lMovingTime = 0L;
        this.fMovingPercentage = 0.0f;
        this.ColorLine = new Color(0.57254905f, 0.50980395f, 0.4509804f, 1.0f);
        this.ColorLine2 = new Color(0.04f, 0.04f, 0.04f, 1.0f);
        this.buildRoute(nCivID, iFromProvinceID, iToProvinceID);
        if (this.iRouteSize > 1) {
            this.buildMoveUnitsLine(true);
            this.ColorLine2 = CFG.getColorStep(Colors.HOVER_GOLD, Game.getCiv(Game.getProvince(this.lRoute.get(0)).getCivID()).getColor(1.0f), 50, 100, 0.4f);
        }
    }
    
    public void update() {
        this.littleAnimationMainLine.update();
    }
    
    public void draw(final SpriteBatch oSB, final float nScale) {
        try {
            if (this.iRouteSize > 0) {
                final Array<Vector2> nPath = (Array<Vector2>)new Array();
                if (Game.getProvince(this.lRoute.get(0)).getDrawProvince()) {
                    for (int j = 0; j < (int)((this.iPrecision - 2) * this.fMovingPercentage); ++j) {
                        nPath.add((Object)new Vector2((this.vPoints[j].x + Game.getProvince(this.lRoute.get(0)).getTranslateProvincePosX()) * nScale, (this.vPoints[j].y - Game.mapCoords.getPosY()) * nScale));
                    }
                }
                else {
                    for (int j = 0; j < (int)(this.iPrecision * this.fMovingPercentage); ++j) {
                        nPath.add((Object)new Vector2((this.vPoints[j].x + Game.mapCoords.getPosX()) * nScale, (this.vPoints[j].y - Game.mapCoords.getPosY()) * nScale));
                    }
                }
                if (this.fMovingPercentage < 0.99f) {
                    Renderer.shapeDrawer.setColor(CFG.getColorStep(Colors.HOVER_GOLD, this.ColorLine2, (int)(this.fMovingPercentage * 100.0f), 100, 0.4f));
                }
                else {
                    Renderer.shapeDrawer.setColor(new Color(this.ColorLine2.r, this.ColorLine2.g, this.ColorLine2.b, 0.4f));
                }
                Renderer.shapeDrawer.path((Iterable)nPath, 3.0f * (0.25f + 0.75f * this.fMovingPercentage), JoinType.SMOOTH, true);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public boolean draw2(final SpriteBatch oSB, final float nScale) {
        return false;
    }
    
    protected boolean buildRoute(final int nCivID, final int fromProvinceID, final int toProvinceID) {
        this.lRoute.clear();
        if (fromProvinceID < 0 || toProvinceID < 0 || Game.getProvince(toProvinceID).getWasteland() >= 0) {
            return false;
        }
        final List<Integer> was = new ArrayList<Integer>();
        was.add(fromProvinceID);
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            Game.getProvince(i).wasCities = false;
        }
        Game.getProvince(fromProvinceID).wasCities = true;
        final List<Integer> in = new ArrayList<Integer>();
        final List<List<Integer>> inPath = new ArrayList<List<Integer>>();
        for (int j = 0; j < Game.getProvince(fromProvinceID).getNeighboringProvincesSize(); ++j) {
            if (this.canBeUsedInPath(nCivID, Game.getProvince(fromProvinceID).getNeighboringProvinces(j), this.isFriendlyProvince(nCivID, toProvinceID), toProvinceID)) {
                in.add(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringProvinces(j)).getProvinceID());
                final List<Integer> tP = new ArrayList<Integer>();
                tP.add(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringProvinces(j)).getProvinceID());
                inPath.add(tP);
                was.add(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringProvinces(j)).getProvinceID());
                Game.getProvince(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringProvinces(j)).getProvinceID()).wasCities = true;
            }
        }
        if (!Game.getProvince(fromProvinceID).getSeaProvince()) {
            for (int j = 0; j < Game.getProvince(fromProvinceID).getNeighboringSeaProvincesSize(); ++j) {
                in.add(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringSeaProvinces(j)).getProvinceID());
                final List<Integer> tP = new ArrayList<Integer>();
                tP.add(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringSeaProvinces(j)).getProvinceID());
                inPath.add(tP);
                was.add(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringSeaProvinces(j)).getProvinceID());
                Game.getProvince(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringSeaProvinces(j)).getProvinceID()).wasCities = true;
            }
        }
        this.buildPath(nCivID, was, in, inPath, fromProvinceID, toProvinceID, true);
        return true;
    }
    
    public boolean isFriendlyProvince(final int nCivID, final int toProvinceID) {
        return Game.getProvince(toProvinceID).getCivID() == nCivID;
    }
    
    public boolean canBeUsedInPath(final int nCivID, final int nProvinceID, final boolean moveToFriendlyProvince, final int toProvinceID) {
        return Game.getProvince(nProvinceID).getWasteland() < 0 && Game.getProvince(nProvinceID).getCivID() == nCivID;
    }
    
    protected boolean buildPath(final int nCivID, final List<Integer> was, final List<Integer> in, final List<List<Integer>> inPath, final int from, final int lookingFor, final boolean forDirection) {
        final List<Integer> nIN = new ArrayList<Integer>();
        final List<List<Integer>> nINPath = new ArrayList<List<Integer>>();
        for (int i = 0; i < in.size(); ++i) {
            if (Game.getProvince(in.get(i)).getProvinceID() == lookingFor) {
                this.setPath(from, lookingFor, inPath.get(i), lookingFor, from);
                this.clearWas(was);
                return true;
            }
        }
        if (forDirection) {
            for (int i = 0; i < in.size(); ++i) {
                for (int j = 0; j < Game.getProvince(in.get(i)).getNeighboringProvincesSize(); ++j) {
                    if (this.canBeUsedInPath(nCivID, Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID(), this.isFriendlyProvince(nCivID, lookingFor), lookingFor) && !Game.getProvince(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID()).wasCities) {
                        if (Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID() == lookingFor) {
                            this.setPath(from, lookingFor, inPath.get(i), lookingFor, from);
                            this.clearWas(was);
                            return true;
                        }
                        nIN.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID());
                        final List<Integer> tPL = new ArrayList<Integer>();
                        for (int u = 0; u < inPath.get(i).size(); ++u) {
                            tPL.add(inPath.get(i).get(u));
                        }
                        tPL.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID());
                        nINPath.add(tPL);
                        Game.getProvince(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID()).wasCities = true;
                        was.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID());
                    }
                }
                if (!Game.getProvince(in.get(i)).getSeaProvince()) {
                    for (int j = 0; j < Game.getProvince(in.get(i)).getNeighboringSeaProvincesSize(); ++j) {
                        if (!Game.getProvince(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID()).wasCities) {
                            if (Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID() == lookingFor) {
                                this.setPath(from, lookingFor, inPath.get(i), lookingFor, from);
                                this.clearWas(was);
                                return true;
                            }
                            nIN.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID());
                            final List<Integer> tPL = new ArrayList<Integer>();
                            for (int u = 0; u < inPath.get(i).size(); ++u) {
                                tPL.add(inPath.get(i).get(u));
                            }
                            tPL.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID());
                            nINPath.add(tPL);
                            Game.getProvince(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID()).wasCities = true;
                            was.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID());
                        }
                    }
                }
            }
        }
        else {
            for (int i = 0; i < in.size(); ++i) {
                for (int j = Game.getProvince(in.get(i)).getNeighboringProvincesSize() - 1; j >= 0; --j) {
                    if (this.canBeUsedInPath(nCivID, Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID(), this.isFriendlyProvince(nCivID, lookingFor), lookingFor) && !Game.getProvince(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID()).wasCities) {
                        if (Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID() == lookingFor) {
                            this.setPath(from, lookingFor, inPath.get(i), lookingFor, from);
                            this.clearWas(was);
                            return true;
                        }
                        nIN.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID());
                        final List<Integer> tPL = new ArrayList<Integer>();
                        for (int u = 0; u < inPath.get(i).size(); ++u) {
                            tPL.add(inPath.get(i).get(u));
                        }
                        tPL.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID());
                        nINPath.add(tPL);
                        Game.getProvince(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID()).wasCities = true;
                        was.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID());
                    }
                }
                if (!Game.getProvince(in.get(i)).getSeaProvince()) {
                    for (int j = Game.getProvince(in.get(i)).getNeighboringSeaProvincesSize() - 1; j >= 0; --j) {
                        if (!Game.getProvince(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID()).wasCities) {
                            if (Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID() == lookingFor) {
                                this.setPath(from, lookingFor, inPath.get(i), lookingFor, from);
                                this.clearWas(was);
                                return true;
                            }
                            nIN.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID());
                            final List<Integer> tPL = new ArrayList<Integer>();
                            for (int u = 0; u < inPath.get(i).size(); ++u) {
                                tPL.add(inPath.get(i).get(u));
                            }
                            tPL.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID());
                            nINPath.add(tPL);
                            Game.getProvince(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID()).wasCities = true;
                            was.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID());
                        }
                    }
                }
            }
        }
        try {
            return this.buildPath(nCivID, was, nIN, nINPath, from, lookingFor, !forDirection);
        }
        catch (final StackOverflowError ex) {
            this.clearWas(was);
            return false;
        }
    }
    
    protected final void clearWas(final List<Integer> was) {
        for (int i = was.size() - 1; i >= 0; --i) {
            Game.getProvince(was.get(i)).wasCities = false;
        }
    }
    
    protected final void setPath(final int p1, final int p2, final List<Integer> lPath, final int toProvinceID, final int fromProvinceID) {
        this.lRoute.add(fromProvinceID);
        for (int i = 0; i < lPath.size(); ++i) {
            this.lRoute.add(lPath.get(i));
        }
        if (toProvinceID != this.lRoute.get(this.lRoute.size() - 1)) {
            this.lRoute.add(toProvinceID);
        }
        this.iRouteSize = this.lRoute.size();
    }
    
    public void buildAnimation(final boolean updateAnimation) {
        if (updateAnimation) {
            this.lMovingTime = CFG.currentTimeMillis;
            this.fMovingPercentage = 0.01f;
            this.littleAnimationMainLine = new MoveUnits.LittleAnimation() {
                @Override
                public void update() {
                    final MoveUnits_BiggestCities this$0 = MoveUnits_BiggestCities.this;
                    this$0.fMovingPercentage += (CFG.currentTimeMillis - MoveUnits_BiggestCities.this.lMovingTime) / GameValues.inGame.DIPLOMACY_LINES_ANIMATION_DURATION;
                    MoveUnits_BiggestCities.this.lMovingTime = CFG.currentTimeMillis;
                    if (MoveUnits_BiggestCities.this.fMovingPercentage >= 1.0f) {
                        MoveUnits_BiggestCities.this.fMovingPercentage = 1.0f;
                        MoveUnits_BiggestCities.this.littleAnimationMainLine = new MoveUnits.LittleAnimation() {
                            @Override
                            public void update() {
                            }
                        };
                    }
                }
            };
        }
    }
    
    public int getShiftPosXY() {
        return -15 + Game.oR.nextInt(31);
    }
    
    public void buildMoveUnitsLine(final boolean updateAnimation) {
        this.buildAnimation(updateAnimation);
        this.iPrecision = 15 * this.iRouteSize;
        this.vPoints = new Vector2[this.iPrecision];
        final Vector2[] dataSet = new Vector2[this.iRouteSize + 2];
        for (int i = 0; i < this.iRouteSize; ++i) {
            if (Game.getProvince(this.lRoute.get(i)).getCitiesSize() > 0) {
                dataSet[i + 1] = new Vector2((float)Game.getProvince(this.lRoute.get(i)).getCity(0).getPosX(), (float)(-Game.getProvince(this.lRoute.get(i)).getCity(0).getPosY()));
            }
            else {
                dataSet[i + 1] = new Vector2((float)Game.getProvince(this.lRoute.get(i)).iCenterShiftX, (float)(-Game.getProvince(this.lRoute.get(i)).iCenterShiftY));
            }
        }
        if (Game.getProvince(this.lRoute.get(0)).getCitiesSize() > 0) {
            dataSet[0] = new Vector2((float)(Game.getProvince(this.lRoute.get(0)).getCity(0).getPosX() + this.getShiftPosXY()), (float)(-(Game.getProvince(this.lRoute.get(0)).getCity(0).getPosY() + this.getShiftPosXY())));
        }
        else {
            dataSet[0] = new Vector2((float)(Game.getProvince(this.lRoute.get(0)).iCenterShiftX + this.getShiftPosXY()), (float)(-(Game.getProvince(this.lRoute.get(0)).iCenterShiftY + this.getShiftPosXY())));
        }
        if (Game.getProvince(this.lRoute.get(this.iRouteSize - 1)).getCitiesSize() > 0) {
            dataSet[this.iRouteSize + 1] = new Vector2((float)Game.getProvince(this.lRoute.get(this.iRouteSize - 1)).getCity(0).getPosX(), (float)(-Game.getProvince(this.lRoute.get(this.iRouteSize - 1)).getCity(0).getPosY()));
        }
        else {
            dataSet[this.iRouteSize + 1] = new Vector2((float)Game.getProvince(this.lRoute.get(this.iRouteSize - 1)).iCenterShiftX, (float)(-Game.getProvince(this.lRoute.get(this.iRouteSize - 1)).iCenterShiftY));
        }
        final CatmullRomSpline<Vector2> oCatmull = (CatmullRomSpline<Vector2>)new CatmullRomSpline((Vector[])dataSet, false);
        for (int j = 0; j < this.iPrecision; ++j) {
            oCatmull.valueAt((Vector)(this.vPoints[j] = new Vector2()), j / (this.iPrecision - 1.0f));
        }
    }
    
    public int getFromProvinceID() {
        return this.lRoute.get(0);
    }
    
    public int getToProvinceID() {
        return this.lRoute.get(1);
    }
    
    public int getToProvinceLastID() {
        return this.lRoute.get(this.iRouteSize - 1);
    }
    
    interface LittleAnimation
    {
        void update();
    }
}
