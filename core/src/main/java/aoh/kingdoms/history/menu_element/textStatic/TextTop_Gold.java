// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Game;

public class TextTop_Gold extends TextTop
{
    public float lastValuePerMonth;
    
    public TextTop_Gold(final int imageID, final String sText, final String sText2, final int iPosX, final int iPosY) {
        super(imageID, sText, sText2, iPosX, iPosY);
        this.lastValuePerMonth = -997654.3f;
    }
    
    @Override
    public String getTextToDraw() {
        if (this.lastValue != Game.getCiv(Game.player.iCivID).fGold) {
            if (Game.getCiv(Game.player.iCivID).fGold >= 100000.0f) {
                this.setText("" + CFG.getShortNumber((int)Math.floor(Game.getCiv(Game.player.iCivID).fGold)));
            }
            else {
                this.setText("" + (int)Math.floor(Game.getCiv(Game.player.iCivID).fGold));
            }
            this.lastValue = Game.getCiv(Game.player.iCivID).fGold;
        }
        if (this.lastValuePerMonth != Game.getCiv(Game.player.iCivID).fTotalIncomePerMonth + Game.getCiv(Game.player.iCivID).civBonuses.MonthlyIncome - Game.getCiv(Game.player.iCivID).fTotalExpensesPerMonth) {
            final float fGold = Game.getCiv(Game.player.iCivID).fTotalIncomePerMonth + Game.getCiv(Game.player.iCivID).civBonuses.MonthlyIncome - Game.getCiv(Game.player.iCivID).fTotalExpensesPerMonth;
            if (Game.getCiv(Game.player.iCivID).fGold >= Game.getMaxAmountOfGold(Game.player.iCivID)) {
                this.setText2("---");
            }
            else {
                this.setText2(((fGold > 0.0f) ? "+" : "") + CFG.getPrecision2(fGold, (Math.abs(fGold) < 10.0f) ? 100 : ((Math.abs(fGold) < 100.0f) ? 10 : 1)));
            }
            this.lastValuePerMonth = fGold;
        }
        return super.getTextToDraw();
    }
    
    @Override
    public boolean getIsActiveButton() {
        return Game.menuManager.getVisibleInGame_Budget();
    }
}
