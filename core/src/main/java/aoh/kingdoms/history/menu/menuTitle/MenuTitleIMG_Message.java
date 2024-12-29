// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu.menuTitle;

import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuTitleIMG_Message extends MenuTitleIMG
{
    public MenuTitleIMG_Message(final String sText, final boolean moveable, final boolean resizable, final int imageID) {
        super(sText, moveable, resizable, imageID);
    }
    
    public MenuTitleIMG_Message(final String sText, final int nHeight, final boolean moveable, final boolean resizable, final int imageID) {
        super(sText, nHeight, moveable, resizable, imageID);
    }
    
    public MenuTitleIMG_Message(final String sText, final int nHeight, final int fontID, final boolean moveable, final boolean resizable, final int imageID) {
        super(sText, nHeight, fontID, moveable, resizable, imageID);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final Status titleStatus) {
        super.draw(oSB, nPosX, nPosY, nWidth, titleStatus);
        ImageManager.getImage(Images.message).draw(oSB, nPosX + (this.getHeight() - ImageManager.getImage(Images.message).getHeight()) / 2, nPosY - this.getHeight() / 2 - ImageManager.getImage(Images.message).getHeight() / 2);
    }
}
