// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.map.diplomacy;

import aoc.kingdoms.lukasz.map.RivalsManager;
import aoc.kingdoms.lukasz.jakowski.AI.Diplomacy.AI_Rivals;
import aoc.kingdoms.lukasz.jakowski.Player.MessageTypes.PMessageRivalryExpired;
import aoc.kingdoms.lukasz.jakowski.Player.MessageTypes.PMessageRivals;
import aoc.kingdoms.lukasz.jakowski.Player.MessageTypes.PMessageMilitaryAccessExpired;
import aoc.kingdoms.lukasz.menusInGame.Info.InGame_Info;
import aoc.kingdoms.lukasz.jakowski.Player.MessageTypes.PMessageAllianceExpired;
import aoc.kingdoms.lukasz.jakowski.Player.MessageTypes.PMessageGuaranteeExpired_We;
import aoc.kingdoms.lukasz.jakowski.Player.MessageTypes.PMessageGuaranteeExpired_OurInd;
import aoc.kingdoms.lukasz.jakowski.Player.MessageTypes.PMessageNonAggressionPactExpired;
import aoc.kingdoms.lukasz.jakowski.Player.MessageTypes.PMessageDefensivePactExpired;
import aoc.kingdoms.lukasz.jakowski.Player.MessageTypes.PMessageTruceExpired;
import aoc.kingdoms.lukasz.map.war.WarManager;
import aoc.kingdoms.lukasz.map.war.War;
import aoc.kingdoms.lukasz.jakowski.Player.MessageTypes.PMessageDamagingRelations;
import aoc.kingdoms.lukasz.jakowski.Player.MessageTypes.PMessage;
import aoc.kingdoms.lukasz.jakowski.Player.MessageTypes.PMessageImprovingRelations;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.Player.Notification.Notification;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import java.util.Iterator;
import java.util.Map;
import aoc.kingdoms.lukasz.jakowski.CFG;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Diplomacy
{
    public ConcurrentHashMap<Integer, Float> relation;
    public List<String> lWars;
    public int iWarsSize;
    public List<Integer> atWar;
    public int iAtWarSize;
    public ConcurrentHashMap<Integer, DiplomacyData> truce;
    public ConcurrentHashMap<Integer, DiplomacyData> rivals;
    public ConcurrentHashMap<Integer, DiplomacyData> alliance;
    public ConcurrentHashMap<Integer, DiplomacyData> nonAggressionPact;
    public ConcurrentHashMap<Integer, DiplomacyData> defensivePact;
    public ConcurrentHashMap<Integer, DiplomacyData> guarantee;
    public ConcurrentHashMap<Integer, DiplomacyData> guaranteeByCivID;
    public ConcurrentHashMap<Integer, DiplomacyData> militaryAccess;
    public List<Vassal> lVassals;
    public int iVassalsSize;
    public List<Diplomacy_RelationsAction> improvingRelations;
    public int iImprovingRelationsSize;
    public List<Diplomacy_RelationsAction> damagingRelations;
    public int iDamagingRelationsSize;
    
    public Diplomacy() {
        this.relation = new ConcurrentHashMap<Integer, Float>();
        this.lWars = new ArrayList<String>();
        this.iWarsSize = 0;
        this.atWar = new ArrayList<Integer>();
        this.iAtWarSize = 0;
        this.truce = new ConcurrentHashMap<Integer, DiplomacyData>();
        this.rivals = new ConcurrentHashMap<Integer, DiplomacyData>();
        this.alliance = new ConcurrentHashMap<Integer, DiplomacyData>();
        this.nonAggressionPact = new ConcurrentHashMap<Integer, DiplomacyData>();
        this.defensivePact = new ConcurrentHashMap<Integer, DiplomacyData>();
        this.guarantee = new ConcurrentHashMap<Integer, DiplomacyData>();
        this.guaranteeByCivID = new ConcurrentHashMap<Integer, DiplomacyData>();
        this.militaryAccess = new ConcurrentHashMap<Integer, DiplomacyData>();
        this.lVassals = new ArrayList<Vassal>();
        this.iVassalsSize = 0;
        this.improvingRelations = new ArrayList<Diplomacy_RelationsAction>();
        this.iImprovingRelationsSize = 0;
        this.damagingRelations = new ArrayList<Diplomacy_RelationsAction>();
        this.iDamagingRelationsSize = 0;
    }
    
    public int getVassal_TributeLevel(final int iVassalCivID) {
        try {
            for (int i = this.iVassalsSize - 1; i >= 0; --i) {
                if (this.lVassals.get(i).c == iVassalCivID) {
                    return this.lVassals.get(i).tL;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return 0;
    }
    
    public void setVassal_TributeLevel(final int iVassalCivID, final int iLevel) {
        try {
            for (int i = this.iVassalsSize - 1; i >= 0; --i) {
                if (this.lVassals.get(i).c == iVassalCivID) {
                    this.lVassals.get(i).setTribute(iLevel);
                    return;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public int getVassal_ManpowerLevel(final int iVassalCivID) {
        try {
            for (int i = this.iVassalsSize - 1; i >= 0; --i) {
                if (this.lVassals.get(i).c == iVassalCivID) {
                    return this.lVassals.get(i).mL;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return 0;
    }
    
    public void setVassal_ManpowerLevel(final int iVassalCivID, final int iLevel) {
        try {
            for (int i = this.iVassalsSize - 1; i >= 0; --i) {
                if (this.lVassals.get(i).c == iVassalCivID) {
                    this.lVassals.get(i).setManpower(iLevel);
                    return;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public void setVassal_CanDeclareWar(final int iVassalCivID, final boolean canDeclareWar) {
        try {
            for (int i = this.iVassalsSize - 1; i >= 0; --i) {
                if (this.lVassals.get(i).c == iVassalCivID) {
                    this.lVassals.get(i).cW = canDeclareWar;
                    return;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public boolean getVassal_CanDeclareWar(final int iVassalCivID) {
        try {
            for (int i = this.iVassalsSize - 1; i >= 0; --i) {
                if (this.lVassals.get(i).c == iVassalCivID) {
                    return this.lVassals.get(i).cW;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return true;
    }
    
    public float getVassal_LibertyDesire(final int iVassalCivID) {
        try {
            for (int i = this.iVassalsSize - 1; i >= 0; --i) {
                if (this.lVassals.get(i).c == iVassalCivID) {
                    return this.lVassals.get(i).lD;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return 0.0f;
    }
    
    public void setLibertyDesire_Change(final int iVassalCivID, final float value) {
        try {
            for (int i = this.iVassalsSize - 1; i >= 0; --i) {
                if (this.lVassals.get(i).c == iVassalCivID) {
                    this.lVassals.get(i).setLibertyDesire_Change(value);
                    return;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public void setVassal_LoadData(final int iVassalCivID, final int tL, final int mL, final boolean cW, final float lD) {
        try {
            for (int i = this.iVassalsSize - 1; i >= 0; --i) {
                if (this.lVassals.get(i).c == iVassalCivID) {
                    this.lVassals.get(i).tL = tL;
                    this.lVassals.get(i).mL = mL;
                    this.lVassals.get(i).cW = cW;
                    this.lVassals.get(i).lD = lD;
                    return;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateRelationsAfterRemoveCiv(final int iCivID) {
        try {
            final ConcurrentHashMap<Integer, Float> nRelation = new ConcurrentHashMap<Integer, Float>();
            for (final Map.Entry<Integer, Float> entry : this.relation.entrySet()) {
                if (entry.getKey() > iCivID) {
                    nRelation.put(entry.getKey() - 1, entry.getValue());
                }
                else {
                    if (entry.getKey() == iCivID) {
                        continue;
                    }
                    nRelation.put(entry.getKey(), entry.getValue());
                }
            }
            this.relation = nRelation;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateRelations_ToNeutral(final int civID) {
        try {
            final ConcurrentHashMap<Integer, Float> nRelation = new ConcurrentHashMap<Integer, Float>();
            for (final Map.Entry<Integer, Float> entry : this.relation.entrySet()) {
                if (entry.getValue() > 0.0f) {
                    if (entry.getValue() - GameValues.diplomacy.RELATIONS_CHANGE_PER_UPDATE_TO_NEUTRAL <= 0.0f) {
                        continue;
                    }
                    nRelation.put(entry.getKey(), Math.max(0.0f, entry.getValue() - GameValues.diplomacy.RELATIONS_CHANGE_PER_UPDATE_TO_NEUTRAL));
                }
                else {
                    if (entry.getValue() >= 0.0f) {
                        continue;
                    }
                    if (DiplomacyManager.isAtWar(civID, entry.getKey())) {
                        nRelation.put(entry.getKey(), entry.getValue());
                    }
                    else {
                        if (entry.getValue() + GameValues.diplomacy.RELATIONS_CHANGE_PER_UPDATE_TO_NEUTRAL >= 0.0f) {
                            continue;
                        }
                        nRelation.put(entry.getKey(), Math.min(0.0f, entry.getValue() + GameValues.diplomacy.RELATIONS_CHANGE_PER_UPDATE_TO_NEUTRAL));
                    }
                }
            }
            this.relation = nRelation;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public void updateRelation(final int theCivID, final int withCivID, final float nRelation) {
        try {
            if (!DiplomacyManager.isAtWar(theCivID, withCivID)) {
                this.relation.put(withCivID, Math.min(GameValues.diplomacy.DIPLOMACY_RELATIONS_MAX, Math.max(GameValues.diplomacy.DIPLOMACY_RELATIONS_MIN, this.getRelation(withCivID) + nRelation)));
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void setRelation(final int theCivID, final int withCivID, final float nRelation) {
        try {
            if (!DiplomacyManager.isAtWar(theCivID, withCivID)) {
                this.relation.put(withCivID, Math.min(GameValues.diplomacy.DIPLOMACY_RELATIONS_MAX, Math.max(GameValues.diplomacy.DIPLOMACY_RELATIONS_MIN, nRelation)));
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void setRelation_Load(final int iCivID, final float nRelation) {
        try {
            this.relation.put(iCivID, nRelation);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void setRelation_War(final int iCivID, final float nRelation) {
        try {
            this.relation.put(iCivID, nRelation);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            this.addAtWar(iCivID);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final float getRelation(final int iCivID) {
        try {
            if (this.relation.containsKey(iCivID)) {
                return this.relation.get(iCivID);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return 0.0f;
    }
    
    public final void setRelation_Peace(final int iCivID, final float nRelation, final String warKey) {
        try {
            this.relation.put(iCivID, nRelation);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            this.removeAtWar(iCivID);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            this.removeWar(warKey, iCivID);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        if (!this.isAtWar() && iCivID != Game.player.iCivID) {
            Game.getCiv(iCivID).updateMilitaryLevel(1);
        }
    }
    
    public final void updateImproveRelations(final int iFromCivID) {
        for (int i = this.iImprovingRelationsSize - 1; i >= 0; --i) {
            try {
                if (this.improvingRelations.get(i).iTurnID <= Game_Calendar.TURN_ID) {
                    if (DiplomacyManager.isAtWar(iFromCivID, this.improvingRelations.get(i).iCivID) || Game.getCiv(this.improvingRelations.get(i).iCivID).getNumOfProvinces() <= 0) {
                        this.removeImproveRelations(this.improvingRelations.get(i).iCivID);
                    }
                    else {
                        final float fRelationsUpdate = DiplomacyManager.getRelationImprove(iFromCivID, this.improvingRelations.get(i).iCivID);
                        this.updateRelation(iFromCivID, this.improvingRelations.get(i).iCivID, fRelationsUpdate);
                        Game.getCiv(this.improvingRelations.get(i).iCivID).diplomacy.updateRelation(this.improvingRelations.get(i).iCivID, iFromCivID, fRelationsUpdate);
                        this.improvingRelations.get(i).iTurnID = Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_IMPROVE_RELATIONS_TIME;
                        if (Game.getCiv(this.improvingRelations.get(i).iCivID).diplomacy.getRelation(iFromCivID) > GameValues.diplomacy.DIPLOMACY_IMPROVE_RELATIONS_MAX) {
                            if (iFromCivID == Game.player.iCivID) {
                                Game.player.addNotification(new Notification(Notification.Notification_Type.RELATIONS_COMPLETED, Game.lang.get("ImprovingRelationsCompleted"), Images.relationsUp, Game_Calendar.TURN_ID, Notification.Notification_BG.GREEN, this.improvingRelations.get(i).iCivID));
                            }
                            this.improvingRelations.remove(i);
                            this.iImprovingRelationsSize = this.improvingRelations.size();
                        }
                    }
                }
            }
            catch (final Exception ex) {}
        }
    }
    
    public final void addImproveRelations_Load(final int iCivID, final int turnID) {
        this.improvingRelations.add(new Diplomacy_RelationsAction(iCivID, turnID));
    }
    
    public final boolean addImproveRelations(final int iFromCivID, final int iCivID) {
        if (DiplomacyManager.isAtWar(iFromCivID, iCivID)) {
            return false;
        }
        if (Game.getCiv(iFromCivID).diplomacy.isRival(iCivID) || Game.getCiv(iCivID).diplomacy.isRival(iFromCivID)) {
            return false;
        }
        if (Game.getCiv(iCivID).diplomacy.getRelation(iFromCivID) >= GameValues.diplomacy.DIPLOMACY_IMPROVE_RELATIONS_MAX) {
            return false;
        }
        for (int i = this.iImprovingRelationsSize - 1; i >= 0; --i) {
            if (this.improvingRelations.get(i).iCivID == iCivID) {
                return false;
            }
        }
        this.improvingRelations.add(new Diplomacy_RelationsAction(iCivID, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_IMPROVE_RELATIONS_TIME));
        this.iImprovingRelationsSize = this.improvingRelations.size();
        this.removeDamageRelations(iCivID);
        if (iCivID == Game.player.iCivID) {
            if (GameValues.notifications.RELATIONS_FROM_AS_NOTIFICATION) {
                Game.player.addNotification(new Notification(Notification.Notification_Type.RELATIONS_IMPROVING, Game.getCiv(iFromCivID).getCivName() + ": " + Game.lang.get("ImprovingRelations"), Images.relationsUp, Game_Calendar.TURN_ID, Notification.Notification_BG.GREEN, iFromCivID));
            }
            else {
                Game.player.addMessage(new PMessageImprovingRelations(iFromCivID, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_RELATIONS_DAYS));
            }
        }
        return true;
    }
    
    public final void removeImproveRelations(final int iCivID) {
        try {
            for (int i = this.iImprovingRelationsSize - 1; i >= 0; --i) {
                if (this.improvingRelations.get(i).iCivID == iCivID) {
                    this.improvingRelations.remove(i);
                    this.iImprovingRelationsSize = this.improvingRelations.size();
                    return;
                }
            }
        }
        catch (final Exception ex) {}
    }
    
    public final void clearImproveRelations() {
        this.improvingRelations.clear();
        this.iImprovingRelationsSize = 0;
    }
    
    public final void clearImproveRelations_AI(final int civID) {
        try {
            for (int i = this.iImprovingRelationsSize - 1; i >= 0; --i) {
                if (!Game.getCiv(civID).aiCivDiplomacy.isPreparingForAllianceWithCivID(this.improvingRelations.get(i).iCivID)) {
                    this.improvingRelations.remove(i);
                }
            }
        }
        catch (final Exception ex) {}
        this.iImprovingRelationsSize = this.improvingRelations.size();
    }
    
    public final boolean isImprovingRelations(final int iCivID) {
        try {
            for (int i = this.iImprovingRelationsSize - 1; i >= 0; --i) {
                if (this.improvingRelations.get(i).iCivID == iCivID) {
                    return true;
                }
            }
        }
        catch (final Exception ex) {}
        return false;
    }
    
    public final float getImprovingRelations_Perc(final int iCivID) {
        int i = this.iImprovingRelationsSize - 1;
        while (i >= 0) {
            if (this.improvingRelations.get(i).iCivID != iCivID) {
                --i;
            }
            else {
                if (this.improvingRelations.get(i).iTurnID - Game_Calendar.TURN_ID == GameValues.diplomacy.DIPLOMACY_IMPROVE_RELATIONS_TIME) {
                    return 99.9f;
                }
                return (this.improvingRelations.get(i).iTurnID - Game_Calendar.TURN_ID) / (float)GameValues.diplomacy.DIPLOMACY_IMPROVE_RELATIONS_TIME * 100.0f;
            }
        }
        return -1.0f;
    }
    
    public final void updateDamageRelations(final int iFromCivID) {
        for (int i = this.iDamagingRelationsSize - 1; i >= 0; --i) {
            try {
                if (this.damagingRelations.get(i).iTurnID <= Game_Calendar.TURN_ID) {
                    if (DiplomacyManager.isAtWar(iFromCivID, this.damagingRelations.get(i).iCivID) || Game.getCiv(this.damagingRelations.get(i).iCivID).getNumOfProvinces() <= 0) {
                        this.removeDamageRelations(this.damagingRelations.get(i).iCivID);
                    }
                    else {
                        final float fRelationsUpdate = DiplomacyManager.getRelationDamage(iFromCivID, this.damagingRelations.get(i).iCivID);
                        this.updateRelation(iFromCivID, this.damagingRelations.get(i).iCivID, fRelationsUpdate);
                        Game.getCiv(this.damagingRelations.get(i).iCivID).diplomacy.updateRelation(this.damagingRelations.get(i).iCivID, iFromCivID, fRelationsUpdate);
                        this.damagingRelations.get(i).iTurnID = Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_DAMAGE_RELATIONS_TIME;
                        if (Game.getCiv(this.damagingRelations.get(i).iCivID).diplomacy.getRelation(iFromCivID) < GameValues.diplomacy.DIPLOMACY_DAMAGE_RELATIONS_MAX) {
                            if (iFromCivID == Game.player.iCivID) {
                                Game.player.addNotification(new Notification(Notification.Notification_Type.RELATIONS_COMPLETED, Game.lang.get("DamagingRelationsCompleted"), Images.relationsDown, Game_Calendar.TURN_ID, Notification.Notification_BG.GREEN, this.damagingRelations.get(i).iCivID));
                            }
                            this.damagingRelations.remove(i);
                            this.iDamagingRelationsSize = this.damagingRelations.size();
                        }
                    }
                }
            }
            catch (final Exception ex) {}
        }
    }
    
    public final void addDamageRelations_Load(final int iCivID, final int turnID) {
        this.damagingRelations.add(new Diplomacy_RelationsAction(iCivID, turnID));
    }
    
    public final boolean addDamageRelations(final int iFromCivID, final int iCivID) {
        if (DiplomacyManager.isAtWar(iFromCivID, iCivID)) {
            return false;
        }
        if (Game.getCiv(iCivID).diplomacy.getRelation(iFromCivID) <= GameValues.diplomacy.DIPLOMACY_DAMAGE_RELATIONS_MAX) {
            return false;
        }
        for (int i = this.iDamagingRelationsSize - 1; i >= 0; --i) {
            if (this.damagingRelations.get(i).iCivID == iCivID) {
                return false;
            }
        }
        this.damagingRelations.add(new Diplomacy_RelationsAction(iCivID, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_DAMAGE_RELATIONS_TIME));
        this.iDamagingRelationsSize = this.damagingRelations.size();
        this.removeImproveRelations(iCivID);
        if (iCivID == Game.player.iCivID) {
            if (GameValues.notifications.RELATIONS_FROM_AS_NOTIFICATION) {
                Game.player.addNotification(new Notification(Notification.Notification_Type.RELATIONS_DAMAGING, Game.getCiv(iFromCivID).getCivName() + ": " + Game.lang.get("DamagingRelations"), Images.relationsDown, Game_Calendar.TURN_ID, Notification.Notification_BG.RED, iFromCivID));
            }
            else {
                Game.player.addMessage(new PMessageDamagingRelations(iFromCivID, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_RELATIONS_DAYS));
            }
        }
        return true;
    }
    
    public final void removeDamageRelations(final int iCivID) {
        for (int i = this.iDamagingRelationsSize - 1; i >= 0; --i) {
            if (this.damagingRelations.get(i).iCivID == iCivID) {
                this.damagingRelations.remove(i);
                this.iDamagingRelationsSize = this.damagingRelations.size();
                return;
            }
        }
    }
    
    public final void clearDamageRelations() {
        this.damagingRelations.clear();
        this.iDamagingRelationsSize = 0;
    }
    
    public final void clearDamageRelations_AI(final int civID) {
        try {
            for (int i = this.iDamagingRelationsSize - 1; i >= 0; --i) {
                if (!Game.getCiv(civID).aiCivDiplomacy.isPreparingForWarWithCivID(this.damagingRelations.get(i).iCivID)) {
                    this.damagingRelations.remove(i);
                }
            }
        }
        catch (final Exception ex) {}
        this.iDamagingRelationsSize = this.damagingRelations.size();
    }
    
    public final boolean isDamagingRelations(final int iCivID) {
        try {
            for (int i = this.iDamagingRelationsSize - 1; i >= 0; --i) {
                if (this.damagingRelations.get(i).iCivID == iCivID) {
                    return true;
                }
            }
        }
        catch (final Exception ex) {}
        return false;
    }
    
    public final float getDamagingRelations_Perc(final int iCivID) {
        int i = this.iDamagingRelationsSize - 1;
        while (i >= 0) {
            if (this.damagingRelations.get(i).iCivID != iCivID) {
                --i;
            }
            else {
                if (this.damagingRelations.get(i).iTurnID - Game_Calendar.TURN_ID == GameValues.diplomacy.DIPLOMACY_DAMAGE_RELATIONS_TIME) {
                    return 99.9f;
                }
                return (this.damagingRelations.get(i).iTurnID - Game_Calendar.TURN_ID) / (float)GameValues.diplomacy.DIPLOMACY_DAMAGE_RELATIONS_TIME * 100.0f;
            }
        }
        return -1.0f;
    }
    
    public final void addAtWar(final int iCivID) {
        for (int i = this.iAtWarSize - 1; i >= 0; --i) {
            if (this.atWar.get(i) == iCivID) {
                return;
            }
        }
        this.atWar.add(iCivID);
        this.iAtWarSize = this.atWar.size();
    }
    
    public final void removeAtWar(final int iCivID) {
        for (int i = this.iAtWarSize - 1; i >= 0; --i) {
            if (this.atWar.get(i) == iCivID) {
                this.atWar.remove(i);
                this.iAtWarSize = this.atWar.size();
                return;
            }
        }
    }
    
    public final void addWar(final String key, final int civID) {
        if (civID != Game.player.iCivID) {
            Game.getCiv(civID).updateMilitaryLevel(Math.max(GameValues.war.AI_AT_WAR_MIN_MILITARY_LEVEL, Game.getCiv(civID).getMilitaryLevel()));
            if (GameValues.notifications.NOTIFICATION_NEIGHBOR_OR_RIVAL_IS_AT_WAR && (Game.getCiv(Game.player.iCivID).civNeighbors.isNeighbor(civID) || Game.getCiv(Game.player.iCivID).diplomacy.isRival(civID))) {
                Game.player.addNotification(new Notification(Notification.Notification_Type.NEIGHBOR_OR_RIVAL_AT_WAR, Game.getCiv(civID).getCivName() + ": " + Game.lang.get("IaAtWar"), Images.war, Game_Calendar.TURN_ID, Notification.Notification_BG.NEUTRAL_BG, civID));
            }
        }
        for (int i = 0; i < this.iWarsSize; ++i) {
            if (this.lWars.get(i).equals(key)) {
                return;
            }
        }
        this.lWars.add(key);
        this.iWarsSize = this.lWars.size();
    }
    
    public final void removeWar(final String key, final int civID) {
        for (int i = 0; i < this.iWarsSize; ++i) {
            if (this.lWars.get(i).equals(key)) {
                this.lWars.remove(i);
                this.iWarsSize = this.lWars.size();
                if (this.iWarsSize == 0) {
                    Game.getCiv(civID).setWarPlayDefensiveUntilTurnID(0);
                }
                return;
            }
        }
    }
    
    public final String getWarKey(final int iCivA, final int iCivB) {
        try {
            for (int i = 0; i < this.iWarsSize; ++i) {
                if (WarManager.lWars.get(this.lWars.get(i)).isAggressor(iCivA) && WarManager.lWars.get(this.lWars.get(i)).isDefender(iCivB)) {
                    return this.lWars.get(i);
                }
                if (WarManager.lWars.get(this.lWars.get(i)).isAggressor(iCivB) && WarManager.lWars.get(this.lWars.get(i)).isDefender(iCivA)) {
                    return this.lWars.get(i);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return null;
    }
    
    public boolean isAtWar() {
        return this.iAtWarSize > 0;
    }
    
    public final void updateTruceAfterRemoveCiv(final int iCivID) {
        try {
            final ConcurrentHashMap<Integer, DiplomacyData> nList = new ConcurrentHashMap<Integer, DiplomacyData>();
            for (final Map.Entry<Integer, DiplomacyData> entry : this.truce.entrySet()) {
                if (entry.getKey() > iCivID) {
                    nList.put(entry.getKey() - 1, new DiplomacyData(entry.getValue().iCivID - 1, entry.getValue().iTurnID));
                }
                else {
                    if (entry.getKey() == iCivID) {
                        continue;
                    }
                    nList.put(entry.getKey(), entry.getValue());
                }
            }
            this.truce = nList;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void addTruce(final int iCivID, final int iTurnID) {
        if (!this.truce.containsKey(iCivID)) {
            this.truce.put(iCivID, new DiplomacyData(iCivID, iTurnID));
        }
    }
    
    public boolean haveTruce(final int iCivID) {
        return this.truce.containsKey(iCivID);
    }
    
    public final void updateTruces(final int civID) {
        try {
            if (this.truce.size() > 0) {
                final Iterator<Map.Entry<Integer, DiplomacyData>> it = this.truce.entrySet().iterator();
                while (it.hasNext()) {
                    final DiplomacyData tData = (DiplomacyData)it.next().getValue();
                    if (tData.iTurnID >= Game_Calendar.TURN_ID) {
                        continue;
                    }
                    if (tData.iCivID == Game.player.iCivID) {
                        Game.player.addMessage(new PMessageTruceExpired(civID, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
                    }
                    else if (civID == Game.player.iCivID) {
                        Game.player.addMessage(new PMessageTruceExpired(tData.iCivID, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
                    }
                    Game.getCiv(tData.iCivID).diplomacy.removeTruce(civID);
                    it.remove();
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void removeTruce(final int iCivID) {
        this.truce.remove(iCivID);
    }
    
    public final void updateDefensivePactAfterRemoveCiv(final int iCivID) {
        try {
            final ConcurrentHashMap<Integer, DiplomacyData> nList = new ConcurrentHashMap<Integer, DiplomacyData>();
            for (final Map.Entry<Integer, DiplomacyData> entry : this.defensivePact.entrySet()) {
                if (entry.getKey() > iCivID) {
                    nList.put(entry.getKey() - 1, new DiplomacyData(entry.getValue().iCivID - 1, entry.getValue().iTurnID));
                }
                else {
                    if (entry.getKey() == iCivID) {
                        continue;
                    }
                    nList.put(entry.getKey(), entry.getValue());
                }
            }
            this.defensivePact = nList;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void addDefensivePact(final int iCivID, final int iTurnID) {
        if (!this.defensivePact.containsKey(iCivID)) {
            this.defensivePact.put(iCivID, new DiplomacyData(iCivID, iTurnID));
        }
    }
    
    public final void removeDefensivePact(final int iCivID) {
        this.defensivePact.remove(iCivID);
    }
    
    public boolean haveDefensivePact(final int iCivID) {
        return this.defensivePact.containsKey(iCivID);
    }
    
    public final void updateDefensivePact(final int civID) {
        try {
            if (this.defensivePact.size() > 0) {
                final Iterator<Map.Entry<Integer, DiplomacyData>> it = this.defensivePact.entrySet().iterator();
                while (it.hasNext()) {
                    final DiplomacyData tData = (DiplomacyData)it.next().getValue();
                    if (tData.iTurnID >= Game_Calendar.TURN_ID) {
                        continue;
                    }
                    if (tData.iCivID == Game.player.iCivID) {
                        Game.player.addMessage(new PMessageDefensivePactExpired(civID, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
                    }
                    else if (civID == Game.player.iCivID) {
                        Game.player.addMessage(new PMessageDefensivePactExpired(tData.iCivID, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
                    }
                    Game.getCiv(tData.iCivID).diplomacy.removeDefensivePact(civID);
                    it.remove();
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateNonAggressionPactAfterRemoveCiv(final int iCivID) {
        try {
            final ConcurrentHashMap<Integer, DiplomacyData> nList = new ConcurrentHashMap<Integer, DiplomacyData>();
            for (final Map.Entry<Integer, DiplomacyData> entry : this.nonAggressionPact.entrySet()) {
                if (entry.getKey() > iCivID) {
                    nList.put(entry.getKey() - 1, new DiplomacyData(entry.getValue().iCivID - 1, entry.getValue().iTurnID));
                }
                else {
                    if (entry.getKey() == iCivID) {
                        continue;
                    }
                    nList.put(entry.getKey(), entry.getValue());
                }
            }
            this.nonAggressionPact = nList;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void addNonAggressionPact(final int iCivID, final int iTurnID) {
        if (!this.nonAggressionPact.containsKey(iCivID)) {
            this.nonAggressionPact.put(iCivID, new DiplomacyData(iCivID, iTurnID));
        }
    }
    
    public final void removeNonAggressionPact(final int iCivID) {
        this.nonAggressionPact.remove(iCivID);
    }
    
    public boolean haveNonAggressionPact(final int iCivID) {
        return this.nonAggressionPact.containsKey(iCivID);
    }
    
    public final void updateNonAggressionPact(final int civID) {
        try {
            if (this.nonAggressionPact.size() > 0) {
                final Iterator<Map.Entry<Integer, DiplomacyData>> it = this.nonAggressionPact.entrySet().iterator();
                while (it.hasNext()) {
                    final DiplomacyData tData = (DiplomacyData)it.next().getValue();
                    if (tData.iTurnID >= Game_Calendar.TURN_ID) {
                        continue;
                    }
                    if (tData.iCivID == Game.player.iCivID) {
                        Game.player.addMessage(new PMessageNonAggressionPactExpired(civID, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
                    }
                    else if (civID == Game.player.iCivID) {
                        Game.player.addMessage(new PMessageNonAggressionPactExpired(tData.iCivID, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
                    }
                    Game.getCiv(tData.iCivID).diplomacy.removeNonAggressionPact(civID);
                    it.remove();
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateGuaranteeAfterRemoveCiv(final int iCivID) {
        try {
            final ConcurrentHashMap<Integer, DiplomacyData> nList = new ConcurrentHashMap<Integer, DiplomacyData>();
            for (final Map.Entry<Integer, DiplomacyData> entry : this.guarantee.entrySet()) {
                if (entry.getKey() > iCivID) {
                    nList.put(entry.getKey() - 1, new DiplomacyData(entry.getValue().iCivID - 1, entry.getValue().iTurnID));
                }
                else {
                    if (entry.getKey() == iCivID) {
                        continue;
                    }
                    nList.put(entry.getKey(), entry.getValue());
                }
            }
            this.guarantee = nList;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void addGuarantee(final int iCivID, final int iTurnID) {
        if (!this.guarantee.containsKey(iCivID)) {
            this.guarantee.put(iCivID, new DiplomacyData(iCivID, iTurnID));
        }
    }
    
    public final void removeGuarantee(final int iCivID) {
        this.guarantee.remove(iCivID);
    }
    
    public boolean haveGuarantee(final int iCivID) {
        return this.guarantee.containsKey(iCivID);
    }
    
    public final void updateGuarantee(final int civID) {
        try {
            if (this.guarantee.size() > 0) {
                final Iterator<Map.Entry<Integer, DiplomacyData>> it = this.guarantee.entrySet().iterator();
                while (it.hasNext()) {
                    final DiplomacyData tData = (DiplomacyData)it.next().getValue();
                    if (tData.iTurnID >= Game_Calendar.TURN_ID) {
                        continue;
                    }
                    if (tData.iCivID == Game.player.iCivID) {
                        Game.player.addMessage(new PMessageGuaranteeExpired_OurInd(civID, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
                    }
                    else if (civID == Game.player.iCivID) {
                        Game.player.addMessage(new PMessageGuaranteeExpired_We(tData.iCivID, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
                    }
                    Game.getCiv(tData.iCivID).diplomacy.removeGuaranteeByCivID(civID);
                    it.remove();
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateGuaranteeByCivIDAfterRemoveCiv(final int iCivID) {
        try {
            final ConcurrentHashMap<Integer, DiplomacyData> nList = new ConcurrentHashMap<Integer, DiplomacyData>();
            for (final Map.Entry<Integer, DiplomacyData> entry : this.guaranteeByCivID.entrySet()) {
                if (entry.getKey() > iCivID) {
                    nList.put(entry.getKey() - 1, new DiplomacyData(entry.getValue().iCivID - 1, entry.getValue().iTurnID));
                }
                else {
                    if (entry.getKey() == iCivID) {
                        continue;
                    }
                    nList.put(entry.getKey(), entry.getValue());
                }
            }
            this.guaranteeByCivID = nList;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void addGuaranteeByCivID(final int iCivID, final int iTurnID) {
        if (!this.guaranteeByCivID.containsKey(iCivID)) {
            this.guaranteeByCivID.put(iCivID, new DiplomacyData(iCivID, iTurnID));
        }
    }
    
    public final void removeGuaranteeByCivID(final int iCivID) {
        this.guaranteeByCivID.remove(iCivID);
    }
    
    public boolean haveGuaranteeByCivID(final int iCivID) {
        return this.guaranteeByCivID.containsKey(iCivID);
    }
    
    public final void updateGuaranteeByCivID() {
    }
    
    public final void updateAllianceAfterRemoveCiv(final int iCivID) {
        try {
            final ConcurrentHashMap<Integer, DiplomacyData> nList = new ConcurrentHashMap<Integer, DiplomacyData>();
            for (final Map.Entry<Integer, DiplomacyData> entry : this.alliance.entrySet()) {
                if (entry.getKey() > iCivID) {
                    nList.put(entry.getKey() - 1, new DiplomacyData(entry.getValue().iCivID - 1, entry.getValue().iTurnID));
                }
                else {
                    if (entry.getKey() == iCivID) {
                        continue;
                    }
                    nList.put(entry.getKey(), entry.getValue());
                }
            }
            this.alliance = nList;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void addAlliance(final int iCivID, final int iTurnID) {
        if (!this.alliance.containsKey(iCivID)) {
            this.alliance.put(iCivID, new DiplomacyData(iCivID, iTurnID));
        }
    }
    
    public final void removeAlliance(final int iCivID) {
        this.alliance.remove(iCivID);
    }
    
    public boolean haveAlliance(final int iCivID) {
        return this.alliance.containsKey(iCivID);
    }
    
    public final void updateAlliance(final int civID) {
        try {
            if (this.alliance.size() > 0) {
                final Iterator<Map.Entry<Integer, DiplomacyData>> it = this.alliance.entrySet().iterator();
                while (it.hasNext()) {
                    final DiplomacyData tData = (DiplomacyData)it.next().getValue();
                    if (tData.iTurnID >= Game_Calendar.TURN_ID && Game.getCiv(tData.iCivID).getNumOfProvinces() > 0 && Game.getCiv(civID).getNumOfProvinces() > 0) {
                        continue;
                    }
                    int updateFog = -1;
                    if (tData.iCivID == Game.player.iCivID) {
                        Game.player.addMessage(new PMessageAllianceExpired(civID, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
                        Game.player.addNotification(new Notification(Notification.Notification_Type.ALLIANCE_EXPIRED, Game.getCiv(civID).getCivName() + ": " + Game.lang.get("AllianceExpired"), Images.alliance, Game_Calendar.TURN_ID, Notification.Notification_BG.RED, civID));
                        updateFog = civID;
                        Game.menuManager.rebuildInGame_Info(Game.lang.get("AllianceExpired"), Game.getCiv(civID).getCivName() + " - " + Game.getCiv(Game.player.iCivID).getCivName());
                        InGame_Info.imgID = Images.infoDiplomacy;
                    }
                    else if (civID == Game.player.iCivID) {
                        Game.player.addMessage(new PMessageAllianceExpired(tData.iCivID, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
                        Game.player.addNotification(new Notification(Notification.Notification_Type.ALLIANCE_EXPIRED, Game.getCiv(tData.iCivID).getCivName() + ": " + Game.lang.get("AllianceExpired"), Images.alliance, Game_Calendar.TURN_ID, Notification.Notification_BG.RED, tData.iCivID));
                        updateFog = tData.iCivID;
                        Game.menuManager.rebuildInGame_Info(Game.lang.get("AllianceExpired"), Game.getCiv(tData.iCivID).getCivName() + " - " + Game.getCiv(Game.player.iCivID).getCivName());
                        InGame_Info.imgID = Images.infoDiplomacy;
                    }
                    Game.getCiv(tData.iCivID).diplomacy.removeAlliance(civID);
                    it.remove();
                    if (updateFog <= 0) {
                        continue;
                    }
                    Game.getCiv(updateFog).updateArmyImgID();
                    Game.player.fog.updateFogOfWar_Civ(updateFog);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateAlliance_ConqueredProvinces(final int civID) {
        try {
            if (this.alliance.size() > 0) {
                final Iterator<Map.Entry<Integer, DiplomacyData>> it = this.alliance.entrySet().iterator();
                while (it.hasNext()) {
                    final DiplomacyData tData = (DiplomacyData)it.next().getValue();
                    if (Game.getCiv(tData.iCivID).getNumOfProvinces() > 0 && Game.getCiv(civID).getNumOfProvinces() > 0) {
                        continue;
                    }
                    int updateFog = -1;
                    if (tData.iCivID == Game.player.iCivID) {
                        Game.player.addMessage(new PMessageAllianceExpired(civID, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
                        Game.player.addNotification(new Notification(Notification.Notification_Type.ALLIANCE_EXPIRED, Game.getCiv(civID).getCivName() + ": " + Game.lang.get("AllianceExpired"), Images.alliance, Game_Calendar.TURN_ID, Notification.Notification_BG.RED, civID));
                        updateFog = civID;
                        Game.menuManager.rebuildInGame_Info(Game.lang.get("AllianceExpired"), Game.getCiv(civID).getCivName() + " - " + Game.getCiv(Game.player.iCivID).getCivName());
                        InGame_Info.imgID = Images.infoDiplomacy;
                    }
                    else if (civID == Game.player.iCivID) {
                        Game.player.addMessage(new PMessageAllianceExpired(tData.iCivID, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
                        Game.player.addNotification(new Notification(Notification.Notification_Type.ALLIANCE_EXPIRED, Game.getCiv(tData.iCivID).getCivName() + ": " + Game.lang.get("AllianceExpired"), Images.alliance, Game_Calendar.TURN_ID, Notification.Notification_BG.RED, tData.iCivID));
                        updateFog = tData.iCivID;
                        Game.menuManager.rebuildInGame_Info(Game.lang.get("AllianceExpired"), Game.getCiv(tData.iCivID).getCivName() + " - " + Game.getCiv(Game.player.iCivID).getCivName());
                        InGame_Info.imgID = Images.infoDiplomacy;
                    }
                    Game.getCiv(tData.iCivID).diplomacy.removeAlliance(civID);
                    it.remove();
                    if (updateFog <= 0) {
                        continue;
                    }
                    Game.getCiv(updateFog).updateArmyImgID();
                    Game.player.fog.updateFogOfWar_Civ(updateFog);
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void updateMilitaryAccessAfterRemoveCiv(final int iCivID) {
        try {
            final ConcurrentHashMap<Integer, DiplomacyData> nList = new ConcurrentHashMap<Integer, DiplomacyData>();
            for (final Map.Entry<Integer, DiplomacyData> entry : this.militaryAccess.entrySet()) {
                if (entry.getKey() > iCivID) {
                    nList.put(entry.getKey() - 1, new DiplomacyData(entry.getValue().iCivID - 1, entry.getValue().iTurnID));
                }
                else {
                    if (entry.getKey() == iCivID) {
                        continue;
                    }
                    nList.put(entry.getKey(), entry.getValue());
                }
            }
            this.militaryAccess = nList;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void addMilitaryAccess(final int iCivID, final int iTurnID) {
        if (!this.militaryAccess.containsKey(iCivID)) {
            this.militaryAccess.put(iCivID, new DiplomacyData(iCivID, iTurnID));
        }
    }
    
    public final void removeMilitaryAccess(final int iCivID) {
        this.militaryAccess.remove(iCivID);
    }
    
    public boolean haveMilitaryAccess(final int iCivID) {
        return this.militaryAccess.containsKey(iCivID);
    }
    
    public final void updateMilitaryAccess(final int civID) {
        try {
            if (this.militaryAccess.size() > 0) {
                final Iterator<Map.Entry<Integer, DiplomacyData>> it = this.militaryAccess.entrySet().iterator();
                while (it.hasNext()) {
                    final DiplomacyData tData = (DiplomacyData)it.next().getValue();
                    if (tData.iTurnID >= Game_Calendar.TURN_ID) {
                        continue;
                    }
                    if (civID == Game.player.iCivID) {
                        Game.player.addMessage(new PMessageMilitaryAccessExpired(tData.iCivID, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
                    }
                    Game.getCiv(tData.iCivID).diplomacy.removeMilitaryAccess(civID);
                    it.remove();
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void addRival_load(final int rivalCivID, final int endRivalryTurnID) {
        this.rivals.put(rivalCivID, new DiplomacyData(rivalCivID, endRivalryTurnID));
    }
    
    public final boolean addRival(final int byCivID, final int rivalCivID) {
        return this.addRival(byCivID, rivalCivID, Game_Calendar.TURN_ID + 365 * GameValues.rivals.END_OF_RIVALRY_AFTER_YEARS + Game.oR.nextInt(GameValues.rivals.END_OF_RIVALRY_AFTER_EXTRA_DAYS_RANDOM));
    }
    
    public final boolean addRival(final int byCivID, final int rivalCivID, final int endRivalryTurnID) {
        if (this.rivals.containsKey(rivalCivID)) {
            return false;
        }
        if (this.rivals.size() >= GameValues.rivals.RIVALS_LIMIT) {
            return false;
        }
        this.rivals.put(rivalCivID, new DiplomacyData(rivalCivID, endRivalryTurnID));
        if (!DiplomacyManager.isAtWar(byCivID, rivalCivID)) {
            this.updateRelation(byCivID, rivalCivID, (float)GameValues.rivals.RIVALS_OPINION_CHANGE);
            Game.getCiv(rivalCivID).diplomacy.updateRelation(rivalCivID, byCivID, (float)GameValues.rivals.RIVALS_OPINION_CHANGE);
        }
        Game.getCiv(rivalCivID).diplomacy.removeImproveRelations(byCivID);
        Game.getCiv(byCivID).diplomacy.removeImproveRelations(rivalCivID);
        Game.gameThread.addCivUpdateLegacyPerMonth(byCivID);
        Game.gameThreadTurns.addCivUpdateMaxManpower(byCivID);
        if (rivalCivID == Game.player.iCivID) {
            Game.player.addMessage(new PMessageRivals(byCivID, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
        }
        return true;
    }
    
    public final void removeRival(final int iCivID) {
        if (this.rivals.containsValue(iCivID)) {
            this.rivals.remove(iCivID);
        }
    }
    
    public boolean isRival(final int iCivID) {
        return this.rivals.containsKey(iCivID);
    }
    
    public final void updateRivals(final int civID) {
        try {
            if (this.rivals.size() > 0) {
                final Iterator<Map.Entry<Integer, DiplomacyData>> it = this.rivals.entrySet().iterator();
                while (it.hasNext()) {
                    final DiplomacyData tData = (DiplomacyData)it.next().getValue();
                    if (Game.getCiv(tData.iCivID).getNumOfProvinces() > 0 && Game_Calendar.TURN_ID < tData.iTurnID) {
                        continue;
                    }
                    if (civID == Game.player.iCivID) {
                        Game.player.addMessage(new PMessageRivalryExpired(tData.iCivID, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
                    }
                    else {
                        Game.gameThread.addAI_SimpleTask(new Game.SimpleTask("chooseRivals" + civID, civID) {
                            @Override
                            public void update() {
                                AI_Rivals.chooseRivals(this.id);
                            }
                        });
                    }
                    Game.gameThread.addCivUpdateLegacyPerMonth(civID);
                    Game.gameThreadTurns.addCivUpdateMaxManpower(civID);
                    it.remove();
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final boolean haveTheSameRival(final int civID, final int withCivID) {
        try {
            if (this.rivals.size() > 0) {
                final Iterator<Map.Entry<Integer, DiplomacyData>> it = this.rivals.entrySet().iterator();
                while (it.hasNext()) {
                    final DiplomacyData tData = (DiplomacyData)it.next().getValue();
                    if (!Game.getCiv(withCivID).diplomacy.rivals.containsKey(tData.iCivID)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return false;
    }
    
    public final int getRivalsManpower(final int civID) {
        int out = 0;
        try {
            if (this.rivals.size() > 0) {
                final Iterator<Map.Entry<Integer, DiplomacyData>> it = this.rivals.entrySet().iterator();
                while (it.hasNext()) {
                    final DiplomacyData tData = (DiplomacyData)it.next().getValue();
                    if (Game.getCiv(tData.iCivID).getNumOfProvinces() <= 0) {
                        it.remove();
                    }
                    else {
                        out += RivalsManager.getManpower(civID, tData.iCivID);
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return out;
    }
    
    public final float getRivalsLegacy(final int civID) {
        float out = 0.0f;
        try {
            if (this.rivals.size() > 0) {
                final Iterator<Map.Entry<Integer, DiplomacyData>> it = this.rivals.entrySet().iterator();
                while (it.hasNext()) {
                    final DiplomacyData tData = (DiplomacyData)it.next().getValue();
                    if (Game.getCiv(tData.iCivID).getNumOfProvinces() <= 0) {
                        it.remove();
                    }
                    else {
                        out += RivalsManager.getLegacy(civID, tData.iCivID);
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return out;
    }
    
    public class DiplomacyData
    {
        public int iCivID;
        public int iTurnID;
        
        public DiplomacyData(final int iCivID, final int iTurnID) {
            this.iCivID = iCivID;
            this.iTurnID = iTurnID;
        }
    }
}
