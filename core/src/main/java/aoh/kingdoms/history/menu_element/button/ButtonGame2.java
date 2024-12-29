// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.Color;

public class ButtonGame2 extends Button
{
    protected static long lTimeAnimation;
    protected static int animationState;
    protected static final int ANIMATION_T = 1000;
    public int iCurrent;
    public static Color colorGradient;
    public static Color colorGradientHover;
    private static final Color colorLine;
    
    public ButtonGame2(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int nWidth, final boolean isClickable) {
        this.iCurrent = 0;
        this.init(sText, fontID, iTextPositionX, iPosX, iPosY, nWidth, CFG.BUTTON_HEIGHT, isClickable, true, false, false);
    }
    
    public ButtonGame2(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int nWidth, final boolean isClickable, final int nHeight) {
        this.iCurrent = 0;
        this.init(sText, fontID, iTextPositionX, iPosX, iPosY, nWidth, nHeight, isClickable, true, false, false);
    }
    
    public ButtonGame2(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int nWidth, final boolean isClickable, final int nHeight, final boolean checkBox) {
        this.iCurrent = 0;
        this.init(sText, fontID, iTextPositionX, iPosX, iPosY, nWidth, nHeight, isClickable, true, true, checkBox);
    }
    
    public ButtonGame2(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int nWidth, final boolean isClickable, final boolean checkBox) {
        this.iCurrent = 0;
        this.init(sText, fontID, iTextPositionX, iPosX, iPosY, nWidth, CFG.BUTTON_HEIGHT, isClickable, true, true, checkBox);
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if ((isActive || this.getIsHovered()) && this.getClickable()) {
            Renderer.drawBox(oSB, this.getButtonBG_Active(), this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
            oSB.setColor(ButtonGame2.colorGradientHover);
        }
        else {
            Renderer.drawBox(oSB, this.getButtonBG(), this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
            oSB.setColor(ButtonGame2.colorGradient);
        }
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 2 + iTranslateY, this.getWidth(), this.getHeight() - 4);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.35f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 3 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
        if (this.getClickable() && this.getIsHovered() && ButtonGame2.animationState >= 0) {
            if (ButtonGame2.animationState == 0) {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - ButtonGame2.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + 1 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (ButtonGame2.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    ++ButtonGame2.animationState;
                    ButtonGame2.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            else {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - ButtonGame2.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (ButtonGame2.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    ButtonGame2.animationState = 0;
                    ButtonGame2.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            oSB.setColor(Color.WHITE);
        }
    }
    
    public int getButtonBG() {
        return Images.buttonGame;
    }
    
    public int getButtonBG_Active() {
        return Images.buttonGameH;
    }
    
    protected static final Color getColorLine() {
        return ButtonGame2.colorLine;
    }
    
    @Override
    public void setIsHovered(final boolean isHovered) {
        super.setIsHovered(isHovered);
        ButtonGame2.lTimeAnimation = CFG.currentTimeMillis;
        ButtonGame2.animationState = 0;
    }
    
    @Override
    public void setCurrent(final int nCurrent) {
        this.iCurrent = nCurrent;
    }
    
    @Override
    public int getCurrent() {
        return this.iCurrent;
    }
    
    static {
        ButtonGame2.lTimeAnimation = 0L;
        ButtonGame2.animationState = 0;
        ButtonGame2.colorGradient = new Color(0.09803922f, 0.15686275f, 0.23529412f, 0.4f);
        ButtonGame2.colorGradientHover = new Color(0.19607843f, 0.13725491f, 0.11764706f, 0.75f);
        colorLine = new Color(0.5176471f, 0.43529412f, 0.25882354f, 0.55f);
    }
}
