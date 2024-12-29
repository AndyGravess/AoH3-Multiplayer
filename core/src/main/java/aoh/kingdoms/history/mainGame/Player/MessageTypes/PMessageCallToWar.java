// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.Player.MessageTypes;

import java.util.List;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonusFlag;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.map.province.ProvinceBorderManager;
import aoc.kingdoms.lukasz.map.war.WarCivilization;
import aoc.kingdoms.lukasz.map.war.WarManager;
import aoc.kingdoms.lukasz.map.war.War;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.map.province.ProvinceDraw;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.menusInGame.DiplomacyMessage.InGame_MessageCallToWar;
import aoc.kingdoms.lukasz.menu.MenuManager;
import aoc.kingdoms.lukasz.jakowski.Game;

public class PMessageCallToWar extends PMessage
{
    public PMessageCallToWar(final int iFromCivID, final String warKey, final int iExpiresTurnID) {
        super(iFromCivID, iExpiresTurnID, 0);
        this.key = warKey;
    }
    
    @Override
    public void actionClick() {
        if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 46 && InGame_MessageCallToWar.key.equals(this.key)) {
            Game.menuManager.setVisibleInGame_PopUp(false);
        }
        else {
            Game.menuManager.rebuildInGame_MessageCallToWar(this);
        }
    }
    
    @Override
    public void onAccept() {
        final Civilization civ = Game.getCiv(Game.player.iCivID);
        civ.fLegacy += GameValues.diplomacy.CALL_TO_ARMS_REFUSE_LEGACY;
        Game.getCiv(Game.player.iCivID).diplomacy.removeAlliance(this.fromCivID);
        Game.getCiv(Game.player.iCivID).diplomacy.removeDefensivePact(this.fromCivID);
        ProvinceDraw.addDiplomacyLines(Game.getCiv(Game.player.iCivID).getCapitalProvinceID(), Game.getCiv(this.fromCivID).getCapitalProvinceID(), Colors.HOVER_NEGATIVE);
    }
    
    @Override
    public void onRefuse() {
        final War war = WarManager.lWars.get(this.key);
        if (war != null) {
            if (war.isAggressor(this.fromCivID)) {
                war.addAggressor(Game.player.iCivID);
            }
            else if (war.isDefender(this.fromCivID)) {
                war.addDefender(Game.player.iCivID);
            }
            try {
                for (int i = 0; i < war.lDefenders.size(); ++i) {
                    Game.getCiv(war.lDefenders.get(i).iCivID).updateArmyImgID();
                }
                for (int i = 0; i < war.lAggressors.size(); ++i) {
                    Game.getCiv(war.lAggressors.get(i).iCivID).updateArmyImgID();
                }
            }
            catch (final Exception ex) {}
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
        ProvinceDraw.addDiplomacyLines(Game.getCiv(Game.player.iCivID).getCapitalProvinceID(), Game.getCiv(this.fromCivID).getCapitalProvinceID(), DiplomacyManager.COLOR_ALLIANCE);
    }
    
    @Override
    public MenuElement_Hover buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("CallToArms"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.war, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag("", Game.getCiv(this.fromCivID).getCivName(), this.fromCivID, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
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
        return Images.alliance;
    }
}
