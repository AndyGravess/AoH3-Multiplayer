// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.SaveLoad;

import java.util.List;
import aoc.kingdoms.lukasz.map.FormableCivManager;
import aoc.kingdoms.lukasz.map.province.ProvinceNameData;
import aoc.kingdoms.lukasz.map.province.ProvinceNamesManager;
import aoc.kingdoms.lukasz.map.map.MapScenarios;
import aoc.kingdoms.lukasz.map.province.ProvinceBorder;
import aoc.kingdoms.lukasz.map.diplomacy.Diplomacy;
import java.util.Iterator;
import java.util.Map;
import com.badlogic.gdx.files.FileHandle;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioSettings;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.map.army.ArmyRegiment;
import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.ArrayList;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.Json;

public class SaveManager
{
    public static final int iNeighboringProvincesPerFile = 2000;
    
    public static final Json getJson() {
        final Json json = new Json();
        json.setTypeName((String)null);
        json.setUsePrototypes(false);
        json.setIgnoreUnknownFields(true);
        json.setOutputType(JsonWriter.OutputType.javascript);
        return json;
    }
    
    public static final void saveScenarioArmies() {
        final ArrayList<ScenarioData_Army> tempDetails = new ArrayList<ScenarioData_Army>();
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            if (Game.getProvince(i).getCivID() > 0 && Game.getProvince(i).getArmySize() > 0) {
                try {
                    final ScenarioData_Army tArmy = new ScenarioData_Army();
                    tArmy.ProvinceID = i;
                    for (int j = 0; j < Game.getProvince(i).getArmy(0).iArmyRegimentSize; ++j) {
                        tArmy.UnitTypeID.add(Game.getProvince(i).getArmy(0).lArmyRegiment.get(j).uID);
                        tArmy.ArmyID.add(Game.getProvince(i).getArmy(0).lArmyRegiment.get(j).aID);
                    }
                    if (tArmy.UnitTypeID.size() > 0) {
                        tempDetails.add(tArmy);
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
        }
        final Json json = getJson();
        final FileHandle file = FileManager.getSaveType("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + ScenarioSettings.sTag + "/" + "Armies.json");
        file.writeString(json.prettyPrint((Object)tempDetails), false);
    }
    
    public static final void saveScenarioBuildings() {
        final ArrayList<ScenarioData_Buildings> tempDetails = new ArrayList<ScenarioData_Buildings>();
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            if (Game.getProvince(i).getCivID() > 0 && Game.getProvince(i).iBuildingsSize > 0) {
                try {
                    final ScenarioData_Buildings tData = new ScenarioData_Buildings();
                    tData.pid = i;
                    for (int j = 0; j < Game.getProvince(i).iBuildingsSize; ++j) {
                        tData.b0.add(Game.getProvince(i).getBuildings(j).getBuilding());
                        tData.b1.add(Game.getProvince(i).getBuildings(j).getBuildingID());
                    }
                    if (tData.b0.size() > 0) {
                        tempDetails.add(tData);
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
        }
        final Json json = getJson();
        final FileHandle file = FileManager.getSaveType("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + ScenarioSettings.sTag + "/" + "Buildings.json");
        file.writeString(json.prettyPrint((Object)tempDetails), false);
    }
    
    public static final void saveScenarioRelations() {
        ArrayList<ScenarioData_Relations> tData = new ArrayList<ScenarioData_Relations>();
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).diplomacy.relation.size() > 0) {
                final ScenarioData_Relations nData = new ScenarioData_Relations();
                nData.c = i;
                for (final Map.Entry<Integer, Float> entry : Game.getCiv(i).diplomacy.relation.entrySet()) {
                    nData.w.add(entry.getKey());
                    nData.r.add(entry.getValue().intValue());
                }
                tData.add(nData);
            }
        }
        final Json json = getJson();
        final FileHandle file = FileManager.getSaveType("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + ScenarioSettings.sTag + "/" + "Relations.json");
        file.writeString(json.toJson((Object)tData), false);
        tData.clear();
        tData = null;
    }
    
    public static final void saveScenarioAlliances() {
        ArrayList<ScenarioData_Diplomacy> tData = new ArrayList<ScenarioData_Diplomacy>();
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).diplomacy.alliance.size() > 0) {
                final ScenarioData_Diplomacy nData = new ScenarioData_Diplomacy();
                nData.pid = i;
                for (final Diplomacy.DiplomacyData dData : Game.getCiv(i).diplomacy.alliance.values()) {
                    nData.w0.add(dData.iCivID);
                    nData.t0.add(dData.iTurnID);
                }
                tData.add(nData);
            }
        }
        final Json json = getJson();
        final FileHandle file = FileManager.getSaveType("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + ScenarioSettings.sTag + "/" + "Alliances.json");
        file.writeString(json.toJson((Object)tData), false);
        tData.clear();
        tData = null;
    }
    
    public static final void saveScenarioDefensive() {
        ArrayList<ScenarioData_Diplomacy> tData = new ArrayList<ScenarioData_Diplomacy>();
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).diplomacy.defensivePact.size() > 0) {
                final ScenarioData_Diplomacy nData = new ScenarioData_Diplomacy();
                nData.pid = i;
                for (final Diplomacy.DiplomacyData dData : Game.getCiv(i).diplomacy.defensivePact.values()) {
                    nData.w0.add(dData.iCivID);
                    nData.t0.add(dData.iTurnID);
                }
                tData.add(nData);
            }
        }
        final Json json = getJson();
        final FileHandle file = FileManager.getSaveType("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + ScenarioSettings.sTag + "/" + "Defensive.json");
        file.writeString(json.toJson((Object)tData), false);
        tData.clear();
        tData = null;
    }
    
