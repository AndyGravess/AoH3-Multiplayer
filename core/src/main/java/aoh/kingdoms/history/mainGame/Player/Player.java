// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.Player;

import aoc.kingdoms.lukasz.menusInGame.InGame_Event;
import aoc.kingdoms.lukasz.events.EventsManager;
import aoc.kingdoms.lukasz.jakowski.Missions.MissionTree;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Player.More.PlayerActiveEvent;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.map.battles.BattleReport;
import aoc.kingdoms.lukasz.map.PeaceTreaty;
import aoc.kingdoms.lukasz.map.civilization.CivilizationAdvisorsPool;
import aoc.kingdoms.lukasz.map.civilization.CivilizationGeneralsPool;
import aoc.kingdoms.lukasz.jakowski.Player.More.PlayerFogOfWar;
import aoc.kingdoms.lukasz.map.FormableCivManager;
import aoc.kingdoms.lukasz.jakowski.Player.Notification.Notification;
import aoc.kingdoms.lukasz.jakowski.Player.MessageTypes.PMessage;
import java.util.List;
import aoc.kingdoms.lukasz.jakowski.Player.More.PlayerCurrentSituation;
import aoc.kingdoms.lukasz.jakowski.Player.More.PlayerStats3;
import aoc.kingdoms.lukasz.jakowski.Player.More.PlayerStats2;
import aoc.kingdoms.lukasz.jakowski.Player.More.PlayerStats;
import aoc.kingdoms.lukasz.jakowski.Player.More.PlayerData;

public class Player
{
    public int iCivID;
    public PlayerData playerData;
    public PlayerStats playerStats;
    public PlayerStats2 playerStats2;
    public PlayerStats3 playerStats3;
    public boolean allowAIMove;
    public PlayerCurrentSituation currSituation;
    public List<PMessage> messages;
    public int iMessagesSize;
    public List<Notification> lNotifications;
    public int iNotificationsSize;
    public List<FormableCivManager.FormableCiv> formableCivs;
    public PlayerFogOfWar fog;
    public CivilizationGeneralsPool civilizationGeneralsPool;
    public CivilizationAdvisorsPool civAdvisorsPool_Administration;
    public CivilizationAdvisorsPool civAdvisorsPool_Economy;
    public CivilizationAdvisorsPool civAdvisorsPool_Technology;
    public CivilizationAdvisorsPool civAdvisorsPool_Military;
    public PeaceTreaty peaceTreaty;
    public int iActiveEventsSize;
    public int iPinnedArmiesSize;
    public int iPinnedProvincesSize;
    public List<BattleReport> lBattleReports;
    public int iBattleReportsSize;
    
    public Player() {
        this.iCivID = 0;
        this.playerData = new PlayerData();
        this.playerStats = new PlayerStats();
        this.playerStats2 = new PlayerStats2();
        this.playerStats3 = new PlayerStats3();
        this.allowAIMove = false;
        this.currSituation = new PlayerCurrentSituation();
        this.messages = new ArrayList<PMessage>();
        this.iMessagesSize = 0;
        this.lNotifications = new ArrayList<Notification>();
        this.iNotificationsSize = 0;
        this.formableCivs = new ArrayList<FormableCivManager.FormableCiv>();
        this.fog = new PlayerFogOfWar();
        this.civilizationGeneralsPool = new CivilizationGeneralsPool();
        this.civAdvisorsPool_Administration = new CivilizationAdvisorsPool(0);
        this.civAdvisorsPool_Economy = new CivilizationAdvisorsPool(1);
        this.civAdvisorsPool_Technology = new CivilizationAdvisorsPool(2);
        this.civAdvisorsPool_Military = new CivilizationAdvisorsPool(3);
        this.peaceTreaty = null;
        this.iActiveEventsSize = 0;
        this.iPinnedArmiesSize = 0;
        this.iPinnedProvincesSize = 0;
        this.lBattleReports = new ArrayList<BattleReport>();
        this.iBattleReportsSize = 0;
    }
    
