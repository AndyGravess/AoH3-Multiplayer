// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.moveUnits;

import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.CatmullRomSpline;
import space.earlygrey.shapedrawer.JoinType;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import java.util.Collection;
import aoc.kingdoms.lukasz.map.province.Province;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Comparator;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.map.province.ProvinceDrawArmy;
import java.util.ArrayList;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;
import java.util.List;

public class MoveUnits
{
    public String key;
    private List<Integer> lRoute;
    public int iRouteSize;
    public float doneMovementProgressWidth;
    public float currentMovementProgressWidth;
    public float movementProgressOverWidth;
    public boolean inBattle;
    public boolean inRetreat;
    public List<Integer> iWidth;
    public long lMovingTime;
    public float fMovingPercentage;
    public long lCurrentMovingTime;
    public float fCurrentMovingPercentage;
    public float fSpeed;
    public int extraArmyY;
    public Color colorLine;
    private int iPrecision;
    private Vector2[] vPoints;
    public static final int PRECISION = 16;
    public boolean isBelowZero;
    public LittleAnimation littleAnimationMainLine;
    public LittleAnimation littleAnimationMovingArmy;
    
    public MoveUnits(final int nCivID, final int iFromProvinceID, final int iToProvinceID, final String key, final int extraArmyY, final boolean inRetreat, final boolean landOnly) {
        this.lRoute = new ArrayList<Integer>();
        this.iRouteSize = 0;
        this.doneMovementProgressWidth = 0.0f;
        this.currentMovementProgressWidth = 0.0f;
        this.movementProgressOverWidth = 0.0f;
        this.inBattle = false;
        this.inRetreat = false;
        this.iWidth = new ArrayList<Integer>();
        this.lMovingTime = 0L;
        this.fMovingPercentage = 0.0f;
        this.lCurrentMovingTime = 0L;
        this.fCurrentMovingPercentage = 0.0f;
        this.fSpeed = 0.0f;
        this.extraArmyY = 0;
        this.isBelowZero = false;
        this.key = key;
        this.extraArmyY = ProvinceDrawArmy.getArmyHeight() * extraArmyY + extraArmyY * 2;
        this.updateColorLine(nCivID, this.inRetreat = inRetreat);
        this.updateSpeed(nCivID, iFromProvinceID);
        this.buildRoute2(nCivID, iFromProvinceID, iToProvinceID, landOnly);
        if (this.iRouteSize > 1) {
            this.buildMoveUnitsLine(true, nCivID);
            final int armyID = Game.getProvince(iFromProvinceID).getArmyKeyID(key);
            if (armyID >= 0) {
                Game.getProvince(iFromProvinceID).getArmy(armyID).iShiftX = Game.getProvince(iFromProvinceID).getArmy(armyID).defaultShiftX();
                Game.getProvince(iFromProvinceID).getArmy(armyID).iShiftY = Game.getProvince(iFromProvinceID).getArmy(armyID).defaultShiftY();
            }
        }
    }
    
    public MoveUnits(final int nCivID, final int iFromProvinceID, final int iToProvinceID, final String key, final int extraArmyY, final int iFromProvinceIDExtra) {
        this.lRoute = new ArrayList<Integer>();
        this.iRouteSize = 0;
        this.doneMovementProgressWidth = 0.0f;
        this.currentMovementProgressWidth = 0.0f;
        this.movementProgressOverWidth = 0.0f;
        this.inBattle = false;
        this.inRetreat = false;
        this.iWidth = new ArrayList<Integer>();
        this.lMovingTime = 0L;
        this.fMovingPercentage = 0.0f;
        this.lCurrentMovingTime = 0L;
        this.fCurrentMovingPercentage = 0.0f;
        this.fSpeed = 0.0f;
        this.extraArmyY = 0;
        this.isBelowZero = false;
        this.key = key;
        this.extraArmyY = extraArmyY;
        this.updateColorLine(nCivID, this.inRetreat);
        this.updateSpeed(nCivID, iFromProvinceID);
        this.buildRoute2(nCivID, iFromProvinceID, iToProvinceID, false);
        if (this.iRouteSize > 1) {
            final List<Integer> tRoute = new ArrayList<Integer>();
            for (int i = 0; i < this.iRouteSize; ++i) {
                tRoute.add(this.lRoute.get(i));
            }
            this.lRoute.clear();
            this.lRoute.add(iFromProvinceIDExtra);
            for (int i = 0; i < this.iRouteSize; ++i) {
                this.lRoute.add(tRoute.get(i));
            }
            this.iRouteSize = this.lRoute.size();
            this.buildMoveUnitsLine_FromToTheSameProvince(true, nCivID);
        }
    }
    
