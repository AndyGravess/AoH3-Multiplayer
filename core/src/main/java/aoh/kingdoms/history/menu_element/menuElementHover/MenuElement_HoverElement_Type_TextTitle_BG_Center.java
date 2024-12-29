// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.menuElementHover;

import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;

public class MenuElement_HoverElement_Type_TextTitle_BG_Center implements MenuElement_HoverElement_Type
{
    private String sText;
    private int iTextWidth;
    private Color oColor;
    private int fontID;
    
    public MenuElement_HoverElement_Type_TextTitle_BG_Center(final String sText) {
        this.init(sText, this.fontID = 0, Colors.HOVER_LEFT);
    }
    
    public MenuElement_HoverElement_Type_TextTitle_BG_Center(final String sText, final int fontID) {
        this.fontID = 0;
        this.init(sText, fontID, Colors.HOVER_LEFT);
    }
    
    public MenuElement_HoverElement_Type_TextTitle_BG_Center(final String sText, final Color nColor) {
        this.init(sText, this.fontID = 0, nColor);
    }
    
    public MenuElement_HoverElement_Type_TextTitle_BG_Center(final String sText, final int fontID, final Color nColor) {
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
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.9f * nAlpha));
        Images.pix.draw(oSB, nPosX - MenuElement_Hover.getDrawExtraXPos(), nPosY, iMaxWidth + MenuElement_Hover.getDrawExtraXPos() * 2, this.getHeight2() + CFG.PADDING * 2);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.75f * nAlpha));
        Images.gradientXY.draw(oSB, nPosX - MenuElement_Hover.getDrawExtraXPos(), nPosY, iMaxWidth + MenuElement_Hover.getDrawExtraXPos() * 2, this.getHeight2() + CFG.PADDING * 2);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 1.0f * nAlpha));
        Images.gradientXY.draw(oSB, nPosX - MenuElement_Hover.getDrawExtraXPos(), nPosY, iMaxWidth + MenuElement_Hover.getDrawExtraXPos() * 2, this.getHeight2() + CFG.PADDING * 2);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.2f * nAlpha));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX - MenuElement_Hover.getDrawExtraXPos(), nPosY, iMaxWidth + MenuElement_Hover.getDrawExtraXPos() * 2, CFG.PADDING * 2);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 1.0f * nAlpha));
        Images.gradientFull.draw(oSB, nPosX - MenuElement_Hover.getDrawExtraXPos(), nPosY + 1, iMaxWidth + MenuElement_Hover.getDrawExtraXPos() * 2, 1);
        Images.gradientFull.draw(oSB, nPosX - MenuElement_Hover.getDrawExtraXPos(), nPosY + this.getHeight2() + CFG.PADDING * 2 - 2, iMaxWidth + MenuElement_Hover.getDrawExtraXPos() * 2, 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 1.0f * nAlpha));
        Images.gradientFull.draw(oSB, nPosX - MenuElement_Hover.getDrawExtraXPos(), nPosY, iMaxWidth + MenuElement_Hover.getDrawExtraXPos() * 2, 1);
        Images.gradientFull.draw(oSB, nPosX - MenuElement_Hover.getDrawExtraXPos(), nPosY + this.getHeight2() + CFG.PADDING * 2 - 1, iMaxWidth + MenuElement_Hover.getDrawExtraXPos() * 2, 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.15f * nAlpha));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX - MenuElement_Hover.getDrawExtraXPos(), nPosY + this.getHeight2() + CFG.PADDING * 2, iMaxWidth + MenuElement_Hover.getDrawExtraXPos() * 2, CFG.PADDING);
        Images.gradientXY.draw(oSB, nPosX - MenuElement_Hover.getDrawExtraXPos(), nPosY + this.getHeight2() + CFG.PADDING * 2, iMaxWidth + MenuElement_Hover.getDrawExtraXPos() * 2, CFG.PADDING, false, true);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX - MenuElement_Hover.getDrawExtraXPos(), nPosY - CFG.PADDING, iMaxWidth + MenuElement_Hover.getDrawExtraXPos() * 2, CFG.PADDING, false, true);
        Images.gradientXY.draw(oSB, nPosX - MenuElement_Hover.getDrawExtraXPos(), nPosY - CFG.PADDING, iMaxWidth + MenuElement_Hover.getDrawExtraXPos() * 2, CFG.PADDING, false, true);
        oSB.setColor(Color.WHITE);
        Renderer.drawText(oSB, this.fontID, this.sText, nPosX + iMaxWidth / 2 - this.iTextWidth / 2, nPosY + CFG.PADDING * 2 + CFG.PADDING / 2, new Color(this.oColor.r, this.oColor.g, this.oColor.b, nAlpha));
    }
    
    @Override
    public int getWidth() {
        return this.iTextWidth;
    }
    
    @Override
    public int getHeight() {
        return CFG.TEXT_HEIGHT + CFG.PADDING * 5;
    }
    
    public int getHeight2() {
        return CFG.TEXT_HEIGHT + CFG.PADDING * 3;
    }
}
