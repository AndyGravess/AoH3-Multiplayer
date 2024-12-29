// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Resource;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Center;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.map.ResourcesManager;
import aoh.kingdoms.history.textures.Image;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.map.BuildingsManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement_Type;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.MenuElement;

public class ButtonBuilding extends MenuElement
{
    public int building;
    public int buildingID;
    public String sText;
    public int iTextWidth;
    public boolean built;
    public String sCost;
    public int iCostWidth;
    public int iCostHeight;
    public String sRecruitmentTime;
    public int iRecruitmentTimeWidth;
    public int iRecruitmentTimeHeight;
    public static float iconScale;
    public static int timeIconWidth;
    public static int timeIconHeight;
    public static int goldIconWidth;
    public static int goldIconHeight;
    public Color colorBG;
    public Color colorBGOver;
    public static final Color COLOR_STATS;
    
    public ButtonBuilding(final boolean built, final int building, final int buildingID, final int iPosX, final int iPosY, final int nWidth, final boolean isClickable, final boolean isResearched) {
        this.iTextWidth = 0;
        this.built = false;
        this.typeOfElement = MenuElement_Type.BUTTON;
        this.built = built;
        this.building = building;
        this.buildingID = buildingID;
        this.fontID = CFG.FONT_REGULAR;
        ButtonBuilding.iconScale = this.getImageScale(Images.gold);
        ButtonBuilding.timeIconWidth = (int)(ImageManager.getImage(Images.buildTime).getWidth() * ButtonBuilding.iconScale);
        ButtonBuilding.timeIconHeight = (int)(ImageManager.getImage(Images.buildTime).getHeight() * ButtonBuilding.iconScale);
        ButtonBuilding.goldIconWidth = (int)(ImageManager.getImage(Images.gold).getWidth() * ButtonBuilding.iconScale);
        ButtonBuilding.goldIconHeight = (int)(ImageManager.getImage(Images.gold).getHeight() * ButtonBuilding.iconScale);
        this.sRecruitmentTime = "" + Game.getBuildingConstructionTime(Game.player.iCivID, -1, building, buildingID);
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sRecruitmentTime);
        this.iRecruitmentTimeWidth = (int)Renderer.glyphLayout.width;
        this.iRecruitmentTimeHeight = (int)Renderer.glyphLayout.height;
        this.sCost = "" + Game.getBuildingConstructionCost(Game.player.iCivID, -1, building, buildingID);
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), this.sCost);
        this.iCostWidth = (int)Renderer.glyphLayout.width;
        this.iCostHeight = (int)Renderer.glyphLayout.height;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidth(nWidth);
        this.setHeight(ImageManager.getImage(Images.buildingsFrame).getHeight());
        this.setText(BuildingsManager.buildings.get(building).Name[buildingID]);
        this.setClickable(isClickable);
        this.setVisible(true);
        if (isResearched) {
            this.colorBG = Colors.COLOR_GRADIENT_BG_BLUE;
            this.colorBGOver = Colors.COLOR_GRADIENT_OVER_BLUE;
        }
        else {
            this.colorBG = Colors.COLOR_GRADIENT_BG;
            this.colorBGOver = Colors.COLOR_GRADIENT_OVER;
        }
        int tWMax = 0;
        while (this.iTextWidth > nWidth && this.getText().length() > 5 && ++tWMax < 100) {
            this.setText(this.getText().substring(0, Math.max(1, this.getText().length() - 3)) + "..");
        }
    }
    
    @Override
    public final void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        this.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
        this.drawText(oSB, iTranslateX, iTranslateY, isActive);
    }
    
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(this.colorBG.r, this.colorBG.g, this.colorBG.b, 0.4f));
        Images.pix.draw(oSB, this.getPosX() + this.getInnerPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getInnerWidth(), this.getHeight());
        oSB.setColor(new Color(this.colorBG.r, this.colorBG.g, this.colorBG.b, 0.3f));
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, this.getPosX() + this.getInnerPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getInnerWidth(), this.getHeight());
        oSB.setColor(new Color(ButtonBuilding.COLOR_STATS.r, ButtonBuilding.COLOR_STATS.g, ButtonBuilding.COLOR_STATS.b, 0.6f));
        Images.pix.draw(oSB, this.getPosX() + this.getInnerPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getInnerWidth(), CFG.TEXT_HEIGHT + CFG.PADDING * 4);
        oSB.setColor(new Color(this.colorBGOver.r, this.colorBGOver.g, this.colorBGOver.b, 0.6f));
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, this.getPosX() + this.getInnerPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getInnerWidth(), CFG.TEXT_HEIGHT + CFG.PADDING * 4);
        oSB.setColor(new Color(ButtonBuilding.COLOR_STATS.r, ButtonBuilding.COLOR_STATS.g, ButtonBuilding.COLOR_STATS.b, 0.4f));
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, this.getPosX() + this.getInnerPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getInnerWidth(), this.getHeight());
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, this.getPosX() + this.getInnerPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getInnerWidth(), 1);
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, this.getPosX() + this.getInnerPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getInnerWidth(), 1);
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, this.getPosX() + this.getInnerPosX() + iTranslateX, this.getPosY() + CFG.TEXT_HEIGHT + CFG.PADDING * 4 - 1 + iTranslateY, this.getInnerWidth(), 1);
        oSB.setColor(new Color(this.colorBGOver.r, this.colorBGOver.g, this.colorBGOver.b, 0.8f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + this.getInnerPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getInnerWidth(), CFG.TEXT_HEIGHT + CFG.PADDING * 4, false, true);
        oSB.setColor(new Color(this.colorBG.r, this.colorBG.g, this.colorBG.b, 0.5f));
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, this.getPosX() + this.getInnerPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getInnerWidth(), 1);
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, this.getPosX() + this.getInnerPosX() + iTranslateX, this.getPosY() + CFG.TEXT_HEIGHT + CFG.PADDING * 4 - 1 + iTranslateY, this.getInnerWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.3f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + this.getInnerPosX() + iTranslateX, this.getPosY() + CFG.TEXT_HEIGHT + CFG.PADDING * 4 + iTranslateY, this.getInnerWidth(), CFG.PADDING);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.2f));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + this.getInnerPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING + iTranslateY, this.getInnerWidth(), CFG.PADDING, false, true);
        oSB.setColor(Color.WHITE);
        if (this.built || this.getIsHovered() || isActive) {
            BuildingsManager.buildingImages.get(BuildingsManager.buildings.get(this.building).ImageID[this.buildingID]).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.buildingsFrame).getWidth(), ImageManager.getImage(Images.buildingsFrame).getHeight());
        }
        else {
            Renderer.setBlackWhite(oSB);
            BuildingsManager.buildingImages.get(BuildingsManager.buildings.get(this.building).ImageID[this.buildingID]).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.buildingsFrame).getWidth(), ImageManager.getImage(Images.buildingsFrame).getHeight());
            Renderer.setShaderDefault(oSB);
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.3f));
            BuildingsManager.buildingImages.get(BuildingsManager.buildings.get(this.building).ImageID[this.buildingID]).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.buildingsFrame).getWidth(), ImageManager.getImage(Images.buildingsFrame).getHeight());
            oSB.setColor(Color.WHITE);
        }
        oSB.setColor(Color.WHITE);
        if (BuildingsManager.buildings.get(this.building).RequiredResource >= 0) {
            ResourcesManager.resourceImages.get(BuildingsManager.buildings.get(this.building).RequiredResource).draw(oSB, this.getPosX() + ImageManager.getImage(Images.buildingsFrame).getWidth() - ResourcesManager.resourceImages.get(BuildingsManager.buildings.get(this.building).RequiredResource).getWidth() - CFG.PADDING + iTranslateX, this.getPosY() + ImageManager.getImage(Images.buildingsFrame).getHeight() - ResourcesManager.resourceImages.get(BuildingsManager.buildings.get(this.building).RequiredResource).getHeight() - CFG.PADDING + iTranslateY);
        }
        ImageManager.getImage(Images.buildingsFrame).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        if (isActive) {
            Renderer.drawBoxLineFrame(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.buildingsFrame).getWidth(), ImageManager.getImage(Images.buildingsFrame).getHeight(), Colors.COLOR_BOX_ACTIVE);
        }
        else if (this.getIsHovered()) {
            Renderer.drawBoxLineFrame(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.buildingsFrame).getWidth(), ImageManager.getImage(Images.buildingsFrame).getHeight(), Colors.COLOR_BOX_HOVER);
        }
    }
    
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getText(), this.getPosX() + this.getInnerPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + CFG.PADDING * 2 + iTranslateY, this.built ? Colors.HOVER_POSITIVE : this.getColor(isActive));
        final int centerY = CFG.TEXT_HEIGHT + CFG.PADDING * 4 + (this.getHeight() - CFG.TEXT_HEIGHT - CFG.PADDING * 4) / 2;
        ImageManager.getImage(Images.gold).draw(oSB, this.getPosX() + this.getInnerPosX() + this.getInnerWidth() / 2 - (this.iCostWidth + ButtonBuilding.goldIconWidth + this.iRecruitmentTimeWidth + ButtonBuilding.timeIconWidth + CFG.PADDING * 6) / 2 + this.iCostWidth + CFG.PADDING + iTranslateX, this.getPosY() + centerY - ButtonBuilding.timeIconHeight / 2 + iTranslateY, ButtonBuilding.goldIconWidth, ButtonBuilding.goldIconHeight);
        Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, this.sCost, this.getPosX() + this.getInnerPosX() + this.getInnerWidth() / 2 - (this.iCostWidth + ButtonBuilding.goldIconWidth + this.iRecruitmentTimeWidth + ButtonBuilding.timeIconWidth + CFG.PADDING * 6) / 2 + iTranslateX, this.getPosY() + centerY - this.iRecruitmentTimeHeight / 2 + iTranslateY, Colors.HOVER_RIGHT2);
        ImageManager.getImage(Images.buildTime).draw(oSB, this.getPosX() + this.getInnerPosX() + this.getInnerWidth() / 2 - (this.iCostWidth + ButtonBuilding.goldIconWidth + this.iRecruitmentTimeWidth + ButtonBuilding.timeIconWidth + CFG.PADDING * 6) / 2 + this.iCostWidth + ButtonBuilding.goldIconWidth + CFG.PADDING * 6 + this.iRecruitmentTimeWidth + iTranslateX, this.getPosY() + centerY - ButtonBuilding.timeIconHeight / 2 + iTranslateY, ButtonBuilding.timeIconWidth, ButtonBuilding.timeIconHeight);
        Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, this.sRecruitmentTime, this.getPosX() + this.getInnerPosX() + this.getInnerWidth() / 2 - (this.iCostWidth + ButtonBuilding.goldIconWidth + this.iRecruitmentTimeWidth + ButtonBuilding.timeIconWidth + CFG.PADDING * 6) / 2 + this.iCostWidth + CFG.PADDING * 5 + ButtonBuilding.goldIconWidth + iTranslateX, this.getPosY() + centerY - this.iRecruitmentTimeHeight / 2 + iTranslateY, Colors.HOVER_RIGHT2);
    }
    
    protected Color getColor(final boolean isActive) {
        return Colors.getColorTopStatsHover(isActive, this.getIsHovered());
    }
    
    @Override
    public void setText(final String sText) {
        this.sText = sText;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), this.getText());
        this.iTextWidth = (int)Renderer.glyphLayout.width;
    }
    
    @Override
    public String getText() {
        return this.sText;
    }
    
    public int getInnerPosX() {
        return ImageManager.getImage(Images.buildingsFrame).getWidth();
    }
    
    public int getInnerWidth() {
        return this.getWidth() - ImageManager.getImage(Images.buildingsFrame).getWidth();
    }
    
    private final float getImageScale(final int iImageID) {
        return CFG.TEXT_HEIGHT / (float)ImageManager.getImage(iImageID).getHeight();
    }
    
    @Override
    public void buildElementHover() {
        this.menuElementHover = getHoverBuilding(this.building, this.buildingID, false);
    }
    
    @Override
    public int getValue1() {
        return this.building;
    }
    
    @Override
    public int getValue2() {
        return this.buildingID;
    }
    
    public static MenuElement_Hover getHoverBuilding(final int building, final int buildingID, final boolean addBack) {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        if (addBack) {
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.lang.get("Back"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        nData.add(new MenuElement_HoverElement_Type_TextTitle(BuildingsManager.buildings.get(building).Name[buildingID], CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (BuildingsManager.buildings.get(building).RequiredResource < 0) {
            nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get(BuildingsManager.buildings.get(building).NameDesc[buildingID]), CFG.FONT_REGULAR_SMALL, Colors.BUTTON_TEXT_BRIGHT));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (BuildingsManager.buildings.get(building).MonthlyIncome != null && BuildingsManager.buildings.get(building).MonthlyIncome[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyIncome") + ": ", ((BuildingsManager.buildings.get(building).MonthlyIncome[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).MonthlyIncome[buildingID], 100), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).TaxEfficiency != null && BuildingsManager.buildings.get(building).TaxEfficiency[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("TaxEfficiency") + ": ", "" + ((BuildingsManager.buildings.get(building).TaxEfficiency[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).TaxEfficiency[buildingID], 100) + "%", Images.tax, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).LocalTaxEfficiency != null && BuildingsManager.buildings.get(building).LocalTaxEfficiency[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("LocalTaxEfficiency") + ": ", "" + ((BuildingsManager.buildings.get(building).LocalTaxEfficiency[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).LocalTaxEfficiency[buildingID], 100) + "%", Images.tax, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).MaxInfrastructure != null && BuildingsManager.buildings.get(building).MaxInfrastructure[buildingID] != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumInfrastructureLevel") + ": ", "" + ((BuildingsManager.buildings.get(building).MaxInfrastructure[buildingID] > 0) ? "+" : "") + BuildingsManager.buildings.get(building).MaxInfrastructure[buildingID], Images.infrastructure, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).BuildingSlots != null && BuildingsManager.buildings.get(building).BuildingSlots[buildingID] != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("BuildingSlots") + ": ", "" + ((BuildingsManager.buildings.get(building).BuildingSlots[buildingID] > 0) ? "+" : "") + BuildingsManager.buildings.get(building).BuildingSlots[buildingID], Images.build, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).IncomeProduction != null && BuildingsManager.buildings.get(building).IncomeProduction[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("IncomeProduction") + ": ", "" + ((BuildingsManager.buildings.get(building).IncomeProduction[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).IncomeProduction[buildingID], 100) + "%", Images.goods, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).ProductionEfficiency != null && BuildingsManager.buildings.get(building).ProductionEfficiency[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProductionEfficiency") + ": ", "" + ((BuildingsManager.buildings.get(building).ProductionEfficiency[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).ProductionEfficiency[buildingID], 100) + "%", Images.goods, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).ProvinceMaintenance != null && BuildingsManager.buildings.get(building).ProvinceMaintenance[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProvinceMaintenance") + ": ", "" + ((BuildingsManager.buildings.get(building).ProvinceMaintenance[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).ProvinceMaintenance[buildingID], 100) + "%", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).Economy != null && BuildingsManager.buildings.get(building).Economy[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Economy") + ": ", "" + ((BuildingsManager.buildings.get(building).Economy[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).Economy[buildingID], 100) + "", Game_Calendar.IMG_ECONOMY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).ConstructionCost != null && BuildingsManager.buildings.get(building).ConstructionCost[buildingID] != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ConstructionCost") + ": ", "" + ((BuildingsManager.buildings.get(building).ConstructionCost[buildingID] > 0) ? "+" : "") + CFG.getPrecision2((float)(BuildingsManager.buildings.get(building).ConstructionCost[buildingID] * 100), 100) + "%", Images.construction, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).ConstructionTimeBonus != null && BuildingsManager.buildings.get(building).ConstructionTimeBonus[buildingID] != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ConstructionTime") + ": ", "" + ((BuildingsManager.buildings.get(building).ConstructionTimeBonus[buildingID] > 0) ? "+" : "") + CFG.getPrecision2((float)(BuildingsManager.buildings.get(building).ConstructionTimeBonus[buildingID] * 100), 100) + "%", Images.buildTime, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).LocalGrowthRate != null && BuildingsManager.buildings.get(building).LocalGrowthRate[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("LocalGrowthRate") + ": ", "" + ((BuildingsManager.buildings.get(building).LocalGrowthRate[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).LocalGrowthRate[buildingID], 100) + "%", Images.populationGrowth, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).InvestInEconomyCost != null && BuildingsManager.buildings.get(building).InvestInEconomyCost[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("InvestInEconomyCost") + ": ", "" + ((BuildingsManager.buildings.get(building).InvestInEconomyCost[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).InvestInEconomyCost[buildingID] * 100.0f, 100) + "%", Game_Calendar.IMG_ECONOMY_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).IncreaseGrowthRateCost != null && BuildingsManager.buildings.get(building).IncreaseGrowthRateCost[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("IncreaseGrowthRateCost") + ": ", "" + ((BuildingsManager.buildings.get(building).IncreaseGrowthRateCost[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).IncreaseGrowthRateCost[buildingID] * 100.0f, 100) + "%", Images.populationUp, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).DevelopInfrastructureCost != null && BuildingsManager.buildings.get(building).DevelopInfrastructureCost[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DevelopInfrastructureCost") + ": ", "" + ((BuildingsManager.buildings.get(building).DevelopInfrastructureCost[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).DevelopInfrastructureCost[buildingID] * 100.0f, 100) + "%", Images.infrastructureUp, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).IncreaseManpowerCost != null && BuildingsManager.buildings.get(building).IncreaseManpowerCost[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("IncreaseManpowerCost") + ": ", "" + ((BuildingsManager.buildings.get(building).IncreaseManpowerCost[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).IncreaseManpowerCost[buildingID] * 100.0f, 100) + "%", Game_Calendar.IMG_MANPOWER_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).IncreaseTaxEfficiencyCost != null && BuildingsManager.buildings.get(building).IncreaseTaxEfficiencyCost[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("IncreaseTaxEfficiencyCost") + ": ", "" + ((BuildingsManager.buildings.get(building).IncreaseTaxEfficiencyCost[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).IncreaseTaxEfficiencyCost[buildingID] * 100.0f, 100) + "%", Images.taxUp, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).ResearchPoints != null && BuildingsManager.buildings.get(building).ResearchPoints[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ResearchPerMonth") + ": ", "" + ((BuildingsManager.buildings.get(building).ResearchPoints[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).ResearchPoints[buildingID], 100), Game_Calendar.IMG_TECHNOLOGY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).MonthlyLegacy != null && BuildingsManager.buildings.get(building).MonthlyLegacy[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyLegacy") + ": ", "" + ((BuildingsManager.buildings.get(building).MonthlyLegacy[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).MonthlyLegacy[buildingID], 100) + "", Images.legacy, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).MaximumManpower != null && BuildingsManager.buildings.get(building).MaximumManpower[buildingID] != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", "" + ((BuildingsManager.buildings.get(building).MaximumManpower[buildingID] > 0) ? "+" : "") + CFG.getPrecision2((float)BuildingsManager.buildings.get(building).MaximumManpower[buildingID], 1) + "", Game_Calendar.IMG_MANPOWER_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).LocalManpower != null && BuildingsManager.buildings.get(building).LocalManpower[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("LocalManpower") + ": ", "" + ((BuildingsManager.buildings.get(building).LocalManpower[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).LocalManpower[buildingID] * 100.0f, 1) + "%", Game_Calendar.IMG_MANPOWER_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).RecruitArmyCostInProvince != null && BuildingsManager.buildings.get(building).RecruitArmyCostInProvince[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ArmyRecruitmentCostInProvince") + ": ", "" + ((BuildingsManager.buildings.get(building).RecruitArmyCostInProvince[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).RecruitArmyCostInProvince[buildingID], 100) + "%", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).DefenseBonus != null && BuildingsManager.buildings.get(building).DefenseBonus[buildingID] != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DefenseBonus") + ": ", "" + ((BuildingsManager.buildings.get(building).DefenseBonus[buildingID] > 0) ? "+" : "") + CFG.getPrecision2((float)BuildingsManager.buildings.get(building).DefenseBonus[buildingID], 100), Images.defense, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).FortLevel != null && BuildingsManager.buildings.get(building).FortLevel[buildingID] != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DefenseLevel") + ": ", "" + ((BuildingsManager.buildings.get(building).FortLevel[buildingID] > 0) ? "+" : "") + CFG.getPrecision2((float)BuildingsManager.buildings.get(building).FortLevel[buildingID], 100), Images.fort, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).FortDefense != null && BuildingsManager.buildings.get(building).FortDefense[buildingID] != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProvinceDefense") + ": ", "" + ((BuildingsManager.buildings.get(building).FortDefense[buildingID] > 0) ? "+" : "") + CFG.getPrecision2((float)BuildingsManager.buildings.get(building).FortDefense[buildingID], 1), Game_Calendar.IMG_FORT_DEFENSE, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).ArmyMovementSpeed != null && BuildingsManager.buildings.get(building).ArmyMovementSpeed[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ArmyMovementSpeed") + ": ", "" + ((BuildingsManager.buildings.get(building).ArmyMovementSpeed[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).ArmyMovementSpeed[buildingID] * 100.0f, 100) + "%", Images.movementSpeed, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).CasualtiesNuclearAttacks != null && BuildingsManager.buildings.get(building).CasualtiesNuclearAttacks[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("CasualtiesFromNuclearAttacks") + ": ", "" + ((BuildingsManager.buildings.get(building).CasualtiesNuclearAttacks[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).CasualtiesNuclearAttacks[buildingID] * 100.0f, 100) + "%", Images.nuke, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).DiseaseDeathRate != null && BuildingsManager.buildings.get(building).DiseaseDeathRate[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DiseasesDeathRate") + ": ", "" + ((BuildingsManager.buildings.get(building).DiseaseDeathRate[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).DiseaseDeathRate[buildingID] * 100.0f, 100) + "%", Images.disease, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).MaintenanceCost[buildingID] != 0.0f) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaintenanceCost") + ": ", Game.lang.get("XPerMonth", "" + CFG.getPrecision2(BuildingsManager.buildings.get(building).MaintenanceCost[buildingID], 1000)), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getBuildingConstructionCost(Game.player.iCivID, -1, building, buildingID), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ConstructionTime") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DaysX", Game.getBuildingConstructionTime(Game.player.iCivID, -1, building, buildingID)), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.buildTime, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (BuildingsManager.buildings.get(building).RequiredResource >= 0) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("RequiredResource") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text(ResourcesManager.getResourceName(BuildingsManager.buildings.get(building).RequiredResource), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_Resource(BuildingsManager.buildings.get(building).RequiredResource, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).RequiredGovernmentID >= 0) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Government") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text(Game.ideologiesManager.getIdeology(BuildingsManager.buildings.get(building).RequiredGovernmentID).Name, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.government, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).RequiredReligionID >= 0) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Religion") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text(Game.religionManager.getReligion(BuildingsManager.buildings.get(building).RequiredReligionID).Name, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.religion, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (BuildingsManager.buildings.get(building).FortLevel != null && BuildingsManager.buildings.get(building).FortLevel[buildingID] != 0) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData, false));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("AfterASuccessfulSiegeAllNeighboringProvincesWithoutDefensiveBuildingsWillBeOccupied"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nElements.add(new MenuElement_HoverElement(nData, false));
            nData.clear();
        }
        return new MenuElement_Hover(nElements);
    }
    
    public static List<MenuElement> getBuildingBonuses(final int building, final int buildingID, final int paddingLeft, final int menuWidth) {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int maxIconW = ImageManager.getImage(Images.gold).getWidth();
        final int statH = CFG.TEXT_HEIGHT + CFG.PADDING * 4;
        if (BuildingsManager.buildings.get(building).MonthlyIncome != null && BuildingsManager.buildings.get(building).MonthlyIncome[buildingID] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("MonthlyIncome") + ": ", ((BuildingsManager.buildings.get(building).MonthlyIncome[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).MonthlyIncome[buildingID], 100), Images.gold, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (BuildingsManager.buildings.get(building).TaxEfficiency != null && BuildingsManager.buildings.get(building).TaxEfficiency[buildingID] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("TaxEfficiency") + ": ", ((BuildingsManager.buildings.get(building).TaxEfficiency[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).TaxEfficiency[buildingID], 100) + "%", Images.tax, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (BuildingsManager.buildings.get(building).LocalTaxEfficiency != null && BuildingsManager.buildings.get(building).LocalTaxEfficiency[buildingID] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("LocalTaxEfficiency") + ": ", ((BuildingsManager.buildings.get(building).LocalTaxEfficiency[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).LocalTaxEfficiency[buildingID], 100) + "%", Images.tax, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (BuildingsManager.buildings.get(building).MaxInfrastructure != null && BuildingsManager.buildings.get(building).MaxInfrastructure[buildingID] != 0) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("MaximumInfrastructureLevel") + ": ", ((BuildingsManager.buildings.get(building).MaxInfrastructure[buildingID] > 0) ? "+" : "") + BuildingsManager.buildings.get(building).MaxInfrastructure[buildingID], Images.infrastructure, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (BuildingsManager.buildings.get(building).BuildingSlots != null && BuildingsManager.buildings.get(building).BuildingSlots[buildingID] != 0) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("BuildingSlots") + ": ", ((BuildingsManager.buildings.get(building).BuildingSlots[buildingID] > 0) ? "+" : "") + BuildingsManager.buildings.get(building).BuildingSlots[buildingID], Images.build, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (BuildingsManager.buildings.get(building).IncomeProduction != null && BuildingsManager.buildings.get(building).IncomeProduction[buildingID] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("IncomeProduction") + ": ", ((BuildingsManager.buildings.get(building).IncomeProduction[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).IncomeProduction[buildingID], 100) + "%", Images.goods, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (BuildingsManager.buildings.get(building).ProductionEfficiency != null && BuildingsManager.buildings.get(building).ProductionEfficiency[buildingID] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("ProductionEfficiency") + ": ", ((BuildingsManager.buildings.get(building).ProductionEfficiency[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).ProductionEfficiency[buildingID], 100) + "%", Images.goods, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (BuildingsManager.buildings.get(building).ProvinceMaintenance != null && BuildingsManager.buildings.get(building).ProvinceMaintenance[buildingID] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("ProvinceMaintenance") + ": ", ((BuildingsManager.buildings.get(building).ProvinceMaintenance[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).ProvinceMaintenance[buildingID], 100) + "%", Images.gold, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (BuildingsManager.buildings.get(building).Economy != null && BuildingsManager.buildings.get(building).Economy[buildingID] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Economy") + ": ", ((BuildingsManager.buildings.get(building).Economy[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).Economy[buildingID], 100), Game_Calendar.IMG_ECONOMY, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (BuildingsManager.buildings.get(building).ConstructionCost != null && BuildingsManager.buildings.get(building).ConstructionCost[buildingID] != 0) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("ConstructionCost") + ": ", ((BuildingsManager.buildings.get(building).ConstructionCost[buildingID] > 0) ? "+" : "") + CFG.getPrecision2((float)(BuildingsManager.buildings.get(building).ConstructionCost[buildingID] * 100), 100) + "%", Images.construction, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (BuildingsManager.buildings.get(building).ConstructionTimeBonus != null && BuildingsManager.buildings.get(building).ConstructionTimeBonus[buildingID] != 0) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("ConstructionTime") + ": ", ((BuildingsManager.buildings.get(building).ConstructionTimeBonus[buildingID] > 0) ? "+" : "") + CFG.getPrecision2((float)(BuildingsManager.buildings.get(building).ConstructionTimeBonus[buildingID] * 100), 100) + "%", Images.buildTime, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (BuildingsManager.buildings.get(building).LocalGrowthRate != null && BuildingsManager.buildings.get(building).LocalGrowthRate[buildingID] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("LocalGrowthRate") + ": ", ((BuildingsManager.buildings.get(building).LocalGrowthRate[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).LocalGrowthRate[buildingID], 100) + "%", Images.populationGrowth, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (BuildingsManager.buildings.get(building).InvestInEconomyCost != null && BuildingsManager.buildings.get(building).InvestInEconomyCost[buildingID] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("InvestInEconomyCost") + ": ", ((BuildingsManager.buildings.get(building).InvestInEconomyCost[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).InvestInEconomyCost[buildingID] * 100.0f, 100) + "%", Game_Calendar.IMG_ECONOMY_UP, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (BuildingsManager.buildings.get(building).IncreaseGrowthRateCost != null && BuildingsManager.buildings.get(building).IncreaseGrowthRateCost[buildingID] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("IncreaseGrowthRateCost") + ": ", ((BuildingsManager.buildings.get(building).IncreaseGrowthRateCost[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).IncreaseGrowthRateCost[buildingID] * 100.0f, 100) + "%", Images.populationUp, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (BuildingsManager.buildings.get(building).DevelopInfrastructureCost != null && BuildingsManager.buildings.get(building).DevelopInfrastructureCost[buildingID] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("DevelopInfrastructureCost") + ": ", ((BuildingsManager.buildings.get(building).DevelopInfrastructureCost[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).DevelopInfrastructureCost[buildingID] * 100.0f, 100) + "%", Images.infrastructureUp, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (BuildingsManager.buildings.get(building).IncreaseManpowerCost != null && BuildingsManager.buildings.get(building).IncreaseManpowerCost[buildingID] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("IncreaseManpowerCost") + ": ", ((BuildingsManager.buildings.get(building).IncreaseManpowerCost[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).IncreaseManpowerCost[buildingID] * 100.0f, 100) + "%", Game_Calendar.IMG_MANPOWER_UP, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (BuildingsManager.buildings.get(building).IncreaseTaxEfficiencyCost != null && BuildingsManager.buildings.get(building).IncreaseTaxEfficiencyCost[buildingID] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("IncreaseTaxEfficiencyCost") + ": ", ((BuildingsManager.buildings.get(building).IncreaseTaxEfficiencyCost[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).IncreaseTaxEfficiencyCost[buildingID] * 100.0f, 100) + "%", Images.taxUp, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (BuildingsManager.buildings.get(building).ResearchPoints != null && BuildingsManager.buildings.get(building).ResearchPoints[buildingID] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("ResearchPerMonth") + ": ", ((BuildingsManager.buildings.get(building).ResearchPoints[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).ResearchPoints[buildingID], 100), Game_Calendar.IMG_TECHNOLOGY, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (BuildingsManager.buildings.get(building).MonthlyLegacy != null && BuildingsManager.buildings.get(building).MonthlyLegacy[buildingID] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("MonthlyLegacy") + ": ", ((BuildingsManager.buildings.get(building).MonthlyLegacy[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).MonthlyLegacy[buildingID], 100), Images.legacy, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (BuildingsManager.buildings.get(building).MaximumManpower != null && BuildingsManager.buildings.get(building).MaximumManpower[buildingID] != 0) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("MaximumManpower") + ": ", ((BuildingsManager.buildings.get(building).MaximumManpower[buildingID] > 0) ? "+" : "") + CFG.getPrecision2((float)BuildingsManager.buildings.get(building).MaximumManpower[buildingID], 1), Game_Calendar.IMG_MANPOWER_UP, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (BuildingsManager.buildings.get(building).LocalManpower != null && BuildingsManager.buildings.get(building).LocalManpower[buildingID] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("LocalManpower") + ": ", ((BuildingsManager.buildings.get(building).LocalManpower[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).LocalManpower[buildingID] * 100.0f, 1) + "%", Game_Calendar.IMG_MANPOWER_UP, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (BuildingsManager.buildings.get(building).RecruitArmyCostInProvince != null && BuildingsManager.buildings.get(building).RecruitArmyCostInProvince[buildingID] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("ArmyRecruitmentCostInProvince") + ": ", ((BuildingsManager.buildings.get(building).RecruitArmyCostInProvince[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).RecruitArmyCostInProvince[buildingID], 100) + "%", Images.gold, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (BuildingsManager.buildings.get(building).DefenseBonus != null && BuildingsManager.buildings.get(building).DefenseBonus[buildingID] != 0) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("DefenseBonus") + ": ", ((BuildingsManager.buildings.get(building).DefenseBonus[buildingID] > 0) ? "+" : "") + CFG.getPrecision2((float)BuildingsManager.buildings.get(building).DefenseBonus[buildingID], 100), Images.defense, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (BuildingsManager.buildings.get(building).FortLevel != null && BuildingsManager.buildings.get(building).FortLevel[buildingID] != 0) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("DefenseLevel") + ": ", ((BuildingsManager.buildings.get(building).FortLevel[buildingID] > 0) ? "+" : "") + CFG.getPrecision2((float)BuildingsManager.buildings.get(building).FortLevel[buildingID], 100), Images.fort, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (BuildingsManager.buildings.get(building).FortDefense != null && BuildingsManager.buildings.get(building).FortDefense[buildingID] != 0) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("ProvinceDefense") + ": ", "" + ((BuildingsManager.buildings.get(building).FortDefense[buildingID] > 0) ? "+" : "") + CFG.getPrecision2((float)BuildingsManager.buildings.get(building).FortDefense[buildingID], 1), Game_Calendar.IMG_FORT_DEFENSE, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (BuildingsManager.buildings.get(building).ArmyMovementSpeed != null && BuildingsManager.buildings.get(building).ArmyMovementSpeed[buildingID] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("ArmyMovementSpeed") + ": ", "" + ((BuildingsManager.buildings.get(building).ArmyMovementSpeed[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).ArmyMovementSpeed[buildingID] * 100.0f, 100) + "%", Images.movementSpeed, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (BuildingsManager.buildings.get(building).CasualtiesNuclearAttacks != null && BuildingsManager.buildings.get(building).CasualtiesNuclearAttacks[buildingID] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("CasualtiesFromNuclearAttacks") + ": ", "" + ((BuildingsManager.buildings.get(building).CasualtiesNuclearAttacks[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).CasualtiesNuclearAttacks[buildingID] * 100.0f, 100) + "%", Images.nuke, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        if (BuildingsManager.buildings.get(building).DiseaseDeathRate != null && BuildingsManager.buildings.get(building).DiseaseDeathRate[buildingID] != 0.0f) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("DiseasesDeathRate") + ": ", "" + ((BuildingsManager.buildings.get(building).DiseaseDeathRate[buildingID] > 0.0f) ? "+" : "") + CFG.getPrecision2(BuildingsManager.buildings.get(building).DiseaseDeathRate[buildingID] * 100.0f, 100) + "%", Images.disease, paddingLeft, 0, menuWidth - paddingLeft * 2, statH, maxIconW));
        }
        return menuElements;
    }
    
    static {
        ButtonBuilding.iconScale = 1.0f;
        COLOR_STATS = new Color(0.039215688f, 0.05882353f, 0.09803922f, 0.6f);
    }
}
