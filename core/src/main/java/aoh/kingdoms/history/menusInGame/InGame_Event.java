// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG_DoubleText;
import aoh.kingdoms.history.menu_element.Empty;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.mainGame.Steam.SteamAchievementsManager;
import aoh.kingdoms.history.mainGame.Missions.MissionTree;
import aoh.kingdoms.history.mainGame.SoundsManager;
import aoh.kingdoms.history.menu_element.button.ButtonGame_Value;
import aoh.kingdoms.history.menu_element.textStatic.Text_Desc;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.map.ResourcesManager;
import aoh.kingdoms.history.events.EventOption;
import aoh.kingdoms.history.events.outcome.EventOutcome;
import aoh.kingdoms.history.events.EventsManager;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.events.Event;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Event extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    public Event event;
    public static int eventType;
    public static int eventID;
    public int imgWidth;
    public int imgHeight;
    
    public InGame_Event(final Event nEvent, final int nEventType, final int nEventID) {
        this.imgWidth = 1;
        this.imgHeight = 1;
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        this.event = nEvent;
        InGame_Event.eventType = nEventType;
        InGame_Event.eventID = nEventID;
        final int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title600).getHeight();
        final int menuWidth = ImageManager.getImage(Images.title600).getWidth();
        final int menuX = CFG.BUTTON_WIDTH + Renderer.boxBGExtraY + CFG.PADDING;
        final int menuY = ImageManager.getImage(Images.topStats).getHeight() + CFG.PADDING * 2 + ImageManager.getImage(Images.title600).getHeight();
        int buttonY = CFG.PADDING * 2;
        final int buttonX = Images.boxTitleBORDERWIDTH;
        EventsManager.loadEventIMG(this.event.image);
        try {
            final float fScale = (menuWidth - Images.boxTitleBORDERWIDTH * 2) / (float)EventsManager.eventIMG.getWidth();
            this.imgWidth = menuWidth - Images.boxTitleBORDERWIDTH * 2;
            this.imgHeight = (int)(EventsManager.eventIMG.getHeight() * fScale);
            buttonY += this.imgHeight;
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        if (InGame_Event.eventType == 2 || InGame_Event.eventType == 5) {
            String sResource = "";
            String sPriceChange = "";
            try {
                sResource = ResourcesManager.getResourceName(this.event.options.get(0).outcome.get(0).getValue1());
                sPriceChange = CFG.getPrecision2(this.event.options.get(0).outcome.get(0).getValue2(), 10);
            }
            catch (final Exception ex2) {
                CFG.exceptionStack(ex2);
            }
            menuElements.add(new Text_Desc(Game.lang.get(this.event.desc, sResource, sPriceChange), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
        }
        else {
            menuElements.add(new Text_Desc(Game.lang.get(this.event.desc), paddingLeft, buttonY, menuWidth - paddingLeft * 2));
        }
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        for (int i = 0; i < this.event.options.size(); ++i) {
            menuElements.add(new ButtonGame_Value(Game.lang.get(this.event.options.get(i).name), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true, i) {
                @Override
                public void actionElement() {
                    Game.soundsManager.playSound(SoundsManager.SOUND_EVENT_CLICK);
                    Game.player.removeActiveEvent(InGame_Event.eventType, InGame_Event.eventID);
                    if (InGame_Event.eventType == 999) {
                        MissionTree.takeMissionDecision(Game.player.iCivID, InGame_Event.eventID, this.getCurrent());
                    }
                    else if (InGame_Event.eventType == 1000) {
                        MissionTree.takeMissionDecision_Civ(Game.player.iCivID, InGame_Event.eventID, this.getCurrent());
                        Game.player.currSituation.updateCurrentSituation();
                    }
                    else {
                        EventsManager.takeEventDecision(Game.player.iCivID, InGame_Event.eventType, InGame_Event.eventID, this.getCurrent());
                        Game.player.currSituation.updateCurrentSituation();
                    }
                    Game.menuManager.rebuildInGame_Right();
                    Game.menuManager.setVisibleInGame_Event(false);
                    SteamAchievementsManager.unlockAchievement(SteamAchievementsManager.EVENT_RES);
                }
                
                @Override
                public void buildElementHover() {
                    final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                    final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    try {
                        for (int i = 0; i < InGame_Event.this.event.options.get(this.getCurrent()).outcome.size(); ++i) {
                            if (InGame_Event.this.event.options.get(this.getCurrent()).outcome.get(i).getStringLeft() != null) {
                                nData.add(new MenuElement_HoverElement_Type_Text(InGame_Event.this.event.options.get(this.getCurrent()).outcome.get(i).getStringLeft(), CFG.FONT_REGULAR_SMALL));
                                if (InGame_Event.this.event.options.get(this.getCurrent()).outcome.get(i).getStringRight() != null) {
                                    nData.add(new MenuElement_HoverElement_Type_Text(InGame_Event.this.event.options.get(this.getCurrent()).outcome.get(i).getStringRight(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                                }
                                if (InGame_Event.this.event.options.get(this.getCurrent()).outcome.get(i).getImage() >= 0) {
                                    nData.add(new MenuElement_HoverElement_Type_Image(InGame_Event.this.event.options.get(this.getCurrent()).outcome.get(i).getImage(), CFG.PADDING, (InGame_Event.this.event.options.get(this.getCurrent()).outcome.get(i).getStringRight2(InGame_Event.this.event.options.get(this.getCurrent()).bonus_duration) != null) ? CFG.PADDING : 0));
                                }
                                if (InGame_Event.this.event.options.get(this.getCurrent()).outcome.get(i).getStringRight2(InGame_Event.this.event.options.get(this.getCurrent()).bonus_duration) != null) {
                                    nData.add(new MenuElement_HoverElement_Type_Text(InGame_Event.this.event.options.get(this.getCurrent()).outcome.get(i).getStringRight2(InGame_Event.this.event.options.get(this.getCurrent()).bonus_duration), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                                }
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                        }
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                    this.menuElementHover = new MenuElement_Hover(nElements, nElements.size() == 1);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        buttonY = 0;
        for (int i = 0, iSize = menuElements.size(); i < iSize; ++i) {
            if (buttonY < menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING * 2) {
                buttonY = menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING * 2;
            }
        }
        final int tMenuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 2);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, tMenuHeight)));
        int inProvinceID = Game.getCiv(Game.player.iCivID).eventProvinceID;
        if (inProvinceID < 0 && Game.getCiv(Game.player.iCivID).getCapitalProvinceID() >= 0) {
            inProvinceID = Game.getCiv(Game.player.iCivID).getCapitalProvinceID();
        }
        else if (Game.getCiv(Game.player.iCivID).getNumOfProvinces() > 0) {
            inProvinceID = Game.getCiv(Game.player.iCivID).getProvinceID(Game.oR.nextInt(Game.getCiv(Game.player.iCivID).getNumOfProvinces()));
        }
        inProvinceID = Math.max(0, inProvinceID);
        this.initMenu(new MenuTitleIMG_DoubleText(Game.lang.get(this.event.title), Game.lang.get("EventInX", Game.getProvince(inProvinceID).getProvinceName()), true, false, Images.title600) {
            @Override
            public long getTime() {
                return InGame_Event.lTime;
            }
        }, CFG.GAME_WIDTH / 2 - menuWidth / 2, CFG.GAME_HEIGHT / 5, menuWidth, tMenuHeight, menuElements, false, true);
        this.drawScrollPositionAlways = false;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_Event.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateY = iTranslateY - CFG.BUTTON_HEIGHT + (int)(CFG.BUTTON_HEIGHT * ((CFG.currentTimeMillis - InGame_Event.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop600, Images.insideBot600);
        oSB.setColor(Color.WHITE);
        try {
            EventsManager.eventIMG.draw(oSB, this.getPosX() + Images.boxTitleBORDERWIDTH + iTranslateX, this.getPosY() + iTranslateY, this.imgWidth, this.imgHeight);
            Renderer.drawBox(oSB, Images.eventCorner, this.getPosX() + Images.boxTitleBORDERWIDTH + iTranslateX, this.getPosY() + iTranslateY, this.imgWidth, this.imgHeight, 1.0f);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
            ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + Images.boxTitleBORDERWIDTH + iTranslateX, this.getPosY() + this.imgHeight + iTranslateY, this.imgWidth, CFG.PADDING * 2);
            Images.gradientXY.draw(oSB, this.getPosX() + Images.boxTitleBORDERWIDTH + iTranslateX, this.getPosY() + this.imgHeight + iTranslateY, this.imgWidth, CFG.PADDING * 2, false, true);
            oSB.setColor(Color.WHITE);
        }
        catch (final Exception ex) {}
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_Event.lTime = CFG.currentTimeMillis;
    }
    
    static {
        InGame_Event.lTime = 0L;
        InGame_Event.eventType = 0;
        InGame_Event.eventID = 0;
    }
}
