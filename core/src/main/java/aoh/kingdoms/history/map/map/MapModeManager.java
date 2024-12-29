// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.map;

import aoc.kingdoms.lukasz.map.province.ProvinceTouchExtraAction;
import aoc.kingdoms.lukasz.menusInGame.InGame;
import aoc.kingdoms.lukasz.map.war.War;
import aoc.kingdoms.lukasz.map.war.WarCivilization;
import aoc.kingdoms.lukasz.map.war.WarManager;
import aoc.kingdoms.lukasz.menusInGame.AllianceSpecial.InGame_AllianceSpecial;
import aoc.kingdoms.lukasz.map.BuildingsManager;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court_Buildings2;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_ReligionTitle;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoc.kingdoms.lukasz.map.province.ProvinceInvest;
import aoc.kingdoms.lukasz.map.WondersManager;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Resource;
import aoc.kingdoms.lukasz.menusInGame.Right.InGame_RightGoods;
import aoc.kingdoms.lukasz.map.terrain.Terrain;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Clear;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoc.kingdoms.lukasz.map.province.Province;
import aoc.kingdoms.lukasz.menusInGame.Right.InGame_RightReligion;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Religion;
import aoc.kingdoms.lukasz.menusInGame.Right.InGame_RightGovernment;
import aoc.kingdoms.lukasz.map.province.ProvinceDrawDetails;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Disease;
import aoc.kingdoms.lukasz.map.plague.PlagueManager;
import aoc.kingdoms.lukasz.map.plague.Plague;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagTitle;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Terrain;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonusResource;
import aoc.kingdoms.lukasz.map.ResourcesManager;
import aoc.kingdoms.lukasz.menusInGame.InGame_War;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_FormCiv;
import aoc.kingdoms.lukasz.map.FormableCivManager;
import java.util.Iterator;
import java.util.Map;
import aoc.kingdoms.lukasz.map.diplomacy.Diplomacy;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_DeclareWar;
import aoc.kingdoms.lukasz.menusInGame.Civ.InGame_Civ;
import aoc.kingdoms.lukasz.map.allianceHRE.Alliance;
import aoc.kingdoms.lukasz.jakowski.Game_Ages;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.map.army.ArmyManager;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.map.army.ArmyRecruit;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Flag;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import aoc.kingdoms.lukasz.map.province.ProvinceHover;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoc.kingdoms.lukasz.map.province.ProvinceDraw;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.menusInGame.Right.InGame_Right;
import java.util.List;
import com.badlogic.gdx.graphics.Color;

public class MapModeManager
{
    public static Color PROVINCE_GREEN;
    public static Color PROVINCE_RED;
    public static Color PROVINCE_GRAY;
    public static Color PROVINCE_BLUE;
    public static Color PROVINCE_BLUE2;
    public static Color PROVINCE_BLUE2_ALLY;
    public static Color PROVINCE_RED2;
    public static Color PROVINCE_RED2_ALLY;
    public List<MapMode> lMapModes;
    public int iActiveMapModeID;
    public int MODE_DEFAULT;
    public int MODE_DEFAULT_TERRAIN;
    public int MODE_POPULATION;
    public int MODE_ECONOMY;
    public int MODE_PROVINCE_INCOME;
    public int MODE_PROVINCE_INCOME_HOVER;
    public int MODE_PROVINCE_EXPENSES_HOVER;
    public int MODE_CIV_POPULATION_HOVER;
    public int MODE_CIV_ECONOMY_HOVER;
    public int MODE_PROVINCE_MANPOWER_HOVER_PLAYER;
    public int MODE_PROVINCE_GROWTH_RATE_HOVER;
    public int MODE_PROVINCE_MANPOWER_HOVER;
    public int MODE_PROVINCE_ECONOMY_HOVER;
    public int MODE_PROVINCE_TAX_EFFICIENCY_HOVER;
    public int MODE_PROVINCE_POPULATION_HOVER;
    public int MODE_PROVINCE_RELIGION_HOVER;
    public int MODE_PROVINCE_FORTS_HOVER;
    public int MODE_PROVINCE_PROVINCE_INCOME_HOVER;
    public int MODE_PROVINCE_INFRASTRUCTURE_HOVER;
    public int MODE_PROVINCE_LOOT_HOVER;
    public int MODE_PROVINCE_PROVINCE_VALUE_HOVER;
    public int MODE_PROVINCE_DEVASTATION_HOVER;
    public int MODE_PROVINCE_UNREST_HOVER;
    public int MODE_PROVINCE_TAX;
    public int MODE_TERRAIN;
    public int MODE_GOODS;
    public int MODE_INFRASTRUCTURE;
    public int MODE_WONDERS;
    public int MODE_RELIGION;
    public int MODE_GOVERNMENT;
    public int MODE_DEVASTATION;
    public int MODE_LOOT;
    public int MODE_DEFENSE_LEVEL;
    public int MODE_UNREST;
    public int MODE_DISEASES;
    public int MODE_WARS;
    public int MODE_ALLIANCES;
    public int MODE_DEFENSIVE_PACTS;
    public int MODE_NON_AGGRESSION_PACTS;
    public int MODE_RECRUIT_ARMY;
    public int MODE_NEW_ARMY_CHOOSE_PROVINCE;
    public int MODE_NUKE_CHOOSE_PROVINCE;
    public int MODE_MERCENARIES_CHOOSE_PROVINCE;
    public int MODE_INVEST_IN_ECONOMY;
    public int MODE_INCREASE_TAX_EFFICIENCY;
    public int MODE_INCREASE_MANPOWER;
    public int MODE_MOVE_CAPITAL;
    public int MODE_COLONIZE_CHOOSE_PROVINCE;
    public int MODE_DEVELOP_INFRASTRUCTURE;
    public int MODE_INCREASE_GROWTH_RATE;
    public int MODE_CIV_STABILITY_HOVER;
    public int MODE_BUILDING;
    public int MODE_CONVERT_RELIGION;
    public int MODE_CORE;
    public int MODE_DIPLOMACY;
    public int MODE_DIPLOMACY_IMPROVE_RELATIONS;
    public int MODE_DIPLOMACY_DAMAGE_RELATIONS;
    public int MODE_DECLARE_WAR;
    public int MODE_FORM_CIV;
    public int MODE_SPECIAL_ALLIANCE_VIEW;
    public int MODE_WAR_VIEW;
    public int MODE_PEACE_VIEW;
    public int MODE_RELEASE_VASSAL;
    public static long lTime;
    protected static final int ANIMATION_TIME = 500;
    public static float fAlphaAnimation;
    public int POPULATION_MAX;
    public float ECONOMY_MAX;
    public float TAX_MAX;
    public float PROVINCE_MANPOWER_MAX;
    public float PROVINCE_INCOME_MAX;
    public static int diplomacyColorsPosX;
    public static int diplomacyColorsPosY;
    public static float diplomacyScale;
    public static int diplomacyActiveCivID;
    
    public static final void updateRightMenu() {
        if (InGame_Right.stateVisible) {
            Game.menuManager.rebuildInGame_Right();
            InGame_Right.lTime = 0L;
        }
        else {
            Game.menuManager.setVisibleInGame_Right(false);
        }
    }
    
    public static final void updateAlpha() {
        if (MapModeManager.lTime + 500L > CFG.currentTimeMillis) {
            MapModeManager.fAlphaAnimation = Math.min(1.0f, 0.55f + 0.45f * (CFG.currentTimeMillis - MapModeManager.lTime) / 500.0f);
        }
        else {
            MapModeManager.fAlphaAnimation = 1.0f;
        }
    }
    
