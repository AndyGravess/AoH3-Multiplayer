// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;

public class Text_Title_v2_TextLR extends Text_Title_v2
{
    public String sTextRight;
    public int iTextRightWidth;
    
    public Text_Title_v2_TextLR(final String sText, final int iTextPositionX, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final String sTextRight) {
        super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight);
        this.sTextRight = sTextRight;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), sTextRight);
        this.iTextRightWidth = (int)Renderer.glyphLayout.width;
    }
    
    public Text_Title_v2_TextLR(final String sText, final String sTextRight, final int iTextPositionX, final int iPosX, final int iPosY, final int iWidth, final int iHeight) {
        super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight);
        this.sTextRight = sTextRight;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), sTextRight);
        this.iTextRightWidth = (int)Renderer.glyphLayout.width;
    }
    
    public Text_Title_v2_TextLR(final String sText, final int nFontID, final int iTextPositionX, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final String sTextRight) {
        super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight);
        this.fontID = nFontID;
        this.sTextRight = sTextRight;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), sTextRight);
        this.iTextRightWidth = (int)Renderer.glyphLayout.width;
    }
    
    @Override
    public void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sTextRight, this.getPosX() + this.getWidth() - this.textPosition.getTextPosition() - this.iTextRightWidth + iTranslateX, this.getPosY() + (this.getHeight() - this.getTextHeight()) / 2 + iTranslateY, this.getColor2(isActive));
        super.drawText(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
    }
    
    protected Color getColor2(final boolean isActive) {
        return Colors.getColorButtonHover(isActive, this.getIsHovered());
    }
}
