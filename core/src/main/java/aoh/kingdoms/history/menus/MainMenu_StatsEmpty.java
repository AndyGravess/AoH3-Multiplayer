// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menus;

import java.util.List;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.View;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class MainMenu_StatsEmpty extends Menu
{
    public MainMenu_StatsEmpty() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Empty(0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT) {
            @Override
            public void actionElement() {
                MainMenu_Stats.disposeFlags();
                Game.menuManager.setViewID(View.MAINMENU);
            }
        });
        menuElements.get(menuElements.size() - 1).setClickable(true);
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements, true);
    }
    
    @Override
    public void onHovered() {
        super.onHovered();
        Game.menuManager.setOrderOfMenu_MainMenu_Stats();
    }
}
