// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.SaveLoad;

import aoc.kingdoms.lukasz.menu.View;
import aoc.kingdoms.lukasz.map.WondersManager;
import java.util.Iterator;
import aoc.kingdoms.lukasz.jakowski.CFG;
import com.badlogic.gdx.utils.JsonValue;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.Gdx;
import aoc.kingdoms.lukasz.jakowski.Game;
import java.util.ArrayList;

public class ExportData
{
    public static final void Save_Save_ProvinceData_3M() {
        ArrayList<Save_ProvinceData_3M> tSaveDetails = new ArrayList<Save_ProvinceData_3M>();
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            if (!Game.getProvince(i).getSeaProvince() && Game.getProvince(i).getCitiesSize() > 0) {
                final Save_ProvinceData_3M tData = new Save_ProvinceData_3M();
                tData.iX = Game.getProvince(i).getCity(0).getPosX() / Game.mapBG.iMapScale;
                tData.iY = Game.getProvince(i).getCity(0).getPosY() / Game.mapBG.iMapScale;
                tData.terrainID = Game.getProvince(i).getTerrainID();
                tData.resourceID = Game.getProvince(i).getResourceID();
                tData.baseDevelopment = Game.getProvince(i).BaseDevelopment;
                tSaveDetails.add(tData);
            }
        }
        final Json json = SaveManager.getJson();
        final FileHandle file = Gdx.files.local("P3M.json");
        file.writeString(json.toJson((Object)tSaveDetails), false);
        tSaveDetails.clear();
        tSaveDetails = null;
    }
    
    public static final void loadSave_ProvinceData_3M() {
        try {
            final FileHandle fileList = FileManager.loadFile("P3M.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                Save_ProvinceData_3M tempData = (Save_ProvinceData_3M)json.readValue((Class)Save_ProvinceData_3M.class, jValue);
                final int provID = Game.setProvinceID_Point(tempData.iX * Game.mapBG.iMapScale, tempData.iY * Game.mapBG.iMapScale);
                if (provID >= 0 && !Game.getProvince(provID).getSeaProvince()) {
                    if (Game.getProvince(provID).getResourceID() >= 0) {
                        if (Game.oR.nextInt(100) < 25) {
                            Game.getProvince(provID).setTerrainID(tempData.terrainID);
                            Game.getProvince(provID).setResourceID(tempData.resourceID);
                            Game.getProvince(provID).BaseDevelopment = tempData.baseDevelopment;
                        }
                    }
                    else {
                        Game.getProvince(provID).setTerrainID(tempData.terrainID);
                        Game.getProvince(provID).setResourceID(tempData.resourceID);
                        Game.getProvince(provID).BaseDevelopment = tempData.baseDevelopment;
                    }
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
    
    public static final void Save_Save_ProvinceData_Images() {
        ArrayList<Save_ProvinceData_Images> tSaveDetails = new ArrayList<Save_ProvinceData_Images>();
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            if (!Game.getProvince(i).getSeaProvince() && Game.getProvince(i).getCitiesSize() > 0 && FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "provinces/" + i + ".png").exists()) {
                final Save_ProvinceData_Images tData = new Save_ProvinceData_Images();
                tData.iX = Game.getProvince(i).getCity(0).getPosX() / Game.mapBG.iMapScale;
                tData.iY = Game.getProvince(i).getCity(0).getPosY() / Game.mapBG.iMapScale;
                tData.imgID = i;
                tSaveDetails.add(tData);
            }
        }
        final Json json = SaveManager.getJson();
        final FileHandle file = Gdx.files.local("P_IMG.json");
        file.writeString(json.toJson((Object)tSaveDetails), false);
        tSaveDetails.clear();
        tSaveDetails = null;
    }
    
    public static final void loadSave_ProvinceData_Images() {
        try {
            final FileHandle fileList = FileManager.loadFile("P_IMG.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                Save_ProvinceData_Images tempData = (Save_ProvinceData_Images)json.readValue((Class)Save_ProvinceData_Images.class, jValue);
                final int provID = Game.setProvinceID_Point(tempData.iX * Game.mapBG.iMapScale, tempData.iY * Game.mapBG.iMapScale);
                if (provID >= 0 && !Game.getProvince(provID).getSeaProvince()) {
                    CFG.LOG("" + tempData.imgID + ".png -----> " + provID);
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
    
    public static final void Save_Save_ProvinceData_Wonders() {
        ArrayList<Save_ProvinceData_Images> tSaveDetails = new ArrayList<Save_ProvinceData_Images>();
        for (int i = 0; i < WondersManager.wondersSize; ++i) {
            final Save_ProvinceData_Images tData = new Save_ProvinceData_Images();
            tData.iX = Game.getProvince(WondersManager.wonders.get(i).ProvinceID).getCity(0).getPosX() / Game.mapBG.iMapScale;
            tData.iY = Game.getProvince(WondersManager.wonders.get(i).ProvinceID).getCity(0).getPosY() / Game.mapBG.iMapScale;
            tData.imgID = WondersManager.wonders.get(i).ProvinceID;
            tSaveDetails.add(tData);
        }
        final Json json = SaveManager.getJson();
        final FileHandle file = Gdx.files.local("P_WOND.json");
        file.writeString(json.toJson((Object)tSaveDetails), false);
        tSaveDetails.clear();
        tSaveDetails = null;
    }
    
    public static final void loadSave_ProvinceData_Wonders() {
        try {
            final FileHandle fileList = FileManager.loadFile("P_WOND.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                Save_ProvinceData_Images tempData = (Save_ProvinceData_Images)json.readValue((Class)Save_ProvinceData_Images.class, jValue);
                final int provID = Game.setProvinceID_Point(tempData.iX * Game.mapBG.iMapScale, tempData.iY * Game.mapBG.iMapScale);
                if (provID >= 0 && !Game.getProvince(provID).getSeaProvince()) {
                    CFG.LOG("Old prov: " + tempData.imgID + " -----> New: " + provID);
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
    
    public static final void Save_Save_ProvinceData_ScenarioCivs() {
        ArrayList<Save_ProvinceData_ScenarioCivs> tSaveDetails = new ArrayList<Save_ProvinceData_ScenarioCivs>();
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).getNumOfProvinces() > 0 && Game.getProvince(Game.getCiv(i).getCapitalProvinceID()).getCitiesSize() > 0) {
                final Save_ProvinceData_ScenarioCivs tData = new Save_ProvinceData_ScenarioCivs();
                tData.iX = Game.getProvince(Game.getCiv(i).getCapitalProvinceID()).getCity(0).getPosX() / Game.mapBG.iMapScale;
                tData.iY = Game.getProvince(Game.getCiv(i).getCapitalProvinceID()).getCity(0).getPosY() / Game.mapBG.iMapScale;
                tData.sTag = Game.getCiv(i).getCivTag();
                tData.numOfProvinces = Game.getCiv(i).getNumOfProvinces();
                tSaveDetails.add(tData);
            }
        }
        final Json json = SaveManager.getJson();
        final FileHandle file = Gdx.files.local("P_SCEN.json");
        file.writeString(json.toJson((Object)tSaveDetails), false);
        tSaveDetails.clear();
        tSaveDetails = null;
    }
    
    public static final void Save_Save_ProvinceData_ScenarioCivsAssign() {
        ArrayList<Save_ProvinceData_ScenarioCivs> tSaveDetails = new ArrayList<Save_ProvinceData_ScenarioCivs>();
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            if (Game.getProvince(i).getCivID() > 0 && Game.getProvince(i).getCitiesSize() > 0) {
                final Save_ProvinceData_ScenarioCivs tData = new Save_ProvinceData_ScenarioCivs();
                tData.iX = Game.getProvince(i).getCity(0).getPosX() / Game.mapBG.iMapScale;
                tData.iY = Game.getProvince(i).getCity(0).getPosY() / Game.mapBG.iMapScale;
                tData.sTag = Game.getCiv(Game.getProvince(i).getCivID()).getCivTag();
                tData.numOfProvinces = Game.getCiv(Game.getProvince(i).getCivID()).getNumOfProvinces();
                tSaveDetails.add(tData);
            }
        }
        final Json json = SaveManager.getJson();
        final FileHandle file = Gdx.files.local("P_SCEN_ASSI.json");
        file.writeString(json.toJson((Object)tSaveDetails), false);
        tSaveDetails.clear();
        tSaveDetails = null;
    }
    
    public static final void loadSave_ProvinceData_ScenarioCivs() {
        try {
            final FileHandle fileList = FileManager.loadFile("P_SCEN.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            Game.mapScenarios.createScenario();
            Game.mapBG.requestToDisposeMinimap = true;
            CFG.iCreateScenario_AssignProvinces_Civ = 0;
            Game.mapScenarios.loadReligions_JustBuild(true);
            Game.mapScenarios.scenarioEditor_isCampaign = false;
            Game.menuManager.setViewID(View.SCENARIO_WASTELAND_CONTINENTS);
            for (final JsonValue jValue : tempArrayData) {
                Save_ProvinceData_ScenarioCivs tempData = (Save_ProvinceData_ScenarioCivs)json.readValue((Class)Save_ProvinceData_ScenarioCivs.class, jValue);
                final int provID = Game.setProvinceID_Point(tempData.iX * Game.mapBG.iMapScale, tempData.iY * Game.mapBG.iMapScale);
                if (Game.getProvince(provID).getCivID() > 0) {
                    CFG.LOG("NOT ADDED: " + Game.lang.getCiv(tempData.sTag));
                }
                else {
                    Game.addCivilization(tempData.sTag, provID, true, true, false, true, false);
                }
                tempData = null;
            }
            Game.iCivsSize = Game.lCivs.size();
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void loadSave_ProvinceData_ScenarioCivs_Assign() {
        try {
            final FileHandle fileList = FileManager.loadFile("P_SCEN_ASSI.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                Save_ProvinceData_ScenarioCivs tempData = (Save_ProvinceData_ScenarioCivs)json.readValue((Class)Save_ProvinceData_ScenarioCivs.class, jValue);
                final int provID = Game.setProvinceID_Point(tempData.iX * Game.mapBG.iMapScale, tempData.iY * Game.mapBG.iMapScale);
                if (provID >= 0 && Game.getProvince(provID).getCivID() <= 0) {
                    final int civID = Game.getCivID(tempData.sTag);
                    if (civID > 0) {
                        Game.getProvince(provID).setCivID_RemoveOldAddNewToCiv(civID);
                    }
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
    
    public static class Save_ProvinceData_3M
    {
        public int iX;
        public int iY;
        public int terrainID;
        public int resourceID;
        public float baseDevelopment;
    }
    
    public static class Save_ProvinceData_Images
    {
        public int iX;
        public int iY;
        public int imgID;
    }
    
    public static class Save_ProvinceData_ScenarioCivs
    {
        public int iX;
        public int iY;
        public String sTag;
        public int numOfProvinces;
    }
}
