// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.province;

import aoc.kingdoms.lukasz.map.map.GeographicalRegions;
import aoc.kingdoms.lukasz.map.map.Continents;
import aoc.kingdoms.lukasz.map.terrain.Terrain;
import aoc.kingdoms.lukasz.map.terrain.TerrainManager;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Disease;
import aoc.kingdoms.lukasz.map.plague.Plague;
import aoc.kingdoms.lukasz.map.plague.PlagueManager;
import com.badlogic.gdx.Gdx;
import aoc.kingdoms.lukasz.map.WondersManager;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Resource;
import aoc.kingdoms.lukasz.map.ResourcesManager;
import aoc.kingdoms.lukasz.menusInGame.Right.InGame_RightGoods;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Religion;
import aoc.kingdoms.lukasz.textures.Image;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.menu.View;
import aoc.kingdoms.lukasz.menusScenarioEditor.Diplomacy.ScenarioDiplomacy;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Flag;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.map.technology.TechnologyTree;
import aoc.kingdoms.lukasz.map.map.MapScenarios;
import java.util.List;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagTitle;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoc.kingdoms.lukasz.jakowski.GlyphLayout_Game;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;

public class ProvinceDrawDetails
{
    public static final Color COLOR_DETAILS_YELLOW;
    public static final Color COLOR_DETAILS;
    public static int fontDetailsID;
    public static int detailsImageID;
    public static int detailsImageWidth;
    public static int detailsImageHeight;
    
    public static final void defaultDrawDetails(final SpriteBatch oSB, final int nProvinceID) {
        final int nPosX = ProvinceDrawArmy.getDetailsPosX(nProvinceID);
        final int nPosY = ProvinceDrawArmy.getDetailsPosY(nProvinceID);
        Renderer.drawBox(oSB, Images.boxDetails, nPosX, nPosY, Game.getProvince(nProvinceID).drawDetails.iWidth, Game.getProvince(nProvinceID).drawDetails.iHeight, 1.0f);
        Renderer.drawText(oSB, ProvinceDrawDetails.fontDetailsID, Game.getProvince(nProvinceID).drawDetails.sText, nPosX + CFG.PADDING * 2, nPosY + CFG.PADDING, ProvinceDrawDetails.COLOR_DETAILS_YELLOW);
    }
    
    public static final void defaultDrawDetailsActive(final SpriteBatch oSB, final int nProvinceID) {
        final int nPosX = ProvinceDrawArmy.getDetailsPosX(nProvinceID);
        final int nPosY = ProvinceDrawArmy.getDetailsPosY(nProvinceID);
        Renderer.drawBox(oSB, Images.boxDetails, nPosX, nPosY, Game.getProvince(nProvinceID).drawDetails.iWidth, Game.getProvince(nProvinceID).drawDetails.iHeight, 1.0f);
        Renderer.drawText(oSB, ProvinceDrawDetails.fontDetailsID, Game.getProvince(nProvinceID).drawDetails.sText, nPosX + CFG.PADDING * 2, nPosY + CFG.PADDING, ProvinceDrawDetails.COLOR_DETAILS);
    }
    
    public static final void defaultUpdateDrawDetails_Text(final int i) {
        final GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        glyphLayout.setText(Renderer.fontMain.get(ProvinceDrawDetails.fontDetailsID), Game.getProvince(i).drawDetails.sText);
        Game.getProvince(i).drawDetails.iTextWidth = (int)glyphLayout.width;
        Game.getProvince(i).drawDetails.iWidth = Game.getProvince(i).drawDetails.iTextWidth + CFG.PADDING * 4;
        Game.getProvince(i).drawDetails.iHeight = CFG.TEXT_HEIGHT + CFG.PADDING * 2;
        Game.getProvince(i).drawDetails.iShiftX = -Game.getProvince(i).drawDetails.iWidth / 2;
        Game.getProvince(i).drawDetails.iShiftY = -Game.getProvince(i).drawDetails.iHeight / 2;
    }
    
