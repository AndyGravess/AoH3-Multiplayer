// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import java.util.List;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.View;
import aoh.kingdoms.history.menu_element.Empty;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.SoundsManager;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.button.ButtonTopDate;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_HideUI extends Menu
{
    public static boolean addDate;
    
    public InGame_HideUI() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        if (InGame_HideUI.addDate) {
            menuElements.add(new ButtonTopDate(Game_Calendar.getCurrentDate(), 0, InGame.topStatsHeight) {
                @Override
                public int getSFX() {
                    return Game.gameThread.play ? SoundsManager.SOUND_CLICK_TOP : SoundsManager.PLAY;
                }
                
                @Override
                public void actionElement() {
                    Game.gameThread.play = !Game.gameThread.play;
                }
                
                @Override
                public int getPosX() {
                    return CFG.GAME_WIDTH - InGame.getDatePadding() - this.getWidth();
                }
                
                @Override
                protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
                }
            });
        }
        menuElements.add(new Empty(0, 0, CFG.GAME_WIDTH / 4, CFG.GAME_HEIGHT / 4) {
            @Override
            public void actionElement() {
                Game.menuManager.setViewIDWithoutAnimation(View.IN_GAME);
            }
        });
        menuElements.get(menuElements.size() - 1).setClickable(true);
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements, true);
    }
    
    static {
        InGame_HideUI.addDate = true;
    }
}
