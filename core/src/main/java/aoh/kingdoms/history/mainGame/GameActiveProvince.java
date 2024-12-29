// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski;

import aoc.kingdoms.lukasz.map.province.ProvinceBorderManager;
import java.util.Iterator;
import java.util.List;
import aoc.kingdoms.lukasz.map.army.ArmyDivision;
import aoc.kingdoms.lukasz.jakowski.desktop.DesktopLauncher;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Function;
import aoc.kingdoms.lukasz.map.army.ArmyRegiment;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.menusInGame.Province.InGame_ProvinceArmy;
import aoc.kingdoms.lukasz.map.province.ProvinceTouchExtraAction;
import aoc.kingdoms.lukasz.map.map.MapMode;

public class GameActiveProvince
{
    public final void actionUp_setActiveProvinceID(final int nPosX, final int nPosY, final int nPointer, final int button) {
        if (!Game.mapScale.getScaleMode() && Game.mapTouchManager.actionDownPosX + CFG.PADDING * CFG.DENSITY > nPosX && Game.mapTouchManager.actionDownPosX - CFG.PADDING * CFG.DENSITY < nPosX && Game.mapTouchManager.actionDownPosY + CFG.PADDING * CFG.DENSITY > nPosY && Game.mapTouchManager.actionDownPosY - CFG.PADDING * CFG.DENSITY < nPosY) {
            this.actionUp_setActiveProvinceID2(nPosX, nPosY, nPointer, button);
            if (!CFG.brushTool) {
                Game.mapModes.lMapModes.get(Game.mapModes.iActiveMapModeID).playSFX_ProvinceClick();
            }
            try {
                ProvinceTouchExtraAction.actionUp_SetActiveProvinceID_ExtraAction.extraAction(nPosX, nPosY, nPointer, button);
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
    }
    
    private final void actionUp_setActiveProvinceID2(int nPosX, int nPosY, final int nPointer, final int button) {
        nPosX /= (int)Game.mapScale.getCurrentScale();
        nPosY /= (int)Game.mapScale.getCurrentScale();
        if (!Game.regroupArmyMode) {
            if (!Game.invasionArmyMode) {
                if (Game.chooseProvinceMode) {
                    Game.chooseProvinceMode = false;
                    InGame_ProvinceArmy.chooseProvinceExtraY = 0;
                    int tPosX = 0;
                    for (int i4 = 0; i4 < Game.getProvincesSize(); ++i4) {
                        tPosX = (int)Math.abs(Game.checkPosOfClickX((float)(Game.mapCoords.getPosX() - nPosX)));
                        if (Game.getProvince(i4).getMinX() <= tPosX && Game.getProvince(i4).getMaxX() >= tPosX && Game.getProvince(i4).getMinY() <= -Game.mapCoords.getPosY() + nPosY && Game.getProvince(i4).getMaxY() >= -Game.mapCoords.getPosY() + nPosY && Game.pathContains(i4, tPosX, -Game.mapCoords.getPosY() + nPosY)) {
                            this.moveActiveArmiesToProvinceID(i4);
                            return;
                        }
                    }
                    if (Math.abs(Game.checkPosOfClickX((float)(Game.mapCoords.getPosX() - nPosX))) + -Game.MAX_BELOW_ZERO_POINT_X * Game.mapBG.iMapScale / Game.mapScale.getCurrentScale() > Game.mapBG.getWidth()) {
                        tPosX = 0;
                        for (int i4 = 0; i4 < Game.getProvincesSize(); ++i4) {
                            if (Game.getProvince(i4).getBelowZero()) {
                                tPosX = (int)Math.abs(Game.checkPosOfClickX((float)(Game.mapCoords.getPosX() - nPosX)));
                                if (Game.getProvince(i4).getMinX() <= tPosX - Game.mapBG.getWidth() && Game.getProvince(i4).getMaxX() >= tPosX - Game.mapBG.getWidth() && Game.getProvince(i4).getMinY() <= -Game.mapCoords.getPosY() + nPosY && Game.getProvince(i4).getMaxY() >= -Game.mapCoords.getPosY() + nPosY) {
                                    if (Game.pathContains(i4, tPosX - Game.mapBG.getWidth(), -Game.mapCoords.getPosY() + nPosY)) {
                                        this.moveActiveArmiesToProvinceID(i4);
                                        return;
                                    }
                                }
                            }
                        }
                    }
                    return;
                }
                int tPosX2 = 0;
                for (int j = 0; j < Game.getProvincesSize(); ++j) {
                    tPosX2 = (int)Math.abs(Game.checkPosOfClickX((float)(Game.mapCoords.getPosX() - nPosX)));
                    if (Game.getProvince(j).getMinX() <= tPosX2 && Game.getProvince(j).getMaxX() >= tPosX2 && Game.getProvince(j).getMinY() <= -Game.mapCoords.getPosY() + nPosY && Game.getProvince(j).getMaxY() >= -Game.mapCoords.getPosY() + nPosY && Game.pathContains(j, tPosX2, -Game.mapCoords.getPosY() + nPosY)) {
                        if (j != Game.iActiveProvince) {
                            this.resetLastActiveProvince();
                            this.setActiveProvinceID(j);
                        }
                        else {
                            Game.iOldActiveProvinceID = Game.iActiveProvince;
                        }
                        return;
                    }
                }
                if (Math.abs(Game.checkPosOfClickX((float)(Game.mapCoords.getPosX() - nPosX))) + -Game.MAX_BELOW_ZERO_POINT_X * Game.mapBG.iMapScale / Game.mapScale.getCurrentScale() > Game.mapBG.getWidth()) {
                    tPosX2 = 0;
                    for (int j = 0; j < Game.getProvincesSize(); ++j) {
                        if (Game.getProvince(j).getBelowZero()) {
                            tPosX2 = (int)Math.abs(Game.checkPosOfClickX((float)(Game.mapCoords.getPosX() - nPosX)));
                            if (Game.getProvince(j).getMinX() <= tPosX2 - Game.mapBG.getWidth() && Game.getProvince(j).getMaxX() >= tPosX2 - Game.mapBG.getWidth() && Game.getProvince(j).getMinY() <= -Game.mapCoords.getPosY() + nPosY && Game.getProvince(j).getMaxY() >= -Game.mapCoords.getPosY() + nPosY) {
                                if (Game.pathContains(j, tPosX2 - Game.mapBG.getWidth(), -Game.mapCoords.getPosY() + nPosY)) {
                                    if (j != Game.iActiveProvince) {
                                        this.resetLastActiveProvince();
                                        this.setActiveProvinceID(j);
                                    }
                                    else {
                                        Game.iOldActiveProvinceID = Game.iActiveProvince;
                                    }
                                    return;
                                }
                            }
                        }
                    }
                }
            }
            else {
                int tPosX = 0;
                for (int i5 = 0; i5 < Game.getProvincesSize(); ++i5) {
                    tPosX = (int)Math.abs(Game.checkPosOfClickX((float)(Game.mapCoords.getPosX() - nPosX)));
                    if (Game.getProvince(i5).getMinX() <= tPosX && Game.getProvince(i5).getMaxX() >= tPosX && Game.getProvince(i5).getMinY() <= -Game.mapCoords.getPosY() + nPosY && Game.getProvince(i5).getMaxY() >= -Game.mapCoords.getPosY() + nPosY && Game.pathContains(i5, tPosX, -Game.mapCoords.getPosY() + nPosY)) {
                        Game.invasionArmy_AddProvince(i5);
                        return;
                    }
                }
                if (Math.abs(Game.checkPosOfClickX((float)(Game.mapCoords.getPosX() - nPosX))) + -Game.MAX_BELOW_ZERO_POINT_X * Game.mapBG.iMapScale / Game.mapScale.getCurrentScale() > Game.mapBG.getWidth()) {
                    tPosX = 0;
                    for (int i5 = 0; i5 < Game.getProvincesSize(); ++i5) {
                        if (Game.getProvince(i5).getBelowZero()) {
                            tPosX = (int)Math.abs(Game.checkPosOfClickX((float)(Game.mapCoords.getPosX() - nPosX)));
                            if (Game.getProvince(i5).getMinX() <= tPosX - Game.mapBG.getWidth() && Game.getProvince(i5).getMaxX() >= tPosX - Game.mapBG.getWidth() && Game.getProvince(i5).getMinY() <= -Game.mapCoords.getPosY() + nPosY && Game.getProvince(i5).getMaxY() >= -Game.mapCoords.getPosY() + nPosY) {
                                if (Game.pathContains(i5, tPosX - Game.mapBG.getWidth(), -Game.mapCoords.getPosY() + nPosY)) {
                                    Game.invasionArmy_AddProvince(i5);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
        else {
            int tPosX3 = 0;
            for (int i6 = 0; i6 < Game.getProvincesSize(); ++i6) {
                tPosX3 = (int)Math.abs(Game.checkPosOfClickX((float)(Game.mapCoords.getPosX() - nPosX)));
                if (Game.getProvince(i6).getMinX() <= tPosX3 && Game.getProvince(i6).getMaxX() >= tPosX3 && Game.getProvince(i6).getMinY() <= -Game.mapCoords.getPosY() + nPosY && Game.getProvince(i6).getMaxY() >= -Game.mapCoords.getPosY() + nPosY && Game.pathContains(i6, tPosX3, -Game.mapCoords.getPosY() + nPosY)) {
                    Game.regroupArmy_AddProvince(i6);
                    return;
                }
            }
            if (Math.abs(Game.checkPosOfClickX((float)(Game.mapCoords.getPosX() - nPosX))) + -Game.MAX_BELOW_ZERO_POINT_X * Game.mapBG.iMapScale / Game.mapScale.getCurrentScale() > Game.mapBG.getWidth()) {
                tPosX3 = 0;
                for (int i6 = 0; i6 < Game.getProvincesSize(); ++i6) {
                    if (Game.getProvince(i6).getBelowZero()) {
                        tPosX3 = (int)Math.abs(Game.checkPosOfClickX((float)(Game.mapCoords.getPosX() - nPosX)));
                        if (Game.getProvince(i6).getMinX() <= tPosX3 - Game.mapBG.getWidth() && Game.getProvince(i6).getMaxX() >= tPosX3 - Game.mapBG.getWidth() && Game.getProvince(i6).getMinY() <= -Game.mapCoords.getPosY() + nPosY && Game.getProvince(i6).getMaxY() >= -Game.mapCoords.getPosY() + nPosY) {
                            if (Game.pathContains(i6, tPosX3 - Game.mapBG.getWidth(), -Game.mapCoords.getPosY() + nPosY)) {
                                Game.regroupArmy_AddProvince(i6);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }
    
    public final void moveActiveArmiesToProvinceID(final int toProvinceID) {
        int extraArmyY = 0;
        for (int i = 0; i < Game.activeArmySize; ++i) {
            if (toProvinceID != Game.activeArmy.get(i).iProvinceID) {
                extraArmyY = 0;
                for (int j = 0; j < i; ++j) {
                    if (Game.activeArmy.get(j).iProvinceID == Game.activeArmy.get(i).iProvinceID) {
                        ++extraArmyY;
                    }
                }
                Game.player.playerData.invasion.removeInvasion(Game.activeArmy.get(i).key);
                if (GameValues.move.PLAYER_CAN_MOVE_ALL_ARMIES || (Game.SPECTATOR_MODE && Game.SANDBOX)) {
                    if (Game.activeArmy.get(i).iCivID < 0) {
                        Game.revolutionMoveUnits.newMove(Game.activeArmy.get(i).iProvinceID, toProvinceID, Game.activeArmy.get(i).key, extraArmyY, false);
                    }
                    else {
                        Game.getCiv(Game.activeArmy.get(i).iCivID).newMove(Game.activeArmy.get(i).iProvinceID, toProvinceID, Game.activeArmy.get(i).key, extraArmyY, false);
                        final ArmyDivision army = Game.getProvince(Game.activeArmy.get(i).iProvinceID).getArmy(Game.getProvince(Game.activeArmy.get(i).iProvinceID).getArmyKeyID(Game.activeArmy.get(i).key));
                        final List<ArmyRegiment> lArmyRegiment = army.lArmyRegiment;
                        final ArrayList<String> list = new ArrayList<String>();
                        for (final ArmyRegiment regiment : army.lArmyRegiment) {
                            final int uID = regiment.uID;
                            final int aID = regiment.aID;
                            final int num = regiment.num;
                            final float mo = regiment.mo;
                            final String key = regiment.key;
                            list.add(uID + " " + aID + " " + num + " " + mo + " " + key + " " + army.lArmyRegiment.size() + " " + Game.activeArmy.get(i).key + " " + Game.activeArmy.get(i).iCivID + " " + Game.activeArmy.get(i).iProvinceID);
                        }
                        final Object listString = list.stream().map((Function<? super Object, ?>)String::valueOf).collect((Collector<? super Object, ?, String>)Collectors.joining(","));
                        DesktopLauncher.SMS("DELETE = ARMY: " + Game.activeArmy.get(i).key + " " + Game.activeArmy.get(i).iCivID + " " + Game.activeArmy.get(i).iProvinceID);
                        DesktopLauncher.SMS("CR: " + (String)listString);
                        DesktopLauncher.SMS("Move: " + Game.activeArmy.get(i).iCivID + " " + Game.activeArmy.get(i).iProvinceID + " " + toProvinceID + " " + extraArmyY + " " + Game.activeArmy.get(i).key);
                    }
                }
                else if (Game.activeArmy.get(i).iCivID == Game.player.iCivID || (GameValues.move.PLAYER_CAN_MOVE_VASSALS_ARMIES && Game.getCiv(Game.activeArmy.get(i).iCivID).getPuppetOfCivID() == Game.player.iCivID)) {
                    Game.getCiv(Game.activeArmy.get(i).iCivID).newMove(Game.activeArmy.get(i).iProvinceID, toProvinceID, Game.activeArmy.get(i).key, extraArmyY, false);
                    final ArmyDivision army = Game.getProvince(Game.activeArmy.get(i).iProvinceID).getArmy(Game.getProvince(Game.activeArmy.get(i).iProvinceID).getArmyKeyID(Game.activeArmy.get(i).key));
                    final List<ArmyRegiment> lArmyRegiment = army.lArmyRegiment;
                    final ArrayList<String> list = new ArrayList<String>();
                    for (final ArmyRegiment regiment : army.lArmyRegiment) {
                        final int uID = regiment.uID;
                        final int aID = regiment.aID;
                        final int num = regiment.num;
                        final float mo = regiment.mo;
                        final String key = regiment.key;
                        list.add(uID + " " + aID + " " + num + " " + mo + " " + key + " " + army.lArmyRegiment.size() + " " + Game.activeArmy.get(i).key + " " + Game.activeArmy.get(i).iCivID + " " + Game.activeArmy.get(i).iProvinceID);
                    }
                    final Object listString = list.stream().map((Function<? super Object, ?>)String::valueOf).collect((Collector<? super Object, ?, String>)Collectors.joining(","));
                    DesktopLauncher.SMS("DELETE = ARMY: " + Game.activeArmy.get(i).key + " " + Game.activeArmy.get(i).iCivID + " " + Game.activeArmy.get(i).iProvinceID);
                    DesktopLauncher.SMS("CR: " + (String)listString);
                    DesktopLauncher.SMS("Move: " + Game.activeArmy.get(i).iCivID + " " + Game.activeArmy.get(i).iProvinceID + " " + toProvinceID + " " + extraArmyY + " " + Game.activeArmy.get(i).key);
                }
                Game.soundsManager.playMove();
                if (Game.menuManager.getVisibleInGame_ProvinceArmy()) {
                    Game.menuManager.rebuildInGame_ProvinceArmy(true, true);
                }
            }
            else {
                Game.player.playerData.invasion.removeInvasion(Game.activeArmy.get(i).key);
                Game.getCiv(Game.activeArmy.get(i).iCivID).cancelMove(Game.activeArmy.get(i).key);
                if (Game.menuManager.getVisibleInGame_ProvinceArmy()) {
                    Game.menuManager.rebuildInGame_ProvinceArmy(true, true);
                }
            }
        }
    }
    
    public final int mouseOverProvinceID(int nPosX, int nPosY) {
        nPosX /= (int)Game.mapScale.getCurrentScale();
        nPosY /= (int)Game.mapScale.getCurrentScale();
        int tPosX = 0;
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            tPosX = (int)Math.abs(Game.checkPosOfClickX((float)(Game.mapCoords.getPosX() - nPosX)));
            if (Game.getProvince(i).getMinX() <= tPosX && Game.getProvince(i).getMaxX() >= tPosX && Game.getProvince(i).getMinY() <= -Game.mapCoords.getPosY() + nPosY && Game.getProvince(i).getMaxY() >= -Game.mapCoords.getPosY() + nPosY && Game.pathContains(i, tPosX, -Game.mapCoords.getPosY() + nPosY)) {
                return i;
            }
        }
        if (Math.abs(Game.checkPosOfClickX((float)(Game.mapCoords.getPosX() - nPosX))) + -Game.MAX_BELOW_ZERO_POINT_X * Game.mapBG.iMapScale / Game.mapScale.getCurrentScale() > Game.mapBG.getWidth()) {
            tPosX = 0;
            for (int i = 0; i < Game.getProvincesSize(); ++i) {
                if (Game.getProvince(i).getBelowZero()) {
                    tPosX = (int)Math.abs(Game.checkPosOfClickX((float)(Game.mapCoords.getPosX() - nPosX)));
                    if (Game.getProvince(i).getMinX() <= tPosX - Game.mapBG.getWidth() && Game.getProvince(i).getMaxX() >= tPosX - Game.mapBG.getWidth() && Game.getProvince(i).getMinY() <= -Game.mapCoords.getPosY() + nPosY && Game.getProvince(i).getMaxY() >= -Game.mapCoords.getPosY() + nPosY) {
                        if (Game.pathContains(i, tPosX - Game.mapBG.getWidth(), -Game.mapCoords.getPosY() + nPosY)) {
                            return i;
                        }
                    }
                }
            }
        }
        return -1;
    }
    
    public final void setActiveProvinceID(final int nActiveProvinceID) {
        try {
            Game.iOldActiveProvinceID = Game.iActiveProvince;
            Game.setActiveProvinceID(nActiveProvinceID);
            Game.activeProvince_Animation_Data.resetAnimationData();
            Game.clearActiveArmy();
            if (nActiveProvinceID >= 0 && Game.getProvince(nActiveProvinceID).getSeaProvince()) {
                for (int i = 0; i < Game.getProvince(nActiveProvinceID).getArmySize(); ++i) {
                    if (Game.getProvince(nActiveProvinceID).getArmy(i).civID == Game.player.iCivID) {
                        final Game.HoveredArmy nHA = new Game.HoveredArmy();
                        nHA.iProvinceID = nActiveProvinceID;
                        nHA.key = Game.getProvince(nActiveProvinceID).getArmy(i).key;
                        nHA.iArmyID = i;
                        nHA.iCivID = Game.getProvince(nActiveProvinceID).getArmy(i).civID;
                        Game.addActiveArmy(nHA);
                    }
                }
                if (Game.activeArmySize > 0) {
                    Game.setActiveProvinceID(-1);
                    Game.menuManager.rebuildInGame_ProvinceArmy();
                    return;
                }
            }
            ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
        }
        catch (final NullPointerException ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void resetLastActiveProvince() {
        ProvinceBorderManager.action.resetProvinceID();
    }
    
    public final void setProvinceID_PPM(final int nPosX, final int nPosY) {
        Game.animationManager.clickAnimation(nPosX, nPosY);
        final int nNewChosenProvinceID = Game.setProvinceID_HoverAProvince((int)(nPosX / Game.mapScale.getCurrentScale()), (int)(nPosY / Game.mapScale.getCurrentScale()));
        if (nNewChosenProvinceID >= 0) {
            if (Game.regroupArmyMode) {
                Game.regroupArmy_RemoveProvince(nNewChosenProvinceID);
            }
            else if (Game.invasionArmyMode) {
                Game.invasionArmy_RemoveProvince(nNewChosenProvinceID);
            }
            else {
                int extraArmyY = 0;
                for (int i = 0; i < Game.activeArmySize; ++i) {
                    if (nNewChosenProvinceID != Game.activeArmy.get(i).iProvinceID) {
                        extraArmyY = 0;
                        for (int j = 0; j < i; ++j) {
                            if (Game.activeArmy.get(j).iProvinceID == Game.activeArmy.get(i).iProvinceID) {
                                ++extraArmyY;
                            }
                        }
                        Game.player.playerData.invasion.removeInvasion(Game.activeArmy.get(i).key);
                        if (GameValues.move.PLAYER_CAN_MOVE_ALL_ARMIES || (Game.SPECTATOR_MODE && Game.SANDBOX)) {
                            if (Game.activeArmy.get(i).iCivID < 0) {
                                Game.revolutionMoveUnits.newMove(Game.activeArmy.get(i).iProvinceID, nNewChosenProvinceID, Game.activeArmy.get(i).key, extraArmyY, false);
                            }
                            else {
                                Game.getCiv(Game.activeArmy.get(i).iCivID).newMove(Game.activeArmy.get(i).iProvinceID, nNewChosenProvinceID, Game.activeArmy.get(i).key, extraArmyY, false);
                                final ArmyDivision army = Game.getProvince(Game.activeArmy.get(i).iProvinceID).getArmy(Game.getProvince(Game.activeArmy.get(i).iProvinceID).getArmyKeyID(Game.activeArmy.get(i).key));
                                final List<ArmyRegiment> lArmyRegiment = army.lArmyRegiment;
                                final ArrayList<String> list = new ArrayList<String>();
                                for (final ArmyRegiment regiment : army.lArmyRegiment) {
                                    final int uID = regiment.uID;
                                    final int aID = regiment.aID;
                                    final int num = regiment.num;
                                    final float mo = regiment.mo;
                                    final String key = regiment.key;
                                    list.add(uID + " " + aID + " " + num + " " + mo + " " + key + " " + army.lArmyRegiment.size() + " " + Game.activeArmy.get(i).key + " " + Game.activeArmy.get(i).iCivID + " " + Game.activeArmy.get(i).iProvinceID);
                                }
                                final Object listString = list.stream().map((Function<? super Object, ?>)String::valueOf).collect((Collector<? super Object, ?, String>)Collectors.joining(","));
                                DesktopLauncher.SMS("DELETE = ARMY: " + Game.activeArmy.get(i).key + " " + Game.activeArmy.get(i).iCivID + " " + Game.activeArmy.get(i).iProvinceID);
                                DesktopLauncher.SMS("CR: " + (String)listString);
                                DesktopLauncher.SMS("Move: " + Game.activeArmy.get(i).iCivID + " " + Game.activeArmy.get(i).iProvinceID + " " + nNewChosenProvinceID + " " + extraArmyY + " " + Game.activeArmy.get(i).key);
                            }
                        }
                        else if (Game.activeArmy.get(i).iCivID == Game.player.iCivID || (GameValues.move.PLAYER_CAN_MOVE_VASSALS_ARMIES && Game.getCiv(Game.activeArmy.get(i).iCivID).getPuppetOfCivID() == Game.player.iCivID)) {
                            Game.getCiv(Game.activeArmy.get(i).iCivID).newMove(Game.activeArmy.get(i).iProvinceID, nNewChosenProvinceID, Game.activeArmy.get(i).key, extraArmyY, false);
                            final ArmyDivision army = Game.getProvince(Game.activeArmy.get(i).iProvinceID).getArmy(Game.getProvince(Game.activeArmy.get(i).iProvinceID).getArmyKeyID(Game.activeArmy.get(i).key));
                            final List<ArmyRegiment> lArmyRegiment = army.lArmyRegiment;
                            final ArrayList<String> list = new ArrayList<String>();
                            for (final ArmyRegiment regiment : army.lArmyRegiment) {
                                final int uID = regiment.uID;
                                final int aID = regiment.aID;
                                final int num = regiment.num;
                                final float mo = regiment.mo;
                                final String key = regiment.key;
                                list.add(uID + " " + aID + " " + num + " " + mo + " " + key + " " + army.lArmyRegiment.size() + " " + Game.activeArmy.get(i).key + " " + Game.activeArmy.get(i).iCivID + " " + Game.activeArmy.get(i).iProvinceID);
                            }
                            final Object listString = list.stream().map((Function<? super Object, ?>)String::valueOf).collect((Collector<? super Object, ?, String>)Collectors.joining(","));
                            DesktopLauncher.SMS("DELETE = ARMY: " + Game.activeArmy.get(i).key + " " + Game.activeArmy.get(i).iCivID + " " + Game.activeArmy.get(i).iProvinceID);
                            DesktopLauncher.SMS("CR: " + (String)listString);
                            DesktopLauncher.SMS("Move: " + Game.activeArmy.get(i).iCivID + " " + Game.activeArmy.get(i).iProvinceID + " " + nNewChosenProvinceID + " " + extraArmyY + " " + Game.activeArmy.get(i).key);
                        }
                        Game.soundsManager.playMove();
                        if (Game.menuManager.getVisibleInGame_ProvinceArmy()) {
                            Game.menuManager.rebuildInGame_ProvinceArmy(true, true);
                        }
                    }
                    else {
                        Game.player.playerData.invasion.removeInvasion(Game.activeArmy.get(i).key);
                        Game.getCiv(Game.activeArmy.get(i).iCivID).cancelMove(Game.activeArmy.get(i).key);
                        if (Game.menuManager.getVisibleInGame_ProvinceArmy()) {
                            Game.menuManager.rebuildInGame_ProvinceArmy(true, true);
                        }
                    }
                }
            }
        }
    }
}
