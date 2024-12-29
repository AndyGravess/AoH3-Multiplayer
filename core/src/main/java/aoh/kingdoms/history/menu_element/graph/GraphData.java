// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.graph;

import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import space.earlygrey.shapedrawer.JoinType;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import java.util.List;

public class GraphData
{
    private static final float ALPHA_CIV_LINE = 0.8f;
    private int iCivID;
    private List<Integer> lPointsY;
    private int iPointsSize;
    private Array<Vector2> lVectorPoints;
    private int iBeginTurnID;
    private boolean drawData;
    private boolean visible;
    private boolean backAnimation;
    protected static final int ANIMATION_TIME = 450;
    private long lTime;
    
    protected GraphData(final int iCivID, final List<Integer> nPointsY, final int iBeginTurnID) {
        this.iPointsSize = 0;
        this.drawData = true;
        this.visible = true;
        this.backAnimation = false;
        this.lTime = 0L;
        this.iCivID = iCivID;
        this.iPointsSize = nPointsY.size();
        this.lPointsY = new ArrayList<Integer>();
        this.lVectorPoints = (Array<Vector2>)new Array();
        for (int i = 0; i < this.iPointsSize; ++i) {
            this.lPointsY.add(nPointsY.get(i));
        }
        this.iBeginTurnID = iBeginTurnID;
        this.drawData = false;
    }
    
    protected final void draw(final SpriteBatch oSB, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final List<Integer> nPointsPosX, final int id, final boolean active, final int iFixPosY) {
        if (this.lTime + 450L >= CFG.currentTimeMillis) {
            this.drawAnimation(oSB, iPosX, iPosY, iWidth, iHeight, nPointsPosX, id, active, iFixPosY);
        }
        else {
            this.drawGraphData(oSB, iPosX, iPosY - iFixPosY, nPointsPosX, id, active);
        }
    }
    
    protected final void drawAnimation(final SpriteBatch oSB, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final List<Integer> nPointsPosX, final int id, final boolean active, final int iFixPosY) {
        if (this.backAnimation) {
            Renderer.clipView_Start(oSB, iPosX, CFG.GAME_HEIGHT - iPosY, iWidth - (int)(iWidth * ((CFG.currentTimeMillis - this.lTime) / 450.0f)), -iHeight);
        }
        else {
            Renderer.clipView_Start(oSB, iPosX, CFG.GAME_HEIGHT - iPosY, (int)(iWidth * ((CFG.currentTimeMillis - this.lTime) / 450.0f)), -iHeight);
        }
        this.drawGraphData(oSB, iPosX, iPosY - iFixPosY, nPointsPosX, id, true);
        Renderer.clipView_End(oSB);
    }
    
    private final void drawGraphData(final SpriteBatch oSB, final int iPosX, final int iPosY, final List<Integer> nPointsPosX, final int id, final boolean active) {
        try {
            oSB.setColor(new Color(Game.getCiv(this.iCivID).getR(), Game.getCiv(this.iCivID).getG(), Game.getCiv(this.iCivID).getB(), active ? 1.0f : 0.8f));
        }
        catch (final IndexOutOfBoundsException ex) {
            oSB.setColor(new Color(0.05882353f, 0.05882353f, 0.05882353f, active ? 1.0f : 0.8f));
        }
        if (this.lVectorPoints.size > 1) {
            final Array<Vector2> nPath = (Array<Vector2>)new Array();
            nPath.add((Object)new Vector2(iPosX + ((Vector2)this.lVectorPoints.get(0)).x, -iPosY + -((Vector2)this.lVectorPoints.get(0)).y));
            for (int i = 1, iSize = this.lVectorPoints.size; i < iSize; ++i) {
                if (((Vector2)this.lVectorPoints.get(i)).x != ((Vector2)this.lVectorPoints.get(i - 1)).x) {
                    nPath.add((Object)new Vector2(iPosX + ((Vector2)this.lVectorPoints.get(i)).x, -iPosY + -((Vector2)this.lVectorPoints.get(i)).y));
                }
            }
            Renderer.shapeDrawer.setColor(Graph.GRAPH_LINE_COLOR);
            Renderer.shapeDrawer.path((Iterable)nPath, 1.0f, JoinType.SMOOTH, true);
            Renderer.oSBBorder.end();
            Renderer.oSBBorder.begin();
        }
    }
    
