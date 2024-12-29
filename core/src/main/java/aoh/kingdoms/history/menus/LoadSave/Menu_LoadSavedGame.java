// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menus.LoadSave;

import aoh.kingdoms.history.map.province.ProvinceDraw;
import aoh.kingdoms.history.menu.View;
import aoh.kingdoms.history.map.map.MapScale;
import aoh.kingdoms.history.menus.NewGame.NewGame;
import aoh.kingdoms.history.mainGame.AI.Colonization.AI_Colonization;
import aoh.kingdoms.history.map.civilization.CivilizationRanking;
import aoh.kingdoms.history.map.ResourcesManager;
import aoh.kingdoms.history.map.map.MapScenarios;
import aoh.kingdoms.history.map.civilization.CivilizationRegionsManager;
import aoh.kingdoms.history.map.SiegeManager;
import aoh.kingdoms.history.mainGame.SaveLoad.LoadSavedGameManager;
import aoh.kingdoms.history.textures.ImageManager;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menus.InitGame;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class Menu_LoadSavedGame extends Menu
{
    public int iStepID;
    public int iNumOfSteps;
    public String loadingName;
    
    public Menu_LoadSavedGame() {
        this.iStepID = 0;
        this.iNumOfSteps = 250;
        this.loadingName = "";
        this.iStepID = 0;
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements, true);
        this.setLoadText(Game.lang.get("Loading") + " #1");
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        this.loadAction();
        if (InitGame.background != null) {
            oSB.setColor(new Color(0.047058824f, 0.047058824f, 0.047058824f, 1.0f));
            Images.pix.draw(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.GAME_HEIGHT);
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, this.iStepID / (float)this.iNumOfSteps * 0.25f));
            InitGame.background.draw(oSB, iTranslateX + (CFG.GAME_WIDTH - InitGame.backgroundWidth) / 2, iTranslateY + (CFG.GAME_HEIGHT - InitGame.backgroundHeight) / 2, InitGame.backgroundWidth, InitGame.backgroundHeight);
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.95f + this.iStepID / (float)this.iNumOfSteps * 0.05f));
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
        Renderer.drawLoading(oSB, iTranslateX, iTranslateY, 0.12f + 0.88f * this.iStepID / this.iNumOfSteps);
        Renderer.drawTextWithShadow(oSB, CFG.FONT_REGULAR_SMALL, this.loadingName, iTranslateX + CFG.PADDING * 4, iTranslateY + CFG.PADDING * 4, new Color(1.0f, 1.0f, 1.0f, 0.15f));
    }
    
    public void setLoadText(final String sText) {
        this.loadingName = sText;
    }
    
    public final void loadAction() {
        try {
            this.setLoadText(Game.lang.get("Loading") + " #" + (this.iStepID + 2));
            switch (this.iStepID) {
                case 0: {
                    Game.mapScenarios.clearData();
                    Menu_LoadSavingGame.SAVE_FILE_ID = 0;
                    break;
                }
                case 1: {
                    LoadSavedGameManager.loadSave_Details();
                    break;
                }
                case 2: {
                    LoadSavedGameManager.loadSave_Civs();
                    break;
                }
                case 3: {
                    Game.mapScenarios.loadScenario_3_A();
                    break;
                }
                case 4: {
                    Game.mapScenarios.loadScenario_3_B();
                    break;
                }
                case 5: {
                    LoadSavedGameManager.loadSave_Civs2();
                    break;
                }
                case 6: {
                    LoadSavedGameManager.loadSave_Civs3();
                    break;
                }
                case 7: {
                    LoadSavedGameManager.loadSave_Civs4();
                    break;
                }
                case 8: {
                    LoadSavedGameManager.loadSave_InitCivsData();
                    break;
                }
                case 9: {
                    LoadSavedGameManager.loadSave_CivsUnlockedTechnologies();
                    break;
                }
                case 10: {
                    LoadSavedGameManager.loadSave_CivsLaws();
                    break;
                }
                case 11: {
                    LoadSavedGameManager.loadSave_CivsUnlockedAdvantages();
                    break;
                }
                case 12: {
                    LoadSavedGameManager.loadSave_BuildTechTree();
                    break;
                }
                case 13: {
                    Game.initProvinceData();
                    break;
                }
                case 17: {
                    LoadSavedGameManager.loadSave_ProvinceData();
                    break;
                }
                case 18: {
                    LoadSavedGameManager.loadSave_ProvinceData2();
                    break;
                }
                case 19: {
                    LoadSavedGameManager.loadSave_ProvinceData3();
                    break;
                }
                case 20: {
                    LoadSavedGameManager.loadSave_ProvinceData4();
                    break;
                }
                case 21: {
                    LoadSavedGameManager.loadSave_ProvinceData5();
                    break;
                }
                case 22: {
                    LoadSavedGameManager.loadSave_ProvinceData6();
                    break;
                }
                case 23: {
                    LoadSavedGameManager.loadSave_ProvinceData7();
                    break;
                }
                case 24: {
                    LoadSavedGameManager.loadSave_ProvinceData8();
                    break;
                }
                case 25: {
                    LoadSavedGameManager.loadSave_ProvinceData9();
                    break;
                }
                case 26: {
                    LoadSavedGameManager.loadSave_ProvinceData10();
                    break;
                }
                case 27: {
                    LoadSavedGameManager.buildProvinceCores();
                    break;
                }
                case 28: {
                    SiegeManager.buildProvinceUnderSiege_Load();
                    break;
                }
                case 29: {
                    Game.buildProvinceIsCapital();
                    break;
                }
                case 30: {
                    Game.mapScenarios.loadScenario_7();
                    break;
                }
                case 31: {
                    Game.buildWastelandLevels();
                    break;
                }
                case 33: {
                    LoadSavedGameManager.loadSave_Population();
                    break;
                }
                case 34: {
                    LoadSavedGameManager.buildProvincePopulationData();
                    break;
                }
                case 35: {
                    Game.addSimpleTask(new Game.SimpleTask("buildCivilizationsRegions") {
                        @Override
                        public void update() {
                            CivilizationRegionsManager.buildCivilizationsRegions();
                        }
                    });
                    break;
                }
                case 42: {
                    LoadSavedGameManager.loadSave_CivsResearchProgress();
                    break;
                }
                case 43: {
                    LoadSavedGameManager.loadSave_CivsLegacies();
                    break;
                }
                case 44: {
                    LoadSavedGameManager.loadSave_CivsLoans();
                    break;
                }
                case 45: {
                    Menu_LoadSavingGame.SAVE_FILE_ID = 0;
                    break;
                }
                case 46: {
                    if (LoadSavedGameManager.loadSave_ProvincesBuildings(Menu_LoadSavingGame.SAVE_FILE_ID++)) {
                        return;
                    }
                    break;
                }
                case 47: {
                    Menu_LoadSavingGame.SAVE_FILE_ID = 0;
                    LoadSavedGameManager.loadSave_ProvinceConstructionBuilding();
                    break;
                }
                case 48: {
                    LoadSavedGameManager.loadSave_ProvinceInvestDaysLeft();
                    break;
                }
                case 49: {
                    LoadSavedGameManager.loadSave_ProvinceInvestTax();
                    break;
                }
                case 50: {
                    LoadSavedGameManager.loadSave_ProvinceInvestManpower();
                    break;
                }
                case 51: {
                    LoadSavedGameManager.loadSave_ProvinceInvestGrowthRate();
                    break;
                }
                case 52: {
                    LoadSavedGameManager.loadSave_ProvinceInvestInfrastructure();
                    break;
                }
                case 53: {
                    LoadSavedGameManager.loadSave_ProvinceCoreCreation();
                    break;
                }
                case 54: {
                    LoadSavedGameManager.loadSave_ProvinceReligionConversion();
                    break;
                }
                case 55: {
                    LoadSavedGameManager.loadSave_ProvinceWonderConstruction();
                    break;
                }
                case 59: {
                    LoadSavedGameManager.loadSave_CivsTemporaryBonuses();
                    break;
                }
                case 60: {
                    LoadSavedGameManager.loadSave_CivsGoldenAges();
                    break;
                }
                case 62: {
                    LoadSavedGameManager.loadSave_CivsRulers();
                    break;
                }
                case 63: {
                    LoadSavedGameManager.loadSave_CivsRulers_Bonuses();
                    break;
                }
                case 64: {
                    LoadSavedGameManager.loadSave_CivsAdvisorsAdmBonuses();
                    break;
                }
                case 65: {
                    LoadSavedGameManager.loadSave_CivsAdvisorsAdm();
                    break;
                }
                case 67: {
                    LoadSavedGameManager.loadSave_CivsAdvisorsEconomyBonuses();
                    break;
                }
                case 68: {
                    LoadSavedGameManager.loadSave_CivsAdvisorsEconomy();
                    break;
                }
                case 69: {
                    LoadSavedGameManager.loadSave_CivsAdvisorsInnovationBonuses();
                    break;
                }
                case 70: {
                    LoadSavedGameManager.loadSave_CivsAdvisorsInnovation();
                    break;
                }
                case 71: {
                    LoadSavedGameManager.loadSave_CivsAdvisorsMilitaryBonuses();
                    break;
                }
                case 72: {
                    LoadSavedGameManager.loadSave_CivsAdvisorsMilitary();
                    break;
                }
                case 73: {
                    LoadSavedGameManager.loadSave_CivsGeneralsNotAssigned();
                    break;
                }
                case 75: {
                    if (LoadSavedGameManager.loadSave_ProvincesArmy_MoreFiles(Menu_LoadSavingGame.SAVE_FILE_ID++)) {
                        return;
                    }
                    break;
                }
                case 77: {
                    LoadSavedGameManager.loadSave_CivsRecruitArmyCreate();
                    break;
                }
                case 78: {
                    LoadSavedGameManager.loadSave_CivsRecruitArmy();
                    break;
                }
                case 80: {
                    LoadSavedGameManager.loadSave_MapPlagues();
                    break;
                }
                case 81: {
                    LoadSavedGameManager.loadSave_ProvincesPlagues();
                    break;
                }
                case 90: {
                    LoadSavedGameManager.loadSave_CivsEventsVariables();
                    break;
                }
                case 91: {
                    LoadSavedGameManager.loadSave_CivsEventsVariables2();
                    break;
                }
                case 95: {
                    LoadSavedGameManager.loadSave_CivsEventsData();
                    break;
                }
                case 96: {
                    LoadSavedGameManager.loadSave_CivsEventsData2();
                    break;
                }
                case 97: {
                    LoadSavedGameManager.loadSave_CivsEventsData3();
                    break;
                }
                case 105: {
                    LoadSavedGameManager.loadSaveWars();
                    break;
                }
                case 106: {
                    LoadSavedGameManager.loadSaveWars_BuildData();
                    break;
                }
                case 110: {
                    LoadSavedGameManager.loadSaveRelations();
                    break;
                }
                case 111: {
                    LoadSavedGameManager.loadSaveRelations2();
                    break;
                }
                case 115: {
                    LoadSavedGameManager.loadSaveAlliances();
                    break;
                }
                case 116: {
                    LoadSavedGameManager.loadSaveDefensive();
                    break;
                }
                case 117: {
                    LoadSavedGameManager.loadSaveTruce();
                    break;
                }
                case 118: {
                    LoadSavedGameManager.loadSaveNonAggression();
                    break;
                }
                case 119: {
                    LoadSavedGameManager.loadSaveMilitaryAccess();
                    break;
                }
                case 120: {
                    LoadSavedGameManager.loadSaveGuarantee();
                    break;
                }
                case 121: {
                    LoadSavedGameManager.loadSaveRivals();
                    break;
                }
                case 122: {
                    LoadSavedGameManager.loadSaveRelationsImprove();
                    break;
                }
                case 123: {
                    LoadSavedGameManager.loadSaveRelationsDamage();
                    break;
                }
                case 124: {
                    LoadSavedGameManager.loadSave_AllianceSpecial();
                    break;
                }
                case 125: {
                    Game.loadAlliancesSpecial_Images();
                    break;
                }
                case 126: {
                    final MapScenarios mapScenarios = Game.mapScenarios;
                    MapScenarios.buildAlliancesSpecial();
                    break;
                }
                case 127: {
                    LoadSavedGameManager.loadSave_CivsNukesProduction();
                    break;
                }
                case 129: {
                    final MapScenarios mapScenarios2 = Game.mapScenarios;
                    MapScenarios.buildCivData_Load();
                    break;
                }
                case 130: {
                    final MapScenarios mapScenarios3 = Game.mapScenarios;
                    MapScenarios.buildVassals();
                    break;
                }
                case 135: {
                    LoadSavedGameManager.loadSave_Vassals();
                    break;
                }
                case 140: {
                    LoadSavedGameManager.loadSave_UpdatePlayersCivID();
                    break;
                }
                case 141: {
                    LoadSavedGameManager.loadPlayer_Data();
                    break;
                }
                case 142: {
                    LoadSavedGameManager.loadPlayer_Stats();
                    break;
                }
                case 143: {
                    LoadSavedGameManager.loadPlayer_Stats2();
                    break;
                }
                case 144: {
                    LoadSavedGameManager.loadPlayer_Stats3();
                    break;
                }
                case 150: {
                    Game.mapScenarios.loadScenario_60();
                    break;
                }
                case 154: {
                    Game.mapScenarios.loadScenario_62(false);
                    break;
                }
                case 158: {
                    Game.mapScenarios.loadScenario_64();
                    break;
                }
                case 175: {
                    LoadSavedGameManager.loadSave_MapBattles();
                    break;
                }
                case 176: {
                    LoadSavedGameManager.loadSave_MapBattles_Update();
                    break;
                }
                case 180: {
                    LoadSavedGameManager.loadSave_CivsMoveUnits();
                    break;
                }
                case 185: {
                    ResourcesManager.updateWorldResourcesProduced(true);
                    break;
                }
                case 186: {
                    ResourcesManager.initUniqueCivsGoods();
                    Game.buildDistanceToCapital();
                    break;
                }
                case 188: {
                    Game.mapScenarios.buildCivsGovernmentBuildings_LoadSavedGame();
                    break;
                }
                case 190: {
                    final MapScenarios mapScenarios4 = Game.mapScenarios;
                    MapScenarios.buildManpower();
                    break;
                }
                case 191: {
                    CivilizationRanking.buildCivilizationRanking();
                    break;
                }
                case 192: {
                    Game.mapCities.updateCities();
                    break;
                }
                case 193: {
                    Game.mapScenarios.loadScenario_48();
                    break;
                }
                case 194: {
                    final MapScenarios mapScenarios5 = Game.mapScenarios;
                    MapScenarios.buildArmyPosition();
                    break;
                }
                case 195: {
                    final MapScenarios mapScenarios6 = Game.mapScenarios;
                    MapScenarios.buildCivsStability();
                    break;
                }
                case 196: {
                    final MapScenarios mapScenarios7 = Game.mapScenarios;
                    MapScenarios.buildIncome();
                    break;
                }
                case 197: {
                    LoadSavedGameManager.loadSave_UpdateDiplomacyPerMonth();
                    break;
                }
                case 198: {
                    LoadSavedGameManager.buildProvinceData();
                    break;
                }
                case 199: {
                    LoadSavedGameManager.loadSave_InitCivsData_CoresReligionGenerals();
                    break;
                }
                case 200: {
                    Game.mapScenarios.loadScenario_56();
                    break;
                }
                case 201: {
                    Game.mapScenarios.loadScenario_57();
                    break;
                }
                case 205: {
                    Game.mapScenarios.loadScenario_58();
                    break;
                }
                case 207: {
                    Game.mapScenarios.loadScenario_59();
                    break;
                }
                case 208: {
                    final MapScenarios mapScenarios8 = Game.mapScenarios;
                    MapScenarios.buildCivData_Load2();
                    break;
                }
                case 209: {
                    LoadSavedGameManager.loadSave_Rebels();
                    break;
                }
                case 212: {
                    LoadSavedGameManager.loadSave_RebelsMoveUnits();
                    break;
                }
                case 215: {
                    LoadSavedGameManager.loadSave_AI_Merge();
                    break;
                }
                case 218: {
                    LoadSavedGameManager.loadSave_AI_CreateNewArmy();
                    break;
                }
                case 220: {
                    LoadSavedGameManager.loadSave_AI_Diplomacy();
                    break;
                }
                case 222: {
                    LoadSavedGameManager.loadBuildColonization();
                    break;
                }
                case 224: {
                    LoadSavedGameManager.loadBuild_ProvincesUnderSiege();
                    break;
                }
                case 226: {
                    LoadSavedGameManager.loadBuild_ProvincesOccupied();
                    break;
                }
                case 228: {
                    AI_Colonization.initData();
                    break;
                }
                case 231: {
                    LoadSavedGameManager.loadSave_AI_Budget();
                    break;
                }
                case 234: {
                    Game.stats.loadStats(Game.getCiv(Game.player.iCivID).getCivTag(), false);
                    break;
                }
                case 249: {
                    NewGame.initNewGame();
                    break;
                }
                case 250: {
                    Game.mapScale.definedScale = MapScale.defScales.definedScale_Default;
                    Game.mapScale.setCurrentScale(1.0f);
                    Game.mapBG.requestToDisposeMinimap = true;
                    Game.menuManager.setViewIDWithoutAnimation(View.IN_GAME);
                    Game.addSimpleTask(new Game.SimpleTask("LoadGame_RebuildMenus") {
                        @Override
                        public void update() {
                            Game.menuManager.rebuildInGame_Messages();
                            Game.menuManager.rebuildInGame_Wars();
                            Game.menuManager.rebuildInGame_Right();
                            Game.menuManager.setOrderOfMenu_InGame();
                            ProvinceDraw.updateDrawExtraDetails();
                        }
                    });
                    break;
                }
            }
        }
        catch (final Exception ex) {
            CFG.LOG(Game.lang.get("Loading") + ": " + this.iStepID);
            CFG.exceptionStack(ex);
        }
        ++this.iStepID;
    }
}
