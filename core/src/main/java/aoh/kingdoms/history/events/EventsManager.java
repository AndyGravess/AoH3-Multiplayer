// 
// Decompiled by Procyon v0.6.0
// 

package aoc.kingdoms.lukasz.events;

import com.badlogic.gdx.graphics.Texture;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusRegimentsLimit;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusAllCharactersLifeExpectancy;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusAdvisorMaxLevel;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusManpowerRecoveryFromADisbandedArmy;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusDiscipline;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusBattleWidth;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusDiplomacyPoints;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusDiseaseDeathRate;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusGeneralCost;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusAdvisorCost;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusReligionCost;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusCoreCost;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusRevolutionaryRisk;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusIncomeFromVassals;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusAggressiveExpansion;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusLoansLimit;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusLoanInterest;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusImproveRelationsModifier;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusSiegeEffectiveness;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusArmyMovementSpeed;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusMaxMorale;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusUnitsDefense;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusUnitsAttack;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusGeneralDefense;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusGeneralAttack;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusIncreaseGrowthRateCost;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusDevelopInfrastructureCost;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusIncreaseTaxEfficiencyCost;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusIncreaseManpowerCost;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusMaximumAmountOfGold;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusInvestInEconomyCost;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusConstructionTime;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusEconomyBuildingsCost;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusMilitaryBuildingsCost;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusAdministrationBuildingsCost;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusConstructionCost;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusResearchPoints;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusResearch;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusRecruitArmySecondLineCost;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusRecruitArmyFirstLineCost;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusRecruitArmyCost;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusRecruitmentTime;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusArmyMaintenance;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusWarScoreCost;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusArmyMoraleRecovery;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusReinforcementSpeed;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusManpowerRecoverySpeed;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusMaxManpowerPercentage;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusMaxManpower;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusIncomeProduction;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusIncomeEconomy;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusIncomeTaxation;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusProductionEfficiency;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusInflation;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusCorruption;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusGrowthRate;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusMaintenanceCost;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusBuildingsMaintenanceCost;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusProvinceMaintenance;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusTaxEfficiency;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusMonthlyLegacyPerc;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusMonthlyLegacy;
import aoc.kingdoms.lukasz.events.outcome.civilizationBonus.EventOutcome_BonusMonthlyIncome;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_Infrastructure_ID;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_Infrastructure_All;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_Infrastructure_Capital;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_Infrastructure;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_Unrest_ID;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_Unrest_All;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_Unrest_Capital;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_Unrest;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_Devastation_ID;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_Devastation_All;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_Devastation_Capital;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_Devastation;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_Religion_ID;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_Religion_All;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_Religion_Capital;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_Religion;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_Population_All;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_Population_Capital;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_Population;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_GrowthRate_ID;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_GrowthRate_All;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_GrowthRate_Capital;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_GrowthRate;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_Manpower_ID;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_Manpower_All;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_Manpower_Capital;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_Manpower;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_TaxEfficiency_All;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_TaxEfficiency_Capital;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_TaxEfficiency_ID;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_TaxEfficiency;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_Economy_All;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_Economy_Capital;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_Economy_ID;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_Economy;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_ID_CoreRemove;
import aoc.kingdoms.lukasz.events.outcome.province.EventOutcome_Province_ID_CoreAdd;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_PlayMusic;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_AddTruce;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_DefensivePact;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_Guarantee;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_MilitaryAccess;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_NonAggressionPact;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_Explode;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_Alliance;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_AnnexedByCivilization;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_AnnexCivilization;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_Vassalize;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_AnnexedProvincesByCivFromCiv;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_AnnexProvincesFromCiv;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_AnnexProvinces;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_AddAdvisor_Character;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_AddAdvisor;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_AddRuler;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_AddGeneral_CharacterAttackDefense;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_AddGeneral_Character;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_Resource_PriceChangeRandomDown;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_Resource_PriceChangeRandomUp;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_Resource_PriceChangeRandom;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_Resource_PriceChangeGroupDown;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_Resource_PriceChangeGroupUp;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_Resource_PriceChangeGroup;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_Resource_PriceChangeDown;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_Resource_PriceChangeUp;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_Resource_PriceChange;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_AddGeneral;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_KillRuler_Chance;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_KillRuler;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_KillAdvisor;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_LeaveAllianceSpecial;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_JoinAllianceSpecialSecondTier;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_JoinAllianceSpecialFirstTier;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_PromoteAdvisor;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_RunEvent;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_ChangeReligionCiv;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_ChangeIdeologyCiv;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_ChangeReligion;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_ChangeIdeology;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_PlayerChangeCiv;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_DeclareWar2;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_DeclareWar;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_SetCiv2;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_SetCiv;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_NuclearReactor;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_SupremeCourt;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_CapitalCityLevel;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_MilitaryAcademyForGenerals;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_MilitaryAcademy;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_AddArmy;
import java.util.ArrayList;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_AI_Aggression;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_AddVariable_Civ;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_AddVariable;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_AdvantagePoints;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_AggressiveExpansion_Set;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_Manpower;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_MoveCapital;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_Inflation;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_Research;
import aoc.kingdoms.lukasz.events.triggers.EventOutcome_Legacy_Monthly;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_Legacy;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_Gold_MonthlyIncome;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome_Gold;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivTag_GovernmentIsNot;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivTag_GovernmentIs;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivTag_ReligionIsNot;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivTag_ReligionIs;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ExistsAnyNot;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ExistsAny;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ExistsNot;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_Exists;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_IsNotPlayer;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_IsPlayer;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_IsNotVassal;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_IsVassal;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_HasVariableCiv;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_HasVariableNot;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_HasVariable;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_HaveMilitaryAccess;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_HaveGuarantee;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_HaveTruce;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_HaveAlliance;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_HaveNonAggressionPact;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_HaveDefensivePact;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_OpinionBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_OpinionOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivsAreNeighborsNot;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivsAreNeighbors;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_IsVassalOfCiv;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivRivaledCivNot;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivRivaledCiv;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivsAreRivals;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivHasMoreTechsThanCiv;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivHasHigherRankThanCiv;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivHasMoreRegimentsThanCiv;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivHasLargerRegimentsLimitThanCiv;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivHasLargerEconomyThanCiv;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivHasLargerPopulationThanCiv;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivHasMoreProvinceThanCiv;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_AreNotAtWar;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_AreAtWar;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_IsAtWar_DaysOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_IsNotAtWar;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_IsAtWar;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivIncomeProductionBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivIncomeProductionOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivIncomeEconomyBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivIncomeEconomyOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivIncomeTaxationBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivIncomeTaxationOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivResearchPerMonthBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivResearchPerMonthOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivLegacyPerMonthBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivLegacyPerMonthOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivDiplomacyBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivDiplomacyOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivIncomeBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivIncomeOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivLoansBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivLoansOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_AllianceIsNotInAlliance;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_AllianceIsInAlliance;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_AllianceIsLeaderID;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_AllianceIsLeader;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivInflationBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivInflationOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_RecruitedAdvisorsBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_RecruitedAdvisorsOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivLegacyBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivLegacyOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_IsLargestProducer;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_LargestProducer_ProductionOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivLargestProducerOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivManpower_PercOfMax_Below;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivManpower_PercOfMax_Over;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivMaxManpowerBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivMaxManpowerOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivManpowerBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivManpowerOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivAdvisor_ConstructionCostOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivAdvisor_ProductionEfficiencyOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivAdvisor_AgeOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivMilitaryAdvisorSkillBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivMilitaryAdvisorSkillOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivInnovationAdvisorSkillBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivInnovationAdvisorSkillOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivEconomyAdvisorSkillBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivEconomyAdvisorSkillOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivAdministrativeAdvisorSkillBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivAdministrativeAdvisorSkillOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivGoldOver_MaxAmountOfGold;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivRegimentsOverRegimentsLimit;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivRegimentsBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivRegimentsOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivRegimentsLimitBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivRegimentsLimitOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivNuclearReactorBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivNuclearReactorOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivSupremeCourtBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivSupremeCourtOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivCapitalCityBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivCapitalCityOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivMilitaryAcademyForGeneralsBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivMilitaryAcademyForGeneralsOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivMilitaryAcademyBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivMilitaryAcademyOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivBattleWidthBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivBattleWidthOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_IsCiv;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivRankPrestigeBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivRankPrestigeOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivRankPositionBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivRankPositionOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivUnlockedAdvantagesBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivUnlockedAdvantagesOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivUnlockedTechnologiesBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivUnlockedTechnologiesOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivUnlockedLegaciesBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivUnlockedLegaciesOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivNeighborsBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivNeighborsOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivVassalsBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivVassalsOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivNonAggressionPactsBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivNonAggressionPactsOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivDefensivePactsBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivDefensivePactsOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivAlliesBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivAlliesOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivGovernmentIs;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivReligionIs;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivGoldBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivGoldOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivCapital_IsUnderSiege;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivCapital_IsOccupiedNot;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivCapital_IsOccupied;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivCapital_FortLevelBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivCapital_FortLevelOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivCapital_PopulationBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivCapital_PopulationOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivCapital_InfrastructureBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivCapital_InfrastructureOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivCapital_IncomeBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivCapital_IncomeOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivCapital_GrowthRateBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivCapital_GrowthRateOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivCapital_UnrestBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivCapital_UnrestOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivCapital_ManpowerBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivCapital_ManpowerOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivCapital_EconomyBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivCapital_EconomyOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivCapital_ContinentIsNot;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivCapital_ContinentIs;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivCapital_TaxEfficiencyBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivCapital_TaxEfficiencyOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivCapital_BuildingsBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivCapital_BuildingsOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivEconomyBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivEconomyOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivPopulationBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivPopulationOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivProvincesEquals;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivProvincesBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivProvincesOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ExactDay;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_YearBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_YearOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_PlayingTimeBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_PlayingTimeOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_Buildings_ConstructedCapitalBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_Buildings_ConstructedCapitalOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_Buildings_ConstructedMilitaryBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_Buildings_ConstructedMilitaryOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_Buildings_ConstructedEconomyBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_Buildings_ConstructedEconomyOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_Buildings_ConstructedAdministrativeBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_Buildings_ConstructedAdministrativeOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_Buildings_ConstructedBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_Buildings_ConstructedOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ProvinceIsUnderSiege;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ProvinceIsOccupiedNot;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ProvinceIsOccupied;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ProvinceIsCapital;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ProvinceBuildingsLimitOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivCapital_ReligionIs;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivCapital_HasBuilding;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ProvinceHasBuilding;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ProvinceControlledByCivNot;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ProvinceControlledByCiv;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ProvinceCivHasCore;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ProvinceDefenseLevelBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ProvinceDefenseLevelOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ProvinceBuildingsBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ProvinceBuildingsOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ProvinceInfrastructureBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ProvinceInfrastructureOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ProvinceUnrestBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ProvinceUnrestOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ProvinceReligionIsNot;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ProvinceReligionIs;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ProvinceIncomeBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ProvinceIncomeOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ProvinceManpowerBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ProvinceManpowerOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ProvincePopulationBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ProvincePopulationOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ProvinceTaxEfficiencyBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ProvinceTaxEfficiencyOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ProvinceGrowthRateBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ProvinceGrowthRateOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ProvinceEconomyBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ProvinceEconomyOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivHasResourceOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivHasResource;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_IncreasedManpowerBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_IncreasedManpowerOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_IncreasedTaxEfficiencyBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_IncreasedTaxEfficiencyOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_IncreasedGrowthRateBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_IncreasedGrowthRateOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivWarsTotalBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_CivWarsTotalOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ConqueredProvincesBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_ConqueredProvincesOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_DevelopedInfrastructureBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_DevelopedInfrastructureOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_InvestedInEconomyBelow;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_InvestedInEconomyOver;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_Value;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger_RandomChance;
import aoc.kingdoms.lukasz.events.triggers.EventTrigger;
import com.codedisaster.steamworks.SteamUGC;
import aoc.kingdoms.lukasz.jakowski.Steam.SteamManager;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.Gdx;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.map.ResourcesManager;
import aoc.kingdoms.lukasz.events.outcome.EventOutcome;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.SoundsManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.map.civilization.CivilizationEventsData_Variables;
import java.util.List;
import aoc.kingdoms.lukasz.textures.Image;

public class EventsManager
{
    public static Image eventIMG;
    public static String loadedEventIMG;
    public static List<Event> events;
    public static int iEventsSize;
    public static List<Event> eventsSiege;
    public static int iEventsSiegeSize;
    public static List<Event> eventsGlobal;
    public static int iEventsGlobalSize;
    public static List<Event> eventsScenario;
    public static int iEventsScenarioSize;
    public static List<Event> eventsSiegeScenario;
    public static int iEventsSiegeScenarioSize;
    public static List<Event> eventsGlobalScenario;
    public static int iEventsGlobalScenarioSize;
    public static CivilizationEventsData_Variables eventsGlobal_Variables;
    public static List<Image> missionImages;
    public static List<String> runEvent;
    public static List<Event_ExactDate> exactDate_Events;
    public static List<Event_ExactDate> exactDate_EventsScenario;
    public static int runEventGlobalID;
    public static int runEventGlobalID_Scenario;
    public static String loadScenarioEventsTag;
    
    public static Event getActiveEvent(final int id, final int eventType) {
        switch (eventType) {
            case 0: {
                return EventsManager.events.get(id);
            }
            case 1: {
                return EventsManager.eventsSiege.get(id);
            }
            case 2: {
                return EventsManager.eventsGlobal.get(id);
            }
            case 3: {
                return EventsManager.eventsScenario.get(id);
            }
            case 4: {
                return EventsManager.eventsSiegeScenario.get(id);
            }
            case 5: {
                return EventsManager.eventsGlobalScenario.get(id);
            }
            default: {
                return EventsManager.events.get(id);
            }
        }
    }
    
