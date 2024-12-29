// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element;

public class Empty extends MenuElement
{
    public Empty(final int iPosX, final int iPosY, final int iWidth, final int iHeight) {
        this.typeOfElement = MenuElement_Type.TRANSPARENT_BACKGROUND;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidth(iWidth);
        this.setHeight(iHeight);
        this.setClickable(false);
        this.setVisible(true);
    }
    
    @Override
    public boolean playSFX_Hovered() {
        return false;
    }
}
