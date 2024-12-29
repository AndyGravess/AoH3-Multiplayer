// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.DrawOver;

import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.button.ButtonFlag;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.CFG;

public class InGameDrawOver_InvasionArmy extends InGameDrawOver
{
    public InGameDrawOver_InvasionArmy() {
        this.fontID = CFG.FONT_BOLD_SMALL;
        this.sText = Game.lang.get("SelectProvincesToConquer");
        Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), this.sText);
        this.iTextWidth = (int)Renderer.glyphLayout.width;
        this.iTextHeight = (int)Renderer.glyphLayout.height;
        this.iPosY = ButtonFlag.getButtonHeight() + CFG.PADDING;
        InGameDrawOver_InvasionArmy.drawAllTheTime = true;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY) {
        if (InGameDrawOver_InvasionArmy.drawAllTheTime) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.6f));
            final int tX = CFG.GAME_WIDTH / 2 - this.iTextWidth / 2 - CFG.PADDING * 7 + iTranslateX;
            Renderer.drawBoxCorner2(oSB, tX, this.iPosY - CFG.PADDING * 2 + iTranslateY, this.iTextWidth + CFG.PADDING * 14, this.iTextHeight + CFG.PADDING * 4);
            oSB.setColor(Color.WHITE);
            Renderer.drawTextWithShadow(oSB, this.fontID, this.sText, CFG.GAME_WIDTH / 2 - this.iTextWidth / 2 + iTranslateX, this.iPosY + CFG.PADDING + iTranslateY, Colors.COLOR_TOP_STATS3);
        }
    }
}
