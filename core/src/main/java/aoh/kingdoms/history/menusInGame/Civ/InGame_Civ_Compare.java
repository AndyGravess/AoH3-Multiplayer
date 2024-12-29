// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Civ;

import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_DoubleText;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon2_Horizontal;
import aoh.kingdoms.history.map.battles.BattleManager;
import aoh.kingdoms.history.mainGame.Game_Ages;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonusResource;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title;
import aoh.kingdoms.history.menu_element.button.ButtonResource2;
import aoh.kingdoms.history.map.ResourcesManager;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_Compare;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Rank_Ranking;
import aoh.kingdoms.history.map.civilization.CivilizationRanking;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2_TextLR;
import aoh.kingdoms.history.menu_element.button.ButtonReligion2;
import aoh.kingdoms.history.menu_element.button.ButtonIdeology2;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon_Diplomacy;
import aoh.kingdoms.history.menu_element.textStatic.Text_Static_ID;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.button.ButtonFlag_Diplomacy;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Civ_Compare extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static long lTime2;
    public static int civLeft;
    public static int civRight;
    public static int civLeft_Rank;
    public static int civRight_Rank;
    
    public InGame_Civ_Compare() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING;
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        final int buttonYPadding = CFG.PADDING;
        final int buttonX = CFG.PADDING + Images.boxTitleBORDERWIDTH;
        int buttonY = buttonYPadding;
        final int buttonH = CFG.BUTTON_HEIGHT3;
        final int buttonW = (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2;
        final int maxIconW = ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 2;
        final int rightPosX = paddingLeft + CFG.PADDING + buttonW;
        final int rightW = (buttonW - CFG.PADDING) / 2;
        final int rightPosX2 = rightPosX + CFG.PADDING + rightW;
        final int maxWidth = ImageManager.getImage(Images.compareBig).getWidth() + CFG.PADDING * 4;
        final int tempTitlePaddingY = CFG.PADDING;
        final int tempTitleH = ImageManager.getImage(Images.flagDiplomacyOver).getHeight() + tempTitlePaddingY * 2;
        final int tempTextW = menuWidth / 2 - paddingLeft - CFG.PADDING * 2 - maxWidth / 2 - ImageManager.getImage(Images.flagDiplomacyOver).getWidth();
        menuElements.add(new ButtonFlag_Diplomacy(InGame_Civ_Compare.civLeft, menuWidth / 2 - maxWidth / 2 - CFG.PADDING * 2 - ImageManager.getImage(Images.flagDiplomacyOver).getWidth(), buttonY + tempTitlePaddingY, true));
        menuElements.add(new ButtonFlag_Diplomacy(InGame_Civ_Compare.civRight, menuWidth / 2 + maxWidth / 2 + CFG.PADDING * 2, buttonY + tempTitlePaddingY, true));
        menuElements.add(new Text_Static_ID(InGame_Civ_Compare.civRight, Game.getCiv(InGame_Civ_Compare.civRight).getCivName(), CFG.FONT_REGULAR, -1, menuWidth / 2 + maxWidth / 2 + CFG.PADDING * 2 + ImageManager.getImage(Images.flagDiplomacyOver).getWidth(), buttonY, tempTextW, tempTitleH));
        menuElements.add(new Text_Static_ID(InGame_Civ_Compare.civLeft, Game.getCiv(InGame_Civ_Compare.civLeft).getCivName(), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, tempTextW, tempTitleH));
        menuElements.add(new TextIcon_Diplomacy(Images.compareBig, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tempTitleH, maxWidth) {
            @Override
            public Color getColorBar() {
                return Colors.TECH_BLUE;
            }
            
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
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final int buttonH2 = (int)(buttonH * 1.25f);
        final int buttonTopW = (menuWidth - paddingLeft * 2 - CFG.PADDING * 3) / 4;
        menuElements.add(new ButtonIdeology2(Game.getCiv(InGame_Civ_Compare.civLeft).getIdeologyID(), paddingLeft, buttonY, buttonTopW, buttonH2));
        menuElements.add(new ButtonReligion2(Game.getCiv(InGame_Civ_Compare.civLeft).getReligionID(), buttonX + CFG.PADDING + buttonTopW, buttonY, buttonTopW, buttonH2));
        menuElements.add(new ButtonReligion2(Game.getCiv(InGame_Civ_Compare.civRight).getReligionID(), buttonX + (CFG.PADDING + buttonTopW) * 2, buttonY, buttonTopW, buttonH2));
        menuElements.add(new ButtonIdeology2(Game.getCiv(InGame_Civ_Compare.civRight).getIdeologyID(), buttonX + (CFG.PADDING + buttonTopW) * 3, buttonY, buttonTopW, buttonH2));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Ranking"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG(Game.lang.get("Ranking"), Images.rankGold, paddingLeft, buttonY, buttonW, buttonH, maxIconW));
        menuElements.add(new ButtonStatsRectIMG_Rank_Ranking("" + Game.getCiv(InGame_Civ_Compare.civLeft).iCivRankPosition, CivilizationRanking.getCivilizationRanking_IMG_STAR_CIVID(InGame_Civ_Compare.civLeft), rightPosX, buttonY, rightW, buttonH, ImageManager.getImage(Images.rankGold).getWidth()));
        menuElements.add(new ButtonStatsRectIMG_Rank_Ranking("" + Game.getCiv(InGame_Civ_Compare.civRight).iCivRankPosition, CivilizationRanking.getCivilizationRanking_IMG_STAR_CIVID(InGame_Civ_Compare.civRight), rightPosX2, buttonY, rightW, buttonH, ImageManager.getImage(Images.rankGold).getWidth()));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG(Game.lang.get("Prestige"), Images.rankSilver, paddingLeft, buttonY, buttonW, buttonH, maxIconW));
        menuElements.add(new Text_StaticBG_Compare(true, Game.lang.get("Prestige"), Images.rankSilver, "" + CFG.getNumberWithSpaces("" + (int)Math.ceil(Game.getCiv(InGame_Civ_Compare.civLeft).iCivRankScore)), rightPosX, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).iCivRankScore > Game.getCiv(InGame_Civ_Compare.civRight).iCivRankScore));
        menuElements.add(new Text_StaticBG_Compare(false, Game.lang.get("Prestige"), Images.rankSilver, "" + CFG.getNumberWithSpaces("" + (int)Math.ceil(Game.getCiv(InGame_Civ_Compare.civRight).iCivRankScore)), rightPosX2, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).iCivRankScore < Game.getCiv(InGame_Civ_Compare.civRight).iCivRankScore));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Provinces"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG(Game.lang.get("Provinces"), Images.provinces, paddingLeft, buttonY, buttonW, buttonH, maxIconW));
        menuElements.add(new Text_StaticBG_Compare(true, Game.lang.get("Provinces"), Images.provinces, "" + CFG.getNumberWithSpaces("" + Game.getCiv(InGame_Civ_Compare.civLeft).getNumOfProvinces()), rightPosX, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).getNumOfProvinces() > Game.getCiv(InGame_Civ_Compare.civRight).getNumOfProvinces()));
        menuElements.add(new Text_StaticBG_Compare(false, Game.lang.get("Provinces"), Images.provinces, "" + CFG.getNumberWithSpaces("" + Game.getCiv(InGame_Civ_Compare.civRight).getNumOfProvinces()), rightPosX2, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).getNumOfProvinces() < Game.getCiv(InGame_Civ_Compare.civRight).getNumOfProvinces()));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final long popLeft = Game.getCiv(InGame_Civ_Compare.civLeft).getPopulationTotal();
        final long popRight = Game.getCiv(InGame_Civ_Compare.civRight).getPopulationTotal();
        menuElements.add(new ButtonStatsRectIMG(Game.lang.get("Population"), Images.population, paddingLeft, buttonY, buttonW, buttonH, maxIconW));
        if (popLeft > 999999999L) {
            menuElements.add(new Text_StaticBG_Compare(true, Game.lang.get("Population"), Images.population, "" + CFG.getShortNumber(popLeft), rightPosX, buttonY, rightW, buttonH, popLeft > popRight));
            menuElements.add(new Text_StaticBG_Compare(false, Game.lang.get("Population"), Images.population, "" + CFG.getShortNumber(popRight), rightPosX2, buttonY, rightW, buttonH, popLeft < popRight));
        }
        else {
            menuElements.add(new Text_StaticBG_Compare(true, Game.lang.get("Population"), Images.population, "" + CFG.getNumberWithSpaces("" + popLeft), rightPosX, buttonY, rightW, buttonH, popLeft > popRight));
            menuElements.add(new Text_StaticBG_Compare(false, Game.lang.get("Population"), Images.population, "" + CFG.getNumberWithSpaces("" + popRight), rightPosX2, buttonY, rightW, buttonH, popLeft < popRight));
        }
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG(Game.lang.get("Economy"), Game_Calendar.IMG_ECONOMY, paddingLeft, buttonY, buttonW, buttonH, maxIconW));
        menuElements.add(new Text_StaticBG_Compare(true, Game.lang.get("Economy"), Game_Calendar.IMG_ECONOMY, "" + CFG.getNumberWithSpaces("" + (int)Math.ceil(Game.getCiv(InGame_Civ_Compare.civLeft).getEconomyTotal())), rightPosX, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).getEconomyTotal() > Game.getCiv(InGame_Civ_Compare.civRight).getEconomyTotal()));
        menuElements.add(new Text_StaticBG_Compare(false, Game.lang.get("Economy"), Game_Calendar.IMG_ECONOMY, "" + CFG.getNumberWithSpaces("" + (int)Math.ceil(Game.getCiv(InGame_Civ_Compare.civRight).getEconomyTotal())), rightPosX2, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).getEconomyTotal() < Game.getCiv(InGame_Civ_Compare.civRight).getEconomyTotal()));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG(Game.lang.get("Infrastructure"), Images.infrastructure, paddingLeft, buttonY, buttonW, buttonH, maxIconW));
        menuElements.add(new Text_StaticBG_Compare(true, Game.lang.get("Infrastructure"), Images.infrastructure, "" + CFG.getNumberWithSpaces("" + Game.getCiv(InGame_Civ_Compare.civLeft).getInfrastructure()), rightPosX, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).getInfrastructure() > Game.getCiv(InGame_Civ_Compare.civRight).getInfrastructure()));
        menuElements.add(new Text_StaticBG_Compare(false, Game.lang.get("Infrastructure"), Images.infrastructure, "" + CFG.getNumberWithSpaces("" + Game.getCiv(InGame_Civ_Compare.civRight).getInfrastructure()), rightPosX2, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).getInfrastructure() < Game.getCiv(InGame_Civ_Compare.civRight).getInfrastructure()));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG(Game.lang.get("ConstructedBuildings"), Images.buildings, paddingLeft, buttonY, buttonW, buttonH, maxIconW));
        menuElements.add(new Text_StaticBG_Compare(true, Game.lang.get("ConstructedBuildings"), Images.buildings, "" + CFG.getNumberWithSpaces("" + Game.getCiv(InGame_Civ_Compare.civLeft).getConstructedBuildings()), rightPosX, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).getConstructedBuildings() > Game.getCiv(InGame_Civ_Compare.civRight).getConstructedBuildings()));
        menuElements.add(new Text_StaticBG_Compare(false, Game.lang.get("ConstructedBuildings"), Images.buildings, "" + CFG.getNumberWithSpaces("" + Game.getCiv(InGame_Civ_Compare.civRight).getConstructedBuildings()), rightPosX2, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).getConstructedBuildings() < Game.getCiv(InGame_Civ_Compare.civRight).getConstructedBuildings()));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonResource2(ResourcesManager.getLargestGoodsProducedByCiv(InGame_Civ_Compare.civLeft), buttonX, buttonY, buttonW, buttonH2) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(InGame_Civ_Compare.civLeft, Game.lang.get("ProducedGoods")));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                final List<Float> goodsProduced = new ArrayList<Float>();
                for (int i = 0; i < ResourcesManager.iResourcesSize; ++i) {
                    goodsProduced.add(0.0f);
                }
                int bestResourceID = -1;
                for (int j = 0; j < Game.getCiv(InGame_Civ_Compare.civLeft).getNumOfProvinces(); ++j) {
                    if (Game.getProvince(Game.getCiv(InGame_Civ_Compare.civLeft).getProvinceID(j)).getResourceID() >= 0) {
                        goodsProduced.set(Game.getProvince(Game.getCiv(InGame_Civ_Compare.civLeft).getProvinceID(j)).getResourceID(), goodsProduced.get(Game.getProvince(Game.getCiv(InGame_Civ_Compare.civLeft).getProvinceID(j)).getResourceID()) + ResourcesManager.getProducedGoods(Game.getCiv(InGame_Civ_Compare.civLeft).getProvinceID(j)));
                        bestResourceID = Game.getProvince(Game.getCiv(InGame_Civ_Compare.civLeft).getProvinceID(j)).getResourceID();
                    }
                }
                if (bestResourceID >= 0) {
                    int numAdded = 0;
                    while (numAdded++ < 6) {
                        for (int k = 0; k < ResourcesManager.iResourcesSize; ++k) {
                            if (goodsProduced.get(bestResourceID) < goodsProduced.get(k)) {
                                bestResourceID = k;
                            }
                        }
                        if (goodsProduced.get(bestResourceID) <= 0.0f) {
                            break;
                        }
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonusResource(ResourcesManager.getResourceName(bestResourceID) + ": ", CFG.getPrecision2(goodsProduced.get(bestResourceID), 10), bestResourceID, CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        goodsProduced.set(bestResourceID, 0.0f);
                    }
                }
                else {
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("None"), "", Images.resourceNone, CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                goodsProduced.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new ButtonResource2(ResourcesManager.getLargestGoodsProducedByCiv(InGame_Civ_Compare.civRight), buttonX + CFG.PADDING + buttonW, buttonY, buttonW, buttonH2) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(InGame_Civ_Compare.civRight, Game.lang.get("ProducedGoods")));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                final List<Float> goodsProduced = new ArrayList<Float>();
                for (int i = 0; i < ResourcesManager.iResourcesSize; ++i) {
                    goodsProduced.add(0.0f);
                }
                int bestResourceID = -1;
                for (int j = 0; j < Game.getCiv(InGame_Civ_Compare.civRight).getNumOfProvinces(); ++j) {
                    if (Game.getProvince(Game.getCiv(InGame_Civ_Compare.civRight).getProvinceID(j)).getResourceID() >= 0) {
                        goodsProduced.set(Game.getProvince(Game.getCiv(InGame_Civ_Compare.civRight).getProvinceID(j)).getResourceID(), goodsProduced.get(Game.getProvince(Game.getCiv(InGame_Civ_Compare.civRight).getProvinceID(j)).getResourceID()) + ResourcesManager.getProducedGoods(Game.getCiv(InGame_Civ_Compare.civRight).getProvinceID(j)));
                        bestResourceID = Game.getProvince(Game.getCiv(InGame_Civ_Compare.civRight).getProvinceID(j)).getResourceID();
                    }
                }
                if (bestResourceID >= 0) {
                    int numAdded = 0;
                    while (numAdded++ < 6) {
                        for (int k = 0; k < ResourcesManager.iResourcesSize; ++k) {
                            if (goodsProduced.get(bestResourceID) < goodsProduced.get(k)) {
                                bestResourceID = k;
                            }
                        }
                        if (goodsProduced.get(bestResourceID) <= 0.0f) {
                            break;
                        }
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonusResource(ResourcesManager.getResourceName(bestResourceID) + ": ", CFG.getPrecision2(goodsProduced.get(bestResourceID), 10), bestResourceID, CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        goodsProduced.set(bestResourceID, 0.0f);
                    }
                }
                else {
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("None"), "", Images.resourceNone, CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                goodsProduced.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Civilization"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG(Game.lang.get("UnlockedTechnologies"), Game_Calendar.IMG_TECHNOLOGY, paddingLeft, buttonY, buttonW, buttonH, maxIconW));
        menuElements.add(new Text_StaticBG_Compare(true, Game.lang.get("UnlockedTechnologies"), Game_Calendar.IMG_TECHNOLOGY, "" + CFG.getNumberWithSpaces("" + Game.getCiv(InGame_Civ_Compare.civLeft).getResearchedTechnologies()), rightPosX, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).getResearchedTechnologies() > Game.getCiv(InGame_Civ_Compare.civRight).getResearchedTechnologies()));
        menuElements.add(new Text_StaticBG_Compare(false, Game.lang.get("UnlockedTechnologies"), Game_Calendar.IMG_TECHNOLOGY, "" + CFG.getNumberWithSpaces("" + Game.getCiv(InGame_Civ_Compare.civRight).getResearchedTechnologies()), rightPosX2, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).getResearchedTechnologies() < Game.getCiv(InGame_Civ_Compare.civRight).getResearchedTechnologies()));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG(Game.lang.get("UnlockedLegacy"), Images.legacy, paddingLeft, buttonY, buttonW, buttonH, maxIconW));
        menuElements.add(new Text_StaticBG_Compare(true, Game.lang.get("UnlockedLegacy"), Images.legacy, "" + CFG.getNumberWithSpaces("" + Game.getCiv(InGame_Civ_Compare.civLeft).getLegaciesUnlocked()), rightPosX, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).getLegaciesUnlocked() > Game.getCiv(InGame_Civ_Compare.civRight).getLegaciesUnlocked()));
        menuElements.add(new Text_StaticBG_Compare(false, Game.lang.get("UnlockedLegacy"), Images.legacy, "" + CFG.getNumberWithSpaces("" + Game.getCiv(InGame_Civ_Compare.civRight).getLegaciesUnlocked()), rightPosX2, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).getLegaciesUnlocked() < Game.getCiv(InGame_Civ_Compare.civRight).getLegaciesUnlocked()));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG(Game.lang.get("CivilizationAdvantages"), Images.advantages, paddingLeft, buttonY, buttonW, buttonH, maxIconW));
        menuElements.add(new Text_StaticBG_Compare(true, Game.lang.get("CivilizationAdvantages"), Images.advantages, "" + CFG.getNumberWithSpaces("" + Game.getCiv(InGame_Civ_Compare.civLeft).iAdvantagesSize), rightPosX, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).iAdvantagesSize > Game.getCiv(InGame_Civ_Compare.civRight).iAdvantagesSize));
        menuElements.add(new Text_StaticBG_Compare(false, Game.lang.get("CivilizationAdvantages"), Images.advantages, "" + CFG.getNumberWithSpaces("" + Game.getCiv(InGame_Civ_Compare.civRight).iAdvantagesSize), rightPosX2, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).iAdvantagesSize < Game.getCiv(InGame_Civ_Compare.civRight).iAdvantagesSize));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG(Game_Ages.getVassals(), Images.vassal, paddingLeft, buttonY, buttonW, buttonH, maxIconW));
        menuElements.add(new Text_StaticBG_Compare(true, Game.lang.get(Game_Ages.getVassals()), Images.vassal, "" + CFG.getNumberWithSpaces("" + Game.getCiv(InGame_Civ_Compare.civLeft).diplomacy.iVassalsSize), rightPosX, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).diplomacy.iVassalsSize > Game.getCiv(InGame_Civ_Compare.civRight).diplomacy.iVassalsSize));
        menuElements.add(new Text_StaticBG_Compare(false, Game.lang.get(Game_Ages.getVassals()), Images.vassal, "" + CFG.getNumberWithSpaces("" + Game.getCiv(InGame_Civ_Compare.civRight).diplomacy.iVassalsSize), rightPosX2, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).diplomacy.iVassalsSize < Game.getCiv(InGame_Civ_Compare.civRight).diplomacy.iVassalsSize));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Military"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG(Game.lang.get("MaximumManpower"), Game_Calendar.IMG_MANPOWER, paddingLeft, buttonY, buttonW, buttonH, maxIconW));
        menuElements.add(new Text_StaticBG_Compare(true, Game.lang.get("MaximumManpower"), Game_Calendar.IMG_MANPOWER, "" + CFG.getNumberWithSpaces("" + (int)Game.getCiv(InGame_Civ_Compare.civLeft).fManpowerMax), rightPosX, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).fManpowerMax > Game.getCiv(InGame_Civ_Compare.civRight).fManpowerMax));
        menuElements.add(new Text_StaticBG_Compare(false, Game.lang.get("MaximumManpower"), Game_Calendar.IMG_MANPOWER, "" + CFG.getNumberWithSpaces("" + (int)Game.getCiv(InGame_Civ_Compare.civRight).fManpowerMax), rightPosX2, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).fManpowerMax < Game.getCiv(InGame_Civ_Compare.civRight).fManpowerMax));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG(Game.lang.get("RegimentsLimit"), Images.regimentsLimit, paddingLeft, buttonY, buttonW, buttonH, maxIconW));
        menuElements.add(new Text_StaticBG_Compare(true, Game.lang.get("RegimentsLimit"), Images.regimentsLimit, "" + CFG.getNumberWithSpaces("" + Game.getCiv(InGame_Civ_Compare.civLeft).iRegimentsLimit), rightPosX, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).iRegimentsLimit > Game.getCiv(InGame_Civ_Compare.civRight).iRegimentsLimit));
        menuElements.add(new Text_StaticBG_Compare(false, Game.lang.get("RegimentsLimit"), Images.regimentsLimit, "" + CFG.getNumberWithSpaces("" + Game.getCiv(InGame_Civ_Compare.civRight).iRegimentsLimit), rightPosX2, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).iRegimentsLimit < Game.getCiv(InGame_Civ_Compare.civRight).iRegimentsLimit));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG(Game.lang.get("Discipline"), Images.discipline, paddingLeft, buttonY, buttonW, buttonH, maxIconW));
        menuElements.add(new Text_StaticBG_Compare(true, Game.lang.get("Discipline"), Images.discipline, "" + (int)(Game.getCiv(InGame_Civ_Compare.civLeft).civBonuses.Discipline * 100.0f) + "%", rightPosX, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).civBonuses.Discipline > Game.getCiv(InGame_Civ_Compare.civRight).civBonuses.Discipline));
        menuElements.add(new Text_StaticBG_Compare(false, Game.lang.get("Discipline"), Images.discipline, "" + (int)(Game.getCiv(InGame_Civ_Compare.civRight).civBonuses.Discipline * 100.0f) + "%", rightPosX2, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).civBonuses.Discipline < Game.getCiv(InGame_Civ_Compare.civRight).civBonuses.Discipline));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG(Game.lang.get("BattleWidth"), Images.battleWidth, paddingLeft, buttonY, buttonW, buttonH, maxIconW));
        menuElements.add(new Text_StaticBG_Compare(true, Game.lang.get("BattleWidth"), Images.battleWidth, "" + CFG.getNumberWithSpaces("" + BattleManager.getBattleWidth(InGame_Civ_Compare.civLeft)), rightPosX, buttonY, rightW, buttonH, BattleManager.getBattleWidth(InGame_Civ_Compare.civLeft) > BattleManager.getBattleWidth(InGame_Civ_Compare.civRight)));
        menuElements.add(new Text_StaticBG_Compare(false, Game.lang.get("BattleWidth"), Images.battleWidth, "" + CFG.getNumberWithSpaces("" + BattleManager.getBattleWidth(InGame_Civ_Compare.civRight)), rightPosX2, buttonY, rightW, buttonH, BattleManager.getBattleWidth(InGame_Civ_Compare.civLeft) < BattleManager.getBattleWidth(InGame_Civ_Compare.civRight)));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG(Game.lang.get("AggressiveExpansion"), Images.aggressiveExpansion, paddingLeft, buttonY, buttonW, buttonH, maxIconW));
        menuElements.add(new Text_StaticBG_Compare(true, Game.lang.get("AggressiveExpansion"), Images.aggressiveExpansion, "" + (int)Game.getCiv(InGame_Civ_Compare.civLeft).getAggressiveExpansion(), rightPosX, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).getAggressiveExpansion() < Game.getCiv(InGame_Civ_Compare.civRight).getAggressiveExpansion()));
        menuElements.add(new Text_StaticBG_Compare(false, Game.lang.get("AggressiveExpansion"), Images.aggressiveExpansion, "" + (int)Game.getCiv(InGame_Civ_Compare.civRight).getAggressiveExpansion(), rightPosX2, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).getAggressiveExpansion() > Game.getCiv(InGame_Civ_Compare.civRight).getAggressiveExpansion()));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG(Game.lang.get("WarWeariness"), Images.weariness, paddingLeft, buttonY, buttonW, buttonH, maxIconW));
        menuElements.add(new Text_StaticBG_Compare(true, Game.lang.get("WarWeariness"), Images.weariness, "" + (int)Game.getCiv(InGame_Civ_Compare.civLeft).getWarWeariness(), rightPosX, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).getWarWeariness() < Game.getCiv(InGame_Civ_Compare.civRight).getWarWeariness()));
        menuElements.add(new Text_StaticBG_Compare(false, Game.lang.get("WarWeariness"), Images.weariness, "" + (int)Game.getCiv(InGame_Civ_Compare.civRight).getWarWeariness(), rightPosX2, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).getWarWeariness() > Game.getCiv(InGame_Civ_Compare.civRight).getWarWeariness()));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Capital"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new TextIcon2_Horizontal((Game.getCiv(InGame_Civ_Compare.civLeft).getCapitalProvinceID() >= 0) ? Game.getProvince(Game.getCiv(InGame_Civ_Compare.civLeft).getCapitalProvinceID()).getProvinceName() : "", Images.capital, paddingLeft, buttonY, buttonW, buttonH, maxIconW) {
            @Override
            public void actionElement() {
                if (Game.getCiv(InGame_Civ_Compare.civLeft).getCapitalProvinceID() >= 0) {
                    if (Game.iActiveProvince == Game.getCiv(InGame_Civ_Compare.civLeft).getCapitalProvinceID()) {
                        Game.menuManager.hideCourtCiv();
                        Game.menuManager.setVisibleInGame_Civ(false);
                        Game.setActiveProvinceID(Game.getCiv(InGame_Civ_Compare.civLeft).getCapitalProvinceID());
                        Game.menuManager.rebuildInGame_ProvinceInfo(true);
                        ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                    }
                    else {
                        Game.mapCoords.centerToProvinceID(Game.getCiv(InGame_Civ_Compare.civLeft).getCapitalProvinceID());
                        Game.setActiveProvinceID(Game.getCiv(InGame_Civ_Compare.civLeft).getCapitalProvinceID());
                        ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                    }
                }
            }
            
            @Override
            public void buildElementHover() {
                if (Game.getCiv(InGame_Civ_Compare.civLeft).getCapitalProvinceID() >= 0) {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(InGame_Civ_Compare.civLeft, Game.lang.get("Capital") + ": " + Game.getProvince(Game.getCiv(InGame_Civ_Compare.civLeft).getCapitalProvinceID()).getProvinceName()));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", CFG.getNumberWithSpaces("" + Game.getProvince(Game.getCiv(InGame_Civ_Compare.civLeft).getCapitalProvinceID()).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Economy") + ": ", CFG.getPrecision2(Game.getProvince(Game.getCiv(InGame_Civ_Compare.civLeft).getCapitalProvinceID()).getEconomyWithBonuses(), 10), Game_Calendar.IMG_ECONOMY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Income") + ": ", CFG.getPrecision2(Game.getProvince(Game.getCiv(InGame_Civ_Compare.civLeft).getCapitalProvinceID()).getProvinceIncome() - Game.getProvince(Game.getCiv(InGame_Civ_Compare.civLeft).getCapitalProvinceID()).fProvinceMaintenance, 100), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
                else {
                    this.menuElementHover = null;
                }
            }
        });
        menuElements.add(new TextIcon2_Horizontal((Game.getCiv(InGame_Civ_Compare.civRight).getCapitalProvinceID() >= 0) ? Game.getProvince(Game.getCiv(InGame_Civ_Compare.civRight).getCapitalProvinceID()).getProvinceName() : "", Images.capital, paddingLeft + buttonW + CFG.PADDING, buttonY, buttonW, buttonH, maxIconW) {
            @Override
            public void actionElement() {
                if (Game.getCiv(InGame_Civ_Compare.civRight).getCapitalProvinceID() >= 0) {
                    if (Game.iActiveProvince == Game.getCiv(InGame_Civ_Compare.civRight).getCapitalProvinceID()) {
                        Game.menuManager.hideCourtCiv();
                        Game.menuManager.setVisibleInGame_Civ(false);
                        Game.setActiveProvinceID(Game.getCiv(InGame_Civ_Compare.civRight).getCapitalProvinceID());
                        Game.menuManager.rebuildInGame_ProvinceInfo(true);
                        ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                    }
                    else {
                        Game.mapCoords.centerToProvinceID(Game.getCiv(InGame_Civ_Compare.civRight).getCapitalProvinceID());
                        Game.setActiveProvinceID(Game.getCiv(InGame_Civ_Compare.civRight).getCapitalProvinceID());
                        ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                    }
                }
            }
            
            @Override
            public void buildElementHover() {
                if (Game.getCiv(InGame_Civ_Compare.civRight).getCapitalProvinceID() >= 0) {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(InGame_Civ_Compare.civRight, Game.lang.get("Capital") + ": " + Game.getProvince(Game.getCiv(InGame_Civ_Compare.civRight).getCapitalProvinceID()).getProvinceName()));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", CFG.getNumberWithSpaces("" + Game.getProvince(Game.getCiv(InGame_Civ_Compare.civRight).getCapitalProvinceID()).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Economy") + ": ", CFG.getPrecision2(Game.getProvince(Game.getCiv(InGame_Civ_Compare.civRight).getCapitalProvinceID()).getEconomyWithBonuses(), 10), Game_Calendar.IMG_ECONOMY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Income") + ": ", CFG.getPrecision2(Game.getProvince(Game.getCiv(InGame_Civ_Compare.civRight).getCapitalProvinceID()).getProvinceIncome() - Game.getProvince(Game.getCiv(InGame_Civ_Compare.civRight).getCapitalProvinceID()).fProvinceMaintenance, 100), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
                else {
                    this.menuElementHover = null;
                }
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG(Game.lang.get("CapitalCity"), Images.capital, paddingLeft, buttonY, buttonW, buttonH, maxIconW));
        menuElements.add(new Text_StaticBG_Compare(true, Game.lang.get("CapitalCity"), Images.capital, "" + CFG.getNumberWithSpaces("" + Game.getCiv(InGame_Civ_Compare.civLeft).getCapitalLevel()), rightPosX, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).getCapitalLevel() > Game.getCiv(InGame_Civ_Compare.civRight).getCapitalLevel()));
        menuElements.add(new Text_StaticBG_Compare(false, Game.lang.get("CapitalCity"), Images.capital, "" + CFG.getNumberWithSpaces("" + Game.getCiv(InGame_Civ_Compare.civRight).getCapitalLevel()), rightPosX2, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).getCapitalLevel() < Game.getCiv(InGame_Civ_Compare.civRight).getCapitalLevel()));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG(Game.lang.get("MilitaryAcademy"), Game_Calendar.IMG_MANPOWER, paddingLeft, buttonY, buttonW, buttonH, maxIconW));
        menuElements.add(new Text_StaticBG_Compare(true, Game.lang.get("MilitaryAcademy"), Game_Calendar.IMG_MANPOWER, "" + CFG.getNumberWithSpaces("" + Game.getCiv(InGame_Civ_Compare.civLeft).getMilitaryAcademyLevel()), rightPosX, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).getMilitaryAcademyLevel() > Game.getCiv(InGame_Civ_Compare.civRight).getMilitaryAcademyLevel()));
        menuElements.add(new Text_StaticBG_Compare(false, Game.lang.get("MilitaryAcademy"), Game_Calendar.IMG_MANPOWER, "" + CFG.getNumberWithSpaces("" + Game.getCiv(InGame_Civ_Compare.civRight).getMilitaryAcademyLevel()), rightPosX2, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).getMilitaryAcademyLevel() < Game.getCiv(InGame_Civ_Compare.civRight).getMilitaryAcademyLevel()));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG(Game.lang.get("MilitaryAcademyForGenerals"), Images.general, paddingLeft, buttonY, buttonW, buttonH, maxIconW));
        menuElements.add(new Text_StaticBG_Compare(true, Game.lang.get("MilitaryAcademyForGenerals"), Images.general, "" + CFG.getNumberWithSpaces("" + Game.getCiv(InGame_Civ_Compare.civLeft).getMilitaryAcademyForGeneralsLevel()), rightPosX, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).getMilitaryAcademyForGeneralsLevel() > Game.getCiv(InGame_Civ_Compare.civRight).getMilitaryAcademyForGeneralsLevel()));
        menuElements.add(new Text_StaticBG_Compare(false, Game.lang.get("MilitaryAcademyForGenerals"), Images.general, "" + CFG.getNumberWithSpaces("" + Game.getCiv(InGame_Civ_Compare.civRight).getMilitaryAcademyForGeneralsLevel()), rightPosX2, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).getMilitaryAcademyForGeneralsLevel() < Game.getCiv(InGame_Civ_Compare.civRight).getMilitaryAcademyForGeneralsLevel()));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG(Game.lang.get("SupremeCourt"), Images.stability, paddingLeft, buttonY, buttonW, buttonH, maxIconW));
        menuElements.add(new Text_StaticBG_Compare(true, Game.lang.get("SupremeCourt"), Images.stability, "" + CFG.getNumberWithSpaces("" + Game.getCiv(InGame_Civ_Compare.civLeft).getSupremeCourtLevel()), rightPosX, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).getSupremeCourtLevel() > Game.getCiv(InGame_Civ_Compare.civRight).getSupremeCourtLevel()));
        menuElements.add(new Text_StaticBG_Compare(false, Game.lang.get("SupremeCourt"), Images.stability, "" + CFG.getNumberWithSpaces("" + Game.getCiv(InGame_Civ_Compare.civRight).getSupremeCourtLevel()), rightPosX2, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).getSupremeCourtLevel() < Game.getCiv(InGame_Civ_Compare.civRight).getSupremeCourtLevel()));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        if (Game.getCiv(InGame_Civ_Compare.civLeft).canBuildNuke || Game.getCiv(InGame_Civ_Compare.civRight).canBuildNuke) {
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("NuclearReactor"), Images.nuke, paddingLeft, buttonY, buttonW, buttonH, maxIconW));
            menuElements.add(new Text_StaticBG_Compare(true, Game.lang.get("NuclearReactor"), Images.nuke, "" + CFG.getNumberWithSpaces("" + Game.getCiv(InGame_Civ_Compare.civLeft).getNuclearReactorLevel()), rightPosX, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).getNuclearReactorLevel() > Game.getCiv(InGame_Civ_Compare.civRight).getNuclearReactorLevel()));
            menuElements.add(new Text_StaticBG_Compare(false, Game.lang.get("NuclearReactor"), Images.nuke, "" + CFG.getNumberWithSpaces("" + Game.getCiv(InGame_Civ_Compare.civRight).getNuclearReactorLevel()), rightPosX2, buttonY, rightW, buttonH, Game.getCiv(InGame_Civ_Compare.civLeft).getNuclearReactorLevel() < Game.getCiv(InGame_Civ_Compare.civRight).getNuclearReactorLevel()));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG_DoubleText(Game.lang.get("CompareCivilizations"), Game.getCiv(InGame_Civ_Compare.civLeft).getCivName() + " - " + Game.getCiv(InGame_Civ_Compare.civRight).getCivName(), false, false, Images.title500) {
            @Override
            public long getTime() {
                return InGame_Civ_Compare.lTime2;
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_Civ_Compare.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_Civ_Compare.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.civInfoOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.civInfoOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.civInfoOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_Civ_Compare.lTime = CFG.currentTimeMillis;
        InGame_Civ_Compare.lTime2 = InGame_Civ_Compare.lTime;
    }
    
    @Override
    public void actionCloseMenu() {
        super.actionCloseMenu();
        InGame_Civ.actionOnClose();
    }
    
    static {
        InGame_Civ_Compare.lTime = 0L;
        InGame_Civ_Compare.lTime2 = 0L;
        InGame_Civ_Compare.civLeft = 0;
        InGame_Civ_Compare.civRight = 0;
        InGame_Civ_Compare.civLeft_Rank = -1;
        InGame_Civ_Compare.civRight_Rank = -1;
    }
}
