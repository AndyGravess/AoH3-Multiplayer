// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.province;

import java.util.ArrayList;
import aoc.kingdoms.lukasz.map.diplomacy.Diplomacy_RelationsAction;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.setting.SettingsProvince;
import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.List;

public class ProvinceBorderManager
{
    public static List<PBData> lProvinces;
    public static List<PBData> lProvincesLandBySea;
    public static List<PBData> lProvincesSeaBySea;
    public static long lTimeLine;
    public static int iLineOffset;
    public static int iLineOffsetInterval;
    public static boolean drawInnerBorder;
    public static boolean drawBorder;
    public static long WAR_COLOR_TIME;
    public static int WAR_COLOR_STEP;
    public static boolean warColorMovingBack;
    public static Action action;
    
    public static final void update() {
        updateWarColor();
        if (Game.mapScale.getCurrentScale() >= Game.DRAW_INNER_BORDERS && !ProvinceBorderManager.drawInnerBorder) {
            updateProvinceBorder();
            ProvinceBorderManager.drawInnerBorder = true;
        }
        else if (Game.mapScale.getCurrentScale() < Game.DRAW_INNER_BORDERS && ProvinceBorderManager.drawInnerBorder) {
            updateProvinceBorder();
            ProvinceBorderManager.drawInnerBorder = false;
        }
        else if (Game.mapScale.getCurrentScale() > SettingsProvince.value.DRAW_BORDERS && !ProvinceBorderManager.drawBorder) {
            updateProvinceBorder();
            ProvinceBorderManager.drawBorder = true;
        }
        else if (Game.mapScale.getCurrentScale() <= SettingsProvince.value.DRAW_BORDERS && ProvinceBorderManager.drawBorder) {
            updateProvinceBorder();
            ProvinceBorderManager.drawBorder = false;
        }
    }
    
    public static final void updateWarColor() {
        if (GameValues.provinceBorderWar.ENABLE_WAR_BORDER) {
            if (ProvinceBorderManager.warColorMovingBack) {
                ProvinceBorderManager.WAR_COLOR_STEP += (int)Math.min(100L, CFG.currentTimeMillis - ProvinceBorderManager.WAR_COLOR_TIME);
                if (ProvinceBorderManager.WAR_COLOR_STEP < 0) {
                    CFG.COLOR_PROVINCE_STRAIGHT_WAR_ACTIVE = CFG.COLOR_PROVINCE_STRAIGHT_WAR;
                    ProvinceBorderManager.WAR_COLOR_TIME = CFG.currentTimeMillis;
                }
                else {
                    CFG.COLOR_PROVINCE_STRAIGHT_WAR_ACTIVE = CFG.getColorStep(CFG.COLOR_PROVINCE_STRAIGHT_WAR, CFG.COLOR_PROVINCE_STRAIGHT_WAR2, ProvinceBorderManager.WAR_COLOR_STEP, GameValues.provinceBorderWar.WAR_COLOR_TIME_ANIMATION, 1.0f);
                    ProvinceBorderManager.WAR_COLOR_TIME = CFG.currentTimeMillis;
                    if (ProvinceBorderManager.WAR_COLOR_STEP >= GameValues.provinceBorderWar.WAR_COLOR_TIME_ANIMATION) {
                        ProvinceBorderManager.WAR_COLOR_STEP = -GameValues.provinceBorderWar.WAR_COLOR_TIME_PAUSE2;
                        ProvinceBorderManager.warColorMovingBack = false;
                    }
                }
            }
            else {
                ProvinceBorderManager.WAR_COLOR_STEP += (int)Math.min(100L, CFG.currentTimeMillis - ProvinceBorderManager.WAR_COLOR_TIME);
                if (ProvinceBorderManager.WAR_COLOR_STEP < 0) {
                    CFG.COLOR_PROVINCE_STRAIGHT_WAR_ACTIVE = CFG.COLOR_PROVINCE_STRAIGHT_WAR2;
                    ProvinceBorderManager.WAR_COLOR_TIME = CFG.currentTimeMillis;
                }
                else {
                    CFG.COLOR_PROVINCE_STRAIGHT_WAR_ACTIVE = CFG.getColorStep(CFG.COLOR_PROVINCE_STRAIGHT_WAR2, CFG.COLOR_PROVINCE_STRAIGHT_WAR, ProvinceBorderManager.WAR_COLOR_STEP, GameValues.provinceBorderWar.WAR_COLOR_TIME_ANIMATION, 1.0f);
                    ProvinceBorderManager.WAR_COLOR_TIME = CFG.currentTimeMillis;
                    if (ProvinceBorderManager.WAR_COLOR_STEP >= GameValues.provinceBorderWar.WAR_COLOR_TIME_ANIMATION) {
                        ProvinceBorderManager.WAR_COLOR_STEP = -GameValues.provinceBorderWar.WAR_COLOR_TIME_PAUSE;
                        ProvinceBorderManager.warColorMovingBack = true;
                    }
                }
            }
        }
    }
    
