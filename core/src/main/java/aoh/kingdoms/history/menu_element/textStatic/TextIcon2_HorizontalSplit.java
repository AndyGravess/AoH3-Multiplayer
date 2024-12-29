// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import java.util.ArrayList;
import java.util.List;
import aoh.kingdoms.history.menu_element.button.Button;

public class TextIcon2_HorizontalSplit extends Button
{
    public List<String> sLines;
    public int iLineSize;
    public int imageID;
    public int iconWidth;
    public int iconHeight;
    public int maxIconWidth;
    public int iY;
    public int maxWidth;
    
    public TextIcon2_HorizontalSplit(final String sText, final int imageID, final int iPosX, final int iPosY, final int nWidth, final int nHeight, final int maxIconWidth, final int maxWidth) {
        this.sLines = new ArrayList<String>();
        this.iLineSize = 0;
        this.iY = 0;
        this.maxWidth = 0;
        this.init(sText, CFG.FONT_REGULAR_SMALL, 0, iPosX, iPosY, nWidth, nHeight, true, true, false, false);
        this.imageID = imageID;
        this.maxIconWidth = maxIconWidth;
        this.maxWidth = maxWidth;
        this.iconWidth = ImageManager.getImage(imageID).getWidth();
        this.iconHeight = ImageManager.getImage(imageID).getHeight();
        final String[] words = sText.split(" ");
        int textPosX = 0;
        final int maxW = nWidth - this.getIconBGWidth() - CFG.PADDING * 2;
        String currentLine = "";
        for (int i = 0, iSize = words.length; i < iSize; ++i) {
            Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), words[i] + " ");
            this.iTextWidth = (int)Renderer.glyphLayout.width;
            textPosX += this.iTextWidth;
            if (textPosX < maxW) {
                currentLine = currentLine + words[i] + " ";
            }
            else {
                this.sLines.add(currentLine);
                currentLine = words[i] + " ";
                textPosX = this.iTextWidth;
            }
        }
        if (currentLine.length() > 0) {
            this.sLines.add(currentLine);
        }
        if (this.sLines.size() > 0 && this.sLines.get(0).length() > 0) {
            Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), this.sLines.get(0));
            this.iTextHeight = (int)Renderer.glyphLayout.height;
        }
        this.iLineSize = Math.min(3, this.sLines.size());
        this.setHeight(Math.max(this.getHeight(), CFG.PADDING * 3 + (this.iTextHeight * this.iLineSize + CFG.PADDING * 2 * (this.iLineSize - 1))));
        this.iY = this.getHeight() / 2 - (this.iTextHeight * this.iLineSize + CFG.PADDING * 2 * (this.iLineSize - 1)) / 2;
    }
    
    public int getIconBGWidth() {
        return this.maxIconWidth + CFG.PADDING * 2;
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, (this.getIsHovered() || isActive) ? 0.6f : 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.maxWidth, this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.3f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getIconBGWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(getColor_gradientXY());
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.maxWidth, 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.maxWidth, 1);
        oSB.setColor(Color.WHITE);
    }
    
    public static Color getColor_gradientXY() {
        return new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.85f);
    }
    
    public static final float getBoxAlpha(final boolean clickable, final boolean isHovered, final boolean isActive) {
        return clickable ? (isActive ? 0.85f : (isHovered ? 0.7f : 0.5f)) : 0.2f;
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        ImageManager.getImage(this.imageID).draw(oSB, this.getPosX() + CFG.PADDING + this.maxIconWidth / 2 - this.iconWidth / 2 + iTranslateX, this.getPosY() + (this.getHeight() - this.iconHeight) / 2 + iTranslateY, this.iconWidth, this.iconHeight);
        for (int i = 0; i < this.iLineSize; ++i) {
            Renderer.drawTextWithShadow(oSB, this.fontID, this.sLines.get(i), this.getPosX() + this.getIconBGWidth() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.iY + (this.iTextHeight + CFG.PADDING * 2) * i + iTranslateY, this.getColor(isActive));
        }
    }
    
    private final float getImageScale(final int iImageID) {
        return CFG.TEXT_HEIGHT / (float)ImageManager.getImage(iImageID).getHeight();
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        return Colors.getColorButtonHover(isActive, this.getIsHovered());
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        String tText = "";
        for (int i = 0; i < this.sLines.size(); ++i) {
            tText += this.sLines.get(i);
        }
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(tText, "", this.imageID, CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover(nElements);
    }
}
