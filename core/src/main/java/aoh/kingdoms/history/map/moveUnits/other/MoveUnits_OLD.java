// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.moveUnits.other;

import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoc.kingdoms.lukasz.jakowski.CFG;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.ArrayList;
import java.util.List;

public class MoveUnits_OLD
{
    protected List<Integer> lRoute;
    protected int iRouteSize;
    public String key;
    protected List<Integer> iWidth;
    protected List<Integer> fAngle;
    public long lMovingTime;
    public float fMovingPercentage;
    public int moveLineSrcX;
    
    public MoveUnits_OLD(final int nCivID, final int iFromProvinceID, final int iToProvinceID, final String key) {
        this.lRoute = new ArrayList<Integer>();
        this.iRouteSize = 0;
        this.iWidth = new ArrayList<Integer>();
        this.fAngle = new ArrayList<Integer>();
        this.lMovingTime = 0L;
        this.fMovingPercentage = 0.0f;
        this.moveLineSrcX = 0;
        this.key = key;
        this.buildRoute(nCivID, iFromProvinceID, iToProvinceID);
        this.buildMoveUnitsLine();
        final int armyID = Game.getProvince(iFromProvinceID).getArmyKeyID(key);
        if (armyID >= 0) {
            Game.getProvince(iFromProvinceID).getArmy(armyID).iShiftX = Game.getProvince(iFromProvinceID).getArmy(armyID).defaultShiftX();
            Game.getProvince(iFromProvinceID).getArmy(armyID).iShiftY = Game.getProvince(iFromProvinceID).getArmy(armyID).defaultShiftY();
        }
    }
    
