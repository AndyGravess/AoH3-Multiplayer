// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menus.InitGame;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menus.Dialog;
import aoh.kingdoms.history.menus.Settings.Settings_Menu;
import aoh.kingdoms.history.menu.View;
import aoh.kingdoms.history.menu_element.button.ButtonGame2;
import aoh.kingdoms.history.menus.MainMenu;
import aoh.kingdoms.history.menu.MenuManager;
import aoh.kingdoms.history.map.diplomacy.DiplomacyManager;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.ClickAnimation;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.button.ButtonMainTitle;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Escape extends Menu
{
    protected static final int ANIMATION_TIME = 125;
    private long lTime;
    
    public InGame_Escape() {
        this.lTime = 0L;
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2;
        final int titleHeight = ImageManager.getImage(Images.title500).getHeight();
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        final int menuX = CFG.GAME_WIDTH / 10;
        final int menuY = CFG.GAME_HEIGHT / 10;
        final int buttonYPadding = CFG.PADDING + CFG.PADDING / 2;
        int buttonY = CFG.PADDING;
        final int textPosX = CFG.PADDING * 4;
        menuElements.add(new ButtonMainTitle("", 0, -1, 0, 0, menuWidth, true) {
            @Override
            public void actionElement() {
                final MenuManager menuManager = Game.menuManager;
                MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Escape.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Escape.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                    @Override
                    public Color getColor() {
                        return DiplomacyManager.COLOR_WAR;
                    }
                });
            }
            
            @Override
            public void buildElementHover() {
                this.menuElementHover = MainMenu.getHoverAbout_Short();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        menuElements.add(new ButtonGame2(null, 1, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true) {
            @Override
            public void updateLanguage() {
                this.setText(Game.lang.get("ReturnToGame"));
            }
            
            @Override
            public void actionElement() {
                Game.menuManager.setVisibleInGame_SaveGame(false);
                Game.menuManager.setVisibleInGame_Escape(false);
                Game.mapBG.updateActiveMapBGShader();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        menuElements.add(new ButtonGame2(null, 1, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true) {
            @Override
            public void updateLanguage() {
                this.setText(Game.lang.get("SaveTheGame"));
            }
            
            @Override
            public void actionElement() {
                if (Game.menuManager.getVisibleInGame_SaveGame()) {
                    Game.menuManager.setVisibleInGame_SaveGame(false);
                }
                else {
                    Game.menuManager.rebuildInGame_SaveGame();
                }
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding + buttonYPadding;
        menuElements.add(new ButtonGame2(null, 1, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true) {
            @Override
            public void updateLanguage() {
                this.setText(Game.lang.get("Audio"));
            }
            
            @Override
            public void actionElement() {
                Game.menuManager.setVisibleInGame_SaveGame(false);
                Game.menuManager.setVisibleInGame_Escape(false);
                Game.mapBG.updateActiveMapBGShader();
                Game.menuManager.rebuildInGame_Audio();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        menuElements.add(new ButtonGame2(null, 1, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true) {
            @Override
            public void updateLanguage() {
                this.setText(Game.lang.get("GameOptions"));
            }
            
            @Override
            public void actionElement() {
                Settings_Menu.goBackToMenu = View.IN_GAME;
                Game.menuManager.setViewID(View.SETTINGS);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        menuElements.add(new ButtonGame2(null, 1, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true) {
            @Override
            public void updateLanguage() {
                this.setText(Game.lang.get("ExitToMainMenu"));
            }
            
            @Override
            public void actionElement() {
                Dialog.setDialogType(Dialog.DialogType.ESCAPE_TO_MAIN_MENU);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING + Images.boxTitleBORDERWIDTH;
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - titleHeight - menuY - CFG.PADDING * 2);
        this.initMenu(null, CFG.GAME_WIDTH / 10, CFG.GAME_HEIGHT / 2 - (titleHeight + menuHeight) / 2, menuWidth, menuHeight, menuElements, false, false);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        try {
            float fAlpha;
            if (this.lTime + 125L >= CFG.currentTimeMillis) {
                fAlpha = (CFG.currentTimeMillis - this.lTime) / 125.0f;
            }
            else {
                fAlpha = 1.0f;
            }
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.05f * fAlpha));
            Images.pix.draw2(oSB, iTranslateX, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.8f * fAlpha));
            ImageManager.getImage(Images.gradientHorizontal3).draw(oSB, (CFG.GAME_WIDTH - InitGame.backgroundWidth) / 2 + iTranslateX, (CFG.GAME_HEIGHT - InitGame.backgroundHeight) / 2 + iTranslateY, InitGame.backgroundWidth, InitGame.backgroundHeight);
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f * fAlpha));
            oSB.setShader(Renderer.shaderAlpha);
            InitGame.background.getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            Images.gradientFull.draw(oSB, (CFG.GAME_WIDTH - InitGame.backgroundWidth) / 2 + iTranslateX, (CFG.GAME_HEIGHT - InitGame.backgroundHeight) / 2 + iTranslateY, InitGame.backgroundWidth, InitGame.backgroundHeight);
            ImageManager.getImage(Images.gradientHorizontal3).draw(oSB, (CFG.GAME_WIDTH - InitGame.backgroundWidth) / 2 + iTranslateX, (CFG.GAME_HEIGHT - InitGame.backgroundHeight) / 2 + iTranslateY, InitGame.backgroundWidth, InitGame.backgroundHeight);
            oSB.flush();
            oSB.setShader(Renderer.shaderDefault);
            if (Game.menuManager.getVisibleInGame_SaveGame()) {
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f * fAlpha));
                Images.pix.draw2(oSB, iTranslateX, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT);
            }
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.15f * fAlpha));
            ImageManager.getImage(Images.patt2).draw2(oSB, iTranslateX, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f * fAlpha));
            ImageManager.getImage(Images.gradientHorizontal).draw(oSB, iTranslateX, 0, CFG.LEFT_MENU_WIDTH / 2, CFG.GAME_HEIGHT);
            ImageManager.getImage(Images.gradientHorizontal).draw(oSB, CFG.GAME_WIDTH - CFG.LEFT_MENU_WIDTH / 2 + iTranslateX, 0, CFG.LEFT_MENU_WIDTH / 2, CFG.GAME_HEIGHT, true, false);
            ImageManager.getImage(Images.gradientVertical).draw(oSB, iTranslateX, 0, CFG.GAME_WIDTH, CFG.LEFT_MENU_WIDTH / 2);
            ImageManager.getImage(Images.gradientVertical).draw(oSB, iTranslateX, 0 + CFG.GAME_HEIGHT - CFG.LEFT_MENU_WIDTH / 2, CFG.GAME_WIDTH, CFG.LEFT_MENU_WIDTH / 2, false, true);
            oSB.setColor(MainMenu.sparksColors);
            MenuManager.sparksAnimation.draw2(oSB, iTranslateX, CFG.GAME_HEIGHT - Images.sparkHeight + iTranslateY, CFG.GAME_WIDTH, Images.sparkHeight);
            oSB.setColor(Color.WHITE);
            if (this.lTime + 125L >= CFG.currentTimeMillis) {
                iTranslateY = iTranslateY - this.getHeight() * 4 / 5 + (int)(this.getHeight() * 4 / 5 * fAlpha);
            }
            Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING);
            Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
            super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public void updateLanguage() {
        super.updateLanguage();
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        this.lTime = CFG.currentTimeMillis;
        if (!visible) {
            Game.menuManager.setVisibleInGame_SaveGame(false);
        }
    }
    
    @Override
    public void onHovered() {
        super.onHovered();
        Game.menuManager.setOrderOfMenu_InGameEscape();
    }
}
