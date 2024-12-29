// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Game;

public class TextIcon2_Devastation extends TextIcon2
{
    public float lastValue;
    public int iProvinceID;
    
    public TextIcon2_Devastation(final String sText, final int imageID, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final int iProvinceID) {
        super(sText, imageID, nPosX, nPosY, nWidth, nHeight);
        this.lastValue = -997654.3f;
        this.iProvinceID = iProvinceID;
    }
    
    @Override
    public String getTextToDraw() {
        if (this.lastValue != Game.getProvince(this.iProvinceID).getDevastation()) {
            this.setText("" + CFG.getPrecision2(Game.getProvince(this.iProvinceID).getDevastation() * 100.0f, 10) + "%");
            this.lastValue = Game.getProvince(this.iProvinceID).getDevastation();
        }
        return super.getTextToDraw();
    }
    
    @Override
    public int getCurrent() {
        return this.iProvinceID;
    }
}
