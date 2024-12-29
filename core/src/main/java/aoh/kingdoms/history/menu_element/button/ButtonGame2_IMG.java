// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.Color;

public class ButtonGame2_IMG extends Button
{
    protected static long lTimeAnimation;
    protected static int animationState;
    protected static final int ANIMATION_T = 1000;
    public int iCurrent;
    public int imgID;
    public static Color colorGradient;
    public static Color colorGradientHover;
    private static final Color colorLine;
    
    public ButtonGame2_IMG(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int nWidth, final boolean isClickable, final int imgID) {
        this.iCurrent = 0;
        this.init(sText, fontID, iTextPositionX, iPosX, iPosY, nWidth, CFG.BUTTON_HEIGHT, isClickable, true, false, false);
        this.imgID = imgID;
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if ((isActive || this.getIsHovered()) && this.getClickable()) {
            Renderer.drawBox(oSB, this.getButtonBG_Active(), this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
            oSB.setColor(ButtonGame2_IMG.colorGradientHover);
        }
        else {
            Renderer.drawBox(oSB, this.getButtonBG(), this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
            oSB.setColor(ButtonGame2_IMG.colorGradient);
        }
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 2 + iTranslateY, this.getWidth(), this.getHeight() - 4);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.35f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 3 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
        if (this.getClickable() && this.getIsHovered() && ButtonGame2_IMG.animationState >= 0) {
            if (ButtonGame2_IMG.animationState == 0) {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - ButtonGame2_IMG.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + 1 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (ButtonGame2_IMG.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    ++ButtonGame2_IMG.animationState;
                    ButtonGame2_IMG.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            else {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - ButtonGame2_IMG.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (ButtonGame2_IMG.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    ButtonGame2_IMG.animationState = 0;
                    ButtonGame2_IMG.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            oSB.setColor(Color.WHITE);
        }
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        ImageManager.getImage(this.imgID).draw(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(this.imgID).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(this.imgID).getHeight() / 2 + iTranslateY);
    }
    
    public int getButtonBG() {
        return Images.buttonGame;
    }
    
    public int getButtonBG_Active() {
        return Images.buttonGameH;
    }
    
    protected static final Color getColorLine() {
        return ButtonGame2_IMG.colorLine;
    }
    
    @Override
    public void setIsHovered(final boolean isHovered) {
        super.setIsHovered(isHovered);
        ButtonGame2_IMG.lTimeAnimation = CFG.currentTimeMillis;
        ButtonGame2_IMG.animationState = 0;
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
        ButtonGame2_IMG.lTimeAnimation = 0L;
        ButtonGame2_IMG.animationState = 0;
        ButtonGame2_IMG.colorGradient = new Color(0.09803922f, 0.15686275f, 0.23529412f, 0.4f);
        ButtonGame2_IMG.colorGradientHover = new Color(0.19607843f, 0.13725491f, 0.11764706f, 0.75f);
        colorLine = new Color(0.5176471f, 0.43529412f, 0.25882354f, 0.55f);
    }
}
