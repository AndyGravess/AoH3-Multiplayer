// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski;

import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.menusInGame.Info.InGame_Info;
import aoc.kingdoms.lukasz.map.map.MapScenarios;
import aoc.kingdoms.lukasz.menu_element.button.ButtonTopDate;
import java.util.List;
import java.util.ArrayList;

public class Game_Calendar
{
    public static int TURN_ID;
    public static int HOUR;
    public static int IMG_MANPOWER;
    public static int IMG_MANPOWER_SPLIT;
    public static int IMG_MANPOWER_DISBAND;
    public static int IMG_MANPOWER_REORGANIZE;
    public static int IMG_MANPOWER_MERGE;
    public static int IMG_MANPOWER_UP;
    public static int IMG_MANPOWER_TIME;
    public static int IMG_TECHNOLOGY;
    public static int IMG_ECONOMY;
    public static int IMG_ECONOMY_UP;
    public static int IMG_ECONOMY_DOWN;
    public static int IMG_FORT_DEFENSE;
    public static int CURRENT_AGEID;
    public static int currentDay;
    public static int currentMonth;
    public static int currentYear;
    public static float GAME_SPEED;
    public static float GAME_SPEED_MIN;
    public static float GAME_SPEED_MAX;
    public static float UPDATE_NUM_OF_DAYS;
    public static int UPDATE_NUM_OF_DAYS_INT;
    public static boolean ENABLE_COLONIZATION_OF_WASTELAND;
    private static final int[] NUM_OF_DAYS_IN_MONTH;
    public static final int NUM_OF_DAYS = 365;
    public static final int NUM_OF_MONTHS = 12;
    
    public static final int getNumOfDaysInMonth(final int nMonth) {
        try {
            return Game_Calendar.NUM_OF_DAYS_IN_MONTH[nMonth - 1];
        }
        catch (final Exception ex) {
            return 28;
        }
    }
    
    public static final String getCurrentDate() {
        return ((Game.HOURS_PER_TURN < 24) ? (((Game_Calendar.HOUR < 10) ? "0" : "") + Game_Calendar.HOUR + ":00, ") : "") + "" + Game_Calendar.currentDay + " " + getMonthName(Game_Calendar.currentMonth) + " " + Game.gameAges.getYear(Game_Calendar.currentYear);
    }
    
    public static final String getCurrentDate_Simple() {
        return ((Game.HOURS_PER_TURN < 24) ? (((Game_Calendar.HOUR < 10) ? "0" : "") + Game_Calendar.HOUR + ":00, ") : "") + "" + Game_Calendar.currentDay + " " + Game_Calendar.currentMonth + " " + Game.gameAges.getYear(Game_Calendar.currentYear);
    }
    
    public static final String getMonthName(final int nMonth) {
        switch (nMonth) {
            case 1: {
                return Game.lang.get("January");
            }
            case 2: {
                return Game.lang.get("February");
            }
            case 3: {
                return Game.lang.get("March");
            }
            case 4: {
                return Game.lang.get("April");
            }
            case 5: {
                return Game.lang.get("May");
            }
            case 6: {
                return Game.lang.get("June");
            }
            case 7: {
                return Game.lang.get("July");
            }
            case 8: {
                return Game.lang.get("August");
            }
            case 9: {
                return Game.lang.get("September");
            }
            case 10: {
                return Game.lang.get("October");
            }
            case 11: {
                return Game.lang.get("November");
            }
            case 12: {
                return Game.lang.get("December");
            }
            case 13: {
                return Game.lang.get("January");
            }
            default: {
                return Game.lang.get("December");
            }
        }
    }
    
    public static void updateDateNextTurn() {
        nextDays(Game.gameAges.getAge_TurnDays(Game_Calendar.CURRENT_AGEID));
    }
    
