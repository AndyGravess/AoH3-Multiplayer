// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menus.Settings;

import aoh.kingdoms.history.mainGame.zOther.Point_XY;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.mainGame.setting.SettingsDesktop;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2_TextLR;
import aoh.kingdoms.history.menu.View;
import aoh.kingdoms.history.menu_element.button.ButtonGame2;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class Settings_Resolution extends Menu
{
    public Settings_Resolution() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title600).getHeight();
        final int menuWidth = ImageManager.getImage(Images.title600).getWidth();
        final int menuX = CFG.BUTTON_WIDTH + Renderer.boxBGExtraY + CFG.PADDING;
        final int menuY = ImageManager.getImage(Images.topStats).getHeight() + CFG.PADDING * 2 + ImageManager.getImage(Images.title600).getHeight();
        final int buttonYPadding = CFG.PADDING;
        int buttonY = buttonYPadding * 2;
        final int buttonX = paddingLeft;
        menuElements.add(new ButtonGame2(Game.lang.get("Back"), 1, -1, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, true, CFG.BUTTON_HEIGHT2) {
            @Override
            public void actionElement() {
                Renderer.drawArmyInProvince = true;
                Game.menuManager.setViewID(View.SETTINGS);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding * 2;
        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Resolution"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame2(Game.lang.get("Max"), 1, -1, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, true, CFG.BUTTON_HEIGHT2) {
            @Override
            public void actionElement() {
                SettingsDesktop.iWidth = -1;
                SettingsDesktop.iHeight = -1;
                SettingsDesktop.saveConfig();
                Game.menuManager.setViewID(View.SETTINGS);
                Game.menuManager.addToastGold(Game.lang.get("GameNeedsToBeRestartedToApplyTheChanges"), Images.settings);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        for (int i = 0; i < 26; ++i) {
            menuElements.add(new ButtonGame2("" + this.getResolution(i).getPosX() + " x " + this.getResolution(i).getPosY(), 1, -1, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, true, CFG.BUTTON_HEIGHT2) {
                int id;
                
                @Override
                public void actionElement() {
                    SettingsDesktop.iWidth = Settings_Resolution.this.getResolution(this.id).getPosX();
                    SettingsDesktop.iHeight = Settings_Resolution.this.getResolution(this.id).getPosY();
                    SettingsDesktop.saveConfig();
                    Game.menuManager.setViewID(View.SETTINGS);
                    Game.menuManager.addToastGold(Game.lang.get("GameNeedsToBeRestartedToApplyTheChanges"), Images.settings);
                }
                
                @Override
                public void setCurrent(final int nCurrent) {
                    this.id = nCurrent;
                }
            });
            menuElements.get(menuElements.size() - 1).setCurrent(i);
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        }
        buttonY = 0;
        for (int i = 0, iSize = menuElements.size(); i < iSize; ++i) {
            if (buttonY < menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING * 2) {
                buttonY = menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING * 2;
            }
        }
        final int tMenuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY * 2 - CFG.PADDING * 2);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, tMenuHeight)));
        this.initMenu(new MenuTitleIMG(Game.lang.get("Resolution"), false, false, Images.title600), CFG.GAME_WIDTH / 10, CFG.GAME_HEIGHT / 8, menuWidth, tMenuHeight, menuElements, true, false);
        this.drawScrollPositionAlways = false;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop600, Images.insideBot600);
        ImageManager.getImage(Images.rulerOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.rulerOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.rulerOver).getHeight()));
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    public Point_XY getResolution(final int id) {
        switch (id) {
            case 0: {
                return new Point_XY(7680, 4320);
            }
            case 1: {
                return new Point_XY(5120, 2880);
            }
            case 2: {
                return new Point_XY(5120, 2160);
            }
            case 3: {
                return new Point_XY(4096, 2160);
            }
            case 4: {
                return new Point_XY(3840, 2160);
            }
            case 5: {
                return new Point_XY(3440, 1440);
            }
            case 6: {
                return new Point_XY(2560, 2048);
            }
            case 7: {
                return new Point_XY(2560, 1920);
            }
            case 8: {
                return new Point_XY(2560, 1600);
            }
            case 9: {
                return new Point_XY(2560, 1440);
            }
            case 10: {
                return new Point_XY(2560, 1080);
            }
            case 11: {
                return new Point_XY(2048, 1536);
            }
            case 12: {
                return new Point_XY(1920, 1440);
            }
            case 13: {
                return new Point_XY(1920, 1080);
            }
            case 14: {
                return new Point_XY(1856, 1392);
            }
            case 15: {
                return new Point_XY(1792, 1344);
            }
            case 16: {
                return new Point_XY(1680, 1050);
            }
            case 17: {
                return new Point_XY(1600, 1200);
            }
            case 18: {
                return new Point_XY(1600, 900);
            }
            case 19: {
                return new Point_XY(1440, 1050);
            }
            case 20: {
                return new Point_XY(1440, 900);
            }
            case 21: {
                return new Point_XY(1366, 768);
            }
            case 22: {
                return new Point_XY(1280, 1024);
            }
            case 23: {
                return new Point_XY(1280, 960);
            }
            case 24: {
                return new Point_XY(1024, 768);
            }
            case 25: {
                return new Point_XY(800, 600);
            }
            default: {
                return new Point_XY(1920, 1080);
            }
        }
    }
}