    public void updateColorLine(final int nCivID, final boolean inRetreat) {
        if (inRetreat) {
            this.colorLine = Colors.HOVER_NEGATIVE;
        }
        else if (nCivID == Game.player.iCivID) {
            this.colorLine = Colors.HOVER_GOLD;
        }
        else {
            this.colorLine = CFG.getColorStep(Colors.HOVER_GOLD, Game.getCiv(nCivID).getColor(1.0f), 60, 100, 0.4f);
        }
    }
    
    public void updateSpeed(final int nCivID, final int iFromProvinceID) {
        final int tArmyID = Game.getProvince(iFromProvinceID).getArmyKeyID(this.key);
        this.fSpeed = GameValues.army.MIN_ARMY_MOVEMENT_SPEED;
        try {
            if (tArmyID >= 0) {
                this.fSpeed = Math.max(GameValues.army.MIN_ARMY_MOVEMENT_SPEED, Game.getProvince(iFromProvinceID).getArmy(tArmyID).getArmyMovementSpeed() * (this.inRetreat ? GameValues.army.RETREATING_ARMY_MOVEMENT_SPEED : 1.0f));
            }
            else {
                final Game.ArmyPos nArmyPos = Game.findArmy_FullCheck(nCivID, this.key);
                if (nArmyPos != null) {
                    this.fSpeed = Math.max(GameValues.army.MIN_ARMY_MOVEMENT_SPEED, Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).getArmyMovementSpeed() * (this.inRetreat ? GameValues.army.RETREATING_ARMY_MOVEMENT_SPEED : 1.0f));
                }
                else if (this.fSpeed <= GameValues.army.MIN_ARMY_MOVEMENT_SPEED) {
                    this.fSpeed = GameValues.army.MIN_ARMY_MOVEMENT_SPEED * (this.inRetreat ? GameValues.army.RETREATING_ARMY_MOVEMENT_SPEED : 1.0f);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            if (this.fSpeed <= GameValues.army.MIN_ARMY_MOVEMENT_SPEED) {
                this.fSpeed = GameValues.army.MIN_ARMY_MOVEMENT_SPEED * (this.inRetreat ? GameValues.army.RETREATING_ARMY_MOVEMENT_SPEED : 1.0f);
            }
        }
    }
    
    protected boolean buildRoute2(final int nCivID, final int fromProvinceID, final int toProvinceID, final boolean landOnly) {
        this.lRoute.clear();
        if (fromProvinceID < 0 || toProvinceID < 0 || Game.getProvince(toProvinceID).getWasteland() >= 0) {
            return false;
        }
        final PriorityQueue<Node> openSet = new PriorityQueue<Node>(Comparator.comparingDouble(Node::getFCost));
        final Map<Integer, Node> closedSet = new HashMap<Integer, Node>();
        final Node startNode = new Node(fromProvinceID, null, 0.0f, 0.0f);
        openSet.add(startNode);
        closedSet.put(fromProvinceID, startNode);
        while (!openSet.isEmpty()) {
            final Node currentNode = openSet.poll();
            if (currentNode.provinceID == toProvinceID) {
                this.setPath(fromProvinceID, toProvinceID, reconstructPath(currentNode), toProvinceID, fromProvinceID);
                return true;
            }
            for (final int neighbor : Game.getProvince(currentNode.provinceID).lNeighboringProvinces) {
                if (!closedSet.containsKey(neighbor)) {
                    if (canBeUsedInPath(nCivID, Game.getProvince(neighbor).getProvinceID(), this.inRetreat, fromProvinceID)) {
                        final float gCost = currentNode.gCost + Game.getManhattanDistance(currentNode.provinceID, neighbor);
                        final float hCost = Game.getManhattanDistance(neighbor, toProvinceID);
                        final float fCost = gCost + hCost;
                        final Node neighborNode = new Node(neighbor, currentNode, gCost, hCost);
                        if (!openSet.contains(neighborNode) || fCost < openSet.peek().getFCost()) {
                            if (openSet.contains(neighborNode)) {
                                openSet.remove(neighborNode);
                            }
                            openSet.add(neighborNode);
                        }
                        closedSet.put(neighbor, neighborNode);
                    }
                    else {
                        closedSet.put(neighbor, null);
                    }
                }
            }
            if (landOnly) {
                continue;
            }
            for (final int neighbor : Game.getProvince(currentNode.provinceID).lNeighboringSeaProvinces) {
                if (!closedSet.containsKey(neighbor)) {
                    final float gCost = currentNode.gCost + Game.getDistanceFromProvinceToProvince(currentNode.provinceID, neighbor);
                    final float hCost = Game.getDistanceFromProvinceToProvince(neighbor, toProvinceID);
                    final float fCost = gCost + hCost;
                    final Node neighborNode = new Node(neighbor, currentNode, gCost, hCost);
                    if (!openSet.contains(neighborNode)) {
                        openSet.add(neighborNode);
                    }
                    else if (fCost < openSet.peek().getFCost()) {
                        if (openSet.contains(neighborNode)) {
                            openSet.remove(neighborNode);
                        }
                        openSet.add(neighborNode);
                    }
                    closedSet.put(neighbor, neighborNode);
                }
            }
        }
        return false;
    }
    
    private static List<Integer> reconstructPath(Node node) {
        final List<Integer> path = new ArrayList<Integer>();
        while (node != null) {
            path.add(node.provinceID);
            node = node.parent;
        }
        Collections.reverse(path);
        path.remove(0);
        return path;
    }
    
    protected boolean buildRoute(final int nCivID, final int fromProvinceID, final int toProvinceID, final boolean landOnly) {
        this.lRoute.clear();
        if (fromProvinceID < 0 || toProvinceID < 0 || Game.getProvince(toProvinceID).getWasteland() >= 0) {
            return false;
        }
        final List<Integer> was = new ArrayList<Integer>();
        was.add(fromProvinceID);
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            this.setWas(i, false);
        }
        this.setWas(fromProvinceID, true);
        final List<Integer> in = new ArrayList<Integer>();
        final List<List<Integer>> inPath = new ArrayList<List<Integer>>();
        final Province provinceA = Game.getProvince(fromProvinceID);
        for (int j = 0; j < provinceA.getNeighboringProvincesSize(); ++j) {
            if (canBeUsedInPath(nCivID, provinceA.getNeighboringProvinces(j), this.inRetreat, fromProvinceID)) {
                in.add(Game.getProvince(provinceA.getNeighboringProvinces(j)).getProvinceID());
                final List<Integer> tP = new ArrayList<Integer>();
                tP.add(Game.getProvince(provinceA.getNeighboringProvinces(j)).getProvinceID());
                inPath.add(tP);
                was.add(Game.getProvince(provinceA.getNeighboringProvinces(j)).getProvinceID());
                this.setWas(Game.getProvince(provinceA.getNeighboringProvinces(j)).getProvinceID(), true);
            }
        }
        if (!landOnly && !provinceA.getSeaProvince()) {
            for (int j = 0; j < provinceA.getNeighboringSeaProvincesSize(); ++j) {
                in.add(Game.getProvince(provinceA.getNeighboringSeaProvinces(j)).getProvinceID());
                final List<Integer> tP = new ArrayList<Integer>();
                tP.add(Game.getProvince(provinceA.getNeighboringSeaProvinces(j)).getProvinceID());
                inPath.add(tP);
                was.add(Game.getProvince(provinceA.getNeighboringSeaProvinces(j)).getProvinceID());
                this.setWas(Game.getProvince(provinceA.getNeighboringSeaProvinces(j)).getProvinceID(), true);
            }
        }
        for (int j = 0; j < in.size(); ++j) {
            if (Game.getProvince(in.get(j)).getProvinceID() == toProvinceID) {
                this.setPath(fromProvinceID, toProvinceID, inPath.get(j), toProvinceID, fromProvinceID);
                this.clearWas(was);
                return true;
            }
        }
        this.buildPath(nCivID, was, in, inPath, fromProvinceID, toProvinceID, true, landOnly);
        return true;
    }
    
