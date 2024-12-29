// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.map;

import aoc.kingdoms.lukasz.map.RulersManager;
import com.badlogic.gdx.utils.JsonValue;
import java.util.Iterator;
import aoc.kingdoms.lukasz.jakowski.SaveLoad.SaveManager;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.files.FileHandle;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import com.badlogic.gdx.utils.Json;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.map.province.ProvinceDrawArmy;
import aoc.kingdoms.lukasz.jakowski.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.textures.Image;
import java.util.List;
import com.badlogic.gdx.graphics.Color;

public class MapCities
{
    public static Color COLOR_CITY_NAME;
    public static Color COLOR_CITY_CAPITAL_NAME;
    public static final int FONT_ID = 4;
    public List<Image> imageCity;
    public List<Image> imageCityFort;
    public CitiesSettings citiesSettings;
    public static float citiesScaleGrowthRate;
    public static final int ACTIVE_CITIES_ANIMATION_TIME = 550;
    public static long lTIME_ACTIVE_CITIES;
    public static final int HOVERED_CITIES_ANIMATION_TIME = 2000;
    public static long lTIME_HOVERED_CITIES;
    public CitiesInGame citiesInGame;
    public CapitalCityName capitalCityName;
    
    public MapCities() {
        this.imageCity = new ArrayList<Image>();
        this.imageCityFort = new ArrayList<Image>();
        this.citiesSettings = new CitiesSettings();
        this.citiesInGame = new CitiesInGame() {
            @Override
            public void draw(final SpriteBatch oSB, final float nScale) {
            }
        };
        this.capitalCityName = new CapitalCityName() {
            @Override
            public void draw(final SpriteBatch oSB, final int nProvinceID, final float fAlpha, final int nPosX, final int nPosY) {
            }
        };
        this.readSettings();
    }
    
    public final void updateCitiesScale_CurrentScale() {
        for (int i = this.citiesSettings.citiesScale_CurrentScale.length - 1; i >= 0; --i) {
            this.citiesSettings.citiesScale_CurrentScale[i] = Game.mapCities.citiesSettings.citiesScale[i] * Game.mapScale.getCurrentScale();
        }
    }
    
    public final void drawCities(final SpriteBatch oSB, final float nScale) {
        if (Game.mapScale.getCurrentScale() >= Game.DRAW_CITIES_MIN_SCALE) {
            this.drawCities_Just(oSB, nScale * 5.0f, ProvinceDrawArmy.DRAW_CITIES_ALPHA);
        }
        else if (ProvinceDrawArmy.drawCitiesHideAnimation) {
            this.drawCities_Just(oSB, nScale * 5.0f, ProvinceDrawArmy.DRAW_CITIES_ALPHA);
        }
        if (Game.settingsManager.SETTINGS_PROVINCE_FLAGS > 0) {
            if (Game.settingsManager.SETTINGS_PROVINCE_FLAGS == 1) {
                if (Game.mapScale.getCurrentScale() >= Game.DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE) {
                    this.drawCities_Just_InGame_CivFlag(oSB, nScale, ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA);
                }
                else if (ProvinceDrawArmy.drawProvinceNamesHideAnimation) {
                    this.drawCities_Just_InGame_CivFlag(oSB, nScale, ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA);
                }
            }
            else if (Game.mapScale.getCurrentScale() >= Game.DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE) {
                this.drawCities_Just_InGame_CivFlagName(oSB, nScale, ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA);
            }
            else if (ProvinceDrawArmy.drawProvinceNamesHideAnimation) {
                this.drawCities_Just_InGame_CivFlagName(oSB, nScale, ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA);
            }
        }
    }
    
    public final void drawCities_HighAllTheTime(final SpriteBatch oSB, final float nScale) {
        if (Game.mapScale.getCurrentScale() >= Game.DRAW_CITIES_MIN_SCALE) {
            this.drawCities_Just(oSB, nScale, ProvinceDrawArmy.DRAW_CITIES_ALPHA);
        }
        else if (ProvinceDrawArmy.drawCitiesHideAnimation) {
            this.drawCities_Just(oSB, nScale, ProvinceDrawArmy.DRAW_CITIES_ALPHA);
        }
        if (Game.mapScale.getCurrentScale() >= Game.DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE) {
            this.drawCities_Just_InGame_CivFlagName(oSB, nScale, ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA);
        }
        else if (ProvinceDrawArmy.drawProvinceNamesHideAnimation) {
            this.drawCities_Just_InGame_CivFlagName(oSB, nScale, ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA);
        }
    }
    
