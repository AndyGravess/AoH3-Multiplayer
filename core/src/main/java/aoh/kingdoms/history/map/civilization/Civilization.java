// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.civilization;

import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.map.ColonizationManager;
import aoc.kingdoms.lukasz.map.LawsManager;
import aoc.kingdoms.lukasz.map.AdvantagesManager;
import aoc.kingdoms.lukasz.map.ResourcesManager;
import aoc.kingdoms.lukasz.map.allianceHRE.HREManager;
import aoc.kingdoms.lukasz.map.allianceHRE.Alliance;
import aoc.kingdoms.lukasz.jakowski.AI.Technology.AI_UnlockedTechnology;
import aoc.kingdoms.lukasz.menusInGame.Info.InGame_Info;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_CourtOptions;
import aoc.kingdoms.lukasz.menusInGame.Technology.InGame_TechnologyTree;
import aoc.kingdoms.lukasz.menusInGame.Technology.InGame_TechnologyChoose;
import aoc.kingdoms.lukasz.map.BuildingsManager;
import aoc.kingdoms.lukasz.menusInGame.InGame_Generals;
import aoc.kingdoms.lukasz.jakowski.Player.Notification.Notification;
import aoc.kingdoms.lukasz.map.RulersManager;
import aoc.kingdoms.lukasz.map.LegacyManager;
import aoc.kingdoms.lukasz.jakowski.Stats.Stats;
import aoc.kingdoms.lukasz.jakowski.Player.More.PlayerStats;
import aoc.kingdoms.lukasz.map.army.ArmyRegiment;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Game_Ages;
import aoc.kingdoms.lukasz.map.army.ArmyManager;
import aoc.kingdoms.lukasz.map.army.ArmyDivision;
import aoc.kingdoms.lukasz.jakowski.SaveLoad.SaveGameManager;
import aoc.kingdoms.lukasz.map.SiegeManager;
import aoc.kingdoms.lukasz.map.moveUnits.MoveUnits_Player;
import aoc.kingdoms.lukasz.map.diplomacy.Vassal;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.desktop.DesktopLauncher;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoc.kingdoms.lukasz.jakowski.GlyphLayout_Game;
import aoc.kingdoms.lukasz.map.province.Province;
import aoc.kingdoms.lukasz.textures.ImageManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.Collections;
import com.badlogic.gdx.graphics.Color;
import aoc.kingdoms.lukasz.textures.Image;
import aoc.kingdoms.lukasz.map.map.MapScenarios;
import aoc.kingdoms.lukasz.map.army.ArmyPosition;
import aoc.kingdoms.lukasz.map.technology.TechnologyTree;
import aoc.kingdoms.lukasz.map.technology.TechnologyResearch;
import aoc.kingdoms.lukasz.map.Loan;
import aoc.kingdoms.lukasz.map.civilization.data.Civilization_ArmiesWithoutGenerals;
import aoc.kingdoms.lukasz.map.civilization.data.Civilization_Cores;
import aoc.kingdoms.lukasz.map.civilization.data.Civilization_ConvertReligion;
import aoc.kingdoms.lukasz.jakowski.AI.Army.AI_MoveNoConnection;
import aoc.kingdoms.lukasz.jakowski.AI.Army.AI_Merge;
import aoc.kingdoms.lukasz.jakowski.AI.AI_Civ_CreateNewArmy;
import aoc.kingdoms.lukasz.jakowski.AI.AI_CivDiplomacy;
import aoc.kingdoms.lukasz.jakowski.AI.AI_Civ;
import aoc.kingdoms.lukasz.jakowski.Missions.Mission;
import aoc.kingdoms.lukasz.map.advisors.Advisor;
import aoc.kingdoms.lukasz.map.army.ArmyGeneral;
import java.util.concurrent.ConcurrentHashMap;
import aoc.kingdoms.lukasz.map.army.ArmyRecruit;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.map.moveUnits.MoveUnits;
import aoc.kingdoms.lukasz.map.province.ProvinceInvest;
import java.util.List;
import aoc.kingdoms.lukasz.map.diplomacy.Diplomacy;
import aoc.kingdoms.lukasz.map.civilization.stats.CivilizationEventsData3;
import aoc.kingdoms.lukasz.map.civilization.stats.CivilizationEventsData2;
import aoc.kingdoms.lukasz.map.civilization.stats.CivilizationEventsData;
import aoc.kingdoms.lukasz.map.Ruler;
import aoc.kingdoms.lukasz.map.civilization.save.CivData4;
import aoc.kingdoms.lukasz.map.civilization.save.CivData3;
import aoc.kingdoms.lukasz.map.civilization.save.CivData;

public class Civilization
{
    public int iCivID;
    public CivData civData;
    public CivData3 civData3;
    public CivData4 civData4;
    public String sCivName;
    public String realTag;
    public Ruler ruler;
    public CivilizationEventsData eventsData;
    public CivilizationEventsData2 eventsData2;
    public CivilizationEventsData3 eventsData3;
    public CivilizationEventsData_Variables eventsDataVariables;
    public CivilizationGoldenAge goldenAge;
    public int eventProvinceID;
    public float fGold;
    public float fLegacy;
    public double fManpower;
    public float fDiplomacy;
    public Diplomacy diplomacy;
    private int iR;
    private int iG;
    private int iB;
    private float fR;
    private float fG;
    private float fB;
    public int iCivRankID;
    public int iCivRankPosition;
    public float iCivRankScore;
    public float iCivRankScoreArmy;
    public int iIdeologyID;
    public int iGroupID;
    public int iRegiments;
    public int iRegimentsLimit;
    public int iUniqueResources;
    public boolean haveAccessSea;
    public boolean canAccessSea;
    public boolean canColonize;
    public boolean canBuildNuke;
    public List<ProvinceInvest> nukesDaysLeft;
    public int iNukesSize;
    private List<CivilizationLegacy> legacies;
    private int iLegaciesSize;
    public List<MoveUnits> lMoveUnits;
    private int iMoveUnitsSize;
    public ArrayList<ArrayList<ArmyRecruit>> lArmyRecruit;
    public int iArmyRecruitSize;
    public int iArmyRecruitSize_Total;
    public ConcurrentHashMap<String, Integer> lCreateNewArmy;
    private List<ArmyGeneral> lGeneralsNotAssigned;
    public int iGeneralsSize;
    public boolean isPlayerAlly;
    private int iNumOfProvinces;
    public float civStability_LostFrom100;
    public List<Integer> lProvinces;
    public Advisor advisorAdministration;
    public Advisor advisorEconomy;
    public Advisor advisorTechnology;
    public Advisor advisorMilitary;
    public int iAlternativeTechResearch;
    public List<Mission> lMissions;
    public int iMissionsSize;
    public CivilizationsNeighbors civNeighbors;
    public AI_Civ aiCiv;
    public AI_CivDiplomacy aiCivDiplomacy;
    public AI_Civ_CreateNewArmy aiCivCreateNewArmy;
    public AI_Merge aiMerge;
    public int aiUpdateID;
    public float aiScore;
    public AI_MoveNoConnection noConnectionMoveUnits;
    public List<String> inBattles;
    public List<Integer> underSiege;
    public int underSiegeSize;
    public List<Integer> occupiedProvinces;
    public int occupiedProvincesSize;
    public Civilization_ConvertReligion convertReligion;
    public Civilization_Cores civilizationCores;
    public Civilization_ArmiesWithoutGenerals armiesWithoutGenerals;
    public float fTotalIncomePerMonth;
    public float fTotalExpensesPerMonth;
    public float fArmyMaintenance;
    public float fIncomeLord;
    public float fExpenseVassal;
    public float fProsperity_AverageEconomy;
    public float fLoansCost;
    public List<Loan> loans;
    public int iLoansSize;
    public float fLegacyPerMonth;
    public double fManpowerMax;
    public double fManpowerPerMonth;
    public double fManpowerMax_ToLord;
    public float fResearchPerMonth;
    public float fDiplomacyMax;
    private float fDiplomacyPerMonth;
    public List<Integer> inAlliance;
    public int inAllianceSize;
    public CivilizationBonuses civBonuses;
    public List<CivilizationBonuses> civBonusesTemporary;
    public int iCivBonusesTemporarySize;
    public List<Integer> colonizationProvince;
    public int iColonizationProvinceSize;
    public List<Boolean> lTechResearched;
    public List<TechnologyResearch> lResearching;
    public List<CivilizationLegacy> lAdvantages;
    public int iAdvantagesSize;
    private List<TechnologyTree.Building> unlockedBuildings;
    private List<TechnologyTree.Unit> unlockedUnits;
    public List<TechnologyTree.Unit> unitsBest;
    public List<TechnologyTree.Unit> unitsBest_FirstLine;
    public List<TechnologyTree.Unit> unitsBest_Flank;
    public List<TechnologyTree.Unit> unitsBest_Support;
    public List<TechnologyTree.Unit> unitsBest_Siege;
    public int unitsBestSize;
    public int unitsBest_FirstLineSize;
    public int unitsBest_FlankSize;
    public int unitsBest_SupportSize;
    public int unitsBest_SiegeSize;
    public List<Integer> laws;
    public List<Integer> goodsProduced;
    public int largestProducerNum;
    public List<String> sTagsCanForm;
    public List<ArmyPosition> armyPosition;
    public int iArmyPositionSize;
    public int iArmyImgID;
    public int iCapitalNameWidth;
    public int iCapitalNameHeight;
    public MapScenarios.ScenarioCivData scenarioEditorData;
    private Image civFlag;
    public List<String> lCivNameChars;
    public int iCivNameLength;
    public String sCivName_UpperCase;
    public int iCivNameWidth;
    public int iCivNameHeight;
    private List<CivilizationRegion> lCivRegions;
    private int iCivRegionsSize;
    private boolean updateRegions;
    public float fTotalProvincesValue;
    public boolean warView_ParticipatesInWar;
    public boolean warView_IsAggressor;
    public boolean isFlagNearest;
    public Color civColor;
    public Color civColorMap;
    public Color civColorFog;
    
    public float getBalance() {
        return this.fTotalIncomePerMonth + this.civBonuses.MonthlyIncome - this.fTotalExpensesPerMonth;
    }
    
    public Civilization(final int iCivID, final String sCivTag, final int iPuppetOfCivID, final int iR, final int iG, final int iB, final int iCapitalProvinceID, final int iReligionID, final int iGroupID) {
        this.civData = new CivData();
        this.civData3 = new CivData3();
        this.civData4 = new CivData4();
        this.eventsData = new CivilizationEventsData();
        this.eventsData2 = new CivilizationEventsData2();
        this.eventsData3 = new CivilizationEventsData3();
        this.eventsDataVariables = new CivilizationEventsData_Variables();
        this.goldenAge = new CivilizationGoldenAge();
        this.eventProvinceID = -1;
        this.fGold = 0.0f;
        this.fLegacy = 0.0f;
        this.fManpower = 0.0;
        this.fDiplomacy = 0.0f;
        this.diplomacy = new Diplomacy();
        this.iCivRankID = 0;
        this.iCivRankPosition = 999;
        this.iCivRankScore = 0.0f;
        this.iCivRankScoreArmy = 0.0f;
        this.iIdeologyID = 0;
        this.iGroupID = 0;
        this.iRegiments = 0;
        this.iRegimentsLimit = 0;
        this.iUniqueResources = 0;
        this.haveAccessSea = false;
        this.canAccessSea = false;
        this.canColonize = false;
        this.canBuildNuke = false;
        this.nukesDaysLeft = new ArrayList<ProvinceInvest>();
        this.iNukesSize = 0;
        this.legacies = new ArrayList<CivilizationLegacy>();
        this.iLegaciesSize = 0;
        this.lMoveUnits = Collections.synchronizedList(new ArrayList<MoveUnits>());
        this.lArmyRecruit = new ArrayList<ArrayList<ArmyRecruit>>();
        this.lCreateNewArmy = new ConcurrentHashMap<String, Integer>();
        this.lGeneralsNotAssigned = Collections.synchronizedList(new ArrayList<ArmyGeneral>());
        this.iGeneralsSize = 0;
        this.isPlayerAlly = false;
        this.civStability_LostFrom100 = 0.0f;
        this.lProvinces = new ArrayList<Integer>();
        this.advisorAdministration = new Advisor();
        this.advisorEconomy = new Advisor();
        this.advisorTechnology = new Advisor();
        this.advisorMilitary = new Advisor();
        this.iAlternativeTechResearch = -1;
        this.lMissions = new ArrayList<Mission>();
        this.iMissionsSize = 0;
        this.civNeighbors = new CivilizationsNeighbors();
        this.aiCiv = new AI_Civ();
        this.aiCivDiplomacy = new AI_CivDiplomacy();
        this.aiCivCreateNewArmy = new AI_Civ_CreateNewArmy();
        this.aiMerge = new AI_Merge();
        this.aiUpdateID = 0;
        this.noConnectionMoveUnits = new AI_MoveNoConnection();
        this.inBattles = new ArrayList<String>();
        this.underSiege = new ArrayList<Integer>();
        this.underSiegeSize = 0;
        this.occupiedProvinces = new ArrayList<Integer>();
        this.occupiedProvincesSize = 0;
        this.convertReligion = new Civilization_ConvertReligion();
        this.civilizationCores = new Civilization_Cores();
        this.armiesWithoutGenerals = new Civilization_ArmiesWithoutGenerals();
        this.fTotalIncomePerMonth = 0.0f;
        this.fTotalExpensesPerMonth = 0.0f;
        this.fArmyMaintenance = 0.0f;
        this.fIncomeLord = 0.0f;
        this.fExpenseVassal = 0.0f;
        this.fProsperity_AverageEconomy = 0.0f;
        this.fLoansCost = 0.0f;
        this.loans = new ArrayList<Loan>();
        this.iLoansSize = 0;
        this.fLegacyPerMonth = 0.0f;
        this.fManpowerMax = 0.0;
        this.fManpowerPerMonth = 0.0;
        this.fManpowerMax_ToLord = 0.0;
        this.fResearchPerMonth = 0.0f;
        this.fDiplomacyMax = 0.0f;
        this.fDiplomacyPerMonth = 0.0f;
        this.inAlliance = new ArrayList<Integer>();
        this.inAllianceSize = 0;
        this.civBonuses = new CivilizationBonuses();
        this.civBonusesTemporary = new ArrayList<CivilizationBonuses>();
        this.iCivBonusesTemporarySize = 0;
        this.colonizationProvince = new ArrayList<Integer>();
        this.iColonizationProvinceSize = 0;
        this.lTechResearched = new ArrayList<Boolean>();
        this.lResearching = new ArrayList<TechnologyResearch>();
        this.lAdvantages = new ArrayList<CivilizationLegacy>();
        this.iAdvantagesSize = 0;
        this.unlockedBuildings = new ArrayList<TechnologyTree.Building>();
        this.unlockedUnits = new ArrayList<TechnologyTree.Unit>();
        this.unitsBest = new ArrayList<TechnologyTree.Unit>();
        this.unitsBest_FirstLine = new ArrayList<TechnologyTree.Unit>();
        this.unitsBest_Flank = new ArrayList<TechnologyTree.Unit>();
        this.unitsBest_Support = new ArrayList<TechnologyTree.Unit>();
        this.unitsBest_Siege = new ArrayList<TechnologyTree.Unit>();
        this.unitsBestSize = 0;
        this.unitsBest_FirstLineSize = 0;
        this.unitsBest_FlankSize = 0;
        this.unitsBest_SupportSize = 0;
        this.unitsBest_SiegeSize = 0;
        this.laws = new ArrayList<Integer>();
        this.goodsProduced = new ArrayList<Integer>();
        this.largestProducerNum = 0;
        this.sTagsCanForm = new ArrayList<String>();
        this.armyPosition = Collections.synchronizedList(new ArrayList<ArmyPosition>());
        this.iArmyPositionSize = 0;
        this.iArmyImgID = 0;
        this.iCapitalNameWidth = 0;
        this.iCapitalNameHeight = 0;
        this.scenarioEditorData = new MapScenarios.ScenarioCivData();
        this.civFlag = null;
        this.iCivNameLength = 0;
        this.lCivRegions = new ArrayList<CivilizationRegion>();
        this.updateRegions = false;
        this.fTotalProvincesValue = 1.0f;
        this.warView_ParticipatesInWar = false;
        this.warView_IsAggressor = false;
        this.isFlagNearest = false;
        this.civColor = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.civColorMap = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.civColorFog = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.civData.t = sCivTag;
        this.realTag = Game.ideologiesManager.getRealTag(sCivTag);
        this.setCivName(Game.lang.getCiv(sCivTag));
        this.iCivID = iCivID;
        this.civData.p = iPuppetOfCivID;
        this.civData.c = iCapitalProvinceID;
        this.setR(iR);
        this.setG(iG);
        this.setB(iB);
        this.civData.r = iReligionID;
        this.iGroupID = iGroupID;
        this.iArmyImgID = Images.army;
        this.isPlayerAlly = false;
        this.loadFlag();
        this.updateCivilizationTAG();
        this.buildLaws();
    }
    
    public Civilization(final int iCivID, final String sCivTag, final int iPuppetOfCivID, final int iR, final int iG, final int iB, final int iCapitalProvinceID, final int iReligionID, final int iGroupID, final boolean loadScenario) {
        this.civData = new CivData();
        this.civData3 = new CivData3();
        this.civData4 = new CivData4();
        this.eventsData = new CivilizationEventsData();
        this.eventsData2 = new CivilizationEventsData2();
        this.eventsData3 = new CivilizationEventsData3();
        this.eventsDataVariables = new CivilizationEventsData_Variables();
        this.goldenAge = new CivilizationGoldenAge();
        this.eventProvinceID = -1;
        this.fGold = 0.0f;
        this.fLegacy = 0.0f;
        this.fManpower = 0.0;
        this.fDiplomacy = 0.0f;
        this.diplomacy = new Diplomacy();
        this.iCivRankID = 0;
        this.iCivRankPosition = 999;
        this.iCivRankScore = 0.0f;
        this.iCivRankScoreArmy = 0.0f;
        this.iIdeologyID = 0;
        this.iGroupID = 0;
        this.iRegiments = 0;
        this.iRegimentsLimit = 0;
        this.iUniqueResources = 0;
        this.haveAccessSea = false;
        this.canAccessSea = false;
        this.canColonize = false;
        this.canBuildNuke = false;
        this.nukesDaysLeft = new ArrayList<ProvinceInvest>();
        this.iNukesSize = 0;
        this.legacies = new ArrayList<CivilizationLegacy>();
        this.iLegaciesSize = 0;
        this.lMoveUnits = Collections.synchronizedList(new ArrayList<MoveUnits>());
        this.lArmyRecruit = new ArrayList<ArrayList<ArmyRecruit>>();
        this.lCreateNewArmy = new ConcurrentHashMap<String, Integer>();
        this.lGeneralsNotAssigned = Collections.synchronizedList(new ArrayList<ArmyGeneral>());
        this.iGeneralsSize = 0;
        this.isPlayerAlly = false;
        this.civStability_LostFrom100 = 0.0f;
        this.lProvinces = new ArrayList<Integer>();
        this.advisorAdministration = new Advisor();
        this.advisorEconomy = new Advisor();
        this.advisorTechnology = new Advisor();
        this.advisorMilitary = new Advisor();
        this.iAlternativeTechResearch = -1;
        this.lMissions = new ArrayList<Mission>();
        this.iMissionsSize = 0;
        this.civNeighbors = new CivilizationsNeighbors();
        this.aiCiv = new AI_Civ();
        this.aiCivDiplomacy = new AI_CivDiplomacy();
        this.aiCivCreateNewArmy = new AI_Civ_CreateNewArmy();
        this.aiMerge = new AI_Merge();
        this.aiUpdateID = 0;
        this.noConnectionMoveUnits = new AI_MoveNoConnection();
        this.inBattles = new ArrayList<String>();
        this.underSiege = new ArrayList<Integer>();
        this.underSiegeSize = 0;
        this.occupiedProvinces = new ArrayList<Integer>();
        this.occupiedProvincesSize = 0;
        this.convertReligion = new Civilization_ConvertReligion();
        this.civilizationCores = new Civilization_Cores();
        this.armiesWithoutGenerals = new Civilization_ArmiesWithoutGenerals();
        this.fTotalIncomePerMonth = 0.0f;
        this.fTotalExpensesPerMonth = 0.0f;
        this.fArmyMaintenance = 0.0f;
        this.fIncomeLord = 0.0f;
        this.fExpenseVassal = 0.0f;
        this.fProsperity_AverageEconomy = 0.0f;
        this.fLoansCost = 0.0f;
        this.loans = new ArrayList<Loan>();
        this.iLoansSize = 0;
        this.fLegacyPerMonth = 0.0f;
        this.fManpowerMax = 0.0;
        this.fManpowerPerMonth = 0.0;
        this.fManpowerMax_ToLord = 0.0;
        this.fResearchPerMonth = 0.0f;
        this.fDiplomacyMax = 0.0f;
        this.fDiplomacyPerMonth = 0.0f;
        this.inAlliance = new ArrayList<Integer>();
        this.inAllianceSize = 0;
        this.civBonuses = new CivilizationBonuses();
        this.civBonusesTemporary = new ArrayList<CivilizationBonuses>();
        this.iCivBonusesTemporarySize = 0;
        this.colonizationProvince = new ArrayList<Integer>();
        this.iColonizationProvinceSize = 0;
        this.lTechResearched = new ArrayList<Boolean>();
        this.lResearching = new ArrayList<TechnologyResearch>();
        this.lAdvantages = new ArrayList<CivilizationLegacy>();
        this.iAdvantagesSize = 0;
        this.unlockedBuildings = new ArrayList<TechnologyTree.Building>();
        this.unlockedUnits = new ArrayList<TechnologyTree.Unit>();
        this.unitsBest = new ArrayList<TechnologyTree.Unit>();
        this.unitsBest_FirstLine = new ArrayList<TechnologyTree.Unit>();
        this.unitsBest_Flank = new ArrayList<TechnologyTree.Unit>();
        this.unitsBest_Support = new ArrayList<TechnologyTree.Unit>();
        this.unitsBest_Siege = new ArrayList<TechnologyTree.Unit>();
        this.unitsBestSize = 0;
        this.unitsBest_FirstLineSize = 0;
        this.unitsBest_FlankSize = 0;
        this.unitsBest_SupportSize = 0;
        this.unitsBest_SiegeSize = 0;
        this.laws = new ArrayList<Integer>();
        this.goodsProduced = new ArrayList<Integer>();
        this.largestProducerNum = 0;
        this.sTagsCanForm = new ArrayList<String>();
        this.armyPosition = Collections.synchronizedList(new ArrayList<ArmyPosition>());
        this.iArmyPositionSize = 0;
        this.iArmyImgID = 0;
        this.iCapitalNameWidth = 0;
        this.iCapitalNameHeight = 0;
        this.scenarioEditorData = new MapScenarios.ScenarioCivData();
        this.civFlag = null;
        this.iCivNameLength = 0;
        this.lCivRegions = new ArrayList<CivilizationRegion>();
        this.updateRegions = false;
        this.fTotalProvincesValue = 1.0f;
        this.warView_ParticipatesInWar = false;
        this.warView_IsAggressor = false;
        this.isFlagNearest = false;
        this.civColor = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.civColorMap = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.civColorFog = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.civData.t = sCivTag;
        this.realTag = Game.ideologiesManager.getRealTag(sCivTag);
        this.iCivID = iCivID;
        this.civData.p = iPuppetOfCivID;
        this.civData.c = iCapitalProvinceID;
        this.setR(iR);
        this.setG(iG);
        this.setB(iB);
        this.civData.r = iReligionID;
        this.iGroupID = iGroupID;
        this.iArmyImgID = Images.army;
        this.isPlayerAlly = false;
    }
    
    public void loadScenario_A() {
        this.setCivName(Game.lang.getCiv(this.getCivTag()));
        this.updateCivilizationTAG();
        this.buildLaws();
    }
    
    public void loadScenario_B() {
        this.loadFlag();
    }
    
    public final void addProvince_Just(final int nProvinceID) {
        for (int i = 0; i < this.iNumOfProvinces; ++i) {
            if (this.lProvinces.get(i) == nProvinceID) {
                return;
            }
        }
        this.lProvinces.add(nProvinceID);
        this.updateNumOfProvinces();
    }
    
    public final void addProvince_LoadScenario(final int nProvinceID) {
        for (int i = 0; i < this.iNumOfProvinces; ++i) {
            if (this.lProvinces.get(i) == nProvinceID) {
                return;
            }
        }
        this.lProvinces.add(nProvinceID);
        this.iNumOfProvinces = this.lProvinces.size();
    }
    
    public final void addProvince(final int nProvinceID) {
        for (int i = 0; i < this.iNumOfProvinces; ++i) {
            if (this.lProvinces.get(i) == nProvinceID) {
                return;
            }
        }
        this.lProvinces.add(nProvinceID);
        this.updateNumOfProvinces();
        Game.getProvince(nProvinceID).setCivRegionID(-1);
        this.convertReligion.checkProvince(nProvinceID, this.getCivID());
        this.civilizationCores.checkProvince_Full(nProvinceID, this.getCivID());
    }
    
    public final void removeProvince(final int nProvinceID) {
        for (int i = 0; i < this.iNumOfProvinces; ++i) {
            if (this.lProvinces.get(i) == nProvinceID) {
                this.lProvinces.remove(i);
                this.updateNumOfProvinces();
                break;
            }
        }
        Game.getProvince(nProvinceID).setCivRegionID(-1);
        this.convertReligion.removeProvince(nProvinceID);
        this.civilizationCores.removeProvince(nProvinceID);
    }
    
    public final void updateNumOfProvinces() {
        this.iNumOfProvinces = this.lProvinces.size();
        if (this.iCivRankID != CivilizationRanking.getCivilizationRank(this.iNumOfProvinces)) {
            this.iCivRankID = CivilizationRanking.getCivilizationRank(this.iNumOfProvinces);
            Game.gameThreadTurns.addCivUpdateMaxManpower(this.getCivID());
            Game.gameThread.addCivUpdateLegacyPerMonth(this.getCivID());
            Game.gameThread.addCivUpdateResearchPerMonth(this.getCivID());
            Game.gameThread.addCivUpdateTotalIncomePerMonth(this.iCivID);
        }
    }
    