    public static final String getNumOfDates_ByTurnID(final int nTurnID) {
        if (nTurnID == Game_Calendar.TURN_ID) {
            return Game.lang.get("DaysX", 0);
        }
        if (nTurnID > Game_Calendar.TURN_ID) {
            List<Integer> tempDate = new ArrayList<Integer>();
            tempDate.add(Game_Calendar.currentDay);
            tempDate.add(Game_Calendar.currentMonth);
            tempDate.add(Game_Calendar.currentYear);
            tempDate.add(Game_Calendar.CURRENT_AGEID);
            tempDate = forwardDays(tempDate, nTurnID - Game_Calendar.TURN_ID);
            return "" + tempDate.get(0) + " " + getMonthName(tempDate.get(1)) + " " + Game.gameAges.getYear(tempDate.get(2));
        }
        List<Integer> tempDate = new ArrayList<Integer>();
        tempDate.add(Game_Calendar.currentDay);
        tempDate.add(Game_Calendar.currentMonth);
        tempDate.add(Game_Calendar.currentYear);
        tempDate.add(Game_Calendar.CURRENT_AGEID);
        tempDate = backwardsDays(tempDate, Game_Calendar.TURN_ID - nTurnID);
        final List<Integer> tempDateOut = getNumOfDates_ByTurnID(tempDate);
        return "" + ((tempDateOut.get(2) > 0) ? (Game.lang.get("YearsX", tempDateOut.get(2)) + ((tempDateOut.get(1) > 0 || tempDateOut.get(0) > 0) ? " " : "")) : "") + ((tempDateOut.get(1) > 0) ? (Game.lang.get("MonthsX", tempDateOut.get(1)) + ((tempDateOut.get(0) > 0) ? " " : "")) : "") + ((tempDateOut.get(0) > 0) ? Game.lang.get("DaysX", tempDateOut.get(0)) : "");
    }
    
    public static final String getNumOfDays_ByTurnsPlayed(final int nTurnID) {
        List<Integer> tempDate = new ArrayList<Integer>();
        tempDate.add(Game_Calendar.currentDay);
        tempDate.add(Game_Calendar.currentMonth);
        tempDate.add(Game_Calendar.currentYear);
        tempDate.add(Game_Calendar.CURRENT_AGEID);
        tempDate = backwardsDays(tempDate, nTurnID);
        final List<Integer> tempDateOut = getNumOfDates_ByTurnID(tempDate);
        return "" + ((tempDateOut.get(2) > 0) ? (Game.lang.get("YearsX", tempDateOut.get(2)) + ((tempDateOut.get(1) > 0 || tempDateOut.get(0) > 0) ? " " : "")) : "") + ((tempDateOut.get(1) > 0) ? (Game.lang.get("MonthsX", tempDateOut.get(1)) + ((tempDateOut.get(0) > 0) ? " " : "")) : "") + ((tempDateOut.get(0) > 0) ? Game.lang.get("DaysX", tempDateOut.get(0)) : "");
    }
    
    public static final String getNumOfDays_ByTurnsPlayed_WithoutDays(final int nTurnID) {
        List<Integer> tempDate = new ArrayList<Integer>();
        tempDate.add(Game_Calendar.currentDay);
        tempDate.add(Game_Calendar.currentMonth);
        tempDate.add(Game_Calendar.currentYear);
        tempDate.add(Game_Calendar.CURRENT_AGEID);
        tempDate = backwardsDays(tempDate, nTurnID);
        final List<Integer> tempDateOut = getNumOfDates_ByTurnID(tempDate);
        return "" + ((tempDateOut.get(2) > 0) ? (Game.lang.get("YearsX", tempDateOut.get(2)) + ((tempDateOut.get(1) > 0 || tempDateOut.get(0) > 0) ? " " : "")) : "") + ((tempDateOut.get(1) > 0) ? (Game.lang.get("MonthsX", tempDateOut.get(1)) + ((tempDateOut.get(0) > 0) ? " " : "")) : "");
    }
    
