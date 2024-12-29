// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.pieChart;

import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menu_element.MenuElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;

public class PieChart_WithStats extends PieChart
{
    protected float TEXT_SCALE;
    protected static final int ANIMATION_TIME = 300;
    protected long lTime;
    protected boolean isDescriptionActive;
    protected boolean hideAnimation;
    protected boolean scrollable;
    protected int statsPosY;
    protected int statsExtraWidth;
    public boolean enableHideStats;
    
    public PieChart_WithStats(final int iPosX, final int iPosY, final int iWidth, final int iHeight, final PieChart_Data nPieChartData, final MenuElement_Hover menuElementHover) {
        super(iPosX, iPosY, iWidth, iHeight, nPieChartData, menuElementHover);
        this.TEXT_SCALE = 0.72f;
        this.lTime = 0L;
        this.isDescriptionActive = true;
        this.hideAnimation = false;
        this.scrollable = false;
        this.statsPosY = 0;
        this.statsExtraWidth = 0;
        this.enableHideStats = false;
        this.initPieChartStats();
    }
    
    public PieChart_WithStats(final int iPosX, final int iPosY, final int iWidth, final int iHeight, final PieChart_Data nPieChartData, final MenuElement_Hover menuElementHover, final float textScale) {
        super(iPosX, iPosY, iWidth, iHeight, nPieChartData, menuElementHover);
        this.TEXT_SCALE = 0.72f;
        this.lTime = 0L;
        this.isDescriptionActive = true;
        this.hideAnimation = false;
        this.scrollable = false;
        this.statsPosY = 0;
        this.statsExtraWidth = 0;
        this.enableHideStats = false;
        this.TEXT_SCALE = textScale;
        this.initPieChartStats();
    }
    
