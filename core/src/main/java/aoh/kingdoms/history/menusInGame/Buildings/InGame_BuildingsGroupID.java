// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Buildings;

import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menusInGame.Technology.InGame_TechnologyTree;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.button.ButtonBuilding2_NotAvailable;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title;
import java.util.List;
import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceBonuses;
import aoh.kingdoms.history.menu_element.Toast;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.button.ButtonBuilding2;
import aoh.kingdoms.history.map.BuildingsManager;
import aoh.kingdoms.history.map.ResourcesManager;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.zOther.Point_XY;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_BuildingsGroupID extends Menu
{
    public int groupID;
    
    public InGame_BuildingsGroupID(final int groupID) {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        this.groupID = groupID;
        final int paddingLeft = CFG.PADDING * 2;
        int buttonY = CFG.PADDING * 2;
        final int menuWidth = (ImageManager.getImage(Images.title928).getWidth() - Images.boxTitleBORDERWIDTH * 2) / 4;
        final List<Point_XY> notAvailable = new ArrayList<Point_XY>();
        final List<Point_XY> notAvailableTech = new ArrayList<Point_XY>();
        if (Game.getProvince(InGame_BuildingsGroup.iProvinceID).getResourceID() >= 0 && ResourcesManager.lResources.get(Game.getProvince(InGame_BuildingsGroup.iProvinceID).getResourceID()).uniqueBuildingID >= 0 && BuildingsManager.buildings.get(ResourcesManager.lResources.get(Game.getProvince(InGame_BuildingsGroup.iProvinceID).getResourceID()).uniqueBuildingID).GroupID == groupID) {
            for (int j = 0; j < BuildingsManager.buildingSize.get(ResourcesManager.lResources.get(Game.getProvince(InGame_BuildingsGroup.iProvinceID).getResourceID()).uniqueBuildingID); ++j) {
                boolean addBuilding = true;
                if (!Game.getCiv(Game.player.iCivID).isBuildingResearched(ResourcesManager.lResources.get(Game.getProvince(InGame_BuildingsGroup.iProvinceID).getResourceID()).uniqueBuildingID, j)) {
                    addBuilding = false;
                    if (BuildingsManager.buildings.get(ResourcesManager.lResources.get(Game.getProvince(InGame_BuildingsGroup.iProvinceID).getResourceID()).uniqueBuildingID).ShowUpgrades) {
                        notAvailableTech.add(new Point_XY(ResourcesManager.lResources.get(Game.getProvince(InGame_BuildingsGroup.iProvinceID).getResourceID()).uniqueBuildingID, j));
                    }
                }
                else if (j > 0 && !Game.getProvince(InGame_BuildingsGroup.iProvinceID).buildingBuilt(ResourcesManager.lResources.get(Game.getProvince(InGame_BuildingsGroup.iProvinceID).getResourceID()).uniqueBuildingID, j - 1)) {
                    addBuilding = false;
                    if (BuildingsManager.buildings.get(ResourcesManager.lResources.get(Game.getProvince(InGame_BuildingsGroup.iProvinceID).getResourceID()).uniqueBuildingID).ShowUpgrades) {
                        notAvailable.add(new Point_XY(ResourcesManager.lResources.get(Game.getProvince(InGame_BuildingsGroup.iProvinceID).getResourceID()).uniqueBuildingID, j));
                    }
                }
                else if (BuildingsManager.buildings.get(ResourcesManager.lResources.get(Game.getProvince(InGame_BuildingsGroup.iProvinceID).getResourceID()).uniqueBuildingID).SeaAccessRequired && Game.getProvince(InGame_BuildingsGroup.iProvinceID).getLevelOfPort() < 0) {
                    addBuilding = false;
                }
                else if (BuildingsManager.buildings.get(ResourcesManager.lResources.get(Game.getProvince(InGame_BuildingsGroup.iProvinceID).getResourceID()).uniqueBuildingID).RequiredGovernmentID >= 0 && BuildingsManager.buildings.get(ResourcesManager.lResources.get(Game.getProvince(InGame_BuildingsGroup.iProvinceID).getResourceID()).uniqueBuildingID).RequiredGovernmentID != Game.getCiv(Game.getProvince(InGame_BuildingsGroup.iProvinceID).getCivID()).getIdeologyID()) {
                    addBuilding = false;
                }
                else if (BuildingsManager.buildings.get(ResourcesManager.lResources.get(Game.getProvince(InGame_BuildingsGroup.iProvinceID).getResourceID()).uniqueBuildingID).RequiredReligionID >= 0 && BuildingsManager.buildings.get(ResourcesManager.lResources.get(Game.getProvince(InGame_BuildingsGroup.iProvinceID).getResourceID()).uniqueBuildingID).RequiredReligionID != Game.getCiv(Game.getProvince(InGame_BuildingsGroup.iProvinceID).getCivID()).getReligionID()) {
                    addBuilding = false;
                }
                if (addBuilding) {
                    menuElements.add(new ButtonBuilding2(InGame_BuildingsGroup.iProvinceID, Game.getProvince(InGame_BuildingsGroup.iProvinceID).buildingBuilt(ResourcesManager.lResources.get(Game.getProvince(InGame_BuildingsGroup.iProvinceID).getResourceID()).uniqueBuildingID, j), ResourcesManager.lResources.get(Game.getProvince(InGame_BuildingsGroup.iProvinceID).getResourceID()).uniqueBuildingID, j, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true) {
                        @Override
                        public void actionElement() {
                            if (Game.getProvince(InGame_BuildingsGroup.iProvinceID).getCivID() == Game.player.iCivID) {
                                if (Game.getProvince(InGame_BuildingsGroup.iProvinceID).buildingBuilt(this.getValue1(), this.getValue2())) {
                                    InGame_Destroy.iProvinceID = InGame_BuildingsGroup.iProvinceID;
                                    InGame_Destroy.building = this.getValue1();
                                    InGame_Destroy.buildingID = this.getValue2();
                                    Game.menuManager.rebuildInGame_Destroy();
                                }
                                else if (Game.getProvince(InGame_BuildingsGroup.iProvinceID).getUsedBuildingsSlots() >= Game.getProvince(InGame_BuildingsGroup.iProvinceID).iBuildingsLimit) {
                                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BuildingSlots") + ": "));
                                    nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(InGame_BuildingsGroup.iProvinceID).getUsedBuildingsSlots() + " / " + Game.getProvince(InGame_BuildingsGroup.iProvinceID).iBuildingsLimit, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                                    nData.add(new MenuElement_HoverElement_Type_Image(Images.build, CFG.PADDING, 0));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("IncreaseEconomyInAProvinceToUnlockMoreBuildingSlots"), Colors.HOVER_GOLD));
                                    nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_ECONOMY, CFG.PADDING, 0));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    Game.menuManager.addToast(new Toast(nElements, 0, 6000));
                                }
                                else if (Game.getProvince(InGame_BuildingsGroup.iProvinceID).isOccupied()) {
                                    Game.menuManager.addToast_Error(Game.lang.get("OccupiedProvince"), Images.war);
                                }
                                else if (Game.getProvince(InGame_BuildingsGroup.iProvinceID).isUnderConstruction(this.getValue1(), this.getValue2())) {
                                    Game.menuManager.addToastGold(Game.lang.get("UnderConstruction"), Images.build);
                                }
                                else if (BuildingsManager.buildings.get(this.getValue1()).RequiredReligionID >= 0 && BuildingsManager.buildings.get(this.getValue1()).RequiredReligionID != Game.getCiv(Game.getProvince(InGame_BuildingsGroup.iProvinceID).getCivID()).getReligionID()) {
                                    Game.menuManager.addToastGold(Game.lang.get("Religion") + ": " + Game.religionManager.getReligion(BuildingsManager.buildings.get(this.getValue1()).RequiredReligionID).Name, Images.religion);
                                }
                                else if (BuildingsManager.buildings.get(this.getValue1()).RequiredGovernmentID >= 0 && BuildingsManager.buildings.get(this.getValue1()).RequiredGovernmentID != Game.getCiv(Game.getProvince(InGame_BuildingsGroup.iProvinceID).getCivID()).getIdeologyID()) {
                                    Game.menuManager.addToastGold(Game.lang.get("Government") + ": " + Game.ideologiesManager.getIdeology(BuildingsManager.buildings.get(this.getValue1()).RequiredGovernmentID).Name, Images.government);
                                }
                                else if (!Game.getProvince(InGame_BuildingsGroup.iProvinceID).addBuildingConstruction(this.getValue1(), this.getValue2())) {
                                    Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2((float)Game.getBuildingConstructionCost(Game.player.iCivID, InGame_BuildingsGroup.iProvinceID, this.getValue1(), this.getValue2()), 100), Images.gold);
                                }
                                else if (Game.menuManager.getVisibleInGame_ProvinceInfo()) {
                                    Game.menuManager.rebuildInGame_ProvinceInfo(false);
                                    Game.menuManager.setOrderInGame_Buildings();
                                    if (Game.menuManager.getVisibleInGame_ProvinceBonuses()) {
                                        Game.menuManager.rebuildInGame_ProvinceBonuses();
                                        Game.menuManager.setVisibleInGame_ProvinceBonuses(true, false);
                                        InGame_ProvinceBonuses.lTime = 0L;
                                    }
                                }
                            }
                        }
                    });
                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
                }
            }
        }
        for (int i = 0; i < BuildingsManager.buildingsSize; ++i) {
            if (BuildingsManager.buildings.get(i).GroupID == groupID) {
                for (int k = 0; k < BuildingsManager.buildingSize.get(i); ++k) {
                    boolean addBuilding2 = true;
                    if (!Game.getProvince(InGame_BuildingsGroup.iProvinceID).buildingBuilt(i, k)) {
                        if (!Game.getCiv(Game.player.iCivID).isBuildingResearched(i, k)) {
                            addBuilding2 = false;
                            if (BuildingsManager.buildings.get(i).ShowUpgrades) {
                                notAvailableTech.add(new Point_XY(i, k));
                            }
                        }
                        else if (BuildingsManager.buildings.get(i).SeaAccessRequired && Game.getProvince(InGame_BuildingsGroup.iProvinceID).getLevelOfPort() < 0) {
                            addBuilding2 = false;
                        }
                        else if (BuildingsManager.buildings.get(i).RequiredGovernmentID >= 0 && BuildingsManager.buildings.get(i).RequiredGovernmentID != Game.getCiv(Game.getProvince(InGame_BuildingsGroup.iProvinceID).getCivID()).getIdeologyID()) {
                            addBuilding2 = false;
                        }
                        else if (BuildingsManager.buildings.get(i).RequiredReligionID >= 0 && BuildingsManager.buildings.get(i).RequiredReligionID != Game.getCiv(Game.getProvince(InGame_BuildingsGroup.iProvinceID).getCivID()).getReligionID()) {
                            addBuilding2 = false;
                        }
                    }
                    if (addBuilding2) {
                        menuElements.add(new ButtonBuilding2(InGame_BuildingsGroup.iProvinceID, Game.getProvince(InGame_BuildingsGroup.iProvinceID).buildingBuilt(i, k), i, k, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true) {
                            @Override
                            public void actionElement() {
                                if (Game.getProvince(InGame_BuildingsGroup.iProvinceID).getCivID() == Game.player.iCivID) {
                                    if (Game.getProvince(InGame_BuildingsGroup.iProvinceID).buildingBuilt(this.getValue1(), this.getValue2())) {
                                        InGame_Destroy.iProvinceID = InGame_BuildingsGroup.iProvinceID;
                                        InGame_Destroy.building = this.getValue1();
                                        InGame_Destroy.buildingID = this.getValue2();
                                        Game.menuManager.rebuildInGame_Destroy();
                                    }
                                    else if (Game.getProvince(InGame_BuildingsGroup.iProvinceID).getUsedBuildingsSlots() >= Game.getProvince(InGame_BuildingsGroup.iProvinceID).iBuildingsLimit) {
                                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BuildingSlots") + ": "));
                                        nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(InGame_BuildingsGroup.iProvinceID).getUsedBuildingsSlots() + " / " + Game.getProvince(InGame_BuildingsGroup.iProvinceID).iBuildingsLimit, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                                        nData.add(new MenuElement_HoverElement_Type_Image(Images.build, CFG.PADDING, 0));
                                        nElements.add(new MenuElement_HoverElement(nData));
                                        nData.clear();
                                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("IncreaseEconomyInAProvinceToUnlockMoreBuildingSlots"), Colors.HOVER_GOLD));
                                        nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_ECONOMY, CFG.PADDING, 0));
                                        nElements.add(new MenuElement_HoverElement(nData));
                                        nData.clear();
                                        Game.menuManager.addToast(new Toast(nElements, 0, 6000));
                                    }
                                    else if (Game.getProvince(InGame_BuildingsGroup.iProvinceID).isOccupied()) {
                                        Game.menuManager.addToast_Error(Game.lang.get("OccupiedProvince"), Images.war);
                                    }
                                    else if (Game.getProvince(InGame_BuildingsGroup.iProvinceID).isUnderConstruction(this.getValue1(), this.getValue2())) {
                                        Game.menuManager.addToastGold(Game.lang.get("UnderConstruction"), Images.build);
                                    }
                                    else if (!Game.getProvince(InGame_BuildingsGroup.iProvinceID).addBuildingConstruction(this.getValue1(), this.getValue2())) {
                                        Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2((float)Game.getBuildingConstructionCost(Game.player.iCivID, InGame_BuildingsGroup.iProvinceID, this.getValue1(), this.getValue2()), 100), Images.gold);
                                    }
                                    else if (Game.menuManager.getVisibleInGame_ProvinceInfo()) {
                                        Game.menuManager.rebuildInGame_ProvinceInfo(false);
                                        Game.menuManager.setOrderInGame_Buildings();
                                        if (Game.menuManager.getVisibleInGame_ProvinceBonuses()) {
                                            Game.menuManager.rebuildInGame_ProvinceBonuses();
                                            Game.menuManager.setVisibleInGame_ProvinceBonuses(true, false);
                                            InGame_ProvinceBonuses.lTime = 0L;
                                        }
                                    }
                                }
                            }
                        });
                        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
                    }
                }
            }
        }
        if (notAvailable.size() > 0) {
            menuElements.add(new Text_Title(Game.lang.get("NotAvailable"), -1, 0, buttonY, menuWidth, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_REGULAR_SMALL) {
                @Override
                protected Color getColor(final boolean isActive) {
                    return Colors.getColorTopStats3(isActive, this.getIsHovered());
                }
                
                @Override
                public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
            for (int i = 0, iSize = notAvailable.size(); i < iSize; ++i) {
                menuElements.add(new ButtonBuilding2_NotAvailable(InGame_BuildingsGroup.iProvinceID, false, notAvailable.get(i).getPosX(), notAvailable.get(i).getPosY(), paddingLeft, buttonY, menuWidth - paddingLeft * 2, true, false, false, false));
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
            }
        }
        if (GameValues.inGame.SHOW_TO_BE_RESEARCHED_BUILDINGS && notAvailableTech.size() > 0) {
            menuElements.add(new Text_Title(Game.lang.get("ToBeResearched"), -1, 0, buttonY, menuWidth, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_REGULAR_SMALL) {
                @Override
                protected Color getColor(final boolean isActive) {
                    return Colors.getColorTopStats3(isActive, this.getIsHovered());
                }
                
                @Override
                public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
            for (int i = 0, iSize = notAvailableTech.size(); i < iSize; ++i) {
                menuElements.add(new ButtonBuilding2_NotAvailable(InGame_BuildingsGroup.iProvinceID, false, notAvailableTech.get(i).getPosX(), notAvailableTech.get(i).getPosY(), paddingLeft, buttonY, menuWidth - paddingLeft * 2, true, true, false, false) {
                    @Override
                    public void actionElement() {
                        if (Game.menuManager.getVisibleInGame_TechnologyTree()) {
                            Game.menuManager.setVisibleInGame_TechnologyTree(false);
                        }
                        else {
                            Game.menuManager.setVisibleInGame_TechnologyChoose(false);
                            InGame_TechnologyTree.centerToTechID = BuildingsManager.buildings.get(this.getValue1()).RequiredTechID[this.getValue2()];
                            Game.menuManager.rebuildInGame_TechnologyTree(false, true);
                        }
                    }
                });
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
            }
        }
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, InGame_BuildingsGroup.menuHeight)));
        this.initMenu(null, InGame_BuildingsGroup.menuX + menuWidth * groupID, InGame_BuildingsGroup.menuY, menuWidth, InGame_BuildingsGroup.menuHeight, menuElements, false, false);
        this.drawScrollPositionAlways2 = false;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        iTranslateX = InGame_BuildingsGroup.mTranslateX;
        if (this.groupID % 2 == 1) {
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.3f));
            Images.pix.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
            oSB.setColor(Color.WHITE);
        }
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.325f));
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, CFG.PADDING * 2, this.getHeight());
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * 2 + iTranslateX, this.getPosY() + iTranslateY, CFG.PADDING * 2, this.getHeight(), true, false);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void onHovered() {
        super.onHovered();
        Game.menuManager.setOrderOfMenu_InGameBuildings();
    }
}
