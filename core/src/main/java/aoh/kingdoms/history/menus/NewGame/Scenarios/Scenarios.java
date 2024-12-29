// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menus.NewGame.Scenarios;

import aoh.kingdoms.history.menu.MenuManager;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menus.InitGame;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.files.FileHandle;
import aoh.kingdoms.history.mainGame.FileManager;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.map.map.MapScenarios;
import aoh.kingdoms.history.menu_element.button.ButtonFlag2_CivName_Random;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Bonuses;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.textStatic.Text_DescScenarios;
import aoh.kingdoms.history.menus.NewGame.NewGame;
import aoh.kingdoms.history.map.map.MapScale;
import aoh.kingdoms.history.menu.View;
import aoh.kingdoms.history.menus.LoadSave.Menu_LoadScenario;
import aoh.kingdoms.history.menus.MainMenu;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.button.ButtonPlay;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class Scenarios extends Menu
{
    private int iXPos;
    private int iYPos;
    private int iWidth;
    private int iHeight;
    public static int activeDescID;
    public static int currentActiveDescID;
    public static boolean SCENARIOS_DEFAULT_MODE;
    
    public Scenarios() {
        this.iXPos = 0;
        this.iYPos = 0;
        this.iWidth = 480;
        this.iHeight = 480;
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingTopBot = CFG.PADDING * 2 + CFG.PADDING / 2;
        this.iXPos = CFG.GAME_WIDTH / 10;
        this.iWidth = Math.max(CFG.LEFT_MENU_WIDTH, Math.min(this.iWidth, CFG.GAME_WIDTH / 4));
        this.iHeight = paddingTopBot * 3 + paddingTopBot / 2 + (CFG.BUTTON_HEIGHT + CFG.PADDING * 2) * 6;
        this.iYPos = (int)(0.5f * (CFG.GAME_HEIGHT - this.iHeight - ImageManager.getImage(Images.mainTitle).getHeight()));
        menuElements.add(new ButtonPlay("", 1, -1, CFG.GAME_WIDTH - ImageManager.getImage(Images.buttonPlay).getWidth(), CFG.GAME_HEIGHT - ImageManager.getImage(Images.buttonPlay).getHeight(), false) {
            @Override
            public void actionElement() {
                if (Game.reloadScenario || MainMenu.canContinue) {
                    Game.reloadScenario = false;
                    Menu_LoadScenario.editorMode = false;
                    Menu_LoadScenario.goToMenu = View.CLOUDS_MENU;
                    Game.menuManager.setViewIDWithoutAnimation(View.LOAD_SCENARIO);
                }
                else {
                    Game.mapScale.definedScale = MapScale.defScales.definedScale_Default;
                    Game.mapScale.setCurrentScale(1.0f);
                    NewGame.setRandomCiv();
                    Game.menuManager.setViewIDWithoutAnimation(View.CLOUDS_MENU);
                }
            }
            
            @Override
            public void updateLanguage() {
                this.setText(Game.lang.get("PLAY"));
            }
        });
        menuElements.add(new ButtonPlay("", 1, -1, 0, CFG.GAME_HEIGHT - ImageManager.getImage(Images.buttonPlay).getHeight(), true) {
            @Override
            public void actionElement() {
                Game.menuManager.setViewID(View.MAINMENU);
            }
            
            @Override
            public void updateLanguage() {
                this.setText(Game.lang.get("Back"));
            }
        });
        menuElements.add(new Text_DescScenarios(Game.lang.get("1440Desc1") + " " + Game.lang.get("1440Desc2") + " " + Game.lang.get("1440Desc3"), CFG.BUTTON_HEIGHT, 150, CFG.GAME_WIDTH - CFG.BUTTON_HEIGHT * 2) {
            @Override
            public boolean getClickable() {
                return false;
            }
            
            @Override
            public int getPosY() {
                return ScenariosList_NewGame.iYPos / 2 - this.getHeight() / 2;
            }
            
            @Override
            protected Color getColor(final boolean isActive) {
                return Colors.HOVER_RIGHT2;
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Scenarios"), "", Images.outliner, CFG.PADDING, CFG.GAME_HEIGHT - ImageManager.getImage(Images.buttonPlay).getHeight() - CFG.PADDING * 2 - CFG.BUTTON_HEIGHT3, ImageManager.getImage(Images.buttonPlay).getWidth() * 3 / 4, CFG.BUTTON_HEIGHT3, ImageManager.getImage(Images.time).getWidth()) {
            @Override
            public void actionElement() {
                Scenarios.SCENARIOS_DEFAULT_MODE = !Scenarios.SCENARIOS_DEFAULT_MODE;
                Game.menuManager.setViewIDWithoutAnimation(View.SCENARIOS);
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Campaign"), "", Images.outliner, CFG.PADDING, CFG.GAME_HEIGHT - ImageManager.getImage(Images.buttonPlay).getHeight() - CFG.PADDING * 2 - CFG.BUTTON_HEIGHT3 - CFG.PADDING - CFG.BUTTON_HEIGHT3, ImageManager.getImage(Images.buttonPlay).getWidth() * 3 / 4, CFG.BUTTON_HEIGHT3, ImageManager.getImage(Images.time).getWidth()) {
            @Override
            public void actionElement() {
                Game.menuManager.setViewID(View.SCENARIOS_CAMPAIGN);
                Game.menuManager.setOrderOfMenu_Scenarios();
            }
        });
        menuElements.add(new ButtonFlag2_CivName_Random(CFG.PADDING, CFG.GAME_HEIGHT - ImageManager.getImage(Images.buttonPlay).getHeight() - CFG.PADDING * 4 - CFG.BUTTON_HEIGHT3 * 2 - (ImageManager.getImage(Images.flagOverDefault).getHeight() + CFG.TEXT_HEIGHT + CFG.PADDING * 2), true) {
            @Override
            public void actionElement() {
                MapScenarios.loadRandomScenario();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("RandomScenario"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.dice, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements, true);
        this.updateDesc();
    }
    
    public void updateDesc() {
        if (!Scenarios.SCENARIOS_DEFAULT_MODE) {
            this.getMenuElement(2).setText("");
            return;
        }
        if (Scenarios.currentActiveDescID != Scenarios.activeDescID) {
            String sDesc = "";
            if (FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + Game.mapScenarios.lScenarios_TagsList.get(Scenarios.activeDescID) + "/" + "Desc.txt").exists()) {
                final FileHandle tempFileT = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + Game.mapScenarios.lScenarios_TagsList.get(Scenarios.activeDescID) + "/" + "Desc.txt");
                final String tempT = tempFileT.readString();
                final String[] sSplit = tempT.split(";");
                for (int i = 0; i < sSplit.length; ++i) {
                    sDesc = sDesc + Game.lang.get(sSplit[i]) + ((i == sSplit.length - 1) ? "" : " ");
                }
            }
            Scenarios.currentActiveDescID = Scenarios.activeDescID;
            this.getMenuElement(2).setText(sDesc);
        }
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        this.updateDesc();
        oSB.setColor(new Color(0.050980393f, 0.08627451f, 0.13333334f, 1.0f));
        InitGame.background.draw(oSB, iTranslateX + (CFG.GAME_WIDTH - InitGame.backgroundWidth) / 2, iTranslateY + (CFG.GAME_HEIGHT - InitGame.backgroundHeight) / 2, InitGame.backgroundWidth, InitGame.backgroundHeight);
        oSB.setColor(Color.WHITE);
        oSB.setShader(Renderer.shaderAlpha);
        InitGame.background.getTexture().bind(1);
        Gdx.gl.glActiveTexture(33984);
        Images.gradientXY.draw(oSB, this.getPosX() + (CFG.GAME_WIDTH - InitGame.backgroundWidth) / 2 + iTranslateX, this.getPosY() + (CFG.GAME_HEIGHT - InitGame.backgroundHeight) / 2 + iTranslateY, InitGame.backgroundWidth, InitGame.backgroundHeight);
        oSB.flush();
        oSB.setShader(Renderer.shaderDefault);
        oSB.setColor(MainMenu.sparksColors);
        MenuManager.sparksAnimation.draw2(oSB, iTranslateX, CFG.GAME_HEIGHT - Images.sparkHeight + iTranslateY, CFG.GAME_WIDTH, Images.sparkHeight);
        MenuManager.sparksAnimationHover.draw2(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, Images.sparkHeight, false, true);
        oSB.setColor(Color.WHITE);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void onHovered() {
        super.onHovered();
        Game.menuManager.setOrderOfMenu_Scenarios();
    }
    
    static {
        Scenarios.activeDescID = 0;
        Scenarios.currentActiveDescID = 0;
        Scenarios.SCENARIOS_DEFAULT_MODE = true;
    }
}
