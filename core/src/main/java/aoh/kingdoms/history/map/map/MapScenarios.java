// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.map;

import aoc.kingdoms.lukasz.menus.NewGame.NewGame;
import aoc.kingdoms.lukasz.menu.View;
import aoc.kingdoms.lukasz.menus.LoadSave.Menu_LoadScenario;
import aoc.kingdoms.lukasz.map.army.ArmyGeneral;
import aoc.kingdoms.lukasz.map.army.ArmyDivision;
import aoc.kingdoms.lukasz.map.technology.TechnologyTree;
import aoc.kingdoms.lukasz.map.army.ArmyRegiment;
import aoc.kingdoms.lukasz.map.civilization.CivilizationGeneralsPool;
import aoc.kingdoms.lukasz.map.advisors.AdvisorManager;
import aoc.kingdoms.lukasz.map.civilization.CivilizationAdvisorsPool;
import aoc.kingdoms.lukasz.map.allianceHRE.Alliance;
import aoc.kingdoms.lukasz.map.BonusesManager;
import aoc.kingdoms.lukasz.map.terrain.Terrain;
import aoc.kingdoms.lukasz.menusInGame.InGame;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.Game_Ages;
import aoc.kingdoms.lukasz.jakowski.Missions.MissionTree;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court_Government;
import aoc.kingdoms.lukasz.map.FormableCivManager;
import aoc.kingdoms.lukasz.events.EventsManager;
import aoc.kingdoms.lukasz.map.civilization.CivilizationRanking;
import aoc.kingdoms.lukasz.jakowski.SaveLoad.LoadManager;
import aoc.kingdoms.lukasz.map.AdvantagesManager;
import aoc.kingdoms.lukasz.map.civilization.CivilizationRegionsManager;
import aoc.kingdoms.lukasz.map.RulersManager;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.map.diplomacy.Diplomacy;
import aoc.kingdoms.lukasz.menus.MainMenu;
import aoc.kingdoms.lukasz.jakowski.AI.AI_Manager;
import aoc.kingdoms.lukasz.jakowski.AI.AI_Budget;
import aoc.kingdoms.lukasz.map.war.WarManager;
import aoc.kingdoms.lukasz.map.SiegeManager;
import aoc.kingdoms.lukasz.map.ResourcesManager;
import aoc.kingdoms.lukasz.jakowski.Player.Player;
import aoc.kingdoms.lukasz.map.province.ProvinceBorder;
import aoc.kingdoms.lukasz.map.WondersManager;
import aoc.kingdoms.lukasz.jakowski.SaveLoad.SaveGameManager;
import aoc.kingdoms.lukasz.jakowski.CharactersManager;
import java.util.Iterator;
import com.badlogic.gdx.utils.JsonValue;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.jakowski.SaveLoad.SaveManager;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioSettings;
import com.badlogic.gdx.graphics.Texture;
import aoc.kingdoms.lukasz.textures.ImageManager;
import com.badlogic.gdx.files.FileHandle;
import com.codedisaster.steamworks.SteamUGC;
import com.badlogic.gdx.Gdx;
import aoc.kingdoms.lukasz.jakowski.Steam.SteamManager;
import com.badlogic.gdx.utils.Json;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.CFG;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.textures.Image;
import java.util.List;

public class MapScenarios
{
    public int SCENARIOS_SIZE;
    public List<String> lScenarios_TagsList;
    public List<Image> lScenarios_Preview;
    public String sActiveScenarioTag;
    public boolean scenarioEditor_isCampaign;
    public List<Integer> editorProvinceReligion;
    public List<Details> details;
    public static Details scenarioEditorDetails;
    public static int DEFAULT_VALUE;
    
    public MapScenarios() {
        this.lScenarios_TagsList = new ArrayList<String>();
        this.lScenarios_Preview = new ArrayList<Image>();
        this.sActiveScenarioTag = "";
        this.scenarioEditor_isCampaign = false;
        this.editorProvinceReligion = new ArrayList<Integer>();
        this.details = new ArrayList<Details>();
    }
    
