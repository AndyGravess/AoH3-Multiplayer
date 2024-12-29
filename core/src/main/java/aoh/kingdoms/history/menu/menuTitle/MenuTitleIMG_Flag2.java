// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu.menuTitle;

import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.GlyphLayout_Game;

public class MenuTitleIMG_Flag2 extends MenuTitleIMG_Flag
{
    public String sText3;
    public int iTextWidth3;
    
    public MenuTitleIMG_Flag2(final String sText, final String sText2, final String sText3, final boolean moveable, final boolean resizable, final int imageID) {
        super(sText, sText2, moveable, resizable, imageID);
        this.sText3 = sText3;
        final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), sText3);
        this.iTextWidth3 = (int)glyphLayout.width;
    }
    
    @Override
    public void drawText(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final Status titleStatus) {
        Renderer.drawText(oSB, this.fontID, this.getText(), nPosX + this.flagWidth + CFG.PADDING, 1 + nPosY - this.getHeight() + (this.getHeight() / 2 - this.getTextHeight()), this.getColorText(titleStatus));
        Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, this.sText2, nPosX + this.flagWidth + CFG.PADDING, 1 + nPosY - this.getHeight() + (this.getHeight() / 2 + CFG.PADDING), MenuTitle.colorHovered);
        Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, this.sText3, nPosX + nWidth - this.iTextWidth3 - CFG.PADDING, 1 + nPosY - this.getHeight() + (this.getHeight() / 2 + CFG.PADDING), MenuTitle.colorHovered);
    }
}
