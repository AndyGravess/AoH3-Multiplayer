// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import aoh.kingdoms.history.menu_element.Status;
import java.util.List;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.textStatic.Text_Static;
import aoh.kingdoms.history.menu.View;
import aoh.kingdoms.history.menus.MainMenu;
import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import aoh.kingdoms.history.menu_element.button.ButtonPlay;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_GameLost extends Menu
{
    public static final int ANIMATION_TIME = 500;
    public static long lTime;
    
    public InGame_GameLost() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new ButtonPlay(Game.lang.get("Continue"), 1, -1, CFG.GAME_WIDTH - ImageManager.getImage(Images.buttonPlay).getWidth(), CFG.GAME_HEIGHT - ImageManager.getImage(Images.buttonPlay).getHeight(), false) {
            @Override
            public void actionElement() {
                Game.setActiveProvinceID(-1);
                ProvinceBorderManager.clearProvinceBorder();
                MainMenu.canContinue = false;
                Game.menuManager.setViewID(View.MAINMENU);
            }
        });
        menuElements.add(new ButtonPlay(Game.lang.get("SpectatorMode"), 1, -1, 0, CFG.GAME_HEIGHT - ImageManager.getImage(Images.buttonPlay).getHeight(), true) {
            @Override
            public void actionElement() {
                Game.SPECTATOR_MODE = true;
                Game.player.iCivID = 0;
                Game.setActiveProvinceID(-1);
                ProvinceBorderManager.clearProvinceBorder();
                Game.menuManager.setViewIDWithoutAnimation(View.IN_GAME);
            }
        });
        InGame_GameLost.lTime = CFG.currentTimeMillis;
        menuElements.add(new Text_Static(Game.lang.get("YouHaveBeenDefeated"), -1, 0, 0, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT) {
            @Override
            public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 1.0f));
                Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), false, true);
                oSB.setColor(Color.WHITE);
                super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
            }
            
            @Override
            protected Color getColor(final boolean isActive) {
                return Colors.HOVER_GOLD;
            }
        });
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), CFG.BUTTON_HEIGHT / 2);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.BUTTON_HEIGHT / 2 + iTranslateY, this.getWidth(), CFG.BUTTON_HEIGHT / 2, false, true);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    static {
        InGame_GameLost.lTime = 0L;
    }
}
