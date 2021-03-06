package jBot;
import java.lang.reflect.Constructor;		
import java.math.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashSet;
import java.util.Random;

import java.util.Collections;
import java.util.HashMap;
import java.util.Collection;
import java.applet.Applet;
import java.applet.AudioClip;
import bwapi.*;
import bwapi.Position;
import bwapi.TilePosition;
import bwapi.BulletType;
import bwapi.Order;
import bwapi.TechType;
import bwapi.UnitType;
import bwapi.UpgradeType;
import bwapi.Color;
import bwapi.Flag;
import bwem.*;
import bwem.BWEM;
import bwem.Base;
import bwem.area.Area;
import bwem.unit.Geyser;
import bwem.unit.Mineral;
import javafx.util.Pair;
import jfap.*;



public class Jbot extends DefaultBWListener {
	BWClient bwClient;
	Game game;
	
    Jbot() {
    	bwClient = new BWClient(this);
        bwClient.startGame();
    }
	
	private int Workers = 0;
	private int AMPS = 0;
	private int MPS = 0;
	private TilePosition LastBuild;
	private int needs;
	private int GPS = 0;
	private int income = 0;
	private int MaxWorkers = 17;
	private int MaxRacks = 2;
	private int Bases = 0;
	private int MaxBases = 4;
	private int SavingAntiFuck = 0;
	private int Racks = 0;
	private int Academy = 0;
	private int Gases = 0;
	private int MaxGases = 0;
	private Unit scouter;
	private boolean HasScoutUnit;
	private int scoutID = 0;
	private int Factories = 0;
	private int MaxFactories = 1;
	private int StarPorts = 0;
	private int TSF = 0;
	private int Marines = 0;
	private int Tanks = 0;
	private int Bats = 0;
	private int Gol = 0;
	private int MaxGol = 0;
	private int Medics = 0;
	private int walker = 0;
	private int GroundThreat = 0;
	private int AirThreat = 0;
	private int Tick = 0;
	private int AttackTick = 0;
	private int Mines = 0;
	private int Bays = 0;
	private int Turrets = 0;
	private int Armor = 0;
	private int MarinesNeed = 10;
	private int BatsNeed = 4;
	private int MedicsNeed = 4;
	private int AddNeeds = 0;
	private int Vultures = 0;
	private int VulturesMax = 7;
	private int attackNum = 0;
	private boolean buildwait = false;
	private boolean orderRetreat = false;
	private int Bunks = 0;
	private int LastX = 0;
	private int LastY = 0;
	private boolean HasExpanded = false;
	private int APM = 0;
	private int enemyRace = 0;
	private int ArmySize = 0;
	private int forceSupply = 0;
	private int supplyBuilding = 0;
	private int Tech = 0;
	private int DifBuild = 1;
	private int MilUnits = 0;
	private int EnemyUnits = 0;
	private int bwub = 0;
	private boolean attacking = false;
	private boolean expanding = false;
	private boolean hasAttackpos;
	private Position enemyBasePos;
	private Unit Commander;
	private int CommanderID;
	private boolean HasCommander = false;
	private boolean ExpandEnabled = true;
	private Color EColor;
	private TilePosition ExpandPos;
	private boolean isExpanding = false;
	private boolean saving = false;
	private int reserves = 0;
	private int reservesgas = 0;
	private TilePosition lastExpandPos;
	private Position MainChoke;
	private Position Choke;
	int mainChoke = 0;
	private Position BasePos;
	private boolean cheats = true;
	private HashSet currentBuildPlacements = new HashSet();
	private HashSet baseLocations = new HashSet();
	private TilePosition NewBuild;
	private boolean AcademyBuilt = false;
	private boolean AcademyFinshed = false;
	private int buildWaitFix = 0;
	private int ExpansionNumber = 0;
	private UnitType buildingName;
	private Player self;
	private Unit GetBuilder;
	private int attack;
	private int RacksBuilding;
	private boolean ScoutSent;
	private Unit CCBuilder = null;
	private Base BaseLocation;
	private boolean BunkerStarted = false;
	private boolean BunkerFinshed = true;
	private boolean buildingArmor = false;
	private boolean armorFinshed = false;
	private boolean scienceStarted = false;
	private boolean scienceFinished = false;
	private int factorybuilding = 0;
	ArrayList<Integer> repairingBuildings = new ArrayList<Integer>();
	ArrayList<Position> enemyBuildingMemory = new ArrayList<Position>();
	ArrayList<Base> claimedBaseLocations = new ArrayList<Base>();
	ArrayList<Unit> enemyUnits = new ArrayList<Unit>();
	ArrayList<Unit> enemyWorkers = new ArrayList<Unit>();
	ArrayList<Unit> myUnits = new ArrayList<Unit>();
	ArrayList<Unit> enemyRushUnits = new ArrayList<Unit>();
	ArrayList<Unit> enemyBases = new ArrayList<Unit>();
	ArrayList<Mineral> myMinerals = new ArrayList<Mineral>();
	ArrayList<Unit> gasWorkers = new ArrayList<Unit>();
	ArrayList<Unit> availableGas = new ArrayList<Unit>();
	ArrayList<Unit> constructingWorkers = new ArrayList<Unit>();
	ArrayList<UnitType> buildTypes = new ArrayList<UnitType>();
	ArrayList<Unit> myWorkers = new ArrayList<Unit>();
	ArrayList<Unit> myComSats = new ArrayList<Unit>();
	ArrayList<Unit> myMedics = new ArrayList<Unit>();
	ArrayList<Unit> productionBuildings = new ArrayList<Unit>();
	ArrayList<Unit> bunkersFilled = new ArrayList<Unit>();
	ArrayList<Unit> bunkerUnits = new ArrayList<Unit>();
	ArrayList<Unit> scoredBuildings = new ArrayList<Unit>();
	ArrayList<Position> ScanLocations = new ArrayList<Position>();
	ArrayList<Region> myRegions = new ArrayList<Region>();
	ArrayList<Unit> enemyFlyers = new ArrayList<Unit>();
	ArrayList<Unit> enemyBuildings = new ArrayList<Unit>();
	ArrayList<Integer> fapIDs = new ArrayList<Integer>();
	ArrayList<Region> enemyChokes = new ArrayList<Region>();
	ArrayList<UnitType> pBuildings = new ArrayList<UnitType>();
	ArrayList<Pair<Unit, UnitType>> pWorkers = new ArrayList<Pair<Unit, UnitType>>();
	ArrayList<UnitType> pWorkersBuilds = new ArrayList<UnitType>();
	ArrayList<UnitType> pBuildingsBuilt = new ArrayList<UnitType>();
	ArrayList<Integer> pBuildingsBuiltAmount = new ArrayList<Integer>();
	ArrayList<Integer> pAmount = new ArrayList<Integer>();
	ArrayList<TilePosition> pPosition = new ArrayList<TilePosition>();
	ArrayList<Unit> myBunkers = new ArrayList<Unit>();
	ArrayList<Geyser> claimedGas = new ArrayList<Geyser>();
	ArrayList<Unit> enemyAttackers = new ArrayList<Unit>();
	ArrayList<Unit> enemyDefences = new ArrayList<Unit>();
	ArrayList<Unit> retreatingTanks = new ArrayList<Unit>();
	ArrayList<Base> scoutedLocations = new ArrayList<Base>();
	ArrayList<Base> myBases = new ArrayList<Base>();
	HashMap<Integer, Integer> bunkersSize = new HashMap<>();
	ArrayList<UnitType> pUnits = new ArrayList<UnitType>();
	HashMap <Integer, Integer> playerScores = new HashMap<>();
	HashMap<Integer, ArrayList<Position>> playerBuildings = new HashMap<>();
	HashMap<TilePosition, Integer> baseWorkers = new HashMap<>();
	HashMap<TilePosition, Integer> maxWorkers = new HashMap<>();
	HashMap<Unit, TilePosition> tWorkers = new HashMap<>();
	HashMap<Unit, TilePosition> employees = new HashMap<>();
	HashMap<Unit, ArrayList<Unit>> jukers = new HashMap<>();
	HashMap<Unit, Integer> simedUnits = new HashMap<>();
	
	private int buildingTick = 0;
	private int savetries = 0;
	private int enemyRaceBonus = 0;
	private int LastBuildTick = 0;
	private TilePosition LastBunkerPos;
	private int supplyBuildingMax = 1;
	private boolean AlreadyExpanding = false;
	private boolean EnablePrints = false;
	private boolean BayCompleted = false;
	private boolean StealthUnitsDetected = false;
	private boolean SafetoExpand = false;
	private boolean ExpandPlacing = false;
	private Position RegroupPos;
	private int FactoriesBuilding = 0;
	private boolean needsToExpand = false;
	private String Strategy = null;
	private int GameSpeed = 0;
	private int macroBuildings = 0;
	private Position ralleyPoint = null;
	private TilePosition comsatLocation;
	int DifBuildMax = 4;
	Unit macrobuilder;
	boolean buildingMacroBuilding = false;
	boolean simEnabled = true;
	private int detectionNeeded = 2; 
	private int defenceCallFrames = 30;
	boolean defenderCall = true;
	boolean BCTech = false;
	int totalFrames = 0;
	int simCallFrames = 0;
	int enemyFrames = 0;
	int checkBases = 0;
	int PortsBuilding = 0;
	int bunkersBuilding = 0;
	int MarinesNeedBonus = 0;
	String Strats = "Unknown";
	int updateStrategy = 0;
	int fapMyScores = 0;
	int fapEnemyScores = 0;
	int canExpandCheck = 0;
	boolean ValidBasesClaimed = false;
	int expandFrames = 0;
	boolean NextCallToAttack = false;
	int LastExpandFrame = 0;
	boolean startedDet = false;
	boolean finishedDet = true;
	int SupplyDepotsMax = 1;
	int ExpandingBonus = 0;
	int enemyGhostPoints = 0;
	int MaxBunks;
	int numberOfRetreats = 0;
	int lastExpandCheck = 0;
	boolean drawOrderLines = false;
	int searchSiege = 0;
	int Targets = 0;
	int savingUnFreeze = 0;
	boolean siegeResearched = true;
	int SVCheck = 0;
	int lastCheckUnits = 0;
	int fapEnemyHighest = 0;
	boolean sdsddhgfio = false;
	int blindCounts = 0;
	int tempBunks = 0;
	int customTargets = 0;
	int estimatedEnemyScore = 0;
	int lastEst = 0;
	int lureCheck = 0;
	int enemyDefenceScore = 0;
	int AggressiveNess = 200;
	int placementIncrease = 5;
	String focus;
	boolean ForcingExpand = false;
	int checkInvaders = 0;
	int InvadersScore = 0;
	UnitType NextTech = null;
	boolean TeamGameMode = false;
	int TeamModeTargetPlayer = 10;
	boolean DoneCreatingList = false;
	int enemyRushScore = 0;
	boolean tourament = false;
	int scoutTime = 0;
	int lastSimScore = 0;
	int removeUnits = 0;
	int miniFap = 40;
	int moveFap = 0;
	boolean wasAttacking = false;
	boolean scouterFleeing = false;
	static BWEM bewb;
	
	public void onStart() {
		game = bwClient.getGame();
		self = game.self();
		System.out.println(game.mapFileName());
		// Use BWTA to analyze map
		// This may take a few minutes if the map is processed first time!
		bewb = new BWEM(game);
		bewb.initialize();
		bewb.getMap().getData();
		bewb.getMap().assignStartingLocationsToSuitableBases();
		BasePos = self.getStartLocation().toPosition();
		Choke = null;
		game.sendText("1800 Free Elo Ryan speaking how am i be of service?");
		if(game.isMultiplayer() == false){
			game.setLocalSpeed(GameSpeed);
		}
		CustomGreetings(game.enemy().toString());
		if(game.getPlayers().size() > 3){
			TeamGameMode = true;
		}
		
		int numP = game.enemies().size();
	
		if(TeamGameMode == true){
			for(Player players : game.enemies()){
				playerScores.put(players.getID(), 0);
				playerBuildings.put(players.getID(), new ArrayList<Position>());
				//System.out.println(players.getName() + " Id is: " + players.getID());
			}
			DoneCreatingList = true;
			//System.out.println("List building done");
			
		}
		
		if(TeamGameMode == true){
			if(playerScores.isEmpty() == true){
				//System.out.println("Player scores is empty");
			}
			
			if(playerBuildings.isEmpty() == true){
				//System.out.println("Player buildings is empty");
			}
		}
		
		if(TeamGameMode == false){
		Strategy = GetStrategy(game.enemy().getRace().toString());
		}
		// getExpands();

		
		//for(BaseLocation bases : BWTA.getBaseLocations()){
			//if(bases.isStartLocation() == true){
			//System.out.println(bases.getPosition());
			//}
		//}
		//System.out.println(game.mapName());
		//System.out.println(game.mapHash());
	
	}

	public void onUnitCreate(Unit unit) {
		try{
		boolean isMilitray = IsMilitrayUnit(unit);
	

		if(pBuildings.isEmpty() == false){
			if(unit.getType().isBuilding() == true && pBuildings.get(0) == unit.getType() && unit.getPlayer() == self){
				game.sendText("Priority Building Done! " + unit.getType().toString());
				if(!pBuildings.isEmpty()){
					pBuildings.remove(0);
				}
				if(!pWorkers.isEmpty()){
					pWorkers.remove(0);
				}
				if(!buildTypes.isEmpty()){
					buildTypes.remove(unit.getType());
				}
				if(!pPosition.isEmpty()){
					pPosition.remove(0);
				}
				if(pBuildingsBuilt.contains(unit.getType()) == false){
					pBuildingsBuilt.add(unit.getType());
				}

			}
			
		}	
		
		if(unit.getType() == UnitType.Terran_Physics_Lab){
			BCTech = true;
		}
		

		if(unit.getType().isBuilding() == true){
			LastBuildTick = totalFrames + 130;
		}
		
		if(unit.getType() == UnitType.Terran_Starport){
			PortsBuilding = PortsBuilding + 1;
		}

		if (unit.getType() == UnitType.Terran_Bunker && unit.getPlayer() == self) {
			int myID = unit.getID();
			if(bunkersSize.containsValue(myID) == false){
				bunkersSize.put(myID, 0);
			}
			LastBunkerPos = unit.getTilePosition();
			bunkersBuilding = bunkersBuilding + 1;
			tempBunks--;
		}

		if (unit.getType() == UnitType.Terran_SCV && unit.getPlayer() == self) {
			Workers = Workers + 1;
		}

		if (unit.getType() == UnitType.Terran_SCV && HasScoutUnit == false && unit.getPlayer() == self) {
			scouter = unit;
			HasScoutUnit = true;
			scoutID = scouter.getID();
		}

		if (unit.getType() == UnitType.Terran_Command_Center && unit.getPlayer() == self) {
			
			lastExpandPos = unit.getTilePosition();
			Position pos = unit.getPosition();
			Base nearest = getClosestBaseLocation(unit.getPosition());
			ChokePoint nearestc = getClosestChokePoint(unit.getPosition());


			if(Bases > 1){
				LastExpandFrame = totalFrames + 1500;
				MaxRacks = MaxRacks + 2;
				MaxFactories = MaxFactories + 1;
				MaxBunks = 2;
			}
			ExpandingBonus = 0;	
			isExpanding = false;
			expanding = false;
			needsToExpand = false;
			ExpandPlacing = false;
			Bases = Bases + 1;
			buildwait = false;
			saving = false;
			AlreadyExpanding = true;


			if(claimedBaseLocations.contains(nearest) == false){
				claimedBaseLocations.add(nearest);
			}
			
			if(Bases == 2){
			startedDet = false;
			Region baseChoke = game.getRegionAt(nearestc.getCenter().toPosition());
			LastBunkerPos = baseChoke.getCenter().toTilePosition();
			boolean kilo = ShouldMoveRalleyPoint(unit.getPosition());
				if(kilo == true){
					ralleyPoint = baseChoke.getCenter();
				}
			
			}	
			
			for(Region region : game.getAllRegions()){
				if(region.getDistance(unit.getRegion()) < 800 && myRegions.contains(region) == false && region.isAccessible() == true){
					myRegions.add(region);
				}
			}

			
			
		}
		
		if (unit.getType() == UnitType.Terran_Command_Center && unit.getPlayer() == self) {
			Position pos = unit.getPosition();
			Base loc = getClosestBaseLocation(unit.getPosition());
			int code = loc.hashCode();
			TilePosition tile = loc.getLocation();
			//System.out.println("New Code for map is: " + code);
			
			if(maxWorkers.containsKey(tile) == false){
				int newa = loc.getMinerals().size() * 2 + loc.getGeysers().size() * 3;
				maxWorkers.put(tile, newa);
				//System.out.println("MaxWorkers size in total is now: " + maxWorkers.size());
			}
			
			if(baseWorkers.containsKey(tile) == false){
				baseWorkers.put(tile, 0);
				//System.out.println("baseWorkers size in total is now: " + baseWorkers.size());
			}
			
			
		}
		
		

		if (unit.getType() == UnitType.Terran_Command_Center && unit.getPlayer() == self) {
			Position pos = unit.getPosition();
			Base loc = getClosestBaseLocation(unit.getPosition());
			int mSize = loc.getMinerals().size();
			int gSize = loc.getGeysers().size();
			income = income + (int) (mSize * 5);
			MaxGases = MaxGases + gSize;
			for (Mineral minerals : loc.getMinerals()) {
				if (myMinerals.contains(minerals) == false) {
					myMinerals.add(minerals);
					//System.out.println("Adding mineral to claimed mineral's list with size of: " + myMinerals.size());
				}
			}
			

			
			if(myBases.contains(loc) == false){
				myBases.add(loc);
			}
			
			for (Geyser minerals : loc.getGeysers()) {
				if (claimedGas.contains(minerals) == false) {
					claimedGas.add(minerals);
				}
			}
			
			
		}
		
		if (unit.getType() == UnitType.Terran_Barracks && unit.getPlayer() == self) {
			RacksBuilding = RacksBuilding + 1;
			supplyBuildingMax = 2;
			buildwait = false;
			saving = false;
			reserves = reserves - unit.getType().mineralPrice();
		}

		if (unit.getType() == UnitType.Terran_Refinery && unit.getPlayer() == self) {
			Gases = Gases + 1;
			Tech = Tech + 1;
			buildwait = false;
			reserves = reserves - unit.getType().mineralPrice();
		}

		if (unit.getType() == UnitType.Terran_Academy && unit.getPlayer() == self) {
			AcademyBuilt = true;
			MaxRacks = MaxRacks + 1;
			Tech = Tech + 1;
			reserves = reserves - unit.getType().mineralPrice();
			buildwait = false;
			saving = false;
		}

		if (unit.getType() == UnitType.Terran_Armory && unit.getPlayer() == self) {
			reserves = reserves - unit.getType().mineralPrice();
			reserves = reserves - unit.getType().gasPrice();
			buildwait = false;
			saving = false;
		}
		if (unit.getType() == UnitType.Terran_Engineering_Bay && unit.getPlayer() == self) {
			BayCompleted = true;;
			reserves = reserves - unit.getType().mineralPrice();
			buildwait = false;
			saving = false;
		}
		if (unit.getType() == UnitType.Terran_Missile_Turret && unit.getPlayer() == self) {
			Turrets = Turrets + 1;
			startedDet = true;
			buildwait = false;
			saving = false;
		}

		if (unit.getType() == UnitType.Terran_Supply_Depot && unit.getPlayer() == self) {
			supplyBuilding = supplyBuilding + 1;
			reserves = reserves - unit.getType().mineralPrice();
			buildwait = false;
			saving = false;
		}

		if (unit.getType() == UnitType.Terran_Factory && unit.getPlayer() == self) {
			Tech = Tech + 1;
			factorybuilding = factorybuilding - 1;
			reserves = reserves - unit.getType().mineralPrice();
			buildwait = false;
			saving = false;
			FactoriesBuilding = FactoriesBuilding + 1;
		}

		if (unit.getType() == UnitType.Terran_Starport && unit.getPlayer() == self) {
			reserves = reserves - unit.getType().mineralPrice();
			reserves = reserves - unit.getType().gasPrice();
			buildwait = false;
			saving = false;
		}

		if (unit.getType() == UnitType.Terran_Science_Facility && unit.getPlayer() == self) {
			buildwait = false;
			saving = false;
			scienceStarted = true;
		}

		if (unit.getType() == UnitType.Terran_Marine && unit.getPlayer() == self) {
			Marines = Marines + 1;
		}

		if (unit.getType() == UnitType.Terran_Firebat && unit.getPlayer() == self) {
			Bats = Bats + 1;
		}

		if (unit.getType() == UnitType.Terran_Medic && unit.getPlayer() == self) {
			Medics = Medics + 1;
		}

		if (unit.getType() == UnitType.Terran_Vulture && unit.getPlayer() == self) {
			Vultures = Vultures + 1;
		}

		if (unit.getType() == UnitType.Terran_Siege_Tank_Tank_Mode && unit.getPlayer() == self) {
			Tanks = Tanks + 1;
		}

		if (unit.getType() == UnitType.Terran_Medic && unit.getPlayer() == self) {
			Medics = Medics + 1;
		}

		if (unit.getType() == UnitType.Terran_Goliath && unit.getPlayer() == self) {
			
		}
		
		if (unit.getType() == UnitType.Terran_Science_Vessel && unit.getPlayer() == self) {
			TSF = TSF + 1;
		}
		 
		} 
		catch(Exception e){
		 e.printStackTrace();
		}
		

	}

	public void onEnd(boolean isWinner) {
		// A little bit of kung pow doesn't hurt anyone.
		Random rand = new Random();
		int n = rand.nextInt(3) + 1;

		if (isWinner == true && n == 1) {
			game.sendText("GG");

		}
		if (isWinner == true && n == 2) {
			game.sendText("Ha, face to foot style, how'd you like it?");

		}
		if (isWinner == true && n == 3) {
			game.sendText("Oh yeah? Try my nuts to your fist style.");

		}
		if (isWinner == false && n == 1) {
			game.sendText(
					"I must apologize for Wimp Lo, he is an idiot. We have purposely trained him wrong, as a joke");
		}
		if (isWinner == false && n == 2) {
			game.sendText("Please stop, Wimp Lo sucks as a fighter, a child could beat him.");
		}
		if (isWinner == false && n == 3) {
			game.sendText(" I'm bleeding, making me the victor.");
		}

	}

	public void onUnitMorph(Unit unit) {
		if (unit.getType() == UnitType.Terran_Refinery && unit.getPlayer() == self) {
			Gases = Gases + 1;
			buildwait = false;
			saving = false;
		}

	}

