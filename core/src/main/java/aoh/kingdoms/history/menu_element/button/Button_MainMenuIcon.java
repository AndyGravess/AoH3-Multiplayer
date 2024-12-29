// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_MainMenuIcon extends Button
{
    public int imgID;
    
    public Button_MainMenuIcon(final int imgID, final int iPosX, final int iPosY, final int nWidth, final int nHeight) {
        this.imgID = imgID;
        this.init("", this.fontID, this.iTextPositionX, iPosX, iPosY, nWidth, nHeight, true, true, false, false);
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (isActive) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.2f));
        }
        else if (this.getIsHovered()) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.1f));
        }
        else {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.035f));
        }
        ImageManager.getImage(Images.gradientXYVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), true, false);
        if (isActive) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.75f));
        }
        else if (this.getIsHovered()) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.5f));
        }
        else {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.3f));
        }
        ImageManager.getImage(this.imgID).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(this.imgID).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(this.imgID).getHeight() / 2 + iTranslateY);
        oSB.setColor(Color.WHITE);
    }
}
