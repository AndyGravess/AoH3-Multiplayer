// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.jakowski;

import aoc.kingdoms.lukasz.menusMapEditor.EditorMapPortPosition;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapProvinceNamePoints;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapArmyPosition;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapSeaProvinces;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapLinesWaves;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapLines;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapProvinceConnections;
import aoc.kingdoms.lukasz.menusEditor.GameCivsEdit;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioAssign;
import aoc.kingdoms.lukasz.menus.Settings.Settings_Menu;
import aoc.kingdoms.lukasz.menu_element.Toast;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import java.util.List;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.jakowski.desktop.DesktopLauncher;
import aoc.kingdoms.lukasz.menusInGame.Civ.InGame_Civ;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_Intervene;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_ShareTechnology;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_LiberateCivilization;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_Rivals_End;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_CallAllies;
import aoc.kingdoms.lukasz.menusInGame.AllianceSpecial.InGame_AllianceSpecialReformHRE;
import aoc.kingdoms.lukasz.menusInGame.DiplomacyMessage.InGame_MessageInsult;
import aoc.kingdoms.lukasz.menusInGame.DiplomacyMessage.InGame_MessageGuarantee;
import aoc.kingdoms.lukasz.menusInGame.DiplomacyMessage.InGame_MessageDemandsMilitaryAccess;
import aoc.kingdoms.lukasz.menusInGame.DiplomacyMessage.InGame_MessageNonAggressionPact;
import aoc.kingdoms.lukasz.menusInGame.DiplomacyMessage.InGame_MessageDefensivePact;
import aoc.kingdoms.lukasz.menusInGame.DiplomacyMessage.InGame_MessageAlliance;
import aoc.kingdoms.lukasz.menusInGame.DiplomacyMessage.InGame_MessageGift;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_SendSpy;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_Rivals;
import aoc.kingdoms.lukasz.menusInGame.Laws.InGame_LawReform;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_SendGift;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_Guarantee;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_OfferMilitaryAccess;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_DemandMilitaryAccess;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_Alliance;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_NonAggression;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_DefensivePact;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_SendInsult;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_DeclareWar;
import aoc.kingdoms.lukasz.menusInGame.Court.ChangeGovernmentReligion.InGame_ChangeIdeology2;
import aoc.kingdoms.lukasz.menusInGame.Upgrade.InGame_UpgradeCapital;
import aoc.kingdoms.lukasz.menusInGame.InGame_MoveCapital_PopUp;
import aoc.kingdoms.lukasz.menusInGame.AtomicNukes.InGame_BuildAtomicBomb;
import aoc.kingdoms.lukasz.menusInGame.Upgrade.InGame_UpgradeNuclearReactor;
import aoc.kingdoms.lukasz.menusInGame.Upgrade.InGame_UpgradeSupremeCourt;
import aoc.kingdoms.lukasz.menusInGame.Upgrade.InGame_UpgradeMilitaryAcademyForGenerals;
import aoc.kingdoms.lukasz.menusInGame.Upgrade.InGame_UpgradeMilitaryAcademy;
import aoc.kingdoms.lukasz.menusInGame.Buildings.InGame_Destroy;
import aoc.kingdoms.lukasz.menusInGame.InGame_ConvertReligion;
import aoc.kingdoms.lukasz.menu.MenuManager;
import aoc.kingdoms.lukasz.menusInGame.Province.InGame_ProvinceArmy_Invasion;
import aoc.kingdoms.lukasz.menusInGame.Province.InGame_ProvinceArmy_Regroup;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_CourtOptions;
import aoc.kingdoms.lukasz.menusInGame.RecruitArmy.InGame_RecruitArmy_NewArmy;
import aoc.kingdoms.lukasz.menusInGame.RecruitArmy.InGame_RecruitArmy;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_CourtOptions2;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court;
import aoc.kingdoms.lukasz.menusInGame.InGame;
import aoc.kingdoms.lukasz.menu.View;
import aoc.kingdoms.lukasz.menus.Dialog;
import aoc.kingdoms.lukasz.menusInGame.Province.InGame_ProvinceArmy;
import aoc.kingdoms.lukasz.map.map.MapEdgeMove;

public class AA_KeyManager
{
    public static boolean SHIFT_HOLD;
    public static boolean CTRL_HOLD;
    public static boolean ALT_HOLD;
    public static keyExtraAction keyExtraAction;
    
