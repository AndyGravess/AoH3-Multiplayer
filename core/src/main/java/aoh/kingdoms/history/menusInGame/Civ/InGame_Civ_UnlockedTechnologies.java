// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Civ;

import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_FlagCenter;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import aoh.kingdoms.history.menu_element.button.ButtonTechnology;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon2;
import aoh.kingdoms.history.map.technology.TechnologyTree;
import aoh.kingdoms.history.menu_element.textStatic.Text_TitleBlue;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.textStatic.Text_TitleBlueSort;
import aoh.kingdoms.history.menu_element.graph.Graph_Vertical;
import aoh.kingdoms.history.menu_element.graph.Graph_Vertical_Data_Type;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Active_Click;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Civ_UnlockedTechnologies extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static long lTime2;
    public static int iSortID;
    
    public InGame_Civ_UnlockedTechnologies() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2;
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        final int buttonYPadding = CFG.PADDING * 2;
        int buttonX = CFG.PADDING + Images.boxTitleBORDERWIDTH;
        int buttonY = buttonYPadding;
        final int buttonH = CFG.isDesktop() ? CFG.BUTTON_HEIGHT3 : CFG.BUTTON_HEIGHT2;
        if (Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() > 0) {
            InGame_Civ.iActiveCivID = Game.getProvince(Game.iActiveProvince).getCivID();
        }
        else if (InGame_Civ.iActiveCivID <= 0) {
            InGame_Civ.iActiveCivID = Game.player.iCivID;
        }
        final int c0W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2 - CFG.PADDING * 4) / 3.0f);
        menuElements.add(new ButtonStatsRectIMG_Active_Click(CFG.getNumberWithSpaces("" + Game.getCiv(InGame_Civ.iActiveCivID).getResearchedTechnologies()), Game_Calendar.IMG_TECHNOLOGY, buttonX, buttonY, c0W, buttonH, ImageManager.getImage(Game_Calendar.IMG_TECHNOLOGY).getWidth(), 0) {
            @Override
            public void actionElement() {
                Game.menuManager.rebuildInGame_Civ();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("OpenTheDiplomacyView"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.diplomacy, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("UnlockedTechnologies") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(CFG.getNumberWithSpaces("" + Game.getCiv(InGame_Civ.iActiveCivID).getResearchedTechnologies()), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        final float fGold = Game.getCiv(InGame_Civ.iActiveCivID).fResearchPerMonth;
        menuElements.add(new ButtonStatsRectIMG_Active_Click("" + CFG.getPrecision2(fGold, (fGold < 10.0f) ? 100 : ((fGold < 100.0f) ? 10 : 1)), Game_Calendar.IMG_TECHNOLOGY, buttonX, buttonY, c0W, buttonH, ImageManager.getImage(Game_Calendar.IMG_TECHNOLOGY).getWidth(), 0) {
            @Override
            public void actionElement() {
                Game.menuManager.rebuildInGame_Civ();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("OpenTheDiplomacyView"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.diplomacy, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ResearchPerMonth") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(Game.getCiv(InGame_Civ.iActiveCivID).fResearchPerMonth, 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Active_Click("" + Game.getCiv(InGame_Civ.iActiveCivID).iAdvantagesSize, Images.advantages, buttonX, buttonY, c0W, buttonH, ImageManager.getImage(Game_Calendar.IMG_TECHNOLOGY).getWidth(), 0) {
            @Override
            public void actionElement() {
                Game.menuManager.rebuildInGame_Civ_UnlockedAdvantages();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.getCiv(InGame_Civ.iActiveCivID).getCivName() + ": " + Game.lang.get("CivilizationAdvantages"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.advantages, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final Graph_Vertical graphVertical = new Graph_Vertical(Graph_Vertical_Data_Type.CIVS_UNLOCKED_TECHS, Game.lang.get("Civilizations"), Game.lang.get("Civilizations"), paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT * 3, true) {};
        menuElements.add(graphVertical);
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        buttonX = Images.boxTitleBORDERWIDTH;
        final int statW = menuWidth - paddingLeft * 2 - CFG.PADDING - ImageManager.getImage(Images.techGray).getWidth();
        final int statWTitle = menuWidth - Images.boxTitleBORDERWIDTH * 2 - CFG.PADDING - ImageManager.getImage(Images.techGray).getWidth();
        menuElements.add(new Text_TitleBlueSort(InGame_Civ_UnlockedTechnologies.iSortID == 0 || InGame_Civ_UnlockedTechnologies.iSortID == 1, InGame_Civ_UnlockedTechnologies.iSortID == 1, Game.lang.get("ResearchCost"), -1, buttonX, buttonY, statWTitle, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Civ_UnlockedTechnologies.iSortID == 0) {
                    InGame_Civ_UnlockedTechnologies.iSortID = 1;
                }
                else {
                    InGame_Civ_UnlockedTechnologies.iSortID = 0;
                }
                Game.menuManager.rebuildInGame_Civ_UnlockedTechnologies();
                InGame_Civ_UnlockedTechnologies.lTime = 0L;
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
        menuElements.add(new Text_TitleBlue(Game.lang.get("UnlockedTechnologies"), -1, buttonX, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2 - statWTitle, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final List<Integer> tTechnologies = new ArrayList<Integer>();
        try {
            for (int i = 0, iSize = TechnologyTree.iTechnologySize; i < iSize; ++i) {
                if (Game.getCiv(InGame_Civ.iActiveCivID).getTechResearched(i)) {
                    tTechnologies.add(i);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        if (!tTechnologies.isEmpty()) {
            while (tTechnologies.size() > 0) {
                int toAddID = 0;
                if (InGame_Civ_UnlockedTechnologies.iSortID == 0) {
                    for (int o = 1; o < tTechnologies.size(); ++o) {
                        if (TechnologyTree.lTechnology.get(tTechnologies.get(toAddID)).getResearchCost() < TechnologyTree.lTechnology.get(tTechnologies.get(o)).getResearchCost()) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Civ_UnlockedTechnologies.iSortID == 1) {
                    for (int o = 1; o < tTechnologies.size(); ++o) {
                        if (TechnologyTree.lTechnology.get(tTechnologies.get(toAddID)).getResearchCost() > TechnologyTree.lTechnology.get(tTechnologies.get(o)).getResearchCost()) {
                            toAddID = o;
                        }
                    }
                }
                buttonX = paddingLeft;
                menuElements.add(new TextIcon2("" + CFG.getPrecision2(TechnologyTree.getResearchCost(tTechnologies.get(toAddID), Game.player.iCivID), 10), Game_Calendar.IMG_TECHNOLOGY, buttonX, buttonY, statW, ImageManager.getImage(Images.techGray).getHeight()) {
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("ResearchCost") + ": ", CFG.FONT_BOLD));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(this.getText(), CFG.FONT_BOLD, Colors.COLOR_TEXT_RESEARCH));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                    
                    @Override
                    public int getTextH() {
                        return CFG.TEXT_HEIGHT + CFG.PADDING * 4;
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new ButtonTechnology(Images.techResearched, (int)tTechnologies.get(toAddID), buttonX, buttonY, false, Game.player.playerData.techQueue.getTechIsInQueue(tTechnologies.get(toAddID))) {
                    @Override
                    public void actionElement() {
                    }
                });
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
                tTechnologies.remove(toAddID);
            }
        }
        else {
            menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG_FlagCenter(Game.getCiv(InGame_Civ.iActiveCivID).getCivName(), Game.lang.get("UnlockedTechnologies"), false, false, Images.title500) {
            @Override
            public int getFlagCivID() {
                return InGame_Civ.iActiveCivID;
            }
            
            @Override
            public long getTime() {
                return InGame_Civ_UnlockedTechnologies.lTime2;
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_Civ_UnlockedTechnologies.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_Civ_UnlockedTechnologies.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.civInfoOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.civInfoOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.civInfoOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_Civ_UnlockedTechnologies.lTime = CFG.currentTimeMillis;
        InGame_Civ_UnlockedTechnologies.lTime2 = InGame_Civ_UnlockedTechnologies.lTime;
    }
    
    @Override
    public void actionCloseMenu() {
        super.actionCloseMenu();
        InGame_Civ.actionOnClose();
    }
    
    static {
        InGame_Civ_UnlockedTechnologies.lTime = 0L;
        InGame_Civ_UnlockedTechnologies.lTime2 = 0L;
        InGame_Civ_UnlockedTechnologies.iSortID = 0;
    }
}
