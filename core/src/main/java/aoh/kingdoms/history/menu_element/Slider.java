// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.map.province.ProvinceDraw;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;

public class Slider extends MenuElement
{
    public int iMin;
    public int iMax;
    public int iCurrentPosX;
    public String sText;
    private int iCurrent;
    private int iTextWidth;
    private int iTextHeight;
    private long lTime;
    public int iDifference_CurrentPosX;
    private int iDifference_PosX;
    
    public Slider() {
        this.iCurrentPosX = -1;
        this.sText = null;
        this.iTextWidth = -1;
        this.iTextHeight = -1;
        this.lTime = 0L;
        this.iDifference_CurrentPosX = 0;
        this.iDifference_PosX = 0;
    }
    
    public Slider(final int iPosX, final int iPosY, final int iWidth, final int iHeight, final int iMin, final int iMax, final int iCurrent) {
        this.iCurrentPosX = -1;
        this.sText = null;
        this.iTextWidth = -1;
        this.iTextHeight = -1;
        this.lTime = 0L;
        this.iDifference_CurrentPosX = 0;
        this.iDifference_PosX = 0;
        this.initSlider("", iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
    }
    
    public Slider(final String sText, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final int iMin, final int iMax, final int iCurrent) {
        this.iCurrentPosX = -1;
        this.sText = null;
        this.iTextWidth = -1;
        this.iTextHeight = -1;
        this.lTime = 0L;
        this.iDifference_CurrentPosX = 0;
        this.iDifference_PosX = 0;
        this.initSlider(sText, iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
    }
    
    public void initSlider(final String sText, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final int iMin, final int iMax, final int iCurrent) {
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidth(iWidth);
        this.setHeight(iHeight);
        this.fontID = CFG.FONT_REGULAR_SMALL;
        this.sText = sText;
        this.iMin = iMin;
        this.iMax = iMax;
        this.iCurrent = iCurrent;
        this.updateSlider(-1);
        this.typeOfElement = MenuElement_Type.SLIDER;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        this.drawSliderBG(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
        this.drawSliderText(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
        this.drawSliderBorder(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
        oSB.setColor(Color.WHITE);
    }
    
    public final void drawSliderBG_UpdateAnimation() {
        if (this.iDifference_CurrentPosX != 0) {
            if (this.lTime == 0L) {
                this.lTime = CFG.currentTimeMillis;
            }
            this.iDifference_CurrentPosX = this.iDifference_PosX - (int)(this.iDifference_PosX * ((CFG.currentTimeMillis - this.lTime) / 375.0f));
            if (CFG.currentTimeMillis >= this.lTime + 375L) {
                this.iDifference_CurrentPosX = 0;
            }
        }
    }
    
    public void drawSliderBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        this.drawSliderBG_UpdateAnimation();
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(new Color(ProvinceDraw.progressBar.r, ProvinceDraw.progressBar.g, ProvinceDraw.progressBar.b, 0.5f));
        Renderer.drawBoxProgress(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.iCurrentPosX + this.iDifference_CurrentPosX, this.getHeight(), this.getWidth());
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.2f));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(Color.WHITE);
    }
    
    public Color getColorLEFT() {
        return Colors.COLOR_SLIDER_LEFT_BG;
    }
    
    public Color getColorRIGHT() {
        return Colors.COLOR_SLIDER_RIGHT_BG;
    }
    
    public void drawSliderText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getDrawText(), this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, new Color(0.945f, 0.945f, 0.945f, 1.0f));
    }
    
    public void drawSliderBorder(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
    }
    
    public String getDrawText() {
        return this.sText + this.iCurrent;
    }
    
    @Override
    public void updateSlider(int nX) {
        if (nX >= 0) {
            nX -= this.getPosX();
            this.iCurrent = (int)(nX * 100.0f / this.getWidth() * (this.iMax - this.iMin) / 100.0f + this.iMin);
        }
        if (this.iCurrent < this.iMin) {
            this.iCurrent = this.iMin;
        }
        else if (this.iCurrent > this.iMax) {
            this.iCurrent = this.iMax;
        }
        this.updateCurrentPosX();
        this.updateTextWidth();
        this.iDifference_CurrentPosX = 0;
        this.iDifference_PosX = 0;
    }
    
    private final void updateCurrentPosX() {
        this.iCurrentPosX = (int)((this.iCurrent - this.iMin) * 100.0f / (this.iMax - this.iMin) * this.getWidth() / 100.0f);
    }
    
    public final void updateTextWidth() {
        Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), this.getDrawText());
        this.iTextWidth = (int)Renderer.glyphLayout.width;
        this.iTextHeight = (int)Renderer.glyphLayout.height;
    }
    
    @Override
    public final String getText() {
        return this.sText;
    }
    
    @Override
    public void setText(final String sText) {
        this.sText = sText;
        this.updateTextWidth();
    }
    
    @Override
    public void setCurrent(final int nCurrent) {
        final int tempCurr = this.iCurrentPosX;
        if (nCurrent > this.iMax) {
            this.iCurrent = this.iMax;
        }
        else if (nCurrent < this.iMin) {
            this.iCurrent = this.iMin;
        }
        else {
            this.iCurrent = nCurrent;
        }
        this.updateCurrentPosX();
        this.updateTextWidth();
        if (tempCurr != this.iCurrentPosX) {
            this.lTime = 0L;
            this.iDifference_CurrentPosX = tempCurr - this.iCurrentPosX;
            this.iDifference_PosX = this.iDifference_CurrentPosX;
        }
    }
    
    @Override
    public final int getCurrent() {
        return this.iCurrent;
    }
    
    @Override
    public int getTextWidth() {
        return this.iTextWidth;
    }
    
    @Override
    public final int getTextHeight() {
        return this.iTextHeight;
    }
    
    @Override
    public void setMin(final int iMin) {
        this.iMin = iMin;
        if (this.iCurrent < iMin) {
            this.iCurrent = iMin;
            this.updateTextWidth();
        }
    }
    
    @Override
    public void setMax(final int iMax) {
        this.iMax = iMax;
        if (this.iCurrent > iMax) {
            this.iCurrent = iMax;
            this.updateTextWidth();
        }
    }
    
    @Override
    public int getTextPos() {
        return this.iMax;
    }
    
    @Override
    public void scrollByWheel(final int nScoll) {
        this.setCurrent(this.getCurrent() + nScoll);
        this.actionElement();
    }
    
    @Override
    public boolean getScrollable() {
        return true;
    }
}
