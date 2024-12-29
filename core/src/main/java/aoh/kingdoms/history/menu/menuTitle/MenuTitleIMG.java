// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu.menuTitle;

import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;

public class MenuTitleIMG extends MenuTitle
{
    public int imageID;
    
    public MenuTitleIMG(final String sText, final boolean moveable, final boolean resizable, final int imageID) {
        super(sText, ImageManager.getImage(imageID).getHeight(), moveable, resizable);
        this.imageID = imageID;
    }
    
    public MenuTitleIMG(final String sText, final int nHeight, final boolean moveable, final boolean resizable, final int imageID) {
        super(sText, nHeight, moveable, resizable);
        this.imageID = imageID;
    }
    
    public MenuTitleIMG(final String sText, final int nHeight, final int fontID, final boolean moveable, final boolean resizable, final int imageID) {
        super(sText, nHeight, fontID, moveable, resizable);
        this.imageID = imageID;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final Status titleStatus) {
        ImageManager.getImage(this.imageID).draw(oSB, nPosX, nPosY - this.getHeight(), nWidth, this.getHeight());
        this.drawGradient(oSB, nPosX, nPosY, nWidth, titleStatus);
        this.drawText(oSB, nPosX, nPosY, nWidth, titleStatus);
    }
}
