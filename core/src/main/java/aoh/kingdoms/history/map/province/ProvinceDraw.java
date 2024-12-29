// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.province;

import aoc.kingdoms.lukasz.map.moveUnits.other.MoveUnits_BiggestCities_Diplomacy;
import aoc.kingdoms.lukasz.map.moveUnits.MoveUnits;
import java.util.Iterator;
import aoc.kingdoms.lukasz.map.diplomacy.Vassal;
import aoc.kingdoms.lukasz.map.diplomacy.Diplomacy;
import java.util.Map;
import aoc.kingdoms.lukasz.jakowski.setting.SettingsProvince;
import aoc.kingdoms.lukasz.map.map.Map_Data;
import aoc.kingdoms.lukasz.map.map.MapScale;
import aoc.kingdoms.lukasz.menusInGame.InGame_ReleaseAVassal;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_FormCiv;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_DeclareWar;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.menusInGame.Civ.InGame_Civ;
import aoc.kingdoms.lukasz.menusInGame.InGame;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.Pixmap;
import aoc.kingdoms.lukasz.jakowski.FBO.FBOProvincesBG;
import aoc.kingdoms.lukasz.map.army.ArmyInvasion;
import aoc.kingdoms.lukasz.map.BuildingsManager;
import aoc.kingdoms.lukasz.map.WondersManager;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.jakowski.Region;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapOptimizationRegions;
import aoc.kingdoms.lukasz.map.map.GeographicalRegions;
import aoc.kingdoms.lukasz.map.map.Continents;
import aoc.kingdoms.lukasz.map.ResourcesManager;
import aoc.kingdoms.lukasz.menusMapEditor.EditorSelectProvinces;
import aoc.kingdoms.lukasz.map.FormableCivManager;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMap_FormableCiv;
import aoc.kingdoms.lukasz.map.technology.TechnologyTree;
import aoc.kingdoms.lukasz.map.map.MapScenarios;
import aoc.kingdoms.lukasz.map.terrain.Terrain;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menusScenarioEditor.Diplomacy.ScenarioRelationsList;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioCreateAllianceList;
import aoc.kingdoms.lukasz.menu.MenuManager;
import aoc.kingdoms.lukasz.menusEditor.CreateCiv;
import aoc.kingdoms.lukasz.menusEditor.GameCivsEdit;
import aoc.kingdoms.lukasz.map.map.MapMode;
import aoc.kingdoms.lukasz.menus.Settings.Settings_Menu;
import aoc.kingdoms.lukasz.map.map.MapModeManager;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.Gdx;
import aoc.kingdoms.lukasz.map.army.ArmyManager;
import aoc.kingdoms.lukasz.textures.Image;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.map.army.ArmyRecruit;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.map.province.animation.ProvinceAnimationDot;
import aoc.kingdoms.lukasz.map.moveUnits.other.MoveUnits_BiggestCities;
import aoc.kingdoms.lukasz.map.moveUnits.other.MoveUnits_BiggestCities_Siege;
import java.util.List;
import space.earlygrey.shapedrawer.JoinType;
import com.badlogic.gdx.graphics.Color;

public class ProvinceDraw
{
    public static DrawProvinces drawProvinces;
    public static final float PROVINCE_ALPHA_TERRAIN = 0.55f;
    public static DrawExtraDetails drawExtraDetails;
    public static Color progressBarBG;
    public static Color progressBar;
    public static Color progressBar2;
    public static int drawProvincesCiv_HoveredFlagID;
    public static Color PROVINCE_DIPLOMACY_NEUTRAL;
    public static Color PROVINCE_DIPLOMACY_NEUTRAL_GREEN;
    public static Color PROVINCE_DIPLOMACY_RED;
    public static Color PROVINCE_DIPLOMACY_RED2;
    public static Color PROVINCE_DIPLOMACY_GREEN;
    public static Color PROVINCE_DIPLOMACY_GREEN2;
    public static Color PROVINCE_DIPLOMACY_ALLIANCE;
    public static Color PROVINCE_DIPLOMACY_AT_WAR;
    public static Color PROVINCE_DIPLOMACY_VASSAL;
    public static Color PROVINCE_DIPLOMACY_PACT;
    public static Color PROVINCE_DIPLOMACY_INDEPENDENCE;
    public static Color PROVINCE_DIPLOMACY_INDEPENDENCE2;
    public static Color PROVINCE_DIPLOMACY_MILITARY_ACCESS;
    public static Color PROVINCE_DIPLOMACY_DEFENSIVE_PACT;
    public static Color PROVINCE_DIPLOMACY_TRUCE;
    public static JoinType joinType;
    public static JoinType joinType_Shadow;
    public static float lineWidth;
    public static DrawMoveUnits oDrawMoveUnits;
    public static int FRAME_ID;
    public static List<MoveUnits_BiggestCities_Siege> siegeLines;
    public static int iSiegeLinesSize;
    public static List<MoveUnits_BiggestCities> biggestCitiesLines;
    public static int iBiggestCitiesLinesSize;
    public static List<MoveUnits_BiggestCities> diplomacyLines;
    public static int iDiplomacyLinesSize;
    public static List<ProvinceAnimationDot> provinceDots;
    public static int iProvinceDotsSize;
    
    public static void updateDrawExtraDetails() {
        if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_RECRUIT_ARMY) {
            ProvinceDraw.drawExtraDetails = new DrawExtraDetails() {
                @Override
                public void draw(final SpriteBatch oSB) {
                    try {
                        if (Game.mapScale.getCurrentScale() >= Game.DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE) {
                            for (int i = 0; i < Game.getCiv(Game.player.iCivID).getArmyRecruitSize(); ++i) {
                                for (int j = 0; j < Game.getCiv(Game.player.iCivID).lArmyRecruit.get(i).size(); ++j) {
                                    if (Game.getProvince(Game.getCiv(Game.player.iCivID).lArmyRecruit.get(i).get(j).provinceID).getDrawProvince()) {
                                        oSB.setShader(Renderer.shaderAlpha);
                                        ArmyManager.armyImages.get(ArmyManager.lArmy.get(Game.getCiv(Game.player.iCivID).lArmyRecruit.get(i).get(j).unitID).get(Game.getCiv(Game.player.iCivID).lArmyRecruit.get(i).get(j).armyID).ImageID).getTexture().bind(1);
                                        Gdx.gl.glActiveTexture(33984);
                                        ImageManager.getImage(Images.unitsFrameMapMask).draw(oSB, (int)((Game.getProvince(Game.getCiv(Game.player.iCivID).lArmyRecruit.get(i).get(j).provinceID).getTranslateProvincePosX() + Game.getProvince(Game.getCiv(Game.player.iCivID).lArmyRecruit.get(i).get(j).provinceID).iCenterShiftX) * Game.mapScale.getCurrentScale() - ImageManager.getImage(Images.unitsFrameMap).getWidth() / 2), (int)((Game.mapCoords.getPosY() + Game.getProvince(Game.getCiv(Game.player.iCivID).lArmyRecruit.get(i).get(j).provinceID).iCenterShiftY) * Game.mapScale.getCurrentScale() - ProvinceDrawArmy.getArmyHeight() / 2 - ImageManager.getImage(Images.unitsFrameMap).getHeight() * (j + 1) - 1 * (j + 1)));
                                        oSB.flush();
                                        oSB.setShader(Renderer.shaderDefault);
                                        ImageManager.getImage(Images.unitsFrameMap).draw(oSB, (int)((Game.getProvince(Game.getCiv(Game.player.iCivID).lArmyRecruit.get(i).get(j).provinceID).getTranslateProvincePosX() + Game.getProvince(Game.getCiv(Game.player.iCivID).lArmyRecruit.get(i).get(j).provinceID).iCenterShiftX) * Game.mapScale.getCurrentScale() - ImageManager.getImage(Images.unitsFrameMap).getWidth() / 2), (int)((Game.mapCoords.getPosY() + Game.getProvince(Game.getCiv(Game.player.iCivID).lArmyRecruit.get(i).get(j).provinceID).iCenterShiftY) * Game.mapScale.getCurrentScale() - ProvinceDrawArmy.getArmyHeight() / 2 - ImageManager.getImage(Images.unitsFrameMap).getHeight() * (j + 1) - 1 * (j + 1)));
                                        final int tCenterX = (ImageManager.getImage(Images.progressBarFrame).getWidth() - ImageManager.getImage(Images.progressBarFrameMask).getWidth()) / 2;
                                        final int tCenterY = (ImageManager.getImage(Images.progressBarFrame).getHeight() - ImageManager.getImage(Images.progressBarFrameMask).getHeight()) / 2;
                                        oSB.setColor(ProvinceDraw.progressBarBG);
                                        ImageManager.getImage(Images.progressBarFrameMask).draw(oSB, tCenterX + (int)((Game.getProvince(Game.getCiv(Game.player.iCivID).lArmyRecruit.get(i).get(j).provinceID).getTranslateProvincePosX() + Game.getProvince(Game.getCiv(Game.player.iCivID).lArmyRecruit.get(i).get(j).provinceID).iCenterShiftX) * Game.mapScale.getCurrentScale() - ImageManager.getImage(Images.unitsFrameMap).getWidth() / 2), tCenterY + (int)((Game.mapCoords.getPosY() + Game.getProvince(Game.getCiv(Game.player.iCivID).lArmyRecruit.get(i).get(j).provinceID).iCenterShiftY) * Game.mapScale.getCurrentScale() - ProvinceDrawArmy.getArmyHeight() / 2 - ImageManager.getImage(Images.unitsFrameMap).getHeight() * (j + 1) - 1 * (j + 1) + ImageManager.getImage(Images.unitsFrameMap).getHeight() - ImageManager.getImage(Images.progressBarFrame).getHeight()));
                                        oSB.setColor(ProvinceDraw.progressBar);
                                        ImageManager.getImage(Images.progressBarFrameMask).draw(oSB, tCenterX + (int)((Game.getProvince(Game.getCiv(Game.player.iCivID).lArmyRecruit.get(i).get(j).provinceID).getTranslateProvincePosX() + Game.getProvince(Game.getCiv(Game.player.iCivID).lArmyRecruit.get(i).get(j).provinceID).iCenterShiftX) * Game.mapScale.getCurrentScale() - ImageManager.getImage(Images.unitsFrameMap).getWidth() / 2), tCenterY + (int)((Game.mapCoords.getPosY() + Game.getProvince(Game.getCiv(Game.player.iCivID).lArmyRecruit.get(i).get(j).provinceID).iCenterShiftY) * Game.mapScale.getCurrentScale() - ProvinceDrawArmy.getArmyHeight() / 2 - ImageManager.getImage(Images.unitsFrameMap).getHeight() * (j + 1) - 1 * (j + 1) + ImageManager.getImage(Images.unitsFrameMap).getHeight() - ImageManager.getImage(Images.progressBarFrame).getHeight()), (int)(ImageManager.getImage(Images.progressBarFrameMask).getWidth() * ((ArmyManager.lArmy.get(Game.getCiv(Game.player.iCivID).lArmyRecruit.get(i).get(j).unitID).get(Game.getCiv(Game.player.iCivID).lArmyRecruit.get(i).get(j).armyID).RecruitmentTime - Game.getCiv(Game.player.iCivID).lArmyRecruit.get(i).get(j).timeLeft) / (float)ArmyManager.lArmy.get(Game.getCiv(Game.player.iCivID).lArmyRecruit.get(i).get(j).unitID).get(Game.getCiv(Game.player.iCivID).lArmyRecruit.get(i).get(j).armyID).RecruitmentTime)), ImageManager.getImage(Images.progressBarFrameMask).getHeight());
                                        oSB.setColor(Color.WHITE);
                                        ImageManager.getImage(Images.progressBarFrame).draw(oSB, (int)((Game.getProvince(Game.getCiv(Game.player.iCivID).lArmyRecruit.get(i).get(j).provinceID).getTranslateProvincePosX() + Game.getProvince(Game.getCiv(Game.player.iCivID).lArmyRecruit.get(i).get(j).provinceID).iCenterShiftX) * Game.mapScale.getCurrentScale() - ImageManager.getImage(Images.unitsFrameMap).getWidth() / 2), (int)((Game.mapCoords.getPosY() + Game.getProvince(Game.getCiv(Game.player.iCivID).lArmyRecruit.get(i).get(j).provinceID).iCenterShiftY) * Game.mapScale.getCurrentScale() - ProvinceDrawArmy.getArmyHeight() / 2 - ImageManager.getImage(Images.unitsFrameMap).getHeight() * (j + 1) - 1 * (j + 1) + ImageManager.getImage(Images.unitsFrameMap).getHeight() - ImageManager.getImage(Images.progressBarFrame).getHeight()));
                                    }
                                }
                            }
                        }
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            };
        }
        else {
            ProvinceDraw.drawExtraDetails = new DrawExtraDetails() {
                @Override
                public void draw(final SpriteBatch oSB) {
                    if (Game.mapScale.getCurrentScale() >= Game.DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE) {
                        if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFAULT) {
                            ProvinceDraw.drawWondersConstruction(oSB);
                            ProvinceDraw.drawBuildingsInConstruction(oSB);
                            ProvinceDraw.drawRecruitingArmyPlayer(oSB);
                        }
                        else {
                            ProvinceDraw.drawBuildingsInConstruction(oSB);
                        }
                    }
                }
            };
        }
    }
    
