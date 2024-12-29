// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.desktop;

import aoc.kingdoms.lukasz.menu_element.Toast;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.map.war.WarManager;
import aoc.kingdoms.lukasz.map.war.War;
import aoc.kingdoms.lukasz.map.army.ArmyDivision;
import aoc.kingdoms.lukasz.jakowski.AI.Army.AI_RecruitArmy;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Function;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.menusInGame.InGame_Console;
import aoc.kingdoms.lukasz.menus.MainMenu_Multiplayer;
import aoc.kingdoms.lukasz.jakowski.GameThreads.GameThread;
import aoc.kingdoms.lukasz.jakowski.Game;
import java.io.InputStreamReader;
import com.badlogic.gdx.ApplicationListener;
import aoc.kingdoms.lukasz.jakowski.AA_Game;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import aoc.kingdoms.lukasz.map.army.ArmyRegiment;
import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;
import java.net.Socket;

public class DesktopLauncher
{
    private static Socket socket;
    public static PrintWriter out;
    public static boolean host;
    public static List<Integer> player;
    public static String Name;
    public static String SS;
    public static String Server2;
    public static int civId;
    public static int uID;
    public static int aID;
    public static int num;
    public static float mo;
    public static int numARMY;
    public static String keyARMY;
    public static ArrayList<ArmyRegiment> a31;
    
    public static void main(final String[] arg) {
        final Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Age of History 3");
        config.setWindowIcon(Files.FileType.Internal, new String[] { "gfx/icon/icon_16x16.png" });
        config.setWindowIcon(Files.FileType.Internal, new String[] { "gfx/icon/icon_32x32.png" });
        config.setWindowIcon(Files.FileType.Internal, new String[] { "gfx/icon/icon_128x128.png" });
        config.setResizable(true);
        int tWidth = -1;
        int tHeight = -1;
        boolean fullScreenMode = false;
        boolean tVSync = true;
        try {
            FileReader fr = new FileReader("settings/Config.txt");
            BufferedReader bfr = new BufferedReader(fr);
            String sLine = "";
            while ((sLine = bfr.readLine()) != null) {
                final String[] tempR = sLine.replace(";", "").split("=");
                try {
                    if (tempR[0].equals("FULLSCREEN")) {
                        fullScreenMode = Boolean.parseBoolean(tempR[1]);
                    }
                    else if (tempR[0].equals("WIDTH")) {
                        tWidth = Integer.parseInt(tempR[1]);
                    }
                    else if (tempR[0].equals("HEIGHT")) {
                        tHeight = Integer.parseInt(tempR[1]);
                    }
                    else {
                        if (tempR[0].equals("ANTIALIASING") || !tempR[0].equals("VSYNC")) {
                            continue;
                        }
                        tVSync = Boolean.parseBoolean(tempR[1]);
                    }
                }
                catch (final Exception var12) {
                    tWidth = -1;
                    tHeight = -1;
                    fullScreenMode = true;
                    tVSync = true;
                    break;
                }
            }
            fr.close();
            fr = null;
            bfr = null;
        }
        catch (final IOException ex) {}
        if (tWidth < 0 && tHeight < 0) {
            config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
        }
        else {
            config.setWindowedMode(tWidth, tHeight);
            if (fullScreenMode) {
                config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
            }
        }
        config.useVsync(tVSync);
        config.setAudioConfig(32, 512, 18);
        new Lwjgl3Application((ApplicationListener)new AA_Game(), config);
    }
    
    public static void setupServerConnection(final String address, final int port) {
        try {
            DesktopLauncher.socket = new Socket(address, port);
            DesktopLauncher.out = new PrintWriter(DesktopLauncher.socket.getOutputStream(), true);
            System.out.println("Sunucuya ba\u011fland\u0131: " + address + ":" + port);
        }
        catch (final IOException var3) {
            final IOException e = var3;
            System.err.println("Ba\u011flant\u0131 hatas\u0131: " + e.getMessage());
        }
    }
    
    public static void SMS(final String message) {
        if (DesktopLauncher.out != null) {
            final String formattedMessage = message;
            DesktopLauncher.out.println(formattedMessage);
            System.out.println("G\u00f6nderilen mesaj: " + formattedMessage);
        }
        else {
            System.err.println("Mesaj g\u00f6nderilemedi, sunucu ba\u011flant\u0131s\u0131 yok.");
        }
    }
    
