package hafen;

import java.util.ArrayList;
import java.util.Random;

import gui.GUI;
//import linear.QueueWithViewer;
import linear.QueueWithViewer;
import linear.StackWithViewer;


public class Harbour {
	private final String country;
	private final String city;
	private final Ship[] shipsAtAnchor;
	public StackWithViewer<Container> containers;
	public StackWithViewer<Container> helpStack;
	public QueueWithViewer<Ship> waitingLine;
	public QueueWithViewer<Ship> helpQueueWithViewer = new QueueWithViewer<>();
	String[] destinationNames = {"Rotterdam", "Lisbon", "Piräus", "Shanghai", "Los Angeles", "Guangzhou", "Antwerpen", "Le Havre", "Singapur", "Koeln"};
	//Immer am Start von methode benutzen:
	//StackWithViewer<Container> stack = CopyStackWithViewer(containers);

	// das folgende Attribut ist nur zu Testzwecken vorhanden, es muss ansonsten nicht beachtet werden
	Ship testship = new Ship("Testship I", 187.45, "Hamburg");


	public Harbour(String country, String city, int size) {		
		this.country = country;
		this.city = city;
		this.shipsAtAnchor = new Ship[size];
		testship.load(new Container(15.5, 13.3));
		testship.load(new Container(22.3, 0.2));
		containers = new StackWithViewer<>();
		helpStack = new StackWithViewer<>();
		waitingLine = new QueueWithViewer<>();
		createCargoStackWithViewer(25);
		createWaitingLine();
	}
	
	//QueueWithViewer-Methoden


	public void createWaitingLine() {
		for(int i=0;i<5;i++) {
			Ship s1 = new Ship("Titanic" + i, i*20 +30, destinationNames[i]);
			waitingLine.enqueue(s1);
		}
	}
	
	public double approxWaitingTime() {
		int ships = 0;
		while(!waitingLine.isEmpty()) {
			ships++;
			helpQueueWithViewer.enqueue(waitingLine.front());
			waitingLine.dequeue();
		}
		while(!helpQueueWithViewer.isEmpty()) {
			waitingLine.enqueue(helpQueueWithViewer.front());
			helpQueueWithViewer.dequeue();
		}
		return (ships * 2.5);
	}
	
	public int numberOfFirstEmptyPosition() {
		for(int i=0;i<shipsAtAnchor.length;i++) {
			if(shipsAtAnchor[i] == null) {
				return i;
			}
		}
		return -1;
	}
	
	public Ship findShipInLine() {
		while(!waitingLine.isEmpty()) {
			if(waitingLine.front().getDestination().equals(containers.top().getDestination())) {
				return waitingLine.front();
			}
			helpQueueWithViewer.enqueue(waitingLine.front());
			waitingLine.dequeue();
		}
		while(!helpQueueWithViewer.isEmpty()) {
			waitingLine.enqueue(helpQueueWithViewer.front());
			helpQueueWithViewer.dequeue();
		}
		return null;
	}
	
	public void removeWaitingShip(String name) {
		
		while(!waitingLine.isEmpty()) {
			if(!waitingLine.front().getName().equals(name)) {
			helpQueueWithViewer.enqueue(waitingLine.front());
			}
			waitingLine.dequeue();
		}
		while(!helpQueueWithViewer.isEmpty()) {
			waitingLine.enqueue(helpQueueWithViewer.front());
			helpQueueWithViewer.dequeue();
		}
	}

	public void allEnterHarbour(){
		while(!waitingLine.isEmpty() && giveFirstFreeAnchorage() > -1){
			shipsAtAnchor[giveFirstFreeAnchorage()] = waitingLine.front();
			waitingLine.dequeue();
		}
	}

	public boolean toFront(Ship beFirstShip){
		QueueWithViewer<Ship> tmpQueueWithViewer = new QueueWithViewer<>();
		boolean ergebnis = false;
		while(!waitingLine.isEmpty()){
			if(beFirstShip.equals(waitingLine.front())){
				helpQueueWithViewer.enqueue(waitingLine.front());
				waitingLine.dequeue();
				ergebnis = true;
			}else {
				tmpQueueWithViewer.enqueue(waitingLine.front());
				waitingLine.dequeue();
			}
		}
		while(!tmpQueueWithViewer.isEmpty()){
				helpQueueWithViewer.enqueue(tmpQueueWithViewer.front());
				tmpQueueWithViewer.dequeue();
		}
		while(!helpQueueWithViewer.isEmpty()){
			waitingLine.enqueue(helpQueueWithViewer.front());
			helpQueueWithViewer.dequeue();
		}
		return ergebnis;
	}

	//StackWithViewer-Methoden