    public static final void drawProvinces(final SpriteBatch oSB) {
        try {
            Game.updateProvincesInView();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        oSB.setShader(Renderer.shaderDefaultProvince);
        try {
            final MapModeManager mapModes = Game.mapModes;
            MapModeManager.updateAlpha();
            ProvinceDraw.drawProvinces.draw(oSB);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        oSB.setShader(Renderer.shaderDefault);
        oSB.setColor(Color.WHITE);
    }
    
    public static final void drawProvinces_Settings(final SpriteBatch oSB) {
        long time = System.nanoTime();
        try {
            Game.updateProvincesInView();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        if (Settings_Menu.updateTimes) {
            Settings_Menu.provinceInView_Time = System.nanoTime() - time;
            time = System.nanoTime();
        }
        oSB.setShader(Renderer.shaderDefaultProvince);
        try {
            final MapModeManager mapModes = Game.mapModes;
            MapModeManager.updateAlpha();
            ProvinceDraw.drawProvinces.draw(oSB);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        oSB.setShader(Renderer.shaderDefault);
        if (Settings_Menu.updateTimes) {
            Settings_Menu.drawProvinces_Time = System.nanoTime() - time;
        }
        oSB.setColor(Color.WHITE);
    }
    
    public static final void updateDrawProvinces() {
        if (Game.menuManager.getInInitGameMenu() || Game.menuManager.getInInitGame_Menus() || Game.menuManager.getInLoadScenario()) {
            ProvinceDraw.drawProvinces = new DrawProvinces() {
                @Override
                public void draw(final SpriteBatch oSB) {
                }
            };
        }
        else if (Game.menuManager.getInGame()) {
            ProvinceDraw.drawProvinces = Game.mapModes.lMapModes.get(Game.mapModes.iActiveMapModeID).drawProvinces;
        }
        else if (Game.menuManager.getInMapEditorProvinceConnections()) {
            ProvinceDraw.drawProvinces = new DrawProvinces() {
                @Override
                public void draw(final SpriteBatch oSB) {
                    ProvinceDraw.drawProvincesInMapEditor_Connections(oSB);
                }
            };
        }
        else if (Game.menuManager.getInEditorGameCivsEdit()) {
            ProvinceDraw.drawProvinces = new DrawProvinces() {
                @Override
                public void draw(final SpriteBatch oSB) {
                    final float fAlpha = ProvinceDraw.getProvinceAlpha();
                    for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        oSB.setColor(new Color(GameCivsEdit.nCiv.iR / 255.0f, GameCivsEdit.nCiv.iG / 255.0f, GameCivsEdit.nCiv.iB / 255.0f, fAlpha));
                        Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                    }
                    for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                        oSB.setColor(new Color(GameCivsEdit.nCiv.iR / 255.0f, GameCivsEdit.nCiv.iG / 255.0f, GameCivsEdit.nCiv.iB / 255.0f, fAlpha));
                        Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                    }
                }
            };
        }
        else if (Game.menuManager.getInEditorCreateCiv()) {
            ProvinceDraw.drawProvinces = new DrawProvinces() {
                @Override
                public void draw(final SpriteBatch oSB) {
                    final float fAlpha = ProvinceDraw.getProvinceAlpha();
                    for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        oSB.setColor(new Color(CreateCiv.nCiv.iR / 255.0f, CreateCiv.nCiv.iG / 255.0f, CreateCiv.nCiv.iB / 255.0f, fAlpha));
                        Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                    }
                    for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                        oSB.setColor(new Color(CreateCiv.nCiv.iR / 255.0f, CreateCiv.nCiv.iG / 255.0f, CreateCiv.nCiv.iB / 255.0f, fAlpha));
                        Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                    }
                }
            };
        }
        else if (Game.menuManager.getInMapEditorGrowthRate()) {
            ProvinceDraw.drawProvinces = new DrawProvinces() {
                @Override
                public void draw(final SpriteBatch oSB) {
                    if (MenuManager.mapEditorDrawProvinces) {
                        for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                            oSB.setColor(CFG.getGrowthRateColor((int)Game.getProvince(Game.getProvinceInViewID(i)).getGrowthRate(), 0.75f));
                            Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                        for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                            oSB.setColor(CFG.getGrowthRateColor((int)Game.getProvince(Game.getExtraProvinceInViewID(i)).getGrowthRate(), 0.75f));
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                        }
                    }
                }
            };
        }
        else if (Game.menuManager.getInScenarioEditor_CreateAllianceEdit()) {
            ProvinceDraw.drawProvinces = new DrawProvinces() {
                @Override
                public void draw(final SpriteBatch oSB) {
                    for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() > 0) {
                            if (ScenarioCreateAllianceList.editAlliance.isInAlliance(Game.getProvince(Game.getProvinceInViewID(i)).getCivID())) {
                                Game.getProvince(Game.getProvinceInViewID(i)).setProvinceColor(oSB, Game.settingsManager.PROVINCE_ALPHA * 1.75f);
                            }
                            else {
                                Game.getProvince(Game.getProvinceInViewID(i)).setProvinceColor(oSB, Game.settingsManager.PROVINCE_ALPHA / 3.0f);
                            }
                            Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                    }
                    for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() > 0) {
                            if (ScenarioCreateAllianceList.editAlliance.isInAlliance(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID())) {
                                Game.getProvince(Game.getExtraProvinceInViewID(i)).setProvinceColor(oSB, Game.settingsManager.PROVINCE_ALPHA * 1.75f);
                            }
                            else {
                                Game.getProvince(Game.getExtraProvinceInViewID(i)).setProvinceColor(oSB, Game.settingsManager.PROVINCE_ALPHA / 3.0f);
                            }
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                        }
                    }
                }
            };
        }
        else if (Game.menuManager.getInPrintMap()) {
            ProvinceDraw.drawProvinces = new DrawProvinces() {
                @Override
                public void draw(final SpriteBatch oSB) {
                    ProvinceDraw.updateDrawProvinces_Standard();
                }
            };
        }
        else if (Game.menuManager.getInMapEditorEconomy()) {
            ProvinceDraw.drawProvinces = new DrawProvinces() {
                @Override
                public void draw(final SpriteBatch oSB) {
                    if (MenuManager.mapEditorDrawProvinces) {
                        for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                            oSB.setColor(CFG.getGrowthRateColor((int)Game.getProvince(Game.getProvinceInViewID(i)).BaseDevelopment, 0.75f));
                            Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                        for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                            oSB.setColor(CFG.getGrowthRateColor((int)Game.getProvince(Game.getExtraProvinceInViewID(i)).BaseDevelopment, 0.75f));
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                        }
                    }
                }
            };
        }
        else if (Game.menuManager.getInMapEditorLines() || Game.menuManager.getInMapEditorWaves()) {
            ProvinceDraw.drawProvinces = new DrawProvinces() {
                @Override
                public void draw(final SpriteBatch oSB) {
                    ProvinceDraw.updateDrawProvinces_Standard();
                }
            };
        }
        else if (Game.menuManager.getInScenarioEditorAlliances()) {
            ProvinceDraw.drawProvinces = new DrawProvinces() {
                @Override
                public void draw(final SpriteBatch oSB) {
                    for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() != 0) {
                            if (Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).diplomacy.alliance.size() > 0) {
                                Game.getProvince(Game.getProvinceInViewID(i)).setProvinceColor(oSB, 1.0f);
                            }
                            else {
                                Game.getProvince(Game.getProvinceInViewID(i)).setProvinceColor(oSB, Game.settingsManager.PROVINCE_ALPHA);
                            }
                            Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                    }
                    for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() != 0) {
                            if (Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).diplomacy.alliance.size() > 0) {
                                Game.getProvince(Game.getExtraProvinceInViewID(i)).setProvinceColor(oSB, 1.0f);
                            }
                            else {
                                Game.getProvince(Game.getExtraProvinceInViewID(i)).setProvinceColor(oSB, Game.settingsManager.PROVINCE_ALPHA);
                            }
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                        }
                    }
                }
            };
        }
        else if (Game.menuManager.getInScenarioEditorVassals()) {
            ProvinceDraw.drawProvinces = new DrawProvinces() {
                @Override
                public void draw(final SpriteBatch oSB) {
                    for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() != 0) {
                            if (Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).diplomacy.lVassals.size() > 0) {
                                Game.getProvince(Game.getProvinceInViewID(i)).setProvinceColor(oSB, 1.0f);
                            }
                            else {
                                Game.getProvince(Game.getProvinceInViewID(i)).setProvinceColor(oSB, Game.settingsManager.PROVINCE_ALPHA);
                            }
                            Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                    }
                    for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() != 0) {
                            if (Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).diplomacy.lVassals.size() > 0) {
                                Game.getProvince(Game.getExtraProvinceInViewID(i)).setProvinceColor(oSB, 1.0f);
                            }
                            else {
                                Game.getProvince(Game.getExtraProvinceInViewID(i)).setProvinceColor(oSB, Game.settingsManager.PROVINCE_ALPHA);
                            }
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                        }
                    }
                }
            };
        }
        else if (Game.menuManager.getInScenarioEditorTruces()) {
            ProvinceDraw.drawProvinces = new DrawProvinces() {
                @Override
                public void draw(final SpriteBatch oSB) {
                    for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() != 0) {
                            if (Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).diplomacy.truce.size() > 0) {
                                Game.getProvince(Game.getProvinceInViewID(i)).setProvinceColor(oSB, 1.0f);
                            }
                            else {
                                Game.getProvince(Game.getProvinceInViewID(i)).setProvinceColor(oSB, Game.settingsManager.PROVINCE_ALPHA);
                            }
                            Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                    }
                    for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() != 0) {
                            if (Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).diplomacy.truce.size() > 0) {
                                Game.getProvince(Game.getExtraProvinceInViewID(i)).setProvinceColor(oSB, 1.0f);
                            }
                            else {
                                Game.getProvince(Game.getExtraProvinceInViewID(i)).setProvinceColor(oSB, Game.settingsManager.PROVINCE_ALPHA);
                            }
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                        }
                    }
                }
            };
        }
        else if (Game.menuManager.getInScenarioEditorGuarantee()) {
            ProvinceDraw.drawProvinces = new DrawProvinces() {
                @Override
                public void draw(final SpriteBatch oSB) {
                    for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() != 0) {
                            if (Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).diplomacy.guarantee.size() > 0) {
                                Game.getProvince(Game.getProvinceInViewID(i)).setProvinceColor(oSB, 1.0f);
                            }
                            else {
                                Game.getProvince(Game.getProvinceInViewID(i)).setProvinceColor(oSB, Game.settingsManager.PROVINCE_ALPHA);
                            }
                            Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                    }
                    for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() != 0) {
                            if (Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).diplomacy.guarantee.size() > 0) {
                                Game.getProvince(Game.getExtraProvinceInViewID(i)).setProvinceColor(oSB, 1.0f);
                            }
                            else {
                                Game.getProvince(Game.getExtraProvinceInViewID(i)).setProvinceColor(oSB, Game.settingsManager.PROVINCE_ALPHA);
                            }
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                        }
                    }
                }
            };
        }
        else if (Game.menuManager.getInScenarioEditorMilitaryAccess()) {
            ProvinceDraw.drawProvinces = new DrawProvinces() {
                @Override
                public void draw(final SpriteBatch oSB) {
                    for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() != 0) {
                            if (Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).diplomacy.militaryAccess.size() > 0) {
                                Game.getProvince(Game.getProvinceInViewID(i)).setProvinceColor(oSB, 1.0f);
                            }
                            else {
                                Game.getProvince(Game.getProvinceInViewID(i)).setProvinceColor(oSB, Game.settingsManager.PROVINCE_ALPHA);
                            }
                            Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                    }
                    for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() != 0) {
                            if (Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).diplomacy.militaryAccess.size() > 0) {
                                Game.getProvince(Game.getExtraProvinceInViewID(i)).setProvinceColor(oSB, 1.0f);
                            }
                            else {
                                Game.getProvince(Game.getExtraProvinceInViewID(i)).setProvinceColor(oSB, Game.settingsManager.PROVINCE_ALPHA);
                            }
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                        }
                    }
                }
            };
        }
        else if (Game.menuManager.getInScenarioEditorNonAggression()) {
            ProvinceDraw.drawProvinces = new DrawProvinces() {
                @Override
                public void draw(final SpriteBatch oSB) {
                    for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() != 0) {
                            if (Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).diplomacy.nonAggressionPact.size() > 0) {
                                Game.getProvince(Game.getProvinceInViewID(i)).setProvinceColor(oSB, 1.0f);
                            }
                            else {
                                Game.getProvince(Game.getProvinceInViewID(i)).setProvinceColor(oSB, Game.settingsManager.PROVINCE_ALPHA);
                            }
                            Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                    }
                    for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() != 0) {
                            if (Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).diplomacy.nonAggressionPact.size() > 0) {
                                Game.getProvince(Game.getExtraProvinceInViewID(i)).setProvinceColor(oSB, 1.0f);
                            }
                            else {
                                Game.getProvince(Game.getExtraProvinceInViewID(i)).setProvinceColor(oSB, Game.settingsManager.PROVINCE_ALPHA);
                            }
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                        }
                    }
                }
            };
        }
        else if (Game.menuManager.getInScenarioEditorDefensive()) {
            ProvinceDraw.drawProvinces = new DrawProvinces() {
                @Override
                public void draw(final SpriteBatch oSB) {
                    for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() != 0) {
                            if (Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).diplomacy.defensivePact.size() > 0) {
                                Game.getProvince(Game.getProvinceInViewID(i)).setProvinceColor(oSB, 1.0f);
                            }
                            else {
                                Game.getProvince(Game.getProvinceInViewID(i)).setProvinceColor(oSB, Game.settingsManager.PROVINCE_ALPHA);
                            }
                            Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                    }
                    for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() != 0) {
                            if (Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).diplomacy.defensivePact.size() > 0) {
                                Game.getProvince(Game.getExtraProvinceInViewID(i)).setProvinceColor(oSB, 1.0f);
                            }
                            else {
                                Game.getProvince(Game.getExtraProvinceInViewID(i)).setProvinceColor(oSB, Game.settingsManager.PROVINCE_ALPHA);
                            }
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                        }
                    }
                }
            };
        }
        else if (Game.menuManager.getInScenarioEditorRelations()) {
            ProvinceDraw.drawProvinces = new DrawProvinces() {
                @Override
                public void draw(final SpriteBatch oSB) {
                    if (ScenarioRelationsList.activeCivID == 0) {
                        for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                            if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() != 0) {
                                Game.getProvince(Game.getProvinceInViewID(i)).setProvinceColor(oSB, Game.settingsManager.PROVINCE_ALPHA);
                                Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                            }
                        }
                        for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                            if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() != 0) {
                                Game.getProvince(Game.getExtraProvinceInViewID(i)).setProvinceColor(oSB, Game.settingsManager.PROVINCE_ALPHA);
                                Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                            }
                        }
                    }
                    else {
                        for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                            if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() != 0) {
                                if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == ScenarioRelationsList.activeCivID) {
                                    oSB.setColor(new Color(Colors.HOVER_GOLD.r, Colors.HOVER_GOLD.g, Colors.HOVER_GOLD.b, Game.settingsManager.PROVINCE_ALPHA));
                                }
                                else {
                                    final int tempRelation = (int)Game.getCiv(ScenarioRelationsList.activeCivID).diplomacy.getRelation(Game.getProvince(Game.getProvinceInViewID(i)).getCivID());
                                    if (tempRelation == 0) {
                                        oSB.setColor(new Color(ProvinceDraw.PROVINCE_DIPLOMACY_NEUTRAL_GREEN.r, ProvinceDraw.PROVINCE_DIPLOMACY_NEUTRAL_GREEN.g, ProvinceDraw.PROVINCE_DIPLOMACY_NEUTRAL_GREEN.b, Game.settingsManager.PROVINCE_ALPHA));
                                    }
                                    else if (tempRelation > 0) {
                                        oSB.setColor(CFG.getColorStep(ProvinceDraw.PROVINCE_DIPLOMACY_GREEN, ProvinceDraw.PROVINCE_DIPLOMACY_GREEN2, tempRelation, (int)GameValues.diplomacy.DIPLOMACY_RELATIONS_MAX, Game.settingsManager.PROVINCE_ALPHA));
                                    }
                                    else {
                                        oSB.setColor(CFG.getColorStep(ProvinceDraw.PROVINCE_DIPLOMACY_RED, ProvinceDraw.PROVINCE_DIPLOMACY_RED2, -tempRelation, (int)(-GameValues.diplomacy.DIPLOMACY_RELATIONS_MIN), Game.settingsManager.PROVINCE_ALPHA));
                                    }
                                }
                                Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                            }
                        }
                        for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                            if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == 0) {
                                if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() != ScenarioRelationsList.activeCivID) {
                                    oSB.setColor(new Color(Colors.HOVER_GOLD.r, Colors.HOVER_GOLD.g, Colors.HOVER_GOLD.b, Game.settingsManager.PROVINCE_ALPHA));
                                }
                                else {
                                    final int tempRelation = (int)Game.getCiv(ScenarioRelationsList.activeCivID).diplomacy.getRelation(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID());
                                    if (tempRelation == 0) {
                                        oSB.setColor(new Color(ProvinceDraw.PROVINCE_DIPLOMACY_NEUTRAL_GREEN.r, ProvinceDraw.PROVINCE_DIPLOMACY_NEUTRAL_GREEN.g, ProvinceDraw.PROVINCE_DIPLOMACY_NEUTRAL_GREEN.b, Game.settingsManager.PROVINCE_ALPHA));
                                    }
                                    else if (tempRelation > 0) {
                                        oSB.setColor(CFG.getColorStep(ProvinceDraw.PROVINCE_DIPLOMACY_GREEN, ProvinceDraw.PROVINCE_DIPLOMACY_GREEN2, tempRelation, (int)GameValues.diplomacy.DIPLOMACY_RELATIONS_MAX, Game.settingsManager.PROVINCE_ALPHA));
                                    }
                                    else {
                                        oSB.setColor(CFG.getColorStep(ProvinceDraw.PROVINCE_DIPLOMACY_RED, ProvinceDraw.PROVINCE_DIPLOMACY_RED2, -tempRelation, (int)(-GameValues.diplomacy.DIPLOMACY_RELATIONS_MIN), Game.settingsManager.PROVINCE_ALPHA));
                                    }
                                }
                                Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvince(oSB);
                            }
                        }
                    }
                }
            };
        }
        else if (Game.menuManager.getInMapEditorTerrain()) {
            ProvinceDraw.drawProvinces = new DrawProvinces() {
                @Override
                public void draw(final SpriteBatch oSB) {
                    if (MenuManager.mapEditorDrawProvinces) {
                        for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                            oSB.setColor(new Color(Game.terrainManager.terrains.get(Game.getProvince(Game.getProvinceInViewID(i)).getTerrainID()).Color[0], Game.terrainManager.terrains.get(Game.getProvince(Game.getProvinceInViewID(i)).getTerrainID()).Color[1], Game.terrainManager.terrains.get(Game.getProvince(Game.getProvinceInViewID(i)).getTerrainID()).Color[2], 0.55f));
                            Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                        for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                            oSB.setColor(new Color(Game.terrainManager.terrains.get(Game.getProvince(Game.getExtraProvinceInViewID(i)).getTerrainID()).Color[0], Game.terrainManager.terrains.get(Game.getProvince(Game.getExtraProvinceInViewID(i)).getTerrainID()).Color[1], Game.terrainManager.terrains.get(Game.getProvince(Game.getExtraProvinceInViewID(i)).getTerrainID()).Color[2], 0.55f));
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                        }
                    }
                }
            };
        }
        else if (Game.menuManager.getInScenarioEditorTechnologiesCivs()) {
            ProvinceDraw.drawProvinces = new DrawProvinces() {
                @Override
                public void draw(final SpriteBatch oSB) {
                    for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() > 0) {
                            if (Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).scenarioEditorData.TechnologyID == MapScenarios.DEFAULT_VALUE) {
                                oSB.setColor(Colors.getWhiteBlackColor((int)(Math.max(Math.max(0, MapScenarios.scenarioEditorDetails.CivDefault_Technology), Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).scenarioEditorData.TechnologyID) / (float)TechnologyTree.iTechnologySize * 100.0f), Game.settingsManager.PROVINCE_ALPHA));
                            }
                            else {
                                oSB.setColor(Colors.getWhiteBlackColor((int)(Math.max(0, Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).scenarioEditorData.TechnologyID) / (float)TechnologyTree.iTechnologySize * 100.0f), Game.settingsManager.PROVINCE_ALPHA));
                            }
                            Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                    }
                    for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() > 0) {
                            if (Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).scenarioEditorData.TechnologyID == MapScenarios.DEFAULT_VALUE) {
                                oSB.setColor(Colors.getWhiteBlackColor((int)(Math.max(Math.max(0, MapScenarios.scenarioEditorDetails.CivDefault_Technology), Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).scenarioEditorData.TechnologyID) / (float)TechnologyTree.iTechnologySize * 100.0f), Game.settingsManager.PROVINCE_ALPHA));
                            }
                            else {
                                oSB.setColor(Colors.getWhiteBlackColor((int)(Math.max(0, Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).scenarioEditorData.TechnologyID) / (float)TechnologyTree.iTechnologySize * 100.0f), Game.settingsManager.PROVINCE_ALPHA));
                            }
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                        }
                    }
                }
            };
        }
        else if (Game.menuManager.getInEditorFormableCiv()) {
            ProvinceDraw.drawProvinces = new DrawProvinces() {
                @Override
                public void draw(final SpriteBatch oSB) {
                    if (EditorMap_FormableCiv.drawMap) {
                        ProvinceDraw.drawProvinces_Standard(oSB);
                    }
                    oSB.setShader(Renderer.shaderDefaultProvince);
                    try {
                        for (int i = 0; i < FormableCivManager.activeFormableCiv.Provinces.size(); ++i) {
                            if (Game.getProvince(FormableCivManager.activeFormableCiv.Provinces.get(i)).getDrawProvince()) {
                                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, Game.settingsManager.PROVINCE_ALPHA));
                                Game.getProvince(FormableCivManager.activeFormableCiv.Provinces.get(i)).drawLandProvince(oSB);
                            }
                        }
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                    oSB.setShader(Renderer.shaderDefault);
                }
            };
        }
        else if (Game.menuManager.getInEditorSelectProvinces()) {
            ProvinceDraw.drawProvinces = new DrawProvinces() {
                @Override
                public void draw(final SpriteBatch oSB) {
                    if (MenuManager.mapEditorDrawProvinces) {
                        ProvinceDraw.drawProvinces_Standard(oSB);
                    }
                    try {
                        for (int i = 0; i < EditorSelectProvinces.selectedProvinces.size(); ++i) {
                            if (Game.getProvince(EditorSelectProvinces.selectedProvinces.get(i)).getDrawProvince()) {
                                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, Game.settingsManager.PROVINCE_ALPHA));
                                Game.getProvince(EditorSelectProvinces.selectedProvinces.get(i)).drawLandProvince(oSB);
                            }
                        }
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            };
        }
        else if (Game.menuManager.getInScenarioCores()) {
            ProvinceDraw.drawProvinces = new DrawProvinces() {
                @Override
                public void draw(final SpriteBatch oSB) {
                    if (MenuManager.mapEditorDrawProvinces) {
                        ProvinceDraw.drawProvinces_Standard(oSB);
                    }
                    try {
                        for (int i = 0; i < EditorSelectProvinces.selectedProvinces.size(); ++i) {
                            if (Game.getProvince(EditorSelectProvinces.selectedProvinces.get(i)).getDrawProvince()) {
                                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, Game.settingsManager.PROVINCE_ALPHA));
                                Game.getProvince(EditorSelectProvinces.selectedProvinces.get(i)).drawLandProvince(oSB);
                            }
                        }
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            };
        }
        else if (Game.menuManager.getInScenarioReligion()) {
            ProvinceDraw.drawProvinces = new DrawProvinces() {
                @Override
                public void draw(final SpriteBatch oSB) {
                    if (MenuManager.mapEditorDrawProvinces) {
                        ProvinceDraw.drawProvinces_Standard(oSB);
                    }
                    try {
                        for (int i = 0; i < EditorSelectProvinces.selectedProvinces.size(); ++i) {
                            if (Game.getProvince(EditorSelectProvinces.selectedProvinces.get(i)).getDrawProvince()) {
                                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, Game.settingsManager.PROVINCE_ALPHA));
                                Game.getProvince(EditorSelectProvinces.selectedProvinces.get(i)).drawLandProvince(oSB);
                            }
                        }
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            };
        }
        else if (Game.menuManager.getInScenarioEditorBuildings()) {
            ProvinceDraw.drawProvinces = new DrawProvinces() {
                @Override
                public void draw(final SpriteBatch oSB) {
                    for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() != 0) {
                            Game.getProvince(Game.getProvinceInViewID(i)).setProvinceColor(oSB, (Game.getProvince(Game.getProvinceInViewID(i)).iBuildingsSize > 0) ? 1.0f : Game.settingsManager.PROVINCE_ALPHA);
                            Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                    }
                    for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() != 0) {
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).setProvinceColor(oSB, (Game.getProvince(Game.getProvinceInViewID(i)).iBuildingsSize > 0) ? 1.0f : Game.settingsManager.PROVINCE_ALPHA);
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                        }
                    }
                    try {
                        for (int i = 0; i < EditorSelectProvinces.selectedProvinces.size(); ++i) {
                            if (Game.getProvince(EditorSelectProvinces.selectedProvinces.get(i)).getDrawProvince()) {
                                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, Game.settingsManager.PROVINCE_ALPHA));
                                Game.getProvince(EditorSelectProvinces.selectedProvinces.get(i)).drawLandProvince(oSB);
                            }
                        }
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            };
        }
        else if (Game.menuManager.getInMapEditorResource()) {
            ProvinceDraw.drawProvinces = new DrawProvinces() {
                @Override
                public void draw(final SpriteBatch oSB) {
                    if (MenuManager.mapEditorDrawProvinces) {
                        for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                            if (Game.getProvince(Game.getProvinceInViewID(i)).getResourceID() >= 0) {
                                oSB.setColor(new Color(ResourcesManager.lResources.get(Game.getProvince(Game.getProvinceInViewID(i)).getResourceID()).Color[0], ResourcesManager.lResources.get(Game.getProvince(Game.getProvinceInViewID(i)).getResourceID()).Color[1], ResourcesManager.lResources.get(Game.getProvince(Game.getProvinceInViewID(i)).getResourceID()).Color[2], 0.55f));
                                Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                            }
                        }
                        for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                            if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getResourceID() >= 0) {
                                oSB.setColor(new Color(ResourcesManager.lResources.get(Game.getProvince(Game.getExtraProvinceInViewID(i)).getResourceID()).Color[0], ResourcesManager.lResources.get(Game.getProvince(Game.getExtraProvinceInViewID(i)).getResourceID()).Color[1], ResourcesManager.lResources.get(Game.getProvince(Game.getExtraProvinceInViewID(i)).getResourceID()).Color[2], 0.55f));
                                Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                            }
                        }
                    }
                }
            };
        }
        else if (Game.menuManager.getInMapEditorContinents()) {
            ProvinceDraw.drawProvinces = new DrawProvinces() {
                @Override
                public void draw(final SpriteBatch oSB) {
                    for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        oSB.setColor(new Color(Game.continents.lContinents.get(Game.getProvince(Game.getProvinceInViewID(i)).getContinent()).iR / 255.0f, Game.continents.lContinents.get(Game.getProvince(Game.getProvinceInViewID(i)).getContinent()).iG / 255.0f, Game.continents.lContinents.get(Game.getProvince(Game.getProvinceInViewID(i)).getContinent()).iB / 255.0f, 0.55f));
                        Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                    }
                    for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                        oSB.setColor(new Color(Game.continents.lContinents.get(Game.getProvince(Game.getExtraProvinceInViewID(i)).getContinent()).iR / 255.0f, Game.continents.lContinents.get(Game.getProvince(Game.getExtraProvinceInViewID(i)).getContinent()).iG / 255.0f, Game.continents.lContinents.get(Game.getProvince(Game.getExtraProvinceInViewID(i)).getContinent()).iB / 255.0f, 0.55f));
                        Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                    }
                }
            };
        }
        else if (Game.menuManager.getInMapEditorGeoRegions()) {
            ProvinceDraw.drawProvinces = new DrawProvinces() {
                @Override
                public void draw(final SpriteBatch oSB) {
                    for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        oSB.setColor(new Color(Game.geographicalRegions.lGeographicalRegions.get(Game.getProvince(Game.getProvinceInViewID(i)).getGeoRegion()).iR / 255.0f, Game.geographicalRegions.lGeographicalRegions.get(Game.getProvince(Game.getProvinceInViewID(i)).getGeoRegion()).iG / 255.0f, Game.geographicalRegions.lGeographicalRegions.get(Game.getProvince(Game.getProvinceInViewID(i)).getGeoRegion()).iB / 255.0f, 0.55f));
                        Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                    }
                    for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                        oSB.setColor(new Color(Game.geographicalRegions.lGeographicalRegions.get(Game.getProvince(Game.getExtraProvinceInViewID(i)).getGeoRegion()).iR / 255.0f, Game.geographicalRegions.lGeographicalRegions.get(Game.getProvince(Game.getExtraProvinceInViewID(i)).getGeoRegion()).iG / 255.0f, Game.geographicalRegions.lGeographicalRegions.get(Game.getProvince(Game.getExtraProvinceInViewID(i)).getGeoRegion()).iB / 255.0f, 0.55f));
                        Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                    }
                }
            };
        }
        else if (Game.menuManager.getInMapEditorOptimizationRegions()) {
            ProvinceDraw.drawProvinces = new DrawProvinces() {
                @Override
                public void draw(final SpriteBatch oSB) {
                    for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        oSB.setColor(new Color(EditorMapOptimizationRegions.lColors.get(Game.regions.getRegionID(Game.getProvinceInViewID(i))).r, EditorMapOptimizationRegions.lColors.get(Game.regions.getRegionID(Game.getProvinceInViewID(i))).g, EditorMapOptimizationRegions.lColors.get(Game.regions.getRegionID(Game.getProvinceInViewID(i))).b, 0.55f));
                        Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                    }
                    for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                        oSB.setColor(new Color(EditorMapOptimizationRegions.lColors.get(Game.regions.getRegionID(Game.getExtraProvinceInViewID(i))).r, EditorMapOptimizationRegions.lColors.get(Game.regions.getRegionID(Game.getExtraProvinceInViewID(i))).g, EditorMapOptimizationRegions.lColors.get(Game.regions.getRegionID(Game.getExtraProvinceInViewID(i))).b, 0.55f));
                        Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                    }
                    if (Game.iActiveProvince >= 0) {
                        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.2f));
                        Images.pix.draw(oSB, Game.regions.lRegions.get(Game.regions.getRegionID(Game.iActiveProvince)).getMinX() + Game.mapCoords.getPosX(), Game.regions.lRegions.get(Game.regions.getRegionID(Game.iActiveProvince)).getMinY() + Game.mapCoords.getPosY(), Game.regions.lRegions.get(Game.regions.getRegionID(Game.iActiveProvince)).getMaxX() - Game.regions.lRegions.get(Game.regions.getRegionID(Game.iActiveProvince)).getMinX(), Game.regions.lRegions.get(Game.regions.getRegionID(Game.iActiveProvince)).getMaxY() - Game.regions.lRegions.get(Game.regions.getRegionID(Game.iActiveProvince)).getMinY());
                        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.6f));
                        Renderer.drawRect(oSB, Game.regions.lRegions.get(Game.regions.getRegionID(Game.iActiveProvince)).getMinX() + Game.mapCoords.getPosX(), Game.regions.lRegions.get(Game.regions.getRegionID(Game.iActiveProvince)).getMinY() + Game.mapCoords.getPosY(), Game.regions.lRegions.get(Game.regions.getRegionID(Game.iActiveProvince)).getMaxX() - Game.regions.lRegions.get(Game.regions.getRegionID(Game.iActiveProvince)).getMinX(), Game.regions.lRegions.get(Game.regions.getRegionID(Game.iActiveProvince)).getMaxY() - Game.regions.lRegions.get(Game.regions.getRegionID(Game.iActiveProvince)).getMinY());
                        if (Game.map.getMapWorldMap(Game.map.getActiveMapID())) {
                            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.2f));
                            Images.pix.draw(oSB, Game.regions.lRegions.get(Game.regions.getRegionID(Game.iActiveProvince)).getMinX() + Game.mapCoords.getPosX() + Game.mapBG.getWidth(), Game.regions.lRegions.get(Game.regions.getRegionID(Game.iActiveProvince)).getMinY() + Game.mapCoords.getPosY(), Game.regions.lRegions.get(Game.regions.getRegionID(Game.iActiveProvince)).getMaxX() - Game.regions.lRegions.get(Game.regions.getRegionID(Game.iActiveProvince)).getMinX(), Game.regions.lRegions.get(Game.regions.getRegionID(Game.iActiveProvince)).getMaxY() - Game.regions.lRegions.get(Game.regions.getRegionID(Game.iActiveProvince)).getMinY());
                            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.6f));
                            Renderer.drawRect(oSB, Game.regions.lRegions.get(Game.regions.getRegionID(Game.iActiveProvince)).getMinX() + Game.mapCoords.getPosX() + Game.mapBG.getWidth(), Game.regions.lRegions.get(Game.regions.getRegionID(Game.iActiveProvince)).getMinY() + Game.mapCoords.getPosY(), Game.regions.lRegions.get(Game.regions.getRegionID(Game.iActiveProvince)).getMaxX() - Game.regions.lRegions.get(Game.regions.getRegionID(Game.iActiveProvince)).getMinX(), Game.regions.lRegions.get(Game.regions.getRegionID(Game.iActiveProvince)).getMaxY() - Game.regions.lRegions.get(Game.regions.getRegionID(Game.iActiveProvince)).getMinY());
                        }
                    }
                }
            };
        }
        else if (Game.menuManager.getInMapEditorSeaProvinces()) {
            ProvinceDraw.drawProvinces = new DrawProvinces() {
                @Override
                public void draw(final SpriteBatch oSB) {
                    try {
                        for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                            if (Game.getProvince(Game.getProvinceInViewID(i)).getLevelOfPort() >= -1) {
                                oSB.setColor(new Color(0.1254902f, 0.2901961f, 0.043137256f, 0.6f));
                                Game.getProvince(Game.getProvinceInViewID(i)).drawProvince_ActiveProvince(oSB);
                            }
                            else if (Game.getProvince(Game.getProvinceInViewID(i)).getLevelOfPort() == -1) {
                                oSB.setColor(new Color(0.02745098f, 0.12941177f, 0.18431373f, 0.6f));
                                Game.getProvince(Game.getProvinceInViewID(i)).drawProvince_ActiveProvince(oSB);
                            }
                            else {
                                oSB.setColor(new Color(0.007843138f, 0.09411765f, 0.13725491f, 0.6f));
                                Game.getProvince(Game.getProvinceInViewID(i)).drawProvince_ActiveProvince(oSB);
                            }
                            Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                        for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                            if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getLevelOfPort() >= -1) {
                                oSB.setColor(new Color(0.1254902f, 0.2901961f, 0.043137256f, 0.6f));
                                Game.getProvince(Game.getExtraProvinceInViewID(i)).drawProvince_ActiveProvince(oSB);
                            }
                            else if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getLevelOfPort() == -1) {
                                oSB.setColor(new Color(0.02745098f, 0.12941177f, 0.18431373f, 0.6f));
                                Game.getProvince(Game.getExtraProvinceInViewID(i)).drawProvince_ActiveProvince(oSB);
                            }
                            else {
                                oSB.setColor(new Color(0.007843138f, 0.09411765f, 0.13725491f, 0.6f));
                                Game.getProvince(Game.getExtraProvinceInViewID(i)).drawProvince_ActiveProvince(oSB);
                            }
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                        }
                    }
                    catch (final Exception ex) {}
                }
            };
        }
        else {
            ProvinceDraw.drawProvinces = new DrawProvinces() {
                @Override
                public void draw(final SpriteBatch oSB) {
                    ProvinceDraw.updateDrawProvinces_Standard();
                }
            };
        }
    }
    
    public static final void drawRecruitingArmyPlayer(final SpriteBatch oSB) {
        final Civilization civPlayer = Game.getCiv(Game.player.iCivID);
        final Image progressBarFrame = ImageManager.getImage(Images.progressBarFrame);
        final Image progressBarFrameMask = ImageManager.getImage(Images.progressBarFrameMask);
        final Image unitsFrameMap = ImageManager.getImage(Images.unitsFrameMap);
        for (int i = 0; i < civPlayer.getArmyRecruitSize(); ++i) {
            for (int j = 0; j < civPlayer.lArmyRecruit.get(i).size() && j < 1; ++j) {
                final ArmyRecruit armyRecruit = civPlayer.lArmyRecruit.get(i).get(j);
                final Province province = Game.getProvince(armyRecruit.provinceID);
                if (province.getDrawProvince()) {
                    int extraY = (province.iBuildingsConstructionSize > 0) ? 1 : 0;
                    if (province.wonderConstruction != null) {
                        ++extraY;
                    }
                    oSB.setShader(Renderer.shaderAlpha);
                    ArmyManager.armyImages.get(ArmyManager.lArmy.get(armyRecruit.unitID).get(armyRecruit.armyID).ImageID).getTexture().bind(1);
                    Gdx.gl.glActiveTexture(33984);
                    ImageManager.getImage(Images.unitsFrameMapMask).draw(oSB, (int)((province.getTranslateProvincePosX() + province.iCenterShiftX) * Game.mapScale.getCurrentScale() - unitsFrameMap.getWidth() / 2), (int)((Game.mapCoords.getPosY() + province.iCenterShiftY) * Game.mapScale.getCurrentScale() - ProvinceDrawArmy.getArmyHeight() / 2 - unitsFrameMap.getHeight() * (j + extraY + 1) - 1 * j));
                    oSB.flush();
                    oSB.setShader(Renderer.shaderDefault);
                    unitsFrameMap.draw(oSB, (int)((province.getTranslateProvincePosX() + province.iCenterShiftX) * Game.mapScale.getCurrentScale() - unitsFrameMap.getWidth() / 2), (int)((Game.mapCoords.getPosY() + province.iCenterShiftY) * Game.mapScale.getCurrentScale() - ProvinceDrawArmy.getArmyHeight() / 2 - unitsFrameMap.getHeight() * (j + extraY + 1) - 1 * j));
                    final int tCenterX = (progressBarFrame.getWidth() - progressBarFrameMask.getWidth()) / 2;
                    final int tCenterY = (progressBarFrame.getHeight() - progressBarFrameMask.getHeight()) / 2;
                    oSB.setColor(new Color(ProvinceDraw.progressBarBG));
                    progressBarFrameMask.draw(oSB, tCenterX + (int)((province.getTranslateProvincePosX() + province.iCenterShiftX) * Game.mapScale.getCurrentScale() - unitsFrameMap.getWidth() / 2), tCenterY + (int)((Game.mapCoords.getPosY() + province.iCenterShiftY) * Game.mapScale.getCurrentScale() - ProvinceDrawArmy.getArmyHeight() / 2 - unitsFrameMap.getHeight() * (j + extraY + 1) - 1 * j + unitsFrameMap.getHeight() - progressBarFrame.getHeight()));
                    oSB.setColor(ProvinceDraw.progressBar);
                    progressBarFrameMask.draw(oSB, tCenterX + (int)((province.getTranslateProvincePosX() + province.iCenterShiftX) * Game.mapScale.getCurrentScale() - unitsFrameMap.getWidth() / 2), tCenterY + (int)((Game.mapCoords.getPosY() + province.iCenterShiftY) * Game.mapScale.getCurrentScale() - ProvinceDrawArmy.getArmyHeight() / 2 - unitsFrameMap.getHeight() * (j + extraY + 1) - 1 * j + unitsFrameMap.getHeight() - progressBarFrame.getHeight()), (int)Math.max(1.0f, progressBarFrameMask.getWidth() * ((ArmyManager.lArmy.get(armyRecruit.unitID).get(armyRecruit.armyID).RecruitmentTime - armyRecruit.timeLeft) / (float)ArmyManager.lArmy.get(armyRecruit.unitID).get(armyRecruit.armyID).RecruitmentTime)), progressBarFrameMask.getHeight());
                    oSB.setColor(Color.WHITE);
                    progressBarFrame.draw(oSB, (int)((province.getTranslateProvincePosX() + province.iCenterShiftX) * Game.mapScale.getCurrentScale() - unitsFrameMap.getWidth() / 2), (int)((Game.mapCoords.getPosY() + province.iCenterShiftY) * Game.mapScale.getCurrentScale() - ProvinceDrawArmy.getArmyHeight() / 2 - unitsFrameMap.getHeight() * (j + extraY + 1) - 1 * j + unitsFrameMap.getHeight() - progressBarFrame.getHeight()));
                }
            }
        }
    }
    
    public static final void drawWondersConstruction(final SpriteBatch oSB) {
        try {
            for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (Game.getProvince(Game.getProvinceInViewID(i)).wonderConstruction != null) {
                    oSB.setShader(Renderer.shaderAlpha);
                    WondersManager.wonderImages.get(WondersManager.wonders.get(Game.getProvince(Game.getProvinceInViewID(i)).wonderID).ImageID).getTexture().bind(1);
                    Gdx.gl.glActiveTexture(33984);
                    ImageManager.getImage(Images.unitsFrameMapMask).draw(oSB, (int)((Game.getProvince(Game.getProvinceInViewID(i)).getTranslateProvincePosX() + Game.getProvince(Game.getProvinceInViewID(i)).iCenterShiftX) * Game.mapScale.getCurrentScale() - ImageManager.getImage(Images.unitsFrameMap).getWidth() / 2), (int)((Game.mapCoords.getPosY() + Game.getProvince(Game.getProvinceInViewID(i)).iCenterShiftY) * Game.mapScale.getCurrentScale() - ProvinceDrawArmy.getArmyHeight() / 2 - ImageManager.getImage(Images.unitsFrameMap).getHeight()));
                    oSB.flush();
                    oSB.setShader(Renderer.shaderDefault);
                    ImageManager.getImage(Images.unitsFrameMap).draw(oSB, (int)((Game.getProvince(Game.getProvinceInViewID(i)).getTranslateProvincePosX() + Game.getProvince(Game.getProvinceInViewID(i)).iCenterShiftX) * Game.mapScale.getCurrentScale() - ImageManager.getImage(Images.unitsFrameMap).getWidth() / 2), (int)((Game.mapCoords.getPosY() + Game.getProvince(Game.getProvinceInViewID(i)).iCenterShiftY) * Game.mapScale.getCurrentScale() - ProvinceDrawArmy.getArmyHeight() / 2 - ImageManager.getImage(Images.unitsFrameMap).getHeight()));
                    final int tCenterX = (ImageManager.getImage(Images.progressBarFrame).getWidth() - ImageManager.getImage(Images.progressBarFrameMask).getWidth()) / 2;
                    final int tCenterY = (ImageManager.getImage(Images.progressBarFrame).getHeight() - ImageManager.getImage(Images.progressBarFrameMask).getHeight()) / 2;
                    oSB.setColor(new Color(ProvinceDraw.progressBarBG));
                    ImageManager.getImage(Images.progressBarFrameMask).draw(oSB, tCenterX + (int)((Game.getProvince(Game.getProvinceInViewID(i)).getTranslateProvincePosX() + Game.getProvince(Game.getProvinceInViewID(i)).iCenterShiftX) * Game.mapScale.getCurrentScale() - ImageManager.getImage(Images.unitsFrameMap).getWidth() / 2), tCenterY + (int)((Game.mapCoords.getPosY() + Game.getProvince(Game.getProvinceInViewID(i)).iCenterShiftY) * Game.mapScale.getCurrentScale() - ProvinceDrawArmy.getArmyHeight() / 2 - ImageManager.getImage(Images.unitsFrameMap).getHeight() + ImageManager.getImage(Images.unitsFrameMap).getHeight() - ImageManager.getImage(Images.progressBarFrame).getHeight()));
                    oSB.setColor(ProvinceDraw.progressBar);
                    ImageManager.getImage(Images.progressBarFrameMask).draw(oSB, tCenterX + (int)((Game.getProvince(Game.getProvinceInViewID(i)).getTranslateProvincePosX() + Game.getProvince(Game.getProvinceInViewID(i)).iCenterShiftX) * Game.mapScale.getCurrentScale() - ImageManager.getImage(Images.unitsFrameMap).getWidth() / 2), tCenterY + (int)((Game.mapCoords.getPosY() + Game.getProvince(Game.getProvinceInViewID(i)).iCenterShiftY) * Game.mapScale.getCurrentScale() - ProvinceDrawArmy.getArmyHeight() / 2 - ImageManager.getImage(Images.unitsFrameMap).getHeight() + ImageManager.getImage(Images.unitsFrameMap).getHeight() - ImageManager.getImage(Images.progressBarFrame).getHeight()), (int)(ImageManager.getImage(Images.progressBarFrameMask).getWidth() * (1.0f - Game.getProvince(Game.getProvinceInViewID(i)).wonderConstruction.daysLeft / (float)Game.getProvince(Game.getProvinceInViewID(i)).wonderConstruction.investTime)), ImageManager.getImage(Images.progressBarFrameMask).getHeight());
                    oSB.setColor(Color.WHITE);
                    ImageManager.getImage(Images.progressBarFrame).draw(oSB, (int)((Game.getProvince(Game.getProvinceInViewID(i)).getTranslateProvincePosX() + Game.getProvince(Game.getProvinceInViewID(i)).iCenterShiftX) * Game.mapScale.getCurrentScale() - ImageManager.getImage(Images.unitsFrameMap).getWidth() / 2), (int)((Game.mapCoords.getPosY() + Game.getProvince(Game.getProvinceInViewID(i)).iCenterShiftY) * Game.mapScale.getCurrentScale() - ProvinceDrawArmy.getArmyHeight() / 2 - ImageManager.getImage(Images.unitsFrameMap).getHeight() + ImageManager.getImage(Images.unitsFrameMap).getHeight() - ImageManager.getImage(Images.progressBarFrame).getHeight()));
                }
            }
        }
        catch (final Exception ex) {}
    }
    
    public static final void drawBuildingsInConstruction(final SpriteBatch oSB) {
        try {
            if (Game.FOG_OF_WAR) {
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    try {
                        final Province province = Game.getProvince(Game.getProvinceInViewID(i));
                        if (province.getCivID() == Game.player.iCivID && province.iBuildingsConstructionSize > 0) {
                            final int extraY = (province.wonderConstruction != null) ? 1 : 0;
                            final Image unitsFrame = ImageManager.getImage(Images.unitsFrameMap);
                            final Image progressBarFrame = ImageManager.getImage(Images.progressBarFrame);
                            final Image progressBarFrameMask = ImageManager.getImage(Images.progressBarFrameMask);
                            oSB.setShader(Renderer.shaderAlpha);
                            BuildingsManager.buildingImages.get(BuildingsManager.buildings.get(province.getBuildingsConstruction(0).getBuilding()).ImageID[province.getBuildingsConstruction(0).getBuildingID()]).getTexture().bind(1);
                            Gdx.gl.glActiveTexture(33984);
                            ImageManager.getImage(Images.unitsFrameMapMask).draw(oSB, (int)((province.getTranslateProvincePosX() + province.iCenterShiftX) * Game.mapScale.getCurrentScale() - unitsFrame.getWidth() / 2), (int)((Game.mapCoords.getPosY() + province.iCenterShiftY) * Game.mapScale.getCurrentScale() - ProvinceDrawArmy.getArmyHeight() / 2 - unitsFrame.getHeight() - unitsFrame.getHeight() * extraY));
                            oSB.flush();
                            oSB.setShader(Renderer.shaderDefault);
                            unitsFrame.draw(oSB, (int)((province.getTranslateProvincePosX() + province.iCenterShiftX) * Game.mapScale.getCurrentScale() - unitsFrame.getWidth() / 2), (int)((Game.mapCoords.getPosY() + province.iCenterShiftY) * Game.mapScale.getCurrentScale() - ProvinceDrawArmy.getArmyHeight() / 2 - unitsFrame.getHeight() - unitsFrame.getHeight() * extraY));
                            final int tCenterX = (progressBarFrame.getWidth() - progressBarFrameMask.getWidth()) / 2;
                            final int tCenterY = (progressBarFrame.getHeight() - progressBarFrameMask.getHeight()) / 2;
                            oSB.setColor(new Color(ProvinceDraw.progressBarBG));
                            progressBarFrameMask.draw(oSB, tCenterX + (int)((province.getTranslateProvincePosX() + province.iCenterShiftX) * Game.mapScale.getCurrentScale() - unitsFrame.getWidth() / 2), tCenterY + (int)((Game.mapCoords.getPosY() + province.iCenterShiftY) * Game.mapScale.getCurrentScale() - ProvinceDrawArmy.getArmyHeight() / 2 - unitsFrame.getHeight() + unitsFrame.getHeight() - progressBarFrame.getHeight() - unitsFrame.getHeight() * extraY));
                            oSB.setColor(ProvinceDraw.progressBar);
                            progressBarFrameMask.draw(oSB, tCenterX + (int)((province.getTranslateProvincePosX() + province.iCenterShiftX) * Game.mapScale.getCurrentScale() - unitsFrame.getWidth() / 2), tCenterY + (int)((Game.mapCoords.getPosY() + province.iCenterShiftY) * Game.mapScale.getCurrentScale() - ProvinceDrawArmy.getArmyHeight() / 2 - unitsFrame.getHeight() + unitsFrame.getHeight() - progressBarFrame.getHeight() - unitsFrame.getHeight() * extraY), (int)(progressBarFrameMask.getWidth() * (1.0f - province.getBuildingsConstruction(0).getConstructionTimeLeft() / (float)province.getBuildingsConstruction(0).getConstructionTime())), progressBarFrameMask.getHeight());
                            oSB.setColor(Color.WHITE);
                            progressBarFrame.draw(oSB, (int)((province.getTranslateProvincePosX() + province.iCenterShiftX) * Game.mapScale.getCurrentScale() - unitsFrame.getWidth() / 2), (int)((Game.mapCoords.getPosY() + province.iCenterShiftY) * Game.mapScale.getCurrentScale() - ProvinceDrawArmy.getArmyHeight() / 2 - unitsFrame.getHeight() + unitsFrame.getHeight() - progressBarFrame.getHeight() - unitsFrame.getHeight() * extraY));
                        }
                    }
                    catch (final Exception ex) {}
                }
            }
            else {
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    try {
                        final Province province = Game.getProvince(Game.getProvinceInViewID(i));
                        if (province.iBuildingsConstructionSize > 0) {
                            final int extraY = (province.wonderConstruction != null) ? 1 : 0;
                            final Image unitsFrame = ImageManager.getImage(Images.unitsFrameMap);
                            final Image progressBarFrame = ImageManager.getImage(Images.progressBarFrame);
                            final Image progressBarFrameMask = ImageManager.getImage(Images.progressBarFrameMask);
                            oSB.setShader(Renderer.shaderAlpha);
                            BuildingsManager.buildingImages.get(BuildingsManager.buildings.get(province.getBuildingsConstruction(0).getBuilding()).ImageID[province.getBuildingsConstruction(0).getBuildingID()]).getTexture().bind(1);
                            Gdx.gl.glActiveTexture(33984);
                            ImageManager.getImage(Images.unitsFrameMapMask).draw(oSB, (int)((province.getTranslateProvincePosX() + province.iCenterShiftX) * Game.mapScale.getCurrentScale() - unitsFrame.getWidth() / 2), (int)((Game.mapCoords.getPosY() + province.iCenterShiftY) * Game.mapScale.getCurrentScale() - ProvinceDrawArmy.getArmyHeight() / 2 - unitsFrame.getHeight() - unitsFrame.getHeight() * extraY));
                            oSB.flush();
                            oSB.setShader(Renderer.shaderDefault);
                            unitsFrame.draw(oSB, (int)((province.getTranslateProvincePosX() + province.iCenterShiftX) * Game.mapScale.getCurrentScale() - unitsFrame.getWidth() / 2), (int)((Game.mapCoords.getPosY() + province.iCenterShiftY) * Game.mapScale.getCurrentScale() - ProvinceDrawArmy.getArmyHeight() / 2 - unitsFrame.getHeight() - unitsFrame.getHeight() * extraY));
                            final int tCenterX = (progressBarFrame.getWidth() - progressBarFrameMask.getWidth()) / 2;
                            final int tCenterY = (progressBarFrame.getHeight() - progressBarFrameMask.getHeight()) / 2;
                            oSB.setColor(new Color(ProvinceDraw.progressBarBG));
                            progressBarFrameMask.draw(oSB, tCenterX + (int)((province.getTranslateProvincePosX() + province.iCenterShiftX) * Game.mapScale.getCurrentScale() - unitsFrame.getWidth() / 2), tCenterY + (int)((Game.mapCoords.getPosY() + province.iCenterShiftY) * Game.mapScale.getCurrentScale() - ProvinceDrawArmy.getArmyHeight() / 2 - unitsFrame.getHeight() + unitsFrame.getHeight() - progressBarFrame.getHeight() - unitsFrame.getHeight() * extraY));
                            oSB.setColor(ProvinceDraw.progressBar);
                            progressBarFrameMask.draw(oSB, tCenterX + (int)((province.getTranslateProvincePosX() + province.iCenterShiftX) * Game.mapScale.getCurrentScale() - unitsFrame.getWidth() / 2), tCenterY + (int)((Game.mapCoords.getPosY() + province.iCenterShiftY) * Game.mapScale.getCurrentScale() - ProvinceDrawArmy.getArmyHeight() / 2 - unitsFrame.getHeight() + unitsFrame.getHeight() - progressBarFrame.getHeight() - unitsFrame.getHeight() * extraY), (int)(progressBarFrameMask.getWidth() * (1.0f - province.getBuildingsConstruction(0).getConstructionTimeLeft() / (float)province.getBuildingsConstruction(0).getConstructionTime())), progressBarFrameMask.getHeight());
                            oSB.setColor(Color.WHITE);
                            progressBarFrame.draw(oSB, (int)((province.getTranslateProvincePosX() + province.iCenterShiftX) * Game.mapScale.getCurrentScale() - unitsFrame.getWidth() / 2), (int)((Game.mapCoords.getPosY() + province.iCenterShiftY) * Game.mapScale.getCurrentScale() - ProvinceDrawArmy.getArmyHeight() / 2 - unitsFrame.getHeight() + unitsFrame.getHeight() - progressBarFrame.getHeight() - unitsFrame.getHeight() * extraY));
                        }
                    }
                    catch (final Exception ex2) {}
                }
            }
        }
        catch (final Exception ex3) {}
    }
    
    public static final void updateDrawProvinces_Standard() {
        ProvinceDraw.drawProvinces = new DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                ProvinceDraw.drawProvinces_Standard(oSB);
            }
        };
    }
    
    public static final void drawProvinces_StandardFog2(final SpriteBatch oSB) {
        final float fProvinceAlpha = getLandProvinces_Alpha();
        for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() != 0) {
                Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince_Fog(oSB, fProvinceAlpha);
            }
        }
        for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() != 0) {
                Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvince_Fog(oSB, fProvinceAlpha);
            }
        }
    }
    
    public static final void drawProvinces_Standard(final SpriteBatch oSB) {
        drawProvinces_Standard_InvasionArmy(oSB);
        try {
            drawWastelandProvinces(oSB);
            drawProvinces_Standard_LandProvinces(oSB, getLandProvinces_Alpha());
            drawOccupiedProvinces(oSB);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        drawProvinces_Standard_RegroupArmy(oSB);
    }
    
    public static final void drawProvinces_Standard_FBO(final SpriteBatch oSB) {
        drawProvinces_Standard_InvasionArmy(oSB);
        try {
            drawWastelandProvinces(oSB);
            drawProvinces_Standard_LandProvinces_FBO(oSB);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        drawProvinces_Standard_RegroupArmy(oSB);
    }
    
    public static final void drawProvinces_Standard_InGame(final SpriteBatch oSB) {
        drawProvinces_Standard_InvasionArmy(oSB);
        try {
            drawWastelandProvinces(oSB);
            drawProvinces_Standard_LandProvinces_InGame(oSB, getLandProvinces_Alpha());
            drawOccupiedProvinces(oSB);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        drawProvinces_Standard_RegroupArmy(oSB);
    }
    
    public static final void drawProvinces_Standard_FBO_InGame(final SpriteBatch oSB) {
        drawProvinces_Standard_InvasionArmy(oSB);
        try {
            drawWastelandProvinces(oSB);
            drawProvinces_Standard_LandProvinces_FBO(oSB);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        drawProvinces_Standard_RegroupArmy(oSB);
    }
    
    public static final void drawProvinces_Standard_RegroupArmy(final SpriteBatch oSB) {
        try {
            if (Game.regroupArmyMode) {
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, Game.selectedProvinces_Animation_Data.fAlpha / 255.0f));
                for (int i = 0; i < Game.iRegroupArmyProvincesSize; ++i) {
                    if (Game.getProvince(Game.regroupArmyProvinces.get(i)).getDrawProvince()) {
                        Game.getProvince(Game.regroupArmyProvinces.get(i)).drawLandProvince(oSB);
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void drawProvinces_Standard_InvasionArmy(final SpriteBatch oSB) {
        if (Game.invasionArmyMode) {
            try {
                if (Game.player.playerData.invasion.invasionsSize > 0) {
                    oSB.setColor(new Color(1.0f, 1.0f, 1.0f, Game.settingsManager.PROVINCE_ALPHA / 2.0f + Game.selectedProvinces_Animation_Data.fAlpha / 255.0f));
                    for (int i = 0; i < Game.player.playerData.invasion.invasionsSize; ++i) {
                        for (int j = 0, jSize = Game.player.playerData.invasion.invasions.get(i).lProvinces.size(); j < jSize; ++j) {
                            if (Game.getProvince(Game.player.playerData.invasion.invasions.get(i).lProvinces.get(j)).getDrawProvince()) {
                                Game.getProvince(Game.player.playerData.invasion.invasions.get(i).lProvinces.get(j)).drawLandProvince(oSB);
                            }
                        }
                    }
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
            try {
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, Game.settingsManager.PROVINCE_ALPHA + Game.selectedProvinces_Animation_Data.fAlpha / 255.0f));
                for (int i = 0; i < Game.invasionArmyProvincesSize; ++i) {
                    if (Game.getProvince(Game.invasionArmyProvinces.get(i)).getDrawProvince()) {
                        Game.getProvince(Game.invasionArmyProvinces.get(i)).drawLandProvince(oSB);
                    }
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
    }
    
    public static final float getLandProvinces_Alpha() {
        return getProvinceAlpha() * MapModeManager.fAlphaAnimation;
    }
    
    public static final void drawProvinces_Standard_LandProvinces(final SpriteBatch oSB, final float fProvinceAlpha) {
        for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() != 0) {
                Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB, fProvinceAlpha);
            }
        }
        for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() != 0) {
                Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvince(oSB, fProvinceAlpha);
            }
        }
    }
    
    public static final void drawProvinces_Standard_LandProvinces_InGame(final SpriteBatch oSB, final float fProvinceAlpha) {
        if (Game.mapScale.getCurrentScale() >= Game.DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE) {
            for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() != 0) {
                    Game.getProvince(Game.getProvinceInViewID(i)).fog_drawLandProvince.drawLandProvinceFog(oSB, fProvinceAlpha);
                }
            }
            for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() != 0) {
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).fog_drawLandProvince.drawLandProvinceFog(oSB, fProvinceAlpha);
                }
            }
        }
        else {
            drawProvinces_Standard_LandProvinces(oSB, fProvinceAlpha);
        }
    }
    
    public static final void drawProvinces_Standard_LandProvinces_FBO(final SpriteBatch oSB) {
        try {
            if (FBOProvincesBG.textureProvince_PBG != null) {
                if (FBOProvincesBG.lastPosX != Game.mapCoords.getPosX() || FBOProvincesBG.lastPosY != Game.mapCoords.getPosY()) {
                    FBOProvincesBG.disposeProvincesTexture();
                    FBOProvincesBG.disposeProvincesFBO();
                    drawProvinces_Standard_LandProvinces_InGame(oSB, getLandProvinces_Alpha());
                }
            }
            else {
                FBOProvincesBG.updateFBO();
                if (FBOProvincesBG.fboNumToGenerate_PB >= GameValues.value.FBO_NUM_TO_GENERATE_PB) {
                    FBOProvincesBG.fboNumToGenerate_PB = 0;
                    oSB.end();
                    FBOProvincesBG.disposeProvincesFBO();
                    FBOProvincesBG.disposeProvincesTexture();
                    FBOProvincesBG.fboProvince_PBG = new FrameBuffer(Pixmap.Format.RGBA8888, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, false);
                    FBOProvincesBG.textureProvince_PBG = new Texture(CFG.GAME_WIDTH, CFG.GAME_HEIGHT, Pixmap.Format.RGBA8888);
                    FBOProvincesBG.fboProvince_PBG.begin();
                    oSB.begin();
                    drawProvinces_Standard_LandProvinces_InGame(oSB, 1.0f);
                    oSB.end();
                    FBOProvincesBG.fboProvince_PBG.end();
                    FBOProvincesBG.textureProvince_PBG = (Texture)FBOProvincesBG.fboProvince_PBG.getColorBufferTexture();
                    FBOProvincesBG.fboPosX = Game.mapCoords.getPosX();
                    FBOProvincesBG.fboPosY = Game.mapCoords.getPosY();
                    oSB.begin();
                    if (FBOProvincesBG.textureProvince_PBG != null) {}
                }
                else {
                    drawProvinces_Standard_LandProvinces_InGame(oSB, getLandProvinces_Alpha());
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void drawProvinces_Diplomacy(final SpriteBatch oSB) {
        final float fProvinceAlpha2 = getProvinceAlpha() * MapModeManager.fAlphaAnimation;
        if (InGame.ONLY_MAP_MODE) {
            for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getPuppetOfCivID() == MapModeManager.diplomacyActiveCivID) {
                    Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB, fProvinceAlpha2);
                }
            }
            for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                if (Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getPuppetOfCivID() == MapModeManager.diplomacyActiveCivID) {
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvince(oSB, fProvinceAlpha2);
                }
            }
        }
        else {
            drawWastelandProvinces(oSB);
            for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() > 0) {
                    oSB.setColor(new Color(Game.getProvince(Game.getProvinceInViewID(i)).provinceColor.r, Game.getProvince(Game.getProvinceInViewID(i)).provinceColor.g, Game.getProvince(Game.getProvinceInViewID(i)).provinceColor.b, fProvinceAlpha2 * Game.getProvince(Game.getProvinceInViewID(i)).provinceColor.a));
                    Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                }
            }
            for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() > 0) {
                    oSB.setColor(new Color(Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor.r, Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor.g, Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor.b, fProvinceAlpha2 * Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor.a));
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvince(oSB);
                }
            }
            drawOccupiedProvinces(oSB);
        }
    }
    
    public static final void drawProvinces_Diplomacy_Hover(final SpriteBatch oSB) {
        final float fProvinceAlpha = getProvinceAlpha(Colors.PROVINCE_ALPHA_POPULATION) * MapModeManager.fAlphaAnimation;
        final float fProvinceAlpha2 = getProvinceAlpha() * MapModeManager.fAlphaAnimation;
        drawWastelandProvinces(oSB);
        for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() > 0) {
                if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == MapModeManager.diplomacyActiveCivID) {
                    oSB.setColor(new Color(Game.getProvince(Game.getProvinceInViewID(i)).provinceColor.r, Game.getProvince(Game.getProvinceInViewID(i)).provinceColor.g, Game.getProvince(Game.getProvinceInViewID(i)).provinceColor.b, fProvinceAlpha));
                }
                else {
                    oSB.setColor(new Color(Game.getProvince(Game.getProvinceInViewID(i)).provinceColor.r, Game.getProvince(Game.getProvinceInViewID(i)).provinceColor.g, Game.getProvince(Game.getProvinceInViewID(i)).provinceColor.b, fProvinceAlpha2 * Game.getProvince(Game.getProvinceInViewID(i)).provinceColor.a));
                }
                Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
            }
        }
        for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() > 0) {
                if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == MapModeManager.diplomacyActiveCivID) {
                    oSB.setColor(new Color(Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor.r, Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor.g, Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor.b, fProvinceAlpha));
                }
                else {
                    oSB.setColor(new Color(Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor.r, Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor.g, Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor.b, fProvinceAlpha2 * Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor.a));
                }
                Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvince(oSB);
            }
        }
        drawOccupiedProvinces(oSB);
    }
    
    public static final void drawProvinces_DiplomacyRelations(final SpriteBatch oSB) {
        final float fProvinceAlpha = getProvinceAlpha() * MapModeManager.fAlphaAnimation;
        final float fProvinceAlpha2 = getProvinceAlpha() * MapModeManager.fAlphaAnimation;
        drawWastelandProvinces(oSB);
        final int nActiveCivID = InGame_Civ.iActiveCivID;
        for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() > 0) {
                if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == nActiveCivID) {
                    oSB.setColor(Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getColor(fProvinceAlpha));
                }
                else if (DiplomacyManager.isAtWar(nActiveCivID, Game.getProvince(Game.getProvinceInViewID(i)).getCivID())) {
                    oSB.setColor(new Color(ProvinceDraw.PROVINCE_DIPLOMACY_AT_WAR.r, ProvinceDraw.PROVINCE_DIPLOMACY_AT_WAR.g, ProvinceDraw.PROVINCE_DIPLOMACY_AT_WAR.b, fProvinceAlpha));
                }
                else {
                    final int tempRelation = (int)Game.getCiv(nActiveCivID).diplomacy.getRelation(Game.getProvince(Game.getProvinceInViewID(i)).getCivID());
                    if (tempRelation == 0) {
                        oSB.setColor(new Color(ProvinceDraw.PROVINCE_DIPLOMACY_NEUTRAL_GREEN.r, ProvinceDraw.PROVINCE_DIPLOMACY_NEUTRAL_GREEN.g, ProvinceDraw.PROVINCE_DIPLOMACY_NEUTRAL_GREEN.b, fProvinceAlpha2 / 3.0f));
                    }
                    else if (tempRelation > 0) {
                        oSB.setColor(CFG.getColorStep(ProvinceDraw.PROVINCE_DIPLOMACY_GREEN, ProvinceDraw.PROVINCE_DIPLOMACY_GREEN2, tempRelation, (int)GameValues.diplomacy.DIPLOMACY_RELATIONS_MAX, fProvinceAlpha2));
                    }
                    else {
                        oSB.setColor(CFG.getColorStep(ProvinceDraw.PROVINCE_DIPLOMACY_RED, ProvinceDraw.PROVINCE_DIPLOMACY_RED2, -tempRelation, (int)(-GameValues.diplomacy.DIPLOMACY_RELATIONS_MIN), fProvinceAlpha2));
                    }
                }
                Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
            }
        }
        for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() > 0) {
                if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == nActiveCivID) {
                    oSB.setColor(Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getColor(fProvinceAlpha));
                }
                else if (DiplomacyManager.isAtWar(nActiveCivID, Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID())) {
                    oSB.setColor(new Color(ProvinceDraw.PROVINCE_DIPLOMACY_AT_WAR.r, ProvinceDraw.PROVINCE_DIPLOMACY_AT_WAR.g, ProvinceDraw.PROVINCE_DIPLOMACY_AT_WAR.b, fProvinceAlpha));
                }
                else {
                    final int tempRelation = (int)Game.getCiv(nActiveCivID).diplomacy.getRelation(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID());
                    if (tempRelation == 0) {
                        oSB.setColor(new Color(ProvinceDraw.PROVINCE_DIPLOMACY_NEUTRAL_GREEN.r, ProvinceDraw.PROVINCE_DIPLOMACY_NEUTRAL_GREEN.g, ProvinceDraw.PROVINCE_DIPLOMACY_NEUTRAL_GREEN.b, fProvinceAlpha2 / 3.0f));
                    }
                    else if (tempRelation > 0) {
                        oSB.setColor(CFG.getColorStep(ProvinceDraw.PROVINCE_DIPLOMACY_GREEN, ProvinceDraw.PROVINCE_DIPLOMACY_GREEN2, tempRelation, (int)GameValues.diplomacy.DIPLOMACY_RELATIONS_MAX, fProvinceAlpha2));
                    }
                    else {
                        oSB.setColor(CFG.getColorStep(ProvinceDraw.PROVINCE_DIPLOMACY_RED, ProvinceDraw.PROVINCE_DIPLOMACY_RED2, -tempRelation, (int)(-GameValues.diplomacy.DIPLOMACY_RELATIONS_MIN), fProvinceAlpha2));
                    }
                }
                Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvince(oSB);
            }
        }
        drawOccupiedProvinces(oSB);
    }
    
    public static final void drawProvinces_DeclareWar(final SpriteBatch oSB) {
        final float fProvinceAlpha = getProvinceAlpha() * MapModeManager.fAlphaAnimation;
        final float fProvinceAlphaGray = getProvinceAlpha() * MapModeManager.fAlphaAnimation * 0.4f;
        drawWastelandProvinces(oSB);
        for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() > 0) {
                if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_BLUE2.r, MapModeManager.PROVINCE_BLUE2.g, MapModeManager.PROVINCE_BLUE2.b, fProvinceAlpha));
                }
                else if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == InGame_DeclareWar.iCivID) {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_RED2.r, MapModeManager.PROVINCE_RED2.g, MapModeManager.PROVINCE_RED2.b, fProvinceAlpha));
                }
                else if (Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).warView_IsAggressor) {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_BLUE2_ALLY.r, MapModeManager.PROVINCE_BLUE2_ALLY.g, MapModeManager.PROVINCE_BLUE2_ALLY.b, fProvinceAlpha));
                }
                else if (Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).warView_ParticipatesInWar) {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_RED2_ALLY.r, MapModeManager.PROVINCE_RED2_ALLY.g, MapModeManager.PROVINCE_RED2_ALLY.b, fProvinceAlpha));
                }
                else {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlphaGray));
                }
                Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
            }
        }
        for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() > 0) {
                if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_BLUE2.r, MapModeManager.PROVINCE_BLUE2.g, MapModeManager.PROVINCE_BLUE2.b, fProvinceAlpha));
                }
                else if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == InGame_DeclareWar.iCivID) {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_RED2.r, MapModeManager.PROVINCE_RED2.g, MapModeManager.PROVINCE_RED2.b, fProvinceAlpha));
                }
                else if (Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).warView_IsAggressor) {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_BLUE2_ALLY.r, MapModeManager.PROVINCE_BLUE2_ALLY.g, MapModeManager.PROVINCE_BLUE2_ALLY.b, fProvinceAlpha));
                }
                else if (Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).warView_ParticipatesInWar) {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_RED2_ALLY.r, MapModeManager.PROVINCE_RED2_ALLY.g, MapModeManager.PROVINCE_RED2_ALLY.b, fProvinceAlpha));
                }
                else {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlphaGray));
                }
                Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
            }
        }
        drawOccupiedProvinces(oSB);
    }
    
    public static final void drawProvinces_CurrentWars(final SpriteBatch oSB) {
        final float fProvinceAlpha = getProvinceAlpha() * MapModeManager.fAlphaAnimation;
        final float fProvinceAlphaGray = getProvinceAlpha() * MapModeManager.fAlphaAnimation * 0.4f;
        drawWastelandProvinces(oSB);
        for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() > 0) {
                if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_BLUE2.r, MapModeManager.PROVINCE_BLUE2.g, MapModeManager.PROVINCE_BLUE2.b, fProvinceAlpha));
                }
                else if (Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).diplomacy.isAtWar()) {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_RED2.r, MapModeManager.PROVINCE_RED2.g, MapModeManager.PROVINCE_RED2.b, fProvinceAlpha));
                }
                else {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlphaGray));
                }
                Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
            }
        }
        for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() > 0) {
                if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_BLUE2.r, MapModeManager.PROVINCE_BLUE2.g, MapModeManager.PROVINCE_BLUE2.b, fProvinceAlpha));
                }
                else if (Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).diplomacy.isAtWar()) {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_RED2.r, MapModeManager.PROVINCE_RED2.g, MapModeManager.PROVINCE_RED2.b, fProvinceAlpha));
                }
                else {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlphaGray));
                }
                Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
            }
        }
        drawOccupiedProvinces(oSB);
    }
    
    public static final void drawProvinces_Alliances(final SpriteBatch oSB) {
        final float fProvinceAlpha = getProvinceAlpha() * MapModeManager.fAlphaAnimation;
        final float fProvinceAlphaGray = getProvinceAlpha() * MapModeManager.fAlphaAnimation * 0.4f;
        drawWastelandProvinces(oSB);
        for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() > 0) {
                if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_BLUE2.r, MapModeManager.PROVINCE_BLUE2.g, MapModeManager.PROVINCE_BLUE2.b, fProvinceAlpha));
                }
                else if (!Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).diplomacy.alliance.isEmpty()) {
                    oSB.setColor(new Color(DiplomacyManager.COLOR_ALLIANCE.r, DiplomacyManager.COLOR_ALLIANCE.g, DiplomacyManager.COLOR_ALLIANCE.b, fProvinceAlpha));
                }
                else {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlphaGray));
                }
                Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
            }
        }
        for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() > 0) {
                if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_BLUE2.r, MapModeManager.PROVINCE_BLUE2.g, MapModeManager.PROVINCE_BLUE2.b, fProvinceAlpha));
                }
                else if (!Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).diplomacy.alliance.isEmpty()) {
                    oSB.setColor(new Color(DiplomacyManager.COLOR_ALLIANCE.r, DiplomacyManager.COLOR_ALLIANCE.g, DiplomacyManager.COLOR_ALLIANCE.b, fProvinceAlpha));
                }
                else {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlphaGray));
                }
                Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
            }
        }
        drawOccupiedProvinces(oSB);
    }
    
    public static final void drawProvinces_DefensivePacts(final SpriteBatch oSB) {
        final float fProvinceAlpha = getProvinceAlpha() * MapModeManager.fAlphaAnimation;
        final float fProvinceAlphaGray = getProvinceAlpha() * MapModeManager.fAlphaAnimation * 0.4f;
        drawWastelandProvinces(oSB);
        for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() > 0) {
                if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_BLUE2.r, MapModeManager.PROVINCE_BLUE2.g, MapModeManager.PROVINCE_BLUE2.b, fProvinceAlpha));
                }
                else if (!Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).diplomacy.defensivePact.isEmpty()) {
                    oSB.setColor(new Color(DiplomacyManager.COLOR_DEFENSIVE_PACT.r, DiplomacyManager.COLOR_DEFENSIVE_PACT.g, DiplomacyManager.COLOR_DEFENSIVE_PACT.b, fProvinceAlpha));
                }
                else {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlphaGray));
                }
                Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
            }
        }
        for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() > 0) {
                if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_BLUE2.r, MapModeManager.PROVINCE_BLUE2.g, MapModeManager.PROVINCE_BLUE2.b, fProvinceAlpha));
                }
                else if (!Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).diplomacy.defensivePact.isEmpty()) {
                    oSB.setColor(new Color(DiplomacyManager.COLOR_DEFENSIVE_PACT.r, DiplomacyManager.COLOR_DEFENSIVE_PACT.g, DiplomacyManager.COLOR_DEFENSIVE_PACT.b, fProvinceAlpha));
                }
                else {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlphaGray));
                }
                Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
            }
        }
        drawOccupiedProvinces(oSB);
    }
    
    public static final void drawProvinces_NonAggressionPacts(final SpriteBatch oSB) {
        final float fProvinceAlpha = getProvinceAlpha() * MapModeManager.fAlphaAnimation;
        final float fProvinceAlphaGray = getProvinceAlpha() * MapModeManager.fAlphaAnimation * 0.4f;
        drawWastelandProvinces(oSB);
        for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() > 0) {
                if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_BLUE2.r, MapModeManager.PROVINCE_BLUE2.g, MapModeManager.PROVINCE_BLUE2.b, fProvinceAlpha));
                }
                else if (!Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).diplomacy.nonAggressionPact.isEmpty()) {
                    oSB.setColor(new Color(DiplomacyManager.COLOR_NON_AGGRESSION_PACT.r, DiplomacyManager.COLOR_NON_AGGRESSION_PACT.g, DiplomacyManager.COLOR_NON_AGGRESSION_PACT.b, fProvinceAlpha));
                }
                else {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlphaGray));
                }
                Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
            }
        }
        for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() > 0) {
                if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_BLUE2.r, MapModeManager.PROVINCE_BLUE2.g, MapModeManager.PROVINCE_BLUE2.b, fProvinceAlpha));
                }
                else if (!Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).diplomacy.nonAggressionPact.isEmpty()) {
                    oSB.setColor(new Color(DiplomacyManager.COLOR_NON_AGGRESSION_PACT.r, DiplomacyManager.COLOR_NON_AGGRESSION_PACT.g, DiplomacyManager.COLOR_NON_AGGRESSION_PACT.b, fProvinceAlpha));
                }
                else {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlphaGray));
                }
                Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
            }
        }
        drawOccupiedProvinces(oSB);
    }
    
    public static final void drawProvinces_Nuke(final SpriteBatch oSB) {
        final float fProvinceAlpha = getProvinceAlpha() * MapModeManager.fAlphaAnimation;
        final float fProvinceAlphaGray = getProvinceAlpha() * MapModeManager.fAlphaAnimation * 0.4f;
        drawWastelandProvinces(oSB);
        for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() > 0) {
                if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_BLUE2.r, MapModeManager.PROVINCE_BLUE2.g, MapModeManager.PROVINCE_BLUE2.b, fProvinceAlpha));
                }
                else if (DiplomacyManager.isAtWar(Game.player.iCivID, Game.getProvince(Game.getProvinceInViewID(i)).getCivID())) {
                    oSB.setColor(Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getColor(fProvinceAlpha));
                }
                else {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlphaGray));
                }
                Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
            }
        }
        for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() > 0) {
                if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_BLUE2.r, MapModeManager.PROVINCE_BLUE2.g, MapModeManager.PROVINCE_BLUE2.b, fProvinceAlpha));
                }
                else if (DiplomacyManager.isAtWar(Game.player.iCivID, Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID())) {
                    oSB.setColor(Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getColor(fProvinceAlpha));
                }
                else {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlphaGray));
                }
                Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
            }
        }
        drawOccupiedProvinces(oSB);
    }
    
    public static final void drawProvinces_Colonize(final SpriteBatch oSB) {
        final float fProvinceAlpha = getProvinceAlpha() * MapModeManager.fAlphaAnimation;
        final float fProvinceAlphaGray = getProvinceAlpha() * MapModeManager.fAlphaAnimation * 0.4f;
        drawWastelandProvinces(oSB);
        if (Game.getCiv(Game.player.iCivID).fGold < GameValues.colonization.ALLOW_COLONIZATION_BY_SPENDING_GOLD_COST) {
            for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (Game.getProvince(Game.getProvinceInViewID(i)).getWasteland() < 0) {
                    if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() > 0) {
                        oSB.setColor(Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getColor(fProvinceAlpha));
                    }
                    else {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_RED2.r, MapModeManager.PROVINCE_RED2.g, MapModeManager.PROVINCE_RED2.b, fProvinceAlphaGray));
                    }
                    Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                }
            }
            for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getWasteland() < 0) {
                    if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() > 0) {
                        oSB.setColor(Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getColor(fProvinceAlpha));
                    }
                    else {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_RED2.r, MapModeManager.PROVINCE_RED2.g, MapModeManager.PROVINCE_RED2.b, fProvinceAlphaGray));
                    }
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                }
            }
        }
        else {
            for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (Game.getProvince(Game.getProvinceInViewID(i)).getWasteland() < 0) {
                    if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() > 0) {
                        oSB.setColor(Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getColor(fProvinceAlpha));
                    }
                    else {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_GREEN.r, MapModeManager.PROVINCE_GREEN.g, MapModeManager.PROVINCE_GREEN.b, fProvinceAlphaGray));
                    }
                    Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                }
            }
            for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getWasteland() < 0) {
                    if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() > 0) {
                        oSB.setColor(Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getColor(fProvinceAlpha));
                    }
                    else {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_GREEN.r, MapModeManager.PROVINCE_GREEN.g, MapModeManager.PROVINCE_GREEN.b, fProvinceAlphaGray));
                    }
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                }
            }
        }
        drawOccupiedProvinces(oSB);
    }
    
    public static final void drawProvinces_FormCiv(final SpriteBatch oSB) {
        final float fProvinceAlpha = getProvinceAlpha() * MapModeManager.fAlphaAnimation;
        final float fProvinceAlphaGray = getProvinceAlpha() * MapModeManager.fAlphaAnimation * 0.6f;
        drawWastelandProvinces(oSB);
        try {
            if (InGame_FormCiv.formCivID >= 0 && Game.player.formableCivs.size() > InGame_FormCiv.formCivID) {
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() > 0) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).drawInMapMode) {
                            if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                                Game.getProvince(Game.getProvinceInViewID(i)).setProvinceColor(oSB, fProvinceAlpha);
                            }
                            else {
                                oSB.setColor(new Color(MapModeManager.PROVINCE_RED2.r, MapModeManager.PROVINCE_RED2.g, MapModeManager.PROVINCE_RED2.b, fProvinceAlphaGray));
                            }
                        }
                        else if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                            Game.getProvince(Game.getProvinceInViewID(i)).setProvinceColor(oSB, fProvinceAlphaGray);
                        }
                        else {
                            oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlphaGray));
                        }
                        Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                    }
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() > 0) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).drawInMapMode) {
                            if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                                Game.getProvince(Game.getExtraProvinceInViewID(i)).setProvinceColor(oSB, fProvinceAlpha);
                            }
                            else {
                                oSB.setColor(new Color(MapModeManager.PROVINCE_RED2.r, MapModeManager.PROVINCE_RED2.g, MapModeManager.PROVINCE_RED2.b, fProvinceAlphaGray));
                            }
                        }
                        else if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).setProvinceColor(oSB, fProvinceAlphaGray);
                        }
                        else {
                            oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlphaGray));
                        }
                        Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvince(oSB);
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        drawOccupiedProvinces(oSB);
    }
    
    public static final void drawProvinces_SpecialAlliance_View(final SpriteBatch oSB) {
        final float fProvinceAlpha = getProvinceAlpha() * MapModeManager.fAlphaAnimation;
        final float fProvinceAlphaGray = getProvinceAlpha() * MapModeManager.fAlphaAnimation * 0.4f;
        drawWastelandProvinces(oSB);
        for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() > 0) {
                if (Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).warView_ParticipatesInWar) {
                    if (Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).warView_IsAggressor) {
                        oSB.setColor(new Color(Colors.HOVER_YELLOW.r, Colors.HOVER_YELLOW.g, Colors.HOVER_YELLOW.b, fProvinceAlpha));
                    }
                    else {
                        oSB.setColor(new Color(Colors.HOVER_GOLD.r, Colors.HOVER_GOLD.g, Colors.HOVER_GOLD.b, fProvinceAlpha));
                    }
                }
                else {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlphaGray));
                }
                Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
            }
        }
        for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() > 0) {
                if (Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).warView_ParticipatesInWar) {
                    if (Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).warView_IsAggressor) {
                        oSB.setColor(new Color(Colors.HOVER_YELLOW.r, Colors.HOVER_YELLOW.g, Colors.HOVER_YELLOW.b, fProvinceAlpha));
                    }
                    else {
                        oSB.setColor(new Color(Colors.HOVER_GOLD.r, Colors.HOVER_GOLD.g, Colors.HOVER_GOLD.b, fProvinceAlpha));
                    }
                }
                else {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlphaGray));
                }
                Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
            }
        }
        drawOccupiedProvinces(oSB);
    }
    
    public static final void drawProvinces_WarView(final SpriteBatch oSB) {
        final float fProvinceAlpha = getProvinceAlpha() * MapModeManager.fAlphaAnimation;
        final float fProvinceAlphaGray = getProvinceAlpha() * MapModeManager.fAlphaAnimation * 0.4f;
        drawWastelandProvinces(oSB);
        for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() > 0) {
                if (Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).warView_ParticipatesInWar) {
                    if (Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).warView_IsAggressor) {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_BLUE2.r, MapModeManager.PROVINCE_BLUE2.g, MapModeManager.PROVINCE_BLUE2.b, fProvinceAlpha));
                    }
                    else {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_RED2.r, MapModeManager.PROVINCE_RED2.g, MapModeManager.PROVINCE_RED2.b, fProvinceAlpha));
                    }
                }
                else {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlphaGray));
                }
                Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
            }
        }
        for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() > 0) {
                if (Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).warView_ParticipatesInWar) {
                    if (Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).warView_IsAggressor) {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_BLUE2.r, MapModeManager.PROVINCE_BLUE2.g, MapModeManager.PROVINCE_BLUE2.b, fProvinceAlpha));
                    }
                    else {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_RED2.r, MapModeManager.PROVINCE_RED2.g, MapModeManager.PROVINCE_RED2.b, fProvinceAlpha));
                    }
                }
                else {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlphaGray));
                }
                Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
            }
        }
        drawOccupiedProvinces(oSB);
    }
    
    public static final void drawProvinces_Peace(final SpriteBatch oSB) {
        final float fProvinceAlpha = getProvinceAlpha() * MapModeManager.fAlphaAnimation;
        final float fProvinceAlphaGray = getProvinceAlpha() * MapModeManager.fAlphaAnimation * 0.4f;
        drawWastelandProvinces(oSB);
        for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() > 0) {
                if (Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).warView_ParticipatesInWar) {
                    if (Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).warView_IsAggressor) {
                        oSB.setColor(Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getColor(fProvinceAlpha));
                    }
                    else if (Game.getProvince(Game.getProvinceInViewID(i)).peaceTreatyIsTaken) {
                        oSB.setColor(Game.getCiv(Game.player.iCivID).getColor(fProvinceAlpha));
                    }
                    else {
                        oSB.setColor(Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getColor(fProvinceAlpha));
                    }
                }
                else {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlphaGray));
                }
                Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
            }
        }
        for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() > 0) {
                if (Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).warView_ParticipatesInWar) {
                    if (Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).warView_IsAggressor) {
                        oSB.setColor(Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getColor(fProvinceAlpha));
                    }
                    else if (Game.getProvince(Game.getExtraProvinceInViewID(i)).peaceTreatyIsTaken) {
                        oSB.setColor(Game.getCiv(Game.player.iCivID).getColor(fProvinceAlpha));
                    }
                    else {
                        oSB.setColor(Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getColor(fProvinceAlpha));
                    }
                }
                else {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlphaGray));
                }
                Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvince(oSB);
            }
        }
        try {
            for (int i = Game.player.peaceTreaty.lProvinces_Liberate.size() - 1; i >= 0; --i) {
                if (Game.getProvince(Game.player.peaceTreaty.lProvinces_Liberate.get(i)).getDrawProvince()) {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlphaGray));
                    Game.getProvince(Game.player.peaceTreaty.lProvinces_Liberate.get(i)).drawLandProvince(oSB);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void drawProvinces_ReleaseVassal(final SpriteBatch oSB) {
        final float fProvinceAlpha = getProvinceAlpha() * MapModeManager.fAlphaAnimation;
        final float fProvinceAlphaGray = getProvinceAlpha() * MapModeManager.fAlphaAnimation * 0.4f;
        drawWastelandProvinces(oSB);
        for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() > 0) {
                if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                    if (Game.getProvince(Game.getProvinceInViewID(i)).peaceTreatyIsTaken) {
                        oSB.setColor(Game.getCiv(InGame_ReleaseAVassal.releaseVassalData.iCivID).getColor(fProvinceAlpha));
                    }
                    else {
                        oSB.setColor(Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getColor(fProvinceAlpha));
                    }
                }
                else {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlphaGray));
                }
                Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
            }
        }
        for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() > 0) {
                if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                    if (Game.getProvince(Game.getExtraProvinceInViewID(i)).peaceTreatyIsTaken) {
                        oSB.setColor(Game.getCiv(InGame_ReleaseAVassal.releaseVassalData.iCivID).getColor(fProvinceAlpha));
                    }
                    else {
                        oSB.setColor(Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getColor(fProvinceAlpha));
                    }
                }
                else {
                    oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlphaGray));
                }
                Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvince(oSB);
            }
        }
    }
    
    public static final void drawWastelandProvinces(final SpriteBatch oSB) {
        for (int i = 0; i < Game.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            oSB.setColor(getWastelandColor(Game.getProvince(Game.getWastelandProvinceInViewID(i)).getWasteland(), Game.settingsManager.PROVINCE_ALPHA_WASTELAND));
            Game.getProvince(Game.getWastelandProvinceInViewID(i)).drawLandProvince(oSB);
        }
    }
    
    public static final float getProvinceAlpha() {
        if (Game.mapScale.getCurrentScale() > 1.0f) {
            final float a = 0.0f;
            final float prov\u0131nce_ALPHA = Game.settingsManager.PROVINCE_ALPHA;
            final float n = 1.0f;
            final float n2 = GameValues.zoom.PROVINCE_ALPHA_ZOOM_IN * Game.mapScale.getCurrentScale();
            final MapScale mapScale = Game.mapScale;
            return Math.max(a, prov\u0131nce_ALPHA * (n - n2 / MapScale.defScales.definedScales[0]));
        }
        if (Game.mapScale.getCurrentScale() == 1.0f) {
            return Game.settingsManager.PROVINCE_ALPHA;
        }
        if (Game.mapScale.getCurrentScale() < Game.map.getActiveMap_MapData().mapData.BackgroundZoomOut_Scale) {
            return Math.min(1.0f, Game.settingsManager.PROVINCE_ALPHA + (1.0f - Game.settingsManager.PROVINCE_ALPHA) * (GameValues.zoom.PROVINCE_ALPHA_ZOOM_OUT * 1.5f) * (1.0f - Game.mapScale.getCurrentScale()));
        }
        return Math.min(1.0f, Game.settingsManager.PROVINCE_ALPHA + (1.0f - Game.settingsManager.PROVINCE_ALPHA) * GameValues.zoom.PROVINCE_ALPHA_ZOOM_OUT * (1.0f - Game.mapScale.getCurrentScale()));
    }
    
    public static final float getProvinceAlpha(final float fAlpha) {
        if (Game.mapScale.getCurrentScale() > 1.0f) {
            final float a = 0.0f;
            final float n = 1.0f;
            final float n2 = GameValues.zoom.PROVINCE_ALPHA_ZOOM_IN * Game.mapScale.getCurrentScale();
            final MapScale mapScale = Game.mapScale;
            return Math.max(a, fAlpha * (n - n2 / MapScale.defScales.definedScales[0]));
        }
        if (Game.mapScale.getCurrentScale() == 1.0f) {
            return fAlpha;
        }
        return Math.min(1.0f, fAlpha + (1.0f - fAlpha) * GameValues.zoom.PROVINCE_ALPHA_ZOOM_OUT * (1.0f - Game.mapScale.getCurrentScale()));
    }
    
    public static final void drawOccupiedProvinces(final SpriteBatch oSB) {
        if (CFG.isDesktop() && ProvinceDraw.drawProvincesCiv_HoveredFlagID > 0) {
            oSB.setShader(Renderer.shaderDefaultProvince);
            final float fProvinceAlpha = getProvinceAlpha() * MapModeManager.fAlphaAnimation;
            for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == ProvinceDraw.drawProvincesCiv_HoveredFlagID) {
                    Game.getProvince(Game.getProvinceInViewID(i)).setProvinceColor(oSB, fProvinceAlpha);
                    Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                }
            }
            for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == ProvinceDraw.drawProvincesCiv_HoveredFlagID) {
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).setProvinceColor(oSB, fProvinceAlpha);
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                }
            }
            oSB.setShader(Renderer.shaderDefault);
        }
        if (Game.mapScale.getCurrentScale() >= Game.DRAW_OCCUPIED_PROVINCES_MIN_SCALE) {
            drawOccupiedProvinces_Just(oSB);
        }
        else if (ProvinceDrawArmy.drawOccupiedHideAnimation) {
            drawOccupiedProvinces_Just(oSB);
        }
    }
    
    private static final void drawOccupiedProvinces_Just(final SpriteBatch oSB) {
        final float fAlpha = getProvinceAlpha() * (1.0f + Game.settingsManager.PROVINCE_OCCUPIED_ALPHA_EXTRA) * ProvinceDrawArmy.DRAW_OCCUPIED_ALPHA;
        if (fAlpha > 0.001f) {
            oSB.setShader(Renderer.shaderAlpha_Pattern);
            for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (Game.getProvince(Game.getProvinceInViewID(i)).isOccupied()) {
                    Game.getProvince(Game.getProvinceInViewID(i)).drawOccupiedProvince(oSB, fAlpha);
                }
            }
            for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                if (Game.getProvince(Game.getExtraProvinceInViewID(i)).isOccupied()) {
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).drawOccupiedProvince(oSB, fAlpha);
                }
            }
            oSB.setShader(Renderer.shaderDefault);
        }
    }
    
    public static final Color getWastelandColor(final int wastelandLevel, final float fAlpha) {
        return new Color(Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.WastelandColor[0] - 0.0209f * wastelandLevel, Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.WastelandColor[1] - 0.0176f * wastelandLevel, Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.WastelandColor[2] - 0.0148f * wastelandLevel, fAlpha);
    }
    
    public static final void drawProvincesBorder_Prepare() {
        ProvinceDraw.lineWidth = 1.5f;
        if (Game.mapScale.getCurrentScale() < 1.0f) {
            ProvinceDraw.lineWidth = Math.min(6.0f, ProvinceDraw.lineWidth / Game.mapScale.getCurrentScale());
        }
        ProvinceDraw.lineWidth += Game.settingsManager.BORDER_EXTRA_WIDTH;
        ProvinceBorder.mapCordsPosY = -Game.mapCoords.getPosY();
        ProvinceBorder.pathProvinceBorderExtraWidth = Math.min(SettingsProvince.value.MAX_BORDER_WIDTH, Math.max(SettingsProvince.value.MIN_BORDER_WIDTH, SettingsProvince.value.MAX_BORDER_WIDTH / Game.mapScale.getCurrentScale())) + Game.settingsManager.BORDER_EXTRA_WIDTH;
        ProvinceBorder.pathProvinceBorderExtraWidth2 = Math.max(1.0f, ProvinceBorder.pathProvinceBorderExtraWidth / SettingsProvince.value.BORDER_WIDTH_DIVIDE) + Game.settingsManager.BORDER_EXTRA_WIDTH;
        if (Game.mapScale.getCurrentScale() < SettingsProvince.value.SCALE_NONE_NONE) {
            ProvinceDraw.joinType = JoinType.NONE;
            ProvinceDraw.joinType_Shadow = JoinType.NONE;
        }
        else if (Game.mapScale.getCurrentScale() < SettingsProvince.value.SCALE_NONE_POINTY) {
            ProvinceDraw.joinType = JoinType.NONE;
            ProvinceDraw.joinType_Shadow = JoinType.POINTY;
        }
        else if (Game.mapScale.getCurrentScale() < SettingsProvince.value.SCALE_POINTY_POINTY) {
            ProvinceDraw.joinType = JoinType.POINTY;
            ProvinceDraw.joinType_Shadow = JoinType.POINTY;
        }
        else if (Game.mapScale.getCurrentScale() < SettingsProvince.value.SCALE_POINTY_SMOOTH) {
            ProvinceDraw.joinType = JoinType.POINTY;
            ProvinceDraw.joinType_Shadow = JoinType.SMOOTH;
        }
        else {
            ProvinceDraw.joinType = JoinType.SMOOTH;
            ProvinceDraw.joinType_Shadow = JoinType.SMOOTH;
        }
    }
    
    public static final void drawProvincesBorder(final SpriteBatch oSB) {
        if (Game.settingsManager.SETTINGS_PROVINCE_BORDER > 0 && Game.mapScale.getCurrentScale() > SettingsProvince.value.DRAW_BORDERS) {
            Renderer.oSBBorder.begin();
            drawProvincesBorder_Prepare();
            try {
                for (int i = 0; i < Game.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
                    Game.getProvince(Game.getWastelandProvinceInViewID(i)).drawProvinceBorder(oSB, ProvinceDraw.lineWidth, ProvinceDraw.joinType);
                }
                for (int i = 0; i < Game.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
                    Game.getProvince(Game.getSeaProvinceInViewID(i)).drawProvinceBorder(oSB, ProvinceDraw.lineWidth, ProvinceDraw.joinType);
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).drawProvinceBorder(oSB, ProvinceDraw.lineWidth, ProvinceDraw.joinType);
                }
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    Game.getProvince(Game.getProvinceInViewID(i)).drawProvinceBorder(oSB, ProvinceDraw.lineWidth, ProvinceDraw.joinType);
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
            Renderer.oSBBorder.end();
            oSB.setColor(Color.WHITE);
        }
    }
    
    protected static final void drawProvincesInMapEditor_Connections(final SpriteBatch oSB) {
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.1f));
        for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
        }
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.4f));
        for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            for (int j = 0; j < Game.getProvince(Game.getProvinceInViewID(i)).getProvinceBordersLandByLandSize(); ++j) {
                drawProvincesInMapEditor_Connections_Line(oSB, Images.pix2, Game.getProvinceInViewID(i), Game.getProvince(Game.getProvinceInViewID(i)).getProvinceBordersLandByLand().get(j).getWithProvinceID());
            }
            for (int j = 0; j < Game.getProvince(Game.getProvinceInViewID(i)).getProvinceBordersLandBySeaSize(); ++j) {
                drawProvincesInMapEditor_Connections_Line(oSB, Images.line_33, Game.getProvinceInViewID(i), Game.getProvince(Game.getProvinceInViewID(i)).getProvinceBordersLandBySea().get(j).getWithProvinceID());
            }
        }
        for (int i = 0; i < Game.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            for (int j = 0; j < Game.getProvince(Game.getSeaProvinceInViewID(i)).getProvinceBordersLandBySeaSize(); ++j) {
                drawProvincesInMapEditor_Connections_Line(oSB, Images.line_33, Game.getSeaProvinceInViewID(i), Game.getProvince(Game.getSeaProvinceInViewID(i)).getProvinceBordersLandBySea().get(j).getWithProvinceID());
            }
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.25f));
            for (int j = 0; j < Game.getProvince(Game.getSeaProvinceInViewID(i)).getProvinceBordersSeaBySeaSize(); ++j) {
                drawProvincesInMapEditor_Connections_Line(oSB, Images.line_33, Game.getSeaProvinceInViewID(i), Game.getProvince(Game.getSeaProvinceInViewID(i)).getProvinceBordersSeaBySea().get(j).getWithProvinceID());
            }
        }
    }
    
    private static final void drawProvincesInMapEditor_Connections_Line(final SpriteBatch oSB, final int nImageID, final int fromProvinceID, final int toProvinceID) {
        if (!Game.getProvince(toProvinceID).getDrawProvince()) {
            return;
        }
        final int iWidth = (int)Math.ceil(Math.sqrt((Game.getProvince(toProvinceID).iCenterShiftX + Game.getProvince(toProvinceID).getTranslateProvincePosX() - (Game.getProvince(fromProvinceID).iCenterShiftX + Game.getProvince(fromProvinceID).getTranslateProvincePosX())) * (Game.getProvince(toProvinceID).iCenterShiftX + Game.getProvince(toProvinceID).getTranslateProvincePosX() - (Game.getProvince(fromProvinceID).iCenterShiftX + Game.getProvince(fromProvinceID).getTranslateProvincePosX())) + (Game.getProvince(fromProvinceID).iCenterShiftY - Game.getProvince(toProvinceID).iCenterShiftY) * (Game.getProvince(fromProvinceID).iCenterShiftY - Game.getProvince(toProvinceID).iCenterShiftY)));
        final float fAngle = (float)(Math.atan2(Game.getProvince(fromProvinceID).iCenterShiftY - Game.getProvince(toProvinceID).iCenterShiftY, -(Game.getProvince(fromProvinceID).iCenterShiftX + Game.getProvince(fromProvinceID).getTranslateProvincePosX()) + (Game.getProvince(toProvinceID).iCenterShiftX + Game.getProvince(toProvinceID).getTranslateProvincePosX())) * 180.0 / 3.141592653589793);
        ImageManager.getImage(nImageID).draw(oSB, Game.getProvince(fromProvinceID).iCenterShiftX + Game.getProvince(fromProvinceID).getTranslateProvincePosX(), Game.getProvince(fromProvinceID).iCenterShiftY + Game.mapCoords.getPosY(), iWidth, ImageManager.getImage(nImageID).getHeight(), fAngle, 0);
    }
    
    public static final void drawProvinces(final SpriteBatch oSB, final int nPosX, final int nPosY, final float scale, final int nAlpha) {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            if (Game.getProvince(i).getCivID() != 0) {
                Game.getProvince(i).draw(oSB, nPosX, nPosY, scale, nAlpha);
            }
            else if (Game.getProvince(i).getWasteland() >= 0) {
                Game.getProvince(i).drawWasteland(oSB, nPosX, nPosY, scale, nAlpha);
            }
        }
        oSB.setColor(Color.WHITE);
    }
    
    public static final void drawProvinces_Minimap(final SpriteBatch oSB, final int nPosX, final int nPosY, final float scale, final int nAlpha) {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            if (Game.getProvince(i).getCivID() != 0) {
                Game.getProvince(i).draw(oSB, nPosX, nPosY, scale, nAlpha);
            }
        }
        oSB.setColor(Color.WHITE);
    }
    
    public static final void drawProvinces_FogOfWarDiscovery(final SpriteBatch oSB, final int nPosX, final int nPosY, final float scale, final int nAlpha) {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            if (Game.getProvince(i).getCivID() != 0) {
                Game.getProvince(i).draw_FogOfWarDiscovery(oSB, nPosX, nPosY, scale, nAlpha);
            }
            else if (Game.getProvince(i).getWasteland() >= 0 && CFG.getMetProvince(i)) {
                Game.getProvince(i).drawWasteland(oSB, nPosX, nPosY, scale, nAlpha);
            }
        }
        oSB.setColor(Color.WHITE);
    }
    
    public static final void updateDrawMoveUnits() {
        if (Game.menuManager.getInGame()) {
            ProvinceDraw.oDrawMoveUnits = new DrawMoveUnits() {
                @Override
                public void drawMoveUnits(final SpriteBatch oSB) {
                    if (Game.mapScale.getCurrentScale() >= Game.DRAW_ARMY_MIN_SCALE) {
                        ProvinceDraw.drawMoveUnits_Just(oSB, Game.mapScale.getCurrentScale());
                        try {
                            if (Game.regroupArmyLine != null) {
                                Game.regroupArmyLine.draw(oSB, Game.mapScale.getCurrentScale());
                                try {
                                    Renderer.oSBBorder.end();
                                    Renderer.oSBBorder.begin();
                                }
                                catch (final Exception ex) {
                                    CFG.exceptionStack(ex);
                                }
                            }
                        }
                        catch (final Exception ex) {
                            CFG.exceptionStack(ex);
                        }
                    }
                }
            };
        }
        else {
            ProvinceDraw.oDrawMoveUnits = new DrawMoveUnits() {
                @Override
                public void drawMoveUnits(final SpriteBatch oSB) {
                }
            };
        }
    }
    
    public static final void drawMoveUnits_Just_CheckArmies() {
        try {
            for (final Map.Entry<Integer, Diplomacy.DiplomacyData> entry : Game.getCiv(Game.player.iCivID).diplomacy.alliance.entrySet()) {
                final int civID = entry.getKey();
                if (Game.getCiv(civID).getPuppetOfCivID() != Game.player.iCivID) {
                    for (int j = Game.getCiv(civID).getMoveUnitsSize() - 1; j >= 0; --j) {
                        if (Game.getProvince(Game.getCiv(civID).getMoveUnits(j).getFromProvinceID()).getArmy(Game.getCiv(civID).getMoveUnits(j).key) == null) {
                            Game.getCiv(civID).removeMove(j);
                        }
                    }
                }
            }
            for (int i = 0; i < Game.getCiv(Game.player.iCivID).diplomacy.iVassalsSize; ++i) {
                final int civID = Game.getCiv(Game.player.iCivID).diplomacy.lVassals.get(i).c;
                for (int k = Game.getCiv(civID).getMoveUnitsSize() - 1; k >= 0; --k) {
                    if (Game.getProvince(Game.getCiv(civID).getMoveUnits(k).getFromProvinceID()).getArmy(Game.getCiv(civID).getMoveUnits(k).key) == null) {
                        Game.getCiv(civID).removeMove(k);
                    }
                }
            }
            for (int l = Game.getCiv(Game.player.iCivID).getMoveUnitsSize() - 1; l >= 0; --l) {
                if (Game.getProvince(Game.getCiv(Game.player.iCivID).getMoveUnits(l).getFromProvinceID()).getArmy(Game.getCiv(Game.player.iCivID).getMoveUnits(l).key) == null) {
                    Game.getCiv(Game.player.iCivID).removeMove(l);
                    CFG.LOG("REMOVED");
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void drawMoveUnits_Just(final SpriteBatch oSB, final float nScale) {
        oSB.setColor(Color.WHITE);
        boolean drawn = false;
        try {
            if (++ProvinceDraw.FRAME_ID >= 14400) {
                ProvinceDraw.FRAME_ID = 0;
                drawMoveUnits_Just_CheckArmies();
            }
            for (int i = 1; i < Game.getCivsSize(); ++i) {
                if (Game.getCiv(i).getMoveUnitsSize() > 0) {
                    for (int j = 0; j < Game.getCiv(i).getMoveUnitsSize(); ++j) {
                        try {
                            Game.getCiv(i).getMoveUnits(j).update();
                        }
                        catch (final Exception ex2) {}
                    }
                }
            }
            for (int k = 0; k < Game.revolutionMoveUnits.iMoveUnitsSize; ++k) {
                try {
                    Game.revolutionMoveUnits.lMoveUnits.get(k).update();
                }
                catch (final Exception ex3) {}
            }
            try {
                for (final Map.Entry<Integer, Diplomacy.DiplomacyData> entry : Game.getCiv(Game.player.iCivID).diplomacy.alliance.entrySet()) {
                    final int civID = entry.getKey();
                    if (Game.getCiv(civID).getPuppetOfCivID() != Game.player.iCivID) {
                        for (int l = 0; l < Game.getCiv(civID).getMoveUnitsSize(); ++l) {
                            try {
                                Game.getCiv(civID).getMoveUnits(l).draw_Ally(oSB, nScale);
                                drawn = true;
                            }
                            catch (final Exception ex4) {}
                        }
                    }
                }
                for (int m = 0; m < Game.getCiv(Game.player.iCivID).diplomacy.iVassalsSize; ++m) {
                    for (int civID = Game.getCiv(Game.player.iCivID).diplomacy.lVassals.get(m).c, j2 = 0; j2 < Game.getCiv(civID).getMoveUnitsSize(); ++j2) {
                        try {
                            Game.getCiv(civID).getMoveUnits(j2).draw_Ally(oSB, nScale);
                            drawn = true;
                        }
                        catch (final Exception ex5) {}
                    }
                }
                for (int j = 0; j < Game.getCiv(Game.player.iCivID).getMoveUnitsSize(); ++j) {
                    try {
                        Game.getCiv(Game.player.iCivID).getMoveUnits(j).draw(oSB, nScale);
                        drawn = true;
                    }
                    catch (final Exception ex6) {}
                }
            }
            catch (final Exception ex7) {}
        }
        catch (final Exception ex8) {}
        if (drawn) {
            try {
                Renderer.oSBBorder.end();
                Renderer.oSBBorder.begin();
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
    }
    
    public static final void buildSiegeLines(final int provinceID) {
        clearSiegeLines();
        final Province province = Game.getProvince(provinceID);
        for (int i = 0; i < province.getNeighboringProvincesSize(); ++i) {
            if (province.getCivID() == Game.getProvince(province.getNeighboringProvinces(i)).getCivID() && Game.getProvince(province.getNeighboringProvinces(i)).getFortLevel() == 0) {
                final MoveUnits_BiggestCities_Siege nLine = new MoveUnits_BiggestCities_Siege(province.getCivID(), provinceID, province.getNeighboringProvinces(i));
                ProvinceDraw.siegeLines.add(nLine);
            }
        }
        ProvinceDraw.iSiegeLinesSize = ProvinceDraw.siegeLines.size();
    }
    
    public static final void drawSiegeLines_Just(final SpriteBatch oSB, final float nScale) {
        if (ProvinceDraw.iSiegeLinesSize > 0 && Game.mapScale.getCurrentScale() > Game.DRAW_INNER_BORDERS) {
            try {
                oSB.setColor(Color.WHITE);
                for (int j = 0; j < ProvinceDraw.iSiegeLinesSize; ++j) {
                    try {
                        ProvinceDraw.siegeLines.get(j).update();
                    }
                    catch (final Exception ex2) {}
                }
                try {
                    for (int j = 0; j < ProvinceDraw.iSiegeLinesSize; ++j) {
                        try {
                            ProvinceDraw.siegeLines.get(j).draw(oSB, nScale);
                        }
                        catch (final Exception ex3) {}
                    }
                }
                catch (final Exception ex4) {}
            }
            catch (final Exception ex5) {}
            try {
                Renderer.oSBBorder.end();
                Renderer.oSBBorder.begin();
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
    }
    
    public static final void clearSiegeLines() {
        ProvinceDraw.siegeLines.clear();
        ProvinceDraw.iSiegeLinesSize = 0;
    }
    
    public static final void buildBiggestCitiesLines(final int iCivID) {
        final List<Integer> lCiv = new ArrayList<Integer>();
        lCiv.add(iCivID);
        Game.gameThreadUpdate.addSimpleTask(new Game.SimpleTask("buildBiggestCitiesLines" + iCivID) {
            @Override
            public void update() {
                ProvinceDraw.buildBiggestCitiesLines(lCiv);
                if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_DIPLOMACY) {
                    ProvinceDraw.clearBiggestCities();
                }
            }
        });
    }
    
    public static final void buildBiggestCitiesLines(final List<Integer> iCivID) {
        ProvinceDraw.biggestCitiesLines.clear();
        ProvinceDraw.iBiggestCitiesLinesSize = 0;
        for (int k = 0, kSize = iCivID.size(); k < kSize; ++k) {
            if (Game.getCiv(iCivID.get(k)).getNumOfProvinces() > 1 && Game.getCiv(iCivID.get(k)).getCapitalProvinceID() >= 0) {
                final int numOfLines = Math.min(GameValues.diplomacy.DIPLOMACY_VIEW_BIGGEST_CITIES_LINES_MAX, Math.min(Math.max(GameValues.diplomacy.DIPLOMACY_VIEW_BIGGEST_CITIES_LINES_MIN, (int)(Game.getCiv(iCivID.get(k)).getNumOfProvinces() * GameValues.diplomacy.DIPLOMACY_VIEW_BIGGEST_CITIES_LINES_PERC_OF_PROVINCES)), Game.getCiv(iCivID.get(k)).getNumOfProvinces() - 1));
                final List<Integer> tProvinces = new ArrayList<Integer>();
                for (int i = 0; i < Game.getCiv(iCivID.get(k)).getNumOfProvinces(); ++i) {
                    if (Game.getCiv(iCivID.get(k)).getProvinceID(i) != Game.getCiv(iCivID.get(k)).getCapitalProvinceID()) {
                        tProvinces.add(Game.getCiv(iCivID.get(k)).getProvinceID(i));
                    }
                }
                for (int i = 0; i < numOfLines && i < tProvinces.size(); ++i) {
                    int bestID = 0;
                    for (int j = 1, jSize = tProvinces.size(); j < jSize; ++j) {
                        if (Game.getProvince(tProvinces.get(bestID)).getPopulationTotal() < Game.getProvince(tProvinces.get(j)).getPopulationTotal()) {
                            bestID = j;
                        }
                    }
                    try {
                        final MoveUnits_BiggestCities nLine = new MoveUnits_BiggestCities(iCivID.get(k), Game.getCiv(iCivID.get(k)).getCapitalProvinceID(), tProvinces.get(bestID));
                        if (nLine.iRouteSize > 0) {
                            ProvinceDraw.biggestCitiesLines.add(nLine);
                        }
                        else {
                            --i;
                        }
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                    tProvinces.remove(bestID);
                }
                tProvinces.clear();
            }
        }
        ProvinceDraw.iBiggestCitiesLinesSize = ProvinceDraw.biggestCitiesLines.size();
    }
    
    public static final void buildBiggestCitiesLines_Province(final int iProvinceA, final int iProvinceB) {
        ProvinceDraw.biggestCitiesLines.clear();
        ProvinceDraw.iBiggestCitiesLinesSize = 0;
        final MoveUnits_BiggestCities nLine = new MoveUnits_BiggestCities(Game.getProvince(iProvinceA).getCivID(), iProvinceA, iProvinceB) {
            @Override
            public boolean isFriendlyProvince(final int nCivID, final int toProvinceID) {
                return true;
            }
            
            @Override
            public boolean canBeUsedInPath(final int nCivID, final int nProvinceID, final boolean moveToFriendlyProvince, final int toProvinceID) {
                return Game.getProvince(nProvinceID).getWasteland() < 0;
            }
        };
        if (nLine.iRouteSize > 0) {
            ProvinceDraw.biggestCitiesLines.add(nLine);
        }
        ProvinceDraw.iBiggestCitiesLinesSize = ProvinceDraw.biggestCitiesLines.size();
    }
    
    public static final void drawBiggestCitiesLines_Just(final SpriteBatch oSB, final float nScale) {
        if (ProvinceDraw.iBiggestCitiesLinesSize > 0 && Game.mapScale.getCurrentScale() > Game.DRAW_INNER_BORDERS) {
            try {
                oSB.setColor(Color.WHITE);
                for (int j = 0; j < ProvinceDraw.iBiggestCitiesLinesSize; ++j) {
                    try {
                        ProvinceDraw.biggestCitiesLines.get(j).update();
                    }
                    catch (final Exception ex2) {}
                }
                try {
                    for (int j = 0; j < ProvinceDraw.iBiggestCitiesLinesSize; ++j) {
                        try {
                            ProvinceDraw.biggestCitiesLines.get(j).draw(oSB, nScale);
                        }
                        catch (final Exception ex3) {}
                    }
                }
                catch (final Exception ex4) {}
            }
            catch (final Exception ex5) {}
            try {
                Renderer.oSBBorder.end();
                Renderer.oSBBorder.begin();
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
    }
    
    public static final void clearBiggestCities() {
        ProvinceDraw.biggestCitiesLines.clear();
        ProvinceDraw.iBiggestCitiesLinesSize = 0;
    }
    
    public static final void addDiplomacyLines(final int iProvinceA, final int iProvinceB, final Color nColor) {
        try {
            if (iProvinceA < 0 || iProvinceB < 0) {
                return;
            }
            if ((Game.getProvince(iProvinceA).getDrawProvince() || Game.getProvince(iProvinceB).getDrawProvince()) && Game.mapScale.getCurrentScale() > Game.DRAW_INNER_BORDERS) {
                for (int i = ProvinceDraw.diplomacyLines.size() - 1; i >= 0; --i) {
                    if (ProvinceDraw.diplomacyLines.get(i).getFromProvinceID() == iProvinceA && ProvinceDraw.diplomacyLines.get(i).getToProvinceLastID() == iProvinceB) {
                        return;
                    }
                }
                final MoveUnits_BiggestCities nLine = new MoveUnits_BiggestCities_Diplomacy(Game.getProvince(iProvinceA).getCivID(), iProvinceA, iProvinceB, nColor) {
                    @Override
                    public boolean isFriendlyProvince(final int nCivID, final int toProvinceID) {
                        return true;
                    }
                    
                    @Override
                    public boolean canBeUsedInPath(final int nCivID, final int nProvinceID, final boolean moveToFriendlyProvince, final int toProvinceID) {
                        return Game.getProvince(nProvinceID).getWasteland() < 0;
                    }
                };
                if (nLine.iRouteSize > 0) {
                    ProvinceDraw.diplomacyLines.add(nLine);
                }
                ProvinceDraw.iDiplomacyLinesSize = ProvinceDraw.diplomacyLines.size();
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void drawDiplomacyLines_Just(final SpriteBatch oSB, final float nScale) {
        if (ProvinceDraw.iDiplomacyLinesSize > 0) {
            try {
                oSB.setColor(Color.WHITE);
                Renderer.oSBBorder.begin();
                for (int j = ProvinceDraw.iDiplomacyLinesSize - 1; j >= 0; --j) {
                    try {
                        ProvinceDraw.diplomacyLines.get(j).update();
                    }
                    catch (final Exception ex) {
                        ProvinceDraw.diplomacyLines.remove(j);
                        CFG.exceptionStack(ex);
                    }
                }
                try {
                    for (int j = ProvinceDraw.iDiplomacyLinesSize - 1; j >= 0; --j) {
                        try {
                            if (ProvinceDraw.diplomacyLines.get(j).draw2(oSB, nScale)) {
                                ProvinceDraw.diplomacyLines.remove(j);
                            }
                        }
                        catch (final Exception ex) {
                            ProvinceDraw.diplomacyLines.remove(j);
                            CFG.exceptionStack(ex);
                        }
                    }
                    ProvinceDraw.iDiplomacyLinesSize = ProvinceDraw.diplomacyLines.size();
                }
                catch (final Exception ex2) {
                    CFG.exceptionStack(ex2);
                }
            }
            catch (final Exception ex2) {
                CFG.exceptionStack(ex2);
            }
            try {
                Renderer.oSBBorder.end();
            }
            catch (final Exception ex2) {
                CFG.exceptionStack(ex2);
            }
        }
    }
    
    public static final void clearDiplomacyLines() {
        ProvinceDraw.diplomacyLines.clear();
        ProvinceDraw.iDiplomacyLinesSize = 0;
    }
    
    public static final void addProvinceDot(final int iProvinceA, final Color nColor) {
        try {
            for (int i = ProvinceDraw.provinceDots.size() - 1; i >= 0; --i) {
                if (ProvinceDraw.provinceDots.get(i).iProvinceID == iProvinceA && ProvinceDraw.provinceDots.get(i).fPerc > 0.9f) {
                    return;
                }
            }
            ProvinceDraw.provinceDots.add(new ProvinceAnimationDot(iProvinceA, nColor));
            ProvinceDraw.iProvinceDotsSize = ProvinceDraw.provinceDots.size();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void addProvinceDot_Economy(final int iProvinceID) {
        addProvinceDot(iProvinceID, Colors.COLOR_TEXT_ECONOMY);
    }
    
    public static final void addProvinceDot_TaxEfficiency(final int iProvinceID) {
        addProvinceDot(iProvinceID, Colors.HOVER_YELLOW);
    }
    
    public static final void addProvinceDot_Manpower(final int iProvinceID) {
        addProvinceDot(iProvinceID, Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getColor(1.0f));
    }
    
    public static final void addProvinceDot_GrowthRate(final int iProvinceID) {
        addProvinceDot(iProvinceID, Colors.COLOR_POPULATION);
    }
    
    public static final void addProvinceDot_Infrastructure(final int iProvinceID) {
        addProvinceDot(iProvinceID, Colors.HOVER_LEFT);
    }
    
    public static final void drawProvinceDots_Just(final SpriteBatch oSB, final float nScale) {
        if (ProvinceDraw.iProvinceDotsSize > 0 && Game.mapScale.getCurrentScale() > Game.DRAW_INNER_BORDERS) {
            try {
                oSB.setColor(Color.WHITE);
                Renderer.oSBBorder.begin();
                try {
                    for (int j = ProvinceDraw.iProvinceDotsSize - 1; j >= 0; --j) {
                        try {
                            if (ProvinceDraw.provinceDots.get(j).draw(oSB, nScale)) {
                                ProvinceDraw.provinceDots.remove(j);
                            }
                        }
                        catch (final Exception ex) {
                            CFG.exceptionStack(ex);
                        }
                    }
                    ProvinceDraw.iProvinceDotsSize = ProvinceDraw.provinceDots.size();
                }
                catch (final Exception ex2) {
                    CFG.exceptionStack(ex2);
                }
            }
            catch (final Exception ex2) {
                CFG.exceptionStack(ex2);
            }
            try {
                Renderer.oSBBorder.end();
            }
            catch (final Exception ex2) {
                CFG.exceptionStack(ex2);
            }
        }
    }
    
    public static final void clearProvinceDots() {
        ProvinceDraw.provinceDots.clear();
        ProvinceDraw.iProvinceDotsSize = 0;
    }
    
    public static void drawReligionConversion(final SpriteBatch oSB) {
        if (Game.mapScale.getCurrentScale() >= Game.DRAW_ARMY_MIN_SCALE) {
            drawReligionConversion(oSB, ProvinceDrawArmy.DRAW_ARMY_ALPHA);
        }
        else if (Game.mapTouchManager.selectMode) {
            drawReligionConversion(oSB, 1.0f);
        }
        else if (ProvinceDrawArmy.drawHideAnimation && Game.mapScale.getCurrentScale() >= ProvinceDrawArmy.DRAW_ARMY_MIN_SCALE_ANIMATION) {
            drawReligionConversion(oSB, ProvinceDrawArmy.DRAW_ARMY_ALPHA);
        }
    }
    
    public static final void drawReligionConversion(final SpriteBatch oSB, final float fAlpha) {
        try {
            if (Game.getCiv(Game.player.iCivID).convertReligion.provincesSize > 0) {
                final int iW = Game.religionManager.religionImages.get(Game.getCiv(Game.player.iCivID).getReligionID()).getWidth();
                final int iH = Game.religionManager.religionImages.get(Game.getCiv(Game.player.iCivID).getReligionID()).getHeight();
                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, fAlpha));
                for (int i = 0; i < Game.getCiv(Game.player.iCivID).convertReligion.provincesSize; ++i) {
                    if (Game.getProvince(Game.getCiv(Game.player.iCivID).convertReligion.provinces.get(i)).getDrawProvince()) {
                        drawReligionConversion(oSB, fAlpha, Game.getCiv(Game.player.iCivID).convertReligion.provinces.get(i), iW, iH);
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        oSB.setColor(Color.WHITE);
    }
    
    private static final void drawReligionConversion(final SpriteBatch oSB, final float fAlpha, final int provinceID, final int iW, final int iH) {
        try {
            final int iPosX = (int)((((Game.getProvince(provinceID).getCitiesSize() > 0) ? Game.getProvince(provinceID).getCity(0).getPosX() : Game.getProvince(provinceID).iCenterShiftX) + Game.getProvince(provinceID).getTranslateProvincePosX()) * Game.mapScale.getCurrentScale()) - iW / 2;
            final int iPosY = (int)((((Game.getProvince(provinceID).getCitiesSize() > 0) ? Game.getProvince(provinceID).getCity(0).getPosY() : Game.getProvince(provinceID).iCenterShiftY) + Game.mapCoords.getPosY()) * Game.mapScale.getCurrentScale() - iH / 2);
            Game.religionManager.religionImages.get(Game.getCiv(Game.player.iCivID).getReligionID()).draw(oSB, iPosX, iPosY, iW, iH);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    static {
        ProvinceDraw.drawExtraDetails = new DrawExtraDetails();
        ProvinceDraw.progressBarBG = new Color(0.09803922f, 0.09803922f, 0.15686275f, 1.0f);
        ProvinceDraw.progressBar = new Color(0.29411766f, 0.49019608f, 0.64705884f, 1.0f);
        ProvinceDraw.progressBar2 = new Color(0.3529412f, 0.64705884f, 0.8039216f, 1.0f);
        ProvinceDraw.drawProvincesCiv_HoveredFlagID = 0;
        ProvinceDraw.PROVINCE_DIPLOMACY_NEUTRAL = new Color(0.8235294f, 0.8235294f, 0.78431374f, 1.0f);
        ProvinceDraw.PROVINCE_DIPLOMACY_NEUTRAL_GREEN = new Color(0.9019608f, 0.9019608f, 0.8235294f, 1.0f);
        ProvinceDraw.PROVINCE_DIPLOMACY_RED = new Color(0.92941177f, 0.627451f, 0.5882353f, 1.0f);
        ProvinceDraw.PROVINCE_DIPLOMACY_RED2 = new Color(0.627451f, 0.19607843f, 0.19607843f, 1.0f);
        ProvinceDraw.PROVINCE_DIPLOMACY_GREEN = new Color(0.5176471f, 0.74509805f, 0.43137255f, 1.0f);
        ProvinceDraw.PROVINCE_DIPLOMACY_GREEN2 = new Color(0.0f, 0.4f, 0.0f, 1.0f);
        ProvinceDraw.PROVINCE_DIPLOMACY_ALLIANCE = new Color(0.0f, 0.627451f, 0.92156863f, 1.0f);
        ProvinceDraw.PROVINCE_DIPLOMACY_AT_WAR = new Color(0.8039216f, 0.0f, 0.0f, 1.0f);
        ProvinceDraw.PROVINCE_DIPLOMACY_VASSAL = new Color(0.21568628f, 0.9529412f, 0.8745098f, 1.0f);
        ProvinceDraw.PROVINCE_DIPLOMACY_PACT = new Color(0.8f, 0.8f, 0.0f, 1.0f);
        ProvinceDraw.PROVINCE_DIPLOMACY_INDEPENDENCE = new Color(0.74509805f, 0.23529412f, 0.627451f, 1.0f);
        ProvinceDraw.PROVINCE_DIPLOMACY_INDEPENDENCE2 = new Color(0.5882353f, 0.15686275f, 0.47058824f, 1.0f);
        ProvinceDraw.PROVINCE_DIPLOMACY_MILITARY_ACCESS = new Color(0.08627451f, 0.08627451f, 0.5411765f, 1.0f);
        ProvinceDraw.PROVINCE_DIPLOMACY_DEFENSIVE_PACT = new Color(0.5019608f, 0.23921569f, 0.7490196f, 1.0f);
        ProvinceDraw.PROVINCE_DIPLOMACY_TRUCE = new Color(0.92156863f, 0.92156863f, 0.92156863f, 1.0f);
        ProvinceDraw.joinType = JoinType.POINTY;
        ProvinceDraw.joinType_Shadow = JoinType.SMOOTH;
        ProvinceDraw.lineWidth = 1.0f;
        ProvinceDraw.oDrawMoveUnits = new DrawMoveUnits() {
            @Override
            public void drawMoveUnits(final SpriteBatch oSB) {
            }
        };
        ProvinceDraw.FRAME_ID = 0;
        ProvinceDraw.siegeLines = new ArrayList<MoveUnits_BiggestCities_Siege>();
        ProvinceDraw.iSiegeLinesSize = 0;
        ProvinceDraw.biggestCitiesLines = new ArrayList<MoveUnits_BiggestCities>();
        ProvinceDraw.iBiggestCitiesLinesSize = 0;
        ProvinceDraw.diplomacyLines = new ArrayList<MoveUnits_BiggestCities>();
        ProvinceDraw.iDiplomacyLinesSize = 0;
        ProvinceDraw.provinceDots = new ArrayList<ProvinceAnimationDot>();
        ProvinceDraw.iProvinceDotsSize = 0;
    }
    
    public static class DrawExtraDetails
    {
        public void draw(final SpriteBatch oSB) {
        }
    }
    
    public interface DrawProvinces
    {
        void draw(final SpriteBatch p0);
    }
    
    public interface DrawMoveUnits
    {
        void drawMoveUnits(final SpriteBatch p0);
    }
}
