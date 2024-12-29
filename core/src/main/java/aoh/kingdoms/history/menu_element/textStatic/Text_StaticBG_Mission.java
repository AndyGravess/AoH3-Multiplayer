// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.events.triggers.EventTrigger_Value;
import aoh.kingdoms.history.events.triggers.EventTrigger;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Title;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.events.EventsManager;
import aoh.kingdoms.history.textures.Image;

public class Text_StaticBG_Mission extends Text_StaticBG_ID
{
    public int missionImage;
    public int eventType;
    
    public Text_StaticBG_Mission(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int iWidth, final int eventType, final int id, final int missionImage) {
        super(sText, fontID, iTextPositionX, iPosX, iPosY, iWidth, EventsManager.missionImages.get(missionImage).getHeight(), id);
        this.missionImage = 0;
        this.eventType = 0;
        this.eventType = eventType;
        this.missionImage = missionImage;
    }
    
    public void drawBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.5f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        this.drawBG(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, (this.getIsHovered() || isActive) ? 0.65f : 0.35f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.3f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.35f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.3f));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), CFG.PADDING * 2, false, true);
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING * 2 + iTranslateY, this.getWidth(), CFG.PADDING * 2);
        try {
            oSB.setColor(Color.WHITE);
            EventsManager.missionImages.get(this.missionImage).draw(oSB, this.getPosX() + this.getWidth() - EventsManager.missionImages.get(this.missionImage).getWidth() + iTranslateX, this.getPosY() + iTranslateY);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.25f));
        Renderer.drawBox(oSB, Images.statsRectBGBorder, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.85f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.9f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sText, this.getPosX() + this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + (this.getHeight() - CFG.TEXT_HEIGHT) / 2 + iTranslateY, this.getColor(isActive));
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        return Colors.getColorTopStats4(isActive, this.getIsHovered());
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_Title(Images.missions, "", this.getText(), Colors.HOVER_GOLD, Game.lang.get("Mission"), "", Colors.HOVER_RIGHT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        final String sInner = "- ";
        boolean addType = true;
        if (EventsManager.getActiveEvent(this.id, this.eventType).mission_desc.length() > 0) {
            nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get(EventsManager.getActiveEvent(this.id, this.eventType).mission_desc), CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT2));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        try {
            for (int i = 0; i < EventsManager.getActiveEvent(this.id, this.eventType).triggersAnd.size(); ++i) {
                for (int j = 0; j < EventsManager.getActiveEvent(this.id, this.eventType).triggersAnd.get(i).triggersAnd.size(); ++j) {
                    if (EventsManager.getActiveEvent(this.id, this.eventType).triggersAnd.get(i).triggersAnd.get(j).getText().length() > 0) {
                        if (addType) {
                            addType = false;
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(sInner + EventsManager.getActiveEvent(this.id, this.eventType).triggersAnd.get(i).triggersAnd.get(j).getText(), CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + EventsManager.getActiveEvent(this.id, this.eventType).triggersAnd.get(i).triggersAnd.get(j).getText2(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + EventsManager.getActiveEvent(this.id, this.eventType).triggersAnd.get(i).triggersAnd.get(j).getText3(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        if (EventsManager.getActiveEvent(this.id, this.eventType).triggersAnd.get(i).triggersAnd.get(j).getImage() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Image(EventsManager.getActiveEvent(this.id, this.eventType).triggersAnd.get(i).triggersAnd.get(j).getImage(), CFG.PADDING, 0));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(" (" + Game.lang.get("and") + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
                for (int j = 0; j < EventsManager.getActiveEvent(this.id, this.eventType).triggersAnd.get(i).triggersOr.size(); ++j) {
                    if (EventsManager.getActiveEvent(this.id, this.eventType).triggersAnd.get(i).triggersOr.get(j).getText().length() > 0) {
                        if (addType) {
                            addType = false;
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(sInner + EventsManager.getActiveEvent(this.id, this.eventType).triggersAnd.get(i).triggersOr.get(j).getText(), CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + EventsManager.getActiveEvent(this.id, this.eventType).triggersAnd.get(i).triggersOr.get(j).getText2(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + EventsManager.getActiveEvent(this.id, this.eventType).triggersAnd.get(i).triggersOr.get(j).getText3(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        if (EventsManager.getActiveEvent(this.id, this.eventType).triggersAnd.get(i).triggersOr.get(j).getImage() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Image(EventsManager.getActiveEvent(this.id, this.eventType).triggersAnd.get(i).triggersOr.get(j).getImage(), CFG.PADDING, 0));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(" (" + Game.lang.get("or") + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
                for (int j = 0; j < EventsManager.getActiveEvent(this.id, this.eventType).triggersAnd.get(i).triggersAndNot.size(); ++j) {
                    if (EventsManager.getActiveEvent(this.id, this.eventType).triggersAnd.get(i).triggersAndNot.get(j).getText().length() > 0) {
                        if (addType) {
                            addType = false;
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(sInner + EventsManager.getActiveEvent(this.id, this.eventType).triggersAnd.get(i).triggersAndNot.get(j).getText(), CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + EventsManager.getActiveEvent(this.id, this.eventType).triggersAnd.get(i).triggersAndNot.get(j).getText2(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + EventsManager.getActiveEvent(this.id, this.eventType).triggersAnd.get(i).triggersAndNot.get(j).getText3(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        if (EventsManager.getActiveEvent(this.id, this.eventType).triggersAnd.get(i).triggersAndNot.get(j).getImage() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Image(EventsManager.getActiveEvent(this.id, this.eventType).triggersAnd.get(i).triggersAndNot.get(j).getImage(), CFG.PADDING, 0));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(" (" + Game.lang.get("andNot") + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
                for (int j = 0; j < EventsManager.getActiveEvent(this.id, this.eventType).triggersAnd.get(i).triggersOrNot.size(); ++j) {
                    if (EventsManager.getActiveEvent(this.id, this.eventType).triggersAnd.get(i).triggersOrNot.get(j).getText().length() > 0) {
                        if (addType) {
                            addType = false;
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(sInner + EventsManager.getActiveEvent(this.id, this.eventType).triggersAnd.get(i).triggersOrNot.get(j).getText(), CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + EventsManager.getActiveEvent(this.id, this.eventType).triggersAnd.get(i).triggersOrNot.get(j).getText2(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + EventsManager.getActiveEvent(this.id, this.eventType).triggersAnd.get(i).triggersOrNot.get(j).getText3(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        if (EventsManager.getActiveEvent(this.id, this.eventType).triggersAnd.get(i).triggersOrNot.get(j).getImage() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Image(EventsManager.getActiveEvent(this.id, this.eventType).triggersAnd.get(i).triggersOrNot.get(j).getImage(), CFG.PADDING, 0));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(" (" + Game.lang.get("orNot") + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
            }
            addType = true;
            for (int i = 0; i < EventsManager.getActiveEvent(this.id, this.eventType).triggersAndNot.size(); ++i) {
                for (int j = 0; j < EventsManager.getActiveEvent(this.id, this.eventType).triggersAndNot.get(i).triggersAnd.size(); ++j) {
                    if (EventsManager.getActiveEvent(this.id, this.eventType).triggersAndNot.get(i).triggersAnd.get(j).getText().length() > 0) {
                        if (addType) {
                            addType = false;
                            nData.add(new MenuElement_HoverElement_Type_Text("" + Game.lang.get("andNot"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(sInner + EventsManager.getActiveEvent(this.id, this.eventType).triggersAndNot.get(i).triggersAnd.get(j).getText(), CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + EventsManager.getActiveEvent(this.id, this.eventType).triggersAndNot.get(i).triggersAnd.get(j).getText2(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + EventsManager.getActiveEvent(this.id, this.eventType).triggersAndNot.get(i).triggersAnd.get(j).getText3(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        if (EventsManager.getActiveEvent(this.id, this.eventType).triggersAndNot.get(i).triggersAnd.get(j).getImage() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Image(EventsManager.getActiveEvent(this.id, this.eventType).triggersAndNot.get(i).triggersAnd.get(j).getImage(), CFG.PADDING, 0));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(" (" + Game.lang.get("and") + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
                for (int j = 0; j < EventsManager.getActiveEvent(this.id, this.eventType).triggersAndNot.get(i).triggersOr.size(); ++j) {
                    if (EventsManager.getActiveEvent(this.id, this.eventType).triggersAndNot.get(i).triggersOr.get(j).getText().length() > 0) {
                        if (addType) {
                            addType = false;
                            nData.add(new MenuElement_HoverElement_Type_Text("" + Game.lang.get("andNot"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(sInner + EventsManager.getActiveEvent(this.id, this.eventType).triggersAndNot.get(i).triggersOr.get(j).getText(), CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + EventsManager.getActiveEvent(this.id, this.eventType).triggersAndNot.get(i).triggersOr.get(j).getText2(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + EventsManager.getActiveEvent(this.id, this.eventType).triggersAndNot.get(i).triggersOr.get(j).getText3(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        if (EventsManager.getActiveEvent(this.id, this.eventType).triggersAndNot.get(i).triggersOr.get(j).getImage() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Image(EventsManager.getActiveEvent(this.id, this.eventType).triggersAndNot.get(i).triggersOr.get(j).getImage(), CFG.PADDING, 0));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(" (" + Game.lang.get("or") + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
                for (int j = 0; j < EventsManager.getActiveEvent(this.id, this.eventType).triggersAndNot.get(i).triggersAndNot.size(); ++j) {
                    if (EventsManager.getActiveEvent(this.id, this.eventType).triggersAndNot.get(i).triggersAndNot.get(j).getText().length() > 0) {
                        if (addType) {
                            addType = false;
                            nData.add(new MenuElement_HoverElement_Type_Text("" + Game.lang.get("andNot"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(sInner + EventsManager.getActiveEvent(this.id, this.eventType).triggersAndNot.get(i).triggersAndNot.get(j).getText(), CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + EventsManager.getActiveEvent(this.id, this.eventType).triggersAndNot.get(i).triggersAndNot.get(j).getText2(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + EventsManager.getActiveEvent(this.id, this.eventType).triggersAndNot.get(i).triggersAndNot.get(j).getText3(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        if (EventsManager.getActiveEvent(this.id, this.eventType).triggersAndNot.get(i).triggersAndNot.get(j).getImage() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Image(EventsManager.getActiveEvent(this.id, this.eventType).triggersAndNot.get(i).triggersAndNot.get(j).getImage(), CFG.PADDING, 0));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(" (" + Game.lang.get("andNot") + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
                for (int j = 0; j < EventsManager.getActiveEvent(this.id, this.eventType).triggersAndNot.get(i).triggersOrNot.size(); ++j) {
                    if (EventsManager.getActiveEvent(this.id, this.eventType).triggersAndNot.get(i).triggersOrNot.get(j).getText().length() > 0) {
                        if (addType) {
                            addType = false;
                            nData.add(new MenuElement_HoverElement_Type_Text("" + Game.lang.get("andNot"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(sInner + EventsManager.getActiveEvent(this.id, this.eventType).triggersAndNot.get(i).triggersOrNot.get(j).getText(), CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + EventsManager.getActiveEvent(this.id, this.eventType).triggersAndNot.get(i).triggersOrNot.get(j).getText2(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + EventsManager.getActiveEvent(this.id, this.eventType).triggersAndNot.get(i).triggersOrNot.get(j).getText3(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        if (EventsManager.getActiveEvent(this.id, this.eventType).triggersAndNot.get(i).triggersOrNot.get(j).getImage() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Image(EventsManager.getActiveEvent(this.id, this.eventType).triggersAndNot.get(i).triggersOrNot.get(j).getImage(), CFG.PADDING, 0));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(" (" + Game.lang.get("orNot") + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
            }
            addType = true;
            for (int i = 0; i < EventsManager.getActiveEvent(this.id, this.eventType).triggersOr.size(); ++i) {
                for (int j = 0; j < EventsManager.getActiveEvent(this.id, this.eventType).triggersOr.get(i).triggersAnd.size(); ++j) {
                    if (EventsManager.getActiveEvent(this.id, this.eventType).triggersOr.get(i).triggersAnd.get(j).getText().length() > 0) {
                        if (addType) {
                            addType = false;
                            nData.add(new MenuElement_HoverElement_Type_Text("" + Game.lang.get("or"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(sInner + EventsManager.getActiveEvent(this.id, this.eventType).triggersOr.get(i).triggersAnd.get(j).getText(), CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + EventsManager.getActiveEvent(this.id, this.eventType).triggersOr.get(i).triggersAnd.get(j).getText2(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + EventsManager.getActiveEvent(this.id, this.eventType).triggersOr.get(i).triggersAnd.get(j).getText3(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        if (EventsManager.getActiveEvent(this.id, this.eventType).triggersOr.get(i).triggersAnd.get(j).getImage() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Image(EventsManager.getActiveEvent(this.id, this.eventType).triggersOr.get(i).triggersAnd.get(j).getImage(), CFG.PADDING, 0));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(" (" + Game.lang.get("and") + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
                for (int j = 0; j < EventsManager.getActiveEvent(this.id, this.eventType).triggersOr.get(i).triggersOr.size(); ++j) {
                    if (EventsManager.getActiveEvent(this.id, this.eventType).triggersOr.get(i).triggersOr.get(j).getText().length() > 0) {
                        if (addType) {
                            addType = false;
                            nData.add(new MenuElement_HoverElement_Type_Text("" + Game.lang.get("or"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(sInner + EventsManager.getActiveEvent(this.id, this.eventType).triggersOr.get(i).triggersOr.get(j).getText(), CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + EventsManager.getActiveEvent(this.id, this.eventType).triggersOr.get(i).triggersOr.get(j).getText2(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + EventsManager.getActiveEvent(this.id, this.eventType).triggersOr.get(i).triggersOr.get(j).getText3(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        if (EventsManager.getActiveEvent(this.id, this.eventType).triggersOr.get(i).triggersOr.get(j).getImage() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Image(EventsManager.getActiveEvent(this.id, this.eventType).triggersOr.get(i).triggersOr.get(j).getImage(), CFG.PADDING, 0));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(" (" + Game.lang.get("or") + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
                for (int j = 0; j < EventsManager.getActiveEvent(this.id, this.eventType).triggersOr.get(i).triggersAndNot.size(); ++j) {
                    if (EventsManager.getActiveEvent(this.id, this.eventType).triggersOr.get(i).triggersAndNot.get(j).getText().length() > 0) {
                        if (addType) {
                            addType = false;
                            nData.add(new MenuElement_HoverElement_Type_Text("" + Game.lang.get("or"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(sInner + EventsManager.getActiveEvent(this.id, this.eventType).triggersOr.get(i).triggersAndNot.get(j).getText(), CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + EventsManager.getActiveEvent(this.id, this.eventType).triggersOr.get(i).triggersAndNot.get(j).getText2(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + EventsManager.getActiveEvent(this.id, this.eventType).triggersOr.get(i).triggersAndNot.get(j).getText3(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        if (EventsManager.getActiveEvent(this.id, this.eventType).triggersOr.get(i).triggersAndNot.get(j).getImage() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Image(EventsManager.getActiveEvent(this.id, this.eventType).triggersOr.get(i).triggersAndNot.get(j).getImage(), CFG.PADDING, 0));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(" (" + Game.lang.get("andNot") + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
                for (int j = 0; j < EventsManager.getActiveEvent(this.id, this.eventType).triggersOr.get(i).triggersOrNot.size(); ++j) {
                    if (EventsManager.getActiveEvent(this.id, this.eventType).triggersOr.get(i).triggersOrNot.get(j).getText().length() > 0) {
                        if (addType) {
                            addType = false;
                            nData.add(new MenuElement_HoverElement_Type_Text("" + Game.lang.get("or"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(sInner + EventsManager.getActiveEvent(this.id, this.eventType).triggersOr.get(i).triggersOrNot.get(j).getText(), CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + EventsManager.getActiveEvent(this.id, this.eventType).triggersOr.get(i).triggersOrNot.get(j).getText2(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + EventsManager.getActiveEvent(this.id, this.eventType).triggersOr.get(i).triggersOrNot.get(j).getText3(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        if (EventsManager.getActiveEvent(this.id, this.eventType).triggersOr.get(i).triggersOrNot.get(j).getImage() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Image(EventsManager.getActiveEvent(this.id, this.eventType).triggersOr.get(i).triggersOrNot.get(j).getImage(), CFG.PADDING, 0));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(" (" + Game.lang.get("orNot") + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
            }
            addType = true;
            for (int i = 0; i < EventsManager.getActiveEvent(this.id, this.eventType).triggersOrNot.size(); ++i) {
                for (int j = 0; j < EventsManager.getActiveEvent(this.id, this.eventType).triggersOrNot.get(i).triggersAnd.size(); ++j) {
                    if (EventsManager.getActiveEvent(this.id, this.eventType).triggersOrNot.get(i).triggersAnd.get(j).getText().length() > 0) {
                        if (addType) {
                            addType = false;
                            nData.add(new MenuElement_HoverElement_Type_Text("" + Game.lang.get("orNot"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(sInner + EventsManager.getActiveEvent(this.id, this.eventType).triggersOrNot.get(i).triggersAnd.get(j).getText(), CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + EventsManager.getActiveEvent(this.id, this.eventType).triggersOrNot.get(i).triggersAnd.get(j).getText2(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + EventsManager.getActiveEvent(this.id, this.eventType).triggersOrNot.get(i).triggersAnd.get(j).getText3(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        if (EventsManager.getActiveEvent(this.id, this.eventType).triggersOrNot.get(i).triggersAnd.get(j).getImage() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Image(EventsManager.getActiveEvent(this.id, this.eventType).triggersOrNot.get(i).triggersAnd.get(j).getImage(), CFG.PADDING, 0));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(" (" + Game.lang.get("and") + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
                for (int j = 0; j < EventsManager.getActiveEvent(this.id, this.eventType).triggersOrNot.get(i).triggersOr.size(); ++j) {
                    if (EventsManager.getActiveEvent(this.id, this.eventType).triggersOrNot.get(i).triggersOr.get(j).getText().length() > 0) {
                        if (addType) {
                            addType = false;
                            nData.add(new MenuElement_HoverElement_Type_Text("" + Game.lang.get("orNot"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(sInner + EventsManager.getActiveEvent(this.id, this.eventType).triggersOrNot.get(i).triggersOr.get(j).getText(), CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + EventsManager.getActiveEvent(this.id, this.eventType).triggersOrNot.get(i).triggersOr.get(j).getText2(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + EventsManager.getActiveEvent(this.id, this.eventType).triggersOrNot.get(i).triggersOr.get(j).getText3(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        if (EventsManager.getActiveEvent(this.id, this.eventType).triggersOrNot.get(i).triggersOr.get(j).getImage() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Image(EventsManager.getActiveEvent(this.id, this.eventType).triggersOrNot.get(i).triggersOr.get(j).getImage(), CFG.PADDING, 0));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(" (" + Game.lang.get("or") + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
                for (int j = 0; j < EventsManager.getActiveEvent(this.id, this.eventType).triggersOrNot.get(i).triggersAndNot.size(); ++j) {
                    if (EventsManager.getActiveEvent(this.id, this.eventType).triggersOrNot.get(i).triggersAndNot.get(j).getText().length() > 0) {
                        if (addType) {
                            addType = false;
                            nData.add(new MenuElement_HoverElement_Type_Text("" + Game.lang.get("orNot"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(sInner + EventsManager.getActiveEvent(this.id, this.eventType).triggersOrNot.get(i).triggersAndNot.get(j).getText(), CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + EventsManager.getActiveEvent(this.id, this.eventType).triggersOrNot.get(i).triggersAndNot.get(j).getText2(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + EventsManager.getActiveEvent(this.id, this.eventType).triggersOrNot.get(i).triggersAndNot.get(j).getText3(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        if (EventsManager.getActiveEvent(this.id, this.eventType).triggersOrNot.get(i).triggersAndNot.get(j).getImage() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Image(EventsManager.getActiveEvent(this.id, this.eventType).triggersOrNot.get(i).triggersAndNot.get(j).getImage(), CFG.PADDING, 0));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(" (" + Game.lang.get("andNot") + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
                for (int j = 0; j < EventsManager.getActiveEvent(this.id, this.eventType).triggersOrNot.get(i).triggersOrNot.size(); ++j) {
                    if (EventsManager.getActiveEvent(this.id, this.eventType).triggersOrNot.get(i).triggersOrNot.get(j).getText().length() > 0) {
                        if (addType) {
                            addType = false;
                            nData.add(new MenuElement_HoverElement_Type_Text("" + Game.lang.get("orNot"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(sInner + EventsManager.getActiveEvent(this.id, this.eventType).triggersOrNot.get(i).triggersOrNot.get(j).getText(), CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + EventsManager.getActiveEvent(this.id, this.eventType).triggersOrNot.get(i).triggersOrNot.get(j).getText2(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + EventsManager.getActiveEvent(this.id, this.eventType).triggersOrNot.get(i).triggersOrNot.get(j).getText3(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        if (EventsManager.getActiveEvent(this.id, this.eventType).triggersOrNot.get(i).triggersOrNot.get(j).getImage() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Image(EventsManager.getActiveEvent(this.id, this.eventType).triggersOrNot.get(i).triggersOrNot.get(j).getImage(), CFG.PADDING, 0));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(" (" + Game.lang.get("orNot") + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        this.menuElementHover = new MenuElement_Hover(nElements);
    }
    
    @Override
    public int getValue1() {
        return this.eventType;
    }
}
