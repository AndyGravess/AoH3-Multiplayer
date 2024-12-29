// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame.Diplomacy;

import aoh.kingdoms.history.map.province.ProvinceDraw;
import aoh.kingdoms.history.menusInGame.Info.InGame_Info;
import aoh.kingdoms.history.map.diplomacy.DiplomacyManager;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.mainGame.SoundsManager;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.button.ButtonGame_ImageSparks;
import aoh.kingdoms.history.menu_element.button.ButtonGame;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv_SpecialEmpty;
import aoh.kingdoms.history.menu_element.button.ButtonTechnology;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.map.RulersManager;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Bonuses;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Diplomacy_Flip;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Diplomacy;
import aoh.kingdoms.history.menu_element.button.ButtonRuler_Diplomacy;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon_Diplomacy;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.textStatic.Text_Static_ID;
import aoh.kingdoms.history.menu_element.button.ButtonFlag_Diplomacy;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.List;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.map.technology.TechnologyTree;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_ShareTechnology extends Menu
{
    public static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static int shareWithCivID;
    public static int technologyID;
    
    public void shareRandomTechnology(final int civID) {
        final List<Integer> possibleToShare = new ArrayList<Integer>();
        for (int i = 0; i < TechnologyTree.iTechnologySize; ++i) {
            if (Game.getCiv(Game.player.iCivID).getTechResearched(i) && !Game.getCiv(civID).getTechResearched(i) && (TechnologyTree.lTechnology.get(i).RequiredTech < 0 || Game.getCiv(civID).getTechResearched(TechnologyTree.lTechnology.get(i).RequiredTech)) && (TechnologyTree.lTechnology.get(i).RequiredTech2 < 0 || Game.getCiv(civID).getTechResearched(TechnologyTree.lTechnology.get(i).RequiredTech2))) {
                possibleToShare.add(i);
            }
        }
        if (possibleToShare.size() > 0) {
            InGame_ShareTechnology.technologyID = possibleToShare.get(Game.oR.nextInt(possibleToShare.size()));
        }
        else {
            InGame_ShareTechnology.technologyID = -1;
        }
    }
    
    public InGame_ShareTechnology(final int nCivID) {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        int paddingLeft = CFG.PADDING + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title600).getHeight();
        final int menuWidth = ImageManager.getImage(Images.insideTop600).getWidth();
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title600).getHeight();
        final int buttonYPadding = CFG.PADDING * 2;
        int buttonY = CFG.PADDING;
        final int buttonX = paddingLeft;
        this.shareRandomTechnology(InGame_ShareTechnology.shareWithCivID = nCivID);
        final int maxWidth = ImageManager.getImage(Images.insultBig).getWidth() + CFG.PADDING * 4;
        final int tempTitlePaddingY = CFG.PADDING;
        final int tempTitleH = ImageManager.getImage(Images.flagDiplomacyOver).getHeight() + tempTitlePaddingY * 2;
        final int tempTextW = menuWidth / 2 - paddingLeft - CFG.PADDING * 2 - maxWidth / 2 - ImageManager.getImage(Images.flagDiplomacyOver).getWidth();
        menuElements.add(new ButtonFlag_Diplomacy(Game.player.iCivID, menuWidth / 2 - maxWidth / 2 - CFG.PADDING * 2 - ImageManager.getImage(Images.flagDiplomacyOver).getWidth(), buttonY + tempTitlePaddingY, true));
        menuElements.add(new ButtonFlag_Diplomacy(InGame_ShareTechnology.shareWithCivID, menuWidth / 2 + maxWidth / 2 + CFG.PADDING * 2, buttonY + tempTitlePaddingY, true));
        menuElements.add(new Text_Static_ID(InGame_ShareTechnology.shareWithCivID, Game.getCiv(InGame_ShareTechnology.shareWithCivID).getCivName(), CFG.FONT_REGULAR, -1, menuWidth / 2 + maxWidth / 2 + CFG.PADDING * 2 + ImageManager.getImage(Images.flagDiplomacyOver).getWidth(), buttonY, tempTextW, tempTitleH));
        menuElements.add(new Text_Static_ID(Game.player.iCivID, Game.getCiv(Game.player.iCivID).getCivName(), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, tempTextW, tempTitleH));
        menuElements.add(new TextIcon_Diplomacy(Game_Calendar.IMG_TECHNOLOGY, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tempTitleH, maxWidth) {
            @Override
            public Color getColorBar() {
                return Colors.TECH_BLUE;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final int statsX = paddingLeft + ButtonRuler_Diplomacy.getButtonWidth() + CFG.PADDING * 2;
        final int statsW = menuWidth / 2 - statsX - CFG.PADDING / 2;
        final int statsH = (ButtonRuler_Diplomacy.getButtonHeight() - CFG.PADDING * 2) / 3;
        final int maxIconW = ImageManager.getImage(Game_Calendar.IMG_MANPOWER).getWidth() + CFG.PADDING * 2;
        menuElements.add(new ButtonStatsRectIMG_Diplomacy(Game.lang.get("Opinion") + ": " + CFG.getPrecision2(Game.getCiv(InGame_ShareTechnology.shareWithCivID).diplomacy.getRelation(Game.player.iCivID), 1), Images.relations, statsX, buttonY, statsW * 2 + CFG.PADDING / 2 * 2, statsH, maxIconW, 0) {
            @Override
            protected Color getColor(final boolean isActive) {
                return Colors.getColorTopStats(isActive, this.getIsHovered());
            }
            
            @Override
            public void buildElementHover() {
                this.menuElementHover = InGame_Civ.getHoverBetweenCivilizations(InGame_ShareTechnology.shareWithCivID, Game.player.iCivID, Game.getCiv(Game.player.iCivID).diplomacy.isImprovingRelations(InGame_ShareTechnology.shareWithCivID), Game.getCiv(Game.player.iCivID).diplomacy.isDamagingRelations(InGame_ShareTechnology.shareWithCivID));
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Diplomacy("" + CFG.getShortNumber(Game.getCiv(Game.player.iCivID).getResearchedTechnologies()), Game_Calendar.IMG_TECHNOLOGY, statsX, buttonY + CFG.PADDING + statsH, statsW, statsH, maxIconW, 0) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("UnlockedTechnologies") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.getCiv(Game.player.iCivID).getResearchedTechnologies(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Diplomacy_Flip("" + CFG.getShortNumber(Game.getCiv(InGame_ShareTechnology.shareWithCivID).getResearchedTechnologies()), Game_Calendar.IMG_TECHNOLOGY, statsX + CFG.PADDING + statsW, buttonY + CFG.PADDING + statsH, statsW, statsH, maxIconW, 0) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("UnlockedTechnologies") + ": "));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.getCiv(InGame_ShareTechnology.shareWithCivID).getResearchedTechnologies(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Opinion") + ": ", ((GameValues.diplomacy.SHARE_TECHNOLOGY_OPINION > 0.0f) ? "+" : "") + CFG.getPrecision2(GameValues.diplomacy.SHARE_TECHNOLOGY_OPINION, 100), Images.relationsUp, statsX, buttonY + (CFG.PADDING + statsH) * 2, statsW * 2 + CFG.PADDING, statsH, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 2));
        RulersManager.loadRulerIMG_DiplomacyLeft(Game.player.iCivID);
        RulersManager.loadRulerIMG_DiplomacyRight(InGame_ShareTechnology.shareWithCivID);
        menuElements.add(new ButtonRuler_Diplomacy(Game.player.iCivID, buttonX + CFG.PADDING, buttonY));
        menuElements.add(new ButtonRuler_Diplomacy(InGame_ShareTechnology.shareWithCivID, menuWidth - paddingLeft - ButtonRuler_Diplomacy.getButtonWidth() - CFG.PADDING, buttonY) {
            @Override
            public Image getRulerImage() {
                return RulersManager.rulerIMG_DiplomacyRight;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        if (InGame_ShareTechnology.technologyID < 0) {
            menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        else {
            menuElements.add(new ButtonTechnology(Images.techAvailable, InGame_ShareTechnology.technologyID, menuWidth / 2 - ImageManager.getImage(Images.techGray).getWidth() / 2, buttonY + CFG.PADDING, false, -1) {
                @Override
                public void actionElement() {
                    Game.menuManager.rebuildInGame_ShareTechnology(InGame_ShareTechnology.shareWithCivID);
                }
            });
            menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(paddingLeft, buttonY, menuWidth - paddingLeft * 2, TechnologyTree.iTechnologyHeight + CFG.PADDING * 2));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        paddingLeft += CFG.PADDING;
        menuElements.add(new ButtonGame(Game.lang.get("Cancel"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, true) {
            @Override
            public void actionElement() {
                Game.menuManager.setVisibleInGame_PopUp(false);
            }
        });
        menuElements.add(new ButtonGame_ImageSparks(Game.lang.get("ShareTechnology"), CFG.FONT_REGULAR, -1, paddingLeft + CFG.PADDING + (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, true, Game_Calendar.IMG_TECHNOLOGY) {
            @Override
            public void actionElement() {
                InGame_ShareTechnology.confirm();
            }
            
            @Override
            public boolean getClickable() {
                return InGame_ShareTechnology.technologyID >= 0;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ShareTechnology"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Opinion") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                nData.add(new MenuElement_HoverElement_Type_Text(((GameValues.diplomacy.SHARE_TECHNOLOGY_OPINION > 0.0f) ? "+" : "") + CFG.getPrecision2(GameValues.diplomacy.SHARE_TECHNOLOGY_OPINION, 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.relations, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            
            @Override
            public int getSFX() {
                return SoundsManager.DIPLOMACY_CLICK;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - titleHeight - menuY);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, buttonY)));
        this.initMenu(new MenuTitleIMG(Game.lang.get("ShareTechnology"), true, false, Images.title600) {
            @Override
            public long getTime() {
                return InGame_ShareTechnology.lTime;
            }
        }, CFG.GAME_WIDTH / 2 - menuWidth / 2, Math.min((int)(CFG.GAME_HEIGHT * 0.2f), CFG.GAME_HEIGHT / 2 - (menuHeight + titleHeight) / 2), menuWidth, menuHeight, menuElements, false, true);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        DiplomacyManager.updateInAnimation();
        if (InGame_ShareTechnology.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateY = iTranslateY - CFG.BUTTON_HEIGHT * 3 / 5 + (int)(CFG.BUTTON_HEIGHT * 3 / 5 * ((CFG.currentTimeMillis - InGame_ShareTechnology.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop600, Images.insideBot600);
        ImageManager.getImage(Images.civInfoOver).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.civInfoOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_ShareTechnology.lTime = CFG.currentTimeMillis;
        DiplomacyManager.updateAnimationTime();
    }
    
    public static final void confirm() {
        if (InGame_ShareTechnology.technologyID < 0) {
            return;
        }
        Game.getCiv(InGame_ShareTechnology.shareWithCivID).addTechnology(InGame_ShareTechnology.technologyID, false);
        Game.getCiv(InGame_ShareTechnology.shareWithCivID).diplomacy.updateRelation(InGame_ShareTechnology.shareWithCivID, Game.player.iCivID, GameValues.diplomacy.SHARE_TECHNOLOGY_OPINION);
        Game.getCiv(Game.player.iCivID).diplomacy.updateRelation(Game.player.iCivID, InGame_ShareTechnology.shareWithCivID, GameValues.diplomacy.SHARE_TECHNOLOGY_OPINION);
        if (Game.menuManager.getVisibleInGame_Civ()) {
            InGame_Civ.rebuildMenu();
        }
        InGame_Info.iCivID = Game.player.iCivID;
        InGame_Info.iCivID2 = InGame_ShareTechnology.shareWithCivID;
        Game.menuManager.rebuildInGame_Info(Game.lang.get("TechnologyShared"), Game.getCiv(InGame_ShareTechnology.shareWithCivID).getCivName());
        InGame_Info.imgID = Images.infoDiplomacy;
        ProvinceDraw.addDiplomacyLines(Game.getCiv(Game.player.iCivID).getCapitalProvinceID(), Game.getCiv(InGame_ShareTechnology.shareWithCivID).getCapitalProvinceID(), Colors.TECH_BLUE);
        Game.menuManager.setVisibleInGame_PopUp(false);
    }
    
    static {
        InGame_ShareTechnology.lTime = 0L;
        InGame_ShareTechnology.shareWithCivID = 0;
        InGame_ShareTechnology.technologyID = 0;
    }
}
