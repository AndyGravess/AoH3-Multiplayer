// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.civilization;

import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Center;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoc.kingdoms.lukasz.jakowski.Game_Ages;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Graph;
import aoc.kingdoms.lukasz.menu_element.graph.Graph;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Empty;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.textures.Images;
import java.util.List;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.map.ResourcesManager;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.Game;

public class CivilizationRanking
{
    public static float bestArmyAttackDefense;
    public static int rankingMaxCivs;
    public static int numGold;
    public static int numSilver;
    public static int numBronze;
    
    public static final void buildCivilizationRanking() {
        CivilizationRanking.bestArmyAttackDefense = 0.0f;
        CivilizationRanking.rankingMaxCivs = 0;
        int i = 1;
        int bestArmyCiv = 0;
        while (i < Game.getCivsSize()) {
            bestArmyCiv = Game.getCiv(i).getUnitsBest_AttackDefense();
            if (CivilizationRanking.bestArmyAttackDefense < bestArmyCiv) {
                CivilizationRanking.bestArmyAttackDefense = (float)bestArmyCiv;
            }
            ++i;
        }
        final List<Integer> tCivs = new ArrayList<Integer>();
        for (int j = 1; j < Game.getCivsSize(); ++j) {
            Game.getCiv(j).iCivRankScoreArmy = 0.0f;
            if (Game.getCiv(j).getNumOfProvinces() > 0) {
                tCivs.add(j);
                updateCivilizationRanking(j);
                ++CivilizationRanking.rankingMaxCivs;
            }
            else {
                Game.getCiv(j).iCivRankPosition = Game.getCivsSize();
            }
        }
        updateCivilizationRanking_Army();
        for (int j = 1; j < Game.getCivsSize(); ++j) {
            final Civilization civ = Game.getCiv(j);
            civ.iCivRankScore += Game.getCiv(j).iCivRankScoreArmy;
        }
        for (int j = 0, iSize = ResourcesManager.worldResources_LargestProducer.size(); j < iSize; ++j) {
            final Civilization civ2 = Game.getCiv(ResourcesManager.worldResources_LargestProducer.get(j));
            civ2.iCivRankScore += GameValues.civRank.CIV_SCORE_PER_LARGEST_PRODUCER;
        }
        for (int j = 1; j < Game.getCivsSize(); ++j) {
            if (Game.getCiv(j).getNumOfProvinces() > 0 && Game.getCiv(j).getCivID() != Game.getCiv(j).getPuppetOfCivID() && Game.getCiv(j).getPuppetOfCivID() == Game.getCiv(Game.getCiv(j).getPuppetOfCivID()).getPuppetOfCivID()) {
                final Civilization civ3 = Game.getCiv(Game.getCiv(j).getPuppetOfCivID());
                civ3.iCivRankScore += Game.getCiv(j).iCivRankScore * GameValues.civRank.CIV_SCORE_VASSALS;
            }
        }
        int tID = 1;
        while (tCivs.size() > 0) {
            int tBestID = 0;
            for (int k = 1, iSize2 = tCivs.size(); k < iSize2; ++k) {
                if (Game.getCiv(tCivs.get(tBestID)).iCivRankScore < Game.getCiv(tCivs.get(k)).iCivRankScore) {
                    tBestID = k;
                }
            }
            Game.getCiv(tCivs.get(tBestID)).iCivRankPosition = tID++;
            tCivs.remove(tBestID);
        }
        CivilizationRanking.numGold = (int)Math.min(7.0, Math.max(2.0, Math.ceil(CivilizationRanking.rankingMaxCivs * 0.07f)));
        CivilizationRanking.numSilver = CivilizationRanking.numGold + (int)Math.min(8.0, Math.max(2.0, Math.ceil(CivilizationRanking.rankingMaxCivs * 0.07f)));
        CivilizationRanking.numBronze = CivilizationRanking.numSilver + Math.max(2, (int)(CivilizationRanking.rankingMaxCivs * 0.55f));
    }
    
