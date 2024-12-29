// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.textures.Images;

public class ButtonMainReverse extends ButtonMain
{
    public ButtonMainReverse(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int nWidth, final boolean isClickable) {
        super(sText, fontID, iTextPositionX, iPosX, iPosY, nWidth, isClickable);
    }
    
    @Override
    public int getButtonBG() {
        return Images.buttonMenuH;
    }
    
    @Override
    public int getButtonBG_Active() {
        return Images.buttonMenu;
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        if (isActive) {
            return Colors.BUTTON_TEXT;
        }
        if (this.getIsHovered()) {
            return Colors.BUTTON_TEXT_HOVERED;
        }
        return this.getClickable() ? Colors.BUTTON_TEXT_ACTIVE : Colors.BUTTON_TEXT_DISABLED;
    }
}