    private static List<Integer> getNumOfDates_ByTurnID(final List<Integer> tempDate) {
        final ArrayList<Integer> out = new ArrayList<Integer>();
        out.add(0);
        out.add(0);
        out.add(0);
        out.set(2, Math.abs(Game_Calendar.currentYear - tempDate.get(2)));
        tempDate.set(2, Game_Calendar.currentYear);
        if (tempDate.get(1) == Game_Calendar.currentMonth) {
            if (tempDate.get(0) > Game_Calendar.currentDay) {
                out.set(1, out.get(1) - 1);
                out.set(0, Game_Calendar.currentDay + (Game_Calendar.NUM_OF_DAYS_IN_MONTH[tempDate.get(1) - 1] - tempDate.get(0)));
                if (out.get(1) < 0) {
                    out.set(1, 11);
                    out.set(2, out.get(2) - 1);
                    if (out.get(2) < 0) {
                        out.set(2, 0);
                    }
                }
            }
            else {
                out.set(0, Game_Calendar.currentDay - tempDate.get(0));
            }
        }
        else if (tempDate.get(1) < Game_Calendar.currentMonth) {
            out.set(1, Game_Calendar.currentMonth - tempDate.get(1));
            if (tempDate.get(0) > Game_Calendar.currentDay) {
                out.set(1, out.get(1) - 1);
                out.set(0, Game_Calendar.currentDay + (Game_Calendar.NUM_OF_DAYS_IN_MONTH[tempDate.get(1) - 1] - tempDate.get(0)));
            }
            else {
                out.set(0, Game_Calendar.currentDay - tempDate.get(0));
            }
        }
        else {
            if (out.get(2) > 0) {
                out.set(2, out.get(2) - 1);
            }
            out.set(1, Game_Calendar.currentMonth + (12 - tempDate.get(1)));
            if (tempDate.get(0) > Game_Calendar.currentDay) {
                out.set(1, out.get(1) - 1);
                out.set(0, Game_Calendar.currentDay + (Game_Calendar.NUM_OF_DAYS_IN_MONTH[tempDate.get(1) - 1] - tempDate.get(0)));
            }
            else {
                out.set(0, Game_Calendar.currentDay - tempDate.get(0));
            }
        }
        return out;
    }
    
    public static final String getDate_ByTurnID(final int nTurnID) {
        if (nTurnID == Game_Calendar.TURN_ID) {
            return getCurrentDate();
        }
        if (nTurnID > Game_Calendar.TURN_ID) {
            List<Integer> tempDate = new ArrayList<Integer>();
            tempDate.add(Game_Calendar.currentDay);
            tempDate.add(Game_Calendar.currentMonth);
            tempDate.add(Game_Calendar.currentYear);
            tempDate.add(Game_Calendar.CURRENT_AGEID);
            tempDate = forwardDays(tempDate, nTurnID - Game_Calendar.TURN_ID);
            return "" + tempDate.get(0) + " " + getMonthName(tempDate.get(1)) + " " + Game.gameAges.getYear(tempDate.get(2));
        }
        List<Integer> tempDate = new ArrayList<Integer>();
        tempDate.add(Game_Calendar.currentDay);
        tempDate.add(Game_Calendar.currentMonth);
        tempDate.add(Game_Calendar.currentYear);
        tempDate.add(Game_Calendar.CURRENT_AGEID);
        tempDate = backwardsDays(tempDate, Game_Calendar.TURN_ID - nTurnID);
        return "" + tempDate.get(0) + " " + getMonthName(tempDate.get(1)) + " " + Game.gameAges.getYear(tempDate.get(2));
    }
    
    public static final String getDate_ByTurnID_MessageShort(final int nTurnID) {
        if (nTurnID == Game_Calendar.TURN_ID) {
            return "" + Game_Calendar.currentDay + " " + getMonthName(Game_Calendar.currentMonth).substring(0, Math.min(getMonthName(Game_Calendar.currentMonth).length(), 3));
        }
        if (nTurnID > Game_Calendar.TURN_ID) {
            List<Integer> tempDate = new ArrayList<Integer>();
            tempDate.add(Game_Calendar.currentDay);
            tempDate.add(Game_Calendar.currentMonth);
            tempDate.add(Game_Calendar.currentYear);
            tempDate.add(Game_Calendar.CURRENT_AGEID);
            tempDate = forwardDays(tempDate, nTurnID - Game_Calendar.TURN_ID);
            return "" + tempDate.get(0) + " " + getMonthName(tempDate.get(1)).substring(0, Math.min(getMonthName(tempDate.get(1)).length(), 3));
        }
        List<Integer> tempDate = new ArrayList<Integer>();
        tempDate.add(Game_Calendar.currentDay);
        tempDate.add(Game_Calendar.currentMonth);
        tempDate.add(Game_Calendar.currentYear);
        tempDate.add(Game_Calendar.CURRENT_AGEID);
        tempDate = backwardsDays(tempDate, Game_Calendar.TURN_ID - nTurnID);
        return "" + tempDate.get(0) + " " + getMonthName(tempDate.get(1)).substring(0, Math.min(getMonthName(tempDate.get(1)).length(), 3));
    }
    