    public final void loadScenarios(final boolean updateDefaultScenario) {
        if (!updateDefaultScenario) {
            this.clearScenarios();
        }
        final List<String> tempScenarios_TagsList = new ArrayList<String>();
        final List<Details> tempDetails = new ArrayList<Details>();
        if (CFG.isDesktop()) {
            if (FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "Scenarios.txt").exists()) {
                final FileHandle tempFileT = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "Scenarios.txt");
                final String tempT = tempFileT.readString();
                final String[] tagsSPLITED = tempT.split(";");
                for (int i = 0, iSize = tagsSPLITED.length; i < iSize; ++i) {
                    final Json json = new Json();
                    FileHandle file = null;
                    for (int a = 0; a < SteamManager.modsFoldersSize; ++a) {
                        if (FileManager.IS_MAC) {
                            if (Gdx.files.external(SteamManager.modsFolders.get(a) + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tagsSPLITED[i] + "/" + "Details.json").exists()) {
                                file = Gdx.files.external(SteamManager.modsFolders.get(a) + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tagsSPLITED[i] + "/" + "Details.json");
                                break;
                            }
                        }
                        else if (Gdx.files.internal(SteamManager.modsFolders.get(a) + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tagsSPLITED[i] + "/" + "Details.json").exists()) {
                            file = Gdx.files.internal(SteamManager.modsFolders.get(a) + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tagsSPLITED[i] + "/" + "Details.json");
                            break;
                        }
                    }
                    if (file == null) {
                        for (int a = 0; a < SteamManager.itemsInstalledSize; ++a) {
                            if (Gdx.files.absolute(SteamManager.itemsInstalled.get(a).getFolder() + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tagsSPLITED[i] + "/" + "Details.json").exists()) {
                                file = Gdx.files.absolute(SteamManager.itemsInstalled.get(a).getFolder() + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tagsSPLITED[i] + "/" + "Details.json");
                                break;
                            }
                        }
                    }
                    if (file == null) {
                        file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tagsSPLITED[i] + "/" + "Details.json");
                    }
                    if (file != null && file.exists()) {
                        tempScenarios_TagsList.add(tagsSPLITED[i]);
                        tempDetails.add((Details)json.fromJson((Class)Details.class, file));
                    }
                }
            }
            final int listBegin = tempScenarios_TagsList.size();
            FileHandle[] files;
            if (FileManager.IS_MAC) {
                files = Gdx.files.external("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/").list();
            }
            else {
                files = Gdx.files.internal("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/").list();
            }
            for (final FileHandle file2 : files) {
                if (!tempScenarios_TagsList.contains(file2.name())) {
                    tempScenarios_TagsList.add(file2.name());
                }
            }
            for (int j = 0; j < SteamManager.modsFoldersSize; ++j) {
                if (FileManager.IS_MAC) {
                    files = Gdx.files.external(SteamManager.modsFolders.get(j) + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/").list();
                }
                else {
                    files = Gdx.files.internal(SteamManager.modsFolders.get(j) + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/").list();
                }
                for (final FileHandle file : files) {
                    if (!tempScenarios_TagsList.contains(file.name())) {
                        tempScenarios_TagsList.add(file.name());
                    }
                }
            }
            for (int j = 0; j < SteamManager.itemsInstalledSize; ++j) {
                final FileHandle[] list;
                files = (list = Gdx.files.absolute(SteamManager.itemsInstalled.get(j).getFolder() + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/").list());
                for (final FileHandle file : list) {
                    if (!tempScenarios_TagsList.contains(file.name())) {
                        tempScenarios_TagsList.add(file.name());
                    }
                }
            }
            for (int j = listBegin, iSize2 = tempScenarios_TagsList.size(); j < iSize2; ++j) {
                try {
                    final Json json2 = new Json();
                    FileHandle file3 = null;
                    for (int a2 = 0; a2 < SteamManager.modsFoldersSize; ++a2) {
                        if (FileManager.IS_MAC) {
                            if (Gdx.files.external(SteamManager.modsFolders.get(a2) + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tempScenarios_TagsList.get(j) + "/" + "Details.json").exists()) {
                                file3 = Gdx.files.external(SteamManager.modsFolders.get(a2) + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tempScenarios_TagsList.get(j) + "/" + "Details.json");
                                break;
                            }
                        }
                        else if (Gdx.files.internal(SteamManager.modsFolders.get(a2) + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tempScenarios_TagsList.get(j) + "/" + "Details.json").exists()) {
                            CFG.LOG("" + SteamManager.modsFolders.get(a2) + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tempScenarios_TagsList.get(j) + "/" + "Details.json");
                            file3 = Gdx.files.internal(SteamManager.modsFolders.get(a2) + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tempScenarios_TagsList.get(j) + "/" + "Details.json");
                            break;
                        }
                    }
                    if (file3 == null) {
                        for (int a2 = 0; a2 < SteamManager.itemsInstalledSize; ++a2) {
                            if (Gdx.files.absolute(SteamManager.itemsInstalled.get(a2).getFolder() + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tempScenarios_TagsList.get(j) + "/" + "Details.json").exists()) {
                                file3 = Gdx.files.absolute(SteamManager.itemsInstalled.get(a2).getFolder() + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tempScenarios_TagsList.get(j) + "/" + "Details.json");
                                break;
                            }
                        }
                    }
                    if (file3 == null) {
                        file3 = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tempScenarios_TagsList.get(j) + "/" + "Details.json");
                    }
                    if (file3 != null && file3.exists()) {
                        tempDetails.add((Details)json2.fromJson((Class)Details.class, file3));
                    }
                }
                catch (final Exception ex) {
                    tempScenarios_TagsList.remove(j--);
                    iSize2 = tempScenarios_TagsList.size();
                    CFG.exceptionStack(ex);
                }
            }
        }
        else {
            final FileHandle tempFileT = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "Scenarios.txt");
            final String tempT = tempFileT.readString();
            final String[] tagsSPLITED = tempT.split(";");
            for (int i = 0, iSize = tagsSPLITED.length; i < iSize; ++i) {
                tempScenarios_TagsList.add(tagsSPLITED[i]);
                final Json json = new Json();
                final FileHandle file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tagsSPLITED[i] + "/" + "Details.json");
                tempDetails.add((Details)json.fromJson((Class)Details.class, file));
            }
        }
        while (tempScenarios_TagsList.size() > 0) {
            int nAdd = 0;
            for (int k = 1; k < tempScenarios_TagsList.size(); ++k) {
                if (tempDetails.get(nAdd).Year < tempDetails.get(k).Year) {
                    nAdd = k;
                }
            }
            this.lScenarios_TagsList.add(tempScenarios_TagsList.get(nAdd));
            tempScenarios_TagsList.remove(nAdd);
            this.details.add(tempDetails.get(nAdd));
            tempDetails.remove(nAdd);
        }
        this.SCENARIOS_SIZE = this.lScenarios_TagsList.size();
        if (updateDefaultScenario) {
            Game.updateDaultScenarioID();
        }
        this.loadPreviews();
        tempScenarios_TagsList.clear();
        tempDetails.clear();
    }
    
    public final void clearScenarios() {
        this.lScenarios_TagsList.clear();
        this.details.clear();
        this.SCENARIOS_SIZE = 0;
    }
    
    public final void loadPreviews() {
        if (this.lScenarios_Preview.size() > 0) {
            for (int i = 0; i < this.lScenarios_Preview.size(); ++i) {
                this.lScenarios_Preview.get(i).dispose();
                this.lScenarios_Preview.set(i, null);
            }
            this.lScenarios_Preview.clear();
        }
        for (int i = 0; i < this.lScenarios_TagsList.size(); ++i) {
            try {
                if (FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(i) + "/" + "previewSpecial.png").exists()) {
                    this.lScenarios_Preview.add(new Image(ImageManager.loadTexture_RGB888("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(i) + "/" + "previewSpecial.png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
                }
                else {
                    this.lScenarios_Preview.add(new Image(ImageManager.loadTexture_RGB888("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(i) + "/" + "preview.png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
    }
    
    public final void saveScenario_1() {
        final Details nDetails = new Details();
        nDetails.Name = ScenarioSettings.sName;
        nDetails.Author = ScenarioSettings.sAuthor;
        nDetails.Day = Game_Calendar.currentDay;
        nDetails.Month = Game_Calendar.currentMonth;
        nDetails.Year = Game_Calendar.currentYear;
        nDetails.Civs = Game.getCivsSize() - 1;
        nDetails.Age = Game.gameAges.getAgeOfYear(Game_Calendar.currentYear);
        nDetails.Campaign = this.scenarioEditor_isCampaign;
        nDetails.CivDefault_Gold = MapScenarios.scenarioEditorDetails.CivDefault_Gold;
        nDetails.CivDefault_GoldRandom = MapScenarios.scenarioEditorDetails.CivDefault_GoldRandom;
        nDetails.CivDefault_Legacy = MapScenarios.scenarioEditorDetails.CivDefault_Legacy;
        nDetails.CivDefault_LegacyRandom = MapScenarios.scenarioEditorDetails.CivDefault_LegacyRandom;
        nDetails.CivDefault_ManpowerPercentage = MapScenarios.scenarioEditorDetails.CivDefault_ManpowerPercentage;
        nDetails.CivDefault_Technology = MapScenarios.scenarioEditorDetails.CivDefault_Technology;
        nDetails.HoursPerTurn = MapScenarios.scenarioEditorDetails.HoursPerTurn;
        nDetails.ProvinceDefault_Population = MapScenarios.scenarioEditorDetails.ProvinceDefault_Population;
        nDetails.ProvinceDefault_Economy = MapScenarios.scenarioEditorDetails.ProvinceDefault_Economy;
        nDetails.ProvinceDefault_TaxEfficiency = MapScenarios.scenarioEditorDetails.ProvinceDefault_TaxEfficiency;
        nDetails.ProvinceDefault_Manpower = MapScenarios.scenarioEditorDetails.ProvinceDefault_Manpower;
        final Json json = SaveManager.getJson();
        final FileHandle file = FileManager.getSaveType("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + ScenarioSettings.sTag + "/" + "Details.json");
        file.writeString(json.prettyPrint((Object)nDetails), false);
    }
    
    public final void saveScenario_2() {
    }
    
    public final void saveScenario_3() {
        SaveManager.saveScenarioDetails();
    }
    
    public final void saveScenario_4() {
    }
    
    public final void saveScenario_5() {
        SaveManager.saveScenarioDetailsProvinces();
    }
    
    public final void saveScenario_6() {
        this.saveWasteland();
    }
    
    public final void saveScenario_7() {
    }
    
    public final void saveScenario_8() {
        SaveManager.saveScenarioArmies();
    }
    
    public final void saveScenario_9() {
    }
    
    public final void saveScenario_10() {
        SaveManager.saveScenarioBuildings();
    }
    
    public final void saveScenario_11() {
        SaveManager.saveScenariosList();
    }
    
    public final void saveScenario_12() {
        SaveManager.saveScenarioAlliances();
    }
    
    public final void saveScenario_13() {
        SaveManager.saveScenarioRelations();
    }
    
    public final void saveScenario_14() {
    }
    
    public final void saveScenario_15() {
        SaveManager.saveScenarioGuarantee();
    }
    
    public final void saveScenario_16() {
        SaveManager.saveScenarioDefensive();
    }
    
    public final void saveScenario_17() {
    }
    
    public final void saveScenario_18() {
        SaveManager.saveScenarioMilitaryAccess();
    }
    
    public final void saveScenario_19() {
        SaveManager.saveScenarioNonAggression();
    }
    
    public final void saveScenario_20() {
        SaveManager.saveScenarioTruces();
    }
    
    public final void saveScenario_21() {
    }
    
    public final void saveScenario_22() {
        SaveManager.saveScenarioAlliancesSpecial();
    }
    
    public final void saveScenario_23() {
        SaveManager.saveScenarioCores();
    }
    
    public final void saveScenario_24() {
    }
    
    public final void saveScenario_25() {
        SaveManager.saveScenarioReligion();
        Game.mapScenarios.editorProvinceReligion.clear();
    }
    
    public final void saveWasteland() {
        final FileHandle fileWrite = FileManager.getSaveType("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + ScenarioSettings.sTag + "/" + "Wasteland.txt");
        fileWrite.writeString("", false);
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            if (Game.getProvince(i).getWasteland() >= 0) {
                fileWrite.writeString(i + ";", true);
            }
        }
    }
    
    public final void loadWasteland() {
        try {
            if (FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(Game.scenarioID) + "/" + "Wasteland.txt").exists()) {
                final FileHandle tempFileT = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(Game.scenarioID) + "/" + "Wasteland.txt");
                final String tempT = tempFileT.readString();
                if (tempT.length() > 0) {
                    String[] tagsSPLITED = tempT.split(";");
                    for (int i = 0, iSize = tagsSPLITED.length; i < iSize; ++i) {
                        Game.getProvince(Integer.parseInt(tagsSPLITED[i])).setWasteland(0);
                    }
                    tagsSPLITED = null;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final List<Civilization> loadCivilizations(final boolean nEditor) {
        final List<Civilization> lCivs = new ArrayList<Civilization>();
        lCivs.add(Game.getNeutralCivilization());
        lCivs.get(0).iCivID = 0;
        try {
            FileHandle fileList;
            if (Game.settingsManager.LANGUAGE_TAG == null || Game.settingsManager.LANGUAGE_TAG.length() == 0 || !FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(Game.scenarioID) + "/" + "Data_" + Game.settingsManager.LANGUAGE_TAG + ".json").exists()) {
                fileList = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(Game.scenarioID) + "/" + "Data.json");
            }
            else {
                fileList = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(Game.scenarioID) + "/" + "Data_" + Game.settingsManager.LANGUAGE_TAG + ".json");
            }
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tCivID = 1;
            for (final JsonValue jValue : tempArrayData) {
                ScenarioCivData tempData = (ScenarioCivData)json.readValue((Class)ScenarioCivData.class, jValue);
                final Game.LoadCivilizationData civData = Game.loadCivilization(tempData.CivTAG);
                lCivs.add(new Civilization(tCivID, tempData.CivTAG, tempData.PCID, civData.iR, civData.iG, civData.iB, tempData.CPID, civData.ReligionID, civData.GroupID, true));
                lCivs.get(tCivID).scenarioEditorData = tempData;
                ++tCivID;
                tempData = null;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            this.sActiveScenarioTag = this.lScenarios_TagsList.get(Game.scenarioID);
        }
        catch (final Exception ex) {
            this.sActiveScenarioTag = "";
        }
        return lCivs;
    }
    
    public final void loadCivilizationsProvinces() {
        try {
            final FileHandle fileList = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(Game.scenarioID) + "/" + "DataProvinces.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            int tCivID = 1;
            for (final JsonValue jValue : tempArrayData) {
                final ScenarioCivData_Provinces tempData = (ScenarioCivData_Provinces)json.readValue((Class)ScenarioCivData_Provinces.class, jValue);
                for (int i = 0, iSize = tempData.Provinces.length; i < iSize; ++i) {
                    Game.getProvince(tempData.Provinces[i]).setCivID_LoadScenario(tCivID);
                }
                ++tCivID;
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void loadCores() {
        try {
            if (FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(Game.scenarioID) + "/" + "Cores.json").exists()) {
                final FileHandle fileList = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(Game.scenarioID) + "/" + "Cores.json");
                final Json json = new Json();
                ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
                for (final JsonValue jValue : tempArrayData) {
                    final ScenarioCoresData tempData = (ScenarioCoresData)json.readValue((Class)ScenarioCoresData.class, jValue);
                    if (tempData.id >= 0 && tempData.id < Game.getProvincesSize()) {
                        Game.getProvince(tempData.id).clearCores();
                        for (int i = 0; i < tempData.Cores.size(); ++i) {
                            if (tempData.Cores.get(i) > 0 && tempData.Cores.get(i) < Game.getCivsSize()) {
                                Game.getProvince(tempData.id).addCore_Just(tempData.Cores.get(i));
                            }
                        }
                    }
                }
                tempArrayData.clear();
                tempArrayData = null;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void loadReligions_JustBuild(final boolean editorMode) {
        if (editorMode) {
            this.editorProvinceReligion.clear();
            for (int i = 0; i < Game.getProvincesSize(); ++i) {
                this.editorProvinceReligion.add(-1);
            }
        }
    }
    
    public final void loadReligions(final boolean editorMode) {
        try {
            this.loadReligions_JustBuild(editorMode);
            final FileHandle fileList = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(Game.scenarioID) + "/" + "Religions.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                final ScenarioReligionData tempData = (ScenarioReligionData)json.readValue((Class)ScenarioReligionData.class, jValue);
                if (tempData.id >= 0 && tempData.id < Game.getProvincesSize()) {
                    Game.getProvince(tempData.id).setReligion(tempData.rel);
                    if (!editorMode && Game.getProvince(tempData.id).isCapital && Game.getProvince(tempData.id).getCivID() > 0 && Game.getCiv(Game.getProvince(tempData.id).getCivID()).getCapitalProvinceID() == tempData.id) {
                        Game.getCiv(Game.getProvince(tempData.id).getCivID()).setReligionID_UpdateBonuses(tempData.rel);
                    }
                    if (!editorMode) {
                        continue;
                    }
                    this.editorProvinceReligion.set(tempData.id, tempData.rel);
                }
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void loadScenarioCharacters(final boolean nEditor) {
        if (!nEditor) {
            try {
                if (FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(Game.scenarioID) + "/" + "Characters.json").exists()) {
                    final FileHandle fileList = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(Game.scenarioID) + "/" + "Characters.json");
                    final Json json = new Json();
                    ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
                    for (final JsonValue jValue : tempArrayData) {
                        try {
                            final CharactersManager.ScenarioCharacters tData = (CharactersManager.ScenarioCharacters)json.readValue((Class)CharactersManager.ScenarioCharacters.class, jValue);
                            final int civID = Game.getCivID(tData.CivTAG);
                            if (civID <= 0) {
                                continue;
                            }
                            if (tData.Administrative != null && tData.Administrative.length() > 0) {
                                CharactersManager.loadAdvisor(civID, tData.Administrative, 0);
                            }
                            if (tData.Economic != null && tData.Economic.length() > 0) {
                                CharactersManager.loadAdvisor(civID, tData.Economic, 1);
                            }
                            if (tData.Innovation != null && tData.Innovation.length() > 0) {
                                CharactersManager.loadAdvisor(civID, tData.Innovation, 2);
                            }
                            if (tData.Military != null && tData.Military.length() > 0) {
                                CharactersManager.loadAdvisor(civID, tData.Military, 3);
                            }
                            if (tData.Generals == null || tData.Generals.length <= 0) {
                                continue;
                            }
                            for (int i = tData.Generals.length - 1; i >= 0; --i) {
                                if (tData.Generals[i].length() > 0) {
                                    CharactersManager.loadGeneral(civID, tData.Generals[i], -99, -99);
                                }
                            }
                        }
                        catch (final Exception ex) {
                            CFG.exceptionStack(ex);
                        }
                    }
                    tempArrayData.clear();
                    tempArrayData = null;
                }
            }
            catch (final Exception ex2) {
                CFG.exceptionStack(ex2);
            }
        }
    }
    
    public final void createScenario() {
        Game.disposeCivilizations();
        Game.clearAllianceSpecial();
        MapScenarios.scenarioEditorDetails = new Details();
        Game_Calendar.TURN_ID = 1;
        SaveGameManager.AUTO_SAVE_LAST_TURN_ID = Game_Calendar.TURN_ID;
        Game_Calendar.HOUR = 0;
        try {
            Game_Calendar.currentDay = this.details.get(Game.scenarioID).Day;
            Game_Calendar.currentMonth = this.details.get(Game.scenarioID).Month;
            Game_Calendar.currentYear = this.details.get(Game.scenarioID).Year;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        Game_Calendar.CURRENT_AGEID = Game.gameAges.getAgeOfYear(Game_Calendar.currentYear);
        Game_Calendar.updateManpowerImg();
        MapScenarios.scenarioEditorDetails = new Details();
        ScenarioSettings.sAuthor = "";
        ScenarioSettings.sTag = "";
        ScenarioSettings.sName = "";
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            Game.getProvince(i).clearData();
        }
        WondersManager.initProvinceWonders();
        Game.lCivs.add(Game.getNeutralCivilization());
        Game.lCivs.get(0).iCivID = 0;
        Game.iCivsSize = Game.lCivs.size();
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            for (int j = 0; j < Game.getProvince(i).getProvinceBordersLandByLandSize(); ++j) {
                Game.getProvince(i).getProvinceBordersLandByLand().get(j).setIsCivilizationBorder(Game.getProvince(i).getCivID() != Game.getProvince(Game.getProvince(i).getProvinceBordersLandByLand().get(j).getWithProvinceID()).getCivID(), i);
            }
            if (Game.getProvince(i).getCivID() > 0) {
                Game.getCiv(Game.getProvince(i).getCivID()).addProvince_Just(i);
            }
        }
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            Game.updateProvinceBorder(i);
        }
        Game.player = new Player();
    }
    
    public final void clearData() {
        SaveGameManager.AUTO_SAVE_LAST_TURN_ID = 0;
        this.loadScenario_1_ClearData();
        ResourcesManager.resetPriceChangePerc();
        Game.player = new Player();
        Game.player.fog.initFogOfWar();
        Game.battleManager.clearData();
        SiegeManager.clearData();
        Game.revolutionManager.clearData();
        Game.gameThread.clearData();
        Game.gameThreadTurns.clearData();
        Game.gameThreadTurns.iLastUpdateTurnID = 0;
        Game.gameThreadTurns.THREAD_TURN_ID = 0;
        Game.gameThreadUpdate.clearData();
        WarManager.clearData();
        AI_Manager.aiBudget = new AI_Budget();
        Game.aiAggressivnes = 0;
    }
    
    public final void loadScenario_1_ClearData() {
        Game.disposeCivilizations();
        Game.clearAllianceSpecial();
        Game_Calendar.TURN_ID = 1;
        Game_Calendar.HOUR = 0;
        Game_Calendar.currentDay = this.details.get(Game.scenarioID).Day;
        Game_Calendar.currentMonth = this.details.get(Game.scenarioID).Month;
        Game_Calendar.currentYear = this.details.get(Game.scenarioID).Year;
        Game_Calendar.CURRENT_AGEID = Game.gameAges.getAgeOfYear(Game_Calendar.currentYear);
    }
    
    public final void loadScenario_1() {
        MainMenu.canContinue = false;
        this.loadScenario_1_ClearData();
        Game_Calendar.updateManpowerImg();
        Game_Calendar.updateAge(false);
        this.scenarioEditor_isCampaign = this.details.get(Game.scenarioID).Campaign;
        MapScenarios.scenarioEditorDetails = new Details();
        MapScenarios.scenarioEditorDetails.CivDefault_Gold = this.details.get(Game.scenarioID).CivDefault_Gold;
        MapScenarios.scenarioEditorDetails.CivDefault_GoldRandom = this.details.get(Game.scenarioID).CivDefault_GoldRandom;
        MapScenarios.scenarioEditorDetails.CivDefault_Legacy = this.details.get(Game.scenarioID).CivDefault_Legacy;
        MapScenarios.scenarioEditorDetails.CivDefault_LegacyRandom = this.details.get(Game.scenarioID).CivDefault_LegacyRandom;
        MapScenarios.scenarioEditorDetails.CivDefault_ManpowerPercentage = this.details.get(Game.scenarioID).CivDefault_ManpowerPercentage;
        MapScenarios.scenarioEditorDetails.CivDefault_Technology = this.details.get(Game.scenarioID).CivDefault_Technology;
        MapScenarios.scenarioEditorDetails.HoursPerTurn = this.details.get(Game.scenarioID).HoursPerTurn;
        MapScenarios.scenarioEditorDetails.ProvinceDefault_Population = this.details.get(Game.scenarioID).ProvinceDefault_Population;
        MapScenarios.scenarioEditorDetails.ProvinceDefault_Economy = this.details.get(Game.scenarioID).ProvinceDefault_Economy;
        MapScenarios.scenarioEditorDetails.ProvinceDefault_TaxEfficiency = this.details.get(Game.scenarioID).ProvinceDefault_TaxEfficiency;
        MapScenarios.scenarioEditorDetails.ProvinceDefault_Manpower = this.details.get(Game.scenarioID).ProvinceDefault_Manpower;
        Game.SCENARIO_EVENTS = true;
        Game.HOURS_PER_TURN = Math.max(1, Math.min(24, this.details.get(Game.scenarioID).HoursPerTurn));
    }
    
    public final void loadScenario_2() {
        this.loadWasteland();
    }
    
    public final void loadScenario_3(final boolean nEditor) {
        Game.lCivs = this.loadCivilizations(nEditor);
        Game.iCivsSize = Game.lCivs.size();
        if (nEditor) {
            for (int i = 0; i < Game.getProvincesSize(); ++i) {
                Game.getProvince(i).fogDrawArmy = true;
            }
        }
    }
    
    public final void loadScenario_3_A() {
        for (int i = 1; i < Game.iCivsSize; ++i) {
            Game.getCiv(i).loadScenario_A();
        }
    }
    
    public final void loadScenario_3_B() {
        for (int i = 1; i < Game.iCivsSize; ++i) {
            Game.getCiv(i).loadScenario_B();
        }
    }
    
    public final void loadScenario_3_C() {
        this.loadCivilizationsProvinces();
    }
    
    public final void loadScenario_4() {
        for (int i = 0; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).buildTechTree();
        }
    }
    
    public final void loadScenario_5() {
        Game.buildProvinceIsCapital();
    }
    
    public final void loadScenario_6() {
        Game.initProvinceData();
    }
    
    public final void loadScenario_7() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            for (int j = 0; j < Game.getProvince(i).getProvinceBordersLandByLandSize(); ++j) {
                Game.getProvince(i).getProvinceBordersLandByLand().get(j).setIsCivilizationBorder(Game.getProvince(i).getCivID() != Game.getProvince(Game.getProvince(i).getProvinceBordersLandByLand().get(j).getWithProvinceID()).getCivID(), i);
            }
            if (Game.getProvince(i).getCivID() > 0) {
                Game.getCiv(Game.getProvince(i).getCivID()).addProvince_LoadScenario(i);
            }
        }
        for (int i = 0; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).updateNumOfProvinces();
        }
    }
    
    public final void loadScenario_8() {
        for (int i = 0; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).diplomacy = new Diplomacy();
            Game.getCiv(i).fDiplomacy = GameValues.diplomacy.STARTING_DIPLOMACY_POINTS;
        }
    }
    
    public final void loadScenario_9() {
        Game.buildWastelandLevels();
    }
    
    public final void loadScenario_10() {
        RulersManager.loadRulers(1, (int)Math.floor(Game.getCivsSize() / 2.0f));
    }
    
    public final void loadScenario_11() {
        RulersManager.loadRulers((int)Math.floor(Game.getCivsSize() / 2.0f), Game.getCivsSize());
    }
    
    public final void loadScenario_12() {
        Game.addSimpleTask(new Game.SimpleTask("buildCivilizationsRegions") {
            @Override
            public void update() {
                CivilizationRegionsManager.buildCivilizationsRegions();
            }
        });
        AdvantagesManager.initRandomAdvantages();
        AdvantagesManager.initAdvantagePoints();
    }
    
    public final void loadScenario_13() {
        this.buildProvincesReligion();
    }
    
    public final void loadScenario_14() {
        this.buildProvincesCores();
    }
    
    public final void loadScenario_15() {
        LoadManager.loadScenarioArmies();
    }
    
    public final void loadScenario_16() {
        this.buildProvincesEconomy();
    }
    
    public final void loadScenario_17() {
        this.buildProvincesTaxEfficiency();
    }
    
    public final void loadScenario_18() {
        this.buildProvincesManpower();
    }
    
    public final void loadScenario_19() {
        this.buildCivsGovernmentBuildings();
    }
    
    public final void loadScenario_20() {
        LoadManager.loadScenarioBuildings();
    }
    
    public final void loadScenario_21() {
        buildProvinceData();
    }
    
    public final void loadScenario_22() {
        buildCivData();
    }
    
    public final void loadScenario_23() {
        this.loadCores();
    }
    
    public final void loadScenario_24() {
        this.buildProvincesPopulation();
    }
    
    public final void loadScenario_25(final boolean nEditor) {
        this.loadReligions(nEditor);
    }
    
    public final void loadScenario_26() {
        this.buildCivsGold();
    }
    
    public final void loadScenario_27() {
        this.buildCivsManpower();
    }
    
    public final void loadScenario_28() {
        ResourcesManager.updateWorldResourcesProduced(true);
    }
    
    public final void loadScenario_29() {
        ResourcesManager.initUniqueCivsGoods();
        Game.buildDistanceToCapital();
    }
    
    public final void loadScenario_30() {
        LoadManager.loadScenarioRelations();
    }
    
    public final void loadScenario_31() {
        LoadManager.loadScenarioMilitaryAccess();
    }
    
    public final void loadScenario_32() {
        LoadManager.loadScenarioAlliances();
    }
    
    public final void loadScenario_33() {
        LoadManager.loadScenarioDefensive();
    }
    
    public final void loadScenario_34() {
        LoadManager.loadScenarioTruces();
    }
    
    public final void loadScenario_35() {
        LoadManager.loadScenarioNonAggression();
    }
    
    public final void loadScenario_36(final boolean nEditor) {
        if (!nEditor) {
            buildStartingRelationsRandom();
        }
    }
    
    public final void loadScenario_37() {
        LoadManager.loadScenarioGuarantee();
    }
    
    public final void loadScenario_38(final boolean editorMode) {
        this.loadScenarioCharacters(editorMode);
    }
    
    public final void loadScenario_39(final boolean editorMode) {
    }
    
    public final void loadScenario_40(final boolean editorMode) {
        if (!editorMode) {
            this.buildStartingAdvisors(1, (int)Math.floor(Game.getCivsSize() / 2.0f));
        }
    }
    
    public final void loadScenario_41(final boolean editorMode) {
        if (!editorMode) {
            this.buildStartingAdvisors((int)Math.floor(Game.getCivsSize() / 2.0f), Game.getCivsSize());
        }
    }
    
    public final void loadScenario_42() {
        LoadManager.loadScenarioAlliancesSpecial();
        Game.loadAlliancesSpecial_Images();
    }
    
    public final void loadScenario_43() {
        buildAlliancesSpecial();
    }
    
    public final void loadScenario_44() {
        buildManpower();
    }
    
    public final void loadScenario_45() {
        CivilizationRanking.buildCivilizationRanking();
    }
    
    public final void loadScenario_46() {
    }
    
    public final void loadScenario_47() {
        Game.mapCities.updateCities();
    }
    
    public final void loadScenario_48() {
        Game.addSimpleTask(new Game.SimpleTask("loadScenario_48") {
            @Override
            public void update() {
                for (int i = 1; i < Game.getCivsSize(); ++i) {
                    Game.mapCities.updateNameToNewTrueOwner_Civ(i, false);
                }
            }
        });
    }
    
    public final void loadScenario_49(final boolean editorMode) {
        if (!editorMode) {
            this.buildStartingGenerals(1, (int)Math.floor(Game.getCivsSize() / 2.0f));
        }
    }
    
    public final void loadScenario_50(final boolean editorMode) {
        if (!editorMode) {
            this.buildStartingGenerals((int)Math.floor(Game.getCivsSize() / 2.0f), Game.getCivsSize());
        }
    }
    
    public final void loadScenario_51(final boolean editorMode) {
        if (!editorMode) {
            this.buildStartingArmy();
        }
    }
    
    public final void loadScenario_52() {
        buildArmyPosition();
    }
    
    public final void loadScenario_53() {
        buildCivsStability();
    }
    
    public final void loadScenario_54() {
        this.buildCivsLegacy_Nukes_Aggressiveness();
    }
    
    public final void loadScenario_55() {
        buildIncome();
    }
    
    public final void loadScenario_56() {
        this.initCivilizations_ConvertReligion();
        this.initCivilizations_NonCore();
    }
    
    public final void loadScenario_57() {
        this.initCivilizations_ArmiesWithoutGenerals();
    }
    
    public final void loadScenario_58() {
        updateUQ_UI();
        buildCivsColors();
    }
    
    public final void loadScenario_59() {
        EventsManager.clearEventsScenario();
        EventsManager.loadEvents_Scenario();
    }
    
    public final void loadScenario_60() {
        this.buildCivsNeighbors();
    }
    
    public final void loadScenario_61() {
    }
    
    public final void loadScenario_62(final boolean nEditor) {
        if (!nEditor) {
            FormableCivManager.buildFormableCivilizations();
            Game.player.loadFormableCivs();
            InGame_Court_Government.reloadFlags = true;
        }
    }
    
    public final void loadScenario_63() {
    }
    
    public final void loadScenario_64() {
        MissionTree.loadMissions_Civs();
    }
    
    public static final void buildCivsColors() {
        for (int i = 0; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).updateColorMap();
        }
    }
    
    public static final void updateUQ_UI() {
        if (Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).UQ_UI) {
            Images.flagBG = Images.flagBGUQ;
            Images.topStats = Images.topStatsUQ;
            Images.topStats2 = Images.topStats2UQ;
            Images.leftSideBar = Images.leftSideBarUQ;
            InGame.outlinerExtraX = InGame.outlinerExtraUQ;
        }
        else {
            Images.flagBG = Images.flagBGClassic;
            Images.topStats = Images.topStatsClassic;
            Images.topStats2 = Images.topStats2Classic;
            Images.leftSideBar = Images.leftSideBarClassic;
            InGame.outlinerExtraX = InGame.outlinerExtraClassic;
        }
    }
    
    public final void buildCivsNeighbors() {
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).civNeighbors.buildNeighbors(i);
        }
    }
    
    public final void initCivilizations_ConvertReligion() {
        for (int i = 0; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).convertReligion.buildProvincesConvertReligion(i);
        }
    }
    
    public final void initCivilizations_NonCore() {
        for (int i = 0; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).civilizationCores.buildProvincesNonCore(i);
        }
    }
    
    public final void initCivilizations_ArmiesWithoutGenerals() {
        for (int i = 0; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).armiesWithoutGenerals.buildArmiesWithoutGenerals(i);
        }
    }
    
    public final void buildProvincesCores() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            if (!Game.getProvince(i).getSeaProvince() && Game.getProvince(i).getCivID() > 0) {
                Game.getProvince(i).addCore(Game.getProvince(i).getCivID());
            }
        }
    }
    
    public final void buildProvincesReligion() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            if (!Game.getProvince(i).getSeaProvince() && Game.getProvince(i).getCivID() > 0) {
                Game.getProvince(i).setReligion_LoadScenario(Game.getCiv(Game.getProvince(i).getCivID()).getReligionID());
            }
        }
    }
    
    public final void buildProvincesPopulation() {
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).scenarioEditorData.Population != 0) {
                Game.getCiv(i).fDiplomacyMax = 0.0f;
                for (int j = 0; j < Game.getCiv(i).getNumOfProvinces(); ++j) {
                    final Civilization civ = Game.getCiv(i);
                    civ.fDiplomacyMax += Math.max(1.0f, Game.getProvince(Game.getCiv(i).getProvinceID(j)).getGrowthRateWithBonuses() + (Game.getProvince(Game.getCiv(i).getProvinceID(j)).isCapital ? (-GameValues.capital.CAPITAL_GROWTH_RATE) : 0.0f) + Game.terrainManager.terrains.get(Game.getProvince(Game.getCiv(i).getProvinceID(j)).getTerrainID()).BasePopulation);
                }
            }
        }
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            if (!Game.getProvince(i).getSeaProvince()) {
                if (Game.getProvince(i).getCivID() == 0) {
                    Game.getProvince(i).setPopulationOfCivID(Game.getProvince(i).getCivID(), (int)((this.details.get(Game.scenarioID).ProvinceDefault_Population + Game.oR.nextInt(this.details.get(Game.scenarioID).ProvinceDefault_Population) / 100.0f) * (Math.max(1.0f, Game.getProvince(i).getGrowthRateWithBonuses() + (Game.getProvince(i).isCapital ? (-GameValues.capital.CAPITAL_GROWTH_RATE) : 0.0f) + Game.terrainManager.terrains.get(Game.getProvince(i).getTerrainID()).BasePopulation) / 10000.0f)));
                }
                else {
                    int population;
                    if (Game.getCiv(Game.getProvince(i).getCivID()).scenarioEditorData.Population == 0) {
                        population = (int)((this.details.get(Game.scenarioID).ProvinceDefault_Population + Game.oR.nextInt(this.details.get(Game.scenarioID).ProvinceDefault_Population) / 100.0f) * (Math.max(1.0f, Game.getProvince(i).getGrowthRateWithBonuses() + (Game.getProvince(i).isCapital ? (-GameValues.capital.CAPITAL_GROWTH_RATE) : 0.0f) + Game.terrainManager.terrains.get(Game.getProvince(i).getTerrainID()).BasePopulation) / 100.0f));
                    }
                    else {
                        population = (int)Math.max(1.0f, Game.getCiv(Game.getProvince(i).getCivID()).scenarioEditorData.Population * (Math.max(1.0f, Game.getProvince(i).getGrowthRateWithBonuses() + (Game.getProvince(i).isCapital ? (-GameValues.capital.CAPITAL_GROWTH_RATE) : 0.0f) + Game.terrainManager.terrains.get(Game.getProvince(i).getTerrainID()).BasePopulation) / Game.getCiv(Game.getProvince(i).getCivID()).fDiplomacyMax));
                    }
                    if (Game.getProvince(i).iCoresSize == 0) {
                        Game.getProvince(i).setPopulationOfCivID(Game.getProvince(i).getCivID(), population);
                    }
                    else if (Game.getProvince(i).iCoresSize == 1) {
                        Game.getProvince(i).setPopulationOfCivID(Game.getProvince(i).getCore(0), population);
                    }
                    else {
                        final List<Integer> tPop = new ArrayList<Integer>();
                        for (int a = 0; a < Game.getProvince(i).iCoresSize; ++a) {
                            tPop.add(GameValues.province.CORES_STARTING_POPULATION_MIN + Game.oR.nextInt(Math.max(1, GameValues.province.CORES_STARTING_POPULATION_RANDOM)));
                        }
                        int sum = 0;
                        for (int a2 = 0; a2 < Game.getProvince(i).iCoresSize; ++a2) {
                            sum += tPop.get(a2);
                        }
                        for (int a2 = 0; a2 < Game.getProvince(i).iCoresSize; ++a2) {
                            Game.getProvince(i).setPopulationOfCivID(Game.getProvince(i).getCore(a2), (int)(population * (tPop.get(a2) / (float)sum)));
                        }
                        tPop.clear();
                    }
                }
            }
        }
    }
    
    public final void buildProvincesEconomy() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            if (!Game.getProvince(i).getSeaProvince()) {
                if (Game.getProvince(i).getCivID() == 0) {
                    Game.getProvince(i).setEconomy(this.details.get(Game.scenarioID).ProvinceDefault_Economy * GameValues.economy.BASE_ECONOMY_NEUTRAL * (Math.max(0.0f, Game.getProvince(i).BaseDevelopment + Game.terrainManager.terrains.get(Game.getProvince(i).getTerrainID()).BaseEconomy) / 100.0f));
                }
                else if (Game.getCiv(Game.getProvince(i).getCivID()).scenarioEditorData.Economy == 0) {
                    Game.getProvince(i).setEconomy(this.details.get(Game.scenarioID).ProvinceDefault_Economy * (Math.max(0.0f, Game.getProvince(i).BaseDevelopment + Game.terrainManager.terrains.get(Game.getProvince(i).getTerrainID()).BaseEconomy) / 100.0f));
                }
                else {
                    Game.getProvince(i).setEconomy(Math.max(1, Game.getCiv(Game.getProvince(i).getCivID()).scenarioEditorData.Economy) * (Math.max(0.0f, Game.getProvince(i).BaseDevelopment + Game.terrainManager.terrains.get(Game.getProvince(i).getTerrainID()).BaseEconomy) / 100.0f));
                }
            }
        }
    }
    
    public final void buildProvincesTaxEfficiency() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            if (!Game.getProvince(i).getSeaProvince()) {
                if (Game.getProvince(i).getCivID() == 0) {
                    Game.getProvince(i).setTaxEfficiency(this.details.get(Game.scenarioID).ProvinceDefault_TaxEfficiency * GameValues.tax.BASE_TAX_EFFICIENCY_NEUTRAL * (Math.max(0.0f, Game.getProvince(i).BaseDevelopment + Game.terrainManager.terrains.get(Game.getProvince(i).getTerrainID()).BaseEconomy) / 100.0f));
                }
                else if (Game.getCiv(Game.getProvince(i).getCivID()).scenarioEditorData.TaxEff == 0) {
                    Game.getProvince(i).setTaxEfficiency(this.details.get(Game.scenarioID).ProvinceDefault_TaxEfficiency * (Math.max(0.0f, Game.getProvince(i).BaseDevelopment + Game.terrainManager.terrains.get(Game.getProvince(i).getTerrainID()).BaseEconomy) / 100.0f));
                }
                else {
                    Game.getProvince(i).setTaxEfficiency(Math.max(1, Game.getCiv(Game.getProvince(i).getCivID()).scenarioEditorData.TaxEff) * (Math.max(0.0f, Game.getProvince(i).BaseDevelopment + Game.terrainManager.terrains.get(Game.getProvince(i).getTerrainID()).BaseEconomy) / 100.0f));
                }
            }
        }
    }
    
    public final void buildProvincesManpower() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            if (!Game.getProvince(i).getSeaProvince()) {
                if (Game.getProvince(i).getCivID() == 0) {
                    Game.getProvince(i).setManpower(this.details.get(Game.scenarioID).ProvinceDefault_Manpower * GameValues.manpower.BASE_MANPOWER_NEUTRAL * (Math.max(0.0f, Game.getProvince(i).BaseDevelopment + Game.terrainManager.terrains.get(Game.getProvince(i).getTerrainID()).BaseEconomy) / 100.0f));
                }
                else if (Game.getCiv(Game.getProvince(i).getCivID()).scenarioEditorData.Manpower == 0) {
                    Game.getProvince(i).setManpower(this.details.get(Game.scenarioID).ProvinceDefault_Manpower * (Math.max(0.0f, Game.getProvince(i).BaseDevelopment + Game.terrainManager.terrains.get(Game.getProvince(i).getTerrainID()).BaseEconomy) / 100.0f));
                }
                else {
                    Game.getProvince(i).setManpower(Math.max(1, Game.getCiv(Game.getProvince(i).getCivID()).scenarioEditorData.Manpower) * (Math.max(0.0f, Game.getProvince(i).BaseDevelopment + Game.terrainManager.terrains.get(Game.getProvince(i).getTerrainID()).BaseEconomy) / 100.0f));
                }
            }
        }
    }
    
    public final void buildCivsGold() {
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).scenarioEditorData.Gold != MapScenarios.DEFAULT_VALUE) {
                Game.getCiv(i).fGold = (float)Game.getCiv(i).scenarioEditorData.Gold;
            }
            else {
                Game.getCiv(i).fGold = (float)(this.details.get(Game.scenarioID).CivDefault_Gold + Game.oR.nextInt(Math.max(1, this.details.get(Game.scenarioID).CivDefault_GoldRandom)));
            }
        }
    }
    
    public final void buildCivsLegacy_Nukes_Aggressiveness() {
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).scenarioEditorData.Legacy != MapScenarios.DEFAULT_VALUE) {
                Game.getCiv(i).fLegacy = (float)Game.getCiv(i).scenarioEditorData.Legacy;
            }
            else {
                Game.getCiv(i).fLegacy = (float)(this.details.get(Game.scenarioID).CivDefault_Legacy + Game.oR.nextInt(Math.max(1, this.details.get(Game.scenarioID).CivDefault_LegacyRandom)));
            }
            if (Game.getCiv(i).scenarioEditorData.Nukes > 0) {
                Game.getCiv(i).setNukes(Game.getCiv(i).scenarioEditorData.Nukes);
            }
            if (Game.getCiv(i).scenarioEditorData.v != 0) {
                Game.getCiv(i).setExtraAggressiveness(Game.getCiv(i).scenarioEditorData.v);
            }
        }
    }
    
    public final void buildCivsManpower() {
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).fManpower = Math.max(0.0, Game.getCiv(i).fManpowerMax * (this.details.get(Game.scenarioID).CivDefault_ManpowerPercentage / 100.0f));
        }
    }
    
    public final void buildCivsGovernmentBuildings() {
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).setCapitalLevel(Math.max(0, Game.getCiv(i).scenarioEditorData.CCL));
            Game.getCiv(i).setMilitaryAcademyLevel(Math.max(0, Game.getCiv(i).scenarioEditorData.MAL));
            Game.getCiv(i).setMilitaryAcademyForGeneralsLevel(Math.max(0, Game.getCiv(i).scenarioEditorData.MAGL));
            Game.getCiv(i).setSupremeCourtLevel(Math.max(0, Game.getCiv(i).scenarioEditorData.SCL));
            Game.getCiv(i).setNuclearReactorLevel(Math.max(0, Game.getCiv(i).scenarioEditorData.NRL));
            Game.getCiv(i).buildCapitalCity_Bonuses();
            Game.getCiv(i).buildMilitaryAcademy_Bonuses();
            Game.getCiv(i).buildMilitaryAcademyForGenerals_Bonuses();
            Game.getCiv(i).buildSupremeCourt_Bonuses();
            Game.getCiv(i).buildNuclearReactor_Bonuses();
        }
    }
    
    public final void buildCivsGovernmentBuildings_LoadSavedGame() {
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).buildCapitalCity_Bonuses();
            Game.getCiv(i).buildMilitaryAcademy_Bonuses();
            Game.getCiv(i).buildMilitaryAcademyForGenerals_Bonuses();
            Game.getCiv(i).buildSupremeCourt_Bonuses();
            Game.getCiv(i).buildNuclearReactor_Bonuses();
        }
    }
    
    public static final void buildArmyPosition() {
        for (int i = 0; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).clearArmyPosition();
        }
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            for (int j = 0; j < Game.getProvince(i).getArmySize(); ++j) {
                Game.getCiv(Game.getProvince(i).getArmy(j).civID).addArmyPosition(i, Game.getProvince(i).getArmy(j).key);
            }
        }
    }
    
    public static void buildProvinceData() {
        BonusesManager.initAndBuildProvinceBonuses();
        Game.initProvinceData();
    }
    
    public static void buildCivData_Load() {
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            try {
                Game.ideologiesManager.updateCivBonuses(i, Game.getCiv(i).getIdeologyID(), 1, true);
                Game.religionManager.updateCivBonuses(i, Game.getCiv(i).getReligionID(), 1, true);
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
    }
    
    public static void buildCivData_Load2() {
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            try {
                Game.getCiv(i).updateRegimentsLimit();
                Game.getCiv(i).updateResearchPerMonth();
                Game.getCiv(i).updateLegacyPerMonth();
                Game.getCiv(i).updateManpowerPerMonth();
                Game.getCiv(i).updateDiplomacyPerMonth();
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
    }
    
    public static void buildCivData() {
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            try {
                Game.ideologiesManager.updateCivBonuses(i, Game.getCiv(i).getIdeologyID(), 1, true);
                Game.religionManager.updateCivBonuses(i, Game.getCiv(i).getReligionID(), 1, true);
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
        buildVassals();
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            try {
                Game.getCiv(i).setTaxationLevel(1);
                Game.getCiv(i).setResearchLevel(1);
                Game.getCiv(i).setCorruption(GameValues.supremeCourt.CORRUPTION_BASE_VALUE);
                Game.getCiv(i).updateRegimentsLimit();
                Game.getCiv(i).updateResearchPerMonth();
                Game.getCiv(i).updateLegacyPerMonth();
                Game.getCiv(i).updateManpowerPerMonth();
                Game.getCiv(i).updateDiplomacyPerMonth();
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
    }
    
    public static final void buildManpower() {
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).updateManpowerPerMonth();
        }
    }
    
    public static final void buildIncome() {
        Game.buildProsperity_AverageEconomy();
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).updateRegimentsLimit();
            Game.getCiv(i).updateArmyMaintenance();
        }
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            Game.getProvince(i).updateProvinceIncome();
        }
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).getPuppetOfCivID() != i) {
                Game.getCiv(i).updateIncome();
            }
        }
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).getPuppetOfCivID() == i) {
                Game.getCiv(i).updateIncome();
            }
        }
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).updateTotalIncomePerMonth();
        }
    }
    
    public static final void buildVassals() {
        try {
            for (int i = 1; i < Game.getCivsSize(); ++i) {
                if (Game.getCiv(i).getPuppetOfCivID() != Game.getCiv(i).getCivID()) {
                    Game.getCiv(Game.getCiv(i).getPuppetOfCivID()).addVassal(Game.getCiv(i).getCivID());
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void buildAlliancesSpecial() {
        for (int i = 0; i < Game.alliancesSpecialSize; ++i) {
            try {
                Game.getCiv(Game.alliancesSpecial.get(i).iLeaderCivID).addInAllianceSpecial(i);
                for (int j = Game.alliancesSpecial.get(i).firstTier.size() - 1; j >= 0; --j) {
                    Game.getCiv(Game.alliancesSpecial.get(i).firstTier.get(j)).addInAllianceSpecial(i);
                }
                for (int j = Game.alliancesSpecial.get(i).secondTier.size() - 1; j >= 0; --j) {
                    Game.getCiv(Game.alliancesSpecial.get(i).secondTier.get(j)).addInAllianceSpecial(i);
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
    }
    
    public static final void buildCivsStability() {
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).updateCivStability();
        }
    }
    
    public static final void buildStartingRelationsRandom() {
        if (GameValues.diplomacy.STARTING_RANDOM_RELATIONS_CHANCE > 0) {
            final List<Integer> lCivs = new ArrayList<Integer>();
            for (int civID = 1; civID < Game.getCivsSize(); ++civID) {
                if (Game.getCiv(civID).getNumOfProvinces() > 0) {
                    for (int i = civID + 1; i < Game.getCivsSize(); ++i) {
                        if (Game.getCiv(civID).diplomacy.getRelation(i) == 0.0f && buildStartingRelationsRandom_IsInDistance(civID, i) && GameValues.diplomacy.STARTING_RANDOM_RELATIONS_CHANCE > Game.oR.nextInt(100)) {
                            lCivs.add(i);
                        }
                    }
                    for (int j = lCivs.size() - 1; j >= 0; --j) {
                        float relation = Game.oR.nextInt(GameValues.diplomacy.STARTING_RANDOM_RELATIONS) - GameValues.diplomacy.STARTING_RANDOM_RELATIONS / 2.0f;
                        relation += ((relation > 0.0f) ? GameValues.diplomacy.STARTING_RANDOM_RELATIONS_MIN : (-GameValues.diplomacy.STARTING_RANDOM_RELATIONS_MIN));
                        Game.getCiv(civID).diplomacy.setRelation(civID, lCivs.get(j), relation);
                        Game.getCiv(lCivs.get(j)).diplomacy.setRelation(lCivs.get(j), civID, relation);
                    }
                    lCivs.clear();
                }
            }
        }
    }
    
    public static boolean buildStartingRelationsRandom_IsInDistance(final int civID, final int rivalID) {
        return Game.getCiv(rivalID).getNumOfProvinces() > 0 && Game.getDistance_PercOfMax(Game.getCiv(civID).getCapitalProvinceID(), Game.getCiv(rivalID).getCapitalProvinceID()) <= GameValues.diplomacy.RANDOM_RELATIONS_DISTANCE;
    }
    
    public final void buildStartingAdvisors(final int startID, final int endID) {
        for (int i = startID; i < endID; ++i) {
            if (Game.getCiv(i).getNumOfProvinces() > 0) {
                if (Game.getCiv(i).advisorAdministration.sName == null && Game.oR.nextInt(100) < GameValues.civRank.CIV_RANK_STARTING_ADVISOR_ADMINISTRATIVE_CHANCE[Game.getCiv(i).iCivRankID]) {
                    Game.getCiv(i).advisorAdministration = CivilizationAdvisorsPool.generateAdvisor_Random(i, 0);
                    final AdvisorManager advisorManager = Game.advisorManager;
                    AdvisorManager.updateCivBonuses(Game.getCiv(i).advisorAdministration, i, 1);
                }
                if (Game.getCiv(i).advisorEconomy.sName == null && Game.oR.nextInt(100) < GameValues.civRank.CIV_RANK_STARTING_ADVISOR_ECONOMY_CHANCE[Game.getCiv(i).iCivRankID]) {
                    Game.getCiv(i).advisorEconomy = CivilizationAdvisorsPool.generateAdvisor_Random(i, 1);
                    final AdvisorManager advisorManager2 = Game.advisorManager;
                    AdvisorManager.updateCivBonuses(Game.getCiv(i).advisorEconomy, i, 1);
                }
                if (Game.getCiv(i).advisorTechnology.sName == null && Game.oR.nextInt(100) < GameValues.civRank.CIV_RANK_STARTING_ADVISOR_INNOVATION_CHANCE[Game.getCiv(i).iCivRankID]) {
                    Game.getCiv(i).advisorTechnology = CivilizationAdvisorsPool.generateAdvisor_Random(i, 2);
                    final AdvisorManager advisorManager3 = Game.advisorManager;
                    AdvisorManager.updateCivBonuses(Game.getCiv(i).advisorTechnology, i, 1);
                }
                if (Game.getCiv(i).advisorMilitary.sName == null && Game.oR.nextInt(100) < GameValues.civRank.CIV_RANK_STARTING_ADVISOR_MILITARY_CHANCE[Game.getCiv(i).iCivRankID]) {
                    Game.getCiv(i).advisorMilitary = CivilizationAdvisorsPool.generateAdvisor_Random(i, 3);
                    final AdvisorManager advisorManager4 = Game.advisorManager;
                    AdvisorManager.updateCivBonuses(Game.getCiv(i).advisorMilitary, i, 1);
                }
            }
        }
    }
    
    public final void buildStartingGenerals(final int startID, final int endID) {
        for (int i = startID; i < endID; ++i) {
            if (Game.getCiv(i).getNumOfProvinces() > 0) {
                for (int j = 0; j < GameValues.civRank.CIV_RANK_NUM_OF_STARTING_GENERALS[Game.getCiv(i).iCivRankID]; ++j) {
                    if (Game.oR.nextInt(100) < GameValues.civRank.CIV_RANK_STARTING_GENERAL_CHANCE[Game.getCiv(i).iCivRankID]) {
                        Game.getCiv(i).addGeneral(CivilizationGeneralsPool.getGeneral_Random(i));
                    }
                }
            }
        }
    }
    
    public final void buildStartingArmy() {
        try {
            for (int i = 1; i < Game.getCivsSize(); ++i) {
                this.buildStartingArmy(i);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void buildStartingArmy(final int i) {
        if (Game.getCiv(i).getNumOfProvinces() > 0) {
            try {
                int units = (int)((GameValues.civRank.CIV_RANK_STARTING_ARMY[Game.getCiv(i).iCivRankID] + ((GameValues.civRank.CIV_RANK_STARTING_ARMY_RANDOM[Game.getCiv(i).iCivRankID] > 0) ? Game.oR.nextInt(GameValues.civRank.CIV_RANK_STARTING_ARMY_RANDOM[Game.getCiv(i).iCivRankID] + 1) : 0)) * Game.ideologiesManager.getIdeology(Game.getCiv(i).getIdeologyID()).STARTING_ARMY);
                if (Game.getCiv(i).getPuppetOfCivID() != i) {
                    units *= (int)GameValues.civRank.CIV_RANK_STARTING_ARMY_VASSAL;
                }
                if (units > 0) {
                    final List<ArmyRegiment> nArmyRegiment = new ArrayList<ArmyRegiment>();
                    int firstLine = (int)Math.ceil(units / 2.0f);
                    int secondLine = (int)Math.floor(units / 2.0f);
                    if (Game.getCiv(i).unitsBest_SupportSize == 0) {
                        firstLine = units;
                        secondLine = 0;
                    }
                    if (Game.getCiv(i).unitsBest_FirstLineSize == 0 && Game.getCiv(i).unitsBest_FlankSize == 0) {
                        firstLine = 0;
                        secondLine = units;
                    }
                    int flankUnits = 0;
                    if (Game.getCiv(i).unitsBest_FlankSize > 0) {
                        if (Game.getCiv(i).unitsBest_FirstLineSize == 0) {
                            flankUnits = firstLine;
                            firstLine = 0;
                        }
                        else {
                            flankUnits = (int)Math.floor(firstLine / 2.0f);
                            firstLine = (int)Math.ceil(firstLine / 2.0f);
                        }
                    }
                    if (Game.getCiv(i).unitsBest_FirstLineSize > 0) {
                        for (int a = 0; a < firstLine; ++a) {
                            final int rand = Game.oR.nextInt(Game.getCiv(i).unitsBest_FirstLineSize);
                            nArmyRegiment.add(new ArmyRegiment(Game.getCiv(i).unitsBest_FirstLine.get(rand).unitID, Game.getCiv(i).unitsBest_FirstLine.get(rand).armyID));
                        }
                    }
                    if (Game.getCiv(i).unitsBest_FlankSize > 0) {
                        for (int a = 0; a < flankUnits; ++a) {
                            final int rand = Game.oR.nextInt(Game.getCiv(i).unitsBest_FlankSize);
                            nArmyRegiment.add(new ArmyRegiment(Game.getCiv(i).unitsBest_Flank.get(rand).unitID, Game.getCiv(i).unitsBest_Flank.get(rand).armyID));
                        }
                    }
                    int siegeUnits = 0;
                    if (Game.getCiv(i).unitsBest_SiegeSize > 0) {
                        siegeUnits = (int)(secondLine * GameValues.civRank.STARTING_ARMY_SIEGE_UNITS_PERC[Game.getCiv(i).iCivRankID]);
                        secondLine -= siegeUnits;
                    }
                    if (Game.getCiv(i).unitsBest_SupportSize > 0) {
                        for (int a2 = 0; a2 < secondLine; ++a2) {
                            final int rand2 = Game.oR.nextInt(Game.getCiv(i).unitsBest_SupportSize);
                            nArmyRegiment.add(new ArmyRegiment(Game.getCiv(i).unitsBest_Support.get(rand2).unitID, Game.getCiv(i).unitsBest_Support.get(rand2).armyID));
                        }
                    }
                    if (Game.getCiv(i).unitsBest_SiegeSize > 0) {
                        for (int a2 = 0; a2 < siegeUnits; ++a2) {
                            final int rand2 = Game.oR.nextInt(Game.getCiv(i).unitsBest_SiegeSize);
                            nArmyRegiment.add(new ArmyRegiment(Game.getCiv(i).unitsBest_Siege.get(rand2).unitID, Game.getCiv(i).unitsBest_Siege.get(rand2).armyID));
                        }
                    }
                    final int inProvinceID = (Game.getCiv(i).getCapitalProvinceID() >= 0 && Game.getProvince(Game.getCiv(i).getCapitalProvinceID()).getCivID() == i) ? Game.getCiv(i).getCapitalProvinceID() : Game.getCiv(i).getProvinceID(Game.oR.nextInt(Game.getCiv(i).getNumOfProvinces()));
                    final ArmyGeneral armyGeneral = CivilizationGeneralsPool.getGeneral_Random(i);
                    if (nArmyRegiment.size() > 0) {
                        Game.getProvince(inProvinceID).addArmy(new ArmyDivision(i, inProvinceID, nArmyRegiment, armyGeneral));
                    }
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
    }
    
    public static void loadRandomScenario() {
        final int scenarioID = Game.oR.nextInt(Game.mapScenarios.SCENARIOS_SIZE);
        if (Game.scenarioID != scenarioID || Game_Calendar.TURN_ID != 1 || Game.reloadScenario || MainMenu.canContinue) {
            Game.scenarioID = scenarioID;
            Game.reloadScenario = false;
            Menu_LoadScenario.editorMode = false;
            Menu_LoadScenario.goToMenu = View.CLOUDS_MENU;
            Game.menuManager.setViewIDWithoutAnimation(View.LOAD_SCENARIO);
        }
        else {
            Game.mapScale.definedScale = MapScale.defScales.definedScale_Default;
            Game.mapScale.setCurrentScale(1.0f);
            NewGame.setRandomCiv();
            Game.menuManager.setViewIDWithoutAnimation(View.CLOUDS_MENU);
        }
        Game.menuManager.addToastGold(Game.lang.get(Game.mapScenarios.details.get(scenarioID).Name), Images.dice);
    }
    
    static {
        MapScenarios.scenarioEditorDetails = new Details();
        MapScenarios.DEFAULT_VALUE = -2;
    }
    
    public static class Details
    {
        public String Name;
        public String Author;
        public int Civs;
        public int Age;
        public int Day;
        public int Month;
        public int Year;
        public boolean Campaign;
        public int CivDefault_Gold;
        public int CivDefault_GoldRandom;
        public int CivDefault_Legacy;
        public int CivDefault_LegacyRandom;
        public int CivDefault_ManpowerPercentage;
        public int CivDefault_Technology;
        public int ProvinceDefault_Population;
        public int ProvinceDefault_Economy;
        public int ProvinceDefault_TaxEfficiency;
        public int ProvinceDefault_Manpower;
        public int HoursPerTurn;
        
        public Details() {
            this.Campaign = false;
            this.CivDefault_Gold = 100;
            this.CivDefault_GoldRandom = 50;
            this.CivDefault_Legacy = 100;
            this.CivDefault_LegacyRandom = 50;
            this.CivDefault_ManpowerPercentage = 60;
            this.CivDefault_Technology = -1;
            this.ProvinceDefault_Population = 200000;
            this.ProvinceDefault_Economy = 10;
            this.ProvinceDefault_TaxEfficiency = 10;
            this.ProvinceDefault_Manpower = 3;
            this.HoursPerTurn = 24;
        }
    }
    
    public static class ScenarioCivData
    {
        public String CivTAG;
        public int CivID;
        public int PCID;
        public int CPID;
        public int Gold;
        public int Legacy;
        public int TechnologyID;
        public int Population;
        public int Economy;
        public int TaxEff;
        public int Manpower;
        public int CCL;
        public int MAL;
        public int MAGL;
        public int SCL;
        public int NRL;
        public int Nukes;
        public int v;
        
        public ScenarioCivData() {
            this.Gold = MapScenarios.DEFAULT_VALUE;
            this.Legacy = MapScenarios.DEFAULT_VALUE;
            this.TechnologyID = MapScenarios.DEFAULT_VALUE;
            this.Population = 0;
            this.Economy = 0;
            this.TaxEff = 0;
            this.Manpower = 0;
            this.CCL = 0;
            this.MAL = 0;
            this.MAGL = 0;
            this.SCL = 0;
            this.NRL = 0;
            this.Nukes = 0;
            this.v = 0;
        }
    }
    
    public static class ScenarioCivDataSerializer implements Json.Serializer<ScenarioCivData>
    {
        public void write(final Json json, final ScenarioCivData data, final Class knownType) {
            json.writeObjectStart();
            json.writeValue("CivTAG", (Object)data.CivTAG);
            json.writeValue("CivID", (Object)data.CivID);
            json.writeValue("PCID", (Object)data.PCID);
            json.writeValue("CPID", (Object)data.CPID);
            if (data.Gold != MapScenarios.DEFAULT_VALUE) {
                json.writeValue("Gold", (Object)data.Gold);
            }
            if (data.Legacy != MapScenarios.DEFAULT_VALUE) {
                json.writeValue("Legacy", (Object)data.Legacy);
            }
            if (data.TechnologyID != MapScenarios.DEFAULT_VALUE) {
                json.writeValue("TechnologyID", (Object)data.TechnologyID);
            }
            if (data.Population != 0) {
                json.writeValue("Population", (Object)data.Population);
            }
            if (data.Economy != 0) {
                json.writeValue("Economy", (Object)data.Economy);
            }
            if (data.TaxEff != 0) {
                json.writeValue("TaxEff", (Object)data.TaxEff);
            }
            if (data.Manpower != 0) {
                json.writeValue("Manpower", (Object)data.Manpower);
            }
            if (data.CCL != 0) {
                json.writeValue("CCL", (Object)data.CCL);
            }
            if (data.MAL != 0) {
                json.writeValue("MAL", (Object)data.MAL);
            }
            if (data.MAGL != 0) {
                json.writeValue("MAGL", (Object)data.MAGL);
            }
            if (data.SCL != 0) {
                json.writeValue("SCL", (Object)data.SCL);
            }
            if (data.NRL != 0) {
                json.writeValue("NRL", (Object)data.NRL);
            }
            if (data.Nukes != 0) {
                json.writeValue("Nukes", (Object)data.Nukes);
            }
            if (data.v != 0) {
                json.writeValue("v", (Object)data.v);
            }
            json.writeObjectEnd();
        }
        
        public ScenarioCivData read(final Json json, final JsonValue jsonData, final Class type) {
            final ScenarioCivData out = new ScenarioCivData();
            return out;
        }
    }
    
    public static class ScenarioCivData_Provinces
    {
        public int[] Provinces;
    }
    
    public static class ScenarioCoresData
    {
        public int id;
        public List<Integer> Cores;
    }
    
    public static class ScenarioReligionData
    {
        public int id;
        public int rel;
    }
}
