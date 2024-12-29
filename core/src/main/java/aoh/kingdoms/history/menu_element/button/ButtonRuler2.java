// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.map.RulersManager;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;

public class ButtonRuler2 extends Button
{
    public int iCivID;
    
    public ButtonRuler2(final int iCivID, final int iPosX, final int iPosY) {
        this.iCivID = iCivID;
        this.init("", CFG.FONT_REGULAR_SMALL, this.iTextPositionX, iPosX, iPosY, getButtonWidth(), getButtonHeight(), true, true, false, false);
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        if (this.getIsHovered() || isActive) {
            Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.rulerFrame).getWidth(), ImageManager.getImage(Images.rulerFrame).getHeight());
        }
        try {
            if (RulersManager.rulerIMG != null) {
                RulersManager.rulerIMG.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.rulerFrame).getWidth(), ImageManager.getImage(Images.rulerFrame).getHeight());
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        ImageManager.getImage(Images.rulerFrame).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
    }
    
    public static int getButtonWidth() {
        return ImageManager.getImage(Images.rulerFrame).getWidth();
    }
    
    public static int getButtonHeight() {
        return ImageManager.getImage(Images.rulerFrame).getHeight();
    }
    
    @Override
    public void buildElementHover() {
        this.menuElementHover = getHoverRuler(this.iCivID, true);
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        return Colors.getColorTopStatsHover(isActive, this.getIsHovered());
    }
    
    @Override
    public void actionElementPPM() {
        if (Game.getCiv(this.iCivID).getCapitalProvinceID() >= 0) {
            Game.mapCoords.centerToProvinceID(Game.getCiv(this.iCivID).getCapitalProvinceID());
        }
    }
    
    public static final MenuElement_Hover getHoverRuler(final int iCivID, final boolean openDiplomacyView) {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(iCivID, Game.ideologiesManager.getIdeology(Game.getCiv(iCivID).getIdeologyID()).RulerTitle + ": " + Game.getCiv(iCivID).ruler.Name));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Born") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(iCivID).ruler.BornDay + " " + Game_Calendar.getMonthName(Game.getCiv(iCivID).ruler.BornMonth) + " " + Game.getCiv(iCivID).ruler.BornYear, CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, CFG.PADDING));
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("XYearsOld", Math.min(99, Game_Calendar.currentYear - Game.getCiv(iCivID).ruler.BornYear)), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (Game.getCiv(iCivID).ruler.rulerBonuses.MonthlyIncome != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyIncome") + ": ", ((Game.getCiv(iCivID).ruler.rulerBonuses.MonthlyIncome > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).ruler.rulerBonuses.MonthlyIncome, 100), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (Game.getCiv(iCivID).ruler.rulerBonuses.TaxEfficiency != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("TaxEfficiency") + ": ", ((Game.getCiv(iCivID).ruler.rulerBonuses.TaxEfficiency > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).ruler.rulerBonuses.TaxEfficiency, 100) + "%", Images.tax, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (Game.getCiv(iCivID).ruler.rulerBonuses.ProductionEfficiency != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProductionEfficiency") + ": ", ((Game.getCiv(iCivID).ruler.rulerBonuses.ProductionEfficiency > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).ruler.rulerBonuses.ProductionEfficiency, 100) + "%", Images.goods, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (Game.getCiv(iCivID).ruler.rulerBonuses.ProvinceMaintenance != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProvinceMaintenance") + ": ", ((Game.getCiv(iCivID).ruler.rulerBonuses.ProvinceMaintenance > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).ruler.rulerBonuses.ProvinceMaintenance, 100) + "%", Images.goods, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (Game.getCiv(iCivID).ruler.rulerBonuses.MaxManpower != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", ((Game.getCiv(iCivID).ruler.rulerBonuses.MaxManpower > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).ruler.rulerBonuses.MaxManpower, 1), Game_Calendar.IMG_MANPOWER_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (Game.getCiv(iCivID).ruler.rulerBonuses.IncreaseManpowerCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("IncreaseManpowerCost") + ": ", ((Game.getCiv(iCivID).ruler.rulerBonuses.IncreaseManpowerCost > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).ruler.rulerBonuses.IncreaseManpowerCost, 100) + "%", Game_Calendar.IMG_MANPOWER_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (Game.getCiv(iCivID).ruler.rulerBonuses.RecruitmentTime != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("RecruitmentTime") + ": ", ((Game.getCiv(iCivID).ruler.rulerBonuses.RecruitmentTime > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).ruler.rulerBonuses.RecruitmentTime, 100) + "%", Game_Calendar.IMG_MANPOWER_TIME, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (Game.getCiv(iCivID).ruler.rulerBonuses.Research != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Research") + ": ", ((Game.getCiv(iCivID).ruler.rulerBonuses.Research > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).ruler.rulerBonuses.Research, 100) + "%", Game_Calendar.IMG_TECHNOLOGY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (Game.getCiv(iCivID).ruler.rulerBonuses.ResearchPoints != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ResearchPerMonth") + ": ", ((Game.getCiv(iCivID).ruler.rulerBonuses.ResearchPoints > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).ruler.rulerBonuses.ResearchPoints, 100), Game_Calendar.IMG_TECHNOLOGY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (Game.getCiv(iCivID).ruler.rulerBonuses.Devastation != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Devastation") + ": ", ((Game.getCiv(iCivID).ruler.rulerBonuses.Devastation > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).ruler.rulerBonuses.Devastation * 100.0f, 100) + "%", Images.devastation, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (Game.getCiv(iCivID).ruler.rulerBonuses.GeneralCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GeneralCost") + ": ", ((Game.getCiv(iCivID).ruler.rulerBonuses.GeneralCost > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).ruler.rulerBonuses.GeneralCost * 100.0f, 100) + "%", Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (Game.getCiv(iCivID).ruler.rulerBonuses.ConstructionCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ConstructionCost") + ": ", ((Game.getCiv(iCivID).ruler.rulerBonuses.ConstructionCost > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).ruler.rulerBonuses.ConstructionCost * 100.0f, 100) + "%", Images.construction, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (Game.getCiv(iCivID).ruler.rulerBonuses.AdministrationBuildingsCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AdministrationBuildingsCost") + ": ", ((Game.getCiv(iCivID).ruler.rulerBonuses.AdministrationBuildingsCost > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).ruler.rulerBonuses.AdministrationBuildingsCost * 100.0f, 100) + "%", Images.construction, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (Game.getCiv(iCivID).ruler.rulerBonuses.MilitaryBuildingsCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MilitaryBuildingsCost") + ": ", ((Game.getCiv(iCivID).ruler.rulerBonuses.MilitaryBuildingsCost > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).ruler.rulerBonuses.MilitaryBuildingsCost * 100.0f, 100) + "%", Images.construction, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (Game.getCiv(iCivID).ruler.rulerBonuses.EconomyBuildingsCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("EconomyBuildingsCost") + ": ", ((Game.getCiv(iCivID).ruler.rulerBonuses.EconomyBuildingsCost > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).ruler.rulerBonuses.EconomyBuildingsCost * 100.0f, 100) + "%", Images.construction, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (Game.getCiv(iCivID).ruler.rulerBonuses.InvestInEconomyCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("InvestInEconomyCost") + ": ", ((Game.getCiv(iCivID).ruler.rulerBonuses.InvestInEconomyCost > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).ruler.rulerBonuses.InvestInEconomyCost * 100.0f, 100) + "%", Game_Calendar.IMG_ECONOMY_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (Game.getCiv(iCivID).ruler.rulerBonuses.IncreaseTaxEfficiencyCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("IncreaseTaxEfficiencyCost") + ": ", ((Game.getCiv(iCivID).ruler.rulerBonuses.IncreaseTaxEfficiencyCost > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).ruler.rulerBonuses.IncreaseTaxEfficiencyCost * 100.0f, 100) + "%", Images.taxUp, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (Game.getCiv(iCivID).ruler.rulerBonuses.IncreaseGrowthRateCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("IncreaseGrowthRateCost") + ": ", ((Game.getCiv(iCivID).ruler.rulerBonuses.IncreaseGrowthRateCost > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).ruler.rulerBonuses.IncreaseGrowthRateCost * 100.0f, 100) + "%", Images.populationUp, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (Game.getCiv(iCivID).ruler.rulerBonuses.DevelopInfrastructureCost != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DevelopInfrastructureCost") + ": ", ((Game.getCiv(iCivID).ruler.rulerBonuses.DevelopInfrastructureCost > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).ruler.rulerBonuses.DevelopInfrastructureCost * 100.0f, 100) + "%", Images.infrastructureUp, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (Game.getCiv(iCivID).ruler.rulerBonuses.ImproveRelationsModifier != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ImproveRelationsModifier") + ": ", ((Game.getCiv(iCivID).ruler.rulerBonuses.ImproveRelationsModifier > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).ruler.rulerBonuses.ImproveRelationsModifier, 100) + "%", Images.diplomacy, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (Game.getCiv(iCivID).ruler.rulerBonuses.LoanInterest != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("LoanInterest") + ": ", ((Game.getCiv(iCivID).ruler.rulerBonuses.LoanInterest > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).ruler.rulerBonuses.LoanInterest, 100) + "%", Images.loan, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (Game.getCiv(iCivID).ruler.rulerBonuses.MonthlyLegacy != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyLegacy") + ": ", ((Game.getCiv(iCivID).ruler.rulerBonuses.MonthlyLegacy > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).ruler.rulerBonuses.MonthlyLegacy, 100), Images.legacy, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (Game.getCiv(iCivID).ruler.rulerBonuses.UnitsAttack != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsAttack") + ": ", ((Game.getCiv(iCivID).ruler.rulerBonuses.UnitsAttack > 0) ? "+" : "") + Game.getCiv(iCivID).ruler.rulerBonuses.UnitsAttack, Images.attack, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (Game.getCiv(iCivID).ruler.rulerBonuses.UnitsDefense != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsDefense") + ": ", ((Game.getCiv(iCivID).ruler.rulerBonuses.UnitsDefense > 0) ? "+" : "") + Game.getCiv(iCivID).ruler.rulerBonuses.UnitsDefense, Images.defense, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (Game.getCiv(iCivID).ruler.rulerBonuses.GeneralAttack != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GeneralsAttack") + ": ", ((Game.getCiv(iCivID).ruler.rulerBonuses.GeneralAttack > 0) ? "+" : "") + Game.getCiv(iCivID).ruler.rulerBonuses.GeneralAttack, Images.attack, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (Game.getCiv(iCivID).ruler.rulerBonuses.GeneralDefense != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GeneralsDefense") + ": ", ((Game.getCiv(iCivID).ruler.rulerBonuses.GeneralDefense > 0) ? "+" : "") + Game.getCiv(iCivID).ruler.rulerBonuses.GeneralDefense, Images.defense, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        nData.add(new MenuElement_HoverElement_Type_Empty());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (openDiplomacyView) {
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("OpenTheDiplomacyView"), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.diplomacy, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        return new MenuElement_Hover(nElements, openDiplomacyView);
    }
}
