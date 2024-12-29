// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menusInGame;

import aoh.kingdoms.history.menu_element.Status;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.menu.menuTitle.MenuTitle;
import aoh.kingdoms.history.menu.menuTitle.MenuTitleIMG;
import aoh.kingdoms.history.menu_element.Empty;
import java.util.List;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_Hover;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.menusInGame.Info.InGame_Info;
import aoh.kingdoms.history.menu.Colors;
import aoh.kingdoms.history.mainGame.SoundsManager;
import aoh.kingdoms.history.menu_element.button.ButtonStatsBudget_Right2;
import aoh.kingdoms.history.menu_element.button.ButtonStatsBudget;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.map.Loan;
import aoh.kingdoms.history.menu_element.textStatic.Text_StaticBG;
import aoh.kingdoms.history.menu_element.button.ButtonGame;
import aoh.kingdoms.history.mainGame.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menu_element.MenuElement;
import java.util.ArrayList;
import aoh.kingdoms.history.menu.Menu;

public class InGame_LoanRepay extends Menu
{
    protected static final int ANIMATION_TIME = 60;
    public static long lTime;
    
    public InGame_LoanRepay() {
        final List<MenuElement> menuElements = new ArrayList<MenuElement>();
        final int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        final int titleHeight = ImageManager.getImage(Images.title500).getHeight();
        final int menuWidth = ImageManager.getImage(Images.title500).getWidth();
        final int menuX = CFG.BUTTON_WIDTH + Renderer.boxBGExtraY + CFG.PADDING;
        final int menuY = ImageManager.getImage(Images.topStats).getHeight() + CFG.PADDING * 2 + ImageManager.getImage(Images.title500).getHeight();
        int buttonY = CFG.PADDING;
        final int buttonX = Images.boxTitleBORDERWIDTH;
        final int tIconMaxW = ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4 + CFG.PADDING / 2;
        final int tButtonH = CFG.BUTTON_HEIGHT2;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_BOLD), "+999 999");
        final int tButtonRightW = (int)Renderer.glyphLayout.width + CFG.PADDING * 4 + CFG.PADDING / 2;
        final int tButtonW = menuWidth - paddingLeft * 2 - CFG.PADDING - tButtonRightW;
        menuElements.add(new ButtonGame(Game.lang.get("Close"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true) {
            @Override
            public void actionElement() {
                Game.menuManager.setVisibleInGame_TakeLoanRepay(false);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        if (Game.getCiv(Game.player.iCivID).iLoansSize == 0) {
            menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT));
        }
        else {
            for (int i = 0; i < Game.getCiv(Game.player.iCivID).iLoansSize; ++i) {
                menuElements.add(new ButtonStatsBudget(i + 1 + ". " + Game.lang.get("Loan") + ", " + Game.lang.get("Expires") + ": " + Game_Calendar.getNumOfDates_ByTurnID(Game.getCiv(Game.player.iCivID).loans.get(i).iExpires_TurnID), Images.loan, paddingLeft, buttonY, tButtonW, tButtonH, tIconMaxW, true));
                menuElements.add(new ButtonStatsBudget_Right2("" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).loans.get(i).fLoanValue - Game.getCiv(Game.player.iCivID).loans.get(i).getLoanValueLeft(), 100), paddingLeft + tButtonW + CFG.PADDING, buttonY, tButtonRightW, tButtonH, i) {
                    @Override
                    public int getSFX() {
                        return SoundsManager.SOUND_LOAN_REPAY;
                    }
                    
                    @Override
                    public String getTextToDraw() {
                        try {
                            if (this.lastValue != Game.getCiv(Game.player.iCivID).loans.get(this.id).fLoanValue - Game.getCiv(Game.player.iCivID).loans.get(this.id).getLoanValueLeft()) {
                                final float fVal = Game.getCiv(Game.player.iCivID).loans.get(this.id).fLoanValue - Game.getCiv(Game.player.iCivID).loans.get(this.id).getLoanValueLeft();
                                this.setText(CFG.getPrecision2(fVal, 100));
                                this.lastValue = fVal;
                                this.nColor = Colors.COLOR_TEXT_MODIFIER_NEGATIVE;
                                this.nColorH = Colors.COLOR_TEXT_MODIFIER_NEGATIVE_HOVER;
                                this.nColorA = Colors.COLOR_TEXT_MODIFIER_NEGATIVE_ACTIVE;
                            }
                        }
                        catch (final IndexOutOfBoundsException ex) {
                            Game.menuManager.rebuildInGame_TakeLoanRepay();
                            InGame_LoanRepay.lTime = 0L;
                        }
                        return super.getTextToDraw();
                    }
                    
                    @Override
                    public void actionElement() {
                        if (Game.getCiv(Game.player.iCivID).repayLoan(this.id)) {
                            InGame_Info.iCivID = Game.player.iCivID;
                            InGame_Info.iCivID2 = 0;
                            Game.menuManager.rebuildInGame_Info(Game.lang.get("LoanRepaid"), Game.lang.get("Loans") + ": " + CFG.getPrecision2((float)Game.getCiv(Game.player.iCivID).iLoansSize, 100) + " / " + Game.getLoanMaxNumber(Game.player.iCivID));
                            InGame_Info.imgID = Images.infoCrown;
                            Game.getCiv(Game.player.iCivID).updateTotalIncomePerMonth();
                        }
                        else {
                            Game.menuManager.addToast_Error(Game.lang.get("InsufficientGold"));
                        }
                        if (Game.getCiv(Game.player.iCivID).iLoansSize == 0) {
                            Game.menuManager.setVisibleInGame_TakeLoanRepay(false);
                        }
                        else {
                            Game.menuManager.rebuildInGame_TakeLoanRepay();
                            InGame_LoanRepay.lTime = 0L;
                        }
                    }
                    
                    @Override
                    public void buildElementHover() {
                        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Repay"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.loan, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Gold") + ": ", CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text(this.getText(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            }
        }
        buttonY = 0;
        for (int i = 0, iSize = menuElements.size(); i < iSize; ++i) {
            if (buttonY < menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING * 2) {
                buttonY = menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING * 2;
            }
        }
        final int tMenuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 2);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, tMenuHeight)));
        this.initMenu(new MenuTitleIMG(Game.lang.get("RepayLoans"), true, false, Images.title500) {
            @Override
            public long getTime() {
                return InGame_LoanRepay.lTime;
            }
        }, CFG.GAME_WIDTH / 2 - menuWidth / 2, CFG.GAME_HEIGHT / 4, menuWidth, tMenuHeight, menuElements, false, true);
        this.drawScrollPositionAlways = false;
    }
    
    @Override
    public void draw(final SpriteBatch oSB, final int iTranslateX, int iTranslateY, final boolean menuIsActive, final Status titleStatus) {
        if (InGame_LoanRepay.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateY = iTranslateY - CFG.BUTTON_HEIGHT + (int)(CFG.BUTTON_HEIGHT * ((CFG.currentTimeMillis - InGame_LoanRepay.lTime) / 60.0f));
        }
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.civInfoOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.civInfoOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.civInfoOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
    
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        InGame_LoanRepay.lTime = CFG.currentTimeMillis;
    }
    
    static {
        InGame_LoanRepay.lTime = 0L;
    }
}