    public static final void updateProvinceBorder() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            for (int j = 0; j < Game.getProvince(i).getNeighboringProvincesSize(); ++j) {
                if (i < Game.getProvince(i).getNeighboringProvinces(j)) {
                    if (Game.getProvince(i).getSeaProvince() && Game.getProvince(Game.getProvince(i).getNeighboringProvinces(j)).getSeaProvince()) {
                        Game.getProvince(i).getProvinceBordersSeaBySea(Game.getProvince(i).getNeighboringProvinces(j)).updateDrawProvinceBorder(i);
                    }
                    else if (Game.getProvince(i).getSeaProvince() || Game.getProvince(Game.getProvince(i).getNeighboringProvinces(j)).getSeaProvince()) {
                        Game.getProvince(i).getProvinceBordersLandBySea(Game.getProvince(i).getNeighboringProvinces(j)).updateDrawProvinceBorder(i);
                    }
                    else {
                        Game.getProvince(i).getProvinceBordersLandByLand(Game.getProvince(i).getNeighboringProvinces(j)).updateDrawProvinceBorder(i);
                    }
                }
                else if (Game.getProvince(i).getSeaProvince() && Game.getProvince(Game.getProvince(i).getNeighboringProvinces(j)).getSeaProvince()) {
                    Game.getProvince(Game.getProvince(i).getNeighboringProvinces(j)).getProvinceBordersSeaBySea(j).updateDrawProvinceBorder(Game.getProvince(i).getNeighboringProvinces(j));
                }
                else if (Game.getProvince(i).getSeaProvince() || Game.getProvince(Game.getProvince(i).getNeighboringProvinces(j)).getSeaProvince()) {
                    Game.getProvince(Game.getProvince(i).getNeighboringProvinces(j)).getProvinceBordersLandBySea(j).updateDrawProvinceBorder(Game.getProvince(i).getNeighboringProvinces(j));
                }
                else {
                    Game.getProvince(Game.getProvince(i).getNeighboringProvinces(j)).getProvinceBordersLandByLand(j).updateDrawProvinceBorder(Game.getProvince(i).getNeighboringProvinces(j));
                }
            }
        }
    }
    
    public static final void updateProvinceBorder(final int i) {
        for (int j = 0; j < Game.getProvince(i).getNeighboringProvincesSize(); ++j) {
            if (i < Game.getProvince(i).getNeighboringProvinces(j)) {
                if (Game.getProvince(i).getSeaProvince() && Game.getProvince(Game.getProvince(i).getNeighboringProvinces(j)).getSeaProvince()) {
                    Game.getProvince(i).getProvinceBordersSeaBySea(Game.getProvince(i).getNeighboringProvinces(j)).updateDrawProvinceBorder(i);
                }
                else if (Game.getProvince(i).getSeaProvince() || Game.getProvince(Game.getProvince(i).getNeighboringProvinces(j)).getSeaProvince()) {
                    Game.getProvince(i).getProvinceBordersLandBySea(Game.getProvince(i).getNeighboringProvinces(j)).updateDrawProvinceBorder(i);
                }
                else {
                    Game.getProvince(i).getProvinceBordersLandByLand(Game.getProvince(i).getNeighboringProvinces(j)).updateDrawProvinceBorder(i);
                }
            }
            else if (Game.getProvince(i).getSeaProvince() && Game.getProvince(Game.getProvince(i).getNeighboringProvinces(j)).getSeaProvince()) {
                Game.getProvince(Game.getProvince(i).getNeighboringProvinces(j)).getProvinceBordersSeaBySea(j).updateDrawProvinceBorder(Game.getProvince(i).getNeighboringProvinces(j));
            }
            else if (Game.getProvince(i).getSeaProvince() || Game.getProvince(Game.getProvince(i).getNeighboringProvinces(j)).getSeaProvince()) {
                Game.getProvince(Game.getProvince(i).getNeighboringProvinces(j)).getProvinceBordersLandBySea(j).updateDrawProvinceBorder(Game.getProvince(i).getNeighboringProvinces(j));
            }
            else {
                Game.getProvince(Game.getProvince(i).getNeighboringProvinces(j)).getProvinceBordersLandByLand(j).updateDrawProvinceBorder(Game.getProvince(i).getNeighboringProvinces(j));
            }
        }
    }
    
    public static final void clearProvinceBorder() {
        for (int i = 0, iSize = ProvinceBorderManager.lProvinces.size(); i < iSize; ++i) {
            Game.getProvince(ProvinceBorderManager.lProvinces.get(i).iProvinceID).getProvinceBordersLandByLand(ProvinceBorderManager.lProvinces.get(i).iWithProvinceID).isLocked = false;
            Game.getProvince(ProvinceBorderManager.lProvinces.get(i).iProvinceID).getProvinceBordersLandByLand(ProvinceBorderManager.lProvinces.get(i).iWithProvinceID).updateDrawProvinceBorder(ProvinceBorderManager.lProvinces.get(i).iProvinceID);
        }
        ProvinceBorderManager.lProvinces.clear();
        for (int i = 0, iSize = ProvinceBorderManager.lProvincesLandBySea.size(); i < iSize; ++i) {
            Game.getProvince(ProvinceBorderManager.lProvincesLandBySea.get(i).iProvinceID).getProvinceBordersLandBySea(ProvinceBorderManager.lProvincesLandBySea.get(i).iWithProvinceID).isLocked = false;
            Game.getProvince(ProvinceBorderManager.lProvincesLandBySea.get(i).iProvinceID).getProvinceBordersLandBySea(ProvinceBorderManager.lProvincesLandBySea.get(i).iWithProvinceID).updateDrawProvinceBorder(ProvinceBorderManager.lProvincesLandBySea.get(i).iProvinceID);
        }
        ProvinceBorderManager.lProvincesLandBySea.clear();
        for (int i = 0, iSize = ProvinceBorderManager.lProvincesSeaBySea.size(); i < iSize; ++i) {
            Game.getProvince(ProvinceBorderManager.lProvincesSeaBySea.get(i).iProvinceID).getProvinceBordersSeaBySea(ProvinceBorderManager.lProvincesSeaBySea.get(i).iWithProvinceID).isLocked = false;
            Game.getProvince(ProvinceBorderManager.lProvincesSeaBySea.get(i).iProvinceID).getProvinceBordersSeaBySea(ProvinceBorderManager.lProvincesSeaBySea.get(i).iWithProvinceID).updateDrawProvinceBorder(ProvinceBorderManager.lProvincesSeaBySea.get(i).iProvinceID);
        }
        ProvinceBorderManager.lProvincesSeaBySea.clear();
    }
    
    public static final void updateAction() {
        if (Game.menuManager.getInGame()) {
            ProvinceBorderManager.action = new Action() {
                @Override
                public void setProvinceID(final int nProvinceID) {
                    try {
                        if (nProvinceID >= 0) {
                            if (!Game.menuManager.getVisibleInGame_Civ()) {
                                if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_PEACE_VIEW) {
                                    ProvinceBorderManager.clearProvinceBorder();
                                    for (int i = 0; i < Game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
                                        if (Game.getProvince(nProvinceID).getSeaProvince() && Game.getProvince(Game.getProvince(nProvinceID).getNeighboringProvinces(i)).getSeaProvince()) {
                                            if (nProvinceID < Game.getProvince(nProvinceID).getNeighboringProvinces(i)) {
                                                Game.getProvince(nProvinceID).getProvinceBordersSeaBySea(Game.getProvince(nProvinceID).getNeighboringProvinces(i)).updateDrawProvinceBorder_ActiveProvince();
                                                ProvinceBorderManager.lProvincesSeaBySea.add(new PBData(nProvinceID, Game.getProvince(nProvinceID).getNeighboringProvinces(i)));
                                            }
                                            else {
                                                Game.getProvince(Game.getProvince(nProvinceID).getNeighboringProvinces(i)).getProvinceBordersSeaBySea(nProvinceID).updateDrawProvinceBorder_ActiveProvince();
                                                ProvinceBorderManager.lProvincesSeaBySea.add(new PBData(Game.getProvince(nProvinceID).getNeighboringProvinces(i), nProvinceID));
                                            }
                                        }
                                        else if (Game.getProvince(nProvinceID).getSeaProvince() || Game.getProvince(Game.getProvince(nProvinceID).getNeighboringProvinces(i)).getSeaProvince()) {
                                            if (nProvinceID < Game.getProvince(nProvinceID).getNeighboringProvinces(i)) {
                                                Game.getProvince(nProvinceID).getProvinceBordersLandBySea(Game.getProvince(nProvinceID).getNeighboringProvinces(i)).updateDrawProvinceBorder_ActiveProvince();
                                                ProvinceBorderManager.lProvincesLandBySea.add(new PBData(nProvinceID, Game.getProvince(nProvinceID).getNeighboringProvinces(i)));
                                            }
                                            else {
                                                Game.getProvince(Game.getProvince(nProvinceID).getNeighboringProvinces(i)).getProvinceBordersLandBySea(nProvinceID).updateDrawProvinceBorder_ActiveProvince();
                                                ProvinceBorderManager.lProvincesLandBySea.add(new PBData(Game.getProvince(nProvinceID).getNeighboringProvinces(i), nProvinceID));
                                            }
                                        }
                                        else if (nProvinceID < Game.getProvince(nProvinceID).getNeighboringProvinces(i)) {
                                            Game.getProvince(nProvinceID).getProvinceBordersLandByLand(Game.getProvince(nProvinceID).getNeighboringProvinces(i)).updateDrawProvinceBorder_ActiveProvince();
                                            ProvinceBorderManager.lProvinces.add(new PBData(nProvinceID, Game.getProvince(nProvinceID).getNeighboringProvinces(i)));
                                        }
                                        else {
                                            Game.getProvince(Game.getProvince(nProvinceID).getNeighboringProvinces(i)).getProvinceBordersLandByLand(nProvinceID).updateDrawProvinceBorder_ActiveProvince();
                                            ProvinceBorderManager.lProvinces.add(new PBData(Game.getProvince(nProvinceID).getNeighboringProvinces(i), nProvinceID));
                                        }
                                    }
                                    for (int i = 0; i < Game.getProvince(nProvinceID).getNeighboringSeaProvincesSize(); ++i) {
                                        if (nProvinceID < Game.getProvince(nProvinceID).getNeighboringSeaProvinces(i)) {
                                            Game.getProvince(nProvinceID).getProvinceBordersLandBySea(Game.getProvince(nProvinceID).getNeighboringSeaProvinces(i)).updateDrawProvinceBorder_ActiveProvince();
                                            ProvinceBorderManager.lProvincesLandBySea.add(new PBData(nProvinceID, Game.getProvince(nProvinceID).getNeighboringSeaProvinces(i)));
                                        }
                                        else {
                                            Game.getProvince(Game.getProvince(nProvinceID).getNeighboringSeaProvinces(i)).getProvinceBordersLandBySea(nProvinceID).updateDrawProvinceBorder_ActiveProvince();
                                            ProvinceBorderManager.lProvincesLandBySea.add(new PBData(Game.getProvince(nProvinceID).getNeighboringSeaProvinces(i), nProvinceID));
                                        }
                                    }
                                }
                            }
                        }
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
                
                @Override
                public void resetProvinceID() {
                    if (!Game.menuManager.getVisibleInGame_Civ()) {
                        ProvinceBorderManager.clearProvinceBorder();
                    }
                }
                
                @Override
                public void update() {
                    if (ProvinceBorderManager.lTimeLine < CFG.currentTimeMillis - ProvinceBorderManager.iLineOffsetInterval) {
                        ++ProvinceBorderManager.iLineOffset;
                        ProvinceBorderManager.lTimeLine = CFG.currentTimeMillis;
                    }
                }
            };
        }
        else if (Game.menuManager.getInNewGame()) {
            ProvinceBorderManager.action = new Action() {
                @Override
                public void setProvinceID(final int nProvinceID) {
                    ProvinceBorderManager.updateDrawProvinceBorder_SelectCiv_ByProvinceID(nProvinceID);
                }
                
                @Override
                public void resetProvinceID() {
                }
                
                @Override
                public void update() {
                }
            };
        }
        else if (Game.menuManager.getInGameLost()) {
            ProvinceBorderManager.action = new Action() {
                @Override
                public void setProvinceID(final int nProvinceID) {
                }
                
                @Override
                public void resetProvinceID() {
                }
                
                @Override
                public void update() {
                }
            };
        }
        else if (Game.menuManager.getInInitGameMenu() || Game.menuManager.getInInitGame_Menus() || Game.menuManager.getInLoadScenario()) {
            ProvinceBorderManager.action = new Action() {
                @Override
                public void setProvinceID(final int nProvinceID) {
                }
                
                @Override
                public void resetProvinceID() {
                }
                
                @Override
                public void update() {
                }
            };
        }
        else {
            ProvinceBorderManager.action = new Action() {
                @Override
                public void setProvinceID(final int nProvinceID) {
                }
                
                @Override
                public void resetProvinceID() {
                }
                
                @Override
                public void update() {
                }
            };
        }
    }
    
    public static final void updateDrawProvinceBorder_SelectCiv_ByProvinceID(final int nProvinceID) {
        clearProvinceBorder();
        if (Game.getProvince(nProvinceID).getCivID() > 0 && Game.getCiv(Game.getProvince(nProvinceID).getCivID()).getNumOfProvinces() > 0) {
            for (int o = 0; o < Game.getCiv(Game.getProvince(nProvinceID).getCivID()).getNumOfProvinces(); ++o) {
                for (int i = 0; i < Game.getProvince(Game.getCiv(Game.getProvince(nProvinceID).getCivID()).getProvinceID(o)).getNeighboringProvincesSize(); ++i) {
                    if (Game.getProvince(Game.getCiv(Game.getProvince(nProvinceID).getCivID()).getProvinceID(o)).getCivID() != Game.getProvince(Game.getProvince(Game.getCiv(Game.getProvince(nProvinceID).getCivID()).getProvinceID(o)).getNeighboringProvinces(i)).getCivID()) {
                        if (Game.getCiv(Game.getProvince(nProvinceID).getCivID()).getProvinceID(o) < Game.getProvince(Game.getCiv(Game.getProvince(nProvinceID).getCivID()).getProvinceID(o)).getNeighboringProvinces(i)) {
                            Game.getProvince(Game.getCiv(Game.getProvince(nProvinceID).getCivID()).getProvinceID(o)).getProvinceBordersLandByLand(Game.getProvince(Game.getCiv(Game.getProvince(nProvinceID).getCivID()).getProvinceID(o)).getNeighboringProvinces(i)).updateDrawProvinceBorder_ActiveCivilizationBorder();
                            ProvinceBorderManager.lProvinces.add(new PBData(Game.getCiv(Game.getProvince(nProvinceID).getCivID()).getProvinceID(o), Game.getProvince(Game.getCiv(Game.getProvince(nProvinceID).getCivID()).getProvinceID(o)).getNeighboringProvinces(i)));
                        }
                        else {
                            Game.getProvince(Game.getProvince(Game.getCiv(Game.getProvince(nProvinceID).getCivID()).getProvinceID(o)).getNeighboringProvinces(i)).getProvinceBordersLandByLand(Game.getCiv(Game.getProvince(nProvinceID).getCivID()).getProvinceID(o)).updateDrawProvinceBorder_ActiveCivilizationBorder();
                            ProvinceBorderManager.lProvinces.add(new PBData(Game.getProvince(Game.getCiv(Game.getProvince(nProvinceID).getCivID()).getProvinceID(o)).getNeighboringProvinces(i), Game.getCiv(Game.getProvince(nProvinceID).getCivID()).getProvinceID(o)));
                        }
                    }
                }
            }
        }
    }
    
    public static final void updateDrawProvinceBorder_SelectCiv_ByCivID(final int nCivID) {
        clearProvinceBorder();
        if (nCivID > 0 && Game.getCiv(nCivID).getNumOfProvinces() > 0) {
            for (int o = 0; o < Game.getCiv(nCivID).getNumOfProvinces(); ++o) {
                for (int i = 0; i < Game.getProvince(Game.getCiv(nCivID).getProvinceID(o)).getNeighboringProvincesSize(); ++i) {
                    if (Game.getProvince(Game.getCiv(nCivID).getProvinceID(o)).getCivID() != Game.getProvince(Game.getProvince(Game.getCiv(nCivID).getProvinceID(o)).getNeighboringProvinces(i)).getCivID()) {
                        if (Game.getCiv(nCivID).getProvinceID(o) < Game.getProvince(Game.getCiv(nCivID).getProvinceID(o)).getNeighboringProvinces(i)) {
                            Game.getProvince(Game.getCiv(nCivID).getProvinceID(o)).getProvinceBordersLandByLand(Game.getProvince(Game.getCiv(nCivID).getProvinceID(o)).getNeighboringProvinces(i)).updateDrawProvinceBorder_ActiveCivilizationBorder();
                            ProvinceBorderManager.lProvinces.add(new PBData(Game.getCiv(nCivID).getProvinceID(o), Game.getProvince(Game.getCiv(nCivID).getProvinceID(o)).getNeighboringProvinces(i)));
                        }
                        else {
                            Game.getProvince(Game.getProvince(Game.getCiv(nCivID).getProvinceID(o)).getNeighboringProvinces(i)).getProvinceBordersLandByLand(Game.getCiv(nCivID).getProvinceID(o)).updateDrawProvinceBorder_ActiveCivilizationBorder();
                            ProvinceBorderManager.lProvinces.add(new PBData(Game.getProvince(Game.getCiv(nCivID).getProvinceID(o)).getNeighboringProvinces(i), Game.getCiv(nCivID).getProvinceID(o)));
                        }
                    }
                }
            }
        }
    }
    
    public static final void updateDrawProvinceBorder_ImprovingRelations() {
        clearProvinceBorder();
        for (int p = Game.getCiv(Game.player.iCivID).diplomacy.iImprovingRelationsSize - 1; p >= 0; --p) {
            final int nCivID = Game.getCiv(Game.player.iCivID).diplomacy.improvingRelations.get(p).iCivID;
            if (nCivID > 0 && Game.getCiv(nCivID).getNumOfProvinces() > 0) {
                for (int o = 0; o < Game.getCiv(nCivID).getNumOfProvinces(); ++o) {
                    for (int i = 0; i < Game.getProvince(Game.getCiv(nCivID).getProvinceID(o)).getNeighboringProvincesSize(); ++i) {
                        if (Game.getProvince(Game.getCiv(nCivID).getProvinceID(o)).getCivID() != Game.getProvince(Game.getProvince(Game.getCiv(nCivID).getProvinceID(o)).getNeighboringProvinces(i)).getCivID()) {
                            if (Game.getCiv(nCivID).getProvinceID(o) < Game.getProvince(Game.getCiv(nCivID).getProvinceID(o)).getNeighboringProvinces(i)) {
                                Game.getProvince(Game.getCiv(nCivID).getProvinceID(o)).getProvinceBordersLandByLand(Game.getProvince(Game.getCiv(nCivID).getProvinceID(o)).getNeighboringProvinces(i)).updateDrawProvinceBorder_RelationUp();
                                ProvinceBorderManager.lProvinces.add(new PBData(Game.getCiv(nCivID).getProvinceID(o), Game.getProvince(Game.getCiv(nCivID).getProvinceID(o)).getNeighboringProvinces(i)));
                            }
                            else {
                                Game.getProvince(Game.getProvince(Game.getCiv(nCivID).getProvinceID(o)).getNeighboringProvinces(i)).getProvinceBordersLandByLand(Game.getCiv(nCivID).getProvinceID(o)).updateDrawProvinceBorder_RelationUp();
                                ProvinceBorderManager.lProvinces.add(new PBData(Game.getProvince(Game.getCiv(nCivID).getProvinceID(o)).getNeighboringProvinces(i), Game.getCiv(nCivID).getProvinceID(o)));
                            }
                        }
                    }
                }
            }
        }
        final int nCivID2 = Game.player.iCivID;
        if (nCivID2 > 0 && Game.getCiv(nCivID2).getNumOfProvinces() > 0) {
            for (int o2 = 0; o2 < Game.getCiv(nCivID2).getNumOfProvinces(); ++o2) {
                for (int j = 0; j < Game.getProvince(Game.getCiv(nCivID2).getProvinceID(o2)).getNeighboringProvincesSize(); ++j) {
                    if (Game.getProvince(Game.getCiv(nCivID2).getProvinceID(o2)).getCivID() != Game.getProvince(Game.getProvince(Game.getCiv(nCivID2).getProvinceID(o2)).getNeighboringProvinces(j)).getCivID()) {
                        if (Game.getCiv(nCivID2).getProvinceID(o2) < Game.getProvince(Game.getCiv(nCivID2).getProvinceID(o2)).getNeighboringProvinces(j)) {
                            Game.getProvince(Game.getCiv(nCivID2).getProvinceID(o2)).getProvinceBordersLandByLand(Game.getProvince(Game.getCiv(nCivID2).getProvinceID(o2)).getNeighboringProvinces(j)).updateDrawProvinceBorder_ActiveCivilizationBorder();
                            ProvinceBorderManager.lProvinces.add(new PBData(Game.getCiv(nCivID2).getProvinceID(o2), Game.getProvince(Game.getCiv(nCivID2).getProvinceID(o2)).getNeighboringProvinces(j)));
                        }
                        else {
                            Game.getProvince(Game.getProvince(Game.getCiv(nCivID2).getProvinceID(o2)).getNeighboringProvinces(j)).getProvinceBordersLandByLand(Game.getCiv(nCivID2).getProvinceID(o2)).updateDrawProvinceBorder_ActiveCivilizationBorder();
                            ProvinceBorderManager.lProvinces.add(new PBData(Game.getProvince(Game.getCiv(nCivID2).getProvinceID(o2)).getNeighboringProvinces(j), Game.getCiv(nCivID2).getProvinceID(o2)));
                        }
                    }
                }
            }
        }
    }
    
    static {
        ProvinceBorderManager.lProvinces = new ArrayList<PBData>();
        ProvinceBorderManager.lProvincesLandBySea = new ArrayList<PBData>();
        ProvinceBorderManager.lProvincesSeaBySea = new ArrayList<PBData>();
        ProvinceBorderManager.iLineOffset = 0;
        ProvinceBorderManager.iLineOffsetInterval = 75;
        ProvinceBorderManager.drawInnerBorder = true;
        ProvinceBorderManager.drawBorder = true;
        ProvinceBorderManager.WAR_COLOR_TIME = 0L;
        ProvinceBorderManager.WAR_COLOR_STEP = 1;
        ProvinceBorderManager.warColorMovingBack = false;
        ProvinceBorderManager.action = new Action() {
            @Override
            public void setProvinceID(final int nProvinceID) {
            }
            
            @Override
            public void resetProvinceID() {
            }
            
            @Override
            public void update() {
            }
        };
    }
    
    public static class PBData
    {
        public int iProvinceID;
        public int iWithProvinceID;
        
        public PBData(final int iProvinceID, final int iWithProvinceID) {
            this.iProvinceID = iProvinceID;
            this.iWithProvinceID = iWithProvinceID;
        }
    }
    
    public interface Action
    {
        void setProvinceID(final int p0);
        
        void resetProvinceID();
        
        void update();
    }
}
