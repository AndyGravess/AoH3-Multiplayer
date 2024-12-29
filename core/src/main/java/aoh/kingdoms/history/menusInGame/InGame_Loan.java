// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menusInGame.Info.InGame_Info;
import aoh.kingdoms.history.mainGame.SoundsManager;
import aoh.kingdoms.history.menu_element.button.ButtonGame;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menu_element.button.ButtonStatsBudget_Right2;
import aoh.kingdoms.history.menu_element.button.ButtonStatsBudget;
import aoh.kingdoms.history.mainGame.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_Loan extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    
    public InGame_Loan() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title500).getHeight();
        final int menuWidth = ImageManager.getImage(Images.title500).getWidth();
        final int menuX = CFG.BUTTON_WIDTH + Renderer.boxBGExtraY + CFG.PADDING;
        final int menuY = ImageManager.getImage(Images.topStats).getHeight() + CFG.PADDING * 2 + ImageManager.getImage(Images.title500).getHeight();
        int buttonY = CFG.PADDING * 2;
        final int buttonX = Images.boxTitleBORDERWIDTH;
        final int tIconMaxW = ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4 + CFG.PADDING / 2;
        final int tButtonH = CFG.TEXT_HEIGHT + CFG.PADDING * 4;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_BOLD), "+999 999");
        final int tButtonRightW = (int)Renderer.glyphLayout.width + CFG.PADDING * 4 + CFG.PADDING / 2;
        final int tButtonW = menuWidth - paddingLeft * 2 - CFG.PADDING - tButtonRightW;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Loan"), Images.loan, paddingLeft, buttonY, tButtonW, tButtonH, tIconMaxW, true));
        menuElements.add(new ButtonStatsBudget_Right2("+" + CFG.getPrecision2(Game.getLoanValue(Game.player.iCivID), 100), paddingLeft + tButtonW + CFG.PADDING, buttonY, tButtonRightW, tButtonH));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Interest") + ": " + CFG.getPrecision2(Game.getLoanInterest(Game.player.iCivID), 100) + "%", Images.gold, paddingLeft, buttonY, tButtonW, tButtonH, tIconMaxW, false) {
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Interest") + ": ", CFG.FONT_BOLD));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + CFG.getPrecision2(Game.getLoanInterest(Game.player.iCivID), 100) + "%", CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.loan, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("BaseValue") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + CFG.getPrecision2(GameValues.loan.LOAN_DEFAULT_INTEREST, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.loan, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new ButtonStatsBudget_Right2("+" + CFG.getPrecision2(Game.getLoanInterestValue(Game.player.iCivID), 100), paddingLeft + tButtonW + CFG.PADDING, buttonY, tButtonRightW, tButtonH) {
            @Override
            public void setText(final String sText) {
                super.setText(sText);
                this.nColor = Colors.COLOR_TEXT_MODIFIER_NEGATIVE;
                this.nColorH = Colors.COLOR_TEXT_MODIFIER_NEGATIVE_HOVER;
                this.nColorA = Colors.COLOR_TEXT_MODIFIER_NEGATIVE_ACTIVE;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("MonthlyExpenses"), Images.goldNegative, paddingLeft, buttonY, tButtonW, tButtonH, tIconMaxW, true));
        menuElements.add(new ButtonStatsBudget_Right2("-" + CFG.getPrecision2(Game.getLoanMonthlyExpenses(Game.getLoanValue(Game.player.iCivID) + Game.getLoanInterestValue(Game.player.iCivID)), 100), paddingLeft + tButtonW + CFG.PADDING, buttonY, tButtonRightW, tButtonH) {
            @Override
            public void setText(final String sText) {
                super.setText(sText);
                this.nColor = Colors.COLOR_TEXT_MODIFIER_NEGATIVE;
                this.nColorH = Colors.COLOR_TEXT_MODIFIER_NEGATIVE_HOVER;
                this.nColorA = Colors.COLOR_TEXT_MODIFIER_NEGATIVE_ACTIVE;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Inflation"), Images.inflation, paddingLeft, buttonY, tButtonW, tButtonH, tIconMaxW, true));
        menuElements.add(new ButtonStatsBudget_Right2("+" + CFG.getPrecision2(GameValues.loan.LOAN_INFLATION * 100.0f, 100) + "%", paddingLeft + tButtonW + CFG.PADDING, buttonY, tButtonRightW, tButtonH) {
            @Override
            public void setText(final String sText) {
                super.setText(sText);
                this.nColor = Colors.COLOR_TEXT_MODIFIER_NEGATIVE;
                this.nColorH = Colors.COLOR_TEXT_MODIFIER_NEGATIVE_HOVER;
                this.nColorA = Colors.COLOR_TEXT_MODIFIER_NEGATIVE_ACTIVE;
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Inflation") + ": ", "+" + CFG.getPrecision2(GameValues.loan.LOAN_INFLATION * 100.0f, 100) + "%", Images.inflation, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Expires") + ": " + Game_Calendar.getNumOfDates_ByTurnID(Game_Calendar.TURN_ID + Game.getLoanExpires()), Images.time, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tButtonH, tIconMaxW, false));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("MaximumNumberOfLoans") + ": " + Game.getCiv(Game.player.iCivID).iLoansSize + " / " + Game.getLoanMaxNumber(Game.player.iCivID), Images.loan, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tButtonH, tIconMaxW, false));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        menuElements.add(new ButtonGame(Game.lang.get("Cancel"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING - CFG.PADDING - CFG.BUTTON_HEIGHT2) / 2, true) {
            @Override
            public void actionElement() {
                Game.menuManager.setVisibleInGame_TakeLoan(false);
            }
        });
        menuElements.add(new ButtonGame(Game.lang.get("Confirm"), CFG.FONT_REGULAR, -1, paddingLeft + CFG.PADDING + (menuWidth - paddingLeft * 2 - CFG.PADDING - CFG.PADDING - CFG.BUTTON_HEIGHT2) / 2, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING - CFG.BUTTON_HEIGHT2) / 2, true) {
            @Override
            public int getSFX() {
                return SoundsManager.SOUND_LOAN;
            }
            
            @Override
            public boolean getClickable() {
                return Game.getCiv(Game.player.iCivID).iLoansSize < Game.getLoanMaxNumber(Game.player.iCivID);
            }
            
            @Override
            public void actionElement() {
                if (Game.getCiv(Game.player.iCivID).takeLoan()) {
                    InGame_Info.iCivID = Game.player.iCivID;
                    InGame_Info.iCivID2 = 0;
                    Game.menuManager.rebuildInGame_Info(Game.lang.get("ALoanHasBeenTaken"), Game.lang.get("Loans") + ": " + CFG.getPrecision2((float)Game.getCiv(Game.player.iCivID).iLoansSize, 100) + " / " + Game.getLoanMaxNumber(Game.player.iCivID));
                    InGame_Info.imgID = Images.infoCrown;
                    Game.player.currSituation.updateCurrentSituation();
                }
                Game.menuManager.setVisibleInGame_TakeLoan(false);
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("TakeLoan"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.loan, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                final float fVal = Game.getLoanValue(Game.player.iCivID);
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Gold") + ": ", CFG.FONT_BOLD));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("+" + CFG.getPrecision2(fVal, 100), CFG.FONT_BOLD, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Interest") + ": ", CFG.FONT_BOLD));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("+" + CFG.getPrecision2(Game.getLoanInterestValue(Game.player.iCivID), 100), CFG.FONT_BOLD, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.goldNegative, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (Game.getCiv(Game.player.iCivID).iLoansSize >= Game.getLoanMaxNumber(Game.player.iCivID)) {
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("MaximumNumberOfLoans") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.getCiv(Game.player.iCivID).iLoansSize + " / " + Game.getLoanMaxNumber(Game.player.iCivID), CFG.FONT_BOLD, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.gold, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new ButtonGame("+", CFG.FONT_REGULAR, -1, paddingLeft + CFG.PADDING * 2 + (menuWidth - paddingLeft * 2 - CFG.PADDING - CFG.PADDING - CFG.BUTTON_HEIGHT2), buttonY, CFG.BUTTON_HEIGHT2, true) {
            @Override
            public int getSFX() {
                return SoundsManager.SOUND_LOAN;
            }
            
            @Override
            public boolean getClickable() {
                return Game.getCiv(Game.player.iCivID).iLoansSize < Game.getLoanMaxNumber(Game.player.iCivID);
            }
            
            @Override
            public void actionElement() {
                if (Game.getCiv(Game.player.iCivID).takeLoan()) {
                    InGame_Info.iCivID = Game.player.iCivID;
                    InGame_Info.iCivID2 = 0;
                    Game.menuManager.rebuildInGame_Info(Game.lang.get("ALoanHasBeenTaken"), Game.lang.get("Loans") + ": " + CFG.getPrecision2((float)Game.getCiv(Game.player.iCivID).iLoansSize, 100) + " / " + Game.getLoanMaxNumber(Game.player.iCivID));
                    InGame_Info.imgID = Images.infoCrown;
                    Game.player.currSituation.updateCurrentSituation();
                }
                if (Game.getCiv(Game.player.iCivID).iLoansSize >= Game.getLoanMaxNumber(Game.player.iCivID)) {
                    Game.menuManager.setVisibleInGame_TakeLoan(false);
                }
            }
            
            @Override
            public void buildElementHover() {
                final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("TakeLoan"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.loan, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                final float fVal = Game.getLoanValue(Game.player.iCivID);
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Gold") + ": ", CFG.FONT_BOLD));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("+" + CFG.getPrecision2(fVal, 100), CFG.FONT_BOLD, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Interest") + ": ", CFG.FONT_BOLD));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("+" + CFG.getPrecision2(Game.getLoanInterestValue(Game.player.iCivID), 100), CFG.FONT_BOLD, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.goldNegative, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (Game.getCiv(Game.player.iCivID).iLoansSize >= Game.getLoanMaxNumber(Game.player.iCivID)) {
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("MaximumNumberOfLoans") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.getCiv(Game.player.iCivID).iLoansSize + " / " + Game.getLoanMaxNumber(Game.player.iCivID), CFG.FONT_BOLD, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.gold, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        buttonY = 0;
        for (int i = 0, iSize = menuElements.size(); i < iSize; ++i) {
            if (buttonY < menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING * 2) {
                buttonY = menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING * 2;
            }
        }
        final int tMenuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 2);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, tMenuHeight)));
        this.initMenu(new MenuTitleIMG(Game.lang.get("TakeLoan"), true, false, Images.title500) {
            @Override
            public long getTime() {
                return InGame_Loan.lTime;
            }
        }, CFG.GAME_WIDTH / 2 - menuWidth / 2, CFG.GAME_HEIGHT / 4, menuWidth, tMenuHeight, menuElements, false, true);
        this.drawScrollPositionAlways = false;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_Loan.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateY = iTranslateY - CFG.BUTTON_HEIGHT + (int)(CFG.BUTTON_HEIGHT * ((CFG.currentTimeMillis - InGame_Loan.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.civInfoOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.civInfoOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.civInfoOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_Loan.lTime = CFG.currentTimeMillis;
    }
    
    static {
        InGame_Loan.lTime = 0L;
    }
}