    public void initPieChartStats() {
        this.typeOfElement = MenuElement_Type.PIECHART_WITH_STATS;
        this.updateScrollable_Y();
        try {
            float tempMaxWidth = 0.0f;
            for (int i = 0; i < this.pieChartData.getPieChartValuesSize(); ++i) {
                Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), "" + CFG.getPercentage(this.pieChartData.getPieChartValue(i).getPercentage(), 5) + "%");
                if (tempMaxWidth < Renderer.glyphLayout.width) {
                    tempMaxWidth = Renderer.glyphLayout.width;
                }
            }
            this.statsExtraWidth = (int)(tempMaxWidth * this.TEXT_SCALE) + CFG.PADDING * 4;
        }
        catch (final IndexOutOfBoundsException ex) {
            this.statsExtraWidth = super.getWidth();
        }
        catch (final IllegalArgumentException ex2) {
            this.statsExtraWidth = super.getWidth();
        }
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        this.drawPieChart(oSB, iTranslateX, iTranslateY, isActive, scrollableY, this.getPosX(), this.getPosY(), this.getWidth_StatsExtraWidth(super.getWidth()), this.getHeight_StatsExtraHeight(), this.iPieChartWidth);
    }
    
    @Override
    protected void drawPieChart(final SpriteBatch oSB, final int iTranslateX, int iTranslateY, final boolean isActive, final boolean scrollableY, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final int nWidth_LEFT) {
        Renderer.pieChartRenderer.draw(oSB, this.getPieChartBackground(), nPosX + iTranslateX, nPosY + iTranslateY, this.iPieChartWidth, this.iPieChartHeight, this.pieChartData, isActive || this.getIsHovered(), this.animationPerc());
        if (this.isDescriptionActive || this.hideAnimation) {
            if (!Renderer.clipView_Start(oSB, nPosX + iTranslateX, CFG.GAME_HEIGHT - nPosY - iTranslateY, nWidth, -nHeight)) {
                return;
            }
            Renderer.setFontScale(this.TEXT_SCALE);
            iTranslateY += this.statsPosY;
            for (int i = 0; i < this.pieChartData.getPieChartValuesSize(); ++i) {
                try {
                    oSB.setColor(new Color(this.pieChartData.getPieChartValue_ColorR(i), this.pieChartData.getPieChartValue_ColorG(i), this.pieChartData.getPieChartValue_ColorB(i), 0.725f));
                    Images.pix.draw(oSB, nPosX + nWidth_LEFT + CFG.PADDING + iTranslateX, nPosY + (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) * i + CFG.PADDING * i + iTranslateY, CFG.COLOR_WIDTH, (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2));
                    oSB.setColor(new Color(this.pieChartData.getPieChartValue_ColorR(i), this.pieChartData.getPieChartValue_ColorG(i), this.pieChartData.getPieChartValue_ColorB(i), 0.425f));
                    ImageManager.getImage(Images.gradientHorizontal).draw(oSB, nPosX + nWidth_LEFT + CFG.PADDING + iTranslateX, nPosY + (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) * i + CFG.PADDING * i + iTranslateY, CFG.COLOR_WIDTH + nWidth - nWidth_LEFT, (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2));
                    oSB.setColor(new Color(this.pieChartData.getPieChartValue_ColorR(i), this.pieChartData.getPieChartValue_ColorG(i), this.pieChartData.getPieChartValue_ColorB(i), 0.3f));
                    ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX + nWidth_LEFT + CFG.PADDING + iTranslateX, nPosY + (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) * i + CFG.PADDING * i + iTranslateY, CFG.COLOR_WIDTH + nWidth - nWidth_LEFT, (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) / 4);
                    ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX + nWidth_LEFT + CFG.PADDING + iTranslateX, nPosY + (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) * i + CFG.PADDING * i + iTranslateY + (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) - (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) / 4, CFG.COLOR_WIDTH + nWidth - nWidth_LEFT, (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) / 4, false, true);
                    oSB.setColor(Color.WHITE);
                }
                catch (final IndexOutOfBoundsException ex) {
                    oSB.setColor(new Color(Colors.COLOR_UNKNOWN.r, Colors.COLOR_UNKNOWN.g, Colors.COLOR_UNKNOWN.b, 0.725f));
                    Images.pix.draw(oSB, nPosX + nWidth_LEFT + CFG.PADDING + iTranslateX, nPosY + (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) * i + CFG.PADDING * i + iTranslateY, CFG.COLOR_WIDTH, (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2));
                    oSB.setColor(new Color(Colors.COLOR_UNKNOWN.r, Colors.COLOR_UNKNOWN.g, Colors.COLOR_UNKNOWN.b, 0.45f));
                    ImageManager.getImage(Images.gradientHorizontal).draw(oSB, nPosX + nWidth_LEFT + CFG.PADDING + iTranslateX, nPosY + (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) * i + CFG.PADDING * i + iTranslateY, CFG.COLOR_WIDTH + nWidth - nWidth_LEFT, (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2));
                    oSB.setColor(new Color(Colors.COLOR_UNKNOWN.r, Colors.COLOR_UNKNOWN.g, Colors.COLOR_UNKNOWN.b, 0.2f));
                    ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX + nWidth_LEFT + CFG.PADDING + iTranslateX, nPosY + (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) * i + CFG.PADDING * i + iTranslateY, CFG.COLOR_WIDTH + nWidth - nWidth_LEFT, (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) / 4);
                    ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX + nWidth_LEFT + CFG.PADDING + iTranslateX, (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) - (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) / 4 + nPosY + (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) * i + CFG.PADDING * i + iTranslateY, CFG.COLOR_WIDTH + nWidth - nWidth_LEFT, (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) / 4, false, true);
                    oSB.setColor(Color.WHITE);
                }
                Renderer.drawTextWithShadow(oSB, this.fontID, "" + CFG.getPercentage(this.pieChartData.getPieChartValue(i).getPercentage(), 5) + "%", nPosX + nWidth_LEFT + CFG.PADDING + CFG.PADDING + CFG.COLOR_WIDTH + iTranslateX, nPosY + (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) * i + CFG.PADDING * i + (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) / 2 - (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE / 2.0f) + iTranslateY, Colors.COLOR_TEXT_PIE_CHART_STATS);
            }
            iTranslateY -= this.statsPosY;
            Renderer.resetFontScale();
            Renderer.clipView_End(oSB);
        }
        PieChart_Renderer.pieChart_Frame.draw(oSB, nPosX + iTranslateX, nPosY + iTranslateY, this.iPieChartWidth, this.iPieChartHeight);
    }
    
    protected final void updateScrollable_Y() {
        if (this.getMaxHeight() > this.getHeight_StatsExtraHeight()) {
            this.scrollable = true;
        }
        else {
            this.scrollable = false;
            this.statsPosY = 0;
        }
    }
    
    protected final int getMaxHeight() {
        return (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) * this.pieChartData.getPieChartValuesSize() + CFG.PADDING * (this.pieChartData.getPieChartValuesSize() - 1);
    }
    
    @Override
    public int getScrollPosY() {
        return this.statsPosY;
    }
    
    @Override
    public void setScrollPosY(int nStatsPosY) {
        if (nStatsPosY > 0) {
            nStatsPosY = 0;
            Game.menuManager.setUpdateSliderMenuPosY(true);
        }
        else if (nStatsPosY < -this.getMaxHeight() + this.getHeight_StatsExtraHeight()) {
            nStatsPosY = -this.getMaxHeight() + this.getHeight_StatsExtraHeight();
            Game.menuManager.setUpdateSliderMenuPosY(true);
        }
        if (this.statsPosY != nStatsPosY) {
            this.statsPosY = nStatsPosY;
        }
    }
    
    protected int getHeight_StatsExtraHeight() {
        return this.iPieChartHeight;
    }
    
    @Override
    public int getWidth() {
        return this.getWidth_StatsExtraWidth(super.getWidth());
    }
    
    protected int getWidth_StatsExtraWidth(final int nWidth) {
        if (this.isDescriptionActive) {
            if (this.lTime + 300L >= CFG.currentTimeMillis) {
                return nWidth + (int)(this.statsExtraWidth * ((CFG.currentTimeMillis - this.lTime) / 300.0f));
            }
            return nWidth + this.statsExtraWidth;
        }
        else {
            if (!this.hideAnimation) {
                return nWidth;
            }
            if (this.lTime + 300L >= CFG.currentTimeMillis) {
                return nWidth + this.statsExtraWidth - (int)(this.statsExtraWidth * ((CFG.currentTimeMillis - this.lTime) / 300.0f));
            }
            this.hideAnimation = false;
            return nWidth;
        }
    }
    
    @Override
    public boolean getScrollable() {
        return this.scrollable;
    }
    
    @Override
    public void setScrollable(final boolean scrollable) {
        this.scrollable = scrollable;
    }
    
    @Override
    public boolean getDescription() {
        return this.isDescriptionActive;
    }
    
    @Override
    public void setDescription(final boolean isDescriptionActive) {
        if (this.enableHideStats) {
            this.isDescriptionActive = isDescriptionActive;
            if (this.getVisible()) {
                this.hideAnimation = !this.isDescriptionActive;
                this.lTime = CFG.currentTimeMillis;
            }
            else {
                this.lTime = 0L;
            }
            this.animationTimer = CFG.currentTimeMillis - 37L;
        }
    }
    
    @Override
    public void scrollByWheel(final int nScoll) {
        this.setScrollPosY(this.getScrollPosY() + nScoll);
    }
}
