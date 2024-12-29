// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.Color;

public class ButtonGame_Style extends Button
{
    protected static long lTimeAnimation;
    protected static int animationState;
    protected static final int ANIMATION_T = 1000;
    public long lTimeAnimation2;
    public int animationState2;
    public final int ANIMATION_T2 = 1500;
    public int iCurrent;
    private static final Color colorLine;
    
    public ButtonGame_Style(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int nWidth, final boolean isClickable) {
        this.lTimeAnimation2 = 0L;
        this.animationState2 = 0;
        this.iCurrent = 0;
        this.init(sText, fontID, iTextPositionX, iPosX, iPosY, nWidth, CFG.BUTTON_HEIGHT2, isClickable, true, false, false);
        this.lTimeAnimation2 = CFG.currentTimeMillis;
    }
    
    public ButtonGame_Style(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int nWidth, final int nHeight, final boolean isClickable) {
        this.lTimeAnimation2 = 0L;
        this.animationState2 = 0;
        this.iCurrent = 0;
        this.init(sText, fontID, iTextPositionX, iPosX, iPosY, nWidth, nHeight, isClickable, true, false, false);
        this.lTimeAnimation2 = CFG.currentTimeMillis;
    }
    
    public ButtonGame_Style(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int nWidth, final boolean isClickable, final boolean checkBox) {
        this.lTimeAnimation2 = 0L;
        this.animationState2 = 0;
        this.iCurrent = 0;
        this.init(sText, fontID, iTextPositionX, iPosX, iPosY, nWidth, CFG.BUTTON_HEIGHT2, isClickable, true, true, checkBox);
        this.lTimeAnimation2 = CFG.currentTimeMillis;
    }
    
    public static Color getColorGradient() {
        return Colors.COLOR_GRADIENT_OVER_BLUE;
    }
    
    public static Color getColorGradient2() {
        return Colors.COLOR_GRADIENT;
    }
    
    public static float getAlpha() {
        return 0.55f;
    }
    
    public static float getAlpha2() {
        return 0.4f;
    }
    
    public static final float getBoxAlpha(final boolean clickable, final boolean isHovered, final boolean isActive) {
        return clickable ? (isActive ? 0.85f : (isHovered ? 0.7f : 0.5f)) : 0.2f;
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, getBoxAlpha(this.getClickable(), this.getIsHovered(), isActive)));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, (this.getIsHovered() || isActive) ? 0.65f : 0.35f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.35f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.3f));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.9f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth(), 1);
        if (this.getClickable() && this.getIsHovered() && ButtonGame_Style.animationState >= 0) {
            if (ButtonGame_Style.animationState == 0) {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - ButtonGame_Style.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + 1 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (ButtonGame_Style.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    ++ButtonGame_Style.animationState;
                    ButtonGame_Style.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            else {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - ButtonGame_Style.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (ButtonGame_Style.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    ButtonGame_Style.animationState = 0;
                    ButtonGame_Style.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            oSB.setColor(Color.WHITE);
        }
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY, this.getColor(isActive));
    }
    
    protected static final Color getColorLine() {
        return ButtonGame_Style.colorLine;
    }
    
    @Override
    public void setIsHovered(final boolean isHovered) {
        super.setIsHovered(isHovered);
        ButtonGame_Style.lTimeAnimation = CFG.currentTimeMillis;
        ButtonGame_Style.animationState = 0;
    }
    
    @Override
    public void setCurrent(final int nCurrent) {
        this.iCurrent = nCurrent;
    }
    
    @Override
    public int getCurrent() {
        return this.iCurrent;
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        if (!this.getClickable()) {
            return Colors.BUTTON_TEXT_DISABLED;
        }
        return Colors.getColorTopStats3(isActive, this.getIsHovered());
    }
    
    static {
        ButtonGame_Style.lTimeAnimation = 0L;
        ButtonGame_Style.animationState = 0;
        colorLine = new Color(0.5176471f, 0.43529412f, 0.25882354f, 0.55f);
    }
}
