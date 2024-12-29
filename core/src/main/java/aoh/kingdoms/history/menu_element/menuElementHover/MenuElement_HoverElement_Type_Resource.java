// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.menuElementHover;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.map.ResourcesManager;
import aoh.kingdoms.history.textures.Image;

public class MenuElement_HoverElement_Type_Resource implements MenuElement_HoverElement_Type
{
    private int iResourceID;
    private int offsetLeft;
    private int offsetRight;
    private int iWidth;
    private int iHeight;
    
    public MenuElement_HoverElement_Type_Resource(final int iResourceID, final int offsetLeft, final int offsetRight) {
        this.offsetLeft = 0;
        this.offsetRight = 0;
        this.iWidth = 0;
        this.iHeight = 0;
        this.iResourceID = iResourceID;
        this.offsetLeft = offsetLeft;
        this.offsetRight = offsetRight;
        this.iWidth = (int)(ResourcesManager.resourceImages.get(iResourceID).getWidth() * (CFG.CIV_FLAG_HEIGHT * this.getImageScale()) / ResourcesManager.resourceImages.get(iResourceID).getHeight());
        this.iHeight = (int)(ResourcesManager.resourceImages.get(iResourceID).getHeight() * (CFG.CIV_FLAG_HEIGHT * this.getImageScale()) / ResourcesManager.resourceImages.get(iResourceID).getHeight());
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final float nAlpha, final int iMaxWidth) {
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, nAlpha));
        ResourcesManager.resourceImages.get(this.iResourceID).draw(oSB, nPosX + this.offsetLeft, nPosY + CFG.PADDING + CFG.PADDING / 2 + CFG.TEXT_HEIGHT / 2 - this.iHeight / 2, this.iWidth, this.iHeight);
    }
    
    @Override
    public int getWidth() {
        return this.offsetRight + this.offsetLeft + this.iWidth;
    }
    
    @Override
    public int getHeight() {
        return CFG.TEXT_HEIGHT + CFG.PADDING;
    }
    
    private final float getImageScale() {
        return CFG.TEXT_HEIGHT / (float)CFG.CIV_FLAG_HEIGHT * 1.2f;
    }
}
