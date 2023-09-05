package _test.array;

import gui.GUI;

public class WetterDaten2018 {
	private int[][] niederschlaege = {
			{},
			{ 3,12, 7,19, 6, 0, 0,17, 5, 2, 0, 1, 0, 3, 8,11,12,10,21, 9, 3, 0, 0, 0, 2, 5, 8,11,11,12},	
			{ 8,10, 0, 0, 3, 6, 0, 0,17, 5, 2, 0, 1, 0, 3, 8,11,12, 6, 0, 0,17, 5, 2, 0, 1, 0},	
			{17, 5, 2, 0, 1, 0, 3, 8,11,12,10,24,17, 5, 2, 0, 1, 0, 3, 8,11,12,10,21,21,12, 6, 0, 0,17},	
			{11,10,14, 0,17, 5, 2, 0, 1, 0,16, 8,22,12,10,24,17, 0, 3, 8,11,12, 6, 0, 0,17, 8, 7, 0},	
			{14,6, 7,19, 6, 0, 0,17, 5, 2,10, 1, 0, 3, 8,11,12,10,21, 9, 3, 0, 0, 0, 2, 5, 8,11,11,12},	
			{ 8,10, 0, 0, 3, 6, 0, 0,17, 5, 2, 0, 1, 0, 3, 8,11,12, 6, 0, 0,17, 5, 2, 0, 1, 0, 3, 8},	
			{17, 5, 2, 0, 1, 0, 3, 8,32,12,10,24,17, 5, 2, 0, 1, 0, 3, 8,11,12,10,21,21,12, 6, 0, 0,17},	
			{ 4, 0, 0, 0,17, 5, 2, 0, 1, 0, 3, 8,11,12,10,24,17, 0, 3, 8,11,12, 6, 0, 0,17, 8, 7, 0, 5},	
			{ 3,12, 7,19, 6, 0, 0,17, 5, 2, 0, 1, 0, 3, 8,11,12,10,21, 9, 3, 0, 0, 0, 2, 5, 8,11,11},	
			{ 9,14, 0, 0, 3, 6, 0, 0,17, 5, 2, 0, 1, 0, 3, 8,11,12, 6, 0, 0,17, 5, 2, 0, 1, 0, 3, 8,11},	
			{17, 5, 2, 0, 1, 0, 3, 8,11,10,10,24,17, 5, 2, 0, 1, 0, 3, 8,11,12,10,21,21,12, 6, 0, 0},	
			{ 8,10, 0, 0,17, 5, 2, 0, 1, 0,13, 8,11,12,10,24,17, 0, 3, 8,11,12, 6, 0, 0,17, 8, 7, 0, 5}	
	};
	
	public void eintragen(int pMonat, int pTag, int pWert){
		//TODO
		niederschlaege[pMonat] [pTag -1] = pWert;
	}
	
	public int gesamtNiederschlagJahr(){
		int insgesamtNiederschlag = 0;
		for (int i = 0; i < niederschlaege.length; i++) {
			for (int j = 0; j < niederschlaege[i].length; j++) {
				int z = niederschlaege[i] [j];
				insgesamtNiederschlag += z;
			}
		}
		return insgesamtNiederschlag;
	}

	public int gesamtNiederschlag(int pMonat){
		//TODO
		int ergebnis = 0;
		for (int i = 0; i < niederschlaege[pMonat].length; i++) {
			int z = niederschlaege[pMonat] [i];
			ergebnis+=z;
		}
		
		return ergebnis;
	}
	
	public int hoechsterNiederschlag(){
		//TODO
		int hoechste = niederschlaege[1] [0];
		for (int i = 0; i < niederschlaege.length; i++) {
			for (int j = 0; j < niederschlaege[i].length; j++) {
				int z = niederschlaege[i] [j];
				if(hoechste < z)
				{
					hoechste =z;
				}
			}
		}
		return hoechste;
	}
	
	public int monatMitHoechsterNiederschlagsMenge(){
		//TODO: es soll der Monat zurueckgegeben werden!
		for (int i = 0; i < niederschlaege.length; i++) {
			gesamtNiederschlag(i);
			//TODO!!!
		}
		return -1;
	}

	public static void main(String[] args) {
		new GUI(new WetterDaten2018());
	}
	
}
