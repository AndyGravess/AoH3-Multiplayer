// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski;

import java.util.Objects;
import aoc.kingdoms.lukasz.map.diplomacy.Vassal;
import aoc.kingdoms.lukasz.jakowski.zOther.Point_XY;
import aoc.kingdoms.lukasz.map.province.ProvinceHover;
import com.badlogic.gdx.graphics.Color;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.PixmapIO;
import aoc.kingdoms.lukasz.map.WondersManager;
import aoc.kingdoms.lukasz.map.civilization.CivilizationRegionsManager;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.graphics.Pixmap;
import aoc.kingdoms.lukasz.map.province.ProvinceBorderManager;
import aoc.kingdoms.lukasz.menus.Init_SelectLanguage;
import aoc.kingdoms.lukasz.jakowski.SaveLoad.LoadManager;
import aoc.kingdoms.lukasz.jakowski.SaveLoad.SaveManager;
import com.badlogic.gdx.utils.Json;
import aoc.kingdoms.lukasz.menusInGame.Province.InGame_ProvinceArmy;
import java.util.Iterator;
import aoc.kingdoms.lukasz.map.army.ArmyDivision;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Function;
import aoc.kingdoms.lukasz.map.army.ArmyRegiment;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.menusInGame.DrawOver.InGameDrawOver_RegroupArmy;
import aoc.kingdoms.lukasz.menusInGame.InGame;
import aoc.kingdoms.lukasz.menusInGame.DrawOver.InGameDrawOver;
import aoc.kingdoms.lukasz.menusInGame.DrawOver.InGameDrawOver_InvasionArmy;
import aoc.kingdoms.lukasz.jakowski.Renderer.RendererGame;
import aoc.kingdoms.lukasz.jakowski.Steam.SteamAchievementsManager;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.menusInGame.Info.InGame_Info;
import aoc.kingdoms.lukasz.jakowski.desktop.DesktopLauncher;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.map.terrain.Terrain;
import aoc.kingdoms.lukasz.map.BuildingsManager;
import aoc.kingdoms.lukasz.map.province.ProvinceInvest;
import com.badlogic.gdx.graphics.Texture;
import aoc.kingdoms.lukasz.map.civilization.CivilizationBonuses;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import aoc.kingdoms.lukasz.map.map.Map_Data;
import com.badlogic.gdx.graphics.Cursor;
import java.util.concurrent.ConcurrentLinkedDeque;
import aoc.kingdoms.lukasz.jakowski.setting.SettingsManager;
import aoc.kingdoms.lukasz.jakowski.Player.Player;
import aoc.kingdoms.lukasz.map.rebels.RevolutionMoveUnits;
import aoc.kingdoms.lukasz.map.rebels.RevolutionManager;
import aoc.kingdoms.lukasz.map.battles.BattleManager;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.map.province.ProvinceCut;
import aoc.kingdoms.lukasz.map.moveUnits.other.MoveUnits_RegroupLine;
import aoc.kingdoms.lukasz.map.army.ArmyDivision_Shadow;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.map.province.animation.ProvinceAnimationHover;
import aoc.kingdoms.lukasz.map.province.animation.ProvinceAnimation;
import aoc.kingdoms.lukasz.map.army.ArmyRecruit;
import aoc.kingdoms.lukasz.textures.Image;
import aoc.kingdoms.lukasz.map.allianceHRE.Alliance;
import aoc.kingdoms.lukasz.map.province.data.ProvinceData_Population;
import aoc.kingdoms.lukasz.map.province.data.ProvinceData10;
import aoc.kingdoms.lukasz.map.province.data.ProvinceData9;
import aoc.kingdoms.lukasz.map.province.data.ProvinceData8;
import aoc.kingdoms.lukasz.map.province.data.ProvinceData7;
import aoc.kingdoms.lukasz.map.province.data.ProvinceData6;
import aoc.kingdoms.lukasz.map.province.data.ProvinceData5;
import aoc.kingdoms.lukasz.map.province.data.ProvinceData4;
import aoc.kingdoms.lukasz.map.province.data.ProvinceData3;
import aoc.kingdoms.lukasz.map.province.data.ProvinceData2;
import aoc.kingdoms.lukasz.map.province.data.ProvinceData;
import aoc.kingdoms.lukasz.map.province.Province;
import java.util.List;
import aoc.kingdoms.lukasz.map.map.MapScenarios;
import aoc.kingdoms.lukasz.jakowski.Stats.StatsManager;
import aoc.kingdoms.lukasz.jakowski.FlagsEditor.FlagManager;
import aoc.kingdoms.lukasz.map.ReligionManager;
import aoc.kingdoms.lukasz.map.IdeologiesManager;
import aoc.kingdoms.lukasz.jakowski.GameThreads.GameThread_Events;
import aoc.kingdoms.lukasz.jakowski.GameThreads.GameThread_Turns;
import aoc.kingdoms.lukasz.jakowski.GameThreads.GameThread_Update;
import aoc.kingdoms.lukasz.jakowski.GameThreads.GameThread;
import aoc.kingdoms.lukasz.menu.HoverManager;
import aoc.kingdoms.lukasz.menu.MenuManager;
import aoc.kingdoms.lukasz.map.advisors.AdvisorManager;
import aoc.kingdoms.lukasz.map.GeneralManager;
import aoc.kingdoms.lukasz.map.map.GeographicalRegions;
import aoc.kingdoms.lukasz.map.map.Continents;
import aoc.kingdoms.lukasz.map.terrain.TerrainManager;
import aoc.kingdoms.lukasz.map.map.MapTouchManager;
import aoc.kingdoms.lukasz.map.map.MapModeManager;
import aoc.kingdoms.lukasz.map.map.MapOver;
import aoc.kingdoms.lukasz.map.map.MapCities;
import aoc.kingdoms.lukasz.map.clouds.CloudsManager;
import aoc.kingdoms.lukasz.map.map.MapScroll;
import aoc.kingdoms.lukasz.map.map.MapEdgeMove;
import aoc.kingdoms.lukasz.map.map.MapScale;
import aoc.kingdoms.lukasz.map.map.MapCoords;
import aoc.kingdoms.lukasz.map.map.MapBG;
import aoc.kingdoms.lukasz.map.map.Map;
import aoc.kingdoms.lukasz.jakowski.AI.Values.AI_ValuesDiplomacy;
import aoc.kingdoms.lukasz.jakowski.AI.Values.AI_ValuesArmies;
import aoc.kingdoms.lukasz.jakowski.AI.Values.AI_ValuesCores;
import aoc.kingdoms.lukasz.jakowski.AI.Values.AI_ValuesConvert;
import aoc.kingdoms.lukasz.jakowski.AI.Values.AI_ValuesBuild;
import aoc.kingdoms.lukasz.jakowski.AI.Values.AI_Values;
import aoc.kingdoms.lukasz.jakowski.AI.AI_Manager;
import java.util.Random;

public class Game
{
    public static int versionWidth;
    public static Random oR;
    public static AI_Manager aiManager;
    public static AI_Values aiValues;
    public static AI_ValuesBuild aiValuesBuild;
    public static AI_ValuesConvert aiValuesConvert;
    public static AI_ValuesCores aiValuesCores;
    public static AI_ValuesArmies aiValuesArmies;
    public static AI_ValuesDiplomacy aiValuesDiplomacy;
    public static Map map;
    public static MapBG mapBG;
    public static MapCoords mapCoords;
    public static MapScale mapScale;
    public static MapEdgeMove mapEdgeMove;
    public static MapScroll mapScroll;
    public static CloudsManager cloudsAnimation;
    public static MapCities mapCities;
    public static MapOver mapOver;
    public static MapOver mapOverSea;
    public static MapModeManager mapModes;
    public static MapTouchManager mapTouchManager;
    public static TerrainManager terrainManager;
    public static Continents continents;
    public static GeographicalRegions geographicalRegions;
    public static GeneralManager generalManager;
    public static AdvisorManager advisorManager;
    public static MenuManager menuManager;
    public static HoverManager hoverManager;
    public static SoundsManager soundsManager;
    public static AmbienceManager ambienceManager;
    public static Keyboard keyboard;
    public static AnimationManager animationManager;
    public static GameThread gameThread;
    public static GameThread_Update gameThreadUpdate;
    public static GameThread_Turns gameThreadTurns;
    public static GameThread_Events gameThreadEvents;
    public static IdeologiesManager ideologiesManager;
    public static ReligionManager religionManager;
    public static Game_Ages gameAges;
    public static FlagManager flagManager;
    public static StatsManager stats;
    public static boolean FOG_OF_WAR;
    public static boolean SPECTATOR_MODE;
    public static boolean SANDBOX;
    public static boolean SCENARIO_EVENTS;
    public static boolean ENABLE_CALL_VASSALS;
    public static int HOURS_PER_TURN;
    public static int scenarioID;
    public static boolean reloadScenario;
    public static MapScenarios mapScenarios;
    public static boolean highTextureSettings;
    public static int aiAggressivnes;
    public static int difficultyID;
    public static float DRAW_CITIES_MIN_SCALE;
    public static float DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE;
    public static float DRAW_INNER_BORDERS;
    public static float DRAW_OCCUPIED_PROVINCES_MIN_SCALE;
    public static float DRAW_ARMY_MIN_SCALE;
    public static float DRAW_OCCUPIED_SCALE;
    public static FlagData flagData;
    public static List<Province> lProvinces;
    public static int iProvincesSize;
    public static List<ProvinceData> lProvincesData;
    public static List<ProvinceData2> lProvincesData2;
    public static List<ProvinceData3> lProvincesData3;
    public static List<ProvinceData4> lProvincesData4;
    public static List<ProvinceData5> lProvincesData5;
    public static List<ProvinceData6> lProvincesData6;
    public static List<ProvinceData7> lProvincesData7;
    public static List<ProvinceData8> lProvincesData8;
    public static List<ProvinceData9> lProvincesData9;
    public static List<ProvinceData10> lProvincesData10;
    public static List<ProvinceData_Population> lProvincesPopulation;
    public static List<Alliance> alliancesSpecial;
    public static int alliancesSpecialSize;
    public static List<Image> alliancesSpecial_Flag;
    public static ArmyRecruit armyRecruit;
    public static GameActiveProvince gameActiveProvince;
    public static ProvinceAnimation activeProvince_Animation_Data;
    public static ProvinceAnimation selectedProvinces_Animation_Data;
    public static ProvinceAnimationHover animationHover;
    public static int iActiveProvince;
    public static int iHoveredProvinceID;
    public static MenuElement_Hover provinceHover_Informations;
    public static int iOldActiveProvinceID;
    public static int iOldHoveredProvinceID;
    public static HoveredArmy hoveredArmy;
    public static List<HoveredArmy> activeArmy;
    public static int activeArmySize;
    public static int iHoveredCapitalProvinceID;
    public static boolean invasionArmyMode;
    public static List<Integer> invasionArmyProvinces;
    public static int invasionArmyProvincesSize;
    public static boolean regroupArmyMode;
    public static List<Integer> regroupArmyProvinces;
    public static List<ArmyDivision_Shadow> regroupArmyShadows;
    public static int iRegroupArmyProvincesSize;
    public static MoveUnits_RegroupLine regroupArmyLine;
    public static int hoveredShipID;
    public static HoveredBattle hoveredBattle;
    public static int hoveredSiegeID;
    public static boolean chooseProvinceMode;
    public static int MAX_BELOW_ZERO_POINT_X;
    public static List<ProvinceCut> cutProvinces;
    public static RegionManager regions;
    public static boolean updateProvincesInView;
    public static List<Integer> lProvincesInView;
    public static List<Integer> lSeaProvincesInView;
    public static List<Integer> lWastelandProvincesInView;
    public static List<Integer> lExtraProvincesInView;
    public static int NUM_OF_PROVINCES_IN_VIEW;
    public static int NUM_OF_EXTRA_PROVINCES_IN_VIEW;
    public static int NUM_OF_SEA_PROVINCES_IN_VIEW;
    public static int NUM_OF_WASTELAND_PROVINCES_IN_VIEW;
    public static List<Civilization> lCivs;
    public static int iCivsSize;
    public static BattleManager battleManager;
    public static RevolutionManager revolutionManager;
    public static RevolutionMoveUnits revolutionMoveUnits;
    public static Player player;
    public static SettingsManager settingsManager;
    public static LanguageManager lang;
    public static float deltaTime;
    private static ConcurrentLinkedDeque<SimpleTask> simpleTasks;
    public static List<Cursor> lCursors;
    public static int activeCursorID;
    public static int maxWastelandLvl;
    public static int hoveredProvinceBG_LoadedID;
    public static Image hoveredProvinceBG;
    private static final float HIGHLIGHTED_PROVINCES_ANIMATION_TIME = 750.0f;
    private static final float HIGHLIGHTED_PROVINCES_ANIMATION_TIME_BACK = 350.0f;
    public static float fDashedLine_Percentage_HighlitedProvinceBorder;
    public static long lDashedLineTime_Percentage_HighlitedProvinceBorder;
    public static boolean highlightedProvinceBorder_BackAnimation;
    public static boolean highlightedProvinceBorder_Update;
    public static int inViewY_CordsY;
    public static int inViewY_CordsY_Height;
    public static int inViewX_CordsX;
    public static int inViewX_CordsX_Width;
    public static float iMaxDistance;
    public static float iMaxDistanceManhattan;
    public static MapDistance mapDistance;
    
    public static void loadAiValues() {
        Game.aiValuesBuild.BUILD_SCORE_CAPITAL_TOTAL = 0;
        for (int i = Game.aiValuesBuild.BUILD_SCORE_CAPITAL.length - 1; i >= 0; --i) {
            final AI_ValuesBuild aiValuesBuild3;
            final AI_ValuesBuild aiValuesBuild2;
            final AI_ValuesBuild ai_ValuesBuild3;
            final AI_ValuesBuild a\u0131_ValuesBuild;
            final AI_ValuesBuild ai_ValuesBuild2 = a\u0131_ValuesBuild = (ai_ValuesBuild3 = (aiValuesBuild2 = (aiValuesBuild3 = Game.aiValuesBuild)));
            a\u0131_ValuesBuild.BUILD_SCORE_CAPITAL_TOTAL += Game.aiValuesBuild.BUILD_SCORE_CAPITAL[i];
        }
    }
    
    public static final void updateDaultScenarioID() {
        for (int i = 0; i < Game.mapScenarios.SCENARIOS_SIZE; ++i) {
            if (Game.mapScenarios.lScenarios_TagsList.get(i).equals(Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.DefaultScenario)) {
                Game.scenarioID = i;
                Game.mapScenarios.sActiveScenarioTag = Game.mapScenarios.lScenarios_TagsList.get(i);
                break;
            }
        }
    }
    
    public static boolean highTextueSettings() {
        return Game.highTextureSettings;
    }
    
