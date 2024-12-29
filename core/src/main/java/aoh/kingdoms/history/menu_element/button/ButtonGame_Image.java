// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;

public class ButtonGame_Image extends ButtonGame
{
    public int imageID;
    public int iconWidth;
    public int iconHeight;
    
    public ButtonGame_Image(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int nWidth, final boolean isClickable, final int imageID) {
        super(sText, fontID, iTextPositionX, iPosX, iPosY, nWidth, isClickable);
        this.imageID = 0;
        this.imageID = imageID;
        final float iconScale = this.getImageScale(imageID) * 1.25f;
        this.iconWidth = (int)(ImageManager.getImage(imageID).getWidth() * iconScale);
        this.iconHeight = (int)(ImageManager.getImage(imageID).getHeight() * iconScale);
    }
    
    public ButtonGame_Image(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int nWidth, final int nHeight, final boolean isClickable, final int imageID) {
        super(sText, fontID, iTextPositionX, iPosX, iPosY, nWidth, nHeight, isClickable);
        this.imageID = 0;
        this.imageID = imageID;
        final float iconScale = this.getImageScale(imageID) * 1.25f;
        this.iconWidth = (int)(ImageManager.getImage(imageID).getWidth() * iconScale);
        this.iconHeight = (int)(ImageManager.getImage(imageID).getHeight() * iconScale);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        ImageManager.getImage(this.imageID).draw(oSB, this.getPosX() + this.getWidth() / 2 - (this.iTextWidth + CFG.PADDING * 2 + this.iconWidth) / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iconHeight / 2 + iTranslateY, this.iconWidth, this.iconHeight);
        Renderer.drawText(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.getWidth() / 2 - (this.iTextWidth + CFG.PADDING * 2 + this.iconWidth) / 2 + CFG.PADDING * 2 + this.iconWidth + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY, this.getColor(isActive));
    }
    
    private final float getImageScale(final int iImageID) {
        return CFG.TEXT_HEIGHT / (float)ImageManager.getImage(iImageID).getHeight();
    }
}
