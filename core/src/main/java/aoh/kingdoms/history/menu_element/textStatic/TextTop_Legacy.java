// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Game;

public class TextTop_Legacy extends TextTop
{
    public float lastValuePerMonth;
    
    public TextTop_Legacy(final int imageID, final String sText, final String sText2, final int iPosX, final int iPosY) {
        super(imageID, sText, sText2, iPosX, iPosY);
        this.lastValuePerMonth = -997654.3f;
    }
    
    @Override
    public String getTextToDraw() {
        if (this.lastValue != Game.getCiv(Game.player.iCivID).fLegacy) {
            if (Game.getCiv(Game.player.iCivID).fLegacy >= 10000.0f) {
                this.setText("" + CFG.getShortNumber((int)Math.floor(Game.getCiv(Game.player.iCivID).fLegacy)));
            }
            else {
                this.setText(CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fLegacy, (Game.getCiv(Game.player.iCivID).fLegacy < 100.0f) ? ((Game.getCiv(Game.player.iCivID).fLegacy < 10.0f) ? 10 : 10) : 1));
            }
            this.lastValue = Game.getCiv(Game.player.iCivID).fLegacy;
        }
        if (this.lastValuePerMonth != Game.getCiv(Game.player.iCivID).getLegacyPerMonth()) {
            this.lastValuePerMonth = Game.getCiv(Game.player.iCivID).getLegacyPerMonth();
            this.setText2(((this.lastValuePerMonth > 0.0f) ? "+" : "") + CFG.getPrecision2(this.lastValuePerMonth, (this.lastValuePerMonth < 100.0f) ? ((this.lastValuePerMonth < 10.0f) ? 100 : 10) : 1));
        }
        return super.getTextToDraw();
    }
}