    private static List<Integer> forwardDays(final List<Integer> tempDate, final int nTurns) {
        try {
            for (int i = 0; i < nTurns; ++i) {
                tempDate.set(0, tempDate.get(0) + Game.gameAges.getAge_TurnDays(tempDate.get(3)));
                while (tempDate.get(0) > Game_Calendar.NUM_OF_DAYS_IN_MONTH[tempDate.get(1) - 1]) {
                    tempDate.set(0, tempDate.get(0) - Game_Calendar.NUM_OF_DAYS_IN_MONTH[tempDate.get(1) - 1]);
                    tempDate.set(1, tempDate.get(1) + 1);
                    if (tempDate.get(1) <= 12) {
                        continue;
                    }
                    tempDate.set(1, 1);
                    tempDate.set(2, tempDate.get(2) + 1);
                    tempDate.set(3, Game.gameAges.getAgeOfYear(tempDate.get(2)));
                }
            }
        }
        catch (final Exception ex) {
            tempDate.set(1, 1);
        }
        return tempDate;
    }
    
    private static List<Integer> backwardsDays(final List<Integer> tempDate, final int nTurns) {
        try {
            for (int i = 0; i < nTurns; ++i) {
                for (int nMinDays = Game.gameAges.getAge_TurnDays(tempDate.get(3)); nMinDays > 0; nMinDays -= tempDate.get(0)) {
                    if (nMinDays < tempDate.get(0)) {
                        tempDate.set(0, tempDate.get(0) - nMinDays);
                        break;
                    }
                    tempDate.set(1, tempDate.get(1) - 1);
                    if (tempDate.get(1) < 1) {
                        tempDate.set(1, 12);
                        tempDate.set(2, tempDate.get(2) - 1);
                        tempDate.set(3, Game.gameAges.getAgeOfYear(tempDate.get(2)));
                    }
                    tempDate.set(0, Game_Calendar.NUM_OF_DAYS_IN_MONTH[tempDate.get(1) - 1]);
                }
            }
        }
        catch (final Exception ex) {
            tempDate.set(1, 1);
        }
        return tempDate;
    }
    
    public static void nextDays(final int numOfDays) {
        try {
            Game_Calendar.currentDay += numOfDays;
            while (Game_Calendar.currentDay > Game_Calendar.NUM_OF_DAYS_IN_MONTH[Game_Calendar.currentMonth - 1]) {
                Game_Calendar.currentDay -= Game_Calendar.NUM_OF_DAYS_IN_MONTH[Game_Calendar.currentMonth - 1];
                if (++Game_Calendar.currentMonth <= 12) {
                    continue;
                }
                Game_Calendar.currentMonth = 1;
                ++Game_Calendar.currentYear;
                updateAge();
            }
        }
        catch (final Exception ex) {
            Game_Calendar.currentMonth = 1;
        }
    }
    
    public static void minusMonth() {
        try {
            if (--Game_Calendar.currentMonth < 1) {
                Game_Calendar.currentMonth = 12;
            }
            if (Game_Calendar.currentDay > Game_Calendar.NUM_OF_DAYS_IN_MONTH[Game_Calendar.currentMonth - 1]) {
                Game_Calendar.currentDay = Game_Calendar.NUM_OF_DAYS_IN_MONTH[Game_Calendar.currentMonth - 1];
            }
        }
        catch (final Exception ex) {
            Game_Calendar.currentMonth = 1;
        }
    }
    
