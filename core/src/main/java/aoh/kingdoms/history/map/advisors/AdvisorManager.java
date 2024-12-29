// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.advisors;

import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.map.civilization.CivilizationBonuses;
import aoc.kingdoms.lukasz.jakowski.Game_Ages;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_CourtOptions2;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_CourtOptions;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.Player.Notification.Notification;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.map.RulersManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import java.util.ArrayList;
import java.util.List;

public class AdvisorManager
{
    public List<Integer> advisorsImagesSize;
    
    public AdvisorManager() {
        this.advisorsImagesSize = new ArrayList<Integer>();
    }
    
    public static int getRecruitGoldCost(final int iCivID) {
        return (int)(GameValues.advisors.RECRUIT_ADVISOR_GOLD_COST * Math.max(0.0f, 1.0f + Game.getCiv(iCivID).civBonuses.AdvisorCost + GameValues.civRank.CIV_RANK_ADVISOR_COST[Game.getCiv(iCivID).iCivRankID]));
    }
    
    public static int getRecruitCostLegacy(final int iCivID) {
        return (int)(GameValues.advisors.RECRUIT_ADVISOR_LEGACY_COST * Math.max(0.0f, 1.0f + Game.getCiv(iCivID).civBonuses.AdvisorCost + GameValues.civRank.CIV_RANK_ADVISOR_COST[Game.getCiv(iCivID).iCivRankID]));
    }
    
