// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.pieChart;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.MenuElement_Type;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.MenuElement;

public class PieChart extends MenuElement
{
    protected PieChart_Data pieChartData;
    protected int iPieChartWidth;
    protected int iPieChartHeight;
    protected long animationTimer;
    
    public PieChart(final int iPosX, final int iPosY, final int iWidth, final int iHeight, final PieChart_Data nPieChartData, final MenuElement_Hover menuElementHover) {
        this.animationTimer = -1L;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidth(iWidth);
        this.setHeight(iHeight);
        this.fontID = CFG.FONT_REGULAR;
        this.iPieChartWidth = iWidth;
        this.iPieChartHeight = iHeight;
        this.typeOfElement = MenuElement_Type.PIECHART;
        this.menuElementHover = menuElementHover;
        (this.pieChartData = nPieChartData).sortAndBuild_PieChartValues();
        this.animationTimer = CFG.currentTimeMillis;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        this.drawPieChart(oSB, iTranslateX, iTranslateY, isActive, scrollableY, this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight(), Renderer.pieChartRenderer.getWidth());
    }
    
    protected void drawPieChart(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final int nWidth_LEFT) {
        Renderer.pieChartRenderer.draw(oSB, this.getPieChartBackground(), nPosX + iTranslateX, nPosY + iTranslateY, this.iPieChartWidth, this.iPieChartHeight, this.pieChartData, isActive || this.getIsHovered(), this.animationPerc());
        PieChart_Renderer.pieChart_Frame.draw(oSB, nPosX + iTranslateX, nPosY + iTranslateY, this.iPieChartWidth, this.iPieChartHeight);
    }
    
    public void draw2(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY, final float fPerc, final Color nColor) {
        this.drawPieChart2(oSB, iTranslateX, iTranslateY, isActive, scrollableY, this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight(), Renderer.pieChartRenderer.getWidth(), fPerc, nColor);
    }
    
    protected void drawPieChart2(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final int nWidth_LEFT, final float fPerc, final Color nColor) {
        Renderer.pieChartRenderer.draw2(oSB, PieChart_Renderer.pieChart_BG2, nPosX + iTranslateX, nPosY + iTranslateY, this.iPieChartWidth, this.iPieChartHeight, this.pieChartData, isActive || this.getIsHovered(), this.animationPerc(), fPerc, nColor);
        PieChart_Renderer.pieChart_Frame.draw(oSB, nPosX + iTranslateX, nPosY + iTranslateY, this.iPieChartWidth, this.iPieChartHeight);
    }
    
    protected float animationPerc() {
        return Math.min(1.0f, (CFG.currentTimeMillis - this.animationTimer) / 150.0f);
    }
    
    public TextureRegion getPieChartBackground() {
        return PieChart_Renderer.pieChart_BG;
    }
}