    public static void plusMonth() {
        try {
            if (++Game_Calendar.currentMonth > 12) {
                Game_Calendar.currentMonth = 1;
            }
            if (Game_Calendar.currentDay > Game_Calendar.NUM_OF_DAYS_IN_MONTH[Game_Calendar.currentMonth - 1]) {
                Game_Calendar.currentDay = Game_Calendar.NUM_OF_DAYS_IN_MONTH[Game_Calendar.currentMonth - 1];
            }
        }
        catch (final Exception ex) {
            Game_Calendar.currentMonth = 1;
        }
    }
    
    public static void addYears(final int numOfYears) {
        Game_Calendar.currentYear += numOfYears;
        updateAge();
    }
    
    public static void updateAge() {
        updateAge(true);
    }
    
    public static void updateAge(final boolean sendMessages) {
        final int nAgeID = Game.gameAges.getAgeOfYear(Game_Calendar.currentYear);
        final boolean updateAge = Game_Calendar.CURRENT_AGEID != nAgeID;
        Game_Calendar.CURRENT_AGEID = nAgeID;
        if (updateAge && Game.menuManager.getInGame()) {
            Game_Calendar.HOUR = 0;
            ButtonTopDate.updateMaxWidth();
            MapScenarios.updateUQ_UI();
            if (sendMessages) {
                InGame_Info.iCivID = Game.player.iCivID;
                InGame_Info.iCivID2 = Game.player.iCivID;
                Game.menuManager.rebuildInGame_Info(Game.lang.get("WelcomeInTheNewAge"), Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).Name);
                InGame_Info.imgID = Images.infoCrown;
            }
            Game.menuManager.updateInGameFlag();
        }
        updateManpowerImg();
    }
    
    public static final void updateManpowerImg() {
        if (Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).OVER_IMAGE_ID == 0) {
            Game_Calendar.IMG_MANPOWER = Images.manpower;
            Game_Calendar.IMG_MANPOWER_SPLIT = Images.splitArmy;
            Game_Calendar.IMG_MANPOWER_MERGE = Images.mergeArmy;
            Game_Calendar.IMG_MANPOWER_REORGANIZE = Images.reorganizeArmy;
            Game_Calendar.IMG_MANPOWER_DISBAND = Images.disbandArmy;
            Game_Calendar.IMG_MANPOWER_UP = Images.manpowerUp;
            Game_Calendar.IMG_MANPOWER_TIME = Images.manpowerTime;
            Game_Calendar.IMG_TECHNOLOGY = Images.technology;
            Game_Calendar.IMG_ECONOMY = Images.economy;
            Game_Calendar.IMG_ECONOMY_UP = Images.economyUp;
            Game_Calendar.IMG_ECONOMY_DOWN = Images.economyDown;
            Game_Calendar.IMG_FORT_DEFENSE = Images.fortDefense;
        }
        else if (Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).OVER_IMAGE_ID == 1) {
            Game_Calendar.IMG_MANPOWER = Images.manpower;
            Game_Calendar.IMG_MANPOWER_SPLIT = Images.splitArmy;
            Game_Calendar.IMG_MANPOWER_MERGE = Images.mergeArmy;
            Game_Calendar.IMG_MANPOWER_REORGANIZE = Images.reorganizeArmy;
            Game_Calendar.IMG_MANPOWER_DISBAND = Images.disbandArmy;
            Game_Calendar.IMG_MANPOWER_UP = Images.manpowerUp;
            Game_Calendar.IMG_MANPOWER_TIME = Images.manpowerTime;
            Game_Calendar.IMG_TECHNOLOGY = Images.technology;
            Game_Calendar.IMG_ECONOMY = Images.economy;
            Game_Calendar.IMG_ECONOMY_UP = Images.economyUp;
            Game_Calendar.IMG_ECONOMY_DOWN = Images.economyDown;
            Game_Calendar.IMG_FORT_DEFENSE = Images.fortDefense;
        }
        else if (Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).OVER_IMAGE_ID == 2) {
            Game_Calendar.IMG_MANPOWER = Images.manpower2;
            Game_Calendar.IMG_MANPOWER_SPLIT = Images.splitArmy2;
            Game_Calendar.IMG_MANPOWER_MERGE = Images.mergeArmy2;
            Game_Calendar.IMG_MANPOWER_REORGANIZE = Images.reorganizeArmy2;
            Game_Calendar.IMG_MANPOWER_DISBAND = Images.disbandArmy2;
            Game_Calendar.IMG_MANPOWER_UP = Images.manpower2Up;
            Game_Calendar.IMG_MANPOWER_TIME = Images.manpower2Time;
            Game_Calendar.IMG_TECHNOLOGY = Images.technology;
            Game_Calendar.IMG_ECONOMY = Images.economy;
            Game_Calendar.IMG_ECONOMY_UP = Images.economyUp;
            Game_Calendar.IMG_ECONOMY_DOWN = Images.economyDown;
            Game_Calendar.IMG_FORT_DEFENSE = Images.fortDefense_2;
        }
        else {
            Game_Calendar.IMG_MANPOWER = Images.manpower3;
            Game_Calendar.IMG_MANPOWER_SPLIT = Images.splitArmy3;
            Game_Calendar.IMG_MANPOWER_MERGE = Images.mergeArmy3;
            Game_Calendar.IMG_MANPOWER_REORGANIZE = Images.reorganizeArmy3;
            Game_Calendar.IMG_MANPOWER_DISBAND = Images.disbandArmy3;
            Game_Calendar.IMG_MANPOWER_UP = Images.manpower3Up;
            Game_Calendar.IMG_MANPOWER_TIME = Images.manpower3Time;
            Game_Calendar.IMG_TECHNOLOGY = Images.technology2;
            Game_Calendar.IMG_ECONOMY = Images.economy2;
            Game_Calendar.IMG_ECONOMY_UP = Images.economyUp2;
            Game_Calendar.IMG_ECONOMY_DOWN = Images.economyDown2;
            Game_Calendar.IMG_FORT_DEFENSE = Images.fortDefense_2;
        }
    }
    
    public static int getYear() {
        return Game_Calendar.currentYear;
    }
    
    public static int getDay() {
        return Game_Calendar.currentDay;
    }
    
    public static int getMonth() {
        return Game_Calendar.currentMonth;
    }
    
    static {
        Game_Calendar.TURN_ID = 1;
        Game_Calendar.HOUR = 0;
        Game_Calendar.IMG_MANPOWER = 0;
        Game_Calendar.IMG_MANPOWER_SPLIT = 0;
        Game_Calendar.IMG_MANPOWER_DISBAND = 0;
        Game_Calendar.IMG_MANPOWER_REORGANIZE = 0;
        Game_Calendar.IMG_MANPOWER_MERGE = 0;
        Game_Calendar.IMG_MANPOWER_UP = 0;
        Game_Calendar.IMG_MANPOWER_TIME = 0;
        Game_Calendar.IMG_TECHNOLOGY = 0;
        Game_Calendar.IMG_ECONOMY = 0;
        Game_Calendar.IMG_ECONOMY_UP = 0;
        Game_Calendar.IMG_ECONOMY_DOWN = 0;
        Game_Calendar.IMG_FORT_DEFENSE = 0;
        Game_Calendar.CURRENT_AGEID = 0;
        Game_Calendar.currentDay = 1;
        Game_Calendar.currentMonth = 1;
        Game_Calendar.currentYear = 2014;
        Game_Calendar.GAME_SPEED = 1.0f;
        Game_Calendar.GAME_SPEED_MIN = 0.5f;
        Game_Calendar.GAME_SPEED_MAX = 2.0f;
        Game_Calendar.UPDATE_NUM_OF_DAYS = 30.0f;
        Game_Calendar.UPDATE_NUM_OF_DAYS_INT = 30;
        Game_Calendar.ENABLE_COLONIZATION_OF_WASTELAND = true;
        NUM_OF_DAYS_IN_MONTH = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    }
}
