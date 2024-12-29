// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.map;

import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import com.badlogic.gdx.graphics.Color;
import aoc.kingdoms.lukasz.map.technology.TechnologyTree;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoc.kingdoms.lukasz.jakowski.Game;
import com.badlogic.gdx.files.FileHandle;
import java.util.Iterator;
import com.codedisaster.steamworks.SteamUGC;
import com.badlogic.gdx.Gdx;
import aoc.kingdoms.lukasz.jakowski.Steam.SteamManager;
import aoc.kingdoms.lukasz.jakowski.CFG;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import com.badlogic.gdx.utils.Json;
import java.util.List;

public class Map
{
    public int iActiveMapID;
    public List<Map_Data> lMaps;
    public static DrawProvincesFlags drawProvincesFlags;
    
    public Map() {
        this.iActiveMapID = 0;
        Config data = new Config();
        final Json json = new Json();
        json.setElementType((Class)Config.class, "Map", (Class)Maps.class);
        data = (Config)json.fromJson((Class)Config.class, FileManager.loadFile("map/Maps.json").reader("UTF8"));
        this.lMaps = new ArrayList<Map_Data>();
        for (final Object obj : data.Map) {
            final Maps tempMapFolder = (Maps)obj;
            if (FileManager.loadFile("map/" + tempMapFolder.Folder + "/" + "Config.json").exists()) {
                this.lMaps.add(new Map_Data(tempMapFolder.Folder));
            }
        }
        if (CFG.isDesktop()) {
            for (int i = 0; i < SteamManager.modsFoldersSize; ++i) {
                FileHandle[] files;
                if (FileManager.IS_MAC) {
                    files = Gdx.files.external(SteamManager.modsFolders.get(i) + "map/").list();
                }
                else {
                    files = Gdx.files.internal(SteamManager.modsFolders.get(i) + "map/").list();
                }
                for (final FileHandle file : files) {
                    boolean addMap = true;
                    if (file.name().indexOf("jar") >= 0 || file.name().indexOf("txt") >= 0 || file.name().indexOf("json") >= 0) {
                        addMap = false;
                    }
                    if (addMap) {
                        for (int a = 0; a < this.lMaps.size(); ++a) {
                            if (this.lMaps.get(a).Folder.equals(file.name())) {
                                addMap = false;
                                break;
                            }
                        }
                    }
                    if (addMap && FileManager.loadFile("map/" + file.name() + "/" + "Config.json").exists()) {
                        this.lMaps.add(new Map_Data(file.name()));
                    }
                }
            }
            for (int i = 0; i < SteamManager.itemsInstalledSize; ++i) {
                final FileHandle[] list;
                final FileHandle[] files = list = Gdx.files.absolute(SteamManager.itemsInstalled.get(i).getFolder() + "/" + "map/").list();
                for (final FileHandle file : list) {
                    boolean addMap = true;
                    if (file.name().indexOf("jar") >= 0 || file.name().indexOf("txt") >= 0 || file.name().indexOf("json") >= 0) {
                        addMap = false;
                    }
                    if (addMap) {
                        for (int a = 0; a < this.lMaps.size(); ++a) {
                            if (this.lMaps.get(a).Folder.equals(file.name())) {
                                addMap = false;
                                break;
                            }
                        }
                    }
                    if (addMap && FileManager.loadFile("map/" + file.name() + "/" + "Config.json").exists()) {
                        this.lMaps.add(new Map_Data(file.name()));
                    }
                }
            }
        }
        this.setActiveMapID(this.iActiveMapID);
    }
    
    public final void updateWorldMap() {
        Game.mapBG.updateWorldMap();
        Game.mapCoords.updateWorldMap();
    }
    
    public final void update() {
        Game.mapScale.update();
        Game.mapScroll.update();
        Game.mapEdgeMove.updateMoveMap();
        Game.mapCoords.updateMapPosition();
        Game.updateInView_CordsXY();
    }
    
    public final void drawMap(final SpriteBatch oSB) {
        if (Game.mapBG.requestToDisposeMinimap) {
            Game.mapBG.disposeMinimapOfCivilizations_Real();
        }
        Game.mapBG.drawMinimapTexture_Generate(oSB);
        Game.mapBG.drawMap(oSB, Game.mapCoords.getPosX(), Game.mapCoords.getPosY());
    }
    