    public static void listenForMessages() {
        try (final BufferedReader in = new BufferedReader(new InputStreamReader(DesktopLauncher.socket.getInputStream()))) {
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Gelen mesaj: " + message);
                handleIncomingMessage(message);
            }
        }
        catch (final IOException var5) {
            DesktopLauncher.out = null;
            disconnectTimeout();
        }
    }
    
    public static void handleIncomingMessage(final String message) {
        int provinceId = 0;
        if (message.contains("JOINOK:")) {
            final String rrData = message.substring(message.indexOf("JOINOK:") + 7).trim();
            final String[] rrParts = rrData.split(" ");
            if (rrParts.length >= 3) {
                try {
                    final String Server = rrParts[0];
                    final String UID = rrParts[1];
                    if (Server == DesktopLauncher.Server2) {
                        displayJoinMessage(UID);
                    }
                }
                catch (final NumberFormatException e) {
                    System.out.println("RR format\u0131 yanl\u0131\u015f: Say\u0131 format\u0131nda hata.");
                }
            }
            else {
                System.out.println("RR format\u0131 yanl\u0131\u015f.");
            }
        }
        else if (message.contains("YOU:")) {
            if (message.contains("YOU: admin")) {
                DesktopLauncher.host = true;
            }
            else if (message.contains("YOU: player")) {
                DesktopLauncher.host = false;
            }
        }
        if (message.contains("UNIT!")) {
            Game.gameThread.updateMoveUnits_Client();
            final GameThread gameThread = Game.gameThread;
            GameThread.updateArmyMorale_Reinforce();
            Game.gameThread.updateSieges();
            Game.gameThread.updateBattles();
            Game.gameThread.checkGameOver();
            Game.gameThread.updateRecruitArmy_Client();
        }
        if (message.contains("DATE!")) {
            Game.gameThread.updateNewMonth();
            Game.gameThread.updateNewYear();
        }
        if (message.contains("RR:")) {
            final String rrData = message.substring(message.indexOf("RR:") + 4).trim();
            final String[] rrParts = rrData.split(" ");
            if (rrParts.length >= 3) {
                try {
                    final String roomName = rrParts[0];
                    final int type = Integer.parseInt(rrParts[1]);
                    final int players = Integer.parseInt(rrParts[2]);
                    final String roomInfo = roomName + "," + type + "," + players;
                    MainMenu_Multiplayer.roomList.add(roomInfo);
                    System.out.println("Oda bilgisi listeye eklendi: " + roomInfo);
                }
                catch (final NumberFormatException e) {
                    System.out.println("RR format\u0131 yanl\u0131\u015f: Say\u0131 format\u0131nda hata.");
                }
            }
            else {
                System.out.println("RR format\u0131 yanl\u0131\u015f.");
            }
        }
        if (message.contains("Name:")) {
            DesktopLauncher.Name = message.split("Name: ")[1].trim();
        }
        if (message.contains("Message:")) {
            final String warKey = message.substring(message.indexOf("Message:") + "Message:".length()).trim();
            InGame_Console.addMessage_Client(warKey);
        }
        if (message.contains("RTS: ON")) {
            Game.gameThread.play = true;
        }
        else if (message.contains("RTS: OFF")) {
            Game.gameThread.play = false;
        }
        String[] parts;
        if (message.contains("ChangeMoney:") && (parts = message.split(" = ")).length == 2) {
            final String warKey = parts[0].split(":")[1].trim();
            final String peaceData = parts[1].trim();
            Game.getCiv(Integer.parseInt(warKey)).fGold = Float.parseFloat(peaceData);
            System.out.println("Money [" + warKey + "] de\u011feri g\u00fcncellendi: " + peaceData);
        }
        if (message.contains("Data: Year = ") && (parts = message.split(" = ")).length == 2) {
            final String warKey = parts[1].trim();
            Game_Calendar.currentYear = Integer.parseInt(warKey);
            System.out.println("Al\u0131nan y\u0131l: " + warKey);
        }
        if (message.contains("Data: Month = ") && (parts = message.split(" = ")).length == 2) {
            final String warKey = parts[1].trim();
            Game_Calendar.currentMonth = Integer.parseInt(warKey);
            System.out.println("Al\u0131nan ay: " + warKey);
        }
        if (message.contains("Data: Day = ") && (parts = message.split(" = ")).length == 2) {
            final String warKey = parts[1].trim();
            Game_Calendar.currentDay = Integer.parseInt(warKey);
            System.out.println("Al\u0131nan g\u00fcn: " + warKey);
        }
        if (message.contains("Data: Hours = ") && (parts = message.split(" = ")).length == 2) {
            final String warKey = parts[1].trim();
            Game_Calendar.HOUR = Integer.parseInt(warKey);
            System.out.println("Al\u0131nan saat: " + warKey);
        }
        if (message.contains("Time: Speed = ") && (parts = message.split(" = ")).length == 2) {
            final String warKey = parts[1].trim();
            Game.gameThread.updateSpeedClient(Integer.parseInt(warKey));
            System.out.println("Al\u0131nan h\u0131z: " + warKey);
        }
        if (message.contains("WANT: data") && DesktopLauncher.host) {
            SMS("Data: Year = " + Game.gameAges.getYear(Game_Calendar.getYear()));
            SMS("Data: Day = " + Game_Calendar.getDay());
            SMS("Data: Month = " + Game_Calendar.getMonth());
            SMS("Data: Hours = " + Game_Calendar.HOUR);
            SMS("RTS: " + (Game.gameThread.play ? "ON" : "OFF"));
            SMS("Time: Speed = " + Game.gameThread.playSpeed);
            final ArrayList<String> list = new ArrayList<String>();
            for (int i = 1; i < Game.getCivsSize(); ++i) {
                list.add(i + " " + (int)Game.getCiv(i).fGold);
            }
            final String listString = list.stream().map((Function<? super Object, ?>)String::valueOf).collect((Collector<? super Object, ?, String>)Collectors.joining(","));
            SMS("Money: " + listString);
            final ArrayList<String> list2 = new ArrayList<String>();
            for (int j = 0; j < Game.getProvincesSize(); ++j) {
                list2.add(j + " " + Game.getCiv(Game.getProvince(j).getCivID()).getCivID());
            }
            final String listString2 = list2.stream().map((Function<? super Object, ?>)String::valueOf).collect((Collector<? super Object, ?, String>)Collectors.joining(","));
            SMS("ProvinceSET: " + listString2);
        }
        if (message.contains("Recruit: ")) {
            final String warKey = message.substring(message.indexOf("Recruit:") + 8).trim();
            final String[] invadeParts = warKey.split(" ");
            if (invadeParts.length == 5) {
                final int civId = Integer.parseInt(invadeParts[0]);
                provinceId = Integer.parseInt(invadeParts[1]);
                final int key2 = Integer.parseInt(invadeParts[2]);
                AI_RecruitArmy.recruitArmy_New2(civId, key2, provinceId);
            }
            else {
                System.out.println("Recruit format\u0131 yanl\u0131\u015f.");
            }
        }
        if (message.contains("CR:")) {
            final String peaceData = message.substring(message.indexOf("CR:") + 3).trim();
            final String[] split;
            final String[] peaceParts = split = peaceData.split(",");
            for (final String part : split) {
                final String[] details = part.trim().split(" ");
                if (details.length == 9) {
                    DesktopLauncher.uID = Integer.parseInt(details[0]);
                    DesktopLauncher.aID = Integer.parseInt(details[1]);
                    DesktopLauncher.num = Integer.parseInt(details[2]);
                    DesktopLauncher.mo = Float.parseFloat(details[3]);
                    final String key3 = details[4];
                    DesktopLauncher.numARMY = Integer.parseInt(details[5]);
                    DesktopLauncher.keyARMY = details[6];
                    DesktopLauncher.civId = Integer.parseInt(details[7]);
                    provinceId = Integer.parseInt(details[8]);
                }
            }
            for (int i = 0; i < DesktopLauncher.numARMY; ++i) {
                final ArmyRegiment newRegiment = new ArmyRegiment();
                newRegiment.uID = DesktopLauncher.uID;
                newRegiment.aID = DesktopLauncher.aID;
                newRegiment.num = DesktopLauncher.num;
                newRegiment.mo = DesktopLauncher.mo;
                DesktopLauncher.a31.add(newRegiment);
            }
            Game.getProvince(provinceId).removeArmyAll(DesktopLauncher.civId, DesktopLauncher.keyARMY);
            Game.getProvince(provinceId).addArmy(new ArmyDivision(DesktopLauncher.civId, provinceId, DesktopLauncher.a31, DesktopLauncher.keyARMY));
        }
        if (message.contains("Selected:")) {
            final String peaceData = message.substring(message.indexOf("Selected:") + 9).trim();
            final String[] split2;
            final String[] peaceParts = split2 = peaceData.split(",");
            for (final String part : split2) {
                final String[] details = part.trim().split(" ");
                if (details.length == 1) {
                    DesktopLauncher.uID = Integer.parseInt(details[0]);
                }
            }
            DesktopLauncher.player.add(DesktopLauncher.uID);
        }
        if (message.contains("Move: ")) {
            final String moveData = message.substring(message.indexOf("Move:") + 5).trim();
            final String[] moveParts = moveData.split(" ");
            if (moveParts.length == 5) {
                final int civId2 = Integer.parseInt(moveParts[0]);
                provinceId = Integer.parseInt(moveParts[1]);
                final int regroupProvinceId = Integer.parseInt(moveParts[2]);
                final int extraY = Integer.parseInt(moveParts[3]);
                final String armyKey = moveParts[4];
                final int ID2 = Game.getProvince(provinceId).getArmyKeyID(armyKey);
                Game.getCiv(civId2).newMove(provinceId, regroupProvinceId, armyKey, extraY, false);
                System.out.println("Asker hareket ettirildi: CivID: " + civId2 + ", ProvinceID: " + provinceId + ", RegroupProvinceID: " + regroupProvinceId + ", ArmyKey: " + armyKey + ", ExtraY: " + extraY);
            }
            else {
                System.out.println("Move format\u0131 yanl\u0131\u015f.");
            }
        }
        if (message.contains("DELETE = ARMY: ")) {
            final String moveData = message.substring(message.indexOf("DELETE = ARMY:") + 14).trim();
            final String[] moveParts = moveData.split(" ");
            if (moveParts.length == 3) {
                final String armyKey2 = moveParts[0];
                final int CID = Integer.parseInt(moveParts[1]);
                final int PID = Integer.parseInt(moveParts[2]);
                final int ID3 = Game.getProvince(provinceId).getArmyKeyID(armyKey2);
                Game.getProvince(provinceId).removeArmyAll(DesktopLauncher.civId, armyKey2);
            }
            else {
                System.out.println("heci bu yanlis amg.");
            }
        }
        if (message.contains("PeaceOK:")) {
            final String warKey = message.substring(message.indexOf("PeaceOK:") + 8).trim();
            WarManager.lWars.get(warKey).peaceTreaty();
        }
        if (message.contains("War:")) {
            final String warData = message.substring(message.indexOf("War:") + 4).trim();
            final String[] warParts = warData.split(" ");
            if (warParts.length >= 3) {
                try {
                    final int civId3 = Integer.parseInt(warParts[0]);
                    final int i2 = Integer.parseInt(warParts[1]);
                    final String KEY = warParts[2];
                    final ArrayList<Integer> callToWarList = new ArrayList<Integer>();
                    final String callToWarData;
                    if (warParts.length > 3 && !(callToWarData = warParts[3].replace("[", "").replace("]", "")).isEmpty()) {
                        for (final String id : callToWarData.split(",")) {
                            callToWarList.add(Integer.parseInt(id.trim()));
                        }
                    }
                    DiplomacyManager.declareWar2(civId3, i2, true, callToWarList, false, KEY);
                    System.out.println("Sava\u015f ba\u015flat\u0131ld\u0131: PlayerCivID: " + civId3 + ", OpponentCivID: " + i2 + ", CallToWar: " + callToWarList);
                }
                catch (final NumberFormatException var21) {
                    System.out.println("War format\u0131 yanl\u0131\u015f: Say\u0131 format\u0131nda hata.");
                }
            }
            else {
                System.out.println("War format\u0131 yanl\u0131\u015f.");
            }
        }
        if (message.contains("Relation:")) {
            final String warData = message.substring(message.indexOf("Relation:") + 9).trim();
            final String[] warParts = warData.split(" ");
            if (warParts.length >= 3) {
                try {
                    final int civId4 = Integer.parseInt(warParts[0]);
                    final int i3 = Integer.parseInt(warParts[1]);
                    provinceId = Integer.parseInt(warParts[1]);
                    Game.getCiv(civId4).diplomacy.updateRelation(civId4, i3, (float)provinceId);
                }
                catch (final NumberFormatException var22) {
                    System.out.println("War format\u0131 yanl\u0131\u015f: Say\u0131 format\u0131nda hata.");
                }
            }
            else {
                System.out.println("War format\u0131 yanl\u0131\u015f.");
            }
        }
        if (message.contains("SELL: ")) {
            final String warKey = message.substring(message.indexOf("SELL:") + 5).trim();
            final String[] invadeParts = warKey.split(" ");
            if (invadeParts.length == 4) {
                provinceId = Integer.parseInt(invadeParts[0]);
                final int provinceId2 = Integer.parseInt(invadeParts[1]);
                final int provinceId3 = Integer.parseInt(invadeParts[2]);
                final float provinceId4 = Float.parseFloat(invadeParts[3]);
                Game.getProvince(provinceId).setCivID(provinceId2);
                final Civilization civ2 = Game.getCiv(provinceId3);
                civ2.fGold += provinceId4;
                System.out.println("\u0130\u015fgal ger\u00e7ekle\u015fti: ProvinceID: " + provinceId + ", OccupiedByCivID: " + provinceId);
            }
            else {
                System.out.println("Invade format\u0131 yanl\u0131\u015f.");
            }
        }
        if (message.contains("Savunma:")) {
            final String pactData = message.substring(message.indexOf("Savunma:") + 8).trim();
            final String[] pactParts = pactData.split(" ");
            if (pactParts.length == 2) {
                final int civId5 = Integer.parseInt(pactParts[0]);
                final int civId6 = Integer.parseInt(pactParts[1]);
                DiplomacyManager.offerDefensivePact(civId5, civId6);
                System.out.println("Savunma pakti teklifi g\u00f6nderildi: CivID1: " + civId5 + ", CivID2: " + civId6);
            }
            else {
                System.out.println("Savunma pakti format\u0131 yanl\u0131\u015f.");
            }
        }
        if (message.contains("SavunmaOK:")) {
            final String pactData = message.substring(message.indexOf("SavunmaOK:") + 10).trim();
            final String[] pactParts = pactData.split(" ");
            if (pactParts.length == 2) {
                final int civId5 = Integer.parseInt(pactParts[0]);
                final int civId6 = Integer.parseInt(pactParts[1]);
                DiplomacyManager.acceptDefensivePactOffer(civId5, civId6);
                System.out.println("Savunma pakti kuruldu: CivID1: " + civId5 + ", CivID2: " + civId6);
            }
            else {
                System.out.println("Savunma pakti kabul format\u0131 yanl\u0131\u015f.");
            }
        }
        if (message.contains("Ally:")) {
            final String pactData = message.substring(message.indexOf("Ally:") + 5).trim();
            final String[] pactParts = pactData.split(" ");
            if (pactParts.length == 2) {
                final int civId5 = Integer.parseInt(pactParts[0]);
                final int civId6 = Integer.parseInt(pactParts[1]);
                DiplomacyManager.offerAlliance(civId5, civId6);
                DiplomacyManager.offerAlliance(civId5, civId6);
                System.out.println("\u0130ttifak teklifi g\u00f6nderildi: CivID1: " + civId5 + ", CivID2: " + civId6);
            }
            else {
                System.out.println("\u0130ttifak format\u0131 yanl\u0131\u015f.");
            }
        }
        if (message.contains("AllyOK:")) {
            final String pactData = message.substring(message.indexOf("AllyOK:") + 7).trim();
            final String[] pactParts = pactData.split(" ");
            if (pactParts.length == 2) {
                final int civId5 = Integer.parseInt(pactParts[0]);
                final int civId6 = Integer.parseInt(pactParts[1]);
                DiplomacyManager.acceptAllianceOffer(civId5, civId6);
                System.out.println("\u0130ttifak kuruldu: CivID1: " + civId5 + ", CivID2: " + civId6);
            }
            else {
                System.out.println("\u0130ttifak kabul format\u0131 yanl\u0131\u015f.");
            }
        }
        if (message.contains("NonAgression:")) {
            final String pactData = message.substring(message.indexOf("NonAgression:") + 13).trim();
            final String[] pactParts = pactData.split(" ");
            if (pactParts.length == 2) {
                final int civId5 = Integer.parseInt(pactParts[0]);
                final int civId6 = Integer.parseInt(pactParts[1]);
                DiplomacyManager.acceptDefensivePactOffer(civId5, civId6);
                System.out.println("Savunma pakti kuruldu: CivID1: " + civId5 + ", CivID2: " + civId6);
            }
            else {
                System.out.println("Savunma pakti kabul format\u0131 yanl\u0131\u015f.");
            }
        }
        if (message.contains("NonAgressionOK:")) {
            final String pactData = message.substring(message.indexOf("NonAgressionOK:") + 15).trim();
            final String[] pactParts = pactData.split(" ");
            if (pactParts.length == 2) {
                final int civId5 = Integer.parseInt(pactParts[0]);
                final int civId6 = Integer.parseInt(pactParts[1]);
                DiplomacyManager.acceptAllianceOffer(civId5, civId6);
                System.out.println("\u0130ttifak Anla\u015fmas\u0131 Reddi: CivID1: " + civId5 + ", CivID2: " + civId6);
            }
            else {
                System.out.println("\u0130ttifak Reddi format\u0131 yanl\u0131\u015f.");
            }
        }
        if (message.contains("UpgradeCapital:")) {
            final String pactData = message.substring(message.indexOf("UpgradeCapital:") + 15).trim();
            final String[] pactParts = pactData.split(" ");
            if (pactParts.length == 1) {
                final int civId5 = Integer.parseInt(pactParts[0]);
                final boolean bl = Game.getCiv(civId5).upgradeCapitalCity_Client();
            }
            else {
                System.out.println("\u0130ttifak Reddi format\u0131 yanl\u0131\u015f.");
            }
        }
        if (message.contains("UpgradeMilitaryAcademy:")) {
            final String pactData = message.substring(message.indexOf("UpgradeMilitaryAcademy:") + 23).trim();
            final String[] pactParts = pactData.split(" ");
            if (pactParts.length == 1) {
                final int civId5 = Integer.parseInt(pactParts[0]);
                final boolean bl = Game.getCiv(civId5).upgradeMilitaryAcademy_Client();
            }
            else {
                System.out.println("\u0130ttifak Reddi format\u0131 yanl\u0131\u015f.");
            }
        }
        if (message.contains("UpgradeMilitaryAcademyForGenerals:")) {
            final String pactData = message.substring(message.indexOf("UpgradeMilitaryAcademyForGenerals:") + 31).trim();
            final String[] pactParts = pactData.split(" ");
            if (pactParts.length == 1) {
                final int civId5 = Integer.parseInt(pactParts[0]);
                final boolean bl = Game.getCiv(civId5).upgradeMilitaryAcademyForGenerals_Client();
            }
            else {
                System.out.println("\u0130ttifak Reddi format\u0131 yanl\u0131\u015f.");
            }
        }
        if (message.contains("Bomb:")) {
            final String pactData = message.substring(message.indexOf("Bomb:") + 5).trim();
            final String[] pactParts = pactData.split(" ");
            if (pactParts.length == 2) {
                final int civId5 = Integer.parseInt(pactParts[0]);
                final int civId6 = Integer.parseInt(pactParts[1]);
                Game.dropAtomicBomb_Client(civId5, civId6);
            }
            else {
                System.out.println("\u0130ttifak Reddi format\u0131 yanl\u0131\u015f.");
            }
        }
        if (message.contains("ChangeIdeology:")) {
            final String pactData = message.substring(message.indexOf("ChangeIdeology:") + 15).trim();
            final String[] pactParts = pactData.split(" ");
            if (pactParts.length == 2) {
                final int civId5 = Integer.parseInt(pactParts[0]);
                final int civId6 = Integer.parseInt(pactParts[1]);
                Game.ideologiesManager.changeGovernmentType_Client(civId5, civId6, false);
            }
            else {
                System.out.println("\u0130ttifak Reddi format\u0131 yanl\u0131\u015f.");
            }
        }
        if (message.contains("ChangeReligion:")) {
            final String pactData = message.substring(message.indexOf("ChangeReligion:") + 15).trim();
            final String[] pactParts = pactData.split(" ");
            if (pactParts.length == 2) {
                final int civId5 = Integer.parseInt(pactParts[0]);
                final int civId6 = Integer.parseInt(pactParts[1]);
                Game.getCiv(civId5).setReligionID_UpdateBonuses_Client(civId6);
            }
            else {
                System.out.println("\u0130ttifak Reddi format\u0131 yanl\u0131\u015f.");
            }
        }
        if (message.contains("PeaceProvince:")) {
            final String peaceData = message.substring(message.indexOf("PeaceProvince:") + 14).trim();
            final String[] peaceParts = peaceData.split(" \\[|\\]")[0].trim().split(" ");
            provinceId = Integer.parseInt(peaceParts[0]);
            for (final String civ : peaceParts[1].split(",")) {
                final int civIdToSet = Integer.parseInt(civ.trim());
                Game.getProvince(civIdToSet).setCivID(provinceId);
                System.out.println("Bar\u0131\u015f sa\u011fland\u0131: ProvinceID: " + provinceId + ", CivID: " + civIdToSet);
            }
        }
        if (message.contains("Money:")) {
            final String peaceData = message.substring(message.indexOf("Money:") + 6).trim();
            final String[] split5;
            final String[] peaceParts = split5 = peaceData.split(",");
            for (final String part : split5) {
                final String[] details = part.trim().split(" ");
                if (details.length == 2) {
                    provinceId = Integer.parseInt(details[0]);
                    final int civIdToSet = Integer.parseInt(details[1]);
                    Game.getCiv(provinceId).fGold = (float)civIdToSet;
                }
            }
        }
        if (message.contains("ProvinceSET:")) {
            final String peaceData = message.substring(message.indexOf("ProvinceSET:") + 12).trim();
            final String[] split6;
            final String[] peaceParts = split6 = peaceData.split(",");
            for (final String part : split6) {
                final String[] details = part.trim().split(" ");
                if (details.length == 2) {
                    provinceId = Integer.parseInt(details[0]);
                    final int civIdToSet = Integer.parseInt(details[1]);
                    Game.getProvince(provinceId).setCivID(civIdToSet);
                }
            }
        }
    }
    
    public static String extractUidFromMessage(final String message) {
        final String[] parts = message.split(":");
        return (parts.length > 1) ? parts[1].trim() : "Bilinmeyen UID";
    }
    
    public static void displayJoinMessage(final String uid) {
        final ArrayList<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final ArrayList<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_Text("JOIN: " + uid + "", CFG.FONT_BOLD_SMALL));
        nElements.add(new MenuElement_HoverElement(nData));
        Game.menuManager.addToast(new Toast(nElements, 0, 10000, CFG.GAME_WIDTH, ImageManager.getImage(Images.topStats).getHeight() + CFG.PADDING * 2));
        Game.gameThread.play = false;
    }
    
    public static void disconnectTimeout() {
        final ArrayList<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final ArrayList<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_Text("Disconnected: Timeout", CFG.FONT_BOLD_SMALL));
        nElements.add(new MenuElement_HoverElement(nData));
        Game.menuManager.addToast(new Toast(nElements, 0, 5000, CFG.GAME_WIDTH, ImageManager.getImage(Images.topStats).getHeight() + CFG.PADDING * 2));
        Game.gameThread.play = false;
    }
    
    static {
        DesktopLauncher.uID = 1;
        DesktopLauncher.numARMY = 1;
        DesktopLauncher.civId = 1;
        DesktopLauncher.keyARMY = "";
        DesktopLauncher.a31 = new ArrayList<ArmyRegiment>();
        DesktopLauncher.host = false;
        DesktopLauncher.player = new ArrayList<Integer>();
        DesktopLauncher.Name = "31ci baba";
    }
}
