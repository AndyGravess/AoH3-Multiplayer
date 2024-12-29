// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menus;

import java.util.List;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class EmptyMenu extends Menu
{
    public EmptyMenu() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        menuElements.add(new Empty(0, 0, 1, 1));
        this.initMenu(null, 0, 0, 2, 2, menuElements, false);
        this.getMenuElement(0).setVisible(false);
    }
}
