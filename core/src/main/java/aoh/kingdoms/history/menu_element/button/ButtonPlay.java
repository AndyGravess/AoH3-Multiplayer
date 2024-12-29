// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;

public class ButtonPlay extends ButtonMain
{
    public boolean flipX;
    
    public ButtonPlay(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final boolean flipX) {
        super(sText, fontID, iTextPositionX, iPosX, iPosY, ImageManager.getImage(Images.buttonPlay).getWidth(), true);
        this.flipX = flipX;
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (this.getIsHovered() || isActive) {
            ImageManager.getImage(Images.buttonPlay2).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), this.flipX, false);
        }
        else {
            ImageManager.getImage(Images.buttonPlay).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), this.flipX, false);
        }
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (isActive) {
            Renderer.drawText(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + (this.flipX ? 0 : Images.buttonPlayPADDING) + (this.getWidth() - Images.buttonPlayPADDING) / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY, this.getColor(isActive));
        }
        else {
            Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + (this.flipX ? 0 : Images.buttonPlayPADDING) + (this.getWidth() - Images.buttonPlayPADDING) / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY, this.getColor(isActive));
        }
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        return Colors.getColorTopStats(isActive, this.getIsHovered());
    }
    
    @Override
    public int getHeight() {
        return ImageManager.getImage(Images.buttonPlay).getHeight();
    }
}
