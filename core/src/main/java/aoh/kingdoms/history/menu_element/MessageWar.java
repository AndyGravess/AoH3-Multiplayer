// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonusFlag;
import aoh.kingdoms.history.map.war.WarCivilization;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Center;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.map.province.ProvinceDraw;
import aoh.kingdoms.history.menusInGame.InGame_War;
import aoh.kingdoms.history.mainGame.SoundsManager;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.map.war.War;
import aoh.kingdoms.history.map.war.WarManager;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.Gdx;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu_element.button.Button;

public class MessageWar extends Button
{
    public static int warMaskY;
    public String key;
    private int iCivID;
    public int lastValue;
    public Color colorText;
    
    public MessageWar(final int iPosX, final int iPosY, final boolean isClickable, final int iCivID, final String key) {
        this.iCivID = 1;
        this.lastValue = -9415629;
        this.colorText = Color.WHITE;
        this.iCivID = iCivID;
        this.key = key;
        this.init("", CFG.FONT_BOLD_SMALL, this.iTextPositionX, iPosX, iPosY, ImageManager.getImage(Images.warBG).getWidth(), ImageManager.getImage(Images.warBG).getHeight(), isClickable, true, false, false);
        this.typeOfElement = MenuElement_Type.BUTTON_FLAG;
        this.getTextToDraw();
    }
    
