// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.mainGame.Game;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.map.diplomacy.DiplomacyManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.button.Button;

public class TextIcon_Siege extends Button
{
    public int imageID;
    public int maxWidth;
    public long lTimeAnimation;
    public int animationState;
    public final int ANIMATION_T = 1500;
    public float lastValue;
    public String sText2;
    public int iTextWidth2;
    public String sText3;
    public int iTextWidth3;
    public int iTextHeight3;
    private int iSiegeWidth;
    private int iSiegeHeight;
    private int iFortWidth;
    private int iFortHeight;
    public int iProvinceID;
    
    public TextIcon_Siege(final String sText2, final int iProvinceID, final int imageID, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final int maxWidth) {
        this.lTimeAnimation = 0L;
        this.animationState = 0;
        this.lastValue = -989566.75f;
        this.sText3 = "";
        this.init(null, CFG.FONT_REGULAR, 0, nPosX, nPosY, nWidth, nHeight, true, true, false, false);
        this.iProvinceID = iProvinceID;
        this.imageID = imageID;
        this.maxWidth = maxWidth;
        this.lTimeAnimation = CFG.currentTimeMillis;
        this.sText2 = sText2;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), sText2);
        this.iTextWidth2 = (int)Renderer.glyphLayout.width;
        this.iSiegeWidth = (int)(ImageManager.getImage(Images.siege).getWidth() * this.getImageScale(Images.siege));
        this.iSiegeHeight = (int)(ImageManager.getImage(Images.siege).getHeight() * this.getImageScale(Images.siege));
        this.iFortWidth = (int)(ImageManager.getImage(Game_Calendar.IMG_FORT_DEFENSE).getWidth() * this.getImageScale(Game_Calendar.IMG_FORT_DEFENSE));
        this.iFortHeight = (int)(ImageManager.getImage(Game_Calendar.IMG_FORT_DEFENSE).getHeight() * this.getImageScale(Game_Calendar.IMG_FORT_DEFENSE));
        this.getTextToDraw();
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        int tX = this.getPosX();
        int tWidth = this.getWidth();
        if (DiplomacyManager.inAnimation) {
            tWidth = (int)(this.getWidth() * DiplomacyManager.fAnimationPerc);
            tX = tX + this.getWidth() / 2 - tWidth / 2;
        }
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.25f));
        Renderer.drawBox(oSB, Images.statsRectBG, tX + iTranslateX, this.getPosY() + iTranslateY, tWidth, this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, (this.getIsHovered() || isActive) ? 0.65f : 0.25f));
        Renderer.drawBox(oSB, Images.statsRectBG, tX + iTranslateX, this.getPosY() + iTranslateY, tWidth, this.getHeight(), 1.0f);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.2f));
        Images.gradientFull.draw(oSB, tX + iTranslateX, this.getPosY() + iTranslateY, tWidth, this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.25f));
        Images.gradientXY.draw(oSB, tX + iTranslateX, this.getPosY() + iTranslateY, tWidth, this.getHeight());
        final int progressWidth = (int)Math.max(1.0f, tWidth * Game.getProvince(this.iProvinceID).getSiegeProgress());
        oSB.setColor(Color.WHITE);
        if (DiplomacyManager.inAnimation) {
            oSB.setColor(new Color(this.getColorBar().r, this.getColorBar().g, this.getColorBar().b, 0.65f * (1.0f - DiplomacyManager.fAnimationPerc)));
            Images.gradientFull.draw(oSB, tX + iTranslateX, this.getPosY() + iTranslateY, progressWidth, this.getHeight());
            oSB.setColor(new Color(this.getColorBar().r, this.getColorBar().g, this.getColorBar().b, 0.45f + 0.6f * (1.0f - DiplomacyManager.fAnimationPerc)));
        }
        else {
            oSB.setColor(new Color(this.getColorBar().r, this.getColorBar().g, this.getColorBar().b, 0.45f));
        }
        Images.gradientXY.draw(oSB, tX + iTranslateX, this.getPosY() + iTranslateY, progressWidth, this.getHeight());
        if (this.animationState >= 0) {
            if (this.animationState == 0) {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - this.lTimeAnimation) / 1500.0f, 1.0f);
                oSB.setColor(new Color(this.getColorBar().r, this.getColorBar().g, this.getColorBar().b, 0.55f * (1.0f - drawPerc)));
                Images.gradientXY.draw(oSB, tX + iTranslateX, this.getPosY() + this.getHeight() / 2 + iTranslateY, progressWidth, this.getHeight() / 2);
                if (this.lTimeAnimation < CFG.currentTimeMillis - 1500L) {
                    ++this.animationState;
                    this.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            else {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - this.lTimeAnimation) / 1500.0f, 1.0f);
                oSB.setColor(new Color(this.getColorBar().r, this.getColorBar().g, this.getColorBar().b, 0.55f * drawPerc));
                Images.gradientXY.draw(oSB, tX + iTranslateX, this.getPosY() + this.getHeight() / 2 + iTranslateY, progressWidth, this.getHeight() / 2);
                if (this.lTimeAnimation < CFG.currentTimeMillis - 1500L) {
                    this.animationState = 0;
                    this.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            oSB.setColor(Color.WHITE);
        }
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
        Images.gradientXY.draw(oSB, tX + iTranslateX, this.getPosY() + iTranslateY, tWidth, CFG.PADDING * 2, false, true);
        Images.gradientXY.draw(oSB, tX + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING * 2 + iTranslateY, tWidth, CFG.PADDING * 2);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
        Renderer.drawBox(oSB, Images.statsRectBGBorder, tX + iTranslateX, this.getPosY() + iTranslateY, tWidth, this.getHeight(), 1.0f);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        Images.gradientFull.draw(oSB, tX + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, tWidth, 1);
        Images.gradientFull.draw(oSB, tX + iTranslateX, this.getPosY() + iTranslateY, tWidth, 1);
        oSB.setColor(Color.WHITE);
    }
    
    public Color getColorBar() {
        return DiplomacyManager.COLOR_RED;
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        ImageManager.getImage(this.imageID).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.iTextWidth3 / 2 - CFG.PADDING - this.iSiegeWidth + iTranslateX, this.getPosY() + this.getHeight() / 2 - (this.iTextHeight + this.iTextHeight3 + CFG.PADDING) / 2 + CFG.PADDING + this.iTextHeight + iTranslateY, this.iSiegeWidth, this.iSiegeHeight);
        ImageManager.getImage(Game_Calendar.IMG_FORT_DEFENSE).draw(oSB, this.getPosX() + this.getWidth() / 2 + this.iTextWidth3 / 2 + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 - (this.iTextHeight + this.iTextHeight3 + CFG.PADDING) / 2 + CFG.PADDING + this.iTextHeight + iTranslateY, this.iFortWidth, this.iFortHeight);
        Renderer.drawText(oSB, this.fontID, this.sText2, this.getPosX() + this.getWidth() / 2 - (this.iTextWidth + this.iTextWidth2) / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (this.iTextHeight + this.iTextHeight3 + CFG.PADDING) / 2 + iTranslateY, this.getColor(isActive));
        Renderer.drawText(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.getWidth() / 2 - (this.iTextWidth + this.iTextWidth2) / 2 + this.iTextWidth2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (this.iTextHeight + this.iTextHeight3 + CFG.PADDING) / 2 + iTranslateY, Colors.HOVER_GOLD);
        Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, this.sText3, this.getPosX() + this.getWidth() / 2 - this.iTextWidth3 / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - (this.iTextHeight + this.iTextHeight3 + CFG.PADDING) / 2 + CFG.PADDING + this.iTextHeight + iTranslateY, this.getColor(isActive));
    }
    
    @Override
    public String getTextToDraw() {
        if (this.iProvinceID >= 0 && Game.getProvinceData4(this.iProvinceID).getSiegeProgress() != this.lastValue) {
            this.lastValue = Game.getProvinceData4(this.iProvinceID).getSiegeProgress();
            this.setText("" + CFG.getPrecision2(Game.getProvince(this.iProvinceID).getSiegeProgress() * 100.0f, 1) + "%");
            this.sText3 = "" + CFG.getPrecision2(Game.getProvinceData4(this.iProvinceID).getSiegeProgress(), 1) + " / " + Game.getProvince(this.iProvinceID).getFortDefense();
            Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sText3);
            this.iTextWidth3 = (int)Renderer.glyphLayout.width;
            this.iTextHeight3 = (int)Renderer.glyphLayout.height;
        }
        return this.sText;
    }
    
    private final float getImageScale(final int iImageID) {
        return CFG.TEXT_HEIGHT_SMALL / (float)ImageManager.getImage(iImageID).getHeight();
    }
}