	public void onUnitComplete(Unit unit) {
		boolean isMilitray = IsMilitrayUnit(unit);
	
		if(retreatingTanks.contains(unit) == true && ralleyPoint != null){
			unit.move(ralleyPoint);
			retreatingTanks.remove(unit);
		}

		if (unit.getType() == UnitType.Terran_Barracks && unit.getPlayer().isEnemy(self)) {
			GroundThreat = GroundThreat + 2;
			enemyRace = 1;
		}
		
		if (unit.getPlayer().isEnemy(self) && unit.getType() == UnitType.Terran_Bunker || unit.getType() == UnitType.Protoss_Photon_Cannon || unit.getType() == UnitType.Zerg_Sunken_Colony ) {
			if(ScanLocations.contains(unit.getPosition()) == false){
					ScanLocations.add(unit.getPosition());
			}
		}
		
		


		if (unit.getType() == UnitType.Protoss_Gateway && unit.getPlayer().isEnemy(self)) {
			GroundThreat = GroundThreat + 4;


		}

		if (unit.getType() == UnitType.Terran_Factory && unit.getPlayer().isEnemy(self)) {
			GroundThreat = GroundThreat + 2;
			enemyRace = 1;
		}
		
		if (unit.getType() == UnitType.Zerg_Hatchery && unit.getPlayer().isEnemy(self)) {
			GroundThreat = GroundThreat + 2;
		}
		
		
		
		
		if(unit.getType() == UnitType.Terran_Barracks ||
		unit.getType() == UnitType.Terran_Factory ||
		unit.getType() == UnitType.Terran_Starport
		&& productionBuildings.contains(unit) == false){
			productionBuildings.add(unit);
		}
		
		if(isMilitray == true && unit.getType() != UnitType.Terran_Siege_Tank_Tank_Mode){
			if(ralleyPoint != null){
				if(InvadersScore == 0){
				unit.attack(ralleyPoint);
				
				}
			}
			
			if(ralleyPoint == null){
				//Position choke = BWTA.getNearestChokepoint(self.getStartLocation()).getCenter();
				//Region regions = game.getRegionAt(choke).getClosestAccessibleRegion();
				//ralleyPoint = regions.getCenter();
				Position pos = GetBunkerChoke();
				if(pos != null){
				ralleyPoint = pos;
				unit.attack(ralleyPoint);
				}
			}
		}
		
		if(unit.getType().isWorker() == true && myWorkers.contains(unit) == false && unit.getPlayer() == self){
			myWorkers.add(unit);
		}
		
		if(unit.getType() == UnitType.Terran_Comsat_Station && myComSats.contains(unit) == false){
			 myComSats.add(unit);
		}
		
		if(unit.getType() == UnitType.Terran_Medic && myMedics.contains(unit) == false){
			 myMedics.add(unit);
		}
		
		if(unit.getType() == UnitType.Terran_Physics_Lab){
			BCTech = true;
		}
		
		if(unit.getType() == UnitType.Terran_Bunker && unit.getPlayer() == self){
			bunkersBuilding = bunkersBuilding - 1;
		}

		
		if (unit.getPlayer() == self && myUnits.contains(unit) == false && unit.getType().isWorker() == false
				&& unit.getType().isBuilding() == false && unit.getType().isSpell() == false
				&& unit.getType() != UnitType.Terran_Vulture_Spider_Mine) {
			myUnits.add(unit);
			int score = getScoreOf(unit);
			fapMyScores = fapMyScores + score;

		}
		
		if (unit.getType() == UnitType.Terran_Refinery && unit.getPlayer() == self) {
			int local = 1;
			Unit Gas;
			Gas = unit;
			Base loc = getClosestBaseLocation(unit.getPosition());
			TilePosition tile = loc.getLocation();
			int amount = baseWorkers.get(tile);
			TilePosition assigned = employees.get(unit);
			for (Unit myUnits : myWorkers) {
				if (local <= 2) {
					if (myUnits.getType() == UnitType.Terran_SCV && myUnits.isGatheringMinerals() == true
							&& myUnits.getID() != scoutID) {
						myUnits.gather(unit, false);
						gasWorkers.add(myUnits);
						baseWorkers.put(tile, amount + 1);
						if(assigned != tile){
							baseWorkers.put(assigned, amount - 1);
						}
						local = local + 1;
						if (local > 3) {
							break;
						}
					}
				}
			}
		}

		if (unit.canAttack() == true && unit.getType().isWorker() == false && unit.getType().isBuilding() == false) {
			MilUnits = MilUnits + 1;
		}

		if (unit.getType() == UnitType.Terran_Bunker && unit.getPlayer() == self) {
			LastBunkerPos = unit.getTilePosition();
			BunkerStarted = false;
			Bunks = Bunks + 1;
			ralleyPoint = unit.getPosition();
			
			
		}
		
		if (unit.getType() == UnitType.Terran_Command_Center && unit.getPlayer() == self) {
			expanding = false;
			AlreadyExpanding = false;
			ExpandPlacing = false;
			buildwait = false;
			saving = false;
			Base loc = getClosestBaseLocation(unit.getPosition());
			TilePosition tile = loc.getLocation();
			int local = 0;
			if(Bases > 1){
				for (Unit myUnits : myWorkers) {
					if (local <= 6) {
						if (myUnits.getType() == UnitType.Terran_SCV && myUnits.isGatheringMinerals() == true && employees.containsKey(myUnits)) {
							myUnits.move(unit.getPosition());
							int amount = baseWorkers.get(tile);
							int neww = baseWorkers.put(tile, amount + 1);
							local = local + 1;
							if(getClosestBaseLocation(employees.get(myUnits).toPosition()) != loc){
								TilePosition employed = employees.get(myUnits);
								baseWorkers.put(employed, baseWorkers.get(employed) - 1);
								employees.put(myUnits, tile);
							}
						}
					}
					else{
						break;
					}
					if(myWorkers.size() < 3){
						break;
					}
				}
			}
			Position pos = unit.getPosition();
			if(myBases.contains(loc) == false){
				myBases.add(loc);
			}
		}
		
		
	
		
		if (unit.getType() == UnitType.Terran_Supply_Depot) {
			supplyBuilding = supplyBuilding - 1;
		}

		if (unit.getType() == UnitType.Terran_Siege_Tank_Tank_Mode && unit.getPlayer() == self
				&& HasCommander == false) {
			Commander = unit;
			CommanderID = unit.getID();
			HasCommander = true;
		}

		if (unit.getType() == UnitType.Terran_Barracks && unit.getPlayer() == self) {
			RacksBuilding = RacksBuilding - 1;
			Racks = Racks + 1;
		}

		if (unit.getType() == UnitType.Terran_Academy && unit.getPlayer() == self) {
			AcademyFinshed = true;
			int bonus = (estimatedEnemyScore / 100) + (InvadersScore / 25) + 1;
			MarinesNeedBonus = MarinesNeedBonus + bonus;
		}	

		if (unit.getType() == UnitType.Terran_Factory && unit.getPlayer() == self) {
			Factories = Factories + 1;
			FactoriesBuilding = FactoriesBuilding - 1;
			int bonus = (estimatedEnemyScore / 100) + (InvadersScore / 25) + 1;
			MarinesNeedBonus = bonus;
		}
		
		if (unit.getType() == UnitType.Protoss_Gateway && unit.getPlayer() == self) {
			Factories = Factories + 1;
			FactoriesBuilding = FactoriesBuilding - 1;
			MarinesNeedBonus = MarinesNeedBonus + 6;
		}

		if (unit.getType() == UnitType.Terran_Starport && unit.getPlayer() == self) {
			StarPorts = StarPorts + 1;
		}

		if (unit.getType() == UnitType.Terran_Armory && unit.getPlayer() == self) {
			Armor = Armor + 1;
		}

		if (unit.getType() == UnitType.Terran_Science_Facility && unit.getPlayer() == self && scienceFinished == false) {
			scienceFinished = true; 
			MaxFactories = MaxFactories + 1;
		}

		if (unit.getType() == UnitType.Terran_Engineering_Bay && unit.getPlayer() == self) {
			Bays = 1;
		}

	}

	public void onUnitDestroy(Unit unit) {
		try {
		if(enemyWorkers.contains(unit) == true){
			enemyWorkers.remove(unit);
		}
		
		
		if(enemyBuildings.contains(unit) == true){
			enemyBuildings.remove(unit);
		}
		
		
		int p = getGhostScore(unit);
		
		if(unit.getPlayer().isEnemy(self) == true && p != 0 && scoredBuildings.contains(unit) == true ){
			enemyGhostPoints = enemyGhostPoints - p;

		}
		
		if(unit.getPlayer().isEnemy(self) && unit.getType() == UnitType.Terran_Bunker || unit.getType() == UnitType.Protoss_Photon_Cannon || unit.getType() == UnitType.Zerg_Sunken_Colony){
		int k = getScoreOf(unit);
		enemyDefenceScore = enemyDefenceScore - k;
		if(enemyDefences.contains(unit) == true){
			enemyDefences.remove(unit);
		}
		}
		
		if(unit.getType().isResourceDepot() == true && enemyBases.contains(unit) == true){
			enemyBases.remove(unit);
		}
		
		if(enemyRushUnits.contains(unit) == true){
			enemyRushUnits.remove(unit);
			int score = getScoreOf(unit);
			enemyRushScore = enemyRushScore - score;
		}
		
		if(constructingWorkers.contains(unit) == true){
			constructingWorkers.remove(unit);
			saving = false;
			buildwait = false;
			
		}
		
		if(productionBuildings.contains(unit) == true){
			productionBuildings.remove(unit);
		}
		
		
		if(unit.getType().isWorker() == true && myWorkers.contains(unit) == true){
			myWorkers.remove(unit);
		}
		
		if(unit.getType() == UnitType.Terran_Comsat_Station && myComSats.contains(unit) == true){
			 myComSats.remove(unit);
		}
		
		if(unit.getType() == UnitType.Terran_Medic && myMedics.contains(unit) == true){
			 myMedics.remove(unit);
		}
		
		
		
		
		if(repairingBuildings.contains(unit) == true){
			int index = repairingBuildings.indexOf(unit);
			repairingBuildings.remove(index);
		}
		
		
		if(enemyFlyers.contains(unit) == true){
			enemyFlyers.remove(unit);
		}
		
		if (enemyUnits.contains(unit) == true) {
			int index = enemyUnits.indexOf(unit);
			enemyUnits.remove(index);
			int score = getScoreOf(unit);
			estimatedEnemyScore = estimatedEnemyScore - score;
			
			if(TeamGameMode == true){
			int pID = unit.getPlayer().getID();
			int pScore = playerScores.get(pID);
			int newScore = pScore - score;
			playerScores.put(pID, newScore);
			}
			
		}
		

		if (myUnits.contains(unit) == true) {
			int index = myUnits.indexOf(unit);
			myUnits.remove(index);
			int score = getScoreOf(unit);
			fapMyScores = fapMyScores - score;
			// game.sendText("Unit " + unit.getType().toString() + " is now
			// removered from myUnit's list with current size of " +
			// myUnits.size());
		}

		if (unit == CCBuilder) {
			Base nearest = getClosestBaseLocation(unit.getPosition());
			expanding = false;
			saving = false;
			AlreadyExpanding = false;
			ExpandPlacing = false;
			needsToExpand = false;
			if (claimedBaseLocations.contains(nearest) == true) {
				int index = claimedBaseLocations.indexOf(nearest);
				claimedBaseLocations.remove(index);
			}

		}
		
		if(gasWorkers.contains(unit) == true){
			int index = gasWorkers.indexOf(unit);
			gasWorkers.remove(index);
			game.sendText("Removing gas worker at index: " + index );
		}

		if (unit.isConstructing() == true && unit.getPlayer() == self) {
			Unit building = unit.getOrderTarget();
			Unit newbuilder = GetWorker();
			if(newbuilder != null){
				if (newbuilder.exists() == true && building.exists() == true) {
					newbuilder.rightClick(building);
				}
			}
		}

		if (unit.isRepairing() == true && unit.getPlayer() == self) {
			Unit building = unit.getOrderTarget();
			int buildingID = unit.getOrderTarget().getID();
			if (building.getHitPoints() != building.getType().maxHitPoints()
					&& repairingBuildings.contains(buildingID) == true) {
				int index = repairingBuildings.indexOf(buildingID);
				repairingBuildings.remove(index);
			}

		}

		if (unit.getType() == UnitType.Resource_Mineral_Field
				|| unit.getType() == UnitType.Resource_Mineral_Field_Type_2
				|| unit.getType() == UnitType.Resource_Mineral_Field_Type_3) {
			income = income - (int) 5;
			TilePosition tile = getClosestBaseLocation(unit.getPosition()).getLocation();
			if(maxWorkers.containsKey(tile) == true){
				maxWorkers.put(tile, maxWorkers.get(tile) - 2);
			}
		}

		if (unit.getID() == CommanderID) {
			HasCommander = false;
		}


		if (unit.getType() == UnitType.Terran_Marine && unit.getPlayer() == self) {
			Marines = Marines - 1;
		}
		
		if (unit.getType() == UnitType.Terran_Vulture && unit.getPlayer() == self) {
			Vultures = Vultures - 1;
		}
		
		if (unit.getType() == UnitType.Terran_Science_Vessel && unit.getPlayer() == self) {
			TSF = TSF - 1;
		}
		
		if (unit.getType() == UnitType.Terran_Firebat && unit.getPlayer() == self) {
			Bats = Bats - 1;
		}

		if (unit.getType().isResourceDepot() && unit.getPlayer().equals(self)) {
			Position pos = unit.getPosition();
			Base loc = getClosestBaseLocation(unit.getPosition());
			TilePosition tile = loc.getLocation();
			Bases = Bases - 1;	
			income = income - (int) (loc.getMinerals().size() * 5);
			MaxGases = MaxGases - loc.getGeysers().size();
			if (claimedBaseLocations.contains(loc) == true) {
				int index = claimedBaseLocations.indexOf(loc);
				claimedBaseLocations.remove(index);
				for (Mineral unit1 : loc.getMinerals()) {
					if (myMinerals.contains(unit1) == true) {
						int index1 = myMinerals.indexOf(unit1);
						myMinerals.remove(index1);
					}

				}

			}
			
			if(maxWorkers.containsKey(tile) == true) {
				maxWorkers.remove(tile);
			}
			
			if(baseWorkers.containsKey(tile) == true) {
				maxWorkers.remove(tile);
			}
			
			if(myBases.contains(loc) == true){
				myBases.remove(loc);
			}
			
			if(myWorkers.isEmpty() == false){
				for(Unit units : myWorkers){
					if(employees.containsKey(units) == true){
						if(employees.containsValue(tile) == true){
							employees.remove(units);
						}
					}
				}
			}

		}
		
		
		if (unit.getType() == UnitType.Terran_Command_Center && unit.getPlayer() == self) {
			Position pos = unit.getPosition();
			Base nearest = getClosestBaseLocation(pos);
			Base location = getClosestBaseLocation(pos);
			int index = claimedBaseLocations.indexOf(nearest);
			if (index != 0) {
				claimedBaseLocations.remove(index);
			}

		}

		if (unit.getType() == UnitType.Terran_SCV && unit.getPlayer() == self && unit.getID() == scoutID) {
			HasScoutUnit = false;

		}
		
		if (unit.getType() == UnitType.Terran_Medic && unit.getPlayer() == self) {
			Medics = Medics - 1;
		}

		if (unit.getType() == UnitType.Terran_Barracks && unit.getPlayer() == self) {
			Racks = Racks - 1;
		}

		if (unit.getType() == UnitType.Terran_Academy && unit.getPlayer() == self) {
			Academy = 0;
		}

		if (unit.getType() == UnitType.Terran_Factory && unit.getPlayer() == self) {
			Factories = Factories - 1;
		}

		
		if (unit.getType() == UnitType.Terran_SCV && unit.getPlayer() == self) {
			Workers = Workers - 1;
			
			if(employees.containsKey(unit) == true){
			TilePosition tile = employees.get(unit);
			
			if(baseWorkers.containsKey(tile) == true){
				baseWorkers.put(tile, baseWorkers.get(tile) - 1);
			}
			
			if(employees.containsKey(unit) == true){
				employees.remove(unit);
			}
			
			
			}
			
			
		}
		
		

		if (unit.getType() == UnitType.Terran_SCV && unit.isGatheringGas() == true
				|| unit.isCarryingGas() == true && unit.getPlayer() == self) {
			Unit gas = unit.getTarget();
			Unit myUnit = GetWorker();
			if (gas.exists() == true && myUnit.exists() == true) {
				myUnit.gather(gas);
			}

		}

		if (unit.getType() == UnitType.Terran_SCV && unit.isConstructing() == true) {
			Unit target = unit.getTarget();
			if(target != null){
				if (target.getType() == UnitType.Terran_Command_Center) {
					expanding = false;
					saving = false;
				}
			}
		}
		
		if (unit.getType() == UnitType.Terran_Goliath && unit.getPlayer() == self) {
			Gol = Gol - 1;
		}
		
		if (unit.getType() == UnitType.Terran_Bunker && unit.getPlayer() == self) {
			Bunks = Bunks - 1;
			if(myBunkers.contains(unit) == true){
				int index = myBunkers.indexOf(unit);
				myBunkers.remove(unit);
				bunkersSize.remove(index);
			}
		
		}
		
		
		} 
		catch(Exception e){
			e.printStackTrace();
		}

	}

	public void onUnitDiscover(Unit unit) {
		 try {
		boolean ismil = IsMilitrayUnit(unit);
		//System.out.println("Is hostile? " + unit.getPlayer().isEnemy(self));
		//System.out.println("Is Mil" + ismil + " Unit: " + unit.getType().toString());
		Base nearest = getClosestBaseLocation(unit.getPosition());
		
		if(unit.getPlayer().isEnemy(self) == true && unit.getType().isWorker() == true && enemyWorkers.contains(unit) == false){
			enemyWorkers.add(unit);
		}
		
		if(unit.getPlayer().isEnemy(self) == true && unit.getType().isBuilding() == true && enemyBuildings.contains(unit) == false){
			int p = getScoreOf(unit);
			enemyBuildings.add(unit);
			CheckBuilding(unit);
			String str = CheckOpener(unit.getPlayer());
			if(unit.getPlayer() != self && unit.getType() == UnitType.Terran_Bunker || unit.getType() == UnitType.Protoss_Photon_Cannon || unit.getType() == UnitType.Zerg_Sunken_Colony){
			enemyDefenceScore = enemyDefenceScore + p;;
			if(enemyDefences.contains(unit) == false){
				enemyDefences.add(unit);
			}
			}
		
			if(unit.getType().isResourceDepot() == true && claimedBaseLocations.contains(nearest) == false){
				claimedBaseLocations.add(nearest);
			}
		}
		
		
		int p = getGhostScore(unit);
		
		if(unit.getPlayer() != self && p != 0 && scoredBuildings.contains(unit) == false){
			enemyGhostPoints = enemyGhostPoints + p;
			scoredBuildings.add(unit);
		}
		
		
		
		if(unit.getType().isResourceDepot() == true && enemyBases.contains(unit) == false && unit.getPlayer().isEnemy(self)){
			enemyBases.add(unit);
			Position pos = unit.getPosition();
			Base base = getClosestBaseLocation(pos);
			ChokePoint choke = getClosestChokePoint(pos);
			Region cRegion = game.getRegionAt(pos);
			if(enemyChokes.contains(cRegion) == false){
				enemyChokes.add(cRegion);
			}
			for(Region region : cRegion.getNeighbors()){
				if(region.isAccessible() == true && enemyChokes.contains(region) == false){
					enemyChokes.add(region);
				}
			}


		}
		
		
		if(unit.getPlayer() == game.enemy() && ismil == true && enemyRushUnits.contains(unit) == false && totalFrames < 9500){
			enemyRushUnits.add(unit);
			int score = getScoreOf(unit);
			enemyRushScore = enemyRushScore + score;
		}

		if (unit.getPlayer().isEnemy(self) == true && enemyUnits.contains(unit) == false && ismil == true && unit.getType().isBuilding() == false) {
			enemyUnits.add(unit);
			int score = getScoreOf(unit);
			if(TeamGameMode == true){
			int pID = unit.getPlayer().getID();
			int pScore = playerScores.get(pID);
			int newScore = pScore + score;
			playerScores.put(pID, newScore);
			}
			estimatedEnemyScore = estimatedEnemyScore + score;
			
		}
		
		
		if(unit.getType().isFlyer() == true && unit.getPlayer() == game.enemy() && unit.getType().isBuilding() == false && unit.canAttack() == true && unit.getType() != UnitType.Protoss_Interceptor && unit.getType().groundWeapon().damageAmount() > 0){
			AirThreat = AirThreat + 1;
		}


		if (unit.getType() == UnitType.Protoss_Gateway && unit.getPlayer().isEnemy(self)) {
			enemyRace = 2;
		}


		if (unit.getType() == UnitType.Terran_Command_Center && unit.getPlayer().isEnemy(self)) {
			hasAttackpos = true;
			enemyRace = 1;
		}
		if (unit.getType() == UnitType.Protoss_Nexus && unit.getPlayer().isEnemy(self)) {
			hasAttackpos = true;;
			enemyRace = 2;
		}
		
	} 
	catch(Exception e){
	 e.printStackTrace();
	}
		
	
	}
	
	
	public void onNukeDetect(Position target){
		updateStrategy = 0;
		// TFW YOUR BOT TRIES TO REGROUP NICELY FOR THE NUKE
		for(Unit units: getUnitsInRadius(target, 500)){
				if(units.getPlayer() == self){
					Position regroup = RunFromPos(units, target);
						if(regroup != null){
								if(regroup.isValid(game) == true && units.isMoving() == false){
									units.move(regroup);
								}
						}
				}
		}
		
		
		

	}
	
	
	public void onUnitShow(Unit unit){
		try {
		if(estimatedEnemyScore > fapMyScores){
			UpdateStrats();
		}
		
		if(unit.getPlayer().isEnemy(self) && IsMilitrayUnit(unit) == true){	
			bwapi.Region rego = game.getRegionAt(unit.getOrderTargetPosition());
		
			if(myRegions.contains(rego) == true){
		        //System.out.println("Attacker Detected region");
			}
			else {
				//System.out.println("Not in one of my regions");
			}
			
			if(unit.getOrderTargetPosition().getApproxDistance(self.getStartLocation().toPosition()) < 2000){
				//System.out.println("Attacker Detected distance");
				InvadersScore = InvadersScore + getScoreOf(unit);
				//game.sendText("!");
				if(HowManyDoIHave(UnitType.Terran_Bunker) == 0 && pBuildings.contains(UnitType.Terran_Bunker) == false){
					pBuildings.add(UnitType.Terran_Bunker);
					game.sendText("Enemy Attack incoming, building bunker!");
					if(ralleyPoint != null){
					pPosition.add(ralleyPoint.toTilePosition());
					}
					else {	
						Position pos = GetBunkerChoke();
						if(pos != null){
							pPosition.add(pos.toTilePosition());
						}
					}
				}
				
			}
			
			if(totalFrames < 9000){
				if(fapMyScores < InvadersScore){
					UnitType type = GetRecommendedCounter(unit.getType());
						int amount = Math.round(getScoreOf(unit) / 55);
						if(pUnits.size() < amount * 2){
							for (int i = 0; i < amount; i++){
								if(type != null){
									pUnits.add(UnitType.Terran_Firebat);
								}
								else {
									pUnits.add(UnitType.Terran_Marine);
								}
							}
						}
						//System.out.println("Enemy Attack Detected, pUniting: " + amount + " Units");
					}
			}
		}
		
		if(unit.getType() == UnitType.Protoss_Dark_Templar && HowManyDoIHave(UnitType.Terran_Missile_Turret) == 0 && pBuildings.contains(UnitType.Terran_Missile_Turret) == false){
			pBuildings.add(UnitType.Terran_Missile_Turret);
			pPosition.add(ralleyPoint.toTilePosition());
			pBuildings.add(UnitType.Terran_Missile_Turret);
			pPosition.add(self.getStartLocation());
		}
		
		if(unit.getType() == UnitType.Zerg_Lurker && HowManyDoIHave(UnitType.Terran_Missile_Turret) == 0 && pBuildings.contains(UnitType.Terran_Missile_Turret) == false){
			pBuildings.add(UnitType.Terran_Missile_Turret);
			pPosition.add(ralleyPoint.toTilePosition());
			pBuildings.add(UnitType.Terran_Missile_Turret);
			pPosition.add(self.getStartLocation());
		}
		
		} catch(Exception e){
			e.printStackTrace();
		}
		//end of onUnitShow
		
	}
	

