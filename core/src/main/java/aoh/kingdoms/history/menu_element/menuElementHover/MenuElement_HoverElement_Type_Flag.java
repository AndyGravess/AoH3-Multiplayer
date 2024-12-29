// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.menuElementHover;

import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;

public class MenuElement_HoverElement_Type_Flag implements MenuElement_HoverElement_Type
{
    private int iCivID;
    private int offsetLeft;
    private int offsetRight;
    
    public MenuElement_HoverElement_Type_Flag(final int iCivID) {
        this.offsetLeft = 0;
        this.offsetRight = 0;
        this.iCivID = iCivID;
        this.offsetLeft = 0;
        this.offsetRight = CFG.PADDING;
    }
    
    public MenuElement_HoverElement_Type_Flag(final int iCivID, final int offsetLeft) {
        this.offsetLeft = 0;
        this.offsetRight = 0;
        this.iCivID = iCivID;
        this.offsetLeft = offsetLeft;
        this.offsetRight = CFG.PADDING;
    }
    
    public MenuElement_HoverElement_Type_Flag(final int iCivID, final int offsetLeft, final int offsetRight) {
        this.offsetLeft = 0;
        this.offsetRight = 0;
        this.iCivID = iCivID;
        this.offsetLeft = offsetLeft;
        this.offsetRight = offsetRight;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final float nAlpha, final int iMaxWidth) {
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, nAlpha));
        try {
            if (this.iCivID >= 0) {
                Game.getCiv(this.iCivID).getFlag().draw(oSB, nPosX + this.offsetLeft, nPosY + CFG.PADDING + CFG.PADDING / 2 + CFG.TEXT_HEIGHT / 2 - (int)(CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0f), (int)(CFG.CIV_FLAG_WIDTH * this.getImageScale()), (int)(CFG.CIV_FLAG_HEIGHT * this.getImageScale()));
            }
            else {
                ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, nPosX + this.offsetLeft, nPosY + CFG.PADDING + CFG.PADDING / 2 + CFG.TEXT_HEIGHT / 2 - (int)(CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0f), (int)(CFG.CIV_FLAG_WIDTH * this.getImageScale()), (int)(CFG.CIV_FLAG_HEIGHT * this.getImageScale()));
            }
        }
        catch (final IndexOutOfBoundsException e) {
            ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, nPosX + this.offsetLeft, nPosY + CFG.PADDING + CFG.PADDING / 2 + CFG.TEXT_HEIGHT / 2 - (int)(CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0f), (int)(CFG.CIV_FLAG_WIDTH * this.getImageScale()), (int)(CFG.CIV_FLAG_HEIGHT * this.getImageScale()));
        }
        ImageManager.getImage(Images.flag_rect).draw(oSB, nPosX + this.offsetLeft, nPosY + CFG.PADDING + CFG.PADDING / 2 + CFG.TEXT_HEIGHT / 2 - (int)(CFG.CIV_FLAG_HEIGHT * this.getImageScale() / 2.0f), (int)(CFG.CIV_FLAG_WIDTH * this.getImageScale()), (int)(CFG.CIV_FLAG_HEIGHT * this.getImageScale()));
    }
    
    @Override
    public int getWidth() {
        return this.offsetRight + this.offsetLeft + (int)(CFG.CIV_FLAG_WIDTH * this.getImageScale());
    }
    
    @Override
    public int getHeight() {
        return CFG.TEXT_HEIGHT + CFG.PADDING;
    }
    
    private final float getImageScale() {
        return CFG.TEXT_HEIGHT / (float)CFG.CIV_FLAG_HEIGHT * 1.2f;
    }
}
