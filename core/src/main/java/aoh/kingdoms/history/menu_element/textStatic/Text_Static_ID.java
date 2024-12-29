// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;

public class Text_Static_ID extends Text_Static
{
    public int iCivID;
    
    public Text_Static_ID(final int iCivID, final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int iWidth, final int iHeight) {
        super(sText, fontID, iTextPositionX, iPosX, iPosY, iWidth, iHeight);
        this.iCivID = iCivID;
    }
    
    @Override
    public int getCurrent() {
        return this.iCivID;
    }
    
    @Override
    public void buildElementHover() {
        try {
            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
            nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.iCivID, Game.lang.get("Provinces") + ": " + CFG.getNumberWithSpaces("" + Game.getCiv(this.iCivID).getNumOfProvinces())));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", CFG.getNumberWithSpaces("" + Game.getCiv(this.iCivID).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Economy") + ": ", CFG.getPrecision2(Game.getCiv(this.iCivID).getEconomyTotal(), 10), Game_Calendar.IMG_ECONOMY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            this.menuElementHover = new MenuElement_Hover(nElements);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public void actionElement() {
        try {
            if (Game.getCiv(this.iCivID).getCapitalProvinceID() >= 0 && Game.getProvince(Game.getCiv(this.iCivID).getCapitalProvinceID()).getCivID() == this.iCivID) {
                Game.mapCoords.centerToProvinceID(Game.getCiv(this.iCivID).getCapitalProvinceID());
                Game.setActiveProvinceID(Game.getCiv(this.iCivID).getCapitalProvinceID());
                ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
}
