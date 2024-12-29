// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;

public class ButtonStatsBudget_Balance extends Button
{
    public int imageID;
    public int iconWidth;
    public int iconHeight;
    public String sText2;
    public int iTextWidth2;
    public int iTextHeight2;
    public float lastValue;
    public Color nColor;
    public Color nColorH;
    public Color nColorA;
    
    public ButtonStatsBudget_Balance(final String sText, final String sText2, final int imageID, final int iPosX, final int iPosY, final int nWidth, final int nHeight) {
        this.lastValue = -997654.3f;
        this.nColor = Colors.COLOR_TEXT_MODIFIER_POSITIVE;
        this.nColorH = Colors.COLOR_TEXT_MODIFIER_POSITIVE_HOVER;
        this.nColorA = Colors.COLOR_TEXT_MODIFIER_POSITIVE_ACTIVE;
        this.init(sText, CFG.FONT_REGULAR, 0, iPosX, iPosY, nWidth, nHeight, true, true, false, false);
        this.imageID = imageID;
        final float iconScale = this.getImageScale(imageID) * 1.2f;
        this.iconWidth = (int)(ImageManager.getImage(imageID).getWidth() * iconScale);
        this.iconHeight = (int)(ImageManager.getImage(imageID).getHeight() * iconScale);
        this.setText2(sText2);
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, getBoxAlpha(this.getClickable(), this.getIsHovered(), isActive)));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.45f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.8f));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 1.0f));
        Renderer.drawBox(oSB, Images.statsRectBGBorder, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
    }
    
    public static final float getBoxAlpha(final boolean clickable, final boolean isHovered, final boolean isActive) {
        return clickable ? (isActive ? 0.85f : (isHovered ? 0.7f : 0.5f)) : 0.2f;
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        ImageManager.getImage(this.imageID).draw(oSB, this.getPosX() + this.getWidth() / 2 - (this.getTextWidth() + this.iTextWidth2 + this.iconWidth) / 2 + this.getTextWidth() + this.iTextWidth2 + CFG.PADDING + iTranslateX, this.getPosY() + (this.getHeight() - this.iconHeight) / 2 + iTranslateY, this.iconWidth, this.iconHeight);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.getWidth() / 2 - (this.getTextWidth() + this.iTextWidth2 + this.iconWidth) / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY, this.getColor2(isActive));
        Renderer.drawTextWithShadow(oSB, CFG.FONT_BOLD, this.getText2(), this.getPosX() + this.getWidth() / 2 - (this.getTextWidth() + this.iTextWidth2 + this.iconWidth) / 2 + this.getTextWidth() + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iTextHeight2 / 2 + iTranslateY, this.getColor(isActive));
    }
    
    private final float getImageScale(final int iImageID) {
        return CFG.TEXT_HEIGHT / (float)ImageManager.getImage(iImageID).getHeight();
    }
    
    protected Color getColor2(final boolean isActive) {
        return Colors.getColorTopStats(isActive, this.getIsHovered());
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        if (isActive) {
            return this.nColorA;
        }
        if (this.getIsHovered()) {
            return this.nColorH;
        }
        return this.getClickable() ? this.nColor : Colors.BUTTON_TEXT_DISABLED;
    }
    
    public String getText2() {
        if (this.lastValue != Game.getCiv(Game.player.iCivID).fTotalIncomePerMonth + Game.getCiv(Game.player.iCivID).civBonuses.MonthlyIncome - Game.getCiv(Game.player.iCivID).fTotalExpensesPerMonth) {
            final float fGold = Game.getCiv(Game.player.iCivID).fTotalIncomePerMonth + Game.getCiv(Game.player.iCivID).civBonuses.MonthlyIncome - Game.getCiv(Game.player.iCivID).fTotalExpensesPerMonth;
            this.setText2(((fGold > 0.0f) ? "+" : "") + ((fGold >= 1000.0f) ? CFG.getNumberWithSpaces("" + (int)fGold) : CFG.getPrecision2(fGold, (fGold >= 100.0f) ? 10 : 100)));
            this.lastValue = fGold;
            if (fGold == 0.0f) {
                this.nColor = Colors.COLOR_TEXT_MODIFIER_NEUTRAL;
                this.nColorH = Colors.COLOR_TEXT_MODIFIER_NEUTRAL_HOVER;
                this.nColorA = Colors.COLOR_TEXT_MODIFIER_NEUTRAL_ACTIVE;
            }
            else if (fGold > 0.0f) {
                this.nColor = Colors.COLOR_TEXT_MODIFIER_POSITIVE;
                this.nColorH = Colors.COLOR_TEXT_MODIFIER_POSITIVE_HOVER;
                this.nColorA = Colors.COLOR_TEXT_MODIFIER_POSITIVE_ACTIVE;
            }
            else {
                this.nColor = Colors.COLOR_TEXT_MODIFIER_NEGATIVE;
                this.nColorH = Colors.COLOR_TEXT_MODIFIER_NEGATIVE_HOVER;
                this.nColorA = Colors.COLOR_TEXT_MODIFIER_NEGATIVE_ACTIVE;
            }
        }
        return this.sText2;
    }
    
    @Override
    public final void setText2(final String sText2) {
        this.sText2 = sText2;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_BOLD), sText2);
        this.iTextWidth2 = (int)Renderer.glyphLayout.width;
        this.iTextHeight2 = (int)Renderer.glyphLayout.height;
    }
}