    public final void addArmyPosition(final int nProvinceID, final String key) {
        try {
            for (int i = this.iArmyPositionSize - 1; i >= 0; --i) {
                if (this.armyPosition.get(i).provinceID == nProvinceID && this.armyPosition.get(i).key.equals(key)) {
                    return;
                }
            }
            this.armyPosition.add(new ArmyPosition(nProvinceID, key));
            this.iArmyPositionSize = this.armyPosition.size();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void removeArmyPosition(final int nProvinceID, final String key) {
        try {
            for (int i = 0; i < this.iArmyPositionSize; ++i) {
                if (this.armyPosition.get(i).provinceID == nProvinceID && this.armyPosition.get(i).key.equals(key)) {
                    this.armyPosition.remove(i);
                    this.iArmyPositionSize = this.armyPosition.size();
                    return;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public void updateArmyPosition(final String key, final int nProvinceID) {
        try {
            for (int i = 0; i < this.iArmyPositionSize; ++i) {
                if (this.armyPosition.get(i).key.equals(key)) {
                    this.armyPosition.get(i).provinceID = nProvinceID;
                    return;
                }
            }
            this.armyPosition.add(new ArmyPosition(nProvinceID, key));
            this.iArmyPositionSize = this.armyPosition.size();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public int getArmyPosition(final int i) {
        try {
            return this.armyPosition.get(i).provinceID;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            return -1;
        }
    }
    
    public String getArmyPositionKey(final int i) {
        try {
            return this.armyPosition.get(i).key;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            return "";
        }
    }
    
    public final void clearArmyPosition() {
        this.armyPosition.clear();
        this.iArmyPositionSize = 0;
    }
    
    public boolean armyExists(final String key) {
        try {
            for (int i = 0; i < this.iArmyPositionSize; ++i) {
                if (this.armyPosition.get(i).key.equals(key)) {
                    return true;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return false;
    }
    
    public final boolean loadFlag() {
        if (FileManager.loadFile("gfx/flagsXH/" + this.getCivTag() + ".png").exists()) {
            this.civFlag = new Image(new Texture(FileManager.loadFile("gfx/flagsXH/" + this.getCivTag() + ".png"), Pixmap.Format.RGB888, false), Texture.TextureFilter.Linear);
        }
        else if (FileManager.loadFile("gfx/flagsXH/" + this.realTag + ".png").exists()) {
            this.civFlag = new Image(new Texture(FileManager.loadFile("gfx/flagsXH/" + this.realTag + ".png"), Pixmap.Format.RGB888, false), Texture.TextureFilter.Linear);
        }
        else if (FileManager.loadFile("gfx/flagsH/" + this.getCivTag() + ".png").exists()) {
            this.civFlag = new Image(new Texture(FileManager.loadFile("gfx/flagsH/" + this.getCivTag() + ".png"), Pixmap.Format.RGB888, false), Texture.TextureFilter.Linear);
        }
        else if (FileManager.loadFile("gfx/flagsH/" + this.realTag + ".png").exists()) {
            this.civFlag = new Image(new Texture(FileManager.loadFile("gfx/flagsH/" + this.realTag + ".png"), Pixmap.Format.RGB888, false), Texture.TextureFilter.Linear);
        }
        else if (FileManager.loadFile("gfx/flags/" + this.getCivTag() + ".png").exists()) {
            this.civFlag = new Image(new Texture(FileManager.loadFile("gfx/flags/" + this.getCivTag() + ".png"), Pixmap.Format.RGB888, false), Texture.TextureFilter.Nearest);
            this.isFlagNearest = true;
        }
        else if (FileManager.loadFile("gfx/flags/" + this.realTag + ".png").exists()) {
            this.civFlag = new Image(new Texture(FileManager.loadFile("gfx/flags/" + this.realTag + ".png"), Pixmap.Format.RGB888, false), Texture.TextureFilter.Nearest);
            this.isFlagNearest = true;
        }
        else {
            this.civFlag = new Image(new Texture(FileManager.loadFile("gfx/flagsXH/ran.png"), Pixmap.Format.RGB888, false), Texture.TextureFilter.Nearest);
            this.isFlagNearest = true;
        }
        return true;
    }
    
    public final Image getFlag() {
        return (this.civFlag == null) ? ImageManager.getImage(Images.randomCivilizationFlag) : this.civFlag;
    }
    
    public final void disposeFlag() {
        if (this.civFlag != null) {
            this.civFlag.getTexture().dispose();
            this.civFlag = null;
        }
    }
    
    public final void createCivilizationRegion(final int nProvinceID) {
        this.lCivRegions.add(new CivilizationRegion(nProvinceID, this.iCivRegionsSize));
        this.iCivRegionsSize = this.lCivRegions.size();
        try {
            for (int i = 0; i < this.getNumOfProvinces(); ++i) {
                Game.getProvince(this.getProvinceID(i)).wasCivRegion = false;
            }
        }
        catch (final Exception ex) {}
        Game.getProvince(nProvinceID).wasCivRegion = true;
        this.buildCivilizationRegion(nProvinceID, this.iCivRegionsSize - 1);
    }
    
    private final void buildCivilizationRegion(final int nProvinceID, final int nCivRegionID) {
        for (int i = 0; i < Game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++i) {
            final int neighborProvinceID = Game.getProvince(nProvinceID).getNeighboringProvinces(i);
            final Province neighProvince = Game.getProvince(neighborProvinceID);
            if (neighProvince.getCivID() == this.iCivID && !neighProvince.wasCivRegion && neighProvince.getCivRegionID() < 0) {
                neighProvince.wasCivRegion = true;
                this.lCivRegions.get(nCivRegionID).addProvince(neighborProvinceID);
                neighProvince.setCivRegionID(nCivRegionID);
                CivilizationRegionsManager.updateIsProvinceAssigned(neighborProvinceID, true);
                this.buildCivilizationRegion(neighborProvinceID, nCivRegionID);
            }
        }
    }
    
    protected final boolean civRegionsContainsProvince(final int nProvinceID) {
        for (int i = 0; i < this.iCivRegionsSize; ++i) {
            if (this.lCivRegions.get(i).containsProvince(nProvinceID)) {
                return true;
            }
        }
        return false;
    }
    
    public final void clearCivRegions() {
        for (int i = 0; i < this.getNumOfProvinces(); ++i) {
            Game.getProvince(this.getProvinceID(i)).setCivRegionID(-1);
        }
        this.lCivRegions.clear();
        this.iCivRegionsSize = 0;
    }
    
    public final void clearCivRegions_Just() {
        this.lCivRegions.clear();
        this.iCivRegionsSize = 0;
    }
    
    public final CivilizationRegion getCivRegion(final int i) {
        return this.lCivRegions.get(i);
    }
    
    public final int getCivRegionsSize() {
        return this.iCivRegionsSize;
    }
    
    public final void setCivName(String sCivName) {
        if (sCivName.length() <= 0) {
            sCivName = "AB";
        }
        this.sCivName = sCivName;
        final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        glyphLayout.setText(Renderer.fontMain.get(0), sCivName);
        this.iCivNameWidth = (int)glyphLayout.width;
        this.iCivNameHeight = (int)glyphLayout.height;
        this.lCivNameChars = new ArrayList<String>();
        this.sCivName_UpperCase = sCivName.toUpperCase();
        for (int i = 0; i < this.sCivName.length(); ++i) {
            this.lCivNameChars.add("" + this.sCivName_UpperCase.charAt(i));
        }
        this.iCivNameLength = this.lCivNameChars.size();
        glyphLayout.setText(Renderer.fontMain.get(4), this.sCivName);
        this.iCapitalNameWidth = (int)glyphLayout.width;
        this.iCapitalNameHeight = (int)glyphLayout.height;
    }
    
    public final int getCivNameWidth() {
        return this.iCivNameWidth;
    }
    
    public final int getCivNameHeight() {
        return this.iCivNameHeight;
    }
    
    public final String getCivNameCharacter(final int id) {
        return this.lCivNameChars.get(id);
    }
    
    public final int getCivNameLength() {
        return this.iCivNameLength;
    }
    
    public final boolean getUpdateRegions() {
        return this.updateRegions;
    }
    
    public final void setUpdateRegions(final boolean updateRegions) {
        this.updateRegions = updateRegions;
    }
    
    public final int getIdeologyID() {
        return this.iIdeologyID;
    }
    
    public final void setIdeologyID(final int iIdeologyID) {
        this.iIdeologyID = iIdeologyID;
    }
    
    public final int getReligionID() {
        return this.civData.r;
    }
    
    public final void setReligionID(final int iReligionID) {
        this.civData.r = iReligionID;
    }
    
    public final void setReligionID_UpdateBonuses(final int nReligionID) {
        DesktopLauncher.SMS("ChangeReligion: " + this.getCivID() + " " + nReligionID);
        Game.religionManager.updateCivBonuses(this.iCivID, this.civData.r, -1, true);
        this.civData.r = nReligionID;
        Game.religionManager.updateCivBonuses(this.iCivID, this.civData.r, 1, false);
    }
    
    public final void setReligionID_UpdateBonuses_Client(final int nReligionID) {
        Game.religionManager.updateCivBonuses(this.iCivID, this.civData.r, -1, true);
        this.civData.r = nReligionID;
        Game.religionManager.updateCivBonuses(this.iCivID, this.civData.r, 1, false);
    }
    
    public final void setR(final int nR) {
        this.iR = nR;
        this.fR = nR / 255.0f;
        this.civColor.r = this.fR;
        this.civColorMap.r = this.fR;
        this.civColorFog.r = Math.max(0.0f, this.fR - GameValues.fog.PROVINCE_FOG_COLOR_DIFFERENCE);
    }
    
    public final void setG(final int nG) {
        this.iG = nG;
        this.fG = nG / 255.0f;
        this.civColor.g = this.fG;
        this.civColorMap.g = this.fG;
        this.civColorFog.g = Math.max(0.0f, this.fG - GameValues.fog.PROVINCE_FOG_COLOR_DIFFERENCE);
    }
    
    public final void setB(final int nB) {
        this.iB = nB;
        this.fB = nB / 255.0f;
        this.civColor.b = this.fB;
        this.civColorMap.b = this.fB;
        this.civColorFog.b = Math.max(0.0f, this.fB - GameValues.fog.PROVINCE_FOG_COLOR_DIFFERENCE);
        this.updateVassalsColors();
    }
    
    public final void updateColorMap() {
        if (this.iCivID == this.getPuppetOfCivID()) {
            this.civColorMap = this.civColor;
        }
        else {
            this.civColorMap = CFG.getColorMixed_2(this.civColor, Game.getCiv(this.getPuppetOfCivID()).civColor);
        }
        this.civColorFog.r = Math.max(0.0f, this.civColorMap.r - GameValues.fog.PROVINCE_FOG_COLOR_DIFFERENCE);
        this.civColorFog.g = Math.max(0.0f, this.civColorMap.g - GameValues.fog.PROVINCE_FOG_COLOR_DIFFERENCE);
        this.civColorFog.b = Math.max(0.0f, this.civColorMap.b - GameValues.fog.PROVINCE_FOG_COLOR_DIFFERENCE);
    }
    
    public final void updateVassalsColors() {
        for (int i = 0; i < this.diplomacy.iVassalsSize; ++i) {
            Game.getCiv(this.diplomacy.lVassals.get(i).c).updateColorMap();
        }
    }
    
    public final int getR_Int() {
        return this.iR;
    }
    
    public final int getG_Int() {
        return this.iG;
    }
    
    public final int getB_Int() {
        return this.iB;
    }
    
    public final float getR() {
        return this.fR;
    }
    
    public final float getG() {
        return this.fG;
    }
    
    public final float getB() {
        return this.fB;
    }
    
    public final Color getColor(final float fAlpha) {
        return this.getRGB(fAlpha);
    }
    
    public final Color getRGB(final float fAlpha) {
        return new Color(this.getR(), this.getG(), this.getB(), fAlpha);
    }
    
    public final String getCivName() {
        return this.sCivName;
    }
    
    public final int getNumOfProvinces() {
        return this.iNumOfProvinces;
    }
    
    public final int getProvinceID(final int i) {
        try {
            return this.lProvinces.get(i);
        }
        catch (final Exception ex) {
            return -1;
        }
    }
    
    public final List<Integer> getProvinces() {
        return this.lProvinces;
    }
    
    public final int getCapitalProvinceID() {
        return this.civData.c;
    }
    
    public final void moveCapital_ToLargestProvince() {
        if (this.getNumOfProvinces() > 0) {
            int tBestID = 0;
            for (int i = this.getNumOfProvinces() - 1; i > 0; --i) {
                if (Game.getProvince(this.getProvinceID(tBestID)).getPopulationTotal() < Game.getProvince(this.getProvinceID(i)).getPopulationTotal()) {
                    tBestID = i;
                }
            }
            this.setCapitalProvinceID(this.getProvinceID(tBestID));
        }
    }
    
    public final boolean moveCapital(final int toProvinceID) {
        if (toProvinceID >= 0 && toProvinceID < Game.getProvincesSize() && toProvinceID != this.getCapitalProvinceID() && !Game.getProvince(toProvinceID).isOccupied() && this.fGold >= GameValues.capital.MOVE_CAPITAL_COST) {
            this.fGold -= GameValues.capital.MOVE_CAPITAL_COST;
            this.setCapitalProvinceID(toProvinceID);
            return true;
        }
        return false;
    }
    
    public final void setCapitalProvinceID(final int nCapitalProvinceID) {
        if (Game.getProvince(this.civData.c).getCivID() == this.iCivID) {
            Game.getProvince(this.civData.c).setIsCapital(false);
            Game.getProvince(this.civData.c).updateBuildingLimit();
            Game.getProvince(this.civData.c).updateInfrastructureMax();
        }
        this.civData.c = nCapitalProvinceID;
        if (Game.getProvince(this.civData.c).getCivID() == this.iCivID) {
            Game.getProvince(this.civData.c).setIsCapital(true);
            Game.getProvince(this.civData.c).updateBuildingLimit();
            Game.getProvince(this.civData.c).updateInfrastructureMax();
            Game.getProvince(this.civData.c).setDrawCities(true);
            Game.buildDistanceToCapital();
        }
    }
    
    public final void setCivTag(final String sCivTag) {
        this.civData.t = sCivTag;
        this.realTag = Game.ideologiesManager.getRealTag(sCivTag);
        this.setCivName(Game.lang.getCiv(sCivTag));
    }
    
    public final void updateCivilizationTAG(final String nCivTag, final int iR, final int iG, final int iB) {
        this.setCivTag(nCivTag);
        this.setR(iR);
        this.setG(iG);
        this.setB(iB);
        this.updateCivilizationTAG();
        this.loadFlag();
        this.updateColorMap();
    }
    
    public final void updateCivilizationTAG() {
        this.setIdeologyID(Game.ideologiesManager.getIdeologyID(this.getCivTag()));
    }
    
    public final String getCivTag() {
        return this.civData.t;
    }
    
    public int getCivID() {
        return this.iCivID;
    }
    
    public final void setCivID(final int iCivID) {
        this.iCivID = iCivID;
        this.civData.p = iCivID;
    }
    
    public final void setCivID_Just(final int iCivID) {
        this.iCivID = iCivID;
    }
    
    public int getPuppetOfCivID() {
        return this.civData.p;
    }
    
    public final void setPuppetOfCivID(final int nPuppetOfCivID) {
        if (nPuppetOfCivID != this.iCivID || nPuppetOfCivID != this.getPuppetOfCivID()) {
            Game.getCiv(this.getPuppetOfCivID()).removeVassal(this.iCivID);
        }
        this.civData.p = nPuppetOfCivID;
        if (this.getPuppetOfCivID() != this.iCivID) {
            Game.getCiv(this.getPuppetOfCivID()).addVassal(this.iCivID);
        }
        this.updateColorMap();
    }
    
    public final void addVassal(final int nCivID) {
        try {
            for (int i = 0; i < this.diplomacy.lVassals.size(); ++i) {
                if (this.diplomacy.lVassals.get(i).c == nCivID) {
                    return;
                }
            }
            this.diplomacy.lVassals.add(new Vassal(nCivID));
            this.diplomacy.iVassalsSize = this.diplomacy.lVassals.size();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void removeVassal(final int nCivID) {
        try {
            for (int i = 0; i < this.diplomacy.lVassals.size(); ++i) {
                if (this.diplomacy.lVassals.get(i).c == nCivID) {
                    this.diplomacy.lVassals.remove(i);
                    this.diplomacy.iVassalsSize = this.diplomacy.lVassals.size();
                    return;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateVassalsIncome() {
        try {
            this.fIncomeLord = 0.0f;
            for (int i = 0; i < this.diplomacy.lVassals.size(); ++i) {
                this.fIncomeLord += Game.getCiv(this.diplomacy.lVassals.get(i).c).fExpenseVassal;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateMoveInBattle(final String key, final boolean inBattle) {
        try {
            for (int i = 0; i < this.iMoveUnitsSize; ++i) {
                if (this.lMoveUnits.get(i).key.equals(key)) {
                    this.lMoveUnits.get(i).inBattle = inBattle;
                    return;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final boolean newMove(final int fromProvinceID, final int toProvinceID, final String key, final int extraArmyY, final boolean retreat) {
        try {
            if (fromProvinceID == toProvinceID) {
                return false;
            }
            final int armyID = Game.getProvince(fromProvinceID).getArmyKeyID(key);
            if (armyID >= 0) {
                if (Game.getProvince(fromProvinceID).getArmy(armyID).inBattle && Game.battleManager.isArmyInBattle(fromProvinceID, key)) {
                    return false;
                }
                MoveUnits nMoveUnits;
                if (this.getCivID() == Game.player.iCivID) {
                    nMoveUnits = new MoveUnits_Player(this.getCivID(), fromProvinceID, toProvinceID, key, extraArmyY, retreat, false);
                    if (nMoveUnits.iRouteSize > 2 && nMoveUnits.iRouteSize < GameValues.army.MOVE_UNITS_TRY_MOVING_LAND_IF_PATH_IS_BELOW_PLAYER && nMoveUnits.haveSeaProvince()) {
                        MoveUnits nMoveUnitsLand = new MoveUnits_Player(this.getCivID(), fromProvinceID, toProvinceID, key, extraArmyY, retreat, true);
                        if (nMoveUnitsLand.iRouteSize > 1 && nMoveUnitsLand.getWidthTotal() < nMoveUnits.getWidthTotal()) {
                            nMoveUnits = nMoveUnitsLand;
                            nMoveUnitsLand = null;
                        }
                    }
                }
                else {
                    nMoveUnits = new MoveUnits(this.getCivID(), fromProvinceID, toProvinceID, key, extraArmyY, retreat, false);
                    if (nMoveUnits.iRouteSize > 2 && nMoveUnits.iRouteSize < GameValues.army.MOVE_UNITS_TRY_MOVING_LAND_IF_PATH_IS_BELOW && nMoveUnits.haveSeaProvince()) {
                        MoveUnits nMoveUnitsLand = new MoveUnits(this.getCivID(), fromProvinceID, toProvinceID, key, extraArmyY, retreat, true);
                        if (nMoveUnitsLand.iRouteSize > 1 && nMoveUnitsLand.getWidthTotal() < nMoveUnits.getWidthTotal()) {
                            nMoveUnits = nMoveUnitsLand;
                            nMoveUnitsLand = null;
                        }
                    }
                }
                if (nMoveUnits.iRouteSize > 1) {
                    int i = 0;
                    while (i < this.iMoveUnitsSize) {
                        if (this.lMoveUnits.get(i).key.equals(key)) {
                            if (this.lMoveUnits.get(i).inRetreat) {
                                return false;
                            }
                            if (this.lMoveUnits.get(i).getToProvinceID() == nMoveUnits.getToProvinceID()) {
                                nMoveUnits.doneMovementProgressWidth = this.lMoveUnits.get(i).doneMovementProgressWidth;
                                nMoveUnits.movementProgressOverWidth = this.lMoveUnits.get(i).movementProgressOverWidth;
                                nMoveUnits.currentMovementProgressWidth = this.lMoveUnits.get(i).currentMovementProgressWidth;
                                nMoveUnits.lCurrentMovingTime = this.lMoveUnits.get(i).lCurrentMovingTime;
                                nMoveUnits.fCurrentMovingPercentage = this.lMoveUnits.get(i).fCurrentMovingPercentage;
                                this.removeMove(i);
                                break;
                            }
                            if (this.lMoveUnits.get(i).getProgressPerc() < GameValues.army.MOVE_UNITS_LOCKED_MOVE) {
                                this.removeMove(i);
                                break;
                            }
                            nMoveUnits = new MoveUnits(this.getCivID(), this.lMoveUnits.get(i).getToProvinceID(), toProvinceID, key, this.lMoveUnits.get(i).extraArmyY, this.lMoveUnits.get(i).getFromProvinceID());
                            if (nMoveUnits.iRouteSize > 1) {
                                nMoveUnits.doneMovementProgressWidth = this.lMoveUnits.get(i).doneMovementProgressWidth;
                                nMoveUnits.movementProgressOverWidth = this.lMoveUnits.get(i).movementProgressOverWidth;
                                nMoveUnits.currentMovementProgressWidth = this.lMoveUnits.get(i).currentMovementProgressWidth;
                                nMoveUnits.lCurrentMovingTime = this.lMoveUnits.get(i).lCurrentMovingTime;
                                nMoveUnits.fCurrentMovingPercentage = this.lMoveUnits.get(i).fCurrentMovingPercentage;
                                this.lMoveUnits.set(i, nMoveUnits);
                                Game.getProvince(fromProvinceID).getArmy(armyID).inRetreat = retreat;
                                return true;
                            }
                            return false;
                        }
                        else {
                            ++i;
                        }
                    }
                    this.lMoveUnits.add(nMoveUnits);
                    this.iMoveUnitsSize = this.lMoveUnits.size();
                    Game.getProvince(fromProvinceID).getArmy(armyID).inRetreat = retreat;
                    Game.getProvince(fromProvinceID).getArmy(armyID).setInMovement(true);
                    Game.getProvince(fromProvinceID).updateArmyPosY();
                    Game.getProvince(fromProvinceID).updateIsUnderSiege();
                    return true;
                }
                return false;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return false;
    }
    
    public final void removeMove(final int i) {
        try {
            final int tID = Game.getProvince(this.lMoveUnits.get(i).getFromProvinceID()).getArmyKeyID(this.lMoveUnits.get(i).key);
            if (tID >= 0) {
                Game.getProvince(this.lMoveUnits.get(i).getFromProvinceID()).getArmy(tID).setInMovement(false);
                Game.getProvince(this.lMoveUnits.get(i).getFromProvinceID()).getArmy(tID).inRetreat = false;
                Game.getProvince(this.lMoveUnits.get(i).getFromProvinceID()).getArmy(tID).iShiftX_Scaled = 0;
                Game.getProvince(this.lMoveUnits.get(i).getFromProvinceID()).getArmy(tID).iShiftY_Scaled = 0;
            }
            else {
                final Game.ArmyPos armyPos = Game.findArmy_FullCheck(this.getCivID(), this.lMoveUnits.get(i).key);
                if (armyPos != null) {
                    Game.getProvince(armyPos.iProvinceID).getArmy(armyPos.iID).setInMovement(false);
                    Game.getProvince(armyPos.iProvinceID).getArmy(armyPos.iID).inRetreat = false;
                    Game.getProvince(armyPos.iProvinceID).getArmy(armyPos.iID).iShiftX_Scaled = 0;
                    Game.getProvince(armyPos.iProvinceID).getArmy(armyPos.iID).iShiftY_Scaled = 0;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        this.lMoveUnits.remove(i);
        this.iMoveUnitsSize = this.lMoveUnits.size();
    }
    
    public final boolean cancelMove(final String key) {
        int i = 0;
        while (i < this.iMoveUnitsSize) {
            if (this.lMoveUnits.get(i).key.equals(key)) {
                if (this.lMoveUnits.get(i).inRetreat) {
                    return false;
                }
                if (this.lMoveUnits.get(i).getProgressPerc() >= GameValues.army.MOVE_UNITS_LOCKED_MOVE) {
                    final MoveUnits nMoveUnits = new MoveUnits(this.getCivID(), this.lMoveUnits.get(i).getToProvinceID(), this.lMoveUnits.get(i).getFromProvinceID(), key, this.lMoveUnits.get(i).extraArmyY, this.lMoveUnits.get(i).getFromProvinceID());
                    nMoveUnits.doneMovementProgressWidth = this.lMoveUnits.get(i).doneMovementProgressWidth;
                    nMoveUnits.movementProgressOverWidth = this.lMoveUnits.get(i).movementProgressOverWidth;
                    nMoveUnits.currentMovementProgressWidth = this.lMoveUnits.get(i).currentMovementProgressWidth;
                    nMoveUnits.lCurrentMovingTime = this.lMoveUnits.get(i).lCurrentMovingTime;
                    nMoveUnits.fCurrentMovingPercentage = this.lMoveUnits.get(i).fCurrentMovingPercentage;
                    this.lMoveUnits.set(i, nMoveUnits);
                }
                else {
                    final int tID = Game.getProvince(this.lMoveUnits.get(i).getFromProvinceID()).getArmyKeyID(this.lMoveUnits.get(i).key);
                    if (tID >= 0) {
                        Game.getProvince(this.lMoveUnits.get(i).getFromProvinceID()).getArmy(tID).setInMovement(false);
                        Game.getProvince(this.lMoveUnits.get(i).getFromProvinceID()).getArmy(tID).iShiftX_Scaled = 0;
                        Game.getProvince(this.lMoveUnits.get(i).getFromProvinceID()).getArmy(tID).iShiftY_Scaled = 0;
                    }
                    SiegeManager.checkForSiege(this.lMoveUnits.get(i).getFromProvinceID());
                    Game.getProvince(this.lMoveUnits.get(i).getFromProvinceID()).updateArmyPosY();
                    this.removeMove(i);
                }
                return true;
            }
            else {
                ++i;
            }
        }
        return false;
    }
    
    public final boolean removeMove(final String key) {
        for (int i = 0; i < this.iMoveUnitsSize; ++i) {
            if (this.lMoveUnits.get(i).key.equals(key)) {
                final int tID = Game.getProvince(this.lMoveUnits.get(i).getFromProvinceID()).getArmyKeyID(this.lMoveUnits.get(i).key);
                if (tID >= 0) {
                    Game.getProvince(this.lMoveUnits.get(i).getFromProvinceID()).getArmy(tID).setInMovement(false);
                    Game.getProvince(this.lMoveUnits.get(i).getFromProvinceID()).getArmy(tID).iShiftX_Scaled = 0;
                    Game.getProvince(this.lMoveUnits.get(i).getFromProvinceID()).getArmy(tID).iShiftY_Scaled = 0;
                }
                this.removeMove(i);
                return true;
            }
        }
        return false;
    }
    
    public final void clearMoveUnits() {
        this.lMoveUnits.clear();
        this.iMoveUnitsSize = this.lMoveUnits.size();
    }
    
    public final MoveUnits getMoveUnits(final int i) {
        return this.lMoveUnits.get(i);
    }
    
    public final void updateMoveUnits_Load(final SaveGameManager.Save_Civ_MoveUnits civMoveUnits, final boolean nInRetreat, final boolean nInBattle) {
        for (int i = this.iMoveUnitsSize - 1; i >= 0; --i) {
            if (this.lMoveUnits.get(i).key.equals(civMoveUnits.k)) {
                this.lMoveUnits.get(i).currentMovementProgressWidth = civMoveUnits.m;
                this.lMoveUnits.get(i).inRetreat = nInRetreat;
                this.lMoveUnits.get(i).inBattle = nInBattle;
                return;
            }
        }
    }
    
    public final boolean isArmyMovingToProvince(final String armyKey, final int toProvinceID) {
        for (int i = 0; i < this.iMoveUnitsSize; ++i) {
            if (this.lMoveUnits.get(i).getToProvinceLastID() == toProvinceID && this.lMoveUnits.get(i).key.equals(armyKey)) {
                return true;
            }
        }
        return false;
    }
    
    public final int getMoveUnitsSize() {
        return this.iMoveUnitsSize;
    }
    
    public final boolean isArmyMovingToProvince_MoveUnits(final int provinceID) {
        for (int i = 0; i < this.iMoveUnitsSize; ++i) {
            if (this.lMoveUnits.get(i).getToProvinceLastID() == provinceID) {
                return true;
            }
        }
        return false;
    }
    
    public final boolean isInMoveUnits_ArmyKey(final String key) {
        for (int i = 0; i < this.iMoveUnitsSize; ++i) {
            if (this.lMoveUnits.get(i).key.equals(key)) {
                return true;
            }
        }
        return false;
    }
    
    public final int armyMovingToProvince_MoveUnits(final int provinceID) {
        int out = 0;
        for (int i = 0; i < this.iMoveUnitsSize; ++i) {
            if (this.lMoveUnits.get(i).getToProvinceLastID() == provinceID) {
                final ArmyDivision armyDivision = Game.getProvince(this.lMoveUnits.get(i).getFromProvinceID()).getArmy(this.lMoveUnits.get(i).key);
                if (armyDivision != null) {
                    out += armyDivision.iArmyRegimentSize;
                }
            }
        }
        return out;
    }
    
    public final boolean recruitArmy(final ArmyRecruit nArmyRecruit) {
        try {
            if (this.fGold >= ArmyManager.getRecruitmentCost(this.getCivID(), nArmyRecruit.provinceID, nArmyRecruit.unitID, nArmyRecruit.armyID)) {
                if (this.fManpower < Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE) {
                    return false;
                }
                int nID = -1;
                nArmyRecruit.cost = ArmyManager.getRecruitmentCost(this.getCivID(), nArmyRecruit.provinceID, nArmyRecruit.unitID, nArmyRecruit.armyID);
                nArmyRecruit.timeLeft = ArmyManager.getRecruitmentTime(this.getCivID(), nArmyRecruit.provinceID, nArmyRecruit.unitID, nArmyRecruit.armyID);
                this.fGold -= nArmyRecruit.cost;
                this.setManpower(this.fManpower - Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE);
                for (int i = 0; i < this.iArmyRecruitSize; ++i) {
                    if (this.lArmyRecruit.get(i).get(0).provinceID == nArmyRecruit.provinceID) {
                        nID = i;
                        break;
                    }
                }
                if (nID >= 0) {
                    this.lArmyRecruit.get(nID).add(nArmyRecruit);
                }
                else {
                    final ArrayList<ArmyRecruit> nListArmyRecruit = new ArrayList<ArmyRecruit>();
                    nListArmyRecruit.add(nArmyRecruit);
                    this.lArmyRecruit.add(nListArmyRecruit);
                }
                this.iArmyRecruitSize = this.lArmyRecruit.size();
                this.iArmyRecruitSize_Total = 0;
                for (int i = 0; i < this.iArmyRecruitSize; ++i) {
                    this.iArmyRecruitSize_Total += this.lArmyRecruit.get(i).size();
                }
                if (this.getCivID() == Game.player.iCivID) {
                    Game.menuManager.addRebuildInGame_RightQueue();
                }
                return true;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return false;
    }
    
    public final void addRecruitArmy_Load(final ArmyRecruit nArmyRecruit) {
        int nID = -1;
        for (int i = 0; i < this.iArmyRecruitSize; ++i) {
            if (this.lArmyRecruit.get(i).get(0).provinceID == nArmyRecruit.provinceID) {
                nID = i;
                break;
            }
        }
        if (nID >= 0) {
            this.lArmyRecruit.get(nID).add(nArmyRecruit);
        }
        else {
            final ArrayList<ArmyRecruit> nListArmyRecruit = new ArrayList<ArmyRecruit>();
            nListArmyRecruit.add(nArmyRecruit);
            this.lArmyRecruit.add(nListArmyRecruit);
        }
        this.iArmyRecruitSize = this.lArmyRecruit.size();
    }
    
    public final void addRecruitArmy_LoadUpdateSize() {
        this.iArmyRecruitSize_Total = 0;
        for (int i = 0; i < this.iArmyRecruitSize; ++i) {
            this.iArmyRecruitSize_Total += this.lArmyRecruit.get(i).size();
        }
    }
    
    public final boolean cancelRecruitArmy(final ArmyRecruit nArmyRecruit) {
        return this.cancelRecruitArmy(nArmyRecruit.provinceID);
    }
    
    public final boolean cancelRecruitArmy(final int nProvinceID) {
        try {
            for (int i = this.iArmyRecruitSize - 1; i >= 0; --i) {
                if (this.lArmyRecruit.get(i).get(0).provinceID == nProvinceID) {
                    final int tRemoveID = this.lArmyRecruit.get(i).size() - 1;
                    this.fGold += this.lArmyRecruit.get(i).get(tRemoveID).cost;
                    this.setManpower(this.fManpower + Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE);
                    this.lArmyRecruit.get(i).remove(tRemoveID);
                    if (this.lArmyRecruit.get(i).size() == 0) {
                        this.lArmyRecruit.remove(i);
                        this.iArmyRecruitSize = this.lArmyRecruit.size();
                    }
                    this.iArmyRecruitSize_Total = 0;
                    for (int o = 0; o < this.iArmyRecruitSize; ++o) {
                        this.iArmyRecruitSize_Total += this.lArmyRecruit.get(o).size();
                    }
                    if (this.getCivID() == Game.player.iCivID) {
                        Game.menuManager.addRebuildInGame_RightQueue();
                    }
                    return true;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return false;
    }
    
    public final void cancelRecruitArmy_All() {
        try {
            for (int i = this.iArmyRecruitSize - 1; i >= 0; --i) {
                for (int j = this.lArmyRecruit.get(i).size() - 1; j >= 0; --j) {
                    this.fGold += this.lArmyRecruit.get(i).get(j).cost;
                    this.setManpower(this.fManpower + Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE);
                    this.lArmyRecruit.get(i).remove(j);
                    if (this.lArmyRecruit.get(i).size() == 0) {
                        this.lArmyRecruit.remove(i);
                        this.iArmyRecruitSize = this.lArmyRecruit.size();
                    }
                    this.iArmyRecruitSize_Total = 0;
                    for (int o = 0; o < this.iArmyRecruitSize; ++o) {
                        this.iArmyRecruitSize_Total += this.lArmyRecruit.get(o).size();
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateRecruitArmy(final int i, final int j) {
        final ArmyRecruit armyRecruit28;
        final ArmyRecruit armyRecruit27;
        final ArmyRecruit armyRecruit26;
        final ArmyRecruit armyRecruit25;
        final ArmyRecruit armyRecruit24;
        final ArmyRecruit armyRecruit23;
        final ArmyRecruit armyRecruit29;
        final ArmyRecruit armyRecruit22 = armyRecruit29 = (armyRecruit23 = (armyRecruit24 = (armyRecruit25 = (armyRecruit26 = (armyRecruit27 = (armyRecruit28 = this.lArmyRecruit.get(i).get(j)))))));
        --armyRecruit29.timeLeft;
        if (armyRecruit28.timeLeft > 0) {
            return;
        }
        if (armyRecruit28.toArmyKey != null) {
            try {
                final Game.ArmyPos nArmyPos = Game.findArmy_FullCheck(this.getCivID(), armyRecruit28.toArmyKey);
                if (nArmyPos != null) {
                    if (nArmyPos.iProvinceID >= 0 && nArmyPos.iID >= 0 && Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).civID == this.getCivID()) {
                        Game.getProvince(nArmyPos.iProvinceID).getArmy(nArmyPos.iID).addRegiment(new ArmyRegiment(armyRecruit28.unitID, armyRecruit28.armyID));
                        this.lArmyRecruit.get(i).remove(j);
                        if (this.lArmyRecruit.get(i).size() == 0) {
                            this.lArmyRecruit.remove(i);
                            this.iArmyRecruitSize = this.lArmyRecruit.size();
                        }
                        this.iArmyRecruitSize_Total = 0;
                        for (int o = 0; o < this.iArmyRecruitSize; ++o) {
                            this.iArmyRecruitSize_Total += this.lArmyRecruit.get(o).size();
                        }
                        Game.gameThread.addCivUpdateArmyMaintenance(this.getCivID());
                        if (this.getCivID() == Game.player.iCivID) {
                            final PlayerStats playerStats81;
                            final PlayerStats playerStats80;
                            final PlayerStats playerStats79;
                            final PlayerStats playerStats78;
                            final PlayerStats playerStats77;
                            final PlayerStats playerStats106;
                            final PlayerStats playerStats76 = playerStats106 = (playerStats77 = (playerStats78 = (playerStats79 = (playerStats80 = (playerStats81 = Game.player.playerStats)))));
                            ++playerStats106.recruitedRegiments;
                            final Stats civStats7;
                            final Stats civStats6;
                            final Stats stats34;
                            final Stats stats33;
                            final Stats stats32;
                            final Stats stats51;
                            final Stats stats31 = stats51 = (stats32 = (stats33 = (stats34 = (civStats6 = (civStats7 = Game.stats.civStats)))));
                            ++stats51.rr;
                            Game.menuManager.addRebuildInGame_RightQueue();
                        }
                        return;
                    }
                }
                else if (this.lCreateNewArmy.containsKey(armyRecruit28.toArmyKey)) {
                    int nProvinceID = this.lCreateNewArmy.get(armyRecruit28.toArmyKey);
                    if (nProvinceID >= 0) {
                        if (this.getNumOfProvinces() > 0) {
                            final List<ArmyRegiment> nArmyRegiment = new ArrayList<ArmyRegiment>();
                            nArmyRegiment.add(new ArmyRegiment(armyRecruit28.unitID, armyRecruit28.armyID));
                            if (Game.getProvince(nProvinceID).getCivID() != this.getCivID() && Game.getCiv(Game.getProvince(nProvinceID).getCivID()).getPuppetOfCivID() != this.getCivID()) {
                                if (this.getCapitalProvinceID() >= 0 && Game.getProvince(this.getCapitalProvinceID()).getCivID() == this.getCivID()) {
                                    nProvinceID = this.getCapitalProvinceID();
                                }
                                else {
                                    nProvinceID = this.getProvinceID(0);
                                }
                            }
                            final ArmyDivision nArmyDivision = new ArmyDivision(this.getCivID(), nProvinceID, nArmyRegiment);
                            nArmyDivision.key = armyRecruit28.toArmyKey;
                            Game.getProvince(nProvinceID).addArmy(nArmyDivision);
                        }
                        this.lCreateNewArmy.remove(armyRecruit28.toArmyKey);
                        this.lArmyRecruit.get(i).remove(j);
                        if (this.lArmyRecruit.get(i).size() == 0) {
                            this.lArmyRecruit.remove(i);
                            this.iArmyRecruitSize = this.lArmyRecruit.size();
                        }
                        this.iArmyRecruitSize_Total = 0;
                        for (int o2 = 0; o2 < this.iArmyRecruitSize; ++o2) {
                            this.iArmyRecruitSize_Total += this.lArmyRecruit.get(o2).size();
                        }
                        Game.gameThread.addCivUpdateArmyMaintenance(this.getCivID());
                        if (this.getCivID() == Game.player.iCivID) {
                            final PlayerStats playerStats87;
                            final PlayerStats playerStats86;
                            final PlayerStats playerStats85;
                            final PlayerStats playerStats84;
                            final PlayerStats playerStats83;
                            final PlayerStats playerStats107;
                            final PlayerStats playerStats82 = playerStats107 = (playerStats83 = (playerStats84 = (playerStats85 = (playerStats86 = (playerStats87 = Game.player.playerStats)))));
                            ++playerStats107.recruitedRegiments;
                            final Stats civStats9;
                            final Stats civStats8;
                            final Stats stats38;
                            final Stats stats37;
                            final Stats stats36;
                            final Stats stats52;
                            final Stats stats35 = stats52 = (stats36 = (stats37 = (stats38 = (civStats8 = (civStats9 = Game.stats.civStats)))));
                            ++stats52.rr;
                            Game.menuManager.addRebuildInGame_RightQueue();
                        }
                        return;
                    }
                }
                else {
                    int nProvinceID = armyRecruit28.provinceID;
                    if (nProvinceID >= 0) {
                        if (this.getNumOfProvinces() > 0) {
                            final List<ArmyRegiment> nArmyRegiment = new ArrayList<ArmyRegiment>();
                            nArmyRegiment.add(new ArmyRegiment(armyRecruit28.unitID, armyRecruit28.armyID));
                            if (Game.getProvince(nProvinceID).getCivID() != this.getCivID() && Game.getCiv(Game.getProvince(nProvinceID).getCivID()).getPuppetOfCivID() != this.getCivID()) {
                                if (this.getCapitalProvinceID() >= 0 && Game.getProvince(this.getCapitalProvinceID()).getCivID() == this.getCivID()) {
                                    nProvinceID = this.getCapitalProvinceID();
                                }
                                else {
                                    nProvinceID = this.getProvinceID(0);
                                }
                            }
                            final ArmyDivision nArmyDivision = new ArmyDivision(this.getCivID(), nProvinceID, nArmyRegiment);
                            nArmyDivision.key = armyRecruit28.toArmyKey;
                            Game.getProvince(nProvinceID).addArmy(nArmyDivision);
                        }
                        this.lArmyRecruit.get(i).remove(j);
                        if (this.lArmyRecruit.get(i).size() == 0) {
                            this.lArmyRecruit.remove(i);
                            this.iArmyRecruitSize = this.lArmyRecruit.size();
                        }
                        this.iArmyRecruitSize_Total = 0;
                        for (int o2 = 0; o2 < this.iArmyRecruitSize; ++o2) {
                            this.iArmyRecruitSize_Total += this.lArmyRecruit.get(o2).size();
                        }
                        Game.gameThread.addCivUpdateArmyMaintenance(this.getCivID());
                        if (this.getCivID() == Game.player.iCivID) {
                            final PlayerStats playerStats93;
                            final PlayerStats playerStats92;
                            final PlayerStats playerStats91;
                            final PlayerStats playerStats90;
                            final PlayerStats playerStats89;
                            final PlayerStats playerStats108;
                            final PlayerStats playerStats88 = playerStats108 = (playerStats89 = (playerStats90 = (playerStats91 = (playerStats92 = (playerStats93 = Game.player.playerStats)))));
                            ++playerStats108.recruitedRegiments;
                            final Stats civStats11;
                            final Stats civStats10;
                            final Stats stats42;
                            final Stats stats41;
                            final Stats stats40;
                            final Stats stats53;
                            final Stats stats39 = stats53 = (stats40 = (stats41 = (stats42 = (civStats10 = (civStats11 = Game.stats.civStats)))));
                            ++stats53.rr;
                            Game.menuManager.addRebuildInGame_RightQueue();
                        }
                        return;
                    }
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
        for (int o3 = 0; o3 < Game.getProvince(armyRecruit28.provinceID).getArmySize(); ++o3) {
            if (Game.getProvince(armyRecruit28.provinceID).getArmy(o3).civID == this.getCivID()) {
                Game.getProvince(armyRecruit28.provinceID).getArmy(o3).addRegiment(new ArmyRegiment(armyRecruit28.unitID, armyRecruit28.armyID));
                this.lArmyRecruit.get(i).remove(j);
                if (this.lArmyRecruit.get(i).size() == 0) {
                    this.lArmyRecruit.remove(i);
                    this.iArmyRecruitSize = this.lArmyRecruit.size();
                }
                this.iArmyRecruitSize_Total = 0;
                for (int a = 0; a < this.iArmyRecruitSize; ++a) {
                    this.iArmyRecruitSize_Total += this.lArmyRecruit.get(a).size();
                }
                Game.gameThread.addCivUpdateArmyMaintenance(this.getCivID());
                if (this.getCivID() == Game.player.iCivID) {
                    final PlayerStats playerStats99;
                    final PlayerStats playerStats98;
                    final PlayerStats playerStats97;
                    final PlayerStats playerStats96;
                    final PlayerStats playerStats95;
                    final PlayerStats playerStats109;
                    final PlayerStats playerStats94 = playerStats109 = (playerStats95 = (playerStats96 = (playerStats97 = (playerStats98 = (playerStats99 = Game.player.playerStats)))));
                    ++playerStats109.recruitedRegiments;
                    final Stats civStats13;
                    final Stats civStats12;
                    final Stats stats46;
                    final Stats stats45;
                    final Stats stats44;
                    final Stats stats54;
                    final Stats stats43 = stats54 = (stats44 = (stats45 = (stats46 = (civStats12 = (civStats13 = Game.stats.civStats)))));
                    ++stats54.rr;
                    Game.menuManager.addRebuildInGame_RightQueue();
                }
                return;
            }
        }
        if (this.getNumOfProvinces() > 0) {
            int recruitInProvinceID = armyRecruit28.provinceID;
            if ((Game.getProvince(recruitInProvinceID).getCivID() != this.getCivID() && Game.getCiv(Game.getProvince(recruitInProvinceID).getCivID()).getPuppetOfCivID() != this.getCivID()) || Game.getProvince(recruitInProvinceID).isOccupied()) {
                if (this.getCapitalProvinceID() >= 0 && Game.getProvince(this.getCapitalProvinceID()).getCivID() == this.getCivID() && !Game.getProvince(this.getCapitalProvinceID()).isOccupied()) {
                    recruitInProvinceID = this.getCapitalProvinceID();
                }
                else {
                    recruitInProvinceID = -1;
                    for (int p = 0; p < this.getNumOfProvinces(); ++p) {
                        if (Game.getProvince(this.getProvinceID(p)).getCivID() == this.getCivID() && !Game.getProvince(this.getProvinceID(p)).isOccupied()) {
                            recruitInProvinceID = this.getProvinceID(p);
                            break;
                        }
                    }
                }
            }
            if (recruitInProvinceID >= 0) {
                final List<ArmyRegiment> nArmyRegiment2 = new ArrayList<ArmyRegiment>();
                nArmyRegiment2.add(new ArmyRegiment(armyRecruit28.unitID, armyRecruit28.armyID));
                final ArmyDivision nArmyDivision2 = new ArmyDivision(this.getCivID(), recruitInProvinceID, nArmyRegiment2);
                Game.getProvince(recruitInProvinceID).addArmy(nArmyDivision2);
            }
        }
        this.lArmyRecruit.get(i).remove(j);
        if (this.lArmyRecruit.get(i).size() == 0) {
            this.lArmyRecruit.remove(i);
            this.iArmyRecruitSize = this.lArmyRecruit.size();
        }
        this.iArmyRecruitSize_Total = 0;
        for (int o3 = 0; o3 < this.iArmyRecruitSize; ++o3) {
            this.iArmyRecruitSize_Total += this.lArmyRecruit.get(o3).size();
        }
        Game.gameThread.addCivUpdateArmyMaintenance(this.getCivID());
        if (this.getCivID() == Game.player.iCivID) {
            final PlayerStats playerStats105;
            final PlayerStats playerStats104;
            final PlayerStats playerStats103;
            final PlayerStats playerStats102;
            final PlayerStats playerStats101;
            final PlayerStats playerStats110;
            final PlayerStats playerStats100 = playerStats110 = (playerStats101 = (playerStats102 = (playerStats103 = (playerStats104 = (playerStats105 = Game.player.playerStats)))));
            ++playerStats110.recruitedRegiments;
            final Stats civStats15;
            final Stats civStats14;
            final Stats stats50;
            final Stats stats49;
            final Stats stats48;
            final Stats stats55;
            final Stats stats47 = stats55 = (stats48 = (stats49 = (stats50 = (civStats14 = (civStats15 = Game.stats.civStats)))));
            ++stats55.rr;
            Game.menuManager.addRebuildInGame_RightQueue();
        }
    }
    
    public int getRecruitArmyInProvinceID(final int nProvinceID) {
        try {
            for (int i = 0; i < this.iArmyRecruitSize; ++i) {
                if (this.lArmyRecruit.get(i).get(0).provinceID == nProvinceID) {
                    return i;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return -1;
    }
    
    public int getRecruitArmyInProgress(final String key) {
        int out = 0;
        for (int i = 0; i < this.iArmyRecruitSize; ++i) {
            if (this.lArmyRecruit.get(i).get(0).toArmyKey.equals(key)) {
                out += this.lArmyRecruit.get(i).size();
            }
        }
        return out;
    }
    
    public boolean isRecruitArmyInProgress(final String key) {
        for (int i = 0; i < this.iArmyRecruitSize; ++i) {
            if (this.lArmyRecruit.get(i).get(0).toArmyKey.equals(key)) {
                return true;
            }
        }
        return false;
    }
    
    public int getArmyRecruitSize() {
        return this.iArmyRecruitSize;
    }
    
    public int getLegaciesSize() {
        return this.iLegaciesSize;
    }
    
    public int getLegacyLevel(final int legacyID) {
        for (int i = 0; i < this.iLegaciesSize; ++i) {
            if (this.legacies.get(i).id == legacyID) {
                return this.legacies.get(i).lvl;
            }
        }
        return -1;
    }
    
    public final boolean addLegacy(final int legacyID, final int levelID) {
        for (int i = 0; i < this.iLegaciesSize; ++i) {
            if (this.legacies.get(i).id == legacyID) {
                this.legacies.get(i).lvl = Math.max(this.legacies.get(i).lvl, levelID);
                return false;
            }
        }
        this.legacies.add(new CivilizationLegacy(legacyID, levelID));
        this.iLegaciesSize = this.legacies.size();
        return true;
    }
    
    public final void addLegacy_Load(final int legacyID, final int levelID) {
        this.legacies.add(new CivilizationLegacy(legacyID, levelID));
        this.iLegaciesSize = this.legacies.size();
        LegacyManager.updateCivBonuses(legacyID, levelID, this.getCivID(), true);
    }
    
    public final boolean unlockLegacy(final int legacyID) {
        int i = 0;
        while (i < this.iLegaciesSize) {
            if (this.legacies.get(i).id == legacyID) {
                if (this.legacies.get(i).lvl + 1 >= LegacyManager.legacies.get(legacyID).CostLegacy.length) {
                    return false;
                }
                if (this.fLegacy < LegacyManager.legacies.get(legacyID).CostLegacy[this.legacies.get(i).lvl + 1]) {
                    return false;
                }
                this.fLegacy -= LegacyManager.legacies.get(legacyID).CostLegacy[this.legacies.get(i).lvl + 1];
                LegacyManager.updateCivBonuses(legacyID, this.legacies.get(i).lvl = Math.min(this.legacies.get(i).lvl + 1, LegacyManager.legacies.get(legacyID).CostLegacy.length - 1), this.getCivID());
                return true;
            }
            else {
                ++i;
            }
        }
        if (this.fLegacy < LegacyManager.legacies.get(legacyID).CostLegacy[0]) {
            return false;
        }
        this.fLegacy -= LegacyManager.legacies.get(legacyID).CostLegacy[0];
        this.legacies.add(new CivilizationLegacy(legacyID, 0));
        this.iLegaciesSize = this.legacies.size();
        LegacyManager.updateCivBonuses(legacyID, 0, this.getCivID());
        return true;
    }
    
    public CivilizationLegacy getCivLegacy(final int i) {
        try {
            return this.legacies.get(i);
        }
        catch (final Exception ex) {
            return new CivilizationLegacy(0, 0);
        }
    }
    
    public int getLegaciesUnlocked() {
        int out = 0;
        for (int i = 0; i < this.iLegaciesSize; ++i) {
            out += this.legacies.get(i).lvl + 1;
        }
        return out;
    }
    
    public int getLegaciesUnlocked(final int groupID) {
        int out = 0;
        for (int i = 0; i < this.iLegaciesSize; ++i) {
            if (LegacyManager.legacies.get(this.legacies.get(i).id).GroupID == groupID) {
                out += this.legacies.get(i).lvl + 1;
            }
        }
        return out;
    }
    
    public ArmyGeneral getGeneralNotAssigned(final int i) {
        return this.lGeneralsNotAssigned.get(i);
    }
    
    public final void addGeneral(final ArmyGeneral armyGeneral) {
        this.lGeneralsNotAssigned.add(armyGeneral);
        this.iGeneralsSize = this.lGeneralsNotAssigned.size();
    }
    
    public final boolean assignGeneralToArmy(final int nProvinceID, final int nArmyID) {
        return this.iGeneralsSize > 0 && this.assignGeneralToArmy(nProvinceID, nArmyID, this.lGeneralsNotAssigned.get(Game.oR.nextInt(this.lGeneralsNotAssigned.size())).key);
    }
    
    public final boolean assignGeneralToArmy(final int nProvinceID, final int nArmyID, final String generalKey) {
        for (int i = this.iGeneralsSize - 1; i >= 0; --i) {
            if (this.lGeneralsNotAssigned.get(i).key.equals(generalKey)) {
                this.assignGeneralToArmy(nProvinceID, nArmyID, i);
                return true;
            }
        }
        return false;
    }
    
    public final void assignGeneralToArmy(final int nProvinceID, final int nArmyID, final int generalID) {
        try {
            if (Game.getProvince(nProvinceID).getArmy(nArmyID).armyGeneral != null) {
                this.addGeneral(Game.getProvince(nProvinceID).getArmy(nArmyID).armyGeneral);
            }
            Game.getProvince(nProvinceID).getArmy(nArmyID).setArmyGeneral(this.lGeneralsNotAssigned.get(generalID));
            this.lGeneralsNotAssigned.remove(generalID);
            this.iGeneralsSize = this.lGeneralsNotAssigned.size();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void update_ChanceOfGeneral_NotAssigned() {
        try {
            for (int i = this.iGeneralsSize - 1; i >= 0; --i) {
                if (this.lGeneralsNotAssigned.get(i).d % 12 + 1 == Game_Calendar.currentMonth && RulersManager.characterDies(this.iCivID, this.lGeneralsNotAssigned.get(i).y)) {
                    if (this.iCivID == Game.player.iCivID) {
                        Game.player.addNotification(new Notification(Notification.Notification_Type.GENERAL_DIED, Game.lang.get("GeneralDied") + ": " + this.lGeneralsNotAssigned.get(i).n, Images.general, Game_Calendar.TURN_ID, Notification.Notification_BG.RED, Game.player.iCivID));
                        Game.addSimpleTask(new Game.SimpleTask("rebuildGenerals") {
                            @Override
                            public void update() {
                                if (Game.menuManager.getVisibleInGame_Generals()) {
                                    Game.menuManager.rebuildInGame_Generals();
                                    Game.menuManager.setVisibleInGame_Generals(true);
                                    InGame_Generals.lTime = 0L;
                                }
                            }
                        });
                    }
                    this.lGeneralsNotAssigned.remove(i);
                    this.iGeneralsSize = this.lGeneralsNotAssigned.size();
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void buildTechTree_UnlockTechnologies(int iTechID) {
        iTechID = Math.max(0, Math.min(TechnologyTree.iTechnologySize, iTechID));
        this.lTechResearched.set(iTechID, true);
        this.buildUnlockTechnologies_TechQueue1(iTechID);
        this.buildUnlockTechnologies_TechQueue2(iTechID);
    }
    
    private final void buildUnlockTechnologies_TechQueue1(final int iTechID) {
        if (TechnologyTree.lTechnology.get(iTechID).RequiredTech >= 0 && !this.getTechResearched(TechnologyTree.lTechnology.get(iTechID).RequiredTech)) {
            this.lTechResearched.set(TechnologyTree.lTechnology.get(iTechID).RequiredTech, true);
            this.buildUnlockTechnologies_TechQueue1(TechnologyTree.lTechnology.get(iTechID).RequiredTech);
            this.buildUnlockTechnologies_TechQueue2(TechnologyTree.lTechnology.get(iTechID).RequiredTech);
        }
    }
    
    private final void buildUnlockTechnologies_TechQueue2(final int iTechID) {
        if (TechnologyTree.lTechnology.get(iTechID).RequiredTech2 >= 0 && !Game.getCiv(this.iCivID).getTechResearched(TechnologyTree.lTechnology.get(iTechID).RequiredTech2)) {
            this.lTechResearched.set(TechnologyTree.lTechnology.get(iTechID).RequiredTech2, true);
            this.buildUnlockTechnologies_TechQueue1(TechnologyTree.lTechnology.get(iTechID).RequiredTech2);
            this.buildUnlockTechnologies_TechQueue2(TechnologyTree.lTechnology.get(iTechID).RequiredTech2);
        }
    }
    
    public final void setTechnologyResearched(final int iTechID, final boolean researched) {
        this.lTechResearched.set(iTechID, researched);
        if (TechnologyTree.lTechnology.get(iTechID).UnlocksNukes) {
            this.canBuildNuke = true;
        }
        if (TechnologyTree.lTechnology.get(iTechID).UnlocksAccessToTheSea) {
            this.canAccessSea = true;
        }
        if (TechnologyTree.lTechnology.get(iTechID).UnlocksColonization) {
            this.canColonize = true;
        }
    }
    
    public void initTechTree() {
        this.lTechResearched.clear();
        for (int i = 0; i < TechnologyTree.iTechnologySize; ++i) {
            this.lTechResearched.add(false);
        }
    }
    
    public void buildTechTree() {
        this.lTechResearched.clear();
        this.unlockedUnits.clear();
        this.unlockedBuildings.clear();
        for (int i = 0; i < TechnologyTree.iTechnologySize; ++i) {
            this.lTechResearched.add(false);
        }
        if (this.scenarioEditorData.TechnologyID != MapScenarios.DEFAULT_VALUE) {
            if (this.scenarioEditorData.TechnologyID >= 0) {
                this.buildTechTree_UnlockTechnologies(this.scenarioEditorData.TechnologyID);
            }
            else if (Game.mapScenarios.details.get(Game.scenarioID).CivDefault_Technology >= 0) {
                this.buildTechTree_UnlockTechnologies(Game.mapScenarios.details.get(Game.scenarioID).CivDefault_Technology);
            }
        }
        else if (Game.mapScenarios.details.get(Game.scenarioID).CivDefault_Technology >= 0) {
            this.buildTechTree_UnlockTechnologies(Game.mapScenarios.details.get(Game.scenarioID).CivDefault_Technology);
        }
        for (int i = 0; i < BuildingsManager.buildingsSize; ++i) {
            for (int j = 0; j < BuildingsManager.buildingSize.get(i); ++j) {
                if (BuildingsManager.buildings.get(i).RequiredTechID[j] < 0) {
                    this.unlockedBuildings.add(new TechnologyTree.Building(i, j));
                }
            }
        }
        for (int i = 0; i < ArmyManager.iUnitsTypesSize; ++i) {
            for (int j = 0; j < ArmyManager.lArmySize.get(i); ++j) {
                if (ArmyManager.lArmy.get(i).get(j).RequiredTechID < 0) {
                    this.unlockedUnits.add(new TechnologyTree.Unit(i, j));
                }
            }
        }
        for (int i = 0; i < TechnologyTree.iTechnologySize; ++i) {
            if (this.lTechResearched.get(i)) {
                this.addTechnology(i, true);
            }
        }
        this.updateBestUnits();
        this.initMaxLaws();
    }
    
    public void buildTechTree_Load() {
        this.unlockedUnits.clear();
        this.unlockedBuildings.clear();
        for (int i = 0; i < BuildingsManager.buildingsSize; ++i) {
            for (int j = 0; j < BuildingsManager.buildingSize.get(i); ++j) {
                if (BuildingsManager.buildings.get(i).RequiredTechID[j] < 0) {
                    this.unlockedBuildings.add(new TechnologyTree.Building(i, j));
                }
            }
        }
        for (int i = 0; i < ArmyManager.iUnitsTypesSize; ++i) {
            for (int j = 0; j < ArmyManager.lArmySize.get(i); ++j) {
                if (ArmyManager.lArmy.get(i).get(j).RequiredTechID < 0) {
                    this.unlockedUnits.add(new TechnologyTree.Unit(i, j));
                }
            }
        }
        for (int t = 0; t < TechnologyTree.iTechnologySize; ++t) {
            if (this.getTechResearched(t)) {
                for (int k = 0; k < TechnologyTree.lTechUnlocksBuildings.get(t).size(); ++k) {
                    this.unlockedBuildings.add(TechnologyTree.lTechUnlocksBuildings.get(t).get(k));
                }
                for (int k = 0; k < TechnologyTree.lTechUnlocksUnits.get(t).size(); ++k) {
                    this.unlockedUnits.add(TechnologyTree.lTechUnlocksUnits.get(t).get(k));
                }
            }
        }
        for (int i = 0; i < TechnologyTree.iTechnologySize; ++i) {
            if (this.lTechResearched.get(i)) {
                this.updateTechnologyBonuses(i);
            }
        }
        this.updateBestUnits();
    }
    
    public final int getActiveTechResearch() {
        return this.civData4.e;
    }
    
    public final void setActiveTechResearch(final int iTechID) {
        this.civData4.e = iTechID;
        if (iTechID < 0) {
            return;
        }
        for (int i = this.lResearching.size() - 1; i >= 0; --i) {
            if (this.lResearching.get(i).iTechID == iTechID) {
                return;
            }
        }
        this.lResearching.add(new TechnologyResearch(iTechID));
    }
    
    public final void addResearchProgress(final float nProgress) {
        this.addResearchProgress(this.getActiveTechResearch(), nProgress);
    }
    
    public final void addResearchProgress(final int iTechID, final float nProgress) {
        if (iTechID >= 0) {
            for (int i = this.lResearching.size() - 1; i >= 0; --i) {
                if (this.lResearching.get(i).iTechID == iTechID) {
                    this.lResearching.get(i).fProgress = Math.max(0.0f, this.lResearching.get(i).fProgress + nProgress);
                    if (this.lResearching.get(i).fProgress >= TechnologyTree.getResearchCost(this.lResearching.get(i).iTechID, this.getCivID())) {
                        this.addTechnology(iTechID, false);
                    }
                    return;
                }
            }
            this.lResearching.add(new TechnologyResearch(iTechID));
            final TechnologyResearch technologyResearch21;
            final TechnologyResearch technologyResearch20;
            final TechnologyResearch technologyResearch19;
            final TechnologyResearch technologyResearch18;
            final TechnologyResearch technologyResearch17;
            final TechnologyResearch technologyResearch22;
            final TechnologyResearch technologyResearch16 = technologyResearch22 = (technologyResearch17 = (technologyResearch18 = (technologyResearch19 = (technologyResearch20 = (technologyResearch21 = this.lResearching.get(this.lResearching.size() - 1))))));
            technologyResearch22.fProgress += nProgress;
        }
    }
    
    public void addTechnology(final int iTechID, final boolean loadScenario) {
        if (!this.lTechResearched.get(iTechID) || TechnologyTree.lTechnology.get(iTechID).Repeatable || loadScenario) {
            this.lTechResearched.set(iTechID, true);
            if (!loadScenario) {
                this.setAdvantagePoints(this.getAdvantagePoints() + 1);
            }
            for (int i = 0; i < TechnologyTree.lTechUnlocksBuildings.get(iTechID).size(); ++i) {
                this.unlockedBuildings.add(TechnologyTree.lTechUnlocksBuildings.get(iTechID).get(i));
            }
            boolean updateBestUnits = false;
            for (int j = 0; j < TechnologyTree.lTechUnlocksUnits.get(iTechID).size(); ++j) {
                this.unlockedUnits.add(TechnologyTree.lTechUnlocksUnits.get(iTechID).get(j));
                updateBestUnits = true;
            }
            if (!loadScenario && updateBestUnits) {
                this.updateBestUnits();
            }
            for (int j = this.lResearching.size() - 1; j >= 0; --j) {
                if (this.lResearching.get(j).iTechID == iTechID) {
                    this.lResearching.remove(j);
                    break;
                }
            }
            if (!TechnologyTree.lTechnology.get(iTechID).Repeatable) {
                this.civData4.e = -1;
            }
            this.iAlternativeTechResearch = -1;
            this.updateTechnologyBonuses(iTechID);
            if (!loadScenario) {
                if (TechnologyTree.lTechnology.get(iTechID).Legacy != 0) {
                    this.addLegacy((float)TechnologyTree.lTechnology.get(iTechID).Legacy);
                }
                if (TechnologyTree.lTechnology.get(iTechID).Gold != 0) {
                    this.fGold += TechnologyTree.lTechnology.get(iTechID).Gold;
                }
                Game.gameThread.addCivUpdateLegacyPerMonth(this.getCivID());
                if (this.getCivID() == Game.player.iCivID) {
                    if (Game.menuManager.getVisibleInGame_TechnologyChoose() && InGame_TechnologyChoose.IN_TECHNOLOGY_CHOOSE) {
                        Game.addSimpleTask(new Game.SimpleTask("rebuildTechTree") {
                            @Override
                            public void update() {
                                Game.menuManager.rebuildInGame_TechnologyChoose(true, false);
                                InGame_TechnologyChoose.lTime = 0L;
                            }
                        });
                    }
                    if (Game.menuManager.getVisibleInGame_TechnologyTree()) {
                        Game.addSimpleTask(new Game.SimpleTask("rebuildTechTree") {
                            @Override
                            public void update() {
                                Game.menuManager.rebuildInGame_TechnologyTree(true, false);
                                InGame_TechnologyTree.lTime = 0L;
                            }
                        });
                    }
                    if (Game.menuManager.getVisibleInGame_Court() && InGame_CourtOptions.iActiveID == InGame_CourtOptions.buildID && Game.mapModes.MODE_BUILDING != Game.mapModes.iActiveMapModeID) {
                        Game.addSimpleTask(new Game.SimpleTask("rebuildBuildings2SavePos") {
                            @Override
                            public void update() {
                                Game.menuManager.rebuildInGame_Buildings2SavePos();
                                Game.menuManager.setVisibleInGame_Court(true);
                                InGame_Court.lTime = 0L;
                            }
                        });
                    }
                    InGame_Info.iCivID = Game.player.iCivID;
                    InGame_Info.iCivID2 = 0;
                    Game.menuManager.rebuildInGame_Info(TechnologyTree.lTechnology.get(iTechID).Name, Game.lang.get("ResearchCompleted"));
                    if (Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).OVER_IMAGE_ID > 2) {
                        InGame_Info.imgID = Images.infoTechnology2;
                    }
                    else {
                        InGame_Info.imgID = Images.infoTechnology;
                    }
                    if (Game.player.playerData.techQueue.lTechQueue.size() > 0) {
                        this.setActiveTechResearch(Game.player.playerData.techQueue.getTechQueue());
                    }
                    Game.player.currSituation.updateCurrentSituation();
                }
            }
            if (!loadScenario && this.getCivID() != Game.player.iCivID) {
                AI_UnlockedTechnology.unlockedTechnology(this.getCivID(), iTechID);
            }
        }
    }
    
    public void updateTechnologyBonuses(final int iTechID) {
        if (TechnologyTree.lTechnology.get(iTechID).BattleWidth != 0) {
            final CivilizationBonuses civBonuses12;
            final CivilizationBonuses civBonuses11;
            final CivilizationBonuses civilizationBonuses64;
            final CivilizationBonuses civilizationBonuses63;
            final CivilizationBonuses civilizationBonuses62;
            final CivilizationBonuses civilizationBonuses101;
            final CivilizationBonuses civilizationBonuses61 = civilizationBonuses101 = (civilizationBonuses62 = (civilizationBonuses63 = (civilizationBonuses64 = (civBonuses11 = (civBonuses12 = this.civBonuses)))));
            civilizationBonuses101.BattleWidth += TechnologyTree.lTechnology.get(iTechID).BattleWidth;
        }
        if (TechnologyTree.lTechnology.get(iTechID).UnitsAttack != 0) {
            final CivilizationBonuses civBonuses14;
            final CivilizationBonuses civBonuses13;
            final CivilizationBonuses civilizationBonuses68;
            final CivilizationBonuses civilizationBonuses67;
            final CivilizationBonuses civilizationBonuses66;
            final CivilizationBonuses civilizationBonuses102;
            final CivilizationBonuses civilizationBonuses65 = civilizationBonuses102 = (civilizationBonuses66 = (civilizationBonuses67 = (civilizationBonuses68 = (civBonuses13 = (civBonuses14 = this.civBonuses)))));
            civilizationBonuses102.UnitsAttack += TechnologyTree.lTechnology.get(iTechID).UnitsAttack;
        }
        if (TechnologyTree.lTechnology.get(iTechID).UnitsDefense != 0) {
            final CivilizationBonuses civBonuses16;
            final CivilizationBonuses civBonuses15;
            final CivilizationBonuses civilizationBonuses72;
            final CivilizationBonuses civilizationBonuses71;
            final CivilizationBonuses civilizationBonuses70;
            final CivilizationBonuses civilizationBonuses103;
            final CivilizationBonuses civilizationBonuses69 = civilizationBonuses103 = (civilizationBonuses70 = (civilizationBonuses71 = (civilizationBonuses72 = (civBonuses15 = (civBonuses16 = this.civBonuses)))));
            civilizationBonuses103.UnitsDefense += TechnologyTree.lTechnology.get(iTechID).UnitsDefense;
        }
        if (TechnologyTree.lTechnology.get(iTechID).GeneralAttack != 0) {
            final CivilizationBonuses civBonuses18;
            final CivilizationBonuses civBonuses17;
            final CivilizationBonuses civilizationBonuses76;
            final CivilizationBonuses civilizationBonuses75;
            final CivilizationBonuses civilizationBonuses74;
            final CivilizationBonuses civilizationBonuses104;
            final CivilizationBonuses civilizationBonuses73 = civilizationBonuses104 = (civilizationBonuses74 = (civilizationBonuses75 = (civilizationBonuses76 = (civBonuses17 = (civBonuses18 = this.civBonuses)))));
            civilizationBonuses104.GeneralAttack += TechnologyTree.lTechnology.get(iTechID).GeneralAttack;
        }
        if (TechnologyTree.lTechnology.get(iTechID).GeneralDefense != 0) {
            final CivilizationBonuses civBonuses20;
            final CivilizationBonuses civBonuses19;
            final CivilizationBonuses civilizationBonuses80;
            final CivilizationBonuses civilizationBonuses79;
            final CivilizationBonuses civilizationBonuses78;
            final CivilizationBonuses civilizationBonuses105;
            final CivilizationBonuses civilizationBonuses77 = civilizationBonuses105 = (civilizationBonuses78 = (civilizationBonuses79 = (civilizationBonuses80 = (civBonuses19 = (civBonuses20 = this.civBonuses)))));
            civilizationBonuses105.GeneralDefense += TechnologyTree.lTechnology.get(iTechID).GeneralDefense;
        }
        if (TechnologyTree.lTechnology.get(iTechID).MaxMorale != 0) {
            final CivilizationBonuses civBonuses22;
            final CivilizationBonuses civBonuses21;
            final CivilizationBonuses civilizationBonuses84;
            final CivilizationBonuses civilizationBonuses83;
            final CivilizationBonuses civilizationBonuses82;
            final CivilizationBonuses civilizationBonuses106;
            final CivilizationBonuses civilizationBonuses81 = civilizationBonuses106 = (civilizationBonuses82 = (civilizationBonuses83 = (civilizationBonuses84 = (civBonuses21 = (civBonuses22 = this.civBonuses)))));
            civilizationBonuses106.MaxMorale += TechnologyTree.lTechnology.get(iTechID).MaxMorale / 100.0f;
        }
        if (TechnologyTree.lTechnology.get(iTechID).Discipline != 0) {
            final CivilizationBonuses civBonuses24;
            final CivilizationBonuses civBonuses23;
            final CivilizationBonuses civilizationBonuses88;
            final CivilizationBonuses civilizationBonuses87;
            final CivilizationBonuses civilizationBonuses86;
            final CivilizationBonuses civilizationBonuses107;
            final CivilizationBonuses civilizationBonuses85 = civilizationBonuses107 = (civilizationBonuses86 = (civilizationBonuses87 = (civilizationBonuses88 = (civBonuses23 = (civBonuses24 = this.civBonuses)))));
            civilizationBonuses107.Discipline += TechnologyTree.lTechnology.get(iTechID).Discipline / 100.0f;
        }
        if (TechnologyTree.lTechnology.get(iTechID).MaximumLevelOfCapitalCity != 0) {
            final CivilizationBonuses civBonuses26;
            final CivilizationBonuses civBonuses25;
            final CivilizationBonuses civilizationBonuses92;
            final CivilizationBonuses civilizationBonuses91;
            final CivilizationBonuses civilizationBonuses90;
            final CivilizationBonuses civilizationBonuses108;
            final CivilizationBonuses civilizationBonuses89 = civilizationBonuses108 = (civilizationBonuses90 = (civilizationBonuses91 = (civilizationBonuses92 = (civBonuses25 = (civBonuses26 = this.civBonuses)))));
            civilizationBonuses108.MaximumLevelOfCapitalCity += TechnologyTree.lTechnology.get(iTechID).MaximumLevelOfCapitalCity;
        }
        if (TechnologyTree.lTechnology.get(iTechID).MaximumLevelOfTheMilitaryAcademy != 0) {
            final CivilizationBonuses civBonuses28;
            final CivilizationBonuses civBonuses27;
            final CivilizationBonuses civilizationBonuses96;
            final CivilizationBonuses civilizationBonuses95;
            final CivilizationBonuses civilizationBonuses94;
            final CivilizationBonuses civilizationBonuses109;
            final CivilizationBonuses civilizationBonuses93 = civilizationBonuses109 = (civilizationBonuses94 = (civilizationBonuses95 = (civilizationBonuses96 = (civBonuses27 = (civBonuses28 = this.civBonuses)))));
            civilizationBonuses109.MaximumLevelOfTheMilitaryAcademy += TechnologyTree.lTechnology.get(iTechID).MaximumLevelOfTheMilitaryAcademy;
        }
        if (TechnologyTree.lTechnology.get(iTechID).MaximumLevelOfTheMilitaryAcademyForGenerals != 0) {
            final CivilizationBonuses civBonuses30;
            final CivilizationBonuses civBonuses29;
            final CivilizationBonuses civilizationBonuses100;
            final CivilizationBonuses civilizationBonuses99;
            final CivilizationBonuses civilizationBonuses98;
            final CivilizationBonuses civilizationBonuses110;
            final CivilizationBonuses civilizationBonuses97 = civilizationBonuses110 = (civilizationBonuses98 = (civilizationBonuses99 = (civilizationBonuses100 = (civBonuses29 = (civBonuses30 = this.civBonuses)))));
            civilizationBonuses110.MaximumLevelOfTheMilitaryAcademyForGenerals += TechnologyTree.lTechnology.get(iTechID).MaximumLevelOfTheMilitaryAcademyForGenerals;
        }
        if (TechnologyTree.lTechnology.get(iTechID).UnlocksNukes) {
            this.canBuildNuke = true;
        }
        if (TechnologyTree.lTechnology.get(iTechID).UnlocksAccessToTheSea) {
            this.canAccessSea = true;
        }
        if (TechnologyTree.lTechnology.get(iTechID).UnlocksColonization) {
            this.canColonize = true;
        }
    }
    
    public boolean getAvailableToResearch(final int iTechID) {
        return (!this.getTechResearched(iTechID) || TechnologyTree.lTechnology.get(iTechID).Repeatable) && (TechnologyTree.lTechnology.get(iTechID).RequiredTech < 0 || Game.getCiv(this.iCivID).getTechResearched(TechnologyTree.lTechnology.get(iTechID).RequiredTech)) && (TechnologyTree.lTechnology.get(iTechID).RequiredTech2 < 0 || Game.getCiv(this.iCivID).getTechResearched(TechnologyTree.lTechnology.get(iTechID).RequiredTech2));
    }
    
    public boolean getTechResearched(final int iTechID) {
        try {
            return iTechID < 0 || this.lTechResearched.get(iTechID);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            return true;
        }
    }
    
    public float getResearchProgress(final int iTechID) {
        for (int i = this.lResearching.size() - 1; i >= 0; --i) {
            if (this.lResearching.get(i).iTechID == iTechID) {
                return this.lResearching.get(i).fProgress / TechnologyTree.getResearchCost(this.lResearching.get(i).iTechID, this.getCivID());
            }
        }
        return 0.0f;
    }
    
    public final void updateBestUnits() {
        this.unitsBest.clear();
        this.unitsBest_FirstLine.clear();
        this.unitsBest_Flank.clear();
        this.unitsBest_Support.clear();
        this.unitsBestSize = 0;
        this.unitsBest_FirstLineSize = 0;
        this.unitsBest_FlankSize = 0;
        this.unitsBest_SupportSize = 0;
        this.unitsBest_SiegeSize = 0;
        int i = 0;
        final int jSize = this.unlockedUnits.size();
        while (i < ArmyManager.iUnitsTypesSize) {
            int bestID = -1;
            for (int j = 0; j < jSize; ++j) {
                if (this.unlockedUnits.get(j).unitID == i) {
                    if (bestID < 0) {
                        bestID = j;
                    }
                    else if (ArmyManager.lArmy.get(this.unlockedUnits.get(bestID).unitID).get(this.unlockedUnits.get(bestID).armyID).UnitLevel < ArmyManager.lArmy.get(this.unlockedUnits.get(j).unitID).get(this.unlockedUnits.get(j).armyID).UnitLevel) {
                        bestID = j;
                    }
                }
            }
            if (bestID >= 0) {
                this.unitsBest.add(this.unlockedUnits.get(bestID));
            }
            ++i;
        }
        this.unitsBestSize = this.unitsBest.size();
        for (i = this.unitsBestSize - 1; i >= 0; --i) {
            if (!ArmyManager.lArmy.get(this.unitsBest.get(i).unitID).get(this.unitsBest.get(i).armyID).isSettler) {
                if (ArmyManager.lUnitsTypes.get(this.unitsBest.get(i).unitID).Line == 0) {
                    this.unitsBest_FirstLine.add(this.unitsBest.get(i));
                }
                else if (ArmyManager.lUnitsTypes.get(this.unitsBest.get(i).unitID).Line == 1) {
                    this.unitsBest_Flank.add(this.unitsBest.get(i));
                }
                else if (ArmyManager.lArmy.get(this.unitsBest.get(i).unitID).get(this.unitsBest.get(i).armyID).SiegeUnit) {
                    this.unitsBest_Siege.add(this.unitsBest.get(i));
                }
                else {
                    this.unitsBest_Support.add(this.unitsBest.get(i));
                }
            }
        }
        this.unitsBest_FirstLineSize = this.unitsBest_FirstLine.size();
        this.unitsBest_FlankSize = this.unitsBest_Flank.size();
        this.unitsBest_SupportSize = this.unitsBest_Support.size();
        this.unitsBest_SiegeSize = this.unitsBest_Siege.size();
    }
    
    public int getResearchedTechnologies() {
        int out = 0;
        for (int i = this.lTechResearched.size() - 1; i >= 0; --i) {
            if (this.lTechResearched.get(i)) {
                ++out;
            }
        }
        return out;
    }
    
    public int getInfrastructure() {
        int out = 0;
        for (int i = this.getNumOfProvinces() - 1; i >= 0; --i) {
            out += Game.getProvince(this.getProvinceID(i)).getInfrastructure();
        }
        return out;
    }
    
    public int getConstructedBuildings() {
        int out = 0;
        for (int i = this.getNumOfProvinces() - 1; i >= 0; --i) {
            out += Game.getProvince(this.getProvinceID(i)).iBuildingsSize;
        }
        return out;
    }
    
    public boolean isBuildingResearched(final int building, final int buildingID) {
        for (int i = this.unlockedBuildings.size() - 1; i >= 0; --i) {
            if (this.unlockedBuildings.get(i).building == building && this.unlockedBuildings.get(i).buildingID == buildingID) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isUnitResearched(final int unitID, final int armyID) {
        for (int i = this.unlockedUnits.size() - 1; i >= 0; --i) {
            if (this.unlockedUnits.get(i).unitID == unitID && this.unlockedUnits.get(i).armyID == armyID) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isUnitBest(final int unitID, final int armyID) {
        for (int i = this.unitsBest.size() - 1; i >= 0; --i) {
            if (this.unitsBest.get(i).unitID == unitID && this.unitsBest.get(i).armyID == armyID) {
                return true;
            }
        }
        return false;
    }
    
    public final int getUnitsBest_AttackDefense() {
        int out = 0;
        try {
            for (int i = this.unitsBest.size() - 1; i >= 0; --i) {
                if (ArmyManager.lArmy.get(this.unitsBest.get(i).unitID).get(this.unitsBest.get(i).armyID).getAttack() + ArmyManager.lArmy.get(this.unitsBest.get(i).unitID).get(this.unitsBest.get(i).armyID).getDefense() > out) {
                    out = ArmyManager.lArmy.get(this.unitsBest.get(i).unitID).get(this.unitsBest.get(i).armyID).getAttack() + ArmyManager.lArmy.get(this.unitsBest.get(i).unitID).get(this.unitsBest.get(i).armyID).getDefense();
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return out;
    }
    
    public final void updateResearchPerMonth() {
        this.fResearchPerMonth = 0.0f;
        for (int i = 0; i < this.getNumOfProvinces(); ++i) {
            this.fResearchPerMonth += Game.getProvince(this.getProvinceID(i)).provBonuses.ResearchPoints;
        }
        this.fResearchPerMonth += GameValues.research.BASE_RESEARCH + this.civBonuses.ResearchPoints + GameValues.civRank.CIV_RANK_MONTHLY_RESEARCH[this.iCivRankID];
        this.fResearchPerMonth *= Math.max(0.0f, 1.0f + this.civBonuses.Research / 100.0f + GameValues.budget.TAXATION_LEVEL_RESEARCH[this.getTaxationLevel()] + GameValues.budget.RESEARCH_LEVEL_RESEARCH[this.getResearchLevel()]);
        this.fResearchPerMonth = Math.min(TechnologyTree.getMaxResearch(this.getCivID()), this.fResearchPerMonth);
    }
    
    public final float getResearchFromBuildings() {
        float fOut = 0.0f;
        for (int i = 0; i < this.getNumOfProvinces(); ++i) {
            fOut += Game.getProvince(this.getProvinceID(i)).provBonuses.ResearchPoints;
        }
        return fOut;
    }
    
    public final void updateLegacyPerMonth() {
        this.fLegacyPerMonth = this.civBonuses.MonthlyLegacy;
        for (int i = 0; i < this.getNumOfProvinces(); ++i) {
            this.fLegacyPerMonth += Game.getProvince(this.getProvinceID(i)).provBonuses.MonthlyLegacy;
        }
        this.fLegacyPerMonth += GameValues.civRank.CIV_RANK_MONTHLY_LEGACY[this.iCivRankID] + Game.getLegacyPerUniqueResources(this.getCivID()) + this.diplomacy.getRivalsLegacy(this.getCivID());
        this.fLegacyPerMonth *= 1.0f + this.civBonuses.MonthlyLegacy_Percentage;
        this.fLegacyPerMonth *= 1.0f + GameValues.budget.TAXATION_LEVEL_LEGACY[this.getTaxationLevel()];
    }
    
    public final void addLegacy(final float fValue) {
        this.fLegacy = Math.max(GameValues.legacy.MIN_LEGACY_POINTS, Math.min(GameValues.legacy.MAX_LEGACY_POINTS, this.fLegacy + fValue));
    }
    
    public final void updateManpowerPerMonth() {
        this.fManpowerMax_ToLord = 0.0;
        this.fManpowerMax = this.civBonuses.MaxManpower;
        try {
            for (int i = 0; i < this.getNumOfProvinces(); ++i) {
                this.fManpowerMax += Game.getProvince(this.getProvinceID(i)).provBonuses.MaximumManpower;
                this.fManpowerMax += Game.getManpowerMaxFromProvinceManpowerLvl(this.getProvinceID(i));
            }
        }
        catch (final Exception ex) {}
        this.fManpowerMax += GameValues.civRank.CIV_RANK_MANPOWER_MAX[this.iCivRankID];
        if (this.getPuppetOfCivID() != this.getCivID()) {
            this.fManpowerMax_ToLord = this.fManpowerMax * GameValues.vassal.VASSAL_MANPOWER_TO_LORD[Game.getCiv(this.getPuppetOfCivID()).diplomacy.getVassal_ManpowerLevel(this.getCivID())];
            this.fManpowerMax = Math.max(0.0, this.fManpowerMax - this.fManpowerMax_ToLord);
        }
        this.fManpowerMax += GameValues.manpower.MANPOWER_MAX_BASE;
        this.fManpowerMax *= 1.0f + this.civBonuses.MaxManpower_Percentage;
        for (int i = 0; i < this.diplomacy.lVassals.size(); ++i) {
            this.fManpowerMax += Game.getCiv(this.diplomacy.lVassals.get(i).c).fManpowerMax_ToLord;
        }
        this.fManpowerMax += this.diplomacy.getRivalsManpower(this.getCivID());
        for (int i = 0; i < this.inAllianceSize; ++i) {
            if (Game.alliancesSpecial.get(this.inAlliance.get(i)).iLeaderCivID == this.getCivID() && Game.alliancesSpecial.get(this.inAlliance.get(i)).typeOfAlliance == 0) {
                this.fManpowerMax += HREManager.getManpower_Emperor(this.inAlliance.get(i));
            }
        }
        this.fManpowerPerMonth = Game.getManpowerPerMonth(this.getCivID(), this.fManpowerMax) * (1.0f + GameValues.budget.MILITARY_LEVEL_MANPOWER[this.getMilitaryLevel()]);
    }
    
    public final float getManpowerMax_Provinces_INFO() {
        float out = 0.0f;
        for (int i = 0; i < this.getNumOfProvinces(); ++i) {
            out += Game.getProvince(this.getProvinceID(i)).provBonuses.MaximumManpower;
            out += Game.getManpowerMaxFromProvinceManpowerLvl(this.getProvinceID(i));
        }
        return out * (1.0f + this.civBonuses.MaxManpower_Percentage);
    }
    
    public final float getManpowerMax_Vassals_INFO() {
        float out = 0.0f;
        for (int i = 0; i < this.diplomacy.lVassals.size(); ++i) {
            out += (float)Game.getCiv(this.diplomacy.lVassals.get(i).c).fManpowerMax_ToLord;
        }
        return out;
    }
    
    public final void updateDiplomacyPerMonth() {
        this.fDiplomacyPerMonth = GameValues.diplomacy.DIPLOMACY_POINTS_PER_MONTH_BASE_VALUE * (1.0f + this.civBonuses.DiplomacyPoints) + CivilizationRanking.getDiplomacyPoints_RankStar(this.getCivID());
        this.fDiplomacyMax = GameValues.diplomacy.DIPLOMACY_POINTS_MAX + this.getNumOfProvinces() * GameValues.diplomacy.DIPLOMACY_POINTS_MAX_PER_PROVINCE;
    }
    
    public final float getDiplomacyPerMonth() {
        return this.fDiplomacyPerMonth - GameValues.diplomacy.DIPLOMACY_IMPROVE_RELATIONS_COST_PER_MONTH * this.diplomacy.iImprovingRelationsSize - GameValues.diplomacy.DIPLOMACY_DAMAGE_RELATIONS_COST_PER_MONTH * this.diplomacy.iDamagingRelationsSize;
    }
    
    public final void setDiplomacyPerMonth(final float fDiplomacyPerMonth) {
        this.fDiplomacyPerMonth = fDiplomacyPerMonth;
    }
    
    public final void updateIncome() {
        if (this.getPuppetOfCivID() == this.getCivID()) {
            this.fTotalIncomePerMonth = GameValues.civRank.CIV_BASE_INCOME[this.iCivRankID];
        }
        else {
            this.fTotalIncomePerMonth = GameValues.civRank.CIV_BASE_INCOME_VASSAL[this.iCivRankID];
        }
        this.fTotalExpensesPerMonth = 0.0f;
        for (int i = 0; i < this.getNumOfProvinces(); ++i) {
            this.fTotalIncomePerMonth += Game.getProvince(this.getProvinceID(i)).fProvinceIncome;
            this.fTotalExpensesPerMonth += Game.getProvince(this.getProvinceID(i)).fProvinceMaintenance;
        }
        for (int i = 0; i < this.inAllianceSize; ++i) {
            if (Game.alliancesSpecial.get(this.inAlliance.get(i)).iLeaderCivID == this.getCivID() && Game.alliancesSpecial.get(this.inAlliance.get(i)).typeOfAlliance == 0) {
                this.fTotalIncomePerMonth += HREManager.getIncome_Emperor(this.inAlliance.get(i));
            }
        }
        this.fTotalExpensesPerMonth += this.fArmyMaintenance;
        this.fTotalExpensesPerMonth += Game.getResearchCost(this.getCivID());
        if (this.getPuppetOfCivID() != this.getCivID()) {
            this.fExpenseVassal = Game.getIncomeFromVassal(this.getPuppetOfCivID(), this.getCivID());
            this.fTotalExpensesPerMonth += this.fExpenseVassal;
        }
        else {
            this.fExpenseVassal = 0.0f;
        }
    }
    
    public final void updateTotalIncomePerMonth() {
        this.updateIncome();
        this.updateVassalsIncome();
        this.fTotalIncomePerMonth += this.fIncomeLord + this.fLoansCost + this.civBonuses.MaintenanceCost;
    }
    
    public final void updateArmyMaintenance() {
        this.fArmyMaintenance = 0.0f;
        for (int i = this.iArmyPositionSize - 1; i >= 0; --i) {
            final Province province = Game.getProvince(this.getArmyPosition(i));
            for (int j = province.getArmySize() - 1; j >= 0; --j) {
                final ArmyDivision army = Game.getProvince(this.getArmyPosition(i)).getArmy(j);
                if (army.civID == this.getCivID() && this.getArmyPositionKey(i).equals(army.key)) {
                    this.fArmyMaintenance += army.fMaintenanceCost;
                }
            }
        }
        this.fArmyMaintenance = this.fArmyMaintenance * Math.max(0.05f, 1.0f + this.civBonuses.ArmyMaintenance / 100.0f + GameValues.budget.MILITARY_LEVEL_ARMY_MAINTENANCE[this.getMilitaryLevel()]) * (1.0f + Math.max(0.0f, this.iRegiments / (float)this.iRegimentsLimit - 1.0f) * GameValues.army.ARMY_MAINTENANCE_OVER_REGIMENTS_LIMIT_MODIFIER);
    }
    
    public final float getAdditionalExpenses() {
        return this.civBonuses.MaintenanceCost + this.fExpenseVassal;
    }
    
    public float getIncomeTaxation() {
        float out = 0.0f;
        for (int i = 0; i < this.getNumOfProvinces(); ++i) {
            out += Game.getProvince(this.getProvinceID(i)).fProvinceIncomeTaxation;
        }
        return out;
    }
    
    public float getIncomeEconomy() {
        float out = 0.0f;
        for (int i = 0; i < this.getNumOfProvinces(); ++i) {
            out += Game.getProvince(this.getProvinceID(i)).fProvinceIncomeEconomy;
        }
        return out;
    }
    
    public float getIncomeProduction() {
        float out = 0.0f;
        for (int i = 0; i < this.getNumOfProvinces(); ++i) {
            out += Game.getProvince(this.getProvinceID(i)).fProvinceIncomeProduction;
        }
        return out;
    }
    
    public float getIncomeBuildings() {
        float out = 0.0f;
        for (int i = 0; i < this.getNumOfProvinces(); ++i) {
            out += Game.getProvince(this.getProvinceID(i)).provBonuses.MonthlyIncome;
        }
        return out;
    }
    
    public float getProvinceMaintenance() {
        float out = 0.0f;
        for (int i = 0; i < this.getNumOfProvinces(); ++i) {
            out += Game.getProvince(this.getProvinceID(i)).fProvinceMaintenance;
        }
        return out;
    }
    
    public float getBuildingsMaintenance() {
        float out = 0.0f;
        for (int i = 0; i < this.getNumOfProvinces(); ++i) {
            out += Game.getProvince(this.getProvinceID(i)).getProvinceBuildingsMaintenance();
        }
        return out;
    }
    
    public final boolean takeLoan() {
        if (this.iLoansSize >= Game.getLoanMaxNumber(this.getCivID())) {
            return false;
        }
        final float fValue = Game.getLoanValue(this.getCivID()) + Game.getLoanInterestValue(this.getCivID());
        this.loans.add(new Loan(fValue));
        this.iLoansSize = this.loans.size();
        this.fGold += fValue;
        this.fLoansCost += this.loans.get(this.iLoansSize - 1).fInterestPerMonth;
        this.fTotalExpensesPerMonth += this.loans.get(this.iLoansSize - 1).fInterestPerMonth;
        this.setInflation(this.civData3.getInflation() + GameValues.loan.LOAN_INFLATION);
        Game.gameThread.addCivUpdateLoans(this.getCivID());
        Game.gameThread.addCivUpdateTotalIncomePerMonth(this.getCivID());
        return true;
    }
    
    public final void addLoan_Load(final Loan nLoan) {
        this.loans.add(nLoan);
        this.iLoansSize = this.loans.size();
        this.fLoansCost += nLoan.fInterestPerMonth;
        this.fTotalExpensesPerMonth += nLoan.fInterestPerMonth;
    }
    
    public final void updateLoans() {
        if (this.iLoansSize > 0) {
            for (int i = this.iLoansSize - 1; i >= 0; --i) {
                if (Game_Calendar.TURN_ID >= this.loans.get(i).iExpires_TurnID) {
                    this.fLoansCost -= this.loans.get(i).fInterestPerMonth;
                    this.fTotalExpensesPerMonth -= this.loans.get(i).fInterestPerMonth;
                    this.loans.remove(i);
                    this.iLoansSize = this.loans.size();
                    if (this.iLoansSize == 0) {
                        Game.gameThread.removeCivUpdateLoans(this.getCivID());
                        Game.gameThread.addCivUpdateTotalIncomePerMonth(this.getCivID());
                    }
                }
            }
        }
    }
    
    public final boolean repayLoan(final int i) {
        try {
            if (i < this.iLoansSize) {
                final float toRepay = this.loans.get(i).fLoanValue - this.loans.get(i).getLoanValueLeft();
                if (this.fGold >= toRepay) {
                    this.fGold -= toRepay;
                    this.fLoansCost -= this.loans.get(i).fInterestPerMonth;
                    this.loans.remove(i);
                    this.iLoansSize = this.loans.size();
                    if (this.iLoansSize == 0) {
                        Game.gameThread.removeCivUpdateLoans(this.getCivID());
                        Game.gameThread.addCivUpdateTotalIncomePerMonth(this.getCivID());
                    }
                    return true;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return false;
    }
    
    public final void updateProvincesIncomeAndExpenses() {
        for (int i = 0; i < this.getNumOfProvinces(); ++i) {
            Game.getProvince(this.getProvinceID(i)).updateProvinceIncome();
        }
        this.updateResearchPerMonth();
        this.updateLegacyPerMonth();
        this.updateTotalIncomePerMonth();
    }
    
    public final void updateTaxationLevel(final int nTaxationLevel) {
        if (this.getTaxationLevel() != nTaxationLevel) {
            this.setTaxationLevel(nTaxationLevel);
            for (int i = 0; i < this.getNumOfProvinces(); ++i) {
                Game.getProvince(this.getProvinceID(i)).updateProvinceIncome();
            }
            this.updateResearchPerMonth();
            this.updateLegacyPerMonth();
            this.updateTotalIncomePerMonth();
        }
    }
    
    public final void updateResearchLevel(final int nResearchLevel) {
        if (this.getResearchLevel() != nResearchLevel) {
            this.setResearchLevel(nResearchLevel);
            this.updateResearchPerMonth();
            this.updateTotalIncomePerMonth();
        }
    }
    
    public final void updateMilitaryLevel(final int nMilitaryLevel) {
        if (this.getMilitaryLevel() != nMilitaryLevel) {
            this.setMilitaryLevel(nMilitaryLevel);
            this.updateManpowerPerMonth();
            this.updateArmyMaintenance();
            this.updateTotalIncomePerMonth();
        }
    }
    
    public final boolean reduceInflation() {
        if (this.civData3.getInflation() > 0.0f && this.fLegacy > 0.0f) {
            if (this.fLegacy >= Game.getInflationReduceCost_Legacy(this.getCivID())) {
                this.fLegacy -= Game.getInflationReduceCost_Legacy(this.getCivID());
                this.civData3.setInflation(0.0f);
            }
            else {
                final float maxDecrease = this.fLegacy / GameValues.inflation.INFLATION_REDUCE_COST_LEGACY_PER_INFLATION;
                this.civData3.setInflation(Math.max(0.0f, this.civData3.getInflation() - maxDecrease / 100.0f));
                this.fLegacy -= maxDecrease * GameValues.inflation.INFLATION_REDUCE_COST_LEGACY_PER_INFLATION;
            }
            return true;
        }
        return false;
    }
    
    public final float getInflation() {
        return this.civData3.getInflation() + this.civBonuses.Inflation;
    }
    
    public final float getInflation_Just() {
        return this.civData3.getInflation();
    }
    
    public final void setInflation(final float fInflation) {
        this.civData3.setInflation(Math.max(0.0f, Math.min(fInflation, GameValues.inflation.INFLATION_MAX)));
    }
    
    public final float getCorruption() {
        return this.civData3.getCorruption() + this.civBonuses.Corruption;
    }
    
    public final void setCorruption(final float fCorruption) {
        this.civData3.setCorruption(Math.max(GameValues.supremeCourt.CORRUPTION_MIN, Math.min(fCorruption, GameValues.supremeCourt.CORRUPTION_MAX)));
    }
    
    public final void addGold(final float nGold) {
        if (this.fGold < Game.getMaxAmountOfGold(this.getCivID())) {
            this.fGold = Math.min((float)Game.getMaxAmountOfGold(this.getCivID()), this.fGold + nGold);
        }
        else if (nGold < 0.0f) {
            this.fGold += nGold;
        }
    }
    
    public final boolean upgradeMilitaryAcademyForGenerals() {
        DesktopLauncher.SMS("UpgradeMilitaryAcademyForGenerals: " + this.getCivID());
        this.fGold -= Game.getMilitaryAcademyForGenerals_Cost(this.getCivID());
        final CivilizationBonuses civBonuses8;
        final CivilizationBonuses civBonuses7;
        final CivilizationBonuses civilizationBonuses40;
        final CivilizationBonuses civilizationBonuses39;
        final CivilizationBonuses civilizationBonuses38;
        final CivilizationBonuses civilizationBonuses61;
        final CivilizationBonuses civilizationBonuses37 = civilizationBonuses61 = (civilizationBonuses38 = (civilizationBonuses39 = (civilizationBonuses40 = (civBonuses7 = (civBonuses8 = this.civBonuses)))));
        civilizationBonuses61.GeneralAttack -= Game.getMilitaryAcademyForGenerals_GeneralAttack(this.getCivID());
        final CivilizationBonuses civBonuses10;
        final CivilizationBonuses civBonuses9;
        final CivilizationBonuses civilizationBonuses44;
        final CivilizationBonuses civilizationBonuses43;
        final CivilizationBonuses civilizationBonuses42;
        final CivilizationBonuses civilizationBonuses62;
        final CivilizationBonuses civilizationBonuses41 = civilizationBonuses62 = (civilizationBonuses42 = (civilizationBonuses43 = (civilizationBonuses44 = (civBonuses9 = (civBonuses10 = this.civBonuses)))));
        civilizationBonuses62.GeneralDefense -= Game.getMilitaryAcademyForGenerals_GeneralDefense(this.getCivID());
        final CivilizationBonuses civBonuses12;
        final CivilizationBonuses civBonuses11;
        final CivilizationBonuses civilizationBonuses48;
        final CivilizationBonuses civilizationBonuses47;
        final CivilizationBonuses civilizationBonuses46;
        final CivilizationBonuses civilizationBonuses63;
        final CivilizationBonuses civilizationBonuses45 = civilizationBonuses63 = (civilizationBonuses46 = (civilizationBonuses47 = (civilizationBonuses48 = (civBonuses11 = (civBonuses12 = this.civBonuses)))));
        civilizationBonuses63.MaintenanceCost -= Game.getMilitaryAcademyForGenerals_MaintenanceCost(this.getCivID());
        this.setMilitaryAcademyForGeneralsLevel(this.getMilitaryAcademyForGeneralsLevel() + 1);
        final CivilizationBonuses civBonuses14;
        final CivilizationBonuses civBonuses13;
        final CivilizationBonuses civilizationBonuses52;
        final CivilizationBonuses civilizationBonuses51;
        final CivilizationBonuses civilizationBonuses50;
        final CivilizationBonuses civilizationBonuses64;
        final CivilizationBonuses civilizationBonuses49 = civilizationBonuses64 = (civilizationBonuses50 = (civilizationBonuses51 = (civilizationBonuses52 = (civBonuses13 = (civBonuses14 = this.civBonuses)))));
        civilizationBonuses64.GeneralAttack += Game.getMilitaryAcademyForGenerals_GeneralAttack(this.getCivID());
        final CivilizationBonuses civBonuses16;
        final CivilizationBonuses civBonuses15;
        final CivilizationBonuses civilizationBonuses56;
        final CivilizationBonuses civilizationBonuses55;
        final CivilizationBonuses civilizationBonuses54;
        final CivilizationBonuses civilizationBonuses65;
        final CivilizationBonuses civilizationBonuses53 = civilizationBonuses65 = (civilizationBonuses54 = (civilizationBonuses55 = (civilizationBonuses56 = (civBonuses15 = (civBonuses16 = this.civBonuses)))));
        civilizationBonuses65.GeneralDefense += Game.getMilitaryAcademyForGenerals_GeneralDefense(this.getCivID());
        final CivilizationBonuses civBonuses18;
        final CivilizationBonuses civBonuses17;
        final CivilizationBonuses civilizationBonuses60;
        final CivilizationBonuses civilizationBonuses59;
        final CivilizationBonuses civilizationBonuses58;
        final CivilizationBonuses civilizationBonuses66;
        final CivilizationBonuses civilizationBonuses57 = civilizationBonuses66 = (civilizationBonuses58 = (civilizationBonuses59 = (civilizationBonuses60 = (civBonuses17 = (civBonuses18 = this.civBonuses)))));
        civilizationBonuses66.MaintenanceCost += Game.getMilitaryAcademyForGenerals_MaintenanceCost(this.getCivID());
        Game.gameThread.addCivUpdateTotalIncomePerMonth(this.getCivID());
        return true;
    }
    
    public final boolean upgradeMilitaryAcademyForGenerals_Client() {
        DesktopLauncher.SMS("UpgradeMilitaryAcademyForGenerals: " + this.getCivID());
        this.fGold -= Game.getMilitaryAcademyForGenerals_Cost(this.getCivID());
        final CivilizationBonuses civBonuses8;
        final CivilizationBonuses civBonuses7;
        final CivilizationBonuses civilizationBonuses40;
        final CivilizationBonuses civilizationBonuses39;
        final CivilizationBonuses civilizationBonuses38;
        final CivilizationBonuses civilizationBonuses61;
        final CivilizationBonuses civilizationBonuses37 = civilizationBonuses61 = (civilizationBonuses38 = (civilizationBonuses39 = (civilizationBonuses40 = (civBonuses7 = (civBonuses8 = this.civBonuses)))));
        civilizationBonuses61.GeneralAttack -= Game.getMilitaryAcademyForGenerals_GeneralAttack(this.getCivID());
        final CivilizationBonuses civBonuses10;
        final CivilizationBonuses civBonuses9;
        final CivilizationBonuses civilizationBonuses44;
        final CivilizationBonuses civilizationBonuses43;
        final CivilizationBonuses civilizationBonuses42;
        final CivilizationBonuses civilizationBonuses62;
        final CivilizationBonuses civilizationBonuses41 = civilizationBonuses62 = (civilizationBonuses42 = (civilizationBonuses43 = (civilizationBonuses44 = (civBonuses9 = (civBonuses10 = this.civBonuses)))));
        civilizationBonuses62.GeneralDefense -= Game.getMilitaryAcademyForGenerals_GeneralDefense(this.getCivID());
        final CivilizationBonuses civBonuses12;
        final CivilizationBonuses civBonuses11;
        final CivilizationBonuses civilizationBonuses48;
        final CivilizationBonuses civilizationBonuses47;
        final CivilizationBonuses civilizationBonuses46;
        final CivilizationBonuses civilizationBonuses63;
        final CivilizationBonuses civilizationBonuses45 = civilizationBonuses63 = (civilizationBonuses46 = (civilizationBonuses47 = (civilizationBonuses48 = (civBonuses11 = (civBonuses12 = this.civBonuses)))));
        civilizationBonuses63.MaintenanceCost -= Game.getMilitaryAcademyForGenerals_MaintenanceCost(this.getCivID());
        this.setMilitaryAcademyForGeneralsLevel(this.getMilitaryAcademyForGeneralsLevel() + 1);
        final CivilizationBonuses civBonuses14;
        final CivilizationBonuses civBonuses13;
        final CivilizationBonuses civilizationBonuses52;
        final CivilizationBonuses civilizationBonuses51;
        final CivilizationBonuses civilizationBonuses50;
        final CivilizationBonuses civilizationBonuses64;
        final CivilizationBonuses civilizationBonuses49 = civilizationBonuses64 = (civilizationBonuses50 = (civilizationBonuses51 = (civilizationBonuses52 = (civBonuses13 = (civBonuses14 = this.civBonuses)))));
        civilizationBonuses64.GeneralAttack += Game.getMilitaryAcademyForGenerals_GeneralAttack(this.getCivID());
        final CivilizationBonuses civBonuses16;
        final CivilizationBonuses civBonuses15;
        final CivilizationBonuses civilizationBonuses56;
        final CivilizationBonuses civilizationBonuses55;
        final CivilizationBonuses civilizationBonuses54;
        final CivilizationBonuses civilizationBonuses65;
        final CivilizationBonuses civilizationBonuses53 = civilizationBonuses65 = (civilizationBonuses54 = (civilizationBonuses55 = (civilizationBonuses56 = (civBonuses15 = (civBonuses16 = this.civBonuses)))));
        civilizationBonuses65.GeneralDefense += Game.getMilitaryAcademyForGenerals_GeneralDefense(this.getCivID());
        final CivilizationBonuses civBonuses18;
        final CivilizationBonuses civBonuses17;
        final CivilizationBonuses civilizationBonuses60;
        final CivilizationBonuses civilizationBonuses59;
        final CivilizationBonuses civilizationBonuses58;
        final CivilizationBonuses civilizationBonuses66;
        final CivilizationBonuses civilizationBonuses57 = civilizationBonuses66 = (civilizationBonuses58 = (civilizationBonuses59 = (civilizationBonuses60 = (civBonuses17 = (civBonuses18 = this.civBonuses)))));
        civilizationBonuses66.MaintenanceCost += Game.getMilitaryAcademyForGenerals_MaintenanceCost(this.getCivID());
        Game.gameThread.addCivUpdateTotalIncomePerMonth(this.getCivID());
        return true;
    }
    
    public final void buildMilitaryAcademyForGenerals_Bonuses() {
        if (this.getMilitaryAcademyForGeneralsLevel() > 0) {
            final CivilizationBonuses civBonuses5;
            final CivilizationBonuses civBonuses4;
            final CivilizationBonuses civilizationBonuses22;
            final CivilizationBonuses civilizationBonuses21;
            final CivilizationBonuses civilizationBonuses20;
            final CivilizationBonuses civilizationBonuses31;
            final CivilizationBonuses civilizationBonuses19 = civilizationBonuses31 = (civilizationBonuses20 = (civilizationBonuses21 = (civilizationBonuses22 = (civBonuses4 = (civBonuses5 = this.civBonuses)))));
            civilizationBonuses31.GeneralAttack += Game.getMilitaryAcademyForGenerals_GeneralAttack(this.getCivID());
            final CivilizationBonuses civBonuses7;
            final CivilizationBonuses civBonuses6;
            final CivilizationBonuses civilizationBonuses26;
            final CivilizationBonuses civilizationBonuses25;
            final CivilizationBonuses civilizationBonuses24;
            final CivilizationBonuses civilizationBonuses32;
            final CivilizationBonuses civilizationBonuses23 = civilizationBonuses32 = (civilizationBonuses24 = (civilizationBonuses25 = (civilizationBonuses26 = (civBonuses6 = (civBonuses7 = this.civBonuses)))));
            civilizationBonuses32.GeneralDefense += Game.getMilitaryAcademyForGenerals_GeneralDefense(this.getCivID());
            final CivilizationBonuses civBonuses9;
            final CivilizationBonuses civBonuses8;
            final CivilizationBonuses civilizationBonuses30;
            final CivilizationBonuses civilizationBonuses29;
            final CivilizationBonuses civilizationBonuses28;
            final CivilizationBonuses civilizationBonuses33;
            final CivilizationBonuses civilizationBonuses27 = civilizationBonuses33 = (civilizationBonuses28 = (civilizationBonuses29 = (civilizationBonuses30 = (civBonuses8 = (civBonuses9 = this.civBonuses)))));
            civilizationBonuses33.MaintenanceCost += Game.getMilitaryAcademyForGenerals_MaintenanceCost(this.getCivID());
        }
    }
    
    public final boolean upgradeMilitaryAcademy() {
        if (this.fGold >= Game.getMilitaryAcademy_Cost(this.getCivID()) && this.getMilitaryAcademyLevel() < Game.getMilitaryAcademy_MaxLvl(this.getCivID())) {
            DesktopLauncher.SMS("UpgradeMilitaryAcademy: " + this.getCivID());
            this.fGold -= Game.getMilitaryAcademy_Cost(this.getCivID());
            final CivilizationBonuses civBonuses10;
            final CivilizationBonuses civBonuses9;
            final CivilizationBonuses civilizationBonuses52;
            final CivilizationBonuses civilizationBonuses51;
            final CivilizationBonuses civilizationBonuses50;
            final CivilizationBonuses civilizationBonuses81;
            final CivilizationBonuses civilizationBonuses49 = civilizationBonuses81 = (civilizationBonuses50 = (civilizationBonuses51 = (civilizationBonuses52 = (civBonuses9 = (civBonuses10 = this.civBonuses)))));
            civilizationBonuses81.UnitsAttack -= Game.getMilitaryAcademy_Attack(this.getCivID());
            final CivilizationBonuses civBonuses12;
            final CivilizationBonuses civBonuses11;
            final CivilizationBonuses civilizationBonuses56;
            final CivilizationBonuses civilizationBonuses55;
            final CivilizationBonuses civilizationBonuses54;
            final CivilizationBonuses civilizationBonuses82;
            final CivilizationBonuses civilizationBonuses53 = civilizationBonuses82 = (civilizationBonuses54 = (civilizationBonuses55 = (civilizationBonuses56 = (civBonuses11 = (civBonuses12 = this.civBonuses)))));
            civilizationBonuses82.UnitsDefense -= Game.getMilitaryAcademy_Defense(this.getCivID());
            final CivilizationBonuses civBonuses14;
            final CivilizationBonuses civBonuses13;
            final CivilizationBonuses civilizationBonuses60;
            final CivilizationBonuses civilizationBonuses59;
            final CivilizationBonuses civilizationBonuses58;
            final CivilizationBonuses civilizationBonuses83;
            final CivilizationBonuses civilizationBonuses57 = civilizationBonuses83 = (civilizationBonuses58 = (civilizationBonuses59 = (civilizationBonuses60 = (civBonuses13 = (civBonuses14 = this.civBonuses)))));
            civilizationBonuses83.MaintenanceCost -= Game.getMilitaryAcademy_MaintenanceCost(this.getCivID());
            final CivilizationBonuses civBonuses16;
            final CivilizationBonuses civBonuses15;
            final CivilizationBonuses civilizationBonuses64;
            final CivilizationBonuses civilizationBonuses63;
            final CivilizationBonuses civilizationBonuses62;
            final CivilizationBonuses civilizationBonuses84;
            final CivilizationBonuses civilizationBonuses61 = civilizationBonuses84 = (civilizationBonuses62 = (civilizationBonuses63 = (civilizationBonuses64 = (civBonuses15 = (civBonuses16 = this.civBonuses)))));
            civilizationBonuses84.RegimentsLimit -= Game.getMilitaryAcademy_RegimentsLimit(this.getCivID());
            this.setMilitaryAcademyLevel(this.getMilitaryAcademyLevel() + 1);
            final CivilizationBonuses civBonuses18;
            final CivilizationBonuses civBonuses17;
            final CivilizationBonuses civilizationBonuses68;
            final CivilizationBonuses civilizationBonuses67;
            final CivilizationBonuses civilizationBonuses66;
            final CivilizationBonuses civilizationBonuses85;
            final CivilizationBonuses civilizationBonuses65 = civilizationBonuses85 = (civilizationBonuses66 = (civilizationBonuses67 = (civilizationBonuses68 = (civBonuses17 = (civBonuses18 = this.civBonuses)))));
            civilizationBonuses85.UnitsAttack += Game.getMilitaryAcademy_Attack(this.getCivID());
            final CivilizationBonuses civBonuses20;
            final CivilizationBonuses civBonuses19;
            final CivilizationBonuses civilizationBonuses72;
            final CivilizationBonuses civilizationBonuses71;
            final CivilizationBonuses civilizationBonuses70;
            final CivilizationBonuses civilizationBonuses86;
            final CivilizationBonuses civilizationBonuses69 = civilizationBonuses86 = (civilizationBonuses70 = (civilizationBonuses71 = (civilizationBonuses72 = (civBonuses19 = (civBonuses20 = this.civBonuses)))));
            civilizationBonuses86.UnitsDefense += Game.getMilitaryAcademy_Defense(this.getCivID());
            final CivilizationBonuses civBonuses22;
            final CivilizationBonuses civBonuses21;
            final CivilizationBonuses civilizationBonuses76;
            final CivilizationBonuses civilizationBonuses75;
            final CivilizationBonuses civilizationBonuses74;
            final CivilizationBonuses civilizationBonuses87;
            final CivilizationBonuses civilizationBonuses73 = civilizationBonuses87 = (civilizationBonuses74 = (civilizationBonuses75 = (civilizationBonuses76 = (civBonuses21 = (civBonuses22 = this.civBonuses)))));
            civilizationBonuses87.MaintenanceCost += Game.getMilitaryAcademy_MaintenanceCost(this.getCivID());
            final CivilizationBonuses civBonuses24;
            final CivilizationBonuses civBonuses23;
            final CivilizationBonuses civilizationBonuses80;
            final CivilizationBonuses civilizationBonuses79;
            final CivilizationBonuses civilizationBonuses78;
            final CivilizationBonuses civilizationBonuses88;
            final CivilizationBonuses civilizationBonuses77 = civilizationBonuses88 = (civilizationBonuses78 = (civilizationBonuses79 = (civilizationBonuses80 = (civBonuses23 = (civBonuses24 = this.civBonuses)))));
            civilizationBonuses88.RegimentsLimit += Game.getMilitaryAcademy_RegimentsLimit(this.getCivID());
            Game.gameThread.addCivUpdateTotalIncomePerMonth(this.getCivID());
            this.updateRegimentsLimit();
            return true;
        }
        return false;
    }
    
    public final boolean upgradeMilitaryAcademy_Client() {
        this.fGold -= Game.getMilitaryAcademy_Cost(this.getCivID());
        final CivilizationBonuses civBonuses10;
        final CivilizationBonuses civBonuses9;
        final CivilizationBonuses civilizationBonuses52;
        final CivilizationBonuses civilizationBonuses51;
        final CivilizationBonuses civilizationBonuses50;
        final CivilizationBonuses civilizationBonuses81;
        final CivilizationBonuses civilizationBonuses49 = civilizationBonuses81 = (civilizationBonuses50 = (civilizationBonuses51 = (civilizationBonuses52 = (civBonuses9 = (civBonuses10 = this.civBonuses)))));
        civilizationBonuses81.UnitsAttack -= Game.getMilitaryAcademy_Attack(this.getCivID());
        final CivilizationBonuses civBonuses12;
        final CivilizationBonuses civBonuses11;
        final CivilizationBonuses civilizationBonuses56;
        final CivilizationBonuses civilizationBonuses55;
        final CivilizationBonuses civilizationBonuses54;
        final CivilizationBonuses civilizationBonuses82;
        final CivilizationBonuses civilizationBonuses53 = civilizationBonuses82 = (civilizationBonuses54 = (civilizationBonuses55 = (civilizationBonuses56 = (civBonuses11 = (civBonuses12 = this.civBonuses)))));
        civilizationBonuses82.UnitsDefense -= Game.getMilitaryAcademy_Defense(this.getCivID());
        final CivilizationBonuses civBonuses14;
        final CivilizationBonuses civBonuses13;
        final CivilizationBonuses civilizationBonuses60;
        final CivilizationBonuses civilizationBonuses59;
        final CivilizationBonuses civilizationBonuses58;
        final CivilizationBonuses civilizationBonuses83;
        final CivilizationBonuses civilizationBonuses57 = civilizationBonuses83 = (civilizationBonuses58 = (civilizationBonuses59 = (civilizationBonuses60 = (civBonuses13 = (civBonuses14 = this.civBonuses)))));
        civilizationBonuses83.MaintenanceCost -= Game.getMilitaryAcademy_MaintenanceCost(this.getCivID());
        final CivilizationBonuses civBonuses16;
        final CivilizationBonuses civBonuses15;
        final CivilizationBonuses civilizationBonuses64;
        final CivilizationBonuses civilizationBonuses63;
        final CivilizationBonuses civilizationBonuses62;
        final CivilizationBonuses civilizationBonuses84;
        final CivilizationBonuses civilizationBonuses61 = civilizationBonuses84 = (civilizationBonuses62 = (civilizationBonuses63 = (civilizationBonuses64 = (civBonuses15 = (civBonuses16 = this.civBonuses)))));
        civilizationBonuses84.RegimentsLimit -= Game.getMilitaryAcademy_RegimentsLimit(this.getCivID());
        this.setMilitaryAcademyLevel(this.getMilitaryAcademyLevel() + 1);
        final CivilizationBonuses civBonuses18;
        final CivilizationBonuses civBonuses17;
        final CivilizationBonuses civilizationBonuses68;
        final CivilizationBonuses civilizationBonuses67;
        final CivilizationBonuses civilizationBonuses66;
        final CivilizationBonuses civilizationBonuses85;
        final CivilizationBonuses civilizationBonuses65 = civilizationBonuses85 = (civilizationBonuses66 = (civilizationBonuses67 = (civilizationBonuses68 = (civBonuses17 = (civBonuses18 = this.civBonuses)))));
        civilizationBonuses85.UnitsAttack += Game.getMilitaryAcademy_Attack(this.getCivID());
        final CivilizationBonuses civBonuses20;
        final CivilizationBonuses civBonuses19;
        final CivilizationBonuses civilizationBonuses72;
        final CivilizationBonuses civilizationBonuses71;
        final CivilizationBonuses civilizationBonuses70;
        final CivilizationBonuses civilizationBonuses86;
        final CivilizationBonuses civilizationBonuses69 = civilizationBonuses86 = (civilizationBonuses70 = (civilizationBonuses71 = (civilizationBonuses72 = (civBonuses19 = (civBonuses20 = this.civBonuses)))));
        civilizationBonuses86.UnitsDefense += Game.getMilitaryAcademy_Defense(this.getCivID());
        final CivilizationBonuses civBonuses22;
        final CivilizationBonuses civBonuses21;
        final CivilizationBonuses civilizationBonuses76;
        final CivilizationBonuses civilizationBonuses75;
        final CivilizationBonuses civilizationBonuses74;
        final CivilizationBonuses civilizationBonuses87;
        final CivilizationBonuses civilizationBonuses73 = civilizationBonuses87 = (civilizationBonuses74 = (civilizationBonuses75 = (civilizationBonuses76 = (civBonuses21 = (civBonuses22 = this.civBonuses)))));
        civilizationBonuses87.MaintenanceCost += Game.getMilitaryAcademy_MaintenanceCost(this.getCivID());
        final CivilizationBonuses civBonuses24;
        final CivilizationBonuses civBonuses23;
        final CivilizationBonuses civilizationBonuses80;
        final CivilizationBonuses civilizationBonuses79;
        final CivilizationBonuses civilizationBonuses78;
        final CivilizationBonuses civilizationBonuses88;
        final CivilizationBonuses civilizationBonuses77 = civilizationBonuses88 = (civilizationBonuses78 = (civilizationBonuses79 = (civilizationBonuses80 = (civBonuses23 = (civBonuses24 = this.civBonuses)))));
        civilizationBonuses88.RegimentsLimit += Game.getMilitaryAcademy_RegimentsLimit(this.getCivID());
        Game.gameThread.addCivUpdateTotalIncomePerMonth(this.getCivID());
        this.updateRegimentsLimit();
        return true;
    }
    
    public final void buildMilitaryAcademy_Bonuses() {
        if (this.getMilitaryAcademyLevel() > 0) {
            final CivilizationBonuses civBonuses6;
            final CivilizationBonuses civBonuses5;
            final CivilizationBonuses civilizationBonuses28;
            final CivilizationBonuses civilizationBonuses27;
            final CivilizationBonuses civilizationBonuses26;
            final CivilizationBonuses civilizationBonuses41;
            final CivilizationBonuses civilizationBonuses25 = civilizationBonuses41 = (civilizationBonuses26 = (civilizationBonuses27 = (civilizationBonuses28 = (civBonuses5 = (civBonuses6 = this.civBonuses)))));
            civilizationBonuses41.UnitsAttack += Game.getMilitaryAcademy_Attack(this.getCivID());
            final CivilizationBonuses civBonuses8;
            final CivilizationBonuses civBonuses7;
            final CivilizationBonuses civilizationBonuses32;
            final CivilizationBonuses civilizationBonuses31;
            final CivilizationBonuses civilizationBonuses30;
            final CivilizationBonuses civilizationBonuses42;
            final CivilizationBonuses civilizationBonuses29 = civilizationBonuses42 = (civilizationBonuses30 = (civilizationBonuses31 = (civilizationBonuses32 = (civBonuses7 = (civBonuses8 = this.civBonuses)))));
            civilizationBonuses42.UnitsDefense += Game.getMilitaryAcademy_Defense(this.getCivID());
            final CivilizationBonuses civBonuses10;
            final CivilizationBonuses civBonuses9;
            final CivilizationBonuses civilizationBonuses36;
            final CivilizationBonuses civilizationBonuses35;
            final CivilizationBonuses civilizationBonuses34;
            final CivilizationBonuses civilizationBonuses43;
            final CivilizationBonuses civilizationBonuses33 = civilizationBonuses43 = (civilizationBonuses34 = (civilizationBonuses35 = (civilizationBonuses36 = (civBonuses9 = (civBonuses10 = this.civBonuses)))));
            civilizationBonuses43.MaintenanceCost += Game.getMilitaryAcademy_MaintenanceCost(this.getCivID());
            final CivilizationBonuses civBonuses12;
            final CivilizationBonuses civBonuses11;
            final CivilizationBonuses civilizationBonuses40;
            final CivilizationBonuses civilizationBonuses39;
            final CivilizationBonuses civilizationBonuses38;
            final CivilizationBonuses civilizationBonuses44;
            final CivilizationBonuses civilizationBonuses37 = civilizationBonuses44 = (civilizationBonuses38 = (civilizationBonuses39 = (civilizationBonuses40 = (civBonuses11 = (civBonuses12 = this.civBonuses)))));
            civilizationBonuses44.RegimentsLimit += Game.getMilitaryAcademy_RegimentsLimit(this.getCivID());
        }
    }
    
    public final boolean upgradeNuclearReactor() {
        if (this.fGold >= Game.getNuclearReactor_Cost(this.getCivID()) && this.getNuclearReactorLevel() < Game.getNuclearReactor_MaxLvl(this.getCivID())) {
            this.fGold -= Game.getNuclearReactor_Cost(this.getCivID());
            final CivilizationBonuses civBonuses4;
            final CivilizationBonuses civBonuses3;
            final CivilizationBonuses civilizationBonuses16;
            final CivilizationBonuses civilizationBonuses15;
            final CivilizationBonuses civilizationBonuses14;
            final CivilizationBonuses civilizationBonuses21;
            final CivilizationBonuses civilizationBonuses13 = civilizationBonuses21 = (civilizationBonuses14 = (civilizationBonuses15 = (civilizationBonuses16 = (civBonuses3 = (civBonuses4 = this.civBonuses)))));
            civilizationBonuses21.ProductionEfficiency -= Game.getNuclearReactor_ProductionEfficiency(this.getCivID());
            this.setNuclearReactorLevel(this.getNuclearReactorLevel() + 1);
            final CivilizationBonuses civBonuses6;
            final CivilizationBonuses civBonuses5;
            final CivilizationBonuses civilizationBonuses20;
            final CivilizationBonuses civilizationBonuses19;
            final CivilizationBonuses civilizationBonuses18;
            final CivilizationBonuses civilizationBonuses22;
            final CivilizationBonuses civilizationBonuses17 = civilizationBonuses22 = (civilizationBonuses18 = (civilizationBonuses19 = (civilizationBonuses20 = (civBonuses5 = (civBonuses6 = this.civBonuses)))));
            civilizationBonuses22.ProductionEfficiency += Game.getNuclearReactor_ProductionEfficiency(this.getCivID());
            for (int i = 0; i < this.getNumOfProvinces(); ++i) {
                Game.getProvince(this.getProvinceID(i)).updateProvinceIncome();
            }
            Game.gameThread.addCivUpdateTotalIncomePerMonth(this.getCivID());
            return true;
        }
        return false;
    }
    
    public final void buildNuclearReactor_Bonuses() {
        if (this.getNuclearReactorLevel() > 0) {
            final CivilizationBonuses civBonuses3;
            final CivilizationBonuses civBonuses2;
            final CivilizationBonuses civilizationBonuses10;
            final CivilizationBonuses civilizationBonuses9;
            final CivilizationBonuses civilizationBonuses8;
            final CivilizationBonuses civilizationBonuses11;
            final CivilizationBonuses civilizationBonuses7 = civilizationBonuses11 = (civilizationBonuses8 = (civilizationBonuses9 = (civilizationBonuses10 = (civBonuses2 = (civBonuses3 = this.civBonuses)))));
            civilizationBonuses11.ProductionEfficiency += Game.getNuclearReactor_ProductionEfficiency(this.getCivID());
        }
    }
    
    public final boolean upgradeCapitalCity() {
        if (this.fGold >= Game.getCapital_Cost(this.getCivID()) && this.getCapitalLevel() < Game.getCapital_MaxLvl(this.getCivID())) {
            DesktopLauncher.SMS("UpgradeCapital: " + this.getCivID());
            this.fGold -= Game.getCapital_Cost(this.getCivID());
            final CivilizationBonuses civBonuses6;
            final CivilizationBonuses civBonuses5;
            final CivilizationBonuses civilizationBonuses28;
            final CivilizationBonuses civilizationBonuses27;
            final CivilizationBonuses civilizationBonuses26;
            final CivilizationBonuses civilizationBonuses41;
            final CivilizationBonuses civilizationBonuses25 = civilizationBonuses41 = (civilizationBonuses26 = (civilizationBonuses27 = (civilizationBonuses28 = (civBonuses5 = (civBonuses6 = this.civBonuses)))));
            civilizationBonuses41.MonthlyIncome -= Game.getCapital_Income(this.getCivID());
            final CivilizationBonuses civBonuses8;
            final CivilizationBonuses civBonuses7;
            final CivilizationBonuses civilizationBonuses32;
            final CivilizationBonuses civilizationBonuses31;
            final CivilizationBonuses civilizationBonuses30;
            final CivilizationBonuses civilizationBonuses42;
            final CivilizationBonuses civilizationBonuses29 = civilizationBonuses42 = (civilizationBonuses30 = (civilizationBonuses31 = (civilizationBonuses32 = (civBonuses7 = (civBonuses8 = this.civBonuses)))));
            civilizationBonuses42.ProvinceMaintenance -= Game.getCapital_ProvincesMaintenance(this.getCivID()) * 100.0f;
            this.setCapitalLevel(this.getCapitalLevel() + 1);
            final CivilizationBonuses civBonuses10;
            final CivilizationBonuses civBonuses9;
            final CivilizationBonuses civilizationBonuses36;
            final CivilizationBonuses civilizationBonuses35;
            final CivilizationBonuses civilizationBonuses34;
            final CivilizationBonuses civilizationBonuses43;
            final CivilizationBonuses civilizationBonuses33 = civilizationBonuses43 = (civilizationBonuses34 = (civilizationBonuses35 = (civilizationBonuses36 = (civBonuses9 = (civBonuses10 = this.civBonuses)))));
            civilizationBonuses43.MonthlyIncome += Game.getCapital_Income(this.getCivID());
            final CivilizationBonuses civBonuses12;
            final CivilizationBonuses civBonuses11;
            final CivilizationBonuses civilizationBonuses40;
            final CivilizationBonuses civilizationBonuses39;
            final CivilizationBonuses civilizationBonuses38;
            final CivilizationBonuses civilizationBonuses44;
            final CivilizationBonuses civilizationBonuses37 = civilizationBonuses44 = (civilizationBonuses38 = (civilizationBonuses39 = (civilizationBonuses40 = (civBonuses11 = (civBonuses12 = this.civBonuses)))));
            civilizationBonuses44.ProvinceMaintenance += Game.getCapital_ProvincesMaintenance(this.getCivID()) * 100.0f;
            Game.gameThread.addCivUpdateTotalIncomePerMonth(this.getCivID());
            return true;
        }
        return false;
    }
    
    public final boolean upgradeCapitalCity_Client() {
        DesktopLauncher.SMS("UpgradeCapital: " + this.getCivID());
        this.fGold -= Game.getCapital_Cost(this.getCivID());
        final CivilizationBonuses civBonuses6;
        final CivilizationBonuses civBonuses5;
        final CivilizationBonuses civilizationBonuses28;
        final CivilizationBonuses civilizationBonuses27;
        final CivilizationBonuses civilizationBonuses26;
        final CivilizationBonuses civilizationBonuses41;
        final CivilizationBonuses civilizationBonuses25 = civilizationBonuses41 = (civilizationBonuses26 = (civilizationBonuses27 = (civilizationBonuses28 = (civBonuses5 = (civBonuses6 = this.civBonuses)))));
        civilizationBonuses41.MonthlyIncome -= Game.getCapital_Income(this.getCivID());
        final CivilizationBonuses civBonuses8;
        final CivilizationBonuses civBonuses7;
        final CivilizationBonuses civilizationBonuses32;
        final CivilizationBonuses civilizationBonuses31;
        final CivilizationBonuses civilizationBonuses30;
        final CivilizationBonuses civilizationBonuses42;
        final CivilizationBonuses civilizationBonuses29 = civilizationBonuses42 = (civilizationBonuses30 = (civilizationBonuses31 = (civilizationBonuses32 = (civBonuses7 = (civBonuses8 = this.civBonuses)))));
        civilizationBonuses42.ProvinceMaintenance -= Game.getCapital_ProvincesMaintenance(this.getCivID()) * 100.0f;
        this.setCapitalLevel(this.getCapitalLevel() + 1);
        final CivilizationBonuses civBonuses10;
        final CivilizationBonuses civBonuses9;
        final CivilizationBonuses civilizationBonuses36;
        final CivilizationBonuses civilizationBonuses35;
        final CivilizationBonuses civilizationBonuses34;
        final CivilizationBonuses civilizationBonuses43;
        final CivilizationBonuses civilizationBonuses33 = civilizationBonuses43 = (civilizationBonuses34 = (civilizationBonuses35 = (civilizationBonuses36 = (civBonuses9 = (civBonuses10 = this.civBonuses)))));
        civilizationBonuses43.MonthlyIncome += Game.getCapital_Income(this.getCivID());
        final CivilizationBonuses civBonuses12;
        final CivilizationBonuses civBonuses11;
        final CivilizationBonuses civilizationBonuses40;
        final CivilizationBonuses civilizationBonuses39;
        final CivilizationBonuses civilizationBonuses38;
        final CivilizationBonuses civilizationBonuses44;
        final CivilizationBonuses civilizationBonuses37 = civilizationBonuses44 = (civilizationBonuses38 = (civilizationBonuses39 = (civilizationBonuses40 = (civBonuses11 = (civBonuses12 = this.civBonuses)))));
        civilizationBonuses44.ProvinceMaintenance += Game.getCapital_ProvincesMaintenance(this.getCivID()) * 100.0f;
        Game.gameThread.addCivUpdateTotalIncomePerMonth(this.getCivID());
        return true;
    }
    
    public final void buildCapitalCity_Bonuses() {
        if (this.getCapitalLevel() > 0) {
            final CivilizationBonuses civBonuses4;
            final CivilizationBonuses civBonuses3;
            final CivilizationBonuses civilizationBonuses16;
            final CivilizationBonuses civilizationBonuses15;
            final CivilizationBonuses civilizationBonuses14;
            final CivilizationBonuses civilizationBonuses21;
            final CivilizationBonuses civilizationBonuses13 = civilizationBonuses21 = (civilizationBonuses14 = (civilizationBonuses15 = (civilizationBonuses16 = (civBonuses3 = (civBonuses4 = this.civBonuses)))));
            civilizationBonuses21.MonthlyIncome += Game.getCapital_Income(this.getCivID());
            final CivilizationBonuses civBonuses6;
            final CivilizationBonuses civBonuses5;
            final CivilizationBonuses civilizationBonuses20;
            final CivilizationBonuses civilizationBonuses19;
            final CivilizationBonuses civilizationBonuses18;
            final CivilizationBonuses civilizationBonuses22;
            final CivilizationBonuses civilizationBonuses17 = civilizationBonuses22 = (civilizationBonuses18 = (civilizationBonuses19 = (civilizationBonuses20 = (civBonuses5 = (civBonuses6 = this.civBonuses)))));
            civilizationBonuses22.ProvinceMaintenance += Game.getCapital_ProvincesMaintenance(this.getCivID()) * 100.0f;
        }
    }
    
    public final void buildSupremeCourt_Bonuses() {
        this.civData3.setCorruption(this.civData3.getCorruption() + GameValues.supremeCourt.SUPREME_COURT_CORRUPTION_REDUCTION_PER_LVL * this.getSupremeCourtLevel());
    }
    
    public final boolean upgradeSupremeCourt() {
        if (this.fGold >= Game.getSupremeCourt_Cost(this.getCivID()) && this.getSupremeCourtLevel() < Game.getSupremeCourt_MaxLvl(this.getCivID())) {
            this.fGold -= Game.getSupremeCourt_Cost(this.getCivID());
            this.setSupremeCourtLevel(this.getSupremeCourtLevel() + 1);
            this.civData3.setCorruption(this.civData3.getCorruption() + GameValues.supremeCourt.SUPREME_COURT_CORRUPTION_REDUCTION_PER_LVL);
            for (int i = 0; i < this.getNumOfProvinces(); ++i) {
                Game.getProvince(this.getProvinceID(i)).updateProvinceIncome();
            }
            Game.gameThread.addCivUpdateTotalIncomePerMonth(this.getCivID());
            return true;
        }
        return false;
    }
    
    public final void updateRegimentsLimit() {
        this.iRegimentsLimit = (int)((Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENTS_LIMIT_BASE_VALUE + this.civBonuses.RegimentsLimit + GameValues.army.REGIMENTS_LIMIT_PER_VASSAL * this.diplomacy.iVassalsSize + this.getRegimentsLimit_FromAllianceSpecial() + this.getRegimentsLimit_Manpower()) * ((this.getCivID() == this.getPuppetOfCivID()) ? 1.0f : GameValues.army.REGIMENTS_LIMIT_VASSAL));
    }
    
    public final int getRegimentsLimit_Manpower() {
        double sum = 0.0;
        for (int i = this.getNumOfProvinces() - 1; i >= 0; --i) {
            sum += Game.getProvince(this.getProvinceID(i)).getManpower();
        }
        return (int)(sum * GameValues.army.REGIMENTS_LIMIT_MANPOWER_LEVEL);
    }
    
    public final float getRegimentsLimit_FromAllianceSpecial() {
        final float sum = 0.0f;
        for (int i = 0; i < this.inAllianceSize; ++i) {
            if (Game.alliancesSpecial.get(this.inAlliance.get(i)).iLeaderCivID == this.getCivID() && Game.alliancesSpecial.get(this.inAlliance.get(i)).typeOfAlliance == 0) {
                this.iRegimentsLimit += (int)GameValues.hre.HRE_EMPEROR_REGIMENTS;
            }
        }
        return 0.0f;
    }
    
    public final void updateMorale_Reinforce() {
        final float maxMorale = this.getMaxMorale();
        final float moraleRecovery = this.getMoraleRecoveryPerMonth();
        int maxReinforce = this.getMaxReinforce();
        final float maxRegimentSize = (float)Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE;
        float reinforceCost = 0.0f;
        for (int i = 0; i < this.iArmyPositionSize; ++i) {
            final Province province = Game.getProvince(this.getArmyPosition(i));
            for (int j = 0; j < province.getArmySize(); ++j) {
                final ArmyDivision army = province.getArmy(j);
                if (!Game.getProvince(army.provinceID).getSeaProvince() && !army.inBattle && !army.inRetreat && army.civID == this.getCivID() && army.key.equals(this.getArmyPositionKey(i))) {
                    army.updateMorale_Regiments(maxMorale, moraleRecovery);
                    reinforceCost += army.updateReinforce(maxReinforce, maxRegimentSize);
                    maxReinforce = (int)Math.max(maxReinforce, this.fManpower);
                }
            }
        }
        if (this.getCivID() == Game.player.iCivID && reinforceCost >= 0.01f) {
            final Stats civStats3;
            final Stats civStats2;
            final Stats stats3;
            final Stats stats4;
            final Stats stats2 = stats4 = (stats3 = (civStats2 = (civStats3 = Game.stats.civStats)));
            stats4.rf += (int)(reinforceCost * 100.0f);
            Game.player.addNotification_Reinforce(new Notification(Notification.Notification_Type.REINFORCE_ARMY_COST, Game.lang.get("ReinforceCost") + ": " + CFG.getPrecision2(reinforceCost, (reinforceCost > 99.9f) ? 1 : ((reinforceCost < 0.1f) ? 100 : 10)), Images.gold, Game_Calendar.TURN_ID, Notification.Notification_BG.NEUTRAL_BG, (int)(reinforceCost * 100.0f)));
        }
    }
    
    public final float getMaxMorale() {
        return (GameValues.army.MORALE_BASE_VALUE + this.civBonuses.MaxMorale) * GameValues.budget.MILITARY_LEVEL_MORALE[this.getMilitaryLevel()];
    }
    
    public final float getMoraleRecoveryPerMonth() {
        return GameValues.army.MORALE_RECOVERY_PER_MONTH * (1.0f + this.civBonuses.ArmyMoraleRecovery + this.civStability_LostFrom100 * GameValues.civStability.CS_ARMY_MORALE_RECOVERY_PER_POINT);
    }
    
    public final int getMaxReinforce() {
        if (this.fManpower < GameValues.army.REINFORCE_MIN_MANPOWER) {
            return 0;
        }
        return (int)Math.min(this.fManpower, Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE * ((GameValues.army.REINFORCE_PER_MONTH + this.civBonuses.ReinforcementSpeed) * GameValues.budget.MILITARY_LEVEL_REINFORCE_SPEED[this.getMilitaryLevel()]));
    }
    
    public final long getPopulationTotal() {
        long out = 0L;
        for (int i = this.getNumOfProvinces() - 1; i >= 0; --i) {
            out += Game.getProvince(this.getProvinceID(i)).getPopulationTotal();
        }
        return out;
    }
    
    public final float getAverageGrowthRate() {
        float out = 0.0f;
        for (int i = this.getNumOfProvinces() - 1; i >= 0; --i) {
            out += Game.getProvince(this.getProvinceID(i)).getGrowthRateWithBonuses();
        }
        return out / this.getNumOfProvinces();
    }
    
    public final float getEconomyTotal() {
        float out = 0.0f;
        for (int i = this.getNumOfProvinces() - 1; i >= 0; --i) {
            out += Game.getProvince(this.getProvinceID(i)).getEconomyWithBonuses();
        }
        return out;
    }
    
    public final float getEconomyTotal_CivRank() {
        float out = 0.0f;
        for (int i = this.getNumOfProvinces() - 1; i >= 0; --i) {
            out += Game.getProvince(this.getProvinceID(i)).getEconomyWithBonuses() * (1.0f + (Game.getProvince(i).haveACore ? 0.0f : GameValues.civRank.CIV_SCORE_NON_CORE) + ((Game.getProvince(this.getProvinceID(i)).getReligion() == this.getReligionID()) ? 0.0f : GameValues.civRank.CIV_SCORE_DIFFERENT_RELIGION));
        }
        return out;
    }
    
    public final float getTaxEfficiencyTotal_CivRank() {
        float out = 0.0f;
        for (int i = this.getNumOfProvinces() - 1; i >= 0; --i) {
            out += Game.getProvince(this.getProvinceID(i)).getTaxEfficiencyWithBonuses() * (1.0f + (Game.getProvince(i).haveACore ? 0.0f : GameValues.civRank.CIV_SCORE_NON_CORE) + ((Game.getProvince(this.getProvinceID(i)).getReligion() == this.getReligionID()) ? 0.0f : GameValues.civRank.CIV_SCORE_DIFFERENT_RELIGION));
        }
        return out;
    }
    
    public final float getTaxEfficiencyTotal() {
        float out = 0.0f;
        for (int i = this.getNumOfProvinces() - 1; i >= 0; --i) {
            out += Game.getProvince(this.getProvinceID(i)).getTaxEfficiencyWithBonuses();
        }
        return out;
    }
    
    public final float getTotalProvinceIncome() {
        float out = 0.0f;
        for (int i = this.getNumOfProvinces() - 1; i >= 0; --i) {
            out += Game.getProvince(this.getProvinceID(i)).getProvinceIncome();
        }
        return out;
    }
    
    public final float getTotalProvinceIncomeTax() {
        float out = 0.0f;
        for (int i = this.getNumOfProvinces() - 1; i >= 0; --i) {
            out += Game.getProvince(this.getProvinceID(i)).fProvinceIncomeTaxation;
        }
        return out;
    }
    
    public final float getAverageTaxEfficiency() {
        float out = 0.0f;
        for (int i = this.getNumOfProvinces() - 1; i >= 0; --i) {
            out += Game.getProvince(this.getProvinceID(i)).getTaxEfficiencyWithBonuses();
        }
        return out / this.getNumOfProvinces();
    }
    
    public final float getTotalIncome() {
        float out = 0.0f;
        for (int i = this.getNumOfProvinces() - 1; i >= 0; --i) {
            out += Game.getProvince(this.getProvinceID(i)).getProvinceIncome() - Game.getProvince(this.getProvinceID(i)).fProvinceMaintenance;
        }
        return out;
    }
    
    public final float getTotalLoot() {
        float out = 0.0f;
        for (int i = this.getNumOfProvinces() - 1; i >= 0; --i) {
            out += Game.getLootValue(this.getProvinceID(i));
        }
        return out;
    }
    
    public final float getTotalIncomeFromEconomy() {
        float out = 0.0f;
        for (int i = this.getNumOfProvinces() - 1; i >= 0; --i) {
            out += Game.getIncomeFromEconomy(this.getProvinceID(i));
        }
        return out;
    }
    
    public final float getTotalIncomeFromProduction() {
        float out = 0.0f;
        for (int i = this.getNumOfProvinces() - 1; i >= 0; --i) {
            out += ResourcesManager.getMonthlyIncome(this.getProvinceID(i));
        }
        return out;
    }
    
    public final int getTotalGenerals() {
        int outGenerals = 0;
        for (int i = 0; i < this.iArmyPositionSize; ++i) {
            for (int j = 0; j < Game.getProvince(this.getArmyPosition(i)).getArmySize(); ++j) {
                if (this.getCivID() == Game.getProvince(this.getArmyPosition(i)).getArmy(j).civID && this.getArmyPositionKey(i).equals(Game.getProvince(this.getArmyPosition(i)).getArmy(j).key) && Game.getProvince(this.getArmyPosition(i)).getArmy(j).armyGeneral != null) {
                    ++outGenerals;
                }
            }
        }
        return this.lGeneralsNotAssigned.size() + outGenerals;
    }
    
    public final int getGeneralsNotAssignedSize() {
        return this.lGeneralsNotAssigned.size();
    }
    
    public final int getArmySize() {
        int out = 0;
        for (int i = 0; i < this.iArmyPositionSize; ++i) {
            for (int j = 0; j < Game.getProvince(this.getArmyPosition(i)).getArmySize(); ++j) {
                if (this.getCivID() == Game.getProvince(this.getArmyPosition(i)).getArmy(j).civID && this.getArmyPositionKey(i).equals(Game.getProvince(this.getArmyPosition(i)).getArmy(j).key)) {
                    out += Game.getProvince(this.getArmyPosition(i)).getArmy(j).iArmy;
                }
            }
        }
        return out;
    }
    
    public final int getArmyRegimentSize() {
        return this.iRegiments;
    }
    
    public final int updateArmyRegimentSize() {
        this.iRegiments = 0;
        for (int i = 0; i < this.iArmyPositionSize; ++i) {
            final Province province = Game.getProvince(this.getArmyPosition(i));
            for (int j = 0; j < province.getArmySize(); ++j) {
                final ArmyDivision army = province.getArmy(j);
                if (this.getCivID() == army.civID && this.getArmyPositionKey(i).equals(army.key)) {
                    this.iRegiments += army.iArmyRegimentSize;
                }
            }
        }
        return this.iRegiments;
    }
    
    public final void updateBuildingLimit() {
        for (int o = 0; o < this.getNumOfProvinces(); ++o) {
            Game.getProvince(this.getProvinceID(o)).updateBuildingLimit();
        }
    }
    
    public final void updateInfrastructureMax() {
        for (int o = 0; o < this.getNumOfProvinces(); ++o) {
            Game.getProvince(this.getProvinceID(o)).updateInfrastructureMax();
        }
    }
    
    public boolean addNukeProduction() {
        if (this.getNuclearReactorLevel() < GameValues.atomic.NUCLEAR_REACTOR_LVL_TO_CONSTRUCT_NUKE) {
            return false;
        }
        if (this.fGold < Game.getAtomicBombCost(this.getCivID())) {
            return false;
        }
        final Civilization civ3;
        final Civilization civ2;
        final Civilization civilization10;
        final Civilization civilization9;
        final Civilization civilization8;
        final Civilization civilization11;
        final Civilization civilization7 = civilization11 = (civilization8 = (civilization9 = (civilization10 = (civ2 = (civ3 = Game.getCiv(this.getCivID()))))));
        civilization11.fGold -= Game.getAtomicBombCost(this.getCivID());
        final int investTime = Game.getAtomicBombProductionTime(this.getCivID());
        this.nukesDaysLeft.add(new ProvinceInvest(investTime, investTime));
        this.iNukesSize = this.nukesDaysLeft.size();
        Game.gameThreadTurns.addCivsNukes(this.getCivID());
        return true;
    }
    
    public void addNukeProduction_Load(final int daysLeft, final int investTime) {
        this.nukesDaysLeft.add(new ProvinceInvest(daysLeft, investTime));
        this.iNukesSize = this.nukesDaysLeft.size();
        Game.gameThreadTurns.addCivsNukes(this.getCivID());
    }
    
    public void updateNukeProduction(final int turns) {
        if (this.iNukesSize > 0) {
            final ProvinceInvest provinceInvest21;
            final ProvinceInvest provinceInvest20;
            final ProvinceInvest provinceInvest19;
            final ProvinceInvest provinceInvest18;
            final ProvinceInvest provinceInvest17;
            final ProvinceInvest provinceInvest22;
            final ProvinceInvest provinceInvest16 = provinceInvest22 = (provinceInvest17 = (provinceInvest18 = (provinceInvest19 = (provinceInvest20 = (provinceInvest21 = this.nukesDaysLeft.get(0))))));
            provinceInvest22.daysLeft -= turns;
            if (this.nukesDaysLeft.get(0).daysLeft <= 0) {
                this.nukesDaysLeft.remove(0);
                this.iNukesSize = this.nukesDaysLeft.size();
                this.setNukes(this.getNukes() + 1);
                if (this.getCivID() == Game.player.iCivID) {
                    InGame_Info.iCivID = Game.player.iCivID;
                    InGame_Info.iCivID2 = 0;
                    Game.menuManager.rebuildInGame_Info(Game.lang.get("AnAtomicBombHasBeenBuilt"), Game.lang.get("AtomicBombs") + ": " + this.getNukes());
                    InGame_Info.imgID = Images.infoAtomic;
                }
                if (this.iNukesSize == 0) {
                    Game.gameThreadTurns.removeCivsNukes(this.getCivID());
                }
            }
        }
    }
    
    public final void setManpower(final double nManpower) {
        this.fManpower = Math.max(0.0, Math.min(Math.max(this.fManpowerMax, this.fManpower), nManpower));
    }
    
    public final boolean haveAdvantage(final int iAdvantageID, final int iLevel) {
        for (int i = 0; i < this.iAdvantagesSize; ++i) {
            if (this.lAdvantages.get(i).id == iAdvantageID) {
                return this.lAdvantages.get(i).lvl >= iLevel;
            }
        }
        return false;
    }
    
    public final boolean haveAdvantageMaxLvl(final int iAdvantageID) {
        for (int i = 0; i < this.iAdvantagesSize; ++i) {
            if (this.lAdvantages.get(i).id == iAdvantageID) {
                return this.lAdvantages.get(i).lvl >= AdvantagesManager.advantages.get(iAdvantageID).ImageID.length - 1;
            }
        }
        return false;
    }
    
    public final int getAdvantageLvl(final int iAdvantageID) {
        for (int i = 0; i < this.iAdvantagesSize; ++i) {
            if (this.lAdvantages.get(i).id == iAdvantageID) {
                return this.lAdvantages.get(i).lvl;
            }
        }
        return -1;
    }
    
    public final boolean canUnlockAdvantage(final int iAdvantageID, final int iLevel) {
        if (iLevel == 0) {
            return true;
        }
        for (int i = 0; i < this.iAdvantagesSize; ++i) {
            if (this.lAdvantages.get(i).id == iAdvantageID) {
                return this.lAdvantages.get(i).lvl + 1 >= iLevel;
            }
        }
        return false;
    }
    
    public final void addAdvantage(final int iAdvantageID, final int iLevel) {
        if (!this.haveAdvantage(iAdvantageID, iLevel)) {
            if (iLevel == 0) {
                this.lAdvantages.add(new CivilizationLegacy(iAdvantageID, iLevel));
                this.iAdvantagesSize = this.lAdvantages.size();
            }
            else {
                for (int i = 0; i < this.iAdvantagesSize; ++i) {
                    if (this.lAdvantages.get(i).id == iAdvantageID && this.lAdvantages.get(i).lvl + 1 >= iLevel) {
                        this.lAdvantages.get(i).lvl = iLevel;
                    }
                }
            }
        }
    }
    
    public final void updateTotalProvincesValue() {
        this.fTotalProvincesValue = 0.0f;
        for (int i = this.getNumOfProvinces() - 1; i >= 0; --i) {
            this.fTotalProvincesValue += Game.getProvince(this.getProvinceID(i)).fProvinceValue;
        }
    }
    
    public final void addCivilizationBonus_Temporary(final CivilizationBonuses nBonus) {
        this.updateCivilizationBonuses_Temporary(nBonus, 1.0f);
        this.civBonusesTemporary.add(nBonus);
        this.iCivBonusesTemporarySize = this.civBonusesTemporary.size();
    }
    
    public final void updateCivilizationBonuses_Temporary() {
        try {
            for (int i = this.iCivBonusesTemporarySize - 1; i >= 0; --i) {
                if (this.civBonusesTemporary.get(i).TempTurnID <= Game_Calendar.TURN_ID) {
                    this.updateCivilizationBonuses_Temporary(this.civBonusesTemporary.get(i), -1.0f);
                    this.civBonusesTemporary.remove(i);
                    this.iCivBonusesTemporarySize = this.civBonusesTemporary.size();
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateCivilizationBonuses_Temporary(final CivilizationBonuses nBonus, final float mod) {
        if (nBonus.MonthlyIncome != 0.0f) {
            final CivilizationBonuses civBonuses69;
            final CivilizationBonuses civBonuses68;
            final CivilizationBonuses civilizationBonuses406;
            final CivilizationBonuses civilizationBonuses405;
            final CivilizationBonuses civilizationBonuses404;
            final CivilizationBonuses civilizationBonuses671;
            final CivilizationBonuses civilizationBonuses403 = civilizationBonuses671 = (civilizationBonuses404 = (civilizationBonuses405 = (civilizationBonuses406 = (civBonuses68 = (civBonuses69 = this.civBonuses)))));
            civilizationBonuses671.MonthlyIncome += nBonus.MonthlyIncome * mod;
        }
        if (nBonus.TaxEfficiency != 0.0f) {
            final CivilizationBonuses civBonuses71;
            final CivilizationBonuses civBonuses70;
            final CivilizationBonuses civilizationBonuses410;
            final CivilizationBonuses civilizationBonuses409;
            final CivilizationBonuses civilizationBonuses408;
            final CivilizationBonuses civilizationBonuses672;
            final CivilizationBonuses civilizationBonuses407 = civilizationBonuses672 = (civilizationBonuses408 = (civilizationBonuses409 = (civilizationBonuses410 = (civBonuses70 = (civBonuses71 = this.civBonuses)))));
            civilizationBonuses672.TaxEfficiency += nBonus.TaxEfficiency * mod;
        }
        if (nBonus.IncomeProduction != 0.0f) {
            final CivilizationBonuses civBonuses73;
            final CivilizationBonuses civBonuses72;
            final CivilizationBonuses civilizationBonuses414;
            final CivilizationBonuses civilizationBonuses413;
            final CivilizationBonuses civilizationBonuses412;
            final CivilizationBonuses civilizationBonuses673;
            final CivilizationBonuses civilizationBonuses411 = civilizationBonuses673 = (civilizationBonuses412 = (civilizationBonuses413 = (civilizationBonuses414 = (civBonuses72 = (civBonuses73 = this.civBonuses)))));
            civilizationBonuses673.IncomeProduction += nBonus.IncomeProduction * mod;
            Game.gameThread.addCivUpdateTotalIncomePerMonth(this.getCivID());
        }
        if (nBonus.IncomeTaxation != 0.0f) {
            final CivilizationBonuses civBonuses75;
            final CivilizationBonuses civBonuses74;
            final CivilizationBonuses civilizationBonuses418;
            final CivilizationBonuses civilizationBonuses417;
            final CivilizationBonuses civilizationBonuses416;
            final CivilizationBonuses civilizationBonuses674;
            final CivilizationBonuses civilizationBonuses415 = civilizationBonuses674 = (civilizationBonuses416 = (civilizationBonuses417 = (civilizationBonuses418 = (civBonuses74 = (civBonuses75 = this.civBonuses)))));
            civilizationBonuses674.IncomeTaxation += nBonus.IncomeTaxation * mod;
            Game.gameThread.addCivUpdateTotalIncomePerMonth(this.getCivID());
        }
        if (nBonus.IncomeEconomy != 0.0f) {
            final CivilizationBonuses civBonuses77;
            final CivilizationBonuses civBonuses76;
            final CivilizationBonuses civilizationBonuses422;
            final CivilizationBonuses civilizationBonuses421;
            final CivilizationBonuses civilizationBonuses420;
            final CivilizationBonuses civilizationBonuses675;
            final CivilizationBonuses civilizationBonuses419 = civilizationBonuses675 = (civilizationBonuses420 = (civilizationBonuses421 = (civilizationBonuses422 = (civBonuses76 = (civBonuses77 = this.civBonuses)))));
            civilizationBonuses675.IncomeEconomy += nBonus.IncomeEconomy * mod;
            Game.gameThread.addCivUpdateTotalIncomePerMonth(this.getCivID());
        }
        if (nBonus.ProductionEfficiency != 0.0f) {
            final CivilizationBonuses civBonuses79;
            final CivilizationBonuses civBonuses78;
            final CivilizationBonuses civilizationBonuses426;
            final CivilizationBonuses civilizationBonuses425;
            final CivilizationBonuses civilizationBonuses424;
            final CivilizationBonuses civilizationBonuses676;
            final CivilizationBonuses civilizationBonuses423 = civilizationBonuses676 = (civilizationBonuses424 = (civilizationBonuses425 = (civilizationBonuses426 = (civBonuses78 = (civBonuses79 = this.civBonuses)))));
            civilizationBonuses676.ProductionEfficiency += nBonus.ProductionEfficiency * mod;
        }
        if (nBonus.MaintenanceCost != 0.0f) {
            final CivilizationBonuses civBonuses81;
            final CivilizationBonuses civBonuses80;
            final CivilizationBonuses civilizationBonuses430;
            final CivilizationBonuses civilizationBonuses429;
            final CivilizationBonuses civilizationBonuses428;
            final CivilizationBonuses civilizationBonuses677;
            final CivilizationBonuses civilizationBonuses427 = civilizationBonuses677 = (civilizationBonuses428 = (civilizationBonuses429 = (civilizationBonuses430 = (civBonuses80 = (civBonuses81 = this.civBonuses)))));
            civilizationBonuses677.MaintenanceCost += nBonus.MaintenanceCost * mod;
            Game.gameThread.addCivUpdateTotalIncomePerMonth(this.getCivID());
        }
        if (nBonus.BuildingsMaintenanceCost != 0.0f) {
            final CivilizationBonuses civBonuses83;
            final CivilizationBonuses civBonuses82;
            final CivilizationBonuses civilizationBonuses434;
            final CivilizationBonuses civilizationBonuses433;
            final CivilizationBonuses civilizationBonuses432;
            final CivilizationBonuses civilizationBonuses678;
            final CivilizationBonuses civilizationBonuses431 = civilizationBonuses678 = (civilizationBonuses432 = (civilizationBonuses433 = (civilizationBonuses434 = (civBonuses82 = (civBonuses83 = this.civBonuses)))));
            civilizationBonuses678.BuildingsMaintenanceCost += nBonus.BuildingsMaintenanceCost * mod;
            Game.gameThread.addCivUpdateTotalIncomePerMonth(this.getCivID());
        }
        if (nBonus.ProvinceMaintenance != 0.0f) {
            final CivilizationBonuses civBonuses85;
            final CivilizationBonuses civBonuses84;
            final CivilizationBonuses civilizationBonuses438;
            final CivilizationBonuses civilizationBonuses437;
            final CivilizationBonuses civilizationBonuses436;
            final CivilizationBonuses civilizationBonuses679;
            final CivilizationBonuses civilizationBonuses435 = civilizationBonuses679 = (civilizationBonuses436 = (civilizationBonuses437 = (civilizationBonuses438 = (civBonuses84 = (civBonuses85 = this.civBonuses)))));
            civilizationBonuses679.ProvinceMaintenance += nBonus.ProvinceMaintenance * mod;
            Game.gameThread.addCivUpdateTotalIncomePerMonth(this.getCivID());
        }
        if (nBonus.GrowthRate != 0.0f) {
            final CivilizationBonuses civBonuses87;
            final CivilizationBonuses civBonuses86;
            final CivilizationBonuses civilizationBonuses442;
            final CivilizationBonuses civilizationBonuses441;
            final CivilizationBonuses civilizationBonuses440;
            final CivilizationBonuses civilizationBonuses680;
            final CivilizationBonuses civilizationBonuses439 = civilizationBonuses680 = (civilizationBonuses440 = (civilizationBonuses441 = (civilizationBonuses442 = (civBonuses86 = (civBonuses87 = this.civBonuses)))));
            civilizationBonuses680.GrowthRate += nBonus.GrowthRate * mod;
            Game.getCiv(this.getCivID()).updateResearchPerMonth();
            Game.gameThread.addCivUpdateTotalIncomePerMonth(this.getCivID());
        }
        if (nBonus.MonthlyLegacy != 0.0f) {
            final CivilizationBonuses civBonuses89;
            final CivilizationBonuses civBonuses88;
            final CivilizationBonuses civilizationBonuses446;
            final CivilizationBonuses civilizationBonuses445;
            final CivilizationBonuses civilizationBonuses444;
            final CivilizationBonuses civilizationBonuses681;
            final CivilizationBonuses civilizationBonuses443 = civilizationBonuses681 = (civilizationBonuses444 = (civilizationBonuses445 = (civilizationBonuses446 = (civBonuses88 = (civBonuses89 = this.civBonuses)))));
            civilizationBonuses681.MonthlyLegacy += nBonus.MonthlyLegacy * mod;
            Game.gameThread.addCivUpdateLegacyPerMonth(this.getCivID());
        }
        if (nBonus.MonthlyLegacy_Percentage != 0.0f) {
            final CivilizationBonuses civBonuses91;
            final CivilizationBonuses civBonuses90;
            final CivilizationBonuses civilizationBonuses450;
            final CivilizationBonuses civilizationBonuses449;
            final CivilizationBonuses civilizationBonuses448;
            final CivilizationBonuses civilizationBonuses682;
            final CivilizationBonuses civilizationBonuses447 = civilizationBonuses682 = (civilizationBonuses448 = (civilizationBonuses449 = (civilizationBonuses450 = (civBonuses90 = (civBonuses91 = this.civBonuses)))));
            civilizationBonuses682.MonthlyLegacy_Percentage += nBonus.MonthlyLegacy_Percentage * mod;
            Game.gameThread.addCivUpdateLegacyPerMonth(this.getCivID());
        }
        if (nBonus.MaxManpower != 0.0f) {
            final CivilizationBonuses civBonuses93;
            final CivilizationBonuses civBonuses92;
            final CivilizationBonuses civilizationBonuses454;
            final CivilizationBonuses civilizationBonuses453;
            final CivilizationBonuses civilizationBonuses452;
            final CivilizationBonuses civilizationBonuses683;
            final CivilizationBonuses civilizationBonuses451 = civilizationBonuses683 = (civilizationBonuses452 = (civilizationBonuses453 = (civilizationBonuses454 = (civBonuses92 = (civBonuses93 = this.civBonuses)))));
            civilizationBonuses683.MaxManpower += nBonus.MaxManpower * mod;
            Game.gameThreadTurns.addCivUpdateMaxManpower(this.getCivID());
        }
        if (nBonus.MaxManpower_Percentage != 0.0f) {
            final CivilizationBonuses civBonuses95;
            final CivilizationBonuses civBonuses94;
            final CivilizationBonuses civilizationBonuses458;
            final CivilizationBonuses civilizationBonuses457;
            final CivilizationBonuses civilizationBonuses456;
            final CivilizationBonuses civilizationBonuses684;
            final CivilizationBonuses civilizationBonuses455 = civilizationBonuses684 = (civilizationBonuses456 = (civilizationBonuses457 = (civilizationBonuses458 = (civBonuses94 = (civBonuses95 = this.civBonuses)))));
            civilizationBonuses684.MaxManpower_Percentage += nBonus.MaxManpower_Percentage * mod;
            Game.gameThreadTurns.addCivUpdateMaxManpower(this.getCivID());
        }
        if (nBonus.ManpowerRecoverySpeed != 0.0f) {
            final CivilizationBonuses civBonuses97;
            final CivilizationBonuses civBonuses96;
            final CivilizationBonuses civilizationBonuses462;
            final CivilizationBonuses civilizationBonuses461;
            final CivilizationBonuses civilizationBonuses460;
            final CivilizationBonuses civilizationBonuses685;
            final CivilizationBonuses civilizationBonuses459 = civilizationBonuses685 = (civilizationBonuses460 = (civilizationBonuses461 = (civilizationBonuses462 = (civBonuses96 = (civBonuses97 = this.civBonuses)))));
            civilizationBonuses685.ManpowerRecoverySpeed += nBonus.ManpowerRecoverySpeed * mod;
        }
        if (nBonus.ReinforcementSpeed != 0.0f) {
            final CivilizationBonuses civBonuses99;
            final CivilizationBonuses civBonuses98;
            final CivilizationBonuses civilizationBonuses466;
            final CivilizationBonuses civilizationBonuses465;
            final CivilizationBonuses civilizationBonuses464;
            final CivilizationBonuses civilizationBonuses686;
            final CivilizationBonuses civilizationBonuses463 = civilizationBonuses686 = (civilizationBonuses464 = (civilizationBonuses465 = (civilizationBonuses466 = (civBonuses98 = (civBonuses99 = this.civBonuses)))));
            civilizationBonuses686.ReinforcementSpeed += nBonus.ReinforcementSpeed * mod;
        }
        if (nBonus.ArmyMoraleRecovery != 0.0f) {
            final CivilizationBonuses civBonuses101;
            final CivilizationBonuses civBonuses100;
            final CivilizationBonuses civilizationBonuses470;
            final CivilizationBonuses civilizationBonuses469;
            final CivilizationBonuses civilizationBonuses468;
            final CivilizationBonuses civilizationBonuses687;
            final CivilizationBonuses civilizationBonuses467 = civilizationBonuses687 = (civilizationBonuses468 = (civilizationBonuses469 = (civilizationBonuses470 = (civBonuses100 = (civBonuses101 = this.civBonuses)))));
            civilizationBonuses687.ArmyMoraleRecovery += nBonus.ArmyMoraleRecovery * mod;
        }
        if (nBonus.WarScoreCost != 0.0f) {
            final CivilizationBonuses civBonuses103;
            final CivilizationBonuses civBonuses102;
            final CivilizationBonuses civilizationBonuses474;
            final CivilizationBonuses civilizationBonuses473;
            final CivilizationBonuses civilizationBonuses472;
            final CivilizationBonuses civilizationBonuses688;
            final CivilizationBonuses civilizationBonuses471 = civilizationBonuses688 = (civilizationBonuses472 = (civilizationBonuses473 = (civilizationBonuses474 = (civBonuses102 = (civBonuses103 = this.civBonuses)))));
            civilizationBonuses688.WarScoreCost += nBonus.WarScoreCost * mod;
        }
        if (nBonus.ArmyMaintenance != 0.0f) {
            final CivilizationBonuses civBonuses105;
            final CivilizationBonuses civBonuses104;
            final CivilizationBonuses civilizationBonuses478;
            final CivilizationBonuses civilizationBonuses477;
            final CivilizationBonuses civilizationBonuses476;
            final CivilizationBonuses civilizationBonuses689;
            final CivilizationBonuses civilizationBonuses475 = civilizationBonuses689 = (civilizationBonuses476 = (civilizationBonuses477 = (civilizationBonuses478 = (civBonuses104 = (civBonuses105 = this.civBonuses)))));
            civilizationBonuses689.ArmyMaintenance += nBonus.ArmyMaintenance * mod;
            Game.gameThread.addCivUpdateArmyMaintenance(this.getCivID());
        }
        if (nBonus.RecruitmentTime != 0.0f) {
            final CivilizationBonuses civBonuses107;
            final CivilizationBonuses civBonuses106;
            final CivilizationBonuses civilizationBonuses482;
            final CivilizationBonuses civilizationBonuses481;
            final CivilizationBonuses civilizationBonuses480;
            final CivilizationBonuses civilizationBonuses690;
            final CivilizationBonuses civilizationBonuses479 = civilizationBonuses690 = (civilizationBonuses480 = (civilizationBonuses481 = (civilizationBonuses482 = (civBonuses106 = (civBonuses107 = this.civBonuses)))));
            civilizationBonuses690.RecruitmentTime += nBonus.RecruitmentTime * mod;
        }
        if (nBonus.RecruitArmyCost != 0.0f) {
            final CivilizationBonuses civBonuses109;
            final CivilizationBonuses civBonuses108;
            final CivilizationBonuses civilizationBonuses486;
            final CivilizationBonuses civilizationBonuses485;
            final CivilizationBonuses civilizationBonuses484;
            final CivilizationBonuses civilizationBonuses691;
            final CivilizationBonuses civilizationBonuses483 = civilizationBonuses691 = (civilizationBonuses484 = (civilizationBonuses485 = (civilizationBonuses486 = (civBonuses108 = (civBonuses109 = this.civBonuses)))));
            civilizationBonuses691.RecruitArmyCost += nBonus.RecruitArmyCost * mod;
        }
        if (nBonus.RecruitArmyFirstLineCost != 0.0f) {
            final CivilizationBonuses civBonuses111;
            final CivilizationBonuses civBonuses110;
            final CivilizationBonuses civilizationBonuses490;
            final CivilizationBonuses civilizationBonuses489;
            final CivilizationBonuses civilizationBonuses488;
            final CivilizationBonuses civilizationBonuses692;
            final CivilizationBonuses civilizationBonuses487 = civilizationBonuses692 = (civilizationBonuses488 = (civilizationBonuses489 = (civilizationBonuses490 = (civBonuses110 = (civBonuses111 = this.civBonuses)))));
            civilizationBonuses692.RecruitArmyFirstLineCost += nBonus.RecruitArmyFirstLineCost * mod;
        }
        if (nBonus.RecruitArmySecondLineCost != 0.0f) {
            final CivilizationBonuses civBonuses113;
            final CivilizationBonuses civBonuses112;
            final CivilizationBonuses civilizationBonuses494;
            final CivilizationBonuses civilizationBonuses493;
            final CivilizationBonuses civilizationBonuses492;
            final CivilizationBonuses civilizationBonuses693;
            final CivilizationBonuses civilizationBonuses491 = civilizationBonuses693 = (civilizationBonuses492 = (civilizationBonuses493 = (civilizationBonuses494 = (civBonuses112 = (civBonuses113 = this.civBonuses)))));
            civilizationBonuses693.RecruitArmySecondLineCost += nBonus.RecruitArmySecondLineCost * mod;
        }
        if (nBonus.ResearchPoints != 0.0f) {
            final CivilizationBonuses civBonuses115;
            final CivilizationBonuses civBonuses114;
            final CivilizationBonuses civilizationBonuses498;
            final CivilizationBonuses civilizationBonuses497;
            final CivilizationBonuses civilizationBonuses496;
            final CivilizationBonuses civilizationBonuses694;
            final CivilizationBonuses civilizationBonuses495 = civilizationBonuses694 = (civilizationBonuses496 = (civilizationBonuses497 = (civilizationBonuses498 = (civBonuses114 = (civBonuses115 = this.civBonuses)))));
            civilizationBonuses694.ResearchPoints += nBonus.ResearchPoints * mod;
            Game.gameThread.addCivUpdateResearchPerMonth(this.getCivID());
            Game.gameThread.addCivUpdateTotalIncomePerMonth(this.getCivID());
        }
        if (nBonus.Research != 0.0f) {
            final CivilizationBonuses civBonuses117;
            final CivilizationBonuses civBonuses116;
            final CivilizationBonuses civilizationBonuses502;
            final CivilizationBonuses civilizationBonuses501;
            final CivilizationBonuses civilizationBonuses500;
            final CivilizationBonuses civilizationBonuses695;
            final CivilizationBonuses civilizationBonuses499 = civilizationBonuses695 = (civilizationBonuses500 = (civilizationBonuses501 = (civilizationBonuses502 = (civBonuses116 = (civBonuses117 = this.civBonuses)))));
            civilizationBonuses695.Research += nBonus.Research * mod;
            Game.gameThread.addCivUpdateResearchPerMonth(this.getCivID());
            Game.gameThread.addCivUpdateTotalIncomePerMonth(this.getCivID());
        }
        if (nBonus.TechnologyCost != 0.0f) {
            final CivilizationBonuses civBonuses119;
            final CivilizationBonuses civBonuses118;
            final CivilizationBonuses civilizationBonuses506;
            final CivilizationBonuses civilizationBonuses505;
            final CivilizationBonuses civilizationBonuses504;
            final CivilizationBonuses civilizationBonuses696;
            final CivilizationBonuses civilizationBonuses503 = civilizationBonuses696 = (civilizationBonuses504 = (civilizationBonuses505 = (civilizationBonuses506 = (civBonuses118 = (civBonuses119 = this.civBonuses)))));
            civilizationBonuses696.TechnologyCost += nBonus.TechnologyCost * mod;
        }
        if (nBonus.ConstructionCost != 0.0f) {
            final CivilizationBonuses civBonuses121;
            final CivilizationBonuses civBonuses120;
            final CivilizationBonuses civilizationBonuses510;
            final CivilizationBonuses civilizationBonuses509;
            final CivilizationBonuses civilizationBonuses508;
            final CivilizationBonuses civilizationBonuses697;
            final CivilizationBonuses civilizationBonuses507 = civilizationBonuses697 = (civilizationBonuses508 = (civilizationBonuses509 = (civilizationBonuses510 = (civBonuses120 = (civBonuses121 = this.civBonuses)))));
            civilizationBonuses697.ConstructionCost += nBonus.ConstructionCost * mod;
        }
        if (nBonus.AdministrationBuildingsCost != 0.0f) {
            final CivilizationBonuses civBonuses123;
            final CivilizationBonuses civBonuses122;
            final CivilizationBonuses civilizationBonuses514;
            final CivilizationBonuses civilizationBonuses513;
            final CivilizationBonuses civilizationBonuses512;
            final CivilizationBonuses civilizationBonuses698;
            final CivilizationBonuses civilizationBonuses511 = civilizationBonuses698 = (civilizationBonuses512 = (civilizationBonuses513 = (civilizationBonuses514 = (civBonuses122 = (civBonuses123 = this.civBonuses)))));
            civilizationBonuses698.AdministrationBuildingsCost += nBonus.AdministrationBuildingsCost * mod;
        }
        if (nBonus.EconomyBuildingsCost != 0.0f) {
            final CivilizationBonuses civBonuses125;
            final CivilizationBonuses civBonuses124;
            final CivilizationBonuses civilizationBonuses518;
            final CivilizationBonuses civilizationBonuses517;
            final CivilizationBonuses civilizationBonuses516;
            final CivilizationBonuses civilizationBonuses699;
            final CivilizationBonuses civilizationBonuses515 = civilizationBonuses699 = (civilizationBonuses516 = (civilizationBonuses517 = (civilizationBonuses518 = (civBonuses124 = (civBonuses125 = this.civBonuses)))));
            civilizationBonuses699.EconomyBuildingsCost += nBonus.EconomyBuildingsCost * mod;
        }
        if (nBonus.MilitaryBuildingsCost != 0.0f) {
            final CivilizationBonuses civBonuses127;
            final CivilizationBonuses civBonuses126;
            final CivilizationBonuses civilizationBonuses522;
            final CivilizationBonuses civilizationBonuses521;
            final CivilizationBonuses civilizationBonuses520;
            final CivilizationBonuses civilizationBonuses700;
            final CivilizationBonuses civilizationBonuses519 = civilizationBonuses700 = (civilizationBonuses520 = (civilizationBonuses521 = (civilizationBonuses522 = (civBonuses126 = (civBonuses127 = this.civBonuses)))));
            civilizationBonuses700.MilitaryBuildingsCost += nBonus.MilitaryBuildingsCost * mod;
        }
        if (nBonus.WonderConstructionCost != 0.0f) {
            final CivilizationBonuses civBonuses129;
            final CivilizationBonuses civBonuses128;
            final CivilizationBonuses civilizationBonuses526;
            final CivilizationBonuses civilizationBonuses525;
            final CivilizationBonuses civilizationBonuses524;
            final CivilizationBonuses civilizationBonuses701;
            final CivilizationBonuses civilizationBonuses523 = civilizationBonuses701 = (civilizationBonuses524 = (civilizationBonuses525 = (civilizationBonuses526 = (civBonuses128 = (civBonuses129 = this.civBonuses)))));
            civilizationBonuses701.WonderConstructionCost += nBonus.WonderConstructionCost * mod;
        }
        if (nBonus.ConstructionTime != 0.0f) {
            final CivilizationBonuses civBonuses131;
            final CivilizationBonuses civBonuses130;
            final CivilizationBonuses civilizationBonuses530;
            final CivilizationBonuses civilizationBonuses529;
            final CivilizationBonuses civilizationBonuses528;
            final CivilizationBonuses civilizationBonuses702;
            final CivilizationBonuses civilizationBonuses527 = civilizationBonuses702 = (civilizationBonuses528 = (civilizationBonuses529 = (civilizationBonuses530 = (civBonuses130 = (civBonuses131 = this.civBonuses)))));
            civilizationBonuses702.ConstructionTime += nBonus.ConstructionTime * mod;
        }
        if (nBonus.InvestInEconomyCost != 0.0f) {
            final CivilizationBonuses civBonuses133;
            final CivilizationBonuses civBonuses132;
            final CivilizationBonuses civilizationBonuses534;
            final CivilizationBonuses civilizationBonuses533;
            final CivilizationBonuses civilizationBonuses532;
            final CivilizationBonuses civilizationBonuses703;
            final CivilizationBonuses civilizationBonuses531 = civilizationBonuses703 = (civilizationBonuses532 = (civilizationBonuses533 = (civilizationBonuses534 = (civBonuses132 = (civBonuses133 = this.civBonuses)))));
            civilizationBonuses703.InvestInEconomyCost += nBonus.InvestInEconomyCost * mod;
        }
        if (nBonus.IncreaseTaxEfficiencyCost != 0.0f) {
            final CivilizationBonuses civBonuses135;
            final CivilizationBonuses civBonuses134;
            final CivilizationBonuses civilizationBonuses538;
            final CivilizationBonuses civilizationBonuses537;
            final CivilizationBonuses civilizationBonuses536;
            final CivilizationBonuses civilizationBonuses704;
            final CivilizationBonuses civilizationBonuses535 = civilizationBonuses704 = (civilizationBonuses536 = (civilizationBonuses537 = (civilizationBonuses538 = (civBonuses134 = (civBonuses135 = this.civBonuses)))));
            civilizationBonuses704.IncreaseTaxEfficiencyCost += nBonus.IncreaseTaxEfficiencyCost * mod;
        }
        if (nBonus.IncreaseGrowthRateCost != 0.0f) {
            final CivilizationBonuses civBonuses137;
            final CivilizationBonuses civBonuses136;
            final CivilizationBonuses civilizationBonuses542;
            final CivilizationBonuses civilizationBonuses541;
            final CivilizationBonuses civilizationBonuses540;
            final CivilizationBonuses civilizationBonuses705;
            final CivilizationBonuses civilizationBonuses539 = civilizationBonuses705 = (civilizationBonuses540 = (civilizationBonuses541 = (civilizationBonuses542 = (civBonuses136 = (civBonuses137 = this.civBonuses)))));
            civilizationBonuses705.IncreaseGrowthRateCost += nBonus.IncreaseGrowthRateCost * mod;
        }
        if (nBonus.DevelopInfrastructureCost != 0.0f) {
            final CivilizationBonuses civBonuses139;
            final CivilizationBonuses civBonuses138;
            final CivilizationBonuses civilizationBonuses546;
            final CivilizationBonuses civilizationBonuses545;
            final CivilizationBonuses civilizationBonuses544;
            final CivilizationBonuses civilizationBonuses706;
            final CivilizationBonuses civilizationBonuses543 = civilizationBonuses706 = (civilizationBonuses544 = (civilizationBonuses545 = (civilizationBonuses546 = (civBonuses138 = (civBonuses139 = this.civBonuses)))));
            civilizationBonuses706.DevelopInfrastructureCost += nBonus.DevelopInfrastructureCost * mod;
        }
        if (nBonus.IncreaseManpowerCost != 0.0f) {
            final CivilizationBonuses civBonuses141;
            final CivilizationBonuses civBonuses140;
            final CivilizationBonuses civilizationBonuses550;
            final CivilizationBonuses civilizationBonuses549;
            final CivilizationBonuses civilizationBonuses548;
            final CivilizationBonuses civilizationBonuses707;
            final CivilizationBonuses civilizationBonuses547 = civilizationBonuses707 = (civilizationBonuses548 = (civilizationBonuses549 = (civilizationBonuses550 = (civBonuses140 = (civBonuses141 = this.civBonuses)))));
            civilizationBonuses707.IncreaseManpowerCost += nBonus.IncreaseManpowerCost * mod;
        }
        if (nBonus.GeneralAttack != 0) {
            final CivilizationBonuses civBonuses143;
            final CivilizationBonuses civBonuses142;
            final CivilizationBonuses civilizationBonuses554;
            final CivilizationBonuses civilizationBonuses553;
            final CivilizationBonuses civilizationBonuses552;
            final CivilizationBonuses civilizationBonuses708;
            final CivilizationBonuses civilizationBonuses551 = civilizationBonuses708 = (civilizationBonuses552 = (civilizationBonuses553 = (civilizationBonuses554 = (civBonuses142 = (civBonuses143 = this.civBonuses)))));
            civilizationBonuses708.GeneralAttack += (int)(nBonus.GeneralAttack * mod);
        }
        if (nBonus.GeneralDefense != 0) {
            final CivilizationBonuses civBonuses145;
            final CivilizationBonuses civBonuses144;
            final CivilizationBonuses civilizationBonuses558;
            final CivilizationBonuses civilizationBonuses557;
            final CivilizationBonuses civilizationBonuses556;
            final CivilizationBonuses civilizationBonuses709;
            final CivilizationBonuses civilizationBonuses555 = civilizationBonuses709 = (civilizationBonuses556 = (civilizationBonuses557 = (civilizationBonuses558 = (civBonuses144 = (civBonuses145 = this.civBonuses)))));
            civilizationBonuses709.GeneralDefense += (int)(nBonus.GeneralDefense * mod);
        }
        if (nBonus.UnitsAttack != 0) {
            final CivilizationBonuses civBonuses147;
            final CivilizationBonuses civBonuses146;
            final CivilizationBonuses civilizationBonuses562;
            final CivilizationBonuses civilizationBonuses561;
            final CivilizationBonuses civilizationBonuses560;
            final CivilizationBonuses civilizationBonuses710;
            final CivilizationBonuses civilizationBonuses559 = civilizationBonuses710 = (civilizationBonuses560 = (civilizationBonuses561 = (civilizationBonuses562 = (civBonuses146 = (civBonuses147 = this.civBonuses)))));
            civilizationBonuses710.UnitsAttack += (int)(nBonus.UnitsAttack * mod);
        }
        if (nBonus.UnitsDefense != 0) {
            final CivilizationBonuses civBonuses149;
            final CivilizationBonuses civBonuses148;
            final CivilizationBonuses civilizationBonuses566;
            final CivilizationBonuses civilizationBonuses565;
            final CivilizationBonuses civilizationBonuses564;
            final CivilizationBonuses civilizationBonuses711;
            final CivilizationBonuses civilizationBonuses563 = civilizationBonuses711 = (civilizationBonuses564 = (civilizationBonuses565 = (civilizationBonuses566 = (civBonuses148 = (civBonuses149 = this.civBonuses)))));
            civilizationBonuses711.UnitsDefense += (int)(nBonus.UnitsDefense * mod);
        }
        if (nBonus.AdvisorCost != 0.0f) {
            final CivilizationBonuses civBonuses151;
            final CivilizationBonuses civBonuses150;
            final CivilizationBonuses civilizationBonuses570;
            final CivilizationBonuses civilizationBonuses569;
            final CivilizationBonuses civilizationBonuses568;
            final CivilizationBonuses civilizationBonuses712;
            final CivilizationBonuses civilizationBonuses567 = civilizationBonuses712 = (civilizationBonuses568 = (civilizationBonuses569 = (civilizationBonuses570 = (civBonuses150 = (civBonuses151 = this.civBonuses)))));
            civilizationBonuses712.AdvisorCost += nBonus.AdvisorCost * mod;
        }
        if (nBonus.GeneralCost != 0.0f) {
            final CivilizationBonuses civBonuses153;
            final CivilizationBonuses civBonuses152;
            final CivilizationBonuses civilizationBonuses574;
            final CivilizationBonuses civilizationBonuses573;
            final CivilizationBonuses civilizationBonuses572;
            final CivilizationBonuses civilizationBonuses713;
            final CivilizationBonuses civilizationBonuses571 = civilizationBonuses713 = (civilizationBonuses572 = (civilizationBonuses573 = (civilizationBonuses574 = (civBonuses152 = (civBonuses153 = this.civBonuses)))));
            civilizationBonuses713.GeneralCost += nBonus.GeneralCost * mod;
        }
        if (nBonus.MaxMorale != 0.0f) {
            final CivilizationBonuses civBonuses155;
            final CivilizationBonuses civBonuses154;
            final CivilizationBonuses civilizationBonuses578;
            final CivilizationBonuses civilizationBonuses577;
            final CivilizationBonuses civilizationBonuses576;
            final CivilizationBonuses civilizationBonuses714;
            final CivilizationBonuses civilizationBonuses575 = civilizationBonuses714 = (civilizationBonuses576 = (civilizationBonuses577 = (civilizationBonuses578 = (civBonuses154 = (civBonuses155 = this.civBonuses)))));
            civilizationBonuses714.MaxMorale += nBonus.MaxMorale * mod;
        }
        if (nBonus.DiseaseDeathRate != 0.0f) {
            final CivilizationBonuses civBonuses157;
            final CivilizationBonuses civBonuses156;
            final CivilizationBonuses civilizationBonuses582;
            final CivilizationBonuses civilizationBonuses581;
            final CivilizationBonuses civilizationBonuses580;
            final CivilizationBonuses civilizationBonuses715;
            final CivilizationBonuses civilizationBonuses579 = civilizationBonuses715 = (civilizationBonuses580 = (civilizationBonuses581 = (civilizationBonuses582 = (civBonuses156 = (civBonuses157 = this.civBonuses)))));
            civilizationBonuses715.DiseaseDeathRate += nBonus.DiseaseDeathRate * mod;
        }
        if (nBonus.AllCharactersLifeExpectancy != 0) {
            final CivilizationBonuses civBonuses159;
            final CivilizationBonuses civBonuses158;
            final CivilizationBonuses civilizationBonuses586;
            final CivilizationBonuses civilizationBonuses585;
            final CivilizationBonuses civilizationBonuses584;
            final CivilizationBonuses civilizationBonuses716;
            final CivilizationBonuses civilizationBonuses583 = civilizationBonuses716 = (civilizationBonuses584 = (civilizationBonuses585 = (civilizationBonuses586 = (civBonuses158 = (civBonuses159 = this.civBonuses)))));
            civilizationBonuses716.AllCharactersLifeExpectancy += (int)(nBonus.AllCharactersLifeExpectancy * mod);
        }
        if (nBonus.BattleWidth != 0) {
            final CivilizationBonuses civBonuses161;
            final CivilizationBonuses civBonuses160;
            final CivilizationBonuses civilizationBonuses590;
            final CivilizationBonuses civilizationBonuses589;
            final CivilizationBonuses civilizationBonuses588;
            final CivilizationBonuses civilizationBonuses717;
            final CivilizationBonuses civilizationBonuses587 = civilizationBonuses717 = (civilizationBonuses588 = (civilizationBonuses589 = (civilizationBonuses590 = (civBonuses160 = (civBonuses161 = this.civBonuses)))));
            civilizationBonuses717.BattleWidth += (int)(nBonus.BattleWidth * mod);
        }
        if (nBonus.Discipline != 0.0f) {
            final CivilizationBonuses civBonuses163;
            final CivilizationBonuses civBonuses162;
            final CivilizationBonuses civilizationBonuses594;
            final CivilizationBonuses civilizationBonuses593;
            final CivilizationBonuses civilizationBonuses592;
            final CivilizationBonuses civilizationBonuses718;
            final CivilizationBonuses civilizationBonuses591 = civilizationBonuses718 = (civilizationBonuses592 = (civilizationBonuses593 = (civilizationBonuses594 = (civBonuses162 = (civBonuses163 = this.civBonuses)))));
            civilizationBonuses718.Discipline += nBonus.Discipline * mod;
        }
        if (nBonus.ManpowerRecoveryFromADisbandedArmy != 0.0f) {
            final CivilizationBonuses civBonuses165;
            final CivilizationBonuses civBonuses164;
            final CivilizationBonuses civilizationBonuses598;
            final CivilizationBonuses civilizationBonuses597;
            final CivilizationBonuses civilizationBonuses596;
            final CivilizationBonuses civilizationBonuses719;
            final CivilizationBonuses civilizationBonuses595 = civilizationBonuses719 = (civilizationBonuses596 = (civilizationBonuses597 = (civilizationBonuses598 = (civBonuses164 = (civBonuses165 = this.civBonuses)))));
            civilizationBonuses719.ManpowerRecoveryFromADisbandedArmy += nBonus.ManpowerRecoveryFromADisbandedArmy * mod;
        }
        if (nBonus.MaxInfrastructure != 0) {
            final CivilizationBonuses civBonuses167;
            final CivilizationBonuses civBonuses166;
            final CivilizationBonuses civilizationBonuses602;
            final CivilizationBonuses civilizationBonuses601;
            final CivilizationBonuses civilizationBonuses600;
            final CivilizationBonuses civilizationBonuses720;
            final CivilizationBonuses civilizationBonuses599 = civilizationBonuses720 = (civilizationBonuses600 = (civilizationBonuses601 = (civilizationBonuses602 = (civBonuses166 = (civBonuses167 = this.civBonuses)))));
            civilizationBonuses720.MaxInfrastructure += (int)(nBonus.MaxInfrastructure * mod);
            for (int o = 0; o < Game.getCiv(this.getCivID()).getNumOfProvinces(); ++o) {
                Game.getProvince(Game.getCiv(this.getCivID()).getProvinceID(o)).updateInfrastructureMax();
            }
        }
        if (nBonus.ArmyMovementSpeed != 0.0f) {
            final CivilizationBonuses civBonuses169;
            final CivilizationBonuses civBonuses168;
            final CivilizationBonuses civilizationBonuses606;
            final CivilizationBonuses civilizationBonuses605;
            final CivilizationBonuses civilizationBonuses604;
            final CivilizationBonuses civilizationBonuses721;
            final CivilizationBonuses civilizationBonuses603 = civilizationBonuses721 = (civilizationBonuses604 = (civilizationBonuses605 = (civilizationBonuses606 = (civBonuses168 = (civBonuses169 = this.civBonuses)))));
            civilizationBonuses721.ArmyMovementSpeed += nBonus.ArmyMovementSpeed * mod;
        }
        if (nBonus.SiegeEffectiveness != 0.0f) {
            final CivilizationBonuses civBonuses171;
            final CivilizationBonuses civBonuses170;
            final CivilizationBonuses civilizationBonuses610;
            final CivilizationBonuses civilizationBonuses609;
            final CivilizationBonuses civilizationBonuses608;
            final CivilizationBonuses civilizationBonuses722;
            final CivilizationBonuses civilizationBonuses607 = civilizationBonuses722 = (civilizationBonuses608 = (civilizationBonuses609 = (civilizationBonuses610 = (civBonuses170 = (civBonuses171 = this.civBonuses)))));
            civilizationBonuses722.SiegeEffectiveness += nBonus.SiegeEffectiveness * mod;
        }
        if (nBonus.ImproveRelationsModifier != 0.0f) {
            final CivilizationBonuses civBonuses173;
            final CivilizationBonuses civBonuses172;
            final CivilizationBonuses civilizationBonuses614;
            final CivilizationBonuses civilizationBonuses613;
            final CivilizationBonuses civilizationBonuses612;
            final CivilizationBonuses civilizationBonuses723;
            final CivilizationBonuses civilizationBonuses611 = civilizationBonuses723 = (civilizationBonuses612 = (civilizationBonuses613 = (civilizationBonuses614 = (civBonuses172 = (civBonuses173 = this.civBonuses)))));
            civilizationBonuses723.ImproveRelationsModifier += nBonus.ImproveRelationsModifier * mod;
        }
        if (nBonus.IncomeFromVassals != 0.0f) {
            final CivilizationBonuses civBonuses175;
            final CivilizationBonuses civBonuses174;
            final CivilizationBonuses civilizationBonuses618;
            final CivilizationBonuses civilizationBonuses617;
            final CivilizationBonuses civilizationBonuses616;
            final CivilizationBonuses civilizationBonuses724;
            final CivilizationBonuses civilizationBonuses615 = civilizationBonuses724 = (civilizationBonuses616 = (civilizationBonuses617 = (civilizationBonuses618 = (civBonuses174 = (civBonuses175 = this.civBonuses)))));
            civilizationBonuses724.IncomeFromVassals += nBonus.IncomeFromVassals * mod;
            Game.gameThread.addCivUpdateTotalIncomePerMonth(this.iCivID);
        }
        if (nBonus.LoanInterest != 0.0f) {
            final CivilizationBonuses civBonuses177;
            final CivilizationBonuses civBonuses176;
            final CivilizationBonuses civilizationBonuses622;
            final CivilizationBonuses civilizationBonuses621;
            final CivilizationBonuses civilizationBonuses620;
            final CivilizationBonuses civilizationBonuses725;
            final CivilizationBonuses civilizationBonuses619 = civilizationBonuses725 = (civilizationBonuses620 = (civilizationBonuses621 = (civilizationBonuses622 = (civBonuses176 = (civBonuses177 = this.civBonuses)))));
            civilizationBonuses725.LoanInterest += nBonus.LoanInterest * mod;
        }
        if (nBonus.DiplomacyPoints != 0.0f) {
            final CivilizationBonuses civBonuses179;
            final CivilizationBonuses civBonuses178;
            final CivilizationBonuses civilizationBonuses626;
            final CivilizationBonuses civilizationBonuses625;
            final CivilizationBonuses civilizationBonuses624;
            final CivilizationBonuses civilizationBonuses726;
            final CivilizationBonuses civilizationBonuses623 = civilizationBonuses726 = (civilizationBonuses624 = (civilizationBonuses625 = (civilizationBonuses626 = (civBonuses178 = (civBonuses179 = this.civBonuses)))));
            civilizationBonuses726.DiplomacyPoints += nBonus.DiplomacyPoints * mod;
            Game.getCiv(this.getCivID()).updateDiplomacyPerMonth();
        }
        if (nBonus.AggressiveExpansion != 0.0f) {
            final CivilizationBonuses civBonuses181;
            final CivilizationBonuses civBonuses180;
            final CivilizationBonuses civilizationBonuses630;
            final CivilizationBonuses civilizationBonuses629;
            final CivilizationBonuses civilizationBonuses628;
            final CivilizationBonuses civilizationBonuses727;
            final CivilizationBonuses civilizationBonuses627 = civilizationBonuses727 = (civilizationBonuses628 = (civilizationBonuses629 = (civilizationBonuses630 = (civBonuses180 = (civBonuses181 = this.civBonuses)))));
            civilizationBonuses727.AggressiveExpansion += nBonus.AggressiveExpansion * mod;
        }
        if (nBonus.RevolutionaryRisk != 0.0f) {
            final CivilizationBonuses civBonuses183;
            final CivilizationBonuses civBonuses182;
            final CivilizationBonuses civilizationBonuses634;
            final CivilizationBonuses civilizationBonuses633;
            final CivilizationBonuses civilizationBonuses632;
            final CivilizationBonuses civilizationBonuses728;
            final CivilizationBonuses civilizationBonuses631 = civilizationBonuses728 = (civilizationBonuses632 = (civilizationBonuses633 = (civilizationBonuses634 = (civBonuses182 = (civBonuses183 = this.civBonuses)))));
            civilizationBonuses728.RevolutionaryRisk += nBonus.RevolutionaryRisk * mod;
        }
        if (nBonus.Devastation != 0.0f) {
            final CivilizationBonuses civBonuses185;
            final CivilizationBonuses civBonuses184;
            final CivilizationBonuses civilizationBonuses638;
            final CivilizationBonuses civilizationBonuses637;
            final CivilizationBonuses civilizationBonuses636;
            final CivilizationBonuses civilizationBonuses729;
            final CivilizationBonuses civilizationBonuses635 = civilizationBonuses729 = (civilizationBonuses636 = (civilizationBonuses637 = (civilizationBonuses638 = (civBonuses184 = (civBonuses185 = this.civBonuses)))));
            civilizationBonuses729.Devastation += nBonus.Devastation * mod;
        }
        if (nBonus.CoreCost != 0.0f) {
            final CivilizationBonuses civBonuses187;
            final CivilizationBonuses civBonuses186;
            final CivilizationBonuses civilizationBonuses642;
            final CivilizationBonuses civilizationBonuses641;
            final CivilizationBonuses civilizationBonuses640;
            final CivilizationBonuses civilizationBonuses730;
            final CivilizationBonuses civilizationBonuses639 = civilizationBonuses730 = (civilizationBonuses640 = (civilizationBonuses641 = (civilizationBonuses642 = (civBonuses186 = (civBonuses187 = this.civBonuses)))));
            civilizationBonuses730.CoreCost += nBonus.CoreCost * mod;
        }
        if (nBonus.ReligionCost != 0.0f) {
            final CivilizationBonuses civBonuses189;
            final CivilizationBonuses civBonuses188;
            final CivilizationBonuses civilizationBonuses646;
            final CivilizationBonuses civilizationBonuses645;
            final CivilizationBonuses civilizationBonuses644;
            final CivilizationBonuses civilizationBonuses731;
            final CivilizationBonuses civilizationBonuses643 = civilizationBonuses731 = (civilizationBonuses644 = (civilizationBonuses645 = (civilizationBonuses646 = (civBonuses188 = (civBonuses189 = this.civBonuses)))));
            civilizationBonuses731.ReligionCost += nBonus.ReligionCost * mod;
        }
        if (nBonus.AdvisorMaxLevel != 0) {
            final CivilizationBonuses civBonuses191;
            final CivilizationBonuses civBonuses190;
            final CivilizationBonuses civilizationBonuses650;
            final CivilizationBonuses civilizationBonuses649;
            final CivilizationBonuses civilizationBonuses648;
            final CivilizationBonuses civilizationBonuses732;
            final CivilizationBonuses civilizationBonuses647 = civilizationBonuses732 = (civilizationBonuses648 = (civilizationBonuses649 = (civilizationBonuses650 = (civBonuses190 = (civBonuses191 = this.civBonuses)))));
            civilizationBonuses732.AdvisorMaxLevel += (int)(nBonus.AdvisorMaxLevel * mod);
        }
        if (nBonus.MaximumAmountOfGold != 0.0f) {
            final CivilizationBonuses civBonuses193;
            final CivilizationBonuses civBonuses192;
            final CivilizationBonuses civilizationBonuses654;
            final CivilizationBonuses civilizationBonuses653;
            final CivilizationBonuses civilizationBonuses652;
            final CivilizationBonuses civilizationBonuses733;
            final CivilizationBonuses civilizationBonuses651 = civilizationBonuses733 = (civilizationBonuses652 = (civilizationBonuses653 = (civilizationBonuses654 = (civBonuses192 = (civBonuses193 = this.civBonuses)))));
            civilizationBonuses733.MaximumAmountOfGold += nBonus.MaximumAmountOfGold * mod;
        }
        if (nBonus.MaximumAmountOfGold_Percentage != 0.0f) {
            final CivilizationBonuses civBonuses195;
            final CivilizationBonuses civBonuses194;
            final CivilizationBonuses civilizationBonuses658;
            final CivilizationBonuses civilizationBonuses657;
            final CivilizationBonuses civilizationBonuses656;
            final CivilizationBonuses civilizationBonuses734;
            final CivilizationBonuses civilizationBonuses655 = civilizationBonuses734 = (civilizationBonuses656 = (civilizationBonuses657 = (civilizationBonuses658 = (civBonuses194 = (civBonuses195 = this.civBonuses)))));
            civilizationBonuses734.MaximumAmountOfGold_Percentage += nBonus.MaximumAmountOfGold_Percentage * mod;
        }
        if (nBonus.Corruption != 0.0f) {
            final CivilizationBonuses civBonuses197;
            final CivilizationBonuses civBonuses196;
            final CivilizationBonuses civilizationBonuses662;
            final CivilizationBonuses civilizationBonuses661;
            final CivilizationBonuses civilizationBonuses660;
            final CivilizationBonuses civilizationBonuses735;
            final CivilizationBonuses civilizationBonuses659 = civilizationBonuses735 = (civilizationBonuses660 = (civilizationBonuses661 = (civilizationBonuses662 = (civBonuses196 = (civBonuses197 = this.civBonuses)))));
            civilizationBonuses735.Corruption += nBonus.Corruption * mod;
            Game.gameThread.addCivUpdateTotalIncomePerMonth(this.iCivID);
        }
        if (nBonus.Inflation != 0.0f) {
            final CivilizationBonuses civBonuses199;
            final CivilizationBonuses civBonuses198;
            final CivilizationBonuses civilizationBonuses666;
            final CivilizationBonuses civilizationBonuses665;
            final CivilizationBonuses civilizationBonuses664;
            final CivilizationBonuses civilizationBonuses736;
            final CivilizationBonuses civilizationBonuses663 = civilizationBonuses736 = (civilizationBonuses664 = (civilizationBonuses665 = (civilizationBonuses666 = (civBonuses198 = (civBonuses199 = this.civBonuses)))));
            civilizationBonuses736.Inflation += nBonus.Inflation * mod;
            Game.gameThread.addCivUpdateTotalIncomePerMonth(this.iCivID);
        }
        if (nBonus.RegimentsLimit != 0) {
            final CivilizationBonuses civBonuses201;
            final CivilizationBonuses civBonuses200;
            final CivilizationBonuses civilizationBonuses670;
            final CivilizationBonuses civilizationBonuses669;
            final CivilizationBonuses civilizationBonuses668;
            final CivilizationBonuses civilizationBonuses737;
            final CivilizationBonuses civilizationBonuses667 = civilizationBonuses737 = (civilizationBonuses668 = (civilizationBonuses669 = (civilizationBonuses670 = (civBonuses200 = (civBonuses201 = this.civBonuses)))));
            civilizationBonuses737.RegimentsLimit += (int)(nBonus.RegimentsLimit * mod);
            this.updateRegimentsLimit();
        }
        Game.getCiv(this.getCivID()).updateProvincesIncomeAndExpenses();
    }
    
    public final void addGoodsProduced(final int iResourceID, final int value) {
        try {
            this.goodsProduced.set(iResourceID, this.goodsProduced.get(iResourceID) + value);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            this.initGoodsProduced();
        }
    }
    
    public final int getGoodsProduced(final int iResourceID) {
        try {
            return this.goodsProduced.get(iResourceID);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            this.initGoodsProduced();
            return 0;
        }
    }
    
    public final void initGoodsProduced() {
        this.goodsProduced.clear();
        for (int i = 0; i < ResourcesManager.iResourcesSize; ++i) {
            this.goodsProduced.add(0);
        }
    }
    
    public final void resetGoodsProduced() {
        try {
            for (int i = 0; i < ResourcesManager.iResourcesSize; ++i) {
                this.goodsProduced.set(i, 0);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void buildLaws() {
        this.laws.clear();
        for (int i = 0; i < LawsManager.iLawsSize; ++i) {
            this.laws.add(0);
        }
    }
    
    public final void adoptReform(final int lawID, final int lawID2) {
        try {
            this.laws.set(lawID, lawID2);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            this.buildLaws();
        }
    }
    
    public final void initMaxLaws() {
        try {
            for (int i = 0; i < LawsManager.iLawsSize; ++i) {
                for (int j = LawsManager.laws.get(i).RequiredTechID.length - 1; j > 0; --j) {
                    if (this.getTechResearched(LawsManager.laws.get(i).RequiredTechID[j]) && (LawsManager.laws.get(i).RequiredGovernmentID == null || LawsManager.laws.get(i).RequiredGovernmentID[j] < 0 || LawsManager.laws.get(i).RequiredGovernmentID[j] == this.getIdeologyID())) {
                        LawsManager.updateCivBonuses(i, this.laws.get(i), this.iCivID, -1.0f);
                        this.laws.set(i, j);
                        LawsManager.updateCivBonuses(i, j, this.iCivID, 1.0f);
                        break;
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void addColonizationProvince(final int iProvinceID) {
        try {
            for (int i = this.iColonizationProvinceSize - 1; i >= 0; --i) {
                if (this.colonizationProvince.get(i) == iProvinceID) {
                    return;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        this.colonizationProvince.add(iProvinceID);
        this.iColonizationProvinceSize = this.colonizationProvince.size();
    }
    
    public final void removeColonizationProvince(final int iProvinceID) {
        try {
            for (int i = this.iColonizationProvinceSize - 1; i >= 0; --i) {
                if (this.colonizationProvince.get(i) == iProvinceID) {
                    this.colonizationProvince.remove(i);
                    this.iColonizationProvinceSize = this.colonizationProvince.size();
                    return;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void buildColonizationProvince() {
        this.colonizationProvince.clear();
        try {
            for (int i = 0; i < this.getNumOfProvinces(); ++i) {
                if (Game.getProvinceData9(this.getProvinceID(i)).getColonizationStartedTurnID() >= 0) {
                    if (ColonizationManager.getSettlementEstablishmentProgress(this.getProvinceID(i)) >= 0.99f) {
                        Game.getProvince(this.getProvinceID(i)).resetColonizationData();
                    }
                    else if (!this.colonizationProvince.contains(this.getProvinceID(i))) {
                        this.colonizationProvince.add(this.getProvinceID(i));
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        this.iColonizationProvinceSize = this.colonizationProvince.size();
    }
    
    public final void clearColonizationProvince() {
        this.colonizationProvince.clear();
        this.iColonizationProvinceSize = 0;
    }
    
    public final void updateColonizationProvince() {
        try {
            for (int i = this.iColonizationProvinceSize - 1; i >= 0; --i) {
                if (ColonizationManager.getSettlementEstablishmentProgress(this.colonizationProvince.get(i)) >= 0.99f) {
                    if (this.getCivID() == Game.player.iCivID) {
                        Game.player.addNotification(new Notification(Notification.Notification_Type.SETTLEMENT_ESTABLISHED, Game.lang.get("SettlementEstablished") + ": " + Game.getProvince(this.colonizationProvince.get(i)).getProvinceName(), Images.population, Game_Calendar.TURN_ID, Notification.Notification_BG.GREEN, this.colonizationProvince.get(i)));
                    }
                    this.expandColony(this.colonizationProvince.get(i));
                    Game.getProvince(this.colonizationProvince.get(i)).resetColonizationData();
                    this.colonizationProvince.remove(i);
                    this.iColonizationProvinceSize = this.colonizationProvince.size();
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void expandColony(final int provinceID) {
        if (Game.oR.nextInt(100) < GameValues.colonization.AUTO_EXPAND_CHANCE) {
            final List<Integer> possibleExpand = new ArrayList<Integer>();
            for (int i = 0; i < Game.getProvince(provinceID).getNeighboringProvincesSize(); ++i) {
                if (Game.getProvince(Game.getProvince(provinceID).getNeighboringProvinces(i)).getWasteland() < 0 && Game.getProvince(Game.getProvince(provinceID).getNeighboringProvinces(i)).getCivID() == 0 && !Game.getProvince(Game.getProvince(provinceID).getNeighboringProvinces(i)).getSeaProvince()) {
                    possibleExpand.add(Game.getProvince(provinceID).getNeighboringProvinces(i));
                }
            }
            if (possibleExpand.size() > 0) {
                final List<Integer> possibleScore = new ArrayList<Integer>();
                int bestID = 0;
                for (int j = 0; j < possibleExpand.size(); ++j) {
                    int score = 0;
                    boolean isLastNeutral = true;
                    for (int k = 0; k < Game.getProvince(possibleExpand.get(j)).getNeighboringProvincesSize(); ++k) {
                        if (Game.getProvince(Game.getProvince(possibleExpand.get(j)).getNeighboringProvinces(k)).getCivID() == Game.getProvince(provinceID).getCivID()) {
                            score += 10;
                        }
                        else if (Game.getProvince(Game.getProvince(possibleExpand.get(j)).getNeighboringProvinces(k)).getCivID() == 0) {
                            isLastNeutral = false;
                        }
                    }
                    if (isLastNeutral) {
                        score += 500;
                    }
                    possibleScore.add(score);
                }
                for (int j = 1; j < possibleExpand.size(); ++j) {
                    if (possibleScore.get(bestID) < possibleScore.get(j)) {
                        bestID = j;
                    }
                }
                if (ColonizationManager.establishSettlement(Game.getProvince(provinceID).getCivID(), possibleExpand.get(bestID), GameValues.colonization.AUTO_EXPAND_POPULATION) && Game.getProvince(provinceID).getCivID() == Game.player.iCivID) {
                    InGame_Info.iCivID = Game.player.iCivID;
                    InGame_Info.iCivID2 = 0;
                    Game.menuManager.rebuildInGame_Info(Game.lang.get("SettlementEstablished") + ": " + Game.getProvince(possibleExpand.get(bestID)).getProvinceName(), Game.lang.get("Population") + ": " + CFG.getNumberWithSpaces("" + Game.getProvince(possibleExpand.get(bestID)).getPopulationTotal()));
                    InGame_Info.imgID = Images.infoCrown;
                }
                possibleScore.clear();
            }
            possibleExpand.clear();
        }
    }
    
    public final void clearTagsCanForm() {
        this.sTagsCanForm.clear();
    }
    
    public final void addTagsCanForm(final String nTag) {
        for (int i = 0; i < this.sTagsCanForm.size(); ++i) {
            if (this.sTagsCanForm.get(i).equals(nTag)) {
                return;
            }
        }
        this.sTagsCanForm.add(nTag);
    }
    
    public final void removeTagsCanForm(final int i) {
        this.sTagsCanForm.remove(i);
    }
    
    public final void removeTagsCanForm(final String nTag) {
        for (int i = 0; i < this.sTagsCanForm.size(); ++i) {
            if (this.sTagsCanForm.get(i).equals(nTag)) {
                this.sTagsCanForm.remove(i);
                return;
            }
        }
    }
    
    public float getLegacyPerMonth() {
        return this.fLegacyPerMonth * (1.0f + this.getWarWeariness() * GameValues.warWeariness.WW_LEGACY_PER_POINT);
    }
    
    public final void setBattleTacticsID(final int nBattleTactics) {
        this.civData4.b = Math.max(0, Math.min(GameValues.battleTactics.BATTLE_TACTICS.length - 1, nBattleTactics));
    }
    
    public final void updateArmyImgID() {
        if (this.getCivID() == Game.player.iCivID) {
            this.updateArmyImgID_Player();
            this.isPlayerAlly = true;
        }
        else if (DiplomacyManager.isAtWar(Game.player.iCivID, this.getCivID())) {
            this.iArmyImgID = Images.armyEnemy;
            this.isPlayerAlly = false;
        }
        else if (DiplomacyManager.isAlly(Game.player.iCivID, this.getCivID())) {
            this.iArmyImgID = Images.armyAlly;
            this.isPlayerAlly = true;
        }
        else {
            this.iArmyImgID = Images.army;
            this.isPlayerAlly = false;
        }
    }
    
    public final void updateArmyImgID_Player() {
        switch (Game.player.playerData.armyImgID) {
            case 0: {
                this.iArmyImgID = Images.armyPlayer0;
                break;
            }
            case 1: {
                this.iArmyImgID = Images.armyPlayer1;
                break;
            }
            case 2: {
                this.iArmyImgID = Images.armyPlayer2;
                break;
            }
            case 3: {
                this.iArmyImgID = Images.armyPlayer3;
                break;
            }
            case 4: {
                this.iArmyImgID = Images.armyPlayer4;
                break;
            }
            case 5: {
                this.iArmyImgID = Images.armyPlayer5;
                break;
            }
            case 6: {
                this.iArmyImgID = Images.armyPlayer6;
                break;
            }
            case 7: {
                this.iArmyImgID = Images.armyPlayer7;
                break;
            }
            case 8: {
                this.iArmyImgID = Images.armyPlayer8;
                break;
            }
            case 9: {
                this.iArmyImgID = Images.armyPlayer9;
                break;
            }
            case 10: {
                this.iArmyImgID = Images.armyPlayer10;
                break;
            }
            case 11: {
                this.iArmyImgID = Images.armyPlayer11;
                break;
            }
            case 12: {
                this.iArmyImgID = Images.armyPlayer12;
                break;
            }
            case 13: {
                this.iArmyImgID = Images.armyPlayer13;
                break;
            }
            case 14: {
                this.iArmyImgID = Images.armyPlayer14;
                break;
            }
            default: {
                this.iArmyImgID = Images.armyPlayer0;
                break;
            }
        }
    }
    
    public final void updateProsperity_AverageEconomy() {
        final long popTotal = this.getPopulationTotal();
        float out = 0.0f;
        for (int i = this.getNumOfProvinces() - 1; i >= 0; --i) {
            out += Game.getProvince(this.getProvinceID(i)).getEconomyWithBonuses() * (Game.getProvince(this.getProvinceID(i)).getPopulationTotal() / (float)popTotal);
        }
        this.fProsperity_AverageEconomy = out;
    }
    
    public final void addInAllianceSpecial(final int nAllianceID) {
        if (!this.inAlliance.contains(nAllianceID)) {
            this.inAlliance.add(nAllianceID);
            this.inAllianceSize = this.inAlliance.size();
        }
    }
    
    public final boolean areInAllianceSpecial(final int civID) {
        for (int i = 0; i < Game.getCiv(civID).inAllianceSize; ++i) {
            if (this.inAlliance.contains(Game.getCiv(civID).inAlliance.get(i))) {
                return true;
            }
        }
        return false;
    }
    
    public final void removeInAllianceSpecial(final int nAllianceID) {
        for (int i = 0; i < this.inAllianceSize; ++i) {
            if (this.inAlliance.get(i) == nAllianceID) {
                this.inAlliance.remove(i);
                this.inAllianceSize = this.inAlliance.size();
                return;
            }
        }
    }
    
    public final void addAggressiveExpansion(final float fValue) {
        this.setAggressiveExpansion(Math.min(GameValues.aggressiveExpansion.AE_MAX_VALUE, this.getAggressiveExpansion() + fValue * Math.max(0.0f, 1.0f + this.civBonuses.AggressiveExpansion / 100.0f)));
    }
    
    public final void decayAggressiveExpansion() {
        this.setAggressiveExpansion(Math.max(0.0f, this.getAggressiveExpansion() - GameValues.aggressiveExpansion.AE_DECAY_PER_TICK));
    }
    
    public final void updateWarWeariness(final float fValue) {
        this.setWarWeariness(Math.max(0.0f, Math.min(GameValues.warWeariness.WAR_WEARINESS_MAX, this.getWarWeariness() + fValue)));
    }
    
    public final boolean reduceWarWeariness() {
        if (this.fGold < GameValues.warWeariness.WW_REDUCE_COST_GOLD) {
            return false;
        }
        if (this.fLegacy < GameValues.warWeariness.WW_REDUCE_COST_LEGACY) {
            return false;
        }
        if (this.getWarWeariness() <= 0.005) {
            return false;
        }
        this.updateWarWeariness(GameValues.warWeariness.WW_REDUCE);
        this.addGold(GameValues.warWeariness.WW_REDUCE_COST_GOLD);
        this.addLegacy(GameValues.warWeariness.WW_REDUCE_COST_LEGACY);
        return true;
    }
    
    public final void removeAllArmies() {
        for (int i = this.iArmyPositionSize - 1; i >= 0; --i) {
            Game.getProvince(this.armyPosition.get(i).provinceID).removeArmyCivID(this.getCivID());
        }
        this.armyPosition.clear();
        this.iArmyPositionSize = 0;
    }
    
    public final void updateCivStability() {
        if (this.getNumOfProvinces() > 0) {
            float score = 0.0f;
            int numOfProvinces = 0;
            for (int i = 0; i < this.getNumOfProvinces(); ++i) {
                if (!Game.getProvince(this.getProvinceID(i)).haveACore) {
                    score += GameValues.civStability.CS_SCORE_NON_CORE;
                    ++numOfProvinces;
                }
                if (Game.getProvince(this.getProvinceID(i)).getReligion() != this.getReligionID()) {
                    score += GameValues.civStability.CS_SCORE_DIFFERENT_RELIGION;
                    if (Game.getProvince(this.getProvinceID(i)).haveACore) {
                        ++numOfProvinces;
                    }
                }
            }
            this.civStability_LostFrom100 = Math.min(GameValues.civStability.CS_MAX_VALUE, Math.min(GameValues.civStability.CS_SCORE_MAX_PER_PROVINCE * numOfProvinces, score / this.getNumOfProvinces()));
        }
        else {
            this.civStability_LostFrom100 = 0.0f;
        }
    }
    
    public final int civStability_NumOfProvinces() {
        int score = 0;
        for (int i = 0; i < this.getNumOfProvinces(); ++i) {
            if (!Game.getProvince(this.getProvinceID(i)).haveACore) {
                ++score;
            }
            else if (Game.getProvince(this.getProvinceID(i)).getReligion() != this.getReligionID()) {
                ++score;
            }
        }
        return score;
    }
    
    public void addInBattles(final String key) {
        if (!this.inBattles.contains(key)) {
            this.inBattles.add(key);
            if (this.iCivID == Game.player.iCivID) {
                Game.addSimpleTask(new Game.SimpleTask("rebuildInGame_Right") {
                    @Override
                    public void update() {
                        Game.menuManager.rebuildInGame_Right();
                    }
                });
            }
        }
    }
    
    public void removeInBattles(final String key) {
        this.inBattles.remove(key);
    }
    
    public int getAdvantagePoints() {
        return this.civData3.a;
    }
    
    public void setAdvantagePoints(final int iAdvantagePoints) {
        this.civData3.a = iAdvantagePoints;
    }
    
    public float getWarWeariness() {
        return this.civData3.w;
    }
    
    public void setWarWeariness(final float warWeariness) {
        this.civData3.w = warWeariness;
    }
    
    public float getAggressiveExpansion() {
        return this.civData3.e;
    }
    
    public void setAggressiveExpansion(final float aggressiveExpansion) {
        this.civData3.e = aggressiveExpansion;
    }
    
    public int getMilitaryAcademyForGeneralsLevel() {
        return this.civData4.g;
    }
    
    public void setMilitaryAcademyForGeneralsLevel(final int iMilitaryAcademyForGeneralsLevel) {
        this.civData4.g = iMilitaryAcademyForGeneralsLevel;
    }
    
    public int getMilitaryAcademyLevel() {
        return this.civData4.m;
    }
    
    public void setMilitaryAcademyLevel(final int iMilitaryAcademyLevel) {
        this.civData4.m = iMilitaryAcademyLevel;
    }
    
    public int getSupremeCourtLevel() {
        return this.civData4.s;
    }
    
    public void setSupremeCourtLevel(final int iSupremeCourtLevel) {
        this.civData4.s = iSupremeCourtLevel;
    }
    
    public int getNuclearReactorLevel() {
        return this.civData4.n;
    }
    
    public void setNuclearReactorLevel(final int iNuclearReactorLevel) {
        this.civData4.n = iNuclearReactorLevel;
    }
    
    public int getCapitalLevel() {
        return this.civData4.c;
    }
    
    public void setCapitalLevel(final int iCapitalLevel) {
        this.civData4.c = iCapitalLevel;
    }
    
    public int getWarPlayDefensiveUntilTurnID() {
        return this.civData4.d;
    }
    
    public void setWarPlayDefensiveUntilTurnID(final int untilTurnID) {
        this.civData4.d = untilTurnID;
    }
    
    public int getExtraAggressiveness() {
        return this.civData4.v;
    }
    
    public void setExtraAggressiveness(final int extraAggressiveness) {
        this.civData4.v = extraAggressiveness;
    }
    
    public int getBattleTacticsID() {
        return this.civData4.b;
    }
    
    public int getNukes() {
        return this.civData4.u;
    }
    
    public void setNukes(final int iNukes) {
        this.civData4.u = iNukes;
    }
    
    public int getTaxationLevel() {
        return this.civData4.t;
    }
    
    public void setTaxationLevel(final int iTaxationLevel) {
        this.civData4.t = iTaxationLevel;
    }
    
    public int getResearchLevel() {
        return this.civData4.r;
    }
    
    public void setResearchLevel(final int iResearchLevel) {
        this.civData4.r = iResearchLevel;
    }
    
    public int getMilitaryLevel() {
        return this.civData4.y;
    }
    
    public void setMilitaryLevel(final int iMilitaryLevel) {
        this.civData4.y = iMilitaryLevel;
    }
    
    public int getAlternativeTechResearch() {
        return this.iAlternativeTechResearch;
    }
    
    public void setAlternativeTechResearch(final int iAlternativeTechResearch) {
        this.iAlternativeTechResearch = iAlternativeTechResearch;
    }
    
    public final void updateProvinceBorder() {
        for (int i = 0; i < this.getNumOfProvinces(); ++i) {
            Game.updateProvinceBorder(this.getProvinceID(i));
        }
    }
    
    public List<TechnologyTree.Unit> getUnlockedUnitsFirstLine() {
        final List<TechnologyTree.Unit> out = new ArrayList<TechnologyTree.Unit>();
        for (int i = this.unlockedUnits.size() - 1; i >= 0; --i) {
            if (ArmyManager.lUnitsTypes.get(this.unlockedUnits.get(i).unitID).Line == 0) {
                out.add(this.unlockedUnits.get(i));
            }
        }
        return out;
    }
    
    public List<TechnologyTree.Unit> getUnlockedUnitsFlank() {
        final List<TechnologyTree.Unit> out = new ArrayList<TechnologyTree.Unit>();
        for (int i = this.unlockedUnits.size() - 1; i >= 0; --i) {
            if (ArmyManager.lUnitsTypes.get(this.unlockedUnits.get(i).unitID).Line == 1) {
                out.add(this.unlockedUnits.get(i));
            }
        }
        return out;
    }
    
    public List<TechnologyTree.Unit> getUnlockedUnitsSupport() {
        final List<TechnologyTree.Unit> out = new ArrayList<TechnologyTree.Unit>();
        for (int i = this.unlockedUnits.size() - 1; i >= 0; --i) {
            if (ArmyManager.lUnitsTypes.get(this.unlockedUnits.get(i).unitID).Line == 2 && !ArmyManager.lArmy.get(this.unlockedUnits.get(i).unitID).get(this.unlockedUnits.get(i).armyID).isSettler && !ArmyManager.lArmy.get(this.unlockedUnits.get(i).unitID).get(this.unlockedUnits.get(i).armyID).SiegeUnit) {
                out.add(this.unlockedUnits.get(i));
            }
        }
        return out;
    }
    
    public final void addUnderSiege(final int provinceID) {
        if (!this.underSiege.contains(provinceID)) {
            this.underSiege.add(provinceID);
            this.underSiegeSize = this.underSiege.size();
        }
    }
    
    public final void removeUnderSiege(final int provinceID) {
        for (int i = this.underSiege.size() - 1; i >= 0; --i) {
            if (this.underSiege.get(i) == provinceID) {
                this.underSiege.remove(i);
                this.underSiegeSize = this.underSiege.size();
                return;
            }
        }
    }
    
    public final void buildUnderSiege() {
        this.underSiege.clear();
        for (int i = 0; i < this.getNumOfProvinces(); ++i) {
            if (Game.getProvinceData4(this.getProvinceID(i)).isUnderSiege()) {
                this.underSiege.add(this.getProvinceID(i));
            }
        }
        this.underSiegeSize = this.underSiege.size();
    }
    
    public final void addOccupiedProvince(final int provinceID) {
        if (!this.occupiedProvinces.contains(provinceID)) {
            this.occupiedProvinces.add(provinceID);
            this.occupiedProvincesSize = this.occupiedProvinces.size();
        }
    }
    
    public final void removeOccupiedProvince(final int provinceID) {
        for (int i = this.occupiedProvinces.size() - 1; i >= 0; --i) {
            if (this.occupiedProvinces.get(i) == provinceID) {
                this.occupiedProvinces.remove(i);
                this.occupiedProvincesSize = this.occupiedProvinces.size();
                return;
            }
        }
    }
    
    public final void buildOccupiedProvinces() {
        this.occupiedProvinces.clear();
        for (int i = 0; i < this.getNumOfProvinces(); ++i) {
            if (Game.getProvinceData(this.getProvinceID(i)).getOccupiedByCivID() != 0) {
                this.occupiedProvinces.add(this.getProvinceID(i));
            }
        }
        this.occupiedProvincesSize = this.occupiedProvinces.size();
    }
}
