// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.menuElementHover;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.textures.Image;

public class MenuElement_HoverElement_Type_Religion implements MenuElement_HoverElement_Type
{
    private int iReligionID;
    private int offsetLeft;
    private int offsetRight;
    private int iWidth;
    private int iHeight;
    
    public MenuElement_HoverElement_Type_Religion(final int iReligionID, final int offsetLeft, final int offsetRight) {
        this.offsetLeft = 0;
        this.offsetRight = 0;
        this.iWidth = 0;
        this.iHeight = 0;
        this.iReligionID = iReligionID;
        this.offsetLeft = offsetLeft;
        this.offsetRight = offsetRight;
        this.iWidth = (int)(Game.religionManager.religionImages.get(iReligionID).getWidth() * (CFG.CIV_FLAG_HEIGHT * this.getImageScale()) / Game.religionManager.religionImages.get(iReligionID).getHeight());
        this.iHeight = (int)(Game.religionManager.religionImages.get(iReligionID).getHeight() * (CFG.CIV_FLAG_HEIGHT * this.getImageScale()) / Game.religionManager.religionImages.get(iReligionID).getHeight());
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final float nAlpha, final int iMaxWidth) {
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, nAlpha));
        Game.religionManager.religionImages.get(this.iReligionID).draw(oSB, nPosX + this.offsetLeft, nPosY + CFG.PADDING + CFG.PADDING / 2 + CFG.TEXT_HEIGHT / 2 - this.iHeight / 2, this.iWidth, this.iHeight);
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
        return CFG.TEXT_HEIGHT / (float)CFG.CIV_FLAG_HEIGHT;
    }
}