    public static final void updateCivilizationRanking(final int iCivID) {
        Game.getCiv(iCivID).iCivRankScore = getCivilizationRanking(iCivID);
    }
    
    public static final float getCivilizationRanking(final int iCivID) {
        return getCivilizationRanking_Rank(iCivID) + getCivilizationRanking_Economy(iCivID) + getCivilizationRanking_TaxEfficiency(iCivID) + getCivilizationRanking_Technology(iCivID) + getCivilizationRanking_Legacy(iCivID) + getCivilizationRanking_LevelsUpgrades(iCivID) + getCivilizationRanking_Buildings(iCivID) + Game.getCiv(iCivID).iCivRankScoreArmy;
    }
    
    public static final float getCivilizationRanking_INFO(final int iCivID) {
        return getCivilizationRanking_Rank(iCivID) + getCivilizationRanking_Economy(iCivID) + getCivilizationRanking_TaxEfficiency(iCivID) + getCivilizationRanking_Technology(iCivID) + Game.getCiv(iCivID).iCivRankScoreArmy + getCivilizationRanking_LargestProducer(iCivID) + getCivilizationRanking_Legacy(iCivID) + getCivilizationRanking_LevelsUpgrades(iCivID) + getCivilizationRanking_Buildings(iCivID) + getCivilizationRanking_Vassals(iCivID);
    }
    
