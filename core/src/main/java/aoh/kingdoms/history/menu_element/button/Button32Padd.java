// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.mainGame.SoundsManager;
import aoh.kingdoms.history.menusInGame.InGame_MapModes;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.ImageManager;
import com.badlogic.gdx.graphics.Color;

public class Button32Padd extends Button
{
    protected static long lTimeAnimation;
    protected static int animationState;
    protected static final int ANIMATION_T = 1000;
    public int iconImageID;
    public int imgX;
    public int imgY;
    public int imgWidth;
    public int imgHeight;
    private static final Color colorLine;
    public static final Color buttonColor;
    public static final Color buttonColor2;
    
    public Button32Padd(final int iconImageID) {
        this.iconImageID = iconImageID;
        this.init(this.sText, this.fontID, 0, 0, 0, ImageManager.getImage(iconImageID).getWidth() + CFG.PADDING * 2, ImageManager.getImage(iconImageID).getHeight() + CFG.PADDING * 2, true, true, false, false);
        this.updateImgWH();
    }
    
    public Button32Padd(final int iconImageID, final int nPosX, final int nPosY) {
        this.iconImageID = iconImageID;
        this.init(this.sText, this.fontID, 0, nPosX, nPosY, ImageManager.getImage(iconImageID).getWidth() + CFG.PADDING * 2, ImageManager.getImage(iconImageID).getHeight() + CFG.PADDING * 2, true, true, false, false);
        this.updateImgWH();
    }
    
    public Button32Padd(final int iconImageID, final int nPosX, final int nPosY, final int nH) {
        this.iconImageID = iconImageID;
        this.init(this.sText, this.fontID, 0, nPosX, nPosY, ImageManager.getImage(iconImageID).getWidth() + CFG.PADDING * 2, nH, true, true, false, false);
        this.updateImgWH();
    }
    
    public void updateImgWH() {
        this.imgWidth = (int)(ImageManager.getImage(this.iconImageID).getWidth() * this.imgScale());
        this.imgHeight = (int)(ImageManager.getImage(this.iconImageID).getHeight() * this.imgScale());
        this.imgX = this.getWidth() / 2 - this.imgWidth / 2;
        this.imgY = (ImageManager.getImage(this.iconImageID).getHeight() - this.imgHeight) / 2 + CFG.PADDING;
    }
    
    public float imgScale() {
        return 0.85f;
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, (this.getIsHovered() || isActive) ? 0.6f : 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        if ((isActive || this.getIsHovered()) && this.getClickable()) {
            oSB.setColor(Button32Padd.COLOR_BUTTON_MENU_HOVER_BG);
            if (this.getIsHovered() || isActive) {
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 1.0f));
                Renderer.drawBox(oSB, Images.statsRectBGBorder, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
                oSB.setColor(Color.WHITE);
            }
        }
        if (this.getClickable() && this.getIsHovered() && Button32Padd.animationState >= 0) {
            if (Button32Padd.animationState == 0) {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - Button32Padd.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(ButtonGame.getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + 1 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (Button32Padd.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    ++Button32Padd.animationState;
                    Button32Padd.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            else {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - Button32Padd.lTimeAnimation) / 1000.0f, 1.0f);
                oSB.setColor(ButtonGame.getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (Button32Padd.lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    Button32Padd.animationState = 0;
                    Button32Padd.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            oSB.setColor(Color.WHITE);
        }
        oSB.setColor(this.getColor(isActive));
        ImageManager.getImage(this.iconImageID).draw(oSB, this.getPosX() + this.imgX + iTranslateX, this.getPosY() + this.imgY + iTranslateY, this.imgWidth, this.imgHeight, false, this.getFlipY());
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
    }
    
    protected static final Color getColorLine() {
        return Button32Padd.colorLine;
    }
    
    @Override
    public int getWidth() {
        return InGame_MapModes.maxWidth + CFG.PADDING * 2;
    }
    
    @Override
    public int getHeight() {
        return ImageManager.getImage(this.iconImageID).getHeight() + CFG.PADDING * 2;
    }
    
    public static int getButtonHeight() {
        return ImageManager.getImage(Images.mapModesCivs).getHeight() + CFG.PADDING * 2;
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        if (isActive) {
            return Colors.COLOR_TOP_STATS_ACTIVE;
        }
        if (this.getIsHovered()) {
            return Colors.COLOR_TOP_STATS_HOVER;
        }
        return this.isActiveView() ? Colors.COLOR_TOP_STATS : Button32Padd.buttonColor2;
    }
    
    public boolean isActiveView() {
        return false;
    }
    
    public boolean getFlipY() {
        return false;
    }
    
    @Override
    public void setIsHovered(final boolean isHovered) {
        super.setIsHovered(isHovered);
        Button32Padd.lTimeAnimation = CFG.currentTimeMillis;
        Button32Padd.animationState = 0;
    }
    
    @Override
    public int getSFX() {
        return this.isActiveView() ? SoundsManager.MAP_MODE1 : SoundsManager.MAP_MODE0;
    }
    
    static {
        Button32Padd.lTimeAnimation = 0L;
        Button32Padd.animationState = 0;
        colorLine = new Color(0.5176471f, 0.43529412f, 0.25882354f, 0.55f);
        buttonColor = new Color(0.627451f, 0.50980395f, 0.29411766f, 1.0f);
        buttonColor2 = new Color(0.627451f, 0.50980395f, 0.29411766f, 0.75f);
    }
}
