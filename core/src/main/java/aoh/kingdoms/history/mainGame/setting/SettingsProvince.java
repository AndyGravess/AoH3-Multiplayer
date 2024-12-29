// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.setting;

import aoc.kingdoms.lukasz.map.civilization.CivilizationRegionsManager;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.map.province.ProvinceNamesManager;
import aoc.kingdoms.lukasz.map.map.Map;
import aoc.kingdoms.lukasz.map.province.ProvinceBorderManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import com.badlogic.gdx.files.FileHandle;
import aoc.kingdoms.lukasz.map.province.ProvinceBorder;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import com.badlogic.gdx.utils.Json;

public class SettingsProvince
{
    public static final boolean DOUBLE_BORDER_DEFAULT = true;
    public static final boolean CLOUDS_DEFAULT = true;
    public static final int MIN = 0;
    public static final int PROVINCE_BORDER_DEFAULT = 4;
    public static final int PROVINCE_FLAGS_DEFAULT = 2;
    public static final int MAX = 5;
    public static final int PROVINCE_NAMES_DEFAULT = 2;
    public static final int CIV_NAMES_DEFAULT = 2;
    public static final boolean PROVINCE_CITIES_DEFAULT = true;
    public static final float PROVINCE_ALPHA_DEFAULT = 0.3137255f;
    public static final float PROVINCE_ALPHA_WASTELAND_DEFAULT = 0.3f;
    public static final float CIV_NAMES_TEXT_ALPHA_DEFAULT = 0.5f;
    public static final float PROVINCE_NAMES_ALPHA_DEFAULT = 0.45f;
    public static final float PROVINCE_OCCUPIED_ALPHA_EXTRA_DEFAULT = 0.25f;
    public static ValuesProvinceBorder value;
    public static final int PROVINCE_NAMES_LOW = 1;
    
    public static final void init(final int settings) {
        final Json json = new Json();
        final FileHandle file = FileManager.loadFile(getFile(settings));
        SettingsProvince.value = (ValuesProvinceBorder)json.fromJson((Class)ValuesProvinceBorder.class, file);
        ProvinceBorder.updateDrawCivBorder();
    }
    
    private static String getFile(final int settings) {
        switch (settings) {
            case 0:
            case 1:
            case 2: {
                return "game//settings/ProvinceBorder_Low.json";
            }
            case 4: {
                return "game//settings/ProvinceBorder_High.json";
            }
            case 5: {
                return "game//settings/ProvinceBorder_VeryHigh.json";
            }
            default: {
                return "game//settings/ProvinceBorder_Medium.json";
            }
        }
    }
    
    public static final void updateSettingsProvinceBorder(final int change) {
        final boolean updateProvinces = Game.settingsManager.SETTINGS_PROVINCE_BORDER == 1;
        Game.settingsManager.SETTINGS_PROVINCE_BORDER = Math.max(0, Math.min(5, Game.settingsManager.SETTINGS_PROVINCE_BORDER + change));
        Game.addSimpleTask(new Game.SimpleTask("SettingsProvinceBorder", updateProvinces) {
            @Override
            public void update() {
                SettingsProvince.init(Game.settingsManager.SETTINGS_PROVINCE_BORDER);
                if (Game.settingsManager.SETTINGS_PROVINCE_BORDER == 1 || this.id == 1) {
                    ProvinceBorderManager.updateProvinceBorder();
                }
            }
        });
    }
    
    public static String getSettingsText() {
        String out = Game.lang.get("ProvinceBorder") + ": ";
        switch (Game.settingsManager.SETTINGS_PROVINCE_BORDER) {
            case 0: {
                out += Game.lang.get("Off");
                break;
            }
            case 1: {
                out += Game.lang.get("VeryLow");
                break;
            }
            case 2: {
                out += Game.lang.get("Low");
                break;
            }
            case 3: {
                out += Game.lang.get("Medium");
                break;
            }
            case 4: {
                out += Game.lang.get("High");
                break;
            }
            case 5: {
                out += Game.lang.get("VeryHigh");
                break;
            }
        }
        return out;
    }
    
    public static String getSettingsText_InGamePaddingLeft() {
        return "Menu Left Padding: " + Game.settingsManager.IN_GAME_LEFT_PADDING_EXTRA + " px";
    }
    
    public static final void updateSettings_ProvinceFlags(final int change) {
        Game.settingsManager.SETTINGS_PROVINCE_FLAGS = Math.max(0, Math.min(2, Game.settingsManager.SETTINGS_PROVINCE_FLAGS + change));
        Map.updateDrawProvincesFlags();
    }
    
    public static String getSettingsText_ProvinceFlags() {
        String out = Game.lang.get("Provinces") + ", " + Game.lang.get("Flags") + ": ";
        switch (Game.settingsManager.SETTINGS_PROVINCE_FLAGS) {
            case 0: {
                out += Game.lang.get("Off");
                break;
            }
            case 1: {
                out += Game.lang.get("Medium");
                break;
            }
            case 2: {
                out += Game.lang.get("High");
                break;
            }
        }
        return out;
    }
    
    public static final void updateSettingsProvinceNames_Scale(final int change) {
        Game.settingsManager.PROVINCE_NAMES_SCALE = Math.max(0.0f, Math.min(3.0f, Game.settingsManager.PROVINCE_NAMES_SCALE + change * 0.025f));
    }
    