    public final void drawMapBorder(final SpriteBatch oSB) {
        Game.mapBG.drawMapBorder(oSB, Game.mapCoords.getPosX(), Game.mapCoords.getPosY());
    }
    
    public final String getFile_ActiveMap_Path() {
        return this.lMaps.get(this.iActiveMapID).Folder + "/";
    }
    
    public final String getFile_Map_Path(final int nMapID) {
        return this.lMaps.get(nMapID).Folder + "/";
    }
    
    public final boolean isWorldMap(final int i) {
        return this.lMaps.get(i).mapData.WorldMap;
    }
    
    public final int getActiveMapID() {
        return this.iActiveMapID;
    }
    
    public final void setActiveMapID(final int nActiveMapID) {
        if (this.iActiveMapID != nActiveMapID) {
            this.iActiveMapID = nActiveMapID;
            this.updateWorldMap();
        }
        Game.DRAW_CITIES_MIN_SCALE = this.lMaps.get(this.iActiveMapID).mapData.DRAW_CITIES_MIN_SCALE;
        Game.DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE = this.lMaps.get(this.iActiveMapID).mapData.DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE;
        Game.DRAW_INNER_BORDERS = this.lMaps.get(this.iActiveMapID).mapData.DRAW_INNER_BORDERS;
        Game.DRAW_OCCUPIED_PROVINCES_MIN_SCALE = this.lMaps.get(this.iActiveMapID).mapData.DRAW_OCCUPIED_PROVINCES_MIN_SCALE;
        Game.DRAW_ARMY_MIN_SCALE = this.lMaps.get(this.iActiveMapID).mapData.DRAW_ARMY_MIN_SCALE;
        Game.DRAW_OCCUPIED_SCALE = this.lMaps.get(this.iActiveMapID).mapData.DRAW_OCCUPIED_SCALE;
        TechnologyTree.MapResearchCost = this.lMaps.get(this.iActiveMapID).mapData.ResearchCost;
    }
    
    public final Map_Data getActiveMap_MapData() {
        return this.lMaps.get(this.getActiveMapID());
    }
    
    public final boolean getMapWorldMap(final int i) {
        return this.lMaps.get(i).mapData.WorldMap;
    }
    
    public void dispose() {
    }
    
    public static void updateDrawProvincesFlags() {
        if (Game.settingsManager.SETTINGS_PROVINCE_FLAGS == 0) {
            Map.drawProvincesFlags = new DrawProvincesFlags() {
                @Override
                public void drawCity_CivFlag(final SpriteBatch oSB, final int nProvinceID, final float nScale, final float fAlpha, final int nPosX, final int nPosY) {
                }
            };
        }
        else if (Game.settingsManager.SETTINGS_PROVINCE_FLAGS == 1) {
            Map.drawProvincesFlags = new DrawProvincesFlags() {
                @Override
                public void drawCity_CivFlag(final SpriteBatch oSB, final int nProvinceID, final float nScale, final float fAlpha, final int nPosX, final int nPosY) {
                    Map.drawCityName_Capital_CivFlag_Low(oSB, nProvinceID, nScale, fAlpha, nPosX, nPosY);
                }
            };
        }
        else {
            Map.drawProvincesFlags = new DrawProvincesFlags() {
                @Override
                public void drawCity_CivFlag(final SpriteBatch oSB, final int nProvinceID, final float nScale, final float fAlpha, final int nPosX, final int nPosY) {
                    Map.drawCityName_Capital_CivFlag(oSB, nProvinceID, nScale, fAlpha, nPosX, nPosY);
                }
            };
        }
    }
    
