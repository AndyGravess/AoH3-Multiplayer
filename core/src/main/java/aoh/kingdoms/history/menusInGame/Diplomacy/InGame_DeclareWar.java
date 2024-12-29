// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Diplomacy;

import aoh.kingdoms.history.mainGame.Steam.SteamAchievementsManager;
import aoh.kingdoms.history.mainGame.SoundsManager;
import aoh.kingdoms.history.menusInGame.Info.InGame_Info;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.map.civilization.CivilizationRanking;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv_GreenRed;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2Center;
import aoh.kingdoms.history.map.diplomacy.Diplomacy;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.button.ButtonGame_ImageSparks;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ;
import aoh.kingdoms.history.menu_element.button.ButtonGame;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoh.kingdoms.history.menu_element.textStatic.TextBonus;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Bonuses;
import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title;
import aoh.kingdoms.history.mainGame.GameValues;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.textStatic.Text_Desc;
import aoh.kingdoms.history.map.diplomacy.DiplomacyManager;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.map.RulersManager;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Diplomacy_Flip;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Diplomacy;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.button.ButtonRuler_Diplomacy;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon_Diplomacy;
import aoh.kingdoms.history.menu_element.textStatic.Text_Static_ID;
import aoh.kingdoms.history.menu_element.button.ButtonFlag_Diplomacy;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import java.util.List;
import aoh.kingdoms.history.menu.Menu;

public class InGame_DeclareWar extends Menu
{
    public static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static int iCivID;
    public static List<Integer> callToWar;
    
