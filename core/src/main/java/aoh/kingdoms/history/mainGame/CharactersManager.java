// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski;

import aoc.kingdoms.lukasz.map.advisors.AdvisorManager;
import aoc.kingdoms.lukasz.map.civilization.CivilizationAdvisorsPool;
import aoc.kingdoms.lukasz.map.advisors.Advisor;
import java.util.Iterator;
import com.badlogic.gdx.files.FileHandle;
import aoc.kingdoms.lukasz.map.army.ArmyGeneral;
import com.badlogic.gdx.utils.JsonValue;
import java.util.ArrayList;
import com.badlogic.gdx.utils.Json;

public class CharactersManager
{
    public static final void loadGeneral(final int iCivID, final String fileName, final int nAttack, final int nDefense) {
        try {
            if (FileManager.loadFile("game/characters/" + fileName + ".json").exists()) {
                final FileHandle fileList = FileManager.loadFile("game/characters/" + fileName + ".json");
                final Json json = new Json();
                final ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
                for (final JsonValue jValue : tempArrayData) {
                    try {
                        final Characters tData = (Characters)json.readValue((Class)Characters.class, jValue);
                        if (tData != null && tData.ImageID != null && tData.ImageID.length() > 0) {
                            int bornYear = tData.BornYear;
                            if (Game_Calendar.currentYear - tData.BornYear < 10 || Game_Calendar.currentYear - tData.BornYear > 99) {
                                bornYear = Game_Calendar.currentYear - GameValues.generals.GENERAL_YEARS_OLD_MIN - Game.oR.nextInt(GameValues.generals.GENERAL_YEARS_OLD_RANDOM);
                            }
                            if (nAttack != -99) {
                                tData.Attack = nAttack;
                            }
                            if (nDefense != -99) {
                                tData.Defense = nDefense;
                            }
                            final ArmyGeneral armyGeneral = new ArmyGeneral(CFG.checkName(tData.Name), 0, (tData.Attack >= 0) ? tData.Attack : (GameValues.generals.GENERAL_ATTACK_BASE_VALUE + Game.oR.nextInt(GameValues.generals.GENERAL_ATTACK_RANDOM) + Game.oR.nextInt(GameValues.generals.GENERAL_ATTACK_RANDOM2)), (tData.Defense >= 0) ? tData.Defense : (GameValues.generals.GENERAL_DEFENSE_BASE_VALUE + Game.oR.nextInt(GameValues.generals.GENERAL_DEFENSE_RANDOM) + Game.oR.nextInt(GameValues.generals.GENERAL_DEFENSE_RANDOM2)), bornYear, iCivID, tData.ImageID);
                            armyGeneral.d = tData.BornDay;
                            armyGeneral.m = tData.BornMonth;
                            Game.getCiv(iCivID).addGeneral(armyGeneral);
                            break;
                        }
                        continue;
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
    }
    
    public static final void loadAdvisor(final int iCivID, final String fileName, final int iAdvisorType) {
        try {
            if (FileManager.loadFile("game/characters/" + fileName + ".json").exists()) {
                final FileHandle fileList = FileManager.loadFile("game/characters/" + fileName + ".json");
                final Json json = new Json();
                final ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson((Class)ArrayList.class, fileList);
                for (final JsonValue jValue : tempArrayData) {
                    try {
                        final Characters tData = (Characters)json.readValue((Class)Characters.class, jValue);
                        if (tData != null && tData.ImageID != null && tData.ImageID.length() > 0) {
                            int bornYear = tData.BornYear;
                            if (Game_Calendar.currentYear - tData.BornYear < 10 || Game_Calendar.currentYear - tData.BornYear > 99) {
                                bornYear = Game_Calendar.currentYear - GameValues.advisors.ADVISOR_YEARS_OLD_MIN - Game.oR.nextInt(Math.max(1, GameValues.advisors.ADVISOR_YEARS_OLD_RANDOM));
                            }
                            int advIMG;
                            if (iAdvisorType == 3) {
                                advIMG = Game.advisorManager.getRandomGeneralImage(iCivID);
                            }
                            else {
                                advIMG = Game.advisorManager.getRandomImage(iCivID, iAdvisorType);
                            }
                            Advisor advisor = new Advisor(CFG.checkName(tData.Name), advIMG, bornYear, tData.ImageID);
                            advisor = CivilizationAdvisorsPool.buildAdvisorBonuses(advisor, iAdvisorType);
                            advisor.iDayOfBirth = tData.BornDay;
                            advisor.iMonthOfBirth = tData.BornMonth;
                            switch (iAdvisorType) {
                                case 0: {
                                    if (Game.getCiv(iCivID).advisorAdministration.sName != null) {
                                        final AdvisorManager advisorManager = Game.advisorManager;
                                        AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorAdministration, iCivID, -1);
                                    }
                                    Game.getCiv(iCivID).advisorAdministration = advisor;
                                    final AdvisorManager advisorManager2 = Game.advisorManager;
                                    AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorAdministration, iCivID, 1);
                                    break;
                                }
                                case 1: {
                                    if (Game.getCiv(iCivID).advisorEconomy.sName != null) {
                                        final AdvisorManager advisorManager3 = Game.advisorManager;
                                        AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorEconomy, iCivID, -1);
                                    }
                                    Game.getCiv(iCivID).advisorEconomy = advisor;
                                    final AdvisorManager advisorManager4 = Game.advisorManager;
                                    AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorEconomy, iCivID, 1);
                                    break;
                                }
                                case 2: {
                                    if (Game.getCiv(iCivID).advisorTechnology.sName != null) {
                                        final AdvisorManager advisorManager5 = Game.advisorManager;
                                        AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorTechnology, iCivID, -1);
                                    }
                                    Game.getCiv(iCivID).advisorTechnology = advisor;
                                    final AdvisorManager advisorManager6 = Game.advisorManager;
                                    AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorTechnology, iCivID, 1);
                                    break;
                                }
                                default: {
                                    if (Game.getCiv(iCivID).advisorMilitary.sName != null) {
                                        final AdvisorManager advisorManager7 = Game.advisorManager;
                                        AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorMilitary, iCivID, -1);
                                    }
                                    Game.getCiv(iCivID).advisorMilitary = advisor;
                                    final AdvisorManager advisorManager8 = Game.advisorManager;
                                    AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorMilitary, iCivID, 1);
                                    break;
                                }
                            }
                            break;
                        }
                        continue;
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
    }
    
    public static class ConfigCharactersData
    {
        public String Age_of_History;
        public ArrayList Rulers;
    }
    
    public static class Characters
    {
        public String Name;
        public String ImageID;
        public int BornDay;
        public int BornMonth;
        public int BornYear;
        public int Attack;
        public int Defense;
    }
    
    public static class ScenarioCharacters
    {
        public String CivTAG;
        public String Administrative;
        public String Economic;
        public String Innovation;
        public String Military;
        public String[] Generals;
    }
}
