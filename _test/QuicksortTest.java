package _test;


import gui.GUI;

import java.util.Random;

import linear.List;
import linear.ListWithViewer;

public class QuicksortTest {
	private List<String> avengers;
	private int anzahlVergleiche;

	public QuicksortTest(){
		anzahlVergleiche = 0;
	}

	public List<String> quicksortStrings(List<String> pStrings){
		int len =0;
		for(pStrings.toFirst(); pStrings.hasAccess();pStrings.next()){
			len++;
		}
		if(len <=1){
			return pStrings;
		}

		List<String> ergebnis = new List<String>();
		List<String> left = new List<String>();
		List<String> right = new List<String>();
		//getting Pivot
		pStrings.toFirst();
		String pivot = pStrings.getContent();
		pStrings.remove();
		//sorting in Left and right / DIVIDE
		for(pStrings.toFirst(); pStrings.hasAccess();pStrings.next()){
			if(pStrings.getContent().compareToIgnoreCase(pivot) < 0){
				left.append(pStrings.getContent());
				anzahlVergleiche++;
			}else if(pStrings.getContent().compareToIgnoreCase(pivot) > 0){
				right.append(pStrings.getContent());
				anzahlVergleiche++;
			}else {
				left.append(pStrings.getContent());
				anzahlVergleiche++;
			}
		}
		//Recursively calling quickSort
		left = quicksortStrings(left);
		right = quicksortStrings(right);
		//Merging Back
		ergebnis.concat(left);
		ergebnis.append(pivot);
		ergebnis.concat(right);

		return ergebnis;		
	}

	public List<Integer> quicksort(List<Integer> pList){
		pList.toFirst();
		pList.next();
		if(!pList.hasAccess()){
			return pList;
		}

		List<Integer> ergebnis = new List<Integer>();
		List<Integer> left = new List<Integer>();
		List<Integer> right = new List<Integer>();
		//getting Pivot
		pList.toFirst();
		int pivot = pList.getContent();
		pList.remove();
		//sorting in Left and right / DIVIDE
		for(pList.toFirst(); pList.hasAccess();pList.next()){
			if(pList.getContent() < pivot){
				left.append(pList.getContent());
				anzahlVergleiche++;
			}else if(pList.getContent() > pivot){
				right.append(pList.getContent());
				anzahlVergleiche++;
			}else {
				left.append(pList.getContent());
				anzahlVergleiche++;
			}
		}
		//Recursively calling quickSort
		left = quicksort(left);
		right = quicksort(right);
		//Merging Back
		ergebnis.concat(left);
		ergebnis.append(pivot);
		ergebnis.concat(right);

		return ergebnis;
	}

	public void quicksortTestKlein(){
		anzahlVergleiche = 0;
		avengers = new ListWithViewer<String>();
		avengers.append("Iron Man");
		avengers.append("Captain America");
		avengers.append("Thor");
		avengers.append("Spider Man");
		avengers.append("Black Widow");
		List<String> ergebnis = quicksortStrings(avengers);
		for(ergebnis.toFirst(); ergebnis.hasAccess();ergebnis.next()){
			System.out.println(ergebnis.getContent());
		}
	}

	public void quicksortTestGrossStrings(int pAnzahl){
		anzahlVergleiche = 0;
		List<String>strings = stringserzeugen(pAnzahl);
		long startzeit = System.currentTimeMillis();
		List<String> ergebnis = quicksortStrings(strings);
		long endzeit = System.currentTimeMillis();
		//ausgeben(ergebnis);
		long verbrauchteZeit = endzeit - startzeit; 
		System.out.println("+++ Zeitverbrauch: "+verbrauchteZeit+"ms +++");
		System.out.println("+++ Anzahl Vergleiche: "+anzahlVergleiche);
	}

	public void quicksortTestGross(int pAnzahl){
		anzahlVergleiche = 0;
		List<Integer> ints = erzeugen(pAnzahl);
		long startzeit = System.currentTimeMillis();
		List<Integer> ergebnis = quicksort(ints);
		long endzeit = System.currentTimeMillis();
		//ausgeben(ergebnis);
		long verbrauchteZeit = endzeit - startzeit;
		System.out.println("+++ Zeitverbrauch: "+verbrauchteZeit+"ms +++");
		System.out.println("+++ Anzahl Vergleiche: "+anzahlVergleiche);
	}

	/**
	 * erzeugt eine List mit zufaelligen Strings der Laenge 10.
	 * @param pAnzahl
	 */
	public List<String> stringserzeugen(int pAnzahl){
		List<String> ergebnis = new List<String>();
		Random r = new Random();
		System.out.println("*** erzeugen("+pAnzahl+") ***");
		for(int n=0; n<pAnzahl; n++){
			String s = "";
			for (int i=0; i<10;i++)
			{
				s += (char)(r.nextInt(26) + 65);
			}
			ergebnis.append(s);
			//System.out.println(s);
		}
		return ergebnis;
	}

	public List<Integer> erzeugen(int pAnzahl){
		List<Integer> ergebnis = new List<>();
		Random r = new Random();
		System.out.println("*** erzeugen("+pAnzahl+") ***");
		for(int n=0; n<pAnzahl; n++){
			int s = r.nextInt(1000000);
			ergebnis.append(s);
		}
		return ergebnis;
	}

	public void ausgeben(List<String> pStrings){
		System.out.println();
		System.out.println("*** ausgeben() ***");
		for(pStrings.toFirst();pStrings.hasAccess(); pStrings.next()){
			System.out.println(pStrings.getContent());
		}
	}

	public static void main(String[] args) {
		new GUI(new QuicksortTest());
	}
}
