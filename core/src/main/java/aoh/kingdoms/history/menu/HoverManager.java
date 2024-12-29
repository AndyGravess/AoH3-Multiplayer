// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu;

import aoh.kingdoms.history.textures.ImageManager;
import com.badlogic.gdx.utils.GdxRuntimeException;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menusInGame.InGame;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.map.province.ProvinceDraw;
import aoh.kingdoms.history.menu_element.MenuElement_Type;
import aoh.kingdoms.history.mainGame.Game;
import java.util.ArrayList;
import aoh.kingdoms.history.textures.Image;
import java.util.List;

public class HoverManager
{
    public int hoverActiveSliderMenuID;
    public int hoverActiveMenuElementID;
    public int hoverActiveMenuTitleID;
    public boolean hoverActiveMenuTitleCloseHovered;
    public static long hoverTime;
    protected static long hoverMobileTime;
    public static int HOVER_MOBILE_TIME_VISIBLE;
    public List<Image> lHoverLoadedTemporaryImages;
    
    public HoverManager() {
        this.hoverActiveSliderMenuID = -1;
        this.hoverActiveMenuElementID = -1;
        this.hoverActiveMenuTitleID = -1;
        this.hoverActiveMenuTitleCloseHovered = false;
        this.lHoverLoadedTemporaryImages = new ArrayList<Image>();
    }
    
    public final void rebuildHoverAfterRebuildMenu() {
        try {
            if (this.hoverActiveSliderMenuID >= 0 && this.hoverActiveMenuElementID >= 0) {
                Game.menuManager.getActiveMenu().get(this.hoverActiveSliderMenuID).getMenuElement(this.hoverActiveMenuElementID).setIsHovered(true);
                Game.menuManager.getActiveMenu().get(this.hoverActiveSliderMenuID).getMenuElement(this.hoverActiveMenuElementID).buildElementHover();
            }
        }
        catch (final Exception ex) {}
    }
    
