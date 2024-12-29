// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menus.NewGame;

import aoh.kingdoms.history.menu_element.Status;
import java.util.Iterator;
import com.badlogic.gdx.files.FileHandle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.map.diplomacy.Diplomacy;
import aoh.kingdoms.history.map.diplomacy.Vassal;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon2_HorizontalSplit;
import aoh.kingdoms.history.menu_element.button.ButtonFlag_Diplomacy;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2Center;
import aoh.kingdoms.history.menu_element.button.Button_Advantage3;
import aoh.kingdoms.history.menusInGame.InGame_CivilizationAdvantages;
import aoh.kingdoms.history.mainGame.Game_Ages;
import aoh.kingdoms.history.menu_element.textStatic.Text_Desc_SimpleNewGame;
import aoh.kingdoms.history.mainGame.FileManager;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon2;
import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG;
import aoh.kingdoms.history.map.civilization.CivilizationRanking;
import aoh.kingdoms.history.menu_element.button.ButtonReligion2;
import aoh.kingdoms.history.menu_element.button.ButtonIdeology2;
import aoh.kingdoms.history.menu_element.button.ButtonIdeology;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu.ColorPicker;
import aoh.kingdoms.history.menu_element.button.ButtonFlag_Animation;
import aoh.kingdoms.history.menu_element.button.ButtonFlag;
import aoh.kingdoms.history.menu_element.button.ButtonRuler;
import aoh.kingdoms.history.map.RulersManager;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class NewGameCiv extends Menu
{
    public static int iActiveCivID;
    public int flagX;
    public int flagY;
    public int flagW;
    public int flagH;
    public static boolean expandCivDesc;
    
    public NewGameCiv() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2;
        final int titleHeight = ImageManager.getImage(Images.title1Red).getHeight();
        final int menuWidth = ImageManager.getImage(Images.insideTop).getWidth();
        final int menuX = CFG.GAME_WIDTH - menuWidth - CFG.PADDING * 2;
        final int menuY = CFG.GAME_HEIGHT / 10;
        int buttonY;
        final int buttonYPadding = buttonY = CFG.PADDING * 2;
        RulersManager.loadRulerIMG(NewGameCiv.iActiveCivID = Game.player.iCivID);
        menuElements.add(new ButtonRuler(NewGameCiv.iActiveCivID, menuWidth - ButtonRuler.getButtonWidth() - paddingLeft, buttonY));
        this.flagX = paddingLeft;
        this.flagY = buttonY;
        this.flagW = menuWidth - paddingLeft * 2 - CFG.PADDING - ButtonRuler.getButtonWidth();
        buttonY += CFG.PADDING * 3;
        menuElements.add(new ButtonFlag_Animation((menuWidth - ButtonRuler.getButtonWidth() - Images.boxTitleBORDERWIDTH) / 2 - ButtonFlag.getButtonWidth() / 2, buttonY, true) {
            @Override
            public int getFlagCivID() {
                return NewGameCiv.iActiveCivID;
            }
            
            @Override
            public void actionElement() {
                if (Game.menuManager.getColorPicker().getVisible()) {
                    Game.menuManager.getColorPicker().hideColorPicker();
                }
                else {
                    ColorPicker.ACTIVE_CIV_ID = NewGameCiv.iActiveCivID;
                    Game.menuManager.getColorPicker().setActiveRGBColor(Game.getCiv(NewGameCiv.iActiveCivID).getR(), Game.getCiv(NewGameCiv.iActiveCivID).getG(), Game.getCiv(NewGameCiv.iActiveCivID).getB());
                    Game.menuManager.getColorPicker().setVisible(true, ColorPicker.PickerAction.CIV_COLOR_NEWGAME);
                    Game.menuManager.getColorPicker().setPosX(CFG.BUTTON_WIDTH);
                    Game.menuManager.getColorPicker().setPosY(CFG.GAME_HEIGHT / 10);
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("ShowHideColorPicker"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.player.iCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
                oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5f));
                Renderer.drawBox(oSB, Images.statsRectBG, NewGameCiv.this.flagX + iTranslateX, NewGameCiv.this.flagY + iTranslateY, NewGameCiv.this.flagW, NewGameCiv.this.flagH, 1.0f);
                oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5f));
                Images.gradientXY.draw(oSB, NewGameCiv.this.flagX + iTranslateX, NewGameCiv.this.flagY + iTranslateY, NewGameCiv.this.flagW, NewGameCiv.this.flagH);
                oSB.setColor(Color.WHITE);
                oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.55f));
                Images.gradientXY.draw(oSB, NewGameCiv.this.flagX + iTranslateX, NewGameCiv.this.flagY + iTranslateY, NewGameCiv.this.flagW, NewGameCiv.this.flagH / 2, false, true);
                Images.gradientXY.draw(oSB, NewGameCiv.this.flagX + iTranslateX, NewGameCiv.this.flagY + NewGameCiv.this.flagH / 2 + iTranslateY, NewGameCiv.this.flagW, NewGameCiv.this.flagH / 2, false, false);
                Images.gradientFull.draw(oSB, NewGameCiv.this.flagX + iTranslateX, NewGameCiv.this.flagY + NewGameCiv.this.flagH - 2 + iTranslateY, NewGameCiv.this.flagW, 1);
                Images.gradientFull.draw(oSB, NewGameCiv.this.flagX + iTranslateX, NewGameCiv.this.flagY + 1 + iTranslateY, NewGameCiv.this.flagW, 1);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.2f));
                Images.gradientFull.draw(oSB, NewGameCiv.this.flagX + iTranslateX, NewGameCiv.this.flagY + iTranslateY, NewGameCiv.this.flagW, 1);
                Images.gradientFull.draw(oSB, NewGameCiv.this.flagX + iTranslateX, NewGameCiv.this.flagY + NewGameCiv.this.flagH - 1 + iTranslateY, NewGameCiv.this.flagW, 1);
                oSB.setColor(Color.WHITE);
                if (this.getIsHovered()) {
                    oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 1.0f));
                    Renderer.drawBox(oSB, Images.statsRectBGBorder, NewGameCiv.this.flagX + iTranslateX, NewGameCiv.this.flagY + iTranslateY, NewGameCiv.this.flagW, NewGameCiv.this.flagH, 1.0f);
                    oSB.setColor(Color.WHITE);
                }
                super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 3;
        this.flagH = buttonY - this.flagY - CFG.PADDING;
        ButtonIdeology.maxHeight = Math.max(Game.ideologiesManager.maxHeight, Game.religionManager.maxHeight);
        int tWidth = (menuWidth - ButtonRuler.getButtonWidth() - paddingLeft * 2) / 2 - CFG.PADDING;
        menuElements.add(new ButtonIdeology2(Game.getCiv(NewGameCiv.iActiveCivID).getIdeologyID(), paddingLeft, buttonY, tWidth, ButtonRuler.getButtonHeight() - buttonY + CFG.PADDING * 2));
        menuElements.add(new ButtonReligion2(Game.getCiv(NewGameCiv.iActiveCivID).getReligionID(), paddingLeft + CFG.PADDING + tWidth, buttonY, tWidth, ButtonRuler.getButtonHeight() - buttonY + CFG.PADDING * 2));
        buttonY = menuElements.get(0).getPosY() + menuElements.get(0).getHeight() + CFG.PADDING;
        int buttonX = paddingLeft;
        tWidth = (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2;
        menuElements.add(new ButtonStatsRectIMG("" + Game.getCiv(NewGameCiv.iActiveCivID).iCivRankPosition, CivilizationRanking.getCivilizationRanking_IMG_STAR_CIVID(NewGameCiv.iActiveCivID), buttonX, buttonY, tWidth, CFG.TEXT_HEIGHT + CFG.PADDING * 5, ImageManager.getImage(Images.vassal).getWidth() + CFG.PADDING * 2) {
            @Override
            public void buildElementHover() {
                this.menuElementHover = CivilizationRanking.getHover_CivilizationRanking(NewGameCiv.iActiveCivID, false, false);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG((Game.getCiv(NewGameCiv.iActiveCivID).getCapitalProvinceID() >= 0) ? Game.getProvince(Game.getCiv(NewGameCiv.iActiveCivID).getCapitalProvinceID()).getProvinceName() : "", Images.capital, buttonX, buttonY, tWidth, CFG.TEXT_HEIGHT + CFG.PADDING * 5, ImageManager.getImage(Images.vassal).getWidth() + CFG.PADDING * 2) {
            @Override
            public void buildElementHover() {
                if (Game.getCiv(NewGameCiv.iActiveCivID).getCapitalProvinceID() >= 0) {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Capital") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.getCiv(NewGameCiv.iActiveCivID).getCapitalProvinceID()).getProvinceName(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_FlagTitle(NewGameCiv.iActiveCivID, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", "" + CFG.getNumberWithSpaces("" + Game.getProvince(Game.getCiv(NewGameCiv.iActiveCivID).getCapitalProvinceID()).getPopulationTotal()), Images.population, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
                else {
                    this.menuElementHover = null;
                }
            }
            
            @Override
            public void actionElement() {
                if (Game.getCiv(NewGameCiv.iActiveCivID).getCapitalProvinceID() >= 0) {
                    Game.mapCoords.centerToProvinceID(Game.getCiv(NewGameCiv.iActiveCivID).getCapitalProvinceID());
                    Game.setActiveProvinceID(Game.getCiv(NewGameCiv.iActiveCivID).getCapitalProvinceID());
                    ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                }
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        tWidth = (menuWidth - paddingLeft * 2 - CFG.PADDING * 3) / 4;
        buttonX = paddingLeft;
        final int statsH = ImageManager.getImage(Images.provinces).getHeight() + CFG.TEXT_HEIGHT + CFG.PADDING * 6;
        menuElements.add(new TextIcon2("" + Game.getCiv(NewGameCiv.iActiveCivID).getNumOfProvinces(), Images.provinces, buttonX, buttonY, tWidth, statsH) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Provinces") + ": ", "" + CFG.getNumberWithSpaces("" + Game.getCiv(NewGameCiv.iActiveCivID).getNumOfProvinces()), Images.provinces, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new TextIcon2(CFG.getShortNumber(Game.getCiv(NewGameCiv.iActiveCivID).getPopulationTotal()), Images.population, buttonX, buttonY, tWidth, statsH) {
            @Override
            protected Color getColor(final boolean isActive) {
                if (isActive) {
                    return Colors.COLOR_POPULATION_ACTIVE;
                }
                if (this.getIsHovered()) {
                    return Colors.COLOR_POPULATION_HOVER;
                }
                return this.getClickable() ? Colors.COLOR_POPULATION : Colors.BUTTON_TEXT_DISABLED;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", "" + CFG.getNumberWithSpaces("" + Game.getCiv(NewGameCiv.iActiveCivID).getPopulationTotal()), Images.population, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new TextIcon2(CFG.getShortNumber((int)Game.getCiv(NewGameCiv.iActiveCivID).getEconomyTotal()), Game_Calendar.IMG_ECONOMY, buttonX, buttonY, tWidth, statsH) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Economy") + ": ", "" + CFG.getNumberWithSpaces("" + CFG.getPrecision2(Game.getCiv(NewGameCiv.iActiveCivID).getEconomyTotal(), 1)), Game_Calendar.IMG_ECONOMY, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new TextIcon2("" + Game.getCiv(NewGameCiv.iActiveCivID).getResearchedTechnologies(), Game_Calendar.IMG_TECHNOLOGY, buttonX, buttonY, tWidth, statsH) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnlockedTechnologies") + ": ", "" + CFG.getNumberWithSpaces("" + Game.getCiv(NewGameCiv.iActiveCivID).getResearchedTechnologies()), Game_Calendar.IMG_TECHNOLOGY, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        String description = "";
        if (FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + Game.mapScenarios.lScenarios_TagsList.get(Game.scenarioID) + "/" + "descriptions/" + Game.getCiv(NewGameCiv.iActiveCivID).getCivTag() + ".txt").exists()) {
            final FileHandle tempFileT = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + Game.mapScenarios.lScenarios_TagsList.get(Game.scenarioID) + "/" + "descriptions/" + Game.getCiv(NewGameCiv.iActiveCivID).getCivTag() + ".txt");
            description = tempFileT.readString();
        }
        else if (FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + Game.mapScenarios.lScenarios_TagsList.get(Game.scenarioID) + "/" + "descriptions/" + Game.getCiv(NewGameCiv.iActiveCivID).realTag + ".txt").exists()) {
            final FileHandle tempFileT = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + Game.mapScenarios.lScenarios_TagsList.get(Game.scenarioID) + "/" + "descriptions/" + Game.getCiv(NewGameCiv.iActiveCivID).realTag + ".txt");
            description = tempFileT.readString();
        }
        if (description.length() > 0) {
            menuElements.add(new Text_Desc_SimpleNewGame(description, paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void actionElement() {
                    NewGameCiv.expandCivDesc = !NewGameCiv.expandCivDesc;
                    Game.menuManager.rebuildNewGameCiv();
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).NEW_GAME_ADVANTAGES) {
            final List<MenuElement> toSort = InGame_CivilizationAdvantages.getAdvantagesSmall(NewGameCiv.iActiveCivID);
            if (toSort.size() > 0) {
                int toSortX = menuWidth / 2 - (Button_Advantage3.getButtonWidth() * toSort.size() + CFG.PADDING * (toSort.size() - 1)) / 2;
                while (toSort.size() > 0) {
                    int bestID = 0;
                    for (int i = 1, iSize = toSort.size(); i < iSize; ++i) {
                        if (CFG.compareAlphabetic_TwoString(toSort.get(bestID).getTextToDraw(), toSort.get(i).getTextToDraw())) {
                            bestID = i;
                        }
                    }
                    toSort.get(bestID).setPosX(toSortX);
                    toSort.get(bestID).setPosY(buttonY);
                    menuElements.add(toSort.get(bestID));
                    toSortX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                    toSort.remove(bestID);
                }
                buttonY += Button_Advantage3.getButtonHeight() + CFG.PADDING;
            }
        }
        menuElements.add(new Text_Title_v2Center(Game.lang.get("Diplomacy"), -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        final int tempElementsBefore = menuElements.size();
        final int leftW = menuWidth - paddingLeft * 2 - ButtonFlag_Diplomacy.getButtonWidth() * 3 - CFG.PADDING * 2;
        final int lineH = ImageManager.getImage(Images.flagDiplomacyOver).getHeight() + CFG.PADDING * 2;
        final int maxIconW = ImageManager.getImage(Images.relations).getWidth() + CFG.PADDING * 2;
        int linesAdded = 0;
        paddingLeft = Images.boxTitleBORDERWIDTH;
        if (Game.getCiv(NewGameCiv.iActiveCivID).getPuppetOfCivID() != Game.getCiv(NewGameCiv.iActiveCivID).getCivID()) {
            buttonX = paddingLeft + leftW + CFG.PADDING;
            menuElements.add(new ButtonFlag_Diplomacy(Game.getCiv(NewGameCiv.iActiveCivID).getPuppetOfCivID(), paddingLeft + leftW + CFG.PADDING, buttonY + CFG.PADDING, true, true, false));
            menuElements.add(new TextIcon2_HorizontalSplit(Game.lang.get(Game_Ages.getLord()), Images.vassal, paddingLeft, buttonY, leftW, lineH, maxIconW, menuWidth - paddingLeft * 2));
            buttonY += lineH;
        }
        if (Game.getCiv(NewGameCiv.iActiveCivID).diplomacy.iVassalsSize > 0) {
            buttonX = paddingLeft + leftW + CFG.PADDING;
            for (int j = 0; j < Game.getCiv(NewGameCiv.iActiveCivID).diplomacy.iVassalsSize; ++j) {
                if (buttonX + ButtonFlag_Diplomacy.getButtonWidth() > menuWidth) {
                    buttonX = paddingLeft + leftW + CFG.PADDING;
                    buttonY += lineH;
                    ++linesAdded;
                }
                menuElements.add(new ButtonFlag_Diplomacy(Game.getCiv(NewGameCiv.iActiveCivID).diplomacy.lVassals.get(j).c, buttonX, buttonY + CFG.PADDING, true, true, true) {
                    @Override
                    public void actionElement() {
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            }
            menuElements.add(new TextIcon2_HorizontalSplit(Game.lang.get(Game_Ages.getVassals()), Images.vassal, paddingLeft, buttonY - lineH * linesAdded, leftW, lineH * (linesAdded + 1), maxIconW, menuWidth - paddingLeft * 2));
            linesAdded = 0;
            buttonY += lineH;
        }
        try {
            if (Game.getCiv(NewGameCiv.iActiveCivID).diplomacy.alliance.size() > 0) {
                buttonX = paddingLeft + leftW + CFG.PADDING;
                for (final Diplomacy.DiplomacyData nData : Game.getCiv(NewGameCiv.iActiveCivID).diplomacy.alliance.values()) {
                    if (buttonX + ButtonFlag_Diplomacy.getButtonWidth() > menuWidth) {
                        buttonX = paddingLeft + leftW + CFG.PADDING;
                        buttonY += lineH;
                        ++linesAdded;
                    }
                    menuElements.add(new ButtonFlag_Diplomacy(nData.iCivID, buttonX, buttonY + CFG.PADDING, true) {
                        @Override
                        public void actionElement() {
                        }
                    });
                    buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                }
                menuElements.add(new TextIcon2_HorizontalSplit(Game.lang.get("Alliance"), Images.alliance, paddingLeft, buttonY - lineH * linesAdded, leftW, lineH * (linesAdded + 1), maxIconW, menuWidth - paddingLeft * 2));
                linesAdded = 0;
                buttonY += Math.max(lineH, menuElements.get(menuElements.size() - 1).getHeight());
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            if (Game.getCiv(NewGameCiv.iActiveCivID).diplomacy.truce.size() > 0) {
                buttonX = paddingLeft + leftW + CFG.PADDING;
                for (final Diplomacy.DiplomacyData nData : Game.getCiv(NewGameCiv.iActiveCivID).diplomacy.truce.values()) {
                    if (buttonX + ButtonFlag_Diplomacy.getButtonWidth() > menuWidth) {
                        buttonX = paddingLeft + leftW + CFG.PADDING;
                        buttonY += lineH;
                        ++linesAdded;
                    }
                    menuElements.add(new ButtonFlag_Diplomacy(nData.iCivID, buttonX, buttonY + CFG.PADDING, true) {
                        @Override
                        public void actionElement() {
                        }
                    });
                    buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                }
                menuElements.add(new TextIcon2_HorizontalSplit(Game.lang.get("Truce"), Images.truce, paddingLeft, buttonY - lineH * linesAdded, leftW, lineH * (linesAdded + 1), maxIconW, menuWidth - paddingLeft * 2));
                linesAdded = 0;
                buttonY += Math.max(lineH, menuElements.get(menuElements.size() - 1).getHeight());
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            if (Game.getCiv(NewGameCiv.iActiveCivID).diplomacy.defensivePact.size() > 0) {
                buttonX = paddingLeft + leftW + CFG.PADDING;
                for (final Diplomacy.DiplomacyData nData : Game.getCiv(NewGameCiv.iActiveCivID).diplomacy.defensivePact.values()) {
                    if (buttonX + ButtonFlag_Diplomacy.getButtonWidth() > menuWidth) {
                        buttonX = paddingLeft + leftW + CFG.PADDING;
                        buttonY += lineH;
                        ++linesAdded;
                    }
                    menuElements.add(new ButtonFlag_Diplomacy(nData.iCivID, buttonX, buttonY + CFG.PADDING, true) {
                        @Override
                        public void actionElement() {
                        }
                    });
                    buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                }
                menuElements.add(new TextIcon2_HorizontalSplit(Game.lang.get("DefensivePact"), Images.defensivePact, paddingLeft, buttonY - lineH * linesAdded, leftW, lineH * (linesAdded + 1), maxIconW, menuWidth - paddingLeft * 2));
                linesAdded = 0;
                buttonY += Math.max(lineH, menuElements.get(menuElements.size() - 1).getHeight());
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            if (Game.getCiv(NewGameCiv.iActiveCivID).diplomacy.guarantee.size() > 0) {
                buttonX = paddingLeft + leftW + CFG.PADDING;
                for (final Diplomacy.DiplomacyData nData : Game.getCiv(NewGameCiv.iActiveCivID).diplomacy.guarantee.values()) {
                    if (buttonX + ButtonFlag_Diplomacy.getButtonWidth() > menuWidth) {
                        buttonX = paddingLeft + leftW + CFG.PADDING;
                        buttonY += lineH;
                        ++linesAdded;
                    }
                    menuElements.add(new ButtonFlag_Diplomacy(nData.iCivID, buttonX, buttonY + CFG.PADDING, true) {
                        @Override
                        public void actionElement() {
                        }
                    });
                    buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                }
                menuElements.add(new TextIcon2_HorizontalSplit(Game.lang.get("GuaranteeIndependence"), Images.guaranteeIndependence, paddingLeft, buttonY - lineH * linesAdded, leftW, lineH * (linesAdded + 1), maxIconW, menuWidth - paddingLeft * 2));
                linesAdded = 0;
                buttonY += Math.max(lineH, menuElements.get(menuElements.size() - 1).getHeight());
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            if (Game.getCiv(NewGameCiv.iActiveCivID).diplomacy.guaranteeByCivID.size() > 0) {
                buttonX = paddingLeft + leftW + CFG.PADDING;
                for (final Diplomacy.DiplomacyData nData : Game.getCiv(NewGameCiv.iActiveCivID).diplomacy.guaranteeByCivID.values()) {
                    if (buttonX + ButtonFlag_Diplomacy.getButtonWidth() > menuWidth) {
                        buttonX = paddingLeft + leftW + CFG.PADDING;
                        buttonY += lineH;
                        ++linesAdded;
                    }
                    menuElements.add(new ButtonFlag_Diplomacy(nData.iCivID, buttonX, buttonY + CFG.PADDING, true) {
                        @Override
                        public void actionElement() {
                        }
                    });
                    buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                }
                menuElements.add(new TextIcon2_HorizontalSplit(Game.lang.get("GuaranteeTheirIndependence"), Images.guaranteeIndependence, paddingLeft, buttonY - lineH * linesAdded, leftW, lineH * (linesAdded + 1), maxIconW, menuWidth - paddingLeft * 2));
                linesAdded = 0;
                buttonY += Math.max(lineH, menuElements.get(menuElements.size() - 1).getHeight());
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            if (Game.getCiv(NewGameCiv.iActiveCivID).diplomacy.nonAggressionPact.size() > 0) {
                buttonX = paddingLeft + leftW + CFG.PADDING;
                for (final Diplomacy.DiplomacyData nData : Game.getCiv(NewGameCiv.iActiveCivID).diplomacy.nonAggressionPact.values()) {
                    if (buttonX + ButtonFlag_Diplomacy.getButtonWidth() > menuWidth) {
                        buttonX = paddingLeft + leftW + CFG.PADDING;
                        buttonY += lineH;
                        ++linesAdded;
                    }
                    menuElements.add(new ButtonFlag_Diplomacy(nData.iCivID, buttonX, buttonY + CFG.PADDING, true) {
                        @Override
                        public void actionElement() {
                        }
                    });
                    buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                }
                menuElements.add(new TextIcon2_HorizontalSplit(Game.lang.get("NonAggressionPact").replace("-", " "), Images.nonAggression, paddingLeft, buttonY - lineH * linesAdded, leftW, lineH * (linesAdded + 1), maxIconW, menuWidth - paddingLeft * 2));
                linesAdded = 0;
                buttonY += Math.max(lineH, menuElements.get(menuElements.size() - 1).getHeight());
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            final List<Integer> friendly = new ArrayList<Integer>();
            for (int k = 1; k < Game.getCivsSize(); ++k) {
                if (Game.getCiv(k).diplomacy.getRelation(NewGameCiv.iActiveCivID) >= GameValues.diplomacy.DIPLOMACY_RELATIONS_FRIENDLY) {
                    friendly.add(k);
                }
            }
            if (friendly.size() > 0) {
                buttonX = paddingLeft + leftW + CFG.PADDING;
                for (int k = 0; k < GameValues.diplomacy.DIPLOMACY_FRIENDLY_LIMIT_IN_DIPLOMACY_VIEW && k < friendly.size(); ++k) {
                    int tBestID = 0;
                    for (int l = friendly.size() - 1; l > 0; --l) {
                        if (Game.getCiv(friendly.get(tBestID)).diplomacy.getRelation(NewGameCiv.iActiveCivID) < Game.getCiv(friendly.get(l)).diplomacy.getRelation(NewGameCiv.iActiveCivID)) {
                            tBestID = l;
                        }
                    }
                    if (buttonX + ButtonFlag_Diplomacy.getButtonWidth() > menuWidth) {
                        buttonX = paddingLeft + leftW + CFG.PADDING;
                        buttonY += lineH;
                        ++linesAdded;
                    }
                    menuElements.add(new ButtonFlag_Diplomacy((int)friendly.get(tBestID), buttonX, buttonY + CFG.PADDING, true) {
                        @Override
                        public void actionElement() {
                        }
                    });
                    buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                    friendly.remove(tBestID);
                }
                menuElements.add(new TextIcon2_HorizontalSplit(Game.lang.get("FriendlyCivilizations"), Images.heart, paddingLeft, buttonY - lineH * linesAdded, leftW, lineH * (linesAdded + 1), maxIconW, menuWidth - paddingLeft * 2));
                linesAdded = 0;
                buttonY += Math.max(lineH, menuElements.get(menuElements.size() - 1).getHeight());
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            if (Game.getCiv(NewGameCiv.iActiveCivID).diplomacy.militaryAccess.size() > 0) {
                buttonX = paddingLeft + leftW + CFG.PADDING;
                for (final Diplomacy.DiplomacyData nData : Game.getCiv(NewGameCiv.iActiveCivID).diplomacy.militaryAccess.values()) {
                    if (buttonX + ButtonFlag_Diplomacy.getButtonWidth() > menuWidth) {
                        buttonX = paddingLeft + leftW + CFG.PADDING;
                        buttonY += lineH;
                        ++linesAdded;
                    }
                    menuElements.add(new ButtonFlag_Diplomacy(nData.iCivID, buttonX, buttonY + CFG.PADDING, true) {
                        @Override
                        public void actionElement() {
                        }
                    });
                    buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                }
                menuElements.add(new TextIcon2_HorizontalSplit(Game.lang.get("HaveMilitaryAccess"), Images.militaryAccess, paddingLeft, buttonY - lineH * linesAdded, leftW, lineH * (linesAdded + 1), maxIconW, menuWidth - paddingLeft * 2));
                linesAdded = 0;
                buttonY += Math.max(lineH, menuElements.get(menuElements.size() - 1).getHeight());
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            final List<Integer> tMilitaryAccessGives = new ArrayList<Integer>();
            for (int k = 1; k < Game.getCivsSize(); ++k) {
                if (Game.getCiv(k).getNumOfProvinces() > 0 && Game.getCiv(k).diplomacy.militaryAccess.containsKey(NewGameCiv.iActiveCivID)) {
                    tMilitaryAccessGives.add(k);
                }
            }
            if (tMilitaryAccessGives.size() > 0) {
                buttonX = paddingLeft + leftW + CFG.PADDING;
                for (int k = 0; k < tMilitaryAccessGives.size(); ++k) {
                    if (buttonX + ButtonFlag_Diplomacy.getButtonWidth() > menuWidth) {
                        buttonX = paddingLeft + leftW + CFG.PADDING;
                        buttonY += lineH;
                        ++linesAdded;
                    }
                    menuElements.add(new ButtonFlag_Diplomacy((int)tMilitaryAccessGives.get(k), buttonX, buttonY + CFG.PADDING, true) {
                        @Override
                        public void actionElement() {
                        }
                    });
                    buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                }
                menuElements.add(new TextIcon2_HorizontalSplit(Game.lang.get("GivesMilitaryAccess"), Images.militaryAccess2, paddingLeft, buttonY - lineH * linesAdded, leftW, lineH * (linesAdded + 1), maxIconW, menuWidth - paddingLeft * 2));
                linesAdded = 0;
                buttonY += Math.max(lineH, menuElements.get(menuElements.size() - 1).getHeight());
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        if (tempElementsBefore == menuElements.size()) {
            buttonY += CFG.PADDING;
            menuElements.add(new Text_StaticBG("---", CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        final int menuHeight = Math.min(CFG.GAME_HEIGHT - ImageManager.getImage(Images.buttonPlay).getHeight() - CFG.PADDING * 3 - menuY - titleHeight, Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3));
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG("", true, false, Images.title1Red), menuX, titleHeight + menuY, menuWidth, menuHeight, menuElements, true, false);
        if (Game.menuManager.getColorPicker().getVisible()) {
            ColorPicker.ACTIVE_CIV_ID = NewGameCiv.iActiveCivID;
            Game.menuManager.getColorPicker().setActiveRGBColor(Game.getCiv(NewGameCiv.iActiveCivID).getR(), Game.getCiv(NewGameCiv.iActiveCivID).getG(), Game.getCiv(NewGameCiv.iActiveCivID).getB());
        }
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (NewGame.lTime + 500L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX + this.getWidth() - (int)(this.getWidth() * ((CFG.currentTimeMillis - NewGame.lTime) / 500.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false);
        ImageManager.getImage(Images.newGameOver).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.newGameOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void updateLanguage() {
        super.updateLanguage();
        this.getTitle().setText(Game.getCiv(NewGameCiv.iActiveCivID).getCivName());
    }
    
    static {
        NewGameCiv.iActiveCivID = 0;
        NewGameCiv.expandCivDesc = false;
    }
}
