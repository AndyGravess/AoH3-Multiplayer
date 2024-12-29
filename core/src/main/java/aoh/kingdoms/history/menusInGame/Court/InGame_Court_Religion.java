// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Court;

import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Active_Value;
import aoh.kingdoms.history.menu.MenuManager;
import aoh.kingdoms.history.menu.ClickAnimation;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Religion;
import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceInfo;
import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.textStatic.Text_TitleBlueSort;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Active_Click;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menus.Dialog;
import aoh.kingdoms.history.menu_element.button.ButtonGame;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonusReligion;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import aoh.kingdoms.history.menu_element.button.ButtonReligion2;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Court_Religion extends Menu
{
    public static int iSortID;
    public int iNumOfProvinces;
    
    public InGame_Court_Religion() {
        this.iNumOfProvinces = 0;
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING;
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX();
        int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING;
        final int buttonYPadding = CFG.PADDING * 2;
        int buttonX = Images.boxTitleBORDERWIDTH;
        int buttonY = CFG.PADDING;
        final int buttonH = CFG.isDesktop() ? CFG.BUTTON_HEIGHT3 : CFG.BUTTON_HEIGHT2;
        int r0W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.3f);
        int r1W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2) * 0.2f);
        int tConverted = 0;
        int tConverted_OfAll = 0;
        for (int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
            if (!Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).isOccupied()) {
                if (Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).getReligion() == Game.getCiv(Game.player.iCivID).getReligionID()) {
                    ++tConverted;
                }
                ++tConverted_OfAll;
            }
        }
        menuElements.add(new ButtonReligion2(Game.getCiv(Game.player.iCivID).getReligionID(), paddingLeft, buttonY, (int)((menuWidth - paddingLeft * 2 - CFG.PADDING) * 0.35f), CFG.BUTTON_HEIGHT) {
            @Override
            public void buildElementHover() {
                this.menuElementHover = Game.religionManager.getHoverReligion(this.religionID, Game.player.iCivID);
            }
        });
        menuElements.add(new Text_StaticBG(Game.lang.get("Provinces") + ": " + tConverted + " / " + tConverted_OfAll, CFG.FONT_REGULAR, -1, paddingLeft + CFG.PADDING + (int)((menuWidth - paddingLeft * 2 - CFG.PADDING) * 0.35f), buttonY, (int)((menuWidth - paddingLeft * 2 - CFG.PADDING) * 0.65f), CFG.BUTTON_HEIGHT) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonusReligion(Game.lang.get("Religion") + ": ", Game.religionManager.getReligion(Game.getCiv(Game.player.iCivID).getReligionID()).Name, Game.getCiv(Game.player.iCivID).getReligionID(), CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonusReligion(this.getText(), "", Game.getCiv(Game.player.iCivID).getReligionID(), CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame(Game.lang.get("ConvertReligion") + ": " + Game.lang.get("AllProvinces"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING) * 7 / 10, true) {
            @Override
            public void actionElement() {
                Dialog.setDialogType(Dialog.DialogType.CONVERT_RELIGION_ALL_PROVINCES);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                float fCost = 0.0f;
                int numOfProvinces = 0;
                for (int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                    if (!Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).isOccupied() && Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).religionConversion == null && Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).getReligion() != Game.getCiv(Game.player.iCivID).getReligionID()) {
                        ++numOfProvinces;
                        fCost += Game.religionManager.getReligionConversionCost(Game.getCiv(Game.player.iCivID).getProvinceID(i));
                    }
                }
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ConvertReligion") + ": " + Game.lang.get("AllProvinces"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Provinces") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + numOfProvinces, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.provinces, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(fCost, 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            public boolean getClickable() {
                return InGame_Court_Religion.this.iNumOfProvinces > 0;
            }
        });
        float tCost = 0.0f;
        for (int j = 0; j < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++j) {
            if (!Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(j)).isOccupied() && Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(j)).religionConversion == null && Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(j)).getReligion() != Game.getCiv(Game.player.iCivID).getReligionID()) {
                tCost += Game.religionManager.getReligionConversionCost(Game.getCiv(Game.player.iCivID).getProvinceID(j));
            }
        }
        menuElements.add(new ButtonStatsRectIMG_Active_Click("" + CFG.getNumberWithSpaces(CFG.getPrecision2((float)(int)Math.ceil(tCost), 1)), Images.gold, paddingLeft + CFG.PADDING + (menuWidth - paddingLeft * 2 - CFG.PADDING) * 7 / 10, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING) * 3 / 10, CFG.BUTTON_HEIGHT2, ImageManager.getImage(Images.gold).getWidth(), 0) {
            @Override
            public void actionElement() {
                Dialog.setDialogType(Dialog.DialogType.CONVERT_RELIGION_ALL_PROVINCES);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                float fCost = 0.0f;
                int numOfProvinces = 0;
                for (int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                    if (!Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).isOccupied() && Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).religionConversion == null && Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).getReligion() != Game.getCiv(Game.player.iCivID).getReligionID()) {
                        ++numOfProvinces;
                        fCost += Game.religionManager.getReligionConversionCost(Game.getCiv(Game.player.iCivID).getProvinceID(i));
                    }
                }
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ConvertReligion") + ": " + Game.lang.get("AllProvinces"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Provinces") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + numOfProvinces, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.provinces, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(fCost, 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            public boolean getClickable() {
                return InGame_Court_Religion.this.iNumOfProvinces > 0;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Text_TitleBlueSort(InGame_Court_Religion.iSortID == 0 || InGame_Court_Religion.iSortID == 1, InGame_Court_Religion.iSortID == 1, Game.lang.get("Name"), -1, buttonX, buttonY, r0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Court_Religion.iSortID == 0) {
                    InGame_Court_Religion.iSortID = 1;
                }
                else {
                    InGame_Court_Religion.iSortID = 0;
                }
                Game.menuManager.rebuildInGame_Religion();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Name"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(InGame_Court_Religion.iSortID == 2 || InGame_Court_Religion.iSortID == 3, InGame_Court_Religion.iSortID == 3, Game.lang.get("Religion"), -1, buttonX, buttonY, r0W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Court_Religion.iSortID == 2) {
                    InGame_Court_Religion.iSortID = 3;
                }
                else {
                    InGame_Court_Religion.iSortID = 2;
                }
                Game.menuManager.rebuildInGame_Religion();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Religion"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(InGame_Court_Religion.iSortID == 4 || InGame_Court_Religion.iSortID == 5, InGame_Court_Religion.iSortID == 5, Game.lang.get("ReligionConversionTime"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Court_Religion.iSortID == 4) {
                    InGame_Court_Religion.iSortID = 5;
                }
                else {
                    InGame_Court_Religion.iSortID = 4;
                }
                Game.menuManager.rebuildInGame_Religion();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("ReligionConversionTime"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth();
        menuElements.add(new Text_TitleBlueSort(InGame_Court_Religion.iSortID == 6 || InGame_Court_Religion.iSortID == 7, InGame_Court_Religion.iSortID == 7, Game.lang.get("Cost"), -1, buttonX, buttonY, r1W, CFG.TEXT_HEIGHT + CFG.PADDING * 6, CFG.FONT_BOLD_SMALL) {
            @Override
            public void drawLines(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
            }
            
            @Override
            public void actionElement() {
                if (InGame_Court_Religion.iSortID == 6) {
                    InGame_Court_Religion.iSortID = 7;
                }
                else {
                    InGame_Court_Religion.iSortID = 6;
                }
                Game.menuManager.rebuildInGame_Religion();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SortBy") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Cost"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        r0W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2 - CFG.PADDING * 5) * 0.3f);
        r1W = (int)((menuWidth - Images.boxTitleBORDERWIDTH * 2 - CFG.PADDING * 5) * 0.2f);
        final List<Integer> tProvinces = new ArrayList<Integer>();
        for (int k = 0; k < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++k) {
            if (!Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(k)).isOccupied() && Game.getCiv(Game.player.iCivID).getReligionID() != Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(k)).getReligion()) {
                tProvinces.add(Game.getCiv(Game.player.iCivID).getProvinceID(k));
            }
        }
        if (tProvinces.size() == 0) {
            menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        else {
            while (tProvinces.size() > 0) {
                int toAddID = 0;
                if (InGame_Court_Religion.iSortID == 0) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (CFG.compareAlphabetic_TwoString(Game.getProvince(tProvinces.get(toAddID)).getProvinceName(), Game.getProvince(tProvinces.get(o)).getProvinceName())) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_Religion.iSortID == 1) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (CFG.compareAlphabetic_TwoString(Game.getProvince(tProvinces.get(o)).getProvinceName(), Game.getProvince(tProvinces.get(toAddID)).getProvinceName())) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_Religion.iSortID == 2) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (CFG.compareAlphabetic_TwoString(Game.religionManager.getReligion(Game.getProvince(tProvinces.get(toAddID)).getReligion()).Name, Game.religionManager.getReligion(Game.getProvince(tProvinces.get(o)).getReligion()).Name)) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_Religion.iSortID == 3) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (CFG.compareAlphabetic_TwoString(Game.religionManager.getReligion(Game.getProvince(tProvinces.get(o)).getReligion()).Name, Game.religionManager.getReligion(Game.getProvince(tProvinces.get(toAddID)).getReligion()).Name)) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_Religion.iSortID == 4) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (Game.religionManager.getReligionConversionTime(tProvinces.get(toAddID)) > Game.religionManager.getReligionConversionTime(tProvinces.get(o))) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_Religion.iSortID == 5) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (Game.religionManager.getReligionConversionTime(tProvinces.get(o)) > Game.religionManager.getReligionConversionTime(tProvinces.get(toAddID))) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_Religion.iSortID == 6) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (Game.religionManager.getReligionConversionCost(tProvinces.get(toAddID)) > Game.religionManager.getReligionConversionCost(tProvinces.get(o))) {
                            toAddID = o;
                        }
                    }
                }
                else if (InGame_Court_Religion.iSortID == 7) {
                    for (int o = 1; o < tProvinces.size(); ++o) {
                        if (Game.religionManager.getReligionConversionCost(tProvinces.get(toAddID)) < Game.religionManager.getReligionConversionCost(tProvinces.get(o))) {
                            toAddID = o;
                        }
                    }
                }
                buttonX = paddingLeft;
                menuElements.add(new Text_StaticBG_ID(Game.getProvince(tProvinces.get(toAddID)).getProvinceName(), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, buttonX, buttonY, r0W, buttonH, (int)tProvinces.get(toAddID)) {
                    @Override
                    public void actionElement() {
                        if (Game.iActiveProvince == this.getCurrent()) {
                            Game.menuManager.hideCourtCiv();
                            Game.menuManager.setVisibleInGame_Civ(false);
                            Game.setActiveProvinceID(this.getCurrent());
                            Game.menuManager.rebuildInGame_ProvinceInfo(true);
                            ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                        }
                        else {
                            Game.mapCoords.centerToProvinceID(this.getCurrent());
                            Game.setActiveProvinceID(this.getCurrent());
                            ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                        }
                    }
                    
                    @Override
                    public void buildElementHover() {
                        this.menuElementHover = InGame_ProvinceInfo.getHoverPopulation(this.getCurrent(), false);
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new ButtonStatsRectIMG_Religion("" + Game.religionManager.getReligion(Game.getProvince(tProvinces.get(toAddID)).getReligion()).Name, Game.getProvince(tProvinces.get(toAddID)).getReligion(), buttonX, buttonY, r0W, buttonH, ImageManager.getImage(Images.religion).getWidth(), (int)tProvinces.get(toAddID)) {
                    @Override
                    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
                        super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
                        if (Game.getProvince(this.id).getReligion() != this.imageID) {
                            Game.addSimpleTask(new Game.SimpleTask("rebuildConvertReligion") {
                                @Override
                                public void update() {
                                    Game.menuManager.rebuildInGame_ReligionSavePos();
                                    Game.menuManager.setVisibleInGame_Court(true);
                                    InGame_Court.lTime = 0L;
                                }
                            });
                        }
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new Text_StaticBG_ID("" + CFG.getPrecision2((float)Game.religionManager.getReligionConversionTime(tProvinces.get(toAddID)), 10), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r1W, buttonH, (int)tProvinces.get(toAddID)) {
                    @Override
                    public void actionElement() {
                        if (Game.getProvince(this.getCurrent()).getCivID() == Game.player.iCivID && Game.getProvince(this.getCurrent()).religionConversion == null) {
                            if (!Game.getProvince(this.getCurrent()).addReligionConversion()) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2((float)Game.religionManager.getReligionConversionCost(this.getCurrent()), 100), Images.gold);
                            }
                            else {
                                final MenuManager menuManager = Game.menuManager;
                                MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Court_Religion.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Court_Religion.this.getMenuPosY(), this.getWidth(), this.getHeight()));
                            }
                        }
                    }
                    
                    @Override
                    public void buildElementHover() {
                        this.menuElementHover = InGame_ProvinceInfo.getHoverReligion(this.getCurrent(), false, true);
                    }
                });
                buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
                menuElements.add(new ButtonStatsRectIMG_Active_Value("" + CFG.getPrecision2((float)Game.religionManager.getReligionConversionCost(tProvinces.get(toAddID)), 100), Images.gold, buttonX, buttonY, r1W, buttonH, ImageManager.getImage(Images.gold).getWidth(), (int)tProvinces.get(toAddID)) {
                    @Override
                    public void actionElement() {
                        if (Game.getProvince(this.getCurrent()).getCivID() == Game.player.iCivID && Game.getProvince(this.getCurrent()).religionConversion == null) {
                            if (!Game.getProvince(this.getCurrent()).addReligionConversion()) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2((float)Game.religionManager.getReligionConversionCost(this.getCurrent()), 100), Images.gold);
                            }
                            else {
                                final MenuManager menuManager = Game.menuManager;
                                MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Court_Religion.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Court_Religion.this.getMenuPosY(), this.getWidth(), this.getHeight()));
                            }
                        }
                    }
                    
                    @Override
                    public void buildElementHover() {
                        this.menuElementHover = InGame_ProvinceInfo.getHoverReligion(this.getCurrent(), false, true);
                    }
                    
                    @Override
                    public String getTextToDraw() {
                        if (this.lastValue != Game.religionManager.getReligionConversionCost(this.id)) {
                            this.setText("" + CFG.getPrecision2((float)Game.religionManager.getReligionConversionCost(this.id), 100));
                            this.lastValue = (float)Game.religionManager.getReligionConversionCost(this.id);
                        }
                        return this.sText;
                    }
                });
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                if (Game.getProvince(tProvinces.get(toAddID)).religionConversion == null) {
                    ++this.iNumOfProvinces;
                }
                tProvinces.remove(toAddID);
            }
        }
        menuY += InGame_CourtOptions.menuH;
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(null, menuX, menuY, menuWidth, menuHeight, menuElements, false, false);
        this.drawScrollPositionAlways = false;
        Game.menuManager.setInGame_CivOptions_Title(Game.lang.get("ConvertReligion"));
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_Court.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_Court.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - InGame_CourtOptions.menuH + iTranslateY, this.getWidth(), this.getHeight() + InGame_CourtOptions.menuH + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.rulerOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.rulerOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.rulerOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_Court.lTime = CFG.currentTimeMillis;
        InGame_Court.lTime2 = CFG.currentTimeMillis;
    }
    
    @Override
    public void onHovered() {
        super.onHovered();
        Game.menuManager.setOrderOfMenu_InGameCourt();
    }
    
    static {
        InGame_Court_Religion.iSortID = 0;
    }
}
