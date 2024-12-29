// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.pieChart;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.ShortArray;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.math.EarClippingTriangulator;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import aoh.kingdoms.history.textures.ImageManager;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;
import aoh.kingdoms.history.textures.Image;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.Color;

public class PieChart_Renderer
{
    private static Color COLOR_UNKNOWN_DATA;
    private PolygonSpriteBatch oPB;
    public static TextureRegion pieChart_BG;
    public static TextureRegion pieChart_BG2;
    public static Image pieChart_Frame;
    private Vector2 center;
    private Vector2 centerTop;
    private Vector2 leftTop;
    private Vector2 leftBottom;
    private Vector2 rightBottom;
    private Vector2 rightTop;
    private float[] fv;
    private IntersectAt intersectAt;
    public static Color progressBarBG;
    public static Color progressBar;
    public static final int ANIMATION_TIME = 150;
    
    public PieChart_Renderer() {
        PieChart_Renderer.pieChart_BG = new TextureRegion(ImageManager.loadTexture("ui/piechart/bg.png", Pixmap.Format.RGBA8888));
        PieChart_Renderer.pieChart_BG2 = new TextureRegion(ImageManager.loadTexture("ui/piechart/bg2.png", Pixmap.Format.RGBA8888));
        PieChart_Renderer.pieChart_Frame = new Image(ImageManager.loadTexture("ui/piechart/frame.png", Pixmap.Format.RGBA8888), Texture.TextureFilter.Linear);
        this.oPB = new PolygonSpriteBatch();
        this.center = new Vector2((float)(PieChart_Renderer.pieChart_BG.getRegionWidth() / 2), (float)(PieChart_Renderer.pieChart_BG.getRegionHeight() / 2));
        this.centerTop = new Vector2((float)(PieChart_Renderer.pieChart_BG.getRegionWidth() / 2), (float)PieChart_Renderer.pieChart_BG.getRegionHeight());
        this.leftTop = new Vector2(0.0f, (float)PieChart_Renderer.pieChart_BG.getRegionHeight());
        this.leftBottom = new Vector2(0.0f, 0.0f);
        this.rightBottom = new Vector2((float)PieChart_Renderer.pieChart_BG.getRegionWidth(), 0.0f);
        this.rightTop = new Vector2((float)PieChart_Renderer.pieChart_BG.getRegionWidth(), (float)PieChart_Renderer.pieChart_BG.getRegionHeight());
        this.setPercentage(0.0f);
    }
    
    protected final void draw(final SpriteBatch oSB, final TextureRegion nPieChartBG, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final PieChart_Data nData, final boolean isActive, final float fPerc) {
        try {
            try {
                this.drawCircle_100Percent(oSB, nPieChartBG, nPosX, nPosY, nWidth, nHeight, new Color(nData.getPieChartValue_ColorR(0), nData.getPieChartValue_ColorG(0), nData.getPieChartValue_ColorB(0), 1.0f));
            }
            catch (final Exception ex) {
                this.drawCircle_100Percent(oSB, nPieChartBG, nPosX, nPosY, nWidth, nHeight, PieChart_Renderer.COLOR_UNKNOWN_DATA);
            }
            oSB.end();
            this.drawPieChart_Data(nPieChartBG, nPosX, nPosY, nWidth, nHeight, nData, fPerc);
            oSB.begin();
        }
        catch (final Exception exr) {
            CFG.exceptionStack(exr);
        }
    }
    
    protected final void draw2(final SpriteBatch oSB, final TextureRegion nPieChartBG, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final PieChart_Data nData, final boolean isActive, final float fPerc, final float fPercValue, final Color nColor) {
        try {
            try {
                this.drawCircle_100Percent(oSB, nPieChartBG, nPosX, nPosY, nWidth, nHeight, PieChart_Renderer.progressBarBG);
            }
            catch (final IndexOutOfBoundsException ex) {
                this.drawCircle_100Percent(oSB, nPieChartBG, nPosX, nPosY, nWidth, nHeight, PieChart_Renderer.COLOR_UNKNOWN_DATA);
            }
            oSB.end();
            this.drawPieChart_Data2(nPieChartBG, nPosX, nPosY, nWidth, nHeight, nData, fPerc, fPercValue, nColor);
            oSB.begin();
        }
        catch (final Exception exr) {
            CFG.exceptionStack(exr);
        }
    }
    
