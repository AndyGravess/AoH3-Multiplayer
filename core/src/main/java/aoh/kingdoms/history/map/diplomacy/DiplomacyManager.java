// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.diplomacy;

import aoc.kingdoms.lukasz.jakowski.AI.AI_PeaceTreaty;
import aoc.kingdoms.lukasz.jakowski.Player.MessageTypes.PMessageGuarantee;
import aoc.kingdoms.lukasz.jakowski.Player.MessageTypes.PMessageMilitaryAccessRefused;
import aoc.kingdoms.lukasz.jakowski.Player.MessageTypes.PMessageDemandMilitaryAccess;
import aoc.kingdoms.lukasz.jakowski.Player.MessageTypes.PMessageNonAggressionPact;
import aoc.kingdoms.lukasz.jakowski.Player.MessageTypes.PMessageDefensivePact;
import aoc.kingdoms.lukasz.jakowski.Steam.SteamAchievementsManager;
import aoc.kingdoms.lukasz.jakowski.Player.MessageTypes.PMessageAlliance;
import aoc.kingdoms.lukasz.jakowski.Player.MessageTypes.PMessageGift;
import aoc.kingdoms.lukasz.jakowski.Player.MessageTypes.PMessageInsult;
import aoc.kingdoms.lukasz.map.allianceHRE.Alliance;
import java.util.Map;
import aoc.kingdoms.lukasz.map.civilization.CivilizationsNeighbors;
import java.util.Iterator;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.map.province.ProvinceDraw;
import aoc.kingdoms.lukasz.jakowski.AI.AI_Manager;
import aoc.kingdoms.lukasz.map.war.WarCivilization;
import aoc.kingdoms.lukasz.map.war.War;
import aoc.kingdoms.lukasz.jakowski.Player.MessageTypes.PMessageCallToWar;
import aoc.kingdoms.lukasz.map.province.ProvinceBorderManager;
import aoc.kingdoms.lukasz.menusInGame.Info.InGame_Info;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.Player.MessageTypes.PMessage;
import aoc.kingdoms.lukasz.jakowski.Player.MessageTypes.PMessageWar;
import aoc.kingdoms.lukasz.jakowski.desktop.DesktopLauncher;
import aoc.kingdoms.lukasz.map.war.WarManager;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import java.util.List;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.CFG;
import com.badlogic.gdx.graphics.Color;

public class DiplomacyManager
{
    public static final Color COLOR_WAR;
    public static final Color COLOR_INSULT;
    public static final Color COLOR_ALLIANCE;
    public static final Color COLOR_DEFENSIVE_PACT;
    public static final Color COLOR_NON_AGGRESSION_PACT;
    public static final Color COLOR_MILITARY_ACCESS;
    public static final Color COLOR_GUARANTEE;
    public static final Color COLOR_PEACE;
    public static final Color COLOR_SPY;
    public static final Color COLOR_RED;
    public static final Color COLOR_NEUTRAL;
    public static final Color COLOR_GREEN;
    public static final Color COLOR_BATTLE;
    public static long lTime;
    public static final int ANIMATION_TIME = 500;
    public static boolean inAnimation;
    public static float fAnimationPerc;
    
    public static final void updateInAnimation() {
        if (DiplomacyManager.lTime + 500L >= CFG.currentTimeMillis) {
            DiplomacyManager.inAnimation = true;
            DiplomacyManager.fAnimationPerc = Math.min(1.0f, (CFG.currentTimeMillis - DiplomacyManager.lTime) / 500.0f);
        }
        else {
            DiplomacyManager.inAnimation = false;
        }
    }
    
    public static final void updateAnimationTime() {
        DiplomacyManager.lTime = CFG.currentTimeMillis;
        DiplomacyManager.inAnimation = true;
    }
    
    public static String getOpinion_String(final int iOpinion) {
        if (iOpinion <= getRelationAtWar()) {
            return Game.lang.get("AtWar");
        }
        if (iOpinion < GameValues.diplomacy.DIPLOMACY_RELATION_UNFAVORABLE) {
            return Game.lang.get("Unfavorable");
        }
        if (iOpinion < GameValues.diplomacy.DIPLOMACY_RELATION_STRAINED) {
            return Game.lang.get("Strained");
        }
        if (iOpinion < GameValues.diplomacy.DIPLOMACY_RELATION_DETACHED) {
            return Game.lang.get("Detached");
        }
        if (iOpinion < GameValues.diplomacy.DIPLOMACY_RELATION_NEUTRAL) {
            return Game.lang.get("Neutral");
        }
        if (iOpinion < GameValues.diplomacy.DIPLOMACY_RELATION_WARM) {
            return Game.lang.get("Warm");
        }
        if (iOpinion < GameValues.diplomacy.DIPLOMACY_RELATION_COOPERATIVE) {
            return Game.lang.get("Cooperative");
        }
        return Game.lang.get("Supportive");
    }
    
    public static Color getOpinion_Color(final int iOpinion) {
        if (iOpinion < -15) {
            return DiplomacyManager.COLOR_RED;
        }
        if (iOpinion < 15) {
            return DiplomacyManager.COLOR_NEUTRAL;
        }
        return DiplomacyManager.COLOR_GREEN;
    }
    
    public static final float getRelationImprove(final int iFromCivID, final int iCivB) {
        return (GameValues.diplomacy.DIPLOMACY_IMPROVE_RELATIONS_VALUE + GameValues.diplomacy.DIPLOMACY_IMPROVE_RELATIONS_VALUE_PRESTIGE * Math.min(1.0f, Game.getCiv(iFromCivID).iCivRankScore / Game.getCiv(iCivB).iCivRankScore)) * (1.0f + Game.getCiv(iFromCivID).civBonuses.ImproveRelationsModifier / 100.0f);
    }
    
    public static final float getRelationDamage(final int iFromCivID, final int iCivB) {
        return GameValues.diplomacy.DIPLOMACY_DAMAGE_RELATIONS_VALUE;
    }
    
    public static final String getInsult() {
        switch (Game.oR.nextInt(5)) {
            case 0: {
                return Game.lang.get("WeWillClaimBothYourLivesAndYourFreedom");
            }
            case 1: {
                return Game.lang.get("YourCivilizationWillCrumbleAndWeShallDanceUponTheRuinsOfYourFormerGreatness");
            }
            case 2: {
                return Game.lang.get("WellSnatchAwayYourMightLeavingYourCivilizationInTheShadowsOfItsFormerSelf");
            }
            case 3: {
                return Game.lang.get("PrepareForTheDemiseOfYourSovereigntyForWeWillSeizeIt");
            }
            default: {
                return Game.lang.get("YourStandingWillBeReducedToRuinsAndWeShallRevelInYourDownfall");
            }
        }
    }
    
    public static final String getInsult(final int id) {
        switch (id % 5) {
            case 0: {
                return Game.lang.get("WeWillClaimBothYourLivesAndYourFreedom");
            }
            case 1: {
                return Game.lang.get("YourCivilizationWillCrumbleAndWeShallDanceUponTheRuinsOfYourFormerGreatness");
            }
            case 2: {
                return Game.lang.get("WellSnatchAwayYourMightLeavingYourCivilizationInTheShadowsOfItsFormerSelf");
            }
            case 3: {
                return Game.lang.get("PrepareForTheDemiseOfYourSovereigntyForWeWillSeizeIt");
            }
            default: {
                return Game.lang.get("YourStandingWillBeReducedToRuinsAndWeShallRevelInYourDownfall");
            }
        }
    }
    
    public static final String getWarMessage() {
        switch (Game.oR.nextInt(7)) {
            case 0: {
                return Game.lang.get("WitnessTheMightOfMyForcesAsWeDeclareWarUponYouForYourWeaknessCannotWithstandOurStrength");
            }
            case 1: {
                return Game.lang.get("BraceYourselfForIDeclareWarUponYouToAssertMyRightfulAuthorityOverYourTerritories");
            }
            case 2: {
                return Game.lang.get("ThisWarIsTheDestinyYouCannotEscapeAsWeRiseToCrushYourFeebleResistance");
            }
            case 3: {
                return Game.lang.get("YourLandsWillBeReshapedUnderOurBannerAsWarIsDeclaredToSecureOurRightfulDominion");
            }
            case 4: {
                return Game.lang.get("PrepareForTheMarchOfOurArmiesAsWeDeclareWarToAssertOurDominanceOverYourLands");
            }
            case 5: {
                return Game.lang.get("TheDrumsOfWarBeatLoudlyAnnouncingTheBeginningOfYourDemiseUnderOurRule");
            }
            default: {
                return Game.lang.get("TheBannersOfWarUnfurlMarkingTheBeginningOfTheEndForYourInsignificantCivilization");
            }
        }
    }
    
    public static final String getWarMessage(final int id) {
        switch (id % 7) {
            case 0: {
                return Game.lang.get("WitnessTheMightOfMyForcesAsWeDeclareWarUponYouForYourWeaknessCannotWithstandOurStrength");
            }
            case 1: {
                return Game.lang.get("BraceYourselfForIDeclareWarUponYouToAssertMyRightfulAuthorityOverYourTerritories");
            }
            case 2: {
                return Game.lang.get("ThisWarIsTheDestinyYouCannotEscapeAsWeRiseToCrushYourFeebleResistance");
            }
            case 3: {
                return Game.lang.get("YourLandsWillBeReshapedUnderOurBannerAsWarIsDeclaredToSecureOurRightfulDominion");
            }
            case 4: {
                return Game.lang.get("PrepareForTheMarchOfOurArmiesAsWeDeclareWarToAssertOurDominanceOverYourLands");
            }
            case 5: {
                return Game.lang.get("TheDrumsOfWarBeatLoudlyAnnouncingTheBeginningOfYourDemiseUnderOurRule");
            }
            default: {
                return Game.lang.get("TheBannersOfWarUnfurlMarkingTheBeginningOfTheEndForYourInsignificantCivilization");
            }
        }
    }
    