    public static final void drawCityName_Capital_CivFlag(final SpriteBatch oSB, final int nProvinceID, final float nScale, final float fAlpha, int nPosX, final int nPosY) {
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.9f * fAlpha));
        oSB.setShader(Renderer.shaderAlpha);
        Game.getCiv(Game.getProvince(nProvinceID).getCivID()).getFlag().getTexture().bind(1);
        Gdx.gl.glActiveTexture(33984);
        if (Game.getCiv(Game.getProvince(nProvinceID).getCivID()).iCivRankID == 4) {
            nPosX -= ImageManager.getImage(Images.flagCapitalOver_l).getWidth() / 2;
            ImageManager.getImage(Images.flagCapitalMask_l).draw(oSB, nPosX, nPosY);
            oSB.flush();
            oSB.setShader(Renderer.shaderDefault);
            ImageManager.getImage(Images.flagCapitalOver_l).draw(oSB, nPosX, nPosY);
        }
        else if (Game.getCiv(Game.getProvince(nProvinceID).getCivID()).iCivRankID > 1) {
            nPosX -= ImageManager.getImage(Images.flagCapitalOver).getWidth() / 2;
            ImageManager.getImage(Images.flagCapitalMask).draw(oSB, nPosX, nPosY);
            oSB.flush();
            oSB.setShader(Renderer.shaderDefault);
            ImageManager.getImage(Images.flagCapitalOver).draw(oSB, nPosX, nPosY);
        }
        else {
            nPosX -= ImageManager.getImage(Images.flagCapitalOver_s).getWidth() / 2;
            ImageManager.getImage(Images.flagCapitalMask_s).draw(oSB, nPosX, nPosY);
            oSB.flush();
            oSB.setShader(Renderer.shaderDefault);
            ImageManager.getImage(Images.flagCapitalOver_s).draw(oSB, nPosX, nPosY);
        }
    }
    
    public static final void drawCityName_Capital_CivFlag_Low(final SpriteBatch oSB, final int nProvinceID, final float nScale, final float fAlpha, int nPosX, final int nPosY) {
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.9f * fAlpha));
        if (Game.getCiv(Game.getProvince(nProvinceID).getCivID()).iCivRankID == 4) {
            nPosX -= ImageManager.getImage(Images.flagCapitalOver_l_Low).getWidth() / 2;
            Game.getCiv(Game.getProvince(nProvinceID).getCivID()).getFlag().draw(oSB, nPosX, nPosY, ImageManager.getImage(Images.flagCapitalOver_l_Low).getWidth(), ImageManager.getImage(Images.flagCapitalOver_l_Low).getHeight());
            ImageManager.getImage(Images.flagCapitalOver_l_Low).draw(oSB, nPosX, nPosY);
        }
        else if (Game.getCiv(Game.getProvince(nProvinceID).getCivID()).iCivRankID > 1) {
            nPosX -= ImageManager.getImage(Images.flagCapitalOver_Low).getWidth() / 2;
            Game.getCiv(Game.getProvince(nProvinceID).getCivID()).getFlag().draw(oSB, nPosX, nPosY, ImageManager.getImage(Images.flagCapitalOver_Low).getWidth(), ImageManager.getImage(Images.flagCapitalOver_Low).getHeight());
            ImageManager.getImage(Images.flagCapitalOver_Low).draw(oSB, nPosX, nPosY);
        }
        else {
            nPosX -= ImageManager.getImage(Images.flagCapitalOver_s_Low).getWidth() / 2;
            Game.getCiv(Game.getProvince(nProvinceID).getCivID()).getFlag().draw(oSB, nPosX, nPosY, ImageManager.getImage(Images.flagCapitalOver_s_Low).getWidth(), ImageManager.getImage(Images.flagCapitalOver_s_Low).getHeight());
            ImageManager.getImage(Images.flagCapitalOver_s_Low).draw(oSB, nPosX, nPosY);
        }
    }
    
    static {
        Map.drawProvincesFlags = new DrawProvincesFlags() {
            @Override
            public void drawCity_CivFlag(final SpriteBatch oSB, final int nProvinceID, final float nScale, final float fAlpha, final int nPosX, final int nPosY) {
            }
        };
    }
    
    public static class Config
    {
        private String Age_of_History;
        private ArrayList Map;
    }
    
    public static class Maps
    {
        private String Folder;
    }
    
    public interface DrawProvincesFlags
    {
        void drawCity_CivFlag(final SpriteBatch p0, final int p1, final float p2, final float p3, final int p4, final int p5);
    }
}
