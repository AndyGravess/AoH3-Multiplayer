// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.menuElementHover;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuElement_HoverElement_Type_Empty implements MenuElement_HoverElement_Type
{
    private int iWidth;
    private int iHeight;
    
    public MenuElement_HoverElement_Type_Empty() {
        this.iWidth = 1;
        this.iHeight = 1;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, final float nAlpha, final int iMaxWidth) {
    }
    
    @Override
    public int getWidth() {
        return this.iWidth;
    }
    
    @Override
    public int getHeight() {
        return this.iHeight;
    }
}
