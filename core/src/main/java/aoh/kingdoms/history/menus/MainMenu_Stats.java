// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menus;

import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title_Stats;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.graphics.Texture;
import aoh.kingdoms.history.mainGame.FileManager;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_ID_FlagCiv_SpecialEmpty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoh.kingdoms.history.menu_element.textStatic.TextIcon2;
import aoh.kingdoms.history.menu_element.button.ButtonStatsRectIMG_Bonuses;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG_RulerTitle;
import aoh.kingdoms.history.menu_element.button.ButtonFlag_Formable;
import aoh.kingdoms.history.menu_element.button.ButtonFlag_Stats;
import aoh.kingdoms.history.menu.View;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.textStatic.Text_TitleBlueSort;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu_element.Icon;
import aoh.kingdoms.history.menu.MenuManager;
import aoh.kingdoms.history.map.diplomacy.DiplomacyManager;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.ClickAnimation;
import aoh.kingdoms.history.menu_element.textStatic.Text_Desc_Simple;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.button.ButtonRuler_Diplomacy;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.mainGame.Stats.Stats;
import aoh.kingdoms.history.textures.Image;
import java.util.List;
import aoh.kingdoms.history.menu.Menu;

public class MainMenu_Stats extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static long lTime2;
    public static List<Image> lFlags;
    public static List<Stats> statsData;
    public static int modeID;
    public static int hideReview;
    
    public MainMenu_Stats() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING;
        final int paddingLeft2 = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2;
        final int titleHeight = ImageManager.getImage(Images.title500).getHeight();
        final int menuWidth = ImageManager.getImage(Images.title500).getWidth();
        final int menuX = (int)(CFG.GAME_WIDTH / (10.0f * CFG.GUI_SCALE));
        final int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        int buttonY = 0;
        int buttonX = paddingLeft;
        final int topHeight = ButtonRuler_Diplomacy.getButtonHeight() + CFG.PADDING * 2;
        final int maxIconW = ImageManager.getImage(Images.gold).getWidth();
        if (MainMenu_Stats.statsData.size() >= 4 && MainMenu_Stats.hideReview > 0) {
            buttonY = CFG.PADDING;
            menuElements.add(new Text_Desc_Simple(Game.lang.get("Review0"), paddingLeft + (ImageManager.getImage(Images.heart).getWidth() + CFG.PADDING * 3), buttonY, menuWidth - paddingLeft * 2 - (ImageManager.getImage(Images.heart).getWidth() + CFG.PADDING * 3)) {
                @Override
                public void actionElement() {
                    final MenuManager menuManager = Game.menuManager;
                    MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + MainMenu_Stats.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + MainMenu_Stats.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                        @Override
                        public Color getColor() {
                            return DiplomacyManager.COLOR_GREEN;
                        }
                    });
                    --MainMenu_Stats.hideReview;
                    if (MainMenu_Stats.hideReview <= 0) {
                        Game.menuManager.rebuildMainMenu_Stats();
                        Game.menuManager.addToastGold(":P", Images.heart);
                    }
                }
            });
            menuElements.add(new Icon(Images.heart, paddingLeft, buttonY, ImageManager.getImage(Images.heart).getWidth() + CFG.PADDING * 2, menuElements.get(menuElements.size() - 1).getHeight(), 0) {
                @Override
                protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
                    oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, (this.getIsHovered() || isActive) ? 0.6f : 0.5f));
                    Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
                    oSB.setColor(Color.WHITE);
                    super.drawText(oSB, iTranslateX, iTranslateY, isActive);
                }
                
                @Override
                public void actionElement() {
                    final MenuManager menuManager = Game.menuManager;
                    MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + MainMenu_Stats.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + MainMenu_Stats.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                        @Override
                        public Color getColor() {
                            return DiplomacyManager.COLOR_GREEN;
                        }
                    });
                    --MainMenu_Stats.hideReview;
                    if (MainMenu_Stats.hideReview <= 0) {
                        Game.menuManager.rebuildMainMenu_Stats();
                        Game.menuManager.addToastGold(":P", Images.heart);
                    }
                }
            });
            buttonY += menuElements.get(menuElements.size() - 2).getHeight() + CFG.PADDING;
        }
        final int titleButtonW = (menuWidth - Images.boxTitleBORDERWIDTH * 2) / 4;
        menuElements.add(new Text_TitleBlueSort(MainMenu_Stats.modeID == 0, false, Game.lang.get("Name"), -1, Images.boxTitleBORDERWIDTH, buttonY, titleButtonW, CFG.BUTTON_HEIGHT4) {
            @Override
            public void actionElement() {
                if (MainMenu_Stats.modeID != 0) {
                    MainMenu_Stats.modeID = 0;
                    Game.menuManager.rebuildMainMenu_Stats();
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Name"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.government, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
            
            @Override
            public int getSFX() {
                return Game.soundsManager.getTab();
            }
        });
        menuElements.add(new Text_TitleBlueSort(MainMenu_Stats.modeID == 1, false, Game.lang.get("PlayingTime"), -1, Images.boxTitleBORDERWIDTH + titleButtonW, buttonY, titleButtonW, CFG.BUTTON_HEIGHT4) {
            @Override
            public void actionElement() {
                if (MainMenu_Stats.modeID != 1) {
                    MainMenu_Stats.modeID = 1;
                    Game.menuManager.rebuildMainMenu_Stats();
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("PlayingTime"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.play, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
            
            @Override
            public int getSFX() {
                return Game.soundsManager.getTab();
            }
        });
        menuElements.add(new Text_TitleBlueSort(MainMenu_Stats.modeID == 2, false, Game.lang.get("RecruitedArmy"), -1, Images.boxTitleBORDERWIDTH + titleButtonW * 2, buttonY, titleButtonW, CFG.BUTTON_HEIGHT4) {
            @Override
            public void actionElement() {
                if (MainMenu_Stats.modeID != 2) {
                    MainMenu_Stats.modeID = 2;
                    Game.menuManager.rebuildMainMenu_Stats();
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("RecruitedArmy"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
            
            @Override
            public int getSFX() {
                return Game.soundsManager.getTab();
            }
        });
        menuElements.add(new Text_TitleBlueSort(MainMenu_Stats.modeID == 3, false, Game.lang.get("Wars"), -1, Images.boxTitleBORDERWIDTH + titleButtonW * 3, buttonY, titleButtonW, CFG.BUTTON_HEIGHT4) {
            @Override
            public void actionElement() {
                if (MainMenu_Stats.modeID != 3) {
                    MainMenu_Stats.modeID = 3;
                    Game.menuManager.rebuildMainMenu_Stats();
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Wars"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.war, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
            
            @Override
            public int getSFX() {
                return Game.soundsManager.getTab();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final int statH = CFG.TEXT_HEIGHT + CFG.PADDING * 4;
        MainMenu_Stats.statsData.clear();
        MainMenu_Stats.statsData = Game.stats.loadAllStats();
        this.loadFlags();
        final List<Integer> ids = new ArrayList<Integer>();
        final List<String> names = new ArrayList<String>();
        for (int i = 0; i < MainMenu_Stats.statsData.size(); ++i) {
            ids.add(i);
            names.add(Game.lang.getCiv(MainMenu_Stats.statsData.get(i).tg));
        }
        final int statsRightW = (menuWidth - paddingLeft * 2 - CFG.PADDING * 5) / 6;
        final int statsRightH = CFG.BUTTON_HEIGHT;
        if (ids.isEmpty()) {
            menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2) {
                @Override
                public void actionElement() {
                    MainMenu_Stats.disposeFlags();
                    Game.menuManager.setViewID(View.MAINMENU);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        while (!ids.isEmpty()) {
            int bestID = 0;
            if (MainMenu_Stats.modeID == 0) {
                for (int j = 1; j < ids.size(); ++j) {
                    if (CFG.compareAlphabetic_TwoString(names.get(bestID), names.get(j))) {
                        bestID = j;
                    }
                }
            }
            else if (MainMenu_Stats.modeID == 1) {
                for (int j = 1; j < ids.size(); ++j) {
                    if (MainMenu_Stats.statsData.get(ids.get(bestID)).tr < MainMenu_Stats.statsData.get(ids.get(j)).tr) {
                        bestID = j;
                    }
                }
            }
            else if (MainMenu_Stats.modeID == 2) {
                for (int j = 1; j < ids.size(); ++j) {
                    if (MainMenu_Stats.statsData.get(ids.get(bestID)).rr < MainMenu_Stats.statsData.get(ids.get(j)).rr) {
                        bestID = j;
                    }
                }
            }
            else {
                for (int j = 1; j < ids.size(); ++j) {
                    if (MainMenu_Stats.statsData.get(ids.get(bestID)).nw < MainMenu_Stats.statsData.get(ids.get(j)).nw) {
                        bestID = j;
                    }
                }
            }
            final int startY = buttonY;
            buttonX = paddingLeft2;
            buttonY += CFG.PADDING;
            menuElements.add(new ButtonFlag_Stats((int)ids.get(bestID), buttonX, buttonY) {
                @Override
                public void actionElement() {
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            final int bHeight = (ButtonFlag_Formable.getButtonHeight() - CFG.PADDING) / 2;
            menuElements.add(new Text_StaticBG_RulerTitle(Game.lang.getCiv(MainMenu_Stats.statsData.get(ids.get(bestID)).tg), buttonX, buttonY, menuWidth - buttonX - paddingLeft2, bHeight) {
                int id;
                
                @Override
                public int getCurrent() {
                    return this.id;
                }
                
                @Override
                public void setCurrent(final int nCurrent) {
                    this.id = nCurrent;
                }
                
                @Override
                public void buildElementHover() {
                    this.menuElementHover = MainMenu_Stats.getHover(this.id);
                }
            });
            menuElements.get(menuElements.size() - 1).setCurrent(ids.get(bestID));
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Games") + ": ", CFG.getNumberWithSpaces("" + MainMenu_Stats.statsData.get(ids.get(bestID)).ga), Images.play, buttonX, buttonY + bHeight + CFG.PADDING, menuWidth - buttonX - paddingLeft2, bHeight, maxIconW) {
                int id;
                
                @Override
                public int getCurrent() {
                    return this.id;
                }
                
                @Override
                public void setCurrent(final int nCurrent) {
                    this.id = nCurrent;
                }
                
                @Override
                public void buildElementHover() {
                    this.menuElementHover = MainMenu_Stats.getHover(this.id);
                }
                
                @Override
                public Color getColorBonus() {
                    return Colors.HOVER_RIGHT;
                }
            });
            menuElements.get(menuElements.size() - 1).setCurrent(ids.get(bestID));
            buttonY += ButtonFlag_Stats.getButtonHeight() + CFG.PADDING;
            buttonX = paddingLeft;
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("PlayingTime") + ": ", "" + Game_Calendar.getNumOfDays_ByTurnsPlayed(MainMenu_Stats.statsData.get(ids.get(bestID)).tr), Images.time, paddingLeft2, buttonY, menuWidth - paddingLeft2 * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, maxIconW) {
                @Override
                public Color getColorBonus() {
                    return Colors.HOVER_RIGHT;
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("NumberOfProvinces") + ": ", CFG.getNumberWithSpaces("" + MainMenu_Stats.statsData.get(ids.get(bestID)).lp), Images.provinces, paddingLeft2, buttonY, menuWidth - paddingLeft2 * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, maxIconW) {
                @Override
                public Color getColorBonus() {
                    return Colors.HOVER_RIGHT;
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("MonthlyIncome") + ": ", CFG.getNumberWithSpaces("" + MainMenu_Stats.statsData.get(ids.get(bestID)).li), Images.gold, paddingLeft2, buttonY, menuWidth - paddingLeft2 * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, maxIconW) {
                @Override
                public Color getColorBonus() {
                    return Colors.HOVER_RIGHT;
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("ReinforceArmyCost") + ": ", CFG.getNumberWithSpaces("" + (int)(MainMenu_Stats.statsData.get(ids.get(bestID)).rf / 100.0f)), Game_Calendar.IMG_MANPOWER_UP, paddingLeft2, buttonY, menuWidth - paddingLeft2 * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, maxIconW) {
                @Override
                public Color getColorBonus() {
                    return Colors.HOVER_RIGHT;
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            buttonX = paddingLeft2;
            menuElements.add(new TextIcon2("" + CFG.getNumberWithSpaces("" + MainMenu_Stats.statsData.get(ids.get(bestID)).nw), Images.war, buttonX, buttonY, statsRightW, statsRightH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Wars") + ": ", this.getText(), this.imageID, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new TextIcon2("" + CFG.getNumberWithSpaces("" + MainMenu_Stats.statsData.get(ids.get(bestID)).cp), Images.provinces, buttonX, buttonY, statsRightW, statsRightH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ConqueredProvinces") + ": ", this.getText(), this.imageID, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new TextIcon2("" + CFG.getNumberWithSpaces("" + MainMenu_Stats.statsData.get(ids.get(bestID)).rr), Game_Calendar.IMG_MANPOWER, buttonX, buttonY, statsRightW, statsRightH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("RecruitedArmy") + ", " + Game.lang.get("Regiments") + ": ", this.getText(), this.imageID, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new TextIcon2("" + CFG.getNumberWithSpaces("" + MainMenu_Stats.statsData.get(ids.get(bestID)).rg), Images.general, buttonX, buttonY, statsRightW, statsRightH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Generals") + ": ", this.getText(), this.imageID, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new TextIcon2("" + CFG.getNumberWithSpaces("" + MainMenu_Stats.statsData.get(ids.get(bestID)).ra), Images.council, buttonX, buttonY, statsRightW, statsRightH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Advisors") + ": ", this.getText(), this.imageID, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX += menuElements.get(menuElements.size() - 1).getWidth() + CFG.PADDING;
            menuElements.add(new TextIcon2("" + CFG.getNumberWithSpaces("" + MainMenu_Stats.statsData.get(ids.get(bestID)).bc), Images.buildings, buttonX, buttonY, statsRightW, statsRightH) {
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("BuildingsConstructed") + ": ", this.getText(), this.imageID, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonX = paddingLeft2;
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(paddingLeft, startY, menuWidth - paddingLeft * 2, buttonY - startY));
            buttonX = paddingLeft;
            buttonY += CFG.PADDING;
            ids.remove(bestID);
            names.remove(bestID);
        }
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Close"), "", Images.x, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT3, maxIconW) {
            @Override
            public void actionElement() {
                MainMenu_Stats.disposeFlags();
                Game.menuManager.setViewID(View.MAINMENU);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu(new MenuTitleIMG(Game.lang.get("HallofFame"), true, false, Images.title500) {
            @Override
            public long getTime() {
                return MainMenu_Stats.lTime2;
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, true, false);
        this.drawScrollPositionAlways = false;
    }
    
    @Override
    public void beginClip(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive) {
        try {
            if (MainMenu.bgAlpha < 1.0f) {
                oSB.setColor(0.0f, 0.0f, 0.0f, 1.0f);
                Images.pix.draw(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.GAME_HEIGHT);
                MainMenu.bgAlpha = Math.min(1.0f, (CFG.currentTimeMillis - MainMenu.bgTIME) / (float)GameValues.text.MAIN_MENU_BG_ANIMATION_TIME);
            }
            oSB.setColor(new Color(0.050980393f, 0.08627451f, 0.13333334f, 1.0f * MainMenu.bgAlpha));
            InitGame.background.draw(oSB, iTranslateX + (CFG.GAME_WIDTH - InitGame.backgroundWidth) / 2, iTranslateY + (CFG.GAME_HEIGHT - InitGame.backgroundHeight) / 2, InitGame.backgroundWidth, InitGame.backgroundHeight);
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, MainMenu.bgAlpha));
            oSB.setShader(Renderer.shaderAlpha);
            InitGame.background.getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            ImageManager.getImage(Images.gradientHorizontal2).draw(oSB, (CFG.GAME_WIDTH - InitGame.backgroundWidth) / 2 + iTranslateX, (CFG.GAME_HEIGHT - InitGame.backgroundHeight) / 2 + iTranslateY, InitGame.backgroundWidth, InitGame.backgroundHeight);
            oSB.flush();
            oSB.setShader(Renderer.shaderDefault);
            oSB.setColor(MainMenu.sparksColors);
            MenuManager.sparksAnimation.draw2(oSB, iTranslateX, CFG.GAME_HEIGHT - Images.sparkHeight + iTranslateY, CFG.GAME_WIDTH, Images.sparkHeight);
            oSB.setColor(Color.WHITE);
            if (((CFG.isDesktop() && GameValues.text.MAIN_MENU_BG_ENABLE_AUTO_BG_CHANGE) || (!CFG.isDesktop() && GameValues.text.MAIN_MENU_BG_ENABLE_AUTO_BG_CHANGE_MOBILE)) && CFG.currentTimeMillis > MainMenu.bgTIME_CHANGE + GameValues.text.MAIN_MENU_BG_CHANGE_BG_EVERY_X_MS) {
                MainMenu.bgTIME_CHANGE = CFG.currentTimeMillis;
                Game.addSimpleTask(new Game.SimpleTask("loadBackground") {
                    @Override
                    public void update() {
                        InitGame.loadBackground();
                        MainMenu.bgTIME = System.currentTimeMillis();
                        MainMenu.bgTIME_CHANGE = System.currentTimeMillis();
                        MainMenu.bgAlpha = 0.0f;
                    }
                });
            }
        }
        catch (final Exception ex) {}
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.rulerOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.rulerOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.rulerOver).getHeight()));
        super.beginClip(oSB, iTranslateX, iTranslateY, menuIsActive);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        MainMenu_Stats.lTime = CFG.currentTimeMillis;
        MainMenu_Stats.lTime2 = MainMenu_Stats.lTime;
        if (!visible) {
            disposeFlags();
        }
    }
    
    @Override
    public void onHovered() {
        super.onHovered();
        Game.menuManager.setOrderOfMenu_MainMenu_Stats();
    }
    
    public final void loadFlags() {
        disposeFlags();
        for (int i = 0; i < MainMenu_Stats.statsData.size(); ++i) {
            try {
                try {
                    try {
                        MainMenu_Stats.lFlags.add(new Image(new Texture(FileManager.loadFile("gfx/flagsXH/" + MainMenu_Stats.statsData.get(i).tg + ".png")), Texture.TextureFilter.Nearest));
                    }
                    catch (final GdxRuntimeException e) {
                        MainMenu_Stats.lFlags.add(new Image(new Texture(FileManager.loadFile("gfx/flagsXH/" + Game.ideologiesManager.getRealTag(MainMenu_Stats.statsData.get(i).tg) + ".png")), Texture.TextureFilter.Nearest));
                    }
                }
                catch (final GdxRuntimeException ex) {
                    try {
                        MainMenu_Stats.lFlags.add(new Image(new Texture(FileManager.loadFile("gfx/flags/" + MainMenu_Stats.statsData.get(i).tg + ".png")), Texture.TextureFilter.Nearest));
                    }
                    catch (final GdxRuntimeException e2) {
                        MainMenu_Stats.lFlags.add(new Image(new Texture(FileManager.loadFile("gfx/flags/" + Game.ideologiesManager.getRealTag(MainMenu_Stats.statsData.get(i).tg) + ".png")), Texture.TextureFilter.Nearest));
                    }
                }
            }
            catch (final GdxRuntimeException e) {
                MainMenu_Stats.lFlags.add(new Image(new Texture(FileManager.loadFile("gfx/flags/ran.png")), Texture.TextureFilter.Nearest));
            }
        }
    }
    
    public static final void disposeFlags() {
        for (int i = 0; i < MainMenu_Stats.lFlags.size(); ++i) {
            MainMenu_Stats.lFlags.get(i).getTexture().dispose();
        }
        MainMenu_Stats.lFlags.clear();
    }
    
    public static MenuElement_Hover getHover(final int id) {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title_Stats(id, Game.lang.get("Games") + ": " + MainMenu_Stats.statsData.get(id).ga));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        return new MenuElement_Hover(nElements);
    }
    
    static {
        MainMenu_Stats.lTime = 0L;
        MainMenu_Stats.lTime2 = 0L;
        MainMenu_Stats.lFlags = new ArrayList<Image>();
        MainMenu_Stats.statsData = new ArrayList<Stats>();
        MainMenu_Stats.modeID = 1;
        MainMenu_Stats.hideReview = 5;
    }
}
