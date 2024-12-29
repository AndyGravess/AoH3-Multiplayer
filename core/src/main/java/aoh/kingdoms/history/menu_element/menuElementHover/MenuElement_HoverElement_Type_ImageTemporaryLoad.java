// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.menuElementHover;

import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.textures.Image;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.CFG;

public class MenuElement_HoverElement_Type_ImageTemporaryLoad implements MenuElement_HoverElement_Type
{
    private int iHoverLoadedTemporaryImagesID;
    private int offsetLeft;
    private int offsetRight;
    private int iWidth;
    private int iHeight;
    
    public MenuElement_HoverElement_Type_ImageTemporaryLoad(final String sFile) {
        this.init(sFile, 0, CFG.PADDING);
    }
    
    public MenuElement_HoverElement_Type_ImageTemporaryLoad(final String sFile, final int offsetLeft) {
        this.init(sFile, offsetLeft, CFG.PADDING);
    }
    
    public MenuElement_HoverElement_Type_ImageTemporaryLoad(final String sFile, final int offsetLeft, final int offsetRight) {
        this.init(sFile, offsetLeft, offsetRight);
    }
    
    private final void init(final String sFile, final int offsetLeft, final int offsetRight) {
        this.iHoverLoadedTemporaryImagesID = Game.hoverManager.loadHoverLoadedTemporaryImage(sFile);
        this.offsetLeft = offsetLeft;
        this.offsetRight = offsetRight;
        this.iWidth = (int)(this.getImage().getWidth() * this.getImageScale());
        this.iHeight = (int)(this.getImage().getHeight() * this.getImageScale());
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final float nAlpha, final int iMaxWidth) {
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, nAlpha));
        this.getImage().draw(oSB, nPosX + this.offsetLeft, nPosY + CFG.PADDING, this.iWidth, this.iHeight);
    }
    
    private final Image getImage() {
        try {
            return Game.hoverManager.lHoverLoadedTemporaryImages.get(this.iHoverLoadedTemporaryImagesID);
        }
        catch (final Exception ex) {
            return ImageManager.getImage(Images.imageNotFound);
        }
    }
    
    @Override
    public int getWidth() {
        return this.offsetLeft + this.offsetRight + this.iWidth;
    }
    
    private final float getImageScale() {
        return 1.0f;
    }
    
    @Override
    public int getHeight() {
        return this.getImage().getHeight();
    }
}
