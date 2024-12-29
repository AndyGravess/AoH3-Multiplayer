// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.allianceHRE;

import com.badlogic.gdx.graphics.Texture;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoc.kingdoms.lukasz.map.technology.TechnologyTree;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.map.civilization.CivilizationBonuses;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Steam.SteamAchievementsManager;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.textures.Image;
import java.util.List;

public class HREManager
{
    public static final int TYPE_OF_ALLIANCE_HRE = 0;
    public static int numOfReforms;
    public static List<Image> reformsIMG;
    
    public static int getManpower_Emperor(final int allianceID) {
        final int out = Game.alliancesSpecial.get(allianceID).getNumOfCivilizations_FirstTier() + Game.alliancesSpecial.get(allianceID).getNumOfCivilizations_SecondTier();
        return (int)(out * GameValues.hre.HRE_EMPEROR_MANPOWER);
    }
    
    public static float getIncome_Emperor(final int allianceID) {
        final int out = Game.alliancesSpecial.get(allianceID).getNumOfCivilizations_FirstTier() + Game.alliancesSpecial.get(allianceID).getNumOfCivilizations_SecondTier();
        return out * GameValues.hre.HRE_EMPEROR_INCOME;
    }
    
    public static final void uniteHRE(final int allianceID) {
        if (Game.alliancesSpecial.get(allianceID).iLeaderCivID > 0) {
            try {
                if (Game.alliancesSpecial.get(allianceID).iLeaderCivID == Game.player.iCivID) {
                    SteamAchievementsManager.unlockAchievement(SteamAchievementsManager.UNITE_HRE);
                }
                Game.getCiv(Game.alliancesSpecial.get(allianceID).iLeaderCivID).setCivTag(Game.alliancesSpecial.get(allianceID).FlagTag);
                Game.addSimpleTask(new Game.SimpleTask("loadFlagCiv" + Game.alliancesSpecial.get(allianceID).iLeaderCivID, Game.alliancesSpecial.get(allianceID).iLeaderCivID) {
                    @Override
                    public void update() {
                        Game.getCiv(this.id).disposeFlag();
                        Game.getCiv(this.id).loadFlag();
                    }
                });
                final int toCivID = Game.alliancesSpecial.get(allianceID).iLeaderCivID;
                for (int i = Game.alliancesSpecial.get(allianceID).firstTier.size() - 1; i >= 0; --i) {
                    try {
                        if (Game.alliancesSpecial.get(allianceID).firstTier.get(i) != toCivID) {
                            Game.getCiv(Game.alliancesSpecial.get(allianceID).firstTier.get(i)).removeAllArmies();
                        }
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
                for (int i = Game.alliancesSpecial.get(allianceID).secondTier.size() - 1; i >= 0; --i) {
                    try {
                        if (Game.alliancesSpecial.get(allianceID).secondTier.get(i) != toCivID) {
                            Game.getCiv(Game.alliancesSpecial.get(allianceID).secondTier.get(i)).removeAllArmies();
                        }
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
                for (int i = Game.alliancesSpecial.get(allianceID).firstTier.size() - 1; i >= 0; --i) {
                    try {
                        if (Game.alliancesSpecial.get(allianceID).firstTier.get(i) != toCivID) {
                            for (int j = Game.getCiv(Game.alliancesSpecial.get(allianceID).firstTier.get(i)).getNumOfProvinces() - 1; j >= 0; --j) {
                                Game.getProvince(Game.getCiv(Game.alliancesSpecial.get(allianceID).firstTier.get(i)).getProvinceID(j)).addCore_Just(toCivID);
                                Game.getProvince(Game.getCiv(Game.alliancesSpecial.get(allianceID).firstTier.get(i)).getProvinceID(j)).setCivID(toCivID);
                            }
                        }
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
                for (int i = Game.alliancesSpecial.get(allianceID).secondTier.size() - 1; i >= 0; --i) {
                    try {
                        if (Game.alliancesSpecial.get(allianceID).secondTier.get(i) != toCivID) {
                            for (int j = Game.getCiv(Game.alliancesSpecial.get(allianceID).secondTier.get(i)).getNumOfProvinces() - 1; j >= 0; --j) {
                                Game.getProvince(Game.getCiv(Game.alliancesSpecial.get(allianceID).secondTier.get(i)).getProvinceID(j)).addCore_Just(toCivID);
                                Game.getProvince(Game.getCiv(Game.alliancesSpecial.get(allianceID).secondTier.get(i)).getProvinceID(j)).setCivID(toCivID);
                            }
                        }
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
                for (int i = 0; i < Game.getCiv(toCivID).getNumOfProvinces(); ++i) {
                    Game.getProvince(Game.getCiv(toCivID).getProvinceID(i)).updateHaveACore();
                    if (Game.getProvince(Game.getCiv(toCivID).getProvinceID(i)).isCapital) {
                        Game.getProvince(Game.getCiv(toCivID).getProvinceID(i)).setIsCapital(Game.getCiv(toCivID).getProvinceID(i) == Game.getCiv(toCivID).getCapitalProvinceID());
                    }
                }
                for (int i = Game.alliancesSpecial.get(allianceID).firstTier.size() - 1; i >= 0; --i) {
                    Game.getCiv(Game.alliancesSpecial.get(allianceID).firstTier.get(i)).removeInAllianceSpecial(allianceID);
                }
                for (int i = Game.alliancesSpecial.get(allianceID).secondTier.size() - 1; i >= 0; --i) {
                    Game.getCiv(Game.alliancesSpecial.get(allianceID).secondTier.get(i)).removeInAllianceSpecial(allianceID);
                }
                Game.getCiv(Game.alliancesSpecial.get(allianceID).iLeaderCivID).removeInAllianceSpecial(allianceID);
                Game.alliancesSpecial.get(allianceID).iLeaderCivID = 0;
                Game.alliancesSpecial.get(allianceID).firstTier.clear();
                Game.alliancesSpecial.get(allianceID).secondTier.clear();
                Game.gameThread.addCivUpdateTotalIncomePerMonth(toCivID);
                Game.gameThread.addCivUpdateResearchPerMonth(toCivID);
                Game.gameThread.addCivUpdateLegacyPerMonth(toCivID);
                Game.gameThread.addCivUpdateArmyMaintenance(toCivID);
                Game.gameThreadTurns.addCivUpdateMaxManpower(toCivID);
            }
            catch (final Exception exr) {
                CFG.exceptionStack(exr);
            }
        }
    }
    
    public static final boolean passReform_Diplomacy(final int allianceID, final int reformID) {
        return GameValues.hre.HRE_REFORM_COST_DIPLOMACY[reformID] > Game.getCiv(Game.alliancesSpecial.get(allianceID).iLeaderCivID).fDiplomacy;
    }
    
    public static final boolean passReform_Legacy(final int allianceID, final int reformID) {
        return GameValues.hre.HRE_REFORM_COST_LEGACY[reformID] > Game.getCiv(Game.alliancesSpecial.get(allianceID).iLeaderCivID).fLegacy;
    }
    
    public static final boolean passReform_Gold(final int allianceID, final int reformID) {
        return GameValues.hre.HRE_REFORM_COST_GOLD[reformID] > Game.getCiv(Game.alliancesSpecial.get(allianceID).iLeaderCivID).fGold;
    }
    
    public static final boolean passReform_ReformsPassed(final int allianceID, final int reformID) {
        return Game.alliancesSpecial.get(allianceID).iReformsPassed != reformID;
    }
    
    public static final boolean passReform_RequiredTech(final int allianceID, final int reformID) {
        return GameValues.hre.HRE_REFORM_COST_REQUIRED_TECH[reformID] >= 0 && !Game.getCiv(Game.alliancesSpecial.get(allianceID).iLeaderCivID).getTechResearched(GameValues.hre.HRE_REFORM_COST_REQUIRED_TECH[reformID]);
    }
    
    public static final boolean passReform(final int allianceID, final int reformID) {
        if (passReform_Diplomacy(allianceID, reformID)) {
            return false;
        }
        if (passReform_Gold(allianceID, reformID)) {
            return false;
        }
        if (passReform_Legacy(allianceID, reformID)) {
            return false;
        }
        if (passReform_ReformsPassed(allianceID, reformID)) {
            return false;
        }
        if (passReform_RequiredTech(allianceID, reformID)) {
            return false;
        }
        try {
            if (GameValues.hre.HRE_REFORM_COST_LEGACY[reformID] > 0.0f) {
                final Civilization civ = Game.getCiv(Game.alliancesSpecial.get(allianceID).iLeaderCivID);
                civ.fLegacy -= GameValues.hre.HRE_REFORM_COST_LEGACY[reformID];
            }
            if (GameValues.hre.HRE_REFORM_COST_DIPLOMACY[reformID] > 0.0f) {
                final Civilization civ2 = Game.getCiv(Game.alliancesSpecial.get(allianceID).iLeaderCivID);
                civ2.fDiplomacy -= GameValues.hre.HRE_REFORM_COST_DIPLOMACY[reformID];
            }
            if (GameValues.hre.HRE_REFORM_COST_GOLD[reformID] > 0.0f) {
                final Civilization civ3 = Game.getCiv(Game.alliancesSpecial.get(allianceID).iLeaderCivID);
                civ3.fGold -= GameValues.hre.HRE_REFORM_COST_GOLD[reformID];
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            if (reformID == 0) {
                final CivilizationBonuses nCivBonus = new CivilizationBonuses();
                nCivBonus.IncomeEconomy = GameValues.hre.HRE_BONUS_R0;
                nCivBonus.TempTurnID = Game_Calendar.TURN_ID + GameValues.hre.HRE_BONUS_R0_DAYS;
                Game.getCiv(Game.alliancesSpecial.get(allianceID).iLeaderCivID).addCivilizationBonus_Temporary(nCivBonus);
            }
            else if (reformID == 1) {
                final List<Integer> tProvinces = new ArrayList<Integer>();
                for (int i = Game.alliancesSpecial.get(allianceID).firstTier.size() - 1; i >= 0; --i) {
                    if (Game.getCiv(Game.alliancesSpecial.get(allianceID).firstTier.get(i)).getCapitalProvinceID() >= 0 && Game.getProvince(Game.getCiv(Game.alliancesSpecial.get(allianceID).firstTier.get(i)).getCapitalProvinceID()).getCivID() == Game.alliancesSpecial.get(allianceID).firstTier.get(i)) {
                        tProvinces.add(Game.getCiv(Game.alliancesSpecial.get(allianceID).firstTier.get(i)).getCapitalProvinceID());
                    }
                }
                for (int i = Game.alliancesSpecial.get(allianceID).secondTier.size() - 1; i >= 0; --i) {
                    if (Game.getCiv(Game.alliancesSpecial.get(allianceID).secondTier.get(i)).getCapitalProvinceID() >= 0 && Game.getProvince(Game.getCiv(Game.alliancesSpecial.get(allianceID).secondTier.get(i)).getCapitalProvinceID()).getCivID() == Game.alliancesSpecial.get(allianceID).secondTier.get(i)) {
                        tProvinces.add(Game.getCiv(Game.alliancesSpecial.get(allianceID).secondTier.get(i)).getCapitalProvinceID());
                    }
                }
                if (tProvinces.size() > 0) {
                    for (int i = 0; i < GameValues.hre.HRE_BONUS_R1; ++i) {
                        Game.getProvince(tProvinces.get(Game.oR.nextInt(tProvinces.size()))).addDevelopInfrastructure_Free();
                    }
                }
            }
            else if (reformID == 2) {
                final List<Integer> tCivs = getCivs(allianceID);
                for (int i = 0; i < GameValues.hre.HRE_BONUS_R2_CIVS && tCivs.size() > 0; ++i) {
                    final int withID = Game.oR.nextInt(tCivs.size());
                    Game.getCiv(Game.alliancesSpecial.get(allianceID).iLeaderCivID).diplomacy.setRelation(Game.alliancesSpecial.get(allianceID).iLeaderCivID, tCivs.get(withID), Game.getCiv(Game.alliancesSpecial.get(allianceID).iLeaderCivID).diplomacy.getRelation(tCivs.get(withID)) + GameValues.hre.HRE_BONUS_R2_OPINION);
                    Game.getCiv(tCivs.get(withID)).diplomacy.setRelation(tCivs.get(withID), Game.alliancesSpecial.get(allianceID).iLeaderCivID, Game.getCiv(tCivs.get(withID)).diplomacy.getRelation(Game.alliancesSpecial.get(allianceID).iLeaderCivID) + GameValues.hre.HRE_BONUS_R2_OPINION);
                    tCivs.remove(withID);
                }
            }
            else if (reformID == 3) {
                final CivilizationBonuses nCivBonus = new CivilizationBonuses();
                nCivBonus.MaxManpower_Percentage = GameValues.hre.HRE_BONUS_R3;
                nCivBonus.TempTurnID = Game_Calendar.TURN_ID + GameValues.hre.HRE_BONUS_R3_DAYS;
                Game.getCiv(Game.alliancesSpecial.get(allianceID).iLeaderCivID).addCivilizationBonus_Temporary(nCivBonus);
            }
            else if (reformID == 4) {
                final CivilizationBonuses nCivBonus = new CivilizationBonuses();
                nCivBonus.IncomeEconomy = GameValues.hre.HRE_BONUS_R4;
                nCivBonus.TempTurnID = Game_Calendar.TURN_ID + GameValues.hre.HRE_BONUS_R4_DAYS;
                Game.getCiv(Game.alliancesSpecial.get(allianceID).iLeaderCivID).addCivilizationBonus_Temporary(nCivBonus);
            }
            else if (reformID == 5) {
                final CivilizationBonuses nCivBonus = new CivilizationBonuses();
                nCivBonus.ProductionEfficiency = GameValues.hre.HRE_BONUS_R5;
                nCivBonus.TempTurnID = Game_Calendar.TURN_ID + GameValues.hre.HRE_BONUS_R5_DAYS;
                Game.getCiv(Game.alliancesSpecial.get(allianceID).iLeaderCivID).addCivilizationBonus_Temporary(nCivBonus);
            }
            else if (reformID == 7) {
                final CivilizationBonuses nCivBonus = new CivilizationBonuses();
                nCivBonus.ImproveRelationsModifier = GameValues.hre.HRE_BONUS_R7;
                nCivBonus.TempTurnID = Game_Calendar.TURN_ID + GameValues.hre.HRE_BONUS_R7_DAYS;
                Game.getCiv(Game.alliancesSpecial.get(allianceID).iLeaderCivID).addCivilizationBonus_Temporary(nCivBonus);
            }
            else if (reformID == 8) {
                final CivilizationBonuses nCivBonus = new CivilizationBonuses();
                nCivBonus.MonthlyIncome = GameValues.hre.HRE_BONUS_R8;
                nCivBonus.TempTurnID = Game_Calendar.TURN_ID + GameValues.hre.HRE_BONUS_R8_DAYS;
                Game.getCiv(Game.alliancesSpecial.get(allianceID).iLeaderCivID).addCivilizationBonus_Temporary(nCivBonus);
            }
            else if (reformID == 9) {
                final CivilizationBonuses nCivBonus = new CivilizationBonuses();
                nCivBonus.MonthlyLegacy = GameValues.hre.HRE_BONUS_R9;
                nCivBonus.TempTurnID = Game_Calendar.TURN_ID + GameValues.hre.HRE_BONUS_R9_DAYS;
                Game.getCiv(Game.alliancesSpecial.get(allianceID).iLeaderCivID).addCivilizationBonus_Temporary(nCivBonus);
            }
            else if (reformID == 10) {
                final CivilizationBonuses nCivBonus = new CivilizationBonuses();
                nCivBonus.GeneralAttack = (int)GameValues.hre.HRE_BONUS_R10;
                nCivBonus.TempTurnID = Game_Calendar.TURN_ID + GameValues.hre.HRE_BONUS_R10_DAYS;
                Game.getCiv(Game.alliancesSpecial.get(allianceID).iLeaderCivID).addCivilizationBonus_Temporary(nCivBonus);
            }
            else if (reformID == 11) {
                final CivilizationBonuses nCivBonus = new CivilizationBonuses();
                nCivBonus.MonthlyIncome = GameValues.hre.HRE_BONUS_R11;
                nCivBonus.TempTurnID = Game_Calendar.TURN_ID + GameValues.hre.HRE_BONUS_R11_DAYS;
                Game.getCiv(Game.alliancesSpecial.get(allianceID).iLeaderCivID).addCivilizationBonus_Temporary(nCivBonus);
            }
            else if (reformID == 12) {
                final CivilizationBonuses nCivBonus = new CivilizationBonuses();
                nCivBonus.ProvinceMaintenance = GameValues.hre.HRE_BONUS_R12;
                nCivBonus.TempTurnID = Game_Calendar.TURN_ID + GameValues.hre.HRE_BONUS_R12_DAYS;
                Game.getCiv(Game.alliancesSpecial.get(allianceID).iLeaderCivID).addCivilizationBonus_Temporary(nCivBonus);
            }
            else if (reformID == 13) {
                for (int j = 0; j < Game.alliancesSpecial.get(allianceID).firstTier.size(); ++j) {
                    final int nRelation = -(GameValues.hre.HRE_BONUS_R13_OPINION_MIN + Game.oR.nextInt(GameValues.hre.HRE_BONUS_R13_OPINION_RANDOM));
                    Game.getCiv(Game.alliancesSpecial.get(allianceID).iLeaderCivID).diplomacy.setRelation(Game.alliancesSpecial.get(allianceID).iLeaderCivID, Game.alliancesSpecial.get(allianceID).firstTier.get(j), Game.getCiv(Game.alliancesSpecial.get(allianceID).iLeaderCivID).diplomacy.getRelation(Game.alliancesSpecial.get(allianceID).firstTier.get(j)) + nRelation);
                    Game.getCiv(Game.alliancesSpecial.get(allianceID).firstTier.get(j)).diplomacy.setRelation(Game.alliancesSpecial.get(allianceID).firstTier.get(j), Game.alliancesSpecial.get(allianceID).iLeaderCivID, Game.getCiv(Game.alliancesSpecial.get(allianceID).firstTier.get(j)).diplomacy.getRelation(Game.alliancesSpecial.get(allianceID).iLeaderCivID) + nRelation);
                }
            }
            else if (reformID == 14) {
                uniteHRE(allianceID);
                Game.menuManager.setVisibleInGame_PopUp(false);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        final Alliance alliance = Game.alliancesSpecial.get(allianceID);
        ++alliance.iReformsPassed;
        return true;
    }
    
    public static MenuElement_Hover getHoverReform(final int allianceID, final int reformID) {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Reform" + reformID), CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.hre, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (GameValues.hre.HRE_REFORM_COST_REQUIRED_TECH[reformID] >= 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("RequiredTechnology") + ": ", TechnologyTree.lTechnology.get(GameValues.hre.HRE_REFORM_COST_REQUIRED_TECH[reformID]).Name, Game_Calendar.IMG_TECHNOLOGY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (GameValues.hre.HRE_REFORM_COST_GOLD[reformID] > 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Gold") + ": ", CFG.getPrecision2(GameValues.hre.HRE_REFORM_COST_GOLD[reformID], 100), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DiplomacyPoints") + ": ", CFG.getPrecision2(GameValues.hre.HRE_REFORM_COST_DIPLOMACY[reformID], 10), Images.diplomacy, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("LegacyPoints") + ": ", CFG.getPrecision2(GameValues.hre.HRE_REFORM_COST_LEGACY[reformID], 10), Images.legacy, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (reformID == 0) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyIncomeEconomy") + ": ", CFG.getPrecision2(GameValues.hre.HRE_BONUS_R0 * 100.0f, 10) + "%", Game_Calendar.IMG_ECONOMY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Duration") + ": ", Game.lang.get("XDays", GameValues.hre.HRE_BONUS_R0_DAYS), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        else if (reformID == 1) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DevelopInfrastructure") + ": ", Game.lang.get("Provinces") + ": " + GameValues.hre.HRE_BONUS_R1, Images.infrastructure, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        else if (reformID == 2) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Opinion") + ": " + CFG.getPrecision2(GameValues.hre.HRE_BONUS_R2_OPINION, 10) + ": ", Game.lang.get("Civilizations") + ": " + GameValues.hre.HRE_BONUS_R2_CIVS, Images.relations, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        else if (reformID == 3) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", CFG.getPrecision2(GameValues.hre.HRE_BONUS_R3 * 100.0f, 10) + "%", Game_Calendar.IMG_MANPOWER_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Duration") + ": ", Game.lang.get("XDays", GameValues.hre.HRE_BONUS_R3_DAYS), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        else if (reformID == 4) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyIncomeEconomy") + ": ", CFG.getPrecision2(GameValues.hre.HRE_BONUS_R4 * 100.0f, 10) + "%", Game_Calendar.IMG_ECONOMY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Duration") + ": ", Game.lang.get("XDays", GameValues.hre.HRE_BONUS_R4_DAYS), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        else if (reformID == 5) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProductionEfficiency") + ": ", CFG.getPrecision2(GameValues.hre.HRE_BONUS_R5, 10) + "%", Images.goods, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Duration") + ": ", Game.lang.get("XDays", GameValues.hre.HRE_BONUS_R5_DAYS), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        else if (reformID == 7) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ImproveRelationsModifier") + ": ", "+" + CFG.getPrecision2(GameValues.hre.HRE_BONUS_R7, 10) + "%", Images.relations, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Duration") + ": ", Game.lang.get("XDays", GameValues.hre.HRE_BONUS_R7_DAYS), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        else if (reformID == 8) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyIncome") + ": ", CFG.getPrecision2(GameValues.hre.HRE_BONUS_R8, 100), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Duration") + ": ", Game.lang.get("XDays", GameValues.hre.HRE_BONUS_R8_DAYS), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        else if (reformID == 9) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyLegacy") + ": ", CFG.getPrecision2(GameValues.hre.HRE_BONUS_R9, 100), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Duration") + ": ", Game.lang.get("XDays", GameValues.hre.HRE_BONUS_R9_DAYS), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        else if (reformID == 10) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GeneralsAttack") + ": ", CFG.getPrecision2(GameValues.hre.HRE_BONUS_R10, 1), Images.attack, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Duration") + ": ", Game.lang.get("XDays", GameValues.hre.HRE_BONUS_R10_DAYS), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        else if (reformID == 11) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyIncome") + ": ", CFG.getPrecision2(GameValues.hre.HRE_BONUS_R11, 100), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Duration") + ": ", Game.lang.get("XDays", GameValues.hre.HRE_BONUS_R11_DAYS), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        else if (reformID == 12) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProvinceMaintenance") + ": ", CFG.getPrecision2(GameValues.hre.HRE_BONUS_R12, 100) + "%", Images.provinces, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Duration") + ": ", Game.lang.get("XDays", GameValues.hre.HRE_BONUS_R12_DAYS), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        else if (reformID != 13) {
            if (reformID == 14) {
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("Reform14.d"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
        }
        return new MenuElement_Hover(nElements);
    }
    
    public static List<Integer> getCivs(final int allianceID) {
        final List<Integer> out = new ArrayList<Integer>();
        for (int i = Game.alliancesSpecial.get(allianceID).firstTier.size() - 1; i >= 0; --i) {
            out.add(Game.alliancesSpecial.get(allianceID).firstTier.get(i));
        }
        for (int i = Game.alliancesSpecial.get(allianceID).secondTier.size() - 1; i >= 0; --i) {
            out.add(Game.alliancesSpecial.get(allianceID).secondTier.get(i));
        }
        return out;
    }
    
    public static final void loadReformsImages() {
        for (int i = 0; i < HREManager.numOfReforms; ++i) {
            HREManager.reformsIMG.add(new Image(ImageManager.loadTexture_RGB888("ui/" + CFG.getRescouresPath() + "hre/" + i + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
        }
    }
    
    static {
        HREManager.numOfReforms = 15;
        HREManager.reformsIMG = new ArrayList<Image>();
    }
}
