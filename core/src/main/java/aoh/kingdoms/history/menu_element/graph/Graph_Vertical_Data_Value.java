// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.graph;

import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;

public class Graph_Vertical_Data_Value
{
    protected int iValue;
    protected int iHeight;
    protected static final float ALPHA = 0.35f;
    protected static final float ALPHA_GRADIENT = 0.7f;
    protected static final float ALPHA_GRADIENT2 = 0.35f;
    protected static final Color COLOR_VALUE_BORDER;
    protected int iColorDataID;
    
    protected Graph_Vertical_Data_Value(final int iValue, final int iColorDataID) {
        this.iColorDataID = 0;
        this.iValue = iValue;
        this.iColorDataID = iColorDataID;
    }
    
    protected void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final Color nColor) {
        this.drawData(oSB, nPosX, nPosY, nWidth, nHeight, this.iHeight, nColor);
    }
    
    protected void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final int nAnimationHeight, final Color nColor) {
        this.drawData(oSB, nPosX, nPosY, nWidth, nHeight, nAnimationHeight, nColor);
    }
    
    private void drawData(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final int nHeight2, final Color nColor) {
        oSB.setColor(new Color(nColor.r, nColor.g, nColor.b, 0.35f));
        Images.pix.draw(oSB, nPosX, nPosY - nHeight - nHeight2, nWidth, nHeight2);
        oSB.setColor(new Color(nColor.r, nColor.g, nColor.b, 0.7f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX, nPosY - nHeight - nHeight2, nWidth, nHeight2, false, true);
        oSB.setColor(new Color(nColor.r, nColor.g, nColor.b, 0.35f));
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, nPosX, nPosY - nHeight - nHeight2, nWidth / 3, nHeight2, false, false);
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, nPosX + nWidth - nWidth / 3, nPosY - nHeight - nHeight2, nWidth / 3, nHeight2, true, false);
    }
    
    protected final int getValue() {
        return this.iValue;
    }
    
    protected final int getHeight() {
        return this.iHeight;
    }
    
    protected final void setHeight(final int iHeight) {
        this.iHeight = iHeight;
        if (this.iHeight < 1) {
            this.iHeight = 1;
        }
    }
    
    protected final int getDataTypeID() {
        return this.iColorDataID;
    }
    
    static {
        COLOR_VALUE_BORDER = new Color(0.9f, 0.9f, 0.9f, 0.1f);
    }
}
