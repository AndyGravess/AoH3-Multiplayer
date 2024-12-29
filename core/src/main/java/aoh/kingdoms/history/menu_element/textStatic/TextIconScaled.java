// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.button.Button;

public class TextIconScaled extends Button
{
    public int imageID;
    public int imgWidth;
    public int imgHeight;
    
    public TextIconScaled(final String sText, final int imageID, final int nPosX, final int nPosY, final int nWidth, final int nHeight) {
        this.init(sText, CFG.FONT_REGULAR_SMALL, 0, nPosX, nPosY, nWidth, nHeight, true, true, false, false);
        this.imageID = imageID;
        if (ImageManager.getImage(imageID).getHeight() > this.getHeight() - this.getTextH() - CFG.PADDING * 2) {
            this.imgHeight = this.getHeight() - this.getTextH() - CFG.PADDING * 2;
            this.imgWidth = (int)(ImageManager.getImage(imageID).getWidth() * ((this.getHeight() - this.getTextH() - CFG.PADDING * 2) / (float)ImageManager.getImage(imageID).getHeight()));
        }
        else {
            this.imgWidth = ImageManager.getImage(imageID).getWidth();
            this.imgHeight = ImageManager.getImage(imageID).getHeight();
        }
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.3f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.getTextH() + iTranslateY, this.getWidth(), this.getTextH(), 1.0f);
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(this.imageID).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.imgWidth / 2 + iTranslateX, this.getPosY() + (this.getHeight() - this.getTextH()) / 2 - this.imgHeight / 2 + iTranslateY, this.imgWidth, this.imgHeight);
        Renderer.drawText(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - this.getTextHeight() + iTranslateY, this.getColor(isActive));
    }
    
    public int getTextH() {
        return CFG.TEXT_HEIGHT + CFG.PADDING * 2;
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        return Colors.getColorTopStatsHover(isActive, this.getIsHovered());
    }
}
