// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menus.Settings;

import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.map.map.Ship.ShipManager;
import aoh.kingdoms.history.menu_element.Slider;
import aoh.kingdoms.history.mainGame.FBO.FBOProvinceNames;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Center;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.mainGame.setting.SettingsManager;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.mainGame.setting.SettingsProvince;
import aoh.kingdoms.history.menus.Init_SelectLanguage;
import aoh.kingdoms.history.mainGame.setting.SettingsDesktop;
import aoh.kingdoms.history.menu_element.textStatic.Text_Title_v2_TextLR;
import aoh.kingdoms.history.menu_element.button.ButtonGame2;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.View;
import aoh.kingdoms.history.menu.Menu;

public class Settings_Menu extends Menu
{
    public static View goBackToMenu;
    public static long provinceInView_Time;
    public static long drawProvinces_Time;
    public static long drawProvincesFBO_Time;
    public static long drawArmies_Time;
    public static long drawProvincesBorder_Time;
    public static long drawProvincesNames_Time;
    public static long drawCivsNames_Time;
    public static long drawCities_Time;
    public static long drawMoveUnits_Time;
    public static long drawClouds_Time;
    public static long drawShips_Time;
    public static long drawShips2_Time;
    public static long drawMapBorder_Time;
    public static boolean updateTimes;
    public static long lastUpdateTime;
    public int statTextW;
    public int statTextPercW;
    public int nanoW;
    