    public static final void updateDrawProvinceDetails_GrowthRate() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            updateDrawProvinceDetails_GrowthRate(i);
        }
    }
    
    public static final void updateDrawProvinceDetails_GrowthRate(final int i) {
        Game.getProvince(i).drawDetails = new ProvinceDrawArmy.ProvinceDrawDetailsINT() {
            @Override
            public void drawDetails(final SpriteBatch oSB, final int nProvinceID) {
                ProvinceDrawDetails.defaultDrawDetails(oSB, nProvinceID);
            }
            
            @Override
            public MenuElement_Hover buildHover(final int nProvinceID) {
                if (!Game.getProvince(Game.iHoveredProvinceID).getSeaProvince()) {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID()));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName()));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("GrowthRate") + ": ", CFG.FONT_BOLD_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + (int)Game.getProvince(Game.iHoveredProvinceID).getGrowthRate() + "%", CFG.FONT_REGULAR_SMALL, Colors.HOVER_IMPORTANT));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BaseEconomy") + ": ", CFG.FONT_BOLD_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + (int)Game.getProvince(Game.iHoveredProvinceID).BaseDevelopment, CFG.FONT_REGULAR_SMALL, Colors.HOVER_IMPORTANT));
                    nElements.add(new MenuElement_HoverElement(nData));
                    return new MenuElement_Hover(nElements);
                }
                return null;
            }
        };
        Game.getProvince(i).drawDetails.sText = (int)Game.getProvince(i).getGrowthRate() + "%";
        defaultUpdateDrawDetails_Text(i);
    }
    
    public static final void updateDrawProvinceDetails_Economy() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            updateDrawProvinceDetails_Economy(i);
        }
    }
    
    public static final void updateDrawProvinceDetails_Economy(final int i) {
        Game.getProvince(i).drawDetails = new ProvinceDrawArmy.ProvinceDrawDetailsINT() {
            @Override
            public void drawDetails(final SpriteBatch oSB, final int nProvinceID) {
                ProvinceDrawDetails.defaultDrawDetails(oSB, nProvinceID);
            }
            
            @Override
            public MenuElement_Hover buildHover(final int nProvinceID) {
                if (!Game.getProvince(Game.iHoveredProvinceID).getSeaProvince()) {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID()));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName()));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("GrowthRate") + ": ", CFG.FONT_BOLD_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + (int)Game.getProvince(Game.iHoveredProvinceID).getGrowthRate() + "%", CFG.FONT_REGULAR_SMALL, Colors.HOVER_IMPORTANT));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BaseEconomy") + ": ", CFG.FONT_BOLD_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + (int)Game.getProvince(Game.iHoveredProvinceID).BaseDevelopment, CFG.FONT_REGULAR_SMALL, Colors.HOVER_IMPORTANT));
                    nElements.add(new MenuElement_HoverElement(nData));
                    return new MenuElement_Hover(nElements);
                }
                return null;
            }
        };
        Game.getProvince(i).drawDetails.sText = "" + (int)Game.getProvince(i).BaseDevelopment;
        defaultUpdateDrawDetails_Text(i);
    }
    
    public static final void updateDrawProvinceDetails_ProvinceID() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            updateDrawProvinceDetails_ProvinceID(i);
        }
    }
    
    public static final void updateDrawProvinceDetails_ProvinceID(final int i) {
        Game.getProvince(i).drawDetails = new ProvinceDrawArmy.ProvinceDrawDetailsINT() {
            @Override
            public void drawDetails(final SpriteBatch oSB, final int nProvinceID) {
                ProvinceDrawDetails.defaultDrawDetails(oSB, nProvinceID);
            }
            
            @Override
            public void drawDetailsSea(final SpriteBatch oSB, final int nProvinceID) {
                ProvinceDrawDetails.defaultDrawDetails(oSB, nProvinceID);
            }
            
            @Override
            public MenuElement_Hover buildHover(final int nProvinceID) {
                if (!Game.getProvince(Game.iHoveredProvinceID).getSeaProvince()) {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID()));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName()));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Province") + ": ", CFG.FONT_BOLD_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getProvince(Game.iHoveredProvinceID).getProvinceID(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_IMPORTANT));
                    nElements.add(new MenuElement_HoverElement(nData));
                    return new MenuElement_Hover(nElements);
                }
                return null;
            }
        };
        Game.getProvince(i).drawDetails.sText = "" + Game.getProvince(i).getProvinceID();
        defaultUpdateDrawDetails_Text(i);
    }
    
    public static final void updateDrawProvinceDetails_ProvinceID_Active(final int i) {
        Game.getProvince(i).drawDetails = new ProvinceDrawArmy.ProvinceDrawDetailsINT() {
            @Override
            public void drawDetails(final SpriteBatch oSB, final int nProvinceID) {
                ProvinceDrawDetails.defaultDrawDetailsActive(oSB, nProvinceID);
            }
            
            @Override
            public void drawDetailsSea(final SpriteBatch oSB, final int nProvinceID) {
                ProvinceDrawDetails.defaultDrawDetails(oSB, nProvinceID);
            }
            
            @Override
            public MenuElement_Hover buildHover(final int nProvinceID) {
                if (!Game.getProvince(Game.iHoveredProvinceID).getSeaProvince()) {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID()));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName()));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Province") + ": ", CFG.FONT_BOLD_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getProvince(Game.iHoveredProvinceID).getProvinceID(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_IMPORTANT));
                    nElements.add(new MenuElement_HoverElement(nData));
                    return new MenuElement_Hover(nElements);
                }
                return null;
            }
        };
        Game.getProvince(i).drawDetails.sText = "" + Game.getProvince(i).getProvinceID();
        defaultUpdateDrawDetails_Text(i);
    }
    
    public static final void updateDrawProvinceDetails_Technology() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            updateDrawProvinceDetails_Technology(i);
        }
    }
    
    public static final void updateDrawProvinceDetails_Technology(final int i) {
        Game.getProvince(i).drawDetails = new ProvinceDrawArmy.ProvinceDrawDetailsINT() {
            @Override
            public void drawDetails(final SpriteBatch oSB, final int nProvinceID) {
                if (Game.getProvince(nProvinceID).isCapital) {
                    ProvinceDrawDetails.defaultDrawDetails(oSB, nProvinceID);
                }
            }
            
            @Override
            public void drawDetailsSea(final SpriteBatch oSB, final int nProvinceID) {
                if (Game.getProvince(nProvinceID).isCapital) {
                    ProvinceDrawDetails.defaultDrawDetails(oSB, nProvinceID);
                }
            }
            
            @Override
            public MenuElement_Hover buildHover(final int nProvinceID) {
                if (!Game.getProvince(Game.iHoveredProvinceID).getSeaProvince()) {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID()));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).getCivName()));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    try {
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Technology") + ": ", CFG.FONT_BOLD_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + ((Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).scenarioEditorData.TechnologyID == MapScenarios.DEFAULT_VALUE) ? Game.lang.get("Default") : ((Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).scenarioEditorData.TechnologyID == -1) ? Game.lang.get("None") : TechnologyTree.lTechnology.get(Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).scenarioEditorData.TechnologyID).Name)), CFG.FONT_REGULAR_SMALL, Colors.HOVER_IMPORTANT));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                    return new MenuElement_Hover(nElements);
                }
                return null;
            }
        };
        try {
            Game.getProvince(i).drawDetails.sText = "" + ((Game.getCiv(Game.getProvince(i).getCivID()).scenarioEditorData.TechnologyID == MapScenarios.DEFAULT_VALUE) ? (Game.lang.get("Default") + " " + MapScenarios.scenarioEditorDetails.CivDefault_Technology) : ((Game.getCiv(Game.getProvince(i).getCivID()).scenarioEditorData.TechnologyID == -1) ? Game.lang.get("None") : (TechnologyTree.lTechnology.get(Game.getCiv(Game.getProvince(i).getCivID()).scenarioEditorData.TechnologyID).Name + " " + Game.getCiv(Game.getProvince(i).getCivID()).scenarioEditorData.TechnologyID)));
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        defaultUpdateDrawDetails_Text(i);
    }
    
    public static final void setDetailsImageID(final int nImageID) {
        ProvinceDrawDetails.detailsImageID = nImageID;
        final float tImageScale = CFG.TEXT_HEIGHT / (float)ImageManager.getImage(ProvinceDrawDetails.detailsImageID).getHeight() * 1.25f;
        ProvinceDrawDetails.detailsImageWidth = (int)(ImageManager.getImage(ProvinceDrawDetails.detailsImageID).getWidth() * tImageScale);
        ProvinceDrawDetails.detailsImageHeight = (int)(ImageManager.getImage(ProvinceDrawDetails.detailsImageID).getHeight() * tImageScale);
    }
    
    public static final void defaultDrawDetails_Image(final SpriteBatch oSB, final int nProvinceID) {
        final int nPosX = ProvinceDrawArmy.getDetailsPosX(nProvinceID);
        final int nPosY = ProvinceDrawArmy.getDetailsPosY(nProvinceID);
        Renderer.drawBox(oSB, Images.boxDetails, nPosX - (ProvinceDrawDetails.detailsImageWidth + CFG.PADDING) / 2, nPosY, Game.getProvince(nProvinceID).drawDetails.iWidth + (ProvinceDrawDetails.detailsImageWidth + CFG.PADDING), Game.getProvince(nProvinceID).drawDetails.iHeight, 1.0f);
        Renderer.drawText(oSB, ProvinceDrawDetails.fontDetailsID, Game.getProvince(nProvinceID).drawDetails.sText, nPosX + CFG.PADDING * 2 - (ProvinceDrawDetails.detailsImageWidth + CFG.PADDING) / 2, nPosY + CFG.PADDING, ProvinceDrawDetails.COLOR_DETAILS_YELLOW);
        ImageManager.getImage(ProvinceDrawDetails.detailsImageID).draw(oSB, nPosX + CFG.PADDING * 2 - (ProvinceDrawDetails.detailsImageWidth + CFG.PADDING) / 2 + CFG.PADDING + Game.getProvince(nProvinceID).drawDetails.iTextWidth, nPosY + Game.getProvince(nProvinceID).drawDetails.iHeight / 2 - ProvinceDrawDetails.detailsImageHeight / 2, ProvinceDrawDetails.detailsImageWidth, ProvinceDrawDetails.detailsImageHeight);
    }
    
    public static final void updateDrawProvinceDetails_PeaceVictoryPoints() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            updateDrawProvinceDetails_PeaceVictoryPoints(i);
        }
        setDetailsImageID(Images.victoryPoints);
    }
    
    public static final void updateDrawProvinceDetails_PeaceVictoryPoints(final int i) {
        if (Game.getCiv(Game.getProvince(i).getCivID()).warView_ParticipatesInWar && !Game.getCiv(Game.getProvince(i).getCivID()).warView_IsAggressor) {
            Game.getProvince(i).drawDetails = new ProvinceDrawArmy.ProvinceDrawDetailsINT() {
                @Override
                public void drawDetails(final SpriteBatch oSB, final int nProvinceID) {
                    ProvinceDrawDetails.defaultDrawDetails_Image(oSB, nProvinceID);
                }
                
                @Override
                public void drawDetailsSea(final SpriteBatch oSB, final int nProvinceID) {
                }
                
                @Override
                public MenuElement_Hover buildHover(final int nProvinceID) {
                    if (!Game.getProvince(Game.iHoveredProvinceID).getSeaProvince()) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Cost") + ": ", CFG.getPrecision2(Game.getProvince(Game.iHoveredProvinceID).fProvinceValue, 10), Images.victoryPoints, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        return new MenuElement_Hover(nElements);
                    }
                    return null;
                }
            };
        }
        else {
            Game.getProvince(i).drawDetails = new ProvinceDrawArmy.ProvinceDrawDetailsINT() {
                @Override
                public void drawDetails(final SpriteBatch oSB, final int nProvinceID) {
                }
                
                @Override
                public void drawDetailsSea(final SpriteBatch oSB, final int nProvinceID) {
                }
                
                @Override
                public MenuElement_Hover buildHover(final int nProvinceID) {
                    if (!Game.getProvince(Game.iHoveredProvinceID).getSeaProvince()) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        return new MenuElement_Hover(nElements);
                    }
                    return null;
                }
            };
        }
        Game.getProvince(i).drawDetails.sText = "" + CFG.getPrecision2(Game.getProvince(i).fProvinceValue, 10);
        defaultUpdateDrawDetails_Text(i);
    }
    
    public static final void updateDrawProvinceDetails_ScenarioCores() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            updateDrawProvinceDetails_ScenarioCores(i);
        }
        setDetailsImageID(Images.core);
    }
    
    public static final void updateDrawProvinceDetails_ScenarioCores(final int i) {
        if ((Game.getProvince(i).iCoresSize > 1 || Game.getProvince(i).iCoresSize == 0 || !Game.getProvince(i).haveACore) && !Game.getProvince(i).getSeaProvince()) {
            Game.getProvince(i).drawDetails = new ProvinceDrawArmy.ProvinceDrawDetailsINT() {
                @Override
                public void drawDetails(final SpriteBatch oSB, final int nProvinceID) {
                    ProvinceDrawDetails.defaultDrawDetails_Image(oSB, nProvinceID);
                }
                
                @Override
                public void drawDetailsSea(final SpriteBatch oSB, final int nProvinceID) {
                }
                
                @Override
                public MenuElement_Hover buildHover(final int nProvinceID) {
                    if (!Game.getProvince(Game.iHoveredProvinceID).getSeaProvince()) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        for (int i = 0; i < Game.getProvince(Game.iHoveredProvinceID).iCoresSize; ++i) {
                            nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCore(i), 0, CFG.PADDING));
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCore(i)).getCivName(), CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        return new MenuElement_Hover(nElements);
                    }
                    return null;
                }
            };
        }
        else {
            Game.getProvince(i).drawDetails = new ProvinceDrawArmy.ProvinceDrawDetailsINT() {
                @Override
                public void drawDetails(final SpriteBatch oSB, final int nProvinceID) {
                }
                
                @Override
                public void drawDetailsSea(final SpriteBatch oSB, final int nProvinceID) {
                }
                
                @Override
                public MenuElement_Hover buildHover(final int nProvinceID) {
                    if (!Game.getProvince(Game.iHoveredProvinceID).getSeaProvince()) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        return new MenuElement_Hover(nElements);
                    }
                    return null;
                }
            };
        }
        Game.getProvince(i).drawDetails.sText = "" + CFG.getPrecision2((float)Game.getProvince(i).iCoresSize, 1);
        defaultUpdateDrawDetails_Text(i);
    }
    
    public static final void updateDrawProvinceDetails_ScenarioReligion() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            updateDrawProvinceDetails_ScenarioReligion(i);
        }
        setDetailsImageID(Images.religion);
    }
    
    public static final void updateDrawProvinceDetails_ScenarioReligion(final int i) {
        if (ScenarioDiplomacy.goBackTo == View.IN_GAME) {
            if (!Game.getProvince(i).getSeaProvince() && Game.getProvince(i).getReligion() != Game.getCiv(Game.getProvince(i).getCivID()).getReligionID()) {
                Game.getProvince(i).drawDetails = new ProvinceDrawArmy.ProvinceDrawDetailsINT() {
                    @Override
                    public void drawDetails(final SpriteBatch oSB, final int nProvinceID) {
                        ProvinceDrawDetails.defaultDrawDetails_Image(oSB, nProvinceID);
                    }
                    
                    @Override
                    public void drawDetailsSea(final SpriteBatch oSB, final int nProvinceID) {
                    }
                    
                    @Override
                    public MenuElement_Hover buildHover(final int nProvinceID) {
                        if (!Game.getProvince(Game.iHoveredProvinceID).getSeaProvince()) {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Line());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.religionManager.getReligion(Game.getProvince(Game.iHoveredProvinceID).getReligion()).Name, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            return new MenuElement_Hover(nElements);
                        }
                        return null;
                    }
                };
            }
            else {
                Game.getProvince(i).drawDetails = new ProvinceDrawArmy.ProvinceDrawDetailsINT() {
                    @Override
                    public void drawDetails(final SpriteBatch oSB, final int nProvinceID) {
                    }
                    
                    @Override
                    public void drawDetailsSea(final SpriteBatch oSB, final int nProvinceID) {
                    }
                    
                    @Override
                    public MenuElement_Hover buildHover(final int nProvinceID) {
                        if (!Game.getProvince(Game.iHoveredProvinceID).getSeaProvince()) {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Line());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.religionManager.getReligion(Game.getProvince(Game.iHoveredProvinceID).getReligion()).Name, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            return new MenuElement_Hover(nElements);
                        }
                        return null;
                    }
                };
            }
            Game.getProvince(i).drawDetails.sText = "" + Game.religionManager.getReligion(Game.getProvince(i).getReligion()).Name;
            defaultUpdateDrawDetails_Text(i);
        }
        else {
            if (!Game.getProvince(i).getSeaProvince() && Game.mapScenarios.editorProvinceReligion.get(i) >= 0 && Game.mapScenarios.editorProvinceReligion.get(i) != Game.getCiv(Game.getProvince(i).getCivID()).getReligionID()) {
                Game.getProvince(i).drawDetails = new ProvinceDrawArmy.ProvinceDrawDetailsINT() {
                    @Override
                    public void drawDetails(final SpriteBatch oSB, final int nProvinceID) {
                        ProvinceDrawDetails.defaultDrawDetails_Image(oSB, nProvinceID);
                    }
                    
                    @Override
                    public void drawDetailsSea(final SpriteBatch oSB, final int nProvinceID) {
                    }
                    
                    @Override
                    public MenuElement_Hover buildHover(final int nProvinceID) {
                        if (!Game.getProvince(Game.iHoveredProvinceID).getSeaProvince()) {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Line());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            if (Game.mapScenarios.editorProvinceReligion.get(Game.iHoveredProvinceID) < 0) {
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.religionManager.getReligion(Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).getReligionID()).Name, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT));
                            }
                            else {
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.religionManager.getReligion(Game.mapScenarios.editorProvinceReligion.get(Game.iHoveredProvinceID)).Name, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT));
                            }
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            return new MenuElement_Hover(nElements);
                        }
                        return null;
                    }
                };
            }
            else {
                Game.getProvince(i).drawDetails = new ProvinceDrawArmy.ProvinceDrawDetailsINT() {
                    @Override
                    public void drawDetails(final SpriteBatch oSB, final int nProvinceID) {
                    }
                    
                    @Override
                    public void drawDetailsSea(final SpriteBatch oSB, final int nProvinceID) {
                    }
                    
                    @Override
                    public MenuElement_Hover buildHover(final int nProvinceID) {
                        if (!Game.getProvince(Game.iHoveredProvinceID).getSeaProvince()) {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Line());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            if (Game.mapScenarios.editorProvinceReligion.get(Game.iHoveredProvinceID) < 0) {
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.religionManager.getReligion(Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).getReligionID()).Name, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT));
                            }
                            else {
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.religionManager.getReligion(Game.mapScenarios.editorProvinceReligion.get(Game.iHoveredProvinceID)).Name, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT));
                            }
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            return new MenuElement_Hover(nElements);
                        }
                        return null;
                    }
                };
            }
            if (Game.mapScenarios.editorProvinceReligion.get(i) < 0) {
                Game.getProvince(i).drawDetails.sText = "" + Game.religionManager.getReligion(Game.getProvince(i).getReligion()).Name;
            }
            else {
                Game.getProvince(i).drawDetails.sText = "" + Game.religionManager.getReligion(Game.mapScenarios.editorProvinceReligion.get(i)).Name;
            }
            defaultUpdateDrawDetails_Text(i);
        }
    }
    
    public static final void updateDrawProvinceDetails_PeacePopulation() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            updateDrawProvinceDetails_PeacePopulation(i);
        }
        setDetailsImageID(Images.population);
    }
    
    public static final void updateDrawProvinceDetails_PeacePopulation(final int i) {
        if (Game.getCiv(Game.getProvince(i).getCivID()).warView_ParticipatesInWar && !Game.getCiv(Game.getProvince(i).getCivID()).warView_IsAggressor) {
            Game.getProvince(i).drawDetails = new ProvinceDrawArmy.ProvinceDrawDetailsINT() {
                @Override
                public void drawDetails(final SpriteBatch oSB, final int nProvinceID) {
                    ProvinceDrawDetails.defaultDrawDetails_Image(oSB, nProvinceID);
                }
                
                @Override
                public void drawDetailsSea(final SpriteBatch oSB, final int nProvinceID) {
                }
                
                @Override
                public MenuElement_Hover buildHover(final int nProvinceID) {
                    if (!Game.getProvince(Game.iHoveredProvinceID).getSeaProvince()) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", CFG.getNumberWithSpaces("" + Game.getProvince(Game.iHoveredProvinceID).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Cost") + ": ", CFG.getPrecision2(Game.getProvince(Game.iHoveredProvinceID).fProvinceValue, 10), Images.victoryPoints, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        return new MenuElement_Hover(nElements);
                    }
                    return null;
                }
            };
        }
        else {
            Game.getProvince(i).drawDetails = new ProvinceDrawArmy.ProvinceDrawDetailsINT() {
                @Override
                public void drawDetails(final SpriteBatch oSB, final int nProvinceID) {
                }
                
                @Override
                public void drawDetailsSea(final SpriteBatch oSB, final int nProvinceID) {
                }
                
                @Override
                public MenuElement_Hover buildHover(final int nProvinceID) {
                    if (!Game.getProvince(Game.iHoveredProvinceID).getSeaProvince()) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        return new MenuElement_Hover(nElements);
                    }
                    return null;
                }
            };
        }
        Game.getProvince(i).drawDetails.sText = "" + CFG.getShortNumber(Game.getProvince(i).getPopulationTotal());
        defaultUpdateDrawDetails_Text(i);
    }
    
    public static final void updateDrawProvinceDetails_PeaceEconomy() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            updateDrawProvinceDetails_PeaceEconomy(i);
        }
        setDetailsImageID(Game_Calendar.IMG_ECONOMY);
    }
    
    public static final void updateDrawProvinceDetails_PeaceEconomy(final int i) {
        if (Game.getCiv(Game.getProvince(i).getCivID()).warView_ParticipatesInWar && !Game.getCiv(Game.getProvince(i).getCivID()).warView_IsAggressor) {
            Game.getProvince(i).drawDetails = new ProvinceDrawArmy.ProvinceDrawDetailsINT() {
                @Override
                public void drawDetails(final SpriteBatch oSB, final int nProvinceID) {
                    ProvinceDrawDetails.defaultDrawDetails_Image(oSB, nProvinceID);
                }
                
                @Override
                public void drawDetailsSea(final SpriteBatch oSB, final int nProvinceID) {
                }
                
                @Override
                public MenuElement_Hover buildHover(final int nProvinceID) {
                    if (!Game.getProvince(Game.iHoveredProvinceID).getSeaProvince()) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Economy") + ": ", CFG.getPrecision2(Game.getProvince(i).getEconomyWithBonuses(), 10), Game_Calendar.IMG_ECONOMY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Cost") + ": ", CFG.getPrecision2(Game.getProvince(Game.iHoveredProvinceID).fProvinceValue, 10), Images.victoryPoints, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        return new MenuElement_Hover(nElements);
                    }
                    return null;
                }
            };
        }
        else {
            Game.getProvince(i).drawDetails = new ProvinceDrawArmy.ProvinceDrawDetailsINT() {
                @Override
                public void drawDetails(final SpriteBatch oSB, final int nProvinceID) {
                }
                
                @Override
                public void drawDetailsSea(final SpriteBatch oSB, final int nProvinceID) {
                }
                
                @Override
                public MenuElement_Hover buildHover(final int nProvinceID) {
                    if (!Game.getProvince(Game.iHoveredProvinceID).getSeaProvince()) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        return new MenuElement_Hover(nElements);
                    }
                    return null;
                }
            };
        }
        Game.getProvince(i).drawDetails.sText = "" + CFG.getPrecision2(Game.getProvince(i).getEconomyWithBonuses(), 10);
        defaultUpdateDrawDetails_Text(i);
    }
    
    public static final void drawDetailsReligion(final SpriteBatch oSB, final int nProvinceID) {
        final int nPosX = ProvinceDrawArmy.getDetailsPosX_2(nProvinceID);
        final int nPosY = ProvinceDrawArmy.getDetailsPosY(nProvinceID);
        Game.religionManager.religionImages.get(Game.getProvince(nProvinceID).getReligion()).draw(oSB, nPosX - Game.religionManager.religionImages.get(Game.getProvince(nProvinceID).getReligion()).getWidth() / 2, nPosY - Game.religionManager.religionImages.get(Game.getProvince(nProvinceID).getReligion()).getHeight() / 2);
    }
    
    public static final void updateDrawProvinceDetails_PeaceReligion() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            updateDrawProvinceDetails_PeaceReligion(i);
        }
    }
    
    public static final void updateDrawProvinceDetails_PeaceReligion(final int i) {
        if (Game.getCiv(Game.getProvince(i).getCivID()).warView_ParticipatesInWar) {
            Game.getProvince(i).drawDetails = new ProvinceDrawArmy.ProvinceDrawDetailsINT() {
                @Override
                public void drawDetails(final SpriteBatch oSB, final int nProvinceID) {
                    ProvinceDrawDetails.drawDetailsReligion(oSB, nProvinceID);
                }
                
                @Override
                public void drawDetailsSea(final SpriteBatch oSB, final int nProvinceID) {
                }
                
                @Override
                public MenuElement_Hover buildHover(final int nProvinceID) {
                    if (!Game.getProvince(Game.iHoveredProvinceID).getSeaProvince()) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Religion") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.religionManager.getReligion(Game.getProvince(Game.iHoveredProvinceID).getReligion()).Name, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Religion(Game.getProvince(Game.iHoveredProvinceID).getReligion(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Cost") + ": ", CFG.getPrecision2(Game.getProvince(Game.iHoveredProvinceID).fProvinceValue, 10), Images.victoryPoints, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        return new MenuElement_Hover(nElements);
                    }
                    return null;
                }
            };
        }
        else {
            Game.getProvince(i).drawDetails = new ProvinceDrawArmy.ProvinceDrawDetailsINT() {
                @Override
                public void drawDetails(final SpriteBatch oSB, final int nProvinceID) {
                }
                
                @Override
                public void drawDetailsSea(final SpriteBatch oSB, final int nProvinceID) {
                }
                
                @Override
                public MenuElement_Hover buildHover(final int nProvinceID) {
                    if (!Game.getProvince(Game.iHoveredProvinceID).getSeaProvince()) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Religion") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.religionManager.getReligion(Game.getProvince(Game.iHoveredProvinceID).getReligion()).Name, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Religion(Game.getProvince(Game.iHoveredProvinceID).getReligion(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        return new MenuElement_Hover(nElements);
                    }
                    return null;
                }
            };
        }
        Game.getProvince(i).drawDetails.sText = "";
        defaultUpdateDrawDetails_Text(i);
    }
    
    public static final void drawDetailsGoods(final SpriteBatch oSB, final int nProvinceID) {
        final int nPosX = ProvinceDrawArmy.getDetailsPosX_2(nProvinceID);
        final int nPosY = ProvinceDrawArmy.getDetailsPosY(nProvinceID);
        if (InGame_RightGoods.iActiveResID >= 0) {
            final int resID = InGame_RightGoods.iActiveResID;
            if (Game.getProvince(nProvinceID).getResourceID() == resID) {
                ResourcesManager.resourceImages.get(Game.getProvince(nProvinceID).getResourceID()).draw(oSB, nPosX - ResourcesManager.resourceImages.get(Game.getProvince(nProvinceID).getResourceID()).getWidth() / 2, nPosY - ResourcesManager.resourceImages.get(Game.getProvince(nProvinceID).getResourceID()).getHeight() / 2);
            }
        }
        else if (Game.getProvince(nProvinceID).getResourceID() >= 0) {
            ResourcesManager.resourceImages.get(Game.getProvince(nProvinceID).getResourceID()).draw(oSB, nPosX - ResourcesManager.resourceImages.get(Game.getProvince(nProvinceID).getResourceID()).getWidth() / 2, nPosY - ResourcesManager.resourceImages.get(Game.getProvince(nProvinceID).getResourceID()).getHeight() / 2);
        }
    }
    
    public static final void updateDrawProvinceDetails_Goods() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            updateDrawProvinceDetails_Goods(i);
        }
    }
    
    public static final void updateDrawProvinceDetails_Goods(final int i) {
        Game.getProvince(i).drawDetails = new ProvinceDrawArmy.ProvinceDrawDetailsINT() {
            @Override
            public void drawDetails(final SpriteBatch oSB, final int nProvinceID) {
                ProvinceDrawDetails.drawDetailsGoods(oSB, nProvinceID);
            }
            
            @Override
            public void drawDetailsSea(final SpriteBatch oSB, final int nProvinceID) {
                ProvinceDrawDetails.drawDetailsGoods(oSB, nProvinceID);
            }
            
            @Override
            public MenuElement_Hover buildHover(final int nProvinceID) {
                if (!Game.getProvince(Game.iHoveredProvinceID).getSeaProvince()) {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    if (Game.getProvince(Game.iHoveredProvinceID).getResourceID() >= 0) {
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Resource") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_Text(ResourcesManager.lResources.get(Game.getProvince(Game.iHoveredProvinceID).getResourceID()).Name, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Resource(Game.getProvince(Game.iHoveredProvinceID).getResourceID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                    else {
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Resource") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("None"), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.resourceNone, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                    return new MenuElement_Hover(nElements);
                }
                return null;
            }
        };
        Game.getProvince(i).drawDetails.sText = "";
        defaultUpdateDrawDetails_Text(i);
    }
    
    public static final void updateDrawProvinceDetails_Goods_InvestInEconomy() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            updateDrawProvinceDetails_Goods_InvestInEconomy(i);
        }
    }
    
    public static final void updateDrawProvinceDetails_Goods_InvestInEconomy(final int i) {
        Game.getProvince(i).drawDetails = new ProvinceDrawArmy.ProvinceDrawDetailsINT() {
            @Override
            public void drawDetails(final SpriteBatch oSB, final int nProvinceID) {
                ProvinceDrawDetails.drawDetailsGoods(oSB, nProvinceID);
            }
            
            @Override
            public void drawDetailsSea(final SpriteBatch oSB, final int nProvinceID) {
                ProvinceDrawDetails.drawDetailsGoods(oSB, nProvinceID);
            }
            
            @Override
            public MenuElement_Hover buildHover(final int nProvinceID) {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        if (Game.getProvince(Game.iHoveredProvinceID).getCivID() == Game.player.iCivID) {
                            if (Game.getCiv(Game.player.iCivID).fGold >= Game.getInvestCost(Game.iHoveredProvinceID) && Game.getCiv(Game.player.iCivID).fLegacy >= Game.getInvestCost_Legacy(Game.iHoveredProvinceID)) {
                                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ClickToInvestInEconomy"), Colors.HOVER_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_ECONOMY, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            if (Game.getCiv(Game.player.iCivID).fGold < Game.getInvestCost(Game.iHoveredProvinceID)) {
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("InsufficientGold") + ": ", Colors.HOVER_NEGATIVE));
                                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getInvestCost(Game.iHoveredProvinceID), 100), Colors.COLOR_TEXT_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getInvestCost_Legacy(Game.iHoveredProvinceID)) {
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("InsufficientLegacy") + ": ", Colors.HOVER_NEGATIVE));
                                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getInvestCost_Legacy(Game.iHoveredProvinceID), 100), Colors.COLOR_TEXT_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Economy") + ": "));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getProvince(Game.iHoveredProvinceID).getEconomyWithBonuses(), 100), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_ECONOMY, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            if (Game.canInvestInEconomy(Game.iHoveredProvinceID)) {
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaximumEconomy") + ": ", CFG.FONT_REGULAR_SMALL));
                                nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(Game.getMaxEconomy(Game.iHoveredProvinceID), 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_ECONOMY, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            if (Game.getProvince(Game.iHoveredProvinceID).getResourceID() >= 0) {
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Resource") + ": "));
                                nData.add(new MenuElement_HoverElement_Type_Text(ResourcesManager.getResourceName(Game.getProvince(Game.iHoveredProvinceID).getResourceID()), Colors.HOVER_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_Resource(Game.getProvince(Game.iHoveredProvinceID).getResourceID(), CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            if (Game.getProvince(Game.iHoveredProvinceID).iProvinceInvestSize > 1) {
                                nData.add(new MenuElement_HoverElement_Type_Line());
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                int tDays = 0;
                                for (int i = 0; i < Game.getProvince(Game.iHoveredProvinceID).iProvinceInvestSize; ++i) {
                                    tDays += Game.getProvince(Game.iHoveredProvinceID).provinceInvestDaysLeft.get(i).daysLeft;
                                }
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("InQueue") + ": ", CFG.FONT_REGULAR_SMALL));
                                nData.add(new MenuElement_HoverElement_Type_Text("" + (Game.getProvince(Game.iHoveredProvinceID).iProvinceInvestSize - 1), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_Text(" [" + Game.lang.get("DaysX", tDays) + "]", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                                nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, CFG.PADDING));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            if (CFG.isDesktop()) {
                                nData.add(new MenuElement_HoverElement_Type_Line());
                                nElements.add(new MenuElement_HoverElement(nData, false));
                                nData.clear();
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ShiftClickToInvestXTimes", 5), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                                nElements.add(new MenuElement_HoverElement(nData, false));
                                nData.clear();
                            }
                            Game.setCursorEconomy();
                        }
                        else {
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            Game.setCursorX();
                        }
                        return new MenuElement_Hover(nElements);
                    }
                    Game.setCursorX();
                }
                catch (final Exception ex) {}
                return null;
            }
        };
        Game.getProvince(i).drawDetails.sText = "";
        defaultUpdateDrawDetails_Text(i);
    }
    
    public static final void updateDrawProvinceDetails_PeaceGoods() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            updateDrawProvinceDetails_PeaceGoods(i);
        }
    }
    
    public static final void updateDrawProvinceDetails_PeaceGoods(final int i) {
        if (Game.getCiv(Game.getProvince(i).getCivID()).warView_ParticipatesInWar) {
            Game.getProvince(i).drawDetails = new ProvinceDrawArmy.ProvinceDrawDetailsINT() {
                @Override
                public void drawDetails(final SpriteBatch oSB, final int nProvinceID) {
                    ProvinceDrawDetails.drawDetailsGoods(oSB, nProvinceID);
                }
                
                @Override
                public void drawDetailsSea(final SpriteBatch oSB, final int nProvinceID) {
                }
                
                @Override
                public MenuElement_Hover buildHover(final int nProvinceID) {
                    if (!Game.getProvince(Game.iHoveredProvinceID).getSeaProvince()) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        if (Game.getProvince(Game.iHoveredProvinceID).getResourceID() >= 0) {
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Resource") + ": "));
                            nData.add(new MenuElement_HoverElement_Type_Text(ResourcesManager.lResources.get(Game.getProvince(Game.iHoveredProvinceID).getResourceID()).Name, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Resource(Game.getProvince(Game.iHoveredProvinceID).getResourceID(), CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        else {
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Resource") + ": "));
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("None"), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.resourceNone, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Cost") + ": ", CFG.getPrecision2(Game.getProvince(Game.iHoveredProvinceID).fProvinceValue, 10), Images.victoryPoints, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        return new MenuElement_Hover(nElements);
                    }
                    return null;
                }
            };
        }
        else {
            Game.getProvince(i).drawDetails = new ProvinceDrawArmy.ProvinceDrawDetailsINT() {
                @Override
                public void drawDetails(final SpriteBatch oSB, final int nProvinceID) {
                }
                
                @Override
                public void drawDetailsSea(final SpriteBatch oSB, final int nProvinceID) {
                }
                
                @Override
                public MenuElement_Hover buildHover(final int nProvinceID) {
                    if (!Game.getProvince(Game.iHoveredProvinceID).getSeaProvince()) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        if (Game.getProvince(Game.iHoveredProvinceID).getResourceID() >= 0) {
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Resource") + ": "));
                            nData.add(new MenuElement_HoverElement_Type_Text(ResourcesManager.lResources.get(Game.getProvince(Game.iHoveredProvinceID).getResourceID()).Name, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Resource(Game.getProvince(Game.iHoveredProvinceID).getResourceID(), CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        else {
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Resource") + ": "));
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("None"), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.resourceNone, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        return new MenuElement_Hover(nElements);
                    }
                    return null;
                }
            };
        }
        Game.getProvince(i).drawDetails.sText = "";
        defaultUpdateDrawDetails_Text(i);
    }
    
    public static final void drawDetailsWonders(final SpriteBatch oSB, final int nProvinceID) {
        try {
            final int nPosX = ProvinceDrawArmy.getDetailsPosX_2(nProvinceID);
            final int nPosY = ProvinceDrawArmy.getDetailsPosY(nProvinceID);
            if (Game.getProvince(nProvinceID).wonderID >= 0) {
                oSB.setShader(Renderer.shaderAlpha);
                WondersManager.wonderImages.get(WondersManager.wonders.get(Game.getProvince(nProvinceID).wonderID).ImageID).getTexture().bind(1);
                Gdx.gl.glActiveTexture(33984);
                ImageManager.getImage(Images.unitsFrameMapMask).draw(oSB, nPosX - ImageManager.getImage(Images.unitsFrameMap).getWidth() / 2, nPosY - ImageManager.getImage(Images.unitsFrameMap).getHeight() / 2);
                oSB.flush();
                oSB.setShader(Renderer.shaderDefault);
                ImageManager.getImage(Images.unitsFrameMap).draw(oSB, nPosX - ImageManager.getImage(Images.unitsFrameMap).getWidth() / 2, nPosY - ImageManager.getImage(Images.unitsFrameMap).getHeight() / 2);
                if (Game.getProvince(nProvinceID).wonderConstruction != null) {
                    final int tCenterX = (ImageManager.getImage(Images.progressBarFrame).getWidth() - ImageManager.getImage(Images.progressBarFrameMask).getWidth()) / 2;
                    final int tCenterY = (ImageManager.getImage(Images.progressBarFrame).getHeight() - ImageManager.getImage(Images.progressBarFrameMask).getHeight()) / 2;
                    oSB.setColor(new Color(ProvinceDraw.progressBarBG));
                    ImageManager.getImage(Images.progressBarFrameMask).draw(oSB, tCenterX + (nPosX - ImageManager.getImage(Images.unitsFrameMap).getWidth() / 2), tCenterY + (nPosY - ImageManager.getImage(Images.unitsFrameMap).getHeight() / 2 + ImageManager.getImage(Images.unitsFrameMap).getHeight() - ImageManager.getImage(Images.progressBarFrame).getHeight()));
                    oSB.setColor(ProvinceDraw.progressBar);
                    ImageManager.getImage(Images.progressBarFrameMask).draw(oSB, tCenterX + (nPosX - ImageManager.getImage(Images.unitsFrameMap).getWidth() / 2), tCenterY + (nPosY - ImageManager.getImage(Images.unitsFrameMap).getHeight() / 2 + ImageManager.getImage(Images.unitsFrameMap).getHeight() - ImageManager.getImage(Images.progressBarFrame).getHeight()), (int)(ImageManager.getImage(Images.progressBarFrameMask).getWidth() * (1.0f - Game.getProvince(nProvinceID).wonderConstruction.daysLeft / (float)Game.getProvince(nProvinceID).wonderConstruction.investTime)), ImageManager.getImage(Images.progressBarFrameMask).getHeight());
                    oSB.setColor(Color.WHITE);
                    ImageManager.getImage(Images.progressBarFrame).draw(oSB, nPosX - ImageManager.getImage(Images.unitsFrameMap).getWidth() / 2, nPosY - ImageManager.getImage(Images.unitsFrameMap).getHeight() / 2 + ImageManager.getImage(Images.unitsFrameMap).getHeight() - ImageManager.getImage(Images.progressBarFrame).getHeight());
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void drawDetailsDiseases(final SpriteBatch oSB, final int nProvinceID) {
        try {
            final int nPosX = ProvinceDrawArmy.getDetailsPosX_2(nProvinceID);
            final int nPosY = ProvinceDrawArmy.getDetailsPosY(nProvinceID);
            if (Game.getProvince(nProvinceID).provincePlague != null) {
                PlagueManager.plagueImagesBig.get(PlagueManager.activePlagues.get(Game.getProvince(nProvinceID).provincePlague.id).iImageID).draw(oSB, nPosX - PlagueManager.plagueImagesBig.get(PlagueManager.activePlagues.get(Game.getProvince(nProvinceID).provincePlague.id).iImageID).getWidth() / 2, nPosY - PlagueManager.plagueImagesBig.get(PlagueManager.activePlagues.get(Game.getProvince(nProvinceID).provincePlague.id).iImageID).getHeight() / 2);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void updateDrawProvinceDetails_Wonders() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            updateDrawProvinceDetails_Wonders(i);
        }
    }
    
    public static final void updateDrawProvinceDetails_Wonders(final int i) {
        Game.getProvince(i).drawDetails = new ProvinceDrawArmy.ProvinceDrawDetailsINT() {
            @Override
            public void drawDetails(final SpriteBatch oSB, final int nProvinceID) {
                ProvinceDrawDetails.drawDetailsWonders(oSB, nProvinceID);
            }
            
            @Override
            public void drawDetailsSea(final SpriteBatch oSB, final int nProvinceID) {
                ProvinceDrawDetails.drawDetailsWonders(oSB, nProvinceID);
            }
            
            @Override
            public MenuElement_Hover buildHover(final int nProvinceID) {
                if (Game.getProvince(Game.iHoveredProvinceID).wonderID >= 0) {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID()));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Wonder") + ": "));
                    nData.add(new MenuElement_HoverElement_Type_Text(WondersManager.wonders.get(Game.getProvince(Game.iHoveredProvinceID).wonderID).Name, Colors.COLOR_TEXT_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    return new MenuElement_Hover(nElements);
                }
                return null;
            }
        };
        Game.getProvince(i).drawDetails.sText = "";
        defaultUpdateDrawDetails_Text(i);
    }
    
    public static final void updateDrawProvinceDetails_Diseases() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            updateDrawProvinceDetails_Diseases(i);
        }
    }
    
    public static final void updateDrawProvinceDetails_Diseases(final int i) {
        Game.getProvince(i).drawDetails = new ProvinceDrawArmy.ProvinceDrawDetailsINT() {
            @Override
            public void drawDetails(final SpriteBatch oSB, final int nProvinceID) {
                ProvinceDrawDetails.drawDetailsDiseases(oSB, nProvinceID);
            }
            
            @Override
            public void drawDetailsSea(final SpriteBatch oSB, final int nProvinceID) {
                ProvinceDrawDetails.drawDetailsDiseases(oSB, nProvinceID);
            }
            
            @Override
            public MenuElement_Hover buildHover(final int nProvinceID) {
                try {
                    if (Game.getProvince(Game.iHoveredProvinceID).provincePlague != null) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID()));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        if (Game.getProvince(Game.iHoveredProvinceID).provincePlague != null) {
                            nData.add(new MenuElement_HoverElement_Type_Line());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Disease") + ": "));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + PlagueManager.activePlagues.get(Game.getProvince(Game.iHoveredProvinceID).provincePlague.id).sName, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Disease(PlagueManager.activePlagues.get(Game.getProvince(Game.iHoveredProvinceID).provincePlague.id).iImageID, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Deaths") + ": "));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getNumberWithSpaces("" + Game.getProvince(Game.iHoveredProvinceID).provincePlague.deaths), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.skull, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        return new MenuElement_Hover(nElements);
                    }
                    return null;
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                    return null;
                }
            }
        };
        Game.getProvince(i).drawDetails.sText = "";
        defaultUpdateDrawDetails_Text(i);
    }
    
    public static final void updateDrawProvinceDetails_SeaProvinces() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            updateDrawProvinceDetails_SeaProvinces(i);
        }
    }
    
    public static final void updateDrawProvinceDetails_SeaProvinces(final int i) {
        Game.getProvince(i).drawDetails = new ProvinceDrawArmy.ProvinceDrawDetailsINT() {
            @Override
            public void drawDetails(final SpriteBatch oSB, final int nProvinceID) {
                ProvinceDrawDetails.defaultDrawDetails(oSB, nProvinceID);
            }
            
            @Override
            public void drawDetailsSea(final SpriteBatch oSB, final int nProvinceID) {
                ProvinceDrawDetails.defaultDrawDetails(oSB, nProvinceID);
            }
            
            @Override
            public MenuElement_Hover buildHover(final int nProvinceID) {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                if (Game.getProvince(Game.iHoveredProvinceID).getProvinceName().length() > 0) {
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), CFG.FONT_BOLD_SMALL));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                }
                if (Game.getProvince(Game.iHoveredProvinceID).getLevelOfPort() >= -1) {
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LandProvince") + ": ", CFG.FONT_BOLD_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getProvince(Game.iHoveredProvinceID).getLevelOfPort(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_IMPORTANT));
                    nElements.add(new MenuElement_HoverElement(nData));
                }
                else if (Game.getProvince(Game.iHoveredProvinceID).getLevelOfPort() == -2) {
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("SeaProvince") + ": ", CFG.FONT_BOLD_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getProvince(Game.iHoveredProvinceID).getLevelOfPort(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_IMPORTANT));
                    nElements.add(new MenuElement_HoverElement(nData));
                }
                else if (Game.getProvince(Game.iHoveredProvinceID).getLevelOfPort() == -3) {
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ClosedSeaLake") + ": ", CFG.FONT_BOLD_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getProvince(Game.iHoveredProvinceID).getLevelOfPort(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_IMPORTANT));
                    nElements.add(new MenuElement_HoverElement(nData));
                }
                return new MenuElement_Hover(nElements);
            }
        };
        Game.getProvince(i).drawDetails.sText = "" + Game.getProvince(i).getLevelOfPort();
        defaultUpdateDrawDetails_Text(i);
    }
    
    public static final void updateDrawProvinceDetails_Terrain() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            updateDrawProvinceDetails_Terrain(i);
        }
    }
    
    public static final void updateDrawProvinceDetails_Terrain(final int i) {
        Game.getProvince(i).drawDetails = new ProvinceDrawArmy.ProvinceDrawDetailsINT() {
            @Override
            public void drawDetails(final SpriteBatch oSB, final int nProvinceID) {
                final int nPosX = ProvinceDrawArmy.getDetailsPosX(nProvinceID);
                final int nPosY = ProvinceDrawArmy.getDetailsPosY(nProvinceID);
                Renderer.clipView_Start(oSB, nPosX - ImageManager.getImage(Images.terrainSmall).getWidth() / 2, CFG.GAME_HEIGHT - (nPosY - ImageManager.getImage(Images.terrainSmall).getHeight() / 2), ImageManager.getImage(Images.terrainSmall).getWidth(), -ImageManager.getImage(Images.terrainSmall).getHeight());
                Game.terrainManager.terrainImages.get(Game.getProvince(nProvinceID).getTerrainID()).get(nProvinceID % Game.terrainManager.terrainImages.get(Game.getProvince(nProvinceID).getTerrainID()).size()).draw(oSB, nPosX - TerrainManager.terrainSmallWidth / 2, nPosY - TerrainManager.terrainSmallHeight / 2, TerrainManager.terrainSmallWidth, TerrainManager.terrainSmallHeight);
                Renderer.clipView_End(oSB);
                ImageManager.getImage(Images.terrainSmall).draw(oSB, nPosX - ImageManager.getImage(Images.terrainSmall).getWidth() / 2, nPosY - ImageManager.getImage(Images.terrainSmall).getHeight() / 2);
            }
            
            @Override
            public void drawDetailsSea(final SpriteBatch oSB, final int nProvinceID) {
            }
            
            @Override
            public MenuElement_Hover buildHover(final int nProvinceID) {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                if (Game.getProvince(Game.iHoveredProvinceID).getProvinceName().length() > 0) {
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), CFG.FONT_BOLD_SMALL));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                }
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Terrain") + ": ", CFG.FONT_BOLD_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + Game.terrainManager.terrains.get(Game.getProvince(Game.iHoveredProvinceID).getTerrainID()).Name, CFG.FONT_REGULAR_SMALL, Colors.HOVER_IMPORTANT));
                nElements.add(new MenuElement_HoverElement(nData));
                return new MenuElement_Hover(nElements);
            }
        };
    }
    
    public static final void updateDrawProvinceDetails_Resource() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            updateDrawProvinceDetails_Resource(i);
        }
    }
    
    public static final void updateDrawProvinceDetails_Resource(final int i) {
        Game.getProvince(i).drawDetails = new ProvinceDrawArmy.ProvinceDrawDetailsINT() {
            @Override
            public void drawDetails(final SpriteBatch oSB, final int nProvinceID) {
                if (Game.getProvince(nProvinceID).getResourceID() >= 0) {
                    final int nPosX = ProvinceDrawArmy.getDetailsPosX_2(nProvinceID);
                    final int nPosY = ProvinceDrawArmy.getDetailsPosY(nProvinceID);
                    ResourcesManager.resourceImages.get(Game.getProvince(nProvinceID).getResourceID()).draw(oSB, nPosX - ResourcesManager.resourceImages.get(Game.getProvince(nProvinceID).getResourceID()).getWidth() / 2, nPosY - ResourcesManager.resourceImages.get(Game.getProvince(nProvinceID).getResourceID()).getHeight() / 2);
                }
            }
            
            @Override
            public void drawDetailsSea(final SpriteBatch oSB, final int nProvinceID) {
            }
            
            @Override
            public MenuElement_Hover buildHover(final int nProvinceID) {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                if (Game.getProvince(Game.iHoveredProvinceID).getProvinceName().length() > 0) {
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), CFG.FONT_BOLD_SMALL));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                }
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Resource") + ": ", CFG.FONT_BOLD_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + ResourcesManager.lResources.get(Game.getProvince(Game.iHoveredProvinceID).getResourceID()).Name, CFG.FONT_REGULAR_SMALL, Colors.HOVER_IMPORTANT));
                nElements.add(new MenuElement_HoverElement(nData));
                return new MenuElement_Hover(nElements);
            }
        };
    }
    
    public static final void updateDrawProvinceDetails_Continent() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            updateDrawProvinceDetails_Continent(i);
        }
    }
    
    public static final void updateDrawProvinceDetails_Continent(final int i) {
        Game.getProvince(i).drawDetails = new ProvinceDrawArmy.ProvinceDrawDetailsINT() {
            @Override
            public void drawDetails(final SpriteBatch oSB, final int nProvinceID) {
            }
            
            @Override
            public void drawDetailsSea(final SpriteBatch oSB, final int nProvinceID) {
            }
            
            @Override
            public MenuElement_Hover buildHover(final int nProvinceID) {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                if (Game.getProvince(Game.iHoveredProvinceID).getProvinceName().length() > 0) {
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), CFG.FONT_BOLD_SMALL));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                }
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Continent") + ": ", CFG.FONT_BOLD_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + Game.continents.lContinents.get(Game.getProvince(Game.iHoveredProvinceID).getContinent()).sName, CFG.FONT_REGULAR_SMALL, Colors.HOVER_IMPORTANT));
                nElements.add(new MenuElement_HoverElement(nData));
                return new MenuElement_Hover(nElements);
            }
        };
    }
    
    public static final void updateDrawProvinceDetails_GeoRegion() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            updateDrawProvinceDetails_GeoRegion(i);
        }
    }
    
    public static final void updateDrawProvinceDetails_GeoRegion(final int i) {
        Game.getProvince(i).drawDetails = new ProvinceDrawArmy.ProvinceDrawDetailsINT() {
            @Override
            public void drawDetails(final SpriteBatch oSB, final int nProvinceID) {
            }
            
            @Override
            public void drawDetailsSea(final SpriteBatch oSB, final int nProvinceID) {
            }
            
            @Override
            public MenuElement_Hover buildHover(final int nProvinceID) {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                if (Game.getProvince(Game.iHoveredProvinceID).getProvinceName().length() > 0) {
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), CFG.FONT_BOLD_SMALL));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                }
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Region") + ": ", CFG.FONT_BOLD_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + Game.geographicalRegions.lGeographicalRegions.get(Game.getProvince(Game.iHoveredProvinceID).getGeoRegion()).sName, CFG.FONT_REGULAR_SMALL, Colors.HOVER_IMPORTANT));
                nElements.add(new MenuElement_HoverElement(nData));
                return new MenuElement_Hover(nElements);
            }
        };
    }
    
    public static final void updateDrawProvinceDetails_OptimizationRegions() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            updateDrawProvinceDetails_OptimizationRegions(i);
        }
    }
    
    public static final void updateDrawProvinceDetails_OptimizationRegions(final int i) {
        Game.getProvince(i).drawDetails = new ProvinceDrawArmy.ProvinceDrawDetailsINT() {
            @Override
            public void drawDetails(final SpriteBatch oSB, final int nProvinceID) {
                ProvinceDrawDetails.defaultDrawDetails(oSB, nProvinceID);
            }
            
            @Override
            public void drawDetailsSea(final SpriteBatch oSB, final int nProvinceID) {
                ProvinceDrawDetails.defaultDrawDetails(oSB, nProvinceID);
            }
            
            @Override
            public MenuElement_Hover buildHover(final int nProvinceID) {
                if (!Game.getProvince(Game.iHoveredProvinceID).getSeaProvince()) {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    if (Game.getProvince(Game.iHoveredProvinceID).getProvinceName().length() > 0) {
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID()));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName()));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                    }
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Region") + ": ", CFG.FONT_BOLD_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + Game.regions.getRegionID(Game.iHoveredProvinceID), CFG.FONT_REGULAR_SMALL, Colors.HOVER_IMPORTANT));
                    nElements.add(new MenuElement_HoverElement(nData));
                    return new MenuElement_Hover(nElements);
                }
                return null;
            }
        };
        Game.getProvince(i).drawDetails.sText = "" + Game.regions.getRegionID(i);
        defaultUpdateDrawDetails_Text(i);
    }
    
    static {
        COLOR_DETAILS_YELLOW = new Color(0.8627451f, 0.78431374f, 0.27450982f, 1.0f);
        COLOR_DETAILS = new Color(0.75686276f, 0.7372549f, 0.7176471f, 1.0f);
        ProvinceDrawDetails.fontDetailsID = 0;
        ProvinceDrawDetails.detailsImageID = 0;
        ProvinceDrawDetails.detailsImageWidth = 0;
        ProvinceDrawDetails.detailsImageHeight = 0;
    }
}