    public static final void updateCivilizationRanking_Army() {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            for (int j = Game.getProvince(i).getArmySize() - 1; j >= 0; --j) {
                final Civilization civ = Game.getCiv(Game.getProvince(i).getArmy(j).civID);
                civ.iCivRankScoreArmy += Game.getProvince(i).getArmy(j).getCivilizationRanking_ArmyScore();
            }
        }
    }
    
    public static final float getCivilizationRanking_Rank(final int iCivID) {
        return GameValues.civRank.CIV_SCORE_RANK[Game.getCiv(iCivID).iCivRankID];
    }
    
    public static final float getCivilizationRanking_TaxEfficiency(final int iCivID) {
        return Game.getCiv(iCivID).getTaxEfficiencyTotal_CivRank() * GameValues.civRank.CIV_SCORE_PER_TAX_EFFICIENCY;
    }
    
    public static final float getCivilizationRanking_Economy(final int iCivID) {
        return Game.getCiv(iCivID).getEconomyTotal_CivRank() * GameValues.civRank.CIV_SCORE_PER_ECONOMY;
    }
    
    public static final float getCivilizationRanking_Technology(final int iCivID) {
        return Game.getCiv(iCivID).getResearchedTechnologies() * GameValues.civRank.CIV_SCORE_PER_TECHNOLOGY;
    }
    
    public static final float getCivilizationRanking_Legacy(final int iCivID) {
        return Game.getCiv(iCivID).getLegaciesUnlocked() * GameValues.civRank.CIV_SCORE_PER_LEGACY;
    }
    
    public static final float getCivilizationRanking_LevelsUpgrades(final int iCivID) {
        return (Game.getCiv(iCivID).getCapitalLevel() + Game.getCiv(iCivID).getMilitaryAcademyLevel() + Game.getCiv(iCivID).getMilitaryAcademyForGeneralsLevel() + Game.getCiv(iCivID).getSupremeCourtLevel() + Game.getCiv(iCivID).getNuclearReactorLevel()) * GameValues.civRank.CIV_SCORE_PER_LEVELS_UPGRADES;
    }
    
    public static final float getCivilizationRanking_Buildings(final int iCivID) {
        return Game.getCiv(iCivID).getConstructedBuildings() * GameValues.civRank.CIV_SCORE_PER_BUILDING;
    }
    
    public static final float getCivilizationRanking_LargestProducer(final int iCivID) {
        float out = 0.0f;
        for (int i = 0, iSize = ResourcesManager.worldResources_LargestProducer.size(); i < iSize; ++i) {
            if (ResourcesManager.worldResources_LargestProducer.get(i) == iCivID) {
                out += GameValues.civRank.CIV_SCORE_PER_LARGEST_PRODUCER;
            }
        }
        return out;
    }
    
    public static final float getCivilizationRanking_Vassals(final int iCivID) {
        float out = 0.0f;
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).getNumOfProvinces() > 0 && iCivID == Game.getCiv(i).getPuppetOfCivID() && Game.getCiv(i).getCivID() != Game.getCiv(i).getPuppetOfCivID() && Game.getCiv(i).getPuppetOfCivID() == Game.getCiv(Game.getCiv(i).getPuppetOfCivID()).getPuppetOfCivID()) {
                out += Game.getCiv(i).iCivRankScore * GameValues.civRank.CIV_SCORE_VASSALS;
            }
        }
        return out;
    }
    
    public static final int getCivilizationRanking_IMG_STAR_CIVID(final int iCivID) {
        if (Game.getCiv(iCivID).iCivRankPosition <= CivilizationRanking.numGold) {
            return Images.rankGold;
        }
        if (Game.getCiv(iCivID).iCivRankPosition <= CivilizationRanking.numSilver) {
            return Images.rankSilver;
        }
        if (Game.getCiv(iCivID).iCivRankPosition <= CivilizationRanking.numBronze) {
            return Images.rankBronze;
        }
        return Images.rankBlack;
    }
    
    public static final float getDiplomacyPoints_RankStar(final int iCivID) {
        if (Game.getCiv(iCivID).iCivRankPosition <= CivilizationRanking.numGold) {
            return GameValues.diplomacy.DIPLOMACY_POINTS_RANK_GOLD;
        }
        if (Game.getCiv(iCivID).iCivRankPosition <= CivilizationRanking.numSilver) {
            return GameValues.diplomacy.DIPLOMACY_POINTS_RANK_SILVER;
        }
        if (Game.getCiv(iCivID).iCivRankPosition <= CivilizationRanking.numBronze) {
            return GameValues.diplomacy.DIPLOMACY_POINTS_RANK_BRONZE;
        }
        return GameValues.diplomacy.DIPLOMACY_POINTS_RANK_BLACK;
    }
    
    public static MenuElement_Hover getHover_CivilizationRanking_Short(final int iCivID, final boolean showTitleBG, final boolean drawGraph) {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("CivilizationRank") + ": ", "" + Game.getCiv(iCivID).iCivRankPosition, getCivilizationRanking_IMG_STAR_CIVID(iCivID), CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Prestige") + ": ", "" + CFG.getPrecision2(getCivilizationRanking_INFO(iCivID), 10), getCivilizationRanking_IMG_STAR_CIVID(iCivID), CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        return new MenuElement_Hover(nElements);
    }
    
    public static MenuElement_Hover getHover_CivilizationRanking(final int iCivID, final boolean showTitleBG, final boolean drawGraph) {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(iCivID, Game.lang.get("CivilizationRank") + ": " + ((Game.getCiv(iCivID).iCivRankPosition > 0) ? Integer.valueOf(Game.getCiv(iCivID).iCivRankPosition) : "---")));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        float totalPrestige = getCivilizationRanking_INFO(iCivID);
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Prestige") + ": ", "" + CFG.getPrecision2(totalPrestige, 10), getCivilizationRanking_IMG_STAR_CIVID(iCivID), CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (drawGraph) {
            try {
                nData.add(new MenuElement_HoverElement_Type_Empty());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Graph(Game.lang.get("Prestige"), Graph.GraphType.PLAYER_PRESTIGE, false));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData, !drawGraph));
        nData.clear();
        final List<SortedElements> sortedElements = new ArrayList<SortedElements>();
        totalPrestige = Math.max(0.1f, totalPrestige);
        float value = getCivilizationRanking_Rank(iCivID);
        nData.add(new MenuElement_HoverElement_Type_Text(getCivilizationRank_Name(Game.getCiv(iCivID).iCivRankID) + ": ", CFG.FONT_REGULAR, Colors.HOVER_LEFT));
        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(value, 10), CFG.FONT_BOLD, (value <= 0.0f) ? Colors.HOVER_NEUTRAL : Colors.COLOR_TEXT_MODIFIER_POSITIVE));
        nData.add(new MenuElement_HoverElement_Type_Image(getCivilizationRank_IMG(Game.getCiv(iCivID).iCivRankID), CFG.PADDING, CFG.PADDING));
        nData.add(new MenuElement_HoverElement_Type_Text(" [" + CFG.getPrecision2(value / totalPrestige * 100.0f, 10) + "%]", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
        sortedElements.add(new SortedElements(new MenuElement_HoverElement(nData, !drawGraph), value));
        nData.clear();
        value = getCivilizationRanking_TaxEfficiency(iCivID);
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("TaxEfficiency") + ": ", CFG.FONT_REGULAR, Colors.HOVER_LEFT));
        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(value, 10), CFG.FONT_BOLD, (value <= 0.0f) ? Colors.HOVER_NEUTRAL : Colors.COLOR_TEXT_MODIFIER_POSITIVE));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.tax, CFG.PADDING, CFG.PADDING));
        nData.add(new MenuElement_HoverElement_Type_Text(" [" + CFG.getPrecision2(value / totalPrestige * 100.0f, 10) + "%]", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
        sortedElements.add(new SortedElements(new MenuElement_HoverElement(nData, !drawGraph), value));
        nData.clear();
        value = getCivilizationRanking_Economy(iCivID);
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Economy") + ": ", CFG.FONT_REGULAR, Colors.HOVER_LEFT));
        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(value, 10), CFG.FONT_BOLD, (value <= 0.0f) ? Colors.HOVER_NEUTRAL : Colors.COLOR_TEXT_MODIFIER_POSITIVE));
        nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_ECONOMY, CFG.PADDING, CFG.PADDING));
        nData.add(new MenuElement_HoverElement_Type_Text(" [" + CFG.getPrecision2(value / totalPrestige * 100.0f, 10) + "%]", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
        sortedElements.add(new SortedElements(new MenuElement_HoverElement(nData, !drawGraph), value));
        nData.clear();
        value = getCivilizationRanking_LargestProducer(iCivID);
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LargestProducer") + ": ", CFG.FONT_REGULAR, Colors.HOVER_LEFT));
        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(value, 10), CFG.FONT_BOLD, (value <= 0.0f) ? Colors.HOVER_NEUTRAL : Colors.COLOR_TEXT_MODIFIER_POSITIVE));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.goods, CFG.PADDING, CFG.PADDING));
        nData.add(new MenuElement_HoverElement_Type_Text(" [" + CFG.getPrecision2(value / totalPrestige * 100.0f, 10) + "%]", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
        sortedElements.add(new SortedElements(new MenuElement_HoverElement(nData, !drawGraph), value));
        nData.clear();
        value = getCivilizationRanking_Technology(iCivID);
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Technology") + ": ", CFG.FONT_REGULAR, Colors.HOVER_LEFT));
        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(value, 10), CFG.FONT_BOLD, (value <= 0.0f) ? Colors.HOVER_NEUTRAL : Colors.COLOR_TEXT_MODIFIER_POSITIVE));
        nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, CFG.PADDING));
        nData.add(new MenuElement_HoverElement_Type_Text(" [" + CFG.getPrecision2(value / totalPrestige * 100.0f, 10) + "%]", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
        sortedElements.add(new SortedElements(new MenuElement_HoverElement(nData, !drawGraph), value));
        nData.clear();
        value = getCivilizationRanking_Legacy(iCivID);
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("CivilizationLegacy") + ": ", CFG.FONT_REGULAR, Colors.HOVER_LEFT));
        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(value, 10), CFG.FONT_BOLD, (value <= 0.0f) ? Colors.HOVER_NEUTRAL : Colors.COLOR_TEXT_MODIFIER_POSITIVE));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, CFG.PADDING));
        nData.add(new MenuElement_HoverElement_Type_Text(" [" + CFG.getPrecision2(value / totalPrestige * 100.0f, 10) + "%]", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
        sortedElements.add(new SortedElements(new MenuElement_HoverElement(nData, !drawGraph), value));
        nData.clear();
        value = getCivilizationRanking_LevelsUpgrades(iCivID);
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("CapitalCity") + ": ", CFG.FONT_REGULAR, Colors.HOVER_LEFT));
        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(value, 10), CFG.FONT_BOLD, (value <= 0.0f) ? Colors.HOVER_NEUTRAL : Colors.COLOR_TEXT_MODIFIER_POSITIVE));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.capital, CFG.PADDING, CFG.PADDING));
        nData.add(new MenuElement_HoverElement_Type_Text(" [" + CFG.getPrecision2(value / totalPrestige * 100.0f, 10) + "%]", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
        sortedElements.add(new SortedElements(new MenuElement_HoverElement(nData, !drawGraph), value));
        nData.clear();
        value = getCivilizationRanking_Buildings(iCivID);
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Buildings") + ": ", CFG.FONT_REGULAR, Colors.HOVER_LEFT));
        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(value, 10), CFG.FONT_BOLD, (value <= 0.0f) ? Colors.HOVER_NEUTRAL : Colors.COLOR_TEXT_MODIFIER_POSITIVE));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.buildings, CFG.PADDING, CFG.PADDING));
        nData.add(new MenuElement_HoverElement_Type_Text(" [" + CFG.getPrecision2(value / totalPrestige * 100.0f, 10) + "%]", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
        sortedElements.add(new SortedElements(new MenuElement_HoverElement(nData, !drawGraph), value));
        nData.clear();
        value = Game.getCiv(iCivID).iCivRankScoreArmy;
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Army") + ": ", CFG.FONT_REGULAR, Colors.HOVER_LEFT));
        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(value, 10), CFG.FONT_BOLD, (value <= 0.0f) ? Colors.HOVER_NEUTRAL : Colors.COLOR_TEXT_MODIFIER_POSITIVE));
        nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, CFG.PADDING));
        nData.add(new MenuElement_HoverElement_Type_Text(" [" + CFG.getPrecision2(value / totalPrestige * 100.0f, 10) + "%]", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
        sortedElements.add(new SortedElements(new MenuElement_HoverElement(nData, !drawGraph), value));
        nData.clear();
        value = getCivilizationRanking_Vassals(iCivID);
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get(Game_Ages.getVassals()) + ": ", CFG.FONT_REGULAR, Colors.HOVER_LEFT));
        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(value, 10), CFG.FONT_BOLD, (value <= 0.0f) ? Colors.HOVER_NEUTRAL : Colors.COLOR_TEXT_MODIFIER_POSITIVE));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.vassal, CFG.PADDING, CFG.PADDING));
        nData.add(new MenuElement_HoverElement_Type_Text(" [" + CFG.getPrecision2(value / totalPrestige * 100.0f, 10) + "%]", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
        sortedElements.add(new SortedElements(new MenuElement_HoverElement(nData, !drawGraph), value));
        nData.clear();
        while (!sortedElements.isEmpty()) {
            int bestID = 0;
            for (int i = sortedElements.size() - 1; i > 0; --i) {
                if (sortedElements.get(bestID).value < sortedElements.get(i).value) {
                    bestID = i;
                }
            }
            if (sortedElements.get(bestID).value > 0.0f) {
                nElements.add(sortedElements.get(bestID).hoverElement);
            }
            sortedElements.remove(bestID);
        }
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData, !drawGraph));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DiplomacyPoints") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(getDiplomacyPoints_RankStar(iCivID), 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_POSITIVE));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.diplomacy, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData, !drawGraph));
        nData.clear();
        if (showTitleBG && drawGraph && CFG.isDesktop()) {
            nData.add(new MenuElement_HoverElement_Type_Empty());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Shortcut") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
            nData.add(new MenuElement_HoverElement_Type_Text("R", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.rank, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (showTitleBG) {
            nData.add(new MenuElement_HoverElement_Type_Empty());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Ranking"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            return new MenuElement_Hover(nElements, true);
        }
        return new MenuElement_Hover(nElements);
    }
    
    public static int getCivilizationRank(final int iNumOfProvinces) {
        if (iNumOfProvinces < GameValues.civRank.CIV_RANK_PROVINCES[0]) {
            return 0;
        }
        if (iNumOfProvinces < GameValues.civRank.CIV_RANK_PROVINCES[1]) {
            return 1;
        }
        if (iNumOfProvinces < GameValues.civRank.CIV_RANK_PROVINCES[2]) {
            return 2;
        }
        if (iNumOfProvinces < GameValues.civRank.CIV_RANK_PROVINCES[3]) {
            return 3;
        }
        if (iNumOfProvinces < GameValues.civRank.CIV_RANK_PROVINCES[4]) {
            return 4;
        }
        return 5;
    }
    
    public static String getCivilizationRank_Name(final int iRank) {
        try {
            return Game.lang.get(GameValues.civRank.CIV_RANK_NAME[iRank]);
        }
        catch (final Exception ex) {
            switch (iRank) {
                case 1: {
                    return Game.lang.get("LocalPower");
                }
                case 2: {
                    return Game.lang.get("RegionalPower");
                }
                case 3: {
                    return Game.lang.get("MajorPower");
                }
                case 4: {
                    return Game.lang.get("GreatPower");
                }
                case 5: {
                    return Game.lang.get("Empire");
                }
                default: {
                    return Game.lang.get("CityState");
                }
            }
        }
    }
    
    public static int getCivilizationRank_IMG(final int iRank) {
        switch (iRank) {
            case 0: {
                return Images.civRank_0;
            }
            case 1: {
                return Images.civRank_1;
            }
            case 2: {
                return Images.civRank_2;
            }
            case 3: {
                return Images.civRank_3;
            }
            case 4: {
                return Images.civRank_4;
            }
            case 5: {
                return Images.civRank_5;
            }
            default: {
                return Images.civRank_5;
            }
        }
    }
    
    public static MenuElement_Hover buildElementHover(final int iCivID, final int iRank) {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("CivilizationRank") + ": ", CFG.FONT_BOLD));
        nData.add(new MenuElement_HoverElement_Type_TextTitle(getCivilizationRank_Name(iRank), CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_ImageTitle(getCivilizationRank_IMG(iRank), CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        String sProvinces = "";
        if (iRank == 0) {
            sProvinces = "1 - " + GameValues.civRank.CIV_RANK_PROVINCES[iRank];
        }
        else {
            try {
                sProvinces = "" + (GameValues.civRank.CIV_RANK_PROVINCES[iRank - 1] + 1) + " - " + GameValues.civRank.CIV_RANK_PROVINCES[iRank];
            }
            catch (final Exception ex) {
                sProvinces = "" + (GameValues.civRank.CIV_RANK_PROVINCES[iRank - 1] + 1) + "+";
            }
        }
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.lang.get("Required"), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Provinces") + ": ", sProvinces, Images.provinces, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (Game.getCiv(iCivID).getPuppetOfCivID() == iCivID) {
            if (GameValues.civRank.CIV_BASE_INCOME[iRank] != 0.0f) {
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Income") + ": ", "+" + CFG.getPrecision2(GameValues.civRank.CIV_BASE_INCOME[iRank], 100), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
        }
        else if (GameValues.civRank.CIV_BASE_INCOME[iRank] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get(Game_Ages.getVassal()) + ", " + Game.lang.get("Income") + ": ", "+" + CFG.getPrecision2(GameValues.civRank.CIV_BASE_INCOME_VASSAL[iRank], 100), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (GameValues.civRank.CIV_RANK_ARMY_MAINTENANCE[iRank] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ArmyMaintenance") + ": ", "" + CFG.getPrecision2(GameValues.civRank.CIV_RANK_ARMY_MAINTENANCE[iRank] * 100.0f, 100) + "%", Images.armyMaintenance, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (GameValues.civRank.CIV_RANK_MANPOWER_MAX[iRank] != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", "+" + GameValues.civRank.CIV_RANK_MANPOWER_MAX[iRank], Game_Calendar.IMG_MANPOWER_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (GameValues.civRank.CIV_RANK_INVEST_ECONOMY_COST[iRank] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("InvestInEconomyCost") + ": ", "" + CFG.getPrecision2(GameValues.civRank.CIV_RANK_INVEST_ECONOMY_COST[iRank] * 100.0f, 100) + "%", Game_Calendar.IMG_ECONOMY_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (GameValues.civRank.CIV_RANK_BUILDING_CONSTRUCTION_COST[iRank] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ConstructionCost") + ": ", "" + CFG.getPrecision2(GameValues.civRank.CIV_RANK_BUILDING_CONSTRUCTION_COST[iRank] * 100.0f, 100) + "%", Images.build, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (GameValues.civRank.CIV_RANK_PROVINCE_MAINTENANCE[iRank] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProvinceMaintenance") + ": ", ((GameValues.civRank.CIV_RANK_PROVINCE_MAINTENANCE[iRank] > 0.0f) ? "+" : "") + CFG.getPrecision2(GameValues.civRank.CIV_RANK_PROVINCE_MAINTENANCE[iRank] * 100.0f, 100) + "%", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (GameValues.civRank.CIV_RANK_ADVISOR_COST[iRank] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AdvisorCost") + ": ", "" + CFG.getPrecision2(GameValues.civRank.CIV_RANK_ADVISOR_COST[iRank] * 100.0f, 100) + "%", Images.diplomacy, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (GameValues.civRank.CIV_RANK_GENERAL_COST[iRank] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GeneralCost") + ": ", "" + CFG.getPrecision2(GameValues.civRank.CIV_RANK_GENERAL_COST[iRank] * 100.0f, 100) + "%", Images.general, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (GameValues.civRank.CIV_RANK_MONTHLY_LEGACY[iRank] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyLegacy") + ": ", "+" + CFG.getPrecision2(GameValues.civRank.CIV_RANK_MONTHLY_LEGACY[iRank], 100), Images.legacy, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (GameValues.civRank.CIV_RANK_MONTHLY_RESEARCH[iRank] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ResearchPerMonth") + ": ", "+" + CFG.getPrecision2(GameValues.civRank.CIV_RANK_MONTHLY_RESEARCH[iRank], 100), Game_Calendar.IMG_TECHNOLOGY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (GameValues.civRank.CIV_RANK_LOAN_INTEREST[iRank] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Interest") + ": ", "" + CFG.getPrecision2(GameValues.civRank.CIV_RANK_LOAN_INTEREST[iRank], 100) + "%", Images.loan, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (GameValues.civRank.CIV_RANK_WAR_SCORE_COST[iRank] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("WarScoreCost") + ": ", ((GameValues.civRank.CIV_RANK_WAR_SCORE_COST[iRank] > 0.0f) ? "+" : "") + CFG.getPrecision2(GameValues.civRank.CIV_RANK_WAR_SCORE_COST[iRank] * 100.0f, 100) + "%", Images.victoryPoints, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (Game.menuManager.getInGame()) {
            nData.add(new MenuElement_HoverElement_Type_Empty());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("CivilizationRank"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            return new MenuElement_Hover(nElements, true);
        }
        return new MenuElement_Hover(nElements);
    }
    
    static {
        CivilizationRanking.bestArmyAttackDefense = 0.0f;
        CivilizationRanking.rankingMaxCivs = 0;
        CivilizationRanking.numGold = 1;
        CivilizationRanking.numSilver = 1;
        CivilizationRanking.numBronze = 1;
    }
    
    public static class SortedElements
    {
        public MenuElement_HoverElement hoverElement;
        public float value;
        
        public SortedElements(final MenuElement_HoverElement hoverElement, final float value) {
            this.hoverElement = hoverElement;
            this.value = value;
        }
    }
}
