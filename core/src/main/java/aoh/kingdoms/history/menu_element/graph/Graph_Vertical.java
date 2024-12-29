// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.graph;

import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.map.ResourcesManager;
import aoh.kingdoms.history.menusInGame.Goods.InGame_Goods_LargestProducers;
import aoh.kingdoms.history.map.civilization.CivilizationRanking;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.map.map.Continents;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.MenuElement_Type;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.menusInGame.Right.InGame_RightReligion;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ_Religion;
import aoh.kingdoms.history.menusInGame.Right.InGame_RightGovernment;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ_Government;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.Game;
import java.util.ArrayList;
import java.util.List;
import aoh.kingdoms.history.menu_element.MenuElement;

public class Graph_Vertical extends MenuElement
{
    public static List<Integer> graphCivs;
    private List<Graph_Vertical_Data> lValues;
    private int iValuesSize;
    private int iValuesTotal;
    private int iDataWidth;
    private Graph_Vertical_Info verticalInfo;
    private Graph_Vertical_Data_Type GRAPH_DATA_TYPE;
    public boolean splitBy100;
    public boolean drawShort;
    private boolean statisticsMode;
    private String sTextX;
    private String sTextY;
    private int iWidthTextY;
    private int iMinPoint;
    private int iMaxPoint;
    private float fAvaragePoint;
    private int iAvaragePosY;
    private byte bDecimal;
    private boolean lessThanTen;
    private String sTotal;
    private boolean moveable;
    private int iButtonsPosX;
    private int iButtonsPosY;
    private int iHoveredID;
    private boolean scrollModeY;
    private int iScrollPosX;
    private int iScrollPosX2;
    private float fScrollNewMenuPosY;
    private DrawStatisticsData drawStatisticsData;
    public boolean allowStatisticsMode;
    
