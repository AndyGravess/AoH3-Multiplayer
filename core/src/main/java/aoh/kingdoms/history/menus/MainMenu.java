// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menus;

import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Center;
import com.badlogic.gdx.graphics.Texture;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.files.FileHandle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu_element.button.Button_MainMenuIcon;
import aoh.kingdoms.history.menu_element.textStatic.Text_Static;
import aoh.kingdoms.history.menu_element.button.ButtonGame2_IMG;
import aoh.kingdoms.history.menus.Settings.Settings_Menu;
import aoh.kingdoms.history.mainGame.Steam.SteamManager;
import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import aoh.kingdoms.history.menu_element.button.ButtonGame2Sparks_Hovered;
import aoh.kingdoms.history.menu_element.button.ButtonGame2;
import aoh.kingdoms.history.mainGame.SaveLoad.LoadSavedGameManager;
import aoh.kingdoms.history.mainGame.FileManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.map.province.ProvinceDraw;
import aoh.kingdoms.history.menu.View;
import aoh.kingdoms.history.menu_element.button.Button_LoadGame_MainMenu;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu.MenuManager;
import aoh.kingdoms.history.map.diplomacy.DiplomacyManager;
import aoh.kingdoms.history.menu.ClickAnimation;
import aoh.kingdoms.history.menu_element.button.ButtonMainTitle;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.GameValues;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.mainGame.SaveLoad.SaveGameManager;
import aoh.kingdoms.history.textures.Image;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Menu;

public class MainMenu extends Menu
{
    public static Color sparksColors;
    private int iXPos;
    private int iYPos;
    private int iWidth;
    private int iHeight;
    public static boolean canContinue;
    public static Image flag;
    public static SaveGameManager.SaveDetails savedGame;
    public static String savedGameKey;
    public static float bgAlpha;
    public static long bgTIME;
    public static long bgTIME_CHANGE;
    
