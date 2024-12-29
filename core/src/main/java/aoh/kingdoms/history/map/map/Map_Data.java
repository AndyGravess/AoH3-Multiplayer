// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.map;

import com.badlogic.gdx.files.FileHandle;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import com.badlogic.gdx.utils.Json;
import aoc.kingdoms.lukasz.textures.Image;

public class Map_Data
{
    public String Folder;
    public Image Icon;
    public MapData mapData;
    
    public Map_Data(final String MAP_TAG) {
        this.mapData = new MapData();
        this.Folder = MAP_TAG;
        final Json json = new Json();
        final FileHandle file = FileManager.loadFile("map/" + MAP_TAG + "/" + (Game.highTextueSettings() ? "Config.json" : "ConfigLow.json"));
        this.mapData = (MapData)json.fromJson((Class)MapData.class, file);
        try {
            for (int i = 0; i < 3; ++i) {
                this.mapData.BackgroundColor[i] /= 255.0f;
                this.mapData.BackgroundColor_ZoomIn[i] /= 255.0f;
                this.mapData.BackgroundColor_ZoomOut[i] /= 255.0f;
                this.mapData.WastelandColor[i] /= 255.0f;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        this.Icon = new Image(ImageManager.loadTexture("map/" + MAP_TAG + "/" + "icon.png"));
    }
    
    public static class MapData
    {
        public String Name;
        public String Author;
        public int NumOfProvinces;
        public int DefaultMapScale;
        public int ExtraMapScale;
        public boolean WorldMap;
        public int BackgroundSize_X;
        public int BackgroundSize_Y;
        public float BackgroundScale;
        public boolean BackgroundZoomOut_Enable;
        public int BackgroundZoomOut_Size_X;
        public int BackgroundZoomOut_Size_Y;
        public float BackgroundZoomOut_Scale;
        public int BackgroundZoomOut_AnimationDuration;
        public float[] BackgroundColor;
        public float[] BackgroundColor_ZoomIn;
        public float[] BackgroundColor_ZoomOut;
        public float SeaOverAlpha;
        public float[] WastelandColor;
        public float DistanceKm;
        public float ResearchCost;
        public float UnitsRecruitCost;
        public float UnitsMaintenanceCost;
        public float BuildingsCost;
        public float BuildingsMaintenanceCost;
        public float BuildingsConstructionTime;
        public String DefaultScenario;
        public String Wiki;
        public float DRAW_CITIES_MIN_SCALE;
        public float DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE;
        public float DRAW_INNER_BORDERS;
        public float DRAW_OCCUPIED_PROVINCES_MIN_SCALE;
        public float DRAW_ARMY_MIN_SCALE;
        public float DRAW_OCCUPIED_SCALE;
        
        public MapData() {
            this.ResearchCost = 1.0f;
            this.UnitsRecruitCost = 1.0f;
            this.UnitsMaintenanceCost = 1.0f;
            this.BuildingsCost = 1.0f;
            this.BuildingsMaintenanceCost = 1.0f;
            this.BuildingsConstructionTime = 1.0f;
            this.DRAW_CITIES_MIN_SCALE = 1.3f;
            this.DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE = 0.6f;
            this.DRAW_INNER_BORDERS = 0.6f;
            this.DRAW_OCCUPIED_PROVINCES_MIN_SCALE = 0.15f;
            this.DRAW_ARMY_MIN_SCALE = 0.5f;
        }
    }
}
