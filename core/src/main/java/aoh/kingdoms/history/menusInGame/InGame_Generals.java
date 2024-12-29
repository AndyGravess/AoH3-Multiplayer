// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import aoh.kingdoms.history.menusInGame.Info.InGame_Info;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageFull;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Center;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG;
import aoh.kingdoms.history.map.province.ProvinceTouchExtraAction;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Clear;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_RulerTitle_General;
import aoh.kingdoms.history.menu_element.button.ButtonArmyGeneral_Assign;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import aoh.kingdoms.history.menu_element.button.ButtonGame;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Active_Click;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Generals extends Menu
{
    public static int assignProvinceID;
    public static String assignArmyKey;
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static boolean backButton;
    
    public InGame_Generals() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title2).getHeight();
        final int menuWidth = ImageManager.getImage(Images.insideTop).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        final int buttonYPadding = CFG.PADDING * 2;
        int buttonY = 0;
        int buttonX = paddingLeft;
        final int leftW = (menuWidth - paddingLeft * 2 - CFG.PADDING) * 3 / 5;
        final int rightW = (menuWidth - paddingLeft * 2 - CFG.PADDING) * 2 / 5;
        final int buttonH = CFG.isDesktop() ? CFG.BUTTON_HEIGHT3 : CFG.BUTTON_HEIGHT2;
        final int maxIconWidth = ImageManager.getImage(Game_Calendar.IMG_MANPOWER).getWidth();
        buttonY += CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Active_Click(InGame_Generals.backButton ? Game.lang.get("Back") : Game.lang.get("Close"), InGame_Generals.backButton ? Game_Calendar.IMG_MANPOWER : Images.x, buttonX, buttonY, leftW, buttonH, maxIconWidth, 0, true) {
            @Override
            public void actionElement() {
                if (InGame_Generals.backButton) {
                    InGame.action3();
                }
                else {
                    Game.menuManager.setVisibleInGame_Generals(false);
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                if (InGame_Generals.backButton) {
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Back"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                }
                else {
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Close"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.x, CFG.PADDING, 0));
                }
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Active_Click("" + Game.getCiv(Game.player.iCivID).getTotalGenerals(), Images.general, buttonX + leftW + CFG.PADDING, buttonY, rightW, buttonH, maxIconWidth, 0) {
            @Override
            public void actionElement() {
                if (Game.menuManager.getVisibleInGame_GeneralRecruit()) {
                    Game.menuManager.setVisibleInGame_GeneralRecruit(false);
                }
                else {
                    Game.menuManager.rebuildInGame_GeneralRecruit();
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("RecruitGeneral"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.general, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Generals") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).getTotalGenerals(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.general, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("UnassignedGenerals") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).getGeneralsNotAssignedSize(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.general, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MilitaryAcademyForGenerals"), CFG.FONT_BOLD_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.general, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Level") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(Game.player.iCivID).getMilitaryAcademyForGeneralsLevel() + " / " + Game.getMilitaryAcademyForGenerals_MaxLvl(Game.player.iCivID), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        int statsY;
        buttonY = (statsY = buttonY + (menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING));
        final int maxIconW = ImageManager.getImage(Images.gold).getWidth();
        buttonX = paddingLeft;
        final int buttonGeneralWidth = (menuWidth - paddingLeft * 2 - CFG.PADDING * 2) / 2;
        menuElements.add(new ButtonGame(Game.lang.get("RecruitGeneral"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true) {
            @Override
            public void actionElement() {
                if (Game.menuManager.getVisibleInGame_GeneralRecruit()) {
                    Game.menuManager.setVisibleInGame_GeneralRecruit(false);
                }
                else {
                    Game.menuManager.rebuildInGame_GeneralRecruit();
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("RecruitGeneral"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.general, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        boolean assignedGenerals = false;
        for (int i = 0; i < Game.getCiv(Game.player.iCivID).iArmyPositionSize; ++i) {
            for (int j = 0; j < Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmySize(); ++j) {
                if (Game.player.iCivID == Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(j).civID && Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(j).armyGeneral != null) {
                    assignedGenerals = true;
                    break;
                }
            }
            if (assignedGenerals) {
                break;
            }
        }
        if (Game.getCiv(Game.player.iCivID).iGeneralsSize == 0 && !assignedGenerals) {
            menuElements.add(new Text_StaticBG(Game.lang.get("NoGenerals") + ".", CFG.FONT_REGULAR_SMALL, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, ImageManager.getImage(Images.generalFrameBattle).getHeight() + CFG.TEXT_HEIGHT + CFG.PADDING * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        }
        menuElements.add(new Text_Title_v2(Game.lang.get("UnassignedGenerals"), CFG.FONT_BOLD, CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        if (Game.getCiv(Game.player.iCivID).iGeneralsSize > 0) {
            for (int i = 0; i < Game.getCiv(Game.player.iCivID).iGeneralsSize; ++i) {
                menuElements.add(new ButtonArmyGeneral_Assign(buttonX, buttonY, buttonGeneralWidth, Game.getCiv(Game.player.iCivID).getGeneralNotAssigned(i).n, Game.player.iCivID, Game.getCiv(Game.player.iCivID).getGeneralNotAssigned(i).getAttack(), Game.getCiv(Game.player.iCivID).getGeneralNotAssigned(i).getDefense(), Game.getCiv(Game.player.iCivID).getGeneralNotAssigned(i).g, Game.getCiv(Game.player.iCivID).getGeneralNotAssigned(i).d, Game.getCiv(Game.player.iCivID).getGeneralNotAssigned(i).m, Game.getCiv(Game.player.iCivID).getGeneralNotAssigned(i).y, false, Game.getCiv(Game.player.iCivID).getGeneralNotAssigned(i).key, Game.getCiv(Game.player.iCivID).getGeneralNotAssigned(i).sI, Game.getCiv(Game.player.iCivID).getGeneralNotAssigned(i).getCombatExperience()));
                if (i % 2 == 1) {
                    buttonX = paddingLeft;
                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                }
                else {
                    buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                }
            }
        }
        else {
            menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        buttonY = menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        if (assignedGenerals) {
            menuElements.add(new Text_Title_v2(Game.lang.get("GeneralsCommandingArmies"), CFG.FONT_BOLD, CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        }
        buttonX = paddingLeft;
        int i = 0;
        int addedGenerals = 0;
        while (i < Game.getCiv(Game.player.iCivID).iArmyPositionSize) {
            for (int k = 0; k < Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmySize(); ++k) {
                if (Game.player.iCivID == Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(k).civID && Game.getCiv(Game.player.iCivID).getArmyPositionKey(i).equals(Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(k).key) && Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(k).armyGeneral != null) {
                    menuElements.add(new ButtonArmyGeneral_Assign(buttonX, buttonY, buttonGeneralWidth, Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(k).armyGeneral.n, Game.player.iCivID, Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(k).armyGeneral.getAttack(), Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(k).armyGeneral.getDefense(), Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(k).armyGeneral.g, Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(k).armyGeneral.d, Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(k).armyGeneral.m, Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(k).armyGeneral.y, false, Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(k).armyGeneral.key, Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(k).armyGeneral.sI, Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(k).armyGeneral.getCombatExperience()));
                    buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                    final int statW2 = menuWidth - buttonX - paddingLeft;
                    final int statH2 = (ButtonArmyGeneral_Assign.getButtonHeight() - CFG.PADDING * 2) / 3;
                    statsY = buttonY;
                    menuElements.add(new Text_StaticBG_RulerTitle_General("" + Game.getProvince(Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(k).provinceID).getProvinceName(), buttonX, statsY, statW2, statH2, Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(k).key, Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(k).provinceID, Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(k).civID) {
                        @Override
                        public void buildElementHover() {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Army") + ": ", CFG.FONT_BOLD));
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear("" + this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements, true);
                        }
                        
                        @Override
                        public void actionElement() {
                            int nArmyID = Game.getProvince(this.iProvinceID).getArmyKeyID(this.key);
                            if (nArmyID < 0) {
                                for (int i = 0; i < Game.getProvincesSize(); ++i) {
                                    final int outID = Game.getProvince(i).getArmyKeyID(this.key, this.iCivID);
                                    if (outID >= 0) {
                                        this.iProvinceID = i;
                                        nArmyID = outID;
                                        break;
                                    }
                                }
                            }
                            if (nArmyID >= 0) {
                                if (Game.activeArmySize == 1 && Game.activeArmy.get(0).key.equals(Game.getProvince(this.iProvinceID).getArmy(nArmyID).key)) {
                                    Game.mapCoords.centerToProvinceID(this.iProvinceID);
                                }
                                else {
                                    Game.clearActiveArmy();
                                    final Game.HoveredArmy nHA = new Game.HoveredArmy();
                                    nHA.key = Game.getProvince(this.iProvinceID).getArmy(nArmyID).key;
                                    nHA.iCivID = Game.getProvince(this.iProvinceID).getArmy(nArmyID).civID;
                                    nHA.iProvinceID = this.iProvinceID;
                                    nHA.iArmyID = nArmyID;
                                    Game.addActiveArmy(nHA);
                                    ProvinceTouchExtraAction.actionUp_SetActiveArmy();
                                    if (!Game.getProvince(this.iProvinceID).getDrawProvince()) {
                                        Game.mapCoords.centerToProvinceID(this.iProvinceID);
                                    }
                                }
                                if (Game.menuManager.getVisibleInGame_Generals()) {
                                    Game.menuManager.setVisibleInGame_Generals(false);
                                }
                                if (Game.menuManager.getVisibleInGame_GeneralRecruit()) {
                                    Game.menuManager.setVisibleInGame_GeneralRecruit(false);
                                }
                            }
                        }
                    });
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                    menuElements.add(new ButtonStatsRectIMG(CFG.getNumberWithSpaces("" + Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(k).iArmy), Game_Calendar.IMG_MANPOWER, buttonX, statsY, statW2, statH2, maxIconW) {
                        @Override
                        public void buildElementHover() {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ArmySize") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + this.getText(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                    });
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                    menuElements.add(new ButtonStatsRectIMG(CFG.getPrecision2(Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmy(k).fMaintenanceCost, 100), Images.gold, buttonX, statsY, statW2, statH2, maxIconW) {
                        @Override
                        public void buildElementHover() {
                            final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ArmyMaintenance") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + this.getText(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.armyMaintenance, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                    });
                    statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                    buttonX = paddingLeft;
                    buttonY += ButtonArmyGeneral_Assign.getButtonHeight() + CFG.PADDING;
                    ++addedGenerals;
                }
            }
            ++i;
        }
        if (menuElements.size() > 0) {
            buttonY = menuElements.get(menuElements.size() - 1).getPosY() + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        }
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG("", false, false, Images.title1Red) {
            @Override
            public long getTime() {
                return InGame_Generals.lTime;
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
        this.drawScrollPositionAlways = false;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_Generals.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_Generals.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false);
        ImageManager.getImage(Images.recruitArmyOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.recruitArmyOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.recruitArmyOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void updateLanguage() {
        super.updateLanguage();
        this.getTitle().setText(Game.lang.get("Generals"));
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_Generals.lTime = CFG.currentTimeMillis;
    }
    
    public static MenuElement_Hover getHoverMilitaryAcademyForGenerals() {
        return getHoverMilitaryAcademyForGenerals(true, Game.player.iCivID);
    }
    
    public static MenuElement_Hover getHoverMilitaryAcademyForGenerals(final boolean showUpgrade, final int iCivID) {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.lang.get("MilitaryAcademyForGenerals"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_ImageFull(Images.armyGeneralAcademyBig, 0, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Level") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(iCivID).getMilitaryAcademyForGeneralsLevel() + " / " + Game.getMilitaryAcademyForGenerals_MaxLvl(iCivID), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GeneralsAttack") + ": ", ((Game.getMilitaryAcademyForGenerals_GeneralAttack(iCivID) > 0) ? "+" : "") + CFG.getPrecision2((float)Game.getMilitaryAcademyForGenerals_GeneralAttack(iCivID), 100), Images.attack, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (Game.getMilitaryAcademyForGenerals_GeneralAttack(iCivID) > 0) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.HOVER_RIGHT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GeneralsDefense") + ": ", ((Game.getMilitaryAcademyForGenerals_GeneralDefense(iCivID) > 0) ? "+" : "") + CFG.getPrecision2((float)Game.getMilitaryAcademyForGenerals_GeneralDefense(iCivID), 100), Images.defense, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (Game.getMilitaryAcademyForGenerals_GeneralDefense(iCivID) > 0) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.HOVER_RIGHT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaintenanceCost") + ": ", "" + CFG.getPrecision2(Game.getMilitaryAcademyForGenerals_MaintenanceCost(iCivID), 100), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, (Game.getMilitaryAcademyForGenerals_MaintenanceCost(iCivID) > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_NEGATIVE : Colors.HOVER_RIGHT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (showUpgrade) {
            if (Game.getCiv(iCivID).getMilitaryAcademyForGeneralsLevel() >= Game.getMilitaryAcademyForGenerals_MaxLvl(iCivID)) {
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaximumLevel"), CFG.FONT_BOLD_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.general, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            else {
                nData.add(new MenuElement_HoverElement_Type_Empty());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("UpgradeMilitaryAcademyForGenerals"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.general, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(Game.getMilitaryAcademyForGenerals_Cost(iCivID), 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("GeneralsAttackPerLevel") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2((float)GameValues.militaryAcademy.MILITARY_ACADEMY_FOR_GENERALS_ATTACK_PER_LVL, 100), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.attack, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("GeneralsDefensePerLevel") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2((float)GameValues.militaryAcademy.MILITARY_ACADEMY_FOR_GENERALS_DEFENSE_PER_LVL, 100), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.defense, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaintenanceCost") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(GameValues.militaryAcademy.MILITARY_ACADEMY_FOR_GENERALS_MAINTENANCE_COST_PER_LVL, 100), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
        }
        return new MenuElement_Hover(nElements);
    }
    
    public static final void upgradeMilitaryAcademyForGenerals() {
        if (Game.getCiv(Game.player.iCivID).getMilitaryAcademyForGeneralsLevel() >= Game.getMilitaryAcademyForGenerals_MaxLvl(Game.player.iCivID)) {
            Game.menuManager.addToastInsufficient(Game.lang.get("MaximumLevel") + ": ", "" + Game.getCiv(Game.player.iCivID).getMilitaryAcademyForGeneralsLevel() + " / " + Game.getMilitaryAcademyForGenerals_MaxLvl(Game.player.iCivID), Images.general);
        }
        else if (Game.getCiv(Game.player.iCivID).fGold < Game.getMilitaryAcademyForGenerals_Cost(Game.player.iCivID)) {
            Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getMilitaryAcademyForGenerals_Cost(Game.player.iCivID), 100), Images.gold);
        }
        else {
            Game.getCiv(Game.player.iCivID).upgradeMilitaryAcademyForGenerals();
            if (Game.menuManager.getVisibleInGame_Generals()) {
                Game.menuManager.rebuildInGame_Generals();
                Game.menuManager.setVisibleInGame_Generals(true);
                InGame_Generals.lTime = 0L;
            }
            InGame_Info.iCivID = Game.player.iCivID;
            InGame_Info.iCivID2 = 0;
            Game.menuManager.rebuildInGame_Info(Game.lang.get("MilitaryAcademyForGenerals"), Game.lang.get("Level") + ": " + Game.getCiv(Game.player.iCivID).getMilitaryAcademyForGeneralsLevel() + " / " + Game.getMilitaryAcademyForGenerals_MaxLvl(Game.player.iCivID));
            InGame_Info.imgID = Images.infoGeneral;
            Game.getCiv(Game.player.iCivID).updateTotalIncomePerMonth();
        }
    }
    
    static {
        InGame_Generals.assignProvinceID = 0;
        InGame_Generals.assignArmyKey = "";
        InGame_Generals.lTime = 0L;
        InGame_Generals.backButton = false;
    }
}
