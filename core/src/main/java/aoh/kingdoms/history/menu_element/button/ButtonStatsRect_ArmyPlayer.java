// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;

public class ButtonStatsRect_ArmyPlayer extends Button
{
    public int id;
    public int imgID;
    
    public ButtonStatsRect_ArmyPlayer(final String sText, final int id, final int imgID, final int iPosX, final int iPosY, final int nWidth, final int nHeight) {
        this.init(sText, CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, iPosX, iPosY, nWidth, nHeight, true, true, false, false);
        this.id = id;
        this.imgID = imgID;
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, getBoxAlpha(this.getClickable(), this.getIsHovered(), isActive)));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        final int imgW = Math.min(ImageManager.getImage(this.imgID).getWidth() / 2, this.getWidth() / 4);
        ImageManager.getImage(this.imgID).draw2(oSB, this.getPosX() + this.getWidth() / 2 - imgW / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(this.imgID).getHeight() / 2 + iTranslateY, imgW, ImageManager.getImage(this.imgID).getHeight(), true, false);
    }
    
    public static final float getBoxAlpha(final boolean clickable, final boolean isHovered, final boolean isActive) {
        return clickable ? (isActive ? 0.85f : (isHovered ? 0.7f : 0.5f)) : 0.2f;
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        Renderer.drawText(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY, this.getColor(isActive));
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        if (isActive) {
            return Colors.BUTTON_TEXT_ACTIVE;
        }
        if (this.getIsHovered()) {
            return Colors.BUTTON_TEXT_HOVERED;
        }
        return Colors.HOVER_RIGHT2;
    }
    
    @Override
    public void actionElement() {
        Game.player.playerData.armyImgID = this.id;
        Game.getCiv(Game.player.iCivID).updateArmyImgID_Player();
        Game.menuManager.addToastGold(Game.lang.get("Done"), Images.v);
    }
}