	@Override
	public void onFrame() {
		AMPS = (int) (Workers * 2);
		MPS = (int) (AMPS * 0.14);
		GPS = Gases * 3;
		MaxGol = 3 + (enemyFlyers.size() * 2);
		MaxWorkers = 2 + (myMinerals.size() * 1) + (MaxGases * 3);
		VulturesMax = (Tanks * 2) + 6;
		MarinesNeed = 10 + (Bunks * 4) + (Tanks * 5) + MarinesNeedBonus + (InvadersScore / 25) + (enemyRushScore / 50) + enemyGhostPoints / 50;
		MedicsNeed = Math.round(Marines / 4) + 2;
		BatsNeed = Math.round(game.enemy().allUnitCount(UnitType.Protoss_Zealot) / 2) + Math.round(game.enemy().allUnitCount(UnitType.Zerg_Zergling) / 6) + 3;
		Tick = Tick + 1;
		buildingTick = buildingTick + 1;
		AddNeeds = AddNeeds + 1;
		needs = (Factories * 20) + (Racks * 20);
		APM = game.getAPM(true);
		game.enableFlag(Flag.UserInput);
		// game.setTextSize(10);`
		game.drawTextScreen(10, 10, "Playing as " + self.getName() + " - " + self.getRace() + " And with.. " + APM
				+ " APM" + " With.." + game.getAverageFPS() + "FPS" + " Total Frames: " + totalFrames);
		game.drawTextScreen(10, 20, "I have " + Bases + " Bases and i have " + Factories + " Factories"
				+ " and i have: " + Racks + " Barracks" + " And i have.. " + " " + myUnits.size() + "Militray units.");
		game.drawTextScreen(10, 30, enemyFlyers.size() + " Early game threat: " + enemyRushScore);
		game.drawTextScreen(10, 40, "EnemyUnits: " + enemyUnits.size() + " My Fap Score: " + fapMyScores + " Enemy Production Score: " + enemyGhostPoints + " Estimated Enemy Score: " + estimatedEnemyScore + " Enemy Defence Score: " + enemyDefenceScore);
		game.drawTextScreen(10, 50, "income: " + income + " " + " needs: " + needs   + " Am i currently saving for an expansion: " + needsToExpand + " Am i currently expanding: " + expanding);
		//game.drawTextScreen(10, 30, "Versing " + game.enemy().getName() + " as "  + game.enemy().getRace() + " ");
		game.drawTextScreen(10, 60, "Strategy: " + Strats + " " + " Frames: " + updateStrategy);
		//game.drawTextScreen(10, 80, "Invaders: " + sScore);
		//game.drawTextScreen(10, 90, "Early Marine Amount: " + MarinesNeed);
		
//		if (game.getFrameCount() % game.getLatencyFrames() != 0) {
			
			
		StringBuilder sPlayerScores = new StringBuilder("Enemy Player Scores:\n");
		StringBuilder sPlayerSize = new StringBuilder("Enemy Player Building Size:\n");
//		
		if(DoneCreatingList == true && playerScores.isEmpty() == false && playerBuildings.isEmpty() == false){
			for (Player p123 : game.enemies()){
				int pID = p123.getID();
				int pScore = playerScores.get(pID);
				int pBuildSize = playerBuildings.get(pID).size();
				sPlayerScores.append(p123.getName()).append(" ").append(pScore).append("\n");
				sPlayerSize.append(p123.getName()).append(" ").append(pBuildSize).append("\n");
				}
		}
			

			totalFrames = totalFrames + 1;
			updateStrategy = updateStrategy + 1;
			canExpandCheck = canExpandCheck + 1;
			checkInvaders++;
			
			
			if (supplyBuilding < 0) {
				supplyBuilding = 0;
			}
			
			
			if (lureCheck != 0){
				lureCheck++;
			}
			
			if(scoutTime != 0){
				scoutTime++;
			}
			
			if(scoutTime >= 15){
				scoutTime = 0;
			}

			if(checkInvaders > 20){
				CheckInvaders();
				checkInvaders = 0;
			}
			
			if(lureCheck > 30){
				lureCheck = 0;
			}
			
			if (attacking == true) {
				Mines = Mines + 1;
			}
		
			if(lastExpandCheck != 0){
				lastExpandCheck = lastExpandCheck + 1; 
			}
			
			if(SVCheck != 0){
				SVCheck = SVCheck + 1;
			}
			
			if(SVCheck == 70){
				SVCheck = 0;
			}
			
			if(customTargets == 25){
				customTargets = 0;
			}
			
			if(customTargets > 0){
				customTargets++;
			}
			
			if(lastExpandCheck > 250){
				lastExpandCheck = 0;
			}
			
			if(savingUnFreeze != 0){
				savingUnFreeze = savingUnFreeze + 1; 
			}
			
			if(savingUnFreeze > 600){
				savingUnFreeze = 0;
			}
			
			if(self.minerals() > 700 && savingUnFreeze == 0 && saving == true && expanding == false){
				saving = false;
				game.sendText("Saving for something that doesnt exist? NOPE.");
				savingUnFreeze = 1;
			}
			
			if(totalFrames < 10000){
				
				MaxBunks = (enemyRushScore / 300) + 1;
				if(MaxBunks > 1){
					MaxBunks = 1;
				}
				
			}
			
			if(expanding == true){
				expandFrames = expandFrames + 1;
			}
			
			if(scienceFinished == true && scienceStarted == false){
				scienceStarted = true;
			}
			
			if (buildingTick > 30) {
				buildingTick = 0;
			}
			
			if(saving == true && LastBuildTick == 0){
				saving = false;
			}
			
			if(self.supplyUsed() > 7 && ScoutSent == false){
				ScoutSent = true;
			}

//			if(saving == true && self.minerals() > 2000 && self.gas() > 500){
//				saving = false;
//				buildwait = false;
//			}


			// t = 1
			// p = 2
			// z = 3

			if (enemyRace > 0 && enemyRaceBonus == 0) {
				if (enemyRace == 1) {
					enemyRaceBonus = (int) 0.5;
				}

				if (enemyRace == 2) {
					enemyRaceBonus = (int) 0.5;
				}
				if (enemyRace == 3) {
					enemyRaceBonus = (int) 0.5;
				}
			}
				

			if (scouter.isVisible() == true) {
				Position pos = scouter.getPosition();
				game.drawCircleMap(pos, 25, bwapi.Color.Purple, false);
			}

			if (expanding == true && CCBuilder.exists() == true) {
				Position pos = CCBuilder.getPosition();
				game.drawCircleMap(pos, 25, bwapi.Color.Yellow, false);
			}

			if (income < needs && ExpandEnabled == true && LastExpandFrame < totalFrames && Bases != 5 && needsToExpand == false){
				boolean canDoOfThat = CanExpand();
				if(canDoOfThat = true){
					needsToExpand = true;
				}
			}
			
			if(RegroupPos != null){
				game.drawCircleMap(RegroupPos, 35, bwapi.Color.Red, true);
			}
			
			if(defenderCall == false){
				defenceCallFrames = defenceCallFrames + 1;
			}
			
			if(defenceCallFrames >= 30){
				defenceCallFrames = 0;
				defenderCall = true;
			}
			
			if(simCallFrames != 0){
				simCallFrames = simCallFrames + 1;
			}
			
			if(simCallFrames > 30){
				simCallFrames = 0;
			}
			
			if(enemyFrames > 30){
				enemyFrames = 1;
			}
			
			
			if(searchSiege > 0){
				searchSiege = searchSiege + 1;
			}
			
			if(searchSiege == 30){
				searchSiege = 0;
			}
			
			if(Targets > 0){
				Targets = Targets + 1;
			}
			
			if(Targets == 30){
				Targets = 0;
			}
			
			if(enemyFrames != 0){
				enemyFrames = enemyFrames + 1;
			}
			
			if(enemyFrames > 30){
				enemyFrames = 0;
			}

			if(checkBases != 0){
				checkBases = checkBases + 1;
				
			}
			
			if(checkBases > 100){
				checkBases = 0;
			}
			
			if(updateStrategy >= AggressiveNess){
				UpdateStrats();
				updateStrategy = 0;
			}
			
			if(moveFap > 0){
				moveFap++;
			}
			
			if(moveFap > 15){
				moveFap=0;
			}
			
			if(totalFrames > 8000 && enemyRushScore != 0){
				enemyRushScore = 0;
			}
			
			if(needsToExpand == true && saving == false && CanExpand() == true){
				saving = true;
			}
			
			
			if (saving == true && self.minerals() >= 700 && self.gas() >= 300){
				if(pUnits.isEmpty() == true){
					for (Unit buildings : productionBuildings) {
						// best way to get the bot to build after its build
						// frozen is probably max the supply and let it spam.
						
						if (buildings.getType() == UnitType.Terran_Factory && self.minerals() >= 600
								&& self.gas() >= 300 && buildings.isIdle() == true) {
							buildings.train(UnitType.Terran_Siege_Tank_Tank_Mode);
						}
						if (buildings.getType() == UnitType.Terran_Barracks && self.minerals() >= 700
								 && AcademyBuilt == true && buildings.isIdle() == true) {
									buildings.train(UnitType.Terran_Marine);
						}
	
						if (buildings.getType() == UnitType.Terran_Starport && game.canMake(UnitType.Terran_Battlecruiser) == true && self.minerals() >= 700 && self.gas() >= 300 && buildings.isIdle() == true) {
							buildings.train(UnitType.Terran_Battlecruiser);
					}
	
					}
				}
				else{
					UnitType type = pUnits.get(0);
					for (Unit buildings : productionBuildings) {
						if(game.canMake(type, buildings) == true){
							buildings.train(type);
							game.sendText("Training Priority Unit: " + type.toString());
							pUnits.remove(0);
						}
	
					}
				}

			}
			
//			if(attacking == false && myUnits.isEmpty() == false && lureCheck == 0){
//				lureCheck = 1;
//					for(Unit unit : myUnits){
//						if(unit.getOrder() == Order.AttackUnit || unit.getOrder() == Order.AttackMove || unit.isAttacking() == true){
//							Unit bunker = GetNearestBunker(unit.getPosition());
//							if(bunker != null){
//								if(unit.getDistance(bunker.getPosition()) > 600 && myRegions.contains(unit.getRegion()) == true){
//									Unit target = unit.getOrderTarget();
//									if(target == null){
//										for(Unit unit1 : game.getUnitsInRadius(unit.getOrderTargetPosition(), 70)){
//											if(unit1.getPlayer().isEnemy(self) == true){
//												target = unit1;
//												break;
//											}
//										}
//									}
//										if(target != null){
//											ArrayList<Unit> ass1 = GetMyUnitsNearby(unit.getPosition(), 250, true);
//											ArrayList<Unit> ass2 = GetEnemyUnitsNearby(target.getPosition(), 250, true);
//											boolean canWin = jFaplocal(ass1, ass2, 1);
//											if(canWin == false){
//											int bonus = 0;
//											if(self.getUpgradeLevel(UpgradeType.U_238_Shells) > 0){
//												bonus = 24;
//											}
//										if(GetAverageRange(target.getPosition()) <= UnitType.Terran_Marine.groundWeapon().maxRange() + bonus) {
//												if(BunkerCanAttackAnything(bunker) == false){
//													Position runTo = GetRegionBehindBunker(bunker, target).getCenter();
//													if(runTo != null){
//														unit.move(runTo);
//														game.drawCircleMap(runTo, 30, bwapi.Color.Red);
//													}
//												}
//											}
//											break;
//										}
//										break;
//										}
//									}
//							}
//						}
//					}
//				}
			
		
			for(Base bases : bewb.getMap().getBases()){
				
				if(game.isVisible(bases.getLocation()) == true 
				&& game.canBuildHere(bases.getLocation(), UnitType.Terran_Command_Center) == true
				&& claimedBaseLocations.contains(bases) == true && bases.getLocation() != self.getStartLocation() && expanding == false){
					claimedBaseLocations.remove(bases);
					game.sendText("OIL DETECTED AT " + bases.getLocation() + " Deploying US forces");
		
				}
				if(scoutedLocations.isEmpty() == false){
					if(game.isVisible(bases.getLocation())== true && scoutedLocations.contains(bases) == true){
						scoutedLocations.remove(bases);
					}
				}
				
				
				TilePosition tile = bases.getLocation();
				if(baseWorkers.containsKey(tile) == true && maxWorkers.containsKey(tile) == true){
					int max = 0;
					int workers = baseWorkers.get(tile);
					max = maxWorkers.get(tile);
					game.drawTextMap(bases.getCenter(), "Workers: " + workers + " Max Workers: " + max);
				}
				
				
//				

			}
			

			
			if(enemyChokes.isEmpty() == false){
				for(Region region : enemyChokes){
					game.drawCircleMap(region.getCenter(), 20, bwapi.Color.White);
				}
			}

			if (Mines > 300) {
				Mines = 0;
			}

			if (DifBuild > DifBuildMax) {
				DifBuild = 1;
			}
			

			if (isExpanding == true) {
				game.drawCircleMap(ExpandPos.toPosition(), 25, bwapi.Color.Cyan, false);
			}

			if (HowManyDoIHave(UnitType.Terran_Academy) == 0) {
				DifBuild = 1;
			}

			if (buildwait == true && bwub < 1) {
				bwub = Tick + 90;

			}

			if (Tick > bwub) {
				bwub = 0;
			}
			
				
			if (totalFrames > LastBuildTick && saving == true && LastBuildTick != 0) {
				saving = false;
				LastBuildTick = 0;
			}
			
			if(buildingMacroBuilding == true)
				if(macrobuilder.isConstructing() == false){
				saving = false;
				macrobuilder = null;
				buildingMacroBuilding = false;
			}
			
			if(buildingMacroBuilding == true){
				if(macrobuilder != null){
					game.drawCircleMap(macrobuilder.getPosition(), 10, bwapi.Color.Yellow);
					game.drawTextMap(macrobuilder.getPosition(), "Communist Sympathiser");
					
				}
			}
			
			
			if(HowManyOrderedToBuild(UnitType.Terran_Supply_Depot) == 0 && supplyBuilding > 0){
				supplyBuilding = 0;
			}
			
			if (self.minerals() >= 400 && ExpandEnabled == true && needsToExpand == true && expanding == false && Bases <= MaxBases && totalFrames > LastExpandFrame && CanExpand() == true) {
				game.sendText("Expanding");
				ExpandingBonus = 400;
				expanding = true;
				saving = true;
				Unit myUnit = GetWorker();
				CCBuilder = myUnit;
				TilePosition buildTile = NextExpand(CCBuilder);
				if (buildTile != null && myUnit != null) {
					CCBuilder.move(buildTile.toPosition(), false);

				}
				
				if(buildTile == null){
					expanding = false;
				}
			
		}
			if (expanding == true && ExpandPos.isValid(game) == true
					&& game.canBuildHere(ExpandPos, UnitType.Terran_Command_Center)) {
				if (game.isVisible(ExpandPos) == true && CCBuilder.isConstructing() == false) {
					CCBuilder.build(UnitType.Terran_Command_Center, ExpandPos);
				} else if (CCBuilder.isMoving() == false || CCBuilder.isGatheringMinerals() == true) {
					CCBuilder.move(ExpandPos.toPosition());
				}

			}

			if (expanding == true && game.isVisible(ExpandPos) == true && self.minerals() > 400) {
				if (game.canBuildHere(ExpandPos, UnitType.Terran_Command_Center) == false
					&& CCBuilder.isConstructing() == false){
					expanding = false;
					ExpandPlacing = false;
					saving = false;
					buildwait = false;
					//game.printf("Can't place expand at " + ExpandPos + " Making sure we dont try that ever again.");
				}
			}

			if (expanding == true && CCBuilder.exists() == true
					&& CCBuilder.hasPath(ExpandPos.toPosition()) == false) {
				game.sendText("Unable to path to expand location");
				expanding = false;
				ExpandPlacing = false;
				saving = false;
				buildwait = false;
			}
			// new code
			// building
		if(pBuildings.isEmpty() == true){
			if (saving == false && expanding == false && buildingTick == 20) {
				buildingTick = 0;
				
				if (self.minerals() >= 100 && supplyBuilding <= SupplyDepotsMax && saving == false && self.supplyUsed() != 200 && (self.supplyTotal() - self.supplyUsed()) < 5) {
					boolean killing = false;
					 SavingAntiFuck = Tick + 200;
					 buildWaitFix = buildWaitFix + 200;
					Unit myUnit = GetWorker();
					if(myUnit == null){
						saving = false;
						//game.sendText("FBI tracer detected, deleteing webcam drivers and running incognito mode");
						killing = true;
						
					}
					if(killing == false){
					TilePosition buildTile = getBuildTile(myUnit, UnitType.Terran_Supply_Depot, myUnit.getTilePosition());
					if (buildTile != null && buildTile != null && myUnit.exists() == true) {
						saving = true;
						if(game.canBuildHere(buildTile, UnitType.Terran_Supply_Depot) == false){
							//game.sendText("Oh hey now your a null star, get the fuck out. Don't crash meee");
						}
						else if(buildTile != null){
						myUnit.build(UnitType.Terran_Supply_Depot, buildTile);
						buildTypes.add(UnitType.Terran_Supply_Depot);
						if(constructingWorkers.contains(myUnit) == false){
						constructingWorkers.add(myUnit);
						}
						game.drawCircleMap(buildTile.toPosition(), 10, bwapi.Color.Yellow, false);
						if(LastBuildTick == 0){
						LastBuildTick = totalFrames + 100;
						}
						}
						
					}
					else {
						saving = false;
					}
				}
			}
					

				if (game.canMake(UnitType.Terran_Bunker) == true && (Bunks + bunkersBuilding) <=  MaxBunks - 1 && saving == false  && bunkersBuilding < MaxBunks) {
					TilePosition bunkertile = null;
					Unit myUnit = GetWorker();	
					placementIncrease = 5;
					if(Bunks == 0 || bunkersBuilding == 0){
						if(ralleyPoint == null){
							bunkertile = GetBunkerChoke().toTilePosition();
						}
						else {
							bunkertile = ralleyPoint.toTilePosition();
						}
						
					}
					else{
						bunkertile = LastBunkerPos;
					}
					if(myUnit != null){
					//TilePosition DefenderableTile = getDefendableTile(myUnit, UnitType.Terran_Bunker, bunkertile);
					TilePosition DefenderableTile = game.getBuildLocation(UnitType.Terran_Bunker, bunkertile, 1 + placementIncrease);
					if (DefenderableTile != null && myUnit != null) {
						saving = true;
						myUnit.build(UnitType.Terran_Bunker, DefenderableTile);
						buildTypes.add(UnitType.Terran_Bunker);
						tempBunks++;
						if(Bunks > 1){
							Conscript();
						}
						if(constructingWorkers.contains(myUnit) == false){
						constructingWorkers.add(myUnit);
						}

						if(LastBuildTick == 0){
						LastBuildTick = totalFrames + 100;
						}
					}
					else {
						saving = false;
						placementIncrease = placementIncrease + 2;
					}

				}
			}

				if (self.minerals() >= 100 && Gases < MaxGases && isExpanding == false && Racks > 0) {
					Unit myUnit = GetWorker();
					TilePosition buildTile = getBuildTile(myUnit, UnitType.Terran_Refinery, self.getStartLocation());
					if (buildTile != null && myUnit != null) {
						saving = true;
						if(constructingWorkers.contains(myUnit) == false){
						constructingWorkers.add(myUnit);
						}
						myUnit.build(UnitType.Terran_Refinery, buildTile);
						buildTypes.add(UnitType.Terran_Refinery);
						if(LastBuildTick == 0){
							LastBuildTick = totalFrames + 100;
						}
					}
				}
				
				if (Racks + RacksBuilding <= MaxRacks - 1 && self.minerals() >= (150 + ExpandingBonus) && saving == false) {
					// SavingAntiFuck = Tick + 200;
					// buildWaitFix = buildWaitFix + 200;
					Unit myUnit = GetWorker();
					if(myUnit != null){
					TilePosition buildTile = getBuildTile(myUnit, UnitType.Terran_Barracks, myUnit.getTilePosition());
					if (buildTile != null && myUnit != null) {
						saving = true;
						myUnit.build(UnitType.Terran_Barracks, buildTile);
						buildTypes.add(UnitType.Terran_Barracks);
						if(constructingWorkers.contains(myUnit) == false){
						constructingWorkers.add(myUnit);
						}
						if(LastBuildTick == 0){
						LastBuildTick = totalFrames + 100;
						}
					
					}
					else {
						saving = false;
					}

					}
				}
				
//				if (self.minerals() >= 700 && expanding == false) {
//					// SavingAntiFuck = Tick + 200;
//					// buildWaitFix = buildWaitFix + 200;
//					Unit myUnit = GetWorker();
//					if(myUnit != null){
//					TilePosition buildTile = getBuildTile(myUnit, UnitType.Terran_Barracks, myUnit.getTilePosition());
//					if (buildTile != null && myUnit != null) {
//						saving = true;
//						myUnit.build(UnitType.Terran_Barracks, buildTile);
//						if(constructingWorkers.contains(myUnit) == false){
//						constructingWorkers.add(myUnit);
//						}
//						if(LastBuildTick == 0){
//						LastBuildTick = totalFrames + 100;
//						}
//					
//					}
//					else {
//						saving = false;
//					}
//
//					}
//				}
				

				if ((Factories + FactoriesBuilding) < MaxFactories && self.minerals() >= (200 + ExpandingBonus) && self.gas() >= 150 && Bunks > 0 && saving == false && Bays > 0) {
					SavingAntiFuck = Tick + 200;
					buildWaitFix = buildWaitFix + 200;
					Unit myUnit = GetWorker();
					if(myUnit != null){
					TilePosition buildTile = getBuildTile(myUnit, UnitType.Terran_Factory, myUnit.getTilePosition());
					if (buildTile != null && myUnit != null) {
						saving = true;
						myUnit.build(UnitType.Terran_Factory, buildTile);
						buildTypes.add(UnitType.Terran_Factory);
						if(constructingWorkers.contains(myUnit) == false){
						constructingWorkers.add(myUnit);
						}
						if(LastBuildTick == 0){
						LastBuildTick = totalFrames + 100;
						}
						

					}
					else {
						saving = false;
					}

				}
			}
				if (game.canMake(UnitType.Terran_Armory) && Armor == 0 && saving == false) {
					boolean sdfg = BuildingAlreadyBeingOrdered(UnitType.Terran_Armory, 1);
					if(sdfg == false){
						Unit myUnit = GetWorker();
						if(myUnit != null){
						TilePosition buildTile = getBuildTile(myUnit, UnitType.Terran_Armory, myUnit.getTilePosition());
						if (buildTile != null && myUnit != null) {
							saving = true;
							myUnit.build(UnitType.Terran_Armory, buildTile);
							buildTypes.add(UnitType.Terran_Armory);
							if(constructingWorkers.contains(myUnit) == false){
							constructingWorkers.add(myUnit);
							}
							if(LastBuildTick == 0){
								LastBuildTick = totalFrames + 100;
							}
							
	
						}
						else {
							saving = false;
						}
	
					}
				}
			}

				if (self.minerals() >= (150 + ExpandingBonus) && isExpanding == false && HowManyDoIHave(UnitType.Terran_Academy) == 0 && saving == false && Racks > 0 && Bunks > 0) {
					// SavingAntiFuck = Tick + 200;
					// buildWaitFix = buildWaitFix + 200;
					Unit myUnit = GetWorker();
					if(myUnit != null){
					TilePosition buildTile = getBuildTile(myUnit, UnitType.Terran_Academy, self.getStartLocation());
					if (buildTile != null && myUnit != null) {
						saving = true;
						myUnit.build(UnitType.Terran_Academy, buildTile);
						buildTypes.add(UnitType.Terran_Academy);
						if(constructingWorkers.contains(myUnit) == false){
						constructingWorkers.add(myUnit);
						}
						if(LastBuildTick == 0){
						LastBuildTick = totalFrames + 100;
						}
						
					}
					else {
						saving = false;
					}

				}
			}
				if (BayCompleted == false && self.minerals() > (125 + ExpandingBonus) && saving == false && HowManyDoIHave(UnitType.Terran_Academy) > 0) {
					 SavingAntiFuck = Tick + 200;
					 buildWaitFix = buildWaitFix + 200;
					Unit myUnit = GetWorker();
					if(myUnit != null){
					TilePosition buildTile = getBuildTile(myUnit, UnitType.Terran_Engineering_Bay, self.getStartLocation());
					if (buildTile != null && myUnit != null) {
						saving = true;
						myUnit.build(UnitType.Terran_Engineering_Bay, buildTile);
						buildTypes.add(UnitType.Terran_Engineering_Bay);
						if(constructingWorkers.contains(myUnit) == false){
						constructingWorkers.add(myUnit);
						}
						if(LastBuildTick == 0){
						LastBuildTick = totalFrames + 100;
						}
						
					}
					else {
						saving = false;
					}

				}
			}
			if (game.canMake(UnitType.Terran_Missile_Turret) && Turrets <= (enemyFlyers.size() * 4) + 1 && saving == false && enemyFlyers.isEmpty() == false) {
					SavingAntiFuck = Tick + 200;
					Unit myUnit = GetWorker();
					if(myUnit != null){
					TilePosition buildTile = getBuildTile(myUnit, UnitType.Terran_Missile_Turret, myUnit.getTilePosition());
					if (buildTile != null && myUnit != null) {
					saving = true;
					myUnit.build(UnitType.Terran_Missile_Turret, buildTile);
					buildTypes.add(UnitType.Terran_Missile_Turret);
					if(constructingWorkers.contains(myUnit) == false){
					constructingWorkers.add(myUnit);
					}
						if(LastBuildTick == 0){
						LastBuildTick = totalFrames + 100;
						}
						
					}
					else {
						saving = false;
					}
				}
			}
			
			if (game.canMake(UnitType.Terran_Missile_Turret) == true && BayCompleted == true && startedDet == false && Bunks > 0) {
				Unit myUnit = GetWorker();
				placementIncrease = 2;
				TilePosition loc = null;
				if(myUnit != null){
				if(Bunks == 0 || bunkersBuilding == 0){
					loc = LastBunkerPos;
				}
				else {
					loc = ralleyPoint.toTilePosition();
				}
				//TilePosition DefenderableTile = getBuildTile(myUnit, UnitType.Terran_Missile_Turret, loc);
				TilePosition DefenderableTile = game.getBuildLocation(UnitType.Terran_Missile_Turret, loc, placementIncrease);
				if (DefenderableTile != null && myUnit != null) {
				game.sendText("Started detection near ramp");
				saving = true;
				myUnit.build(UnitType.Terran_Missile_Turret, DefenderableTile);
				buildTypes.add(UnitType.Terran_Missile_Turret);
				if(constructingWorkers.contains(myUnit) == false){
				constructingWorkers.add(myUnit);
				}
					if(LastBuildTick == 0){
					LastBuildTick = totalFrames + 100;
					}
					
				}
				else {
					placementIncrease = placementIncrease + 2;
					saving = false;
				}
			}
		}
			


				
			
			//This supply script is for building system lock up. 

			if ((PortsBuilding + StarPorts) == 0 && self.minerals() > (150 + ExpandingBonus) && self.gas() > 100 && buildwait == false && saving == false && Factories > 0) {
	
				 SavingAntiFuck = Tick + 200;
				 buildWaitFix = buildWaitFix + 200;
				Unit myUnit = GetWorker();
				if(myUnit != null){
				TilePosition buildTile = getBuildTile(myUnit, UnitType.Terran_Starport, self.getStartLocation());
				if (buildTile != null && myUnit != null) {
					saving = true;
					myUnit.build(UnitType.Terran_Starport, buildTile);
					buildTypes.add(UnitType.Terran_Starport);
					if(constructingWorkers.contains(myUnit) == false){
					constructingWorkers.add(myUnit);
					}
					if(LastBuildTick == 0){
					LastBuildTick = totalFrames + 100;
					}
					
				}
				else {
					saving = false;
				}

			}
		}
			
			if (game.canMake(UnitType.Terran_Science_Facility) == true && scienceStarted == false && self.minerals() > (100 + ExpandingBonus) && self.gas() > 200 && saving == false){
				Unit myUnit = GetWorker();
				if(myUnit != null){
				TilePosition buildTile = getBuildTile(myUnit, UnitType.Terran_Science_Facility, myUnit.getTilePosition());
				if (buildTile != null && myUnit != null) {
					saving = true;
					myUnit.build(UnitType.Terran_Science_Facility, buildTile);
					buildTypes.add(UnitType.Terran_Science_Facility);
					if(constructingWorkers.contains(myUnit) == false){
					constructingWorkers.add(myUnit);
					}
					if(LastBuildTick == 0){
					LastBuildTick = totalFrames + 100;
					}
					
				}
				else {
					saving = false;
				}

			}
		}
			



		}
	}
		// else if there is priority buildings that need to be built
		else {
			UnitType first = pBuildings.get(0);
				if(game.canMake(first) == false){
					saving = true;
					//System.out.println("Can't make " + first.toString());
					// save for the building
				}
				else {
					boolean bees94 = isOrderToBuildOrBetter(pBuildings.get(0));
						if(bees94 == false){
						// if not, build it\
						Unit myUnit = GetWorker();
						UnitType type = pBuildings.get(0);
						//System.out.println(type.toString());
						TilePosition pos = pPosition.get(0);
						//System.out.println("Pos: " + pos);
						if(myUnit != null){
							if(pos == null){
								pos = myUnit.getTilePosition();
								//System.out.println("Pos not set, pos is now: " + pos);
							}
							TilePosition buildTile = getBuildTile(myUnit, type, pos);
							if (buildTile != null) {
								saving = true;
								myUnit.build(type, buildTile);
								buildTypes.add(type);
								if(constructingWorkers.contains(myUnit) == false){
								constructingWorkers.add(myUnit);
								}
								if(pWorkers.contains(myUnit) == false){
									Pair pair = new Pair<Unit, UnitType>(myUnit, type);
									pWorkers.add(pair);
									//System.out.println("Adding to pWorkers");
								}
		
							}
					}
				}
			}
		}

		
			
			if(Bats >= BatsNeed && DifBuild == 4){	
				DifBuild++;
			}
			
			if(Medics >= MedicsNeed && DifBuild == 3){
				DifBuild++;
			}
			
			if(DifBuild == 5 && HowManyDoIHave(UnitType.Terran_Marine) + HowManyDoIHave(UnitType.Terran_Medic) < 15){
				DifBuild++;
			}
			
			
			if(ralleyPoint != null){
				game.drawCircleMap(ralleyPoint, 100, bwapi.Color.Green);
			}
			

			for(Unit unit : enemyFlyers){
				if(unit.exists() == false){
					enemyFlyers.remove(unit);
				}
			}
			
		for (Unit myUnit : self.getUnits()) {

				// jukers
				if(jukers.containsKey(myUnit) == true){
					if(jukers.get(myUnit).isEmpty() == false){
						ArrayList<Unit> list = jukers.get(myUnit);
						for(Unit units : list){
							if(units.isVisible() == true && units.getTarget() == myUnit){
								Position regroup = GetJukePos2(myUnit, units);
								if(regroup != null){
									if(regroup.isValid(game) == true){
										myUnit.move(regroup);
									}
	
								}
							}
							
							if(units.isVisible() == false || units.exists() == false){
								jukers.remove(units);
							}
						}
					}
				}
				
				if(myUnit.isMoving() == true  && myUnit.isUnderAttack() == true && attacking == false && moveFap == 0 && IsMilitrayUnit(myUnit) == true){
					ArrayList<Unit> mine = GetMyUnitsNearby(myUnit.getPosition(), 220, false);
					ArrayList<Unit> notmine = GetEnemyUnitsNearby(myUnit.getPosition(), 300, false);	
					boolean canWin = ScoreFap(mine, notmine, 1);
					Unit attacker = null;
					attacker = GetAttacker(myUnit, 300);
					if(attacker == null){
						attacker = notmine.get(0);
					}
					if(canWin == true){
						for(Unit units : mine){
							boolean isStacking = orderStacking(units, attacker.getPosition());
							if(units.exists() == true && units.isAttacking() == false && isStacking == false){
								units.attack(attacker.getPosition());
							}
						}
					}
						
				}
					
			
				
				if(myUnit.isSelected() == true && drawOrderLines == true){
				game.drawTextMap(myUnit.getPosition().getX(), myUnit.getPosition().getY(), myUnit.getOrder().toString());
				game.drawLineMap(myUnit.getPosition().getX(), myUnit.getPosition().getY(), myUnit.getOrderTargetPosition().getX(),
				myUnit.getOrderTargetPosition().getY(), bwapi.Color.Black);
				}
				
				if(myUnit.isStartingAttack() || myUnit.isAttacking() == true){
					ThinkUnit(myUnit);
				}
				
				if(pWorkers.isEmpty() == false){
					if(pWorkers.contains(myUnit) == true){
						game.drawCircleMap(myUnit.getPosition(), 20, bwapi.Color.Black);
					}
				}
				
				if(myUnit.isAttacking() == true && searchSiege == 0){
					for(Unit units : myUnit.getUnitsInRadius(120)){
						if(units.getType() == UnitType.Terran_Siege_Tank_Tank_Mode && units.isSieged() == false){
							units.siege();
						}
					}
				}
				
				if(employees.containsKey(myUnit) == true){
					game.drawLineMap(myUnit.getPosition(), employees.get(myUnit).toPosition(), bwapi.Color.Black);
				}
				
				if(employees.isEmpty() == false && baseWorkers.isEmpty() == false && maxWorkers.isEmpty() == false){
					if(myUnit.isGatheringGas() || myUnit.isGatheringMinerals()){	
					Position target = myUnit.getPosition();
					//getOrderTargetPosition();
					Base loc = getClosestBaseLocation(target);
					Base value = null;
					if(employees.containsKey(myUnit)){
						 value = getClosestBaseLocation(employees.get(myUnit).toPosition());
					}
					
					if(value != null){
						if(employees.containsKey(myUnit) && loc != value){
							//game.sendText("Hey, You don't work here!");
							game.pingMinimap(myUnit.getPosition());
							myUnit.move(employees.get(myUnit).toPosition());
						}
						
						if(baseWorkers.containsKey(loc.getLocation()) && maxWorkers.containsKey(loc.getLocation()) && employees.containsKey(myUnit)){
							if(baseWorkers.get(loc.getLocation()) > maxWorkers.get(loc.getLocation())){
								game.sendText("BaseWorkers is full, mining at a new base");
								employees.remove(myUnit);
								baseWorkers.put(loc.getLocation(), baseWorkers.get(loc.getLocation()) - 1);
							}
						}
					}
				}
				}
				
				if(myUnit.isUnderStorm() && myUnit.getPosition().getApproxDistance(ralleyPoint) > 700){
					if(ralleyPoint != null && myUnit.isMoving() == false){
						myUnit.move(ralleyPoint);
						for(Unit units : GetMyUnitsNearby(myUnit.getPosition(), 100, false)){
							if(units.isMoving() == false && units.getPosition().getApproxDistance(ralleyPoint) > 700){
								units.move(ralleyPoint);
							}
						}
					}
				}
				
	
				
				
				boolean isMil = IsMilitrayUnit(myUnit);
				
				if(constructingWorkers.contains(myUnit) == true && myUnit.isConstructing() == false){
				int index = constructingWorkers.indexOf(myUnit);
				constructingWorkers.remove(index);
				saving = false;
				buildwait = false;
				}
				
				if(constructingWorkers.contains(myUnit) == true && myUnit.isConstructing() == false && myUnit.hasPath(myUnit.getOrderTargetPosition()) == false){
				int index = constructingWorkers.indexOf(myUnit);
				constructingWorkers.remove(index);
				saving = false;
				buildwait = false;
				game.sendText("SCV can't path to construction area");
				}
				
				
				if(myUnit.getType() == UnitType.Terran_Physics_Lab && BCTech == false){
					BCTech = true;
					game.sendText("wget www.ifBot.weebly.com/src/ShitBot/Lab.docx -OutFile D:/danface.txt");
				}
				
				
				if(myUnit.isUnderAttack() == true && defenderCall == true && myUnit.isUnderStorm() == false){
					DefenceCall(myUnit);
				}
					
				
//			
//				if(myUnit.isAttacking() || myUnit.isMoving() && myUnit.isGatheringMinerals() == false && myUnit.isGatheringGas() == false){
//					Position fuckthisshit = new Position(0,0);
//					game.drawTextMap(myUnit.getPosition().getX(), myUnit.getPosition().getY(), myUnit.getOrder().toString());
//					if(myUnit.getOrderTargetPosition() != fuckthisshit){
//						game.drawLineMap(myUnit.getPosition().getX(), myUnit.getPosition().getY(), myUnit.getOrderTargetPosition().getX(),
//								myUnit.getOrderTargetPosition().getY(), bwapi.Color.Orange);
//					}
//
//				}
				

				if (saving == false) {
						if(pUnits.isEmpty() == true){
							if(CanMakeWorkers() == true){
								if (myUnit.getType() == UnitType.Terran_Command_Center && self.minerals() >= 50
										&& Workers < MaxWorkers && myUnit.isIdle() == true) {
									myUnit.train(UnitType.Terran_SCV);
								}
							}
							if (myUnit.getType() == UnitType.Terran_Barracks && self.minerals() >= 50 && myUnit.isIdle() == true
									&& Marines < MarinesNeed && DifBuild == 1) {
								myUnit.train(UnitType.Terran_Marine);
								DifBuild++;
							}
		
							if (myUnit.getType() == UnitType.Terran_Barracks && self.minerals() >= 50 && myUnit.isIdle() == true
									&& Marines < MarinesNeed && DifBuild == 2 && HowManyDoIHave(UnitType.Terran_Academy) > 0) {
								myUnit.train(UnitType.Terran_Marine);
								DifBuild++;
							}
		
							if (myUnit.getType() == UnitType.Terran_Barracks && self.minerals() >= 50 && self.gas() >= 25
									&& myUnit.isIdle() == true && Medics < MedicsNeed && game.canMake(UnitType.Terran_Medic)
									&& DifBuild == 3) {
								myUnit.train(UnitType.Terran_Medic);
								DifBuild++;
							}
		
							if (myUnit.getType() == UnitType.Terran_Barracks && self.minerals() >= 100 && self.gas() >= 25
									&& myUnit.isIdle() == true && Bats < BatsNeed && game.canMake(UnitType.Terran_Firebat) && DifBuild == 4) {
								myUnit.train(UnitType.Terran_Firebat);
								DifBuild++;
							}

								if (myUnit.getType() == UnitType.Terran_Factory && self.minerals() >= 150 && self.gas() >= 100
										&& myUnit.isIdle() == true && DifBuild == 5) {
									myUnit.train(UnitType.Terran_Siege_Tank_Tank_Mode);
									DifBuild++;
								}
							

								if (myUnit.getType() == UnitType.Terran_Factory && self.minerals() >= 150 && self.gas() >= 100
										&& myUnit.isIdle() == true) {
									myUnit.train(UnitType.Terran_Siege_Tank_Tank_Mode);
								}
		
							if (myUnit.getType() == UnitType.Terran_Factory && self.minerals() >= 100 && self.gas() >= 50
									&& Gol <= enemyFlyers.size() + 2 && myUnit.isIdle() == true && Armor > 0 && Tanks > 3) {
								myUnit.train(UnitType.Terran_Goliath);
								Gol = Gol + 1;
		
							}
							
							if (myUnit.getType() == UnitType.Terran_Starport && scienceFinished == true && self.minerals() >= 100 && self.gas() > 225 && TSF <= detectionNeeded
									&& myUnit.isIdle() == true) {
									myUnit.train(UnitType.Terran_Science_Vessel);
							}
							
							if (myUnit.getType() == UnitType.Terran_Starport && game.canMake(UnitType.Terran_Battlecruiser) == true && myUnit.isIdle() == true) {
									myUnit.train(UnitType.Terran_Battlecruiser);
							}
						
					
					}
					else{
						boolean hgi = HasTechFor(pUnits.get(0));
						if(hgi == true){
							UnitType type = pUnits.get(0);
							for (Unit buildings : productionBuildings) {
								if(game.canMake(type, buildings) == true && buildings.isIdle() == true){
									buildings.train(type);
									game.sendText("Training Priority Unit: " + type.toString());
									pUnits.remove(0);
								}
			
							}
						}
						else {
							UnitType type = pUnits.get(0);
							game.sendText("No requirements for: " + type.toString() + " Blaming Someone on discord for it");
							pUnits.remove(0);
							
						}
					}



					if (myUnit.getType() == UnitType.Terran_Machine_Shop && self.minerals() >= 200 && self.gas() >= 200
							&& myUnit.isIdle() == true && myUnit.canResearch(TechType.Tank_Siege_Mode, true) && Tanks > 1) {
						myUnit.research(TechType.Tank_Siege_Mode);
						siegeResearched = true;

					}
					
					
					if(pBuildings.isEmpty() == false && pWorkers.isEmpty() == false){
						for(Pair<Unit, UnitType> pear : pWorkers){
							Unit worker = pear.getKey();
							if(worker != null){
								if(worker.isConstructing() == false){
									String type = pBuildings.get(0).toString();
									//game.sendText("HEY, YOU GET TO EAT ONCE YOU FINSIH BUILDING " + type);
									pWorkers.remove(0);
									break;
								}
							}
							
						}
					}
					
//					if (myUnit.getType() == UnitType.Terran_Factory && self.minerals() >= 80 && Vultures < VulturesMax
//							&& myUnit.isIdle() == true) {
//							myUnit.train(UnitType.Terran_Vulture);
//					}
					


					if (myUnit.getType() == UnitType.Terran_Machine_Shop && self.minerals() >= 200 && self.gas() >= 100
							&& myUnit.isIdle() == true && myUnit.canResearch(TechType.Spider_Mines, true) && Vultures > 0) {
						myUnit.research(TechType.Spider_Mines);
					}
					
					if (myUnit.getType() == UnitType.Terran_Machine_Shop & myUnit.isIdle() == true && myUnit.canUpgrade(UpgradeType.Charon_Boosters) == true && Gol > 4) {
						myUnit.upgrade(UpgradeType.Charon_Boosters);
					}
					
					if (myUnit.getType() == UnitType.Terran_Armory && myUnit.isIdle() == true && myUnit.canUpgrade(UpgradeType.Terran_Vehicle_Weapons) == true){
						myUnit.upgrade(UpgradeType.Terran_Vehicle_Weapons);
					}
					
					if (myUnit.getType() == UnitType.Terran_Armory && myUnit.isIdle() == true && myUnit.canUpgrade(UpgradeType.Terran_Vehicle_Plating) == true){
						myUnit.upgrade(UpgradeType.Terran_Vehicle_Plating);
					}
					
					
					if (myUnit.getType() == UnitType.Terran_Academy && myUnit.isIdle() == true && saving == false && myUnit.canUpgrade(UpgradeType.U_238_Shells) == true){
						myUnit.upgrade(UpgradeType.U_238_Shells);
					}
					
					
					if (myUnit.getType() == UnitType.Terran_Academy && myUnit.isIdle() == true && saving == false && Racks > 1 && myUnit.canResearch(TechType.Stim_Packs) == true && IsUpgradingorBetter(UpgradeType.U_238_Shells) == true){
						myUnit.research(TechType.Stim_Packs);
			
					}
					
					if (myUnit.getType() == UnitType.Terran_Engineering_Bay && myUnit.isIdle() == true && saving == false && Factories > 0 && myUnit.canUpgrade(UpgradeType.Terran_Infantry_Weapons)){
						myUnit.upgrade(UpgradeType.Terran_Infantry_Weapons);
						
						
					}
					
					if (myUnit.getType() == UnitType.Terran_Engineering_Bay && myUnit.isIdle() == true && saving == false && Factories > 0 && myUnit.canUpgrade(UpgradeType.Terran_Infantry_Armor)){
						myUnit.upgrade(UpgradeType.Terran_Infantry_Armor);
						
						
					}
					


				}
				
				if(ExpandPlacing == true) {
					
					
					if (myUnit.getType() == UnitType.Terran_Factory && self.minerals() >= 600 && self.gas() >= 100
							&& myUnit.isIdle() == true) {
						myUnit.train(UnitType.Terran_Siege_Tank_Tank_Mode);
					}
					
					
					if (myUnit.getType() == UnitType.Terran_Barracks && self.minerals() >= 600 && myUnit.isIdle() == true) {
						myUnit.train(UnitType.Terran_Marine);
					}
					
					
//					if (myUnit.getType() == UnitType.Terran_Factory && self.minerals() >= 600 && myUnit.isIdle() == true) {
//						myUnit.train(UnitType.Terran_Vulture);
//					}
					
					
					if (myUnit.getType() == UnitType.Terran_Starport && self.minerals() > 900 && self.gas() > 400 && game.canMake(UnitType.Terran_Battlecruiser) && myUnit.isIdle() == true) {
						myUnit.train(UnitType.Terran_Battlecruiser);
					}
					
					
								
					
				}
				
		
				if (myUnit.getType() == UnitType.Terran_Bunker && myUnit.isCompleted() == true && myUnit.getLoadedUnits().size() != 4) {
					int myID = myUnit.getID();
					if(bunkersSize.get(myID) != null){
						int amount = bunkersSize.get(myID);
						if(amount != 4){
							for(Unit units : myUnits){
								if(units.getType() == UnitType.Terran_Marine && units.isCompleted() == true && units.isIdle() && units.isLoaded() == false){
									if(units.isIdle() == true){
										units.rightClick(myUnit);
										int neww = amount + 1;
										bunkersSize.put(myID, neww);
										myUnits.remove(units);
										break;
										
									}
								}
							}
						}
						
					}
					else {
						game.sendText("Can't find object in hashmap for item: " + myID + " Blaming someone on discord for it");
					}

				}
						
				if(myUnit.getType() == UnitType.Terran_Bunker && myUnit.getLoadedUnits().size() == 4 && bunkersFilled.contains(myUnit) == false){
					bunkersFilled.add(myUnit);
				}
				
				if(bunkersFilled.contains(myUnit) == true){
					game.drawCircleMap(myUnit.getPosition(), 100, bwapi.Color.Purple);
				}

				if (myUnit.getType() == UnitType.Terran_Siege_Tank_Siege_Mode && myUnit.isSieged() == true && myUnit.isIdle() == true) {
					boolean hostiles = false;
					for (Unit targets : myUnit.getUnitsInRadius(400)) {
						if (targets.getPlayer().isEnemy(self) && myUnit.canAttack(targets) == true) {
							hostiles = true;
							break;
						}
					}
					if(hostiles == false){
						myUnit.unsiege();
					}

				}

				// if (myUnit.getType() == UnitType.Terran_Vulture &&
				// myUnit.isAttacking() == true) {
				// System.out.println("Launch");
				// Unit unitt = myUnit;
				// System.out.println("Vulture");
				// Unit targett = myUnit.getTarget();
				// System.out.println("Target");
				// Position Move = GetKitePos2(unitt, targett);
				// System.out.println("Call");
				// if (Move.isValid() == true) {
				// System.out.println("Valid");
				// unitt.move(Move);
				// System.out.println("move");
				// game.drawCircleMap(Move, 5, Color.Orange, false);
				// }
				// System.out.println("Not valid");
				// }



				if (myUnit.isIdle() && attacking == true && myUnit.getType().isWorker() == false && myUnit.getType() != UnitType.Terran_Medic && myUnit.isUnderStorm() == false) {
					if (enemyBuildingMemory.isEmpty() == false) {
						// if we are not in a FFA
						if(TeamGameMode == false){
							myUnit.attack(enemyBuildingMemory.get(0));
						}
					} else {
						if(scoutedLocations.isEmpty()){
							for(Base base : bewb.getMap().getBases()){
								if(scoutedLocations.contains(base) == false){
									scoutedLocations.add(base);
								}
							}
						}
						for (Base b : scoutedLocations) {
							if (game.isVisible(b.getLocation()) == false) {
								myUnit.attack(b.getCenter());
								break;
							}
						}
					}
					
					if(DoneCreatingList == true && playerBuildings.isEmpty() == false && TeamGameMode == true){
						//if we are in a FFA
						int target = GetPlayerTarget();
						if(target != 10){
							Position pos = playerBuildings.get(target).get(0);
							if(pos != null){
								myUnit.attack(pos, false);
							}
						}

						}
				}

				if (myUnit.isConstructing() == true) {
					Position pos = myUnit.getPosition();
					game.drawCircleMap(pos, 20, bwapi.Color.Brown);

				}
				
				//science vesseles
				
				
				if(myUnit.getType() == UnitType.Terran_Science_Vessel && myUnit.isIdle()){
					boolean a = false;
					for(Unit unit : myUnits){
						if(unit.isAttacking() == true || unit.isAttackFrame() == true){
							Position pos = unit.getPosition();
							if(pos != null){
								myUnit.move(pos);
								a = true;
								break;
							}
						}
					}
					if(a == false){
						for(Unit unit : myUnits){
							if(unit.isMoving() == true){
								Position pos = unit.getPosition();
									if(pos != null){
										myUnit.move(pos);
										a = true;
										break;
									}
							}
						}
					}
				}
				
				if(myUnit.getType() == UnitType.Terran_Medic && myUnit.isIdle()){
					boolean a = false;
					for(Unit unit : myUnits){
						if(unit.isAttacking() == true || unit.isAttackFrame() == true){
							Position pos = unit.getPosition();
							if(pos != null){
								myUnit.move(pos);
								a = true;
								break;
							}
						}
					}
					if(a == false){
						for(Unit unit : myUnits){
							if(unit.isMoving() == true){
								Position pos = unit.getPosition();
									if(pos != null){
										myUnit.move(pos);
										a = true;
										break;
									}
							}
						}
					}
				}

				if(myUnit.getType() == UnitType.Terran_Science_Vessel && myUnit.getEnergy() > 100 && SVCheck == 0){
					SVCheck = 1;
					for(Unit units : myUnit.getUnitsInRadius(300)){
						if(units.isUnderAttack() == true && units.getPlayer() == self && myUnit.canUseTech(TechType.Defensive_Matrix, units) == true && units.isDefenseMatrixed() == false){
							myUnit.useTech(TechType.Defensive_Matrix, units);
						}
					}
				}

				// combat sim
				
				

				if (isInCombat(myUnit) == true && myUnit.getType().isBuilding() == false && simCallFrames == 0 && myUnit.getType().isWorker() == false && !simedUnits.containsKey(myUnit)) {
					int bonus = 1;	
					simCallFrames = 1;
					Unit attacker = null;
					boolean canRetreat = true;
					int attackerx = 0;	
					int attackery = 0;
//			
//					if(myUnit.getRegion().getDistance(BWTA.getNearestChokepoint(myUnit.getPosition()).getRegions().first) < 600 && Strats == "Full Attack" &&
//						myUnit.getDistance(self.getStartLocation().toPosition()) > 2000 &&
//						myUnit.getDistance(enemyBasePos.getPoint()) < 3000){
//						gaf = true;
//						bonus = myUnit.getRegion().getDefensePriority();
//					}
					
					if(enemyChokes.contains(myUnit.getRegion()) == true){
						//if our unit is in an enemy chokepoint
						bonus = 3;
					}
					
					if(myUnit.getDistance(self.getStartLocation().toPosition()) < 2000){
						canRetreat = false;
					}
					
					// We will make a new list of units nearby, sort them,  and then to give to the function.
					
					ArrayList<Unit> mine = new ArrayList<Unit>();
					ArrayList<Unit> hostile = new ArrayList<Unit>();
					
					for(Unit units : getUnitsInRadius(myUnit.getPosition(), 220)){
						boolean bool = IsMilitrayUnit(units);
						boolean bool1 = IsMilitrayBuilding(units);
						
						if(units.getPlayer() == self && bool == true && mine.contains(units) == false && bool == true){
							mine.add(units);
						}
						
						if(units.getPlayer() == self && bool1 == true && mine.contains(units) == false && bool1 == true){
							mine.add(units);
						}
						System.out.println("Is enemy: " + units.getPlayer().isEnemy(self));
						System.out.println("Is Mil Unit: " + bool);
						System.out.println("Contains " + hostile.contains(units));
						
						if(units.getPlayer().isEnemy(self) && bool == true && hostile.contains(units) == false){
							System.out.println("Trigger");
							hostile.add(units);
							if(attacker == null){
								attacker = units;
								System.out.println("Attacker is now: " + attacker.getType().toString());
								attackerx = attacker.getPosition().x;
								attackery = attacker.getPosition().y;
							}
						}
						
						if(units.getPlayer().isEnemy(self) && bool1 == true && hostile.contains(units) == false){
							hostile.add(units);
						}
						
						
					}
				
					boolean canWin = jFaplocal(mine, hostile, bonus);	
					//System.out.println(canWin);
					//System.out.println(bonus);
					
					if(canWin == false && canRetreat == true){
						//System.out.println("Can't win");
						Position regroup = null;
						if(attacker == null){
							System.out.println("Attacker is null");
						}
						System.out.println("Attacker visible: " + attacker.isVisible());
						if(game.isVisible(attacker.getTilePosition()) == false){
						//System.out.println("Is not visible");
						regroup = GetJukePosManual(myUnit, attackerx, attackery);
						} else {
						regroup = GetJukePos2(myUnit, attacker);
						//System.out.println("Is visible");
						}
						
						if(regroup != null){
							//System.out.println("Not null");
							if(regroup.isValid(game) == true){
								//System.out.println("is Valid");
								for(Unit unitss : mine){
									if(unitss != null){
										if(unitss.isMoving() == false){
											unitss.move(regroup);
										}
									}
									if(unitss != null){
										if(unitss.getType() == UnitType.Terran_Siege_Tank_Siege_Mode){
											retreatingTanks.add(unitss);
											unitss.unsiege();
										}
									}
								}
							}
							else {
								game.sendText("Regroup is not valid");
							}
						}
					}

			}
				
				
				
			// end of combat sim
					
				//for all units


//				if (myUnit.isAttacking() == true && myUnit.isAttackFrame() == false && myUnit.getType() == UnitType.Terran_Marine && myUnit.getType().isWorker() == false) {
//					Unit target = myUnit.getOrderTarget();
//					Unit unit = myUnit;
//					if (target.getType() == UnitType.Protoss_Zealot) {
//						Position JukePos = getJukePos(target, unit);
//						if (JukePos != null) {
//							myUnit.move(JukePos);
//							System.out.println("Juking at: " + JukePos);
//						}
//				}
//					if (target.getType() == UnitType.Zerg_Zergling) {
//						Position JukePos = getJukePos(target, unit);
//						if (JukePos.isValid() == true) {
//							myUnit.move(JukePos);
//							
//						}
//					}
//
//				}

				// repair script decided it wanted to break everything

				
				if (myUnit.getType().isBuilding() == true && myUnit.getHitPoints() < myUnit.getType().maxHitPoints()
						&& myUnit.isBeingHealed() == false && myUnit.isBeingConstructed() == false && IsMilitrayBuilding(myUnit) == true && repairingBuildings.contains(myUnit.getID()) == false) {
					// don't repair buildings that are under attack unless they are an MilitrayBuilding
					
					if(repairingBuildings.isEmpty() || repairingBuildings.contains(myUnit.getID()) == false){
						repairingBuildings.add(myUnit.getID());
					}
					
					//System.out.println("Trigger repair");
					Unit unit = myUnit;
					ArrayList<Unit> repairers = GetWorkerAmount(3);
					if(repairers != null){
						for(Unit repairs : repairers){
							repairs.repair(unit);
						}
					}
					else {
						//System.out.println("Get workers is null");
					}

				}
				
				if (myUnit.getType().isBuilding() == true && myUnit.getHitPoints() < myUnit.getType().maxHitPoints()
						&& myUnit.isBeingHealed() == false && myUnit.isBeingConstructed() == false && IsMilitrayBuilding(myUnit) == false && myUnit.isUnderAttack() == false) {
					Unit unit = myUnit;
					int index = 0;
					Unit repairs = GetWorker();
						if (repairingBuildings.isEmpty() == true) {
							if (repairs.getType() == UnitType.Terran_SCV && repairs.getID() != scoutID
									&& repairs.isGatheringMinerals() == true) {
								Position oldOrder = repairs.getOrderTargetPosition();
								repairs.repair(unit);
								repairingBuildings.add(unit.getID());
							}
						}
						if (repairingBuildings.contains(myUnit.getID()) == false && repairs.getType() == UnitType.Terran_SCV
								&& repairs.getID() != scoutID && repairs.isGatheringMinerals() == true) {
							Position oldOrder = repairs.getOrderTargetPosition();
							repairs.repair(unit);
							repairingBuildings.add(unit.getID());
						}

				}
		
				

				if (myUnit.getType().isBuilding() == true && myUnit.getHitPoints() == myUnit.getType().maxHitPoints()
						&& repairingBuildings.contains(myUnit.getID()) == true) {
					int ID = myUnit.getID();
					if (repairingBuildings.contains(ID) == true) {
						int index = repairingBuildings.indexOf(ID);
						repairingBuildings.remove(index);
					}

				}


				if (myUnit.isLifted() == true && myUnit.isIdle() == true) {
					TilePosition LandLocation = getLandLocation(myUnit, myUnit.getTilePosition());
					if (LandLocation != null) {
						if(myUnit.canLand(LandLocation) == true){
							myUnit.land(LandLocation);
						}
						
					}

				}



				if (myUnit.isUnderAttack() == true && myUnit.getType() == UnitType.Terran_Marine
						|| myUnit.getType() == UnitType.Terran_Firebat || myUnit.getType() == UnitType.Terran_Medic ) {
					for (Unit medics : myMedics) {
						if (medics.getType() == UnitType.Terran_Medic && medics.getEnergy() > 5  && medics.isMoving() == true
								|| medics.isIdle() == true) {
							medics.useTech(TechType.Healing, myUnit);
						}

					}

				}
				
				if(myUnit.isUnderAttack() == true && myUnit.getType() == UnitType.Terran_Medic){
					Unit attacker = GetAttacker(myUnit, 200);
					if(attacker != null && myUnit.isMoving() == false){
						Position regroup = GetJukePos2(myUnit, attacker);
						myUnit.move(regroup);
					}
					
				}


			if(myUnit.isAttacking() == true && customTargets == 0){
					customTargets = 1;
					ArrayList<Unit> myUnits = new ArrayList<Unit>();
					ArrayList<Unit> notMyUnits = new ArrayList<Unit>();
					for(Unit units : getUnitsInRadius(myUnit.getPosition(), 350)){
						boolean yes = IsMilitrayUnit(units);
						
						if(units.getPlayer() == self && yes == true && units.getType().isWorker() == false){
							myUnits.add(units);
						}
						
						if(game.enemies().contains(units.getPlayer()) == true && ShouldBeFocused(units) == true && IsHealing(units) == true){
							notMyUnits.add(units);
						}
						
						if(units.getType().isWorker() == true && units.isRepairing() == true && units.getPlayer().isEnemy(self)){
							notMyUnits.add(units);
						}
						
						for(Unit unitss : notMyUnits){
							if(notMyUnits.isEmpty() == false){
								for(Unit unit : myUnits){
									if(unitss != null){
										unit.attack(unitss);
										game.drawCircleMap(unitss.getPosition(), 15, bwapi.Color.Green);
										game.pingMinimap(unitss.getPosition());
									}
								}
							}
						}

					}

				}
				
				//To stop the bot freezing
				//defence
				//defence
				//defenceFrames
				//defenceCallFrames
				//
				

				
				if (myUnit.getType() == UnitType.Terran_Factory
						&& myUnit.canBuild(UnitType.Terran_Machine_Shop, true) && myUnit.isCompleted() == true) {
					myUnit.buildAddon(UnitType.Terran_Machine_Shop);
				}
				
				if (myUnit.getType() == UnitType.Terran_Starport && myUnit.canBuild(UnitType.Terran_Control_Tower, true) && myUnit.isCompleted() == true) {
					myUnit.buildAddon(UnitType.Terran_Control_Tower);
				}
				
				if (myUnit.getType() == UnitType.Terran_Science_Facility
						&& myUnit.canBuild(UnitType.Terran_Physics_Lab, true) && myUnit.isCompleted() == true) {
					myUnit.buildAddon(UnitType.Terran_Physics_Lab);
					BCTech = true;
					
				}

				if (myUnit.getType() == UnitType.Terran_Command_Center
						&& myUnit.canBuild(UnitType.Terran_Comsat_Station, true) && myUnit.isCompleted() == true) {
					myUnit.buildAddon(UnitType.Terran_Comsat_Station);
				}
 
				if (myUnit.getType() == UnitType.Terran_Command_Center
						&& myUnit.canBuild(UnitType.Terran_Comsat_Station, false)) {
					Position Postion = myUnit.getPosition();
					for (Unit buildings : myUnit.getUnitsInRadius(30)) {
						if (buildings.canLift() == true && buildings.getType() != UnitType.Terran_Command_Center) {
							buildings.lift();
						}
						if (buildings.getType() == UnitType.Terran_Supply_Depot) {
							for (Unit helpers : myUnit.getUnitsInRadius(200)) {
								if (helpers.canAttack() == true && helpers.getType().isBuilding() == false
										&& helpers.getType().isWorker() == false && helpers.isIdle() == true
										&& buildings.exists() == true) {
									helpers.attack(buildings);

								}
							}

						}
					}
				}
		
				
				// TODO: FIX THIS SHIT CURRENTLY REPORTING 0 CURRENT HARVESTERS
//				if(myUnit.getType() == UnitType.Terran_Refinery && myUnit.isBeingGathered() == false && myUnit.isCompleted() == true && myUnit.getResources() != myUnit.getInitialResources()){
//					int amount = amountOfGasHarvesters(myUnit);
//					if(amount != 3 && myWorkers.size() > 13){
//						for (int i = 0; i!=3; i++){
//							Unit worker = GetWorker();
//							if(myUnit != null){
//								worker.gather(myUnit);
//								if(gasWorkers.contains(worker) == false){
//								gasWorkers.add(worker);
//								}
//							}
//						}
//					}
//				}
				
				if (myUnit.getType() == UnitType.Terran_Factory && myUnit.canBuild(UnitType.Terran_Machine_Shop, false) && myUnit.isLifted() == false && myUnit.isCompleted() == true) {
					myUnit.lift();
				}
				
				if (myUnit.getType() == UnitType.Terran_Starport && myUnit.canBuild(UnitType.Terran_Control_Tower, false) && myUnit.isLifted() == false && myUnit.isCompleted() == true) {
					myUnit.lift();

				}
				
				if (myUnit.getType() == UnitType.Terran_Science_Facility && myUnit.canBuild(UnitType.Terran_Physics_Lab, false) && myUnit.isLifted() == false && myUnit.isCompleted() == true) {
					myUnit.lift();

				}
				
				if (myUnit.getID() == scoutID && ScoutSent == false && myUnit.isIdle() == true && myMinerals.isEmpty() == false) {
					Unit closestMineral = null;
					for (Unit neutralUnit : game.neutral().getUnits()) {
						if (neutralUnit.getType().isMineralField() && neutralUnit.isBeingGathered() == false) {
							if (closestMineral == null
									|| myUnit.getDistance(neutralUnit) < myUnit.getDistance(closestMineral)) {
								closestMineral = neutralUnit;
							}
						}
					}

					if (closestMineral != null) {
						myUnit.gather(closestMineral, false);
					}
				}

				// if it's a worker and it's idle, send it to the closest
				// mineral
				// patch

				if (myUnit.getType().isWorker() && myUnit.isIdle() == true && myUnit.isGatheringGas() == false
						&& myUnit.getID() != scoutID && gasWorkers.contains(myUnit) == false) {
					if(employees.containsKey(myUnit) == true){
						Mineral closestMineral = null;
						for (Mineral neutralUnit : myMinerals) {
							if (closestMineral == null
									|| myUnit.getPosition().getApproxDistance(neutralUnit.getCenter()) < myUnit.getPosition().getApproxDistance(closestMineral.getCenter())) {
								closestMineral = neutralUnit;
								if (closestMineral != null) {
									Base loc = getClosestBaseLocation(myUnit.getPosition());
									myUnit.gather(closestMineral.getUnit(), false);
								}
							}
						}
					}
					else {
						Base loc = GetBaseToGatherAt();
						if(loc != null){
						myUnit.move(loc.getCenter());
						int code = loc.hashCode();
						TilePosition tile = loc.getLocation();
						if(baseWorkers.containsKey(tile) == true){
							//System.out.println("Contains base");
							baseWorkers.put(tile, baseWorkers.get(tile) + 1);
							employees.put(myUnit, tile);
								
							}
						}
						else {
							//System.out.println("404_BASE_NOT_FOUND");
						}
					}
				}
				
				
				
				if(myUnit.isIdle() == true && gasWorkers.contains(myUnit) == true){
					int index = 0;
					int max = self.getUnits().size();
					for(Unit unit : self.getUnits()){
						if(index >= max && gasWorkers.contains(myUnit) == true){
							int indexx = gasWorkers.indexOf(myUnit);
							gasWorkers.remove(indexx);
							game.sendText("No sign of free oil, sending it back to the t-34 factories");
						}
						if(unit.getType() == UnitType.Terran_Refinery == true && unit.isBeingGathered() == false){
							myUnit.gather(unit);
							break;
						}
					}
					
					
				}
				
//				if(scouter != null && scoutTime == 0){
//					scoutTime = 1;
//					if(scouter.exists() && scouter.isCompleted() == true && scouter.isMoving() == true){
//						for(Unit units : game.getUnitsInRadius(myUnit.getPosition(), 300)){
//							boolean asds = isTargettingUnit(units, scouter);
//							//System.out.println("is Targetting: " + asds);
//							if(IsMilitrayUnit(units) == true){
//								System.out.println("Unit: " + units.getType().toString() + " Is a militray unit and should not be hugged");
//							}
//							if(game.enemies().contains(units.getPlayer()) && asds == true){
//								System.out.println("TRIGGER ATTACK");
//								game.drawCircleMap(units.getPosition(), 30, bwapi.Color.Yellow);
//								Position flee = GetKitePos2(scouter, units);
//								if(flee != null){
//									System.out.println("flee not null");
//										if(flee.isValid() == true){
//										myUnit.move(flee);
//										System.out.println("is valid");
//										game.pingMinimap(flee);
//										break;
//										}
//									}
//									else{
//										if(ralleyPoint != null){
//										myUnit.move(ralleyPoint);
//										System.out.println("Flee to regroup");
//										break;
//										}
//									}
//								break;						
//							}
//							
//						}
//				}
//			}
				
				
			if(scouter != null){
				if(scouter.isUnderAttack() == true && scouterFleeing == false){
					if(ralleyPoint != null){
						scouter.move(ralleyPoint);
						scouterFleeing = true;
					}
					else {
						scouter.move(self.getStartLocation().toPosition());
					}
				}
				
				if(scouterFleeing == true && myUnit.isUnderAttack() == false){
					scouter.stop();
					scouterFleeing = false;
				}
				
			}
				
				// end of myUnits loop

			}
			
			if(TeamGameMode == false){

				for (Unit EnemyUnits1 : game.enemy().getUnits()) {
					
					boolean isMil = IsMilitrayUnit(EnemyUnits1);
					if(EnemyUnits1.isCloaked() || EnemyUnits1.isBurrowed()){
						if (EnemyUnits1.isDetected() == false && EnemyUnits1.isAttacking() == true
							&& isMil == true && HasUnitsNearbyToCombat(EnemyUnits1) == true ) {
							for (Unit detectors : myComSats) {
								if (detectors.getType() == UnitType.Terran_Comsat_Station && detectors.getEnergy() >= 50) {
									detectors.useTech(TechType.Scanner_Sweep, EnemyUnits1.getPosition());
									break;
								}
							}
						}
					}
					
					if (EnemyUnits1.getType().isBuilding() && EnemyUnits1.isLifted() == false) {
						if (!enemyBuildingMemory.contains(EnemyUnits1.getPosition())) {
							enemyBuildingMemory.add(EnemyUnits1.getPosition());
						}
					}
					
				
					if(EnemyUnits1.isFlying() && isMil == true && enemyFlyers.contains(EnemyUnits1) == false){
						enemyFlyers.add(EnemyUnits1);
						int score = getScoreOf(EnemyUnits1);
						if(Armor == 0 && Factories > 0 && pBuildings.contains(UnitType.Terran_Armory) == false){
							pBuildings.add(UnitType.Terran_Armory);
							pPosition.add(null);
						}
						if(Armor > 0 && EnemyUnits1.getType().groundWeapon().damageAmount() > 0){
						UnitType auxType = UnitType.Terran_Goliath;
						int amount = ((auxType.destroyScore() * auxType.maxHitPoints()) / (auxType.maxHitPoints() * 2) / score) + 1;
						//game.sendText("AIR UNITS DETECTED, ADDING " + amount + " Of Golaiths to counter: " + EnemyUnits1.getType().toString());
						int i;
						for (i = 0; i < amount; i++) { 
							pUnits.add(UnitType.Terran_Goliath);
						}
						
						}
					}		
						
					if(EnemyUnits1.isVisible() == true){
						String name = GetCustomName(EnemyUnits1.getType());
						game.drawTextMap(EnemyUnits1.getPosition(), name);
					}
					
	
				}
			}
			else {
			if(DoneCreatingList == true && playerBuildings.isEmpty() == false){
					for (Player P : game.enemies()) {
						for (Unit EnemyUnits1 : P.getUnits()) {
							if (EnemyUnits1.getType().isBuilding() && EnemyUnits1.isLifted() == false) {
									int index = EnemyUnits1.getPlayer().getID();
									//System.out.println("Index is: " + index);
									ArrayList<Position> list = playerBuildings.get(index);
									if(list != null){
										if(list.contains(EnemyUnits1.getPosition()) == false){
											list.add(EnemyUnits1.getPosition());
											playerBuildings.put(index, list);
										}
									}
									else {
										//System.out.println("List for player: " + EnemyUnits1.getPlayer().getName() + " is null");
									}
								
								if (!enemyBuildingMemory.contains(EnemyUnits1.getPosition())) {
									enemyBuildingMemory.add(EnemyUnits1.getPosition());
									scoutedLocations.clear();
								}
							}
							
							boolean isMil = IsMilitrayUnit(EnemyUnits1);
							if(EnemyUnits1.isCloaked() || EnemyUnits1.isBurrowed()){
								if (EnemyUnits1.isDetected() == false && EnemyUnits1.isAttacking() == true
									&& isMil == true && HasUnitsNearbyToCombat(EnemyUnits1) == true ) {
									for (Unit detectors : myComSats) {
										if (detectors.getType() == UnitType.Terran_Comsat_Station && detectors.getEnergy() >= 50) {
											detectors.useTech(TechType.Scanner_Sweep, EnemyUnits1.getPosition());
											break;
										}
									}
								}
							}
						}
					}
				}
			}
			
			for(Region region : myRegions){
				game.drawCircleMap(region.getCenter(), 10, bwapi.Color.White);
			}


			if(TeamGameMode == true){
				if(DoneCreatingList == true && playerBuildings.isEmpty() == false){
					for(Player pp : game.enemies()){
						int index1 = pp.getID();
						ArrayList<Position> items = playerBuildings.get(index1);
						for (Position p : items) {
							// compute the TilePosition corresponding to our remembered
							// Position p
							TilePosition tileCorrespondingToP = new TilePosition(p.getX() / 32, p.getY() / 32);
			
							// if that tile is currently visible to us...
							if (game.isVisible(tileCorrespondingToP)) {
			
								// loop over all the visible enemy buildings and find out if
								// at least
								// one of them is still at that remembered position
								boolean buildingStillThere = false;
								for (Unit u : pp.getUnits()) {
									if ((u.getType().isBuilding()) && (u.getPosition().equals(p) )) {
										buildingStillThere = true;
										break;
									}
								}
			
								// if there is no more any building, remove that position
								// from our memory
								if (buildingStillThere == false) {
									int index = enemyBuildingMemory.indexOf(p);
									items.remove(index);
									game.sendText("Building not there for player: " + pp.getName() +  " removing: " + p.toString());
									break;
								}
							}
						}
						playerBuildings.put(index1, items);
					}
				}
			}
			else {
				for (Position p : enemyBuildingMemory) {
					// compute the TilePosition corresponding to our remembered
					// Position p
					TilePosition tileCorrespondingToP = new TilePosition(p.getX() / 32, p.getY() / 32);
	
					// if that tile is currently visible to us...
					if (game.isVisible(tileCorrespondingToP)) {
	
						// loop over all the visible enemy buildings and find out if
						// at least
						// one of them is still at that remembered position
					boolean buildingStillThere = false;
				for (Unit u : game.enemy().getUnits()) {
							if ((u.getType().isBuilding()) && (u.getPosition().equals(p))) {
								buildingStillThere = true;
								break;
						}
						}
	
						// if there is no more any building, remove that position
						// from our memory
						if (buildingStillThere == false) {
							int index = enemyBuildingMemory.indexOf(p);
						enemyBuildingMemory.remove(index);
							game.sendText("Building not there, removing: " + p.toString());
							break;
						}
					}
				}
			}
			
			// expanding script
			// expanding
			// income < needs

	if(TeamGameMode == false){
		if (HasScoutUnit == true && ScoutSent == true && scouter.isMoving() == false) {
			if(enemyBuildingMemory.isEmpty() == true){
			scouter.move(self.getStartLocation().toPosition());
			for (Base b : bewb.getMap().getBases()) {
				if (b.isStartingLocation() && game.isExplored(b.getLocation()) == false) {
					scouter.move(b.getCenter());
					break;
				}
		}
	}
	else{
		for(Position pos : enemyBuildingMemory){
				scouter.move(pos, true);
				break;
		}
	}
		
			
			
		}
	}
	// if we are in a FFA
	else {
		if (HasScoutUnit == true && ScoutSent == true && scouter.isMoving() == false) {
			boolean found = false;
			for (Base b : bewb.getMap().getBases()) {
				if (b.isStartingLocation() && game.isExplored(b.getLocation()) == false) {
					found = true;
					scouter.move(b.getCenter());
					break;
				}
		}
			if(found == false){
				for (Base b : bewb.getMap().getBases()) {
					if (b.isStartingLocation() && game.isExplored(b.getLocation()) == false) {
						found = true;
						scouter.move(b.getCenter());
						break;
					}
				}
			}
			
		}
	}
	
		if(ExpandPos != null){
			if (ExpandPos.isValid(game)) {
				game.drawCircleMap(ExpandPos.toPosition(), 25, bwapi.Color.Cyan, false);
			}
		}
		
		if(Commander != null){
			if (Commander.exists()) {
				game.drawCircleMap(Commander.getPosition(), 25, bwapi.Color.Orange, false);
			}
		}
		
		if(playerScores.isEmpty() == false|| playerBuildings.isEmpty() == false ){
		game.drawTextScreen(100, 90, sPlayerScores.toString());
		game.drawTextScreen(300, 90, sPlayerSize.toString());
		}
		
	}
//}
	
	
	
	

