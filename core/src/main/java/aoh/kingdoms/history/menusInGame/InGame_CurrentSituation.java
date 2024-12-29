// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_FlagCenter2;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menusInGame.AtomicNukes.InGame_Nukes;
import aoh.kingdoms.history.menusInGame.Budget.InGame_Budget;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court_Government;
import aoh.kingdoms.history.menu.MenuManager;
import aoh.kingdoms.history.menu.View;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Clear;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court_InvestInEconomy;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.button.ButtonCurrentSituation;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_CurrentSituation extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    
    public InGame_CurrentSituation() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title2).getHeight();
        final int menuWidth = ImageManager.getImage(Images.insideTop).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        int buttonY;
        final int buttonYPadding = buttonY = CFG.PADDING * 2;
        final int buttonH = CFG.isDesktop() ? CFG.BUTTON_HEIGHT2 : CFG.BUTTON_HEIGHT3;
        final int tIconMaxW = ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4;
        int iRow = 0;
        if (Game.player.currSituation.noActiveResearch) {
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("NoActiveResearch"), Game_Calendar.IMG_TECHNOLOGY, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, tIconMaxW, iRow++ % 2 == 0) {
                @Override
                public void actionElement() {
                    Game.menuManager.setVisibleInGame_CurrentSituation(false);
                    Game.menuManager.rebuildInGame_TechnologyChoose(false, true);
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ChooseResearch"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (Game.player.currSituation.maxAmountOfGold) {
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("MaximumAmountOfGold"), Images.gold, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, tIconMaxW, iRow++ % 2 == 0) {
                @Override
                public void actionElement() {
                    Game.menuManager.setVisibleInGame_CurrentSituation(false);
                    InGame_Court_InvestInEconomy.CLICK_X_TIMES = 1;
                    InGame_Court.iActiveCivID = Game.player.iCivID;
                    Game.menuManager.rebuildInGame_InvestInEconomy();
                    Game.menuManager.setVisibleInGame_Court(true);
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("InvestInEconomy"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_ECONOMY, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaximumAmountOfGold") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fGold, 1) + " / " + CFG.getPrecision2((float)Game.getMaxAmountOfGold(Game.player.iCivID), 1), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(" = " + CFG.getPrecision2((float)GameValues.budget.MAX_AMOUNT_OF_GOLD_MIN, 1) + " + " + Game.lang.get("TotalIncome") + " * " + CFG.getPrecision2(GameValues.budget.MAX_AMOUNT_OF_GOLD_PER_INCOME, 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (Game.player.currSituation.missionCanBeUnlocked) {
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("Missions") + ": " + Game.lang.get("Available"), Images.missions, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, tIconMaxW, iRow++ % 2 == 0) {
                @Override
                public void actionElement() {
                    InGame_CourtOptions2.actionMissions();
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Missions") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear("" + Game.lang.get("Available"), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.missions, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (Game.player.currSituation.chooseRivals) {
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("ChooseYourRivals") + ": " + Game.getCiv(Game.player.iCivID).diplomacy.rivals.size() + "/" + GameValues.rivals.RIVALS_LIMIT, Images.rivals, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, tIconMaxW, iRow++ % 2 == 0) {
                @Override
                public void actionElement() {
                    Game.menuManager.setVisibleInGame_CurrentSituation(false);
                    InGame_Civ.actionRivals();
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ChooseYourRivals"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.rivals, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (Game.player.currSituation.newLawAvailable) {
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("Law") + ": " + Game.lang.get("Unlocked"), Images.law, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, tIconMaxW, iRow++ % 2 == 0) {
                @Override
                public void actionElement() {
                    InGame_Court.iActiveCivID = Game.player.iCivID;
                    Game.menuManager.rebuildInGame_LawsCourt();
                    Game.menuManager.setVisibleInGame_Court(true);
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Law") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear("" + Game.lang.get("Unlocked"), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.law, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (Game.player.currSituation.availableAdvantage) {
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("AdvantagePoints") + ": " + Game.getCiv(Game.player.iCivID).getAdvantagePoints(), Images.advantages, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, tIconMaxW, iRow++ % 2 == 0) {
                @Override
                public void actionElement() {
                    Game.menuManager.setVisibleInGame_CurrentSituation(false);
                    Game.menuManager.rebuildInGame_CivilizationAdvantages(Game.player.iCivID);
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("CivilizationAdvantages"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.advantages, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (Game.player.currSituation.highInflation) {
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("HighInflation"), Images.inflation, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, tIconMaxW, iRow++ % 2 == 0) {
                @Override
                public void actionElement() {
                    Game.menuManager.setVisibleInGame_CurrentSituation(false);
                    Game.menuManager.rebuildInGame_Budget();
                    Game.menuManager.setVisibleInGame_Budget(true);
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Budget"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.gold, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Inflation") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).getInflation() * 100.0f, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.inflation, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (Game.player.currSituation.nonCoreProvinces) {
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("NonCoreProvince"), Images.core, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, tIconMaxW, iRow++ % 2 == 0) {
                @Override
                public void actionElement() {
                    InGame_Court.iActiveCivID = Game.player.iCivID;
                    Game.menuManager.rebuildInGame_Core();
                    Game.menuManager.setVisibleInGame_Court(true);
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("CoreConstruction"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.core, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    int numProvinces = 0;
                    for (int i = Game.getCiv(Game.player.iCivID).getNumOfProvinces() - 1; i >= 0; --i) {
                        if (!Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).isOccupied() && !Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).haveACore(Game.player.iCivID) && Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).coreCreation == null) {
                            ++numProvinces;
                        }
                    }
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Provinces") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + numProvinces, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.core, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (Game.player.currSituation.differentReligionProvinces) {
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("DifferentReligion"), Images.religion, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, tIconMaxW, iRow++ % 2 == 0) {
                @Override
                public void actionElement() {
                    InGame_Court.iActiveCivID = Game.player.iCivID;
                    Game.menuManager.rebuildInGame_Religion();
                    Game.menuManager.setVisibleInGame_Court(true);
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("DifferentReligion"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.religion, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    int numProvinces = 0;
                    for (int i = Game.getCiv(Game.player.iCivID).getNumOfProvinces() - 1; i >= 0; --i) {
                        if (!Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).isOccupied() && Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).getReligion() != Game.getCiv(Game.player.iCivID).getReligionID() && Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).religionConversion == null) {
                            ++numProvinces;
                        }
                    }
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Provinces") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + numProvinces, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.religion, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (Game.player.currSituation.availableCivilizationLegacy) {
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("AvailableCivilizationLegacy"), Images.legacy, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, tIconMaxW, iRow++ % 2 == 0) {
                @Override
                public void actionElement() {
                    Game.menuManager.setVisibleInGame_CurrentSituation(false);
                    InGame_Legacies.iActiveCivID = Game.player.iCivID;
                    Game.menuManager.setViewIDWithoutAnimation(View.IN_GAME_LEGACIES);
                    Game.menuManager.setOrderOfMenu_InGameLegacies();
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("CivilizationLegacy"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.legacy, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (Game.player.currSituation.noAdvisor > 0) {
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("NoAdvisor"), Images.skill, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, tIconMaxW, iRow++ % 2 == 0) {
                @Override
                public void actionElement() {
                    Game.menuManager.setVisibleInGame_CurrentSituation(false);
                    InGame.action1();
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(GameValues.court.COUNCIL_NAME), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.council, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (Game.player.currSituation.promoteAdvisor > 0) {
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("PromoteAdvisor"), Images.skill, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, tIconMaxW, iRow++ % 2 == 0) {
                @Override
                public void actionElement() {
                    Game.menuManager.setVisibleInGame_CurrentSituation(false);
                    InGame.action1();
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(GameValues.court.COUNCIL_NAME), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.council, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (Game.player.currSituation.lackOfGeneral) {
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("LackOfGeneral"), Images.general, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, tIconMaxW, iRow++ % 2 == 0) {
                @Override
                public void actionElement() {
                    Game.menuManager.setVisibleInGame_CurrentSituation(false);
                    Game.menuManager.rebuildInGame_Armies(true, false);
                    Game.menuManager.setVisibleInGame_Armies(true);
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Armies"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    for (int i = 0; i < Game.getCiv(Game.player.iCivID).iArmyPositionSize; ++i) {
                        for (int j = 0; j < Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmySize(); ++j) {
                            if (Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(j).civID == Game.player.iCivID && Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(j).armyGeneral == null) {
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Army") + ": ", CFG.FONT_REGULAR_SMALL));
                                nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getProvince(Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(j).provinceID).getProvinceName(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                        }
                    }
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (Game.player.currSituation.upgradeCapitalCity) {
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("UpgradeCapitalCity"), Images.capital, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, tIconMaxW, iRow++ % 2 == 0) {
                @Override
                public void actionElement() {
                    if (Game.getCiv(Game.player.iCivID).getCapitalLevel() >= Game.getCapital_MaxLvl(Game.player.iCivID)) {
                        Game.menuManager.addToastInsufficient(Game.lang.get("MaximumLevel") + ": ", "" + Game.getCiv(Game.player.iCivID).getCapitalLevel() + " / " + Game.getCapital_MaxLvl(Game.player.iCivID), Images.capital);
                    }
                    else if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 8) {
                        Game.menuManager.setVisibleInGame_PopUp(false);
                    }
                    else {
                        Game.menuManager.rebuildInGame_UpgradeCapital();
                    }
                }
                
                @Override
                public void buildElementHover() {
                    this.menuElementHover = InGame_Court_Government.getHoverCapitalCity();
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (Game.player.currSituation.upgradeSupremeCourt) {
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("UpgradeSupremeCourt"), Images.stability, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, tIconMaxW, iRow++ % 2 == 0) {
                @Override
                public void actionElement() {
                    if (Game.getCiv(Game.player.iCivID).getSupremeCourtLevel() >= Game.getSupremeCourt_MaxLvl(Game.player.iCivID)) {
                        Game.menuManager.addToastInsufficient(Game.lang.get("MaximumLevel") + ": ", "" + Game.getCiv(Game.player.iCivID).getSupremeCourtLevel() + " / " + Game.getSupremeCourt_MaxLvl(Game.player.iCivID), Images.stability);
                    }
                    else if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 4) {
                        Game.menuManager.setVisibleInGame_PopUp(false);
                    }
                    else {
                        Game.menuManager.rebuildInGame_UpgradeSupremeCourt();
                    }
                }
                
                @Override
                public void buildElementHover() {
                    this.menuElementHover = InGame_Budget.getHoverCorruption();
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (Game.player.currSituation.militaryAcademyCanBeUpgraded) {
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("UpgradeMilitaryAcademy"), Game_Calendar.IMG_MANPOWER, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, tIconMaxW, iRow++ % 2 == 0) {
                @Override
                public void actionElement() {
                    InGame_Court.iActiveCivID = Game.player.iCivID;
                    Game.menuManager.rebuildInGame_Government();
                    Game.menuManager.setVisibleInGame_Court(true);
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("LandUnits"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (Game.player.currSituation.militaryAcademyForGeneralsCanBeUpgraded) {
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("UpgradeMilitaryAcademyForGenerals"), Images.general, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, tIconMaxW, iRow++ % 2 == 0) {
                @Override
                public void actionElement() {
                    InGame_Generals.backButton = false;
                    Game.menuManager.rebuildInGame_Generals();
                    Game.menuManager.setVisibleInGame_Generals(true);
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("MilitaryAcademyForGenerals"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.general, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (Game.player.currSituation.upgradeNuclearReactor) {
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("UpgradeNuclearReactor"), Images.nuke, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, tIconMaxW, iRow++ % 2 == 0) {
                @Override
                public void actionElement() {
                    if (Game.getCiv(Game.player.iCivID).getNuclearReactorLevel() >= Game.getNuclearReactor_MaxLvl(Game.player.iCivID)) {
                        Game.menuManager.addToastInsufficient(Game.lang.get("MaximumLevel") + ": ", "" + Game.getCiv(Game.player.iCivID).getNuclearReactorLevel() + " / " + Game.getNuclearReactor_MaxLvl(Game.player.iCivID), Images.nuke);
                    }
                    else if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 5) {
                        Game.menuManager.setVisibleInGame_PopUp(false);
                    }
                    else {
                        Game.menuManager.rebuildInGame_UpgradeNuclearReactor();
                    }
                }
                
                @Override
                public void buildElementHover() {
                    this.menuElementHover = InGame_Nukes.getHoverNuclearReactor();
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (Game.player.currSituation.wonderCanBeBuilt) {
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("AWonderCanBeBuilt"), Images.mapModesWonders, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, tIconMaxW, iRow++ % 2 == 0) {
                @Override
                public void actionElement() {
                    Game.menuManager.rebuildInGame_CurrentSituation_Wonders();
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Wonders"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.mapModesWonders, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG_FlagCenter2("", false, false, Images.title1Red) {
            @Override
            public long getTime() {
                return InGame_CurrentSituation.lTime;
            }
            
            @Override
            public int getFlagCivID() {
                return Game.player.iCivID;
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_CurrentSituation.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_CurrentSituation.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false);
        ImageManager.getImage(Images.newGameOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.newGameOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.newGameOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void updateLanguage() {
        super.updateLanguage();
        this.getTitle().setText(Game.lang.get("CurrentSituation"));
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_CurrentSituation.lTime = CFG.currentTimeMillis;
    }
    
    static {
        InGame_CurrentSituation.lTime = 0L;
    }
}