    public static final void updateAfterRemoveCiv(final int iCivID) {
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            try {
                Game.getCiv(i).diplomacy.updateRelationsAfterRemoveCiv(iCivID);
                Game.getCiv(i).diplomacy.updateTruceAfterRemoveCiv(iCivID);
                Game.getCiv(i).diplomacy.updateDefensivePactAfterRemoveCiv(iCivID);
                Game.getCiv(i).diplomacy.updateNonAggressionPactAfterRemoveCiv(iCivID);
                Game.getCiv(i).diplomacy.updateGuaranteeAfterRemoveCiv(iCivID);
                Game.getCiv(i).diplomacy.updateGuaranteeByCivIDAfterRemoveCiv(iCivID);
                Game.getCiv(i).diplomacy.updateAllianceAfterRemoveCiv(iCivID);
                Game.getCiv(i).diplomacy.updateMilitaryAccessAfterRemoveCiv(iCivID);
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
    }
    
    public static boolean willJoinWar(final int iCivID, final int iToWarOfCivID) {
        return Game.getCiv(iCivID).getPuppetOfCivID() == iToWarOfCivID;
    }
    
    public static final void declareWar(final int iCivA, final int iCivB) {
        try {
            declareWar(iCivA, iCivB, false, new ArrayList<Integer>());
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final boolean declareWar(final int civA, final int civWarDeclaredOn, final boolean free, final List<Integer> callToWar) {
        return declareWar(civA, civWarDeclaredOn, free, callToWar, false);
    }
    
    public static final boolean declareWar(final int civA, final int civWarDeclaredOn, final boolean free, final List<Integer> callToWar, final boolean isCoalition) {
        if (civA == civWarDeclaredOn) {
            return false;
        }
        if (Game.getCiv(civA).getNumOfProvinces() <= 0 || Game.getCiv(civWarDeclaredOn).getNumOfProvinces() <= 0) {
            return false;
        }
        if (!free) {
            if (Game.getCiv(civA).diplomacy.getRelation(civWarDeclaredOn) > GameValues.war.RELATIONS_TO_DECLARE_WAR) {
                return false;
            }
            if (Game.getCiv(civA).diplomacy.haveNonAggressionPact(civWarDeclaredOn)) {
                return false;
            }
            if (Game.getCiv(civA).fDiplomacy < GameValues.diplomacy.DIPLOMACY_DECLARE_WAR_COST) {
                return false;
            }
            if (Game.getCiv(civA).diplomacy.haveAlliance(civWarDeclaredOn) || Game.getCiv(civWarDeclaredOn).diplomacy.haveAlliance(civA)) {
                return false;
            }
            final Civilization civ3;
            final Civilization civ2;
            final Civilization civilization10;
            final Civilization civilization9;
            final Civilization civilization8;
            final Civilization civilization11;
            final Civilization civilization7 = civilization11 = (civilization8 = (civilization9 = (civilization10 = (civ2 = (civ3 = Game.getCiv(civA))))));
            civilization11.fDiplomacy -= GameValues.diplomacy.DIPLOMACY_DECLARE_WAR_COST;
        }
        if (Game.getCiv(civA).diplomacy.truce.containsKey(civWarDeclaredOn)) {
            if (Game.getCiv(civA).diplomacy.truce.get(civWarDeclaredOn).iTurnID >= Game_Calendar.TURN_ID && !free) {
                return false;
            }
            Game.getCiv(civA).diplomacy.truce.remove(civWarDeclaredOn);
            Game.getCiv(civWarDeclaredOn).diplomacy.truce.remove(civA);
        }
        final boolean conquerVassal = Game.getCiv(civWarDeclaredOn).getPuppetOfCivID() == civA;
        try {
            if (Game.getCiv(civA).diplomacy.alliance.containsKey(civWarDeclaredOn)) {
                Game.getCiv(civA).diplomacy.alliance.remove(civWarDeclaredOn);
            }
            if (Game.getCiv(civWarDeclaredOn).diplomacy.alliance.containsKey(civA)) {
                Game.getCiv(civWarDeclaredOn).diplomacy.alliance.remove(civA);
            }
            if (Game.getCiv(civA).diplomacy.defensivePact.containsKey(civWarDeclaredOn)) {
                Game.getCiv(civA).diplomacy.defensivePact.remove(civWarDeclaredOn);
            }
            if (Game.getCiv(civWarDeclaredOn).diplomacy.defensivePact.containsKey(civA)) {
                Game.getCiv(civWarDeclaredOn).diplomacy.defensivePact.remove(civA);
            }
            if (Game.getCiv(civA).diplomacy.nonAggressionPact.containsKey(civWarDeclaredOn)) {
                Game.getCiv(civA).diplomacy.nonAggressionPact.remove(civWarDeclaredOn);
            }
            if (Game.getCiv(civWarDeclaredOn).diplomacy.nonAggressionPact.containsKey(civA)) {
                Game.getCiv(civWarDeclaredOn).diplomacy.nonAggressionPact.remove(civA);
            }
            if (Game.getCiv(civA).diplomacy.guarantee.containsKey(civWarDeclaredOn)) {
                Game.getCiv(civA).diplomacy.guarantee.remove(civWarDeclaredOn);
            }
            if (Game.getCiv(civWarDeclaredOn).diplomacy.guarantee.containsKey(civA)) {
                Game.getCiv(civWarDeclaredOn).diplomacy.guarantee.remove(civA);
            }
            if (Game.getCiv(civA).diplomacy.militaryAccess.containsKey(civWarDeclaredOn)) {
                Game.getCiv(civA).diplomacy.militaryAccess.remove(civWarDeclaredOn);
            }
            if (Game.getCiv(civWarDeclaredOn).diplomacy.militaryAccess.containsKey(civA)) {
                Game.getCiv(civWarDeclaredOn).diplomacy.militaryAccess.remove(civA);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            Game.getCiv(civA).addAggressiveExpansion(GameValues.aggressiveExpansion.AE_DECLARE_WAR);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            declareWar_UpdateRelation(civA, civWarDeclaredOn);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        final String warKey = WarManager.addWar(civA, civWarDeclaredOn, conquerVassal, isCoalition);
        final ArrayList<String> list2 = new ArrayList<String>();
        for (final int value : callToWar) {
            list2.add(String.valueOf(value));
        }
        final String listString2 = "[" + String.join(", ", list2) + "]";
        DesktopLauncher.SMS("War: " + civA + " " + civWarDeclaredOn + " " + WarManager.getWarKey(civA, civWarDeclaredOn) + " " + listString2);
        if (warKey == null) {
            return false;
        }
        List<Integer> alliesA = new ArrayList<Integer>();
        try {
            alliesA = declareWar_AlliesDefender(civWarDeclaredOn, civA);
            if (alliesA.size() > 0 && warKey != null) {
                for (int i = 0; i < alliesA.size(); ++i) {
                    declareWar_UpdateRelation(civWarDeclaredOn, alliesA.get(i));
                    if (civA == Game.player.iCivID) {
                        Game.getCiv(civWarDeclaredOn).updateArmyImgID();
                        Game.getCiv(alliesA.get(i)).updateArmyImgID();
                    }
                    else if (civWarDeclaredOn == Game.player.iCivID) {
                        Game.getCiv(civA).updateArmyImgID();
                        Game.getCiv(alliesA.get(i)).updateArmyImgID();
                    }
                    else if (alliesA.get(i) == Game.player.iCivID) {
                        for (int a = callToWar.size() - 1; a >= 0; --a) {
                            Game.getCiv(callToWar.get(a)).updateArmyImgID();
                        }
                        Game.getCiv(civA).updateArmyImgID();
                        Game.getCiv(civWarDeclaredOn).updateArmyImgID();
                        Game.player.addMessage(new PMessageWar(civA, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
                        Game.menuManager.rebuildInGame_Info(Game.lang.get("War"), Game.getCiv(civA).getCivName() + " - " + Game.getCiv(Game.player.iCivID).getCivName());
                        InGame_Info.imgID = Images.infoWar;
                        Game.soundsManager.loadNextMusicWar();
                        if (GameValues.provinceBorderWar.ENABLE_WAR_BORDER) {
                            Game.addSimpleTask(new Game.SimpleTask("updateProvinceBorder") {
                                @Override
                                public void update() {
                                    ProvinceBorderManager.updateProvinceBorder();
                                }
                            });
                        }
                        Game.addSimpleTask(new Game.SimpleTask("rebuildInGame_Wars") {
                            @Override
                            public void update() {
                                Game.menuManager.rebuildInGame_Wars();
                            }
                        });
                    }
                }
                for (int i = 0; i < callToWar.size(); ++i) {
                    if (callToWar.get(i) == Game.player.iCivID) {
                        for (int a = 0; a < alliesA.size(); ++a) {
                            Game.getCiv(alliesA.get(a)).updateArmyImgID();
                        }
                        break;
                    }
                }
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
        try {
            if (callToWar.size() > 0 && warKey != null) {
                for (int i = 0; i < callToWar.size(); ++i) {
                    if (callToWar.get(i) == Game.player.iCivID) {
                        Game.player.addMessage(new PMessageCallToWar(civA, warKey, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
                    }
                    else {
                        WarManager.lWars.get(warKey).addAggressor(callToWar.get(i));
                        if (civA == Game.player.iCivID) {
                            Game.getCiv(civWarDeclaredOn).updateArmyImgID();
                            Game.getCiv(callToWar.get(i)).updateArmyImgID();
                        }
                        else if (civWarDeclaredOn == Game.player.iCivID) {
                            Game.getCiv(civA).updateArmyImgID();
                            Game.getCiv(callToWar.get(i)).updateArmyImgID();
                        }
                        else if (callToWar.get(i) == Game.player.iCivID) {
                            Game.getCiv(civA).updateArmyImgID();
                            Game.getCiv(civWarDeclaredOn).updateArmyImgID();
                            if (GameValues.provinceBorderWar.ENABLE_WAR_BORDER) {
                                Game.addSimpleTask(new Game.SimpleTask("updateProvinceBorder") {
                                    @Override
                                    public void update() {
                                        ProvinceBorderManager.updateProvinceBorder();
                                    }
                                });
                            }
                            Game.addSimpleTask(new Game.SimpleTask("rebuildInGame_Wars") {
                                @Override
                                public void update() {
                                    Game.menuManager.rebuildInGame_Wars();
                                }
                            });
                        }
                    }
                }
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
        int regimentsA = Game.getCiv(civA).getArmyRegimentSize();
        int regimentsB = Game.getCiv(civWarDeclaredOn).getArmyRegimentSize();
        try {
            for (int j = WarManager.lWars.get(warKey).lAggressors.size() - 1; j > 0; --j) {
                regimentsA += Game.getCiv(WarManager.lWars.get(warKey).lAggressors.get(j).iCivID).getArmyRegimentSize();
            }
        }
        catch (final Exception ex3) {
            CFG.exceptionStack(ex3);
        }
        try {
            for (int j = WarManager.lWars.get(warKey).lDefenders.size() - 1; j > 0; --j) {
                regimentsB += Game.getCiv(WarManager.lWars.get(warKey).lDefenders.get(j).iCivID).getArmyRegimentSize();
            }
        }
        catch (final Exception ex3) {
            CFG.exceptionStack(ex3);
        }
        try {
            if (!Game.ideologiesManager.getIdeology(Game.getCiv(WarManager.lWars.get(warKey).lAggressors.get(0).iCivID).getIdeologyID()).TRIBAL && Game.ideologiesManager.getIdeology(Game.getCiv(WarManager.lWars.get(warKey).lDefenders.get(0).iCivID).getIdeologyID()).TRIBAL) {
                for (int j = WarManager.lWars.get(warKey).lDefenders.size() - 1; j >= 0; --j) {
                    Game.getCiv(WarManager.lWars.get(warKey).lDefenders.get(j).iCivID).setWarPlayDefensiveUntilTurnID(Game_Calendar.TURN_ID + Game.aiValuesDiplomacy.AI_WAR_DEFENDERS_PLAY_DEFENSIVE_TRIBAL_TURNS_MIN + Game.oR.nextInt(Game.aiValuesDiplomacy.AI_WAR_DEFENDERS_PLAY_DEFENSIVE_TRIBAL_TURNS_RANDOM));
                }
            }
            else {
                for (int j = WarManager.lWars.get(warKey).lDefenders.size() - 1; j >= 0; --j) {
                    Game.getCiv(WarManager.lWars.get(warKey).lDefenders.get(j).iCivID).setWarPlayDefensiveUntilTurnID(Game_Calendar.TURN_ID + Game.aiValuesDiplomacy.AI_WAR_DEFENDERS_PLAY_DEFENSIVE_TURNS_MIN + Game.oR.nextInt(Game.aiValuesDiplomacy.AI_WAR_DEFENDERS_PLAY_DEFENSIVE_TURNS_RANDOM));
                }
            }
        }
        catch (final Exception ex3) {
            CFG.exceptionStack(ex3);
        }
        try {
            AI_Manager.aiBudget.updateMilitaryLevel_War(civA, civWarDeclaredOn, regimentsA, regimentsB);
            AI_Manager.aiBudget.updateMilitaryLevel_War(civWarDeclaredOn, civA, regimentsB, regimentsA);
        }
        catch (final Exception ex3) {
            CFG.exceptionStack(ex3);
        }
        declareWar_NeighborsRelationChange(civA, civWarDeclaredOn);
        if (civA == Game.player.iCivID) {
            Game.getCiv(civWarDeclaredOn).updateArmyImgID();
            if (GameValues.provinceBorderWar.ENABLE_WAR_BORDER) {
                Game.addSimpleTask(new Game.SimpleTask("updateProvinceBorder") {
                    @Override
                    public void update() {
                        ProvinceBorderManager.updateProvinceBorder();
                    }
                });
            }
            Game.addSimpleTask(new Game.SimpleTask("rebuildInGame_Wars") {
                @Override
                public void update() {
                    Game.menuManager.rebuildInGame_Wars();
                }
            });
        }
        else if (civWarDeclaredOn == Game.player.iCivID) {
            Game.getCiv(civA).updateArmyImgID();
            Game.menuManager.rebuildInGame_Info(Game.lang.get("War"), Game.getCiv(civA).getCivName() + " - " + Game.getCiv(Game.player.iCivID).getCivName());
            InGame_Info.imgID = Images.infoWar;
            Game.soundsManager.loadNextMusicWar();
            Game.player.addMessage(new PMessageWar(civA, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
            if (GameValues.provinceBorderWar.ENABLE_WAR_BORDER) {
                Game.addSimpleTask(new Game.SimpleTask("updateProvinceBorder") {
                    @Override
                    public void update() {
                        ProvinceBorderManager.updateProvinceBorder();
                    }
                });
            }
            Game.addSimpleTask(new Game.SimpleTask("rebuildInGame_Wars") {
                @Override
                public void update() {
                    Game.menuManager.rebuildInGame_Wars();
                }
            });
        }
        if (Game.getCiv(civA).getCapitalProvinceID() >= 0 && Game.getCiv(civWarDeclaredOn).getCapitalProvinceID() >= 0 && (GameValues.value.DRAW_DIPLOMACY_LINES_WAR_DECLARED || Game.player.iCivID == civA || Game.player.iCivID == civWarDeclaredOn)) {
            ProvinceDraw.addDiplomacyLines(Game.getCiv(civA).getCapitalProvinceID(), Game.getCiv(civWarDeclaredOn).getCapitalProvinceID(), DiplomacyManager.COLOR_WAR);
        }
        for (int j = 0; j < alliesA.size(); ++j) {
            if (alliesA.get(j) == Game.player.iCivID) {
                for (int a2 = callToWar.size() - 1; a2 >= 0; --a2) {
                    Game.getCiv(callToWar.get(a2)).updateArmyImgID();
                }
                break;
            }
        }
        for (int j = 0; j < callToWar.size(); ++j) {
            if (callToWar.get(j) == Game.player.iCivID) {
                for (int a2 = alliesA.size() - 1; a2 >= 0; --a2) {
                    Game.getCiv(alliesA.get(a2)).updateArmyImgID();
                }
                break;
            }
        }
        return true;
    }
    
    public static final boolean declareWar(final int civA, final int civWarDeclaredOn, final boolean free, final List<Integer> callToWar, final boolean isCoalition, final String KEY) {
        if (civA == civWarDeclaredOn) {
            return false;
        }
        if (Game.getCiv(civA).getNumOfProvinces() <= 0 || Game.getCiv(civWarDeclaredOn).getNumOfProvinces() <= 0) {
            return false;
        }
        if (!free) {
            if (Game.getCiv(civA).diplomacy.getRelation(civWarDeclaredOn) > GameValues.war.RELATIONS_TO_DECLARE_WAR) {
                return false;
            }
            if (Game.getCiv(civA).diplomacy.haveNonAggressionPact(civWarDeclaredOn)) {
                return false;
            }
            if (Game.getCiv(civA).fDiplomacy < GameValues.diplomacy.DIPLOMACY_DECLARE_WAR_COST) {
                return false;
            }
            if (Game.getCiv(civA).diplomacy.haveAlliance(civWarDeclaredOn) || Game.getCiv(civWarDeclaredOn).diplomacy.haveAlliance(civA)) {
                return false;
            }
            final Civilization civ3;
            final Civilization civ2;
            final Civilization civilization10;
            final Civilization civilization9;
            final Civilization civilization8;
            final Civilization civilization11;
            final Civilization civilization7 = civilization11 = (civilization8 = (civilization9 = (civilization10 = (civ2 = (civ3 = Game.getCiv(civA))))));
            civilization11.fDiplomacy -= GameValues.diplomacy.DIPLOMACY_DECLARE_WAR_COST;
        }
        if (Game.getCiv(civA).diplomacy.truce.containsKey(civWarDeclaredOn)) {
            if (Game.getCiv(civA).diplomacy.truce.get(civWarDeclaredOn).iTurnID >= Game_Calendar.TURN_ID && !free) {
                return false;
            }
            Game.getCiv(civA).diplomacy.truce.remove(civWarDeclaredOn);
            Game.getCiv(civWarDeclaredOn).diplomacy.truce.remove(civA);
        }
        final boolean conquerVassal = Game.getCiv(civWarDeclaredOn).getPuppetOfCivID() == civA;
        try {
            if (Game.getCiv(civA).diplomacy.alliance.containsKey(civWarDeclaredOn)) {
                Game.getCiv(civA).diplomacy.alliance.remove(civWarDeclaredOn);
            }
            if (Game.getCiv(civWarDeclaredOn).diplomacy.alliance.containsKey(civA)) {
                Game.getCiv(civWarDeclaredOn).diplomacy.alliance.remove(civA);
            }
            if (Game.getCiv(civA).diplomacy.defensivePact.containsKey(civWarDeclaredOn)) {
                Game.getCiv(civA).diplomacy.defensivePact.remove(civWarDeclaredOn);
            }
            if (Game.getCiv(civWarDeclaredOn).diplomacy.defensivePact.containsKey(civA)) {
                Game.getCiv(civWarDeclaredOn).diplomacy.defensivePact.remove(civA);
            }
            if (Game.getCiv(civA).diplomacy.nonAggressionPact.containsKey(civWarDeclaredOn)) {
                Game.getCiv(civA).diplomacy.nonAggressionPact.remove(civWarDeclaredOn);
            }
            if (Game.getCiv(civWarDeclaredOn).diplomacy.nonAggressionPact.containsKey(civA)) {
                Game.getCiv(civWarDeclaredOn).diplomacy.nonAggressionPact.remove(civA);
            }
            if (Game.getCiv(civA).diplomacy.guarantee.containsKey(civWarDeclaredOn)) {
                Game.getCiv(civA).diplomacy.guarantee.remove(civWarDeclaredOn);
            }
            if (Game.getCiv(civWarDeclaredOn).diplomacy.guarantee.containsKey(civA)) {
                Game.getCiv(civWarDeclaredOn).diplomacy.guarantee.remove(civA);
            }
            if (Game.getCiv(civA).diplomacy.militaryAccess.containsKey(civWarDeclaredOn)) {
                Game.getCiv(civA).diplomacy.militaryAccess.remove(civWarDeclaredOn);
            }
            if (Game.getCiv(civWarDeclaredOn).diplomacy.militaryAccess.containsKey(civA)) {
                Game.getCiv(civWarDeclaredOn).diplomacy.militaryAccess.remove(civA);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            Game.getCiv(civA).addAggressiveExpansion(GameValues.aggressiveExpansion.AE_DECLARE_WAR);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            declareWar_UpdateRelation(civA, civWarDeclaredOn);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        final String warKey = WarManager.addWar(civA, civWarDeclaredOn, conquerVassal, isCoalition, KEY);
        final ArrayList<String> list2 = new ArrayList<String>();
        for (final int value : callToWar) {
            list2.add(String.valueOf(value));
        }
        final String listString2 = "[" + String.join(", ", list2) + "]";
        DesktopLauncher.SMS("War: " + civA + " " + civWarDeclaredOn + " " + WarManager.getWarKey(civA, civWarDeclaredOn) + " " + listString2);
        if (warKey == null) {
            return false;
        }
        List<Integer> alliesA = new ArrayList<Integer>();
        try {
            alliesA = declareWar_AlliesDefender(civWarDeclaredOn, civA);
            if (alliesA.size() > 0 && warKey != null) {
                for (int i = 0; i < alliesA.size(); ++i) {
                    declareWar_UpdateRelation(civWarDeclaredOn, alliesA.get(i));
                    if (civA == Game.player.iCivID) {
                        Game.getCiv(civWarDeclaredOn).updateArmyImgID();
                        Game.getCiv(alliesA.get(i)).updateArmyImgID();
                    }
                    else if (civWarDeclaredOn == Game.player.iCivID) {
                        Game.getCiv(civA).updateArmyImgID();
                        Game.getCiv(alliesA.get(i)).updateArmyImgID();
                    }
                    else if (alliesA.get(i) == Game.player.iCivID) {
                        for (int a = callToWar.size() - 1; a >= 0; --a) {
                            Game.getCiv(callToWar.get(a)).updateArmyImgID();
                        }
                        Game.getCiv(civA).updateArmyImgID();
                        Game.getCiv(civWarDeclaredOn).updateArmyImgID();
                        Game.player.addMessage(new PMessageWar(civA, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
                        Game.menuManager.rebuildInGame_Info(Game.lang.get("War"), Game.getCiv(civA).getCivName() + " - " + Game.getCiv(Game.player.iCivID).getCivName());
                        InGame_Info.imgID = Images.infoWar;
                        Game.soundsManager.loadNextMusicWar();
                        if (GameValues.provinceBorderWar.ENABLE_WAR_BORDER) {
                            Game.addSimpleTask(new Game.SimpleTask("updateProvinceBorder") {
                                @Override
                                public void update() {
                                    ProvinceBorderManager.updateProvinceBorder();
                                }
                            });
                        }
                        Game.addSimpleTask(new Game.SimpleTask("rebuildInGame_Wars") {
                            @Override
                            public void update() {
                                Game.menuManager.rebuildInGame_Wars();
                            }
                        });
                    }
                }
                for (int i = 0; i < callToWar.size(); ++i) {
                    if (callToWar.get(i) == Game.player.iCivID) {
                        for (int a = 0; a < alliesA.size(); ++a) {
                            Game.getCiv(alliesA.get(a)).updateArmyImgID();
                        }
                        break;
                    }
                }
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
        try {
            if (callToWar.size() > 0 && warKey != null) {
                for (int i = 0; i < callToWar.size(); ++i) {
                    if (callToWar.get(i) == Game.player.iCivID) {
                        Game.player.addMessage(new PMessageCallToWar(civA, warKey, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
                    }
                    else {
                        WarManager.lWars.get(warKey).addAggressor(callToWar.get(i));
                        if (civA == Game.player.iCivID) {
                            Game.getCiv(civWarDeclaredOn).updateArmyImgID();
                            Game.getCiv(callToWar.get(i)).updateArmyImgID();
                        }
                        else if (civWarDeclaredOn == Game.player.iCivID) {
                            Game.getCiv(civA).updateArmyImgID();
                            Game.getCiv(callToWar.get(i)).updateArmyImgID();
                        }
                        else if (callToWar.get(i) == Game.player.iCivID) {
                            Game.getCiv(civA).updateArmyImgID();
                            Game.getCiv(civWarDeclaredOn).updateArmyImgID();
                            if (GameValues.provinceBorderWar.ENABLE_WAR_BORDER) {
                                Game.addSimpleTask(new Game.SimpleTask("updateProvinceBorder") {
                                    @Override
                                    public void update() {
                                        ProvinceBorderManager.updateProvinceBorder();
                                    }
                                });
                            }
                            Game.addSimpleTask(new Game.SimpleTask("rebuildInGame_Wars") {
                                @Override
                                public void update() {
                                    Game.menuManager.rebuildInGame_Wars();
                                }
                            });
                        }
                    }
                }
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
        int regimentsA = Game.getCiv(civA).getArmyRegimentSize();
        int regimentsB = Game.getCiv(civWarDeclaredOn).getArmyRegimentSize();
        try {
            for (int j = WarManager.lWars.get(warKey).lAggressors.size() - 1; j > 0; --j) {
                regimentsA += Game.getCiv(WarManager.lWars.get(warKey).lAggressors.get(j).iCivID).getArmyRegimentSize();
            }
        }
        catch (final Exception ex3) {
            CFG.exceptionStack(ex3);
        }
        try {
            for (int j = WarManager.lWars.get(warKey).lDefenders.size() - 1; j > 0; --j) {
                regimentsB += Game.getCiv(WarManager.lWars.get(warKey).lDefenders.get(j).iCivID).getArmyRegimentSize();
            }
        }
        catch (final Exception ex3) {
            CFG.exceptionStack(ex3);
        }
        try {
            if (!Game.ideologiesManager.getIdeology(Game.getCiv(WarManager.lWars.get(warKey).lAggressors.get(0).iCivID).getIdeologyID()).TRIBAL && Game.ideologiesManager.getIdeology(Game.getCiv(WarManager.lWars.get(warKey).lDefenders.get(0).iCivID).getIdeologyID()).TRIBAL) {
                for (int j = WarManager.lWars.get(warKey).lDefenders.size() - 1; j >= 0; --j) {
                    Game.getCiv(WarManager.lWars.get(warKey).lDefenders.get(j).iCivID).setWarPlayDefensiveUntilTurnID(Game_Calendar.TURN_ID + Game.aiValuesDiplomacy.AI_WAR_DEFENDERS_PLAY_DEFENSIVE_TRIBAL_TURNS_MIN + Game.oR.nextInt(Game.aiValuesDiplomacy.AI_WAR_DEFENDERS_PLAY_DEFENSIVE_TRIBAL_TURNS_RANDOM));
                }
            }
            else {
                for (int j = WarManager.lWars.get(warKey).lDefenders.size() - 1; j >= 0; --j) {
                    Game.getCiv(WarManager.lWars.get(warKey).lDefenders.get(j).iCivID).setWarPlayDefensiveUntilTurnID(Game_Calendar.TURN_ID + Game.aiValuesDiplomacy.AI_WAR_DEFENDERS_PLAY_DEFENSIVE_TURNS_MIN + Game.oR.nextInt(Game.aiValuesDiplomacy.AI_WAR_DEFENDERS_PLAY_DEFENSIVE_TURNS_RANDOM));
                }
            }
        }
        catch (final Exception ex3) {
            CFG.exceptionStack(ex3);
        }
        try {
            AI_Manager.aiBudget.updateMilitaryLevel_War(civA, civWarDeclaredOn, regimentsA, regimentsB);
            AI_Manager.aiBudget.updateMilitaryLevel_War(civWarDeclaredOn, civA, regimentsB, regimentsA);
        }
        catch (final Exception ex3) {
            CFG.exceptionStack(ex3);
        }
        declareWar_NeighborsRelationChange(civA, civWarDeclaredOn);
        if (civA == Game.player.iCivID) {
            Game.getCiv(civWarDeclaredOn).updateArmyImgID();
            if (GameValues.provinceBorderWar.ENABLE_WAR_BORDER) {
                Game.addSimpleTask(new Game.SimpleTask("updateProvinceBorder") {
                    @Override
                    public void update() {
                        ProvinceBorderManager.updateProvinceBorder();
                    }
                });
            }
            Game.addSimpleTask(new Game.SimpleTask("rebuildInGame_Wars") {
                @Override
                public void update() {
                    Game.menuManager.rebuildInGame_Wars();
                }
            });
        }
        else if (civWarDeclaredOn == Game.player.iCivID) {
            Game.getCiv(civA).updateArmyImgID();
            Game.menuManager.rebuildInGame_Info(Game.lang.get("War"), Game.getCiv(civA).getCivName() + " - " + Game.getCiv(Game.player.iCivID).getCivName());
            InGame_Info.imgID = Images.infoWar;
            Game.soundsManager.loadNextMusicWar();
            Game.player.addMessage(new PMessageWar(civA, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
            if (GameValues.provinceBorderWar.ENABLE_WAR_BORDER) {
                Game.addSimpleTask(new Game.SimpleTask("updateProvinceBorder") {
                    @Override
                    public void update() {
                        ProvinceBorderManager.updateProvinceBorder();
                    }
                });
            }
            Game.addSimpleTask(new Game.SimpleTask("rebuildInGame_Wars") {
                @Override
                public void update() {
                    Game.menuManager.rebuildInGame_Wars();
                }
            });
        }
        if (Game.getCiv(civA).getCapitalProvinceID() >= 0 && Game.getCiv(civWarDeclaredOn).getCapitalProvinceID() >= 0 && (GameValues.value.DRAW_DIPLOMACY_LINES_WAR_DECLARED || Game.player.iCivID == civA || Game.player.iCivID == civWarDeclaredOn)) {
            ProvinceDraw.addDiplomacyLines(Game.getCiv(civA).getCapitalProvinceID(), Game.getCiv(civWarDeclaredOn).getCapitalProvinceID(), DiplomacyManager.COLOR_WAR);
        }
        for (int j = 0; j < alliesA.size(); ++j) {
            if (alliesA.get(j) == Game.player.iCivID) {
                for (int a2 = callToWar.size() - 1; a2 >= 0; --a2) {
                    Game.getCiv(callToWar.get(a2)).updateArmyImgID();
                }
                break;
            }
        }
        for (int j = 0; j < callToWar.size(); ++j) {
            if (callToWar.get(j) == Game.player.iCivID) {
                for (int a2 = alliesA.size() - 1; a2 >= 0; --a2) {
                    Game.getCiv(alliesA.get(a2)).updateArmyImgID();
                }
                break;
            }
        }
        return true;
    }
    
    public static final void declareWar_NeighborsRelationChange(final int civID, final int civWarDeclaredOn) {
        try {
            final Civilization civ = Game.getCiv(civID);
            int capitalProvinceID = -1;
            if (civ.getCapitalProvinceID() >= 0) {
                capitalProvinceID = civ.getCapitalProvinceID();
            }
            else if (civ.getNumOfProvinces() > 0) {
                capitalProvinceID = civ.getProvinceID(0);
            }
            if (capitalProvinceID < 0) {
                return;
            }
            final List<Integer> civsList = new ArrayList<Integer>();
            for (int i = 0; i < civ.civNeighbors.civsSize; ++i) {
                if (!civsList.contains(civ.civNeighbors.civs.get(i).civID) && civ.civNeighbors.civs.get(i).civID != civID && civ.civNeighbors.civs.get(i).civID != civWarDeclaredOn) {
                    civsList.add(civ.civNeighbors.civs.get(i).civID);
                }
                for (int j = 0; j < Game.getCiv(civ.civNeighbors.civs.get(i).civID).civNeighbors.civsSize; ++j) {
                    if (!civsList.contains(Game.getCiv(civ.civNeighbors.civs.get(i).civID).civNeighbors.civs.get(j).civID) && Game.getCiv(civ.civNeighbors.civs.get(i).civID).civNeighbors.civs.get(j).civID != civID && Game.getCiv(civ.civNeighbors.civs.get(i).civID).civNeighbors.civs.get(j).civID != civWarDeclaredOn) {
                        civsList.add(Game.getCiv(civ.civNeighbors.civs.get(i).civID).civNeighbors.civs.get(j).civID);
                    }
                }
            }
            float distance = 0.0f;
            for (int k = 0, iSize = civsList.size(); k < iSize; ++k) {
                distance = GameValues.aggressiveExpansion.DECLARE_WAR_RELATION_CHANGE_WITH_NEIGHBORS * (1.0f - Game.getDistance_PercOfMax(capitalProvinceID, Game.getCiv(civsList.get(k)).getCapitalProvinceID()));
                Game.getCiv(civID).diplomacy.updateRelation(civID, civsList.get(k), distance);
                Game.getCiv(civsList.get(k)).diplomacy.updateRelation(civsList.get(k), civID, distance);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final boolean declareWar2(final int civA, final int civWarDeclaredOn, final boolean free, final List<Integer> callToWar, final boolean isCoalition, final String KEY) {
        if (civA == civWarDeclaredOn) {
            return false;
        }
        if (Game.getCiv(civA).getNumOfProvinces() <= 0 || Game.getCiv(civWarDeclaredOn).getNumOfProvinces() <= 0) {
            return false;
        }
        if (!free) {
            if (Game.getCiv(civA).diplomacy.getRelation(civWarDeclaredOn) > GameValues.war.RELATIONS_TO_DECLARE_WAR) {
                return false;
            }
            if (Game.getCiv(civA).diplomacy.haveNonAggressionPact(civWarDeclaredOn)) {
                return false;
            }
            if (Game.getCiv(civA).fDiplomacy < GameValues.diplomacy.DIPLOMACY_DECLARE_WAR_COST) {
                return false;
            }
            if (Game.getCiv(civA).diplomacy.haveAlliance(civWarDeclaredOn) || Game.getCiv(civWarDeclaredOn).diplomacy.haveAlliance(civA)) {
                return false;
            }
            final Civilization civ3;
            final Civilization civ2;
            final Civilization civilization10;
            final Civilization civilization9;
            final Civilization civilization8;
            final Civilization civilization11;
            final Civilization civilization7 = civilization11 = (civilization8 = (civilization9 = (civilization10 = (civ2 = (civ3 = Game.getCiv(civA))))));
            civilization11.fDiplomacy -= GameValues.diplomacy.DIPLOMACY_DECLARE_WAR_COST;
        }
        if (Game.getCiv(civA).diplomacy.truce.containsKey(civWarDeclaredOn)) {
            if (Game.getCiv(civA).diplomacy.truce.get(civWarDeclaredOn).iTurnID >= Game_Calendar.TURN_ID && !free) {
                return false;
            }
            Game.getCiv(civA).diplomacy.truce.remove(civWarDeclaredOn);
            Game.getCiv(civWarDeclaredOn).diplomacy.truce.remove(civA);
        }
        final boolean conquerVassal = Game.getCiv(civWarDeclaredOn).getPuppetOfCivID() == civA;
        try {
            if (Game.getCiv(civA).diplomacy.alliance.containsKey(civWarDeclaredOn)) {
                Game.getCiv(civA).diplomacy.alliance.remove(civWarDeclaredOn);
            }
            if (Game.getCiv(civWarDeclaredOn).diplomacy.alliance.containsKey(civA)) {
                Game.getCiv(civWarDeclaredOn).diplomacy.alliance.remove(civA);
            }
            if (Game.getCiv(civA).diplomacy.defensivePact.containsKey(civWarDeclaredOn)) {
                Game.getCiv(civA).diplomacy.defensivePact.remove(civWarDeclaredOn);
            }
            if (Game.getCiv(civWarDeclaredOn).diplomacy.defensivePact.containsKey(civA)) {
                Game.getCiv(civWarDeclaredOn).diplomacy.defensivePact.remove(civA);
            }
            if (Game.getCiv(civA).diplomacy.nonAggressionPact.containsKey(civWarDeclaredOn)) {
                Game.getCiv(civA).diplomacy.nonAggressionPact.remove(civWarDeclaredOn);
            }
            if (Game.getCiv(civWarDeclaredOn).diplomacy.nonAggressionPact.containsKey(civA)) {
                Game.getCiv(civWarDeclaredOn).diplomacy.nonAggressionPact.remove(civA);
            }
            if (Game.getCiv(civA).diplomacy.guarantee.containsKey(civWarDeclaredOn)) {
                Game.getCiv(civA).diplomacy.guarantee.remove(civWarDeclaredOn);
            }
            if (Game.getCiv(civWarDeclaredOn).diplomacy.guarantee.containsKey(civA)) {
                Game.getCiv(civWarDeclaredOn).diplomacy.guarantee.remove(civA);
            }
            if (Game.getCiv(civA).diplomacy.militaryAccess.containsKey(civWarDeclaredOn)) {
                Game.getCiv(civA).diplomacy.militaryAccess.remove(civWarDeclaredOn);
            }
            if (Game.getCiv(civWarDeclaredOn).diplomacy.militaryAccess.containsKey(civA)) {
                Game.getCiv(civWarDeclaredOn).diplomacy.militaryAccess.remove(civA);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            Game.getCiv(civA).addAggressiveExpansion(GameValues.aggressiveExpansion.AE_DECLARE_WAR);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            declareWar_UpdateRelation(civA, civWarDeclaredOn);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        final String warKey = WarManager.addWar(civA, civWarDeclaredOn, conquerVassal, isCoalition, KEY);
        if (warKey == null) {
            return false;
        }
        List<Integer> alliesA = new ArrayList<Integer>();
        try {
            alliesA = declareWar_AlliesDefender(civWarDeclaredOn, civA);
            if (alliesA.size() > 0 && warKey != null) {
                for (int i = 0; i < alliesA.size(); ++i) {
                    declareWar_UpdateRelation(civWarDeclaredOn, alliesA.get(i));
                    if (civA == Game.player.iCivID) {
                        Game.getCiv(civWarDeclaredOn).updateArmyImgID();
                        Game.getCiv(alliesA.get(i)).updateArmyImgID();
                    }
                    else if (civWarDeclaredOn == Game.player.iCivID) {
                        Game.getCiv(civA).updateArmyImgID();
                        Game.getCiv(alliesA.get(i)).updateArmyImgID();
                    }
                    else if (alliesA.get(i) == Game.player.iCivID) {
                        for (int a = callToWar.size() - 1; a >= 0; --a) {
                            Game.getCiv(callToWar.get(a)).updateArmyImgID();
                        }
                        Game.getCiv(civA).updateArmyImgID();
                        Game.getCiv(civWarDeclaredOn).updateArmyImgID();
                        Game.player.addMessage(new PMessageWar(civA, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
                        Game.menuManager.rebuildInGame_Info(Game.lang.get("War"), Game.getCiv(civA).getCivName() + " - " + Game.getCiv(Game.player.iCivID).getCivName());
                        InGame_Info.imgID = Images.infoWar;
                        Game.soundsManager.loadNextMusicWar();
                        if (GameValues.provinceBorderWar.ENABLE_WAR_BORDER) {
                            Game.addSimpleTask(new Game.SimpleTask("updateProvinceBorder") {
                                @Override
                                public void update() {
                                    ProvinceBorderManager.updateProvinceBorder();
                                }
                            });
                        }
                        Game.addSimpleTask(new Game.SimpleTask("rebuildInGame_Wars") {
                            @Override
                            public void update() {
                                Game.menuManager.rebuildInGame_Wars();
                            }
                        });
                    }
                }
                for (int i = 0; i < callToWar.size(); ++i) {
                    if (callToWar.get(i) == Game.player.iCivID) {
                        for (int a = 0; a < alliesA.size(); ++a) {
                            Game.getCiv(alliesA.get(a)).updateArmyImgID();
                        }
                        break;
                    }
                }
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
        try {
            if (callToWar.size() > 0 && warKey != null) {
                for (int i = 0; i < callToWar.size(); ++i) {
                    if (callToWar.get(i) == Game.player.iCivID) {
                        Game.player.addMessage(new PMessageCallToWar(civA, warKey, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
                    }
                    else {
                        WarManager.lWars.get(warKey).addAggressor(callToWar.get(i));
                        if (civA == Game.player.iCivID) {
                            Game.getCiv(civWarDeclaredOn).updateArmyImgID();
                            Game.getCiv(callToWar.get(i)).updateArmyImgID();
                        }
                        else if (civWarDeclaredOn == Game.player.iCivID) {
                            Game.getCiv(civA).updateArmyImgID();
                            Game.getCiv(callToWar.get(i)).updateArmyImgID();
                        }
                        else if (callToWar.get(i) == Game.player.iCivID) {
                            Game.getCiv(civA).updateArmyImgID();
                            Game.getCiv(civWarDeclaredOn).updateArmyImgID();
                            if (GameValues.provinceBorderWar.ENABLE_WAR_BORDER) {
                                Game.addSimpleTask(new Game.SimpleTask("updateProvinceBorder") {
                                    @Override
                                    public void update() {
                                        ProvinceBorderManager.updateProvinceBorder();
                                    }
                                });
                            }
                            Game.addSimpleTask(new Game.SimpleTask("rebuildInGame_Wars") {
                                @Override
                                public void update() {
                                    Game.menuManager.rebuildInGame_Wars();
                                }
                            });
                        }
                    }
                }
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
        int regimentsA = Game.getCiv(civA).getArmyRegimentSize();
        int regimentsB = Game.getCiv(civWarDeclaredOn).getArmyRegimentSize();
        try {
            for (int j = WarManager.lWars.get(warKey).lAggressors.size() - 1; j > 0; --j) {
                regimentsA += Game.getCiv(WarManager.lWars.get(warKey).lAggressors.get(j).iCivID).getArmyRegimentSize();
            }
        }
        catch (final Exception ex3) {
            CFG.exceptionStack(ex3);
        }
        try {
            for (int j = WarManager.lWars.get(warKey).lDefenders.size() - 1; j > 0; --j) {
                regimentsB += Game.getCiv(WarManager.lWars.get(warKey).lDefenders.get(j).iCivID).getArmyRegimentSize();
            }
        }
        catch (final Exception ex3) {
            CFG.exceptionStack(ex3);
        }
        try {
            if (!Game.ideologiesManager.getIdeology(Game.getCiv(WarManager.lWars.get(warKey).lAggressors.get(0).iCivID).getIdeologyID()).TRIBAL && Game.ideologiesManager.getIdeology(Game.getCiv(WarManager.lWars.get(warKey).lDefenders.get(0).iCivID).getIdeologyID()).TRIBAL) {
                for (int j = WarManager.lWars.get(warKey).lDefenders.size() - 1; j >= 0; --j) {
                    Game.getCiv(WarManager.lWars.get(warKey).lDefenders.get(j).iCivID).setWarPlayDefensiveUntilTurnID(Game_Calendar.TURN_ID + Game.aiValuesDiplomacy.AI_WAR_DEFENDERS_PLAY_DEFENSIVE_TRIBAL_TURNS_MIN + Game.oR.nextInt(Game.aiValuesDiplomacy.AI_WAR_DEFENDERS_PLAY_DEFENSIVE_TRIBAL_TURNS_RANDOM));
                }
            }
            else {
                for (int j = WarManager.lWars.get(warKey).lDefenders.size() - 1; j >= 0; --j) {
                    Game.getCiv(WarManager.lWars.get(warKey).lDefenders.get(j).iCivID).setWarPlayDefensiveUntilTurnID(Game_Calendar.TURN_ID + Game.aiValuesDiplomacy.AI_WAR_DEFENDERS_PLAY_DEFENSIVE_TURNS_MIN + Game.oR.nextInt(Game.aiValuesDiplomacy.AI_WAR_DEFENDERS_PLAY_DEFENSIVE_TURNS_RANDOM));
                }
            }
        }
        catch (final Exception ex3) {
            CFG.exceptionStack(ex3);
        }
        try {
            AI_Manager.aiBudget.updateMilitaryLevel_War(civA, civWarDeclaredOn, regimentsA, regimentsB);
            AI_Manager.aiBudget.updateMilitaryLevel_War(civWarDeclaredOn, civA, regimentsB, regimentsA);
        }
        catch (final Exception ex3) {
            CFG.exceptionStack(ex3);
        }
        declareWar_NeighborsRelationChange(civA, civWarDeclaredOn);
        if (civA == Game.player.iCivID) {
            Game.getCiv(civWarDeclaredOn).updateArmyImgID();
            if (GameValues.provinceBorderWar.ENABLE_WAR_BORDER) {
                Game.addSimpleTask(new Game.SimpleTask("updateProvinceBorder") {
                    @Override
                    public void update() {
                        ProvinceBorderManager.updateProvinceBorder();
                    }
                });
            }
            Game.addSimpleTask(new Game.SimpleTask("rebuildInGame_Wars") {
                @Override
                public void update() {
                    Game.menuManager.rebuildInGame_Wars();
                }
            });
        }
        else if (civWarDeclaredOn == Game.player.iCivID) {
            Game.getCiv(civA).updateArmyImgID();
            Game.menuManager.rebuildInGame_Info(Game.lang.get("War"), Game.getCiv(civA).getCivName() + " - " + Game.getCiv(Game.player.iCivID).getCivName());
            InGame_Info.imgID = Images.infoWar;
            Game.soundsManager.loadNextMusicWar();
            Game.player.addMessage(new PMessageWar(civA, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
            if (GameValues.provinceBorderWar.ENABLE_WAR_BORDER) {
                Game.addSimpleTask(new Game.SimpleTask("updateProvinceBorder") {
                    @Override
                    public void update() {
                        ProvinceBorderManager.updateProvinceBorder();
                    }
                });
            }
            Game.addSimpleTask(new Game.SimpleTask("rebuildInGame_Wars") {
                @Override
                public void update() {
                    Game.menuManager.rebuildInGame_Wars();
                }
            });
        }
        if (Game.getCiv(civA).getCapitalProvinceID() >= 0 && Game.getCiv(civWarDeclaredOn).getCapitalProvinceID() >= 0 && (GameValues.value.DRAW_DIPLOMACY_LINES_WAR_DECLARED || Game.player.iCivID == civA || Game.player.iCivID == civWarDeclaredOn)) {
            ProvinceDraw.addDiplomacyLines(Game.getCiv(civA).getCapitalProvinceID(), Game.getCiv(civWarDeclaredOn).getCapitalProvinceID(), DiplomacyManager.COLOR_WAR);
        }
        for (int j = 0; j < alliesA.size(); ++j) {
            if (alliesA.get(j) == Game.player.iCivID) {
                for (int a2 = callToWar.size() - 1; a2 >= 0; --a2) {
                    Game.getCiv(callToWar.get(a2)).updateArmyImgID();
                }
                break;
            }
        }
        for (int j = 0; j < callToWar.size(); ++j) {
            if (callToWar.get(j) == Game.player.iCivID) {
                for (int a2 = alliesA.size() - 1; a2 >= 0; --a2) {
                    Game.getCiv(alliesA.get(a2)).updateArmyImgID();
                }
                break;
            }
        }
        return true;
    }
    
    public static final void declareWar_UpdateRelation(final int iCivA, final int iCivB) {
        if (iCivA == iCivB) {
            return;
        }
        try {
            Game.getCiv(iCivA).diplomacy.removeImproveRelations(iCivB);
            Game.getCiv(iCivB).diplomacy.removeImproveRelations(iCivA);
        }
        catch (final Exception ex) {}
        Game.getCiv(iCivA).diplomacy.setRelation_War(iCivB, getRelationAtWar());
        Game.getCiv(iCivB).diplomacy.setRelation_War(iCivA, getRelationAtWar());
        if (Game.getCiv(iCivA).getPuppetOfCivID() == iCivB) {
            Game.getCiv(iCivA).setPuppetOfCivID(iCivA);
        }
        if (Game.getCiv(iCivB).getPuppetOfCivID() == iCivA) {
            Game.getCiv(iCivB).setPuppetOfCivID(iCivB);
        }
        if (Game.getCiv(iCivA).diplomacy.haveAlliance(iCivB)) {
            Game.getCiv(iCivA).diplomacy.removeAlliance(iCivB);
            Game.getCiv(iCivB).diplomacy.removeAlliance(iCivA);
        }
        if (Game.getCiv(iCivB).diplomacy.haveAlliance(iCivA)) {
            Game.getCiv(iCivB).diplomacy.removeAlliance(iCivA);
            Game.getCiv(iCivA).diplomacy.removeAlliance(iCivB);
        }
    }
    
    public static final void declareWar_UpdateRelation_Peace(final int iCivA, final int iCivB, final String warKey) {
        Game.getCiv(iCivA).diplomacy.removeImproveRelations(iCivB);
        Game.getCiv(iCivB).diplomacy.removeImproveRelations(iCivA);
        int tRandom = 0;
        if (GameValues.peace.PEACE_RELATION_RANDOM * 100 != 0) {
            try {
                tRandom = (int)(-GameValues.peace.PEACE_RELATION_RANDOM + Game.oR.nextInt(Math.abs(GameValues.peace.PEACE_RELATION_RANDOM * 2 * 100)) / 100.0f);
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
        Game.getCiv(iCivA).diplomacy.setRelation_Peace(iCivB, GameValues.peace.PEACE_RELATION + tRandom, warKey);
        Game.getCiv(iCivB).diplomacy.setRelation_Peace(iCivA, GameValues.peace.PEACE_RELATION + tRandom, warKey);
    }
    
    public static final List<Integer> civAllies(final int iCivA) {
        final List<Integer> out = new ArrayList<Integer>();
        if (Game.getCiv(iCivA).diplomacy.alliance.size() > 0) {
            for (final Map.Entry<Integer, Diplomacy.DiplomacyData> entry : Game.getCiv(iCivA).diplomacy.alliance.entrySet()) {
                if (entry.getValue().iCivID != iCivA && !out.contains(entry.getValue().iCivID)) {
                    out.add(entry.getValue().iCivID);
                }
            }
        }
        if (Game.ENABLE_CALL_VASSALS) {
            for (int i = 0; i < Game.getCiv(iCivA).diplomacy.iVassalsSize; ++i) {
                if (Game.getCiv(iCivA).diplomacy.lVassals.get(i).c != iCivA && !out.contains(Game.getCiv(iCivA).diplomacy.lVassals.get(i).c)) {
                    out.add(Game.getCiv(iCivA).diplomacy.lVassals.get(i).c);
                }
            }
        }
        for (int i = out.size() - 1; i >= 0; --i) {
            if (out.get(i) < 1 || Game.getCiv(out.get(i)).getNumOfProvinces() <= 0) {
                out.remove(i);
            }
            else if (out.get(i) == iCivA) {
                out.remove(i);
            }
        }
        return out;
    }
    
    public static final boolean isAlly(final int iCivA, final int isAlly) {
        if (iCivA == isAlly) {
            return true;
        }
        if (Game.getCiv(iCivA).diplomacy.alliance.size() > 0) {
            for (final Map.Entry<Integer, Diplomacy.DiplomacyData> entry : Game.getCiv(iCivA).diplomacy.alliance.entrySet()) {
                if (entry.getValue().iCivID == isAlly) {
                    return true;
                }
            }
        }
        for (int i = 0; i < Game.getCiv(iCivA).diplomacy.iVassalsSize; ++i) {
            if (Game.getCiv(iCivA).diplomacy.lVassals.get(i).c == isAlly) {
                return true;
            }
        }
        return Game.getCiv(iCivA).getPuppetOfCivID() == isAlly;
    }
    
    public static final boolean isAlly_AllianceCheck(final int iCivA, final int isAlly) {
        if (Game.getCiv(iCivA).diplomacy.alliance.size() > 0) {
            for (final Map.Entry<Integer, Diplomacy.DiplomacyData> entry : Game.getCiv(iCivA).diplomacy.alliance.entrySet()) {
                if (entry.getValue().iCivID == isAlly) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static final List<Integer> declareWar_AlliesAttacker(final int civAllies, final int civEnemy) {
        final List<Integer> out = new ArrayList<Integer>();
        if (Game.getCiv(civAllies).diplomacy.alliance.size() > 0) {
            for (final Map.Entry<Integer, Diplomacy.DiplomacyData> entry : Game.getCiv(civAllies).diplomacy.alliance.entrySet()) {
                if (entry.getValue().iCivID != civAllies && entry.getValue().iCivID != civEnemy && !out.contains(entry.getValue().iCivID)) {
                    out.add(entry.getValue().iCivID);
                }
            }
        }
        if (Game.ENABLE_CALL_VASSALS) {
            for (int i = 0; i < Game.getCiv(civAllies).diplomacy.iVassalsSize; ++i) {
                if (Game.getCiv(civAllies).diplomacy.lVassals.get(i).c != civAllies && Game.getCiv(civAllies).diplomacy.lVassals.get(i).c != civEnemy && !out.contains(Game.getCiv(civAllies).diplomacy.lVassals.get(i).c)) {
                    out.add(Game.getCiv(civAllies).diplomacy.lVassals.get(i).c);
                }
            }
        }
        for (int i = out.size() - 1; i >= 0; --i) {
            if (out.get(i) < 1 || Game.getCiv(out.get(i)).getNumOfProvinces() <= 0) {
                out.remove(i);
            }
            else if (out.get(i) == civAllies || out.get(i) == civEnemy) {
                out.remove(i);
            }
        }
        return out;
    }
    
    public static final List<Integer> declareWar_AlliesDefender(final int findCivAllies, final int enemyCiv) {
        final List<Integer> out = new ArrayList<Integer>();
        final Civilization civAllies = Game.getCiv(findCivAllies);
        if (civAllies.inAllianceSize > 0) {
            try {
                for (int i = 0; i < civAllies.inAllianceSize; ++i) {
                    if (!Game.alliancesSpecial.get(civAllies.inAlliance.get(i)).isInAlliance(enemyCiv)) {
                        if (Game.alliancesSpecial.get(civAllies.inAlliance.get(i)).typeOfAlliance == 0) {
                            if (GameValues.hre.HRE_EMPEROR_WAR_DEFEND_HRE && Game.alliancesSpecial.get(civAllies.inAlliance.get(i)).iLeaderCivID > 0 && Game.getCiv(Game.alliancesSpecial.get(civAllies.inAlliance.get(i)).iLeaderCivID).getNumOfProvinces() > 0 && !out.contains(Game.alliancesSpecial.get(civAllies.inAlliance.get(i)).iLeaderCivID)) {
                                out.add(Game.alliancesSpecial.get(civAllies.inAlliance.get(i)).iLeaderCivID);
                            }
                            if (GameValues.hre.HRE_ELECTORS_WAR_DEFEND_HRE) {
                                for (int a = 0; a < Game.alliancesSpecial.get(civAllies.inAlliance.get(i)).firstTier.size(); ++a) {
                                    if (!out.contains(Game.alliancesSpecial.get(civAllies.inAlliance.get(i)).firstTier.get(a)) && Game.alliancesSpecial.get(civAllies.inAlliance.get(i)).firstTier.get(a) != findCivAllies) {
                                        out.add(Game.alliancesSpecial.get(civAllies.inAlliance.get(i)).firstTier.get(a));
                                    }
                                }
                            }
                            if (GameValues.hre.HRE_PRINCES_WAR_DEFEND_HRE) {
                                for (int a = 0; a < Game.alliancesSpecial.get(civAllies.inAlliance.get(i)).secondTier.size(); ++a) {
                                    if (!out.contains(Game.alliancesSpecial.get(civAllies.inAlliance.get(i)).secondTier.get(a)) && Game.alliancesSpecial.get(civAllies.inAlliance.get(i)).secondTier.get(a) != findCivAllies) {
                                        out.add(Game.alliancesSpecial.get(civAllies.inAlliance.get(i)).secondTier.get(a));
                                    }
                                }
                            }
                        }
                        else if (Game.alliancesSpecial.get(civAllies.inAlliance.get(i)).typeOfAlliance == 1) {
                            for (int a = 0; a < Game.alliancesSpecial.get(civAllies.inAlliance.get(i)).firstTier.size(); ++a) {
                                if (!out.contains(Game.alliancesSpecial.get(civAllies.inAlliance.get(i)).firstTier.get(a)) && Game.alliancesSpecial.get(civAllies.inAlliance.get(i)).firstTier.get(a) != findCivAllies) {
                                    out.add(Game.alliancesSpecial.get(civAllies.inAlliance.get(i)).firstTier.get(a));
                                }
                            }
                            for (int a = 0; a < Game.alliancesSpecial.get(civAllies.inAlliance.get(i)).secondTier.size(); ++a) {
                                if (!out.contains(Game.alliancesSpecial.get(civAllies.inAlliance.get(i)).secondTier.get(a)) && Game.alliancesSpecial.get(civAllies.inAlliance.get(i)).secondTier.get(a) != findCivAllies) {
                                    out.add(Game.alliancesSpecial.get(civAllies.inAlliance.get(i)).secondTier.get(a));
                                }
                            }
                            if (!out.contains(Game.alliancesSpecial.get(civAllies.inAlliance.get(i)).iLeaderCivID) && Game.alliancesSpecial.get(civAllies.inAlliance.get(i)).iLeaderCivID != findCivAllies) {
                                out.add(Game.alliancesSpecial.get(civAllies.inAlliance.get(i)).iLeaderCivID);
                            }
                        }
                    }
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
        if (civAllies.diplomacy.alliance.size() > 0) {
            for (final Map.Entry<Integer, Diplomacy.DiplomacyData> entry : civAllies.diplomacy.alliance.entrySet()) {
                if (entry.getValue().iCivID != enemyCiv && entry.getValue().iCivID != findCivAllies && !out.contains(entry.getValue().iCivID)) {
                    out.add(entry.getValue().iCivID);
                }
            }
        }
        if (civAllies.diplomacy.defensivePact.size() > 0) {
            for (final Map.Entry<Integer, Diplomacy.DiplomacyData> entry : civAllies.diplomacy.defensivePact.entrySet()) {
                if (entry.getValue().iCivID != enemyCiv && entry.getValue().iCivID != findCivAllies && !out.contains(entry.getValue().iCivID)) {
                    out.add(entry.getValue().iCivID);
                }
            }
        }
        if (civAllies.diplomacy.guaranteeByCivID.size() > 0) {
            for (final Map.Entry<Integer, Diplomacy.DiplomacyData> entry : civAllies.diplomacy.guaranteeByCivID.entrySet()) {
                if (entry.getValue().iCivID != enemyCiv && entry.getValue().iCivID != findCivAllies && !out.contains(entry.getValue().iCivID)) {
                    out.add(entry.getValue().iCivID);
                }
            }
        }
        if (Game.ENABLE_CALL_VASSALS) {
            for (int i = 0; i < civAllies.diplomacy.iVassalsSize; ++i) {
                if (civAllies.diplomacy.lVassals.get(i).c != enemyCiv && civAllies.diplomacy.lVassals.get(i).c != findCivAllies && !out.contains(civAllies.diplomacy.lVassals.get(i).c)) {
                    out.add(civAllies.diplomacy.lVassals.get(i).c);
                }
            }
        }
        if (GameValues.vassal.LORD_AUTO_JOIN_VASSALS_DEFENSIVE_WAR && civAllies.getPuppetOfCivID() != findCivAllies && Game.getCiv(enemyCiv).getPuppetOfCivID() != civAllies.getPuppetOfCivID() && !out.contains(civAllies.getPuppetOfCivID())) {
            out.add(civAllies.getPuppetOfCivID());
        }
        for (int i = out.size() - 1; i >= 0; --i) {
            if (out.get(i) < 1 || Game.getCiv(out.get(i)).getNumOfProvinces() <= 0) {
                out.remove(i);
            }
            else if (out.get(i) == enemyCiv || out.get(i) == findCivAllies) {
                out.remove(i);
            }
        }
        return out;
    }
    
    public static float getRelationAtWar() {
        return GameValues.diplomacy.DIPLOMACY_RELATIONS_MIN - GameValues.diplomacy.DIPLOMACY_RELATIONS_WAR_EXTRA;
    }
    
    public static boolean isAtWar(final int iCivA, final int iCivB) {
        return Game.getCiv(iCivA).diplomacy.getRelation(iCivB) <= getRelationAtWar() || (iCivA < 0 && iCivB >= 0) || (iCivA >= 0 && iCivB < 0);
    }
    
    public static final boolean sendInsult(final int iFromCivID, final int iCivB) {
        if (Game.getCiv(iFromCivID).fLegacy < -GameValues.diplomacy.DIPLOMACY_SEND_AN_INSULT_COST_LEGACY) {
            return false;
        }
        if (Game.getCiv(iFromCivID).fDiplomacy < GameValues.diplomacy.DIPLOMACY_SEND_INSULT_COST) {
            return false;
        }
        if (isAtWar(iFromCivID, iCivB)) {
            return false;
        }
        if (iCivB == Game.player.iCivID) {
            Game.player.addMessage(new PMessageInsult(iFromCivID, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
        }
        Game.getCiv(iFromCivID).diplomacy.updateRelation(iFromCivID, iCivB, GameValues.diplomacy.DIPLOMACY_SEND_AN_INSULT_DAMAGE);
        Game.getCiv(iCivB).diplomacy.updateRelation(iCivB, iFromCivID, GameValues.diplomacy.DIPLOMACY_SEND_AN_INSULT_DAMAGE);
        Game.getCiv(iFromCivID).addLegacy(GameValues.diplomacy.DIPLOMACY_SEND_AN_INSULT_COST_LEGACY);
        final Civilization civ3;
        final Civilization civ2;
        final Civilization civilization10;
        final Civilization civilization9;
        final Civilization civilization8;
        final Civilization civilization11;
        final Civilization civilization7 = civilization11 = (civilization8 = (civilization9 = (civilization10 = (civ2 = (civ3 = Game.getCiv(iFromCivID))))));
        civilization11.fDiplomacy -= GameValues.diplomacy.DIPLOMACY_SEND_INSULT_COST;
        return true;
    }
    
    public static final boolean startEspionageMission(final int spyCivID) {
        if (Game.getCiv(Game.player.iCivID).fGold < sendSpyCost(Game.player.iCivID, spyCivID)) {
            return false;
        }
        if (Game.player.playerData.espionage.espionageMission_IsAdded(spyCivID)) {
            return false;
        }
        final Civilization civ3;
        final Civilization civ2;
        final Civilization civilization10;
        final Civilization civilization9;
        final Civilization civilization8;
        final Civilization civilization11;
        final Civilization civilization7 = civilization11 = (civilization8 = (civilization9 = (civilization10 = (civ2 = (civ3 = Game.getCiv(Game.player.iCivID))))));
        civilization11.fGold -= sendSpyCost(Game.player.iCivID, spyCivID);
        Game.player.playerData.espionage.addEspionageMission(spyCivID);
        return true;
    }
    
    public static final int sendSpyCost(final int byCivID, final int spyCivID) {
        return (int)((GameValues.spy.SEND_SPY_COST + Game.getCiv(spyCivID).getNumOfProvinces() * GameValues.spy.SEND_SPY_COST_PER_PROVINCE) * (1.0f + (Game.getCiv(byCivID).diplomacy.isRival(spyCivID) ? (-GameValues.spy.SEND_SPY_TO_RIVAL_COST_REDUCTION) : 0.0f) + Game.getDistance_PercOfMax(Game.getCiv(byCivID).getCapitalProvinceID(), Game.getCiv(spyCivID).getCapitalProvinceID()) * GameValues.spy.SEND_SPY_DISTANCE_COST_MODIFIER));
    }
    
    public static final int sendSpyTime(final int byCivID, final int spyCivID) {
        return (int)((GameValues.spy.SEND_SPY_TIME + Game.getCiv(spyCivID).getNumOfProvinces() * GameValues.spy.SEND_SPY_TIME_PER_PROVINCE) * (1.0f + (Game.getCiv(byCivID).diplomacy.isRival(spyCivID) ? (-GameValues.spy.SEND_SPY_TO_RIVAL_TIME_REDUCTION) : 0.0f) + Game.getDistance_PercOfMax(Game.getCiv(byCivID).getCapitalProvinceID(), Game.getCiv(spyCivID).getCapitalProvinceID()) * GameValues.spy.SEND_SPY_DISTANCE_TIME_MODIFIER));
    }
    
    public static final boolean giftGold(final int iFromCivID, final int iToCivID, final int iClicks) {
        if (Game.getCiv(iFromCivID).fDiplomacy < GameValues.diplomacy.DIPLOMACY_GIFT_COST) {
            return false;
        }
        final float goldToGive = iClicks * GameValues.diplomacy.DIPLOMACY_GIFT_GOLD_PER_CLICK;
        if (Game.getCiv(iFromCivID).fGold < goldToGive) {
            return false;
        }
        final Civilization civ5;
        final Civilization civ4;
        final Civilization civilization22;
        final Civilization civilization21;
        final Civilization civilization20;
        final Civilization civilization31;
        final Civilization civilization19 = civilization31 = (civilization20 = (civilization21 = (civilization22 = (civ4 = (civ5 = Game.getCiv(iFromCivID))))));
        civilization31.fDiplomacy -= GameValues.diplomacy.DIPLOMACY_GIFT_COST;
        final Civilization civ7;
        final Civilization civ6;
        final Civilization civilization26;
        final Civilization civilization25;
        final Civilization civilization24;
        final Civilization civilization32;
        final Civilization civilization23 = civilization32 = (civilization24 = (civilization25 = (civilization26 = (civ6 = (civ7 = Game.getCiv(iFromCivID))))));
        civilization32.fGold -= goldToGive;
        final Civilization civ9;
        final Civilization civ8;
        final Civilization civilization30;
        final Civilization civilization29;
        final Civilization civilization28;
        final Civilization civilization33;
        final Civilization civilization27 = civilization33 = (civilization28 = (civilization29 = (civilization30 = (civ8 = (civ9 = Game.getCiv(iToCivID))))));
        civilization33.fGold += goldToGive;
        if (!isAtWar(iFromCivID, iToCivID)) {
            Game.getCiv(iFromCivID).diplomacy.setRelation(iFromCivID, iToCivID, Game.getCiv(iFromCivID).diplomacy.getRelation(iToCivID) + iClicks * GameValues.diplomacy.DIPLOMACY_GIFT_RELATION_PER_CLICK);
            Game.getCiv(iToCivID).diplomacy.setRelation(iToCivID, iFromCivID, Game.getCiv(iToCivID).diplomacy.getRelation(iFromCivID) + iClicks * GameValues.diplomacy.DIPLOMACY_GIFT_RELATION_PER_CLICK);
        }
        if (iToCivID == Game.player.iCivID) {
            Game.player.addMessage(new PMessageGift(iFromCivID, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS, (int)goldToGive));
        }
        return true;
    }
    
    public static final boolean offerAlliance(final int iFromCivID, final int iToCivID) {
        if (Game.getCiv(iFromCivID).fDiplomacy < GameValues.diplomacy.DIPLOMACY_ALLIANCE_COST) {
            return false;
        }
        if (isAtWar(iFromCivID, iToCivID)) {
            return false;
        }
        final Civilization civ3;
        final Civilization civ2;
        final Civilization civilization10;
        final Civilization civilization9;
        final Civilization civilization8;
        final Civilization civilization11;
        final Civilization civilization7 = civilization11 = (civilization8 = (civilization9 = (civilization10 = (civ2 = (civ3 = Game.getCiv(iFromCivID))))));
        civilization11.fDiplomacy -= GameValues.diplomacy.DIPLOMACY_ALLIANCE_COST;
        Game.player.addMessage(new PMessageAlliance(iFromCivID, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
        return true;
    }
    
    public static int getMaxNumberOfAlliances(final int civID) {
        return GameValues.diplomacy.BASE_MAX_NUM_OF_ALLIANCES + Game.getCiv(civID).civBonuses.MaxNumOfAlliances;
    }
    
    public static boolean getCanJoinNewAlliance(final int civID) {
        return getMaxNumberOfAlliances(civID) > Game.getCiv(civID).diplomacy.alliance.size();
    }
    
    public static final void acceptAllianceOffer(final int iCivA, final int iCivB) {
        if (iCivA == Game.player.iCivID || iCivB == Game.player.iCivID) {
            InGame_Info.iCivID = iCivA;
            InGame_Info.iCivID2 = iCivB;
            Game.menuManager.rebuildInGame_Info(Game.lang.get("AllianceProposalAccepted"), Game.getCiv(iCivA).getCivName() + " - " + Game.getCiv(iCivB).getCivName());
            InGame_Info.imgID = Images.infoDiplomacy;
            SteamAchievementsManager.unlockAchievement(SteamAchievementsManager.ALLIANCE);
        }
        addAlliance(iCivA, iCivB);
        if (iCivA != Game.player.iCivID && GameValues.value.DRAW_DIPLOMACY_LINES_WHEN_ALLIANCE_SIGNED && Game.getCiv(iCivA).getCapitalProvinceID() >= 0 && Game.getCiv(iCivB).getCapitalProvinceID() >= 0) {
            ProvinceDraw.addDiplomacyLines(Game.getCiv(iCivA).getCapitalProvinceID(), Game.getCiv(iCivB).getCapitalProvinceID(), DiplomacyManager.COLOR_ALLIANCE);
        }
        Game.getCiv(iCivA).aiCivDiplomacy.removePreparingForAllianceWithCivID(iCivB);
        Game.getCiv(iCivB).aiCivDiplomacy.removePreparingForAllianceWithCivID(iCivA);
    }
    
    public static final void declineAllianceOffer(final int iCivA, final int iCivB) {
        if (iCivA == Game.player.iCivID || iCivB == Game.player.iCivID) {
            InGame_Info.iCivID = iCivA;
            InGame_Info.iCivID2 = iCivB;
            Game.menuManager.rebuildInGame_Info(Game.lang.get("AllianceProposalWasRejected"), Game.getCiv(iCivA).getCivName() + " - " + Game.getCiv(iCivB).getCivName());
            InGame_Info.imgID = Images.infoDiplomacy;
        }
    }
    
    public static final void addAlliance(final int iCivA, final int iCivB) {
        if (iCivA == iCivB) {
            return;
        }
        if (isAtWar(iCivA, iCivB)) {
            return;
        }
        if (!getCanJoinNewAlliance(iCivA)) {
            return;
        }
        if (!getCanJoinNewAlliance(iCivB)) {
            return;
        }
        final int extraRand = (GameValues.diplomacy.DIPLOMACY_ALLIANCE_EXPIRES_EXTRA_RANDOM > 0) ? Game.oR.nextInt(GameValues.diplomacy.DIPLOMACY_ALLIANCE_EXPIRES_EXTRA_RANDOM) : 0;
        Game.getCiv(iCivA).diplomacy.addAlliance(iCivB, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_ALLIANCE_EXPIRES + extraRand);
        Game.getCiv(iCivB).diplomacy.addAlliance(iCivA, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_ALLIANCE_EXPIRES + extraRand);
        if (iCivA == Game.player.iCivID) {
            Game.getCiv(iCivB).updateArmyImgID();
            Game.player.fog.updateFogOfWar_Civ(iCivB);
        }
        else if (iCivB == Game.player.iCivID) {
            Game.getCiv(iCivA).updateArmyImgID();
            Game.player.fog.updateFogOfWar_Civ(iCivA);
        }
    }
    
    public static final void removeAlliance(final int iCivA, final int iCivB) {
        Game.getCiv(iCivA).diplomacy.removeAlliance(iCivB);
        Game.getCiv(iCivB).diplomacy.removeAlliance(iCivA);
        if (iCivA == Game.player.iCivID) {
            Game.getCiv(iCivB).updateArmyImgID();
            Game.player.fog.updateFogOfWar_Civ(iCivB);
        }
        else if (iCivB == Game.player.iCivID) {
            Game.getCiv(iCivA).updateArmyImgID();
            Game.player.fog.updateFogOfWar_Civ(iCivA);
        }
    }
    
    public static final float getAlliance_Score_BASE_VALUE(final int civFrom, final int civTo) {
        return GameValues.diplomacy.ALLIANCE_SCORE_BASE_VALUE * Math.max(GameValues.diplomacy.ALLIANCE_SCORE_BASE_RANK_SCORE_MIN, Math.min(GameValues.diplomacy.ALLIANCE_SCORE_BASE_RANK_SCORE_MAX, Game.getCiv(civTo).iCivRankScore / Game.getCiv(civFrom).iCivRankScore));
    }
    
    public static final float getAlliance_Score_SameRivals(final int civFrom, final int civTo) {
        float out = 0.0f;
        if (Game.getCiv(civFrom).diplomacy.rivals.size() > 0) {
            final Iterator<Map.Entry<Integer, Diplomacy.DiplomacyData>> it = Game.getCiv(civFrom).diplomacy.rivals.entrySet().iterator();
            while (it.hasNext()) {
                final Diplomacy.DiplomacyData tData = (Diplomacy.DiplomacyData)it.next().getValue();
                if (Game.getCiv(civTo).diplomacy.isRival(tData.iCivID)) {
                    out += GameValues.diplomacy.ALLIANCE_SCORE_PER_SAME_RIVAL;
                }
            }
        }
        return out;
    }
    
    public static final int getAlliance_Score(final int civFrom, final int civTo) {
        if (!getCanJoinNewAlliance(civFrom) || !getCanJoinNewAlliance(civTo)) {
            return GameValues.diplomacy.ALLIANCE_SCORE_MAX_NUM_OF_ALLIANCES;
        }
        if (Game.getCiv(civFrom).diplomacy.isAtWar()) {
            return (int)GameValues.diplomacy.ALLIANCE_SCORE_AT_WAR;
        }
        return (int)(getAlliance_Score_BASE_VALUE(civFrom, civTo) + Game.getCiv(civTo).diplomacy.getRelation(civFrom) * GameValues.diplomacy.ALLIANCE_SCORE_PER_RELATION + GameValues.diplomacy.ALLIANCE_SCORE_DISTANCE * Game.getDistance_PercOfMax(Game.getCiv(civFrom).getCapitalProvinceID(), Game.getCiv(civTo).getCapitalProvinceID()) + (Game.getCiv(civTo).diplomacy.isRival(civFrom) ? GameValues.diplomacy.ALLIANCE_SCORE_RIVALS : 0.0f) + (Game.getCiv(civFrom).diplomacy.isRival(civTo) ? GameValues.diplomacy.ALLIANCE_SCORE_RIVALS : 0.0f) + getAlliance_Score_SameRivals(civFrom, civTo) + ((Game.getCiv(civTo).getPuppetOfCivID() == civFrom) ? GameValues.diplomacy.ALLIANCE_SCORE_FOR_VASSAL_OF_CIV : 0.0f) + Game.getCiv(civFrom).getAggressiveExpansion() * GameValues.diplomacy.ALLIANCE_SCORE_PER_AGGRESSIVE_EXPANSION + ((Game.getCiv(civFrom).getReligionID() != Game.getCiv(civTo).getReligionID()) ? ((Game.religionManager.getReligion(Game.getCiv(civFrom).getReligionID()).ReligionGroupID != Game.religionManager.getReligion(Game.getCiv(civTo).getReligionID()).ReligionGroupID) ? GameValues.diplomacy.ALLIANCE_SCORE_DIFFERENT_RELIGION_GROUP : GameValues.diplomacy.ALLIANCE_SCORE_DIFFERENT_RELIGION) : 0.0f) + ((Game.getCiv(civFrom).getIdeologyID() != Game.getCiv(civTo).getIdeologyID()) ? ((Game.ideologiesManager.getIdeology(Game.getCiv(civFrom).getIdeologyID()).GOV_GROUP_ID != Game.ideologiesManager.getIdeology(Game.getCiv(civTo).getIdeologyID()).GOV_GROUP_ID) ? GameValues.diplomacy.ALLIANCE_SCORE_DIFFERENT_GOVERNMENT_GROUP : GameValues.diplomacy.ALLIANCE_SCORE_DIFFERENT_GOVERNMENT) : 0.0f));
    }
    
    public static final float getDefensivePact_Score_BASE_VALUE(final int civFrom, final int civTo) {
        return GameValues.diplomacy.DEFENSIVE_PACT_SCORE_BASE_VALUE * Math.max(GameValues.diplomacy.DEFENSIVE_PACT_SCORE_BASE_RANK_SCORE_MIN, Math.min(GameValues.diplomacy.DEFENSIVE_PACT_SCORE_BASE_RANK_SCORE_MAX, Game.getCiv(civTo).iCivRankScore / Game.getCiv(civFrom).iCivRankScore));
    }
    
    public static final float getDefensivePact_Score_SameRivals(final int civFrom, final int civTo) {
        float out = 0.0f;
        if (Game.getCiv(civFrom).diplomacy.rivals.size() > 0) {
            final Iterator<Map.Entry<Integer, Diplomacy.DiplomacyData>> it = Game.getCiv(civFrom).diplomacy.rivals.entrySet().iterator();
            while (it.hasNext()) {
                final Diplomacy.DiplomacyData tData = (Diplomacy.DiplomacyData)it.next().getValue();
                if (Game.getCiv(civTo).diplomacy.isRival(tData.iCivID)) {
                    out += GameValues.diplomacy.DEFENSIVE_PACT_SCORE_PER_SAME_RIVAL;
                }
            }
        }
        return out;
    }
    
    public static final int getDefensivePact_Score(final int civFrom, final int civTo) {
        if (Game.getCiv(civFrom).diplomacy.isAtWar()) {
            return (int)GameValues.diplomacy.DEFENSIVE_PACT_SCORE_AT_WAR;
        }
        return (int)(getDefensivePact_Score_BASE_VALUE(civFrom, civTo) + Game.getCiv(civTo).diplomacy.getRelation(civFrom) * GameValues.diplomacy.DEFENSIVE_PACT_SCORE_PER_RELATION + GameValues.diplomacy.DEFENSIVE_PACT_SCORE_DISTANCE * Game.getDistance_PercOfMax(Game.getCiv(civFrom).getCapitalProvinceID(), Game.getCiv(civTo).getCapitalProvinceID()) + (Game.getCiv(civTo).diplomacy.isRival(civFrom) ? GameValues.diplomacy.DEFENSIVE_PACT_SCORE_RIVALS : 0.0f) + (Game.getCiv(civFrom).diplomacy.isRival(civTo) ? GameValues.diplomacy.DEFENSIVE_PACT_SCORE_RIVALS : 0.0f) + getDefensivePact_Score_SameRivals(civFrom, civTo) + Game.getCiv(civFrom).getAggressiveExpansion() * GameValues.diplomacy.DEFENSIVE_PACT_SCORE_PER_AGGRESSIVE_EXPANSION + (Game.getCiv(civFrom).diplomacy.defensivePact.size() + Game.getCiv(civTo).diplomacy.defensivePact.size()) * GameValues.diplomacy.DEFENSIVE_PACT_SCORE_PER_DEFENSIVE_PACT + ((Game.getCiv(civFrom).getReligionID() != Game.getCiv(civTo).getReligionID()) ? ((Game.religionManager.getReligion(Game.getCiv(civFrom).getReligionID()).ReligionGroupID != Game.religionManager.getReligion(Game.getCiv(civTo).getReligionID()).ReligionGroupID) ? GameValues.diplomacy.DEFENSIVE_PACT_SCORE_DIFFERENT_RELIGION_GROUP : GameValues.diplomacy.DEFENSIVE_PACT_SCORE_DIFFERENT_RELIGION) : 0.0f) + ((Game.getCiv(civFrom).getIdeologyID() != Game.getCiv(civTo).getIdeologyID()) ? ((Game.ideologiesManager.getIdeology(Game.getCiv(civFrom).getIdeologyID()).GOV_GROUP_ID != Game.ideologiesManager.getIdeology(Game.getCiv(civTo).getIdeologyID()).GOV_GROUP_ID) ? GameValues.diplomacy.DEFENSIVE_PACT_SCORE_DIFFERENT_GOVERNMENT_GROUP : GameValues.diplomacy.DEFENSIVE_PACT_SCORE_DIFFERENT_GOVERNMENT) : 0.0f));
    }
    
    public static final float getNonAggression_Score_BASE_VALUE(final int civFrom, final int civTo) {
        return GameValues.diplomacy.NON_AGGRESSION_SCORE_BASE_VALUE * Math.max(GameValues.diplomacy.NON_AGGRESSION_SCORE_BASE_RANK_SCORE_MIN, Math.min(GameValues.diplomacy.NON_AGGRESSION_SCORE_BASE_RANK_SCORE_MAX, Game.getCiv(civTo).iCivRankScore / Game.getCiv(civFrom).iCivRankScore));
    }
    
    public static final float getNonAggression_Score_SameRivals(final int civFrom, final int civTo) {
        float out = 0.0f;
        if (Game.getCiv(civFrom).diplomacy.rivals.size() > 0) {
            final Iterator<Map.Entry<Integer, Diplomacy.DiplomacyData>> it = Game.getCiv(civFrom).diplomacy.rivals.entrySet().iterator();
            while (it.hasNext()) {
                final Diplomacy.DiplomacyData tData = (Diplomacy.DiplomacyData)it.next().getValue();
                if (Game.getCiv(civTo).diplomacy.isRival(tData.iCivID)) {
                    out += GameValues.diplomacy.NON_AGGRESSION_SCORE_PER_SAME_RIVAL;
                }
            }
        }
        return out;
    }
    
    public static final int getNonAggression_Score(final int civFrom, final int civTo) {
        if (Game.getCiv(civFrom).diplomacy.isAtWar()) {
            return (int)GameValues.diplomacy.NON_AGGRESSION_SCORE_AT_WAR;
        }
        return (int)(getDefensivePact_Score_BASE_VALUE(civFrom, civTo) + Game.getCiv(civTo).diplomacy.getRelation(civFrom) * GameValues.diplomacy.NON_AGGRESSION_SCORE_PER_RELATION + GameValues.diplomacy.NON_AGGRESSION_SCORE_DISTANCE * Game.getDistance_PercOfMax(Game.getCiv(civFrom).getCapitalProvinceID(), Game.getCiv(civTo).getCapitalProvinceID()) + (Game.getCiv(civTo).diplomacy.isRival(civFrom) ? GameValues.diplomacy.NON_AGGRESSION_SCORE_RIVALS : 0.0f) + (Game.getCiv(civFrom).diplomacy.isRival(civTo) ? GameValues.diplomacy.NON_AGGRESSION_SCORE_RIVALS : 0.0f) + (Game.getCiv(civFrom).diplomacy.nonAggressionPact.size() + Game.getCiv(civTo).diplomacy.nonAggressionPact.size()) * GameValues.diplomacy.NON_AGGRESSION_SCORE_PER_NON_AGGRESSION_PACTS + getNonAggression_Score_SameRivals(civFrom, civTo) + ((Game.getCiv(civFrom).getReligionID() != Game.getCiv(civTo).getReligionID()) ? ((Game.religionManager.getReligion(Game.getCiv(civFrom).getReligionID()).ReligionGroupID != Game.religionManager.getReligion(Game.getCiv(civTo).getReligionID()).ReligionGroupID) ? GameValues.diplomacy.NON_AGGRESSION_SCORE_DIFFERENT_RELIGION_GROUP : GameValues.diplomacy.NON_AGGRESSION_SCORE_DIFFERENT_RELIGION) : 0.0f) + ((Game.getCiv(civFrom).getIdeologyID() != Game.getCiv(civTo).getIdeologyID()) ? ((Game.ideologiesManager.getIdeology(Game.getCiv(civFrom).getIdeologyID()).GOV_GROUP_ID != Game.ideologiesManager.getIdeology(Game.getCiv(civTo).getIdeologyID()).GOV_GROUP_ID) ? GameValues.diplomacy.NON_AGGRESSION_SCORE_DIFFERENT_GOVERNMENT_GROUP : GameValues.diplomacy.NON_AGGRESSION_SCORE_DIFFERENT_GOVERNMENT) : 0.0f));
    }
    
    public static final void addVassal(final int iCivA, final int iCivB) {
        if (iCivA == iCivB) {
            return;
        }
        if (isAtWar(iCivA, iCivB)) {
            return;
        }
        Game.getCiv(iCivB).setPuppetOfCivID(iCivA);
        if (iCivA == Game.player.iCivID) {
            Game.getCiv(iCivB).updateArmyImgID();
            Game.player.fog.updateFogOfWar_Civ(iCivB);
        }
        else if (iCivB == Game.player.iCivID) {
            Game.getCiv(iCivA).updateArmyImgID();
            Game.player.fog.updateFogOfWar_Civ(iCivA);
        }
    }
    
    public static final void removeVassal(final int iCivA) {
        if (Game.getCiv(iCivA).getPuppetOfCivID() != iCivA) {
            final int updateFog = Game.getCiv(iCivA).getPuppetOfCivID();
            Game.getCiv(iCivA).setPuppetOfCivID(iCivA);
            if (iCivA == Game.player.iCivID) {
                Game.getCiv(updateFog).updateArmyImgID();
                Game.player.fog.updateFogOfWar_Civ(updateFog);
            }
            else if (updateFog == Game.player.iCivID) {
                Game.getCiv(iCivA).updateArmyImgID();
                Game.player.fog.updateFogOfWar_Civ(iCivA);
            }
        }
    }
    
    public static final void addTruce(final int iCivA, final int iCivB) {
        if (iCivA == iCivB) {
            return;
        }
        if (isAtWar(iCivA, iCivB)) {
            return;
        }
        Game.getCiv(iCivA).diplomacy.addTruce(iCivB, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_TRUCE_EXPIRES);
        Game.getCiv(iCivB).diplomacy.addTruce(iCivA, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_TRUCE_EXPIRES);
    }
    
    public static final void removeTruce(final int iCivA, final int iCivB) {
        Game.getCiv(iCivA).diplomacy.removeTruce(iCivB);
        Game.getCiv(iCivB).diplomacy.removeTruce(iCivA);
    }
    
    public static final boolean offerDefensivePact(final int iFromCivID, final int iToCivID) {
        if (Game.getCiv(iFromCivID).fDiplomacy < GameValues.diplomacy.DIPLOMACY_DEFENSIVE_PACT_COST) {
            return false;
        }
        if (isAtWar(iFromCivID, iToCivID)) {
            return false;
        }
        final Civilization civ3;
        final Civilization civ2;
        final Civilization civilization10;
        final Civilization civilization9;
        final Civilization civilization8;
        final Civilization civilization11;
        final Civilization civilization7 = civilization11 = (civilization8 = (civilization9 = (civilization10 = (civ2 = (civ3 = Game.getCiv(iFromCivID))))));
        civilization11.fDiplomacy -= GameValues.diplomacy.DIPLOMACY_DEFENSIVE_PACT_COST;
        if (iToCivID == Game.player.iCivID) {
            Game.player.addMessage(new PMessageDefensivePact(iFromCivID, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
            return true;
        }
        if (getDefensivePact_Score(iFromCivID, iToCivID) >= 0) {
            acceptDefensivePactOffer(iFromCivID, iToCivID);
        }
        else {
            declineDefensivePactOffer(iFromCivID, iToCivID);
        }
        return true;
    }
    
    public static final void acceptDefensivePactOffer(final int iCivA, final int iCivB) {
        if (iCivA == Game.player.iCivID || iCivB == Game.player.iCivID) {
            InGame_Info.iCivID = iCivA;
            InGame_Info.iCivID2 = iCivB;
            Game.menuManager.rebuildInGame_Info(Game.lang.get("DefensivePact"), Game.getCiv(iCivA).getCivName() + " - " + Game.getCiv(iCivB).getCivName());
            InGame_Info.imgID = Images.infoDiplomacy;
        }
        addDefensivePact(iCivA, iCivB);
        if (iCivA != Game.player.iCivID && GameValues.value.DRAW_DIPLOMACY_LINES_WHEN_DEFENSIVE_PACT_SIGNED && Game.getCiv(iCivA).getCapitalProvinceID() >= 0 && Game.getCiv(iCivB).getCapitalProvinceID() >= 0) {
            ProvinceDraw.addDiplomacyLines(Game.getCiv(iCivA).getCapitalProvinceID(), Game.getCiv(iCivB).getCapitalProvinceID(), DiplomacyManager.COLOR_DEFENSIVE_PACT);
        }
    }
    
    public static final void declineDefensivePactOffer(final int iCivA, final int iCivB) {
        if (iCivA == Game.player.iCivID || iCivB == Game.player.iCivID) {
            InGame_Info.iCivID = iCivA;
            InGame_Info.iCivID2 = iCivB;
            Game.menuManager.rebuildInGame_Info(Game.lang.get("ProposalWasRejected"), Game.getCiv(iCivA).getCivName() + " - " + Game.getCiv(iCivB).getCivName());
            InGame_Info.imgID = Images.infoDiplomacy;
        }
    }
    
    public static final void addDefensivePact(final int iCivA, final int iCivB) {
        if (iCivA == iCivB) {
            return;
        }
        if (isAtWar(iCivA, iCivB)) {
            return;
        }
        Game.getCiv(iCivA).diplomacy.addDefensivePact(iCivB, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_DEFENSIVE_PACT_EXPIRES);
        Game.getCiv(iCivB).diplomacy.addDefensivePact(iCivA, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_DEFENSIVE_PACT_EXPIRES);
    }
    
    public static final void removeDefensivePact(final int iCivA, final int iCivB) {
        Game.getCiv(iCivA).diplomacy.removeDefensivePact(iCivB);
        Game.getCiv(iCivB).diplomacy.removeDefensivePact(iCivA);
    }
    
    public static final boolean offerNonAggressionPact(final int iFromCivID, final int iToCivID) {
        if (Game.getCiv(iFromCivID).fDiplomacy < GameValues.diplomacy.DIPLOMACY_NON_AGGRESSION_PACT_COST) {
            return false;
        }
        if (isAtWar(iFromCivID, iToCivID)) {
            return false;
        }
        final Civilization civ3;
        final Civilization civ2;
        final Civilization civilization10;
        final Civilization civilization9;
        final Civilization civilization8;
        final Civilization civilization11;
        final Civilization civilization7 = civilization11 = (civilization8 = (civilization9 = (civilization10 = (civ2 = (civ3 = Game.getCiv(iFromCivID))))));
        civilization11.fDiplomacy -= GameValues.diplomacy.DIPLOMACY_NON_AGGRESSION_PACT_COST;
        if (iToCivID == Game.player.iCivID) {
            Game.player.addMessage(new PMessageNonAggressionPact(iFromCivID, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
            return true;
        }
        if (getNonAggression_Score(iFromCivID, iToCivID) >= 0) {
            acceptNonAggressionOffer(iFromCivID, iToCivID);
        }
        else {
            declineNonAggressionOffer(iFromCivID, iToCivID);
        }
        return true;
    }
    
    public static final void acceptNonAggressionOffer(final int iCivA, final int iCivB) {
        if (iCivA == Game.player.iCivID || iCivB == Game.player.iCivID) {
            InGame_Info.iCivID = iCivA;
            InGame_Info.iCivID2 = iCivB;
            Game.menuManager.rebuildInGame_Info(Game.lang.get("NonAggressionPact"), Game.getCiv(iCivA).getCivName() + " - " + Game.getCiv(iCivB).getCivName());
            InGame_Info.imgID = Images.infoDiplomacy;
        }
        addNonAggressionPact(iCivA, iCivB);
        if (iCivA != Game.player.iCivID && GameValues.value.DRAW_DIPLOMACY_LINES_WHEN_NON_AGGRESSION_SIGNED && Game.getCiv(iCivA).getCapitalProvinceID() >= 0 && Game.getCiv(iCivB).getCapitalProvinceID() >= 0) {
            ProvinceDraw.addDiplomacyLines(Game.getCiv(iCivA).getCapitalProvinceID(), Game.getCiv(iCivB).getCapitalProvinceID(), DiplomacyManager.COLOR_NON_AGGRESSION_PACT);
        }
    }
    
    public static final void declineNonAggressionOffer(final int iCivA, final int iCivB) {
        if (iCivA == Game.player.iCivID || iCivB == Game.player.iCivID) {
            InGame_Info.iCivID = iCivA;
            InGame_Info.iCivID2 = iCivB;
            Game.menuManager.rebuildInGame_Info(Game.lang.get("ProposalWasRejected"), Game.getCiv(iCivA).getCivName() + " - " + Game.getCiv(iCivB).getCivName());
            InGame_Info.imgID = Images.infoDiplomacy;
        }
    }
    
    public static final void addNonAggressionPact(final int iCivA, final int iCivB) {
        if (iCivA == iCivB) {
            return;
        }
        if (isAtWar(iCivA, iCivB)) {
            return;
        }
        Game.getCiv(iCivA).diplomacy.addNonAggressionPact(iCivB, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_NON_AGGRESSION_PACT_EXPIRES);
        Game.getCiv(iCivB).diplomacy.addNonAggressionPact(iCivA, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_NON_AGGRESSION_PACT_EXPIRES);
    }
    
    public static final void removeNonAggressionPact(final int iCivA, final int iCivB) {
        Game.getCiv(iCivA).diplomacy.removeNonAggressionPact(iCivB);
        Game.getCiv(iCivB).diplomacy.removeNonAggressionPact(iCivA);
    }
    
    public static final boolean offerMilitaryAccess(final int iFromCivID, final int iToCivID) {
        if (Game.getCiv(iFromCivID).fDiplomacy < GameValues.diplomacy.DIPLOMACY_OFFER_MILITARY_ACCESS_COST) {
            return false;
        }
        if (isAtWar(iFromCivID, iToCivID)) {
            return false;
        }
        final Civilization civ3;
        final Civilization civ2;
        final Civilization civilization10;
        final Civilization civilization9;
        final Civilization civilization8;
        final Civilization civilization11;
        final Civilization civilization7 = civilization11 = (civilization8 = (civilization9 = (civilization10 = (civ2 = (civ3 = Game.getCiv(iFromCivID))))));
        civilization11.fDiplomacy -= GameValues.diplomacy.DIPLOMACY_OFFER_MILITARY_ACCESS_COST;
        if (iToCivID == Game.player.iCivID) {}
        acceptOfferMilitaryAccess(iFromCivID, iToCivID);
        return true;
    }
    
    public static final void acceptOfferMilitaryAccess(final int iCivA, final int iCivB) {
        if (iCivA == Game.player.iCivID || iCivB == Game.player.iCivID) {
            InGame_Info.iCivID = iCivB;
            InGame_Info.iCivID2 = iCivA;
            Game.menuManager.rebuildInGame_Info(Game.lang.get("MilitaryAccess"), Game.getCiv(iCivB).getCivName() + " - " + Game.getCiv(iCivA).getCivName());
            InGame_Info.imgID = Images.infoDiplomacy;
        }
        addMilitaryAccess(iCivB, iCivA);
    }
    
    public static final boolean demandMilitaryAccess(final int fromCivID, final int toCivID) {
        if (Game.getCiv(fromCivID).fDiplomacy < GameValues.diplomacy.DIPLOMACY_DEMAND_MILITARY_ACCESS_COST) {
            return false;
        }
        if (isAtWar(fromCivID, toCivID)) {
            return false;
        }
        final Civilization civ3;
        final Civilization civ2;
        final Civilization civilization10;
        final Civilization civilization9;
        final Civilization civilization8;
        final Civilization civilization11;
        final Civilization civilization7 = civilization11 = (civilization8 = (civilization9 = (civilization10 = (civ2 = (civ3 = Game.getCiv(fromCivID))))));
        civilization11.fDiplomacy -= GameValues.diplomacy.DIPLOMACY_DEMAND_MILITARY_ACCESS_COST;
        if (toCivID == Game.player.iCivID) {
            Game.player.addMessage(new PMessageDemandMilitaryAccess(fromCivID, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
            return true;
        }
        if (Game.getCiv(fromCivID).diplomacy.getRelation(toCivID) < Game.aiValuesDiplomacy.AI_MILITARY_ACCESS_DECLINE_IF_RELATIONS_BELOW + (Game.getCiv(toCivID).diplomacy.isRival(fromCivID) ? Game.aiValuesDiplomacy.AI_MILITARY_ACCESS_DECLINE_IF_RELATIONS_BELOW_EXTRA_RIVAL : 0) + ((Game.getCiv(fromCivID).getReligionID() != Game.getCiv(toCivID).getReligionID()) ? Game.aiValuesDiplomacy.AI_MILITARY_ACCESS_DECLINE_IF_RELATIONS_BELOW_EXTRA_DIFFERENT_RELIGION : 0) + ((Game.getCiv(fromCivID).getIdeologyID() != Game.getCiv(toCivID).getIdeologyID()) ? Game.aiValuesDiplomacy.AI_MILITARY_ACCESS_DECLINE_IF_RELATIONS_BELOW_EXTRA_DIFFERENT_GOVERNMENT : 0)) {
            if (fromCivID == Game.player.iCivID) {
                Game.player.addMessage(new PMessageMilitaryAccessRefused(toCivID, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
            }
            return false;
        }
        acceptDemandMilitaryAccess(fromCivID, toCivID);
        return true;
    }
    
    public static final void acceptDemandMilitaryAccess(final int iCivA, final int iCivB) {
        if (iCivA == Game.player.iCivID || iCivB == Game.player.iCivID) {
            InGame_Info.iCivID = iCivA;
            InGame_Info.iCivID2 = iCivB;
            Game.menuManager.rebuildInGame_Info(Game.lang.get("MilitaryAccess"), Game.getCiv(iCivA).getCivName() + " - " + Game.getCiv(iCivB).getCivName());
            InGame_Info.imgID = Images.infoDiplomacy;
        }
        addMilitaryAccess(iCivA, iCivB);
    }
    
    public static final boolean addMilitaryAccess(final int iCivA, final int iCivB) {
        if (iCivA == iCivB) {
            return false;
        }
        if (isAtWar(iCivA, iCivB)) {
            return false;
        }
        Game.getCiv(iCivA).diplomacy.addMilitaryAccess(iCivB, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MILITARY_ACCESS_EXPIRES);
        return true;
    }
    
    public static final void removeMilitaryAccess(final int iCivA, final int iCivB) {
        Game.getCiv(iCivA).diplomacy.removeMilitaryAccess(iCivB);
    }
    
    public static final boolean offerGuarantee(final int iFromCivID, final int iToCivID) {
        if (Game.getCiv(iFromCivID).fDiplomacy < GameValues.diplomacy.DIPLOMACY_GUARANTEE_COST) {
            return false;
        }
        if (isAtWar(iFromCivID, iToCivID)) {
            return false;
        }
        final Civilization civ3;
        final Civilization civ2;
        final Civilization civilization10;
        final Civilization civilization9;
        final Civilization civilization8;
        final Civilization civilization11;
        final Civilization civilization7 = civilization11 = (civilization8 = (civilization9 = (civilization10 = (civ2 = (civ3 = Game.getCiv(iFromCivID))))));
        civilization11.fDiplomacy -= GameValues.diplomacy.DIPLOMACY_GUARANTEE_COST;
        if (iToCivID == Game.player.iCivID) {
            Game.player.addMessage(new PMessageGuarantee(iFromCivID, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
            return true;
        }
        acceptGuaranteeOffer(iFromCivID, iToCivID);
        return true;
    }
    
    public static final void acceptGuaranteeOffer(final int iCivA, final int iCivB) {
        if (iCivA == Game.player.iCivID || iCivB == Game.player.iCivID) {
            InGame_Info.iCivID = iCivA;
            InGame_Info.iCivID2 = iCivB;
            Game.menuManager.rebuildInGame_Info(Game.lang.get("GuaranteeIndependence"), Game.getCiv(iCivA).getCivName() + " - " + Game.getCiv(iCivB).getCivName());
            InGame_Info.imgID = Images.infoDiplomacy;
        }
        addGuarantee(iCivA, iCivB);
    }
    
    public static final void addGuarantee(final int iCivA, final int iCivB) {
        if (iCivA == iCivB) {
            return;
        }
        if (isAtWar(iCivA, iCivB)) {
            return;
        }
        Game.getCiv(iCivA).diplomacy.addGuarantee(iCivB, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_GUARANTEE_EXPIRES);
        Game.getCiv(iCivB).diplomacy.addGuaranteeByCivID(iCivA, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_GUARANTEE_EXPIRES);
    }
    
    public static final void removeGuarantee(final int iCivA, final int iCivB) {
        Game.getCiv(iCivA).diplomacy.removeGuarantee(iCivB);
        Game.getCiv(iCivB).diplomacy.removeGuaranteeByCivID(iCivA);
    }
    
    public static final boolean whitePeace(final String warKey) {
        try {
            if (WarManager.lWars.containsKey(warKey)) {
                WarManager.lWars.get(warKey).peaceTreaty();
                WarManager.lWars.remove(warKey);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            return false;
        }
        return true;
    }
    
    public static final boolean surrenderWar(final String warKey, final int civID) {
        try {
            if (WarManager.lWars.containsKey(warKey) && (WarManager.lWars.get(warKey).lAggressors.get(0).iCivID == civID || WarManager.lWars.get(warKey).lDefenders.get(0).iCivID == civID)) {
                WarManager.lWars.get(warKey).warScore = (WarManager.lWars.get(warKey).isAggressor(civID) ? -1 : 1) * GameValues.peace.WAR_MAKE_DEMANDS_MIN_WAR_SCORE * 2.0f;
                AI_PeaceTreaty.peaceTreaty(warKey, WarManager.lWars.get(warKey).isDefender(civID) ? WarManager.lWars.get(warKey).lAggressors.get(0).iCivID : WarManager.lWars.get(warKey).lDefenders.get(0).iCivID, civID);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            return false;
        }
        return true;
    }
    
    static {
        COLOR_WAR = new Color(0.78431374f, 0.1254902f, 0.14117648f, 1.0f);
        COLOR_INSULT = new Color(0.8627451f, 0.39215687f, 0.0f, 1.0f);
        COLOR_ALLIANCE = new Color(0.0f, 0.627451f, 1.0f, 1.0f);
        COLOR_DEFENSIVE_PACT = new Color(0.41960785f, 0.23529412f, 0.7490196f, 1.0f);
        COLOR_NON_AGGRESSION_PACT = new Color(0.78431374f, 0.78431374f, 0.19607843f, 1.0f);
        COLOR_MILITARY_ACCESS = new Color(0.08627451f, 0.08627451f, 0.5411765f, 1.0f);
        COLOR_GUARANTEE = new Color(0.74509805f, 0.23529412f, 0.6156863f, 1.0f);
        COLOR_PEACE = new Color(0.78431374f, 0.92156863f, 0.7254902f, 1.0f);
        COLOR_SPY = new Color(0.8235294f, 0.4117647f, 0.13725491f, 1.0f);
        COLOR_RED = new Color(0.70980394f, 0.17254902f, 0.1254902f, 1.0f);
        COLOR_NEUTRAL = new Color(0.92156863f, 0.9411765f, 0.8627451f, 1.0f);
        COLOR_GREEN = new Color(0.27450982f, 0.50980395f, 0.23529412f, 1.0f);
        COLOR_BATTLE = new Color(0.64705884f, 0.078431375f, 0.039215688f, 0.75f);
        DiplomacyManager.inAnimation = false;
        DiplomacyManager.fAnimationPerc = 0.0f;
    }
}