    public Settings_Menu() {
        this.statTextW = 0;
        this.statTextPercW = 0;
        this.nanoW = 0;
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title600).getHeight();
        final int menuWidth = ImageManager.getImage(Images.title600).getWidth();
        final int menuX = CFG.BUTTON_WIDTH + Renderer.boxBGExtraY + CFG.PADDING;
        final int menuY = ImageManager.getImage(Images.topStats).getHeight() + CFG.PADDING * 2 + ImageManager.getImage(Images.title600).getHeight();
        final int buttonYPadding = CFG.PADDING;
        int buttonY = buttonYPadding * 2;
        final int buttonX = paddingLeft;
        menuElements.add(new ButtonGame2(Game.lang.get("Back"), 1, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true, CFG.BUTTON_HEIGHT2) {
            @Override
            public void actionElement() {
                Game.saveSettings();
                Renderer.drawArmyInProvince = true;
                Game.menuManager.setViewID(Settings_Menu.goBackToMenu);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding * 2;
        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Graphics"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        if (CFG.isDesktop()) {
            menuElements.add(new ButtonGame2(Game.lang.get("Fullscreen"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true, CFG.BUTTON_HEIGHT, true) {
                @Override
                public boolean getCheckboxState() {
                    return SettingsDesktop.fullscreen;
                }
                
                @Override
                public void actionElement() {
                    SettingsDesktop.fullscreen = !SettingsDesktop.fullscreen;
                    SettingsDesktop.saveConfig();
                    Game.menuManager.addToastGold(Game.lang.get("GameNeedsToBeRestartedToApplyTheChanges"), Images.settings);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonGame2(Game.lang.get("Resolution") + ": " + ((SettingsDesktop.iWidth <= 0 || SettingsDesktop.iHeight <= 0) ? (CFG.GAME_WIDTH + "x" + CFG.GAME_HEIGHT) : (SettingsDesktop.iWidth + " x " + SettingsDesktop.iHeight)), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true, CFG.BUTTON_HEIGHT) {
                @Override
                public void updateLanguage() {
                    this.setText(Game.lang.get("Resolution") + ": " + ((SettingsDesktop.iWidth <= 0 || SettingsDesktop.iHeight <= 0) ? (CFG.GAME_WIDTH + "x" + CFG.GAME_HEIGHT) : (SettingsDesktop.iWidth + " x " + SettingsDesktop.iHeight)));
                }
                
                @Override
                public void actionElement() {
                    Game.menuManager.setViewID(View.SETTINGS_RESOLUTION);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        menuElements.add(new ButtonGame2(Game.lang.get("UIScale"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true, CFG.BUTTON_HEIGHT) {
            @Override
            public void actionElement() {
                Game.menuManager.setViewID(View.SETTINGS_UI);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Game"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame2(Game.lang.get("SelectLanguage"), 1, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true, CFG.BUTTON_HEIGHT) {
            @Override
            public void actionElement() {
                Init_SelectLanguage.goBackToMenu = View.SETTINGS;
                Game.menuManager.setViewIDWithoutAnimation(View.INIT_GAME_MENU_LANGUAGE);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame2(SettingsProvince.getSettingsText_Double(), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH + CFG.PADDING), true, CFG.BUTTON_HEIGHT, true) {
            @Override
            public void updateLanguage() {
                this.setText(Game.lang.get("Sidebar") + ": " + (Game.settingsManager.enableHideSideMenu ? Game.lang.get("Off") : Game.lang.get("On")));
            }
            
            @Override
            public boolean getCheckboxState() {
                return !Game.settingsManager.enableHideSideMenu;
            }
        });
        menuElements.add(new ButtonGame2(">>", CFG.FONT_BOLD, -1, paddingLeft + (menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2)) + CFG.PADDING * 2 + CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            @Override
            public void actionElement() {
                Game.settingsManager.enableHideSideMenu = !Game.settingsManager.enableHideSideMenu;
                Settings_Menu.this.updateLanguage();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame2("<<", CFG.FONT_BOLD, -1, paddingLeft, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            @Override
            public void actionElement() {
                boolean changed = false;
                for (int i = 0; i < GameValues.value.AUTO_SAVE_DAYS.length; ++i) {
                    if (GameValues.value.AUTO_SAVE_DAYS[i] == Game.settingsManager.AUTO_SAVE_DAYS) {
                        Game.settingsManager.AUTO_SAVE_DAYS = GameValues.value.AUTO_SAVE_DAYS[Math.max(0, i - 1)];
                        changed = true;
                        break;
                    }
                }
                if (!changed) {
                    Game.settingsManager.AUTO_SAVE_DAYS = GameValues.value.AUTO_SAVE_DAYS[0];
                }
                Settings_Menu.this.updateLanguage();
            }
        });
        menuElements.add(new ButtonGame2(Game.lang.get("Autosave") + ": " + ((Game.settingsManager.AUTO_SAVE_DAYS == 0) ? Game.lang.get("Off") : Game.lang.get("DaysX", Game.settingsManager.AUTO_SAVE_DAYS)), CFG.FONT_REGULAR, -1, paddingLeft + CFG.BUTTON_WIDTH + CFG.PADDING, buttonY, menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2), true, CFG.BUTTON_HEIGHT) {
            @Override
            public void updateLanguage() {
                this.setText(Game.lang.get("Autosave") + ": " + ((Game.settingsManager.AUTO_SAVE_DAYS == 0) ? Game.lang.get("Off") : Game.lang.get("DaysX", Game.settingsManager.AUTO_SAVE_DAYS)));
            }
        });
        menuElements.add(new ButtonGame2(">>", CFG.FONT_BOLD, -1, paddingLeft + (menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2)) + CFG.PADDING * 2 + CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            @Override
            public void actionElement() {
                boolean changed = false;
                for (int i = 0; i < GameValues.value.AUTO_SAVE_DAYS.length; ++i) {
                    if (GameValues.value.AUTO_SAVE_DAYS[i] == Game.settingsManager.AUTO_SAVE_DAYS) {
                        Game.settingsManager.AUTO_SAVE_DAYS = GameValues.value.AUTO_SAVE_DAYS[Math.min(GameValues.value.AUTO_SAVE_DAYS.length - 1, i + 1)];
                        changed = true;
                        break;
                    }
                }
                if (!changed) {
                    Game.settingsManager.AUTO_SAVE_DAYS = GameValues.value.AUTO_SAVE_DAYS[0];
                }
                Settings_Menu.this.updateLanguage();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        if (!CFG.isDesktop()) {
            menuElements.add(new ButtonGame2("<<", CFG.FONT_BOLD, -1, paddingLeft, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
                @Override
                public void actionElement() {
                    final SettingsManager settingsManager = Game.settingsManager;
                    --settingsManager.IN_GAME_LEFT_PADDING_EXTRA;
                    Settings_Menu.this.updateLanguage();
                    Game.menuManager.rebuildInGame_CourtOptions();
                }
            });
            menuElements.add(new ButtonGame2(SettingsProvince.getSettingsText_InGamePaddingLeft(), CFG.FONT_REGULAR, -1, paddingLeft + CFG.BUTTON_WIDTH + CFG.PADDING, buttonY, menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2), true, CFG.BUTTON_HEIGHT) {
                @Override
                public void updateLanguage() {
                    this.setText(SettingsProvince.getSettingsText_InGamePaddingLeft());
                }
            });
            menuElements.add(new ButtonGame2(">>", CFG.FONT_BOLD, -1, paddingLeft + (menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2)) + CFG.PADDING * 2 + CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
                @Override
                public void actionElement() {
                    final SettingsManager settingsManager = Game.settingsManager;
                    ++settingsManager.IN_GAME_LEFT_PADDING_EXTRA;
                    Settings_Menu.this.updateLanguage();
                    Game.menuManager.rebuildInGame_CourtOptions();
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Audio"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame2(Game.lang.get("Audio"), 1, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true, CFG.BUTTON_HEIGHT) {
            @Override
            public void actionElement() {
                if (Game.menuManager.getVisibleSettingsAudio()) {
                    Game.menuManager.setVisibleSettingsAudio(false);
                }
                else {
                    Game.menuManager.rebuildSettingsAudio();
                }
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Provinces"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6, "") {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center("Render Time Details", CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc("On the right side of the menu, in a black box, you can check which elements take the most render time.", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc("Typically, these are:", CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc("Province Borders", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc("Province Names", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc("After each change, check the FPS to see if the improvements are sufficient.", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc("Steps to Improve FPS", CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc("Decrease Province Names to: Medium -> Turn off Double Border -> Decrease Civilization Names to: Low -> Decrease Province Borders to: Medium -> If needed, further decrease Province Borders to: Low -> Continue adjusting other settings as required.", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame2("<<", CFG.FONT_BOLD, -1, paddingLeft, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            @Override
            public void actionElement() {
                SettingsProvince.updateSettingsProvinceBorder(-1);
                Settings_Menu.this.updateLanguage();
            }
        });
        menuElements.add(new ButtonGame2(SettingsProvince.getSettingsText(), CFG.FONT_REGULAR, -1, paddingLeft + CFG.BUTTON_WIDTH + CFG.PADDING, buttonY, menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2), true, CFG.BUTTON_HEIGHT) {
            @Override
            public void updateLanguage() {
                this.setText(SettingsProvince.getSettingsText());
            }
        });
        menuElements.add(new ButtonGame2(">>", CFG.FONT_BOLD, -1, paddingLeft + (menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2)) + CFG.PADDING * 2 + CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            @Override
            public void actionElement() {
                SettingsProvince.updateSettingsProvinceBorder(1);
                Settings_Menu.this.updateLanguage();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame2(SettingsProvince.getSettingsText_Double(), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH + CFG.PADDING), true, CFG.BUTTON_HEIGHT) {
            @Override
            public void updateLanguage() {
                this.setText(SettingsProvince.getSettingsText_Double());
            }
        });
        menuElements.add(new ButtonGame2(">>", CFG.FONT_BOLD, -1, paddingLeft + (menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2)) + CFG.PADDING * 2 + CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            @Override
            public void actionElement() {
                SettingsProvince.updateSettings_Double();
                Settings_Menu.this.updateLanguage();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame2("<<", CFG.FONT_BOLD, -1, paddingLeft, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            @Override
            public void actionElement() {
                SettingsProvince.updateSettingsProvinceNames(-1);
                Settings_Menu.this.updateLanguage();
                FBOProvinceNames.redrawnProvinceNames();
            }
        });
        menuElements.add(new ButtonGame2(SettingsProvince.getSettingsText_Names(), CFG.FONT_REGULAR, -1, paddingLeft + CFG.BUTTON_WIDTH + CFG.PADDING, buttonY, menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2), true, CFG.BUTTON_HEIGHT) {
            @Override
            public void updateLanguage() {
                this.setText(SettingsProvince.getSettingsText_Names());
            }
        });
        menuElements.add(new ButtonGame2(">>", CFG.FONT_BOLD, -1, paddingLeft + (menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2)) + CFG.PADDING * 2 + CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            @Override
            public void actionElement() {
                SettingsProvince.updateSettingsProvinceNames(1);
                Settings_Menu.this.updateLanguage();
                FBOProvinceNames.redrawnProvinceNames();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame2("<<", CFG.FONT_BOLD, -1, paddingLeft, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            @Override
            public void actionElement() {
                SettingsProvince.updateSettingsProvinceNames_Scale(-1);
                Settings_Menu.this.updateLanguage();
                FBOProvinceNames.redrawnProvinceNames();
            }
        });
        menuElements.add(new ButtonGame2(SettingsProvince.getSettingsText_Names(), CFG.FONT_REGULAR, -1, paddingLeft + CFG.BUTTON_WIDTH + CFG.PADDING, buttonY, menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2), true, CFG.BUTTON_HEIGHT) {
            @Override
            public void updateLanguage() {
                this.setText(Game.lang.get("ProvinceNamesMinScale") + ": " + CFG.getPrecision2(Game.settingsManager.PROVINCE_NAMES_SCALE, 1000));
            }
        });
        menuElements.add(new ButtonGame2(">>", CFG.FONT_BOLD, -1, paddingLeft + (menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2)) + CFG.PADDING * 2 + CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            @Override
            public void actionElement() {
                SettingsProvince.updateSettingsProvinceNames_Scale(1);
                Settings_Menu.this.updateLanguage();
                FBOProvinceNames.redrawnProvinceNames();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame2("<<", CFG.FONT_BOLD, -1, paddingLeft, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            @Override
            public void actionElement() {
                SettingsProvince.updateSettingsCivNames(-1);
                Settings_Menu.this.updateLanguage();
            }
        });
        menuElements.add(new ButtonGame2(SettingsProvince.getSettingsText_CivNames(), CFG.FONT_REGULAR, -1, paddingLeft + CFG.BUTTON_WIDTH + CFG.PADDING, buttonY, menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2), true, CFG.BUTTON_HEIGHT) {
            @Override
            public void updateLanguage() {
                this.setText(SettingsProvince.getSettingsText_CivNames());
            }
        });
        menuElements.add(new ButtonGame2(">>", CFG.FONT_BOLD, -1, paddingLeft + (menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2)) + CFG.PADDING * 2 + CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            @Override
            public void actionElement() {
                SettingsProvince.updateSettingsCivNames(1);
                Settings_Menu.this.updateLanguage();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame2("<<", CFG.FONT_BOLD, -1, paddingLeft, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            @Override
            public void actionElement() {
                SettingsProvince.updateSettings_ProvinceFlags(-1);
                Settings_Menu.this.updateLanguage();
            }
        });
        menuElements.add(new ButtonGame2(SettingsProvince.getSettingsText_ProvinceFlags(), CFG.FONT_REGULAR, -1, paddingLeft + CFG.BUTTON_WIDTH + CFG.PADDING, buttonY, menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2), true, CFG.BUTTON_HEIGHT) {
            @Override
            public void updateLanguage() {
                this.setText(SettingsProvince.getSettingsText_ProvinceFlags());
            }
        });
        menuElements.add(new ButtonGame2(">>", CFG.FONT_BOLD, -1, paddingLeft + (menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2)) + CFG.PADDING * 2 + CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            @Override
            public void actionElement() {
                SettingsProvince.updateSettings_ProvinceFlags(1);
                Settings_Menu.this.updateLanguage();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame2(SettingsProvince.getSettingsText_Cities(), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH + CFG.PADDING), true, CFG.BUTTON_HEIGHT) {
            @Override
            public void updateLanguage() {
                this.setText(SettingsProvince.getSettingsText_Cities());
            }
        });
        menuElements.add(new ButtonGame2(">>", CFG.FONT_BOLD, -1, paddingLeft + (menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2)) + CFG.PADDING * 2 + CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            @Override
            public void actionElement() {
                SettingsProvince.updateSettings_Cities();
                Settings_Menu.this.updateLanguage();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Slider(Game.lang.get("Ships") + ": ", paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, 0, 100, Game.settingsManager.SHIPS_ON_MAP) {
            @Override
            public void actionElement() {
                Game.settingsManager.SHIPS_ON_MAP = this.getCurrent();
                ShipManager.updateLimitOfShipsAtSea();
            }
            
            @Override
            public String getDrawText() {
                return this.sText + Game.settingsManager.SHIPS_ON_MAP + "%";
            }
            
            @Override
            public boolean getScrollable() {
                return false;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        if (Game.cloudsAnimation.loadClouds()) {
            menuElements.add(new ButtonGame2(SettingsProvince.getSettingsText_Clouds(), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH + CFG.PADDING), true, CFG.BUTTON_HEIGHT) {
                @Override
                public void updateLanguage() {
                    this.setText(SettingsProvince.getSettingsText_Clouds());
                }
            });
            menuElements.add(new ButtonGame2(">>", CFG.FONT_BOLD, -1, paddingLeft + (menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2)) + CFG.PADDING * 2 + CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
                @Override
                public void actionElement() {
                    SettingsProvince.updateSettings_Clouds();
                    Settings_Menu.this.updateLanguage();
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        menuElements.add(new ButtonGame2("<<", CFG.FONT_BOLD, -1, paddingLeft, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            @Override
            public void actionElement() {
                Game.DRAW_ARMY_MIN_SCALE = Math.max(0.05f, Game.DRAW_ARMY_MIN_SCALE - 0.05f);
                Settings_Menu.this.updateLanguage();
            }
        });
        menuElements.add(new ButtonGame2(Game.lang.get("DrawArmyScale") + ": " + CFG.getPrecision2(Game.DRAW_ARMY_MIN_SCALE, 100), CFG.FONT_REGULAR, -1, paddingLeft + CFG.BUTTON_WIDTH + CFG.PADDING, buttonY, menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2), true, CFG.BUTTON_HEIGHT) {
            @Override
            public void updateLanguage() {
                this.setText(Game.lang.get("DrawArmyScale") + ": " + CFG.getPrecision2(Game.DRAW_ARMY_MIN_SCALE, 100));
            }
        });
        menuElements.add(new ButtonGame2(">>", CFG.FONT_BOLD, -1, paddingLeft + (menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2)) + CFG.PADDING * 2 + CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            @Override
            public void actionElement() {
                Game.DRAW_ARMY_MIN_SCALE = Math.min(1.0f, Game.DRAW_ARMY_MIN_SCALE + 0.05f);
                Settings_Menu.this.updateLanguage();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        if (CFG.isDesktop() || !GameValues.value.MOBILE_DISABLE_FBO) {
            menuElements.add(new ButtonGame2(SettingsProvince.getSettingsText_FBO(), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH + CFG.PADDING), true, CFG.BUTTON_HEIGHT) {
                @Override
                public void updateLanguage() {
                    this.setText(SettingsProvince.getSettingsText_FBO());
                }
            });
            menuElements.add(new ButtonGame2(">>", CFG.FONT_BOLD, -1, paddingLeft + (menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2)) + CFG.PADDING * 2 + CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
                @Override
                public void actionElement() {
                    SettingsProvince.updateSettings_FBO();
                    Settings_Menu.this.updateLanguage();
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonGame2(SettingsProvince.getSettingsText_FBO_Provinces(), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH + CFG.PADDING), true, CFG.BUTTON_HEIGHT) {
                @Override
                public void updateLanguage() {
                    this.setText(SettingsProvince.getSettingsText_FBO_Provinces());
                }
            });
            menuElements.add(new ButtonGame2(">>", CFG.FONT_BOLD, -1, paddingLeft + (menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2)) + CFG.PADDING * 2 + CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
                @Override
                public void actionElement() {
                    SettingsProvince.updateSettings_FBO_Provinces();
                    Settings_Menu.this.updateLanguage();
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("ProvinceBorder") + ": " + Game.lang.get("Extra"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame2("<<", CFG.FONT_BOLD, -1, paddingLeft, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            @Override
            public void actionElement() {
                Game.settingsManager.BORDER_EXTRA_WIDTH = Math.max(0.0f, Game.settingsManager.BORDER_EXTRA_WIDTH - 0.25f);
                Settings_Menu.this.updateLanguage();
            }
        });
        menuElements.add(new ButtonGame2(SettingsProvince.getSettingsText_ProvincesBorderExtra(), CFG.FONT_REGULAR, -1, paddingLeft + CFG.BUTTON_WIDTH + CFG.PADDING, buttonY, menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2), true, CFG.BUTTON_HEIGHT) {
            @Override
            public void updateLanguage() {
                this.setText(SettingsProvince.getSettingsText_ProvincesBorderExtra());
            }
        });
        menuElements.add(new ButtonGame2(">>", CFG.FONT_BOLD, -1, paddingLeft + (menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2)) + CFG.PADDING * 2 + CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            @Override
            public void actionElement() {
                Game.settingsManager.BORDER_EXTRA_WIDTH = Math.max(0.0f, Game.settingsManager.BORDER_EXTRA_WIDTH + 0.25f);
                Settings_Menu.this.updateLanguage();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Provinces") + ": " + Game.lang.get("Alpha"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Slider(Game.lang.get("ProvinceAlpha") + ": ", paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, 10, 255, (int)(Game.settingsManager.PROVINCE_ALPHA * 255.0f)) {
            @Override
            public void actionElement() {
                Game.settingsManager.PROVINCE_ALPHA = this.getCurrent() / 255.0f;
            }
            
            @Override
            public String getDrawText() {
                return this.sText + CFG.getPrecision2(this.getCurrent() / 255.0f * 100.0f, 1) + "%";
            }
            
            @Override
            public boolean getScrollable() {
                return false;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Default") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(31.372551f, 1) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Slider(Game.lang.get("Alpha") + ", " + Game.lang.get("OccupiedProvinces") + ": ", paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, 10, 100, (int)(Game.settingsManager.PROVINCE_OCCUPIED_ALPHA_EXTRA * 100.0f)) {
            @Override
            public void actionElement() {
                Game.settingsManager.PROVINCE_OCCUPIED_ALPHA_EXTRA = this.getCurrent() / 100.0f;
            }
            
            @Override
            public String getDrawText() {
                return this.sText + CFG.getPrecision2(Game.settingsManager.PROVINCE_OCCUPIED_ALPHA_EXTRA * 100.0f, 1) + "%";
            }
            
            @Override
            public boolean getScrollable() {
                return false;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Default") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(25.0f, 1) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Slider(Game.lang.get("Alpha") + ", " + Game.lang.get("Wasteland") + ": ", paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, 10, 255, (int)(Game.settingsManager.PROVINCE_ALPHA_WASTELAND * 255.0f)) {
            @Override
            public void actionElement() {
                Game.settingsManager.PROVINCE_ALPHA_WASTELAND = this.getCurrent() / 255.0f;
            }
            
            @Override
            public String getDrawText() {
                return this.sText + CFG.getPrecision2(this.getCurrent() / 255.0f * 100.0f, 1) + "%";
            }
            
            @Override
            public boolean getScrollable() {
                return false;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Default") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(30.000002f, 1) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Slider(Game.lang.get("Alpha") + ", " + Game.lang.get("ProvinceNames") + ": ", paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, 10, 255, (int)(Game.settingsManager.PROVINCE_NAMES_ALPHA * 255.0f)) {
            @Override
            public void actionElement() {
                Game.settingsManager.PROVINCE_NAMES_ALPHA = this.getCurrent() / 255.0f;
            }
            
            @Override
            public String getDrawText() {
                return this.sText + CFG.getPrecision2(this.getCurrent() / 255.0f * 100.0f, 1) + "%";
            }
            
            @Override
            public boolean getScrollable() {
                return false;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Default") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(45.0f, 1) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Slider(Game.lang.get("Alpha") + ", " + Game.lang.get("CivilizationsNames") + ": ", paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, 10, 255, (int)(Game.settingsManager.CIV_NAMES_TEXT_ALPHA * 255.0f)) {
            @Override
            public void actionElement() {
                Game.settingsManager.CIV_NAMES_TEXT_ALPHA = this.getCurrent() / 255.0f;
            }
            
            @Override
            public String getDrawText() {
                return this.sText + CFG.getPrecision2(this.getCurrent() / 255.0f * 100.0f, 1) + "%";
            }
            
            @Override
            public boolean getScrollable() {
                return false;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Default") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(50.0f, 1) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("TexturesQuality"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame2(SettingsProvince.getSettingsText_Double(), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH + CFG.PADDING), true, CFG.BUTTON_HEIGHT, true) {
            @Override
            public void updateLanguage() {
                this.setText(Game.lang.get("TexturesQuality") + ": " + (Game.highTextureSettings ? Game.lang.get("High") : Game.lang.get("Low")));
            }
            
            @Override
            public boolean getCheckboxState() {
                return Game.highTextureSettings;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc("Reduce texture quality for better performance on graphics cards with low VRAM. Additionally, consider disabling FBO.", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new ButtonGame2(">>", CFG.FONT_BOLD, -1, paddingLeft + (menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2)) + CFG.PADDING * 2 + CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            @Override
            public void actionElement() {
                Game.highTextureSettings = !Game.highTextureSettings;
                Game.saveTextueSettings();
                Game.menuManager.addToastGold(Game.lang.get("GameNeedsToBeRestartedToApplyTheChanges"), Images.settings);
                Settings_Menu.this.updateLanguage();
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc("Reduce texture quality for better performance on graphics cards with low VRAM. Additionally, consider disabling FBO.", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("More"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame2(Game.lang.get("Council") + ": " + Game.lang.get("Tip"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true, CFG.BUTTON_HEIGHT, true) {
            @Override
            public boolean getCheckboxState() {
                return Game.settingsManager.COUNCIL_TIPS;
            }
            
            @Override
            public void actionElement() {
                Game.settingsManager.COUNCIL_TIPS = !Game.settingsManager.COUNCIL_TIPS;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame2(Game.lang.get("EdgeScrolling"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true, CFG.BUTTON_HEIGHT, true) {
            @Override
            public boolean getCheckboxState() {
                return Game.settingsManager.ENABLE_EDGE_SCROLL;
            }
            
            @Override
            public void actionElement() {
                Game.settingsManager.ENABLE_EDGE_SCROLL = !Game.settingsManager.ENABLE_EDGE_SCROLL;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        if (CFG.isDesktop()) {
            menuElements.add(new ButtonGame2(Game.lang.get("VSync"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true, CFG.BUTTON_HEIGHT, true) {
                @Override
                public boolean getCheckboxState() {
                    return SettingsDesktop.vSync;
                }
                
                @Override
                public void actionElement() {
                    SettingsDesktop.vSync = !SettingsDesktop.vSync;
                    SettingsDesktop.saveConfig();
                    Game.menuManager.addToastGold(Game.lang.get("GameNeedsToBeRestartedToApplyTheChanges"), Images.settings);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        menuElements.add(new ButtonGame2(Game.lang.get("Reset"), 1, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true, CFG.BUTTON_HEIGHT) {
            @Override
            public void actionElement() {
                Game.settingsManager = new SettingsManager();
                Game.loadSettingsDefault();
                GameValues.updateSettingsFBO();
                Settings_Menu.this.updateLanguage();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        buttonY = 0;
        for (int i = 0, iSize = menuElements.size(); i < iSize; ++i) {
            if (buttonY < menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING * 2) {
                buttonY = menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING * 2;
            }
        }
        final int tMenuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - CFG.GAME_HEIGHT / 8 - CFG.PADDING * 2);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, tMenuHeight)));
        this.initMenu(new MenuTitleIMG(Game.lang.get("Settings"), false, false, Images.title600), CFG.GAME_WIDTH / 10, CFG.GAME_HEIGHT / 8, menuWidth, tMenuHeight, menuElements, true, false);
        this.drawScrollPositionAlways = false;
        this.statTextW = Renderer.getTextWidth("Provinces Border: 12", CFG.FONT_REGULAR_SMALL);
        this.statTextPercW = Renderer.getTextWidth("1000%", CFG.FONT_REGULAR_SMALL);
        this.nanoW = Renderer.getTextWidth("X16 666 667", CFG.FONT_REGULAR_SMALL);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (GameValues.value.SETTINGS_MENU_DRAW_TIMES) {
            final int paddingL = CFG.PADDING * 4;
            int extraY = 0;
            final long totalTime = Settings_Menu.drawProvinces_Time + Settings_Menu.drawProvincesFBO_Time + Settings_Menu.drawArmies_Time + Settings_Menu.drawProvincesBorder_Time + Settings_Menu.drawProvincesNames_Time + Settings_Menu.drawCivsNames_Time + Settings_Menu.drawCities_Time + Settings_Menu.drawMoveUnits_Time + Settings_Menu.drawClouds_Time + Settings_Menu.drawShips_Time + Settings_Menu.drawShips2_Time + Settings_Menu.drawMapBorder_Time;
            Renderer.drawBoxCorner(oSB, this.getPosX() + this.getWidth() + paddingL - CFG.PADDING * 2 + iTranslateX, this.getPosY() + extraY - CFG.PADDING * 2 + iTranslateY, CFG.PADDING * 4 + this.statTextW + this.statTextPercW + CFG.PADDING * 3 + this.nanoW, (CFG.TEXT_HEIGHT_SMALL + CFG.PADDING) * 16 + CFG.PADDING * 2);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "Draw", this.getPosX() + this.getWidth() + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "Perc", this.getPosX() + this.getWidth() + this.statTextW + CFG.PADDING * 2 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "Nanotime", this.getPosX() + this.getWidth() + this.statTextW + this.statTextPercW + CFG.PADDING * 3 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            extraY += CFG.TEXT_HEIGHT_SMALL + CFG.PADDING;
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "Update Provinces ", this.getPosX() + this.getWidth() + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "" + CFG.getPrecision2(Settings_Menu.provinceInView_Time / (float)totalTime * 100.0f, 1) + "%", this.getPosX() + this.getWidth() + this.statTextW + CFG.PADDING * 2 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, CFG.getNumberWithSpaces("" + Settings_Menu.provinceInView_Time), this.getPosX() + this.getWidth() + this.statTextW + this.statTextPercW + CFG.PADDING * 3 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            extraY += CFG.TEXT_HEIGHT_SMALL + CFG.PADDING;
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "Draw Provinces ", this.getPosX() + this.getWidth() + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "" + CFG.getPrecision2(Settings_Menu.drawProvinces_Time / (float)totalTime * 100.0f, 1) + "%", this.getPosX() + this.getWidth() + this.statTextW + CFG.PADDING * 2 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, CFG.getNumberWithSpaces("" + Settings_Menu.drawProvinces_Time), this.getPosX() + this.getWidth() + this.statTextW + this.statTextPercW + CFG.PADDING * 3 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            extraY += CFG.TEXT_HEIGHT_SMALL + CFG.PADDING;
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "Provinces FBO ", this.getPosX() + this.getWidth() + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "" + CFG.getPrecision2(Settings_Menu.drawProvincesFBO_Time / (float)totalTime * 100.0f, 1) + "%", this.getPosX() + this.getWidth() + this.statTextW + CFG.PADDING * 2 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, CFG.getNumberWithSpaces("" + Settings_Menu.drawProvincesFBO_Time), this.getPosX() + this.getWidth() + this.statTextW + this.statTextPercW + CFG.PADDING * 3 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            extraY += CFG.TEXT_HEIGHT_SMALL + CFG.PADDING;
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "Provinces Border ", this.getPosX() + this.getWidth() + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "" + CFG.getPrecision2(Settings_Menu.drawProvincesBorder_Time / (float)totalTime * 100.0f, 1) + "%", this.getPosX() + this.getWidth() + this.statTextW + CFG.PADDING * 2 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, CFG.getNumberWithSpaces("" + Settings_Menu.drawProvincesBorder_Time), this.getPosX() + this.getWidth() + this.statTextW + this.statTextPercW + CFG.PADDING * 3 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            extraY += CFG.TEXT_HEIGHT_SMALL + CFG.PADDING;
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "Provinces Names ", this.getPosX() + this.getWidth() + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "" + CFG.getPrecision2(Settings_Menu.drawProvincesNames_Time / (float)totalTime * 100.0f, 1) + "%", this.getPosX() + this.getWidth() + this.statTextW + CFG.PADDING * 2 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, CFG.getNumberWithSpaces("" + Settings_Menu.drawProvincesNames_Time), this.getPosX() + this.getWidth() + this.statTextW + this.statTextPercW + CFG.PADDING * 3 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            extraY += CFG.TEXT_HEIGHT_SMALL + CFG.PADDING;
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "Civs Names ", this.getPosX() + this.getWidth() + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "" + CFG.getPrecision2(Settings_Menu.drawCivsNames_Time / (float)totalTime * 100.0f, 1) + "%", this.getPosX() + this.getWidth() + this.statTextW + CFG.PADDING * 2 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, CFG.getNumberWithSpaces("" + Settings_Menu.drawCivsNames_Time), this.getPosX() + this.getWidth() + this.statTextW + this.statTextPercW + CFG.PADDING * 3 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            extraY += CFG.TEXT_HEIGHT_SMALL + CFG.PADDING;
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "Cities & Flags ", this.getPosX() + this.getWidth() + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "" + CFG.getPrecision2(Settings_Menu.drawCities_Time / (float)totalTime * 100.0f, 1) + "%", this.getPosX() + this.getWidth() + this.statTextW + CFG.PADDING * 2 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, CFG.getNumberWithSpaces("" + Settings_Menu.drawCities_Time), this.getPosX() + this.getWidth() + this.statTextW + this.statTextPercW + CFG.PADDING * 3 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            extraY += CFG.TEXT_HEIGHT_SMALL + CFG.PADDING;
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "Clouds ", this.getPosX() + this.getWidth() + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "" + CFG.getPrecision2(Settings_Menu.drawClouds_Time / (float)totalTime * 100.0f, 1) + "%", this.getPosX() + this.getWidth() + this.statTextW + CFG.PADDING * 2 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, CFG.getNumberWithSpaces("" + Settings_Menu.drawClouds_Time), this.getPosX() + this.getWidth() + this.statTextW + this.statTextPercW + CFG.PADDING * 3 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            extraY += CFG.TEXT_HEIGHT_SMALL + CFG.PADDING;
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "Ships ", this.getPosX() + this.getWidth() + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "" + CFG.getPrecision2(Settings_Menu.drawShips_Time / (float)totalTime * 100.0f, 1) + "%", this.getPosX() + this.getWidth() + this.statTextW + CFG.PADDING * 2 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, CFG.getNumberWithSpaces("" + Settings_Menu.drawShips_Time), this.getPosX() + this.getWidth() + this.statTextW + this.statTextPercW + CFG.PADDING * 3 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            extraY += CFG.TEXT_HEIGHT_SMALL + CFG.PADDING;
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "Ships 2 ", this.getPosX() + this.getWidth() + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "" + CFG.getPrecision2(Settings_Menu.drawShips2_Time / (float)totalTime * 100.0f, 1) + "%", this.getPosX() + this.getWidth() + this.statTextW + CFG.PADDING * 2 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, CFG.getNumberWithSpaces("" + Settings_Menu.drawShips2_Time), this.getPosX() + this.getWidth() + this.statTextW + this.statTextPercW + CFG.PADDING * 3 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            extraY += CFG.TEXT_HEIGHT_SMALL + CFG.PADDING;
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "Armies ", this.getPosX() + this.getWidth() + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "" + CFG.getPrecision2(Settings_Menu.drawArmies_Time / (float)totalTime * 100.0f, 1) + "%", this.getPosX() + this.getWidth() + this.statTextW + CFG.PADDING * 2 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, CFG.getNumberWithSpaces("" + Settings_Menu.drawArmies_Time), this.getPosX() + this.getWidth() + this.statTextW + this.statTextPercW + CFG.PADDING * 3 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            extraY += CFG.TEXT_HEIGHT_SMALL + CFG.PADDING;
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "Move Units ", this.getPosX() + this.getWidth() + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "" + CFG.getPrecision2(Settings_Menu.drawMoveUnits_Time / (float)totalTime * 100.0f, 1) + "%", this.getPosX() + this.getWidth() + this.statTextW + CFG.PADDING * 2 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, CFG.getNumberWithSpaces("" + Settings_Menu.drawMoveUnits_Time), this.getPosX() + this.getWidth() + this.statTextW + this.statTextPercW + CFG.PADDING * 3 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            extraY += CFG.TEXT_HEIGHT_SMALL + CFG.PADDING;
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "Map Border ", this.getPosX() + this.getWidth() + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "" + CFG.getPrecision2(Settings_Menu.drawMapBorder_Time / (float)totalTime * 100.0f, 1) + "%", this.getPosX() + this.getWidth() + this.statTextW + CFG.PADDING * 2 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, CFG.getNumberWithSpaces("" + Settings_Menu.drawMapBorder_Time), this.getPosX() + this.getWidth() + this.statTextW + this.statTextPercW + CFG.PADDING * 3 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            extraY += CFG.TEXT_HEIGHT_SMALL + CFG.PADDING;
            extraY += CFG.TEXT_HEIGHT_SMALL + CFG.PADDING;
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "1 FPS", this.getPosX() + this.getWidth() + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "", this.getPosX() + this.getWidth() + this.statTextW + CFG.PADDING * 2 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "16 666 667", this.getPosX() + this.getWidth() + this.statTextW + this.statTextPercW + CFG.PADDING * 3 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop600, Images.insideBot600);
        ImageManager.getImage(Images.rulerOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.rulerOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.rulerOver).getHeight()));
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void updateLanguage() {
        super.updateLanguage();
    }
    
    static {
        Settings_Menu.goBackToMenu = View.MAINMENU;
        Settings_Menu.provinceInView_Time = 0L;
        Settings_Menu.drawProvinces_Time = 0L;
        Settings_Menu.drawProvincesFBO_Time = 0L;
        Settings_Menu.drawArmies_Time = 0L;
        Settings_Menu.drawProvincesBorder_Time = 0L;
        Settings_Menu.drawProvincesNames_Time = 0L;
        Settings_Menu.drawCivsNames_Time = 0L;
        Settings_Menu.drawCities_Time = 0L;
        Settings_Menu.drawMoveUnits_Time = 0L;
        Settings_Menu.drawClouds_Time = 0L;
        Settings_Menu.drawShips_Time = 0L;
        Settings_Menu.drawShips2_Time = 0L;
        Settings_Menu.drawMapBorder_Time = 0L;
        Settings_Menu.updateTimes = true;
        Settings_Menu.lastUpdateTime = 0L;
    }
}
