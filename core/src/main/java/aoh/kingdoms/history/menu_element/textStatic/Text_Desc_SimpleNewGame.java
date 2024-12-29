// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menus.NewGame.NewGameCiv;

public class Text_Desc_SimpleNewGame extends Text_Desc
{
    public Text_Desc_SimpleNewGame(final String sText, final int iPosX, final int iPosY, final int iWidth) {
        super(sText, iPosX, iPosY, iWidth);
        if (!NewGameCiv.expandCivDesc) {
            if (this.iLineSize > 3) {
                this.iLineSize = Math.min(this.iLineSize, 3);
                this.sLines.set(this.iLineSize - 1, this.sLines.get(this.iLineSize - 1) + "..");
            }
            this.setHeight(this.iTextHeight * this.iLineSize + (this.iLineSize - 1) * CFG.PADDING * 2 + this.getPaddingY() * 2);
        }
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, Text_Desc.getBoxAlpha(this.getClickable(), this.getIsHovered(), isActive)));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 0.8f);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.75f));
        ImageManager.getImage(Images.activeSort).draw(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.activeSort).getWidth() - ImageManager.getImage(Images.activeSort).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.activeSort).getHeight() / 2 + iTranslateY, false, NewGameCiv.expandCivDesc);
        oSB.setColor(Color.WHITE);
        for (int i = 0; i < this.iLineSize; ++i) {
            Renderer.drawTextWithShadow(oSB, this.fontID, this.sLines.get(i), this.getPosX() + this.getPadding() + iTranslateX, this.getPosY() + this.getPaddingY() + (this.iTextHeight + CFG.PADDING * 2) * i + iTranslateY, this.getColor(isActive));
        }
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        if (isActive) {
            return Colors.BUTTON_TEXT_ACTIVE;
        }
        if (this.getIsHovered()) {
            return Colors.BUTTON_TEXT_HOVERED;
        }
        return this.getClickable() ? Colors.BUTTON_TEXT_DESC_SIMPLE : Colors.BUTTON_TEXT_DISABLED;
    }
}
