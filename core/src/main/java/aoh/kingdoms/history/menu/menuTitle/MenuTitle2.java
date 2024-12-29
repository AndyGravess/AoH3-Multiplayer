// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu.menuTitle;

import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuTitle2 extends MenuTitle
{
    public MenuTitle2(final String sText, final int iHeight, final boolean moveable, final boolean resizable) {
        super(sText, iHeight, moveable, resizable);
    }
    
    public MenuTitle2(final String sText, final float fontScale, final int iHeight, final boolean moveable, final boolean resizable) {
        super(sText, fontScale, iHeight, moveable, resizable);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final Status titleStatus) {
        ImageManager.getImage(Images.title2).draw(oSB, nPosX, nPosY - this.getHeight(), nWidth, this.getHeight());
        this.drawText(oSB, nPosX, nPosY, nWidth, titleStatus);
    }
}
