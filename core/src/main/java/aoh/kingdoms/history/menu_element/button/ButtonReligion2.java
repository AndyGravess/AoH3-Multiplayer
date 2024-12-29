// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ReligionTitle;
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

public class ButtonReligion2 extends Button
{
    public int religionID;
    public int imgWidth;
    public int imgHeight;
    
    public ButtonReligion2(final int religionID, final int iPosX, final int iPosY, final int iWidth, final int iHeight) {
        this.religionID = religionID;
        this.init(Game.religionManager.getReligion(religionID).Name, CFG.FONT_REGULAR_SMALL, this.iTextPositionX, iPosX, iPosY, iWidth, iHeight, true, true, false, false);
        if (ImageManager.getImage(Images.population).getHeight() < Game.religionManager.religionImages.get(religionID).getHeight()) {
            final float fScale = ImageManager.getImage(Images.population).getHeight() / (float)Game.religionManager.religionImages.get(religionID).getHeight();
            this.imgWidth = (int)(Game.religionManager.religionImages.get(religionID).getWidth() * fScale);
            this.imgHeight = (int)(Game.religionManager.religionImages.get(religionID).getHeight() * fScale);
        }
        else {
            this.imgWidth = Game.religionManager.religionImages.get(religionID).getWidth();
            this.imgHeight = Game.religionManager.religionImages.get(religionID).getHeight();
        }
    }
    
    public ButtonReligion2(final int religionID, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final boolean x) {
        this.religionID = religionID;
        this.init(Game.religionManager.getReligion(religionID).Name, CFG.FONT_REGULAR_SMALL, this.iTextPositionX, iPosX, iPosY, iWidth, iHeight, true, true, false, false);
        this.imgWidth = Game.religionManager.religionImages.get(religionID).getWidth();
        this.imgHeight = Game.religionManager.religionImages.get(religionID).getHeight();
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(TextIcon2.getColor_gradientXY());
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - getTextHeightBG() + iTranslateY, this.getWidth(), getTextHeightBG());
        oSB.setColor(TextIcon2.getColor_gradientFull());
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
        Game.religionManager.religionImages.get(this.religionID).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.imgWidth / 2 + iTranslateX, this.getPosY() + (this.getHeight() - getTextHeightBG()) / 2 - this.imgHeight / 2 + iTranslateY, this.imgWidth, this.imgHeight);
        Renderer.drawText(oSB, this.fontID, this.getText(), this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() - CFG.TEXT_HEIGHT / 2 - this.getTextHeight() / 2 - CFG.PADDING + iTranslateY, this.getColor(isActive));
    }
    
    public static int getTextHeightBG() {
        return CFG.TEXT_HEIGHT + CFG.PADDING * 2;
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Religion") + ": ", CFG.FONT_BOLD));
        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.religionManager.getReligion(this.religionID).Name, CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_ReligionTitle(this.religionID, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover(nElements);
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        return Colors.getColorButtonHover(isActive, this.getIsHovered());
    }
    
    @Override
    public int getCurrent() {
        return this.religionID;
    }
}