	// end of lat frames


	// Returns a suitable TilePosition to build a given building type near
	// specified TilePosition aroundTile, or null if not found. (builder
	// parameter is our worker)
	public TilePosition getBuildTile(Unit builder, UnitType buildingType, TilePosition aroundTile) {
		TilePosition ret = null;
		int maxDist = 6;
		int stopDist = 150;
		// Refinery, Assimilator, Extractor
		if (buildingType.isRefinery()) {
			
			if(claimedGas.isEmpty() == false){
				for(Geyser unit : claimedGas){
						return unit.getUnit().getTilePosition();
				}
			}
			else {
			for (Unit n : game.neutral().getUnits()) {
				if ((n.getType() == UnitType.Resource_Vespene_Geyser)
						&& (Math.abs(n.getTilePosition().getX() - aroundTile.getX()) < stopDist)
						&& (Math.abs(n.getTilePosition().getY() - aroundTile.getY()) < stopDist))
					return n.getTilePosition();
			}
		}
	}

		if (buildingType.isResourceDepot()) {
			maxDist = 1;
		}



		while ((maxDist < stopDist) && (ret == null)) {
			for (int x = aroundTile.getX() - maxDist; x <= aroundTile.getX() + maxDist; x++) {
				for (int y = aroundTile.getY() - maxDist; y <= aroundTile.getY() + maxDist; y++) {
					if (game.canBuildHere(new TilePosition(x, y), buildingType, builder, false)) {
						// units that are blocking the tile
						boolean unitsInWay = false;
						for (Unit u : game.getAllUnits()) {
							if (u.getID() == builder.getID())
								continue;
							if ((Math.abs(u.getTilePosition().getX() - x) < 4)
									&& (Math.abs(u.getTilePosition().getY() - y) < 4)) {
								unitsInWay = true;
								NewBuild = new TilePosition(x, y);
							}
						}
						if (!unitsInWay && NewBuild != LastBuild && game.isVisible(new TilePosition(x, y))) {
							LastBuild = new TilePosition(x, y);
							buildingName = buildingType;
							return new TilePosition(x, y);

						}
			
					}
				}
			}
			maxDist += 6;
		}
		game.sendText("Something went wrong, returning null for build");
		return null;

	}
	
	
	public TilePosition getDefendableTile(Unit builder, UnitType buildingType, TilePosition aroundTile) {
		TilePosition ret = null;
		int maxDist = 1;
		int stopDist = 10;
		// Refinery, Assimilator, Extractor
		if (buildingType.isRefinery()) {
			for (Unit n : game.neutral().getUnits()) {
				if ((n.getType() == UnitType.Resource_Vespene_Geyser)
						&& (Math.abs(n.getTilePosition().getX() - aroundTile.getX()) < stopDist)
						&& (Math.abs(n.getTilePosition().getY() - aroundTile.getY()) < stopDist))
					return n.getTilePosition();
			}
		}

		while ((maxDist < stopDist) && (ret == null)) {
			for (int x = aroundTile.getX() - maxDist; x <= aroundTile.getX() + maxDist; x++) {
				for (int y = aroundTile.getY() - maxDist; y <= aroundTile.getY() + maxDist; y++) {
					if (game.canBuildHere(new TilePosition(x, y), buildingType, builder, false)) {
						// units that are blocking the tile
						boolean unitsInWay = false;
						for (Unit u : game.getAllUnits()) {
							if (u.getID() == builder.getID())
								continue;
							if ((Math.abs(u.getTilePosition().getX() - x) < 4)
									&& (Math.abs(u.getTilePosition().getY() - y) < 4)) {
								unitsInWay = true;
								NewBuild = new TilePosition(x, y);
							}
						}
						if (!unitsInWay && NewBuild != LastBuild && game.isVisible(new TilePosition(x, y))) {
							LastBuild = new TilePosition(x, y);
							buildingName = buildingType;
							return new TilePosition(x, y);

						}
			
					}
				}
			}
		}
		game.sendText("Something went wrong, returning null for build");
		return null;

	}
	
	
	public TilePosition getLandLocation(Unit building, TilePosition aroundTile) {
		TilePosition ret = null;
		int LastX1 = building.getPosition().x;
		int LastY1 = building.getPosition().y;
		int maxDist = 10;
		int stopDist = 600;
		
		while ((maxDist < stopDist) && (ret == null)) {
			for (LastX1 = aroundTile.getX() - maxDist; LastX1 <= aroundTile.getX() + maxDist; LastX1++) {
				for (LastY1 = aroundTile.getY() - maxDist; LastY1 <= aroundTile.getY() + maxDist; LastY1++) {
					if (building.canLand(new TilePosition(LastX1, LastY1)) == true) {
						return new TilePosition(LastX1, LastY1);
					}
				}

			}

		}
		return ret;
	}

