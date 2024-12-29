// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Game;

public class TextTop_Manpower extends TextTop
{
    public double lastValuePerMonth;
    public double lastValueDouble;
    
    public TextTop_Manpower(final int imageID, final String sText, final String sText2, final int iPosX, final int iPosY) {
        super(imageID, sText, sText2, iPosX, iPosY);
        this.lastValuePerMonth = -997654.3125;
        this.lastValueDouble = -997654.3125;
    }
    
    @Override
    public String getTextToDraw() {
        if (this.lastValuePerMonth != Game.getCiv(Game.player.iCivID).fManpowerPerMonth) {
            if (Game.getCiv(Game.player.iCivID).fManpowerPerMonth < 10.0) {
                this.setText2("+" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fManpowerPerMonth, 100));
            }
            else if (Game.getCiv(Game.player.iCivID).fManpowerPerMonth < 100.0) {
                this.setText2("+" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fManpowerPerMonth, 10));
            }
            else if (Game.getCiv(Game.player.iCivID).fManpowerPerMonth > 10000.0) {
                this.setText2("+" + CFG.getShortNumber((int)Game.getCiv(Game.player.iCivID).fManpowerPerMonth));
            }
            else {
                this.setText2("+" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fManpowerPerMonth, 1));
            }
            this.lastValuePerMonth = Game.getCiv(Game.player.iCivID).fManpowerPerMonth;
        }
        if (this.lastValueDouble != Game.getCiv(Game.player.iCivID).fManpower) {
            if (Game.getCiv(Game.player.iCivID).fManpower >= 10000.0) {
                this.setText("" + CFG.getShortNumber((int)Math.floor(Game.getCiv(Game.player.iCivID).fManpower)));
            }
            else {
                this.setText("" + (int)Math.floor(Game.getCiv(Game.player.iCivID).fManpower));
            }
            if (Game.getCiv(Game.player.iCivID).fManpower >= Game.getCiv(Game.player.iCivID).fManpowerMax) {
                this.setText2("---");
            }
            this.lastValueDouble = Game.getCiv(Game.player.iCivID).fManpower;
        }
        return super.getTextToDraw();
    }
    
    @Override
    public boolean getIsActiveButton() {
        return Game.menuManager.getVisibleInGame_RecruitArmy();
    }
}
