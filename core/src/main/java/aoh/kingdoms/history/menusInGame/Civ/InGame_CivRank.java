// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Civ;

import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID;
import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import aoh.kingdoms.history.menu_element.button.ButtonFlag2;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2Center;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon2_Horizontal;
import aoh.kingdoms.history.map.civilization.CivilizationRanking;
import aoh.kingdoms.history.mainGame.GameValues;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Active_Click;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_CivRank extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    
    public InGame_CivRank() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING;
        final int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        final int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        int buttonX = paddingLeft;
        int buttonY = CFG.PADDING * 2;
        final int buttonH = ImageManager.getImage(Images.flagOverDefault).getHeight();
        final int buttonW = (int)((menuWidth - paddingLeft * 2 - ImageManager.getImage(Images.flagOverDefault).getWidth() - CFG.PADDING * 2) * 0.65f);
        final int buttonW2 = (int)((menuWidth - paddingLeft * 2 - ImageManager.getImage(Images.flagOverDefault).getWidth() - CFG.PADDING * 2) * 0.35f);
        final int paddingLeft2 = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2;
        final int c0W = menuWidth - paddingLeft2 * 2;
        menuElements.add(new ButtonStatsRectIMG_Active_Click(Game.lang.get("Civilizations") + ": " + CFG.getNumberWithSpaces("" + Game.getCivilizationsInGame()), Images.council, paddingLeft2, buttonY, c0W, buttonH, ImageManager.getImage(Images.council).getWidth(), 0) {
            @Override
            public void actionElement() {
                Game.menuManager.rebuildInGame_Civ();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("OpenTheDiplomacyView"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.diplomacy, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final int maxIconWidth = ImageManager.getImage(Images.council).getWidth() + CFG.PADDING * 2;
        final int buttonW_Rank = (menuWidth - paddingLeft2 * 2 - CFG.PADDING) / 2;
        int i = 0;
        while (i < GameValues.civRank.CIV_RANK_MANPOWER_MAX.length) {
            if (i % 2 == 0) {
                buttonX = paddingLeft;
            }
            else {
                buttonX = paddingLeft + CFG.PADDING + buttonW_Rank;
            }
            menuElements.add(new TextIcon2_Horizontal(CivilizationRanking.getCivilizationRank_Name(i), CivilizationRanking.getCivilizationRank_IMG(i), buttonX, buttonY, buttonW_Rank, CFG.BUTTON_HEIGHT3, maxIconWidth) {
                int id;
                
                @Override
                public void buildElementHover() {
                    this.menuElementHover = CivilizationRanking.buildElementHover(this.id, this.id);
                }
                
                @Override
                public void setCurrent(final int nCurrent) {
                    this.id = nCurrent;
                }
            });
            menuElements.get(menuElements.size() - 1).setCurrent(i);
            if (++i % 2 == 0) {
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
        }
        i = 0;
        for (int iSize = menuElements.size(); i < iSize; ++i) {
            if (buttonY < menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING) {
                buttonY = menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING;
            }
        }
        buttonX = paddingLeft;
        final List<Integer> tCivs = new ArrayList<Integer>();
        for (int j = 1; j < Game.getCivsSize(); ++j) {
            if (Game.getCiv(j).getNumOfProvinces() > 0) {
                tCivs.add(j);
            }
        }
        int currentRankID = 5;
        while (tCivs.size() > 0) {
            int bestID = 0;
            for (int k = 0, iSize2 = tCivs.size(); k < iSize2; ++k) {
                if (Game.getCiv(tCivs.get(bestID)).getNumOfProvinces() < Game.getCiv(tCivs.get(k)).getNumOfProvinces()) {
                    bestID = k;
                }
            }
            if (Game.getCiv(tCivs.get(bestID)).iCivRankID != currentRankID) {
                currentRankID = Game.getCiv(tCivs.get(bestID)).iCivRankID;
                menuElements.add(new Text_Title_v2Center(Game.lang.get("CivilizationRank") + ": " + CivilizationRanking.getCivilizationRank_Name(currentRankID), -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
                buttonY += menuElements.get(menuElements.size() - 1).getHeight();
                buttonX = Images.boxTitleBORDERWIDTH;
                menuElements.add(new Text_Title_v2Center(Game.lang.get("Civilization"), -1, CFG.FONT_REGULAR_SMALL, buttonX, buttonY, menuWidth - buttonW2 - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4));
                buttonX += menuElements.get(menuElements.size() - 1).getWidth();
                menuElements.add(new Text_Title_v2Center(Game.lang.get("Provinces"), -1, CFG.FONT_REGULAR_SMALL, buttonX, buttonY, menuWidth - buttonX - Images.boxTitleBORDERWIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 4));
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
            buttonX = paddingLeft;
            menuElements.add(new ButtonFlag2((int)tCivs.get(bestID), buttonX, buttonY, true) {
                @Override
                public void actionElement() {
                    if (Game.getCiv(this.getCurrent()).getCapitalProvinceID() >= 0) {
                        if (!Game.getProvince(Game.getCiv(this.getCurrent()).getCapitalProvinceID()).getDrawProvince()) {
                            Game.mapCoords.centerToProvinceID(Game.getCiv(this.getCurrent()).getCapitalProvinceID());
                        }
                        Game.setActiveProvinceID(Game.getCiv(this.getCurrent()).getCapitalProvinceID());
                        ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                        Game.menuManager.rebuildInGame_Civ();
                    }
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new Text_StaticBG_ID(Game.getCiv(tCivs.get(bestID)).getCivName(), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, buttonW, buttonH, (int)tCivs.get(bestID)) {
                @Override
                public void actionElement() {
                    if (Game.getCiv(this.getCurrent()).getCapitalProvinceID() >= 0) {
                        Game.mapCoords.centerToProvinceID(Game.getCiv(this.getCurrent()).getCapitalProvinceID());
                    }
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new Text_StaticBG("" + Game.getCiv(tCivs.get(bestID)).getNumOfProvinces(), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, buttonW2, buttonH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("NumberOfProvinces") + ": "));
                    nData.add(new MenuElement_HoverElement_Type_Text(this.getText(), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.provinces, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            tCivs.remove(bestID);
        }
        buttonY = 0;
        for (int l = 0, iSize3 = menuElements.size(); l < iSize3; ++l) {
            if (buttonY < menuElements.get(l).getPosY() + menuElements.get(l).getHeight() + CFG.PADDING) {
                buttonY = menuElements.get(l).getPosY() + menuElements.get(l).getHeight() + CFG.PADDING;
            }
        }
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG(Game.lang.get("CivilizationRank"), false, false, Images.title500) {
            @Override
            public long getTime() {
                return InGame_CivRank.lTime;
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_CivRank.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)(CFG.BUTTON_WIDTH * ((CFG.currentTimeMillis - InGame_CivRank.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() + Images.boxTitleBORDERWIDTH, this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.rulerOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.rulerOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.rulerOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void updateLanguage() {
        super.updateLanguage();
        this.getTitle().setText(Game.lang.get("Civilizations"));
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_CivRank.lTime = CFG.currentTimeMillis;
    }
    
    static {
        InGame_CivRank.lTime = 0L;
    }
}
