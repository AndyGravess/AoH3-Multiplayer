// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu.menuTitle;

import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.GlyphLayout_Game;

public class MenuTitleIMG_Flag_TextRight extends MenuTitleIMG_Flag
{
    public String sRight;
    public int iTextRightWidth;
    
    public MenuTitleIMG_Flag_TextRight(final String sText, final String sText2, final boolean moveable, final boolean resizable, final int imageID, final String sRight) {
        super(sText, sText2, moveable, resizable, imageID);
        this.sRight = sRight;
        final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), sRight);
        this.iTextRightWidth = (int)glyphLayout.width;
    }
    
    @Override
    public void drawText(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final Status titleStatus) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.3f));
        Renderer.drawBox(oSB, Images.statsRectBG, nPosX + nWidth - CFG.PADDING * 3 - Images.boxTitleBORDERWIDTH - this.iTextRightWidth, 1 + nPosY - this.getHeight() + (this.getHeight() / 2 + CFG.PADDING) - CFG.PADDING, this.iTextRightWidth + CFG.PADDING * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 2, 1.0f);
        super.drawText(oSB, nPosX, nPosY, nWidth, titleStatus);
        Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, this.sRight, nPosX + nWidth - CFG.PADDING * 2 - Images.boxTitleBORDERWIDTH - this.iTextRightWidth, 1 + nPosY - this.getHeight() + (this.getHeight() / 2 + CFG.PADDING), MenuTitle.colorHovered);
    }
}