    @Override
    protected void drawButtonBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        ImageManager.getImage(Images.warBG).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        oSB.setShader(Renderer.shaderAlpha);
        Game.getCiv(this.iCivID).getFlag().getTexture().bind(1);
        Gdx.gl.glActiveTexture(33984);
        ImageManager.getImage(Images.warMask).draw(oSB, this.getPosX() + ImageManager.getImage(Images.warBG).getWidth() / 2 - ImageManager.getImage(Images.warMask).getWidth() / 2 + iTranslateX, this.getPosY() + MessageWar.warMaskY + ImageManager.getImage(Images.warBG).getHeight() / 2 - ImageManager.getImage(Images.warMask).getHeight() / 2 + iTranslateY);
        oSB.flush();
        oSB.setShader(Renderer.shaderDefault);
        ImageManager.getImage(Images.warOver).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        if (this.getIsHovered()) {
            oSB.setColor(new Color(1.0f, 1.0f, 1.0f, 0.5f));
            ImageManager.getImage(Images.warOver).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        }
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    protected void drawText(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 1.0f));
        Images.gradientXY.draw(oSB, this.getPosX() + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() - this.iTextHeight + iTranslateY, this.getWidth() - CFG.PADDING * 4, this.iTextHeight + CFG.PADDING / 2, false, true);
        oSB.setColor(Color.WHITE);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.getWidth() / 2 - this.iTextWidth / 2 + iTranslateX, this.getPosY() + this.getHeight() - this.iTextHeight + iTranslateY, this.getColor(isActive));
    }
    
    @Override
    public String getTextToDraw() {
        try {
            if (WarManager.lWars.containsKey(this.key) && this.lastValue != (int)WarManager.lWars.get(this.key).warScore) {
                final boolean playSound = Math.abs(this.lastValue) < GameValues.peace.WAR_MAKE_DEMANDS_MIN_WAR_SCORE;
                this.lastValue = (int)WarManager.lWars.get(this.key).warScore;
                final int lastValue2 = this.lastValue * WarManager.lWars.get(this.key).getWarScore_Side(this.iCivID);
                if (playSound && Math.abs(this.lastValue) >= GameValues.peace.WAR_MAKE_DEMANDS_MIN_WAR_SCORE) {
                    Game.soundsManager.playSound(SoundsManager.SOUND_WAR_END);
                }
                if (lastValue2 == 0) {
                    this.colorText = Colors.COLOR_TOP_STATS3;
                }
                else if (lastValue2 < 0) {
                    this.colorText = Colors.HOVER_NEGATIVE;
                }
                else if (lastValue2 >= GameValues.peace.WAR_MAKE_DEMANDS_MIN_WAR_SCORE) {
                    this.colorText = Colors.HOVER_GOLD;
                }
                else {
                    this.colorText = Colors.HOVER_POSITIVE;
                }
                this.setText(((lastValue2 > 0) ? "+" : "") + Math.min(100, Math.max(-100, lastValue2)) + "%");
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return super.getTextToDraw();
    }
    
    @Override
    protected Color getColor(final boolean isActive) {
        if (this.getIsHovered()) {
            return Colors.getColorTopStats3(isActive, this.getIsHovered());
        }
        return this.colorText;
    }
    
    @Override
    public void actionElement() {
        if (Game.menuManager.getVisibleInGame_War() && InGame_War.key.equals(this.key)) {
            Game.menuManager.setVisibleInGame_War(false);
        }
        else {
            Game.gameActiveProvince.resetLastActiveProvince();
            Game.setActiveProvinceID(-1);
            Game.clearActiveArmy();
            Game.menuManager.showInGame_Battle_HideMenus();
            InGame_War.key = this.key;
            Game.menuManager.rebuildInGame_War();
            if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_WAR_VIEW) {
                Game.mapModes.setActiveViewID(Game.mapModes.MODE_WAR_VIEW);
            }
            else {
                Game.mapModes.updateWarView(InGame_War.key);
            }
        }
    }
    
    @Override
    public void setIsHovered(final boolean isHovered) {
        super.setIsHovered(isHovered);
        if (isHovered) {
            ProvinceDraw.drawProvincesCiv_HoveredFlagID = this.iCivID;
        }
    }
    
    @Override
    public void buildElementHover() {
        this.menuElementHover = getHoverWar(this.key, this.iCivID);
    }
    
    public static MenuElement_Hover getHoverWar(final String key, final int iCivID) {
        try {
            if (key == null) {
                return null;
            }
            if (WarManager.lWars.containsKey(key)) {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.lang.get("Attackers"), CFG.FONT_BOLD, Colors.HOVER_NEGATIVE));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.getCiv(WarManager.lWars.get(key).lAggressors.get(0).iCivID).getCivName(), "", WarManager.lWars.get(key).lAggressors.get(0).iCivID, CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_LEFT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (WarManager.lWars.get(key).lAggressors.size() > 1) {
                    for (int i = 1; i < WarManager.lWars.get(key).lAggressors.size() && i < 9; ++i) {
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(WarManager.lWars.get(key).lAggressors.get(i).iCivID, 0, CFG.PADDING));
                    }
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Empty());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                nData.add(new MenuElement_HoverElement_Type_Empty());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.lang.get("Defenders"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.getCiv(WarManager.lWars.get(key).lDefenders.get(0).iCivID).getCivName(), "", WarManager.lWars.get(key).lDefenders.get(0).iCivID, CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_LEFT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (WarManager.lWars.get(key).lDefenders.size() > 1) {
                    for (int i = 1; i < WarManager.lWars.get(key).lDefenders.size() && i < 9; ++i) {
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(WarManager.lWars.get(key).lDefenders.get(i).iCivID, 0, CFG.PADDING));
                    }
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Empty());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                final float lastValue2 = (float)WarManager.lWars.get(key).getWarScore_Side(iCivID);
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("WarScore") + ": ", CFG.FONT_BOLD));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(CFG.getPrecision2(Math.min(Math.max(WarManager.lWars.get(key).warScore * lastValue2, -100.0f), 100.0f), 1) + "%", CFG.FONT_BOLD, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.victoryPoints, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (Math.abs(WarManager.lWars.get(key).warScore) >= 1.0f) {
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("InFavorOf") + ": ", CFG.FONT_REGULAR));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv((WarManager.lWars.get(key).warScore > 0.0f) ? WarManager.lWars.get(key).lAggressors.get(0).iCivID : WarManager.lWars.get(key).lDefenders.get(0).iCivID).getCivName(), CFG.FONT_BOLD, Colors.HOVER_POSITIVE));
                    nData.add(new MenuElement_HoverElement_Type_FlagTitle((WarManager.lWars.get(key).warScore > 0.0f) ? WarManager.lWars.get(key).lAggressors.get(0).iCivID : WarManager.lWars.get(key).lDefenders.get(0).iCivID, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("OccupiedProvinces") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(WarManager.lWars.get(key).warScoreFromOccupiedProvinces * lastValue2, 10) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.victoryPoints, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("WarScoreFromBattles") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(WarManager.lWars.get(key).warScoreFromBattles * lastValue2, 10) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.victoryPoints, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("TickingWarScore") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(WarManager.lWars.get(key).tickingWarScore * lastValue2, 10) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.victoryPoints, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("WarStarted") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(Game_Calendar.getDate_ByTurnID(WarManager.lWars.get(key).iWarTurnID), CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (Math.abs(WarManager.lWars.get(key).warScore) < GameValues.war.WAR_AUTO_WHITE_PEACE_IF_WARSCORE_BELOW) {
                    nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("AWhitePeaceWillBeAutomaticallySignedIfThereIsNoActivityForACertainPeriodAndTheWarscoreRemainsLow"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("WhitePeace") + ": " + Game_Calendar.getDate_ByTurnID(WarManager.lWars.get(key).lastFight_TurnID + GameValues.war.WAR_AUTO_WHITE_PEACE_IF_NOTHING_HAPPENS_IN_WAR_DAYS) + ",  |" + Game.lang.get("WarScore") + "| < " + CFG.getPrecision2(GameValues.war.WAR_AUTO_WHITE_PEACE_IF_WARSCORE_BELOW, 10), CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                }
                nData.add(new MenuElement_HoverElement_Type_Empty());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("WarOverview"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.war, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                return new MenuElement_Hover(nElements, true);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return null;
    }
    
    @Override
    public int getSFX() {
        return SoundsManager.SOUND_CLICK_WAR;
    }
    
    static {
        MessageWar.warMaskY = 0;
    }
}