	public TilePosition NextExpand(Unit builder) {
		boolean hasLocation = false;
		int stopdist = 5000;
		int dist = 0;
		int i = 0;
		int max = bewb.getMap().getBases().size();
		boolean alreadyTaken;
		while (hasLocation == false && dist < stopdist) {
			dist = dist + 200;
				for (Base Expand : bewb.getMap().getBases()) {
					alreadyTaken = false;
					int AmountofBases = bewb.getMap().getBases().size();
					int tree = BasePos.getApproxDistance(Expand.getCenter());
					if (Expand.getCenter().getApproxDistance(BasePos) < dist
							&& Expand.getCenter().getApproxDistance(BasePos) > 50
							&& claimedBaseLocations.contains(Expand) == false) {
						if (Bases > 1 && lastExpandPos.toPosition().getApproxDistance(Expand.getCenter()) > 150) {
							hasLocation = true;
							claimedBaseLocations.add(Expand);
							ExpandPos = Expand.getLocation();
							return Expand.getLocation();
						}
						if (Bases == 1 && alreadyTaken == false) {
							hasLocation = true;
							claimedBaseLocations.add(Expand);
							ExpandPos = Expand.getLocation();
							return Expand.getLocation();
						}
	
						if (dist >= stopdist) {
							ValidBasesClaimed = true;
							expanding = false;
							game.sendText("Yeah, Straight from the top of my dome ran outta of places to drop a CC and call home");
							saving = false;
							return null;
						}
					}
	
				}

		}
		ValidBasesClaimed = true;
		expanding = false;
		needsToExpand = false;
		buildwait = false;
		saving = false;
		return null;
	}
	
	
	public Position GetBunkerChoke(){
		int dist = 0;
		Base nextBase = null;
		while (nextBase == null) {
			dist = dist + 200;
			for (Base Expand : bewb.getMap().getBases()) {
				int AmountofBases = bewb.getMap().getBases().size();
				int tree = BasePos.getApproxDistance(Expand.getCenter());
					if (Expand.getCenter().getApproxDistance(BasePos) < dist
					&& Expand.getCenter().getApproxDistance(BasePos) > 50) {
						nextBase = Expand;
					}
			}
		}
				if(nextBase != null){
					Area bregion = nextBase.getArea();
					int lowest = 0;
					ChokePoint chosen = null;
					for(ChokePoint chokes : bregion.getChokePoints()){
							int distt = 0;
							int thiss = 0;
							int amount = 0;
							for(Base bases : bewb.getMap().getBases()){
								if(bases.isStartingLocation() == true){
									int dsfdsf = chokes.getCenter().toPosition().getApproxDistance(bases.getCenter());
									distt = distt + dsfdsf;
									amount++;
								}
							}
							thiss = distt / amount;
							if(thiss < lowest || chosen == null){
								lowest = thiss;
								chosen = chokes;
							}
						
						
						if(chosen != null){
						return chosen.getCenter().toPosition();
						}
						else {
							return null;
						}
					
					}
				}
				return null;


	}
	
	
	public ChokePoint GetMainChoke(){
		Area bregion = bewb.getMap().getArea(self.getStartLocation());
		int lowest = 0;
		ChokePoint chosen = null;
		for(ChokePoint chokes : bregion.getChokePoints()){
			int distt = 0;
			int thiss = 0;
			int amount = 0;
			for(Base bases : bewb.getMap().getBases()){
				if(bases.isStartingLocation() == true){
					int dsfdsf = chokes.getCenter().toPosition().getApproxDistance(bases.getCenter());
					distt = distt + dsfdsf;
					amount++;
				}
			}
			thiss = distt / amount;
			if(thiss < lowest || chosen == null){
				lowest = thiss;
				chosen = chokes;
			}
		}
		
		if(chosen != null){
		return chosen;
		}
		else {
			return null;
		}
		

	}


