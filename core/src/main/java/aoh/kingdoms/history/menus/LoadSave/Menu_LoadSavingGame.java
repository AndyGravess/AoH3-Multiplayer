// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menus.LoadSave;

import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.mainGame.SaveLoad.SaveGameManager;
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
import aoh.kingdoms.history.menu.View;
import aoh.kingdoms.history.menu.Menu;

public class Menu_LoadSavingGame extends Menu
{
    public int iStepID;
    public int iNumOfSteps;
    public static int SAVE_FILE_ID;
    public static View goToMenu;
    public String loadingName;
    
    public Menu_LoadSavingGame() {
        this.iStepID = 0;
        this.iNumOfSteps = 200;
        this.loadingName = "";
        this.iStepID = 0;
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements, true);
        this.setLoadText(Game.lang.get("Saving") + " #1");
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
            this.setLoadText(Game.lang.get("Saving") + " #" + (this.iStepID + 2));
            switch (this.iStepID) {
                case 0: {
                    Menu_LoadSavingGame.SAVE_FILE_ID = 0;
                    SaveGameManager.Save_1();
                    break;
                }
                case 2: {
                    SaveGameManager.Save_Provinces_Data();
                    break;
                }
                case 5: {
                    SaveGameManager.Save_Provinces_Data2();
                    break;
                }
                case 9: {
                    SaveGameManager.Save_Provinces_Data3();
                    break;
                }
                case 12: {
                    SaveGameManager.Save_Provinces_Data4();
                    break;
                }
                case 16: {
                    SaveGameManager.Save_Provinces_Data5();
                    break;
                }
                case 20: {
                    SaveGameManager.Save_Provinces_Data6();
                    break;
                }
                case 24: {
                    SaveGameManager.Save_Provinces_Data7();
                    break;
                }
                case 28: {
                    SaveGameManager.Save_Provinces_Data8();
                    break;
                }
                case 32: {
                    SaveGameManager.Save_Provinces_Data9();
                    break;
                }
                case 35: {
                    SaveGameManager.Save_Provinces_Data_Population();
                    break;
                }
                case 40: {
                    SaveGameManager.Save_Provinces_Data_ConstructionBuilding();
                    break;
                }
                case 42: {
                    Menu_LoadSavingGame.SAVE_FILE_ID = 0;
                    break;
                }
                case 43: {
                    if (Menu_LoadSavingGame.SAVE_FILE_ID * GameValues.value.SAVE_PROVINCES_BUILDINGS_PER_FILE < Game.getProvincesSize()) {
                        SaveGameManager.Save_Provinces_Data_Buildings(Menu_LoadSavingGame.SAVE_FILE_ID++);
                        return;
                    }
                    break;
                }
                case 44: {
                    Menu_LoadSavingGame.SAVE_FILE_ID = 0;
                    break;
                }
                case 46: {
                    SaveGameManager.saveGuarantee();
                    break;
                }
                case 48: {
                    SaveGameManager.saveAlliances();
                    break;
                }
                case 50: {
                    SaveGameManager.saveRivals();
                    break;
                }
                case 53: {
                    SaveGameManager.saveRelationsImprove();
                    break;
                }
                case 55: {
                    SaveGameManager.saveRelationsDamage();
                    break;
                }
                case 57: {
                    SaveGameManager.saveDefensive();
                    break;
                }
                case 59: {
                    SaveGameManager.saveMilitaryAccess();
                    break;
                }
                case 61: {
                    SaveGameManager.saveNonAggression();
                    break;
                }
                case 63: {
                    SaveGameManager.saveTruces();
                    break;
                }
                case 65: {
                    SaveGameManager.saveRelations();
                    break;
                }
                case 69: {
                    SaveGameManager.saveRelations2();
                    break;
                }
                case 73: {
                    SaveGameManager.saveWars();
                    break;
                }
                case 76: {
                    SaveGameManager.Save_Provinces_Data_InvestDaysLeft();
                    break;
                }
                case 79: {
                    SaveGameManager.Save_Provinces_Data_InvestTax();
                    break;
                }
                case 82: {
                    SaveGameManager.Save_Provinces_Data_InvestManpower();
                    break;
                }
                case 85: {
                    SaveGameManager.Save_Provinces_Data_InvestGrowthRate();
                    break;
                }
                case 88: {
                    SaveGameManager.Save_Provinces_Data_Infrastructure();
                    break;
                }
                case 91: {
                    SaveGameManager.Save_Provinces_Data_CoreConstruction();
                    break;
                }
                case 93: {
                    SaveGameManager.Save_Provinces_Data_ReligionConversion();
                    break;
                }
                case 95: {
                    SaveGameManager.Save_Provinces_Data_WondersConstruction();
                    break;
                }
                case 97: {
                    SaveGameManager.Save_Civ_Data_UnlockedTechnologies();
                    break;
                }
                case 101: {
                    SaveGameManager.Save_Civ_Data_NukeProduction();
                    break;
                }
                case 103: {
                    SaveGameManager.Save_Civ_Data_ResearchProgress();
                    break;
                }
                case 106: {
                    SaveGameManager.Save_Civ_Data_UnlockedAdvantages();
                    break;
                }
                case 109: {
                    SaveGameManager.Save_Civ_Data_Laws();
                    break;
                }
                case 111: {
                    SaveGameManager.Save_Civ_Data_RecruitingArmy();
                    break;
                }
                case 112: {
                    SaveGameManager.Save_Civ_Data_RecruitArmyCreate();
                    break;
                }
                case 115: {
                    if (Menu_LoadSavingGame.SAVE_FILE_ID * GameValues.value.SAVE_PROVINCES_ARMIES_PER_FILE < Game.getProvincesSize()) {
                        SaveGameManager.Save_Provinces_Data_Armies_MoreFiles(Menu_LoadSavingGame.SAVE_FILE_ID++);
                        return;
                    }
                    break;
                }
                case 121: {
                    SaveGameManager.Save_Map_Data_Plagues();
                    break;
                }
                case 123: {
                    SaveGameManager.Save_Provinces_Data10();
                    break;
                }
                case 125: {
                    SaveGameManager.Save_Provinces_Data_Plagues();
                    break;
                }
                case 127: {
                    SaveGameManager.Save_Civs_Data_Rulers();
                    break;
                }
                case 129: {
                    SaveGameManager.Save_Civs_Data_RulersBonuses();
                    break;
                }
                case 132: {
                    SaveGameManager.Save_Civs_Data_AdvisorsAdm();
                    break;
                }
                case 134: {
                    SaveGameManager.Save_Civs_Data_AdvisorsAdmBonuses();
                    break;
                }
                case 136: {
                    SaveGameManager.Save_Civs_Data_AdvisorsEconomy();
                    break;
                }
                case 138: {
                    SaveGameManager.Save_Civs_Data_AdvisorsEconomyBonuses();
                    break;
                }
                case 141: {
                    SaveGameManager.Save_Civs_Data_AdvisorsInnovation();
                    break;
                }
                case 143: {
                    SaveGameManager.Save_Civs_Data_AdvisorsInnovationBonuses();
                    break;
                }
                case 145: {
                    SaveGameManager.Save_Civs_Data_AdvisorsMilitary();
                    break;
                }
                case 147: {
                    SaveGameManager.Save_Civs_Data_AdvisorsMilitaryBonuses();
                    break;
                }
                case 149: {
                    SaveGameManager.Save_Civs_Data_GeneralsNotAssigned();
                    break;
                }
                case 152: {
                    SaveGameManager.Save_Civs_Data_TemporaryBonuses();
                    break;
                }
                case 155: {
                    SaveGameManager.Save_Civs_Data_EventsVariables();
                    break;
                }
                case 158: {
                    SaveGameManager.Save_Civs_Data_EventsVariables2();
                    break;
                }
                case 160: {
                    SaveGameManager.Save_Civs_Data_GoldenAges();
                    break;
                }
                case 162: {
                    SaveGameManager.Save_Civs_Data_Loans();
                    break;
                }
                case 164: {
                    SaveGameManager.Save_Civs_Data_Legacies();
                    break;
                }
                case 165: {
                    SaveGameManager.Save_Civs_Data_MoveUnits();
                    break;
                }
                case 168: {
                    SaveGameManager.Save_Civs_Data_EventsData();
                    break;
                }
                case 170: {
                    SaveGameManager.Save_Civs_Data_EventsData2();
                    break;
                }
                case 172: {
                    SaveGameManager.Save_Civs_Data_EventsData3();
                    break;
                }
                case 175: {
                    SaveGameManager.Save_Civs_Data();
                    break;
                }
                case 178: {
                    SaveGameManager.Save_Civs_Data2();
                    break;
                }
                case 181: {
                    SaveGameManager.Save_Civs_Data3();
                    break;
                }
                case 183: {
                    SaveGameManager.Save_Civs_Data4();
                    break;
                }
                case 186: {
                    SaveGameManager.Save_Player_Data();
                    break;
                }
                case 188: {
                    SaveGameManager.Save_Player_Stats();
                    break;
                }
                case 191: {
                    SaveGameManager.Save_Player_Stats2();
                    break;
                }
                case 193: {
                    SaveGameManager.Save_Player_Stats3();
                    break;
                }
                case 195: {
                    SaveGameManager.Save_Map_AlliancesSpecial();
                    break;
                }
                case 198: {
                    SaveGameManager.Save_Map_Data_Battles();
                    break;
                }
                case 202: {
                    SaveGameManager.Save_Rebels_Data_MoveUnits();
                    break;
                }
                case 205: {
                    SaveGameManager.Save_Rebels_Data();
                    break;
                }
                case 208: {
                    SaveGameManager.Save_Civs_Data_AI_Merge();
                    break;
                }
                case 211: {
                    SaveGameManager.Save_Civs_Data_AI_CreateNewArmy();
                    break;
                }
                case 213: {
                    SaveGameManager.Save_Civs_Data_AI_Diplomacy();
                    break;
                }
                case 215: {
                    SaveGameManager.Save_Civs_Data_AI_Budget();
                    break;
                }
                case 218: {
                    Game.stats.saveStats();
                    break;
                }
                case 221: {
                    SaveGameManager.Save_Provinces_Data_Vassals();
                    break;
                }
                case 223: {
                    Game.menuManager.setViewIDWithoutAnimation(Menu_LoadSavingGame.goToMenu);
                    break;
                }
            }
        }
        catch (final Exception ex) {
            CFG.LOG(Game.lang.get("Saving") + ": " + this.iStepID);
            CFG.exceptionStack(ex);
        }
        ++this.iStepID;
    }
    
    static {
        Menu_LoadSavingGame.SAVE_FILE_ID = 0;
        Menu_LoadSavingGame.goToMenu = View.IN_GAME;
    }
}
