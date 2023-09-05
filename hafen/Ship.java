package hafen;

import java.util.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import gui.GUI;

public class Ship {
	private String name;
	private double maxWeight;	
	private Container[] loadedContainers;
	private String destination;
	private int numberOfCrewMembers = 0;
	private Container testcontainer = new Container(26.3, 15.6);
	
	
	public Ship(String name, double maxWeight, String destination) {
		this.name = name;
		this.maxWeight = maxWeight;
		this.destination = destination;
		loadedContainers = new Container[6];
		testcontainer.setDestination("Hamburg");
		testcontainer.setLoadedProduct("LEGO Sets");
		
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(double maxWeight) {
		this.maxWeight = maxWeight;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	
	public void hireCrewMembers(int number) {
		if(number > 0) {
			numberOfCrewMembers += number;
		}
	}
	
	public void fireOneCrewMember() {
		if(numberOfCrewMembers >0) {
			numberOfCrewMembers--;
		}
	}
	
	public boolean unloadContainer(int position) {
		if(loadedContainers[position] != null) {
			loadedContainers[position] = null;
		}
		return false;
	}
	
	public double calculateCurrentWeight() {
		double weight = 0;
		for(int i=0;i<loadedContainers.length;i++) {
			if(loadedContainers[i] != null) {
				weight += loadedContainers[i].getLoadedWeight();
			}
		}
		return weight;
	}
	
	public int numberOfFirstEmptyPosition() {
		for(int i=0;i<loadedContainers.length;i++) {
			if(loadedContainers[i] == null) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean checkIfFits(Container newContainer) {
		if(this.calculateCurrentWeight() + newContainer.getLoadedWeight() < this.getMaxWeight()) {
			if(this.numberOfFirstEmptyPosition() >= 0) {
				return true;
			}
		}
		return false;
	}
	
	public void load(Container newContainer) {
		if(this.checkIfFits(newContainer)) {
			loadedContainers[this.numberOfFirstEmptyPosition()] = newContainer;
		}
	}
	
	public Container[] sortContainerArray(Container[] arr) {

			Container temp;
			for(int i=1; i<arr.length; i++) {
				for(int j=0; j<arr.length-i; j++) {
					if(arr[j] != null && arr[j+1] != null && arr[j].getLoadedWeight()>arr[j+1].getLoadedWeight()) {
						temp=arr[j];
						arr[j]=arr[j+1];
						arr[j+1]=temp;
					}
					
				}
			}
			return arr;
	}
	
	//public Container[] stripArray(Container[] arr) {
	
	public Container[] stripArray(Container[] arr) {
		int numberOfContainers = 0;
		Map<Integer,Container> meineMap = new HashMap<Integer, Container>();
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] != null) {
				meineMap.put(i, arr[i]);
				numberOfContainers++;
			}
		}
		
		Container[] done = new Container[numberOfContainers];
		//TODO
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(Map.Entry<Integer, Container> v: meineMap.entrySet()) {
			list.add(v.getKey());
		}
		
		
		for (int j = 0; j < done.length; j++) {
			done[j] = meineMap.get(list.get(j));
		}
		
		
		return done;
	}
	
	public void balanceLoad() {
		
		Container[] sorted = sortContainerArray(stripArray(loadedContainers));
		int arrMid = (int) Math.floor((loadedContainers.length-1)/2);
		int arrStart = arrMid;
		int arrEnd = arrMid - 1;
		
		//System.out.println(arrMid);
		
		Arrays.fill(loadedContainers, null);
		for (int i = sorted.length-1; i >= 0; i--) {
			//System.out.println(sorted[i]);
			if(arrStart<=loadedContainers.length) {
				if(i%2==0) {
					loadedContainers[arrStart] = sorted[i];
					arrStart++;
				}else {
					loadedContainers[arrEnd] = sorted[i];
					arrEnd--;
				}
			}
			
		}
		
		for(int i=0; i< loadedContainers.length; i++) {
			System.out.println(loadedContainers[i]);
		}
		
	}

	@Override
	public String toString() {
		return name + ", maxWeight " + maxWeight + "t, Crew " + numberOfCrewMembers + ", goes to " + destination;
	}

	public void printLoad() {
		for(int i=0; i< loadedContainers.length; i++) {
			System.out.println(loadedContainers[i]);
		}
	}
	
	private void createExampleoad() {
		Container c1 = new Container(24.2, 12.3);
		c1.setDestination("Hamburg");
		c1.setLoadedProduct("Oil");
		Container c2 = new Container(23.2, 23.2);
		c2.setDestination("Lisbon");
		c2.setLoadedProduct("Computer chips");
		Container c3 = new Container(15.0, 4.2);
		c3.setDestination("Antwerpen");
		c3.setLoadedProduct("Hop");
		loadedContainers[0] = c1;
		loadedContainers[2] = c2;
		loadedContainers[5] = c3;
	}
	
	public static void main(String[] args) {
		Ship ship = new Ship("Floating Sibi", 111.2, "Lisbon");
		ship.createExampleoad();
		new GUI(ship);
	}
	
}