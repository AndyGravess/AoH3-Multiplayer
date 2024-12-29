// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.pieChart;

import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menu_element.MenuElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;

public class PieChart_WithStatsReligion extends PieChart_WithStats
{
    public PieChart_WithStatsReligion(final int iPosX, final int iPosY, final int iWidth, final int iHeight, final PieChart_Data nPieChartData, final MenuElement_Hover menuElementHover) {
        super(iPosX, iPosY, iWidth, iHeight, nPieChartData, menuElementHover, 1.0f);
    }
    
    @Override
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
            this.statsExtraWidth = (int)(tempMaxWidth * this.TEXT_SCALE) + CFG.PADDING * 4 + (int)(this.getStatsImageWidth() * (CFG.TEXT_HEIGHT * this.TEXT_SCALE) / this.getStatsImageHeight());
        }
        catch (final IndexOutOfBoundsException ex) {
            this.statsExtraWidth = super.getWidth();
        }
        catch (final IllegalArgumentException ex2) {
            this.statsExtraWidth = super.getWidth();
        }
    }
    
    public Image getStatImage(final int nID) {
        return Game.religionManager.religionImages.get(nID);
    }
    
    public int getStatsImageWidth() {
        return Game.religionManager.religionImages.get(0).getWidth();
    }
    
    public int getStatsImageHeight() {
        return Game.religionManager.religionImages.get(0).getHeight();
    }
    
    public int getStatsImageWidth(final int nID) {
        return Game.religionManager.religionImages.get(nID).getWidth();
    }
    
    public int getStatsImageHeight(final int nID) {
        return Game.religionManager.religionImages.get(nID).getHeight();
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
            float tempFlagScale = (CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING) / this.getStatsImageHeight();
            for (int i = 0; i < this.pieChartData.getPieChartValuesSize(); ++i) {
                try {
                    tempFlagScale = (CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING) / this.getStatsImageHeight(this.pieChartData.getPieChartValue(i).getDataID());
                    oSB.setColor(new Color(this.pieChartData.getPieChartValue_ColorR(i), this.pieChartData.getPieChartValue_ColorG(i), this.pieChartData.getPieChartValue_ColorB(i), 0.725f));
                    Images.pix.draw(oSB, nPosX + nWidth_LEFT + CFG.PADDING + iTranslateX, nPosY + (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) * i + CFG.PADDING * i + iTranslateY, CFG.COLOR_WIDTH, (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2));
                    oSB.setColor(new Color(this.pieChartData.getPieChartValue_ColorR(i), this.pieChartData.getPieChartValue_ColorG(i), this.pieChartData.getPieChartValue_ColorB(i), 0.425f));
                    ImageManager.getImage(Images.gradientHorizontal).draw(oSB, nPosX + nWidth_LEFT + CFG.PADDING + iTranslateX, nPosY + (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) * i + CFG.PADDING * i + iTranslateY, CFG.COLOR_WIDTH + nWidth - nWidth_LEFT, (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2));
                    oSB.setColor(new Color(this.pieChartData.getPieChartValue_ColorR(i), this.pieChartData.getPieChartValue_ColorG(i), this.pieChartData.getPieChartValue_ColorB(i), 0.3f));
                    ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX + nWidth_LEFT + CFG.PADDING + iTranslateX, nPosY + (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) * i + CFG.PADDING * i + iTranslateY, CFG.COLOR_WIDTH + nWidth - nWidth_LEFT, (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) / 4);
                    ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX + nWidth_LEFT + CFG.PADDING + iTranslateX, nPosY + (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) * i + CFG.PADDING * i + iTranslateY + (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) - (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) / 4, CFG.COLOR_WIDTH + nWidth - nWidth_LEFT, (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) / 4, false, true);
                    oSB.setColor(Color.WHITE);
                    this.getStatImage(this.pieChartData.getPieChartValue(i).getDataID()).draw(oSB, nPosX + nWidth_LEFT + CFG.PADDING * 2 + CFG.COLOR_WIDTH + iTranslateX, nPosY + (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) * i + CFG.PADDING * i - CFG.PADDING / 2 + (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) / 2 - (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE / 2.0f) + iTranslateY, (int)(Game.religionManager.religionImages.get(this.pieChartData.getPieChartValue(i).getDataID()).getWidth() * tempFlagScale), (int)(Game.religionManager.religionImages.get(this.pieChartData.getPieChartValue(i).getDataID()).getHeight() * tempFlagScale));
                }
                catch (final IndexOutOfBoundsException ex) {
                    oSB.setColor(new Color(Colors.COLOR_UNKNOWN.r, Colors.COLOR_UNKNOWN.g, Colors.COLOR_UNKNOWN.b, 0.45f));
                    ImageManager.getImage(Images.gradientHorizontal).draw(oSB, nPosX + nWidth_LEFT + CFG.PADDING + iTranslateX, nPosY + (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) * i + CFG.PADDING * i + iTranslateY, CFG.COLOR_WIDTH + nWidth - nWidth_LEFT, (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2));
                    oSB.setColor(new Color(Colors.COLOR_UNKNOWN.r, Colors.COLOR_UNKNOWN.g, Colors.COLOR_UNKNOWN.b, 0.2f));
                    ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX + nWidth_LEFT + CFG.PADDING + iTranslateX, nPosY + (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) * i + CFG.PADDING * i + iTranslateY, CFG.COLOR_WIDTH + nWidth - nWidth_LEFT, (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) / 4);
                    ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX + nWidth_LEFT + CFG.PADDING + iTranslateX, (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) - (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) / 4 + nPosY + (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) * i + CFG.PADDING * i + iTranslateY, CFG.COLOR_WIDTH + nWidth - nWidth_LEFT, (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) / 4, false, true);
                    oSB.setColor(Color.WHITE);
                }
                Renderer.drawTextWithShadow(oSB, this.fontID, "" + CFG.getPercentage(this.pieChartData.getPieChartValue(i).getPercentage(), 4) + "%", nPosX + nWidth_LEFT + (int)(this.getStatsImageWidth() * tempFlagScale) + CFG.PADDING * 3 + CFG.COLOR_WIDTH + iTranslateX, nPosY + (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) * i + CFG.PADDING * i + (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE + CFG.PADDING * 2) / 2 - (int)(CFG.TEXT_HEIGHT * this.TEXT_SCALE / 2.0f) + iTranslateY, Colors.COLOR_TEXT_PIE_CHART_STATS);
            }
            iTranslateY -= this.statsPosY;
            Renderer.resetFontScale();
            Renderer.clipView_End(oSB);
        }
        PieChart_Renderer.pieChart_Frame.draw(oSB, nPosX + iTranslateX, nPosY + iTranslateY, this.iPieChartWidth, this.iPieChartHeight);
    }
}