 	public void createCargoStackWithViewer(int anzahl) {

		/*
		for(int i=0;i<anzahl;i++) {
			Container c1 = new Container(100.0, Math.random()* 100);
			c1.setDestination(destinationNames[(int) Math.floor(Math.random()*destinationNames.length)]);
			containers.push(c1);

		}*/
		
		Container c1 = new Container(60.0, 50.0);
		c1.setDestination("Shanghai");
		Container c2 = new Container(10.0, 07.0);
		c2.setDestination("Hamburg");
		Container c3 = new Container(20.0, 16.0);
		c3.setDestination("Lisbon");
		Container c4 = new Container(100.0, 60.0);
		c4.setDestination("Shanghai");
		containers.push(c1);
		containers.push(c2);
		containers.push(c3);
		containers.push(c4);
		
	}
	
	private void createAndStoreShipsTest() {
		Ship s1 = new Ship("Titanic", 250.0, "Hamburg");
		Ship s2 = new Ship("Queen Mary", 100, "Shanghai");
		Ship s3 = new Ship("MS Sealife", 10, "Lisbon");
		Random random = new Random();
		int randomIndex;
		do {
			randomIndex = random.nextInt(shipsAtAnchor.length);
		} while (shipsAtAnchor[randomIndex] != null);

		shipsAtAnchor[0] = s1;
		shipsAtAnchor[1] = s2;
		shipsAtAnchor[2] = s3;
	}

	public ArrayList<Container> makeStackWithViewerToArray(StackWithViewer<Container> stack) {
		ArrayList<Container> arr = new ArrayList<>();
		while(!stack.isEmpty()) {
			arr.add(stack.top());
			stack.pop();
		}
		return arr;
	}

	public StackWithViewer<Container> makeArraytoStackWithViewer(ArrayList<Container> cList){
		StackWithViewer<Container> stack = new StackWithViewer<>();
		for(int i = cList.size()-1;i>=0;i--) {
			stack.push(cList.get(i));
		}
		return stack;
	}

	public StackWithViewer<Container> CopyStackWithViewer(StackWithViewer<Container> inputStackWithViewer){
		ArrayList<Container> arrList = makeStackWithViewerToArray(inputStackWithViewer);
		StackWithViewer<Container> stack = makeArraytoStackWithViewer(arrList);
		inputStackWithViewer = makeArraytoStackWithViewer(arrList);
		return stack;
	}

	public int countContainers(String pDestination) {
		//StackWithViewer<Container> stack = CopyStackWithViewer(containers);
		int result = 0;

		while(!containers.isEmpty()) {
			if(containers.top().getDestination().equals(pDestination)) {
				result++;
			}
			helpStack.push(containers.top());
			containers.pop();
		}
		while(!helpStack.isEmpty()) {
			containers.push(helpStack.top());
			helpStack.pop();
		}
		return result;
	}

	public double getWeightOfStack() {
		double weight = 0;
		//StackWithViewer<Container> helpStack = new StackWithViewer<>();
		while(!containers.isEmpty()) {

			weight += containers.top().getLoadedWeight();

			helpStack.push(containers.top());
			containers.pop();
		}
		while(!helpStack.isEmpty()) {
			containers.push(helpStack.top());
			helpStack.pop();
		}
		return weight;
	}

	public boolean loadCont(Container pCont) {
		String dest = pCont.getDestination();
        for (Ship ship : shipsAtAnchor) {
            if (ship.getDestination().equals(dest)) {
                ship.load(pCont);
                return true;
            }
        }
		return false;
	}

	public Container findContainer(String destination, double weightLimit) {
		Container c = null;
		//StackWithViewer<Container> helpStack = new StackWithViewer<>();
		while(!containers.isEmpty()) {

			if(containers.top().getDestination().equals(destination) && containers.top().getLoadedWeight() <= weightLimit) {
				c = containers.top();
				break;
			}
			helpStack.push(containers.top());
			containers.pop();
		}
		while(!helpStack.isEmpty()) {
			containers.push(helpStack.top());
			helpStack.pop();
		}
		return c;
	}

	public Container findAndRemoveHeaviest(StackWithViewer<Container> stack) {
		Container heaviest = stack.top();
		//StackWithViewer<Container> helpStack = new StackWithViewer<>();
		while(!stack.isEmpty()) {

			if(stack.top().getLoadedWeight() > heaviest.getLoadedWeight()) {
				heaviest = stack.top();
			}
			helpStack.push(stack.top());
			stack.pop();
		}
		while(!helpStack.isEmpty()) {
			if(helpStack.top() != heaviest) {
				stack.push(helpStack.top());
			}
			helpStack.pop();
		}
		return heaviest;
	}

	public void turnStack(StackWithViewer<Container> stack) {
		ArrayList<Container> helpList = new ArrayList<>();
		while(!stack.isEmpty()) {
			helpList.add(stack.top());
			stack.pop();
		}
        for (Container container : helpList) {
            stack.push(container);
        }
	}

