// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusEditor;

import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.button.ButtonCurrentSituation_Value_Mods;
import com.codedisaster.steamworks.SteamUGC;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.Steam.SteamManager;
import aoh.kingdoms.history.menu.View;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menu_element.button.ButtonMain;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class ManageMods extends Menu
{
    public ManageMods() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2;
        final int titleHeight = CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 2;
        final int menuX = CFG.GAME_WIDTH / 10;
        final int menuY = CFG.GAME_HEIGHT / 10;
        int buttonY;
        final int buttonYPadding = buttonY = CFG.PADDING * 2;
        menuElements.add(new ButtonMain(Game.lang.get("Back"), 1, -1, paddingLeft, CFG.PADDING, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, true) {
            @Override
            public void actionElement() {
                Renderer.drawArmyInProvince = true;
                Game.menuManager.setViewID(View.EDITOR);
            }
        });
        buttonY += menuElements.get(0).getHeight() + buttonYPadding;
        for (int i = 0; i < SteamManager.itemsInstalledAll.size(); ++i) {
            menuElements.add(new ButtonCurrentSituation_Value_Mods((String)SteamManager.modsFoldersAll_ModName.get(i), Images.technology2, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true, i, SteamManager.isTurnedOn(SteamManager.itemsInstalledAll.get(i).getFolder())) {
                @Override
                public void actionElement() {
                    SteamManager.addModsTurnedOff(SteamManager.itemsInstalledAll.get(this.getCurrent()).getFolder());
                    this.setCheckboxState(SteamManager.isTurnedOn(SteamManager.itemsInstalledAll.get(this.getCurrent()).getFolder()));
                    Game.menuManager.addToastGold(Game.lang.get("GameNeedsToBeRestartedToApplyTheChanges"), Images.settings);
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("On") + " / " + Game.lang.get("Off"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        }
        for (int i = 0; i < SteamManager.modsFoldersAll.size(); ++i) {
            menuElements.add(new ButtonCurrentSituation_Value_Mods((String)SteamManager.modsFoldersAll.get(i), Images.technology2, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true, i, SteamManager.isTurnedOn(SteamManager.modsFoldersAll.get(i))) {
                @Override
                public void actionElement() {
                    SteamManager.addModsTurnedOff(SteamManager.modsFoldersAll.get(this.getCurrent()));
                    this.setCheckboxState(SteamManager.isTurnedOn(SteamManager.modsFoldersAll.get(this.getCurrent())));
                    Game.menuManager.addToastGold(Game.lang.get("GameNeedsToBeRestartedToApplyTheChanges"), Images.settings);
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("On") + " / " + Game.lang.get("Off"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        }
        this.initMenu(new MenuTitle(Game.lang.get("InstalledMods"), 1.0f, titleHeight, true, true), menuX, titleHeight + menuY, CFG.LEFT_MENU_WIDTH2, CFG.GAME_HEIGHT - titleHeight - menuY - CFG.PADDING * 2, menuElements, true, false);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawEditorMenuBG(oSB, this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight() + CFG.PADDING, iTranslateX, iTranslateY);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
}