    protected boolean buildPath(final int civID, final List<Integer> was, final List<Integer> in, final List<List<Integer>> inPath, final int from, final int lookingFor, final boolean forDirection, final boolean landOnly) {
        final List<Integer> nIN = new ArrayList<Integer>();
        final List<List<Integer>> nINPath = new ArrayList<List<Integer>>();
        if (forDirection) {
            for (int i = 0; i < in.size(); ++i) {
                for (int j = 0; j < Game.getProvince(in.get(i)).getNeighboringProvincesSize(); ++j) {
                    if (!this.getWas(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID()) && canBeUsedInPath(civID, Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID(), this.inRetreat, from)) {
                        if (Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID() == lookingFor) {
                            this.setPath(from, lookingFor, inPath.get(i), lookingFor, from);
                            this.clearWas(was);
                            return true;
                        }
                        nIN.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID());
                        final List<Integer> tPL = new ArrayList<Integer>(inPath.get(i));
                        tPL.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID());
                        nINPath.add(tPL);
                        this.setWas(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID(), true);
                        was.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID());
                    }
                }
                if (!landOnly && !Game.getProvince(in.get(i)).getSeaProvince()) {
                    for (int j = 0; j < Game.getProvince(in.get(i)).getNeighboringSeaProvincesSize(); ++j) {
                        if (!this.getWas(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID())) {
                            if (Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID() == lookingFor) {
                                this.setPath(from, lookingFor, inPath.get(i), lookingFor, from);
                                this.clearWas(was);
                                return true;
                            }
                            nIN.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID());
                            final List<Integer> tPL = new ArrayList<Integer>(inPath.get(i));
                            tPL.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID());
                            nINPath.add(tPL);
                            this.setWas(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID(), true);
                            was.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID());
                        }
                    }
                }
            }
        }
        else {
            for (int i = 0; i < in.size(); ++i) {
                for (int j = Game.getProvince(in.get(i)).getNeighboringProvincesSize() - 1; j >= 0; --j) {
                    if (!this.getWas(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID()) && canBeUsedInPath(civID, Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID(), this.inRetreat, from)) {
                        if (Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID() == lookingFor) {
                            this.setPath(from, lookingFor, inPath.get(i), lookingFor, from);
                            this.clearWas(was);
                            return true;
                        }
                        nIN.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID());
                        final List<Integer> tPL = new ArrayList<Integer>(inPath.get(i));
                        tPL.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID());
                        nINPath.add(tPL);
                        this.setWas(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID(), true);
                        was.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringProvinces(j)).getProvinceID());
                    }
                }
                if (!landOnly && !Game.getProvince(in.get(i)).getSeaProvince()) {
                    for (int j = Game.getProvince(in.get(i)).getNeighboringSeaProvincesSize() - 1; j >= 0; --j) {
                        if (!this.getWas(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID())) {
                            if (Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID() == lookingFor) {
                                this.setPath(from, lookingFor, inPath.get(i), lookingFor, from);
                                this.clearWas(was);
                                return true;
                            }
                            nIN.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID());
                            final List<Integer> tPL = new ArrayList<Integer>(inPath.get(i));
                            tPL.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID());
                            nINPath.add(tPL);
                            this.setWas(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID(), true);
                            was.add(Game.getProvince(Game.getProvince(in.get(i)).getNeighboringSeaProvinces(j)).getProvinceID());
                        }
                    }
                }
            }
        }
        try {
            return this.buildPath(civID, was, nIN, nINPath, from, lookingFor, !forDirection, landOnly);
        }
        catch (final StackOverflowError ex) {
            this.clearWas(was);
            return false;
        }
    }
    
    protected final void clearWas(final List<Integer> was) {
        for (int i = was.size() - 1; i >= 0; --i) {
            this.setWas(was.get(i), true);
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
    
    public static final boolean isFriendlyProvince(final int civID, final int toProvinceID) {
        return GameValues.move.ENABLE_MOVE_UNITS_TO_ANY_PROVINCE || Game.getProvince(toProvinceID).getCivID() == civID || Game.getProvince(toProvinceID).getCivID() == 0 || Game.getProvince(toProvinceID).getSeaProvince() || Game.getCiv(civID).diplomacy.haveAlliance(Game.getProvince(toProvinceID).getCivID()) || Game.getCiv(civID).areInAllianceSpecial(Game.getProvince(toProvinceID).getCivID()) || Game.getCiv(civID).getPuppetOfCivID() == Game.getProvince(toProvinceID).getCivID() || Game.getCiv(Game.getProvince(toProvinceID).getCivID()).getPuppetOfCivID() == civID || Game.getCiv(Game.getProvince(toProvinceID).getCivID()).getPuppetOfCivID() == Game.getCiv(civID).getPuppetOfCivID() || Game.getCiv(civID).diplomacy.militaryAccess.containsKey(Game.getProvince(toProvinceID).getCivID()) || civID < 0;
    }
    
    public static final boolean isFriendlyProvince_OrAtWAr(final int civID, final int nProvinceID) {
        return isFriendlyProvince(civID, nProvinceID) || DiplomacyManager.isAtWar(civID, Game.getProvince(nProvinceID).getCivID());
    }
    
    public static boolean canBeUsedInPath(final int nCivID, final int nProvinceID, final boolean inRetreat, final int fromProvince) {
        return Game.getProvince(nProvinceID).getWasteland() < 0 && (inRetreat || isFriendlyProvince_OrAtWAr(nCivID, nProvinceID) || Game.getProvince(nProvinceID).getCivID() == Game.getProvince(fromProvince).getCivID());
    }
    
    public void update() {
        this.littleAnimationMainLine.update();
        this.littleAnimationMovingArmy.update();
        try {
            final int armyID = Game.getProvince(this.lRoute.get(0)).getArmyKeyID(this.key);
            if (armyID < 0) {
                return;
            }
            final Province provinceA = Game.getProvince(this.lRoute.get(0));
            final Province provinceB = Game.getProvince(this.lRoute.get(1));
            if ((provinceA.getBelowZero() || provinceB.getBelowZero()) && (!provinceA.getBelowZero() || !provinceB.getBelowZero())) {
                if (provinceA.getBelowZero()) {
                    if (provinceB.iCenterShiftX > Game.mapBG.getWidth() / 2) {
                        provinceA.getArmy(armyID).iShiftX_Scaled = (int)((provinceB.iCenterShiftX - Game.mapBG.getWidth() - provinceA.iCenterShiftX) * (this.doneMovementProgressWidth + (this.currentMovementProgressWidth - this.doneMovementProgressWidth) * this.fCurrentMovingPercentage) / this.iWidth.get(0));
                    }
                    else {
                        provinceA.getArmy(armyID).iShiftX_Scaled = (int)((provinceB.iCenterShiftX - provinceA.iCenterShiftX) * (this.doneMovementProgressWidth + (this.currentMovementProgressWidth - this.doneMovementProgressWidth) * this.fCurrentMovingPercentage) / this.iWidth.get(0));
                    }
                }
                else if (provinceA.iCenterShiftX > Game.mapBG.getWidth() / 2) {
                    provinceA.getArmy(armyID).iShiftX_Scaled = (int)((provinceB.iCenterShiftX + Game.mapBG.getWidth() - provinceA.iCenterShiftX) * (this.doneMovementProgressWidth + (this.currentMovementProgressWidth - this.doneMovementProgressWidth) * this.fCurrentMovingPercentage) / this.iWidth.get(0));
                }
                else {
                    provinceA.getArmy(armyID).iShiftX_Scaled = (int)((provinceB.iCenterShiftX - provinceA.iCenterShiftX) * (this.doneMovementProgressWidth + (this.currentMovementProgressWidth - this.doneMovementProgressWidth) * this.fCurrentMovingPercentage) / this.iWidth.get(0));
                }
            }
            else {
                provinceA.getArmy(armyID).iShiftX_Scaled = (int)((provinceB.iCenterShiftX - provinceA.iCenterShiftX) * (this.doneMovementProgressWidth + (this.currentMovementProgressWidth - this.doneMovementProgressWidth) * this.fCurrentMovingPercentage) / this.iWidth.get(0));
            }
            provinceA.getArmy(armyID).iShiftY_Scaled = (int)((provinceB.iCenterShiftY - provinceA.iCenterShiftY) * (this.doneMovementProgressWidth + (this.currentMovementProgressWidth - this.doneMovementProgressWidth) * this.fCurrentMovingPercentage) / this.iWidth.get(0)) + (int)(this.extraArmyY / Game.mapScale.getCurrentScale());
        }
        catch (final Exception ex) {}
    }
    
    public void draw(final SpriteBatch oSB, final float nScale) {
        try {
            final Array<Vector2> nPath = (Array<Vector2>)new Array();
            if (this.isBelowZero) {
                if (Game.getProvince(this.lRoute.get(0)).getDrawProvince()) {
                    for (int j = 0; j < (int)((this.iPrecision - 2) * this.fMovingPercentage); ++j) {
                        nPath.add((Object)new Vector2((this.vPoints[j].x + Game.getProvince(this.lRoute.get(0)).getTranslateProvincePosX() + Game.mapBG.getWidth()) * nScale, (this.vPoints[j].y - Game.mapCoords.getPosY()) * nScale));
                    }
                }
                else {
                    for (int j = 0; j < (int)(this.iPrecision * this.fMovingPercentage); ++j) {
                        nPath.add((Object)new Vector2((this.vPoints[j].x + Game.mapCoords.getPosX() + Game.mapBG.getWidth()) * nScale, (this.vPoints[j].y - Game.mapCoords.getPosY()) * nScale));
                    }
                }
                Renderer.shapeDrawer.setColor(new Color(this.colorLine.r, this.colorLine.g, this.colorLine.b, 0.4f));
                Renderer.shapeDrawer.path((Iterable)nPath, 3.0f * (0.25f + 0.75f * this.fMovingPercentage), JoinType.SMOOTH, true);
                nPath.clear();
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
                Renderer.shapeDrawer.setColor(new Color(this.colorLine.r, this.colorLine.g, this.colorLine.b, 0.4f));
                Renderer.shapeDrawer.path((Iterable)nPath, 3.0f * (0.25f + 0.75f * this.fMovingPercentage), JoinType.SMOOTH, true);
            }
            else {
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
                Renderer.shapeDrawer.setColor(new Color(this.colorLine.r, this.colorLine.g, this.colorLine.b, 0.4f));
                Renderer.shapeDrawer.path((Iterable)nPath, 3.0f * (0.25f + 0.75f * this.fMovingPercentage), JoinType.SMOOTH, true);
            }
        }
        catch (final Exception ex) {}
    }
    
    public void draw_Ally(final SpriteBatch oSB, final float nScale) {
        try {
            final Array<Vector2> nPath = (Array<Vector2>)new Array();
            if (this.isBelowZero) {
                if (Game.getProvince(this.lRoute.get(0)).getDrawProvince()) {
                    for (int j = 0; j < (int)((this.iPrecision - 2) * this.fMovingPercentage); ++j) {
                        nPath.add((Object)new Vector2((this.vPoints[j].x + Game.getProvince(this.lRoute.get(0)).getTranslateProvincePosX() + Game.mapBG.getWidth()) * nScale, (this.vPoints[j].y - Game.mapCoords.getPosY()) * nScale));
                    }
                }
                else {
                    for (int j = 0; j < (int)(this.iPrecision * this.fMovingPercentage); ++j) {
                        nPath.add((Object)new Vector2((this.vPoints[j].x + Game.mapCoords.getPosX() + Game.mapBG.getWidth()) * nScale, (this.vPoints[j].y - Game.mapCoords.getPosY()) * nScale));
                    }
                }
                Renderer.shapeDrawer.setColor(new Color(this.colorLine.r, this.colorLine.g, this.colorLine.b, 0.4f));
                Renderer.shapeDrawer.path((Iterable)nPath, 2.0f * (0.25f + 0.75f * this.fMovingPercentage), JoinType.SMOOTH, true);
                nPath.clear();
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
                Renderer.shapeDrawer.setColor(new Color(this.colorLine.r, this.colorLine.g, this.colorLine.b, 0.4f));
                Renderer.shapeDrawer.path((Iterable)nPath, 2.0f * (0.25f + 0.75f * this.fMovingPercentage), JoinType.SMOOTH, true);
            }
            else {
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
                Renderer.shapeDrawer.setColor(new Color(this.colorLine.r, this.colorLine.g, this.colorLine.b, 0.4f));
                Renderer.shapeDrawer.path((Iterable)nPath, 2.0f * (0.25f + 0.75f * this.fMovingPercentage), JoinType.SMOOTH, true);
            }
        }
        catch (final Exception ex) {}
    }
    
    public final void updateToNextProvince(final int civID) {
        this.lRoute.remove(0);
        this.iRouteSize = this.lRoute.size();
        this.buildMoveUnitsLine(false, civID);
    }
    
    public final void buildMoveUnitsLine(final boolean updateAnimation, final int civID) {
        this.iWidth.clear();
        this.isBelowZero = false;
        this.buildWidth();
        if (updateAnimation) {
            this.lMovingTime = CFG.currentTimeMillis;
            this.fMovingPercentage = 0.01f;
            this.littleAnimationMainLine = new LittleAnimation() {
                @Override
                public void update() {
                    final MoveUnits this$0 = MoveUnits.this;
                    this$0.fMovingPercentage += (CFG.currentTimeMillis - MoveUnits.this.lMovingTime) / GameValues.inGame.MOVE_UNITS_ANIMATION_DURATION;
                    MoveUnits.this.lMovingTime = CFG.currentTimeMillis;
                    if (MoveUnits.this.fMovingPercentage >= 1.0f) {
                        MoveUnits.this.fMovingPercentage = 1.0f;
                        MoveUnits.this.littleAnimationMainLine = new LittleAnimation() {
                            @Override
                            public void update() {
                            }
                        };
                    }
                }
            };
        }
        this.updateLittleAnimationMovingArmy();
        this.buildLine(civID);
    }
    
    public final void buildLine(final int civID) {
        if (civID == Game.player.iCivID || Game.getCiv(civID).isPlayerAlly) {
            this.iPrecision = 16 * this.iRouteSize;
            this.vPoints = new Vector2[this.iPrecision];
            Vector2[] dataSet = new Vector2[this.iRouteSize + 2];
            for (int i = 0; i < this.iRouteSize; ++i) {
                if (Game.getProvince(this.lRoute.get(i)).getBelowZero()) {
                    this.isBelowZero = true;
                    break;
                }
            }
            if (this.isBelowZero) {
                for (int i = 0; i < this.iRouteSize; ++i) {
                    dataSet[i + 1] = new Vector2((float)(Game.getProvince(this.lRoute.get(i)).iCenterShiftX + ((!Game.getProvince(this.lRoute.get(i)).getBelowZero() && Game.getProvince(this.lRoute.get(i)).iCenterShiftX > Game.mapBG.getWidth() / 2) ? (-Game.mapBG.getWidth()) : 0)), (float)(-Game.getProvince(this.lRoute.get(i)).iCenterShiftY));
                }
                dataSet[0] = new Vector2((float)(Game.getProvince(this.lRoute.get(0)).iCenterShiftX + ((!Game.getProvince(this.lRoute.get(0)).getBelowZero() && Game.getProvince(this.lRoute.get(0)).iCenterShiftX > Game.mapBG.getWidth() / 2) ? (-Game.mapBG.getWidth()) : 0)), (float)(-Game.getProvince(this.lRoute.get(0)).iCenterShiftY));
                dataSet[this.iRouteSize + 1] = new Vector2((float)(Game.getProvince(this.lRoute.get(this.iRouteSize - 1)).iCenterShiftX + ((!Game.getProvince(this.lRoute.get(this.iRouteSize - 1)).getBelowZero() && Game.getProvince(this.lRoute.get(this.iRouteSize - 1)).iCenterShiftX > Game.mapBG.getWidth() / 2) ? (-Game.mapBG.getWidth()) : 0)), (float)(-Game.getProvince(this.lRoute.get(this.iRouteSize - 1)).iCenterShiftY));
            }
            else {
                for (int i = 0; i < this.iRouteSize; ++i) {
                    dataSet[i + 1] = new Vector2((float)Game.getProvince(this.lRoute.get(i)).iCenterShiftX, (float)(-Game.getProvince(this.lRoute.get(i)).iCenterShiftY));
                }
                dataSet[0] = new Vector2((float)Game.getProvince(this.lRoute.get(0)).iCenterShiftX, (float)(-Game.getProvince(this.lRoute.get(0)).iCenterShiftY));
                dataSet[this.iRouteSize + 1] = new Vector2((float)Game.getProvince(this.lRoute.get(this.iRouteSize - 1)).iCenterShiftX, (float)(-Game.getProvince(this.lRoute.get(this.iRouteSize - 1)).iCenterShiftY));
            }
            final CatmullRomSpline<Vector2> oCatmull = (CatmullRomSpline<Vector2>)new CatmullRomSpline((Vector[])dataSet, false);
            for (int j = 0; j < this.iPrecision; ++j) {
                oCatmull.valueAt((Vector)(this.vPoints[j] = new Vector2()), j / (this.iPrecision - 1.0f));
            }
            dataSet = null;
        }
    }
    
    public final void buildWidth() {
        try {
            for (int i = 0; i < this.iRouteSize - 1; ++i) {
                final Province provinceA = Game.getProvince(this.lRoute.get(i));
                final Province provinceB = Game.getProvince(this.lRoute.get(i + 1));
                this.iWidth.add(Math.max(1, (int)Math.ceil(Math.sqrt((provinceB.iCenterShiftX - provinceA.iCenterShiftX) * (provinceB.iCenterShiftX - provinceA.iCenterShiftX) + (provinceA.iCenterShiftY - provinceB.iCenterShiftY) * (provinceA.iCenterShiftY - provinceB.iCenterShiftY))) / Game.mapBG.iMapScale));
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void buildMoveUnitsLine_FromToTheSameProvince(final boolean updateAnimation, final int civID) {
        this.iWidth.clear();
        this.isBelowZero = false;
        this.buildWidth();
        if (updateAnimation) {
            this.lMovingTime = CFG.currentTimeMillis;
            this.fMovingPercentage = 0.01f;
            this.littleAnimationMainLine = new LittleAnimation() {
                @Override
                public void update() {
                    final MoveUnits this$0 = MoveUnits.this;
                    this$0.fMovingPercentage += (CFG.currentTimeMillis - MoveUnits.this.lMovingTime) / GameValues.inGame.MOVE_UNITS_ANIMATION_DURATION;
                    MoveUnits.this.lMovingTime = CFG.currentTimeMillis;
                    if (MoveUnits.this.fMovingPercentage >= 1.0f) {
                        MoveUnits.this.fMovingPercentage = 1.0f;
                        MoveUnits.this.littleAnimationMainLine = new LittleAnimation() {
                            @Override
                            public void update() {
                            }
                        };
                    }
                }
            };
        }
        this.updateLittleAnimationMovingArmy();
        this.buildLine(civID);
    }
    
    public void updateLittleAnimationMovingArmy() {
        this.lCurrentMovingTime = CFG.currentTimeMillis;
        this.fCurrentMovingPercentage = 0.01f;
        this.littleAnimationMovingArmy = new LittleAnimation() {
            @Override
            public void update() {
                final MoveUnits this$0 = MoveUnits.this;
                this$0.fCurrentMovingPercentage += (CFG.currentTimeMillis - MoveUnits.this.lCurrentMovingTime) / (float)Game.gameThread.playSpeedTIME;
                MoveUnits.this.lCurrentMovingTime = CFG.currentTimeMillis;
                if (MoveUnits.this.fCurrentMovingPercentage >= 1.0f) {
                    MoveUnits.this.fCurrentMovingPercentage = 1.0f;
                    MoveUnits.this.littleAnimationMovingArmy = new LittleAnimation() {
                        @Override
                        public void update() {
                        }
                    };
                }
            }
        };
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
    
    public final float getProgressPerc() {
        return this.currentMovementProgressWidth / this.iWidth.get(0);
    }
    
    public int getWidthTotal() {
        int out = 0;
        for (int i = this.iWidth.size() - 1; i >= 0; --i) {
            out += this.iWidth.get(i);
        }
        return out;
    }
    
    public boolean haveSeaProvince() {
        for (int i = 0; i < this.iRouteSize; ++i) {
            if (Game.getProvince(this.lRoute.get(i)).getSeaProvince()) {
                return true;
            }
        }
        return false;
    }
    
    public boolean getWas(final int nProvinceID) {
        return Game.getProvince(nProvinceID).was;
    }
    
    public void setWas(final int nProvinceID, final boolean nWas) {
        Game.getProvince(nProvinceID).was = nWas;
    }
    
    public class Node
    {
        int provinceID;
        Node parent;
        float gCost;
        float hCost;
        
        public Node(final int provinceID, final Node parent, final float gCost, final float hCost) {
            this.provinceID = provinceID;
            this.parent = parent;
            this.gCost = gCost;
            this.hCost = hCost;
        }
        
        public float getFCost() {
            return this.gCost + this.hCost;
        }
    }
    
    public interface LittleAnimation
    {
        void update();
    }
}
