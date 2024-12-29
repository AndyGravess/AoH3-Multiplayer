// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menus;

import aoh.kingdoms.history.menu_element.MessageWar;
import aoh.kingdoms.history.menu_element.MessageButton;
import com.badlogic.gdx.utils.Json;
import aoh.kingdoms.history.menu_element.button.ButtonArmyIcon;
import aoh.kingdoms.history.map.province.ProvinceDrawArmy;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.files.FileHandle;
import aoh.kingdoms.history.menus.NewGame.NewGame;
import aoh.kingdoms.history.map.civilization.CivilizationRegionsManager;
import aoh.kingdoms.history.map.allianceHRE.HREManager;
import aoh.kingdoms.history.map.map.Ship.ShipManager;
import aoh.kingdoms.history.menu_element.button.ButtonFlag;
import aoh.kingdoms.history.mainGame.Missions.MissionTree;
import aoh.kingdoms.history.events.EventsManager;
import aoh.kingdoms.history.map.plague.PlagueManager;
import aoh.kingdoms.history.map.technology.TechnologyTree;
import aoh.kingdoms.history.map.WondersManager;
import aoh.kingdoms.history.menusInGame.InGame;
import aoh.kingdoms.history.map.ResourcesManager;
import aoh.kingdoms.history.mainGame.AI.Build.AI_Build;
import aoh.kingdoms.history.map.BuildingsManager;
import aoh.kingdoms.history.map.army.ArmyManager;
import aoh.kingdoms.history.map.AdvantagesManager;
import aoh.kingdoms.history.map.LawsManager;
import aoh.kingdoms.history.map.LegacyManager;
import aoh.kingdoms.history.map.RulersManager;
import aoh.kingdoms.history.map.battles.BattleManager;
import aoh.kingdoms.history.mainGame.SaveLoad.LoadManager;
import aoh.kingdoms.history.mainGame.setting.SettingsDesktop;
import aoh.kingdoms.history.map.map.MapBG;
import aoh.kingdoms.history.mainGame.setting.SettingsProvince;
import aoh.kingdoms.history.map.province.ProvinceNamesManager;
import aoh.kingdoms.history.map.province.ProvinceBorder;
import aoh.kingdoms.history.menu.View;
import com.badlogic.gdx.utils.GdxRuntimeException;
import aoh.kingdoms.history.mainGame.FileManager;
import com.badlogic.gdx.graphics.Texture;
import aoh.kingdoms.history.textures.ImageManager;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.menu.Menu;

public class InitGame extends Menu
{
    public String loadingName;
    public static Image background;
    public static int backgroundID;
    public static int backgroundSize;
    public static int backgroundWidth;
    public static int backgroundHeight;
    public static int iStepID;
    public static int iNumOfSteps;
    public static int numOfProvincesBGToLoad;
    private boolean justOnce;
    public int takeABreak;
    public boolean reloadBoldFont;
    
