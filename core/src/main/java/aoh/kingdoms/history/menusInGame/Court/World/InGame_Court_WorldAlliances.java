// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Court.World;

import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Iterator;
import aoh.kingdoms.history.map.map.Map_Data;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon_Diplomacy;
import aoh.kingdoms.history.map.diplomacy.Diplomacy;
import java.util.Map;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv_SpecialEmpty;
import aoh.kingdoms.history.menu_element.textStatic.Text_Static_ID;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_TitleSpecial_ID;
import aoh.kingdoms.history.map.allianceHRE.Alliance;
import aoh.kingdoms.history.menusInGame.AllianceSpecial.InGame_AllianceSpecial;
import aoh.kingdoms.history.menu.MenuManager;
import aoh.kingdoms.history.menu_element.button.ButtonFlag_Diplomacy;
import aoh.kingdoms.history.menu_element.button.ButtonFlag_AllianceSpecial;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2Center;
import aoh.kingdoms.history.mainGame.SoundsManager;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court;
import aoh.kingdoms.history.menu_element.button.ButtonCurrentSituation;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Court_WorldAlliances extends Menu
{
    public InGame_Court_WorldAlliances() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING;
        final int paddingLeft2 = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2;
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        final int menuX = CFG.PADDING * 2 + InGame_CourtOptions2.getMenuWidth();
        int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING;
        final int buttonYPadding = CFG.PADDING * 2;
        final int buttonX = paddingLeft;
        int buttonY = CFG.PADDING;
        menuElements.add(new ButtonCurrentSituation(Game.lang.get(GameValues.court.COUNCIL_NAME), Images.council, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.council).getWidth() + CFG.PADDING * 4, true) {
            @Override
            public void actionElement() {
                InGame_Court.iActiveCivID = Game.player.iCivID;
                Game.menuManager.rebuildInGame_Court();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
                Game.setRegroupArmyMode(false);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(GameValues.court.COUNCIL_NAME), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.council, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
            
            @Override
            public int getSFX() {
                return SoundsManager.getClickSound_CivOptions();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final int maxWidth = ImageManager.getImage(Images.alliance).getWidth() + CFG.PADDING * 4;
        final int tempTitlePaddingY = CFG.PADDING;
        final int tempTitleH = ImageManager.getImage(Images.flagDiplomacyOver).getHeight() + tempTitlePaddingY * 2;
        final int tempTextW = menuWidth / 2 - paddingLeft - CFG.PADDING * 2 - maxWidth / 2 - ImageManager.getImage(Images.flagDiplomacyOver).getWidth();
        menuElements.add(new Text_Title_v2Center(Game.lang.get("Alliances"), -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final int tElements = menuElements.size();
        final int allianceTitle = ButtonFlag_AllianceSpecial.getButtonHeight() - ButtonFlag_Diplomacy.getButtonHeight() - CFG.PADDING;
        for (int i = 0; i < Game.alliancesSpecialSize; ++i) {
            buttonY += CFG.PADDING * 2;
            menuElements.add(new ButtonFlag_AllianceSpecial(i, paddingLeft2, buttonY) {
                @Override
                public void actionElement() {
                    if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 35 && InGame_AllianceSpecial.allianceID == this.getCurrent()) {
                        Game.menuManager.setVisibleInGame_PopUp(false);
                    }
                    else {
                        Game.menuManager.hideCourtCiv();
                        Game.menuManager.rebuildInGame_AllianceSpecial(this.getCurrent());
                    }
                }
            });
            menuElements.add(new Text_StaticBG_TitleSpecial_ID(Game.lang.get(Game.alliancesSpecial.get(i).Name_Alliance), CFG.FONT_REGULAR, -1, paddingLeft2 + ButtonFlag_AllianceSpecial.getButtonWidth() + CFG.PADDING * 2, buttonY, menuWidth - (paddingLeft2 * 2 + ButtonFlag_AllianceSpecial.getButtonWidth() + CFG.PADDING * 2), allianceTitle, i) {
                @Override
                public void actionElement() {
                    if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 35 && InGame_AllianceSpecial.allianceID == this.getCurrent()) {
                        Game.menuManager.setVisibleInGame_PopUp(false);
                    }
                    else {
                        Game.menuManager.hideCourtCiv();
                        Game.menuManager.rebuildInGame_AllianceSpecial(this.getCurrent());
                    }
                }
            });
            menuElements.add(new ButtonFlag_Diplomacy(Game.alliancesSpecial.get(i).iLeaderCivID, paddingLeft2 + CFG.PADDING + ButtonFlag_AllianceSpecial.getButtonWidth() + CFG.PADDING, buttonY + allianceTitle + CFG.PADDING, true));
            menuElements.add(new Text_Static_ID(Game.alliancesSpecial.get(i).iLeaderCivID, Game.getCiv(Game.alliancesSpecial.get(i).iLeaderCivID).getCivName(), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, paddingLeft2 + ButtonFlag_AllianceSpecial.getButtonWidth() + CFG.PADDING * 2 + ButtonFlag_Diplomacy.getButtonWidth(), buttonY + allianceTitle + CFG.PADDING, menuWidth - (paddingLeft2 * 2 + ButtonFlag_AllianceSpecial.getButtonWidth() + CFG.PADDING * 2 + ButtonFlag_Diplomacy.getButtonWidth()), ButtonFlag_Diplomacy.getButtonHeight()));
            menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(paddingLeft, buttonY - CFG.PADDING * 2, menuWidth - paddingLeft * 2, ButtonFlag_AllianceSpecial.getButtonHeight() + CFG.PADDING * 4));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() - CFG.PADDING;
        }
        for (int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).diplomacy.alliance.size() > 0) {
                for (final Map.Entry<Integer, Diplomacy.DiplomacyData> entry : Game.getCiv(i).diplomacy.alliance.entrySet()) {
                    if (entry.getKey() > i) {
                        menuElements.add(new ButtonFlag_Diplomacy(i, menuWidth / 2 - maxWidth / 2 - CFG.PADDING * 2 - ImageManager.getImage(Images.flagDiplomacyOver).getWidth(), buttonY + tempTitlePaddingY, true));
                        menuElements.add(new ButtonFlag_Diplomacy(entry.getValue().iCivID, menuWidth / 2 + maxWidth / 2 + CFG.PADDING * 2, buttonY + tempTitlePaddingY, true));
                        menuElements.add(new Text_Static_ID(entry.getValue().iCivID, Game.getCiv(entry.getValue().iCivID).getCivName(), CFG.FONT_REGULAR_SMALL, -1, menuWidth / 2 + maxWidth / 2 + CFG.PADDING * 2 + ImageManager.getImage(Images.flagDiplomacyOver).getWidth(), buttonY, tempTextW, tempTitleH));
                        menuElements.add(new Text_Static_ID(i, Game.getCiv(i).getCivName(), CFG.FONT_REGULAR_SMALL, -1, paddingLeft, buttonY, tempTextW, tempTitleH));
                        menuElements.add(new TextIcon_Diplomacy(Images.alliance, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tempTitleH, maxWidth) {
                            @Override
                            public Color getColorBar() {
                                return Colors.COLOR_GRADIENT_OVER_BLUE;
                            }
                        });
                        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                    }
                }
            }
        }
        if (tElements == menuElements.size()) {
            menuElements.add(new Text_StaticBG(Game.lang.get("None") + ".", CFG.FONT_REGULAR_SMALL, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, ImageManager.getImage(Images.generalFrameBattle).getHeight() + CFG.TEXT_HEIGHT + CFG.PADDING * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        menuY += InGame_CourtOptions.menuH;
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(null, menuX, menuY, menuWidth, menuHeight, menuElements, false, false);
        this.drawScrollPositionAlways = false;
        Game.menuManager.setInGame_CivOptions_Title(Game.lang.get(Game.map.lMaps.get(Game.map.getActiveMapID()).mapData.Name));
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
}
