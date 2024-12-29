// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;

public class ButtonStats_WarScore2 extends Button
{
    public float fPerc;
    private int iAggressor;
    private int iDefender;
    
    public ButtonStats_WarScore2(final String sText, final int iPosX, final int iPosY, final int nWidth, final int nHeight, final int iAggressor, final int iDefender) {
        this.fPerc = 0.35f;
        this.init(sText, CFG.FONT_REGULAR_SMALL, 0, iPosX, iPosY, nWidth, nHeight, true, true, false, false);
        this.iAggressor = iAggressor;
        this.iDefender = iDefender;
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.3f));
        Images.pix.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(Game.getCiv(this.iAggressor).getColor(0.45f));
        Images.pix.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, (int)(this.getWidth() * this.fPerc), this.getHeight(), false, true);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, (int)(this.getWidth() * this.fPerc), CFG.PADDING, false, false);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING + iTranslateY, (int)(this.getWidth() * this.fPerc), CFG.PADDING, false, true);
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, (int)(this.getWidth() * this.fPerc), this.getHeight(), true, true);
        oSB.setColor(Game.getCiv(this.iDefender).getColor(0.45f));
        Images.pix.draw(oSB, this.getPosX() + (int)(this.getWidth() * this.fPerc) + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - (int)(this.getWidth() * this.fPerc), this.getHeight(), false, true);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + (int)(this.getWidth() * this.fPerc) + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - (int)(this.getWidth() * this.fPerc), CFG.PADDING, false, false);
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + (int)(this.getWidth() * this.fPerc) + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING + iTranslateY, this.getWidth() - (int)(this.getWidth() * this.fPerc), CFG.PADDING, false, true);
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, this.getPosX() + (int)(this.getWidth() * this.fPerc) + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - (int)(this.getWidth() * this.fPerc), this.getHeight(), false, true);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.785f));
        Images.pix.draw(oSB, this.getPosX() + (int)(this.getWidth() * this.fPerc) + iTranslateX, this.getPosY() + iTranslateY, 1, this.getHeight());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.95f));
        Images.pix.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getWidth() - 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
    }
    
    public static final float getBoxAlpha(final boolean clickable, final boolean isHovered, final boolean isActive) {
        return clickable ? (isActive ? 0.85f : (isHovered ? 0.7f : 0.5f)) : 0.2f;
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY, this.getColor(isActive));
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        return Colors.getColorButtonHover(isActive, this.getIsHovered());
    }
}
