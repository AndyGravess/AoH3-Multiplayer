// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ_Compare;
import aoh.kingdoms.history.menu.View;
import aoh.kingdoms.history.menus.MainMenu;
import aoh.kingdoms.history.menusInGame.Technology.InGame_TechnologyChoose;
import aoh.kingdoms.history.menu.MenuManager;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions;
import aoh.kingdoms.history.menu.ColorPicker;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.menusInGame.Battle.InGame_OngoingBattles;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu_element.button.Button32Padd;
import aoh.kingdoms.history.menu_element.Minimap;
import aoh.kingdoms.history.menusInGame.Right.InGame_Right;
import aoh.kingdoms.history.mainGame.Touch;
import aoh.kingdoms.history.menu_element.button.ButtonTopOutliner;
import aoh.kingdoms.history.menu_element.button.ButtonTopDate;
import aoh.kingdoms.history.menu_element.button.ButtonTopSpeed;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_CurrentSituation;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.map.civilization.CivilizationsNeighbors;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ_List;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Rank;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.map.province.ProvinceInvest;
import aoh.kingdoms.history.menu_element.textStatic.TextTop_Nukes;
import aoh.kingdoms.history.map.diplomacy.DiplomacyManager;
import aoh.kingdoms.history.menu_element.textStatic.TextTop_Diplomacy;
import aoh.kingdoms.history.menu_element.textStatic.TextTop_Legacy;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Clear;
import aoh.kingdoms.history.map.technology.TechnologyTree;
import aoh.kingdoms.history.menusInGame.Technology.InGame_TechnologyTree;
import aoh.kingdoms.history.menu_element.textStatic.TextTop_Technology;
import aoh.kingdoms.history.map.army.ArmyDivision;
import aoh.kingdoms.history.map.civilization.Civilization;
import aoh.kingdoms.history.map.allianceHRE.HREManager;
import aoh.kingdoms.history.map.allianceHRE.Alliance;
import aoh.kingdoms.history.mainGame.Game_Ages;
import aoh.kingdoms.history.map.army.ArmyRegiment;
import aoh.kingdoms.history.menusInGame.RecruitArmy.InGame_RecruitArmy;
import aoh.kingdoms.history.menu_element.textStatic.TextTop_Manpower;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menusInGame.Budget.InGame_Budget;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Graph;
import aoh.kingdoms.history.menu_element.graph.Graph;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus_Perc;
import aoh.kingdoms.history.menu.HoverManager;
import aoh.kingdoms.history.menu_element.textStatic.TextTop_Gold;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Space;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title;
import aoh.kingdoms.history.map.civilization.CivilizationRanking;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.mainGame.SoundsManager;
import aoh.kingdoms.history.menu_element.button.ButtonTopFlag;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.FileManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menusInGame.DrawOver.InGameDrawOver_RecruitArmy;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menusInGame.DrawOver.InGameDrawOver;
import aoh.kingdoms.history.menu.Menu;

public class InGame extends Menu
{
    public static InGameDrawOver drawOver;
    public static boolean ONLY_MAP_MODE;
    public static int rankPosXW;
    public static int topStatsPadding;
    public static int topRightPadding;
    public static int topStatsHeight;
    public static int topBar2PosY;
    public static int leftSideBarPadding;
    public static int leftSideBarInnerWidth;
    public static int outlinerExtraX;
    public static int outlinerExtraClassic;
    public static int outlinerExtraUQ;
    public static int iMinimapPosY;
    public static boolean inAnimation;
    public static boolean hideAnimation;
    public long minimapTime;
    public int minimapAnimationTime;
    public int lastStatElementID;
    public int lastStatElementID2;
    public int dateElementID;
    public int plusElementID;
    public int minusElementID;
    public int outlinerElementID;
    public int minimapElementID;
    
