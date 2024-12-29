// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.menuElementHover;

import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;

public class MenuElement_HoverElement_Type_TextTitleCenter implements MenuElement_HoverElement_Type
{
    private String sText;
    private int iTextWidth;
    private Color oColor;
    private int fontID;
    
    public MenuElement_HoverElement_Type_TextTitleCenter(final String sText) {
        this.init(sText, this.fontID = 0, Colors.HOVER_LEFT);
    }
    
    public MenuElement_HoverElement_Type_TextTitleCenter(final String sText, final int fontID) {
        this.fontID = 0;
        this.init(sText, fontID, Colors.HOVER_LEFT);
    }
    
    public MenuElement_HoverElement_Type_TextTitleCenter(final String sText, final Color nColor) {
        this.init(sText, this.fontID = 0, nColor);
    }
    
    public MenuElement_HoverElement_Type_TextTitleCenter(final String sText, final int fontID, final Color nColor) {
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
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.275f * nAlpha));
        Images.statsBG.draw2(oSB, nPosX - CFG.PADDING, nPosY, iMaxWidth + CFG.PADDING * 2 - Images.statsBG2.getWidth(), this.getHeight() + CFG.PADDING * 2);
        Images.statsBG2.draw2(oSB, nPosX - CFG.PADDING + iMaxWidth - Images.statsBG2.getWidth() + CFG.PADDING * 2, nPosY, Images.statsBG2.getWidth(), this.getHeight() + CFG.PADDING * 2, true, false);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.5f * nAlpha));
        Images.gradientXY.draw(oSB, nPosX, nPosY, iMaxWidth, this.getHeight() + CFG.PADDING * 2);
        oSB.setColor(Color.WHITE);
        Renderer.drawText(oSB, this.fontID, this.sText, nPosX + iMaxWidth / 2 - this.iTextWidth / 2, nPosY + CFG.PADDING * 2, new Color(this.oColor.r, this.oColor.g, this.oColor.b, nAlpha));
    }
    
    @Override
    public int getWidth() {
        return this.iTextWidth;
    }
    
    @Override
    public int getHeight() {
        return CFG.TEXT_HEIGHT + CFG.PADDING * 2;
    }
}