    public final void updateHoveredFlag() {
        try {
            if (this.hoverActiveSliderMenuID >= 0 && this.hoverActiveMenuElementID >= 0) {
                if (Game.menuManager.getActiveMenu().get(this.hoverActiveSliderMenuID).getMenuElement(this.hoverActiveMenuElementID).getTypeOfElement() != MenuElement_Type.BUTTON_FLAG) {
                    ProvinceDraw.drawProvincesCiv_HoveredFlagID = 0;
                }
            }
            else {
                ProvinceDraw.drawProvincesCiv_HoveredFlagID = 0;
            }
        }
        catch (final Exception ex) {
            ProvinceDraw.drawProvincesCiv_HoveredFlagID = 0;
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateHoveredElement() {
        if (this.hoverActiveSliderMenuID >= 0 && this.hoverActiveMenuElementID >= 0) {
            try {
                if (Game.menuManager.getActiveMenu().get(this.hoverActiveSliderMenuID).getVisible() && Game.menuManager.getActiveMenu().get(this.hoverActiveSliderMenuID).getMenuElement(this.hoverActiveMenuElementID).getTypeOfElement() != MenuElement_Type.TRANSPARENT_BACKGROUND) {
                    Game.menuManager.getActiveMenu().get(this.hoverActiveSliderMenuID).getMenuElement(this.hoverActiveMenuElementID).updateHovered();
                }
            }
            catch (final Exception ex) {}
        }
    }
    
    public final boolean actionMove_Hover(final int nPosX, final int nPosY, final boolean updateHoveredElementAfterScrollingTheMenu) {
        if (Game.menuManager.getInGame() && InGame.ONLY_MAP_MODE) {
            return false;
        }
        try {
            if (Game.menuManager.dialogMenu.getVisible()) {
                this.resetHoverActive_Menu();
                return true;
            }
            this.hoverActiveMenuTitleID = -1;
            this.hoverActiveMenuTitleCloseHovered = false;
            if (this.hoverActiveSliderMenuID >= 0 && this.hoverActiveMenuElementID >= 0) {
                try {
                    if (Game.menuManager.getActiveMenu().get(this.hoverActiveSliderMenuID).getVisible() && Game.menuManager.getActiveMenu().get(this.hoverActiveSliderMenuID).getMenuElement(this.hoverActiveMenuElementID).getTypeOfElement() != MenuElement_Type.TRANSPARENT_BACKGROUND) {
                        if (Game.menuManager.getActiveMenu().get(this.hoverActiveSliderMenuID).getMenuElement(this.hoverActiveMenuElementID).getClickable() && nPosX >= Game.menuManager.getActiveMenu().get(this.hoverActiveSliderMenuID).getMenuElement(this.hoverActiveMenuElementID).getPosX() + Game.menuManager.getActiveMenu().get(this.hoverActiveSliderMenuID).getMenuPosX() && nPosX <= Game.menuManager.getActiveMenu().get(this.hoverActiveSliderMenuID).getMenuElement(this.hoverActiveMenuElementID).getPosX() + Game.menuManager.getActiveMenu().get(this.hoverActiveSliderMenuID).getMenuPosX() + Game.menuManager.getActiveMenu().get(this.hoverActiveSliderMenuID).getMenuElement(this.hoverActiveMenuElementID).getWidth() && nPosY >= Game.menuManager.getActiveMenu().get(this.hoverActiveSliderMenuID).getMenuElement(this.hoverActiveMenuElementID).getPosY() + Game.menuManager.getActiveMenu().get(this.hoverActiveSliderMenuID).getMenuPosY() && nPosY <= Game.menuManager.getActiveMenu().get(this.hoverActiveSliderMenuID).getMenuElement(this.hoverActiveMenuElementID).getPosY() + Game.menuManager.getActiveMenu().get(this.hoverActiveSliderMenuID).getMenuElement(this.hoverActiveMenuElementID).getHeight() + Game.menuManager.getActiveMenu().get(this.hoverActiveSliderMenuID).getMenuPosY()) {
                            return true;
                        }
                    }
                    else {
                        this.resetHoverActive_Menu();
                    }
                }
                catch (final Exception ex) {
                    this.resetHoverActive_Menu();
                }
            }
            try {
                for (int i = 0; i < Game.menuManager.getActiveMenu().size(); ++i) {
                    if (Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getVisible()) {
                        if (nPosX >= Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getPosX() && nPosX <= Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getPosX() + Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getWidth() && nPosY >= Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getPosY() && nPosY <= Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getPosY() + Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getHeight()) {
                            for (int j = 0; j < Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getMenuElementsSize(); ++j) {
                                if (Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getMenuElement(j).getVisible() && Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getMenuElement(j).canBeHovered() && nPosX >= Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getMenuElement(j).getPosX() + Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getMenuPosX() && nPosX <= Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getMenuElement(j).getPosX() + Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getMenuPosX() + Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getMenuElement(j).getWidth() && nPosY >= Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getMenuElement(j).getPosY() + Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getMenuPosY() && nPosY <= Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getMenuElement(j).getPosY() + Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getMenuElement(j).getHeight() + Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getMenuPosY()) {
                                    if (this.hoverActiveSliderMenuID != Game.menuManager.getActiveOrder(i) || this.hoverActiveMenuElementID != j) {
                                        if (this.hoverActiveMenuElementID >= 0 && this.hoverActiveSliderMenuID >= 0) {
                                            this.resetHoverActive_Menu();
                                        }
                                        else {
                                            MenuElement_Hover.resetAnimation();
                                        }
                                        this.hoverActiveSliderMenuID = Game.menuManager.getActiveOrder(i);
                                        this.hoverActiveMenuElementID = j;
                                        HoverManager.hoverTime = CFG.currentTimeMillis;
                                        Game.menuManager.getActiveMenu().get(this.hoverActiveSliderMenuID).getMenuElement(this.hoverActiveMenuElementID).setIsHovered(true);
                                        Game.menuManager.getActiveMenu().get(this.hoverActiveSliderMenuID).getMenuElement(this.hoverActiveMenuElementID).buildElementHover();
                                        this.updateHoveredFlag();
                                        if (Game.menuManager.getActiveMenu().get(this.hoverActiveSliderMenuID).getMenuElement(this.hoverActiveMenuElementID).playSFX_Hovered()) {
                                            Game.soundsManager.playHover();
                                        }
                                    }
                                    return true;
                                }
                            }
                            if (Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getLockHoverOverMenuBackground()) {
                                this.resetHoverActive_Menu();
                                return true;
                            }
                        }
                        else if (Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getTitle() != null && nPosX >= Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getPosX() && nPosX <= Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getPosX() + Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getWidth() && nPosY >= Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getPosY() - Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getTitle().getHeight() && nPosY <= Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getPosY()) {
                            if (Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getCloseable() && nPosX >= Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).menuClose.getCloseMenu_PosX() && nPosX <= Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).menuClose.getCloseMenu_PosX() + Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).menuClose.getCloseMenu_Width() && nPosY >= Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).menuClose.getCloseMenu_PosY() && nPosY <= Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).menuClose.getCloseMenu_PosY() + Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).menuClose.getCloseMenu_Height()) {
                                this.hoverActiveMenuTitleCloseHovered = true;
                            }
                            this.resetHoverActive_Menu();
                            this.hoverActiveMenuTitleID = Game.menuManager.getActiveOrder(i);
                            return true;
                        }
                    }
                }
            }
            catch (final IndexOutOfBoundsException ex2) {
                CFG.exceptionStack(ex2);
            }
            this.resetHoverActive_Menu();
        }
        catch (final GdxRuntimeException ex3) {
            CFG.exceptionStack((Throwable)ex3);
        }
        catch (final NullPointerException ex4) {
            CFG.exceptionStack(ex4);
        }
        return false;
    }
    
    public final int getHoveredMenuID_Scroll(final int nPosX, final int nPosY) {
        try {
            for (int i = 0; i < Game.menuManager.getActiveMenu().size(); ++i) {
                if (Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getVisible()) {
                    if (nPosX >= Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getPosX() && nPosX <= Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getPosX() + Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getWidth() && nPosY >= Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getPosY() && nPosY <= Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getPosY() + Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getHeight()) {
                        if (Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getScrollableX() || Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getScrollableY()) {
                            return Game.menuManager.getActiveOrder(i);
                        }
                    }
                    else if (Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getTitle() != null && nPosX >= Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getPosX() && nPosX <= Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getPosX() + Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getWidth() && nPosY >= Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getPosY() - Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getTitle().getHeight() && nPosY <= Game.menuManager.getActiveMenu().get(Game.menuManager.getActiveOrder(i)).getPosY()) {
                        return -1;
                    }
                }
            }
        }
        catch (final IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        return -1;
    }
    
    public final void udpateMobile() {
        if (CFG.isAndroid && this.getMenuElementHover_IsInView() && HoverManager.hoverMobileTime < CFG.currentTimeMillis - HoverManager.HOVER_MOBILE_TIME_VISIBLE) {
            this.resetHoverActive_Menu();
        }
    }
    
    public final void updateHoveredMenuElement_Hover(final int nPosX, final int nPosY) {
        try {
            if (this.hoverActiveSliderMenuID >= 0 && this.hoverActiveMenuElementID >= 0) {
                Game.menuManager.getActiveMenu().get(this.hoverActiveSliderMenuID).getMenuElement(this.hoverActiveMenuElementID).updateHover(nPosX, nPosY, Game.menuManager.getActiveMenu().get(this.hoverActiveSliderMenuID).getMenuPosX(), Game.menuManager.getActiveMenu().get(this.hoverActiveSliderMenuID).getMenuPosY());
            }
        }
        catch (final IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (final NullPointerException ex2) {
            CFG.exceptionStack(ex2);
        }
    }
    
    protected final void updateElementHover_Animation() {
        if (MenuElement_Hover.ANIMATION_ALPHA < 1.0f) {
            MenuElement_Hover.ANIMATION_ALPHA += (CFG.currentTimeMillis - MenuElement_Hover.ANIMATION_TIME) / (float)MenuElement_Hover.ANIMATION_INTERVAL;
            MenuElement_Hover.ANIMATION_PADDING = CFG.PADDING * 2 - CFG.PADDING * 2 * (MenuElement_Hover.ANIMATION_ALPHA * 1.65f);
            if (MenuElement_Hover.ANIMATION_PADDING < 0.0f) {
                MenuElement_Hover.ANIMATION_PADDING = 0.0f;
            }
            if (MenuElement_Hover.ANIMATION_ALPHA > 1.0f) {
                MenuElement_Hover.ANIMATION_ALPHA = 1.0f;
            }
        }
    }
    
    protected final boolean isSomethingHovered() {
        try {
            return this.hoverActiveMenuElementID >= 0 && this.hoverActiveSliderMenuID >= 0 && Game.menuManager.getActiveMenu().get(this.hoverActiveSliderMenuID).getMenuElement(this.hoverActiveMenuElementID).menuElementHover != null;
        }
        catch (final IndexOutOfBoundsException ex) {
            return false;
        }
        catch (final NullPointerException ex2) {
            return false;
        }
    }
    
    protected final boolean getMenuElementHover_IsInView() {
        try {
            if (Game.hoverManager.hoverActiveSliderMenuID >= 0 && Game.hoverManager.hoverActiveMenuElementID >= 0 && !Game.menuManager.getActiveMenu().get(Game.hoverManager.hoverActiveSliderMenuID).getMenuElement(Game.hoverManager.hoverActiveMenuElementID).getMenuElement_Hover_IsNull()) {
                return true;
            }
        }
        catch (final IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (final NullPointerException ex2) {
            CFG.exceptionStack(ex2);
        }
        return false;
    }
    
    public final void resetHoverActive_Menu() {
        try {
            if (this.hoverActiveSliderMenuID >= 0 && this.hoverActiveMenuElementID >= 0) {
                Game.menuManager.getActiveMenu().get(this.hoverActiveSliderMenuID).getMenuElement(this.hoverActiveMenuElementID).setIsHovered(false);
                Game.menuManager.getActiveMenu().get(this.hoverActiveSliderMenuID).getMenuElement(this.hoverActiveMenuElementID).resetElementHover();
                this.hoverActiveSliderMenuID = -1;
                this.hoverActiveMenuElementID = -1;
                this.hoverActiveMenuTitleID = -1;
                this.updateHoveredFlag();
                for (int i = this.lHoverLoadedTemporaryImages.size() - 1; i >= 0; --i) {
                    this.lHoverLoadedTemporaryImages.get(i).dispose();
                    this.lHoverLoadedTemporaryImages.remove(i);
                }
                return;
            }
        }
        catch (final Exception ex) {}
        this.hoverActiveSliderMenuID = -1;
        this.hoverActiveMenuElementID = -1;
        this.hoverActiveMenuTitleID = -1;
    }
    
    public final void resetHoverActive_Menu_Force() {
        this.hoverActiveSliderMenuID = -1;
        this.hoverActiveMenuElementID = -1;
        this.hoverActiveMenuTitleID = -1;
        this.updateHoveredFlag();
        try {
            for (int i = this.lHoverLoadedTemporaryImages.size() - 1; i >= 0; --i) {
                this.lHoverLoadedTemporaryImages.get(i).dispose();
                this.lHoverLoadedTemporaryImages.remove(i);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final int loadHoverLoadedTemporaryImage(final String sFile) {
        try {
            this.lHoverLoadedTemporaryImages.add(new Image(ImageManager.loadTexture(sFile)));
            return Math.max(0, this.lHoverLoadedTemporaryImages.size() - 1);
        }
        catch (final GdxRuntimeException ex) {
            this.lHoverLoadedTemporaryImages.add(new Image(ImageManager.loadTexture("gfx/imageNotFound.png")));
            return Math.max(0, this.lHoverLoadedTemporaryImages.size() - 1);
        }
    }
    
    static {
        HoverManager.hoverTime = 0L;
        HoverManager.hoverMobileTime = 0L;
        HoverManager.HOVER_MOBILE_TIME_VISIBLE = 25000;
    }
}
