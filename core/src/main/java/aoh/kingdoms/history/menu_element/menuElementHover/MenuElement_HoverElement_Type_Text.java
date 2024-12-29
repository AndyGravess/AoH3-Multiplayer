// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.menuElementHover;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.Color;

public class MenuElement_HoverElement_Type_Text implements MenuElement_HoverElement_Type
{
    private String sText;
    private int iTextWidth;
    private Color oColor;
    private int fontID;
    
    public MenuElement_HoverElement_Type_Text(final String sText) {
        this.fontID = 0;
        this.init(sText, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT);
    }
    
    public MenuElement_HoverElement_Type_Text(final String sText, final int fontID) {
        this.fontID = 0;
        this.init(sText, fontID, Colors.HOVER_LEFT);
    }
    
    public MenuElement_HoverElement_Type_Text(final String sText, final Color nColor) {
        this.fontID = 0;
        this.init(sText, CFG.FONT_BOLD_SMALL, nColor);
    }
    
    public MenuElement_HoverElement_Type_Text(final String sText, final int fontID, final Color nColor) {
        this.fontID = 0;
        this.init(sText, fontID, nColor);
    }
    
    public final void init(final String sText, final int nFontID, final Color oColor) {
        this.sText = sText;
        this.oColor = oColor;
        this.fontID = nFontID;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), sText);
        this.iTextWidth = (int)Renderer.glyphLayout.width;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final float nAlpha, final int iMaxWidth) {
        Renderer.drawText(oSB, this.fontID, this.sText, nPosX, nPosY + CFG.PADDING + CFG.PADDING / 2 + (int)((CFG.TEXT_HEIGHT - CFG.TEXT_HEIGHT * 0.9f) / 2.0f), new Color(this.oColor.r, this.oColor.g, this.oColor.b, nAlpha));
    }
    
    @Override
    public int getWidth() {
        return this.iTextWidth;
    }
    
    @Override
    public int getHeight() {
        return CFG.TEXT_HEIGHT + CFG.PADDING;
    }
}
