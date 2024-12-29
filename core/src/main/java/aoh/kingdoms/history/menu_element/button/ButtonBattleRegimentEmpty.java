// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.menu_element.MenuElement_Type;
import aoh.kingdoms.history.menu_element.MenuElement;

public class ButtonBattleRegimentEmpty extends MenuElement
{
    public int imageID;
    
    public ButtonBattleRegimentEmpty(final int imageID, final int iPosX, final int iPosY) {
        this.typeOfElement = MenuElement_Type.BUTTON;
        this.imageID = imageID;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidth(ImageManager.getImage(imageID).getWidth() + 2);
        this.setHeight(ImageManager.getImage(imageID).getHeight() + 2);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, this.getImageAlpha(isActive)));
        ImageManager.getImage(this.imageID).draw(oSB, this.getPosX() + 1 + iTranslateX, this.getPosY() + 1 + iTranslateY);
        oSB.setColor(Color.WHITE);
        if (this.getIsHovered()) {
            oSB.setColor(Colors.HOVER_GOLD);
            Renderer.drawBox2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        }
        oSB.setColor(Color.WHITE);
    }
    
    public float getImageAlpha(final boolean isActive) {
        return (this.getIsHovered() || isActive) ? 0.35f : 0.15f;
    }
}
