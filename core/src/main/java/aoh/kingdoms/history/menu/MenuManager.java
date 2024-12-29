// 
// Decompiled by Procyon v0.6.0
// 

package aoh.kingdoms.history.menu;

import aoh.kingdoms.history.menusInGame.Buildings.InGame_BuildingsGroupID;
import aoh.kingdoms.history.menusInGame.Buildings.InGame_BuildingsGroup;
import aoh.kingdoms.history.mainGame.zOther.Point_XY;
import aoh.kingdoms.history.menusInGame.Info.InGame_Info;
import aoh.kingdoms.history.menusInGame.Info.InGame_Info_2;
import aoh.kingdoms.history.mainGame.Game_Calendar;
import aoh.kingdoms.history.mainGame.Game_Ages;
import aoh.kingdoms.history.menusInGame.InGame_Wonder;
import aoh.kingdoms.history.menusInGame.InGame_MessagesWars;
import aoh.kingdoms.history.menusInGame.InGame_Messages;
import aoh.kingdoms.history.menusInGame.Goods.InGame_Goods;
import aoh.kingdoms.history.menusInGame.Goods.InGame_GoodsMarket_Resource;
import aoh.kingdoms.history.menusInGame.Goods.InGame_GoodsMarket;
import aoh.kingdoms.history.menusInGame.Goods.InGame_Goods_Provinces;
import aoh.kingdoms.history.menusInGame.Graph.InGame_GraphPopulation;
import aoh.kingdoms.history.menusInGame.Goods.InGame_Goods_LargestProducers;
import aoh.kingdoms.history.menusInGame.AtomicNukes.InGame_Nukes;
import aoh.kingdoms.history.menusInGame.InGame_ListOfUnits;
import aoh.kingdoms.history.menusInGame.InGame_ListOfBuildings;
import aoh.kingdoms.history.menusInGame.Technology.InGame_TechnologyChoose;
import aoh.kingdoms.history.menusInGame.Battle.InGame_Battle_Full;
import aoh.kingdoms.history.menusInGame.InGame_MissionTree;
import aoh.kingdoms.history.menusInGame.Technology.InGame_TechnologyTree;
import aoh.kingdoms.history.menusInGame.InGame_AdvisorRecruit;
import aoh.kingdoms.history.menusInGame.InGame_ArmyCustomize;
import aoh.kingdoms.history.menusInGame.InGame_ConvertReligion;
import aoh.kingdoms.history.menusInGame.Buildings.InGame_Destroy;
import aoh.kingdoms.history.menusInGame.Upgrade.InGame_UpgradeMilitaryAcademy;
import aoh.kingdoms.history.menusInGame.Upgrade.InGame_UpgradeMilitaryAcademyForGenerals;
import aoh.kingdoms.history.menusInGame.Upgrade.InGame_UpgradeSupremeCourt;
import aoh.kingdoms.history.menusInGame.Upgrade.InGame_UpgradeNuclearReactor;
import aoh.kingdoms.history.menusInGame.AtomicNukes.InGame_BuildAtomicBomb;
import aoh.kingdoms.history.menusInGame.InGame_MoveCapital_PopUp;
import aoh.kingdoms.history.menusInGame.Upgrade.InGame_UpgradeCapital;
import aoh.kingdoms.history.menusInGame.Court.ChangeGovernmentReligion.InGame_ChangeIdeology;
import aoh.kingdoms.history.menusInGame.Court.ChangeGovernmentReligion.InGame_ChangeIdeology2;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_DeclareWar;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_SendInsult;
import aoh.kingdoms.history.menusInGame.Battle.InGame_BattleReport;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_DefensivePact;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_NonAggression;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_Alliance;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_DemandMilitaryAccess;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_OfferMilitaryAccess;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_Guarantee;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_SendGift;
import aoh.kingdoms.history.menusInGame.Laws.InGame_Laws;
import aoh.kingdoms.history.menusInGame.Laws.InGame_LawReform;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_Rivals;
import aoh.kingdoms.history.menusInGame.InGame_BattleTactics;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_SendSpy;
import aoh.kingdoms.history.menusInGame.DiplomacyMessage.InGame_MessageGift;
import aoh.kingdoms.history.menusInGame.DiplomacyMessage.InGame_MessageAlliance;
import aoh.kingdoms.history.menusInGame.DiplomacyMessage.InGame_MessageDefensivePact;
import aoh.kingdoms.history.menusInGame.DiplomacyMessage.InGame_MessageNonAggressionPact;
import aoh.kingdoms.history.menusInGame.DiplomacyMessage.InGame_MessageDemandsMilitaryAccess;
import aoh.kingdoms.history.menusInGame.DiplomacyMessage.InGame_MessageGuarantee;
import aoh.kingdoms.history.menusInGame.DiplomacyMessage.InGame_MessageInsult;
import aoh.kingdoms.history.menusInGame.AllianceSpecial.InGame_AllianceSpecial;
import aoh.kingdoms.history.menusInGame.AllianceSpecial.InGame_AllianceSpecialReformHRE;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_CallAllies;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_RivalsList;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_FormCiv;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_Rivals_End;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_SellProvince;
import aoh.kingdoms.history.menusInGame.InGame_Sandbox;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_LiberateCivilization;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_ShareTechnology;
import aoh.kingdoms.history.menusInGame.InGame_Revolutions;
import aoh.kingdoms.history.menusInGame.DiplomacyMessage.InGame_MessageCallToWar;
import aoh.kingdoms.history.mainGame.Player.MessageTypes.PMessage;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_AllianceList;
import aoh.kingdoms.history.menusInGame.Diplomacy.InGame_Intervene;
import aoh.kingdoms.history.menusInGame.Court.ChangeGovernmentReligion.InGame_ChangeReligion;
import aoh.kingdoms.history.menusInGame.Court.ChangeGovernmentReligion.InGame_ChangeReligion2;
import aoh.kingdoms.history.menusInGame.Battle.InGame_Battlefield;
import aoh.kingdoms.history.menusInGame.InGame_LoanRepay;
import aoh.kingdoms.history.menusInGame.InGame_SaveGame;
import aoh.kingdoms.history.menusInGame.InGame_Console;
import aoh.kingdoms.history.menusInGame.InGame_Loan;
import aoh.kingdoms.history.menusInGame.InGame_GeneralRecruit;
import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceBonuses;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ_Compare;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ_Economy;
import aoh.kingdoms.history.menusInGame.InGame_CivilizationAdvantages;
import aoh.kingdoms.history.menusInGame.InGame_CivilizationAdvantages2;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ_UnlockedTechnologies;
import aoh.kingdoms.history.menusInGame.Civ.InGame_CivRank;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ_Provinces;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ_List;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ_Population;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ_AggressiveExpansion;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ_ArmiesRegiments;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ_CapitalCity;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ_MilitaryAcademy;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ_Religion;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ_Government;
import aoh.kingdoms.history.menusInGame.Civ.InGame_Civ;
import aoh.kingdoms.history.menusInGame.Budget.InGame_BudgetIncomeBuildings;
import aoh.kingdoms.history.menusInGame.Budget.InGame_BudgetIncomeProduction;
import aoh.kingdoms.history.menusInGame.Budget.InGame_BudgetExpensesBuildingsMaintenance;
import aoh.kingdoms.history.menusInGame.Budget.InGame_BudgetExpensesMaintenance;
import aoh.kingdoms.history.menusInGame.Budget.InGame_BudgetIncomeEconomy;
import aoh.kingdoms.history.menusInGame.Budget.InGame_BudgetBalanceProvinces;
import aoh.kingdoms.history.menusInGame.Budget.InGame_BudgetIncomeProvinces;
import aoh.kingdoms.history.menusInGame.Budget.InGame_BudgetIncomeTaxation;
import aoh.kingdoms.history.menusInGame.InGame_Wonders;
import aoh.kingdoms.history.menusInGame.InGame_CurrentSituation;
import aoh.kingdoms.history.menusInGame.InGame_ReleaseAVassal;
import aoh.kingdoms.history.menusInGame.InGame_Missions;
import aoh.kingdoms.history.menusInGame.InGame_Ranking;
import aoh.kingdoms.history.menusInGame.InGame_Event;
import aoh.kingdoms.history.events.Event;
import aoh.kingdoms.history.menusInGame.Budget.InGame_Budget;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court_Build;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court_Buildings2;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court_Religion;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court_Core;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court_DevelopInfrastructure;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court_IncreaseGrowthRate;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court_IncreaseManpower;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court_Espionage;
import aoh.kingdoms.history.menusInGame.Court.World.InGame_Court_WorldVassals;
import aoh.kingdoms.history.menusInGame.Court.World.InGame_Court_WorldNonAggression;
import aoh.kingdoms.history.menusInGame.Court.World.InGame_Court_WorldDefensive;
import aoh.kingdoms.history.menusInGame.Court.World.InGame_Court_WorldTruces;
import aoh.kingdoms.history.menusInGame.Court.World.InGame_Court_WorldAlliances;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court_GoldenAges;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court_Missions;
import aoh.kingdoms.history.menusInGame.Court.World.InGame_Court_WorldCivs;
import aoh.kingdoms.history.menusInGame.Court.World.InGame_Court_WorldSearch;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court_Stats;
import aoh.kingdoms.history.menusInGame.Court.World.InGame_Court_WorldWars;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court_Provinces;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court_Law;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court_Government;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court_IncreaseTaxEfficiency;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court_InvestInEconomy;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court_ExploitEconomy;
import aoh.kingdoms.history.menusInGame.Court.InGame_Court;
import aoh.kingdoms.history.menusInGame.InGame_CivBonuses;
import aoh.kingdoms.history.menusInGame.RecruitArmy.InGame_RecruitMercenaries;
import aoh.kingdoms.history.menusInGame.InGame_Armies;
import aoh.kingdoms.history.menusInGame.Right.InGame_RightTaxEfficiency;
import aoh.kingdoms.history.menusInGame.Right.InGame_RightProvinceIncome;
import aoh.kingdoms.history.menusInGame.Right.InGame_RightGoodsProvinces;
import aoh.kingdoms.history.menusInGame.Right.InGame_RightGoods;
import aoh.kingdoms.history.menusInGame.Right.InGame_RightInfrastructure;
import aoh.kingdoms.history.menusInGame.Right.InGame_RightEconomy;
import aoh.kingdoms.history.menusInGame.Right.InGame_RightWonders;
import aoh.kingdoms.history.menusInGame.Right.InGame_RightReligion;
import aoh.kingdoms.history.menusInGame.Right.InGame_RightGovernment;
import aoh.kingdoms.history.menusInGame.Right.InGame_RightPopulation;
import aoh.kingdoms.history.menusInGame.InGame_Generals;
import aoh.kingdoms.history.menusInGame.RecruitArmy.InGame_RecruitArmy_NewArmy_Battlefield;
import aoh.kingdoms.history.menusInGame.RecruitArmy.InGame_RecruitArmy_NewArmy;
import aoh.kingdoms.history.menusInGame.RecruitArmy.InGame_RecruitArmy;
import aoh.kingdoms.history.menusInGame.RecruitArmy.InGame_RecruitSameType;
import aoh.kingdoms.history.menusInGame.InGame_Encyclopedia;
import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceInfo_Army;
import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceInfoBuildings;
import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceInfo;
import aoh.kingdoms.history.mainGame.Keyboard;
import aoh.kingdoms.history.menusInGame.Battle.InGame_BattleArmyDefenders;
import aoh.kingdoms.history.menusInGame.Battle.InGame_BattleArmy;
import aoh.kingdoms.history.menusInGame.Battle.InGame_Battle;
import aoh.kingdoms.history.menusInGame.Peace.InGame_Peace;
import aoh.kingdoms.history.menusInGame.InGame_War;
import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceSiege;
import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceArmy_Move;
import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceArmyTopBar;
import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceArmy;
import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceArmy_Invasion;
import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceArmyUnits;
import aoh.kingdoms.history.menusInGame.Province.InGame_ProvinceArmy_Regroup;
import aoh.kingdoms.history.menusInGame.InGame_Audio;
import aoh.kingdoms.history.mainGame.SoundsManager;
import aoh.kingdoms.history.menu_element.button.Button;
import aoh.kingdoms.history.menu_element.MenuElement;
import aoh.kingdoms.history.mainGame.Touch;
import aoh.kingdoms.history.menu_element.Status;
import aoh.kingdoms.history.map.diplomacy.DiplomacyManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import aoh.kingdoms.history.utilities.FPS;
import aoh.kingdoms.history.mainGame.Renderer.Renderer;
import aoh.kingdoms.history.mainGame.GameValues;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Flag;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoh.kingdoms.history.menu_element.menuElementHover.MenuElement_HoverElement;
import aoh.kingdoms.history.textures.ImageManager;
import aoh.kingdoms.history.textures.Images;
import aoh.kingdoms.history.menus.Dialog;
import java.util.ArrayList;
import aoh.kingdoms.history.map.province.ProvinceHover;
import aoh.kingdoms.history.map.civilization.CivilizationRegionsManager;
import aoh.kingdoms.history.mainGame.Renderer.RendererGame;
import aoh.kingdoms.history.map.province.ProvinceDraw;
import aoh.kingdoms.history.mainGame.AA_KeyManager;
import aoh.kingdoms.history.map.province.ProvinceTouchExtraAction;
import aoh.kingdoms.history.mainGame.Game;
import aoh.kingdoms.history.menusScenarioEditor.Scenario_Calendar;
import aoh.kingdoms.history.menusScenarioEditor.ScenarioSettings;
import aoh.kingdoms.history.menusScenarioEditor.ScenarioGeneratePreview;
import aoh.kingdoms.history.menusScenarioEditor.Diplomacy.ScenarioGuaranteeList;
import aoh.kingdoms.history.menusScenarioEditor.Diplomacy.ScenarioGuarantee;
import aoh.kingdoms.history.menusScenarioEditor.Diplomacy.ScenarioDefensiveList;
import aoh.kingdoms.history.menusScenarioEditor.Diplomacy.ScenarioDefensive;
import aoh.kingdoms.history.menusScenarioEditor.ScenarioCreateAllianceList;
import aoh.kingdoms.history.menusScenarioEditor.ScenarioCreateAlliance;
import aoh.kingdoms.history.menusScenarioEditor.ScenarioCreateAlliance_AlliancesList;
import aoh.kingdoms.history.menusScenarioEditor.ScenarioCreateAlliance_Alliances;
import aoh.kingdoms.history.menusScenarioEditor.Diplomacy.ScenarioMilitaryAccessList;
import aoh.kingdoms.history.menusScenarioEditor.Diplomacy.ScenarioMilitaryAccess;
import aoh.kingdoms.history.menusScenarioEditor.Diplomacy.ScenarioNonAggressionList;
import aoh.kingdoms.history.menusScenarioEditor.Diplomacy.ScenarioNonAggression;
import aoh.kingdoms.history.menusScenarioEditor.Diplomacy.ScenarioDeclareWarList;
import aoh.kingdoms.history.menusScenarioEditor.Diplomacy.ScenarioDeclareWar;
import aoh.kingdoms.history.menusScenarioEditor.Diplomacy.ScenarioTrucesList;
import aoh.kingdoms.history.menusScenarioEditor.Diplomacy.ScenarioTruces;
import aoh.kingdoms.history.menusScenarioEditor.Diplomacy.ScenarioVassalsList;
import aoh.kingdoms.history.menusScenarioEditor.Diplomacy.ScenarioVassals;
import aoh.kingdoms.history.menusScenarioEditor.Diplomacy.ScenarioAlliancesList;
import aoh.kingdoms.history.menusScenarioEditor.Diplomacy.ScenarioAlliances;
import aoh.kingdoms.history.menusScenarioEditor.Diplomacy.ScenarioRelationsList;
import aoh.kingdoms.history.menusScenarioEditor.Diplomacy.ScenarioRelations;
import aoh.kingdoms.history.menusScenarioEditor.ScenarioReligion_List;
import aoh.kingdoms.history.menusScenarioEditor.ScenarioReligion;
import aoh.kingdoms.history.menusScenarioEditor.ScenarioCores_InProvinces;
import aoh.kingdoms.history.menusScenarioEditor.ScenarioCores_List;
import aoh.kingdoms.history.menusScenarioEditor.ScenarioCores;
import aoh.kingdoms.history.menusScenarioEditor.ScenarioBuildings_List;
import aoh.kingdoms.history.menusScenarioEditor.ScenarioBuildings;
import aoh.kingdoms.history.menusScenarioEditor.ScenarioGovernment_List;
import aoh.kingdoms.history.menusScenarioEditor.ScenarioGovernment;
import aoh.kingdoms.history.menusScenarioEditor.ScenarioEconomy_List;
import aoh.kingdoms.history.menusScenarioEditor.ScenarioEconomy;
import aoh.kingdoms.history.menusScenarioEditor.ScenarioPopulation_List;
import aoh.kingdoms.history.menusScenarioEditor.ScenarioPopulation;
import aoh.kingdoms.history.menusScenarioEditor.ScenarioArmies_List;
import aoh.kingdoms.history.menusScenarioEditor.ScenarioArmies;
import aoh.kingdoms.history.menusScenarioEditor.Technology.ScenarioTechnologyCivs_List;
import aoh.kingdoms.history.menusScenarioEditor.Technology.ScenarioTechnologyCivs;
import aoh.kingdoms.history.menusScenarioEditor.Technology.ScenarioTechnology_Default_List;
import aoh.kingdoms.history.menusScenarioEditor.Technology.ScenarioTechnology_Default;
import aoh.kingdoms.history.menusScenarioEditor.ScenarioAssign_InGameCivs;
import aoh.kingdoms.history.menusScenarioEditor.ScenarioAssign_InGameList;
import aoh.kingdoms.history.menusScenarioEditor.ScenarioAssign_InGame;
import aoh.kingdoms.history.menusScenarioEditor.ScenarioAssignCivs;
import aoh.kingdoms.history.menusScenarioEditor.ScenarioAssignList;
import aoh.kingdoms.history.menusScenarioEditor.ScenarioAssign;
import aoh.kingdoms.history.map.province.ProvinceBorderManager;
import aoh.kingdoms.history.menusScenarioEditor.ScenarioCivilizationsList;
import aoh.kingdoms.history.menusScenarioEditor.ScenarioCivilizations;
import aoh.kingdoms.history.menusScenarioEditor.Wasteland.ScenarioWastelandOptions;
import aoh.kingdoms.history.menusScenarioEditor.Wasteland.ScenarioWastelandContinentsList;
import aoh.kingdoms.history.menusScenarioEditor.Wasteland.ScenarioWastelandContinents;
import aoh.kingdoms.history.menusScenarioEditor.Wasteland.ScenarioWasteland;
import aoh.kingdoms.history.menusScenarioEditor.ScenariosList;
import aoh.kingdoms.history.menusMapEditor.EditorMapOptimizationRegions;
import aoh.kingdoms.history.menusMapEditor.EditorMapGeoRegionsList;
import aoh.kingdoms.history.menusMapEditor.EditorMapGeoRegions;
import aoh.kingdoms.history.menusMapEditor.EditorMapContinentsList;
import aoh.kingdoms.history.menusMapEditor.EditorMapContinents;
import aoh.kingdoms.history.menusMapEditor.EditorSelectProvinces;
import aoh.kingdoms.history.menusMapEditor.EditorMapResourcesList;
import aoh.kingdoms.history.menusMapEditor.EditorMapResources;
import aoh.kingdoms.history.menusMapEditor.EditorMapTerrainTypeList;
import aoh.kingdoms.history.menusMapEditor.EditorMapTerrainType;
import aoh.kingdoms.history.menusMapEditor.EditorMapPortPosition;
import aoh.kingdoms.history.menusMapEditor.EditorMap_PrintMap;
import aoh.kingdoms.history.menusMapEditor.EditorMap_GenerateSuggestedCivilizations;
import aoh.kingdoms.history.menusMapEditor.EditorMapLinesWaves;
import aoh.kingdoms.history.menusMapEditor.EditorMapLines;
import aoh.kingdoms.history.menusMapEditor.EditorMapProvinceNamePoints;
import aoh.kingdoms.history.menusMapEditor.EditorMapArmyPosition;
import aoh.kingdoms.history.menusMapEditor.EditorMapSeaProvinces;
import aoh.kingdoms.history.menusMapEditor.EditorMapEconomy_Random;
import aoh.kingdoms.history.menusMapEditor.EditorMapEconomy;
import aoh.kingdoms.history.menusMapEditor.EditorMap_CivSelect;
import aoh.kingdoms.history.menusMapEditor.EditorMap_FormableCivFormable;
import aoh.kingdoms.history.menusMapEditor.EditorMap_FormableCivClaimants;
import aoh.kingdoms.history.menusMapEditor.EditorMap_FormableCiv;
import aoh.kingdoms.history.menusMapEditor.EditorMap_FormableCivs;
import aoh.kingdoms.history.menusMapEditor.EditorMapGrowthRate_Random;
import aoh.kingdoms.history.menusMapEditor.EditorMapGrowthRate;
import aoh.kingdoms.history.menusMapEditor.EditorMapProvinceConnectionsList;
import aoh.kingdoms.history.menusMapEditor.EditorMapProvinceConnections;
import aoh.kingdoms.history.menusMapEditor.EditorMapEdit;
import aoh.kingdoms.history.menusMapEditor.EditorMaps;
import aoh.kingdoms.history.menusEditor.GameCivsEditGroup;
import aoh.kingdoms.history.menusEditor.GameCivsEditReligion;
import aoh.kingdoms.history.menusEditor.GameCivsEdit;
import aoh.kingdoms.history.menusEditor.CreateCiv_Flag;
import aoh.kingdoms.history.menusEditor.CreateCivGroup;
import aoh.kingdoms.history.menusEditor.CreateCivReligion;
import aoh.kingdoms.history.menusEditor.CreateCiv;
import aoh.kingdoms.history.menusEditor.GameCivsAlphabet;
import aoh.kingdoms.history.menusEditor.GameCivs;
import aoh.kingdoms.history.menusEditor.Editor;
import aoh.kingdoms.history.menusInGame.Battle.InGame_Battle_FullEmpty;
import aoh.kingdoms.history.menusInGame.Technology.InGame_TechnologyTreeEmpty;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions2;
import aoh.kingdoms.history.menusInGame.Court.InGame_CourtOptions;
import aoh.kingdoms.history.menusInGame.InGame_Notifications;
import aoh.kingdoms.history.menusInGame.Right.InGame_RightQueue;
import aoh.kingdoms.history.menusInGame.Right.InGame_Right;
import aoh.kingdoms.history.menusInGame.InGame_Disband;
import aoh.kingdoms.history.menusInGame.InGame_ReorganizeUnits;
import aoh.kingdoms.history.menusInGame.Goods.InGame_GoodsEmpty;
import aoh.kingdoms.history.menusInGame.InGame_EscapeEmpty;
import aoh.kingdoms.history.menusInGame.InGame_Escape;
import aoh.kingdoms.history.menusInGame.InGame_MapModes;
import aoh.kingdoms.history.menusInGame.InGame;
import aoh.kingdoms.history.menusInGame.InGame_HideUI;
import aoh.kingdoms.history.menusInGame.InGame_LegaciesEmpty;
import aoh.kingdoms.history.menusInGame.InGame_Legacies;
import aoh.kingdoms.history.menus.Settings.Settings_UIScale;
import aoh.kingdoms.history.menus.Settings.Settings_Resolution;
import aoh.kingdoms.history.menus.EmptyMenu;
import aoh.kingdoms.history.menus.Settings.Settings_Menu;
import aoh.kingdoms.history.menus.NewGame.Scenarios.Scenarios_Campaign_Buttons;
import aoh.kingdoms.history.menus.NewGame.Scenarios.Scenarios_Campaign_VerticalScenarios;
import aoh.kingdoms.history.menus.NewGame.Scenarios.Scenarios_Campaign_Vertical;
import aoh.kingdoms.history.menus.NewGame.Scenarios.ScenariosList_NewGame_Buttons;
import aoh.kingdoms.history.menus.NewGame.Scenarios.ScenariosList_NewGame;
import aoh.kingdoms.history.menus.NewGame.Scenarios.Scenarios;
import aoh.kingdoms.history.menusEditor.ManageMods;
import aoh.kingdoms.history.menus.WorkshopMenu;
import aoh.kingdoms.history.menus.CloudsMenu;
import aoh.kingdoms.history.menusInGame.InGame_GameLost;
import aoh.kingdoms.history.menus.NewGame.NewGameFlags;
import aoh.kingdoms.history.menus.NewGame.NewGame_Settings;
import aoh.kingdoms.history.menus.NewGame.NewGameCiv;
import aoh.kingdoms.history.menus.NewGame.NewGame;
import aoh.kingdoms.history.menus.Menu_LoadGames_List;
import aoh.kingdoms.history.menus.MainMenu_StatsEmpty;
import aoh.kingdoms.history.menus.MainMenu_Stats;
import aoh.kingdoms.history.menus.MainMenu;
import aoh.kingdoms.history.menus.LoadSave.Menu_Load_Workshop;
import aoh.kingdoms.history.menus.LoadSave.Menu_LoadSavedGame;
import aoh.kingdoms.history.menus.LoadSave.Menu_LoadSavingGame;
import aoh.kingdoms.history.menus.LoadSave.Menu_LoadSaveScenario;
import aoh.kingdoms.history.menus.LoadSave.Menu_LoadScenario;
import aoh.kingdoms.history.menus.Init_SelectLanguage;
import aoh.kingdoms.history.menus.Init_SelectMap2;
import aoh.kingdoms.history.menus.Init_SelectMap;
import aoh.kingdoms.history.mainGame.CFG;
import aoh.kingdoms.history.menus.InitGame;
import aoh.kingdoms.history.mainGame.Renderer.SparksAnimation;
import aoh.kingdoms.history.menu_element.Toast;
import java.util.List;

public class MenuManager
{
    private List<List<Menu>> menus;
    private List<List<Integer>> orderOfMenu;
    public Menu dialogMenu;
    private List<Toast> lToast;
    private int iToastSize;
    private int viewID;
    private ColorPicker colorPicker;
    public static SparksAnimation sparksAnimation;
    public static SparksAnimation sparksAnimationHover;
    public static SparksAnimation sparksAnimationSidebar;
    public static SparksAnimation sparksAnimationSidebarActive;
    public int INIT_GAME_MENU;
    public int INIT_GAME_MENU_SELECT_MAP;
    public int INIT_GAME_MENU_LANGUAGE;
    public int MAINMENU;
    public int MAINMENU_STATS;
    public int LOAD_GAMES_LIST;
    public int CLOUDS_MENU;
    public int IN_GAME;
    public int IN_GAME_MAPMODES;
    public int IN_GAME_PROVINCE_ARMY;
    public int IN_GAME_PROVINCE_ARMY_UNITS;
    public int IN_GAME_PROVINCE_ARMY_TOP_BAR;
    public int IN_GAME_PROVINCE_ARMY_MOVE;
    public int IN_GAME_ESCAPE;
    public int IN_GAME_ESCAPE_EMPTY;
    public int IN_GAME_RECRUIT_ARMY;
    public int IN_GAME_RECRUIT_ARMY_BATTLEFIELD;
    public int IN_GAME_GENERALS;
    public int IN_GAME_GENERAL_RECRUIT;
    public int IN_GAME_BUILDINGS;
    public int IN_GAME_BUILDINGS_GROUP_0;
    public int IN_GAME_BUILDINGS_GROUP_1;
    public int IN_GAME_BUILDINGS_GROUP_2;
    public int IN_GAME_BUILDINGS_GROUP_3;
    public int IN_GAME_ARMIES;
    public int IN_GAME_CIV;
    public int IN_GAME_COURT;
    public int IN_GAME_COURT_OPTIONS;
    public int IN_GAME_COURT_OPTIONS2;
    public int IN_GAME_RIGHT;
    public int IN_GAME_RIGHT_QUEUE;
    public int IN_GAME_NOTIFICATION;
    public int IN_GAME_ADVISOR_RECRUIT;
    public int IN_GAME_CHOOSE_TECHNOLOGY;
    public int IN_GAME_NUKES;
    public int IN_GAME_TECHNOLOGY_TREE;
    public int IN_GAME_TECHNOLOGY_TREE_EMPTY;
    public int IN_GAME_BATTLE_FULL;
    public int IN_GAME_BATTLE_FULL_EMPTY;
    public int IN_GAME_BUDGET;
    public int IN_GAME_TAKE_LOAN;
    public int IN_GAME_TAKE_LOAN_REPAY;
    public int IN_GAME_EVENT;
    public int IN_GAME_CURRENT_SITUATION;
    public int IN_GAME_MESSAGES;
    public int IN_GAME_WARS;
    public int IN_GAME_CONSOLE;
    public int IN_GAME_SAVE_GAME;
    public int IN_GAME_POP_UP;
    public int IN_GAME_CIV_BONUSES;
    public int IN_GAME_PROVINCE_BONUSES;
    public int IN_GAME_GOODS_CIV;
    public int IN_GAME_GOODS;
    public int IN_GAME_GOODS_EMPTY;
    public int IN_GAME_WONDER;
    public int IN_GAME_LEGACIES;
    public int IN_GAME_LEGACIES_EMPTY;
    public int IN_GAME_LEGACIES_INFO;
    public int IN_GAME_HIDE_UI;
    public int IN_GAME_INFO;
    public int IN_GAME_PROVINCE_INFO;
    public int IN_GAME_PROVINCE_INFO_BUILDINGS;
    public int IN_GAME_PROVINCE_INFO_ARMY;
    public int IN_GAME_REORGANIZE_UNITS;
    public int IN_GAME_DISBAND_UNITS;
    public int IN_GAME_BATTLE;
    public int IN_GAME_BATTLE_ARMY;
    public int IN_GAME_BATTLE_ARMY_DEFENDERS;
    public int IN_GAME_SIEGE;
    public int IN_GAME_WAR;
    public int IN_GAME_PEACE;
    public int NEW_GAME;
    public int NEW_GAME_CIV;
    public int NEW_GAME_SETTINGS;
    public int NEW_GAME_FLAGS;
    public int SCENARIOS;
    public int SCENARIOS_CAMPAIGN;
    public int GAME_LOST;
    public int EDITOR;
    public int EDITOR_GAMECIVS;
    public int EDITOR_GAMECIVS_EDIT;
    public int EDITOR_GAMECIVS_EDIT_RELIGION;
    public int EDITOR_GAMECIVS_EDIT_GROUP;
    public int CREATE_CIV;
    public int CREATE_CIV_RELIGION;
    public int CREATE_CIV_GROUP;
    public int CREATE_CIV_FLAG;
    public int EDITOR_MAPS;
    public int EDITOR_MAPS_EDIT_PROVINCE_CONNECTIONS;
    public int EDITOR_MAPS_EDIT;
    public int EDITOR_MAPS_EDIT_SEA_PROVINCES;
    public int EDITOR_MAPS_EDIT_ARMY_POSITION;
    public int EDITOR_MAPS_EDIT_PORT_POSITION;
    public int EDITOR_MAPS_EDIT_TERRAIN;
    public int EDITOR_MAPS_EDIT_RESOURCE;
    public int EDITOR_SELECT_PROVINCES;
    public int EDITOR_MAPS_EDIT_CONTINENTS;
    public int EDITOR_MAPS_EDIT_GEO_REGION;
    public int EDITOR_MAPS_EDIT_OPTIMIZATION_REGIONS;
    public int EDITOR_MAPS_EDIT_GROWTH_RATE;
    public int EDITOR_MAPS_EDIT_FORMABLE_CIVS;
    public int EDITOR_MAPS_EDIT_FORMABLE_CIV;
    public int EDITOR_MAPS_EDIT_CIV_SELECT;
    public int EDITOR_MAPS_EDIT_ECONOMY;
    public int EDITOR_MAPS_EDIT_PROVINCE_NAMES;
    public int EDITOR_MAPS_EDIT_LINES;
    public int EDITOR_MAPS_EDIT_WAVES;
    public int EDITOR_MAPS_EDIT_SUGGESTED_CIVILIZATIONS;
    public int PRINT_A_MAP;
    public int SETTINGS;
    public int SETTINGS_RESOLUTION;
    public int SETTINGS_UI;
    public int EDITOR_SCENARIOS_LIST;
    public int SCENARIO_WASTELAND_CONTINENTS;
    public int SCENARIO_WASTELAND;
    public int SCENARIO_CIVILIZATIONS;
    public int SCENARIO_CIVILIZATIONS_LIST;
    public int SCENARIO_ASSIGN;
    public int SCENARIO_ASSIGN_CIVS;
    public int SCENARIO_TECHNOLOGIES;
    public int SCENARIO_TECHNOLOGIES_CIVS;
    public int SCENARIO_SETTINGS;
    public int SCENARIO_CALENDAR;
    public int SCENARIO_ARMIES;
    public int SCENARIO_POPULATION;
    public int SCENARIO_ECONOMY;
    public int SCENARIO_GOVERNMENT;
    public int SCENARIO_BUILDINGS;
    public int SCENARIO_CORES;
    public int SCENARIO_CORES_LIST;
    public int SCENARIO_CORES_LIST_IN_PROVINCE;
    public int SCENARIO_RELIGION;
    public int SCENARIO_RELIGION_LIST;
    public int SCENARIO_ASSIGN_IN_GAME;
    public int SCENARIO_ASSIGN_IN_GAME_CIVS;
    public int SCENARIO_PREVIEW;
    public int SCENARIO_RELATIONS;
    public int SCENARIO_VASSALS;
    public int SCENARIO_TRUCES;
    public int SCENARIO_DECLARE_WAR;
    public int SCENARIO_ALLIANCES;
    public int SCENARIO_NON_AGGRESSION;
    public int SCENARIO_GUARANTEE;
    public int SCENARIO_DEFENSIVE;
    public int SCENARIO_MILITARY_ACCESS;
    public int SCENARIO_CREATE_ALLIANCE;
    public int SCENARIO_CREATE_ALLIANCE_EDIT;
    public int WORKSHOP;
    public int MANAGE_MODS;
    public int LOAD_STUFF;
    private int activeSliderMenuID;
    private int activeMenuElementID;
    private boolean menu_MoveInnerElements;
    private int iSliderMenuStartPosY;
    private int iSliderMenuActionDownPosY;
    private boolean updateSliderMenuPosY;
    private int iSliderMenuStartPosX;
    private int iSliderMenuActionDownPosX;
    private boolean updateSliderMenuPosX;
    private boolean menu_MoveByTitleMode;
    private boolean menuResizeMode;
    private boolean menuResizeLEFT;
    private boolean closeMenuMode;
    private boolean colorPickerMode;
    private boolean pieChartMode;
    private boolean textScrollableMode;
    private boolean sliderMode;
    public boolean graphVertical_ScrollMode_Y;
    public boolean graphVertical_ScrollMode_X;
    private int fromViewID;
    private int toViewID;
    private boolean backAnimation;
    private float animationChangeViewPosX;
    private int animationStepID;
    public static boolean orderOfMenuInGame;
    public int ANIMATION_TIME;
    public long TOAST_TIME;
    public int WAR_ANIMATION_TIME;
    public long WAR_TIME;
    public static List<ClickAnimation> clickAnimations;
    public static int clickAnimationsSize;
    public static boolean mapEditorDrawProvinces;
    public boolean inCreateNewArmy;
    public static boolean currentSituationMode;
    public static int IN_GAME_POP_UP_MENU_ID;
    public static int IN_GAME_POP_UP_MENU_FORM_CIV;
    public static int IN_GAME_POP_UP_MENU_ID_DECLARE_WAR;
    
