// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menus.LoadSave;

import aoh.kingdoms.history.menus.NewGame.NewGame;
import aoh.kingdoms.history.map.map.MapScale;
import aoh.kingdoms.history.events.EventsManager;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.textures.ImageManager;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menus.InitGame;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.View;
import aoh.kingdoms.history.menu.Menu;

public class Menu_LoadScenario extends Menu
{
    public int iStepID;
    public int iNumOfSteps;
    public static View goToMenu;
    public static boolean editorMode;
    public String loadingName;
    
    public Menu_LoadScenario() {
        this.iStepID = 0;
        this.iNumOfSteps = 61;
        this.loadingName = "";
        this.iStepID = 0;
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements, true);
        this.setLoadText("Loading Scenario #1");
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        this.loadScenario();
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
    
    public final void loadScenario() {
        try {
            if (this.iStepID == 0) {
                Game.mapScenarios.clearData();
                Game.mapScenarios.loadScenario_1();
                this.setLoadText("Loading Scenario #2 Wasteland");
            }
            else if (this.iStepID == 1) {
                Game.mapScenarios.loadScenario_2();
                this.setLoadText("Loading Scenario #3 Civilizations");
            }
            else if (this.iStepID == 2) {
                Game.mapScenarios.loadScenario_3(Menu_LoadScenario.editorMode);
                this.setLoadText("Loading Scenario #3A Civilizations Names");
            }
            else if (this.iStepID == 3) {
                Game.mapScenarios.loadScenario_3_A();
                this.setLoadText("Loading Scenario #3B Flags");
            }
            else if (this.iStepID == 4) {
                Game.mapScenarios.loadScenario_3_B();
                this.setLoadText("Loading Scenario #3C Civilization's Provinces");
            }
            else if (this.iStepID == 5) {
                Game.mapScenarios.loadScenario_3_C();
                this.setLoadText("Loading Scenario #4");
            }
            else if (this.iStepID == 6) {
                Game.mapScenarios.loadScenario_4();
                this.setLoadText("Loading Scenario #5");
            }
            else if (this.iStepID == 7) {
                Game.mapScenarios.loadScenario_5();
                this.setLoadText("Loading Scenario #6");
            }
            else if (this.iStepID == 8) {
                Game.mapScenarios.loadScenario_6();
                this.setLoadText("Loading Scenario #7");
            }
            else if (this.iStepID == 9) {
                Game.mapScenarios.loadScenario_7();
                this.setLoadText("Loading Scenario #8");
            }
            else if (this.iStepID == 10) {
                Game.mapScenarios.loadScenario_8();
                this.setLoadText("Loading Scenario #9");
            }
            else if (this.iStepID == 11) {
                Game.mapScenarios.loadScenario_9();
                this.setLoadText("Loading Scenario #10");
            }
            else if (this.iStepID == 12) {
                Game.mapScenarios.loadScenario_10();
                this.setLoadText("Loading Scenario #11");
            }
            else if (this.iStepID == 13) {
                Game.mapScenarios.loadScenario_11();
                this.setLoadText("Loading Scenario #12");
            }
            else if (this.iStepID == 14) {
                Game.mapScenarios.loadScenario_12();
                this.setLoadText("Loading Scenario #13");
            }
            else if (this.iStepID == 15) {
                Game.mapScenarios.loadScenario_13();
                this.setLoadText("Loading Scenario #14");
            }
            else if (this.iStepID == 16) {
                Game.mapScenarios.loadScenario_14();
                this.setLoadText("Loading Scenario #15");
            }
            else if (this.iStepID == 17) {
                Game.mapScenarios.loadScenario_15();
                this.setLoadText("Loading Scenario #16");
            }
            else if (this.iStepID == 18) {
                Game.mapScenarios.loadScenario_16();
                this.setLoadText("Loading Scenario #17");
            }
            else if (this.iStepID == 19) {
                Game.mapScenarios.loadScenario_17();
                this.setLoadText("Loading Scenario #18");
            }
            else if (this.iStepID == 20) {
                Game.mapScenarios.loadScenario_18();
                this.setLoadText("Loading Scenario #19");
            }
            else if (this.iStepID == 21) {
                Game.mapScenarios.loadScenario_19();
                this.setLoadText("Loading Scenario #20");
            }
            else if (this.iStepID == 22) {
                Game.mapScenarios.loadScenario_20();
                this.setLoadText("Loading Scenario #21");
            }
            else if (this.iStepID == 23) {
                Game.mapScenarios.loadScenario_21();
                this.setLoadText("Loading Scenario #22");
            }
            else if (this.iStepID == 24) {
                Game.mapScenarios.loadScenario_22();
                this.setLoadText("Loading Scenario #23");
            }
            else if (this.iStepID == 25) {
                Game.mapScenarios.loadScenario_23();
                this.setLoadText("Loading Scenario #24");
            }
            else if (this.iStepID == 26) {
                Game.mapScenarios.loadScenario_24();
                this.setLoadText("Loading Scenario #25");
            }
            else if (this.iStepID == 27) {
                Game.mapScenarios.loadScenario_25(Menu_LoadScenario.editorMode);
                this.setLoadText("Loading Scenario #26");
            }
            else if (this.iStepID == 28) {
                Game.mapScenarios.loadScenario_26();
                this.setLoadText("Loading Scenario #27");
            }
            else if (this.iStepID == 29) {
                Game.mapScenarios.loadScenario_27();
                this.setLoadText("Loading Scenario #28");
            }
            else if (this.iStepID == 30) {
                Game.mapScenarios.loadScenario_28();
                this.setLoadText("Loading Scenario #29");
            }
            else if (this.iStepID == 31) {
                Game.mapScenarios.loadScenario_29();
                this.setLoadText("Loading Scenario #30");
            }
            else if (this.iStepID == 32) {
                Game.mapScenarios.loadScenario_30();
                this.setLoadText("Loading Scenario #31");
            }
            else if (this.iStepID == 33) {
                Game.mapScenarios.loadScenario_31();
                this.setLoadText("Loading Scenario #32");
            }
            else if (this.iStepID == 34) {
                Game.mapScenarios.loadScenario_32();
                this.setLoadText("Loading Scenario #33");
            }
            else if (this.iStepID == 35) {
                Game.mapScenarios.loadScenario_33();
                this.setLoadText("Loading Scenario #34");
            }
            else if (this.iStepID == 36) {
                Game.mapScenarios.loadScenario_34();
                this.setLoadText("Loading Scenario #35");
            }
            else if (this.iStepID == 37) {
                Game.mapScenarios.loadScenario_35();
                this.setLoadText("Loading Scenario #36");
            }
            else if (this.iStepID == 38) {
                Game.mapScenarios.loadScenario_36(Menu_LoadScenario.editorMode);
                this.setLoadText("Loading Scenario #37");
            }
            else if (this.iStepID == 39) {
                Game.mapScenarios.loadScenario_37();
                this.setLoadText("Loading Scenario #38");
            }
            else if (this.iStepID == 40) {
                Game.mapScenarios.loadScenario_38(Menu_LoadScenario.editorMode);
                this.setLoadText("Loading Scenario #39");
            }
            else if (this.iStepID == 41) {
                Game.mapScenarios.loadScenario_39(Menu_LoadScenario.editorMode);
                this.setLoadText("Loading Scenario #40");
            }
            else if (this.iStepID == 42) {
                Game.mapScenarios.loadScenario_40(Menu_LoadScenario.editorMode);
                this.setLoadText("Loading Scenario #41");
            }
            else if (this.iStepID == 43) {
                Game.mapScenarios.loadScenario_41(Menu_LoadScenario.editorMode);
                this.setLoadText("Loading Scenario #42");
            }
            else if (this.iStepID == 44) {
                Game.mapScenarios.loadScenario_42();
                this.setLoadText("Loading Scenario #43");
            }
            else if (this.iStepID == 45) {
                Game.mapScenarios.loadScenario_43();
                this.setLoadText("Loading Scenario #44");
            }
            else if (this.iStepID == 46) {
                Game.mapScenarios.loadScenario_44();
                this.setLoadText("Loading Scenario #45");
            }
            else if (this.iStepID == 47) {
                Game.mapScenarios.loadScenario_45();
                this.setLoadText("Loading Scenario #46");
            }
            else if (this.iStepID == 48) {
                Game.mapScenarios.loadScenario_46();
                this.setLoadText("Loading Scenario #47");
            }
            else if (this.iStepID == 49) {
                Game.mapScenarios.loadScenario_47();
                this.setLoadText("Loading Scenario #48");
            }
            else if (this.iStepID == 50) {
                Game.mapScenarios.loadScenario_48();
                this.setLoadText("Loading Scenario #49");
            }
            else if (this.iStepID == 51) {
                Game.mapScenarios.loadScenario_49(Menu_LoadScenario.editorMode);
                this.setLoadText("Loading Scenario #50");
            }
            else if (this.iStepID == 52) {
                Game.mapScenarios.loadScenario_50(Menu_LoadScenario.editorMode);
                this.setLoadText("Loading Scenario #51");
            }
            else if (this.iStepID == 53) {
                Game.mapScenarios.loadScenario_51(Menu_LoadScenario.editorMode);
                this.setLoadText("Loading Scenario #52");
            }
            else if (this.iStepID == 54) {
                Game.mapScenarios.loadScenario_52();
                this.setLoadText("Loading Scenario #53");
            }
            else if (this.iStepID == 55) {
                Game.mapScenarios.loadScenario_53();
                this.setLoadText("Loading Scenario #54");
            }
            else if (this.iStepID == 56) {
                Game.mapScenarios.loadScenario_54();
                this.setLoadText("Loading Scenario #55");
            }
            else if (this.iStepID == 57) {
                Game.mapScenarios.loadScenario_55();
                this.setLoadText("Loading Scenario #56");
            }
            else if (this.iStepID == 58) {
                Game.mapScenarios.loadScenario_56();
                this.setLoadText("Loading Scenario #57");
            }
            else if (this.iStepID == 59) {
                Game.mapScenarios.loadScenario_57();
                this.setLoadText("Loading Scenario #58");
            }
            else if (this.iStepID == 60) {
                Game.mapScenarios.loadScenario_58();
                this.setLoadText("Loading Scenario #59");
            }
            else if (this.iStepID == 61) {
                EventsManager.loadScenarioEventsTag = Game.mapScenarios.lScenarios_TagsList.get(Game.scenarioID);
                Game.mapScenarios.loadScenario_59();
                this.setLoadText("Loading Scenario #60");
            }
            else if (this.iStepID == 62) {
                Game.mapScenarios.loadScenario_60();
                this.setLoadText("Loading Scenario #61");
            }
            else if (this.iStepID == 63) {
                Game.mapScenarios.loadScenario_61();
                this.setLoadText("Loading Scenario #62");
            }
            else if (this.iStepID == 64) {
                Game.mapScenarios.loadScenario_62(Menu_LoadScenario.editorMode);
                this.setLoadText("Loading Scenario #63");
            }
            else if (this.iStepID == 65) {
                Game.mapScenarios.loadScenario_63();
                this.setLoadText("Loading Scenario #64");
            }
            else if (this.iStepID == 66) {
                Game.mapScenarios.loadScenario_64();
                this.setLoadText("Loading Scenario #65");
            }
            else if (this.iStepID == 67) {
                Game.mapScale.definedScale = MapScale.defScales.definedScale_Default;
                Game.mapScale.setCurrentScale(1.0f);
                NewGame.setRandomCiv();
                Game.mapBG.requestToDisposeMinimap = true;
                Game.menuManager.setViewIDWithoutAnimation(Menu_LoadScenario.goToMenu);
            }
        }
        catch (final Exception ex) {
            CFG.LOG("loadScenario: " + this.iStepID);
            CFG.exceptionStack(ex);
        }
        ++this.iStepID;
    }
    
    static {
        Menu_LoadScenario.goToMenu = View.NEW_GAME;
        Menu_LoadScenario.editorMode = false;
    }
}
