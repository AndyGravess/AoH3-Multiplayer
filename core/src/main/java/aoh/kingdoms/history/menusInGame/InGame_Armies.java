// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.textStatic.TextArmies;
import aoh.kingdoms.history.menu_element.button.ButtonArmyBattle_Upgrade;
import aoh.kingdoms.history.map.army.ArmyRegiment;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.button.ButtonArmyIcon_Special;
import aoh.kingdoms.history.menu_element.button.ButtonArmyGeneral2_Armies;
import aoh.kingdoms.history.menu_element.button.ButtonArmyNoGeneral2_Armies;
import aoh.kingdoms.history.menu_element.button.ButtonArmyBattle;
import aoh.kingdoms.history.menusInGame.Battle.InGame_Battlefield;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.map.battles.BattleManager;
import aoh.kingdoms.history.menu.MenuManager;
import aoh.kingdoms.history.menusInGame.Info.InGame_Info;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Bonuses_Style;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2Center;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menusInGame.RecruitArmy.InGame_RecruitArmy;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Active_Click;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.map.army.ArmyManager;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Armies extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    
    public InGame_Armies(final boolean noGenerals) {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2;
        final int titleHeight = ImageManager.getImage(Images.title2).getHeight();
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth() - Images.boxTitleBORDERWIDTH * 2;
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        int buttonY;
        final int buttonYPadding = buttonY = CFG.PADDING;
        int buttonX = paddingLeft;
        final int extraX = 0;
        final int regimentsToUpgrade = ArmyManager.getRegimentsAvailableToUpgrade(Game.player.iCivID);
        final int leftW = (menuWidth - paddingLeft * 2 - CFG.PADDING) * 3 / 5;
        final int rightW = (menuWidth - paddingLeft * 2 - CFG.PADDING) * 2 / 5;
        final int buttonH = CFG.isDesktop() ? CFG.BUTTON_HEIGHT3 : CFG.BUTTON_HEIGHT2;
        final int maxIconWidth = ImageManager.getImage(Game_Calendar.IMG_MANPOWER).getWidth();
        menuElements.add(new ButtonStatsRectIMG_Active_Click(Game.lang.get("CreateNewArmy"), Game_Calendar.IMG_MANPOWER, buttonX + CFG.PADDING + rightW, buttonY, leftW, buttonH, maxIconWidth, 0, true) {
            @Override
            public void actionElement() {
                Game.menuManager.setVisibleInGame_Armies(false);
                InGame_RecruitArmy.actionCreateNewArmy();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("CreateNewArmy"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (CFG.isDesktop()) {
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Shortcut") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                    nData.add(new MenuElement_HoverElement_Type_Text("V", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover(nElements, !CFG.isDesktop());
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Active_Click(Game.lang.get("Back"), Game_Calendar.IMG_MANPOWER, buttonX, buttonY, rightW, buttonH, maxIconWidth, 0) {
            @Override
            public void actionElement() {
                InGame.action3();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Back"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Text_Title_v2Center(Game.lang.get("AvailableUpgrades"), -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        if (regimentsToUpgrade == 0) {
            menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2) {
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
                
                @Override
                public void actionElement() {
                    if (Game.menuManager.getVisibleInGame_RecruitArmy()) {
                        Game.menuManager.setVisibleInGame_RecruitArmy(false);
                    }
                    else {
                        if (Game.menuManager.getVisibleInGame_Armies()) {
                            Game.menuManager.setVisibleInGame_Armies(false);
                        }
                        Game.menuManager.rebuildInGame_RecruitArmy();
                        Game.menuManager.setVisibleInGame_RecruitArmy(true);
                    }
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        else {
            menuElements.add(new ButtonStatsRectIMG_Bonuses_Style(Game.lang.get("UpgradeAllRegiments") + ": ", "" + regimentsToUpgrade, Game_Calendar.IMG_MANPOWER, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT3, ImageManager.getImage(Images.battleWidth).getWidth()) {
                @Override
                public Color getColorBonus() {
                    return Colors.HOVER_GOLD;
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("UpgradeAllRegiments"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.upgrade, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    final int tNumOfRegiments = ArmyManager.getRegimentsAvailableToUpgrade(Game.player.iCivID);
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AvailableUpgrades") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + tNumOfRegiments, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(ArmyManager.getUpgradeRegimentCost(Game.player.iCivID) * tNumOfRegiments, 10), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(" = " + Game.lang.get("NumberOfRegiments") + " * " + CFG.getPrecision2(ArmyManager.getUpgradeRegimentCost(Game.player.iCivID), 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
                
                @Override
                public void actionElement() {
                    if (Game.getCiv(Game.player.iCivID).fGold < ArmyManager.getUpgradeRegimentCost(Game.player.iCivID)) {
                        Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(ArmyManager.getUpgradeRegimentCost(Game.player.iCivID), 100), Images.gold);
                    }
                    else {
                        final int tNum = ArmyManager.upgradeAllArmies(Game.player.iCivID);
                        InGame_Info.iCivID = Game.player.iCivID;
                        InGame_Info.iCivID2 = 0;
                        Game.menuManager.rebuildInGame_Info(Game.getCiv(Game.player.iCivID).getCivName(), Game.lang.get("RegimentsUpgraded") + ": " + tNum);
                        InGame_Info.imgID = Images.infoCrown;
                        Game.menuManager.rebuildInGame_Armies(false, true);
                        Game.menuManager.setVisibleInGame_Armies(true);
                        InGame_Armies.lTime = 0L;
                    }
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonStatsRectIMG_Bonuses_Style(Game.lang.get("Regiments") + ": ", "" + regimentsToUpgrade, Game_Calendar.IMG_MANPOWER, paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, CFG.BUTTON_HEIGHT3, ImageManager.getImage(Images.battleWidth).getWidth()) {
                @Override
                public Color getColorBonus() {
                    return Colors.HOVER_GOLD;
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("UpgradeAllRegiments"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.upgrade, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    final int tNumOfRegiments = ArmyManager.getRegimentsAvailableToUpgrade(Game.player.iCivID);
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AvailableUpgrades") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + tNumOfRegiments, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(ArmyManager.getUpgradeRegimentCost(Game.player.iCivID) * tNumOfRegiments, 10), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(" = " + Game.lang.get("NumberOfRegiments") + " * " + CFG.getPrecision2(ArmyManager.getUpgradeRegimentCost(Game.player.iCivID), 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
                
                @Override
                public void actionElement() {
                    if (Game.getCiv(Game.player.iCivID).fGold < ArmyManager.getUpgradeRegimentCost(Game.player.iCivID)) {
                        Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(ArmyManager.getUpgradeRegimentCost(Game.player.iCivID), 100), Images.gold);
                    }
                    else {
                        final int tNum = ArmyManager.upgradeAllArmies(Game.player.iCivID);
                        InGame_Info.iCivID = Game.player.iCivID;
                        InGame_Info.iCivID2 = 0;
                        Game.menuManager.rebuildInGame_Info(Game.getCiv(Game.player.iCivID).getCivName(), Game.lang.get("RegimentsUpgraded") + ": " + tNum);
                        InGame_Info.imgID = Images.infoCrown;
                        Game.menuManager.rebuildInGame_Armies(false, true);
                        Game.menuManager.setVisibleInGame_Armies(true);
                        InGame_Armies.lTime = 0L;
                    }
                }
            });
            menuElements.add(new ButtonStatsRectIMG_Bonuses_Style(Game.lang.get("Cost") + ": ", "" + CFG.getPrecision2(ArmyManager.getUpgradeRegimentCost(Game.player.iCivID) * regimentsToUpgrade, 10), Images.gold, paddingLeft + CFG.PADDING + (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, CFG.BUTTON_HEIGHT3, ImageManager.getImage(Images.battleWidth).getWidth()) {
                @Override
                public Color getColorBonus() {
                    return Colors.HOVER_GOLD;
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("UpgradeAllRegiments"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.upgrade, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    final int tNumOfRegiments = ArmyManager.getRegimentsAvailableToUpgrade(Game.player.iCivID);
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AvailableUpgrades") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + tNumOfRegiments, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(ArmyManager.getUpgradeRegimentCost(Game.player.iCivID) * tNumOfRegiments, 10), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(" = " + Game.lang.get("NumberOfRegiments") + " * " + CFG.getPrecision2(ArmyManager.getUpgradeRegimentCost(Game.player.iCivID), 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
                
                @Override
                public void actionElement() {
                    if (Game.getCiv(Game.player.iCivID).fGold < ArmyManager.getUpgradeRegimentCost(Game.player.iCivID)) {
                        Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(ArmyManager.getUpgradeRegimentCost(Game.player.iCivID), 100), Images.gold);
                    }
                    else {
                        final int tNum = ArmyManager.upgradeAllArmies(Game.player.iCivID);
                        InGame_Info.iCivID = Game.player.iCivID;
                        InGame_Info.iCivID2 = 0;
                        Game.menuManager.rebuildInGame_Info(Game.getCiv(Game.player.iCivID).getCivName(), Game.lang.get("RegimentsUpgraded") + ": " + tNum);
                        InGame_Info.imgID = Images.infoCrown;
                        Game.menuManager.rebuildInGame_Armies(false, true);
                        Game.menuManager.setVisibleInGame_Armies(true);
                        InGame_Armies.lTime = 0L;
                    }
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        menuElements.add(new Text_Title_v2Center(Game.lang.get("Armies"), -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses_Style(Game.lang.get("MilitaryAcademy") + ": ", "" + Game.getCiv(Game.player.iCivID).getMilitaryAcademyLevel() + " / " + Game.getMilitaryAcademy_MaxLvl(Game.player.iCivID), Game_Calendar.IMG_MANPOWER, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, ImageManager.getImage(Images.battleWidth).getWidth()) {
            @Override
            public Color getColorBonus() {
                return Colors.HOVER_GOLD;
            }
            
            @Override
            public void actionElement() {
                if (Game.getCiv(Game.player.iCivID).getMilitaryAcademyLevel() >= Game.getMilitaryAcademy_MaxLvl(Game.player.iCivID)) {
                    Game.menuManager.addToastInsufficient(Game.lang.get("MaximumLevel") + ": ", "" + Game.getCiv(Game.player.iCivID).getMilitaryAcademyLevel() + " / " + Game.getMilitaryAcademy_MaxLvl(Game.player.iCivID), Game_Calendar.IMG_MANPOWER);
                }
                else if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 2) {
                    Game.menuManager.setVisibleInGame_PopUp(false);
                }
                else {
                    Game.menuManager.rebuildInGame_UpgradeMilitaryAcademy();
                }
            }
            
            @Override
            public void buildElementHover() {
                this.menuElementHover = InGame_RecruitArmy.getHoverMilitaryAcademy();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses_Style(Game.lang.get("BattleWidth") + ": ", "" + BattleManager.getBattleWidth(Game.player.iCivID) + " / " + GameValues.battle.BATTLE_MAX_BATTLE_WIDTH, Images.battleWidth, paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, CFG.BUTTON_HEIGHT3, ImageManager.getImage(Images.battleWidth).getWidth()) {
            @Override
            public Color getColorBonus() {
                return Colors.HOVER_GOLD;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("BattleWidth") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + BattleManager.getBattleWidth(Game.player.iCivID) + " / " + GameValues.battle.BATTLE_MAX_BATTLE_WIDTH, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.battleWidth, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("BattleWidthRefersToTheNumberOfUnitsThatCanBeDeployedInAFormationOnTheBattlefield"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Empty());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ArmyDeployment"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.battleWidth, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
            
            @Override
            public void actionElement() {
                if (Game.getCiv(Game.player.iCivID).iArmyPositionSize > 0) {
                    try {
                        if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 51) {
                            Game.menuManager.setVisibleInGame_PopUp(false);
                        }
                        else {
                            InGame_Battlefield.armyDivision = Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(0)).getArmy(Game.getCiv(Game.player.iCivID).getArmyPositionKey(0));
                            if (InGame_Battlefield.armyDivision != null) {
                                Game.menuManager.rebuildInGame_Battlefield();
                            }
                            else {
                                Game.menuManager.setVisibleInGame_PopUp(false);
                            }
                        }
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Bonuses_Style(Game.lang.get("Discipline") + ": ", "" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).civBonuses.Discipline * 100.0f, 10) + "%", Images.discipline, paddingLeft + CFG.PADDING + (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, CFG.BUTTON_HEIGHT3, ImageManager.getImage(Images.battleWidth).getWidth()) {
            @Override
            public Color getColorBonus() {
                return Colors.HOVER_GOLD;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Discipline") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).civBonuses.Discipline * 100.0f, 10) + "%", Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.discipline, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("DisciplineSignifiesOrganizationAndCoordinationOfTheArmyInBattleIncreasingUnitsAttack"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Empty());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("LandUnits"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
            
            @Override
            public void actionElement() {
                if (Game.menuManager.getVisibleInGame_RecruitArmy()) {
                    Game.menuManager.setVisibleInGame_RecruitArmy(false);
                }
                else {
                    if (Game.menuManager.getVisibleInGame_Armies()) {
                        Game.menuManager.setVisibleInGame_Armies(false);
                    }
                    Game.menuManager.rebuildInGame_RecruitArmy();
                    Game.menuManager.setVisibleInGame_RecruitArmy(true);
                }
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        if (Game.getCiv(Game.player.iCivID).iArmyPositionSize == 0) {
            menuElements.add(new Text_StaticBG(Game.lang.get("ArmyNotFound") + ".", CFG.FONT_REGULAR_SMALL, -1, paddingLeft + extraX, buttonY, menuWidth - paddingLeft * 2, ImageManager.getImage(Images.generalFrameBattle).getHeight() + CFG.TEXT_HEIGHT + CFG.PADDING * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        }
        else {
            buttonX = paddingLeft;
            final List<SortedArmies> toSort = new ArrayList<SortedArmies>();
            final List<SortedArmies> sortedArmies = new ArrayList<SortedArmies>();
            for (int i = 0; i < Game.getCiv(Game.player.iCivID).iArmyPositionSize; ++i) {
                final int nProvinceID = Game.getCiv(Game.player.iCivID).getArmyPosition(i);
                if (nProvinceID >= 0) {
                    for (int j = Game.getProvince(nProvinceID).getArmySize() - 1; j >= 0; --j) {
                        if (Game.getProvince(nProvinceID).getArmy(j).civID == Game.player.iCivID && Game.getCiv(Game.player.iCivID).getArmyPositionKey(i).equals(Game.getProvince(nProvinceID).getArmy(j).key)) {
                            toSort.add(new SortedArmies(nProvinceID, j, getSortKey(Game.getProvince(nProvinceID).getArmy(j).key)));
                        }
                    }
                }
            }
            if (noGenerals) {
                final List<SortedArmies> toAddTemp = new ArrayList<SortedArmies>();
                while (toSort.size() > 0) {
                    int addID = 0;
                    for (int k = 1, iSize = toSort.size(); k < iSize; ++k) {
                        if (toSort.get(addID).sortKey > toSort.get(k).sortKey) {
                            addID = k;
                        }
                    }
                    if (Game.getProvince(toSort.get(addID).iProvinceID).getArmy(toSort.get(addID).iArmyID).armyGeneral == null) {
                        sortedArmies.add(toSort.get(addID));
                    }
                    else {
                        toAddTemp.add(toSort.get(addID));
                    }
                    toSort.remove(addID);
                }
                while (toAddTemp.size() > 0) {
                    sortedArmies.add(toAddTemp.get(0));
                    toAddTemp.remove(0);
                }
            }
            else {
                while (toSort.size() > 0) {
                    int addID2 = 0;
                    for (int l = 1, iSize2 = toSort.size(); l < iSize2; ++l) {
                        if (toSort.get(addID2).sortKey > toSort.get(l).sortKey) {
                            addID2 = l;
                        }
                    }
                    sortedArmies.add(toSort.get(addID2));
                    toSort.remove(addID2);
                }
            }
            final int pinH = (ButtonArmyBattle.getButtonHeight() - CFG.PADDING) / 2;
            for (int l = 0, iSize2 = sortedArmies.size(); l < iSize2; ++l) {
                final int tTitleY = buttonY;
                buttonY += CFG.TEXT_HEIGHT + CFG.PADDING * 6;
                buttonX += CFG.PADDING;
                if (Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).armyGeneral == null) {
                    menuElements.add(new ButtonArmyNoGeneral2_Armies(Game.lang.get("NoGeneral"), Game.player.iCivID, buttonX + extraX, buttonY, Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).key, Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).civID, sortedArmies.get(l).iProvinceID));
                }
                else {
                    menuElements.add(new ButtonArmyGeneral2_Armies(Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).armyGeneral.n, Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).civID, Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).armyGeneral.getAttack(), Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).armyGeneral.getDefense(), buttonX + extraX, buttonY, Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).armyGeneral.g, Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).armyGeneral.d, Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).armyGeneral.m, Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).armyGeneral.y, Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).key, Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).civID, sortedArmies.get(l).iProvinceID, Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).armyGeneral.sI, Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).armyGeneral.getCombatExperience()));
                }
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new ButtonArmyIcon_Special(Images.pin, buttonX, buttonY, Game.player.isPinned(Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).key), Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).key, pinH) {
                    @Override
                    public int getImageID() {
                        return Images.pin;
                    }
                    
                    @Override
                    public void actionElement() {
                        this.checkbox = !this.checkbox;
                        Game.player.actionPinArmy(this.armyKey);
                        Game.menuManager.rebuildInGame_Right();
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Pin"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.pin, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                    
                    @Override
                    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
                        if (!this.checkbox) {
                            if (this.getIsHovered()) {
                                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.75f));
                            }
                            else {
                                oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.25f));
                            }
                        }
                        super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                        oSB.setColor(Color.WHITE);
                    }
                });
                menuElements.add(new ButtonArmyIcon_Special(Images.center, buttonX, buttonY + CFG.PADDING + pinH, Game.player.isPinned(Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).key), Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).key, pinH) {
                    @Override
                    public int getImageID() {
                        return Images.center;
                    }
                    
                    @Override
                    public void actionElement() {
                        final Game.ArmyPos nArmyPos = Game.findArmy_FullCheck(Game.player.iCivID, this.armyKey);
                        if (nArmyPos != null) {
                            Game.mapCoords.centerToProvinceID(nArmyPos.iProvinceID);
                        }
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("CenterToProvince"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.center, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                for (int m = 0; m < Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).iArmyRegimentSize; ++m) {
                    int tUnits = Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).lArmyRegiment.get(m).num;
                    int numOfRegiments = 1;
                    for (int o = m + 1; o < Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).iArmyRegimentSize && Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).lArmyRegiment.get(m).uID == Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).lArmyRegiment.get(o).uID && Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).lArmyRegiment.get(m).aID == Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).lArmyRegiment.get(o).aID; ++m, ++numOfRegiments, tUnits += Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).lArmyRegiment.get(m).num, ++o) {}
                    if (ArmyManager.armyCanBeUpgraded(Game.player.iCivID, Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).lArmyRegiment.get(m).uID, Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).lArmyRegiment.get(m).aID)) {
                        menuElements.add(new ButtonArmyBattle_Upgrade("" + tUnits, numOfRegiments, Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).civID, buttonX + extraX, buttonY, Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).lArmyRegiment.get(m).uID, Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).lArmyRegiment.get(m).aID, false, Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).key));
                    }
                    else {
                        menuElements.add(new ButtonArmyBattle("" + tUnits, numOfRegiments, Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).civID, buttonX + extraX, buttonY, Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).lArmyRegiment.get(m).uID, Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).lArmyRegiment.get(m).aID, false));
                    }
                    buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                }
                buttonY = menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding + CFG.PADDING;
                menuElements.add(new TextArmies(Game.getProvince(Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).iArmyOfProvinceID).getProvinceName(), CFG.getNumberWithSpaces("" + Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).iArmy), paddingLeft + extraX + CFG.PADDING + menuWidth * 3 / 10, tTitleY, Math.max(buttonX, menuWidth - paddingLeft * 2) - menuWidth * 3 / 10 - CFG.PADDING, CFG.TEXT_HEIGHT + CFG.PADDING * 5, Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).key, Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).civID, sortedArmies.get(l).iProvinceID, menuWidth - paddingLeft * 2));
                menuElements.add(new ButtonStatsRectIMG_Active_Click(Game.lang.get("ArmyDeployment"), Images.battleWidth, paddingLeft + extraX, tTitleY, menuWidth * 3 / 10, CFG.TEXT_HEIGHT + CFG.PADDING * 5, ImageManager.getImage(Images.battleWidth).getWidth(), sortedArmies.get(l).iProvinceID, true) {
                    String key;
                    
                    @Override
                    public void setText2(final String sText) {
                        this.key = sText;
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("BattleWidth") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle("" + BattleManager.getBattleWidth(Game.player.iCivID) + " / " + GameValues.battle.BATTLE_MAX_BATTLE_WIDTH, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.battleWidth, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Empty());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ArmyDeployment"), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.battleWidth, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                    
                    @Override
                    public void actionElement() {
                        if (Game.getCiv(Game.player.iCivID).iArmyPositionSize > 0) {
                            try {
                                if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 51) {
                                    Game.menuManager.setVisibleInGame_PopUp(false);
                                }
                                else {
                                    int nArmyID = Game.getProvince(this.id).getArmyKeyID(this.key);
                                    if (nArmyID < 0) {
                                        for (int i = 0; i < Game.getProvincesSize(); ++i) {
                                            final int outID = Game.getProvince(i).getArmyKeyID(this.key, Game.player.iCivID);
                                            if (outID >= 0) {
                                                this.id = i;
                                                nArmyID = outID;
                                                break;
                                            }
                                        }
                                    }
                                    if (nArmyID >= 0) {
                                        InGame_Battlefield.armyDivision = Game.getProvince(this.id).getArmy(nArmyID);
                                        if (InGame_Battlefield.armyDivision != null) {
                                            Game.menuManager.rebuildInGame_Battlefield();
                                        }
                                        else {
                                            Game.menuManager.setVisibleInGame_PopUp(false);
                                        }
                                    }
                                }
                            }
                            catch (final Exception ex) {
                                CFG.exceptionStack(ex);
                            }
                        }
                    }
                });
                menuElements.get(menuElements.size() - 1).setText2(Game.getProvince(sortedArmies.get(l).iProvinceID).getArmy(sortedArmies.get(l).iArmyID).key);
                buttonX = paddingLeft;
            }
        }
        buttonY = 0;
        for (int i2 = 0, iSize3 = menuElements.size(); i2 < iSize3; ++i2) {
            if (buttonY < menuElements.get(i2).getPosY() + menuElements.get(i2).getHeight() + CFG.PADDING) {
                buttonY = menuElements.get(i2).getPosY() + menuElements.get(i2).getHeight() + CFG.PADDING;
            }
        }
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG("", false, false, Images.title500) {
            @Override
            public void draw(final SpriteBatch oSB, final int nPosX, final int nPosY, int nWidth, final Status titleStatus) {
                nWidth += Images.boxTitleBORDERWIDTH * 2;
                super.draw(oSB, nPosX - Images.boxTitleBORDERWIDTH, nPosY, nWidth, titleStatus);
            }
            
            @Override
            public long getTime() {
                return InGame_Armies.lTime;
            }
        }, menuX + Images.boxTitleBORDERWIDTH, menuY, menuWidth, menuHeight, menuElements, false, true);
        this.scrollExtraPosX = Images.boxTitleBORDERWIDTH;
    }
    
    public static int getSortKey(final String key) {
        int out = 0;
        for (int i = 0, iSize = key.length(); i < iSize; ++i) {
            out += key.charAt(i);
        }
        return out;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_Armies.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_Armies.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() - Images.boxTitleBORDERWIDTH + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth() + Images.boxTitleBORDERWIDTH * 2, this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() - Images.boxTitleBORDERWIDTH + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() + Images.boxTitleBORDERWIDTH * 2, this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.rulerOver).draw2(oSB, this.getPosX() + (this.getWidth() + Images.boxTitleBORDERWIDTH) / 2 - ImageManager.getImage(Images.rulerOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.rulerOver).getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.rulerOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void updateLanguage() {
        super.updateLanguage();
        this.getTitle().setText(Game.lang.get("Armies"));
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_Armies.lTime = CFG.currentTimeMillis;
    }
    
    @Override
    public boolean getVisible() {
        return super.getVisible() && Game.mapBG.getHideMenuZoomOut();
    }
    
    static {
        InGame_Armies.lTime = 0L;
    }
    
    public static class SortedArmies
    {
        public int iProvinceID;
        public int iArmyID;
        public int sortKey;
        
        public SortedArmies(final int iProvinceID, final int iArmyID, final int sortKey) {
            this.iProvinceID = iProvinceID;
            this.iArmyID = iArmyID;
            this.sortKey = sortKey;
        }
    }
}
