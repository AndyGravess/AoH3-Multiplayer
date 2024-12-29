// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.province;

import aoc.kingdoms.lukasz.map.map.MapMode;
import aoc.kingdoms.lukasz.map.map.Continents;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Terrain;
import aoc.kingdoms.lukasz.map.terrain.Terrain;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Religion;
import aoc.kingdoms.lukasz.map.civilization.CivilizationRanking;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonusResource;
import aoc.kingdoms.lukasz.map.ResourcesManager;
import aoc.kingdoms.lukasz.map.map.Ship.Ship2;
import aoc.kingdoms.lukasz.map.map.Ship.ShipManager;
import aoc.kingdoms.lukasz.map.map.Ship.ShipLine;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_BattleArmyPerc;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Game_Ages;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.map.army.ArmyRegiment;
import aoc.kingdoms.lukasz.map.army.ArmyManager;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title;
import java.util.List;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Flag;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;

public class ProvinceHover
{
    public static ProvinceHoverBuild provinceHoverBuild;
    
    public static final void updateProvinceHoverBattle() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("BattleOf", Game.getProvince(Game.hoveredBattle.iProvinceID).getProvinceName()), Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.battle, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        try {
            final int battleID = Game.battleManager.getBattleID(Game.hoveredBattle.iProvinceID, Game.hoveredBattle.key);
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Soldiers") + ":", CFG.FONT_REGULAR_SMALL));
            if (Game.battleManager.getBattle(battleID).attackingArmy.iCivID < 0) {
                nData.add(new MenuElement_HoverElement_Type_Image(Images.rebelsFlag, CFG.PADDING, CFG.PADDING));
            }
            else {
                nData.add(new MenuElement_HoverElement_Type_Flag(Game.battleManager.getBattle(battleID).attackingArmy.iCivID, CFG.PADDING, CFG.PADDING));
            }
            nData.add(new MenuElement_HoverElement_Type_Text(CFG.getNumberWithSpaces("" + Game.battleManager.getBattle(battleID).attackingArmy.numOfUnits) + " vs " + CFG.getNumberWithSpaces("" + Game.battleManager.getBattle(battleID).defendingArmy.numOfUnits), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
            if (Game.battleManager.getBattle(battleID).defendingArmy.iCivID < 0) {
                nData.add(new MenuElement_HoverElement_Type_Image(Images.rebelsFlag, CFG.PADDING, 0));
            }
            else {
                nData.add(new MenuElement_HoverElement_Type_Flag(Game.battleManager.getBattle(battleID).defendingArmy.iCivID, CFG.PADDING, 0));
            }
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
    }
    
    public static final void updateProvinceHoverSiege() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("SiegeOf", Game.getProvince(Game.hoveredSiegeID).getProvinceName()), Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        try {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("SiegeProgress") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(Game.getProvince(Game.hoveredSiegeID).getSiegeProgress() * 100.0f, 10) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.siege, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
    }
    
    public static final void updateProvinceHoverArmy() {
        try {
            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
            if (Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).civID < 0) {}
            nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).civID, (Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).armyGeneral == null) ? Game.lang.get("NoGeneral") : Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).armyGeneral.n));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            try {
                for (int i = 0; i < Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).iArmyRegimentSize; ++i) {
                    if (ArmyManager.lUnitsTypes.get(Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).lArmyRegiment.get(i).uID).Line < 2) {
                        int tUnits = Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).lArmyRegiment.get(i).num;
                        int numOfRegiments = 1;
                        for (int o = i + 1; o < Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).iArmyRegimentSize && Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).lArmyRegiment.get(i).uID == Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).lArmyRegiment.get(o).uID && Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).lArmyRegiment.get(i).aID == Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).lArmyRegiment.get(o).aID; ++i, ++numOfRegiments, tUnits += Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).lArmyRegiment.get(i).num, ++o) {}
                        final int checkCivID = Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).civID;
                        String textUnits = "";
                        float fPerc = -1.0f;
                        if (!Game.FOG_OF_WAR || !GameValues.fog.HIDE_MANPOWER || checkCivID == Game.player.iCivID || DiplomacyManager.isAlly(checkCivID, Game.player.iCivID)) {
                            textUnits = "" + tUnits;
                            fPerc = tUnits / (float)(numOfRegiments * Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE);
                        }
                        else {
                            textUnits = GameValues.fog.TEXT_UNKNOWN_MANPOWER;
                        }
                        nData.add(new MenuElement_HoverElement_Type_BattleArmyPerc(textUnits, Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).lArmyRegiment.get(i).uID, Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).lArmyRegiment.get(i).aID, (i > 0) ? CFG.PADDING : 0, fPerc));
                    }
                }
                for (int i = 0; i < Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).iArmyRegimentSize; ++i) {
                    if (ArmyManager.lUnitsTypes.get(Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).lArmyRegiment.get(i).uID).Line >= 2) {
                        int tUnits = Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).lArmyRegiment.get(i).num;
                        int numOfRegiments = 1;
                        for (int o = i + 1; o < Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).iArmyRegimentSize && Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).lArmyRegiment.get(i).uID == Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).lArmyRegiment.get(o).uID && Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).lArmyRegiment.get(i).aID == Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).lArmyRegiment.get(o).aID; ++i, ++numOfRegiments, tUnits += Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).lArmyRegiment.get(i).num, ++o) {}
                        final int checkCivID = Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).civID;
                        String textUnits = "";
                        float fPerc = -1.0f;
                        if (!Game.FOG_OF_WAR || !GameValues.fog.HIDE_MANPOWER || checkCivID == Game.player.iCivID || DiplomacyManager.isAlly(checkCivID, Game.player.iCivID)) {
                            textUnits = "" + tUnits;
                            fPerc = tUnits / (float)(numOfRegiments * Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE);
                        }
                        else {
                            textUnits = GameValues.fog.TEXT_UNKNOWN_MANPOWER;
                        }
                        nData.add(new MenuElement_HoverElement_Type_BattleArmyPerc(textUnits, Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).lArmyRegiment.get(i).uID, Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).lArmyRegiment.get(i).aID, (i > 0) ? CFG.PADDING : 0, fPerc));
                    }
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
            if (nData.size() > 0) {
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            final int checkCivID2 = Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).civID;
            if (!Game.FOG_OF_WAR || !GameValues.fog.HIDE_MANPOWER || checkCivID2 == Game.player.iCivID || DiplomacyManager.isAlly(checkCivID2, Game.player.iCivID)) {
                int armiesManpower = 0;
                int armiesManpowerFull = 0;
                try {
                    for (int j = 0; j < Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).iArmyRegimentSize; ++j) {
                        armiesManpower += Game.getProvince(Game.hoveredArmy.iProvinceID).getArmy(Game.hoveredArmy.iArmyID).lArmyRegiment.get(j).num;
                        ++armiesManpowerFull;
                    }
                }
                catch (final Exception ex3) {}
                armiesManpowerFull *= Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE;
                if (armiesManpowerFull != armiesManpower) {
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Manpower") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getNumberWithSpaces("" + armiesManpower) + " / " + CFG.getShortNumber(armiesManpowerFull), CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Text(" - " + CFG.getPrecision2(armiesManpower / (float)armiesManpowerFull * 100.0f, 10) + "%", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                    nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
            }
            Game.provinceHover_Informations = new MenuElement_Hover(nElements);
        }
        catch (final Exception ex2) {
            Game.provinceHover_Informations = null;
        }
    }
    
    public static final void updateShipHovered() {
        try {
            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
            if (ShipManager.shipLines.get(Game.hoveredShipID).fromProvinceID >= 0 && ShipManager.shipLines.get(Game.hoveredShipID).toProvinceID >= 0) {
                if (!ShipManager.ships.get(Game.hoveredShipID).movingBack) {
                    if (Game.getProvince(ShipManager.shipLines.get(Game.hoveredShipID).fromProvinceID).getCivID() == 0) {
                        if (Game.getProvince(ShipManager.shipLines.get(Game.hoveredShipID).toProvinceID).getCivID() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(Game.getProvince(ShipManager.shipLines.get(Game.hoveredShipID).toProvinceID).getCivID(), Game.lang.get("Ship")));
                        }
                        else {
                            nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(Game.getProvince(ShipManager.shipLines.get(Game.hoveredShipID).fromProvinceID).getCivID(), Game.lang.get("UnknownShip")));
                        }
                    }
                    else {
                        nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(Game.getProvince(ShipManager.shipLines.get(Game.hoveredShipID).fromProvinceID).getCivID(), Game.lang.get("Ship")));
                    }
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(ShipManager.shipLines.get(Game.hoveredShipID).fromProvinceID).getProvinceName() + " - " + Game.getProvince(ShipManager.shipLines.get(Game.hoveredShipID).toProvinceID).getProvinceName(), CFG.FONT_REGULAR_SMALL));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    if (Game.getProvince(ShipManager.shipLines.get(Game.hoveredShipID).fromProvinceID).getCivID() > 0 && Game.getProvince(ShipManager.shipLines.get(Game.hoveredShipID).fromProvinceID).getResourceID() >= 0) {
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonusResource(ResourcesManager.lResources.get(Game.getProvince(ShipManager.shipLines.get(Game.hoveredShipID).fromProvinceID).getResourceID()).Name, "", Game.getProvince(ShipManager.shipLines.get(Game.hoveredShipID).fromProvinceID).getResourceID(), CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
                else {
                    if (Game.getProvince(ShipManager.shipLines.get(Game.hoveredShipID).toProvinceID).getCivID() == 0) {
                        if (Game.getProvince(ShipManager.shipLines.get(Game.hoveredShipID).fromProvinceID).getCivID() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(Game.getProvince(ShipManager.shipLines.get(Game.hoveredShipID).fromProvinceID).getCivID(), Game.lang.get("Ship")));
                        }
                        else {
                            nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(Game.getProvince(ShipManager.shipLines.get(Game.hoveredShipID).toProvinceID).getCivID(), Game.lang.get("UnknownShip")));
                        }
                    }
                    else {
                        nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(Game.getProvince(ShipManager.shipLines.get(Game.hoveredShipID).toProvinceID).getCivID(), Game.lang.get("Ship")));
                    }
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(ShipManager.shipLines.get(Game.hoveredShipID).toProvinceID).getProvinceName() + " - " + Game.getProvince(ShipManager.shipLines.get(Game.hoveredShipID).fromProvinceID).getProvinceName(), CFG.FONT_REGULAR_SMALL));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    if (Game.getProvince(ShipManager.shipLines.get(Game.hoveredShipID).toProvinceID).getCivID() > 0 && Game.getProvince(ShipManager.shipLines.get(Game.hoveredShipID).toProvinceID).getResourceID() >= 0) {
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonusResource(ResourcesManager.lResources.get(Game.getProvince(ShipManager.shipLines.get(Game.hoveredShipID).toProvinceID).getResourceID()).Name, "", Game.getProvince(ShipManager.shipLines.get(Game.hoveredShipID).toProvinceID).getResourceID(), CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
            }
            else {
                nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(Game.getProvince(1).getCivID(), Game.lang.get("UnknownShip")));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Pirates"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_NEGATIVE));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            Game.provinceHover_Informations = new MenuElement_Hover(nElements);
        }
        catch (final Exception ex) {
            Game.provinceHover_Informations = null;
        }
    }
    
    public static final void updateProvinceHoverCapitalFlag() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(Game.getProvince(Game.iHoveredCapitalProvinceID).getCivID(), Game.getProvince(Game.iHoveredCapitalProvinceID).getProvinceName()));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (Game.menuManager.getInGame()) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("CivilizationRank") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.getProvince(Game.iHoveredCapitalProvinceID).getCivID()).iCivRankPosition, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_Image(CivilizationRanking.getCivilizationRanking_IMG_STAR_CIVID(Game.getProvince(Game.iHoveredCapitalProvinceID).getCivID()), CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Religion") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text(Game.religionManager.getReligion(Game.getCiv(Game.getProvince(Game.iHoveredCapitalProvinceID).getCivID()).getReligionID()).Name, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_Religion(Game.getCiv(Game.getProvince(Game.iHoveredCapitalProvinceID).getCivID()).getReligionID(), CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        else if (Game.menuManager.getInNewGame()) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("CivilizationRank") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.getProvince(Game.iHoveredCapitalProvinceID).getCivID()).iCivRankPosition, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_Image(CivilizationRanking.getCivilizationRanking_IMG_STAR_CIVID(Game.getProvince(Game.iHoveredCapitalProvinceID).getCivID()), CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
    }
    
    public static final void updateProvinceHoverBuild() {
        if (Game.menuManager.getInMainMenu() || Game.menuManager.getInLoadGamesList() || Game.menuManager.getInScenarios_NewGame() || Game.menuManager.getInGameLegacies()) {
            ProvinceHover.provinceHoverBuild = new ProvinceHoverBuild() {
                @Override
                public void build() {
                    Game.provinceHover_Informations = null;
                }
            };
        }
        else if (Game.menuManager.getInScenarioWasteland() || Game.menuManager.getInScenarioCivilizations() || Game.menuManager.getInScenarioAssign()) {
            ProvinceHover.provinceHoverBuild = new ProvinceHoverBuild() {
                @Override
                public void build() {
                    if (Game.iHoveredProvinceID >= 0) {
                        if (!Game.getProvince(Game.iHoveredProvinceID).getSeaProvince()) {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                            if (Game.getProvince(Game.iHoveredProvinceID).getProvinceName().length() > 0) {
                                nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID()));
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), CFG.FONT_BOLD_SMALL));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                nData.add(new MenuElement_HoverElement_Type_Line());
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Terrain") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.terrainManager.terrains.get(Game.getProvince(Game.iHoveredProvinceID).getTerrainID()).Name, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Terrain(Game.getProvince(Game.iHoveredProvinceID).getTerrainID(), Game.iHoveredProvinceID, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("GrowthRate") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + (int)Game.getProvince(Game.iHoveredProvinceID).getGrowthRate() + "%", CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        }
                        else {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Terrain") + ": ", CFG.FONT_BOLD_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.terrainManager.terrains.get(Game.getProvince(Game.iHoveredProvinceID).getTerrainID()).Name, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Terrain(Game.getProvince(Game.iHoveredProvinceID).getTerrainID(), Game.iHoveredProvinceID, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                        }
                    }
                }
            };
        }
        else if (Game.menuManager.getInScenarioWastelandContinents()) {
            ProvinceHover.provinceHoverBuild = new ProvinceHoverBuild() {
                @Override
                public void build() {
                    if (Game.iHoveredProvinceID >= 0 && !Game.getProvince(Game.iHoveredProvinceID).getSeaProvince()) {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Continent") + ": ", CFG.FONT_BOLD_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.continents.lContinents.get(Game.getProvince(Game.iHoveredProvinceID).getContinent()).sName, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        if (Game.getProvince(Game.iHoveredProvinceID).getProvinceName().length() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(Game.iHoveredProvinceID).getCivID()));
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(Game.iHoveredProvinceID).getProvinceName(), CFG.FONT_REGULAR_SMALL));
                            nElements.add(new MenuElement_HoverElement(nData));
                        }
                        Game.provinceHover_Informations = new MenuElement_Hover(nElements);
                    }
                    else {
                        Game.provinceHover_Informations = null;
                    }
                }
            };
        }
        else if (Game.menuManager.getInGame()) {
            ProvinceHover.provinceHoverBuild = Game.mapModes.lMapModes.get(Game.mapModes.iActiveMapModeID).provinceHoverBuild;
        }
        else {
            ProvinceHover.provinceHoverBuild = new ProvinceHoverBuild() {
                @Override
                public void build() {
                    Game.provinceHover_Informations = null;
                }
            };
        }
        Game.provinceHover_Informations = null;
    }
    
    static {
        ProvinceHover.provinceHoverBuild = new ProvinceHoverBuild() {
            @Override
            public void build() {
            }
        };
    }
    
    public interface ProvinceHoverBuild
    {
        void build();
    }
}
