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

public class TextIcon_Vertical extends Button
{
    public int imageID;
    public int iconWidth;
    public int iconHeight;
    
    public TextIcon_Vertical(final String sText, final int imageID, final int nProvinceID, final int iPosX, final int iPosY, final int nWidth, final int nHeight) {
        this.init(sText, CFG.FONT_REGULAR_SMALL, 0, iPosX, iPosY, nWidth, nHeight, true, true, false, false);
        this.imageID = imageID;
        final float iconScale = this.getImageScale(imageID) * 1.2f;
        this.iconWidth = (int)(ImageManager.getImage(imageID).getWidth() * iconScale);
        this.iconHeight = (int)(ImageManager.getImage(imageID).getHeight() * iconScale);
        int tWMax = 0;
        while (this.iTextWidth > this.getHeight() - this.iconHeight - CFG.PADDING * 4 && this.getText().length() > 5 && ++tWMax < 100) {
            this.setText(this.getText().substring(0, Math.max(1, this.getText().length() - 3)) + "..");
        }
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, getBoxAlpha(this.getClickable(), this.getIsHovered(), isActive)));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, getBoxAlpha(this.getClickable(), this.getIsHovered(), isActive) / 2.0f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - this.iconHeight - CFG.PADDING * 2 + iTranslateY, this.getWidth(), this.iconHeight + CFG.PADDING * 2, 1.0f);
        oSB.setColor(Color.WHITE);
    }
    
    public static final float getBoxAlpha(final boolean clickable, final boolean isHovered, final boolean isActive) {
        return clickable ? (isActive ? 0.85f : (isHovered ? 0.7f : 0.5f)) : 0.2f;
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        ImageManager.getImage(this.imageID).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.iconWidth / 2 + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - this.iconHeight + iTranslateY, this.iconWidth, this.iconHeight);
        Renderer.drawTextWithShadowRotated(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.getWidth() / 2 - this.getTextHeight() / 2 + iTranslateX, this.getPosY() + (this.getHeight() - CFG.PADDING * 2 - this.iconHeight) / 2 + this.getTextWidth() / 2 + iTranslateY, this.getColor(isActive), 90.0f);
    }
    
    private final float getImageScale(final int iImageID) {
        return CFG.TEXT_HEIGHT_SMALL / (float)ImageManager.getImage(iImageID).getHeight();
    }
}
