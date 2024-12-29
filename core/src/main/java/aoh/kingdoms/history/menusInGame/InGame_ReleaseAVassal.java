// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_Special;
import aoh.kingdoms.history.menu_element.MenuElement_Type;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_Flag;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2_TextLR;
import aoh.kingdoms.history.menusInGame.Info.InGame_Info;
import aoh.kingdoms.history.map.civilization.CivilizationRanking;
import aoh.kingdoms.history.map.province.ProvinceDrawArmy;
import aoh.kingdoms.history.menus.NewGame.NewGame;
import aoh.kingdoms.history.map.civilization.Civilization;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.button.ButtonGame_Image;
import aoh.kingdoms.history.mainGame.Game_Ages;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Active_Click;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Bonuses;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2Center;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu.Menu;

public class InGame_ReleaseAVassal extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static ReleaseVassalData releaseVassalData;
    
    public static void buildData(final int lordCivID, final int vassalCivID) {
        InGame_ReleaseAVassal.releaseVassalData = new ReleaseVassalData(lordCivID, vassalCivID);
        InGame_ReleaseAVassal.releaseVassalData.lProvinces = Game.getVassalsToRelease_Provinces(lordCivID, vassalCivID);
    }
    
    public static void buildData_MapMode(final int lordCivID, final int vassalCivID) {
        for (int i = 0; i < Game.getCiv(lordCivID).getNumOfProvinces(); ++i) {
            Game.getProvince(Game.getCiv(lordCivID).getProvinceID(i)).peaceTreatyIsTaken = false;
        }
        for (int i = 0; i < InGame_ReleaseAVassal.releaseVassalData.lProvinces.size(); ++i) {
            Game.getProvince(InGame_ReleaseAVassal.releaseVassalData.lProvinces.get(i)).peaceTreatyIsTaken = true;
        }
    }
    
    public static final void addProvince(final int provinceID) {
        if (Game.getProvince(provinceID).getCivID() == InGame_ReleaseAVassal.releaseVassalData.iLordID) {
            for (int i = 0; i < InGame_ReleaseAVassal.releaseVassalData.lProvinces.size(); ++i) {
                if (InGame_ReleaseAVassal.releaseVassalData.lProvinces.get(i) == provinceID) {
                    return;
                }
            }
            InGame_ReleaseAVassal.releaseVassalData.lProvinces.add(provinceID);
        }
    }
    
    public static final void removeProvince(final int provinceID) {
        for (int i = 0; i < InGame_ReleaseAVassal.releaseVassalData.lProvinces.size(); ++i) {
            if (InGame_ReleaseAVassal.releaseVassalData.lProvinces.get(i) == provinceID) {
                InGame_ReleaseAVassal.releaseVassalData.lProvinces.remove(i);
                return;
            }
        }
    }
    
    public InGame_ReleaseAVassal() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2;
        final int titleHeight = ImageManager.getImage(Images.title2).getHeight();
        final int menuWidth = ImageManager.getImage(Images.insideTop).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        int buttonY = 0;
        int buttonX = paddingLeft;
        final int buttonH = CFG.isDesktop() ? CFG.BUTTON_HEIGHT2 : CFG.BUTTON_HEIGHT;
        menuElements.add(new Text_Title_v2Center(Game.getCiv(InGame_ReleaseAVassal.releaseVassalData.iCivID).getCivName(), -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final int maxIconW = ImageManager.getImage(Images.gold).getWidth();
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Provinces") + ": ", CFG.getNumberWithSpaces("" + InGame_ReleaseAVassal.releaseVassalData.lProvinces.size()), Images.provinces, buttonX, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT3, maxIconW) {
            @Override
            public Color getColorBonus() {
                return Colors.HOVER_GOLD;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        long tPop = 0L;
        for (int i = 0; i < InGame_ReleaseAVassal.releaseVassalData.lProvinces.size(); ++i) {
            tPop += Game.getProvince(InGame_ReleaseAVassal.releaseVassalData.lProvinces.get(i)).getPopulationTotal();
        }
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Population") + ": ", CFG.getNumberWithSpaces("" + tPop), Images.population, buttonX, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT3, maxIconW) {
            @Override
            public Color getColorBonus() {
                return Colors.COLOR_POPULATION;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Active_Click(Game.lang.get("GovernmentChange"), InGame_ReleaseAVassal.releaseVassalData.changeGovernment ? Images.v : Images.x, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, ImageManager.getImage(Images.advantages).getWidth() + CFG.PADDING * 2, 0) {
            @Override
            public void actionElement() {
                InGame_ReleaseAVassal.releaseVassalData.changeGovernment = !InGame_ReleaseAVassal.releaseVassalData.changeGovernment;
                this.setImageID(InGame_ReleaseAVassal.releaseVassalData.changeGovernment ? Images.v : Images.x);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("GovernmentChange"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.government, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Active_Click(Game.lang.get("DemandReligionConversion"), InGame_ReleaseAVassal.releaseVassalData.changeReligion ? Images.v : Images.x, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, ImageManager.getImage(Images.advantages).getWidth() + CFG.PADDING * 2, 0) {
            @Override
            public void actionElement() {
                InGame_ReleaseAVassal.releaseVassalData.changeReligion = !InGame_ReleaseAVassal.releaseVassalData.changeReligion;
                this.setImageID(InGame_ReleaseAVassal.releaseVassalData.changeReligion ? Images.v : Images.x);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("DemandReligionConversion"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.religion, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Active_Click(Game.lang.get(Game_Ages.getLiberateAVassal()), InGame_ReleaseAVassal.releaseVassalData.liberateVassal ? Images.v : Images.x, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, ImageManager.getImage(Images.advantages).getWidth() + CFG.PADDING * 2, 0) {
            @Override
            public void actionElement() {
                InGame_ReleaseAVassal.releaseVassalData.liberateVassal = !InGame_ReleaseAVassal.releaseVassalData.liberateVassal;
                this.setImageID(InGame_ReleaseAVassal.releaseVassalData.liberateVassal ? Images.v : Images.x);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(Game_Ages.getLiberateAVassal()), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.council, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Active_Click(Game.lang.get(Game_Ages.getPlayAsAReleasedVassal()), InGame_ReleaseAVassal.releaseVassalData.playAsVassal ? Images.v : Images.x, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, ImageManager.getImage(Images.advantages).getWidth() + CFG.PADDING * 2, 0) {
            @Override
            public void actionElement() {
                InGame_ReleaseAVassal.releaseVassalData.playAsVassal = !InGame_ReleaseAVassal.releaseVassalData.playAsVassal;
                this.setImageID(InGame_ReleaseAVassal.releaseVassalData.playAsVassal ? Images.v : Images.x);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(Game_Ages.getPlayAsAReleasedVassal()), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.vassal, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame_Image(Game.lang.get(Game_Ages.getReleaseAVassal()), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2, true, Images.vassal) {
            @Override
            public void actionElement() {
                if (InGame_ReleaseAVassal.releaseVassalData.lProvinces.size() > 0) {
                    if (Game.getCiv(InGame_ReleaseAVassal.releaseVassalData.iCivID).getNumOfProvinces() > 0) {
                        Game.menuManager.addToast_Error(Game.getCiv(InGame_ReleaseAVassal.releaseVassalData.iCivID).getCivName() + ": " + Game.lang.get("Provinces") + ": " + Game.getCiv(InGame_ReleaseAVassal.releaseVassalData.iCivID).getNumOfProvinces(), Images.provinces);
                        return;
                    }
                    for (int i = InGame_ReleaseAVassal.releaseVassalData.lProvinces.size() - 1; i >= 0; --i) {
                        if (Game.getProvince(InGame_ReleaseAVassal.releaseVassalData.lProvinces.get(i)).getCivID() == InGame_ReleaseAVassal.releaseVassalData.iLordID) {
                            Game.getProvince(InGame_ReleaseAVassal.releaseVassalData.lProvinces.get(i)).setCivID(InGame_ReleaseAVassal.releaseVassalData.iCivID);
                        }
                    }
                    if (InGame_ReleaseAVassal.releaseVassalData.liberateVassal) {
                        Game.getCiv(InGame_ReleaseAVassal.releaseVassalData.iCivID).setPuppetOfCivID(InGame_ReleaseAVassal.releaseVassalData.iCivID);
                    }
                    else {
                        Game.getCiv(InGame_ReleaseAVassal.releaseVassalData.iCivID).setPuppetOfCivID(InGame_ReleaseAVassal.releaseVassalData.iLordID);
                    }
                    if (Game.getCiv(InGame_ReleaseAVassal.releaseVassalData.iCivID).fGold < 25.0f) {
                        Game.getCiv(InGame_ReleaseAVassal.releaseVassalData.iCivID).fGold = 25.0f;
                    }
                    if (Game.getCiv(InGame_ReleaseAVassal.releaseVassalData.iCivID).fLegacy < 25.0f) {
                        Game.getCiv(InGame_ReleaseAVassal.releaseVassalData.iCivID).fLegacy = 25.0f;
                    }
                    if (Game.getCiv(InGame_ReleaseAVassal.releaseVassalData.iCivID).fDiplomacy < 25.0f) {
                        Game.getCiv(InGame_ReleaseAVassal.releaseVassalData.iCivID).fDiplomacy = 25.0f;
                    }
                    try {
                        for (int i = 0; i < Game.getCiv(InGame_ReleaseAVassal.releaseVassalData.iLordID).lTechResearched.size(); ++i) {
                            if (Game.getCiv(InGame_ReleaseAVassal.releaseVassalData.iLordID).lTechResearched.get(i) && !Game.getCiv(InGame_ReleaseAVassal.releaseVassalData.iCivID).getTechResearched(i)) {
                                Game.getCiv(InGame_ReleaseAVassal.releaseVassalData.iLordID).addTechnology(i, true);
                            }
                        }
                    }
                    catch (final Exception ex) {}
                    if (InGame_ReleaseAVassal.releaseVassalData.changeReligion && Game.getCiv(InGame_ReleaseAVassal.releaseVassalData.iCivID).getReligionID() != Game.getCiv(InGame_ReleaseAVassal.releaseVassalData.iLordID).getReligionID()) {
                        Game.getCiv(InGame_ReleaseAVassal.releaseVassalData.iCivID).setReligionID_UpdateBonuses(Game.getCiv(InGame_ReleaseAVassal.releaseVassalData.iLordID).getReligionID());
                    }
                    if (InGame_ReleaseAVassal.releaseVassalData.changeGovernment && Game.getCiv(InGame_ReleaseAVassal.releaseVassalData.iCivID).getIdeologyID() != Game.getCiv(InGame_ReleaseAVassal.releaseVassalData.iLordID).getIdeologyID()) {
                        Game.addSimpleTask(new Game.SimpleTask("changeGovernmentType" + InGame_ReleaseAVassal.releaseVassalData.iCivID + "_" + Game.getCiv(InGame_ReleaseAVassal.releaseVassalData.iLordID).getIdeologyID(), InGame_ReleaseAVassal.releaseVassalData.iCivID, Game.getCiv(InGame_ReleaseAVassal.releaseVassalData.iLordID).getIdeologyID()) {
                            @Override
                            public void update() {
                                Game.ideologiesManager.changeGovernmentType(this.id, this.id2, true);
                                final Civilization civ = Game.getCiv(this.id);
                                civ.fGold += GameValues.government.CHANGE_GOVERNMENT_COST;
                                final Civilization civ2 = Game.getCiv(this.id);
                                civ2.fLegacy += GameValues.government.CHANGE_GOVERNMENT_COST_LEGACY;
                            }
                        });
                    }
                    if (Game.getCiv(InGame_ReleaseAVassal.releaseVassalData.iCivID).getCapitalProvinceID() < 0 || Game.getProvince(Game.getCiv(InGame_ReleaseAVassal.releaseVassalData.iCivID).getCapitalProvinceID()).getCivID() != InGame_ReleaseAVassal.releaseVassalData.iCivID) {
                        Game.getCiv(InGame_ReleaseAVassal.releaseVassalData.iCivID).moveCapital_ToLargestProvince();
                    }
                    else if (Game.getCiv(InGame_ReleaseAVassal.releaseVassalData.iCivID).getCapitalProvinceID() >= 0 && Game.getProvince(Game.getCiv(InGame_ReleaseAVassal.releaseVassalData.iCivID).getCapitalProvinceID()).getCivID() == InGame_ReleaseAVassal.releaseVassalData.iCivID) {
                        Game.getProvince(Game.getCiv(InGame_ReleaseAVassal.releaseVassalData.iCivID).getCapitalProvinceID()).setIsCapital(true);
                    }
                    if (InGame_ReleaseAVassal.releaseVassalData.playAsVassal) {
                        Game.player.iCivID = InGame_ReleaseAVassal.releaseVassalData.iCivID;
                        NewGame.clearPlayerData();
                        Game.player.currSituation.updateCurrentSituation();
                        ProvinceDrawArmy.updateArmyImgID();
                        Game.menuManager.rebuildInGame_Right();
                        Game.menuManager.rebuildInGame_Wars();
                        Game.menuManager.rebuildInGame_Messages();
                        Game.menuManager.rebuildInGame_Notifications();
                    }
                    CivilizationRanking.buildCivilizationRanking();
                    InGame_Info.iCivID = InGame_ReleaseAVassal.releaseVassalData.iLordID;
                    InGame_Info.iCivID2 = InGame_ReleaseAVassal.releaseVassalData.iCivID;
                    Game.menuManager.rebuildInGame_Info(InGame_ReleaseAVassal.releaseVassalData.liberateVassal ? Game.lang.get("Liberation") : Game.lang.get(Game_Ages.getVassal()), Game.getCiv(InGame_ReleaseAVassal.releaseVassalData.iCivID).getCivName());
                    InGame_Info.imgID = Images.infoCrown;
                    Game.menuManager.setVisibleInGame_CurrentSituation(false);
                    if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_RELEASE_VASSAL) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                    }
                    if (!Game.menuManager.getVisibleInGame_Court()) {
                        InGame.action1();
                    }
                }
                else {
                    Game.menuManager.addToast_Error(Game.lang.get("Provinces") + ": " + InGame_ReleaseAVassal.releaseVassalData.lProvinces.size(), Images.provinces);
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(Game_Ages.getReleaseAVassal()), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.vassal, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Provinces"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final int r0W0 = (int)((menuWidth - paddingLeft * 2 - CFG.PADDING * 2) * 0.25f);
        final int r0W2 = (int)((menuWidth - paddingLeft * 2 - CFG.PADDING * 2) * 0.5f);
        final int buttonHProvince = CFG.isDesktop() ? CFG.BUTTON_HEIGHT3 : CFG.BUTTON_HEIGHT2;
        menuElements.add(new ButtonStatsRectIMG_Active_Click(Game.lang.get("Reset"), Images.provinces, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, ImageManager.getImage(Images.advantages).getWidth() + CFG.PADDING * 2, 0) {
            @Override
            public void actionElement() {
                for (int i = 0; i < InGame_ReleaseAVassal.releaseVassalData.lProvinces.size(); ++i) {
                    Game.getProvince(InGame_ReleaseAVassal.releaseVassalData.lProvinces.get(i)).peaceTreatyIsTaken = false;
                }
                InGame_ReleaseAVassal.releaseVassalData.lProvinces.clear();
                Game.menuManager.addToastPositive("", Game.lang.get("Done"), Images.v);
                Game.menuManager.rebuildInGame_ReleaseAVassal_SavePos();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Reset"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.provinces, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        for (int j = 0; j < InGame_ReleaseAVassal.releaseVassalData.lProvinces.size(); ++j) {
            menuElements.add(new Text_StaticBG_ID_Flag(Game.getProvince(InGame_ReleaseAVassal.releaseVassalData.lProvinces.get(j)).getProvinceName(), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, buttonX, buttonY, r0W2, buttonH, (int)InGame_ReleaseAVassal.releaseVassalData.lProvinces.get(j)) {
                @Override
                public int getFlagID() {
                    return InGame_ReleaseAVassal.releaseVassalData.iCivID;
                }
                
                @Override
                public void actionElement() {
                    Game.mapCoords.centerToProvinceID(this.getCurrent());
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("CenterToProvince"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.center, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            menuElements.get(menuElements.size() - 1).setTypeOfElement(MenuElement_Type.BUTTON);
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new Text_StaticBG_ID_Special(CFG.getNumberWithSpaces("" + Game.getProvince(InGame_ReleaseAVassal.releaseVassalData.lProvinces.get(j)).getPopulationTotal()), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r0W0, buttonH, (int)InGame_ReleaseAVassal.releaseVassalData.lProvinces.get(j)) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", this.getText(), Images.provinces, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
                
                @Override
                protected Color getColor(final boolean isActive) {
                    return Colors.getColorPopulation(isActive, this.getIsHovered());
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new Text_StaticBG_ID_Special(Game.lang.get("Remove"), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r0W0, buttonH, (int)InGame_ReleaseAVassal.releaseVassalData.lProvinces.get(j)) {
                @Override
                public void actionElement() {
                    InGame_ReleaseAVassal.removeProvince(this.getCurrent());
                    Game.getProvince(this.getCurrent()).peaceTreatyIsTaken = false;
                    Game.menuManager.rebuildInGame_ReleaseAVassal_SavePos();
                    Game.menuManager.addToastPositive("", Game.lang.get("Removed"), Images.x);
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Remove"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.x, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            buttonX = paddingLeft;
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG(Game.lang.get(Game_Ages.getVassal()), false, false, Images.title1Red) {
            @Override
            public long getTime() {
                return InGame_ReleaseAVassal.lTime;
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, true, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_ReleaseAVassal.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_ReleaseAVassal.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false);
        ImageManager.getImage(Images.rulerOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.rulerOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.rulerOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_ReleaseAVassal.lTime = CFG.currentTimeMillis;
        if (!visible) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        }
    }
    
    @Override
    public void actionCloseMenu() {
        Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        super.actionCloseMenu();
    }
    
    static {
        InGame_ReleaseAVassal.lTime = 0L;
        InGame_ReleaseAVassal.releaseVassalData = new ReleaseVassalData();
    }
    
    public static class ReleaseVassalData
    {
        public int iLordID;
        public int iCivID;
        public List<Integer> lProvinces;
        public boolean playAsVassal;
        public boolean changeGovernment;
        public boolean changeReligion;
        public boolean liberateVassal;
        
        public ReleaseVassalData() {
            this.lProvinces = new ArrayList<Integer>();
            this.playAsVassal = false;
            this.changeGovernment = true;
            this.changeReligion = true;
            this.liberateVassal = false;
        }
        
        public ReleaseVassalData(final int iLordID, final int iCivID) {
            this.lProvinces = new ArrayList<Integer>();
            this.playAsVassal = false;
            this.changeGovernment = true;
            this.changeReligion = true;
            this.liberateVassal = false;
            this.iLordID = iLordID;
            this.iCivID = iCivID;
        }
    }
}
