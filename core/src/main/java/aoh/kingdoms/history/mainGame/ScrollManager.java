// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski;

import aoc.kingdoms.lukasz.menu.Menu;

public class ScrollManager
{
    private static final int DEFAULT_SCROLL = 15;
    private static int iScroll;
    private static long lScrollTime;
    
    public static final boolean scrolled(final int amount) {
        try {
            final int isAnyScrollableMenuHovered = Game.hoverManager.getHoveredMenuID_Scroll(Touch.getMousePosX(), Touch.getMousePosY());
            if (getIsScrollable_Hovered_MenuElement()) {
                updateScroll();
                scrollHoveredMenuElement(-ScrollManager.iScroll * amount);
            }
            else if (isAnyScrollableMenuHovered >= 0) {
                if (getIsScrollableY_MenuHovered(isAnyScrollableMenuHovered)) {
                    updateScroll();
                    scrollHoveredMenu_Y(isAnyScrollableMenuHovered, -ScrollManager.iScroll * amount);
                }
                else if (getIsScrollableX_MenuHovered(isAnyScrollableMenuHovered)) {
                    updateScroll();
                    scrollHoveredMenu_X(isAnyScrollableMenuHovered, -ScrollManager.iScroll * amount);
                }
            }
            else if (Game.hoverManager.hoverActiveSliderMenuID < 0 && Game.hoverManager.hoverActiveMenuElementID < 0) {
                Game.mapTouchManager.Scroll(amount);
            }
            Game.soundsManager.playHover();
        }
        catch (final IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (final NullPointerException ex2) {
            CFG.exceptionStack(ex2);
        }
        catch (final StackOverflowError ex3) {
            CFG.exceptionStack(ex3);
        }
        catch (final ArithmeticException ex4) {
            CFG.exceptionStack(ex4);
        }
        return true;
    }
    
    protected static final void updateScroll() {
        if (ScrollManager.lScrollTime + 50L > CFG.currentTimeMillis) {
            ScrollManager.lScrollTime = CFG.currentTimeMillis;
            ScrollManager.iScroll += (int)(ScrollManager.iScroll * 1.2f);
            if (ScrollManager.iScroll > 75) {
                ScrollManager.iScroll = 75;
            }
        }
        else {
            ScrollManager.lScrollTime = CFG.currentTimeMillis;
            ScrollManager.iScroll = 15;
        }
    }
    
    protected static final void updateHoveredMenuElement_Hover(final int nPosX, final int nPosY) {
        try {
            if (Game.hoverManager.hoverActiveSliderMenuID >= 0 && Game.hoverManager.hoverActiveMenuElementID >= 0) {
                Game.menuManager.getActiveMenu().get(Game.hoverManager.hoverActiveSliderMenuID).getMenuElement(Game.hoverManager.hoverActiveMenuElementID).updateHover(nPosX, nPosY, Game.menuManager.getActiveMenu().get(Game.hoverManager.hoverActiveSliderMenuID).getMenuPosX(), Game.menuManager.getActiveMenu().get(Game.hoverManager.hoverActiveSliderMenuID).getMenuPosY());
            }
        }
        catch (final IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (final NullPointerException ex2) {
            CFG.exceptionStack(ex2);
        }
    }
    
    protected static final boolean getIsScrollableX_MenuHovered(final int menuID) {
        try {
            return Game.menuManager.getActiveMenu().get(menuID).getScrollableX();
        }
        catch (final IndexOutOfBoundsException ex) {
            return false;
        }
        catch (final NullPointerException ex2) {
            return false;
        }
    }
    
    protected static final void scrollHoveredMenu_X(final int menuID, final int nChange) {
        try {
            Game.menuManager.getActiveMenu().get(menuID).stopScrolling();
            Game.menuManager.getActiveMenu().get(menuID).updateMenuPosX(Game.menuManager.getActiveMenu().get(menuID).getNewMenuPosX() + nChange);
            Game.hoverManager.actionMove_Hover(Touch.getMousePosX(), Touch.getMousePosY(), true);
        }
        catch (final IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (final NullPointerException ex2) {
            CFG.exceptionStack(ex2);
        }
    }
    
    protected static final boolean getIsScrollableY_MenuHovered(final int menuID) {
        try {
            return Game.menuManager.getActiveMenu().get(menuID).getScrollableY();
        }
        catch (final IndexOutOfBoundsException ex) {
            return false;
        }
        catch (final NullPointerException ex2) {
            return false;
        }
    }
    
    protected static final void scrollHoveredMenu_Y(final int menuID, final int nChange) {
        try {
            Game.menuManager.getActiveMenu().get(menuID).stopScrolling();
            Game.menuManager.getActiveMenu().get(menuID).updateMenuPosY(Game.menuManager.getActiveMenu().get(menuID).getNewMenuPosY() + nChange);
            Game.hoverManager.actionMove_Hover(Touch.getMousePosX(), Touch.getMousePosY(), true);
        }
        catch (final IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (final NullPointerException ex2) {
            CFG.exceptionStack(ex2);
        }
    }
    
    protected static final boolean getIsScrollable_Hovered_MenuElement() {
        try {
            return Game.menuManager.getActiveMenu().get(Game.hoverManager.hoverActiveSliderMenuID).getMenuElement(Game.hoverManager.hoverActiveMenuElementID).getScrollable();
        }
        catch (final IndexOutOfBoundsException ex) {
            return false;
        }
        catch (final NullPointerException ex2) {
            return false;
        }
    }
    
    protected static final void scrollHoveredMenuElement(final int nChange) {
        try {
            Game.menuManager.getActiveMenu().get(Game.hoverManager.hoverActiveSliderMenuID).getMenuElement(Game.hoverManager.hoverActiveMenuElementID).scrollByWheel(nChange);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    static {
        ScrollManager.iScroll = 15;
        ScrollManager.lScrollTime = 0L;
    }
}
