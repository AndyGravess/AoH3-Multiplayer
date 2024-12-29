// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Civ;

import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagTitle;
import aoh.kingdoms.history.menusInGame.InGame_CivBonuses;
import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.mainGame.Keyboard;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_ShareTechnology;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_LiberateCivilization;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_Guarantee;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_OfferMilitaryAccess;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_DemandMilitaryAccess;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_NonAggression;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_DefensivePact;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_Alliance;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_Rivals;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_Rivals_End;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_SendInsult;
import aoh.kingdoms.history.menu.ClickAnimation;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoh.kingdoms.history.menu_element.MessageWar;
import aoh.kingdoms.history.menusInGame.InGame_War;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_SellProvince;
import aoh.kingdoms.history.map.PeaceTreaty;
import aoh.kingdoms.history.map.civilization.CivilizationsNeighbors;
import aoh.kingdoms.history.map.RivalsManager;
import aoh.kingdoms.history.map.diplomacy.Diplomacy_RelationsAction;
import aoh.kingdoms.history.map.diplomacy.Vassal;
import aoh.kingdoms.history.menu_element.button.ButtonFlag_DiplomacyWar;
import aoh.kingdoms.history.map.war.War;
import aoh.kingdoms.history.map.war.WarManager;
import aoh.kingdoms.history.map.province.ProvinceDraw;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_SpecialColor;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Rank;
import aoh.kingdoms.history.menu_element.textStatic.Text_TitleBlueSort;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_SendGift;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv_SpecialEmpty;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Bonuses;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_RulerTitle;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_FormCiv;
import aoh.kingdoms.history.map.FormableCivManager;
import aoh.kingdoms.history.menu_element.button.ButtonFlag_Formable;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2Center;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Active_Click;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import java.util.Iterator;
import java.util.Map;
import aoh.kingdoms.history.map.diplomacy.Diplomacy;
import aoh.kingdoms.history.mainGame.SoundsManager;
import aoh.kingdoms.history.menu_element.button.ButtonDiplomacy;
import aoh.kingdoms.history.menu_element.textStatic.Text_Static;
import aoh.kingdoms.history.map.allianceHRE.Alliance;
import aoh.kingdoms.history.menu_element.button.ButtonFlag_Diplomacy_Alliance;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon2_HorizontalSplit;
import aoh.kingdoms.history.map.diplomacy.DiplomacyManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_Static_Opinion;
import aoh.kingdoms.history.menu_element.button.ButtonFlag_Diplomacy;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_Diplomacy;
import aoh.kingdoms.history.menusInGame.Battle.InGame_Battlefield;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Empty;
import aoh.kingdoms.history.map.battles.BattleManager;
import aoh.kingdoms.history.menusInGame.InGame_Generals;
import aoh.kingdoms.history.menusInGame.RecruitArmy.InGame_RecruitArmy;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court_Government;
import aoh.kingdoms.history.map.LegacyManager;
import aoh.kingdoms.history.menu.View;
import aoh.kingdoms.history.menusInGame.InGame_Legacies;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ResourceTitle;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.map.map.Continents;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon2_Rank;
import aoh.kingdoms.history.menu_element.button.ButtonReligion2;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonusResource;
import aoh.kingdoms.history.menusInGame.Goods.InGame_GoodsMarket;
import aoh.kingdoms.history.menu_element.button.ButtonResource2;
import aoh.kingdoms.history.map.ResourcesManager;
import aoh.kingdoms.history.menusInGame.InGame_MapModes;
import aoh.kingdoms.history.menu_element.button.ButtonIdeology2;
import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Flag;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title;
import aoh.kingdoms.history.menusInGame.InGame_Ranking;
import aoh.kingdoms.history.menu.MenuManager;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon2_Horizontal;
import aoh.kingdoms.history.map.civilization.CivilizationRanking;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonusFlag;
import aoh.kingdoms.history.mainGame.Game_Ages;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu.ColorPicker;
import aoh.kingdoms.history.menu_element.button.ButtonFlag;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court;
import aoh.kingdoms.history.menu_element.button.ButtonRuler;
import aoh.kingdoms.history.map.RulersManager;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Civ extends Menu
{
    public static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static long lTime2;
    public static int iActiveCivID;
    public static int iRebuildToCivID;
    public static boolean diplomacyMode;
    public static int iSortID;
    public int flagX;
    public int flagY;
    public int flagW;
    public int flagH;
    public static boolean TRADE;
    public static boolean enabledByScaleOut;
    public static String renameCiv;
    public static int iMaxH_DiplomacyIcon;
    public static boolean relationsMode;
    
    public InGame_Civ() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING;
        final int paddingLeft2 = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2;
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        final int buttonYPadding = CFG.PADDING;
        int buttonX = paddingLeft;
        int buttonY = buttonYPadding;
        if (InGame_Civ.iRebuildToCivID >= 0) {
            InGame_Civ.iActiveCivID = InGame_Civ.iRebuildToCivID;
            InGame_Civ.iRebuildToCivID = -1;
        }
        else if (Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() > 0) {
            InGame_Civ.iActiveCivID = Game.getProvince(Game.iActiveProvince).getCivID();
        }
        else {
            InGame_Civ.iActiveCivID = Game.player.iCivID;
        }
        updateMaxIconH();
        RulersManager.loadRulerIMG(InGame_Civ.iActiveCivID);
        menuElements.add(new ButtonRuler(InGame_Civ.iActiveCivID, buttonX, buttonY) {
            @Override
            public void actionElement() {
                InGame_Court.iActiveCivID = InGame_Civ.iActiveCivID;
                Game.menuManager.setVisibleInGame_Civ(false);
                Game.menuManager.rebuildInGame_Court();
                Game.menuManager.setVisibleInGame_Court(true);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        final int flagPadding = CFG.PADDING * 3;
        this.flagX = buttonX;
        this.flagY = buttonY;
        this.flagW = menuWidth - ButtonRuler.getButtonWidth() * 2 - paddingLeft * 2 - CFG.PADDING * 2;
        this.flagH = ButtonFlag.getButtonHeight() + flagPadding * 2;
        menuElements.add(new ButtonFlag(this.flagX + this.flagW / 2 - ButtonFlag.getButtonWidth() / 2, this.flagY + flagPadding, true) {
            @Override
            public int getFlagCivID() {
                return InGame_Civ.iActiveCivID;
            }
            
            @Override
            public void actionElement() {
                if (Game.menuManager.getColorPicker().getVisible()) {
                    Game.menuManager.getColorPicker().hideColorPicker();
                }
                else {
                    ColorPicker.ACTIVE_CIV_ID = InGame_Civ.iActiveCivID;
                    Game.menuManager.getColorPicker().setActiveRGBColor(Game.getCiv(InGame_Civ.iActiveCivID).getR(), Game.getCiv(InGame_Civ.iActiveCivID).getG(), Game.getCiv(InGame_Civ.iActiveCivID).getB());
                    Game.menuManager.getColorPicker().setVisible(true, ColorPicker.PickerAction.CIV_COLOR_INGAME);
                    Game.menuManager.getColorPicker().setPosX(CFG.GAME_WIDTH - CFG.BUTTON_WIDTH - Game.menuManager.getColorPicker().getWidth());
                    Game.menuManager.getColorPicker().setPosY(CFG.GAME_HEIGHT / 10);
                    Game.menuManager.setVisibleInGame_Civ(false);
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ShowHideColorPicker"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (Game.getCiv(InGame_Civ.iActiveCivID).getPuppetOfCivID() != InGame_Civ.iActiveCivID) {
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.lang.get(Game_Ages.getLord()) + ": ", Game.getCiv(Game.getCiv(InGame_Civ.iActiveCivID).getPuppetOfCivID()).getCivName(), Game.getCiv(InGame_Civ.iActiveCivID).getPuppetOfCivID(), CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("CapitalCity") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(InGame_Civ.iActiveCivID).getCapitalLevel() + " / " + Game.getCapital_MaxLvl(InGame_Civ.iActiveCivID), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.capital, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MilitaryAcademy") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(InGame_Civ.iActiveCivID).getMilitaryAcademyLevel() + " / " + Game.getMilitaryAcademy_MaxLvl(InGame_Civ.iActiveCivID), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MilitaryAcademyForGenerals") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(InGame_Civ.iActiveCivID).getMilitaryAcademyForGeneralsLevel() + " / " + Game.getMilitaryAcademyForGenerals_MaxLvl(InGame_Civ.iActiveCivID), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.general, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("SupremeCourt") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(InGame_Civ.iActiveCivID).getSupremeCourtLevel() + " / " + Game.getSupremeCourt_MaxLvl(InGame_Civ.iActiveCivID), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.stability, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (Game.getCiv(InGame_Civ.iActiveCivID).canBuildNuke || Game.getCiv(InGame_Civ.iActiveCivID).getNuclearReactorLevel() > 0) {
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("NuclearReactor") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(InGame_Civ.iActiveCivID).getNuclearReactorLevel() + " / " + Game.getNuclearReactor_MaxLvl(InGame_Civ.iActiveCivID), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.nuke, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                if (Game.getCiv(InGame_Civ.iActiveCivID).canBuildNuke) {
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("NuclearWeapons"), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.nuke, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AtomicBombs") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(InGame_Civ.iActiveCivID).getNukes(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.nuke, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
                oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5f));
                Renderer.drawBox(oSB, Images.statsRectBG, InGame_Civ.this.flagX + iTranslateX, InGame_Civ.this.flagY + iTranslateY, InGame_Civ.this.flagW, InGame_Civ.this.flagH, 1.0f);
                oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5f));
                Images.gradientXY.draw(oSB, InGame_Civ.this.flagX + iTranslateX, InGame_Civ.this.flagY + iTranslateY, InGame_Civ.this.flagW, InGame_Civ.this.flagH);
                oSB.setColor(Color.WHITE);
                oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.55f));
                Images.gradientXY.draw(oSB, InGame_Civ.this.flagX + iTranslateX, InGame_Civ.this.flagY + iTranslateY, InGame_Civ.this.flagW, InGame_Civ.this.flagH / 2, false, true);
                Images.gradientXY.draw(oSB, InGame_Civ.this.flagX + iTranslateX, InGame_Civ.this.flagY + InGame_Civ.this.flagH / 2 + iTranslateY, InGame_Civ.this.flagW, InGame_Civ.this.flagH / 2, false, false);
                Images.gradientFull.draw(oSB, InGame_Civ.this.flagX + iTranslateX, InGame_Civ.this.flagY + InGame_Civ.this.flagH - 2 + iTranslateY, InGame_Civ.this.flagW, 1);
                Images.gradientFull.draw(oSB, InGame_Civ.this.flagX + iTranslateX, InGame_Civ.this.flagY + 1 + iTranslateY, InGame_Civ.this.flagW, 1);
                oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.2f));
                Images.gradientFull.draw(oSB, InGame_Civ.this.flagX + iTranslateX, InGame_Civ.this.flagY + iTranslateY, InGame_Civ.this.flagW, 1);
                Images.gradientFull.draw(oSB, InGame_Civ.this.flagX + iTranslateX, InGame_Civ.this.flagY + InGame_Civ.this.flagH - 1 + iTranslateY, InGame_Civ.this.flagW, 1);
                oSB.setColor(Color.WHITE);
                if (this.getIsHovered()) {
                    oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 1.0f));
                    Renderer.drawBox(oSB, Images.statsRectBGBorder, InGame_Civ.this.flagX + iTranslateX, InGame_Civ.this.flagY + iTranslateY, InGame_Civ.this.flagW, InGame_Civ.this.flagH, 1.0f);
                    oSB.setColor(Color.WHITE);
                }
                super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
            }
        });
        buttonX = this.flagX + this.flagW + CFG.PADDING;
        final int tTextButtonH = (this.flagH - CFG.PADDING * 2) / 3;
        final int maxIconWidth = ImageManager.getImage(Images.capital).getWidth();
        menuElements.add(new TextIcon2_Horizontal("" + ((Game.getCiv(InGame_Civ.iActiveCivID).iCivRankPosition > 0) ? Integer.valueOf(Game.getCiv(InGame_Civ.iActiveCivID).iCivRankPosition) : "---"), CivilizationRanking.getCivilizationRanking_IMG_STAR_CIVID(InGame_Civ.iActiveCivID), buttonX, buttonY, ButtonRuler.getButtonWidth(), tTextButtonH, maxIconWidth, true) {
            int lastValue = 0;
            
            @Override
            public String getTextToDraw() {
                if (this.lastValue != Game.getCiv(InGame_Civ.iActiveCivID).iCivRankPosition) {
                    this.lastValue = Game.getCiv(InGame_Civ.iActiveCivID).iCivRankPosition;
                    this.setText("" + ((this.lastValue > 0) ? ("" + this.lastValue) : "---"));
                }
                return this.sText;
            }
            
            @Override
            public void actionElement() {
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
            
            @Override
            public void buildElementHover() {
                this.menuElementHover = CivilizationRanking.getHover_CivilizationRanking(InGame_Civ.iActiveCivID, true, false);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new TextIcon2_Horizontal(CivilizationRanking.getCivilizationRank_Name(Game.getCiv(InGame_Civ.iActiveCivID).iCivRankID), CivilizationRanking.getCivilizationRank_IMG(Game.getCiv(InGame_Civ.iActiveCivID).iCivRankID), buttonX, buttonY, ButtonRuler.getButtonWidth(), tTextButtonH, maxIconWidth, true) {
            @Override
            public void buildElementHover() {
                this.menuElementHover = CivilizationRanking.buildElementHover(InGame_Civ.iActiveCivID, Game.getCiv(InGame_Civ.iActiveCivID).iCivRankID);
            }
            
            @Override
            public void actionElement() {
                Game.menuManager.rebuildInGame_CivRank();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new TextIcon2_Horizontal((Game.getCiv(InGame_Civ.iActiveCivID).getCapitalProvinceID() >= 0) ? Game.getProvince(Game.getCiv(InGame_Civ.iActiveCivID).getCapitalProvinceID()).getProvinceName() : "", Images.capital, buttonX, buttonY, ButtonRuler.getButtonWidth(), tTextButtonH, maxIconWidth, true) {
            @Override
            public void buildElementHover() {
                if (Game.getCiv(InGame_Civ.iActiveCivID).getCapitalProvinceID() >= 0) {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(InGame_Civ.iActiveCivID, Game.lang.get("Capital") + ": " + Game.getProvince(Game.getCiv(InGame_Civ.iActiveCivID).getCapitalProvinceID()).getProvinceName()));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", CFG.getNumberWithSpaces("" + Game.getProvince(Game.getCiv(InGame_Civ.iActiveCivID).getCapitalProvinceID()).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Economy") + ": ", CFG.getPrecision2(Game.getProvince(Game.getCiv(InGame_Civ.iActiveCivID).getCapitalProvinceID()).getEconomyWithBonuses(), 10), Game_Calendar.IMG_ECONOMY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Income") + ": ", CFG.getPrecision2(Game.getProvince(Game.getCiv(InGame_Civ.iActiveCivID).getCapitalProvinceID()).getProvinceIncome() - Game.getProvince(Game.getCiv(InGame_Civ.iActiveCivID).getCapitalProvinceID()).fProvinceMaintenance, 100), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    try {
                        if (Game.player.iCivID != InGame_Civ.iActiveCivID && Game.getCiv(Game.player.iCivID).getCapitalProvinceID() >= 0 && Game.getCiv(InGame_Civ.iActiveCivID).getCapitalProvinceID() >= 0) {
                            nData.add(new MenuElement_HoverElement_Type_Line());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DistanceFromCapital") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getDistanceFromAToB_Km(Game.getCiv(Game.player.iCivID).getCapitalProvinceID(), Game.getCiv(InGame_Civ.iActiveCivID).getCapitalProvinceID()), 10) + " km", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Flag(Game.player.iCivID, CFG.PADDING, 0));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.capital, CFG.PADDING, 0));
                            nData.add(new MenuElement_HoverElement_Type_Flag(InGame_Civ.iActiveCivID, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(InGame_Civ.iActiveCivID).getCivName() + " - " + Game.getCiv(Game.player.iCivID).getCivName(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                    if (InGame_Civ.iActiveCivID == Game.player.iCivID) {
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Shortcut") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                        nData.add(new MenuElement_HoverElement_Type_Text("HOME", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
                else {
                    this.menuElementHover = null;
                }
            }
            
            @Override
            public void actionElement() {
                if (Game.getCiv(InGame_Civ.iActiveCivID).getCapitalProvinceID() >= 0) {
                    if (Game.iActiveProvince == Game.getCiv(InGame_Civ.iActiveCivID).getCapitalProvinceID()) {
                        Game.menuManager.hideCourtCiv();
                        Game.menuManager.setVisibleInGame_Civ(false);
                        Game.setActiveProvinceID(Game.getCiv(InGame_Civ.iActiveCivID).getCapitalProvinceID());
                        Game.menuManager.rebuildInGame_ProvinceInfo(true);
                        ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                    }
                    else {
                        Game.mapCoords.centerToProvinceID(Game.getCiv(InGame_Civ.iActiveCivID).getCapitalProvinceID());
                        Game.setActiveProvinceID(Game.getCiv(InGame_Civ.iActiveCivID).getCapitalProvinceID());
                        ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                    }
                }
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonIdeology2(Game.getCiv(InGame_Civ.iActiveCivID).getIdeologyID(), buttonX, buttonY, ButtonRuler.getButtonWidth() / 2 - CFG.PADDING / 2, ButtonRuler.getButtonHeight() - buttonY + CFG.PADDING) {
            @Override
            public void actionElement() {
                InGame_Civ_Government.iGovID = Game.getCiv(InGame_Civ.iActiveCivID).getIdeologyID();
                Game.menuManager.rebuildInGame_Civ_Government();
            }
            
            @Override
            public void actionElementPPM() {
                InGame_MapModes.actionGovernment();
            }
        });
        menuElements.add(new ButtonResource2(ResourcesManager.getLargestGoodsProducedByCiv(InGame_Civ.iActiveCivID), buttonX + ButtonRuler.getButtonWidth() / 2 + CFG.PADDING, buttonY, ButtonRuler.getButtonWidth() / 2 - CFG.PADDING / 2, ButtonRuler.getButtonHeight() - buttonY + CFG.PADDING) {
            @Override
            public void actionElement() {
                Game.menuManager.setVisibleInGame_Civ(false);
                InGame_GoodsMarket.iActiveCivID = InGame_Civ.iActiveCivID;
                Game.menuManager.rebuildInGame_GoodsMarket();
            }
            
            @Override
            public void actionElementPPM() {
                InGame_MapModes.actionGoods();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(InGame_Civ.iActiveCivID, Game.lang.get("ProducedGoods")));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                final List<Float> goodsProduced = new ArrayList<Float>();
                for (int i = 0; i < ResourcesManager.iResourcesSize; ++i) {
                    goodsProduced.add(0.0f);
                }
                int bestResourceID = -1;
                for (int j = 0; j < Game.getCiv(InGame_Civ.iActiveCivID).getNumOfProvinces(); ++j) {
                    if (Game.getProvince(Game.getCiv(InGame_Civ.iActiveCivID).getProvinceID(j)).getResourceID() >= 0) {
                        goodsProduced.set(Game.getProvince(Game.getCiv(InGame_Civ.iActiveCivID).getProvinceID(j)).getResourceID(), goodsProduced.get(Game.getProvince(Game.getCiv(InGame_Civ.iActiveCivID).getProvinceID(j)).getResourceID()) + ResourcesManager.getProducedGoods(Game.getCiv(InGame_Civ.iActiveCivID).getProvinceID(j)));
                        bestResourceID = Game.getProvince(Game.getCiv(InGame_Civ.iActiveCivID).getProvinceID(j)).getResourceID();
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
        menuElements.add(new ButtonReligion2(Game.getCiv(InGame_Civ.iActiveCivID).getReligionID(), this.flagX, buttonY, this.flagW, ButtonRuler.getButtonHeight() - buttonY + CFG.PADDING) {
            @Override
            public void actionElement() {
                InGame_Civ_Religion.iReligionID = Game.getCiv(InGame_Civ.iActiveCivID).getReligionID();
                Game.menuManager.rebuildInGame_Civ_Religion();
            }
            
            @Override
            public void actionElementPPM() {
                InGame_MapModes.actionReligion();
            }
            
            @Override
            public void buildElementHover() {
                this.menuElementHover = Game.religionManager.getHoverReligion(this.religionID, InGame_Civ.iActiveCivID);
            }
        });
        buttonY = buttonYPadding + ButtonRuler.getButtonHeight() + CFG.PADDING;
        final int tWidth = (menuWidth - paddingLeft * 2 - CFG.PADDING * 5) / 6;
        buttonX = paddingLeft;
        final int statsH = ImageManager.getImage(Images.provinces).getHeight() + CFG.TEXT_HEIGHT + CFG.PADDING * 6;
        final int statsH2 = (ImageManager.getImage(Images.provinces).getHeight() + CFG.TEXT_HEIGHT + CFG.PADDING * 6 - CFG.PADDING) / 2;
        menuElements.add(new TextIcon2_Rank("#" + this.getRankProvinces(InGame_Civ.iActiveCivID), "" + Game.getCiv(InGame_Civ.iActiveCivID).getNumOfProvinces(), Images.provinces, buttonX, buttonY, tWidth, statsH) {
            @Override
            public void actionElement() {
                Game.menuManager.rebuildInGame_Civ_Provinces();
            }
            
            @Override
            public void buildElementHover() {
                if (Game.getCiv(InGame_Civ.iActiveCivID).getCapitalProvinceID() >= 0) {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.getCiv(InGame_Civ.iActiveCivID).getCivName() + ": " + Game.lang.get("Provinces"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.provinces, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    final int mapProvinces = Game.countPlayableProvince();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Provinces") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.getCiv(InGame_Civ.iActiveCivID).getNumOfProvinces() + " / " + mapProvinces, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(" [" + CFG.getPrecision2(Game.getCiv(InGame_Civ.iActiveCivID).getNumOfProvinces() / (float)mapProvinces * 100.0f, 100) + "%]", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.provinces, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    try {
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        final List<Integer> tContNum = new ArrayList<Integer>();
                        for (int i = 0; i < Game.continents.iContinentsSize; ++i) {
                            tContNum.add(0);
                        }
                        for (int i = 0; i < Game.getCiv(InGame_Civ.iActiveCivID).getNumOfProvinces(); ++i) {
                            tContNum.set(Game.getProvince(Game.getCiv(InGame_Civ.iActiveCivID).getProvinceID(i)).getContinent(), tContNum.get(Game.getProvince(Game.getCiv(InGame_Civ.iActiveCivID).getProvinceID(i)).getContinent()) + 1);
                        }
                        for (int i = 0; i < Game.continents.iContinentsSize; ++i) {
                            int bestID = 0;
                            for (int j = 1; j < Game.continents.iContinentsSize; ++j) {
                                if (tContNum.get(bestID) < tContNum.get(j)) {
                                    bestID = j;
                                }
                            }
                            if (tContNum.get(bestID) <= 0) {
                                break;
                            }
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.continents.lContinents.get(bestID).sName + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + tContNum.get(bestID), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.provinces, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            tContNum.set(bestID, 0);
                        }
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
                else {
                    this.menuElementHover = null;
                }
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new TextIcon2_Rank("#" + getRankPopulation(InGame_Civ.iActiveCivID), CFG.getShortNumber(Game.getCiv(InGame_Civ.iActiveCivID).getPopulationTotal()), Images.population, buttonX, buttonY, tWidth, statsH) {
            @Override
            public void actionElement() {
                InGame_Civ_Population.goBackToRank = false;
                Game.menuManager.rebuildInGame_Civ_Population();
                if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_CIV_POPULATION_HOVER) {
                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DIPLOMACY);
                }
            }
            
            @Override
            public void actionElementPPM() {
                Game.mapModes.setActiveViewID(Game.mapModes.MODE_POPULATION);
            }
            
            @Override
            public void setIsHovered(final boolean isHovered) {
                super.setIsHovered(isHovered);
                if (isHovered) {
                    if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DIPLOMACY || Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFAULT || Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_CIV_ECONOMY_HOVER) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_CIV_POPULATION_HOVER);
                    }
                }
                else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_CIV_POPULATION_HOVER) {
                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DIPLOMACY);
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.getCiv(InGame_Civ.iActiveCivID).getCivName() + ": " + Game.lang.get("Population"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.population, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", "" + CFG.getNumberWithSpaces("" + Game.getCiv(InGame_Civ.iActiveCivID).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Ranking") + ": ", "#" + InGame_Civ.getRankPopulation(InGame_Civ.iActiveCivID) + " / " + InGame_Civ.getRankCivsWithProvinces(InGame_Civ.iActiveCivID), CivilizationRanking.getCivilizationRanking_IMG_STAR_CIVID(InGame_Civ.iActiveCivID), CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("GrowthRate") + ", " + Game.lang.get("Average") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getCiv(InGame_Civ.iActiveCivID).getAverageGrowthRate(), 100) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.populationGrowth, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
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
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new TextIcon2_Rank("#" + this.getRankEconomy(InGame_Civ.iActiveCivID), "" + CFG.getShortNumber((int)Game.getCiv(InGame_Civ.iActiveCivID).getEconomyTotal()), Game_Calendar.IMG_ECONOMY, buttonX, buttonY, tWidth, statsH) {
            @Override
            public void actionElement() {
                InGame_Civ_Economy.goBackToRank = false;
                Game.menuManager.rebuildInGame_Civ_Economy();
                if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_CIV_ECONOMY_HOVER) {
                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DIPLOMACY);
                }
            }
            
            @Override
            public void actionElementPPM() {
                Game.mapModes.setActiveViewID(Game.mapModes.MODE_ECONOMY);
            }
            
            @Override
            public void setIsHovered(final boolean isHovered) {
                super.setIsHovered(isHovered);
                if (isHovered) {
                    if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DIPLOMACY || Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFAULT || Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_CIV_POPULATION_HOVER) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_CIV_ECONOMY_HOVER);
                    }
                }
                else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_CIV_ECONOMY_HOVER) {
                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DIPLOMACY);
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.getCiv(InGame_Civ.iActiveCivID).getCivName() + ": " + Game.lang.get("Economy"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_ECONOMY, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Economy") + ": ", "" + CFG.getPrecision2(Game.getCiv(InGame_Civ.iActiveCivID).getEconomyTotal(), 100), Game_Calendar.IMG_ECONOMY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_ECONOMY));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Ranking") + ": ", "#" + InGame_Civ.this.getRankEconomy(InGame_Civ.iActiveCivID) + " / " + InGame_Civ.getRankCivsWithProvinces(InGame_Civ.iActiveCivID), CivilizationRanking.getCivilizationRanking_IMG_STAR_CIVID(InGame_Civ.iActiveCivID), CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Infrastructure") + ": ", "" + CFG.getPrecision2((float)Game.getCiv(InGame_Civ.iActiveCivID).getInfrastructure(), 1), Images.infrastructure, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_ECONOMY));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Loans") + ": ", "" + Game.getCiv(InGame_Civ.iActiveCivID).loans.size() + " / " + Game.getLoanMaxNumber(InGame_Civ.iActiveCivID), Images.loan, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_ECONOMY));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ProsperityTier") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getProsperityLevel(InGame_Civ.iActiveCivID), CFG.FONT_BOLD_SMALL, Colors.getEconomyColor((int)(Game.getCiv(InGame_Civ.iActiveCivID).fProsperity_AverageEconomy / GameValues.prosperity.PROSPERITY_INCOME), 1.0f)));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.goldPositive, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MonthlyIncomeEconomy") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(Game.getCiv(InGame_Civ.iActiveCivID).fProsperity_AverageEconomy * GameValues.prosperity.PROSPERITY_INCOME, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.goldPositive, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new TextIcon2_Horizontal("" + Game.getCiv(InGame_Civ.iActiveCivID).largestProducerNum, Images.goods, buttonX, buttonY, tWidth, statsH2, maxIconWidth) {
            @Override
            public void actionElement() {
                Game.menuManager.setVisibleInGame_Civ(false);
                InGame_GoodsMarket.iActiveCivID = InGame_Civ.iActiveCivID;
                Game.menuManager.rebuildInGame_GoodsMarket();
            }
            
            @Override
            public void actionElementPPM() {
                InGame_MapModes.actionGoods();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.getCiv(InGame_Civ.iActiveCivID).getCivName() + ": " + Game.lang.get("ProducedGoods"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.goods, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UniqueResources") + ": ", "" + Game.getCiv(InGame_Civ.iActiveCivID).iUniqueResources, Images.goods, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("LargestProducer") + ": ", "" + Game.getCiv(InGame_Civ.iActiveCivID).largestProducerNum, Images.goods, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (Game.getCiv(InGame_Civ.iActiveCivID).largestProducerNum > 0) {
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    for (int i = 0; i < ResourcesManager.worldResources_LargestProducer.size(); ++i) {
                        if (InGame_Civ.iActiveCivID == ResourcesManager.worldResources_LargestProducer.get(i)) {
                            nData.add(new MenuElement_HoverElement_Type_TextTitle(ResourcesManager.lResources.get(i).Name, CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_ResourceTitle(i, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                    }
                }
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new TextIcon2_Horizontal("" + CFG.getShortNumber(Game.getCiv(InGame_Civ.iActiveCivID).getLegaciesUnlocked()), Images.legacy, buttonX, buttonY + CFG.PADDING + statsH2, tWidth, statsH2, maxIconWidth) {
            @Override
            public void actionElement() {
                InGame_Legacies.iActiveCivID = InGame_Civ.iActiveCivID;
                Game.menuManager.setViewIDWithoutAnimation(View.IN_GAME_LEGACIES);
                Game.menuManager.setOrderOfMenu_InGameLegacies();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.getCiv(InGame_Civ.iActiveCivID).getCivName() + ": " + Game.lang.get("Legacy"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.legacy, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnlockedLegacy") + ": ", "" + Game.getCiv(InGame_Civ.iActiveCivID).getLegaciesUnlocked(), Images.legacy, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                for (int i = 0; i < LegacyManager.iLegacyGroupsSize; ++i) {
                    nData.add(new MenuElement_HoverElement_Type_Text(LegacyManager.legacyGroups.get(i) + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(InGame_Civ.iActiveCivID).getLegaciesUnlocked(i), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new TextIcon2_Horizontal("" + Game.getCiv(InGame_Civ.iActiveCivID).getResearchedTechnologies(), Game_Calendar.IMG_TECHNOLOGY, buttonX, buttonY, tWidth, statsH2, maxIconWidth) {
            @Override
            public void actionElement() {
                Game.menuManager.rebuildInGame_Civ_UnlockedTechnologies();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.getCiv(InGame_Civ.iActiveCivID).getCivName() + ": " + Game.lang.get("UnlockedTechnologies"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Technologies") + ": ", "" + Game.getCiv(InGame_Civ.iActiveCivID).getResearchedTechnologies(), Game_Calendar.IMG_TECHNOLOGY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ResearchPerMonth") + ": ", "" + CFG.getPrecision2(Game.getCiv(InGame_Civ.iActiveCivID).fResearchPerMonth, 100), Game_Calendar.IMG_TECHNOLOGY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new TextIcon2_Horizontal("" + Game.getCiv(InGame_Civ.iActiveCivID).iAdvantagesSize, Images.advantages, buttonX, buttonY + CFG.PADDING + statsH2, tWidth, statsH2, maxIconWidth) {
            @Override
            public void actionElement() {
                Game.menuManager.rebuildInGame_Civ_UnlockedAdvantages();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.getCiv(InGame_Civ.iActiveCivID).getCivName() + ": " + Game.lang.get("CivilizationAdvantages"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.advantages, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("CivilizationAdvantages") + ": ", "" + Game.getCiv(InGame_Civ.iActiveCivID).iAdvantagesSize, Images.advantages, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new TextIcon2_Horizontal("" + Game.getCiv(InGame_Civ.iActiveCivID).iRegimentsLimit, Images.regimentsLimit, buttonX, buttonY, tWidth, statsH2, maxIconWidth) {
            @Override
            public void actionElement() {
                Game.menuManager.rebuildInGame_Civ_Regiments();
                if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_CIV_POPULATION_HOVER) {
                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DIPLOMACY);
                }
            }
            
            @Override
            public void buildElementHover() {
                if (Game.getCiv(InGame_Civ.iActiveCivID).getCapitalProvinceID() >= 0) {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Civilizations") + ": " + Game.lang.get("RegimentsLimit"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.regimentsLimit, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("RegimentsLimit") + ": ", "" + Game.getCiv(InGame_Civ.iActiveCivID).iRegimentsLimit, Images.regimentsLimit, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BaseValue") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENTS_LIMIT_BASE_VALUE, CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.regimentsLimit, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    if (Game.getCiv(InGame_Civ.iActiveCivID).civBonuses.RegimentsLimit > 0) {
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Bonuses") + ": ", CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(InGame_Civ.iActiveCivID).civBonuses.RegimentsLimit, CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.regimentsLimit, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                    if (Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.iVassalsSize > 0) {
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Vassals") + ": ", CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + GameValues.army.REGIMENTS_LIMIT_PER_VASSAL * Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.iVassalsSize, CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.vassal, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LocalManpower") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(InGame_Civ.iActiveCivID).getRegimentsLimit_Manpower(), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                    nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    final float value = Game.getCiv(InGame_Civ.iActiveCivID).getRegimentsLimit_FromAllianceSpecial();
                    if (value > 0.0f) {
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Alliances") + ": ", CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(value, 1), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.alliance, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                    if (Game.getCiv(InGame_Civ.iActiveCivID).getPuppetOfCivID() != InGame_Civ.iActiveCivID) {
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Vassal") + ": ", CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(GameValues.army.REGIMENTS_LIMIT_VASSAL * 100.0f, 1) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.vassal, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", "" + CFG.getNumberWithSpaces("" + (int)Game.getCiv(InGame_Civ.iActiveCivID).fManpowerMax), Game_Calendar.IMG_MANPOWER_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
                else {
                    this.menuElementHover = null;
                }
            }
        });
        menuElements.add(new TextIcon2_Horizontal("" + CFG.getPrecision2(Game.getCiv(InGame_Civ.iActiveCivID).civBonuses.Discipline * 100.0f, 1) + "%", Images.discipline, buttonX, buttonY + CFG.PADDING + statsH2, tWidth, statsH2, maxIconWidth) {
            @Override
            public void buildElementHover() {
                if (Game.getCiv(InGame_Civ.iActiveCivID).getCapitalProvinceID() >= 0) {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("CivilizationBonuses"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(CivilizationRanking.getCivilizationRank_IMG(InGame_Civ.iActiveCivID), CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Discipline") + ": ", CFG.getPrecision2(Game.getCiv(InGame_Civ.iActiveCivID).civBonuses.Discipline * 100.0f, 100) + "%", Images.discipline, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
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
                InGame_Civ.actionCivBonuses();
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        buttonY = menuElements.get(0).getPosY() + menuElements.get(0).getHeight() + CFG.PADDING;
        buttonY += statsH + CFG.PADDING;
        buttonX = paddingLeft;
        menuElements.add(new TextIcon2_Horizontal("" + Game.getCiv(InGame_Civ.iActiveCivID).getCapitalLevel(), Images.capital, buttonX, buttonY, tWidth, statsH2, maxIconWidth) {
            int lastValue = 0;
            
            @Override
            public String getTextToDraw() {
                if (this.lastValue != Game.getCiv(InGame_Civ.iActiveCivID).getCapitalLevel()) {
                    this.lastValue = Game.getCiv(InGame_Civ.iActiveCivID).getCapitalLevel();
                    this.setText("" + this.lastValue);
                }
                return this.sText;
            }
            
            @Override
            public void buildElementHover() {
                if (Game.getCiv(InGame_Civ.iActiveCivID).getCapitalProvinceID() >= 0) {
                    this.menuElementHover = InGame_Court_Government.getHoverCapitalCity(InGame_Civ.iActiveCivID == Game.player.iCivID, InGame_Civ.iActiveCivID);
                }
                else {
                    this.menuElementHover = null;
                }
            }
            
            @Override
            public void actionElement() {
                if (InGame_Civ.iActiveCivID == Game.player.iCivID) {
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
                else {
                    Game.menuManager.rebuildInGame_Civ_CapitalCity();
                    if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_CIV_POPULATION_HOVER) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_DIPLOMACY);
                    }
                }
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new TextIcon2_Horizontal("" + Game.getCiv(InGame_Civ.iActiveCivID).getMilitaryAcademyLevel(), Game_Calendar.IMG_MANPOWER, buttonX, buttonY, tWidth, statsH2, maxIconWidth) {
            int lastValue = 0;
            
            @Override
            public String getTextToDraw() {
                if (this.lastValue != Game.getCiv(InGame_Civ.iActiveCivID).getMilitaryAcademyLevel()) {
                    this.lastValue = Game.getCiv(InGame_Civ.iActiveCivID).getMilitaryAcademyLevel();
                    this.setText("" + this.lastValue);
                }
                return this.sText;
            }
            
            @Override
            public void buildElementHover() {
                this.menuElementHover = InGame_RecruitArmy.getHoverMilitaryAcademy(InGame_Civ.iActiveCivID == Game.player.iCivID, InGame_Civ.iActiveCivID);
            }
            
            @Override
            public void actionElement() {
                if (InGame_Civ.iActiveCivID == Game.player.iCivID) {
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
                else {
                    Game.menuManager.rebuildInGame_Civ_MilitaryAcademy();
                    if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_CIV_POPULATION_HOVER) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_DIPLOMACY);
                    }
                }
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new TextIcon2_Horizontal("" + Game.getCiv(InGame_Civ.iActiveCivID).getMilitaryAcademyForGeneralsLevel(), Images.general, buttonX, buttonY, tWidth, statsH2, maxIconWidth) {
            int lastValue = 0;
            
            @Override
            public String getTextToDraw() {
                if (this.lastValue != Game.getCiv(InGame_Civ.iActiveCivID).getMilitaryAcademyForGeneralsLevel()) {
                    this.lastValue = Game.getCiv(InGame_Civ.iActiveCivID).getMilitaryAcademyForGeneralsLevel();
                    this.setText("" + this.lastValue);
                }
                return this.sText;
            }
            
            @Override
            public void buildElementHover() {
                this.menuElementHover = InGame_Generals.getHoverMilitaryAcademyForGenerals(InGame_Civ.iActiveCivID == Game.player.iCivID, InGame_Civ.iActiveCivID);
            }
            
            @Override
            public void actionElement() {
                if (InGame_Civ.iActiveCivID == Game.player.iCivID) {
                    if (Game.getCiv(Game.player.iCivID).getMilitaryAcademyForGeneralsLevel() >= Game.getMilitaryAcademyForGenerals_MaxLvl(Game.player.iCivID)) {
                        Game.menuManager.addToastInsufficient(Game.lang.get("MaximumLevel") + ": ", "" + Game.getCiv(Game.player.iCivID).getMilitaryAcademyForGeneralsLevel() + " / " + Game.getMilitaryAcademyForGenerals_MaxLvl(Game.player.iCivID), Images.general);
                    }
                    else if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 3) {
                        Game.menuManager.setVisibleInGame_PopUp(false);
                    }
                    else {
                        Game.menuManager.rebuildInGame_UpgradeMilitaryAcademyForGenerals();
                    }
                }
                else {
                    Game.menuManager.rebuildInGame_Civ_MilitaryAcademy();
                    if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_CIV_POPULATION_HOVER) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_DIPLOMACY);
                    }
                }
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new TextIcon2_Horizontal("" + CFG.getPrecision2(Game.getCiv(InGame_Civ.iActiveCivID).getAggressiveExpansion(), (Game.getCiv(InGame_Civ.iActiveCivID).getAggressiveExpansion() < 100.0f) ? 10 : 1), Images.aggressiveExpansion, buttonX, buttonY, tWidth, statsH2, maxIconWidth) {
            float lastValue = 0.0f;
            
            @Override
            public String getTextToDraw() {
                if (this.lastValue != Game.getCiv(InGame_Civ.iActiveCivID).getAggressiveExpansion()) {
                    this.lastValue = Game.getCiv(InGame_Civ.iActiveCivID).getAggressiveExpansion();
                    this.setText("" + CFG.getPrecision2(Game.getCiv(InGame_Civ.iActiveCivID).getAggressiveExpansion(), (Game.getCiv(InGame_Civ.iActiveCivID).getAggressiveExpansion() < 100.0f) ? 10 : 1));
                }
                return this.sText;
            }
            
            @Override
            public void buildElementHover() {
                if (Game.getCiv(InGame_Civ.iActiveCivID).getCapitalProvinceID() >= 0) {
                    this.menuElementHover = InGame_Civ.getHover_AggressiveExpansion(InGame_Civ.iActiveCivID, true);
                }
                else {
                    this.menuElementHover = null;
                }
            }
            
            @Override
            public void actionElement() {
                Game.menuManager.rebuildInGame_Civ_AggressiveExpansion();
                if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_CIV_POPULATION_HOVER) {
                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DIPLOMACY);
                }
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new TextIcon2_Horizontal("" + CFG.getPrecision2(Game.getCiv(InGame_Civ.iActiveCivID).getWarWeariness(), 10) + "%", Images.weariness, buttonX, buttonY, tWidth, statsH2, maxIconWidth) {
            float lastValue = 0.0f;
            
            @Override
            public String getTextToDraw() {
                if (this.lastValue != Game.getCiv(InGame_Civ.iActiveCivID).getWarWeariness()) {
                    this.lastValue = Game.getCiv(InGame_Civ.iActiveCivID).getWarWeariness();
                    this.setText(CFG.getPrecision2(Game.getCiv(InGame_Civ.iActiveCivID).getWarWeariness(), 1) + "%");
                }
                return this.sText;
            }
            
            @Override
            public void buildElementHover() {
                if (Game.getCiv(InGame_Civ.iActiveCivID).getCapitalProvinceID() >= 0) {
                    this.menuElementHover = InGame_Civ.getHover_WarWeariness(InGame_Civ.iActiveCivID, true);
                }
                else {
                    this.menuElementHover = null;
                }
            }
            
            @Override
            public void actionElement() {
                Game.menuManager.rebuildInGame_Civ_AggressiveExpansion();
                if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_CIV_POPULATION_HOVER) {
                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DIPLOMACY);
                }
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        menuElements.add(new TextIcon2_Horizontal("" + BattleManager.getBattleWidth(InGame_Civ.iActiveCivID), Images.battleWidth, buttonX, buttonY, tWidth, statsH2, maxIconWidth) {
            @Override
            public void buildElementHover() {
                if (Game.getCiv(InGame_Civ.iActiveCivID).getCapitalProvinceID() >= 0) {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("BattleWidth") + ": ", "" + BattleManager.getBattleWidth(InGame_Civ.iActiveCivID) + " / " + GameValues.battle.BATTLE_MAX_BATTLE_WIDTH, Images.battleWidth, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
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
                else {
                    this.menuElementHover = null;
                }
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
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        buttonY += statsH2 + CFG.PADDING;
        buttonX = paddingLeft;
        if (CFG.debugMode) {
            final List<MenuElement> debugList = InGame_Civ_AI.getMenuElements(InGame_Civ.iActiveCivID, buttonY, menuWidth);
            for (int i = 0, iSize = debugList.size(); i < iSize; ++i) {
                menuElements.add(debugList.get(i));
                buttonY = Math.max(buttonY, debugList.get(i).getPosY() + debugList.get(i).getHeight() + CFG.PADDING);
            }
        }
        if (InGame_Civ.iActiveCivID == Game.player.iCivID) {
            menuElements.add(new Text_Title_Diplomacy(Game.lang.get("Diplomacy"), Images.boxTitleBORDERWIDTH, buttonY, (menuWidth - Images.boxTitleBORDERWIDTH * 2) / 2, CFG.BUTTON_HEIGHT4, InGame_Civ.diplomacyMode) {
                @Override
                public void actionElement() {
                    InGame_Civ.diplomacyMode = !InGame_Civ.diplomacyMode;
                    if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_DIPLOMACY) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_DIPLOMACY);
                    }
                    InGame_Civ.iRebuildToCivID = InGame_Civ.iActiveCivID;
                    Game.menuManager.rebuildInGame_Civ(true);
                    InGame_Civ.lTime = 0L;
                }
                
                @Override
                public int getSFX() {
                    return Game.soundsManager.getTab();
                }
            });
            menuElements.add(new Text_Title_Diplomacy(Game.lang.get("Civilizations"), Images.boxTitleBORDERWIDTH + (menuWidth - Images.boxTitleBORDERWIDTH * 2) / 2, buttonY, (menuWidth - Images.boxTitleBORDERWIDTH * 2) / 2, CFG.BUTTON_HEIGHT4, !InGame_Civ.diplomacyMode) {
                @Override
                public void actionElement() {
                    InGame_Civ.diplomacyMode = !InGame_Civ.diplomacyMode;
                    if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_DIPLOMACY) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_DIPLOMACY);
                    }
                    InGame_Civ.iRebuildToCivID = InGame_Civ.iActiveCivID;
                    Game.menuManager.rebuildInGame_Civ(true);
                    InGame_Civ.lTime = 0L;
                }
                
                @Override
                public int getSFX() {
                    return Game.soundsManager.getTab();
                }
            });
        }
        else {
            final int menuElementWidth = (menuWidth - Images.boxTitleBORDERWIDTH * 2) / 3;
            menuElements.add(new Text_Title_Diplomacy(Game.lang.get("Diplomacy"), Images.boxTitleBORDERWIDTH, buttonY, menuElementWidth, CFG.BUTTON_HEIGHT4, InGame_Civ.diplomacyMode || InGame_Civ.iActiveCivID == Game.player.iCivID) {
                @Override
                public void actionElement() {
                    InGame_Civ.diplomacyMode = !InGame_Civ.diplomacyMode;
                    InGame_Civ.TRADE = false;
                    if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_DIPLOMACY) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_DIPLOMACY);
                    }
                    InGame_Civ.iRebuildToCivID = InGame_Civ.iActiveCivID;
                    Game.menuManager.rebuildInGame_Civ(true);
                    InGame_Civ.lTime = 0L;
                }
                
                @Override
                public int getSFX() {
                    return Game.soundsManager.getTab();
                }
            });
            menuElements.add(new Text_Title_Diplomacy(Game.lang.get("Interactions"), Images.boxTitleBORDERWIDTH + menuElementWidth, buttonY, menuElementWidth, CFG.BUTTON_HEIGHT4, !InGame_Civ.diplomacyMode && InGame_Civ.iActiveCivID != Game.player.iCivID) {
                @Override
                public void actionElement() {
                    InGame_Civ.diplomacyMode = !InGame_Civ.diplomacyMode;
                    InGame_Civ.TRADE = false;
                    if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_DIPLOMACY) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_DIPLOMACY);
                    }
                    InGame_Civ.iRebuildToCivID = InGame_Civ.iActiveCivID;
                    Game.menuManager.rebuildInGame_Civ(true);
                    InGame_Civ.lTime = 0L;
                }
                
                @Override
                public int getSFX() {
                    return Game.soundsManager.getTab();
                }
            });
            menuElements.add(new Text_Title_Diplomacy(Game.lang.get("Trade"), Images.boxTitleBORDERWIDTH + menuElementWidth * 2, buttonY, menuElementWidth, CFG.BUTTON_HEIGHT4, true) {
                @Override
                public void actionElement() {
                    InGame_Civ.TRADE = true;
                }
                
                @Override
                public int getSFX() {
                    return Game.soundsManager.getTab();
                }
            });
        }
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        final int leftW = menuWidth - paddingLeft * 2 - ButtonFlag_Diplomacy.getButtonWidth() * 4 - CFG.PADDING * 4;
        final int lineH = ImageManager.getImage(Images.flagDiplomacyOver).getHeight() + CFG.PADDING * 2;
        final int maxIconW = ImageManager.getImage(Images.relations).getWidth() + CFG.PADDING * 2;
        final int tOpinion = (int)Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.getRelation(Game.player.iCivID);
        final int tOpinionW = menuWidth - paddingLeft * 2 - leftW;
        if (InGame_Civ.iActiveCivID != Game.player.iCivID) {
            menuElements.add(new Text_Static_Opinion(((tOpinion > 0) ? "+" : "") + tOpinion, CFG.FONT_REGULAR_SMALL, -1, paddingLeft + leftW, buttonY, tOpinionW / 2, lineH, tOpinion, false) {
                @Override
                public void buildElementHover() {
                    this.menuElementHover = InGame_Civ.getHoverBetweenCivilizations(InGame_Civ.iActiveCivID, Game.player.iCivID, false, false);
                }
                
                @Override
                public void actionElement() {
                    InGame_Civ.diplomacyMode = !InGame_Civ.diplomacyMode;
                    if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_DIPLOMACY) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_DIPLOMACY);
                    }
                    InGame_Civ.iRebuildToCivID = InGame_Civ.iActiveCivID;
                    Game.menuManager.rebuildInGame_Civ(true);
                    InGame_Civ.lTime = 0L;
                }
                
                @Override
                public int getSFX() {
                    return Game.soundsManager.getTab();
                }
            });
            menuElements.add(new Text_Static_Opinion(DiplomacyManager.getOpinion_String(tOpinion), CFG.FONT_REGULAR_SMALL, -1, paddingLeft + leftW + tOpinionW / 2, buttonY, tOpinionW / 2, lineH, tOpinion, true) {
                @Override
                public void buildElementHover() {
                    this.menuElementHover = InGame_Civ.getHoverBetweenCivilizations(InGame_Civ.iActiveCivID, Game.player.iCivID, false, false);
                }
                
                @Override
                public void actionElement() {
                    InGame_Civ.diplomacyMode = !InGame_Civ.diplomacyMode;
                    if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_DIPLOMACY) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_DIPLOMACY);
                    }
                    InGame_Civ.iRebuildToCivID = InGame_Civ.iActiveCivID;
                    Game.menuManager.rebuildInGame_Civ(true);
                    InGame_Civ.lTime = 0L;
                }
                
                @Override
                public int getSFX() {
                    return Game.soundsManager.getTab();
                }
            });
            menuElements.add(new TextIcon2_HorizontalSplit(Game.lang.get("Opinion"), Images.relations, paddingLeft, buttonY, leftW, lineH, maxIconW, menuWidth - paddingLeft * 2) {
                @Override
                public void actionElement() {
                    InGame_Civ.diplomacyMode = !InGame_Civ.diplomacyMode;
                    if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_DIPLOMACY) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_DIPLOMACY);
                    }
                    InGame_Civ.iRebuildToCivID = InGame_Civ.iActiveCivID;
                    Game.menuManager.rebuildInGame_Civ(true);
                    InGame_Civ.lTime = 0L;
                }
                
                @Override
                public int getSFX() {
                    return Game.soundsManager.getTab();
                }
            });
            buttonY += lineH;
        }
        for (int j = 0; j < Game.getCiv(InGame_Civ.iActiveCivID).inAlliance.size(); ++j) {
            try {
                buttonX = paddingLeft + leftW + CFG.PADDING;
                menuElements.add(new ButtonFlag_Diplomacy_Alliance(Game.getCiv(InGame_Civ.iActiveCivID).inAlliance.get(j), paddingLeft + leftW + CFG.PADDING, buttonY + CFG.PADDING, true));
                menuElements.add(new TextIcon2_HorizontalSplit(Game.lang.get(Game.alliancesSpecial.get(Game.getCiv(InGame_Civ.iActiveCivID).inAlliance.get(j)).Name_Alliance), Images.alliance, paddingLeft, buttonY, leftW, lineH, maxIconW, menuWidth - paddingLeft * 2) {
                    int id = 0;
                    
                    @Override
                    public void setCurrent(final int nCurrent) {
                        this.id = nCurrent;
                    }
                    
                    @Override
                    public void actionElement() {
                        InGame_Civ_List.civsList.clear();
                        InGame_Civ_List.civsListTitle = Game.lang.get(Game.alliancesSpecial.get(Game.getCiv(InGame_Civ.iActiveCivID).inAlliance.get(this.id)).Name_Alliance);
                        InGame_Civ_List.civsListTitle2 = Game.getCiv(InGame_Civ.iActiveCivID).getCivName();
                        InGame_Civ_List.civsList.add(Game.alliancesSpecial.get(Game.getCiv(InGame_Civ.iActiveCivID).inAlliance.get(this.id)).iLeaderCivID);
                        for (int i = Game.alliancesSpecial.get(Game.getCiv(InGame_Civ.iActiveCivID).inAlliance.get(this.id)).firstTier.size() - 1; i >= 0; --i) {
                            InGame_Civ_List.addCiv(Game.alliancesSpecial.get(Game.getCiv(InGame_Civ.iActiveCivID).inAlliance.get(this.id)).firstTier.get(i));
                        }
                        for (int i = Game.alliancesSpecial.get(Game.getCiv(InGame_Civ.iActiveCivID).inAlliance.get(this.id)).secondTier.size() - 1; i >= 0; --i) {
                            InGame_Civ_List.addCiv(Game.alliancesSpecial.get(Game.getCiv(InGame_Civ.iActiveCivID).inAlliance.get(this.id)).secondTier.get(i));
                        }
                        InGame_Civ_List.addCiv(InGame_Civ.iActiveCivID);
                        Game.menuManager.rebuildInGame_Civ_List();
                    }
                    
                    @Override
                    public int getSFX() {
                        return Game.soundsManager.getTab();
                    }
                });
                menuElements.get(menuElements.size() - 1).setCurrent(j);
                buttonY += Math.max(lineH, menuElements.get(menuElements.size() - 1).getHeight());
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
        buttonX = paddingLeft + leftW + CFG.PADDING;
        if (Game.getCiv(InGame_Civ.iActiveCivID).getPuppetOfCivID() != Game.getCiv(InGame_Civ.iActiveCivID).getCivID()) {
            buttonX = paddingLeft + leftW + CFG.PADDING;
            menuElements.add(new ButtonFlag_Diplomacy(Game.getCiv(InGame_Civ.iActiveCivID).getPuppetOfCivID(), paddingLeft + leftW + CFG.PADDING, buttonY + CFG.PADDING, true, true, false));
            if (InGame_Civ.iActiveCivID != Game.player.iCivID) {
                final int libertyWidth = menuWidth - paddingLeft - (paddingLeft + leftW + CFG.PADDING * 2 + ButtonFlag_Diplomacy.getButtonWidth());
                menuElements.add(new Text_Static("" + CFG.getPrecision2(Game.getCiv(Game.getCiv(InGame_Civ.iActiveCivID).getPuppetOfCivID()).diplomacy.getVassal_LibertyDesire(InGame_Civ.iActiveCivID), 10) + "%", CFG.FONT_REGULAR_SMALL, -1, paddingLeft + leftW + CFG.PADDING * 2 + ButtonFlag_Diplomacy.getButtonWidth(), buttonY, libertyWidth, lineH) {
                    @Override
                    public String getTextToDraw() {
                        return this.sText;
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.getCiv(InGame_Civ.iActiveCivID).getCivName(), "", InGame_Civ.iActiveCivID, CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("LibertyDesire") + ": ", CFG.getPrecision2(Game.getCiv(Game.getCiv(InGame_Civ.iActiveCivID).getPuppetOfCivID()).diplomacy.getVassal_LibertyDesire(InGame_Civ.iActiveCivID), 10) + "%", Images.revolutionRisk, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
            }
            menuElements.add(new TextIcon2_HorizontalSplit(Game.lang.get(Game_Ages.getLord()), Images.vassal, paddingLeft, buttonY, leftW, lineH, maxIconW, menuWidth - paddingLeft * 2) {
                @Override
                public void actionElement() {
                    InGame_Civ_List.civsList.clear();
                    InGame_Civ_List.civsListTitle = Game.lang.get(Game_Ages.getLord());
                    InGame_Civ_List.civsListTitle2 = Game.getCiv(InGame_Civ.iActiveCivID).getCivName();
                    InGame_Civ_List.civsList.add(InGame_Civ.iActiveCivID);
                    InGame_Civ_List.civsList.add(Game.getCiv(InGame_Civ.iActiveCivID).getPuppetOfCivID());
                    Game.menuManager.rebuildInGame_Civ_List();
                }
            });
            buttonY += lineH;
        }
        if (InGame_Civ.iActiveCivID == Game.player.iCivID && !InGame_Civ.diplomacyMode) {
            buttonY += CFG.PADDING;
            final int buttonW = (menuWidth - paddingLeft * 2 - CFG.PADDING * 3) / 4;
            final int buttonH = (int)(buttonW * 1.1f);
            buttonX = paddingLeft;
            if (!InGame_Civ.relationsMode) {
                final List<MenuElement> tempElements = new ArrayList<MenuElement>();
                if (Game.getCiv(Game.player.iCivID).getNumOfProvinces() <= 0) {
                    tempElements.add(new ButtonDiplomacy(Game.lang.get("Provinces") + ": " + Game.getCiv(Game.player.iCivID).getNumOfProvinces(), Images.provinces, buttonX, buttonY, menuWidth - paddingLeft * 2, buttonH) {
                        @Override
                        public void actionElement() {
                        }
                        
                        @Override
                        public Color getColorHover1() {
                            return new Color(DiplomacyManager.COLOR_RED.r, DiplomacyManager.COLOR_RED.g, DiplomacyManager.COLOR_RED.b, 0.15f);
                        }
                        
                        @Override
                        public Color getColorHover2() {
                            return new Color(DiplomacyManager.COLOR_RED.r, DiplomacyManager.COLOR_RED.g, DiplomacyManager.COLOR_RED.b, 0.5f);
                        }
                        
                        @Override
                        public void buildElementHover() {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.getCiv(Game.player.iCivID).getCivName(), "", Game.player.iCivID, CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Provinces") + ": ", CFG.FONT_BOLD, Colors.HOVER_LEFT));
                            nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.getCiv(Game.player.iCivID).getNumOfProvinces(), CFG.FONT_BOLD, Colors.HOVER_NEGATIVE));
                            nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.provinces, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                        
                        @Override
                        public boolean getIsHovered() {
                            return true;
                        }
                        
                        @Override
                        public int getSFX() {
                            return SoundsManager.SOUND_CLICK_TOP;
                        }
                    });
                }
                else {
                    tempElements.add(new ButtonDiplomacy(Game.lang.get("ImproveRelations"), Images.relationsUp, buttonX, buttonY, buttonW, buttonH) {
                        @Override
                        public void buildElementHover() {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ImproveRelations"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.relationsUp, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("SelectOnMap"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                        
                        @Override
                        public void actionElement() {
                            if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DIPLOMACY_IMPROVE_RELATIONS) {
                                Game.mapModes.setActiveViewID(Game.mapModes.MODE_DIPLOMACY);
                            }
                            else {
                                Game.mapModes.setActiveViewID(Game.mapModes.MODE_DIPLOMACY_IMPROVE_RELATIONS);
                            }
                            InGame_Civ.iRebuildToCivID = InGame_Civ.iActiveCivID;
                            Game.menuManager.rebuildInGame_Civ(true);
                            InGame_Civ.lTime = 0L;
                        }
                        
                        @Override
                        public Color getColorHover1() {
                            return new Color(DiplomacyManager.COLOR_GREEN.r, DiplomacyManager.COLOR_GREEN.g, DiplomacyManager.COLOR_GREEN.b, 0.15f);
                        }
                        
                        @Override
                        public Color getColorHover2() {
                            return new Color(DiplomacyManager.COLOR_GREEN.r, DiplomacyManager.COLOR_GREEN.g, DiplomacyManager.COLOR_GREEN.b, 0.5f);
                        }
                        
                        @Override
                        public boolean getIsHovered() {
                            return super.getIsHovered() || Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DIPLOMACY_IMPROVE_RELATIONS;
                        }
                        
                        @Override
                        public int getSFX() {
                            return SoundsManager.SOUND_CLICK_TOP;
                        }
                    });
                    tempElements.add(new ButtonDiplomacy(Game.lang.get("DamageRelations"), Images.relationsDown, buttonX, buttonY, buttonW, buttonH) {
                        @Override
                        public void actionElement() {
                            if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DIPLOMACY_DAMAGE_RELATIONS) {
                                Game.mapModes.setActiveViewID(Game.mapModes.MODE_DIPLOMACY);
                            }
                            else {
                                Game.mapModes.setActiveViewID(Game.mapModes.MODE_DIPLOMACY_DAMAGE_RELATIONS);
                            }
                            InGame_Civ.iRebuildToCivID = InGame_Civ.iActiveCivID;
                            Game.menuManager.rebuildInGame_Civ(true);
                            InGame_Civ.lTime = 0L;
                        }
                        
                        @Override
                        public void buildElementHover() {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("DamageRelations"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.relationsDown, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("SelectOnMap"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                        
                        @Override
                        public Color getColorHover1() {
                            return new Color(DiplomacyManager.COLOR_RED.r, DiplomacyManager.COLOR_RED.g, DiplomacyManager.COLOR_RED.b, 0.15f);
                        }
                        
                        @Override
                        public Color getColorHover2() {
                            return new Color(DiplomacyManager.COLOR_RED.r, DiplomacyManager.COLOR_RED.g, DiplomacyManager.COLOR_RED.b, 0.5f);
                        }
                        
                        @Override
                        public boolean getIsHovered() {
                            return super.getIsHovered() || Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DIPLOMACY_DAMAGE_RELATIONS;
                        }
                        
                        @Override
                        public int getSFX() {
                            return SoundsManager.SOUND_CLICK_TOP;
                        }
                    });
                    tempElements.add(new ButtonDiplomacy(Game.lang.get("Rivals"), Images.rivals, buttonX, buttonY, buttonW, buttonH) {
                        @Override
                        public void actionElement() {
                            InGame_Civ.actionRivals();
                        }
                        
                        @Override
                        public void buildElementHover() {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ChooseYourRivals"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.rivals, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Rivals") + ": ", "" + Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.rivals.size() + " / " + GameValues.rivals.RIVALS_LIMIT, Images.rivals, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Line());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", "+" + CFG.getPrecision2((float)(Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.rivals.size() * GameValues.rivals.RIVAL_MANPOWER_MIN), 1), Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.rivals.isEmpty() ? Colors.HOVER_NEUTRAL : Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyLegacy") + ": ", "+" + CFG.getPrecision2(Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.rivals.size() * GameValues.rivals.RIVAL_LEGACY_MIN, 100), Images.legacy, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.rivals.isEmpty() ? Colors.HOVER_NEUTRAL : Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            if (!Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.rivals.isEmpty()) {
                                nData.add(new MenuElement_HoverElement_Type_Line());
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                if (!Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.rivals.isEmpty()) {
                                    final Iterator<Map.Entry<Integer, Diplomacy.DiplomacyData>> it = Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.rivals.entrySet().iterator();
                                    while (it.hasNext()) {
                                        final Diplomacy.DiplomacyData tData = (Diplomacy.DiplomacyData)it.next().getValue();
                                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.getCiv(tData.iCivID).getCivName(), "", tData.iCivID, CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
                                        nElements.add(new MenuElement_HoverElement(nData));
                                        nData.clear();
                                    }
                                }
                            }
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                        
                        @Override
                        public Color getColorHover1() {
                            return new Color(DiplomacyManager.COLOR_RED.r, DiplomacyManager.COLOR_RED.g, DiplomacyManager.COLOR_RED.b, 0.15f);
                        }
                        
                        @Override
                        public Color getColorHover2() {
                            return new Color(DiplomacyManager.COLOR_RED.r, DiplomacyManager.COLOR_RED.g, DiplomacyManager.COLOR_RED.b, 0.5f);
                        }
                        
                        @Override
                        public int getSFX() {
                            return SoundsManager.SOUND_CLICK_TOP;
                        }
                    });
                    tempElements.add(new ButtonDiplomacy(Game.lang.get("FormAlliance"), Images.alliance, buttonX, buttonY, buttonW, buttonH) {
                        @Override
                        public void actionElement() {
                            if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 47) {
                                Game.menuManager.setVisibleInGame_PopUp(false);
                            }
                            else {
                                Game.menuManager.rebuildInGame_AllianceList();
                            }
                        }
                        
                        @Override
                        public Color getColorHover1() {
                            return new Color(DiplomacyManager.COLOR_ALLIANCE.r, DiplomacyManager.COLOR_ALLIANCE.g, DiplomacyManager.COLOR_ALLIANCE.b, 0.15f);
                        }
                        
                        @Override
                        public Color getColorHover2() {
                            return new Color(DiplomacyManager.COLOR_ALLIANCE.r, DiplomacyManager.COLOR_ALLIANCE.g, DiplomacyManager.COLOR_ALLIANCE.b, 0.5f);
                        }
                        
                        @Override
                        public int getSFX() {
                            return SoundsManager.SOUND_CLICK_TOP;
                        }
                    });
                }
                for (int k = 0, iSize2 = tempElements.size(); k < iSize2; ++k) {
                    tempElements.get(k).setPosX(buttonX);
                    tempElements.get(k).setPosY(buttonY);
                    buttonX += tempElements.get(k).getWidth() + CFG.PADDING;
                    menuElements.add(tempElements.get(k));
                    if ((k + 1) % 4 == 0 || k == iSize2 - 1) {
                        buttonY += tempElements.get(k).getHeight() + CFG.PADDING;
                        buttonX = paddingLeft;
                    }
                }
                tempElements.clear();
                if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DIPLOMACY_IMPROVE_RELATIONS) {
                    menuElements.add(new Text_StaticBG(Game.lang.get("ChooseAProvince"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2));
                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DIPLOMACY_DAMAGE_RELATIONS) {
                    menuElements.add(new Text_StaticBG(Game.lang.get("ChooseAProvince"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2));
                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                if (!GameValues.diplomacy.SHOW_LIST_OF_CIVS_RELATIONS_IN_PLAYERS_CIV_VIEW) {
                    menuElements.add(new ButtonStatsRectIMG_Active_Click(Game.lang.get("Relations"), Images.relations, paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, CFG.BUTTON_HEIGHT3, maxIconW, 0, true) {
                        @Override
                        public void actionElement() {
                            InGame_Civ.relationsMode = !InGame_Civ.relationsMode;
                            InGame_Civ.iRebuildToCivID = InGame_Civ.iActiveCivID;
                            Game.menuManager.rebuildInGame_Civ(true);
                            InGame_Civ.lTime = 0L;
                        }
                    });
                    menuElements.add(new ButtonStatsRectIMG_Active_Click(Game.lang.get("Government"), Images.government, paddingLeft + CFG.PADDING + (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, CFG.BUTTON_HEIGHT3, maxIconW, 0, true) {
                        @Override
                        public void actionElement() {
                            Game.menuManager.setVisibleInGame_Civ(false);
                            InGame_Court.iActiveCivID = Game.player.iCivID;
                            InGame_CourtOptions.iActiveID = InGame_CourtOptions.iGovernmentID;
                            InGame_CourtOptions2.disableAllViews();
                            Game.menuManager.rebuildInGame_Government();
                            Game.menuManager.setVisibleInGame_Court(true);
                            InGame_Court.lTime = 0L;
                        }
                    });
                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                menuElements.add(new Text_Title_v2Center(Game.lang.get("FormableCivilizations"), -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                if (!Game.getCiv(Game.player.iCivID).sTagsCanForm.isEmpty()) {
                    buttonX = paddingLeft2;
                    try {
                        for (int k = 0; k < Game.getCiv(Game.player.iCivID).sTagsCanForm.size(); ++k) {
                            buttonY += CFG.PADDING;
                            menuElements.add(new ButtonFlag_Formable(k, buttonX, buttonY) {
                                @Override
                                public void buildElementHover() {
                                    this.menuElementHover = FormableCivManager.getHover(this.getCurrent());
                                }
                                
                                @Override
                                public void actionElement() {
                                    Game.menuManager.setVisibleInGame_Civ(false);
                                    Game.menuManager.setVisibleInGame_CivBonuses(false);
                                    InGame_FormCiv.GO_BACK_TO_COURT = false;
                                    Game.menuManager.rebuildInGame_FormCiv(this.getCurrent());
                                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_FORM_CIV);
                                }
                            });
                            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                            final int bHeight = (ButtonFlag_Formable.getButtonHeight() - CFG.PADDING) / 2;
                            menuElements.add(new Text_StaticBG_RulerTitle(Game.lang.getCiv(Game.getCiv(Game.player.iCivID).sTagsCanForm.get(k)), buttonX, buttonY, menuWidth - buttonX - paddingLeft2, bHeight) {
                                int id;
                                
                                @Override
                                public int getCurrent() {
                                    return this.id;
                                }
                                
                                @Override
                                public void setCurrent(final int nCurrent) {
                                    this.id = nCurrent;
                                }
                                
                                @Override
                                public void actionElement() {
                                    Game.menuManager.setVisibleInGame_Civ(false);
                                    Game.menuManager.setVisibleInGame_CivBonuses(false);
                                    InGame_FormCiv.GO_BACK_TO_COURT = false;
                                    Game.menuManager.rebuildInGame_FormCiv(this.getCurrent());
                                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_FORM_CIV);
                                }
                                
                                @Override
                                public void buildElementHover() {
                                    this.menuElementHover = FormableCivManager.getHover(this.getCurrent());
                                }
                            });
                            menuElements.get(menuElements.size() - 1).setCurrent(k);
                            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Provinces") + ": ", Game.player.formableCivs.get(k).getControlledProvinces(Game.player.iCivID) + " / " + Game.player.formableCivs.get(k).getProvincesSize_WithoutNeutral(), Images.provinces, buttonX, buttonY + bHeight + CFG.PADDING, menuWidth - buttonX - paddingLeft2, bHeight, maxIconW) {
                                int id;
                                
                                @Override
                                public int getCurrent() {
                                    return this.id;
                                }
                                
                                @Override
                                public void setCurrent(final int nCurrent) {
                                    this.id = nCurrent;
                                }
                                
                                @Override
                                public void actionElement() {
                                    Game.menuManager.setVisibleInGame_Civ(false);
                                    Game.menuManager.setVisibleInGame_CivBonuses(false);
                                    InGame_FormCiv.GO_BACK_TO_COURT = false;
                                    Game.menuManager.rebuildInGame_FormCiv(this.getCurrent());
                                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_FORM_CIV);
                                }
                                
                                @Override
                                public void buildElementHover() {
                                    this.menuElementHover = FormableCivManager.getHover(this.getCurrent());
                                }
                                
                                @Override
                                public Color getColorBonus() {
                                    return Colors.HOVER_GOLD;
                                }
                            });
                            menuElements.get(menuElements.size() - 1).setCurrent(k);
                            buttonX = paddingLeft2;
                            buttonY += ButtonFlag_Formable.getButtonHeight() + CFG.PADDING;
                            menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(paddingLeft, buttonY - (ButtonFlag_Formable.getButtonHeight() + CFG.PADDING * 2), menuWidth - paddingLeft * 2, ButtonFlag_Formable.getButtonHeight() + CFG.PADDING * 2));
                            buttonY += CFG.PADDING;
                        }
                    }
                    catch (final Exception ex2) {
                        CFG.exceptionStack(ex2);
                    }
                    buttonX = paddingLeft;
                }
                else {
                    menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4));
                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
            }
            else if (!GameValues.diplomacy.SHOW_LIST_OF_CIVS_RELATIONS_IN_PLAYERS_CIV_VIEW) {
                menuElements.add(new ButtonStatsRectIMG_Active_Click(Game.lang.get("Back"), Images.relations, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT3, maxIconW, 0, true) {
                    @Override
                    public void actionElement() {
                        InGame_Civ.relationsMode = false;
                        InGame_Civ.iRebuildToCivID = InGame_Civ.iActiveCivID;
                        Game.menuManager.rebuildInGame_Civ(true);
                        InGame_Civ.lTime = 0L;
                    }
                });
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
        }
        if (InGame_Civ.TRADE) {
            buttonY += CFG.PADDING;
            final int buttonW = (menuWidth - paddingLeft * 2 - CFG.PADDING * 3) / 4;
            final int buttonH = (int)(buttonW * 1.1f);
            buttonX = paddingLeft;
            final List<MenuElement> tempElements = new ArrayList<MenuElement>();
            tempElements.add(new ButtonDiplomacy(Game.lang.get("SendGift"), Images.gift, buttonX, buttonY, buttonW, buttonH) {
                @Override
                public void actionElement() {
                    if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 22 && InGame_SendGift.iCivID == InGame_Civ.iActiveCivID) {
                        Game.menuManager.setVisibleInGame_PopUp(false);
                    }
                    else {
                        Game.menuManager.rebuildInGame_SendGift(InGame_Civ.iActiveCivID);
                    }
                }
                
                @Override
                public Color getColorHover1() {
                    return new Color(Colors.HOVER_GOLD.r, Colors.HOVER_GOLD.g, Colors.HOVER_GOLD.b, 0.15f);
                }
                
                @Override
                public Color getColorHover2() {
                    return new Color(Colors.HOVER_GOLD.r, Colors.HOVER_GOLD.g, Colors.HOVER_GOLD.b, 0.5f);
                }
                
                @Override
                public int getSFX() {
                    return SoundsManager.SOUND_CLICK_TOP;
                }
            });
        }
        else if ((InGame_Civ.diplomacyMode && !InGame_Civ.TRADE) || InGame_Civ.iActiveCivID == Game.player.iCivID) {
            if (InGame_Civ.iActiveCivID == Game.player.iCivID && !InGame_Civ.diplomacyMode) {
                if (GameValues.diplomacy.SHOW_LIST_OF_CIVS_RELATIONS_IN_PLAYERS_CIV_VIEW || InGame_Civ.relationsMode) {
                    int r0W0 = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.175f);
                    int r0W2 = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.45f);
                    int r1W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.375f);
                    buttonX = Images.boxTitleBORDERWIDTH;
                    menuElements.add(new Text_TitleBlueSort(InGame_Civ.iSortID == 0 || InGame_Civ.iSortID == 1, InGame_Civ.iSortID == 1, Game.lang.get("Ranking"), -1, buttonX, buttonY, r0W0, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
                        @Override
                        public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
                        }
                        
                        @Override
                        public void actionElement() {
                            if (InGame_Civ.iSortID == 0) {
                                InGame_Civ.iSortID = 1;
                            }
                            else {
                                InGame_Civ.iSortID = 0;
                            }
                            InGame_Civ.relationsMode = true;
                            InGame_Civ.iRebuildToCivID = InGame_Civ.iActiveCivID;
                            Game.menuManager.rebuildInGame_Civ(true);
                            InGame_Civ.lTime = 0L;
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
                    menuElements.add(new Text_TitleBlueSort(InGame_Civ.iSortID == 2 || InGame_Civ.iSortID == 3, InGame_Civ.iSortID == 3, Game.lang.get("Name"), -1, buttonX, buttonY, r0W2, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
                        @Override
                        public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
                        }
                        
                        @Override
                        public void actionElement() {
                            if (InGame_Civ.iSortID == 2) {
                                InGame_Civ.iSortID = 3;
                            }
                            else {
                                InGame_Civ.iSortID = 2;
                            }
                            InGame_Civ.relationsMode = true;
                            InGame_Civ.iRebuildToCivID = InGame_Civ.iActiveCivID;
                            Game.menuManager.rebuildInGame_Civ(true);
                            InGame_Civ.lTime = 0L;
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
                    menuElements.add(new Text_TitleBlueSort(InGame_Civ.iSortID == 4 || InGame_Civ.iSortID == 5, InGame_Civ.iSortID == 5, Game.lang.get("Opinion"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
                        @Override
                        public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
                        }
                        
                        @Override
                        public void actionElement() {
                            if (InGame_Civ.iSortID == 4) {
                                InGame_Civ.iSortID = 5;
                            }
                            else {
                                InGame_Civ.iSortID = 4;
                            }
                            InGame_Civ.relationsMode = true;
                            InGame_Civ.iRebuildToCivID = InGame_Civ.iActiveCivID;
                            Game.menuManager.rebuildInGame_Civ(true);
                            InGame_Civ.lTime = 0L;
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
                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                    buttonX = paddingLeft;
                    final List<Integer> tCivs = new ArrayList<Integer>();
                    final List<Float> tCivOpinion = new ArrayList<Float>();
                    for (int l = 1; l < Game.getCivsSize(); ++l) {
                        if (Game.getCiv(l).getNumOfProvinces() > 0 && l != InGame_Civ.iActiveCivID) {
                            tCivs.add(l);
                            tCivOpinion.add(Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.getRelation(l));
                        }
                    }
                    r0W0 = (int)((menuWidth - paddingLeft * 2 - CFG.PADDING * 2) * 0.175f);
                    r0W2 = (int)((menuWidth - paddingLeft * 2 - CFG.PADDING * 2) * 0.45f);
                    r1W = (int)((menuWidth - paddingLeft * 2 - CFG.PADDING * 2) * 0.375f);
                    final int buttonH2 = CFG.isDesktop() ? CFG.BUTTON_HEIGHT3 : CFG.BUTTON_HEIGHT2;
                    while (!tCivs.isEmpty()) {
                        int toAddID = 0;
                        if (InGame_Civ.iSortID == 0) {
                            for (int o = 1; o < tCivs.size(); ++o) {
                                if (Game.getCiv(tCivs.get(toAddID)).iCivRankPosition > Game.getCiv(tCivs.get(o)).iCivRankPosition) {
                                    toAddID = o;
                                }
                            }
                        }
                        else if (InGame_Civ.iSortID == 1) {
                            for (int o = 1; o < tCivs.size(); ++o) {
                                if (Game.getCiv(tCivs.get(toAddID)).iCivRankPosition < Game.getCiv(tCivs.get(o)).iCivRankPosition) {
                                    toAddID = o;
                                }
                            }
                        }
                        else if (InGame_Civ.iSortID == 2) {
                            for (int o = 1; o < tCivs.size(); ++o) {
                                if (CFG.compareAlphabetic_TwoString(Game.getCiv(tCivs.get(toAddID)).getCivName(), Game.getCiv(tCivs.get(o)).getCivName())) {
                                    toAddID = o;
                                }
                            }
                        }
                        else if (InGame_Civ.iSortID == 3) {
                            for (int o = 1; o < tCivs.size(); ++o) {
                                if (CFG.compareAlphabetic_TwoString(Game.getCiv(tCivs.get(o)).getCivName(), Game.getCiv(tCivs.get(toAddID)).getCivName())) {
                                    toAddID = o;
                                }
                            }
                        }
                        else if (InGame_Civ.iSortID == 4) {
                            for (int o = 1; o < tCivs.size(); ++o) {
                                if (tCivOpinion.get(toAddID) < tCivOpinion.get(o)) {
                                    toAddID = o;
                                }
                            }
                        }
                        else if (InGame_Civ.iSortID == 5) {
                            for (int o = 1; o < tCivs.size(); ++o) {
                                if (tCivOpinion.get(toAddID) > tCivOpinion.get(o)) {
                                    toAddID = o;
                                }
                            }
                        }
                        buttonX = paddingLeft;
                        menuElements.add(new ButtonStatsRectIMG_Rank("" + ((Game.getCiv(tCivs.get(toAddID)).iCivRankPosition > 0) ? Integer.valueOf(Game.getCiv(tCivs.get(toAddID)).iCivRankPosition) : "---"), CivilizationRanking.getCivilizationRanking_IMG_STAR_CIVID(tCivs.get(toAddID)), buttonX, buttonY, r0W0, buttonH2, ImageManager.getImage(Images.rankGold).getWidth()) {});
                        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                        menuElements.add(new Text_StaticBG_ID_FlagCiv("" + Game.getCiv(tCivs.get(toAddID)).getCivName(), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, buttonX, buttonY, r0W2, buttonH2, (int)tCivs.get(toAddID)) {
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
                                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                                nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.getCurrent(), DiplomacyManager.getOpinion_String((int)Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.getRelation(this.getCurrent()))));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Prestige") + ": ", "" + CFG.getPrecision2(CivilizationRanking.getCivilizationRanking_INFO(this.getCurrent()), 10), CivilizationRanking.getCivilizationRanking_IMG_STAR_CIVID(this.getCurrent()), CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", CFG.getShortNumber(Game.getCiv(this.getCurrent()).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Economy") + ": ", CFG.getPrecision2(Game.getCiv(this.getCurrent()).getEconomyTotal(), 10), Game_Calendar.IMG_ECONOMY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                this.menuElementHover = new MenuElement_Hover(nElements);
                            }
                        });
                        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                        menuElements.add(new Text_StaticBG_ID_SpecialColor(DiplomacyManager.getOpinion_String((int)(float)tCivOpinion.get(toAddID)), "" + CFG.getPrecision2(tCivOpinion.get(toAddID), 1), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2 + CFG.PADDING / 2, buttonX, buttonY, r1W, buttonH2, (int)tCivs.get(toAddID), DiplomacyManager.getOpinion_Color((int)(float)tCivOpinion.get(toAddID))) {
                            @Override
                            public void actionElement() {
                                if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DIPLOMACY_IMPROVE_RELATIONS) {
                                    final int iActiveCivID = this.getCurrent();
                                    if (iActiveCivID != Game.player.iCivID) {
                                        if (DiplomacyManager.isAtWar(Game.player.iCivID, iActiveCivID)) {
                                            Game.menuManager.addToastNegative("", Game.lang.get("WeAreAtWar"), Images.war);
                                        }
                                        else if (Game.getCiv(Game.player.iCivID).diplomacy.isRival(iActiveCivID) || Game.getCiv(iActiveCivID).diplomacy.isRival(Game.player.iCivID)) {
                                            Game.menuManager.addToastNegative("", Game.lang.get("WeAreRivals"), Images.rivals);
                                        }
                                        else if (Game.getCiv(Game.player.iCivID).diplomacy.isImprovingRelations(iActiveCivID)) {
                                            Game.getCiv(Game.player.iCivID).diplomacy.removeImproveRelations(iActiveCivID);
                                            Game.menuManager.addToastNegative(Game.lang.get("Removed") + ": ", "" + Game.getCiv(iActiveCivID).getCivName(), Images.relationsDown);
                                            ProvinceDraw.addDiplomacyLines(Game.getCiv(iActiveCivID).getCapitalProvinceID(), Game.getCiv(Game.player.iCivID).getCapitalProvinceID(), Colors.HOVER_POSITIVE);
                                        }
                                        else if (Game.getCiv(iActiveCivID).diplomacy.getRelation(Game.player.iCivID) >= GameValues.diplomacy.DIPLOMACY_IMPROVE_RELATIONS_MAX) {
                                            Game.menuManager.addToastNegative(Game.lang.get("MaximumOpinion") + ": ", "" + CFG.getPrecision2(GameValues.diplomacy.DIPLOMACY_IMPROVE_RELATIONS_MAX, 1), Images.relations);
                                        }
                                        else {
                                            Game.getCiv(Game.player.iCivID).diplomacy.addImproveRelations(Game.player.iCivID, iActiveCivID);
                                            Game.menuManager.addToastPositive(Game.lang.get("ImprovingRelations") + ": ", "" + Game.getCiv(iActiveCivID).getCivName(), Images.relationsDown);
                                            ProvinceDraw.addDiplomacyLines(Game.getCiv(Game.player.iCivID).getCapitalProvinceID(), Game.getCiv(iActiveCivID).getCapitalProvinceID(), Colors.HOVER_POSITIVE);
                                        }
                                        Game.menuManager.rebuildInGame_Right();
                                        Game.soundsManager.playSound(SoundsManager.DIPLOMACY_CLICK);
                                    }
                                }
                                else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DIPLOMACY_DAMAGE_RELATIONS) {
                                    final int iActiveCivID = this.getCurrent();
                                    if (iActiveCivID != Game.player.iCivID) {
                                        if (DiplomacyManager.isAtWar(Game.player.iCivID, iActiveCivID)) {
                                            Game.menuManager.addToastNegative("", Game.lang.get("WeAreAtWar"), Images.war);
                                        }
                                        else if (Game.getCiv(Game.player.iCivID).diplomacy.isDamagingRelations(iActiveCivID)) {
                                            Game.getCiv(Game.player.iCivID).diplomacy.removeDamageRelations(iActiveCivID);
                                            Game.menuManager.addToastNegative(Game.lang.get("Removed") + ": ", "" + Game.getCiv(iActiveCivID).getCivName(), Images.relationsDown);
                                            ProvinceDraw.addDiplomacyLines(Game.getCiv(iActiveCivID).getCapitalProvinceID(), Game.getCiv(Game.player.iCivID).getCapitalProvinceID(), Colors.HOVER_POSITIVE);
                                        }
                                        else if (Game.getCiv(iActiveCivID).diplomacy.getRelation(Game.player.iCivID) <= GameValues.diplomacy.DIPLOMACY_DAMAGE_RELATIONS_MAX) {
                                            Game.menuManager.addToastInsufficient(Game.lang.get("MaximumOpinion") + ": ", "" + CFG.getPrecision2(GameValues.diplomacy.DIPLOMACY_DAMAGE_RELATIONS_MAX, 1), Images.relations);
                                        }
                                        else {
                                            Game.getCiv(Game.player.iCivID).diplomacy.addDamageRelations(Game.player.iCivID, iActiveCivID);
                                            ProvinceDraw.addDiplomacyLines(Game.getCiv(Game.player.iCivID).getCapitalProvinceID(), Game.getCiv(iActiveCivID).getCapitalProvinceID(), Colors.HOVER_NEGATIVE);
                                            Game.menuManager.addToastNegative(Game.lang.get("DamagingRelations") + ": ", "" + Game.getCiv(iActiveCivID).getCivName(), Images.relationsDown);
                                        }
                                        Game.menuManager.rebuildInGame_Right();
                                        Game.soundsManager.playSound(SoundsManager.DIPLOMACY_CLICK);
                                    }
                                }
                            }
                            
                            @Override
                            public void buildElementHover() {
                                this.menuElementHover = InGame_Civ.getHoverBetweenCivilizations_RightMenu(InGame_Civ.iActiveCivID, this.getCurrent(), false, false);
                            }
                        });
                        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                        tCivs.remove(toAddID);
                        tCivOpinion.remove(toAddID);
                    }
                }
            }
            else {
                int linesAdded = 0;
                if (Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.iAtWarSize > 0) {
                    buttonX = paddingLeft + leftW + CFG.PADDING;
                    for (int m = 0; m < Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.iAtWarSize; ++m) {
                        try {
                            if (buttonX + ButtonFlag_Diplomacy.getButtonWidth() > menuWidth) {
                                buttonX = paddingLeft + leftW + CFG.PADDING;
                                buttonY += lineH + (CFG.TEXT_HEIGHT + CFG.PADDING * 2);
                                ++linesAdded;
                            }
                            final String warKey = WarManager.getWarKey(InGame_Civ.iActiveCivID, Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.atWar.get(m));
                            String warScore = "";
                            Color textColor;
                            if (warScore != null) {
                                final float tempWarScore = WarManager.lWars.get(warKey).warScore * WarManager.lWars.get(warKey).getWarScore_Side(Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.atWar.get(m));
                                warScore = ((tempWarScore > 0.0f) ? "+" : "") + CFG.getPrecision2(Math.max(Math.min(tempWarScore, 100.0f), -100.0f), 1) + "%";
                                if ((int)tempWarScore == 0) {
                                    textColor = Colors.COLOR_TOP_STATS3;
                                }
                                else if (tempWarScore < 0.0f) {
                                    textColor = Colors.COLOR_TEXT_MODIFIER_NEGATIVE;
                                }
                                else {
                                    textColor = Colors.COLOR_TEXT_MODIFIER_POSITIVE;
                                }
                            }
                            else {
                                warScore = "";
                                textColor = Colors.COLOR_TOP_STATS3;
                            }
                            menuElements.add(new ButtonFlag_DiplomacyWar(Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.atWar.get(m), buttonX, buttonY + CFG.PADDING, true, warScore, textColor, warKey));
                            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                        }
                        catch (final Exception ex3) {
                            CFG.exceptionStack(ex3);
                        }
                    }
                    menuElements.add(new TextIcon2_HorizontalSplit(Game.lang.get("AtWarWith"), Images.war, paddingLeft, buttonY - (lineH + (CFG.TEXT_HEIGHT + CFG.PADDING * 2)) * linesAdded, leftW, (lineH + (CFG.TEXT_HEIGHT + CFG.PADDING * 2)) * (linesAdded + 1), maxIconW, menuWidth - paddingLeft * 2) {
                        @Override
                        public void actionElement() {
                            InGame_Civ_List.civsList.clear();
                            InGame_Civ_List.civsListTitle = Game.lang.get("AtWarWith");
                            InGame_Civ_List.civsListTitle2 = Game.getCiv(InGame_Civ.iActiveCivID).getCivName();
                            InGame_Civ_List.civsList.add(InGame_Civ.iActiveCivID);
                            for (int i = 0; i < Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.iAtWarSize; ++i) {
                                InGame_Civ_List.civsList.add(Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.atWar.get(i));
                            }
                            Game.menuManager.rebuildInGame_Civ_List();
                        }
                    });
                    linesAdded = 0;
                    buttonY += lineH + (CFG.TEXT_HEIGHT + CFG.PADDING * 2);
                }
                if (Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.iVassalsSize > 0) {
                    buttonX = paddingLeft + leftW + CFG.PADDING;
                    for (int m = 0; m < Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.iVassalsSize; ++m) {
                        if (buttonX + ButtonFlag_Diplomacy.getButtonWidth() > menuWidth) {
                            buttonX = paddingLeft + leftW + CFG.PADDING;
                            buttonY += lineH;
                            ++linesAdded;
                        }
                        menuElements.add(new ButtonFlag_Diplomacy(Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.lVassals.get(m).c, buttonX, buttonY + CFG.PADDING, true, true, true));
                        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                    }
                    menuElements.add(new TextIcon2_HorizontalSplit(Game.lang.get(Game_Ages.getVassals()), Images.vassal, paddingLeft, buttonY - lineH * linesAdded, leftW, lineH * (linesAdded + 1), maxIconW, menuWidth - paddingLeft * 2) {
                        @Override
                        public void actionElement() {
                            InGame_Civ_List.civsList.clear();
                            InGame_Civ_List.civsListTitle = Game.lang.get(Game_Ages.getVassals());
                            InGame_Civ_List.civsListTitle2 = Game.getCiv(InGame_Civ.iActiveCivID).getCivName();
                            InGame_Civ_List.civsList.add(InGame_Civ.iActiveCivID);
                            for (int i = 0; i < Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.iVassalsSize; ++i) {
                                InGame_Civ_List.civsList.add(Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.lVassals.get(i).c);
                            }
                            Game.menuManager.rebuildInGame_Civ_List();
                        }
                    });
                    linesAdded = 0;
                    buttonY += lineH;
                }
                try {
                    if (!Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.alliance.isEmpty()) {
                        buttonX = paddingLeft + leftW + CFG.PADDING;
                        for (final Diplomacy.DiplomacyData nData : Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.alliance.values()) {
                            if (buttonX + ButtonFlag_Diplomacy.getButtonWidth() > menuWidth) {
                                buttonX = paddingLeft + leftW + CFG.PADDING;
                                buttonY += lineH;
                                ++linesAdded;
                            }
                            menuElements.add(new ButtonFlag_Diplomacy(nData.iCivID, buttonX, buttonY + CFG.PADDING, true) {
                                @Override
                                public void buildElementHover() {
                                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.getCiv(this.getFlagCivID()).getCivName(), "", this.getFlagCivID(), CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Line());
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    try {
                                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Expires") + ": ", "" + Game_Calendar.getDate_ByTurnID(Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.alliance.get(this.getFlagCivID()).iTurnID), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                        nElements.add(new MenuElement_HoverElement(nData));
                                        nData.clear();
                                    }
                                    catch (final Exception ex) {}
                                    this.menuElementHover = new MenuElement_Hover(nElements);
                                }
                            });
                            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                        }
                        menuElements.add(new TextIcon2_HorizontalSplit(Game.lang.get("Alliance"), Images.alliance, paddingLeft, buttonY - lineH * linesAdded, leftW, lineH * (linesAdded + 1), maxIconW, menuWidth - paddingLeft * 2) {
                            @Override
                            public void actionElement() {
                                InGame_Civ_List.civsList.clear();
                                InGame_Civ_List.civsListTitle = Game.lang.get("Alliance");
                                InGame_Civ_List.civsListTitle2 = Game.getCiv(InGame_Civ.iActiveCivID).getCivName();
                                InGame_Civ_List.civsList.add(InGame_Civ.iActiveCivID);
                                for (final Diplomacy.DiplomacyData nData : Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.alliance.values()) {
                                    InGame_Civ_List.civsList.add(nData.iCivID);
                                }
                                Game.menuManager.rebuildInGame_Civ_List();
                            }
                        });
                        linesAdded = 0;
                        buttonY = Math.max(buttonY + lineH, menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight());
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
                try {
                    if (!Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.truce.isEmpty()) {
                        boolean addTruces = false;
                        for (final Diplomacy.DiplomacyData nData2 : Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.truce.values()) {
                            if (Game.getCiv(nData2.iCivID).getNumOfProvinces() > 0) {
                                addTruces = true;
                                break;
                            }
                        }
                        if (addTruces) {
                            buttonX = paddingLeft + leftW + CFG.PADDING;
                            for (final Diplomacy.DiplomacyData nData2 : Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.truce.values()) {
                                if (Game.getCiv(nData2.iCivID).getNumOfProvinces() > 0) {
                                    if (buttonX + ButtonFlag_Diplomacy.getButtonWidth() > menuWidth) {
                                        buttonX = paddingLeft + leftW + CFG.PADDING;
                                        buttonY += lineH;
                                        ++linesAdded;
                                    }
                                    menuElements.add(new ButtonFlag_Diplomacy(nData2.iCivID, buttonX, buttonY + CFG.PADDING, true) {
                                        @Override
                                        public void buildElementHover() {
                                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.getCiv(this.getFlagCivID()).getCivName(), "", this.getFlagCivID(), CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
                                            nElements.add(new MenuElement_HoverElement(nData));
                                            nData.clear();
                                            nData.add(new MenuElement_HoverElement_Type_Line());
                                            nElements.add(new MenuElement_HoverElement(nData));
                                            nData.clear();
                                            try {
                                                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Expires") + ": ", "" + Game_Calendar.getDate_ByTurnID(Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.truce.get(this.getFlagCivID()).iTurnID), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                                nElements.add(new MenuElement_HoverElement(nData));
                                                nData.clear();
                                            }
                                            catch (final Exception ex) {}
                                            this.menuElementHover = new MenuElement_Hover(nElements);
                                        }
                                    });
                                    buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                                }
                            }
                            menuElements.add(new TextIcon2_HorizontalSplit(Game.lang.get("Truce"), Images.truce, paddingLeft, buttonY - lineH * linesAdded, leftW, lineH * (linesAdded + 1), maxIconW, menuWidth - paddingLeft * 2) {
                                @Override
                                public void actionElement() {
                                    InGame_Civ_List.civsList.clear();
                                    InGame_Civ_List.civsListTitle = Game.lang.get("Truce");
                                    InGame_Civ_List.civsListTitle2 = Game.getCiv(InGame_Civ.iActiveCivID).getCivName();
                                    InGame_Civ_List.civsList.add(InGame_Civ.iActiveCivID);
                                    for (final Diplomacy.DiplomacyData nData : Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.truce.values()) {
                                        InGame_Civ_List.civsList.add(nData.iCivID);
                                    }
                                    Game.menuManager.rebuildInGame_Civ_List();
                                }
                            });
                            linesAdded = 0;
                            buttonY = Math.max(buttonY + lineH, menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight());
                        }
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
                try {
                    if (!Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.defensivePact.isEmpty()) {
                        buttonX = paddingLeft + leftW + CFG.PADDING;
                        for (final Diplomacy.DiplomacyData nData : Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.defensivePact.values()) {
                            if (buttonX + ButtonFlag_Diplomacy.getButtonWidth() > menuWidth) {
                                buttonX = paddingLeft + leftW + CFG.PADDING;
                                buttonY += lineH;
                                ++linesAdded;
                            }
                            menuElements.add(new ButtonFlag_Diplomacy(nData.iCivID, buttonX, buttonY + CFG.PADDING, true) {
                                @Override
                                public void buildElementHover() {
                                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.getCiv(this.getFlagCivID()).getCivName(), "", this.getFlagCivID(), CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Line());
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    try {
                                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Expires") + ": ", "" + Game_Calendar.getDate_ByTurnID(Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.defensivePact.get(this.getFlagCivID()).iTurnID), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                        nElements.add(new MenuElement_HoverElement(nData));
                                        nData.clear();
                                    }
                                    catch (final Exception ex) {}
                                    this.menuElementHover = new MenuElement_Hover(nElements);
                                }
                            });
                            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                        }
                        menuElements.add(new TextIcon2_HorizontalSplit(Game.lang.get("DefensivePact"), Images.defensivePact, paddingLeft, buttonY - lineH * linesAdded, leftW, lineH * (linesAdded + 1), maxIconW, menuWidth - paddingLeft * 2) {
                            @Override
                            public void actionElement() {
                                InGame_Civ_List.civsList.clear();
                                InGame_Civ_List.civsListTitle = Game.lang.get("DefensivePact");
                                InGame_Civ_List.civsListTitle2 = Game.getCiv(InGame_Civ.iActiveCivID).getCivName();
                                InGame_Civ_List.civsList.add(InGame_Civ.iActiveCivID);
                                for (final Diplomacy.DiplomacyData nData : Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.defensivePact.values()) {
                                    InGame_Civ_List.civsList.add(nData.iCivID);
                                }
                                Game.menuManager.rebuildInGame_Civ_List();
                            }
                        });
                        linesAdded = 0;
                        buttonY = Math.max(buttonY + lineH, menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight());
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
                try {
                    if (!Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.rivals.isEmpty()) {
                        buttonX = paddingLeft + leftW + CFG.PADDING;
                        for (final Diplomacy.DiplomacyData data : Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.rivals.values()) {
                            if (buttonX + ButtonFlag_Diplomacy.getButtonWidth() > menuWidth) {
                                buttonX = paddingLeft + leftW + CFG.PADDING;
                                buttonY += lineH;
                                ++linesAdded;
                            }
                            menuElements.add(new ButtonFlag_Diplomacy(data.iCivID, buttonX, buttonY + CFG.PADDING, true) {
                                @Override
                                public void buildElementHover() {
                                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.getCiv(this.getFlagCivID()).getCivName(), "", this.getFlagCivID(), CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Line());
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    try {
                                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Expires") + ": ", "" + Game_Calendar.getDate_ByTurnID(Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.rivals.get(this.getFlagCivID()).iTurnID), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                        nElements.add(new MenuElement_HoverElement(nData));
                                        nData.clear();
                                    }
                                    catch (final Exception ex) {}
                                    this.menuElementHover = new MenuElement_Hover(nElements);
                                }
                            });
                            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                        }
                        menuElements.add(new TextIcon2_HorizontalSplit(Game.lang.get("Rivals"), Images.rivals, paddingLeft, buttonY - lineH * linesAdded, leftW, lineH * (linesAdded + 1), maxIconW, menuWidth - paddingLeft * 2) {
                            @Override
                            public void actionElement() {
                                InGame_Civ_List.civsList.clear();
                                InGame_Civ_List.civsListTitle = Game.lang.get("Rivals");
                                InGame_Civ_List.civsListTitle2 = Game.getCiv(InGame_Civ.iActiveCivID).getCivName();
                                InGame_Civ_List.civsList.add(InGame_Civ.iActiveCivID);
                                for (final Diplomacy.DiplomacyData nData : Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.rivals.values()) {
                                    InGame_Civ_List.civsList.add(nData.iCivID);
                                }
                                Game.menuManager.rebuildInGame_Civ_List();
                            }
                        });
                        linesAdded = 0;
                        buttonY = Math.max(buttonY + lineH, menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight());
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
                try {
                    if (!Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.guarantee.isEmpty()) {
                        buttonX = paddingLeft + leftW + CFG.PADDING;
                        for (final Diplomacy.DiplomacyData nData : Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.guarantee.values()) {
                            if (buttonX + ButtonFlag_Diplomacy.getButtonWidth() > menuWidth) {
                                buttonX = paddingLeft + leftW + CFG.PADDING;
                                buttonY += lineH;
                                ++linesAdded;
                            }
                            menuElements.add(new ButtonFlag_Diplomacy(nData.iCivID, buttonX, buttonY + CFG.PADDING, true) {
                                @Override
                                public void buildElementHover() {
                                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.getCiv(this.getFlagCivID()).getCivName(), "", this.getFlagCivID(), CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Line());
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    try {
                                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Expires") + ": ", "" + Game_Calendar.getDate_ByTurnID(Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.guarantee.get(this.getFlagCivID()).iTurnID), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                        nElements.add(new MenuElement_HoverElement(nData));
                                        nData.clear();
                                    }
                                    catch (final Exception ex) {}
                                    this.menuElementHover = new MenuElement_Hover(nElements);
                                }
                            });
                            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                        }
                        menuElements.add(new TextIcon2_HorizontalSplit(Game.lang.get("GuaranteeIndependence"), Images.guaranteeIndependence, paddingLeft, buttonY - lineH * linesAdded, leftW, lineH * (linesAdded + 1), maxIconW, menuWidth - paddingLeft * 2) {
                            @Override
                            public void actionElement() {
                                InGame_Civ_List.civsList.clear();
                                InGame_Civ_List.civsListTitle = Game.lang.get("GuaranteeIndependence");
                                InGame_Civ_List.civsListTitle2 = Game.getCiv(InGame_Civ.iActiveCivID).getCivName();
                                InGame_Civ_List.civsList.add(InGame_Civ.iActiveCivID);
                                for (final Diplomacy.DiplomacyData nData : Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.guarantee.values()) {
                                    InGame_Civ_List.civsList.add(nData.iCivID);
                                }
                                Game.menuManager.rebuildInGame_Civ_List();
                            }
                        });
                        linesAdded = 0;
                        buttonY = Math.max(buttonY + lineH, menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight());
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
                try {
                    if (!Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.guaranteeByCivID.isEmpty()) {
                        buttonX = paddingLeft + leftW + CFG.PADDING;
                        for (final Diplomacy.DiplomacyData nData : Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.guaranteeByCivID.values()) {
                            if (buttonX + ButtonFlag_Diplomacy.getButtonWidth() > menuWidth) {
                                buttonX = paddingLeft + leftW + CFG.PADDING;
                                buttonY += lineH;
                                ++linesAdded;
                            }
                            menuElements.add(new ButtonFlag_Diplomacy(nData.iCivID, buttonX, buttonY + CFG.PADDING, true) {
                                @Override
                                public void buildElementHover() {
                                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.getCiv(this.getFlagCivID()).getCivName(), "", this.getFlagCivID(), CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Line());
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    try {
                                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Expires") + ": ", "" + Game_Calendar.getDate_ByTurnID(Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.guaranteeByCivID.get(this.getFlagCivID()).iTurnID), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                        nElements.add(new MenuElement_HoverElement(nData));
                                        nData.clear();
                                    }
                                    catch (final Exception ex) {}
                                    this.menuElementHover = new MenuElement_Hover(nElements);
                                }
                            });
                            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                        }
                        menuElements.add(new TextIcon2_HorizontalSplit(Game.lang.get("GuaranteeTheirIndependence"), Images.guaranteeIndependence, paddingLeft, buttonY - lineH * linesAdded, leftW, lineH * (linesAdded + 1), maxIconW, menuWidth - paddingLeft * 2) {
                            @Override
                            public void actionElement() {
                                InGame_Civ_List.civsList.clear();
                                InGame_Civ_List.civsListTitle = Game.lang.get("GuaranteeTheirIndependence");
                                InGame_Civ_List.civsListTitle2 = Game.getCiv(InGame_Civ.iActiveCivID).getCivName();
                                InGame_Civ_List.civsList.add(InGame_Civ.iActiveCivID);
                                for (final Diplomacy.DiplomacyData nData : Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.guaranteeByCivID.values()) {
                                    InGame_Civ_List.civsList.add(nData.iCivID);
                                }
                                Game.menuManager.rebuildInGame_Civ_List();
                            }
                        });
                        linesAdded = 0;
                        buttonY = Math.max(buttonY + lineH, menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight());
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
                try {
                    if (!Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.nonAggressionPact.isEmpty()) {
                        buttonX = paddingLeft + leftW + CFG.PADDING;
                        for (final Diplomacy.DiplomacyData nData : Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.nonAggressionPact.values()) {
                            if (buttonX + ButtonFlag_Diplomacy.getButtonWidth() > menuWidth) {
                                buttonX = paddingLeft + leftW + CFG.PADDING;
                                buttonY += lineH;
                                ++linesAdded;
                            }
                            menuElements.add(new ButtonFlag_Diplomacy(nData.iCivID, buttonX, buttonY + CFG.PADDING, true) {
                                @Override
                                public void buildElementHover() {
                                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.getCiv(this.getFlagCivID()).getCivName(), "", this.getFlagCivID(), CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Line());
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    try {
                                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Expires") + ": ", "" + Game_Calendar.getDate_ByTurnID(Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.nonAggressionPact.get(this.getFlagCivID()).iTurnID), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                        nElements.add(new MenuElement_HoverElement(nData));
                                        nData.clear();
                                    }
                                    catch (final Exception ex) {}
                                    this.menuElementHover = new MenuElement_Hover(nElements);
                                }
                            });
                            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                        }
                        menuElements.add(new TextIcon2_HorizontalSplit(Game.lang.get("NonAggressionPact").replace("-", " "), Images.nonAggression, paddingLeft, buttonY - lineH * linesAdded, leftW, lineH * (linesAdded + 1), maxIconW, menuWidth - paddingLeft * 2) {
                            @Override
                            public void actionElement() {
                                InGame_Civ_List.civsList.clear();
                                InGame_Civ_List.civsListTitle = Game.lang.get("NonAggressionPact");
                                InGame_Civ_List.civsListTitle2 = Game.getCiv(InGame_Civ.iActiveCivID).getCivName();
                                InGame_Civ_List.civsList.add(InGame_Civ.iActiveCivID);
                                for (final Diplomacy.DiplomacyData nData : Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.nonAggressionPact.values()) {
                                    InGame_Civ_List.civsList.add(nData.iCivID);
                                }
                                Game.menuManager.rebuildInGame_Civ_List();
                            }
                        });
                        linesAdded = 0;
                        buttonY = Math.max(buttonY + lineH, menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight());
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
                try {
                    final List<Integer> improvingRelationsFrom = new ArrayList<Integer>();
                    for (int i2 = 1; i2 < Game.getCivsSize(); ++i2) {
                        if (Game.getCiv(i2).diplomacy.isImprovingRelations(InGame_Civ.iActiveCivID) && !Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.isImprovingRelations(i2)) {
                            improvingRelationsFrom.add(i2);
                        }
                    }
                    if (Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.iImprovingRelationsSize > 0 || !improvingRelationsFrom.isEmpty()) {
                        buttonX = paddingLeft + leftW + CFG.PADDING;
                        for (int i2 = 0; i2 < Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.iImprovingRelationsSize; ++i2) {
                            if (buttonX + ButtonFlag_Diplomacy.getButtonWidth() > menuWidth) {
                                buttonX = paddingLeft + leftW + CFG.PADDING;
                                buttonY += lineH;
                                ++linesAdded;
                            }
                            if (Game.getCiv(Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.improvingRelations.get(i2).iCivID).diplomacy.isImprovingRelations(InGame_Civ.iActiveCivID)) {
                                menuElements.add(new ButtonFlag_Diplomacy(Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.improvingRelations.get(i2).iCivID, buttonX, buttonY + CFG.PADDING, true, true));
                            }
                            else {
                                menuElements.add(new ButtonFlag_Diplomacy(Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.improvingRelations.get(i2).iCivID, buttonX, buttonY + CFG.PADDING, true, true, true));
                            }
                            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                        }
                        for (int i2 = 0; i2 < improvingRelationsFrom.size(); ++i2) {
                            if (buttonX + ButtonFlag_Diplomacy.getButtonWidth() > menuWidth) {
                                buttonX = paddingLeft + leftW + CFG.PADDING;
                                buttonY += lineH;
                                ++linesAdded;
                            }
                            menuElements.add(new ButtonFlag_Diplomacy(improvingRelationsFrom.get(i2), buttonX, buttonY + CFG.PADDING, true, true, false));
                            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                        }
                        menuElements.add(new TextIcon2_HorizontalSplit(Game.lang.get("ImprovingRelations"), Images.relationsUp, paddingLeft, buttonY - lineH * linesAdded, leftW, lineH * (linesAdded + 1), maxIconW, menuWidth - paddingLeft * 2) {
                            @Override
                            public void actionElement() {
                                final List<Integer> improvingRelationsFrom = new ArrayList<Integer>();
                                for (int i = 1; i < Game.getCivsSize(); ++i) {
                                    if (Game.getCiv(i).diplomacy.isImprovingRelations(InGame_Civ.iActiveCivID) && !Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.isImprovingRelations(i)) {
                                        improvingRelationsFrom.add(i);
                                    }
                                }
                                InGame_Civ_List.civsList.clear();
                                InGame_Civ_List.civsListTitle = Game.lang.get("ImprovingRelations");
                                InGame_Civ_List.civsListTitle2 = Game.getCiv(InGame_Civ.iActiveCivID).getCivName();
                                InGame_Civ_List.civsList.add(InGame_Civ.iActiveCivID);
                                for (int i = 0; i < Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.iImprovingRelationsSize; ++i) {
                                    InGame_Civ_List.addCiv(Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.improvingRelations.get(i).iCivID);
                                }
                                for (int i = improvingRelationsFrom.size() - 1; i >= 0; --i) {
                                    InGame_Civ_List.addCiv(improvingRelationsFrom.get(i));
                                }
                                Game.menuManager.rebuildInGame_Civ_List();
                            }
                        });
                        linesAdded = 0;
                        buttonY = Math.max(buttonY + lineH, menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight());
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
                try {
                    final List<Integer> damagingRelationsFrom = new ArrayList<Integer>();
                    for (int i2 = 1; i2 < Game.getCivsSize(); ++i2) {
                        if (Game.getCiv(i2).diplomacy.isDamagingRelations(InGame_Civ.iActiveCivID) && !Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.isDamagingRelations(i2)) {
                            damagingRelationsFrom.add(i2);
                        }
                    }
                    if (Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.iDamagingRelationsSize > 0 || !damagingRelationsFrom.isEmpty()) {
                        buttonX = paddingLeft + leftW + CFG.PADDING;
                        for (int i2 = 0; i2 < Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.iDamagingRelationsSize; ++i2) {
                            if (buttonX + ButtonFlag_Diplomacy.getButtonWidth() > menuWidth) {
                                buttonX = paddingLeft + leftW + CFG.PADDING;
                                buttonY += lineH;
                                ++linesAdded;
                            }
                            if (Game.getCiv(Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.damagingRelations.get(i2).iCivID).diplomacy.isDamagingRelations(InGame_Civ.iActiveCivID)) {
                                menuElements.add(new ButtonFlag_Diplomacy(Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.damagingRelations.get(i2).iCivID, buttonX, buttonY + CFG.PADDING, true, true));
                            }
                            else {
                                menuElements.add(new ButtonFlag_Diplomacy(Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.damagingRelations.get(i2).iCivID, buttonX, buttonY + CFG.PADDING, true, true, true));
                            }
                            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                        }
                        for (int i2 = 0; i2 < damagingRelationsFrom.size(); ++i2) {
                            if (buttonX + ButtonFlag_Diplomacy.getButtonWidth() > menuWidth) {
                                buttonX = paddingLeft + leftW + CFG.PADDING;
                                buttonY += lineH;
                                ++linesAdded;
                            }
                            menuElements.add(new ButtonFlag_Diplomacy(damagingRelationsFrom.get(i2), buttonX, buttonY + CFG.PADDING, true, true, false));
                            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                        }
                        menuElements.add(new TextIcon2_HorizontalSplit(Game.lang.get("DamagingRelations"), Images.relationsDown, paddingLeft, buttonY - lineH * linesAdded, leftW, lineH * (linesAdded + 1), maxIconW, menuWidth - paddingLeft * 2) {
                            @Override
                            public void actionElement() {
                                final List<Integer> damagingRelationsFrom = new ArrayList<Integer>();
                                for (int i = 1; i < Game.getCivsSize(); ++i) {
                                    if (Game.getCiv(i).diplomacy.isDamagingRelations(InGame_Civ.iActiveCivID) && !Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.isDamagingRelations(i)) {
                                        damagingRelationsFrom.add(i);
                                    }
                                }
                                InGame_Civ_List.civsList.clear();
                                InGame_Civ_List.civsListTitle = Game.lang.get("DamagingRelations");
                                InGame_Civ_List.civsListTitle2 = Game.getCiv(InGame_Civ.iActiveCivID).getCivName();
                                InGame_Civ_List.civsList.add(InGame_Civ.iActiveCivID);
                                for (int i = 0; i < Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.iDamagingRelationsSize; ++i) {
                                    InGame_Civ_List.addCiv(Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.damagingRelations.get(i).iCivID);
                                }
                                for (int i = damagingRelationsFrom.size() - 1; i >= 0; --i) {
                                    InGame_Civ_List.addCiv(damagingRelationsFrom.get(i));
                                }
                                Game.menuManager.rebuildInGame_Civ_List();
                            }
                        });
                        linesAdded = 0;
                        buttonY = Math.max(buttonY + lineH, menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight());
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
                try {
                    final List<Integer> friendly = new ArrayList<Integer>();
                    for (int i2 = 1; i2 < Game.getCivsSize(); ++i2) {
                        if (Game.getCiv(i2).diplomacy.getRelation(InGame_Civ.iActiveCivID) >= GameValues.diplomacy.DIPLOMACY_RELATIONS_FRIENDLY && !Game.getCiv(i2).diplomacy.isRival(InGame_Civ.iActiveCivID) && !Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.isRival(i2)) {
                            friendly.add(i2);
                        }
                    }
                    if (!friendly.isEmpty()) {
                        buttonX = paddingLeft + leftW + CFG.PADDING;
                        for (int i2 = 0; i2 < GameValues.diplomacy.DIPLOMACY_FRIENDLY_LIMIT_IN_DIPLOMACY_VIEW && i2 < friendly.size(); ++i2) {
                            int tBestID = 0;
                            for (int j2 = friendly.size() - 1; j2 > 0; --j2) {
                                if (Game.getCiv(friendly.get(tBestID)).diplomacy.getRelation(InGame_Civ.iActiveCivID) < Game.getCiv(friendly.get(j2)).diplomacy.getRelation(InGame_Civ.iActiveCivID)) {
                                    tBestID = j2;
                                }
                            }
                            if (buttonX + ButtonFlag_Diplomacy.getButtonWidth() > menuWidth) {
                                buttonX = paddingLeft + leftW + CFG.PADDING;
                                buttonY += lineH;
                                ++linesAdded;
                            }
                            menuElements.add(new ButtonFlag_Diplomacy(friendly.get(tBestID), buttonX, buttonY + CFG.PADDING, true));
                            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                            friendly.remove(tBestID);
                        }
                        menuElements.add(new TextIcon2_HorizontalSplit(Game.lang.get("FriendlyCivilizations"), Images.heart, paddingLeft, buttonY - lineH * linesAdded, leftW, lineH * (linesAdded + 1), maxIconW, menuWidth - paddingLeft * 2) {
                            @Override
                            public void actionElement() {
                                final List<Integer> friendly = new ArrayList<Integer>();
                                for (int i = 1; i < Game.getCivsSize(); ++i) {
                                    if (Game.getCiv(i).diplomacy.getRelation(InGame_Civ.iActiveCivID) >= GameValues.diplomacy.DIPLOMACY_RELATIONS_FRIENDLY && !Game.getCiv(i).diplomacy.isRival(InGame_Civ.iActiveCivID) && !Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.isRival(i)) {
                                        friendly.add(i);
                                    }
                                }
                                InGame_Civ_List.civsList.clear();
                                InGame_Civ_List.civsListTitle = Game.lang.get("FriendlyCivilizations");
                                InGame_Civ_List.civsListTitle2 = Game.getCiv(InGame_Civ.iActiveCivID).getCivName();
                                InGame_Civ_List.civsList.add(InGame_Civ.iActiveCivID);
                                for (int i = friendly.size() - 1; i >= 0; --i) {
                                    InGame_Civ_List.civsList.add(friendly.get(i));
                                }
                                Game.menuManager.rebuildInGame_Civ_List();
                            }
                        });
                        linesAdded = 0;
                        buttonY = Math.max(buttonY + lineH, menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight());
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
                try {
                    if (!Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.militaryAccess.isEmpty()) {
                        buttonX = paddingLeft + leftW + CFG.PADDING;
                        for (final Diplomacy.DiplomacyData nData : Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.militaryAccess.values()) {
                            if (buttonX + ButtonFlag_Diplomacy.getButtonWidth() > menuWidth) {
                                buttonX = paddingLeft + leftW + CFG.PADDING;
                                buttonY += lineH;
                                ++linesAdded;
                            }
                            menuElements.add(new ButtonFlag_Diplomacy(nData.iCivID, buttonX, buttonY + CFG.PADDING, true) {
                                @Override
                                public void buildElementHover() {
                                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.getCiv(this.getFlagCivID()).getCivName(), "", this.getFlagCivID(), CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Line());
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    try {
                                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Expires") + ": ", "" + Game_Calendar.getDate_ByTurnID(Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.militaryAccess.get(this.getFlagCivID()).iTurnID), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                        nElements.add(new MenuElement_HoverElement(nData));
                                        nData.clear();
                                    }
                                    catch (final Exception ex) {}
                                    this.menuElementHover = new MenuElement_Hover(nElements);
                                }
                            });
                            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                        }
                        menuElements.add(new TextIcon2_HorizontalSplit(Game.lang.get("HaveMilitaryAccess"), Images.militaryAccess, paddingLeft, buttonY - lineH * linesAdded, leftW, lineH * (linesAdded + 1), maxIconW, menuWidth - paddingLeft * 2) {
                            @Override
                            public void actionElement() {
                                InGame_Civ_List.civsList.clear();
                                InGame_Civ_List.civsListTitle = Game.lang.get("HaveMilitaryAccess");
                                InGame_Civ_List.civsListTitle2 = Game.getCiv(InGame_Civ.iActiveCivID).getCivName();
                                InGame_Civ_List.civsList.add(InGame_Civ.iActiveCivID);
                                for (final Diplomacy.DiplomacyData nData : Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.militaryAccess.values()) {
                                    InGame_Civ_List.civsList.add(nData.iCivID);
                                }
                                Game.menuManager.rebuildInGame_Civ_List();
                            }
                        });
                        linesAdded = 0;
                        buttonY = Math.max(buttonY + lineH, menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight());
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
                try {
                    final List<Integer> tMilitaryAccessGives = new ArrayList<Integer>();
                    for (int i2 = 1; i2 < Game.getCivsSize(); ++i2) {
                        if (Game.getCiv(i2).getNumOfProvinces() > 0 && Game.getCiv(i2).diplomacy.militaryAccess.containsKey(InGame_Civ.iActiveCivID)) {
                            tMilitaryAccessGives.add(i2);
                        }
                    }
                    if (!tMilitaryAccessGives.isEmpty()) {
                        buttonX = paddingLeft + leftW + CFG.PADDING;
                        for (int i2 = 0; i2 < tMilitaryAccessGives.size(); ++i2) {
                            if (buttonX + ButtonFlag_Diplomacy.getButtonWidth() > menuWidth) {
                                buttonX = paddingLeft + leftW + CFG.PADDING;
                                buttonY += lineH;
                                ++linesAdded;
                            }
                            menuElements.add(new ButtonFlag_Diplomacy((int)tMilitaryAccessGives.get(i2), buttonX, buttonY + CFG.PADDING, true) {
                                @Override
                                public void buildElementHover() {
                                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.getCiv(this.getFlagCivID()).getCivName(), "", this.getFlagCivID(), CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Line());
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    try {
                                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Expires") + ": ", "" + Game_Calendar.getDate_ByTurnID(Game.getCiv(this.getFlagCivID()).diplomacy.militaryAccess.get(InGame_Civ.iActiveCivID).iTurnID), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                        nElements.add(new MenuElement_HoverElement(nData));
                                        nData.clear();
                                    }
                                    catch (final Exception ex) {}
                                    this.menuElementHover = new MenuElement_Hover(nElements);
                                }
                            });
                            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                        }
                        menuElements.add(new TextIcon2_HorizontalSplit(Game.lang.get("GivesMilitaryAccess"), Images.militaryAccess2, paddingLeft, buttonY - lineH * linesAdded, leftW, lineH * (linesAdded + 1), maxIconW, menuWidth - paddingLeft * 2) {
                            @Override
                            public void actionElement() {
                                final List<Integer> tMilitaryAccessGives = new ArrayList<Integer>();
                                for (int i = 1; i < Game.getCivsSize(); ++i) {
                                    if (Game.getCiv(i).getNumOfProvinces() > 0 && Game.getCiv(i).diplomacy.militaryAccess.containsKey(InGame_Civ.iActiveCivID)) {
                                        tMilitaryAccessGives.add(i);
                                    }
                                }
                                InGame_Civ_List.civsList.clear();
                                InGame_Civ_List.civsListTitle = Game.lang.get("GivesMilitaryAccess");
                                InGame_Civ_List.civsListTitle2 = Game.getCiv(InGame_Civ.iActiveCivID).getCivName();
                                InGame_Civ_List.civsList.add(InGame_Civ.iActiveCivID);
                                for (int a = 0; a < tMilitaryAccessGives.size(); ++a) {
                                    InGame_Civ_List.civsList.add(tMilitaryAccessGives.get(a));
                                }
                                Game.menuManager.rebuildInGame_Civ_List();
                            }
                        });
                        linesAdded = 0;
                        buttonY = Math.max(buttonY + lineH, menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight());
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
                try {
                    final List<Integer> rivaledBy = RivalsManager.getRivaledBy(InGame_Civ.iActiveCivID);
                    if (!rivaledBy.isEmpty()) {
                        buttonX = paddingLeft + leftW + CFG.PADDING;
                        for (int i2 = 0, iSize3 = rivaledBy.size(); i2 < iSize3; ++i2) {
                            if (buttonX + ButtonFlag_Diplomacy.getButtonWidth() > menuWidth) {
                                buttonX = paddingLeft + leftW + CFG.PADDING;
                                buttonY += lineH;
                                ++linesAdded;
                            }
                            menuElements.add(new ButtonFlag_Diplomacy(rivaledBy.get(i2), buttonX, buttonY + CFG.PADDING, true));
                            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                        }
                        menuElements.add(new TextIcon2_HorizontalSplit(Game.lang.get("RivaledBy"), Images.rivals, paddingLeft, buttonY - lineH * linesAdded, leftW, lineH * (linesAdded + 1), maxIconW, menuWidth - paddingLeft * 2) {
                            @Override
                            public void actionElement() {
                                final List<Integer> rivaledBy = RivalsManager.getRivaledBy(InGame_Civ.iActiveCivID);
                                InGame_Civ_List.civsList.clear();
                                InGame_Civ_List.civsListTitle = Game.lang.get("RivaledBy");
                                InGame_Civ_List.civsListTitle2 = Game.getCiv(InGame_Civ.iActiveCivID).getCivName();
                                InGame_Civ_List.civsList.add(InGame_Civ.iActiveCivID);
                                for (int a = 0; a < rivaledBy.size(); ++a) {
                                    InGame_Civ_List.civsList.add(rivaledBy.get(a));
                                }
                                Game.menuManager.rebuildInGame_Civ_List();
                            }
                        });
                        linesAdded = 0;
                        buttonY = Math.max(buttonY + lineH, menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight());
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
                try {
                    if (Game.getCiv(InGame_Civ.iActiveCivID).civNeighbors.civsSize > 0) {
                        buttonX = paddingLeft + leftW + CFG.PADDING;
                        final int elementsBefore = menuElements.size();
                        for (int a = 0; a < Game.getCiv(InGame_Civ.iActiveCivID).civNeighbors.civsSize; ++a) {
                            if (Game.getCiv(InGame_Civ.iActiveCivID).civNeighbors.civs.get(a).byLand) {
                                if (buttonX + ButtonFlag_Diplomacy.getButtonWidth() > menuWidth) {
                                    buttonX = paddingLeft + leftW + CFG.PADDING;
                                    buttonY += lineH;
                                    ++linesAdded;
                                }
                                menuElements.add(new ButtonFlag_Diplomacy(Game.getCiv(InGame_Civ.iActiveCivID).civNeighbors.civs.get(a).civID, buttonX, buttonY + CFG.PADDING, true) {
                                    @Override
                                    public void buildElementHover() {
                                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.getCiv(this.getFlagCivID()).getCivName(), "", this.getFlagCivID(), CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
                                        nElements.add(new MenuElement_HoverElement(nData));
                                        nData.clear();
                                        nData.add(new MenuElement_HoverElement_Type_Line());
                                        nElements.add(new MenuElement_HoverElement(nData));
                                        nData.clear();
                                        try {
                                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Provinces") + ": ", "" + Game.getCiv(this.getCurrent()).getNumOfProvinces(), Images.provinces, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                            nElements.add(new MenuElement_HoverElement(nData));
                                            nData.clear();
                                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", "" + CFG.getNumberWithSpaces("" + Game.getCiv(this.getCurrent()).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                                            nElements.add(new MenuElement_HoverElement(nData));
                                            nData.clear();
                                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Economy") + ": ", "" + CFG.getPrecision2(Game.getCiv(this.getCurrent()).getEconomyTotal(), 1), Game_Calendar.IMG_ECONOMY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                            nElements.add(new MenuElement_HoverElement(nData));
                                            nData.clear();
                                        }
                                        catch (final Exception ex) {}
                                        this.menuElementHover = new MenuElement_Hover(nElements);
                                    }
                                });
                                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                            }
                        }
                        if (elementsBefore != menuElements.size()) {
                            menuElements.add(new TextIcon2_HorizontalSplit(Game.lang.get("NeighbouringCivilizations") + ": " + (menuElements.size() - elementsBefore), Images.frontLine, paddingLeft, buttonY - lineH * linesAdded, leftW, lineH * (linesAdded + 1), maxIconW, menuWidth - paddingLeft * 2) {
                                @Override
                                public void actionElement() {
                                    InGame_Civ_List.civsList.clear();
                                    InGame_Civ_List.civsListTitle = Game.lang.get("NeighbouringCivilizations");
                                    InGame_Civ_List.civsListTitle2 = Game.getCiv(InGame_Civ.iActiveCivID).getCivName();
                                    InGame_Civ_List.civsList.add(InGame_Civ.iActiveCivID);
                                    for (int a = 0; a < Game.getCiv(InGame_Civ.iActiveCivID).civNeighbors.civsSize; ++a) {
                                        if (Game.getCiv(InGame_Civ.iActiveCivID).civNeighbors.civs.get(a).byLand) {
                                            InGame_Civ_List.civsList.add(Game.getCiv(InGame_Civ.iActiveCivID).civNeighbors.civs.get(a).civID);
                                        }
                                    }
                                    Game.menuManager.rebuildInGame_Civ_List();
                                }
                            });
                            linesAdded = 0;
                            buttonY = Math.max(buttonY + lineH, menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight());
                        }
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
                try {
                    final List<Integer> conqueredCivs = PeaceTreaty.getCivsPossibleToLiberate(InGame_Civ.iActiveCivID);
                    if (!conqueredCivs.isEmpty()) {
                        buttonX = paddingLeft + leftW + CFG.PADDING;
                        for (int a = 0, aSize = conqueredCivs.size(); a < aSize; ++a) {
                            if (buttonX + ButtonFlag_Diplomacy.getButtonWidth() > menuWidth) {
                                buttonX = paddingLeft + leftW + CFG.PADDING;
                                buttonY += lineH;
                                ++linesAdded;
                            }
                            menuElements.add(new ButtonFlag_Diplomacy((int)conqueredCivs.get(a), buttonX, buttonY + CFG.PADDING, true) {
                                @Override
                                public void buildElementHover() {
                                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.getCiv(this.getFlagCivID()).getCivName(), "", this.getFlagCivID(), CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Line());
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    try {
                                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Provinces") + ": ", "" + PeaceTreaty.getLiberateCivProvinces(InGame_Civ.iActiveCivID, this.getCurrent()), Images.provinces, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                        nElements.add(new MenuElement_HoverElement(nData));
                                        nData.clear();
                                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", "" + CFG.getNumberWithSpaces("" + PeaceTreaty.getLiberateCivPopulation(InGame_Civ.iActiveCivID, this.getCurrent())), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                                        nElements.add(new MenuElement_HoverElement(nData));
                                        nData.clear();
                                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Economy") + ": ", "" + PeaceTreaty.getLiberateCivEconomy(InGame_Civ.iActiveCivID, this.getCurrent()), Game_Calendar.IMG_ECONOMY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                        nElements.add(new MenuElement_HoverElement(nData));
                                        nData.clear();
                                    }
                                    catch (final Exception ex) {}
                                    this.menuElementHover = new MenuElement_Hover(nElements);
                                }
                            });
                            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                        }
                        menuElements.add(new TextIcon2_HorizontalSplit(Game.lang.get("ConqueredCivilizations"), Images.conqueredCivs, paddingLeft, buttonY - lineH * linesAdded, leftW, lineH * (linesAdded + 1), maxIconW, menuWidth - paddingLeft * 2) {
                            @Override
                            public void actionElement() {
                                final List<Integer> conqueredCivs = PeaceTreaty.getCivsPossibleToLiberate(InGame_Civ.iActiveCivID);
                                InGame_Civ_List.civsList.clear();
                                InGame_Civ_List.civsListTitle = Game.lang.get("ConqueredCivilizations");
                                InGame_Civ_List.civsListTitle2 = Game.getCiv(InGame_Civ.iActiveCivID).getCivName();
                                InGame_Civ_List.civsList.add(InGame_Civ.iActiveCivID);
                                for (int a = 0; a < conqueredCivs.size(); ++a) {
                                    InGame_Civ_List.civsList.add(conqueredCivs.get(a));
                                }
                                Game.menuManager.rebuildInGame_Civ_List();
                            }
                        });
                        linesAdded = 0;
                        buttonY = Math.max(buttonY + lineH, menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight());
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
                buttonY += CFG.PADDING;
            }
        }
        else {
            buttonY += CFG.PADDING;
            final int buttonW = (menuWidth - paddingLeft * 2 - CFG.PADDING * 3) / 4;
            final int buttonH = (int)(buttonW * 1.1f);
            buttonX = paddingLeft;
            final List<MenuElement> tempElements = new ArrayList<MenuElement>();
            if (Game.getCiv(Game.player.iCivID).getNumOfProvinces() <= 0) {
                tempElements.add(new ButtonDiplomacy(Game.lang.get("Provinces") + ": " + Game.getCiv(Game.player.iCivID).getNumOfProvinces(), Images.provinces, buttonX, buttonY, menuWidth - paddingLeft * 2, buttonH) {
                    @Override
                    public void actionElement() {
                    }
                    
                    @Override
                    public Color getColorHover1() {
                        return new Color(DiplomacyManager.COLOR_RED.r, DiplomacyManager.COLOR_RED.g, DiplomacyManager.COLOR_RED.b, 0.15f);
                    }
                    
                    @Override
                    public Color getColorHover2() {
                        return new Color(DiplomacyManager.COLOR_RED.r, DiplomacyManager.COLOR_RED.g, DiplomacyManager.COLOR_RED.b, 0.5f);
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.getCiv(Game.player.iCivID).getCivName(), "", Game.player.iCivID, CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Provinces") + ": ", CFG.FONT_BOLD, Colors.HOVER_LEFT));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.getCiv(Game.player.iCivID).getNumOfProvinces(), CFG.FONT_BOLD, Colors.HOVER_NEGATIVE));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.provinces, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                    
                    @Override
                    public boolean getIsHovered() {
                        return true;
                    }
                    
                    @Override
                    public int getSFX() {
                        return SoundsManager.SOUND_CLICK_TOP;
                    }
                });
            }
            else if (Game.getCiv(InGame_Civ.iActiveCivID).getNumOfProvinces() == 0) {
                tempElements.add(new ButtonDiplomacy(Game.lang.get("Provinces") + ": " + Game.getCiv(InGame_Civ.iActiveCivID).getNumOfProvinces(), Images.provinces, buttonX, buttonY, buttonW * 2 + CFG.PADDING, buttonH) {
                    @Override
                    public void actionElement() {
                    }
                    
                    @Override
                    public Color getColorHover1() {
                        return new Color(DiplomacyManager.COLOR_RED.r, DiplomacyManager.COLOR_RED.g, DiplomacyManager.COLOR_RED.b, 0.15f);
                    }
                    
                    @Override
                    public Color getColorHover2() {
                        return new Color(DiplomacyManager.COLOR_RED.r, DiplomacyManager.COLOR_RED.g, DiplomacyManager.COLOR_RED.b, 0.5f);
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.getCiv(InGame_Civ.iActiveCivID).getCivName(), "", InGame_Civ.iActiveCivID, CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Provinces") + ": ", CFG.FONT_BOLD, Colors.HOVER_LEFT));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.getCiv(InGame_Civ.iActiveCivID).getNumOfProvinces(), CFG.FONT_BOLD, Colors.HOVER_NEGATIVE));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.provinces, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                    
                    @Override
                    public boolean getIsHovered() {
                        return true;
                    }
                    
                    @Override
                    public int getSFX() {
                        return SoundsManager.SOUND_CLICK_TOP;
                    }
                });
                tempElements.add(new ButtonDiplomacy(Game.lang.get("SellProvince"), Images.provinces, buttonX, buttonY, buttonW * 2 + CFG.PADDING, buttonH) {
                    @Override
                    public void actionElement() {
                        if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 41 && InGame_SellProvince.sellToCivID == InGame_Civ.iActiveCivID) {
                            Game.menuManager.setVisibleInGame_PopUp(false);
                        }
                        else {
                            Game.menuManager.setVisibleInGame_Civ(false);
                            Game.menuManager.rebuildInGame_SellProvince(InGame_Civ.iActiveCivID);
                        }
                    }
                    
                    @Override
                    public int getSFX() {
                        return SoundsManager.SOUND_CLICK_TOP;
                    }
                });
            }
            else if (DiplomacyManager.isAtWar(Game.player.iCivID, InGame_Civ.iActiveCivID)) {
                tempElements.add(new ButtonDiplomacy(Game.lang.get("WeAreAtWar"), Images.war, buttonX, buttonY, menuWidth - paddingLeft * 2, buttonH) {
                    @Override
                    public void actionElement() {
                        final String warKey = WarManager.getWarKey(Game.player.iCivID, InGame_Civ.iActiveCivID);
                        if (warKey != null) {
                            if (Game.menuManager.getVisibleInGame_War() && InGame_War.key.equals(warKey)) {
                                Game.menuManager.setVisibleInGame_War(false);
                            }
                            else {
                                Game.gameActiveProvince.resetLastActiveProvince();
                                Game.setActiveProvinceID(-1);
                                Game.clearActiveArmy();
                                Game.menuManager.showInGame_Battle_HideMenus();
                                InGame_War.key = warKey;
                                Game.menuManager.rebuildInGame_War();
                                if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_WAR_VIEW) {
                                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_WAR_VIEW);
                                }
                                else {
                                    Game.mapModes.updateWarView(InGame_War.key);
                                }
                            }
                        }
                    }
                    
                    @Override
                    public Color getColorHover1() {
                        return new Color(DiplomacyManager.COLOR_WAR.r, DiplomacyManager.COLOR_WAR.g, DiplomacyManager.COLOR_WAR.b, 0.15f);
                    }
                    
                    @Override
                    public Color getColorHover2() {
                        return new Color(DiplomacyManager.COLOR_WAR.r, DiplomacyManager.COLOR_WAR.g, DiplomacyManager.COLOR_WAR.b, 0.5f);
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final String warKey = WarManager.getWarKey(Game.player.iCivID, InGame_Civ.iActiveCivID);
                        this.menuElementHover = MessageWar.getHoverWar(warKey, Game.player.iCivID);
                    }
                    
                    @Override
                    public boolean getIsHovered() {
                        return true;
                    }
                    
                    @Override
                    public int getSFX() {
                        return SoundsManager.SOUND_CLICK_TOP;
                    }
                });
            }
            else {
                if (Game.getCiv(Game.player.iCivID).diplomacy.truce.containsKey(InGame_Civ.iActiveCivID)) {
                    tempElements.add(new ButtonDiplomacy(Game.lang.get("Truce"), Images.truce, buttonX, buttonY, buttonW, buttonH) {
                        @Override
                        public void actionElement() {
                            if (Game.getCiv(Game.player.iCivID).diplomacy.truce.containsKey(InGame_Civ.iActiveCivID)) {
                                if (Game.getCiv(Game.player.iCivID).diplomacy.truce.get(InGame_Civ.iActiveCivID).iTurnID < Game_Calendar.TURN_ID) {
                                    Game.getCiv(Game.player.iCivID).diplomacy.truce.remove(InGame_Civ.iActiveCivID);
                                    Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.truce.remove(Game.player.iCivID);
                                    InGame_Civ.rebuildMenu();
                                }
                                else {
                                    Game.menuManager.addToastInsufficient(Game.lang.get("Expires") + ": ", Game_Calendar.getDate_ByTurnID(Game.getCiv(Game.player.iCivID).diplomacy.truce.get(InGame_Civ.iActiveCivID).iTurnID), Images.time);
                                }
                            }
                            else {
                                InGame_Civ.rebuildMenu();
                            }
                        }
                        
                        @Override
                        public Color getColorHover1() {
                            return new Color(DiplomacyManager.COLOR_PEACE.r, DiplomacyManager.COLOR_PEACE.g, DiplomacyManager.COLOR_PEACE.b, 0.15f);
                        }
                        
                        @Override
                        public Color getColorHover2() {
                            return new Color(DiplomacyManager.COLOR_PEACE.r, DiplomacyManager.COLOR_PEACE.g, DiplomacyManager.COLOR_PEACE.b, 0.5f);
                        }
                        
                        @Override
                        public void buildElementHover() {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.getCiv(InGame_Civ.iActiveCivID).getCivName(), "", InGame_Civ.iActiveCivID, CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Line());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            try {
                                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Expires") + ": ", "" + Game_Calendar.getDate_ByTurnID(Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.truce.get(Game.player.iCivID).iTurnID), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            catch (final Exception ex) {}
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                        
                        @Override
                        public int getSFX() {
                            return SoundsManager.SOUND_CLICK_TOP;
                        }
                    });
                }
                else if (Game.getCiv(Game.player.iCivID).getPuppetOfCivID() == InGame_Civ.iActiveCivID) {
                    tempElements.add(new ButtonDiplomacy(Game.lang.get("ProclaimIndependence"), Images.vassal, buttonX, buttonY, buttonW, buttonH) {
                        @Override
                        public void actionElement() {
                            if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 12) {
                                Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                                Game.menuManager.setVisibleInGame_PopUp(false);
                            }
                            else {
                                Game.menuManager.setVisibleInGame_Civ(false);
                                Game.menuManager.rebuildInGame_DeclareWar(InGame_Civ.iActiveCivID);
                                Game.mapModes.setActiveViewID(Game.mapModes.MODE_DECLARE_WAR);
                                if (Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.getRelation(Game.player.iCivID) > GameValues.war.RELATIONS_TO_DECLARE_WAR) {
                                    Game.menuManager.addToast_Error(Game.lang.get("ToDeclareWarTheRelationsBetweenCivilizationsMustBeBelowX", GameValues.war.RELATIONS_TO_DECLARE_WAR), Images.relations);
                                }
                                if (Game.getCiv(Game.player.iCivID).diplomacy.haveNonAggressionPact(InGame_Civ.iActiveCivID)) {
                                    Game.menuManager.addToast_Error(Game.lang.get("NonAggressionPact") + ": " + Game.getCiv(Game.player.iCivID).getCivName() + " - " + Game.getCiv(InGame_Civ.iActiveCivID).getCivName(), Images.nonAggression);
                                }
                                if (Game.getCiv(Game.player.iCivID).diplomacy.haveAlliance(InGame_Civ.iActiveCivID) || Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.haveAlliance(Game.player.iCivID)) {
                                    Game.menuManager.addToast_Error(Game.lang.get("Alliance") + ": " + Game.getCiv(Game.player.iCivID).getCivName() + " - " + Game.getCiv(InGame_Civ.iActiveCivID).getCivName(), Images.alliance);
                                }
                            }
                        }
                        
                        @Override
                        public Color getColorHover1() {
                            return new Color(DiplomacyManager.COLOR_BATTLE.r, DiplomacyManager.COLOR_BATTLE.g, DiplomacyManager.COLOR_BATTLE.b, 0.15f);
                        }
                        
                        @Override
                        public Color getColorHover2() {
                            return new Color(DiplomacyManager.COLOR_BATTLE.r, DiplomacyManager.COLOR_BATTLE.g, DiplomacyManager.COLOR_BATTLE.b, 0.5f);
                        }
                        
                        @Override
                        public void buildElementHover() {
                            try {
                                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                                final int iCivB = InGame_Civ.iActiveCivID;
                                final int iCivFrom = Game.player.iCivID;
                                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ProclaimIndependence"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.war, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                if (Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.getRelation(Game.player.iCivID) > GameValues.war.RELATIONS_TO_DECLARE_WAR) {
                                    nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("ToDeclareWarTheRelationsBetweenCivilizationsMustBeBelowX", GameValues.war.RELATIONS_TO_DECLARE_WAR), CFG.FONT_REGULAR_SMALL, Colors.HOVER_NEGATIVE));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Line());
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                }
                                if (Game.getCiv(Game.player.iCivID).diplomacy.haveNonAggressionPact(InGame_Civ.iActiveCivID)) {
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("NonAggressionPact"), "", Images.nonAggression, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Expires") + ": ", "" + Game_Calendar.getDate_ByTurnID(Game.getCiv(Game.player.iCivID).diplomacy.nonAggressionPact.get(InGame_Civ.iActiveCivID).iTurnID), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Line());
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                }
                                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Opinion") + ": ", ((Game.getCiv(iCivB).diplomacy.getRelation(iCivFrom) > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivB).diplomacy.getRelation(iCivFrom), 10), Images.relations, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, DiplomacyManager.getOpinion_Color((int)Game.getCiv(iCivB).diplomacy.getRelation(iCivFrom))));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                this.menuElementHover = new MenuElement_Hover(nElements);
                            }
                            catch (final Exception ex) {
                                this.menuElementHover = null;
                            }
                            if (Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.getRelation(Game.player.iCivID) <= GameValues.war.RELATIONS_TO_DECLARE_WAR) {
                                this.menuElementHover = null;
                            }
                        }
                        
                        @Override
                        public int getSFX() {
                            return SoundsManager.SOUND_CLICK_TOP;
                        }
                    });
                }
                else {
                    tempElements.add(new ButtonDiplomacy(Game.lang.get("DeclareWar"), Images.war, buttonX, buttonY, buttonW, buttonH) {
                        @Override
                        public void actionElement() {
                            if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 12) {
                                Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                                Game.menuManager.setVisibleInGame_PopUp(false);
                            }
                            else {
                                Game.menuManager.setVisibleInGame_Civ(false);
                                Game.menuManager.rebuildInGame_DeclareWar(InGame_Civ.iActiveCivID);
                                Game.mapModes.setActiveViewID(Game.mapModes.MODE_DECLARE_WAR);
                                if (Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.getRelation(Game.player.iCivID) > GameValues.war.RELATIONS_TO_DECLARE_WAR) {
                                    Game.menuManager.addToast_Error(Game.lang.get("ToDeclareWarTheRelationsBetweenCivilizationsMustBeBelowX", GameValues.war.RELATIONS_TO_DECLARE_WAR), Images.relations);
                                }
                                if (Game.getCiv(Game.player.iCivID).diplomacy.haveNonAggressionPact(InGame_Civ.iActiveCivID)) {
                                    Game.menuManager.addToast_Error(Game.lang.get("NonAggressionPact") + ": " + Game.getCiv(Game.player.iCivID).getCivName() + " - " + Game.getCiv(InGame_Civ.iActiveCivID).getCivName(), Images.nonAggression);
                                }
                                if (Game.getCiv(Game.player.iCivID).diplomacy.haveAlliance(InGame_Civ.iActiveCivID) || Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.haveAlliance(Game.player.iCivID)) {
                                    Game.menuManager.addToast_Error(Game.lang.get("Alliance") + ": " + Game.getCiv(Game.player.iCivID).getCivName() + " - " + Game.getCiv(InGame_Civ.iActiveCivID).getCivName(), Images.alliance);
                                }
                            }
                        }
                        
                        @Override
                        public Color getColorHover1() {
                            return new Color(DiplomacyManager.COLOR_WAR.r, DiplomacyManager.COLOR_WAR.g, DiplomacyManager.COLOR_WAR.b, 0.15f);
                        }
                        
                        @Override
                        public Color getColorHover2() {
                            return new Color(DiplomacyManager.COLOR_WAR.r, DiplomacyManager.COLOR_WAR.g, DiplomacyManager.COLOR_WAR.b, 0.5f);
                        }
                        
                        @Override
                        public void buildElementHover() {
                            try {
                                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                                final int iCivB = InGame_Civ.iActiveCivID;
                                final int iCivFrom = Game.player.iCivID;
                                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("DeclareWar"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.war, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                if (Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.getRelation(Game.player.iCivID) > GameValues.war.RELATIONS_TO_DECLARE_WAR) {
                                    nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("ToDeclareWarTheRelationsBetweenCivilizationsMustBeBelowX", GameValues.war.RELATIONS_TO_DECLARE_WAR), CFG.FONT_REGULAR_SMALL, Colors.HOVER_NEGATIVE));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Line());
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                }
                                if (Game.getCiv(Game.player.iCivID).diplomacy.haveNonAggressionPact(InGame_Civ.iActiveCivID)) {
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("NonAggressionPact"), "", Images.nonAggression, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Expires") + ": ", "" + Game_Calendar.getDate_ByTurnID(Game.getCiv(Game.player.iCivID).diplomacy.nonAggressionPact.get(InGame_Civ.iActiveCivID).iTurnID), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Line());
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                }
                                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Opinion") + ": ", ((Game.getCiv(iCivB).diplomacy.getRelation(iCivFrom) > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivB).diplomacy.getRelation(iCivFrom), 10), Images.relations, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, DiplomacyManager.getOpinion_Color((int)Game.getCiv(iCivB).diplomacy.getRelation(iCivFrom))));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                this.menuElementHover = new MenuElement_Hover(nElements);
                            }
                            catch (final Exception ex) {
                                this.menuElementHover = null;
                            }
                            if (Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.getRelation(Game.player.iCivID) <= GameValues.war.RELATIONS_TO_DECLARE_WAR) {
                                this.menuElementHover = null;
                            }
                        }
                        
                        @Override
                        public int getSFX() {
                            return SoundsManager.SOUND_CLICK_TOP;
                        }
                    });
                    tempElements.add(new ButtonDiplomacy(Game.lang.get("Ultimatum"), Images.war, buttonX, buttonY, buttonW, buttonH) {
                        @Override
                        public void actionElement() {
                            if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 12) {
                                Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                                Game.menuManager.setVisibleInGame_PopUp(false);
                            }
                            else {
                                Game.menuManager.setVisibleInGame_Civ(false);
                                Game.menuManager.rebuildInGame_DeclareWar(InGame_Civ.iActiveCivID);
                                Game.mapModes.setActiveViewID(Game.mapModes.MODE_DECLARE_WAR);
                                if (Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.getRelation(Game.player.iCivID) > GameValues.war.RELATIONS_TO_DECLARE_WAR) {
                                    Game.menuManager.addToast_Error(Game.lang.get("ToDeclareWarTheRelationsBetweenCivilizationsMustBeBelowX", GameValues.war.RELATIONS_TO_DECLARE_WAR), Images.relations);
                                }
                                if (Game.getCiv(Game.player.iCivID).diplomacy.haveNonAggressionPact(InGame_Civ.iActiveCivID)) {
                                    Game.menuManager.addToast_Error(Game.lang.get("NonAggressionPact") + ": " + Game.getCiv(Game.player.iCivID).getCivName() + " - " + Game.getCiv(InGame_Civ.iActiveCivID).getCivName(), Images.nonAggression);
                                }
                                if (Game.getCiv(Game.player.iCivID).diplomacy.haveAlliance(InGame_Civ.iActiveCivID) || Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.haveAlliance(Game.player.iCivID)) {
                                    Game.menuManager.addToast_Error(Game.lang.get("Alliance") + ": " + Game.getCiv(Game.player.iCivID).getCivName() + " - " + Game.getCiv(InGame_Civ.iActiveCivID).getCivName(), Images.alliance);
                                }
                            }
                        }
                        
                        @Override
                        public Color getColorHover1() {
                            return new Color(DiplomacyManager.COLOR_WAR.r, DiplomacyManager.COLOR_WAR.g, DiplomacyManager.COLOR_WAR.b, 0.15f);
                        }
                        
                        @Override
                        public Color getColorHover2() {
                            return new Color(DiplomacyManager.COLOR_WAR.r, DiplomacyManager.COLOR_WAR.g, DiplomacyManager.COLOR_WAR.b, 0.5f);
                        }
                        
                        @Override
                        public void buildElementHover() {
                            try {
                                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                                final int iCivB = InGame_Civ.iActiveCivID;
                                final int iCivFrom = Game.player.iCivID;
                                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("DeclareWar"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.war, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                if (Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.getRelation(Game.player.iCivID) > GameValues.war.RELATIONS_TO_DECLARE_WAR) {
                                    nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("ToDeclareWarTheRelationsBetweenCivilizationsMustBeBelowX", GameValues.war.RELATIONS_TO_DECLARE_WAR), CFG.FONT_REGULAR_SMALL, Colors.HOVER_NEGATIVE));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Line());
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                }
                                if (Game.getCiv(Game.player.iCivID).diplomacy.haveNonAggressionPact(InGame_Civ.iActiveCivID)) {
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("NonAggressionPact"), "", Images.nonAggression, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Expires") + ": ", "" + Game_Calendar.getDate_ByTurnID(Game.getCiv(Game.player.iCivID).diplomacy.nonAggressionPact.get(InGame_Civ.iActiveCivID).iTurnID), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Line());
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                }
                                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Opinion") + ": ", ((Game.getCiv(iCivB).diplomacy.getRelation(iCivFrom) > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivB).diplomacy.getRelation(iCivFrom), 10), Images.relations, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, DiplomacyManager.getOpinion_Color((int)Game.getCiv(iCivB).diplomacy.getRelation(iCivFrom))));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                this.menuElementHover = new MenuElement_Hover(nElements);
                            }
                            catch (final Exception ex) {
                                this.menuElementHover = null;
                            }
                            if (Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.getRelation(Game.player.iCivID) <= GameValues.war.RELATIONS_TO_DECLARE_WAR) {
                                this.menuElementHover = null;
                            }
                        }
                        
                        @Override
                        public int getSFX() {
                            return SoundsManager.SOUND_CLICK_TOP;
                        }
                    });
                }
                tempElements.add(new ButtonDiplomacy(Game.getCiv(Game.player.iCivID).diplomacy.isImprovingRelations(InGame_Civ.iActiveCivID) ? Game.lang.get("StopImprovingRelations") : Game.lang.get("ImproveRelations"), Images.relationsUp, buttonX, buttonY, buttonW, buttonH, !Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.isRival(Game.player.iCivID) && !Game.getCiv(Game.player.iCivID).diplomacy.isRival(InGame_Civ.iActiveCivID)) {
                    @Override
                    public void actionElement() {
                        if (DiplomacyManager.isAtWar(Game.player.iCivID, InGame_Civ.iActiveCivID)) {
                            Game.menuManager.addToastNegative("", Game.lang.get("WeAreAtWar"), Images.war);
                        }
                        else if (Game.getCiv(Game.player.iCivID).diplomacy.isRival(InGame_Civ.iActiveCivID) || Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.isRival(Game.player.iCivID)) {
                            Game.menuManager.addToastNegative("", Game.lang.get("WeAreRivals"), Images.rivals);
                        }
                        else if (Game.getCiv(Game.player.iCivID).diplomacy.isImprovingRelations(InGame_Civ.iActiveCivID)) {
                            Game.getCiv(Game.player.iCivID).diplomacy.removeImproveRelations(InGame_Civ.iActiveCivID);
                            Game.menuManager.addToastNegative(Game.lang.get("Removed") + ": ", "" + Game.getCiv(InGame_Civ.iActiveCivID).getCivName(), Images.relationsDown);
                            ProvinceDraw.addDiplomacyLines(Game.getCiv(InGame_Civ.iActiveCivID).getCapitalProvinceID(), Game.getCiv(Game.player.iCivID).getCapitalProvinceID(), Colors.HOVER_POSITIVE);
                        }
                        else if (Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.getRelation(Game.player.iCivID) >= GameValues.diplomacy.DIPLOMACY_IMPROVE_RELATIONS_MAX) {
                            Game.menuManager.addToastInsufficient(Game.lang.get("MaximumOpinion") + ": ", "" + CFG.getPrecision2(GameValues.diplomacy.DIPLOMACY_IMPROVE_RELATIONS_MAX, 1), Images.relations);
                        }
                        else {
                            Game.getCiv(Game.player.iCivID).diplomacy.addImproveRelations(Game.player.iCivID, InGame_Civ.iActiveCivID);
                            ProvinceDraw.addDiplomacyLines(Game.getCiv(Game.player.iCivID).getCapitalProvinceID(), Game.getCiv(InGame_Civ.iActiveCivID).getCapitalProvinceID(), Colors.HOVER_POSITIVE);
                            Game.menuManager.addToastPositive(Game.lang.get("ImprovingRelations") + ": ", "" + Game.getCiv(InGame_Civ.iActiveCivID).getCivName(), Images.relationsDown);
                            final MenuManager menuManager = Game.menuManager;
                            MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Civ.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Civ.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                @Override
                                public Color getColor() {
                                    return DiplomacyManager.COLOR_GREEN;
                                }
                            });
                        }
                        Game.menuManager.rebuildInGame_Right();
                        InGame_Civ.rebuildMenu();
                    }
                    
                    @Override
                    public void buildElementHover() {
                        this.menuElementHover = InGame_Civ.getHoverBetweenCivilizations(InGame_Civ.iActiveCivID, Game.player.iCivID, true, false);
                    }
                    
                    @Override
                    public Color getColorHover1() {
                        return new Color(DiplomacyManager.COLOR_GREEN.r, DiplomacyManager.COLOR_GREEN.g, DiplomacyManager.COLOR_GREEN.b, 0.15f);
                    }
                    
                    @Override
                    public Color getColorHover2() {
                        return new Color(DiplomacyManager.COLOR_GREEN.r, DiplomacyManager.COLOR_GREEN.g, DiplomacyManager.COLOR_GREEN.b, 0.5f);
                    }
                });
                tempElements.add(new ButtonDiplomacy(Game.getCiv(Game.player.iCivID).diplomacy.isDamagingRelations(InGame_Civ.iActiveCivID) ? Game.lang.get("StopDamagingRelations") : Game.lang.get("DamageRelations"), Images.relationsDown, buttonX, buttonY, buttonW, buttonH) {
                    @Override
                    public void actionElement() {
                        if (DiplomacyManager.isAtWar(Game.player.iCivID, InGame_Civ.iActiveCivID)) {
                            Game.menuManager.addToastNegative("", Game.lang.get("WeAreAtWar"), Images.war);
                        }
                        else if (Game.getCiv(Game.player.iCivID).diplomacy.isDamagingRelations(InGame_Civ.iActiveCivID)) {
                            Game.getCiv(Game.player.iCivID).diplomacy.removeDamageRelations(InGame_Civ.iActiveCivID);
                            Game.menuManager.addToastNegative(Game.lang.get("Removed") + ": ", "" + Game.getCiv(InGame_Civ.iActiveCivID).getCivName(), Images.relationsDown);
                            ProvinceDraw.addDiplomacyLines(Game.getCiv(InGame_Civ.iActiveCivID).getCapitalProvinceID(), Game.getCiv(Game.player.iCivID).getCapitalProvinceID(), Colors.HOVER_NEGATIVE);
                        }
                        else if (Game.getCiv(InGame_Civ.iActiveCivID).diplomacy.getRelation(Game.player.iCivID) <= GameValues.diplomacy.DIPLOMACY_DAMAGE_RELATIONS_MAX) {
                            Game.menuManager.addToastInsufficient(Game.lang.get("MaximumOpinion") + ": ", "" + CFG.getPrecision2(GameValues.diplomacy.DIPLOMACY_DAMAGE_RELATIONS_MAX, 1), Images.relations);
                        }
                        else {
                            Game.getCiv(Game.player.iCivID).diplomacy.addDamageRelations(Game.player.iCivID, InGame_Civ.iActiveCivID);
                            ProvinceDraw.addDiplomacyLines(Game.getCiv(Game.player.iCivID).getCapitalProvinceID(), Game.getCiv(InGame_Civ.iActiveCivID).getCapitalProvinceID(), Colors.HOVER_NEGATIVE);
                            Game.menuManager.addToastNegative(Game.lang.get("DamagingRelations") + ": ", "" + Game.getCiv(InGame_Civ.iActiveCivID).getCivName(), Images.relationsDown);
                            final MenuManager menuManager = Game.menuManager;
                            MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Civ.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Civ.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                @Override
                                public Color getColor() {
                                    return DiplomacyManager.COLOR_RED;
                                }
                            });
                        }
                        Game.menuManager.rebuildInGame_Right();
                        InGame_Civ.rebuildMenu();
                    }
                    
                    @Override
                    public void buildElementHover() {
                        this.menuElementHover = InGame_Civ.getHoverBetweenCivilizations(InGame_Civ.iActiveCivID, Game.player.iCivID, false, true);
                    }
                    
                    @Override
                    public Color getColorHover1() {
                        return new Color(DiplomacyManager.COLOR_RED.r, DiplomacyManager.COLOR_RED.g, DiplomacyManager.COLOR_RED.b, 0.15f);
                    }
                    
                    @Override
                    public Color getColorHover2() {
                        return new Color(DiplomacyManager.COLOR_RED.r, DiplomacyManager.COLOR_RED.g, DiplomacyManager.COLOR_RED.b, 0.5f);
                    }
                });
                tempElements.add(new ButtonDiplomacy(Game.lang.get("SendAnInsult"), Images.insult, buttonX, buttonY, buttonW, buttonH) {
                    @Override
                    public void actionElement() {
                        if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 13 && InGame_SendInsult.iCivID == InGame_Civ.iActiveCivID) {
                            Game.menuManager.setVisibleInGame_PopUp(false);
                        }
                        else {
                            Game.menuManager.rebuildInGame_SendInsult(InGame_Civ.iActiveCivID);
                        }
                    }
                    
                    @Override
                    public void buildElementHover() {
                        this.menuElementHover = InGame_Civ.getHoverInsult(Game.player.iCivID, InGame_Civ.iActiveCivID);
                    }
                    
                    @Override
                    public Color getColorHover1() {
                        return new Color(DiplomacyManager.COLOR_INSULT.r, DiplomacyManager.COLOR_INSULT.g, DiplomacyManager.COLOR_INSULT.b, 0.15f);
                    }
                    
                    @Override
                    public Color getColorHover2() {
                        return new Color(DiplomacyManager.COLOR_INSULT.r, DiplomacyManager.COLOR_INSULT.g, DiplomacyManager.COLOR_INSULT.b, 0.5f);
                    }
                    
                    @Override
                    public int getSFX() {
                        return SoundsManager.SOUND_CLICK_TOP;
                    }
                });
                tempElements.add(new ButtonDiplomacy(Game.getCiv(Game.player.iCivID).diplomacy.isRival(InGame_Civ.iActiveCivID) ? Game.lang.get("EndRivalry") : Game.lang.get("Rivalry"), Images.rivals, buttonX, buttonY, buttonW, buttonH, true) {
                    @Override
                    public void actionElement() {
                        if (Game.getCiv(Game.player.iCivID).diplomacy.isRival(InGame_Civ.iActiveCivID)) {
                            if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 40 && InGame_Rivals_End.endRivalryWithCivID == InGame_Civ.iActiveCivID) {
                                Game.menuManager.setVisibleInGame_PopUp(false);
                            }
                            else {
                                InGame_Rivals_End.endRivalryWithCivID = InGame_Civ.iActiveCivID;
                                Game.menuManager.rebuildInGame_Rivals_End();
                            }
                        }
                        else if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 25 && InGame_Rivals.rivalCivID == InGame_Civ.iActiveCivID) {
                            Game.menuManager.setVisibleInGame_PopUp(false);
                        }
                        else {
                            InGame_Rivals.rivalCivID = InGame_Civ.iActiveCivID;
                            InGame_Rivals.backToRivalsList = false;
                            Game.menuManager.rebuildInGame_Rivals();
                        }
                    }
                    
                    @Override
                    public Color getColorHover1() {
                        return new Color(DiplomacyManager.COLOR_RED.r, DiplomacyManager.COLOR_RED.g, DiplomacyManager.COLOR_RED.b, 0.15f);
                    }
                    
                    @Override
                    public Color getColorHover2() {
                        return new Color(DiplomacyManager.COLOR_RED.r, DiplomacyManager.COLOR_RED.g, DiplomacyManager.COLOR_RED.b, 0.5f);
                    }
                    
                    @Override
                    public int getSFX() {
                        return SoundsManager.SOUND_CLICK_TOP;
                    }
                });
                tempElements.add(new ButtonDiplomacy(Game.lang.get("FormAlliance"), Images.alliance, buttonX, buttonY, buttonW, buttonH) {
                    @Override
                    public void actionElement() {
                        if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 18 && InGame_Alliance.iCivID == InGame_Civ.iActiveCivID) {
                            Game.menuManager.setVisibleInGame_PopUp(false);
                        }
                        else {
                            Game.menuManager.rebuildInGame_Alliance(InGame_Civ.iActiveCivID);
                        }
                    }
                    
                    @Override
                    public Color getColorHover1() {
                        return new Color(DiplomacyManager.COLOR_ALLIANCE.r, DiplomacyManager.COLOR_ALLIANCE.g, DiplomacyManager.COLOR_ALLIANCE.b, 0.15f);
                    }
                    
                    @Override
                    public Color getColorHover2() {
                        return new Color(DiplomacyManager.COLOR_ALLIANCE.r, DiplomacyManager.COLOR_ALLIANCE.g, DiplomacyManager.COLOR_ALLIANCE.b, 0.5f);
                    }
                    
                    @Override
                    public int getSFX() {
                        return SoundsManager.SOUND_CLICK_TOP;
                    }
                });
                tempElements.add(new ButtonDiplomacy(Game.lang.get("DefensivePact"), Images.defensivePact, buttonX, buttonY, buttonW, buttonH) {
                    @Override
                    public void actionElement() {
                        if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 16 && InGame_DefensivePact.iCivID == InGame_Civ.iActiveCivID) {
                            Game.menuManager.setVisibleInGame_PopUp(false);
                        }
                        else {
                            Game.menuManager.rebuildInGame_DefensivePact(InGame_Civ.iActiveCivID);
                        }
                    }
                    
                    @Override
                    public Color getColorHover1() {
                        return new Color(DiplomacyManager.COLOR_DEFENSIVE_PACT.r, DiplomacyManager.COLOR_DEFENSIVE_PACT.g, DiplomacyManager.COLOR_DEFENSIVE_PACT.b, 0.15f);
                    }
                    
                    @Override
                    public Color getColorHover2() {
                        return new Color(DiplomacyManager.COLOR_DEFENSIVE_PACT.r, DiplomacyManager.COLOR_DEFENSIVE_PACT.g, DiplomacyManager.COLOR_DEFENSIVE_PACT.b, 0.5f);
                    }
                    
                    @Override
                    public int getSFX() {
                        return SoundsManager.SOUND_CLICK_TOP;
                    }
                });
                tempElements.add(new ButtonDiplomacy(Game.lang.get("NonAggressionPact").replace("-", " "), Images.nonAggression, buttonX, buttonY, buttonW, buttonH) {
                    @Override
                    public void actionElement() {
                        if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 17 && InGame_NonAggression.iCivID == InGame_Civ.iActiveCivID) {
                            Game.menuManager.setVisibleInGame_PopUp(false);
                        }
                        else {
                            Game.menuManager.rebuildInGame_NonAggression(InGame_Civ.iActiveCivID);
                        }
                    }
                    
                    @Override
                    public Color getColorHover1() {
                        return new Color(DiplomacyManager.COLOR_NON_AGGRESSION_PACT.r, DiplomacyManager.COLOR_NON_AGGRESSION_PACT.g, DiplomacyManager.COLOR_NON_AGGRESSION_PACT.b, 0.15f);
                    }
                    
                    @Override
                    public Color getColorHover2() {
                        return new Color(DiplomacyManager.COLOR_NON_AGGRESSION_PACT.r, DiplomacyManager.COLOR_NON_AGGRESSION_PACT.g, DiplomacyManager.COLOR_NON_AGGRESSION_PACT.b, 0.5f);
                    }
                    
                    @Override
                    public int getSFX() {
                        return SoundsManager.SOUND_CLICK_TOP;
                    }
                });
                tempElements.add(new ButtonDiplomacy(Game.lang.get("DemandMilitaryAccess"), Images.militaryAccess, buttonX, buttonY, buttonW, buttonH) {
                    @Override
                    public void actionElement() {
                        if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 19 && InGame_DemandMilitaryAccess.iCivID == InGame_Civ.iActiveCivID) {
                            Game.menuManager.setVisibleInGame_PopUp(false);
                        }
                        else {
                            Game.menuManager.rebuildInGame_DemandMilitaryAccess(InGame_Civ.iActiveCivID);
                        }
                    }
                    
                    @Override
                    public Color getColorHover1() {
                        return new Color(DiplomacyManager.COLOR_MILITARY_ACCESS.r, DiplomacyManager.COLOR_MILITARY_ACCESS.g, DiplomacyManager.COLOR_MILITARY_ACCESS.b, 0.15f);
                    }
                    
                    @Override
                    public Color getColorHover2() {
                        return new Color(DiplomacyManager.COLOR_MILITARY_ACCESS.r, DiplomacyManager.COLOR_MILITARY_ACCESS.g, DiplomacyManager.COLOR_MILITARY_ACCESS.b, 0.5f);
                    }
                    
                    @Override
                    public int getSFX() {
                        return SoundsManager.SOUND_CLICK_TOP;
                    }
                });
                tempElements.add(new ButtonDiplomacy(Game.lang.get("OfferMilitaryAccess"), Images.militaryAccess2, buttonX, buttonY, buttonW, buttonH) {
                    @Override
                    public void actionElement() {
                        if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 20 && InGame_OfferMilitaryAccess.iCivID == InGame_Civ.iActiveCivID) {
                            Game.menuManager.setVisibleInGame_PopUp(false);
                        }
                        else {
                            Game.menuManager.rebuildInGame_OfferMilitaryAccess(InGame_Civ.iActiveCivID);
                        }
                    }
                    
                    @Override
                    public Color getColorHover1() {
                        return new Color(DiplomacyManager.COLOR_MILITARY_ACCESS.r, DiplomacyManager.COLOR_MILITARY_ACCESS.g, DiplomacyManager.COLOR_MILITARY_ACCESS.b, 0.15f);
                    }
                    
                    @Override
                    public Color getColorHover2() {
                        return new Color(DiplomacyManager.COLOR_MILITARY_ACCESS.r, DiplomacyManager.COLOR_MILITARY_ACCESS.g, DiplomacyManager.COLOR_MILITARY_ACCESS.b, 0.5f);
                    }
                    
                    @Override
                    public int getSFX() {
                        return SoundsManager.SOUND_CLICK_TOP;
                    }
                });
                tempElements.add(new ButtonDiplomacy(Game.lang.get("GuaranteeIndependence"), Images.guaranteeIndependence, buttonX, buttonY, buttonW, buttonH) {
                    @Override
                    public void actionElement() {
                        if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 21 && InGame_Guarantee.iCivID == InGame_Civ.iActiveCivID) {
                            Game.menuManager.setVisibleInGame_PopUp(false);
                        }
                        else {
                            Game.menuManager.rebuildInGame_Guarantee(InGame_Civ.iActiveCivID);
                        }
                    }
                    
                    @Override
                    public Color getColorHover1() {
                        return new Color(DiplomacyManager.COLOR_GUARANTEE.r, DiplomacyManager.COLOR_GUARANTEE.g, DiplomacyManager.COLOR_GUARANTEE.b, 0.15f);
                    }
                    
                    @Override
                    public Color getColorHover2() {
                        return new Color(DiplomacyManager.COLOR_GUARANTEE.r, DiplomacyManager.COLOR_GUARANTEE.g, DiplomacyManager.COLOR_GUARANTEE.b, 0.5f);
                    }
                    
                    @Override
                    public int getSFX() {
                        return SoundsManager.SOUND_CLICK_TOP;
                    }
                });
                tempElements.add(new ButtonDiplomacy(Game.lang.get("SendGift"), Images.gift, buttonX, buttonY, buttonW, buttonH) {
                    @Override
                    public void actionElement() {
                        if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 22 && InGame_SendGift.iCivID == InGame_Civ.iActiveCivID) {
                            Game.menuManager.setVisibleInGame_PopUp(false);
                        }
                        else {
                            Game.menuManager.rebuildInGame_SendGift(InGame_Civ.iActiveCivID);
                        }
                    }
                    
                    @Override
                    public Color getColorHover1() {
                        return new Color(Colors.HOVER_GOLD.r, Colors.HOVER_GOLD.g, Colors.HOVER_GOLD.b, 0.15f);
                    }
                    
                    @Override
                    public Color getColorHover2() {
                        return new Color(Colors.HOVER_GOLD.r, Colors.HOVER_GOLD.g, Colors.HOVER_GOLD.b, 0.5f);
                    }
                    
                    @Override
                    public int getSFX() {
                        return SoundsManager.SOUND_CLICK_TOP;
                    }
                });
                if (Game.player.iCivID != InGame_Civ.iActiveCivID && Game.player.iCivID == Game.getCiv(InGame_Civ.iActiveCivID).getPuppetOfCivID()) {
                    tempElements.add(new ButtonDiplomacy(Game.lang.get("LiberateCivilization"), Images.vassalBig, buttonX, buttonY, buttonW, buttonH) {
                        @Override
                        public void actionElement() {
                            if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 43 && InGame_LiberateCivilization.iCivID == InGame_Civ.iActiveCivID) {
                                Game.menuManager.setVisibleInGame_PopUp(false);
                            }
                            else {
                                Game.menuManager.rebuildInGame_LiberateCivilization(InGame_Civ.iActiveCivID);
                            }
                        }
                        
                        @Override
                        public int getSFX() {
                            return SoundsManager.SOUND_CLICK_TOP;
                        }
                    });
                }
                tempElements.add(new ButtonDiplomacy(Game.lang.get("SendSpy"), Images.spy, buttonX, buttonY, buttonW, buttonH) {
                    @Override
                    public void actionElement() {
                        if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 27 && InGame_SendGift.iCivID == InGame_Civ.iActiveCivID) {
                            Game.menuManager.setVisibleInGame_PopUp(false);
                        }
                        else {
                            Game.menuManager.rebuildInGame_SendSpy(InGame_Civ.iActiveCivID);
                        }
                    }
                    
                    @Override
                    public Color getColorHover1() {
                        return new Color(DiplomacyManager.COLOR_SPY.r, DiplomacyManager.COLOR_SPY.g, DiplomacyManager.COLOR_SPY.b, 0.15f);
                    }
                    
                    @Override
                    public Color getColorHover2() {
                        return new Color(DiplomacyManager.COLOR_SPY.r, DiplomacyManager.COLOR_SPY.g, DiplomacyManager.COLOR_SPY.b, 0.5f);
                    }
                    
                    @Override
                    public int getSFX() {
                        return SoundsManager.SOUND_CLICK_TOP;
                    }
                });
                tempElements.add(new ButtonDiplomacy(Game.lang.get("ShareTechnology"), Game_Calendar.IMG_TECHNOLOGY, buttonX, buttonY, buttonW, buttonH) {
                    @Override
                    public void actionElement() {
                        if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 44 && InGame_ShareTechnology.shareWithCivID == InGame_Civ.iActiveCivID) {
                            Game.menuManager.setVisibleInGame_PopUp(false);
                        }
                        else {
                            Game.menuManager.rebuildInGame_ShareTechnology(InGame_Civ.iActiveCivID);
                        }
                    }
                    
                    @Override
                    public int getSFX() {
                        return SoundsManager.SOUND_CLICK_TOP;
                    }
                });
                tempElements.add(new ButtonDiplomacy(Game.lang.get("SellProvince"), Images.provinces, buttonX, buttonY, buttonW, buttonH) {
                    @Override
                    public void actionElement() {
                        if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 41 && InGame_SellProvince.sellToCivID == InGame_Civ.iActiveCivID) {
                            Game.menuManager.setVisibleInGame_PopUp(false);
                        }
                        else {
                            Game.menuManager.setVisibleInGame_Civ(false);
                            Game.menuManager.rebuildInGame_SellProvince(InGame_Civ.iActiveCivID);
                        }
                    }
                    
                    @Override
                    public int getSFX() {
                        return SoundsManager.SOUND_CLICK_TOP;
                    }
                });
                tempElements.add(new ButtonDiplomacy(Game.lang.get("CompareCivilizations"), Images.compare, buttonX, buttonY, buttonW, buttonH) {
                    @Override
                    public void actionElement() {
                        InGame_Civ_Compare.civLeft = Game.player.iCivID;
                        InGame_Civ_Compare.civRight = InGame_Civ.iActiveCivID;
                        Game.menuManager.rebuildInGame_Civ_Compare();
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("CompareCivilizations"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.compare, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.getCiv(InGame_Civ.iActiveCivID).getCivName(), "", InGame_Civ.iActiveCivID, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.getCiv(Game.player.iCivID).getCivName(), "", Game.player.iCivID, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                    
                    @Override
                    public int getSFX() {
                        return SoundsManager.SOUND_CLICK_TOP;
                    }
                });
            }
            for (int k = 0, iSize2 = tempElements.size(); k < iSize2; ++k) {
                tempElements.get(k).setPosX(buttonX);
                tempElements.get(k).setPosY(buttonY);
                buttonX += tempElements.get(k).getWidth() + CFG.PADDING;
                menuElements.add(tempElements.get(k));
                if ((k + 1) % 4 == 0 || k == iSize2 - 1) {
                    buttonY += tempElements.get(k).getHeight() + CFG.PADDING;
                    buttonX = paddingLeft;
                }
            }
            tempElements.clear();
        }
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG(Game.getCiv(InGame_Civ.iActiveCivID).getCivName(), false, false, Images.title500) {
            @Override
            public long getTime() {
                return InGame_Civ.lTime2;
            }
            
            @Override
            public String getText() {
                if (Keyboard.keyboardActionType == Keyboard.KeyboardActionType.INGAME_RENAME_CIV) {
                    return InGame_Civ.renameCiv + Keyboard.getKeyboardVerticalLine();
                }
                return super.getText();
            }
            
            @Override
            public void action() {
                super.action();
                if (Keyboard.keyboardActionType != Keyboard.KeyboardActionType.INGAME_RENAME_CIV) {
                    InGame_Civ.renameCiv = Game.getCiv(InGame_Civ.iActiveCivID).getCivName();
                    Game.keyboard.showKeyboard(Keyboard.KeyboardActionType.INGAME_RENAME_CIV, Game.getCiv(InGame_Civ.iActiveCivID).getCivName());
                    Game.menuManager.addToastGold(Game.lang.get("CivilizationName") + ": " + Game.getCiv(InGame_Civ.iActiveCivID).getCivName(), Images.council);
                }
                else {
                    Game.keyboard.hideKeyboard();
                }
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
        InGame_Court_Government.loadFormableFlags();
        InGame_Civ.relationsMode = false;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_Civ.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_Civ.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.civInfoOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.civInfoOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.civInfoOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_Civ.lTime = CFG.currentTimeMillis;
        InGame_Civ.lTime2 = InGame_Civ.lTime;
        final Keyboard keyboard = Game.keyboard;
        if (Keyboard.keyboardMode && Keyboard.keyboardActionType == Keyboard.KeyboardActionType.INGAME_RENAME_CIV) {
            Game.keyboard.hideKeyboard();
        }
    }
    
    @Override
    public void updateLanguage() {
        super.updateLanguage();
        this.getTitle().setText(Game.getCiv(InGame_Civ.iActiveCivID).getCivName());
    }
    
    public static final void actionCivBonuses() {
        if (Game.menuManager.getVisibleInGame_CivBonuses()) {
            Game.menuManager.setVisibleInGame_CivBonuses(false);
        }
        else {
            InGame_CivBonuses.iCivID = InGame_Civ.iActiveCivID;
            Game.menuManager.rebuildInGame_CivBonuses();
            Game.menuManager.setVisibleInGame_CivBonuses(true);
            if (Game.menuManager.getVisibleInGame_Armies()) {
                Game.menuManager.setVisibleInGame_Armies(false);
            }
        }
    }
    
    @Override
    public void actionCloseMenu() {
        super.actionCloseMenu();
        actionOnClose();
        final Keyboard keyboard = Game.keyboard;
        if (Keyboard.keyboardMode && Keyboard.keyboardActionType == Keyboard.KeyboardActionType.INGAME_RENAME_CIV) {
            Game.keyboard.hideKeyboard();
        }
    }
    
    public static final void actionOnOpen() {
        if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_DIPLOMACY) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DIPLOMACY);
        }
        ProvinceBorderManager.updateDrawProvinceBorder_SelectCiv_ByCivID(InGame_Civ.iActiveCivID);
        ProvinceDraw.buildBiggestCitiesLines(InGame_Civ.iActiveCivID);
    }
    
    public static final void actionOnClose() {
        if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DIPLOMACY || Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DIPLOMACY_IMPROVE_RELATIONS || Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DIPLOMACY_DAMAGE_RELATIONS) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        }
        Game.iActiveProvince = -1;
        ProvinceBorderManager.clearProvinceBorder();
        ProvinceDraw.clearBiggestCities();
        final Keyboard keyboard = Game.keyboard;
        if (Keyboard.keyboardMode && Keyboard.keyboardActionType == Keyboard.KeyboardActionType.INGAME_RENAME_CIV) {
            Game.keyboard.hideKeyboard();
        }
    }
    
    public static final void actionRivals() {
        if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 38) {
            Game.menuManager.setVisibleInGame_PopUp(false);
        }
        else {
            Game.menuManager.rebuildInGame_RivalsList();
        }
    }
    
    public static MenuElement_Hover getHoverBetweenCivilizations_RightMenu(final int iCivA, final int iCivB, final boolean showImprovingRelations, final boolean showDamagingRelations) {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        if (showImprovingRelations) {
            nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(iCivA, Game.lang.get("ImprovingRelations")));
        }
        else if (showDamagingRelations) {
            nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(iCivA, Game.lang.get("DamagingRelations")));
        }
        else {
            nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(iCivB, Game.getCiv(iCivA).getCivName()));
        }
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Opinion") + ": ", ((Game.getCiv(iCivA).diplomacy.getRelation(iCivB) > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivA).diplomacy.getRelation(iCivB), 10), Images.relations, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, DiplomacyManager.getOpinion_Color((int)Game.getCiv(iCivA).diplomacy.getRelation(iCivB))));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Empty());
        nElements.add(new MenuElement_HoverElement(nData, false));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_FlagTitle(iCivA, 0, CFG.PADDING));
        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("AttitudeTowards"), CFG.FONT_REGULAR));
        nData.add(new MenuElement_HoverElement_Type_FlagTitle(iCivB, CFG.PADDING, CFG.PADDING));
        nElements.add(new MenuElement_HoverElement(nData, false));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_TextTitle(DiplomacyManager.getOpinion_String((int)Game.getCiv(iCivA).diplomacy.getRelation(iCivB)) + "", CFG.FONT_BOLD, DiplomacyManager.getOpinion_Color((int)Game.getCiv(iCivA).diplomacy.getRelation(iCivB))));
        nElements.add(new MenuElement_HoverElement(nData, false));
        nData.clear();
        if (showImprovingRelations) {
            nData.add(new MenuElement_HoverElement_Type_Empty());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AfterXDays", GameValues.diplomacy.DIPLOMACY_IMPROVE_RELATIONS_TIME) + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT2));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Opinion") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(DiplomacyManager.getRelationImprove(iCivB, iCivA), 100), CFG.FONT_BOLD_SMALL, DiplomacyManager.COLOR_GREEN));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.relations, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        else if (showDamagingRelations) {
            nData.add(new MenuElement_HoverElement_Type_Empty());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AfterXDays", GameValues.diplomacy.DIPLOMACY_DAMAGE_RELATIONS_TIME) + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT2));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Opinion") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(DiplomacyManager.getRelationDamage(iCivB, iCivA), 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.relations, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData, false));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Prestige") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(Game.getCiv(iCivB).iCivRankScore, 1), CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
        nData.add(new MenuElement_HoverElement_Type_Image(CivilizationRanking.getCivilizationRanking_IMG_STAR_CIVID(iCivB), CFG.PADDING, 0));
        nData.add(new MenuElement_HoverElement_Type_Flag(iCivB, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData, false));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Prestige") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(Game.getCiv(iCivA).iCivRankScore, 1), CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
        nData.add(new MenuElement_HoverElement_Type_Image(CivilizationRanking.getCivilizationRanking_IMG_STAR_CIVID(iCivA), CFG.PADDING, 0));
        nData.add(new MenuElement_HoverElement_Type_Flag(iCivA, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData, false));
        nData.clear();
        return new MenuElement_Hover(nElements);
    }
    
    public static MenuElement_Hover getHoverBetweenCivilizations(final int iCivA, final int iCivB, final boolean showImprovingRelations, final boolean showDamagingRelations) {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        if (showImprovingRelations) {
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.getCiv(iCivB).diplomacy.isImprovingRelations(iCivA) ? Game.lang.get("StopImprovingRelations") : Game.lang.get("ImproveRelations"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.relationsUp, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            if (Game.getCiv(iCivA).diplomacy.isRival(iCivB) || Game.getCiv(iCivB).diplomacy.isRival(iCivA)) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("WeAreRivals"), CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.rivals, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            else {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("XPerMonth", "" + CFG.getPrecision2(GameValues.diplomacy.DIPLOMACY_IMPROVE_RELATIONS_COST_PER_MONTH, 100)), CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.diplomacy, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AfterXDays", GameValues.diplomacy.DIPLOMACY_IMPROVE_RELATIONS_TIME) + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT2));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Opinion") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(DiplomacyManager.getRelationImprove(iCivB, iCivA), 100), CFG.FONT_BOLD_SMALL, DiplomacyManager.COLOR_GREEN));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.relations, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(" = " + CFG.getPrecision2(GameValues.diplomacy.DIPLOMACY_IMPROVE_RELATIONS_VALUE, 100) + " + " + CFG.getPrecision2(GameValues.diplomacy.DIPLOMACY_IMPROVE_RELATIONS_VALUE_PRESTIGE, 100) + " * min(1, " + Game.lang.get("Prestige") + " ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                nData.add(new MenuElement_HoverElement_Type_Flag(iCivB, 0, 0));
                nData.add(new MenuElement_HoverElement_Type_Text(" / " + Game.lang.get("Prestige") + " ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                nData.add(new MenuElement_HoverElement_Type_Flag(iCivA, 0, 0));
                nData.add(new MenuElement_HoverElement_Type_Text(" )", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
            }
        }
        else if (showDamagingRelations) {
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.getCiv(iCivB).diplomacy.isDamagingRelations(iCivA) ? Game.lang.get("StopDamagingRelations") : Game.lang.get("DamageRelations"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.relationsDown, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("XPerMonth", "" + CFG.getPrecision2(GameValues.diplomacy.DIPLOMACY_DAMAGE_RELATIONS_COST_PER_MONTH, 100)), CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.diplomacy, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AfterXDays", GameValues.diplomacy.DIPLOMACY_DAMAGE_RELATIONS_TIME) + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT2));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Opinion") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(DiplomacyManager.getRelationDamage(iCivB, iCivA), 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.relations, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (showImprovingRelations || showDamagingRelations) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Opinion") + ": ", ((Game.getCiv(iCivA).diplomacy.getRelation(iCivB) > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivA).diplomacy.getRelation(iCivB), 10), Images.relations, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, DiplomacyManager.getOpinion_Color((int)Game.getCiv(iCivA).diplomacy.getRelation(iCivB))));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Empty());
        nElements.add(new MenuElement_HoverElement(nData, false));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_FlagTitle(iCivA, 0, CFG.PADDING));
        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("AttitudeTowards"), CFG.FONT_REGULAR));
        nData.add(new MenuElement_HoverElement_Type_FlagTitle(iCivB, CFG.PADDING, CFG.PADDING));
        nData.add(new MenuElement_HoverElement_Type_TextTitle(DiplomacyManager.getOpinion_String((int)Game.getCiv(iCivA).diplomacy.getRelation(iCivB)) + "", CFG.FONT_REGULAR, DiplomacyManager.getOpinion_Color((int)Game.getCiv(iCivA).diplomacy.getRelation(iCivB))));
        nElements.add(new MenuElement_HoverElement(nData, false));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData, false));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Prestige") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(Game.getCiv(iCivB).iCivRankScore, 1), CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
        nData.add(new MenuElement_HoverElement_Type_Image(CivilizationRanking.getCivilizationRanking_IMG_STAR_CIVID(iCivB), CFG.PADDING, 0));
        nData.add(new MenuElement_HoverElement_Type_Flag(iCivB, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData, false));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Prestige") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(Game.getCiv(iCivA).iCivRankScore, 1), CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
        nData.add(new MenuElement_HoverElement_Type_Image(CivilizationRanking.getCivilizationRanking_IMG_STAR_CIVID(iCivA), CFG.PADDING, 0));
        nData.add(new MenuElement_HoverElement_Type_Flag(iCivA, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData, false));
        nData.clear();
        return new MenuElement_Hover(nElements);
    }
    
    public static MenuElement_Hover getHoverInsult(final int iCivFrom, final int iCivB) {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("SendAnInsult"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.insult, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Opinion") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_TextTitle(CFG.getPrecision2(GameValues.diplomacy.DIPLOMACY_SEND_AN_INSULT_DAMAGE, 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
        nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.relations, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Legacy") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_TextTitle(CFG.getPrecision2(GameValues.diplomacy.DIPLOMACY_SEND_AN_INSULT_COST_LEGACY, 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
        nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.legacy, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("DiplomacyPoints") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_TextTitle("-" + CFG.getPrecision2(GameValues.diplomacy.DIPLOMACY_SEND_INSULT_COST, 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
        nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.diplomacy, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_FlagTitle(iCivB, 0, CFG.PADDING));
        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("AttitudeTowards"), CFG.FONT_BOLD));
        nData.add(new MenuElement_HoverElement_Type_FlagTitle(iCivFrom, CFG.PADDING, CFG.PADDING));
        nData.add(new MenuElement_HoverElement_Type_TextTitle(DiplomacyManager.getOpinion_String((int)Game.getCiv(iCivB).diplomacy.getRelation(iCivFrom)) + "", CFG.FONT_BOLD, DiplomacyManager.getOpinion_Color((int)Game.getCiv(iCivB).diplomacy.getRelation(iCivFrom))));
        nElements.add(new MenuElement_HoverElement(nData, false));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Opinion") + ": ", ((Game.getCiv(iCivB).diplomacy.getRelation(iCivFrom) > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivB).diplomacy.getRelation(iCivFrom), 10), Images.relations, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, DiplomacyManager.getOpinion_Color((int)Game.getCiv(iCivB).diplomacy.getRelation(iCivFrom))));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        return new MenuElement_Hover(nElements);
    }
    
    public static final void rebuildMenu() {
        InGame_Civ.iRebuildToCivID = InGame_Civ.iActiveCivID;
        Game.menuManager.rebuildInGame_Civ_SavePos();
        InGame_Civ.lTime = 0L;
        InGame_Civ.lTime2 = 0L;
    }
    
    public static int getRankPopulation(final int civID) {
        int out = 1;
        final long num = Game.getCiv(civID).getPopulationTotal();
        for (int i = 1; i < civID; ++i) {
            if (Game.getCiv(i).getPopulationTotal() > num) {
                ++out;
            }
        }
        for (int i = civID + 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).getPopulationTotal() > num) {
                ++out;
            }
        }
        return out;
    }
    
    public int getRankEconomy(final int civID) {
        int out = 1;
        final float num = Game.getCiv(civID).getEconomyTotal();
        for (int i = 1; i < civID; ++i) {
            if (Game.getCiv(i).getEconomyTotal() > num) {
                ++out;
            }
        }
        for (int i = civID + 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).getEconomyTotal() > num) {
                ++out;
            }
        }
        return out;
    }
    
    public int getRankProvinces(final int civID) {
        int out = 1;
        final int num = Game.getCiv(civID).getNumOfProvinces();
        for (int i = 1; i < civID; ++i) {
            if (Game.getCiv(i).getNumOfProvinces() > num) {
                ++out;
            }
        }
        for (int i = civID + 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).getNumOfProvinces() > num) {
                ++out;
            }
        }
        return out;
    }
    
    public static int getRankCivsWithProvinces(final int civID) {
        int out = 0;
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).getNumOfProvinces() > 0) {
                ++out;
            }
        }
        return out;
    }
    
    public static MenuElement_Hover getHover_AggressiveExpansion(final int civID, final boolean addTitle) {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        if (addTitle) {
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Civilizations") + ": " + Game.lang.get("AggressiveExpansion"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.aggressiveExpansion, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AggressiveExpansion") + ": ", CFG.getPrecision2(Game.getCiv(civID).getAggressiveExpansion(), 100), Images.aggressiveExpansion, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("XDays", GameValues.aggressiveExpansion.GAME_UPDATE_AE_DECAY_TURNS) + ": ", "-" + CFG.getPrecision2(GameValues.aggressiveExpansion.AE_DECAY_PER_TICK, 100), Images.aggressiveExpansion, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AggressiveExpansion") + " > " + CFG.getPrecision2(GameValues.aggressiveExpansion.START_COALITION_IF_AE_OVER, 10) + " = ", Game.lang.get("Coalition"), Images.alliance, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_NEGATIVE));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        return new MenuElement_Hover(nElements);
    }
    
    public static MenuElement_Hover getHover_WarWeariness(final int civID, final boolean addTitle) {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        if (addTitle) {
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Civilizations") + ": " + Game.lang.get("WarWeariness"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.weariness, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("WarWeariness") + ": ", CFG.getPrecision2(Game.getCiv(civID).getWarWeariness(), 100) + "%", Images.weariness, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        float value = Game.getCiv(civID).getWarWeariness() * GameValues.warWeariness.WW_LEGACY_PER_POINT * 100.0f;
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Legacy") + ": ", ((value > 0.0f) ? "+" : "") + CFG.getPrecision2(value, 10) + "%", Images.legacy, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (value == 0.0f) ? Colors.HOVER_NEUTRAL : ((value > 0.0f) ? Colors.HOVER_POSITIVE : Colors.HOVER_NEGATIVE)));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        value = Game.getCiv(civID).getWarWeariness() * GameValues.warWeariness.WW_GOODS_PRODUCTION_PER_POINT * 100.0f;
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProducedGoods") + ": ", ((value > 0.0f) ? "+" : "") + CFG.getPrecision2(value, 10) + "%", Images.goods, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (value == 0.0f) ? Colors.HOVER_NEUTRAL : ((value > 0.0f) ? Colors.HOVER_POSITIVE : Colors.HOVER_NEGATIVE)));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        value = Game.getCiv(civID).getWarWeariness() * GameValues.warWeariness.WW_CORE_COST_PER_POINT * 100.0f;
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("CoreConstruction") + ": ", ((value > 0.0f) ? "+" : "") + CFG.getPrecision2(value, 10) + "%", Images.core, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (value == 0.0f) ? Colors.HOVER_NEUTRAL : ((value < 0.0f) ? Colors.HOVER_POSITIVE : Colors.HOVER_NEGATIVE)));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        value = Game.getCiv(civID).getWarWeariness() * GameValues.warWeariness.WW_CONVERSION_COST_PER_POINT * 100.0f;
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ReligionConversionCost") + ": ", ((value > 0.0f) ? "+" : "") + CFG.getPrecision2(value, 10) + "%", Images.religion, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (value == 0.0f) ? Colors.HOVER_NEUTRAL : ((value < 0.0f) ? Colors.HOVER_POSITIVE : Colors.HOVER_NEGATIVE)));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        value = Game.getCiv(civID).getWarWeariness() * GameValues.warWeariness.WW_RECRUITMENT_TIME_PER_POINT * 100.0f;
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("RecruitmentTime") + ": ", ((value > 0.0f) ? "+" : "") + CFG.getPrecision2(value, 10) + "%", Game_Calendar.IMG_MANPOWER_TIME, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (value == 0.0f) ? Colors.HOVER_NEUTRAL : ((value < 0.0f) ? Colors.HOVER_POSITIVE : Colors.HOVER_NEGATIVE)));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        value = Game.getCiv(civID).getWarWeariness() * GameValues.warWeariness.WW_ARMY_MOVEMENT_SPEED_PER_POINT * 100.0f;
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ArmyMovementSpeed") + ": ", ((value > 0.0f) ? "+" : "") + CFG.getPrecision2(value, 10) + "%", Images.movementSpeed, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (value == 0.0f) ? Colors.HOVER_NEUTRAL : ((value < 0.0f) ? Colors.HOVER_POSITIVE : Colors.HOVER_NEGATIVE)));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        value = Game.getCiv(civID).getWarWeariness() * GameValues.warWeariness.WW_SIEGE_EFFECTIVENESS_PER_POINT * 100.0f;
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("SiegeEffectiveness") + ": ", ((value > 0.0f) ? "+" : "") + CFG.getPrecision2(value, 10) + "%", Images.siege, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (value == 0.0f) ? Colors.HOVER_NEUTRAL : ((value > 0.0f) ? Colors.HOVER_POSITIVE : Colors.HOVER_NEGATIVE)));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        value = Game.getCiv(civID).getWarWeariness() * GameValues.warWeariness.WW_UNREST_PER_POINT;
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Unrest") + ": ", ((value > 0.0f) ? "+" : "") + Game.lang.get("XPerMonth", CFG.getPrecision2(value, 100)), Images.revolutionRisk, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (value == 0.0f) ? Colors.HOVER_NEUTRAL : ((value < 0.0f) ? Colors.HOVER_POSITIVE : Colors.HOVER_NEGATIVE)));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        value = Game.getCiv(civID).getWarWeariness() * GameValues.warWeariness.WW_UNREST_NON_CORE_PER_POINT;
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Unrest") + ": " + Game.lang.get("NonCoreProvince") + ": ", ((value > 0.0f) ? "+" : "") + Game.lang.get("XPerMonth", CFG.getPrecision2(value, 100)), Images.revolutionRisk, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (value == 0.0f) ? Colors.HOVER_NEUTRAL : ((value < 0.0f) ? Colors.HOVER_POSITIVE : Colors.HOVER_NEGATIVE)));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        value = Game.getCiv(civID).getWarWeariness() * GameValues.warWeariness.WW_UNREST_DIFFERENT_RELIGION_PER_POINT;
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Unrest") + ": " + Game.lang.get("DifferentReligion") + ": ", ((value > 0.0f) ? "+" : "") + Game.lang.get("XPerMonth", CFG.getPrecision2(value, 100)), Images.revolutionRisk, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (value == 0.0f) ? Colors.HOVER_NEUTRAL : ((value < 0.0f) ? Colors.HOVER_POSITIVE : Colors.HOVER_NEGATIVE)));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData, false));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("WarWearinessH1"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
        nElements.add(new MenuElement_HoverElement(nData, false));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData, false));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("WarWearinessH2"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
        nElements.add(new MenuElement_HoverElement(nData, false));
        nData.clear();
        return new MenuElement_Hover(nElements);
    }
    
    public static MenuElement_Hover getHover_CivStability(final int civID) {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("CivilizationStability") + ": ", CFG.getPrecision2((1.0f - Game.getCiv(civID).civStability_LostFrom100) * 100.0f, 100) + "%", Images.civStability, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        float value = Game.getCiv(civID).civStability_LostFrom100 * GameValues.civStability.CS_UNREST_PER_POINT;
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Unrest") + ": ", ((value > 0.0f) ? "+" : "") + Game.lang.get("XPerMonth", CFG.getPrecision2(value, 100)), Images.revolutionRisk, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (value == 0.0f) ? Colors.HOVER_NEUTRAL : ((value < 0.0f) ? Colors.HOVER_POSITIVE : Colors.HOVER_NEGATIVE)));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        value = Game.getCiv(civID).civStability_LostFrom100 * GameValues.civStability.CS_UNREST_NON_CORE_PER_POINT;
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Unrest") + ": " + Game.lang.get("NonCoreProvince") + ": ", ((value > 0.0f) ? "+" : "") + Game.lang.get("XPerMonth", CFG.getPrecision2(value, 100)), Images.revolutionRisk, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (value == 0.0f) ? Colors.HOVER_NEUTRAL : ((value < 0.0f) ? Colors.HOVER_POSITIVE : Colors.HOVER_NEGATIVE)));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        value = Game.getCiv(civID).civStability_LostFrom100 * GameValues.civStability.CS_UNREST_DIFFERENT_RELIGION_PER_POINT;
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Unrest") + ": " + Game.lang.get("DifferentReligion") + ": ", ((value > 0.0f) ? "+" : "") + Game.lang.get("XPerMonth", CFG.getPrecision2(value, 100)), Images.revolutionRisk, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (value == 0.0f) ? Colors.HOVER_NEUTRAL : ((value < 0.0f) ? Colors.HOVER_POSITIVE : Colors.HOVER_NEGATIVE)));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        value = Game.getCiv(civID).civStability_LostFrom100 * GameValues.civStability.CS_ARMY_MORALE_RECOVERY_PER_POINT * 100.0f;
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ArmyMoraleRecovery") + ": ", ((value > 0.0f) ? "+" : "") + CFG.getPrecision2(value, 10) + "%", Images.morale, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (value == 0.0f) ? Colors.HOVER_NEUTRAL : ((value > 0.0f) ? Colors.HOVER_POSITIVE : Colors.HOVER_NEGATIVE)));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData, false));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Provinces") + ": ", "" + (Game.getCiv(civID).getNumOfProvinces() - Game.getCiv(civID).civStability_NumOfProvinces()) + " / " + Game.getCiv(civID).getNumOfProvinces(), Images.provinces, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_RIGHT));
        nElements.add(new MenuElement_HoverElement(nData, false));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData, false));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("CivilizationStabilityReflectsThePercentageOfYourProvincesWithLegitimateClaimsComparedToThoseWithoutAndTakesIntoAccountYourReligiousUnity"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
        nElements.add(new MenuElement_HoverElement(nData, false));
        nData.clear();
        return new MenuElement_Hover(nElements);
    }
    
    public static void updateMaxIconH() {
        if (InGame_Civ.iMaxH_DiplomacyIcon == 0) {
            InGame_Civ.iMaxH_DiplomacyIcon = Math.max(InGame_Civ.iMaxH_DiplomacyIcon, ImageManager.getImage(Images.alliance).getHeight());
            InGame_Civ.iMaxH_DiplomacyIcon = Math.max(InGame_Civ.iMaxH_DiplomacyIcon, ImageManager.getImage(Images.rivals).getHeight());
            InGame_Civ.iMaxH_DiplomacyIcon = Math.max(InGame_Civ.iMaxH_DiplomacyIcon, ImageManager.getImage(Images.war).getHeight());
            InGame_Civ.iMaxH_DiplomacyIcon = Math.max(InGame_Civ.iMaxH_DiplomacyIcon, ImageManager.getImage(Images.relationsUp).getHeight());
            InGame_Civ.iMaxH_DiplomacyIcon = Math.max(InGame_Civ.iMaxH_DiplomacyIcon, ImageManager.getImage(Images.relationsDown).getHeight());
            InGame_Civ.iMaxH_DiplomacyIcon = Math.max(InGame_Civ.iMaxH_DiplomacyIcon, ImageManager.getImage(Images.militaryAccess).getHeight());
            InGame_Civ.iMaxH_DiplomacyIcon = Math.max(InGame_Civ.iMaxH_DiplomacyIcon, ImageManager.getImage(Images.defensivePact).getHeight());
            InGame_Civ.iMaxH_DiplomacyIcon = Math.max(InGame_Civ.iMaxH_DiplomacyIcon, ImageManager.getImage(Images.alliance).getHeight());
            InGame_Civ.iMaxH_DiplomacyIcon = Math.max(InGame_Civ.iMaxH_DiplomacyIcon, ImageManager.getImage(Images.nonAggression).getHeight());
            InGame_Civ.iMaxH_DiplomacyIcon = Math.max(InGame_Civ.iMaxH_DiplomacyIcon, ImageManager.getImage(Images.vassal).getHeight());
            InGame_Civ.iMaxH_DiplomacyIcon = Math.max(InGame_Civ.iMaxH_DiplomacyIcon, ImageManager.getImage(Images.militaryAccess).getHeight());
            InGame_Civ.iMaxH_DiplomacyIcon = Math.max(InGame_Civ.iMaxH_DiplomacyIcon, ImageManager.getImage(Images.militaryAccess2).getHeight());
            InGame_Civ.iMaxH_DiplomacyIcon = Math.max(InGame_Civ.iMaxH_DiplomacyIcon, ImageManager.getImage(Images.gift).getHeight());
            InGame_Civ.iMaxH_DiplomacyIcon = Math.max(InGame_Civ.iMaxH_DiplomacyIcon, ImageManager.getImage(Images.guaranteeIndependence).getHeight());
            InGame_Civ.iMaxH_DiplomacyIcon = Math.max(InGame_Civ.iMaxH_DiplomacyIcon, ImageManager.getImage(Images.spy).getHeight());
        }
    }
    
    static {
        InGame_Civ.TRADE = false;
        InGame_Civ.lTime = 0L;
        InGame_Civ.lTime2 = 0L;
        InGame_Civ.iActiveCivID = 0;
        InGame_Civ.iRebuildToCivID = -1;
        InGame_Civ.diplomacyMode = true;
        InGame_Civ.iSortID = 0;
        InGame_Civ.enabledByScaleOut = false;
        InGame_Civ.renameCiv = "";
        InGame_Civ.iMaxH_DiplomacyIcon = 0;
        InGame_Civ.relationsMode = false;
    }
}
