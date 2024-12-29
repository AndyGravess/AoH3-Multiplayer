// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.GlyphLayout_Game;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.Color;

public class ButtonStatsRectIMG_Bonuses_Right_Color extends ButtonStatsRectIMG_Bonuses_Right
{
    public Color bonusColors;
    
    public ButtonStatsRectIMG_Bonuses_Right_Color(final String sText, final String sText2, final int imageID, final int iPosX, final int iPosY, final int nWidth, final int nHeight, final int maxIconWidth) {
        this.init(sText, CFG.FONT_REGULAR_SMALL, 0, iPosX, iPosY, nWidth, nHeight, true, true, false, false);
        this.sText_Orginal = sText;
        this.fontID2 = CFG.FONT_BOLD_SMALL;
        this.imageID = imageID;
        this.maxIconWidth = maxIconWidth;
        final float iconScale = this.getImageScale(imageID) * 1.2f;
        this.iconWidth = (int)(ImageManager.getImage(imageID).getWidth() * iconScale);
        this.iconHeight = (int)(ImageManager.getImage(imageID).getHeight() * iconScale);
        this.sText2 = sText2;
        final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        glyphLayout.setText(Renderer.fontMain.get(this.fontID2), sText2);
        this.iTextBonusW = (int)glyphLayout.width;
        int tWMax = 0;
        while (this.iTextWidth >= this.getWidth() - (maxIconWidth + CFG.PADDING * 6 + this.iTextBonusW) && this.getText().length() > 5 && ++tWMax < 100) {
            this.setText(this.getText().substring(0, Math.max(1, this.getText().length() - 3)) + ".");
        }
        this.bonusColors = Colors.HOVER_POSITIVE;
    }
    
    public ButtonStatsRectIMG_Bonuses_Right_Color(final String sText, final String sText2, final int imageID, final int iPosX, final int iPosY, final int nWidth, final int nHeight, final int maxIconWidth, final Color nColor) {
        this.init(sText, CFG.FONT_REGULAR_SMALL, 0, iPosX, iPosY, nWidth, nHeight, true, true, false, false);
        this.sText_Orginal = sText;
        this.fontID2 = CFG.FONT_BOLD_SMALL;
        this.imageID = imageID;
        this.maxIconWidth = maxIconWidth;
        final float iconScale = this.getImageScale(imageID) * 1.2f;
        this.iconWidth = (int)(ImageManager.getImage(imageID).getWidth() * iconScale);
        this.iconHeight = (int)(ImageManager.getImage(imageID).getHeight() * iconScale);
        this.sText2 = sText2;
        final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        glyphLayout.setText(Renderer.fontMain.get(this.fontID2), sText2);
        this.iTextBonusW = (int)glyphLayout.width;
        int tWMax = 0;
        while (this.iTextWidth >= this.getWidth() - (maxIconWidth + CFG.PADDING * 6 + this.iTextBonusW) && this.getText().length() > 5 && ++tWMax < 100) {
            this.setText(this.getText().substring(0, Math.max(1, this.getText().length() - 3)) + ".");
        }
        this.bonusColors = nColor;
    }
    
    @Override
    public Color getColorBonus() {
        return this.bonusColors;
    }
}
