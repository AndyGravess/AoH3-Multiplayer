// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu;

import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.List;

public class Menu
{
    private List<MenuElement> menuElements;
    private int iMenuElementsSize;
    private int iPosX;
    private int iPosY;
    private int iWidth;
    private int iHeight;
    private boolean visible;
    private MenuTitle menuTitle;
    private boolean lockHoverOverMenuBackground;
    public MenuClose menuClose;
    private boolean closeable;
    private int iMenuPosX;
    private int iNewMenuPositionX;
    private int iMaxSliderPositionX;
    private boolean scrollableX;
    private int iMenuPosY;
    private int iNewMenuPositionY;
    protected int iMaxSliderPositionY;
    private boolean scrollableY;
    private boolean scrollModeY;
    private int iScrollPosY;
    private int iScrollPosY2;
    private float fScrollNewMenuPosY;
    private boolean scrollModeX;
    private int iScrollPosX;
    private int iScrollPosX2;
    private float fScrollNewMenuPosX;
    public final float SCROLL_MENU_UPDATE = 0.97f;
    public boolean drawScrollPositionAlways;
    public boolean drawScrollPositionAlways2;
    public int scrollExtraPosX;
    private final Color COLOR_SCROLL_POSITION;
    private final Color COLOR_SCROLL_POSITION_INVIEW;
    private final Color COLOR_SCROLL_POSITION_INVIEW_ACTIVE;
    
    public Menu() {
        this.menuElements = new ArrayList<MenuElement>();
        this.visible = true;
        this.menuTitle = null;
        this.lockHoverOverMenuBackground = false;
        this.closeable = false;
        this.scrollableX = false;
        this.scrollableY = false;
        this.scrollModeY = false;
        this.iScrollPosY = -1;
        this.iScrollPosY2 = -1;
        this.fScrollNewMenuPosY = 0.0f;
        this.scrollModeX = false;
        this.iScrollPosX = -1;
        this.iScrollPosX2 = -1;
        this.fScrollNewMenuPosX = 0.0f;
        this.drawScrollPositionAlways = true;
        this.drawScrollPositionAlways2 = true;
        this.scrollExtraPosX = 0;
        this.COLOR_SCROLL_POSITION = new Color(0.039215688f, 0.039215688f, 0.078431375f, 0.4f);
        this.COLOR_SCROLL_POSITION_INVIEW = new Color(0.50980395f, 0.42352942f, 0.23529412f, 0.2f);
        this.COLOR_SCROLL_POSITION_INVIEW_ACTIVE = new Color(0.50980395f, 0.47058824f, 0.27450982f, 0.6f);
    }
    
    public void initCloseMenu() {
        this.menuClose = new MenuClose() {
            @Override
            public int getCloseMenu_PosX() {
                return Menu.this.getPosX() + Menu.this.getWidth() - this.getCloseMenu_Width();
            }
            
            @Override
            public int getCloseMenu_PosY() {
                if (Menu.this.menuTitle != null) {
                    return Menu.this.getPosY() - Menu.this.menuTitle.getHeight();
                }
                return Menu.this.getPosY();
            }
            
            @Override
            public int getCloseMenu_Width() {
                return ImageManager.getImage(Images.btn_close).getWidth();
            }
            
            @Override
            public int getCloseMenu_Height() {
                return ImageManager.getImage(Images.btn_close).getHeight();
            }
        };
    }
    
    protected final void initMenu(final MenuTitle menuTitle, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final List<MenuElement> menuElements) {
        this.initMenu(menuTitle, iPosX, iPosY, iWidth, iHeight, menuElements, true, false, false, false);
    }
    
    protected final void initMenu(final MenuTitle menuTitle, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final List<MenuElement> menuElements, final boolean visible) {
        this.initMenu(menuTitle, iPosX, iPosY, iWidth, iHeight, menuElements, visible, false, false, false);
    }
    
    protected final void initMenu(final MenuTitle menuTitle, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final List<MenuElement> menuElements, final boolean visible, final boolean closeable) {
        this.initMenu(menuTitle, iPosX, iPosY, iWidth, iHeight, menuElements, visible, false, closeable, false);
    }
    
