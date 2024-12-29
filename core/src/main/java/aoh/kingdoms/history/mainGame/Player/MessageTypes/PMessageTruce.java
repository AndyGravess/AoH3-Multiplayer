// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.Player.MessageTypes;

import java.util.List;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.map.province.ProvinceDraw;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.menusInGame.Info.InGame_Info;
import aoc.kingdoms.lukasz.map.PeaceTreaty;

public class PMessageTruce extends PMessage
{
    public PeaceTreaty peaceTreaty;
    
    public PMessageTruce(final int iFromCivID, final int iExpiresTurnID, final PeaceTreaty peaceTreaty) {
        super(iFromCivID, iExpiresTurnID, 0);
        this.peaceTreaty = peaceTreaty;
    }
    
    @Override
    public void actionClick() {
        InGame_Info.iCivID = this.fromCivID;
        InGame_Info.iCivID2 = Game.player.iCivID;
        Game.menuManager.rebuildInGame_Info(Game.lang.get("PeaceTreaty"), Game.getCiv(this.fromCivID).getCivName());
        InGame_Info.imgID = Images.infoDiplomacy;
        ProvinceDraw.addProvinceDot(Game.getCiv(Game.player.iCivID).getCapitalProvinceID(), Colors.HOVER_GOLD);
        Game.player.removeMessage(this.key);
    }
    
    @Override
    public void onAccept() {
    }
    
    @Override
    public void onRefuse() {
    }
    
    @Override
    public MenuElement_Hover buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("PeaceTreaty") + ": " + Game.getCiv(this.fromCivID).getCivName(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.truce, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (this.peaceTreaty.demandVassalization) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DemandVassalization"), "", Images.vassal, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.peaceTreaty.lProvinces.size() > 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Provinces") + ": ", "" + CFG.getNumberWithSpaces("" + this.peaceTreaty.lProvinces.size()), Images.provinces, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.peaceTreaty.demandReligionConversion) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DemandReligionConversion"), "", Images.religion, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.peaceTreaty.demandGovernmentChange) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GovernmentChanger"), "", Images.government, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.peaceTreaty.demandWarReparations) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("WarReparations"), "", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.peaceTreaty.demandMilitaryAccess) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DemandMilitaryAccess"), "", Images.militaryAccess, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.peaceTreaty.demandHumiliate) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Humiliate"), "", Images.insult, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.peaceTreaty.demandGold > 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Gold") + ": ", "" + CFG.getPrecision2(this.peaceTreaty.getGold_PerDemand(), 100), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (this.peaceTreaty.lSubjectTransfer.size() > 0) {
            for (int i = 0; i < this.peaceTreaty.lSubjectTransfer.size(); ++i) {
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DemandSubjectTransfer") + ": ", "" + Game.getCiv(this.peaceTreaty.lSubjectTransfer.get(i)).getCivName(), Images.vassal, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
        }
        if (this.peaceTreaty.lLiberateCiv.size() > 0) {
            for (int i = 0; i < this.peaceTreaty.lLiberateCiv.size(); ++i) {
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("LiberateCivilization") + ": ", "" + Game.getCiv(this.peaceTreaty.lLiberateCiv.get(i)).getCivName(), Images.council, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
        }
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Message"), "", Images.message, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Expires") + ": ", "" + Game_Calendar.getDate_ByTurnID(this.expiresTurnID), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        return new MenuElement_Hover(nElements);
    }
    
    @Override
    public int getImageID() {
        return Images.truceExpired;
    }
}
