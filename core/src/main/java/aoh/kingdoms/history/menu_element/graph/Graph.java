// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.graph;

import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.MenuElement_Type;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Game;
import java.util.ArrayList;
import aoh.kingdoms.history.mainGame.CFG;
import java.util.List;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.MenuElement;

public class Graph extends MenuElement
{
    protected static final Color GRAPH_BG_COLOR;
    protected static final Color GRAPH_BORDERS_COLOR;
    protected static final Color GRAPH_LINES_COLOR;
    protected static final Color GRAPH_LINES_DESC;
    protected static final Color GRAPH_LINE_COLOR;
    protected static final Color TEXT_COLOR;
    protected static final Color DATA_COLOR;
    protected static float POINTS_TEXT_SCALE;
    protected List<GraphData> lData;
    public int iDataSize;
    public List<Integer> lSortedData;
    public List<Integer> lPointsPosX;
    public int iPointsPosXSize;
    public int iMaxSize;
    public int iFixPosY;
    public int iHoveredID;
    public static final int FONT_ID = 1;
    public int iZeroPosY;
    public int iMinPoint;
    public int iMinTextWidth;
    public int iWorstCivID;
    public int iMaxPoint;
    public int iMaxPoint_Text;
    public int iMaxTextWidth;
    public int iBestCivID;
    public float fAvaragePoint;
    public int iAvaragePosY;
    public byte bDecimal;
    public boolean lessThanTen;
    public boolean split100;
    public int iDescOfTurnID;
    public int iWorstDescDataID;
    public int iWorstDescDataTextWidth;
    public int iBestDescDataID;
    public int iBestDescDataTextWidth;
    public String sTextX;
    public String sTextX2;
    public String sTextY;
    public int iWidthTextX;
    public int iWidthTextX2;
    public int iWidthTextY;
    public static final int ANIMATION_TIME = 250;
    public long lTime;
    public static final int AUTO_MOVE_TURN_TIME = 1450;
    public long lAuto_Move_Turn_Time;
    public boolean moveable;
    public int iButtonsPosY;
    public int iActiveButtonID;
    public GraphType graphType;
    
    protected static final int getGraphButtonWidth() {
        return 0;
    }
    
    protected static final int getGraphButtonHeight() {
        return CFG.BUTTON_HEIGHT / 2;
    }
    