	public Unit GetWorker() {
		for (Unit unit : myWorkers) {
			if (unit.getType() == UnitType.Terran_SCV && unit.isGatheringMinerals() == true
					&& unit.isConstructing() == false && unit.isMoving() == false
					&& unit.isRepairing() == false && unit.isTraining() == false) {
				if(unit.getID() != scoutID){
				return unit;
				}
			}
		}
		//System.out.println("Get Worker returning null");
		return null;
		

	}
	
	public ArrayList<Unit> GetWorkerAmount(int amount) {
		int i = 0;
		ArrayList<Unit> workers = new ArrayList<Unit>();
		for (Unit unit : myWorkers) {
			if(i <= amount){
				if (unit.getType() == UnitType.Terran_SCV && unit.isGatheringMinerals() == true && workers.contains(unit) == false && unit.getID() != scoutID) {
						i++;
						workers.add(unit);
				}
			}
		}
		if(workers.size() >= 3){
		return workers;
		}
		else {
		return null;
		}
		

	}


	public Position GetJukePos2(Unit unit, Unit targett){
		if(targett != null){
			int unitx = unit.getPosition().x;
			int unity = unit.getPosition().y;
			int targetx = targett.getPosition().x;
			int targety = targett.getPosition().y;
			int finalx = unitx + unitx - targetx;
			int finaly = unity + unity - targetx;
			Position Final = new Position(finalx, finaly);
			if(Final.isValid(game) == true){
				return Final;
			}
		return null;
		}
		
		return null;
	}
	
	public Position GetJukePosManual(Unit unit, int enemyx, int enemyy){
		int unitx = unit.getPosition().x;
		int unity = unit.getPosition().y;
		int targetx = enemyx;
		int targety = enemyy;
		int finalx = unitx + unitx - targetx;
		int finaly = unity + unity - targetx;
		Position Final = new Position(finalx, finaly);
			if(Final.isValid(game) == true){
				return Final;
			}
		return null;

	}
	
	public Position RunFromPos(Unit unit, Position pos){
		int unitx = unit.getPosition().x;
		int unity = unit.getPosition().y;
		int targetx = pos.getX();
		int targety = pos.getY();
		int finalx = unitx + unitx - targetx;
		int finaly = unity + unity - targetx;
		Position Final = new Position(finalx, finaly);
		if(Final.isValid(game) == true){
			return Final;
		}
		return null;
	}
	
	

	public boolean IsMilitrayUnit(Unit unit) {
		if(unit == null){
			System.out.println("Unit is Null");
		}
		else {
			int Damage = unit.getType().groundWeapon().damageAmount() + unit.getType().airWeapon().damageAmount();
			if(Damage > 0 && unit.getType().isWorker() == false && unit.getType().isBuilding() == false && unit.getType().isSpell() == false){
				return true;
			}
		return false;
		}
	return false;
	}
		
		public boolean IsMilitrayBuilding(Unit unit) {
			if(unit.getType() == UnitType.Terran_Bunker ||
			unit.getType() == UnitType.Terran_Missile_Turret ||
			unit.getType() == UnitType.Zerg_Sunken_Colony ||
			unit.getType() == UnitType.Zerg_Spore_Colony ||
			unit.getType() == UnitType.Protoss_Photon_Cannon){
				return true;
			}
		

		return false;

	}

	public String GetStrategy(String enemyRace) {
		boolean hasStrategy = false;
		
		if(enemyRace == "Zerg"){
			//game.printf("Bio");
			return "Bio";
			
		}
		
		if(enemyRace == "Protoss"){
			Random rand = new Random();
			int n = rand.nextInt(3) + 1;
			if(n == 4){
				//game.printf("Bio");
				return "Bio";
				
			}
			else {
				//game.printf("Mech");
				return "Mech";
			}
				
			
		}
		
		if(enemyRace == "Terran"){
			Random rand = new Random();
			int n = rand.nextInt(3) + 1;
			
			if(n == 4){
				//game.printf("Bio");
				return "Bio";
			}
			else {
				//game.printf("Mech");
				return "Mech";
			}
				
			
		}
		
		return null;
	}
	
	public void GlobalRetreat(){
		
		for(Unit i: myUnits){
			if(ralleyPoint != null){
				if(i.getPosition().getApproxDistance(ralleyPoint) > 300){
				i.move(ralleyPoint);
				}
			}
		}
		
		for(Unit i : self.getUnits()){
			if(IsMilitrayUnit(i) == true && i.isLoaded() == false && i.isLockedDown() == false && i.isMaelstrommed() == false)
				if(ralleyPoint != null){
					if(i.getPosition().getApproxDistance(ralleyPoint) > 300){
					i.move(ralleyPoint);
					}
				}
			}
		
		for(Unit i : myMedics){
			if(ralleyPoint != null){
				if(i.getPosition().getApproxDistance(ralleyPoint) > 300){
				i.move(ralleyPoint);
				}
			}
		}
		
		
			
		}
	
	

	
	public boolean IsAttackMoving(Unit unit){
		if(unit.getOrder() == Order.AttackMove){
			return true;
		}
		return false;
	}
	
	public boolean IsABusy(Unit unit){
		if(unit.getOrder() == Order.AttackMove || unit.isAttacking() == true){
			return true;
		}
		return false;
	}
	
