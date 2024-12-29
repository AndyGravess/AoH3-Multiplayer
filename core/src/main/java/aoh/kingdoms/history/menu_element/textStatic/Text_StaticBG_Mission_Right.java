// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu_element.textStatic;

import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Title;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menusInGame.InGame_Event;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.button.ButtonGame;
import aoh.kingdoms.history.textures.Images;
import com.badlogic.gdx.graphics.Color;
import aoh.kingdoms.history.menu.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.mainGame.CFG;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.mainGame.Game;

public class Text_StaticBG_Mission_Right extends Text_StaticBG_Mission
{
    public static long lTimeAnimation;
    public static int animationState;
    public static final int ANIMATION_T = 2000;
    public int iLastTurnID;
    public int iRespondTurnID;
    public String sText2;
    public int iText2Width;
    
    public Text_StaticBG_Mission_Right(final String sText, final int fontID, final int iTextPositionX, final int iPosX, final int iPosY, final int iWidth, final int eventType, final int id, final int missionImage, final int iRespondTurnID) {
        super(sText, fontID, iTextPositionX, iPosX, iPosY, iWidth, eventType, id, missionImage);
        this.iLastTurnID = -65151;
        this.iRespondTurnID = 0;
        this.sText2 = "";
        this.iText2Width = 0;
        this.iRespondTurnID = iRespondTurnID;
        this.sText2 = Game.lang.get("DaysX", GameValues.events.EVENT_TIME_TO_RESPOND);
        Renderer.glyphLayout.setText(Renderer.fontMain.get(fontID), this.sText2);
        this.iText2Width = (int)Renderer.glyphLayout.width;
        int tWMax = 0;
        while (this.iTextWidth > this.getWidth() - this.textPosition.getTextPosition() * 2 - CFG.PADDING * 2 - this.iText2Width && this.getText().length() > 5 && ++tWMax < 100) {
            this.setText(this.getText().substring(0, Math.max(1, this.getText().length() - 3)) + ".");
        }
    }
    
    @Override
    public void drawBG(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 1.0f));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0f);
        oSB.setColor(Color.WHITE);
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, final int iTranslateY, final boolean isActive, final boolean scrollableY) {
        super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
        if (Text_StaticBG_Mission_Right.animationState >= 0) {
            if (Text_StaticBG_Mission_Right.animationState == 0) {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - Text_StaticBG_Mission_Right.lTimeAnimation) / 2000.0f, 1.0f);
                oSB.setColor(ButtonGame.getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + 1 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                oSB.setColor(new Color(ButtonGame.getColorLine().r, ButtonGame.getColorLine().g, ButtonGame.getColorLine().b, 0.35f * Math.min(1.0f * (CFG.currentTimeMillis - Text_StaticBG_Mission_Right.lTimeAnimation) / 2000.0f, 1.0f)));
                Renderer.drawBox(oSB, Images.statsRectBGBorder, this.getPosX() - 1 + iTranslateX, this.getPosY() - 1 + iTranslateY, this.getWidth() + 2, this.getHeight() + 2, 1.0f);
                if (Text_StaticBG_Mission_Right.lTimeAnimation < CFG.currentTimeMillis - 2000L) {
                    ++Text_StaticBG_Mission_Right.animationState;
                    Text_StaticBG_Mission_Right.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            else {
                final float drawPerc = Math.min(1.0f * (CFG.currentTimeMillis - Text_StaticBG_Mission_Right.lTimeAnimation) / 2000.0f, 1.0f);
                oSB.setColor(ButtonGame.getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                oSB.setColor(new Color(ButtonGame.getColorLine().r, ButtonGame.getColorLine().g, ButtonGame.getColorLine().b, 0.35f - 0.35f * Math.min(1.0f * (CFG.currentTimeMillis - Text_StaticBG_Mission_Right.lTimeAnimation) / 2000.0f, 1.0f)));
                Renderer.drawBox(oSB, Images.statsRectBGBorder, this.getPosX() - 1 + iTranslateX, this.getPosY() - 1 + iTranslateY, this.getWidth() + 2, this.getHeight() + 2, 1.0f);
                if (Text_StaticBG_Mission_Right.lTimeAnimation < CFG.currentTimeMillis - 2000L) {
                    Text_StaticBG_Mission_Right.animationState = 0;
                    Text_StaticBG_Mission_Right.lTimeAnimation = CFG.currentTimeMillis;
                }
            }
            oSB.setColor(Color.WHITE);
        }
        try {
            if (Game_Calendar.TURN_ID != this.iLastTurnID) {
                this.iLastTurnID = Game_Calendar.TURN_ID;
                this.sText2 = Game.lang.get("DaysX", this.iRespondTurnID - Game_Calendar.TURN_ID);
                Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), this.sText2);
                this.iText2Width = (int)Renderer.glyphLayout.width;
                if (this.iRespondTurnID - Game_Calendar.TURN_ID <= 0) {
                    Game.addSimpleTask(new Game.SimpleTask("RebuildInGameRight", this.getCurrent()) {
                        @Override
                        public void update() {
                            Game.menuManager.rebuildInGame_Right();
                            if (Game.menuManager.getVisibleInGame_Event() && InGame_Event.eventID == this.id) {
                                Game.menuManager.setVisibleInGame_Event(false);
                            }
                        }
                    });
                }
            }
            Renderer.drawTextWithShadow(oSB, this.fontID, this.sText2, this.getPosX() + this.getWidth() - this.textPosition.getTextPosition() - this.iText2Width + iTranslateX, this.getPosY() + (this.getHeight() - CFG.TEXT_HEIGHT) / 2 + iTranslateY, Colors.getColorTopStats(isActive, this.getIsHovered()));
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    @Override
    public void buildElementHover() {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_Title(Images.missions, "", this.getText(), Colors.HOVER_GOLD, Game.lang.get("Event"), "", Colors.HOVER_RIGHT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ClickToView"), CFG.FONT_BOLD_SMALL, Colors.HOVER_POSITIVE));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover(nElements);
    }
    
    static {
        Text_StaticBG_Mission_Right.lTimeAnimation = 0L;
        Text_StaticBG_Mission_Right.animationState = 0;
    }
}
