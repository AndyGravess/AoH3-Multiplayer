// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.Color;

public class Text_StaticBG_ID_SpecialColor extends Text_StaticBG_ID_Special
{
    public Color textColor;
    public String sText2;
    public int iTextWidth2;
    
    public Text_StaticBG_ID_SpecialColor(final String sText, final String sText2, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final int id, final Color nColor) {
        super(sText, fontID, iTextPositionX, iPosX, iPosY, iWidth, iHeight, id);
        this.textColor = nColor;
        this.id = id;
        this.sText2 = sText2;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(fontID), sText2);
        this.iTextWidth2 = (int)Renderer.glyphLayout.width;
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        return this.textColor;
    }
    
    @Override
    public void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sText, this.getPosX() + this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + (this.getHeight() - CFG.TEXT_HEIGHT) / 2 + iTranslateY, this.getColor(isActive));
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sText2, this.getPosX() + this.getWidth() - this.iTextWidth2 - this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + (this.getHeight() - CFG.TEXT_HEIGHT) / 2 + iTranslateY, this.getColor(isActive));
    }
}
