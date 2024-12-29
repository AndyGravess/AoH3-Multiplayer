// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import aoh.kingdoms.history.menu_element.button.Button32Padd;
import aoh.kingdoms.history.menu_element.Minimap;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu_element.button.Button_NotificationResource;
import aoh.kingdoms.history.menu_element.button.Button_NotificationResource_Green;
import aoh.kingdoms.history.menu_element.button.Button_NotificationResource_Red;
import aoh.kingdoms.history.menu_element.button.Button_Notification;
import aoh.kingdoms.history.menu_element.button.Button_Notification_Green;
import aoh.kingdoms.history.menu_element.button.Button_Notification_Red;
import aoh.kingdoms.history.menu_element.button.Button_Notification_Flag;
import aoh.kingdoms.history.menu_element.button.Button_Notification_Flag_Green;
import aoh.kingdoms.history.menu.MenuManager;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.ClickAnimation;
import aoh.kingdoms.history.menu_element.button.Button_Notification_Flag_Red;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.mainGame.Player.Notification.Notification;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Notifications extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public static boolean stateVisible;
    
    public InGame_Notifications() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = 1;
        final int paddingRight = CFG.PADDING;
        final int titleHeight = ImageManager.getImage(Images.title2).getHeight();
        final int menuWidth = Game.mapBG.iMinimapWidth + Images.boxTitleBORDERWIDTH;
        final int menuX = CFG.GAME_WIDTH - menuWidth;
        final int menuY = ImageManager.getImage(Images.topStats).getHeight() + CFG.PADDING * 2;
        final int buttonYPadding = CFG.PADDING;
        int buttonY = 0;
        final int buttonX = paddingLeft;
        final int buttonH = Math.max(CFG.BUTTON_HEIGHT / 2, Math.max(ImageManager.getImage(Images.gold).getHeight(), CFG.TEXT_HEIGHT) + CFG.PADDING);
        if (Game.player.iNotificationsSize < GameValues.notifications.NUMBER_OF_NOTIFICATIONS) {
            buttonY += (buttonH + CFG.PADDING) * (GameValues.notifications.NUMBER_OF_NOTIFICATIONS - Game.player.iNotificationsSize);
        }
        try {
            for (int i = 0; i < Game.player.iNotificationsSize; ++i) {
                if (Game.player.lNotifications.get(i).notificationType != Notification.Notification_Type.PRICE_CHANGE && Game.player.lNotifications.get(i).notificationType != Notification.Notification_Type.LARGEST_PRODUCER) {
                    if (Game.player.lNotifications.get(i).notificationType != Notification.Notification_Type.NO_LONGER_LARGEST_PRODUCER) {
                        if (Game.player.lNotifications.get(i).notificationType == Notification.Notification_Type.RELATIONS_IMPROVING || Game.player.lNotifications.get(i).notificationType == Notification.Notification_Type.RELATIONS_DAMAGING || Game.player.lNotifications.get(i).notificationType == Notification.Notification_Type.NEIGHBOR_OR_RIVAL_AT_WAR || Game.player.lNotifications.get(i).notificationType == Notification.Notification_Type.RELATIONS || Game.player.lNotifications.get(i).notificationType == Notification.Notification_Type.RELATIONS_COMPLETED || Game.player.lNotifications.get(i).notificationType == Notification.Notification_Type.ALLIANCE_EXPIRED || Game.player.lNotifications.get(i).notificationType == Notification.Notification_Type.LEADER_DIED || Game.player.lNotifications.get(i).notificationType == Notification.Notification_Type.ADVISOR_DIED || Game.player.lNotifications.get(i).notificationType == Notification.Notification_Type.GENERAL_DIED) {
                            switch (Game.player.lNotifications.get(i).notificationBG) {
                                case RED: {
                                    menuElements.add(new Button_Notification_Flag_Red(Game.player.lNotifications.get(i).sText, Game_Calendar.getDate_ByTurnID_MessageShort(Game.player.lNotifications.get(i).iTurnID), paddingLeft, buttonY, menuWidth - paddingRight - paddingLeft, buttonH, Game.player.lNotifications.get(i).imageID, i, Game.player.lNotifications.get(i).lTime, Game.player.lNotifications.get(i).id) {
                                        @Override
                                        public void actionElement() {
                                            super.actionElement();
                                            final MenuManager menuManager = Game.menuManager;
                                            MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Notifications.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Notifications.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                                @Override
                                                public Color getColor() {
                                                    return Colors.COLOR_NOTIFICATION_OVER_RED;
                                                }
                                            });
                                        }
                                    });
                                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                                    continue;
                                }
                                case GREEN: {
                                    menuElements.add(new Button_Notification_Flag_Green(Game.player.lNotifications.get(i).sText, Game_Calendar.getDate_ByTurnID_MessageShort(Game.player.lNotifications.get(i).iTurnID), paddingLeft, buttonY, menuWidth - paddingRight - paddingLeft, buttonH, Game.player.lNotifications.get(i).imageID, i, Game.player.lNotifications.get(i).lTime, Game.player.lNotifications.get(i).id) {
                                        @Override
                                        public void actionElement() {
                                            super.actionElement();
                                            final MenuManager menuManager = Game.menuManager;
                                            MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Notifications.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Notifications.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                                @Override
                                                public Color getColor() {
                                                    return Colors.COLOR_NOTIFICATION_OVER_GREEN;
                                                }
                                            });
                                        }
                                    });
                                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                                    continue;
                                }
                                default: {
                                    menuElements.add(new Button_Notification_Flag(Game.player.lNotifications.get(i).sText, Game_Calendar.getDate_ByTurnID_MessageShort(Game.player.lNotifications.get(i).iTurnID), paddingLeft, buttonY, menuWidth - paddingRight - paddingLeft, buttonH, Game.player.lNotifications.get(i).imageID, i, Game.player.lNotifications.get(i).lTime, Game.player.lNotifications.get(i).id) {
                                        @Override
                                        public void actionElement() {
                                            super.actionElement();
                                            final MenuManager menuManager = Game.menuManager;
                                            MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Notifications.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Notifications.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                                @Override
                                                public Color getColor() {
                                                    return Colors.COLOR_NOTIFICATION_OVER;
                                                }
                                            });
                                        }
                                    });
                                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                                    continue;
                                }
                            }
                        }
                        else if (Game.player.lNotifications.get(i).notificationType == Notification.Notification_Type.SIEGE || Game.player.lNotifications.get(i).notificationType == Notification.Notification_Type.BATTLE_REPORT || Game.player.lNotifications.get(i).notificationType == Notification.Notification_Type.HIGH_UNREST || Game.player.lNotifications.get(i).notificationType == Notification.Notification_Type.SETTLEMENT_ESTABLISHED || Game.player.lNotifications.get(i).notificationType == Notification.Notification_Type.ARMY_DESTROYED || Game.player.lNotifications.get(i).notificationType == Notification.Notification_Type.DISEASE || Game.player.lNotifications.get(i).notificationType == Notification.Notification_Type.REVOLT) {
                            switch (Game.player.lNotifications.get(i).notificationBG) {
                                case RED: {
                                    menuElements.add(new Button_Notification_Flag_Red(Game.player.lNotifications.get(i).sText, Game_Calendar.getDate_ByTurnID_MessageShort(Game.player.lNotifications.get(i).iTurnID), paddingLeft, buttonY, menuWidth - paddingRight - paddingLeft, buttonH, Game.player.lNotifications.get(i).imageID, i, Game.player.lNotifications.get(i).lTime, Game.getProvince(Game.player.lNotifications.get(i).id).getCivID()) {
                                        @Override
                                        public void actionElement() {
                                            super.actionElement();
                                            final MenuManager menuManager = Game.menuManager;
                                            MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Notifications.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Notifications.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                                @Override
                                                public Color getColor() {
                                                    return Colors.COLOR_NOTIFICATION_OVER_RED;
                                                }
                                            });
                                        }
                                    });
                                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                                    continue;
                                }
                                case GREEN: {
                                    menuElements.add(new Button_Notification_Flag_Green(Game.player.lNotifications.get(i).sText, Game_Calendar.getDate_ByTurnID_MessageShort(Game.player.lNotifications.get(i).iTurnID), paddingLeft, buttonY, menuWidth - paddingRight - paddingLeft, buttonH, Game.player.lNotifications.get(i).imageID, i, Game.player.lNotifications.get(i).lTime, Game.getProvince(Game.player.lNotifications.get(i).id).getCivID()) {
                                        @Override
                                        public void actionElement() {
                                            super.actionElement();
                                            final MenuManager menuManager = Game.menuManager;
                                            MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Notifications.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Notifications.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                                @Override
                                                public Color getColor() {
                                                    return Colors.COLOR_NOTIFICATION_OVER_GREEN;
                                                }
                                            });
                                        }
                                    });
                                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                                    continue;
                                }
                                default: {
                                    menuElements.add(new Button_Notification_Flag(Game.player.lNotifications.get(i).sText, Game_Calendar.getDate_ByTurnID_MessageShort(Game.player.lNotifications.get(i).iTurnID), paddingLeft, buttonY, menuWidth - paddingRight - paddingLeft, buttonH, Game.player.lNotifications.get(i).imageID, i, Game.player.lNotifications.get(i).lTime, Game.getProvince(Game.player.lNotifications.get(i).id).getCivID()) {
                                        @Override
                                        public void actionElement() {
                                            super.actionElement();
                                            final MenuManager menuManager = Game.menuManager;
                                            MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Notifications.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Notifications.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                                @Override
                                                public Color getColor() {
                                                    return Colors.COLOR_NOTIFICATION_OVER;
                                                }
                                            });
                                        }
                                    });
                                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                                    continue;
                                }
                            }
                        }
                        else {
                            switch (Game.player.lNotifications.get(i).notificationBG) {
                                case RED: {
                                    menuElements.add(new Button_Notification_Red(Game.player.lNotifications.get(i).sText, Game_Calendar.getDate_ByTurnID_MessageShort(Game.player.lNotifications.get(i).iTurnID), paddingLeft, buttonY, menuWidth - paddingRight - paddingLeft, buttonH, Game.player.lNotifications.get(i).imageID, i, Game.player.lNotifications.get(i).lTime) {
                                        @Override
                                        public void actionElement() {
                                            super.actionElement();
                                            final MenuManager menuManager = Game.menuManager;
                                            MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Notifications.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Notifications.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                                @Override
                                                public Color getColor() {
                                                    return Colors.COLOR_NOTIFICATION_OVER_RED;
                                                }
                                            });
                                        }
                                    });
                                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                                    continue;
                                }
                                case GREEN: {
                                    menuElements.add(new Button_Notification_Green(Game.player.lNotifications.get(i).sText, Game_Calendar.getDate_ByTurnID_MessageShort(Game.player.lNotifications.get(i).iTurnID), paddingLeft, buttonY, menuWidth - paddingRight - paddingLeft, buttonH, Game.player.lNotifications.get(i).imageID, i, Game.player.lNotifications.get(i).lTime) {
                                        @Override
                                        public void actionElement() {
                                            super.actionElement();
                                            final MenuManager menuManager = Game.menuManager;
                                            MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Notifications.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Notifications.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                                @Override
                                                public Color getColor() {
                                                    return Colors.COLOR_NOTIFICATION_OVER_GREEN;
                                                }
                                            });
                                        }
                                    });
                                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                                    continue;
                                }
                                default: {
                                    menuElements.add(new Button_Notification(Game.player.lNotifications.get(i).sText, Game_Calendar.getDate_ByTurnID_MessageShort(Game.player.lNotifications.get(i).iTurnID), paddingLeft, buttonY, menuWidth - paddingRight - paddingLeft, buttonH, Game.player.lNotifications.get(i).imageID, i, Game.player.lNotifications.get(i).lTime) {
                                        @Override
                                        public void actionElement() {
                                            super.actionElement();
                                            final MenuManager menuManager = Game.menuManager;
                                            MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Notifications.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Notifications.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                                @Override
                                                public Color getColor() {
                                                    return Colors.COLOR_NOTIFICATION_OVER;
                                                }
                                            });
                                        }
                                    });
                                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                                    continue;
                                }
                            }
                        }
                    }
                }
                try {
                    switch (Game.player.lNotifications.get(i).notificationBG) {
                        case RED: {
                            menuElements.add(new Button_NotificationResource_Red(Game.player.lNotifications.get(i).sText, Game_Calendar.getDate_ByTurnID_MessageShort(Game.player.lNotifications.get(i).iTurnID), paddingLeft, buttonY, menuWidth - paddingRight - paddingLeft, buttonH, Game.player.lNotifications.get(i).imageID, i, Game.player.lNotifications.get(i).lTime) {
                                @Override
                                public void actionElement() {
                                    super.actionElement();
                                    final MenuManager menuManager = Game.menuManager;
                                    MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Notifications.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Notifications.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                        @Override
                                        public Color getColor() {
                                            return Colors.COLOR_NOTIFICATION_OVER_RED;
                                        }
                                    });
                                }
                            });
                            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                            break;
                        }
                        case GREEN: {
                            menuElements.add(new Button_NotificationResource_Green(Game.player.lNotifications.get(i).sText, Game_Calendar.getDate_ByTurnID_MessageShort(Game.player.lNotifications.get(i).iTurnID), paddingLeft, buttonY, menuWidth - paddingRight - paddingLeft, buttonH, Game.player.lNotifications.get(i).imageID, i, Game.player.lNotifications.get(i).lTime) {
                                @Override
                                public void actionElement() {
                                    super.actionElement();
                                    final MenuManager menuManager = Game.menuManager;
                                    MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Notifications.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Notifications.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                        @Override
                                        public Color getColor() {
                                            return Colors.COLOR_NOTIFICATION_OVER_GREEN;
                                        }
                                    });
                                }
                            });
                            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                            break;
                        }
                        default: {
                            menuElements.add(new Button_NotificationResource(Game.player.lNotifications.get(i).sText, Game_Calendar.getDate_ByTurnID_MessageShort(Game.player.lNotifications.get(i).iTurnID), paddingLeft, buttonY, menuWidth - paddingRight - paddingLeft, buttonH, Game.player.lNotifications.get(i).imageID, i, Game.player.lNotifications.get(i).lTime) {
                                @Override
                                public void actionElement() {
                                    super.actionElement();
                                    final MenuManager menuManager = Game.menuManager;
                                    MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Notifications.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Notifications.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                        @Override
                                        public Color getColor() {
                                            return Colors.COLOR_NOTIFICATION_OVER;
                                        }
                                    });
                                }
                            });
                            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
                            break;
                        }
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
        buttonY = 0;
        for (int i = 0, iSize = menuElements.size(); i < iSize; ++i) {
            if (buttonY < menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING) {
                buttonY = menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING;
            }
        }
        final int menuHeight = (buttonH + CFG.PADDING) * GameValues.notifications.NUMBER_OF_NOTIFICATIONS;
        this.initMenu(null, menuX, menuY, menuWidth, menuHeight, menuElements, true, false);
        this.drawScrollPositionAlways2 = false;
        this.setMenuPosY(this.getPosY() - 990000);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_Notifications.lTime = CFG.currentTimeMillis;
        InGame_Notifications.stateVisible = visible;
    }
    
    @Override
    public boolean getVisible() {
        return super.getVisible() && Game.mapBG.getHideMenuZoomOut() && !Game.menuManager.getVisibleInGame_CivBonuses();
    }
    
    @Override
    public int getMenuPosY() {
        if (this.getScrollableY()) {
            return super.getMenuPosY();
        }
        return CFG.GAME_HEIGHT - Game.mapBG.minimapOfCivilizations.getHeight() - Minimap.getPadding() * 2 - this.getHeight() + InGame.iMinimapPosY - Button32Padd.getButtonHeight() - CFG.PADDING * 4;
    }
    
    @Override
    public int getPosY() {
        return CFG.GAME_HEIGHT - Game.mapBG.minimapOfCivilizations.getHeight() - Minimap.getPadding() * 2 - this.getHeight() + InGame.iMinimapPosY - Button32Padd.getButtonHeight() - CFG.PADDING * 4;
    }
    
    static {
        InGame_Notifications.lTime = 0L;
        InGame_Notifications.stateVisible = false;
    }
}