    public static void loadLowSettings() {
        if (CFG.isDesktop()) {
            try {
                if (FileManager.loadFile("settings/LoadTexturesHigh.txt").exists()) {
                    final FileHandle tempFileT2 = FileManager.loadFile("settings/LoadTexturesHigh.txt");
                    Game.highTextureSettings = Boolean.parseBoolean(tempFileT2.readString());
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
        else {
            Game.highTextureSettings = true;
        }
    }
    
    public static final void saveTextueSettings() {
        final FileHandle fileSave = FileManager.IS_MAC ? Gdx.files.external("settings/LoadTexturesHigh.txt") : Gdx.files.local("settings/LoadTexturesHigh.txt");
        fileSave.writeString("" + Game.highTextureSettings, false);
    }
    
    public static final void initDifficulty() {
        if (!Game.SPECTATOR_MODE && Game_Calendar.TURN_ID == 1) {
            final int turnID = Game_Calendar.TURN_ID + GameValues.difficulty.BONUS_DURATION;
            final CivilizationBonuses bonusDifficulty = new CivilizationBonuses(turnID);
            bonusDifficulty.IncomeProduction = GameValues.difficulty.INCOME_PRODUCTION[Game.difficultyID];
            bonusDifficulty.MonthlyIncome = GameValues.difficulty.MONTHLY_INCOME[Game.difficultyID];
            bonusDifficulty.MonthlyLegacy_Percentage = GameValues.difficulty.LEGACY[Game.difficultyID];
            bonusDifficulty.MaxManpower_Percentage = GameValues.difficulty.MANPOWER[Game.difficultyID];
            bonusDifficulty.RecruitArmyCost = GameValues.difficulty.RECRUIT_ARMY_COST[Game.difficultyID];
            bonusDifficulty.RecruitmentTime = GameValues.difficulty.RECRUIT_ARMY_TIME[Game.difficultyID];
            bonusDifficulty.RegimentsLimit = GameValues.difficulty.REGIMENTS_LIMIT[Game.difficultyID];
            bonusDifficulty.ConstructionCost = GameValues.difficulty.CONSTRUCTION_COST[Game.difficultyID];
            bonusDifficulty.CoreCost = GameValues.difficulty.CORE_COST[Game.difficultyID];
            bonusDifficulty.ReligionCost = GameValues.difficulty.RELIGION_COST[Game.difficultyID];
            getCiv(Game.player.iCivID).addCivilizationBonus_Temporary(bonusDifficulty);
        }
    }
    
    public static final void clearAllianceSpecial() {
        try {
            Game.alliancesSpecial.clear();
            Game.alliancesSpecialSize = 0;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            for (int i = Game.alliancesSpecial_Flag.size() - 1; i >= 0; --i) {
                Game.alliancesSpecial_Flag.get(i).dispose();
                Game.alliancesSpecial_Flag.set(i, null);
            }
            Game.alliancesSpecial_Flag.clear();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static int getAllianceSpecialNumOfCivs(final int allianceID) {
        int out = 0;
        if (getCiv(Game.alliancesSpecial.get(allianceID).iLeaderCivID).getNumOfProvinces() > 0 && !Game.alliancesSpecial.get(allianceID).firstTier.contains(Game.alliancesSpecial.get(allianceID).iLeaderCivID) && !Game.alliancesSpecial.get(allianceID).secondTier.contains(Game.alliancesSpecial.get(allianceID).iLeaderCivID)) {
            ++out;
        }
        try {
            for (int i = Game.alliancesSpecial.get(allianceID).firstTier.size() - 1; i >= 0; --i) {
                if (getCiv(Game.alliancesSpecial.get(allianceID).firstTier.get(i)).getNumOfProvinces() > 0) {
                    ++out;
                }
            }
            for (int i = Game.alliancesSpecial.get(allianceID).secondTier.size() - 1; i >= 0; --i) {
                if (getCiv(Game.alliancesSpecial.get(allianceID).secondTier.get(i)).getNumOfProvinces() > 0) {
                    ++out;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return out;
    }
    
    public static void addAllianceSpecial(final Alliance nAlliance) {
        for (int i = 0; i < Game.alliancesSpecial.size(); ++i) {
            if (Game.alliancesSpecial.get(i).Name_Alliance.equals(nAlliance.Name_Alliance)) {
                Game.alliancesSpecial.set(i, nAlliance);
                return;
            }
        }
        Game.alliancesSpecial.add(nAlliance);
    }
    
    public static void removeAllianceSpecial(final String tag) {
        for (int i = 0; i < Game.alliancesSpecial.size(); ++i) {
            if (Game.alliancesSpecial.get(i).Name_Alliance.equals(tag)) {
                Game.alliancesSpecial.remove(i);
                return;
            }
        }
    }
    
    public static void removeAllianceSpecial(final int id) {
        try {
            Game.alliancesSpecial.remove(id);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static void loadAlliancesSpecial_Images() {
        for (int i = 0; i < Game.alliancesSpecial.size(); ++i) {
            if (FileManager.loadFile("gfx/flagsXH/" + Game.alliancesSpecial.get(i).FlagTag + ".png").exists()) {
                Game.alliancesSpecial_Flag.add(new Image(new Texture(FileManager.loadFile("gfx/flagsXH/" + Game.alliancesSpecial.get(i).FlagTag + ".png")), Texture.TextureFilter.Linear));
            }
            else if (FileManager.loadFile("gfx/flagsH/" + Game.alliancesSpecial.get(i).FlagTag + ".png").exists()) {
                Game.alliancesSpecial_Flag.add(new Image(new Texture(FileManager.loadFile("gfx/flagsH/" + Game.alliancesSpecial.get(i).FlagTag + ".png")), Texture.TextureFilter.Linear));
            }
            else if (FileManager.loadFile("gfx/flags/" + Game.alliancesSpecial.get(i).FlagTag + ".png").exists()) {
                Game.alliancesSpecial_Flag.add(new Image(new Texture(FileManager.loadFile("gfx/flags/" + Game.alliancesSpecial.get(i).FlagTag + ".png")), Texture.TextureFilter.Nearest));
            }
            else {
                Game.alliancesSpecial_Flag.add(new Image(new Texture(FileManager.loadFile("gfx/flagsXH/ran.png")), Texture.TextureFilter.Nearest));
            }
        }
    }
    
    public static void setActiveProvinceID(final int nActiveProvince) {
        Game.iActiveProvince = nActiveProvince;
        MapCities.lTIME_ACTIVE_CITIES = ((Game.iHoveredProvinceID == Game.iActiveProvince) ? ((MapCities.lTIME_HOVERED_CITIES > CFG.currentTimeMillis - 2000L) ? (CFG.currentTimeMillis - (long)(550.0f * ((CFG.currentTimeMillis - MapCities.lTIME_HOVERED_CITIES) / 2000.0f))) : 0L) : CFG.currentTimeMillis);
    }
    
    public static void setHoveredProvinceID(final int nHoveredProvinceID) {
        if (Game.iOldHoveredProvinceID != Game.iHoveredProvinceID && nHoveredProvinceID >= 0) {
            Game.iOldHoveredProvinceID = Game.iHoveredProvinceID;
            if (Game.mapScale.getCurrentScale() >= Game.DRAW_INNER_BORDERS) {
                Game.soundsManager.playHover();
            }
        }
        Game.iHoveredProvinceID = nHoveredProvinceID;
        MapCities.lTIME_HOVERED_CITIES = CFG.currentTimeMillis;
    }
    
    public static final float getInvestEconomyGrowth(final int iProvinceID) {
        return GameValues.economy.INVEST_ECONOMY_GROWTH;
    }
    
    public static float getInvestCost(final int nProvinceID) {
        final Province province = getProvince(nProvinceID);
        try {
            if (province.iProvinceInvestSize > 0) {
                return Math.max(1.0f, (GameValues.economy.INVEST_COST + (province.getEconomyWithBonuses() + getInvestEconomyGrowth(nProvinceID) * province.provinceInvestDaysLeft.get(0).daysLeft / province.provinceInvestDaysLeft.get(0).investTime + getInvestEconomyGrowth(nProvinceID) * (province.iProvinceInvestSize - 1)) * GameValues.economy.INVEST_COST_PER_ECONOMY) * (1.0f + province.provBonuses.InvestInEconomyCost + province.getInfrastructure() * GameValues.infrastructure.INFRASTRUCTURE_INVEST_COST_PER_LVL + getCiv(province.getCivID()).getInflation() + getCiv(province.getCivID()).civBonuses.InvestInEconomyCost + GameValues.civRank.CIV_RANK_INVEST_ECONOMY_COST[getCiv(province.getCivID()).iCivRankID]));
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return Math.max(1.0f, (GameValues.economy.INVEST_COST + province.getEconomyWithBonuses() * GameValues.economy.INVEST_COST_PER_ECONOMY) * (1.0f + province.provBonuses.InvestInEconomyCost + province.getInfrastructure() * GameValues.infrastructure.INFRASTRUCTURE_INVEST_COST_PER_LVL + getCiv(province.getCivID()).getInflation() + getCiv(province.getCivID()).civBonuses.InvestInEconomyCost + GameValues.civRank.CIV_RANK_INVEST_ECONOMY_COST[getCiv(province.getCivID()).iCivRankID]));
    }
    
    public static float getInvestCost_Legacy(final int nProvinceID) {
        final Province province = getProvince(nProvinceID);
        try {
            if (province.iProvinceInvestSize > 0) {
                return Math.max(1.0f, (GameValues.economy.INVEST_COST_LEGACY + (province.getEconomyWithBonuses() + getInvestEconomyGrowth(nProvinceID) * province.provinceInvestDaysLeft.get(0).daysLeft / province.provinceInvestDaysLeft.get(0).investTime + getInvestEconomyGrowth(nProvinceID) * (province.iProvinceInvestSize - 1)) * GameValues.economy.INVEST_COST_LEGACY_PER_ECONOMY) * (1.0f + province.provBonuses.InvestInEconomyCost + province.getInfrastructure() * GameValues.infrastructure.INFRASTRUCTURE_INVEST_COST_PER_LVL + getCiv(province.getCivID()).getInflation() + getCiv(province.getCivID()).civBonuses.InvestInEconomyCost + GameValues.civRank.CIV_RANK_INVEST_ECONOMY_COST[getCiv(province.getCivID()).iCivRankID]));
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return Math.max(1.0f, (GameValues.economy.INVEST_COST_LEGACY + province.getEconomyWithBonuses() * GameValues.economy.INVEST_COST_LEGACY_PER_ECONOMY) * (1.0f + province.provBonuses.InvestInEconomyCost + province.getInfrastructure() * GameValues.infrastructure.INFRASTRUCTURE_INVEST_COST_PER_LVL + getCiv(province.getCivID()).getInflation() + getCiv(province.getCivID()).civBonuses.InvestInEconomyCost + GameValues.civRank.CIV_RANK_INVEST_ECONOMY_COST[getCiv(province.getCivID()).iCivRankID]));
    }
    
    public static int getInvestTime(final int nCivID) {
        return GameValues.economy.INVEST_IN_PROVINCE_DAYS;
    }
    
    public static float getMaxEconomy(final int nProvinceID) {
        return getProvince(nProvinceID).getGrowthRateWithBonuses() * GameValues.economy.MAX_ECONOMY_GROWTH_RATE;
    }
    
    public static boolean canInvestInEconomy(final int nProvinceID) {
        return getProvince(nProvinceID).getEconomyWithBonuses() + getProvince(nProvinceID).iProvinceInvestSize * getInvestEconomyGrowth(nProvinceID) > getMaxEconomy(nProvinceID);
    }
    
    public static final float getExploitEconomy_Lose(final int iProvinceID) {
        return Math.min(GameValues.economy.EXPLOIT_ECONOMY, (getProvince(iProvinceID).getEconomy() > GameValues.economy.EXPLOIT_ECONOMY_MIN_ECONOMY) ? (getProvince(iProvinceID).getEconomy() - GameValues.economy.EXPLOIT_ECONOMY_MIN_ECONOMY) : 0.0f);
    }
    
    public static final float getExploitEconomy_Gain(final int iProvinceID) {
        return getInvestCost(iProvinceID) * getExploitEconomy_Lose(iProvinceID) / GameValues.economy.INVEST_ECONOMY_GROWTH * GameValues.economy.EXPLOIT_ECONOMY_GAIN_PER_INVEST_COST;
    }
    
    public static final boolean exploitEconomy(final int iProvinceID) {
        if (getProvince(iProvinceID).getEconomy() > GameValues.economy.EXPLOIT_ECONOMY_MIN_ECONOMY) {
            getCiv(getProvince(iProvinceID).getCivID()).addGold(getExploitEconomy_Gain(iProvinceID));
            getProvince(iProvinceID).setEconomy(getProvince(iProvinceID).getEconomy() - getExploitEconomy_Lose(iProvinceID));
            getProvince(iProvinceID).updateAfterEconomyChange();
            getCiv(getProvince(iProvinceID).getCivID()).updateTotalIncomePerMonth();
            return true;
        }
        return false;
    }
    
    public static float getLootValue(final int iProvinceID) {
        return getProvince(iProvinceID).getProvinceIncome() * GameValues.siege.LOOT_PROVINCE_PER_PROVINCE_INCOME * getProvince(iProvinceID).getLoot();
    }
    
    public static final float getDevelopInfrastructureCost(final int nProvinceID) {
        final Province province = getProvince(nProvinceID);
        return Math.max(1.0f, (GameValues.infrastructure.DEVELOP_INFRASTRUCTURE_COST_GOLD + (province.getInfrastructure() + province.iProvinceDevelopInfrastructureSize) * GameValues.infrastructure.DEVELOP_INFRASTRUCTURE_COST_GOLD_PER_INFRASTRUCTURE) * (1.0f + province.provBonuses.DevelopInfrastructureCost + getCiv(province.getCivID()).civBonuses.DevelopInfrastructureCost));
    }
    
    public static final float getDevelopInfrastructureCostLegacy(final int nProvinceID) {
        final Province province = getProvince(nProvinceID);
        return Math.max(1.0f, (GameValues.infrastructure.DEVELOP_INFRASTRUCTURE_COST_LEGACY + (province.getInfrastructure() + province.iProvinceDevelopInfrastructureSize) * GameValues.infrastructure.DEVELOP_INFRASTRUCTURE_COST_LEGACY_PER_INFRASTRUCTURE) * (1.0f + province.provBonuses.DevelopInfrastructureCost + getCiv(province.getCivID()).civBonuses.DevelopInfrastructureCost));
    }
    
    public static final int getDevelopInfrastructureTime(final int nProvinceID) {
        final Province province = getProvince(nProvinceID);
        return Math.max(1, GameValues.infrastructure.DEVELOP_INFRASTRUCTURE_TIME + (province.getInfrastructure() + province.iProvinceDevelopInfrastructureSize) * GameValues.infrastructure.DEVELOP_INFRASTRUCTURE_TIME_PER_INFRASTRUCTURE);
    }
    
    public static float getProvinceMaintenanceEconomy(final int iCivID, final int iProvinceID) {
        return GameValues.province.PROVINCE_MAINTENANCE_PER_ECONOMY * getProvince(iProvinceID).getEconomyWithBonuses();
    }
    
    public static float getProvinceMaintenanceTax(final int iCivID, final int iProvinceID) {
        return GameValues.province.PROVINCE_MAINTENANCE_PER_TAX * getProvince(iProvinceID).getEconomyWithBonuses();
    }
    
    public static float getProvinceMaintenanceManpower(final int iCivID, final int iProvinceID) {
        return GameValues.province.PROVINCE_MAINTENANCE_PER_MANPOWER * getProvince(iProvinceID).getEconomyWithBonuses();
    }
    
    public static float getDevastationOccupiedProvince(final int iCivID) {
        return GameValues.siege.DEVASTATION_LOOTED * (1.0f + getCiv(iCivID).civBonuses.Devastation);
    }
    
    public static float getIncomeFromVassal(final int iCivID, final int iVassalCivID) {
        return getIncomeFromVassal(iCivID, iVassalCivID, getCiv(iCivID).diplomacy.getVassal_TributeLevel(iVassalCivID));
    }
    
    public static float getIncomeFromVassal(final int iCivID, final int iVassalCivID, final int iLevel) {
        return getCiv(iVassalCivID).fTotalIncomePerMonth * GameValues.vassal.VASSAL_INCOME_TO_LORD[iLevel] * (1.0f + getCiv(iCivID).civBonuses.IncomeFromVassals);
    }
    
    public static double getManpowerFromVassal_INFO(final int iCivID, final int iVassalCivID, final int iLevel) {
        return (getCiv(iVassalCivID).fManpowerMax + getCiv(iVassalCivID).fManpowerMax_ToLord - GameValues.manpower.MANPOWER_MAX_BASE) * GameValues.vassal.VASSAL_MANPOWER_TO_LORD[iLevel];
    }
    
    public static float getIncomeFromEconomy(final int iProvinceID) {
        final Province province = getProvince(iProvinceID);
        final Civilization civ = getCiv(province.getCivID());
        return province.getEconomyWithBonuses() * GameValues.economy.INCOME_ECONOMY_PER_ECONOMY * Math.max(0.01f, 1.0f + GameValues.budget.TAXATION_LEVEL_INCOME_ECONOMY[civ.getTaxationLevel()] + civ.fProsperity_AverageEconomy / 100.0f * GameValues.prosperity.PROSPERITY_INCOME + civ.civBonuses.IncomeEconomy - province.getDevastation() - civ.getCorruption() + ((province.getReligion() == civ.getReligionID()) ? 0.0f : GameValues.religion.BASE_INCOME_DIFFERENT_RELIGION) + (province.haveACore ? 0.0f : GameValues.core.BASE_INCOME_NON_CORE));
    }
    
    public static float getIncomeFromEconomy_Invest(final int iProvinceID) {
        final Province province = getProvince(iProvinceID);
        final Civilization civ = getCiv(province.getCivID());
        return getInvestEconomyGrowth(iProvinceID) * GameValues.economy.INCOME_ECONOMY_PER_ECONOMY * Math.max(0.01f, 1.0f + GameValues.budget.TAXATION_LEVEL_INCOME_ECONOMY[civ.getTaxationLevel()] + civ.fProsperity_AverageEconomy / 100.0f * GameValues.prosperity.PROSPERITY_INCOME + civ.civBonuses.IncomeEconomy - province.getDevastation() - civ.getCorruption() + ((province.getReligion() == civ.getReligionID()) ? 0.0f : GameValues.religion.BASE_INCOME_DIFFERENT_RELIGION) + (province.haveACore ? 0.0f : GameValues.core.BASE_INCOME_NON_CORE));
    }
    
    public static int getBuildingConstructionCost(final int iCivID, final int iProvinceID, final int building, final int buildingID) {
        final Province province = (iProvinceID >= 0) ? getProvince(iProvinceID) : null;
        return Math.max(1, (int)Math.ceil((BuildingsManager.buildings.get(building).CostGold[buildingID] + ((BuildingsManager.buildings.get(building).ResearchPoints != null) ? (GameValues.research.RESEARCH_BUILDING_COST_PER_RESEARCH_POINT * getCiv(iCivID).fResearchPerMonth) : 0.0f)) * ((iProvinceID >= 0) ? (Game.terrainManager.terrains.get(province.getTerrainID()).BuildCost + getCiv(iCivID).getInflation() + GameValues.infrastructure.INFRASTRUCTURE_CONSTRUCTION_COST_PER_LVL * province.getInfrastructure() + province.provBonuses.ConstructionCost + getCiv(iCivID).civBonuses.ConstructionCost + getBuildingGroupCost(iCivID, building)) : 1.0f)));
    }
    
    public static int getBuildingConstructionTime(final int iCivID, final int iProvinceID, final int building, final int buildingID) {
        final Province province = (iProvinceID >= 0) ? getProvince(iProvinceID) : null;
        return Math.max(1, (int)Math.ceil(BuildingsManager.buildings.get(building).ConstructionTime[buildingID] * (1.0f + ((iProvinceID >= 0) ? (province.getInfrastructure() * GameValues.infrastructure.INFRASTRUCTURE_CONSTRUCTION_TIME_PER_LVL + province.provBonuses.ConstructionTimeBonus) : 0.0f) + getCiv(iCivID).civBonuses.ConstructionTime)));
    }
    
    public static float getBuildingGroupCost(final int iCivID, final int building) {
        switch (BuildingsManager.buildings.get(building).GroupID) {
            case 0: {
                return getCiv(iCivID).civBonuses.AdministrationBuildingsCost;
            }
            case 1: {
                return getCiv(iCivID).civBonuses.MilitaryBuildingsCost;
            }
            case 2: {
                return getCiv(iCivID).civBonuses.EconomyBuildingsCost;
            }
            default: {
                return 0.0f;
            }
        }
    }
    
    public static float getCoreCreationCost(final int iProvinceID) {
        final Province province = getProvince(iProvinceID);
        final Civilization civ = getCiv(province.getCivID());
        return Math.max(1.0f, (GameValues.core.CORE_CREATION_COST_DEFAULT + province.getEconomyWithBonuses() * GameValues.core.CORE_CREATION_COST_PER_ECONOMY + province.getInfrastructure() * GameValues.infrastructure.INFRASTRUCTURE_CORE_COST_PER_LVL) * (1.0f + civ.getInflation() + civ.getWarWeariness() * GameValues.warWeariness.WW_CORE_COST_PER_POINT + civ.civBonuses.CoreCost / 100.0f));
    }
    
    public static int getCoreCreationTime(final int iProvinceID) {
        return Math.max(1, (int)(GameValues.core.CORE_CREATION_TIME_DEFAULT + getProvince(iProvinceID).getTaxEfficiencyWithBonuses() * GameValues.core.CORE_CREATION_TIME_PER_TAX_EFFICIENCY));
    }
    
    public static float getProvinceTaxEfficiency(final int iProvinceID) {
        return Math.max(0.1f, getProvince(iProvinceID).getTaxEfficiencyWithBonuses()) * (1.0f + GameValues.budget.TAXATION_LEVEL_EFFICIENCY[getCiv(getProvince(iProvinceID).getCivID()).getTaxationLevel()]);
    }
    
    public static float getIncomePopulationTaxation(final int iProvinceID) {
        return getIncomePopulationTaxation(iProvinceID, getProvinceTaxEfficiency(iProvinceID));
    }
    
    public static float getIncomePopulationTaxation(final int iProvinceID, final float taxEfficiency) {
        final Province province = getProvince(iProvinceID);
        final Civilization civ = getCiv(province.getCivID());
        return GameValues.tax.BASE_INCOME_POPULATION_INCOME + Math.min(province.getPopulationTotal(), GameValues.tax.TAX_EFFICIENCY_MAX_POPULATION) / GameValues.tax.TAX_EFFICIENCY_POPULATION_DIVIDE * (taxEfficiency / 100.0f) * Math.max(0.01f, 1.0f + civ.civBonuses.IncomeTaxation - province.getDevastation() - civ.getCorruption() + ((province.getReligion() == civ.getReligionID()) ? 0.0f : GameValues.religion.BASE_INCOME_DIFFERENT_RELIGION) + (province.haveACore ? 0.0f : GameValues.core.BASE_INCOME_NON_CORE));
    }
    
    public static float getIncomePopulationTaxation_Invest(final int iProvinceID) {
        return getIncomePopulationTaxation(iProvinceID, getProvinceTaxEfficiency(iProvinceID) + GameValues.tax.INCREASE_TAX_EFFICIENCY_GROWTH) - getIncomePopulationTaxation(iProvinceID, getProvinceTaxEfficiency(iProvinceID));
    }
    
    public static final int getManpowerMaxFromProvinceManpowerLvl(final int iProvinceID) {
        final Province province = getProvince(iProvinceID);
        return (int)(province.getManpower() * GameValues.manpower.MANPOWER_MAX_PER_PROVINCE_MANPOWER_LVL * (Math.min(province.getGrowthRateWithBonuses(), (float)GameValues.manpower.MANPOWER_MAX_PER_PROVINCE_MAX_GROWTH_RATE) / 100.0f) * (1.0f + province.provBonuses.LocalManpower + ((province.getReligion() != getCiv(province.getCivID()).getReligionID()) ? GameValues.manpower.MANPOWER_MAX_DIFFERENT_RELIGION : 0.0f) + (province.haveACore(province.getCivID()) ? 0.0f : GameValues.manpower.MANPOWER_MAX_NON_CORE)));
    }
    
    public static final float getManpowerFromProvincePerMonth_Info(final int iProvinceID) {
        return getManpowerMaxFromProvinceManpowerLvl(iProvinceID) / GameValues.manpower.MANPOWER_FULL_RECOVERY_MONTHS;
    }
    
    public static int getIncreseManpower_MaximumManpowerInfo(final int iProvinceID) {
        final Province province = getProvince(iProvinceID);
        return (int)(GameValues.manpower.MANPOWER_MAX_PER_PROVINCE_MANPOWER_LVL * GameValues.manpower.INCREASE_MANPOWER_GROWTH * (Math.min(province.getGrowthRateWithBonuses(), (float)GameValues.manpower.MANPOWER_MAX_PER_PROVINCE_MAX_GROWTH_RATE) / 100.0f) * (1.0f + province.provBonuses.LocalManpower + ((province.getReligion() != getCiv(province.getCivID()).getReligionID()) ? GameValues.manpower.MANPOWER_MAX_DIFFERENT_RELIGION : 0.0f) + (province.haveACore(province.getCivID()) ? 0.0f : GameValues.manpower.MANPOWER_MAX_NON_CORE)));
    }
    
    public static float getIncreseManpower_ManpowerPerMonthInfo(final int nProvinceID) {
        return getIncreseManpower_MaximumManpowerInfo(nProvinceID) / GameValues.manpower.MANPOWER_FULL_RECOVERY_MONTHS;
    }
    
    public static final double getManpowerPerMonth(final int iCivID, final double iManpowerMax) {
        return iManpowerMax / GameValues.manpower.MANPOWER_FULL_RECOVERY_MONTHS * (1.0f + getCiv(iCivID).civBonuses.ManpowerRecoverySpeed);
    }
    
    public static final float getManpowerRecoveryFromADisbandedArmy(final int iCivID) {
        return Math.min(1.0f, GameValues.manpower.MANPOWER_RECOVERY_FROM_DISBANDED_ARMY_DEFAULT + getCiv(iCivID).civBonuses.ManpowerRecoveryFromADisbandedArmy);
    }
    
    public static final int getMilitaryAcademyForGenerals_MaxLvl(final int iCivID) {
        return GameValues.militaryAcademy.MILITARY_ACADEMY_FOR_GENERALS_MAX_LVL + getCiv(iCivID).civBonuses.MaximumLevelOfTheMilitaryAcademyForGenerals;
    }
    
    public static final int getMilitaryAcademyForGenerals_GeneralAttack(final int iCivID) {
        return GameValues.militaryAcademy.MILITARY_ACADEMY_FOR_GENERALS_ATTACK_PER_LVL * getCiv(iCivID).getMilitaryAcademyForGeneralsLevel();
    }
    
    public static final int getMilitaryAcademyForGenerals_GeneralDefense(final int iCivID) {
        return GameValues.militaryAcademy.MILITARY_ACADEMY_FOR_GENERALS_DEFENSE_PER_LVL * getCiv(iCivID).getMilitaryAcademyForGeneralsLevel();
    }
    
    public static final float getMilitaryAcademyForGenerals_Cost(final int iCivID) {
        return GameValues.militaryAcademy.MILITARY_ACADEMY_FOR_GENERALS_COST + GameValues.militaryAcademy.MILITARY_ACADEMY_FOR_GENERALS_COST_PER_LVL * getCiv(iCivID).getMilitaryAcademyForGeneralsLevel();
    }
    
    public static final float getMilitaryAcademyForGenerals_MaintenanceCost(final int iCivID) {
        return GameValues.militaryAcademy.MILITARY_ACADEMY_FOR_GENERALS_MAINTENANCE_COST_PER_LVL * getCiv(iCivID).getMilitaryAcademyForGeneralsLevel();
    }
    
    public static final int getMilitaryAcademy_MaxLvl(final int iCivID) {
        return GameValues.militaryAcademy.MILITARY_ACADEMY_MAX_LVL + getCiv(iCivID).civBonuses.MaximumLevelOfTheMilitaryAcademy;
    }
    
    public static final int getMilitaryAcademy_Attack(final int iCivID) {
        return GameValues.militaryAcademy.MILITARY_ACADEMY_ATTACK_PER_LVL * getCiv(iCivID).getMilitaryAcademyLevel();
    }
    
    public static final int getMilitaryAcademy_Defense(final int iCivID) {
        return GameValues.militaryAcademy.MILITARY_ACADEMY_DEFENSE_PER_LVL * getCiv(iCivID).getMilitaryAcademyLevel();
    }
    
    public static final int getMilitaryAcademy_RegimentsLimit(final int iCivID) {
        return GameValues.militaryAcademy.MILITARY_ACADEMY_REGIMENTS_LIMIT_PER_LVL * getCiv(iCivID).getMilitaryAcademyLevel();
    }
    
    public static final float getMilitaryAcademy_Cost(final int iCivID) {
        return GameValues.militaryAcademy.MILITARY_ACADEMY_COST + GameValues.militaryAcademy.MILITARY_ACADEMY_COST_PER_LVL * getCiv(iCivID).getMilitaryAcademyLevel();
    }
    
    public static final float getMilitaryAcademy_MaintenanceCost(final int iCivID) {
        return GameValues.militaryAcademy.MILITARY_ACADEMY_MAINTENANCE_COST_PER_LVL * getCiv(iCivID).getMilitaryAcademyLevel();
    }
    
    public static final int getSupremeCourt_MaxLvl(final int iCivID) {
        return GameValues.supremeCourt.SUPREME_COURT_MAX_LVL + getCiv(iCivID).civBonuses.MaximumLevelOfTheSupremeCourt;
    }
    
    public static final float getSupremeCourt_Corruption(final int iCivID) {
        return GameValues.supremeCourt.SUPREME_COURT_CORRUPTION_REDUCTION_PER_LVL * getCiv(iCivID).getSupremeCourtLevel();
    }
    
    public static final float getSupremeCourt_Cost(final int iCivID) {
        return GameValues.supremeCourt.SUPREME_COURT_COST + GameValues.supremeCourt.SUPREME_COURT_COST_PER_LVL * getCiv(iCivID).getSupremeCourtLevel();
    }
    
    public static final int getCapital_MaxLvl(final int iCivID) {
        return GameValues.capital.CAPITAL_MAX_LVL + getCiv(iCivID).civBonuses.MaximumLevelOfCapitalCity;
    }
    
    public static final float getCapital_Income(final int iCivID) {
        return GameValues.capital.CAPITAL_INCOME_PER_LVL * getCiv(iCivID).getCapitalLevel();
    }
    
    public static final float getCapital_ProvincesMaintenance(final int iCivID) {
        return GameValues.capital.CAPITAL_PROVINCES_MAINTENANCE_COST_PER_LVL * getCiv(iCivID).getCapitalLevel();
    }
    
    public static final float getCapital_MaxResearch(final int iCivID) {
        return GameValues.capital.CAPITAL_MAX_RESEARCH_PER_LVL * getCiv(iCivID).getCapitalLevel();
    }
    
    public static final float getCapital_Cost(final int iCivID) {
        return GameValues.capital.CAPITAL_COST + GameValues.capital.CAPITAL_COST_PER_LVL * getCiv(iCivID).getCapitalLevel();
    }
    
    public static final float getAtomicBombCasualties(final int iProvinceID) {
        return GameValues.atomic.ATOMIC_BOMB_POPULATION_CASUALTIES * Math.max(0.01f, 1.0f + getProvince(iProvinceID).provBonuses.CasualtiesNuclearAttacks);
    }
    
    public static final int dropAtomicBomb(final int iCivID, final int iProvinceID) {
        if (getCiv(iCivID).getNukes() > 0 && DiplomacyManager.isAtWar(iCivID, getProvince(iProvinceID).getCivID())) {
            DesktopLauncher.SMS("Bomb: " + iCivID + " " + iProvinceID);
            getCiv(iCivID).setNukes(getCiv(iCivID).getNukes() - 1);
            final int iCasualties = getProvince(iProvinceID).atomicBombDropped();
            if (iCivID == Game.player.iCivID || getProvince(iProvinceID).getCivID() == Game.player.iCivID) {
                InGame_Info.iCivID = iCivID;
                InGame_Info.iCivID2 = getProvince(iProvinceID).getCivID();
                Game.menuManager.rebuildInGame_Info(Game.lang.get("AtomicBombing") + ": " + getProvince(iProvinceID).getProvinceName(), Game.lang.get("Casualties") + ": " + CFG.getNumberWithSpaces("" + CFG.getPrecision2((float)iCasualties, 1)));
                InGame_Info.imgID = Images.infoAtomic;
                if (iCivID == Game.player.iCivID) {
                    SteamAchievementsManager.unlockAchievement(SteamAchievementsManager.DROP_NUKE);
                }
            }
            RendererGame.addNuke(iProvinceID);
        }
        return -1;
    }
    
    public static final int dropAtomicBomb_Client(final int iCivID, final int iProvinceID) {
        getCiv(iCivID).setNukes(getCiv(iCivID).getNukes() - 1);
        final int iCasualties = getProvince(iProvinceID).atomicBombDropped();
        if (iCivID == Game.player.iCivID || getProvince(iProvinceID).getCivID() == Game.player.iCivID) {
            InGame_Info.iCivID = iCivID;
            InGame_Info.iCivID2 = getProvince(iProvinceID).getCivID();
            Game.menuManager.rebuildInGame_Info(Game.lang.get("AtomicBombing") + ": " + getProvince(iProvinceID).getProvinceName(), Game.lang.get("Casualties") + ": " + CFG.getNumberWithSpaces("" + CFG.getPrecision2((float)iCasualties, 1)));
            InGame_Info.imgID = Images.infoAtomic;
            if (iCivID == Game.player.iCivID) {
                SteamAchievementsManager.unlockAchievement(SteamAchievementsManager.DROP_NUKE);
            }
            RendererGame.addNuke(iProvinceID);
        }
        return -1;
    }
    
    public static final int getNuclearReactor_MaxLvl(final int iCivID) {
        return GameValues.atomic.NUCLEAR_REACTOR_MAX_LVL + getCiv(iCivID).civBonuses.MaximumLevelOfTheSupremeCourt;
    }
    
    public static final float getNuclearReactor_ProductionEfficiency(final int iCivID) {
        return GameValues.atomic.NUCLEAR_REACTOR_PRODUCTION_EFFICIENCY_PER_LVL * getCiv(iCivID).getNuclearReactorLevel();
    }
    
    public static final float getNuclearReactor_AtomicBombCost(final int iCivID) {
        return GameValues.atomic.NUCLEAR_REACTOR_ATOMIC_BOMB_COST_PER_LEVEL * getCiv(iCivID).getNuclearReactorLevel();
    }
    
    public static final float getNuclearReactor_Cost(final int iCivID) {
        return GameValues.atomic.NUCLEAR_REACTOR_COST + GameValues.atomic.NUCLEAR_REACTOR_COST_PER_LVL * getCiv(iCivID).getNuclearReactorLevel();
    }
    
    public static final float getAtomicBombCost(final int iCivID) {
        return Math.max(1.0f, (GameValues.atomic.ATOMIC_BOMB_COST + GameValues.atomic.ATOMIC_BOMB_COST_PER_ATOMIC_BOMB * (getCiv(iCivID).getNukes() + getCiv(iCivID).iNukesSize)) * (1.0f + getNuclearReactor_AtomicBombCost(iCivID)));
    }
    
    public static final int getAtomicBombProductionTime(final int iCivID) {
        return (int)GameValues.atomic.ATOMIC_BOMB_PRODUCTION_TIME;
    }
    
    public static float getIncreaseManpowerCost(final int nProvinceID) {
        return Math.max(1.0f, (GameValues.manpower.INCREASE_MANPOWER_COST + getProvince(nProvinceID).getManpower() * GameValues.manpower.INCREASE_MANPOWER_COST_PER_LEVEL) * (1.0f + getProvince(nProvinceID).provBonuses.IncreaseManpowerCost + getCiv(getProvince(nProvinceID).getCivID()).civBonuses.IncreaseManpowerCost / 100.0f));
    }
    
    public static float getIncreaseManpowerCostLegacy(final int nProvinceID) {
        return Math.max(0.05f, (GameValues.manpower.INCREASE_MANPOWER_COST_LEGACY + getProvince(nProvinceID).getManpower() * GameValues.manpower.INCREASE_MANPOWER_COST_LEGACY_PER_LEVEL) * (1.0f + getProvince(nProvinceID).provBonuses.IncreaseManpowerCost + getCiv(getProvince(nProvinceID).getCivID()).civBonuses.IncreaseManpowerCost / 100.0f));
    }
    
    public static int getIncreseManpowerTime(final int nCivID) {
        return GameValues.manpower.INCREASE_MANPOWER_IN_PROVINCE_DAYS;
    }
    
    public static float getLegacyPerUniqueResources(final int iCivID) {
        return GameValues.legacy.LEGACY_PER_UNIQUE_RESOURCE * getCiv(iCivID).iUniqueResources * Math.min(GameValues.legacy.LEGACY_PER_UNIQUE_RESOURCE_PER_TECHS_MAX, getCiv(iCivID).getResearchedTechnologies() * GameValues.legacy.LEGACY_PER_UNIQUE_RESOURCE_PER_TECHS);
    }
    
    public static float getIncreaseTaxEfficiencyCost(final int nProvinceID) {
        return Math.max(1.0f, (GameValues.tax.INCREASE_TAX_EFFICIENCY_COST + getProvince(nProvinceID).getTaxEfficiencyWithBonuses() * GameValues.tax.INCREASE_TAX_EFFICIENCY_COST_PER_TAX_EFFICIENCY) * (1.0f + getProvince(nProvinceID).provBonuses.IncreaseTaxEfficiencyCost + getCiv(getProvince(nProvinceID).getCivID()).getInflation() + getCiv(getProvince(nProvinceID).getCivID()).civBonuses.IncreaseTaxEfficiencyCost));
    }
    
    public static float getIncreaseTaxEfficiencyCostLegacy(final int nProvinceID) {
        return Math.max(1.0f, (GameValues.tax.INCREASE_TAX_EFFICIENCY_COST_LEGACY + getProvince(nProvinceID).getTaxEfficiencyWithBonuses() * GameValues.tax.INCREASE_TAX_EFFICIENCY_COST_LEGACY_PER_TAX) * (1.0f + getProvince(nProvinceID).provBonuses.IncreaseTaxEfficiencyCost + getCiv(getProvince(nProvinceID).getCivID()).getInflation() + getCiv(getProvince(nProvinceID).getCivID()).civBonuses.IncreaseTaxEfficiencyCost));
    }
    
    public static int getIncreasTaxEfficiencyTime(final int nCivID) {
        return GameValues.tax.INCREASE_TAX_EFFICIENCY_IN_PROVINCE_DAYS;
    }
    
    public static float getIncreaseGrowthRateCost(final int nProvinceID) {
        return (float)Math.max(1, (int)((GameValues.growthRate.INCREASE_GROWTH_RATE_COST + (getProvince(nProvinceID).getGrowthRateWithBonuses() + getProvince(nProvinceID).iProvinceIncreaseGrowthRateSize * GameValues.growthRate.INCREASE_GROWTH_RATE_GROWTH) * GameValues.growthRate.INCREASE_GROWTH_RATE_COST_PER_GROWTH_RATE) * (Game.terrainManager.terrains.get(getProvince(nProvinceID).getTerrainID()).IncreaseGrowthRateCost + getProvince(nProvinceID).provBonuses.IncreaseGrowthRateCost + getCiv(getProvince(nProvinceID).getCivID()).civBonuses.IncreaseGrowthRateCost)));
    }
    
    public static int getIncreaseGrowthRateTime(final int nCivID) {
        return GameValues.growthRate.INCREASE_GROWTH_RATE_IN_PROVINCE_DAYS;
    }
    
    public static final void checkHoveredArmy_Fog() {
        if (Game.hoveredArmy.iProvinceID >= 0 && !getProvince(Game.hoveredArmy.iProvinceID).getFogDrawArmy()) {
            Game.hoveredArmy.iProvinceID = -1;
        }
    }
    
    public static void setInvasionArmyMode() {
        setInvasionArmyMode(!Game.invasionArmyMode);
    }
    
    public static void setInvasionArmyMode(final boolean nInvasionArmyMode) {
        if (Game.invasionArmyMode != nInvasionArmyMode) {
            Game.invasionArmyMode = nInvasionArmyMode;
            invasionArmy_Clear();
            CFG.brushTool = false;
            Game.menuManager.rebuildInGame_ProvinceArmy_InvasionArmy();
            InGame.drawOver = (Game.invasionArmyMode ? new InGameDrawOver_InvasionArmy() : new InGameDrawOver());
        }
    }
    
    public static void invasionArmy_AddProvince(final int nProvinceID) {
        try {
            if (Game.invasionArmyProvinces.contains(nProvinceID)) {
                return;
            }
            if (getProvince(nProvinceID).isOccupied()) {
                if (getCiv(getProvince(nProvinceID).getCivID()).getPuppetOfCivID() != Game.player.iCivID) {
                    return;
                }
            }
            else if (!DiplomacyManager.isAtWar(Game.player.iCivID, getProvince(nProvinceID).getCivID())) {
                return;
            }
            Game.invasionArmyProvinces.add(nProvinceID);
            Game.invasionArmyProvincesSize = Game.invasionArmyProvinces.size();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static void invasionArmy_UndoProvince() {
        try {
            if (Game.invasionArmyProvincesSize > 0) {
                Game.invasionArmyProvinces.remove(Game.invasionArmyProvincesSize - 1);
                Game.invasionArmyProvincesSize = Game.invasionArmyProvinces.size();
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static void invasionArmy_RemoveProvince(final int nProvinceID) {
        try {
            for (int i = 0; i < Game.invasionArmyProvincesSize; ++i) {
                if (Game.invasionArmyProvinces.get(i) == nProvinceID) {
                    Game.invasionArmyProvinces.remove(i);
                    Game.invasionArmyProvincesSize = Game.invasionArmyProvinces.size();
                    return;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static void invasionArmy_Clear() {
        Game.invasionArmyProvinces.clear();
        Game.invasionArmyProvincesSize = 0;
    }
    
    public static void setRegroupArmyMode() {
        setRegroupArmyMode(!Game.regroupArmyMode);
    }
    
    public static void setRegroupArmyMode(final boolean nRegroupArmyMode) {
        if (Game.regroupArmyMode != nRegroupArmyMode) {
            Game.regroupArmyMode = nRegroupArmyMode;
            regroupArmy_Clear();
            CFG.brushTool = false;
            Game.menuManager.rebuildInGame_ProvinceArmy_RegroupArmy();
            if (Game.regroupArmyMode) {
                InGame.drawOver = new InGameDrawOver_RegroupArmy();
            }
            else {
                InGame.drawOver = new InGameDrawOver();
                Game.regroupArmyLine = null;
            }
        }
    }
    
    public static void regroupArmy_AddProvince(final int nProvinceID) {
        try {
            for (int i = 0; i < Game.iRegroupArmyProvincesSize; ++i) {
                if (Game.regroupArmyProvinces.get(i) == nProvinceID) {
                    return;
                }
            }
            Game.regroupArmyProvinces.add(nProvinceID);
            Game.iRegroupArmyProvincesSize = Game.regroupArmyProvinces.size();
            regroupArmy_BuildArmyShadow();
            Game.regroupArmyLine = new MoveUnits_RegroupLine(Game.regroupArmyProvinces);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static void regroupArmy_UndoProvince() {
        try {
            if (Game.iRegroupArmyProvincesSize > 0) {
                Game.regroupArmyProvinces.remove(Game.iRegroupArmyProvincesSize - 1);
                Game.iRegroupArmyProvincesSize = Game.regroupArmyProvinces.size();
                regroupArmy_BuildArmyShadow();
                Game.regroupArmyLine = new MoveUnits_RegroupLine(Game.regroupArmyProvinces);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static void regroupArmy_RemoveProvince(final int nProvinceID) {
        try {
            for (int i = 0; i < Game.iRegroupArmyProvincesSize; ++i) {
                if (Game.regroupArmyProvinces.get(i) == nProvinceID) {
                    Game.regroupArmyProvinces.remove(i);
                    Game.iRegroupArmyProvincesSize = Game.regroupArmyProvinces.size();
                    regroupArmy_BuildArmyShadow();
                    Game.regroupArmyLine = new MoveUnits_RegroupLine(Game.regroupArmyProvinces);
                    return;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static void regroupArmy_BuildArmyShadow() {
        try {
            Game.regroupArmyShadows.clear();
            final ArrayList<Integer> numOfArmies = new ArrayList<Integer>();
            for (int i = 0; i < Game.iRegroupArmyProvincesSize; ++i) {
                numOfArmies.add(0);
            }
            if (Game.iRegroupArmyProvincesSize > 0) {
                for (int i = 0; i < Game.activeArmySize; ++i) {
                    final ArmyPos nArmyPos = findArmy_FullCheck(Game.activeArmy.get(i).iCivID, Game.activeArmy.get(i).key);
                    if (nArmyPos != null) {
                        Game.regroupArmyShadows.add(new ArmyDivision_Shadow(getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).sArmy, Game.regroupArmyProvinces.get(i % Game.iRegroupArmyProvincesSize), numOfArmies.get(i % Game.iRegroupArmyProvincesSize)));
                        numOfArmies.set(i % Game.iRegroupArmyProvincesSize, numOfArmies.get(i % Game.iRegroupArmyProvincesSize) + 1);
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static void regroupArmy_Move() {
        try {
            if (Game.iRegroupArmyProvincesSize > 0) {
                for (int i = 0; i < Game.activeArmySize; ++i) {
                    final ArmyPos nArmyPos = findArmy_FullCheck(Game.activeArmy.get(i).iCivID, Game.activeArmy.get(i).key);
                    if (nArmyPos != null) {
                        int extraY = 0;
                        try {
                            for (int j = 0; j < i; ++j) {
                                if (Game.activeArmy.get(i).iProvinceID == Game.activeArmy.get(j).iProvinceID) {
                                    ++extraY;
                                }
                            }
                        }
                        catch (final Exception ex) {
                            CFG.exceptionStack(ex);
                        }
                        Game.player.playerData.invasion.removeInvasion(Game.activeArmy.get(i).key);
                        getCiv(Game.activeArmy.get(i).iCivID).newMove(nArmyPos.iProvinceID, Game.regroupArmyProvinces.get(i % Game.iRegroupArmyProvincesSize), Game.activeArmy.get(i).key, extraY, false);
                        final ArmyDivision army = getProvince(nArmyPos.iProvinceID).getArmy(getProvince(nArmyPos.iProvinceID).getArmyKeyID(Game.activeArmy.get(i).key));
                        final List<ArmyRegiment> lArmyRegiment = army.lArmyRegiment;
                        final ArrayList<String> list = new ArrayList<String>();
                        for (final ArmyRegiment regiment : army.lArmyRegiment) {
                            final int uID = regiment.uID;
                            final int aID = regiment.aID;
                            final int num = regiment.num;
                            final float mo = regiment.mo;
                            final String key = regiment.key;
                            list.add(uID + " " + aID + " " + num + " " + mo + " " + key + " " + army.lArmyRegiment.size() + " " + Game.activeArmy.get(i).key + " " + Game.activeArmy.get(i).iCivID + " " + nArmyPos.iProvinceID);
                        }
                        final String listString = list.stream().map((Function<? super Object, ?>)String::valueOf).collect((Collector<? super Object, ?, String>)Collectors.joining(","));
                        DesktopLauncher.SMS("DELETE = ARMY: " + Game.activeArmy.get(i).key + " " + Game.activeArmy.get(i).iCivID + " " + nArmyPos.iProvinceID);
                        DesktopLauncher.SMS("CR: " + listString);
                        DesktopLauncher.SMS("Move: " + Game.activeArmy.get(i).iCivID + " " + nArmyPos.iProvinceID + " " + Game.regroupArmyProvinces.get(i % Game.iRegroupArmyProvincesSize) + " " + extraY + " " + Game.activeArmy.get(i).key);
                    }
                }
            }
            setRegroupArmyMode(false);
            Game.soundsManager.playMove();
            if (Game.menuManager.getVisibleInGame_ProvinceArmy()) {
                Game.menuManager.rebuildInGame_ProvinceArmy(true, true);
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
    }
    
    public static void regroupArmy_Clear() {
        Game.regroupArmyProvinces.clear();
        Game.regroupArmyShadows.clear();
        Game.iRegroupArmyProvincesSize = 0;
        Game.regroupArmyLine = null;
    }
    
    public static final void addActiveArmy(final HoveredArmy nHA) {
        for (int i = 0; i < Game.activeArmySize; ++i) {
            if (Game.activeArmy.get(i).key.equals(nHA.key)) {
                return;
            }
        }
        Game.activeArmy.add(nHA);
        Game.activeArmySize = Game.activeArmy.size();
    }
    
    public static final boolean isAlreadyActiveArmy(final String key) {
        for (int i = 0; i < Game.activeArmySize; ++i) {
            if (Game.activeArmy.get(i).key.equals(key)) {
                return true;
            }
        }
        return false;
    }
    
    public static final void setActiveArmy(final int nProvinceID, final String sKey) {
        clearActiveArmy();
        try {
            final int tID = getProvince(nProvinceID).getArmyKeyID(sKey);
            if (tID >= 0) {
                final HoveredArmy nHA = new HoveredArmy();
                nHA.key = getProvince(nProvinceID).getArmy(tID).key;
                nHA.iCivID = getProvince(nProvinceID).getArmy(tID).civID;
                nHA.iProvinceID = nProvinceID;
                nHA.iArmyID = tID;
                Game.activeArmy.add(nHA);
                Game.activeArmySize = Game.activeArmy.size();
            }
        }
        catch (final IndexOutOfBoundsException ex) {}
    }
    
    public static final void updateActiveArmy_MoveUnits(final String key, final int oldProvinceID, final int newProvinceID) {
        try {
            if (Game.hoveredArmy.iProvinceID == oldProvinceID) {
                Game.hoveredArmy.iProvinceID = newProvinceID;
                Game.hoveredArmy.iArmyID = getProvince(Game.hoveredArmy.iProvinceID).getArmyKeyID(Game.hoveredArmy.key);
                if (Game.hoveredArmy.iArmyID < 0) {
                    Game.hoveredArmy.iProvinceID = -1;
                }
            }
            for (int i = 0; i < Game.activeArmySize; ++i) {
                if (Game.activeArmy.get(i).iProvinceID == oldProvinceID && Game.activeArmy.get(i).key.equals(key)) {
                    Game.activeArmy.get(i).iArmyID = getProvince(newProvinceID).getArmyKeyID(Game.activeArmy.get(i).key);
                    if (Game.activeArmy.get(i).iArmyID >= 0) {
                        Game.activeArmy.get(i).iProvinceID = newProvinceID;
                        Game.activeArmy.get(i).iArmyID = newProvinceID;
                        if (Game.menuManager.getVisibleInGame_ProvinceArmy() && InGame_ProvinceArmy.sActiveKEY.equals(key)) {
                            addSimpleTask(new SimpleTask("rebuildProvinceArmy") {
                                @Override
                                public void update() {
                                    Game.menuManager.rebuildInGame_ProvinceArmy(true, false);
                                }
                            });
                        }
                    }
                    else {
                        Game.activeArmy.remove(i);
                        Game.activeArmySize = Game.activeArmy.size();
                        if (Game.activeArmySize == 0) {
                            Game.menuManager.setVisibleInGame_ProvinceArmy(false);
                        }
                        else if (Game.menuManager.getVisibleInGame_ProvinceArmy() && InGame_ProvinceArmy.sActiveKEY.equals(key)) {
                            addSimpleTask(new SimpleTask("rebuildProvinceArmy") {
                                @Override
                                public void update() {
                                    Game.menuManager.rebuildInGame_ProvinceArmy(true, false);
                                }
                            });
                        }
                    }
                    return;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void clearActiveArmy() {
        Game.activeArmy.clear();
        Game.activeArmySize = 0;
        setRegroupArmyMode(false);
    }
    
    public static final void removeActiveArmy(final int id) {
        Game.activeArmy.remove(id);
        Game.activeArmySize = Game.activeArmy.size();
        if (Game.activeArmySize == 0) {
            setRegroupArmyMode(false);
        }
    }
    
    public static final void removeActiveArmy(final String key) {
        for (int i = 0; i < Game.activeArmySize; ++i) {
            if (Game.activeArmy.get(i).key.equals(key)) {
                Game.activeArmy.remove(i);
                Game.activeArmySize = Game.activeArmy.size();
                return;
            }
        }
    }
    
    public static final boolean checkActiveArmy_Fog() {
        boolean outUpdate = false;
        try {
            for (int i = 0; i < Game.activeArmySize; ++i) {
                if (!getProvince(Game.activeArmy.get(i).iProvinceID).getFogDrawArmy()) {
                    Game.activeArmy.remove(i);
                    Game.activeArmySize = Game.activeArmy.size();
                    outUpdate = true;
                    --i;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return outUpdate;
    }
    
    public static final Civilization getNeutralCivilization() {
        return new Civilization(0, "neu", 0, 251, 251, 221, -1, 0, 0);
    }
    
    protected static final void loadSettings() {
        Game.settingsManager = new SettingsManager();
        loadSettingsDefault();
        try {
            if (FileManager.loadFile("settings/Settings.txt").exists()) {
                final FileHandle fileList = FileManager.loadFile("settings/Settings.txt");
                final Json json = new Json();
                Game.settingsManager = (SettingsManager)json.fromJson((Class)SettingsManager.class, fileList);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        Map.updateDrawProvincesFlags();
    }
    
    public static void saveSettings() {
        final Json json = SaveManager.getJson();
        json.setElementType((Class)LoadManager.ConfigJson.class, "Data", (Class)SettingsManager.class);
        final FileHandle file = FileManager.IS_MAC ? Gdx.files.external("settings/Settings.txt") : Gdx.files.local("settings/Settings.txt");
        file.writeString(json.prettyPrint((Object)Game.settingsManager), false);
    }
    
    public static final void loadSettingsDefault() {
        if (!CFG.isDesktop()) {
            Game.settingsManager.DOUBLE_BORDER = false;
            Game.settingsManager.SETTINGS_PROVINCE_BORDER = 2;
            Game.settingsManager.SETTINGS_PROVINCE_FLAGS = 1;
            Game.settingsManager.SETTINGS_PROVINCE_NAMES = 2;
            Game.settingsManager.CLOUDS = false;
            Game.settingsManager.SHIPS_ON_MAP = 30;
            Game.settingsManager.SETTINGS_CIV_NAMES = 1;
            Game.settingsManager.BORDER_EXTRA_WIDTH = 0.5f;
            Game.settingsManager.FONT_BORDER_SIZE = 84;
        }
    }
    
    protected static final void loadLanguage() {
        Init_SelectLanguage.loadLanguages();
        Game.lang = new LanguageManager(Game.settingsManager.LANGUAGE_TAG);
    }
    
    public static final void updateGame() {
        Game.deltaTime = Math.min(2.0f, Gdx.graphics.getDeltaTime() / 0.016667f);
        ProvinceBorderManager.update();
        ProvinceBorderManager.action.update();
        if (Game.activeArmySize > 0 || Game.hoveredArmy.iProvinceID >= 0) {
            Game.animationHover.update();
        }
        if (Game.iActiveProvince >= 0 && getProvince(Game.iActiveProvince).getDrawProvince()) {
            Game.activeProvince_Animation_Data.update();
        }
        Game.selectedProvinces_Animation_Data.update();
    }
    
    public static final void updateSimpleTask() {
        try {
            for (int i = Game.simpleTasks.size() - 1; i >= 0; --i) {
                try {
                    Game.simpleTasks.remove().update();
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
    }
    
    public static final void addSimpleTask(final SimpleTask nSimpleTask) {
        if (Game.simpleTasks.contains(nSimpleTask)) {
            return;
        }
        Game.simpleTasks.add(nSimpleTask);
    }
    
    public static final void addSimpleTask_First(final SimpleTask nSimpleTask) {
        if (Game.simpleTasks.contains(nSimpleTask)) {
            return;
        }
        Game.simpleTasks.addFirst(nSimpleTask);
    }
    
    public static final void loadCursor(final boolean resetToDefault) {
        if (Game.settingsManager.loadCursor) {
            Game.lCursors.add(Gdx.graphics.newCursor(new Pixmap(FileManager.loadFile("gfx/cursor/standard.png")), 0, 0));
            Game.lCursors.add(Gdx.graphics.newCursor(new Pixmap(FileManager.loadFile("gfx/cursor/drag.png")), 0, 0));
            Game.lCursors.add(Gdx.graphics.newCursor(new Pixmap(FileManager.loadFile("gfx/cursor/build.png")), 0, 0));
            Game.lCursors.add(Gdx.graphics.newCursor(new Pixmap(FileManager.loadFile("gfx/cursor/recruit.png")), 0, 0));
            Game.lCursors.add(Gdx.graphics.newCursor(new Pixmap(FileManager.loadFile("gfx/cursor/recruit2.png")), 0, 0));
            Game.lCursors.add(Gdx.graphics.newCursor(new Pixmap(FileManager.loadFile("gfx/cursor/x.png")), 0, 0));
            Game.lCursors.add(Gdx.graphics.newCursor(new Pixmap(FileManager.loadFile("gfx/cursor/plus.png")), 0, 0));
            Game.lCursors.add(Gdx.graphics.newCursor(new Pixmap(FileManager.loadFile("gfx/cursor/economy.png")), 0, 0));
            Game.lCursors.add(Gdx.graphics.newCursor(new Pixmap(FileManager.loadFile("gfx/cursor/tax.png")), 0, 0));
            Game.lCursors.add(Gdx.graphics.newCursor(new Pixmap(FileManager.loadFile("gfx/cursor/core.png")), 0, 0));
            Game.lCursors.add(Gdx.graphics.newCursor(new Pixmap(FileManager.loadFile("gfx/cursor/religion.png")), 0, 0));
            Game.lCursors.add(Gdx.graphics.newCursor(new Pixmap(FileManager.loadFile("gfx/cursor/populationGrowth.png")), 0, 0));
            Game.lCursors.add(Gdx.graphics.newCursor(new Pixmap(FileManager.loadFile("gfx/cursor/infrastructure.png")), 0, 0));
            Game.lCursors.add(Gdx.graphics.newCursor(new Pixmap(FileManager.loadFile("gfx/cursor/recruit3.png")), 0, 0));
            Game.lCursors.add(Gdx.graphics.newCursor(new Pixmap(FileManager.loadFile("gfx/cursor/economy2.png")), 0, 0));
            Game.lCursors.add(Gdx.graphics.newCursor(new Pixmap(FileManager.loadFile("gfx/cursor/nuke.png")), 0, 0));
            Gdx.graphics.setCursor((Cursor)Game.lCursors.get(0));
        }
        else if (resetToDefault) {
            Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
        }
    }
    
    public static final void setCursorDefault() {
        if (Game.settingsManager.loadCursor && Game.activeCursorID != 0) {
            Gdx.graphics.setCursor((Cursor)Game.lCursors.get(0));
            Game.activeCursorID = 0;
        }
    }
    
    public static final void setCursorDrag() {
        if (Game.settingsManager.loadCursor && Game.activeCursorID != 1) {
            Gdx.graphics.setCursor((Cursor)Game.lCursors.get(1));
            Game.activeCursorID = 1;
        }
    }
    
    public static final void setCursorBuild() {
        if (Game.settingsManager.loadCursor && Game.activeCursorID != 2) {
            Gdx.graphics.setCursor((Cursor)Game.lCursors.get(2));
            Game.activeCursorID = 2;
        }
    }
    
    public static final void setCursorRecruit() {
        if (Game.settingsManager.loadCursor && Game.activeCursorID != 3) {
            if (Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).OVER_IMAGE_ID > 2) {
                Gdx.graphics.setCursor((Cursor)Game.lCursors.get(13));
                Game.activeCursorID = 13;
            }
            else if (Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).OVER_IMAGE_ID > 1) {
                Gdx.graphics.setCursor((Cursor)Game.lCursors.get(4));
                Game.activeCursorID = 4;
            }
            else {
                Gdx.graphics.setCursor((Cursor)Game.lCursors.get(3));
                Game.activeCursorID = 3;
            }
        }
    }
    
    public static final void setCursorX() {
        if (Game.settingsManager.loadCursor && Game.activeCursorID != 5) {
            Gdx.graphics.setCursor((Cursor)Game.lCursors.get(5));
            Game.activeCursorID = 5;
        }
    }
    
    public static final void setCursorPlus() {
        if (Game.settingsManager.loadCursor && Game.activeCursorID != 6) {
            Gdx.graphics.setCursor((Cursor)Game.lCursors.get(6));
            Game.activeCursorID = 6;
        }
    }
    
    public static final void setCursorEconomy() {
        if (Game.settingsManager.loadCursor && Game.activeCursorID != 7) {
            if (Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).OVER_IMAGE_ID > 2) {
                Gdx.graphics.setCursor((Cursor)Game.lCursors.get(14));
                Game.activeCursorID = 14;
            }
            else {
                Gdx.graphics.setCursor((Cursor)Game.lCursors.get(7));
                Game.activeCursorID = 7;
            }
        }
    }
    
    public static final void setCursorTax() {
        if (Game.settingsManager.loadCursor && Game.activeCursorID != 8) {
            Gdx.graphics.setCursor((Cursor)Game.lCursors.get(8));
            Game.activeCursorID = 8;
        }
    }
    
    public static final void setCursorCore() {
        if (Game.settingsManager.loadCursor && Game.activeCursorID != 9) {
            Gdx.graphics.setCursor((Cursor)Game.lCursors.get(9));
            Game.activeCursorID = 9;
        }
    }
    
    public static final void setCursorReligion() {
        if (Game.settingsManager.loadCursor && Game.activeCursorID != 10) {
            Gdx.graphics.setCursor((Cursor)Game.lCursors.get(10));
            Game.activeCursorID = 10;
        }
    }
    
    public static final void setCursorPopulationGrowth() {
        if (Game.settingsManager.loadCursor && Game.activeCursorID != 11) {
            Gdx.graphics.setCursor((Cursor)Game.lCursors.get(11));
            Game.activeCursorID = 11;
        }
    }
    
    public static final void setCursorInfrastructure() {
        if (Game.settingsManager.loadCursor && Game.activeCursorID != 12) {
            Gdx.graphics.setCursor((Cursor)Game.lCursors.get(12));
            Game.activeCursorID = 12;
        }
    }
    
    public static final void setCursorNuke() {
        if (Game.settingsManager.loadCursor && Game.activeCursorID != 15) {
            Gdx.graphics.setCursor((Cursor)Game.lCursors.get(15));
            Game.activeCursorID = 15;
        }
    }
    
    public static final LoadCivilizationData loadCivilization(final String nTag) {
        try {
            final Json json = new Json();
            LoadCivilizationData outCivData;
            if (FileManager.loadFile("game/civilizations/" + nTag + ".json").exists()) {
                final FileHandle file = FileManager.loadFile("game/civilizations/" + nTag + ".json");
                outCivData = (LoadCivilizationData)json.fromJson((Class)LoadCivilizationData.class, file);
            }
            else {
                if (!FileManager.loadFile("game/civilizations/" + Game.ideologiesManager.getRealTag(nTag) + ".json").exists()) {
                    return new LoadCivilizationData();
                }
                final FileHandle file = FileManager.loadFile("game/civilizations/" + Game.ideologiesManager.getRealTag(nTag) + ".json");
                outCivData = (LoadCivilizationData)json.fromJson((Class)LoadCivilizationData.class, file);
            }
            return outCivData;
        }
        catch (final GdxRuntimeException ex) {
            CFG.exceptionStack((Throwable)ex);
            return new LoadCivilizationData();
        }
    }
    
    public static final void initProvinceData() {
        for (int i = 0; i < getProvincesSize(); ++i) {
            getProvince(i).updateBuildingLimit();
            getProvince(i).updateInfrastructureMax();
        }
        for (int i = 0; i < getProvincesSize(); ++i) {
            getProvince(i).updateProvinceValue();
            getProvince(i).updateCityScale();
        }
    }
    
    public static final void removeCivilization(final int nRemoveCivID) {
        if (nRemoveCivID == 0) {
            return;
        }
        getCiv(nRemoveCivID).getFlag().getTexture().dispose();
        if (getCiv(nRemoveCivID).getCapitalProvinceID() >= 0) {
            getProvince(getCiv(nRemoveCivID).getCapitalProvinceID()).setIsCapital(false);
        }
        for (int i = 0; i < getProvincesSize(); ++i) {
            getProvince(i).clearArmiesCivID(nRemoveCivID);
        }
        Game.lCivs.remove(nRemoveCivID);
        Game.iCivsSize = Game.lCivs.size();
        for (int i = 1; i < Game.iCivsSize; ++i) {
            getCiv(i).setCivID_Just(i);
            if (getCiv(i).getPuppetOfCivID() > nRemoveCivID) {
                getCiv(i).setPuppetOfCivID(getCiv(i).getPuppetOfCivID() - 1);
            }
            else if (getCiv(i).getPuppetOfCivID() == nRemoveCivID) {
                getCiv(i).setPuppetOfCivID(getCiv(i).getCivID());
            }
        }
        for (int i = 0; i < Game.iProvincesSize; ++i) {
            if (getProvince(i).getCivID() > nRemoveCivID) {
                getProvince(i).setCivID_Just(getProvince(i).getCivID() - 1);
            }
            else if (getProvince(i).getCivID() == nRemoveCivID) {
                getProvince(i).setCivID_Just(0);
                updateProvinceBorder(i);
            }
            for (int j = getProvince(i).getArmySize() - 1; j >= 0; --j) {
                if (getProvince(i).getArmy(j).civID > nRemoveCivID) {
                    final ArmyDivision army3;
                    final ArmyDivision army2;
                    final ArmyDivision armyDivision3;
                    final ArmyDivision armyDivision4;
                    final ArmyDivision armyDivision2 = armyDivision4 = (armyDivision3 = (army2 = (army3 = getProvince(i).getArmy(j))));
                    --armyDivision4.civID;
                }
                else if (getProvince(i).getArmy(j).civID == nRemoveCivID) {
                    getProvince(i).removeArmy(j);
                }
            }
            getProvince(i).updateCores_AfterRemoveCiv(nRemoveCivID);
        }
        try {
            for (int i = 0; i < Game.alliancesSpecialSize; ++i) {
                Game.alliancesSpecial.get(i).updateAfterRemoveOfCiv(nRemoveCivID);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        DiplomacyManager.updateAfterRemoveCiv(nRemoveCivID);
        Game.updateProvincesInView = true;
        CivilizationRegionsManager.updateRegionsInView = true;
    }
    
    public static final boolean addCivilization(final String nCivTag, final int nProvinceID, final boolean rebuildCores, final boolean addCore, final boolean isInGame, final boolean updateOwnerOfProvince, final boolean loadFlagLater) {
        for (int i = 0; i < getCivsSize(); ++i) {
            if (getCiv(i).getCivTag().equals(nCivTag)) {
                return false;
            }
        }
        final LoadCivilizationData civData = loadCivilization(nCivTag);
        if (loadFlagLater) {
            Game.lCivs.add(new Civilization(getCivsSize(), nCivTag, Game.iCivsSize, civData.iR, civData.iG, civData.iB, nProvinceID, civData.ReligionID, civData.GroupID, true));
            Game.lCivs.get(Game.lCivs.size() - 1).loadScenario_A();
            addSimpleTask(new SimpleTask("LOAD_FLAG" + Game.lCivs.size() + CFG.extraRandomTag(), Game.lCivs.size() - 1) {
                @Override
                public void update() {
                    Game.getCiv(this.id).loadFlag();
                }
            });
        }
        else {
            Game.lCivs.add(new Civilization(getCivsSize(), nCivTag, Game.iCivsSize, civData.iR, civData.iG, civData.iB, nProvinceID, civData.ReligionID, civData.GroupID));
        }
        Game.iCivsSize = Game.lCivs.size();
        Game.lCivs.get(Game.iCivsSize - 1).initTechTree();
        Game.lCivs.get(Game.iCivsSize - 1).initGoodsProduced();
        if (updateOwnerOfProvince) {
            final int tCivID = getProvince(nProvinceID).getCivID();
            getCiv(Game.iCivsSize - 1).addProvince(nProvinceID);
            getProvince(nProvinceID).setCivID_RemoveOldAddNewToCiv(Game.iCivsSize - 1);
            getProvince(nProvinceID).setIsCapital(true);
            getProvince(nProvinceID).setDrawCities(true);
            if (tCivID > 0) {
                getCiv(tCivID).removeProvince(nProvinceID);
                addSimpleTask(new SimpleTask("buildCivilizationsRegion" + tCivID, tCivID) {
                    @Override
                    public void update() {
                        CivilizationRegionsManager.buildCivilizationsRegion(this.id);
                    }
                });
            }
        }
        return true;
    }
    
    public static final void updateProvinceBorder(final int i) {
        for (int j = 0; j < getProvince(i).getNeighboringProvincesSize(); ++j) {
            if (i < getProvince(i).getNeighboringProvinces(j)) {
                getProvince(i).getProvinceBordersLandByLand(getProvince(i).getNeighboringProvinces(j)).setIsCivilizationBorder(getProvince(i).getCivID() != getProvince(getProvince(i).getNeighboringProvinces(j)).getCivID(), i);
            }
            else {
                getProvince(getProvince(i).getNeighboringProvinces(j)).getProvinceBordersLandByLand(i).setIsCivilizationBorder(getProvince(i).getCivID() != getProvince(getProvince(i).getNeighboringProvinces(j)).getCivID(), getProvince(i).getNeighboringProvinces(j));
            }
        }
    }
    
    public static final void buildProvinceIsCapital() {
        for (int i = 1; i < getCivsSize(); ++i) {
            if (getCiv(i).getCapitalProvinceID() >= 0 && getProvince(getCiv(i).getCapitalProvinceID()).getCivID() == i) {
                getProvince(getCiv(i).getCapitalProvinceID()).setIsCapital(true);
            }
        }
    }
    
    public static final void disposeCivilizations() {
        if (Game.lCivs != null) {
            clearActiveArmy();
            for (int i = 0; i < getCivsSize(); ++i) {
                try {
                    getCiv(i).disposeFlag();
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
            for (int i = 0; i < getProvincesSize(); ++i) {
                getProvince(i).clearData();
            }
            Game.lProvincesData.clear();
            Game.lProvincesData2.clear();
            Game.lProvincesData3.clear();
            Game.lProvincesData4.clear();
            Game.lProvincesData5.clear();
            Game.lProvincesData6.clear();
            Game.lProvincesData7.clear();
            Game.lProvincesData8.clear();
            Game.lProvincesData9.clear();
            Game.lProvincesData10.clear();
            Game.lProvincesPopulation.clear();
            for (int i = 0; i < getProvincesSize(); ++i) {
                Game.lProvincesData.add(new ProvinceData());
                Game.lProvincesData2.add(new ProvinceData2());
                Game.lProvincesData3.add(new ProvinceData3());
                Game.lProvincesData4.add(new ProvinceData4());
                Game.lProvincesData5.add(new ProvinceData5());
                Game.lProvincesData6.add(new ProvinceData6());
                Game.lProvincesData7.add(new ProvinceData7());
                Game.lProvincesData8.add(new ProvinceData8());
                Game.lProvincesData9.add(new ProvinceData9());
                Game.lProvincesData10.add(new ProvinceData10());
                Game.lProvincesPopulation.add(new ProvinceData_Population());
            }
            Game.lCivs.clear();
            Game.iCivsSize = 0;
            WondersManager.initProvinceWonders();
        }
    }
    
    public static final void disposeCutProvinces() {
        Game.cutProvinces.clear();
        Game.cutProvinces = null;
    }
    
    public static final void loadProvincesBG() {
        for (int i = 0; i < Game.iProvincesSize; ++i) {
            getProvince(i).loadProvinceBG();
        }
        disposeCutProvinces();
    }
    
    public static final void loadProvinceBG(final int nProvinceID) {
        getProvince(nProvinceID).loadProvinceBG();
    }
    
    public static final void buildWastelandLevels() {
        try {
            ArrayList<Integer> tWasteland = new ArrayList<Integer>();
            for (int i = 0; i < getProvincesSize(); ++i) {
                if (getProvince(i).getWasteland() >= 0 && !getProvince(i).getSeaProvince()) {
                    tWasteland.add(i);
                    getProvince(i).setWasteland(0);
                }
            }
            buildWastelandLevels(tWasteland, 0, tWasteland.size());
            tWasteland.clear();
            tWasteland = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void buildWastelandLevels(final List<Integer> tWasteland, final int nLevel, int nWastelandSize) {
        try {
            boolean rec = false;
            for (int i = 0; i < nWastelandSize; ++i) {
                if (getProvince(tWasteland.get(i)).getWasteland() != nLevel || getProvince(tWasteland.get(i)).getNeighboringSeaProvincesSize() > 0) {
                    tWasteland.remove(i);
                    --i;
                    nWastelandSize = tWasteland.size();
                }
                else {
                    boolean incLevel = true;
                    for (int j = 0; j < getProvince(tWasteland.get(i)).getNeighboringProvincesSize(); ++j) {
                        if (getProvince(getProvince(tWasteland.get(i)).getNeighboringProvinces(j)).getWasteland() < nLevel) {
                            incLevel = false;
                            break;
                        }
                    }
                    if (incLevel) {
                        getProvince(tWasteland.get(i)).setWasteland(nLevel + 1);
                        rec = true;
                    }
                    else {
                        tWasteland.remove(i);
                        --i;
                        nWastelandSize = tWasteland.size();
                    }
                }
            }
            if (rec && nLevel < Game.maxWastelandLvl) {
                buildWastelandLevels(tWasteland, nLevel + 1, nWastelandSize);
            }
        }
        catch (final StackOverflowError ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final boolean checkClosedSea(final int nProvinceID) {
        for (int i = 0; i < getProvince(nProvinceID).getNeighboringSeaProvincesSize(); ++i) {
            if (getProvince(getProvince(nProvinceID).getNeighboringSeaProvinces(i)).getLevelOfPort() == -2) {
                return true;
            }
        }
        return false;
    }
    
    public static final void updateProvinceBG_ActiveHoveredProvince() {
        try {
            if (!GameValues.value.LOAD_SEA_PROVINCES && GameValues.value.LOAD_HOVERED_SEA_PROVINCE && Game.iHoveredProvinceID >= 0 && getProvince(Game.iHoveredProvinceID).getSeaProvince() && Game.hoveredProvinceBG_LoadedID != Game.iHoveredProvinceID) {
                if (Game.hoveredProvinceBG != null) {
                    Game.hoveredProvinceBG.dispose();
                    Game.hoveredProvinceBG = null;
                    Game.hoveredProvinceBG_LoadedID = -1;
                }
                if (FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "data/scales/" + ((getProvince(Game.iHoveredProvinceID).getLevelOfPort() == -4) ? 1 : ((int)(Game.mapBG.iMapScale / Game.mapBG.iMapExtraScale))) + "/" + Game.iHoveredProvinceID).exists()) {
                    Pixmap pixmap = PixmapIO.readCIM(FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "data/scales/" + ((getProvince(Game.iHoveredProvinceID).getLevelOfPort() == -4) ? 1 : ((int)(Game.mapBG.iMapScale / Game.mapBG.iMapExtraScale))) + "/" + Game.iHoveredProvinceID));
                    final Pixmap convertedPixmap = new Pixmap(pixmap.getWidth(), pixmap.getHeight(), Pixmap.Format.Alpha);
                    convertedPixmap.drawPixmap(pixmap, 0, 0);
                    Game.hoveredProvinceBG = new Image(new Texture(convertedPixmap), Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
                    pixmap.dispose();
                    pixmap = null;
                    convertedPixmap.dispose();
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void drawActiveProvince(final SpriteBatch oSB) {
        updateProvinceBG_ActiveHoveredProvince();
        oSB.setShader(Renderer.shaderDefaultProvince);
        try {
            if (Game.mapScale.getCurrentScale() >= Game.DRAW_INNER_BORDERS) {
                if (Game.iActiveProvince >= 0 && getProvince(Game.iActiveProvince).getDrawProvince()) {
                    try {
                        if (Game.chooseProvinceMode) {
                            oSB.setColor(new Color(CFG.getColorStep(255, 55, Game.activeProvince_Animation_Data.getColorStepID(), 30), CFG.getColorStep(255, 55, Game.activeProvince_Animation_Data.getColorStepID(), 30), CFG.getColorStep(255, 55, Game.activeProvince_Animation_Data.getColorStepID(), 30), (getProvince(Game.iActiveProvince).getSeaProvince() ? ((Game.activeProvince_Animation_Data.getAlpha() + 35.0f) / 255.0f / 3.0f) : ((Game.activeProvince_Animation_Data.getAlpha() + 35.0f) / 255.0f * (Game.fDashedLine_Percentage_HighlitedProvinceBorder / 100.0f))) / ((Game.mapScale.getCurrentScale() > 1.0f) ? Game.mapScale.getCurrentScale() : 1.0f)));
                            getProvince(Game.iActiveProvince).drawProvince_ActiveProvince(oSB);
                        }
                        else {
                            if (getProvince(Game.iActiveProvince).getSeaProvince()) {
                                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, Game.activeProvince_Animation_Data.getAlpha() / 2.0f / 255.0f));
                            }
                            else {
                                oSB.setColor(new Color(0.96f, 0.96f, 0.94f, Game.activeProvince_Animation_Data.getAlpha() / 255.0f / ((Game.mapScale.getCurrentScale() > 1.0f) ? Game.mapScale.getCurrentScale() : 1.0f)));
                            }
                            getProvince(Game.iActiveProvince).drawProvince_ActiveProvince(oSB);
                        }
                    }
                    catch (final Exception ex2) {}
                }
                try {
                    if (Game.iHoveredProvinceID >= 0 && getProvince(Game.iHoveredProvinceID).getDrawProvince()) {
                        if (!GameValues.value.LOAD_SEA_PROVINCES && getProvince(Game.iHoveredProvinceID).getSeaProvince()) {
                            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, (getProvince(Game.iHoveredProvinceID).getSeaProvince() ? 0.02f : 0.05f) / ((Game.mapScale.getCurrentScale() > 1.0f) ? Game.mapScale.getCurrentScale() : 1.0f)));
                            getProvince(Game.iHoveredProvinceID).drawProvince_ActiveProvince(oSB, Game.hoveredProvinceBG);
                        }
                        else if (Game.iActiveProvince != Game.iHoveredProvinceID) {
                            try {
                                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, (getProvince(Game.iHoveredProvinceID).getSeaProvince() ? 0.02f : 0.05f) / ((Game.mapScale.getCurrentScale() > 1.0f) ? Game.mapScale.getCurrentScale() : 1.0f)));
                                getProvince(Game.iHoveredProvinceID).drawProvince_ActiveProvince(oSB);
                            }
                            catch (final Exception ex3) {}
                        }
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
                oSB.setColor(Color.WHITE);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        oSB.setShader(Renderer.shaderDefault);
    }
    
    protected static final void updateHoveredProvince_HoverInMapMode(final int nPosX, final int nPosY) {
        if (Game.menuManager.dialogMenu.getVisible()) {
            Game.provinceHover_Informations = null;
            return;
        }
        if (Game.hoverManager.hoverActiveMenuTitleID >= 0) {
            return;
        }
        try {
            if (Game.iHoveredProvinceID >= 0) {
                if (setProvinceID_IsMouseOverAProvinceID((int)(nPosX / Game.mapScale.getCurrentScale()), (int)(nPosY / Game.mapScale.getCurrentScale()), Game.iHoveredProvinceID)) {
                    return;
                }
                setHoveredProvinceID(setProvinceID_HoverAProvince((int)(nPosX / Game.mapScale.getCurrentScale()), (int)(nPosY / Game.mapScale.getCurrentScale())));
                if (Game.iHoveredProvinceID < 0) {
                    Game.provinceHover_Informations = null;
                }
            }
            else {
                setHoveredProvinceID(setProvinceID_HoverAProvince((int)(nPosX / Game.mapScale.getCurrentScale()), (int)(nPosY / Game.mapScale.getCurrentScale())));
                if (Game.iHoveredProvinceID >= 0) {
                    MenuElement_Hover.resetAnimation();
                }
                else {
                    Game.provinceHover_Informations = null;
                }
            }
            Game.provinceHover_Informations = getProvince(Game.iHoveredProvinceID).drawDetails.buildHover(Game.iHoveredProvinceID);
        }
        catch (final NullPointerException ex) {
            Game.provinceHover_Informations = null;
            Game.iHoveredProvinceID = -1;
        }
        catch (final IndexOutOfBoundsException ex2) {
            Game.provinceHover_Informations = null;
            Game.iHoveredProvinceID = -1;
        }
    }
    
    protected static final void updateHoveredProvince_Hover(final int nPosX, final int nPosY) {
        try {
            if (Game.menuManager.dialogMenu.getVisible()) {
                Game.provinceHover_Informations = null;
                return;
            }
            if (Game.hoverManager.hoverActiveMenuTitleID >= 0) {
                return;
            }
            try {
                if (Game.iHoveredProvinceID >= 0) {
                    if (!getProvince(Game.iHoveredProvinceID).getSeaProvince() && setProvinceID_IsMouseOverAProvinceID((int)(nPosX / Game.mapScale.getCurrentScale()), (int)(nPosY / Game.mapScale.getCurrentScale()), Game.iHoveredProvinceID)) {
                        return;
                    }
                    setHoveredProvinceID(setProvinceID_HoverAProvince((int)(nPosX / Game.mapScale.getCurrentScale()), (int)(nPosY / Game.mapScale.getCurrentScale())));
                    if (Game.iHoveredProvinceID < 0) {
                        Game.provinceHover_Informations = null;
                    }
                }
                else {
                    setHoveredProvinceID(setProvinceID_HoverAProvince((int)(nPosX / Game.mapScale.getCurrentScale()), (int)(nPosY / Game.mapScale.getCurrentScale())));
                    if (Game.iHoveredProvinceID >= 0) {
                        MenuElement_Hover.resetAnimation();
                    }
                    else {
                        Game.provinceHover_Informations = null;
                    }
                }
                ProvinceHover.provinceHoverBuild.build();
            }
            catch (final Exception ex) {
                Game.provinceHover_Informations = null;
                Game.iHoveredProvinceID = -1;
                CFG.exceptionStack(ex);
            }
        }
        catch (final Exception ex2) {}
    }
    
    public static final void updateHighlitghtProvinceBorder(final SpriteBatch oSB) {
        if (!Game.highlightedProvinceBorder_Update) {
            return;
        }
        if (Game.highlightedProvinceBorder_BackAnimation) {
            if ((Game.fDashedLine_Percentage_HighlitedProvinceBorder -= (CFG.currentTimeMillis - Game.lDashedLineTime_Percentage_HighlitedProvinceBorder) / 350.0f * 100.0f) <= 0.0f) {
                Game.highlightedProvinceBorder_Update = false;
                return;
            }
            Game.lDashedLineTime_Percentage_HighlitedProvinceBorder = CFG.currentTimeMillis;
        }
        else {
            if ((Game.fDashedLine_Percentage_HighlitedProvinceBorder += (CFG.currentTimeMillis - Game.lDashedLineTime_Percentage_HighlitedProvinceBorder) / 750.0f * 95.0f) > 100.0f) {
                Game.fDashedLine_Percentage_HighlitedProvinceBorder = 100.0f;
                Game.highlightedProvinceBorder_Update = false;
                return;
            }
            Game.lDashedLineTime_Percentage_HighlitedProvinceBorder = CFG.currentTimeMillis;
        }
    }
    
    public static final int setProvinceID_HoverAProvince(final int nPosX, final int nPosY) {
        int tPosX = 0;
        for (int i = 0; i < Game.iProvincesSize; ++i) {
            tPosX = (int)Math.abs(checkPosOfClickX((float)(Game.mapCoords.getPosX() - nPosX)));
            if (getProvince(i).getMinX() <= tPosX && getProvince(i).getMaxX() >= tPosX && getProvince(i).getMinY() <= -Game.mapCoords.getPosY() + nPosY && getProvince(i).getMaxY() >= -Game.mapCoords.getPosY() + nPosY && pathContains(i, tPosX, -Game.mapCoords.getPosY() + nPosY)) {
                return i;
            }
        }
        if (Math.abs(checkPosOfClickX((float)(Game.mapCoords.getPosX() - nPosX))) + -Game.MAX_BELOW_ZERO_POINT_X * Game.mapBG.iMapScale / Game.mapScale.getCurrentScale() > Game.mapBG.getWidth()) {
            tPosX = 0;
            for (int i = 0; i < Game.iProvincesSize; ++i) {
                if (getProvince(i).getBelowZero()) {
                    tPosX = (int)Math.abs(checkPosOfClickX((float)(Game.mapCoords.getPosX() - nPosX)));
                    if (getProvince(i).getMinX() <= tPosX - Game.mapBG.getWidth() && getProvince(i).getMaxX() >= tPosX - Game.mapBG.getWidth() && getProvince(i).getMinY() <= -Game.mapCoords.getPosY() + nPosY && getProvince(i).getMaxY() >= -Game.mapCoords.getPosY() + nPosY && pathContains(i, tPosX - Game.mapBG.getWidth(), -Game.mapCoords.getPosY() + nPosY)) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
    
    protected static final boolean setProvinceID_IsMouseOverAProvinceID(final int nPosX, final int nPosY, final int nProvinceID) {
        int tPosX = (int)Math.abs(checkPosOfClickX((float)(Game.mapCoords.getPosX() - nPosX)));
        if (getProvince(nProvinceID).getMinX() <= tPosX && getProvince(nProvinceID).getMaxX() >= tPosX && getProvince(nProvinceID).getMinY() <= -Game.mapCoords.getPosY() + nPosY && getProvince(nProvinceID).getMaxY() >= -Game.mapCoords.getPosY() + nPosY && pathContains(nProvinceID, tPosX, -Game.mapCoords.getPosY() + nPosY)) {
            return true;
        }
        if (Math.abs(checkPosOfClickX((float)(Game.mapCoords.getPosX() - nPosX))) + -Game.MAX_BELOW_ZERO_POINT_X * Game.mapBG.iMapScale / Game.mapScale.getCurrentScale() > Game.mapBG.getWidth() && getProvince(nProvinceID).getBelowZero()) {
            tPosX = (int)Math.abs(checkPosOfClickX((float)(Game.mapCoords.getPosX() - nPosX)));
            if (getProvince(nProvinceID).getMinX() <= tPosX - Game.mapBG.getWidth() && getProvince(nProvinceID).getMaxX() >= tPosX - Game.mapBG.getWidth() && getProvince(nProvinceID).getMinY() <= -Game.mapCoords.getPosY() + nPosY && getProvince(nProvinceID).getMaxY() >= -Game.mapCoords.getPosY() + nPosY && pathContains(nProvinceID, tPosX - Game.mapBG.getWidth(), -Game.mapCoords.getPosY() + nPosY)) {
                return true;
            }
        }
        return false;
    }
    
    public static final int setProvinceID_Point(final int nPosX, final int nPosY) {
        for (int i = 0; i < Game.iProvincesSize; ++i) {
            if (getProvince(i).getMinX() <= nPosX && getProvince(i).getMaxX() >= nPosX && getProvince(i).getMinY() <= nPosY && getProvince(i).getMaxY() >= nPosY && pathContains(i, nPosX, nPosY)) {
                return i;
            }
        }
        if (Math.abs(checkPosOfClickX((float)nPosX)) + -Game.MAX_BELOW_ZERO_POINT_X * Game.mapBG.iMapScale / Game.mapScale.getCurrentScale() > Game.mapBG.getWidth()) {
            int tPosX = 0;
            for (int j = 0; j < Game.iProvincesSize; ++j) {
                if (getProvince(j).getBelowZero()) {
                    tPosX = (int)Math.abs(checkPosOfClickX((float)nPosX));
                    if (getProvince(j).getMinX() <= tPosX - Game.mapBG.getWidth() && getProvince(j).getMaxX() >= tPosX - Game.mapBG.getWidth() && getProvince(j).getMinY() <= nPosY && getProvince(j).getMaxY() >= nPosY && pathContains(j, tPosX - Game.mapBG.getWidth(), nPosY)) {
                        return j;
                    }
                }
            }
        }
        return -1;
    }
    
    public static final void updateProvincesInView() {
        if (!Game.updateProvincesInView) {
            return;
        }
        Game.updateProvincesInView = false;
        for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            getProvince(Game.lProvincesInView.get(i)).setDrawProvince(false);
        }
        for (int i = 0; i < Game.NUM_OF_SEA_PROVINCES_IN_VIEW; ++i) {
            getProvince(Game.lSeaProvincesInView.get(i)).setDrawProvince(false);
        }
        for (int i = 0; i < Game.NUM_OF_WASTELAND_PROVINCES_IN_VIEW; ++i) {
            getProvince(Game.lWastelandProvincesInView.get(i)).setDrawProvince(false);
        }
        for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
            getProvince(Game.lExtraProvincesInView.get(i)).setDrawProvince(false);
        }
        Game.NUM_OF_PROVINCES_IN_VIEW = 0;
        Game.NUM_OF_SEA_PROVINCES_IN_VIEW = 0;
        Game.NUM_OF_WASTELAND_PROVINCES_IN_VIEW = 0;
        Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW = 0;
        Game.lProvincesInView.clear();
        Game.lSeaProvincesInView.clear();
        Game.lWastelandProvincesInView.clear();
        Game.lExtraProvincesInView.clear();
        for (int i = 0; i < Game.regions.iRegionsSize; ++i) {
            final Region region = Game.regions.lRegions.get(i);
            if (inViewY(region.getMinY(), region.getMaxY())) {
                if (inViewX(region.getMinX(), region.getMaxX()) || inViewX2(region.getMinX(), region.getMaxX())) {
                    updateDrawRegionProvinces(i);
                }
                else if (region.getBelowZero() && inViewXBelowZero(region.getMinX(), region.getMaxX())) {
                    updateDrawRegionProvinces(i);
                }
            }
        }
        Game.NUM_OF_PROVINCES_IN_VIEW = Game.lProvincesInView.size();
        Game.NUM_OF_SEA_PROVINCES_IN_VIEW = Game.lSeaProvincesInView.size();
        Game.NUM_OF_WASTELAND_PROVINCES_IN_VIEW = Game.lWastelandProvincesInView.size();
        Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW = Game.lExtraProvincesInView.size();
    }
    
    private static final void updateDrawRegionProvinces(final int iID) {
        final Region region = Game.regions.lRegions.get(iID);
        if (inViewY_WholeRegion(region.getMinY(), region.getMaxY())) {
            if (inViewX_WholeRegion(region.getMinX(), region.getMaxX())) {
                for (int i = 0; i < region.getProvincesSize(); ++i) {
                    getProvince(region.getProvince(i)).setTranslateProvincePosX(Game.mapCoords.getSecondSideOfMap_MoveX() + Game.mapCoords.getPosX());
                    updateProvinceInViewLists(region.getProvince(i));
                }
                return;
            }
            if (inViewX_WholeRegion2(region.getMinX(), region.getMaxX())) {
                for (int i = 0; i < region.getProvincesSize(); ++i) {
                    getProvince(region.getProvince(i)).setTranslateProvincePosX(Game.mapCoords.getPosX());
                    updateProvinceInViewLists(region.getProvince(i));
                }
                return;
            }
        }
        for (int i = 0; i < region.getProvincesSize(); ++i) {
            updateDrawProvince2(region.getProvince(i));
        }
    }
    
    protected static final void updateDrawProvince2(final int nProvinceID) {
        if (inViewY(nProvinceID)) {
            if (inViewX(nProvinceID)) {
                getProvince(nProvinceID).setTranslateProvincePosX(Game.mapCoords.getSecondSideOfMap_MoveX() + Game.mapCoords.getPosX());
                updateProvinceInViewLists(nProvinceID);
                return;
            }
            if (inViewX2(nProvinceID)) {
                getProvince(nProvinceID).setTranslateProvincePosX(Game.mapCoords.getPosX());
                updateProvinceInViewLists(nProvinceID);
                return;
            }
            if (getProvince(nProvinceID).getBelowZero() && inViewXBelowZero(nProvinceID)) {
                getProvince(nProvinceID).setTranslateProvincePosX(Game.mapBG.getWidth() + Game.mapCoords.getPosX());
                updateProvinceInViewLists(nProvinceID);
                return;
            }
        }
        getProvince(nProvinceID).setDrawProvince(false);
    }
    
    protected static final void updateProvinceInViewLists(final int nProvinceID) {
        getProvince(nProvinceID).setDrawProvince(true);
        if (getProvince(nProvinceID).getWasteland() >= 0) {
            Game.lWastelandProvincesInView.add(nProvinceID);
        }
        else if (getProvince(nProvinceID).getSeaProvince()) {
            Game.lSeaProvincesInView.add(nProvinceID);
        }
        else {
            Game.lProvincesInView.add(nProvinceID);
        }
    }
    
    public static final void updateDrawProvince_CheckExtra(final int nProvinceID) {
        if (Game.mapCoords.getSecondSideOfMap() && getProvince(nProvinceID).getMinX() < Game.inViewX_CordsX && getProvince(nProvinceID).getMaxX() > Game.inViewX_CordsX) {
            Game.lExtraProvincesInView.add(nProvinceID);
        }
    }
    
    public final void clearProvincesInView() {
        for (int i = 0; i < getProvincesSize(); ++i) {
            getProvince(i).setDrawProvince(false);
        }
        Game.NUM_OF_PROVINCES_IN_VIEW = 0;
        Game.NUM_OF_SEA_PROVINCES_IN_VIEW = 0;
        Game.NUM_OF_WASTELAND_PROVINCES_IN_VIEW = 0;
        Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW = 0;
        Game.lProvincesInView.clear();
        Game.lSeaProvincesInView.clear();
        Game.lWastelandProvincesInView.clear();
        Game.lExtraProvincesInView.clear();
    }
    
    public static final int getProvinceInViewID(final int iID) {
        try {
            return Game.lProvincesInView.get(iID);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            return 0;
        }
    }
    
    public static final int getExtraProvinceInViewID(final int iID) {
        try {
            return Game.lExtraProvincesInView.get(iID);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            return 0;
        }
    }
    
    public static final int getSeaProvinceInViewID(final int iID) {
        try {
            return Game.lSeaProvincesInView.get(iID);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            return 0;
        }
    }
    
    public static final int getWastelandProvinceInViewID(final int iID) {
        try {
            return Game.lWastelandProvincesInView.get(iID);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            return 0;
        }
    }
    
    public static final void setUpdateProvincesInView(final boolean nUpdateProvincesInView) {
        Game.updateProvincesInView = nUpdateProvincesInView;
    }
    
    public static final void updateInView_CordsXY() {
        Game.inViewY_CordsY = -Game.mapCoords.getPosY();
        Game.inViewY_CordsY_Height = (int)(-Game.mapCoords.getPosY() + CFG.GAME_HEIGHT / Game.mapScale.getCurrentScale());
        Game.inViewX_CordsX = -Game.mapCoords.getPosX();
        Game.inViewX_CordsX_Width = (int)(-Game.mapCoords.getPosX() + CFG.GAME_WIDTH / Game.mapScale.getCurrentScale());
    }
    
    protected static final boolean inViewY(final int nProvinceID) {
        return Game.inViewY_CordsY_Height >= getProvince(nProvinceID).getMinY() && Game.inViewY_CordsY <= getProvince(nProvinceID).getMaxY();
    }
    
    protected static final boolean inViewX(final int nProvinceID) {
        return Game.inViewX_CordsX_Width >= getProvince(nProvinceID).getMinX() + Game.mapCoords.getSecondSideOfMap_MoveX() && Game.inViewX_CordsX <= getProvince(nProvinceID).getMaxX() + Game.mapCoords.getSecondSideOfMap_MoveX();
    }
    
    protected static final boolean inViewX2(final int nProvinceID) {
        return Game.inViewX_CordsX_Width >= getProvince(nProvinceID).getMinX() && Game.inViewX_CordsX <= getProvince(nProvinceID).getMaxX();
    }
    
    protected static final boolean inViewXBelowZero(final int nProvinceID) {
        return Game.inViewX_CordsX_Width >= getProvince(nProvinceID).getMinX() + Game.mapBG.getWidth() && Game.inViewX_CordsX <= getProvince(nProvinceID).getMaxX() + Game.mapBG.getWidth();
    }
    
    public static final boolean inViewY(final int nMinY, final int nMaxY) {
        return Game.inViewY_CordsY_Height >= nMinY && Game.inViewY_CordsY <= nMaxY;
    }
    
    public static final boolean inViewX(final int nMinX, final int nMaxX) {
        return Game.inViewX_CordsX_Width >= nMinX + Game.mapCoords.getSecondSideOfMap_MoveX() && Game.inViewX_CordsX <= nMaxX + Game.mapCoords.getSecondSideOfMap_MoveX();
    }
    
    public static final boolean inViewX2(final int nMinX, final int nMaxX) {
        return Game.inViewX_CordsX_Width >= nMinX && Game.inViewX_CordsX <= nMaxX;
    }
    
    protected static final boolean inViewXBelowZero(final int nMinX, final int nMaxX) {
        return Game.inViewX_CordsX_Width >= nMinX + Game.mapBG.getWidth() && Game.inViewX_CordsX <= nMaxX + Game.mapBG.getWidth();
    }
    
    protected static final boolean inViewY_WholeRegion(final int nMinY, final int nMaxY) {
        return nMinY >= Game.inViewY_CordsY && nMaxY <= Game.inViewY_CordsY_Height;
    }
    
    protected static final boolean inViewX_WholeRegion(final int nMinX, final int nMaxX) {
        return nMinX + Game.mapCoords.getSecondSideOfMap_MoveX() >= Game.inViewX_CordsX && nMaxX + Game.mapCoords.getSecondSideOfMap_MoveX() <= Game.inViewX_CordsX_Width;
    }
    
    protected static final boolean inViewX_WholeRegion2(final int nMinX, final int nMaxX) {
        return nMinX >= Game.inViewX_CordsX && nMaxX <= Game.inViewX_CordsX_Width;
    }
    
    protected static final String getFogOfWarName(final int i) {
        switch (i) {
            case 0: {
                return Game.lang.get("Off");
            }
            case 2: {
                return Game.lang.get("Discovery");
            }
            default: {
                return Game.lang.get("Classic");
            }
        }
    }
    
    public static final boolean pathContains(final int nProvinceID, final int nPosX, final int nPosY) {
        boolean output = false;
        int i = 0;
        final int iSize = getProvince(nProvinceID).getPointsSize();
        int j = iSize - 1;
        while (i < iSize) {
            if (getProvince(nProvinceID).getPointsY(i) > nPosY != getProvince(nProvinceID).getPointsY(j) > nPosY && nPosX < (getProvince(nProvinceID).getPointsX(j) - getProvince(nProvinceID).getPointsX(i)) * (nPosY - getProvince(nProvinceID).getPointsY(i)) / (getProvince(nProvinceID).getPointsY(j) - getProvince(nProvinceID).getPointsY(i)) + getProvince(nProvinceID).getPointsX(i)) {
                output = !output;
            }
            j = i++;
        }
        return output;
    }
    
    public static final boolean pathContains_Cut(final int nProvinceID, final int nPosX, final int nPosY) {
        boolean output = false;
        if (nPosX >= Game.cutProvinces.get(nProvinceID).iMinX * Game.mapBG.iMapScale && nPosX <= Game.cutProvinces.get(nProvinceID).iMaxX * Game.mapBG.iMapScale && nPosY >= Game.cutProvinces.get(nProvinceID).iMinY * Game.mapBG.iMapScale && nPosY <= Game.cutProvinces.get(nProvinceID).iMaxY * Game.mapBG.iMapScale) {
            int i = 0;
            final int iSize = Game.cutProvinces.get(nProvinceID).getPointsSize();
            int j = iSize - 1;
            while (i < iSize) {
                if (Game.cutProvinces.get(nProvinceID).getPointsY(i) > nPosY != Game.cutProvinces.get(nProvinceID).getPointsY(j) > nPosY && nPosX < (Game.cutProvinces.get(nProvinceID).getPointsX(j) - Game.cutProvinces.get(nProvinceID).getPointsX(i)) * (nPosY - Game.cutProvinces.get(nProvinceID).getPointsY(i)) / (Game.cutProvinces.get(nProvinceID).getPointsY(j) - Game.cutProvinces.get(nProvinceID).getPointsY(i)) + Game.cutProvinces.get(nProvinceID).getPointsX(i)) {
                    output = !output;
                }
                j = i++;
            }
        }
        return output;
    }
    
    public static final float checkPosOfClickX(float nPosX) {
        if (-nPosX <= Game.mapBG.getWidth()) {
            if (nPosX > 0.0f) {
                while (nPosX > 0.0f) {
                    nPosX -= Game.mapBG.getWidth();
                }
            }
        }
        else {
            while (-nPosX > Game.mapBG.getWidth()) {
                nPosX += Game.mapBG.getWidth();
            }
        }
        return nPosX;
    }
    
    public static final void centerToRandomMapPosition() {
        Game.mapScroll.stopScrollingTheMap();
        Game.mapScale.setCurrentScale(MapScale.STANDARD_SCALE);
        final Point_XY tempPointToCenterTheMap = CFG.getRandomPointToCenterTheMap();
        Game.mapCoords.setNewPosX(-(tempPointToCenterTheMap.getPosX() * Game.mapBG.iMapScale - CFG.GAME_WIDTH / 2));
        Game.mapCoords.setNewPosY(-(tempPointToCenterTheMap.getPosY() * Game.mapBG.iMapScale - CFG.GAME_HEIGHT / 2));
    }
    
    public static final void updateCivilizationIdeology(final int nCivID, final String nCivTag) {
        try {
            for (int i = 0; i < getCivsSize(); ++i) {
                if (getCiv(i).getCivTag().equals(nCivTag)) {
                    return;
                }
            }
            final LoadCivilizationData civData = loadCivilization(nCivTag);
            getCiv(nCivID).updateCivilizationTAG(nCivTag, civData.iR, civData.iG, civData.iB);
            Renderer.addSimpleTaskCivsNames(new SimpleTask("" + nCivID, nCivID) {
                @Override
                public void update() {
                    CivilizationRegionsManager.buildCivilizationsRegions_TextOver(this.id);
                }
            });
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void updateCivilizationIdeology_InGame(final int nCivID, final String nCivTag) {
        try {
            final LoadCivilizationData civData = loadCivilization(nCivTag);
            getCiv(nCivID).updateCivilizationTAG(nCivTag, civData.iR, civData.iG, civData.iB);
            Renderer.addSimpleTaskCivsNames(new SimpleTask("" + nCivID, nCivID) {
                @Override
                public void update() {
                    CivilizationRegionsManager.buildCivilizationsRegions_TextOver(this.id);
                }
            });
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final Province getProvince(final int ID) {
        return Game.lProvinces.get(ID);
    }
    
    public static final ProvinceData getProvinceData(final int ID) {
        return Game.lProvincesData.get(ID);
    }
    
    public static final ProvinceData2 getProvinceData2(final int ID) {
        return Game.lProvincesData2.get(ID);
    }
    
    public static final ProvinceData3 getProvinceData3(final int ID) {
        return Game.lProvincesData3.get(ID);
    }
    
    public static final ProvinceData4 getProvinceData4(final int ID) {
        return Game.lProvincesData4.get(ID);
    }
    
    public static final ProvinceData5 getProvinceData5(final int ID) {
        return Game.lProvincesData5.get(ID);
    }
    
    public static final ProvinceData6 getProvinceData6(final int ID) {
        return Game.lProvincesData6.get(ID);
    }
    
    public static final ProvinceData7 getProvinceData7(final int ID) {
        return Game.lProvincesData7.get(ID);
    }
    
    public static final ProvinceData8 getProvinceData8(final int ID) {
        return Game.lProvincesData8.get(ID);
    }
    
    public static final ProvinceData9 getProvinceData9(final int ID) {
        return Game.lProvincesData9.get(ID);
    }
    
    public static final ProvinceData10 getProvinceData10(final int ID) {
        return Game.lProvincesData10.get(ID);
    }
    
    public static final ProvinceData_Population getProvincePopulation(final int ID) {
        return Game.lProvincesPopulation.get(ID);
    }
    
    public static final int getProvincesSize() {
        return Game.iProvincesSize;
    }
    
    public static final Civilization getCiv(final int i) {
        try {
            return Game.lCivs.get(i);
        }
        catch (final Exception ex) {
            return Game.lCivs.get(0);
        }
    }
    
    public static final int getCivID(final String tag) {
        try {
            for (int i = 1; i < getCivsSize(); ++i) {
                if (getCiv(i).getCivTag().equals(tag)) {
                    return i;
                }
            }
            for (int i = 1; i < getCivsSize(); ++i) {
                if (getCiv(i).realTag.equals(tag)) {
                    return i;
                }
            }
        }
        catch (final Exception ex) {}
        return -1;
    }
    
    public static final int getCivsSize() {
        return Game.iCivsSize;
    }
    
    public final ProvinceAnimation getProvinceAnimation_Active_Data() {
        return Game.activeProvince_Animation_Data;
    }
    
    public static final int getRegionID(final int nProvinceID) {
        for (int i = 0; i < Game.regions.iRegionsSize; ++i) {
            for (int j = 0; j < Game.regions.lRegions.get(i).getProvincesSize(); ++j) {
                if (Game.regions.lRegions.get(i).getProvince(j) == nProvinceID) {
                    return i;
                }
            }
        }
        return 0;
    }
    
    public static final int countContinentProvinces(final int nContinentID) {
        int out = 0;
        for (int i = 0; i < getProvincesSize(); ++i) {
            if (getProvince(i).getContinent() == nContinentID) {
                ++out;
            }
        }
        return out;
    }
    
    public static int countPlayableProvince() {
        int out = 0;
        for (int i = 0; i < getProvincesSize(); ++i) {
            if (!getProvince(i).getSeaProvince() && getProvince(i).getWasteland() < 0) {
                ++out;
            }
        }
        return out;
    }
    
    public static float getLoanValue(final int iCivID) {
        float out = GameValues.loan.LOAN_VALUE_MIN;
        for (int i = 0; i < getCiv(iCivID).getNumOfProvinces(); ++i) {
            out += getProvince(getCiv(iCivID).getProvinceID(i)).getEconomyWithBonuses();
        }
        return out * GameValues.loan.LOAN_VALUE_BY_ECONOMY;
    }
    
    public static float getLoanInterestValue(final int iCivID) {
        return getLoanValue(iCivID) * (getLoanInterest(iCivID) / 100.0f) * getLoanExpires_Years();
    }
    
    public static float getLoanInterest(final int iCivID) {
        return GameValues.loan.LOAN_DEFAULT_INTEREST * (1.0f + (getCiv(iCivID).civBonuses.LoanInterest + GameValues.civRank.CIV_RANK_LOAN_INTEREST[getCiv(iCivID).iCivRankID]) / 100.0f);
    }
    
    public static int getLoanExpires_Years() {
        return GameValues.loan.LOAN_YEARS;
    }
    
    public static int getLoanExpires() {
        return GameValues.loan.LOAN_DAYS * getLoanExpires_Years();
    }
    
    public static float getLoanMonthlyExpenses(final float fValue) {
        return fValue / (12 * getLoanExpires_Years());
    }
    
    public static int getLoanMaxNumber(final int iCivID) {
        return GameValues.loan.MAX_NUMBER_OF_LOANS + getCiv(iCivID).civBonuses.MaxNumberOfLoans;
    }
    
    public static float getInflationReduceCost_Legacy(final int iCivID) {
        return getCiv(iCivID).getInflation() * 100.0f * GameValues.inflation.INFLATION_REDUCE_COST_LEGACY_PER_INFLATION;
    }
    
    public static int getMaxAmountOfGold(final int iCivID) {
        final Civilization civ = getCiv(iCivID);
        return (int)((GameValues.budget.MAX_AMOUNT_OF_GOLD_MIN + (int)civ.civBonuses.MaximumAmountOfGold + (int)(civ.fTotalIncomePerMonth * GameValues.budget.MAX_AMOUNT_OF_GOLD_PER_INCOME)) * (1.0f + civ.civBonuses.MaximumAmountOfGold_Percentage));
    }
    
    public static float getResearchCost(final int iCivID) {
        return getCiv(iCivID).fResearchPerMonth * GameValues.research.RESEARCH_MAINTENANCE_COST * (1.0f + GameValues.budget.RESEARCH_LEVEL_COST[getCiv(iCivID).getResearchLevel()]);
    }
    
    public static float getResearchCost(final int iCivID, final float value) {
        return value * GameValues.research.RESEARCH_MAINTENANCE_COST * (1.0f + GameValues.budget.RESEARCH_LEVEL_COST[getCiv(iCivID).getResearchLevel()]);
    }
    
    public static final int getCivilizationsInGame() {
        int out = 0;
        for (int i = 1; i < getCivsSize(); ++i) {
            if (getCiv(i).getNumOfProvinces() > 0) {
                ++out;
            }
        }
        return out;
    }
    
    public static ArmyPos findArmy(final int iCivID, final String key) {
        try {
            if (key != null) {
                final Civilization civ = getCiv(iCivID);
                for (int i = civ.getNumOfProvinces() - 1; i >= 0; --i) {
                    for (int j = getProvince(civ.getProvinceID(i)).getArmySize() - 1; j >= 0; --j) {
                        if (getProvince(civ.getProvinceID(i)).getArmy(j).civID == iCivID && getProvince(civ.getProvinceID(i)).getArmy(j).key.equals(key)) {
                            return new ArmyPos(civ.getProvinceID(i), j);
                        }
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return null;
    }
    
    public static ArmyPos findArmy_IncludeVassals(final int iCivID, final String key) {
        try {
            if (key != null) {
                final Civilization civ = getCiv(iCivID);
                for (int i = civ.getNumOfProvinces() - 1; i >= 0; --i) {
                    for (int j = getProvince(civ.getProvinceID(i)).getArmySize() - 1; j >= 0; --j) {
                        if (getProvince(civ.getProvinceID(i)).getArmy(j).civID == iCivID && getProvince(civ.getProvinceID(i)).getArmy(j).key.equals(key)) {
                            return new ArmyPos(civ.getProvinceID(i), j);
                        }
                    }
                }
                for (int a = 0; a < civ.diplomacy.iVassalsSize; ++a) {
                    for (int k = getCiv(civ.diplomacy.lVassals.get(a).c).getNumOfProvinces() - 1; k >= 0; --k) {
                        for (int l = getProvince(getCiv(civ.diplomacy.lVassals.get(a).c).getProvinceID(k)).getArmySize() - 1; l >= 0; --l) {
                            if (getProvince(getCiv(civ.diplomacy.lVassals.get(a).c).getProvinceID(k)).getArmy(l).civID == iCivID && getProvince(getCiv(civ.diplomacy.lVassals.get(a).c).getProvinceID(k)).getArmy(l).key.equals(key)) {
                                return new ArmyPos(getCiv(civ.diplomacy.lVassals.get(a).c).getProvinceID(k), l);
                            }
                        }
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return null;
    }
    
    public static ArmyPos findArmy_FullCheck(final int iCivID, final String key) {
        try {
            if (key != null) {
                final Civilization civ = getCiv(iCivID);
                for (int i = civ.iArmyPositionSize - 1; i >= 0; --i) {
                    for (int j = getProvince(civ.getArmyPosition(i)).getArmySize() - 1; j >= 0; --j) {
                        if (getProvince(civ.getArmyPosition(i)).getArmy(j).civID == iCivID && getProvince(civ.getArmyPosition(i)).getArmy(j).key.equals(key)) {
                            return new ArmyPos(civ.getArmyPosition(i), j);
                        }
                    }
                }
                for (int i = civ.getNumOfProvinces() - 1; i >= 0; --i) {
                    for (int j = getProvince(civ.getProvinceID(i)).getArmySize() - 1; j >= 0; --j) {
                        if (getProvince(civ.getProvinceID(i)).getArmy(j).civID == iCivID && getProvince(civ.getProvinceID(i)).getArmy(j).key.equals(key)) {
                            return new ArmyPos(civ.getProvinceID(i), j);
                        }
                    }
                }
                for (int i = 0; i < getProvincesSize(); ++i) {
                    for (int j = getProvince(i).getArmySize() - 1; j >= 0; --j) {
                        if (getProvince(i).getArmy(j).civID == iCivID && getProvince(i).getArmy(j).key.equals(key)) {
                            return new ArmyPos(i, j);
                        }
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return null;
    }
    
    public static final void buildProsperity_AverageEconomy() {
        for (int i = 1; i < getCivsSize(); ++i) {
            getCiv(i).updateProsperity_AverageEconomy();
        }
    }
    
    public static String getProsperityLevel(final int iCivID) {
        for (int i = 0; i < GameValues.prosperity.PROSPERITY_LEVELS_VALUE.length; ++i) {
            if (getCiv(iCivID).fProsperity_AverageEconomy < GameValues.prosperity.PROSPERITY_LEVELS_VALUE[i]) {
                return Game.lang.get(GameValues.prosperity.PROSPERITY_LEVELS[i]);
            }
        }
        return Game.lang.get(GameValues.prosperity.PROSPERITY_LEVELS[GameValues.prosperity.PROSPERITY_LEVELS.length - 1]);
    }
    
    public static final void updateMapDistance() {
        Game.mapDistance = (Game.map.isWorldMap(Game.map.getActiveMapID()) ? new MapDistance() {
            @Override
            public float getManhattanDistance(final int provA, final int provB) {
                final Province provinceA = Game.getProvince(provA);
                final Province provinceB = Game.getProvince(provB);
                final int xDifference = Math.abs(provinceA.getCenterX_Real() - provinceB.getCenterX_Real());
                final int yDifference = Math.abs(provinceA.getCenterY_Real() - provinceB.getCenterY_Real());
                return (float)Math.min(xDifference + yDifference, Math.abs(provinceA.getCenterX_Real() + Game.mapBG.getWidth_Real() - provinceB.getCenterX_Real()) + Math.abs(provinceA.getCenterY_Real() - provinceB.getCenterY_Real()));
            }
            
            @Override
            public float getDistanceFromProvinceToProvince(final int provA, final int provB) {
                final Province provinceA = Game.getProvince(provA);
                final Province provinceB = Game.getProvince(provB);
                try {
                    return Math.min(Math.min((float)Math.sqrt(Math.pow(provinceB.getCenterX_Real() + Game.mapBG.getWidth_Real() - provinceA.getCenterX_Real(), 2.0) + Math.pow(provinceB.getCenterY_Real() - provinceA.getCenterY_Real(), 2.0)), (float)Math.sqrt(Math.pow(provinceB.getCenterX_Real() - (provinceA.getCenterX_Real() + Game.mapBG.getWidth_Real()), 2.0) + Math.pow(provinceB.getCenterY_Real() - provinceA.getCenterY_Real(), 2.0))), (float)Math.sqrt(Math.pow(provinceB.getCenterX_Real() - provinceA.getCenterX_Real(), 2.0) + Math.pow(provinceB.getCenterY_Real() - provinceA.getCenterY_Real(), 2.0)));
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                    return Game.iMaxDistance;
                }
            }
        } : new MapDistance() {
            @Override
            public float getManhattanDistance(final int provA, final int provB) {
                final Province provinceA = Game.getProvince(provA);
                final Province provinceB = Game.getProvince(provB);
                final int xDifference = Math.abs(provinceA.getCenterX_Real() - provinceB.getCenterX_Real());
                final int yDifference = Math.abs(provinceA.getCenterY_Real() - provinceB.getCenterY_Real());
                return (float)(xDifference + yDifference);
            }
            
            @Override
            public float getDistanceFromProvinceToProvince(final int provA, final int provB) {
                final Province provinceA = Game.getProvince(provA);
                final Province provinceB = Game.getProvince(provB);
                try {
                    return (float)Math.sqrt(Math.pow(provinceB.getCenterX_Real() - provinceA.getCenterX_Real(), 2.0) + Math.pow(provinceB.getCenterY_Real() - provinceA.getCenterY_Real(), 2.0));
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                    return Game.iMaxDistance;
                }
            }
        });
    }
    
    public static float getManhattanDistance(final int provA, final int provB) {
        return Game.mapDistance.getManhattanDistance(provA, provB);
    }
    
    public static final float getManhattanDistance_PercOfMax(final int provA, final int provB) {
        return getManhattanDistance(provA, provB) / Game.iMaxDistanceManhattan;
    }
    
    public static final float getDistanceFromProvinceToProvince(final int provA, final int provB) {
        final Province provinceA = getProvince(provA);
        final Province provinceB = getProvince(provB);
        return Game.mapDistance.getDistanceFromProvinceToProvince(provA, provB);
    }
    
    public static final float getDistanceFromProvinceToProvince_Simpler(final int provA, final int provB) {
        final Province provinceA = getProvince(provA);
        final Province provinceB = getProvince(provB);
        try {
            return (float)Math.sqrt(Math.pow(provinceB.getCenterX_Real() - provinceA.getCenterX_Real(), 2.0) + Math.pow(provinceB.getCenterY_Real() - provinceA.getCenterY_Real(), 2.0));
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            return Game.iMaxDistance;
        }
    }
    
    public static final float getDistance_PercOfMax(final int provA, final int provB) {
        return getDistanceFromProvinceToProvince(provA, provB) / Game.iMaxDistance;
    }
    
    public static final float getDistance_PercOfMax_Simpler(final int provA, final int provB) {
        return getDistanceFromProvinceToProvince_Simpler(provA, provB) / Game.iMaxDistance;
    }
    
    public static final float getDistanceFromAToB_Km(final int provA, final int provB) {
        return getDistanceFromProvinceToProvince(provA, provB) * Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.DistanceKm;
    }
    
    public static final void buildDistanceToCapital() {
        for (int i = 0; i < getProvincesSize(); ++i) {
            getProvince(i).buildDistanceToCapital();
        }
    }
    
    public static List<VassalsToRelease> getVassalsToRelease(final int civID) {
        final ArrayList<VassalsToRelease> out = new ArrayList<VassalsToRelease>();
        for (int i = 0; i < getCiv(civID).getNumOfProvinces(); ++i) {
            for (int j = 0; j < getProvince(getCiv(civID).getProvinceID(i)).iCoresSize; ++j) {
                if (getCiv(getProvince(getCiv(civID).getProvinceID(i)).getCore(j)).getNumOfProvinces() == 0) {
                    boolean found = false;
                    for (int k = out.size() - 1; k >= 0; --k) {
                        if (out.get(k).iCivID == getProvince(getCiv(civID).getProvinceID(i)).getCore(j)) {
                            final VassalsToRelease vassalsToRelease10;
                            final VassalsToRelease vassalsToRelease9;
                            final VassalsToRelease vassalsToRelease8;
                            final VassalsToRelease vassalsToRelease11;
                            final VassalsToRelease vassalsToRelease7 = vassalsToRelease11 = (vassalsToRelease8 = (vassalsToRelease9 = (vassalsToRelease10 = out.get(k))));
                            ++vassalsToRelease11.iNumOfProvinces;
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        out.add(new VassalsToRelease(getProvince(getCiv(civID).getProvinceID(i)).getCore(j), 1));
                    }
                }
            }
        }
        return out;
    }
    
    public static List<Integer> getVassalsToRelease_Provinces(final int civID, final int releaseCivID) {
        final ArrayList<Integer> out = new ArrayList<Integer>();
        for (int i = 0; i < getCiv(civID).getNumOfProvinces(); ++i) {
            for (int j = 0; j < getProvince(getCiv(civID).getProvinceID(i)).iCoresSize; ++j) {
                if (getProvince(getCiv(civID).getProvinceID(i)).getCore(j) == releaseCivID && getCiv(getProvince(getCiv(civID).getProvinceID(i)).getCore(j)).getNumOfProvinces() == 0) {
                    out.add(getCiv(civID).getProvinceID(i));
                    break;
                }
            }
        }
        return out;
    }
    
    public static List<String> loadSuggestedCivs(final int provinceID) {
        final ArrayList<String> out = new ArrayList<String>();
        if (FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "suggestedCivilizations/" + provinceID + ".txt").exists()) {
            final FileHandle file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "suggestedCivilizations/" + provinceID + ".txt");
            final String sOwners = file.readString();
            final String[] sRes = sOwners.split(";");
            for (int a = 0; a < sRes.length; ++a) {
                out.add(sRes[a]);
            }
        }
        return out;
    }
    
    public static void buildSuggestedCivs_ToSmallerMap_OnlyToConvert() {
        for (int i = 0; i < getProvincesSize(); ++i) {
            if (getProvince(i).getCitiesSize() > 0 && FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "suggestedCivilizations/" + i + ".txt").exists()) {
                final FileHandle file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "suggestedCivilizations/" + i + ".txt");
                final String sOwners = file.readString();
                final String[] sRes = sOwners.split(";");
                for (int a = 0; a < sRes.length; ++a) {
                    final FileHandle fileWrite = Gdx.files.local("map/" + Game.map.getFile_ActiveMap_Path() + "SUGGESTED_TEST.txt");
                    fileWrite.writeString(getProvince(i).getCity(0).getPosX() / Game.mapBG.iMapScale + ";" + getProvince(i).getCity(0).getPosY() / Game.mapBG.iMapScale + ";" + sRes[a] + ";", true);
                }
            }
        }
    }
    
    public static void generateSuggestedCivs_ToSmallerMap_OnlyToConvert() {
        final FileHandle file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "SUGGESTED_TEST.txt");
        final String sOwners = file.readString();
        final String[] sRes = sOwners.split(";");
        for (int a = 0; a < sRes.length; a += 3) {
            final int tx = Integer.parseInt(sRes[a]) * Game.mapBG.iMapScale;
            final int ty = Integer.parseInt(sRes[a + 1]) * Game.mapBG.iMapScale;
            int j = 0;
            while (j < getProvincesSize()) {
                if (getProvince(j).getMinX() <= tx && getProvince(j).getMaxX() >= tx && getProvince(j).getMinY() <= ty && getProvince(j).getMaxY() >= ty && pathContains(j, tx, ty)) {
                    if (!FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "suggestedCivilizations/" + j + ".txt").exists()) {
                        final FileHandle fileWrite = Gdx.files.local("map/" + Game.map.getFile_ActiveMap_Path() + "suggestedCivilizations/" + j + ".txt");
                        fileWrite.writeString(sRes[a + 2] + ";", false);
                        break;
                    }
                    final FileHandle file2 = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "suggestedCivilizations/" + j + ".txt");
                    final String sOwners2 = file2.readString();
                    final String[] sRes2 = sOwners2.split(";");
                    boolean add = true;
                    for (int az = 0; az < sRes2.length; ++az) {
                        if (sRes2[az].equals(sRes[a + 2])) {
                            add = false;
                        }
                    }
                    if (!add) {
                        break;
                    }
                    final FileHandle fileWrite2 = Gdx.files.local("map/" + Game.map.getFile_ActiveMap_Path() + "suggestedCivilizations/" + j + ".txt");
                    fileWrite2.writeString(sRes[a + 2] + ";", true);
                    break;
                }
                else {
                    ++j;
                }
            }
        }
    }
    
    public static void updateDrawArmy() {
        for (int i = 0; i < getProvincesSize(); ++i) {
            getProvince(i).updateDrawArmy();
        }
    }
    
    public static final void dispose() {
        Game.map.dispose();
        Game.mapBG.dispose();
    }
    
    static {
        Game.versionWidth = 0;
        Game.oR = new Random();
        Game.aiManager = new AI_Manager();
        Game.aiValues = new AI_Values();
        Game.aiValuesBuild = new AI_ValuesBuild();
        Game.aiValuesConvert = new AI_ValuesConvert();
        Game.aiValuesCores = new AI_ValuesCores();
        Game.aiValuesArmies = new AI_ValuesArmies();
        Game.aiValuesDiplomacy = new AI_ValuesDiplomacy();
        Game.mapCoords = new MapCoords();
        Game.mapScale = new MapScale();
        Game.mapEdgeMove = new MapEdgeMove();
        Game.mapScroll = new MapScroll();
        Game.cloudsAnimation = new CloudsManager();
        Game.mapCities = new MapCities();
        Game.mapOver = new MapOver();
        Game.mapOverSea = new MapOver();
        Game.mapModes = new MapModeManager();
        Game.mapTouchManager = new MapTouchManager();
        Game.terrainManager = new TerrainManager();
        Game.continents = new Continents();
        Game.geographicalRegions = new GeographicalRegions();
        Game.generalManager = new GeneralManager();
        Game.advisorManager = new AdvisorManager();
        Game.keyboard = new Keyboard();
        Game.gameThread = new GameThread();
        Game.gameThreadUpdate = new GameThread_Update();
        Game.gameThreadTurns = new GameThread_Turns();
        Game.gameThreadEvents = new GameThread_Events();
        Game.ideologiesManager = new IdeologiesManager();
        Game.religionManager = new ReligionManager();
        Game.gameAges = new Game_Ages();
        Game.flagManager = new FlagManager();
        Game.stats = new StatsManager();
        Game.FOG_OF_WAR = false;
        Game.SPECTATOR_MODE = false;
        Game.SANDBOX = false;
        Game.SCENARIO_EVENTS = true;
        Game.ENABLE_CALL_VASSALS = false;
        Game.scenarioID = 0;
        Game.reloadScenario = false;
        Game.mapScenarios = new MapScenarios();
        Game.highTextureSettings = true;
        Game.aiAggressivnes = 0;
        Game.difficultyID = 0;
        Game.DRAW_CITIES_MIN_SCALE = 1.3f;
        Game.DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE = 0.6f;
        Game.DRAW_INNER_BORDERS = 0.6f;
        Game.DRAW_OCCUPIED_PROVINCES_MIN_SCALE = 0.15f;
        Game.DRAW_ARMY_MIN_SCALE = 0.5f;
        Game.DRAW_OCCUPIED_SCALE = 2.0f;
        Game.lProvinces = new ArrayList<Province>();
        Game.iProvincesSize = 0;
        Game.lProvincesData = new ArrayList<ProvinceData>();
        Game.lProvincesData2 = new ArrayList<ProvinceData2>();
        Game.lProvincesData3 = new ArrayList<ProvinceData3>();
        Game.lProvincesData4 = new ArrayList<ProvinceData4>();
        Game.lProvincesData5 = new ArrayList<ProvinceData5>();
        Game.lProvincesData6 = new ArrayList<ProvinceData6>();
        Game.lProvincesData7 = new ArrayList<ProvinceData7>();
        Game.lProvincesData8 = new ArrayList<ProvinceData8>();
        Game.lProvincesData9 = new ArrayList<ProvinceData9>();
        Game.lProvincesData10 = new ArrayList<ProvinceData10>();
        Game.lProvincesPopulation = new ArrayList<ProvinceData_Population>();
        Game.alliancesSpecial = new ArrayList<Alliance>();
        Game.alliancesSpecialSize = 0;
        Game.alliancesSpecial_Flag = new ArrayList<Image>();
        Game.armyRecruit = new ArmyRecruit(0, 0, 0, null);
        Game.gameActiveProvince = new GameActiveProvince();
        Game.activeProvince_Animation_Data = new ProvinceAnimation();
        Game.selectedProvinces_Animation_Data = new ProvinceAnimation();
        Game.animationHover = new ProvinceAnimationHover();
        Game.iActiveProvince = -1;
        Game.iHoveredProvinceID = -1;
        Game.iOldActiveProvinceID = -1;
        Game.iOldHoveredProvinceID = 0;
        Game.hoveredArmy = new HoveredArmy();
        Game.activeArmy = new ArrayList<HoveredArmy>();
        Game.activeArmySize = 0;
        Game.iHoveredCapitalProvinceID = -1;
        Game.invasionArmyMode = false;
        Game.invasionArmyProvinces = new ArrayList<Integer>();
        Game.invasionArmyProvincesSize = 0;
        Game.regroupArmyMode = false;
        Game.regroupArmyProvinces = new ArrayList<Integer>();
        Game.regroupArmyShadows = new ArrayList<ArmyDivision_Shadow>();
        Game.iRegroupArmyProvincesSize = 0;
        Game.regroupArmyLine = null;
        Game.hoveredShipID = -1;
        Game.hoveredBattle = new HoveredBattle();
        Game.hoveredSiegeID = -1;
        Game.chooseProvinceMode = false;
        Game.MAX_BELOW_ZERO_POINT_X = 0;
        Game.cutProvinces = null;
        Game.regions = new RegionManager();
        Game.updateProvincesInView = true;
        Game.lProvincesInView = new ArrayList<Integer>();
        Game.lSeaProvincesInView = new ArrayList<Integer>();
        Game.lWastelandProvincesInView = new ArrayList<Integer>();
        Game.lExtraProvincesInView = new ArrayList<Integer>();
        Game.NUM_OF_PROVINCES_IN_VIEW = 0;
        Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW = 0;
        Game.NUM_OF_SEA_PROVINCES_IN_VIEW = 0;
        Game.NUM_OF_WASTELAND_PROVINCES_IN_VIEW = 0;
        Game.lCivs = new ArrayList<Civilization>();
        Game.iCivsSize = 0;
        Game.revolutionManager = new RevolutionManager();
        Game.revolutionMoveUnits = new RevolutionMoveUnits();
        Game.player = new Player();
        Game.deltaTime = Gdx.graphics.getDeltaTime();
        Game.simpleTasks = new ConcurrentLinkedDeque<SimpleTask>();
        Game.lCursors = new ArrayList<Cursor>();
        Game.activeCursorID = -1;
        Game.maxWastelandLvl = 99;
        Game.hoveredProvinceBG_LoadedID = -1;
        Game.hoveredProvinceBG = null;
        Game.fDashedLine_Percentage_HighlitedProvinceBorder = 0.0f;
        Game.highlightedProvinceBorder_BackAnimation = false;
        Game.highlightedProvinceBorder_Update = false;
        Game.inViewY_CordsY = 0;
        Game.inViewY_CordsY_Height = 0;
        Game.inViewX_CordsX = 0;
        Game.inViewX_CordsX_Width = 0;
        Game.iMaxDistance = 1.0f;
        Game.iMaxDistanceManhattan = 1.0f;
        Game.mapDistance = new MapDistance() {
            @Override
            public float getManhattanDistance(final int provA, final int provB) {
                return 0.0f;
            }
            
            @Override
            public float getDistanceFromProvinceToProvince(final int provA, final int provB) {
                return 0.0f;
            }
        };
    }
    
    public static class HoveredArmy
    {
        public String key;
        public int iCivID;
        public int iProvinceID;
        public int iArmyID;
        
        public HoveredArmy() {
            this.iProvinceID = -1;
        }
    }
    
    public static class ArmyPos
    {
        public int iProvinceID;
        public int iID;
        
        public ArmyPos(final int iProvinceID, final int iID) {
            this.iProvinceID = iProvinceID;
            this.iID = iID;
        }
    }
    
    public static class SimpleTask
    {
        public String taskKey;
        public int id;
        public int id2;
        
        public SimpleTask(final String taskKey) {
            this.taskKey = taskKey;
        }
        
        public SimpleTask(final String taskKey, final int id) {
            this.taskKey = taskKey;
            this.id = id;
        }
        
        public SimpleTask(final String taskKey, final int id, final int id2) {
            this.taskKey = taskKey;
            this.id = id;
            this.id2 = id2;
        }
        
        public void update() {
        }
        
        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof SimpleTask)) {
                return false;
            }
            final SimpleTask that = (SimpleTask)o;
            return Objects.equals(this.taskKey, that.taskKey);
        }
        
        @Override
        public int hashCode() {
            return 0;
        }
    }
    
    public static class LoadCivilizationData
    {
        public String Tag;
        public String Name;
        public int iR;
        public int iG;
        public int iB;
        public int ReligionID;
        public int GroupID;
        public String Wiki;
        
        public LoadCivilizationData() {
            this.Name = null;
            this.GroupID = 0;
        }
    }
    
    public static class VassalsToRelease
    {
        public int iCivID;
        public int iNumOfProvinces;
        
        public VassalsToRelease(final int iCivID, final int iNumOfProvinces) {
            this.iNumOfProvinces = 0;
            this.iCivID = iCivID;
            this.iNumOfProvinces = iNumOfProvinces;
        }
    }
    
    public static class HoveredBattle
    {
        public String key;
        public int iProvinceID;
        
        public HoveredBattle() {
            this.iProvinceID = -1;
        }
    }
    
    public static class FlagData
    {
        public int NumOfFlags;
    }
    
    public interface MapDistance
    {
        float getManhattanDistance(final int p0, final int p1);
        
        float getDistanceFromProvinceToProvince(final int p0, final int p1);
    }
}
