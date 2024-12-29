// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Game;

public class TextTop_Diplomacy extends TextTop
{
    public float lastValuePerMonth;
    
    public TextTop_Diplomacy(final int imageID, final String sText, final String sText2, final int iPosX, final int iPosY) {
        super(imageID, sText, sText2, iPosX, iPosY);
        this.lastValuePerMonth = -997654.3f;
    }
    
    @Override
    public String getTextToDraw() {
        if (this.lastValue != Game.getCiv(Game.player.iCivID).fDiplomacy) {
            if (Game.getCiv(Game.player.iCivID).fDiplomacy >= 10000.0f) {
                this.setText("" + CFG.getShortNumber((int)Math.floor(Game.getCiv(Game.player.iCivID).fDiplomacy)));
            }
            else {
                this.setText(CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fDiplomacy, (Game.getCiv(Game.player.iCivID).fDiplomacy < 100.0f) ? ((Game.getCiv(Game.player.iCivID).fDiplomacy < 10.0f) ? 10 : 10) : 1));
            }
            this.lastValue = Game.getCiv(Game.player.iCivID).fDiplomacy;
        }
        if (this.lastValuePerMonth != Game.getCiv(Game.player.iCivID).getDiplomacyPerMonth()) {
            this.lastValuePerMonth = Game.getCiv(Game.player.iCivID).getDiplomacyPerMonth();
            this.setText2(((this.lastValuePerMonth > 0.0f) ? "+" : "") + CFG.getPrecision2(this.lastValuePerMonth, (this.lastValuePerMonth < 100.0f) ? ((this.lastValuePerMonth < 10.0f) ? 100 : 10) : 1));
        }
        return super.getTextToDraw();
    }
    
    @Override
    public boolean getIsActiveButton() {
        return Game.menuManager.getVisibleInGame_Civ();
    }
}