	public boolean orderStacking(Unit unit, Position pos){
		if(unit.getOrderTargetPosition() == pos){
			return true;
		}
		
		if(unit.getPosition().getApproxDistance(pos) < 75){
			return true;
		}
		
		return false;
	}
	
	
	public void DefenceCall(Unit victim){
		ArrayList<Unit> ass2 = GetEnemyUnitsNearby(victim.getPosition(), 250, true);
		boolean canWin = false;
		
		if(simCallFrames == 0){
		canWin = jFaplocal(myUnits, ass2, 1);
		simCallFrames = 1;
		}
		else {
			canWin = ScoreFap(myUnits, ass2, 1);
		}
		
		//System.out.println("Can Win: " + canWin);
		//TODO this whole thing
		//grabbing the score of invaders and sending a squad of units against it with similar or greater score
		
		if(fapMyScores < InvadersScore && victim != scouter && canWin == false && victim.getPosition().getApproxDistance(self.getStartLocation().toPosition()) < 1000 && totalFrames < 9000){
			PullTheBoys(victim.getPosition());
			game.sendText("PULL THE BOYS");
		}
		
			if(defenderCall == true && canWin == true){
			defenceCallFrames = 1;
			defenderCall = false;
			
			//System.out.println("Victim script triggered at: " + totalFrames);
			if(victim.getType().isWorker() == true && victim.getID() != scoutID && canWin == true){
				for(Unit defenders : myUnits){
					boolean isMil = IsMilitrayUnit(defenders);
					int dist = defenders.getDistance(victim);
					if(defenders.isAttacking() == false && dist > 100){
						boolean stacking = orderStacking(defenders, victim.getPosition());
						if(stacking == false){
							defenders.attack(victim.getPosition());
						}
					}
				}
			}
			
			if(victim.getType().isBuilding() == true && canWin == true){
				for(Unit defenders : myUnits){
					boolean isMil = IsMilitrayUnit(defenders);
					int dist = defenders.getDistance(victim);
					if(defenders.isAttacking() == false && dist > 100){
						boolean stacking = orderStacking(defenders, victim.getPosition());
						if(stacking == false){
							defenders.attack(victim.getPosition());
						}
					}
				}
			}
			
			if(victim.getType().isWorker() == false && victim.getType().isBuilding() == false && canWin == true){
				ArrayList<Unit> meinUnits = new ArrayList<Unit>();
				ArrayList<Unit> meinNichtUnits = new ArrayList<Unit>();
				for(Unit units : getUnitsInRadius(victim.getPosition(), 240)){
					boolean isMil = IsMilitrayUnit(units);
					if(units.getPlayer() == self && isMil == true && meinUnits.contains(units) == false){
						meinUnits.add(units);
					}
					
					if(units.getPlayer().isEnemy(self) && isMil == true && meinNichtUnits.contains(units) == false){
						meinNichtUnits.add(units);
					}
				}
				boolean sdf = jFaplocal(meinUnits, meinNichtUnits, 1);
				if(sdf == true){
					for(Unit unit1 : meinUnits){
						int dist = unit1.getDistance(victim);
						if(unit1.isAttacking() == false && dist > 100 && unit1.isUnderStorm() == false){
							boolean stacking = orderStacking(unit1, victim.getPosition());
							if(stacking == false){
								unit1.attack(victim.getPosition());
							}
						}
					}
				}
			}
			
			
			// end of defending. 
			
		}
		
}
	

public void UpdateStrats(){
	int scan = getTotalScanEnergy();
	UpdateMyScore();
	boolean shouldEarlyAttack = false;
	int total = 0;	
	boolean CanWin = CanWinAttack();
	boolean picked = false;

	Strats = "Justifying War Goals";
	boolean canExpand = CanExpand();
	boolean shouldregroup = false;
	if(enemyBuildingMemory.isEmpty() == false){
	shouldregroup = ShouldRegroup(enemyBuildingMemory.get(0));
	//System.out.println("ShouldRegroup: " + shouldregroup);
	}
	

	if(TeamGameMode == false){
		int dist = AvergeDistanceToRalleyPoint();
		
		if(dist > 2000 && CanWin == true && wasAttacking == false){
			shouldregroup = true;
		}
		
	if(fapMyScores > InvadersScore && InvadersScore > 0){
		Strats = "Defending the Motherland";
	}
		

	if(CanWin == true && shouldregroup == false){
		
			UnitType type = NextTechGoal();
			
			if(type != null){
				if(type == UnitType.Terran_Bunker || type == UnitType.Terran_Missile_Turret){
					if(pBuildings.contains(type) == false){
					pBuildings.add(type);
					pPosition.add(null);
					}
				}
				if(fapMyScores >= InvadersScore){
					if(pBuildings.contains(type) == false){
					pBuildings.add(type);
					pPosition.add(null);
					}
				}
			}

				
			if(InvadersScore < fapMyScores){
				attacking = true;
				wasAttacking = true;
				picked = true;
				Strats = "Full Attack";
				GlobalAttack();
			}
			

		}
		else if(shouldregroup == true) {
			attacking = false;
			wasAttacking = false;
			picked = true;
			Strats = "Regrouping main army";
				 GlobalRetreat();	
		}
		else if(shouldregroup == false){
			attacking = false;
			wasAttacking = false;
			picked = true;
			Strats = "Waiting for more strength";
				 GlobalRetreat();
		}
		
	}
	else {
	
		int target = GetPlayerTarget();
		if(target != 10){
		boolean shouldregroup1 = false;
		if(playerBuildings.get(target).isEmpty() == false){
		shouldregroup1 = ShouldRegroup(playerBuildings.get(target).get(0));
		//System.out.println("Should Regroup: " + shouldregroup1);
		}
		
		String names = game.getPlayer(target).getName();
		//System.out.println("Targetted player is: " + names);
		//if we have a target in ffa

		TeamModeTargetPlayer = target;
		if(InvadersScore == 0){
			attacking = true;
			Strats = "Full Attack";
			picked = true;
			GlobalAttack();
		}
		else if(shouldregroup1 == true) {
			attacking = false;
			picked = true;
			Strats = "Regrouping main army";
			 GlobalRetreat();
		}
		else if(shouldregroup1 == false) {
			attacking = false;
			picked = true;
			Strats = "Waiting for more army strength";
			 GlobalRetreat();
		}

		
		}
		else {
			attacking = false;
			picked = true;
			Strats = "Waiting for more army strength";
			 GlobalRetreat();
		}
		
		
	}
	
	
}

public void LocalAttack(Position pos, int radius){
	
}



public boolean CanWin(){
	
	if(fapMyScores > (estimatedEnemyScore + enemyDefenceScore)){
		return true;
	}
	return false;
}

public boolean CanWinAttack(){
	int threshold = (int) Math.round(estimatedEnemyScore * 0.95);
	int i = 0;
	boolean usingInts = ShouldSimUnits();
	
	if(usingInts == true){
		if(jFapGlobal() == true){
			return true;
		}
	}
	
	if(enemyUnits.isEmpty() == false){
		for(Unit units : enemyUnits){
			if(units.isVisible() == true){
				i = i + getScoreOf(units);
			}
			
			if(i >= threshold){
				return true;
			}	
		}
		
		if(i >= threshold){
			return true;
		}
	}
	
	
	if(fapMyScores > (estimatedEnemyScore + enemyDefenceScore + enemyGhostPoints)){
		return true;
	}
	return false;
}


public void ThinkUnit(Unit myUnit){
	
	if(myUnit.isUnderStorm() && myUnit.getPosition().getApproxDistance(ralleyPoint) > 300){
		if(ralleyPoint != null){
			myUnit.move(ralleyPoint);
		}
	}

	if (myUnit.getType() == UnitType.Terran_Marine && myUnit.canUseTechPosition(TechType.Stim_Packs) == true && myUnit.isStimmed() == false && myUnit.getHitPoints() > 30) {
		myUnit.useTech(TechType.Stim_Packs);
	}
	
	if (myUnit.getType() == UnitType.Terran_Siege_Tank_Tank_Mode && myUnit.canUseTechPosition(TechType.Tank_Siege_Mode) == true) {
		myUnit.useTech(TechType.Tank_Siege_Mode);
	}
	
	if (myUnit.getType() == UnitType.Terran_Firebat && myUnit.canUseTechPosition(TechType.Stim_Packs) == true && myUnit.isStimmed() == false) {
		myUnit.useTech(TechType.Stim_Packs);
	}
	
	if (myUnit.getType() == UnitType.Terran_Vulture && myUnit.isAttacking() == true && myUnit.getSpiderMineCount() != 0) {
		Unit unit = myUnit;
		if (myUnit.canUseTech(TechType.Spider_Mines, myUnit.getPosition()) == true
				&& myUnit.getLastCommand() != UnitCommand.useTech(unit, TechType.Spider_Mines)) {
			Position lastorder = unit.getTargetPosition();
			unit.useTech(TechType.Spider_Mines, unit.getPosition());
			unit.attack(lastorder, true);
		}

	}
	
//	if(myUnit.isAttacking() == true && myUnit.getType() == UnitType.Terran_Marine && myUnit.getOrder() == Order.AttackUnit){
//		//System.out.println("Trigger");
//		Unit target = myUnit.getTarget();
//		UnitType type = null;
//		if(target != null && target.exists() == true){
//		type = target.getType();
//		}
//		if(jukers.containsKey(myUnit) == false){
//			jukers.put(myUnit, new ArrayList<Unit>());
//		}
//		
//		if(type != null){
//			if(type == UnitType.Protoss_Zealot && ShouldJuke(myUnit.getPosition()) == true){
//				if(jukers.containsKey(myUnit) == true){
//					ArrayList<Unit> list = jukers.get(myUnit);
//					if(list.contains(target) == false){
//						list.add(target);
//					}
//					jukers.put(myUnit, list);
//				}
//			}
//		}
//		
//		
//	}
	
	
}



private boolean ShouldJuke(Position position) {
	int allies = GetMyUnitsNearby(position, 300, false).size();
	int enemies = GetEnemyUnitsNearby(position, 300, false).size();
	if(allies >= enemies * 6 + 1){
		return true;
	}
	return false;
}


public boolean CanExpand(){
	// TODO Yolo expands. Expanding even if enemy score is greater then mine if needs > income * 0.20 (20% of my income)
	// also TODO is a fancy color in eclipse =D
	// Stop Ctrl-f'ing my code Hannes
	// nuke nuke hannes bregberg nuke more nukes ghost stuff bio bot
	if(enemyBuildingMemory.isEmpty() == true && totalFrames < 7500){
		// don't expand without intell
		return false;
	}
	
	if(estimatedEnemyScore == 0 && enemyDefenceScore > 600){
		// if the enemy is turtling hard
		return true;
	}
	
	if((estimatedEnemyScore * 4 + 300) < enemyDefenceScore){
		// if the enemy is turtling hard
		return true;
	}
	
	if(needs >= income * 2){
		// if we need to badly expand
		return true;
	}
	
	if(HowManyDoIHave(UnitType.Terran_Academy) == 0){
		// don't expand unless you have an academy to deal with zealots because they are OP.
		return false;
	}
	
	
	if(TeamGameMode == false){
		int expandscore = InvadersScore + Math.round(estimatedEnemyScore / 2) + enemyGhostPoints;
		//System.out.println("Expand score: " + expandscore);
		if(fapMyScores >= expandscore){
			return true;
		}
		else{
			return false;
		}
	}
	else {
		int highest = GetHighestPlayerScore();
		if(fapMyScores > InvadersScore + Math.round(highest / 1.5)){
			return true;
		}
		return false;
	}

}

public boolean jFapDefenceCheck(Position pos){
	JFAP simulator = new JFAP(game);
	simulator.clear();
	boolean needsScan = false;
	
	for (Unit targets : getUnitsInRadius(pos, 200)) {
		int damage = targets.getType().groundWeapon().damageAmount() + targets.getType().airWeapon().damageAmount();
		
		if (targets.getPlayer().isEnemy(self) && IsMilitrayUnit(targets) == true) {
			simulator.addUnitPlayer2(new JFAPUnit(targets));
			if(targets.isDetected() == false && targets.getType() == UnitType.Zerg_Lurker || targets.getType() == UnitType.Protoss_Dark_Templar){
				needsScan = true;
			}
		}

		if(targets.getPlayer().isAlly(self) && targets.getType() == UnitType.Terran_Bunker && targets.getLoadedUnits().size() > 0 ){
			simulator.addUnitPlayer1(new JFAPUnit(targets));
		}
	}
	
	for(Unit unit : myUnits){
		simulator.addUnitPlayer1(new JFAPUnit(unit));
	}
	
	
	
	Pair<Integer, Integer> preSimScores = simulator.playerScores();
	int preSimFriendlyUnitCount = simulator.getState().getKey().size();
	simulator.simulate(50);
	int isMain = (int) (fapMyScores * 0.65);
	//is 65% or more of my army in this battle?
	if(preSimScores.getKey() > isMain){
		if(preSimScores.getKey() < preSimScores.getValue()){
			//and if we can't win
			if(Strats == "Full Attack"){
					Strats = "Regrouping Due to major calculated loss";
					GlobalRetreat();
			}
		}
	}
	//int regionbonus = (game.getRegionAt(pos).getDefensePriority() / 3);
	

	Pair<Integer, Integer> postSimScores = simulator.playerScores();
	int postSimFriendlyUnitCount = simulator.getState().getKey().size();
	int myLosses = preSimFriendlyUnitCount - postSimFriendlyUnitCount;
	int myScoreDiff = preSimScores.getKey() - postSimScores.getKey();
	int enemyScoreDiff = preSimScores.getKey() - postSimScores.getValue();
	//System.out.println("Me : " + myScoreDiff);
	//System.out.println("Enemy : " + enemyScoreDiff);
	// if we can win
	
	if(needsScan == true && getTotalScanEnergy() < 50){
		return false;
	}
	
	if (postSimScores.getKey() > postSimScores.getValue()) {
		return true;
	} 
	else {
		return false;
	}
	


}


public void Conscript(){
	
//	for(Unit bunkers : myBunkers){
//		if(bunkers.getType() == UnitType.Terran_Bunker && bunkers.getLoadedUnits().size() > 0){
//			for(Unit loaded : bunkers.getLoadedUnits()){
//				if(bunkerUnits.contains(loaded) == false){
//					bunkerUnits.add(loaded);
//				}
//			}
//			
//			
//			
//		}
//	}
//
//	
//	for(Unit units : self.getUnits()){
//		boolean isMilitray2 = IsMilitrayUnit(units);
//		if(bunkerUnits.isEmpty() == false && bunkerUnits.contains(units) == false && isMilitray2 == true && myUnits.contains(units) == false){
//			myUnits.add(units);
//			System.out.println("Added unit: " + units.getType().toString() + " To active unit pool");
//		}
//		
//
//	}
	

}


public void PreAttackScan(){
	int i = 1;
	int max = ScanLocations.size();
		Position nextScan = ScanLocations.get(i);
		for (Unit detectors : myComSats) {
				if(max == 0){
					break;
				}
				int bonus = 0;
				i = i + 1;
				if (detectors.getType() == UnitType.Terran_Comsat_Station && detectors.getEnergy() >= 100) {
					detectors.useTech(TechType.Scanner_Sweep, nextScan);
					bonus = bonus + 50;
				}
				
				if(nextScan == null){
					break;
				}
				
				if(i >= max){
					break;
				}
				
				if(updateStrategy > 410){
					break;
				}
		}
	
}

public void PanicBuildMil(){
	for (Unit buildings : productionBuildings) {
		// best way to get the bot to build after its build
		// frozen is probably max the supply and let it spam.
		
		if (buildings.getType() == UnitType.Terran_Factory && game.canMake(UnitType.Terran_Siege_Tank_Tank_Mode) && buildings.isIdle() == true) {
			buildings.train(UnitType.Terran_Siege_Tank_Tank_Mode);
		}
		
		if (buildings.getType() == UnitType.Terran_Barracks && game.canMake(UnitType.Terran_Marine) && buildings.isIdle() == true) {
					buildings.train(UnitType.Terran_Marine);
		}

		if (buildings.getType() == UnitType.Terran_Starport && game.canMake(UnitType.Terran_Battlecruiser) == true && buildings.isIdle() == true) {
			buildings.train(UnitType.Terran_Battlecruiser);
		}
		
	}
}


public void PullTheBoys(Position pos){
	if(self.getStartLocation().toPosition().getApproxDistance(pos) < 2000){
		for(Unit unit : myWorkers){
			if(unit.isGatheringMinerals() == true && unit.isRepairing() == false){
			boolean isBusy = IsABusy(unit);
			if(isBusy == false){
				unit.attack(pos);
				}
			}
		}
	}
}

public int getTotalScanEnergy(){
	int fishguy4000 = 0;
	for(Unit unit: myComSats){
		fishguy4000 = fishguy4000 + unit.getEnergy();
	}
	return fishguy4000;
}


public int getGhostScore(Unit unit){

	
	if (unit.getType() == UnitType.Terran_Factory && unit.getPlayer().isEnemy(self)) {
		return 200;
	}
	
	if (unit.getType() == UnitType.Terran_Barracks && unit.getPlayer().isEnemy(self)) {
		return 100;
	}
	
	if (unit.getType() == UnitType.Protoss_Gateway && unit.getPlayer().isEnemy(self)) {
		return 150;
	}
	
	if (unit.getType() == UnitType.Zerg_Hatchery && unit.getPlayer().isEnemy(self)) {
		return 150;
	}
	
	return 0;
	
}

public int getScoreOf(Unit fu){
	// Stolen and modified slightly from jFap's code directly
	
	UnitType auxType = fu.getType();
	int health = fu.getHitPoints();
	int score = fu.getType().destroyScore();
	int maxHealth = fu.getType().maxHitPoints();
	
	if(auxType == UnitType.Terran_Medic || auxType == UnitType.Terran_Science_Facility){
		return 0;
	}
	
	if(auxType == UnitType.Terran_Marine){
		return ((auxType.destroyScore() * auxType.maxHitPoints()) / (auxType.maxHitPoints() * 2)) + 15;
	}
	return ((auxType.destroyScore() * auxType.maxHitPoints()) / (auxType.maxHitPoints() * 2));
}


public int amountOfGasHarvesters(Unit target){
	int eyes = 0;
	for(Unit unit : myWorkers){
		if(unit.isGatheringGas() == true && unit.getOrderTargetPosition() == target.getPosition()){
			eyes++;
		}
	}
	game.sendText("" + eyes);
	return eyes;
	
}


public boolean IsInChoke(Unit unit){
	
if(enemyChokes.contains(unit.getRegion()) == true){
	return true;
}
else {
	return false;
}

}


public void CheckBuilding(Unit unit){
	if(unit.getType() == UnitType.Zerg_Spire || unit.getType() == UnitType.Protoss_Stargate || unit.getType() == UnitType.Terran_Starport){
		if(Bays > 0){
		game.sendText("Air Unit production building detected, responsive unit: Missile Turret");
		pBuildings.add(UnitType.Terran_Missile_Turret);
		pPosition.add(null);;
		}
		else {
			pBuildings.add(UnitType.Terran_Engineering_Bay);
			pPosition.add(null);
			pBuildings.add(UnitType.Terran_Missile_Turret);
			pPosition.add(null);
			pBuildings.add(UnitType.Terran_Missile_Turret);
			pPosition.add(null);
			pBuildings.add(UnitType.Terran_Missile_Turret);
			pPosition.add(null);
		}
	}
	

	if(unit.getType() == UnitType.Protoss_Templar_Archives){
		if(Bays > 0){
		game.sendText("DT production building detected, responsive unit: Missile Turret");
		pBuildings.add(UnitType.Terran_Missile_Turret);
		pPosition.add(ralleyPoint.toTilePosition());
		}
		else {
			pBuildings.add(UnitType.Terran_Engineering_Bay);
			pPosition.add(null);
			pBuildings.add(UnitType.Terran_Missile_Turret);
			pPosition.add(ralleyPoint.toTilePosition());

		}


	}



	
	
}


public boolean isOrderToBuildOrBetter(UnitType type){
	int iHave = HowManyDoIHave(type);
	int ordered = HowManyOrderedToBuild(type);

	if(ordered > 0){
		return true;
	}
	
	return false;
}

public void PAddAmount(UnitType type, int i){
	int index = pBuildingsBuiltAmount.indexOf((pBuildingsBuilt.indexOf(type)));
	int amountInside = pBuildingsBuiltAmount.get(pBuildingsBuilt.indexOf(type));
	int needed = amountInside + i;
	pBuildingsBuiltAmount.add(index, needed);
}



public void CheckInvaders(){
	int i = 0;
	for(Unit unit : getUnitsInRadius(self.getStartLocation().toPosition(), 2000)){
		int bonus = getScoreOf(unit);
		boolean isMil = IsMilitrayUnit(unit);
		if(isMil == true && unit.getPlayer().isEnemy(self)){
			i = i + bonus;
		}
	}
	
//	
//	for(Unit unit : enemyUnits){
//		if(unit.isVisible() == true){
//			bwapi.Region rego = game.getRegionAt(unit.getOrderTargetPosition());
//			if(myRegions.contains(rego) == true){
//				int bonus = getScoreOf(unit);
//				i = i + bonus;
//				System.out.println("Attacker Detected");
//				
//			}
//			else {
//				//System.out.println("Not in one of my regions");
//			}
//		}
//	}
	
	InvadersScore = i;
}


public String GetCustomName(UnitType type){
	
	if(type == UnitType.Terran_Marine){
		return "Shooty Boy";
	}
	
	if(type == UnitType.Terran_Medic){
		return "Gamer Girl";
	}
	
	if(type == UnitType.Terran_Siege_Tank_Tank_Mode){
		return "Angry Rectangle";
	}
	
	if(type == UnitType.Terran_Siege_Tank_Siege_Mode){
		return "Slightly More Angrier Rectangle";
	}
	
	if(type == UnitType.Protoss_Zealot){
		return "Imba Unit";
	}
	
	if(type == UnitType.Protoss_Dragoon){
		return "Walking Cannon";
	}
	
	if(type == UnitType.Zerg_Zergling){
		return "Cute Dog";
	}
	
	if(type == UnitType.Zerg_Hydralisk){
		return "Very Angry Goat";
	}
	
	if(type == UnitType.Zerg_Mutalisk){
		return "Bin Chicken";
	}
	
	if(type == UnitType.Zerg_Lurker){
		return "Karambwan";
	}
	
	if(type == UnitType.Terran_Vulture){
		return "Skateboard";
	}
	
	if(type == UnitType.Terran_Firebat){
		return "Vietnam Flashbacks";
	}
	
	if(type == UnitType.Terran_Goliath){
		return "3 Marines Duct Taped to a Missile Turret";
	}
	
	if(type == UnitType.Protoss_High_Templar){
		return "Al Gore";
	}
		
	if(type == UnitType.Protoss_Archon){
		return "Super Saiyan gone super Saiyan";
	}
	
	if(type == UnitType.Zerg_Overlord){
		return "My sister";
	}
	
	return type.toString();
}


public Position ChooseDefendableArea(Unit unit) {
	return null;
	
}

public Unit GetAttacker(Unit myUnit, int radius){
	for(Unit unit : getUnitsInRadius(myUnit.getPosition(), radius)){
		if(unit.getOrderTarget() == myUnit || unit.getTarget() == myUnit){
			return unit;
		}
	}
	return null;
}

public void UpdateMyScore(){
	int total = 0;
	for(Unit unit : myUnits){
		if(bunkerUnits.contains(unit) == false || unit.isLoaded() == false){
			int score = getScoreOf(unit);
			total = total + score;
		}

	}
	fapMyScores = total;
}

public boolean ShouldMoveRalleyPoint(Position pos){
	
	if(pos.getApproxDistance(ralleyPoint) > 2000){
		return true;
	}
	
	return false;
}

public boolean BuildingAlreadyBeingOrdered(UnitType type, int amount){
int i = 0;
for(UnitType type1 : buildTypes){
	if(type1 == type){
		i++;
	}
}

if(i >= amount){
	return true;
}
	
return false;


}

public boolean HasBunkerNearRegroup(){
	for(Unit unit : getUnitsInRadius(ralleyPoint.toWalkPosition().toPosition(), 100)){
		if(unit.getType() == UnitType.Terran_Bunker && unit.getPlayer() == self){
			return true;
		}
	}
	
	return false;
}

public boolean BunkersCanAttack(Unit target){
	for(Unit unit : myBunkers){
		if(unit.getLoadedUnits().size() > 0 && unit.canAttack(target) == true){
			return true;
		}
	}
	return false;
}


public Unit GetNearestBunker(Position pos){
	int i = 0;
	Unit local = null;
	for(Unit unit : myBunkers){
		if(unit.getDistance(pos) <= i || i == 0){
			i = unit.getDistance(pos);
			local = unit;
		}
	}
	if(local != null){
		return local;
	}
	else {
		return null;
	}
	
}

public boolean jFaplocal(ArrayList<Unit> mine, ArrayList<Unit> enemy, int bonus){
	JFAP simulator = new JFAP(game);
	simulator.clear();
	boolean needscan = false;
	for(Unit unit : mine){
		simulator.addUnitPlayer1(new JFAPUnit(unit));
	}
	
	for(Unit unit : enemy){
		simulator.addUnitPlayer2(new JFAPUnit(unit));
		if(unit.getType() == UnitType.Zerg_Lurker || unit.getType() == UnitType.Protoss_Dark_Templar){
			needscan = true;
		}
	}
	

	Pair<Integer, Integer> preSimScores = simulator.playerScores();
	int preSimFriendlyUnitCount = simulator.getState().getKey().size();
	simulator.simulate(40);
	int isMain = (int) (fapMyScores * 0.65);
	//is 65% or more of my army in this battle?
	if(preSimScores.getKey() > isMain){
		if(preSimScores.getKey() < preSimScores.getValue()){
			//and if we can't win
			if(Strats == "Full Attack"){
					Strats = "Regrouping Due to major calculated loss";
					attacking = false;
					GlobalRetreat();
			}
		}
	}

	Pair<Integer, Integer> postSimScores = simulator.playerScores();
	int postSimFriendlyUnitCount = simulator.getState().getKey().size();
	int myLosses = preSimFriendlyUnitCount - postSimFriendlyUnitCount;
	int myScoreDiff = preSimScores.getKey() - postSimScores.getKey();
	int enemyScoreDiff = preSimScores.getValue() - postSimScores.getValue();
	//System.out.println("Me : " + myScoreDiff);
	//System.out.println("Enemy : " + enemyScoreDiff);
	// if we can win
	
	if(needscan == true && getTotalScanEnergy() < 50){
		return false;
	}
	
	if (postSimScores.getKey() > postSimScores.getValue() * bonus) {
		return true;
	} 
	else {
		return false;
	}
	
}

public boolean HasTechFor(UnitType type){
	if(type == UnitType.Terran_Goliath){
		if(Factories > 0 && Armor > 0){
			return true;
		}
	}
	if(type == UnitType.Terran_Marine){
		if(Racks > 0){
			return true;
		}
	}
	
	if(type == UnitType.Terran_Firebat){
		if(Racks > 0 && game.canMake(UnitType.Terran_Firebat) == true){
			return true;
		}
	}
	
	if(game.canMake(type) == true){
		return true;
	}
	
	return false;
}

public UnitType NextTechGoal(){
	// t = 1
	// p = 2
	// z = 3
	
		if(InvadersScore > 0){
			return null;
		}
		
		if(expanding == true){
			return null;
		}
		
		if(AcademyBuilt == false && buildTypes.contains(UnitType.Terran_Academy) == false && HowManyDoIHave(UnitType.Terran_Academy) == 0 && fapMyScores >= enemyRushScore && fapMyScores > 350){
			return UnitType.Terran_Academy;
		}
		
		if(Bays == 0 && buildTypes.contains(UnitType.Terran_Engineering_Bay) == false && HowManyDoIHave(UnitType.Terran_Engineering_Bay) == 0 && fapMyScores >= enemyRushScore){
			return UnitType.Terran_Engineering_Bay;
		}
		
		if(Factories == 0 && buildTypes.contains(UnitType.Terran_Factory) == false && HowManyDoIHave(UnitType.Terran_Factory) == 0 && Racks >= MaxRacks && fapMyScores >= enemyRushScore && Gases > 0 && fapMyScores > 1500){
			return UnitType.Terran_Factory;
		}
		
		if(Armor == 0 && buildTypes.contains(UnitType.Terran_Armory) == false && HowManyDoIHave(UnitType.Terran_Armory) == 0 && Factories > 0){
			return UnitType.Terran_Armory;
		}
		
		if(StarPorts == 0 && buildTypes.contains(UnitType.Terran_Starport) == false && HowManyDoIHave(UnitType.Terran_Starport) == 0 && fapMyScores > 1500 && Factories > 0){
			return UnitType.Terran_Starport;
		}
		
		if(TSF == 0 && buildTypes.contains(UnitType.Terran_Science_Facility) == false && HowManyDoIHave(UnitType.Terran_Science_Facility) == 0 && fapMyScores > 1500 && StarPorts > 0 ){
			return UnitType.Terran_Science_Facility;
		}
	
	
	return null;
}

public void RemoveAllButFirstTech(){
	if(pBuildings.size() != 1){
		for (int i = 0; i < pBuildings.size(); i++){
			if(i != 0){
				pBuildings.remove(i);
			}
		}
	}
}


public int HowManyDoIHave(UnitType type){
	int i = 0;
	for(Unit unit : self.getUnits()){
		if(unit.getType() == type){
			i++;
		}
	}
	
	return i;
}

public int HowManyDoIHaveCompleted(UnitType type){
	int i = 0;
	for(Unit unit : self.getUnits()){
		if(unit.getType() == type && unit.isCompleted() == true){
			i++;
		}
	}
	
	return i;
}

public int HowManyOrderedToBuild(UnitType type){
	int i = 0;
	
	if(pWorkers.isEmpty()){
		return 0;
	}
	
	for(Pair<Unit, UnitType> pear : pWorkers){
		if(pear.getValue() == type){
			i++;
		}
	}
	return i;
}

public int GetPlayerTarget() {
	for(Player p : game.enemies()){
		if(p.isDefeated() == false){
			int index = p.getID();
			int tscore = playerScores.get(index);
			if(fapMyScores > tscore){
				return index;
			}
		}
	}
	return 10;
	
}

public int GetHighestPlayerScore() {
	int highest = 0;
	int pplayer = 10;
	for(Player p : game.enemies()){
		if(p.isDefeated() == false){
			int index = p.getID();
			int tscore = playerScores.get(index);
			if(tscore >= highest){
				pplayer = p.getID();
				highest = tscore;
			}
		}
	}
	if(pplayer != 10){
		return highest;
	}
	else {
		return 1300;
	}
	
}

public boolean ShouldRegroup(Position pos) {
	// TODO fix this shit
	int dist = 0;
	boolean hasUnitsNearby = false;
	//System.out.println("Should Regroup vis: " + game.isVisible(pos.toTilePosition()));
	
	if(game.isVisible(pos.toTilePosition()) == false){
		return false;
	}

	if(myUnits.isEmpty() == false){

		for(Unit units : myUnits){
			dist = dist + units.getPosition().getApproxDistance(pos);
			//System.out.println("Dist is now: " + dist);
		}
		
		dist = Math.round(dist / myUnits.size());
		//System.out.println("Dist Final is: " + dist);

		if(dist > 2000){
			return true;
		}
		return false;
	}
	else {
		return false;
	}
	
}

public Region GetRegionBehindBunker(Unit bunker, Unit runfrom){
	int dist = 0;
	Region target = null;
	Region bunkerRegion = bunker.getRegion();
	
	for(Region regions : bunkerRegion.getNeighbors()){
		if(bunkerRegion.getDistance(regions) < dist){
			dist = bunkerRegion.getDistance(regions);
			target = regions;
		}
	}
	
	if(target != null){
		return target;
	}
	
	
	
	return null;
}

public boolean DoesOutRangeBunker(Unit bunker, Unit target){
	int hasRange = self.getUpgradeLevel(UpgradeType.U_238_Shells);
	int brange = UnitType.Terran_Marine.groundWeapon().maxRange();
	int range = target.getType().groundWeapon().maxRange();
	int bonus = 0;
	
	if(target.getType() == UnitType.Protoss_Dragoon && target.getPlayer().getUpgradeLevel(UpgradeType.Singularity_Charge) == 1){
		return true;
	}
	
	
	if(range > brange){
		return true;
	}
	
	return false;
}

public Bullet GetNearestBullet(BulletType type, Position pos){
	//System.out.println("Trigger Function");
	//System.out.println("Size: " + game.getBullets().size());
	int lowest = 0;
	Bullet bullet1 = null;
	for(Bullet bullets : game.getBullets()){
		if(bullets.getSource().getType() == UnitType.Protoss_High_Templar){
			if(bullets.getPosition().getApproxDistance(pos) <= lowest || bullet1 == null){
				lowest = bullets.getPosition().getApproxDistance(pos);
				bullet1 = bullets;
			}
		}
	}
	if(bullet1 != null){
		return bullet1;
	}
	else {
		return null;
	}
}

public boolean UnitIsAttackingUnit(Unit attacker, Unit victim){
	if(attacker.getOrder() == Order.AttackUnit && attacker.getOrderTarget() == victim){
		return true;
	}
	
	if(attacker.getOrder() == Order.AttackMove && attacker.getOrderTargetPosition() == victim.getPosition()){
		return true;
	}
	
	return false;
}

public boolean CanMakeWorkers(){
	if(InvadersScore > 0){
		return false;
	}
	
	return true;
}

public boolean HasUnitsNearbyToCombat(Unit target){
	int score = 0;
	int targetscore = getScoreOf(target);
	
	if(fapMyScores < estimatedEnemyScore){
		return true;
	}
	
	for(Unit unit : myUnits){
		if(unit.isInWeaponRange(target) == true){
			score = score + getScoreOf(unit);
		}
		if(score >= targetscore){
			return true;
		}
	}
	
	for(Unit unit : myBunkers){
	if(unit.getLoadedUnits().size() > 0){
		if(unit.getDistance(target.getPosition()) < 400){
			score = score + getScoreOf(unit);
			}
		}
		if(score >= targetscore){
			return true;
		}
	}
	
	if(score >= targetscore){
		return true;
	}
	
	
	return false;
	
}

public boolean BunkerCanAttack(Unit bunker, Unit Target){
	
	if(bunker.getLoadedUnits().isEmpty() == true){
		return false;
	}
	
	for(Unit units : bunker.getLoadedUnits()){
		if(units.isInWeaponRange(Target) == true){
			return true;
		}
	}
	
	return false;
	
}

public boolean BunkerCanAttackAnything(Unit bunker){
	
	if(bunker.getLoadedUnits().isEmpty() == true){
		return false;
	}
	
	for(Unit units : bunker.getLoadedUnits()){
		for(Unit eunits : GetEnemyUnitsNearby(bunker.getPosition(), 170, false)){
			if(units.isInWeaponRange(eunits) == true){
				return true;
			}
		}
	}
	
	return false;
	
}

public boolean ShouldBeFocused(Unit weeheads){
	
	if(weeheads.getType() == UnitType.Terran_Vulture_Spider_Mine){
		return true;
	}
	
	if(weeheads.getType() == UnitType.Zerg_Lurker){
		return true;
	}
	
	if(weeheads.getType() == UnitType.Terran_SCV && weeheads.isRepairing() == true){
		return true;
	}
	
	if(weeheads.getType() == UnitType.Terran_Medic){
		return true;
	}
	
	if(weeheads.getType() == UnitType.Protoss_High_Templar){
		return true;
	}
	
	if(weeheads.getType() == UnitType.Protoss_Carrier){
		return true;
	}
	

	return false;
	
}

public ArrayList<Unit> GetMyUnitsNearby(Position pos, int radius, boolean include){
	 ArrayList<Unit> Mine = new ArrayList<Unit>();
	for (Unit targets : getUnitsInRadius(pos, radius)) {
		int damage = targets.getType().groundWeapon().damageAmount() + targets.getType().airWeapon().damageAmount();
		if (targets.getPlayer() == self && IsMilitrayUnit(targets) == true && Mine.contains(targets) == false) {
			Mine.add(targets);

		}
		if(targets.getPlayer().isAlly(self) && targets.getType() == UnitType.Terran_Bunker && targets.getLoadedUnits().size() > 0 &&Mine.contains(targets) == false && include == true ){
			Mine.add(targets);
		}
	}
	
	return Mine;

	
}

public ArrayList<Unit> GetEnemyUnitsNearby(Position pos, int radius, boolean include){
	 ArrayList<Unit> Mine = new ArrayList<Unit>();
	for (Unit targets : getUnitsInRadius(pos, radius)) {
		int damage = targets.getType().groundWeapon().damageAmount() + targets.getType().airWeapon().damageAmount();
		if (targets.getPlayer().isEnemy(self) == true && IsMilitrayUnit(targets) == true && Mine.contains(targets) == false) {
			Mine.add(targets);

		}
		if(targets.getPlayer().isEnemy(self) == true && IsMilitrayBuilding(targets) == true){
			Mine.add(targets);
		}
	}
	
	return Mine;

	
}

public ArrayList<Unit> BuildSquadToCounter(int tscore, boolean asdf){
	ArrayList<Unit> wisp = new ArrayList<Unit>();
	
	if(asdf == true && tscore >= fapMyScores){
		return myUnits;
	}
	
	for(Unit units : myUnits){
		int score = 0;
		if(units.isCompleted() == true && wisp.contains(units) == false){
			score = score + getScoreOf(units);
			wisp.add(units);
		}
		
		if(score >= tscore){
			return wisp;
		}
	}
	
	return null;
	
	
}

public int GetAverageRange(Position pos){
	int total = 0;
	int amount = 0;
	for(Unit units : GetEnemyUnitsNearby(pos, 300, true)){
		total = total + units.getType().groundWeapon().maxRange();
		amount++;
	}
	return Math.round(total / amount);
}

public UnitType GetRecommendedCounter(UnitType type){
	
	if(type == UnitType.Protoss_Zealot && game.canMake(UnitType.Terran_Firebat) == true){
		return UnitType.Terran_Firebat;
	}
	
	return null;
	
}

public String CheckOpener(Player ply){
	
	if(ply.allUnitCount(UnitType.Zerg_Spawning_Pool) == 1 && ply.allUnitCount(UnitType.Zerg_Drone) < 7){
		return "6 Pool";
	}
	
	return null;
	
}

public void AddUnitAmountToPUnits(UnitType type, int amount){
	for (int i = 0; i < amount; i++){
		pUnits.add(type);
	}
}

public boolean isTargettingUnit(Unit target, Unit victim){
	if(target.exists() == true){
		if(target.getTarget().equals(victim)|| target.getOrderTarget().equals(victim) || target.getOrderTargetPosition().equals(victim.getPosition())){
			return true;
		}
	}
	return false;
}



public boolean ShouldSimUnits(){
	int amount = 0;
	int threshhold = 1;
	int max = enemyUnits.size();
	
	if(enemyUnits.isEmpty() == true){
		return false;
	}
	
	if(SameArmy() == true){
		// basically if the army is the same as the last fap sim we can just decide using that data again
		return true;
	}
	
	for(Unit units : enemyUnits){
		if(units.isVisible() == true){
			amount++;
		}
	}
	
	if(amount >= max){
		return true;
	}
	
	return false;
}

public boolean jFapGlobal(){
	JFAP simulator = new JFAP(game);
	simulator.clear();
	
	for(Unit unit : myUnits){
		if(unit.getType() == UnitType.Unknown){
			//System.out.println("Unit: " + unit.getType().toString() + " DS: " + unit.getType().destroyScore());
		}
		simulator.addUnitPlayer1(new JFAPUnit(unit));
	}
	
	for(Unit unit : enemyUnits){
		if(unit.getType() == UnitType.Unknown){
			//System.out.println("Unit: " + unit.getType().toString() + " DS: " + unit.getType().destroyScore());
		}
		//System.out.println("Unit: " + unit.getType().toString() + " DS: " + unit.getType().destroyScore());
		simulator.addUnitPlayer2(new JFAPUnit(unit));
	}
	
	for(Unit unit : enemyDefences){
		//System.out.println("Unit: " + unit.getType().toString() + " DS: " + unit.getType().destroyScore());
		simulator.addUnitPlayer2(new JFAPUnit(unit));
	}

	Pair<Integer, Integer> preSimScores = simulator.playerScores();
	int preSimFriendlyUnitCount = simulator.getState().getKey().size();
	simulator.simulate(75);
	Pair<Integer, Integer> postSimScores = simulator.playerScores();
	int postSimFriendlyUnitCount = simulator.getState().getKey().size();
	int myLosses = preSimFriendlyUnitCount - postSimFriendlyUnitCount;
	int myScoreDiff = preSimScores.getKey() - postSimScores.getKey();
	int enemyScoreDiff = preSimScores.getValue() - postSimScores.getValue();
	//System.out.println("Me : " + myScoreDiff);
	//System.out.println("Enemy : " + enemyScoreDiff);
	// if we can win
	lastSimScore = preSimScores.getValue();
	if (postSimScores.getKey() >= postSimScores.getValue()) {
		return true;
	} 
	else {
		return false;
	}
	
}

public boolean SameArmy(){
	
	if(estimatedEnemyScore == lastSimScore){
		return true;
	}
	
	return false;
}

public Base GetBaseToGatherAt(){
	if(myBases.isEmpty() == false){
		int lowest = 0;
		Base chosen = null;
		for(Base bases : myBases){
			int code = bases.hashCode();
			TilePosition tile = bases.getLocation();
			if(baseWorkers.containsKey(tile) && maxWorkers.containsKey(tile)){
				int amount = baseWorkers.get(tile);
				int max = maxWorkers.get(tile);
				if(amount <= max){ 
					if(amount <= lowest || chosen == null){
					lowest = baseWorkers.get(tile);
					chosen = bases;
					}
				}
			}
	
		}
		if(chosen != null){
			return chosen;
		}
		else {
			return null;
		}
	}	
	
	return null;
}

public boolean isWorking(Unit unit){
	
	if(unit.isConstructing() == false){
		if(unit.isGatheringGas() || unit.isGatheringMinerals() || unit.isCarryingGas() || unit.isCarryingMinerals()){
			return true;
		}
	}
	 return false;
}

	
public boolean IsHealing(Unit unit){
	if(unit.getOrder() == Order.HealMove && unit.getOrder() == Order.MedicHeal || unit.getOrder() == Order.MedicHealToIdle){
		return true;
	}
	
	return false;
}

public void GlobalAttack(){
	for(Unit myUnit : myUnits){
		if (enemyBuildingMemory.isEmpty() == false) {
			// if we are not in a FFA
			if(TeamGameMode == false){
				myUnit.attack(enemyBuildingMemory.get(0));
			}
		} else {
			if(scoutedLocations.isEmpty()){
				for(Base base : bewb.getMap().getBases()){
					if(scoutedLocations.contains(base) == false){
						scoutedLocations.add(base);
					}
				}
			}
			for (Base b : scoutedLocations) {
				if (game.isVisible(b.getLocation()) == false) {
					myUnit.attack(b.getCenter());
					break;
				}
			}
		}
		
		if(DoneCreatingList == true && playerBuildings.isEmpty() == false && TeamGameMode == true){
			//if we are in a FFA
			int target = GetPlayerTarget();
			if(target != 10){
				Position pos = playerBuildings.get(target).get(0);
				if(pos != null){
					myUnit.attack(pos, false);
				}
			}

			}
	}
}

public boolean isInCombat(Unit unit){
	if(unit.isAttacking() || unit.isUnderAttack() || unit.isStartingAttack()){
		return true;
	}
	
	return false;
}

public boolean ScoreFap(ArrayList<Unit> mine, ArrayList<Unit> hostile, int bonus){
	int a = 0;
	int h = 0;
	boolean needscan = false;
	
	if(bonus < 1){
		bonus = 1;
	}
	
	for(Unit units : mine){
		a = a + getScoreOf(units);
	}
	
	for(Unit units : hostile){
		h = h + getScoreOf(units);
		if(units.isDetected() == false && units.getType() == UnitType.Zerg_Lurker || units.getType() == UnitType.Protoss_Dark_Templar){
			needscan = true;
		}
	}
	
	if(needscan == true && getTotalScanEnergy() < 50){
		return false;
	}

	
	if(a >= h * bonus){
		return true;
	}
	
	
	return false;
}


public void CustomGreetings(String str){

	if(str == "Ecgberht"){
		game.sendText("Sorry it has to come to this, M'lord");
		game.sendText("But it's time to hand over the throne");
	}
	
	
}

public boolean IsUpgradingorBetter(UpgradeType type){
	
	if(self.isUpgrading(type) || self.getMaxUpgradeLevel(type) > 0){
		return true;
	}
	
	return false;

}

public int AvergeDistanceToRalleyPoint(){
	int max = 0;
	
	if(myUnits.isEmpty() == true){
		return 0;
	}
	
	for(Unit units : myUnits){
		int dist = units.getPosition().getApproxDistance(ralleyPoint);
		max = max + dist;
	}
	return max / myUnits.size();

	
}

//special thanks to Jabbo

public static Base getClosestBaseLocation(Position pos) {
    Base closestBase = null;
	Area aaaa = bewb.getMap().getArea(pos.toTilePosition());
	//System.out.println(aaaa.getId());
	//System.out.println(bewb.getMap().getBases().size());
    double dist = Double.MAX_VALUE;
    for (Base base : bewb.getMap().getBases()) {
        double cDist = pos.getApproxDistance(base.getLocation().toPosition());
    	//System.out.println(cDist);
        if (closestBase == null || cDist < dist) {
            closestBase = base;
            dist = cDist;
        }
    }
    
    return closestBase;
}

public static ChokePoint getClosestChokePoint(Position pos) {
    ChokePoint closestBase = null;
	Area aaaa = bewb.getMap().getArea(pos.toTilePosition());
    double dist = Double.MAX_VALUE;
    for (ChokePoint base : bewb.getMap().getChokePoints()) {
        double cDist = pos.getApproxDistance(base.getCenter().toPosition());
        if (closestBase == null || cDist < dist) {
            closestBase = base;
            dist = cDist;
        }
    }

    return closestBase;
}

public ArrayList<Unit> getUnitsInRadius(Position pos, int radius){
	ArrayList<Unit> chum = new ArrayList<Unit>();
	for(Unit unit : game.getAllUnits()){
		if(unit.getDistance(pos) <= radius && chum.contains(unit) == false){
			chum.add(unit);
		}
		
	}
	
	if(!chum.isEmpty()){
		return chum;
	}
	else {
		return null;
	}
	
}

public static void main(String[] args) {
	  new Jbot();
}



}


	
	
