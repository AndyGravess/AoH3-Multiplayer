// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Battle;

import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.button.ButtonGame_Style;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_Special;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.map.RulersManager;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Diplomacy_Flip;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Diplomacy;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_SpecialWarscore;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.button.ButtonRuler_Diplomacy;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon_Diplomacy;
import aoh.kingdoms.history.menu_element.textStatic.Text_Static_ID;
import aoh.kingdoms.history.menu_element.button.ButtonFlag_Diplomacy;
import aoh.kingdoms.history.map.diplomacy.DiplomacyManager;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.map.battles.BattleReport;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Menu;

public class InGame_BattleReport extends Menu
{
    public static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static Color colorBar;
    
    public InGame_BattleReport(final int reportID) {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        int paddingLeft = CFG.PADDING + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title600).getHeight();
        final int menuWidth = ImageManager.getImage(Images.insideTop600).getWidth();
        final int menuX = CFG.PADDING * 2;
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        final int buttonYPadding = CFG.PADDING * 2;
        int buttonY = CFG.PADDING;
        final int buttonX = paddingLeft;
        try {
            final int iCivIDLeft = Game.player.lBattleReports.get(reportID).civReportLeft.iCivID;
            final int iCivID = Game.player.lBattleReports.get(reportID).civReportRight.iCivID;
            final int maxWidth = ImageManager.getImage(Images.battleBig).getWidth() + CFG.PADDING * 4;
            final int tempTitlePaddingY = CFG.PADDING;
            final int tempTitleH = ImageManager.getImage(Images.flagDiplomacyOver).getHeight() + tempTitlePaddingY * 2;
            final int tempTextW = menuWidth / 2 - paddingLeft - CFG.PADDING * 2 - maxWidth / 2 - ImageManager.getImage(Images.flagDiplomacyOver).getWidth();
            if (Game.player.lBattleReports.get(reportID).playerWon) {
                InGame_BattleReport.colorBar = DiplomacyManager.COLOR_GREEN;
            }
            else {
                InGame_BattleReport.colorBar = DiplomacyManager.COLOR_RED;
            }
            menuElements.add(new ButtonFlag_Diplomacy(iCivIDLeft, menuWidth / 2 - maxWidth / 2 - CFG.PADDING * 2 - ImageManager.getImage(Images.flagDiplomacyOver).getWidth(), buttonY + tempTitlePaddingY, true));
            menuElements.add(new ButtonFlag_Diplomacy(iCivID, menuWidth / 2 + maxWidth / 2 + CFG.PADDING * 2, buttonY + tempTitlePaddingY, true));
            menuElements.add(new Text_Static_ID(iCivID, Game.getCiv(iCivID).getCivName(), CFG.FONT_REGULAR, -1, menuWidth / 2 + maxWidth / 2 + CFG.PADDING * 2 + ImageManager.getImage(Images.flagDiplomacyOver).getWidth(), buttonY, tempTextW, tempTitleH));
            menuElements.add(new Text_Static_ID(iCivIDLeft, Game.getCiv(iCivIDLeft).getCivName(), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, tempTextW, tempTitleH));
            menuElements.add(new TextIcon_Diplomacy(Images.battleBig, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tempTitleH, maxWidth) {
                @Override
                public Color getColorBar() {
                    return InGame_BattleReport.colorBar;
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            final int statsX = paddingLeft + ButtonRuler_Diplomacy.getButtonWidth() + CFG.PADDING;
            final int statsW = menuWidth / 2 - statsX - CFG.PADDING / 2;
            final int statsH = (ButtonRuler_Diplomacy.getButtonHeight() - CFG.PADDING * 3) / 4;
            final int maxIconW = ImageManager.getImage(Game_Calendar.IMG_MANPOWER).getWidth() + CFG.PADDING * 2;
            menuElements.add(new Text_StaticBG_ID_SpecialWarscore("" + Game.lang.get("WarScore") + ": ", CFG.getPrecision2(Game.player.lBattleReports.get(reportID).fWarScore, 100) + "%", CFG.FONT_REGULAR, -1, statsX, buttonY, statsW * 2 + CFG.PADDING, statsH, 0));
            menuElements.add(new ButtonStatsRectIMG_Diplomacy("" + CFG.getShortNumber(Game.player.lBattleReports.get(reportID).civReportLeft.iSoldiers), Game_Calendar.IMG_MANPOWER, statsX, buttonY + CFG.PADDING + statsH, statsW, statsH, maxIconW, Game.player.lBattleReports.get(reportID).civReportLeft.iSoldiers) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Soldiers") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(CFG.getNumberWithSpaces("" + this.id), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            menuElements.add(new ButtonStatsRectIMG_Diplomacy("" + CFG.getShortNumber(Game.player.lBattleReports.get(reportID).civReportLeft.iCasualties), Images.skull, statsX, buttonY + (CFG.PADDING + statsH) * 2, statsW, statsH, maxIconW, Game.player.lBattleReports.get(reportID).civReportLeft.iCasualties) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Casualties") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(CFG.getNumberWithSpaces("" + this.id), CFG.FONT_BOLD, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.skull, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            menuElements.add(new ButtonStatsRectIMG_Diplomacy("" + CFG.getShortNumber(Game.player.lBattleReports.get(reportID).civReportLeft.iRetreated), Images.retreat, statsX, buttonY + (CFG.PADDING + statsH) * 3, statsW, statsH, maxIconW, Game.player.lBattleReports.get(reportID).civReportLeft.iRetreated) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Retreated") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(CFG.getNumberWithSpaces("" + this.id), CFG.FONT_BOLD, Colors.HOVER_RIGHT));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.retreat, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            menuElements.add(new ButtonStatsRectIMG_Diplomacy_Flip("" + CFG.getShortNumber(Game.player.lBattleReports.get(reportID).civReportRight.iSoldiers), Game_Calendar.IMG_MANPOWER, statsX + CFG.PADDING + statsW, buttonY + CFG.PADDING + statsH, statsW, statsH, maxIconW, Game.player.lBattleReports.get(reportID).civReportRight.iSoldiers) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Soldiers") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(CFG.getNumberWithSpaces("" + this.id), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            menuElements.add(new ButtonStatsRectIMG_Diplomacy_Flip("" + CFG.getShortNumber(Game.player.lBattleReports.get(reportID).civReportRight.iCasualties), Images.skull, statsX + CFG.PADDING + statsW, buttonY + (CFG.PADDING + statsH) * 2, statsW, statsH, maxIconW, Game.player.lBattleReports.get(reportID).civReportRight.iCasualties) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Casualties") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(CFG.getNumberWithSpaces("" + this.id), CFG.FONT_BOLD, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.skull, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            menuElements.add(new ButtonStatsRectIMG_Diplomacy_Flip("" + CFG.getShortNumber(Game.player.lBattleReports.get(reportID).civReportRight.iRetreated), Images.retreat, statsX + CFG.PADDING + statsW, buttonY + (CFG.PADDING + statsH) * 3, statsW, statsH, maxIconW, Game.player.lBattleReports.get(reportID).civReportRight.iRetreated) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Retreated") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(CFG.getNumberWithSpaces("" + this.id), CFG.FONT_BOLD, Colors.HOVER_RIGHT));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.retreat, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            RulersManager.loadRulerIMG_DiplomacyLeft(iCivIDLeft);
            RulersManager.loadRulerIMG_DiplomacyRight(iCivID);
            menuElements.add(new ButtonRuler_Diplomacy(iCivIDLeft, buttonX, buttonY));
            menuElements.add(new ButtonRuler_Diplomacy(iCivID, menuWidth - paddingLeft - ButtonRuler_Diplomacy.getButtonWidth(), buttonY) {
                @Override
                public Image getRulerImage() {
                    return RulersManager.rulerIMG_DiplomacyRight;
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            paddingLeft += CFG.PADDING;
            menuElements.add(new Text_StaticBG_ID_Special(Game.lang.get("Victorious"), CFG.FONT_BOLD, -1, Game.player.lBattleReports.get(reportID).leftSideWon ? paddingLeft : (paddingLeft + CFG.PADDING + (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2), buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, CFG.BUTTON_HEIGHT2, 0) {
                @Override
                protected Color getColor(final boolean isActive) {
                    return Colors.getColorPositive(isActive, this.getIsHovered());
                }
            });
            menuElements.add(new ButtonGame_Style(Game.lang.get("Close"), CFG.FONT_REGULAR, -1, Game.player.lBattleReports.get(reportID).leftSideWon ? (paddingLeft + CFG.PADDING + (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2) : paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, true) {
                @Override
                public void actionElement() {
                    Game.menuManager.setVisibleInGame_PopUp(false);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
            final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
            menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, buttonY)));
            this.initMenu(new MenuTitleIMG(Game.lang.get("BattleOf", Game.getProvince(Game.player.lBattleReports.get(reportID).iProvinceID).getProvinceName()), true, false, Images.title600) {
                @Override
                public long getTime() {
                    return InGame_BattleReport.lTime;
                }
            }, CFG.GAME_WIDTH / 2 - menuWidth / 2, menuY, menuWidth, menuHeight, menuElements, false, true);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        DiplomacyManager.updateInAnimation();
        if (InGame_BattleReport.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateY = iTranslateY - CFG.BUTTON_HEIGHT + (int)(CFG.BUTTON_HEIGHT * ((CFG.currentTimeMillis - InGame_BattleReport.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop600, Images.insideBot600);
        ImageManager.getImage(Images.civInfoOver).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.civInfoOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_BattleReport.lTime = CFG.currentTimeMillis;
        DiplomacyManager.updateAnimationTime();
    }
    
    static {
        InGame_BattleReport.lTime = 0L;
        InGame_BattleReport.colorBar = Color.WHITE;
    }
}