    public static final void saveScenarioAlliancesSpecial() {
        final Json json = getJson();
        final FileHandle file = FileManager.getSaveType("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + ScenarioSettings.sTag + "/" + "AlliancesSpecial.json");
        file.writeString(json.toJson((Object)Game.alliancesSpecial), false);
    }
    
    public static final void saveScenarioTruces() {
        ArrayList<ScenarioData_Diplomacy> tData = new ArrayList<ScenarioData_Diplomacy>();
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).diplomacy.truce.size() > 0) {
                final ScenarioData_Diplomacy nData = new ScenarioData_Diplomacy();
                nData.pid = i;
                for (final Diplomacy.DiplomacyData dData : Game.getCiv(i).diplomacy.truce.values()) {
                    nData.w0.add(dData.iCivID);
                    nData.t0.add(dData.iTurnID);
                }
                tData.add(nData);
            }
        }
        final Json json = getJson();
        final FileHandle file = FileManager.getSaveType("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + ScenarioSettings.sTag + "/" + "Truces.json");
        file.writeString(json.toJson((Object)tData), false);
        tData.clear();
        tData = null;
    }
    
    public static final void saveScenarioNonAggression() {
        ArrayList<ScenarioData_Diplomacy> tData = new ArrayList<ScenarioData_Diplomacy>();
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).diplomacy.nonAggressionPact.size() > 0) {
                final ScenarioData_Diplomacy nData = new ScenarioData_Diplomacy();
                nData.pid = i;
                for (final Diplomacy.DiplomacyData dData : Game.getCiv(i).diplomacy.nonAggressionPact.values()) {
                    nData.w0.add(dData.iCivID);
                    nData.t0.add(dData.iTurnID);
                }
                tData.add(nData);
            }
        }
        final Json json = getJson();
        final FileHandle file = FileManager.getSaveType("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + ScenarioSettings.sTag + "/" + "NonAggression.json");
        file.writeString(json.toJson((Object)tData), false);
        tData.clear();
        tData = null;
    }
    
    public static final void saveScenarioMilitaryAccess() {
        ArrayList<ScenarioData_Diplomacy> tData = new ArrayList<ScenarioData_Diplomacy>();
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).diplomacy.militaryAccess.size() > 0) {
                final ScenarioData_Diplomacy nData = new ScenarioData_Diplomacy();
                nData.pid = i;
                for (final Diplomacy.DiplomacyData dData : Game.getCiv(i).diplomacy.militaryAccess.values()) {
                    nData.w0.add(dData.iCivID);
                    nData.t0.add(dData.iTurnID);
                }
                tData.add(nData);
            }
        }
        final Json json = getJson();
        final FileHandle file = FileManager.getSaveType("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + ScenarioSettings.sTag + "/" + "MilitaryAccess.json");
        file.writeString(json.toJson((Object)tData), false);
        tData.clear();
        tData = null;
    }
    
    public static final void saveScenarioGuarantee() {
        ArrayList<ScenarioData_Diplomacy> tData = new ArrayList<ScenarioData_Diplomacy>();
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).diplomacy.guarantee.size() > 0) {
                final ScenarioData_Diplomacy nData = new ScenarioData_Diplomacy();
                nData.pid = i;
                for (final Diplomacy.DiplomacyData dData : Game.getCiv(i).diplomacy.guarantee.values()) {
                    nData.w0.add(dData.iCivID);
                    nData.t0.add(dData.iTurnID);
                }
                tData.add(nData);
            }
        }
        final Json json = getJson();
        final FileHandle file = FileManager.getSaveType("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + ScenarioSettings.sTag + "/" + "Guarantee.json");
        file.writeString(json.toJson((Object)tData), false);
        tData.clear();
        tData = null;
    }
    
    public static final void saveProvinceDetails() {
        final ArrayList<LoadManager.ProvinceDetails> tempDetails = new ArrayList<LoadManager.ProvinceDetails>();
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            final LoadManager.ProvinceDetails provinceDetails = new LoadManager.ProvinceDetails();
            if (Game.getProvince(i).getLevelOfPort() >= -1) {
                if (Game.getProvince(i).getTerrainID() == 0) {
                    Game.getProvince(i).setTerrainID(1);
                }
                if (Game.getProvince(i).getContinent() == 0) {
                    Game.getProvince(i).setContinent(1);
                }
            }
            provinceDetails.pid = i;
            provinceDetails.co = Game.getProvince(i).getContinent();
            provinceDetails.re = Game.getProvince(i).getGeoRegion();
            provinceDetails.tr = Game.getProvince(i).getTerrainID();
            provinceDetails.lp = Math.min(Game.getProvince(i).getLevelOfPort(), -1);
            provinceDetails.gr = Game.getProvince(i).getGrowthRate();
            provinceDetails.bd = Game.getProvince(i).BaseDevelopment;
            provinceDetails.rs = Game.getProvince(i).getResourceID();
            provinceDetails.sx = Game.getProvince(i).getShiftX() / Game.mapBG.iMapScale;
            provinceDetails.sy = Game.getProvince(i).getShiftY() / Game.mapBG.iMapScale;
            tempDetails.add(provinceDetails);
        }
        final Json json = getJson();
        json.setElementType((Class)LoadManager.ConfigJson.class, "Data", (Class)LoadManager.Data_ProvincePoints.class);
        final FileHandle file = FileManager.getSaveType("map/" + Game.map.getFile_ActiveMap_Path() + "data/" + "ProvinceDetails.json");
        file.writeString(json.toJson((Object)tempDetails), false);
    }
    
    public static final void saveProvinceNeighboringProvinces() {
        final ArrayList<LoadManager.Data_ProvinceBorder> tempDetails = new ArrayList<LoadManager.Data_ProvinceBorder>();
        int tAdded = 0;
        int fileID = 0;
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            for (int j = 0; j < Game.getProvince(i).getProvinceBordersLandByLandSize(); ++j) {
                final LoadManager.Data_ProvinceBorder provinceNeighboringProvince = new LoadManager.Data_ProvinceBorder();
                provinceNeighboringProvince.pid = i;
                provinceNeighboringProvince.wp = Game.getProvince(i).getProvinceBordersLandByLand().get(j).withProvinceID;
                provinceNeighboringProvince.px = Game.getProvince(i).getProvinceBordersLandByLand().get(j).lPointsX;
                provinceNeighboringProvince.py = Game.getProvince(i).getProvinceBordersLandByLand().get(j).lPointsY;
                tempDetails.add(provinceNeighboringProvince);
                ++tAdded;
            }
            for (int j = 0; j < Game.getProvince(i).getProvinceBordersLandBySeaSize(); ++j) {
                final LoadManager.Data_ProvinceBorder provinceNeighboringProvince = new LoadManager.Data_ProvinceBorder();
                provinceNeighboringProvince.pid = i;
                provinceNeighboringProvince.wp = Game.getProvince(i).getProvinceBordersLandBySea().get(j).withProvinceID;
                provinceNeighboringProvince.px = Game.getProvince(i).getProvinceBordersLandBySea().get(j).lPointsX;
                provinceNeighboringProvince.py = Game.getProvince(i).getProvinceBordersLandBySea().get(j).lPointsY;
                tempDetails.add(provinceNeighboringProvince);
                ++tAdded;
            }
            for (int j = 0; j < Game.getProvince(i).getProvinceBordersSeaBySeaSize(); ++j) {
                final LoadManager.Data_ProvinceBorder provinceNeighboringProvince = new LoadManager.Data_ProvinceBorder();
                provinceNeighboringProvince.pid = i;
                provinceNeighboringProvince.wp = Game.getProvince(i).getProvinceBordersSeaBySea().get(j).withProvinceID;
                provinceNeighboringProvince.px = Game.getProvince(i).getProvinceBordersSeaBySea().get(j).lPointsX;
                provinceNeighboringProvince.py = Game.getProvince(i).getProvinceBordersSeaBySea().get(j).lPointsY;
                tempDetails.add(provinceNeighboringProvince);
                ++tAdded;
            }
            if (tAdded >= 2000) {
                final Json json = getJson();
                json.setElementType((Class)LoadManager.ConfigJson.class, "Data", (Class)LoadManager.Data_ProvinceBorder.class);
                final FileHandle file = FileManager.getSaveType("map/" + Game.map.getFile_ActiveMap_Path() + "data/" + "ProvinceNeighboringProvinces/ProvinceNeighboringProvinces" + ((fileID > 0) ? ("_" + fileID) : "") + ".json");
                file.writeString(json.toJson((Object)tempDetails), false);
                tempDetails.clear();
                tAdded = 0;
                ++fileID;
            }
        }
        if (tAdded > 0) {
            final Json json2 = getJson();
            json2.setElementType((Class)LoadManager.ConfigJson.class, "Data", (Class)LoadManager.Data_ProvinceBorder.class);
            final FileHandle file2 = FileManager.getSaveType("map/" + Game.map.getFile_ActiveMap_Path() + "data/" + "ProvinceNeighboringProvinces/ProvinceNeighboringProvinces" + ((fileID > 0) ? ("_" + fileID) : "") + ".json");
            file2.writeString(json2.toJson((Object)tempDetails), false);
            tempDetails.clear();
        }
    }
    
    public static final void saveScenarioDetails() {
        try {
            final ArrayList<MapScenarios.ScenarioCivData> tempDetails = new ArrayList<MapScenarios.ScenarioCivData>();
            for (int i = 1; i < Game.getCivsSize(); ++i) {
                final MapScenarios.ScenarioCivData tCivID = Game.getCiv(i).scenarioEditorData;
                tCivID.CivTAG = Game.getCiv(i).getCivTag();
                tCivID.CivID = Game.getCiv(i).getCivID();
                tCivID.CPID = Game.getCiv(i).getCapitalProvinceID();
                tCivID.PCID = Game.getCiv(i).getPuppetOfCivID();
                tempDetails.add(tCivID);
            }
            final Json json = getJson();
            json.setSerializer((Class)MapScenarios.ScenarioCivData.class, (Json.Serializer)new MapScenarios.ScenarioCivDataSerializer());
            final FileHandle file = FileManager.getSaveType("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + ScenarioSettings.sTag + "/" + "Data.json");
            file.writeString(json.prettyPrint((Object)tempDetails), false);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void saveScenarioDetailsProvinces() {
        try {
            final ArrayList<MapScenarios.ScenarioCivData_Provinces> tempDetails = new ArrayList<MapScenarios.ScenarioCivData_Provinces>();
            for (int i = 1; i < Game.getCivsSize(); ++i) {
                final MapScenarios.ScenarioCivData_Provinces tCivID = new MapScenarios.ScenarioCivData_Provinces();
                final int[] tempProvinces = new int[Game.getCiv(i).getNumOfProvinces()];
                for (int j = 0; j < Game.getCiv(i).getNumOfProvinces(); ++j) {
                    tempProvinces[j] = Game.getCiv(i).getProvinceID(j);
                }
                tCivID.Provinces = tempProvinces;
                tempDetails.add(tCivID);
            }
            final Json json = getJson();
            final FileHandle file = FileManager.getSaveType("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + ScenarioSettings.sTag + "/" + "DataProvinces.json");
            file.writeString(json.prettyPrint((Object)tempDetails), false);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void saveScenarioCores() {
        try {
            final ArrayList<MapScenarios.ScenarioCoresData> tempDetails = new ArrayList<MapScenarios.ScenarioCoresData>();
            for (int i = 0; i < Game.getProvincesSize(); ++i) {
                if (!Game.getProvince(i).getSeaProvince() && Game.getProvince(i).getWasteland() < 0 && Game.getProvince(i).getCivID() > 0) {
                    Game.getProvince(i).updateHaveACore();
                    if (!Game.getProvince(i).haveACore || Game.getProvince(i).iCoresSize > 1) {
                        final MapScenarios.ScenarioCoresData tCores = new MapScenarios.ScenarioCoresData();
                        tCores.id = i;
                        tCores.Cores = new ArrayList<Integer>();
                        if (Game.getProvince(i).iCoresSize == 0) {
                            tCores.Cores.add(0);
                        }
                        else {
                            for (int j = 0; j < Game.getProvince(i).iCoresSize; ++j) {
                                tCores.Cores.add(Game.getProvince(i).getCore(j));
                            }
                        }
                        if (tCores.Cores.size() > 0) {
                            tempDetails.add(tCores);
                        }
                    }
                }
            }
            final Json json = getJson();
            final FileHandle file = FileManager.getSaveType("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + ScenarioSettings.sTag + "/" + "Cores.json");
            file.writeString(json.prettyPrint((Object)tempDetails), false);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void saveScenarioReligion() {
        try {
            final ArrayList<MapScenarios.ScenarioReligionData> tempDetails = new ArrayList<MapScenarios.ScenarioReligionData>();
            for (int i = 0; i < Game.getProvincesSize(); ++i) {
                if (!Game.getProvince(i).getSeaProvince() && Game.getProvince(i).getWasteland() < 0 && Game.getCiv(Game.getProvince(i).getCivID()).getReligionID() != Game.mapScenarios.editorProvinceReligion.get(i) && Game.mapScenarios.editorProvinceReligion.get(i) >= 0) {
                    final MapScenarios.ScenarioReligionData tReligion = new MapScenarios.ScenarioReligionData();
                    tReligion.id = i;
                    tReligion.rel = Game.mapScenarios.editorProvinceReligion.get(i);
                    tempDetails.add(tReligion);
                }
            }
            final Json json = getJson();
            final FileHandle file = FileManager.getSaveType("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + ScenarioSettings.sTag + "/" + "Religions.json");
            file.writeString(json.prettyPrint((Object)tempDetails), false);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void saveProvinceNamesPoints() {
        final ArrayList<LoadManager.ProvinceNamesPoints> tempData = new ArrayList<LoadManager.ProvinceNamesPoints>();
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            final LoadManager.ProvinceNamesPoints provincePoints = new LoadManager.ProvinceNamesPoints();
            if (ProvinceNamesManager.provinceNames.get(i) != null) {
                provincePoints.pid = i;
                provincePoints.fX = ProvinceNamesManager.provinceNames.get(i).fX / Game.mapBG.iMapScale;
                provincePoints.fY = ProvinceNamesManager.provinceNames.get(i).fY / Game.mapBG.iMapScale;
                provincePoints.fX2 = ProvinceNamesManager.provinceNames.get(i).fX2 / Game.mapBG.iMapScale;
                provincePoints.fY2 = ProvinceNamesManager.provinceNames.get(i).fY2 / Game.mapBG.iMapScale;
                provincePoints.cx = ProvinceNamesManager.provinceNames.get(i).fCenterX / Game.mapBG.iMapScale;
                provincePoints.cy = ProvinceNamesManager.provinceNames.get(i).fCenterY / Game.mapBG.iMapScale;
            }
            else {
                provincePoints.pid = i;
                provincePoints.fX = (float)ProvinceNamesManager.NULL_INDICATOR;
                provincePoints.fY = (float)ProvinceNamesManager.NULL_INDICATOR;
                provincePoints.fX2 = (float)ProvinceNamesManager.NULL_INDICATOR;
                provincePoints.fY2 = (float)ProvinceNamesManager.NULL_INDICATOR;
                provincePoints.cx = (float)ProvinceNamesManager.NULL_INDICATOR;
                provincePoints.cy = (float)ProvinceNamesManager.NULL_INDICATOR;
            }
            tempData.add(provincePoints);
        }
        final Json json = getJson();
        json.setElementType((Class)LoadManager.ConfigJson.class, "Data", (Class)LoadManager.ProvinceNamesPoints.class);
        final FileHandle file = FileManager.getSaveType("map/" + Game.map.getFile_ActiveMap_Path() + "data/" + "ProvinceNamePoints.json");
        file.writeString(json.toJson((Object)tempData), false);
    }
    
    public static final void saveScenariosList() {
        String tList = "";
        if (FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "Scenarios.txt").exists()) {
            final FileHandle file2 = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "Scenarios.txt");
            final String tempTags = tList = file2.readString();
            final String[] tSplited = tempTags.split(";");
            boolean add = true;
            for (int i = 0, iSize = tSplited.length; i < iSize; ++i) {
                if (tSplited[i].equals(ScenarioSettings.sTag)) {
                    add = false;
                    break;
                }
            }
            if (add) {
                tList = tList + ScenarioSettings.sTag + ";";
            }
        }
        else {
            tList = tList + ScenarioSettings.sTag + ";";
        }
        final FileHandle fileSave = FileManager.getSaveType("map/" + Game.map.getFile_ActiveMap_Path() + "Scenarios.txt");
        fileSave.writeString(tList, false);
    }
    
    public static final void saveFormableCiv() {
        final Json json = getJson();
        json.setElementType((Class)FormableCivManager.ConfigJson.class, "Data", (Class)FormableCivManager.FormableCiv.class);
        final FileHandle file = FileManager.getSaveType("map/" + Game.map.getFile_ActiveMap_Path() + "formableCivs/" + "data/" + FormableCivManager.activeFormableCiv.FormableCivTag + ".txt");
        file.writeString(json.prettyPrint((Object)FormableCivManager.activeFormableCiv), false);
        String tList = "";
        if (FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "formableCivs/" + "AoH.txt").exists()) {
            final FileHandle file2 = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "formableCivs/" + "AoH.txt");
            final String tempTags = tList = file2.readString();
            final String[] tSplited = tempTags.split(";");
            boolean add = true;
            for (int i = 0, iSize = tSplited.length; i < iSize; ++i) {
                if (tSplited[i].equals(FormableCivManager.activeFormableCiv.FormableCivTag + ".txt")) {
                    add = false;
                    break;
                }
            }
            if (add) {
                tList = tList + FormableCivManager.activeFormableCiv.FormableCivTag + ".txt;";
            }
        }
        else {
            tList = tList + FormableCivManager.activeFormableCiv.FormableCivTag + ".txt;";
        }
        final FileHandle fileSave = FileManager.getSaveType("map/" + Game.map.getFile_ActiveMap_Path() + "formableCivs/" + "AoH.txt");
        fileSave.writeString(tList, false);
    }
    
    public static class ScenarioData_Army
    {
        public int ProvinceID;
        public List<Integer> UnitTypeID;
        public List<Integer> ArmyID;
        
        public ScenarioData_Army() {
            this.UnitTypeID = new ArrayList<Integer>();
            this.ArmyID = new ArrayList<Integer>();
        }
    }
    
    public static class ScenarioData_Buildings
    {
        public int pid;
        public List<Integer> b0;
        public List<Integer> b1;
        
        public ScenarioData_Buildings() {
            this.b0 = new ArrayList<Integer>();
            this.b1 = new ArrayList<Integer>();
        }
    }
    
    public static class ScenarioData_Relations
    {
        public int c;
        public List<Integer> w;
        public List<Integer> r;
        
        public ScenarioData_Relations() {
            this.w = new ArrayList<Integer>();
            this.r = new ArrayList<Integer>();
        }
    }
    
    public static class ScenarioData_Diplomacy
    {
        public int pid;
        public List<Integer> w0;
        public List<Integer> t0;
        
        public ScenarioData_Diplomacy() {
            this.w0 = new ArrayList<Integer>();
            this.t0 = new ArrayList<Integer>();
        }
    }
}