    public InGame_DeclareWar(final int nCivID) {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        InGame_DeclareWar.callToWar.clear();
        int paddingLeft = CFG.PADDING + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title600).getHeight();
        final int menuWidth = ImageManager.getImage(Images.insideTop600).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        final int buttonYPadding = CFG.PADDING * 2;
        int buttonY = CFG.PADDING;
        final int buttonX = paddingLeft;
        InGame_DeclareWar.iCivID = nCivID;
        final int maxWidth = ImageManager.getImage(Images.warBig).getWidth() + CFG.PADDING * 4;
        final int tempTitlePaddingY = CFG.PADDING;
        final int tempTitleH = ImageManager.getImage(Images.flagDiplomacyOver).getHeight() + tempTitlePaddingY * 2;
        final int tempTextW = menuWidth / 2 - paddingLeft - CFG.PADDING * 2 - maxWidth / 2 - ImageManager.getImage(Images.flagDiplomacyOver).getWidth();
        menuElements.add(new ButtonFlag_Diplomacy(Game.player.iCivID, menuWidth / 2 - maxWidth / 2 - CFG.PADDING * 2 - ImageManager.getImage(Images.flagDiplomacyOver).getWidth(), buttonY + tempTitlePaddingY, true));
        menuElements.add(new ButtonFlag_Diplomacy(InGame_DeclareWar.iCivID, menuWidth / 2 + maxWidth / 2 + CFG.PADDING * 2, buttonY + tempTitlePaddingY, true));
        menuElements.add(new Text_Static_ID(InGame_DeclareWar.iCivID, Game.getCiv(InGame_DeclareWar.iCivID).getCivName(), CFG.FONT_REGULAR, -1, menuWidth / 2 + maxWidth / 2 + CFG.PADDING * 2 + ImageManager.getImage(Images.flagDiplomacyOver).getWidth(), buttonY, tempTextW, tempTitleH));
        menuElements.add(new Text_Static_ID(Game.player.iCivID, Game.getCiv(Game.player.iCivID).getCivName(), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, tempTextW, tempTitleH));
        menuElements.add(new TextIcon_Diplomacy(Images.warBig, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tempTitleH, maxWidth));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final int statsX = paddingLeft + ButtonRuler_Diplomacy.getButtonWidth() + CFG.PADDING;
        final int statsW = menuWidth / 2 - statsX - CFG.PADDING / 2;
        final int statsH = (ButtonRuler_Diplomacy.getButtonHeight() - CFG.PADDING * 2) / 3;
        final int maxIconW = ImageManager.getImage(Game_Calendar.IMG_MANPOWER).getWidth() + CFG.PADDING * 2;
        menuElements.add(new ButtonStatsRectIMG_Diplomacy("" + CFG.getShortNumber(Game.getCiv(Game.player.iCivID).iRegimentsLimit), Images.regimentsLimit, statsX, buttonY, statsW, statsH, maxIconW, 0) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("RegimentsLimit") + ": ", CFG.FONT_BOLD));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + CFG.getShortNumber(Game.getCiv(Game.player.iCivID).iRegimentsLimit), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.regimentsLimit, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Diplomacy("" + Game.getCiv(Game.player.iCivID).getResearchedTechnologies(), Game_Calendar.IMG_TECHNOLOGY, statsX, buttonY + CFG.PADDING + statsH, statsW, statsH, maxIconW, 0) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("UnlockedTechnologies") + ": ", CFG.FONT_BOLD));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.getCiv(Game.player.iCivID).getResearchedTechnologies(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Diplomacy("" + Game.getCiv(Game.player.iCivID).getNumOfProvinces(), Images.provinces, statsX, buttonY + (CFG.PADDING + statsH) * 2, statsW, statsH, maxIconW, 0) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Provinces") + ": ", CFG.FONT_BOLD));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.getCiv(Game.player.iCivID).getNumOfProvinces(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.provinces, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Diplomacy_Flip("" + CFG.getShortNumber(Game.getCiv(InGame_DeclareWar.iCivID).iRegimentsLimit), Images.regimentsLimit, statsX + CFG.PADDING + statsW, buttonY, statsW, statsH, maxIconW, 0) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("RegimentsLimit") + ": ", CFG.FONT_BOLD));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + CFG.getShortNumber(Game.getCiv(InGame_DeclareWar.iCivID).iRegimentsLimit), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.regimentsLimit, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Diplomacy_Flip("" + Game.getCiv(InGame_DeclareWar.iCivID).getResearchedTechnologies(), Game_Calendar.IMG_TECHNOLOGY, statsX + CFG.PADDING + statsW, buttonY + CFG.PADDING + statsH, statsW, statsH, maxIconW, 0) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("UnlockedTechnologies") + ": ", CFG.FONT_BOLD));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.getCiv(InGame_DeclareWar.iCivID).getResearchedTechnologies(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Diplomacy_Flip("" + Game.getCiv(InGame_DeclareWar.iCivID).getNumOfProvinces(), Images.provinces, statsX + CFG.PADDING + statsW, buttonY + (CFG.PADDING + statsH) * 2, statsW, statsH, maxIconW, 0) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Provinces") + ": ", CFG.FONT_BOLD));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.getCiv(InGame_DeclareWar.iCivID).getNumOfProvinces(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.provinces, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        RulersManager.loadRulerIMG_DiplomacyLeft(Game.player.iCivID);
        RulersManager.loadRulerIMG_DiplomacyRight(InGame_DeclareWar.iCivID);
        menuElements.add(new ButtonRuler_Diplomacy(Game.player.iCivID, buttonX, buttonY));
        menuElements.add(new ButtonRuler_Diplomacy(InGame_DeclareWar.iCivID, menuWidth - paddingLeft - ButtonRuler_Diplomacy.getButtonWidth(), buttonY) {
            @Override
            public Image getRulerImage() {
                return RulersManager.rulerIMG_DiplomacyRight;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Text_Desc(DiplomacyManager.getWarMessage(), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
            @Override
            protected Color getColor(final boolean isActive) {
                return Colors.getColorButtonHover2(isActive, this.getIsHovered());
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        if (Game.getCiv(InGame_DeclareWar.iCivID).diplomacy.getRelation(Game.player.iCivID) > GameValues.war.RELATIONS_TO_DECLARE_WAR) {
            menuElements.add(new Text_Desc(Game.lang.get("ToDeclareWarTheRelationsBetweenCivilizationsMustBeBelowX", GameValues.war.RELATIONS_TO_DECLARE_WAR), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                @Override
                protected Color getColor(final boolean isActive) {
                    return Colors.getColorNegative(isActive, this.getIsHovered());
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(Game.player.iCivID, Game.getCiv(InGame_DeclareWar.iCivID).getCivName()));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Opinion") + ": ", ((Game.getCiv(InGame_DeclareWar.iCivID).diplomacy.getRelation(Game.player.iCivID) > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_DeclareWar.iCivID).diplomacy.getRelation(Game.player.iCivID), 10), Images.relations, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DamageRelations"), "", Images.relationsDown, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("SendAnInsult"), "", Images.insult, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
                
                @Override
                public void actionElement() {
                    if (Game.getCiv(InGame_DeclareWar.iCivID).getCapitalProvinceID() >= 0) {
                        Game.setActiveProvinceID(Game.getCiv(InGame_DeclareWar.iCivID).getCapitalProvinceID());
                        ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                        Game.menuManager.rebuildInGame_Civ();
                    }
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        paddingLeft += CFG.PADDING;
        final int iconWidth = (int)Math.ceil(ImageManager.getImage(Images.gold).getWidth() * 1.5f);
        if (Game.getCiv(Game.player.iCivID).fManpower / Game.getCiv(Game.player.iCivID).fManpowerMax < GameValues.diplomacy.DIPLOMACY_DECLARE_WAR_LOW_MANPOWER_INFO_IF_BELOW) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Manpower") + ": ", "" + CFG.getNumberWithSpaces("" + (int)Game.getCiv(Game.player.iCivID).fManpower) + " / " + CFG.getNumberWithSpaces("" + (int)Game.getCiv(Game.player.iCivID).fManpowerMax) + " [" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fManpower / Game.getCiv(Game.player.iCivID).fManpowerMax * 100.0, 10) + "%]", Game_Calendar.IMG_MANPOWER, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT3, iconWidth, CFG.FONT_REGULAR_SMALL, CFG.FONT_REGULAR_SMALL) {
                @Override
                public Color getColorBonus() {
                    return Colors.HOVER_NEGATIVE;
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(Game.player.iCivID, Game.lang.get("MaximumManpower") + ": " + CFG.getNumberWithSpaces("" + (int)Game.getCiv(Game.player.iCivID).fManpowerMax)));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(this.getText(), this.sText2, this.imageID, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Manpower") + ": 0% -> 100%: ", Game.lang.get("MonthsX", (int)GameValues.manpower.MANPOWER_FULL_RECOVERY_MONTHS), Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        menuElements.add(new TextBonus(Game.lang.get("Opinion") + ": ", ((Game.getCiv(InGame_DeclareWar.iCivID).diplomacy.getRelation(Game.player.iCivID) > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_DeclareWar.iCivID).diplomacy.getRelation(Game.player.iCivID), 10), Images.relations, paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, CFG.TEXT_HEIGHT + CFG.PADDING * 5, iconWidth) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(Game.player.iCivID, Game.getCiv(InGame_DeclareWar.iCivID).getCivName()));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Opinion") + ": ", ((Game.getCiv(InGame_DeclareWar.iCivID).diplomacy.getRelation(Game.player.iCivID) > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_DeclareWar.iCivID).diplomacy.getRelation(Game.player.iCivID), 10), Images.relations, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, DiplomacyManager.getOpinion_Color((int)Game.getCiv(InGame_DeclareWar.iCivID).diplomacy.getRelation(Game.player.iCivID))));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("ToDeclareWarTheRelationsBetweenCivilizationsMustBeBelowX", GameValues.war.RELATIONS_TO_DECLARE_WAR), CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            public void actionElement() {
                if (Game.getCiv(InGame_DeclareWar.iCivID).diplomacy.getRelation(Game.player.iCivID) > GameValues.war.RELATIONS_TO_DECLARE_WAR && Game.getCiv(InGame_DeclareWar.iCivID).getCapitalProvinceID() >= 0) {
                    Game.setActiveProvinceID(Game.getCiv(InGame_DeclareWar.iCivID).getCapitalProvinceID());
                    ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                    Game.menuManager.rebuildInGame_Civ();
                }
            }
        });
        menuElements.add(new TextBonus(Game.lang.get("Cost") + ": ", "" + CFG.getPrecision2(GameValues.diplomacy.DIPLOMACY_DECLARE_WAR_COST, 100), Images.diplomacy, paddingLeft + CFG.PADDING + (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, CFG.TEXT_HEIGHT + CFG.PADDING * 5, iconWidth) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DiplomacyPoints") + ": ", "" + CFG.getPrecision2(GameValues.diplomacy.DIPLOMACY_DECLARE_WAR_COST, 100), Images.diplomacy, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame(Game.lang.get("Cancel"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, true) {
            @Override
            public void actionElement() {
                Game.menuManager.setVisibleInGame_PopUp(false);
                if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_DIPLOMACY) {
                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DIPLOMACY);
                }
                ProvinceBorderManager.updateDrawProvinceBorder_SelectCiv_ByCivID(InGame_DeclareWar.iCivID);
                InGame_Civ.iRebuildToCivID = InGame_DeclareWar.iCivID;
                Game.menuManager.rebuildInGame_Civ(true);
                InGame_Civ.lTime = 0L;
            }
        });
        menuElements.add(new ButtonGame_ImageSparks(Game.lang.get("DeclareWar"), CFG.FONT_REGULAR, -1, paddingLeft + CFG.PADDING + (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, true, Images.war) {
            @Override
            public int getSFX() {
                return -1;
            }
            
            @Override
            public void actionElement() {
                InGame_DeclareWar.confirm();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("DeclareWar"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.war, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (Game.getCiv(InGame_DeclareWar.iCivID).diplomacy.getRelation(Game.player.iCivID) > GameValues.war.RELATIONS_TO_DECLARE_WAR) {
                    nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("ToDeclareWarTheRelationsBetweenCivilizationsMustBeBelowX", GameValues.war.RELATIONS_TO_DECLARE_WAR), CFG.FONT_REGULAR_SMALL, (Game.getCiv(InGame_DeclareWar.iCivID).diplomacy.getRelation(Game.player.iCivID) > GameValues.war.RELATIONS_TO_DECLARE_WAR) ? Colors.HOVER_NEGATIVE : Colors.HOVER_LEFT));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                if (Game.getCiv(Game.player.iCivID).diplomacy.haveNonAggressionPact(InGame_DeclareWar.iCivID)) {
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("NonAggressionPact"), "", Images.nonAggression, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Expires") + ": ", "" + Game_Calendar.getDate_ByTurnID(Game.getCiv(Game.player.iCivID).diplomacy.nonAggressionPact.get(InGame_DeclareWar.iCivID).iTurnID), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DiplomacyPoints") + ": ", "" + CFG.getPrecision2(GameValues.diplomacy.DIPLOMACY_DECLARE_WAR_COST, 100), Images.diplomacy, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (GameValues.aggressiveExpansion.AE_DECLARE_WAR > 0.0f) {
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AggressiveExpansion") + ": ", ((GameValues.aggressiveExpansion.AE_DECLARE_WAR > 0.0f) ? "+" : "") + CFG.getPrecision2(GameValues.aggressiveExpansion.AE_DECLARE_WAR, 100), Images.aggressiveExpansion, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final List<Integer> alliesLeft = DiplomacyManager.declareWar_AlliesAttacker(Game.player.iCivID, InGame_DeclareWar.iCivID);
        final List<Integer> alliesRight = DiplomacyManager.declareWar_AlliesDefender(InGame_DeclareWar.iCivID, Game.player.iCivID);
        if (alliesLeft.size() > 0 || alliesRight.size() > 0) {
            menuElements.add(new Text_Title_v2Center(Game.lang.get("Allies"), -1, CFG.FONT_BOLD, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            final int alliesWidth = (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2;
            final int buttonH = CFG.BUTTON_HEIGHT3;
            int tMenuElementsBefore = menuElements.size();
            final int buttonYStart = buttonY;
            if (alliesLeft.size() > 1) {
                menuElements.add(new Text_StaticBG_ID_FlagCiv("" + Game.lang.get("CallAllies"), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, paddingLeft, buttonY, alliesWidth, buttonH, Game.player.iCivID) {
                    @Override
                    public void actionElement() {
                        final List<Integer> alliesLeft = DiplomacyManager.declareWar_AlliesAttacker(Game.player.iCivID, InGame_DeclareWar.iCivID);
                        if (alliesLeft.size() > 0) {
                            for (int i = alliesLeft.size() - 1; i >= 0; --i) {
                                if (InGame_DeclareWar.callToWar.contains(alliesLeft.get(i))) {
                                    for (int a = InGame_DeclareWar.callToWar.size() - 1; a >= 0; --a) {
                                        if (InGame_DeclareWar.callToWar.get(a).equals(alliesLeft.get(i))) {
                                            InGame_DeclareWar.callToWar.remove(a);
                                            break;
                                        }
                                    }
                                }
                                else {
                                    InGame_DeclareWar.callToWar.add(alliesLeft.get(i));
                                }
                            }
                        }
                    }
                });
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            for (int i = 0; i < alliesLeft.size(); ++i) {
                menuElements.add(new Text_StaticBG_ID_FlagCiv_GreenRed("" + Game.getCiv(alliesLeft.get(i)).getCivName(), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, paddingLeft, buttonY, alliesWidth, buttonH, (int)alliesLeft.get(i)) {
                    @Override
                    public void actionElement() {
                        if (InGame_DeclareWar.callToWar.contains(this.getCurrent())) {
                            for (int i = InGame_DeclareWar.callToWar.size() - 1; i >= 0; --i) {
                                if (InGame_DeclareWar.callToWar.get(i) == this.getCurrent()) {
                                    InGame_DeclareWar.callToWar.remove(i);
                                    break;
                                }
                            }
                        }
                        else {
                            InGame_DeclareWar.callToWar.add(this.getCurrent());
                        }
                    }
                    
                    @Override
                    public boolean getCheckboxState() {
                        return InGame_DeclareWar.callToWar.contains(this.getCurrent());
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("CallAlly") + ": " + Game.getCiv(this.getCurrent()).getCivName(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.alliance, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                });
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (tMenuElementsBefore == menuElements.size()) {
                menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, alliesWidth, buttonH));
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            buttonY = buttonYStart;
            tMenuElementsBefore = menuElements.size();
            for (int i = 0; i < alliesRight.size(); ++i) {
                menuElements.add(new Text_StaticBG_ID_FlagCiv("" + Game.getCiv(alliesRight.get(i)).getCivName(), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, paddingLeft + CFG.PADDING + alliesWidth, buttonY, alliesWidth, buttonH, (int)alliesRight.get(i)) {
                    @Override
                    public void actionElement() {
                        if (Game.getCiv(this.getCurrent()).getCapitalProvinceID() >= 0 && Game.getProvince(Game.getCiv(this.getCurrent()).getCapitalProvinceID()).getCivID() == this.getCurrent()) {
                            if (Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() == this.getCurrent()) {
                                Game.menuManager.rebuildInGame_Civ();
                            }
                            else {
                                Game.mapCoords.centerToProvinceID(Game.getCiv(this.getCurrent()).getCapitalProvinceID());
                                Game.setActiveProvinceID(Game.getCiv(this.getCurrent()).getCapitalProvinceID());
                                ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                            }
                        }
                    }
                    
                    @Override
                    public void buildElementHover() {
                        this.menuElementHover = CivilizationRanking.getHover_CivilizationRanking(this.getCurrent(), false, false);
                    }
                });
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            if (tMenuElementsBefore == menuElements.size()) {
                menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft + CFG.PADDING + alliesWidth, buttonY, alliesWidth, buttonH));
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
        }
        for (int j = menuElements.size() - 1; j >= 0; --j) {
            buttonY = Math.max(buttonY, menuElements.get(j).getPosY() + menuElements.get(j).getHeight() + CFG.PADDING);
        }
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, buttonY)));
        this.initMenu(new MenuTitleIMG(Game.lang.get("DeclareWar"), true, false, Images.title600) {
            @Override
            public long getTime() {
                return InGame_DeclareWar.lTime;
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        DiplomacyManager.updateInAnimation();
        if (InGame_DeclareWar.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_DeclareWar.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop600, Images.insideBot600);
        ImageManager.getImage(Images.civInfoOver).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.civInfoOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_DeclareWar.lTime = CFG.currentTimeMillis;
        DiplomacyManager.updateAnimationTime();
    }
    
    public static final boolean actionDeclareWar(final int iCivPlayer, final int onCivID) {
        return actionDeclareWar(iCivPlayer, onCivID, false);
    }
    
    public static final boolean actionDeclareWar(final int iCivPlayer, final int onCivID, final boolean free) {
        return actionDeclareWar(iCivPlayer, onCivID, free, new ArrayList<Integer>());
    }
    
    public static final boolean actionDeclareWar(final int iCivPlayer, final int onCivID, final boolean free, final List<Integer> callToWarAttacker) {
        if (!free) {
            if (Game.getCiv(onCivID).diplomacy.getRelation(iCivPlayer) > GameValues.war.RELATIONS_TO_DECLARE_WAR) {
                Game.menuManager.addToast_Error(Game.lang.get("ToDeclareWarTheRelationsBetweenCivilizationsMustBeBelowX", GameValues.war.RELATIONS_TO_DECLARE_WAR), Images.relations);
                return false;
            }
            if (Game.getCiv(iCivPlayer).diplomacy.haveNonAggressionPact(onCivID)) {
                Game.menuManager.addToast_Error(Game.lang.get("NonAggressionPact") + ": " + Game.getCiv(iCivPlayer).getCivName() + " - " + Game.getCiv(onCivID).getCivName(), Images.nonAggression);
                return false;
            }
            if (Game.getCiv(iCivPlayer).diplomacy.haveAlliance(onCivID) || Game.getCiv(onCivID).diplomacy.haveAlliance(iCivPlayer)) {
                Game.menuManager.addToast_Error(Game.lang.get("Alliance") + ": " + Game.getCiv(iCivPlayer).getCivName() + " - " + Game.getCiv(onCivID).getCivName(), Images.alliance);
                return false;
            }
            if (Game.getCiv(iCivPlayer).fDiplomacy < GameValues.diplomacy.DIPLOMACY_DECLARE_WAR_COST) {
                Game.menuManager.addToastInsufficient(Game.lang.get("Cost") + ", " + Game.lang.get("DiplomacyPoints") + ": ", CFG.getPrecision2(GameValues.diplomacy.DIPLOMACY_DECLARE_WAR_COST, 100), Images.diplomacy);
                return false;
            }
        }
        if (Game.getCiv(iCivPlayer).diplomacy.truce.containsKey(onCivID)) {
            if (Game.getCiv(iCivPlayer).diplomacy.truce.get(onCivID).iTurnID >= Game_Calendar.TURN_ID) {
                Game.menuManager.addToastInsufficient(Game.lang.get("Truce") + ": " + Game.lang.get("Expires") + ": ", Game_Calendar.getDate_ByTurnID(Game.getCiv(iCivPlayer).diplomacy.truce.get(onCivID).iTurnID), Images.time);
                return false;
            }
            Game.getCiv(iCivPlayer).diplomacy.truce.remove(onCivID);
            Game.getCiv(onCivID).diplomacy.truce.remove(iCivPlayer);
        }
        try {
            if (DiplomacyManager.declareWar(iCivPlayer, onCivID, free, callToWarAttacker)) {
                if (GameValues.provinceBorderWar.ENABLE_WAR_BORDER) {
                    Game.addSimpleTask(new Game.SimpleTask("updateProvinceBorder") {
                        @Override
                        public void update() {
                            ProvinceBorderManager.updateProvinceBorder();
                        }
                    });
                }
                Game.menuManager.rebuildInGame_Wars();
                Game.menuManager.setVisibleInGame_PopUp(false);
                InGame_Info.iCivID = iCivPlayer;
                InGame_Info.iCivID2 = onCivID;
                Game.menuManager.rebuildInGame_Info(Game.lang.get("War"), Game.getCiv(iCivPlayer).getCivName() + " - " + Game.getCiv(onCivID).getCivName());
                InGame_Info.imgID = Images.infoWar;
                Game.soundsManager.loadNextMusicWar();
                Game.menuManager.WAR_TIME = CFG.currentTimeMillis;
            }
            else {
                Game.menuManager.addToastInsufficient(Game.lang.get("Refused"), "", Images.x);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return true;
    }
    
    public static void confirm() {
        if (actionDeclareWar(Game.player.iCivID, InGame_DeclareWar.iCivID, false, InGame_DeclareWar.callToWar)) {
            Game.soundsManager.playSound(SoundsManager.WAR);
            SteamAchievementsManager.unlockAchievement(SteamAchievementsManager.DECLARE_WAR);
        }
        else {
            Game.soundsManager.playSound(Game.soundsManager.getClickMain());
        }
    }
    
    static {
        InGame_DeclareWar.lTime = 0L;
        InGame_DeclareWar.iCivID = 0;
        InGame_DeclareWar.callToWar = new ArrayList<Integer>();
    }
}
