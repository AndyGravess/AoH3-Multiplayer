// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement_Type;

public class Text_StaticBG_Ruler extends Text_Static
{
    public String sText2;
    public int iText2Width;
    
    public Text_StaticBG_Ruler(final String sText, final String sText2, final int iPosX, final int iPosY, final int iWidth, final int iHeight) {
        this.typeOfElement = MenuElement_Type.TEXT;
        this.fontID = CFG.FONT_REGULAR_SMALL;
        this.iTextPositionX = CFG.PADDING * 2;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidth(iWidth);
        this.setHeight(iHeight);
        this.sText2 = sText2;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), sText2);
        this.iText2Width = (int)Renderer.glyphLayout.width;
        this.setText(sText);
        this.updateTextPosition();
        int tWMax = 0;
        while (this.iTextWidth > this.getWidth() - this.iText2Width - CFG.PADDING && this.getText().length() > 5 && ++tWMax < 100) {
            this.setText(this.getText().substring(0, Math.max(1, this.getText().length() - 3)) + "..");
        }
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + this.getWidth() - CFG.PADDING * 4 - this.iText2Width + iTranslateX, this.getPosY() + iTranslateY, CFG.PADDING * 4 + this.iText2Width, this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.4f));
        Images.gradientXY.draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 4 - this.iText2Width + iTranslateX, this.getPosY() + iTranslateY, CFG.PADDING * 4 + this.iText2Width, this.getHeight() / 2, false, true);
        Images.gradientXY.draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 4 - this.iText2Width + iTranslateX, this.getPosY() + this.getHeight() / 2 + iTranslateY, CFG.PADDING * 4 + this.iText2Width, this.getHeight() / 2, false, false);
        Images.gradientFull.draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 4 - this.iText2Width + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, CFG.PADDING * 4 + this.iText2Width, 1);
        Images.gradientFull.draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 4 - this.iText2Width + iTranslateX, this.getPosY() + 1 + iTranslateY, CFG.PADDING * 4 + this.iText2Width, 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.2f));
        Images.gradientFull.draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 4 - this.iText2Width + iTranslateX, this.getPosY() + iTranslateY, CFG.PADDING * 4 + this.iText2Width, 1);
        Images.gradientFull.draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 4 - this.iText2Width + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, CFG.PADDING * 4 + this.iText2Width, 1);
        oSB.setColor(Color.WHITE);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sText, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColor(isActive));
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sText2, this.getPosX() + this.getWidth() - CFG.PADDING * 2 - this.iText2Width + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, this.getColor2(isActive));
    }
    
    @Override
    public void setText(final String sText) {
        this.sText = sText;
        try {
            Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), sText);
            this.iTextWidth = (int)Renderer.glyphLayout.width;
            this.iTextHeight = (int)Renderer.glyphLayout.height;
            if (this.getHeight() < this.iTextHeight) {
                this.setHeight(this.iTextHeight);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(this.sText2, CFG.FONT_REGULAR_SMALL));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover(nElements);
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        return Colors.getColorButtonHover(isActive, this.getIsHovered());
    }
    
    protected Color getColor2(final boolean isActive) {
        return Colors.getColorButtonHover(isActive, this.getIsHovered());
    }
}
