// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Game;

public class Text_StaticBG_RulerTitle_Nukes extends Text_StaticBG_RulerTitle
{
    public int lastValue;
    
    public Text_StaticBG_RulerTitle_Nukes(final String sText, final int iPosX, final int iPosY, final int iWidth, final int iHeight) {
        super(sText, iPosX, iPosY, iWidth, iHeight);
        this.lastValue = -975124;
    }
    
    @Override
    public String getTextToDraw() {
        if (this.lastValue != Game.getCiv(Game.player.iCivID).getNukes()) {
            this.setText(Game.lang.get("AtomicBombs") + ": " + CFG.getPrecision2((float)Game.getCiv(Game.player.iCivID).getNukes(), 1));
            this.lastValue = Game.getCiv(Game.player.iCivID).getNukes();
        }
        return super.getTextToDraw();
    }
}
