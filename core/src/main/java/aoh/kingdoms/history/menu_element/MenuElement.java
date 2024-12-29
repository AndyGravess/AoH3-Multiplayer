// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element;

import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.Touch;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;

public class MenuElement
{
    protected MenuElement_Type typeOfElement;
    private int iPosX;
    private int iPosY;
    private int iWidth;
    private int iHeight;
    private boolean isClickable;
    private boolean isVisible;
    private boolean isInView;
    private boolean isHovered;
    protected int iCurrent;
    protected int fontID;
    public MenuElement_Hover menuElementHover;
    
    public void buildElementHover() {
    }
    
    public void resetElementHover() {
        this.menuElementHover = null;
    }
    
    public void drawMenuElementHover(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (this.menuElementHover != null) {
            if (CFG.isAndroid) {
                this.menuElementHover.drawAlwaysOver_Mobile(oSB, Touch.getMousePosX(), Touch.getMousePosY() - Renderer.getHover_ExtraPosY());
            }
            else {
                this.menuElementHover.draw(oSB, Touch.getMousePosX() + Renderer.getHover_ExtraPosX(), Touch.getMousePosY() + Renderer.getHover_ExtraPosY());
            }
        }
    }
    
    public boolean getMenuElement_Hover_IsNull() {
        return this.menuElementHover == null;
    }
    
    public void updateHover(final int nPosX, final int nPosY, final int menuPosX, final int menuPosY) {
    }
    
    protected MenuElement() {
        this.isClickable = true;
        this.isVisible = true;
        this.isInView = false;
        this.isHovered = false;
        this.iCurrent = 0;
        this.fontID = 0;
    }
    
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY) {
    }
    
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
    }
    
    public void setText(final String sText) {
    }
    
    public void setText2(final String sText) {
    }
    
    public boolean getCheckboxState() {
        return false;
    }
    
    public void setCheckboxState(final boolean checkboxState) {
    }
    
    public void setCurrent(final int nCurrent) {
        this.iCurrent = nCurrent;
    }
    
    public int getCurrent() {
        return this.iCurrent;
    }
    
    public void setMin(final int iMin) {
    }
    
    public void setMax(final int iMax) {
    }
    
    public int getValue1() {
        return 0;
    }
    
    public int getValue2() {
        return 0;
    }
    
    public boolean getDescription() {
        return false;
    }
    
    public void setDescription(final boolean isDescriptionActive) {
    }
    
    public boolean getMoveable() {
        return false;
    }
    
    public boolean getInStatisticsMode() {
        return false;
    }
    
    public void setInStatisticsMode(final boolean inStatisticsMode) {
    }
    
    public boolean getScrollable() {
        return false;
    }
    
    public void setScrollable(final boolean scrollable) {
    }
    
    public int getScrollPosX() {
        return 0;
    }
    
    public void setScrollPosX(final int scrollPosX) {
    }
    
    public int getScrollPosY() {
        return 0;
    }
    
    public void setScrollPosY(final int scrollPosY) {
    }
    
    public void scrollTheMenu() {
    }
    
    public void scrollByWheel(final int nScoll) {
    }
    
    public void stopScrolling() {
    }
    
    public void updateLanguage() {
    }
    
    public void actionElement() {
    }
    
    public void actionElementPPM() {
    }
    
    public void actionElement_ExtraAction() {
    }
    
    public void updateSlider(final int nPosX) {
    }
    
    public final MenuElement_Type getTypeOfElement() {
        return this.typeOfElement;
    }
    
    public void setTypeOfElement(final MenuElement_Type typeOfElement) {
    }
    
    public boolean getClickable() {
        return this.isClickable;
    }
    
    public final void setClickable(final boolean isClickable) {
        this.isClickable = isClickable;
    }
    
    public boolean getVisible() {
        return this.isVisible;
    }
    
    public void setVisible(final boolean isVisible) {
        this.isVisible = isVisible;
    }
    
    public int getPosX() {
        return this.iPosX;
    }
    
    public final void setPosX(final int iPosX) {
        this.iPosX = iPosX;
    }
    
    public int getPosY() {
        return this.iPosY;
    }
    
    public final void setPosY(final int iPosY) {
        this.iPosY = iPosY;
    }
    
    public int getWidth() {
        return this.iWidth;
    }
    
    public void setWidth(final int iWidth) {
        this.iWidth = iWidth;
    }
    
    public int getHeight() {
        return this.iHeight;
    }
    
    public final void setHeight(final int iHeight) {
        this.iHeight = iHeight;
    }
    
    public String getText() {
        return "";
    }
    
    public int getTextWidth() {
        return 0;
    }
    
    public int getTextHeight() {
        return 0;
    }
    
    public int getTextPos() {
        return 0;
    }
    
    public final boolean getIsInView() {
        return this.isInView;
    }
    
    public final void setIsInView(final boolean isInView) {
        this.isInView = isInView;
    }
    
    public boolean getIsHovered() {
        return this.isHovered;
    }
    
    public void setIsHovered(final boolean isHovered) {
        this.isHovered = isHovered;
    }
    
    public void updateHovered() {
    }
    
    public int getSFX() {
        return Game.soundsManager.getClickMain();
    }
    
    public boolean playSFX_Hovered() {
        return true;
    }
    
    public int getSFX_Hovered() {
        return Game.soundsManager.getClickMain();
    }
    
    public String getTextToDraw() {
        return "";
    }
    
    public void dispose() {
    }
    
    public boolean canBeHovered() {
        return true;
    }
}