    public final int getViewID(final View eView) {
        try {
            switch (eView) {
                case INIT_GAME_MENU: {
                    if (this.INIT_GAME_MENU == -1) {
                        this.INIT_GAME_MENU = this.addMenu(new InitGame());
                    }
                    return this.INIT_GAME_MENU;
                }
                case INIT_GAME_MENU_SELECT_MAP: {
                    if (this.INIT_GAME_MENU_SELECT_MAP == -1) {
                        if (CFG.isDesktop()) {
                            this.INIT_GAME_MENU_SELECT_MAP = this.addMenu(new Init_SelectMap());
                        }
                        else {
                            this.INIT_GAME_MENU_SELECT_MAP = this.addMenu(new Init_SelectMap2());
                        }
                    }
                    return this.INIT_GAME_MENU_SELECT_MAP;
                }
                case INIT_GAME_MENU_LANGUAGE: {
                    if (this.INIT_GAME_MENU_LANGUAGE == -1) {
                        this.INIT_GAME_MENU_LANGUAGE = this.addMenu(new Init_SelectLanguage());
                    }
                    return this.INIT_GAME_MENU_LANGUAGE;
                }
                case LOAD_SCENARIO: {
                    if (this.LOAD_STUFF == -1) {
                        this.LOAD_STUFF = this.addMenu(new Menu_LoadScenario());
                    }
                    else {
                        this.menus.get(this.LOAD_STUFF).set(0, (Object)new Menu_LoadScenario());
                    }
                    return this.LOAD_STUFF;
                }
                case LOAD_SAVE_SCENARIO: {
                    if (this.LOAD_STUFF == -1) {
                        this.LOAD_STUFF = this.addMenu(new Menu_LoadSaveScenario());
                    }
                    else {
                        this.menus.get(this.LOAD_STUFF).set(0, (Object)new Menu_LoadSaveScenario());
                    }
                    return this.LOAD_STUFF;
                }
                case LOAD_SAVE_GAME: {
                    if (this.LOAD_STUFF == -1) {
                        this.LOAD_STUFF = this.addMenu(new Menu_LoadSavingGame());
                    }
                    else {
                        this.menus.get(this.LOAD_STUFF).set(0, (Object)new Menu_LoadSavingGame());
                    }
                    return this.LOAD_STUFF;
                }
                case LOAD_SAVED_GAME: {
                    if (this.LOAD_STUFF == -1) {
                        this.LOAD_STUFF = this.addMenu(new Menu_LoadSavedGame());
                    }
                    else {
                        this.menus.get(this.LOAD_STUFF).set(0, (Object)new Menu_LoadSavedGame());
                    }
                    return this.LOAD_STUFF;
                }
                case LOAD_WORKSHOP_PUBLISH: {
                    if (this.LOAD_STUFF == -1) {
                        this.LOAD_STUFF = this.addMenu(new Menu_Load_Workshop());
                    }
                    else {
                        this.menus.get(this.LOAD_STUFF).set(0, (Object)new Menu_Load_Workshop());
                    }
                    return this.LOAD_STUFF;
                }
                case MAINMENU: {
                    if (this.MAINMENU == -1) {
                        this.MAINMENU = this.addMenu(new MainMenu());
                    }
                    else {
                        this.menus.get(this.MAINMENU).get(0).setVisible(false);
                        this.menus.get(this.MAINMENU).set(0, (Object)new MainMenu());
                    }
                    return this.MAINMENU;
                }
                case MAINMENU_STATS: {
                    if (this.MAINMENU_STATS == -1) {
                        this.addNextMenuToView(this.MAINMENU_STATS = this.addMenu(new MainMenu_Stats()), new MainMenu_StatsEmpty());
                    }
                    else {
                        this.menus.get(this.MAINMENU_STATS).get(0).setVisible(false);
                        this.menus.get(this.MAINMENU_STATS).set(0, (Object)new MainMenu_Stats());
                    }
                    return this.MAINMENU_STATS;
                }
                case LOAD_GAMES_LIST: {
                    if (this.LOAD_GAMES_LIST == -1) {
                        this.LOAD_GAMES_LIST = this.addMenu(new Menu_LoadGames_List());
                    }
                    else {
                        this.menus.get(this.LOAD_GAMES_LIST).get(0).setVisible(false);
                        this.menus.get(this.LOAD_GAMES_LIST).set(0, (Object)new Menu_LoadGames_List());
                    }
                    return this.LOAD_GAMES_LIST;
                }
                case NEW_GAME: {
                    if (this.NEW_GAME == -1) {
                        this.NEW_GAME = this.addMenu(new NewGame());
                        this.NEW_GAME_CIV = this.addNextMenuToView(this.NEW_GAME, new NewGameCiv());
                        this.NEW_GAME_SETTINGS = this.addNextMenuToView(this.NEW_GAME, new NewGame_Settings());
                        this.NEW_GAME_FLAGS = this.addNextMenuToView(this.NEW_GAME, new NewGameFlags());
                    }
                    else {
                        NewGame.lTime = CFG.currentTimeMillis;
                        this.rebuildNewGameCiv();
                        this.menus.get(this.NEW_GAME).set(this.NEW_GAME_FLAGS, (Object)new NewGameFlags());
                    }
                    return this.NEW_GAME;
                }
                case GAME_LOST: {
                    if (this.GAME_LOST == -1) {
                        this.GAME_LOST = this.addMenu(new InGame_GameLost());
                    }
                    else {
                        this.menus.get(this.GAME_LOST).set(0, (Object)new InGame_GameLost());
                    }
                    return this.GAME_LOST;
                }
                case CLOUDS_MENU: {
                    if (this.CLOUDS_MENU == -1) {
                        this.CLOUDS_MENU = this.addMenu(new CloudsMenu());
                    }
                    else {
                        this.menus.get(this.CLOUDS_MENU).set(0, (Object)new CloudsMenu());
                    }
                    return this.CLOUDS_MENU;
                }
                case WORKSHOP: {
                    if (this.WORKSHOP == -1) {
                        this.WORKSHOP = this.addMenu(new WorkshopMenu());
                    }
                    else {
                        this.menus.get(this.WORKSHOP).set(0, (Object)new WorkshopMenu());
                    }
                    return this.WORKSHOP;
                }
                case MANAGE_MODS: {
                    if (this.MANAGE_MODS == -1) {
                        this.MANAGE_MODS = this.addMenu(new ManageMods());
                    }
                    else {
                        this.menus.get(this.MANAGE_MODS).set(0, (Object)new ManageMods());
                    }
                    return this.MANAGE_MODS;
                }
                case SCENARIOS: {
                    if (this.SCENARIOS == -1) {
                        this.SCENARIOS = this.addMenu(new Scenarios());
                        if (Scenarios.SCENARIOS_DEFAULT_MODE) {
                            this.addNextMenuToView(this.SCENARIOS, new ScenariosList_NewGame());
                        }
                        else {
                            this.addNextMenuToView(this.SCENARIOS, new ScenariosList_NewGame_Buttons());
                        }
                    }
                    else {
                        this.menus.get(this.SCENARIOS).set(0, (Object)new Scenarios());
                        if (Scenarios.SCENARIOS_DEFAULT_MODE) {
                            this.menus.get(this.SCENARIOS).set(1, (Object)new ScenariosList_NewGame());
                        }
                        else {
                            this.menus.get(this.SCENARIOS).set(1, (Object)new ScenariosList_NewGame_Buttons());
                        }
                    }
                    return this.SCENARIOS;
                }
                case SCENARIOS_CAMPAIGN: {
                    if (this.SCENARIOS_CAMPAIGN == -1) {
                        this.SCENARIOS_CAMPAIGN = this.addMenu(new Scenarios_Campaign_Vertical());
                        if (Scenarios.SCENARIOS_DEFAULT_MODE) {
                            this.addNextMenuToView(this.SCENARIOS_CAMPAIGN, new Scenarios_Campaign_VerticalScenarios());
                        }
                        else {
                            this.addNextMenuToView(this.SCENARIOS_CAMPAIGN, new Scenarios_Campaign_Buttons());
                        }
                    }
                    else {
                        this.menus.get(this.SCENARIOS_CAMPAIGN).set(0, (Object)new Scenarios_Campaign_Vertical());
                        if (Scenarios.SCENARIOS_DEFAULT_MODE) {
                            this.menus.get(this.SCENARIOS_CAMPAIGN).set(1, (Object)new Scenarios_Campaign_VerticalScenarios());
                        }
                        else {
                            this.menus.get(this.SCENARIOS_CAMPAIGN).set(1, (Object)new Scenarios_Campaign_Buttons());
                        }
                    }
                    return this.SCENARIOS_CAMPAIGN;
                }
                case SETTINGS: {
                    if (this.SETTINGS == -1) {
                        this.addNextMenuToView(this.SETTINGS = this.addMenu(new Settings_Menu()), new EmptyMenu());
                    }
                    else {
                        this.menus.get(this.SETTINGS).set(0, (Object)new Settings_Menu());
                        this.menus.get(this.SETTINGS).set(1, (Object)new EmptyMenu());
                    }
                    return this.SETTINGS;
                }
                case SETTINGS_RESOLUTION: {
                    if (this.SETTINGS_RESOLUTION == -1) {
                        this.addNextMenuToView(this.SETTINGS_RESOLUTION = this.addMenu(new Settings_Resolution()), new EmptyMenu());
                    }
                    else {
                        this.menus.get(this.SETTINGS_RESOLUTION).set(0, (Object)new Settings_Resolution());
                        this.menus.get(this.SETTINGS_RESOLUTION).set(1, (Object)new EmptyMenu());
                    }
                    return this.SETTINGS_RESOLUTION;
                }
                case SETTINGS_UI: {
                    if (this.SETTINGS_UI == -1) {
                        this.addNextMenuToView(this.SETTINGS_UI = this.addMenu(new Settings_UIScale()), new EmptyMenu());
                    }
                    else {
                        this.menus.get(this.SETTINGS_UI).set(0, (Object)new Settings_UIScale());
                        this.menus.get(this.SETTINGS_UI).set(1, (Object)new EmptyMenu());
                    }
                    return this.SETTINGS_UI;
                }
                case IN_GAME_LEGACIES: {
                    if (this.IN_GAME_LEGACIES == -1) {
                        this.IN_GAME_LEGACIES = this.addMenu(new InGame_Legacies());
                        this.IN_GAME_LEGACIES_EMPTY = this.addNextMenuToView(this.IN_GAME_LEGACIES, new InGame_LegaciesEmpty());
                        this.IN_GAME_LEGACIES_INFO = this.addNextMenuToView(this.IN_GAME_LEGACIES, new EmptyMenu());
                    }
                    else {
                        this.rebuildInGame_Legacies();
                    }
                    return this.IN_GAME_LEGACIES;
                }
                case IN_GAME_HIDE_UI: {
                    if (this.IN_GAME_HIDE_UI == -1) {
                        this.IN_GAME_HIDE_UI = this.addMenu(new InGame_HideUI());
                    }
                    else {
                        this.menus.get(this.IN_GAME_HIDE_UI).set(0, (Object)new InGame_HideUI());
                    }
                    return this.IN_GAME_HIDE_UI;
                }
                case IN_GAME: {
                    if (this.IN_GAME == -1) {
                        this.IN_GAME = this.addMenu(new InGame());
                        this.IN_GAME_MAPMODES = this.addNextMenuToView(this.IN_GAME, new InGame_MapModes());
                        this.IN_GAME_CIV_BONUSES = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_PROVINCE_ARMY = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_PROVINCE_ARMY_UNITS = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_PROVINCE_ARMY_TOP_BAR = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_PROVINCE_ARMY_MOVE = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_ESCAPE = this.addNextMenuToView(this.IN_GAME, new InGame_Escape());
                        this.IN_GAME_ESCAPE_EMPTY = this.addNextMenuToView(this.IN_GAME, new InGame_EscapeEmpty());
                        this.IN_GAME_RECRUIT_ARMY = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_RECRUIT_ARMY_BATTLEFIELD = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_BUILDINGS = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_BUILDINGS_GROUP_0 = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_BUILDINGS_GROUP_1 = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_BUILDINGS_GROUP_2 = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_BUILDINGS_GROUP_3 = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_GOODS = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_GOODS_EMPTY = this.addNextMenuToView(this.IN_GAME, new InGame_GoodsEmpty());
                        this.IN_GAME_GOODS_CIV = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_WONDER = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_INFO = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_PROVINCE_INFO = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_PROVINCE_INFO_BUILDINGS = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_PROVINCE_INFO_ARMY = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_REORGANIZE_UNITS = this.addNextMenuToView(this.IN_GAME, new InGame_ReorganizeUnits());
                        this.IN_GAME_DISBAND_UNITS = this.addNextMenuToView(this.IN_GAME, new InGame_Disband());
                        this.IN_GAME_BATTLE = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_BATTLE_ARMY = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_BATTLE_ARMY_DEFENDERS = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_SIEGE = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_WAR = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_PEACE = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_GENERALS = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_GENERAL_RECRUIT = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_ARMIES = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_RIGHT = this.addNextMenuToView(this.IN_GAME, new InGame_Right());
                        this.IN_GAME_RIGHT_QUEUE = this.addNextMenuToView(this.IN_GAME, new InGame_RightQueue());
                        this.IN_GAME_NOTIFICATION = this.addNextMenuToView(this.IN_GAME, new InGame_Notifications());
                        this.IN_GAME_PROVINCE_BONUSES = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_CIV = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_COURT = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_COURT_OPTIONS = this.addNextMenuToView(this.IN_GAME, new InGame_CourtOptions());
                        this.IN_GAME_COURT_OPTIONS2 = this.addNextMenuToView(this.IN_GAME, new InGame_CourtOptions2());
                        this.IN_GAME_ADVISOR_RECRUIT = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_CHOOSE_TECHNOLOGY = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_TECHNOLOGY_TREE = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_TECHNOLOGY_TREE_EMPTY = this.addNextMenuToView(this.IN_GAME, new InGame_TechnologyTreeEmpty());
                        this.IN_GAME_BATTLE_FULL = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_BATTLE_FULL_EMPTY = this.addNextMenuToView(this.IN_GAME, new InGame_Battle_FullEmpty());
                        this.IN_GAME_NUKES = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_TAKE_LOAN = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_TAKE_LOAN_REPAY = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_CONSOLE = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_SAVE_GAME = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_POP_UP = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_BUDGET = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_EVENT = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_CURRENT_SITUATION = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_MESSAGES = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_WARS = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                    }
                    else {
                        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT_OPTIONS2, (Object)new InGame_CourtOptions2());
                    }
                    InGame.updateDrawOver();
                    return this.IN_GAME;
                }
                case EDITOR: {
                    if (this.EDITOR == -1) {
                        this.EDITOR = this.addMenu(new Editor());
                    }
                    return this.EDITOR;
                }
                case EDITOR_GAMECIVS: {
                    if (this.EDITOR_GAMECIVS == -1) {
                        this.addNextMenuToView(this.EDITOR_GAMECIVS = this.addMenu(new GameCivs()), new GameCivsAlphabet());
                    }
                    else {
                        this.menus.get(this.EDITOR_GAMECIVS).get(0).setVisible(false);
                        this.menus.get(this.EDITOR_GAMECIVS).set(0, (Object)new GameCivs());
                        this.menus.get(this.EDITOR_GAMECIVS).set(1, (Object)new GameCivsAlphabet());
                    }
                    return this.EDITOR_GAMECIVS;
                }
                case CREATE_CIV: {
                    if (this.CREATE_CIV == -1) {
                        this.CREATE_CIV = this.addMenu(new CreateCiv());
                        this.CREATE_CIV_RELIGION = this.addNextMenuToView(this.CREATE_CIV, new CreateCivReligion());
                        this.CREATE_CIV_GROUP = this.addNextMenuToView(this.CREATE_CIV, new CreateCivGroup());
                        this.CREATE_CIV_FLAG = this.addNextMenuToView(this.CREATE_CIV, new CreateCiv_Flag());
                    }
                    else {
                        this.menus.get(this.CREATE_CIV).set(0, (Object)new CreateCiv());
                        this.menus.get(this.CREATE_CIV).set(this.CREATE_CIV_RELIGION, (Object)new CreateCivReligion());
                        this.menus.get(this.CREATE_CIV).set(this.CREATE_CIV_GROUP, (Object)new CreateCivGroup());
                        this.menus.get(this.CREATE_CIV).set(this.CREATE_CIV_FLAG, (Object)new CreateCiv_Flag());
                    }
                    return this.CREATE_CIV;
                }
                case EDITOR_GAMECIVS_EDIT: {
                    if (this.EDITOR_GAMECIVS_EDIT == -1) {
                        this.EDITOR_GAMECIVS_EDIT = this.addMenu(new GameCivsEdit());
                        this.EDITOR_GAMECIVS_EDIT_RELIGION = this.addNextMenuToView(this.EDITOR_GAMECIVS_EDIT, new GameCivsEditReligion());
                        this.EDITOR_GAMECIVS_EDIT_GROUP = this.addNextMenuToView(this.EDITOR_GAMECIVS_EDIT, new GameCivsEditGroup());
                    }
                    else {
                        this.menus.get(this.EDITOR_GAMECIVS_EDIT).set(0, (Object)new GameCivsEdit());
                    }
                    return this.EDITOR_GAMECIVS_EDIT;
                }
                case EDITOR_MAPS: {
                    if (this.EDITOR_MAPS == -1) {
                        this.EDITOR_MAPS = this.addMenu(new EditorMaps());
                    }
                    return this.EDITOR_MAPS;
                }
                case EDITOR_MAPS_EDIT: {
                    if (this.EDITOR_MAPS_EDIT == -1) {
                        this.EDITOR_MAPS_EDIT = this.addMenu(new EditorMapEdit());
                    }
                    return this.EDITOR_MAPS_EDIT;
                }
                case EDITOR_MAPS_EDIT_PROVINCE_CONNECTIONS: {
                    if (this.EDITOR_MAPS_EDIT_PROVINCE_CONNECTIONS == -1) {
                        this.addNextMenuToView(this.EDITOR_MAPS_EDIT_PROVINCE_CONNECTIONS = this.addMenu(new EditorMapProvinceConnections()), new EditorMapProvinceConnectionsList());
                    }
                    return this.EDITOR_MAPS_EDIT_PROVINCE_CONNECTIONS;
                }
                case EDITOR_MAPS_EDIT_GROWTH_RATE: {
                    if (this.EDITOR_MAPS_EDIT_GROWTH_RATE == -1) {
                        this.addNextMenuToView(this.EDITOR_MAPS_EDIT_GROWTH_RATE = this.addMenu(new EditorMapGrowthRate()), new EditorMapGrowthRate_Random());
                    }
                    return this.EDITOR_MAPS_EDIT_GROWTH_RATE;
                }
                case EDITOR_MAPS_EDIT_FORMABLE_CIVS: {
                    if (this.EDITOR_MAPS_EDIT_FORMABLE_CIVS == -1) {
                        this.EDITOR_MAPS_EDIT_FORMABLE_CIVS = this.addMenu(new EditorMap_FormableCivs());
                    }
                    else {
                        this.menus.get(this.EDITOR_MAPS_EDIT_FORMABLE_CIVS).set(0, (Object)new EditorMap_FormableCivs());
                    }
                    return this.EDITOR_MAPS_EDIT_FORMABLE_CIVS;
                }
                case EDITOR_MAPS_EDIT_FORMABLE_CIV: {
                    if (this.EDITOR_MAPS_EDIT_FORMABLE_CIV == -1) {
                        this.addNextMenuToView(this.EDITOR_MAPS_EDIT_FORMABLE_CIV = this.addMenu(new EditorMap_FormableCiv()), new EditorMap_FormableCivClaimants());
                        this.addNextMenuToView(this.EDITOR_MAPS_EDIT_FORMABLE_CIV, new EditorMap_FormableCivFormable());
                    }
                    else {
                        this.menus.get(this.EDITOR_MAPS_EDIT_FORMABLE_CIV).set(0, (Object)new EditorMap_FormableCiv());
                        this.menus.get(this.EDITOR_MAPS_EDIT_FORMABLE_CIV).set(1, (Object)new EditorMap_FormableCivClaimants());
                        this.menus.get(this.EDITOR_MAPS_EDIT_FORMABLE_CIV).set(2, (Object)new EditorMap_FormableCivFormable());
                    }
                    return this.EDITOR_MAPS_EDIT_FORMABLE_CIV;
                }
                case EDITOR_MAPS_EDIT_CIV_SELECT: {
                    if (this.EDITOR_MAPS_EDIT_CIV_SELECT == -1) {
                        this.EDITOR_MAPS_EDIT_CIV_SELECT = this.addMenu(new EditorMap_CivSelect());
                    }
                    else {
                        this.menus.get(this.EDITOR_MAPS_EDIT_CIV_SELECT).set(0, (Object)new EditorMap_CivSelect());
                    }
                    return this.EDITOR_MAPS_EDIT_CIV_SELECT;
                }
                case EDITOR_MAPS_EDIT_ECONOMY: {
                    if (this.EDITOR_MAPS_EDIT_ECONOMY == -1) {
                        this.addNextMenuToView(this.EDITOR_MAPS_EDIT_ECONOMY = this.addMenu(new EditorMapEconomy()), new EditorMapEconomy_Random());
                    }
                    return this.EDITOR_MAPS_EDIT_ECONOMY;
                }
                case EDITOR_MAPS_EDIT_SEA_PROVINCES: {
                    if (this.EDITOR_MAPS_EDIT_SEA_PROVINCES == -1) {
                        this.EDITOR_MAPS_EDIT_SEA_PROVINCES = this.addMenu(new EditorMapSeaProvinces());
                    }
                    return this.EDITOR_MAPS_EDIT_SEA_PROVINCES;
                }
                case EDITOR_MAPS_EDIT_ARMY_POSITION: {
                    if (this.EDITOR_MAPS_EDIT_ARMY_POSITION == -1) {
                        this.EDITOR_MAPS_EDIT_ARMY_POSITION = this.addMenu(new EditorMapArmyPosition());
                    }
                    return this.EDITOR_MAPS_EDIT_ARMY_POSITION;
                }
                case EDITOR_MAPS_EDIT_PROVINCE_NAMES: {
                    if (this.EDITOR_MAPS_EDIT_PROVINCE_NAMES == -1) {
                        this.EDITOR_MAPS_EDIT_PROVINCE_NAMES = this.addMenu(new EditorMapProvinceNamePoints());
                    }
                    return this.EDITOR_MAPS_EDIT_PROVINCE_NAMES;
                }
                case EDITOR_MAPS_EDIT_LINES: {
                    if (this.EDITOR_MAPS_EDIT_LINES == -1) {
                        this.EDITOR_MAPS_EDIT_LINES = this.addMenu(new EditorMapLines());
                    }
                    return this.EDITOR_MAPS_EDIT_LINES;
                }
                case EDITOR_MAPS_EDIT_WAVES: {
                    if (this.EDITOR_MAPS_EDIT_WAVES == -1) {
                        this.EDITOR_MAPS_EDIT_WAVES = this.addMenu(new EditorMapLinesWaves());
                    }
                    return this.EDITOR_MAPS_EDIT_WAVES;
                }
                case EDITOR_MAPS_EDIT_SUGGESTED_CIVILIZATIONS: {
                    if (this.EDITOR_MAPS_EDIT_SUGGESTED_CIVILIZATIONS == -1) {
                        this.EDITOR_MAPS_EDIT_SUGGESTED_CIVILIZATIONS = this.addMenu(new EditorMap_GenerateSuggestedCivilizations());
                    }
                    else {
                        this.menus.get(this.EDITOR_MAPS_EDIT_SUGGESTED_CIVILIZATIONS).set(0, (Object)new EditorMap_GenerateSuggestedCivilizations());
                    }
                    return this.EDITOR_MAPS_EDIT_SUGGESTED_CIVILIZATIONS;
                }
                case PRINT_A_MAP: {
                    if (this.PRINT_A_MAP == -1) {
                        this.PRINT_A_MAP = this.addMenu(new EditorMap_PrintMap());
                    }
                    else {
                        this.menus.get(this.PRINT_A_MAP).set(0, (Object)new EditorMap_PrintMap());
                    }
                    return this.PRINT_A_MAP;
                }
                case EDITOR_MAPS_EDIT_PORT_POSITION: {
                    if (this.EDITOR_MAPS_EDIT_PORT_POSITION == -1) {
                        this.EDITOR_MAPS_EDIT_PORT_POSITION = this.addMenu(new EditorMapPortPosition());
                    }
                    return this.EDITOR_MAPS_EDIT_PORT_POSITION;
                }
                case EDITOR_MAPS_EDIT_TERRAIN: {
                    if (this.EDITOR_MAPS_EDIT_TERRAIN == -1) {
                        this.addNextMenuToView(this.EDITOR_MAPS_EDIT_TERRAIN = this.addMenu(new EditorMapTerrainType()), new EditorMapTerrainTypeList());
                    }
                    return this.EDITOR_MAPS_EDIT_TERRAIN;
                }
                case EDITOR_MAPS_EDIT_RESOURCE: {
                    if (this.EDITOR_MAPS_EDIT_RESOURCE == -1) {
                        this.addNextMenuToView(this.EDITOR_MAPS_EDIT_RESOURCE = this.addMenu(new EditorMapResources()), new EditorMapResourcesList());
                    }
                    return this.EDITOR_MAPS_EDIT_RESOURCE;
                }
                case EDITOR_SELECT_PROVINCES: {
                    if (this.EDITOR_SELECT_PROVINCES == -1) {
                        this.EDITOR_SELECT_PROVINCES = this.addMenu(new EditorSelectProvinces());
                    }
                    return this.EDITOR_SELECT_PROVINCES;
                }
                case EDITOR_MAPS_EDIT_CONTINENTS: {
                    if (this.EDITOR_MAPS_EDIT_CONTINENTS == -1) {
                        this.addNextMenuToView(this.EDITOR_MAPS_EDIT_CONTINENTS = this.addMenu(new EditorMapContinents()), new EditorMapContinentsList());
                    }
                    return this.EDITOR_MAPS_EDIT_CONTINENTS;
                }
                case EDITOR_MAPS_EDIT_GEO_REGION: {
                    if (this.EDITOR_MAPS_EDIT_GEO_REGION == -1) {
                        this.addNextMenuToView(this.EDITOR_MAPS_EDIT_GEO_REGION = this.addMenu(new EditorMapGeoRegions()), new EditorMapGeoRegionsList());
                    }
                    return this.EDITOR_MAPS_EDIT_GEO_REGION;
                }
                case EDITOR_MAPS_EDIT_OPTIMIZATION_REGIONS: {
                    if (this.EDITOR_MAPS_EDIT_OPTIMIZATION_REGIONS == -1) {
                        this.EDITOR_MAPS_EDIT_OPTIMIZATION_REGIONS = this.addMenu(new EditorMapOptimizationRegions());
                    }
                    return this.EDITOR_MAPS_EDIT_OPTIMIZATION_REGIONS;
                }
                case EDITOR_SCENARIOS_LIST: {
                    if (this.EDITOR_SCENARIOS_LIST == -1) {
                        this.EDITOR_SCENARIOS_LIST = this.addMenu(new ScenariosList());
                    }
                    else {
                        this.menus.get(this.EDITOR_SCENARIOS_LIST).set(0, (Object)new ScenariosList());
                    }
                    ScenarioWasteland.lUndo.clear();
                    return this.EDITOR_SCENARIOS_LIST;
                }
                case SCENARIO_WASTELAND_CONTINENTS: {
                    if (this.SCENARIO_WASTELAND_CONTINENTS == -1) {
                        this.addNextMenuToView(this.SCENARIO_WASTELAND_CONTINENTS = this.addMenu(new ScenarioWastelandContinents()), new ScenarioWastelandContinentsList());
                    }
                    return this.SCENARIO_WASTELAND_CONTINENTS;
                }
                case SCENARIO_WASTELAND: {
                    if (this.SCENARIO_WASTELAND == -1) {
                        this.addNextMenuToView(this.SCENARIO_WASTELAND = this.addMenu(new ScenarioWasteland()), new ScenarioWastelandOptions());
                    }
                    return this.SCENARIO_WASTELAND;
                }
                case SCENARIO_CIVILIZATIONS: {
                    if (this.SCENARIO_CIVILIZATIONS == -1) {
                        this.SCENARIO_CIVILIZATIONS = this.addMenu(new ScenarioCivilizations());
                        this.SCENARIO_CIVILIZATIONS_LIST = this.addNextMenuToView(this.SCENARIO_CIVILIZATIONS, new ScenarioCivilizationsList());
                    }
                    else {
                        this.menus.get(this.SCENARIO_CIVILIZATIONS).set(this.SCENARIO_CIVILIZATIONS_LIST, (Object)new ScenarioCivilizationsList());
                    }
                    try {
                        ProvinceBorderManager.clearProvinceBorder();
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                    return this.SCENARIO_CIVILIZATIONS;
                }
                case SCENARIO_ASSIGN: {
                    if (this.SCENARIO_ASSIGN == -1) {
                        this.addNextMenuToView(this.SCENARIO_ASSIGN = this.addMenu(new ScenarioAssign()), new ScenarioAssignList());
                        this.SCENARIO_ASSIGN_CIVS = this.addNextMenuToView(this.SCENARIO_ASSIGN, new ScenarioAssignCivs());
                    }
                    else {
                        this.menus.get(this.SCENARIO_ASSIGN).set(this.SCENARIO_ASSIGN_CIVS, (Object)new ScenarioAssignCivs());
                    }
                    return this.SCENARIO_ASSIGN;
                }
                case SCENARIO_ASSIGN_IN_GAME: {
                    if (this.SCENARIO_ASSIGN_IN_GAME == -1) {
                        this.addNextMenuToView(this.SCENARIO_ASSIGN_IN_GAME = this.addMenu(new ScenarioAssign_InGame()), new ScenarioAssign_InGameList());
                        this.SCENARIO_ASSIGN_IN_GAME_CIVS = this.addNextMenuToView(this.SCENARIO_ASSIGN_IN_GAME, new ScenarioAssign_InGameCivs());
                    }
                    else {
                        this.menus.get(this.SCENARIO_ASSIGN_IN_GAME).set(this.SCENARIO_ASSIGN_IN_GAME_CIVS, (Object)new ScenarioAssign_InGameCivs());
                    }
                    return this.SCENARIO_ASSIGN_IN_GAME;
                }
                case SCENARIO_TECHNOLOGIES: {
                    if (this.SCENARIO_TECHNOLOGIES == -1) {
                        this.addNextMenuToView(this.SCENARIO_TECHNOLOGIES = this.addMenu(new ScenarioTechnology_Default()), new ScenarioTechnology_Default_List());
                    }
                    else {
                        this.menus.get(this.SCENARIO_TECHNOLOGIES).set(0, (Object)new ScenarioTechnology_Default());
                    }
                    return this.SCENARIO_TECHNOLOGIES;
                }
                case SCENARIO_TECHNOLOGIES_CIVS: {
                    if (this.SCENARIO_TECHNOLOGIES_CIVS == -1) {
                        this.addNextMenuToView(this.SCENARIO_TECHNOLOGIES_CIVS = this.addMenu(new ScenarioTechnologyCivs()), new ScenarioTechnologyCivs_List());
                    }
                    else {
                        this.menus.get(this.SCENARIO_TECHNOLOGIES_CIVS).set(0, (Object)new ScenarioTechnologyCivs());
                        this.menus.get(this.SCENARIO_TECHNOLOGIES_CIVS).set(1, (Object)new ScenarioTechnologyCivs_List());
                    }
                    return this.SCENARIO_TECHNOLOGIES_CIVS;
                }
                case SCENARIO_ARMIES: {
                    if (this.SCENARIO_ARMIES == -1) {
                        this.addNextMenuToView(this.SCENARIO_ARMIES = this.addMenu(new ScenarioArmies()), new ScenarioArmies_List());
                    }
                    return this.SCENARIO_ARMIES;
                }
                case SCENARIO_POPULATION: {
                    if (this.SCENARIO_POPULATION == -1) {
                        this.addNextMenuToView(this.SCENARIO_POPULATION = this.addMenu(new ScenarioPopulation()), new ScenarioPopulation_List());
                    }
                    else {
                        this.menus.get(this.SCENARIO_POPULATION).set(1, (Object)new ScenarioPopulation_List());
                    }
                    return this.SCENARIO_POPULATION;
                }
                case SCENARIO_ECONOMY: {
                    if (this.SCENARIO_ECONOMY == -1) {
                        this.addNextMenuToView(this.SCENARIO_ECONOMY = this.addMenu(new ScenarioEconomy()), new ScenarioEconomy_List());
                    }
                    else {
                        this.menus.get(this.SCENARIO_ECONOMY).set(0, (Object)new ScenarioEconomy());
                        this.menus.get(this.SCENARIO_ECONOMY).set(1, (Object)new ScenarioEconomy_List());
                    }
                    return this.SCENARIO_ECONOMY;
                }
                case SCENARIO_GOVERNMENT: {
                    if (this.SCENARIO_GOVERNMENT == -1) {
                        this.addNextMenuToView(this.SCENARIO_GOVERNMENT = this.addMenu(new ScenarioGovernment()), new ScenarioGovernment_List());
                    }
                    else {
                        this.menus.get(this.SCENARIO_GOVERNMENT).set(0, (Object)new ScenarioGovernment());
                        this.menus.get(this.SCENARIO_GOVERNMENT).set(1, (Object)new ScenarioGovernment_List());
                    }
                    return this.SCENARIO_GOVERNMENT;
                }
                case SCENARIO_BUILDINGS: {
                    if (this.SCENARIO_BUILDINGS == -1) {
                        this.addNextMenuToView(this.SCENARIO_BUILDINGS = this.addMenu(new ScenarioBuildings()), new ScenarioBuildings_List());
                    }
                    else {
                        this.menus.get(this.SCENARIO_BUILDINGS).set(1, (Object)new ScenarioBuildings_List());
                    }
                    return this.SCENARIO_BUILDINGS;
                }
                case SCENARIO_CORES: {
                    if (this.SCENARIO_CORES == -1) {
                        this.SCENARIO_CORES = this.addMenu(new ScenarioCores());
                        this.SCENARIO_CORES_LIST = this.addNextMenuToView(this.SCENARIO_CORES, new ScenarioCores_List());
                        this.SCENARIO_CORES_LIST_IN_PROVINCE = this.addNextMenuToView(this.SCENARIO_CORES, new ScenarioCores_InProvinces());
                    }
                    else {
                        this.menus.get(this.SCENARIO_CORES).set(this.SCENARIO_CORES_LIST, (Object)new ScenarioCores_List());
                        this.menus.get(this.SCENARIO_CORES).set(this.SCENARIO_CORES_LIST_IN_PROVINCE, (Object)new ScenarioCores_InProvinces());
                    }
                    return this.SCENARIO_CORES;
                }
                case SCENARIO_RELIGION: {
                    if (this.SCENARIO_RELIGION == -1) {
                        this.SCENARIO_RELIGION = this.addMenu(new ScenarioReligion());
                        this.SCENARIO_RELIGION_LIST = this.addNextMenuToView(this.SCENARIO_RELIGION, new ScenarioReligion_List());
                    }
                    else {
                        this.menus.get(this.SCENARIO_RELIGION).set(this.SCENARIO_RELIGION_LIST, (Object)new ScenarioReligion_List());
                    }
                    return this.SCENARIO_RELIGION;
                }
                case SCENARIO_RELATIONS: {
                    if (this.SCENARIO_RELATIONS == -1) {
                        this.addNextMenuToView(this.SCENARIO_RELATIONS = this.addMenu(new ScenarioRelations()), new ScenarioRelationsList());
                    }
                    else {
                        this.menus.get(this.SCENARIO_RELATIONS).set(1, (Object)new ScenarioRelationsList());
                    }
                    return this.SCENARIO_RELATIONS;
                }
                case SCENARIO_ALLIANCES: {
                    if (this.SCENARIO_ALLIANCES == -1) {
                        this.addNextMenuToView(this.SCENARIO_ALLIANCES = this.addMenu(new ScenarioAlliances()), new ScenarioAlliancesList());
                    }
                    else {
                        this.menus.get(this.SCENARIO_ALLIANCES).set(1, (Object)new ScenarioAlliancesList());
                    }
                    return this.SCENARIO_ALLIANCES;
                }
                case SCENARIO_VASSALS: {
                    if (this.SCENARIO_VASSALS == -1) {
                        this.addNextMenuToView(this.SCENARIO_VASSALS = this.addMenu(new ScenarioVassals()), new ScenarioVassalsList());
                    }
                    else {
                        this.menus.get(this.SCENARIO_VASSALS).set(1, (Object)new ScenarioVassalsList());
                    }
                    return this.SCENARIO_VASSALS;
                }
                case SCENARIO_TRUCES: {
                    if (this.SCENARIO_TRUCES == -1) {
                        this.addNextMenuToView(this.SCENARIO_TRUCES = this.addMenu(new ScenarioTruces()), new ScenarioTrucesList());
                    }
                    else {
                        this.menus.get(this.SCENARIO_TRUCES).set(1, (Object)new ScenarioTrucesList());
                    }
                    return this.SCENARIO_TRUCES;
                }
                case SCENARIO_DECLARE_WAR: {
                    if (this.SCENARIO_DECLARE_WAR == -1) {
                        this.addNextMenuToView(this.SCENARIO_DECLARE_WAR = this.addMenu(new ScenarioDeclareWar()), new ScenarioDeclareWarList());
                    }
                    else {
                        this.menus.get(this.SCENARIO_DECLARE_WAR).set(1, (Object)new ScenarioDeclareWarList());
                    }
                    return this.SCENARIO_DECLARE_WAR;
                }
                case SCENARIO_NON_AGGRESSION: {
                    if (this.SCENARIO_NON_AGGRESSION == -1) {
                        this.addNextMenuToView(this.SCENARIO_NON_AGGRESSION = this.addMenu(new ScenarioNonAggression()), new ScenarioNonAggressionList());
                    }
                    else {
                        this.menus.get(this.SCENARIO_NON_AGGRESSION).set(1, (Object)new ScenarioNonAggressionList());
                    }
                    return this.SCENARIO_NON_AGGRESSION;
                }
                case SCENARIO_MILITARY_ACCESS: {
                    if (this.SCENARIO_MILITARY_ACCESS == -1) {
                        this.addNextMenuToView(this.SCENARIO_MILITARY_ACCESS = this.addMenu(new ScenarioMilitaryAccess()), new ScenarioMilitaryAccessList());
                    }
                    else {
                        this.menus.get(this.SCENARIO_MILITARY_ACCESS).set(1, (Object)new ScenarioMilitaryAccessList());
                    }
                    return this.SCENARIO_MILITARY_ACCESS;
                }
                case SCENARIO_CREATE_ALLIANCE: {
                    if (this.SCENARIO_CREATE_ALLIANCE == -1) {
                        this.addNextMenuToView(this.SCENARIO_CREATE_ALLIANCE = this.addMenu(new ScenarioCreateAlliance_Alliances()), new ScenarioCreateAlliance_AlliancesList());
                    }
                    else {
                        this.menus.get(this.SCENARIO_CREATE_ALLIANCE).set(1, (Object)new ScenarioCreateAlliance_AlliancesList());
                    }
                    return this.SCENARIO_CREATE_ALLIANCE;
                }
                case SCENARIO_CREATE_ALLIANCE_EDIT: {
                    if (this.SCENARIO_CREATE_ALLIANCE_EDIT == -1) {
                        this.addNextMenuToView(this.SCENARIO_CREATE_ALLIANCE_EDIT = this.addMenu(new ScenarioCreateAlliance()), new ScenarioCreateAllianceList());
                    }
                    else {
                        this.menus.get(this.SCENARIO_CREATE_ALLIANCE_EDIT).set(1, (Object)new ScenarioCreateAllianceList());
                    }
                    return this.SCENARIO_CREATE_ALLIANCE_EDIT;
                }
                case SCENARIO_DEFENSIVE: {
                    if (this.SCENARIO_DEFENSIVE == -1) {
                        this.addNextMenuToView(this.SCENARIO_DEFENSIVE = this.addMenu(new ScenarioDefensive()), new ScenarioDefensiveList());
                    }
                    else {
                        this.menus.get(this.SCENARIO_DEFENSIVE).set(1, (Object)new ScenarioDefensiveList());
                    }
                    return this.SCENARIO_DEFENSIVE;
                }
                case SCENARIO_GUARANTEE: {
                    if (this.SCENARIO_GUARANTEE == -1) {
                        this.addNextMenuToView(this.SCENARIO_GUARANTEE = this.addMenu(new ScenarioGuarantee()), new ScenarioGuaranteeList());
                    }
                    else {
                        this.menus.get(this.SCENARIO_GUARANTEE).set(1, (Object)new ScenarioGuaranteeList());
                    }
                    return this.SCENARIO_GUARANTEE;
                }
                case SCENARIO_PREVIEW: {
                    if (this.SCENARIO_PREVIEW == -1) {
                        this.SCENARIO_PREVIEW = this.addMenu(new ScenarioGeneratePreview());
                    }
                    else {
                        this.menus.get(this.SCENARIO_PREVIEW).set(0, (Object)new ScenarioGeneratePreview());
                    }
                    return this.SCENARIO_PREVIEW;
                }
                case SCENARIO_SETTINGS: {
                    if (this.SCENARIO_SETTINGS == -1) {
                        this.SCENARIO_SETTINGS = this.addMenu(new ScenarioSettings());
                        this.SCENARIO_CALENDAR = this.addNextMenuToView(this.SCENARIO_SETTINGS, new Scenario_Calendar(false));
                    }
                    else {
                        this.menus.get(this.SCENARIO_SETTINGS).set(0, (Object)new ScenarioSettings());
                    }
                    return this.SCENARIO_SETTINGS;
                }
                default: {
                    return this.MAINMENU;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            return this.MAINMENU;
        }
    }
    
    public final int getViewID() {
        return this.viewID;
    }
    
    public final void setViewID(final View eView) {
        Game.hoverManager.resetHoverActive_Menu();
        this.fromViewID = this.viewID;
        this.viewID = this.getViewID(eView);
        this.toViewID = this.viewID;
        this.updateDrawProvinces();
        ProvinceTouchExtraAction.updateExtraAction();
        AA_KeyManager.updateKeyExtraAction();
        ProvinceBorderManager.updateAction();
        ProvinceDraw.updateDrawMoveUnits();
        Game.mapBG.updateWorldMap();
    }
    
    public final void setViewIDWithoutAnimation(final View eView) {
        Game.hoverManager.resetHoverActive_Menu();
        this.viewID = this.getViewID(eView);
        this.updateDrawProvinces();
        ProvinceTouchExtraAction.updateExtraAction();
        AA_KeyManager.updateKeyExtraAction();
        ProvinceBorderManager.updateAction();
        ProvinceDraw.updateDrawMoveUnits();
        Game.mapBG.updateWorldMap();
    }
    
    protected final void updateLanguage() {
        for (int i = 0; i < this.menus.size(); ++i) {
            for (int j = 0; j < this.menus.get(i).size(); ++j) {
                this.menus.get(i).get(j).updateLanguage();
            }
        }
        this.dialogMenu.updateLanguage();
    }
    
    private final void updateDrawProvinces() {
        if (this.viewID > 0) {
            RendererGame.updateRenderer();
            ProvinceDraw.updateDrawProvinces();
            CivilizationRegionsManager.updateRenderer_CivNames();
            try {
                ProvinceHover.updateProvinceHoverBuild();
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
        Game.mapCoords.updateMinMaxPosY();
    }
    
    private final void resetChangeViewMode() {
        final int n = -1;
        this.toViewID = n;
        this.fromViewID = n;
        this.animationChangeViewPosX = 0.0f;
        this.backAnimation = false;
        this.animationStepID = 0;
    }
    
    public MenuManager() {
        this.menus = new ArrayList<List<Menu>>();
        this.orderOfMenu = new ArrayList<List<Integer>>();
        this.dialogMenu = new Dialog();
        this.lToast = new ArrayList<Toast>();
        this.iToastSize = 0;
        this.viewID = 0;
        this.INIT_GAME_MENU = -1;
        this.INIT_GAME_MENU_SELECT_MAP = -1;
        this.INIT_GAME_MENU_LANGUAGE = -1;
        this.MAINMENU = -1;
        this.MAINMENU_STATS = -1;
        this.LOAD_GAMES_LIST = -1;
        this.CLOUDS_MENU = -1;
        this.IN_GAME = -1;
        this.IN_GAME_MAPMODES = -1;
        this.IN_GAME_PROVINCE_ARMY = -1;
        this.IN_GAME_PROVINCE_ARMY_UNITS = -1;
        this.IN_GAME_PROVINCE_ARMY_TOP_BAR = -1;
        this.IN_GAME_PROVINCE_ARMY_MOVE = -1;
        this.IN_GAME_ESCAPE = -1;
        this.IN_GAME_ESCAPE_EMPTY = -1;
        this.IN_GAME_RECRUIT_ARMY = -1;
        this.IN_GAME_RECRUIT_ARMY_BATTLEFIELD = -1;
        this.IN_GAME_GENERALS = -1;
        this.IN_GAME_GENERAL_RECRUIT = -1;
        this.IN_GAME_BUILDINGS = -1;
        this.IN_GAME_BUILDINGS_GROUP_0 = -1;
        this.IN_GAME_BUILDINGS_GROUP_1 = -1;
        this.IN_GAME_BUILDINGS_GROUP_2 = -1;
        this.IN_GAME_BUILDINGS_GROUP_3 = -1;
        this.IN_GAME_ARMIES = -1;
        this.IN_GAME_CIV = -1;
        this.IN_GAME_COURT = -1;
        this.IN_GAME_COURT_OPTIONS = -1;
        this.IN_GAME_COURT_OPTIONS2 = -1;
        this.IN_GAME_RIGHT = -1;
        this.IN_GAME_RIGHT_QUEUE = -1;
        this.IN_GAME_NOTIFICATION = -1;
        this.IN_GAME_ADVISOR_RECRUIT = -1;
        this.IN_GAME_CHOOSE_TECHNOLOGY = -1;
        this.IN_GAME_NUKES = -1;
        this.IN_GAME_TECHNOLOGY_TREE = -1;
        this.IN_GAME_TECHNOLOGY_TREE_EMPTY = -1;
        this.IN_GAME_BATTLE_FULL = -1;
        this.IN_GAME_BATTLE_FULL_EMPTY = -1;
        this.IN_GAME_BUDGET = -1;
        this.IN_GAME_TAKE_LOAN = -1;
        this.IN_GAME_TAKE_LOAN_REPAY = -1;
        this.IN_GAME_EVENT = -1;
        this.IN_GAME_CURRENT_SITUATION = -1;
        this.IN_GAME_MESSAGES = -1;
        this.IN_GAME_WARS = -1;
        this.IN_GAME_CONSOLE = -1;
        this.IN_GAME_SAVE_GAME = -1;
        this.IN_GAME_POP_UP = -1;
        this.IN_GAME_CIV_BONUSES = -1;
        this.IN_GAME_PROVINCE_BONUSES = -1;
        this.IN_GAME_GOODS_CIV = -1;
        this.IN_GAME_GOODS = -1;
        this.IN_GAME_GOODS_EMPTY = -1;
        this.IN_GAME_WONDER = -1;
        this.IN_GAME_LEGACIES = -1;
        this.IN_GAME_LEGACIES_EMPTY = -1;
        this.IN_GAME_LEGACIES_INFO = -1;
        this.IN_GAME_HIDE_UI = -1;
        this.IN_GAME_INFO = -1;
        this.IN_GAME_PROVINCE_INFO = -1;
        this.IN_GAME_PROVINCE_INFO_BUILDINGS = -1;
        this.IN_GAME_PROVINCE_INFO_ARMY = -1;
        this.IN_GAME_REORGANIZE_UNITS = -1;
        this.IN_GAME_DISBAND_UNITS = -1;
        this.IN_GAME_BATTLE = -1;
        this.IN_GAME_BATTLE_ARMY = -1;
        this.IN_GAME_BATTLE_ARMY_DEFENDERS = -1;
        this.IN_GAME_SIEGE = -1;
        this.IN_GAME_WAR = -1;
        this.IN_GAME_PEACE = -1;
        this.NEW_GAME = -1;
        this.NEW_GAME_CIV = -1;
        this.NEW_GAME_SETTINGS = -1;
        this.NEW_GAME_FLAGS = -1;
        this.SCENARIOS = -1;
        this.SCENARIOS_CAMPAIGN = -1;
        this.GAME_LOST = -1;
        this.EDITOR = -1;
        this.EDITOR_GAMECIVS = -1;
        this.EDITOR_GAMECIVS_EDIT = -1;
        this.EDITOR_GAMECIVS_EDIT_RELIGION = -1;
        this.EDITOR_GAMECIVS_EDIT_GROUP = -1;
        this.CREATE_CIV = -1;
        this.CREATE_CIV_RELIGION = -1;
        this.CREATE_CIV_GROUP = -1;
        this.CREATE_CIV_FLAG = -1;
        this.EDITOR_MAPS = -1;
        this.EDITOR_MAPS_EDIT_PROVINCE_CONNECTIONS = -1;
        this.EDITOR_MAPS_EDIT = -1;
        this.EDITOR_MAPS_EDIT_SEA_PROVINCES = -1;
        this.EDITOR_MAPS_EDIT_ARMY_POSITION = -1;
        this.EDITOR_MAPS_EDIT_PORT_POSITION = -1;
        this.EDITOR_MAPS_EDIT_TERRAIN = -1;
        this.EDITOR_MAPS_EDIT_RESOURCE = -1;
        this.EDITOR_SELECT_PROVINCES = -1;
        this.EDITOR_MAPS_EDIT_CONTINENTS = -1;
        this.EDITOR_MAPS_EDIT_GEO_REGION = -1;
        this.EDITOR_MAPS_EDIT_OPTIMIZATION_REGIONS = -1;
        this.EDITOR_MAPS_EDIT_GROWTH_RATE = -1;
        this.EDITOR_MAPS_EDIT_FORMABLE_CIVS = -1;
        this.EDITOR_MAPS_EDIT_FORMABLE_CIV = -1;
        this.EDITOR_MAPS_EDIT_CIV_SELECT = -1;
        this.EDITOR_MAPS_EDIT_ECONOMY = -1;
        this.EDITOR_MAPS_EDIT_PROVINCE_NAMES = -1;
        this.EDITOR_MAPS_EDIT_LINES = -1;
        this.EDITOR_MAPS_EDIT_WAVES = -1;
        this.EDITOR_MAPS_EDIT_SUGGESTED_CIVILIZATIONS = -1;
        this.PRINT_A_MAP = -1;
        this.SETTINGS = -1;
        this.SETTINGS_RESOLUTION = -1;
        this.SETTINGS_UI = -1;
        this.EDITOR_SCENARIOS_LIST = -1;
        this.SCENARIO_WASTELAND_CONTINENTS = -1;
        this.SCENARIO_WASTELAND = -1;
        this.SCENARIO_CIVILIZATIONS = -1;
        this.SCENARIO_CIVILIZATIONS_LIST = -1;
        this.SCENARIO_ASSIGN = -1;
        this.SCENARIO_ASSIGN_CIVS = -1;
        this.SCENARIO_TECHNOLOGIES = -1;
        this.SCENARIO_TECHNOLOGIES_CIVS = -1;
        this.SCENARIO_SETTINGS = -1;
        this.SCENARIO_CALENDAR = -1;
        this.SCENARIO_ARMIES = -1;
        this.SCENARIO_POPULATION = -1;
        this.SCENARIO_ECONOMY = -1;
        this.SCENARIO_GOVERNMENT = -1;
        this.SCENARIO_BUILDINGS = -1;
        this.SCENARIO_CORES = -1;
        this.SCENARIO_CORES_LIST = -1;
        this.SCENARIO_CORES_LIST_IN_PROVINCE = -1;
        this.SCENARIO_RELIGION = -1;
        this.SCENARIO_RELIGION_LIST = -1;
        this.SCENARIO_ASSIGN_IN_GAME = -1;
        this.SCENARIO_ASSIGN_IN_GAME_CIVS = -1;
        this.SCENARIO_PREVIEW = -1;
        this.SCENARIO_RELATIONS = -1;
        this.SCENARIO_VASSALS = -1;
        this.SCENARIO_TRUCES = -1;
        this.SCENARIO_DECLARE_WAR = -1;
        this.SCENARIO_ALLIANCES = -1;
        this.SCENARIO_NON_AGGRESSION = -1;
        this.SCENARIO_GUARANTEE = -1;
        this.SCENARIO_DEFENSIVE = -1;
        this.SCENARIO_MILITARY_ACCESS = -1;
        this.SCENARIO_CREATE_ALLIANCE = -1;
        this.SCENARIO_CREATE_ALLIANCE_EDIT = -1;
        this.WORKSHOP = -1;
        this.MANAGE_MODS = -1;
        this.LOAD_STUFF = -1;
        this.activeSliderMenuID = -1;
        this.activeMenuElementID = -1;
        this.menu_MoveInnerElements = false;
        this.iSliderMenuStartPosY = 0;
        this.iSliderMenuActionDownPosY = 0;
        this.updateSliderMenuPosY = false;
        this.iSliderMenuStartPosX = 0;
        this.iSliderMenuActionDownPosX = 0;
        this.updateSliderMenuPosX = false;
        this.menu_MoveByTitleMode = false;
        this.menuResizeMode = false;
        this.menuResizeLEFT = false;
        this.closeMenuMode = false;
        this.colorPickerMode = false;
        this.pieChartMode = false;
        this.textScrollableMode = false;
        this.sliderMode = false;
        this.graphVertical_ScrollMode_Y = false;
        this.graphVertical_ScrollMode_X = false;
        this.fromViewID = -1;
        this.toViewID = -1;
        this.backAnimation = false;
        this.animationChangeViewPosX = 0.0f;
        this.animationStepID = 0;
        this.ANIMATION_TIME = 1000;
        this.TOAST_TIME = 0L;
        this.WAR_ANIMATION_TIME = 3000;
        this.WAR_TIME = 0L;
        this.inCreateNewArmy = false;
    }
    
    public final void initColorPicker() {
        this.colorPicker = new ColorPicker();
        this.getColorPicker().buildColors();
        this.getColorPicker().setHueWidth((int)(CFG.BUTTON_WIDTH * 0.35f * CFG.GUI_SCALE));
        this.getColorPicker().setSVHeight((int)(ImageManager.getImage(Images.pickerSV).getHeight() * 2 * CFG.GUI_SCALE));
        this.getColorPicker().setResizeHeight((int)(30.0f * CFG.GUI_SCALE));
    }
    
    private final int addMenu(final Menu menu) {
        final List<Menu> nMenus = new ArrayList<Menu>();
        nMenus.add(menu);
        this.menus.add(nMenus);
        final List<Integer> order = new ArrayList<Integer>();
        order.add(this.menus.get(this.menus.size() - 1).size() - 1);
        this.orderOfMenu.add(order);
        return this.menus.size() - 1;
    }
    
    public final int addNextMenuToView(final int toView, final Menu menu) {
        this.menus.get(toView).add(menu);
        this.addMenuToOrderAtTheTop(toView);
        return this.menus.get(toView).size() - 1;
    }
    
    public final void addMenuToOrderAtTheTop(final int toView) {
        try {
            this.orderOfMenu.get(toView).add(this.menus.get(toView).size() - 1);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void setOrderOfMenu(int menuID) {
        try {
            MenuManager.orderOfMenuInGame = false;
            boolean found = false;
            int i = 0;
            while (i < this.orderOfMenu.get(this.viewID).size()) {
                if (this.orderOfMenu.get(this.viewID).get(i) == menuID) {
                    menuID = this.orderOfMenu.get(this.viewID).get(i);
                    if (i == 0) {
                        return;
                    }
                    found = true;
                    break;
                }
                else {
                    ++i;
                }
            }
            if (found) {
                final List<Integer> lOrder = new ArrayList<Integer>();
                lOrder.add(menuID);
                for (int j = 0; j < this.orderOfMenu.get(this.viewID).size(); ++j) {
                    if (this.orderOfMenu.get(this.viewID).get(j) != menuID) {
                        lOrder.add(this.orderOfMenu.get(this.viewID).get(j));
                    }
                }
                this.orderOfMenu.set(this.viewID, lOrder);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void addToast(final String sText) {
        this.addToast(new Toast(sText, 0));
    }
    
    public final void addToast(final String sText, final int iFontID) {
        this.addToast(new Toast(sText, iFontID));
    }
    
    public final void addToast_Error(final String sText) {
        this.addToast(new Toast(sText, 0, 6000, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
    }
    
    public final void addToast_Error(final String sText, final int imageID) {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_Text(sText, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
        nData.add(new MenuElement_HoverElement_Type_Image(imageID, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.addToast(new Toast(nElements, 0, 6000));
    }
    
    public final void addToastGold(final String s1, final int img) {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_Text(s1, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_Image(img, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.addToast(new Toast(nElements, 0, 6000));
    }
    
    public final void addToastBonus(final String s1, final String s2, final int img, final int civID) {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_Flag(civID, 0, CFG.PADDING));
        nData.add(new MenuElement_HoverElement_Type_Text(s1, CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
        nData.add(new MenuElement_HoverElement_Type_Text(s2, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_Image(img, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.addToast(new Toast(nElements, 0, 6000));
    }
    
    public final void addToastGold(final String s1, final int img, final int time) {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_Text(s1, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_Image(img, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.addToast(new Toast(nElements, 0, time));
    }
    
    public final void addToastGold_Short(final String s1, final int img) {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_Text(s1, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_Image(img, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.addToast(new Toast(nElements, 0, 1750));
    }
    
    public final void addToastInsufficient(final String s1, final String s2, final int img) {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_Text(s1));
        nData.add(new MenuElement_HoverElement_Type_Text(s2, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
        nData.add(new MenuElement_HoverElement_Type_Image(img, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.addToast(new Toast(nElements, 0, 6000));
    }
    
    public final void addToastPositive(final String s1, final String s2, final int img) {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_Text(s1));
        nData.add(new MenuElement_HoverElement_Type_Text(s2, Colors.HOVER_POSITIVE));
        nData.add(new MenuElement_HoverElement_Type_Image(img, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.addToast(new Toast(nElements, 0, 2500));
    }
    
    public final void addToastNegative(final String s1, final String s2, final int img) {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_Text(s1));
        nData.add(new MenuElement_HoverElement_Type_Text(s2, Colors.HOVER_NEGATIVE));
        nData.add(new MenuElement_HoverElement_Type_Image(img, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.addToast(new Toast(nElements, 0, 2500));
    }
    
    public final void addToast_Error(final String sText, final int imageID, final int time) {
        final List<MenuElement_HoverElement> nElements = new ArrayList<MenuElement_HoverElement>();
        final List<MenuElement_HoverElement_Type> nData = new ArrayList<MenuElement_HoverElement_Type>();
        nData.add(new MenuElement_HoverElement_Type_Text(sText, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
        nData.add(new MenuElement_HoverElement_Type_Image(imageID, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.addToast(new Toast(nElements, 0, time));
    }
    
    public final void addToast(final String sText, final int iFontID, final int nTimeInView) {
        this.addToast(new Toast(sText, iFontID, nTimeInView));
    }
    
    public final void addToast(final Toast nToast) {
        this.lToast.add(nToast);
        if (this.lToast.size() > GameValues.notifications.TOAST_MAX_SIZE) {
            for (int i = 0; i < this.lToast.size() - GameValues.notifications.TOAST_MAX_SIZE; ++i) {
                this.lToast.remove(i);
            }
        }
        this.iToastSize = this.lToast.size();
        this.TOAST_TIME = CFG.currentTimeMillis;
    }
    
    private final void clearToast() {
        this.lToast.clear();
        this.iToastSize = 0;
    }
    
    public final void update() {
        try {
            Label_0070: {
                if (this.fromViewID >= 0) {
                    this.animationChangeViewPosX = CFG.changeAnimationPos(this.animationStepID++, this.animationChangeViewPosX, this.backAnimation, CFG.GAME_WIDTH);
                    if (Math.abs(this.animationChangeViewPosX) < CFG.GAME_WIDTH) {
                        final int iNumOfFPS = Renderer.uFPS.iNumOfFPS;
                        final FPS ufps = Renderer.uFPS;
                        if (iNumOfFPS >= 22) {
                            break Label_0070;
                        }
                    }
                    this.resetChangeViewMode();
                }
            }
            Game.hoverManager.udpateMobile();
            for (int i = 0; i < this.getActiveMenu().size(); ++i) {
                if (this.getActiveMenu().get(i).getScrollableY() || this.getActiveMenu().get(i).getScrollableX()) {
                    this.getActiveMenu().get(i).update();
                }
            }
            Game.hoverManager.updateHoveredElement();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void addClickAnimation(final ClickAnimation nClick) {
        try {
            if (GameValues.clickAnim.ENABLE_ACTION_DOTS_ANIMATION) {
                MenuManager.clickAnimations.add(nClick);
                MenuManager.clickAnimationsSize = MenuManager.clickAnimations.size();
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void draw(final SpriteBatch oSB) {
        try {
            if (this.getInGame() && InGame.ONLY_MAP_MODE) {
                return;
            }
            if (this.TOAST_TIME + this.ANIMATION_TIME >= CFG.currentTimeMillis) {
                final float fAlpha = 0.8f - 0.8f * ((CFG.currentTimeMillis - this.TOAST_TIME) / (float)this.ANIMATION_TIME);
                oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, fAlpha));
                Images.gradientXY.draw(oSB, CFG.GAME_WIDTH / 4, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 2, CFG.GAME_WIDTH / 2, CFG.BUTTON_HEIGHT * 2);
                oSB.setColor(Color.WHITE);
            }
            if (this.WAR_TIME + this.WAR_ANIMATION_TIME >= CFG.currentTimeMillis) {
                final float fAlpha = 0.4f - 0.4f * ((CFG.currentTimeMillis - this.WAR_TIME) / (float)this.WAR_ANIMATION_TIME);
                oSB.setColor(new Color(DiplomacyManager.COLOR_WAR.r, DiplomacyManager.COLOR_WAR.g, DiplomacyManager.COLOR_WAR.b, fAlpha));
                Images.gradientXY.draw(oSB, 0, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT);
                Images.gradientXY.draw(oSB, CFG.GAME_WIDTH / 4, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT / 2, CFG.GAME_WIDTH / 2, CFG.BUTTON_HEIGHT / 2);
                oSB.setColor(Color.WHITE);
            }
            if (this.fromViewID < 0) {
                this.draw(oSB, this.viewID, 0, 0);
            }
            else {
                this.draw(oSB, this.fromViewID, (int)this.animationChangeViewPosX, 0);
                this.draw(oSB, this.toViewID, (int)this.animationChangeViewPosX + CFG.GAME_WIDTH * (this.backAnimation ? -1 : 1), 0);
            }
            if (MenuManager.clickAnimationsSize > 0) {
                try {
                    oSB.setColor(Color.WHITE);
                    Renderer.oSBBorder.end();
                    Renderer.oSBBorder.begin();
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
                try {
                    try {
                        for (int j = MenuManager.clickAnimationsSize - 1; j >= 0; --j) {
                            try {
                                if (MenuManager.clickAnimations.get(j).draw(oSB)) {
                                    MenuManager.clickAnimations.remove(j);
                                }
                            }
                            catch (final Exception ex2) {
                                CFG.exceptionStack(ex2);
                            }
                        }
                        MenuManager.clickAnimationsSize = MenuManager.clickAnimations.size();
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
                try {
                    Renderer.oSBBorder.end();
                    Renderer.oSBBorder.begin();
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
            this.drawToast(oSB);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void drawToast(final SpriteBatch oSB) {
        for (int i = this.iToastSize - 1; i >= 0; --i) {
            if (!this.lToast.get(i).inView) {
                this.lToast.remove(i);
                this.iToastSize = this.lToast.size();
            }
        }
        int i = this.iToastSize - 1;
        int tY = 0;
        while (i >= 0) {
            this.lToast.get(i).draw(oSB, 0, tY);
            if (this.lToast.get(i).iPosX == -1) {
                tY += this.lToast.get(i).iHeight + CFG.PADDING;
            }
            --i;
        }
    }
    
    public final Status getTitleStatus(final int menuID, final int i) {
        try {
            if (this.fromViewID < 0) {
                if (this.menu_MoveByTitleMode) {
                    if (this.activeSliderMenuID == this.orderOfMenu.get(menuID).get(i)) {
                        return Status.ACTIVE;
                    }
                }
                else if (Game.hoverManager.hoverActiveMenuTitleID == this.orderOfMenu.get(menuID).get(i)) {
                    if (Game.hoverManager.hoverActiveMenuTitleCloseHovered) {
                        return Status.CLOSE_HOVERED;
                    }
                    return Status.HOVERED;
                }
            }
        }
        catch (final Exception ex) {}
        return Status.DEFAULT;
    }
    
    public final void draw(final SpriteBatch oSB, final int menuID, final int iTranslateX, final int iTranslateY) {
        try {
            for (int i = this.menus.get(menuID).size() - 1; i >= 0; --i) {
                try {
                    if (this.menus.get(menuID).get(this.orderOfMenu.get(menuID).get(i)).getVisible()) {
                        this.menus.get(menuID).get(this.orderOfMenu.get(menuID).get(i)).draw(oSB, iTranslateX, iTranslateY, !this.dialogMenu.getVisible() && this.orderOfMenu.get(menuID).get(i) == this.activeSliderMenuID, this.getTitleStatus(menuID, i));
                        Renderer.clearUnclearedScissors(oSB);
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
        try {
            if (Game.hoverManager.hoverActiveSliderMenuID >= 0 && Game.hoverManager.hoverActiveMenuElementID >= 0) {
                Game.hoverManager.updateElementHover_Animation();
                this.getActiveMenu().get(Game.hoverManager.hoverActiveSliderMenuID).drawHover(oSB, iTranslateX, 0, Game.hoverManager.hoverActiveMenuElementID);
            }
            else if (Game.provinceHover_Informations != null) {
                Game.hoverManager.updateElementHover_Animation();
                Game.provinceHover_Informations.drawProvinceInfo(oSB, Touch.getMousePosX() + Renderer.getHover_ExtraPosX(), Touch.getMousePosY() + Renderer.getHover_ExtraPosY());
            }
            if (this.colorPicker.getVisible()) {
                this.colorPicker.draw(oSB, iTranslateX);
            }
            if (this.dialogMenu.getVisible()) {
                this.dialogMenu.draw(oSB, iTranslateX, iTranslateY, true, Status.DEFAULT);
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
        }
    }
    
    public final List<Menu> getActiveMenu() {
        try {
            return this.menus.get(this.viewID);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            return this.menus.get(0);
        }
    }
    
    public final MenuElement getActiveMenuElement() {
        try {
            try {
                return this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID);
            }
            catch (final IndexOutOfBoundsException ex) {
                CFG.exceptionStack(ex);
                return this.menus.get(0).get(0).getMenuElement(0);
            }
        }
        catch (final Exception ex2) {
            CFG.exceptionStack(ex2);
            return new Button();
        }
    }
    
    public final MenuElement getMenuElement(final int nSliderMenuID, final int nMenuElementID) {
        return this.getActiveMenu().get(nSliderMenuID).getMenuElement(nMenuElementID);
    }
    
    public final int getActiveOrder(final int i) {
        try {
            return this.orderOfMenu.get(this.viewID).get(i);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
            return 0;
        }
    }
    
    protected final void actionElement(final int sliderMenuID, final int nMenuElementID, final boolean PPM) {
        Game.keyboard.hideKeyboard();
        if (PPM) {
            this.getActiveMenu().get(sliderMenuID).actionElementPPM(nMenuElementID);
        }
        else {
            this.getActiveMenu().get(sliderMenuID).actionElement(nMenuElementID);
        }
    }
    
    protected final void onBackPressed() {
        if (this.dialogMenu.getVisible()) {
            this.dialogMenu.onBackPressed();
        }
        try {
            this.getActiveMenu().get(0).onBackPressed();
        }
        catch (final IndexOutOfBoundsException ex) {}
        Touch.resetAllModes();
    }
    
    public final boolean actionDown(final int nPosX, final int nPosY) {
        if (this.getInGame() && InGame.ONLY_MAP_MODE) {
            return false;
        }
        try {
            if (this.dialogMenu.getVisible()) {
                for (int i = 0; i < this.dialogMenu.getMenuElementsSize(); ++i) {
                    if (nPosX >= this.dialogMenu.getPosX() + this.dialogMenu.getMenuElement(i).getPosX() && nPosX <= this.dialogMenu.getPosX() + this.dialogMenu.getMenuElement(i).getPosX() + this.dialogMenu.getMenuElement(i).getWidth() && nPosY >= this.dialogMenu.getPosY() + this.dialogMenu.getMenuElement(i).getPosY() && nPosY <= this.dialogMenu.getPosY() + this.dialogMenu.getMenuElement(i).getPosY() + this.dialogMenu.getMenuElement(i).getHeight()) {
                        this.activeMenuElementID = i;
                        return true;
                    }
                }
                return true;
            }
            if (this.colorPickerMode) {
                this.colorPicker.touch(nPosX, nPosY);
                return true;
            }
            if (this.colorPicker.getVisible() && nPosX >= this.colorPicker.getPosX() && nPosX <= this.colorPicker.getPosX() + this.colorPicker.getWidth() && nPosY >= this.colorPicker.getPosY() && nPosY <= this.colorPicker.getPosY() + this.colorPicker.getHeight()) {
                this.colorPickerMode = true;
                this.colorPicker.touch(nPosX, nPosY);
                return true;
            }
            if (this.actionDown_CloseMenu(nPosX, nPosY)) {
                return true;
            }
            if (this.actionDown_ResizeMenu(nPosX, nPosY)) {
                return true;
            }
            if (this.actionDown_MenuTitle(nPosX, nPosY)) {
                try {
                    this.getActiveMenu().get(this.activeSliderMenuID).getTitle().onHovered();
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
                return true;
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            for (int i = 0; i < this.getActiveMenu().size(); ++i) {
                if (this.getActiveMenu().get(this.getActiveOrder(i)).getVisible() && nPosX >= this.getActiveMenu().get(this.getActiveOrder(i)).getPosX() && nPosX <= this.getActiveMenu().get(this.getActiveOrder(i)).getPosX() + this.getActiveMenu().get(this.getActiveOrder(i)).getWidth() && nPosY >= this.getActiveMenu().get(this.getActiveOrder(i)).getPosY() && nPosY <= this.getActiveMenu().get(this.getActiveOrder(i)).getPosY() + this.getActiveMenu().get(this.getActiveOrder(i)).getHeight()) {
                    this.activeSliderMenuID = this.getActiveOrder(i);
                    this.getActiveMenu().get(this.activeSliderMenuID).stopScrolling();
                    if (this.getActiveMenu().get(this.getActiveOrder(i)).getScrollableY() || this.getActiveMenu().get(this.getActiveOrder(i)).getScrollableX()) {
                        this.menu_MoveInnerElements = true;
                        this.iSliderMenuStartPosY = this.getActiveMenu().get(this.getActiveOrder(i)).getMenuPosY() - nPosY;
                        this.iSliderMenuActionDownPosY = nPosY;
                        this.iSliderMenuStartPosX = this.getActiveMenu().get(this.getActiveOrder(i)).getMenuPosX() - nPosX;
                        this.iSliderMenuActionDownPosX = nPosX;
                    }
                    for (int j = 0; j < this.getActiveMenu().get(this.getActiveOrder(i)).getMenuElementsSize(); ++j) {
                        if (this.getActiveMenu().get(this.getActiveOrder(i)).getMenuElement(j).getVisible() && nPosX >= this.getActiveMenu().get(this.getActiveOrder(i)).getMenuElement(j).getPosX() + this.getActiveMenu().get(this.getActiveOrder(i)).getMenuPosX() && nPosX <= this.getActiveMenu().get(this.getActiveOrder(i)).getMenuElement(j).getPosX() + this.getActiveMenu().get(this.getActiveOrder(i)).getMenuPosX() + this.getActiveMenu().get(this.getActiveOrder(i)).getMenuElement(j).getWidth() && nPosY >= this.getActiveMenu().get(this.getActiveOrder(i)).getMenuElement(j).getPosY() + this.getActiveMenu().get(this.getActiveOrder(i)).getMenuPosY() && nPosY <= this.getActiveMenu().get(this.getActiveOrder(i)).getMenuElement(j).getPosY() + this.getActiveMenu().get(this.getActiveOrder(i)).getMenuElement(j).getHeight() + this.getActiveMenu().get(this.getActiveOrder(i)).getMenuPosY()) {
                            this.activeMenuElementID = j;
                            if (CFG.isAndroid) {
                                if (Game.hoverManager.hoverActiveSliderMenuID != this.activeSliderMenuID || Game.hoverManager.hoverActiveMenuElementID != j) {
                                    if (Game.hoverManager.hoverActiveMenuElementID >= 0 && Game.hoverManager.hoverActiveSliderMenuID >= 0) {
                                        Game.hoverManager.resetHoverActive_Menu();
                                    }
                                    Game.hoverManager.hoverActiveSliderMenuID = this.activeSliderMenuID;
                                    Game.hoverManager.hoverActiveMenuElementID = this.activeMenuElementID;
                                    HoverManager.hoverTime = CFG.currentTimeMillis;
                                    this.getActiveMenu().get(Game.hoverManager.hoverActiveSliderMenuID).getMenuElement(Game.hoverManager.hoverActiveMenuElementID).setIsHovered(true);
                                    this.getActiveMenu().get(Game.hoverManager.hoverActiveSliderMenuID).getMenuElement(Game.hoverManager.hoverActiveMenuElementID).buildElementHover();
                                    Game.hoverManager.updateHoveredFlag();
                                    final HoverManager hoverManager = Game.hoverManager;
                                    HoverManager.hoverMobileTime = CFG.currentTimeMillis;
                                }
                                else {
                                    final HoverManager hoverManager2 = Game.hoverManager;
                                    HoverManager.hoverMobileTime = CFG.currentTimeMillis;
                                }
                            }
                            this.setOrderOfMenu(this.getActiveOrder(i));
                            this.getActiveMenu().get(this.activeSliderMenuID).onHovered();
                            switch (this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).getTypeOfElement()) {
                                case SLIDER: {
                                    if (this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).getClickable()) {
                                        this.sliderMode = true;
                                        this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).updateSlider(nPosX - this.getActiveMenu().get(this.activeSliderMenuID).getPosX());
                                        break;
                                    }
                                    break;
                                }
                                case PIECHART_WITH_STATS: {
                                    this.pieChartMode = true;
                                    if (this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).getScrollable()) {
                                        this.menu_MoveInnerElements = false;
                                        this.iSliderMenuStartPosY = this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).getScrollPosY() - nPosY;
                                        this.iSliderMenuActionDownPosX = nPosX;
                                        this.iSliderMenuActionDownPosY = nPosY;
                                        break;
                                    }
                                    break;
                                }
                                case TEXT_SCROLLABLE: {
                                    if (this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).getScrollable()) {
                                        this.textScrollableMode = true;
                                        this.menu_MoveInnerElements = false;
                                        this.iSliderMenuStartPosX = this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).getScrollPosX() - nPosX;
                                        this.iSliderMenuActionDownPosX = nPosX;
                                        this.iSliderMenuActionDownPosY = nPosY;
                                        break;
                                    }
                                    break;
                                }
                                case GRAPH_VERTICAL: {
                                    if (this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).getMoveable()) {
                                        if (this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).getInStatisticsMode()) {
                                            this.iSliderMenuStartPosY = this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).getCurrent() - nPosY;
                                            this.graphVertical_ScrollMode_Y = true;
                                        }
                                        else {
                                            this.iSliderMenuStartPosX = this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).getCurrent() - nPosX;
                                            this.graphVertical_ScrollMode_X = true;
                                        }
                                        this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).stopScrolling();
                                    }
                                    else {
                                        this.graphVertical_ScrollMode_X = true;
                                    }
                                    this.iSliderMenuActionDownPosY = nPosY;
                                    this.iSliderMenuActionDownPosX = nPosX;
                                    this.iSliderMenuStartPosX = this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).getCurrent() - nPosX;
                                    this.iSliderMenuActionDownPosX = nPosX;
                                    this.menu_MoveInnerElements = false;
                                    break;
                                }
                            }
                            return true;
                        }
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return false;
    }
    
    public final boolean actionMove(final int nPosX, final int nPosY) {
        try {
            if (this.dialogMenu.getVisible()) {
                return true;
            }
            if (this.colorPickerMode) {
                this.colorPicker.touch(nPosX, nPosY);
                return true;
            }
            if (this.menuResizeMode) {
                this.actionMove_ResizeMenu(nPosX, nPosY);
                return true;
            }
            if (this.menu_MoveByTitleMode) {
                this.actionMove_MenuTitle(nPosX, nPosY);
                return true;
            }
            if (this.menu_MoveInnerElements) {
                this.actionMove_MenuInnerElements(nPosX, nPosY);
                this.getActiveMenu().get(this.activeSliderMenuID).setScrollPosY(nPosY);
                this.getActiveMenu().get(this.activeSliderMenuID).setScrollPosX(nPosX);
                return true;
            }
            if (this.pieChartMode) {
                this.actionMove_ScrollPosY(nPosX, nPosY);
                return true;
            }
            if (this.textScrollableMode) {
                this.actionMove_ScrollPosX(nPosX, nPosY);
                return true;
            }
            if (this.sliderMode) {
                this.actionMoveSlider(nPosX, nPosY);
                return true;
            }
            if (this.graphVertical_ScrollMode_Y) {
                if (this.activeSliderMenuID >= 0 && this.activeMenuElementID >= 0) {
                    if (this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).getMoveable()) {
                        this.actionMove_MenuInnerElements(nPosX, nPosY);
                        this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).setScrollPosY(nPosY);
                        if (this.updateSliderMenuPosY) {
                            this.iSliderMenuStartPosY = this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).getCurrent() - nPosY;
                            this.updateSliderMenuPosY = false;
                        }
                        else {
                            this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).setCurrent(this.iSliderMenuStartPosY + nPosY);
                        }
                    }
                    return true;
                }
            }
            else if (this.graphVertical_ScrollMode_X) {
                if (this.activeSliderMenuID >= 0 && this.activeMenuElementID >= 0) {
                    if (this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).getMoveable()) {
                        this.actionMove_MenuInnerElements(nPosX, nPosY);
                        this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).setScrollPosY(nPosX);
                        if (this.updateSliderMenuPosX) {
                            this.iSliderMenuStartPosX = this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).getCurrent() - nPosX;
                            this.updateSliderMenuPosX = false;
                        }
                        else {
                            this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).setCurrent(this.iSliderMenuStartPosX + nPosX);
                        }
                    }
                    return true;
                }
            }
            else {
                if (this.activeMenuElementID >= 0) {
                    return true;
                }
                if (this.closeMenuMode) {
                    return true;
                }
            }
        }
        catch (final IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (final NullPointerException ex2) {
            CFG.exceptionStack(ex2);
        }
        return false;
    }
    
    public final boolean actionUp(final int nPosX, final int nPosY, final int nPointer, final int button) {
        try {
            if (this.dialogMenu.getVisible()) {
                if (this.activeMenuElementID >= 0) {
                    if (this.dialogMenu.getMenuElement(this.activeMenuElementID).getClickable() && nPosX >= this.dialogMenu.getPosX() + this.dialogMenu.getMenuElement(this.activeMenuElementID).getPosX() && nPosX <= this.dialogMenu.getPosX() + this.dialogMenu.getMenuElement(this.activeMenuElementID).getPosX() + this.dialogMenu.getMenuElement(this.activeMenuElementID).getWidth() && nPosY >= this.dialogMenu.getPosY() + this.dialogMenu.getMenuElement(this.activeMenuElementID).getPosY() && nPosY <= this.dialogMenu.getPosY() + this.dialogMenu.getMenuElement(this.activeMenuElementID).getPosY() + this.dialogMenu.getMenuElement(this.activeMenuElementID).getHeight()) {
                        Game.soundsManager.playSound(SoundsManager.SOUND_CLICK2);
                        this.dialogMenu.actionElement(this.activeMenuElementID);
                        return true;
                    }
                }
                else {
                    this.dialogMenu.disableButtons();
                    Dialog.dialogFalse();
                    this.dialogMenu.closeMenu();
                }
            }
            if (this.colorPickerMode) {
                this.colorPicker.touch(nPosX, nPosY);
                this.colorPicker.touchUp();
                Game.soundsManager.playSound(Game.soundsManager.getClickMain());
                return true;
            }
            if (this.menuResizeMode) {
                return true;
            }
            if (this.menu_MoveByTitleMode) {
                if (this.getActiveMenu().get(this.activeSliderMenuID).getTitle() != null && nPosX >= this.getActiveMenu().get(this.activeSliderMenuID).getPosX() && nPosX <= this.getActiveMenu().get(this.activeSliderMenuID).getPosX() + this.getActiveMenu().get(this.activeSliderMenuID).getWidth() && nPosY >= this.getActiveMenu().get(this.activeSliderMenuID).getPosY() - this.getActiveMenu().get(this.activeSliderMenuID).getTitle().getHeight() && nPosY <= this.getActiveMenu().get(this.activeSliderMenuID).getPosY()) {
                    this.getActiveMenu().get(this.activeSliderMenuID).getTitle().action();
                }
                return true;
            }
            if (this.sliderMode) {
                this.sliderMode = false;
                if (this.getActiveMenuElement().getClickable()) {
                    this.actionElement(this.activeSliderMenuID, this.activeMenuElementID, button == 1);
                }
                return true;
            }
            if (this.closeMenuMode) {
                this.actionUp_CloseMenu(nPosX, nPosY);
                return true;
            }
            if (this.activeMenuElementID >= 0 && this.activeSliderMenuID >= 0) {
                if (this.pieChartMode) {
                    if (this.iSliderMenuActionDownPosY >= nPosY - CFG.PADDING && this.iSliderMenuActionDownPosY <= nPosY + CFG.PADDING && this.iSliderMenuActionDownPosX >= nPosX - CFG.PADDING && this.iSliderMenuActionDownPosX <= nPosX + CFG.PADDING) {
                        this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).setDescription(!this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).getDescription());
                        Game.soundsManager.playSound(this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).getSFX());
                    }
                    if (this.getActiveMenuElement().getClickable()) {
                        this.actionElement(this.activeSliderMenuID, this.activeMenuElementID, button == 1);
                    }
                    return true;
                }
                if (this.graphVertical_ScrollMode_X || this.graphVertical_ScrollMode_Y) {
                    if (this.iSliderMenuActionDownPosY >= nPosY - CFG.PADDING * CFG.GUI_SCALE && this.iSliderMenuActionDownPosY <= nPosY + CFG.PADDING * CFG.GUI_SCALE && this.iSliderMenuActionDownPosX >= nPosX - CFG.PADDING * CFG.GUI_SCALE && this.iSliderMenuActionDownPosX <= nPosX + CFG.PADDING * CFG.GUI_SCALE) {
                        this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).setInStatisticsMode(!this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).getInStatisticsMode());
                    }
                    else if (this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).getMoveable()) {
                        this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).scrollTheMenu();
                    }
                    this.actionElement(this.activeSliderMenuID, this.activeMenuElementID, button == 1);
                    return true;
                }
                if ((!this.menu_MoveInnerElements || (this.iSliderMenuActionDownPosY >= nPosY - CFG.PADDING && this.iSliderMenuActionDownPosY <= nPosY + CFG.PADDING && this.iSliderMenuActionDownPosX >= nPosX - CFG.PADDING && this.iSliderMenuActionDownPosX <= nPosX + CFG.PADDING)) && this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).getClickable() && nPosX >= this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).getPosX() + this.getActiveMenu().get(this.activeSliderMenuID).getMenuPosX() && nPosX <= this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).getPosX() + this.getActiveMenu().get(this.activeSliderMenuID).getMenuPosX() + this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).getWidth() && nPosY >= this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).getPosY() + this.getActiveMenu().get(this.activeSliderMenuID).getMenuPosY() && nPosY <= this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).getPosY() + this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).getHeight() + this.getActiveMenu().get(this.activeSliderMenuID).getMenuPosY()) {
                    try {
                        Game.soundsManager.playSound(this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).getSFX());
                    }
                    catch (final IndexOutOfBoundsException ex) {
                        Game.soundsManager.playSound(Game.soundsManager.getClickMain());
                    }
                    this.actionElement(this.activeSliderMenuID, this.activeMenuElementID, button == 1);
                    return true;
                }
            }
            if (this.menu_MoveInnerElements) {
                this.getActiveMenu().get(this.activeSliderMenuID).scrollTheMenu();
                return true;
            }
        }
        catch (final IndexOutOfBoundsException ex2) {}
        catch (final Exception ex3) {}
        return false;
    }
    
    private final void actionMove_MenuInnerElements(final int nPosX, final int nPosY) {
        try {
            if (this.getActiveMenu().get(this.activeSliderMenuID).getScrollableY()) {
                if (this.updateSliderMenuPosY) {
                    this.iSliderMenuStartPosY = this.getActiveMenu().get(this.activeSliderMenuID).getMenuPosY() - nPosY;
                    this.updateSliderMenuPosY = false;
                }
                else {
                    this.getActiveMenu().get(this.activeSliderMenuID).setMenuPosY(this.iSliderMenuStartPosY + nPosY);
                }
            }
            if (this.getActiveMenu().get(this.activeSliderMenuID).getScrollableX()) {
                if (this.updateSliderMenuPosX) {
                    this.iSliderMenuStartPosX = this.getActiveMenu().get(this.activeSliderMenuID).getMenuPosX() - nPosX;
                    this.updateSliderMenuPosX = false;
                }
                else {
                    this.getActiveMenu().get(this.activeSliderMenuID).setMenuPosX(this.iSliderMenuStartPosX + nPosX);
                }
            }
        }
        catch (final IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (final NullPointerException ex2) {
            CFG.exceptionStack(ex2);
        }
    }
    
    private final boolean actionDown_ResizeMenu(final int nPosX, final int nPosY) {
        try {
            for (int i = 0; i < this.getActiveMenu().size(); ++i) {
                if (this.getActiveMenu().get(this.getActiveOrder(i)).getVisible() && this.getActiveMenu().get(this.getActiveOrder(i)).getResizable()) {
                    if (nPosX >= this.getActiveMenu().get(this.getActiveOrder(i)).getPosX() + this.getActiveMenu().get(this.getActiveOrder(i)).getWidth() - CFG.PADDING * 6 && nPosX <= this.getActiveMenu().get(this.getActiveOrder(i)).getPosX() + this.getActiveMenu().get(this.getActiveOrder(i)).getWidth() && nPosY >= this.getActiveMenu().get(this.getActiveOrder(i)).getPosY() + this.getActiveMenu().get(this.getActiveOrder(i)).getHeight() - CFG.PADDING * 6 && nPosY <= this.getActiveMenu().get(this.getActiveOrder(i)).getPosY() + this.getActiveMenu().get(this.getActiveOrder(i)).getHeight()) {
                        this.startMenuResizeMode(this.getActiveOrder(i), nPosX, nPosY, false);
                        this.setOrderOfMenu(this.getActiveOrder(i));
                        return true;
                    }
                    if (nPosX >= this.getActiveMenu().get(this.getActiveOrder(i)).getPosX() && nPosX <= this.getActiveMenu().get(this.getActiveOrder(i)).getPosX() + CFG.PADDING * 6 && nPosY >= this.getActiveMenu().get(this.getActiveOrder(i)).getPosY() + this.getActiveMenu().get(this.getActiveOrder(i)).getHeight() - CFG.PADDING * 6 && nPosY <= this.getActiveMenu().get(this.getActiveOrder(i)).getPosY() + this.getActiveMenu().get(this.getActiveOrder(i)).getHeight()) {
                        this.startMenuResizeMode(this.getActiveOrder(i), nPosX, nPosY, true);
                        this.iSliderMenuActionDownPosX = nPosX;
                        this.setOrderOfMenu(this.getActiveOrder(i));
                        return true;
                    }
                }
            }
        }
        catch (final IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (final NullPointerException ex2) {
            CFG.exceptionStack(ex2);
        }
        return false;
    }
    
    protected final void startMenuResizeMode(final int i, final int nPosX, final int nPosY, final boolean sliderMenuResizeLEFT) {
        this.menuResizeMode = true;
        this.activeSliderMenuID = i;
        this.iSliderMenuStartPosX = this.getActiveMenu().get(this.activeSliderMenuID).getWidth() - nPosX;
        this.iSliderMenuStartPosY = this.getActiveMenu().get(this.activeSliderMenuID).getHeight() - nPosY;
        this.menuResizeLEFT = sliderMenuResizeLEFT;
    }
    
    private final void actionMove_ResizeMenu(final int nPosX, final int nPosY) {
        this.getActiveMenu().get(this.activeSliderMenuID).setHeight(nPosY + this.iSliderMenuStartPosY);
        this.getActiveMenu().get(this.activeSliderMenuID).setMenuPosY(this.getActiveMenu().get(this.activeSliderMenuID).getMenuPosY());
        this.getActiveMenu().get(this.activeSliderMenuID).updateScrollable();
        this.getActiveMenu().get(this.activeSliderMenuID).updateMenuElements_IsInView();
        if (this.menuResizeLEFT) {
            if (this.getActiveMenu().get(this.activeSliderMenuID).setWidth_Resize(this.getActiveMenu().get(this.activeSliderMenuID).getWidth() - (nPosX - this.iSliderMenuActionDownPosX))) {
                this.iSliderMenuActionDownPosX = nPosX;
            }
            else {
                this.iSliderMenuActionDownPosX = this.getActiveMenu().get(this.activeSliderMenuID).getPosX();
            }
            if (this.getActiveMenu().get(this.activeSliderMenuID).getPosX() < 0) {
                this.getActiveMenu().get(this.activeSliderMenuID).setWidth(this.getActiveMenu().get(this.activeSliderMenuID).getWidth() + this.getActiveMenu().get(this.activeSliderMenuID).getPosX());
                this.getActiveMenu().get(this.activeSliderMenuID).setPosX(0);
            }
        }
        else {
            this.getActiveMenu().get(this.activeSliderMenuID).setWidth(nPosX + this.iSliderMenuStartPosX);
            if (this.getActiveMenu().get(this.activeSliderMenuID).getPosX() + this.getActiveMenu().get(this.activeSliderMenuID).getWidth() > CFG.GAME_WIDTH) {
                this.getActiveMenu().get(this.activeSliderMenuID).setWidth(CFG.GAME_WIDTH - this.getActiveMenu().get(this.activeSliderMenuID).getPosX());
            }
        }
    }
    
    private final boolean actionDown_MenuTitle(final int nPosX, final int nPosY) {
        try {
            for (int i = 0; i < this.getActiveMenu().size(); ++i) {
                if (this.getActiveMenu().get(this.getActiveOrder(i)).getVisible() && this.getActiveMenu().get(this.getActiveOrder(i)).getTitle() != null && nPosX >= this.getActiveMenu().get(this.getActiveOrder(i)).getPosX() && nPosX <= this.getActiveMenu().get(this.getActiveOrder(i)).getPosX() + this.getActiveMenu().get(this.getActiveOrder(i)).getWidth() && nPosY >= this.getActiveMenu().get(this.getActiveOrder(i)).getPosY() - this.getActiveMenu().get(this.getActiveOrder(i)).getTitle().getHeight() && nPosY <= this.getActiveMenu().get(this.getActiveOrder(i)).getPosY()) {
                    this.menu_MoveByTitleMode = true;
                    this.activeSliderMenuID = this.getActiveOrder(i);
                    this.iSliderMenuStartPosX = this.getActiveMenu().get(this.activeSliderMenuID).getPosX() - nPosX;
                    this.iSliderMenuStartPosY = this.getActiveMenu().get(this.activeSliderMenuID).getPosY() - nPosY;
                    this.iSliderMenuActionDownPosX = nPosX;
                    this.iSliderMenuActionDownPosY = nPosY;
                    this.setOrderOfMenu(this.getActiveOrder(i));
                    this.getActiveMenu().get(this.activeSliderMenuID).stopScrolling();
                    return true;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        return false;
    }
    
    private final void actionMove_MenuTitle(final int nPosX, final int nPosY) {
        if (this.getActiveMenu().get(this.activeSliderMenuID).getMoveable()) {
            if (nPosX + this.iSliderMenuStartPosX + this.getActiveMenu().get(this.activeSliderMenuID).getWidth() - this.getActiveMenu().get(this.activeSliderMenuID).getMinWidth() > 0 && nPosX + this.iSliderMenuStartPosX < CFG.GAME_WIDTH - this.getActiveMenu().get(this.activeSliderMenuID).getMinWidth()) {
                this.getActiveMenu().get(this.activeSliderMenuID).setPosX(nPosX + this.iSliderMenuStartPosX);
                this.getActiveMenu().get(this.activeSliderMenuID).updateMenuPosX(this.getActiveMenu().get(this.activeSliderMenuID).getMenuPosX() + (nPosX - this.iSliderMenuActionDownPosX));
                this.iSliderMenuActionDownPosX = nPosX;
            }
            if (nPosY + this.iSliderMenuStartPosY > this.getActiveMenu().get(this.activeSliderMenuID).getMinHeight() && nPosY + this.iSliderMenuStartPosY < CFG.GAME_HEIGHT - this.getActiveMenu().get(this.activeSliderMenuID).getMinHeight()) {
                this.getActiveMenu().get(this.activeSliderMenuID).setPosY(nPosY + this.iSliderMenuStartPosY);
                this.getActiveMenu().get(this.activeSliderMenuID).updateMenuPosY(this.getActiveMenu().get(this.activeSliderMenuID).getMenuPosY() + (nPosY - this.iSliderMenuActionDownPosY));
                this.iSliderMenuActionDownPosY = nPosY;
            }
        }
    }
    
    private final void actionMove_ScrollPosX(final int nPosX, final int nPosY) {
        if (this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).getScrollable()) {
            if (this.updateSliderMenuPosX) {
                this.iSliderMenuStartPosX = this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).getScrollPosX() - nPosX;
                this.updateSliderMenuPosX = false;
            }
            else {
                this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).setScrollPosX(this.iSliderMenuStartPosX + nPosX);
            }
        }
    }
    
    private final void actionMove_ScrollPosY(final int nPosX, final int nPosY) {
        if (this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).getScrollable()) {
            if (this.updateSliderMenuPosY) {
                this.iSliderMenuStartPosY = this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).getScrollPosY() - nPosY;
                this.updateSliderMenuPosY = false;
            }
            else {
                this.getActiveMenu().get(this.activeSliderMenuID).getMenuElement(this.activeMenuElementID).setScrollPosY(this.iSliderMenuStartPosY + nPosY);
            }
        }
    }
    
    private final boolean actionDown_CloseMenu(final int nPosX, final int nPosY) {
        try {
            for (int i = 0; i < this.getActiveMenu().size(); ++i) {
                if (this.getActiveMenu().get(this.getActiveOrder(i)).getVisible() && this.getActiveMenu().get(this.getActiveOrder(i)).getCloseable() && nPosX >= this.getActiveMenu().get(this.getActiveOrder(i)).menuClose.getCloseMenu_PosX() && nPosX <= this.getActiveMenu().get(this.getActiveOrder(i)).menuClose.getCloseMenu_PosX() + this.getActiveMenu().get(this.getActiveOrder(i)).menuClose.getCloseMenu_Width() && nPosY >= this.getActiveMenu().get(this.getActiveOrder(i)).menuClose.getCloseMenu_PosY() && nPosY <= this.getActiveMenu().get(this.getActiveOrder(i)).menuClose.getCloseMenu_PosY() + this.getActiveMenu().get(this.getActiveOrder(i)).menuClose.getCloseMenu_Height()) {
                    this.activeSliderMenuID = i;
                    return this.closeMenuMode = true;
                }
            }
        }
        catch (final IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        }
        catch (final NullPointerException ex2) {
            CFG.exceptionStack(ex2);
        }
        return false;
    }
    
    private final void actionUp_CloseMenu(final int nPosX, final int nPosY) {
        if (this.getActiveMenu().get(this.getActiveOrder(this.activeSliderMenuID)).getVisible() && this.getActiveMenu().get(this.getActiveOrder(this.activeSliderMenuID)).getCloseable() && nPosX >= this.getActiveMenu().get(this.getActiveOrder(this.activeSliderMenuID)).menuClose.getCloseMenu_PosX() && nPosX <= this.getActiveMenu().get(this.getActiveOrder(this.activeSliderMenuID)).menuClose.getCloseMenu_PosX() + this.getActiveMenu().get(this.getActiveOrder(this.activeSliderMenuID)).menuClose.getCloseMenu_Width() && nPosY >= this.getActiveMenu().get(this.getActiveOrder(this.activeSliderMenuID)).menuClose.getCloseMenu_PosY() && nPosY <= this.getActiveMenu().get(this.getActiveOrder(this.activeSliderMenuID)).menuClose.getCloseMenu_PosY() + this.getActiveMenu().get(this.getActiveOrder(this.activeSliderMenuID)).menuClose.getCloseMenu_Height()) {
            this.getActiveMenu().get(this.getActiveOrder(this.activeSliderMenuID)).actionCloseMenu();
        }
    }
    
    private final void actionMoveSlider(final int nPosX, final int nPosY) {
        if (this.getActiveMenuElement().getClickable()) {
            if (nPosX >= this.getActiveMenuElement().getPosX() + this.getActiveMenu().get(this.activeSliderMenuID).getPosX() && nPosX <= this.getActiveMenuElement().getPosX() + this.getActiveMenuElement().getWidth() + this.getActiveMenu().get(this.activeSliderMenuID).getPosX()) {
                this.getActiveMenuElement().updateSlider(nPosX - this.getActiveMenu().get(this.activeSliderMenuID).getPosX());
            }
            else if (nPosX < this.getActiveMenuElement().getPosX() + this.getActiveMenu().get(this.activeSliderMenuID).getPosX()) {
                this.getActiveMenuElement().updateSlider(this.getActiveMenuElement().getPosX());
            }
            else {
                this.getActiveMenuElement().updateSlider(this.getActiveMenuElement().getPosX() + this.getActiveMenuElement().getWidth());
            }
            this.getActiveMenu().get(this.activeSliderMenuID).actionElement(this.activeMenuElementID);
        }
    }
    
    public final void setOrderOfMenu_InGame() {
        this.setOrderOfMenu(0);
        this.setOrderOfMenu(this.IN_GAME_MAPMODES);
        this.setOrderOfMenu(this.IN_GAME_COURT_OPTIONS2);
        MenuManager.orderOfMenuInGame = true;
    }
    
    public final void setOrderOfMenu_InGameBattle() {
        this.setOrderOfMenu(this.IN_GAME_BATTLE);
        this.setOrderOfMenu(this.IN_GAME_BATTLE_ARMY);
        this.setOrderOfMenu(this.IN_GAME_BATTLE_ARMY_DEFENDERS);
    }
    
    public final void setOrderOfMenu_Scenarios() {
        this.setOrderOfMenu(0);
        this.setOrderOfMenu(1);
    }
    
    public final void setOrderOfMenu_InGameProvinceInfo() {
        this.setOrderOfMenu(this.IN_GAME_PROVINCE_INFO);
        this.setOrderOfMenu(this.IN_GAME_PROVINCE_INFO_BUILDINGS);
        this.setOrderOfMenu(this.IN_GAME_PROVINCE_INFO_ARMY);
    }
    
    public final void setOrderOfMenu_InGameCourt() {
        this.setOrderOfMenu(this.IN_GAME_COURT);
        this.setOrderOfMenu(this.IN_GAME_COURT_OPTIONS);
        this.setOrderOfMenu(this.IN_GAME_COURT_OPTIONS2);
    }
    
    public final void setOrderOfMenu_InGameProvinceArmy() {
        this.setOrderOfMenu(this.IN_GAME_PROVINCE_ARMY);
        this.setOrderOfMenu(this.IN_GAME_PROVINCE_ARMY_UNITS);
        this.setOrderOfMenu(this.IN_GAME_PROVINCE_ARMY_TOP_BAR);
        this.setOrderOfMenu(this.IN_GAME_PROVINCE_ARMY_MOVE);
    }
    
    public final void setOrderOfMenu_InGameBuildings() {
        this.setOrderOfMenu(this.IN_GAME_BUILDINGS);
        this.setOrderOfMenu(this.IN_GAME_BUILDINGS_GROUP_0);
        this.setOrderOfMenu(this.IN_GAME_BUILDINGS_GROUP_1);
        this.setOrderOfMenu(this.IN_GAME_BUILDINGS_GROUP_2);
        this.setOrderOfMenu(this.IN_GAME_BUILDINGS_GROUP_3);
    }
    
    public final void setOrderOfMenu_InGameLegacies() {
        this.setOrderOfMenu(this.IN_GAME_LEGACIES_EMPTY);
        this.setOrderOfMenu(0);
        this.setOrderOfMenu(this.IN_GAME_LEGACIES_INFO);
    }
    
    public final void setOrderOfMenu_MainMenu_Stats() {
        this.setOrderOfMenu(1);
        this.setOrderOfMenu(0);
    }
    
    public final void setOrderOfMenu_InGameEscape() {
        this.setOrderOfMenu(this.IN_GAME_ESCAPE_EMPTY);
        this.setOrderOfMenu(this.IN_GAME_ESCAPE);
    }
    
    public final void setOrderOfMenu_InGameGoods() {
        this.setOrderOfMenu(this.IN_GAME_GOODS_EMPTY);
        this.setOrderOfMenu(this.IN_GAME_GOODS);
    }
    
    public final int getActiveMenuElementID() {
        return this.activeMenuElementID;
    }
    
    public final int getActiveSliderMenuID() {
        return this.activeSliderMenuID;
    }
    
    public final void setActiveSliderMenuID(final int activeSliderMenuID) {
        this.activeSliderMenuID = activeSliderMenuID;
    }
    
    public final void setActiveMenuElementID(final int activeMenuElementID) {
        this.activeMenuElementID = activeMenuElementID;
    }
    
    public final void setUpdateSliderMenuPosY(final boolean updateSliderMenuPosY) {
        this.updateSliderMenuPosY = updateSliderMenuPosY;
    }
    
    public final void setUpdateSliderMenuPosX(final boolean updateSliderMenuPosX) {
        this.updateSliderMenuPosX = updateSliderMenuPosX;
    }
    
    public final void setPieChartMode(final boolean pieChartMode) {
        this.pieChartMode = pieChartMode;
    }
    
    public final boolean getTextScrollableMode() {
        return this.textScrollableMode;
    }
    
    public final void setTextScrollableMode(final boolean textScrollableMode) {
        this.textScrollableMode = textScrollableMode;
    }
    
    public final boolean getMenu_MoveInnerElements() {
        return this.menu_MoveInnerElements;
    }
    
    public final void setMenu_MoveInnerElements(final boolean menu_MoveInnerElements) {
        this.menu_MoveInnerElements = menu_MoveInnerElements;
    }
    
    public final void setBackAnimation(final boolean backAnimation) {
        this.backAnimation = backAnimation;
    }
    
    public final boolean getMoveMenu_ByTitleMode() {
        return this.menu_MoveByTitleMode;
    }
    
    public final void setMenu_MoveByTitleMode(final boolean menu_MoveByTitleMode) {
        this.menu_MoveByTitleMode = menu_MoveByTitleMode;
    }
    
    public final boolean getMenuResizeLEFT() {
        return this.menuResizeLEFT;
    }
    
    public final boolean getCloseMenuMode() {
        return this.closeMenuMode;
    }
    
    public final void setCloseMenuMode(final boolean closeMenuMode) {
        this.closeMenuMode = closeMenuMode;
    }
    
    public final int getFromViewID() {
        return this.fromViewID;
    }
    
    public final ColorPicker getColorPicker() {
        return this.colorPicker;
    }
    
    public final boolean getMenuResizeMode() {
        return this.menuResizeMode;
    }
    
    public final void setMenu_ResizeMode(final boolean menuResizeMode) {
        this.menuResizeMode = menuResizeMode;
    }
    
    public final boolean getColorPickerMode() {
        return this.colorPickerMode;
    }
    
    public final void setColorPickerMode(final boolean colorPickerMode) {
        this.colorPickerMode = colorPickerMode;
    }
    
    public final boolean getInGame() {
        return this.viewID == this.IN_GAME || this.getInGameHideUI();
    }
    
    public final boolean getInGameLegacies() {
        return this.viewID == this.IN_GAME_LEGACIES;
    }
    
    public final boolean getInGameHideUI() {
        return this.viewID == this.IN_GAME_HIDE_UI;
    }
    
    public final boolean getInInitGameMenu() {
        return this.viewID == this.INIT_GAME_MENU;
    }
    
    public final boolean getInInitGame_Menus() {
        return this.viewID == this.INIT_GAME_MENU_SELECT_MAP || this.viewID == this.INIT_GAME_MENU_LANGUAGE;
    }
    
    public final boolean getInLoadScenario() {
        return this.viewID == this.LOAD_STUFF;
    }
    
    public final boolean getInMainMenu() {
        return this.viewID == this.MAINMENU || this.viewID == this.MAINMENU_STATS;
    }
    
    public final boolean getInLoadGamesList() {
        return this.viewID == this.LOAD_GAMES_LIST;
    }
    
    public final boolean getInSettingsMenu() {
        return this.viewID == this.SETTINGS;
    }
    
    public final boolean getInScenarios_NewGame() {
        return this.viewID == this.SCENARIOS;
    }
    
    public final boolean getInEditorGameCivsEdit() {
        return this.viewID == this.EDITOR_GAMECIVS_EDIT;
    }
    
    public final boolean getInEditorCreateCiv() {
        return this.viewID == this.CREATE_CIV;
    }
    
    public final boolean getInMapEditorGrowthRate() {
        return this.viewID == this.EDITOR_MAPS_EDIT_GROWTH_RATE;
    }
    
    public final boolean getInNewGame_Scenarios() {
        return this.viewID == this.SCENARIOS;
    }
    
    public final boolean getInNewGame_ScenariosCampaign() {
        return this.viewID == this.SCENARIOS_CAMPAIGN;
    }
    
    public final boolean getInMapEditorEconomy() {
        return this.viewID == this.EDITOR_MAPS_EDIT_ECONOMY;
    }
    
    public final boolean getInMapEditorLines() {
        return this.viewID == this.EDITOR_MAPS_EDIT_LINES;
    }
    
    public final boolean getInMapEditorWaves() {
        return this.viewID == this.EDITOR_MAPS_EDIT_WAVES;
    }
    
    public final boolean getInMapEditorSeaProvinces() {
        return this.viewID == this.EDITOR_MAPS_EDIT_SEA_PROVINCES;
    }
    
    public final boolean getInMapEditorArmyPosition() {
        return this.viewID == this.EDITOR_MAPS_EDIT_ARMY_POSITION;
    }
    
    public final boolean getInMapEditorProvinceNamePoints() {
        return this.viewID == this.EDITOR_MAPS_EDIT_PROVINCE_NAMES;
    }
    
    public final boolean getInScenarioEditor_CreateAllianceEdit() {
        return this.viewID == this.SCENARIO_CREATE_ALLIANCE_EDIT;
    }
    
    public final boolean getInMapEditorTerrain() {
        return this.viewID == this.EDITOR_MAPS_EDIT_TERRAIN;
    }
    
    public final boolean getInScenarioEditorTechnologiesCivs() {
        return this.viewID == this.SCENARIO_TECHNOLOGIES_CIVS;
    }
    
    public final boolean getInScenarioEditorArmies() {
        return this.viewID == this.SCENARIO_ARMIES;
    }
    
    public final boolean getInScenarioEditorGovernment() {
        return this.viewID == this.SCENARIO_GOVERNMENT;
    }
    
    public final boolean getInScenarioEditorBuildings() {
        return this.viewID == this.SCENARIO_BUILDINGS;
    }
    
    public final boolean getInScenarioEditorRelations() {
        return this.viewID == this.SCENARIO_RELATIONS;
    }
    
    public final boolean getInScenarioEditorAlliances() {
        return this.viewID == this.SCENARIO_ALLIANCES;
    }
    
    public final boolean getInScenarioEditorVassals() {
        return this.viewID == this.SCENARIO_VASSALS;
    }
    
    public final boolean getInScenarioEditorTruces() {
        return this.viewID == this.SCENARIO_TRUCES;
    }
    
    public final boolean getInScenarioEditorDeclareWar() {
        return this.viewID == this.SCENARIO_DECLARE_WAR;
    }
    
    public final boolean getInScenarioEditorNonAggression() {
        return this.viewID == this.SCENARIO_NON_AGGRESSION;
    }
    
    public final boolean getInScenarioEditorMilitaryAccess() {
        return this.viewID == this.SCENARIO_MILITARY_ACCESS;
    }
    
    public final boolean getInScenarioEditorDefensive() {
        return this.viewID == this.SCENARIO_DEFENSIVE;
    }
    
    public final boolean getInScenarioEditorGuarantee() {
        return this.viewID == this.SCENARIO_GUARANTEE;
    }
    
    public final boolean getInMapEditorResource() {
        return this.viewID == this.EDITOR_MAPS_EDIT_RESOURCE;
    }
    
    public final boolean getInEditorSelectProvinces() {
        return this.viewID == this.EDITOR_SELECT_PROVINCES;
    }
    
    public final boolean getInMapEditorContinents() {
        return this.viewID == this.EDITOR_MAPS_EDIT_CONTINENTS;
    }
    
    public final boolean getInMapEditorOptimizationRegions() {
        return this.viewID == this.EDITOR_MAPS_EDIT_OPTIMIZATION_REGIONS;
    }
    
    public final boolean getInMapEditorGeoRegions() {
        return this.viewID == this.EDITOR_MAPS_EDIT_GEO_REGION;
    }
    
    public final boolean getInMapEditorPortPosition() {
        return this.viewID == this.EDITOR_MAPS_EDIT_PORT_POSITION;
    }
    
    public final boolean getInMapEditorProvinceConnections() {
        return this.viewID == this.EDITOR_MAPS_EDIT_PROVINCE_CONNECTIONS;
    }
    
    public final boolean getInNewGame() {
        return this.viewID == this.NEW_GAME;
    }
    
    public final boolean getInGameLost() {
        return this.viewID == this.GAME_LOST;
    }
    
    public final boolean getInCloudsMenu() {
        return this.viewID == this.CLOUDS_MENU;
    }
    
    public final boolean getInScenariosList() {
        return this.viewID == this.EDITOR_SCENARIOS_LIST;
    }
    
    public final boolean getInScenarioWasteland() {
        return this.viewID == this.SCENARIO_WASTELAND;
    }
    
    public final boolean getInScenarioWastelandContinents() {
        return this.viewID == this.SCENARIO_WASTELAND_CONTINENTS;
    }
    
    public final boolean getInPrintMap() {
        return this.viewID == this.PRINT_A_MAP;
    }
    
    public final boolean getInScenarioCivilizations() {
        return this.viewID == this.SCENARIO_CIVILIZATIONS;
    }
    
    public final boolean getInScenarioAssign() {
        return this.viewID == this.SCENARIO_ASSIGN;
    }
    
    public final boolean getInScenarioAssignInGame() {
        return this.viewID == this.SCENARIO_ASSIGN_IN_GAME;
    }
    
    public final boolean getInScenarioCores() {
        return this.viewID == this.SCENARIO_CORES;
    }
    
    public final boolean getInScenarioReligion() {
        return this.viewID == this.SCENARIO_RELIGION;
    }
    
    public final boolean getInScenarioSettings() {
        return this.viewID == this.SCENARIO_SETTINGS;
    }
    
    public final boolean getInEditorFormableCiv() {
        return this.viewID == this.EDITOR_MAPS_EDIT_FORMABLE_CIV;
    }
    
    public final void rebuildScenarioReligionList() {
        final int nX = this.menus.get(this.SCENARIO_RELIGION).get(this.SCENARIO_RELIGION_LIST).getPosX();
        final int nY = this.menus.get(this.SCENARIO_RELIGION).get(this.SCENARIO_RELIGION_LIST).getPosY();
        final int mX = this.menus.get(this.SCENARIO_RELIGION).get(this.SCENARIO_RELIGION_LIST).getMenuPosX();
        final int mY = this.menus.get(this.SCENARIO_RELIGION).get(this.SCENARIO_RELIGION_LIST).getMenuPosY();
        this.menus.get(this.SCENARIO_RELIGION).get(this.SCENARIO_RELIGION_LIST).setVisible(false);
        this.menus.get(this.SCENARIO_RELIGION).set(this.SCENARIO_RELIGION_LIST, (Object)new ScenarioReligion_List());
        this.menus.get(this.SCENARIO_RELIGION).get(this.SCENARIO_RELIGION_LIST).setPosX(nX);
        this.menus.get(this.SCENARIO_RELIGION).get(this.SCENARIO_RELIGION_LIST).setPosY(nY);
        this.menus.get(this.SCENARIO_RELIGION).get(this.SCENARIO_RELIGION_LIST).setMenuPosX(mX);
        this.menus.get(this.SCENARIO_RELIGION).get(this.SCENARIO_RELIGION_LIST).setMenuPosY(mY);
    }
    
    public final void rebuildScenarioCoresList() {
        final int nX = this.menus.get(this.SCENARIO_CORES).get(this.SCENARIO_CORES_LIST).getPosX();
        final int nY = this.menus.get(this.SCENARIO_CORES).get(this.SCENARIO_CORES_LIST).getPosY();
        final int mX = this.menus.get(this.SCENARIO_CORES).get(this.SCENARIO_CORES_LIST).getMenuPosX();
        final int mY = this.menus.get(this.SCENARIO_CORES).get(this.SCENARIO_CORES_LIST).getMenuPosY();
        this.menus.get(this.SCENARIO_CORES).get(this.SCENARIO_CORES_LIST).setVisible(false);
        this.menus.get(this.SCENARIO_CORES).set(this.SCENARIO_CORES_LIST, (Object)new ScenarioCores_List());
        this.menus.get(this.SCENARIO_CORES).get(this.SCENARIO_CORES_LIST).setPosX(nX);
        this.menus.get(this.SCENARIO_CORES).get(this.SCENARIO_CORES_LIST).setPosY(nY);
        this.menus.get(this.SCENARIO_CORES).get(this.SCENARIO_CORES_LIST).setMenuPosX(mX);
        this.menus.get(this.SCENARIO_CORES).get(this.SCENARIO_CORES_LIST).setMenuPosY(mY);
    }
    
    public final void rebuildScenarioCoresList_InProvince() {
        final int nX = this.menus.get(this.SCENARIO_CORES).get(this.SCENARIO_CORES_LIST_IN_PROVINCE).getPosX();
        final int nY = this.menus.get(this.SCENARIO_CORES).get(this.SCENARIO_CORES_LIST_IN_PROVINCE).getPosY();
        final int mX = this.menus.get(this.SCENARIO_CORES).get(this.SCENARIO_CORES_LIST_IN_PROVINCE).getMenuPosX();
        final int mY = this.menus.get(this.SCENARIO_CORES).get(this.SCENARIO_CORES_LIST_IN_PROVINCE).getMenuPosY();
        this.menus.get(this.SCENARIO_CORES).get(this.SCENARIO_CORES_LIST_IN_PROVINCE).setVisible(false);
        this.menus.get(this.SCENARIO_CORES).set(this.SCENARIO_CORES_LIST_IN_PROVINCE, (Object)new ScenarioCores_InProvinces());
        this.menus.get(this.SCENARIO_CORES).get(this.SCENARIO_CORES_LIST_IN_PROVINCE).setPosX(nX);
        this.menus.get(this.SCENARIO_CORES).get(this.SCENARIO_CORES_LIST_IN_PROVINCE).setPosY(nY);
        this.menus.get(this.SCENARIO_CORES).get(this.SCENARIO_CORES_LIST_IN_PROVINCE).setMenuPosX(mX);
        this.menus.get(this.SCENARIO_CORES).get(this.SCENARIO_CORES_LIST_IN_PROVINCE).setMenuPosY(mY);
    }
    
    public final void rebuildScenarioCivilizationsList() {
        final int nX = this.menus.get(this.SCENARIO_CIVILIZATIONS).get(this.SCENARIO_CIVILIZATIONS_LIST).getPosX();
        final int nY = this.menus.get(this.SCENARIO_CIVILIZATIONS).get(this.SCENARIO_CIVILIZATIONS_LIST).getPosY();
        final int mX = this.menus.get(this.SCENARIO_CIVILIZATIONS).get(this.SCENARIO_CIVILIZATIONS_LIST).getMenuPosX();
        final int mY = this.menus.get(this.SCENARIO_CIVILIZATIONS).get(this.SCENARIO_CIVILIZATIONS_LIST).getMenuPosY();
        this.menus.get(this.SCENARIO_CIVILIZATIONS).get(this.SCENARIO_CIVILIZATIONS_LIST).setVisible(false);
        this.menus.get(this.SCENARIO_CIVILIZATIONS).set(this.SCENARIO_CIVILIZATIONS_LIST, (Object)new ScenarioCivilizationsList());
        this.menus.get(this.SCENARIO_CIVILIZATIONS).get(this.SCENARIO_CIVILIZATIONS_LIST).setPosX(nX);
        this.menus.get(this.SCENARIO_CIVILIZATIONS).get(this.SCENARIO_CIVILIZATIONS_LIST).setPosY(nY);
        this.menus.get(this.SCENARIO_CIVILIZATIONS).get(this.SCENARIO_CIVILIZATIONS_LIST).setMenuPosX(mX);
        this.menus.get(this.SCENARIO_CIVILIZATIONS).get(this.SCENARIO_CIVILIZATIONS_LIST).setMenuPosY(mY);
    }
    
    public final void rebuildEditorCivSelect() {
        final int nX = this.menus.get(this.EDITOR_MAPS_EDIT_CIV_SELECT).get(0).getPosX();
        final int nY = this.menus.get(this.EDITOR_MAPS_EDIT_CIV_SELECT).get(0).getPosY();
        final int mX = this.menus.get(this.EDITOR_MAPS_EDIT_CIV_SELECT).get(0).getMenuPosX();
        final int mY = this.menus.get(this.EDITOR_MAPS_EDIT_CIV_SELECT).get(0).getMenuPosY();
        this.menus.get(this.EDITOR_MAPS_EDIT_CIV_SELECT).get(0).setVisible(false);
        this.menus.get(this.EDITOR_MAPS_EDIT_CIV_SELECT).set(0, (Object)new EditorMap_CivSelect());
        this.menus.get(this.EDITOR_MAPS_EDIT_CIV_SELECT).get(0).setPosX(nX);
        this.menus.get(this.EDITOR_MAPS_EDIT_CIV_SELECT).get(0).setPosY(nY);
        this.menus.get(this.EDITOR_MAPS_EDIT_CIV_SELECT).get(0).setMenuPosX(mX);
        this.menus.get(this.EDITOR_MAPS_EDIT_CIV_SELECT).get(0).setMenuPosY(mY);
    }
    
    public final Menu rebuildEditorMapProvinceConnectionsList() {
        return (Menu)this.menus.get(this.EDITOR_MAPS_EDIT_PROVINCE_CONNECTIONS).set(1, (Object)new EditorMapProvinceConnectionsList());
    }
    
    public final void rebuildSettingsAudio() {
        this.menus.get(this.SETTINGS).set(1, (Object)new InGame_Audio());
        this.menus.get(this.SETTINGS).get(1).setVisible(true);
        this.setOrderOfMenu(1);
    }
    
    public final boolean getVisibleSettingsAudio() {
        return this.menus.get(this.SETTINGS).get(1).getVisible();
    }
    
    public final void setVisibleSettingsAudio(final boolean visible) {
        this.menus.get(this.SETTINGS).get(1).setVisible(visible);
    }
    
    public final int getInGame_MapModesPosY() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_MAPMODES).getPosY();
    }
    
    public final boolean getVisibleInGame_Escape() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_ESCAPE).getVisible();
    }
    
    public final void setVisibleInGame_Escape(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_ESCAPE_EMPTY).setVisible(visible);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_ESCAPE).setVisible(visible);
        if (visible) {
            if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_DEFAULT) {
                Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
            }
            this.setOrderOfMenu_InGameEscape();
        }
        Game.mapBG.updateActiveMapBGShader();
    }
    
    public final void setVisibleInGame_ProvinceArmy(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY).setVisible(visible);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY_UNITS).setVisible(visible);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY_TOP_BAR).setVisible(visible);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY_MOVE).setVisible(visible);
        if (visible) {
            this.hideCourtCiv();
            this.setOrderOfMenu(this.IN_GAME_PROVINCE_ARMY);
            this.setOrderOfMenu(this.IN_GAME_PROVINCE_ARMY_UNITS);
            this.setOrderOfMenu(this.IN_GAME_PROVINCE_ARMY_TOP_BAR);
            this.setOrderOfMenu(this.IN_GAME_PROVINCE_ARMY_MOVE);
        }
    }
    
    public final void rebuildInGame_ProvinceArmy_HideMenus() {
        if (this.getVisibleInGame_ProvinceInfo()) {
            this.setVisibleInGame_ProvinceInfo(false);
        }
        if (this.getVisibleInGame_RecruitArmy()) {
            this.setVisibleInGame_RecruitArmy(false);
        }
        if (this.getVisibleInGame_Armies()) {
            this.setVisibleInGame_Armies(false);
        }
        if (this.getVisibleInGame_Buildings()) {
            this.setVisibleInGame_Buildings(false, false);
        }
        if (this.getVisibleInGame_Battle()) {
            this.setVisibleInGame_Battle(false);
        }
        if (this.getVisibleInGame_Siege()) {
            this.setVisibleInGame_Siege(false);
        }
        if (this.getVisibleInGame_War()) {
            this.setVisibleInGame_War(false);
        }
        if (this.getVisibleInGame_Peace()) {
            this.setVisibleInGame_Peace(false);
        }
        if (this.getVisibleInGame_DisbandArmy()) {
            this.setVisibleInGame_DisbandUnits(false);
        }
        if (this.getVisibleInGame_ReorganizeUnits()) {
            this.setVisibleInGame_ReorganizeUnits(false);
        }
        this.setOrderOfMenu_InGameProvinceArmy();
        if (this.getVisibleInGame_TechnologyChoose()) {
            this.setVisibleInGame_TechnologyChoose(false);
        }
        if (this.getVisibleDeclareWar()) {
            this.setVisibleInGame_PopUp(false);
        }
        if (this.getVisible_SpecialAlliance()) {
            this.setVisibleInGame_PopUp(false);
        }
        if (this.getVisibleFormCiv() || this.getVisibleSellProvince() || this.getVisibleRevolutions()) {
            this.setVisibleInGame_PopUp(false);
        }
        if (this.getVisibleInGame_Nukes()) {
            this.setVisibleInGame_Nukes(false);
        }
        if (this.getVisibleInGame_TechnologyTree()) {
            this.setVisibleInGame_TechnologyTree(false);
        }
        if (this.getVisibleInGame_CurrentSituation()) {
            this.setVisibleInGame_CurrentSituation(false);
        }
    }
    
    public final void rebuildInGame_ProvinceArmy() {
        this.rebuildInGame_ProvinceArmy(false, true);
    }
    
    public final void rebuildInGame_ProvinceArmy_RegroupArmy() {
        if (Game.regroupArmyMode) {
            this.menus.get(this.IN_GAME).set(this.IN_GAME_PROVINCE_ARMY_UNITS, (Object)new InGame_ProvinceArmy_Regroup());
            this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY_UNITS).setVisible(true);
        }
        else {
            this.menus.get(this.IN_GAME).set(this.IN_GAME_PROVINCE_ARMY_UNITS, (Object)new InGame_ProvinceArmyUnits());
            this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY_UNITS).setVisible(true);
        }
    }
    
    public final void rebuildInGame_ProvinceArmy_InvasionArmy() {
        if (Game.invasionArmyMode) {
            this.menus.get(this.IN_GAME).set(this.IN_GAME_PROVINCE_ARMY_UNITS, (Object)new InGame_ProvinceArmy_Invasion());
            this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY_UNITS).setVisible(true);
        }
        else {
            this.menus.get(this.IN_GAME).set(this.IN_GAME_PROVINCE_ARMY_UNITS, (Object)new InGame_ProvinceArmyUnits());
            this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY_UNITS).setVisible(true);
        }
    }
    
    public final void rebuildInGame_ProvinceArmy(final boolean keepMenuPos, final boolean updateOrder) {
        if (Game.activeArmySize > 0) {
            final boolean odlVisible = this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY).getVisible();
            this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY).dispose();
            int nX = 0;
            final int nY = 0;
            int mX = 0;
            int mY = 0;
            int nX2 = 0;
            final int nY2 = 0;
            int mX2 = 0;
            int mY2 = 0;
            int nX3 = 0;
            final int nY3 = 0;
            int mX3 = 0;
            int mY3 = 0;
            if (keepMenuPos && odlVisible) {
                nX = this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY).getPosX();
                mX = this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY).getMenuPosX();
                mY = this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY).getMenuPosY();
                nX2 = this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY_UNITS).getPosX();
                mX2 = this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY_UNITS).getMenuPosX();
                mY2 = this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY_UNITS).getMenuPosY();
                nX3 = this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY_TOP_BAR).getPosX();
                mX3 = this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY_TOP_BAR).getMenuPosX();
                mY3 = this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY_TOP_BAR).getMenuPosY();
            }
            this.menus.get(this.IN_GAME).set(this.IN_GAME_PROVINCE_ARMY, (Object)new InGame_ProvinceArmy());
            this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY).setVisible(true);
            if (Game.invasionArmyMode) {
                this.rebuildInGame_ProvinceArmy_InvasionArmy();
            }
            else {
                this.rebuildInGame_ProvinceArmy_RegroupArmy();
            }
            this.menus.get(this.IN_GAME).set(this.IN_GAME_PROVINCE_ARMY_TOP_BAR, (Object)new InGame_ProvinceArmyTopBar());
            this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY_TOP_BAR).setVisible(true);
            this.menus.get(this.IN_GAME).set(this.IN_GAME_PROVINCE_ARMY_MOVE, (Object)new InGame_ProvinceArmy_Move());
            Game.hoverManager.rebuildHoverAfterRebuildMenu();
            if (keepMenuPos && odlVisible) {
                this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY).setPosX(nX);
                this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY).setMenuPosX(mX);
                this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY).setMenuPosY(mY);
                this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY_UNITS).setPosX(nX2);
                this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY_UNITS).setMenuPosX(mX2);
                this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY_UNITS).setMenuPosY(mY2);
                this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY_TOP_BAR).setPosX(nX3);
                this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY_TOP_BAR).setMenuPosX(mX3);
                this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY_TOP_BAR).setMenuPosY(mY3);
            }
            if (odlVisible) {
                InGame_ProvinceArmy.lTime = 0L;
            }
            if (updateOrder) {
                this.hideCourtCiv();
                this.setOrderOfMenu_InGameProvinceArmy();
                if (this.getVisibleInGame_GoodsMarket()) {
                    this.setVisibleInGame_GoodsMarket(false);
                }
            }
        }
        else {
            this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY).setVisible(false);
            this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY_UNITS).setVisible(false);
            this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY_TOP_BAR).setVisible(false);
            this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY_MOVE).setVisible(false);
        }
    }
    
    public final void rebuildInGame() {
        if (this.IN_GAME >= 0) {
            this.menus.get(this.IN_GAME).set(0, (Object)new InGame());
        }
    }
    
    public final boolean getVisibleInGame_Battle() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_BATTLE).getVisible();
    }
    
    public final boolean getVisibleInGame_Siege() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_SIEGE).getVisible();
    }
    
    public final boolean getVisibleInGame_War() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_WAR).getVisible();
    }
    
    public final boolean getVisibleInGame_Peace() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_PEACE).getVisible();
    }
    
    public final void hideCourtCiv() {
        this.setVisibleInGame_Court(false);
        this.setVisibleInGame_Civ(false);
        this.setVisibleInGame_Budget(false);
    }
    
    public final void setVisibleInGame_Battle(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_BATTLE).setVisible(visible);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_BATTLE_ARMY).setVisible(visible);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_BATTLE_ARMY_DEFENDERS).setVisible(visible);
        if (visible) {
            this.hideCourtCiv();
            this.setOrderOfMenu(this.IN_GAME_BATTLE);
            this.setOrderOfMenu(this.IN_GAME_BATTLE_ARMY);
            this.setOrderOfMenu(this.IN_GAME_BATTLE_ARMY_DEFENDERS);
        }
    }
    
    public final void setVisibleInGame_Siege(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_SIEGE).setVisible(visible);
        if (visible) {
            this.hideCourtCiv();
            this.setOrderOfMenu(this.IN_GAME_SIEGE);
        }
    }
    
    public final void setVisibleInGame_War(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_WAR).setVisible(visible);
        if (visible) {
            this.hideCourtCiv();
            this.setOrderOfMenu(this.IN_GAME_WAR);
        }
    }
    
    public final void setVisibleInGame_Peace(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_PEACE).setVisible(visible);
        if (visible) {
            this.hideCourtCiv();
            this.setOrderOfMenu(this.IN_GAME_PEACE);
        }
    }
    
    public final void showInGame_Battle_HideMenus() {
        if (this.getVisibleInGame_ProvinceInfo()) {
            this.setVisibleInGame_ProvinceInfo(false);
        }
        if (this.getVisibleInGame_RecruitArmy()) {
            this.setVisibleInGame_RecruitArmy(false);
        }
        if (this.getVisibleInGame_Buildings()) {
            this.setVisibleInGame_Buildings(false, false);
        }
        if (this.getVisibleInGame_ProvinceArmy()) {
            this.setVisibleInGame_ProvinceArmy(false);
        }
        if (this.getVisibleInGame_DisbandArmy()) {
            this.setVisibleInGame_DisbandUnits(false);
        }
        if (this.getVisibleInGame_ReorganizeUnits()) {
            this.setVisibleInGame_ReorganizeUnits(false);
        }
        if (this.getVisibleInGame_TechnologyChoose()) {
            this.setVisibleInGame_TechnologyChoose(false);
        }
        if (this.getVisibleInGame_TechnologyTree()) {
            this.setVisibleInGame_TechnologyTree(false);
        }
        if (this.getVisibleDeclareWar()) {
            this.setVisibleInGame_PopUp(false);
        }
        if (this.getVisible_SpecialAlliance()) {
            this.setVisibleInGame_PopUp(false);
        }
        if (this.getVisibleFormCiv() || this.getVisibleSellProvince() || this.getVisibleRevolutions()) {
            this.setVisibleInGame_PopUp(false);
        }
        if (this.getVisibleInGame_Nukes()) {
            this.setVisibleInGame_Nukes(false);
        }
        if (this.getVisibleInGame_CurrentSituation()) {
            this.setVisibleInGame_CurrentSituation(false);
        }
        if (this.getVisibleInGame_Armies()) {
            this.setVisibleInGame_Armies(false);
        }
    }
    
    public final void rebuildInGame_Siege() {
        final boolean odlVisible = this.menus.get(this.IN_GAME).get(this.IN_GAME_SIEGE).getVisible();
        this.menus.get(this.IN_GAME).get(this.IN_GAME_SIEGE).dispose();
        this.menus.get(this.IN_GAME).set(this.IN_GAME_SIEGE, (Object)new InGame_ProvinceSiege());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_SIEGE).setVisible(true);
        Game.hoverManager.rebuildHoverAfterRebuildMenu();
        this.setOrderOfMenu(this.IN_GAME_SIEGE);
        if (odlVisible) {
            InGame_ProvinceSiege.lTime = 0L;
        }
        this.hideCourtCiv();
        if (this.getVisibleInGame_GoodsMarket()) {
            this.setVisibleInGame_GoodsMarket(false);
        }
    }
    
    public final void rebuildInGame_War() {
        final boolean odlVisible = this.menus.get(this.IN_GAME).get(this.IN_GAME_WAR).getVisible();
        this.menus.get(this.IN_GAME).get(this.IN_GAME_WAR).dispose();
        this.menus.get(this.IN_GAME).set(this.IN_GAME_WAR, (Object)new InGame_War());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_WAR).setVisible(true);
        Game.hoverManager.rebuildHoverAfterRebuildMenu();
        this.setOrderOfMenu(this.IN_GAME_WAR);
        if (odlVisible) {
            InGame_War.lTime = 0L;
        }
        this.hideCourtCiv();
        this.showInGame_Battle_HideMenus();
        if (this.getVisibleInGame_GoodsMarket()) {
            this.setVisibleInGame_GoodsMarket(false);
        }
        if (this.getVisibleInGame_Battle()) {
            this.setVisibleInGame_Battle(false);
        }
        if (this.getVisibleInGame_Siege()) {
            this.setVisibleInGame_Siege(false);
        }
        if (this.getVisibleInGame_Peace()) {
            this.setVisibleInGame_Peace(false);
        }
    }
    
    public final void rebuildInGame_Peace() {
        final boolean odlVisible = this.menus.get(this.IN_GAME).get(this.IN_GAME_PEACE).getVisible();
        this.menus.get(this.IN_GAME).get(this.IN_GAME_PEACE).dispose();
        this.menus.get(this.IN_GAME).set(this.IN_GAME_PEACE, (Object)new InGame_Peace());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_PEACE).setVisible(true);
        Game.hoverManager.rebuildHoverAfterRebuildMenu();
        this.setOrderOfMenu(this.IN_GAME_PEACE);
        if (odlVisible) {
            InGame_War.lTime = 0L;
        }
        this.hideCourtCiv();
        this.showInGame_Battle_HideMenus();
        if (this.getVisibleInGame_GoodsMarket()) {
            this.setVisibleInGame_GoodsMarket(false);
        }
        if (this.getVisibleInGame_Battle()) {
            this.setVisibleInGame_Battle(false);
        }
        if (this.getVisibleInGame_Siege()) {
            this.setVisibleInGame_Siege(false);
        }
    }
    
    public final void rebuildInGame_Battle() {
        final boolean odlVisible = this.menus.get(this.IN_GAME).get(this.IN_GAME_BATTLE).getVisible();
        this.menus.get(this.IN_GAME).get(this.IN_GAME_BATTLE).dispose();
        try {
            this.menus.get(this.IN_GAME).set(this.IN_GAME_BATTLE, (Object)new InGame_Battle());
            this.menus.get(this.IN_GAME).get(this.IN_GAME_BATTLE).setVisible(InGame_Battle.battleID >= 0);
        }
        catch (final Exception ex) {
            this.menus.get(this.IN_GAME).get(this.IN_GAME_BATTLE).setVisible(false);
        }
        if (InGame_Battle.battleID >= 0) {
            try {
                this.menus.get(this.IN_GAME).set(this.IN_GAME_BATTLE_ARMY, (Object)new InGame_BattleArmy());
                this.menus.get(this.IN_GAME).get(this.IN_GAME_BATTLE_ARMY).setVisible(this.menus.get(this.IN_GAME).get(this.IN_GAME_BATTLE).getVisible());
                this.menus.get(this.IN_GAME).set(this.IN_GAME_BATTLE_ARMY_DEFENDERS, (Object)new InGame_BattleArmyDefenders());
                this.menus.get(this.IN_GAME).get(this.IN_GAME_BATTLE_ARMY_DEFENDERS).setVisible(this.menus.get(this.IN_GAME).get(this.IN_GAME_BATTLE).getVisible());
            }
            catch (final Exception ex) {
                this.menus.get(this.IN_GAME).get(this.IN_GAME_BATTLE_ARMY).setVisible(false);
                this.menus.get(this.IN_GAME).get(this.IN_GAME_BATTLE_ARMY_DEFENDERS).setVisible(false);
            }
        }
        else {
            this.menus.get(this.IN_GAME).get(this.IN_GAME_BATTLE_ARMY).setVisible(false);
            this.menus.get(this.IN_GAME).get(this.IN_GAME_BATTLE_ARMY_DEFENDERS).setVisible(false);
        }
        Game.hoverManager.rebuildHoverAfterRebuildMenu();
        this.setOrderOfMenu(this.IN_GAME_BATTLE);
        this.setOrderOfMenu(this.IN_GAME_BATTLE_ARMY);
        this.setOrderOfMenu(this.IN_GAME_BATTLE_ARMY_DEFENDERS);
        if (odlVisible) {
            InGame_Battle.lTime = 0L;
        }
        this.hideCourtCiv();
        if (this.getVisibleInGame_GoodsMarket()) {
            this.setVisibleInGame_GoodsMarket(false);
        }
    }
    
    public final void setVisibleInGame_ProvinceInfo(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_INFO).setVisible(visible);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_INFO_BUILDINGS).setVisible(visible);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_INFO_ARMY).setVisible(visible);
        if (visible) {
            this.hideCourtCiv();
            this.setOrderOfMenu(this.IN_GAME_PROVINCE_INFO);
            this.setOrderOfMenu(this.IN_GAME_PROVINCE_INFO_BUILDINGS);
            this.setOrderOfMenu(this.IN_GAME_PROVINCE_INFO_ARMY);
        }
        else {
            final Keyboard keyboard = Game.keyboard;
            if (Keyboard.keyboardMode && Keyboard.keyboardActionType == Keyboard.KeyboardActionType.INGAME_RENAME_PROVINCE) {
                Game.keyboard.hideKeyboard();
            }
            this.setVisibleInGame_Buildings(false, false);
            this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_BONUSES).setVisible(false);
            this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_INFO_ARMY).setVisible(false);
        }
    }
    
    public final void rebuildInGame_ProvinceInfo(final boolean updateOrder) {
        final boolean odlVisible = this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_INFO).getVisible();
        final Keyboard keyboard = Game.keyboard;
        if (Keyboard.keyboardMode && Keyboard.keyboardActionType == Keyboard.KeyboardActionType.INGAME_RENAME_PROVINCE) {
            Game.keyboard.hideKeyboard();
        }
        this.menus.get(this.IN_GAME).set(this.IN_GAME_PROVINCE_INFO, (Object)new InGame_ProvinceInfo());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_INFO).setVisible(true);
        this.menus.get(this.IN_GAME).set(this.IN_GAME_PROVINCE_INFO_BUILDINGS, (Object)new InGame_ProvinceInfoBuildings());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_INFO_BUILDINGS).setVisible(Game.getProvince(InGame_ProvinceInfo.iProvinceID).getCivID() > 0);
        this.menus.get(this.IN_GAME).set(this.IN_GAME_PROVINCE_INFO_ARMY, (Object)new InGame_ProvinceInfo_Army());
        Game.hoverManager.rebuildHoverAfterRebuildMenu();
        if (updateOrder) {
            this.setOrderOfMenu_InGameProvinceInfo();
        }
        if (odlVisible) {
            InGame_ProvinceInfo.lTime = 0L;
        }
    }
    
    public final void rebuildInGame_ScenarioEditorArmies() {
        this.menus.get(this.SCENARIO_ARMIES).set(1, (Object)new ScenarioArmies_List());
    }
    
    public final void rebuildInGame_ScenarioEditorGovernment() {
        this.menus.get(this.SCENARIO_GOVERNMENT).set(1, (Object)new ScenarioGovernment_List());
    }
    
    public final void rebuildInGame_ScenarioEditorBuildings() {
        this.menus.get(this.SCENARIO_BUILDINGS).set(1, (Object)new ScenarioBuildings_List());
    }
    
    public final void rebuildInGame_ScenarioEditorRelations() {
        this.menus.get(this.SCENARIO_RELATIONS).set(1, (Object)new ScenarioRelationsList());
    }
    
    public final void rebuildInGame_ScenarioEditorAlliances() {
        this.menus.get(this.SCENARIO_ALLIANCES).set(1, (Object)new ScenarioAlliancesList());
    }
    
    public final void rebuildInGame_ScenarioEditorCreateAlliance_Edit() {
        this.menus.get(this.SCENARIO_CREATE_ALLIANCE_EDIT).set(1, (Object)new ScenarioCreateAllianceList());
    }
    
    public final void rebuildInGame_ScenarioEditorCreateAlliance_Edit_SavePos() {
        final int nX = this.menus.get(this.SCENARIO_CREATE_ALLIANCE_EDIT).get(1).getPosX();
        final int nY = this.menus.get(this.SCENARIO_CREATE_ALLIANCE_EDIT).get(1).getPosY();
        final int mX = this.menus.get(this.SCENARIO_CREATE_ALLIANCE_EDIT).get(1).getMenuPosX();
        final int mY = this.menus.get(this.SCENARIO_CREATE_ALLIANCE_EDIT).get(1).getMenuPosY();
        this.menus.get(this.SCENARIO_CREATE_ALLIANCE_EDIT).set(1, (Object)new ScenarioCreateAllianceList());
        this.menus.get(this.SCENARIO_CREATE_ALLIANCE_EDIT).get(1).setPosX(nX);
        this.menus.get(this.SCENARIO_CREATE_ALLIANCE_EDIT).get(1).setPosY(nY);
        this.menus.get(this.SCENARIO_CREATE_ALLIANCE_EDIT).get(1).setMenuPosX(mX);
        this.menus.get(this.SCENARIO_CREATE_ALLIANCE_EDIT).get(1).setMenuPosY(mY);
    }
    
    public final void rebuildInGame_ScenarioEditorVassals() {
        this.menus.get(this.SCENARIO_VASSALS).set(1, (Object)new ScenarioVassalsList());
    }
    
    public final void rebuildInGame_ScenarioEditorTruces() {
        this.menus.get(this.SCENARIO_TRUCES).set(1, (Object)new ScenarioTrucesList());
    }
    
    public final void rebuildInGame_ScenarioEditorDeclareWar() {
        this.menus.get(this.SCENARIO_DECLARE_WAR).set(1, (Object)new ScenarioDeclareWarList());
    }
    
    public final void rebuildInGame_ScenarioEditorNonAggression() {
        this.menus.get(this.SCENARIO_NON_AGGRESSION).set(1, (Object)new ScenarioNonAggressionList());
    }
    
    public final void rebuildInGame_ScenarioEditorMilitaryAccess() {
        this.menus.get(this.SCENARIO_MILITARY_ACCESS).set(1, (Object)new ScenarioMilitaryAccessList());
    }
    
    public final void rebuildInGame_ScenarioEditorDefensive() {
        this.menus.get(this.SCENARIO_DEFENSIVE).set(1, (Object)new ScenarioDefensiveList());
    }
    
    public final void rebuildInGame_ScenarioEditorGuarantee() {
        this.menus.get(this.SCENARIO_GUARANTEE).set(1, (Object)new ScenarioGuaranteeList());
    }
    
    public final void rebuildInGame_ProvinceInfo_Army() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_PROVINCE_INFO_ARMY, (Object)new InGame_ProvinceInfo_Army());
        Game.hoverManager.rebuildHoverAfterRebuildMenu();
    }
    
    public final void setVisibleInGame_ReorganizeUnits(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_REORGANIZE_UNITS).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu(this.IN_GAME_REORGANIZE_UNITS);
        }
    }
    
    public final void rebuildInGame_ReorganizeUnits() {
        final int nX = this.menus.get(this.IN_GAME).get(this.IN_GAME_REORGANIZE_UNITS).getPosX();
        final int nY = this.menus.get(this.IN_GAME).get(this.IN_GAME_REORGANIZE_UNITS).getPosY();
        final int mX = this.menus.get(this.IN_GAME).get(this.IN_GAME_REORGANIZE_UNITS).getMenuPosX();
        final int mY = this.menus.get(this.IN_GAME).get(this.IN_GAME_REORGANIZE_UNITS).getMenuPosY();
        InGame_ReorganizeUnits.restartAnimation = !this.menus.get(this.IN_GAME).get(this.IN_GAME_REORGANIZE_UNITS).getVisible();
        this.menus.get(this.IN_GAME).set(this.IN_GAME_REORGANIZE_UNITS, (Object)new InGame_ReorganizeUnits());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_REORGANIZE_UNITS).setPosX(nX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_REORGANIZE_UNITS).setPosY(nY);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_REORGANIZE_UNITS).setMenuPosX(mX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_REORGANIZE_UNITS).setMenuPosY(mY);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_REORGANIZE_UNITS).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_REORGANIZE_UNITS);
    }
    
    public final void setVisibleInGame_DisbandUnits(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_DISBAND_UNITS).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu(this.IN_GAME_DISBAND_UNITS);
        }
    }
    
    public final void rebuildInGame_DisbandUnits() {
        final int nX = this.menus.get(this.IN_GAME).get(this.IN_GAME_DISBAND_UNITS).getPosX();
        final int nY = this.menus.get(this.IN_GAME).get(this.IN_GAME_DISBAND_UNITS).getPosY();
        final int mX = this.menus.get(this.IN_GAME).get(this.IN_GAME_DISBAND_UNITS).getMenuPosX();
        final int mY = this.menus.get(this.IN_GAME).get(this.IN_GAME_DISBAND_UNITS).getMenuPosY();
        InGame_ReorganizeUnits.restartAnimation = !this.menus.get(this.IN_GAME).get(this.IN_GAME_DISBAND_UNITS).getVisible();
        this.menus.get(this.IN_GAME).set(this.IN_GAME_DISBAND_UNITS, (Object)new InGame_Disband());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_DISBAND_UNITS).setPosX(nX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_DISBAND_UNITS).setPosY(nY);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_DISBAND_UNITS).setMenuPosX(mX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_DISBAND_UNITS).setMenuPosY(mY);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_DISBAND_UNITS).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_DISBAND_UNITS);
    }
    
    public final void rebuildNewGameCiv() {
        this.menus.get(this.NEW_GAME).set(this.NEW_GAME_CIV, (Object)new NewGameCiv());
    }
    
    public final void rebuildNewGame_Settings() {
        this.menus.get(this.NEW_GAME).set(this.NEW_GAME_SETTINGS, (Object)new NewGame_Settings());
        this.setVisible_NewGame_Settings(true);
    }
    
    public final boolean getVisible_NewGame_Settings() {
        return this.menus.get(this.NEW_GAME).get(this.NEW_GAME_SETTINGS).getVisible();
    }
    
    public final void setVisible_NewGame_Settings(final boolean visible) {
        this.menus.get(this.NEW_GAME).get(this.NEW_GAME_SETTINGS).setVisible(visible);
    }
    
    public final void rebuild_NewGame_Settings() {
        this.menus.get(this.NEW_GAME).set(this.NEW_GAME_SETTINGS, (Object)new NewGame_Settings());
        this.menus.get(this.NEW_GAME).get(this.NEW_GAME_SETTINGS).setVisible(true);
    }
    
    public final void rebuild_NewGame_Encyclopedia() {
        this.menus.get(this.NEW_GAME).set(this.NEW_GAME_SETTINGS, (Object)new InGame_Encyclopedia());
        this.menus.get(this.NEW_GAME).get(this.NEW_GAME_SETTINGS).setVisible(true);
    }
    
    public final void rebuildInGame_RecruitSameType() {
        this.inCreateNewArmy = false;
        this.menus.get(this.IN_GAME).set(this.IN_GAME_RECRUIT_ARMY, (Object)new InGame_RecruitSameType());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_RECRUIT_ARMY).setVisible(true);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_RECRUIT_ARMY_BATTLEFIELD).setVisible(false);
        this.setOrderOfMenu(this.IN_GAME_RECRUIT_ARMY);
    }
    
    public final void getInGame_RecruitSameType_UpdateLanguage() {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_RECRUIT_ARMY).updateLanguage();
    }
    
    public final void rebuildInGame_RecruitArmy() {
        this.inCreateNewArmy = false;
        InGame_RecruitArmy.key = null;
        this.menus.get(this.IN_GAME).set(this.IN_GAME_RECRUIT_ARMY, (Object)new InGame_RecruitArmy());
        this.setOrderOfMenu(this.IN_GAME_RECRUIT_ARMY);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_RECRUIT_ARMY_BATTLEFIELD).setVisible(false);
    }
    
    public final void rebuildInGame_RecruitArmy_NewArmy() {
        this.inCreateNewArmy = true;
        InGame_RecruitArmy.key = null;
        this.menus.get(this.IN_GAME).set(this.IN_GAME_RECRUIT_ARMY, (Object)new InGame_RecruitArmy_NewArmy());
        if (InGame_RecruitArmy_NewArmy_Battlefield.autoVisible) {
            this.menus.get(this.IN_GAME).set(this.IN_GAME_RECRUIT_ARMY_BATTLEFIELD, (Object)new InGame_RecruitArmy_NewArmy_Battlefield());
            this.menus.get(this.IN_GAME).get(this.IN_GAME_RECRUIT_ARMY_BATTLEFIELD).setVisible(true);
        }
        else {
            this.menus.get(this.IN_GAME).get(this.IN_GAME_RECRUIT_ARMY_BATTLEFIELD).setVisible(false);
        }
        this.setOrderOfMenu(this.IN_GAME_RECRUIT_ARMY_BATTLEFIELD);
        this.setOrderOfMenu(this.IN_GAME_RECRUIT_ARMY);
    }
    
    public final void rebuildInGame_RecruitArmy_NewArmy_Battlefield() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_RECRUIT_ARMY_BATTLEFIELD, (Object)new InGame_RecruitArmy_NewArmy_Battlefield());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_RECRUIT_ARMY_BATTLEFIELD).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_RECRUIT_ARMY_BATTLEFIELD);
        this.setOrderOfMenu(this.IN_GAME_RECRUIT_ARMY);
    }
    
    public final void rebuildInGame_Generals() {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_GENERALS).dispose();
        this.menus.get(this.IN_GAME).set(this.IN_GAME_GENERALS, (Object)new InGame_Generals());
        if (this.getVisibleInGame_CurrentSituation()) {
            this.setVisibleInGame_CurrentSituation(false);
        }
    }
    
    public final void rebuildInGame_Notifications() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_NOTIFICATION, (Object)new InGame_Notifications());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_NOTIFICATION).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_NOTIFICATION);
    }
    
    public final void rebuildInGame_Right() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_RIGHT, (Object)new InGame_Right());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_RIGHT).setVisible(true);
        this.rebuildInGame_RightQueue();
        this.setOrderOfMenu(this.IN_GAME_RIGHT);
        this.setOrderOfMenu(this.IN_GAME_RIGHT_QUEUE);
    }
    
    public final void addRebuildInGame_RightQueue() {
        Game.addSimpleTask(new Game.SimpleTask("rebuildInGame_RightQueue") {
            @Override
            public void update() {
                Game.menuManager.rebuildInGame_RightQueue();
            }
        });
    }
    
    public final void rebuildInGame_RightQueue() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_RIGHT_QUEUE, (Object)new InGame_RightQueue());
        this.setOrderOfMenu(this.IN_GAME_RIGHT_QUEUE);
    }
    
    public final void rebuildInGame_RightPopulation() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_RIGHT, (Object)new InGame_RightPopulation());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_RIGHT).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_RIGHT);
    }
    
    public final void rebuildInGame_RightGovernment() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_RIGHT, (Object)new InGame_RightGovernment());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_RIGHT).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_RIGHT);
    }
    
    public final void rebuildInGame_RightReligion() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_RIGHT, (Object)new InGame_RightReligion());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_RIGHT).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_RIGHT);
    }
    
    public final void rebuildInGame_RightWonders() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_RIGHT, (Object)new InGame_RightWonders());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_RIGHT).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_RIGHT);
    }
    
    public final void rebuildInGame_RightEconomy() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_RIGHT, (Object)new InGame_RightEconomy());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_RIGHT).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_RIGHT);
    }
    
    public final void rebuildInGame_RightInfrastructure() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_RIGHT, (Object)new InGame_RightInfrastructure());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_RIGHT).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_RIGHT);
    }
    
    public final void rebuildInGame_RightGoods() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_RIGHT, (Object)new InGame_RightGoods());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_RIGHT).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_RIGHT);
    }
    
    public final void rebuildInGame_RightGoodsProvinces() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_RIGHT, (Object)new InGame_RightGoodsProvinces());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_RIGHT).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_RIGHT);
    }
    
    public final void rebuildInGame_RightProvinceIncome() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_RIGHT, (Object)new InGame_RightProvinceIncome());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_RIGHT).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_RIGHT);
    }
    
    public final void rebuildInGame_RightTaxEfficiency() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_RIGHT, (Object)new InGame_RightTaxEfficiency());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_RIGHT).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_RIGHT);
    }
    
    public final void rebuildInGame_Armies(final boolean noGeneralsSort, final boolean savePos) {
        if (savePos) {
            final int nX = this.menus.get(this.IN_GAME).get(this.IN_GAME_ARMIES).getPosX();
            final int nY = this.menus.get(this.IN_GAME).get(this.IN_GAME_ARMIES).getPosY();
            final int mX = this.menus.get(this.IN_GAME).get(this.IN_GAME_ARMIES).getMenuPosX();
            final int mY = this.menus.get(this.IN_GAME).get(this.IN_GAME_ARMIES).getMenuPosY();
            this.menus.get(this.IN_GAME).set(this.IN_GAME_ARMIES, (Object)new InGame_Armies(noGeneralsSort));
            this.menus.get(this.IN_GAME).get(this.IN_GAME_ARMIES).setPosX(nX);
            this.menus.get(this.IN_GAME).get(this.IN_GAME_ARMIES).setPosY(nY);
            this.menus.get(this.IN_GAME).get(this.IN_GAME_ARMIES).setMenuPosX(mX);
            this.menus.get(this.IN_GAME).get(this.IN_GAME_ARMIES).setMenuPosY(mY);
            InGame_Armies.lTime = 0L;
        }
        else {
            this.menus.get(this.IN_GAME).set(this.IN_GAME_ARMIES, (Object)new InGame_Armies(noGeneralsSort));
        }
    }
    
    public final void rebuildInGame_Mercenaries() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_ARMIES, (Object)new InGame_RecruitMercenaries());
    }
    
    public final boolean getVisibleInGame_Right() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_RIGHT).getVisible();
    }
    
    public final boolean getVisibleInGame_Notifications() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_NOTIFICATION).getVisible();
    }
    
    public final void rebuildInGame_CivBonuses() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_CIV_BONUSES, (Object)new InGame_CivBonuses());
    }
    
    public final void rebuildInGame_Court() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court());
    }
    
    public final void rebuildInGame_CourtSavePos() {
        final int nX = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).getPosX();
        final int nY = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).getPosY();
        final int mX = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).getMenuPosX();
        final int mY = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).getMenuPosY();
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).setPosX(nX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).setPosY(nY);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).setMenuPosX(mX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).setMenuPosY(mY);
        this.setVisibleInGame_Court(true);
        InGame_Court.lTime = 0L;
    }
    
    public final void rebuildInGame_CourtOptions() {
        if (this.IN_GAME >= 0 && this.IN_GAME_COURT_OPTIONS >= 0) {
            final boolean vis1 = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT_OPTIONS).getVisible();
            final boolean vis2 = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT_OPTIONS2).getVisible();
            this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT_OPTIONS, (Object)new InGame_CourtOptions());
            this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT_OPTIONS2, (Object)new InGame_CourtOptions2());
            this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT_OPTIONS).setVisible(vis1);
            this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT_OPTIONS2).setVisible(vis2);
        }
    }
    
    public final void rebuildInGame_ExploitEconomy() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court_ExploitEconomy());
    }
    
    public final void rebuildInGame_ExploitEconomy_SavePos() {
        final int nX = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).getPosX();
        final int nY = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).getPosY();
        final int mX = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).getMenuPosX();
        final int mY = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).getMenuPosY();
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court_ExploitEconomy());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).setPosX(nX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).setPosY(nY);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).setMenuPosX(mX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).setMenuPosY(mY);
        this.setVisibleInGame_Court(true);
        InGame_Court.lTime = 0L;
    }
    
    public final void rebuildInGame_InvestInEconomy() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court_InvestInEconomy());
    }
    
    public final void rebuildInGame_IncreaseTaxEfficiency() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court_IncreaseTaxEfficiency());
    }
    
    public final void rebuildInGame_Government() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court_Government());
    }
    
    public final void rebuildInGame_Government_SavePos() {
        final int nX = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).getPosX();
        final int nY = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).getPosY();
        final int mX = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).getMenuPosX();
        final int mY = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).getMenuPosY();
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court_Government());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).setPosX(nX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).setPosY(nY);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).setMenuPosX(mX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).setMenuPosY(mY);
        this.setVisibleInGame_Court(true);
        InGame_Court.lTime = 0L;
    }
    
    public final void rebuildInGame_LawsCourt() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court_Law());
    }
    
    public final void rebuildInGame_CourtProvinces() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court_Provinces());
    }
    
    public final void rebuildInGame_CourtWorld_Wars() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court_WorldWars());
    }
    
    public final void rebuildInGame_CourtStatistics() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court_Stats());
    }
    
    public final void rebuildInGame_CourtSearch() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court_WorldSearch());
    }
    
    public final void rebuildInGame_CourtSearchCivs() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court_WorldCivs());
    }
    
    public final void rebuildInGame_CourtMissions() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court_Missions());
    }
    
    public final void rebuildInGame_CourtGoldenAges() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court_GoldenAges());
    }
    
    public final void rebuildInGame_CourtWorld_Alliances() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court_WorldAlliances());
    }
    
    public final void rebuildInGame_CourtWorld_Truces() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court_WorldTruces());
    }
    
    public final void rebuildInGame_CourtWorld_Defensive() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court_WorldDefensive());
    }
    
    public final void rebuildInGame_CourtWorld_NonAggression() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court_WorldNonAggression());
    }
    
    public final void rebuildInGame_CourtWorld_Vassals() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court_WorldVassals());
    }
    
    public final void rebuildInGame_EspionageReportCourt(final int iCivID, final int iReportEndTurnID) {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court_Espionage(iCivID, iReportEndTurnID));
    }
    
    public final void rebuildInGame_IncreaseManpower() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court_IncreaseManpower());
    }
    
    public final void rebuildInGame_IncreaseGrowthRate() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court_IncreaseGrowthRate());
    }
    
    public final void rebuildInGame_DevelopInfrastructure() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court_DevelopInfrastructure());
    }
    
    public final void rebuildInGame_Core() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court_Core());
    }
    
    public final void rebuildInGame_CoreSavePos() {
        final int nX = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).getPosX();
        final int nY = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).getPosY();
        final int mX = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).getMenuPosX();
        final int mY = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).getMenuPosY();
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court_Core());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).setPosX(nX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).setPosY(nY);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).setMenuPosX(mX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).setMenuPosY(mY);
    }
    
    public final void rebuildInGame_Religion() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court_Religion());
    }
    
    public final void rebuildInGame_ReligionSavePos() {
        final int nX = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).getPosX();
        final int nY = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).getPosY();
        final int mX = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).getMenuPosX();
        final int mY = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).getMenuPosY();
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court_Religion());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).setPosX(nX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).setPosY(nY);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).setMenuPosX(mX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).setMenuPosY(mY);
    }
    
    public final void rebuildInGame_Buildings2() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court_Buildings2());
    }
    
    public final void rebuildInGame_Buildings2SavePos() {
        final int nX = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).getPosX();
        final int nY = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).getPosY();
        final int mX = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).getMenuPosX();
        final int mY = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).getMenuPosY();
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court_Buildings2());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).setPosX(nX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).setPosY(nY);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).setMenuPosX(mX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).setMenuPosY(mY);
    }
    
    public final void savePosInGame_Buildings2() {
        InGame_Court_Buildings2.savePos_X = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).getPosX();
        InGame_Court_Buildings2.savePos_Y = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).getPosY();
        InGame_Court_Buildings2.saveMenuPos_X = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).getMenuPosX();
        InGame_Court_Buildings2.saveMenuPos_Y = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).getMenuPosY();
    }
    
    public final void rebuildInGame_Buildings2_Back() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court_Buildings2());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).setPosX(InGame_Court_Buildings2.savePos_X);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).setPosY(InGame_Court_Buildings2.savePos_Y);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).setMenuPosX(InGame_Court_Buildings2.saveMenuPos_X);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).setMenuPosY(InGame_Court_Buildings2.saveMenuPos_Y);
    }
    
    public final void rebuildInGame_Build() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court_Build());
    }
    
    public final void rebuildInGame_BuildSavePos() {
        final int nX = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).getPosX();
        final int nY = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).getPosY();
        final int mX = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).getMenuPosX();
        final int mY = this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).getMenuPosY();
        this.menus.get(this.IN_GAME).set(this.IN_GAME_COURT, (Object)new InGame_Court_Build());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).setPosX(nX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).setPosY(nY);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).setMenuPosX(mX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).setMenuPosY(mY);
    }
    
    public final void rebuildInGame_Budget() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_BUDGET, (Object)new InGame_Budget());
    }
    
    public final void rebuildInGame_Event(final Event event, final int eventType, final int eventID) {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_EVENT, (Object)new InGame_Event(event, eventType, eventID));
        this.menus.get(this.IN_GAME).get(this.IN_GAME_EVENT).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_EVENT);
    }
    
    public final boolean getVisibleInGame_CurrentSituation_Ranking() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_CURRENT_SITUATION).getVisible() && !MenuManager.currentSituationMode;
    }
    
    public final void rebuildInGame_CurrentSituation_Ranking() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_CURRENT_SITUATION, (Object)new InGame_Ranking());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CURRENT_SITUATION).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_CURRENT_SITUATION);
        MenuManager.currentSituationMode = false;
        this.hideCourtCiv();
        this.setVisibleInGame_Buildings(false, false);
        this.setVisibleInGame_Wonder(false);
        this.setVisibleInGame_Generals(false);
        this.setVisibleInGame_ReorganizeUnits(false);
        this.setVisibleInGame_DisbandUnits(false);
        this.setVisibleInGame_RecruitArmy(false);
        if (this.getVisibleInGame_GoodsMarket()) {
            this.setVisibleInGame_GoodsMarket(false);
        }
        if (this.getVisibleInGame_TechnologyChoose()) {
            this.setVisibleInGame_TechnologyChoose(false);
        }
        if (this.getVisibleDeclareWar()) {
            this.setVisibleInGame_PopUp(false);
        }
        if (this.getVisible_SpecialAlliance()) {
            this.setVisibleInGame_PopUp(false);
        }
        if (this.getVisibleFormCiv() || this.getVisibleSellProvince() || this.getVisibleRevolutions()) {
            this.setVisibleInGame_PopUp(false);
        }
        if (this.getVisibleInGame_Nukes()) {
            this.setVisibleInGame_Nukes(false);
        }
        if (this.getVisibleInGame_Armies()) {
            this.setVisibleInGame_Armies(false);
        }
        if (this.getVisibleInGame_War()) {
            this.setVisibleInGame_War(false);
        }
        if (this.getVisibleInGame_Peace()) {
            this.setVisibleInGame_Peace(false);
        }
    }
    
    public final void rebuildInGame_CurrentSituation_Missions() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_CURRENT_SITUATION, (Object)new InGame_Missions());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CURRENT_SITUATION).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_CURRENT_SITUATION);
        MenuManager.currentSituationMode = true;
        this.hideCourtCiv();
        this.setVisibleInGame_Buildings(false, false);
        this.setVisibleInGame_Wonder(false);
        this.setVisibleInGame_Generals(false);
        this.setVisibleInGame_ReorganizeUnits(false);
        this.setVisibleInGame_DisbandUnits(false);
        this.setVisibleInGame_RecruitArmy(false);
        if (this.getVisibleInGame_GoodsMarket()) {
            this.setVisibleInGame_GoodsMarket(false);
        }
        if (this.getVisibleInGame_TechnologyChoose()) {
            this.setVisibleInGame_TechnologyChoose(false);
        }
        if (this.getVisibleDeclareWar()) {
            this.setVisibleInGame_PopUp(false);
        }
        if (this.getVisible_SpecialAlliance()) {
            this.setVisibleInGame_PopUp(false);
        }
        if (this.getVisibleFormCiv() || this.getVisibleSellProvince() || this.getVisibleRevolutions()) {
            this.setVisibleInGame_PopUp(false);
        }
        if (this.getVisibleInGame_Nukes()) {
            this.setVisibleInGame_Nukes(false);
        }
        if (this.getVisibleInGame_Armies()) {
            this.setVisibleInGame_Armies(false);
        }
    }
    
    public final void rebuildInGame_ReleaseAVassal() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_CURRENT_SITUATION, (Object)new InGame_ReleaseAVassal());
        this.setOrderOfMenu(this.IN_GAME_CURRENT_SITUATION);
        MenuManager.currentSituationMode = false;
        this.rebuildInGame_CurrentSituation_HideMenus();
    }
    
    public final void rebuildInGame_ReleaseAVassal_SavePos() {
        final int nX = this.menus.get(this.IN_GAME).get(this.IN_GAME_CURRENT_SITUATION).getPosX();
        final int nY = this.menus.get(this.IN_GAME).get(this.IN_GAME_CURRENT_SITUATION).getPosY();
        final int mX = this.menus.get(this.IN_GAME).get(this.IN_GAME_CURRENT_SITUATION).getMenuPosX();
        final int mY = this.menus.get(this.IN_GAME).get(this.IN_GAME_CURRENT_SITUATION).getMenuPosY();
        this.menus.get(this.IN_GAME).set(this.IN_GAME_CURRENT_SITUATION, (Object)new InGame_ReleaseAVassal());
        this.setOrderOfMenu(this.IN_GAME_CURRENT_SITUATION);
        MenuManager.currentSituationMode = false;
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CURRENT_SITUATION).setPosX(nX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CURRENT_SITUATION).setPosY(nY);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CURRENT_SITUATION).setMenuPosX(mX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CURRENT_SITUATION).setMenuPosY(mY);
    }
    
    public final void rebuildInGame_CurrentSituation() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_CURRENT_SITUATION, (Object)new InGame_CurrentSituation());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CURRENT_SITUATION).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_CURRENT_SITUATION);
        MenuManager.currentSituationMode = true;
        this.rebuildInGame_CurrentSituation_HideMenus();
    }
    
    public final void rebuildInGame_CurrentSituation_HideMenus() {
        this.hideCourtCiv();
        this.setVisibleInGame_Buildings(false, false);
        this.setVisibleInGame_Wonder(false);
        this.setVisibleInGame_Generals(false);
        this.setVisibleInGame_ReorganizeUnits(false);
        this.setVisibleInGame_DisbandUnits(false);
        this.setVisibleInGame_RecruitArmy(false);
        if (this.getVisibleInGame_GoodsMarket()) {
            this.setVisibleInGame_GoodsMarket(false);
        }
        if (this.getVisibleInGame_TechnologyChoose()) {
            this.setVisibleInGame_TechnologyChoose(false);
        }
        if (this.getVisibleDeclareWar()) {
            this.setVisibleInGame_PopUp(false);
        }
        if (this.getVisible_SpecialAlliance()) {
            this.setVisibleInGame_PopUp(false);
        }
        if (this.getVisibleFormCiv() || this.getVisibleSellProvince() || this.getVisibleRevolutions()) {
            this.setVisibleInGame_PopUp(false);
        }
        if (this.getVisibleInGame_Nukes()) {
            this.setVisibleInGame_Nukes(false);
        }
        if (this.getVisibleInGame_Armies()) {
            this.setVisibleInGame_Armies(false);
        }
    }
    
    public final void rebuildInGame_CurrentSituation_Wonders() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_CURRENT_SITUATION, (Object)new InGame_Wonders());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CURRENT_SITUATION).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_CURRENT_SITUATION);
    }
    
    public final void rebuildInGame_BudgetIncomeTaxation() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_BUDGET, (Object)new InGame_BudgetIncomeTaxation());
        this.setVisibleInGame_Budget(true);
    }
    
    public final void rebuildInGame_BudgetIncomeProvinces() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_BUDGET, (Object)new InGame_BudgetIncomeProvinces());
        this.setVisibleInGame_Budget(true);
    }
    
    public final void rebuildInGame_BudgetBalanceProvinces() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_BUDGET, (Object)new InGame_BudgetBalanceProvinces());
        this.setVisibleInGame_Budget(true);
    }
    
    public final void rebuildInGame_BudgetIncomeEconomy() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_BUDGET, (Object)new InGame_BudgetIncomeEconomy());
        this.setVisibleInGame_Budget(true);
    }
    
    public final void rebuildInGame_BudgetExpensesMaintenance() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_BUDGET, (Object)new InGame_BudgetExpensesMaintenance());
        this.setVisibleInGame_Budget(true);
    }
    
    public final void rebuildInGame_BudgetExpensesBuildingsMaintenance() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_BUDGET, (Object)new InGame_BudgetExpensesBuildingsMaintenance());
        this.setVisibleInGame_Budget(true);
    }
    
    public final void rebuildInGame_BudgetIncomeProduction() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_BUDGET, (Object)new InGame_BudgetIncomeProduction());
        this.setVisibleInGame_Budget(true);
    }
    
    public final void rebuildInGame_BudgetIncomeBuildings() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_BUDGET, (Object)new InGame_BudgetIncomeBuildings());
        this.setVisibleInGame_Budget(true);
    }
    
    public final void rebuildInGame_Civ_SavePos() {
        final Keyboard keyboard = Game.keyboard;
        if (Keyboard.keyboardMode && Keyboard.keyboardActionType == Keyboard.KeyboardActionType.INGAME_RENAME_CIV) {
            Game.keyboard.hideKeyboard();
        }
        final int nX = this.menus.get(this.IN_GAME).get(this.IN_GAME_CIV).getPosX();
        final int nY = this.menus.get(this.IN_GAME).get(this.IN_GAME_CIV).getPosY();
        final int mX = this.menus.get(this.IN_GAME).get(this.IN_GAME_CIV).getMenuPosX();
        final int mY = this.menus.get(this.IN_GAME).get(this.IN_GAME_CIV).getMenuPosY();
        this.menus.get(this.IN_GAME).set(this.IN_GAME_CIV, (Object)new InGame_Civ());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CIV).setVisible(true);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CIV).setPosX(nX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CIV).setPosY(nY);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CIV).setMenuPosX(mX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CIV).setMenuPosY(mY);
        InGame_Civ.enabledByScaleOut = false;
    }
    
    public final void rebuildInGame_Civ() {
        this.rebuildInGame_Civ(false);
    }
    
    public final void rebuildInGame_Civ(final boolean rebuildMode) {
        final Keyboard keyboard = Game.keyboard;
        if (Keyboard.keyboardMode && Keyboard.keyboardActionType == Keyboard.KeyboardActionType.INGAME_RENAME_CIV) {
            Game.keyboard.hideKeyboard();
        }
        if (!rebuildMode && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_DIPLOMACY) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DIPLOMACY);
        }
        this.menus.get(this.IN_GAME).set(this.IN_GAME_CIV, (Object)new InGame_Civ());
        if (rebuildMode) {
            this.menus.get(this.IN_GAME).get(this.IN_GAME_CIV).setVisible(true);
        }
        else {
            this.setVisibleInGame_Civ(true);
            InGame_Civ.actionOnOpen();
        }
        InGame_Civ.enabledByScaleOut = false;
    }
    
    public final void rebuildInGame_Civ_Government() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_CIV, (Object)new InGame_Civ_Government());
        this.setVisibleInGame_Civ(true);
        InGame_Civ.enabledByScaleOut = false;
        if (this.getVisibleInGame_CivBonuses()) {
            this.setVisibleInGame_CivBonuses(false);
        }
    }
    
    public final void rebuildInGame_Civ_Religion() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_CIV, (Object)new InGame_Civ_Religion());
        this.setVisibleInGame_Civ(true);
        InGame_Civ.enabledByScaleOut = false;
        if (this.getVisibleInGame_CivBonuses()) {
            this.setVisibleInGame_CivBonuses(false);
        }
    }
    
    public final void rebuildInGame_Civ_MilitaryAcademy() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_CIV, (Object)new InGame_Civ_MilitaryAcademy());
        this.setVisibleInGame_Civ(true);
        InGame_Civ.enabledByScaleOut = false;
        if (this.getVisibleInGame_CivBonuses()) {
            this.setVisibleInGame_CivBonuses(false);
        }
    }
    
    public final void rebuildInGame_Civ_CapitalCity() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_CIV, (Object)new InGame_Civ_CapitalCity());
        this.setVisibleInGame_Civ(true);
        InGame_Civ.enabledByScaleOut = false;
        if (this.getVisibleInGame_CivBonuses()) {
            this.setVisibleInGame_CivBonuses(false);
        }
    }
    
    public final void rebuildInGame_Civ_Regiments() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_CIV, (Object)new InGame_Civ_ArmiesRegiments());
        this.setVisibleInGame_Civ(true);
        InGame_Civ.enabledByScaleOut = false;
        if (this.getVisibleInGame_CivBonuses()) {
            this.setVisibleInGame_CivBonuses(false);
        }
    }
    
    public final void rebuildInGame_Civ_AggressiveExpansion() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_CIV, (Object)new InGame_Civ_AggressiveExpansion());
        this.setVisibleInGame_Civ(true);
        InGame_Civ.enabledByScaleOut = false;
        if (this.getVisibleInGame_CivBonuses()) {
            this.setVisibleInGame_CivBonuses(false);
        }
    }
    
    public final void rebuildMainMenu_Stats() {
        this.menus.get(this.MAINMENU_STATS).set(0, (Object)new MainMenu_Stats());
    }
    
    public final void rebuildInGame_Civ_Population() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_CIV, (Object)new InGame_Civ_Population());
        this.setVisibleInGame_Civ(true);
        InGame_Civ.enabledByScaleOut = false;
        if (this.getVisibleInGame_CivBonuses()) {
            this.setVisibleInGame_CivBonuses(false);
        }
    }
    
    public final void rebuildInGame_Civ_List() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_CIV, (Object)new InGame_Civ_List());
        this.setVisibleInGame_Civ(true);
        InGame_Civ.enabledByScaleOut = false;
        if (this.getVisibleInGame_CivBonuses()) {
            this.setVisibleInGame_CivBonuses(false);
        }
    }
    
    public final void rebuildInGame_Civ_Provinces() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_CIV, (Object)new InGame_Civ_Provinces());
        this.setVisibleInGame_Civ(true);
        InGame_Civ.enabledByScaleOut = false;
        if (this.getVisibleInGame_CivBonuses()) {
            this.setVisibleInGame_CivBonuses(false);
        }
    }
    
    public final void rebuildInGame_CivRank() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_CIV, (Object)new InGame_CivRank());
        this.setVisibleInGame_Civ(true);
        InGame_Civ.enabledByScaleOut = false;
        if (this.getVisibleInGame_CivBonuses()) {
            this.setVisibleInGame_CivBonuses(false);
        }
    }
    
    public final void rebuildInGame_Civ_UnlockedTechnologies() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_CIV, (Object)new InGame_Civ_UnlockedTechnologies());
        this.setVisibleInGame_Civ(true);
        InGame_Civ.enabledByScaleOut = false;
        if (this.getVisibleInGame_CivBonuses()) {
            this.setVisibleInGame_CivBonuses(false);
        }
    }
    
    public final void rebuildInGame_Civ_UnlockedAdvantages_2() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_CIV, (Object)new InGame_CivilizationAdvantages2());
        this.setVisibleInGame_Civ(true);
        InGame_Civ.enabledByScaleOut = false;
        if (this.getVisibleInGame_CivBonuses()) {
            this.setVisibleInGame_CivBonuses(false);
        }
    }
    
    public final void rebuildInGame_Civ_UnlockedAdvantages() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_CIV, (Object)new InGame_CivilizationAdvantages(InGame_Civ.iActiveCivID));
        this.setVisibleInGame_Civ(true);
        InGame_Civ.enabledByScaleOut = false;
        if (this.getVisibleInGame_CivBonuses()) {
            this.setVisibleInGame_CivBonuses(false);
        }
    }
    
    public final void rebuildInGame_Civ_Economy() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_CIV, (Object)new InGame_Civ_Economy());
        this.setVisibleInGame_Civ(true);
        InGame_Civ.enabledByScaleOut = false;
        if (this.getVisibleInGame_CivBonuses()) {
            this.setVisibleInGame_CivBonuses(false);
        }
    }
    
    public final void rebuildInGame_Civ_Compare() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_CIV, (Object)new InGame_Civ_Compare());
        this.setVisibleInGame_Civ(true);
        InGame_Civ.enabledByScaleOut = false;
        if (this.getVisibleInGame_CivBonuses()) {
            this.setVisibleInGame_CivBonuses(false);
        }
    }
    
    public final void rebuildInGame_ProvinceBonuses() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_PROVINCE_BONUSES, (Object)new InGame_ProvinceBonuses());
    }
    
    public final void rebuildInGame_GeneralRecruit() {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_GENERAL_RECRUIT).dispose();
        this.menus.get(this.IN_GAME).set(this.IN_GAME_GENERAL_RECRUIT, (Object)new InGame_GeneralRecruit());
        this.setVisibleInGame_GeneralRecruit(true);
    }
    
    public final void rebuildInGame_TakeLoan() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_TAKE_LOAN, (Object)new InGame_Loan());
        this.setVisibleInGame_TakeLoan(true);
    }
    
    public final void rebuildInGame_Console() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_CONSOLE, (Object)new InGame_Console(true));
        this.setOrderOfMenu(this.IN_GAME_CONSOLE);
    }
    
    public final void rebuildEditor() {
        this.menus.get(this.EDITOR).set(0, (Object)new Editor());
    }
    
    public final void rebuildInGame_SaveGame() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_SAVE_GAME, (Object)new InGame_SaveGame(true));
        this.setOrderOfMenu(this.IN_GAME_SAVE_GAME);
    }
    
    public final void rebuildScenario_Calendar() {
        this.menus.get(this.SCENARIO_SETTINGS).set(this.SCENARIO_CALENDAR, (Object)new Scenario_Calendar(true));
        this.setOrderOfMenu(this.SCENARIO_CALENDAR);
    }
    
    public final void rebuildInGame_TakeLoanRepay() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_TAKE_LOAN_REPAY, (Object)new InGame_LoanRepay());
        this.setVisibleInGame_TakeLoanRepay(true);
    }
    
    public final void rebuildInGame_Battlefield() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_Battlefield());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 51;
    }
    
    public final void rebuildInGame_ChangeReligion2() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_ChangeReligion2());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 50;
    }
    
    public final void rebuildInGame_ChangeReligion() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_ChangeReligion());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 49;
    }
    
    public final void rebuildInGame_Intervene(final int iCivID, final int iCivID2) {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_Intervene(iCivID, iCivID2));
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 48;
    }
    
    public final void rebuildInGame_AllianceList() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_AllianceList());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 47;
    }
    
    public final void rebuildInGame_AllianceList_SavePos() {
        final int nX = this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).getPosX();
        final int nY = this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).getPosY();
        final int mX = this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).getMenuPosX();
        final int mY = this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).getMenuPosY();
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_AllianceList());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 47;
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setPosX(nX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setPosY(nY);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setMenuPosX(mX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setMenuPosY(mY);
    }
    
    public final void rebuildInGame_MessageCallToWar(final PMessage key) {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_MessageCallToWar(key));
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 46;
    }
    
    public final void rebuildInGame_Revolutions() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_Revolutions());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 45;
    }
    
    public final void rebuildInGame_Revolutions_SavePos() {
        final int nX = this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).getPosX();
        final int nY = this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).getPosY();
        final int mX = this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).getMenuPosX();
        final int mY = this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).getMenuPosY();
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_Revolutions());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setPosX(nX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setPosY(nY);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setMenuPosX(mX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setMenuPosY(mY);
        InGame_Revolutions.lTime = 0L;
    }
    
    public final void rebuildInGame_ShareTechnology(final int iCivID) {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_ShareTechnology(iCivID));
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 44;
    }
    
    public final void rebuildInGame_LiberateCivilization(final int iCivID) {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_LiberateCivilization(iCivID));
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 43;
    }
    
    public final void rebuildInGame_Sandbox() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_Sandbox());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 42;
    }
    
    public final void rebuildInGame_SellProvince(final int sellToCivID) {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_SellProvince(sellToCivID));
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 41;
    }
    
    public final boolean getVisibleSellProvince() {
        return this.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 41;
    }
    
    public final void rebuildInGame_Rivals_End() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_Rivals_End());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 40;
    }
    
    public final boolean getVisibleFormCiv() {
        return this.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == MenuManager.IN_GAME_POP_UP_MENU_FORM_CIV;
    }
    
    public final void rebuildInGame_FormCiv(final int playerCivID) {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_FormCiv(playerCivID));
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = MenuManager.IN_GAME_POP_UP_MENU_FORM_CIV;
    }
    
    public final void rebuildInGame_RivalsList() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_RivalsList());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 38;
    }
    
    public final void rebuildInGame_RivalsList_SavePos() {
        final int nX = this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).getPosX();
        final int nY = this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).getPosY();
        final int mX = this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).getMenuPosX();
        final int mY = this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).getMenuPosY();
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_RivalsList());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 38;
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setPosX(nX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setPosY(nY);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setMenuPosX(mX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setMenuPosY(mY);
    }
    
    public final void rebuildInGame_CallAllies(final int iCivID) {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_CallAllies(iCivID));
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 37;
    }
    
    public final void rebuildInGame_AllianceSpecialReform(final int allianceID, final int reformID) {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_AllianceSpecialReformHRE(allianceID, reformID));
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 36;
    }
    
    public final void rebuildInGame_AllianceSpecial_SavePos(final int allianceID) {
        final int nX = this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).getPosX();
        final int nY = this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).getPosY();
        final int mX = this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).getMenuPosX();
        final int mY = this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).getMenuPosY();
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_AllianceSpecial(allianceID));
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setPosX(nX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setPosY(nY);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setMenuPosX(mX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setMenuPosY(mY);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 35;
    }
    
    public final boolean getVisible_SpecialAlliance() {
        return this.getVisibleInGame_PopUp() && (MenuManager.IN_GAME_POP_UP_MENU_ID == 35 || MenuManager.IN_GAME_POP_UP_MENU_ID == 36);
    }
    
    public final void rebuildInGame_AllianceSpecial(final int nAllianceID) {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_AllianceSpecial(nAllianceID));
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 35;
        if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_SPECIAL_ALLIANCE_VIEW) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_SPECIAL_ALLIANCE_VIEW);
        }
    }
    
    public final void rebuildInGame_MessageInsult(final String key) {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_MessageInsult(key));
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 34;
    }
    
    public final void rebuildInGame_MessageGuarantee(final String key) {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_MessageGuarantee(key));
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 33;
    }
    
    public final void rebuildInGame_MessageDemandsMilitaryAccess(final String key) {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_MessageDemandsMilitaryAccess(key));
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 32;
    }
    
    public final void rebuildInGame_MessageNonAggressionPact(final String key) {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_MessageNonAggressionPact(key));
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 31;
    }
    
    public final void rebuildInGame_MessageDefensivePact(final String key) {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_MessageDefensivePact(key));
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 30;
    }
    
    public final void rebuildInGame_MessageAlliance(final String key) {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_MessageAlliance(key));
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 29;
    }
    
    public final void rebuildInGame_MessageGift(final String key) {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_MessageGift(key));
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 28;
    }
    
    public final void rebuildInGame_SendSpy(final int iCivID) {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_SendSpy(iCivID));
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 27;
    }
    
    public final void rebuildInGame_BattleTactics() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_BattleTactics());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 26;
    }
    
    public final void rebuildInGame_Rivals() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_Rivals());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 25;
    }
    
    public final void rebuildInGame_LawReform(final int lawID, final int lawID2) {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_LawReform(lawID, lawID2));
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 24;
    }
    
    public final void rebuildInGame_Laws(final int lawID) {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_Laws(lawID));
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 23;
    }
    
    public final void rebuildInGame_SendGift(final int iCivID) {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_SendGift(iCivID));
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 22;
    }
    
    public final void rebuildInGame_Guarantee(final int iCivID) {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_Guarantee(iCivID));
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 21;
    }
    
    public final void rebuildInGame_OfferMilitaryAccess(final int iCivID) {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_OfferMilitaryAccess(iCivID));
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 20;
    }
    
    public final void rebuildInGame_DemandMilitaryAccess(final int iCivID) {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_DemandMilitaryAccess(iCivID));
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 19;
    }
    
    public final void rebuildInGame_Alliance(final int iCivID) {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_Alliance(iCivID));
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 18;
    }
    
    public final void rebuildInGame_NonAggression(final int iCivID) {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_NonAggression(iCivID));
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 17;
    }
    
    public final void rebuildInGame_DefensivePact(final int iCivID) {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_DefensivePact(iCivID));
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 16;
    }
    
    public final void rebuildInGame_Encyclopedia() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_Encyclopedia());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 15;
    }
    
    public final void rebuildInGame_BattleReport(final String key) {
        final int reportID = Game.player.getBattleReportID(key);
        if (reportID >= 0) {
            this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_BattleReport(reportID));
            this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
            this.setOrderOfMenu(this.IN_GAME_POP_UP);
            MenuManager.IN_GAME_POP_UP_MENU_ID = 14;
        }
    }
    
    public final void rebuildInGame_SendInsult(final int iCivID) {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_SendInsult(iCivID));
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 13;
    }
    
    public final boolean getVisibleRevolutions() {
        return this.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 45;
    }
    
    public final boolean getVisibleDeclareWar() {
        return this.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == MenuManager.IN_GAME_POP_UP_MENU_ID_DECLARE_WAR;
    }
    
    public final void rebuildInGame_DeclareWar(final int iCivID) {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_DeclareWar(iCivID));
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = MenuManager.IN_GAME_POP_UP_MENU_ID_DECLARE_WAR;
    }
    
    public final void rebuildInGame_ChangeIdeology2() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_ChangeIdeology2());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 11;
    }
    
    public final void rebuildInGame_ChangeIdeology() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_ChangeIdeology());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 10;
    }
    
    public final void rebuildInGame_Audio() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_Audio());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 9;
    }
    
    public final void rebuildInGame_UpgradeCapital() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_UpgradeCapital());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 8;
    }
    
    public final void rebuildInGame_MoveCapital_PopUp() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_MoveCapital_PopUp());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 7;
    }
    
    public final void rebuildInGame_BuildAtomicBomb() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_BuildAtomicBomb());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 6;
    }
    
    public final void rebuildInGame_UpgradeNuclearReactor() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_UpgradeNuclearReactor());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 5;
    }
    
    public final void rebuildInGame_UpgradeSupremeCourt() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_UpgradeSupremeCourt());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 4;
    }
    
    public final void rebuildInGame_UpgradeMilitaryAcademyForGenerals() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_UpgradeMilitaryAcademyForGenerals());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 3;
    }
    
    public final void rebuildInGame_UpgradeMilitaryAcademy() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_UpgradeMilitaryAcademy());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 2;
    }
    
    public final void rebuildInGame_Destroy() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_Destroy());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 1;
    }
    
    public final void rebuildInGame_ConvertReligion() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_POP_UP, (Object)new InGame_ConvertReligion());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        MenuManager.IN_GAME_POP_UP_MENU_ID = 0;
    }
    
    public final void rebuildInGame_CustomizeArmy() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_ADVISOR_RECRUIT, (Object)new InGame_ArmyCustomize());
        this.setVisibleInGame_AdvisorRecruit(true);
    }
    
    public final void rebuildInGame_AdvisorRecruit() {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_ADVISOR_RECRUIT).dispose();
        this.menus.get(this.IN_GAME).set(this.IN_GAME_ADVISOR_RECRUIT, (Object)new InGame_AdvisorRecruit());
        this.setVisibleInGame_AdvisorRecruit(true);
    }
    
    public final void setOrderOfMenu_TechnologyTree() {
        this.setOrderOfMenu(this.IN_GAME_TECHNOLOGY_TREE_EMPTY);
        this.setOrderOfMenu(this.IN_GAME_TECHNOLOGY_TREE);
    }
    
    public final void rebuildInGame_TechnologyTree(final boolean savePos, final boolean updateOrder) {
        final int nX = this.menus.get(this.IN_GAME).get(this.IN_GAME_TECHNOLOGY_TREE).getPosX();
        final int nY = this.menus.get(this.IN_GAME).get(this.IN_GAME_TECHNOLOGY_TREE).getPosY();
        final int mX = this.menus.get(this.IN_GAME).get(this.IN_GAME_TECHNOLOGY_TREE).getMenuPosX();
        final int mY = this.menus.get(this.IN_GAME).get(this.IN_GAME_TECHNOLOGY_TREE).getMenuPosY();
        this.menus.get(this.IN_GAME).set(this.IN_GAME_TECHNOLOGY_TREE, (Object)new InGame_TechnologyTree());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_TECHNOLOGY_TREE).setVisible(true);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_TECHNOLOGY_TREE_EMPTY).setVisible(true);
        if (savePos) {
            this.menus.get(this.IN_GAME).get(this.IN_GAME_TECHNOLOGY_TREE).setPosX(nX);
            this.menus.get(this.IN_GAME).get(this.IN_GAME_TECHNOLOGY_TREE).setPosY(nY);
            this.menus.get(this.IN_GAME).get(this.IN_GAME_TECHNOLOGY_TREE).setMenuPosX(mX);
            this.menus.get(this.IN_GAME).get(this.IN_GAME_TECHNOLOGY_TREE).setMenuPosY(mY);
        }
        if (updateOrder) {
            this.setOrderOfMenu_TechnologyTree();
        }
    }
    
    public final void rebuildInGame_MissionTree(final boolean savePos, final boolean updateOrder) {
        final int nX = this.menus.get(this.IN_GAME).get(this.IN_GAME_TECHNOLOGY_TREE).getPosX();
        final int nY = this.menus.get(this.IN_GAME).get(this.IN_GAME_TECHNOLOGY_TREE).getPosY();
        final int mX = this.menus.get(this.IN_GAME).get(this.IN_GAME_TECHNOLOGY_TREE).getMenuPosX();
        final int mY = this.menus.get(this.IN_GAME).get(this.IN_GAME_TECHNOLOGY_TREE).getMenuPosY();
        this.menus.get(this.IN_GAME).set(this.IN_GAME_TECHNOLOGY_TREE, (Object)new InGame_MissionTree());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_TECHNOLOGY_TREE).setVisible(true);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_TECHNOLOGY_TREE_EMPTY).setVisible(true);
        if (savePos) {
            this.menus.get(this.IN_GAME).get(this.IN_GAME_TECHNOLOGY_TREE).setPosX(nX);
            this.menus.get(this.IN_GAME).get(this.IN_GAME_TECHNOLOGY_TREE).setPosY(nY);
            this.menus.get(this.IN_GAME).get(this.IN_GAME_TECHNOLOGY_TREE).setMenuPosX(mX);
            this.menus.get(this.IN_GAME).get(this.IN_GAME_TECHNOLOGY_TREE).setMenuPosY(mY);
        }
        if (updateOrder) {
            this.setOrderOfMenu_TechnologyTree();
        }
    }
    
    public final void setOrderOfMenu_BattleFull() {
        this.setOrderOfMenu(this.IN_GAME_BATTLE_FULL_EMPTY);
        this.setOrderOfMenu(this.IN_GAME_BATTLE_FULL);
    }
    
    public final void rebuildInGame_BattleFull() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_BATTLE_FULL, (Object)new InGame_Battle_Full());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_BATTLE_FULL).setVisible(true);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_BATTLE_FULL_EMPTY).setVisible(true);
        this.setOrderOfMenu_BattleFull();
    }
    
    public final void rebuildInGame_TechnologyChoose(final boolean savePos, final boolean updateOrder) {
        final int nX = this.menus.get(this.IN_GAME).get(this.IN_GAME_CHOOSE_TECHNOLOGY).getPosX();
        final int nY = this.menus.get(this.IN_GAME).get(this.IN_GAME_CHOOSE_TECHNOLOGY).getPosY();
        final int mX = this.menus.get(this.IN_GAME).get(this.IN_GAME_CHOOSE_TECHNOLOGY).getMenuPosX();
        final int mY = this.menus.get(this.IN_GAME).get(this.IN_GAME_CHOOSE_TECHNOLOGY).getMenuPosY();
        this.menus.get(this.IN_GAME).set(this.IN_GAME_CHOOSE_TECHNOLOGY, (Object)new InGame_TechnologyChoose());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CHOOSE_TECHNOLOGY).setVisible(true);
        if (updateOrder) {
            this.rebuildInGame_TechnologyChoose_HideMenus();
        }
        if (savePos) {
            this.menus.get(this.IN_GAME).get(this.IN_GAME_CHOOSE_TECHNOLOGY).setPosX(nX);
            this.menus.get(this.IN_GAME).get(this.IN_GAME_CHOOSE_TECHNOLOGY).setPosY(nY);
            this.menus.get(this.IN_GAME).get(this.IN_GAME_CHOOSE_TECHNOLOGY).setMenuPosX(mX);
            this.menus.get(this.IN_GAME).get(this.IN_GAME_CHOOSE_TECHNOLOGY).setMenuPosY(mY);
        }
    }
    
    public final void rebuildInGame_TechnologyChoose_HideMenus() {
        this.hideCourtCiv();
        this.setVisibleInGame_Buildings(false, false);
        this.setVisibleInGame_Wonder(false);
        this.setVisibleInGame_Generals(false);
        this.setVisibleInGame_GeneralRecruit(false);
        if (this.getVisibleInGame_ProvinceArmy()) {
            this.setVisibleInGame_ProvinceArmy(false);
        }
        if (this.getVisibleInGame_Battle()) {
            this.setVisibleInGame_Battle(false);
        }
        if (this.getVisibleInGame_Siege()) {
            this.setVisibleInGame_Siege(false);
        }
        if (this.getVisibleInGame_War()) {
            this.setVisibleInGame_War(false);
        }
        if (this.getVisibleInGame_Peace()) {
            this.setVisibleInGame_Peace(false);
        }
        this.setVisibleInGame_ReorganizeUnits(false);
        this.setVisibleInGame_DisbandUnits(false);
        this.setVisibleInGame_RecruitArmy(false);
        if (this.getVisibleInGame_GoodsMarket()) {
            this.setVisibleInGame_GoodsMarket(false);
        }
        if (this.getVisibleDeclareWar()) {
            this.setVisibleInGame_PopUp(false);
        }
        if (this.getVisible_SpecialAlliance()) {
            this.setVisibleInGame_PopUp(false);
        }
        if (this.getVisibleFormCiv() || this.getVisibleSellProvince() || this.getVisibleRevolutions()) {
            this.setVisibleInGame_PopUp(false);
        }
        if (this.getVisibleInGame_Nukes()) {
            this.setVisibleInGame_Nukes(false);
        }
        if (this.getVisibleInGame_CurrentSituation()) {
            this.setVisibleInGame_CurrentSituation(false);
        }
        if (this.getVisibleInGame_Armies()) {
            this.setVisibleInGame_Armies(false);
        }
        this.setVisibleInGame_ProvinceInfo(false);
        Game.gameActiveProvince.resetLastActiveProvince();
        Game.setActiveProvinceID(-1);
        Game.clearActiveArmy();
        this.setOrderOfMenu(this.IN_GAME_CHOOSE_TECHNOLOGY);
    }
    
    public final void rebuildInGame_ListOfBuildings() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_CHOOSE_TECHNOLOGY, (Object)new InGame_ListOfBuildings());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CHOOSE_TECHNOLOGY).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_CHOOSE_TECHNOLOGY);
    }
    
    public final void rebuildInGame_ListOfUnits() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_CHOOSE_TECHNOLOGY, (Object)new InGame_ListOfUnits());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CHOOSE_TECHNOLOGY).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_CHOOSE_TECHNOLOGY);
    }
    
    public final void rebuildInGame_CivilizationAdvantages_2() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_CHOOSE_TECHNOLOGY, (Object)new InGame_CivilizationAdvantages2());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CHOOSE_TECHNOLOGY).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_CHOOSE_TECHNOLOGY);
    }
    
    public final void rebuildInGame_CivilizationAdvantages(final int nActiveCivID) {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_CHOOSE_TECHNOLOGY, (Object)new InGame_CivilizationAdvantages(nActiveCivID));
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CHOOSE_TECHNOLOGY).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_CHOOSE_TECHNOLOGY);
    }
    
    public final void rebuildInGame_CivilizationAdvantages_SavePos_2() {
        final int nX = this.menus.get(this.IN_GAME).get(this.IN_GAME_CHOOSE_TECHNOLOGY).getPosX();
        final int nY = this.menus.get(this.IN_GAME).get(this.IN_GAME_CHOOSE_TECHNOLOGY).getPosY();
        final int mX = this.menus.get(this.IN_GAME).get(this.IN_GAME_CHOOSE_TECHNOLOGY).getMenuPosX();
        final int mY = this.menus.get(this.IN_GAME).get(this.IN_GAME_CHOOSE_TECHNOLOGY).getMenuPosY();
        this.menus.get(this.IN_GAME).set(this.IN_GAME_CHOOSE_TECHNOLOGY, (Object)new InGame_CivilizationAdvantages2());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CHOOSE_TECHNOLOGY).setVisible(true);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CHOOSE_TECHNOLOGY).setPosX(nX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CHOOSE_TECHNOLOGY).setPosY(nY);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CHOOSE_TECHNOLOGY).setMenuPosX(mX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CHOOSE_TECHNOLOGY).setMenuPosY(mY);
        this.setOrderOfMenu(this.IN_GAME_CHOOSE_TECHNOLOGY);
    }
    
    public final void rebuildInGame_CivilizationAdvantages_SavePos(final int nActiveCivID) {
        final int nX = this.menus.get(this.IN_GAME).get(this.IN_GAME_CHOOSE_TECHNOLOGY).getPosX();
        final int nY = this.menus.get(this.IN_GAME).get(this.IN_GAME_CHOOSE_TECHNOLOGY).getPosY();
        final int mX = this.menus.get(this.IN_GAME).get(this.IN_GAME_CHOOSE_TECHNOLOGY).getMenuPosX();
        final int mY = this.menus.get(this.IN_GAME).get(this.IN_GAME_CHOOSE_TECHNOLOGY).getMenuPosY();
        this.menus.get(this.IN_GAME).set(this.IN_GAME_CHOOSE_TECHNOLOGY, (Object)new InGame_CivilizationAdvantages(nActiveCivID));
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CHOOSE_TECHNOLOGY).setVisible(true);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CHOOSE_TECHNOLOGY).setPosX(nX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CHOOSE_TECHNOLOGY).setPosY(nY);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CHOOSE_TECHNOLOGY).setMenuPosX(mX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CHOOSE_TECHNOLOGY).setMenuPosY(mY);
        this.setOrderOfMenu(this.IN_GAME_CHOOSE_TECHNOLOGY);
    }
    
    public final void rebuildInGame_Nukes() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_NUKES, (Object)new InGame_Nukes());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_NUKES).setVisible(true);
        this.hideCourtCiv();
        this.setVisibleInGame_Buildings(false, false);
        this.setVisibleInGame_Wonder(false);
        this.setVisibleInGame_Generals(false);
        this.setVisibleInGame_GeneralRecruit(false);
        this.setVisibleInGame_ProvinceArmy(false);
        this.setVisibleInGame_Battle(false);
        if (this.getVisibleInGame_Siege()) {
            this.setVisibleInGame_Siege(false);
        }
        if (this.getVisibleInGame_War()) {
            this.setVisibleInGame_War(false);
        }
        if (this.getVisibleInGame_Peace()) {
            this.setVisibleInGame_Peace(false);
        }
        this.setVisibleInGame_ReorganizeUnits(false);
        this.setVisibleInGame_DisbandUnits(false);
        this.setVisibleInGame_RecruitArmy(false);
        this.setVisibleInGame_TechnologyChoose(false);
        if (this.getVisibleInGame_GoodsMarket()) {
            this.setVisibleInGame_GoodsMarket(false);
        }
        if (this.getVisibleDeclareWar()) {
            this.setVisibleInGame_PopUp(false);
        }
        if (this.getVisible_SpecialAlliance()) {
            this.setVisibleInGame_PopUp(false);
        }
        if (this.getVisibleFormCiv() || this.getVisibleSellProvince() || this.getVisibleRevolutions()) {
            this.setVisibleInGame_PopUp(false);
        }
        if (this.getVisibleInGame_CurrentSituation()) {
            this.setVisibleInGame_CurrentSituation(false);
        }
        if (this.getVisibleInGame_Armies()) {
            this.setVisibleInGame_Armies(false);
        }
        this.setVisibleInGame_ProvinceInfo(false);
        Game.gameActiveProvince.resetLastActiveProvince();
        Game.setActiveProvinceID(-1);
        Game.clearActiveArmy();
        this.setOrderOfMenu(this.IN_GAME_NUKES);
    }
    
    public final void rebuildInGame_Legacies() {
        this.menus.get(this.IN_GAME_LEGACIES).set(0, (Object)new InGame_Legacies());
    }
    
    public final void rebuildInGame_Goods_LargestProducers() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_GOODS, (Object)new InGame_Goods_LargestProducers());
        this.setVisibleInGame_Goods(true);
    }
    
    public final void rebuildInGame_GraphPopulation() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_GOODS, (Object)new InGame_GraphPopulation());
        this.setVisibleInGame_Goods(true);
    }
    
    public final void rebuildInGame_Goods_Provinces() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_GOODS, (Object)new InGame_Goods_Provinces());
        this.setVisibleInGame_Goods(true);
    }
    
    public final void rebuildInGame_GoodsMarket() {
        this.setVisibleInGame_CivBonuses(false);
        this.setVisibleInGame_Court(false);
        this.setVisibleInGame_Budget(false);
        this.hideCivCourt_Menus();
        this.menus.get(this.IN_GAME).set(this.IN_GAME_GOODS_CIV, (Object)new InGame_GoodsMarket());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_GOODS_CIV).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_GOODS_CIV);
    }
    
    public final void rebuildInGame_GoodsMarketResource() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_GOODS_CIV, (Object)new InGame_GoodsMarket_Resource());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_GOODS_CIV).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_GOODS_CIV);
    }
    
    public final void rebuildInGame_Goods() {
        this.rebuildInGame_Goods(false);
    }
    
    public final void rebuildInGame_Goods(final boolean saveMenuPos) {
        if (saveMenuPos) {
            final int nX = this.menus.get(this.IN_GAME).get(this.IN_GAME_GOODS).getPosX();
            final int nY = this.menus.get(this.IN_GAME).get(this.IN_GAME_GOODS).getPosY();
            final int mX = this.menus.get(this.IN_GAME).get(this.IN_GAME_GOODS).getMenuPosX();
            final int mY = this.menus.get(this.IN_GAME).get(this.IN_GAME_GOODS).getMenuPosY();
            this.menus.get(this.IN_GAME).set(this.IN_GAME_GOODS, (Object)new InGame_Goods());
            this.menus.get(this.IN_GAME).get(this.IN_GAME_GOODS).setPosX(nX);
            this.menus.get(this.IN_GAME).get(this.IN_GAME_GOODS).setPosY(nY);
            this.menus.get(this.IN_GAME).get(this.IN_GAME_GOODS).setMenuPosX(mX);
            this.menus.get(this.IN_GAME).get(this.IN_GAME_GOODS).setMenuPosY(mY);
        }
        else {
            this.menus.get(this.IN_GAME).set(this.IN_GAME_GOODS, (Object)new InGame_Goods());
        }
    }
    
    public final void rebuildInGame_MessagesSavePos() {
        final int nX = this.menus.get(this.IN_GAME).get(this.IN_GAME_MESSAGES).getPosX();
        final int nY = this.menus.get(this.IN_GAME).get(this.IN_GAME_MESSAGES).getPosY();
        final int mX = this.menus.get(this.IN_GAME).get(this.IN_GAME_MESSAGES).getMenuPosX();
        final int mY = this.menus.get(this.IN_GAME).get(this.IN_GAME_MESSAGES).getMenuPosY();
        this.menus.get(this.IN_GAME).set(this.IN_GAME_MESSAGES, (Object)new InGame_Messages());
        this.menus.get(this.IN_GAME).get(this.IN_GAME_MESSAGES).setPosX(nX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_MESSAGES).setPosY(nY);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_MESSAGES).setMenuPosX(mX);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_MESSAGES).setMenuPosY(mY);
    }
    
    public final void rebuildInGame_Messages() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_MESSAGES, (Object)new InGame_Messages());
    }
    
    public final void rebuildInGame_Wars() {
        try {
            this.menus.get(this.IN_GAME).set(this.IN_GAME_WARS, (Object)new InGame_MessagesWars());
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final void rebuildInGame_Wonder() {
        this.menus.get(this.IN_GAME).set(this.IN_GAME_WONDER, (Object)new InGame_Wonder(true));
        this.setVisibleInGame_Generals(false);
        this.setVisibleInGame_Buildings(false, false);
        this.setVisibleInGame_RecruitArmy(false);
        if (this.getVisibleInGame_Armies()) {
            this.setVisibleInGame_Armies(false);
        }
    }
    
    public final void rebuildInGame_Info(final String sText, final String sText2) {
        if (Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).OVER_IMAGE_ID > 2) {
            this.menus.get(this.IN_GAME).set(this.IN_GAME_INFO, (Object)new InGame_Info_2(sText, sText2));
        }
        else {
            this.menus.get(this.IN_GAME).set(this.IN_GAME_INFO, (Object)new InGame_Info(sText, sText2));
        }
        this.setVisibleInGame_Info(true);
        this.setOrderOfMenu(this.IN_GAME_INFO);
    }
    
    public final void rebuildInGameLegacies_Info(final String sText, final String sText2) {
        if (Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).OVER_IMAGE_ID > 2) {
            this.menus.get(this.IN_GAME_LEGACIES).set(this.IN_GAME_LEGACIES_INFO, (Object)new InGame_Info_2(sText, sText2));
        }
        else {
            this.menus.get(this.IN_GAME_LEGACIES).set(this.IN_GAME_LEGACIES_INFO, (Object)new InGame_Info(sText, sText2));
        }
        this.menus.get(this.IN_GAME_LEGACIES).get(this.IN_GAME_LEGACIES_INFO).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_LEGACIES_INFO);
    }
    
    public final void rebuildInGame_Buildings(final boolean updateOrder) {
        final boolean odlVisible = this.menus.get(this.IN_GAME).get(this.IN_GAME_BUILDINGS).getVisible();
        final List<Point_XY> lBuildings = new ArrayList<Point_XY>();
        final List<Point_XY> lBuildingsMenu = new ArrayList<Point_XY>();
        if (odlVisible) {
            lBuildings.add(new Point_XY(this.menus.get(this.IN_GAME).get(this.IN_GAME_BUILDINGS_GROUP_0).getPosX(), this.menus.get(this.IN_GAME).get(this.IN_GAME_BUILDINGS_GROUP_0).getPosY()));
            lBuildingsMenu.add(new Point_XY(this.menus.get(this.IN_GAME).get(this.IN_GAME_BUILDINGS_GROUP_0).getMenuPosX(), this.menus.get(this.IN_GAME).get(this.IN_GAME_BUILDINGS_GROUP_0).getMenuPosY()));
            lBuildings.add(new Point_XY(this.menus.get(this.IN_GAME).get(this.IN_GAME_BUILDINGS_GROUP_1).getPosX(), this.menus.get(this.IN_GAME).get(this.IN_GAME_BUILDINGS_GROUP_1).getPosY()));
            lBuildingsMenu.add(new Point_XY(this.menus.get(this.IN_GAME).get(this.IN_GAME_BUILDINGS_GROUP_1).getMenuPosX(), this.menus.get(this.IN_GAME).get(this.IN_GAME_BUILDINGS_GROUP_1).getMenuPosY()));
            lBuildings.add(new Point_XY(this.menus.get(this.IN_GAME).get(this.IN_GAME_BUILDINGS_GROUP_2).getPosX(), this.menus.get(this.IN_GAME).get(this.IN_GAME_BUILDINGS_GROUP_2).getPosY()));
            lBuildingsMenu.add(new Point_XY(this.menus.get(this.IN_GAME).get(this.IN_GAME_BUILDINGS_GROUP_2).getMenuPosX(), this.menus.get(this.IN_GAME).get(this.IN_GAME_BUILDINGS_GROUP_2).getMenuPosY()));
        }
        this.menus.get(this.IN_GAME).set(this.IN_GAME_BUILDINGS, (Object)new InGame_BuildingsGroup());
        this.menus.get(this.IN_GAME).set(this.IN_GAME_BUILDINGS_GROUP_0, (Object)new InGame_BuildingsGroupID(0));
        this.menus.get(this.IN_GAME).set(this.IN_GAME_BUILDINGS_GROUP_1, (Object)new InGame_BuildingsGroupID(1));
        this.menus.get(this.IN_GAME).set(this.IN_GAME_BUILDINGS_GROUP_2, (Object)new InGame_BuildingsGroupID(2));
        if (InGame_BuildingsGroup.isCapital) {
            this.menus.get(this.IN_GAME).set(this.IN_GAME_BUILDINGS_GROUP_3, (Object)new InGame_BuildingsGroupID(3));
        }
        this.setVisibleInGame_Buildings(true, updateOrder);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_BUILDINGS_GROUP_3).setVisible(InGame_BuildingsGroup.isCapital);
        this.setVisibleInGame_RecruitArmy(false);
        this.setVisibleInGame_Wonder(false);
        this.setVisibleInGame_Generals(false);
        if (this.getVisibleInGame_GoodsMarket()) {
            this.setVisibleInGame_GoodsMarket(false);
        }
        if (this.getVisibleInGame_Armies()) {
            this.setVisibleInGame_Armies(false);
        }
        if (odlVisible) {
            InGame_BuildingsGroup.lTime = 0L;
            this.menus.get(this.IN_GAME).get(this.IN_GAME_BUILDINGS_GROUP_0).setPosX(lBuildings.get(0).getPosX());
            this.menus.get(this.IN_GAME).get(this.IN_GAME_BUILDINGS_GROUP_0).setPosY(lBuildings.get(0).getPosY());
            this.menus.get(this.IN_GAME).get(this.IN_GAME_BUILDINGS_GROUP_0).setMenuPosX(lBuildingsMenu.get(0).getPosX());
            this.menus.get(this.IN_GAME).get(this.IN_GAME_BUILDINGS_GROUP_0).setMenuPosY(lBuildingsMenu.get(0).getPosY());
            this.menus.get(this.IN_GAME).get(this.IN_GAME_BUILDINGS_GROUP_1).setPosX(lBuildings.get(1).getPosX());
            this.menus.get(this.IN_GAME).get(this.IN_GAME_BUILDINGS_GROUP_1).setPosY(lBuildings.get(1).getPosY());
            this.menus.get(this.IN_GAME).get(this.IN_GAME_BUILDINGS_GROUP_1).setMenuPosX(lBuildingsMenu.get(1).getPosX());
            this.menus.get(this.IN_GAME).get(this.IN_GAME_BUILDINGS_GROUP_1).setMenuPosY(lBuildingsMenu.get(1).getPosY());
            this.menus.get(this.IN_GAME).get(this.IN_GAME_BUILDINGS_GROUP_2).setPosX(lBuildings.get(2).getPosX());
            this.menus.get(this.IN_GAME).get(this.IN_GAME_BUILDINGS_GROUP_2).setPosY(lBuildings.get(2).getPosY());
            this.menus.get(this.IN_GAME).get(this.IN_GAME_BUILDINGS_GROUP_2).setMenuPosX(lBuildingsMenu.get(2).getPosX());
            this.menus.get(this.IN_GAME).get(this.IN_GAME_BUILDINGS_GROUP_2).setMenuPosY(lBuildingsMenu.get(2).getPosY());
            lBuildings.clear();
            lBuildingsMenu.clear();
        }
    }
    
    public final void rebuildEditorGameCivs() {
        this.menus.get(this.EDITOR_GAMECIVS).get(0).setVisible(false);
        this.menus.get(this.EDITOR_GAMECIVS).set(0, (Object)new GameCivs());
        this.menus.get(this.EDITOR_GAMECIVS).set(1, (Object)new GameCivsAlphabet());
    }
    
    public final boolean getVisibleScenario_Calendar() {
        return this.menus.get(this.SCENARIO_SETTINGS).get(this.SCENARIO_CALENDAR).getVisible();
    }
    
    public final void setVisibleScenario_Calendar(final boolean visible) {
        this.menus.get(this.SCENARIO_SETTINGS).get(this.SCENARIO_CALENDAR).setVisible(visible);
    }
    
    public final boolean getVisibleInGame_ProvinceArmy() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_ARMY).getVisible();
    }
    
    public final boolean getVisibleInGame_ProvinceInfo() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_INFO).getVisible();
    }
    
    public final boolean getVisibleInGame_RecruitArmy() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_RECRUIT_ARMY).getVisible();
    }
    
    public final boolean getVisibleInGame_CurrentSituation() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_CURRENT_SITUATION).getVisible();
    }
    
    public final boolean getVisibleInGame_Generals() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_GENERALS).getVisible();
    }
    
    public final boolean getVisibleInGame_Armies() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_ARMIES).getVisible();
    }
    
    public final boolean getVisibleInGame_CivBonuses() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_CIV_BONUSES).getVisible();
    }
    
    public final boolean getVisibleInGame_Court() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).getVisible();
    }
    
    public final boolean getVisibleInGame_Budget() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_BUDGET).getVisible();
    }
    
    public final boolean getVisibleInGame_Civ() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_CIV).getVisible();
    }
    
    public final void getInGame_Civ_updateLanguage() {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CIV).updateLanguage();
    }
    
    public final void getInGame_ProvinceInfo_updateLanguage() {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_INFO).updateLanguage();
    }
    
    public final boolean getVisibleInGame_ProvinceBonuses() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_BONUSES).getVisible();
    }
    
    public final boolean getVisibleInGame_GeneralRecruit() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_GENERAL_RECRUIT).getVisible();
    }
    
    public final boolean getVisibleInGame_TakeLoan() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_TAKE_LOAN).getVisible();
    }
    
    public final boolean getVisibleInGame_TakeLoanRepay() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_TAKE_LOAN_REPAY).getVisible();
    }
    
    public final boolean getVisibleInGame_Console() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_CONSOLE).getVisible();
    }
    
    public final boolean getVisibleInGame_SaveGame() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_SAVE_GAME).getVisible();
    }
    
    public final boolean getVisibleInGame_PopUp() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).getVisible();
    }
    
    public final boolean getVisibleInGame_AdvisorRecruit() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_ADVISOR_RECRUIT).getVisible();
    }
    
    public final boolean getVisibleInGame_TechnologyTree() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_TECHNOLOGY_TREE).getVisible();
    }
    
    public final boolean getVisibleInGame_BattleFull() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_BATTLE_FULL).getVisible();
    }
    
    public final boolean getVisibleInGame_TechnologyChoose() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_CHOOSE_TECHNOLOGY).getVisible();
    }
    
    public final boolean getVisibleInGame_Nukes() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_NUKES).getVisible();
    }
    
    public final boolean getVisibleInGame_Goods() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_GOODS).getVisible();
    }
    
    public final boolean getVisibleInGame_GoodsMarket() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_GOODS_CIV).getVisible();
    }
    
    public final boolean getVisibleInGame_Wonder() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_WONDER).getVisible();
    }
    
    public final boolean getVisibleInGame_Info() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_INFO).getVisible();
    }
    
    public final boolean getVisibleInGame_Buildings() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_BUILDINGS).getVisible();
    }
    
    public final boolean getVisibleInGame_DisbandArmy() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_DISBAND_UNITS).getVisible();
    }
    
    public final boolean getVisibleInGame_ReorganizeUnits() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_REORGANIZE_UNITS).getVisible();
    }
    
    public final void setVisibleInGame_RecruitArmy(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_RECRUIT_ARMY).setVisible(visible);
        if (visible) {
            this.hideMenus_RecruitArmy(visible);
        }
        else {
            this.menus.get(this.IN_GAME).get(this.IN_GAME_RECRUIT_ARMY_BATTLEFIELD).setVisible(false);
        }
    }
    
    public final void setVisibleInGame_RecruitArmy_Battlefield(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_RECRUIT_ARMY_BATTLEFIELD).setVisible(visible);
    }
    
    public final void hideMenus_RecruitArmy(final boolean visible) {
        this.hideCourtCiv();
        this.setVisibleInGame_Buildings(false, false);
        if (this.getVisibleInGame_Wonder()) {
            this.setVisibleInGame_Wonder(false);
        }
        if (this.getVisibleInGame_Generals()) {
            this.setVisibleInGame_Generals(false);
        }
        if (this.getVisibleInGame_GeneralRecruit()) {
            this.setVisibleInGame_GeneralRecruit(false);
        }
        if (this.getVisibleInGame_ProvinceArmy()) {
            this.setVisibleInGame_ProvinceArmy(false);
        }
        if (this.getVisibleInGame_Battle()) {
            this.setVisibleInGame_Battle(false);
        }
        if (this.getVisibleInGame_Siege()) {
            this.setVisibleInGame_Siege(false);
        }
        if (this.getVisibleInGame_War()) {
            this.setVisibleInGame_War(false);
        }
        if (this.getVisibleInGame_Peace()) {
            this.setVisibleInGame_Peace(false);
        }
        if (this.getVisibleInGame_ReorganizeUnits()) {
            this.setVisibleInGame_ReorganizeUnits(false);
        }
        if (this.getVisibleInGame_DisbandArmy()) {
            this.setVisibleInGame_DisbandUnits(false);
        }
        if (this.getVisibleInGame_TechnologyChoose()) {
            this.setVisibleInGame_TechnologyChoose(false);
        }
        if (this.getVisibleInGame_TechnologyTree()) {
            this.setVisibleInGame_TechnologyTree(false);
        }
        if (this.getVisibleDeclareWar()) {
            this.setVisibleInGame_PopUp(false);
        }
        if (this.getVisible_SpecialAlliance()) {
            this.setVisibleInGame_PopUp(false);
        }
        if (this.getVisibleFormCiv() || this.getVisibleSellProvince() || this.getVisibleRevolutions()) {
            this.setVisibleInGame_PopUp(false);
        }
        if (this.getVisibleInGame_CurrentSituation()) {
            this.setVisibleInGame_CurrentSituation(false);
        }
        if (this.getVisibleInGame_GoodsMarket()) {
            this.setVisibleInGame_GoodsMarket(false);
        }
        if (this.getVisibleInGame_Nukes()) {
            this.setVisibleInGame_Nukes(false);
        }
        this.setVisibleInGame_ProvinceInfo(false);
        if (visible) {
            Game.gameActiveProvince.resetLastActiveProvince();
            Game.setActiveProvinceID(-1);
        }
        Game.clearActiveArmy();
        this.setOrderOfMenu(this.IN_GAME_RECRUIT_ARMY_BATTLEFIELD);
        this.setOrderOfMenu(this.IN_GAME_RECRUIT_ARMY);
    }
    
    public final void setVisibleInGame_Generals(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_GENERALS).setVisible(visible);
        if (visible) {
            this.hideCourtCiv();
            this.setVisibleInGame_TechnologyChoose(false);
            this.setVisibleInGame_TechnologyTree(false);
            this.setVisibleInGame_Buildings(false, false);
            this.setVisibleInGame_Wonder(false);
            this.setVisibleInGame_RecruitArmy(false);
            if (this.getVisibleInGame_GoodsMarket()) {
                this.setVisibleInGame_GoodsMarket(false);
            }
            this.setOrderOfMenu(this.IN_GAME_GENERALS);
        }
    }
    
    public final void setOrderInGame_Generals() {
        this.setOrderOfMenu(this.IN_GAME_GENERALS);
    }
    
    public final void setVisibleInGame_Armies(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_ARMIES).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu(this.IN_GAME_ARMIES);
        }
    }
    
    public final void setVisibleInGame_Right(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_RIGHT).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu(this.IN_GAME_RIGHT);
        }
    }
    
    public final void setVisibleInGame_Notifications(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_NOTIFICATION).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu(this.IN_GAME_NOTIFICATION);
        }
    }
    
    public final void setVisibleInGame_CivBonuses(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CIV_BONUSES).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu(this.IN_GAME_CIV_BONUSES);
        }
    }
    
    public final void setVisibleInGame_Court(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT).setVisible(visible);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT_OPTIONS).setVisible(visible);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT_OPTIONS2).setVisible(!Game.settingsManager.enableHideSideMenu || visible);
        if (visible) {
            this.setVisibleInGame_Civ(false);
            this.setVisibleInGame_Budget(false);
            this.hideCivCourt_Menus();
            if (this.getVisibleInGame_GoodsMarket()) {
                this.setVisibleInGame_GoodsMarket(false);
            }
            this.setOrderOfMenu_InGameCourt();
        }
        else {
            InGame_CourtOptions.iActiveID = -1;
            this.setVisibleInGame_AdvisorRecruit(false);
            InGame_CourtOptions.disableAllViews();
        }
    }
    
    public final void setInGame_CivOptions_Title(final String sText) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_COURT_OPTIONS).getTitle().setText(sText);
    }
    
    public final void setVisibleInGame_Budget(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_BUDGET).setVisible(visible);
        if (visible) {
            this.setVisibleInGame_Civ(false);
            this.setVisibleInGame_Court(false);
            this.hideCivCourt_Menus();
            if (this.getVisibleInGame_GoodsMarket()) {
                this.setVisibleInGame_GoodsMarket(false);
            }
            this.setOrderOfMenu(this.IN_GAME_BUDGET);
        }
        else {
            this.setVisibleInGame_AdvisorRecruit(false);
        }
    }
    
    private final void hideCivCourt_Menus() {
        if (this.getVisibleInGame_ProvinceInfo()) {
            this.setVisibleInGame_ProvinceInfo(false);
            Game.gameActiveProvince.resetLastActiveProvince();
            Game.setActiveProvinceID(-1);
        }
        if (this.getVisibleInGame_ProvinceArmy()) {
            Game.clearActiveArmy();
            this.setVisibleInGame_ProvinceArmy(false);
        }
        if (this.getVisibleInGame_Battle()) {
            this.setVisibleInGame_Battle(false);
        }
        if (this.getVisibleInGame_Siege()) {
            this.setVisibleInGame_Siege(false);
        }
        if (this.getVisibleInGame_War()) {
            this.setVisibleInGame_War(false);
        }
        if (this.getVisibleInGame_Peace()) {
            this.setVisibleInGame_Peace(false);
        }
        if (this.getVisibleInGame_RecruitArmy()) {
            this.setVisibleInGame_RecruitArmy(false);
        }
        if (this.getVisibleInGame_Armies()) {
            this.setVisibleInGame_Armies(false);
        }
        if (this.getVisibleInGame_DisbandArmy()) {
            this.setVisibleInGame_DisbandUnits(false);
        }
        if (this.getVisibleInGame_ReorganizeUnits()) {
            this.setVisibleInGame_ReorganizeUnits(false);
        }
        if (this.getVisibleInGame_TechnologyChoose()) {
            this.setVisibleInGame_TechnologyChoose(false);
        }
        if (this.getVisibleDeclareWar()) {
            this.setVisibleInGame_PopUp(false);
        }
        if (this.getVisible_SpecialAlliance()) {
            this.setVisibleInGame_PopUp(false);
        }
        if (this.getVisibleFormCiv() || this.getVisibleSellProvince() || this.getVisibleRevolutions()) {
            this.setVisibleInGame_PopUp(false);
        }
        if (this.getVisibleInGame_Nukes()) {
            this.setVisibleInGame_Nukes(false);
        }
        if (this.getVisibleInGame_CurrentSituation()) {
            this.setVisibleInGame_CurrentSituation(false);
        }
        if (this.getVisibleInGame_TechnologyTree()) {
            this.setVisibleInGame_TechnologyTree(false);
        }
        if (this.getVisibleInGame_Wonder()) {
            this.setVisibleInGame_Wonder(false);
        }
        if (this.getVisibleInGame_Generals()) {
            this.setVisibleInGame_Generals(false);
        }
        if (this.getVisibleInGame_GeneralRecruit()) {
            this.setVisibleInGame_GeneralRecruit(false);
        }
    }
    
    public final void setVisibleInGame_Civ(final boolean visible) {
        if (!visible && this.menus.get(this.IN_GAME).get(this.IN_GAME_CIV).getVisible()) {
            InGame_Civ.actionOnClose();
        }
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CIV).setVisible(visible);
        if (!visible) {
            this.setVisibleInGame_CivBonuses(false);
        }
        if (visible) {
            if (this.getVisibleInGame_Court()) {
                this.setVisibleInGame_Court(false);
            }
            if (this.getVisibleInGame_Budget()) {
                this.setVisibleInGame_Budget(false);
            }
            this.hideCivCourt_Menus();
            if (this.getVisibleInGame_GoodsMarket()) {
                this.setVisibleInGame_GoodsMarket(false);
            }
            this.setOrderOfMenu(this.IN_GAME_CIV);
        }
    }
    
    public final void setVisibleInGame_ProvinceBonuses(final boolean visible, final boolean updateOrder) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_PROVINCE_BONUSES).setVisible(visible);
        if (visible && updateOrder) {
            this.setOrderOfMenu(this.IN_GAME_PROVINCE_BONUSES);
        }
    }
    
    public final void setVisibleInGame_GeneralRecruit(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_GENERAL_RECRUIT).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu(this.IN_GAME_GENERAL_RECRUIT);
        }
    }
    
    public final void setVisibleInGame_TakeLoan(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_TAKE_LOAN).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu(this.IN_GAME_TAKE_LOAN);
        }
    }
    
    public final void setVisibleInGame_TakeLoanRepay(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_TAKE_LOAN_REPAY).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu(this.IN_GAME_TAKE_LOAN_REPAY);
        }
    }
    
    public final void setVisibleInGame_Console(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CONSOLE).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu(this.IN_GAME_CONSOLE);
        }
    }
    
    public final boolean getVisibleInGame_Event() {
        return this.menus.get(this.IN_GAME).get(this.IN_GAME_EVENT).getVisible();
    }
    
    public final void setVisibleInGame_Event(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_EVENT).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu(this.IN_GAME_EVENT);
        }
    }
    
    public final void setVisibleInGame_CurrentSituation(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CURRENT_SITUATION).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu(this.IN_GAME_CURRENT_SITUATION);
        }
    }
    
    public final void setVisibleInGame_SaveGame(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_SAVE_GAME).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu(this.IN_GAME_SAVE_GAME);
        }
    }
    
    public final void setVisibleInGame_PopUp(final boolean visible) {
        if (!visible) {
            this.menus.get(this.IN_GAME).get(this.IN_GAME_POP_UP).setVisible(visible);
        }
    }
    
    public final void setVisibleInGame_AdvisorRecruit(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_ADVISOR_RECRUIT).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu(this.IN_GAME_ADVISOR_RECRUIT);
        }
    }
    
    public final void setVisibleInGame_TechnologyTree(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_TECHNOLOGY_TREE).setVisible(visible);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_TECHNOLOGY_TREE_EMPTY).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu_TechnologyTree();
        }
    }
    
    public final void setVisibleInGame_MissionTree(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_TECHNOLOGY_TREE).setVisible(visible);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_TECHNOLOGY_TREE_EMPTY).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu_TechnologyTree();
        }
    }
    
    public final void setVisibleInGame_BattleFull(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_BATTLE_FULL).setVisible(visible);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_BATTLE_FULL_EMPTY).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu_BattleFull();
        }
    }
    
    public final void setVisibleInGame_TechnologyChoose(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_CHOOSE_TECHNOLOGY).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu(this.IN_GAME_CHOOSE_TECHNOLOGY);
        }
    }
    
    public final void setVisibleInGame_Nukes(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_NUKES).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu(this.IN_GAME_NUKES);
        }
    }
    
    public final void setVisibleInGame_Goods(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_GOODS_EMPTY).setVisible(visible);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_GOODS).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu_InGameGoods();
        }
    }
    
    public final void setVisibleInGame_GoodsMarket(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_GOODS_CIV).setVisible(visible);
        this.setOrderOfMenu(this.IN_GAME_GOODS_CIV);
    }
    
    public final void setVisibleInGame_Wonder(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_WONDER).setVisible(visible);
    }
    
    public final void setVisibleInGame_Info(final boolean visible) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_INFO).setVisible(visible);
    }
    
    public final void setVisibleInGame_Buildings(final boolean visible, final boolean updateOrder) {
        this.menus.get(this.IN_GAME).get(this.IN_GAME_BUILDINGS).setVisible(visible);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_BUILDINGS_GROUP_0).setVisible(visible);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_BUILDINGS_GROUP_1).setVisible(visible);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_BUILDINGS_GROUP_2).setVisible(visible);
        this.menus.get(this.IN_GAME).get(this.IN_GAME_BUILDINGS_GROUP_3).setVisible(visible);
        if (visible && updateOrder) {
            this.setVisibleInGame_RecruitArmy(false);
            this.setVisibleInGame_Generals(false);
            if (this.getVisibleInGame_Armies()) {
                this.setVisibleInGame_Armies(false);
            }
            this.setVisibleInGame_TechnologyChoose(false);
            this.setVisibleInGame_TechnologyTree(false);
            if (this.getVisibleDeclareWar()) {
                this.setVisibleInGame_PopUp(false);
            }
            if (this.getVisible_SpecialAlliance()) {
                this.setVisibleInGame_PopUp(false);
            }
            if (this.getVisibleFormCiv() || this.getVisibleSellProvince() || this.getVisibleRevolutions()) {
                this.setVisibleInGame_PopUp(false);
            }
            if (this.getVisibleInGame_Nukes()) {
                this.setVisibleInGame_Nukes(false);
            }
            if (this.getVisibleInGame_CurrentSituation()) {
                this.setVisibleInGame_CurrentSituation(false);
            }
            this.setOrderInGame_Buildings();
        }
    }
    
    public final void setOrderInGame_Buildings() {
        this.setOrderOfMenu(this.IN_GAME_BUILDINGS);
        this.setOrderOfMenu(this.IN_GAME_BUILDINGS_GROUP_0);
        this.setOrderOfMenu(this.IN_GAME_BUILDINGS_GROUP_1);
        this.setOrderOfMenu(this.IN_GAME_BUILDINGS_GROUP_2);
        this.setOrderOfMenu(this.IN_GAME_BUILDINGS_GROUP_3);
    }
    
    public final void updateInGameFlag() {
        try {
            this.menus.get(this.IN_GAME).get(0).getMenuElement(0).updateLanguage();
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public final Menu editorGameCivsEditReligion() {
        return this.menus.get(this.EDITOR_GAMECIVS_EDIT).get(this.EDITOR_GAMECIVS_EDIT_RELIGION);
    }
    
    public final Menu editorGameCivsEditGroup() {
        return this.menus.get(this.EDITOR_GAMECIVS_EDIT).get(this.EDITOR_GAMECIVS_EDIT_GROUP);
    }
    
    public final Menu createCivReligion() {
        return this.menus.get(this.CREATE_CIV).get(this.CREATE_CIV_RELIGION);
    }
    
    public final Menu createCivGroup() {
        return this.menus.get(this.CREATE_CIV).get(this.CREATE_CIV_GROUP);
    }
    
    public final Menu createCivFlag() {
        return this.menus.get(this.CREATE_CIV).get(this.CREATE_CIV_FLAG);
    }
    
    public final void rebuildCreateCivFlag() {
        final int nX = this.menus.get(this.CREATE_CIV).get(this.CREATE_CIV_FLAG).getPosX();
        final int nY = this.menus.get(this.CREATE_CIV).get(this.CREATE_CIV_FLAG).getPosY();
        final int mX = this.menus.get(this.CREATE_CIV).get(this.CREATE_CIV_FLAG).getMenuPosX();
        final int mY = this.menus.get(this.CREATE_CIV).get(this.CREATE_CIV_FLAG).getMenuPosY();
        this.menus.get(this.CREATE_CIV).set(this.CREATE_CIV_FLAG, (Object)new CreateCiv_Flag());
        this.menus.get(this.CREATE_CIV).get(this.CREATE_CIV_FLAG).setVisible(true);
        this.menus.get(this.CREATE_CIV).get(this.CREATE_CIV_FLAG).setPosX(nX);
        this.menus.get(this.CREATE_CIV).get(this.CREATE_CIV_FLAG).setPosY(nY);
        this.menus.get(this.CREATE_CIV).get(this.CREATE_CIV_FLAG).setMenuPosX(mX);
        this.menus.get(this.CREATE_CIV).get(this.CREATE_CIV_FLAG).setMenuPosY(mY);
    }
    
    public final void hideInGameMenus() {
        try {
            if (this.getVisibleInGame_Buildings()) {
                this.setVisibleInGame_Buildings(false, false);
            }
            if (this.getVisibleInGame_Court()) {
                this.setVisibleInGame_Court(false);
            }
            if (this.getVisibleInGame_Civ()) {
                this.setVisibleInGame_Civ(false);
            }
            if (this.getVisibleInGame_Budget()) {
                this.setVisibleInGame_Budget(false);
            }
            if (this.getVisibleInGame_CivBonuses()) {
                this.setVisibleInGame_CivBonuses(false);
            }
            if (this.getVisibleInGame_Wonder()) {
                this.setVisibleInGame_Wonder(false);
            }
            if (this.getVisibleInGame_GeneralRecruit()) {
                this.setVisibleInGame_GeneralRecruit(false);
            }
            if (this.getVisibleInGame_AdvisorRecruit()) {
                this.setVisibleInGame_AdvisorRecruit(false);
            }
            if (this.getVisibleInGame_RecruitArmy()) {
                this.setVisibleInGame_RecruitArmy(false);
            }
            if (this.getVisibleInGame_ProvinceBonuses()) {
                this.setVisibleInGame_ProvinceBonuses(false, false);
            }
            if (this.getVisibleInGame_Generals()) {
                this.setVisibleInGame_Generals(false);
            }
            if (this.getVisibleInGame_GoodsMarket()) {
                this.setVisibleInGame_GoodsMarket(false);
            }
            if (this.getVisibleInGame_Armies()) {
                this.setVisibleInGame_Armies(false);
            }
            if (this.getVisibleInGame_DisbandArmy()) {
                this.setVisibleInGame_DisbandUnits(false);
            }
            if (this.getVisibleInGame_ReorganizeUnits()) {
                this.setVisibleInGame_ReorganizeUnits(false);
            }
            if (this.getVisibleInGame_Battle()) {
                this.setVisibleInGame_Battle(false);
            }
            if (this.getVisibleInGame_Siege()) {
                this.setVisibleInGame_Siege(false);
            }
            if (this.getVisibleInGame_War()) {
                this.setVisibleInGame_War(false);
            }
            if (this.getVisibleInGame_Peace()) {
                this.setVisibleInGame_Peace(false);
            }
            if (this.getVisibleInGame_TechnologyChoose()) {
                this.setVisibleInGame_TechnologyChoose(false);
            }
            if (this.getVisibleInGame_TechnologyTree()) {
                this.setVisibleInGame_TechnologyTree(false);
            }
            if (this.getVisibleDeclareWar()) {
                this.setVisibleInGame_PopUp(false);
            }
            if (this.getVisible_SpecialAlliance()) {
                this.setVisibleInGame_PopUp(false);
            }
            if (this.getVisibleFormCiv() || this.getVisibleSellProvince() || this.getVisibleRevolutions()) {
                this.setVisibleInGame_PopUp(false);
            }
            if (this.getVisibleInGame_Nukes()) {
                this.setVisibleInGame_Nukes(false);
            }
            if (this.getVisibleInGame_CurrentSituation()) {
                this.setVisibleInGame_CurrentSituation(false);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    static {
        MenuManager.sparksAnimation = new SparksAnimation();
        MenuManager.sparksAnimationHover = new SparksAnimation();
        MenuManager.sparksAnimationSidebar = new SparksAnimation();
        MenuManager.sparksAnimationSidebarActive = new SparksAnimation();
        MenuManager.orderOfMenuInGame = false;
        MenuManager.clickAnimations = new ArrayList<ClickAnimation>();
        MenuManager.clickAnimationsSize = 0;
        MenuManager.mapEditorDrawProvinces = true;
        MenuManager.currentSituationMode = false;
        MenuManager.IN_GAME_POP_UP_MENU_ID = 0;
        MenuManager.IN_GAME_POP_UP_MENU_FORM_CIV = 39;
        MenuManager.IN_GAME_POP_UP_MENU_ID_DECLARE_WAR = 12;
    }
}
