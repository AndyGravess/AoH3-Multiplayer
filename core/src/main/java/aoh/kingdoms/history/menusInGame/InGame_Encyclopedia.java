// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menusInGame.Technology.InGame_TechnologyChoose;
import aoh.kingdoms.history.mainGame.SoundsManager;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Active_Click;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon2_Horizontal;
import aoh.kingdoms.history.map.civilization.CivilizationRanking;
import aoh.kingdoms.history.menusInGame.Goods.InGame_Goods;
import aoh.kingdoms.history.mainGame.Game_Ages;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menusInGame.RecruitArmy.InGame_RecruitArmy;
import aoh.kingdoms.history.menu_element.textStatic.Text_Desc;
import aoh.kingdoms.history.menusInGame.Battle.InGame_Battlefield;
import aoh.kingdoms.history.menu.MenuManager;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTemporaryLoad;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2;
import aoh.kingdoms.history.mainGame.Keyboard;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_Write;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Encyclopedia extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static String sSearch;
    
    public InGame_Encyclopedia() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING;
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        final int menuY = ImageManager.getImage(Images.topStats).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        final int buttonYPadding = CFG.PADDING * 2;
        int buttonX = paddingLeft;
        int buttonY = CFG.PADDING;
        final int tTitleH = CFG.TEXT_HEIGHT + CFG.PADDING * 6;
        final int tTitleH_Inner = CFG.TEXT_HEIGHT + CFG.PADDING * 4;
        final int tMaxInconWidth = ImageManager.getImage(Images.gold).getWidth();
        if (Game.menuManager.getInGame()) {
            menuElements.add(new Text_StaticBG_Write(Game.lang.get("Search") + ": ", CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2) {
                @Override
                public String getTextToDraw() {
                    return InGame_Encyclopedia.sSearch + Keyboard.getKeyboardVerticalLine();
                }
                
                @Override
                public void actionElement() {
                    Game.keyboard.showKeyboard(Keyboard.KeyboardActionType.SEARCH_ENCYCLOPEDIA, InGame_Encyclopedia.sSearch);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        else {
            InGame_Encyclopedia.sSearch = "";
        }
        final boolean showAll = InGame_Encyclopedia.sSearch.length() == 0;
        final String innerSearch = InGame_Encyclopedia.sSearch.toLowerCase();
        if (showAll || searchTexts(innerSearch, new String[] { "ArmyComposition", "ArmyComposition0", "SecondLine1", "SecondLine2", "SecondLine3" })) {
            menuElements.add(new Text_Title_v2(Game.lang.get("Armies"), CFG.FONT_BOLD, CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("ArmyComposition"), Game_Calendar.IMG_MANPOWER, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tTitleH_Inner, tMaxInconWidth, CFG.FONT_BOLD) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/armyComposition.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
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
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("ArmyComposition0"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/armyComposition.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("SecondLine1"), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("SecondLine2"), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("SecondLine3"), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            final int textTitleH = Math.max(ImageManager.getImage(Images.battleArmy0).getHeight() * 2 + CFG.PADDING * 3, CFG.TEXT_HEIGHT + CFG.PADDING * 6);
            menuElements.add(InGame_RecruitArmy.getTitleFirstLine(buttonY, menuWidth, textTitleH));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(InGame_RecruitArmy.getTitleFirstLineSide(buttonY, menuWidth, textTitleH));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(InGame_RecruitArmy.getTitleSecondLine(buttonY, menuWidth, textTitleH));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (showAll || searchTexts(innerSearch, new String[] { "Manpower", "Manpower0", "Manpower1", "Manpower2", "Manpower3" })) {
            menuElements.add(new Text_Title_v2(Game.lang.get("Manpower"), CFG.FONT_BOLD, CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/manpower.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("Manpower"), Game_Calendar.IMG_MANPOWER, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tTitleH_Inner, tMaxInconWidth, CFG.FONT_BOLD) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/manpower.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
                
                @Override
                public void actionElement() {
                    InGame.action3();
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("Manpower0"), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("Manpower1"), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("Manpower2"), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("Manpower3"), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("Manpower") + ": 0% -> 100%: " + Game.lang.get("MonthsX", (int)GameValues.manpower.MANPOWER_FULL_RECOVERY_MONTHS), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (showAll || searchTexts(innerSearch, new String[] { "ReinforceArmyCost", "ReinforceCostEncy" })) {
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("ReinforceArmyCost"), Game_Calendar.IMG_MANPOWER_UP, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tTitleH_Inner, tMaxInconWidth, CFG.FONT_BOLD));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("ReinforceCostEncy") + " " + CFG.getPrecision2(GameValues.army.REINFORCE_ARMY_COST_MODIFIER * 100.0f, 1) + "%", paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (showAll || searchTexts(innerSearch, new String[] { "ProvinceContribution", "ProvinceContribution0" })) {
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("ProvinceContribution"), Images.provinces, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tTitleH_Inner, tMaxInconWidth, CFG.FONT_BOLD) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/manpowerProvince.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("ProvinceContribution0"), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (showAll || searchTexts(innerSearch, new String[] { "IncreasingMaximumManpower", "IncreasingMaximumManpower0", "IncreasingMaximumManpower1", "TipForBestResultsIncreaseManpowerInProvincesWithHighestGrowthRateOfPopulation" })) {
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("IncreasingMaximumManpower"), Game_Calendar.IMG_MANPOWER, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tTitleH_Inner, tMaxInconWidth, CFG.FONT_BOLD) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/manpowerProvince.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("IncreasingMaximumManpower0"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/manpowerBuildings.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("IncreasingMaximumManpower1"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/manpowerProvince.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("TipForBestResultsIncreaseManpowerInProvincesWithHighestGrowthRateOfPopulation"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/manpowerProvince.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (showAll || searchTexts(innerSearch, new String[] { "RegimentsLimit", "RegimnetsLimit0", "WhenTheMaximumRegimentLimitIsReachedArmyRecruitmentCostIncreasesBy", "Regiments3" })) {
            menuElements.add(new Text_Title_v2(Game.lang.get("RegimentsLimit"), CFG.FONT_BOLD, CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/regimentsLimit.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("RegimentsLimit"), Images.regimentsLimit, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tTitleH_Inner, tMaxInconWidth, CFG.FONT_BOLD) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/regimentsLimit.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
                
                @Override
                public void actionElement() {
                    InGame.action3();
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("RegimnetsLimit0"), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(" = " + Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENTS_LIMIT_BASE_VALUE + " + " + Game.lang.get("LocalManpower") + " * " + CFG.getPrecision2(GameValues.army.REGIMENTS_LIMIT_MANPOWER_LEVEL, 100) + " + " + Game.lang.get("CivilizationBonuses"), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("WhenTheMaximumRegimentLimitIsReachedArmyRecruitmentCostIncreasesBy") + ": " + CFG.getPrecision2(GameValues.army.REGIMENTS_LIMIT_RECRUIT_COST_OVER * 100.0f, 10) + "%", paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("Regiments3"), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("Regiments") + " > " + Game.lang.get("RegimentsLimit") + " = " + Game.lang.get("ArmyMaintenance") + " * (" + Game.lang.get("Regiments") + " / " + Game.lang.get("RegimentsLimit") + ")%", paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (showAll || searchTexts(innerSearch, new String[] { "Battles", "DeploymentPhase", "DeploymentPhase0", "DeploymentPhase1", "DeploymentPhase2" })) {
            menuElements.add(new Text_Title_v2(Game.lang.get("Battles"), CFG.FONT_BOLD, CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/battle.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("DeploymentPhase"), Game_Calendar.IMG_MANPOWER, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tTitleH_Inner, tMaxInconWidth, CFG.FONT_BOLD) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/battle.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("DeploymentPhase0"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/battle.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("DeploymentPhase1"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/battleLines.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            final int textTitleH = Math.max(ImageManager.getImage(Images.battleArmy0).getHeight() * 2 + CFG.PADDING * 3, CFG.TEXT_HEIGHT + CFG.PADDING * 6);
            menuElements.add(InGame_RecruitArmy.getTitleFirstLine(buttonY, menuWidth, textTitleH));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(InGame_RecruitArmy.getTitleFirstLineSide(buttonY, menuWidth, textTitleH));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(InGame_RecruitArmy.getTitleSecondLine(buttonY, menuWidth, textTitleH));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new Text_Desc(Game.lang.get("DeploymentPhase2"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/battleWidth.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (showAll || searchTexts(innerSearch, new String[] { "RoleOfGenerals", "RoleOfGenerals0", "RoleOfGenerals1" })) {
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("RoleOfGenerals"), Images.general, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tTitleH_Inner, tMaxInconWidth, CFG.FONT_BOLD) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/generals.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("RoleOfGenerals0"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/generalStats.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("RoleOfGenerals1"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/generalStats.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (showAll || searchTexts(innerSearch, new String[] { "Generals", "CombatExperience", "CombatExperienceDesc" })) {
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("Generals") + ": " + Game.lang.get("CombatExperience"), Images.general, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tTitleH_Inner, tMaxInconWidth, CFG.FONT_BOLD) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/generalStats.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("CombatExperienceDesc"), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (showAll || searchTexts(innerSearch, new String[] { "BattlePhase", "BattlePhase0", "BattlePhase1", "BattlePhase2" })) {
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("BattlePhase"), Images.battle, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tTitleH_Inner, tMaxInconWidth, CFG.FONT_BOLD) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/siegeEnd.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("BattlePhase0"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/battleFirstLine.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("BattlePhase1"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/battleSecondLine.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("BattlePhase2"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/battleCombat.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (showAll || searchTexts(innerSearch, new String[] { "MoraleAndRetreatMechanics", "MoraleAndRetreatMechanics0", "MoraleAndRetreatMechanics1" })) {
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("MoraleAndRetreatMechanics"), Images.morale, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tTitleH_Inner, tMaxInconWidth, CFG.FONT_BOLD) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/morale.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("MoraleAndRetreatMechanics0"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/morale.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("MoraleAndRetreatMechanics1"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/battleRetreat.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (showAll || searchTexts(innerSearch, new String[] { "Discipline", "DisciplineSignifiesOrganizationAndCoordinationOfTheArmyInBattleIncreasingUnitsAttack" })) {
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("Discipline"), Images.discipline, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tTitleH_Inner, tMaxInconWidth, CFG.FONT_BOLD) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/discipline.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("DisciplineSignifiesOrganizationAndCoordinationOfTheArmyInBattleIncreasingUnitsAttack"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/discipline.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (showAll || searchTexts(innerSearch, new String[] { "DestructionOrSurvival", "DestructionOrSurvival0" })) {
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("DestructionOrSurvival"), Images.skull, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tTitleH_Inner, tMaxInconWidth, CFG.FONT_BOLD) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/battleRetreat.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("DestructionOrSurvival0", GameValues.battle.BATTLE_ARMY_DESTROYED_ROUND_ID), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (showAll || searchTexts(innerSearch, new String[] { "War", "ToDeclareWarTheRelationsBetweenCivilizationsMustBeBelowX", "WithAWarScoreOfXTheWinningSideCanProposeAWhitePeace", "WithAWarScoreOfXTheWinningSideCanMakeDemands", "EachMonthTheWinningSideGainsXOfTheTickingWarScoreBasedOnTheCurrentOverallWarScoreFromBattlesAndOccupiedProvinces", "AWhitePeaceWillBeAutomaticallySignedIfThereIsNoActivityForACertainPeriodAndTheWarscoreRemainsLow" })) {
            menuElements.add(new Text_Title_v2(Game.lang.get("Wars"), CFG.FONT_BOLD, CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("War"), Images.war, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tTitleH_Inner, tMaxInconWidth, CFG.FONT_BOLD));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("ToDeclareWarTheRelationsBetweenCivilizationsMustBeBelowX", GameValues.war.RELATIONS_TO_DECLARE_WAR), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("WithAWarScoreOfXTheWinningSideCanProposeAWhitePeace", CFG.getPrecision2(GameValues.peace.WAR_WHITE_PEACE_MIN_WAR_SCORE, 100) + "%"), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("WithAWarScoreOfXTheWinningSideCanMakeDemands", CFG.getPrecision2(GameValues.peace.WAR_MAKE_DEMANDS_MIN_WAR_SCORE, 100) + "%"), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("EachMonthTheWinningSideGainsXOfTheTickingWarScoreBasedOnTheCurrentOverallWarScoreFromBattlesAndOccupiedProvinces", "X"), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("AWhitePeaceWillBeAutomaticallySignedIfThereIsNoActivityForACertainPeriodAndTheWarscoreRemainsLow"), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        }
        if (showAll || searchTexts(innerSearch, new String[] { "PeaceTreaty", "PeaceNegotiations", "PeaceAnnex", "PeaceDemands", "WithAWarScoreOfXTheWinningSideCanMakeDemands", "WithAWarScoreOfXTheWinningSideCanProposeAWhitePeace" })) {
            menuElements.add(new Text_Title_v2(Game.lang.get("PeaceNegotiations"), CFG.FONT_BOLD, CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("PeaceTreaty"), Images.peace, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tTitleH_Inner, tMaxInconWidth, CFG.FONT_BOLD));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("WithAWarScoreOfXTheWinningSideCanMakeDemands", CFG.getPrecision2(GameValues.peace.WAR_MAKE_DEMANDS_MIN_WAR_SCORE, 100) + "%"), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("WithAWarScoreOfXTheWinningSideCanProposeAWhitePeace", CFG.getPrecision2(GameValues.peace.WAR_WHITE_PEACE_MIN_WAR_SCORE, 100) + "%"), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("PeaceDemands"), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("PeaceAnnex"), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        }
        if (showAll || searchTexts(innerSearch, new String[] { "Siege", "SiegeProgress", "DailySiegeProgressIsTheSumOfTheSiegeAbilitiesOfAllUnitsInvolvedInTheSiege", "TheSiegeEndsWhenTheProgressReachesTheProvincesDefense", "AfterASuccessfulSiegeAllNeighboringProvincesWithoutDefensiveBuildingsWillBeOccupied", "MinimumArmyRequiredToSiegeProvinceX" })) {
            menuElements.add(new Text_Title_v2(Game.lang.get("Siege"), CFG.FONT_BOLD, CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/siege.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("SiegeProgress"), Images.siege, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tTitleH_Inner, tMaxInconWidth, CFG.FONT_BOLD) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/siege.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("DailySiegeProgressIsTheSumOfTheSiegeAbilitiesOfAllUnitsInvolvedInTheSiege"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/siegeSum.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("TheSiegeEndsWhenTheProgressReachesTheProvincesDefense"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/siegeDefense.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("AfterASuccessfulSiegeAllNeighboringProvincesWithoutDefensiveBuildingsWillBeOccupied"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/siegeEnd.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new Text_Desc(Game.lang.get("MinimumArmyRequiredToSiegeProvinceX", CFG.getPrecision2((float)(GameValues.siege.SIEGE_REGIMENTS_MIN * Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE), 1)), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        }
        if (showAll || searchTexts(innerSearch, new String[] { "Fogofwar", "FogOfWarYouCanOnlyObserveActionsInYourOwnProvincesThoseOfYourAlliesAndSubjectsDirectlyBorderingProvincesAndWhatYourTroopsUncover" })) {
            menuElements.add(new Text_Title_v2(Game.lang.get("Fogofwar"), CFG.FONT_BOLD, CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/fogOfWar.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("Fogofwar"), Images.provinces, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tTitleH_Inner, tMaxInconWidth, CFG.FONT_BOLD) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/fogOfWar.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("FogOfWarYouCanOnlyObserveActionsInYourOwnProvincesThoseOfYourAlliesAndSubjectsDirectlyBorderingProvincesAndWhatYourTroopsUncover"), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        }
        if (showAll || searchTexts(innerSearch, new String[] { "WarWeariness", "WarWearinessH1", "WarWearinessH2" })) {
            menuElements.add(new Text_Title_v2(Game.lang.get("WarWeariness"), CFG.FONT_BOLD, CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("WarWeariness"), Images.weariness, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tTitleH_Inner, tMaxInconWidth, CFG.FONT_BOLD) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/warWeariness.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("WarWearinessH1"), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("WarWearinessH2"), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (showAll || searchTexts(innerSearch, new String[] { "Diplomacy", "DifferentGovReligion" })) {
            menuElements.add(new Text_Title_v2(Game.lang.get("Diplomacy"), CFG.FONT_BOLD, CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("Diplomacy"), Images.diplomacy, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tTitleH_Inner, tMaxInconWidth, CFG.FONT_BOLD));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("DifferentGovReligion"), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        }
        if (showAll || searchTexts(innerSearch, new String[] { "CivilizationStability", "CivilizationStabilityReflectsThePercentageOfYourProvincesWithLegitimateClaimsComparedToThoseWithoutAndTakesIntoAccountYourReligiousUnity" })) {
            menuElements.add(new Text_Title_v2(Game.lang.get("CivilizationStability"), CFG.FONT_BOLD, CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/civStability.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("CivilizationStability"), Images.civStability, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tTitleH_Inner, tMaxInconWidth, CFG.FONT_BOLD) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/civStability.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("CivilizationStabilityReflectsThePercentageOfYourProvincesWithLegitimateClaimsComparedToThoseWithoutAndTakesIntoAccountYourReligiousUnity"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/coreReligion.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        }
        if (showAll || searchTexts(innerSearch, new String[] { "Province", "ProvinceMonthlyIncome", "Income0", "Income1" })) {
            menuElements.add(new Text_Title_v2(Game.lang.get("Province"), CFG.FONT_BOLD, CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/province.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("ProvinceMonthlyIncome"), Images.gold, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tTitleH_Inner, tMaxInconWidth, CFG.FONT_BOLD) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/provinceIncome.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("Income0"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/provinceIncome.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("Income1"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/provinceIncomeReduced.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (showAll || searchTexts(innerSearch, new String[] { "TaxEfficiency", "TaxEfficiency0" })) {
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("TaxEfficiency"), Images.tax, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tTitleH_Inner, tMaxInconWidth, CFG.FONT_BOLD) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/provinceTax.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("TaxEfficiency0"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/provinceTax.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (showAll || searchTexts(innerSearch, new String[] { "EconomyOfProvince", "Economy0", "Economy1" })) {
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("EconomyOfProvince"), Game_Calendar.IMG_ECONOMY, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tTitleH_Inner, tMaxInconWidth, CFG.FONT_BOLD) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/provinceEconomy.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("Economy0"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/provinceEconomy.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("Economy1"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/provinceGrowthRate.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (showAll || searchTexts(innerSearch, new String[] { "ProducedGoods", "Production0", "Production1", "Production2" })) {
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("ProducedGoods"), Images.goods, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tTitleH_Inner, tMaxInconWidth, CFG.FONT_BOLD) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/provinceResource.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("Production0"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/provinceResource.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("Production1"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/provinceProduction.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("Production2"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/largestGoods.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
                
                @Override
                public void actionElement() {
                    Game.menuManager.rebuildInGame_Goods();
                    Game.menuManager.setVisibleInGame_Goods(true);
                    InGame_Goods.lTime = 0L;
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (showAll || searchTexts(innerSearch, new String[] { "Population", "Population0", "ThePopulationWillSlowlyAssimilateIfTheCivilizationHasACore" })) {
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("Population"), Images.population, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tTitleH_Inner, tMaxInconWidth, CFG.FONT_BOLD) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/provincePopulation.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("Population0"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/provinceTax.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("ThePopulationWillSlowlyAssimilateIfTheCivilizationHasACore"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/provinceAssimilation.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (showAll || searchTexts(innerSearch, new String[] { "GrowthRate", "GrowthRate0" })) {
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("GrowthRate"), Images.populationGrowth, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tTitleH_Inner, tMaxInconWidth, CFG.FONT_BOLD) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/provinceGrowthRate.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("GrowthRate0"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/provinceGrowthRate2.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (showAll || searchTexts(innerSearch, new String[] { "Diseases", "Plague0", "Plague1" })) {
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("Diseases"), Images.disease, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tTitleH_Inner, tMaxInconWidth, CFG.FONT_BOLD) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/disease.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("Plague0"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/disease.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("Plague1"), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (showAll || searchTexts(innerSearch, new String[] { "LegacyPoints", "Legacy", "Legacy0", "Legacy1", "Legacy2" })) {
            menuElements.add(new Text_Title_v2(Game.lang.get("LegacyPoints"), CFG.FONT_BOLD, CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/legacy.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("Legacy"), Images.legacy, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tTitleH_Inner, tMaxInconWidth, CFG.FONT_BOLD) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/legacy.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("Legacy0"), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("Legacy1"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/legacy.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("Legacy2"), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (showAll || searchTexts(innerSearch, new String[] { GameValues.court.COUNCIL_NAME, "Advisors", "Advisor0" })) {
            menuElements.add(new Text_Title_v2(Game.lang.get(GameValues.court.COUNCIL_NAME), CFG.FONT_BOLD, CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/advisors.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("Advisors"), Images.council, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tTitleH_Inner, tMaxInconWidth, CFG.FONT_BOLD) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/advisors.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("Advisor0"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/advisors.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (showAll || searchTexts(innerSearch, new String[] { "Corruption", "SupremeCourt", "Corruption0" })) {
            menuElements.add(new Text_Title_v2(Game.lang.get("Corruption"), CFG.FONT_BOLD, CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/supremeCourt.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("SupremeCourt"), Images.corruption, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tTitleH_Inner, tMaxInconWidth, CFG.FONT_BOLD) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/supremeCourt.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("Corruption0"), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (showAll || searchTexts(innerSearch, new String[] { "Technologies", "Research", "Technology0", "Technology1" })) {
            menuElements.add(new Text_Title_v2(Game.lang.get("Technologies"), CFG.FONT_BOLD, CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/technology.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("Research"), Game_Calendar.IMG_TECHNOLOGY, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tTitleH_Inner, tMaxInconWidth, CFG.FONT_BOLD));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("Technology0"), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("Technology1"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/library.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (showAll || searchTexts(innerSearch, new String[] { "CivilizationAdvantages", "EachTimeYouUnlockATechnologyYouWillGainOneAdvantagePointThatYouCanExchangeForABonus" })) {
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("CivilizationAdvantages"), Images.advantages, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tTitleH_Inner, tMaxInconWidth, CFG.FONT_BOLD) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_ImageTemporaryLoad("ui/tutorial/advantages.png"));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new Text_Desc(Game.lang.get("EachTimeYouUnlockATechnologyYouWillGainOneAdvantagePointThatYouCanExchangeForABonus"), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (showAll || searchTexts(innerSearch, new String[] { "CivilizationRank" })) {
            menuElements.add(new Text_Title_v2(Game.lang.get("CivilizationRank"), CFG.FONT_BOLD, CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonStatsRectIMG(Game.lang.get("CivilizationRank"), CivilizationRanking.getCivilizationRank_IMG(Game.getCiv(Game.player.iCivID).iCivRankID), paddingLeft, buttonY, menuWidth - paddingLeft * 2, tTitleH_Inner, tMaxInconWidth, CFG.FONT_BOLD));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            final int maxIconWidth = ImageManager.getImage(Images.council).getWidth() + CFG.PADDING * 2;
            final int buttonW_Rank = (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2;
            int i = 0;
            while (i < GameValues.civRank.CIV_RANK_MANPOWER_MAX.length) {
                if (i % 2 == 0) {
                    buttonX = paddingLeft;
                }
                else {
                    buttonX = paddingLeft + CFG.PADDING + buttonW_Rank;
                }
                menuElements.add(new TextIcon2_Horizontal(CivilizationRanking.getCivilizationRank_Name(i), CivilizationRanking.getCivilizationRank_IMG(i), buttonX, buttonY, buttonW_Rank, CFG.BUTTON_HEIGHT3, maxIconWidth) {
                    int id;
                    
                    @Override
                    public void buildElementHover() {
                        this.menuElementHover = CivilizationRanking.buildElementHover(this.id, this.id);
                    }
                    
                    @Override
                    public void setCurrent(final int nCurrent) {
                        this.id = nCurrent;
                    }
                });
                menuElements.get(menuElements.size() - 1).setCurrent(i);
                if (++i % 2 == 0) {
                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
            }
            i = 0;
            for (int iSize = menuElements.size(); i < iSize; ++i) {
                if (buttonY < menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING) {
                    buttonY = menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING;
                }
            }
            buttonX = paddingLeft;
        }
        if (Game.menuManager.getInGame()) {
            menuElements.add(new Text_Title_v2(Game.lang.get("More"), CFG.FONT_BOLD, CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, tTitleH));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            final int buttonH = CFG.isDesktop() ? CFG.BUTTON_HEIGHT2 : CFG.BUTTON_HEIGHT;
            menuElements.add(new ButtonStatsRectIMG_Active_Click(Game.lang.get("ListOfUnits"), Game_Calendar.IMG_MANPOWER, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, ImageManager.getImage(Images.advantages).getWidth() + CFG.PADDING * 4, 0, 0L) {
                @Override
                public int getSFX() {
                    return SoundsManager.SOUND_CLICK_TOP;
                }
                
                @Override
                public void actionElement() {
                    if (Game.menuManager.getVisibleInGame_TechnologyChoose() && !InGame_TechnologyChoose.IN_TECHNOLOGY_CHOOSE && !InGame_ListOfBuildings.IN_BUILDINGS) {
                        Game.menuManager.setVisibleInGame_TechnologyChoose(false);
                    }
                    else {
                        Game.menuManager.rebuildInGame_TechnologyChoose_HideMenus();
                        Game.menuManager.rebuildInGame_ListOfUnits();
                    }
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ListOfUnits"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonStatsRectIMG_Active_Click(Game.lang.get("ListOfBuildings"), Images.buildings, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, ImageManager.getImage(Images.advantages).getWidth() + CFG.PADDING * 4, 0, 0L) {
                @Override
                public int getSFX() {
                    return SoundsManager.SOUND_CLICK_TOP;
                }
                
                @Override
                public void actionElement() {
                    if (Game.menuManager.getVisibleInGame_TechnologyChoose() && !InGame_TechnologyChoose.IN_TECHNOLOGY_CHOOSE && InGame_ListOfBuildings.IN_BUILDINGS) {
                        Game.menuManager.setVisibleInGame_TechnologyChoose(false);
                    }
                    else {
                        Game.menuManager.rebuildInGame_TechnologyChoose_HideMenus();
                        Game.menuManager.rebuildInGame_ListOfBuildings();
                    }
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ListOfBuildings"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.buildings, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        buttonY += CFG.PADDING;
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - (Game.menuManager.getInGame() ? (CFG.GAME_HEIGHT - Game.menuManager.getInGame_MapModesPosY() + CFG.PADDING * 3) : (ImageManager.getImage(Images.buttonPlay).getHeight() + CFG.PADDING * 3)));
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG(Game.lang.get("Encyclopedia"), false, false, Images.title500) {
            @Override
            public long getTime() {
                return InGame_Encyclopedia.lTime;
            }
        }, Game.menuManager.getInGame() ? (CFG.GAME_WIDTH - menuWidth - CFG.PADDING * 2) : (CFG.PADDING * 2), menuY, menuWidth, menuHeight, menuElements, false, true);
        this.drawScrollPositionAlways = false;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_Encyclopedia.lTime + 60L >= CFG.currentTimeMillis) {
            if (Game.menuManager.getInGame()) {
                iTranslateX = iTranslateX + CFG.BUTTON_WIDTH - (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_Encyclopedia.lTime) / 60.0f));
            }
            else {
                iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_Encyclopedia.lTime) / 60.0f));
            }
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.civInfoOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.civInfoOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.civInfoOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void actionCloseMenu() {
        super.actionCloseMenu();
        Game.keyboard.hideKeyboard();
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_Encyclopedia.lTime = CFG.currentTimeMillis;
        if (!visible) {
            Game.keyboard.hideKeyboard();
        }
    }
    
    public static boolean searchTexts(final String innerSearch, final String[] toCheck) {
        for (int i = toCheck.length - 1; i >= 0; --i) {
            if (Game.lang.get(toCheck[i]).toLowerCase().indexOf(innerSearch) != -1) {
                return true;
            }
        }
        return false;
    }
    
    static {
        InGame_Encyclopedia.lTime = 0L;
        InGame_Encyclopedia.sSearch = "";
    }
}
