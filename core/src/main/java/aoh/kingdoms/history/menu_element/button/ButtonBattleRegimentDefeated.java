// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.textures.ImageManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ButtonBattleRegimentDefeated extends ButtonBattleRegiment
{
    public ButtonBattleRegimentDefeated(final int nCivID, final int imageID, final int iPosX, final int iPosY, final int id, final int offsetY) {
        super(nCivID, imageID, iPosX, iPosY, id, offsetY, 0, false, false);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, (this.getIsHovered() || isActive) ? 0.35f : 0.15f));
        ImageManager.getImage(this.imageID).draw(oSB, this.getPosX() + 1 + iTranslateX, this.getPosY() + 1 + iTranslateY);
        oSB.setColor(Color.WHITE);
        if (this.getIsHovered()) {
            oSB.setColor(Colors.HOVER_GOLD);
            Renderer.drawBox2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        }
        oSB.setColor(Color.WHITE);
    }
}
