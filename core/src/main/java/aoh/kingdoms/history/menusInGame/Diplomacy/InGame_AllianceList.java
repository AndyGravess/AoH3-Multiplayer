// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Diplomacy;

import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_Special;
import aoh.kingdoms.history.menu.MenuManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonusFlag;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ_Compare;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Rank;
import aoh.kingdoms.history.map.civilization.CivilizationRanking;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Bonuses;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.textStatic.Text_TitleBlueSort;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import aoh.kingdoms.history.map.diplomacy.DiplomacyManager;
import aoh.kingdoms.history.mainGame.Game;
import java.util.ArrayList;
import java.util.List;
import aoh.kingdoms.history.menu.Menu;

public class InGame_AllianceList extends Menu
{
    public static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static int iSortID;
    
    public static final List<Integer> buildList(final int civID, final int limit) {
        final List<Integer> civs = new ArrayList<Integer>();
        final List<Float> distance = new ArrayList<Float>();
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).getNumOfProvinces() > 0 && i != civID && !Game.getCiv(civID).diplomacy.haveAlliance(i) && Game.getCiv(i).getPuppetOfCivID() != civID && Game.getCiv(civID).getPuppetOfCivID() != i) {
                final int tScore = DiplomacyManager.getAlliance_Score(civID, i);
                if (tScore > 0) {
                    civs.add(i);
                    distance.add(Game.getDistance_PercOfMax(Game.getCiv(civID).getCapitalProvinceID(), Game.getCiv(i).getCapitalProvinceID()));
                }
            }
        }
        if (civs.size() <= limit) {
            return civs;
        }
        final List<Integer> out = new ArrayList<Integer>();
        while (out.size() <= limit) {
            int bestID = 0;
            for (int j = 1, iSize = civs.size(); j < iSize; ++j) {
                if (distance.get(j) < distance.get(bestID)) {
                    bestID = j;
                }
            }
            out.add(civs.get(bestID));
            civs.remove(bestID);
            distance.remove(bestID);
        }
        return out;
    }
    
    public InGame_AllianceList() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title600).getHeight();
        final int menuWidth = ImageManager.getImage(Images.insideTop600).getWidth();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        int buttonY = 0;
        int buttonX = paddingLeft;
        final List<Integer> allianceCivs = buildList(Game.player.iCivID, GameValues.rivals.NUM_OF_RIVALS_TO_CHOOSE_FROM);
        final int allianceCivsSize = allianceCivs.size();
        int r0W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.4f);
        int r1W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.2f);
        buttonX = Images.boxTitleBORDERWIDTH;
        menuElements.add(new Text_TitleBlueSort(InGame_AllianceList.iSortID == 6 || InGame_AllianceList.iSortID == 7, InGame_AllianceList.iSortID == 6, Game.lang.get("Ranking"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_AllianceList.iSortID == 6) {
                    InGame_AllianceList.iSortID = 7;
                }
                else {
                    InGame_AllianceList.iSortID = 6;
                }
                Game.menuManager.rebuildInGame_AllianceList_SavePos();
                InGame_RivalsList.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Ranking"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(InGame_AllianceList.iSortID == 0 || InGame_AllianceList.iSortID == 1, InGame_AllianceList.iSortID == 1, Game.lang.get("Name"), -1, buttonX, buttonY, r0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_AllianceList.iSortID == 0) {
                    InGame_AllianceList.iSortID = 1;
                }
                else {
                    InGame_AllianceList.iSortID = 0;
                }
                Game.menuManager.rebuildInGame_AllianceList_SavePos();
                InGame_RivalsList.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Name"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(InGame_AllianceList.iSortID == 2 || InGame_AllianceList.iSortID == 3, InGame_AllianceList.iSortID == 3, Game.lang.get("Opinion"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_AllianceList.iSortID == 2) {
                    InGame_AllianceList.iSortID = 3;
                }
                else {
                    InGame_AllianceList.iSortID = 2;
                }
                Game.menuManager.rebuildInGame_AllianceList_SavePos();
                InGame_RivalsList.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Opinion"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(InGame_AllianceList.iSortID == 4 || InGame_AllianceList.iSortID == 5, InGame_AllianceList.iSortID == 5, Game.lang.get("RegimentsLimit"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_AllianceList.iSortID == 4) {
                    InGame_AllianceList.iSortID = 5;
                }
                else {
                    InGame_AllianceList.iSortID = 4;
                }
                Game.menuManager.rebuildInGame_AllianceList_SavePos();
                InGame_RivalsList.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("RegimentsLimit"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        buttonX = paddingLeft;
        r0W = (int)((menuWidth - paddingLeft * 2 - CFG.PADDING * 3) * 0.4f);
        r1W = (int)((menuWidth - paddingLeft * 2 - CFG.PADDING * 3) * 0.2f);
        final int buttonH = CFG.isDesktop() ? CFG.BUTTON_HEIGHT4 : CFG.BUTTON_HEIGHT2;
        if (allianceCivs.isEmpty()) {
            menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            if (Game.getCiv(Game.player.iCivID).diplomacy.alliance.size() >= DiplomacyManager.getMaxNumberOfAlliances(Game.player.iCivID)) {
                menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.getCiv(Game.player.iCivID).getCivName() + ", " + Game.lang.get("MaxNumOfAlliances") + ": ", "" + Game.getCiv(Game.player.iCivID).diplomacy.alliance.size() + " / " + DiplomacyManager.getMaxNumberOfAlliances(Game.player.iCivID), Images.alliance, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT3, ImageManager.getImage(Images.alliance).getWidth(), CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL) {
                    @Override
                    public Color getColorBonus() {
                        return Colors.getColorNegative(false, this.getIsHovered());
                    }
                });
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            else {
                menuElements.add(new Text_StaticBG(Game.lang.get("Tip") + ": " + Game.lang.get("ImproveRelations"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2) {
                    @Override
                    protected Color getColor(final boolean isActive) {
                        return Colors.HOVER_POSITIVE;
                    }
                });
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
        }
        while (allianceCivs.size() > 0) {
            int toAddID = 0;
            if (InGame_AllianceList.iSortID == 6) {
                for (int o = 1; o < allianceCivs.size(); ++o) {
                    if (Game.getCiv(allianceCivs.get(toAddID)).iCivRankPosition > Game.getCiv(allianceCivs.get(o)).iCivRankPosition) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_AllianceList.iSortID == 7) {
                for (int o = 1; o < allianceCivs.size(); ++o) {
                    if (Game.getCiv(allianceCivs.get(toAddID)).iCivRankPosition < Game.getCiv(allianceCivs.get(o)).iCivRankPosition) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_AllianceList.iSortID == 0) {
                for (int o = 1; o < allianceCivs.size(); ++o) {
                    if (CFG.compareAlphabetic_TwoString(Game.getCiv(allianceCivs.get(toAddID)).getCivName(), Game.getCiv(allianceCivs.get(o)).getCivName())) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_AllianceList.iSortID == 1) {
                for (int o = 1; o < allianceCivs.size(); ++o) {
                    if (CFG.compareAlphabetic_TwoString(Game.getCiv(allianceCivs.get(o)).getCivName(), Game.getCiv(allianceCivs.get(toAddID)).getCivName())) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_AllianceList.iSortID == 2) {
                for (int o = 1; o < allianceCivs.size(); ++o) {
                    if (Game.getCiv(allianceCivs.get(toAddID)).diplomacy.getRelation(Game.player.iCivID) < Game.getCiv(allianceCivs.get(o)).diplomacy.getRelation(Game.player.iCivID)) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_AllianceList.iSortID == 3) {
                for (int o = 1; o < allianceCivs.size(); ++o) {
                    if (Game.getCiv(allianceCivs.get(toAddID)).diplomacy.getRelation(Game.player.iCivID) > Game.getCiv(allianceCivs.get(o)).diplomacy.getRelation(Game.player.iCivID)) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_AllianceList.iSortID == 4) {
                for (int o = 1; o < allianceCivs.size(); ++o) {
                    if (Game.getCiv(allianceCivs.get(toAddID)).iRegimentsLimit < Game.getCiv(allianceCivs.get(o)).iRegimentsLimit) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_AllianceList.iSortID == 5) {
                for (int o = 1; o < allianceCivs.size(); ++o) {
                    if (Game.getCiv(allianceCivs.get(toAddID)).iRegimentsLimit > Game.getCiv(allianceCivs.get(o)).iRegimentsLimit) {
                        toAddID = o;
                    }
                }
            }
            menuElements.add(new ButtonStatsRectIMG_Rank("" + Game.getCiv(allianceCivs.get(toAddID)).iCivRankPosition, CivilizationRanking.getCivilizationRanking_IMG_STAR_CIVID(allianceCivs.get(toAddID)), buttonX, buttonY, r1W, buttonH, ImageManager.getImage(Images.rankGold).getWidth()) {
                int id = 0;
                
                @Override
                public void setCurrent(final int nCurrent) {
                    this.id = nCurrent;
                }
                
                @Override
                protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
                    super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
                    if (this.id == InGame_Civ_Compare.civLeft_Rank) {
                        oSB.setColor(Colors.COLOR_NOTIFICATION_OVER_GREEN);
                        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
                        oSB.setColor(Color.WHITE);
                    }
                }
                
                @Override
                public void actionElement() {
                    if (InGame_Civ_Compare.civLeft_Rank > 0) {
                        if (InGame_Civ_Compare.civLeft == this.id) {
                            Game.menuManager.addToast_Error(Game.lang.get("Compare") + ": " + Game.getCiv(InGame_Civ_Compare.civLeft).getCivName() + " == " + Game.getCiv(this.id).getCivName());
                        }
                        else {
                            InGame_Civ_Compare.civRight_Rank = this.id;
                            InGame_Civ_Compare.civLeft = InGame_Civ_Compare.civLeft_Rank;
                            InGame_Civ_Compare.civRight = InGame_Civ_Compare.civRight_Rank;
                            Game.menuManager.setVisibleInGame_PopUp(false);
                            Game.menuManager.rebuildInGame_Civ_Compare();
                        }
                    }
                    else {
                        InGame_Civ_Compare.civLeft_Rank = this.id;
                    }
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("CompareCivilizations"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.compare, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    if (InGame_Civ_Compare.civLeft_Rank > 0) {
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.lang.get("Compare") + ": ", Game.getCiv(InGame_Civ_Compare.civLeft_Rank).getCivName(), InGame_Civ_Compare.civLeft_Rank, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.lang.get("Compare") + ": ", "--", 0, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                    else {
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.lang.get("Compare") + ": ", "--", 0, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.lang.get("Compare") + ": ", "--", 0, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("CivilizationRank") + ": ", this.getText(), this.imageID, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            menuElements.get(menuElements.size() - 1).setCurrent(allianceCivs.get(toAddID));
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new Text_StaticBG_ID_FlagCiv("" + Game.getCiv(allianceCivs.get(toAddID)).getCivName(), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, buttonX, buttonY, r0W, buttonH, (int)allianceCivs.get(toAddID)) {
                @Override
                public void actionElement() {
                    if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 18 && InGame_Alliance.iCivID == this.getCurrent()) {
                        Game.menuManager.setVisibleInGame_PopUp(false);
                    }
                    else {
                        Game.menuManager.rebuildInGame_Alliance(this.getCurrent());
                    }
                }
                
                @Override
                public void buildElementHover() {
                    this.menuElementHover = CivilizationRanking.getHover_CivilizationRanking(this.getCurrent(), false, false);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new Text_StaticBG_ID_Special("" + CFG.getPrecision2((float)(int)Game.getCiv(allianceCivs.get(toAddID)).diplomacy.getRelation(Game.player.iCivID), 1), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r1W, buttonH, (int)allianceCivs.get(toAddID)) {
                @Override
                public void buildElementHover() {
                    this.menuElementHover = CivilizationRanking.getHover_CivilizationRanking(this.getCurrent(), false, false);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            final float tDiff = (Game.getCiv(allianceCivs.get(toAddID)).iRegimentsLimit / (float)Game.getCiv(Game.player.iCivID).iRegimentsLimit - 1.0f) * 100.0f;
            menuElements.add(new Text_StaticBG_ID_Special(((tDiff > 0.0f) ? "+" : "") + CFG.getPrecision2(tDiff, 1) + "%", CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r1W, buttonH, (int)allianceCivs.get(toAddID)) {
                @Override
                public void buildElementHover() {
                    this.menuElementHover = CivilizationRanking.getHover_CivilizationRanking(this.getCurrent(), false, false);
                }
            });
            buttonX = paddingLeft;
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            allianceCivs.remove(toAddID);
        }
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - titleHeight - menuY);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, buttonY)));
        this.initMenu(new MenuTitleIMG(Game.lang.get("OfferAlliance") + " [" + allianceCivsSize + "]", true, false, Images.title600) {
            @Override
            public long getTime() {
                return InGame_AllianceList.lTime;
            }
        }, CFG.GAME_WIDTH / 2 - menuWidth / 2, menuY, menuWidth, menuHeight, menuElements, false, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_AllianceList.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateY = iTranslateY - CFG.BUTTON_HEIGHT * 3 / 5 + (int)(CFG.BUTTON_HEIGHT * 3 / 5 * ((CFG.currentTimeMillis - InGame_AllianceList.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop600, Images.insideBot600);
        ImageManager.getImage(Images.outlinerOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.outlinerOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.outlinerOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_AllianceList.lTime = CFG.currentTimeMillis;
    }
    
    static {
        InGame_AllianceList.lTime = 0L;
        InGame_AllianceList.iSortID = 6;
    }
}