    protected final void initMenuWithBackButton(final MenuTitle menuTitle, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final List<MenuElement> menuElements) {
        this.initMenu(menuTitle, iPosX, iPosY, iWidth, iHeight, menuElements, true, true, false, false);
    }
    
    protected final void initMenuWithBackButton(final MenuTitle menuTitle, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final List<MenuElement> menuElements, final boolean closeable) {
        this.initMenu(menuTitle, iPosX, iPosY, iWidth, iHeight, menuElements, true, true, closeable, false);
    }
    
    protected final void initMenuWithBackButton(final MenuTitle menuTitle, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final List<MenuElement> menuElements, final boolean visible, final boolean closeable) {
        this.initMenu(menuTitle, iPosX, iPosY, iWidth, iHeight, menuElements, visible, true, closeable, false);
    }
    
    protected final void initMenuWithBackButton(final MenuTitle menuTitle, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final List<MenuElement> menuElements, final boolean visible, final boolean closeable, final boolean lockHoverOverMenuBackground) {
        this.initMenu(menuTitle, iPosX, iPosY, iWidth, iHeight, menuElements, visible, true, closeable, lockHoverOverMenuBackground);
    }
    
    protected final void initMenu(final MenuTitle menuTitle, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final List<MenuElement> menuElements, final boolean visible, final boolean initWithBackButton, final boolean closeable, final boolean lockHoverOverMenuBackground) {
        this.iNewMenuPositionX = iPosX;
        this.iMenuPosX = iPosX;
        this.iPosX = iPosX;
        this.iNewMenuPositionY = iPosY;
        this.iMenuPosY = iPosY;
        this.iPosY = iPosY;
        this.iWidth = iWidth;
        this.iHeight = iHeight;
        this.closeable = closeable;
        this.visible = visible;
        this.lockHoverOverMenuBackground = lockHoverOverMenuBackground;
        this.menuTitle = menuTitle;
        this.iMenuElementsSize = menuElements.size();
        if (initWithBackButton) {
            int tempMaxY = 0;
            for (int i = 0; i < this.iMenuElementsSize; ++i) {
                if (menuElements.get(i).getPosY() + menuElements.get(i).getHeight() > tempMaxY) {
                    tempMaxY = menuElements.get(i).getPosY() + menuElements.get(i).getHeight();
                }
            }
            menuElements.get(0).setPosY(tempMaxY + CFG.PADDING);
            if (tempMaxY > iHeight - CFG.PADDING - menuElements.get(0).getHeight()) {
                menuElements.get(0).setPosY(tempMaxY + CFG.PADDING);
            }
            else {
                menuElements.get(0).setPosY(iHeight - menuElements.get(0).getHeight());
            }
        }
        this.menuElements = menuElements;
        if (closeable) {
            this.initCloseMenu();
        }
        this.updateScrollable();
        this.updateMenuElements_IsInView();
        this.updateLanguage();
    }
    
    public final void updateScrollable() {
        final int n = 0;
        this.iMaxSliderPositionX = n;
        this.iMaxSliderPositionY = n;
        for (int i = 0; i < this.iMenuElementsSize; ++i) {
            if (this.menuElements.get(i).getPosY() + this.menuElements.get(i).getHeight() > this.iMaxSliderPositionY) {
                this.iMaxSliderPositionY = this.menuElements.get(i).getPosY() + this.menuElements.get(i).getHeight();
            }
            if (this.menuElements.get(i).getPosX() + this.menuElements.get(i).getWidth() > this.iMaxSliderPositionX) {
                this.iMaxSliderPositionX = this.menuElements.get(i).getPosX() + this.menuElements.get(i).getWidth();
            }
        }
        this.scrollableX = (this.iMaxSliderPositionX > this.getWidth());
        this.scrollableY = (this.iMaxSliderPositionY > this.iHeight);
        if (this.scrollableY) {
            this.updateMenuPosY(this.iPosY);
        }
        if (this.scrollableX) {
            this.updateMenuPosX(this.iPosX);
        }
    }
    
