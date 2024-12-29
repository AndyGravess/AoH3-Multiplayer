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
import aoh.kingdoms.history.menu_element.textStatic.TextIcon2;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Game;

public class ButtonIdeology_Court extends Button
{
    public int ideologyID;
    public int imgWidth;
    public int imgHeight;
    
    public ButtonIdeology_Court(final int ideologyID, final int iPosX, final int iPosY, final int iWidth, final int iHeight) {
        this.ideologyID = ideologyID;
        this.init(Game.ideologiesManager.getIdeology(ideologyID).Name, CFG.FONT_REGULAR_SMALL, this.iTextPositionX, iPosX, iPosY, iWidth, iHeight, true, true, false, false);
        if (ImageManager.getImage(Images.population).getHeight() < Game.ideologiesManager.ideologiesImages.get(ideologyID).getHeight()) {
            final float fScale = ImageManager.getImage(Images.population).getHeight() / (float)Game.ideologiesManager.ideologiesImages.get(ideologyID).getHeight();
            this.imgWidth = (int)(Game.ideologiesManager.ideologiesImages.get(ideologyID).getWidth() * fScale);
            this.imgHeight = (int)(Game.ideologiesManager.ideologiesImages.get(ideologyID).getHeight() * fScale);
        }
        else {
            this.imgWidth = Game.ideologiesManager.ideologiesImages.get(ideologyID).getWidth();
            this.imgHeight = Game.ideologiesManager.ideologiesImages.get(ideologyID).getHeight();
        }
        int tWMax = 0;
        while (this.iTextWidth > this.getWidth() && this.getText().length() > 5 && ++tWMax < 100) {
            this.setText(this.getText().substring(0, Math.max(1, this.getText().length() - 3)) + "..");
        }
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(TextIcon2.getColor_gradientXY());
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - getTextHeightBG() + iTranslateY, this.getWidth(), getTextHeightBG());
        oSB.setColor(new Color(TextIcon2.getColor_gradientFull().r, TextIcon2.getColor_gradientFull().g, TextIcon2.getColor_gradientFull().b, 0.9f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - getTextHeightBG() + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
        if (this.getIsHovered() || isActive) {
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 1.0f));
            Renderer.drawBox(oSB, Images.statsRectBGBorder, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
            oSB.setColor(Color.WHITE);
        }
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        Game.ideologiesManager.ideologiesImages.get(this.ideologyID).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.imgWidth / 2 + iTranslateX, this.getPosY() + (this.getHeight() - getTextHeightBG()) / 2 - this.imgHeight / 2 + iTranslateY, this.imgWidth, this.imgHeight);
        Renderer.drawText(oSB, this.fontID, this.getText(), this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() - getTextHeightBG() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColor(isActive));
    }
    
    public static int getTextHeightBG() {
        return CFG.TEXT_HEIGHT + CFG.PADDING * 4;
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
    
    @Override
    protected Color getColor(final boolean isActive) {
        return Colors.getColorTopStats(isActive, this.getIsHovered());
    }
    
    @Override
    public int getCurrent() {
        return this.ideologyID;
    }
}
