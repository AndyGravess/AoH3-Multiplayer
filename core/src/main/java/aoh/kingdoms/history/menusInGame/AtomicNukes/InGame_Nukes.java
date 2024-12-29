// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.AtomicNukes;

import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoh.kingdoms.history.map.province.ProvinceInvest;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menusInGame.Info.InGame_Info;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageFull;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Center;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Active_Click;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_RulerTitle_Nukes;
import aoh.kingdoms.history.menu_element.button.Button_AtomicBomb;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Bonuses;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_RulerTitle;
import aoh.kingdoms.history.menu.MenuManager;
import aoh.kingdoms.history.menu_element.button.Button_NuclearReactor;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2Center;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Nukes extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    
    public InGame_Nukes() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title500).getHeight();
        final int buttonYPadding = CFG.PADDING * 2;
        int buttonY = 0;
        int buttonX = paddingLeft;
        menuElements.add(new Text_Title_v2Center(Game.lang.get("NuclearReactor"), -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
        int statsY;
        buttonY = (statsY = buttonY + (menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING));
        menuElements.add(new Button_NuclearReactor(paddingLeft, buttonY) {
            @Override
            public void actionElement() {
                if (Game.getCiv(Game.player.iCivID).getNuclearReactorLevel() >= Game.getNuclearReactor_MaxLvl(Game.player.iCivID)) {
                    Game.menuManager.addToastInsufficient(Game.lang.get("MaximumLevel") + ": ", "" + Game.getCiv(Game.player.iCivID).getNuclearReactorLevel() + " / " + Game.getNuclearReactor_MaxLvl(Game.player.iCivID), Images.nuke);
                }
                else if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 5) {
                    Game.menuManager.setVisibleInGame_PopUp(false);
                }
                else {
                    Game.menuManager.rebuildInGame_UpgradeNuclearReactor();
                }
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        int maxIconW = ImageManager.getImage(Images.gold).getWidth();
        int statW = menuWidth - buttonX - paddingLeft;
        int statH = (Button_NuclearReactor.getButtonHeight() - CFG.PADDING * 2) / 3;
        menuElements.add(new Text_StaticBG_RulerTitle(Game.lang.get("Level") + ": " + Game.getCiv(Game.player.iCivID).getNuclearReactorLevel() + " / " + Game.getNuclearReactor_MaxLvl(Game.player.iCivID), buttonX, statsY, statW, statH) {
            @Override
            public void actionElement() {
                if (Game.getCiv(Game.player.iCivID).getNuclearReactorLevel() >= Game.getNuclearReactor_MaxLvl(Game.player.iCivID)) {
                    Game.menuManager.addToastInsufficient(Game.lang.get("MaximumLevel") + ": ", "" + Game.getCiv(Game.player.iCivID).getNuclearReactorLevel() + " / " + Game.getNuclearReactor_MaxLvl(Game.player.iCivID), Images.nuke);
                }
                else if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 5) {
                    Game.menuManager.setVisibleInGame_PopUp(false);
                }
                else {
                    Game.menuManager.rebuildInGame_UpgradeNuclearReactor();
                }
            }
            
            @Override
            public void buildElementHover() {
                this.menuElementHover = InGame_Nukes.getHoverNuclearReactor();
            }
        });
        statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("AtomicBombCost") + ": ", CFG.getPrecision2(Game.getNuclearReactor_AtomicBombCost(Game.player.iCivID) * 100.0f, 100) + "%", Images.nuke, buttonX, statsY, statW, statH, maxIconW));
        statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("ProductionEfficiency") + ": ", "+" + CFG.getPrecision2(Game.getNuclearReactor_ProductionEfficiency(Game.player.iCivID), 100) + "%", Images.goods, buttonX, statsY, statW, statH, maxIconW));
        statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        buttonX = paddingLeft;
        menuElements.add(new Text_Title_v2Center(Game.lang.get("AtomicBomb"), -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
        buttonY = (statsY = buttonY + (menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING));
        menuElements.add(new Button_AtomicBomb(paddingLeft, buttonY) {
            @Override
            public void actionElement() {
                if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 6) {
                    Game.menuManager.setVisibleInGame_PopUp(false);
                }
                else {
                    Game.menuManager.rebuildInGame_BuildAtomicBomb();
                }
            }
        });
        buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        maxIconW = ImageManager.getImage(Images.gold).getWidth();
        statW = menuWidth - buttonX - paddingLeft;
        statH = (Button_NuclearReactor.getButtonHeight() - CFG.PADDING * 2) / 3;
        menuElements.add(new Text_StaticBG_RulerTitle_Nukes(Game.lang.get("AtomicBombs") + ": " + Game.getCiv(Game.player.iCivID).getNukes(), buttonX, statsY, statW, statH) {
            @Override
            public void actionElement() {
                if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 6) {
                    Game.menuManager.setVisibleInGame_PopUp(false);
                }
                else {
                    Game.menuManager.rebuildInGame_BuildAtomicBomb();
                }
            }
            
            @Override
            public void buildElementHover() {
                this.menuElementHover = InGame_Nukes.getHoverBuildAtomicBomb();
            }
        });
        statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Cost") + ": ", CFG.getPrecision2(Game.getAtomicBombCost(Game.player.iCivID), 100), Images.gold, buttonX, statsY, statW, statH, maxIconW) {
            @Override
            public Color getColorBonus() {
                return Colors.HOVER_RIGHT;
            }
            
            @Override
            public String getTextToDraw() {
                if (this.iCurrent != (int)Game.getAtomicBombCost(Game.player.iCivID)) {
                    this.iCurrent = (int)Game.getAtomicBombCost(Game.player.iCivID);
                    this.sText2 = CFG.getPrecision2(Game.getAtomicBombCost(Game.player.iCivID), 100);
                    Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_BOLD_SMALL), this.sText2);
                    this.iTextBonusW = (int)Renderer.glyphLayout.width;
                }
                return super.getTextToDraw();
            }
        });
        statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("ProductionTime") + ": ", Game.lang.get("DaysX", Game.getAtomicBombProductionTime(Game.player.iCivID)), Images.time, buttonX, statsY, statW, statH, maxIconW) {
            @Override
            public Color getColorBonus() {
                return Colors.HOVER_RIGHT;
            }
        });
        statsY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        buttonX = paddingLeft;
        menuElements.add(new Text_Title_v2Center(Game.lang.get("NuclearWeapons"), -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Active_Click(Game.lang.get("AtomicBombs") + ": ", Images.nuke, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT3, maxIconW, 0, true) {
            @Override
            public String getTextToDraw() {
                return super.getTextToDraw() + Game.getCiv(Game.player.iCivID).getNukes();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsRectIMG_Active_Click(Game.lang.get("DropAtomicBomb") + ": " + Game.lang.get("ChooseAProvince"), Images.nuke, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT3, maxIconW, 0, true) {
            @Override
            public void actionElement() {
                if (Game.getCiv(Game.player.iCivID).getNukes() <= 0) {
                    Game.menuManager.addToast_Error(Game.lang.get("AtomicBombs") + ": " + Game.getCiv(Game.player.iCivID).getNukes(), Images.nuke);
                }
                if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_NUKE_CHOOSE_PROVINCE) {
                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                }
                else {
                    Game.gameActiveProvince.resetLastActiveProvince();
                    Game.setActiveProvinceID(-1);
                    Game.menuManager.setVisibleInGame_ProvinceInfo(false);
                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_NUKE_CHOOSE_PROVINCE);
                }
            }
            
            @Override
            protected Color getColor(final boolean isActive) {
                if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_NUKE_CHOOSE_PROVINCE) {
                    return Colors.HOVER_GOLD;
                }
                return super.getColor(isActive);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("DropAtomicBomb") + ": " + Game.lang.get("ChooseAProvince"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.provinces, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AtomicBombs") + ": ", "" + Game.getCiv(Game.player.iCivID).getNukes(), Images.nuke, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG("", false, false, Images.title500) {
            @Override
            public long getTime() {
                return InGame_Nukes.lTime;
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_Nukes.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_Nukes.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.rulerOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.rulerOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.rulerOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void updateLanguage() {
        super.updateLanguage();
        this.getTitle().setText(Game.lang.get("NuclearWeapons"));
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_Nukes.lTime = CFG.currentTimeMillis;
        if (!visible && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_NUKE_CHOOSE_PROVINCE) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        }
    }
    
    @Override
    public void actionCloseMenu() {
        super.actionCloseMenu();
        if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_NUKE_CHOOSE_PROVINCE) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        }
    }
    
    public static MenuElement_Hover getHoverNuclearReactor() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.lang.get("NuclearReactor"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_ImageFull(Images.nuclearReactorBig, 0, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Level") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(Game.player.iCivID).getNuclearReactorLevel() + " / " + Game.getNuclearReactor_MaxLvl(Game.player.iCivID), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AtomicBombCost") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text(((Game.getNuclearReactor_AtomicBombCost(Game.player.iCivID) > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getNuclearReactor_AtomicBombCost(Game.player.iCivID) * 100.0f, 100) + "%", CFG.FONT_BOLD_SMALL, (Game.getNuclearReactor_AtomicBombCost(Game.player.iCivID) < 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.HOVER_RIGHT));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.nuke, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ProductionEfficiency") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text(((Game.getNuclearReactor_ProductionEfficiency(Game.player.iCivID) > 0.0f) ? "+" : "") + CFG.getPrecision2(Game.getNuclearReactor_ProductionEfficiency(Game.player.iCivID), 100) + "%", CFG.FONT_BOLD_SMALL, (Game.getNuclearReactor_ProductionEfficiency(Game.player.iCivID) > 0.0f) ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.HOVER_RIGHT));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.goods, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (Game.getCiv(Game.player.iCivID).getNuclearReactorLevel() >= Game.getNuclearReactor_MaxLvl(Game.player.iCivID)) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaximumLevel"), CFG.FONT_BOLD_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.nuke, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        else {
            nData.add(new MenuElement_HoverElement_Type_Empty());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("UpgradeNuclearReactor"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.nuke, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(Game.getNuclearReactor_Cost(Game.player.iCivID), 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AtomicBombCostPerLevel") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(GameValues.atomic.NUCLEAR_REACTOR_ATOMIC_BOMB_COST_PER_LEVEL * 100.0f, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.nuke, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ProductionEfficiencyPerLevel") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(GameValues.atomic.NUCLEAR_REACTOR_PRODUCTION_EFFICIENCY_PER_LVL, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.goods, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        return new MenuElement_Hover(nElements);
    }
    
    public static final void upgradeNuclearReactor() {
        if (Game.getCiv(Game.player.iCivID).getNuclearReactorLevel() >= Game.getNuclearReactor_MaxLvl(Game.player.iCivID)) {
            Game.menuManager.addToastInsufficient(Game.lang.get("MaximumLevel") + ": ", "" + Game.getCiv(Game.player.iCivID).getNuclearReactorLevel() + " / " + Game.getNuclearReactor_MaxLvl(Game.player.iCivID), Images.nuke);
        }
        else if (Game.getCiv(Game.player.iCivID).fGold < Game.getNuclearReactor_Cost(Game.player.iCivID)) {
            Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getNuclearReactor_Cost(Game.player.iCivID), 100), Images.gold);
        }
        else {
            Game.getCiv(Game.player.iCivID).upgradeNuclearReactor();
            if (Game.menuManager.getVisibleInGame_Nukes()) {
                Game.menuManager.rebuildInGame_Nukes();
                InGame_Nukes.lTime = 0L;
            }
            InGame_Info.iCivID = Game.player.iCivID;
            InGame_Info.iCivID2 = 0;
            Game.menuManager.rebuildInGame_Info(Game.lang.get("NuclearReactor"), Game.lang.get("Level") + ": " + Game.getCiv(Game.player.iCivID).getNuclearReactorLevel() + " / " + Game.getNuclearReactor_MaxLvl(Game.player.iCivID));
            InGame_Info.imgID = Images.infoCrown;
            Game.getCiv(Game.player.iCivID).updateTotalIncomePerMonth();
        }
    }
    
    public static MenuElement_Hover getHoverBuildAtomicBomb() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.lang.get("BuildAnAtomicBomb"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_ImageFull(Images.atomicBombBig, 0, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("AtomicBombs") + ": ", CFG.FONT_REGULAR));
        nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.getCiv(Game.player.iCivID).getNukes(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.nuke, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (Game.getCiv(Game.player.iCivID).iNukesSize > 0) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AtomicBombUnderConstruction") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DaysX", Game.getCiv(Game.player.iCivID).nukesDaysLeft.get(0).daysLeft), CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, CFG.PADDING));
            nData.add(new MenuElement_HoverElement_Type_Text("[" + CFG.getPrecision2((1.0f - Game.getCiv(Game.player.iCivID).nukesDaysLeft.get(0).daysLeft / (float)Game.getCiv(Game.player.iCivID).nukesDaysLeft.get(0).investTime) * 100.0f, 10) + "%]", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            if (Game.getCiv(Game.player.iCivID).iNukesSize > 1) {
                int tDays = 0;
                for (int i = 0; i < Game.getCiv(Game.player.iCivID).iNukesSize; ++i) {
                    tDays += Game.getCiv(Game.player.iCivID).nukesDaysLeft.get(i).daysLeft;
                }
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("InQueue") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + (Game.getCiv(Game.player.iCivID).iNukesSize - 1), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Text(" [" + Game.lang.get("DaysX", tDays) + "]", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, CFG.PADDING));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
        }
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AtomicBombCost") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(Game.getAtomicBombCost(Game.player.iCivID), 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.nuke, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ProductionTime") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("XDays", Game.getAtomicBombProductionTime(Game.player.iCivID)), CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (Game.getCiv(Game.player.iCivID).getNuclearReactorLevel() < GameValues.atomic.NUCLEAR_REACTOR_LVL_TO_CONSTRUCT_NUKE) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("ToConstructAnAtomicBombTheNuclearReactorMustReachAtLeastLevel", GameValues.atomic.NUCLEAR_REACTOR_LVL_TO_CONSTRUCT_NUKE), CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        return new MenuElement_Hover(nElements);
    }
    
    public static final void buildAtomicBomb() {
        if (Game.getCiv(Game.player.iCivID).getNuclearReactorLevel() < GameValues.atomic.NUCLEAR_REACTOR_LVL_TO_CONSTRUCT_NUKE) {
            Game.menuManager.addToastInsufficient("", Game.lang.get("ToConstructAnAtomicBombTheNuclearReactorMustReachAtLeastLevel", GameValues.atomic.NUCLEAR_REACTOR_LVL_TO_CONSTRUCT_NUKE), Images.nuke);
        }
        else if (Game.getCiv(Game.player.iCivID).fGold < Game.getAtomicBombCost(Game.player.iCivID)) {
            Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getAtomicBombCost(Game.player.iCivID), 100), Images.gold);
        }
        else {
            Game.getCiv(Game.player.iCivID).addNukeProduction();
            InGame_Info.iCivID = Game.player.iCivID;
            InGame_Info.iCivID2 = 0;
            Game.menuManager.rebuildInGame_Info(Game.lang.get("AtomicBombUnderConstruction"), Game.lang.get("ProductionTime") + ": " + Game.lang.get("XDays", Game.getAtomicBombProductionTime(Game.player.iCivID)));
            InGame_Info.imgID = Images.infoAtomic;
        }
    }
    
    static {
        InGame_Nukes.lTime = 0L;
    }
}
