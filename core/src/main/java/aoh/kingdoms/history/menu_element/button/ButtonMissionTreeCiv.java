// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.button;

import aoh.kingdoms.history.events.EventsManager;
import aoh.kingdoms.history.menusInGame.Info.InGame_Info;
import aoh.kingdoms.history.mainGame.SoundsManager;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.events.triggers.EventTrigger_Value;
import aoh.kingdoms.history.events.triggers.EventTrigger;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Title;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.textures.Image;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.Missions.MissionTree;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Missions.Mission;
import aoh.kingdoms.history.mainGame.CFG;

public class ButtonMissionTreeCiv extends Button
{
    public int iMissionID;
    public int iImageID;
    public boolean missionUnlocked;
    public boolean missionCanBeUnlocked;
    
    public ButtonMissionTreeCiv(final int iMissionID, final int iImageID, final int iPosX, final int iPosY, final boolean missionUnlocked, final boolean missionCanBeUnlocked) {
        this.init(iMissionID, iImageID, iPosX, iPosY);
        this.missionUnlocked = missionUnlocked;
        this.missionCanBeUnlocked = missionCanBeUnlocked;
    }
    
    public void init(final int iMissionID, final int iImageID, final int iPosX, final int iPosY) {
        this.iMissionID = iMissionID;
        this.iImageID = iImageID;
        this.fontID = CFG.FONT_REGULAR;
        this.init(Game.getCiv(Game.player.iCivID).lMissions.get(iMissionID).Name, this.fontID, this.iTextPositionX, iPosX, iPosY, MissionTree.iMissionWidth, MissionTree.iMissionHeight, true, true, false, false);
        int tWMax = 0;
        while (this.iTextWidth >= this.getWidth() - CFG.PADDING * 2 && this.getText().length() > 5 && ++tWMax < 100) {
            this.setText(this.getText().substring(0, Math.max(1, this.getText().length() - 3)) + ".");
        }
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setShader(Renderer.shaderAlpha);
        try {
            MissionTree.missionImagesCivs.get(this.iImageID).getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            ImageManager.getImage(Images.missionMask).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        oSB.flush();
        oSB.setShader(Renderer.shaderDefault);
        final int nH = CFG.PADDING * 4 + this.getTextHeight();
        if (this.missionUnlocked) {
            oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_BG_GREEN.r, Colors.COLOR_NOTIFICATION_BG_GREEN.g, Colors.COLOR_NOTIFICATION_BG_GREEN.b, 0.7f));
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - nH + iTranslateY, this.getWidth(), nH);
            oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_OVER_GREEN.r, Colors.COLOR_NOTIFICATION_OVER_GREEN.g, Colors.COLOR_NOTIFICATION_OVER_GREEN.b, 0.5f));
            ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - nH + iTranslateY, this.getWidth(), nH, false, false);
            oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_OVER_GREEN.r, Colors.COLOR_NOTIFICATION_OVER_GREEN.g, Colors.COLOR_NOTIFICATION_OVER_GREEN.b, 0.55f));
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - nH + iTranslateY, this.getWidth(), nH, false, false);
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - nH + iTranslateY, this.getWidth(), CFG.PADDING, false, false);
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING + iTranslateY, this.getWidth(), CFG.PADDING, false, true);
            oSB.setColor(new Color(Colors.COLOR_NOTIFICATION_BG_GREEN.r, Colors.COLOR_NOTIFICATION_BG_GREEN.g, Colors.COLOR_NOTIFICATION_BG_GREEN.b, 0.75f));
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - nH + iTranslateY, this.getWidth(), 1);
        }
        else {
            oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.7f));
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - nH + iTranslateY, this.getWidth(), nH);
            oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5f));
            ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - nH + iTranslateY, this.getWidth(), nH, false, false);
            oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.55f));
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - nH + iTranslateY, this.getWidth(), nH, false, false);
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - nH + iTranslateY, this.getWidth(), CFG.PADDING, false, false);
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING + iTranslateY, this.getWidth(), CFG.PADDING, false, true);
            oSB.setColor(new Color(0.0f, 0.0f, 0.0f, 0.75f));
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - nH + iTranslateY, this.getWidth(), 1);
        }
        oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.2f));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - nH + 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.missionOver).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        oSB.setColor(Color.WHITE);
    }
    
    public int titleH() {
        return CFG.TEXT_HEIGHT + CFG.PADDING * 3;
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        if (this.missionUnlocked) {
            return Colors.getColorPositive(isActive, this.getIsHovered());
        }
        if (!this.missionCanBeUnlocked) {
            return Colors.getColorTopStats2(isActive, this.getIsHovered());
        }
        if (this.getIsHovered() || isActive) {
            return Colors.getColorTopStats2(isActive, this.getIsHovered());
        }
        return Colors.HOVER_GOLD;
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        Renderer.drawTextWithShadow(oSB, this.fontID, this.sText, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING * 2 - this.getTextHeight() + iTranslateY, this.getColor(isActive));
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_Title(Images.missions, "", Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).Name, Colors.HOVER_GOLD, Game.lang.get("Mission") + (this.missionUnlocked ? (": " + Game.lang.get("Completed")) : ""), "", Colors.HOVER_RIGHT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        final String sInner = "- ";
        boolean addType = true;
        if (Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.desc.length() > 0) {
            nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get(Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.desc), CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT2));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        try {
            for (int i = 0; i < Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAnd.size(); ++i) {
                for (int j = 0; j < Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAnd.get(i).triggersAnd.size(); ++j) {
                    if (Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAnd.get(i).triggersAnd.get(j).getText().length() > 0) {
                        if (addType) {
                            addType = false;
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(sInner + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAnd.get(i).triggersAnd.get(j).getText(), CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAnd.get(i).triggersAnd.get(j).getText2(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAnd.get(i).triggersAnd.get(j).getText3(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        if (Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAnd.get(i).triggersAnd.get(j).getImage() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Image(Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAnd.get(i).triggersAnd.get(j).getImage(), CFG.PADDING, 0));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(" (" + Game.lang.get("and") + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
                for (int j = 0; j < Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAnd.get(i).triggersOr.size(); ++j) {
                    if (Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAnd.get(i).triggersOr.get(j).getText().length() > 0) {
                        if (addType) {
                            addType = false;
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(sInner + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAnd.get(i).triggersOr.get(j).getText(), CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAnd.get(i).triggersOr.get(j).getText2(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAnd.get(i).triggersOr.get(j).getText3(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        if (Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAnd.get(i).triggersOr.get(j).getImage() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Image(Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAnd.get(i).triggersOr.get(j).getImage(), CFG.PADDING, 0));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(" (" + Game.lang.get("or") + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
                for (int j = 0; j < Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAnd.get(i).triggersAndNot.size(); ++j) {
                    if (Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAnd.get(i).triggersAndNot.get(j).getText().length() > 0) {
                        if (addType) {
                            addType = false;
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(sInner + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAnd.get(i).triggersAndNot.get(j).getText(), CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAnd.get(i).triggersAndNot.get(j).getText2(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAnd.get(i).triggersAndNot.get(j).getText3(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        if (Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAnd.get(i).triggersAndNot.get(j).getImage() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Image(Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAnd.get(i).triggersAndNot.get(j).getImage(), CFG.PADDING, 0));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(" (" + Game.lang.get("andNot") + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
                for (int j = 0; j < Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAnd.get(i).triggersOrNot.size(); ++j) {
                    if (Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAnd.get(i).triggersOrNot.get(j).getText().length() > 0) {
                        if (addType) {
                            addType = false;
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(sInner + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAnd.get(i).triggersOrNot.get(j).getText(), CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAnd.get(i).triggersOrNot.get(j).getText2(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAnd.get(i).triggersOrNot.get(j).getText3(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        if (Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAnd.get(i).triggersOrNot.get(j).getImage() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Image(Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAnd.get(i).triggersOrNot.get(j).getImage(), CFG.PADDING, 0));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(" (" + Game.lang.get("orNot") + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
            }
            addType = true;
            for (int i = 0; i < Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAndNot.size(); ++i) {
                for (int j = 0; j < Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAndNot.get(i).triggersAnd.size(); ++j) {
                    if (Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAndNot.get(i).triggersAnd.get(j).getText().length() > 0) {
                        if (addType) {
                            addType = false;
                            nData.add(new MenuElement_HoverElement_Type_Text("" + Game.lang.get("andNot"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(sInner + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAndNot.get(i).triggersAnd.get(j).getText(), CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAndNot.get(i).triggersAnd.get(j).getText2(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAndNot.get(i).triggersAnd.get(j).getText3(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        if (Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAndNot.get(i).triggersAnd.get(j).getImage() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Image(Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAndNot.get(i).triggersAnd.get(j).getImage(), CFG.PADDING, 0));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(" (" + Game.lang.get("and") + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
                for (int j = 0; j < Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAndNot.get(i).triggersOr.size(); ++j) {
                    if (Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAndNot.get(i).triggersOr.get(j).getText().length() > 0) {
                        if (addType) {
                            addType = false;
                            nData.add(new MenuElement_HoverElement_Type_Text("" + Game.lang.get("andNot"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(sInner + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAndNot.get(i).triggersOr.get(j).getText(), CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAndNot.get(i).triggersOr.get(j).getText2(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAndNot.get(i).triggersOr.get(j).getText3(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        if (Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAndNot.get(i).triggersOr.get(j).getImage() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Image(Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAndNot.get(i).triggersOr.get(j).getImage(), CFG.PADDING, 0));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(" (" + Game.lang.get("or") + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
                for (int j = 0; j < Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAndNot.get(i).triggersAndNot.size(); ++j) {
                    if (Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAndNot.get(i).triggersAndNot.get(j).getText().length() > 0) {
                        if (addType) {
                            addType = false;
                            nData.add(new MenuElement_HoverElement_Type_Text("" + Game.lang.get("andNot"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(sInner + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAndNot.get(i).triggersAndNot.get(j).getText(), CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAndNot.get(i).triggersAndNot.get(j).getText2(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAndNot.get(i).triggersAndNot.get(j).getText3(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        if (Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAndNot.get(i).triggersAndNot.get(j).getImage() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Image(Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAndNot.get(i).triggersAndNot.get(j).getImage(), CFG.PADDING, 0));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(" (" + Game.lang.get("andNot") + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
                for (int j = 0; j < Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAndNot.get(i).triggersOrNot.size(); ++j) {
                    if (Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAndNot.get(i).triggersOrNot.get(j).getText().length() > 0) {
                        if (addType) {
                            addType = false;
                            nData.add(new MenuElement_HoverElement_Type_Text("" + Game.lang.get("andNot"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(sInner + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAndNot.get(i).triggersOrNot.get(j).getText(), CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAndNot.get(i).triggersOrNot.get(j).getText2(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAndNot.get(i).triggersOrNot.get(j).getText3(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        if (Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAndNot.get(i).triggersOrNot.get(j).getImage() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Image(Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersAndNot.get(i).triggersOrNot.get(j).getImage(), CFG.PADDING, 0));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(" (" + Game.lang.get("orNot") + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
            }
            addType = true;
            for (int i = 0; i < Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOr.size(); ++i) {
                for (int j = 0; j < Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOr.get(i).triggersAnd.size(); ++j) {
                    if (Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOr.get(i).triggersAnd.get(j).getText().length() > 0) {
                        if (addType) {
                            addType = false;
                            nData.add(new MenuElement_HoverElement_Type_Text("" + Game.lang.get("or"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(sInner + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOr.get(i).triggersAnd.get(j).getText(), CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOr.get(i).triggersAnd.get(j).getText2(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOr.get(i).triggersAnd.get(j).getText3(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        if (Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOr.get(i).triggersAnd.get(j).getImage() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Image(Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOr.get(i).triggersAnd.get(j).getImage(), CFG.PADDING, 0));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(" (" + Game.lang.get("and") + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
                for (int j = 0; j < Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOr.get(i).triggersOr.size(); ++j) {
                    if (Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOr.get(i).triggersOr.get(j).getText().length() > 0) {
                        if (addType) {
                            addType = false;
                            nData.add(new MenuElement_HoverElement_Type_Text("" + Game.lang.get("or"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(sInner + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOr.get(i).triggersOr.get(j).getText(), CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOr.get(i).triggersOr.get(j).getText2(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOr.get(i).triggersOr.get(j).getText3(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        if (Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOr.get(i).triggersOr.get(j).getImage() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Image(Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOr.get(i).triggersOr.get(j).getImage(), CFG.PADDING, 0));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(" (" + Game.lang.get("or") + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
                for (int j = 0; j < Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOr.get(i).triggersAndNot.size(); ++j) {
                    if (Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOr.get(i).triggersAndNot.get(j).getText().length() > 0) {
                        if (addType) {
                            addType = false;
                            nData.add(new MenuElement_HoverElement_Type_Text("" + Game.lang.get("or"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(sInner + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOr.get(i).triggersAndNot.get(j).getText(), CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOr.get(i).triggersAndNot.get(j).getText2(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOr.get(i).triggersAndNot.get(j).getText3(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        if (Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOr.get(i).triggersAndNot.get(j).getImage() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Image(Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOr.get(i).triggersAndNot.get(j).getImage(), CFG.PADDING, 0));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(" (" + Game.lang.get("andNot") + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
                for (int j = 0; j < Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOr.get(i).triggersOrNot.size(); ++j) {
                    if (Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOr.get(i).triggersOrNot.get(j).getText().length() > 0) {
                        if (addType) {
                            addType = false;
                            nData.add(new MenuElement_HoverElement_Type_Text("" + Game.lang.get("or"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(sInner + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOr.get(i).triggersOrNot.get(j).getText(), CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOr.get(i).triggersOrNot.get(j).getText2(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOr.get(i).triggersOrNot.get(j).getText3(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        if (Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOr.get(i).triggersOrNot.get(j).getImage() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Image(Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOr.get(i).triggersOrNot.get(j).getImage(), CFG.PADDING, 0));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(" (" + Game.lang.get("orNot") + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
            }
            addType = true;
            for (int i = 0; i < Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOrNot.size(); ++i) {
                for (int j = 0; j < Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOrNot.get(i).triggersAnd.size(); ++j) {
                    if (Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOrNot.get(i).triggersAnd.get(j).getText().length() > 0) {
                        if (addType) {
                            addType = false;
                            nData.add(new MenuElement_HoverElement_Type_Text("" + Game.lang.get("orNot"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(sInner + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOrNot.get(i).triggersAnd.get(j).getText(), CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOrNot.get(i).triggersAnd.get(j).getText2(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOrNot.get(i).triggersAnd.get(j).getText3(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        if (Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOrNot.get(i).triggersAnd.get(j).getImage() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Image(Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOrNot.get(i).triggersAnd.get(j).getImage(), CFG.PADDING, 0));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(" (" + Game.lang.get("and") + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
                for (int j = 0; j < Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOrNot.get(i).triggersOr.size(); ++j) {
                    if (Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOrNot.get(i).triggersOr.get(j).getText().length() > 0) {
                        if (addType) {
                            addType = false;
                            nData.add(new MenuElement_HoverElement_Type_Text("" + Game.lang.get("orNot"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(sInner + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOrNot.get(i).triggersOr.get(j).getText(), CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOrNot.get(i).triggersOr.get(j).getText2(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOrNot.get(i).triggersOr.get(j).getText3(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        if (Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOrNot.get(i).triggersOr.get(j).getImage() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Image(Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOrNot.get(i).triggersOr.get(j).getImage(), CFG.PADDING, 0));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(" (" + Game.lang.get("or") + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
                for (int j = 0; j < Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOrNot.get(i).triggersAndNot.size(); ++j) {
                    if (Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOrNot.get(i).triggersAndNot.get(j).getText().length() > 0) {
                        if (addType) {
                            addType = false;
                            nData.add(new MenuElement_HoverElement_Type_Text("" + Game.lang.get("orNot"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(sInner + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOrNot.get(i).triggersAndNot.get(j).getText(), CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOrNot.get(i).triggersAndNot.get(j).getText2(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOrNot.get(i).triggersAndNot.get(j).getText3(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        if (Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOrNot.get(i).triggersAndNot.get(j).getImage() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Image(Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOrNot.get(i).triggersAndNot.get(j).getImage(), CFG.PADDING, 0));
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(" (" + Game.lang.get("andNot") + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }
                for (int j = 0; j < Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOrNot.get(i).triggersOrNot.size(); ++j) {
                    if (Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOrNot.get(i).triggersOrNot.get(j).getText().length() > 0) {
                        if (addType) {
                            addType = false;
                            nData.add(new MenuElement_HoverElement_Type_Text("" + Game.lang.get("orNot"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }
                        nData.add(new MenuElement_HoverElement_Type_Text(sInner + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOrNot.get(i).triggersOrNot.get(j).getText(), CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOrNot.get(i).triggersOrNot.get(j).getText2(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOrNot.get(i).triggersOrNot.get(j).getText3(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        if (Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOrNot.get(i).triggersOrNot.get(j).getImage() > 0) {
                            nData.add(new MenuElement_HoverElement_Type_Image(Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).event.triggersOrNot.get(i).triggersOrNot.get(j).getImage(), CFG.PADDING, 0));
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
    public int getSFX() {
        return SoundsManager.SOUND_CLICK_TOP;
    }
    
    @Override
    public int getCurrent() {
        return this.iMissionID;
    }
    
    @Override
    public void actionElement() {
        if (MissionTree.canRunMission_Civ(Game.player.iCivID, this.iMissionID)) {
            InGame_Info.iCivID = Game.player.iCivID;
            InGame_Info.iCivID2 = 0;
            Game.menuManager.rebuildInGame_Info(Game.getCiv(Game.player.iCivID).lMissions.get(this.iMissionID).Name, Game.lang.get("Mission"));
            InGame_Info.imgID = Images.infoCrown;
            EventsManager.updateRandomProvinceID(Game.player.iCivID);
            Game.menuManager.setVisibleInGame_MissionTree(false);
            MissionTree.runMission_Civ(Game.player.iCivID, this.iMissionID);
        }
        else if (!MissionTree.canRunMission_PreviousMissions_Civ(Game.player.iCivID, this.iMissionID)) {
            Game.menuManager.addToast_Error(Game.lang.get("CompleteThePreviousMissionFirst"), Images.missions);
        }
        else if (MissionTree.haveUnlockedMission_Civ(Game.player.iCivID, this.iMissionID)) {
            Game.menuManager.addToast_Error(Game.lang.get("AlreadyUnlocked"), Images.missions);
        }
        else {
            Game.menuManager.addToast_Error(Game.lang.get("RequirementsNotMet"), Images.missions);
        }
    }
}
