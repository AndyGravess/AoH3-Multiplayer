// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.menuElementHover;

import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.mainGame.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;

public class MenuElement_HoverElement_Type_BattleRegiment implements MenuElement_HoverElement_Type
{
    public int iCivID;
    public int iImageID;
    public int offsetLeft;
    public int offsetRight;
    
    public MenuElement_HoverElement_Type_BattleRegiment(final int iCivID, final int iImageID) {
        this.init(iCivID, iImageID, 0, CFG.PADDING / 2);
    }
    
    public MenuElement_HoverElement_Type_BattleRegiment(final int iCivID, final int iImageID, final int offsetLeft) {
        this.init(iCivID, iImageID, offsetLeft, CFG.PADDING / 2);
    }
    
    public MenuElement_HoverElement_Type_BattleRegiment(final int iCivID, final int iImageID, final int offsetLeft, final int offsetRight) {
        this.init(iCivID, iImageID, offsetLeft, offsetRight);
    }
    
    private final void init(final int iCivID, final int iImageID, final int offsetLeft, final int offsetRight) {
        this.iCivID = iCivID;
        this.iImageID = iImageID;
        this.offsetLeft = offsetLeft;
        this.offsetRight = offsetRight;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final float nAlpha, final int iMaxWidth) {
        oSB.setColor(new Color(Game.getCiv(this.iCivID).getR(), Game.getCiv(this.iCivID).getG(), Game.getCiv(this.iCivID).getB(), nAlpha));
        Images.pix.draw(oSB, nPosX + this.offsetLeft, nPosY + CFG.PADDING / 2, ImageManager.getImage(this.iImageID).getWidth(), ImageManager.getImage(this.iImageID).getHeight());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.4f * nAlpha));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX + this.offsetLeft, nPosY + CFG.PADDING / 2, ImageManager.getImage(this.iImageID).getWidth(), ImageManager.getImage(this.iImageID).getHeight());
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, nAlpha));
        ImageManager.getImage(this.iImageID).draw(oSB, nPosX + this.offsetLeft, nPosY + CFG.PADDING / 2);
    }
    
    @Override
    public int getWidth() {
        return this.offsetLeft + this.offsetRight + ImageManager.getImage(this.iImageID).getWidth();
    }
    
    @Override
    public int getHeight() {
        return ImageManager.getImage(this.iImageID).getHeight();
    }
}