    public final void addActiveEvent(final int eventType, final int id, final int turnsActive) {
        try {
            for (int i = this.iActiveEventsSize - 1; i >= 0; --i) {
                if (this.playerData.activeEvents.get(i).eventType == eventType && this.playerData.activeEvents.get(i).id == id) {
                    return;
                }
            }
            this.playerData.activeEvents.add(new PlayerActiveEvent(eventType, id, Game_Calendar.TURN_ID + turnsActive));
            this.iActiveEventsSize = this.playerData.activeEvents.size();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void removeActiveEvent(final int eventType, final int id) {
        try {
            for (int i = this.iActiveEventsSize - 1; i >= 0; --i) {
                if (this.playerData.activeEvents.get(i).eventType == eventType && this.playerData.activeEvents.get(i).id == id) {
                    this.playerData.activeEvents.remove(i);
                    this.iActiveEventsSize = this.playerData.activeEvents.size();
                    return;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void addMessage(final PMessage nMessage) {
        try {
            if (nMessage.fromCivID < 0 || Game.getCiv(nMessage.fromCivID).getNumOfProvinces() <= 0) {
                return;
            }
            this.messages.add(nMessage);
            this.iMessagesSize = this.messages.size();
            Game.addSimpleTask(new Game.SimpleTask("rebuildInGame_MessagesSavePos") {
                @Override
                public void update() {
                    Game.menuManager.rebuildInGame_MessagesSavePos();
                }
            });
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void removeMessage(final String key) {
        try {
            for (int i = this.iMessagesSize - 1; i >= 0; --i) {
                if (this.messages.get(i).key.equals(key)) {
                    this.messages.remove(i);
                    this.iMessagesSize = this.messages.size();
                    Game.addSimpleTask(new Game.SimpleTask("rebuildInGame_MessagesSavePos") {
                        @Override
                        public void update() {
                            Game.menuManager.rebuildInGame_MessagesSavePos();
                        }
                    });
                    return;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public PMessage getMessage(final String key) {
        try {
            for (int i = this.iMessagesSize - 1; i >= 0; --i) {
                if (this.messages.get(i).key.equals(key)) {
                    return this.messages.get(i);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return null;
    }
    
    public MenuElement_Hover getMessage_Hover(final String key) {
        try {
            for (int i = this.iMessagesSize - 1; i >= 0; --i) {
                if (this.messages.get(i).key.equals(key)) {
                    return this.messages.get(i).buildElementHover();
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return null;
    }
    
    public final void updateMessages() {
        try {
            for (int i = this.iMessagesSize - 1; i >= 0; --i) {
                if (this.messages.get(i).expiresTurnID <= Game_Calendar.TURN_ID) {
                    this.messages.get(i).onRefuse();
                    this.messages.remove(i);
                    this.iMessagesSize = this.messages.size();
                    Game.addSimpleTask(new Game.SimpleTask("rebuildInGame_MessagesSavePos") {
                        @Override
                        public void update() {
                            Game.menuManager.rebuildInGame_MessagesSavePos();
                        }
                    });
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void clearMessages() {
        this.messages.clear();
        this.iMessagesSize = 0;
    }
    
    public void addNotification(final Notification notification) {
        if (notification.notificationType == Notification.Notification_Type.NEIGHBOR_OR_RIVAL_AT_WAR) {
            for (int i = 0; i < this.iNotificationsSize; ++i) {
                if (this.lNotifications.get(i).id == notification.id) {
                    return;
                }
            }
        }
        this.lNotifications.add(notification);
        this.iNotificationsSize = this.lNotifications.size();
        Game.addSimpleTask(new Game.SimpleTask("rebuildNotifications") {
            @Override
            public void update() {
                Game.menuManager.rebuildInGame_Notifications();
            }
        });
    }
    
    public void addNotification_Reinforce(final Notification notification) {
        for (int i = 0; i < this.iNotificationsSize; ++i) {
            if (this.lNotifications.get(i).notificationType == Notification.Notification_Type.REINFORCE_ARMY_COST) {
                final Notification notification2 = this.lNotifications.get(i);
                notification2.id += notification.id;
                final float reinforceCost = this.lNotifications.get(i).id / 100.0f;
                this.lNotifications.get(i).sText = Game.lang.get("ReinforceCost") + ": " + CFG.getPrecision2(reinforceCost, (reinforceCost > 99.9f) ? 1 : ((reinforceCost < 0.1f) ? 100 : 10));
                Game.addSimpleTask(new Game.SimpleTask("rebuildNotifications") {
                    @Override
                    public void update() {
                        Game.menuManager.rebuildInGame_Notifications();
                    }
                });
                return;
            }
        }
        this.lNotifications.add(notification);
        this.iNotificationsSize = this.lNotifications.size();
        Game.addSimpleTask(new Game.SimpleTask("rebuildNotifications") {
            @Override
            public void update() {
                Game.menuManager.rebuildInGame_Notifications();
            }
        });
    }
    
    public void addNotification_Unrest(final Notification notification) {
        int num = 0;
        for (int i = 0; i < this.iNotificationsSize; ++i) {
            if (this.lNotifications.get(i).notificationType == Notification.Notification_Type.HIGH_UNREST) {
                ++num;
            }
        }
        if (num >= GameValues.rebels.NOTIFICATIONS_UNREST_LIMIT) {
            return;
        }
        this.lNotifications.add(notification);
        this.iNotificationsSize = this.lNotifications.size();
        Game.addSimpleTask(new Game.SimpleTask("rebuildNotifications") {
            @Override
            public void update() {
                Game.menuManager.rebuildInGame_Notifications();
            }
        });
    }
    
    public void removeNotification(final int i) {
        try {
            switch (this.lNotifications.get(i).notificationType) {
                case BATTLE_REPORT: {
                    this.removeReport(this.lNotifications.get(i).key);
                    break;
                }
            }
            this.lNotifications.remove(i);
            this.iNotificationsSize = this.lNotifications.size();
            Game.addSimpleTask(new Game.SimpleTask("rebuildNotifications") {
                @Override
                public void update() {
                    Game.menuManager.rebuildInGame_Notifications();
                }
            });
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateNotifications() {
        try {
            for (int i = this.iNotificationsSize - 1; i >= 0; --i) {
                if (Game_Calendar.TURN_ID - this.lNotifications.get(i).iTurnID > GameValues.notifications.REMOVE_NOTIFICATION_DAYS) {
                    this.removeNotification(i);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void clearNotifications() {
        this.lNotifications.clear();
        this.iNotificationsSize = 0;
    }
    
    public final void addBattleReport(final BattleReport battleReport) {
        this.lBattleReports.add(battleReport);
        this.iBattleReportsSize = this.lBattleReports.size();
    }
    
    public final void removeReport(final String key) {
        for (int i = 0; i < this.iBattleReportsSize; ++i) {
            if (this.lBattleReports.get(i).key.equals(key)) {
                this.lBattleReports.remove(i);
                this.iBattleReportsSize = this.lBattleReports.size();
                return;
            }
        }
    }
    
    public final void clearBattleReport() {
        this.lBattleReports.clear();
        this.iBattleReportsSize = 0;
    }
    
    public final int getBattleReportID(final String key) {
        for (int i = this.iBattleReportsSize - 1; i >= 0; --i) {
            if (this.lBattleReports.get(i).key.equals(key)) {
                return i;
            }
        }
        return -1;
    }
    
    public final void initPeaceTreaty_Player(final String nWarKey) {
        for (int i = 0; i < Game.getProvincesSize(); ++i) {
            Game.getProvince(i).peaceTreatyIsToTake = false;
            Game.getProvince(i).peaceTreatyIsTaken = false;
        }
        this.peaceTreaty = new PeaceTreaty(this.iCivID, nWarKey, true);
    }
    
    public final void updateEvents() {
        for (int i = this.iActiveEventsSize - 1; i >= 0; --i) {
            if (this.playerData.activeEvents.get(i).iTurnID + GameValues.events.EVENT_TIME_TO_RESPOND - Game_Calendar.TURN_ID <= 0) {
                final int tEventType = this.playerData.activeEvents.get(i).eventType;
                final int tID = this.playerData.activeEvents.get(i).id;
                Game.player.playerData.activeEvents.remove(i);
                this.iActiveEventsSize = this.playerData.activeEvents.size();
                if (tEventType == 999) {
                    MissionTree.takeMissionDecision(Game.player.iCivID, tID, 0);
                }
                else if (tEventType == 1000) {
                    MissionTree.takeMissionDecision_Civ(Game.player.iCivID, tID, 0);
                }
                else {
                    EventsManager.takeEventDecision(Game.player.iCivID, tEventType, tID, 0);
                }
                Game.menuManager.setVisibleInGame_Event(false);
                Game.addSimpleTask(new Game.SimpleTask("RebuildInGameRight", tID) {
                    @Override
                    public void update() {
                        Game.menuManager.rebuildInGame_Right();
                        if (Game.menuManager.getVisibleInGame_Event() && InGame_Event.eventID == this.id) {
                            Game.menuManager.setVisibleInGame_Event(false);
                        }
                    }
                });
            }
        }
    }
    
    public final void clearEvents() {
        this.playerData.activeEvents.clear();
        this.iActiveEventsSize = 0;
    }
    
    public final void actionPinArmy(final String key) {
        if (!this.addPinArmy(key)) {
            this.removePinArmy(key);
        }
    }
    
    public final boolean addPinArmy(final String key) {
        try {
            for (int i = this.iPinnedArmiesSize - 1; i >= 0; --i) {
                if (this.playerData.pinnedArmies.get(i).equals(key)) {
                    return false;
                }
            }
            this.playerData.pinnedArmies.add(key);
            this.iPinnedArmiesSize = this.playerData.pinnedArmies.size();
            return true;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            return true;
        }
    }
    
    public final void removePinArmy(final String key) {
        try {
            for (int i = this.iPinnedArmiesSize - 1; i >= 0; --i) {
                if (this.playerData.pinnedArmies.get(i).equals(key)) {
                    this.playerData.pinnedArmies.remove(i);
                    this.iPinnedArmiesSize = this.playerData.pinnedArmies.size();
                    return;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void clearPinnedArmy() {
        this.playerData.pinnedArmies.clear();
        this.iPinnedArmiesSize = 0;
    }
    
    public boolean isPinned(final String key) {
        try {
            for (int i = this.iPinnedArmiesSize - 1; i >= 0; --i) {
                if (this.playerData.pinnedArmies.get(i).equals(key)) {
                    return true;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return false;
    }
    
    public void loadFormableCivs() {
        this.clearFormableCivs();
        for (int i = 0; i < Game.getCiv(this.iCivID).sTagsCanForm.size(); ++i) {
            final FormableCivManager.FormableCiv formableCiv = FormableCivManager.getFormableCivilization(Game.getCiv(this.iCivID).sTagsCanForm.get(i));
            if (formableCiv == null) {
                Game.getCiv(this.iCivID).sTagsCanForm.remove(i);
            }
            else {
                this.formableCivs.add(formableCiv);
            }
        }
    }
    
    public void clearFormableCivs() {
        this.formableCivs.clear();
    }
}
