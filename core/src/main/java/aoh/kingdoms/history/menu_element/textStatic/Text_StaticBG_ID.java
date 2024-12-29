// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

public class Text_StaticBG_ID extends Text_StaticBG
{
    public int id;
    
    public Text_StaticBG_ID(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final int id) {
        super(sText, fontID, iTextPositionX, iPosX, iPosY, iWidth, iHeight);
        this.id = id;
    }
    
    @Override
    public int getCurrent() {
        return this.id;
    }
}