    public InitGame() {
        this.loadingName = "";
        this.justOnce = true;
        this.takeABreak = 0;
        this.reloadBoldFont = false;
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements, true);
        InitGame.numOfProvincesBGToLoad = (CFG.isDesktop() ? GameValues.value.LOADING_NUM_OF_PROVINCES_PC : GameValues.value.LOADING_NUM_OF_PROVINCES_MOBILE);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        this.initGame();
        if (InitGame.background != null) {
            oSB.setColor(new Color(0.047058824f, 0.047058824f, 0.047058824f, 1.0f));
            Images.pix.draw(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.GAME_HEIGHT);
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, InitGame.iStepID / (float)(InitGame.iNumOfSteps + Game.map.getActiveMap_MapData().mapData.NumOfProvinces) * 0.25f));
            InitGame.background.draw(oSB, iTranslateX + (CFG.GAME_WIDTH - InitGame.backgroundWidth) / 2, iTranslateY + (CFG.GAME_HEIGHT - InitGame.backgroundHeight) / 2, InitGame.backgroundWidth, InitGame.backgroundHeight);
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.95f + InitGame.iStepID / (float)(InitGame.iNumOfSteps + Game.map.getActiveMap_MapData().mapData.NumOfProvinces) * 0.05f));
            oSB.setShader(Renderer.shaderAlpha);
            InitGame.background.getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            Images.gradientXY.draw(oSB, this.getPosX() + (CFG.GAME_WIDTH - InitGame.backgroundWidth) / 2 + iTranslateX, this.getPosY() + (CFG.GAME_HEIGHT - InitGame.backgroundHeight) / 2 + iTranslateY, InitGame.backgroundWidth, InitGame.backgroundHeight);
            oSB.flush();
            oSB.setShader(Renderer.shaderDefault);
            oSB.setColor(Color.WHITE);
        }
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.6f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.PADDING * 3);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, iTranslateX, CFG.GAME_HEIGHT - CFG.PADDING * 3 + iTranslateY, CFG.GAME_WIDTH, CFG.PADDING * 3, false, true);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
        if (InitGame.iStepID > 8) {
            Renderer.drawLoading(oSB, iTranslateX, iTranslateY, 0.12f + 0.88f * InitGame.iStepID / (InitGame.iNumOfSteps + Game.map.getActiveMap_MapData().mapData.NumOfProvinces));
            Renderer.drawTextWithShadow(oSB, CFG.FONT_REGULAR_SMALL, this.loadingName, iTranslateX + CFG.PADDING * 4, iTranslateY + CFG.PADDING * 4, new Color(1.0f, 1.0f, 1.0f, 0.15f));
        }
    }
    
    public static final void loadBackground() {
        if (InitGame.background != null) {
            InitGame.background.dispose();
            InitGame.background = null;
        }
        for (int i = 0; i < 5; ++i) {
            final int newID = Game.oR.nextInt(InitGame.backgroundSize);
            if (newID != InitGame.backgroundID) {
                InitGame.backgroundID = newID;
                break;
            }
        }
        InitGame.background = new Image(ImageManager.loadTexture_RGB888("ui/loading/" + InitGame.backgroundID + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        final float fScale = Math.max(CFG.GAME_WIDTH / (float)InitGame.background.getWidth(), CFG.GAME_HEIGHT / (float)InitGame.background.getHeight());
        InitGame.backgroundWidth = (int)(InitGame.background.getWidth() * fScale);
        InitGame.backgroundHeight = (int)(InitGame.background.getHeight() * fScale);
    }
    
    public void setLoadText(final String sText) {
        this.loadingName = sText;
    }
    
    public boolean loadMapOverlays() {
        return CFG.isDesktop() || !GameValues.value.MOBILE_DISABLE_MAP_OVERLAYS;
    }
    
    public static boolean loadArmy_Default() {
        return CFG.isDesktop();
    }
    
    public void initGame() {
        if (this.takeABreak > 0) {
            --this.takeABreak;
            return;
        }
        try {
            if (InitGame.iStepID == 0) {
                Game.gameThreadUpdate.start();
                try {
                    final FileHandle tempFileT = FileManager.loadFile("ui/loading/numberOfImages.txt");
                    InitGame.backgroundSize = Integer.parseInt(tempFileT.readString());
                }
                catch (final GdxRuntimeException ex) {
                    CFG.exceptionStack((Throwable)ex);
                }
                loadBackground();
                this.setLoadText("Loading");
            }
            else if (InitGame.iStepID == 1) {
                Renderer.clearFonts();
                Renderer.charset = Game.lang.get("charset");
                Renderer.loadFont(Game.lang.get("font"), Game.lang.get("charset"), Game.settingsManager.FONT_MAIN_SIZE);
                CFG.FONT_BOLD = Renderer.fontMain.size() - 1;
            }
            else if (InitGame.iStepID == 2) {
                this.loadImagesInit();
                if (Game.settingsManager.LANGUAGE_TAG == null) {
                    ++InitGame.iStepID;
                    this.reloadBoldFont = true;
                    Game.menuManager.setViewIDWithoutAnimation(View.INIT_GAME_MENU_LANGUAGE);
                    return;
                }
            }
            else if (InitGame.iStepID == 3) {
                if (Game.map.lMaps.size() > 1) {
                    ++InitGame.iStepID;
                    Game.menuManager.setViewIDWithoutAnimation(View.INIT_GAME_MENU_SELECT_MAP);
                    return;
                }
            }
            else if (InitGame.iStepID == 4) {
                if (this.reloadBoldFont && Game.settingsManager.LANGUAGE_TAG.length() > 0) {
                    Renderer.clearFonts();
                    Renderer.charset = Game.lang.get("charset");
                    Renderer.loadFont(Game.lang.get("font"), Game.lang.get("charset"), Game.settingsManager.FONT_MAIN_SIZE);
                    CFG.FONT_BOLD = Renderer.fontMain.size() - 1;
                }
                GameValues.init();
            }
            else if (InitGame.iStepID == 5) {
                Renderer.loadFont(Game.lang.get("font2"), Game.lang.get("charset"), Game.settingsManager.FONT_MAIN_SIZE);
                CFG.FONT_REGULAR = Renderer.fontMain.size() - 1;
                this.takeABreak = 2;
            }
            else if (InitGame.iStepID == 6) {
                Renderer.loadFont(Game.lang.get("font"), Game.lang.get("charset"), (int)Math.floor(Game.settingsManager.FONT_MAIN_SIZE * 0.9f));
                CFG.FONT_BOLD_SMALL = Renderer.fontMain.size() - 1;
                this.takeABreak = 2;
            }
            else if (InitGame.iStepID == 7) {
                Renderer.loadFont(Game.lang.get("fontArmy"), "0123456789.,+-/*?" + GameValues.fog.TEXT_UNKNOWN_ARMIES, (Game.settingsManager.FONT_ARMY_SIZE < 0) ? (loadArmy_Default() ? GameValues.value.DEFAULT_FONT_ARMY_SIZE : GameValues.value.DEFAULT_FONT_ARMY_SIZE_2) : Game.settingsManager.FONT_ARMY_SIZE);
                CFG.FONT_ARMY = Renderer.fontMain.size() - 1;
                Renderer.loadFontArmy_GlyphLayout(Game.lang.get("fontArmy"), "0123456789.,+-/*?" + GameValues.fog.TEXT_UNKNOWN_ARMIES, (Game.settingsManager.FONT_ARMY_SIZE < 0) ? (loadArmy_Default() ? GameValues.value.DEFAULT_FONT_ARMY_SIZE : GameValues.value.DEFAULT_FONT_ARMY_SIZE_2) : Game.settingsManager.FONT_ARMY_SIZE);
                this.takeABreak = 1;
            }
            else if (InitGame.iStepID == 8) {
                Renderer.loadFont(Game.lang.get("font4"), Game.lang.get("charset"), 15);
                CFG.FONT_CITIES = Renderer.fontMain.size() - 1;
                this.takeABreak = 2;
            }
            else if (InitGame.iStepID == 9) {
                Renderer.loadFont(Game.lang.get("font2"), Game.lang.get("charset"), (int)Math.floor(Game.settingsManager.FONT_MAIN_SIZE * 0.9f));
                CFG.FONT_REGULAR_SMALL = Renderer.fontMain.size() - 1;
                Renderer.loadFont_UpdateTextHeight();
                Renderer.loadFont_UpdateTextHeightSmall();
                this.takeABreak = 2;
                this.setLoadText("Loading");
            }
            else if (InitGame.iStepID == 10) {
                Game.menuManager.initColorPicker();
                Game.lang.loadModsLanguages((Game.settingsManager.LANGUAGE_TAG == null) ? "" : Game.settingsManager.LANGUAGE_TAG);
            }
            else if (InitGame.iStepID == 11) {
                Game.loadAiValues();
                this.setLoadText("Loading Fonts");
            }
            else if (InitGame.iStepID == 12) {
                Renderer.loadFontBorder(Game.lang.get("fontCivNames"), Game.lang.get("charset2"));
                this.takeABreak = 2;
                this.setLoadText("Loading Fonts");
            }
            else if (InitGame.iStepID == 13) {
                Game.menuManager.dialogMenu = new Dialog();
                this.setLoadText("Loading");
            }
            else if (InitGame.iStepID == 14) {
                ProvinceBorder.updateDrawCivBorder();
                ProvinceNamesManager.updateDrawProvinceNames();
                this.setLoadText("Loading");
            }
            else if (InitGame.iStepID == 15) {
                SettingsProvince.init(Game.settingsManager.SETTINGS_PROVINCE_BORDER);
                Game.mapCities.updateCitiesInGame();
                Game.mapCities.updateCapitalCityName();
                Game.loadCursor(false);
                this.setLoadText("Loading Game Values");
            }
            else if (InitGame.iStepID == 16) {
                this.setLoadText("Loading Map BG");
            }
            else if (InitGame.iStepID == 17) {
                Game.mapBG.loadMapBG_Begin();
                this.setLoadText("Loading Map BG #1");
            }
            else if (InitGame.iStepID == 18) {
                if (Game.mapBG.loadMapBG()) {
                    this.setLoadText("Loading Map BG #" + (MapBG.loadMapBG_FileID + 1));
                    return;
                }
            }
            else if (InitGame.iStepID == 19) {
                Game.mapBG.loadMapBG_End();
            }
            else if (InitGame.iStepID == 20) {
                this.setLoadText("Loading");
            }
            else if (InitGame.iStepID == 21) {
                if (CFG.isDesktop()) {
                    SettingsDesktop.readConfig();
                }
                this.setLoadText("Loading");
            }
            else if (InitGame.iStepID == 22) {
                MapBG.loadMapBG_FileID = 0;
                this.setLoadText("Loading Map BG2");
            }
            else if (InitGame.iStepID == 23) {
                if (Game.mapBG.loadMapBG_ZoomOut()) {
                    this.setLoadText("Loading Map BG2 #" + (MapBG.loadMapBG_FileID + 1));
                    return;
                }
                this.setLoadText("Loading Map");
            }
            else if (InitGame.iStepID == 24) {
                Game.mapBG.loadMapBG_ZoomOut_End();
                this.setLoadText("Loading Map");
            }
            else if (InitGame.iStepID == 25) {
                Game.mapBG.loadMapBorder();
                this.setLoadText("Loading Map");
            }
            else if (InitGame.iStepID == 26) {
                Game.mapBG.loadMinimap();
                this.setLoadText("Loading");
            }
            else if (InitGame.iStepID == 27) {
                this.setLoadText("Loading");
            }
            else if (InitGame.iStepID == 28) {
                this.setLoadText("Loading");
            }
            else if (InitGame.iStepID == 29) {
                this.setLoadText("Loading Audio");
            }
            else if (InitGame.iStepID == 30) {
                Game.soundsManager.loadSounds();
                this.setLoadText("Loading Audio");
            }
            else if (InitGame.iStepID == 31) {
                Game.soundsManager.loadMusic_List();
                this.setLoadText("Loading Audio");
            }
            else if (InitGame.iStepID == 32) {
                Game.ambienceManager.loadSounds();
                this.setLoadText("Loading Map");
            }
            else if (InitGame.iStepID == 33) {
                Game.mapBG.updateWorldMap();
                this.setLoadText("Loading Continents");
            }
            else if (InitGame.iStepID == 34) {
                Game.continents.loadMapContinents();
                Game.geographicalRegions.loadMapGeographicalRegions();
                this.setLoadText("Loading Province Points");
            }
            else if (InitGame.iStepID == 35) {
                if (LoadManager.loadProvincePoints()) {
                    this.setLoadText("Loading Province Points #" + (LoadManager.loadProvincePointsFileID + 1));
                    return;
                }
                this.setLoadText("Loading Overlays #1");
            }
            else if (InitGame.iStepID == 36) {
                if (this.loadMapOverlays()) {
                    MapBG.loadMapBG_FileID = 0;
                    Game.mapOver.loadOverlay("Overlays.json");
                }
                this.setLoadText("Loading Overlays #1");
            }
            else if (InitGame.iStepID == 37) {
                if (this.loadMapOverlays()) {
                    if (Game.mapOver.loadOverlayImages()) {
                        return;
                    }
                    MapBG.loadMapBG_FileID = 0;
                }
                this.setLoadText("Loading Overlays #2");
            }
            else if (InitGame.iStepID == 38) {
                if (this.loadMapOverlays()) {
                    if (Game.mapOver.loadOverlayImages_2()) {
                        return;
                    }
                    MapBG.loadMapBG_FileID = 0;
                }
                this.setLoadText("Loading Overlays #2");
            }
            else if (InitGame.iStepID == 39) {
                if (this.loadMapOverlays()) {
                    MapBG.loadMapBG_FileID = 0;
                    Game.mapOverSea.loadOverlay("OverlaysSea.json");
                }
                this.setLoadText("Loading Overlays #2");
            }
            else if (InitGame.iStepID == 40) {
                if (this.loadMapOverlays()) {
                    if (Game.mapOverSea.loadOverlayImages()) {
                        return;
                    }
                    MapBG.loadMapBG_FileID = 0;
                }
                this.setLoadText("Loading Overlays #2");
            }
            else if (InitGame.iStepID == 41) {
                if (this.loadMapOverlays()) {
                    if (Game.mapOverSea.loadOverlayImages_2()) {
                        return;
                    }
                    MapBG.loadMapBG_FileID = 0;
                }
                this.setLoadText("Loading Images #1");
            }
            else if (InitGame.iStepID == 42) {
                this.loadImages_1();
                this.setLoadText("Loading Images #2");
            }
            else if (InitGame.iStepID == 43) {
                this.loadImages_2();
                this.setLoadText("Loading Images #3");
            }
            else if (InitGame.iStepID == 44) {
                this.loadImages_3();
                this.setLoadText("Loading Images #4");
            }
            else if (InitGame.iStepID == 45) {
                this.loadImages_4();
                this.setLoadText("Loading Images #5");
            }
            else if (InitGame.iStepID == 46) {
                this.loadImages_5();
                this.setLoadText("Loading Images #6");
            }
            else if (InitGame.iStepID == 47) {
                this.loadImages_6();
                this.setLoadText("Loading Images #7");
            }
            else if (InitGame.iStepID == 48) {
                this.loadImages_7();
                this.setLoadText("Loading Images #8");
            }
            else if (InitGame.iStepID == 49) {
                this.loadImages_8();
                this.loadSparks();
                this.setLoadText("Loading Province Map Data");
            }
            else if (InitGame.iStepID == 50) {
                LoadManager.loadProvinceMapData();
                this.setLoadText("Loading Ages");
            }
            else if (InitGame.iStepID == 51) {
                Game.player.fog.initFogOfWar();
                Game.battleManager = new BattleManager();
                Game.gameAges.loadAges();
                this.setLoadText("Loading Regions");
            }
            else if (InitGame.iStepID == 52) {
                Game.regions.loadRegions();
                this.setLoadText("Loading Terrains");
            }
            else if (InitGame.iStepID == 53) {
                Game.terrainManager.loadTerrains();
                this.setLoadText("Loading Province Border");
            }
            else if (InitGame.iStepID == 54) {
                if (LoadManager.loadProvinceBorder()) {
                    this.setLoadText("Loading Province Border #" + (LoadManager.loadProvinceBorderFileID + 1));
                    return;
                }
                this.setLoadText("Loading Neighboring Provinces");
            }
            else if (InitGame.iStepID == 55) {
                LoadManager.buildNeighboringProvinces();
                this.setLoadText("Loading Random Rulers");
            }
            else if (InitGame.iStepID == 56) {
                RulersManager.initRandomRulers();
                this.setLoadText("Loading Religions");
            }
            else if (InitGame.iStepID == 57) {
                Game.ideologiesManager.loadIdeologies();
                this.setLoadText("Loading Religions");
            }
            else if (InitGame.iStepID == 58) {
                Game.religionManager.loadReligions();
                this.setLoadText("Loading Legacies");
            }
            else if (InitGame.iStepID == 59) {
                LegacyManager.loadLegacies();
                this.setLoadText("Loading Laws");
            }
            else if (InitGame.iStepID == 60) {
                LawsManager.loadLaws();
                this.setLoadText("Loading Advantages");
            }
            else if (InitGame.iStepID == 61) {
                AdvantagesManager.loadAdvantages();
                this.setLoadText("Loading Units");
            }
            else if (InitGame.iStepID == 62) {
                ArmyManager.loadUnits();
                this.setLoadText("Loading Buildings");
            }
            else if (InitGame.iStepID == 63) {
                BuildingsManager.loadBuildings();
                AI_Build.initBuildings();
                this.setLoadText("Loading Resources");
            }
            else if (InitGame.iStepID == 64) {
                ResourcesManager.loadResources();
                this.setLoadText("Loading Clouds");
            }
            else if (InitGame.iStepID == 65) {
                Game.cloudsAnimation.updateCloudsInterface();
                Game.cloudsAnimation.loadCloudsImages();
                this.setLoadText("Loading Wonders");
            }
            else if (InitGame.iStepID == 66) {
                InGame.initInGame();
                WondersManager.loadWonders();
                this.setLoadText("Loading Cities Images");
            }
            else if (InitGame.iStepID == 67) {
                Game.mapCities.loadCitiesImages();
                this.setLoadText("Loading Cities");
            }
            else if (InitGame.iStepID == 68) {
                Game.mapCities.loadCities();
                this.setLoadText("Loading Cities");
            }
            else if (InitGame.iStepID == 69) {
                Game.mapCities.buildProvinceNames();
                this.setLoadText("Loading Generals");
            }
            else if (InitGame.iStepID == 70) {
                Game.generalManager.loadGenerals();
                this.setLoadText("Loading Advisors");
            }
            else if (InitGame.iStepID == 71) {
                Game.advisorManager.loadAdvisors();
                this.setLoadText("Loading Scenarios");
            }
            else if (InitGame.iStepID == 72) {
                Game.mapScenarios.loadScenarios(true);
                this.setLoadText("Loading Technology Tree");
            }
            else if (InitGame.iStepID == 73) {
                TechnologyTree.loadTechnology();
                this.setLoadText("Build Data");
            }
            else if (InitGame.iStepID == 74) {
                LoadManager.loadProvinceNamesPoints();
                Game.addSimpleTask(new Game.SimpleTask("buildProvNameData") {
                    @Override
                    public void update() {
                        ProvinceNamesManager.buildProvNameData();
                    }
                });
                this.setLoadText("Build Data");
            }
            else if (InitGame.iStepID == 75) {
                TechnologyTree.buildTechUnlocks();
                this.setLoadText("Build Data");
            }
            else if (InitGame.iStepID == 76) {
                TechnologyTree.buildTechUnlocks_Units();
                this.setLoadText("Build Data");
            }
            else if (InitGame.iStepID == 77) {
                TechnologyTree.buildTechUnlocks_Laws();
                TechnologyTree.buildTechnologiesNames();
                this.setLoadText("Loading Diseases");
            }
            else if (InitGame.iStepID == 78) {
                WondersManager.initProvinceWonders();
                PlagueManager.loadDiseases();
                this.setLoadText("Build Data");
            }
            else if (InitGame.iStepID == 79) {
                EventsManager.loadScenarioEventsTag = Game.mapScenarios.lScenarios_TagsList.get(Game.scenarioID);
                MissionTree.loadMissions();
                Game.mapBG.updateMinimapResolution();
                Game.mapBG.updatePreviewResolution();
                ButtonFlag.EXTRA_RANDOM = Game.oR.nextInt(10);
                this.setLoadText("Loading Scenario #1");
            }
            else if (InitGame.iStepID == 80) {
                Game.mapScenarios.loadScenario_1();
                this.setLoadText("Loading Scenario #2 Wasteland");
            }
            else if (InitGame.iStepID == 81) {
                Game.mapScenarios.loadScenario_2();
                this.setLoadText("Loading Scenario #3 Civilizations");
            }
            else if (InitGame.iStepID == 82) {
                Game.mapScenarios.loadScenario_3(false);
                this.setLoadText("Loading Scenario #3A Civilizations Names");
            }
            else if (InitGame.iStepID == 83) {
                Game.mapScenarios.loadScenario_3_A();
                this.setLoadText("Loading Scenario #3B Flags");
            }
            else if (InitGame.iStepID == 84) {
                Game.mapScenarios.loadScenario_3_B();
                this.setLoadText("Loading Scenario #3C Civilization's Provinces");
            }
            else if (InitGame.iStepID == 85) {
                Game.mapScenarios.loadScenario_3_C();
                this.setLoadText("Loading Scenario #4");
            }
            else if (InitGame.iStepID == 86) {
                Game.mapScenarios.loadScenario_4();
                this.setLoadText("Loading Scenario #5");
            }
            else if (InitGame.iStepID == 87) {
                Game.mapScenarios.loadScenario_5();
                this.setLoadText("Loading Scenario #6");
            }
            else if (InitGame.iStepID == 88) {
                Game.mapScenarios.loadScenario_6();
                this.setLoadText("Loading Scenario #7");
            }
            else if (InitGame.iStepID == 89) {
                Game.mapScenarios.loadScenario_7();
                this.setLoadText("Loading Scenario #8");
            }
            else if (InitGame.iStepID == 90) {
                Game.mapScenarios.loadScenario_8();
                this.setLoadText("Loading Scenario #9");
            }
            else if (InitGame.iStepID == 91) {
                Game.mapScenarios.loadScenario_9();
                this.setLoadText("Loading Scenario #10");
            }
            else if (InitGame.iStepID == 92) {
                Game.mapScenarios.loadScenario_10();
                this.setLoadText("Loading Scenario #11");
            }
            else if (InitGame.iStepID == 93) {
                Game.mapScenarios.loadScenario_11();
                this.setLoadText("Loading Scenario #12");
            }
            else if (InitGame.iStepID == 94) {
                Game.mapScenarios.loadScenario_12();
                this.setLoadText("Loading Scenario #13");
            }
            else if (InitGame.iStepID == 95) {
                Game.mapScenarios.loadScenario_13();
                this.setLoadText("Loading Scenario #14");
            }
            else if (InitGame.iStepID == 96) {
                Game.mapScenarios.loadScenario_14();
                this.setLoadText("Loading Scenario #15");
            }
            else if (InitGame.iStepID == 97) {
                Game.mapScenarios.loadScenario_15();
                this.setLoadText("Loading Scenario #16");
            }
            else if (InitGame.iStepID == 98) {
                Game.mapScenarios.loadScenario_16();
                this.setLoadText("Loading Scenario #17");
            }
            else if (InitGame.iStepID == 99) {
                Game.mapScenarios.loadScenario_17();
                this.setLoadText("Loading Scenario #18");
            }
            else if (InitGame.iStepID == 100) {
                Game.mapScenarios.loadScenario_18();
                this.setLoadText("Loading Scenario #19");
            }
            else if (InitGame.iStepID == 101) {
                Game.mapScenarios.loadScenario_19();
                this.setLoadText("Loading Scenario #20");
            }
            else if (InitGame.iStepID == 102) {
                Game.mapScenarios.loadScenario_20();
                this.setLoadText("Loading Scenario #21");
            }
            else if (InitGame.iStepID == 103) {
                Game.mapScenarios.loadScenario_21();
                this.setLoadText("Loading Scenario #22");
            }
            else if (InitGame.iStepID == 104) {
                Game.mapScenarios.loadScenario_22();
                this.setLoadText("Loading Scenario #23");
            }
            else if (InitGame.iStepID == 105) {
                Game.mapScenarios.loadScenario_23();
                this.setLoadText("Loading Scenario #24");
            }
            else if (InitGame.iStepID == 106) {
                Game.mapScenarios.loadScenario_24();
                this.setLoadText("Loading Scenario #25");
            }
            else if (InitGame.iStepID == 107) {
                Game.mapScenarios.loadScenario_25(false);
                this.setLoadText("Loading Scenario #26");
            }
            else if (InitGame.iStepID == 108) {
                Game.mapScenarios.loadScenario_26();
                this.setLoadText("Loading Scenario #27");
            }
            else if (InitGame.iStepID == 109) {
                Game.mapScenarios.loadScenario_27();
                this.setLoadText("Loading Scenario #28");
            }
            else if (InitGame.iStepID == 110) {
                Game.mapScenarios.loadScenario_28();
                this.setLoadText("Loading Scenario #29");
            }
            else if (InitGame.iStepID == 111) {
                Game.mapScenarios.loadScenario_29();
                this.setLoadText("Loading Scenario #30");
            }
            else if (InitGame.iStepID == 112) {
                Game.mapScenarios.loadScenario_30();
                this.setLoadText("Loading Scenario #31");
            }
            else if (InitGame.iStepID == 113) {
                Game.mapScenarios.loadScenario_31();
                this.setLoadText("Loading Scenario #32");
            }
            else if (InitGame.iStepID == 114) {
                Game.mapScenarios.loadScenario_32();
                this.setLoadText("Loading Scenario #33");
            }
            else if (InitGame.iStepID == 115) {
                Game.mapScenarios.loadScenario_33();
                this.setLoadText("Loading Scenario #34");
            }
            else if (InitGame.iStepID == 116) {
                Game.mapScenarios.loadScenario_34();
                this.setLoadText("Loading Scenario #35");
            }
            else if (InitGame.iStepID == 117) {
                Game.mapScenarios.loadScenario_35();
                this.setLoadText("Loading Scenario #36");
            }
            else if (InitGame.iStepID == 118) {
                Game.mapScenarios.loadScenario_36(false);
                this.setLoadText("Loading Scenario #37");
            }
            else if (InitGame.iStepID == 119) {
                Game.mapScenarios.loadScenario_37();
                this.setLoadText("Loading Scenario #38");
            }
            else if (InitGame.iStepID == 120) {
                Game.mapScenarios.loadScenario_38(false);
                this.setLoadText("Loading Scenario #38_2");
            }
            else if (InitGame.iStepID == 121) {
                this.setLoadText("Loading Scenario #39");
            }
            else if (InitGame.iStepID == 122) {
                Game.mapScenarios.loadScenario_39(false);
                this.setLoadText("Loading Scenario #40");
            }
            else if (InitGame.iStepID == 123) {
                Game.mapScenarios.loadScenario_40(false);
                this.setLoadText("Loading Scenario #41");
            }
            else if (InitGame.iStepID == 124) {
                Game.mapScenarios.loadScenario_41(false);
                this.setLoadText("Loading Scenario #42");
            }
            else if (InitGame.iStepID == 125) {
                Game.mapScenarios.loadScenario_42();
                this.setLoadText("Loading Scenario #43");
            }
            else if (InitGame.iStepID == 126) {
                Game.mapScenarios.loadScenario_43();
                this.setLoadText("Loading Scenario #44");
            }
            else if (InitGame.iStepID == 127) {
                Game.mapScenarios.loadScenario_44();
                this.setLoadText("Loading Scenario #45");
            }
            else if (InitGame.iStepID == 128) {
                Game.mapScenarios.loadScenario_45();
                this.setLoadText("Loading Scenario #46");
            }
            else if (InitGame.iStepID == 129) {
                Game.mapScenarios.loadScenario_46();
                this.setLoadText("Loading Scenario #47");
            }
            else if (InitGame.iStepID == 130) {
                Game.mapScenarios.loadScenario_47();
                this.setLoadText("Loading Scenario #48");
            }
            else if (InitGame.iStepID == 131) {
                Game.mapScenarios.loadScenario_48();
                this.setLoadText("Loading Scenario #49");
            }
            else if (InitGame.iStepID == 132) {
                Game.mapScenarios.loadScenario_49(false);
                this.setLoadText("Loading Scenario #50");
            }
            else if (InitGame.iStepID == 133) {
                Game.mapScenarios.loadScenario_50(false);
                this.setLoadText("Loading Scenario #51");
            }
            else if (InitGame.iStepID == 134) {
                Game.mapScenarios.loadScenario_51(false);
                this.setLoadText("Loading Scenario #52");
            }
            else if (InitGame.iStepID == 135) {
                Game.mapScenarios.loadScenario_52();
                this.setLoadText("Loading Scenario #53");
            }
            else if (InitGame.iStepID == 136) {
                Game.mapScenarios.loadScenario_53();
                this.setLoadText("Loading Scenario #54");
            }
            else if (InitGame.iStepID == 137) {
                Game.mapScenarios.loadScenario_54();
                this.setLoadText("Loading Scenario #55");
            }
            else if (InitGame.iStepID == 138) {
                Game.mapScenarios.loadScenario_55();
                this.setLoadText("Loading Scenario #56");
            }
            else if (InitGame.iStepID == 139) {
                Game.mapScenarios.loadScenario_56();
                this.setLoadText("Loading Scenario #57");
            }
            else if (InitGame.iStepID == 140) {
                Game.mapScenarios.loadScenario_57();
                this.setLoadText("Loading Scenario #58");
            }
            else if (InitGame.iStepID == 141) {
                Game.mapScenarios.loadScenario_58();
                this.setLoadText("Loading Scenario #59");
            }
            else if (InitGame.iStepID == 142) {
                this.setLoadText("Loading Scenario #60");
            }
            else if (InitGame.iStepID == 143) {
                Game.mapScenarios.loadScenario_60();
                this.setLoadText("Loading Scenario #61");
            }
            else if (InitGame.iStepID == 144) {
                Game.mapScenarios.loadScenario_61();
                this.setLoadText("Loading Scenario #62 Formable Civilizations");
            }
            else if (InitGame.iStepID == 145) {
                Game.mapScenarios.loadScenario_62(false);
                this.setLoadText("Loading Scenario #63");
            }
            else if (InitGame.iStepID == 146) {
                Game.mapScenarios.loadScenario_63();
                this.setLoadText("Loading Scenario #64");
            }
            else if (InitGame.iStepID == 147) {
                Game.mapScenarios.loadScenario_64();
                this.setLoadText("Loading");
            }
            else if (InitGame.iStepID == 148) {
                ShipManager.loadShipLines();
                this.setLoadText("Loading");
            }
            else if (InitGame.iStepID == 149) {
                ShipManager.loadShipLines_Provinces();
                this.setLoadText("Loading Flags");
            }
            else if (InitGame.iStepID == 150) {
                Game.flagManager.loadData();
                Game.flagManager.initFlagEdit();
                this.setLoadText("Loading");
            }
            else if (InitGame.iStepID == 151) {
                this.setLoadText("Loading Events");
            }
            else if (InitGame.iStepID == 152) {
                EventsManager.loadScenarioEventsTag = Game.mapScenarios.lScenarios_TagsList.get(Game.scenarioID);
                EventsManager.loadEvents();
                EventsManager.loadEvents_Scenario();
                this.setLoadText("Loading HRE");
            }
            else if (InitGame.iStepID == 153) {
                HREManager.loadReformsImages();
                this.setLoadText("Loading Province BG #0");
            }
            else {
                if (InitGame.iStepID >= 154 && InitGame.iStepID < 154 + Game.getProvincesSize()) {
                    for (int i = 0; i < InitGame.numOfProvincesBGToLoad && InitGame.iStepID < 154 + Game.getProvincesSize(); ++i) {
                        Game.loadProvinceBG(InitGame.iStepID++ - 154);
                    }
                    this.setLoadText("Loading Province BG #" + (InitGame.iStepID - 154));
                    return;
                }
                if (this.justOnce) {
                    Game.mapScale.setEnableScaling(true);
                    Game.setUpdateProvincesInView(true);
                    CivilizationRegionsManager.updateRegionsInView = true;
                    Game.menuManager.setViewID(View.MAINMENU);
                    this.justOnce = false;
                    Game.gameThread.start();
                    Game.gameThreadTurns.start();
                    Game.gameThreadEvents.start();
                    Game.soundsManager.playStartMusic();
                    NewGame.setRandomCiv();
                }
                return;
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
        ++InitGame.iStepID;
    }
    
    private final void loadImages_1() {
        Images.randomCivilizationFlag = ImageManager.addImage("gfx/flagsXH/ran.png");
        Images.map_border = ImageManager.addImage("gfx/map/mapBorder.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.Repeat);
        Images.outsideMap = ImageManager.addImage("gfx/map/outsideMap.png", Pixmap.Format.RGB888, Texture.TextureFilter.Linear, Texture.TextureWrap.Repeat);
        Images.waves = ImageManager.addImage("gfx/map/waves.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.Repeat);
        Images.click = ImageManager.addImage("gfx/map/click.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.Repeat);
        Images.noise = ImageManager.addImage("ui/other/noise.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.Repeat);
        Images.activeSort = ImageManager.addImage("ui/other/activeSort.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.Repeat);
        Images.line_11 = ImageManager.addImage("ui/lines/line_11.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.Repeat);
        Images.line_22 = ImageManager.addImage("ui/lines/line_22.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.Repeat);
        Images.imgLine_32 = ImageManager.loadImage("ui/lines/line_32.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.Repeat);
        Images.line_32 = ImageManager.addImage("ui/lines/line_32.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.Repeat);
        Images.line_33 = ImageManager.addImage("ui/lines/line_33.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.Repeat);
        Images.line_44 = ImageManager.addImage("ui/lines/line_44.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.Repeat);
        Images.line_26 = ImageManager.addImage("ui/lines/line_26.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.Repeat);
        Images.line_62 = ImageManager.addImage("ui/lines/line_62.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.Repeat);
        Images.line_32_vertical = ImageManager.addImage("ui/lines/line_32_vertical.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.Repeat);
        Images.circle_55 = ImageManager.addImage("ui/graph/circle_55.png");
        Images.mainTitle = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "mainMenu/" + "mainTitle.png");
        Images.mainBox2 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "mainMenu/" + "mainBox2.png");
        Images.android = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "mainMenu/" + "android.png");
        Images.app = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "mainMenu/" + "app.png");
        Images.pc = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "mainMenu/" + "pc.png");
        Images.yt = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "mainMenu/" + "yt.png");
        Images.title1Red = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "title1Red.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.title2 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "title2.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.insideTop = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "insideTop.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.insideBot = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "insideBot.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.newGameOver = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "newGameOver.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.recruitArmyOver = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "recruitArmyOver.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.provinceInfoOver = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "provinceInfoOver.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.provinceInfoOver1 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "provinceInfoOver1.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.provinceInfoOver2 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "provinceInfoOver2.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.provinceInfoOverCapital = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "provinceInfoOverCapital.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.provinceInfoNeutralOver = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "provinceInfoNeutralOver.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.civInfoOver = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "civInfoOver.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.rulerOver = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "rulerOver.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.civOptionsOver = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "civOptionsOver.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.budgetOver = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "budgetOver.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.goodsOver = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "goodsOver.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.outlinerOver = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "outlinerOver.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.warViewOver = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "warViewOver.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.title500 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "title500.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.insideTop500 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "insideTop500.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.insideBot500 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "insideBot500.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.title530 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "title530.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.insideTop530 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "insideTop530.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.insideBot530 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "insideBot530.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.title630 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "title630.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.insideTop630 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "insideTop630.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.insideBot630 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "insideBot630.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
    }
    
    private final void loadImages_2() {
        Images.infoBox2 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "infoBox/" + "infoBox2.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.infoBox = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "infoBox/" + "infoBox.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.infoTop = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "infoBox/" + "infoTop.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.infoBot = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "infoBox/" + "infoBot.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.infoLeft = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "infoBox/" + "infoLeft.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.infoRight = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "infoBox/" + "infoRight.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.infoLegacy = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "infoBox/" + "infoLegacy.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.infoCrown = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "infoBox/" + "infoCrown.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.infoTechnology = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "infoBox/" + "infoTechnology.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.infoGeneral = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "infoBox/" + "infoGeneral.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.infoAtomic = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "infoBox/" + "infoAtomic.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.infoAdvantage = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "infoBox/" + "infoAdvantage.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.infoDiplomacy = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "infoBox/" + "infoDiplomacy.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.infoWar = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "infoBox/" + "infoWar.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.infoUnrest = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "infoBox/" + "infoUnrest.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.infoTechnology2 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "infoBox/" + "infoTechnology2.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.messageBG = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "message/" + "messageBG.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.messageMask = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "message/" + "messageMask.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.messageOver = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "message/" + "messageOver.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.warBG = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "message/" + "warBG.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.warMask = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "message/" + "warMask.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.warOver = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "message/" + "warOver.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.currentSituationBG = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "message/" + "currentSituationBG.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.infoMaskLeft = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "infoBox/" + "infoMaskLeft.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.infoMaskRight = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "infoBox/" + "infoMaskRight.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.battleOver = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "battleOver0.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.battleOver1 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "battleOver1.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.battleOver2 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "battleOver2.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.battleOver3 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "battleOver3.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.siegeOver0 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "siegeOver0.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.siegeOver1 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "siegeOver1.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.siegeOver2 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "siegeOver2.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.siegeOverFort0 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "siegeOverFort0.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.siegeOverFort1 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "siegeOverFort1.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.siegeOverFort2 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "siegeOverFort2.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.armyCampOver0 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "armyCampOver0.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.armyCampOver1 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "armyCampOver1.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.armyCampOver2 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "armyCampOver2.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.armyCampOver3 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "armyCampOver3.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.armyMove0 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "armyMove0.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.armyMove1 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "armyMove1.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.armyMove2 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "armyMove2.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.armyMove3 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "armyMove3.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.armyAtSea0 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "armyAtSea0.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.armyAtSea1 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "armyAtSea1.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.armyAtSea2 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "armyAtSea2.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.armyAtSea3 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "armyAtSea3.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.title400 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "title400.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.insideTop400 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "insideTop400.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.insideBot400 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "insideBot400.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.title600 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "title600.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.insideTop600 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "insideTop600.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.insideBot600 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "insideBot600.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.insideBot600_2 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "insideBot600_2.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.title580 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "title580.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.insideTop580 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "insideTop580.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.insideBot580 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "insideBot580.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.title698 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "title698.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.insideTop698 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "insideTop698.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.insideBot698 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "insideBot698.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.title928 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "title928.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.insideTop928 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "insideTop928.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.insideBot928 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "insideBot928.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.insideTechTree = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "insideTechTree.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.titleTechTree = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "titleTechTree.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.titleTechTreeOver = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "titleTechTreeOver.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.Repeat);
    }
    
    public String getArmies_Path() {
        return loadArmy_Default() ? "army26/" : "army32/";
    }
    
    private final void loadImages_3() {
        Images.graphBG = ImageManager.addImage("ui/graph/graphBG.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.Repeat);
        Images.army = ImageManager.addImage("ui/army/" + this.getArmies_Path() + "army.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.armyAlly = ImageManager.addImage("ui/army/" + this.getArmies_Path() + "armyAlly.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.armyEnemy = ImageManager.addImage("ui/army/" + this.getArmies_Path() + "armyEnemy.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.armyPlayer0 = ImageManager.addImage("ui/army/" + this.getArmies_Path() + "armyPlayer0.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.armyPlayer1 = ImageManager.addImage("ui/army/" + this.getArmies_Path() + "armyPlayer1.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.armyPlayer2 = ImageManager.addImage("ui/army/" + this.getArmies_Path() + "armyPlayer2.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.armyPlayer3 = ImageManager.addImage("ui/army/" + this.getArmies_Path() + "armyPlayer3.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.armyPlayer4 = ImageManager.addImage("ui/army/" + this.getArmies_Path() + "armyPlayer4.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.armyPlayer5 = ImageManager.addImage("ui/army/" + this.getArmies_Path() + "armyPlayer5.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.armyPlayer6 = ImageManager.addImage("ui/army/" + this.getArmies_Path() + "armyPlayer6.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.armyPlayer7 = ImageManager.addImage("ui/army/" + this.getArmies_Path() + "armyPlayer7.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.armyPlayer8 = ImageManager.addImage("ui/army/" + this.getArmies_Path() + "armyPlayer8.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.armyPlayer9 = ImageManager.addImage("ui/army/" + this.getArmies_Path() + "armyPlayer9.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.armyPlayer10 = ImageManager.addImage("ui/army/" + this.getArmies_Path() + "armyPlayer10.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.armyPlayer11 = ImageManager.addImage("ui/army/" + this.getArmies_Path() + "armyPlayer11.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.armyPlayer12 = ImageManager.addImage("ui/army/" + this.getArmies_Path() + "armyPlayer12.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.armyPlayer13 = ImageManager.addImage("ui/army/" + this.getArmies_Path() + "armyPlayer13.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.armyPlayer14 = ImageManager.addImage("ui/army/" + this.getArmies_Path() + "armyPlayer14.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.terrainSmall = ImageManager.addImage("ui/army/terrainSmall.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.armyRetreat = ImageManager.addImage("ui/army/" + this.getArmies_Path() + "armyRetreat.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.flagCapitalMask = ImageManager.addImage("ui/army/flagCapitalMask.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.flagCapitalOver = ImageManager.addImage("ui/army/flagCapitalOver.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.flagCapitalWar = ImageManager.addImage("ui/army/flagCapitalWar_m.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.flagCapitalMask_s = ImageManager.addImage("ui/army/flagCapitalMask_s.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.flagCapitalOver_s = ImageManager.addImage("ui/army/flagCapitalOver_s.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.flagCapitalWar_s = ImageManager.addImage("ui/army/flagCapitalWar_s.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.flagCapitalMask_l = ImageManager.addImage("ui/army/flagCapitalMask_l.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.flagCapitalOver_l = ImageManager.addImage("ui/army/flagCapitalOver_l.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.flagCapitalWar_l = ImageManager.addImage("ui/army/flagCapitalWar_l.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.flagCapitalOver_Low = ImageManager.addImage("ui/army/flagCapitalOver_Low.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.flagCapitalOver_s_Low = ImageManager.addImage("ui/army/flagCapitalOver_s_Low.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.flagCapitalOver_l_Low = ImageManager.addImage("ui/army/flagCapitalOver_l_Low.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.armyUnitsFrame = ImageManager.addImage("ui/army/armyUnitsFrame.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.battleIcon0 = ImageManager.addImage("ui/army/" + this.getArmies_Path() + "battleIcon0.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.battleIcon1 = ImageManager.addImage("ui/army/" + this.getArmies_Path() + "battleIcon1.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.battleIcon2 = ImageManager.addImage("ui/army/" + this.getArmies_Path() + "battleIcon2.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.battleIcon3 = ImageManager.addImage("ui/army/" + this.getArmies_Path() + "battleIcon3.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.battleIcon4 = ImageManager.addImage("ui/army/" + this.getArmies_Path() + "battleIcon4.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.battleIcon5 = ImageManager.addImage("ui/army/" + this.getArmies_Path() + "battleIcon5.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.armyLeft = ImageManager.addImage("ui/army/" + this.getArmies_Path() + "armyLeft.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        for (int i = 0; i < GameValues.inGame.ARMY_LEFT_IMAGES + 1; ++i) {
            Images.armyLeft3.add(ImageManager.loadImage("ui/army/" + this.getArmies_Path() + i + ".png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge));
        }
        Images.armyLeft3_Width = Images.armyLeft3.get(0).getWidth();
        Images.terrainFrame = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "menus/" + "terrainFrame.png");
        Images.unitsFrame = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "units/" + "unitsFrame.png");
        Images.unitsFrameSmall = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "units/" + "unitsFrameSmall.png");
        Images.unitsFrameBattle = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "units/" + "unitsFrameBattle.png");
        Images.unitsPlus = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "units/" + "unitsPlus.png");
        Images.battleArmy0 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "units/" + "battleArmy0.png");
        Images.battleArmy1 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "units/" + "battleArmy1.png");
        Images.battleArmy2 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "units/" + "battleArmy2.png");
        Images.generalFrame = ImageManager.addImage("game/generals/generals/" + CFG.getRescouresPath_Short() + "generalFrame.png");
        Images.generalFrameBattle = ImageManager.addImage("game/generals/generals/" + CFG.getRescouresPath_Short() + "generalFrameBattle.png");
        Images.pinnedGeneralMask = ImageManager.addImage("game/generals/generals/" + CFG.getRescouresPath_Short() + "pinnedGeneralMask.png");
        Images.pinnedGeneralFrame = ImageManager.addImage("game/generals/generals/" + CFG.getRescouresPath_Short() + "pinnedGeneralFrame.png");
        Images.buildingsFrame = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buildings/" + "buildingsFrame.png");
        Images.buildingsFrameSmall = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buildings/" + "buildingsFrameSmall.png");
        Images.buildButton = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buildings/" + "buildButton.png");
        Images.wonderFrame = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buildings/" + "wonderFrame.png");
        Images.armyGeneralAcademy = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buildings/" + "armyGeneralAcademy.png");
        Images.armyMilitaryAcademy = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buildings/" + "armyMilitaryAcademy.png");
        Images.buildingMoveCapital = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buildings/" + "buildingMoveCapital.png");
        Images.nuclearReactor = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buildings/" + "nuclearReactor.png");
        Images.atomicBomb = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buildings/" + "atomicBomb.png");
        Images.supremeCourt = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buildings/" + "supremeCourt.png");
        Images.armyGeneralAcademyBig = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buildings/" + "armyGeneralAcademyBig.png");
        Images.armyMilitaryAcademyBig = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buildings/" + "armyMilitaryAcademyBig.png");
        Images.supremeCourtBig = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buildings/" + "supremeCourtBig.png");
        Images.nuclearReactorBig = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buildings/" + "nuclearReactorBig.png");
        Images.atomicBombBig = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buildings/" + "atomicBombBig.png");
        for (int i = 0; i < GameValues.capital.CAPITAL_IMAGES; ++i) {
            Images.buildingCapital.add(ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buildings/" + "buildingCapital" + i + ".png"));
            Images.buildingCapitalBig.add(ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buildings/" + "buildingCapitalBig" + i + ".png"));
        }
        this.loadArmiesFlagData();
    }
    
    private final void loadArmiesFlagData() {
        try {
            final FileHandle tempFileT = FileManager.loadFile("ui/army/" + this.getArmies_Path() + "flag.txt");
            final String[] tempSplit = tempFileT.readString().split(";");
            ProvinceDrawArmy.armyFlagPosX = Integer.parseInt(tempSplit[0]);
            ProvinceDrawArmy.armyFlagPosY = Integer.parseInt(tempSplit[1]);
            ProvinceDrawArmy.armyFlagPosWidth = Integer.parseInt(tempSplit[2]);
            ProvinceDrawArmy.armyFlagPosHeight = Integer.parseInt(tempSplit[3]);
        }
        catch (final GdxRuntimeException ex) {
            CFG.exceptionStack((Throwable)ex);
        }
    }
    
    private final void loadImages_4() {
        Images.rulerFrame = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "ruler/" + "rulerFrame.png");
        Images.capitalLeft = ImageManager.addImage("ui/army/capitalLeft.png");
        Images.capitalLeftOver = ImageManager.addImage("ui/army/capitalLeftOver.png");
        Images.capitalRight = ImageManager.addImage("ui/army/capitalRight.png");
        Images.armyFlag = ImageManager.addImage("ui/army/" + this.getArmies_Path() + "armyFlag.png");
        Images.armyMorale = ImageManager.addImage("ui/army/" + this.getArmies_Path() + "armyMorale.png");
        Images.unitsFrameMapMask = ImageManager.addImage("ui/army/unitsFrameMapMask.png");
        Images.unitsFrameMap = ImageManager.addImage("ui/army/unitsFrameMap.png");
        Images.progressBarFrame = ImageManager.addImage("ui/army/progressBarFrame.png");
        Images.progressBarFrameMask = ImageManager.addImage("ui/army/progressBarFrameMask.png");
        Images.progressBarFrame2 = ImageManager.addImage("ui/army/progressBarFrame2.png");
        Images.progressBarFrame2Mask = ImageManager.addImage("ui/army/progressBarFrame2Mask.png");
        Images.armyHover = ImageManager.addImage("ui/army/" + this.getArmies_Path() + "armyHover.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.armyHoverRight = ImageManager.addImage("ui/army/" + this.getArmies_Path() + "armyHoverRight.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.armyActive = ImageManager.addImage("ui/army/" + this.getArmies_Path() + "armyActive.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.flag_rect = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "flags/" + "flagRect.png");
        Images.flagRect2 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "flags/" + "flagRect2.png");
        Images.flagRect2Mask = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "flags/" + "flagRect2Mask.png");
        Images.titleFlagOver = ImageManager.addImage("ui/other/titleFlagOver.png");
        CFG.CIV_FLAG_WIDTH = ImageManager.getImage(Images.flag_rect).getWidth();
        CFG.CIV_FLAG_HEIGHT = ImageManager.getImage(Images.flag_rect).getHeight();
        Images.flagMaskDefault = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "flags/" + "flagMask.png");
        Images.flagOverDefault = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "flags/" + "flagOver.png");
        Images.flagDiplomacyMask = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "flags/" + "flagDiplomacyMask.png");
        Images.flagDiplomacyOver = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "flags/" + "flagDiplomacyOver.png");
        Images.flagDiplomacyArrow = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "flags/" + "flagDiplomacyArrow.png");
        Images.noAdvisor = ImageManager.addImage("game/advisors/advisors/" + CFG.getRescouresPath_Short() + "noAdvisor.png");
        Images.moveLine = ImageManager.addImage("gfx/move/moveLine_32px.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
        Images.wiki = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "wiki.png");
        Images.defense = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "defense.png");
        Images.movementSpeed = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "movementSpeed.png");
        Images.movementCancel = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "movementCancel.png");
        Images.pin = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "pin.png");
        Images.attack = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "attack.png");
        Images.gold = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "gold.png");
        Images.goldPositive = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "goldPositive.png");
        Images.goldNegative = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "goldNegative.png");
        Images.inflation = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "inflation.png");
        Images.time = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "time.png");
        Images.nuke = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "nuke.png");
        Images.ship = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "ship.png");
        Images.government = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "government.png");
        Images.law = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "law.png");
        Images.advantages = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "advantages.png");
        Images.siege = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "siege.png");
        Images.fort = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "fort.png");
        Images.battle = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "battle.png");
        Images.missions = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "missions.png");
        Images.encyclopedia = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "encyclopedia.png");
        Images.upgrade = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "upgrade.png");
        Images.fortDefense = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "fortDefense.png");
        Images.fortDefense_2 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "fortDefense_2.png");
        Images.technology = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "technology.png");
        Images.technology2 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "technology2.png");
        Images.world = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "world.png");
        Images.message = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "message.png");
        Images.hre = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "hre.png");
        Images.hreBig = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "hreBig.png");
        Images.invasion = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "invasion.png");
    }
    
    private final void loadImages_5() {
        Images.heart = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "heart.png");
        Images.war = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "war.png");
        Images.warBig = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "warBig.png");
        Images.defensivePactBig = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "defensivePactBig.png");
        Images.insultBig = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "insultBig.png");
        Images.weariness = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "weariness.png");
        Images.relations = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "relations.png");
        Images.relationsDown = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "relationsDown.png");
        Images.relationsUp = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "relationsUp.png");
        Images.insult = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "insult.png");
        Images.battleBig = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "battleBig.png");
        Images.interveneBig = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "interveneBig.png");
        Images.compare = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "compare.png");
        Images.compareBig = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "compareBig.png");
        Images.intervene = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "intervene.png");
        Images.nonAggressionBig = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "nonAggressionBig.png");
        Images.militaryAccessBig = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "militaryAccessBig.png");
        Images.allianceBig = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "allianceBig.png");
        Images.rivalsBig = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "rivalsBig.png");
        Images.guaranteeIndependenceBig = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "guaranteeIndependenceBig.png");
        Images.giftBig = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "giftBig.png");
        Images.economyUp = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "economyUp.png");
        Images.economyDown = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "economyDown.png");
        Images.economyUp2 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "economyUp2.png");
        Images.economyDown2 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "economyDown2.png");
        Images.tax = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "tax.png");
        Images.taxUp = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "taxUp.png");
        Images.council = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "council.png");
        Images.brush = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "brush.png");
        Images.victoryPoints = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "victoryPoints.png");
        Images.rank = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "rank.png");
        Images.x = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "x.png");
        Images.v = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "v.png");
        Images.religion = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "religion.png");
        Images.swords = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "swords.png");
        Images.core = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "core.png");
        Images.warPeace = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "warPeace.png");
        Images.warPeaceBig = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "warPeaceBig.png");
        Images.warDemands = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "warDemands.png");
        Images.warDemandsBig = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "warDemandsBig.png");
        Images.warSurrender = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "warSurrender.png");
        Images.truce = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "truce.png");
        Images.spy = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "spy.png");
        Images.spyBig = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "spyBig.png");
        Images.frontLine = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "frontLine.png");
        Images.aggressiveExpansion = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "aggressiveExpansion.png");
        Images.mercenaries = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "mercenaries.png");
        Images.sandbox = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "sandbox.png");
        Images.goldenGold = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "goldenGold.png");
        Images.goldenGreen = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "goldenGreen.png");
        Images.goldenBlue = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "goldenBlue.png");
        Images.stats = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "stats.png");
        Images.development = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "development.png");
        Images.rankBronze = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "rankBronze.png");
        Images.rankSilver = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "rankSilver.png");
        Images.rankGold = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "rankGold.png");
        Images.rankBlack = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "rankBlack.png");
        Images.diplomacy = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "diplomacy.png");
        Images.legacy = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "legacy.png");
        Images.corruption = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "corruption.png");
        Images.disbandArmy = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "disbandArmy.png");
        Images.splitArmy = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "splitArmy.png");
        Images.mergeArmy = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "mergeArmy.png");
        Images.reorganizeArmy = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "reorganizeArmy.png");
        Images.disbandArmy2 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "disbandArmy2.png");
        Images.splitArmy2 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "splitArmy2.png");
        Images.mergeArmy2 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "mergeArmy2.png");
        Images.reorganizeArmy2 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "reorganizeArmy2.png");
        Images.disbandArmy3 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "disbandArmy3.png");
        Images.splitArmy3 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "splitArmy3.png");
        Images.mergeArmy3 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "mergeArmy3.png");
        Images.reorganizeArmy3 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "reorganizeArmy3.png");
        Images.gift = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "gift.png");
        Images.loan = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "loan.png");
        Images.nonAggression = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "nonAggression.png");
        Images.nonAggressionExpired = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "nonAggressionExpired.png");
        Images.defensivePactExpired = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "defensivePactExpired.png");
        Images.allianceExpired = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "allianceExpired.png");
        Images.truceExpired = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "truceExpired.png");
        Images.militaryAccessExpired = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "militaryAccessExpired.png");
        Images.guaranteeIndependenceExpired = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "guaranteeIndependenceExpired.png");
        Images.rivals = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "rivals.png");
        Images.alliance = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "alliance.png");
        Images.militaryAccess = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "militaryAccess.png");
        Images.militaryAccess2 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "militaryAccess2.png");
        Images.guaranteeIndependence = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "guaranteeIndependence.png");
        Images.buildings = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "buildings.png");
        Images.construction = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "construction.png");
        Images.currentSituation = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "currentSituation.png");
        Images.plunder = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "plunder.png");
        Images.peace = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "peace.png");
        Images.disease = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "disease.png");
        Images.vassal = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "vassal.png");
        Images.vassalBig = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "vassalBig.png");
        Images.resourceNone = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "resourceNone.png");
        Images.skill = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "skill.png");
        Images.civRank_0 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "civRank_0.png");
        Images.civRank_1 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "civRank_1.png");
        Images.civRank_2 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "civRank_2.png");
        Images.civRank_3 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "civRank_3.png");
        Images.civRank_4 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "civRank_4.png");
        Images.civRank_5 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "civRank_5.png");
        Images.capital = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "capital.png");
        Images.stability = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "stability.png");
        Images.civStability = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "civStability.png");
        if (ImageManager.getImage(Images.disbandArmy).getWidth() > ButtonArmyIcon.maxWidth) {
            ButtonArmyIcon.maxWidth = ImageManager.getImage(Images.disbandArmy).getWidth();
        }
        if (ImageManager.getImage(Images.splitArmy).getWidth() > ButtonArmyIcon.maxWidth) {
            ButtonArmyIcon.maxWidth = ImageManager.getImage(Images.splitArmy).getWidth();
        }
        if (ImageManager.getImage(Images.mergeArmy).getWidth() > ButtonArmyIcon.maxWidth) {
            ButtonArmyIcon.maxWidth = ImageManager.getImage(Images.mergeArmy).getWidth();
        }
        if (ImageManager.getImage(Images.reorganizeArmy).getWidth() > ButtonArmyIcon.maxWidth) {
            ButtonArmyIcon.maxWidth = ImageManager.getImage(Images.reorganizeArmy).getWidth();
        }
    }
    
    private final void loadImages_6() {
        Images.information = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "information.png");
        Images.arrowUpDown = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "minimap/" + "arrowUpDown.png");
        Images.mapModesTerrain = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "minimap/" + "mapModesTerrain.png");
        Images.mapModesCivs = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "minimap/" + "mapModesCivs.png");
        Images.mapModesGoods = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "minimap/" + "mapModesGoods.png");
        Images.mapModesWonders = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "minimap/" + "mapModesWonders.png");
        Images.mapModesReligion = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "minimap/" + "mapModesReligion.png");
        Images.mapModesInfrastructure = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "minimap/" + "mapModesInfrastructure.png");
        Images.buttonPlay = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buttons/" + "buttonPlay.png");
        Images.buttonPlay2 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buttons/" + "buttonPlay2.png");
        Images.missionMask = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buttons/" + "missionMask.png");
        Images.missionOver = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buttons/" + "missionOver.png");
        Images.techGray = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buttons/" + "techGray.png");
        Images.techBlue = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buttons/" + "techBlue.png");
        Images.techResearched = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buttons/" + "techResearched.png");
        Images.techAvailable = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buttons/" + "techAvailable.png");
        Images.techCorner = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buttons/" + "techCorner.png");
        Images.techOver = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buttons/" + "techOver.png");
        Images.techMask = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buttons/" + "techMask.png");
        Images.techMaskSquare = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buttons/" + "techMaskSquare.png");
        Images.techBattleWidth = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buttons/" + "techBattleWidth.png");
        Images.techEmpty = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buttons/" + "techEmpty.png");
        Images.techLegacy = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buttons/" + "techLegacy.png");
        Images.techGeneral = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buttons/" + "techGeneral.png");
        Images.techDiscipline = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buttons/" + "techDiscipline.png");
        Images.techMorale = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buttons/" + "techMorale.png");
        Images.techDefense = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buttons/" + "techDefense.png");
        Images.techSea = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buttons/" + "techSea.png");
        Images.techNuke = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buttons/" + "techNuke.png");
        Images.techAttack = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buttons/" + "techAttack.png");
        Images.lawOver = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buttons/" + "lawOver.png");
        Images.lawMask = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buttons/" + "lawMask.png");
        Images.advantagesOver = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buttons/" + "advantagesOver.png");
        Images.advantagesSmallOver = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buttons/" + "advantagesSmallOver.png");
        Images.advantagesSmallMask = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buttons/" + "advantagesSmallMask.png");
        Images.progressBar = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buttons/" + "progressBar.png");
        Images.progressBarOver = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buttons/" + "progressBarOver.png");
        Images.btnh_close = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buttons/" + "close_h.png");
        Images.boxEdge = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "boxes/" + "boxEdge.png");
        Images.boxDetails = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "boxes/" + "boxDetails.png");
        Images.boxBIG = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "boxes/" + "bigBox.png");
        Images.boxBIG_Red = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "boxes/" + "bigBoxRed.png");
        Images.bigBoxCorner = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "boxes/" + "bigBoxCorner.png");
        Images.bigBoxCorner2 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "boxes/" + "bigBoxCorner2.png");
        Images.boxSimple = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "boxes/" + "boxSimple.png");
    }
    
    private final void loadImagesInit() {
        Images.boxTitle = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "boxes/" + "boxTitle.png");
        Images.mainBox = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "mainMenu/" + "mainBox.png");
        Images.boxBGCorner = ImageManager.addImage("ui/other/boxBGCorner.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.Repeat);
        Images.boxBGLine = ImageManager.addImage("ui/other/boxBGLine.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.Repeat);
        Images.boxBGLineH = ImageManager.addImage("ui/other/boxBGLineH.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.Repeat);
        Images.boxBGLinePix = ImageManager.addImage("ui/other/boxBGLinePix.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.buttonGame = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buttons/" + "buttonGame.png");
        Images.buttonGameH = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "buttons/" + "buttonGameH.png");
        Images.statsBG = ImageManager.loadImage("ui/other/statsBG.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.statsBG2 = ImageManager.loadImage("ui/other/statsBG2.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.statsRectBG = ImageManager.loadImage("ui/" + CFG.getRescouresPath() + "boxes/" + "statsRectBG.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.statsRectBGBorder = ImageManager.loadImage("ui/" + CFG.getRescouresPath() + "boxes/" + "statsRectBGBorder.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
    }
    
    private final void loadImages_7() {
        Renderer.boxBGExtraY = ImageManager.getImage(Images.boxBGLine).getWidth();
        Images.sliderEdge = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "boxes/" + "sliderEdge.png");
        Images.flagBGClassic = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "top/" + "flagBG.png");
        Images.topStatsClassic = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "top/" + "topStats.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.topStats2Classic = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "top/" + "topStats2.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.leftSideBarClassic = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "top/" + "leftSideBar.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.flagBGUQ = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "top/" + "flagBGUQ.png");
        Images.topStatsUQ = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "top/" + "topStatsUQ.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.topStats2UQ = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "top/" + "topStats2UQ.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.leftSideBarUQ = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "top/" + "leftSideBarUQ.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.flagBG = Images.flagBGClassic;
        Images.topStats = Images.topStatsClassic;
        Images.topStats2 = Images.topStats2Classic;
        Images.leftSideBar = Images.leftSideBarClassic;
        Images.levelFrame = ImageManager.addImage("ui/level/levelFrame.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.levelMask = ImageManager.addImage("ui/level/levelMask.png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
        Images.play = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "top/" + "play.png");
        Images.pause = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "top/" + "pause.png");
        Images.plus = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "top/" + "plus.png");
        Images.minus = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "top/" + "minus.png");
        Images.outliner = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "top/" + "outliner.png");
        Images.flagBelow = ImageManager.addImage("ui/flag/" + CFG.getRescouresPath_Short() + "flagBelow.png");
        Images.eventCorner = ImageManager.addImage("game/events/images/" + CFG.getRescouresPath_Short() + "corner.png");
        final FileHandle tempFileT2 = FileManager.loadFile("gfx/map/nuke/numOfImages.txt");
        final int tNukeImages = Integer.parseInt(tempFileT2.readString());
        for (int i = 0; i < tNukeImages; ++i) {
            Images.nukeImg.add(ImageManager.addImage("gfx/map/nuke/" + i + ".png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.Repeat));
        }
        Images.nukeIMGSize = tNukeImages;
        final Json json = new Json();
        final FileHandle file = FileManager.loadFile("ui/flag/Config.json");
        Game.flagData = (Game.FlagData)json.fromJson((Class)Game.FlagData.class, file);
        for (int j = 0; j < Game.flagData.NumOfFlags; ++j) {
            Images.flagMask.add(ImageManager.addImage("ui/flag/" + CFG.getRescouresPath_Short() + j + "/flagMask.png"));
            Images.flagMaskLord.add(ImageManager.addImage("ui/flag/" + CFG.getRescouresPath_Short() + j + "/flagMaskLord.png"));
            Images.flagOver.add(ImageManager.addImage("ui/flag/" + CFG.getRescouresPath_Short() + j + "/flagOver.png"));
        }
        try {
            final FileHandle tempFileT3 = FileManager.loadFile("ui/" + CFG.getRescouresPath() + "boxes/" + "boxTitleBorder.txt");
            Images.boxTitleBORDERWIDTH = Integer.parseInt(tempFileT3.readString());
        }
        catch (final GdxRuntimeException ex) {
            CFG.exceptionStack((Throwable)ex);
        }
        try {
            final FileHandle tempFileT3 = FileManager.loadFile("ui/" + CFG.getRescouresPath() + "buttons/" + "buttonPlayPadding.txt");
            Images.buttonPlayPADDING = Integer.parseInt(tempFileT3.readString());
        }
        catch (final GdxRuntimeException ex) {
            CFG.exceptionStack((Throwable)ex);
        }
        try {
            final FileHandle tempFileT3 = FileManager.loadFile("ui/" + CFG.getRescouresPath() + "message/" + "messageMaskY.txt");
            MessageButton.messageMaskY = Integer.parseInt(tempFileT3.readString());
        }
        catch (final GdxRuntimeException ex) {
            CFG.exceptionStack((Throwable)ex);
        }
        try {
            final FileHandle tempFileT3 = FileManager.loadFile("ui/" + CFG.getRescouresPath() + "message/" + "warMaskY.txt");
            MessageWar.warMaskY = Integer.parseInt(tempFileT3.readString());
        }
        catch (final GdxRuntimeException ex) {
            CFG.exceptionStack((Throwable)ex);
        }
    }
    
    private final void loadImages_8() {
        Images.build = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "build.png");
        Images.dice = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "dice.png");
        Images.manpower = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "manpower.png");
        Images.manpower2 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "manpower2.png");
        Images.manpower3 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "manpower3.png");
        Images.manpowerUp = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "manpowerUp.png");
        Images.manpower2Up = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "manpower2Up.png");
        Images.manpower3Up = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "manpower3Up.png");
        Images.manpowerTime = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "manpowerTime.png");
        Images.manpower2Time = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "manpower2Time.png");
        Images.manpower3Time = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "manpower3Time.png");
        Images.morale = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "morale.png");
        Images.population = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "population.png");
        Images.buildTime = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "builtTime.png");
        Images.skull = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "skull.png");
        Images.devastation = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "devastation.png");
        Images.loot = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "loot.png");
        Images.general = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "general.png");
        Images.defensivePact = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "defensivePact.png");
        Images.armyMaintenance = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "armyMaintenance.png");
        Images.regimentsLimit = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "regimentsLimit.png");
        Images.populationGrowth = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "populationGrowth.png");
        Images.populationUp = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "populationUp.png");
        Images.economy = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "economy.png");
        Images.economy2 = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "economy2.png");
        Images.ai = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "ai.png");
        Images.revolutionRisk = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "revolutionRisk.png");
        Images.infrastructure = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "infrastructure.png");
        Images.infrastructureUp = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "infrastructureUp.png");
        Images.goods = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "goods.png");
        Images.provinces = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "provinces.png");
        Images.console = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "console.png");
        Images.settings = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "settings.png");
        Images.save = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "save.png");
        Images.discipline = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "discipline.png");
        Images.battleWidth = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "battleWidth.png");
        Images.retreat = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "retreat.png");
        Images.center = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "center.png");
        Images.conqueredCivs = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "icons/" + "conqueredCivs.png");
        Images.rebelsFlag = ImageManager.addImage("gfx/rebels/flag.png");
    }
    
    private final void loadSparks() {
        try {
            final FileHandle tempFileT = FileManager.loadFile("gfx/sparks/numberOfImages.txt");
            Images.SPARKS_SIZE = Integer.parseInt(tempFileT.readString());
            for (int i = 0; i < Images.SPARKS_SIZE; ++i) {
                Images.sparks.add(ImageManager.loadImage("gfx/sparks/" + i + ".png", Pixmap.Format.RGBA8888, Texture.TextureFilter.Linear, Texture.TextureWrap.Repeat));
            }
            Images.sparkWidth = Images.sparks.get(0).getWidth();
            Images.sparkHeight = Images.sparks.get(0).getHeight();
        }
        catch (final GdxRuntimeException ex) {
            CFG.exceptionStack((Throwable)ex);
        }
        Images.SPARKS_SIZE = Images.sparks.size();
    }
    
    static {
        InitGame.background = null;
        InitGame.backgroundID = -1;
        InitGame.backgroundSize = 1;
        InitGame.backgroundHeight = -1;
        InitGame.iStepID = 0;
        InitGame.iNumOfSteps = 153;
        InitGame.numOfProvincesBGToLoad = 60;
    }
}