    public static final void updateDrawOver() {
        if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_RECRUIT_ARMY || Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_NEW_ARMY_CHOOSE_PROVINCE || Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_NUKE_CHOOSE_PROVINCE || Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_COLONIZE_CHOOSE_PROVINCE || Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_MERCENARIES_CHOOSE_PROVINCE || Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INVEST_IN_ECONOMY || Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEVELOP_INFRASTRUCTURE || Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INCREASE_TAX_EFFICIENCY || Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INCREASE_MANPOWER || Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_MOVE_CAPITAL || Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INCREASE_GROWTH_RATE || Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_BUILDING || Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_CONVERT_RELIGION || Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DIPLOMACY_IMPROVE_RELATIONS || Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DIPLOMACY_DAMAGE_RELATIONS || Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_CORE) {
            InGame.drawOver = new InGameDrawOver_RecruitArmy();
        }
        else {
            InGame.drawOver = new InGameDrawOver();
        }
    }
    
    public static void initInGame() {
        try {
            final FileHandle tempFileT = FileManager.loadFile("ui/" + CFG.getRescouresPath() + "top/" + "topStatsAndRightPadding.txt");
            final String[] tempSplit = tempFileT.readString().split(";");
            InGame.topStatsPadding = Integer.parseInt(tempSplit[0]);
            InGame.topRightPadding = Integer.parseInt(tempSplit[1]);
            InGame.topStatsHeight = Integer.parseInt(tempSplit[2]);
            InGame.topBar2PosY = ImageManager.getImage(Images.topStats).getHeight() - Integer.parseInt(tempSplit[2]);
            InGame.leftSideBarPadding = Integer.parseInt(tempSplit[4]);
            InGame.leftSideBarInnerWidth = Integer.parseInt(tempSplit[5]);
            InGame.outlinerExtraClassic = Integer.parseInt(tempSplit[6]);
            InGame.outlinerExtraUQ = Integer.parseInt(tempSplit[7]);
        }
        catch (final GdxRuntimeException ex) {
            CFG.exceptionStack((Throwable)ex);
        }
    }
    
    public InGame() {
        this.minimapTime = 0L;
        this.minimapAnimationTime = 275;
        this.lastStatElementID = 0;
        this.lastStatElementID2 = 0;
        this.dateElementID = 0;
        this.plusElementID = 0;
        this.minusElementID = 0;
        this.outlinerElementID = 0;
        this.minimapElementID = 0;
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int initMinimapPosY = InGame.iMinimapPosY;
        InGame.iMinimapPosY = 0;
        menuElements.add(new ButtonTopFlag(0, 0, true) {
            @Override
            public int getSFX() {
                return SoundsManager.FLAG_CLICK;
            }
            
            @Override
            public void actionElement() {
                InGame.action1();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(Game.player.iCivID, CivilizationRanking.getCivilizationRank_Name(Game.getCiv(Game.player.iCivID).iCivRankID)));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(GameValues.court.COUNCIL_NAME), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.council, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (Game.getCiv(Game.player.iCivID).ruler != null) {
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.ideologiesManager.getIdeology(Game.getCiv(Game.player.iCivID).getIdeologyID()).RulerTitle + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(Game.player.iCivID).ruler.Name));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                if (CFG.isDesktop()) {
                    nData.add(new MenuElement_HoverElement_Type_Space());
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Shortcut") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("F1", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        final int elemPosX = ImageManager.getImage(Images.flagBG).getWidth() + CFG.PADDING + CFG.PADDING / 2;
        menuElements.add(new TextTop_Gold(Images.gold, "", "", elemPosX, CFG.PADDING) {
            @Override
            public int getSFX() {
                return this.getIsActiveButton() ? SoundsManager.SOUND_CLICK_TOP : SoundsManager.BUDGET_CLICK;
            }
            
            @Override
            public void updateHovered() {
                super.updateHovered();
                if (CFG.isDesktop() && GameValues.mapModes.TOP_HOVER_BUDGET && HoverManager.hoverTime + GameValues.mapModes.HOVER_TIME <= CFG.currentTimeMillis && this.getIsHovered() && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFAULT) {
                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_PROVINCE_INCOME_HOVER);
                }
            }
            
            @Override
            public void setIsHovered(final boolean isHovered) {
                super.setIsHovered(isHovered);
                if (CFG.isDesktop() && GameValues.mapModes.TOP_HOVER_BUDGET && !isHovered && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_PROVINCE_INCOME_HOVER) {
                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Budget"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                float fVal = Game.getCiv(Game.player.iCivID).fGold;
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus_Perc(Game.lang.get("Treasury") + ": ", "" + CFG.getPrecision2(fVal, 100), Images.gold, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, (fVal == 0.0f) ? Colors.COLOR_TEXT_MODIFIER_NEUTRAL : ((fVal > 0.0f) ? Colors.HOVER_GOLD : Colors.COLOR_TEXT_MODIFIER_NEGATIVE), fVal / Game.getMaxAmountOfGold(Game.player.iCivID)));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumAmountOfGold") + ": ", "" + CFG.getNumberWithSpaces("" + CFG.getPrecision2((float)Game.getMaxAmountOfGold(Game.player.iCivID), 1)), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (Game.getCiv(Game.player.iCivID).fGold >= Game.getMaxAmountOfGold(Game.player.iCivID)) ? Colors.HOVER_NEGATIVE : Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                fVal = Game.getCiv(Game.player.iCivID).fTotalIncomePerMonth + Game.getCiv(Game.player.iCivID).civBonuses.MonthlyIncome;
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("TotalIncome") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(((fVal > 0.0f) ? "+" : "") + CFG.getPrecision2(fVal, 100), CFG.FONT_BOLD_SMALL, (fVal == 0.0f) ? Colors.COLOR_TEXT_MODIFIER_NEUTRAL : ((fVal > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE)));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                fVal = Game.getCiv(Game.player.iCivID).fTotalExpensesPerMonth;
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("TotalExpenses") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(((fVal > 0.0f) ? "-" : "+") + CFG.getPrecision2(fVal, 100), CFG.FONT_BOLD_SMALL, (fVal == 0.0f) ? Colors.COLOR_TEXT_MODIFIER_NEUTRAL : ((fVal > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_NEGATIVE : Colors.COLOR_TEXT_MODIFIER_POSITIVE)));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.goldNegative, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                fVal = Game.getCiv(Game.player.iCivID).fTotalIncomePerMonth + Game.getCiv(Game.player.iCivID).civBonuses.MonthlyIncome - Game.getCiv(Game.player.iCivID).fTotalExpensesPerMonth;
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Balance") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(((fVal > 0.0f) ? "+" : "") + CFG.getPrecision2(fVal, 100), CFG.FONT_BOLD_SMALL, (fVal == 0.0f) ? Colors.COLOR_TEXT_MODIFIER_NEUTRAL : ((fVal > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE)));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                try {
                    nData.add(new MenuElement_HoverElement_Type_Empty());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Graph(Game.lang.get("Income"), Graph.GraphType.PLAYER_INCOME, true));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Empty());
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
                if (CFG.isDesktop()) {
                    nData.add(new MenuElement_HoverElement_Type_Space());
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Shortcut") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("F2", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            public void actionElement() {
                InGame.action2();
            }
            
            @Override
            public void actionElementPPM() {
                InGame_Budget.actionTakeLoan();
            }
        });
        menuElements.add(new TextTop_Manpower(Game_Calendar.IMG_MANPOWER, "", "", elemPosX, CFG.PADDING) {
            @Override
            public int getSFX() {
                return this.getIsActiveButton() ? SoundsManager.SOUND_CLICK_TOP : SoundsManager.ARMY_CLICK;
            }
            
            @Override
            public int getImageID() {
                return Game_Calendar.IMG_MANPOWER;
            }
            
            @Override
            public int getPosX() {
                return InGame.this.getMenuElement(1).getPosX() + InGame.this.getMenuElement(1).getWidth() + InGame.this.getStatsPadding();
            }
            
            @Override
            public void actionElement() {
                InGame.action3();
            }
            
            @Override
            public void actionElementPPM() {
                if (Game.menuManager.inCreateNewArmy && Game.menuManager.getVisibleInGame_RecruitArmy()) {
                    Game.menuManager.setVisibleInGame_RecruitArmy(false);
                }
                else {
                    InGame_RecruitArmy.actionCreateNewArmy();
                    Game.menuManager.hideMenus_RecruitArmy(true);
                }
            }
            
            @Override
            public void updateHovered() {
                super.updateHovered();
                if (CFG.isDesktop() && GameValues.mapModes.TOP_HOVER_ARMY && HoverManager.hoverTime + GameValues.mapModes.HOVER_TIME <= CFG.currentTimeMillis && this.getIsHovered() && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFAULT) {
                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_PROVINCE_MANPOWER_HOVER_PLAYER);
                }
            }
            
            @Override
            public void setIsHovered(final boolean isHovered) {
                super.setIsHovered(isHovered);
                if (CFG.isDesktop() && GameValues.mapModes.TOP_HOVER_ARMY && !isHovered && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_PROVINCE_MANPOWER_HOVER_PLAYER) {
                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                final Civilization playerCiv = Game.getCiv(Game.player.iCivID);
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("RecruitRegiments"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                final float manpowerPerc = (float)Math.min(1.0, playerCiv.fManpower / playerCiv.fManpowerMax);
                final int manpowerDays = (int)((1.0f - manpowerPerc) * (GameValues.manpower.MANPOWER_FULL_RECOVERY_MONTHS * 30.0f));
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus_Perc(Game.lang.get("Manpower") + ": ", "" + CFG.getNumberWithSpaces("" + (int)Math.floor(playerCiv.fManpower)) + " [" + (int)(manpowerPerc * 100.0f) + "%]", Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD, manpowerPerc));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", "" + CFG.getNumberWithSpaces("" + (int)Math.floor(playerCiv.fManpowerMax)), Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (manpowerPerc < 0.98f && Game_Calendar.getNumOfDays_ByTurnsPlayed_WithoutDays(manpowerDays).length() > 0) {
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus((int)(manpowerPerc * 100.0f) + "% -> 100%: ", Game_Calendar.getNumOfDays_ByTurnsPlayed_WithoutDays(manpowerDays), Game_Calendar.IMG_MANPOWER_TIME, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("0% -> 100%: ", "" + Game.lang.get("MonthsX", (int)GameValues.manpower.MANPOWER_FULL_RECOVERY_MONTHS), Game_Calendar.IMG_MANPOWER_TIME, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                int armiesManpower = 0;
                int armiesManpowerFull = 0;
                try {
                    for (int i = 0; i < playerCiv.iArmyPositionSize; ++i) {
                        final ArmyDivision armyDivision = Game.getProvince(playerCiv.getArmyPosition(i)).getArmy(playerCiv.getArmyPositionKey(i));
                        if (armyDivision != null) {
                            for (int j = 0; j < armyDivision.iArmyRegimentSize; ++j) {
                                armiesManpower += armyDivision.lArmyRegiment.get(j).num;
                                ++armiesManpowerFull;
                            }
                        }
                    }
                    armiesManpowerFull *= Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE;
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Armies") + ", " + Game.lang.get("Strength") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(armiesManpower / (float)armiesManpowerFull * 100.0f, 10) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Armies") + ", " + Game.lang.get("Manpower") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getNumberWithSpaces("" + armiesManpower) + " / " + CFG.getNumberWithSpaces("" + armiesManpowerFull), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                }
                catch (final Exception ex) {}
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MonthlyChange") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getNumberWithSpaces("" + CFG.getPrecision2(playerCiv.fManpowerPerMonth, 1)), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BaseValue") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getNumberWithSpaces("" + GameValues.manpower.MANPOWER_MAX_BASE), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Provinces") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getNumberWithSpaces("" + (int)Math.floor(Math.max(0.0f, playerCiv.getManpowerMax_Provinces_INFO()))), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                if (GameValues.civRank.CIV_RANK_MANPOWER_MAX[playerCiv.iCivRankID] != 0) {
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("CivilizationRank") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getNumberWithSpaces("" + CFG.getPrecision2((float)GameValues.civRank.CIV_RANK_MANPOWER_MAX[playerCiv.iCivRankID], 1)), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                }
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("CivilizationBonuses") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getNumberWithSpaces("" + CFG.getPrecision2(playerCiv.civBonuses.MaxManpower, 1)), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                final float tNum = playerCiv.getManpowerMax_Vassals_INFO();
                if (tNum > 0.0f) {
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get(Game_Ages.getVassals()) + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getNumberWithSpaces("" + (int)Math.floor(tNum)), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                }
                if (playerCiv.fManpowerMax_ToLord != 0.0) {
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get(Game_Ages.getLord()) + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(playerCiv.fManpowerMax_ToLord * -1.0, 1), CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                }
                for (int k = 0; k < playerCiv.inAllianceSize; ++k) {
                    if (Game.alliancesSpecial.get(playerCiv.inAlliance.get(k)).iLeaderCivID == Game.player.iCivID && Game.alliancesSpecial.get(playerCiv.inAlliance.get(k)).typeOfAlliance == 0) {
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get(Game.alliancesSpecial.get(playerCiv.inAlliance.get(k)).Name_Alliance) + ": ", CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getNumberWithSpaces("" + CFG.getPrecision2((float)HREManager.getManpower_Emperor(playerCiv.inAlliance.get(k)), 1)), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                        nElements.add(new MenuElement_HoverElement(nData, false));
                        nData.clear();
                    }
                }
                if (playerCiv.civBonuses.MaxManpower_Percentage != 0.0f) {
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaximumManpower") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text(((playerCiv.civBonuses.MaxManpower_Percentage > 0.0f) ? "+" : "") + CFG.getPrecision2(playerCiv.civBonuses.MaxManpower_Percentage * 100.0f, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                }
                if (CFG.isDesktop()) {
                    nData.add(new MenuElement_HoverElement_Type_Space());
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Shortcut") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("F3", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new TextTop_Technology(Game_Calendar.IMG_TECHNOLOGY, "", "", elemPosX, CFG.PADDING) {
            @Override
            public int getSFX() {
                return this.getIsActiveButton() ? SoundsManager.SOUND_CLICK_TOP : SoundsManager.TECHNOLOGY_CLICK;
            }
            
            @Override
            public int getPosX() {
                return InGame.this.getMenuElement(2).getPosX() + InGame.this.getMenuElement(2).getWidth() + InGame.this.getStatsPadding();
            }
            
            @Override
            public void actionElement() {
                InGame.action4();
            }
            
            @Override
            public void actionElementPPM() {
                if (Game.menuManager.getVisibleInGame_TechnologyTree()) {
                    Game.menuManager.setVisibleInGame_TechnologyTree(false);
                }
                else {
                    Game.menuManager.setVisibleInGame_TechnologyChoose(false);
                    InGame_TechnologyTree.centerToTechID = -1;
                    Game.menuManager.rebuildInGame_TechnologyTree(false, true);
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                if (Game.getCiv(Game.player.iCivID).getActiveTechResearch() >= 0) {
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Technology") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(TechnologyTree.lTechnology.get(Game.getCiv(Game.player.iCivID).getActiveTechResearch()).Name, CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus_Perc(Game.lang.get("ResearchProgress") + ": ", CFG.getPrecision2(Game.getCiv(Game.player.iCivID).getResearchProgress(Game.getCiv(Game.player.iCivID).getActiveTechResearch()) * 100.0f, 100) + "%", Game_Calendar.IMG_TECHNOLOGY, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD, Game.getCiv(Game.player.iCivID).getResearchProgress(Game.getCiv(Game.player.iCivID).getActiveTechResearch())));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                else {
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ChooseResearch"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus_Perc(Game.lang.get("MonthlyChange") + ": ", "+" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fResearchPerMonth, 100) + " / " + CFG.getPrecision2(TechnologyTree.getMaxResearch(Game.player.iCivID), 100), Game_Calendar.IMG_TECHNOLOGY, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD, Game.getCiv(Game.player.iCivID).fResearchPerMonth / TechnologyTree.getMaxResearch(Game.player.iCivID)));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumResearch") + ": ", CFG.getPrecision2(TechnologyTree.getMaxResearch(Game.player.iCivID), 100), Game_Calendar.IMG_TECHNOLOGY, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("MaximumResearch") + " = " + CFG.getPrecision2(GameValues.research.MAX_RESEARCH_BASE, 100) + " + " + Game.lang.get("Capital") + ", " + Game.lang.get("GrowthRate") + " * " + CFG.getPrecision2(GameValues.research.MAX_RESEARCH_PER_GROWTH_RATE_IN_CAPITAL, 100) + " + " + Game.lang.get("CapitalCity") + ", " + Game.lang.get("Level") + " * " + CFG.getPrecision2(GameValues.capital.CAPITAL_MAX_RESEARCH_PER_LVL, 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BaseValue") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(GameValues.research.BASE_RESEARCH, 100), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Buildings") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).getResearchFromBuildings(), 100), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                if (Game.getCiv(Game.player.iCivID).advisorTechnology.Research != 0.0f) {
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get(GameValues.court.ADVISOR_NAME_INNOVATION) + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).advisorTechnology.Research, 100), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                    nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                }
                if (GameValues.civRank.CIV_RANK_MONTHLY_RESEARCH[Game.getCiv(Game.player.iCivID).iCivRankID] != 0.0f) {
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("CivilizationRank") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(GameValues.civRank.CIV_RANK_MONTHLY_RESEARCH[Game.getCiv(Game.player.iCivID).iCivRankID], 100), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                    nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                }
                if (Game.getCiv(Game.player.iCivID).civBonuses.ResearchPoints != 0.0f) {
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("CivilizationBonuses") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).civBonuses.ResearchPoints, 100), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                    nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                }
                if (Game.getCiv(Game.player.iCivID).civBonuses.Research != 0.0f) {
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Research") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).civBonuses.Research, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                    nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                }
                if (CFG.isDesktop()) {
                    nData.add(new MenuElement_HoverElement_Type_Space());
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Shortcut") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("F4", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new TextTop_Legacy(Images.legacy, "", "", elemPosX, CFG.PADDING) {
            @Override
            public int getSFX() {
                return SoundsManager.SOUND_CLICK_TOP;
            }
            
            @Override
            public int getPosX() {
                return InGame.this.getMenuElement(3).getPosX() + InGame.this.getMenuElement(3).getWidth() + InGame.this.getStatsPadding();
            }
            
            @Override
            public void actionElement() {
                InGame.action5();
            }
            
            @Override
            public void actionElementPPM() {
                if (Game.menuManager.getVisibleInGame_TechnologyChoose()) {
                    Game.menuManager.setVisibleInGame_TechnologyChoose(false);
                }
                else {
                    Game.menuManager.rebuildInGame_CivilizationAdvantages(Game.player.iCivID);
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("CivilizationLegacy"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.legacy, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus_Perc(Game.lang.get("LegacyPoints") + ": ", "" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fLegacy, 100), Images.legacy, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD, Game.getCiv(Game.player.iCivID).fLegacy / GameValues.legacy.MAX_LEGACY_POINTS));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyChange") + ": ", "" + ((Game.getCiv(Game.player.iCivID).getLegacyPerMonth() > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fLegacyPerMonth, 100), Images.legacy, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_POSITIVE));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("Legacy0"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Max") + ": ", "" + CFG.getPrecision2(GameValues.legacy.MAX_LEGACY_POINTS, 100), Images.legacy, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                if (CFG.isDesktop()) {
                    nData.add(new MenuElement_HoverElement_Type_Space());
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Shortcut") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("F5", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new TextTop_Diplomacy(Images.diplomacy, "", "", elemPosX, CFG.PADDING) {
            @Override
            public int getSFX() {
                return this.getIsActiveButton() ? SoundsManager.SOUND_CLICK_TOP : SoundsManager.DIPLOMACY1;
            }
            
            @Override
            public int getPosX() {
                return InGame.this.getMenuElement(4).getPosX() + InGame.this.getMenuElement(4).getWidth() + InGame.this.getStatsPadding();
            }
            
            @Override
            public void actionElement() {
                InGame.action6();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Diplomacy"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.diplomacy, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus_Perc(Game.lang.get("DiplomacyPoints") + ": ", "" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fDiplomacy, 100), Images.diplomacy, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD, Game.getCiv(Game.player.iCivID).fDiplomacy / Game.getCiv(Game.player.iCivID).fDiplomacyMax));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumDiplomacyPoints") + ": ", "" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fDiplomacyMax, 100), Images.diplomacy, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyChange") + ": ", ((Game.getCiv(Game.player.iCivID).getDiplomacyPerMonth() > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).getDiplomacyPerMonth(), 100), Images.diplomacy, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_POSITIVE));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BaseValue") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2((float)GameValues.diplomacy.DIPLOMACY_POINTS_MAX, 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_POSITIVE));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.diplomacy, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Provinces") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(GameValues.diplomacy.DIPLOMACY_POINTS_MAX_PER_PROVINCE * Game.getCiv(Game.player.iCivID).getNumOfProvinces(), 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_POSITIVE));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.diplomacy, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaxNumOfAlliances") + ": ", "" + DiplomacyManager.getMaxNumberOfAlliances(Game.player.iCivID), Images.alliance, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (CFG.isDesktop()) {
                    nData.add(new MenuElement_HoverElement_Type_Space());
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Shortcut") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("F6", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            public boolean getIsActiveButton() {
                return Game.menuManager.getVisibleInGame_Civ();
            }
        });
        menuElements.add(new TextTop_Nukes(Images.nuke, "", "", elemPosX, CFG.PADDING) {
            @Override
            public int getSFX() {
                return SoundsManager.SOUND_CLICK_TOP;
            }
            
            @Override
            public int getImageID() {
                return Images.nuke;
            }
            
            @Override
            public int getPosX() {
                return this.getVisible() ? (InGame.this.getMenuElement(5).getPosX() + InGame.this.getMenuElement(5).getWidth() + InGame.this.getStatsPadding()) : (InGame.this.getMenuElement(4).getPosX() + InGame.this.getMenuElement(4).getWidth() + InGame.this.getStatsPadding());
            }
            
            @Override
            public boolean getVisible() {
                return Game.getCiv(Game.player.iCivID).canBuildNuke;
            }
            
            @Override
            public void actionElement() {
                if (Game.menuManager.getVisibleInGame_Nukes()) {
                    Game.menuManager.setVisibleInGame_Nukes(false);
                }
                else {
                    Game.menuManager.rebuildInGame_Nukes();
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("NuclearWeapons"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.nuke, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AtomicBombs") + ": ", "" + Game.getCiv(Game.player.iCivID).getNukes(), Images.nuke, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (Game.getCiv(Game.player.iCivID).iNukesSize > 0) {
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AtomicBombUnderConstruction") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DaysX", Game.getCiv(Game.player.iCivID).nukesDaysLeft.get(0).daysLeft), CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, CFG.PADDING));
                    nData.add(new MenuElement_HoverElement_Type_Text("[" + CFG.getPrecision2((1.0f - Game.getCiv(Game.player.iCivID).nukesDaysLeft.get(0).daysLeft / (float)Game.getCiv(Game.player.iCivID).nukesDaysLeft.get(0).investTime) * 100.0f, 10) + "%]", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    if (Game.getCiv(Game.player.iCivID).iNukesSize > 1) {
                        int tDays = 0;
                        for (int i = 0; i < Game.getCiv(Game.player.iCivID).iNukesSize; ++i) {
                            tDays += Game.getCiv(Game.player.iCivID).nukesDaysLeft.get(i).daysLeft;
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("InQueue") + ": ", CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + (Game.getCiv(Game.player.iCivID).iNukesSize - 1), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text(" [" + Game.lang.get("DaysX", tDays) + "]", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, CFG.PADDING));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        this.lastStatElementID = menuElements.size() - 1;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), "999");
        InGame.rankPosXW = (int)Renderer.glyphLayout.width + ImageManager.getImage(Images.victoryPoints).getWidth() + CFG.PADDING * 4;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), "99");
        final int rankPosXW2 = (int)Renderer.glyphLayout.width + ImageManager.getImage(Images.victoryPoints).getWidth() + CFG.PADDING * 4;
        final int rankHeight = ImageManager.getImage(Images.topStats).getHeight() * 3 / 5;
        menuElements.add(new ButtonStatsRectIMG_Rank("" + Game.getCiv(Game.player.iCivID).iCivRankPosition, CivilizationRanking.getCivilizationRanking_IMG_STAR_CIVID(Game.player.iCivID), elemPosX, ImageManager.getImage(Images.topStats).getHeight() + CFG.PADDING / 2, InGame.rankPosXW, rankHeight, ImageManager.getImage(Images.rankGold).getWidth()) {
            @Override
            public String getTextToDraw() {
                if (this.iCurrent != Game.getCiv(Game.player.iCivID).iCivRankPosition) {
                    this.iCurrent = Game.getCiv(Game.player.iCivID).iCivRankPosition;
                    this.setText("" + ((Game.getCiv(Game.player.iCivID).iCivRankPosition < 1) ? "---" : Integer.valueOf(Game.getCiv(Game.player.iCivID).iCivRankPosition)));
                    if (Game.getCiv(Game.player.iCivID).iCivRankPosition < 1) {
                        this.imageID = Images.rankBlack;
                    }
                    else {
                        this.imageID = CivilizationRanking.getCivilizationRanking_IMG_STAR_CIVID(Game.player.iCivID);
                    }
                }
                return super.getTextToDraw();
            }
            
            @Override
            public int getSFX() {
                return SoundsManager.SOUND_CLICK_TOP;
            }
            
            @Override
            public void actionElement() {
                InGame.actionRanking();
            }
            
            @Override
            public void actionElementPPM() {
                if (Game.player.iCivID > 0 && Game.getCiv(Game.player.iCivID).getNumOfProvinces() > 0) {
                    if (Game.menuManager.getVisibleInGame_Civ()) {
                        Game.menuManager.setVisibleInGame_Civ(false);
                    }
                    else {
                        InGame_Civ_List.civsList.clear();
                        InGame_Civ_List.civsListTitle = Game.lang.get("NeighbouringCivilizations");
                        InGame_Civ_List.civsListTitle2 = Game.getCiv(Game.player.iCivID).getCivName();
                        InGame_Civ_List.civsList.add(Game.player.iCivID);
                        for (int a = 0; a < Game.getCiv(Game.player.iCivID).civNeighbors.civsSize; ++a) {
                            if (Game.getCiv(Game.player.iCivID).civNeighbors.civs.get(a).byLand) {
                                InGame_Civ_List.civsList.add(Game.getCiv(Game.player.iCivID).civNeighbors.civs.get(a).civID);
                            }
                        }
                        Game.menuManager.rebuildInGame_Civ_List();
                    }
                }
            }
            
            @Override
            public void buildElementHover() {
                this.menuElementHover = CivilizationRanking.getHover_CivilizationRanking(Game.player.iCivID, true, true);
            }
            
            @Override
            protected Color getColor(final boolean isActive) {
                return Colors.getColorTopStats(isActive, this.getIsHovered());
            }
        });
        menuElements.add(new ButtonStatsRectIMG_CurrentSituation("0", 0, elemPosX + menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING, ImageManager.getImage(Images.topStats).getHeight() + CFG.PADDING / 2, rankPosXW2, rankHeight, ImageManager.getImage(Images.rankGold).getWidth()) {
            @Override
            public int getSFX() {
                return SoundsManager.SOUND_CLICK_TOP;
            }
            
            @Override
            public void actionElement() {
                InGame.actionCurrent();
            }
            
            @Override
            protected Color getColor(final boolean isActive) {
                return Colors.getColorTopStats(isActive, this.getIsHovered());
            }
        });
        this.lastStatElementID2 = menuElements.size() - 1;
        InGame.rankPosXW = menuElements.get(menuElements.size() - 1).getPosX() + menuElements.get(menuElements.size() - 1).getWidth() + InGame.topStatsPadding;
        menuElements.add(new ButtonTopSpeed(Images.plus, 0, InGame.topStatsHeight) {
            @Override
            public int getSFX() {
                return SoundsManager.SOUND_CLICK_TOP;
            }
            
            @Override
            public void actionElement() {
                Game.gameThread.updateSpeedPlus();
                Game.menuManager.TOAST_TIME = 0L;
            }
            
            @Override
            public int getPosX() {
                return InGame.this.getMenuElement(InGame.this.dateElementID).getPosX() - this.getWidth();
            }
        });
        this.plusElementID = menuElements.size() - 1;
        menuElements.add(new ButtonTopDate(Game_Calendar.getCurrentDate(), 0, InGame.topStatsHeight) {
            @Override
            public int getSFX() {
                return Game.gameThread.play ? SoundsManager.SOUND_CLICK_TOP : SoundsManager.PLAY;
            }
            
            @Override
            public void actionElement() {
                Game.gameThread.play = !Game.gameThread.play;
            }
            
            @Override
            public int getPosX() {
                return CFG.GAME_WIDTH - InGame.getDatePadding() - this.getWidth();
            }
        });
        this.dateElementID = menuElements.size() - 1;
        menuElements.add(new ButtonTopSpeed(Images.minus, 0, InGame.topStatsHeight) {
            @Override
            public int getSFX() {
                return SoundsManager.SOUND_CLICK_TOP;
            }
            
            @Override
            public void actionElement() {
                Game.gameThread.updateSpeedMinus();
                Game.menuManager.TOAST_TIME = 0L;
            }
            
            @Override
            public int getPosX() {
                return InGame.this.getMenuElement(InGame.this.plusElementID).getPosX() - this.getWidth();
            }
        });
        this.minusElementID = menuElements.size() - 1;
        menuElements.add(new ButtonTopOutliner(0, InGame.topStatsHeight) {
            @Override
            public int getSFX() {
                return SoundsManager.SOUND_CLICK_TOP;
            }
            
            @Override
            public void actionElement() {
                if (!CFG.isDesktop()) {
                    Touch.selectArmiesMode = !Touch.selectArmiesMode;
                }
                else {
                    InGame_Right.outlinerInView = !InGame_Right.outlinerInView;
                    Game.menuManager.rebuildInGame_Right();
                }
            }
            
            @Override
            public int getPosX() {
                return InGame.this.getMenuElement(InGame.this.minusElementID).getPosX() - InGame.topRightPadding - this.getWidth();
            }
        });
        this.outlinerElementID = menuElements.size() - 1;
        menuElements.add(new Minimap(0, 0) {
            @Override
            public int getPosX() {
                return CFG.GAME_WIDTH - this.getWidth();
            }
            
            @Override
            public int getPosY() {
                return CFG.GAME_HEIGHT - this.getHeight() + InGame.iMinimapPosY;
            }
            
            @Override
            public boolean getVisible() {
                return super.getVisible() && Game.mapBG.getHideMenuZoomOut();
            }
        });
        this.minimapElementID = menuElements.size() - 1;
        menuElements.add(new Button32Padd(Images.arrowUpDown) {
            @Override
            public int getSFX() {
                return SoundsManager.SOUND_CLICK_TOP;
            }
            
            @Override
            public void actionElement() {
                if (!InGame.inAnimation) {
                    InGame.this.minimapTime = CFG.currentTimeMillis;
                }
                InGame.inAnimation = true;
                InGame.hideAnimation = !InGame.hideAnimation;
                this.menuElementHover = null;
                Game.mapBG.requestToDisposeMinimap = true;
            }
            
            @Override
            public int getPosX() {
                return CFG.GAME_WIDTH - this.getWidth() - CFG.PADDING;
            }
            
            @Override
            public int getPosY() {
                return InGame.this.getMenuElement(InGame.this.minimapElementID).getPosY() - this.getHeight() - CFG.PADDING;
            }
            
            @Override
            public boolean getFlipY() {
                return InGame.iMinimapPosY == 0;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get((InGame.iMinimapPosY == 0) ? "HideMinimap" : "ShowMinimap"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
            
            @Override
            public boolean getVisible() {
                return super.getVisible() && Game.mapBG.getHideMenuZoomOut();
            }
            
            @Override
            protected Color getColor(final boolean isActive) {
                if (isActive) {
                    return Colors.COLOR_TOP_STATS_ACTIVE;
                }
                if (this.getIsHovered()) {
                    return Colors.COLOR_TOP_STATS_HOVER;
                }
                return InGame$15.buttonColor;
            }
            
            @Override
            public int getWidth() {
                return ImageManager.getImage(this.iconImageID).getWidth() + CFG.PADDING * 2;
            }
        });
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements, true);
        InGame.iMinimapPosY = initMinimapPosY;
        if (!CFG.isDesktop() && GameValues.value.MOBILE_HIDE_MINIMAP) {
            InGame.iMinimapPosY = this.getMenuElement(this.minimapElementID).getHeight();
            InGame.inAnimation = false;
            InGame.hideAnimation = true;
        }
    }
    
    public final int getRightButtonsPadding() {
        return 0;
    }
    
    public final int getStatsPadding() {
        return CFG.PADDING * 2;
    }
    
    public static final int getDatePadding() {
        return CFG.PADDING;
    }
    
    public final void minimapAnimation() {
        if (InGame.inAnimation) {
            if (InGame.hideAnimation) {
                if (InGame.iMinimapPosY < this.getMenuElement(this.minimapElementID).getHeight()) {
                    InGame.iMinimapPosY = (int)(this.getMenuElement(this.minimapElementID).getHeight() * ((CFG.currentTimeMillis - this.minimapTime) / (float)this.minimapAnimationTime));
                    if (InGame.iMinimapPosY > this.getMenuElement(this.minimapElementID).getHeight()) {
                        InGame.iMinimapPosY = this.getMenuElement(this.minimapElementID).getHeight();
                        InGame.inAnimation = false;
                    }
                }
                else {
                    InGame.iMinimapPosY = this.getMenuElement(this.minimapElementID).getHeight();
                    InGame.inAnimation = false;
                }
            }
            else if (InGame.iMinimapPosY > 0) {
                InGame.iMinimapPosY = (int)(this.getMenuElement(this.minimapElementID).getHeight() - this.getMenuElement(this.minimapElementID).getHeight() * ((CFG.currentTimeMillis - this.minimapTime) / (float)this.minimapAnimationTime));
                if (InGame.iMinimapPosY < 0) {
                    InGame.iMinimapPosY = 0;
                    InGame.inAnimation = false;
                }
            }
            else {
                InGame.iMinimapPosY = 0;
                InGame.inAnimation = false;
            }
        }
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        this.minimapAnimation();
        final Image topStats = ImageManager.getImage(Images.topStats);
        oSB.setColor(Color.WHITE);
        if (!Game.settingsManager.enableHideSideMenu || Game.menuManager.getVisibleInGame_Court()) {
            ImageManager.getImage(Images.leftSideBar).draw2(oSB, iTranslateX + Game.settingsManager.IN_GAME_LEFT_PADDING_EXTRA, iTranslateY, ImageManager.getImage(Images.leftSideBar).getWidth(), InGame_CourtOptions2.HEIGHT, false, true);
        }
        topStats.draw2(oSB, ImageManager.getImage(Images.flagBG).getWidth() / 2 + iTranslateX, InGame.topBar2PosY + this.getMenuElement(this.lastStatElementID2).getHeight() + CFG.PADDING + iTranslateY, this.getMenuElement(this.lastStatElementID2).getPosX() + this.getMenuElement(this.lastStatElementID2).getWidth() + InGame.topStatsPadding - ImageManager.getImage(Images.flagBG).getWidth() / 2, topStats.getHeight(), true, false);
        topStats.draw2(oSB, ImageManager.getImage(Images.flagBG).getWidth() / 2 + iTranslateX, iTranslateY, (this.getMenuElement(this.lastStatElementID).getVisible() ? (this.getMenuElement(this.lastStatElementID).getPosX() + this.getMenuElement(this.lastStatElementID).getWidth()) : (this.getMenuElement(this.lastStatElementID - 1).getPosX() + this.getMenuElement(this.lastStatElementID - 1).getWidth())) + InGame.topStatsPadding - ImageManager.getImage(Images.flagBG).getWidth() / 2, topStats.getHeight(), true, false);
        topStats.draw2(oSB, this.getMenuElement(this.outlinerElementID).getPosX() - InGame.topRightPadding + iTranslateX, iTranslateY, CFG.GAME_WIDTH - (this.getMenuElement(this.outlinerElementID).getPosX() - InGame.topRightPadding), topStats.getHeight());
        final int rightBG_X = this.getMenuElement(this.minusElementID).getPosX() + CFG.PADDING - getDatePadding() - InGame.topRightPadding;
        ImageManager.getImage(Images.topStats2).draw2(oSB, rightBG_X + iTranslateX, iTranslateY, CFG.GAME_WIDTH - rightBG_X, topStats.getHeight());
        if (Game.gameThread.play) {
            oSB.setColor(new Color(Colors.COLOR_TEXT_MODIFIER_NEGATIVE.r, Colors.COLOR_TEXT_MODIFIER_NEGATIVE.g, Colors.COLOR_TEXT_MODIFIER_NEGATIVE.b, 0.175f));
        }
        else {
            oSB.setColor(new Color(Colors.COLOR_TEXT_MODIFIER_NEGATIVE.r, Colors.COLOR_TEXT_MODIFIER_NEGATIVE.g, Colors.COLOR_TEXT_MODIFIER_NEGATIVE.b, 0.05f));
        }
        ImageManager.getImage(Images.gradientXYVertical).draw(oSB, rightBG_X + iTranslateX, iTranslateY, CFG.GAME_WIDTH - rightBG_X, topStats.getHeight(), true, false);
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, rightBG_X + iTranslateX, iTranslateY, CFG.GAME_WIDTH - rightBG_X, topStats.getHeight(), true, false);
        oSB.setColor(Color.WHITE);
        if (this.getMenuElement(this.minimapElementID).getVisible()) {
            final MenuElement minimap = this.getMenuElement(this.minimapElementID);
            Renderer.drawBoxCorner(oSB, minimap.getPosX() - Images.boxTitleBORDERWIDTH + iTranslateX, Game.menuManager.getInGame_MapModesPosY() + iTranslateY, CFG.GAME_WIDTH - minimap.getPosX() + Images.boxTitleBORDERWIDTH, CFG.GAME_HEIGHT - Game.menuManager.getInGame_MapModesPosY() + Images.boxTitleBORDERWIDTH);
            ImageManager.getImage(Images.boxBIG_Red).draw2(oSB, minimap.getPosX() - Images.boxTitleBORDERWIDTH + iTranslateX, Game.menuManager.getInGame_MapModesPosY() + iTranslateY, minimap.getWidth() + Images.boxTitleBORDERWIDTH, CFG.GAME_HEIGHT - Game.menuManager.getInGame_MapModesPosY() + Images.boxTitleBORDERWIDTH);
            oSB.setColor(new Color(Colors.COLOR_TITLE.r, Colors.COLOR_TITLE.g, Colors.COLOR_TITLE.b, 0.15f));
            ImageManager.getImage(Images.gradientVertical).draw(oSB, minimap.getPosX() + iTranslateX, minimap.getPosY() - CFG.PADDING * 2 + iTranslateY, minimap.getWidth(), CFG.PADDING * 2, false, true);
            Images.gradientXY.draw(oSB, minimap.getPosX() + iTranslateX, minimap.getPosY() - CFG.PADDING * 4 + iTranslateY, minimap.getWidth(), CFG.PADDING * 4);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.2f));
            ImageManager.getImage(Images.gradientVertical).draw(oSB, minimap.getPosX() + iTranslateX, minimap.getPosY() - CFG.PADDING + iTranslateY, minimap.getWidth(), CFG.PADDING, false, true);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
            Images.gradientFull.draw(oSB, minimap.getPosX() + iTranslateX, minimap.getPosY() - 2 + iTranslateY, minimap.getWidth(), 1);
            oSB.setColor(Color.WHITE);
            ImageManager.getImage(Images.boxBIG).draw2(oSB, minimap.getPosX() - Images.boxTitleBORDERWIDTH + iTranslateX, minimap.getPosY() + iTranslateY, minimap.getWidth() + Images.boxTitleBORDERWIDTH, CFG.GAME_HEIGHT - minimap.getPosY(), 0, Images.boxTitleBORDERWIDTH * 2);
        }
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
        InGame_OngoingBattles.draw(oSB, iTranslateX, iTranslateY);
        InGame.drawOver.draw(oSB, iTranslateX, iTranslateY);
        Game.soundsManager.playRandomSounds();
    }
    
    @Override
    public void onHovered() {
        super.onHovered();
        Game.menuManager.setOrderOfMenu_InGame();
    }
    
    public static void action1() {
        CFG.brushTool = false;
        if (Game.menuManager.getColorPicker().getVisible()) {
            Game.menuManager.getColorPicker().setVisible(false, null);
        }
        if (Game.menuManager.getVisibleInGame_Court()) {
            Game.menuManager.setVisibleInGame_Court(false);
        }
        else {
            InGame_CourtOptions.iActiveID = InGame_CourtOptions2.idCourt;
            InGame_Court.modeID = 0;
            InGame_Court.iActiveCivID = Game.player.iCivID;
            Game.menuManager.rebuildInGame_Court();
            Game.menuManager.setVisibleInGame_Court(true);
            Game.setRegroupArmyMode(false);
            if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 35) {
                Game.menuManager.setVisibleInGame_PopUp(false);
            }
        }
    }
    
    public static void action2() {
        if (Game.menuManager.getColorPicker().getVisible()) {
            Game.menuManager.getColorPicker().setVisible(false, null);
        }
        if (Game.menuManager.getVisibleInGame_Budget()) {
            Game.menuManager.setVisibleInGame_Budget(false);
        }
        else {
            Game.menuManager.rebuildInGame_Budget();
            Game.menuManager.setVisibleInGame_Budget(true);
        }
    }
    
    public static void action3() {
        if (Game.menuManager.getColorPicker().getVisible()) {
            Game.menuManager.getColorPicker().setVisible(false, null);
        }
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
    
    public static void action4() {
        if (Game.menuManager.getColorPicker().getVisible()) {
            Game.menuManager.getColorPicker().setVisible(false, null);
        }
        if (Game.menuManager.getVisibleInGame_TechnologyTree()) {
            Game.menuManager.setVisibleInGame_TechnologyTree(false);
        }
        else if (Game.menuManager.getVisibleInGame_TechnologyChoose() && InGame_TechnologyChoose.IN_TECHNOLOGY_CHOOSE) {
            Game.menuManager.setVisibleInGame_TechnologyChoose(false);
        }
        else {
            Game.menuManager.rebuildInGame_TechnologyChoose(false, true);
        }
    }
    
    public static void action5() {
        if (Game.menuManager.getColorPicker().getVisible()) {
            Game.menuManager.getColorPicker().setVisible(false, null);
        }
        if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_RECRUIT_ARMY) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        }
        InGame_Legacies.iActiveCivID = Game.player.iCivID;
        MainMenu.bgTIME = System.currentTimeMillis();
        MainMenu.bgTIME_CHANGE = System.currentTimeMillis();
        MainMenu.bgAlpha = 0.0f;
        Game.menuManager.setViewIDWithoutAnimation(View.IN_GAME_LEGACIES);
        Game.menuManager.setOrderOfMenu_InGameLegacies();
    }
    
    public static void action6() {
        if (Game.menuManager.getColorPicker().getVisible()) {
            Game.menuManager.getColorPicker().setVisible(false, null);
        }
        if (Game.menuManager.getVisibleInGame_Civ()) {
            Game.menuManager.setVisibleInGame_Civ(false);
        }
        else {
            Game.menuManager.rebuildInGame_Civ();
        }
    }
    
    public static void actionRanking() {
        if (Game.menuManager.getVisibleInGame_CurrentSituation() && !MenuManager.currentSituationMode) {
            Game.menuManager.setVisibleInGame_CurrentSituation(false);
        }
        else {
            InGame_Civ_Compare.civLeft_Rank = -1;
            InGame_Civ_Compare.civRight_Rank = -1;
            InGame_Ranking.sSearch = "";
            Game.menuManager.rebuildInGame_CurrentSituation_Ranking();
        }
    }
    
    public static void actionCurrent() {
        if ((Game.menuManager.getVisibleInGame_CurrentSituation() && MenuManager.currentSituationMode) || Game.player.currSituation.currentSituationNum == 0) {
            Game.menuManager.setVisibleInGame_CurrentSituation(false);
        }
        else {
            Game.menuManager.rebuildInGame_CurrentSituation();
        }
    }
    
    static {
        InGame.drawOver = new InGameDrawOver();
        InGame.ONLY_MAP_MODE = false;
        InGame.rankPosXW = 0;
        InGame.topStatsPadding = 0;
        InGame.topRightPadding = 0;
        InGame.topStatsHeight = 0;
        InGame.topBar2PosY = 0;
        InGame.leftSideBarPadding = 0;
        InGame.leftSideBarInnerWidth = 0;
        InGame.outlinerExtraX = 0;
        InGame.outlinerExtraClassic = 0;
        InGame.outlinerExtraUQ = 0;
        InGame.iMinimapPosY = 0;
        InGame.inAnimation = false;
        InGame.hideAnimation = false;
    }
}
