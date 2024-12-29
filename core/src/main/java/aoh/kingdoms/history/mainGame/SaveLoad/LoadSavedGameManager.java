// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.SaveLoad;

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
import aoc.kingdoms.lukasz.jakowski.Player.More.PlayerStats3;
import aoc.kingdoms.lukasz.jakowski.Player.More.PlayerStats2;
import aoc.kingdoms.lukasz.jakowski.Player.More.PlayerStats;
import aoc.kingdoms.lukasz.jakowski.Player.More.PlayerData;
import aoc.kingdoms.lukasz.map.war.War;
import aoc.kingdoms.lukasz.map.war.WarManager;
import aoc.kingdoms.lukasz.jakowski.AI.AI_Manager;
import aoc.kingdoms.lukasz.jakowski.AI.AI_Budget;
import aoc.kingdoms.lukasz.jakowski.AI.AI_CivDiplomacy;
import aoc.kingdoms.lukasz.map.allianceHRE.Alliance;
import aoc.kingdoms.lukasz.map.battles.Battle;
import aoc.kingdoms.lukasz.map.rebels.RevolutionManager;
import aoc.kingdoms.lukasz.map.Loan;
import aoc.kingdoms.lukasz.map.civilization.CivilizationGoldenAge;
import aoc.kingdoms.lukasz.map.civilization.CivilizationEventsData_Variables;
import aoc.kingdoms.lukasz.map.civilization.stats.CivilizationEventsData3;
import aoc.kingdoms.lukasz.map.civilization.stats.CivilizationEventsData2;
import aoc.kingdoms.lukasz.map.civilization.stats.CivilizationEventsData;
import aoc.kingdoms.lukasz.map.army.ArmyGeneral;
import aoc.kingdoms.lukasz.map.advisors.Advisor;
import aoc.kingdoms.lukasz.map.civilization.CivilizationBonuses;
import aoc.kingdoms.lukasz.map.Ruler;
import aoc.kingdoms.lukasz.map.civilization.CivilizationLegacy;
import aoc.kingdoms.lukasz.map.plague.Plague;
import aoc.kingdoms.lukasz.map.plague.PlagueManager;
import aoc.kingdoms.lukasz.map.army.ArmyDivision;
import aoc.kingdoms.lukasz.map.army.ArmyRecruit;
import aoc.kingdoms.lukasz.map.LawsManager;
import aoc.kingdoms.lukasz.map.technology.TechnologyResearch;
import aoc.kingdoms.lukasz.map.civilization.save.CivData4;
import aoc.kingdoms.lukasz.map.civilization.save.CivData3;
import aoc.kingdoms.lukasz.map.civilization.save.CivData2;
import aoc.kingdoms.lukasz.map.civilization.save.CivData;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.map.province.ProvinceConstructionBuilding;
import aoc.kingdoms.lukasz.map.province.ProvinceConstructedBuilding;
import aoc.kingdoms.lukasz.map.diplomacy.Vassal;
import java.util.Iterator;
import aoc.kingdoms.lukasz.map.province.data.ProvinceData_Population;
import com.badlogic.gdx.utils.JsonValue;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.GameThreads.GameThread_Events;
import aoc.kingdoms.lukasz.events.EventsManager;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import com.badlogic.gdx.files.FileHandle;
import aoc.kingdoms.lukasz.jakowski.CFG;
import com.badlogic.gdx.utils.Json;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;

public class LoadSavedGameManager
{
    public static String key;
    public static String playerKey;
    
