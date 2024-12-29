// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menus;

import com.badlogic.gdx.files.FileHandle;
import aoh.kingdoms.history.mainGame.FileManager;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.mainGame.LanguageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.button.ButtonGame2;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.View;
import java.util.List;
import aoh.kingdoms.history.menu.Menu;

public class Init_SelectLanguage extends Menu
{
    public static List<String> languages;
    public static List<String> languagesFiles;
    public static View goBackToMenu;
    
    public Init_SelectLanguage() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2;
        final int titleHeight = CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 2;
        final int menuX = CFG.GAME_WIDTH / 10;
        final int menuY = CFG.GAME_HEIGHT / 10;
        int buttonY;
        final int buttonYPadding = buttonY = CFG.PADDING * 2;
        for (int i = 0; i < Init_SelectLanguage.languages.size(); ++i) {
            menuElements.add(new ButtonGame2((String)Init_SelectLanguage.languages.get(i), 0, -1, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
                int id = 0;
                
                @Override
                public void actionElement() {
                    Game.settingsManager.LANGUAGE_TAG = Init_SelectLanguage.languagesFiles.get(this.id);
                    if (Init_SelectLanguage.goBackToMenu != View.INIT_GAME_MENU) {
                        Game.menuManager.addToastGold(Game.lang.get("GameNeedsToBeRestartedToApplyTheChanges"), Images.settings);
                    }
                    else {
                        Game.lang = new LanguageManager(Init_SelectLanguage.languagesFiles.get(this.id));
                    }
                    Game.saveSettings();
                    Game.menuManager.setViewIDWithoutAnimation(Init_SelectLanguage.goBackToMenu);
                }
                
                @Override
                public void setCurrent(final int nCurrent) {
                    this.id = nCurrent;
                }
            });
            menuElements.get(menuElements.size() - 1).setCurrent(i);
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        }
        this.initMenu(new MenuTitle(Game.lang.get("SelectLanguage"), 1.0f, titleHeight, false, false), menuX, titleHeight + menuY, CFG.LEFT_MENU_WIDTH, Math.min(buttonY, CFG.GAME_HEIGHT - titleHeight - menuY * 2), menuElements, true, false);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        oSB.setColor(new Color(0.047058824f, 0.047058824f, 0.047058824f, 1.0f));
        Images.pix.draw(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.GAME_HEIGHT);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.2f));
        InitGame.background.draw(oSB, iTranslateX + (CFG.GAME_WIDTH - InitGame.backgroundWidth) / 2, iTranslateY + (CFG.GAME_HEIGHT - InitGame.backgroundHeight) / 2, InitGame.backgroundWidth, InitGame.backgroundHeight);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.6f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.PADDING * 3);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, iTranslateX, CFG.GAME_HEIGHT - CFG.PADDING * 3 + iTranslateY, CFG.GAME_WIDTH, CFG.PADDING * 3, false, true);
        oSB.setColor(Color.WHITE);
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawEditorMenuBG(oSB, this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight() + CFG.PADDING, iTranslateX, iTranslateY);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    public static void loadLanguages() {
        try {
            final FileHandle tempFileT = FileManager.loadFile("game/languages/Languages.txt");
            final String tempT = tempFileT.readString();
            final String[] tList = tempT.split(";");
            for (int i = 0; i < tList.length; i += 2) {
                Init_SelectLanguage.languages.add(tList[i]);
                Init_SelectLanguage.languagesFiles.add(tList[i + 1]);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    static {
        Init_SelectLanguage.languages = new ArrayList<String>();
        Init_SelectLanguage.languagesFiles = new ArrayList<String>();
        Init_SelectLanguage.goBackToMenu = View.INIT_GAME_MENU;
    }
}
