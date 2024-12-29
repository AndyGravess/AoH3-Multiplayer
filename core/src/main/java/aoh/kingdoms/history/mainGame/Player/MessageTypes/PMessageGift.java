// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.Player.MessageTypes;

import java.util.List;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.map.province.ProvinceDraw;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menusInGame.DiplomacyMessage.InGame_MessageGift;
import aoc.kingdoms.lukasz.menu.MenuManager;
import aoc.kingdoms.lukasz.jakowski.Game;

public class PMessageGift extends PMessage
{
    public PMessageGift(final int iFromCivID, final int iExpiresTurnID, final int iGold) {
        super(iFromCivID, iExpiresTurnID, iGold);
    }
    
    @Override
    public void actionClick() {
        if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 28 && InGame_MessageGift.key.equals(this.key)) {
            Game.menuManager.setVisibleInGame_PopUp(false);
        }
        else {
            Game.menuManager.rebuildInGame_MessageGift(this.key);
        }
    }
    
    @Override
    public void onAccept() {
        ProvinceDraw.addDiplomacyLines(Game.getCiv(this.fromCivID).getCapitalProvinceID(), Game.getCiv(Game.player.iCivID).getCapitalProvinceID(), Colors.HOVER_POSITIVE);
    }
    
    @Override
    public void onRefuse() {
        final Civilization civ = Game.getCiv(Game.player.iCivID);
        civ.fGold -= this.iValue1;
        final Civilization civ2 = Game.getCiv(this.fromCivID);
        civ2.fGold += this.iValue1;
        final int iClicks = (int)(Math.ceil(this.iValue1 / GameValues.diplomacy.DIPLOMACY_GIFT_GOLD_PER_CLICK) * GameValues.diplomacy.DIPLOMACY_GIFT_REFUSE_MULTIPLE);
        Game.getCiv(this.fromCivID).diplomacy.setRelation(this.fromCivID, Game.player.iCivID, Game.getCiv(this.fromCivID).diplomacy.getRelation(Game.player.iCivID) - iClicks * GameValues.diplomacy.DIPLOMACY_GIFT_RELATION_PER_CLICK);
        Game.getCiv(Game.player.iCivID).diplomacy.setRelation(Game.player.iCivID, this.fromCivID, Game.getCiv(Game.player.iCivID).diplomacy.getRelation(this.fromCivID) - iClicks * GameValues.diplomacy.DIPLOMACY_GIFT_RELATION_PER_CLICK);
        ProvinceDraw.addDiplomacyLines(Game.getCiv(Game.player.iCivID).getCapitalProvinceID(), Game.getCiv(this.fromCivID).getCapitalProvinceID(), Colors.HOVER_NEGATIVE);
    }
    
    @Override
    public MenuElement_Hover buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("AGiftFromCivA", Game.getCiv(this.fromCivID).getCivName()), CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.gift, CFG.PADDING, 0));
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
        return Images.gift;
    }
}
