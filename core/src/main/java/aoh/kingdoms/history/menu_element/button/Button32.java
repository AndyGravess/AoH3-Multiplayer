// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import com.badlogic.gdx.graphics.Color;

public class Button32 extends Button
{
    public int iconImageID;
    protected static long lTimeAnimation;
    protected static int animationState;
    protected static final int ANIMATION_T = 750;
    private static final Color colorLine;
    public static final Color buttonColor;
    
    public Button32(final int iconImageID) {
        this.iconImageID = iconImageID;
        this.init(this.sText, this.fontID, this.iTextPositionX, 0, 0, ImageManager.getImage(iconImageID).getWidth(), ImageManager.getImage(iconImageID).getHeight(), true, true, false, false);
    }
    
    public Button32(final int iconImageID, final int nPosX, final int nPosY) {
        this.iconImageID = iconImageID;
        this.init(this.sText, this.fontID, this.iTextPositionX, nPosX, nPosY, ImageManager.getImage(iconImageID).getWidth(), ImageManager.getImage(iconImageID).getHeight(), true, true, false, false);
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.25f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        if ((isActive || this.getIsHovered()) && this.getClickable()) {
            oSB.setColor(Button32.COLOR_BUTTON_MENU_HOVER_BG);
            if (Button32.animationState >= 0) {
                if (Button32.animationState == 0) {
                    final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - Button32.lTimeAnimation) / 750.0f, 1.0f);
                    oSB.setColor(getColorLine());
                    Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + 1 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                    Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                    if (Button32.lTimeAnimation < CFG.currentTimeMillis - 750L) {
                        ++Button32.animationState;
                        Button32.lTimeAnimation = CFG.currentTimeMillis;
                    }
                }
                else {
                    final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - Button32.lTimeAnimation) / 750.0f, 1.0f);
                    oSB.setColor(getColorLine());
                    Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                    Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                    if (Button32.lTimeAnimation < CFG.currentTimeMillis - 750L) {
                        Button32.animationState = 0;
                        Button32.lTimeAnimation = CFG.currentTimeMillis;
                    }
                }
                oSB.setColor(Color.WHITE);
            }
        }
        oSB.setColor(this.getColor(isActive));
        ImageManager.getImage(this.iconImageID).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, false, this.getFlipY());
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
        Button32.lTimeAnimation = CFG.currentTimeMillis;
        Button32.animationState = 0;
    }
    
    protected static final Color getColorLine() {
        return Button32.colorLine;
    }
    
    @Override
    public int getWidth() {
        return ImageManager.getImage(this.iconImageID).getWidth();
    }
    
    @Override
    public int getHeight() {
        return ImageManager.getImage(this.iconImageID).getHeight();
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        if (isActive) {
            return Colors.COLOR_TOP_STATS_ACTIVE;
        }
        if (this.getIsHovered()) {
            return Colors.COLOR_TOP_STATS_HOVER;
        }
        return this.isActiveView() ? Colors.COLOR_TOP_STATS : Button32.buttonColor;
    }
    
    public boolean isActiveView() {
        return false;
    }
    
    public boolean getFlipY() {
        return false;
    }
    
    static {
        Button32.lTimeAnimation = 0L;
        Button32.animationState = 0;
        colorLine = new Color(0.5176471f, 0.43529412f, 0.25882354f, 0.55f);
        buttonColor = new Color(0.627451f, 0.50980395f, 0.29411766f, 1.0f);
    }
}
