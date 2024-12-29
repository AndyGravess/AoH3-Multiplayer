// Decompiled with: CFR 0.152
// Class Version: 8
package aoh.kingdoms.history.menusInGame;

import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.FileManager;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.mainGame.Keyboard;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.SaveLoad.LoadSavedGameManager;
import aoh.kingdoms.history.mainGame.SaveLoad.SaveGameManager;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu.Menu;
import aoh.kingdoms.history.menu.View;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.MenuElement;
import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.menu_element.button.ButtonGame;
import aoh.kingdoms.history.menu_element.button.Button_LoadGame;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_Write;
import aoh.kingdoms.history.menus.Menu_LoadGames_List;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class InGame_SaveGame
    extends Menu {
    protected static final int ANIMATION_TIME = 60;
    public static long lTime = 0L;
    public static String sSaveName = "";

    public InGame_SaveGame(boolean visible) {
        ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>();
        int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        int titleHeight = ImageManager.getImage(Images.title500).getHeight();
        int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        int menuX = CFG.PADDING * 2;
        int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title500).getHeight();
        int buttonY = CFG.PADDING * 2;
        int buttonX = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2;
        int buttonH = CFG.TEXT_HEIGHT + CFG.PADDING * 4;
        sSaveName = "" + Game.getCiv(Game.player.iCivID).getCivName().replace(" ", "_").replace(":", "_").replace(";", "_").replace(",", "_").replace(".", "_") + "_" + Game_Calendar.getCurrentDate_Simple().replace(" ", "_").replace(":", "_").replace(";", "_").replace(",", "_").replace(".", "_");
        menuElements.add(new Text_StaticBG_Write(Game.lang.get("SaveName") + ": ", CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2){

            @Override
            public String getTextToDraw() {
                return sSaveName + Keyboard.getKeyboardVerticalLine();
            }

            @Override
            public void actionElement() {
                Game.keyboard.showKeyboard(Keyboard.KeyboardActionType.SAVE_NAME, sSaveName);
            }
        });
        menuElements.add(new ButtonGame(Game.lang.get("Cancel"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2, (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, true){

            @Override
            public void actionElement() {
                Game.menuManager.setVisibleInGame_SaveGame(false);
            }
        });
        menuElements.add(new ButtonGame(Game.lang.get("SaveTheGame"), CFG.FONT_REGULAR, -1, paddingLeft + CFG.PADDING + (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, true){

            @Override
            public void actionElement() {
                Game.menuManager.setVisibleInGame_SaveGame(false);
                SaveGameManager.saveKey = sSaveName.length() > 0 ? sSaveName : "" + CFG.currentTimeMillis;
                SaveGameManager.saveKey = SaveGameManager.saveKey.replace(" ", "_").replace(":", "_").replace(";", "_").replace(",", "_").replace(".", "_");
                Game.menuManager.setViewIDWithoutAnimation(View.LOAD_SAVE_GAME);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        try {
            Menu_LoadGames_List.disposeData();
            FileHandle file = FileManager.IS_MAC ? Gdx.files.external("saves/" + Game.map.getFile_ActiveMap_Path() + "AoH.txt") : (CFG.readLocalFiles() ? Gdx.files.local("saves/" + Game.map.getFile_ActiveMap_Path() + "AoH.txt") : Gdx.files.internal("saves/" + Game.map.getFile_ActiveMap_Path() + "AoH.txt"));
            if (file.exists()) {
                int i;
                String[] tempTags = file.readString().split(";");
                ArrayList<SaveGameManager.SaveDetails> tempSaveDetails = new ArrayList<SaveGameManager.SaveDetails>();
                ArrayList<String> tempSaveKey = new ArrayList<String>();
                for (i = 0; i < tempTags.length; ++i) {
                    SaveGameManager.SaveDetails readSD = LoadSavedGameManager.loadSave_Details(tempTags[i]);
                    if (readSD == null) continue;
                    tempSaveDetails.add(readSD);
                    tempSaveKey.add(tempTags[i]);
                }
                while (tempSaveDetails.size() > 0) {
                    int bestID = 0;
                    for (int i2 = tempSaveDetails.size() - 1; i2 > 0; --i2) {
                        if (((SaveGameManager.SaveDetails)tempSaveDetails.get((int)i2)).time <= ((SaveGameManager.SaveDetails)tempSaveDetails.get((int)bestID)).time) continue;
                        bestID = i2;
                    }
                    Menu_LoadGames_List.savedGames.add((SaveGameManager.SaveDetails)tempSaveDetails.get(bestID));
                    Menu_LoadGames_List.savedGamesKey.add((String)tempSaveKey.get(bestID));
                    tempSaveDetails.remove(bestID);
                    tempSaveKey.remove(bestID);
                }
                int iSize = Menu_LoadGames_List.savedGames.size();
                for (i = 0; i < iSize; ++i) {
                    Menu_LoadGames_List.loadFlag(Menu_LoadGames_List.savedGames.get((int)i).sCivTag);
                }
                iSize = Menu_LoadGames_List.savedGames.size();
                for (i = 0; i < iSize; ++i) {
                    menuElements.add(new Button_LoadGame(Game.lang.getCiv(Menu_LoadGames_List.savedGames.get((int)i).sCivTag), "" + Menu_LoadGames_List.savedGames.get((int)i).iDay + " " + Game_Calendar.getMonthName(Menu_LoadGames_List.savedGames.get((int)i).iMonth) + " " + Menu_LoadGames_List.savedGames.get((int)i).iYear, paddingLeft, buttonY, menuWidth - paddingLeft * 2, i){

                        @Override
                        public void buildElementHover() {
                            ArrayList<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            ArrayList<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("SaveTheGame"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.save, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements, true);
                        }

                        @Override
                        public void actionElement() {
                            sSaveName = Menu_LoadGames_List.savedGamesKey.get(this.getCurrent());
                        }
                    });
                    buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }
            }
        }
        catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
        buttonY = 0;
        int iSize = menuElements.size();
        for (int i = 0; i < iSize; ++i) {
            if (buttonY >= ((MenuElement)menuElements.get(i)).getPosY() + ((MenuElement)menuElements.get(i)).getHeight() + CFG.PADDING * 2) continue;
            buttonY = ((MenuElement)menuElements.get(i)).getPosY() + ((MenuElement)menuElements.get(i)).getHeight() + CFG.PADDING * 2;
        }
        buttonY = Math.max(buttonY, CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT * 4);
        int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG(Game.lang.get("SaveTheGame"), true, false, Images.title500){

            @Override
            public long getTime() {
                return lTime;
            }
        }, CFG.GAME_WIDTH / 2 - menuWidth / 2, menuY, menuWidth, menuHeight, menuElements, visible, true);
    }

    @Override
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean menuIsActive, Status titleStatus) {
        if (lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)((float)(CFG.BUTTON_WIDTH * 4 / 5) * ((float)(CFG.currentTimeMillis - lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.newGameOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.newGameOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.newGameOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        lTime = CFG.currentTimeMillis;
    }

    @Override
    public void actionCloseMenu() {
        super.actionCloseMenu();
    }
}
