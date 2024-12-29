// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski.Player.MessageTypes;

import java.util.List;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoc.kingdoms.lukasz.jakowski.GameValues;
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
import aoc.kingdoms.lukasz.menusInGame.Info.InGame_Info;
import aoc.kingdoms.lukasz.jakowski.Game;

public class PMessageGoldenAgeMilitary extends PMessage
{
    public PMessageGoldenAgeMilitary(final int iFromCivID, final int iExpiresTurnID) {
        super(iFromCivID, iExpiresTurnID, 0);
    }
    
    @Override
    public void actionClick() {
        this.onRefuse();
        Game.player.removeMessage(this.key);
    }
    
    @Override
    public void onAccept() {
        this.onRefuse();
    }
    
    @Override
    public void onRefuse() {
        InGame_Info.iCivID = Game.player.iCivID;
        InGame_Info.iCivID2 = Game.player.iCivID;
        Game.menuManager.rebuildInGame_Info(Game.lang.get("OurCivilizationIsInAGoldenAgeOf"), Game.lang.get("GAMilitary"));
        InGame_Info.imgID = Images.infoCrown;
        ProvinceDraw.addProvinceDot(Game.getCiv(Game.player.iCivID).getCapitalProvinceID(), Colors.HOVER_GOLD);
    }
    
    @Override
    public MenuElement_Hover buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("OurCivilizationIsInAGoldenAgeOf") + ": " + Game.lang.get("GAMilitary"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.goldenGreen, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsAttack") + ": ", "+" + GameValues.goldenAge.GA_MILITARY_UNITS_ATTACK[Math.min(Game.getCiv(Game.player.iCivID).goldenAge.getGoldenAgeMilitary() - 1, GameValues.iGoldenAge_MilitarySize - 1)], Images.attack, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_POSITIVE));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsDefense") + ": ", "+" + GameValues.goldenAge.GA_MILITARY_UNITS_DEFENSE[Math.min(Game.getCiv(Game.player.iCivID).goldenAge.getGoldenAgeMilitary() - 1, GameValues.iGoldenAge_MilitarySize - 1)], Images.defense, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_POSITIVE));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", "+" + CFG.getPrecision2(100.0f * GameValues.goldenAge.GA_MILITARY_MAX_MANPOWER_PERC[Math.min(Game.getCiv(Game.player.iCivID).goldenAge.getGoldenAgeMilitary() - 1, GameValues.iGoldenAge_MilitarySize - 1)], 10) + "%", Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_POSITIVE));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Duration") + ": ", "" + Game.lang.get("XDays", GameValues.goldenAge.GA_MILITARY_DURATION_DAYS[Math.min(Game.getCiv(Game.player.iCivID).goldenAge.getGoldenAgeMilitary() - 1, GameValues.iGoldenAge_MilitarySize - 1)]), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
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
        return Images.goldenGreen;
    }
}