	public void sortContStack() {
		StackWithViewer<Container> cont;
		cont = CopyStackWithViewer(containers);
		while(!containers.isEmpty()) {
			containers.pop();
		}
		while(!cont.isEmpty()) {
			containers.push(findAndRemoveHeaviest(cont));
		}
		this.turnStack(containers);
	}

	public void optimalVerladen() {
		//Stack nach gewicht sortieren
		//h�chstes gewicht oben
		this.sortContStack();
		//f�r obersten container ein schiff suchen, sonst auf helpstack legen
		while(!containers.isEmpty()) {
			Container currC = containers.top();
            for (Ship ship : shipsAtAnchor) {
                if (ship != null) {

                    if (currC.getDestination().equals(ship.getDestination()) && ship.checkIfFits(currC)) {
                        ship.load(currC);
                        containers.pop();
                    }

                }
            }
			if(!containers.isEmpty()) {
				if(currC.equals(containers.top())) {
					helpStack.push(containers.top());
					containers.pop();
				}
			}
			
		}

		//helpstack umdrehen
		while(!helpStack.isEmpty()) {
			containers.push(helpStack.top());
			helpStack.pop();
		}
	}
	
	//Harbour methoden

	public int countShipsInHarbour() {
		return shipsAtAnchor.length;
	}

	public int giveFirstFreeAnchorage() {
		for(int i=0;i<shipsAtAnchor.length;i++) {
			if(shipsAtAnchor[i] == null) {
				return i;
			}
		}
		return -1;		
	}

	public boolean leave(String leavingShipName) {
		//
		return false;
	}

	public boolean arrive(Ship arrivingShip) {
		// TODO
		return false;
	}


	/*
	 * Hilfsmethode zum Ausgaben aller vor Anker liegenden Schiffe auf der Konsole
	 */
	
	public void printShips() {
		for (Ship ship : shipsAtAnchor) {
			System.out.println(ship);
		}
	}

	/*
	 * getters & setters
	 */

	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}

	public Ship[] getShipsAtAnchor() {
		return shipsAtAnchor;
	}

	// ------------------------------------------------------------------

	/*
	 * Hilfsmethode zum Erzeugen der zu Beginn im Hamburger Hafen liegenden Schiffe 
	 */
	
	
	
	private void createAndStoreShips() {
		String[] shipNames = {"Titanic", "Queen Mary", "Bismarck", "Yamato", "Santa Maria",
				"USS Enterprise", "HMS Victory", "Endeavour", "Mayflower", "Fitzgerald",
				"Black Pearl", "Cutty Sark", "Golden Hind", "Nautilus", "Lusitania",
				"Constitution", "Discovery", "Nina", "Pinta", "Santa Clara"};

		String[] countryNames = {"Netherlands", "Portugal", "Greece", "China", "USA", "China", "Belgium", "France", "Singapur"};
		double maxWeightLimit = 250.0;
		Random random = new Random();



		/* Create Harbours --> Has to be moved to administration class for the harbours later
        Harbour[] destinations = new Harbour[destinationNames.length];
        for(int i=0; i<destinationNames.length; i++) {
        	destinations[i] = new Harbour(destinationNames[i], countryNames[i], 25);
        } */       

		// Create 20 ships for Hamburg
		for (int i = 0; i < 20; i++) {
			String name = shipNames[i];            
			double maxWeight = (double)(Math.round(100 * random.nextDouble() * maxWeightLimit)+5000)/100;
			if( maxWeight > maxWeightLimit) maxWeight = maxWeightLimit;

			int harbourNumber = random.nextInt(destinationNames.length);
			Ship ship = new Ship(name, maxWeight, destinationNames[harbourNumber]);

			int randomIndex;
			do {
				randomIndex = random.nextInt(shipsAtAnchor.length);
			} while (shipsAtAnchor[randomIndex] != null);

			shipsAtAnchor[randomIndex] = ship;
		}
	}

	/*
	 * main-Methode
	 */
	public static void main(String[] args) {
		Harbour theHarbour = new Harbour("Germany", "Hamburg", 30);
		//theHarbour.createAndStoreShips();
		theHarbour.createAndStoreShipsTest();
		//theHarbour.sortContStack();
		//System.out.println(theHarbour.containers.top());
		//theHarbour.findAndRemoveHeaviest(theHarbour.containers);
		//System.out.println(theHarbour.containers.top());
		//System.out.println(theHarbour.countContainers("Shanghai"));
		Ship s1 = new Ship("MSSeaworld", 300, "Shanghai");
		theHarbour.waitingLine.enqueue(s1);
		System.out.println(theHarbour.toFront(s1));
		System.out.println(theHarbour.waitingLine.front());
		new GUI(theHarbour);

	}
}
