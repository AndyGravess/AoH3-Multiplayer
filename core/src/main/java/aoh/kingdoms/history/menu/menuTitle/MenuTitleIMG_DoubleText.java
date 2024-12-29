// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu.menuTitle;

import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.GlyphLayout_Game;
import aoh.kingdoms.history.mainGame.CFG;

public class MenuTitleIMG_DoubleText extends MenuTitleIMG
{
    public String sText2;
    public int iText2Width;
    
    public MenuTitleIMG_DoubleText(final String sText, final String sText2, final boolean moveable, final boolean resizable, final int imageID) {
        super(sText, moveable, resizable, imageID);
        this.iText2Width = 0;
        this.fontID = CFG.FONT_BOLD;
        this.sText2 = sText2;
        final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), sText2);
        this.iText2Width = (int)glyphLayout.width;
    }
    
    @Override
    public void drawText(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final Status titleStatus) {
        Renderer.drawText(oSB, this.fontID, this.getText(), nPosX + nWidth / 2 - this.getTextWidth() / 2, 1 + nPosY - this.getHeight() + (this.getHeight() / 2 - this.getTextHeight()), this.getColorText(titleStatus));
        Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, this.sText2, nPosX + nWidth / 2 - this.iText2Width / 2, 1 + nPosY - this.getHeight() + (this.getHeight() / 2 + CFG.PADDING), MenuTitle.colorHovered);
    }
}
