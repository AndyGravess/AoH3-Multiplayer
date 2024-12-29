// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.menuElementHover;

import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.Color;

public class MenuElement_HoverElement_Type_Color implements MenuElement_HoverElement_Type
{
    private Color oColor;
    private int offsetLeft;
    private int offsetRight;
    private int iHeight;
    
    public MenuElement_HoverElement_Type_Color(final Color oColor) {
        this.offsetLeft = 0;
        this.offsetRight = 0;
        this.init(oColor, this.iHeight = 0, 0, (int)(CFG.TEXT_HEIGHT * 0.9f));
    }
    
    public MenuElement_HoverElement_Type_Color(final Color oColor, final int offsetLeft) {
        this.offsetLeft = 0;
        this.offsetRight = 0;
        this.iHeight = 0;
        this.init(oColor, offsetLeft, CFG.PADDING, (int)(CFG.TEXT_HEIGHT * 0.9f));
    }
    
    public MenuElement_HoverElement_Type_Color(final Color oColor, final int offsetLeft, final int offsetRight) {
        this.offsetLeft = 0;
        this.offsetRight = 0;
        this.iHeight = 0;
        this.init(oColor, offsetLeft, offsetRight, (int)(CFG.TEXT_HEIGHT * 0.9f));
    }
    
    public MenuElement_HoverElement_Type_Color(final Color oColor, final int offsetLeft, final int offsetRight, final int nColorHeight) {
        this.offsetLeft = 0;
        this.offsetRight = 0;
        this.iHeight = 0;
        this.init(oColor, offsetLeft, offsetRight, nColorHeight);
    }
    
    private final void init(final Color oColor, final int offsetLeft, final int offsetRight, final int nHeight) {
        this.oColor = oColor;
        this.offsetLeft = offsetLeft;
        this.offsetRight = offsetRight;
        this.iHeight = nHeight;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final float nAlpha, final int iMaxWidth) {
        oSB.setColor(new Color(this.oColor.r, this.oColor.g, this.oColor.b, nAlpha));
        Images.pix.draw(oSB, nPosX + this.offsetLeft, nPosY + CFG.PADDING + CFG.PADDING / 2 + (int)((CFG.TEXT_HEIGHT - CFG.TEXT_HEIGHT * 0.9f) / 2.0f), CFG.COLOR_WIDTH, this.iHeight);
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    public int getWidth() {
        return this.offsetRight + this.offsetLeft + CFG.COLOR_WIDTH;
    }
    
    @Override
    public int getHeight() {
        return CFG.TEXT_HEIGHT + CFG.PADDING;
    }
}
