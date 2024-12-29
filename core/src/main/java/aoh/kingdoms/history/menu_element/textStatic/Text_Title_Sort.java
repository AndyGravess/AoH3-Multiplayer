// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text_Title_Sort extends Text_Title
{
    public boolean active;
    public boolean flipY;
    
    public Text_Title_Sort(final boolean active, final boolean flipY, final String sText, final int iTextPositionX, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final int fontID) {
        super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, fontID);
        this.active = active;
        this.flipY = flipY;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
        if (this.active) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.4f));
            ImageManager.getImage(Images.activeSort).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.activeSort).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.activeSort).getHeight() / 2 + iTranslateY, false, this.flipY);
            oSB.setColor(Color.WHITE);
        }
    }
}