    protected boolean buildRoute(final int nCivID, final int fromProvinceID, final int toProvinceID) {
        this.lRoute.clear();
        if (fromProvinceID < 0 || toProvinceID < 0 || Game.getProvince(toProvinceID).getWasteland() >= 0) {
            return false;
        }
        if (!Game.getProvince(fromProvinceID).getSeaProvince() && Game.getProvince(fromProvinceID).getNeighboringProvincesSize() == 0 && Game.getProvince(fromProvinceID).getLevelOfPort() <= 0) {
            return false;
        }
        final List<Integer> was = new ArrayList<Integer>();
        was.add(fromProvinceID);
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            Game.getProvince(i).was = false;
        }
        Game.getProvince(fromProvinceID).was = true;
        final List<Integer> in = new ArrayList<Integer>();
        final List<List<Integer>> inPath = new ArrayList<List<Integer>>();
        for (int j = 0; j < Game.getProvince(fromProvinceID).getNeighboringProvincesSize(); ++j) {
            if (canBeUsedInPath(nCivID, Game.getProvince(fromProvinceID).getNeighboringProvinces(j), isFriendlyProvince(nCivID, toProvinceID), toProvinceID)) {
                in.add(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringProvinces(j)).getProvinceID());
                final List<Integer> tP = new ArrayList<Integer>();
                tP.add(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringProvinces(j)).getProvinceID());
                inPath.add(tP);
                was.add(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringProvinces(j)).getProvinceID());
                Game.getProvince(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringProvinces(j)).getProvinceID()).was = true;
            }
        }
        if (!Game.getProvince(fromProvinceID).getSeaProvince() && Game.getProvince(fromProvinceID).getLevelOfPort() > 0) {
            for (int j = 0; j < Game.getProvince(fromProvinceID).getNeighboringSeaProvincesSize(); ++j) {
                in.add(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringSeaProvinces(j)).getProvinceID());
                final List<Integer> tP = new ArrayList<Integer>();
                tP.add(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringSeaProvinces(j)).getProvinceID());
                inPath.add(tP);
                was.add(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringSeaProvinces(j)).getProvinceID());
                Game.getProvince(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringSeaProvinces(j)).getProvinceID()).was = true;
            }
        }
        this.buildPath(nCivID, was, in, inPath, fromProvinceID, toProvinceID);
        return true;
    }
    
    public static final boolean isFriendlyProvince(final int nCivID, final int toProvinceID) {
        return true;
    }
    
    protected static boolean canBeUsedInPath(final int nCivID, final int nProvinceID, final boolean moveToFriendlyProvince, final int toProvinceID) {
        return Game.getProvince(nProvinceID).getWasteland() < 0;
    }
    
    protected boolean buildPath(final int nCivID, final List<Integer> was, final List<Integer> in, final List<List<Integer>> inPath, final int from, final int lookingFor) {
        final List<Integer> nIN = new ArrayList<Integer>();
        final List<List<Integer>> nINPath = new ArrayList<List<Integer>>();
        for (int i = 0; i < in.size(); ++i) {
            if (Game.getProvince(in.get(i)).getProvinceID() == lookingFor) {
                this.setPath(from, lookingFor, inPath.get(i), lookingFor, from);
                this.clearWas(was);
                return true;
            }
        }
        for (int i = 0; i < in.size(); ++i) {
            for (int j = 0; j < Game.getProvince(in.get(i)).getNeighboringProvincesSize(); ++j) {
                if (canBeUsedInPath(nCivID, Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID(), isFriendlyProvince(nCivID, lookingFor), lookingFor) && !Game.getProvince(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID()).was) {
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
                    Game.getProvince(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID()).was = true;
                    was.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID());
                }
            }
            if (!Game.getProvince(in.get(i)).getSeaProvince() && Game.getProvince(in.get(i)).getLevelOfPort() > 0) {
                for (int j = 0; j < Game.getProvince(in.get(i)).getNeighboringSeaProvincesSize(); ++j) {
                    if (!Game.getProvince(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID()).was) {
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
                        Game.getProvince(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID()).was = true;
                        was.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID());
                    }
                }
            }
        }
        try {
            return this.buildPath(nCivID, was, nIN, nINPath, from, lookingFor);
        }
        catch (final StackOverflowError ex) {
            this.clearWas(was);
            return false;
        }
    }
    
    protected final void clearWas(final List<Integer> was) {
        for (int i = was.size() - 1; i >= 0; --i) {
            Game.getProvince(was.get(i)).was = false;
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
    
    public void draw(final SpriteBatch oSB, final float nScale) {
        this.fMovingPercentage += (CFG.currentTimeMillis - this.lMovingTime) / 15000.0f;
        this.lMovingTime = CFG.currentTimeMillis;
        if (this.fMovingPercentage >= 1.0f) {
            this.fMovingPercentage = 1.0f;
        }
        final int armyID = Game.getProvince(this.lRoute.get(0)).getArmyKeyID(this.key);
        if (armyID >= 0) {
            Game.getProvince(this.lRoute.get(0)).getArmy(armyID).iShiftX_Scaled = (int)((Game.getProvince(this.lRoute.get(1)).getCenterX() - Game.getProvince(this.lRoute.get(0)).getCenterX()) * this.fMovingPercentage);
            Game.getProvince(this.lRoute.get(0)).getArmy(armyID).iShiftY_Scaled = (int)((Game.getProvince(this.lRoute.get(1)).getCenterY() - Game.getProvince(this.lRoute.get(0)).getCenterY()) * this.fMovingPercentage);
        }
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.2f));
        ImageManager.getImage(Images.moveLine).draw2(oSB, CFG.rotateXMoveUnits[this.fAngle.get(0)] + (int)((Game.getProvince(this.lRoute.get(0)).iCenterShiftX + Game.getProvince(this.lRoute.get(0)).getTranslateProvincePosX()) * nScale), CFG.rotateYMoveUnits[this.fAngle.get(0)] + (int)((Game.getProvince(this.lRoute.get(0)).iCenterShiftY + Game.mapCoords.getPosY()) * nScale), (int)(this.iWidth.get(0) * nScale), ImageManager.getImage(Images.moveLine).getHeight(), this.fAngle.get(0), true, false);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.5f));
        final int tempWidth = (int)(this.iWidth.get(0) * this.fMovingPercentage * nScale);
        ImageManager.getImage(Images.moveLine).draw2(oSB, CFG.rotateXMoveUnits[this.fAngle.get(0)] + (int)((Game.getProvince(this.lRoute.get(0)).iCenterShiftX + Game.getProvince(this.lRoute.get(0)).getTranslateProvincePosX()) * nScale), CFG.rotateYMoveUnits[this.fAngle.get(0)] + (int)((Game.getProvince(this.lRoute.get(0)).iCenterShiftY + Game.mapCoords.getPosY()) * nScale), tempWidth, ImageManager.getImage(Images.moveLine).getHeight(), this.fAngle.get(0), true, false, (int)(this.iWidth.get(0) * nScale - tempWidth));
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.2f));
        for (int i = 1; i < this.iRouteSize - 1; ++i) {
            Images.line_32_off1.draw2(oSB, (int)((Game.getProvince(this.lRoute.get(i)).iCenterShiftX + Game.getProvince(this.lRoute.get(i)).getTranslateProvincePosX()) * nScale), (int)((Game.getProvince(this.lRoute.get(i)).iCenterShiftY + Game.mapCoords.getPosY()) * nScale), (int)(this.iWidth.get(i) * nScale), Images.line_32_off1.getHeight(), this.fAngle.get(i), true, false, ++this.moveLineSrcX);
        }
        oSB.setColor(Color.WHITE);
    }
    
    private final void buildMoveUnitsLine() {
        try {
            for (int i = 0; i < this.iRouteSize - 1; ++i) {
                this.iWidth.add((int)Math.ceil(Math.sqrt((Game.getProvince(this.lRoute.get(i + 1)).iCenterShiftX + Game.getProvince(this.lRoute.get(i + 1)).getTranslateProvincePosX() - (Game.getProvince(this.lRoute.get(i)).iCenterShiftX + Game.getProvince(this.lRoute.get(i)).getTranslateProvincePosX())) * (Game.getProvince(this.lRoute.get(i + 1)).iCenterShiftX + Game.getProvince(this.lRoute.get(i + 1)).getTranslateProvincePosX() - (Game.getProvince(this.lRoute.get(i)).iCenterShiftX + Game.getProvince(this.lRoute.get(i)).getTranslateProvincePosX())) + (Game.getProvince(this.lRoute.get(i)).iCenterShiftY - Game.getProvince(this.lRoute.get(i + 1)).iCenterShiftY) * (Game.getProvince(this.lRoute.get(i)).iCenterShiftY - Game.getProvince(this.lRoute.get(i + 1)).iCenterShiftY))));
                this.fAngle.add((int)(Math.atan2(Game.getProvince(this.lRoute.get(i)).iCenterShiftY - Game.getProvince(this.lRoute.get(i + 1)).iCenterShiftY, -(Game.getProvince(this.lRoute.get(i)).iCenterShiftX + Game.getProvince(this.lRoute.get(i)).getTranslateProvincePosX()) + (Game.getProvince(this.lRoute.get(i + 1)).iCenterShiftX + Game.getProvince(this.lRoute.get(i + 1)).getTranslateProvincePosX())) * 180.0 / 3.141592653589793));
                if (this.fAngle.get(i) < 0) {
                    this.fAngle.set(i, this.fAngle.get(i) + 360);
                }
                this.fAngle.set(i, this.fAngle.get(i) % 360);
            }
            this.lMovingTime = CFG.currentTimeMillis;
            this.fMovingPercentage = 0.15f;
        }
        catch (final IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (final NullPointerException ex2) {
            CFG.exceptionStack(ex2);
        }
    }
}
