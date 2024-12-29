// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.Steam;

import aoc.kingdoms.lukasz.jakowski.Game;

public class SteamAchievementsManager
{
    public static String EVENT_RES;
    public static String PROMOTE_ADVISOR;
    public static String DROP_NUKE;
    public static String ALLIANCE;
    public static String GOLDEN_SCIENCE;
    public static String GOLDEN_MILITARY;
    public static String GOLDEN_PROSPERITY;
    public static String UNITE_HRE;
    public static String UNLOCK_LEGACY;
    public static String SPY;
    public static String DECLARE_WAR;
    
    public static void unlockAchievement(final String key) {
        try {
            SteamManager.userStats.setAchievement(key);
            SteamManager.userStats.storeStats();
        }
        catch (final Exception ex) {}
    }
    
    public static void unlockFormable(final String tag) {
        try {
            final String tagReal = Game.ideologiesManager.getRealTag(tag);
            if (tag.equals("adri3") || tagReal.equals("adri3")) {
                unlockAchievement("ADRIATIC_IMPERIUM");
            }
            else if (tag.equals("pol2") || tagReal.equals("pol2")) {
                unlockAchievement("COMMONWEALTH");
            }
            else if (tag.equals("afri") || tagReal.equals("afri")) {
                unlockAchievement("AFRICAN_UNION");
            }
            else if (tag.equals("alpi3") || tagReal.equals("alpi3")) {
                unlockAchievement("ALIPINE_IMPERIUM");
            }
            else if (tag.equals("anda3") || tagReal.equals("anda3")) {
                unlockAchievement("ANDALUCIA");
            }
            else if (tag.equals("arab") || tagReal.equals("arab")) {
                unlockAchievement("ARABIA");
            }
            else if (tag.equals("gatr3") || tagReal.equals("gatr3")) {
                unlockAchievement("AUSTRIAN_REICH");
            }
            else if (tag.equals("baby") || tagReal.equals("baby")) {
                unlockAchievement("BABYLON");
            }
            else if (tag.equals("gboh3") || tagReal.equals("gboh3")) {
                unlockAchievement("BOHEMIAN_EMPIRE");
            }
            else if (tag.equals("cary3") || tagReal.equals("cary3")) {
                unlockAchievement("CARPATHIAN_EMPIRE");
            }
            else if (tag.equals("carr") || tagReal.equals("carr")) {
                unlockAchievement("CARTHAGE");
            }
            else if (tag.equals("argg3") || tagReal.equals("argg3")) {
                unlockAchievement("CATALAN_IMPERIUM");
            }
            else if (tag.equals("cau3") || tagReal.equals("cau3")) {
                unlockAchievement("CAUCASIAN_EMPIRE");
            }
            else if (tag.equals("plai3") || tagReal.equals("plai3")) {
                unlockAchievement("CONFED_PLAINS");
            }
            else if (tag.equals("dalm2") || tagReal.equals("dalm2")) {
                unlockAchievement("DALMATIA");
            }
            else if (tag.equals("danu3") || tagReal.equals("danu3")) {
                unlockAchievement("DUNUBIAN");
            }
            else if (tag.equals("eagl3") || tagReal.equals("eagl3")) {
                unlockAchievement("EAGLE_UNION");
            }
            else if (tag.equals("evil3") || tagReal.equals("evil3")) {
                unlockAchievement("EVIL_SCOTLAND");
            }
            else if (tag.equals("east2") || tagReal.equals("east2")) {
                unlockAchievement("EASTERN_ROMAN");
            }
            else if (tag.equals("jauu3") || tagReal.equals("jauu3")) {
                unlockAchievement("EMPIRE_JAUNPUR");
            }
            else if (tag.equals("sarr3") || tagReal.equals("sarr3")) {
                unlockAchievement("SARDINES");
            }
            else if (tag.equals("eunn") || tagReal.equals("eunn")) {
                unlockAchievement("THE_EU");
            }
            else if (tag.equals("frc") || tagReal.equals("frc")) {
                unlockAchievement("FRANCONIA");
            }
            else if (tag.equals("gaja3") || tagReal.equals("gaja3")) {
                unlockAchievement("GAJAPATI");
            }
            else if (tag.equals("frak3") || tagReal.equals("frak3")) {
                unlockAchievement("GALLIAN");
            }
            else if (tag.equals("ger") || tagReal.equals("ger")) {
                unlockAchievement("GERMAN_EMPIRE");
            }
            else if (tag.equals("est") || tagReal.equals("est")) {
                unlockAchievement("ESTONIA");
            }
            else if (tag.equals("ghav") || tagReal.equals("ghav")) {
                unlockAchievement("GHAZNA");
            }
            else if (tag.equals("durr") || tagReal.equals("durr")) {
                unlockAchievement("DURRANI");
            }
            else if (tag.equals("cent") || tagReal.equals("cent")) {
                unlockAchievement("CENTRAL_AMERICA");
            }
            else if (tag.equals("nirr3") || tagReal.equals("nirr3")) {
                unlockAchievement("NIGER_EMPIRE");
            }
            else if (tag.equals("grc") || tagReal.equals("grc")) {
                unlockAchievement("GRAN_COLOMBIA");
            }
            else if (tag.equals("grea3") || tagReal.equals("grea3")) {
                unlockAchievement("GREATER_ASIA_CO");
            }
            else if (tag.equals("hans3") || tagReal.equals("hans3")) {
                unlockAchievement("HANSEATIC");
            }
            else if (tag.equals("iber") || tagReal.equals("iber")) {
                unlockAchievement("IBERIAN_UNION");
            }
            else if (tag.equals("lech3") || tagReal.equals("lech3")) {
                unlockAchievement("LICHITOW");
            }
            else if (tag.equals("inca") || tagReal.equals("inca")) {
                unlockAchievement("INCA_EMPIRE");
            }
            else if (tag.equals("indo3") || tagReal.equals("indo3")) {
                unlockAchievement("INDOCHINA_EMPIRE");
            }
            else if (tag.equals("ita") || tagReal.equals("ita")) {
                unlockAchievement("ITALY");
            }
            else if (tag.equals("jap") || tagReal.equals("jap")) {
                unlockAchievement("JAPAN");
            }
            else if (tag.equals("jur") || tagReal.equals("jur")) {
                unlockAchievement("JERUSALEM");
            }
            else if (tag.equals("keis3") || tagReal.equals("keis3")) {
                unlockAchievement("KAISERREICH");
            }
            else if (tag.equals("kek") || tagReal.equals("kek")) {
                unlockAchievement("KEKISTAN");
            }
            else if (tag.equals("gmag3") || tagReal.equals("gmag3")) {
                unlockAchievement("MAGHREB");
            }
            else if (tag.equals("int") || tagReal.equals("int")) {
                unlockAchievement("INTERMARIUM");
            }
            else if (tag.equals("ire") || tagReal.equals("ire")) {
                unlockAchievement("K_IRELAND");
            }
            else if (tag.equals("glat3") || tagReal.equals("glat3")) {
                unlockAchievement("LATIN_AMERICAN_UNION");
            }
            else if (tag.equals("rivi3") || tagReal.equals("rivi3")) {
                unlockAchievement("K_RIVIERA");
            }
            else if (tag.equals("sok") || tagReal.equals("sok")) {
                unlockAchievement("KOREAN_EMPIRE");
            }
            else if (tag.equals("lati") || tagReal.equals("lati")) {
                unlockAchievement("LATIN_EMPIRE");
            }
            else if (tag.equals("leva3") || tagReal.equals("leva3")) {
                unlockAchievement("LEVANT_EMPIRE");
            }
            else if (tag.equals("glux3") || tagReal.equals("glux3")) {
                unlockAchievement("LUXEMBOURG_EMPIRE");
            }
            else if (tag.equals("lot") || tagReal.equals("lot")) {
                unlockAchievement("LOTHARINGIA");
            }
            else if (tag.equals("mad") || tagReal.equals("mad")) {
                unlockAchievement("MADAGASCAR");
            }
            else if (tag.equals("magy3") || tagReal.equals("magy3")) {
                unlockAchievement("MAGYAR_EMPIRE");
            }
            else if (tag.equals("gmaj3") || tagReal.equals("gmaj3")) {
                unlockAchievement("MAJAPAHIT_E");
            }
            else if (tag.equals("mara") || tagReal.equals("mara")) {
                unlockAchievement("MARATHA_E");
            }
            else if (tag.equals("meko3") || tagReal.equals("meko3")) {
                unlockAchievement("MEKONG_EMPIRE");
            }
            else if (tag.equals("mex2") || tagReal.equals("mex2")) {
                unlockAchievement("MEXICAN_EMPIRE");
            }
            else if (tag.equals("mone3") || tagReal.equals("mone3")) {
                unlockAchievement("MONGOL_EMPIRE");
            }
            else if (tag.equals("nand3") || tagReal.equals("nand3")) {
                unlockAchievement("NANDA_EMPIRE");
            }
            else if (tag.equals("nors") || tagReal.equals("nors")) {
                unlockAchievement("NORTH_SEA_EMPIRE");
            }
            else if (tag.equals("pizz3") || tagReal.equals("pizz3")) {
                unlockAchievement("PIZZA_EMPIRE");
            }
            else if (tag.equals("occa") || tagReal.equals("occa")) {
                unlockAchievement("OCCITANIA");
            }
            else if (tag.equals("mya") || tagReal.equals("mya")) {
                unlockAchievement("PAGAN_EMPIRE");
            }
            else if (tag.equals("ira") || tagReal.equals("ira")) {
                unlockAchievement("PERSIAN_EMPIRE");
            }
            else if (tag.equals("red3") || tagReal.equals("red3")) {
                unlockAchievement("RED_SEA_EMPIRE");
            }
            else if (tag.equals("ratt") || tagReal.equals("ratt")) {
                unlockAchievement("RATTANAKOSIN");
            }
            else if (tag.equals("rash") || tagReal.equals("rash")) {
                unlockAchievement("RASHIDUN");
            }
            else if (tag.equals("mal") || tagReal.equals("mal")) {
                unlockAchievement("MALAYSIA");
            }
            else if (tag.equals("spqr") || tagReal.equals("spqr")) {
                unlockAchievement("ROME_E");
            }
            else if (tag.equals("cono3") || tagReal.equals("cono3")) {
                unlockAchievement("R_CONOSUR");
            }
            else if (tag.equals("rom") || tagReal.equals("rom")) {
                unlockAchievement("K_ROMANIA");
            }
            else if (tag.equals("rus2") || tagReal.equals("rus2")) {
                unlockAchievement("RUSSIAN_EMPIRE");
            }
            else if (tag.equals("gsca3") || tagReal.equals("gsca3")) {
                unlockAchievement("SCANDINAVIA");
            }
            else if (tag.equals("swab") || tagReal.equals("swab")) {
                unlockAchievement("SWABIA");
            }
            else if (tag.equals("swi") || tagReal.equals("swi")) {
                unlockAchievement("SWITZERLAND");
            }
            else if (tag.equals("uni") || tagReal.equals("uni")) {
                unlockAchievement("UNITED_KINGDOM");
            }
            else if (tag.equals("gbul3") || tagReal.equals("gbul3")) {
                unlockAchievement("TSAR_BULGARIA");
            }
            else if (tag.equals("uso") || tagReal.equals("uso")) {
                unlockAchievement("US_OCEANIA");
            }
            else if (tag.equals("somi3") || tagReal.equals("somi3")) {
                unlockAchievement("SOMALI_EMPIRE");
            }
            else if (tag.equals("gsg3") || tagReal.equals("gsg3")) {
                unlockAchievement("S_GERMAN_CONF");
            }
        }
        catch (final Exception ex) {}
    }
    
    static {
        SteamAchievementsManager.EVENT_RES = "EVENT_RES";
        SteamAchievementsManager.PROMOTE_ADVISOR = "PROMOTE_ADVISOR";
        SteamAchievementsManager.DROP_NUKE = "DROP_NUKE";
        SteamAchievementsManager.ALLIANCE = "ALLIANCE";
        SteamAchievementsManager.GOLDEN_SCIENCE = "GOLDEN_SCIENCE";
        SteamAchievementsManager.GOLDEN_MILITARY = "GOLDEN_MILITARY";
        SteamAchievementsManager.GOLDEN_PROSPERITY = "GOLDEN_PROSPERITY";
        SteamAchievementsManager.UNITE_HRE = "UNITE_HRE";
        SteamAchievementsManager.UNLOCK_LEGACY = "UNLOCK_LEGACY";
        SteamAchievementsManager.SPY = "SPY";
        SteamAchievementsManager.DECLARE_WAR = "DECLARE_WAR";
    }
}
