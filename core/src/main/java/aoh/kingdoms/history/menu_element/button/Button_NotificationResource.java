// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.map.ResourcesManager;
import aoh.kingdoms.history.textures.Image;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button_NotificationResource extends Button_Notification
{
    public Button_NotificationResource(final String sText, final String sText2, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final int imageID, final int notificationID, final long lTime) {
        super(sText, sText2, nPosX, nPosY, nWidth, nHeight, imageID, notificationID, lTime);
    }
    
    @Override
    protected void drawIcon(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, this.fAlpha));
        ResourcesManager.resourceImages.get(this.imageID).draw(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iconHeight / 2 + iTranslateY, this.iconWidth, this.iconHeight);
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    public void updateIcon() {
        final float iconScale = this.getImageScale(this.imageID) * 1.2f;
        this.iconWidth = (int)(ResourcesManager.resourceImages.get(this.imageID).getWidth() * iconScale);
        this.iconHeight = (int)(ResourcesManager.resourceImages.get(this.imageID).getHeight() * iconScale);
    }
    
    @Override
    public float getImageScale(final int iImageID) {
        return CFG.TEXT_HEIGHT / (float)ResourcesManager.resourceImages.get(iImageID).getHeight();
    }
}