    public final void update_ChanceOfDeathAdvisor_Administrative(final int iCivID) {
        try {
            if (Game.getCiv(iCivID).advisorAdministration.sName != null && RulersManager.characterDies(iCivID, Game.getCiv(iCivID).advisorAdministration.iYearOfBirth)) {
                this.advisorDied(iCivID, 0);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void update_ChanceOfDeathAdvisor_Economic(final int iCivID) {
        try {
            if (Game.getCiv(iCivID).advisorEconomy.sName != null && RulersManager.characterDies(iCivID, Game.getCiv(iCivID).advisorEconomy.iYearOfBirth)) {
                this.advisorDied(iCivID, 1);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void update_ChanceOfDeathAdvisor_Innovation(final int iCivID) {
        try {
            if (Game.getCiv(iCivID).advisorTechnology.sName != null && RulersManager.characterDies(iCivID, Game.getCiv(iCivID).advisorTechnology.iYearOfBirth)) {
                this.advisorDied(iCivID, 2);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void update_ChanceOfDeathAdvisor_Military(final int iCivID) {
        try {
            if (Game.getCiv(iCivID).advisorMilitary.sName != null && RulersManager.characterDies(iCivID, Game.getCiv(iCivID).advisorMilitary.iYearOfBirth)) {
                this.advisorDied(iCivID, 3);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void advisorDied(final int iCivID, final int iAdvisorTypeID) {
        switch (iAdvisorTypeID) {
            case 0: {
                if (Game.getCiv(iCivID).advisorAdministration.sName != null) {
                    if (iCivID == Game.player.iCivID) {
                        Game.player.addNotification(new Notification(Notification.Notification_Type.ADVISOR_DIED, Game.lang.get("AdvisorDied") + ": " + Game.getCiv(iCivID).advisorAdministration.sName, Images.council, Game_Calendar.TURN_ID, Notification.Notification_BG.RED, Game.player.iCivID));
                        Game.addSimpleTask(new Game.SimpleTask("rebuildCourt") {
                            @Override
                            public void update() {
                                if (Game.menuManager.getVisibleInGame_Court() && InGame_CourtOptions.iActiveID == InGame_CourtOptions2.idCourt) {
                                    Game.menuManager.rebuildInGame_CourtSavePos();
                                    Game.menuManager.setVisibleInGame_Court(true);
                                    InGame_Court.lTime = 0L;
                                }
                            }
                        });
                    }
                    final AdvisorManager advisorManager = Game.advisorManager;
                    updateCivBonuses(Game.getCiv(iCivID).advisorAdministration, iCivID, -1);
                    Game.getCiv(iCivID).advisorAdministration = new Advisor();
                }
                return;
            }
            case 1: {
                if (Game.getCiv(iCivID).advisorEconomy.sName != null) {
                    if (iCivID == Game.player.iCivID) {
                        Game.player.addNotification(new Notification(Notification.Notification_Type.ADVISOR_DIED, Game.lang.get("AdvisorDied") + ": " + Game.getCiv(iCivID).advisorEconomy.sName, Images.council, Game_Calendar.TURN_ID, Notification.Notification_BG.RED, Game.player.iCivID));
                        Game.addSimpleTask(new Game.SimpleTask("rebuildCourt") {
                            @Override
                            public void update() {
                                if (Game.menuManager.getVisibleInGame_Court() && InGame_CourtOptions.iActiveID == InGame_CourtOptions2.idCourt) {
                                    Game.menuManager.rebuildInGame_CourtSavePos();
                                    Game.menuManager.setVisibleInGame_Court(true);
                                    InGame_Court.lTime = 0L;
                                }
                            }
                        });
                    }
                    final AdvisorManager advisorManager2 = Game.advisorManager;
                    updateCivBonuses(Game.getCiv(iCivID).advisorEconomy, iCivID, -1);
                    Game.getCiv(iCivID).advisorEconomy = new Advisor();
                }
                return;
            }
            case 2: {
                if (Game.getCiv(iCivID).advisorTechnology.sName != null) {
                    if (iCivID == Game.player.iCivID) {
                        Game.player.addNotification(new Notification(Notification.Notification_Type.ADVISOR_DIED, Game.lang.get("AdvisorDied") + ": " + Game.getCiv(iCivID).advisorTechnology.sName, Images.council, Game_Calendar.TURN_ID, Notification.Notification_BG.RED, Game.player.iCivID));
                        Game.addSimpleTask(new Game.SimpleTask("rebuildCourt") {
                            @Override
                            public void update() {
                                if (Game.menuManager.getVisibleInGame_Court() && InGame_CourtOptions.iActiveID == InGame_CourtOptions2.idCourt) {
                                    Game.menuManager.rebuildInGame_CourtSavePos();
                                    Game.menuManager.setVisibleInGame_Court(true);
                                    InGame_Court.lTime = 0L;
                                }
                            }
                        });
                    }
                    final AdvisorManager advisorManager3 = Game.advisorManager;
                    updateCivBonuses(Game.getCiv(iCivID).advisorTechnology, iCivID, -1);
                    Game.getCiv(iCivID).advisorTechnology = new Advisor();
                }
                return;
            }
            case 3: {
                if (Game.getCiv(iCivID).advisorMilitary.sName != null) {
                    if (iCivID == Game.player.iCivID) {
                        Game.player.addNotification(new Notification(Notification.Notification_Type.ADVISOR_DIED, Game.lang.get("AdvisorDied") + ": " + Game.getCiv(iCivID).advisorMilitary.sName, Images.council, Game_Calendar.TURN_ID, Notification.Notification_BG.RED, Game.player.iCivID));
                        Game.addSimpleTask(new Game.SimpleTask("rebuildCourt") {
                            @Override
                            public void update() {
                                if (Game.menuManager.getVisibleInGame_Court() && InGame_CourtOptions.iActiveID == InGame_CourtOptions2.idCourt) {
                                    Game.menuManager.rebuildInGame_CourtSavePos();
                                    Game.menuManager.setVisibleInGame_Court(true);
                                    InGame_Court.lTime = 0L;
                                }
                            }
                        });
                    }
                    final AdvisorManager advisorManager4 = Game.advisorManager;
                    updateCivBonuses(Game.getCiv(iCivID).advisorMilitary, iCivID, -1);
                    Game.getCiv(iCivID).advisorMilitary = new Advisor();
                }
            }
            default: {}
        }
    }
    
    public final void loadAdvisors() {
        for (int i = 0; i < RulersManager.iGroupsSize; ++i) {
            int numOfImages = 0;
            try {
                final FileHandle tempFileT = FileManager.loadFile("game/advisors/advisors/" + CFG.getRescouresPath_Short() + i + "/numImages.txt");
                numOfImages = Integer.parseInt(tempFileT.readString());
            }
            catch (final GdxRuntimeException ex) {
                CFG.exceptionStack((Throwable)ex);
            }
            this.advisorsImagesSize.add(numOfImages);
        }
    }
    
    public static String getAdvisorsImgPath() {
        if (!Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).ENABLE_NON_KINGS_IMG && !Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).FORCE_NON_KINGS_IMG) {
            return "advisors/";
        }
        return "advisors2/";
    }
    
    public static String getAdvisorsImgPath(final int iCivID) {
        if ((Game.ideologiesManager.getIdeology(Game.getCiv(iCivID).getIdeologyID()).KingsImages || !Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).ENABLE_NON_KINGS_IMG) && !Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).FORCE_NON_KINGS_IMG) {
            return "advisors/";
        }
        return "advisors2/";
    }
    
    public int getRandomGeneralImage(final int iCivID) {
        if (Game.player.iCivID != iCivID) {
            return Game.oR.nextInt(Game.generalManager.generalsImagesSize.get(Game.getCiv(iCivID).iGroupID));
        }
        final List<Boolean> isUsed = new ArrayList<Boolean>();
        for (int i = 0; i < Game.generalManager.generalsImagesSize.get(Game.getCiv(iCivID).iGroupID); ++i) {
            isUsed.add(false);
        }
        for (int i = 0, iSize = Game.player.civAdvisorsPool_Military.lAdvisors.size(); i < iSize; ++i) {
            isUsed.set(Game.player.civAdvisorsPool_Military.lAdvisors.get(i).imageID, true);
        }
        final List<Integer> out = new ArrayList<Integer>();
        for (int j = 0, iSize2 = Game.generalManager.generalsImagesSize.get(Game.getCiv(iCivID).iGroupID); j < iSize2; ++j) {
            if (!isUsed.get(j)) {
                out.add(j);
            }
        }
        if (out.size() == 0) {
            return Game.oR.nextInt(Game.generalManager.generalsImagesSize.get(Game.getCiv(iCivID).iGroupID));
        }
        return out.get(Game.oR.nextInt(out.size()));
    }
    
    public int getRandomImage(final int iCivID, final int iAdvisorType) {
        final List<Boolean> isUsed = new ArrayList<Boolean>();
        for (int i = 0; i < this.advisorsImagesSize.get(Game.getCiv(iCivID).iGroupID); ++i) {
            isUsed.add(false);
        }
        if (iCivID == Game.player.iCivID) {
            for (int i = 0; i < Game.player.civAdvisorsPool_Administration.lAdvisors.size(); ++i) {
                try {
                    isUsed.set(Game.player.civAdvisorsPool_Administration.lAdvisors.get(i).imageID, true);
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
            for (int i = 0; i < Game.player.civAdvisorsPool_Economy.lAdvisors.size(); ++i) {
                try {
                    isUsed.set(Game.player.civAdvisorsPool_Economy.lAdvisors.get(i).imageID, true);
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
            for (int i = 0; i < Game.player.civAdvisorsPool_Technology.lAdvisors.size(); ++i) {
                try {
                    isUsed.set(Game.player.civAdvisorsPool_Technology.lAdvisors.get(i).imageID, true);
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
        }
        if (Game.getCiv(iCivID).advisorAdministration.sName != null) {
            try {
                isUsed.set(Game.getCiv(iCivID).advisorAdministration.imageID, true);
            }
            catch (final Exception ex2) {
                CFG.exceptionStack(ex2);
            }
        }
        if (Game.getCiv(iCivID).advisorEconomy.sName != null) {
            try {
                isUsed.set(Game.getCiv(iCivID).advisorEconomy.imageID, true);
            }
            catch (final Exception ex2) {
                CFG.exceptionStack(ex2);
            }
        }
        if (Game.getCiv(iCivID).advisorTechnology.sName != null) {
            try {
                isUsed.set(Game.getCiv(iCivID).advisorTechnology.imageID, true);
            }
            catch (final Exception ex2) {
                CFG.exceptionStack(ex2);
            }
        }
        final List<Integer> out = new ArrayList<Integer>();
        for (int j = 0, iSize = this.advisorsImagesSize.get(Game.getCiv(iCivID).iGroupID); j < iSize; ++j) {
            if (!isUsed.get(j)) {
                out.add(j);
            }
        }
        if (out.size() != 0) {
            return out.get(Game.oR.nextInt(out.size()));
        }
        if (iCivID != Game.player.iCivID) {
            return Game.oR.nextInt(this.advisorsImagesSize.get(Game.getCiv(iCivID).iGroupID));
        }
        for (int j = 0; j < this.advisorsImagesSize.get(Game.getCiv(iCivID).iGroupID); ++j) {
            isUsed.set(j, false);
        }
        if (iAdvisorType == 0) {
            for (int j = 0, iSize = Game.player.civAdvisorsPool_Administration.lAdvisors.size(); j < iSize; ++j) {
                isUsed.set(Game.player.civAdvisorsPool_Administration.lAdvisors.get(j).imageID, true);
            }
        }
        else if (iAdvisorType == 1) {
            for (int j = 0, iSize = Game.player.civAdvisorsPool_Economy.lAdvisors.size(); j < iSize; ++j) {
                isUsed.set(Game.player.civAdvisorsPool_Economy.lAdvisors.get(j).imageID, true);
            }
        }
        else if (iAdvisorType == 2) {
            for (int j = 0, iSize = Game.player.civAdvisorsPool_Technology.lAdvisors.size(); j < iSize; ++j) {
                isUsed.set(Game.player.civAdvisorsPool_Technology.lAdvisors.get(j).imageID, true);
            }
        }
        for (int j = 0, iSize = this.advisorsImagesSize.get(Game.getCiv(iCivID).iGroupID); j < iSize; ++j) {
            if (!isUsed.get(j)) {
                out.add(j);
            }
        }
        if (out.size() == 0) {
            return Game.oR.nextInt(this.advisorsImagesSize.get(Game.getCiv(iCivID).iGroupID));
        }
        return out.get(Game.oR.nextInt(out.size()));
    }
    
    public static String getAdvisorGroupName(final int iAdvisorTypeID) {
        switch (iAdvisorTypeID) {
            case 0: {
                return Game.lang.get(GameValues.court.ADVISOR_NAME_ADMINISTRATIVE);
            }
            case 1: {
                return Game.lang.get(GameValues.court.ADVISOR_NAME_ECONOMIC);
            }
            case 2: {
                return Game.lang.get(GameValues.court.ADVISOR_NAME_INNOVATION);
            }
            case 3: {
                return Game.lang.get(GameValues.court.ADVISOR_NAME_MILITARY);
            }
            default: {
                return Game.lang.get(GameValues.court.ADVISOR_NAME_DIPLOMATIC);
            }
        }
    }
    
    public static Advisor getAdvisor(final int iAdvisorTypeID) {
        return getAdvisor(Game.player.iCivID, iAdvisorTypeID);
    }
    
    public static Advisor getAdvisor(final int iCivID, final int iAdvisorTypeID) {
        switch (iAdvisorTypeID) {
            case 0: {
                return Game.getCiv(iCivID).advisorAdministration;
            }
            case 1: {
                return Game.getCiv(iCivID).advisorEconomy;
            }
            case 2: {
                return Game.getCiv(iCivID).advisorTechnology;
            }
            default: {
                return Game.getCiv(iCivID).advisorMilitary;
            }
        }
    }
    
    public static boolean advisorIsRecruited(final int iCivID, final int iAdvisorTypeID) {
        switch (iAdvisorTypeID) {
            case 0: {
                return Game.getCiv(iCivID).advisorAdministration.sName != null;
            }
            case 1: {
                return Game.getCiv(iCivID).advisorEconomy.sName != null;
            }
            case 2: {
                return Game.getCiv(iCivID).advisorTechnology.sName != null;
            }
            default: {
                return Game.getCiv(iCivID).advisorMilitary.sName != null;
            }
        }
    }
    
    public static final void updateCivBonuses(final Advisor nAdvisor, final int iCivID, final int mod) {
        final CivilizationBonuses civBonuses = Game.getCiv(iCivID).civBonuses;
        civBonuses.TaxEfficiency += nAdvisor.TaxEfficiency * mod;
        final CivilizationBonuses civBonuses2 = Game.getCiv(iCivID).civBonuses;
        civBonuses2.ProvinceMaintenance += nAdvisor.ProvinceMaintenance * mod;
        if (nAdvisor.GrowthRate != 0.0f) {
            final CivilizationBonuses civBonuses3 = Game.getCiv(iCivID).civBonuses;
            civBonuses3.GrowthRate += nAdvisor.GrowthRate * mod;
            Game.getCiv(iCivID).updateResearchPerMonth();
            Game.gameThread.addCivUpdateTotalIncomePerMonth(iCivID);
        }
        final CivilizationBonuses civBonuses4 = Game.getCiv(iCivID).civBonuses;
        civBonuses4.ProductionEfficiency += nAdvisor.ProductionEfficiency * mod;
        final CivilizationBonuses civBonuses5 = Game.getCiv(iCivID).civBonuses;
        civBonuses5.IncomeProduction += nAdvisor.IncomeProduction * mod;
        if (nAdvisor.MonthlyLegacy != 0.0f) {
            Game.gameThread.addCivUpdateLegacyPerMonth(iCivID);
        }
        final CivilizationBonuses civBonuses6 = Game.getCiv(iCivID).civBonuses;
        civBonuses6.MonthlyLegacy += nAdvisor.MonthlyLegacy * mod;
        if (nAdvisor.MaxManpower != 0.0f) {
            Game.gameThreadTurns.addCivUpdateMaxManpower(iCivID);
        }
        final CivilizationBonuses civBonuses7 = Game.getCiv(iCivID).civBonuses;
        civBonuses7.MaxManpower += nAdvisor.MaxManpower * mod;
        if (nAdvisor.ArmyMaintenance != 0.0f) {
            final CivilizationBonuses civBonuses8 = Game.getCiv(iCivID).civBonuses;
            civBonuses8.ArmyMaintenance += nAdvisor.ArmyMaintenance * mod;
            Game.gameThread.addCivUpdateArmyMaintenance(iCivID);
        }
        final CivilizationBonuses civBonuses9 = Game.getCiv(iCivID).civBonuses;
        civBonuses9.RecruitmentTime += nAdvisor.RecruitmentTime * mod;
        final CivilizationBonuses civBonuses10 = Game.getCiv(iCivID).civBonuses;
        civBonuses10.RecruitArmyCost += nAdvisor.RecruitArmyCost * mod;
        if (nAdvisor.Research != 0.0f) {
            Game.gameThread.addCivUpdateResearchPerMonth(iCivID);
            Game.gameThread.addCivUpdateTotalIncomePerMonth(iCivID);
        }
        final CivilizationBonuses civBonuses11 = Game.getCiv(iCivID).civBonuses;
        civBonuses11.ResearchPoints += nAdvisor.Research * mod;
        final CivilizationBonuses civBonuses12 = Game.getCiv(iCivID).civBonuses;
        civBonuses12.ConstructionCost += nAdvisor.ConstructionCost * mod;
        final CivilizationBonuses civBonuses13 = Game.getCiv(iCivID).civBonuses;
        civBonuses13.AdministrationBuildingsCost += nAdvisor.AdministrationBuildingsCost * mod;
        final CivilizationBonuses civBonuses14 = Game.getCiv(iCivID).civBonuses;
        civBonuses14.EconomyBuildingsCost += nAdvisor.EconomyBuildingsCost * mod;
        final CivilizationBonuses civBonuses15 = Game.getCiv(iCivID).civBonuses;
        civBonuses15.MilitaryBuildingsCost += nAdvisor.MilitaryBuildingsCost * mod;
        final CivilizationBonuses civBonuses16 = Game.getCiv(iCivID).civBonuses;
        civBonuses16.ConstructionTime += nAdvisor.ConstructionTime * mod;
        final CivilizationBonuses civBonuses17 = Game.getCiv(iCivID).civBonuses;
        civBonuses17.InvestInEconomyCost += nAdvisor.InvestInEconomyCost * mod;
        final CivilizationBonuses civBonuses18 = Game.getCiv(iCivID).civBonuses;
        civBonuses18.IncreaseManpowerCost += nAdvisor.IncreaseManpowerCost * mod;
        final CivilizationBonuses civBonuses19 = Game.getCiv(iCivID).civBonuses;
        civBonuses19.IncreaseTaxEfficiencyCost += nAdvisor.IncreaseTaxEfficiencyCost * mod;
        final CivilizationBonuses civBonuses20 = Game.getCiv(iCivID).civBonuses;
        civBonuses20.IncreaseGrowthRateCost += nAdvisor.IncreaseGrowthRateCost * mod;
        final CivilizationBonuses civBonuses21 = Game.getCiv(iCivID).civBonuses;
        civBonuses21.DevelopInfrastructureCost += nAdvisor.DevelopInfrastructureCost * mod;
        final CivilizationBonuses civBonuses22 = Game.getCiv(iCivID).civBonuses;
        civBonuses22.GeneralAttack += (int)(nAdvisor.GeneralAttack * mod);
        final CivilizationBonuses civBonuses23 = Game.getCiv(iCivID).civBonuses;
        civBonuses23.GeneralDefense += (int)(nAdvisor.GeneralDefense * mod);
        final CivilizationBonuses civBonuses24 = Game.getCiv(iCivID).civBonuses;
        civBonuses24.UnitsAttack += (int)(nAdvisor.UnitsAttack * mod);
        final CivilizationBonuses civBonuses25 = Game.getCiv(iCivID).civBonuses;
        civBonuses25.UnitsDefense += (int)(nAdvisor.UnitsDefense * mod);
        if (nAdvisor.RegimentsLimit != 0) {
            final CivilizationBonuses civBonuses26 = Game.getCiv(iCivID).civBonuses;
            civBonuses26.RegimentsLimit += nAdvisor.RegimentsLimit * mod;
            Game.getCiv(iCivID).updateRegimentsLimit();
        }
        final CivilizationBonuses civBonuses27 = Game.getCiv(iCivID).civBonuses;
        civBonuses27.MaxMorale += nAdvisor.MaxMorale * mod;
        final CivilizationBonuses civBonuses28 = Game.getCiv(iCivID).civBonuses;
        civBonuses28.ArmyMovementSpeed += nAdvisor.ArmyMovementSpeed * mod;
        final CivilizationBonuses civBonuses29 = Game.getCiv(iCivID).civBonuses;
        civBonuses29.SiegeEffectiveness += nAdvisor.SiegeEffectiveness * mod;
        final CivilizationBonuses civBonuses30 = Game.getCiv(iCivID).civBonuses;
        civBonuses30.ImproveRelationsModifier += nAdvisor.ImproveRelationsModifier * mod;
        final CivilizationBonuses civBonuses31 = Game.getCiv(iCivID).civBonuses;
        civBonuses31.LoanInterest += nAdvisor.LoanInterest * mod;
        final CivilizationBonuses civBonuses32 = Game.getCiv(iCivID).civBonuses;
        civBonuses32.CoreCost += nAdvisor.CoreCost * mod;
        final CivilizationBonuses civBonuses33 = Game.getCiv(iCivID).civBonuses;
        civBonuses33.ReligionCost += nAdvisor.ReligionCost * mod;
        Game.getCiv(iCivID).updateProvincesIncomeAndExpenses();
    }
    
    public static final int getAdvisorMaxLevel(final int iCivID) {
        return GameValues.advisors.ADVISOR_MAX_LVL + Game.getCiv(iCivID).civBonuses.AdvisorMaxLevel;
    }
    
    public static final int getAdvisorPromoteCost(final int iCivID, final int iLevel) {
        return (int)(GameValues.advisors.ADVISOR_PROMOTE_COST_PER_LEVEL * iLevel);
    }
    
    public static final int getAdvisorPromoteCostLegacy(final int iCivID, final int iLevel) {
        return (int)(GameValues.advisors.ADVISOR_PROMOTE_COST_LEGACY_PER_LEVEL * iLevel);
    }
    
    public static final boolean promoteAdvisor(final int iCivID, final int iAdvisorTypeID, final boolean free) {
        if (getAdvisor(iCivID, iAdvisorTypeID) != null && (getAdvisor(iCivID, iAdvisorTypeID).iLevel < getAdvisorMaxLevel(iCivID) || free) && advisorIsRecruited(iCivID, iAdvisorTypeID)) {
            if (!free) {
                if (Game.getCiv(iCivID).fGold < getAdvisorPromoteCost(iCivID, getAdvisor(iCivID, iAdvisorTypeID).iLevel)) {
                    return false;
                }
                if (Game.getCiv(iCivID).fLegacy < getAdvisorPromoteCostLegacy(iCivID, getAdvisor(iCivID, iAdvisorTypeID).iLevel)) {
                    return false;
                }
                final Civilization civ = Game.getCiv(iCivID);
                civ.fGold -= getAdvisorPromoteCost(iCivID, getAdvisor(iCivID, iAdvisorTypeID).iLevel);
                final Civilization civ2 = Game.getCiv(iCivID);
                civ2.fLegacy -= getAdvisorPromoteCostLegacy(iCivID, getAdvisor(iCivID, iAdvisorTypeID).iLevel);
            }
            updateCivBonuses(getAdvisor(iCivID, iAdvisorTypeID), iCivID, -1);
            final Advisor advisor = getAdvisor(iCivID, iAdvisorTypeID);
            ++advisor.iLevel;
            try {
                final float mod = 1.0f + GameValues.advisors.ADVISOR_BONUSES_UPGRADE_PER_LEVEL;
                getAdvisor(iCivID, iAdvisorTypeID).TaxEfficiency *= mod;
                getAdvisor(iCivID, iAdvisorTypeID).ProvinceMaintenance *= mod;
                getAdvisor(iCivID, iAdvisorTypeID).GrowthRate *= mod;
                getAdvisor(iCivID, iAdvisorTypeID).ProductionEfficiency *= mod;
                getAdvisor(iCivID, iAdvisorTypeID).IncomeProduction *= mod;
                getAdvisor(iCivID, iAdvisorTypeID).MonthlyLegacy *= mod;
                getAdvisor(iCivID, iAdvisorTypeID).MaxManpower *= mod;
                getAdvisor(iCivID, iAdvisorTypeID).ArmyMaintenance *= mod;
                getAdvisor(iCivID, iAdvisorTypeID).RecruitmentTime *= mod;
                getAdvisor(iCivID, iAdvisorTypeID).RecruitArmyCost *= mod;
                getAdvisor(iCivID, iAdvisorTypeID).Research *= mod;
                getAdvisor(iCivID, iAdvisorTypeID).ConstructionCost *= mod;
                getAdvisor(iCivID, iAdvisorTypeID).AdministrationBuildingsCost *= mod;
                getAdvisor(iCivID, iAdvisorTypeID).EconomyBuildingsCost *= mod;
                getAdvisor(iCivID, iAdvisorTypeID).MilitaryBuildingsCost *= mod;
                getAdvisor(iCivID, iAdvisorTypeID).ConstructionTime *= mod;
                getAdvisor(iCivID, iAdvisorTypeID).DevelopInfrastructureCost *= mod;
                getAdvisor(iCivID, iAdvisorTypeID).IncreaseTaxEfficiencyCost *= mod;
                getAdvisor(iCivID, iAdvisorTypeID).IncreaseGrowthRateCost *= mod;
                getAdvisor(iCivID, iAdvisorTypeID).InvestInEconomyCost *= mod;
                getAdvisor(iCivID, iAdvisorTypeID).IncreaseManpowerCost *= mod;
                if (getAdvisor(iCivID, iAdvisorTypeID).GeneralAttack != 0.0f) {
                    getAdvisor(iCivID, iAdvisorTypeID).GeneralAttack = (float)(int)Math.max(getAdvisor(iCivID, iAdvisorTypeID).GeneralAttack + 1.0f, Math.ceil(getAdvisor(iCivID, iAdvisorTypeID).GeneralAttack * mod));
                }
                if (getAdvisor(iCivID, iAdvisorTypeID).GeneralDefense != 0.0f) {
                    getAdvisor(iCivID, iAdvisorTypeID).GeneralDefense = (float)(int)Math.max(getAdvisor(iCivID, iAdvisorTypeID).GeneralDefense + 1.0f, Math.ceil(getAdvisor(iCivID, iAdvisorTypeID).GeneralDefense * mod));
                }
                if (getAdvisor(iCivID, iAdvisorTypeID).UnitsAttack != 0.0f) {
                    getAdvisor(iCivID, iAdvisorTypeID).UnitsAttack = (float)(int)Math.max(getAdvisor(iCivID, iAdvisorTypeID).UnitsAttack + 1.0f, Math.ceil(getAdvisor(iCivID, iAdvisorTypeID).UnitsAttack * mod));
                }
                if (getAdvisor(iCivID, iAdvisorTypeID).UnitsDefense != 0.0f) {
                    getAdvisor(iCivID, iAdvisorTypeID).UnitsDefense = (float)(int)Math.max(getAdvisor(iCivID, iAdvisorTypeID).UnitsDefense + 1.0f, Math.ceil(getAdvisor(iCivID, iAdvisorTypeID).UnitsDefense * mod));
                }
                if (getAdvisor(iCivID, iAdvisorTypeID).RegimentsLimit != 0) {
                    getAdvisor(iCivID, iAdvisorTypeID).RegimentsLimit = (int)Math.max(getAdvisor(iCivID, iAdvisorTypeID).RegimentsLimit + 1, Math.ceil(getAdvisor(iCivID, iAdvisorTypeID).RegimentsLimit * mod));
                }
                getAdvisor(iCivID, iAdvisorTypeID).MaxMorale *= mod;
                getAdvisor(iCivID, iAdvisorTypeID).ArmyMovementSpeed *= mod;
                getAdvisor(iCivID, iAdvisorTypeID).SiegeEffectiveness *= mod;
                getAdvisor(iCivID, iAdvisorTypeID).ImproveRelationsModifier *= mod;
                getAdvisor(iCivID, iAdvisorTypeID).LoanInterest *= mod;
                getAdvisor(iCivID, iAdvisorTypeID).CoreCost *= mod;
                getAdvisor(iCivID, iAdvisorTypeID).ReligionCost *= mod;
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
            updateCivBonuses(getAdvisor(iCivID, iAdvisorTypeID), iCivID, 1);
            return true;
        }
        return false;
    }
}