    public static final void runEvents(final int turnID) {
        if (EventsManager.runEvent.size() > 0) {
            for (int i = EventsManager.runEvent.size() - 1; i >= 0; --i) {
                runEvent(i);
            }
        }
        runEvents_ExactDay();
        try {
            for (int i = turnID % GameValues.gameUpdate.GAME_UPDATE_EVENTS_EVENTS; i < EventsManager.iEventsSize; i += GameValues.gameUpdate.GAME_UPDATE_EVENTS_EVENTS) {
                for (int j = EventsManager.events.get(i).runCivsID % GameValues.gameUpdate.GAME_UPDATE_EVENTS_CIVS; j < Game.getCivsSize(); j += GameValues.gameUpdate.GAME_UPDATE_EVENTS_CIVS) {
                    if (Game.getCiv(j).getNumOfProvinces() > 0 && (!EventsManager.events.get(i).only_once || !Game.getCiv(j).eventsDataVariables.hasVariable(EventsManager.events.get(i).id)) && EventsManager.events.get(i).possible_to_run && EventsManager.events.get(i).runTriggers(j)) {
                        updateRandomProvinceID(j);
                        if (j == Game.player.iCivID) {
                            Game.player.addActiveEvent(0, i, 0);
                            Game.addSimpleTask(new Game.SimpleTask(EventsManager.events.get(i).id + "0", i) {
                                @Override
                                public void update() {
                                    Game.menuManager.rebuildInGame_Right();
                                    Game.soundsManager.playSound(SoundsManager.EVENT_INFO);
                                    if (EventsManager.events.get(this.id).popUp && !Game.menuManager.getVisibleInGame_Event()) {
                                        Game.menuManager.rebuildInGame_Event(EventsManager.events.get(this.id), 0, this.id);
                                        Game.soundsManager.playSound(SoundsManager.EVENT);
                                    }
                                }
                            });
                        }
                        else if (EventsManager.events.get(i).options.size() > 0) {
                            int score = 0;
                            for (int a = 0; a < EventsManager.events.get(i).options.size(); ++a) {
                                score += (int)EventsManager.events.get(i).options.get(a).ai;
                            }
                            int takeID = 0;
                            if (score > 0) {
                                score = Game.oR.nextInt(score);
                                int a2 = 0;
                                final int currentScore = 0;
                                while (a2 < EventsManager.events.get(i).options.size()) {
                                    if (score <= 0.0f + EventsManager.events.get(i).options.get(a2).ai) {
                                        takeID = a2;
                                        break;
                                    }
                                    ++a2;
                                }
                            }
                            Game.getCiv(j).eventsDataVariables.addVariable(EventsManager.events.get(i).id);
                            EventsManager.events.get(i).options.get(takeID).executeOutcome(j);
                        }
                    }
                }
                final Event event3;
                final Event event = event3 = EventsManager.events.get(i);
                ++event3.runCivsID;
                if (EventsManager.events.get(i).runCivsID >= GameValues.gameUpdate.GAME_UPDATE_EVENTS_CIVS) {
                    EventsManager.events.get(i).runCivsID = 0;
                }
            }
            if (Game.SCENARIO_EVENTS) {
                for (int i = turnID % GameValues.gameUpdate.GAME_UPDATE_EVENTS_EVENTS; i < EventsManager.iEventsScenarioSize; i += GameValues.gameUpdate.GAME_UPDATE_EVENTS_EVENTS) {
                    for (int j = EventsManager.eventsScenario.get(i).runCivsID % GameValues.gameUpdate.GAME_UPDATE_EVENTS_CIVS; j < Game.getCivsSize(); j += GameValues.gameUpdate.GAME_UPDATE_EVENTS_CIVS) {
                        if (Game.getCiv(j).getNumOfProvinces() > 0 && (!EventsManager.eventsScenario.get(i).only_once || !Game.getCiv(j).eventsDataVariables.hasVariable(EventsManager.eventsScenario.get(i).id)) && EventsManager.eventsScenario.get(i).possible_to_run && EventsManager.eventsScenario.get(i).runTriggers(j)) {
                            updateRandomProvinceID(j);
                            if (j == Game.player.iCivID) {
                                Game.player.addActiveEvent(3, i, 0);
                                Game.addSimpleTask(new Game.SimpleTask(EventsManager.eventsScenario.get(i).id + "3", i) {
                                    @Override
                                    public void update() {
                                        Game.menuManager.rebuildInGame_Right();
                                        Game.soundsManager.playSound(SoundsManager.EVENT_INFO);
                                        if (EventsManager.eventsScenario.get(this.id).popUp && !Game.menuManager.getVisibleInGame_Event()) {
                                            Game.menuManager.rebuildInGame_Event(EventsManager.eventsScenario.get(this.id), 3, this.id);
                                            Game.soundsManager.playSound(SoundsManager.EVENT);
                                        }
                                    }
                                });
                            }
                            else if (EventsManager.eventsScenario.get(i).options.size() > 0) {
                                int score = 0;
                                for (int a = 0; a < EventsManager.eventsScenario.get(i).options.size(); ++a) {
                                    score += (int)EventsManager.eventsScenario.get(i).options.get(a).ai;
                                }
                                int takeID = 0;
                                if (score > 0) {
                                    score = Game.oR.nextInt(score);
                                    int a2 = 0;
                                    final int currentScore = 0;
                                    while (a2 < EventsManager.eventsScenario.get(i).options.size()) {
                                        if (score <= 0.0f + EventsManager.eventsScenario.get(i).options.get(a2).ai) {
                                            takeID = a2;
                                            break;
                                        }
                                        ++a2;
                                    }
                                }
                                Game.getCiv(j).eventsDataVariables.addVariable(EventsManager.eventsScenario.get(i).id);
                                EventsManager.eventsScenario.get(i).options.get(takeID).executeOutcome(j);
                            }
                        }
                    }
                    final Event event4;
                    final Event event2 = event4 = EventsManager.eventsScenario.get(i);
                    ++event4.runCivsID;
                    if (EventsManager.eventsScenario.get(i).runCivsID >= GameValues.gameUpdate.GAME_UPDATE_EVENTS_CIVS) {
                        EventsManager.eventsScenario.get(i).runCivsID = 0;
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void runEvent(final int runID) {
        try {
            if (Game.SCENARIO_EVENTS) {
                for (int i = 0; i < EventsManager.iEventsScenarioSize; ++i) {
                    if (EventsManager.eventsScenario.get(i).id.equals(EventsManager.runEvent.get(runID))) {
                        for (int j = 1; j < Game.getCivsSize(); ++j) {
                            if (Game.getCiv(j).getNumOfProvinces() > 0 && (!EventsManager.eventsScenario.get(i).only_once || !Game.getCiv(j).eventsDataVariables.hasVariable(EventsManager.eventsScenario.get(i).id)) && EventsManager.eventsScenario.get(i).runTriggers(j)) {
                                updateRandomProvinceID(j);
                                if (j == Game.player.iCivID) {
                                    Game.player.addActiveEvent(3, i, 0);
                                    Game.addSimpleTask(new Game.SimpleTask(EventsManager.eventsScenario.get(i).id + "3", i) {
                                        @Override
                                        public void update() {
                                            Game.menuManager.rebuildInGame_Right();
                                            Game.soundsManager.playSound(SoundsManager.EVENT_INFO);
                                            if (EventsManager.eventsScenario.get(this.id).popUp && !Game.menuManager.getVisibleInGame_Event()) {
                                                Game.menuManager.rebuildInGame_Event(EventsManager.eventsScenario.get(this.id), 3, this.id);
                                                Game.soundsManager.playSound(SoundsManager.EVENT);
                                            }
                                        }
                                    });
                                }
                                else if (EventsManager.eventsScenario.get(i).options.size() > 0) {
                                    int score = 0;
                                    for (int a = 0; a < EventsManager.eventsScenario.get(i).options.size(); ++a) {
                                        score += (int)EventsManager.eventsScenario.get(i).options.get(a).ai;
                                    }
                                    int takeID = 0;
                                    if (score > 0) {
                                        score = Game.oR.nextInt(score);
                                        int a2 = 0;
                                        final int currentScore = 0;
                                        while (a2 < EventsManager.eventsScenario.get(i).options.size()) {
                                            if (score <= 0.0f + EventsManager.eventsScenario.get(i).options.get(a2).ai) {
                                                takeID = a2;
                                                break;
                                            }
                                            ++a2;
                                        }
                                    }
                                    Game.getCiv(j).eventsDataVariables.addVariable(EventsManager.eventsScenario.get(i).id);
                                    EventsManager.eventsScenario.get(i).options.get(takeID).executeOutcome(j);
                                }
                            }
                        }
                        EventsManager.runEvent.remove(runID);
                        return;
                    }
                }
            }
            for (int i = 0; i < EventsManager.iEventsSize; ++i) {
                if (EventsManager.events.get(i).id.equals(EventsManager.runEvent.get(runID))) {
                    for (int j = 1; j < Game.getCivsSize(); ++j) {
                        if (Game.getCiv(j).getNumOfProvinces() > 0 && (!EventsManager.events.get(i).only_once || !Game.getCiv(j).eventsDataVariables.hasVariable(EventsManager.events.get(i).id)) && EventsManager.events.get(i).runTriggers(j)) {
                            updateRandomProvinceID(j);
                            if (j == Game.player.iCivID) {
                                Game.player.addActiveEvent(0, i, 0);
                                Game.addSimpleTask(new Game.SimpleTask(EventsManager.events.get(i).id + "0", i) {
                                    @Override
                                    public void update() {
                                        Game.menuManager.rebuildInGame_Right();
                                        Game.soundsManager.playSound(SoundsManager.EVENT_INFO);
                                        if (EventsManager.events.get(this.id).popUp && !Game.menuManager.getVisibleInGame_Event()) {
                                            Game.menuManager.rebuildInGame_Event(EventsManager.events.get(this.id), 0, this.id);
                                            Game.soundsManager.playSound(SoundsManager.EVENT);
                                        }
                                    }
                                });
                            }
                            else if (EventsManager.events.get(i).options.size() > 0) {
                                int score = 0;
                                for (int a = 0; a < EventsManager.events.get(i).options.size(); ++a) {
                                    score += (int)EventsManager.events.get(i).options.get(a).ai;
                                }
                                int takeID = 0;
                                if (score > 0) {
                                    score = Game.oR.nextInt(score);
                                    int a2 = 0;
                                    final int currentScore = 0;
                                    while (a2 < EventsManager.events.get(i).options.size()) {
                                        if (score <= 0.0f + EventsManager.events.get(i).options.get(a2).ai) {
                                            takeID = a2;
                                            break;
                                        }
                                        ++a2;
                                    }
                                }
                                Game.getCiv(j).eventsDataVariables.addVariable(EventsManager.events.get(i).id);
                                EventsManager.events.get(i).options.get(takeID).executeOutcome(j);
                            }
                        }
                    }
                    EventsManager.runEvent.remove(runID);
                    return;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void runEvents_ExactDay() {
        try {
            if (EventsManager.exactDate_Events.size() > 0) {
                for (int z = EventsManager.exactDate_Events.size() - 1; z >= 0; --z) {
                    if (Game_Calendar.currentYear > EventsManager.exactDate_Events.get(z).year || (Game_Calendar.currentYear >= EventsManager.exactDate_Events.get(z).year && Game_Calendar.currentMonth > EventsManager.exactDate_Events.get(z).month) || (Game_Calendar.currentYear >= EventsManager.exactDate_Events.get(z).year && Game_Calendar.currentMonth >= EventsManager.exactDate_Events.get(z).month && Game_Calendar.currentDay >= EventsManager.exactDate_Events.get(z).day)) {
                        for (int j = 1; j < Game.getCivsSize(); ++j) {
                            if (Game.getCiv(j).getNumOfProvinces() > 0 && (!EventsManager.events.get(EventsManager.exactDate_Events.get(z).eventID).only_once || !Game.getCiv(j).eventsDataVariables.hasVariable(EventsManager.events.get(EventsManager.exactDate_Events.get(z).eventID).id)) && EventsManager.events.get(EventsManager.exactDate_Events.get(z).eventID).runTriggers(j)) {
                                updateRandomProvinceID(j);
                                if (j == Game.player.iCivID) {
                                    Game.player.addActiveEvent(0, EventsManager.exactDate_Events.get(z).eventID, 0);
                                    Game.addSimpleTask(new Game.SimpleTask(EventsManager.events.get(EventsManager.exactDate_Events.get(z).eventID).id + "0", EventsManager.exactDate_Events.get(z).eventID) {
                                        @Override
                                        public void update() {
                                            Game.menuManager.rebuildInGame_Right();
                                            Game.soundsManager.playSound(SoundsManager.EVENT_INFO);
                                            if (EventsManager.events.get(this.id).popUp && !Game.menuManager.getVisibleInGame_Event()) {
                                                Game.menuManager.rebuildInGame_Event(EventsManager.events.get(this.id), 0, this.id);
                                                Game.soundsManager.playSound(SoundsManager.EVENT);
                                            }
                                        }
                                    });
                                }
                                else if (EventsManager.events.get(EventsManager.exactDate_Events.get(z).eventID).options.size() > 0) {
                                    int score = 0;
                                    for (int a = 0; a < EventsManager.events.get(EventsManager.exactDate_Events.get(z).eventID).options.size(); ++a) {
                                        score += (int)EventsManager.events.get(EventsManager.exactDate_Events.get(z).eventID).options.get(a).ai;
                                    }
                                    int takeID = 0;
                                    if (score > 0) {
                                        score = Game.oR.nextInt(score);
                                        int a2 = 0;
                                        final int currentScore = 0;
                                        while (a2 < EventsManager.events.get(EventsManager.exactDate_Events.get(z).eventID).options.size()) {
                                            if (score <= 0.0f + EventsManager.events.get(EventsManager.exactDate_Events.get(z).eventID).options.get(a2).ai) {
                                                takeID = a2;
                                                break;
                                            }
                                            ++a2;
                                        }
                                    }
                                    Game.getCiv(j).eventsDataVariables.addVariable(EventsManager.events.get(EventsManager.exactDate_Events.get(z).eventID).id);
                                    EventsManager.events.get(EventsManager.exactDate_Events.get(z).eventID).options.get(takeID).executeOutcome(j);
                                }
                            }
                        }
                        EventsManager.exactDate_Events.remove(z);
                    }
                }
            }
            if (EventsManager.exactDate_EventsScenario.size() > 0) {
                for (int z = EventsManager.exactDate_EventsScenario.size() - 1; z >= 0; --z) {
                    if (Game_Calendar.currentYear > EventsManager.exactDate_EventsScenario.get(z).year || (Game_Calendar.currentYear >= EventsManager.exactDate_EventsScenario.get(z).year && Game_Calendar.currentMonth > EventsManager.exactDate_EventsScenario.get(z).month) || (Game_Calendar.currentYear >= EventsManager.exactDate_EventsScenario.get(z).year && Game_Calendar.currentMonth >= EventsManager.exactDate_EventsScenario.get(z).month && Game_Calendar.currentDay >= EventsManager.exactDate_EventsScenario.get(z).day)) {
                        for (int j = 1; j < Game.getCivsSize(); ++j) {
                            if (Game.getCiv(j).getNumOfProvinces() > 0 && (!EventsManager.eventsScenario.get(EventsManager.exactDate_EventsScenario.get(z).eventID).only_once || !Game.getCiv(j).eventsDataVariables.hasVariable(EventsManager.eventsScenario.get(EventsManager.exactDate_EventsScenario.get(z).eventID).id)) && EventsManager.eventsScenario.get(EventsManager.exactDate_EventsScenario.get(z).eventID).runTriggers(j)) {
                                updateRandomProvinceID(j);
                                if (j == Game.player.iCivID) {
                                    Game.player.addActiveEvent(3, EventsManager.exactDate_EventsScenario.get(z).eventID, 0);
                                    Game.addSimpleTask(new Game.SimpleTask(EventsManager.eventsScenario.get(EventsManager.exactDate_EventsScenario.get(z).eventID).id + "3", EventsManager.exactDate_EventsScenario.get(z).eventID) {
                                        @Override
                                        public void update() {
                                            Game.menuManager.rebuildInGame_Right();
                                            Game.soundsManager.playSound(SoundsManager.EVENT_INFO);
                                            if (EventsManager.eventsScenario.get(this.id).popUp && !Game.menuManager.getVisibleInGame_Event()) {
                                                Game.menuManager.rebuildInGame_Event(EventsManager.eventsScenario.get(this.id), 3, this.id);
                                                Game.soundsManager.playSound(SoundsManager.EVENT);
                                            }
                                        }
                                    });
                                }
                                else if (EventsManager.eventsScenario.get(EventsManager.exactDate_EventsScenario.get(z).eventID).options.size() > 0) {
                                    int score = 0;
                                    for (int a = 0; a < EventsManager.eventsScenario.get(EventsManager.exactDate_EventsScenario.get(z).eventID).options.size(); ++a) {
                                        score += (int)EventsManager.eventsScenario.get(EventsManager.exactDate_EventsScenario.get(z).eventID).options.get(a).ai;
                                    }
                                    int takeID = 0;
                                    if (score > 0) {
                                        score = Game.oR.nextInt(score);
                                        int a2 = 0;
                                        final int currentScore = 0;
                                        while (a2 < EventsManager.eventsScenario.get(EventsManager.exactDate_EventsScenario.get(z).eventID).options.size()) {
                                            if (score <= 0.0f + EventsManager.eventsScenario.get(EventsManager.exactDate_EventsScenario.get(z).eventID).options.get(a2).ai) {
                                                takeID = a2;
                                                break;
                                            }
                                            ++a2;
                                        }
                                    }
                                    Game.getCiv(j).eventsDataVariables.addVariable(EventsManager.eventsScenario.get(EventsManager.exactDate_EventsScenario.get(z).eventID).id);
                                    EventsManager.eventsScenario.get(EventsManager.exactDate_EventsScenario.get(z).eventID).options.get(takeID).executeOutcome(j);
                                }
                            }
                        }
                        EventsManager.exactDate_EventsScenario.remove(z);
                    }
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void runEvents_Siege(final int iCivID, final int iProvinceID) {
        try {
            int i = 0;
            while (i < EventsManager.iEventsSiegeSize) {
                if ((!EventsManager.eventsSiege.get(i).only_once || !Game.getCiv(iCivID).eventsDataVariables.hasVariable(EventsManager.eventsSiege.get(i).id)) && EventsManager.eventsSiege.get(i).runTriggers(iCivID)) {
                    updateRandomProvinceID(iCivID);
                    if (iCivID == Game.player.iCivID) {
                        Game.player.addActiveEvent(1, i, 0);
                        Game.addSimpleTask(new Game.SimpleTask(EventsManager.eventsSiege.get(i).id + "1", i) {
                            @Override
                            public void update() {
                                Game.menuManager.rebuildInGame_Right();
                                if (EventsManager.eventsSiege.get(this.id).popUp && !Game.menuManager.getVisibleInGame_Event()) {
                                    Game.menuManager.rebuildInGame_Event(EventsManager.eventsSiege.get(this.id), 1, this.id);
                                    Game.soundsManager.playSound(SoundsManager.EVENT);
                                }
                            }
                        });
                        break;
                    }
                    if (EventsManager.eventsSiege.get(i).options.size() > 0) {
                        int score = 0;
                        for (int a = 0; a < EventsManager.eventsSiege.get(i).options.size(); ++a) {
                            score += (int)EventsManager.eventsSiege.get(i).options.get(a).ai;
                        }
                        int takeID = 0;
                        if (score > 0) {
                            score = Game.oR.nextInt(score);
                            int a2 = 0;
                            final int currentScore = 0;
                            while (a2 < EventsManager.eventsSiege.get(i).options.size()) {
                                if (score <= 0.0f + EventsManager.eventsSiege.get(i).options.get(a2).ai) {
                                    takeID = a2;
                                    break;
                                }
                                ++a2;
                            }
                        }
                        Game.getCiv(iCivID).eventsDataVariables.addVariable(EventsManager.eventsSiege.get(i).id);
                        EventsManager.eventsSiege.get(i).options.get(takeID).executeOutcome(iCivID);
                        break;
                    }
                    break;
                }
                else {
                    ++i;
                }
            }
            i = 0;
            while (i < EventsManager.iEventsSiegeScenarioSize) {
                if ((!EventsManager.eventsSiegeScenario.get(i).only_once || !Game.getCiv(iCivID).eventsDataVariables.hasVariable(EventsManager.eventsSiegeScenario.get(i).id)) && EventsManager.eventsSiegeScenario.get(i).runTriggers(iCivID)) {
                    updateRandomProvinceID(iCivID);
                    if (iCivID == Game.player.iCivID) {
                        Game.player.addActiveEvent(4, i, 0);
                        Game.addSimpleTask(new Game.SimpleTask(EventsManager.eventsSiegeScenario.get(i).id + "4", i) {
                            @Override
                            public void update() {
                                Game.menuManager.rebuildInGame_Right();
                                if (EventsManager.eventsSiegeScenario.get(this.id).popUp && !Game.menuManager.getVisibleInGame_Event()) {
                                    Game.menuManager.rebuildInGame_Event(EventsManager.eventsSiegeScenario.get(this.id), 4, this.id);
                                    Game.soundsManager.playSound(SoundsManager.EVENT);
                                }
                            }
                        });
                        break;
                    }
                    if (EventsManager.eventsSiegeScenario.get(i).options.size() > 0) {
                        int score = 0;
                        for (int a = 0; a < EventsManager.eventsSiegeScenario.get(i).options.size(); ++a) {
                            score += (int)EventsManager.eventsSiegeScenario.get(i).options.get(a).ai;
                        }
                        int takeID = 0;
                        if (score > 0) {
                            score = Game.oR.nextInt(score);
                            int a2 = 0;
                            final int currentScore = 0;
                            while (a2 < EventsManager.eventsSiegeScenario.get(i).options.size()) {
                                if (score <= 0.0f + EventsManager.eventsSiegeScenario.get(i).options.get(a2).ai) {
                                    takeID = a2;
                                    break;
                                }
                                ++a2;
                            }
                        }
                        Game.getCiv(iCivID).eventsDataVariables.addVariable(EventsManager.eventsSiegeScenario.get(i).id);
                        EventsManager.eventsSiegeScenario.get(i).options.get(takeID).executeOutcome(iCivID);
                        break;
                    }
                    break;
                }
                else {
                    ++i;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void runEvents_Global(final int turnID) {
        try {
            if (turnID % GameValues.events.RUN_GLOBAL_EVENTS_EVERY_X_TURNS == 0) {
                try {
                    if (EventsManager.runEventGlobalID < EventsManager.iEventsGlobalSize && (!EventsManager.eventsGlobal.get(EventsManager.runEventGlobalID).only_once || !EventsManager.eventsGlobal_Variables.hasVariable(EventsManager.eventsGlobal.get(EventsManager.runEventGlobalID).id)) && EventsManager.eventsGlobal.get(EventsManager.runEventGlobalID).runTriggers(0)) {
                        updateRandomProvinceID(0);
                        EventsManager.eventsGlobal_Variables.addVariable(getActiveEvent(EventsManager.runEventGlobalID, 2).id);
                        boolean updateMenu = !GameValues.events.EVENT_CHANGE_PRICE_SHOW_ONLY_THOSE_THAT_PLAYER_HAS;
                        try {
                            if (!updateMenu) {
                                updateMenu = (updateMenu || (EventsManager.eventsGlobal.get(EventsManager.runEventGlobalID).options.size() >= 1 && EventsManager.eventsGlobal.get(EventsManager.runEventGlobalID).options.get(0).outcome.size() > 0 && ResourcesManager.hasResource(Game.player.iCivID, EventsManager.eventsGlobal.get(EventsManager.runEventGlobalID).options.get(0).outcome.get(0).getValue1())));
                            }
                        }
                        catch (final Exception ex2) {}
                        EventsManager.eventsGlobal.get(EventsManager.runEventGlobalID).options.get(0).executeOutcome();
                        if (updateMenu) {
                            Game.player.addActiveEvent(2, EventsManager.runEventGlobalID, -(GameValues.events.EVENT_TIME_TO_RESPOND / 2));
                            Game.addSimpleTask(new Game.SimpleTask(EventsManager.eventsGlobal.get(EventsManager.runEventGlobalID).id + "2", EventsManager.runEventGlobalID) {
                                @Override
                                public void update() {
                                    Game.menuManager.rebuildInGame_Right();
                                    if (EventsManager.eventsGlobal.get(this.id).popUp && !Game.menuManager.getVisibleInGame_Event()) {
                                        Game.menuManager.rebuildInGame_Event(EventsManager.eventsGlobal.get(this.id), 2, this.id);
                                        Game.soundsManager.playSound(SoundsManager.EVENT);
                                    }
                                }
                            });
                        }
                        else if (EventsManager.eventsGlobal.get(EventsManager.runEventGlobalID).options.size() >= 1) {
                            EventsManager.eventsGlobal.get(EventsManager.runEventGlobalID).options.get(0).executeOutcome(0);
                        }
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
                ++EventsManager.runEventGlobalID;
                if (EventsManager.runEventGlobalID >= EventsManager.iEventsGlobalSize) {
                    EventsManager.runEventGlobalID = 0;
                }
            }
            if (turnID % GameValues.events.RUN_GLOBAL_EVENTS_EVERY_X_TURNS == 0) {
                try {
                    if (EventsManager.runEventGlobalID_Scenario < EventsManager.iEventsGlobalScenarioSize && (!EventsManager.eventsGlobalScenario.get(EventsManager.runEventGlobalID_Scenario).only_once || !EventsManager.eventsGlobal_Variables.hasVariable(EventsManager.eventsGlobalScenario.get(EventsManager.runEventGlobalID_Scenario).id)) && EventsManager.eventsGlobalScenario.get(EventsManager.runEventGlobalID_Scenario).runTriggers(0)) {
                        updateRandomProvinceID(0);
                        EventsManager.eventsGlobal_Variables.addVariable(getActiveEvent(EventsManager.runEventGlobalID_Scenario, 5).id);
                        boolean updateMenu = !GameValues.events.EVENT_CHANGE_PRICE_SHOW_ONLY_THOSE_THAT_PLAYER_HAS;
                        try {
                            if (!updateMenu) {
                                updateMenu = (updateMenu || (EventsManager.eventsGlobalScenario.get(EventsManager.runEventGlobalID_Scenario).options.size() >= 1 && EventsManager.eventsGlobalScenario.get(EventsManager.runEventGlobalID_Scenario).options.get(0).outcome.size() > 0 && ResourcesManager.hasResource(Game.player.iCivID, EventsManager.eventsGlobalScenario.get(EventsManager.runEventGlobalID_Scenario).options.get(0).outcome.get(0).getValue1())));
                            }
                        }
                        catch (final Exception ex3) {}
                        EventsManager.eventsGlobalScenario.get(EventsManager.runEventGlobalID_Scenario).options.get(0).executeOutcome();
                        if (updateMenu) {
                            Game.player.addActiveEvent(5, EventsManager.runEventGlobalID_Scenario, -(GameValues.events.EVENT_TIME_TO_RESPOND / 2));
                            Game.addSimpleTask(new Game.SimpleTask(EventsManager.eventsGlobalScenario.get(EventsManager.runEventGlobalID_Scenario).id + "5", EventsManager.runEventGlobalID_Scenario) {
                                @Override
                                public void update() {
                                    Game.menuManager.rebuildInGame_Right();
                                    if (EventsManager.eventsGlobalScenario.get(this.id).popUp && !Game.menuManager.getVisibleInGame_Event()) {
                                        Game.menuManager.rebuildInGame_Event(EventsManager.eventsGlobalScenario.get(this.id), 5, this.id);
                                        Game.soundsManager.playSound(SoundsManager.EVENT);
                                    }
                                }
                            });
                        }
                        else if (EventsManager.eventsGlobalScenario.get(EventsManager.runEventGlobalID_Scenario).options.size() >= 1) {
                            EventsManager.eventsGlobalScenario.get(EventsManager.runEventGlobalID_Scenario).options.get(0).executeOutcome(0);
                        }
                    }
                }
                catch (final Exception ex) {
                    CFG.exceptionStack(ex);
                }
                ++EventsManager.runEventGlobalID_Scenario;
                if (EventsManager.runEventGlobalID_Scenario >= EventsManager.iEventsGlobalScenarioSize) {
                    EventsManager.runEventGlobalID_Scenario = 0;
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void takeEventDecision(final int iCivID, final int eventType, final int eventID, final int optionID) {
        try {
            if (eventType == 2) {
                EventsManager.eventsGlobal_Variables.addVariable(getActiveEvent(eventID, eventType).id);
            }
            else if (eventType == 5) {
                EventsManager.eventsGlobal_Variables.addVariable(getActiveEvent(eventID, eventType).id);
            }
            else {
                Game.getCiv(iCivID).eventsDataVariables.addVariable(getActiveEvent(eventID, eventType).id);
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
        try {
            getActiveEvent(eventID, eventType).options.get(optionID).executeOutcome(iCivID);
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static final void updateRandomProvinceID(final int iCivID) {
        if (Game.getCiv(iCivID).getNumOfProvinces() > 0) {
            Game.getCiv(iCivID).eventProvinceID = Game.getCiv(iCivID).getProvinceID(Game.oR.nextInt(Game.getCiv(iCivID).getNumOfProvinces()));
            return;
        }
        if (Game.getCiv(iCivID).getCapitalProvinceID() >= 0 && Game.getProvince(Game.getCiv(iCivID).getCapitalProvinceID()).getCivID() == iCivID) {
            Game.getCiv(iCivID).eventProvinceID = Game.getCiv(iCivID).getCapitalProvinceID();
            return;
        }
        if (iCivID == 0) {
            for (int i = 0; i < 500; ++i) {
                Game.getCiv(iCivID).eventProvinceID = Game.oR.nextInt(Game.getProvincesSize());
                if (!Game.getProvince(Game.getCiv(iCivID).eventProvinceID).getSeaProvince() && Game.getProvince(Game.getCiv(iCivID).eventProvinceID).getWasteland() < 0) {
                    return;
                }
            }
        }
    }
    
    public static final void loadEvents() {
        if (CFG.isDesktop()) {
            try {
                boolean generateList = false;
                final FileHandle tempFileT = FileManager.loadFile("game/events/generate_list.txt");
                generateList = Boolean.parseBoolean(tempFileT.readString());
                if (generateList) {
                    final FileHandle[] files = Gdx.files.local("game/events/common/").list();
                    final FileHandle fileWrite = Gdx.files.local("game/events/list_common.txt");
                    fileWrite.writeString("", false);
                    for (int i = 0; i < files.length; ++i) {
                        fileWrite.writeString(files[i].name() + ";", true);
                    }
                    final FileHandle[] files2 = Gdx.files.local("game/events/siege/").list();
                    final FileHandle fileWrite2 = Gdx.files.local("game/events/list_siege.txt");
                    fileWrite2.writeString("", false);
                    for (int j = 0; j < files2.length; ++j) {
                        fileWrite2.writeString(files2[j].name() + ";", true);
                    }
                    final FileHandle[] files3 = Gdx.files.local("game/events/global/").list();
                    final FileHandle fileWrite3 = Gdx.files.local("game/events/list_global.txt");
                    fileWrite3.writeString("", false);
                    for (int k = 0; k < files3.length; ++k) {
                        fileWrite3.writeString(files3[k].name() + ";", true);
                    }
                }
            }
            catch (final Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
        loadEvents(0);
        loadEvents(1);
        loadEvents(2);
        loadMissionImages();
    }
    
    public static final void loadEvents_Scenario() {
        loadEvents_Scenario(3);
        loadEvents_Scenario(4);
        loadEvents_Scenario(5);
    }
    
    public static void clearEventsScenario() {
        EventsManager.eventsScenario.clear();
        EventsManager.eventsSiegeScenario.clear();
        EventsManager.eventsGlobalScenario.clear();
        EventsManager.iEventsScenarioSize = 0;
        EventsManager.iEventsSiegeScenarioSize = 0;
        EventsManager.iEventsGlobalScenarioSize = 0;
        EventsManager.runEvent.clear();
        EventsManager.exactDate_EventsScenario.clear();
        EventsManager.exactDate_Events.clear();
    }
    
    public static final void loadEvents(final int eventsType) {
        final String sEventsPath = (eventsType == 0) ? "common/" : ((eventsType == 1) ? "siege/" : "global/");
        final String sEventsPath_List = (eventsType == 0) ? "list_common.txt" : ((eventsType == 1) ? "list_siege.txt" : "list_global.txt");
        if (CFG.isDesktop()) {
            if (FileManager.IS_MAC) {
                try {
                    final FileHandle tempFileT = FileManager.loadFile("game/events/" + sEventsPath_List);
                    final String[] tempSplit = tempFileT.readString().split(";");
                    for (int i = 0, iSize = tempSplit.length; i < iSize; ++i) {
                        try {
                            if (tempSplit[i].length() > 0) {
                                final FileHandle tempFileEvent = FileManager.loadFile("game/events/" + sEventsPath + tempSplit[i]);
                                loadEvent(eventsType, tempFileEvent.readString().split("\\r?\\n"));
                            }
                        }
                        catch (final Exception ex) {
                            CFG.exceptionStack(ex);
                        }
                    }
                }
                catch (final GdxRuntimeException ex2) {
                    CFG.exceptionStack((Throwable)ex2);
                }
            }
            FileHandle[] files;
            final FileHandle[] array;
            final FileHandle[] list = array = (files = Gdx.files.internal("game/events/" + sEventsPath).list());
            for (final FileHandle file : array) {
                try {
                    loadEvent(eventsType, file.readString().split("\\r?\\n"));
                }
                catch (final Exception ex3) {
                    CFG.exceptionStack(ex3);
                }
            }
            for (int j = 0; j < SteamManager.modsFoldersSize; ++j) {
                if (FileManager.IS_MAC) {
                    files = Gdx.files.external(SteamManager.modsFolders.get(j) + "game/events/" + sEventsPath).list();
                }
                else {
                    files = Gdx.files.internal(SteamManager.modsFolders.get(j) + "game/events/" + sEventsPath).list();
                }
                for (final FileHandle file2 : files) {
                    try {
                        loadEvent(eventsType, file2.readString().split("\\r?\\n"));
                    }
                    catch (final Exception ex4) {
                        CFG.exceptionStack(ex4);
                    }
                }
            }
            for (int j = 0; j < SteamManager.itemsInstalledSize; ++j) {
                final FileHandle[] array3;
                final FileHandle[] list2 = array3 = (files = Gdx.files.absolute(SteamManager.itemsInstalled.get(j).getFolder() + "/game/events/" + sEventsPath).list());
                for (final FileHandle file3 : array3) {
                    try {
                        loadEvent(eventsType, file3.readString().split("\\r?\\n"));
                    }
                    catch (final Exception ex5) {
                        CFG.exceptionStack(ex5);
                    }
                }
            }
        }
        else {
            try {
                final FileHandle tempFileT = FileManager.loadFile("game/events/" + sEventsPath_List);
                final String[] tempSplit = tempFileT.readString().split(";");
                for (int i = 0, iSize = tempSplit.length; i < iSize; ++i) {
                    try {
                        if (tempSplit[i].length() > 0) {
                            final FileHandle tempFileEvent = FileManager.loadFile("game/events/" + sEventsPath + tempSplit[i]);
                            loadEvent(eventsType, tempFileEvent.readString().split("\\r?\\n"));
                        }
                    }
                    catch (final Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
            catch (final GdxRuntimeException ex2) {
                CFG.exceptionStack((Throwable)ex2);
            }
        }
        if (eventsType == 0) {
            EventsManager.iEventsSize = EventsManager.events.size();
        }
        else if (eventsType == 1) {
            EventsManager.iEventsSiegeSize = EventsManager.eventsSiege.size();
        }
        else if (eventsType == 2) {
            EventsManager.iEventsGlobalSize = EventsManager.eventsGlobal.size();
            GameValues.events.RUN_GLOBAL_EVENTS_EVERY_X_TURNS = Math.max(10, GameValues.events.RUN_GLOBAL_EVENTS_EVERY_X_TURNS / Math.max(1, EventsManager.iEventsGlobalSize));
        }
        if (eventsType == 3) {
            EventsManager.iEventsScenarioSize = EventsManager.eventsScenario.size();
        }
        else if (eventsType == 4) {
            EventsManager.iEventsSiegeScenarioSize = EventsManager.eventsSiegeScenario.size();
        }
        else if (eventsType == 5) {
            EventsManager.iEventsGlobalScenarioSize = EventsManager.eventsGlobalScenario.size();
        }
    }
    
    public static final void loadEvents_Scenario(final int eventsType) {
        final String sEventsPath = (eventsType == 3) ? "common/" : ((eventsType == 4) ? "siege/" : "global/");
        final String sEventsPath_List = (eventsType == 3) ? "list_common.txt" : ((eventsType == 4) ? "list_siege.txt" : "list_global.txt");
        if (CFG.isDesktop()) {
            if (FileManager.IS_MAC) {
                try {
                    try {
                        if (FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + EventsManager.loadScenarioEventsTag + "/events/").exists()) {
                            final FileHandle tempFileT = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + EventsManager.loadScenarioEventsTag + "/events/" + sEventsPath_List);
                            final String[] tempSplit = tempFileT.readString().split(";");
                            for (int i = 0, iSize = tempSplit.length; i < iSize; ++i) {
                                try {
                                    if (tempSplit[i].length() > 0) {
                                        final FileHandle tempFileEvent = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + EventsManager.loadScenarioEventsTag + "/events/" + sEventsPath + tempSplit[i]);
                                        loadEvent(eventsType, tempFileEvent.readString().split("\\r?\\n"));
                                    }
                                }
                                catch (final Exception ex) {
                                    CFG.exceptionStack(ex);
                                }
                            }
                        }
                    }
                    catch (final Exception ex2) {
                        CFG.exceptionStack(ex2);
                    }
                }
                catch (final GdxRuntimeException ex3) {
                    CFG.exceptionStack((Throwable)ex3);
                }
            }
            FileHandle[] files;
            final FileHandle[] array;
            final FileHandle[] list = array = (files = Gdx.files.internal("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + EventsManager.loadScenarioEventsTag + "/events/" + sEventsPath).list());
            for (final FileHandle file : array) {
                try {
                    loadEvent(eventsType, file.readString().split("\\r?\\n"));
                }
                catch (final Exception ex4) {
                    CFG.exceptionStack(ex4);
                }
            }
            for (int j = 0; j < SteamManager.modsFoldersSize; ++j) {
                if (FileManager.IS_MAC) {
                    files = Gdx.files.external(SteamManager.modsFolders.get(j) + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + EventsManager.loadScenarioEventsTag + "/events/" + sEventsPath).list();
                }
                else {
                    files = Gdx.files.internal(SteamManager.modsFolders.get(j) + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + EventsManager.loadScenarioEventsTag + "/events/" + sEventsPath).list();
                }
                for (final FileHandle file2 : files) {
                    try {
                        loadEvent(eventsType, file2.readString().split("\\r?\\n"));
                    }
                    catch (final Exception ex5) {
                        CFG.exceptionStack(ex5);
                    }
                }
            }
            for (int j = 0; j < SteamManager.itemsInstalledSize; ++j) {
                final FileHandle[] array3;
                final FileHandle[] list2 = array3 = (files = Gdx.files.absolute(SteamManager.itemsInstalled.get(j).getFolder() + "/map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + EventsManager.loadScenarioEventsTag + "/events/" + sEventsPath).list());
                for (final FileHandle file3 : array3) {
                    try {
                        loadEvent(eventsType, file3.readString().split("\\r?\\n"));
                    }
                    catch (final Exception ex6) {
                        CFG.exceptionStack(ex6);
                    }
                }
            }
        }
        else {
            try {
                try {
                    if (FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + EventsManager.loadScenarioEventsTag + "/events/").exists()) {
                        final FileHandle tempFileT = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + EventsManager.loadScenarioEventsTag + "/events/" + sEventsPath_List);
                        final String[] tempSplit = tempFileT.readString().split(";");
                        for (int i = 0, iSize = tempSplit.length; i < iSize; ++i) {
                            try {
                                if (tempSplit[i].length() > 0) {
                                    final FileHandle tempFileEvent = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + EventsManager.loadScenarioEventsTag + "/events/" + sEventsPath + tempSplit[i]);
                                    loadEvent(eventsType, tempFileEvent.readString().split("\\r?\\n"));
                                }
                            }
                            catch (final Exception ex) {
                                CFG.exceptionStack(ex);
                            }
                        }
                    }
                }
                catch (final Exception ex2) {
                    CFG.exceptionStack(ex2);
                }
            }
            catch (final GdxRuntimeException ex3) {
                CFG.exceptionStack((Throwable)ex3);
            }
        }
        if (eventsType == 0) {
            EventsManager.iEventsSize = EventsManager.events.size();
        }
        else if (eventsType == 1) {
            EventsManager.iEventsSiegeSize = EventsManager.eventsSiege.size();
        }
        else if (eventsType == 2) {
            EventsManager.iEventsGlobalSize = EventsManager.eventsGlobal.size();
            GameValues.events.RUN_GLOBAL_EVENTS_EVERY_X_TURNS = Math.max(10, GameValues.events.RUN_GLOBAL_EVENTS_EVERY_X_TURNS / Math.max(1, EventsManager.iEventsGlobalSize));
        }
        if (eventsType == 3) {
            EventsManager.iEventsScenarioSize = EventsManager.eventsScenario.size();
        }
        else if (eventsType == 4) {
            EventsManager.iEventsSiegeScenarioSize = EventsManager.eventsSiegeScenario.size();
        }
        else if (eventsType == 5) {
            EventsManager.iEventsGlobalScenarioSize = EventsManager.eventsGlobalScenario.size();
        }
    }
    
    public static Event loadEvent(final int eventsType, final String[] sSplit) {
        try {
            final int iSize = sSplit.length;
            boolean exactDate = false;
            int exactDay = 1;
            int exactMonth = 1;
            int exactYear = 1;
            if (iSize > 1) {
                final Event nEvent = new Event();
                boolean inTrigger = false;
                boolean inOption = false;
                EventTrigger trigger = new EventTrigger();
                EventOption option = new EventOption();
                int nextType = 0;
                int triggerType = 0;
                for (int i = 0; i < iSize; ++i) {
                    try {
                        final String[] sLine = sSplit[i].split("=");
                        if (sLine.length == 1) {
                            if (sLine[0].length() > 0) {
                                if (inTrigger) {
                                    final String s7;
                                    final String s = s7 = sLine[0];
                                    switch (s7) {
                                        case "trigger_and_end":
                                        case "trigger_and_not_end":
                                        case "trigger_or_end":
                                        case "trigger_or_not_end": {
                                            inTrigger = false;
                                            nEvent.addTrigger(trigger, triggerType);
                                            break;
                                        }
                                        case "next_and": {
                                            nextType = 0;
                                            break;
                                        }
                                        case "next_and_not": {
                                            nextType = 1;
                                            break;
                                        }
                                        case "next_or": {
                                            nextType = 2;
                                            break;
                                        }
                                        case "next_or_not": {
                                            nextType = 3;
                                            break;
                                        }
                                        default: {
                                            CFG.LOG(" MISSING LEN=1 IN TRIGGER -> " + sLine[0] + " *** Line: " + (i + 1));
                                            break;
                                        }
                                    }
                                }
                                else if (inOption) {
                                    final String s8;
                                    final String s2 = s8 = sLine[0];
                                    switch (s8) {
                                        case "option_end": {
                                            inOption = false;
                                            nEvent.options.add(option);
                                            break;
                                        }
                                        case "next_and": {
                                            nextType = 0;
                                            break;
                                        }
                                        case "next_and_not": {
                                            nextType = 1;
                                            break;
                                        }
                                        case "next_or": {
                                            nextType = 2;
                                            break;
                                        }
                                        case "next_or_not": {
                                            nextType = 3;
                                            break;
                                        }
                                        default: {
                                            CFG.LOG(" MISSING LEN=1 IN TRIGGER -> " + sLine[0] + " *** Line: " + (i + 1));
                                            break;
                                        }
                                    }
                                }
                                else {
                                    final String s9;
                                    final String s3 = s9 = sLine[0];
                                    switch (s9) {
                                        case "trigger_and": {
                                            triggerType = 0;
                                            inTrigger = true;
                                            trigger = new EventTrigger();
                                            break;
                                        }
                                        case "trigger_and_not": {
                                            triggerType = 1;
                                            inTrigger = true;
                                            trigger = new EventTrigger();
                                            break;
                                        }
                                        case "trigger_or": {
                                            triggerType = 2;
                                            inTrigger = true;
                                            trigger = new EventTrigger();
                                            break;
                                        }
                                        case "trigger_or_not": {
                                            triggerType = 3;
                                            inTrigger = true;
                                            trigger = new EventTrigger();
                                            break;
                                        }
                                        case "option_btn": {
                                            inOption = true;
                                            option = new EventOption();
                                            break;
                                        }
                                        default: {
                                            CFG.LOG(" MISSING LEN=1 -> " + sLine[0] + " *** Line: " + (i + 1));
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        else if (sLine.length > 1) {
                            if (inTrigger) {
                                final String s10;
                                final String s4 = s10 = sLine[0];
                                switch (s10) {
                                    case "random_chance": {
                                        trigger.addTrigger(new EventTrigger_RandomChance(Float.parseFloat(sLine[1])), nextType);
                                        break;
                                    }
                                    case "invested_in_economy_over": {
                                        trigger.addTrigger(new EventTrigger_InvestedInEconomyOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "invested_in_economy_below": {
                                        trigger.addTrigger(new EventTrigger_InvestedInEconomyBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "developed_infrastructure_over": {
                                        trigger.addTrigger(new EventTrigger_DevelopedInfrastructureOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "developed_infrastructure_below": {
                                        trigger.addTrigger(new EventTrigger_DevelopedInfrastructureBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_conquered_provinces_over": {
                                        trigger.addTrigger(new EventTrigger_ConqueredProvincesOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_conquered_provinces_below": {
                                        trigger.addTrigger(new EventTrigger_ConqueredProvincesBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_wars_total_over": {
                                        trigger.addTrigger(new EventTrigger_CivWarsTotalOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_wars_total_below": {
                                        trigger.addTrigger(new EventTrigger_CivWarsTotalBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "increased_growth_rate_over": {
                                        trigger.addTrigger(new EventTrigger_IncreasedGrowthRateOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "increased_growth_rate_below": {
                                        trigger.addTrigger(new EventTrigger_IncreasedGrowthRateBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "increased_tax_efficiency_over": {
                                        trigger.addTrigger(new EventTrigger_IncreasedTaxEfficiencyOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "increased_tax_efficiency_below": {
                                        trigger.addTrigger(new EventTrigger_IncreasedTaxEfficiencyBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "increased_manpower_over": {
                                        trigger.addTrigger(new EventTrigger_IncreasedManpowerOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "increased_manpower_below": {
                                        trigger.addTrigger(new EventTrigger_IncreasedManpowerBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_has_resource": {
                                        trigger.addTrigger(new EventTrigger_CivHasResource(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_has_resource_over":
                                    case "civ_production_over": {
                                        if (sLine.length > 2) {
                                            trigger.addTrigger(new EventTrigger_CivHasResourceOver(Integer.parseInt(sLine[1]), Float.parseFloat(sLine[2])), nextType);
                                            break;
                                        }
                                        break;
                                    }
                                    case "province_economy_over": {
                                        trigger.addTrigger(new EventTrigger_ProvinceEconomyOver(Integer.parseInt(sLine[1]), Float.parseFloat(sLine[2])), nextType);
                                        break;
                                    }
                                    case "province_economy_below": {
                                        trigger.addTrigger(new EventTrigger_ProvinceEconomyBelow(Integer.parseInt(sLine[1]), Float.parseFloat(sLine[2])), nextType);
                                        break;
                                    }
                                    case "province_growth_rate_over": {
                                        trigger.addTrigger(new EventTrigger_ProvinceGrowthRateOver(Integer.parseInt(sLine[1]), Float.parseFloat(sLine[2])), nextType);
                                        break;
                                    }
                                    case "province_growth_rate_below": {
                                        trigger.addTrigger(new EventTrigger_ProvinceGrowthRateBelow(Integer.parseInt(sLine[1]), Float.parseFloat(sLine[2])), nextType);
                                        break;
                                    }
                                    case "province_tax_efficiency_over": {
                                        trigger.addTrigger(new EventTrigger_ProvinceTaxEfficiencyOver(Integer.parseInt(sLine[1]), Float.parseFloat(sLine[2])), nextType);
                                        break;
                                    }
                                    case "province_tax_efficiency_below": {
                                        trigger.addTrigger(new EventTrigger_ProvinceTaxEfficiencyBelow(Integer.parseInt(sLine[1]), Float.parseFloat(sLine[2])), nextType);
                                        break;
                                    }
                                    case "province_population_over": {
                                        trigger.addTrigger(new EventTrigger_ProvincePopulationOver(Integer.parseInt(sLine[1]), Float.parseFloat(sLine[2])), nextType);
                                        break;
                                    }
                                    case "province_population_below": {
                                        trigger.addTrigger(new EventTrigger_ProvincePopulationBelow(Integer.parseInt(sLine[1]), Float.parseFloat(sLine[2])), nextType);
                                        break;
                                    }
                                    case "province_manpower_over": {
                                        trigger.addTrigger(new EventTrigger_ProvinceManpowerOver(Integer.parseInt(sLine[1]), Float.parseFloat(sLine[2])), nextType);
                                        break;
                                    }
                                    case "province_manpower_below": {
                                        trigger.addTrigger(new EventTrigger_ProvinceManpowerBelow(Integer.parseInt(sLine[1]), Float.parseFloat(sLine[2])), nextType);
                                        break;
                                    }
                                    case "province_income_over": {
                                        trigger.addTrigger(new EventTrigger_ProvinceIncomeOver(Integer.parseInt(sLine[1]), Float.parseFloat(sLine[2])), nextType);
                                        break;
                                    }
                                    case "province_income_below": {
                                        trigger.addTrigger(new EventTrigger_ProvinceIncomeBelow(Integer.parseInt(sLine[1]), Float.parseFloat(sLine[2])), nextType);
                                        break;
                                    }
                                    case "province_religion_is": {
                                        trigger.addTrigger(new EventTrigger_ProvinceReligionIs(Integer.parseInt(sLine[1]), Integer.parseInt(sLine[2])), nextType);
                                        break;
                                    }
                                    case "province_religion_is_not": {
                                        trigger.addTrigger(new EventTrigger_ProvinceReligionIsNot(Integer.parseInt(sLine[1]), Integer.parseInt(sLine[2])), nextType);
                                        break;
                                    }
                                    case "province_unrest_over": {
                                        trigger.addTrigger(new EventTrigger_ProvinceUnrestOver(Integer.parseInt(sLine[1]), Float.parseFloat(sLine[2])), nextType);
                                        break;
                                    }
                                    case "province_unrest_below": {
                                        trigger.addTrigger(new EventTrigger_ProvinceUnrestBelow(Integer.parseInt(sLine[1]), Float.parseFloat(sLine[2])), nextType);
                                        break;
                                    }
                                    case "province_infrastructure_over": {
                                        trigger.addTrigger(new EventTrigger_ProvinceInfrastructureOver(Integer.parseInt(sLine[1]), Float.parseFloat(sLine[2])), nextType);
                                        break;
                                    }
                                    case "province_infrastructure_below": {
                                        trigger.addTrigger(new EventTrigger_ProvinceInfrastructureBelow(Integer.parseInt(sLine[1]), Float.parseFloat(sLine[2])), nextType);
                                        break;
                                    }
                                    case "province_buildings_over": {
                                        trigger.addTrigger(new EventTrigger_ProvinceBuildingsOver(Integer.parseInt(sLine[1]), Float.parseFloat(sLine[2])), nextType);
                                        break;
                                    }
                                    case "province_buildings_below": {
                                        trigger.addTrigger(new EventTrigger_ProvinceBuildingsBelow(Integer.parseInt(sLine[1]), Float.parseFloat(sLine[2])), nextType);
                                        break;
                                    }
                                    case "province_defense_lvl_over": {
                                        trigger.addTrigger(new EventTrigger_ProvinceDefenseLevelOver(Integer.parseInt(sLine[1]), Float.parseFloat(sLine[2])), nextType);
                                        break;
                                    }
                                    case "province_defense_lvl_below": {
                                        trigger.addTrigger(new EventTrigger_ProvinceDefenseLevelBelow(Integer.parseInt(sLine[1]), Float.parseFloat(sLine[2])), nextType);
                                        break;
                                    }
                                    case "province_civ_has_core": {
                                        trigger.addTrigger(new EventTrigger_ProvinceCivHasCore(Integer.parseInt(sLine[1]), sLine[2]), nextType);
                                        break;
                                    }
                                    case "province_controlled_by": {
                                        trigger.addTrigger(new EventTrigger_ProvinceControlledByCiv(Integer.parseInt(sLine[1]), sLine[2]), nextType);
                                        break;
                                    }
                                    case "province_not_controlled_by": {
                                        trigger.addTrigger(new EventTrigger_ProvinceControlledByCivNot(Integer.parseInt(sLine[1]), sLine[2]), nextType);
                                        break;
                                    }
                                    case "province_has_building": {
                                        trigger.addTrigger(new EventTrigger_ProvinceHasBuilding(Integer.parseInt(sLine[1]), Integer.parseInt(sLine[2]), Integer.parseInt(sLine[3])), nextType);
                                        break;
                                    }
                                    case "civ_capital_has_building": {
                                        trigger.addTrigger(new EventTrigger_CivCapital_HasBuilding(Integer.parseInt(sLine[1]), Integer.parseInt(sLine[2])), nextType);
                                        break;
                                    }
                                    case "civ_capital_religion_is": {
                                        trigger.addTrigger(new EventTrigger_CivCapital_ReligionIs(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "province_buildings_limit_over": {
                                        trigger.addTrigger(new EventTrigger_ProvinceBuildingsLimitOver(Integer.parseInt(sLine[1]), Float.parseFloat(sLine[2])), nextType);
                                        break;
                                    }
                                    case "province_buildings_limit_below": {
                                        trigger.addTrigger(new EventTrigger_ProvinceBuildingsBelow(Integer.parseInt(sLine[1]), Float.parseFloat(sLine[2])), nextType);
                                        break;
                                    }
                                    case "province_is_capital": {
                                        trigger.addTrigger(new EventTrigger_ProvinceIsCapital(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "province_is_occupied": {
                                        trigger.addTrigger(new EventTrigger_ProvinceIsOccupied(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "province_is_not_occupied": {
                                        trigger.addTrigger(new EventTrigger_ProvinceIsOccupiedNot(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "province_is_under_siege": {
                                        trigger.addTrigger(new EventTrigger_ProvinceIsUnderSiege(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "buildings_constructed_over": {
                                        trigger.addTrigger(new EventTrigger_Buildings_ConstructedOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "buildings_constructed_below": {
                                        trigger.addTrigger(new EventTrigger_Buildings_ConstructedBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "administrative_buildings_constructed_over": {
                                        trigger.addTrigger(new EventTrigger_Buildings_ConstructedAdministrativeOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "administrative_buildings_constructed_below": {
                                        trigger.addTrigger(new EventTrigger_Buildings_ConstructedAdministrativeBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "economy_buildings_constructed_over": {
                                        trigger.addTrigger(new EventTrigger_Buildings_ConstructedEconomyOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "economy_buildings_constructed_below": {
                                        trigger.addTrigger(new EventTrigger_Buildings_ConstructedEconomyBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "military_buildings_constructed_over": {
                                        trigger.addTrigger(new EventTrigger_Buildings_ConstructedMilitaryOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "military_buildings_constructed_below": {
                                        trigger.addTrigger(new EventTrigger_Buildings_ConstructedMilitaryBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "unique_capital_buildings_constructed_over": {
                                        trigger.addTrigger(new EventTrigger_Buildings_ConstructedCapitalOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "unique_capital_buildings_constructed_below": {
                                        trigger.addTrigger(new EventTrigger_Buildings_ConstructedCapitalBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "playing_time_over": {
                                        trigger.addTrigger(new EventTrigger_PlayingTimeOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "playing_time_below": {
                                        trigger.addTrigger(new EventTrigger_PlayingTimeBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "year_over": {
                                        trigger.addTrigger(new EventTrigger_YearOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "year_below": {
                                        trigger.addTrigger(new EventTrigger_YearBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "exact_day": {
                                        trigger.addTrigger(new EventTrigger_ExactDay(Integer.parseInt(sLine[1]), Integer.parseInt(sLine[2]), Integer.parseInt(sLine[3])), nextType);
                                        exactDate = true;
                                        exactDay = Integer.parseInt(sLine[1]);
                                        exactMonth = Integer.parseInt(sLine[2]);
                                        exactYear = Integer.parseInt(sLine[3]);
                                        break;
                                    }
                                    case "civ_provinces_over": {
                                        trigger.addTrigger(new EventTrigger_CivProvincesOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_provinces_below": {
                                        trigger.addTrigger(new EventTrigger_CivProvincesBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_provinces_equals": {
                                        trigger.addTrigger(new EventTrigger_CivProvincesEquals(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_population_over": {
                                        trigger.addTrigger(new EventTrigger_CivPopulationOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_population_below": {
                                        trigger.addTrigger(new EventTrigger_CivPopulationBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_economy_over": {
                                        trigger.addTrigger(new EventTrigger_CivEconomyOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_economy_below": {
                                        trigger.addTrigger(new EventTrigger_CivEconomyBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_capital_buildings_over": {
                                        trigger.addTrigger(new EventTrigger_CivCapital_BuildingsOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_capital_buildings_below": {
                                        trigger.addTrigger(new EventTrigger_CivCapital_BuildingsBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_capital_tax_efficiency_over": {
                                        trigger.addTrigger(new EventTrigger_CivCapital_TaxEfficiencyOver(Float.parseFloat(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_capital_tax_efficiency_below": {
                                        trigger.addTrigger(new EventTrigger_CivCapital_TaxEfficiencyBelow(Float.parseFloat(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_capital_continent_is": {
                                        trigger.addTrigger(new EventTrigger_CivCapital_ContinentIs(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_capital_continent_is_not": {
                                        trigger.addTrigger(new EventTrigger_CivCapital_ContinentIsNot(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_capital_economy_over": {
                                        trigger.addTrigger(new EventTrigger_CivCapital_EconomyOver(Float.parseFloat(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_capital_economy_below": {
                                        trigger.addTrigger(new EventTrigger_CivCapital_EconomyBelow(Float.parseFloat(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_capital_manpower_over": {
                                        trigger.addTrigger(new EventTrigger_CivCapital_ManpowerOver(Float.parseFloat(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_capital_manpower_below": {
                                        trigger.addTrigger(new EventTrigger_CivCapital_ManpowerBelow(Float.parseFloat(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_capital_unrest_over": {
                                        trigger.addTrigger(new EventTrigger_CivCapital_UnrestOver(Float.parseFloat(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_capital_unrest_below": {
                                        trigger.addTrigger(new EventTrigger_CivCapital_UnrestBelow(Float.parseFloat(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_capital_growth_rate_over": {
                                        trigger.addTrigger(new EventTrigger_CivCapital_GrowthRateOver(Float.parseFloat(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_capital_growth_rate_below": {
                                        trigger.addTrigger(new EventTrigger_CivCapital_GrowthRateBelow(Float.parseFloat(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_capital_income_over": {
                                        trigger.addTrigger(new EventTrigger_CivCapital_IncomeOver(Float.parseFloat(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_capital_income_below": {
                                        trigger.addTrigger(new EventTrigger_CivCapital_IncomeBelow(Float.parseFloat(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_capital_infrastructure_over": {
                                        trigger.addTrigger(new EventTrigger_CivCapital_InfrastructureOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_capital_infrastructure_below": {
                                        trigger.addTrigger(new EventTrigger_CivCapital_InfrastructureBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_capital_population_over": {
                                        trigger.addTrigger(new EventTrigger_CivCapital_PopulationOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_capital_population_below": {
                                        trigger.addTrigger(new EventTrigger_CivCapital_PopulationBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_capital_fort_level_over": {
                                        trigger.addTrigger(new EventTrigger_CivCapital_FortLevelOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_capital_fort_level_below": {
                                        trigger.addTrigger(new EventTrigger_CivCapital_FortLevelBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_capital_is_occupied": {
                                        trigger.addTrigger(new EventTrigger_CivCapital_IsOccupied(), nextType);
                                        break;
                                    }
                                    case "civ_capital_is_not_occupied": {
                                        trigger.addTrigger(new EventTrigger_CivCapital_IsOccupiedNot(), nextType);
                                        break;
                                    }
                                    case "civ_capital_is_under_siege": {
                                        trigger.addTrigger(new EventTrigger_CivCapital_IsUnderSiege(), nextType);
                                        break;
                                    }
                                    case "civ_gold_over": {
                                        trigger.addTrigger(new EventTrigger_CivGoldOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_gold_below": {
                                        trigger.addTrigger(new EventTrigger_CivGoldBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_religion_is": {
                                        trigger.addTrigger(new EventTrigger_CivReligionIs(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_government_is": {
                                        trigger.addTrigger(new EventTrigger_CivGovernmentIs(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_allies_over": {
                                        trigger.addTrigger(new EventTrigger_CivAlliesOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_allies_below": {
                                        trigger.addTrigger(new EventTrigger_CivAlliesBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_defensive_pacts_over": {
                                        trigger.addTrigger(new EventTrigger_CivDefensivePactsOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_defensive_pacts_below": {
                                        trigger.addTrigger(new EventTrigger_CivDefensivePactsBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_non_aggression_pacts_over": {
                                        trigger.addTrigger(new EventTrigger_CivNonAggressionPactsOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_non_aggression_pacts_below": {
                                        trigger.addTrigger(new EventTrigger_CivNonAggressionPactsBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_vassals_over": {
                                        trigger.addTrigger(new EventTrigger_CivVassalsOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_vassals_below": {
                                        trigger.addTrigger(new EventTrigger_CivVassalsBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_neighbors_over": {
                                        trigger.addTrigger(new EventTrigger_CivNeighborsOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_neighbors_below": {
                                        trigger.addTrigger(new EventTrigger_CivNeighborsBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_unlocked_legacies_over": {
                                        trigger.addTrigger(new EventTrigger_CivUnlockedLegaciesOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_unlocked_legacies_below": {
                                        trigger.addTrigger(new EventTrigger_CivUnlockedLegaciesBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_unlocked_technologies_over": {
                                        trigger.addTrigger(new EventTrigger_CivUnlockedTechnologiesOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_unlocked_technologies_below": {
                                        trigger.addTrigger(new EventTrigger_CivUnlockedTechnologiesBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_unlocked_advantages_over": {
                                        trigger.addTrigger(new EventTrigger_CivUnlockedAdvantagesOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_unlocked_advantages_below": {
                                        trigger.addTrigger(new EventTrigger_CivUnlockedAdvantagesBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_rank_position_over": {
                                        trigger.addTrigger(new EventTrigger_CivRankPositionOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_rank_position_below": {
                                        trigger.addTrigger(new EventTrigger_CivRankPositionBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_prestige_over": {
                                        trigger.addTrigger(new EventTrigger_CivRankPrestigeOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_prestige_below": {
                                        trigger.addTrigger(new EventTrigger_CivRankPrestigeBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "is_civ": {
                                        trigger.addTrigger(new EventTrigger_IsCiv(sLine[1]), nextType);
                                        break;
                                    }
                                    case "civ_battle_width_over": {
                                        trigger.addTrigger(new EventTrigger_CivBattleWidthOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_battle_width_below": {
                                        trigger.addTrigger(new EventTrigger_CivBattleWidthBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_military_academy_over": {
                                        trigger.addTrigger(new EventTrigger_CivMilitaryAcademyOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_military_academy_below": {
                                        trigger.addTrigger(new EventTrigger_CivMilitaryAcademyBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_military_academy_for_generals_over": {
                                        trigger.addTrigger(new EventTrigger_CivMilitaryAcademyForGeneralsOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_military_academy_for_generals_below": {
                                        trigger.addTrigger(new EventTrigger_CivMilitaryAcademyForGeneralsBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_capital_city_over": {
                                        trigger.addTrigger(new EventTrigger_CivCapitalCityOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_capital_city_below": {
                                        trigger.addTrigger(new EventTrigger_CivCapitalCityBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_supreme_court_over": {
                                        trigger.addTrigger(new EventTrigger_CivSupremeCourtOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_supreme_court_below": {
                                        trigger.addTrigger(new EventTrigger_CivSupremeCourtBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_nuclear_reactor_over": {
                                        trigger.addTrigger(new EventTrigger_CivNuclearReactorOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_nuclear_reactor_below": {
                                        trigger.addTrigger(new EventTrigger_CivNuclearReactorBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_regiments_limit_over": {
                                        trigger.addTrigger(new EventTrigger_CivRegimentsLimitOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_regiments_limit_below": {
                                        trigger.addTrigger(new EventTrigger_CivRegimentsLimitBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_regiments_over": {
                                        trigger.addTrigger(new EventTrigger_CivRegimentsOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_regiments_below": {
                                        trigger.addTrigger(new EventTrigger_CivRegimentsBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_regiments_over_regiments_limit": {
                                        trigger.addTrigger(new EventTrigger_CivRegimentsOverRegimentsLimit(Boolean.parseBoolean(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_gold_over_max_amount_of_gold": {
                                        trigger.addTrigger(new EventTrigger_CivGoldOver_MaxAmountOfGold(Boolean.parseBoolean(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_administrative_advisor_skill_over": {
                                        trigger.addTrigger(new EventTrigger_CivAdministrativeAdvisorSkillOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_administrative_advisor_skill_below": {
                                        trigger.addTrigger(new EventTrigger_CivAdministrativeAdvisorSkillBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_economic_advisor_skill_over": {
                                        trigger.addTrigger(new EventTrigger_CivEconomyAdvisorSkillOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_economic_advisor_skill_below": {
                                        trigger.addTrigger(new EventTrigger_CivEconomyAdvisorSkillBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_innovation_advisor_skill_over": {
                                        trigger.addTrigger(new EventTrigger_CivInnovationAdvisorSkillOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_innovation_advisor_skill_below": {
                                        trigger.addTrigger(new EventTrigger_CivInnovationAdvisorSkillBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_military_advisor_skill_over": {
                                        trigger.addTrigger(new EventTrigger_CivMilitaryAdvisorSkillOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_military_advisor_skill_below": {
                                        trigger.addTrigger(new EventTrigger_CivMilitaryAdvisorSkillBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_advisor_age_over": {
                                        if (sLine.length > 2) {
                                            trigger.addTrigger(new EventTrigger_CivAdvisor_AgeOver(Integer.parseInt(sLine[1]), Integer.parseInt(sLine[2])), nextType);
                                            break;
                                        }
                                        break;
                                    }
                                    case "civ_advisor_production_efficiency_over": {
                                        if (sLine.length > 2) {
                                            trigger.addTrigger(new EventTrigger_CivAdvisor_ProductionEfficiencyOver(Integer.parseInt(sLine[1]), Float.parseFloat(sLine[2])), nextType);
                                            break;
                                        }
                                        break;
                                    }
                                    case "civ_advisor_construction_cost_over": {
                                        if (sLine.length > 2) {
                                            trigger.addTrigger(new EventTrigger_CivAdvisor_ConstructionCostOver(Integer.parseInt(sLine[1]), Float.parseFloat(sLine[2])), nextType);
                                            break;
                                        }
                                        break;
                                    }
                                    case "civ_manpower_over": {
                                        trigger.addTrigger(new EventTrigger_CivManpowerOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_manpower_below": {
                                        trigger.addTrigger(new EventTrigger_CivManpowerBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_max_manpower_over": {
                                        trigger.addTrigger(new EventTrigger_CivMaxManpowerOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_max_manpower_below": {
                                        trigger.addTrigger(new EventTrigger_CivMaxManpowerBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_manpower_perc_over": {
                                        trigger.addTrigger(new EventTrigger_CivManpower_PercOfMax_Over(Float.parseFloat(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_manpower_perc_below": {
                                        trigger.addTrigger(new EventTrigger_CivManpower_PercOfMax_Below(Float.parseFloat(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_largest_producer_over": {
                                        trigger.addTrigger(new EventTrigger_CivLargestProducerOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "largest_producer_production_over": {
                                        trigger.addTrigger(new EventTrigger_LargestProducer_ProductionOver(Integer.parseInt(sLine[1]), Integer.parseInt(sLine[2])), nextType);
                                        break;
                                    }
                                    case "civ_is_largest_producer": {
                                        trigger.addTrigger(new EventTrigger_IsLargestProducer(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_legacy_over": {
                                        trigger.addTrigger(new EventTrigger_CivLegacyOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_legacy_below": {
                                        trigger.addTrigger(new EventTrigger_CivLegacyBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "recruited_advisors_over": {
                                        trigger.addTrigger(new EventTrigger_RecruitedAdvisorsOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "recruited_advisors_below": {
                                        trigger.addTrigger(new EventTrigger_RecruitedAdvisorsBelow(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_inflation_over": {
                                        trigger.addTrigger(new EventTrigger_CivInflationOver(Float.parseFloat(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_inflation_below": {
                                        trigger.addTrigger(new EventTrigger_CivInflationBelow(Float.parseFloat(sLine[1])), nextType);
                                        break;
                                    }
                                    case "alliance_special_is_leader": {
                                        trigger.addTrigger(new EventTrigger_AllianceIsLeader(), nextType);
                                        break;
                                    }
                                    case "alliance_special_is_leader_id": {
                                        trigger.addTrigger(new EventTrigger_AllianceIsLeaderID(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "alliance_special_is_member_id": {
                                        trigger.addTrigger(new EventTrigger_AllianceIsInAlliance(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "alliance_special_is_not_member_id": {
                                        trigger.addTrigger(new EventTrigger_AllianceIsNotInAlliance(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_loans_over": {
                                        trigger.addTrigger(new EventTrigger_CivLoansOver(Float.parseFloat(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_loans_below": {
                                        trigger.addTrigger(new EventTrigger_CivLoansBelow(Float.parseFloat(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_total_income_over": {
                                        trigger.addTrigger(new EventTrigger_CivIncomeOver(Float.parseFloat(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_total_income_below": {
                                        trigger.addTrigger(new EventTrigger_CivIncomeBelow(Float.parseFloat(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_diplomacy_over": {
                                        trigger.addTrigger(new EventTrigger_CivDiplomacyOver(Float.parseFloat(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_diplomacy_below": {
                                        trigger.addTrigger(new EventTrigger_CivDiplomacyBelow(Float.parseFloat(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_legacy_per_month_over": {
                                        trigger.addTrigger(new EventTrigger_CivLegacyPerMonthOver(Float.parseFloat(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_legacy_per_month_below": {
                                        trigger.addTrigger(new EventTrigger_CivLegacyPerMonthBelow(Float.parseFloat(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_research_per_month_over": {
                                        trigger.addTrigger(new EventTrigger_CivResearchPerMonthOver(Float.parseFloat(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_research_per_month_below": {
                                        trigger.addTrigger(new EventTrigger_CivResearchPerMonthBelow(Float.parseFloat(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_income_taxation_over": {
                                        trigger.addTrigger(new EventTrigger_CivIncomeTaxationOver(Float.parseFloat(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_income_taxation_below": {
                                        trigger.addTrigger(new EventTrigger_CivIncomeTaxationBelow(Float.parseFloat(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_income_economy_over": {
                                        trigger.addTrigger(new EventTrigger_CivIncomeEconomyOver(Float.parseFloat(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_income_economy_below": {
                                        trigger.addTrigger(new EventTrigger_CivIncomeEconomyBelow(Float.parseFloat(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_income_production_over": {
                                        trigger.addTrigger(new EventTrigger_CivIncomeProductionOver(Float.parseFloat(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_income_production_below": {
                                        trigger.addTrigger(new EventTrigger_CivIncomeProductionBelow(Float.parseFloat(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civ_is_at_war": {
                                        trigger.addTrigger(new EventTrigger_IsAtWar(), nextType);
                                        break;
                                    }
                                    case "civ_is_not_at_war": {
                                        trigger.addTrigger(new EventTrigger_IsNotAtWar(), nextType);
                                        break;
                                    }
                                    case "civ_is_at_war_days_over": {
                                        trigger.addTrigger(new EventTrigger_IsAtWar_DaysOver(Integer.parseInt(sLine[1])), nextType);
                                        break;
                                    }
                                    case "civs_are_at_war": {
                                        trigger.addTrigger(new EventTrigger_AreAtWar(sLine[1], sLine[2]), nextType);
                                        break;
                                    }
                                    case "civs_are_not_at_war": {
                                        trigger.addTrigger(new EventTrigger_AreNotAtWar(sLine[1], sLine[2]), nextType);
                                        break;
                                    }
                                    case "civ_has_more_provinces_than_civ": {
                                        trigger.addTrigger(new EventTrigger_CivHasMoreProvinceThanCiv(sLine[1], sLine[2]), nextType);
                                        break;
                                    }
                                    case "civ_has_larger_population_than_civ": {
                                        trigger.addTrigger(new EventTrigger_CivHasLargerPopulationThanCiv(sLine[1], sLine[2]), nextType);
                                        break;
                                    }
                                    case "civ_has_larger_economy_than_civ": {
                                        trigger.addTrigger(new EventTrigger_CivHasLargerEconomyThanCiv(sLine[1], sLine[2]), nextType);
                                        break;
                                    }
                                    case "civ_has_larger_regiments_limit_than_civ": {
                                        trigger.addTrigger(new EventTrigger_CivHasLargerRegimentsLimitThanCiv(sLine[1], sLine[2]), nextType);
                                        break;
                                    }
                                    case "civ_has_more_regiments_than_civ": {
                                        trigger.addTrigger(new EventTrigger_CivHasMoreRegimentsThanCiv(sLine[1], sLine[2]), nextType);
                                        break;
                                    }
                                    case "civ_has_higher_ranking_than_civ": {
                                        trigger.addTrigger(new EventTrigger_CivHasHigherRankThanCiv(sLine[1], sLine[2]), nextType);
                                        break;
                                    }
                                    case "civ_has_more_technologies_than_civ": {
                                        trigger.addTrigger(new EventTrigger_CivHasMoreTechsThanCiv(sLine[1], sLine[2]), nextType);
                                        break;
                                    }
                                    case "civs_are_rivals": {
                                        trigger.addTrigger(new EventTrigger_CivsAreRivals(sLine[1], sLine[2]), nextType);
                                        break;
                                    }
                                    case "civ_has_rivalry": {
                                        trigger.addTrigger(new EventTrigger_CivRivaledCiv(sLine[1], sLine[2]), nextType);
                                        break;
                                    }
                                    case "civ_has_rivalry_not": {
                                        trigger.addTrigger(new EventTrigger_CivRivaledCivNot(sLine[1], sLine[2]), nextType);
                                        break;
                                    }
                                    case "civ_is_vassal_of_civ": {
                                        trigger.addTrigger(new EventTrigger_IsVassalOfCiv(sLine[1], sLine[2]), nextType);
                                        break;
                                    }
                                    case "civs_are_neighbors": {
                                        trigger.addTrigger(new EventTrigger_CivsAreNeighbors(sLine[1], sLine[2]), nextType);
                                        break;
                                    }
                                    case "civs_are_not_neighbors": {
                                        trigger.addTrigger(new EventTrigger_CivsAreNeighborsNot(sLine[1], sLine[2]), nextType);
                                        break;
                                    }
                                    case "civs_opinion_over": {
                                        trigger.addTrigger(new EventTrigger_OpinionOver(sLine[1], sLine[2], Float.parseFloat(sLine[3])), nextType);
                                        break;
                                    }
                                    case "civs_opinion_below": {
                                        trigger.addTrigger(new EventTrigger_OpinionBelow(sLine[1], sLine[2], Float.parseFloat(sLine[3])), nextType);
                                        break;
                                    }
                                    case "civs_have_defensive_pact": {
                                        trigger.addTrigger(new EventTrigger_HaveDefensivePact(sLine[1], sLine[2]), nextType);
                                        break;
                                    }
                                    case "civs_have_non_aggression": {
                                        trigger.addTrigger(new EventTrigger_HaveNonAggressionPact(sLine[1], sLine[2]), nextType);
                                        break;
                                    }
                                    case "civs_have_alliance": {
                                        trigger.addTrigger(new EventTrigger_HaveAlliance(sLine[1], sLine[2]), nextType);
                                        break;
                                    }
                                    case "civs_have_truce": {
                                        trigger.addTrigger(new EventTrigger_HaveTruce(sLine[1], sLine[2]), nextType);
                                        break;
                                    }
                                    case "civ_have_guarantee": {
                                        trigger.addTrigger(new EventTrigger_HaveGuarantee(sLine[1], sLine[2]), nextType);
                                        break;
                                    }
                                    case "civ_have_military_access": {
                                        trigger.addTrigger(new EventTrigger_HaveMilitaryAccess(sLine[1], sLine[2]), nextType);
                                        break;
                                    }
                                    case "has_variable": {
                                        trigger.addTrigger(new EventTrigger_HasVariable(sLine[1]), nextType);
                                        break;
                                    }
                                    case "has_variable_not": {
                                        trigger.addTrigger(new EventTrigger_HasVariableNot(sLine[1]), nextType);
                                        break;
                                    }
                                    case "has_variable_civ": {
                                        trigger.addTrigger(new EventTrigger_HasVariableCiv(sLine[1], sLine[2]), nextType);
                                        break;
                                    }
                                    case "is_puppet": {
                                        trigger.addTrigger(new EventTrigger_IsVassal(sLine[1]), nextType);
                                        break;
                                    }
                                    case "is_not_puppet": {
                                        trigger.addTrigger(new EventTrigger_IsNotVassal(sLine[1]), nextType);
                                        break;
                                    }
                                    case "is_player": {
                                        trigger.addTrigger(new EventTrigger_IsPlayer(sLine[1]), nextType);
                                        break;
                                    }
                                    case "is_not_player": {
                                        trigger.addTrigger(new EventTrigger_IsNotPlayer(sLine[1]), nextType);
                                        break;
                                    }
                                    case "exists": {
                                        trigger.addTrigger(new EventTrigger_Exists(sLine[1]), nextType);
                                        break;
                                    }
                                    case "not_exists": {
                                        trigger.addTrigger(new EventTrigger_ExistsNot(sLine[1]), nextType);
                                        break;
                                    }
                                    case "exists_any": {
                                        trigger.addTrigger(new EventTrigger_ExistsAny(sLine[1]), nextType);
                                        break;
                                    }
                                    case "exists_any_not": {
                                        trigger.addTrigger(new EventTrigger_ExistsAnyNot(sLine[1]), nextType);
                                        break;
                                    }
                                    case "civ_tag_religion_is": {
                                        trigger.addTrigger(new EventTrigger_CivTag_ReligionIs(sLine[1], Integer.parseInt(sLine[2])), nextType);
                                        break;
                                    }
                                    case "civ_tag_religion_is_not": {
                                        trigger.addTrigger(new EventTrigger_CivTag_ReligionIsNot(sLine[1], Integer.parseInt(sLine[2])), nextType);
                                        break;
                                    }
                                    case "civ_tag_government_is": {
                                        trigger.addTrigger(new EventTrigger_CivTag_GovernmentIs(sLine[1], Integer.parseInt(sLine[2])), nextType);
                                        break;
                                    }
                                    case "civ_tag_government_is_not": {
                                        trigger.addTrigger(new EventTrigger_CivTag_GovernmentIsNot(sLine[1], Integer.parseInt(sLine[2])), nextType);
                                        break;
                                    }
                                    default: {
                                        CFG.LOG(" MISSING IN TRIGGER -> " + sLine[0] + " *** Line: " + (i + 1));
                                        break;
                                    }
                                }
                            }
                            else if (inOption) {
                                final String s11;
                                final String s5 = s11 = sLine[0];
                                switch (s11) {
                                    case "name": {
                                        option.name = sLine[1];
                                        break;
                                    }
                                    case "gold": {
                                        option.outcome.add(new EventOutcome_Gold(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "gold_monthly_income": {
                                        option.outcome.add(new EventOutcome_Gold_MonthlyIncome(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "legacy": {
                                        option.outcome.add(new EventOutcome_Legacy(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "legacy_monthly": {
                                        option.outcome.add(new EventOutcome_Legacy_Monthly(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "research": {
                                        option.outcome.add(new EventOutcome_Research(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "inflation": {
                                        option.outcome.add(new EventOutcome_Inflation(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "move_capital": {
                                        option.outcome.add(new EventOutcome_MoveCapital(Integer.parseInt(sLine[1])));
                                        break;
                                    }
                                    case "manpower": {
                                        option.outcome.add(new EventOutcome_Manpower(Integer.parseInt(sLine[1])));
                                        break;
                                    }
                                    case "ae_set": {
                                        option.outcome.add(new EventOutcome_AggressiveExpansion_Set(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "advantage_points": {
                                        option.outcome.add(new EventOutcome_AdvantagePoints(Integer.parseInt(sLine[1])));
                                        break;
                                    }
                                    case "add_variable": {
                                        option.outcome.add(new EventOutcome_AddVariable(sLine[1]));
                                        break;
                                    }
                                    case "add_variable2": {
                                        option.outcome.add(new EventOutcome_AddVariable_Civ(sLine[1], sLine[2]));
                                        break;
                                    }
                                    case "ai_aggression": {
                                        option.outcome.add(new EventOutcome_AI_Aggression(Integer.parseInt(sLine[1])));
                                        break;
                                    }
                                    case "add_new_army": {
                                        final List<Integer> unitID = new ArrayList<Integer>();
                                        final List<Integer> armyID = new ArrayList<Integer>();
                                        try {
                                            for (int z = 1; z < sLine.length - 1; z += 2) {
                                                unitID.add(Integer.parseInt(sLine[z]));
                                                armyID.add(Integer.parseInt(sLine[z + 1]));
                                            }
                                        }
                                        catch (final Exception ex) {
                                            CFG.exceptionStack(ex);
                                        }
                                        option.outcome.add(new EventOutcome_AddArmy(unitID, armyID));
                                        break;
                                    }
                                    case "military_academy": {
                                        option.outcome.add(new EventOutcome_MilitaryAcademy(Integer.parseInt(sLine[1])));
                                        break;
                                    }
                                    case "military_academy_generals": {
                                        option.outcome.add(new EventOutcome_MilitaryAcademyForGenerals(Integer.parseInt(sLine[1])));
                                        break;
                                    }
                                    case "capital_city_level": {
                                        option.outcome.add(new EventOutcome_CapitalCityLevel(Integer.parseInt(sLine[1])));
                                        break;
                                    }
                                    case "supreme_court": {
                                        option.outcome.add(new EventOutcome_SupremeCourt(Integer.parseInt(sLine[1])));
                                        break;
                                    }
                                    case "nuclear_reactor": {
                                        option.outcome.add(new EventOutcome_NuclearReactor(Integer.parseInt(sLine[1])));
                                        break;
                                    }
                                    case "set_civ_tag": {
                                        option.outcome.add(new EventOutcome_SetCiv(sLine[1]));
                                        break;
                                    }
                                    case "set_civ_tag2": {
                                        option.outcome.add(new EventOutcome_SetCiv2(sLine[1], sLine[2]));
                                        break;
                                    }
                                    case "declare_war": {
                                        option.outcome.add(new EventOutcome_DeclareWar(sLine[1]));
                                        break;
                                    }
                                    case "declare_war2": {
                                        option.outcome.add(new EventOutcome_DeclareWar2(sLine[1], sLine[2]));
                                        break;
                                    }
                                    case "player_set_civ": {
                                        option.outcome.add(new EventOutcome_PlayerChangeCiv(sLine[1]));
                                        break;
                                    }
                                    case "change_ideology": {
                                        option.outcome.add(new EventOutcome_ChangeIdeology(Integer.parseInt(sLine[1])));
                                        break;
                                    }
                                    case "change_religion": {
                                        option.outcome.add(new EventOutcome_ChangeReligion(Integer.parseInt(sLine[1])));
                                        break;
                                    }
                                    case "change_ideology_civ": {
                                        option.outcome.add(new EventOutcome_ChangeIdeologyCiv(Integer.parseInt(sLine[1]), sLine[2]));
                                        break;
                                    }
                                    case "change_religion_civ": {
                                        option.outcome.add(new EventOutcome_ChangeReligionCiv(Integer.parseInt(sLine[1]), sLine[2]));
                                        break;
                                    }
                                    case "run_event": {
                                        option.outcome.add(new EventOutcome_RunEvent(sLine[1]));
                                        break;
                                    }
                                    case "promote_advisor": {
                                        option.outcome.add(new EventOutcome_PromoteAdvisor(Integer.parseInt(sLine[1])));
                                        break;
                                    }
                                    case "join_alliance_special_id_first_tier": {
                                        option.outcome.add(new EventOutcome_JoinAllianceSpecialFirstTier(Integer.parseInt(sLine[1])));
                                        break;
                                    }
                                    case "join_alliance_special_id_second_tier": {
                                        option.outcome.add(new EventOutcome_JoinAllianceSpecialSecondTier(Integer.parseInt(sLine[1])));
                                        break;
                                    }
                                    case "leave_alliance_special_id": {
                                        option.outcome.add(new EventOutcome_LeaveAllianceSpecial(Integer.parseInt(sLine[1])));
                                        break;
                                    }
                                    case "kill_advisor": {
                                        option.outcome.add(new EventOutcome_KillAdvisor(Integer.parseInt(sLine[1])));
                                        break;
                                    }
                                    case "kill_ruler": {
                                        option.outcome.add(new EventOutcome_KillRuler());
                                        break;
                                    }
                                    case "kill_ruler_chance": {
                                        option.outcome.add(new EventOutcome_KillRuler_Chance(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "add_general": {
                                        option.outcome.add(new EventOutcome_AddGeneral());
                                        break;
                                    }
                                    case "price_change": {
                                        option.outcome.add(new EventOutcome_Resource_PriceChange(Integer.parseInt(sLine[1]), Integer.parseInt(sLine[2]), Integer.parseInt(sLine[3]), Integer.parseInt(sLine[4]), Integer.parseInt(sLine[5])));
                                        break;
                                    }
                                    case "price_change_up": {
                                        option.outcome.add(new EventOutcome_Resource_PriceChangeUp(Integer.parseInt(sLine[1]), Integer.parseInt(sLine[2]), Integer.parseInt(sLine[3]), Integer.parseInt(sLine[4]), Integer.parseInt(sLine[5])));
                                        break;
                                    }
                                    case "price_change_down": {
                                        option.outcome.add(new EventOutcome_Resource_PriceChangeDown(Integer.parseInt(sLine[1]), Integer.parseInt(sLine[2]), Integer.parseInt(sLine[3]), Integer.parseInt(sLine[4]), Integer.parseInt(sLine[5])));
                                        break;
                                    }
                                    case "price_change_group": {
                                        option.outcome.add(new EventOutcome_Resource_PriceChangeGroup(Integer.parseInt(sLine[1]), Integer.parseInt(sLine[2]), Integer.parseInt(sLine[3]), Integer.parseInt(sLine[4]), Integer.parseInt(sLine[5])));
                                        break;
                                    }
                                    case "price_change_group_up": {
                                        option.outcome.add(new EventOutcome_Resource_PriceChangeGroupUp(Integer.parseInt(sLine[1]), Integer.parseInt(sLine[2]), Integer.parseInt(sLine[3]), Integer.parseInt(sLine[4]), Integer.parseInt(sLine[5])));
                                        break;
                                    }
                                    case "price_change_group_down": {
                                        option.outcome.add(new EventOutcome_Resource_PriceChangeGroupDown(Integer.parseInt(sLine[1]), Integer.parseInt(sLine[2]), Integer.parseInt(sLine[3]), Integer.parseInt(sLine[4]), Integer.parseInt(sLine[5])));
                                        break;
                                    }
                                    case "price_change_random": {
                                        option.outcome.add(new EventOutcome_Resource_PriceChangeRandom(Integer.parseInt(sLine[1]), Integer.parseInt(sLine[2]), Integer.parseInt(sLine[3]), Integer.parseInt(sLine[4])));
                                        break;
                                    }
                                    case "price_change_random_up": {
                                        option.outcome.add(new EventOutcome_Resource_PriceChangeRandomUp(Integer.parseInt(sLine[1]), Integer.parseInt(sLine[2]), Integer.parseInt(sLine[3]), Integer.parseInt(sLine[4])));
                                        break;
                                    }
                                    case "price_change_random_down": {
                                        option.outcome.add(new EventOutcome_Resource_PriceChangeRandomDown(Integer.parseInt(sLine[1]), Integer.parseInt(sLine[2]), Integer.parseInt(sLine[3]), Integer.parseInt(sLine[4])));
                                        break;
                                    }
                                    case "add_general2": {
                                        option.outcome.add(new EventOutcome_AddGeneral_Character(sLine[1]));
                                        break;
                                    }
                                    case "add_general3": {
                                        option.outcome.add(new EventOutcome_AddGeneral_CharacterAttackDefense(sLine[1], Integer.parseInt(sLine[2]), Integer.parseInt(sLine[3])));
                                        break;
                                    }
                                    case "add_ruler": {
                                        try {
                                            option.outcome.add(new EventOutcome_AddRuler(sLine[1], sLine[2], sLine[3], Integer.parseInt(sLine[4]), Integer.parseInt(sLine[5]), Integer.parseInt(sLine[6])));
                                        }
                                        catch (final Exception ex2) {
                                            CFG.exceptionStack(ex2);
                                        }
                                        break;
                                    }
                                    case "add_advisor": {
                                        option.outcome.add(new EventOutcome_AddAdvisor(Integer.parseInt(sLine[1])));
                                        break;
                                    }
                                    case "add_advisor2": {
                                        option.outcome.add(new EventOutcome_AddAdvisor_Character(sLine[2], Integer.parseInt(sLine[1])));
                                        break;
                                    }
                                    case "annex": {
                                        try {
                                            if (sLine.length > 1 && sLine[1] != null && sLine[1].length() > 0) {
                                                final String[] tSplit = sLine[1].split(";");
                                                final List<Integer> nProvinces = new ArrayList<Integer>();
                                                for (int j = 0; j < tSplit.length; ++j) {
                                                    try {
                                                        nProvinces.add(Integer.parseInt(tSplit[j]));
                                                    }
                                                    catch (final Exception ex3) {
                                                        CFG.exceptionStack(ex3);
                                                    }
                                                }
                                                if (nProvinces.size() > 0) {
                                                    option.outcome.add(new EventOutcome_AnnexProvinces(nProvinces));
                                                }
                                            }
                                        }
                                        catch (final Exception ex2) {
                                            CFG.exceptionStack(ex2);
                                        }
                                        break;
                                    }
                                    case "annex_from_civ": {
                                        try {
                                            if (sLine.length > 2 && sLine[2] != null && sLine[2].length() > 0 && sLine[1].length() > 0) {
                                                final String[] tSplit = sLine[2].split(";");
                                                final List<Integer> nProvinces = new ArrayList<Integer>();
                                                for (int j = 0; j < tSplit.length; ++j) {
                                                    try {
                                                        nProvinces.add(Integer.parseInt(tSplit[j]));
                                                    }
                                                    catch (final Exception ex3) {
                                                        CFG.exceptionStack(ex3);
                                                    }
                                                }
                                                if (nProvinces.size() > 0) {
                                                    option.outcome.add(new EventOutcome_AnnexProvincesFromCiv(sLine[1], nProvinces));
                                                }
                                            }
                                        }
                                        catch (final Exception ex2) {
                                            CFG.exceptionStack(ex2);
                                        }
                                        break;
                                    }
                                    case "annex_by_civ_from_civ": {
                                        try {
                                            if (sLine.length > 3 && sLine[3] != null && sLine[3].length() > 0) {
                                                final String[] tSplit = sLine[3].split(";");
                                                final List<Integer> nProvinces = new ArrayList<Integer>();
                                                for (int j = 0; j < tSplit.length; ++j) {
                                                    try {
                                                        nProvinces.add(Integer.parseInt(tSplit[j]));
                                                    }
                                                    catch (final Exception ex3) {
                                                        CFG.exceptionStack(ex3);
                                                    }
                                                }
                                                if (nProvinces.size() > 0) {
                                                    option.outcome.add(new EventOutcome_AnnexedProvincesByCivFromCiv(sLine[1], sLine[2], nProvinces));
                                                }
                                            }
                                        }
                                        catch (final Exception ex2) {
                                            CFG.exceptionStack(ex2);
                                        }
                                        break;
                                    }
                                    case "make_puppet": {
                                        try {
                                            option.outcome.add(new EventOutcome_Vassalize(sLine[1], sLine[2]));
                                        }
                                        catch (final Exception ex2) {
                                            CFG.exceptionStack(ex2);
                                        }
                                        break;
                                    }
                                    case "annex_civ": {
                                        try {
                                            if (sLine.length > 1 && sLine[1] != null && sLine[1].length() > 0) {
                                                option.outcome.add(new EventOutcome_AnnexCivilization(sLine[1]));
                                            }
                                        }
                                        catch (final Exception ex2) {
                                            CFG.exceptionStack(ex2);
                                        }
                                        break;
                                    }
                                    case "annexed_by_civ": {
                                        try {
                                            if (sLine.length > 1 && sLine[1] != null && sLine[1].length() > 0) {
                                                option.outcome.add(new EventOutcome_AnnexedByCivilization(sLine[1]));
                                            }
                                        }
                                        catch (final Exception ex2) {
                                            CFG.exceptionStack(ex2);
                                        }
                                        break;
                                    }
                                    case "add_alliance": {
                                        try {
                                            option.outcome.add(new EventOutcome_Alliance(sLine[1], sLine[2]));
                                        }
                                        catch (final Exception ex2) {
                                            CFG.exceptionStack(ex2);
                                        }
                                        break;
                                    }
                                    case "explode": {
                                        try {
                                            option.outcome.add(new EventOutcome_Explode(sLine[1]));
                                        }
                                        catch (final Exception ex2) {
                                            CFG.exceptionStack(ex2);
                                        }
                                        break;
                                    }
                                    case "add_non_aggression": {
                                        try {
                                            option.outcome.add(new EventOutcome_NonAggressionPact(sLine[1], sLine[2]));
                                        }
                                        catch (final Exception ex2) {
                                            CFG.exceptionStack(ex2);
                                        }
                                        break;
                                    }
                                    case "add_military_access": {
                                        try {
                                            option.outcome.add(new EventOutcome_MilitaryAccess(sLine[1], sLine[2]));
                                        }
                                        catch (final Exception ex2) {
                                            CFG.exceptionStack(ex2);
                                        }
                                        break;
                                    }
                                    case "add_guarantee": {
                                        try {
                                            option.outcome.add(new EventOutcome_Guarantee(sLine[1], sLine[2]));
                                        }
                                        catch (final Exception ex2) {
                                            CFG.exceptionStack(ex2);
                                        }
                                        break;
                                    }
                                    case "add_defensive_pact": {
                                        try {
                                            option.outcome.add(new EventOutcome_DefensivePact(sLine[1], sLine[2]));
                                        }
                                        catch (final Exception ex2) {
                                            CFG.exceptionStack(ex2);
                                        }
                                        break;
                                    }
                                    case "add_truce": {
                                        try {
                                            option.outcome.add(new EventOutcome_AddTruce(sLine[1], sLine[2]));
                                        }
                                        catch (final Exception ex2) {
                                            CFG.exceptionStack(ex2);
                                        }
                                        break;
                                    }
                                    case "ai": {
                                        option.ai = Float.parseFloat(sLine[1]);
                                        break;
                                    }
                                    case "play_music": {
                                        option.outcome.add(new EventOutcome_PlayMusic(sLine[1]));
                                        break;
                                    }
                                    case "province_add_core_civ": {
                                        option.outcome.add(new EventOutcome_Province_ID_CoreAdd(Integer.parseInt(sLine[1]), sLine[2]));
                                        break;
                                    }
                                    case "province_remove_core_civ": {
                                        option.outcome.add(new EventOutcome_Province_ID_CoreRemove(Integer.parseInt(sLine[1]), sLine[2]));
                                        break;
                                    }
                                    case "province_economy": {
                                        option.outcome.add(new EventOutcome_Province_Economy(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "province_economy_id": {
                                        option.outcome.add(new EventOutcome_Province_Economy_ID(Integer.parseInt(sLine[1]), Float.parseFloat(sLine[2])));
                                        break;
                                    }
                                    case "province_economy_capital": {
                                        option.outcome.add(new EventOutcome_Province_Economy_Capital(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "province_economy_all": {
                                        option.outcome.add(new EventOutcome_Province_Economy_All(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "province_tax_efficiency": {
                                        option.outcome.add(new EventOutcome_Province_TaxEfficiency(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "province_tax_efficiency_id": {
                                        option.outcome.add(new EventOutcome_Province_TaxEfficiency_ID(Integer.parseInt(sLine[1]), Float.parseFloat(sLine[2])));
                                        break;
                                    }
                                    case "province_tax_efficiency_capital": {
                                        option.outcome.add(new EventOutcome_Province_TaxEfficiency_Capital(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "province_tax_efficiency_all": {
                                        option.outcome.add(new EventOutcome_Province_TaxEfficiency_All(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "province_manpower": {
                                        option.outcome.add(new EventOutcome_Province_Manpower(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "province_manpower_capital": {
                                        option.outcome.add(new EventOutcome_Province_Manpower_Capital(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "province_manpower_all": {
                                        option.outcome.add(new EventOutcome_Province_Manpower_All(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "province_manpower_id": {
                                        option.outcome.add(new EventOutcome_Province_Manpower_ID(Integer.parseInt(sLine[1]), Float.parseFloat(sLine[2])));
                                        break;
                                    }
                                    case "province_growth_rate": {
                                        option.outcome.add(new EventOutcome_Province_GrowthRate(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "province_growth_rate_capital": {
                                        option.outcome.add(new EventOutcome_Province_GrowthRate_Capital(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "province_growth_rate_all": {
                                        option.outcome.add(new EventOutcome_Province_GrowthRate_All(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "province_growth_rate_id": {
                                        option.outcome.add(new EventOutcome_Province_GrowthRate_ID(Integer.parseInt(sLine[1]), Float.parseFloat(sLine[2])));
                                        break;
                                    }
                                    case "province_population": {
                                        option.outcome.add(new EventOutcome_Province_Population(Integer.parseInt(sLine[1])));
                                        break;
                                    }
                                    case "province_population_capital": {
                                        option.outcome.add(new EventOutcome_Province_Population_Capital(Integer.parseInt(sLine[1])));
                                        break;
                                    }
                                    case "province_population_all": {
                                        option.outcome.add(new EventOutcome_Province_Population_All(Integer.parseInt(sLine[1])));
                                        break;
                                    }
                                    case "province_religion": {
                                        option.outcome.add(new EventOutcome_Province_Religion(Integer.parseInt(sLine[1])));
                                        break;
                                    }
                                    case "province_religion_capital": {
                                        option.outcome.add(new EventOutcome_Province_Religion_Capital(Integer.parseInt(sLine[1])));
                                        break;
                                    }
                                    case "province_religion_all": {
                                        option.outcome.add(new EventOutcome_Province_Religion_All(Integer.parseInt(sLine[1])));
                                        break;
                                    }
                                    case "province_religion_id": {
                                        option.outcome.add(new EventOutcome_Province_Religion_ID(Integer.parseInt(sLine[1]), Integer.parseInt(sLine[2])));
                                        break;
                                    }
                                    case "province_devastation": {
                                        option.outcome.add(new EventOutcome_Province_Devastation(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "province_devastation_capital": {
                                        option.outcome.add(new EventOutcome_Province_Devastation_Capital(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "province_devastation_all": {
                                        option.outcome.add(new EventOutcome_Province_Devastation_All(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "province_devastation_id": {
                                        option.outcome.add(new EventOutcome_Province_Devastation_ID(Integer.parseInt(sLine[1]), Float.parseFloat(sLine[2])));
                                        break;
                                    }
                                    case "province_unrest": {
                                        option.outcome.add(new EventOutcome_Province_Unrest(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "province_unrest_capital": {
                                        option.outcome.add(new EventOutcome_Province_Unrest_Capital(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "province_unrest_all": {
                                        option.outcome.add(new EventOutcome_Province_Unrest_All(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "province_unrest_id": {
                                        option.outcome.add(new EventOutcome_Province_Unrest_ID(Integer.parseInt(sLine[1]), Float.parseFloat(sLine[2])));
                                        break;
                                    }
                                    case "province_infrastructure": {
                                        option.outcome.add(new EventOutcome_Province_Infrastructure(Integer.parseInt(sLine[1])));
                                        break;
                                    }
                                    case "province_infrastructure_capital": {
                                        option.outcome.add(new EventOutcome_Province_Infrastructure_Capital(Integer.parseInt(sLine[1])));
                                        break;
                                    }
                                    case "province_infrastructure_all": {
                                        option.outcome.add(new EventOutcome_Province_Infrastructure_All(Integer.parseInt(sLine[1])));
                                        break;
                                    }
                                    case "province_infrastructure_id": {
                                        option.outcome.add(new EventOutcome_Province_Infrastructure_ID(Integer.parseInt(sLine[1]), Integer.parseInt(sLine[2])));
                                        break;
                                    }
                                    case "bonus_duration": {
                                        option.bonus_duration = Integer.parseInt(sLine[1]);
                                        break;
                                    }
                                    case "bonus_monthly_income": {
                                        option.outcome.add(new EventOutcome_BonusMonthlyIncome(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_monthly_legacy": {
                                        option.outcome.add(new EventOutcome_BonusMonthlyLegacy(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_monthly_legacy_percentage": {
                                        option.outcome.add(new EventOutcome_BonusMonthlyLegacyPerc(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_tax_efficiency": {
                                        option.outcome.add(new EventOutcome_BonusTaxEfficiency(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_province_maintenance": {
                                        option.outcome.add(new EventOutcome_BonusProvinceMaintenance(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_buildings_maintenance_cost": {
                                        option.outcome.add(new EventOutcome_BonusBuildingsMaintenanceCost(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_maintenance_cost": {
                                        option.outcome.add(new EventOutcome_BonusMaintenanceCost(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_growth_rate": {
                                        option.outcome.add(new EventOutcome_BonusGrowthRate(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_corruption": {
                                        option.outcome.add(new EventOutcome_BonusCorruption(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_inflation": {
                                        option.outcome.add(new EventOutcome_BonusInflation(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_production_efficiency": {
                                        option.outcome.add(new EventOutcome_BonusProductionEfficiency(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_income_taxation": {
                                        option.outcome.add(new EventOutcome_BonusIncomeTaxation(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_income_economy": {
                                        option.outcome.add(new EventOutcome_BonusIncomeEconomy(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_income_production": {
                                        option.outcome.add(new EventOutcome_BonusIncomeProduction(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_max_manpower": {
                                        option.outcome.add(new EventOutcome_BonusMaxManpower(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_max_manpower_percentage": {
                                        option.outcome.add(new EventOutcome_BonusMaxManpowerPercentage(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_manpower_recovery_speed": {
                                        option.outcome.add(new EventOutcome_BonusManpowerRecoverySpeed(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_reinforcement_speed": {
                                        option.outcome.add(new EventOutcome_BonusReinforcementSpeed(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_army_morale_recovery": {
                                        option.outcome.add(new EventOutcome_BonusArmyMoraleRecovery(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_war_score_cost": {
                                        option.outcome.add(new EventOutcome_BonusWarScoreCost(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_army_maintenance": {
                                        option.outcome.add(new EventOutcome_BonusArmyMaintenance(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_recruitment_time": {
                                        option.outcome.add(new EventOutcome_BonusRecruitmentTime(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_recruit_army_cost": {
                                        option.outcome.add(new EventOutcome_BonusRecruitArmyCost(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_recruit_army_first_line_cost": {
                                        option.outcome.add(new EventOutcome_BonusRecruitArmyFirstLineCost(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_recruit_army_second_line_cost": {
                                        option.outcome.add(new EventOutcome_BonusRecruitArmySecondLineCost(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_research": {
                                        option.outcome.add(new EventOutcome_BonusResearch(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_research_points": {
                                        option.outcome.add(new EventOutcome_BonusResearchPoints(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_construction_cost": {
                                        option.outcome.add(new EventOutcome_BonusConstructionCost(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_administration_buildings_cost": {
                                        option.outcome.add(new EventOutcome_BonusAdministrationBuildingsCost(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_military_buildings_cost": {
                                        option.outcome.add(new EventOutcome_BonusMilitaryBuildingsCost(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_economy_buildings_cost": {
                                        option.outcome.add(new EventOutcome_BonusEconomyBuildingsCost(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_construction_time": {
                                        option.outcome.add(new EventOutcome_BonusConstructionTime(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_invest_in_economy_cost": {
                                        option.outcome.add(new EventOutcome_BonusInvestInEconomyCost(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_maximum_amount_of_gold": {
                                        option.outcome.add(new EventOutcome_BonusMaximumAmountOfGold(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_increase_manpower_cost": {
                                        option.outcome.add(new EventOutcome_BonusIncreaseManpowerCost(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_increase_tax_efficiency_cost": {
                                        option.outcome.add(new EventOutcome_BonusIncreaseTaxEfficiencyCost(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_develop_infrastructure_cost": {
                                        option.outcome.add(new EventOutcome_BonusDevelopInfrastructureCost(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_increase_growth_rate_cost": {
                                        option.outcome.add(new EventOutcome_BonusIncreaseGrowthRateCost(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_generals_attack": {
                                        option.outcome.add(new EventOutcome_BonusGeneralAttack(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_generals_defense": {
                                        option.outcome.add(new EventOutcome_BonusGeneralDefense(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_units_attack": {
                                        option.outcome.add(new EventOutcome_BonusUnitsAttack(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_units_defense": {
                                        option.outcome.add(new EventOutcome_BonusUnitsDefense(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_max_morale": {
                                        option.outcome.add(new EventOutcome_BonusMaxMorale(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_army_movement_speed": {
                                        option.outcome.add(new EventOutcome_BonusArmyMovementSpeed(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_siege_effectiveness": {
                                        option.outcome.add(new EventOutcome_BonusSiegeEffectiveness(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_improve_relations_modifier": {
                                        option.outcome.add(new EventOutcome_BonusImproveRelationsModifier(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_loan_interest": {
                                        option.outcome.add(new EventOutcome_BonusLoanInterest(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_loans_limit": {
                                        option.outcome.add(new EventOutcome_BonusLoansLimit(Integer.parseInt(sLine[1])));
                                        break;
                                    }
                                    case "bonus_aggressive_expansion": {
                                        option.outcome.add(new EventOutcome_BonusAggressiveExpansion(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_income_from_vassals": {
                                        option.outcome.add(new EventOutcome_BonusIncomeFromVassals(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_revolutionary_risk": {
                                        option.outcome.add(new EventOutcome_BonusRevolutionaryRisk(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_core_cost": {
                                        option.outcome.add(new EventOutcome_BonusCoreCost(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_religion_cost": {
                                        option.outcome.add(new EventOutcome_BonusReligionCost(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_advisor_cost": {
                                        option.outcome.add(new EventOutcome_BonusAdvisorCost(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_general_cost": {
                                        option.outcome.add(new EventOutcome_BonusGeneralCost(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_disease_death_rate": {
                                        option.outcome.add(new EventOutcome_BonusDiseaseDeathRate(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_diplomacy_points": {
                                        option.outcome.add(new EventOutcome_BonusDiplomacyPoints(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_battle_width": {
                                        option.outcome.add(new EventOutcome_BonusBattleWidth(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_discipline": {
                                        option.outcome.add(new EventOutcome_BonusDiscipline(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_manpower_recovery_from_disbanded_army": {
                                        option.outcome.add(new EventOutcome_BonusManpowerRecoveryFromADisbandedArmy(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_advisors_max_level": {
                                        option.outcome.add(new EventOutcome_BonusAdvisorMaxLevel(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_all_characters_life_expectancy": {
                                        option.outcome.add(new EventOutcome_BonusAllCharactersLifeExpectancy(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    case "bonus_regiments_limit": {
                                        option.outcome.add(new EventOutcome_BonusRegimentsLimit(Float.parseFloat(sLine[1])));
                                        break;
                                    }
                                    default: {
                                        CFG.LOG(" MISSING IN OPTION -> " + sLine[0] + " *** Line: " + (i + 1));
                                        break;
                                    }
                                }
                            }
                            else {
                                final String s12;
                                final String s6 = s12 = sLine[0];
                                switch (s12) {
                                    case "id": {
                                        nEvent.id = sLine[1];
                                        break;
                                    }
                                    case "title": {
                                        nEvent.title = sLine[1];
                                        break;
                                    }
                                    case "desc": {
                                        nEvent.desc = sLine[1];
                                        break;
                                    }
                                    case "mission_desc": {
                                        nEvent.mission_desc = sLine[1];
                                        break;
                                    }
                                    case "image": {
                                        nEvent.image = sLine[1];
                                        break;
                                    }
                                    case "only_once": {
                                        nEvent.only_once = Boolean.parseBoolean(sLine[1]);
                                        break;
                                    }
                                    case "possible_to_run": {
                                        nEvent.possible_to_run = Boolean.parseBoolean(sLine[1]);
                                        break;
                                    }
                                    case "show_in_missions": {
                                        nEvent.show_in_missions = Boolean.parseBoolean(sLine[1]);
                                        break;
                                    }
                                    case "mission_image": {
                                        nEvent.mission_image = Integer.parseInt(sLine[1]);
                                        break;
                                    }
                                    case "popUp": {
                                        nEvent.popUp = Boolean.parseBoolean(sLine[1]);
                                        break;
                                    }
                                    default: {
                                        CFG.LOG(" MISSING -> " + sLine[0] + " *** Line: " + (i + 1));
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    catch (final Exception exr) {
                        CFG.exceptionStack(exr);
                    }
                }
                if (nEvent.addEvent()) {
                    if (eventsType == 999 || eventsType == 1000) {
                        return nEvent;
                    }
                    if (eventsType == 0) {
                        EventsManager.events.add(nEvent);
                        if (exactDate) {
                            EventsManager.exactDate_Events.add(new Event_ExactDate(EventsManager.events.size() - 1, exactDay, exactMonth, exactYear));
                        }
                    }
                    else if (eventsType == 1) {
                        EventsManager.eventsSiege.add(nEvent);
                    }
                    else if (eventsType == 2) {
                        EventsManager.eventsGlobal.add(nEvent);
                    }
                    else if (eventsType == 3) {
                        EventsManager.eventsScenario.add(nEvent);
                        if (exactDate) {
                            EventsManager.exactDate_EventsScenario.add(new Event_ExactDate(EventsManager.eventsScenario.size() - 1, exactDay, exactMonth, exactYear));
                        }
                    }
                    else if (eventsType == 4) {
                        EventsManager.eventsSiegeScenario.add(nEvent);
                    }
                    else if (eventsType == 5) {
                        EventsManager.eventsGlobalScenario.add(nEvent);
                    }
                }
            }
        }
        catch (final Exception ex4) {
            CFG.exceptionStack(ex4);
        }
        return null;
    }
    
    public static void loadMissionImages() {
        try {
            final FileHandle tempFileT = FileManager.loadFile("game/events/imagesMissions/numOfImages.txt");
            for (int numOfImages = Integer.parseInt(tempFileT.readString()), i = 0; i < numOfImages; ++i) {
                if (FileManager.loadFile("game/events/imagesMissions/" + CFG.getRescouresPath_Short() + i + ".png").exists()) {
                    EventsManager.missionImages.add(new Image(ImageManager.loadTexture("game/events/imagesMissions/" + CFG.getRescouresPath_Short() + i + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
                }
                else {
                    EventsManager.missionImages.add(new Image(ImageManager.loadTexture("game/events/imagesMissions/" + CFG.getRescouresPath_Short_H() + i + ".png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge));
                }
            }
        }
        catch (final Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    
    public static void loadEventIMG(final String sName) {
        if (sName.equals(EventsManager.loadedEventIMG)) {
            return;
        }
        if (EventsManager.eventIMG != null) {
            EventsManager.eventIMG.dispose();
            EventsManager.eventIMG = null;
        }
        try {
            if (FileManager.loadFile("game/events/images/" + CFG.getRescouresPath_Short() + sName).exists()) {
                EventsManager.eventIMG = new Image(ImageManager.loadTexture_RGB888("game/events/images/" + CFG.getRescouresPath_Short() + sName), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
            }
            else {
                EventsManager.eventIMG = new Image(ImageManager.loadTexture_RGB888("game/events/images/" + CFG.getRescouresPath_Short_H() + sName), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
            }
            EventsManager.loadedEventIMG = sName;
        }
        catch (final Exception ex) {
            EventsManager.eventIMG = new Image(ImageManager.loadTexture_RGB888("game/events/images/" + CFG.getRescouresPath_Short() + "default.png"), Texture.TextureFilter.Linear, Texture.TextureWrap.ClampToEdge);
            CFG.exceptionStack(ex);
        }
    }
    
    static {
        EventsManager.eventIMG = null;
        EventsManager.loadedEventIMG = "";
        EventsManager.events = new ArrayList<Event>();
        EventsManager.iEventsSize = 0;
        EventsManager.eventsSiege = new ArrayList<Event>();
        EventsManager.iEventsSiegeSize = 0;
        EventsManager.eventsGlobal = new ArrayList<Event>();
        EventsManager.iEventsGlobalSize = 0;
        EventsManager.eventsScenario = new ArrayList<Event>();
        EventsManager.iEventsScenarioSize = 0;
        EventsManager.eventsSiegeScenario = new ArrayList<Event>();
        EventsManager.iEventsSiegeScenarioSize = 0;
        EventsManager.eventsGlobalScenario = new ArrayList<Event>();
        EventsManager.iEventsGlobalScenarioSize = 0;
        EventsManager.eventsGlobal_Variables = new CivilizationEventsData_Variables();
        EventsManager.missionImages = new ArrayList<Image>();
        EventsManager.runEvent = new ArrayList<String>();
        EventsManager.exactDate_Events = new ArrayList<Event_ExactDate>();
        EventsManager.exactDate_EventsScenario = new ArrayList<Event_ExactDate>();
        EventsManager.runEventGlobalID = 0;
        EventsManager.runEventGlobalID_Scenario = 0;
        EventsManager.loadScenarioEventsTag = "";
    }
    
    public static class Event_ExactDate
    {
        public int eventID;
        public int day;
        public int month;
        public int year;
        
        public Event_ExactDate(final int eventID, final int day, final int month, final int year) {
            this.eventID = eventID;
            this.day = day;
            this.month = month;
            this.year = year;
        }
    }
}