    public static boolean keyDown(final int keycode) {
        if (Keyboard.keyboardMode) {
            return true;
        }
        if (CFG.isDesktop()) {
            if (keycode == 59 || keycode == 60) {
                AA_KeyManager.SHIFT_HOLD = true;
            }
            if (keycode == 129 || keycode == 130) {
                AA_KeyManager.CTRL_HOLD = true;
            }
            if (keycode == 57 || keycode == 58) {
                AA_KeyManager.ALT_HOLD = true;
            }
        }
        Game.soundsManager.playSound(SoundsManager.SOUND_CLICK_MAIN2);
        if (AA_KeyManager.keyExtraAction.extraAction(keycode)) {
            return true;
        }
        if (!AA_KeyManager.CTRL_HOLD) {
            if (keycode == 21 || keycode == 29) {
                Game.mapEdgeMove.MAP_MOVE_LEFT = true;
                Game.mapEdgeMove.MAP_MOVE_RIGHT = false;
                Game.mapEdgeMove.lScrollTime_MAP = CFG.currentTimeMillis;
                Game.mapEdgeMove.iScroll_MAP = (float)MapEdgeMove.DEFAULT_SCROLL;
                final MapEdgeMove mapEdgeMove28;
                final MapEdgeMove mapEdgeMove27;
                final MapEdgeMove mapEdgeMove26;
                final MapEdgeMove mapEdgeMove41;
                final MapEdgeMove mapEdgeMove25 = mapEdgeMove41 = (mapEdgeMove26 = (mapEdgeMove27 = (mapEdgeMove28 = Game.mapEdgeMove)));
                ++mapEdgeMove41.MAP_MOVE_KEYBOARD;
            }
            if (keycode == 22 || keycode == 32) {
                Game.mapEdgeMove.MAP_MOVE_RIGHT = true;
                Game.mapEdgeMove.MAP_MOVE_LEFT = false;
                Game.mapEdgeMove.lScrollTime_MAP = CFG.currentTimeMillis;
                Game.mapEdgeMove.iScroll_MAP = (float)MapEdgeMove.DEFAULT_SCROLL;
                final MapEdgeMove mapEdgeMove32;
                final MapEdgeMove mapEdgeMove31;
                final MapEdgeMove mapEdgeMove30;
                final MapEdgeMove mapEdgeMove42;
                final MapEdgeMove mapEdgeMove29 = mapEdgeMove42 = (mapEdgeMove30 = (mapEdgeMove31 = (mapEdgeMove32 = Game.mapEdgeMove)));
                ++mapEdgeMove42.MAP_MOVE_KEYBOARD;
            }
            if (keycode == 19 || keycode == 51) {
                Game.mapEdgeMove.MAP_MOVE_TOP = true;
                Game.mapEdgeMove.MAP_MOVE_BOT = false;
                Game.mapEdgeMove.lScrollTime_MAP = CFG.currentTimeMillis;
                Game.mapEdgeMove.iScroll_MAP = (float)MapEdgeMove.DEFAULT_SCROLL;
                final MapEdgeMove mapEdgeMove36;
                final MapEdgeMove mapEdgeMove35;
                final MapEdgeMove mapEdgeMove34;
                final MapEdgeMove mapEdgeMove43;
                final MapEdgeMove mapEdgeMove33 = mapEdgeMove43 = (mapEdgeMove34 = (mapEdgeMove35 = (mapEdgeMove36 = Game.mapEdgeMove)));
                ++mapEdgeMove43.MAP_MOVE_KEYBOARD;
            }
            if (keycode == 20 || keycode == 47) {
                Game.mapEdgeMove.MAP_MOVE_BOT = true;
                Game.mapEdgeMove.MAP_MOVE_TOP = false;
                Game.mapEdgeMove.lScrollTime_MAP = CFG.currentTimeMillis;
                Game.mapEdgeMove.iScroll_MAP = (float)MapEdgeMove.DEFAULT_SCROLL;
                final MapEdgeMove mapEdgeMove40;
                final MapEdgeMove mapEdgeMove39;
                final MapEdgeMove mapEdgeMove38;
                final MapEdgeMove mapEdgeMove44;
                final MapEdgeMove mapEdgeMove37 = mapEdgeMove44 = (mapEdgeMove38 = (mapEdgeMove39 = (mapEdgeMove40 = Game.mapEdgeMove)));
                ++mapEdgeMove44.MAP_MOVE_KEYBOARD;
            }
        }
        return false;
    }
    
