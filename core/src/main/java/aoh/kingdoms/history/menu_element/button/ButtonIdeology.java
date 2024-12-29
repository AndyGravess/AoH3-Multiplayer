// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Game;

public class ButtonIdeology extends Button
{
    public int ideologyID;
    public static int maxHeight;
    
    public ButtonIdeology(final int ideologyID, final int iPosX, final int iPosY) {
        this.ideologyID = ideologyID;
        this.init(Game.ideologiesManager.getIdeology(ideologyID).Name, CFG.FONT_REGULAR_SMALL, this.iTextPositionX, iPosX, iPosY, getButtonWidth(), getButtonHeight(), true, true, false, false);
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        Game.ideologiesManager.ideologiesImages.get(this.ideologyID).draw(oSB, this.getPosX() + this.getWidth() / 2 - Game.ideologiesManager.ideologiesImages.get(this.ideologyID).getWidth() / 2 + iTranslateX, this.getPosY() + getImageHeight() / 2 - Game.ideologiesManager.ideologiesImages.get(this.ideologyID).getHeight() / 2 + iTranslateY);
        Renderer.drawText(oSB, this.fontID, this.getText(), this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() - CFG.TEXT_HEIGHT - CFG.PADDING + iTranslateY, this.getColor(isActive));
    }
    
    public static int getButtonWidth() {
        return Game.ideologiesManager.maxWidth + CFG.PADDING * 4;
    }
    
    public static int getImageHeight() {
        return ButtonIdeology.maxHeight + CFG.PADDING * 4;
    }
    
    public static int getButtonHeight() {
        return getImageHeight() + CFG.TEXT_HEIGHT + CFG.PADDING * 2;
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Government") + ": ", CFG.FONT_BOLD));
        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.ideologiesManager.getIdeology(this.ideologyID).Name, CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover(nElements);
    }
}
