// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.menuElementHover;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.GlyphLayout_Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.mainGame.CFG;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.Color;
import java.util.List;

public class MenuElement_HoverElement_Type_Text_Desc implements MenuElement_HoverElement_Type
{
    public List<String> sLines;
    public int iLineSize;
    public int iTextWidth;
    public int iTextHeight;
    private Color oColor;
    private int fontID;
    
    public MenuElement_HoverElement_Type_Text_Desc(final String sText) {
        this.sLines = new ArrayList<String>();
        this.iLineSize = 0;
        this.iTextWidth = 0;
        this.iTextHeight = 0;
        this.fontID = 0;
        this.init(sText, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT);
    }
    
    public MenuElement_HoverElement_Type_Text_Desc(final String sText, final int fontID) {
        this.sLines = new ArrayList<String>();
        this.iLineSize = 0;
        this.iTextWidth = 0;
        this.iTextHeight = 0;
        this.fontID = 0;
        this.init(sText, fontID, Colors.HOVER_LEFT);
    }
    
    public MenuElement_HoverElement_Type_Text_Desc(final String sText, final Color nColor) {
        this.sLines = new ArrayList<String>();
        this.iLineSize = 0;
        this.iTextWidth = 0;
        this.iTextHeight = 0;
        this.fontID = 0;
        this.init(sText, CFG.FONT_BOLD_SMALL, nColor);
    }
    
    public MenuElement_HoverElement_Type_Text_Desc(final String sText, final int fontID, final Color nColor) {
        this.sLines = new ArrayList<String>();
        this.iLineSize = 0;
        this.iTextWidth = 0;
        this.iTextHeight = 0;
        this.fontID = 0;
        this.init(sText, fontID, nColor);
    }
    
    public final void init(final String sText, final int nFontID, final Color oColor) {
        this.oColor = oColor;
        this.fontID = nFontID;
        final String[] words = sText.split(" ");
        int textPosX = 0;
        final int maxW = (int)(ImageManager.getImage(Images.title1Red).getWidth() * 0.85f);
        String currentLine = "";
        int tTextWidth = 0;
        for (int i = 0, iSize = words.length; i < iSize; ++i) {
            Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), words[i] + " ");
            tTextWidth = (int)Renderer.glyphLayout.width;
            textPosX += tTextWidth;
            if (textPosX < maxW) {
                currentLine = currentLine + words[i] + " ";
                this.iTextWidth = Math.max(this.iTextWidth, Math.min(textPosX, maxW));
            }
            else {
                this.sLines.add(currentLine);
                currentLine = words[i] + " ";
                textPosX = tTextWidth;
            }
        }
        if (currentLine.length() > 0) {
            this.sLines.add(currentLine);
        }
        if (this.sLines.size() > 0 && this.sLines.get(0).length() > 0) {
            Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), this.sLines.get(0));
            this.iTextHeight = (int)Renderer.glyphLayout.height;
        }
        this.iLineSize = this.sLines.size();
        for (int i = 0; i < this.iLineSize; ++i) {
            final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
            glyphLayout.setText(Renderer.fontMain.get(this.fontID), this.sLines.get(i));
            if (glyphLayout.width > this.iTextWidth) {
                this.iTextWidth = (int)glyphLayout.width;
            }
        }
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final float nAlpha, final int iMaxWidth) {
        for (int i = 0; i < this.iLineSize; ++i) {
            Renderer.drawTextWithShadow(oSB, this.fontID, this.sLines.get(i), nPosX, nPosY + CFG.PADDING + CFG.PADDING / 2 + (int)((CFG.TEXT_HEIGHT - CFG.TEXT_HEIGHT * 0.9f) / 2.0f) + (this.iTextHeight + CFG.PADDING * 2) * i, new Color(this.oColor.r, this.oColor.g, this.oColor.b, nAlpha));
        }
    }
    
    @Override
    public int getWidth() {
        return this.iTextWidth;
    }
    
    @Override
    public int getHeight() {
        return CFG.PADDING + CFG.TEXT_HEIGHT_SMALL * this.iLineSize + CFG.PADDING * 2 * (this.iLineSize - 1);
    }
}