    public static final void actionPinnedArmy(final int id) {
        try {
            final Game.ArmyPos nArmyPos;
            if (Game.player.playerData.pinnedArmies.size() > id && (nArmyPos = Game.findArmy_FullCheck(Game.player.iCivID, Game.player.playerData.pinnedArmies.get(id))) != null) {
                Game.setActiveArmy(nArmyPos.iProvinceID, Game.player.playerData.pinnedArmies.get(id));
                InGame_ProvinceArmy.iActiveID = 0;
                InGame_ProvinceArmy.sActiveKEY = Game.player.playerData.pinnedArmies.get(id);
                Game.menuManager.rebuildInGame_ProvinceArmy();
                Game.menuManager.rebuildInGame_ProvinceArmy_HideMenus();
                Game.gameActiveProvince.resetLastActiveProvince();
                Game.setActiveProvinceID(-1);
                Game.animationHover.resetAnimationData();
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static boolean keyUp(final int keycode) {
        if (keycode == 61) {
            if (Game.menuManager.getVisibleInGame_Console()) {
                Game.menuManager.setVisibleInGame_Console(false);
            }
            else {
                Game.menuManager.setVisibleInGame_Console(true);
            }
        }
        if (Keyboard.keyboardMode) {
            if (keycode == 66 || keycode == 160) {
                Keyboard.keyboardAction.save();
                return true;
            }
            if (keycode == 111) {
                Game.keyboard.hideKeyboard();
                return true;
            }
        }
        if (CFG.isDesktop()) {
            if (keycode == 59 || keycode == 60) {
                AA_KeyManager.SHIFT_HOLD = false;
            }
            if (keycode == 129 || keycode == 130) {
                AA_KeyManager.CTRL_HOLD = false;
            }
            if (keycode == 57 || keycode == 58) {
                AA_KeyManager.ALT_HOLD = false;
            }
        }
        if (!Game.menuManager.getInMapEditorArmyPosition()) {
            if (keycode == 21 || keycode == 29) {
                Game.mapEdgeMove.MAP_MOVE_LEFT = false;
            }
            if (keycode == 22 || keycode == 32) {
                Game.mapEdgeMove.MAP_MOVE_RIGHT = false;
            }
            if (keycode == 19 || keycode == 51) {
                Game.mapEdgeMove.MAP_MOVE_TOP = false;
            }
            if (keycode == 20 || keycode == 47) {
                Game.mapEdgeMove.MAP_MOVE_BOT = false;
            }
        }
        final MapEdgeMove mapEdgeMove10;
        final MapEdgeMove mapEdgeMove9;
        final MapEdgeMove mapEdgeMove8;
        final MapEdgeMove mapEdgeMove11;
        final MapEdgeMove mapEdgeMove7 = mapEdgeMove11 = (mapEdgeMove8 = (mapEdgeMove9 = (mapEdgeMove10 = Game.mapEdgeMove)));
        --mapEdgeMove11.MAP_MOVE_KEYBOARD;
        Game.mapEdgeMove.MAP_MOVE_KEYBOARD = Math.max(0, Game.mapEdgeMove.MAP_MOVE_KEYBOARD);
        if (CFG.isDesktop()) {
            if (Game.menuManager.dialogMenu.getVisible()) {
                if (keycode == 66 || keycode == 160 || keycode == 62) {
                    Game.menuManager.dialogMenu.disableButtons();
                    Dialog.dialogTrue();
                    Game.menuManager.dialogMenu.closeMenu();
                }
                else if (keycode == 111 || keycode == 67) {
                    Game.menuManager.dialogMenu.disableButtons();
                    Dialog.dialogFalse();
                    Game.menuManager.dialogMenu.closeMenu();
                }
                return true;
            }
            if (keycode == 141) {
                Game.soundsManager.loadNextMusic();
                return true;
            }
            if (!Keyboard.keyboardMode) {
                if (keycode == 44) {
                    Game.mapScale.scrollScale(-1);
                }
                else if (keycode == 43) {
                    Game.mapScale.scrollScale(1);
                }
            }
            if (Game.menuManager.getInGameHideUI() && keycode == 111) {
                Game.menuManager.setViewIDWithoutAnimation(View.IN_GAME);
                return true;
            }
            if (Game.menuManager.getInGame()) {
                if (InGame.ONLY_MAP_MODE) {
                    InGame.ONLY_MAP_MODE = false;
                    return true;
                }
                if (Keyboard.keyboardMode) {
                    return true;
                }
                if (!Game.menuManager.getVisibleInGame_Escape()) {
                    if (keycode == 131) {
                        InGame.action1();
                    }
                    else if (keycode == 132) {
                        InGame.action2();
                    }
                    else if (keycode == 133) {
                        InGame.action3();
                    }
                    else if (keycode == 134) {
                        InGame.action4();
                    }
                    else if (keycode == 135) {
                        InGame.action5();
                    }
                    else if (keycode == 136) {
                        InGame.action6();
                    }
                }
                if (AA_KeyManager.CTRL_HOLD && keycode == 29) {
                    Game.clearActiveArmy();
                    for (int i = 0; i < Game.getCiv(Game.player.iCivID).iArmyPositionSize; ++i) {
                        final int tID = Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmyKeyID(Game.getCiv(Game.player.iCivID).getArmyPositionKey(i));
                        if (tID >= 0) {
                            final Game.HoveredArmy nHA = new Game.HoveredArmy();
                            nHA.iCivID = Game.player.iCivID;
                            nHA.iProvinceID = Game.getCiv(Game.player.iCivID).getArmyPosition(i);
                            nHA.key = Game.getCiv(Game.player.iCivID).getArmyPositionKey(i);
                            nHA.iArmyID = tID;
                            Game.addActiveArmy(nHA);
                        }
                    }
                    if (Game.activeArmySize > 0) {
                        Game.setActiveProvinceID(-1);
                        Game.menuManager.rebuildInGame_ProvinceArmy();
                    }
                    else {
                        Game.menuManager.setVisibleInGame_ProvinceArmy(false);
                    }
                    return true;
                }
                if (keycode == 145 || keycode == 8) {
                    Game.gameThread.updateSpeed(1);
                    Game.menuManager.TOAST_TIME = 0L;
                }
                else if (keycode == 146 || keycode == 9) {
                    Game.gameThread.updateSpeed(2);
                    Game.menuManager.TOAST_TIME = 0L;
                }
                else if (keycode == 147 || keycode == 10) {
                    Game.gameThread.updateSpeed(3);
                    Game.menuManager.TOAST_TIME = 0L;
                }
                else if (keycode == 148 || keycode == 11) {
                    Game.gameThread.updateSpeed(4);
                    Game.menuManager.TOAST_TIME = 0L;
                }
                else if (keycode == 149 || keycode == 12) {
                    Game.gameThread.updateSpeed(5);
                    Game.menuManager.TOAST_TIME = 0L;
                }
                else if (keycode == 34) {
                    if (!Game.menuManager.getVisibleInGame_Court() || !InGame_Court.inSearchProvinces) {
                        InGame_CourtOptions2.actionCourt(InGame_CourtOptions2.idCourt);
                        InGame_Court.actionSearchProvinces();
                    }
                    else {
                        Game.menuManager.setVisibleInGame_Court(false);
                    }
                }
                else if (keycode == 50) {
                    if (!Game.menuManager.getVisibleInGame_RecruitArmy() || !Game.menuManager.inCreateNewArmy) {
                        Game.menuManager.hideMenus_RecruitArmy(false);
                        if (Game.menuManager.getVisibleInGame_Armies()) {
                            Game.menuManager.setVisibleInGame_Armies(false);
                        }
                        InGame_RecruitArmy.actionCreateNewArmy();
                        if (Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() == Game.player.iCivID) {
                            InGame_RecruitArmy_NewArmy.iProvinceID = Game.iActiveProvince;
                        }
                    }
                    else {
                        Game.menuManager.setVisibleInGame_RecruitArmy(false);
                    }
                }
                else if (keycode == 30) {
                    if (Game.menuManager.getVisibleInGame_Court() && InGame_CourtOptions.buildID != InGame_CourtOptions.iActiveID) {
                        InGame_CourtOptions2.actionBuildings(InGame_CourtOptions.buildID);
                    }
                    else if (!Game.menuManager.getVisibleInGame_Court()) {
                        InGame.action1();
                        InGame_CourtOptions2.actionBuildings(InGame_CourtOptions.buildID);
                    }
                    else {
                        Game.menuManager.setVisibleInGame_Court(false);
                    }
                }
                else if (keycode == 40) {
                    if (Game.menuManager.getVisibleInGame_Court() && InGame_CourtOptions.iLawID != InGame_CourtOptions.iActiveID) {
                        InGame_CourtOptions2.actionLaws(InGame_CourtOptions.iLawID);
                    }
                    else if (!Game.menuManager.getVisibleInGame_Court()) {
                        InGame.action1();
                        InGame_CourtOptions2.actionLaws(InGame_CourtOptions.iLawID);
                    }
                    else {
                        Game.menuManager.setVisibleInGame_Court(false);
                    }
                }
                else if (keycode == 35) {
                    if (!Game.menuManager.getVisibleInGame_Generals()) {
                        InGame_RecruitArmy.actionGenerals();
                    }
                    else {
                        Game.menuManager.setVisibleInGame_Generals(false);
                    }
                }
                else if (keycode == 41) {
                    if (!Game.menuManager.getVisibleInGame_Armies()) {
                        Game.menuManager.hideMenus_RecruitArmy(false);
                    }
                    InGame_RecruitArmy.actionArmies();
                }
                else if (keycode == 31) {
                    if (!Game.menuManager.getVisibleInGame_Armies()) {
                        Game.menuManager.hideMenus_RecruitArmy(false);
                    }
                    InGame_RecruitArmy.actionMercenaries();
                }
                else if (keycode == 46) {
                    InGame.actionRanking();
                }
                else if (keycode == 48) {
                    InGame.actionCurrent();
                }
                else if (keycode == 66 || keycode == 160) {
                    if (Game.menuManager.getVisibleInGame_RecruitArmy() && Game.menuManager.inCreateNewArmy) {
                        InGame_RecruitArmy_NewArmy.actionCreateNewArmy();
                    }
                    else if (Game.regroupArmyMode) {
                        if (Game.iRegroupArmyProvincesSize > 0) {
                            InGame_ProvinceArmy_Regroup.confirm();
                        }
                    }
                    else if (Game.invasionArmyMode) {
                        if (Game.invasionArmyProvincesSize > 0) {
                            InGame_ProvinceArmy_Invasion.confirm();
                        }
                    }
                    else if (Game.menuManager.getVisibleInGame_PopUp()) {
                        if (MenuManager.IN_GAME_POP_UP_MENU_ID == 0) {
                            InGame_ConvertReligion.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 1) {
                            InGame_Destroy.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 2) {
                            InGame_UpgradeMilitaryAcademy.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 3) {
                            InGame_UpgradeMilitaryAcademyForGenerals.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 4) {
                            InGame_UpgradeSupremeCourt.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 5) {
                            InGame_UpgradeNuclearReactor.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 6) {
                            InGame_BuildAtomicBomb.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 7) {
                            InGame_MoveCapital_PopUp.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 8) {
                            InGame_UpgradeCapital.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 11) {
                            InGame_ChangeIdeology2.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 12) {
                            InGame_DeclareWar.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 13) {
                            InGame_SendInsult.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 16) {
                            InGame_DefensivePact.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 17) {
                            InGame_NonAggression.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 18) {
                            InGame_Alliance.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 19) {
                            InGame_DemandMilitaryAccess.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 20) {
                            InGame_OfferMilitaryAccess.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 21) {
                            InGame_Guarantee.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 22) {
                            InGame_SendGift.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 24) {
                            InGame_LawReform.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 25) {
                            InGame_Rivals.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 27) {
                            InGame_SendSpy.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 28) {
                            InGame_MessageGift.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 29) {
                            InGame_MessageAlliance.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 30) {
                            InGame_MessageDefensivePact.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 31) {
                            InGame_MessageNonAggressionPact.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 32) {
                            InGame_MessageDemandsMilitaryAccess.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 33) {
                            InGame_MessageGuarantee.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 34) {
                            InGame_MessageInsult.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 36) {
                            InGame_AllianceSpecialReformHRE.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 37) {
                            InGame_CallAllies.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 40) {
                            InGame_Rivals_End.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 43) {
                            InGame_LiberateCivilization.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 44) {
                            InGame_ShareTechnology.confirm();
                        }
                        else if (MenuManager.IN_GAME_POP_UP_MENU_ID == 48) {
                            InGame_Intervene.confirm();
                        }
                    }
                }
                else if (keycode == 61) {
                    if (Game.menuManager.getVisibleInGame_Civ()) {
                        InGame_Civ.diplomacyMode = !InGame_Civ.diplomacyMode;
                        InGame_Civ.iRebuildToCivID = InGame_Civ.iActiveCivID;
                        Game.menuManager.rebuildInGame_Civ(true);
                        InGame_Civ.lTime = 0L;
                    }
                }
                else if (keycode == 137) {
                    InGame.ONLY_MAP_MODE = !InGame.ONLY_MAP_MODE;
                }
                else if (keycode == 142) {
                    if (Game.menuManager.getVisibleInGame_Console()) {
                        Game.menuManager.setVisibleInGame_Console(false);
                    }
                    else {
                        Game.menuManager.setVisibleInGame_Console(false);
                    }
                }
                else if (keycode == 3) {
                    if (Game.getCiv(Game.player.iCivID).getCapitalProvinceID() >= 0) {
                        Game.mapCoords.centerToProvinceID(Game.getCiv(Game.player.iCivID).getCapitalProvinceID());
                    }
                }
                else {
                    if (keycode == 111) {
                        if (Game.regroupArmyMode) {
                            Game.setRegroupArmyMode(false);
                        }
                        else if (Game.invasionArmyMode) {
                            Game.setInvasionArmyMode(false);
                        }
                        else if (Game.menuManager.getVisibleInGame_SaveGame()) {
                            Game.menuManager.setVisibleInGame_SaveGame(false);
                        }
                        else if (Game.menuManager.getVisibleInGame_Escape()) {
                            Game.menuManager.setVisibleInGame_Escape(!Game.menuManager.getVisibleInGame_Escape());
                        }
                        else if (Game.menuManager.getVisibleInGame_Console()) {
                            Game.menuManager.setVisibleInGame_Console(false);
                        }
                        else if (Game.menuManager.getVisibleInGame_TechnologyTree()) {
                            Game.menuManager.setVisibleInGame_TechnologyTree(false);
                        }
                        else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_RECRUIT_ARMY) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                        }
                        else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_NEW_ARMY_CHOOSE_PROVINCE) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                        }
                        else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_NUKE_CHOOSE_PROVINCE) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                        }
                        else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_COLONIZE_CHOOSE_PROVINCE) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                        }
                        else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_WARS) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                        }
                        else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_BUILDING) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                        }
                        else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_CONVERT_RELIGION) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                        }
                        else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_CORE) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                        }
                        else if (Game.menuManager.getVisibleInGame_PopUp()) {
                            Game.menuManager.setVisibleInGame_PopUp(false);
                        }
                        else if (Game.menuManager.getVisibleInGame_TakeLoanRepay()) {
                            Game.menuManager.setVisibleInGame_TakeLoanRepay(false);
                        }
                        else if (Game.menuManager.getVisibleInGame_TakeLoan()) {
                            Game.menuManager.setVisibleInGame_TakeLoan(false);
                        }
                        else if (Game.menuManager.getVisibleInGame_Wonder()) {
                            Game.menuManager.setVisibleInGame_Wonder(false);
                            if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_WONDERS) {
                                Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                            }
                        }
                        else if (Game.menuManager.getVisibleInGame_Nukes()) {
                            Game.menuManager.setVisibleInGame_Nukes(false);
                        }
                        else if (Game.menuManager.getVisibleInGame_GoodsMarket()) {
                            Game.menuManager.setVisibleInGame_GoodsMarket(false);
                        }
                        else if (Game.menuManager.getVisibleInGame_Goods()) {
                            Game.menuManager.setVisibleInGame_Goods(false);
                        }
                        else if (Game.menuManager.getVisibleInGame_DisbandArmy()) {
                            Game.menuManager.setVisibleInGame_DisbandUnits(false);
                        }
                        else if (Game.menuManager.getVisibleInGame_ReorganizeUnits()) {
                            Game.menuManager.setVisibleInGame_ReorganizeUnits(false);
                        }
                        else if (Game.menuManager.getVisibleInGame_GeneralRecruit()) {
                            Game.menuManager.setVisibleInGame_GeneralRecruit(false);
                        }
                        else if (Game.menuManager.getVisibleInGame_RecruitArmy()) {
                            Game.menuManager.setVisibleInGame_RecruitArmy(false);
                        }
                        else if (Game.menuManager.getVisibleInGame_TechnologyChoose()) {
                            Game.menuManager.setVisibleInGame_TechnologyChoose(false);
                        }
                        else if (Game.menuManager.getVisibleInGame_Budget()) {
                            Game.menuManager.setVisibleInGame_Budget(false);
                        }
                        else if (Game.menuManager.getVisibleInGame_Civ()) {
                            Game.menuManager.setVisibleInGame_Civ(false);
                        }
                        else if (Game.menuManager.getVisibleInGame_Court()) {
                            Game.menuManager.setVisibleInGame_Court(false);
                        }
                        else if (Game.menuManager.getVisibleInGame_ProvinceBonuses()) {
                            Game.menuManager.setVisibleInGame_ProvinceBonuses(false, false);
                        }
                        else if (Game.menuManager.getVisibleInGame_CivBonuses()) {
                            Game.menuManager.setVisibleInGame_CivBonuses(false);
                        }
                        else if (Game.menuManager.getVisibleInGame_Armies()) {
                            Game.menuManager.setVisibleInGame_Armies(false);
                        }
                        else if (Game.menuManager.getVisibleInGame_Generals()) {
                            Game.menuManager.setVisibleInGame_Generals(false);
                        }
                        else if (Game.menuManager.getVisibleInGame_Buildings()) {
                            Game.menuManager.setVisibleInGame_Buildings(false, false);
                        }
                        else if (Game.menuManager.getVisibleInGame_CurrentSituation()) {
                            Game.menuManager.setVisibleInGame_CurrentSituation(false);
                        }
                        else if (Game.menuManager.getVisibleInGame_Battle()) {
                            Game.menuManager.setVisibleInGame_Battle(false);
                        }
                        else if (Game.menuManager.getVisibleInGame_Siege()) {
                            Game.menuManager.setVisibleInGame_Siege(false);
                        }
                        else if (Game.menuManager.getVisibleInGame_War()) {
                            Game.menuManager.setVisibleInGame_War(false);
                        }
                        else if (Game.menuManager.getVisibleInGame_Peace()) {
                            Game.menuManager.setVisibleInGame_Peace(false);
                        }
                        else if (Game.menuManager.getVisibleInGame_ProvinceArmy()) {
                            Game.menuManager.setVisibleInGame_ProvinceArmy(false);
                            Game.clearActiveArmy();
                        }
                        else if (Game.menuManager.getVisibleInGame_ProvinceInfo()) {
                            Game.menuManager.setVisibleInGame_ProvinceInfo(false);
                            Game.gameActiveProvince.resetLastActiveProvince();
                            Game.setActiveProvinceID(-1);
                        }
                        else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INVEST_IN_ECONOMY) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                        }
                        else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEVELOP_INFRASTRUCTURE) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                        }
                        else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INCREASE_TAX_EFFICIENCY) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                        }
                        else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INCREASE_MANPOWER) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                        }
                        else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_MOVE_CAPITAL) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                        }
                        else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INCREASE_GROWTH_RATE) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                        }
                        else {
                            Game.menuManager.setVisibleInGame_Escape(!Game.menuManager.getVisibleInGame_Escape());
                        }
                        return true;
                    }
                    if (keycode == 62) {
                        if (DesktopLauncher.host) {
                            if (!Game.gameThread.play) {
                                DesktopLauncher.SMS("RTS: ON");
                            }
                            else {
                                DesktopLauncher.SMS("RTS: OFF");
                            }
                            Game.gameThread.play = !Game.gameThread.play;
                        }
                        else {
                            final ArrayList<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
                            final ArrayList<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
                            nData.add(new MenuElement_HoverElement_Type_Text("Only the host can start or stop the game!", CFG.FONT_BOLD, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            Game.menuManager.addToast(new Toast(nElements, 0, 10000, CFG.GAME_WIDTH, ImageManager.getImage(Images.topStats).getHeight() + CFG.PADDING * 2));
                        }
                    }
                    else if (keycode == 69 || keycode == 156) {
                        Game.gameThread.updateSpeedMinus();
                        Game.menuManager.TOAST_TIME = 0L;
                    }
                    else if (keycode == 81 || keycode == 157) {
                        Game.gameThread.updateSpeedPlus();
                        Game.menuManager.TOAST_TIME = 0L;
                    }
                }
            }
            else if (Game.menuManager.getInGameLegacies()) {
                if (keycode == 111 || keycode == 131 || keycode == 132 || keycode == 133 || keycode == 134 || keycode == 135 || keycode == 136) {
                    Game.menuManager.setViewIDWithoutAnimation(View.IN_GAME);
                }
            }
            else if (Game.menuManager.getInSettingsMenu()) {
                if (keycode == 111) {
                    Game.menuManager.setViewID(Settings_Menu.goBackToMenu);
                }
            }
            else if (Game.menuManager.getInScenarioAssign()) {
                if (keycode == 59 || keycode == 60) {
                    CFG.brushTool = !CFG.brushTool;
                }
                else if (keycode == 67) {
                    ScenarioAssign.popUndo();
                }
                else if (keycode == 45) {
                    CFG.iCreateScenario_AssignProvinces_Civ = 0;
                }
            }
            else if (Game.menuManager.getInScenarioAssignInGame()) {
                if (keycode == 59 || keycode == 60) {
                    CFG.brushTool = !CFG.brushTool;
                }
                else if (keycode == 45) {
                    CFG.iCreateScenario_AssignProvinces_Civ = 0;
                }
            }
            else if (Game.menuManager.getInScenarioCores()) {
                if (keycode == 59 || keycode == 60) {
                    CFG.brushTool = !CFG.brushTool;
                }
            }
            else if (Game.menuManager.getInScenarioReligion() || Game.menuManager.getInScenarioEditorBuildings()) {
                if (keycode == 59 || keycode == 60) {
                    CFG.brushTool = !CFG.brushTool;
                }
            }
            else if (Game.menuManager.getInNewGame()) {
                if (keycode == 33) {
                    GameCivsEdit.nCiv = Game.loadCivilization(Game.ideologiesManager.getRealTag(Game.getCiv(Game.player.iCivID).getCivTag()));
                    GameCivsEdit.goBackTo = View.NEW_GAME;
                    Game.menuManager.setViewID(View.EDITOR_GAMECIVS_EDIT);
                }
                else if (keycode == 46) {
                    GameCivsEdit.nCiv = Game.loadCivilization(Game.getCiv(Game.player.iCivID).getCivTag());
                    GameCivsEdit.goBackTo = View.NEW_GAME;
                    Game.menuManager.setViewID(View.EDITOR_GAMECIVS_EDIT);
                }
            }
        }
        return false;
    }
    
    public static final void updateKeyExtraAction() {
        AA_KeyManager.keyExtraAction = (Game.menuManager.getInMapEditorProvinceConnections() ? new keyExtraAction() {
            @Override
            public boolean extraAction(final int keycode) {
                return EditorMapProvinceConnections.keyUp(keycode);
            }
        } : (Game.menuManager.getInMapEditorLines() ? new keyExtraAction() {
            @Override
            public boolean extraAction(final int keycode) {
                return EditorMapLines.keyUp(keycode);
            }
        } : (Game.menuManager.getInMapEditorWaves() ? new keyExtraAction() {
            @Override
            public boolean extraAction(final int keycode) {
                return EditorMapLinesWaves.keyUp(keycode);
            }
        } : (Game.menuManager.getInMapEditorSeaProvinces() ? new keyExtraAction() {
            @Override
            public boolean extraAction(final int keycode) {
                return EditorMapSeaProvinces.keyUp(keycode);
            }
        } : (Game.menuManager.getInMapEditorArmyPosition() ? new keyExtraAction() {
            @Override
            public boolean extraAction(final int keycode) {
                return EditorMapArmyPosition.keyUp(keycode);
            }
        } : (Game.menuManager.getInMapEditorProvinceNamePoints() ? new keyExtraAction() {
            @Override
            public boolean extraAction(final int keycode) {
                return EditorMapProvinceNamePoints.keyUp(keycode);
            }
        } : (Game.menuManager.getInMapEditorPortPosition() ? new keyExtraAction() {
            @Override
            public boolean extraAction(final int keycode) {
                return EditorMapPortPosition.keyUp(keycode);
            }
        } : new keyExtraAction() {
            @Override
            public boolean extraAction(final int keycode) {
                return false;
            }
        })))))));
    }
    
    static {
        AA_KeyManager.SHIFT_HOLD = false;
        AA_KeyManager.CTRL_HOLD = false;
        AA_KeyManager.ALT_HOLD = false;
        AA_KeyManager.keyExtraAction = new keyExtraAction() {
            @Override
            public boolean extraAction(final int keycode) {
                return false;
            }
        };
    }
    
    public interface keyExtraAction
    {
        boolean extraAction(final int p0);
    }
}