    public MainMenu() {
        this.iXPos = 0;
        this.iYPos = 0;
        this.iWidth = 480;
        this.iHeight = 480;
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingTopBot = CFG.PADDING * 2 + CFG.PADDING / 2;
        final int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2 + CFG.PADDING / 2;
        this.iXPos = (int)(CFG.GAME_WIDTH / (10.0f * CFG.GUI_SCALE));
        this.iWidth = (int)Math.max((float)CFG.LEFT_MENU_WIDTH, Math.min(this.iWidth, CFG.GAME_WIDTH / 4) * CFG.GUI_SCALE);
        this.iHeight = paddingTopBot * 2 + paddingTopBot / 2 + (CFG.BUTTON_HEIGHT + CFG.PADDING * 2) * 6;
        this.iYPos = (int)(0.5f * (CFG.GAME_HEIGHT - this.iHeight - ImageManager.getImage(Images.mainTitle).getHeight()));
        if (this.iXPos + this.iWidth > CFG.GAME_WIDTH) {
            this.iXPos = CFG.PADDING * 2;
        }
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), GameValues.text.VERSION);
        Game.versionWidth = (int)Renderer.glyphLayout.width;
        menuElements.add(new ButtonMainTitle("", 0, -1, this.iXPos, this.iYPos, this.iWidth, true) {
            @Override
            public void actionElement() {
                final MenuManager menuManager = Game.menuManager;
                MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + MainMenu.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + MainMenu.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                    @Override
                    public Color getColor() {
                        return DiplomacyManager.COLOR_WAR;
                    }
                });
            }
            
            @Override
            public void actionElementPPM() {
                final MenuManager menuManager = Game.menuManager;
                MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + MainMenu.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + MainMenu.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                    @Override
                    public Color getColor() {
                        return DiplomacyManager.COLOR_ALLIANCE;
                    }
                });
            }
            
            @Override
            public void buildElementHover() {
                this.menuElementHover = MainMenu.getHoverAbout();
            }
        });
        int buttonY = this.iYPos + ImageManager.getImage(Images.mainTitle).getHeight() + paddingTopBot;
        try {
            if (MainMenu.canContinue) {
                menuElements.add(new Button_LoadGame_MainMenu(Game.lang.get("Continue") + ": " + Game.getCiv(Game.player.iCivID).getCivName(), "" + Game_Calendar.currentDay + " " + Game_Calendar.getMonthName(Game_Calendar.currentMonth) + " " + Game.gameAges.getYear(Game_Calendar.currentYear), this.iXPos + paddingLeft, buttonY, this.iWidth - paddingLeft * 2) {
                    @Override
                    public void actionElement() {
                        Game.menuManager.setViewIDWithoutAnimation(View.IN_GAME);
                        Game.menuManager.setOrderOfMenu_InGame();
                        if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_DEFAULT) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                        }
                        ProvinceDraw.updateDrawExtraDetails();
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Continue"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.getCiv(Game.player.iCivID).getCivName(), "", Images.council, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(this.sText2, CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                    
                    @Override
                    protected void drawImage(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
                        try {
                            if (Game.getCiv(Game.player.iCivID).getFlag() != null) {
                                oSB.setShader(Renderer.shaderAlpha);
                                try {
                                    Game.getCiv(Game.player.iCivID).getFlag().getTexture().bind(1);
                                    Gdx.gl.glActiveTexture(33984);
                                    ImageManager.getImage(Images.flagMaskDefault).draw(oSB, this.getPosX() + getPaddingIMG() + iTranslateX + (ImageManager.getImage(Images.flagOverDefault).getWidth() - ImageManager.getImage(Images.flagMaskDefault).getWidth()) / 2, this.getPosY() + getPaddingIMG() + iTranslateY + (ImageManager.getImage(Images.flagOverDefault).getHeight() - ImageManager.getImage(Images.flagMaskDefault).getHeight()) / 2, ImageManager.getImage(Images.flagMaskDefault).getWidth(), ImageManager.getImage(Images.flagMaskDefault).getHeight());
                                }
                                catch (final Exception ex) {
                                    CFG.exceptionStack(ex);
                                }
                                oSB.flush();
                                oSB.setShader(Renderer.shaderDefault);
                                ImageManager.getImage(Images.flagOverDefault).draw(oSB, this.getPosX() + getPaddingIMG() + iTranslateX, this.getPosY() + getPaddingIMG() + iTranslateY);
                            }
                        }
                        catch (final Exception ex) {
                            CFG.exceptionStack(ex);
                        }
                    }
                });
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
            }
            else {
                FileHandle file;
                if (FileManager.IS_MAC) {
                    file = Gdx.files.external("saves/" + Game.map.getFile_ActiveMap_Path() + "AoH.txt");
                }
                else if (CFG.readLocalFiles()) {
                    file = Gdx.files.local("saves/" + Game.map.getFile_ActiveMap_Path() + "AoH.txt");
                }
                else {
                    file = Gdx.files.internal("saves/" + Game.map.getFile_ActiveMap_Path() + "AoH.txt");
                }
                if (file.exists()) {
                    final String[] tempTags = file.readString().split(";");
                    final List<SaveGameManager.SaveDetails> tempSaveDetails = new ArrayList<SaveGameManager.SaveDetails>();
                    final List<String> tempSaveKey = new ArrayList<String>();
                    for (int i = 0; i < tempTags.length; ++i) {
                        final SaveGameManager.SaveDetails readSD = LoadSavedGameManager.loadSave_Details(tempTags[i]);
                        if (readSD != null) {
                            tempSaveDetails.add(readSD);
                            tempSaveKey.add(tempTags[i]);
                        }
                    }
                    int bestID = 0;
                    for (int j = tempSaveDetails.size() - 1; j > 0; --j) {
                        if (tempSaveDetails.get(j).time > tempSaveDetails.get(bestID).time) {
                            bestID = j;
                        }
                    }
                    MainMenu.savedGame = tempSaveDetails.get(bestID);
                    MainMenu.savedGameKey = tempSaveKey.get(bestID);
                    loadFlag(MainMenu.savedGame.sCivTag);
                    menuElements.add(new Button_LoadGame_MainMenu(Game.lang.get("Continue") + ": " + Game.lang.getCiv(MainMenu.savedGame.sCivTag), "" + MainMenu.savedGame.iDay + " " + Game_Calendar.getMonthName(MainMenu.savedGame.iMonth) + " " + Game.gameAges.getYear(MainMenu.savedGame.iYear), this.iXPos + paddingLeft, buttonY, this.iWidth - paddingLeft * 2) {
                        @Override
                        public void actionElement() {
                            LoadSavedGameManager.key = MainMenu.savedGameKey;
                            Game.menuManager.setViewIDWithoutAnimation(View.LOAD_SAVED_GAME);
                        }
                    });
                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
                }
                else {
                    menuElements.add(new ButtonGame2(Game.lang.get("Continue"), 1, -1, this.iXPos + paddingLeft, buttonY, this.iWidth - paddingLeft * 2, false) {});
                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        menuElements.add(new ButtonGame2Sparks_Hovered(Game.lang.get("NewGame"), 1, -1, this.iXPos + paddingLeft, buttonY, this.iWidth - paddingLeft * 2, true) {
            @Override
            public void actionElement() {
                Game.menuManager.setViewID(View.SCENARIOS);
                Game.menuManager.setOrderOfMenu_Scenarios();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        final int statsW = CFG.BUTTON_WIDTH;
        menuElements.add(new ButtonGame2Sparks_Hovered(Game.lang.get("Campaign"), 1, -1, this.iXPos + paddingLeft, buttonY, this.iWidth - paddingLeft * 2, true) {
            @Override
            public void actionElement() {
                Game.menuManager.setViewID(View.SCENARIOS_CAMPAIGN);
                Game.menuManager.setOrderOfMenu_Scenarios();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        menuElements.add(new ButtonGame2(Game.lang.get("LoadGame"), 1, -1, this.iXPos + paddingLeft, buttonY, this.iWidth - paddingLeft * 2, true) {
            @Override
            public void actionElement() {
                Game.menuManager.setViewID(View.LOAD_GAMES_LIST);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2 + paddingTopBot / 2;
        menuElements.add(new ButtonGame2(Game.lang.get("Editor"), 1, -1, this.iXPos + paddingLeft, buttonY, (this.iWidth - paddingLeft * 2 - CFG.PADDING) / 2, true) {
            @Override
            public void actionElement() {
                ProvinceBorderManager.clearProvinceBorder();
                Game.menuManager.setViewID(View.EDITOR);
            }
            
            @Override
            public void buildElementHover() {
                if (SteamManager.modsFolders.size() > 0 || SteamManager.itemsInstalledSize > 0) {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    if (SteamManager.modsFolders.size() > 0) {
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("InstalledMods") + ": ", "" + SteamManager.modsFolders.size(), Images.technology2, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                    if (SteamManager.itemsInstalledSize > 0) {
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("Steam, " + Game.lang.get("InstalledMods") + ": ", "" + SteamManager.itemsInstalledSize, Images.technology2, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
                else {
                    this.menuElementHover = null;
                }
            }
        });
        menuElements.add(new ButtonGame2(Game.lang.get("Settings"), 1, -1, this.iXPos + paddingLeft + (this.iWidth - paddingLeft * 2 - CFG.PADDING) / 2 + CFG.PADDING, buttonY, (this.iWidth - paddingLeft * 2 - CFG.PADDING) / 2, true) {
            @Override
            public void actionElement() {
                Settings_Menu.goBackToMenu = View.MAINMENU;
                Game.menuManager.setViewID(View.SETTINGS);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        menuElements.add(new ButtonGame2(Game.lang.get("ExitGame"), 1, -1, this.iXPos + paddingLeft, buttonY, this.iWidth - paddingLeft * 2 - CFG.PADDING - statsW, true) {
            @Override
            public void actionElement() {
                Dialog.setDialogType(Dialog.DialogType.EXIT_GAME);
            }
        });
        menuElements.add(new ButtonGame2_IMG(null, 1, -1, this.iXPos + paddingLeft + this.iWidth - paddingLeft * 2 - statsW, buttonY, statsW, true, Images.development) {
            @Override
            public void actionElement() {
                Game.menuManager.setViewID(View.MAINMENU_STATS);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Civilizations") + ": ", Game.lang.get("HallofFame"), Images.development, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        menuElements.add(new Text_Static(GameValues.text.VERSION, CFG.FONT_REGULAR_SMALL, -1, CFG.GAME_WIDTH - CFG.BUTTON_HEIGHT3, 0, CFG.BUTTON_HEIGHT3, CFG.BUTTON_HEIGHT3) {
            @Override
            public void actionElement() {
                final MenuManager menuManager = Game.menuManager;
                MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + MainMenu.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + MainMenu.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                    @Override
                    public Color getColor() {
                        return DiplomacyManager.COLOR_WAR;
                    }
                });
                Game.addSimpleTask(new Game.SimpleTask("loadBackground") {
                    @Override
                    public void update() {
                        InitGame.loadBackground();
                        MainMenu.bgTIME = System.currentTimeMillis();
                        MainMenu.bgTIME_CHANGE = System.currentTimeMillis();
                        MainMenu.bgAlpha = 0.0f;
                    }
                });
            }
        });
        int buttonsY = CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 4;
        menuElements.add(new Button_MainMenuIcon(Images.yt, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH, buttonsY, CFG.BUTTON_WIDTH, CFG.BUTTON_HEIGHT) {
            @Override
            public void actionElement() {
                Dialog.GO_TO_LINK = "https://www.youtube.com/channel/UCppKzood12fbJhZClXfukFw";
                Dialog.setDialogType(Dialog.DialogType.GO_TO_LINK);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("YouTube: ", "Age of History 3", Images.yt, CFG.FONT_BOLD, CFG.FONT_REGULAR, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonsY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new Button_MainMenuIcon(Images.android, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH, buttonsY, CFG.BUTTON_WIDTH, CFG.BUTTON_HEIGHT) {
            @Override
            public void actionElement() {
                Dialog.GO_TO_LINK = "https://play.google.com/store/apps/details?id=age.of.history3.lukasz.jakowski";
                Dialog.setDialogType(Dialog.DialogType.GO_TO_LINK);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("Android: ", "Age of History 3", Images.android, CFG.FONT_BOLD, CFG.FONT_REGULAR, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonsY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new Button_MainMenuIcon(Images.app, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH, buttonsY, CFG.BUTTON_WIDTH, CFG.BUTTON_HEIGHT) {
            @Override
            public void actionElement() {
                Dialog.GO_TO_LINK = "https://apps.apple.com/app/age-of-history-3/id6686394372";
                Dialog.setDialogType(Dialog.DialogType.GO_TO_LINK);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("iOS: ", "Age of History 3", Images.app, CFG.FONT_BOLD, CFG.FONT_REGULAR, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonsY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new Button_MainMenuIcon(Images.pc, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH, buttonsY, CFG.BUTTON_WIDTH, CFG.BUTTON_HEIGHT) {
            @Override
            public void actionElement() {
                Dialog.GO_TO_LINK = "https://store.steampowered.com/app/2772750/Age_of_History_3/";
                Dialog.setDialogType(Dialog.DialogType.GO_TO_LINK);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("Steam: ", "Age of History 3", Images.pc, CFG.FONT_BOLD, CFG.FONT_REGULAR, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonsY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new Text_Static("Lukasz Jakowski", CFG.PADDING * 3, CFG.GAME_HEIGHT - CFG.TEXT_HEIGHT - CFG.PADDING * 3, CFG.FONT_REGULAR_SMALL) {
            @Override
            public void actionElement() {
                final MenuManager menuManager = Game.menuManager;
                MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + MainMenu.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + MainMenu.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                    @Override
                    public Color getColor() {
                        return DiplomacyManager.COLOR_WAR;
                    }
                });
            }
            
            @Override
            public void buildElementHover() {
                this.menuElementHover = MainMenu.getHoverAbout();
            }
            
            @Override
            protected Color getColor(final boolean isActive) {
                if (this.getIsHovered() || isActive) {
                    return Colors.HOVER_LEFT;
                }
                return Colors.HOVER_RIGHT3;
            }
        });
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements, true);
        MainMenu.bgTIME = System.currentTimeMillis();
        MainMenu.bgTIME_CHANGE = System.currentTimeMillis();
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (MainMenu.bgAlpha < 1.0f) {
            oSB.setColor(0.0f, 0.0f, 0.0f, 1.0f);
            Images.pix.draw(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.GAME_HEIGHT);
            MainMenu.bgAlpha = Math.min(1.0f, (CFG.currentTimeMillis - MainMenu.bgTIME) / (float)GameValues.text.MAIN_MENU_BG_ANIMATION_TIME);
        }
        oSB.setColor(new Color(0.050980393f, 0.08627451f, 0.13333334f, 1.0f * MainMenu.bgAlpha));
        InitGame.background.draw(oSB, iTranslateX + (CFG.GAME_WIDTH - InitGame.backgroundWidth) / 2, iTranslateY + (CFG.GAME_HEIGHT - InitGame.backgroundHeight) / 2, InitGame.backgroundWidth, InitGame.backgroundHeight);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, MainMenu.bgAlpha));
        oSB.setShader(Renderer.shaderAlpha);
        InitGame.background.getTexture().bind(1);
        Gdx.gl.glActiveTexture(33984);
        ImageManager.getImage(Images.gradientHorizontal2).draw(oSB, this.getPosX() + (CFG.GAME_WIDTH - InitGame.backgroundWidth) / 2 + iTranslateX, this.getPosY() + (CFG.GAME_HEIGHT - InitGame.backgroundHeight) / 2 + iTranslateY, InitGame.backgroundWidth, InitGame.backgroundHeight);
        oSB.flush();
        oSB.setShader(Renderer.shaderDefault);
        oSB.setColor(MainMenu.sparksColors);
        MenuManager.sparksAnimation.draw2(oSB, iTranslateX, CFG.GAME_HEIGHT - Images.sparkHeight + iTranslateY, CFG.GAME_WIDTH, Images.sparkHeight);
        oSB.setColor(Color.WHITE);
        Renderer.drawBoxCorner(oSB, iTranslateX + this.iXPos, iTranslateY + this.iYPos, this.iWidth, this.iHeight + ImageManager.getImage(Images.mainTitle).getHeight());
        Renderer.drawBox_EDGE_TOP_LR(oSB, Images.mainBox, this.iXPos + iTranslateX, this.iYPos + ImageManager.getImage(Images.mainTitle).getHeight() + iTranslateY, this.iWidth, this.iHeight, true);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.3f));
        Images.gradientXY.draw(oSB, this.iXPos + iTranslateX, this.iYPos + ImageManager.getImage(Images.mainTitle).getHeight() + iTranslateY, this.iWidth, this.iHeight, false, true);
        oSB.setColor(Color.WHITE);
        if (((CFG.isDesktop() && GameValues.text.MAIN_MENU_BG_ENABLE_AUTO_BG_CHANGE) || (!CFG.isDesktop() && GameValues.text.MAIN_MENU_BG_ENABLE_AUTO_BG_CHANGE_MOBILE)) && CFG.currentTimeMillis > MainMenu.bgTIME_CHANGE + GameValues.text.MAIN_MENU_BG_CHANGE_BG_EVERY_X_MS) {
            MainMenu.bgTIME_CHANGE = CFG.currentTimeMillis;
            Game.addSimpleTask(new Game.SimpleTask("loadBackground") {
                @Override
                public void update() {
                    InitGame.loadBackground();
                    MainMenu.bgTIME = System.currentTimeMillis();
                    MainMenu.bgTIME_CHANGE = System.currentTimeMillis();
                    MainMenu.bgAlpha = 0.0f;
                }
            });
        }
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    public static void loadFlag(final String sCivTag) {
        disposeData();
        if (FileManager.loadFile("gfx/flagsXH/" + sCivTag + ".png").exists()) {
            MainMenu.flag = new Image(new Texture(FileManager.loadFile("gfx/flagsXH/" + sCivTag + ".png")), Texture.TextureFilter.Linear);
        }
        else if (FileManager.loadFile("gfx/flagsXH/" + Game.ideologiesManager.getRealTag(sCivTag) + ".png").exists()) {
            MainMenu.flag = new Image(new Texture(FileManager.loadFile("gfx/flagsXH/" + Game.ideologiesManager.getRealTag(sCivTag) + ".png")), Texture.TextureFilter.Linear);
        }
        else if (FileManager.loadFile("gfx/flagsH/" + sCivTag + ".png").exists()) {
            MainMenu.flag = new Image(new Texture(FileManager.loadFile("gfx/flagsH/" + sCivTag + ".png")), Texture.TextureFilter.Linear);
        }
        else if (FileManager.loadFile("gfx/flagsH/" + Game.ideologiesManager.getRealTag(sCivTag) + ".png").exists()) {
            MainMenu.flag = new Image(new Texture(FileManager.loadFile("gfx/flagsH/" + Game.ideologiesManager.getRealTag(sCivTag) + ".png")), Texture.TextureFilter.Linear);
        }
        else if (FileManager.loadFile("gfx/flags/" + sCivTag + ".png").exists()) {
            MainMenu.flag = new Image(new Texture(FileManager.loadFile("gfx/flags/" + sCivTag + ".png")), Texture.TextureFilter.Nearest);
        }
        else if (FileManager.loadFile("gfx/flags/" + Game.ideologiesManager.getRealTag(sCivTag) + ".png").exists()) {
            MainMenu.flag = new Image(new Texture(FileManager.loadFile("gfx/flags/" + Game.ideologiesManager.getRealTag(sCivTag) + ".png")), Texture.TextureFilter.Nearest);
        }
        else {
            MainMenu.flag = new Image(new Texture(FileManager.loadFile("gfx/flagsXH/ran.png")), Texture.TextureFilter.Nearest);
        }
    }
    
    @Override
    public void dispose() {
        disposeData();
    }
    
    public static void disposeData() {
        if (MainMenu.flag != null) {
            MainMenu.flag.getTexture().dispose();
            MainMenu.flag = null;
        }
    }
    
    public static MenuElement_Hover getHoverAbout_Short() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center("Programmer and Designer", CFG.FONT_BOLD, Colors.HOVER_LEFT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("Lukasz Jakowski", "", Images.world, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("One Man Army!", "", Game_Calendar.IMG_MANPOWER, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_LEFT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        return new MenuElement_Hover(nElements);
    }
    
    public static MenuElement_Hover getHoverAbout() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center("Programmer and Designer", CFG.FONT_BOLD, Colors.HOVER_LEFT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("Lukasz Jakowski", "", Images.world, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("One Man Army!", "", Game_Calendar.IMG_MANPOWER, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_LEFT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        boolean lineAdded = false;
        if (GameValues.text.MAIN_MENU_LOGO_HOVER_TEXT != null) {
            for (int i = 0; i < GameValues.text.MAIN_MENU_LOGO_HOVER_TEXT.length; ++i) {
                if (GameValues.text.MAIN_MENU_LOGO_HOVER_TEXT[i] != null && !lineAdded) {
                    lineAdded = true;
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                nData.add(new MenuElement_HoverElement_Type_Text_Desc(GameValues.text.MAIN_MENU_LOGO_HOVER_TEXT[i], CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT2));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
        }
        return new MenuElement_Hover(nElements);
    }
    
    static {
        MainMenu.sparksColors = new Color(1.0f, 1.0f, 1.0f, 0.25f);
        MainMenu.canContinue = false;
        MainMenu.flag = null;
        MainMenu.savedGame = null;
        MainMenu.savedGameKey = null;
        MainMenu.bgAlpha = 0.0f;
    }
}
