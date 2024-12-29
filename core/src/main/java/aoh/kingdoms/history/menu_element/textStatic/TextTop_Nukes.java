// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.map.province.ProvinceInvest;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Game;

public class TextTop_Nukes extends TextTop
{
    public int lastValuePerMonth;
    
    public TextTop_Nukes(final int imageID, final String sText, final String sText2, final int iPosX, final int iPosY) {
        super(imageID, sText, sText2, iPosX, iPosY);
        this.lastValuePerMonth = -997654;
        this.setText2("---");
    }
    
    @Override
    public String getTextToDraw() {
        if (this.lastValuePerMonth != Game.getCiv(Game.player.iCivID).getNukes()) {
            this.setText(CFG.getPrecision2((float)Game.getCiv(Game.player.iCivID).getNukes(), 1));
            this.lastValuePerMonth = Game.getCiv(Game.player.iCivID).getNukes();
        }
        if (Game.getCiv(Game.player.iCivID).iNukesSize > 0) {
            try {
                if (this.lastValue != Game.getCiv(Game.player.iCivID).nukesDaysLeft.get(0).daysLeft / (float)Game.getCiv(Game.player.iCivID).nukesDaysLeft.get(0).investTime) {
                    this.setText2(CFG.getPrecision2((1.0f - Game.getCiv(Game.player.iCivID).nukesDaysLeft.get(0).daysLeft / (float)Game.getCiv(Game.player.iCivID).nukesDaysLeft.get(0).investTime) * 100.0f, 1) + "%");
                    this.lastValue = Game.getCiv(Game.player.iCivID).nukesDaysLeft.get(0).daysLeft / (float)Game.getCiv(Game.player.iCivID).nukesDaysLeft.get(0).investTime;
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
        else if (this.lastValue != 97948.25f) {
            this.setText2("---");
            this.lastValue = 97948.25f;
        }
        return super.getTextToDraw();
    }
    
    @Override
    public boolean getIsActiveButton() {
        return Game.menuManager.getVisibleInGame_Nukes();
    }
}
