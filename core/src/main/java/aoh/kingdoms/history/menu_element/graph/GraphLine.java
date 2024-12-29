// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.graph;

import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GraphLine
{
    private int iPosY;
    private int iWidth;
    private float fAngle;
    
    protected GraphLine(final int fromPosX, final int fromPosY, final int toPosX, final int toPosY) {
        this.iPosY = fromPosY;
        this.iWidth = (int)Math.ceil(Math.sqrt((toPosX - fromPosX) * (toPosX - fromPosX) + (fromPosY - toPosY) * (fromPosY - toPosY)));
        this.fAngle = (float)(Math.atan2(fromPosY - toPosY, -fromPosX + toPosX) * 180.0 / 3.141592653589793);
    }
    
    protected void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final int i) {
        Images.pix.draw(oSB, nPosX, nPosY + this.iPosY, this.iWidth, 1, this.fAngle);
    }
    
    protected final int getPosY() {
        return this.iPosY;
    }
    
    protected final int getWidth() {
        return this.iWidth;
    }
    
    protected final void setWidth(final int iWidth) {
        this.iWidth = iWidth;
    }
}
