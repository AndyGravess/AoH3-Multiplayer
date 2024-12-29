// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.Color;

public class ButtonMain extends Button
{
    protected static long lTimeAnimation;
    protected static int animationState;
    protected static final int ANIMATION_T = 750;
    private static final Color colorLine;
    
    public ButtonMain(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int nWidth, final boolean isClickable) {
        this.init(sText, fontID, iTextPositionX, iPosX, iPosY, nWidth, CFG.BUTTON_HEIGHT, isClickable, true, false, false);
    }
    
    public ButtonMain(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int nWidth, final boolean isClickable, final boolean checkBox) {
        this.init(sText, fontID, iTextPositionX, iPosX, iPosY, nWidth, CFG.BUTTON_HEIGHT, isClickable, true, true, checkBox);
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if ((isActive || this.getIsHovered()) && this.getClickable()) {
            oSB.setColor(ButtonMain.COLOR_BUTTON_MENU_HOVER_BG);
            Renderer.drawBox(oSB, this.getButtonBG_Active(), this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.15f));
            oSB.setColor(Color.WHITE);
            if (ButtonMain.animationState >= 0) {
                if (ButtonMain.animationState == 0) {
                    final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - ButtonMain.lTimeAnimation) / 750.0f, 1.0f);
                    oSB.setColor(getColorLine());
                    Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + 1 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                    Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                    if (ButtonMain.lTimeAnimation < CFG.currentTimeMillis - 750L) {
                        ++ButtonMain.animationState;
                        ButtonMain.lTimeAnimation = CFG.currentTimeMillis;
                    }
                }
                else {
                    final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - ButtonMain.lTimeAnimation) / 750.0f, 1.0f);
                    oSB.setColor(getColorLine());
                    Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                    Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                    if (ButtonMain.lTimeAnimation < CFG.currentTimeMillis - 750L) {
                        ButtonMain.animationState = 0;
                        ButtonMain.lTimeAnimation = CFG.currentTimeMillis;
                    }
                }
                oSB.setColor(Color.WHITE);
            }
        }
        else {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.9f));
            Renderer.drawBox(oSB, this.getButtonBG(), this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
            oSB.setColor(Color.WHITE);
        }
        oSB.setColor(Color.WHITE);
    }
    
    public int getButtonBG() {
        return Images.buttonMenu;
    }
    
    public int getButtonBG_Active() {
        return Images.buttonMenuH;
    }
    
    @Override
    public void setIsHovered(final boolean isHovered) {
        super.setIsHovered(isHovered);
        ButtonMain.lTimeAnimation = CFG.currentTimeMillis;
        ButtonMain.animationState = 0;
    }
    
    protected static final Color getColorLine() {
        return ButtonMain.colorLine;
    }
    
    static {
        ButtonMain.lTimeAnimation = 0L;
        ButtonMain.animationState = 0;
        colorLine = new Color(0.5176471f, 0.43529412f, 0.25882354f, 0.55f);
    }
}
