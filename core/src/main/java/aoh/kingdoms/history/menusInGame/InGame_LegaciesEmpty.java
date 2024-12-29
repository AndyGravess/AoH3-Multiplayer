// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.textures.ImageManager;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.View;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menus.MainMenu;
import aoh.kingdoms.history.menus.InitGame;
import aoh.kingdoms.history.menu.MenuManager;
import aoh.kingdoms.history.map.diplomacy.DiplomacyManager;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.ClickAnimation;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.textStatic.Text_Static;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_LegaciesEmpty extends Menu
{
    public InGame_LegaciesEmpty() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Text_Static(GameValues.text.VERSION, CFG.FONT_REGULAR_SMALL, -1, CFG.GAME_WIDTH - CFG.BUTTON_HEIGHT3, 0, CFG.BUTTON_HEIGHT3, CFG.BUTTON_HEIGHT3) {
            @Override
            public void actionElement() {
                final MenuManager menuManager = Game.menuManager;
                MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_LegaciesEmpty.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_LegaciesEmpty.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
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
        menuElements.add(new Empty(0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT) {
            @Override
            public void actionElement() {
                Game.menuManager.setViewIDWithoutAnimation(View.IN_GAME);
            }
        });
        menuElements.get(menuElements.size() - 1).setClickable(true);
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements, true);
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
        ImageManager.getImage(Images.gradientHorizontal2).draw(oSB, (CFG.GAME_WIDTH - InitGame.backgroundWidth) / 2 + iTranslateX, (CFG.GAME_HEIGHT - InitGame.backgroundHeight) / 2 + iTranslateY, InitGame.backgroundWidth, InitGame.backgroundHeight);
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, (CFG.GAME_WIDTH - InitGame.backgroundWidth) / 2 + iTranslateX, (CFG.GAME_HEIGHT - InitGame.backgroundHeight) / 2 + iTranslateY, InitGame.backgroundWidth, InitGame.backgroundHeight);
        oSB.flush();
        oSB.setShader(Renderer.shaderDefault);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f * MainMenu.bgAlpha));
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, iTranslateX, 0, CFG.LEFT_MENU_WIDTH / 2, CFG.GAME_HEIGHT);
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, CFG.GAME_WIDTH - CFG.LEFT_MENU_WIDTH / 2 + iTranslateX, 0, CFG.LEFT_MENU_WIDTH / 2, CFG.GAME_HEIGHT, true, false);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, iTranslateX, 0, CFG.GAME_WIDTH, CFG.LEFT_MENU_WIDTH / 2);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, iTranslateX, 0 + CFG.GAME_HEIGHT - CFG.LEFT_MENU_WIDTH / 2, CFG.GAME_WIDTH, CFG.LEFT_MENU_WIDTH / 2, false, true);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG.r, Colors.COLOR_GRADIENT_BG.g, Colors.COLOR_GRADIENT_BG.b, 0.65f * MainMenu.bgAlpha));
        Images.gradientFull.draw(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.GAME_HEIGHT);
        oSB.setColor(Color.WHITE);
        oSB.setColor(MainMenu.sparksColors);
        MenuManager.sparksAnimation.draw2(oSB, iTranslateX, CFG.GAME_HEIGHT - Images.sparkHeight + iTranslateY, CFG.GAME_WIDTH, Images.sparkHeight);
        MenuManager.sparksAnimationHover.draw2(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, Images.sparkHeight, false, true);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void onHovered() {
        super.onHovered();
        Game.menuManager.setOrderOfMenu_InGameLegacies();
    }
}
