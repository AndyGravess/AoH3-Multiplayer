// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ;
import aoh.kingdoms.history.menu_element.button.Button_Advantage3;
import aoh.kingdoms.history.map.civilization.CivilizationLegacy;
import aoh.kingdoms.history.mainGame.SoundsManager;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_FlagCenter;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import aoh.kingdoms.history.menu_element.Empty_AdvantageBG;
import aoh.kingdoms.history.menu_element.button.Button_Advantage_Title;
import aoh.kingdoms.history.menu_element.button.Button_Advantage2;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Clear;
import aoh.kingdoms.history.menusInGame.Info.InGame_Info;
import aoh.kingdoms.history.mainGame.AI.Advantages.AI_Advantages;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2_TextLR;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.map.AdvantagesManager;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Active_Click;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2Center;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menusInGame.Technology.InGame_TechnologyChoose;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_CivilizationAdvantages extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static long lTime2;
    public static int iMenuWidth;
    public static int iActiveCivID;
    public static int iActiveSortID;
    
    public InGame_CivilizationAdvantages(final int nActiveCivID) {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        InGame_CivilizationAdvantages.iActiveCivID = nActiveCivID;
        final int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2;
        final int paddingLeft2 = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2;
        final int titleHeight = ImageManager.getImage(Images.title500).getHeight();
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        InGame_CivilizationAdvantages.iMenuWidth = menuWidth - Images.boxTitleBORDERWIDTH * 2;
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title500).getHeight();
        final int buttonYPadding = CFG.PADDING + CFG.PADDING / 2;
        int buttonY = 0;
        final int buttonH = CFG.isDesktop() ? CFG.BUTTON_HEIGHT3 : CFG.BUTTON_HEIGHT2;
        InGame_TechnologyChoose.IN_TECHNOLOGY_CHOOSE = false;
        if (InGame_CivilizationAdvantages.iActiveCivID == Game.player.iCivID) {
            menuElements.add(new Text_Title_v2Center(Game.lang.get("GroupsOfAdvantages"), -1, CFG.FONT_BOLD, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonStatsRectIMG_Active_Click(Game.lang.get("Administrative"), Images.government, paddingLeft2, buttonY, (menuWidth - paddingLeft2 * 2 - CFG.PADDING) / 2, buttonH, ImageManager.getImage(Game_Calendar.IMG_MANPOWER).getWidth(), 0, (InGame_CivilizationAdvantages.iActiveSortID == 0) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL) {
                @Override
                public void actionElement() {
                    InGame_CivilizationAdvantages.iActiveSortID = 0;
                    Game.menuManager.rebuildInGame_CivilizationAdvantages(InGame_CivilizationAdvantages.iActiveCivID);
                    InGame_CivilizationAdvantages.lTime = 0L;
                }
                
                @Override
                protected Color getColor(final boolean isActive) {
                    if (InGame_CivilizationAdvantages.iActiveSortID == 0) {
                        return Colors.HOVER_GOLD;
                    }
                    return super.getColor(isActive);
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    int tNum = 0;
                    int haveNum = 0;
                    for (int i = 0; i < AdvantagesManager.iAdvantagesSize; ++i) {
                        if (0 == AdvantagesManager.advantages.get(i).GroupID) {
                            if (Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantage(i, 0)) {
                                ++haveNum;
                            }
                            if (Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).getTechResearched(AdvantagesManager.advantages.get(i).RequiredTechID) || Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantage(i, 0)) {
                                ++tNum;
                            }
                        }
                    }
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("AdministrativeAdvantages"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.government, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Advantages") + ": "));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle("" + haveNum + " / " + tNum, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.advantages, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            menuElements.add(new ButtonStatsRectIMG_Active_Click(Game.lang.get("Economic"), Game_Calendar.IMG_ECONOMY, paddingLeft2 + (menuWidth - paddingLeft2 * 2 - CFG.PADDING) / 2 + CFG.PADDING, buttonY, (menuWidth - paddingLeft2 * 2 - CFG.PADDING) / 2, buttonH, ImageManager.getImage(Game_Calendar.IMG_MANPOWER).getWidth(), 0, (InGame_CivilizationAdvantages.iActiveSortID == 1) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL) {
                @Override
                public void actionElement() {
                    InGame_CivilizationAdvantages.iActiveSortID = 1;
                    Game.menuManager.rebuildInGame_CivilizationAdvantages(InGame_CivilizationAdvantages.iActiveCivID);
                    InGame_CivilizationAdvantages.lTime = 0L;
                }
                
                @Override
                protected Color getColor(final boolean isActive) {
                    if (InGame_CivilizationAdvantages.iActiveSortID == 1) {
                        return Colors.HOVER_GOLD;
                    }
                    return super.getColor(isActive);
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    int tNum = 0;
                    int haveNum = 0;
                    for (int i = 0; i < AdvantagesManager.iAdvantagesSize; ++i) {
                        if (1 == AdvantagesManager.advantages.get(i).GroupID) {
                            if (Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantage(i, 0)) {
                                ++haveNum;
                            }
                            if (Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).getTechResearched(AdvantagesManager.advantages.get(i).RequiredTechID) || Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantage(i, 0)) {
                                ++tNum;
                            }
                        }
                    }
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("EconomicAdvantages"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_ECONOMY, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Advantages") + ": "));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle("" + haveNum + " / " + tNum, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.advantages, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonStatsRectIMG_Active_Click(Game.lang.get("Military"), Game_Calendar.IMG_MANPOWER, paddingLeft2, buttonY, (menuWidth - paddingLeft2 * 2 - CFG.PADDING) / 2, buttonH, ImageManager.getImage(Game_Calendar.IMG_MANPOWER).getWidth(), 0, (InGame_CivilizationAdvantages.iActiveSortID == 2) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL) {
                @Override
                public void actionElement() {
                    InGame_CivilizationAdvantages.iActiveSortID = 2;
                    Game.menuManager.rebuildInGame_CivilizationAdvantages(InGame_CivilizationAdvantages.iActiveCivID);
                    InGame_CivilizationAdvantages.lTime = 0L;
                }
                
                @Override
                protected Color getColor(final boolean isActive) {
                    if (InGame_CivilizationAdvantages.iActiveSortID == 2) {
                        return Colors.HOVER_GOLD;
                    }
                    return super.getColor(isActive);
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    int tNum = 0;
                    int haveNum = 0;
                    for (int i = 0; i < AdvantagesManager.iAdvantagesSize; ++i) {
                        if (2 == AdvantagesManager.advantages.get(i).GroupID) {
                            if (Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantage(i, 0)) {
                                ++haveNum;
                            }
                            if (Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).getTechResearched(AdvantagesManager.advantages.get(i).RequiredTechID) || Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantage(i, 0)) {
                                ++tNum;
                            }
                        }
                    }
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("MilitaryAdvantages"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Advantages") + ": "));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle("" + haveNum + " / " + tNum, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.advantages, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            menuElements.add(new ButtonStatsRectIMG_Active_Click(Game.lang.get("All"), Images.advantages, paddingLeft2 + (menuWidth - paddingLeft2 * 2 - CFG.PADDING) / 2 + CFG.PADDING, buttonY, (menuWidth - paddingLeft2 * 2 - CFG.PADDING) / 2, buttonH, ImageManager.getImage(Game_Calendar.IMG_MANPOWER).getWidth(), 0, (InGame_CivilizationAdvantages.iActiveSortID == -1) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL) {
                @Override
                public void actionElement() {
                    InGame_CivilizationAdvantages.iActiveSortID = -1;
                    Game.menuManager.rebuildInGame_CivilizationAdvantages(InGame_CivilizationAdvantages.iActiveCivID);
                    InGame_CivilizationAdvantages.lTime = 0L;
                }
                
                @Override
                protected Color getColor(final boolean isActive) {
                    if (InGame_CivilizationAdvantages.iActiveSortID == -1) {
                        return Colors.HOVER_GOLD;
                    }
                    return super.getColor(isActive);
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    int tNum = 0;
                    int haveNum = 0;
                    for (int i = 0; i < AdvantagesManager.iAdvantagesSize; ++i) {
                        if (Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantage(i, 0)) {
                            ++haveNum;
                        }
                        if (Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).getTechResearched(AdvantagesManager.advantages.get(i).RequiredTechID) || Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantage(i, 0)) {
                            ++tNum;
                        }
                    }
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("All"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.advantages, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Advantages") + ": "));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle("" + haveNum + " / " + tNum, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.advantages, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            if (Game.getCiv(Game.player.iCivID).getAdvantagePoints() > 0) {
                menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Advantages"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                menuElements.add(new ButtonStatsRectIMG_Active_Click(Game.lang.get("UnlockAdvantage") + ": " + Game.lang.get("Random"), Images.advantages, paddingLeft2, buttonY, menuWidth - paddingLeft2 * 2, buttonH, ImageManager.getImage(Game_Calendar.IMG_MANPOWER).getWidth(), 0, (InGame_CivilizationAdvantages.iActiveSortID == 0) ? CFG.FONT_BOLD_SMALL : CFG.FONT_REGULAR_SMALL) {
                    @Override
                    public void actionElement() {
                        final int aID = AI_Advantages.takeAdvantage_Player(Game.player.iCivID);
                        if (aID >= 0) {
                            InGame_Info.iCivID = Game.player.iCivID;
                            InGame_Info.iCivID2 = 0;
                            Game.menuManager.rebuildInGame_Info(Game.lang.get("CivilizationAdvantage"), AdvantagesManager.getAdvantageName(aID) + " - " + Game.lang.get("Unlocked"));
                            InGame_Info.imgID = Images.infoAdvantage;
                        }
                        Game.menuManager.rebuildInGame_CivilizationAdvantages_SavePos(Game.player.iCivID);
                        InGame_CivilizationAdvantages.lTime = 0L;
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("UnlockAdvantage") + ": ", CFG.FONT_BOLD, Colors.HOVER_LEFT));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get("Random"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.advantages, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                });
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
        }
        else {
            buttonY += CFG.PADDING;
            menuElements.add(new ButtonStatsRectIMG_Active_Click(Game.lang.get("CivilizationAdvantages") + ": " + Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).iAdvantagesSize, Images.advantages, paddingLeft2, buttonY, menuWidth - paddingLeft2 * 2, buttonH, ImageManager.getImage(Images.advantages).getWidth(), 0) {
                @Override
                public void actionElement() {
                    Game.menuManager.rebuildInGame_Civ_UnlockedTechnologies();
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).getCivName() + ": " + Game.lang.get("UnlockedTechnologies"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        final int tTitleH = CFG.TEXT_HEIGHT + CFG.PADDING * 5;
        int startPosX = 0;
        final List<List<MenuElement>> toSort = new ArrayList<List<MenuElement>>();
        for (int i = 0; i < AdvantagesManager.iAdvantagesSize; ++i) {
            if (InGame_CivilizationAdvantages.iActiveCivID == Game.player.iCivID) {
                if (InGame_CivilizationAdvantages.iActiveSortID >= 0 && InGame_CivilizationAdvantages.iActiveSortID != AdvantagesManager.advantages.get(i).GroupID) {
                    continue;
                }
                if (!Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).getTechResearched(AdvantagesManager.advantages.get(i).RequiredTechID) && !Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantage(i, 0)) {
                    continue;
                }
            }
            else if (!Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantage(i, 0)) {
                continue;
            }
            startPosX = Math.max(CFG.PADDING * 2, menuWidth / 2 - (AdvantagesManager.advantages.get(i).ImageID.length * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * (AdvantagesManager.advantages.get(i).ImageID.length - 1)) / 2);
            if (AdvantagesManager.advantages.get(i).ConstructionCost != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("ConstructionCost") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).ConstructionCost.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("ConstructionCost"), "" + CFG.getPrecision2(AdvantagesManager.advantages.get(i).ConstructionCost[j] * 100.0f, 10) + "%", Images.construction) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).AdministrationBuildingsCost != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("AdministrationBuildingsCost") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).AdministrationBuildingsCost.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("AdministrationBuildingsCost"), "" + CFG.getPrecision2(AdvantagesManager.advantages.get(i).AdministrationBuildingsCost[j] * 100.0f, 10) + "%", Images.construction) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).MilitaryBuildingsCost != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("MilitaryBuildingsCost") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).MilitaryBuildingsCost.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("MilitaryBuildingsCost"), "" + CFG.getPrecision2(AdvantagesManager.advantages.get(i).MilitaryBuildingsCost[j] * 100.0f, 10) + "%", Images.construction) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).EconomyBuildingsCost != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("EconomyBuildingsCost") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).EconomyBuildingsCost.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("EconomyBuildingsCost"), "" + CFG.getPrecision2(AdvantagesManager.advantages.get(i).EconomyBuildingsCost[j] * 100.0f, 10) + "%", Images.construction) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).ConstructionTime != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("ConstructionTime") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).ConstructionTime.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("ConstructionTime"), "" + CFG.getPrecision2(AdvantagesManager.advantages.get(i).ConstructionTime[j] * 100.0f, 10) + "%", Images.buildTime) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).WonderConstructionCost != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("WonderConstructionCost") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).WonderConstructionCost.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("WonderConstructionCost"), "" + CFG.getPrecision2(AdvantagesManager.advantages.get(i).WonderConstructionCost[j] * 100.0f, 10) + "%", Images.mapModesWonders) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).TaxEfficiency != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("TaxEfficiency") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).TaxEfficiency.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("TaxEfficiency"), ((AdvantagesManager.advantages.get(i).TaxEfficiency[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).TaxEfficiency[j], 10) + "%", Images.tax) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).ProvinceMaintenance != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("ProvinceMaintenance") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).ProvinceMaintenance.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("ProvinceMaintenance"), ((AdvantagesManager.advantages.get(i).ProvinceMaintenance[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).ProvinceMaintenance[j], 10) + "%", Images.provinces) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).BuildingsMaintenanceCost != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("BuildingsMaintenanceCost") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).BuildingsMaintenanceCost.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("BuildingsMaintenanceCost"), ((AdvantagesManager.advantages.get(i).BuildingsMaintenanceCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).BuildingsMaintenanceCost[j] * 100.0f, 10) + "%", Images.buildings) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).ManpowerRecoverySpeed != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("ManpowerRecoverySpeed") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).ManpowerRecoverySpeed.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("ManpowerRecoverySpeed"), ((AdvantagesManager.advantages.get(i).ManpowerRecoverySpeed[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).ManpowerRecoverySpeed[j] * 100.0f, 10) + "%", Game_Calendar.IMG_MANPOWER_TIME) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).ArmyMoraleRecovery != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("ArmyMoraleRecovery") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).ArmyMoraleRecovery.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("ArmyMoraleRecovery"), ((AdvantagesManager.advantages.get(i).ArmyMoraleRecovery[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).ArmyMoraleRecovery[j] * 100.0f, 10) + "%", Images.morale) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).WarScoreCost != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("WarScoreCost") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).WarScoreCost.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("WarScoreCost"), ((AdvantagesManager.advantages.get(i).WarScoreCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).WarScoreCost[j] * 100.0f, 10) + "%", Images.victoryPoints) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).ReinforcementSpeed != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("ReinforcementSpeed") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).ReinforcementSpeed.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("ReinforcementSpeed"), ((AdvantagesManager.advantages.get(i).ReinforcementSpeed[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).ReinforcementSpeed[j] * 100.0f, 10) + "%", Game_Calendar.IMG_MANPOWER_TIME) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).MaxManpower != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("MaximumManpower") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).MaxManpower.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("MaximumManpower"), ((AdvantagesManager.advantages.get(i).MaxManpower[j] > 0) ? "+" : "") + CFG.getNumberWithSpaces("" + AdvantagesManager.advantages.get(i).MaxManpower[j]), Game_Calendar.IMG_MANPOWER_UP) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).Research != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("Research") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).Research.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("Research"), ((AdvantagesManager.advantages.get(i).Research[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).Research[j], 10) + "%", Game_Calendar.IMG_TECHNOLOGY) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).ResearchPoints != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("ResearchPerMonth") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).ResearchPoints.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("ResearchPerMonth"), ((AdvantagesManager.advantages.get(i).ResearchPoints[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).ResearchPoints[j], 100), Game_Calendar.IMG_TECHNOLOGY) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).BuildingSlot != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("AdditionalBuildingsInProvince") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).BuildingSlot.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("AdditionalBuildingsInProvince"), ((AdvantagesManager.advantages.get(i).BuildingSlot[j] > 0) ? "+" : "") + CFG.getPrecision2((float)AdvantagesManager.advantages.get(i).BuildingSlot[j], 1), Images.build) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).MaxInfrastructure != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("MaximumInfrastructureLevel") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).MaxInfrastructure.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("MaximumInfrastructureLevel"), ((AdvantagesManager.advantages.get(i).MaxInfrastructure[j] > 0) ? "+" : "") + CFG.getPrecision2((float)AdvantagesManager.advantages.get(i).MaxInfrastructure[j], 1), Images.infrastructure) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).Devastation != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("Devastation") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).Devastation.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("Devastation"), ((AdvantagesManager.advantages.get(i).Devastation[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).Devastation[j] * 100.0f, 10) + "%", Images.devastation) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).GrowthRate != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("GrowthRate") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).GrowthRate.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("GrowthRate"), ((AdvantagesManager.advantages.get(i).GrowthRate[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).GrowthRate[j], 10) + "%", Images.populationGrowth) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).MonthlyIncome != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("MonthlyIncome") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).MonthlyIncome.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("MonthlyIncome"), ((AdvantagesManager.advantages.get(i).MonthlyIncome[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).MonthlyIncome[j], 100), Images.goldPositive) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).Gold != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("Gold") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).Gold.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("Gold"), ((AdvantagesManager.advantages.get(i).Gold[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).Gold[j], 100), Images.gold) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).MonthlyLegacy != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("MonthlyLegacy") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).MonthlyLegacy.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("MonthlyLegacy"), ((AdvantagesManager.advantages.get(i).MonthlyLegacy[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).MonthlyLegacy[j], 100), Images.legacy) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).IncomeProduction != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("IncomeProduction") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).IncomeProduction.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("IncomeProduction"), ((AdvantagesManager.advantages.get(i).IncomeProduction[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).IncomeProduction[j], 10) + "%", Images.goods) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).ProductionEfficiency != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("ProductionEfficiency") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).ProductionEfficiency.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("ProductionEfficiency"), ((AdvantagesManager.advantages.get(i).ProductionEfficiency[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).ProductionEfficiency[j], 10) + "%", Images.goods) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).InvestInEconomyCost != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("InvestInEconomyCost") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).InvestInEconomyCost.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("InvestInEconomyCost"), ((AdvantagesManager.advantages.get(i).InvestInEconomyCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).InvestInEconomyCost[j] * 100.0f, 10) + "%", Game_Calendar.IMG_ECONOMY_UP) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).IncreaseManpowerCost != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("IncreaseManpowerCost") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).IncreaseManpowerCost.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("IncreaseManpowerCost"), ((AdvantagesManager.advantages.get(i).IncreaseManpowerCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).IncreaseManpowerCost[j], 10) + "%", Game_Calendar.IMG_MANPOWER_UP) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).IncreaseTaxEfficiencyCost != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("IncreaseTaxEfficiencyCost") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).IncreaseTaxEfficiencyCost.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("IncreaseTaxEfficiencyCost"), ((AdvantagesManager.advantages.get(i).IncreaseTaxEfficiencyCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).IncreaseTaxEfficiencyCost[j] * 100.0f, 10) + "%", Images.taxUp) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).IncreaseGrowthRateCost != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("IncreaseGrowthRateCost") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).IncreaseGrowthRateCost.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("IncreaseGrowthRateCost"), ((AdvantagesManager.advantages.get(i).IncreaseGrowthRateCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).IncreaseGrowthRateCost[j] * 100.0f, 10) + "%", Images.populationUp) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).DevelopInfrastructureCost != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("DevelopInfrastructureCost") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).DevelopInfrastructureCost.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("DevelopInfrastructureCost"), ((AdvantagesManager.advantages.get(i).DevelopInfrastructureCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).DevelopInfrastructureCost[j] * 100.0f, 10) + "%", Images.infrastructureUp) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).GeneralAttack != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("GeneralsAttack") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).GeneralAttack.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("GeneralsAttack"), ((AdvantagesManager.advantages.get(i).GeneralAttack[j] > 0) ? "+" : "") + CFG.getPrecision2((float)AdvantagesManager.advantages.get(i).GeneralAttack[j], 1), Images.attack) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).GeneralDefense != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("GeneralsDefense") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).GeneralDefense.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("GeneralsDefense"), ((AdvantagesManager.advantages.get(i).GeneralDefense[j] > 0) ? "+" : "") + CFG.getPrecision2((float)AdvantagesManager.advantages.get(i).GeneralDefense[j], 1), Images.defense) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).UnitsAttack != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("UnitsAttack") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).UnitsAttack.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("UnitsAttack"), ((AdvantagesManager.advantages.get(i).UnitsAttack[j] > 0) ? "+" : "") + CFG.getPrecision2((float)AdvantagesManager.advantages.get(i).UnitsAttack[j], 1), Images.attack) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).UnitsDefense != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("UnitsDefense") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).UnitsDefense.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("UnitsDefense"), ((AdvantagesManager.advantages.get(i).UnitsDefense[j] > 0) ? "+" : "") + CFG.getPrecision2((float)AdvantagesManager.advantages.get(i).UnitsDefense[j], 1), Images.defense) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).MaxMorale != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("MaxMorale") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).MaxMorale.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("MaxMorale"), ((AdvantagesManager.advantages.get(i).MaxMorale[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).MaxMorale[j] * 100.0f, 10) + "%", Images.morale) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).ArmyMovementSpeed != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("ArmyMovementSpeed") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).ArmyMovementSpeed.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("ArmyMovementSpeed"), ((AdvantagesManager.advantages.get(i).ArmyMovementSpeed[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).ArmyMovementSpeed[j], 10) + "%", Images.movementSpeed) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).SiegeEffectiveness != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("SiegeEffectiveness") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).SiegeEffectiveness.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("SiegeEffectiveness"), ((AdvantagesManager.advantages.get(i).SiegeEffectiveness[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).SiegeEffectiveness[j] * 100.0f, 10) + "%", Images.siege) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).ImproveRelationsModifier != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("ImproveRelationsModifier") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).ImproveRelationsModifier.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("ImproveRelationsModifier"), ((AdvantagesManager.advantages.get(i).ImproveRelationsModifier[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).ImproveRelationsModifier[j], 10) + "%", Images.relations) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).IncomeFromVassals != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("IncomeFromVassals") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).IncomeFromVassals.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("IncomeFromVassals"), ((AdvantagesManager.advantages.get(i).IncomeFromVassals[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).IncomeFromVassals[j] * 100.0f, 10) + "%", Images.vassal) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).LoanInterest != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("LoanInterest") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).LoanInterest.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("LoanInterest"), ((AdvantagesManager.advantages.get(i).LoanInterest[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).LoanInterest[j], 10) + "%", Images.loan) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).DiplomacyPoints != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("DiplomacyPoints") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).DiplomacyPoints.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("DiplomacyPoints"), ((AdvantagesManager.advantages.get(i).DiplomacyPoints[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).DiplomacyPoints[j] * 100.0f, 10) + "%", Images.diplomacy) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).RecruitmentTime != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("RecruitmentTime") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).RecruitmentTime.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("RecruitmentTime"), ((AdvantagesManager.advantages.get(i).RecruitmentTime[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).RecruitmentTime[j], 10) + "%", Game_Calendar.IMG_MANPOWER_TIME) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).RecruitArmyCost != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("ArmyRecruitmentCost") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).RecruitArmyCost.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("ArmyRecruitmentCost"), ((AdvantagesManager.advantages.get(i).RecruitArmyCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).RecruitArmyCost[j], 10) + "%", Images.gold) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).RecruitArmyFirstLineCost != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("FirstLineArmyRecruitmentCost") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).RecruitArmyFirstLineCost.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("FirstLineArmyRecruitmentCost"), ((AdvantagesManager.advantages.get(i).RecruitArmyFirstLineCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).RecruitArmyFirstLineCost[j], 10) + "%", Images.gold) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).RecruitArmySecondLineCost != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("SecondLineArmyRecruitmentCost") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).RecruitArmySecondLineCost.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("SecondLineArmyRecruitmentCost"), ((AdvantagesManager.advantages.get(i).RecruitArmySecondLineCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).RecruitArmySecondLineCost[j], 10) + "%", Images.gold) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).ArmyMaintenance != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("ArmyMaintenance") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).ArmyMaintenance.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("ArmyMaintenance"), ((AdvantagesManager.advantages.get(i).ArmyMaintenance[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).ArmyMaintenance[j], 10) + "%", Images.armyMaintenance) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).CoreCost != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("CoreConstruction") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).CoreCost.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("CoreConstruction"), ((AdvantagesManager.advantages.get(i).CoreCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).CoreCost[j], 10) + "%", Images.core) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).ReligionCost != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("ReligionConversionCost") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).ReligionCost.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("ReligionConversionCost"), ((AdvantagesManager.advantages.get(i).ReligionCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).ReligionCost[j], 10) + "%", Images.religion) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).MaxNumOfAlliances != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("MaxNumOfAlliances") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).MaxNumOfAlliances.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("MaxNumOfAlliances"), ((AdvantagesManager.advantages.get(i).MaxNumOfAlliances[j] > 0) ? "+" : "") + CFG.getPrecision2((float)AdvantagesManager.advantages.get(i).MaxNumOfAlliances[j], 1), Images.alliance) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).AdvisorMaxLevel != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("MaximumAdvisorSkillLevel") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).AdvisorMaxLevel.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("MaximumAdvisorSkillLevel"), ((AdvantagesManager.advantages.get(i).AdvisorMaxLevel[j] > 0) ? "+" : "") + CFG.getPrecision2((float)AdvantagesManager.advantages.get(i).AdvisorMaxLevel[j], 1), Images.skill) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).AdvisorPoolSize != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("AdvisorPool") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).AdvisorPoolSize.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("AdvisorPool"), ((AdvantagesManager.advantages.get(i).AdvisorPoolSize[j] > 0) ? "+" : "") + CFG.getPrecision2((float)AdvantagesManager.advantages.get(i).AdvisorPoolSize[j], 1), Images.council) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).MaxNumberOfLoans != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("MaximumNumberOfLoans") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).MaxNumberOfLoans.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("MaximumNumberOfLoans"), ((AdvantagesManager.advantages.get(i).MaxNumberOfLoans[j] > 0) ? "+" : "") + CFG.getPrecision2((float)AdvantagesManager.advantages.get(i).MaxNumberOfLoans[j], 1), Images.loan) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).MaximumLevelOfTheMilitaryAcademyForGenerals != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("MaximumLevelOfTheMilitaryAcademyForGenerals") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).MaximumLevelOfTheMilitaryAcademyForGenerals.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("MaximumLevelOfTheMilitaryAcademyForGenerals"), ((AdvantagesManager.advantages.get(i).MaximumLevelOfTheMilitaryAcademyForGenerals[j] > 0) ? "+" : "") + CFG.getPrecision2((float)AdvantagesManager.advantages.get(i).MaximumLevelOfTheMilitaryAcademyForGenerals[j], 1), Images.general) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).MaximumLevelOfTheMilitaryAcademy != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("MaximumLevelOfTheMilitaryAcademy") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).MaximumLevelOfTheMilitaryAcademy.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("MaximumLevelOfTheMilitaryAcademy"), ((AdvantagesManager.advantages.get(i).MaximumLevelOfTheMilitaryAcademy[j] > 0) ? "+" : "") + CFG.getPrecision2((float)AdvantagesManager.advantages.get(i).MaximumLevelOfTheMilitaryAcademy[j], 1), Game_Calendar.IMG_MANPOWER) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).MaximumLevelOfTheSupremeCourt != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("MaximumLevelOfTheSupremeCourt") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).MaximumLevelOfTheSupremeCourt.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("MaximumLevelOfTheSupremeCourt"), ((AdvantagesManager.advantages.get(i).MaximumLevelOfTheSupremeCourt[j] > 0) ? "+" : "") + CFG.getPrecision2((float)AdvantagesManager.advantages.get(i).MaximumLevelOfTheSupremeCourt[j], 1), Images.stability) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).MaximumLevelOfCapitalCity != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("MaximumLevelOfCapitalCity") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).MaximumLevelOfCapitalCity.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("MaximumLevelOfCapitalCity"), ((AdvantagesManager.advantages.get(i).MaximumLevelOfCapitalCity[j] > 0) ? "+" : "") + CFG.getPrecision2((float)AdvantagesManager.advantages.get(i).MaximumLevelOfCapitalCity[j], 1), Images.capital) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).AggressiveExpansion != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("AggressiveExpansion") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).AggressiveExpansion.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("AggressiveExpansion"), ((AdvantagesManager.advantages.get(i).AggressiveExpansion[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).AggressiveExpansion[j], 10) + "%", Images.war) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).DiseaseDeathRate != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("DiseasesDeathRate") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).DiseaseDeathRate.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("DiseasesDeathRate"), ((AdvantagesManager.advantages.get(i).DiseaseDeathRate[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).DiseaseDeathRate[j] * 100.0f, 10) + "%", Images.disease) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).ManpowerRecoveryFromADisbandedArmy != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("ManpowerRecoveryFromADisbandedArmy") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).ManpowerRecoveryFromADisbandedArmy.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("ManpowerRecoveryFromADisbandedArmy"), ((AdvantagesManager.advantages.get(i).ManpowerRecoveryFromADisbandedArmy[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).ManpowerRecoveryFromADisbandedArmy[j] * 100.0f, 10) + "%", Game_Calendar.IMG_MANPOWER_DISBAND) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).AdvisorCost != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("AdvisorCost") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).AdvisorCost.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("AdvisorCost"), ((AdvantagesManager.advantages.get(i).AdvisorCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).AdvisorCost[j] * 100.0f, 10) + "%", Images.council) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).GeneralCost != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("GeneralCost") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).GeneralCost.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("GeneralCost"), ((AdvantagesManager.advantages.get(i).GeneralCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).GeneralCost[j] * 100.0f, 10) + "%", Images.general) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).Discipline != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("Discipline") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).Discipline.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("Discipline"), ((AdvantagesManager.advantages.get(i).Discipline[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).Discipline[j] * 100.0f, 10) + "%", Images.discipline) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).MaximumAmountOfGold != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("MaximumAmountOfGold") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).MaximumAmountOfGold.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("MaximumAmountOfGold"), ((AdvantagesManager.advantages.get(i).MaximumAmountOfGold[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).MaximumAmountOfGold[j], 10), Images.gold) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).MaximumAmountOfGold_Percentage != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("MaximumAmountOfGold") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).MaximumAmountOfGold_Percentage.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("MaximumAmountOfGold"), ((AdvantagesManager.advantages.get(i).MaximumAmountOfGold_Percentage[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).MaximumAmountOfGold_Percentage[j] * 100.0f, 10) + "%", Images.gold) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).Loot != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("Loot") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).Loot.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("Loot"), ((AdvantagesManager.advantages.get(i).Loot[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(i).Loot[j] * 100.0f, 10) + "%", Images.loot) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).BattleWidth != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("BattleWidth") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).BattleWidth.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("BattleWidth"), ((AdvantagesManager.advantages.get(i).BattleWidth[j] > 0) ? "+" : "") + CFG.getPrecision2((float)AdvantagesManager.advantages.get(i).BattleWidth[j], 1), Images.battleWidth) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).RegimentsLimit != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("RegimentsLimit") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).RegimentsLimit.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("RegimentsLimit"), ((AdvantagesManager.advantages.get(i).RegimentsLimit[j] > 0) ? "+" : "") + CFG.getPrecision2((float)AdvantagesManager.advantages.get(i).RegimentsLimit[j], 1), Images.regimentsLimit) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
            else if (AdvantagesManager.advantages.get(i).AllCharactersLifeExpectancy != null) {
                final List<MenuElement> tList = new ArrayList<MenuElement>();
                tList.add(new Button_Advantage_Title(Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantageMaxLvl(i), Game.lang.get("AllCharactersLifeExpectancy") + "", Images.boxTitleBORDERWIDTH, 0, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
                for (int j = 0; j < AdvantagesManager.advantages.get(i).AllCharactersLifeExpectancy.length; ++j) {
                    tList.add(new Button_Advantage2(startPosX + j * Button_Advantage2.getButtonWidth() + CFG.PADDING * 2 * j, tTitleH + CFG.PADDING, i, j, Game.lang.get("AllCharactersLifeExpectancy"), ((AdvantagesManager.advantages.get(i).AllCharactersLifeExpectancy[j] > 0) ? "+" : "") + Game.lang.get("YearsX", AdvantagesManager.advantages.get(i).AllCharactersLifeExpectancy[j]), Images.council) {
                        @Override
                        public void actionElement() {
                            InGame_CivilizationAdvantages.this.actionUnlock(this.getValue1(), this.getValue2(), this.getTextToDraw());
                        }
                    });
                }
                tList.add(new Empty_AdvantageBG(menuWidth - Images.boxTitleBORDERWIDTH, tTitleH, Images.boxTitleBORDERWIDTH, Button_Advantage2.getButtonHeight() + CFG.PADDING * 2));
                toSort.add(tList);
            }
        }
        if (toSort.size() > 0) {
            while (toSort.size() > 0) {
                int bestID = 0;
                for (int k = 1, iSize = toSort.size(); k < iSize; ++k) {
                    if (CFG.compareAlphabetic_TwoString(toSort.get(bestID).get(0).getText(), toSort.get(k).get(0).getText())) {
                        bestID = k;
                    }
                }
                for (int k = 0, iSize = toSort.get(bestID).size(); k < iSize; ++k) {
                    toSort.get(bestID).get(k).setPosY(buttonY + toSort.get(bestID).get(k).getPosY());
                    menuElements.add(toSort.get(bestID).get(k));
                }
                buttonY += tTitleH + CFG.PADDING * 2 + Button_Advantage2.getButtonHeight() + buttonYPadding;
                toSort.remove(bestID);
            }
        }
        else {
            menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG_FlagCenter(Game.lang.get("CivilizationAdvantages"), Game.lang.get("AdvantagePoints") + ": " + Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).getAdvantagePoints(), false, false, Images.title500) {
            @Override
            public long getTime() {
                return InGame_CivilizationAdvantages.lTime2;
            }
            
            @Override
            public int getFlagCivID() {
                return InGame_CivilizationAdvantages.iActiveCivID;
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
        this.drawScrollPositionAlways = false;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_CivilizationAdvantages.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_CivilizationAdvantages.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.rulerOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.rulerOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.rulerOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_CivilizationAdvantages.lTime = CFG.currentTimeMillis;
        InGame_CivilizationAdvantages.lTime2 = CFG.currentTimeMillis;
    }
    
    public final void actionUnlock(final int iAdvantage, final int iLevel, final String sBonus) {
        if (InGame_CivilizationAdvantages.iActiveCivID == Game.player.iCivID) {
            if (Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).haveAdvantage(iAdvantage, iLevel)) {
                Game.menuManager.addToastGold(Game.lang.get("AlreadyUnlocked"), Images.advantages);
                Game.soundsManager.playSound(Game.soundsManager.getClickMain());
            }
            else if (Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).getAdvantagePoints() <= 0) {
                Game.menuManager.addToastInsufficient(Game.lang.get("AdvantagePoints") + ": ", "" + Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).getAdvantagePoints(), Images.advantages);
                Game.soundsManager.playSound(Game.soundsManager.getClickMain());
            }
            else if (!Game.getCiv(InGame_CivilizationAdvantages.iActiveCivID).canUnlockAdvantage(iAdvantage, iLevel)) {
                if (iLevel > 0) {
                    this.actionUnlock(iAdvantage, iLevel - 1, sBonus);
                }
            }
            else if (AdvantagesManager.unlockAdvantage(InGame_CivilizationAdvantages.iActiveCivID, iAdvantage, iLevel)) {
                Game.soundsManager.playSound((iLevel == 0) ? SoundsManager.SOUND_ADVANTAGE0 : ((iLevel == 1) ? SoundsManager.SOUND_ADVANTAGE1 : SoundsManager.SOUND_ADVANTAGE2));
                InGame_Info.iCivID = InGame_CivilizationAdvantages.iActiveCivID;
                InGame_Info.iCivID2 = 0;
                Game.menuManager.rebuildInGame_Info(Game.lang.get("CivilizationAdvantage"), sBonus + " - " + Game.lang.get("Unlocked"));
                InGame_Info.imgID = Images.infoAdvantage;
                Game.menuManager.rebuildInGame_CivilizationAdvantages_SavePos(InGame_CivilizationAdvantages.iActiveCivID);
                InGame_CivilizationAdvantages.lTime = 0L;
            }
            else {
                Game.menuManager.addToastGold(Game.lang.get("NotAvailable"), Images.advantages);
                Game.soundsManager.playSound(Game.soundsManager.getClickMain());
            }
        }
        else {
            Game.soundsManager.playSound(Game.soundsManager.getClickMain());
        }
    }
    
    public static final List<MenuElement> getAdvantagesSmall(final int iCivID) {
        final List<MenuElement> toSort = new ArrayList<MenuElement>();
        for (int i = 0; i < Game.getCiv(iCivID).iAdvantagesSize; ++i) {
            for (int j = 0; j < Game.getCiv(iCivID).lAdvantages.get(i).lvl + 1; ++j) {
                if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ConstructionCost != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("ConstructionCost"), "" + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ConstructionCost[j] * 100.0f, 10) + "%", Images.construction));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).AdministrationBuildingsCost != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("AdministrationBuildingsCost"), "" + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).AdministrationBuildingsCost[j] * 100.0f, 10) + "%", Images.construction));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MilitaryBuildingsCost != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("MilitaryBuildingsCost"), "" + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MilitaryBuildingsCost[j] * 100.0f, 10) + "%", Images.construction));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).EconomyBuildingsCost != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("EconomyBuildingsCost"), "" + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).EconomyBuildingsCost[j] * 100.0f, 10) + "%", Images.construction));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ConstructionTime != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("ConstructionTime"), "" + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ConstructionTime[j] * 100.0f, 10) + "%", Images.buildTime));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).WonderConstructionCost != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("WonderConstructionCost"), "" + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).WonderConstructionCost[j] * 100.0f, 10) + "%", Images.mapModesWonders));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).TaxEfficiency != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("TaxEfficiency"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).TaxEfficiency[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).TaxEfficiency[j], 10) + "%", Images.tax));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ProvinceMaintenance != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("ProvinceMaintenance"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ProvinceMaintenance[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ProvinceMaintenance[j], 10) + "%", Images.provinces));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).BuildingsMaintenanceCost != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("BuildingsMaintenanceCost"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).BuildingsMaintenanceCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).BuildingsMaintenanceCost[j] * 100.0f, 10) + "%", Images.buildings));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ManpowerRecoverySpeed != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("ManpowerRecoverySpeed"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ManpowerRecoverySpeed[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ManpowerRecoverySpeed[j] * 100.0f, 10) + "%", Game_Calendar.IMG_MANPOWER_TIME));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ArmyMoraleRecovery != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("ArmyMoraleRecovery"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ArmyMoraleRecovery[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ArmyMoraleRecovery[j] * 100.0f, 10) + "%", Images.morale));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).WarScoreCost != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("WarScoreCost"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).WarScoreCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).WarScoreCost[j] * 100.0f, 10) + "%", Images.victoryPoints));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ReinforcementSpeed != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("ReinforcementSpeed"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ReinforcementSpeed[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ReinforcementSpeed[j] * 100.0f, 10) + "%", Game_Calendar.IMG_MANPOWER_TIME));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MaxManpower != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("MaximumManpower"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MaxManpower[j] > 0) ? "+" : "") + CFG.getNumberWithSpaces("" + AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MaxManpower[j]), Game_Calendar.IMG_MANPOWER_UP));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).Research != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("Research"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).Research[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).Research[j], 10) + "%", Game_Calendar.IMG_TECHNOLOGY));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ResearchPoints != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("ResearchPerMonth"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ResearchPoints[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ResearchPoints[j], 100), Game_Calendar.IMG_TECHNOLOGY));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).BuildingSlot != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("AdditionalBuildingsInProvince"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).BuildingSlot[j] > 0) ? "+" : "") + CFG.getPrecision2((float)AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).BuildingSlot[j], 1), Images.build));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MaxInfrastructure != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("MaximumInfrastructureLevel"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MaxInfrastructure[j] > 0) ? "+" : "") + CFG.getPrecision2((float)AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MaxInfrastructure[j], 1), Images.infrastructure));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).Devastation != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("Devastation"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).Devastation[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).Devastation[j] * 100.0f, 10) + "%", Images.devastation));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).GrowthRate != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("GrowthRate"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).GrowthRate[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).GrowthRate[j], 10) + "%", Images.populationGrowth));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MonthlyIncome != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("MonthlyIncome"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MonthlyIncome[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MonthlyIncome[j], 100), Images.goldPositive));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).Gold != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("Gold"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).Gold[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).Gold[j], 100), Images.gold));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MonthlyLegacy != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("MonthlyLegacy"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MonthlyLegacy[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MonthlyLegacy[j], 100), Images.legacy));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).IncomeProduction != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("IncomeProduction"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).IncomeProduction[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).IncomeProduction[j], 10) + "%", Images.goods));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ProductionEfficiency != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("ProductionEfficiency"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ProductionEfficiency[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ProductionEfficiency[j], 10) + "%", Images.goods));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).InvestInEconomyCost != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("InvestInEconomyCost"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).InvestInEconomyCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).InvestInEconomyCost[j] * 100.0f, 10) + "%", Game_Calendar.IMG_ECONOMY_UP));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).IncreaseManpowerCost != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("IncreaseManpowerCost"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).IncreaseManpowerCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).IncreaseManpowerCost[j], 10) + "%", Game_Calendar.IMG_MANPOWER_UP));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).IncreaseTaxEfficiencyCost != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("IncreaseTaxEfficiencyCost"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).IncreaseTaxEfficiencyCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).IncreaseTaxEfficiencyCost[j] * 100.0f, 10) + "%", Images.taxUp));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).IncreaseGrowthRateCost != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("IncreaseGrowthRateCost"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).IncreaseGrowthRateCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).IncreaseGrowthRateCost[j] * 100.0f, 10) + "%", Images.populationUp));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).DevelopInfrastructureCost != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("DevelopInfrastructureCost"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).DevelopInfrastructureCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).DevelopInfrastructureCost[j] * 100.0f, 10) + "%", Images.infrastructure));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).GeneralAttack != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("GeneralsAttack"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).GeneralAttack[j] > 0) ? "+" : "") + CFG.getPrecision2((float)AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).GeneralAttack[j], 1), Images.attack));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).GeneralDefense != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("GeneralsDefense"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).GeneralDefense[j] > 0) ? "+" : "") + CFG.getPrecision2((float)AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).GeneralDefense[j], 1), Images.defense));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).UnitsAttack != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("UnitsAttack"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).UnitsAttack[j] > 0) ? "+" : "") + CFG.getPrecision2((float)AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).UnitsAttack[j], 1), Images.attack));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).UnitsDefense != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("UnitsDefense"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).UnitsDefense[j] > 0) ? "+" : "") + CFG.getPrecision2((float)AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).UnitsDefense[j], 1), Images.defense));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MaxMorale != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("MaxMorale"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MaxMorale[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MaxMorale[j] * 100.0f, 10) + "%", Images.morale));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ArmyMovementSpeed != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("ArmyMovementSpeed"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ArmyMovementSpeed[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ArmyMovementSpeed[j], 10) + "%", Images.movementSpeed));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).SiegeEffectiveness != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("SiegeEffectiveness"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).SiegeEffectiveness[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).SiegeEffectiveness[j] * 100.0f, 10) + "%", Images.siege));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ImproveRelationsModifier != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("ImproveRelationsModifier"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ImproveRelationsModifier[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ImproveRelationsModifier[j], 10) + "%", Images.relations));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).IncomeFromVassals != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("IncomeFromVassals"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).IncomeFromVassals[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).IncomeFromVassals[j] * 100.0f, 10) + "%", Images.vassal));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).LoanInterest != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("LoanInterest"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).LoanInterest[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).LoanInterest[j], 10) + "%", Images.loan));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).DiplomacyPoints != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("DiplomacyPoints"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).DiplomacyPoints[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).DiplomacyPoints[j] * 100.0f, 10) + "%", Images.diplomacy));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).RecruitmentTime != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("RecruitmentTime"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).RecruitmentTime[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).RecruitmentTime[j], 10) + "%", Game_Calendar.IMG_MANPOWER_TIME));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).RecruitArmyCost != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("ArmyRecruitmentCost"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).RecruitArmyCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).RecruitArmyCost[j], 10) + "%", Images.gold));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).RecruitArmyFirstLineCost != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("FirstLineArmyRecruitmentCost"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).RecruitArmyFirstLineCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).RecruitArmyFirstLineCost[j], 10) + "%", Images.gold));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).RecruitArmySecondLineCost != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("SecondLineArmyRecruitmentCost"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).RecruitArmySecondLineCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).RecruitArmySecondLineCost[j], 10) + "%", Images.gold));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ArmyMaintenance != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("ArmyMaintenance"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ArmyMaintenance[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ArmyMaintenance[j], 10) + "%", Images.armyMaintenance));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).CoreCost != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("CoreConstruction"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).CoreCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).CoreCost[j], 10) + "%", Images.core));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ReligionCost != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("ReligionConversionCost"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ReligionCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ReligionCost[j], 10) + "%", Images.religion));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MaxNumOfAlliances != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("MaxNumOfAlliances"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MaxNumOfAlliances[j] > 0) ? "+" : "") + CFG.getPrecision2((float)AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MaxNumOfAlliances[j], 1), Images.alliance));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).AdvisorMaxLevel != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("MaximumAdvisorSkillLevel"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).AdvisorMaxLevel[j] > 0) ? "+" : "") + CFG.getPrecision2((float)AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).AdvisorMaxLevel[j], 1), Images.skill));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).AdvisorPoolSize != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("AdvisorPool"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).AdvisorPoolSize[j] > 0) ? "+" : "") + CFG.getPrecision2((float)AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).AdvisorPoolSize[j], 1), Images.council));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MaxNumberOfLoans != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("MaximumNumberOfLoans"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MaxNumberOfLoans[j] > 0) ? "+" : "") + CFG.getPrecision2((float)AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MaxNumberOfLoans[j], 1), Images.loan));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MaximumLevelOfTheMilitaryAcademyForGenerals != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("MaximumLevelOfTheMilitaryAcademyForGenerals"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MaximumLevelOfTheMilitaryAcademyForGenerals[j] > 0) ? "+" : "") + CFG.getPrecision2((float)AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MaximumLevelOfTheMilitaryAcademyForGenerals[j], 1), Images.general));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MaximumLevelOfTheMilitaryAcademy != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("MaximumLevelOfTheMilitaryAcademy"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MaximumLevelOfTheMilitaryAcademy[j] > 0) ? "+" : "") + CFG.getPrecision2((float)AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MaximumLevelOfTheMilitaryAcademy[j], 1), Game_Calendar.IMG_MANPOWER));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MaximumLevelOfTheSupremeCourt != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("MaximumLevelOfTheSupremeCourt"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MaximumLevelOfTheSupremeCourt[j] > 0) ? "+" : "") + CFG.getPrecision2((float)AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MaximumLevelOfTheSupremeCourt[j], 1), Images.stability));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MaximumLevelOfCapitalCity != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("MaximumLevelOfCapitalCity"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MaximumLevelOfCapitalCity[j] > 0) ? "+" : "") + CFG.getPrecision2((float)AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MaximumLevelOfCapitalCity[j], 1), Images.capital));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).AggressiveExpansion != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("AggressiveExpansion"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).AggressiveExpansion[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).AggressiveExpansion[j], 10) + "%", Images.war));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).DiseaseDeathRate != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("DiseasesDeathRate"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).DiseaseDeathRate[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).DiseaseDeathRate[j] * 100.0f, 10) + "%", Images.disease));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ManpowerRecoveryFromADisbandedArmy != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("ManpowerRecoveryFromADisbandedArmy"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ManpowerRecoveryFromADisbandedArmy[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).ManpowerRecoveryFromADisbandedArmy[j] * 100.0f, 10) + "%", Game_Calendar.IMG_MANPOWER_DISBAND));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).AdvisorCost != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("AdvisorCost"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).AdvisorCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).AdvisorCost[j] * 100.0f, 10) + "%", Images.council));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).GeneralCost != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("GeneralCost"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).GeneralCost[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).GeneralCost[j] * 100.0f, 10) + "%", Images.general));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).Discipline != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("Discipline"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).Discipline[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).Discipline[j] * 100.0f, 10) + "%", Images.discipline));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MaximumAmountOfGold != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("MaximumAmountOfGold"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MaximumAmountOfGold[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MaximumAmountOfGold[j], 10), Images.gold));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MaximumAmountOfGold_Percentage != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("MaximumAmountOfGold"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MaximumAmountOfGold_Percentage[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).MaximumAmountOfGold_Percentage[j] * 100.0f, 10) + "%", Images.gold));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).Loot != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("Loot"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).Loot[j] > 0.0f) ? "+" : "") + CFG.getPrecision2(AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).Loot[j] * 100.0f, 10) + "%", Images.loot));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).BattleWidth != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("BattleWidth"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).BattleWidth[j] > 0) ? "+" : "") + CFG.getPrecision2((float)AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).BattleWidth[j], 1), Images.battleWidth));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).RegimentsLimit != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("RegimentsLimit"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).RegimentsLimit[j] > 0) ? "+" : "") + CFG.getPrecision2((float)AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).RegimentsLimit[j], 1), Images.regimentsLimit));
                }
                else if (AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).AllCharactersLifeExpectancy != null) {
                    toSort.add(new Button_Advantage3(0, 0, Game.getCiv(iCivID).lAdvantages.get(i).id, j, Game.lang.get("AllCharactersLifeExpectancy"), ((AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).AllCharactersLifeExpectancy[j] > 0) ? "+" : "") + Game.lang.get("YearsX", AdvantagesManager.advantages.get(Game.getCiv(iCivID).lAdvantages.get(i).id).AllCharactersLifeExpectancy[j]), Images.council));
                }
            }
        }
        return toSort;
    }
    
    @Override
    public void actionCloseMenu() {
        super.actionCloseMenu();
        InGame_Civ.actionOnClose();
    }
    
    static {
        InGame_CivilizationAdvantages.lTime = 0L;
        InGame_CivilizationAdvantages.lTime2 = 0L;
        InGame_CivilizationAdvantages.iMenuWidth = 0;
        InGame_CivilizationAdvantages.iActiveCivID = -1;
        InGame_CivilizationAdvantages.iActiveSortID = -1;
    }
}
