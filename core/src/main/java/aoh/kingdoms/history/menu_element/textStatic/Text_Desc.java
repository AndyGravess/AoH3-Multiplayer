// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.GlyphLayout_Game;
import aoh.kingdoms.history.menu_element.MenuElement_Type;
import aoh.kingdoms.history.mainGame.CFG;
import java.util.ArrayList;
import java.util.List;

public class Text_Desc extends Text_Static
{
    public List<String> sLines;
    public int iLineSize;
    
    public Text_Desc(final String sText, final int iPosX, final int iPosY, final int iWidth) {
        this.sLines = new ArrayList<String>();
        this.iLineSize = 0;
        this.init(sText, iPosX, iPosY, iWidth, CFG.FONT_REGULAR_SMALL);
    }
    
    public Text_Desc(final String sText, final int iPosX, final int iPosY, final int iWidth, final int nFontID) {
        this.sLines = new ArrayList<String>();
        this.iLineSize = 0;
        this.init(sText, iPosX, iPosY, iWidth, nFontID);
    }
    
    public void init(final String sText, final int iPosX, final int iPosY, final int iWidth, final int nFontID) {
        this.typeOfElement = MenuElement_Type.TEXT;
        this.fontID = nFontID;
        this.iTextPositionX = 0;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidth(iWidth);
        this.updateTextPosition();
        final String[] words = sText.split(" ");
        int textPosX = 0;
        final int maxW = iWidth - this.getPadding() * 2;
        String currentLine = "";
        for (int i = 0, iSize = words.length; i < iSize; ++i) {
            final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
            glyphLayout.setText(Renderer.fontMain.get(this.fontID), words[i] + " ");
            this.iTextWidth = (int)glyphLayout.width;
            textPosX += this.iTextWidth;
            if (textPosX < maxW) {
                currentLine = currentLine + words[i] + " ";
            }
            else {
                if (currentLine.length() > 0) {
                    this.sLines.add(currentLine);
                }
                currentLine = words[i] + " ";
                textPosX = this.iTextWidth;
            }
        }
        if (currentLine.length() > 0) {
            this.sLines.add(currentLine);
        }
        if (this.sLines.size() > 0 && this.sLines.get(0).length() > 0) {
            final GlyphLayout_Game glyphLayout2 = new GlyphLayout_Game();
            glyphLayout2.setText(Renderer.fontMain.get(this.fontID), this.sLines.get(0));
            this.iTextHeight = (int)glyphLayout2.height;
        }
        else {
            final GlyphLayout_Game glyphLayout2 = new GlyphLayout_Game();
            glyphLayout2.setText(Renderer.fontMain.get(this.fontID), "ABC");
            this.iTextHeight = (int)glyphLayout2.height;
        }
        this.iLineSize = this.sLines.size();
        for (int i = 0; i < this.iLineSize; ++i) {
            final GlyphLayout_Game glyphLayout3 = new GlyphLayout_Game();
            glyphLayout3.setText(Renderer.fontMain.get(this.fontID), this.sLines.get(i));
            if (glyphLayout3.width > this.getWidth()) {
                this.setWidth((int)glyphLayout3.width);
            }
        }
        this.setHeight(this.iTextHeight * this.sLines.size() + (this.sLines.size() - 1) * CFG.PADDING * 2 + this.getPaddingY() * 2);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, getBoxAlpha(this.getClickable(), this.getIsHovered(), isActive)));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 0.8f);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.175f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.3f));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), false, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.45f));
        Renderer.drawBox(oSB, Images.statsRectBGBorder, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        for (int i = 0; i < this.iLineSize; ++i) {
            Renderer.drawTextWithShadow(oSB, this.fontID, this.sLines.get(i), this.getPosX() + this.getPadding() + iTranslateX, this.getPosY() + this.getPaddingY() + (this.iTextHeight + CFG.PADDING * 2) * i + iTranslateY, this.getColor(isActive));
        }
    }
    
    public static final float getBoxAlpha(final boolean clickable, final boolean isHovered, final boolean isActive) {
        return clickable ? (isActive ? 0.85f : (isHovered ? 0.7f : 0.5f)) : 0.2f;
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        if (isActive) {
            return Colors.BUTTON_TEXT_ACTIVE;
        }
        if (this.getIsHovered()) {
            return Colors.BUTTON_TEXT_HOVERED;
        }
        return this.getClickable() ? Colors.BUTTON_TEXT : Colors.BUTTON_TEXT_DISABLED;
    }
    
    public final int getPadding() {
        return CFG.PADDING * 2;
    }
    
    public final int getPaddingY() {
        return CFG.PADDING * 3;
    }
}