    public static final SaveGameManager.SaveDetails loadSave_Details(final String nKey) {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + nKey + "/" + "Details.json");
            final Json json = new Json();
            final SaveGameManager.SaveDetails tempDetailsData = (SaveGameManager.SaveDetails)json.fromJson((Class)SaveGameManager.SaveDetails.class, fileList);
            return tempDetailsData;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            return null;
        }
    }
    
    public static final void loadSave_Details() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Details.json");
            final Json json = new Json();
            final SaveGameManager.SaveDetails tempDetailsData = (SaveGameManager.SaveDetails)json.fromJson((Class)SaveGameManager.SaveDetails.class, fileList);
            LoadSavedGameManager.playerKey = tempDetailsData.sCivTag;
            Game_Calendar.TURN_ID = tempDetailsData.iTurnID;
            SaveGameManager.AUTO_SAVE_LAST_TURN_ID = tempDetailsData.iTurnID;
            Game_Calendar.currentDay = tempDetailsData.iDay;
            Game_Calendar.currentMonth = tempDetailsData.iMonth;
            Game_Calendar.currentYear = tempDetailsData.iYear;
            Game.mapScenarios.sActiveScenarioTag = tempDetailsData.scenarioTAG;
            EventsManager.loadScenarioEventsTag = tempDetailsData.scenarioTAG;
            Game.difficultyID = tempDetailsData.DIFFICULTY;
            Game.FOG_OF_WAR = tempDetailsData.FOG_OF_WAR;
            Game.SPECTATOR_MODE = tempDetailsData.SPECTATOR_MODE;
            Game.SANDBOX = tempDetailsData.SANDBOX;
            Game.ENABLE_CALL_VASSALS = tempDetailsData.ENABLE_CALL_VASSALS;
            Game.SCENARIO_EVENTS = tempDetailsData.SCENARIO_EVENTS;
            Game.HOURS_PER_TURN = tempDetailsData.HOURS_PER_TURN;
            Game.aiAggressivnes = tempDetailsData.AI_AGGRESSIVENESS;
            Game.gameThreadTurns.THREAD_TURN_ID = Game_Calendar.TURN_ID;
            Game.gameThreadTurns.iLastUpdateTurnID = Game_Calendar.TURN_ID;
            Game_Calendar.updateManpowerImg();
            Game_Calendar.updateAge(false);
            GameThread_Events.THREAD_TURN_ID = Game_Calendar.TURN_ID;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_UpdatePlayersCivID() {
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).getCivTag().equals(LoadSavedGameManager.playerKey)) {
                Game.player.iCivID = i;
                break;
            }
        }
    }
    
    public static final void loadSave_InitCivsData() {
        for (int i = 0; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).initTechTree();
            Game.getCiv(i).initGoodsProduced();
        }
    }
    
    public static final void loadSave_BuildTechTree() {
        for (int i = 0; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).buildTechTree_Load();
        }
    }
    
    public static final void loadSave_UpdateDiplomacyPerMonth() {
        for (int i = 0; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).updateDiplomacyPerMonth();
        }
    }
    
    public static final void loadSave_InitCivsData_CoresReligionGenerals() {
        for (int i = 0; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).convertReligion.buildProvincesConvertReligion(i);
            Game.getCiv(i).civilizationCores.buildProvincesNonCore(i);
            Game.getCiv(i).armiesWithoutGenerals.buildArmiesWithoutGenerals(i);
        }
    }
    
    public static final void loadSave_Population() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Provinces_Population.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int id = 0;
            for (final JsonValue jValue : tempArrayData) {
                final ProvinceData_Population tempData = (ProvinceData_Population)json.readValue((Class)ProvinceData_Population.class, jValue);
                Game.lProvincesPopulation.set(id++, tempData);
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void buildProvincePopulationData() {
        try {
            for (int i = 0; i < Game.getProvincesSize(); ++i) {
                Game.getProvince(i).buildPopulation_LoadGame();
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void buildProvinceCores() {
        try {
            for (int i = 0; i < Game.getProvincesSize(); ++i) {
                Game.getProvince(i).updateCoresSize();
                Game.getProvince(i).updateHaveACore();
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void buildProvinceData() {
        try {
            for (int i = 0; i < Game.getProvincesSize(); ++i) {
                Game.getProvince(i).updateBuildingLimit();
                Game.getProvince(i).updateInfrastructureMax();
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final boolean loadSave_Vassals() {
        try {
            if (FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Vassals.json").exists()) {
                final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Vassals.json");
                final Json json = new Json();
                ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
                for (final JsonValue jValue : tempArrayData) {
                    final Vassal tempData = (Vassal)json.readValue((Class)Vassal.class, jValue);
                    Game.getCiv(Game.getCiv(tempData.c).getPuppetOfCivID()).diplomacy.setVassal_LoadData(tempData.c, tempData.tL, tempData.mL, tempData.cW, tempData.lD);
                }
                tempArrayData.clear();
                tempArrayData = null;
                return true;
            }
            return false;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            return false;
        }
    }
    
    public static final boolean loadSave_ProvincesBuildings(final int id) {
        try {
            if (FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Provinces_Buildings_" + id + ".json").exists()) {
                final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Provinces_Buildings_" + id + ".json");
                final Json json = new Json();
                ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
                for (final JsonValue jValue : tempArrayData) {
                    final SaveManager.ScenarioData_Buildings tempData = (SaveManager.ScenarioData_Buildings)json.readValue((Class)SaveManager.ScenarioData_Buildings.class, jValue);
                    if (tempData.pid >= 0 && tempData.pid < Game.getProvincesSize()) {
                        for (int i = 0, iSize = tempData.b0.size(); i < iSize; ++i) {
                            Game.getProvince(tempData.pid).addNewBuilding_LoadScenario(new ProvinceConstructedBuilding(tempData.b0.get(i), tempData.b1.get(i)));
                        }
                    }
                }
                tempArrayData.clear();
                tempArrayData = null;
                return true;
            }
            return false;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            return false;
        }
    }
    
    public static final void loadSave_ProvinceConstructionBuilding() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Provinces_BuildingsConstruction.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                final SaveGameManager.ScenarioData_ProvinceConstructionBuilding tempData = (SaveGameManager.ScenarioData_ProvinceConstructionBuilding)json.readValue((Class)SaveGameManager.ScenarioData_ProvinceConstructionBuilding.class, jValue);
                if (tempData.p >= 0 && tempData.p < Game.getProvincesSize()) {
                    for (int i = 0, iSize = tempData.b0.size(); i < iSize; ++i) {
                        Game.getProvince(tempData.p).addBuildingConstruction_Load(new ProvinceConstructionBuilding(tempData.b0.get(i), tempData.b1.get(i), tempData.ct.get(i), tempData.ctL.get(i)));
                    }
                }
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_ProvinceInvestDaysLeft() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Provinces_InvestEconomy.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                final SaveGameManager.Save_ProvinceInvest tempData = (SaveGameManager.Save_ProvinceInvest)json.readValue((Class)SaveGameManager.Save_ProvinceInvest.class, jValue);
                if (tempData.p >= 0 && tempData.p < Game.getProvincesSize()) {
                    for (int i = 0, iSize = tempData.d.size(); i < iSize; ++i) {
                        Game.getProvince(tempData.p).addInvestInProvince_Load(tempData.d.get(i), tempData.n.get(i));
                    }
                }
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_ProvinceInvestTax() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Provinces_InvestTax.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                final SaveGameManager.Save_ProvinceInvest tempData = (SaveGameManager.Save_ProvinceInvest)json.readValue((Class)SaveGameManager.Save_ProvinceInvest.class, jValue);
                if (tempData.p >= 0 && tempData.p < Game.getProvincesSize()) {
                    for (int i = 0, iSize = tempData.d.size(); i < iSize; ++i) {
                        Game.getProvince(tempData.p).addIncreaseTaxEfficiencyInProvince_Load(tempData.d.get(i), tempData.n.get(i));
                    }
                }
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_ProvinceInvestManpower() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Provinces_InvestManpower.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                final SaveGameManager.Save_ProvinceInvest tempData = (SaveGameManager.Save_ProvinceInvest)json.readValue((Class)SaveGameManager.Save_ProvinceInvest.class, jValue);
                if (tempData.p >= 0 && tempData.p < Game.getProvincesSize()) {
                    for (int i = 0, iSize = tempData.d.size(); i < iSize; ++i) {
                        Game.getProvince(tempData.p).addIncreaseManpowerInProvince_Load(tempData.d.get(i), tempData.n.get(i));
                    }
                }
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_ProvinceInvestGrowthRate() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Provinces_InvestGrowth.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                final SaveGameManager.Save_ProvinceInvest tempData = (SaveGameManager.Save_ProvinceInvest)json.readValue((Class)SaveGameManager.Save_ProvinceInvest.class, jValue);
                if (tempData.p >= 0 && tempData.p < Game.getProvincesSize()) {
                    for (int i = 0, iSize = tempData.d.size(); i < iSize; ++i) {
                        Game.getProvince(tempData.p).addIncreaseGrowthRateInProvince_Load(tempData.d.get(i), tempData.n.get(i));
                    }
                }
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_ProvinceInvestInfrastructure() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Provinces_InvestInfrastructure.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                final SaveGameManager.Save_ProvinceInvest tempData = (SaveGameManager.Save_ProvinceInvest)json.readValue((Class)SaveGameManager.Save_ProvinceInvest.class, jValue);
                if (tempData.p >= 0 && tempData.p < Game.getProvincesSize()) {
                    for (int i = 0, iSize = tempData.d.size(); i < iSize; ++i) {
                        Game.getProvince(tempData.p).addDevelopInfrastructure_Load(tempData.d.get(i), tempData.n.get(i));
                    }
                }
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_ProvinceCoreCreation() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Provinces_CoreCreation.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                final SaveGameManager.Save_ProvinceInvest_Single tempData = (SaveGameManager.Save_ProvinceInvest_Single)json.readValue((Class)SaveGameManager.Save_ProvinceInvest_Single.class, jValue);
                if (tempData.p >= 0 && tempData.p < Game.getProvincesSize()) {
                    Game.getProvince(tempData.p).addCoreCreation_Load(tempData.d, tempData.n);
                }
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_ProvinceReligionConversion() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Provinces_Conversion.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                final SaveGameManager.Save_ProvinceInvest_Single tempData = (SaveGameManager.Save_ProvinceInvest_Single)json.readValue((Class)SaveGameManager.Save_ProvinceInvest_Single.class, jValue);
                if (tempData.p >= 0 && tempData.p < Game.getProvincesSize()) {
                    Game.getProvince(tempData.p).addReligionConversion_Load(tempData.d, tempData.n);
                }
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_ProvinceWonderConstruction() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Provinces_WonderConstruction.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                final SaveGameManager.Save_ProvinceInvest_Single tempData = (SaveGameManager.Save_ProvinceInvest_Single)json.readValue((Class)SaveGameManager.Save_ProvinceInvest_Single.class, jValue);
                if (tempData.p >= 0 && tempData.p < Game.getProvincesSize()) {
                    Game.getProvince(tempData.p).addWonderConstruction_Load(tempData.d, tempData.n);
                }
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_CivsNukesProduction() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "NukesProduction.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                final SaveGameManager.Save_ProvinceInvest tempData = (SaveGameManager.Save_ProvinceInvest)json.readValue((Class)SaveGameManager.Save_ProvinceInvest.class, jValue);
                if (tempData.p > 0 && tempData.p < Game.getCivsSize()) {
                    for (int i = 0, iSize = tempData.d.size(); i < iSize; ++i) {
                        Game.getCiv(tempData.p).addNukeProduction_Load(tempData.d.get(i), tempData.n.get(i));
                    }
                }
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_Civs() {
        Game.lCivs.add(Game.getNeutralCivilization());
        Game.lCivs.get(0).iCivID = 0;
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Civs.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tCivID = 1;
            for (final JsonValue jValue : tempArrayData) {
                CivData tempData = (CivData)json.readValue((Class)CivData.class, jValue);
                final Game.LoadCivilizationData civData = Game.loadCivilization(tempData.t);
                Game.lCivs.add(new Civilization(tCivID, tempData.t, tempData.p, civData.iR, civData.iG, civData.iB, tempData.c, tempData.r, civData.GroupID, true));
                ++tCivID;
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        Game.iCivsSize = Game.lCivs.size();
    }
    
    public static final void loadSave_Civs2() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Civs2.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tCivID = 1;
            for (final JsonValue jValue : tempArrayData) {
                CivData2 tempData = (CivData2)json.readValue((Class)CivData2.class, jValue);
                tempData.update(tCivID);
                ++tCivID;
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_Civs3() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Civs3.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tCivID = 1;
            for (final JsonValue jValue : tempArrayData) {
                CivData3 tempData = (CivData3)json.readValue((Class)CivData3.class, jValue);
                Game.getCiv(tCivID).civData3 = tempData;
                ++tCivID;
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_Civs4() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Civs4.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tCivID = 1;
            for (final JsonValue jValue : tempArrayData) {
                CivData4 tempData = (CivData4)json.readValue((Class)CivData4.class, jValue);
                Game.getCiv(tCivID).civData4 = tempData;
                ++tCivID;
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_CivsUnlockedTechnologies() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "UnlockedTechnologies.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tCivID = 1;
            for (final JsonValue jValue : tempArrayData) {
                SaveGameManager.Save_Civ_UnlockedTechnologies tempData = (SaveGameManager.Save_Civ_UnlockedTechnologies)json.readValue((Class)SaveGameManager.Save_Civ_UnlockedTechnologies.class, jValue);
                for (int i = 0; i < tempData.a; ++i) {
                    Game.getCiv(tCivID).setTechnologyResearched(i, true);
                }
                for (int i = 0, iSize = tempData.u.size(); i < iSize; ++i) {
                    Game.getCiv(tCivID).setTechnologyResearched(tempData.u.get(i), true);
                }
                ++tCivID;
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_CivsResearchProgress() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "ResearchProgress.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tCivID = 1;
            for (final JsonValue jValue : tempArrayData) {
                SaveGameManager.Save_Civ_ResearchProgress tempData = (SaveGameManager.Save_Civ_ResearchProgress)json.readValue((Class)SaveGameManager.Save_Civ_ResearchProgress.class, jValue);
                for (int i = 0; i < tempData.t.size(); ++i) {
                    Game.getCiv(tCivID).lResearching.add(new TechnologyResearch(tempData.t.get(i), tempData.p.get(i) / 1000.0f));
                }
                ++tCivID;
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_CivsLaws() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Laws.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tCivID = 1;
            for (final JsonValue jValue : tempArrayData) {
                SaveGameManager.Save_Civ_Laws tempData = (SaveGameManager.Save_Civ_Laws)json.readValue((Class)SaveGameManager.Save_Civ_Laws.class, jValue);
                for (int i = 0, iSize = tempData.l.size(); i < iSize; ++i) {
                    Game.getCiv(tCivID).laws.set(i, tempData.l.get(i));
                    LawsManager.updateCivBonuses(i, tempData.l.get(i), tCivID, 1.0f);
                }
                ++tCivID;
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_CivsRecruitArmyCreate() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "RecruitArmyCreate.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                SaveGameManager.Save_Civ_RecruitArmyCreate tempData = (SaveGameManager.Save_Civ_RecruitArmyCreate)json.readValue((Class)SaveGameManager.Save_Civ_RecruitArmyCreate.class, jValue);
                for (int i = 0, iSize = tempData.k.size(); i < iSize; ++i) {
                    Game.getCiv(tempData.c).lCreateNewArmy.put(tempData.k.get(i), tempData.p.get(i));
                }
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_CivsRecruitArmy() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "RecruitArmy.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                SaveGameManager.Save_Civ_RecruitArmy tempData = (SaveGameManager.Save_Civ_RecruitArmy)json.readValue((Class)SaveGameManager.Save_Civ_RecruitArmy.class, jValue);
                for (int i = 0, iSize = tempData.a.size(); i < iSize; ++i) {
                    Game.getCiv(tempData.c).addRecruitArmy_Load(tempData.a.get(i));
                }
                Game.getCiv(tempData.c).addRecruitArmy_LoadUpdateSize();
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final boolean loadSave_ProvincesArmy_MoreFiles(final int id) {
        try {
            if (FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Provinces_Armies_" + id + ".json").exists()) {
                final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Provinces_Armies_" + id + ".json");
                final Json json = new Json();
                ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
                for (final JsonValue jValue : tempArrayData) {
                    SaveGameManager.Save_Provinces_ArmyDivision tempData = (SaveGameManager.Save_Provinces_ArmyDivision)json.readValue((Class)SaveGameManager.Save_Provinces_ArmyDivision.class, jValue);
                    Game.getProvince(tempData.p).addArmy_Load(new ArmyDivision(tempData));
                    tempData = null;
                }
                tempArrayData.clear();
                tempArrayData = null;
                return true;
            }
            return false;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            return false;
        }
    }
    
    public static final void loadSave_MapPlagues() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Plagues.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            PlagueManager.activePlagues.clear();
            for (final JsonValue jValue : tempArrayData) {
                Plague tempData = (Plague)json.readValue((Class)Plague.class, jValue);
                PlagueManager.activePlagues.add(tempData);
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_ProvincesPlagues() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Provinces_Plagues.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                SaveGameManager.Save_Province_Plague tempData = (SaveGameManager.Save_Province_Plague)json.readValue((Class)SaveGameManager.Save_Province_Plague.class, jValue);
                Game.getProvince(tempData.p).provincePlague = tempData.l;
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_CivsUnlockedAdvantages() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Advantages.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tCivID = 1;
            for (final JsonValue jValue : tempArrayData) {
                SaveGameManager.Save_Civ_UnlockedAdvantages tempData = (SaveGameManager.Save_Civ_UnlockedAdvantages)json.readValue((Class)SaveGameManager.Save_Civ_UnlockedAdvantages.class, jValue);
                for (int i = 0, iSize = tempData.a.size(); i < iSize; ++i) {
                    Game.getCiv(tCivID).lAdvantages.add(new CivilizationLegacy(tempData.a.get(i), tempData.l.get(i)));
                }
                Game.getCiv(tCivID).iAdvantagesSize = Game.getCiv(tCivID).lAdvantages.size();
                ++tCivID;
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_CivsRulers() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Rulers.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tCivID = 1;
            for (final JsonValue jValue : tempArrayData) {
                SaveGameManager.Save_Civ_Ruler tempData = (SaveGameManager.Save_Civ_Ruler)json.readValue((Class)SaveGameManager.Save_Civ_Ruler.class, jValue);
                Game.getCiv(tCivID).ruler = new Ruler(tempData);
                ++tCivID;
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_CivsRulers_Bonuses() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "RulersBonuses.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tCivID = 1;
            for (final JsonValue jValue : tempArrayData) {
                CivilizationBonuses tempData = (CivilizationBonuses)json.readValue((Class)CivilizationBonuses.class, jValue);
                if (Game.getCiv(tCivID).ruler != null) {
                    Game.getCiv(tCivID).ruler.initRulerBonuses_Load(tCivID, tempData);
                }
                ++tCivID;
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_CivsAdvisorsAdmBonuses() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "AdvisorAdministrationBonuses.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tCivID = 1;
            for (final JsonValue jValue : tempArrayData) {
                Advisor tempData = (Advisor)json.readValue((Class)Advisor.class, jValue);
                if (tempData != null) {
                    Game.getCiv(tCivID).advisorAdministration = tempData;
                }
                ++tCivID;
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_CivsAdvisorsAdm() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "AdvisorAdministration.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tCivID = 1;
            for (final JsonValue jValue : tempArrayData) {
                SaveGameManager.Save_Civ_Advisor tempData = (SaveGameManager.Save_Civ_Advisor)json.readValue((Class)SaveGameManager.Save_Civ_Advisor.class, jValue);
                if (tempData != null) {
                    Game.getCiv(tCivID).advisorAdministration.sName = tempData.n;
                    Game.getCiv(tCivID).advisorAdministration.iYearOfBirth = tempData.y;
                    Game.getCiv(tCivID).advisorAdministration.iMonthOfBirth = tempData.m;
                    Game.getCiv(tCivID).advisorAdministration.iDayOfBirth = tempData.d;
                    Game.getCiv(tCivID).advisorAdministration.sIMG = tempData.g;
                    Game.getCiv(tCivID).advisorAdministration.imageID = tempData.e;
                    Game.getCiv(tCivID).advisorAdministration.iLevel = tempData.l;
                }
                ++tCivID;
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_CivsAdvisorsEconomyBonuses() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "AdvisorEconomyBonuses.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tCivID = 1;
            for (final JsonValue jValue : tempArrayData) {
                Advisor tempData = (Advisor)json.readValue((Class)Advisor.class, jValue);
                if (tempData != null) {
                    Game.getCiv(tCivID).advisorEconomy = tempData;
                }
                ++tCivID;
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_CivsAdvisorsEconomy() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "AdvisorEconomy.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tCivID = 1;
            for (final JsonValue jValue : tempArrayData) {
                SaveGameManager.Save_Civ_Advisor tempData = (SaveGameManager.Save_Civ_Advisor)json.readValue((Class)SaveGameManager.Save_Civ_Advisor.class, jValue);
                if (tempData != null) {
                    Game.getCiv(tCivID).advisorEconomy.sName = tempData.n;
                    Game.getCiv(tCivID).advisorEconomy.iYearOfBirth = tempData.y;
                    Game.getCiv(tCivID).advisorEconomy.iMonthOfBirth = tempData.m;
                    Game.getCiv(tCivID).advisorEconomy.iDayOfBirth = tempData.d;
                    Game.getCiv(tCivID).advisorEconomy.sIMG = tempData.g;
                    Game.getCiv(tCivID).advisorEconomy.imageID = tempData.e;
                    Game.getCiv(tCivID).advisorEconomy.iLevel = tempData.l;
                }
                ++tCivID;
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_CivsAdvisorsInnovationBonuses() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "AdvisorInnovationBonuses.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tCivID = 1;
            for (final JsonValue jValue : tempArrayData) {
                Advisor tempData = (Advisor)json.readValue((Class)Advisor.class, jValue);
                if (tempData != null) {
                    Game.getCiv(tCivID).advisorTechnology = tempData;
                }
                ++tCivID;
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_CivsAdvisorsInnovation() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "AdvisorInnovation.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tCivID = 1;
            for (final JsonValue jValue : tempArrayData) {
                SaveGameManager.Save_Civ_Advisor tempData = (SaveGameManager.Save_Civ_Advisor)json.readValue((Class)SaveGameManager.Save_Civ_Advisor.class, jValue);
                if (tempData != null) {
                    Game.getCiv(tCivID).advisorTechnology.sName = tempData.n;
                    Game.getCiv(tCivID).advisorTechnology.iYearOfBirth = tempData.y;
                    Game.getCiv(tCivID).advisorTechnology.iMonthOfBirth = tempData.m;
                    Game.getCiv(tCivID).advisorTechnology.iDayOfBirth = tempData.d;
                    Game.getCiv(tCivID).advisorTechnology.sIMG = tempData.g;
                    Game.getCiv(tCivID).advisorTechnology.imageID = tempData.e;
                    Game.getCiv(tCivID).advisorTechnology.iLevel = tempData.l;
                }
                ++tCivID;
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_CivsAdvisorsMilitaryBonuses() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "AdvisorMilitaryBonuses.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tCivID = 1;
            for (final JsonValue jValue : tempArrayData) {
                Advisor tempData = (Advisor)json.readValue((Class)Advisor.class, jValue);
                if (tempData != null) {
                    Game.getCiv(tCivID).advisorMilitary = tempData;
                }
                ++tCivID;
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_CivsAdvisorsMilitary() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "AdvisorMilitary.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tCivID = 1;
            for (final JsonValue jValue : tempArrayData) {
                SaveGameManager.Save_Civ_Advisor tempData = (SaveGameManager.Save_Civ_Advisor)json.readValue((Class)SaveGameManager.Save_Civ_Advisor.class, jValue);
                if (tempData != null) {
                    Game.getCiv(tCivID).advisorMilitary.sName = tempData.n;
                    Game.getCiv(tCivID).advisorMilitary.iYearOfBirth = tempData.y;
                    Game.getCiv(tCivID).advisorMilitary.iMonthOfBirth = tempData.m;
                    Game.getCiv(tCivID).advisorMilitary.iDayOfBirth = tempData.d;
                    Game.getCiv(tCivID).advisorMilitary.sIMG = tempData.g;
                    Game.getCiv(tCivID).advisorMilitary.imageID = tempData.e;
                    Game.getCiv(tCivID).advisorMilitary.iLevel = tempData.l;
                }
                ++tCivID;
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_CivsGeneralsNotAssigned() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "GeneralsNotAssigned.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                ArmyGeneral tempData = (ArmyGeneral)json.readValue((Class)ArmyGeneral.class, jValue);
                if (tempData != null) {
                    Game.getCiv(tempData.c).addGeneral(tempData);
                }
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_CivsEventsData() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "EventsData.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tCivID = 1;
            for (final JsonValue jValue : tempArrayData) {
                CivilizationEventsData tempData = (CivilizationEventsData)json.readValue((Class)CivilizationEventsData.class, jValue);
                Game.getCiv(tCivID).eventsData = tempData;
                ++tCivID;
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_CivsEventsData2() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "EventsData2.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tCivID = 1;
            for (final JsonValue jValue : tempArrayData) {
                CivilizationEventsData2 tempData = (CivilizationEventsData2)json.readValue((Class)CivilizationEventsData2.class, jValue);
                Game.getCiv(tCivID).eventsData2 = tempData;
                ++tCivID;
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_CivsEventsData3() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "EventsData3.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tCivID = 1;
            for (final JsonValue jValue : tempArrayData) {
                CivilizationEventsData3 tempData = (CivilizationEventsData3)json.readValue((Class)CivilizationEventsData3.class, jValue);
                Game.getCiv(tCivID).eventsData3 = tempData;
                ++tCivID;
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_CivsTemporaryBonuses() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "BonusesTemp.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tCivID = 1;
            for (final JsonValue jValue : tempArrayData) {
                SaveGameManager.Save_Civ_TemporaryBonuses tempData = (SaveGameManager.Save_Civ_TemporaryBonuses)json.readValue((Class)SaveGameManager.Save_Civ_TemporaryBonuses.class, jValue);
                if (tempData != null) {
                    for (int i = 0; i < tempData.b.size(); ++i) {
                        Game.getCiv(tCivID).addCivilizationBonus_Temporary(tempData.b.get(i));
                    }
                }
                ++tCivID;
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_CivsEventsVariables() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "EventsVariables.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tCivID = 1;
            for (final JsonValue jValue : tempArrayData) {
                CivilizationEventsData_Variables tempData = (CivilizationEventsData_Variables)json.readValue((Class)CivilizationEventsData_Variables.class, jValue);
                if (tempData != null) {
                    Game.getCiv(tCivID).eventsDataVariables = tempData;
                }
                ++tCivID;
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_CivsEventsVariables2() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "EventsVariables2.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tCivID = (int)Math.floor(Game.getCivsSize() / 2.0f);
            for (final JsonValue jValue : tempArrayData) {
                CivilizationEventsData_Variables tempData = (CivilizationEventsData_Variables)json.readValue((Class)CivilizationEventsData_Variables.class, jValue);
                if (tempData != null) {
                    Game.getCiv(tCivID).eventsDataVariables = tempData;
                }
                ++tCivID;
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_CivsGoldenAges() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "GoldenAge.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tCivID = 1;
            for (final JsonValue jValue : tempArrayData) {
                CivilizationGoldenAge tempData = (CivilizationGoldenAge)json.readValue((Class)CivilizationGoldenAge.class, jValue);
                if (tempData != null) {
                    Game.getCiv(tCivID).goldenAge = tempData;
                }
                ++tCivID;
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_CivsLoans() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Loans.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                SaveGameManager.Save_Civ_Loan tempData = (SaveGameManager.Save_Civ_Loan)json.readValue((Class)SaveGameManager.Save_Civ_Loan.class, jValue);
                Game.getCiv(tempData.c).addLoan_Load(new Loan(tempData));
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_CivsLegacies() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Legacies.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tCivID = 1;
            for (final JsonValue jValue : tempArrayData) {
                SaveGameManager.Save_Civ_Legacies tempData = (SaveGameManager.Save_Civ_Legacies)json.readValue((Class)SaveGameManager.Save_Civ_Legacies.class, jValue);
                if (tempData.b.size() > 0) {
                    for (int i = 0; i < tempData.b.size(); ++i) {
                        Game.getCiv(tCivID).addLegacy_Load(tempData.b.get(i).id, tempData.b.get(i).lvl);
                    }
                }
                ++tCivID;
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_CivsMoveUnits() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "MoveUnits.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                SaveGameManager.Save_Civ_MoveUnits tempData = (SaveGameManager.Save_Civ_MoveUnits)json.readValue((Class)SaveGameManager.Save_Civ_MoveUnits.class, jValue);
                final ArmyDivision armyDivision = Game.getProvince(tempData.f).getArmy(tempData.k);
                if (armyDivision != null && Game.getCiv(tempData.c).newMove(tempData.f, tempData.t, tempData.k, 0, armyDivision.inRetreat)) {
                    Game.getCiv(tempData.c).updateMoveUnits_Load(tempData, armyDivision.inRetreat, armyDivision.inBattle);
                }
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_RebelsMoveUnits() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "RebelsMoveUnits.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                SaveGameManager.Save_Civ_MoveUnits tempData = (SaveGameManager.Save_Civ_MoveUnits)json.readValue((Class)SaveGameManager.Save_Civ_MoveUnits.class, jValue);
                final ArmyDivision armyDivision = Game.getProvince(tempData.f).getArmy(tempData.k);
                if (armyDivision != null && Game.revolutionMoveUnits.newMove(tempData.f, tempData.t, tempData.k, 0, false)) {
                    Game.revolutionMoveUnits.updateMoveUnits_Load(tempData, false, armyDivision.inBattle);
                }
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_Rebels() {
        try {
            final Json json = new Json();
            Game.revolutionManager = (RevolutionManager)json.fromJson((Class)RevolutionManager.class, FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Rebels.json"));
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_MapBattles() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Battles.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                Battle tempData = (Battle)json.readValue((Class)Battle.class, jValue);
                tempData.attackingArmy.updateLoaded_Load();
                tempData.defendingArmy.updateLoaded_Load();
                Game.battleManager.lBattle.add(tempData);
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        Game.battleManager.iBattleSize = Game.battleManager.lBattle.size();
    }
    
    public static final void loadSave_MapBattles_Update() {
        for (int i = 0; i < Game.battleManager.iBattleSize; ++i) {
            Game.battleManager.lBattle.get(i).attackingArmy.updateInBattle_Load(Game.battleManager.lBattle.get(i).provinceID);
            Game.battleManager.lBattle.get(i).defendingArmy.updateInBattle_Load(Game.battleManager.lBattle.get(i).provinceID);
        }
    }
    
    public static final void loadSaveRelations() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Relations.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                final SaveManager.ScenarioData_Relations tempData = (SaveManager.ScenarioData_Relations)json.readValue((Class)SaveManager.ScenarioData_Relations.class, jValue);
                if (tempData.c > 0 && tempData.c < Game.getCivsSize()) {
                    for (int i = tempData.w.size() - 1; i >= 0; --i) {
                        Game.getCiv(tempData.c).diplomacy.setRelation_Load(tempData.w.get(i), tempData.r.get(i));
                    }
                }
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSaveRelations2() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Relations2.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                final SaveManager.ScenarioData_Relations tempData = (SaveManager.ScenarioData_Relations)json.readValue((Class)SaveManager.ScenarioData_Relations.class, jValue);
                if (tempData.c > 0 && tempData.c < Game.getCivsSize()) {
                    for (int i = tempData.w.size() - 1; i >= 0; --i) {
                        Game.getCiv(tempData.c).diplomacy.setRelation_Load(tempData.w.get(i), tempData.r.get(i));
                    }
                }
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSaveAlliances() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Alliances.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                final SaveManager.ScenarioData_Diplomacy tempData = (SaveManager.ScenarioData_Diplomacy)json.readValue((Class)SaveManager.ScenarioData_Diplomacy.class, jValue);
                if (tempData.pid > 0 && tempData.pid < Game.getCivsSize()) {
                    for (int i = tempData.w0.size() - 1; i >= 0; --i) {
                        Game.getCiv(tempData.pid).diplomacy.addAlliance(tempData.w0.get(i), tempData.t0.get(i));
                        Game.getCiv(tempData.w0.get(i)).diplomacy.addAlliance(tempData.pid, tempData.t0.get(i));
                    }
                }
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSaveRivals() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Rivals.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                final SaveManager.ScenarioData_Diplomacy tempData = (SaveManager.ScenarioData_Diplomacy)json.readValue((Class)SaveManager.ScenarioData_Diplomacy.class, jValue);
                if (tempData.pid > 0 && tempData.pid < Game.getCivsSize()) {
                    for (int i = 0, iSize = tempData.w0.size(); i < iSize; ++i) {
                        Game.getCiv(tempData.pid).diplomacy.addRival_load(tempData.w0.get(i), tempData.t0.get(i));
                    }
                }
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSaveRelationsImprove() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "RelationsImprove.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                final SaveManager.ScenarioData_Diplomacy tempData = (SaveManager.ScenarioData_Diplomacy)json.readValue((Class)SaveManager.ScenarioData_Diplomacy.class, jValue);
                if (tempData.pid > 0 && tempData.pid < Game.getCivsSize()) {
                    for (int i = 0, iSize = tempData.w0.size(); i < iSize; ++i) {
                        Game.getCiv(tempData.pid).diplomacy.addImproveRelations_Load(tempData.w0.get(i), tempData.t0.get(i));
                    }
                    Game.getCiv(tempData.pid).diplomacy.iImprovingRelationsSize = Game.getCiv(tempData.pid).diplomacy.improvingRelations.size();
                }
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSaveRelationsDamage() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "RelationsDamage.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                final SaveManager.ScenarioData_Diplomacy tempData = (SaveManager.ScenarioData_Diplomacy)json.readValue((Class)SaveManager.ScenarioData_Diplomacy.class, jValue);
                if (tempData.pid > 0 && tempData.pid < Game.getCivsSize()) {
                    for (int i = 0, iSize = tempData.w0.size(); i < iSize; ++i) {
                        Game.getCiv(tempData.pid).diplomacy.addDamageRelations_Load(tempData.w0.get(i), tempData.t0.get(i));
                    }
                    Game.getCiv(tempData.pid).diplomacy.iDamagingRelationsSize = Game.getCiv(tempData.pid).diplomacy.damagingRelations.size();
                }
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSaveDefensive() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Defensive.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                final SaveManager.ScenarioData_Diplomacy tempData = (SaveManager.ScenarioData_Diplomacy)json.readValue((Class)SaveManager.ScenarioData_Diplomacy.class, jValue);
                if (tempData.pid > 0 && tempData.pid < Game.getCivsSize()) {
                    for (int i = tempData.w0.size() - 1; i >= 0; --i) {
                        Game.getCiv(tempData.pid).diplomacy.addDefensivePact(tempData.w0.get(i), tempData.t0.get(i));
                        Game.getCiv(tempData.w0.get(i)).diplomacy.addDefensivePact(tempData.pid, tempData.t0.get(i));
                    }
                }
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSaveTruce() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Trcues.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                final SaveManager.ScenarioData_Diplomacy tempData = (SaveManager.ScenarioData_Diplomacy)json.readValue((Class)SaveManager.ScenarioData_Diplomacy.class, jValue);
                if (tempData.pid > 0 && tempData.pid < Game.getCivsSize()) {
                    for (int i = tempData.w0.size() - 1; i >= 0; --i) {
                        Game.getCiv(tempData.pid).diplomacy.addTruce(tempData.w0.get(i), tempData.t0.get(i));
                        Game.getCiv(tempData.w0.get(i)).diplomacy.addTruce(tempData.pid, tempData.t0.get(i));
                    }
                }
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSaveNonAggression() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "NonAggression.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                final SaveManager.ScenarioData_Diplomacy tempData = (SaveManager.ScenarioData_Diplomacy)json.readValue((Class)SaveManager.ScenarioData_Diplomacy.class, jValue);
                if (tempData.pid > 0 && tempData.pid < Game.getCivsSize()) {
                    for (int i = tempData.w0.size() - 1; i >= 0; --i) {
                        Game.getCiv(tempData.pid).diplomacy.addNonAggressionPact(tempData.w0.get(i), tempData.t0.get(i));
                        Game.getCiv(tempData.w0.get(i)).diplomacy.addNonAggressionPact(tempData.pid, tempData.t0.get(i));
                    }
                }
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSaveMilitaryAccess() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "MilitaryAccess.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                final SaveManager.ScenarioData_Diplomacy tempData = (SaveManager.ScenarioData_Diplomacy)json.readValue((Class)SaveManager.ScenarioData_Diplomacy.class, jValue);
                if (tempData.pid > 0 && tempData.pid < Game.getCivsSize()) {
                    for (int i = tempData.w0.size() - 1; i >= 0; --i) {
                        Game.getCiv(tempData.pid).diplomacy.addMilitaryAccess(tempData.w0.get(i), tempData.t0.get(i));
                    }
                }
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSaveGuarantee() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Guarantee.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                final SaveManager.ScenarioData_Diplomacy tempData = (SaveManager.ScenarioData_Diplomacy)json.readValue((Class)SaveManager.ScenarioData_Diplomacy.class, jValue);
                if (tempData.pid > 0 && tempData.pid < Game.getCivsSize()) {
                    for (int i = tempData.w0.size() - 1; i >= 0; --i) {
                        Game.getCiv(tempData.pid).diplomacy.addGuarantee(tempData.w0.get(i), tempData.t0.get(i));
                        Game.getCiv(tempData.w0.get(i)).diplomacy.addGuaranteeByCivID(tempData.pid, tempData.t0.get(i));
                    }
                }
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_AllianceSpecial() {
        try {
            Game.alliancesSpecial.clear();
            Game.alliancesSpecialSize = 0;
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "AllianceSpecial.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                Alliance tempData = (Alliance)json.readValue((Class)Alliance.class, jValue);
                Game.alliancesSpecial.add(tempData);
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        Game.alliancesSpecialSize = Game.alliancesSpecial.size();
    }
    
    public static final void loadSave_AI_Merge() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "AI_Merge.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                SaveGameManager.Save_Civ_AI_Merge tempData = (SaveGameManager.Save_Civ_AI_Merge)json.readValue((Class)SaveGameManager.Save_Civ_AI_Merge.class, jValue);
                Game.getCiv(tempData.c).aiMerge = tempData.a;
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_AI_CreateNewArmy() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "AI_CreateNewArmy.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                SaveGameManager.Save_Civ_AI_CreateNewArmy tempData = (SaveGameManager.Save_Civ_AI_CreateNewArmy)json.readValue((Class)SaveGameManager.Save_Civ_AI_CreateNewArmy.class, jValue);
                Game.getCiv(tempData.c).aiCivCreateNewArmy = tempData.a;
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_AI_Diplomacy() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "AI_Diplomacy.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int civID = 1;
            for (final JsonValue jValue : tempArrayData) {
                AI_CivDiplomacy tempData = (AI_CivDiplomacy)json.readValue((Class)AI_CivDiplomacy.class, jValue);
                Game.getCiv(civID++).aiCivDiplomacy = tempData;
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_AI_Budget() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "AI_Budget.json");
            final Json json = new Json();
            AI_Budget tempData = AI_Manager.aiBudget = (AI_Budget)json.fromJson((Class)AI_Budget.class, fileList);
            tempData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static void loadBuildColonization() {
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).buildColonizationProvince();
        }
    }
    
    public static void loadBuild_ProvincesUnderSiege() {
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).buildUnderSiege();
        }
    }
    
    public static void loadBuild_ProvincesOccupied() {
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).buildOccupiedProvinces();
        }
    }
    
    public static final void loadSaveWars() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Wars.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            WarManager.lWars.clear();
            for (final JsonValue jValue : tempArrayData) {
                final War tempData = (War)json.readValue((Class)War.class, jValue);
                WarManager.lWars.put(tempData.key, tempData);
                tempData.loadSave_AddInWar();
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        WarManager.iWarsSize = WarManager.lWars.size();
    }
    
    public static final void loadSaveWars_BuildData() {
        try {
            WarManager.buildWars_Load();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadPlayer_Data() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "PlayerData.json");
            final Json json = new Json();
            PlayerData tempData = (PlayerData)json.fromJson((Class)PlayerData.class, fileList);
            Game.player.playerData = tempData;
            tempData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        Game.player.iPinnedArmiesSize = Game.player.playerData.pinnedArmies.size();
        Game.player.iPinnedProvincesSize = Game.player.playerData.pinnedProvinces.size();
        Game.player.iActiveEventsSize = Game.player.playerData.activeEvents.size();
    }
    
    public static final void loadPlayer_Stats() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "PlayerStats.json");
            final Json json = new Json();
            PlayerStats tempData = (PlayerStats)json.fromJson((Class)PlayerStats.class, fileList);
            Game.player.playerStats = tempData;
            tempData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadPlayer_Stats2() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "PlayerStats2.json");
            final Json json = new Json();
            PlayerStats2 tempData = (PlayerStats2)json.fromJson((Class)PlayerStats2.class, fileList);
            Game.player.playerStats2 = tempData;
            tempData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadPlayer_Stats3() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "PlayerStats3.json");
            final Json json = new Json();
            PlayerStats3 tempData = (PlayerStats3)json.fromJson((Class)PlayerStats3.class, fileList);
            Game.player.playerStats3 = tempData;
            tempData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_ProvinceData() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Provinces_Data.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tID = 0;
            for (final JsonValue jValue : tempArrayData) {
                final ProvinceData tempData = (ProvinceData)json.readValue((Class)ProvinceData.class, jValue);
                Game.lProvincesData.set(tID, tempData);
                ++tID;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_ProvinceData2() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Provinces_Data2.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tID = 0;
            for (final JsonValue jValue : tempArrayData) {
                final ProvinceData2 tempData = (ProvinceData2)json.readValue((Class)ProvinceData2.class, jValue);
                Game.lProvincesData2.set(tID, tempData);
                ++tID;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_ProvinceData3() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Provinces_Data3.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tID = 0;
            for (final JsonValue jValue : tempArrayData) {
                final ProvinceData3 tempData = (ProvinceData3)json.readValue((Class)ProvinceData3.class, jValue);
                Game.lProvincesData3.set(tID, tempData);
                ++tID;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_ProvinceData4() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Provinces_Data4.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tID = 0;
            for (final JsonValue jValue : tempArrayData) {
                final ProvinceData4 tempData = (ProvinceData4)json.readValue((Class)ProvinceData4.class, jValue);
                Game.lProvincesData4.set(tID, tempData);
                ++tID;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_ProvinceData5() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Provinces_Data5.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tID = 0;
            for (final JsonValue jValue : tempArrayData) {
                final ProvinceData5 tempData = (ProvinceData5)json.readValue((Class)ProvinceData5.class, jValue);
                Game.lProvincesData5.set(tID, tempData);
                ++tID;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_ProvinceData6() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Provinces_Data6.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tID = 0;
            for (final JsonValue jValue : tempArrayData) {
                final ProvinceData6 tempData = (ProvinceData6)json.readValue((Class)ProvinceData6.class, jValue);
                Game.lProvincesData6.set(tID, tempData);
                ++tID;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_ProvinceData7() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Provinces_Data7.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tID = 0;
            for (final JsonValue jValue : tempArrayData) {
                final ProvinceData7 tempData = (ProvinceData7)json.readValue((Class)ProvinceData7.class, jValue);
                Game.lProvincesData7.set(tID, tempData);
                ++tID;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_ProvinceData8() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Provinces_Data8.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tID = 0;
            for (final JsonValue jValue : tempArrayData) {
                final ProvinceData8 tempData = (ProvinceData8)json.readValue((Class)ProvinceData8.class, jValue);
                Game.lProvincesData8.set(tID, tempData);
                ++tID;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_ProvinceData9() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Provinces_Data9.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tID = 0;
            for (final JsonValue jValue : tempArrayData) {
                final ProvinceData9 tempData = (ProvinceData9)json.readValue((Class)ProvinceData9.class, jValue);
                Game.lProvincesData9.set(tID, tempData);
                ++tID;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_ProvinceData10() {
        try {
            final FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + LoadSavedGameManager.key + "/" + "Provinces_Data10.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tID = 0;
            for (final JsonValue jValue : tempArrayData) {
                final ProvinceData10 tempData = (ProvinceData10)json.readValue((Class)ProvinceData10.class, jValue);
                Game.lProvincesData10.set(tID, tempData);
                ++tID;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    static {
        LoadSavedGameManager.key = "";
        LoadSavedGameManager.playerKey = "";
    }
}
