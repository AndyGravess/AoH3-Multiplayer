// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Game;

public class TextTop_Technology extends TextTop
{
    public float lastValue;
    public float lastValueResearch;
    
    public TextTop_Technology(final int imageID, final String sText, final String sText2, final int iPosX, final int iPosY) {
        super(imageID, sText, sText2, iPosX, iPosY);
        this.lastValue = -997654.3f;
        this.lastValueResearch = -997654.3f;
    }
    
    @Override
    public String getTextToDraw() {
        if (Game.getCiv(Game.player.iCivID).getActiveTechResearch() < 0) {
            if (this.lastValue != -997652.3f) {
                this.setText("---");
                this.lastValue = 997652.3f;
            }
        }
        else if (this.lastValue != Game.getCiv(Game.player.iCivID).getResearchProgress(Game.getCiv(Game.player.iCivID).getActiveTechResearch())) {
            this.setText("" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).getResearchProgress(Game.getCiv(Game.player.iCivID).getActiveTechResearch()) * 100.0f, 1) + "%");
            this.lastValue = Game.getCiv(Game.player.iCivID).getResearchProgress(Game.getCiv(Game.player.iCivID).getActiveTechResearch());
        }
        if (this.lastValueResearch != Game.getCiv(Game.player.iCivID).fResearchPerMonth) {
            this.setText2(((Game.getCiv(Game.player.iCivID).fResearchPerMonth > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fResearchPerMonth, (Game.getCiv(Game.player.iCivID).fResearchPerMonth < 100.0f) ? ((Game.getCiv(Game.player.iCivID).fResearchPerMonth < 10.0f) ? 100 : 10) : 1));
            this.lastValueResearch = Game.getCiv(Game.player.iCivID).fResearchPerMonth;
        }
        return super.getTextToDraw();
    }
    
    @Override
    public boolean getIsActiveButton() {
        return Game.menuManager.getVisibleInGame_TechnologyChoose();
    }
}