    public Graph(final String sTextX, final String sTextY, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final boolean visible, final int nLoadSize, final GraphType graphType, final boolean split100) {
        this.iMaxSize = 0;
        this.iHoveredID = -1;
        this.bDecimal = 0;
        this.lessThanTen = false;
        this.split100 = false;
        this.iDescOfTurnID = 0;
        this.lTime = 0L;
        this.lAuto_Move_Turn_Time = 0L;
        this.moveable = false;
        this.iButtonsPosY = 0;
        this.iActiveButtonID = -1;
        this.sTextX = sTextX;
        this.sTextY = sTextY;
        this.graphType = graphType;
        this.split100 = split100;
        final List<Integer> nCivs = new ArrayList<Integer>();
        if (graphType == GraphType.PLAYER_INCOME) {
            nCivs.add(Game.player.iCivID);
        }
        else {
            nCivs.add(Game.player.iCivID);
        }
        Renderer.fontMain.get(1).getData().setScale(0.7f);
        Renderer.glyphLayout.setText(Renderer.fontMain.get(1), sTextX);
        this.iWidthTextX = (int)Renderer.glyphLayout.width;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(1), sTextY);
        this.iWidthTextY = (int)Renderer.glyphLayout.width;
        Renderer.fontMain.get(1).getData().setScale(1.0f);
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidth(iWidth);
        this.setHeight(iHeight);
        this.setVisible(visible);
        this.lData = new ArrayList<GraphData>();
        this.lSortedData = new ArrayList<Integer>();
        this.lPointsPosX = new ArrayList<Integer>();
        this.iFixPosY = 0;
        this.typeOfElement = MenuElement_Type.GRAPH;
        for (int i = 0; i < nCivs.size(); ++i) {
            this.addData(new GraphData(nCivs.get(i), new ArrayList<Integer>(), 0));
        }
        for (int i = 0; i < nLoadSize && i < this.lData.size(); ++i) {
            this.loadData(i);
        }
        this.iDataSize = this.lData.size();
    }
    
    protected void loadData(final int i) {
        final int nStartTurnID = 0;
        final List<Integer> tempPoints = new ArrayList<Integer>();
        if (this.graphType == GraphType.PLAYER_INCOME) {
            for (int j = 0; j < Game.player.playerStats2.income.size(); ++j) {
                tempPoints.add(Game.player.playerStats2.income.get(j));
            }
        }
        else if (this.graphType == GraphType.PLAYER_BALANCE) {
            for (int j = 0; j < Game.player.playerStats2.balance.size(); ++j) {
                tempPoints.add(Game.player.playerStats2.balance.get(j));
            }
        }
        else if (this.graphType == GraphType.PLAYER_PRESTIGE) {
            for (int j = 0; j < Game.player.playerStats3.prestige.size(); ++j) {
                tempPoints.add(Game.player.playerStats3.prestige.get(j));
            }
        }
        else if (this.graphType == GraphType.PLAYER_POPULATION) {
            for (int j = 0; j < Game.player.playerStats3.population.size(); ++j) {
                tempPoints.add(Game.player.playerStats3.population.get(j));
            }
        }
        else {
            for (int a = 0; a < 5; ++a) {
                tempPoints.add(100 + Game.oR.nextInt(1 + Game.oR.nextInt(1 + Game.oR.nextInt(100))));
            }
        }
        if (tempPoints.size() > 0) {
            this.lData.set(i, new GraphData(this.lData.get(i).getCivID(), tempPoints, nStartTurnID));
            this.lData.get(i).setDrawData(true);
            this.updateMoveable();
            this.buildGraph();
        }
    }
    
    @Override
    public void updateHover(final int nPosX, final int nPosY, final int menuPosX, final int menuPosY) {
        for (int i = 0; i < this.iDataSize; ++i) {
            if (this.getPosX() + this.getWidth() - getGraphButtonWidth() + menuPosX <= nPosX && this.getPosX() + this.getWidth() + menuPosX >= nPosX && this.getButtonsPosY(i) + this.iButtonsPosY + menuPosY <= nPosY && this.getButtonsPosY(i) + getGraphButtonHeight() + this.iButtonsPosY + menuPosY >= nPosY) {
                this.setHoveredID(this.lSortedData.get(i));
                return;
            }
        }
        this.setHoveredID(-1);
    }
    
    public final void setHoveredID(final int nHoveredID) {
        if (this.iHoveredID != nHoveredID) {
            this.iHoveredID = nHoveredID;
            this.buildElementHover();
        }
    }
    
    @Override
    public void buildElementHover() {
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        if (this.lAuto_Move_Turn_Time + 1450L < CFG.currentTimeMillis) {
            this.incrementTurnDescInfo();
        }
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(0.06666667f, 0.07450981f, 0.09019608f, 1.0f));
        Renderer.drawBox2(oSB, -2 + this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2 + iTranslateX, -2 + this.getPosY() + iTranslateY, 4 + this.getGraphWidth() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2, 4 + this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2, 1.0f);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.75f));
        ImageManager.getImage(Images.graphBG).draw2(oSB, this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + iTranslateY, this.getGraphWidth() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2, this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.8f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + iTranslateY, this.getGraphWidth() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2, this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2, false, true);
        Images.gradientXY.draw(oSB, this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + iTranslateY, this.getGraphWidth() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2, this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2);
        Renderer.fontMain.get(1).getData().setScale(0.8f);
        Renderer.drawTextWithShadowRotated(oSB, 1, this.sTextY, this.getPosX() - 2 + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 + this.iWidthTextY / 2 + iTranslateY, Colors.HOVER_GOLD, 90.0f);
        Renderer.drawTextWithShadow(oSB, 1, this.sTextX, this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.8f) + CFG.PADDING * 2 + CFG.PADDING * 2 + iTranslateX, this.getPosY() + 2 + this.getHeight() - CFG.PADDING - (int)(CFG.TEXT_HEIGHT * 0.8f) + iTranslateY, Graph.DATA_COLOR);
        Renderer.fontMain.get(1).getData().setScale(1.0f);
        oSB.setColor(Graph.GRAPH_LINES_DESC);
        ImageManager.getImage(Images.line_33).draw2(oSB, this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2 + iTranslateX, this.getPosY() - this.iFixPosY + this.iAvaragePosY + iTranslateY, this.getGraphWidth() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2);
        if (this.getMinPoint() < 0 && this.iMaxPoint > 0) {
            oSB.setColor(Graph.GRAPH_LINES_COLOR);
            Images.pix.draw(oSB, this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2 + 1 + iTranslateX, this.getPosY() - this.iFixPosY + this.iZeroPosY + iTranslateY, this.getGraphWidth() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2 - 1);
            oSB.setColor(Graph.GRAPH_BORDERS_COLOR);
            Images.pix.draw(oSB, this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2 + 1 + iTranslateX, this.getPosY() - 1 - this.iFixPosY + this.iZeroPosY + iTranslateY, CFG.PADDING - 1);
            Renderer.fontMain.get(1).getData().setScale(Graph.POINTS_TEXT_SCALE);
            Renderer.drawTextWithShadow(oSB, 1, "0", this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + 1 + (int)(2.0f * CFG.GUI_SCALE) + iTranslateX, this.getPosY() - (int)(2.0f * CFG.GUI_SCALE) - (int)(CFG.TEXT_HEIGHT * Graph.POINTS_TEXT_SCALE) - this.iFixPosY + this.iZeroPosY - 1 + iTranslateY, Graph.DATA_COLOR);
            Renderer.fontMain.get(1).getData().setScale(1.0f);
        }
        if (this.lTime + 250L > CFG.currentTimeMillis) {
            Renderer.clipView_Start(oSB, this.getPosX() + iTranslateX, CFG.GAME_HEIGHT - this.getPosY() - iTranslateY, (int)(this.getGraphWidth() * ((CFG.currentTimeMillis - this.lTime) / 250.0f)), -this.getHeight());
            this.drawGraphData(oSB, iTranslateX, iTranslateY);
            Renderer.clipView_End(oSB);
        }
        else {
            this.drawGraphData(oSB, iTranslateX, iTranslateY);
        }
        oSB.setColor(Graph.GRAPH_BORDERS_COLOR);
        Images.pix.draw(oSB, this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + iTranslateX, this.getPosY() + iTranslateY, CFG.PADDING - 1);
        Images.pix.draw(oSB, this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + iTranslateX, this.getPosY() - this.iFixPosY + this.iAvaragePosY + iTranslateY, CFG.PADDING - 1);
        Images.pix.draw(oSB, this.getPosX() + this.getGraphWidth() - 1 + iTranslateX, this.getPosY() + this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) - CFG.PADDING + 1 + iTranslateY, 1, CFG.PADDING - 1);
        Renderer.fontMain.get(1).getData().setScale(Graph.POINTS_TEXT_SCALE);
        if (this.split100) {
            Renderer.drawTextWithShadow(oSB, 1, "" + CFG.getPrecision2(this.getMinPoint() / 100.0f, 10), this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + 1 + (int)(2.0f * CFG.GUI_SCALE) + iTranslateX, this.getPosY() + this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) - (int)(2.0f * CFG.GUI_SCALE) - (int)(CFG.TEXT_HEIGHT * Graph.POINTS_TEXT_SCALE) + iTranslateY, Graph.DATA_COLOR);
            Renderer.drawTextWithShadow(oSB, 1, "" + CFG.getPrecision2(this.iMaxPoint_Text / 100.0f, 10), this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + 1 + (int)(2.0f * CFG.GUI_SCALE) + iTranslateX, this.getPosY() + 1 + (int)(2.0f * CFG.GUI_SCALE) + iTranslateY, Graph.DATA_COLOR);
        }
        else {
            Renderer.drawTextWithShadow(oSB, 1, "" + this.getMinPoint(), this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + 1 + (int)(2.0f * CFG.GUI_SCALE) + iTranslateX, this.getPosY() + this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) - (int)(2.0f * CFG.GUI_SCALE) - (int)(CFG.TEXT_HEIGHT * Graph.POINTS_TEXT_SCALE) + iTranslateY, Graph.DATA_COLOR);
            Renderer.drawTextWithShadow(oSB, 1, "" + this.iMaxPoint_Text, this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + 1 + (int)(2.0f * CFG.GUI_SCALE) + iTranslateX, this.getPosY() + 1 + (int)(2.0f * CFG.GUI_SCALE) + iTranslateY, Graph.DATA_COLOR);
        }
        oSB.setColor(Color.WHITE);
        Renderer.fontMain.get(1).getData().setScale(1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(Graph.GRAPH_BORDERS_COLOR);
        Images.pix.draw(oSB, this.getPosX() - 1 + (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + iTranslateY, 1, this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2);
        Images.pix.draw(oSB, this.getPosX() - 1 + (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2 + iTranslateY, this.getGraphWidth() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2 + 1, 1);
        Images.pix.draw(oSB, this.getPosX() + this.getGraphWidth() - CFG.PADDING + iTranslateX, this.getPosY() + iTranslateY, CFG.PADDING);
        Images.pix.draw(oSB, this.getPosX() - 1 + this.getGraphWidth() + iTranslateX, this.getPosY() + iTranslateY, 1, CFG.PADDING - 1);
        Renderer.clipView_Start(oSB, this.getPosX() + iTranslateX, CFG.GAME_HEIGHT - this.getPosY() - iTranslateY, this.getWidth(), -(this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2 + 2));
        Renderer.clipView_End(oSB);
        oSB.setColor(Color.WHITE);
    }
    
    public final void drawGraphData(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY) {
        int i = 0;
        final int tempFixPosY = (this.getMinPoint() > 0) ? this.iFixPosY : this.iFixPosY;
        while (i < this.iDataSize) {
            if (this.lData.get(i).getDrawData()) {
                this.lData.get(i).draw(oSB, this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + iTranslateY, this.getGraphWidth(), this.getHeight(), this.lPointsPosX, i, (this.iActiveButtonID >= 0) ? (this.lSortedData.get(this.iActiveButtonID) == i) : (this.iHoveredID >= 0 && this.lSortedData.get(this.iHoveredID) == i), tempFixPosY);
            }
            else if (this.lData.get(i).getBackAnimation()) {
                if (this.lData.get(i).getTime() + 450L <= CFG.currentTimeMillis) {
                    this.lData.get(i).setBackAnimation(false);
                }
                else {
                    this.lData.get(i).drawAnimation(oSB, this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + iTranslateY, this.getGraphWidth(), this.getHeight(), this.lPointsPosX, i, this.iActiveButtonID == this.lSortedData.get(i) || this.iHoveredID == this.lSortedData.get(i), tempFixPosY);
                }
            }
            ++i;
        }
    }
    
    protected final void setData(final List<GraphData> nData) {
        this.lData.clear();
        for (int i = 0; i < nData.size(); ++i) {
            this.lData.add(nData.get(i));
        }
        this.iDataSize = this.lData.size();
        this.buildGraph();
    }
    
    protected final void addData(final GraphData nData) {
        for (int i = 0; i < this.iDataSize; ++i) {
            if (this.lData.get(i).getCivID() == nData.getCivID()) {
                return;
            }
        }
        this.lData.add(nData);
        this.iDataSize = this.lData.size();
        this.updateMoveable();
        this.buildGraph();
        this.sortCivsByLastPoint();
    }
    
    protected final void removeData(final int iCivID) {
        if (this.iDataSize > 1) {
            for (int i = 0; i < this.iDataSize; ++i) {
                if (this.lData.get(i).getCivID() == iCivID) {
                    this.lData.remove(i);
                    this.iDataSize = this.lData.size();
                    this.updateMoveable();
                    this.buildGraph();
                    this.updateButtonsInView();
                    return;
                }
            }
        }
        this.sortCivsByLastPoint();
    }
    
    @Override
    public void setMin(final int nCivID) {
        int i = 0;
        while (i < this.lData.size()) {
            if (this.lData.get(i).getCivID() == nCivID) {
                this.lData.get(i).setDrawData(!this.lData.get(i).getDrawData());
                if (this.lData.get(i).getDrawData()) {
                    this.loadData(i);
                    break;
                }
                break;
            }
            else {
                ++i;
            }
        }
    }
    
    public final void sortCivsByLastPoint() {
        this.lSortedData.clear();
        for (int i = 0; i < this.iDataSize; ++i) {
            this.lSortedData.add(i);
        }
    }
    
    public final int getDataLastPoint(final int id) {
        try {
            return this.lData.get(id).getPointY(this.iPointsPosXSize - 1 - this.lData.get(id).getBeginTurnID());
        }
        catch (final IndexOutOfBoundsException ex) {
            return 0;
        }
    }
    
    @Override
    public void updateSlider(final int nPosX) {
        this.updateMoveTurnTime();
    }
    
    protected final void updateDescInfo() {
        int tempBestResult = this.getMinPoint();
        int tempWorstResult = this.iMaxPoint;
        for (int i = 0; i < this.iDataSize; ++i) {
            if (this.lData.get(i).getDrawData() && this.iDescOfTurnID >= this.lData.get(i).getBeginTurnID() && this.iDescOfTurnID < this.lData.get(i).getBeginTurnID() + this.lData.get(i).getPointsSize()) {
                if (this.lData.get(i).getPointY(this.iDescOfTurnID - this.lData.get(i).getBeginTurnID()) > tempBestResult) {
                    tempBestResult = this.lData.get(i).getPointY(this.iDescOfTurnID - this.lData.get(i).getBeginTurnID());
                    this.iBestDescDataID = i;
                }
                if (this.lData.get(i).getPointY(this.iDescOfTurnID - this.lData.get(i).getBeginTurnID()) <= tempWorstResult) {
                    tempWorstResult = this.lData.get(i).getPointY(this.iDescOfTurnID - this.lData.get(i).getBeginTurnID());
                    this.iWorstDescDataID = i;
                }
            }
        }
        Renderer.fontMain.get(1).getData().setScale(Graph.POINTS_TEXT_SCALE);
        Renderer.glyphLayout.setText(Renderer.fontMain.get(1), "" + this.lData.get(this.iWorstDescDataID).getPointY(this.iDescOfTurnID - this.lData.get(this.iWorstDescDataID).getBeginTurnID()));
        this.iWorstDescDataTextWidth = (int)Renderer.glyphLayout.width;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(1), "" + this.lData.get(this.iBestDescDataID).getPointY(this.iDescOfTurnID - this.lData.get(this.iBestDescDataID).getBeginTurnID()));
        this.iBestDescDataTextWidth = (int)Renderer.glyphLayout.width;
        Renderer.fontMain.get(1).getData().setScale(1.0f);
        int tempRealTurnID = 1;
        if (this.iPointsPosXSize < Game_Calendar.TURN_ID) {
            tempRealTurnID = Game_Calendar.TURN_ID - this.iPointsPosXSize - 1 + (this.iDescOfTurnID + 1);
        }
        else {
            tempRealTurnID = this.iDescOfTurnID + 1;
        }
        this.sTextX = Game_Calendar.getDate_ByTurnID(0);
        Renderer.glyphLayout.setText(Renderer.fontMain.get(1), this.sTextX);
        this.iWidthTextX = (int)Renderer.glyphLayout.width;
        this.updateMoveTurnTime();
    }
    
    protected final void buildGraph() {
        final int pointY = this.lData.get(0).getPointY(0);
        this.iMaxPoint = pointY;
        this.iMinPoint = pointY;
        this.fAvaragePoint = 0.0f;
        final int civID = this.lData.get(0).getCivID();
        this.iWorstCivID = civID;
        this.iBestCivID = civID;
        int tempAvarageSize = 0;
        this.iMaxSize = 0;
        for (int i = 0; i < this.iDataSize; ++i) {
            if (this.lData.get(i).getDrawData()) {
                float tempAverage = 0.0f;
                for (int j = 0; j < this.lData.get(i).getPointsSize(); ++j) {
                    if (this.lData.get(i).getPointY(j) > this.iMaxPoint) {
                        this.iMaxPoint = this.lData.get(i).getPointY(j);
                        this.iBestCivID = this.lData.get(i).getCivID();
                    }
                    if (this.lData.get(i).getPointY(j) <= this.iMinPoint) {
                        this.iMinPoint = this.lData.get(i).getPointY(j);
                        this.iWorstCivID = this.lData.get(i).getCivID();
                    }
                    tempAverage += this.lData.get(i).getPointY(j);
                }
                this.fAvaragePoint += tempAverage / this.lData.get(i).getPointsSize();
                ++tempAvarageSize;
                if (this.iMaxSize < this.lData.get(i).getPointsSize() + this.lData.get(i).getBeginTurnID()) {
                    this.iMaxSize = this.lData.get(i).getPointsSize() + this.lData.get(i).getBeginTurnID();
                }
            }
            else {
                for (int k = 0; k < this.lData.get(i).getPointsSize(); ++k) {
                    if (this.lData.get(i).getPointY(k) > this.iMaxPoint) {
                        this.iMaxPoint = this.lData.get(i).getPointY(k);
                        this.iBestCivID = this.lData.get(i).getCivID();
                    }
                    if (this.lData.get(i).getPointY(k) <= this.iMinPoint) {
                        this.iMinPoint = this.lData.get(i).getPointY(k);
                        this.iWorstCivID = this.lData.get(i).getCivID();
                    }
                }
                if (this.iMaxSize < this.lData.get(i).getPointsSize() + this.lData.get(i).getBeginTurnID()) {
                    this.iMaxSize = this.lData.get(i).getPointsSize() + this.lData.get(i).getBeginTurnID();
                }
            }
        }
        this.iMaxPoint_Text = this.iMaxPoint;
        this.iMaxPoint += (int)(this.iMaxPoint * 0.05f);
        this.fAvaragePoint /= tempAvarageSize;
        try {
            if (this.iMinPoint < 0) {
                this.iFixPosY = -(int)((this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2) * (100.0f * this.getMinPoint()) / (this.iMaxPoint - this.getMinPoint()) / 100.0f);
                this.iZeroPosY = (int)(this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2 - (this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2) * 0.0f / (this.iMaxPoint - this.getMinPoint()) / 100.0f);
            }
            else if (this.iMinPoint > 0) {
                this.iFixPosY = (int)(this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2 - (this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2) * (100.0f * this.getMinPoint()) / (this.iMaxPoint - this.getMinPoint()) / 100.0f - (this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2));
            }
            else {
                this.iFixPosY = 0;
            }
        }
        catch (final ArithmeticException ex) {
            this.iFixPosY = 0;
        }
        this.iAvaragePosY = (int)(this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2 - (this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2) * (100.0f * this.fAvaragePoint) / (this.iMaxPoint - this.getMinPoint()) / 100.0f);
        this.roundAverage();
        this.lPointsPosX.clear();
        this.lPointsPosX.add(0);
        for (int i = 1; i < this.iMaxSize - 1; ++i) {
            this.lPointsPosX.add((int)((this.getGraphWidth() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2) * (100.0f * i) / (this.iMaxSize - 1) / 100.0f));
        }
        this.lPointsPosX.add(this.getGraphWidth() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2);
        this.iPointsPosXSize = this.lPointsPosX.size();
        for (int i = 0; i < this.iDataSize; ++i) {
            this.lData.get(i).buildGraph(this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2, this.getMinPoint(), this.iMaxPoint, this.lPointsPosX);
        }
        Renderer.fontMain.get(1).getData().setScale(Graph.POINTS_TEXT_SCALE);
        Renderer.glyphLayout.setText(Renderer.fontMain.get(1), "" + this.iMinPoint);
        this.iMinTextWidth = (int)Renderer.glyphLayout.width;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(1), "" + this.iMaxPoint_Text);
        this.iMaxTextWidth = (int)Renderer.glyphLayout.width;
        Renderer.fontMain.get(1).getData().setScale(1.0f);
        this.updateDescInfo();
    }
    
    public final void updateButtonsInView() {
        for (int i = 0; i < this.iDataSize; ++i) {
            if (this.getButtonsPosY(i) + this.iButtonsPosY >= 0 && this.getButtonsPosY(i) + this.iButtonsPosY <= this.getHeight()) {
                this.lData.get(this.lSortedData.get(i)).setVisible(true);
            }
            else if (this.getButtonsPosY(i) + getGraphButtonHeight() + this.iButtonsPosY >= 0 && this.getButtonsPosY(i) + getGraphButtonHeight() + this.iButtonsPosY <= this.getHeight()) {
                this.lData.get(this.lSortedData.get(i)).setVisible(true);
            }
            else {
                this.lData.get(this.lSortedData.get(i)).setVisible(false);
            }
        }
    }
    
    protected final void updateMoveable() {
        if (this.getButtonsHeight() > this.getHeight()) {
            this.moveable = true;
        }
        else {
            this.moveable = false;
            this.iButtonsPosY = 0;
        }
    }
    
    @Override
    public final void setScrollPosY(int nPosY) {
        nPosY -= this.getPosY();
        for (int i = 0; i < this.iDataSize; ++i) {
            if (this.getButtonsPosY(i) + this.iButtonsPosY <= nPosY && this.getButtonsPosY(i) + getGraphButtonHeight() + this.iButtonsPosY >= nPosY) {
                this.iActiveButtonID = i;
                Gdx.app.log("AoC", "" + this.iActiveButtonID);
                break;
            }
        }
    }
    
    public final void actionUp(int nPosY) {
        nPosY -= this.getPosY();
        if (this.iActiveButtonID >= 0 && this.getButtonsPosY(this.iActiveButtonID) + this.iButtonsPosY <= nPosY && this.getButtonsPosY(this.iActiveButtonID) + getGraphButtonHeight() + this.iButtonsPosY >= nPosY) {
            if (!this.lData.get(this.lSortedData.get(this.iActiveButtonID)).getDrawData()) {
                this.lData.get(this.lSortedData.get(this.iActiveButtonID)).setDrawData(!this.lData.get(this.lSortedData.get(this.iActiveButtonID)).getDrawData());
                if (this.lData.get(this.lSortedData.get(this.iActiveButtonID)).getDrawData()) {
                    this.loadData(this.lSortedData.get(this.iActiveButtonID));
                }
                this.buildGraph();
            }
            else {
                int numOfActiveDatas = 0;
                for (int j = 0; j < this.iDataSize; ++j) {
                    if (this.lData.get(j).getDrawData()) {
                        ++numOfActiveDatas;
                    }
                }
                if (numOfActiveDatas > 1) {
                    this.lData.get(this.lSortedData.get(this.iActiveButtonID)).setDrawData(!this.lData.get(this.lSortedData.get(this.iActiveButtonID)).getDrawData());
                    if (this.lData.get(this.lSortedData.get(this.iActiveButtonID)).getDrawData()) {
                        this.loadData(this.lSortedData.get(this.iActiveButtonID));
                    }
                    this.buildGraph();
                }
            }
        }
        this.iActiveButtonID = -1;
    }
    
    @Override
    public int getCurrent() {
        return this.iButtonsPosY;
    }
    
    @Override
    public void setCurrent(int nButtonsPosY) {
        if (nButtonsPosY >= 0) {
            nButtonsPosY = 0;
        }
        else if (nButtonsPosY <= -(this.getButtonsHeight() - (this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2))) {
            nButtonsPosY = -(this.getButtonsHeight() - (this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2));
        }
        if (this.iButtonsPosY != nButtonsPosY) {
            this.iButtonsPosY = nButtonsPosY;
            this.updateButtonsInView();
        }
    }
    
    @Override
    public boolean getMoveable() {
        return this.moveable;
    }
    
    public final int getButtonsPosY(final int i) {
        return getGraphButtonHeight() * i + CFG.PADDING * i;
    }
    
    public final int getButtonsHeight() {
        return getGraphButtonHeight() * this.iDataSize + CFG.PADDING * (this.iDataSize - 1);
    }
    
    public final void roundAverage() {
        if (this.fAvaragePoint - (int)this.fAvaragePoint != 0.0f) {
            this.bDecimal = (byte)Math.round((this.fAvaragePoint - (int)this.fAvaragePoint) * 100.0f);
            this.fAvaragePoint -= this.fAvaragePoint - (int)this.fAvaragePoint;
            this.lessThanTen = false;
            if (this.bDecimal % 10 == 0) {
                this.bDecimal /= 10;
            }
            else if (this.bDecimal < 10) {
                this.lessThanTen = true;
            }
        }
        else {
            this.bDecimal = 0;
        }
    }
    
    @Override
    public void setVisible(final boolean isVisible) {
        if (isVisible) {
            if (this.iDescOfTurnID != 0) {
                this.updateSlider(0);
            }
            this.lTime = CFG.currentTimeMillis - 1L;
            this.updateMoveTurnTime();
        }
        else {
            this.lTime = 0L;
            this.iButtonsPosY = 0;
        }
        super.setVisible(isVisible);
        this.setHoveredID(-1);
    }
    
    protected final int getGraphWidth() {
        return this.getWidth() - getGraphButtonWidth() - CFG.PADDING;
    }
    
    public final int getMinPoint() {
        return (this.iMinPoint > 0) ? 0 : this.iMinPoint;
    }
    
    public final void updateMoveTurnTime() {
        this.lAuto_Move_Turn_Time = CFG.currentTimeMillis;
    }
    
    public final void incrementTurnDescInfo() {
        ++this.iDescOfTurnID;
        if (this.iDescOfTurnID >= this.iMaxSize) {
            this.iDescOfTurnID = 0;
        }
        this.updateDescInfo();
    }
    
    @Override
    public void setCheckboxState(final boolean checkboxState) {
        this.buildGraph();
        this.updateMoveable();
        this.updateButtonsInView();
    }
    
    static {
        GRAPH_BG_COLOR = new Color(0.17254902f, 0.14901961f, 0.13333334f, 1.0f);
        GRAPH_BORDERS_COLOR = new Color(0.078431375f, 0.11764706f, 0.17254902f, 1.0f);
        GRAPH_LINES_COLOR = new Color(0.9f, 0.9f, 0.9f, 0.1f);
        GRAPH_LINES_DESC = new Color(0.9f, 0.9f, 0.9f, 0.15f);
        GRAPH_LINE_COLOR = new Color(0.8235294f, 0.8235294f, 0.8235294f, 1.0f);
        TEXT_COLOR = new Color(0.9f, 0.9f, 0.9f, 1.0f);
        DATA_COLOR = new Color(0.6862745f, 0.6862745f, 0.6862745f, 1.0f);
        Graph.POINTS_TEXT_SCALE = 0.8f;
    }
    
    public enum GraphType
    {
        PLAYER_INCOME, 
        PLAYER_BALANCE, 
        PLAYER_POPULATION, 
        PLAYER_PRESTIGE;
    }
}