    public final void updateCitiesInGame() {
        if (Game.settingsManager.SETTINGS_CITIES) {
            if (Game.settingsManager.SETTINGS_PROVINCE_NAMES == 1) {
                this.citiesInGame = new CitiesInGame() {
                    @Override
                    public void draw(final SpriteBatch oSB, final float nScale) {
                        MapCities.this.drawCities_InGame_NamesLow(oSB, nScale);
                    }
                };
            }
            else {
                this.citiesInGame = new CitiesInGame() {
                    @Override
                    public void draw(final SpriteBatch oSB, final float nScale) {
                        MapCities.this.drawCities_InGame(oSB, nScale);
                    }
                };
            }
        }
        else {
            this.citiesInGame = new CitiesInGame() {
                @Override
                public void draw(final SpriteBatch oSB, final float nScale) {
                }
            };
        }
    }
    
    public final void updateCapitalCityName() {
        if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DIPLOMACY_IMPROVE_RELATIONS || Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DIPLOMACY_DAMAGE_RELATIONS) {
            this.capitalCityName = new CapitalCityName() {
                @Override
                public void draw(final SpriteBatch oSB, final int nProvinceID, final float fAlpha, final int nPosX, final int nPosY) {
                    Renderer.drawText(oSB, 4, "" + Game.getCiv(Game.getProvince(nProvinceID).getCivID()).getCivName(), nPosX + ImageManager.getImage(Images.capitalLeft).getWidth(), nPosY + (ImageManager.getImage(Images.capitalLeft).getHeight() - Game.getCiv(Game.getProvince(nProvinceID).getCivID()).iCapitalNameHeight) / 2, Game.getCiv(Game.player.iCivID).diplomacy.isImprovingRelations(Game.getProvince(nProvinceID).getCivID()) ? new Color(Colors.HOVER_POSITIVE.r, Colors.HOVER_POSITIVE.g, Colors.HOVER_POSITIVE.b, MapCities.COLOR_CITY_CAPITAL_NAME.a * fAlpha) : (Game.getCiv(Game.player.iCivID).diplomacy.isDamagingRelations(Game.getProvince(nProvinceID).getCivID()) ? new Color(Colors.HOVER_NEGATIVE.r, Colors.HOVER_NEGATIVE.g, Colors.HOVER_NEGATIVE.b, Colors.HOVER_NEGATIVE.a * fAlpha) : new Color(MapCities.COLOR_CITY_CAPITAL_NAME.r, MapCities.COLOR_CITY_CAPITAL_NAME.g, MapCities.COLOR_CITY_CAPITAL_NAME.b, MapCities.COLOR_CITY_CAPITAL_NAME.a * fAlpha)));
                }
            };
        }
        else {
            this.capitalCityName = new CapitalCityName() {
                @Override
                public void draw(final SpriteBatch oSB, final int nProvinceID, final float fAlpha, final int nPosX, final int nPosY) {
                    Renderer.drawText(oSB, 4, "" + Game.getCiv(Game.getProvince(nProvinceID).getCivID()).getCivName(), nPosX + ImageManager.getImage(Images.capitalLeft).getWidth(), nPosY + (ImageManager.getImage(Images.capitalLeft).getHeight() - Game.getCiv(Game.getProvince(nProvinceID).getCivID()).iCapitalNameHeight) / 2, new Color(MapCities.COLOR_CITY_CAPITAL_NAME.r, MapCities.COLOR_CITY_CAPITAL_NAME.g, MapCities.COLOR_CITY_CAPITAL_NAME.b, MapCities.COLOR_CITY_CAPITAL_NAME.a * fAlpha));
                }
            };
        }
    }
    
    public final void drawCities_InGame(final SpriteBatch oSB, final float nScale) {
        if (Game.mapScale.getCurrentScale() >= Game.DRAW_CITIES_MIN_SCALE) {
            this.drawCities_Just_InGame(oSB, nScale, ProvinceDrawArmy.DRAW_CITIES_ALPHA);
        }
        else if (ProvinceDrawArmy.drawCitiesHideAnimation) {
            this.drawCities_Just_InGame(oSB, nScale, ProvinceDrawArmy.DRAW_CITIES_ALPHA);
        }
        if (Game.mapScale.getCurrentScale() >= Game.DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE) {
            this.drawCities_Just_InGame_CivFlag(oSB, nScale, ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA);
        }
        else if (ProvinceDrawArmy.drawProvinceNamesHideAnimation) {
            this.drawCities_Just_InGame_CivFlag(oSB, nScale, ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA);
        }
    }
    
    public final void drawCities_InGame_NamesLow(final SpriteBatch oSB, final float nScale) {
        if (Game.mapScale.getCurrentScale() >= Game.DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE) {
            this.drawCities_Just_InGame_NamesLow(oSB, nScale, ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA);
        }
        else if (ProvinceDrawArmy.drawCitiesHideAnimation) {
            this.drawCities_Just_InGame_NamesLow(oSB, nScale, ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA);
        }
        if (Game.mapScale.getCurrentScale() >= Game.DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE) {
            this.drawCities_Just_InGame_CivFlag(oSB, nScale, ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA);
        }
        else if (ProvinceDrawArmy.drawProvinceNamesHideAnimation) {
            this.drawCities_Just_InGame_CivFlag(oSB, nScale, ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA);
        }
    }
    
    private final void drawCities_Just(final SpriteBatch oSB, final float nScale, final float fAlpha) {
        final float fAlpha2 = fAlpha * Game.mapScale.getCurrentScale() / 3.0f;
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, fAlpha));
        final float fontScale = Math.min(1.0f, Game.settingsManager.CITIES_FONT_SCALE * Game.mapScale.getCurrentScale());
        this.updateCitiesScale_CurrentScale();
        for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getProvinceInViewID(i)).getDrawCities()) {
                Game.getProvince(Game.getProvinceInViewID(i)).drawCities(oSB, nScale, fAlpha, fAlpha2, fontScale);
            }
        }
        for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getDrawCities()) {
                Game.getProvince(Game.getExtraProvinceInViewID(i)).drawCities(oSB, nScale, fAlpha, fAlpha2, fontScale);
            }
        }
        if (Game.iActiveProvince >= 0) {
            final long tempTime = CFG.currentTimeMillis;
            if (MapCities.lTIME_ACTIVE_CITIES > tempTime - 550L) {
                if (Game.getProvince(Game.iActiveProvince).getDrawProvince()) {
                    Game.getProvince(Game.iActiveProvince).drawCities(oSB, nScale, fAlpha * ((tempTime - MapCities.lTIME_ACTIVE_CITIES) / 550.0f), fAlpha * ((tempTime - MapCities.lTIME_ACTIVE_CITIES) / 550.0f), fontScale);
                }
            }
            else if (Game.getProvince(Game.iActiveProvince).getDrawProvince()) {
                Game.getProvince(Game.iActiveProvince).drawCities(oSB, nScale, fAlpha, fAlpha, fontScale);
            }
        }
        if (Game.iHoveredProvinceID >= 0 && Game.iHoveredProvinceID != Game.iActiveProvince) {
            final long tempTime = CFG.currentTimeMillis;
            if (MapCities.lTIME_HOVERED_CITIES > tempTime - 2000L) {
                if (Game.getProvince(Game.iHoveredProvinceID).getDrawProvince()) {
                    Game.getProvince(Game.iHoveredProvinceID).drawCities(oSB, nScale, fAlpha * ((tempTime - MapCities.lTIME_HOVERED_CITIES) / 2000.0f), fAlpha * ((tempTime - MapCities.lTIME_HOVERED_CITIES) / 2000.0f), fontScale);
                }
            }
            else if (Game.getProvince(Game.iHoveredProvinceID).getDrawProvince()) {
                Game.getProvince(Game.iHoveredProvinceID).drawCities(oSB, nScale, fAlpha, fAlpha, fontScale);
            }
        }
        oSB.setColor(Color.WHITE);
        Renderer.fontMain.get(4).getData().setScale(1.0f);
    }
    
    private final void drawCities_Just_InGame(final SpriteBatch oSB, final float nScale, final float fAlpha) {
        final float fAlpha2 = fAlpha * Game.mapScale.getCurrentScale() / 3.0f;
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, fAlpha));
        final float fontScale = Math.min(1.0f, Game.settingsManager.CITIES_FONT_SCALE * Game.mapScale.getCurrentScale());
        this.updateCitiesScale_CurrentScale();
        for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getProvinceInViewID(i)).getDrawCities()) {
                Game.getProvince(Game.getProvinceInViewID(i)).drawCities_InGame(oSB, nScale, fAlpha, fAlpha2, fontScale);
            }
        }
        for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getDrawCities()) {
                Game.getProvince(Game.getExtraProvinceInViewID(i)).drawCities_InGame(oSB, nScale, fAlpha, fAlpha2, fontScale);
            }
        }
        if (Game.iActiveProvince >= 0) {
            final long tempTime = CFG.currentTimeMillis;
            if (MapCities.lTIME_ACTIVE_CITIES > tempTime - 550L) {
                if (Game.getProvince(Game.iActiveProvince).getDrawProvince()) {
                    Game.getProvince(Game.iActiveProvince).drawCities_InGame(oSB, nScale, fAlpha * ((tempTime - MapCities.lTIME_ACTIVE_CITIES) / 550.0f), fAlpha * ((tempTime - MapCities.lTIME_ACTIVE_CITIES) / 550.0f), fontScale);
                }
            }
            else if (Game.getProvince(Game.iActiveProvince).getDrawProvince()) {
                Game.getProvince(Game.iActiveProvince).drawCities_InGame(oSB, nScale, fAlpha, fAlpha, fontScale);
            }
        }
        if (Game.iHoveredProvinceID >= 0 && Game.iHoveredProvinceID != Game.iActiveProvince) {
            final long tempTime = CFG.currentTimeMillis;
            if (MapCities.lTIME_HOVERED_CITIES > tempTime - 2000L) {
                if (Game.getProvince(Game.iHoveredProvinceID).getDrawProvince()) {
                    Game.getProvince(Game.iHoveredProvinceID).drawCities_InGame(oSB, nScale, fAlpha * ((tempTime - MapCities.lTIME_HOVERED_CITIES) / 2000.0f), fAlpha * ((tempTime - MapCities.lTIME_HOVERED_CITIES) / 2000.0f), fontScale);
                }
            }
            else if (Game.getProvince(Game.iHoveredProvinceID).getDrawProvince()) {
                Game.getProvince(Game.iHoveredProvinceID).drawCities_InGame(oSB, nScale, fAlpha, fAlpha, fontScale);
            }
        }
        oSB.setColor(Color.WHITE);
        Renderer.fontMain.get(4).getData().setScale(1.0f);
    }
    
    public final void drawCities_Just_InGame_CivFlagName(final SpriteBatch oSB, final float nScale, final float fAlpha) {
        for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getProvinceInViewID(i)).getDrawCities()) {
                Game.getProvince(Game.getProvinceInViewID(i)).drawCities_InGame_CivFlagName(oSB, nScale, fAlpha);
            }
        }
        for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getDrawCities()) {
                Game.getProvince(Game.getExtraProvinceInViewID(i)).drawCities_InGame_CivFlagName(oSB, nScale, fAlpha);
            }
        }
        oSB.setColor(Color.WHITE);
    }
    
    public final void drawCities_Just_InGame_CivFlag(final SpriteBatch oSB, final float nScale, final float fAlpha) {
        if (Game.settingsManager.SETTINGS_PROVINCE_FLAGS > 0) {
            for (int i = 0; i < Game.getCiv(Game.player.iCivID).diplomacy.iAtWarSize; ++i) {
                final int provinceID = Game.getCiv(Game.getCiv(Game.player.iCivID).diplomacy.atWar.get(i)).getCapitalProvinceID();
                if (provinceID >= 0 && Game.getProvince(provinceID).getDrawProvince()) {
                    Game.getProvince(provinceID).drawCities_InGame_CivFlagWar(oSB, nScale, fAlpha);
                }
            }
            for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (Game.getProvince(Game.getProvinceInViewID(i)).isCapital) {
                    Game.getProvince(Game.getProvinceInViewID(i)).drawCities_InGame_CivFlag(oSB, nScale, fAlpha);
                }
            }
            for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                if (Game.getProvince(Game.getExtraProvinceInViewID(i)).isCapital) {
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).drawCities_InGame_CivFlag(oSB, nScale, fAlpha);
                }
            }
            oSB.setColor(Color.WHITE);
        }
    }
    
    private final void drawCities_Just_InGame_NamesLow(final SpriteBatch oSB, final float nScale, final float fAlpha) {
        final float fAlpha2 = fAlpha * Game.mapScale.getCurrentScale() / 2.0f;
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, fAlpha));
        final float fontScale = Math.min(1.0f, Game.settingsManager.CITIES_FONT_SCALE * Game.mapScale.getCurrentScale());
        this.updateCitiesScale_CurrentScale();
        for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getProvinceInViewID(i)).getDrawCities()) {
                Game.getProvince(Game.getProvinceInViewID(i)).drawCities_InGame_NamesLow(oSB, nScale, fAlpha, fAlpha2, fontScale);
            }
        }
        for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getDrawCities()) {
                Game.getProvince(Game.getExtraProvinceInViewID(i)).drawCities_InGame_NamesLow(oSB, nScale, fAlpha, fAlpha2, fontScale);
            }
        }
        if (Game.iActiveProvince >= 0) {
            final long tempTime = CFG.currentTimeMillis;
            if (MapCities.lTIME_ACTIVE_CITIES > tempTime - 550L) {
                if (Game.getProvince(Game.iActiveProvince).getDrawProvince()) {
                    Game.getProvince(Game.iActiveProvince).drawCities_InGame_NamesLow(oSB, nScale, fAlpha * ((tempTime - MapCities.lTIME_ACTIVE_CITIES) / 550.0f), fAlpha * ((tempTime - MapCities.lTIME_ACTIVE_CITIES) / 550.0f), fontScale);
                }
            }
            else if (Game.getProvince(Game.iActiveProvince).getDrawProvince()) {
                Game.getProvince(Game.iActiveProvince).drawCities_InGame_NamesLow(oSB, nScale, fAlpha, fAlpha, fontScale);
            }
        }
        if (Game.iHoveredProvinceID >= 0 && Game.iHoveredProvinceID != Game.iActiveProvince) {
            final long tempTime = CFG.currentTimeMillis;
            if (MapCities.lTIME_HOVERED_CITIES > tempTime - 2000L) {
                if (Game.getProvince(Game.iHoveredProvinceID).getDrawProvince()) {
                    Game.getProvince(Game.iHoveredProvinceID).drawCities_InGame_NamesLow(oSB, nScale, fAlpha * ((tempTime - MapCities.lTIME_HOVERED_CITIES) / 2000.0f), fAlpha * ((tempTime - MapCities.lTIME_HOVERED_CITIES) / 2000.0f), fontScale);
                }
            }
            else if (Game.getProvince(Game.iHoveredProvinceID).getDrawProvince()) {
                Game.getProvince(Game.iHoveredProvinceID).drawCities_InGame_NamesLow(oSB, nScale, fAlpha, fAlpha, fontScale);
            }
        }
        oSB.setColor(Color.WHITE);
        Renderer.fontMain.get(4).getData().setScale(1.0f);
    }
    
    public final void loadCitiesImages() {
        if (CFG.isDesktop() || !GameValues.value.MOBILE_LOAD_CITIES_2) {
            for (int i = 0; i < this.citiesSettings.numOfCitiesImages; ++i) {
                this.imageCity.add(ImageManager.loadImage("gfx/cities/" + i + ".png"));
            }
            for (int i = 0; i < this.citiesSettings.numOfCitiesImages; ++i) {
                this.imageCityFort.add(ImageManager.loadImage("gfx/cities/fort/" + i + ".png"));
            }
        }
        else {
            this.imageCity.add(ImageManager.loadImage("gfx/cities/0.png"));
            this.imageCityFort.add(ImageManager.loadImage("gfx/cities/fort/0.png"));
        }
    }
    
    public final void readSettings() {
        final Json json = new Json();
        final FileHandle file = FileManager.loadFile("gfx/cities/" + (Game.highTextureSettings ? "Config.json" : "ConfigLow.json"));
        this.citiesSettings = (CitiesSettings)json.fromJson((Class)CitiesSettings.class, file);
        this.citiesSettings.citiesScale_CurrentScale = new float[this.citiesSettings.citiesScale.length];
    }
    
    public final void buildCities() {
        try {
            final List<GameCity> saveGameCities = new ArrayList<GameCity>();
            try {
                Config citiesData = this.readCities("cities.json");
                for (final Object e : citiesData.cities) {
                    saveGameCities.add((GameCity)e);
                }
                citiesData = null;
            }
            catch (final GdxRuntimeException ex) {
                CFG.exceptionStack((Throwable)ex);
            }
            for (int i = 0, iSize = saveGameCities.size(); i < iSize; ++i) {
                for (int j = 0; j < Game.getProvincesSize(); ++j) {
                    if (Game.getProvince(j).getMinX() <= saveGameCities.get(i).x * Game.mapBG.iMapScale && Game.getProvince(j).getMaxX() >= saveGameCities.get(i).x * Game.mapBG.iMapScale && Game.getProvince(j).getMinY() <= saveGameCities.get(i).y * Game.mapBG.iMapScale && Game.getProvince(j).getMaxY() >= saveGameCities.get(i).y * Game.mapBG.iMapScale && Game.pathContains(j, saveGameCities.get(i).x * Game.mapBG.iMapScale, saveGameCities.get(i).y * Game.mapBG.iMapScale)) {
                        saveGameCities.get(i).p = j;
                        break;
                    }
                }
            }
            final Json json = SaveManager.getJson();
            json.setElementType((Class)Config.class, "cities", (Class)GameCity.class);
            final FileHandle file = FileManager.getSaveType("map/" + Game.map.getFile_ActiveMap_Path() + "cities/cities.json");
            file.writeString(json.prettyPrint((Object)saveGameCities), false);
            Game.menuManager.addToastGold("Cities Generated!", Images.technology2);
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
            Game.menuManager.addToastGold("Cities Generation Error!", Images.technology2);
        }
    }
    
    public final void loadCities() {
        boolean generateCities = false;
        try {
            final FileHandle fileList = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "cities/cities.json");
            final Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
            for (final JsonValue jValue : tempArrayData) {
                final GameCity oCityData = (GameCity)json.readValue((Class)GameCity.class, jValue);
                if (oCityData.p >= 0 && oCityData.p < Game.getProvincesSize()) {
                    Game.getProvince(oCityData.p).addCity(new City(oCityData.Name, oCityData.x * Game.mapBG.iMapScale, oCityData.y * Game.mapBG.iMapScale, 0));
                }
            }
            tempArrayData.clear();
            tempArrayData = null;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            generateCities = true;
        }
        try {
            final FileHandle tempFileT = FileManager.loadFile("game/random/RandomProvinceNames.txt");
            String[] tempSplit = tempFileT.readString().split("\n");
            for (int i = 0; i < Game.getProvincesSize(); ++i) {
                if (Game.getProvince(i).getCitiesSize() == 0) {
                    Game.getProvince(i).setProvinceName(tempSplit[i % tempSplit.length]);
                }
            }
            tempSplit = null;
        }
        catch (final GdxRuntimeException ex2) {
            CFG.exceptionStack((Throwable)ex2);
        }
        try {
            if (generateCities) {
                this.buildCities();
            }
            else {
                final FileHandle tempFileT = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "cities/build_cities.txt");
                final boolean generate = Boolean.parseBoolean(tempFileT.readString());
                if (generate) {
                    this.buildCities();
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    private final Config readCities(final String nFileName) {
        final Json json = new Json();
        json.setElementType((Class)Config.class, "cities", (Class)GameCity.class);
        return (Config)json.fromJson((Class)Config.class, FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "cities/" + nFileName).reader("UTF8"));
    }
    
    public final void buildProvinceNames() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            if (Game.getProvince(i).getCitiesSize() > 0) {
                Game.getProvince(i).setProvinceName(Game.getProvince(i).getCity(0).sCityName);
            }
        }
    }
    
    public final void updateNameToNewTrueOwner(final int iProvinceID, final boolean updateNameNow) {
        try {
            if (Game.getProvince(iProvinceID).getCivID() == 0 || Game.getProvince(iProvinceID).getCitiesSize() == 0) {
                return;
            }
            String sCities = "";
            if (FileManager.loadFile("game/cities/" + RulersManager.groups.get(Game.getCiv(Game.getProvince(iProvinceID).getCivID()).iGroupID) + ".txt").exists()) {
                final FileHandle tempFileT = FileManager.loadFile("game/cities/" + RulersManager.groups.get(Game.getCiv(Game.getProvince(iProvinceID).getCivID()).iGroupID) + ".txt");
                sCities = tempFileT.readString();
            }
            else if (FileManager.loadFile("game/cities/" + Game.getCiv(Game.getProvince(iProvinceID).getCivID()).realTag + ".txt").exists()) {
                final FileHandle tempFileT = FileManager.loadFile("game/cities/" + Game.getCiv(Game.getProvince(iProvinceID).getCivID()).realTag + ".txt");
                sCities = tempFileT.readString();
            }
            else if (FileManager.loadFile("game/rulersRandom/link/" + Game.getCiv(Game.getProvince(iProvinceID).getCivID()).realTag + ".txt").exists()) {
                final FileHandle fileList = FileManager.loadFile("game/rulersRandom/link/" + Game.getCiv(Game.getProvince(iProvinceID).getCivID()).realTag + ".txt");
                final String nFile = fileList.readString();
                if (FileManager.loadFile("game/cities/" + nFile + ".txt").exists()) {
                    final FileHandle fileList2 = FileManager.loadFile("game/cities/" + nFile + ".txt");
                    sCities = fileList2.readString();
                }
            }
            if (sCities.length() > 0 && sCities.indexOf(Game.getProvince(iProvinceID).getCity(0).sCityName + "=") >= 0) {
                sCities = sCities.substring(sCities.indexOf(Game.getProvince(iProvinceID).getCity(0).sCityName));
                final int tIndex = sCities.indexOf(10, sCities.indexOf("=") + 1);
                sCities = sCities.substring(sCities.indexOf("=") + 1, (tIndex < 0) ? sCities.length() : tIndex);
                if (sCities.length() > 1 && sCities.indexOf("=") < 0) {
                    Game.addSimpleTask(new Game.SimpleTask(sCities, iProvinceID) {
                        @Override
                        public void update() {
                            Game.getProvince(this.id).getCity(0).setCityName(this.id, this.taskKey);
                        }
                    });
                    return;
                }
            }
            if (updateNameNow) {
                try {
                    Game.getProvince(iProvinceID).getCity(0).setCityNameOriginal(iProvinceID);
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
            else {
                Game.addSimpleTask(new Game.SimpleTask("updateCityName" + iProvinceID, iProvinceID) {
                    @Override
                    public void update() {
                        try {
                            Game.getProvince(this.id).getCity(0).setCityNameOriginal(this.id);
                        }
                        catch (final Exception ex) {
                            CFG.exceptionStack(ex);
                        }
                    }
                });
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
    }
    
    public final void updateNameToNewTrueOwner_Civ(final int iCivID, final boolean updateNameNow) {
        try {
            if (iCivID == 0 || Game.getCiv(iCivID).getNumOfProvinces() == 0) {
                return;
            }
            String sCities = "";
            if (FileManager.loadFile("game/cities/" + RulersManager.groups.get(Game.getCiv(iCivID).iGroupID) + ".txt").exists()) {
                final FileHandle tempFileT = FileManager.loadFile("game/cities/" + RulersManager.groups.get(Game.getCiv(iCivID).iGroupID) + ".txt");
                sCities = tempFileT.readString();
            }
            else if (FileManager.loadFile("game/cities/" + Game.getCiv(iCivID).realTag + ".txt").exists()) {
                final FileHandle tempFileT = FileManager.loadFile("game/cities/" + Game.getCiv(iCivID).realTag + ".txt");
                sCities = tempFileT.readString();
            }
            else if (FileManager.loadFile("game/rulersRandom/link/" + Game.getCiv(iCivID).realTag + ".txt").exists()) {
                final FileHandle fileList = FileManager.loadFile("game/rulersRandom/link/" + Game.getCiv(iCivID).realTag + ".txt");
                final String nFile = fileList.readString();
                if (FileManager.loadFile("game/cities/" + nFile + ".txt").exists()) {
                    final FileHandle fileList2 = FileManager.loadFile("game/cities/" + nFile + ".txt");
                    sCities = fileList2.readString();
                }
            }
            if (sCities.length() > 0) {
                for (int i = 0; i < Game.getCiv(iCivID).getNumOfProvinces(); ++i) {
                    if (Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).getCitiesSize() > 0) {
                        if (sCities.indexOf(Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).getCity(0).sCityName + "=") >= 0) {
                            String tempCities = sCities.substring(sCities.indexOf(Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).getCity(0).sCityName));
                            final int tIndex = tempCities.indexOf(10, tempCities.indexOf("=") + 1);
                            tempCities = tempCities.substring(tempCities.indexOf("=") + 1, (tIndex < 0) ? tempCities.length() : tIndex);
                            if (tempCities.length() > 1 && tempCities.indexOf("=") < 0) {
                                Game.gameThreadUpdate.addSimpleTask_First(new Game.SimpleTask(tempCities, Game.getCiv(iCivID).getProvinceID(i)) {
                                    @Override
                                    public void update() {
                                        Game.getProvince(this.id).getCity(0).setCityName(this.id, this.taskKey);
                                    }
                                });
                                continue;
                            }
                        }
                        if (updateNameNow) {
                            try {
                                Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).getCity(0).setCityNameOriginal(Game.getCiv(iCivID).getProvinceID(i));
                            }
                            catch (final Exception ex) {
                                CFG.exceptionStack(ex);
                            }
                        }
                        else {
                            Game.gameThreadUpdate.addSimpleTask_First(new Game.SimpleTask("updateCityName" + Game.getCiv(iCivID).getProvinceID(i), Game.getCiv(iCivID).getProvinceID(i)) {
                                @Override
                                public void update() {
                                    try {
                                        Game.getProvince(this.id).getCity(0).setCityNameOriginal(this.id);
                                    }
                                    catch (final Exception ex) {
                                        CFG.exceptionStack(ex);
                                    }
                                }
                            });
                        }
                    }
                }
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
    }
    
    public final String getProvinceCityTitle(final int iProvinceID) {
        if (Game.getProvince(iProvinceID).getCivID() == 0) {
            return "";
        }
        if (Game.getProvince(iProvinceID).isCapital) {
            return Game.lang.get("CapitalCity");
        }
        if (Game.getProvince(iProvinceID).getGrowthRateWithBonuses() >= GameValues.growthRate.GROWTH_RATE_MAJOR_CITY) {
            return Game.lang.get("MajorCity");
        }
        if (Game.getProvince(iProvinceID).getGrowthRateWithBonuses() >= GameValues.growthRate.GROWTH_RATE_CITY) {
            return Game.lang.get("City");
        }
        if (Game.getProvince(iProvinceID).getGrowthRateWithBonuses() >= GameValues.growthRate.GROWTH_RATE_TOWN) {
            return Game.lang.get("Town");
        }
        if (Game.getProvince(iProvinceID).getGrowthRateWithBonuses() >= GameValues.growthRate.GROWTH_RATE_VILLAGE) {
            return Game.lang.get("Village");
        }
        return Game.lang.get("SmallVillage");
    }
    
    public final void updateCities() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            Game.getProvince(i).setDrawCities(false);
        }
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            this.updateCities(i);
        }
    }
    
    public final void updateCities(final int nCivID) {
        try {
            final int tempNumOfCities = (int)Math.ceil(Game.getCiv(nCivID).getNumOfProvinces() * Game.settingsManager.PERCENTAGE_OF_CITIES_ON_MAP);
            int tMaxPopulation = 1;
            final List<Integer> tempProvinces = new ArrayList<Integer>();
            for (int i = 0; i < Game.getCiv(nCivID).getNumOfProvinces(); ++i) {
                tempProvinces.add(Game.getCiv(nCivID).getProvinceID(i));
                Game.getProvince(Game.getCiv(nCivID).getProvinceID(i)).setDrawCities(false);
                if (!Game.getProvince(Game.getCiv(nCivID).getProvinceID(i)).isOccupied() && tMaxPopulation < Game.getProvince(Game.getCiv(nCivID).getProvinceID(i)).getPopulationTotal()) {
                    tMaxPopulation = Game.getProvince(Game.getCiv(nCivID).getProvinceID(i)).getPopulationTotal();
                }
            }
            for (int j = 0; j < tempNumOfCities && tempProvinces.size() > 0; ++j) {
                int largestProvinceID = 0;
                int largestPopulation = Game.getProvince(tempProvinces.get(largestProvinceID)).getPopulationTotal();
                for (int k = 1, iSize = tempProvinces.size(); k < iSize; ++k) {
                    if (largestPopulation < Game.getProvince(tempProvinces.get(k)).getPopulationTotal()) {
                        largestProvinceID = k;
                        largestPopulation = Game.getProvince(tempProvinces.get(largestProvinceID)).getPopulationTotal();
                    }
                }
                Game.getProvince(tempProvinces.get(largestProvinceID)).setDrawCities(true);
                tempProvinces.remove(largestProvinceID);
            }
            if (Game.getCiv(nCivID).getCapitalProvinceID() >= 0) {
                Game.getProvince(Game.getCiv(nCivID).getCapitalProvinceID()).setDrawCities(true);
            }
            tempProvinces.clear();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    static {
        MapCities.COLOR_CITY_NAME = new Color(0.9137255f, 0.9137255f, 0.9137255f, 0.65f);
        MapCities.COLOR_CITY_CAPITAL_NAME = new Color(0.92156863f, 0.92156863f, 0.92156863f, 0.85f);
        MapCities.citiesScaleGrowthRate = 1.0f;
        MapCities.lTIME_ACTIVE_CITIES = 0L;
        MapCities.lTIME_HOVERED_CITIES = 0L;
    }
    
    public static class CitiesSettings
    {
        public int numOfCitiesImages;
        public float[] citiesScale;
        public float[] citiesScale_CurrentScale;
    }
    
    protected static class Config
    {
        public List<GameCity> cities;
        public String name;
    }
    
    public static class GameCity
    {
        public String Name;
        public int x;
        public int y;
        public int p;
    }
    
    public interface CitiesInGame
    {
        void draw(final SpriteBatch p0, final float p1);
    }
    
    public interface CapitalCityName
    {
        void draw(final SpriteBatch p0, final int p1, final float p2, final int p3, final int p4);
    }
}
