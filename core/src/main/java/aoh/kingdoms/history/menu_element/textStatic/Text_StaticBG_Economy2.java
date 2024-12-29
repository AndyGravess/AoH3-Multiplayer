// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.mainGame.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text_StaticBG_Economy2 extends Text_StaticBG_Economy
{
    public Text_StaticBG_Economy2(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final int id) {
        super(sText, fontID, iTextPositionX, iPosX, iPosY, iWidth, iHeight, id);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        if (Game.canInvestInEconomy(this.id)) {
            oSB.setColor(new Color(Colors.HOVER_NEGATIVE.r, Colors.HOVER_NEGATIVE.g, Colors.HOVER_NEGATIVE.b, 0.4f));
            Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
            oSB.setColor(Color.WHITE);
        }
        super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        if (Game.canInvestInEconomy(this.id)) {
            return Colors.COLOR_TEXT_MODIFIER_NEGATIVE;
        }
        return super.getColor(isActive);
    }
}
