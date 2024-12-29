// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menus;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.files.FileHandle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.button.Button_LoadGameX;
import aoh.kingdoms.history.menu_element.button.Button_LoadGame;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.mainGame.SaveLoad.LoadSavedGameManager;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.mainGame.FileManager;
import aoh.kingdoms.history.menu.View;
import aoh.kingdoms.history.menu_element.button.ButtonGame;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.mainGame.SaveLoad.SaveGameManager;
import aoh.kingdoms.history.textures.Image;
import java.util.List;
import aoh.kingdoms.history.menu.Menu;

public class Menu_LoadGames_List extends Menu
{
    public static List<Image> lFlags;
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static List<SaveGameManager.SaveDetails> savedGames;
    public static List<String> savedGamesKey;
    
    public Menu_LoadGames_List() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title500).getHeight();
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        final int menuX = CFG.PADDING * 2;
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title500).getHeight();
        int buttonY = CFG.PADDING * 2;
        menuElements.add(new ButtonGame(Game.lang.get("Back"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true) {
            @Override
            public void actionElement() {
                Game.menuManager.setViewID(View.MAINMENU);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        try {
            disposeData();
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
                while (tempSaveDetails.size() > 0) {
                    int bestID = 0;
                    for (int j = tempSaveDetails.size() - 1; j > 0; --j) {
                        if (tempSaveDetails.get(j).time > tempSaveDetails.get(bestID).time) {
                            bestID = j;
                        }
                    }
                    Menu_LoadGames_List.savedGames.add(tempSaveDetails.get(bestID));
                    Menu_LoadGames_List.savedGamesKey.add(tempSaveKey.get(bestID));
                    tempSaveDetails.remove(bestID);
                    tempSaveKey.remove(bestID);
                }
                for (int i = 0, iSize = Menu_LoadGames_List.savedGames.size(); i < iSize; ++i) {
                    loadFlag(Menu_LoadGames_List.savedGames.get(i).sCivTag);
                }
                for (int i = 0, iSize = Menu_LoadGames_List.savedGames.size(); i < iSize; ++i) {
                    menuElements.add(new Button_LoadGame(Game.lang.getCiv(Menu_LoadGames_List.savedGames.get(i).sCivTag), "" + Menu_LoadGames_List.savedGames.get(i).iDay + " " + Game_Calendar.getMonthName(Menu_LoadGames_List.savedGames.get(i).iMonth) + " " + Game.gameAges.getYear(Menu_LoadGames_List.savedGames.get(i).iYear), paddingLeft, buttonY, menuWidth - paddingLeft * 2 - CFG.PADDING - CFG.BUTTON_WIDTH, i) {
                        @Override
                        public void actionElement() {
                            LoadSavedGameManager.key = Menu_LoadGames_List.savedGamesKey.get(this.getCurrent());
                            Game.menuManager.setViewIDWithoutAnimation(View.LOAD_SAVED_GAME);
                        }
                    });
                    menuElements.add(new Button_LoadGameX("X", menuWidth - paddingLeft - CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, menuElements.get(menuElements.size() - 1).getHeight(), i) {
                        @Override
                        public void actionElement() {
                            SaveGameManager.deleteSavedGameKey = Menu_LoadGames_List.savedGamesKey.get(this.getCurrent());
                            Dialog.setDialogType(Dialog.DialogType.DELETE_SAVE);
                        }
                    });
                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        buttonY = 0;
        for (int k = 0, iSize2 = menuElements.size(); k < iSize2; ++k) {
            if (buttonY < menuElements.get(k).getPosY() + menuElements.get(k).getHeight() + CFG.PADDING * 2) {
                buttonY = menuElements.get(k).getPosY() + menuElements.get(k).getHeight() + CFG.PADDING * 2;
            }
        }
        buttonY = Math.max(buttonY, CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT * 4);
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG(Game.lang.get("LoadGame"), true, false, Images.title500) {
            @Override
            public long getTime() {
                return Menu_LoadGames_List.lTime;
            }
        }, CFG.GAME_WIDTH / 10, menuY, menuWidth, menuHeight, menuElements, true, false);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        oSB.setColor(new Color(0.050980393f, 0.08627451f, 0.13333334f, 1.0f));
        InitGame.background.draw(oSB, iTranslateX + (CFG.GAME_WIDTH - InitGame.backgroundWidth) / 2, iTranslateY + (CFG.GAME_HEIGHT - InitGame.backgroundHeight) / 2, InitGame.backgroundWidth, InitGame.backgroundHeight);
        oSB.setColor(Color.WHITE);
        oSB.setShader(Renderer.shaderAlpha);
        InitGame.background.getTexture().bind(1);
        Gdx.gl.glActiveTexture(33984);
        ImageManager.getImage(Images.gradientHorizontal2).draw(oSB, (CFG.GAME_WIDTH - InitGame.backgroundWidth) / 2 + iTranslateX, (CFG.GAME_HEIGHT - InitGame.backgroundHeight) / 2 + iTranslateY, InitGame.backgroundWidth, InitGame.backgroundHeight);
        oSB.flush();
        oSB.setShader(Renderer.shaderDefault);
        if (Menu_LoadGames_List.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * 4 / 5 * ((CFG.currentTimeMillis - Menu_LoadGames_List.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.newGameOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.newGameOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.newGameOver).getHeight()));
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        Menu_LoadGames_List.lTime = CFG.currentTimeMillis;
        if (!visible) {
            disposeData();
        }
    }
    
    public static void loadFlag(final String sCivTag) {
        if (FileManager.loadFile("gfx/flagsXH/" + sCivTag + ".png").exists()) {
            Menu_LoadGames_List.lFlags.add(new Image(new Texture(FileManager.loadFile("gfx/flagsXH/" + sCivTag + ".png")), Texture.TextureFilter.Linear));
        }
        else if (FileManager.loadFile("gfx/flagsXH/" + Game.ideologiesManager.getRealTag(sCivTag) + ".png").exists()) {
            Menu_LoadGames_List.lFlags.add(new Image(new Texture(FileManager.loadFile("gfx/flagsXH/" + Game.ideologiesManager.getRealTag(sCivTag) + ".png")), Texture.TextureFilter.Linear));
        }
        else if (FileManager.loadFile("gfx/flagsH/" + sCivTag + ".png").exists()) {
            Menu_LoadGames_List.lFlags.add(new Image(new Texture(FileManager.loadFile("gfx/flagsH/" + sCivTag + ".png")), Texture.TextureFilter.Linear));
        }
        else if (FileManager.loadFile("gfx/flagsH/" + Game.ideologiesManager.getRealTag(sCivTag) + ".png").exists()) {
            Menu_LoadGames_List.lFlags.add(new Image(new Texture(FileManager.loadFile("gfx/flagsH/" + Game.ideologiesManager.getRealTag(sCivTag) + ".png")), Texture.TextureFilter.Linear));
        }
        else if (FileManager.loadFile("gfx/flags/" + sCivTag + ".png").exists()) {
            Menu_LoadGames_List.lFlags.add(new Image(new Texture(FileManager.loadFile("gfx/flags/" + sCivTag + ".png")), Texture.TextureFilter.Nearest));
        }
        else if (FileManager.loadFile("gfx/flags/" + Game.ideologiesManager.getRealTag(sCivTag) + ".png").exists()) {
            Menu_LoadGames_List.lFlags.add(new Image(new Texture(FileManager.loadFile("gfx/flags/" + Game.ideologiesManager.getRealTag(sCivTag) + ".png")), Texture.TextureFilter.Nearest));
        }
        else {
            Menu_LoadGames_List.lFlags.add(new Image(new Texture(FileManager.loadFile("gfx/flagsXH/ran.png")), Texture.TextureFilter.Nearest));
        }
    }
    
    public static void disposeData() {
        Menu_LoadGames_List.savedGames.clear();
        Menu_LoadGames_List.savedGamesKey.clear();
        for (int i = 0; i < Menu_LoadGames_List.lFlags.size(); ++i) {
            Menu_LoadGames_List.lFlags.get(i).getTexture().dispose();
        }
        Menu_LoadGames_List.lFlags.clear();
    }
    
    static {
        Menu_LoadGames_List.lFlags = new ArrayList<Image>();
        Menu_LoadGames_List.lTime = 0L;
        Menu_LoadGames_List.savedGames = new ArrayList<SaveGameManager.SaveDetails>();
        Menu_LoadGames_List.savedGamesKey = new ArrayList<String>();
    }
}