    public static final void updateSettingsProvinceNames(final int change) {
        Game.settingsManager.SETTINGS_PROVINCE_NAMES = Math.max(0, Math.min(3, Game.settingsManager.SETTINGS_PROVINCE_NAMES + change));
        Game.addSimpleTask(new Game.SimpleTask("updateDrawProvinceNames") {
            @Override
            public void update() {
                ProvinceNamesManager.updateDrawProvinceNames();
                Game.mapCities.updateCitiesInGame();
            }
        });
    }
    
    public static String getSettingsText_Names() {
        String out = Game.lang.get("ProvinceNames") + ": ";
        switch (Game.settingsManager.SETTINGS_PROVINCE_NAMES) {
            case 0: {
                out += Game.lang.get("Off");
                break;
            }
            case 1: {
                out += Game.lang.get("Low");
                break;
            }
            case 2: {
                out += Game.lang.get("Medium");
                break;
            }
            default: {
                out += Game.lang.get("High");
                break;
            }
        }
        return out;
    }
    
    public static String getSettingsText_ProvincesBorderExtra() {
        String out = Game.lang.get("ProvinceBorder") + ", " + Game.lang.get("Extra") + ": ";
        out += CFG.getPrecision2(Game.settingsManager.BORDER_EXTRA_WIDTH, 100);
        return out;
    }
    
    public static final void updateSettingsCivNames(final int change) {
        Game.settingsManager.SETTINGS_CIV_NAMES = Math.max(0, Math.min(2, Game.settingsManager.SETTINGS_CIV_NAMES + change));
        Game.addSimpleTask(new Game.SimpleTask("updateDrawProvinceNames") {
            @Override
            public void update() {
                CivilizationRegionsManager.updateRenderer_CivNames();
            }
        });
    }
    
    public static String getSettingsText_CivNames() {
        String out = Game.lang.get("CivilizationsNames") + ": ";
        switch (Game.settingsManager.SETTINGS_CIV_NAMES) {
            case 0: {
                out += Game.lang.get("Off");
                break;
            }
            case 1: {
                out += Game.lang.get("Low");
                break;
            }
            case 2: {
                out += Game.lang.get("High");
                break;
            }
        }
        return out;
    }
    
    public static String getSettingsText_Double() {
        return Game.lang.get("DoubleBorder") + ": " + (Game.settingsManager.DOUBLE_BORDER ? Game.lang.get("On") : Game.lang.get("Off"));
    }
    
    public static final void updateSettings_Double() {
        Game.settingsManager.DOUBLE_BORDER = !Game.settingsManager.DOUBLE_BORDER;
        Game.addSimpleTask(new Game.SimpleTask("SettingsProvinceBorder_Double") {
            @Override
            public void update() {
                ProvinceBorder.updateDrawCivBorder();
            }
        });
    }
    
    public static String getSettingsText_Cities() {
        return Game.lang.get("Cities") + ": " + (Game.settingsManager.SETTINGS_CITIES ? Game.lang.get("On") : Game.lang.get("Off"));
    }
    
    public static final void updateSettings_Cities() {
        Game.settingsManager.SETTINGS_CITIES = !Game.settingsManager.SETTINGS_CITIES;
        Game.addSimpleTask(new Game.SimpleTask("updateCitiesInGame") {
            @Override
            public void update() {
                Game.mapCities.updateCitiesInGame();
            }
        });
    }
    
    public static String getSettingsText_Clouds() {
        return Game.lang.get("Clouds") + ": " + (Game.settingsManager.CLOUDS ? Game.lang.get("On") : Game.lang.get("Off"));
    }
    
    public static final void updateSettings_Clouds() {
        Game.settingsManager.CLOUDS = !Game.settingsManager.CLOUDS;
        Game.addSimpleTask(new Game.SimpleTask("updateCloudsInterface") {
            @Override
            public void update() {
                Game.cloudsAnimation.updateCloudsInterface();
            }
        });
    }
    
    public static String getSettingsText_Ships() {
        return Game.lang.get("Ships") + ": " + ((Game.settingsManager.SHIPS_ON_MAP > 0) ? (Game.settingsManager.SHIPS_ON_MAP + "%") : Game.lang.get("Off"));
    }
    
    public static String getSettingsText_FBO() {
        return "FBO, " + Game.lang.get("ProvinceNames") + ": " + (Game.settingsManager.FBO_PROVINCE_NAMES ? Game.lang.get("On") : Game.lang.get("Off"));
    }
    
    public static final void updateSettings_FBO() {
        Game.settingsManager.FBO_PROVINCE_NAMES = !Game.settingsManager.FBO_PROVINCE_NAMES;
    }
    
    public static String getSettingsText_FBO_Provinces() {
        return "FBO, " + Game.lang.get("Provinces") + ": " + (Game.settingsManager.FBO_PROVINCES ? Game.lang.get("On") : Game.lang.get("Off"));
    }
    
    public static final void updateSettings_FBO_Provinces() {
        Game.settingsManager.FBO_PROVINCES = !Game.settingsManager.FBO_PROVINCES;
    }
    
    static {
        SettingsProvince.value = new ValuesProvinceBorder();
    }
    
    public static class ValuesProvinceBorder
    {
        public float SCALE_NONE_NONE;
        public float SCALE_NONE_POINTY;
        public float SCALE_POINTY_POINTY;
        public float SCALE_POINTY_SMOOTH;
        public boolean ENABLE_DOUBLE_BORDER;
        public float MIN_BORDER_WIDTH;
        public float MAX_BORDER_WIDTH;
        public float BORDER_WIDTH_DIVIDE;
        public float DRAW_BORDERS;
    }
}
