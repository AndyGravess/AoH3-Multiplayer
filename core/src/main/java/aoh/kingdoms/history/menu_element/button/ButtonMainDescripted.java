// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ButtonMainDescripted extends ButtonMain
{
    private String sDesc;
    
    public ButtonMainDescripted(final String sText, final String sDesc, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int nWidth, final boolean isClickable) {
        super(sText, fontID, iTextPositionX, iPosX, iPosY, nWidth, isClickable);
        this.sDesc = sDesc;
    }
    
    public ButtonMainDescripted(final String sText, final String sDesc, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int nWidth, final boolean isClickable, final boolean checkBox) {
        super(sText, fontID, iTextPositionX, iPosX, iPosY, nWidth, isClickable, checkBox);
        this.sDesc = sDesc;
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        Renderer.drawText(oSB, CFG.FONT_REGULAR, this.getText(), this.getPosX() + this.getTextPos() + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.TEXT_HEIGHT + iTranslateY, this.getColor(isActive));
        Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, this.sDesc, this.getPosX() + this.getTextPos() + iTranslateX, this.getPosY() + this.getHeight() / 2 + CFG.PADDING + iTranslateY, Colors.HOVER_RIGHT);
    }
}