    public Graph_Vertical(final Graph_Vertical_Data_Type nType, final String sTextX, final String sTextY, final int iPosX, final int iPosY, final int iWidth, final int iHeight, final boolean visible) {
        this.lValues = new ArrayList<Graph_Vertical_Data>();
        this.iValuesSize = 0;
        this.iValuesTotal = 0;
        this.iDataWidth = 0;
        this.splitBy100 = false;
        this.drawShort = false;
        this.statisticsMode = false;
        this.bDecimal = 0;
        this.lessThanTen = false;
        this.sTotal = "";
        this.moveable = false;
        this.iHoveredID = -1;
        this.scrollModeY = false;
        this.iScrollPosX = -1;
        this.iScrollPosX2 = -1;
        this.fScrollNewMenuPosY = 0.0f;
        this.allowStatisticsMode = true;
        this.GRAPH_DATA_TYPE = nType;
        this.sTotal = Game.lang.get("Total");
        if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.NUM_OF_PROVINCES_BY_CONTINENT) {
            for (int i = 1; i < Game.getCivsSize(); ++i) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    this.lValues.add(new Graph_Vertical_Data(i));
                }
            }
            this.iValuesSize = this.lValues.size();
            this.drawStatisticsData = new DrawStatisticsData() {
                @Override
                public void draw(final SpriteBatch oSB, final int i, final int tempOffsetX, final int iTranslateX, final int iTranslateY) {
                    for (int j = 0; j < Graph_Vertical.this.lValues.get(i).getValuesSize(); ++j) {
                        Graph_Vertical.this.drawStatisticsValue(oSB, "" + Graph_Vertical.this.lValues.get(i).getValue(j), Graph_Vertical.this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + Graph_Vertical.this.getStatisticsWidth() * 2 + Graph_Vertical.this.getStatisticsWidth() * Graph_Vertical.this.verticalInfo.getSortedID(Graph_Vertical.this.lValues.get(i).getValueDataTypeID(j)) + iTranslateX, Graph_Vertical.this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                    }
                    Graph_Vertical.this.drawStatisticsValue(oSB, "" + Graph_Vertical.this.lValues.get(i).getValue() + " [" + CFG.getPercentage(Graph_Vertical.this.lValues.get(i).getValue(), Graph_Vertical.this.iValuesTotal, 4) + "%]", Graph_Vertical.this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + tempOffsetX + iTranslateX, Graph_Vertical.this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                }
                
                @Override
                public String getTotal() {
                    return Graph_Vertical.this.sTotal + " [" + Graph_Vertical.this.iValuesTotal + "]";
                }
                
                @Override
                public String getStatsLP(final int i) {
                    return Game.getCiv(Graph_Vertical.this.lValues.get(i).getCivID()).getCivName();
                }
                
                @Override
                public int getStatsLPCivFlagID(final int i) {
                    return Graph_Vertical.this.lValues.get(i).getCivID();
                }
            };
        }
        else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_LIST_PROVINCES) {
            for (int i = Graph_Vertical.graphCivs.size() - 1; i >= 0; --i) {
                if (Graph_Vertical.graphCivs.get(i) > 0 && Graph_Vertical.graphCivs.get(i) < Game.getCivsSize() && Game.getCiv(Graph_Vertical.graphCivs.get(i)).getNumOfProvinces() > 0) {
                    this.lValues.add(new Graph_Vertical_Data(Graph_Vertical.graphCivs.get(i)));
                }
            }
            this.iValuesSize = this.lValues.size();
            this.drawShort = true;
            this.drawStatisticsData = new DrawStatisticsData() {
                @Override
                public void draw(final SpriteBatch oSB, final int i, final int tempOffsetX, final int iTranslateX, final int iTranslateY) {
                    for (int j = 0; j < Graph_Vertical.this.lValues.get(i).getValuesSize(); ++j) {
                        Graph_Vertical.this.drawStatisticsValue(oSB, "" + CFG.getNumberWithSpaces("" + Graph_Vertical.this.lValues.get(i).getValue(j)), Graph_Vertical.this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + Graph_Vertical.this.getStatisticsWidth() * 2 + Graph_Vertical.this.getStatisticsWidth() * Graph_Vertical.this.verticalInfo.getSortedID(Graph_Vertical.this.lValues.get(i).getValueDataTypeID(j)) + iTranslateX, Graph_Vertical.this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                    }
                    Graph_Vertical.this.drawStatisticsValue(oSB, "" + CFG.getShortNumber(Graph_Vertical.this.lValues.get(i).getValue()) + " [" + CFG.getPercentage(Graph_Vertical.this.lValues.get(i).getValue(), Graph_Vertical.this.iValuesTotal, 3) + "%]", Graph_Vertical.this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + tempOffsetX + iTranslateX, Graph_Vertical.this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                }
                
                @Override
                public String getTotal() {
                    return Graph_Vertical.this.sTotal + " [" + CFG.getShortNumber(Math.max(1, Graph_Vertical.this.iValuesTotal)) + "]";
                }
                
                @Override
                public String getStatsLP(final int i) {
                    return Game.getCiv(Graph_Vertical.this.lValues.get(i).getCivID()).getCivName();
                }
                
                @Override
                public int getStatsLPCivFlagID(final int i) {
                    return Graph_Vertical.this.lValues.get(i).getCivID();
                }
            };
        }
        else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_LIST_POPULATION) {
            for (int i = Graph_Vertical.graphCivs.size() - 1; i >= 0; --i) {
                if (Graph_Vertical.graphCivs.get(i) > 0 && Graph_Vertical.graphCivs.get(i) < Game.getCivsSize() && Game.getCiv(Graph_Vertical.graphCivs.get(i)).getNumOfProvinces() > 0) {
                    this.lValues.add(new Graph_Vertical_Data(Graph_Vertical.graphCivs.get(i)));
                }
            }
            this.iValuesSize = this.lValues.size();
            this.drawShort = true;
            this.drawStatisticsData = new DrawStatisticsData() {
                @Override
                public void draw(final SpriteBatch oSB, final int i, final int tempOffsetX, final int iTranslateX, final int iTranslateY) {
                    for (int j = 0; j < Graph_Vertical.this.lValues.get(i).getValuesSize(); ++j) {
                        Graph_Vertical.this.drawStatisticsValue(oSB, "" + CFG.getNumberWithSpaces("" + Graph_Vertical.this.lValues.get(i).getValue(j)), Graph_Vertical.this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + Graph_Vertical.this.getStatisticsWidth() * 2 + Graph_Vertical.this.getStatisticsWidth() * Graph_Vertical.this.verticalInfo.getSortedID(Graph_Vertical.this.lValues.get(i).getValueDataTypeID(j)) + iTranslateX, Graph_Vertical.this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                    }
                    Graph_Vertical.this.drawStatisticsValue(oSB, "" + CFG.getShortNumber(Graph_Vertical.this.lValues.get(i).getValue()) + " [" + CFG.getPercentage(Graph_Vertical.this.lValues.get(i).getValue(), Graph_Vertical.this.iValuesTotal, 3) + "%]", Graph_Vertical.this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + tempOffsetX + iTranslateX, Graph_Vertical.this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                }
                
                @Override
                public String getTotal() {
                    return Graph_Vertical.this.sTotal + " [" + CFG.getShortNumber(Math.max(1, Graph_Vertical.this.iValuesTotal)) + "]";
                }
                
                @Override
                public String getStatsLP(final int i) {
                    return Game.getCiv(Graph_Vertical.this.lValues.get(i).getCivID()).getCivName();
                }
                
                @Override
                public int getStatsLPCivFlagID(final int i) {
                    return Graph_Vertical.this.lValues.get(i).getCivID();
                }
            };
        }
        else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_POPULATION) {
            for (int i = 1; i < Game.getCivsSize(); ++i) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    this.lValues.add(new Graph_Vertical_Data(i));
                }
            }
            this.iValuesSize = this.lValues.size();
            this.drawShort = true;
            this.drawStatisticsData = new DrawStatisticsData() {
                @Override
                public void draw(final SpriteBatch oSB, final int i, final int tempOffsetX, final int iTranslateX, final int iTranslateY) {
                    for (int j = 0; j < Graph_Vertical.this.lValues.get(i).getValuesSize(); ++j) {
                        Graph_Vertical.this.drawStatisticsValue(oSB, "" + CFG.getNumberWithSpaces("" + Graph_Vertical.this.lValues.get(i).getValue(j)), Graph_Vertical.this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + Graph_Vertical.this.getStatisticsWidth() * 2 + Graph_Vertical.this.getStatisticsWidth() * Graph_Vertical.this.verticalInfo.getSortedID(Graph_Vertical.this.lValues.get(i).getValueDataTypeID(j)) + iTranslateX, Graph_Vertical.this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                    }
                    Graph_Vertical.this.drawStatisticsValue(oSB, "" + CFG.getShortNumber(Graph_Vertical.this.lValues.get(i).getValue()) + " [" + CFG.getPercentage(Graph_Vertical.this.lValues.get(i).getValue(), Graph_Vertical.this.iValuesTotal, 3) + "%]", Graph_Vertical.this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + tempOffsetX + iTranslateX, Graph_Vertical.this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                }
                
                @Override
                public String getTotal() {
                    return Graph_Vertical.this.sTotal + " [" + CFG.getShortNumber(Math.max(1, Graph_Vertical.this.iValuesTotal)) + "]";
                }
                
                @Override
                public String getStatsLP(final int i) {
                    return Game.getCiv(Graph_Vertical.this.lValues.get(i).getCivID()).getCivName();
                }
                
                @Override
                public int getStatsLPCivFlagID(final int i) {
                    return Graph_Vertical.this.lValues.get(i).getCivID();
                }
            };
        }
        else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.GOVERNMENTS_CIVS) {
            for (int i = 1; i < Game.getCivsSize(); ++i) {
                if (Game.getCiv(i).getNumOfProvinces() > 0 && Game.getCiv(i).getIdeologyID() == InGame_Civ_Government.iGovID) {
                    this.lValues.add(new Graph_Vertical_Data(i));
                }
            }
            this.iValuesSize = this.lValues.size();
            this.drawShort = true;
            this.drawStatisticsData = new DrawStatisticsData() {
                @Override
                public void draw(final SpriteBatch oSB, final int i, final int tempOffsetX, final int iTranslateX, final int iTranslateY) {
                    for (int j = 0; j < Graph_Vertical.this.lValues.get(i).getValuesSize(); ++j) {
                        Graph_Vertical.this.drawStatisticsValue(oSB, "" + CFG.getNumberWithSpaces("" + Graph_Vertical.this.lValues.get(i).getValue(j)), Graph_Vertical.this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + Graph_Vertical.this.getStatisticsWidth() * 2 + Graph_Vertical.this.getStatisticsWidth() * Graph_Vertical.this.verticalInfo.getSortedID(Graph_Vertical.this.lValues.get(i).getValueDataTypeID(j)) + iTranslateX, Graph_Vertical.this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                    }
                    Graph_Vertical.this.drawStatisticsValue(oSB, "" + CFG.getShortNumber(Graph_Vertical.this.lValues.get(i).getValue()) + " [" + CFG.getPercentage(Graph_Vertical.this.lValues.get(i).getValue(), Graph_Vertical.this.iValuesTotal, 3) + "%]", Graph_Vertical.this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + tempOffsetX + iTranslateX, Graph_Vertical.this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                }
                
                @Override
                public String getTotal() {
                    return Graph_Vertical.this.sTotal + " [" + CFG.getShortNumber(Math.max(1, Graph_Vertical.this.iValuesTotal)) + "]";
                }
                
                @Override
                public String getStatsLP(final int i) {
                    return Game.getCiv(Graph_Vertical.this.lValues.get(i).getCivID()).getCivName();
                }
                
                @Override
                public int getStatsLPCivFlagID(final int i) {
                    return Graph_Vertical.this.lValues.get(i).getCivID();
                }
            };
        }
        else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.GOVERNMENTS_CIVS_RIGHT) {
            for (int i = 1; i < Game.getCivsSize(); ++i) {
                if (Game.getCiv(i).getNumOfProvinces() > 0 && Game.getCiv(i).getIdeologyID() == InGame_RightGovernment.iGovID) {
                    this.lValues.add(new Graph_Vertical_Data(i));
                }
            }
            this.iValuesSize = this.lValues.size();
            this.drawShort = true;
            this.drawStatisticsData = new DrawStatisticsData() {
                @Override
                public void draw(final SpriteBatch oSB, final int i, final int tempOffsetX, final int iTranslateX, final int iTranslateY) {
                    for (int j = 0; j < Graph_Vertical.this.lValues.get(i).getValuesSize(); ++j) {
                        Graph_Vertical.this.drawStatisticsValue(oSB, "" + CFG.getNumberWithSpaces("" + Graph_Vertical.this.lValues.get(i).getValue(j)), Graph_Vertical.this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + Graph_Vertical.this.getStatisticsWidth() * 2 + Graph_Vertical.this.getStatisticsWidth() * Graph_Vertical.this.verticalInfo.getSortedID(Graph_Vertical.this.lValues.get(i).getValueDataTypeID(j)) + iTranslateX, Graph_Vertical.this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                    }
                    Graph_Vertical.this.drawStatisticsValue(oSB, "" + CFG.getShortNumber(Graph_Vertical.this.lValues.get(i).getValue()) + " [" + CFG.getPercentage(Graph_Vertical.this.lValues.get(i).getValue(), Graph_Vertical.this.iValuesTotal, 3) + "%]", Graph_Vertical.this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + tempOffsetX + iTranslateX, Graph_Vertical.this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                }
                
                @Override
                public String getTotal() {
                    return Graph_Vertical.this.sTotal + " [" + CFG.getShortNumber(Math.max(1, Graph_Vertical.this.iValuesTotal)) + "]";
                }
                
                @Override
                public String getStatsLP(final int i) {
                    return Game.getCiv(Graph_Vertical.this.lValues.get(i).getCivID()).getCivName();
                }
                
                @Override
                public int getStatsLPCivFlagID(final int i) {
                    return Graph_Vertical.this.lValues.get(i).getCivID();
                }
            };
        }
        else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.RELIGION_CIVS) {
            for (int i = 1; i < Game.getCivsSize(); ++i) {
                for (int j = 0; j < Game.getCiv(i).getNumOfProvinces(); ++j) {
                    if (Game.getProvince(Game.getCiv(i).getProvinceID(j)).getReligion() == InGame_Civ_Religion.iReligionID) {
                        this.lValues.add(new Graph_Vertical_Data(i));
                        break;
                    }
                }
            }
            this.iValuesSize = this.lValues.size();
            this.drawShort = true;
            this.drawStatisticsData = new DrawStatisticsData() {
                @Override
                public void draw(final SpriteBatch oSB, final int i, final int tempOffsetX, final int iTranslateX, final int iTranslateY) {
                    for (int j = 0; j < Graph_Vertical.this.lValues.get(i).getValuesSize(); ++j) {
                        Graph_Vertical.this.drawStatisticsValue(oSB, "" + CFG.getNumberWithSpaces("" + Graph_Vertical.this.lValues.get(i).getValue(j)), Graph_Vertical.this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + Graph_Vertical.this.getStatisticsWidth() * 2 + Graph_Vertical.this.getStatisticsWidth() * Graph_Vertical.this.verticalInfo.getSortedID(Graph_Vertical.this.lValues.get(i).getValueDataTypeID(j)) + iTranslateX, Graph_Vertical.this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                    }
                    Graph_Vertical.this.drawStatisticsValue(oSB, "" + CFG.getShortNumber(Graph_Vertical.this.lValues.get(i).getValue()) + " [" + CFG.getPercentage(Graph_Vertical.this.lValues.get(i).getValue(), Graph_Vertical.this.iValuesTotal, 3) + "%]", Graph_Vertical.this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + tempOffsetX + iTranslateX, Graph_Vertical.this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                }
                
                @Override
                public String getTotal() {
                    return Graph_Vertical.this.sTotal + " [" + CFG.getShortNumber(Math.max(1, Graph_Vertical.this.iValuesTotal)) + "]";
                }
                
                @Override
                public String getStatsLP(final int i) {
                    return Game.getCiv(Graph_Vertical.this.lValues.get(i).getCivID()).getCivName();
                }
                
                @Override
                public int getStatsLPCivFlagID(final int i) {
                    return Graph_Vertical.this.lValues.get(i).getCivID();
                }
            };
        }
        else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.RELIGION_CIVS_RIGHT) {
            for (int i = 1; i < Game.getCivsSize(); ++i) {
                for (int j = 0; j < Game.getCiv(i).getNumOfProvinces(); ++j) {
                    if (Game.getProvince(Game.getCiv(i).getProvinceID(j)).getReligion() == InGame_RightReligion.iReligionID) {
                        this.lValues.add(new Graph_Vertical_Data(i));
                        break;
                    }
                }
            }
            this.iValuesSize = this.lValues.size();
            this.drawShort = true;
            this.drawStatisticsData = new DrawStatisticsData() {
                @Override
                public void draw(final SpriteBatch oSB, final int i, final int tempOffsetX, final int iTranslateX, final int iTranslateY) {
                    for (int j = 0; j < Graph_Vertical.this.lValues.get(i).getValuesSize(); ++j) {
                        Graph_Vertical.this.drawStatisticsValue(oSB, "" + CFG.getNumberWithSpaces("" + Graph_Vertical.this.lValues.get(i).getValue(j)), Graph_Vertical.this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + Graph_Vertical.this.getStatisticsWidth() * 2 + Graph_Vertical.this.getStatisticsWidth() * Graph_Vertical.this.verticalInfo.getSortedID(Graph_Vertical.this.lValues.get(i).getValueDataTypeID(j)) + iTranslateX, Graph_Vertical.this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                    }
                    Graph_Vertical.this.drawStatisticsValue(oSB, "" + CFG.getShortNumber(Graph_Vertical.this.lValues.get(i).getValue()) + " [" + CFG.getPercentage(Graph_Vertical.this.lValues.get(i).getValue(), Graph_Vertical.this.iValuesTotal, 3) + "%]", Graph_Vertical.this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + tempOffsetX + iTranslateX, Graph_Vertical.this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                }
                
                @Override
                public String getTotal() {
                    return Graph_Vertical.this.sTotal + " [" + CFG.getShortNumber(Math.max(1, Graph_Vertical.this.iValuesTotal)) + "]";
                }
                
                @Override
                public String getStatsLP(final int i) {
                    return Game.getCiv(Graph_Vertical.this.lValues.get(i).getCivID()).getCivName();
                }
                
                @Override
                public int getStatsLPCivFlagID(final int i) {
                    return Graph_Vertical.this.lValues.get(i).getCivID();
                }
            };
        }
        else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_INFRASTRUCTURE) {
            for (int i = 1; i < Game.getCivsSize(); ++i) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    this.lValues.add(new Graph_Vertical_Data(i));
                }
            }
            this.iValuesSize = this.lValues.size();
            this.drawShort = true;
            this.drawStatisticsData = new DrawStatisticsData() {
                @Override
                public void draw(final SpriteBatch oSB, final int i, final int tempOffsetX, final int iTranslateX, final int iTranslateY) {
                    for (int j = 0; j < Graph_Vertical.this.lValues.get(i).getValuesSize(); ++j) {
                        Graph_Vertical.this.drawStatisticsValue(oSB, "" + Graph_Vertical.this.lValues.get(i).getValue(j), Graph_Vertical.this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + Graph_Vertical.this.getStatisticsWidth() * 2 + Graph_Vertical.this.getStatisticsWidth() * Graph_Vertical.this.verticalInfo.getSortedID(Graph_Vertical.this.lValues.get(i).getValueDataTypeID(j)) + iTranslateX, Graph_Vertical.this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                    }
                    Graph_Vertical.this.drawStatisticsValue(oSB, "" + Graph_Vertical.this.lValues.get(i).getValue() + " [" + CFG.getPercentage(Graph_Vertical.this.lValues.get(i).getValue(), Graph_Vertical.this.iValuesTotal, 3) + "%]", Graph_Vertical.this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + tempOffsetX + iTranslateX, Graph_Vertical.this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                }
                
                @Override
                public String getTotal() {
                    return Graph_Vertical.this.sTotal + " [" + Graph_Vertical.this.iValuesTotal + "]";
                }
                
                @Override
                public String getStatsLP(final int i) {
                    return Game.getCiv(Graph_Vertical.this.lValues.get(i).getCivID()).getCivName();
                }
                
                @Override
                public int getStatsLPCivFlagID(final int i) {
                    return Graph_Vertical.this.lValues.get(i).getCivID();
                }
            };
        }
        else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_CONSTRUCTED_BUILDINGS) {
            for (int i = 1; i < Game.getCivsSize(); ++i) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    this.lValues.add(new Graph_Vertical_Data(i));
                }
            }
            this.iValuesSize = this.lValues.size();
            this.drawShort = true;
            this.drawStatisticsData = new DrawStatisticsData() {
                @Override
                public void draw(final SpriteBatch oSB, final int i, final int tempOffsetX, final int iTranslateX, final int iTranslateY) {
                    for (int j = 0; j < Graph_Vertical.this.lValues.get(i).getValuesSize(); ++j) {
                        Graph_Vertical.this.drawStatisticsValue(oSB, "" + Graph_Vertical.this.lValues.get(i).getValue(j), Graph_Vertical.this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + Graph_Vertical.this.getStatisticsWidth() * 2 + Graph_Vertical.this.getStatisticsWidth() * Graph_Vertical.this.verticalInfo.getSortedID(Graph_Vertical.this.lValues.get(i).getValueDataTypeID(j)) + iTranslateX, Graph_Vertical.this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                    }
                    Graph_Vertical.this.drawStatisticsValue(oSB, "" + Graph_Vertical.this.lValues.get(i).getValue() + " [" + CFG.getPercentage(Graph_Vertical.this.lValues.get(i).getValue(), Graph_Vertical.this.iValuesTotal, 3) + "%]", Graph_Vertical.this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + tempOffsetX + iTranslateX, Graph_Vertical.this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                }
                
                @Override
                public String getTotal() {
                    return Graph_Vertical.this.sTotal + " [" + Graph_Vertical.this.iValuesTotal + "]";
                }
                
                @Override
                public String getStatsLP(final int i) {
                    return Game.getCiv(Graph_Vertical.this.lValues.get(i).getCivID()).getCivName();
                }
                
                @Override
                public int getStatsLPCivFlagID(final int i) {
                    return Graph_Vertical.this.lValues.get(i).getCivID();
                }
            };
        }
        else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_ECONOMY) {
            for (int i = 1; i < Game.getCivsSize(); ++i) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    this.lValues.add(new Graph_Vertical_Data(i));
                }
            }
            this.iValuesSize = this.lValues.size();
            this.drawShort = true;
            this.drawStatisticsData = new DrawStatisticsData() {
                @Override
                public void draw(final SpriteBatch oSB, final int i, final int tempOffsetX, final int iTranslateX, final int iTranslateY) {
                    for (int j = 0; j < Graph_Vertical.this.lValues.get(i).getValuesSize(); ++j) {
                        Graph_Vertical.this.drawStatisticsValue(oSB, "" + Graph_Vertical.this.lValues.get(i).getValue(j), Graph_Vertical.this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + Graph_Vertical.this.getStatisticsWidth() * 2 + Graph_Vertical.this.getStatisticsWidth() * Graph_Vertical.this.verticalInfo.getSortedID(Graph_Vertical.this.lValues.get(i).getValueDataTypeID(j)) + iTranslateX, Graph_Vertical.this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                    }
                    Graph_Vertical.this.drawStatisticsValue(oSB, "" + Graph_Vertical.this.lValues.get(i).getValue() + " [" + CFG.getPercentage(Graph_Vertical.this.lValues.get(i).getValue(), Graph_Vertical.this.iValuesTotal, 3) + "%]", Graph_Vertical.this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + tempOffsetX + iTranslateX, Graph_Vertical.this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                }
                
                @Override
                public String getTotal() {
                    return Graph_Vertical.this.sTotal + " [" + Graph_Vertical.this.iValuesTotal + "]";
                }
                
                @Override
                public String getStatsLP(final int i) {
                    return Game.getCiv(Graph_Vertical.this.lValues.get(i).getCivID()).getCivName();
                }
                
                @Override
                public int getStatsLPCivFlagID(final int i) {
                    return Graph_Vertical.this.lValues.get(i).getCivID();
                }
            };
        }
        else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_UNLOCKED_TECHS) {
            for (int i = 1; i < Game.getCivsSize(); ++i) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    this.lValues.add(new Graph_Vertical_Data(i));
                }
            }
            this.iValuesSize = this.lValues.size();
            this.drawShort = true;
            this.drawStatisticsData = new DrawStatisticsData() {
                @Override
                public void draw(final SpriteBatch oSB, final int i, final int tempOffsetX, final int iTranslateX, final int iTranslateY) {
                    for (int j = 0; j < Graph_Vertical.this.lValues.get(i).getValuesSize(); ++j) {
                        Graph_Vertical.this.drawStatisticsValue(oSB, "" + Graph_Vertical.this.lValues.get(i).getValue(j), Graph_Vertical.this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + Graph_Vertical.this.getStatisticsWidth() * 2 + Graph_Vertical.this.getStatisticsWidth() * Graph_Vertical.this.verticalInfo.getSortedID(Graph_Vertical.this.lValues.get(i).getValueDataTypeID(j)) + iTranslateX, Graph_Vertical.this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                    }
                    Graph_Vertical.this.drawStatisticsValue(oSB, "" + Graph_Vertical.this.lValues.get(i).getValue(), Graph_Vertical.this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + tempOffsetX + iTranslateX, Graph_Vertical.this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                }
                
                @Override
                public String getTotal() {
                    return Graph_Vertical.this.sTotal + " [" + Graph_Vertical.this.iValuesTotal + "]";
                }
                
                @Override
                public String getStatsLP(final int i) {
                    return Game.getCiv(Graph_Vertical.this.lValues.get(i).getCivID()).getCivName();
                }
                
                @Override
                public int getStatsLPCivFlagID(final int i) {
                    return Graph_Vertical.this.lValues.get(i).getCivID();
                }
            };
        }
        else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_PRESTIGE) {
            for (int i = 1; i < Game.getCivsSize(); ++i) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    this.lValues.add(new Graph_Vertical_Data(i));
                }
            }
            this.iValuesSize = this.lValues.size();
            this.drawShort = true;
            this.drawStatisticsData = new DrawStatisticsData() {
                @Override
                public void draw(final SpriteBatch oSB, final int i, final int tempOffsetX, final int iTranslateX, final int iTranslateY) {
                    for (int j = 0; j < Graph_Vertical.this.lValues.get(i).getValuesSize(); ++j) {
                        Graph_Vertical.this.drawStatisticsValue(oSB, "" + Graph_Vertical.this.lValues.get(i).getValue(j), Graph_Vertical.this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + Graph_Vertical.this.getStatisticsWidth() * 2 + Graph_Vertical.this.getStatisticsWidth() * Graph_Vertical.this.verticalInfo.getSortedID(Graph_Vertical.this.lValues.get(i).getValueDataTypeID(j)) + iTranslateX, Graph_Vertical.this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                    }
                    Graph_Vertical.this.drawStatisticsValue(oSB, "" + Graph_Vertical.this.lValues.get(i).getValue(), Graph_Vertical.this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + tempOffsetX + iTranslateX, Graph_Vertical.this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                }
                
                @Override
                public String getTotal() {
                    return Graph_Vertical.this.sTotal + " [" + Graph_Vertical.this.iValuesTotal + "]";
                }
                
                @Override
                public String getStatsLP(final int i) {
                    return Game.getCiv(Graph_Vertical.this.lValues.get(i).getCivID()).getCivName();
                }
                
                @Override
                public int getStatsLPCivFlagID(final int i) {
                    return Graph_Vertical.this.lValues.get(i).getCivID();
                }
            };
        }
        else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.RESOURCE_PRODUCTION) {
            for (int i = 1; i < Game.getCivsSize(); ++i) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    this.lValues.add(new Graph_Vertical_Data(i));
                }
            }
            this.iValuesSize = this.lValues.size();
            this.drawShort = true;
            this.drawStatisticsData = new DrawStatisticsData() {
                @Override
                public void draw(final SpriteBatch oSB, final int i, final int tempOffsetX, final int iTranslateX, final int iTranslateY) {
                    for (int j = 0; j < Graph_Vertical.this.lValues.get(i).getValuesSize(); ++j) {
                        Graph_Vertical.this.drawStatisticsValue(oSB, "" + Graph_Vertical.this.lValues.get(i).getValue(j), Graph_Vertical.this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + Graph_Vertical.this.getStatisticsWidth() * 2 + Graph_Vertical.this.getStatisticsWidth() * Graph_Vertical.this.verticalInfo.getSortedID(Graph_Vertical.this.lValues.get(i).getValueDataTypeID(j)) + iTranslateX, Graph_Vertical.this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                    }
                    Graph_Vertical.this.drawStatisticsValue(oSB, "" + Graph_Vertical.this.lValues.get(i).getValue(), Graph_Vertical.this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + tempOffsetX + iTranslateX, Graph_Vertical.this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                }
                
                @Override
                public String getTotal() {
                    return Graph_Vertical.this.sTotal + " [" + Graph_Vertical.this.iValuesTotal + "]";
                }
                
                @Override
                public String getStatsLP(final int i) {
                    return Game.getCiv(Graph_Vertical.this.lValues.get(i).getCivID()).getCivName();
                }
                
                @Override
                public int getStatsLPCivFlagID(final int i) {
                    return Graph_Vertical.this.lValues.get(i).getCivID();
                }
            };
        }
        else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_REGIMENTS_LIMIT) {
            for (int i = 1; i < Game.getCivsSize(); ++i) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    this.lValues.add(new Graph_Vertical_Data(i));
                }
            }
            this.iValuesSize = this.lValues.size();
            this.drawShort = true;
            this.drawStatisticsData = new DrawStatisticsData() {
                @Override
                public void draw(final SpriteBatch oSB, final int i, final int tempOffsetX, final int iTranslateX, final int iTranslateY) {
                    for (int j = 0; j < Graph_Vertical.this.lValues.get(i).getValuesSize(); ++j) {
                        Graph_Vertical.this.drawStatisticsValue(oSB, "" + Graph_Vertical.this.lValues.get(i).getValue(j), Graph_Vertical.this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + Graph_Vertical.this.getStatisticsWidth() * 2 + Graph_Vertical.this.getStatisticsWidth() * Graph_Vertical.this.verticalInfo.getSortedID(Graph_Vertical.this.lValues.get(i).getValueDataTypeID(j)) + iTranslateX, Graph_Vertical.this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                    }
                    Graph_Vertical.this.drawStatisticsValue(oSB, "" + Graph_Vertical.this.lValues.get(i).getValue(), Graph_Vertical.this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + tempOffsetX + iTranslateX, Graph_Vertical.this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                }
                
                @Override
                public String getTotal() {
                    return Graph_Vertical.this.sTotal + " [" + Graph_Vertical.this.iValuesTotal + "]";
                }
                
                @Override
                public String getStatsLP(final int i) {
                    return Game.getCiv(Graph_Vertical.this.lValues.get(i).getCivID()).getCivName();
                }
                
                @Override
                public int getStatsLPCivFlagID(final int i) {
                    return Graph_Vertical.this.lValues.get(i).getCivID();
                }
            };
        }
        this.iDataWidth = CFG.CIV_FLAG_WIDTH;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidth(iWidth);
        this.setHeight(iHeight);
        this.setVisible(visible);
        this.sTextX = sTextX;
        this.sTextY = sTextY;
        Renderer.fontMain.get(0).getData().setScale(0.7f);
        Renderer.glyphLayout.setText(Renderer.fontMain.get(0), sTextY);
        this.iWidthTextY = (int)Renderer.glyphLayout.width;
        Renderer.fontMain.get(0).getData().setScale(1.0f);
        this.buildData();
        this.buildValuesHeights();
        this.typeOfElement = MenuElement_Type.GRAPH_VERTICAL;
    }
    
    public final void buildData() {
        if (this.lValues.size() == 0) {
            return;
        }
        final List<String> nTexts = new ArrayList<String>();
        final List<Color> nColors = new ArrayList<Color>();
        if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.NUM_OF_PROVINCES_BY_CONTINENT) {
            for (int i = 0; i < this.iValuesSize; ++i) {
                this.lValues.get(i).buildContinentData();
            }
            for (int i = 1; i < Game.continents.iContinentsSize; ++i) {
                nTexts.add(Game.continents.lContinents.get(i).sName);
                nColors.add(new Color(Game.continents.lContinents.get(i).iR / 255.0f, Game.continents.lContinents.get(i).iG / 255.0f, Game.continents.lContinents.get(i).iB / 255.0f, 1.0f));
            }
        }
        else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_POPULATION) {
            for (int i = 0; i < this.iValuesSize; ++i) {
                this.lValues.get(i).buildContinentData_Population();
            }
            for (int i = 1; i < Game.continents.iContinentsSize; ++i) {
                nTexts.add(Game.continents.lContinents.get(i).sName);
                nColors.add(new Color(Game.continents.lContinents.get(i).iR / 255.0f, Game.continents.lContinents.get(i).iG / 255.0f, Game.continents.lContinents.get(i).iB / 255.0f, 1.0f));
            }
        }
        else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_LIST_PROVINCES) {
            for (int i = 0; i < this.iValuesSize; ++i) {
                this.lValues.get(i).buildContinentData_CivsProvinces();
            }
            nTexts.add(Game.lang.get("Provinces"));
            nColors.add(Colors.HOVER_GOLD);
        }
        else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_LIST_POPULATION) {
            for (int i = 0; i < this.iValuesSize; ++i) {
                this.lValues.get(i).buildContinentData_CivsPopulation();
            }
            nTexts.add(Game.lang.get("Population"));
            nColors.add(Colors.COLOR_POPULATION);
        }
        else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.GOVERNMENTS_CIVS) {
            for (int i = 0; i < this.iValuesSize; ++i) {
                this.lValues.get(i).buildContinentData_GovernmentCivs();
            }
            nTexts.add(Game.lang.get("Population"));
            nColors.add(new Color(Game.ideologiesManager.getIdeology(InGame_Civ_Government.iGovID).Color[0], Game.ideologiesManager.getIdeology(InGame_Civ_Government.iGovID).Color[1], Game.ideologiesManager.getIdeology(InGame_Civ_Government.iGovID).Color[2], 1.0f));
        }
        else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.GOVERNMENTS_CIVS_RIGHT) {
            for (int i = 0; i < this.iValuesSize; ++i) {
                this.lValues.get(i).buildContinentData_GovernmentCivs();
            }
            nTexts.add(Game.lang.get("Population"));
            nColors.add(new Color(Game.ideologiesManager.getIdeology(InGame_RightGovernment.iGovID).Color[0], Game.ideologiesManager.getIdeology(InGame_RightGovernment.iGovID).Color[1], Game.ideologiesManager.getIdeology(InGame_RightGovernment.iGovID).Color[2], 1.0f));
        }
        else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.RELIGION_CIVS) {
            for (int i = 0; i < this.iValuesSize; ++i) {
                this.lValues.get(i).buildContinentData_ReligionCivs();
            }
            nTexts.add(Game.lang.get("Population"));
            nColors.add(new Color(Game.religionManager.getReligion(InGame_Civ_Religion.iReligionID).Color[0], Game.religionManager.getReligion(InGame_Civ_Religion.iReligionID).Color[1], Game.religionManager.getReligion(InGame_Civ_Religion.iReligionID).Color[2], 1.0f));
        }
        else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.RELIGION_CIVS_RIGHT) {
            for (int i = 0; i < this.iValuesSize; ++i) {
                this.lValues.get(i).buildContinentData_ReligionCivs_Right();
            }
            nTexts.add(Game.lang.get("Population"));
            nColors.add(new Color(Game.religionManager.getReligion(InGame_RightReligion.iReligionID).Color[0], Game.religionManager.getReligion(InGame_RightReligion.iReligionID).Color[1], Game.religionManager.getReligion(InGame_RightReligion.iReligionID).Color[2], 1.0f));
        }
        else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_CONSTRUCTED_BUILDINGS) {
            for (int i = 0; i < this.iValuesSize; ++i) {
                this.lValues.get(i).buildContinentData_ConstructedBuildings();
            }
            for (int i = 1; i < Game.continents.iContinentsSize; ++i) {
                nTexts.add(Game.continents.lContinents.get(i).sName);
                nColors.add(new Color(Game.continents.lContinents.get(i).iR / 255.0f, Game.continents.lContinents.get(i).iG / 255.0f, Game.continents.lContinents.get(i).iB / 255.0f, 1.0f));
            }
        }
        else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_INFRASTRUCTURE) {
            for (int i = 0; i < this.iValuesSize; ++i) {
                this.lValues.get(i).buildContinentData_Infrastructure();
            }
            for (int i = 1; i < Game.continents.iContinentsSize; ++i) {
                nTexts.add(Game.continents.lContinents.get(i).sName);
                nColors.add(new Color(Game.continents.lContinents.get(i).iR / 255.0f, Game.continents.lContinents.get(i).iG / 255.0f, Game.continents.lContinents.get(i).iB / 255.0f, 1.0f));
            }
        }
        else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_ECONOMY) {
            for (int i = 0; i < this.iValuesSize; ++i) {
                this.lValues.get(i).buildContinentData_Economy();
            }
            for (int i = 1; i < Game.continents.iContinentsSize; ++i) {
                nTexts.add(Game.continents.lContinents.get(i).sName);
                nColors.add(new Color(Game.continents.lContinents.get(i).iR / 255.0f, Game.continents.lContinents.get(i).iG / 255.0f, Game.continents.lContinents.get(i).iB / 255.0f, 1.0f));
            }
        }
        else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_UNLOCKED_TECHS) {
            for (int i = 0; i < this.iValuesSize; ++i) {
                this.lValues.get(i).buildContinentData_UnlockedTechnologies();
            }
            nTexts.add(Game.lang.get("UnlockedTechnologies"));
            nColors.add(Colors.HOVER_GOLD);
        }
        else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_PRESTIGE) {
            for (int i = 0; i < this.iValuesSize; ++i) {
                this.lValues.get(i).buildContinentData_Prestige();
            }
            nTexts.add(Game.lang.get("Prestige"));
            nColors.add(Colors.TECH_BLUE2);
        }
        else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.RESOURCE_PRODUCTION) {
            for (int i = 0; i < this.iValuesSize; ++i) {
                this.lValues.get(i).buildContinentData_ResourceProduction();
            }
            nTexts.add(Game.lang.get("Production"));
            nColors.add(Colors.TECH_BLUE2);
        }
        else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_REGIMENTS_LIMIT) {
            for (int i = 0; i < this.iValuesSize; ++i) {
                this.lValues.get(i).buildContinentData_RegimentsLimit();
            }
            nTexts.add(Game.lang.get("RegimentsLimit"));
            nColors.add(Colors.TECH_BLUE);
        }
        this.verticalInfo = new Graph_Vertical_Info(nTexts, nColors, this.getWidth() - ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2), true);
        final List<Graph_Vertical_Data> tempData = new ArrayList<Graph_Vertical_Data>();
        for (int j = 0; j < this.iValuesSize; ++j) {
            tempData.add(this.lValues.get(j));
        }
        this.lValues.clear();
        while (tempData.size() > 0) {
            int tempMaxID = 0;
            for (int k = 1; k < tempData.size(); ++k) {
                if (tempData.get(k).getValue() > tempData.get(tempMaxID).getValue()) {
                    tempMaxID = k;
                }
            }
            this.lValues.add(tempData.get(tempMaxID));
            tempData.remove(tempMaxID);
        }
        try {
            final int value = this.lValues.get(0).getValue();
            this.iMaxPoint = value;
            this.iMinPoint = value;
        }
        catch (final IndexOutOfBoundsException ex) {
            this.iMinPoint = 0;
        }
        this.fAvaragePoint = 0.0f;
        long tempAvarage = 0L;
        int tempAvarageSize = 0;
        for (int l = 0; l < this.iValuesSize; ++l) {
            if (this.iMaxPoint < this.lValues.get(l).getValue()) {
                this.iMaxPoint = this.lValues.get(l).getValue();
            }
            if (this.iMinPoint > this.lValues.get(l).getValue()) {
                this.iMinPoint = this.lValues.get(l).getValue();
            }
            if (this.lValues.get(l).getValue() > 0) {
                ++tempAvarageSize;
                tempAvarage += this.lValues.get(l).getValue();
            }
        }
        this.fAvaragePoint = tempAvarage / (float)tempAvarageSize;
        this.iAvaragePosY = (int)(this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2 - (this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2) * (100.0f * this.fAvaragePoint) / (this.iMaxPoint - this.iMinPoint) / 100.0f);
        this.roundAverage();
        this.updateMoveable();
        this.updateInView();
        this.countValuesTotal();
    }
    
    public final void countValuesTotal() {
        this.iValuesTotal = 0;
        for (int i = 0; i < this.iValuesSize; ++i) {
            this.iValuesTotal += this.lValues.get(i).getValue();
        }
    }
    
    public final void buildValuesHeights() {
        for (int i = 0; i < this.iValuesSize; ++i) {
            this.lValues.get(i).buildHeights(this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2 - (CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2), this.iMaxPoint);
        }
    }
    
    @Override
    public void updateHover(final int nPosX, final int nPosY, final int menuPosX, final int menuPosY) {
        if (!this.statisticsMode) {
            if (nPosX >= menuPosX + this.getPosX() && nPosX <= menuPosX + this.getPosX() + this.getWidth() && nPosY >= menuPosY + this.getPosY() && nPosY <= menuPosY + this.getPosY() + this.getHeight()) {
                for (int i = 0; i < this.iValuesSize; ++i) {
                    if (nPosX >= menuPosX + this.getPosX() + this.getWidth() - CFG.PADDING * (i + 1) - CFG.PADDING * i - this.iDataWidth * (i + 1) + this.iButtonsPosX && nPosX <= menuPosX + this.getPosX() + this.getWidth() - CFG.PADDING * (i + 1) - CFG.PADDING * i - this.iDataWidth * (i + 1) + this.iButtonsPosX + this.iDataWidth) {
                        this.setHoveredID(i);
                        return;
                    }
                }
            }
            this.setHoveredID(-1);
        }
        else {
            if (nPosX >= menuPosX + this.getPosX() && nPosX <= menuPosX + this.getPosX() + this.getWidth() && nPosY >= menuPosY + this.getPosY() && nPosY <= menuPosY + this.getPosY() + this.getHeight()) {
                for (int i = 0; i < this.iValuesSize; ++i) {
                    if (nPosY >= menuPosY + this.getPosY() + this.iButtonsPosY + (int)(CFG.TEXT_HEIGHT * 0.7f + CFG.PADDING * 2) * i && nPosY <= menuPosY + this.getPosY() + this.iButtonsPosY + (int)(CFG.TEXT_HEIGHT * 0.7f + CFG.PADDING * 2) * i + (int)(CFG.TEXT_HEIGHT * 0.7f + CFG.PADDING * 2)) {
                        this.setHoveredID(i);
                        return;
                    }
                }
            }
            this.setHoveredID(-1);
        }
    }
    
    private final void setHoveredID(final int nHoveredID) {
        if (this.iHoveredID != nHoveredID) {
            this.iHoveredID = nHoveredID;
            this.buildElementHover();
        }
    }
    
    @Override
    public void buildElementHover() {
        if (this.iHoveredID >= 0 && !this.statisticsMode) {
            if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.NUM_OF_PROVINCES_BY_CONTINENT) {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(this.lValues.get(this.iHoveredID).getCivID()).getCivName()));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(this.lValues.get(this.iHoveredID).getCivID(), CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Provinces") + ": ", "" + CFG.getNumberWithSpaces("" + Game.getCiv(this.lValues.get(this.iHoveredID).getCivID()).getNumOfProvinces()), Images.provinces, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_POPULATION) {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(this.lValues.get(this.iHoveredID).getCivID()).getCivName()));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(this.lValues.get(this.iHoveredID).getCivID(), CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", "" + CFG.getNumberWithSpaces("" + Game.getCiv(this.lValues.get(this.iHoveredID).getCivID()).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_LIST_PROVINCES) {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(this.lValues.get(this.iHoveredID).getCivID()).getCivName()));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(this.lValues.get(this.iHoveredID).getCivID(), CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Provinces") + ": ", "" + CFG.getNumberWithSpaces("" + Game.getCiv(this.lValues.get(this.iHoveredID).getCivID()).getNumOfProvinces()), Images.provinces, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_LIST_POPULATION) {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(this.lValues.get(this.iHoveredID).getCivID()).getCivName()));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(this.lValues.get(this.iHoveredID).getCivID(), CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", "" + CFG.getNumberWithSpaces("" + Game.getCiv(this.lValues.get(this.iHoveredID).getCivID()).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.GOVERNMENTS_CIVS || this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.GOVERNMENTS_CIVS_RIGHT) {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(this.lValues.get(this.iHoveredID).getCivID()).getCivName()));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(this.lValues.get(this.iHoveredID).getCivID(), CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", "" + CFG.getNumberWithSpaces("" + Game.getCiv(this.lValues.get(this.iHoveredID).getCivID()).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.RELIGION_CIVS || this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.RELIGION_CIVS_RIGHT) {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(this.lValues.get(this.iHoveredID).getCivID()).getCivName()));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(this.lValues.get(this.iHoveredID).getCivID(), CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", "" + CFG.getNumberWithSpaces("" + Game.getCiv(this.lValues.get(this.iHoveredID).getCivID()).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_CONSTRUCTED_BUILDINGS) {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(this.lValues.get(this.iHoveredID).getCivID()).getCivName()));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(this.lValues.get(this.iHoveredID).getCivID(), CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ConstructedBuildings") + ": ", "" + CFG.getNumberWithSpaces("" + Game.getCiv(this.lValues.get(this.iHoveredID).getCivID()).getConstructedBuildings()), Images.buildings, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_INFRASTRUCTURE) {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(this.lValues.get(this.iHoveredID).getCivID()).getCivName()));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(this.lValues.get(this.iHoveredID).getCivID(), CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Infrastructure") + ": ", "" + CFG.getNumberWithSpaces("" + Game.getCiv(this.lValues.get(this.iHoveredID).getCivID()).getInfrastructure()), Images.infrastructure, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_UNLOCKED_TECHS) {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(this.lValues.get(this.iHoveredID).getCivID()).getCivName()));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(this.lValues.get(this.iHoveredID).getCivID(), CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnlockedTechnologies") + ": ", "" + Game.getCiv(this.lValues.get(this.iHoveredID).getCivID()).getResearchedTechnologies(), Game_Calendar.IMG_TECHNOLOGY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_PRESTIGE) {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(this.lValues.get(this.iHoveredID).getCivID()).getCivName()));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(this.lValues.get(this.iHoveredID).getCivID(), CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Prestige") + ": ", "" + CFG.getNumberWithSpaces("" + (int)Game.getCiv(this.lValues.get(this.iHoveredID).getCivID()).iCivRankScore), CivilizationRanking.getCivilizationRanking_IMG_STAR_CIVID(this.lValues.get(this.iHoveredID).getCivID()), CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.RESOURCE_PRODUCTION) {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(this.lValues.get(this.iHoveredID).getCivID()).getCivName()));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(this.lValues.get(this.iHoveredID).getCivID(), CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Production") + ": ", "" + CFG.getNumberWithSpaces("" + (int)ResourcesManager.getProducedGoods_ResourceCiv(this.lValues.get(this.iHoveredID).getCivID(), InGame_Goods_LargestProducers.RESOURCE_ID)), Images.goods, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_REGIMENTS_LIMIT) {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(this.lValues.get(this.iHoveredID).getCivID()).getCivName()));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(this.lValues.get(this.iHoveredID).getCivID(), CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("RegimentsLimit") + ": ", "" + CFG.getNumberWithSpaces("" + Game.getCiv(this.lValues.get(this.iHoveredID).getCivID()).iRegimentsLimit), Images.regimentsLimit, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_ECONOMY) {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(this.lValues.get(this.iHoveredID).getCivID()).getCivName()));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(this.lValues.get(this.iHoveredID).getCivID(), CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Economy") + ": ", "" + CFG.getNumberWithSpaces("" + (int)Game.getCiv(this.lValues.get(this.iHoveredID).getCivID()).getEconomyTotal()), Game_Calendar.IMG_ECONOMY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            else {
                this.menuElementHover = null;
            }
        }
        else {
            this.menuElementHover = null;
        }
    }
    
    @Override
    public void setCheckboxState(final boolean checkboxState) {
        this.buildValuesHeights();
        this.updateInView();
        this.verticalInfo.updateMoveable(this.getWidth() - ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2));
        this.updateMoveable();
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        if (this.scrollModeY) {
            if (this.statisticsMode) {
                if (Math.abs(this.fScrollNewMenuPosY) > 1.0f) {
                    this.setCurrent(this.iButtonsPosY + (int)this.fScrollNewMenuPosY);
                    this.fScrollNewMenuPosY *= 0.97f;
                }
                else {
                    this.scrollModeY = false;
                }
            }
            else if (Math.abs(this.fScrollNewMenuPosY) > 1.0f) {
                this.setCurrent(this.iButtonsPosX + (int)this.fScrollNewMenuPosY);
                this.fScrollNewMenuPosY *= 0.97f;
            }
            else {
                this.scrollModeY = false;
            }
        }
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.9f));
        ImageManager.getImage(Images.graphBG).draw2(oSB, this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2, this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.3f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2, this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2, false, true);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.3f));
        Images.gradientXY.draw(oSB, this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2, this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2, false, true);
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.065f));
        ImageManager.getImage(Images.noise).draw2(oSB, this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2, this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2);
        Renderer.fontMain.get(0).getData().setScale(0.7f);
        Renderer.drawTextRotated(oSB, this.sTextY, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 + this.iWidthTextY / 2 + iTranslateY, Graph.TEXT_COLOR, 90.0f);
        this.verticalInfo.draw(oSB, this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - (int)(CFG.TEXT_HEIGHT * 0.7f) + iTranslateY, this.getWidth() - ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2));
        Renderer.fontMain.get(0).getData().setScale(1.0f);
        if (this.statisticsMode) {
            this.drawStatisticsBegan(oSB, 1 + iTranslateX, iTranslateY, isActive, scrollableY);
        }
        else {
            Images.pix.draw(oSB, this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + 1 + iTranslateX, this.getPosY() + (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) - CFG.CIV_FLAG_HEIGHT - CFG.PADDING * 2) / 2 + CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2 + 1 + iTranslateY, CFG.PADDING - 1);
            oSB.setColor(Graph.GRAPH_LINES_DESC);
            ImageManager.getImage(Images.line_33).draw2(oSB, this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2 + 1 + iTranslateY, this.getWidth() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2);
            ImageManager.getImage(Images.line_33).draw2(oSB, this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.iAvaragePosY + iTranslateY, this.getWidth() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2, 1, 0, -this.iButtonsPosX);
            Renderer.clipView_Start(oSB, this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2 + 1 + iTranslateX, CFG.GAME_HEIGHT - this.getPosY() - iTranslateY, this.getWidth() - ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) - 1, -this.getHeight());
            if (this.getIsHovered() && this.iHoveredID >= 0) {
                oSB.setColor(Graph.GRAPH_LINES_DESC);
                ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + this.getWidth() - 1 - CFG.PADDING * (this.iHoveredID + 1) - CFG.PADDING * this.iHoveredID - this.iDataWidth * (this.iHoveredID + 1) + this.iButtonsPosX + iTranslateX, this.getPosY() + iTranslateY, this.iDataWidth + 2, this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2), false, true);
                oSB.setColor(new Color(Graph.GRAPH_LINES_DESC.r, Graph.GRAPH_LINES_DESC.g, Graph.GRAPH_LINES_DESC.b, 0.025f));
                ImageManager.getImage(Images.gradientHorizontal).draw(oSB, this.getPosX() + this.getWidth() - 1 - CFG.PADDING * (this.iHoveredID + 1) - CFG.PADDING * this.iHoveredID - this.iDataWidth * (this.iHoveredID + 1) + this.iButtonsPosX + iTranslateX, this.getPosY() + iTranslateY, (this.iDataWidth + 2) / 4, this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2), false, false);
                ImageManager.getImage(Images.gradientHorizontal).draw(oSB, this.getPosX() + this.getWidth() + (this.iDataWidth + 2) - (this.iDataWidth + 2) / 4 - 1 - CFG.PADDING * (this.iHoveredID + 1) - CFG.PADDING * this.iHoveredID - this.iDataWidth * (this.iHoveredID + 1) + this.iButtonsPosX + iTranslateX, this.getPosY() + iTranslateY, (this.iDataWidth + 2) / 4, this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2), true, false);
                oSB.setColor(Color.WHITE);
            }
            if (this.drawShort) {
                for (int i = 0; i < this.iValuesSize; ++i) {
                    if (this.lValues.get(i).getInView()) {
                        this.lValues.get(i).drawData(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * (i + 1) - CFG.PADDING * i - this.iDataWidth * (i + 1) + this.iButtonsPosX + iTranslateX, this.getPosY() + iTranslateY, this.iDataWidth, this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2), this.verticalInfo.getColors(), this.verticalInfo.getSorted());
                        this.lValues.get(i).drawDataTextValue_Short(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * (i + 1) - CFG.PADDING * i - this.iDataWidth * (i + 1) + this.iButtonsPosX + iTranslateX, this.getPosY() + iTranslateY, this.iDataWidth, this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2));
                    }
                }
            }
            else if (this.splitBy100) {
                for (int i = 0; i < this.iValuesSize; ++i) {
                    if (this.lValues.get(i).getInView()) {
                        this.lValues.get(i).drawData(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * (i + 1) - CFG.PADDING * i - this.iDataWidth * (i + 1) + this.iButtonsPosX + iTranslateX, this.getPosY() + iTranslateY, this.iDataWidth, this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2), this.verticalInfo.getColors(), this.verticalInfo.getSorted());
                        this.lValues.get(i).drawDataTextValue_Splitted(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * (i + 1) - CFG.PADDING * i - this.iDataWidth * (i + 1) + this.iButtonsPosX + iTranslateX, this.getPosY() + iTranslateY, this.iDataWidth, this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2));
                    }
                }
            }
            else {
                for (int i = 0; i < this.iValuesSize; ++i) {
                    if (this.lValues.get(i).getInView()) {
                        this.lValues.get(i).drawData(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * (i + 1) - CFG.PADDING * i - this.iDataWidth * (i + 1) + this.iButtonsPosX + iTranslateX, this.getPosY() + iTranslateY, this.iDataWidth, this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2), this.verticalInfo.getColors(), this.verticalInfo.getSorted());
                        this.lValues.get(i).drawDataTextValue(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * (i + 1) - CFG.PADDING * i - this.iDataWidth * (i + 1) + this.iButtonsPosX + iTranslateX, this.getPosY() + iTranslateY, this.iDataWidth, this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2));
                    }
                }
            }
            Renderer.clipView_End(oSB);
            Renderer.fontMain.get(0).getData().setScale(Graph.POINTS_TEXT_SCALE);
            Renderer.drawText(oSB, "" + CFG.getNumberWithSpaces("" + this.iMaxPoint), this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + 1 + (int)(2.0f * CFG.GUI_SCALE) + iTranslateX, this.getPosY() + CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2 + 1 + iTranslateY - (int)(2.0f * CFG.GUI_SCALE + Graph.POINTS_TEXT_SCALE * CFG.TEXT_HEIGHT), Graph.DATA_COLOR);
            Renderer.fontMain.get(0).getData().setScale(1.0f);
        }
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.6f));
        Images.pix.draw(oSB, this.getPosX() - 1 + (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + iTranslateY, 1, this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2 + CFG.PADDING);
        Images.pix.draw(oSB, this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2 - CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2 + iTranslateY, this.getWidth() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2 + CFG.PADDING, 1);
        oSB.setColor(Graph.GRAPH_BORDERS_COLOR);
        Images.pix.draw(oSB, this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + iTranslateY, 1, this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2 + CFG.PADDING);
        Images.pix.draw(oSB, this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2 - CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2 + iTranslateY, this.getWidth() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2 + CFG.PADDING, 1);
        Images.pix.draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING + iTranslateX, this.getPosY() + iTranslateY, CFG.PADDING);
        Images.pix.draw(oSB, this.getPosX() - 1 + this.getWidth() + iTranslateX, this.getPosY() + iTranslateY, 1, CFG.PADDING - 1);
        Images.pix.draw(oSB, this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + iTranslateX, this.getPosY() + iTranslateY, CFG.PADDING - 1);
        Images.pix.draw(oSB, this.getPosX() + this.getWidth() - 1 + iTranslateX, this.getPosY() + this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) - CFG.PADDING + iTranslateY, 1, CFG.PADDING - 1);
        oSB.setColor(Color.WHITE);
    }
    
    private final void drawStatisticsBegan(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        Renderer.fontMain.get(0).getData().setScale(0.7f);
        int tempOffsetX = 0;
        this.drawStatisticsBoxTitle(oSB, this.sTextX, this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + iTranslateX, this.getPosY() + iTranslateY, this.getStatisticsWidth() * 2);
        tempOffsetX += this.getStatisticsWidth() * 2;
        for (int i = 0; i < this.verticalInfo.getTextSize(); ++i) {
            this.drawStatisticsBoxTitle(oSB, this.verticalInfo.getText(i), this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + tempOffsetX + iTranslateX, this.getPosY() + iTranslateY, this.getStatisticsWidth());
            tempOffsetX += this.getStatisticsWidth();
        }
        this.drawStatisticsBoxTitle(oSB, this.drawStatisticsData.getTotal(), this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + tempOffsetX + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - tempOffsetX - ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2));
        Renderer.clipView_Start(oSB, this.getPosX() + (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2 + 1 + iTranslateX, CFG.GAME_HEIGHT - this.getPosY() - ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) - 2 - iTranslateY, this.getWidth() - ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) - 1, -this.getHeight() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * 2 + 1);
        this.drawStatisticsEnd(oSB, iTranslateX, this.iButtonsPosY + iTranslateY, isActive, scrollableY, tempOffsetX);
        Renderer.clipView_End(oSB);
        oSB.setColor(Graph.GRAPH_LINES_COLOR);
        for (int i = -1; i < this.verticalInfo.getTextSize() - 1; ++i) {
            ImageManager.getImage(Images.line_32_vertical).draw2(oSB, this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + this.getStatisticsWidth() * 2 + this.getStatisticsWidth() * (i + 1) - 1 + iTranslateX, this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + iTranslateY, 1, this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * 2, false, true);
        }
        Images.pix.draw(oSB, this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + this.getStatisticsWidth() * 2 + this.getStatisticsWidth() * (this.verticalInfo.getTextSize() - 1 + 1) - 1 + iTranslateX, this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + iTranslateY, 1, this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * 2, false, true);
        oSB.setColor(Color.WHITE);
    }
    
    private final void drawStatisticsEnd(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY, final int tempOffsetX) {
        final float tempFlagScale = CFG.TEXT_HEIGHT * 0.7f / CFG.CIV_FLAG_HEIGHT;
        for (int i = 0; i < this.iValuesSize; ++i) {
            if (this.lValues.get(i).getInView()) {
                if (i % 2 == 0) {
                    this.drawStatisticsRowBG(oSB, this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + iTranslateX, this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY, this.getWidth() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2);
                }
                if (this.getIsHovered() && i == this.iHoveredID - 1) {
                    this.drawStatisticsRowHoverBG(oSB, this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + iTranslateX, this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY, this.getWidth() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2);
                }
                this.drawStatisticsRowLine(oSB, this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + iTranslateX, this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY, this.getWidth() - (int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2);
                oSB.setColor(Color.WHITE);
                Game.getCiv(this.drawStatisticsData.getStatsLPCivFlagID(i)).getFlag().draw(oSB, this.getPosX() + ((int)(CFG.TEXT_HEIGHT * 0.7f + CFG.CIV_COLOR_WIDTH) + CFG.PADDING * 2) + iTranslateX, this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + CFG.PADDING + iTranslateY, (int)Math.ceil(CFG.CIV_FLAG_WIDTH * tempFlagScale), (int)Math.ceil(CFG.CIV_FLAG_HEIGHT * tempFlagScale));
                this.drawStatisticsValue2(oSB, this.drawStatisticsData.getStatsLP(i), this.getPosX() + (int)(CFG.CIV_FLAG_WIDTH * tempFlagScale + CFG.CIV_COLOR_WIDTH) + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + iTranslateX, this.getPosY() + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * (i + 1) + iTranslateY, this.getStatisticsWidth() * 2 - (int)(CFG.CIV_FLAG_WIDTH * tempFlagScale + CFG.CIV_COLOR_WIDTH));
                this.drawStatisticsData.draw(oSB, i, tempOffsetX, iTranslateX, iTranslateY);
            }
        }
        Renderer.fontMain.get(0).getData().setScale(1.0f);
    }
    
    private final void drawStatisticsRowLine(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth) {
        oSB.setColor(Graph.GRAPH_LINES_COLOR);
        Images.line_32_off1.draw(oSB, nPosX, nPosY + (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2, nWidth, 1);
    }
    
    private final void drawStatisticsRowBG(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth) {
        oSB.setColor(new Color(0.05f, 0.05f, 0.05f, 0.7f));
        Images.pix.draw(oSB, nPosX, nPosY, nWidth - 1, (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2);
    }
    
    private final void drawStatisticsRowHoverBG(final SpriteBatch oSB, final int nPosX, final int nPosY, final int nWidth) {
        oSB.setColor(new Color(Graph.GRAPH_LINES_DESC.r, Graph.GRAPH_LINES_DESC.g, Graph.GRAPH_LINES_DESC.b, 0.1f));
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, nPosX, nPosY, nWidth - 1, (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2);
    }
    
    private final void drawStatisticsBoxTitle(final SpriteBatch oSB, final String nText, final int nPosX, final int nPosY, final int nWidth) {
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG.r, Colors.COLOR_GRADIENT_BG.g, Colors.COLOR_GRADIENT_BG.b, 0.7f));
        Images.pix.draw(oSB, nPosX, nPosY, nWidth, (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.6f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX, nPosY + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) - ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) / 2, nWidth, ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) / 2, false, true);
        oSB.setColor(Graph.GRAPH_BORDERS_COLOR);
        Images.pix.draw(oSB, nPosX, nPosY + (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2, nWidth, 1);
        oSB.setColor(Graph.GRAPH_LINES_COLOR);
        ImageManager.getImage(Images.line_32_vertical).draw2(oSB, nPosX - 1 + nWidth, nPosY, 1, (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2);
        Renderer.clipView_Start(oSB, nPosX, CFG.GAME_HEIGHT - nPosY, nWidth - CFG.PADDING, -(int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2);
        Renderer.drawText(oSB, nText, nPosX + CFG.PADDING, nPosY + CFG.PADDING, Graph.TEXT_COLOR);
        Renderer.clipView_End(oSB);
    }
    
    private final void drawStatisticsValue(final SpriteBatch oSB, final String nText, final int nPosX, final int nPosY) {
        Renderer.drawText(oSB, nText, nPosX + CFG.PADDING, nPosY + CFG.PADDING, new Color(1.0f, 1.0f, 1.0f, 0.45f));
    }
    
    private final void drawStatisticsValueWithFlag(final SpriteBatch oSB, final String nText, final int nCivID, final int nPosX, final int nPosY) {
        final float tempFlagScale = CFG.TEXT_HEIGHT * 0.7f / CFG.CIV_FLAG_HEIGHT;
        oSB.setColor(Color.WHITE);
        Game.getCiv(nCivID).getFlag().draw(oSB, nPosX + CFG.PADDING, nPosY + CFG.PADDING, (int)Math.ceil(CFG.CIV_FLAG_WIDTH * tempFlagScale), (int)Math.ceil(CFG.CIV_FLAG_HEIGHT * tempFlagScale));
        Renderer.drawText(oSB, nText, nPosX + CFG.PADDING * 2 + (int)(CFG.CIV_FLAG_WIDTH * tempFlagScale), nPosY + CFG.PADDING, new Color(1.0f, 1.0f, 1.0f, 0.45f));
    }
    
    private final void drawStatisticsValue2(final SpriteBatch oSB, final String nText, final int nPosX, final int nPosY, final int nWidth) {
        Renderer.clipView_Start(oSB, nPosX, CFG.GAME_HEIGHT - nPosY, nWidth - CFG.PADDING, -this.getHeight());
        Renderer.drawText(oSB, nText, nPosX + CFG.CIV_COLOR_WIDTH, nPosY + CFG.PADDING, Graph.DATA_COLOR);
        Renderer.clipView_End(oSB);
    }
    
    private final int getStatisticsWidth() {
        return (this.getWidth() - ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2)) / (this.verticalInfo.getTextSize() + 3);
    }
    
    public final void updateInView() {
        if (this.statisticsMode) {
            for (int i = 0; i < this.iValuesSize; ++i) {
                if (this.getButtonsPosY(i) + this.iButtonsPosY >= (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2 && this.getButtonsPosY(i) + this.iButtonsPosY <= this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * 2) {
                    this.lValues.get(i).setInView(true);
                }
                else if (this.getButtonsPosY(i) + this.iButtonsPosY + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) >= 0 && this.getButtonsPosY(i) + ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) + this.iButtonsPosY <= this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * 2) {
                    this.lValues.get(i).setInView(true);
                }
                else {
                    this.lValues.get(i).setInView(false);
                }
            }
        }
        else {
            for (int i = 0; i < this.iValuesSize; ++i) {
                if (this.getButtonsPosX(i) + this.iButtonsPosX >= (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2 && this.getButtonsPosX(i) + this.iButtonsPosX <= this.getWidth()) {
                    this.lValues.get(i).setInView(true);
                }
                else if (this.getButtonsPosX(i) - this.iDataWidth + this.iButtonsPosX >= (int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2 && this.getButtonsPosX(i) - this.iDataWidth + this.iButtonsPosX <= this.getWidth()) {
                    this.lValues.get(i).setInView(true);
                }
                else {
                    this.lValues.get(i).setInView(false);
                }
            }
        }
    }
    
    private final int getButtonsPosX(final int i) {
        return this.getWidth() - this.iDataWidth * i - CFG.PADDING - CFG.PADDING * 2 * i;
    }
    
    private final int getButtonsPosY(final int i) {
        return (int)(CFG.TEXT_HEIGHT * 0.7f + CFG.PADDING * 2) * i;
    }
    
    private final void updateMoveable() {
        if (this.statisticsMode) {
            if (this.getButtonsPosY(this.iValuesSize) > this.getHeight() - (int)(CFG.TEXT_HEIGHT * 0.7f + CFG.PADDING * 2) * 2) {
                this.moveable = true;
            }
            else {
                this.moveable = false;
                this.iButtonsPosY = 0;
            }
        }
        else if (this.getButtonsWidth() > this.getWidth() - ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2)) {
            this.moveable = true;
        }
        else {
            this.moveable = false;
            this.iButtonsPosX = 0;
        }
    }
    
    @Override
    public boolean getScrollable() {
        return this.moveable;
    }
    
    @Override
    public void scrollByWheel(final int nScoll) {
        this.scrollModeY = false;
        this.setCurrent(this.getCurrent() + nScoll);
    }
    
    private final int getButtonsWidth() {
        return this.iDataWidth * this.iValuesSize + CFG.PADDING * 2 * (this.iValuesSize - 1);
    }
    
    private final void roundAverage() {
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
    public boolean getMoveable() {
        return this.moveable;
    }
    
    @Override
    public int getCurrent() {
        if (this.statisticsMode) {
            return this.iButtonsPosY;
        }
        return this.iButtonsPosX;
    }
    
    @Override
    public void setCurrent(int nButtonsPosX) {
        if (this.statisticsMode) {
            if (nButtonsPosX > 0) {
                nButtonsPosX = 0;
                Game.menuManager.setUpdateSliderMenuPosY(true);
                this.scrollModeY = false;
            }
            else if (nButtonsPosX < -((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * this.iValuesSize + (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * 2)) {
                nButtonsPosX = -((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * this.iValuesSize + (this.getHeight() - ((int)(CFG.TEXT_HEIGHT * 0.7f) + CFG.PADDING * 2) * 2);
                Game.menuManager.setUpdateSliderMenuPosY(true);
                this.scrollModeY = false;
            }
            if (this.iButtonsPosY != nButtonsPosX) {
                this.iButtonsPosY = nButtonsPosX;
                this.updateInView();
            }
        }
        else {
            if (nButtonsPosX < 0) {
                nButtonsPosX = 0;
                Game.menuManager.setUpdateSliderMenuPosX(true);
                this.scrollModeY = false;
            }
            else if (nButtonsPosX > this.getButtonsWidth() - this.getWidth() + this.iDataWidth + CFG.PADDING - ((int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2)) {
                nButtonsPosX = this.getButtonsWidth() - this.getWidth() + this.iDataWidth + CFG.PADDING - ((int)(CFG.TEXT_HEIGHT * 0.7f) - CFG.PADDING * 2);
                Game.menuManager.setUpdateSliderMenuPosX(true);
                this.scrollModeY = false;
            }
            if (this.iButtonsPosX != nButtonsPosX) {
                this.iButtonsPosX = nButtonsPosX;
                this.updateInView();
            }
        }
    }
    
    @Override
    public final void scrollTheMenu() {
        if (this.moveable && this.iScrollPosX > 0 && this.iScrollPosX2 > 0 && Math.abs(this.iScrollPosX - this.iScrollPosX2) > 3.0f * CFG.DENSITY) {
            this.fScrollNewMenuPosY = (this.iScrollPosX - this.iScrollPosX2) * 1.25f;
            this.scrollModeY = true;
        }
    }
    
    @Override
    public final void setScrollPosY(final int iScrollPosX) {
        this.iScrollPosX2 = this.iScrollPosX;
        this.iScrollPosX = iScrollPosX;
    }
    
    @Override
    public void stopScrolling() {
        final int n = -1;
        this.iScrollPosX2 = n;
        this.iScrollPosX = n;
        this.scrollModeY = false;
    }
    
    @Override
    public boolean getInStatisticsMode() {
        return this.statisticsMode;
    }
    
    @Override
    public void setInStatisticsMode(final boolean inStatisticsMode) {
        if (this.allowStatisticsMode) {
            this.statisticsMode = inStatisticsMode;
            this.scrollModeY = false;
            final int n = 0;
            this.iButtonsPosY = n;
            this.iButtonsPosX = n;
            if (!this.statisticsMode) {
                for (int i = 0; i < this.iValuesSize; ++i) {
                    this.lValues.get(i).resetAnimation();
                }
            }
            this.updateMoveable();
            this.updateInView();
            this.setHoveredID(-1);
        }
    }
    
    @Override
    public void setVisible(final boolean isVisible) {
        super.setVisible(isVisible);
        this.setHoveredID(-1);
    }
    
    static {
        Graph_Vertical.graphCivs = new ArrayList<Integer>();
    }
    
    interface DrawStatisticsData
    {
        void draw(final SpriteBatch p0, final int p1, final int p2, final int p3, final int p4);
        
        String getTotal();
        
        String getStatsLP(final int p0);
        
        int getStatsLPCivFlagID(final int p0);
    }
}