    private final void drawPieChart_Data(final TextureRegion nPieChartBG, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final PieChart_Data nData, final float fPerc) {
        try {
            this.oPB.begin();
            float drawnPercentage = nData.getPieChartValue(0).getPercentage() * fPerc;
            for (int i = 1; i < nData.getPieChartValuesSize(); ++i) {
                this.setPercentage(drawnPercentage);
                try {
                    this.drawCircle(nPieChartBG, nPosX, nPosY, nWidth, nHeight, new Color(nData.getPieChartValue_ColorR(i), nData.getPieChartValue_ColorG(i), nData.getPieChartValue_ColorB(i), 1.0f));
                }
                catch (final IndexOutOfBoundsException ex) {
                    this.drawCircle(nPieChartBG, nPosX, nPosY, nWidth, nHeight, PieChart_Renderer.COLOR_UNKNOWN_DATA);
                }
                drawnPercentage += nData.getPieChartValue(i).getPercentage() * fPerc;
            }
            this.oPB.end();
        }
        catch (final Exception ex2) {}
    }
    
    private final void drawPieChart_Data2(final TextureRegion nPieChartBG, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final PieChart_Data nData, final float fPerc, final float fPercValue, final Color nColor) {
        try {
            this.oPB.begin();
            this.setPercentage(fPercValue);
            this.drawCircle2(nPieChartBG, nPosX, nPosY, nWidth, nHeight, nColor);
            this.oPB.end();
        }
        catch (final Exception ex) {}
    }
    
    private final void drawCircle_100Percent(final SpriteBatch oSB, final TextureRegion nPieChartBG, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final Color nColor) {
        oSB.setColor(nColor);
        oSB.draw(nPieChartBG.getTexture(), (float)nPosX, (float)(-nPosY - nHeight), 0.0f, 0.0f, (float)nWidth, (float)nHeight, 1.0f, 1.0f, 0.0f, 0, 0, nPieChartBG.getRegionWidth(), nPieChartBG.getRegionHeight(), false, false);
        oSB.setColor(Color.WHITE);
    }
    
    private final void drawCircle_100Percent2(final SpriteBatch oSB, final TextureRegion nPieChartBG, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final Color nColor) {
        oSB.setColor(new Color(nColor.r - 0.1f, nColor.g - 0.1f, nColor.b - 0.1f, nColor.a));
        oSB.draw(nPieChartBG.getTexture(), (float)nPosX, (float)(-nPosY - nHeight), 0.0f, 0.0f, (float)nWidth, (float)nHeight, 1.0f, 1.0f, 0.0f, 0, 0, nPieChartBG.getRegionWidth(), nPieChartBG.getRegionHeight(), false, false);
        oSB.setColor(Color.WHITE);
    }
    
    private final void drawCircle(final TextureRegion nPieChartBG, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final Color nColor) {
        if (this.fv == null) {
            return;
        }
        final EarClippingTriangulator e = new EarClippingTriangulator();
        final ShortArray sv = e.computeTriangles(this.fv);
        final PolygonRegion polyReg = new PolygonRegion(nPieChartBG, this.fv, sv.toArray());
        final PolygonSprite poly = new PolygonSprite(polyReg);
        poly.setOrigin(0.0f, 0.0f);
        poly.setPosition((float)nPosX, (float)(CFG.GAME_HEIGHT - nHeight - nPosY));
        poly.setRotation(0.0f);
        poly.setColor(nColor);
        poly.setSize((float)nWidth, (float)nHeight);
        poly.draw(this.oPB);
    }
    
    private final void drawCircle2(final TextureRegion nPieChartBG, final int nPosX, final int nPosY, final int nWidth, final int nHeight, final Color nColor) {
        if (this.fv == null) {
            return;
        }
        final EarClippingTriangulator e = new EarClippingTriangulator();
        final ShortArray sv = e.computeTriangles(this.fv);
        final PolygonRegion polyReg = new PolygonRegion(nPieChartBG, this.fv, sv.toArray());
        final PolygonSprite poly = new PolygonSprite(polyReg);
        poly.setOrigin(0.0f, 0.0f);
        poly.setPosition((float)nPosX, (float)(CFG.GAME_HEIGHT - nHeight - nPosY));
        poly.setRotation(0.0f);
        poly.setColor(new Color(nColor.r - 0.1f, nColor.g - 0.1f, nColor.b - 0.1f, nColor.a));
        poly.setSize((float)nWidth, (float)nHeight);
        poly.draw(this.oPB);
    }
    