    public void updateLanguage() {
        try {
            for (int i = 0; i < this.getMenuElementsSize(); ++i) {
                this.getMenuElement(i).updateLanguage();
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public void update() {
        if (this.scrollModeY) {
            if (Math.abs(this.fScrollNewMenuPosY) > 1.0f) {
                this.updateMenuPosY(this.iMenuPosY + (int)this.fScrollNewMenuPosY);
                this.fScrollNewMenuPosY *= 0.97f;
            }
            else {
                this.scrollModeY = false;
            }
        }
        if (this.scrollModeX) {
            if (Math.abs(this.fScrollNewMenuPosX) > 1.0f) {
                this.updateMenuPosX(this.iMenuPosX + (int)this.fScrollNewMenuPosX);
                this.fScrollNewMenuPosX *= 0.97f;
            }
            else {
                this.scrollModeX = false;
            }
        }
        boolean updateMenuElementsInView = false;
        if (this.scrollableX && this.iNewMenuPositionX != this.iMenuPosX) {
            this.iMenuPosX = this.iNewMenuPositionX;
            updateMenuElementsInView = true;
        }
        if (this.iNewMenuPositionY != this.iMenuPosY) {
            this.iMenuPosY = this.iNewMenuPositionY;
            updateMenuElementsInView = true;
        }
        if (updateMenuElementsInView) {
            this.updateMenuElements_IsInView();
        }
    }
    
    public void extraAction() {
    }
    
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        this.drawView(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    private void drawView(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        this.beginClip(oSB, iTranslateX, iTranslateY, menuIsActive);
        this.drawMenu(oSB, iTranslateX, iTranslateY, menuIsActive);
        this.endClip(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    public void beginClip(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive) {
        this.drawBackgroundMode(oSB, menuIsActive);
        Renderer.clipView_Start(oSB, this.getPosX() + iTranslateX, CFG.GAME_HEIGHT - this.getPosY() - iTranslateY, this.getWidth(), -this.getHeight());
    }
    
    public void drawMenu(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive) {
        this.drawMenuElements(oSB, iTranslateX, iTranslateY, menuIsActive);
        this.drawMenu_Scroll(oSB, iTranslateX, iTranslateY, menuIsActive);
    }
    
    public void drawMenu_Scroll(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive) {
        this.drawScrollPositionX(oSB, iTranslateX, iTranslateY, menuIsActive);
        this.drawScrollPositionY(oSB, iTranslateX, iTranslateY, menuIsActive);
    }
    
    public void endClip(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        Renderer.clipView_End(oSB);
        this.drawTitle(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
        if (this.getCloseable()) {
            this.drawCloseButton(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus == Status.CLOSE_HOVERED);
        }
    }
    
    public void drawHover(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final int nMenuElementID) {
        try {
            this.getMenuElement(nMenuElementID).drawMenuElementHover(oSB, this.getMenuPosX() + iTranslateX, this.getMenuPosY() + iTranslateY, this.getMenuElementIsActive(true, Game.menuManager.getActiveMenuElementID()));
        }
        catch (final Exception ex) {}
    }
    
    public void drawScrollPositionY(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive) {
        try {
            if (this.drawScrollPositionAlways2 && this.scrollableY && this.getHeight() < this.iMaxSliderPositionY) {
                if (Game.menuManager.getMenu_MoveInnerElements()) {
                    oSB.setColor(this.COLOR_SCROLL_POSITION_INVIEW_ACTIVE);
                }
                else {
                    oSB.setColor(this.COLOR_SCROLL_POSITION_INVIEW);
                }
                ImageManager.getImage(Images.scroll_position_active).draw2(oSB, this.getPosX() + this.scrollExtraPosX + this.getWidth() - Images.boxTitleBORDERWIDTH - Images.boxTitleBORDERWIDTH - 1 + iTranslateX, this.getPosY() + (this.getHeight() - 100 * this.getHeight() / this.iMaxSliderPositionY * this.getHeight() / 100) * (this.getPosY() - this.getMenuPosY()) / (this.iMaxSliderPositionY - this.getHeight()) + iTranslateY, Images.boxTitleBORDERWIDTH, this.getHeight() * 100 / this.iMaxSliderPositionY * this.getHeight() / 100 - ImageManager.getImage(Images.scroll_position_active).getHeight());
                ImageManager.getImage(Images.scroll_position_active).draw(oSB, this.getPosX() + this.scrollExtraPosX + this.getWidth() - Images.boxTitleBORDERWIDTH - Images.boxTitleBORDERWIDTH - 1 + iTranslateX, this.getPosY() + (this.getHeight() - 100 * this.getHeight() / this.iMaxSliderPositionY * this.getHeight() / 100) * (this.getPosY() - this.getMenuPosY()) / (this.iMaxSliderPositionY - this.getHeight()) + this.getHeight() * 100 / this.iMaxSliderPositionY * this.getHeight() / 100 - ImageManager.getImage(Images.scroll_position_active).getHeight() + iTranslateY, Images.boxTitleBORDERWIDTH, ImageManager.getImage(Images.scroll_position_active).getHeight(), false, true);
                ImageManager.getImage(Images.gradientXYVertical).draw2(oSB, this.getPosX() + this.scrollExtraPosX + this.getWidth() - Images.boxTitleBORDERWIDTH - Images.boxTitleBORDERWIDTH - 1 + iTranslateX, this.getPosY() + (this.getHeight() - 100 * this.getHeight() / this.iMaxSliderPositionY * this.getHeight() / 100) * (this.getPosY() - this.getMenuPosY()) / (this.iMaxSliderPositionY - this.getHeight()) + iTranslateY, Images.boxTitleBORDERWIDTH, this.getHeight() * 100 / this.iMaxSliderPositionY * this.getHeight() / 100, true, false);
                oSB.setColor(Color.WHITE);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public void drawScrollPositionX(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive) {
        try {
            if ((this.drawScrollPositionAlways || menuIsActive) && this.scrollableX && this.getWidth() < this.iMaxSliderPositionX) {
                if (Game.menuManager.getMenu_MoveInnerElements()) {
                    oSB.setColor(this.COLOR_SCROLL_POSITION_INVIEW_ACTIVE);
                }
                else {
                    oSB.setColor(this.COLOR_SCROLL_POSITION_INVIEW);
                }
                ImageManager.getImage(Images.scroll_position_active).draw2(oSB, this.getPosX() + (this.getWidth() - 100 * this.getWidth() / this.iMaxSliderPositionX * this.getWidth() / 100) * (this.getPosX() - this.getMenuPosX()) / (this.iMaxSliderPositionX - this.getWidth()) + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING * 2 + 1 - (this.getWidth() * 100 / this.iMaxSliderPositionX * this.getWidth() / 100 - ImageManager.getImage(Images.scroll_position_active).getHeight()) + iTranslateY, ImageManager.getImage(Images.scroll_position_active).getWidth(), this.getWidth() * 100 / this.iMaxSliderPositionX * this.getWidth() / 100 - ImageManager.getImage(Images.scroll_position_active).getHeight(), -90.0f, false, true);
                ImageManager.getImage(Images.scroll_position_active).draw2(oSB, this.getPosX() + (this.getWidth() - 100 * this.getWidth() / this.iMaxSliderPositionX * this.getWidth() / 100) * (this.getPosX() - this.getMenuPosX()) / (this.iMaxSliderPositionX - this.getWidth()) + this.getWidth() * 100 / this.iMaxSliderPositionX * this.getWidth() / 100 - ImageManager.getImage(Images.scroll_position_active).getHeight() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING * 2 + 1 - ImageManager.getImage(Images.scroll_position_active).getHeight() + iTranslateY, ImageManager.getImage(Images.scroll_position_active).getWidth(), ImageManager.getImage(Images.scroll_position_active).getHeight(), -90.0f);
                oSB.setColor(Color.WHITE);
            }
        }
        catch (final Exception ex) {}
    }
    
    public final void drawMenuElements(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive) {
        try {
            for (int i = this.iMenuElementsSize - 1; i >= 0; --i) {
                try {
                    if (this.menuElements.get(i).getVisible() && this.menuElements.get(i).getIsInView()) {
                        this.menuElements.get(i).draw(oSB, this.getMenuPosX() + iTranslateX, this.getMenuPosY() + iTranslateY, this.getMenuElementIsActive(menuIsActive, i), this.scrollableY);
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
    }
    
    public void updateMenuElements_IsInView() {
        for (int i = 0; i < this.iMenuElementsSize; ++i) {
            this.menuElements.get(i).setIsInView(this.getMenuElementIsInView_Y(i) && this.getMenuElementIsInView_X(i));
        }
    }
    
    public void updateMenuElements_IsInView_X() {
        for (int i = 0; i < this.iMenuElementsSize; ++i) {
            this.menuElements.get(i).setIsInView(this.getMenuElementIsInView_X(i));
        }
    }
    
    private final boolean getMenuElementIsInView_Y(final int i) {
        return (this.menuElements.get(i).getPosY() + this.getMenuPosY() > this.getPosY() && this.menuElements.get(i).getPosY() + this.getMenuPosY() < this.getPosY() + this.getHeight()) || (this.menuElements.get(i).getPosY() + this.menuElements.get(i).getHeight() + this.getMenuPosY() > this.getPosY() && this.menuElements.get(i).getPosY() + this.menuElements.get(i).getHeight() + this.getMenuPosY() < this.getPosY() + this.getHeight()) || (this.menuElements.get(i).getPosY() + this.getMenuPosY() <= this.getPosY() && this.menuElements.get(i).getPosY() + this.menuElements.get(i).getHeight() + this.getMenuPosY() >= this.getPosY() + this.getHeight());
    }
    
    private final boolean getMenuElementIsInView_X(final int i) {
        return (this.menuElements.get(i).getPosX() + this.getMenuPosX() >= this.getPosX() && this.menuElements.get(i).getPosX() + this.getMenuPosX() < this.getPosX() + this.getWidth()) || (this.menuElements.get(i).getPosX() + this.menuElements.get(i).getWidth() + this.getMenuPosX() > this.getPosX() && this.menuElements.get(i).getPosX() + this.menuElements.get(i).getWidth() + this.getMenuPosX() <= this.getPosX() + this.getWidth()) || (this.getPosX() > this.menuElements.get(i).getPosX() + this.getMenuPosX() && this.getPosX() < this.menuElements.get(i).getPosX() + this.menuElements.get(i).getWidth() + this.getMenuPosX());
    }
    
    public boolean getMenuElementIsActive(final boolean menuIsActive, final int i) {
        return menuIsActive && i == Game.menuManager.getActiveMenuElementID();
    }
    
    public void drawTitle(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (this.menuTitle != null) {
            this.menuTitle.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), titleStatus);
        }
        if (menuIsActive) {
            if (Game.menuManager.getMenuResizeMode()) {
                this.drawMenuBorder(oSB);
                this.drawMenuResizeRect(oSB);
            }
            else if (Game.menuManager.getMoveMenu_ByTitleMode()) {
                this.drawMenuBorder(oSB);
            }
        }
    }
    
    public final void drawMenuBorder(final SpriteBatch oSB) {
        oSB.setColor(0.196f, 0.196f, 0.196f, 1.0f);
        Images.pix.draw(oSB, this.getPosX(), this.getPosY(), 1, this.getHeight());
        Images.pix.draw(oSB, this.getPosX() + this.getWidth() - 1, this.getPosY(), 1, this.getHeight());
        Images.pix.draw(oSB, this.getPosX(), this.getPosY(), this.getWidth(), -1);
        Images.pix.draw(oSB, this.getPosX(), this.getPosY() + this.getHeight() - 1, this.getWidth(), -1);
        oSB.setColor(Color.WHITE);
    }
    
    public final void drawMenuResizeRect(final SpriteBatch oSB) {
        oSB.setColor(0.196f, 0.196f, 0.196f, 0.95f);
        if (Game.menuManager.getMenuResizeLEFT()) {
            Images.pix.draw(oSB, this.getPosX(), this.getPosY() + this.getHeight() - CFG.PADDING * CFG.PADDING, CFG.PADDING * CFG.PADDING, CFG.PADDING * CFG.PADDING);
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.35f));
            ImageManager.getImage(Images.pickerEdge).draw(oSB, this.getPosX(), this.getPosY() + this.getHeight() - ImageManager.getImage(Images.pickerEdge).getHeight(), ImageManager.getImage(Images.pickerEdge).getWidth(), ImageManager.getImage(Images.pickerEdge).getHeight(), true, false);
        }
        else {
            Images.pix.draw(oSB, this.getPosX() + this.getWidth() - 1 - CFG.PADDING * CFG.PADDING, this.getPosY() + this.getHeight() - CFG.PADDING * CFG.PADDING, CFG.PADDING * CFG.PADDING, CFG.PADDING * CFG.PADDING);
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.35f));
            ImageManager.getImage(Images.pickerEdge).draw(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.pickerEdge).getWidth(), this.getPosY() + this.getHeight() - ImageManager.getImage(Images.pickerEdge).getHeight(), ImageManager.getImage(Images.pickerEdge).getWidth(), ImageManager.getImage(Images.pickerEdge).getHeight(), false, false);
        }
        oSB.setColor(Color.WHITE);
    }
    
    public final void drawBackgroundMode(final SpriteBatch oSB, final boolean menuIsActive) {
        if (menuIsActive && (Game.menuManager.getMenuResizeMode() || Game.menuManager.getMoveMenu_ByTitleMode())) {
            oSB.setColor(new Color(0.1f, 0.1f, 0.1f, 0.05f));
            ImageManager.getImage(Images.patt).draw2(oSB, 0, -ImageManager.getImage(Images.patt).getHeight(), CFG.GAME_WIDTH, CFG.GAME_HEIGHT);
            oSB.setColor(Color.WHITE);
        }
    }
    
    public void drawCloseButton(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive, final boolean isHovered) {
        if (Game.menuManager.getCloseMenuMode() && menuIsActive) {
            ImageManager.getImage(Images.btnh_close).draw(oSB, this.menuClose.getCloseMenu_PosX() + iTranslateX, this.menuClose.getCloseMenu_PosY() + iTranslateY);
        }
        else if (isHovered) {
            oSB.setColor(new Color(1.0f, 0.0f, 0.0f, 0.425f));
            ImageManager.getImage(Images.btnh_close).draw(oSB, this.menuClose.getCloseMenu_PosX() + iTranslateX, this.menuClose.getCloseMenu_PosY() + iTranslateY);
            oSB.setColor(Color.WHITE);
        }
        else {
            ImageManager.getImage(Images.btn_close).draw(oSB, this.menuClose.getCloseMenu_PosX() + iTranslateX, this.menuClose.getCloseMenu_PosY() + iTranslateY);
        }
    }
    
    public void actionElement(final int nMenuElementID) {
        try {
            this.getMenuElement(nMenuElementID).actionElement();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public void actionElementPPM(final int nMenuElementID) {
        try {
            this.getMenuElement(nMenuElementID).actionElementPPM();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public void onBackPressed() {
    }
    
    public void onMenuPressed() {
    }
    
    public void actionCloseMenu() {
        this.setVisible(false);
        this.actionCloseMenu_Sound();
    }
    
    public void actionCloseMenu_Sound() {
        Game.soundsManager.playSound(Game.soundsManager.getClickMain());
    }
    
    public void onHovered() {
    }
    
    public final void updateMenuPosX(final int nMenuPosX) {
        try {
            if (nMenuPosX > this.getPosX()) {
                this.iNewMenuPositionX = this.getPosX();
                Game.menuManager.setUpdateSliderMenuPosX(true);
            }
            else if (nMenuPosX < this.getWidth() + this.getPosX() - this.iMaxSliderPositionX) {
                this.iNewMenuPositionX = this.getWidth() + this.getPosX() - this.iMaxSliderPositionX;
                Game.menuManager.setUpdateSliderMenuPosX(true);
            }
            else {
                this.iNewMenuPositionX = nMenuPosX;
            }
        }
        catch (final Exception ex) {}
    }
    
    public final void updateMenuPosY(final int nMenuPosY) {
        try {
            if (!this.scrollableY) {
                this.iNewMenuPositionY = this.getPosY();
                Game.menuManager.setUpdateSliderMenuPosY(false);
                this.scrollModeY = false;
                return;
            }
            if (nMenuPosY > this.getPosY()) {
                this.iNewMenuPositionY = this.getPosY();
                Game.menuManager.setUpdateSliderMenuPosY(true);
                this.scrollModeY = false;
            }
            else if (nMenuPosY < this.getHeight() + this.getPosY() - this.iMaxSliderPositionY) {
                this.iNewMenuPositionY = this.getHeight() + this.getPosY() - this.iMaxSliderPositionY;
                Game.menuManager.setUpdateSliderMenuPosY(true);
                this.scrollModeY = false;
            }
            else {
                this.iNewMenuPositionY = nMenuPosY;
            }
        }
        catch (final Exception ex) {}
    }
    
    public final void scrollTheMenu() {
        if (this.scrollableY && this.iScrollPosY > 0 && this.iScrollPosY2 > 0 && Math.abs(this.iScrollPosY - this.iScrollPosY2) > 3.0f * CFG.DENSITY) {
            this.fScrollNewMenuPosY = (this.iScrollPosY - this.iScrollPosY2) * 1.45f;
            this.scrollModeY = true;
        }
        if (this.scrollableX && this.iScrollPosX > 0 && this.iScrollPosX2 > 0 && Math.abs(this.iScrollPosX - this.iScrollPosX2) > 3) {
            this.fScrollNewMenuPosX = (this.iScrollPosX - this.iScrollPosX2) * 1.45f;
            this.scrollModeX = true;
        }
        this.resetScrollINFO();
    }
    
    private final void resetScrollINFO() {
        final int n = -1;
        this.iScrollPosX2 = n;
        this.iScrollPosX = n;
        this.iScrollPosY2 = n;
        this.iScrollPosY = n;
    }
    
    public final void stopScrolling() {
        this.resetScrollINFO();
        final boolean b = false;
        this.scrollModeX = b;
        this.scrollModeY = b;
    }
    
    public final void updatedButtonsWidth(int iStartPosX, final int iMinWidth) {
        for (int i = 0; i < this.getMenuElementsSize(); ++i) {
            iStartPosX += this.updateButtonWidth(i, iStartPosX, iMinWidth) + CFG.PADDING;
        }
        this.updateScrollable();
    }
    
    public final void updatedButtonsWidth_Padding(int iStartPosX, final int iMinWidth, final int iPadding) {
        for (int i = 0; i < this.getMenuElementsSize(); ++i) {
            iStartPosX += this.updateButtonWidth(i, iStartPosX, iMinWidth) + iPadding;
        }
        this.updateScrollable();
    }
    
    public final void updatedButtonsWidthFromToID(final int iStartButtonID, final int iEndButtonID, int iStartPosX, final int iMinWidth) {
        for (int i = iStartButtonID; i < iEndButtonID; ++i) {
            iStartPosX += this.updateButtonWidth(i, iStartPosX, iMinWidth) + CFG.PADDING;
        }
        this.updateScrollable();
    }
    
    public final int updateButtonWidth(final int iButtonID, final int iStartPosX, final int iMinWidth) {
        if (this.getMenuElement(iButtonID).getTextWidth() + CFG.PADDING * 4 > iMinWidth) {
            this.getMenuElement(iButtonID).setWidth(this.getMenuElement(iButtonID).getTextWidth() + CFG.PADDING * 4);
        }
        else {
            this.getMenuElement(iButtonID).setWidth(iMinWidth);
        }
        this.getMenuElement(iButtonID).setPosX(iStartPosX);
        this.updateScrollable();
        return this.getMenuElement(iButtonID).getWidth();
    }
    
    public final int getMenuElementsSize() {
        return this.iMenuElementsSize;
    }
    
    public final MenuElement getMenuElement(final int iID) {
        return this.menuElements.get(iID);
    }
    
    public final void setMenuElement(final int iID, final MenuElement nMenuElement) {
        this.menuElements.set(iID, null);
        this.menuElements.set(iID, nMenuElement);
    }
    
    public int getPosX() {
        return this.iPosX;
    }
    
    public void setPosX(final int iPosX) {
        this.iPosX = iPosX;
        this.updateMenuPosX(this.iMenuPosX = iPosX);
    }
    
    public void setPosX_Just(final int iPosX) {
        this.iPosX = iPosX;
    }
    
    public final void setPosX_Force(final int iPosX) {
        this.iPosX = iPosX;
        this.iMenuPosX = iPosX;
        this.iNewMenuPositionX = iPosX;
        Game.menuManager.setUpdateSliderMenuPosX(false);
    }
    
    public int getPosY() {
        return this.iPosY;
    }
    
    public void setPosY(final int iPosY) {
        this.iPosY = iPosY;
        this.updateMenuPosY(this.iMenuPosY = iPosY);
    }
    
    public void setPosY_Just(final int iPosY) {
        this.iPosY = iPosY;
    }
    
    public int getWidth() {
        return this.iWidth;
    }
    
    public boolean setWidth(final int iWidth) {
        if (iWidth >= CFG.GAME_WIDTH) {
            this.iWidth = CFG.GAME_WIDTH;
            return true;
        }
        if (iWidth < this.getMinWidth()) {
            this.iWidth = this.getMinWidth();
            return false;
        }
        this.iWidth = iWidth;
        return true;
    }
    
    public boolean setWidth_Resize(int iWidth) {
        if (iWidth >= CFG.GAME_WIDTH) {
            this.setPosX(this.getPosX() + (this.iWidth - iWidth));
            this.iWidth = CFG.GAME_WIDTH;
            return true;
        }
        if (iWidth < this.getMinWidth()) {
            iWidth = this.getMinWidth();
            this.setPosX(this.getPosX() + (this.iWidth - iWidth));
            this.iWidth = iWidth;
            return false;
        }
        this.setPosX(this.getPosX() + (this.iWidth - iWidth));
        this.iWidth = iWidth;
        return true;
    }
    
    public final int getMinWidth() {
        try {
            return CFG.BUTTON_HEIGHT;
        }
        catch (final Exception ex) {
            return CFG.BUTTON_HEIGHT;
        }
    }
    
    public int getHeight() {
        return this.iHeight;
    }
    
    public void setHeight(final int iHeight) {
        this.iHeight = iHeight;
        if (iHeight < this.getMinHeight()) {
            this.iHeight = this.getMinHeight();
        }
        if (iHeight + this.getPosY() + ((this.menuTitle != null) ? this.menuTitle.getHeight() : 0) >= CFG.GAME_HEIGHT) {
            this.iHeight = CFG.GAME_HEIGHT - (this.getPosY() + ((this.menuTitle != null) ? this.menuTitle.getHeight() : 0));
        }
        this.updateScrollable();
    }
    
    public final int getMinHeight() {
        return CFG.PADDING + CFG.BUTTON_HEIGHT;
    }
    
    public final MenuTitle getTitle() {
        return this.menuTitle;
    }
    
    public final boolean getScrollableY() {
        return this.scrollableY;
    }
    
    public final void setMenuPosY(final int iMenuPosY) {
        this.updateMenuPosY(iMenuPosY);
    }
    
    public int getMenuPosY() {
        return this.iMenuPosY;
    }
    
    public final int getNewMenuPosY() {
        return this.iNewMenuPositionY;
    }
    
    public final int getNewMenuPosX() {
        return this.iNewMenuPositionX;
    }
    
    public final boolean getScrollableX() {
        return this.scrollableX;
    }
    
    public final void setMenuPosX(final int iMenuPosX) {
        this.updateMenuPosX(iMenuPosX);
    }
    
    public int getMenuPosX() {
        return this.iMenuPosX;
    }
    
    public boolean getVisible() {
        return this.visible;
    }
    
    public void setVisible(final boolean visible) {
        this.visible = visible;
    }
    
    public final boolean getCloseable() {
        return this.closeable;
    }
    
    public final boolean getMoveable() {
        return this.menuTitle != null && this.menuTitle.getMoveable();
    }
    
    public final boolean getResizable() {
        return this.menuTitle != null && this.menuTitle.getResizable();
    }
    
    public final void setScrollPosY(final int iScrollPosY) {
        this.iScrollPosY2 = this.iScrollPosY;
        this.iScrollPosY = iScrollPosY;
    }
    
    public final int getScrollPosY() {
        return this.iScrollPosY;
    }
    
    public final void setScrollPosX(final int iScrollPosX) {
        this.iScrollPosX2 = this.iScrollPosX;
        this.iScrollPosX = iScrollPosX;
    }
    
    public final boolean getScrollModeY() {
        return this.scrollModeY;
    }
    
    public final boolean getLockHoverOverMenuBackground() {
        return this.lockHoverOverMenuBackground;
    }
    
    public void disableButtons() {
    }
    
    public void closeMenu() {
    }
    
    public void dispose() {
        for (int i = 0; i < this.getMenuElementsSize(); ++i) {
            this.getMenuElement(i).dispose();
        }
    }
    
    interface MenuClose
    {
        int getCloseMenu_PosX();
        
        int getCloseMenu_PosY();
        
        int getCloseMenu_Width();
        
        int getCloseMenu_Height();
    }
}
