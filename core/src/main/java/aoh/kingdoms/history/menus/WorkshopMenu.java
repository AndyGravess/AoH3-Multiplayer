// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menus;

import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.files.FileHandle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.mainGame.Steam.SteamPublishThread;
import aoh.kingdoms.history.menus.LoadSave.Menu_Load_Workshop;
import aoh.kingdoms.history.menu_element.button.ButtonCurrentSituation_Value;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.mainGame.FileManager;
import aoh.kingdoms.history.menu.View;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menu_element.button.ButtonMain;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import java.util.List;
import aoh.kingdoms.history.menu.Menu;

public class WorkshopMenu extends Menu
{
    public static List<String> lMods;
    
    public WorkshopMenu() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2;
        final int titleHeight = CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 2;
        final int menuX = CFG.GAME_WIDTH / 10;
        final int menuY = CFG.GAME_HEIGHT / 10;
        int buttonY;
        final int buttonYPadding = buttonY = CFG.PADDING * 2;
        WorkshopMenu.lMods.clear();
        menuElements.add(new ButtonMain(Game.lang.get("Back"), 1, -1, paddingLeft, CFG.PADDING, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, true) {
            @Override
            public void actionElement() {
                Renderer.drawArmyInProvince = true;
                Game.menuManager.setViewID(View.EDITOR);
            }
        });
        buttonY += menuElements.get(0).getHeight() + buttonYPadding;
        FileHandle[] files;
        if (FileManager.IS_MAC) {
            files = Gdx.files.external("mods/").list();
        }
        else {
            files = Gdx.files.local("mods/").list();
        }
        for (final FileHandle file : files) {
            WorkshopMenu.lMods.add(file.name());
        }
        for (int i = 0; i < WorkshopMenu.lMods.size(); ++i) {
            if (!WorkshopMenu.lMods.get(i).equals("GameCivs")) {
                menuElements.add(new ButtonCurrentSituation_Value((String)WorkshopMenu.lMods.get(i), Images.technology2, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true, i) {
                    @Override
                    public void actionElement() {
                        if (Gdx.files.internal("mods/" + WorkshopMenu.lMods.get(this.getCurrent()) + "/mod.txt").exists() || (FileManager.IS_MAC && Gdx.files.external("mods/" + WorkshopMenu.lMods.get(this.getCurrent()) + "/mod.txt").exists())) {
                            Menu_Load_Workshop.uploaded = false;
                            Game.menuManager.setViewIDWithoutAnimation(View.LOAD_WORKSHOP_PUBLISH);
                            SteamPublishThread.key = WorkshopMenu.lMods.get(this.getCurrent());
                            final SteamPublishThread nSteamPublish = new SteamPublishThread();
                            nSteamPublish.start();
                        }
                        else {
                            Game.menuManager.addToast_Error(Game.lang.get("MissingFile") + ": mods/" + WorkshopMenu.lMods.get(this.getCurrent()) + "/mod.txt", Images.x);
                        }
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Publish"), "", Images.technology2, CFG.FONT_REGULAR, CFG.FONT_REGULAR, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("AfterClickingTheGameFreezesAndAllFilesAreTransferredJustWait"), CFG.FONT_REGULAR, Colors.HOVER_RIGHT));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
            }
        }
        this.initMenu(new MenuTitle("", 1.0f, titleHeight, true, true), menuX, titleHeight + menuY, CFG.LEFT_MENU_WIDTH2, CFG.GAME_HEIGHT - titleHeight - menuY - CFG.PADDING * 2, menuElements, true, false);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawEditorMenuBG(oSB, this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight() + CFG.PADDING, iTranslateX, iTranslateY);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void updateLanguage() {
        super.updateLanguage();
        this.getTitle().setText(Game.lang.get("SubmitYourModsToTheSteamWorkshop"));
    }
    
    static {
        WorkshopMenu.lMods = new ArrayList<String>();
    }
}