    protected final void setPercentage(final float percent) {
        float angle = this.convertToRadians(90.0f);
        angle -= this.convertToRadians(percent * 360.0f / 100.0f);
        final float len = (PieChart_Renderer.pieChart_BG.getRegionWidth() > PieChart_Renderer.pieChart_BG.getRegionHeight()) ? ((float)PieChart_Renderer.pieChart_BG.getRegionWidth()) : ((float)PieChart_Renderer.pieChart_BG.getRegionHeight());
        final float dy = (float)(Math.sin(angle) * len);
        final float dx = (float)(Math.cos(angle) * len);
        final Vector2 line = new Vector2(this.center.x + dx, this.center.y + dy);
        final Vector2 v = this.IntersectPoint(line);
        if (this.intersectAt == IntersectAt.TOP) {
            if (v.x >= PieChart_Renderer.pieChart_BG.getRegionWidth() / 2) {
                this.fv = new float[] { this.center.x, this.center.y, this.centerTop.x, this.centerTop.y, this.leftTop.x, this.leftTop.y, this.leftBottom.x, this.leftBottom.y, this.rightBottom.x, this.rightBottom.y, this.rightTop.x, this.rightTop.y, v.x, v.y };
            }
            else {
                this.fv = new float[] { this.center.x, this.center.y, this.centerTop.x, this.centerTop.y, v.x, v.y };
            }
        }
        else if (this.intersectAt == IntersectAt.BOTTOM) {
            this.fv = new float[] { this.center.x, this.center.y, this.centerTop.x, this.centerTop.y, this.leftTop.x, this.leftTop.y, this.leftBottom.x, this.leftBottom.y, v.x, v.y };
        }
        else if (this.intersectAt == IntersectAt.LEFT) {
            this.fv = new float[] { this.center.x, this.center.y, this.centerTop.x, this.centerTop.y, this.leftTop.x, this.leftTop.y, v.x, v.y };
        }
        else if (this.intersectAt == IntersectAt.RIGHT) {
            this.fv = new float[] { this.center.x, this.center.y, this.centerTop.x, this.centerTop.y, this.leftTop.x, this.leftTop.y, this.leftBottom.x, this.leftBottom.y, this.rightBottom.x, this.rightBottom.y, v.x, v.y };
        }
        else {
            this.fv = null;
        }
    }
    
    private final Vector2 IntersectPoint(final Vector2 line) {
        final Vector2 v = new Vector2();
        boolean isIntersect = Intersector.intersectSegments(this.leftTop, this.rightTop, this.center, line, v);
        if (isIntersect) {
            this.intersectAt = IntersectAt.TOP;
            return v;
        }
        isIntersect = Intersector.intersectSegments(this.leftBottom, this.rightBottom, this.center, line, v);
        if (isIntersect) {
            this.intersectAt = IntersectAt.BOTTOM;
            return v;
        }
        isIntersect = Intersector.intersectSegments(this.leftTop, this.leftBottom, this.center, line, v);
        if (isIntersect) {
            this.intersectAt = IntersectAt.LEFT;
            return v;
        }
        isIntersect = Intersector.intersectSegments(this.rightTop, this.rightBottom, this.center, line, v);
        if (isIntersect) {
            this.intersectAt = IntersectAt.RIGHT;
            return v;
        }
        this.intersectAt = IntersectAt.NONE;
        return null;
    }
    
    private final float convertToRadians(final float angleInDegrees) {
        final float angleInRadians = angleInDegrees * 0.017453292f;
        return angleInRadians;
    }
    
    public final int getWidth() {
        return PieChart_Renderer.pieChart_Frame.getWidth();
    }
    
    public final int getHeight() {
        return PieChart_Renderer.pieChart_Frame.getHeight();
    }
    
    static {
        PieChart_Renderer.COLOR_UNKNOWN_DATA = new Color(0.0f, 0.0f, 0.0f, 1.0f);
        PieChart_Renderer.progressBarBG = new Color(0.09803922f, 0.09803922f, 0.15686275f, 1.0f);
        PieChart_Renderer.progressBar = new Color(0.29411766f, 0.49019608f, 0.64705884f, 1.0f);
    }
    
    protected enum IntersectAt
    {
        NONE, 
        TOP, 
        BOTTOM, 
        LEFT, 
        RIGHT;
    }
}
