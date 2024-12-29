// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Diplomacy;

import aoh.kingdoms.history.menu_element.Status;
import java.util.Iterator;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_Special;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv_Religion;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonusFlag;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ_Compare;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Rank;
import aoh.kingdoms.history.map.civilization.CivilizationRanking;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.textStatic.Text_TitleBlueSort;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon2_HorizontalSplit;
import aoh.kingdoms.history.map.diplomacy.Diplomacy;
import aoh.kingdoms.history.menu_element.button.ButtonFlag_Diplomacy;
import aoh.kingdoms.history.map.RivalsManager;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_RivalsList extends Menu
{
    public static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static int iSortID;
    
    public InGame_RivalsList() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title600).getHeight();
        final int menuWidth = ImageManager.getImage(Images.insideTop600).getWidth();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        int buttonY = 0;
        int buttonX = paddingLeft;
        final List<Integer> rivals = RivalsManager.buildRivals(Game.player.iCivID, GameValues.rivals.NUM_OF_RIVALS_TO_CHOOSE_FROM);
        final int rivalsSize = rivals.size();
        final int iActiveCivID = Game.player.iCivID;
        final int leftW = menuWidth - paddingLeft * 2 - ButtonFlag_Diplomacy.getButtonWidth() * 4 - CFG.PADDING * 4;
        final int lineH = ImageManager.getImage(Images.flagDiplomacyOver).getHeight() + CFG.PADDING * 2;
        final int maxIconW = ImageManager.getImage(Images.relations).getWidth() + CFG.PADDING * 2;
        int linesAdded = 0;
        try {
            if (Game.getCiv(iActiveCivID).diplomacy.rivals.size() > 0) {
                buttonX = paddingLeft + leftW + CFG.PADDING;
                for (final Diplomacy.DiplomacyData data : Game.getCiv(iActiveCivID).diplomacy.rivals.values()) {
                    if (buttonX + ButtonFlag_Diplomacy.getButtonWidth() > menuWidth) {
                        buttonX = paddingLeft + leftW + CFG.PADDING;
                        buttonY += lineH;
                        ++linesAdded;
                    }
                    menuElements.add(new ButtonFlag_Diplomacy(data.iCivID, buttonX, buttonY + CFG.PADDING, true));
                    buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                }
                menuElements.add(new TextIcon2_HorizontalSplit(Game.lang.get("Rivals"), Images.rivals, paddingLeft, buttonY - lineH * linesAdded, leftW, lineH * (linesAdded + 1), maxIconW, menuWidth - paddingLeft * 2));
                linesAdded = 0;
                buttonY = Math.max(buttonY + lineH, menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight());
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        int r0W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.4f);
        int r1W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.2f);
        buttonX = Images.boxTitleBORDERWIDTH;
        menuElements.add(new Text_TitleBlueSort(InGame_RivalsList.iSortID == 6 || InGame_RivalsList.iSortID == 7, InGame_RivalsList.iSortID == 6, Game.lang.get("Ranking"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_RivalsList.iSortID == 6) {
                    InGame_RivalsList.iSortID = 7;
                }
                else {
                    InGame_RivalsList.iSortID = 6;
                }
                Game.menuManager.rebuildInGame_RivalsList_SavePos();
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
        menuElements.add(new Text_TitleBlueSort(InGame_RivalsList.iSortID == 0 || InGame_RivalsList.iSortID == 1, InGame_RivalsList.iSortID == 1, Game.lang.get("Name"), -1, buttonX, buttonY, r0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_RivalsList.iSortID == 0) {
                    InGame_RivalsList.iSortID = 1;
                }
                else {
                    InGame_RivalsList.iSortID = 0;
                }
                Game.menuManager.rebuildInGame_RivalsList_SavePos();
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
        menuElements.add(new Text_TitleBlueSort(InGame_RivalsList.iSortID == 2 || InGame_RivalsList.iSortID == 3, InGame_RivalsList.iSortID == 3, Game.lang.get("Opinion"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_RivalsList.iSortID == 2) {
                    InGame_RivalsList.iSortID = 3;
                }
                else {
                    InGame_RivalsList.iSortID = 2;
                }
                Game.menuManager.rebuildInGame_RivalsList_SavePos();
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
        menuElements.add(new Text_TitleBlueSort(InGame_RivalsList.iSortID == 4 || InGame_RivalsList.iSortID == 5, InGame_RivalsList.iSortID == 5, Game.lang.get("RegimentsLimit"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_RivalsList.iSortID == 4) {
                    InGame_RivalsList.iSortID = 5;
                }
                else {
                    InGame_RivalsList.iSortID = 4;
                }
                Game.menuManager.rebuildInGame_RivalsList_SavePos();
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
        if (rivals.isEmpty()) {
            menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        while (rivals.size() > 0) {
            int toAddID = 0;
            if (InGame_RivalsList.iSortID == 6) {
                for (int o = 1; o < rivals.size(); ++o) {
                    if (Game.getCiv(rivals.get(toAddID)).iCivRankPosition > Game.getCiv(rivals.get(o)).iCivRankPosition) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_RivalsList.iSortID == 7) {
                for (int o = 1; o < rivals.size(); ++o) {
                    if (Game.getCiv(rivals.get(toAddID)).iCivRankPosition < Game.getCiv(rivals.get(o)).iCivRankPosition) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_RivalsList.iSortID == 0) {
                for (int o = 1; o < rivals.size(); ++o) {
                    if (CFG.compareAlphabetic_TwoString(Game.getCiv(rivals.get(toAddID)).getCivName(), Game.getCiv(rivals.get(o)).getCivName())) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_RivalsList.iSortID == 1) {
                for (int o = 1; o < rivals.size(); ++o) {
                    if (CFG.compareAlphabetic_TwoString(Game.getCiv(rivals.get(o)).getCivName(), Game.getCiv(rivals.get(toAddID)).getCivName())) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_RivalsList.iSortID == 2) {
                for (int o = 1; o < rivals.size(); ++o) {
                    if (Game.getCiv(rivals.get(toAddID)).diplomacy.getRelation(Game.player.iCivID) < Game.getCiv(rivals.get(o)).diplomacy.getRelation(Game.player.iCivID)) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_RivalsList.iSortID == 3) {
                for (int o = 1; o < rivals.size(); ++o) {
                    if (Game.getCiv(rivals.get(toAddID)).diplomacy.getRelation(Game.player.iCivID) > Game.getCiv(rivals.get(o)).diplomacy.getRelation(Game.player.iCivID)) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_RivalsList.iSortID == 4) {
                for (int o = 1; o < rivals.size(); ++o) {
                    if (Game.getCiv(rivals.get(toAddID)).iRegimentsLimit < Game.getCiv(rivals.get(o)).iRegimentsLimit) {
                        toAddID = o;
                    }
                }
            }
            else if (InGame_RivalsList.iSortID == 5) {
                for (int o = 1; o < rivals.size(); ++o) {
                    if (Game.getCiv(rivals.get(toAddID)).iRegimentsLimit > Game.getCiv(rivals.get(o)).iRegimentsLimit) {
                        toAddID = o;
                    }
                }
            }
            menuElements.add(new ButtonStatsRectIMG_Rank("" + Game.getCiv(rivals.get(toAddID)).iCivRankPosition, CivilizationRanking.getCivilizationRanking_IMG_STAR_CIVID(rivals.get(toAddID)), buttonX, buttonY, r1W, buttonH, ImageManager.getImage(Images.rankGold).getWidth()) {
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
            menuElements.get(menuElements.size() - 1).setCurrent(rivals.get(toAddID));
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new Text_StaticBG_ID_FlagCiv_Religion("" + Game.getCiv(rivals.get(toAddID)).getCivName(), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, buttonX, buttonY, r0W, buttonH, (int)rivals.get(toAddID)) {
                @Override
                public void actionElement() {
                    InGame_Rivals.rivalCivID = this.getCurrent();
                    InGame_Rivals.backToRivalsList = true;
                    Game.menuManager.rebuildInGame_Rivals();
                }
                
                @Override
                public void buildElementHover() {
                    this.menuElementHover = CivilizationRanking.getHover_CivilizationRanking(this.getCurrent(), false, false);
                }
                
                @Override
                public void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
                    this.drawTextCiv(oSB, iTranslateX, iTranslateY, isActive);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new Text_StaticBG_ID_Special("" + CFG.getPrecision2((float)(int)Game.getCiv(rivals.get(toAddID)).diplomacy.getRelation(Game.player.iCivID), 1), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r1W, buttonH, (int)rivals.get(toAddID)) {
                @Override
                public void buildElementHover() {
                    this.menuElementHover = CivilizationRanking.getHover_CivilizationRanking(this.getCurrent(), false, false);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            final float tDiff = (Game.getCiv(rivals.get(toAddID)).iRegimentsLimit / (float)Game.getCiv(Game.player.iCivID).iRegimentsLimit - 1.0f) * 100.0f;
            menuElements.add(new Text_StaticBG_ID_Special(((tDiff > 0.0f) ? "+" : "") + CFG.getPrecision2(tDiff, 1) + "%", CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r1W, buttonH, (int)rivals.get(toAddID)) {
                @Override
                public void buildElementHover() {
                    this.menuElementHover = CivilizationRanking.getHover_CivilizationRanking(this.getCurrent(), false, false);
                }
            });
            buttonX = paddingLeft;
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            rivals.remove(toAddID);
        }
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - titleHeight - menuY);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, buttonY)));
        this.initMenu(new MenuTitleIMG(Game.lang.get("ChooseYourRivals") + ": " + Game.getCiv(Game.player.iCivID).diplomacy.rivals.size() + " / " + GameValues.rivals.RIVALS_LIMIT + " [" + rivalsSize + "]", true, false, Images.title600) {
            @Override
            public long getTime() {
                return InGame_RivalsList.lTime;
            }
        }, CFG.GAME_WIDTH / 2 - menuWidth / 2, menuY, menuWidth, menuHeight, menuElements, false, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_RivalsList.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateY = iTranslateY - CFG.BUTTON_HEIGHT * 3 / 5 + (int)(CFG.BUTTON_HEIGHT * 3 / 5 * ((CFG.currentTimeMillis - InGame_RivalsList.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop600, Images.insideBot600);
        ImageManager.getImage(Images.outlinerOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.outlinerOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.outlinerOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_RivalsList.lTime = CFG.currentTimeMillis;
    }
    
    static {
        InGame_RivalsList.lTime = 0L;
        InGame_RivalsList.iSortID = 6;
    }
}