    public MapModeManager() {
        this.iActiveMapModeID = 0;
        this.MODE_DEFAULT = 0;
        this.MODE_DEFAULT_TERRAIN = -1;
        this.MODE_POPULATION = -1;
        this.MODE_ECONOMY = -1;
        this.MODE_PROVINCE_INCOME = -1;
        this.MODE_PROVINCE_INCOME_HOVER = -1;
        this.MODE_PROVINCE_EXPENSES_HOVER = -1;
        this.MODE_CIV_POPULATION_HOVER = -1;
        this.MODE_CIV_ECONOMY_HOVER = -1;
        this.MODE_PROVINCE_MANPOWER_HOVER_PLAYER = -1;
        this.MODE_PROVINCE_GROWTH_RATE_HOVER = -1;
        this.MODE_PROVINCE_MANPOWER_HOVER = -1;
        this.MODE_PROVINCE_ECONOMY_HOVER = -1;
        this.MODE_PROVINCE_TAX_EFFICIENCY_HOVER = -1;
        this.MODE_PROVINCE_POPULATION_HOVER = -1;
        this.MODE_PROVINCE_RELIGION_HOVER = -1;
        this.MODE_PROVINCE_FORTS_HOVER = -1;
        this.MODE_PROVINCE_PROVINCE_INCOME_HOVER = -1;
        this.MODE_PROVINCE_INFRASTRUCTURE_HOVER = -1;
        this.MODE_PROVINCE_LOOT_HOVER = -1;
        this.MODE_PROVINCE_PROVINCE_VALUE_HOVER = -1;
        this.MODE_PROVINCE_DEVASTATION_HOVER = -1;
        this.MODE_PROVINCE_UNREST_HOVER = -1;
        this.MODE_PROVINCE_TAX = -1;
        this.MODE_TERRAIN = -1;
        this.MODE_GOODS = -1;
        this.MODE_INFRASTRUCTURE = -1;
        this.MODE_WONDERS = -1;
        this.MODE_RELIGION = -1;
        this.MODE_GOVERNMENT = -1;
        this.MODE_DEVASTATION = -1;
        this.MODE_LOOT = -1;
        this.MODE_DEFENSE_LEVEL = -1;
        this.MODE_UNREST = -1;
        this.MODE_DISEASES = -1;
        this.MODE_WARS = -1;
        this.MODE_ALLIANCES = -1;
        this.MODE_DEFENSIVE_PACTS = -1;
        this.MODE_NON_AGGRESSION_PACTS = -1;
        this.MODE_RECRUIT_ARMY = -1;
        this.MODE_NEW_ARMY_CHOOSE_PROVINCE = -1;
        this.MODE_NUKE_CHOOSE_PROVINCE = -1;
        this.MODE_MERCENARIES_CHOOSE_PROVINCE = -1;
        this.MODE_INVEST_IN_ECONOMY = -1;
        this.MODE_INCREASE_TAX_EFFICIENCY = -1;
        this.MODE_INCREASE_MANPOWER = -1;
        this.MODE_MOVE_CAPITAL = -1;
        this.MODE_COLONIZE_CHOOSE_PROVINCE = -1;
        this.MODE_DEVELOP_INFRASTRUCTURE = -1;
        this.MODE_INCREASE_GROWTH_RATE = -1;
        this.MODE_CIV_STABILITY_HOVER = -1;
        this.MODE_BUILDING = -1;
        this.MODE_CONVERT_RELIGION = -1;
        this.MODE_CORE = -1;
        this.MODE_DIPLOMACY = -1;
        this.MODE_DIPLOMACY_IMPROVE_RELATIONS = -1;
        this.MODE_DIPLOMACY_DAMAGE_RELATIONS = -1;
        this.MODE_DECLARE_WAR = -1;
        this.MODE_FORM_CIV = -1;
        this.MODE_SPECIAL_ALLIANCE_VIEW = -1;
        this.MODE_WAR_VIEW = -1;
        this.MODE_PEACE_VIEW = -1;
        this.MODE_RELEASE_VASSAL = -1;
        this.POPULATION_MAX = 1;
        this.ECONOMY_MAX = 1.0f;
        this.TAX_MAX = 1.0f;
        this.PROVINCE_MANPOWER_MAX = 0.0f;
        this.PROVINCE_INCOME_MAX = 0.0f;
        this.lMapModes = new ArrayList<MapMode>();
        this.MODE_DEFAULT = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                if (Game.settingsManager.FBO_PROVINCES) {
                    ProvinceDraw.drawProvinces_Standard_FBO(oSB);
                }
                else {
                    ProvinceDraw.drawProvinces_Standard_InGame(oSB);
                }
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        if (Game.getProvince(Game.iHoveredProvinceID).getSeaProvince()) {
                            Game.provinceHover_Informations = null;
                            return;
                        }
                        if (Game.getProvince(Game.iHoveredProvinceID).getCivID() == 0) {
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Line());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("UncolonizedLand"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.provinces, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        else {
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            if (Game.activeArmySize > 0 && Game.getProvince(Game.iHoveredProvinceID).getFortLevel() > 0) {
                                nData.add(new MenuElement_HoverElement_Type_Line());
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DefenseLevel") + ": ", "" + Game.getProvince(Game.iHoveredProvinceID).getFortLevel(), Images.fort, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                        }
                        if (CFG.debugMode) {
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("ID: ", "" + Game.iHoveredProvinceID, Images.provinces, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("Civ ID: ", "" + Game.getProvince(Game.iHoveredProvinceID).getCivID(), Images.council, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        if (Game.getProvince(Game.iHoveredProvinceID).getCivID() == Game.player.iCivID) {
                            boolean addOnce = true;
                            int days = 0;
                            for (int i = 0; i < Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).getArmyRecruitSize(); ++i) {
                                for (int j = 0; j < Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).lArmyRecruit.get(i).size(); ++j) {
                                    if (Game.iHoveredProvinceID == Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).lArmyRecruit.get(i).get(j).provinceID) {
                                        if (addOnce) {
                                            addOnce = false;
                                            nData.add(new MenuElement_HoverElement_Type_Line());
                                            nElements.add(new MenuElement_HoverElement(nData));
                                            nData.clear();
                                            nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), 0, CFG.PADDING));
                                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("TheArmyWillBeDeployedToThisLocation") + ": ", CFG.FONT_REGULAR_SMALL));
                                            nData.add(new MenuElement_HoverElement_Type_Text(Game_Calendar.getDate_ByTurnID(Game_Calendar.TURN_ID + Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).lArmyRecruit.get(i).get(j).timeLeft), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                                            nElements.add(new MenuElement_HoverElement(nData));
                                            nData.clear();
                                        }
                                        nData.add(new MenuElement_HoverElement_Type_Text(ArmyManager.lArmy.get(Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).lArmyRecruit.get(i).get(j).unitID).get(Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).lArmyRecruit.get(i).get(j).armyID).Name + ": ", CFG.FONT_REGULAR_SMALL));
                                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DaysX", days + Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).lArmyRecruit.get(i).get(j).timeLeft), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                                        nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, 0));
                                        nElements.add(new MenuElement_HoverElement(nData));
                                        nData.clear();
                                        days += Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).lArmyRecruit.get(i).get(j).timeLeft;
                                    }
                                }
                            }
                        }
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = null;
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                ProvinceDraw.updateDrawProvinces_Standard();
            }
        });
        this.MODE_DIPLOMACY = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                MapModeManager.updateDiplomacyProvinceColor(true);
                ProvinceDraw.drawProvinces_Diplomacy(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        if (Game.getProvince(Game.iHoveredProvinceID).getSeaProvince()) {
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("SeaProvince"), Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        else if (Game.getProvince(Game.iHoveredProvinceID).getCivID() == 0) {
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Line());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("UncolonizedLand"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.provinces, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        else {
                            nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(Game.getProvince(Game.iHoveredProvinceID).getCivID(), Game.getProvince(Game.iHoveredProvinceID).getProvinceName()));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            if (Game.getProvince(Game.iHoveredProvinceID).getCivID() == MapModeManager.diplomacyActiveCivID) {
                                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Provinces") + ": ", CFG.getNumberWithSpaces("" + Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).getNumOfProvinces()), Images.provinces, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            else if (DiplomacyManager.isAtWar(MapModeManager.diplomacyActiveCivID, Game.getProvince(Game.iHoveredProvinceID).getCivID())) {
                                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AtWar"), "", Images.war, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            else {
                                final int tempRelation = (int)Game.getCiv(MapModeManager.diplomacyActiveCivID).diplomacy.getRelation(Game.getProvince(Game.iHoveredProvinceID).getCivID());
                                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Opinion") + ": ", ((tempRelation > 0) ? "+" : "") + CFG.getPrecision2((float)tempRelation, 10), Images.relations, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, DiplomacyManager.getOpinion_Color(tempRelation)));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                if (Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).getPuppetOfCivID() == MapModeManager.diplomacyActiveCivID) {
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get(Game_Ages.getVassal()), "", Images.vassal, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                }
                                else if (Game.getCiv(MapModeManager.diplomacyActiveCivID).getPuppetOfCivID() == Game.getProvince(Game.iHoveredProvinceID).getCivID()) {
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get(Game_Ages.getLord()), "", Images.vassal, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                }
                                else if (Game.getCiv(MapModeManager.diplomacyActiveCivID).diplomacy.alliance.containsKey(Game.getProvince(Game.iHoveredProvinceID).getCivID())) {
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Alliance"), "", Images.alliance, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                }
                                else if (Game.getCiv(MapModeManager.diplomacyActiveCivID).diplomacy.defensivePact.containsKey(Game.getProvince(Game.iHoveredProvinceID).getCivID())) {
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DefensivePact"), "", Images.defensivePact, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                }
                                else if (Game.getCiv(MapModeManager.diplomacyActiveCivID).diplomacy.truce.containsKey(Game.getProvince(Game.iHoveredProvinceID).getCivID())) {
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Truce"), "", Images.truce, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                }
                                else if (Game.getCiv(MapModeManager.diplomacyActiveCivID).diplomacy.guarantee.containsKey(Game.getProvince(Game.iHoveredProvinceID).getCivID())) {
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GuaranteeIndependence"), "", Images.guaranteeIndependence, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                }
                                else if (Game.getCiv(MapModeManager.diplomacyActiveCivID).diplomacy.guaranteeByCivID.containsKey(Game.getProvince(Game.iHoveredProvinceID).getCivID())) {
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GuaranteeTheirIndependence"), "", Images.guaranteeIndependence, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                }
                                else if (Game.getCiv(MapModeManager.diplomacyActiveCivID).diplomacy.nonAggressionPact.containsKey(Game.getProvince(Game.iHoveredProvinceID).getCivID())) {
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("NonAggressionPact"), "", Images.nonAggression, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                }
                                else if (Game.getCiv(MapModeManager.diplomacyActiveCivID).diplomacy.militaryAccess.containsKey(Game.getProvince(Game.iHoveredProvinceID).getCivID())) {
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MilitaryAccess"), "", Images.militaryAccess, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                }
                                else if (Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).diplomacy.militaryAccess.containsKey(MapModeManager.diplomacyActiveCivID)) {
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GivesMilitaryAccess"), "", Images.militaryAccess2, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                }
                            }
                            if (Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).inAllianceSize > 0) {
                                nData.add(new MenuElement_HoverElement_Type_Line());
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                for (int i = 0; i < Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).inAllianceSize; ++i) {
                                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get(Game.alliancesSpecial.get(Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).inAlliance.get(i)).Name_Alliance), Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                }
                            }
                        }
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = null;
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                MapModeManager.diplomacyColorsPosX = -777777;
                MapModeManager.diplomacyColorsPosY = -777777;
                MapModeManager.diplomacyScale = -777.0f;
                MapModeManager.diplomacyActiveCivID = -777;
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
            }
        });
        this.MODE_DIPLOMACY_IMPROVE_RELATIONS = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                ProvinceDraw.drawProvinces_DiplomacyRelations(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        if (Game.getProvince(Game.iHoveredProvinceID).getCivID() > 0 && Game.getProvince(Game.iHoveredProvinceID).getCivID() != Game.player.iCivID) {
                            Game.provinceHover_Informations = InGame_Civ.getHoverBetweenCivilizations(Game.getProvince(Game.iHoveredProvinceID).getCivID(), Game.player.iCivID, true, false);
                            return;
                        }
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = null;
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                Game.mapCities.updateCapitalCityName();
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                Game.mapCities.updateCapitalCityName();
            }
        });
        this.MODE_DIPLOMACY_DAMAGE_RELATIONS = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                ProvinceDraw.drawProvinces_DiplomacyRelations(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        if (Game.getProvince(Game.iHoveredProvinceID).getCivID() > 0 && Game.getProvince(Game.iHoveredProvinceID).getCivID() != Game.player.iCivID) {
                            Game.provinceHover_Informations = InGame_Civ.getHoverBetweenCivilizations(Game.getProvince(Game.iHoveredProvinceID).getCivID(), Game.player.iCivID, true, false);
                            return;
                        }
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = null;
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                Game.mapCities.updateCapitalCityName();
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                Game.mapCities.updateCapitalCityName();
            }
        });
        this.MODE_DECLARE_WAR = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                ProvinceDraw.drawProvinces_DeclareWar(oSB);
                if (!Game.menuManager.getVisibleDeclareWar()) {
                    MapModeManager.this.setActiveViewID(MapModeManager.this.MODE_DEFAULT);
                }
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                Game.provinceHover_Informations = MapModeManager.getProvinceHover_Default();
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                ProvinceDraw.updateDrawProvinces_Standard();
                ProvinceDraw.buildBiggestCitiesLines_Province(Game.getCiv(Game.player.iCivID).getCapitalProvinceID(), Game.getCiv(InGame_DeclareWar.iCivID).getCapitalProvinceID());
                try {
                    final List<Integer> alliesLeft = DiplomacyManager.declareWar_AlliesAttacker(Game.player.iCivID, InGame_DeclareWar.iCivID);
                    final List<Integer> alliesRight = DiplomacyManager.declareWar_AlliesDefender(InGame_DeclareWar.iCivID, Game.player.iCivID);
                    for (int i = 0; i < Game.getCivsSize(); ++i) {
                        Game.getCiv(i).warView_ParticipatesInWar = false;
                        Game.getCiv(i).warView_IsAggressor = false;
                    }
                    for (int i = alliesLeft.size() - 1; i >= 0; --i) {
                        Game.getCiv(alliesLeft.get(i)).warView_IsAggressor = true;
                    }
                    for (int i = alliesRight.size() - 1; i >= 0; --i) {
                        Game.getCiv(alliesRight.get(i)).warView_ParticipatesInWar = true;
                    }
                }
                catch (final Exception ex) {}
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                if (!Game.menuManager.getVisibleInGame_Civ()) {
                    ProvinceDraw.clearBiggestCities();
                }
            }
        });
        this.MODE_WARS = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                ProvinceDraw.drawProvinces_CurrentWars(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                Game.provinceHover_Informations = MapModeManager.getProvinceHover_Default();
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                ProvinceDraw.updateDrawProvinces_Standard();
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
            }
        });
        this.MODE_ALLIANCES = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                ProvinceDraw.drawProvinces_Alliances(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        if (Game.getProvince(Game.iHoveredProvinceID).getCivID() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).getCivName(), Colors.HOVER_GOLD));
                        }
                        else {
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        if (Game.getProvince(Game.iHoveredProvinceID).getCivID() > 0 && !Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).diplomacy.alliance.isEmpty()) {
                            nData.add(new MenuElement_HoverElement_Type_Line());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            final Iterator<Map.Entry<Integer, Diplomacy.DiplomacyData>> it = Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).diplomacy.alliance.entrySet().iterator();
                            while (it.hasNext()) {
                                final Diplomacy.DiplomacyData tData = (Diplomacy.DiplomacyData)it.next().getValue();
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(tData.iCivID).getCivName(), Colors.HOVER_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_Flag(tData.iCivID, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                        }
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = null;
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                ProvinceDraw.updateDrawProvinces_Standard();
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
            }
        });
        this.MODE_DEFENSIVE_PACTS = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                ProvinceDraw.drawProvinces_DefensivePacts(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        if (Game.getProvince(Game.iHoveredProvinceID).getCivID() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).getCivName(), Colors.HOVER_GOLD));
                        }
                        else {
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        if (Game.getProvince(Game.iHoveredProvinceID).getCivID() > 0 && !Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).diplomacy.defensivePact.isEmpty()) {
                            nData.add(new MenuElement_HoverElement_Type_Line());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            final Iterator<Map.Entry<Integer, Diplomacy.DiplomacyData>> it = Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).diplomacy.defensivePact.entrySet().iterator();
                            while (it.hasNext()) {
                                final Diplomacy.DiplomacyData tData = (Diplomacy.DiplomacyData)it.next().getValue();
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(tData.iCivID).getCivName(), Colors.HOVER_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_Flag(tData.iCivID, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                        }
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = null;
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                ProvinceDraw.updateDrawProvinces_Standard();
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
            }
        });
        this.MODE_NON_AGGRESSION_PACTS = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                ProvinceDraw.drawProvinces_NonAggressionPacts(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        if (Game.getProvince(Game.iHoveredProvinceID).getCivID() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).getCivName(), Colors.HOVER_GOLD));
                        }
                        else {
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        if (Game.getProvince(Game.iHoveredProvinceID).getCivID() > 0 && !Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).diplomacy.nonAggressionPact.isEmpty()) {
                            nData.add(new MenuElement_HoverElement_Type_Line());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            final Iterator<Map.Entry<Integer, Diplomacy.DiplomacyData>> it = Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).diplomacy.nonAggressionPact.entrySet().iterator();
                            while (it.hasNext()) {
                                final Diplomacy.DiplomacyData tData = (Diplomacy.DiplomacyData)it.next().getValue();
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(tData.iCivID).getCivName(), Colors.HOVER_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_Flag(tData.iCivID, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                        }
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = null;
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                ProvinceDraw.updateDrawProvinces_Standard();
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
            }
        });
        this.MODE_NUKE_CHOOSE_PROVINCE = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                ProvinceDraw.drawProvinces_Nuke(oSB);
                if (!Game.menuManager.getVisibleInGame_Nukes()) {
                    MapModeManager.this.setActiveViewID(MapModeManager.this.MODE_DEFAULT);
                }
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                Game.provinceHover_Informations = MapModeManager.getProvinceHover_Default();
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                ProvinceDraw.updateDrawProvinces_Standard();
                Game.setCursorNuke();
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                Game.setCursorDefault();
            }
        });
        this.MODE_COLONIZE_CHOOSE_PROVINCE = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                ProvinceDraw.drawProvinces_Colonize(oSB);
                if (!Game.menuManager.getVisibleInGame_Court()) {
                    MapModeManager.this.setActiveViewID(MapModeManager.this.MODE_DEFAULT);
                }
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                Game.provinceHover_Informations = MapModeManager.getProvinceHover_Default();
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                ProvinceDraw.updateDrawProvinces_Standard();
                Game.setCursorPopulationGrowth();
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                Game.setCursorDefault();
            }
        });
        this.MODE_FORM_CIV = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                ProvinceDraw.drawProvinces_FormCiv(oSB);
                if (!Game.menuManager.getVisibleFormCiv()) {
                    MapModeManager.this.setActiveViewID(MapModeManager.this.MODE_DEFAULT);
                }
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                Game.provinceHover_Informations = MapModeManager.getProvinceHover_Default();
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                try {
                    for (int i = 0; i < Game.getProvincesSize(); ++i) {
                        Game.getProvince(i).drawInMapMode = Game.player.formableCivs.get(InGame_FormCiv.formCivID).Provinces.contains(i);
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
                ProvinceDraw.updateDrawProvinces_Standard();
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
            }
        });
        this.MODE_SPECIAL_ALLIANCE_VIEW = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                ProvinceDraw.drawProvinces_SpecialAlliance_View(oSB);
                if (!Game.menuManager.getVisible_SpecialAlliance()) {
                    MapModeManager.this.setActiveViewID(MapModeManager.this.MODE_DEFAULT);
                }
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                Game.provinceHover_Informations = MapModeManager.getProvinceHover_Default();
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                ProvinceDraw.updateDrawProvinces_Standard();
                MapModeManager.this.updateSpecialAllianceView();
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
            }
        });
        this.MODE_WAR_VIEW = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                ProvinceDraw.drawProvinces_WarView(oSB);
                if (!Game.menuManager.getVisibleInGame_War()) {
                    MapModeManager.this.setActiveViewID(MapModeManager.this.MODE_DEFAULT);
                }
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                Game.provinceHover_Informations = MapModeManager.getProvinceHover_Default();
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                ProvinceDraw.updateDrawProvinces_Standard();
                MapModeManager.this.updateWarView(InGame_War.key);
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                if (!Game.menuManager.getVisibleInGame_Civ()) {
                    ProvinceDraw.clearBiggestCities();
                }
                if (Game.menuManager.getVisibleInGame_War()) {
                    Game.menuManager.setVisibleInGame_War(false);
                }
            }
        });
        this.MODE_PEACE_VIEW = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                ProvinceDraw.drawProvinces_Peace(oSB);
                if (!Game.menuManager.getVisibleInGame_Peace()) {
                    MapModeManager.this.setActiveViewID(MapModeManager.this.MODE_DEFAULT);
                }
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        if (Game.getProvince(Game.iHoveredProvinceID).peaceTreatyIsToTake) {
                            if (Game.getProvince(Game.iHoveredProvinceID).getResourceID() >= 0) {
                                nData.add(new MenuElement_HoverElement_Type_Button_TextBonusResource(ResourcesManager.getResourceName(Game.getProvince(Game.iHoveredProvinceID).getResourceID()), "", Game.getProvince(Game.iHoveredProvinceID).getResourceID(), CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Cost") + ": ", CFG.getPrecision2(Game.getProvince(Game.iHoveredProvinceID).fProvinceValue, 10), Images.victoryPoints, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (Game.getProvince(Game.iHoveredProvinceID).fProvinceValue <= Game.player.peaceTreaty.fScore) ? Colors.HOVER_GOLD : Colors.HOVER_NEGATIVE));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", CFG.getNumberWithSpaces("" + Game.getProvince(Game.iHoveredProvinceID).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                            nElements.add(new MenuElement_HoverElement(nData, false));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Economy") + ": ", CFG.getPrecision2(Game.getProvince(Game.iHoveredProvinceID).getEconomyWithBonuses(), 10), Game_Calendar.IMG_ECONOMY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData, false));
                            nData.clear();
                        }
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = MapModeManager.getProvinceHover_Default();
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                ProvinceDraw.updateDrawProvinces_Standard();
                MapModeManager.this.updatePeaceView(InGame_War.key);
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                CFG.brushTool = false;
                Renderer.drawArmyInProvince = true;
                if (Game.menuManager.getVisibleInGame_Peace()) {
                    Game.menuManager.setVisibleInGame_Peace(false);
                }
            }
        });
        this.MODE_RELEASE_VASSAL = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                ProvinceDraw.drawProvinces_ReleaseVassal(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                Game.provinceHover_Informations = MapModeManager.getProvinceHover_Default();
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                ProvinceDraw.updateDrawProvinces_Standard();
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                CFG.brushTool = false;
                Renderer.drawArmyInProvince = true;
            }
        });
        this.MODE_DEFAULT_TERRAIN = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                ProvinceDraw.drawOccupiedProvinces(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                        nData.add(new MenuElement_HoverElement_Type_Terrain(Game.getProvince(Game.iHoveredProvinceID).getTerrainID(), Game.iHoveredProvinceID, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        if (Game.getProvince(Game.iHoveredProvinceID).getCivID() == Game.player.iCivID) {
                            boolean addOnce = true;
                            int days = 0;
                            for (int i = 0; i < Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).getArmyRecruitSize(); ++i) {
                                for (int j = 0; j < Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).lArmyRecruit.get(i).size(); ++j) {
                                    if (Game.iHoveredProvinceID == Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).lArmyRecruit.get(i).get(j).provinceID) {
                                        if (addOnce) {
                                            addOnce = false;
                                            nData.add(new MenuElement_HoverElement_Type_Line());
                                            nElements.add(new MenuElement_HoverElement(nData));
                                            nData.clear();
                                            nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), 0, CFG.PADDING));
                                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("TheArmyWillBeDeployedToThisLocation") + ": ", CFG.FONT_REGULAR_SMALL));
                                            nData.add(new MenuElement_HoverElement_Type_Text(Game_Calendar.getDate_ByTurnID(Game_Calendar.TURN_ID + Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).lArmyRecruit.get(i).get(j).timeLeft), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                                            nElements.add(new MenuElement_HoverElement(nData));
                                            nData.clear();
                                        }
                                        nData.add(new MenuElement_HoverElement_Type_Text(ArmyManager.lArmy.get(Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).lArmyRecruit.get(i).get(j).unitID).get(Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).lArmyRecruit.get(i).get(j).armyID).Name + ": ", CFG.FONT_REGULAR_SMALL));
                                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DaysX", days + Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).lArmyRecruit.get(i).get(j).timeLeft), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                                        nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, 0));
                                        nElements.add(new MenuElement_HoverElement(nData));
                                        nData.clear();
                                        days += Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).lArmyRecruit.get(i).get(j).timeLeft;
                                    }
                                }
                            }
                        }
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = null;
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                ProvinceDraw.updateDrawProvinces_Standard();
            }
        });
        this.MODE_POPULATION = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                final float fProvinceAlpha = ProvinceDraw.getProvinceAlpha(Colors.PROVINCE_ALPHA_POPULATION) * MapModeManager.fAlphaAnimation;
                ProvinceDraw.drawWastelandProvinces(oSB);
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    Game.getProvince(Game.getProvinceInViewID(i)).provinceColor.a = fProvinceAlpha;
                    oSB.setColor(Game.getProvince(Game.getProvinceInViewID(i)).provinceColor);
                    Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor.a = fProvinceAlpha;
                    oSB.setColor(Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor);
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                }
                ProvinceDraw.drawOccupiedProvinces(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID()));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Population") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_Text(CFG.getNumberWithSpaces("" + Game.getProvince(Game.iHoveredProvinceID).getPopulationTotal()), Colors.COLOR_POPULATION));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.population, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = null;
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                MapModeManager.this.updateMaxPopulation();
                for (int i = 0; i < Game.getProvincesSize(); ++i) {
                    if (!Game.getProvince(i).getSeaProvince()) {
                        Game.getProvince(i).provinceColor = Colors.getPopulationColor((int)(Game.getProvince(i).getPopulationTotal() / (float)MapModeManager.this.POPULATION_MAX * 100.0f), 1.0f);
                    }
                }
                Game.menuManager.rebuildInGame_RightPopulation();
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                MapModeManager.updateRightMenu();
            }
        });
        this.MODE_ECONOMY = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                final float fProvinceAlpha = ProvinceDraw.getProvinceAlpha(Colors.PROVINCE_ALPHA_POPULATION) * MapModeManager.fAlphaAnimation;
                ProvinceDraw.drawWastelandProvinces(oSB);
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    Game.getProvince(Game.getProvinceInViewID(i)).provinceColor.a = fProvinceAlpha;
                    oSB.setColor(Game.getProvince(Game.getProvinceInViewID(i)).provinceColor);
                    Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor.a = fProvinceAlpha;
                    oSB.setColor(Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor);
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                }
                ProvinceDraw.drawOccupiedProvinces(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID()));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Economy") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getProvince(Game.iHoveredProvinceID).getEconomyWithBonuses(), 100), Colors.COLOR_TEXT_ECONOMY));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = null;
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                MapModeManager.this.updateMaxEconomy();
                for (int i = 0; i < Game.getProvincesSize(); ++i) {
                    if (!Game.getProvince(i).getSeaProvince()) {
                        Game.getProvince(i).provinceColor = Colors.getEconomyColor((int)(Game.getProvince(i).getEconomyWithBonuses() / MapModeManager.this.ECONOMY_MAX * 100.0f), 1.0f);
                    }
                }
                Game.menuManager.rebuildInGame_RightEconomy();
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                MapModeManager.updateRightMenu();
            }
        });
        this.MODE_UNREST = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                final float fProvinceAlpha = ProvinceDraw.getProvinceAlpha(Colors.PROVINCE_ALPHA_POPULATION) * MapModeManager.fAlphaAnimation;
                ProvinceDraw.drawWastelandProvinces(oSB);
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    Game.getProvince(Game.getProvinceInViewID(i)).provinceColor.a = fProvinceAlpha;
                    oSB.setColor(Game.getProvince(Game.getProvinceInViewID(i)).provinceColor);
                    Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor.a = fProvinceAlpha;
                    oSB.setColor(Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor);
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                }
                ProvinceDraw.drawOccupiedProvinces(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID()));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Unrest") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(Game.getProvince(Game.iHoveredProvinceID).getRevulutionaryRisk(), 100) + "%", (Game.getProvince(Game.iHoveredProvinceID).getRevulutionaryRisk() == 0.0f) ? Colors.HOVER_NEUTRAL : Colors.HOVER_NEGATIVE));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.revolutionRisk, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = null;
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                for (int i = 0; i < Game.getProvincesSize(); ++i) {
                    if (!Game.getProvince(i).getSeaProvince()) {
                        if (Game.getProvince(i).getRevulutionaryRisk() < 0.5f) {
                            Game.getProvince(i).provinceColor = MapModeManager.PROVINCE_GRAY;
                        }
                        else {
                            Game.getProvince(i).provinceColor = Colors.getProvinceRedColor((int)(Game.getProvince(i).getRevulutionaryRisk() / 50.0f * 100.0f), 1.0f);
                        }
                    }
                }
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
            }
        });
        this.MODE_PROVINCE_TAX = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                final float fProvinceAlpha = ProvinceDraw.getProvinceAlpha(Colors.PROVINCE_ALPHA_POPULATION) * MapModeManager.fAlphaAnimation;
                ProvinceDraw.drawWastelandProvinces(oSB);
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    Game.getProvince(Game.getProvinceInViewID(i)).provinceColor.a = fProvinceAlpha;
                    oSB.setColor(Game.getProvince(Game.getProvinceInViewID(i)).provinceColor);
                    Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor.a = fProvinceAlpha;
                    oSB.setColor(Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor);
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                }
                ProvinceDraw.drawOccupiedProvinces(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID()));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("TaxEfficiency") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getProvince(Game.iHoveredProvinceID).getTaxEfficiencyWithBonuses(), 100) + "%", Colors.COLOR_TEXT_ECONOMY));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = null;
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                MapModeManager.this.updateMaxTax();
                for (int i = 0; i < Game.getProvincesSize(); ++i) {
                    if (!Game.getProvince(i).getSeaProvince()) {
                        Game.getProvince(i).provinceColor = Colors.getProvinceTaxColor((int)(Game.getProvince(i).getTaxEfficiencyWithBonuses() / MapModeManager.this.TAX_MAX * 100.0f), 1.0f);
                    }
                }
                Game.menuManager.rebuildInGame_RightTaxEfficiency();
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                MapModeManager.updateRightMenu();
            }
        });
        this.MODE_DEVASTATION = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                final float fProvinceAlpha = ProvinceDraw.getProvinceAlpha() * MapModeManager.fAlphaAnimation;
                ProvinceDraw.drawWastelandProvinces(oSB);
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    oSB.setColor(Colors.getProvinceDevastationColor((int)(Game.getProvince(Game.getProvinceInViewID(i)).getDevastation() / GameValues.siege.DEVASTATION_MAX * 100.0f), fProvinceAlpha));
                    Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    oSB.setColor(Colors.getProvinceDevastationColor((int)(Game.getProvince(Game.getExtraProvinceInViewID(i)).getDevastation() / GameValues.siege.DEVASTATION_MAX * 100.0f), fProvinceAlpha));
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                }
                ProvinceDraw.drawOccupiedProvinces(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID()));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Devastation") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getProvince(Game.iHoveredProvinceID).getDevastation() * 100.0f, 100) + "%", Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.devastation, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = null;
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
            }
        });
        this.MODE_LOOT = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                final float fProvinceAlpha = ProvinceDraw.getProvinceAlpha() * MapModeManager.fAlphaAnimation;
                ProvinceDraw.drawWastelandProvinces(oSB);
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    oSB.setColor(Colors.getProvinceLootColor((int)(Game.getProvince(Game.getProvinceInViewID(i)).getLoot() * 100.0f), fProvinceAlpha));
                    Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    oSB.setColor(Colors.getProvinceLootColor((int)(Game.getProvince(Game.getExtraProvinceInViewID(i)).getLoot() * 100.0f), fProvinceAlpha));
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                }
                ProvinceDraw.drawOccupiedProvinces(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID()));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Loot") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getProvince(Game.iHoveredProvinceID).getLoot() * 100.0f, 100) + "%", Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.loot, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Gold") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getLootValue(Game.iHoveredProvinceID), 100), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = null;
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
            }
        });
        this.MODE_DEFENSE_LEVEL = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                final float fProvinceAlpha = ProvinceDraw.getProvinceAlpha() * MapModeManager.fAlphaAnimation;
                ProvinceDraw.drawWastelandProvinces(oSB);
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getProvinceInViewID(i)).getFortLevel() == 0) {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlpha));
                    }
                    else {
                        oSB.setColor(new Color(Colors.COLOR_PROVINCE_DEFENSE.r, Colors.COLOR_PROVINCE_DEFENSE.g, Colors.COLOR_PROVINCE_DEFENSE.b, fProvinceAlpha));
                    }
                    Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getFortLevel() == 0) {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlpha));
                    }
                    else {
                        oSB.setColor(new Color(Colors.COLOR_PROVINCE_DEFENSE.r, Colors.COLOR_PROVINCE_DEFENSE.g, Colors.COLOR_PROVINCE_DEFENSE.b, fProvinceAlpha));
                    }
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                }
                ProvinceDraw.drawOccupiedProvinces(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID()));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DefenseLevel") + ": ", "" + Game.getProvince(Game.iHoveredProvinceID).getFortLevel(), Images.fort, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = null;
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
            }
        });
        this.MODE_DISEASES = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                try {
                    final float fProvinceAlpha = ProvinceDraw.getProvinceAlpha() * MapModeManager.fAlphaAnimation;
                    ProvinceDraw.drawWastelandProvinces(oSB);
                    for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).provincePlague != null) {
                            oSB.setColor(new Color(PlagueManager.activePlagues.get(Game.getProvince(Game.getProvinceInViewID(i)).provincePlague.id).fR, PlagueManager.activePlagues.get(Game.getProvince(Game.getProvinceInViewID(i)).provincePlague.id).fG, PlagueManager.activePlagues.get(Game.getProvince(Game.getProvinceInViewID(i)).provincePlague.id).fB, fProvinceAlpha));
                            Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                    }
                    for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).provincePlague != null) {
                            oSB.setColor(new Color(PlagueManager.activePlagues.get(Game.getProvince(Game.getExtraProvinceInViewID(i)).provincePlague.id).fR, PlagueManager.activePlagues.get(Game.getProvince(Game.getExtraProvinceInViewID(i)).provincePlague.id).fG, PlagueManager.activePlagues.get(Game.getProvince(Game.getExtraProvinceInViewID(i)).provincePlague.id).fB, fProvinceAlpha));
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                    }
                    ProvinceDraw.drawOccupiedProvinces(oSB);
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID()));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        if (Game.getProvince(Game.iHoveredProvinceID).provincePlague != null) {
                            nData.add(new MenuElement_HoverElement_Type_Line());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Disease") + ": "));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + PlagueManager.activePlagues.get(Game.getProvince(Game.iHoveredProvinceID).provincePlague.id).sName, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Disease(PlagueManager.activePlagues.get(Game.getProvince(Game.iHoveredProvinceID).provincePlague.id).iImageID, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Deaths") + ": "));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getNumberWithSpaces("" + Game.getProvince(Game.iHoveredProvinceID).provincePlague.deaths), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.skull, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = null;
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                Game.mapBG.updateActiveMapBGShader();
                Renderer.drawArmyInProvince = false;
                ProvinceDrawDetails.updateDrawProvinceDetails_Diseases();
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                Game.mapBG.updateActiveMapBGShader();
                Renderer.drawArmyInProvince = true;
            }
        });
        this.MODE_INFRASTRUCTURE = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                final float fProvinceAlpha = ProvinceDraw.getProvinceAlpha(Colors.PROVINCE_ALPHA_POPULATION) * MapModeManager.fAlphaAnimation;
                ProvinceDraw.drawWastelandProvinces(oSB);
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    oSB.setColor(Colors.getEconomyColor((int)(Game.getProvince(Game.getProvinceInViewID(i)).getInfrastructure() / (float)GameValues.infrastructure.INFRASTRUCTURE_MAX_LVL * 100.0f), fProvinceAlpha));
                    Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    oSB.setColor(Colors.getEconomyColor((int)(Game.getProvince(Game.getExtraProvinceInViewID(i)).getInfrastructure() / (float)GameValues.infrastructure.INFRASTRUCTURE_MAX_LVL * 100.0f), fProvinceAlpha));
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                }
                ProvinceDraw.drawOccupiedProvinces(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID()));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Infrastructure") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getProvince(Game.iHoveredProvinceID).getInfrastructure() + " / " + Game.getProvince(Game.iHoveredProvinceID).iInfrastructureMax, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.infrastructure, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = null;
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                Game.menuManager.rebuildInGame_RightInfrastructure();
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                MapModeManager.updateRightMenu();
            }
        });
        this.MODE_GOVERNMENT = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                final float fProvinceAlpha = ProvinceDraw.getProvinceAlpha() * MapModeManager.fAlphaAnimation;
                ProvinceDraw.drawWastelandProvinces(oSB);
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() > 0) {
                        oSB.setColor(new Color(Game.ideologiesManager.getIdeology(Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getIdeologyID()).Color[0], Game.ideologiesManager.getIdeology(Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getIdeologyID()).Color[1], Game.ideologiesManager.getIdeology(Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getIdeologyID()).Color[2], fProvinceAlpha));
                        Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                    }
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() > 0) {
                        oSB.setColor(new Color(Game.ideologiesManager.getIdeology(Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getIdeologyID()).Color[0], Game.ideologiesManager.getIdeology(Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getIdeologyID()).Color[1], Game.ideologiesManager.getIdeology(Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getIdeologyID()).Color[2], fProvinceAlpha));
                        Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvince(oSB);
                    }
                }
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID()));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Government") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.ideologiesManager.getIdeology(Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).getIdeologyID()).Name, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = null;
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                InGame_RightGovernment.iGovID = -1;
                Game.menuManager.rebuildInGame_RightGovernment();
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                MapModeManager.updateRightMenu();
            }
        });
        this.MODE_RELIGION = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                final float fProvinceAlpha = ProvinceDraw.getProvinceAlpha() * MapModeManager.fAlphaAnimation;
                ProvinceDraw.drawWastelandProvinces(oSB);
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() > 0) {
                        oSB.setColor(new Color(Game.religionManager.getReligion(Game.getProvince(Game.getProvinceInViewID(i)).getReligion()).Color[0], Game.religionManager.getReligion(Game.getProvince(Game.getProvinceInViewID(i)).getReligion()).Color[1], Game.religionManager.getReligion(Game.getProvince(Game.getProvinceInViewID(i)).getReligion()).Color[2], fProvinceAlpha));
                        Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                    }
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() > 0) {
                        oSB.setColor(new Color(Game.religionManager.getReligion(Game.getProvince(Game.getExtraProvinceInViewID(i)).getReligion()).Color[0], Game.religionManager.getReligion(Game.getProvince(Game.getExtraProvinceInViewID(i)).getReligion()).Color[1], Game.religionManager.getReligion(Game.getProvince(Game.getExtraProvinceInViewID(i)).getReligion()).Color[2], fProvinceAlpha));
                        Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvince(oSB);
                    }
                }
                oSB.setShader(Renderer.shaderAlpha_Pattern);
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() > 0 && Game.getProvince(Game.getProvinceInViewID(i)).getReligion() != Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getReligionID()) {
                        Game.getProvince(Game.getProvinceInViewID(i)).drawOccupiedProvince_Religion(oSB);
                    }
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() > 0 && Game.getProvince(Game.getExtraProvinceInViewID(i)).getReligion() != Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getReligionID()) {
                        Game.getProvince(Game.getExtraProvinceInViewID(i)).drawOccupiedProvince_Religion(oSB);
                    }
                }
                oSB.setShader(Renderer.shaderDefault);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID()));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Religion") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.religionManager.getReligion(Game.getProvince(Game.iHoveredProvinceID).getReligion()).Name, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Religion(Game.getProvince(Game.iHoveredProvinceID).getReligion(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = null;
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                InGame_RightReligion.iReligionID = -1;
                Game.menuManager.rebuildInGame_RightReligion();
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                MapModeManager.updateRightMenu();
            }
        });
        this.MODE_PROVINCE_INCOME_HOVER = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                MapModeManager.drawProvinces_DefaultHover_Player(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                Game.provinceHover_Informations = MapModeManager.getProvinceHover_Default();
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                MapModeManager.this.PROVINCE_INCOME_MAX = 0.15f;
                for (int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                    if (Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).getProvinceIncome() > MapModeManager.this.PROVINCE_INCOME_MAX) {
                        MapModeManager.this.PROVINCE_INCOME_MAX = Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).getProvinceIncome();
                    }
                }
                MapModeManager.this.PROVINCE_INCOME_MAX *= 0.95f;
                for (int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                    Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).provinceColor = Colors.getProvinceIncomeColor((int)(Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).getProvinceIncome() / MapModeManager.this.PROVINCE_INCOME_MAX * 100.0f), 1.0f);
                }
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                MapModeManager.lTime = 0L;
            }
        });
        this.MODE_PROVINCE_EXPENSES_HOVER = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                MapModeManager.drawProvinces_DefaultHover(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                Game.provinceHover_Informations = MapModeManager.getProvinceHover_Default();
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                MapModeManager.this.PROVINCE_INCOME_MAX = 0.025f;
                for (int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                    if (Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).fProvinceMaintenance > MapModeManager.this.PROVINCE_INCOME_MAX) {
                        MapModeManager.this.PROVINCE_INCOME_MAX = Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).fProvinceMaintenance;
                    }
                }
                MapModeManager.this.PROVINCE_INCOME_MAX *= 0.95f;
                for (int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                    Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).provinceColor = Colors.getProvinceRedColor((int)(Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).fProvinceMaintenance / MapModeManager.this.PROVINCE_INCOME_MAX * 100.0f), 1.0f);
                }
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                MapModeManager.lTime = 0L;
            }
        });
        this.MODE_CIV_POPULATION_HOVER = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                MapModeManager.updateDiplomacyProvinceColor(false);
                ProvinceDraw.drawProvinces_Diplomacy_Hover(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                Game.provinceHover_Informations = MapModeManager.getProvinceHover_Default();
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                MapModeManager.this.POPULATION_MAX = 1;
                final int civID = MapModeManager.diplomacyActiveCivID;
                for (int i = 0; i < Game.getCiv(civID).getNumOfProvinces(); ++i) {
                    if (Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getPopulationTotal() > MapModeManager.this.POPULATION_MAX) {
                        MapModeManager.this.POPULATION_MAX = Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getPopulationTotal();
                    }
                }
                MapModeManager.this.POPULATION_MAX *= (int)0.95f;
                for (int i = 0; i < Game.getCiv(civID).getNumOfProvinces(); ++i) {
                    Game.getProvince(Game.getCiv(civID).getProvinceID(i)).provinceColor = Colors.getPopulationColor((int)(Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getPopulationTotal() / (float)MapModeManager.this.POPULATION_MAX * 100.0f), 1.0f);
                }
                MapModeManager.lTime = 0L;
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                MapModeManager.lTime = 0L;
            }
        });
        this.MODE_CIV_ECONOMY_HOVER = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                MapModeManager.updateDiplomacyProvinceColor(false);
                ProvinceDraw.drawProvinces_Diplomacy_Hover(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                Game.provinceHover_Informations = MapModeManager.getProvinceHover_Default();
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                MapModeManager.this.ECONOMY_MAX = 1.0f;
                final int civID = MapModeManager.diplomacyActiveCivID;
                for (int i = 0; i < Game.getCiv(civID).getNumOfProvinces(); ++i) {
                    if (Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getEconomyWithBonuses() > MapModeManager.this.ECONOMY_MAX) {
                        MapModeManager.this.ECONOMY_MAX = Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getEconomyWithBonuses();
                    }
                }
                MapModeManager.this.ECONOMY_MAX *= 0.95f;
                for (int i = 0; i < Game.getCiv(civID).getNumOfProvinces(); ++i) {
                    Game.getProvince(Game.getCiv(civID).getProvinceID(i)).provinceColor = Colors.getEconomyColor((int)(Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getEconomyWithBonuses() / MapModeManager.this.ECONOMY_MAX * 100.0f), 1.0f);
                }
                MapModeManager.lTime = 0L;
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                MapModeManager.lTime = 0L;
            }
        });
        this.MODE_PROVINCE_MANPOWER_HOVER_PLAYER = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                MapModeManager.drawProvinces_DefaultHover_Player(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                Game.provinceHover_Informations = MapModeManager.getProvinceHover_Default();
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                MapModeManager.this.PROVINCE_MANPOWER_MAX = 0.15f;
                for (int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                    if (Game.getManpowerMaxFromProvinceManpowerLvl(Game.getCiv(Game.player.iCivID).getProvinceID(i)) > MapModeManager.this.PROVINCE_MANPOWER_MAX) {
                        MapModeManager.this.PROVINCE_MANPOWER_MAX = (float)Game.getManpowerMaxFromProvinceManpowerLvl(Game.getCiv(Game.player.iCivID).getProvinceID(i));
                    }
                }
                MapModeManager.this.PROVINCE_MANPOWER_MAX *= 0.95f;
                for (int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                    Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).provinceColor = Colors.getProvinceManpowerColor((int)(Game.getManpowerMaxFromProvinceManpowerLvl(Game.getCiv(Game.player.iCivID).getProvinceID(i)) / MapModeManager.this.PROVINCE_MANPOWER_MAX * 100.0f), 1.0f);
                }
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                MapModeManager.lTime = 0L;
            }
        });
        this.MODE_CIV_STABILITY_HOVER = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                MapModeManager.drawProvinces_DefaultHover(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                Game.provinceHover_Informations = MapModeManager.getProvinceHover_Default();
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                for (int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                    if (!Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).haveACore || Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).getReligion() != Game.getCiv(Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).getCivID()).getReligionID()) {
                        Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).provinceColor = Colors.HOVER_NEGATIVE;
                    }
                    else {
                        Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).provinceColor = Game.getCiv(Game.player.iCivID).civColorMap;
                    }
                }
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                MapModeManager.lTime = 0L;
            }
        });
        this.MODE_PROVINCE_UNREST_HOVER = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                MapModeManager.drawProvinces_DefaultHover(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                Game.provinceHover_Informations = MapModeManager.getProvinceHover_Default();
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                for (int activeCivID = (Game.iActiveProvince >= 0) ? ((Game.getProvince(Game.iActiveProvince).getCivID() > 0) ? Game.getProvince(Game.iActiveProvince).getCivID() : Game.player.iCivID) : Game.player.iCivID, i = 0; i < Game.getCiv(activeCivID).getNumOfProvinces(); ++i) {
                    if (Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).getRevulutionaryRisk() < 0.5f) {
                        Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).provinceColor = MapModeManager.PROVINCE_GRAY;
                    }
                    else {
                        Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).provinceColor = Colors.getProvinceRedColor((int)(Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).getRevulutionaryRisk() / 50.0f * 100.0f), 1.0f);
                    }
                }
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                MapModeManager.lTime = 0L;
            }
        });
        this.MODE_PROVINCE_MANPOWER_HOVER = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                MapModeManager.drawProvinces_DefaultHover(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                Game.provinceHover_Informations = MapModeManager.getProvinceHover_Default();
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                MapModeManager.this.PROVINCE_MANPOWER_MAX = 0.15f;
                final int activeCivID = (Game.iActiveProvince >= 0) ? ((Game.getProvince(Game.iActiveProvince).getCivID() > 0) ? Game.getProvince(Game.iActiveProvince).getCivID() : Game.player.iCivID) : Game.player.iCivID;
                for (int i = 0; i < Game.getCiv(activeCivID).getNumOfProvinces(); ++i) {
                    if (Game.getManpowerMaxFromProvinceManpowerLvl(Game.getCiv(activeCivID).getProvinceID(i)) > MapModeManager.this.PROVINCE_MANPOWER_MAX) {
                        MapModeManager.this.PROVINCE_MANPOWER_MAX = (float)Game.getManpowerMaxFromProvinceManpowerLvl(Game.getCiv(activeCivID).getProvinceID(i));
                    }
                }
                MapModeManager.this.PROVINCE_MANPOWER_MAX *= 0.95f;
                for (int i = 0; i < Game.getCiv(activeCivID).getNumOfProvinces(); ++i) {
                    Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).provinceColor = Colors.getProvinceManpowerColor((int)(Game.getManpowerMaxFromProvinceManpowerLvl(Game.getCiv(activeCivID).getProvinceID(i)) / MapModeManager.this.PROVINCE_MANPOWER_MAX * 100.0f), 1.0f);
                }
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                MapModeManager.lTime = 0L;
            }
        });
        this.MODE_PROVINCE_TAX_EFFICIENCY_HOVER = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                MapModeManager.drawProvinces_DefaultHover(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                Game.provinceHover_Informations = MapModeManager.getProvinceHover_Default();
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                MapModeManager.this.PROVINCE_MANPOWER_MAX = 0.15f;
                final int activeCivID = (Game.iActiveProvince >= 0) ? ((Game.getProvince(Game.iActiveProvince).getCivID() > 0) ? Game.getProvince(Game.iActiveProvince).getCivID() : Game.player.iCivID) : Game.player.iCivID;
                for (int i = 0; i < Game.getCiv(activeCivID).getNumOfProvinces(); ++i) {
                    if (Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).getTaxEfficiencyWithBonuses() > MapModeManager.this.PROVINCE_MANPOWER_MAX) {
                        MapModeManager.this.PROVINCE_MANPOWER_MAX = Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).getTaxEfficiencyWithBonuses();
                    }
                }
                MapModeManager.this.PROVINCE_MANPOWER_MAX *= 0.95f;
                for (int i = 0; i < Game.getCiv(activeCivID).getNumOfProvinces(); ++i) {
                    Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).provinceColor = Colors.getProvinceTaxColor((int)(Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).getTaxEfficiencyWithBonuses() / MapModeManager.this.PROVINCE_MANPOWER_MAX * 100.0f), 1.0f);
                }
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                MapModeManager.lTime = 0L;
            }
        });
        this.MODE_PROVINCE_ECONOMY_HOVER = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                MapModeManager.drawProvinces_DefaultHover(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                Game.provinceHover_Informations = MapModeManager.getProvinceHover_Default();
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                MapModeManager.this.PROVINCE_MANPOWER_MAX = 0.15f;
                final int activeCivID = (Game.iActiveProvince >= 0) ? ((Game.getProvince(Game.iActiveProvince).getCivID() > 0) ? Game.getProvince(Game.iActiveProvince).getCivID() : Game.player.iCivID) : Game.player.iCivID;
                for (int i = 0; i < Game.getCiv(activeCivID).getNumOfProvinces(); ++i) {
                    if (Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).getEconomyWithBonuses() > MapModeManager.this.PROVINCE_MANPOWER_MAX) {
                        MapModeManager.this.PROVINCE_MANPOWER_MAX = Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).getEconomyWithBonuses();
                    }
                }
                MapModeManager.this.PROVINCE_MANPOWER_MAX *= 0.95f;
                for (int i = 0; i < Game.getCiv(activeCivID).getNumOfProvinces(); ++i) {
                    Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).provinceColor = Colors.getProvinceEconomyColor((int)(Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).getEconomyWithBonuses() / MapModeManager.this.PROVINCE_MANPOWER_MAX * 100.0f), 1.0f);
                }
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                MapModeManager.lTime = 0L;
            }
        });
        this.MODE_PROVINCE_FORTS_HOVER = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                MapModeManager.drawProvinces_DefaultHover(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                Game.provinceHover_Informations = MapModeManager.getProvinceHover_Default();
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                MapModeManager.this.PROVINCE_MANPOWER_MAX = 0.1f;
                final int activeCivID = (Game.iActiveProvince >= 0) ? ((Game.getProvince(Game.iActiveProvince).getCivID() > 0) ? Game.getProvince(Game.iActiveProvince).getCivID() : Game.player.iCivID) : Game.player.iCivID;
                for (int i = 0; i < Game.getCiv(activeCivID).getNumOfProvinces(); ++i) {
                    if (Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).getFortLevel() > MapModeManager.this.PROVINCE_MANPOWER_MAX) {
                        MapModeManager.this.PROVINCE_MANPOWER_MAX = (float)Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).getFortLevel();
                    }
                }
                MapModeManager.this.PROVINCE_MANPOWER_MAX *= 0.95f;
                for (int i = 0; i < Game.getCiv(activeCivID).getNumOfProvinces(); ++i) {
                    Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).provinceColor = Colors.getProvinceFortColor((int)(Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).getFortLevel() / MapModeManager.this.PROVINCE_MANPOWER_MAX * 100.0f), 1.0f);
                }
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                MapModeManager.lTime = 0L;
            }
        });
        this.MODE_PROVINCE_PROVINCE_INCOME_HOVER = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                MapModeManager.drawProvinces_DefaultHover(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                Game.provinceHover_Informations = MapModeManager.getProvinceHover_Default();
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                MapModeManager.this.PROVINCE_MANPOWER_MAX = 0.1f;
                final int activeCivID = (Game.iActiveProvince >= 0) ? ((Game.getProvince(Game.iActiveProvince).getCivID() > 0) ? Game.getProvince(Game.iActiveProvince).getCivID() : Game.player.iCivID) : Game.player.iCivID;
                for (int i = 0; i < Game.getCiv(activeCivID).getNumOfProvinces(); ++i) {
                    if (Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).getProvinceIncome() - Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).fProvinceMaintenance > MapModeManager.this.PROVINCE_MANPOWER_MAX) {
                        MapModeManager.this.PROVINCE_MANPOWER_MAX = Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).getProvinceIncome() - Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).fProvinceMaintenance;
                    }
                }
                MapModeManager.this.PROVINCE_MANPOWER_MAX *= 0.95f;
                for (int i = 0; i < Game.getCiv(activeCivID).getNumOfProvinces(); ++i) {
                    Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).provinceColor = Colors.getProvinceIncomeColor((int)((Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).getProvinceIncome() - Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).fProvinceMaintenance) / MapModeManager.this.PROVINCE_MANPOWER_MAX * 100.0f), 1.0f);
                }
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                MapModeManager.lTime = 0L;
            }
        });
        this.MODE_PROVINCE_INFRASTRUCTURE_HOVER = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                MapModeManager.drawProvinces_DefaultHover(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                Game.provinceHover_Informations = MapModeManager.getProvinceHover_Default();
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                MapModeManager.this.PROVINCE_MANPOWER_MAX = 0.1f;
                final int activeCivID = (Game.iActiveProvince >= 0) ? ((Game.getProvince(Game.iActiveProvince).getCivID() > 0) ? Game.getProvince(Game.iActiveProvince).getCivID() : Game.player.iCivID) : Game.player.iCivID;
                for (int i = 0; i < Game.getCiv(activeCivID).getNumOfProvinces(); ++i) {
                    if (Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).getInfrastructure() > MapModeManager.this.PROVINCE_MANPOWER_MAX) {
                        MapModeManager.this.PROVINCE_MANPOWER_MAX = (float)Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).getInfrastructure();
                    }
                }
                MapModeManager.this.PROVINCE_MANPOWER_MAX *= 0.95f;
                for (int i = 0; i < Game.getCiv(activeCivID).getNumOfProvinces(); ++i) {
                    Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).provinceColor = Colors.getProvinceInfrastructureColor((int)(Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).getInfrastructure() / MapModeManager.this.PROVINCE_MANPOWER_MAX * 100.0f), 1.0f);
                }
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                MapModeManager.lTime = 0L;
            }
        });
        this.MODE_PROVINCE_PROVINCE_VALUE_HOVER = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                MapModeManager.drawProvinces_DefaultHover(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                Game.provinceHover_Informations = MapModeManager.getProvinceHover_Default();
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                MapModeManager.this.PROVINCE_MANPOWER_MAX = 0.1f;
                final int activeCivID = (Game.iActiveProvince >= 0) ? ((Game.getProvince(Game.iActiveProvince).getCivID() > 0) ? Game.getProvince(Game.iActiveProvince).getCivID() : Game.player.iCivID) : Game.player.iCivID;
                for (int i = 0; i < Game.getCiv(activeCivID).getNumOfProvinces(); ++i) {
                    if (Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).fProvinceValue > MapModeManager.this.PROVINCE_MANPOWER_MAX) {
                        MapModeManager.this.PROVINCE_MANPOWER_MAX = Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).fProvinceValue;
                    }
                }
                MapModeManager.this.PROVINCE_MANPOWER_MAX *= 0.95f;
                for (int i = 0; i < Game.getCiv(activeCivID).getNumOfProvinces(); ++i) {
                    Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).provinceColor = Colors.getProvinceProvinceValueColor((int)(Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).fProvinceValue / MapModeManager.this.PROVINCE_MANPOWER_MAX * 100.0f), 1.0f);
                }
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                MapModeManager.lTime = 0L;
            }
        });
        this.MODE_PROVINCE_LOOT_HOVER = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                MapModeManager.drawProvinces_DefaultHover(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                Game.provinceHover_Informations = MapModeManager.getProvinceHover_Default();
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                MapModeManager.this.PROVINCE_MANPOWER_MAX = 0.1f;
                final int activeCivID = (Game.iActiveProvince >= 0) ? ((Game.getProvince(Game.iActiveProvince).getCivID() > 0) ? Game.getProvince(Game.iActiveProvince).getCivID() : Game.player.iCivID) : Game.player.iCivID;
                for (int i = 0; i < Game.getCiv(activeCivID).getNumOfProvinces(); ++i) {
                    if (Game.getLootValue(Game.getCiv(activeCivID).getProvinceID(i)) > MapModeManager.this.PROVINCE_MANPOWER_MAX) {
                        MapModeManager.this.PROVINCE_MANPOWER_MAX = Game.getLootValue(Game.getCiv(activeCivID).getProvinceID(i));
                    }
                }
                MapModeManager.this.PROVINCE_MANPOWER_MAX *= 0.95f;
                for (int i = 0; i < Game.getCiv(activeCivID).getNumOfProvinces(); ++i) {
                    Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).provinceColor = Colors.getProvinceLootColor((int)(Game.getLootValue(Game.getCiv(activeCivID).getProvinceID(i)) / MapModeManager.this.PROVINCE_MANPOWER_MAX * 100.0f), 1.0f);
                }
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                MapModeManager.lTime = 0L;
            }
        });
        this.MODE_PROVINCE_DEVASTATION_HOVER = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                MapModeManager.drawProvinces_DefaultHover(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                Game.provinceHover_Informations = MapModeManager.getProvinceHover_Default();
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                MapModeManager.this.PROVINCE_MANPOWER_MAX = 0.1f;
                final int activeCivID = (Game.iActiveProvince >= 0) ? ((Game.getProvince(Game.iActiveProvince).getCivID() > 0) ? Game.getProvince(Game.iActiveProvince).getCivID() : Game.player.iCivID) : Game.player.iCivID;
                for (int i = 0; i < Game.getCiv(activeCivID).getNumOfProvinces(); ++i) {
                    if (Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).getDevastation() > MapModeManager.this.PROVINCE_MANPOWER_MAX) {
                        MapModeManager.this.PROVINCE_MANPOWER_MAX = Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).getDevastation();
                    }
                }
                MapModeManager.this.PROVINCE_MANPOWER_MAX *= 0.95f;
                for (int i = 0; i < Game.getCiv(activeCivID).getNumOfProvinces(); ++i) {
                    Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).provinceColor = Colors.getProvinceDevastationColor((int)(Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).getDevastation() / MapModeManager.this.PROVINCE_MANPOWER_MAX * 100.0f), 1.0f);
                }
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                MapModeManager.lTime = 0L;
            }
        });
        this.MODE_PROVINCE_GROWTH_RATE_HOVER = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                MapModeManager.drawProvinces_DefaultHover(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                Game.provinceHover_Informations = MapModeManager.getProvinceHover_Default();
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                MapModeManager.this.PROVINCE_MANPOWER_MAX = 0.1f;
                final int activeCivID = (Game.iActiveProvince >= 0) ? ((Game.getProvince(Game.iActiveProvince).getCivID() > 0) ? Game.getProvince(Game.iActiveProvince).getCivID() : Game.player.iCivID) : Game.player.iCivID;
                for (int i = 0; i < Game.getCiv(activeCivID).getNumOfProvinces(); ++i) {
                    if (Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).getGrowthRateWithBonuses() > MapModeManager.this.PROVINCE_MANPOWER_MAX) {
                        MapModeManager.this.PROVINCE_MANPOWER_MAX = Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).getGrowthRateWithBonuses();
                    }
                }
                MapModeManager.this.PROVINCE_MANPOWER_MAX *= 0.95f;
                for (int i = 0; i < Game.getCiv(activeCivID).getNumOfProvinces(); ++i) {
                    Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).provinceColor = Colors.getPopulationColor((int)(Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).getGrowthRateWithBonuses() / MapModeManager.this.PROVINCE_MANPOWER_MAX * 100.0f), 1.0f);
                }
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                MapModeManager.lTime = 0L;
            }
        });
        this.MODE_PROVINCE_POPULATION_HOVER = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                MapModeManager.drawProvinces_DefaultHover(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                Game.provinceHover_Informations = MapModeManager.getProvinceHover_Default();
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                MapModeManager.this.PROVINCE_MANPOWER_MAX = 1.0f;
                final int activeCivID = (Game.iActiveProvince >= 0) ? ((Game.getProvince(Game.iActiveProvince).getCivID() > 0) ? Game.getProvince(Game.iActiveProvince).getCivID() : Game.player.iCivID) : Game.player.iCivID;
                for (int i = 0; i < Game.getCiv(activeCivID).getNumOfProvinces(); ++i) {
                    if (Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).getPopulationTotal() > MapModeManager.this.PROVINCE_MANPOWER_MAX) {
                        MapModeManager.this.PROVINCE_MANPOWER_MAX = (float)Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).getPopulationTotal();
                    }
                }
                MapModeManager.this.PROVINCE_MANPOWER_MAX *= 0.95f;
                for (int i = 0; i < Game.getCiv(activeCivID).getNumOfProvinces(); ++i) {
                    Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).provinceColor = Colors.getPopulationColor((int)(Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).getPopulationTotal() / MapModeManager.this.PROVINCE_MANPOWER_MAX * 100.0f), 1.0f);
                }
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                MapModeManager.lTime = 0L;
            }
        });
        this.MODE_PROVINCE_RELIGION_HOVER = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                final int activeCivID = (Game.iActiveProvince >= 0) ? ((Game.getProvince(Game.iActiveProvince).getCivID() > 0) ? Game.getProvince(Game.iActiveProvince).getCivID() : Game.player.iCivID) : Game.player.iCivID;
                MapModeManager.drawProvinces_DefaultHover(oSB);
                oSB.setShader(Renderer.shaderAlpha_Pattern);
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() != 0 && Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == activeCivID && Game.getProvince(Game.getProvinceInViewID(i)).getReligion() != Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getReligionID()) {
                        Game.getProvince(Game.getProvinceInViewID(i)).drawOccupiedProvince_Religion(oSB);
                    }
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() != 0 && Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == activeCivID && Game.getProvince(Game.getExtraProvinceInViewID(i)).getReligion() != Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getReligionID()) {
                        Game.getProvince(Game.getExtraProvinceInViewID(i)).drawOccupiedProvince_Religion(oSB);
                    }
                }
                oSB.setShader(Renderer.shaderDefault);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                Game.provinceHover_Informations = MapModeManager.getProvinceHover_Default();
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                for (int activeCivID = (Game.iActiveProvince >= 0) ? ((Game.getProvince(Game.iActiveProvince).getCivID() > 0) ? Game.getProvince(Game.iActiveProvince).getCivID() : Game.player.iCivID) : Game.player.iCivID, i = 0; i < Game.getCiv(activeCivID).getNumOfProvinces(); ++i) {
                    Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).provinceColor = new Color(Game.religionManager.getReligion(Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).getReligion()).Color[0], Game.religionManager.getReligion(Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).getReligion()).Color[1], Game.religionManager.getReligion(Game.getProvince(Game.getCiv(activeCivID).getProvinceID(i)).getReligion()).Color[2], 1.0f);
                }
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                MapModeManager.lTime = 0L;
            }
        });
        this.MODE_PROVINCE_INCOME = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                final float fProvinceAlpha = ProvinceDraw.getProvinceAlpha(Colors.PROVINCE_ALPHA_POPULATION) * MapModeManager.fAlphaAnimation;
                ProvinceDraw.drawWastelandProvinces(oSB);
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    Game.getProvince(Game.getProvinceInViewID(i)).provinceColor.a = fProvinceAlpha;
                    oSB.setColor(Game.getProvince(Game.getProvinceInViewID(i)).provinceColor);
                    Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor.a = fProvinceAlpha;
                    oSB.setColor(Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor);
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                }
                ProvinceDraw.drawOccupiedProvinces(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID()));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ProvinceIncome") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getProvince(Game.iHoveredProvinceID).getProvinceIncome(), 100), Colors.COLOR_TEXT_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = null;
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                MapModeManager.this.updateMaxProvinceIncome();
                for (int i = 0; i < Game.getProvincesSize(); ++i) {
                    if (!Game.getProvince(i).getSeaProvince()) {
                        Game.getProvince(i).provinceColor = Colors.getProvinceIncomeColor((int)(Game.getProvince(i).getProvinceIncome() / MapModeManager.this.PROVINCE_INCOME_MAX * 100.0f), 1.0f);
                    }
                }
                Game.menuManager.rebuildInGame_RightProvinceIncome();
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                MapModeManager.updateRightMenu();
            }
        });
        this.MODE_NEW_ARMY_CHOOSE_PROVINCE = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                final float fProvinceAlpha = ProvinceDraw.getProvinceAlpha() * MapModeManager.fAlphaAnimation;
                ProvinceDraw.drawWastelandProvinces(oSB);
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    final Province province = Game.getProvince(Game.getProvinceInViewID(i));
                    if (province.getCivID() == Game.player.iCivID || Game.getCiv(province.getCivID()).getPuppetOfCivID() == Game.player.iCivID) {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_GREEN.r, MapModeManager.PROVINCE_GREEN.g, MapModeManager.PROVINCE_GREEN.b, fProvinceAlpha));
                    }
                    else {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlpha));
                    }
                    province.drawLandProvince(oSB);
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    final Province province = Game.getProvince(Game.getExtraProvinceInViewID(i));
                    if (province.getCivID() == Game.player.iCivID || Game.getCiv(province.getCivID()).getPuppetOfCivID() == Game.player.iCivID) {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_GREEN.r, MapModeManager.PROVINCE_GREEN.g, MapModeManager.PROVINCE_GREEN.b, fProvinceAlpha));
                    }
                    else {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlpha));
                    }
                    province.drawLandProvinceExtra(oSB);
                }
                ProvinceDraw.drawOccupiedProvinces(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        if (Game.getProvince(Game.iHoveredProvinceID).getCivID() == Game.player.iCivID || Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).getPuppetOfCivID() == Game.player.iCivID) {
                            Game.setCursorRecruit();
                        }
                        else {
                            Game.setCursorX();
                        }
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                    Game.setCursorX();
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = null;
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                Game.setCursorRecruit();
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                Game.setCursorDefault();
            }
        });
        this.MODE_MERCENARIES_CHOOSE_PROVINCE = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                final float fProvinceAlpha = ProvinceDraw.getProvinceAlpha() * MapModeManager.fAlphaAnimation;
                ProvinceDraw.drawWastelandProvinces(oSB);
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_GREEN.r, MapModeManager.PROVINCE_GREEN.g, MapModeManager.PROVINCE_GREEN.b, fProvinceAlpha));
                    }
                    else {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlpha));
                    }
                    Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_GREEN.r, MapModeManager.PROVINCE_GREEN.g, MapModeManager.PROVINCE_GREEN.b, fProvinceAlpha));
                    }
                    else {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlpha));
                    }
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                }
                ProvinceDraw.drawOccupiedProvinces(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        if (Game.getProvince(Game.iHoveredProvinceID).getCivID() == Game.player.iCivID) {
                            Game.setCursorRecruit();
                        }
                        else {
                            Game.setCursorX();
                        }
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                    Game.setCursorX();
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = null;
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                Game.setCursorRecruit();
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                Game.setCursorDefault();
            }
        });
        this.MODE_RECRUIT_ARMY = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                final float fProvinceAlpha = ProvinceDraw.getProvinceAlpha() * MapModeManager.fAlphaAnimation;
                ProvinceDraw.drawWastelandProvinces(oSB);
                final boolean canRecruit = Game.getCiv(Game.player.iCivID).fGold >= Game.armyRecruit.cost;
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    final Province province = Game.getProvince(Game.getProvinceInViewID(i));
                    if (province.getCivID() == Game.player.iCivID || Game.getCiv(province.getCivID()).getPuppetOfCivID() == Game.player.iCivID) {
                        if (province.isOccupied() || !canRecruit) {
                            oSB.setColor(new Color(MapModeManager.PROVINCE_RED.r, MapModeManager.PROVINCE_RED.g, MapModeManager.PROVINCE_RED.b, fProvinceAlpha));
                        }
                        else if (province.provBonuses.RecruitArmyCostInProvince != 0.0f) {
                            final float fArmyCost = province.provBonuses.RecruitArmyCostInProvince / 35.0f;
                            oSB.setColor(new Color(MapModeManager.PROVINCE_GREEN.r + MapModeManager.PROVINCE_GREEN.r * fArmyCost, MapModeManager.PROVINCE_GREEN.g + MapModeManager.PROVINCE_GREEN.g * fArmyCost, MapModeManager.PROVINCE_GREEN.b + MapModeManager.PROVINCE_GREEN.b * fArmyCost, fProvinceAlpha));
                        }
                        else {
                            oSB.setColor(new Color(MapModeManager.PROVINCE_GREEN.r, MapModeManager.PROVINCE_GREEN.g, MapModeManager.PROVINCE_GREEN.b, fProvinceAlpha));
                        }
                    }
                    else {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlpha));
                    }
                    province.drawLandProvince(oSB);
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    final Province province = Game.getProvince(Game.getExtraProvinceInViewID(i));
                    if (province.getCivID() == Game.player.iCivID || Game.getCiv(province.getCivID()).getPuppetOfCivID() == Game.player.iCivID) {
                        if (province.isOccupied() || !canRecruit) {
                            oSB.setColor(new Color(MapModeManager.PROVINCE_RED.r, MapModeManager.PROVINCE_RED.g, MapModeManager.PROVINCE_RED.b, fProvinceAlpha));
                        }
                        else if (province.provBonuses.RecruitArmyCostInProvince != 0.0f) {
                            final float fArmyCost = province.provBonuses.RecruitArmyCostInProvince / 35.0f;
                            oSB.setColor(new Color(MapModeManager.PROVINCE_GREEN.r + MapModeManager.PROVINCE_GREEN.r * fArmyCost, MapModeManager.PROVINCE_GREEN.g + MapModeManager.PROVINCE_GREEN.g * fArmyCost, MapModeManager.PROVINCE_GREEN.b + MapModeManager.PROVINCE_GREEN.b * fArmyCost, fProvinceAlpha));
                        }
                        else {
                            oSB.setColor(new Color(MapModeManager.PROVINCE_GREEN.r, MapModeManager.PROVINCE_GREEN.g, MapModeManager.PROVINCE_GREEN.b, fProvinceAlpha));
                        }
                    }
                    else {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlpha));
                    }
                    province.drawLandProvinceExtra(oSB);
                }
                ProvinceDraw.drawOccupiedProvinces(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        if (Game.getProvince(Game.iHoveredProvinceID).getCivID() == Game.player.iCivID || Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).getPuppetOfCivID() == Game.player.iCivID) {
                            final float fCost = (float)ArmyManager.getRecruitmentCost(Game.player.iCivID, Game.iHoveredProvinceID, Game.armyRecruit.unitID, Game.armyRecruit.armyID);
                            final int iTime = ArmyManager.getRecruitmentTime(Game.player.iCivID, Game.iHoveredProvinceID, Game.armyRecruit.unitID, Game.armyRecruit.armyID);
                            if (Game.getCiv(Game.player.iCivID).fGold >= fCost) {
                                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ClickToRecruit") + ": "));
                                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(ArmyManager.lArmy.get(Game.armyRecruit.unitID).get(Game.armyRecruit.armyID).Name, Colors.COLOR_TEXT_GOLD));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            else {
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("InsufficientGold") + ": ", Colors.HOVER_NEGATIVE));
                                nData.add(new MenuElement_HoverElement_Type_Text("" + Game.armyRecruit.cost, Colors.COLOR_TEXT_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(fCost, 10), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("RecruitmentTime") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + iTime, CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
                            nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER_TIME, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            if (Game.getCiv(Game.player.iCivID).getArmyRegimentSize() + Game.getCiv(Game.player.iCivID).iArmyRecruitSize_Total >= Game.getCiv(Game.player.iCivID).iRegimentsLimit) {
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("RegimentsLimit") + ": ", CFG.FONT_REGULAR_SMALL));
                                nData.add(new MenuElement_HoverElement_Type_Text("" + (Game.getCiv(Game.player.iCivID).getArmyRegimentSize() + Game.getCiv(Game.player.iCivID).iArmyRecruitSize_Total) + " / " + Game.getCiv(Game.player.iCivID).iRegimentsLimit, CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                                nData.add(new MenuElement_HoverElement_Type_Image(Images.regimentsLimit, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ArmyRecruitmentCost") + ": ", CFG.FONT_REGULAR_SMALL));
                                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(GameValues.army.REGIMENTS_LIMIT_RECRUIT_COST_OVER * 100.0f, 10) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                                nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            else {
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("RegimentsLimit") + ": ", CFG.FONT_REGULAR_SMALL));
                                nData.add(new MenuElement_HoverElement_Type_Text("" + (Game.getCiv(Game.player.iCivID).getArmyRegimentSize() + Game.getCiv(Game.player.iCivID).iArmyRecruitSize_Total) + " / " + Game.getCiv(Game.player.iCivID).iRegimentsLimit, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_Image(Images.regimentsLimit, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            if (Game.getProvince(Game.iHoveredProvinceID).provBonuses.RecruitArmyCostInProvince != 0.0f) {
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ArmyRecruitmentCostInProvince") + ": ", CFG.FONT_REGULAR_SMALL));
                                nData.add(new MenuElement_HoverElement_Type_Text(((Game.getProvince(Game.iHoveredProvinceID).provBonuses.RecruitArmyCostInProvince > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getProvince(Game.iHoveredProvinceID).provBonuses.RecruitArmyCostInProvince, 100) + "%", CFG.FONT_BOLD_SMALL, (Game.getProvince(Game.iHoveredProvinceID).provBonuses.RecruitArmyCostInProvince > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_NEGATIVE : Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                                nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            if (CFG.isDesktop()) {
                                nData.add(new MenuElement_HoverElement_Type_Line());
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ShiftClickToRecruitXRegiments", 5), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("CtrlClickToRecruitXRegimentsInASingleProvince", 5), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            Game.setCursorRecruit();
                        }
                        else {
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            Game.setCursorX();
                        }
                        if (Game.getProvince(Game.iHoveredProvinceID).getCivID() == Game.player.iCivID) {
                            boolean addOnce = true;
                            int days = 0;
                            for (int i = 0; i < Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).getArmyRecruitSize(); ++i) {
                                for (int j = 0; j < Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).lArmyRecruit.get(i).size(); ++j) {
                                    if (Game.iHoveredProvinceID == Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).lArmyRecruit.get(i).get(j).provinceID) {
                                        if (addOnce) {
                                            addOnce = false;
                                            nData.add(new MenuElement_HoverElement_Type_Line());
                                            nElements.add(new MenuElement_HoverElement(nData));
                                            nData.clear();
                                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("TheArmyWillBeDeployedToThisLocation") + ": ", CFG.FONT_REGULAR_SMALL));
                                            nData.add(new MenuElement_HoverElement_Type_Text("" + Game_Calendar.getDate_ByTurnID(Game_Calendar.TURN_ID + Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).lArmyRecruit.get(i).get(j).timeLeft), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                                            nElements.add(new MenuElement_HoverElement(nData));
                                            nData.clear();
                                        }
                                        nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), 0, CFG.PADDING));
                                        nData.add(new MenuElement_HoverElement_Type_Text(ArmyManager.lArmy.get(Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).lArmyRecruit.get(i).get(j).unitID).get(Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).lArmyRecruit.get(i).get(j).armyID).Name + ": ", CFG.FONT_REGULAR_SMALL));
                                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DaysX", days + Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).lArmyRecruit.get(i).get(j).timeLeft), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                                        nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, 0));
                                        nElements.add(new MenuElement_HoverElement(nData));
                                        nData.clear();
                                        days += Game.getCiv(Game.getProvince(Game.iHoveredProvinceID).getCivID()).lArmyRecruit.get(i).get(j).timeLeft;
                                    }
                                }
                            }
                        }
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                    Game.setCursorX();
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = null;
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                Game.setCursorRecruit();
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                Game.setCursorDefault();
            }
            
            @Override
            public void playSFX_ProvinceClick() {
            }
        });
        this.MODE_TERRAIN = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                final float fProvinceAlpha = ProvinceDraw.getProvinceAlpha() * 1.5f * MapModeManager.fAlphaAnimation;
                ProvinceDraw.drawWastelandProvinces(oSB);
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    oSB.setColor(new Color(Game.terrainManager.terrains.get(Game.getProvince(Game.getProvinceInViewID(i)).getTerrainID()).Color[0], Game.terrainManager.terrains.get(Game.getProvince(Game.getProvinceInViewID(i)).getTerrainID()).Color[1], Game.terrainManager.terrains.get(Game.getProvince(Game.getProvinceInViewID(i)).getTerrainID()).Color[2], fProvinceAlpha));
                    Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    oSB.setColor(new Color(Game.terrainManager.terrains.get(Game.getProvince(Game.getExtraProvinceInViewID(i)).getTerrainID()).Color[0], Game.terrainManager.terrains.get(Game.getProvince(Game.getExtraProvinceInViewID(i)).getTerrainID()).Color[1], Game.terrainManager.terrains.get(Game.getProvince(Game.getExtraProvinceInViewID(i)).getTerrainID()).Color[2], fProvinceAlpha));
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                }
                ProvinceDraw.drawOccupiedProvinces(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID()));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Terrain") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.terrainManager.terrains.get(Game.getProvince(Game.iHoveredProvinceID).getTerrainID()).Name, Colors.COLOR_TEXT_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Terrain(Game.getProvince(Game.iHoveredProvinceID).getTerrainID(), Game.iHoveredProvinceID, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = null;
            }
        }));
        this.MODE_GOODS = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                final float fProvinceAlpha = ProvinceDraw.getProvinceAlpha() * MapModeManager.fAlphaAnimation;
                ProvinceDraw.drawWastelandProvinces(oSB);
                if ((Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getResourceID() >= 0) || InGame_RightGoods.iActiveResID >= 0) {
                    int resID = 0;
                    if (Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getResourceID() >= 0) {
                        resID = Game.getProvince(Game.iActiveProvince).getResourceID();
                    }
                    else {
                        resID = InGame_RightGoods.iActiveResID;
                    }
                    for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).getResourceID() == resID) {
                            oSB.setColor(new Color(ResourcesManager.lResources.get(Game.getProvince(Game.getProvinceInViewID(i)).getResourceID()).Color[0], ResourcesManager.lResources.get(Game.getProvince(Game.getProvinceInViewID(i)).getResourceID()).Color[1], ResourcesManager.lResources.get(Game.getProvince(Game.getProvinceInViewID(i)).getResourceID()).Color[2], fProvinceAlpha));
                            Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                    }
                    for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getResourceID() == resID) {
                            oSB.setColor(new Color(ResourcesManager.lResources.get(Game.getProvince(Game.getExtraProvinceInViewID(i)).getResourceID()).Color[0], ResourcesManager.lResources.get(Game.getProvince(Game.getExtraProvinceInViewID(i)).getResourceID()).Color[1], ResourcesManager.lResources.get(Game.getProvince(Game.getExtraProvinceInViewID(i)).getResourceID()).Color[2], fProvinceAlpha));
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                    }
                }
                else {
                    for (int j = 0; j < Game.NUM_OF_PROVINCES_IN_VIEW; ++j) {
                        if (Game.getProvince(Game.getProvinceInViewID(j)).getCivID() > 0 && Game.getProvince(Game.getProvinceInViewID(j)).getResourceID() >= 0) {
                            oSB.setColor(Game.getCiv(Game.getProvince(Game.getProvinceInViewID(j)).getCivID()).getColor(fProvinceAlpha));
                            Game.getProvince(Game.getProvinceInViewID(j)).drawLandProvince(oSB);
                        }
                    }
                    for (int j = 0; j < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++j) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(j)).getCivID() > 0 && Game.getProvince(Game.getExtraProvinceInViewID(j)).getResourceID() >= 0) {
                            oSB.setColor(Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(j)).getCivID()).getColor(fProvinceAlpha));
                            Game.getProvince(Game.getExtraProvinceInViewID(j)).drawLandProvince(oSB);
                        }
                    }
                }
                ProvinceDraw.drawOccupiedProvinces(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID()));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Resource") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_Text(ResourcesManager.lResources.get(Game.getProvince(Game.iHoveredProvinceID).getResourceID()).Name, Colors.COLOR_TEXT_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Resource(Game.getProvince(Game.iHoveredProvinceID).getResourceID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                    Game.setCursorX();
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = null;
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                InGame_RightGoods.iActiveResID = -1;
                Game.menuManager.rebuildInGame_RightGoods();
                Renderer.drawArmyInProvince = false;
                ProvinceDrawDetails.updateDrawProvinceDetails_Goods();
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                Renderer.drawArmyInProvince = true;
                MapModeManager.updateRightMenu();
            }
        });
        this.MODE_WONDERS = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                final float fProvinceAlpha = ProvinceDraw.getProvinceAlpha() * MapModeManager.fAlphaAnimation;
                ProvinceDraw.drawWastelandProvinces(oSB);
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getProvinceInViewID(i)).wonderID >= 0) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).getWonderBuilt()) {
                            oSB.setColor(new Color(Colors.HOVER_GOLD.r, Colors.HOVER_GOLD.g, Colors.HOVER_GOLD.b, fProvinceAlpha));
                        }
                        else {
                            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, fProvinceAlpha));
                        }
                        Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                    }
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getExtraProvinceInViewID(i)).wonderID >= 0) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getWonderBuilt()) {
                            oSB.setColor(new Color(Colors.HOVER_GOLD.r, Colors.HOVER_GOLD.g, Colors.HOVER_GOLD.b, fProvinceAlpha));
                        }
                        else {
                            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, fProvinceAlpha));
                        }
                        Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvince(oSB);
                    }
                }
                ProvinceDraw.drawOccupiedProvinces(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0 && Game.getProvince(Game.iHoveredProvinceID).wonderID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iHoveredProvinceID).getCivID()));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Wonder") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_Text(WondersManager.wonders.get(Game.getProvince(Game.iHoveredProvinceID).wonderID).Name, Colors.COLOR_TEXT_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                    Game.setCursorX();
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = null;
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                Renderer.drawArmyInProvince = false;
                ProvinceDrawDetails.updateDrawProvinceDetails_Wonders();
                Game.menuManager.rebuildInGame_RightWonders();
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                Renderer.drawArmyInProvince = true;
                if (Game.menuManager.getVisibleInGame_Wonder()) {
                    Game.menuManager.setVisibleInGame_Wonder(false);
                }
                MapModeManager.updateRightMenu();
            }
        });
        this.MODE_INVEST_IN_ECONOMY = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                final float fProvinceAlpha = ProvinceDraw.getProvinceAlpha() * MapModeManager.fAlphaAnimation;
                ProvinceDraw.drawWastelandProvinces(oSB);
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).isOccupied() || Game.getCiv(Game.player.iCivID).fGold < Game.getInvestCost(Game.getProvinceInViewID(i)) || Game.getCiv(Game.player.iCivID).fLegacy < Game.getInvestCost_Legacy(Game.getProvinceInViewID(i)) || Game.canInvestInEconomy(Game.getProvinceInViewID(i))) {
                            oSB.setColor(new Color(MapModeManager.PROVINCE_RED.r, MapModeManager.PROVINCE_RED.g, MapModeManager.PROVINCE_RED.b, fProvinceAlpha));
                        }
                        else if (Game.getProvince(Game.getProvinceInViewID(i)).iProvinceInvestSize == 1) {
                            oSB.setColor(CFG.getColorStep(ProvinceDraw.progressBar2, Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getColor(1.0f), (int)(100.0f - Game.getProvince(Game.getProvinceInViewID(i)).provinceInvestDaysLeft.get(0).daysLeft / (float)Game.getProvince(Game.getProvinceInViewID(i)).provinceInvestDaysLeft.get(0).investTime * 100.0f), 100, fProvinceAlpha));
                        }
                        else if (Game.getProvince(Game.getProvinceInViewID(i)).iProvinceInvestSize > 0) {
                            oSB.setColor(CFG.getLighter(new Color(ProvinceDraw.progressBar2.r, ProvinceDraw.progressBar2.g, ProvinceDraw.progressBar2.b, fProvinceAlpha), 7 * (Game.getProvince(Game.getProvinceInViewID(i)).iProvinceInvestSize - 1), fProvinceAlpha));
                        }
                        else {
                            oSB.setColor(new Color(Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getR(), Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getG(), Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getB(), fProvinceAlpha));
                        }
                    }
                    else {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlpha));
                    }
                    Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).isOccupied() || Game.getCiv(Game.player.iCivID).fGold < Game.getInvestCost(Game.getExtraProvinceInViewID(i)) || Game.getCiv(Game.player.iCivID).fLegacy < Game.getInvestCost_Legacy(Game.getExtraProvinceInViewID(i)) || Game.canInvestInEconomy(Game.getExtraProvinceInViewID(i))) {
                            oSB.setColor(new Color(MapModeManager.PROVINCE_RED.r, MapModeManager.PROVINCE_RED.g, MapModeManager.PROVINCE_RED.b, fProvinceAlpha));
                        }
                        else if (Game.getProvince(Game.getExtraProvinceInViewID(i)).iProvinceInvestSize == 1) {
                            oSB.setColor(CFG.getColorStep(ProvinceDraw.progressBar2, Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getColor(1.0f), (int)(100.0f - Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceInvestDaysLeft.get(0).daysLeft / (float)Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceInvestDaysLeft.get(0).investTime * 100.0f), 100, fProvinceAlpha));
                        }
                        else if (Game.getProvince(Game.getExtraProvinceInViewID(i)).iProvinceInvestSize > 0) {
                            oSB.setColor(CFG.getLighter(new Color(ProvinceDraw.progressBar2.r, ProvinceDraw.progressBar2.g, ProvinceDraw.progressBar2.b, fProvinceAlpha), 7 * (Game.getProvince(Game.getExtraProvinceInViewID(i)).iProvinceInvestSize - 1), fProvinceAlpha));
                        }
                        else {
                            oSB.setColor(new Color(Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getR(), Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getG(), Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getB(), fProvinceAlpha));
                        }
                    }
                    else {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlpha));
                    }
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvince(oSB);
                }
                ProvinceDraw.drawOccupiedProvinces(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                Renderer.drawArmyInProvince = false;
                ProvinceDrawDetails.updateDrawProvinceDetails_Goods_InvestInEconomy();
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                Renderer.drawArmyInProvince = true;
                Game.setCursorDefault();
            }
        });
        this.MODE_DEVELOP_INFRASTRUCTURE = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                final float fProvinceAlpha = ProvinceDraw.getProvinceAlpha() * MapModeManager.fAlphaAnimation;
                ProvinceDraw.drawWastelandProvinces(oSB);
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).isOccupied() || Game.getCiv(Game.player.iCivID).fGold < Game.getDevelopInfrastructureCost(Game.getProvinceInViewID(i)) || Game.getCiv(Game.player.iCivID).fLegacy < Game.getDevelopInfrastructureCostLegacy(Game.getProvinceInViewID(i))) {
                            oSB.setColor(new Color(MapModeManager.PROVINCE_RED.r, MapModeManager.PROVINCE_RED.g, MapModeManager.PROVINCE_RED.b, fProvinceAlpha));
                        }
                        else if (Game.getProvince(Game.getProvinceInViewID(i)).iProvinceDevelopInfrastructureSize == 1) {
                            oSB.setColor(CFG.getColorStep(ProvinceDraw.progressBar2, Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getColor(1.0f), (int)(100.0f - Game.getProvince(Game.getProvinceInViewID(i)).provinceDevelopInfrastructureDaysLeft.get(0).daysLeft / (float)Game.getProvince(Game.getProvinceInViewID(i)).provinceDevelopInfrastructureDaysLeft.get(0).investTime * 100.0f), 100, fProvinceAlpha));
                        }
                        else if (Game.getProvince(Game.getProvinceInViewID(i)).iProvinceDevelopInfrastructureSize > 0) {
                            oSB.setColor(CFG.getLighter(new Color(ProvinceDraw.progressBar2.r, ProvinceDraw.progressBar2.g, ProvinceDraw.progressBar2.b, fProvinceAlpha), 7 * (Game.getProvince(Game.getProvinceInViewID(i)).iProvinceDevelopInfrastructureSize - 1), fProvinceAlpha));
                        }
                        else {
                            oSB.setColor(new Color(Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getR(), Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getG(), Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getB(), fProvinceAlpha));
                        }
                    }
                    else {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlpha));
                    }
                    Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).isOccupied() || Game.getCiv(Game.player.iCivID).fGold < Game.getDevelopInfrastructureCost(Game.getExtraProvinceInViewID(i)) || Game.getCiv(Game.player.iCivID).fLegacy < Game.getDevelopInfrastructureCostLegacy(Game.getExtraProvinceInViewID(i))) {
                            oSB.setColor(new Color(MapModeManager.PROVINCE_RED.r, MapModeManager.PROVINCE_RED.g, MapModeManager.PROVINCE_RED.b, fProvinceAlpha));
                        }
                        else if (Game.getProvince(Game.getExtraProvinceInViewID(i)).iProvinceDevelopInfrastructureSize == 1) {
                            oSB.setColor(CFG.getColorStep(ProvinceDraw.progressBar2, Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getColor(1.0f), (int)(100.0f - Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceDevelopInfrastructureDaysLeft.get(0).daysLeft / (float)Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceDevelopInfrastructureDaysLeft.get(0).investTime * 100.0f), 100, fProvinceAlpha));
                        }
                        else if (Game.getProvince(Game.getExtraProvinceInViewID(i)).iProvinceDevelopInfrastructureSize > 0) {
                            oSB.setColor(CFG.getLighter(new Color(ProvinceDraw.progressBar2.r, ProvinceDraw.progressBar2.g, ProvinceDraw.progressBar2.b, fProvinceAlpha), 7 * (Game.getProvince(Game.getExtraProvinceInViewID(i)).iProvinceDevelopInfrastructureSize - 1), fProvinceAlpha));
                        }
                        else {
                            oSB.setColor(new Color(Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getR(), Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getG(), Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getB(), fProvinceAlpha));
                        }
                    }
                    else {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlpha));
                    }
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvince(oSB);
                }
                ProvinceDraw.drawOccupiedProvinces(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        if (Game.getProvince(Game.iHoveredProvinceID).getCivID() == Game.player.iCivID) {
                            if (Game.getProvince(Game.iHoveredProvinceID).iInfrastructureMax > Game.getProvince(Game.iHoveredProvinceID).getInfrastructure() + Game.getProvince(Game.iHoveredProvinceID).iProvinceDevelopInfrastructureSize) {
                                if (Game.getCiv(Game.player.iCivID).fGold >= Game.getDevelopInfrastructureCost(Game.iHoveredProvinceID)) {
                                    if (Game.getCiv(Game.player.iCivID).fLegacy >= Game.getDevelopInfrastructureCostLegacy(Game.iHoveredProvinceID)) {
                                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ClickToDevelopInfrastructure"), Colors.HOVER_GOLD));
                                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.infrastructureUp, CFG.PADDING, 0));
                                        nElements.add(new MenuElement_HoverElement(nData));
                                        nData.clear();
                                        Game.setCursorInfrastructure();
                                    }
                                    else {
                                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("InsufficientLegacy") + ": ", Colors.HOVER_NEGATIVE));
                                        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getDevelopInfrastructureCostLegacy(Game.iHoveredProvinceID), 100), Colors.COLOR_TEXT_GOLD));
                                        nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, 0));
                                        nElements.add(new MenuElement_HoverElement(nData));
                                        nData.clear();
                                        Game.setCursorX();
                                    }
                                }
                                else {
                                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("InsufficientGold") + ": ", Colors.HOVER_NEGATIVE));
                                    nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getDevelopInfrastructureCost(Game.iHoveredProvinceID), 100), Colors.COLOR_TEXT_GOLD));
                                    nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    Game.setCursorX();
                                }
                            }
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Infrastructure") + ": "));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getProvince(Game.iHoveredProvinceID).getInfrastructure() + " / " + Game.getProvince(Game.iHoveredProvinceID).iInfrastructureMax, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.infrastructure, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            if (Game.getProvince(Game.iHoveredProvinceID).iProvinceDevelopInfrastructureSize > 1) {
                                nData.add(new MenuElement_HoverElement_Type_Line());
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                int tDays = 0;
                                for (int i = 0; i < Game.getProvince(Game.iHoveredProvinceID).iProvinceDevelopInfrastructureSize; ++i) {
                                    tDays += Game.getProvince(Game.iHoveredProvinceID).provinceDevelopInfrastructureDaysLeft.get(i).daysLeft;
                                }
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("InQueue") + ": ", CFG.FONT_REGULAR_SMALL));
                                nData.add(new MenuElement_HoverElement_Type_Text("" + (Game.getProvince(Game.iHoveredProvinceID).iProvinceDevelopInfrastructureSize - 1), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_Text(" [" + Game.lang.get("DaysX", tDays) + "]", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                                nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, CFG.PADDING));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                        }
                        else {
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            Game.setCursorX();
                        }
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                    Game.setCursorX();
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = null;
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                Game.setCursorDefault();
            }
        });
        this.MODE_INCREASE_TAX_EFFICIENCY = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                final float fProvinceAlpha = ProvinceDraw.getProvinceAlpha() * MapModeManager.fAlphaAnimation;
                ProvinceDraw.drawWastelandProvinces(oSB);
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).isOccupied() || Game.getCiv(Game.player.iCivID).fGold < Game.getIncreaseTaxEfficiencyCost(Game.getProvinceInViewID(i))) {
                            oSB.setColor(new Color(MapModeManager.PROVINCE_RED.r, MapModeManager.PROVINCE_RED.g, MapModeManager.PROVINCE_RED.b, fProvinceAlpha));
                        }
                        else if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getIncreaseTaxEfficiencyCostLegacy(Game.getProvinceInViewID(i))) {
                            oSB.setColor(new Color(MapModeManager.PROVINCE_RED.r, MapModeManager.PROVINCE_RED.g, MapModeManager.PROVINCE_RED.b, fProvinceAlpha));
                        }
                        else if (Game.getProvince(Game.getProvinceInViewID(i)).iProvinceIncreaseTaxEfficiencySize == 1) {
                            oSB.setColor(CFG.getColorStep(ProvinceDraw.progressBar2, Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getColor(1.0f), (int)(100.0f - Game.getProvince(Game.getProvinceInViewID(i)).provinceIncreasTaxEfficiencyDaysLeft.get(0).daysLeft / (float)Game.getProvince(Game.getProvinceInViewID(i)).provinceIncreasTaxEfficiencyDaysLeft.get(0).investTime * 100.0f), 100, fProvinceAlpha));
                        }
                        else if (Game.getProvince(Game.getProvinceInViewID(i)).iProvinceIncreaseTaxEfficiencySize > 0) {
                            oSB.setColor(CFG.getLighter(new Color(ProvinceDraw.progressBar2.r, ProvinceDraw.progressBar2.g, ProvinceDraw.progressBar2.b, fProvinceAlpha), 7 * (Game.getProvince(Game.getProvinceInViewID(i)).iProvinceIncreaseTaxEfficiencySize - 1), fProvinceAlpha));
                        }
                        else {
                            oSB.setColor(new Color(Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getR(), Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getG(), Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getB(), fProvinceAlpha));
                        }
                    }
                    else {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlpha));
                    }
                    Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).isOccupied() || Game.getCiv(Game.player.iCivID).fGold < Game.getIncreaseTaxEfficiencyCost(Game.getExtraProvinceInViewID(i))) {
                            oSB.setColor(new Color(MapModeManager.PROVINCE_RED.r, MapModeManager.PROVINCE_RED.g, MapModeManager.PROVINCE_RED.b, fProvinceAlpha));
                        }
                        else if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getIncreaseTaxEfficiencyCostLegacy(Game.getExtraProvinceInViewID(i))) {
                            oSB.setColor(new Color(MapModeManager.PROVINCE_RED.r, MapModeManager.PROVINCE_RED.g, MapModeManager.PROVINCE_RED.b, fProvinceAlpha));
                        }
                        else if (Game.getProvince(Game.getExtraProvinceInViewID(i)).iProvinceIncreaseTaxEfficiencySize == 1) {
                            oSB.setColor(CFG.getColorStep(ProvinceDraw.progressBar2, Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getColor(1.0f), (int)(100.0f - Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceIncreasTaxEfficiencyDaysLeft.get(0).daysLeft / (float)Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceIncreasTaxEfficiencyDaysLeft.get(0).investTime * 100.0f), 100, fProvinceAlpha));
                        }
                        else if (Game.getProvince(Game.getExtraProvinceInViewID(i)).iProvinceIncreaseTaxEfficiencySize > 0) {
                            oSB.setColor(CFG.getLighter(new Color(ProvinceDraw.progressBar2.r, ProvinceDraw.progressBar2.g, ProvinceDraw.progressBar2.b, fProvinceAlpha), 7 * (Game.getProvince(Game.getExtraProvinceInViewID(i)).iProvinceIncreaseTaxEfficiencySize - 1), fProvinceAlpha));
                        }
                        else {
                            oSB.setColor(new Color(Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getR(), Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getG(), Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getB(), fProvinceAlpha));
                        }
                    }
                    else {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlpha));
                    }
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvince(oSB);
                }
                ProvinceDraw.drawOccupiedProvinces(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        if (Game.getProvince(Game.iHoveredProvinceID).getCivID() == Game.player.iCivID) {
                            if (Game.getCiv(Game.player.iCivID).fGold >= Game.getIncreaseTaxEfficiencyCost(Game.iHoveredProvinceID)) {
                                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ClickToIncreaseLocalTaxEfficiency"), Colors.HOVER_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_ECONOMY, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            else {
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("InsufficientGold") + ": ", Colors.HOVER_NEGATIVE));
                                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getIncreaseTaxEfficiencyCost(Game.iHoveredProvinceID), 100), Colors.COLOR_TEXT_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("TaxEfficiency") + ": "));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getProvince(Game.iHoveredProvinceID).getTaxEfficiencyWithBonuses(), 100) + "%", Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.tax, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            if (Game.getProvince(Game.iHoveredProvinceID).iProvinceIncreaseTaxEfficiencySize > 1) {
                                nData.add(new MenuElement_HoverElement_Type_Line());
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                int tDays = 0;
                                for (int i = 0; i < Game.getProvince(Game.iHoveredProvinceID).iProvinceIncreaseTaxEfficiencySize; ++i) {
                                    tDays += Game.getProvince(Game.iHoveredProvinceID).provinceIncreasTaxEfficiencyDaysLeft.get(i).daysLeft;
                                }
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("InQueue") + ": ", CFG.FONT_REGULAR_SMALL));
                                nData.add(new MenuElement_HoverElement_Type_Text("" + (Game.getProvince(Game.iHoveredProvinceID).iProvinceIncreaseTaxEfficiencySize - 1), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_Text(" [" + Game.lang.get("DaysX", tDays) + "]", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                                nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, CFG.PADDING));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            if (CFG.isDesktop()) {
                                nData.add(new MenuElement_HoverElement_Type_Line());
                                nElements.add(new MenuElement_HoverElement(nData, false));
                                nData.clear();
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ShiftClickToIncreaseTaxationEfficiencyXTimes", 5), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                                nElements.add(new MenuElement_HoverElement(nData, false));
                                nData.clear();
                            }
                            Game.setCursorTax();
                        }
                        else {
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            Game.setCursorX();
                        }
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                    Game.setCursorX();
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = null;
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                Game.setCursorDefault();
            }
        });
        this.MODE_INCREASE_GROWTH_RATE = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                final float fProvinceAlpha = ProvinceDraw.getProvinceAlpha() * MapModeManager.fAlphaAnimation;
                ProvinceDraw.drawWastelandProvinces(oSB);
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).isOccupied() || Game.getCiv(Game.player.iCivID).fGold < Game.getIncreaseGrowthRateCost(Game.getProvinceInViewID(i))) {
                            oSB.setColor(new Color(MapModeManager.PROVINCE_RED.r, MapModeManager.PROVINCE_RED.g, MapModeManager.PROVINCE_RED.b, fProvinceAlpha));
                        }
                        else if (Game.getProvince(Game.getProvinceInViewID(i)).iProvinceIncreaseGrowthRateSize == 1) {
                            oSB.setColor(CFG.getColorStep(ProvinceDraw.progressBar2, Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getColor(1.0f), (int)(100.0f - Game.getProvince(Game.getProvinceInViewID(i)).provinceIncreaseGrowthRateDaysLeft.get(0).daysLeft / (float)Game.getProvince(Game.getProvinceInViewID(i)).provinceIncreaseGrowthRateDaysLeft.get(0).investTime * 100.0f), 100, fProvinceAlpha));
                        }
                        else if (Game.getProvince(Game.getProvinceInViewID(i)).iProvinceIncreaseGrowthRateSize > 0) {
                            oSB.setColor(CFG.getLighter(new Color(ProvinceDraw.progressBar2.r, ProvinceDraw.progressBar2.g, ProvinceDraw.progressBar2.b, fProvinceAlpha), 7 * (Game.getProvince(Game.getProvinceInViewID(i)).iProvinceIncreaseGrowthRateSize - 1), fProvinceAlpha));
                        }
                        else {
                            oSB.setColor(new Color(Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getR(), Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getG(), Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getB(), fProvinceAlpha));
                        }
                    }
                    else {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlpha));
                    }
                    Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).isOccupied() || Game.getCiv(Game.player.iCivID).fGold < Game.getIncreaseGrowthRateCost(Game.getExtraProvinceInViewID(i))) {
                            oSB.setColor(new Color(MapModeManager.PROVINCE_RED.r, MapModeManager.PROVINCE_RED.g, MapModeManager.PROVINCE_RED.b, fProvinceAlpha));
                        }
                        else if (Game.getProvince(Game.getExtraProvinceInViewID(i)).iProvinceIncreaseGrowthRateSize == 1) {
                            oSB.setColor(CFG.getColorStep(ProvinceDraw.progressBar2, Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getColor(1.0f), (int)(100.0f - Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceIncreaseGrowthRateDaysLeft.get(0).daysLeft / (float)Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceIncreaseGrowthRateDaysLeft.get(0).investTime * 100.0f), 100, fProvinceAlpha));
                        }
                        else if (Game.getProvince(Game.getExtraProvinceInViewID(i)).iProvinceIncreaseGrowthRateSize > 0) {
                            oSB.setColor(CFG.getLighter(new Color(ProvinceDraw.progressBar2.r, ProvinceDraw.progressBar2.g, ProvinceDraw.progressBar2.b, fProvinceAlpha), 7 * (Game.getProvince(Game.getExtraProvinceInViewID(i)).iProvinceIncreaseGrowthRateSize - 1), fProvinceAlpha));
                        }
                        else {
                            oSB.setColor(new Color(Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getR(), Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getG(), Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getB(), fProvinceAlpha));
                        }
                    }
                    else {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlpha));
                    }
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvince(oSB);
                }
                ProvinceDraw.drawOccupiedProvinces(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        if (Game.getProvince(Game.iHoveredProvinceID).getCivID() == Game.player.iCivID) {
                            if (Game.getCiv(Game.player.iCivID).fGold >= Game.getIncreaseGrowthRateCost(Game.iHoveredProvinceID)) {
                                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ClickToIncreasePopulationGrowthRate"), Colors.HOVER_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.populationGrowth, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                Game.setCursorPopulationGrowth();
                            }
                            else {
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("InsufficientGold") + ": ", Colors.HOVER_NEGATIVE));
                                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getIncreaseGrowthRateCost(Game.iHoveredProvinceID), 100), Colors.COLOR_TEXT_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                Game.setCursorX();
                            }
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("GrowthRate") + ": "));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getProvince(Game.iHoveredProvinceID).getGrowthRateWithBonuses(), 10) + "%", Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.populationGrowth, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            if (CFG.isDesktop()) {
                                nData.add(new MenuElement_HoverElement_Type_Line());
                                nElements.add(new MenuElement_HoverElement(nData, false));
                                nData.clear();
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ShiftClickToIncreaseGrowthRateXTimes", 5), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                                nElements.add(new MenuElement_HoverElement(nData, false));
                                nData.clear();
                            }
                        }
                        else {
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            Game.setCursorX();
                        }
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                    Game.setCursorX();
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = null;
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                Game.setCursorDefault();
            }
        });
        this.MODE_INCREASE_MANPOWER = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                final float fProvinceAlpha = ProvinceDraw.getProvinceAlpha() * MapModeManager.fAlphaAnimation;
                ProvinceDraw.drawWastelandProvinces(oSB);
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).isOccupied()) {
                            oSB.setColor(new Color(MapModeManager.PROVINCE_RED.r, MapModeManager.PROVINCE_RED.g, MapModeManager.PROVINCE_RED.b, fProvinceAlpha));
                        }
                        else if (Game.getProvince(Game.getProvinceInViewID(i)).iProvinceIncreaseManpowerSize == 1) {
                            oSB.setColor(CFG.getColorStep(ProvinceDraw.progressBar2, Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getColor(1.0f), (int)(100.0f - Game.getProvince(Game.getProvinceInViewID(i)).provinceIncreaseManpowerDaysLeft.get(0).daysLeft / (float)Game.getProvince(Game.getProvinceInViewID(i)).provinceIncreaseManpowerDaysLeft.get(0).investTime * 100.0f), 100, fProvinceAlpha));
                        }
                        else if (Game.getProvince(Game.getProvinceInViewID(i)).iProvinceIncreaseManpowerSize > 0) {
                            oSB.setColor(CFG.getLighter(new Color(ProvinceDraw.progressBar2.r, ProvinceDraw.progressBar2.g, ProvinceDraw.progressBar2.b, fProvinceAlpha), 7 * (Game.getProvince(Game.getProvinceInViewID(i)).iProvinceIncreaseManpowerSize - 1), fProvinceAlpha));
                        }
                        else {
                            oSB.setColor(new Color(Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getR(), Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getG(), Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getB(), fProvinceAlpha));
                        }
                    }
                    else {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlpha));
                    }
                    Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).isOccupied()) {
                            oSB.setColor(new Color(MapModeManager.PROVINCE_RED.r, MapModeManager.PROVINCE_RED.g, MapModeManager.PROVINCE_RED.b, fProvinceAlpha));
                        }
                        else if (Game.getProvince(Game.getExtraProvinceInViewID(i)).iProvinceIncreaseManpowerSize == 1) {
                            oSB.setColor(CFG.getColorStep(ProvinceDraw.progressBar2, Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getColor(1.0f), (int)(100.0f - Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceIncreaseManpowerDaysLeft.get(0).daysLeft / (float)Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceIncreaseManpowerDaysLeft.get(0).investTime * 100.0f), 100, fProvinceAlpha));
                        }
                        else if (Game.getProvince(Game.getExtraProvinceInViewID(i)).iProvinceIncreaseManpowerSize > 0) {
                            oSB.setColor(CFG.getLighter(new Color(ProvinceDraw.progressBar2.r, ProvinceDraw.progressBar2.g, ProvinceDraw.progressBar2.b, fProvinceAlpha), 7 * (Game.getProvince(Game.getExtraProvinceInViewID(i)).iProvinceIncreaseManpowerSize - 1), fProvinceAlpha));
                        }
                        else {
                            oSB.setColor(new Color(Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getR(), Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getG(), Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getB(), fProvinceAlpha));
                        }
                    }
                    else {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlpha));
                    }
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                }
                ProvinceDraw.drawOccupiedProvinces(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        if (Game.getProvince(Game.iHoveredProvinceID).getCivID() == Game.player.iCivID) {
                            if (Game.getCiv(Game.player.iCivID).fGold < Game.getIncreaseManpowerCost(Game.iHoveredProvinceID)) {
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("InsufficientGold") + ": ", Colors.HOVER_NEGATIVE));
                                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getIncreaseManpowerCost(Game.iHoveredProvinceID), 100), Colors.COLOR_TEXT_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            else if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getIncreaseManpowerCostLegacy(Game.iHoveredProvinceID)) {
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("InsufficientLegacy") + ": ", Colors.HOVER_NEGATIVE));
                                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getIncreaseManpowerCostLegacy(Game.iHoveredProvinceID), 100), Colors.COLOR_TEXT_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            else {
                                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ClickToIncreaseManpower"), Colors.HOVER_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LocalManpowerLevel") + ": "));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getProvince(Game.iHoveredProvinceID).getManpower(), 100), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            if (CFG.isDesktop()) {
                                nData.add(new MenuElement_HoverElement_Type_Line());
                                nElements.add(new MenuElement_HoverElement(nData, false));
                                nData.clear();
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ShiftClickToIncreaseManpowerXTimes", 5), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                                nElements.add(new MenuElement_HoverElement(nData, false));
                                nData.clear();
                            }
                            Game.setCursorRecruit();
                        }
                        else {
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            Game.setCursorX();
                        }
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                    Game.setCursorX();
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = null;
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                Game.setCursorDefault();
            }
        });
        this.MODE_MOVE_CAPITAL = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                final float fProvinceAlpha = ProvinceDraw.getProvinceAlpha() * MapModeManager.fAlphaAnimation;
                ProvinceDraw.drawWastelandProvinces(oSB);
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).isOccupied() || Game.getProvinceInViewID(i) == Game.getCiv(Game.player.iCivID).getCapitalProvinceID()) {
                            oSB.setColor(new Color(MapModeManager.PROVINCE_RED.r, MapModeManager.PROVINCE_RED.g, MapModeManager.PROVINCE_RED.b, fProvinceAlpha));
                        }
                        else {
                            oSB.setColor(new Color(Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getR(), Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getG(), Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getB(), fProvinceAlpha));
                        }
                    }
                    else {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlpha));
                    }
                    Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).isOccupied() || Game.getExtraProvinceInViewID(i) == Game.getCiv(Game.player.iCivID).getCapitalProvinceID()) {
                            oSB.setColor(new Color(MapModeManager.PROVINCE_RED.r, MapModeManager.PROVINCE_RED.g, MapModeManager.PROVINCE_RED.b, fProvinceAlpha));
                        }
                        else {
                            oSB.setColor(new Color(Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getR(), Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getG(), Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getB(), fProvinceAlpha));
                        }
                    }
                    else {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlpha));
                    }
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                }
                ProvinceDraw.drawOccupiedProvinces(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        if (Game.getProvince(Game.iHoveredProvinceID).getCivID() == Game.player.iCivID) {
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("MoveCapitalTo") + ": "));
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), (Game.iHoveredProvinceID == Game.getCiv(Game.player.iCivID).getCapitalProvinceID()) ? Colors.COLOR_TEXT_MODIFIER_NEGATIVE : Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.capital, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(GameValues.capital.MOVE_CAPITAL_COST, 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            Game.setCursorPlus();
                        }
                        else {
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            Game.setCursorX();
                        }
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                    Game.setCursorX();
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = null;
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                Game.setCursorDefault();
            }
        });
        this.MODE_CONVERT_RELIGION = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                final float fProvinceAlpha = ProvinceDraw.getProvinceAlpha() * MapModeManager.fAlphaAnimation;
                ProvinceDraw.drawWastelandProvinces(oSB);
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).religionConversion != null) {
                            oSB.setColor(CFG.getColorStep(ProvinceDraw.progressBar2, MapModeManager.PROVINCE_GREEN, (int)(100.0f - Game.getProvince(Game.getProvinceInViewID(i)).religionConversion.daysLeft / (float)Game.getProvince(Game.getProvinceInViewID(i)).religionConversion.investTime * 100.0f), 100, fProvinceAlpha));
                        }
                        else if (Game.getProvince(Game.getProvinceInViewID(i)).getReligion() != Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getReligionID()) {
                            oSB.setColor(new Color(MapModeManager.PROVINCE_RED.r, MapModeManager.PROVINCE_RED.g, MapModeManager.PROVINCE_RED.b, fProvinceAlpha));
                        }
                        else {
                            oSB.setColor(new Color(MapModeManager.PROVINCE_GREEN.r, MapModeManager.PROVINCE_GREEN.g, MapModeManager.PROVINCE_GREEN.b, fProvinceAlpha));
                        }
                    }
                    else {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlpha));
                    }
                    Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).religionConversion != null) {
                            oSB.setColor(CFG.getColorStep(ProvinceDraw.progressBar2, MapModeManager.PROVINCE_GREEN, (int)(100.0f - Game.getProvince(Game.getExtraProvinceInViewID(i)).religionConversion.daysLeft / (float)Game.getProvince(Game.getExtraProvinceInViewID(i)).religionConversion.investTime * 100.0f), 100, fProvinceAlpha));
                        }
                        else if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getReligion() != Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getReligionID()) {
                            oSB.setColor(new Color(MapModeManager.PROVINCE_RED.r, MapModeManager.PROVINCE_RED.g, MapModeManager.PROVINCE_RED.b, fProvinceAlpha));
                        }
                        else {
                            oSB.setColor(new Color(MapModeManager.PROVINCE_GREEN.r, MapModeManager.PROVINCE_GREEN.g, MapModeManager.PROVINCE_GREEN.b, fProvinceAlpha));
                        }
                    }
                    else {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlpha));
                    }
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                }
                oSB.setShader(Renderer.shaderAlpha_Pattern);
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == Game.player.iCivID && Game.getProvince(Game.getProvinceInViewID(i)).getReligion() != Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getReligionID()) {
                        Game.getProvince(Game.getProvinceInViewID(i)).drawOccupiedProvince_Religion(oSB);
                    }
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == Game.player.iCivID && Game.getProvince(Game.getExtraProvinceInViewID(i)).getReligion() != Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getReligionID()) {
                        Game.getProvince(Game.getExtraProvinceInViewID(i)).drawOccupiedProvince_Religion(oSB);
                    }
                }
                oSB.setShader(Renderer.shaderDefault);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        if (Game.getProvince(Game.iHoveredProvinceID).getCivID() == Game.player.iCivID) {
                            if (Game.getProvince(Game.iHoveredProvinceID).religionConversion != null) {
                                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("ReligionConversionProgress") + ": ", CFG.FONT_BOLD));
                                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + CFG.getPrecision2((Game.getProvince(Game.iHoveredProvinceID).religionConversion.investTime - Game.getProvince(Game.iHoveredProvinceID).religionConversion.daysLeft) / (float)Game.getProvince(Game.iHoveredProvinceID).religionConversion.investTime * 100.0f, 10) + "%", CFG.FONT_BOLD, Colors.HOVER_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_ReligionTitle(Game.getCiv(Game.player.iCivID).getReligionID(), CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                nData.add(new MenuElement_HoverElement_Type_Line());
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                Game.setCursorReligion();
                            }
                            else if (Game.getProvince(Game.iHoveredProvinceID).getReligion() != Game.getCiv(Game.player.iCivID).getReligionID()) {
                                if (Game.getCiv(Game.player.iCivID).fGold >= Game.religionManager.getReligionConversionCost(Game.iHoveredProvinceID)) {
                                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ConvertReligion"), Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Religion") + ": ", CFG.FONT_REGULAR_SMALL));
                                    nData.add(new MenuElement_HoverElement_Type_Text(Game.religionManager.getReligion(Game.getCiv(Game.player.iCivID).getReligionID()).Name, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                                    nData.add(new MenuElement_HoverElement_Type_Religion(Game.getCiv(Game.player.iCivID).getReligionID(), CFG.PADDING, 0));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
                                    nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2((float)Game.religionManager.getReligionConversionCost(Game.iHoveredProvinceID), 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                                    nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ReligionConversionTime") + ": ", CFG.FONT_REGULAR_SMALL));
                                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("XDays", Game.religionManager.getReligionConversionTime(Game.iHoveredProvinceID)), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                                    nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, 0));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    Game.setCursorReligion();
                                }
                                else {
                                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("InsufficientGold") + ": ", Colors.HOVER_NEGATIVE));
                                    nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2((float)Game.religionManager.getReligionConversionCost(Game.iHoveredProvinceID), 100), Colors.COLOR_TEXT_GOLD));
                                    nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    Game.setCursorX();
                                }
                                nData.add(new MenuElement_HoverElement_Type_Line());
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Religion") + ": "));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + Game.religionManager.getReligion(Game.getProvince(Game.iHoveredProvinceID).getReligion()).Name, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Religion(Game.getProvince(Game.iHoveredProvinceID).getReligion(), CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        else {
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            Game.setCursorX();
                        }
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                    Game.setCursorX();
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = null;
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                Game.setCursorDefault();
            }
        });
        this.MODE_CORE = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                final float fProvinceAlpha = ProvinceDraw.getProvinceAlpha() * MapModeManager.fAlphaAnimation;
                ProvinceDraw.drawWastelandProvinces(oSB);
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).coreCreation != null) {
                            oSB.setColor(CFG.getColorStep(ProvinceDraw.progressBar2, MapModeManager.PROVINCE_GREEN, (int)(100.0f - Game.getProvince(Game.getProvinceInViewID(i)).coreCreation.daysLeft / (float)Game.getProvince(Game.getProvinceInViewID(i)).coreCreation.investTime * 100.0f), 100, fProvinceAlpha));
                        }
                        else if (!Game.getProvince(Game.getProvinceInViewID(i)).haveACore(Game.player.iCivID)) {
                            oSB.setColor(new Color(MapModeManager.PROVINCE_RED.r, MapModeManager.PROVINCE_RED.g, MapModeManager.PROVINCE_RED.b, fProvinceAlpha));
                        }
                        else {
                            oSB.setColor(new Color(MapModeManager.PROVINCE_GREEN.r, MapModeManager.PROVINCE_GREEN.g, MapModeManager.PROVINCE_GREEN.b, fProvinceAlpha));
                        }
                    }
                    else {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlpha));
                    }
                    Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).coreCreation != null) {
                            oSB.setColor(CFG.getColorStep(ProvinceDraw.progressBar2, MapModeManager.PROVINCE_GREEN, (int)(100.0f - Game.getProvince(Game.getExtraProvinceInViewID(i)).coreCreation.daysLeft / (float)Game.getProvince(Game.getExtraProvinceInViewID(i)).coreCreation.investTime * 100.0f), 100, fProvinceAlpha));
                        }
                        else if (!Game.getProvince(Game.getExtraProvinceInViewID(i)).haveACore(Game.player.iCivID)) {
                            oSB.setColor(new Color(MapModeManager.PROVINCE_RED.r, MapModeManager.PROVINCE_RED.g, MapModeManager.PROVINCE_RED.b, fProvinceAlpha));
                        }
                        else {
                            oSB.setColor(new Color(MapModeManager.PROVINCE_GREEN.r, MapModeManager.PROVINCE_GREEN.g, MapModeManager.PROVINCE_GREEN.b, fProvinceAlpha));
                        }
                    }
                    else {
                        oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlpha));
                    }
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                }
                ProvinceDraw.drawOccupiedProvinces(oSB);
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        if (Game.getProvince(Game.iHoveredProvinceID).getCivID() == Game.player.iCivID) {
                            if (Game.getProvince(Game.iHoveredProvinceID).coreCreation != null) {
                                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("CoreConstruction") + ": ", CFG.FONT_BOLD));
                                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + CFG.getPrecision2((Game.getProvince(Game.iHoveredProvinceID).coreCreation.investTime - Game.getProvince(Game.iHoveredProvinceID).coreCreation.daysLeft) / (float)Game.getProvince(Game.iHoveredProvinceID).coreCreation.investTime * 100.0f, 10) + "%", CFG.FONT_BOLD, Colors.HOVER_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.core, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                nData.add(new MenuElement_HoverElement_Type_Line());
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                Game.setCursorCore();
                            }
                            else if (!Game.getProvince(Game.iHoveredProvinceID).haveACore(Game.player.iCivID)) {
                                if (Game.getCiv(Game.player.iCivID).fGold >= Game.getCoreCreationCost(Game.iHoveredProvinceID)) {
                                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("AddCore"), Colors.HOVER_GOLD));
                                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.core, CFG.PADDING, 0));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
                                    nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getCoreCreationCost(Game.iHoveredProvinceID), 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                                    nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("CoreConstruction") + ": ", CFG.FONT_REGULAR_SMALL));
                                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("XDays", Game.getCoreCreationTime(Game.iHoveredProvinceID)), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                                    nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, 0));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    Game.setCursorCore();
                                }
                                else {
                                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("InsufficientGold") + ": ", Colors.HOVER_NEGATIVE));
                                    nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getCoreCreationCost(Game.iHoveredProvinceID), 100), Colors.COLOR_TEXT_GOLD));
                                    nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    Game.setCursorX();
                                }
                                nData.add(new MenuElement_HoverElement_Type_Line());
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        else {
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            Game.setCursorX();
                        }
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                    Game.setCursorX();
                }
                catch (final Exception ex) {}
                Game.provinceHover_Informations = null;
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                Game.setCursorDefault();
            }
        });
        this.MODE_BUILDING = this.addMapMode(new MapMode(new ProvinceDraw.DrawProvinces() {
            @Override
            public void draw(final SpriteBatch oSB) {
                try {
                    final float fProvinceAlpha = ProvinceDraw.getProvinceAlpha() * MapModeManager.fAlphaAnimation;
                    ProvinceDraw.drawWastelandProvinces(oSB);
                    for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                            if (Game.getProvince(Game.getProvinceInViewID(i)).buildingBuilt(InGame_Court_Buildings2.oBuildingID.getPosX(), InGame_Court_Buildings2.oBuildingID.getPosY())) {
                                oSB.setColor(new Color(Colors.HOVER_GOLD.r, Colors.HOVER_GOLD.g, Colors.HOVER_GOLD.b, fProvinceAlpha));
                            }
                            else if (Game.getProvince(Game.getProvinceInViewID(i)).isUnderConstruction(InGame_Court_Buildings2.oBuildingID.getPosX(), InGame_Court_Buildings2.oBuildingID.getPosY())) {
                                oSB.setColor(new Color(MapModeManager.PROVINCE_BLUE.r, MapModeManager.PROVINCE_BLUE.g, MapModeManager.PROVINCE_BLUE.b, fProvinceAlpha));
                            }
                            else if (Game.getProvince(Game.getProvinceInViewID(i)).getBuildingsLimit_FreeSlots() <= 0) {
                                oSB.setColor(new Color(MapModeManager.PROVINCE_RED.r, MapModeManager.PROVINCE_RED.g, MapModeManager.PROVINCE_RED.b, fProvinceAlpha));
                            }
                            else if (BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).SeaAccessRequired && Game.getProvince(Game.getProvinceInViewID(i)).getLevelOfPort() < 0) {
                                oSB.setColor(new Color(MapModeManager.PROVINCE_RED.r, MapModeManager.PROVINCE_RED.g, MapModeManager.PROVINCE_RED.b, fProvinceAlpha));
                            }
                            else if (BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).RequiredResource >= 0 && BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).RequiredResource != Game.getProvince(Game.getProvinceInViewID(i)).getResourceID()) {
                                oSB.setColor(new Color(MapModeManager.PROVINCE_RED.r, MapModeManager.PROVINCE_RED.g, MapModeManager.PROVINCE_RED.b, fProvinceAlpha));
                            }
                            else if (Game.getProvince(Game.getProvinceInViewID(i)).isOccupied() || Game.getCiv(Game.player.iCivID).fGold < Game.getBuildingConstructionCost(Game.player.iCivID, Game.getProvinceInViewID(i), InGame_Court_Buildings2.oBuildingID.getPosX(), InGame_Court_Buildings2.oBuildingID.getPosY())) {
                                oSB.setColor(new Color(MapModeManager.PROVINCE_RED.r, MapModeManager.PROVINCE_RED.g, MapModeManager.PROVINCE_RED.b, fProvinceAlpha));
                            }
                            else {
                                oSB.setColor(new Color(MapModeManager.PROVINCE_GREEN.r, MapModeManager.PROVINCE_GREEN.g, MapModeManager.PROVINCE_GREEN.b, fProvinceAlpha));
                            }
                        }
                        else {
                            oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlpha));
                        }
                        Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                    }
                    for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == Game.player.iCivID) {
                            if (Game.getProvince(Game.getExtraProvinceInViewID(i)).buildingBuilt(InGame_Court_Buildings2.oBuildingID.getPosX(), InGame_Court_Buildings2.oBuildingID.getPosY())) {
                                oSB.setColor(new Color(Colors.HOVER_GOLD.r, Colors.HOVER_GOLD.g, Colors.HOVER_GOLD.b, fProvinceAlpha));
                            }
                            else if (Game.getProvince(Game.getExtraProvinceInViewID(i)).isUnderConstruction(InGame_Court_Buildings2.oBuildingID.getPosX(), InGame_Court_Buildings2.oBuildingID.getPosY())) {
                                oSB.setColor(new Color(MapModeManager.PROVINCE_BLUE.r, MapModeManager.PROVINCE_BLUE.g, MapModeManager.PROVINCE_BLUE.b, fProvinceAlpha));
                            }
                            else if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getBuildingsLimit_FreeSlots() <= 0) {
                                oSB.setColor(new Color(MapModeManager.PROVINCE_RED.r, MapModeManager.PROVINCE_RED.g, MapModeManager.PROVINCE_RED.b, fProvinceAlpha));
                            }
                            else if (BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).SeaAccessRequired && Game.getProvince(Game.getExtraProvinceInViewID(i)).getLevelOfPort() < 0) {
                                oSB.setColor(new Color(MapModeManager.PROVINCE_RED.r, MapModeManager.PROVINCE_RED.g, MapModeManager.PROVINCE_RED.b, fProvinceAlpha));
                            }
                            else if (BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).RequiredResource >= 0 && BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).RequiredResource != Game.getProvince(Game.getExtraProvinceInViewID(i)).getResourceID()) {
                                oSB.setColor(new Color(MapModeManager.PROVINCE_RED.r, MapModeManager.PROVINCE_RED.g, MapModeManager.PROVINCE_RED.b, fProvinceAlpha));
                            }
                            else if (Game.getProvince(Game.getExtraProvinceInViewID(i)).isOccupied() || Game.getCiv(Game.player.iCivID).fGold < Game.getBuildingConstructionCost(Game.player.iCivID, Game.getExtraProvinceInViewID(i), InGame_Court_Buildings2.oBuildingID.getPosX(), InGame_Court_Buildings2.oBuildingID.getPosY())) {
                                oSB.setColor(new Color(MapModeManager.PROVINCE_RED.r, MapModeManager.PROVINCE_RED.g, MapModeManager.PROVINCE_RED.b, fProvinceAlpha));
                            }
                            else {
                                oSB.setColor(new Color(MapModeManager.PROVINCE_GREEN.r, MapModeManager.PROVINCE_GREEN.g, MapModeManager.PROVINCE_GREEN.b, fProvinceAlpha));
                            }
                        }
                        else {
                            oSB.setColor(new Color(MapModeManager.PROVINCE_GRAY.r, MapModeManager.PROVINCE_GRAY.g, MapModeManager.PROVINCE_GRAY.b, fProvinceAlpha));
                        }
                        Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvinceExtra(oSB);
                    }
                    ProvinceDraw.drawOccupiedProvinces(oSB);
                }
                catch (final Exception ex) {
                    MapModeManager.this.setActiveViewID(MapModeManager.this.MODE_DEFAULT);
                }
            }
        }, new ProvinceHover.ProvinceHoverBuild() {
            @Override
            public void build() {
                try {
                    if (Game.iHoveredProvinceID >= 0) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        if (Game.getProvince(Game.iHoveredProvinceID).getCivID() == Game.player.iCivID) {
                            if (BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).RequiredResource >= 0 && BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).RequiredResource != Game.getProvince(Game.iHoveredProvinceID).getResourceID()) {
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("RequiredResource") + ": "));
                                nData.add(new MenuElement_HoverElement_Type_Text(ResourcesManager.getResourceName(BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).RequiredResource), Colors.HOVER_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_Resource(BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).RequiredResource, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            else if (BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).SeaAccessRequired && Game.getProvince(Game.iHoveredProvinceID).getLevelOfPort() < 0) {
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("NoAccessToTheSea")));
                                nData.add(new MenuElement_HoverElement_Type_Image(Images.ship, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            else if (Game.getProvince(Game.iHoveredProvinceID).isUnderConstruction(InGame_Court_Buildings2.oBuildingID.getPosX(), InGame_Court_Buildings2.oBuildingID.getPosY())) {
                                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("UnderConstruction") + ": "));
                                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).Name[InGame_Court_Buildings2.oBuildingID.getPosY()], Colors.COLOR_TEXT_GOLD));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            else if (Game.getProvince(Game.iHoveredProvinceID).buildingBuilt(InGame_Court_Buildings2.oBuildingID.getPosX(), InGame_Court_Buildings2.oBuildingID.getPosY())) {
                                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("BuildingConstructed") + ": "));
                                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).Name[InGame_Court_Buildings2.oBuildingID.getPosY()], Colors.COLOR_TEXT_GOLD));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            else if (Game.getCiv(Game.player.iCivID).fGold >= Game.getBuildingConstructionCost(Game.player.iCivID, Game.iHoveredProvinceID, InGame_Court_Buildings2.oBuildingID.getPosX(), InGame_Court_Buildings2.oBuildingID.getPosY())) {
                                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Build") + ": "));
                                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).Name[InGame_Court_Buildings2.oBuildingID.getPosY()], Colors.COLOR_TEXT_GOLD));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            else if (Game.getProvince(Game.iHoveredProvinceID).getUsedBuildingsSlots() >= Game.getProvince(Game.iHoveredProvinceID).iBuildingsLimit) {
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BuildingSlots") + ": "));
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getUsedBuildingsSlots() + " / " + Game.getProvince(Game.iHoveredProvinceID).iBuildingsLimit, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                                nData.add(new MenuElement_HoverElement_Type_Image(Images.build, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("IncreaseEconomyInAProvinceToUnlockMoreBuildingSlots"), Colors.HOVER_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_ECONOMY, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            else {
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("InsufficientGold") + ": ", Colors.HOVER_NEGATIVE));
                                nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getBuildingConstructionCost(Game.player.iCivID, Game.iHoveredProvinceID, InGame_Court_Buildings2.oBuildingID.getPosX(), InGame_Court_Buildings2.oBuildingID.getPosY()), Colors.COLOR_TEXT_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            if (BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).ResearchPoints != null && BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).ResearchPoints[InGame_Court_Buildings2.oBuildingID.getPosY()] > 0.0f) {
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ResearchPerMonth") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                                nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).ResearchPoints[InGame_Court_Buildings2.oBuildingID.getPosY()] * Game.getProvince(Game.iHoveredProvinceID).getGrowthRateWithBonuses() / 100.0f, 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            if (BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).RequiredResource >= 0) {
                                nData.add(new MenuElement_HoverElement_Type_Line());
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("RequiredResource") + ": "));
                                nData.add(new MenuElement_HoverElement_Type_Text("" + ResourcesManager.lResources.get(BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).RequiredResource).Name, (BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).RequiredResource != Game.getProvince(Game.iHoveredProvinceID).getResourceID()) ? Colors.HOVER_NEGATIVE : Colors.HOVER_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_Resource(BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).RequiredResource, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            Game.setCursorBuild();
                        }
                        else {
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            Game.setCursorX();
                        }
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        return;
                    }
                    Game.setCursorX();
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
                Game.provinceHover_Informations = null;
            }
        }) {
            @Override
            public void enableViewAction() {
                super.enableViewAction();
                Game.setCursorBuild();
            }
            
            @Override
            public void disableViewAction() {
                super.disableViewAction();
                Game.setCursorDefault();
                InGame_Court_Buildings2.oBuildingID = null;
            }
        });
    }
    
    private final int addMapMode(final MapMode nMapMode) {
        this.lMapModes.add(nMapMode);
        return this.lMapModes.size() - 1;
    }
    
    public final void updateMaxPopulation() {
        this.POPULATION_MAX = 1;
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            if (!Game.getProvince(i).getSeaProvince() && Game.getProvince(i).getPopulationTotal() > this.POPULATION_MAX) {
                this.POPULATION_MAX = Game.getProvince(i).getPopulationTotal();
            }
        }
        this.POPULATION_MAX *= (int)0.95f;
    }
    
    public final void updateMaxEconomy() {
        this.ECONOMY_MAX = 1.0f;
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            if (!Game.getProvince(i).getSeaProvince() && Game.getProvince(i).getEconomyWithBonuses() > this.ECONOMY_MAX) {
                this.ECONOMY_MAX = Game.getProvince(i).getEconomyWithBonuses();
            }
        }
        this.ECONOMY_MAX *= 0.95f;
    }
    
    public final void updateMaxTax() {
        this.TAX_MAX = 1.0f;
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            if (!Game.getProvince(i).getSeaProvince() && Game.getProvince(i).getTaxEfficiencyWithBonuses() > this.TAX_MAX) {
                this.TAX_MAX = (float)(int)Game.getProvince(i).getTaxEfficiencyWithBonuses();
            }
        }
        this.TAX_MAX = (float)(int)(this.TAX_MAX * 0.95f);
    }
    
    public final void updateMaxProvinceIncome() {
        this.PROVINCE_INCOME_MAX = 0.15f;
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            if (!Game.getProvince(i).getSeaProvince() && Game.getProvince(i).getProvinceIncome() > this.PROVINCE_INCOME_MAX) {
                this.PROVINCE_INCOME_MAX = Game.getProvince(i).getProvinceIncome();
            }
        }
        this.PROVINCE_INCOME_MAX *= 0.95f;
    }
    
    public final void updateSpecialAllianceView() {
        for (int i = 0; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).warView_ParticipatesInWar = false;
            Game.getCiv(i).warView_IsAggressor = false;
        }
        try {
            Game.getCiv(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).iLeaderCivID).warView_ParticipatesInWar = true;
            Game.getCiv(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).iLeaderCivID).warView_IsAggressor = true;
            for (int i = Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).firstTier.size() - 1; i >= 0; --i) {
                Game.getCiv(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).firstTier.get(i)).warView_ParticipatesInWar = true;
            }
            for (int i = Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).secondTier.size() - 1; i >= 0; --i) {
                Game.getCiv(Game.alliancesSpecial.get(InGame_AllianceSpecial.allianceID).secondTier.get(i)).warView_ParticipatesInWar = true;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateCurrentWarsView() {
        Game.getCiv(0).warView_ParticipatesInWar = false;
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).warView_ParticipatesInWar = Game.getCiv(i).diplomacy.isAtWar();
        }
    }
    
    public final void updateWarView(final String nKey) {
        for (int i = 0; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).warView_ParticipatesInWar = false;
        }
        try {
            if (WarManager.lWars.containsKey(nKey)) {
                ProvinceDraw.buildBiggestCitiesLines_Province(Game.getCiv(WarManager.lWars.get(nKey).lAggressors.get(0).iCivID).getCapitalProvinceID(), Game.getCiv(WarManager.lWars.get(nKey).lDefenders.get(0).iCivID).getCapitalProvinceID());
                if (WarManager.lWars.get(nKey).isDefender(Game.player.iCivID)) {
                    for (int i = 1; i < Game.getCivsSize(); ++i) {
                        if (WarManager.lWars.get(nKey).isAggressor(i)) {
                            Game.getCiv(i).warView_ParticipatesInWar = true;
                            Game.getCiv(i).warView_IsAggressor = false;
                        }
                        else if (WarManager.lWars.get(nKey).isDefender(i)) {
                            Game.getCiv(i).warView_ParticipatesInWar = true;
                            Game.getCiv(i).warView_IsAggressor = true;
                        }
                    }
                }
                else {
                    for (int i = 1; i < Game.getCivsSize(); ++i) {
                        if (WarManager.lWars.get(nKey).isAggressor(i)) {
                            Game.getCiv(i).warView_ParticipatesInWar = true;
                            Game.getCiv(i).warView_IsAggressor = true;
                        }
                        else if (WarManager.lWars.get(nKey).isDefender(i)) {
                            Game.getCiv(i).warView_ParticipatesInWar = true;
                            Game.getCiv(i).warView_IsAggressor = false;
                        }
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updatePeaceView(final String nKey) {
        for (int i = 0; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).warView_ParticipatesInWar = false;
        }
        try {
            if (WarManager.lWars.containsKey(nKey)) {
                if (WarManager.lWars.get(nKey).isDefender(Game.player.iCivID)) {
                    for (int i = 1; i < Game.getCivsSize(); ++i) {
                        if (WarManager.lWars.get(nKey).isAggressor(i)) {
                            Game.getCiv(i).warView_ParticipatesInWar = true;
                            Game.getCiv(i).warView_IsAggressor = (WarManager.lWars.get(nKey).lAggressors.get(0).iCivID != i);
                        }
                        else if (WarManager.lWars.get(nKey).isDefender(i)) {
                            Game.getCiv(i).warView_ParticipatesInWar = true;
                            Game.getCiv(i).warView_IsAggressor = true;
                        }
                    }
                }
                else {
                    for (int i = 1; i < Game.getCivsSize(); ++i) {
                        if (WarManager.lWars.get(nKey).isAggressor(i)) {
                            Game.getCiv(i).warView_ParticipatesInWar = true;
                            Game.getCiv(i).warView_IsAggressor = true;
                        }
                        else if (WarManager.lWars.get(nKey).isDefender(i)) {
                            Game.getCiv(i).warView_ParticipatesInWar = true;
                            Game.getCiv(i).warView_IsAggressor = (WarManager.lWars.get(nKey).lDefenders.get(0).iCivID != i);
                        }
                    }
                }
                if (GameValues.peace.PEACE_TAKE_PROVINCES_FROM_ALLIES) {
                    for (int i = 0; i < Game.getProvincesSize(); ++i) {
                        if (Game.getProvince(i).getCivID() > 0 && Game.getCiv(Game.getProvince(i).getCivID()).warView_ParticipatesInWar && !Game.getCiv(Game.getProvince(i).getCivID()).warView_IsAggressor) {
                            Game.getProvince(i).peaceTreatyIsToTake = true;
                        }
                    }
                }
                else {
                    final int aggressor = WarManager.lWars.get(nKey).lAggressors.get(0).iCivID;
                    final int defender = WarManager.lWars.get(nKey).lDefenders.get(0).iCivID;
                    for (int j = 0; j < Game.getProvincesSize(); ++j) {
                        if (Game.getProvince(j).getCivID() > 0 && Game.getCiv(Game.getProvince(j).getCivID()).warView_ParticipatesInWar && !Game.getCiv(Game.getProvince(j).getCivID()).warView_IsAggressor && (Game.getProvince(j).getCivID() == aggressor || Game.getProvince(j).getCivID() == defender)) {
                            Game.getProvince(j).peaceTreatyIsToTake = true;
                        }
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void setActiveViewID(final int iID) {
        try {
            if (this.iActiveMapModeID == 0 && iID == 0) {
                return;
            }
            if (this.iActiveMapModeID == iID) {
                final int tempActive = this.iActiveMapModeID;
                this.iActiveMapModeID = 0;
                this.lMapModes.get(tempActive).disableViewAction();
            }
            else {
                final int tempActive = this.iActiveMapModeID;
                this.iActiveMapModeID = iID;
                MapModeManager.lTime = CFG.currentTimeMillis;
                this.lMapModes.get(tempActive).disableViewAction();
                this.lMapModes.get(iID).enableViewAction();
            }
            InGame.updateDrawOver();
            ProvinceDraw.updateDrawExtraDetails();
            ProvinceTouchExtraAction.updateExtraAction();
        }
        catch (final Exception ex) {
            this.iActiveMapModeID = 0;
        }
        this.updateViews();
    }
    
    public final void disableAllViews() {
        if (this.iActiveMapModeID != 0) {
            final int tempActive = this.iActiveMapModeID;
            this.iActiveMapModeID = 0;
            this.lMapModes.get(tempActive).disableViewAction();
        }
        this.updateViews();
    }
    
    public final void updateViews() {
        ProvinceDraw.updateDrawProvinces();
        ProvinceHover.updateProvinceHoverBuild();
    }
    
    public static final void updateDiplomacyProvinceColor(final boolean updateActive) {
        if (Game.mapScale.getCurrentScale() != MapModeManager.diplomacyScale || MapModeManager.diplomacyColorsPosX != Game.mapCoords.getPosX() || MapModeManager.diplomacyColorsPosY != Game.mapCoords.getPosY() || MapModeManager.diplomacyActiveCivID != InGame_Civ.iActiveCivID) {
            MapModeManager.diplomacyColorsPosX = Game.mapCoords.getPosX();
            MapModeManager.diplomacyColorsPosY = Game.mapCoords.getPosY();
            MapModeManager.diplomacyScale = Game.mapScale.getCurrentScale();
            MapModeManager.diplomacyActiveCivID = InGame_Civ.iActiveCivID;
            for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() > 0) {
                    if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == MapModeManager.diplomacyActiveCivID) {
                        if (updateActive) {
                            Game.getProvince(Game.getProvinceInViewID(i)).provinceColor = Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getColor(1.0f);
                        }
                    }
                    else if (DiplomacyManager.isAtWar(MapModeManager.diplomacyActiveCivID, Game.getProvince(Game.getProvinceInViewID(i)).getCivID())) {
                        Game.getProvince(Game.getProvinceInViewID(i)).provinceColor = ProvinceDraw.PROVINCE_DIPLOMACY_AT_WAR;
                    }
                    else if (Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).getPuppetOfCivID() == MapModeManager.diplomacyActiveCivID) {
                        Game.getProvince(Game.getProvinceInViewID(i)).provinceColor = ProvinceDraw.PROVINCE_DIPLOMACY_VASSAL;
                    }
                    else if (Game.getCiv(MapModeManager.diplomacyActiveCivID).getPuppetOfCivID() == Game.getProvince(Game.getProvinceInViewID(i)).getCivID()) {
                        Game.getProvince(Game.getProvinceInViewID(i)).provinceColor = ProvinceDraw.PROVINCE_DIPLOMACY_VASSAL;
                    }
                    else if (Game.getCiv(MapModeManager.diplomacyActiveCivID).diplomacy.alliance.containsKey(Game.getProvince(Game.getProvinceInViewID(i)).getCivID())) {
                        Game.getProvince(Game.getProvinceInViewID(i)).provinceColor = ProvinceDraw.PROVINCE_DIPLOMACY_ALLIANCE;
                    }
                    else if (Game.getCiv(MapModeManager.diplomacyActiveCivID).diplomacy.defensivePact.containsKey(Game.getProvince(Game.getProvinceInViewID(i)).getCivID())) {
                        Game.getProvince(Game.getProvinceInViewID(i)).provinceColor = ProvinceDraw.PROVINCE_DIPLOMACY_DEFENSIVE_PACT;
                    }
                    else if (Game.getCiv(MapModeManager.diplomacyActiveCivID).diplomacy.truce.containsKey(Game.getProvince(Game.getProvinceInViewID(i)).getCivID())) {
                        Game.getProvince(Game.getProvinceInViewID(i)).provinceColor = ProvinceDraw.PROVINCE_DIPLOMACY_TRUCE;
                    }
                    else if (Game.getCiv(MapModeManager.diplomacyActiveCivID).diplomacy.guarantee.containsKey(Game.getProvince(Game.getProvinceInViewID(i)).getCivID())) {
                        Game.getProvince(Game.getProvinceInViewID(i)).provinceColor = ProvinceDraw.PROVINCE_DIPLOMACY_INDEPENDENCE;
                    }
                    else if (Game.getCiv(MapModeManager.diplomacyActiveCivID).diplomacy.guaranteeByCivID.containsKey(Game.getProvince(Game.getProvinceInViewID(i)).getCivID())) {
                        Game.getProvince(Game.getProvinceInViewID(i)).provinceColor = ProvinceDraw.PROVINCE_DIPLOMACY_INDEPENDENCE2;
                    }
                    else if (Game.getCiv(MapModeManager.diplomacyActiveCivID).diplomacy.nonAggressionPact.containsKey(Game.getProvince(Game.getProvinceInViewID(i)).getCivID())) {
                        Game.getProvince(Game.getProvinceInViewID(i)).provinceColor = ProvinceDraw.PROVINCE_DIPLOMACY_PACT;
                    }
                    else if (Game.getCiv(MapModeManager.diplomacyActiveCivID).diplomacy.militaryAccess.containsKey(Game.getProvince(Game.getProvinceInViewID(i)).getCivID())) {
                        Game.getProvince(Game.getProvinceInViewID(i)).provinceColor = ProvinceDraw.PROVINCE_DIPLOMACY_MILITARY_ACCESS;
                    }
                    else if (Game.getCiv(Game.getProvince(Game.getProvinceInViewID(i)).getCivID()).diplomacy.militaryAccess.containsKey(MapModeManager.diplomacyActiveCivID)) {
                        Game.getProvince(Game.getProvinceInViewID(i)).provinceColor = ProvinceDraw.PROVINCE_DIPLOMACY_MILITARY_ACCESS;
                    }
                    else {
                        final int tempRelation = (int)Game.getCiv(MapModeManager.diplomacyActiveCivID).diplomacy.getRelation(Game.getProvince(Game.getProvinceInViewID(i)).getCivID());
                        if (tempRelation == 0) {
                            Game.getProvince(Game.getProvinceInViewID(i)).provinceColor = ProvinceDraw.PROVINCE_DIPLOMACY_NEUTRAL_GREEN;
                            Game.getProvince(Game.getProvinceInViewID(i)).provinceColor.a = 0.33f;
                        }
                        else if (tempRelation > 0) {
                            Game.getProvince(Game.getProvinceInViewID(i)).provinceColor = CFG.getColorStep(ProvinceDraw.PROVINCE_DIPLOMACY_GREEN, ProvinceDraw.PROVINCE_DIPLOMACY_GREEN2, -tempRelation, (int)(-GameValues.diplomacy.DIPLOMACY_RELATIONS_MAX), 1.0f);
                        }
                        else {
                            Game.getProvince(Game.getProvinceInViewID(i)).provinceColor = CFG.getColorStep(ProvinceDraw.PROVINCE_DIPLOMACY_RED, ProvinceDraw.PROVINCE_DIPLOMACY_RED2, -tempRelation, (int)(-GameValues.diplomacy.DIPLOMACY_RELATIONS_MIN), 1.0f);
                        }
                    }
                }
            }
            for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() > 0) {
                    if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == MapModeManager.diplomacyActiveCivID) {
                        Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor = Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getColor(1.0f);
                    }
                    else if (DiplomacyManager.isAtWar(MapModeManager.diplomacyActiveCivID, Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID())) {
                        Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor = ProvinceDraw.PROVINCE_DIPLOMACY_AT_WAR;
                    }
                    else if (Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).getPuppetOfCivID() == MapModeManager.diplomacyActiveCivID) {
                        Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor = ProvinceDraw.PROVINCE_DIPLOMACY_VASSAL;
                    }
                    else if (Game.getCiv(MapModeManager.diplomacyActiveCivID).getPuppetOfCivID() == Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()) {
                        Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor = ProvinceDraw.PROVINCE_DIPLOMACY_VASSAL;
                    }
                    else if (Game.getCiv(MapModeManager.diplomacyActiveCivID).diplomacy.alliance.containsKey(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID())) {
                        Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor = ProvinceDraw.PROVINCE_DIPLOMACY_ALLIANCE;
                    }
                    else if (Game.getCiv(MapModeManager.diplomacyActiveCivID).diplomacy.defensivePact.containsKey(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID())) {
                        Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor = ProvinceDraw.PROVINCE_DIPLOMACY_DEFENSIVE_PACT;
                    }
                    else if (Game.getCiv(MapModeManager.diplomacyActiveCivID).diplomacy.truce.containsKey(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID())) {
                        Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor = ProvinceDraw.PROVINCE_DIPLOMACY_TRUCE;
                    }
                    else if (Game.getCiv(MapModeManager.diplomacyActiveCivID).diplomacy.guarantee.containsKey(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID())) {
                        Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor = ProvinceDraw.PROVINCE_DIPLOMACY_INDEPENDENCE;
                    }
                    else if (Game.getCiv(MapModeManager.diplomacyActiveCivID).diplomacy.guaranteeByCivID.containsKey(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID())) {
                        Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor = ProvinceDraw.PROVINCE_DIPLOMACY_INDEPENDENCE2;
                    }
                    else if (Game.getCiv(MapModeManager.diplomacyActiveCivID).diplomacy.nonAggressionPact.containsKey(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID())) {
                        Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor = ProvinceDraw.PROVINCE_DIPLOMACY_PACT;
                    }
                    else if (Game.getCiv(MapModeManager.diplomacyActiveCivID).diplomacy.militaryAccess.containsKey(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID())) {
                        Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor = ProvinceDraw.PROVINCE_DIPLOMACY_MILITARY_ACCESS;
                    }
                    else if (Game.getCiv(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID()).diplomacy.militaryAccess.containsKey(MapModeManager.diplomacyActiveCivID)) {
                        Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor = ProvinceDraw.PROVINCE_DIPLOMACY_MILITARY_ACCESS;
                    }
                    else {
                        final int tempRelation = (int)Game.getCiv(MapModeManager.diplomacyActiveCivID).diplomacy.getRelation(Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID());
                        if (tempRelation == 0) {
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor = ProvinceDraw.PROVINCE_DIPLOMACY_NEUTRAL_GREEN;
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor.a = 0.33f;
                        }
                        else if (tempRelation > 0) {
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor = CFG.getColorStep(ProvinceDraw.PROVINCE_DIPLOMACY_GREEN, ProvinceDraw.PROVINCE_DIPLOMACY_GREEN2, -tempRelation, (int)(-GameValues.diplomacy.DIPLOMACY_RELATIONS_MAX), 1.0f);
                        }
                        else {
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor = CFG.getColorStep(ProvinceDraw.PROVINCE_DIPLOMACY_RED, ProvinceDraw.PROVINCE_DIPLOMACY_RED2, -tempRelation, (int)(-GameValues.diplomacy.DIPLOMACY_RELATIONS_MIN), 1.0f);
                        }
                    }
                }
            }
        }
    }
    
    public static void drawProvinces_DefaultHover(final SpriteBatch oSB) {
        try {
            final float fProvinceAlpha = ProvinceDraw.getProvinceAlpha();
            ProvinceDraw.drawWastelandProvinces(oSB);
            final int activeCivID = (Game.iActiveProvince >= 0) ? ((Game.getProvince(Game.iActiveProvince).getCivID() > 0) ? Game.getProvince(Game.iActiveProvince).getCivID() : Game.player.iCivID) : Game.player.iCivID;
            if (Game.mapScale.getCurrentScale() >= Game.DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE) {
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() != 0) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == activeCivID) {
                            Game.getProvince(Game.getProvinceInViewID(i)).provinceColor.a = fProvinceAlpha * MapModeManager.fAlphaAnimation;
                            oSB.setColor(Game.getProvince(Game.getProvinceInViewID(i)).provinceColor);
                            Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                        else {
                            Game.getProvince(Game.getProvinceInViewID(i)).fog_drawLandProvince.drawLandProvinceFog(oSB, fProvinceAlpha);
                        }
                    }
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() != 0) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == activeCivID) {
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor.a = fProvinceAlpha * MapModeManager.fAlphaAnimation;
                            oSB.setColor(Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor);
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                        else {
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).fog_drawLandProvince.drawLandProvinceFog(oSB, fProvinceAlpha);
                        }
                    }
                }
            }
            else {
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() != 0) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == activeCivID) {
                            Game.getProvince(Game.getProvinceInViewID(i)).provinceColor.a = fProvinceAlpha * MapModeManager.fAlphaAnimation;
                            oSB.setColor(Game.getProvince(Game.getProvinceInViewID(i)).provinceColor);
                            Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                        else {
                            Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB, fProvinceAlpha);
                        }
                    }
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() != 0) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == activeCivID) {
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor.a = fProvinceAlpha * MapModeManager.fAlphaAnimation;
                            oSB.setColor(Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor);
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                        else {
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvince(oSB, fProvinceAlpha);
                        }
                    }
                }
            }
            ProvinceDraw.drawOccupiedProvinces(oSB);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        }
    }
    
    public static void drawProvinces_DefaultHover_Player(final SpriteBatch oSB) {
        try {
            final float fProvinceAlpha = ProvinceDraw.getProvinceAlpha();
            ProvinceDraw.drawWastelandProvinces(oSB);
            final int activeCivID = Game.player.iCivID;
            if (Game.mapScale.getCurrentScale() >= Game.DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE) {
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() != 0) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == activeCivID) {
                            Game.getProvince(Game.getProvinceInViewID(i)).provinceColor.a = fProvinceAlpha * MapModeManager.fAlphaAnimation;
                            oSB.setColor(Game.getProvince(Game.getProvinceInViewID(i)).provinceColor);
                            Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                        else {
                            Game.getProvince(Game.getProvinceInViewID(i)).fog_drawLandProvince.drawLandProvinceFog(oSB, fProvinceAlpha);
                        }
                    }
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() != 0) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == activeCivID) {
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor.a = fProvinceAlpha * MapModeManager.fAlphaAnimation;
                            oSB.setColor(Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor);
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                        else {
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).fog_drawLandProvince.drawLandProvinceFog(oSB, fProvinceAlpha);
                        }
                    }
                }
            }
            else {
                for (int i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() != 0) {
                        if (Game.getProvince(Game.getProvinceInViewID(i)).getCivID() == activeCivID) {
                            Game.getProvince(Game.getProvinceInViewID(i)).provinceColor.a = fProvinceAlpha * MapModeManager.fAlphaAnimation;
                            oSB.setColor(Game.getProvince(Game.getProvinceInViewID(i)).provinceColor);
                            Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                        else {
                            Game.getProvince(Game.getProvinceInViewID(i)).drawLandProvince(oSB, fProvinceAlpha);
                        }
                    }
                }
                for (int i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                    if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() != 0) {
                        if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getCivID() == activeCivID) {
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor.a = fProvinceAlpha * MapModeManager.fAlphaAnimation;
                            oSB.setColor(Game.getProvince(Game.getExtraProvinceInViewID(i)).provinceColor);
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvince(oSB);
                        }
                        else {
                            Game.getProvince(Game.getExtraProvinceInViewID(i)).drawLandProvince(oSB, fProvinceAlpha);
                        }
                    }
                }
            }
            ProvinceDraw.drawOccupiedProvinces(oSB);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        }
    }
    
    public static MenuElement_Hover getProvinceHover_Default() {
        try {
            if (Game.iHoveredProvinceID >= 0) {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID(), CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                return new MenuElement_Hover(nElements);
            }
        }
        catch (final Exception ex) {}
        return null;
    }
    
    static {
        MapModeManager.PROVINCE_GREEN = new Color(0.27450982f, 0.50980395f, 0.23529412f, 1.0f);
        MapModeManager.PROVINCE_RED = new Color(0.70980394f, 0.17254902f, 0.1254902f, 1.0f);
        MapModeManager.PROVINCE_GRAY = new Color(0.8235294f, 0.8235294f, 0.78431374f, 1.0f);
        MapModeManager.PROVINCE_BLUE = new Color(0.13725491f, 0.37254903f, 0.5882353f, 1.0f);
        MapModeManager.PROVINCE_BLUE2 = new Color(0.0f, 0.23529412f, 0.92156863f, 1.0f);
        MapModeManager.PROVINCE_BLUE2_ALLY = new Color(0.15686275f, 0.3529412f, 0.9019608f, 1.0f);
        MapModeManager.PROVINCE_RED2 = new Color(0.84313726f, 0.0f, 0.0f, 1.0f);
        MapModeManager.PROVINCE_RED2_ALLY = new Color(0.78431374f, 0.19607843f, 0.19607843f, 1.0f);
        MapModeManager.lTime = 0L;
        MapModeManager.fAlphaAnimation = 1.0f;
        MapModeManager.diplomacyColorsPosX = -777777;
        MapModeManager.diplomacyColorsPosY = -777777;
        MapModeManager.diplomacyScale = -777.0f;
        MapModeManager.diplomacyActiveCivID = -777;
    }
}