    protected final void drawCivButton(final SpriteBatch oSB, final int iPosX, final int iPosY, final boolean active) {
        oSB.setColor(new Color(Graph.GRAPH_BG_COLOR.r, Graph.GRAPH_BG_COLOR.g, Graph.GRAPH_BG_COLOR.b, active ? (Graph.GRAPH_BG_COLOR.a * 2.0f) : (this.drawData ? Graph.GRAPH_BG_COLOR.a : (Graph.GRAPH_BG_COLOR.a / 4.0f))));
        Images.pix.draw(oSB, iPosX, iPosY, Graph.getGraphButtonWidth(), Graph.getGraphButtonHeight());
        oSB.setColor(new Color(Graph.GRAPH_BORDERS_COLOR.r, Graph.GRAPH_BORDERS_COLOR.g, Graph.GRAPH_BORDERS_COLOR.b, this.drawData ? Graph.GRAPH_BORDERS_COLOR.a : 0.25f));
        try {
            oSB.setColor(new Color(Game.getCiv(this.iCivID).getR(), Game.getCiv(this.iCivID).getG(), Game.getCiv(this.iCivID).getB(), this.drawData ? 0.8f : 0.4f));
        }
        catch (final IndexOutOfBoundsException ex) {
            oSB.setColor(new Color(0.05882353f, 0.05882353f, 0.05882353f, this.drawData ? 0.8f : 0.4f));
        }
        Images.pix.draw(oSB, iPosX, iPosY, CFG.CIV_COLOR_WIDTH, Graph.getGraphButtonHeight());
        oSB.setColor(this.drawData ? Color.WHITE : new Color(1.0f, 1.0f, 1.0f, 0.25f));
        try {
            Game.getCiv(this.iCivID).getFlag().draw(oSB, iPosX + Graph.getGraphButtonWidth() / 2 - CFG.CIV_FLAG_WIDTH / 2, iPosY + Graph.getGraphButtonHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        }
        catch (final IndexOutOfBoundsException ex) {
            ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, iPosX + Graph.getGraphButtonWidth() / 2 - CFG.CIV_FLAG_WIDTH / 2, iPosY + Graph.getGraphButtonHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(Images.randomCivilizationFlag).getHeight(), CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        }
        oSB.setColor(Color.WHITE);
    }
    
    protected final void buildGraph(final int iHeight, final int nMinPoint, final int nMaxPoint, final List<Integer> nPointsPosX) {
        this.lVectorPoints.clear();
        for (int i = 0; i < this.iPointsSize; ++i) {
            this.lVectorPoints.add((Object)new Vector2((float)nPointsPosX.get(this.iBeginTurnID + i), iHeight - iHeight * (100.0f * this.lPointsY.get(i)) / (nMaxPoint - nMinPoint) / 100.0f));
        }
    }
    
    protected final int getPointY(final int i) {
        try {
            return this.lPointsY.get(i);
        }
        catch (final IndexOutOfBoundsException ex) {
            return 0;
        }
    }
    
    protected final int getPointsSize() {
        return this.iPointsSize;
    }
    
    protected final int getCivID() {
        return this.iCivID;
    }
    
    protected final int getBeginTurnID() {
        return this.iBeginTurnID;
    }
    
    protected final boolean getDrawData() {
        return this.drawData;
    }
    
    protected final void setDrawData(final boolean drawData) {
        if (drawData != this.drawData) {
            if (this.lTime > CFG.currentTimeMillis - 450L && (this.drawData || this.backAnimation)) {
                this.lTime = CFG.currentTimeMillis - (450L - (CFG.currentTimeMillis - this.lTime));
            }
            else {
                this.lTime = CFG.currentTimeMillis;
            }
            this.backAnimation = !drawData;
        }
        this.drawData = drawData;
    }
    
    protected final boolean getVisible() {
        return this.visible;
    }
    
    protected final void setVisible(final boolean visible) {
        this.visible = visible;
    }
    
    protected final boolean getBackAnimation() {
        return this.backAnimation;
    }
    
    protected final void setBackAnimation(final boolean backAnimation) {
        this.backAnimation = backAnimation;
    }
    
    protected final long getTime() {
        return this.lTime;
    }
}
