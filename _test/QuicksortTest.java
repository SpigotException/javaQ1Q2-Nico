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

		List<String> ergebnis = new List<>();
		List<String> left = new List<>();
		List<String> right = new List<>();
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

		List<Integer> ergebnis = new List<>();
		List<Integer> left = new List<>();
		List<Integer> right = new List<>();
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

	public List<Integer> mergesort(List<Integer> pList){
		// if length == 1 -> return List
		pList.toFirst();
		pList.next();
		if(!pList.hasAccess()){
			return pList;
		}
		List<Integer> ergebnis = new List<>();
		List<Integer> left = new List<>();
		List<Integer> right = new List<>();
		// Liste aufteilen / halbieren
		int i=0;
		for(pList.toFirst();pList.hasAccess();){
			if (i%2 ==0){
				left.append(pList.getContent());
				pList.remove();
			} else {
				right.append(pList.getContent());
				pList.remove();
			}
			i++;
		}
		// Liste rekursiv weiter aufteilen
		left = mergesort(left);
		right = mergesort(right);

		left.toFirst();
		right.toFirst();

		while (left.hasAccess() && right.hasAccess()){
			if(left.getContent() <= right.getContent()){
				ergebnis.append(left.getContent());
				left.remove();
				anzahlVergleiche++;
			}else {
				ergebnis.append(right.getContent());
				right.remove();
				anzahlVergleiche++;
			}

		}
		ergebnis.concat(left);
		ergebnis.concat(right);
		return ergebnis;
	}

	public void quicksortTestKlein(){
		anzahlVergleiche = 0;
		avengers = new ListWithViewer<>();
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
		if(verbrauchteZeit != 0){
			System.out.println("~~~~~~~~~ Zeit pro Vergleich: " + anzahlVergleiche / verbrauchteZeit + " vergleiche/ms");
		}

	}

	public void mergesortTestGross(int pAnzahl){
		anzahlVergleiche = 0;
		List<Integer> ints = erzeugen(pAnzahl);
		long startzeit = System.currentTimeMillis();
		List<Integer> ergebnis = mergesort(ints);
		long endzeit = System.currentTimeMillis();
		//intausgeben(ergebnis);
		long verbrauchteZeit = endzeit - startzeit;
		if(verbrauchteZeit != 0){
			System.out.println("Mergesort test: " + pAnzahl + " Elemente sortiert, in " + verbrauchteZeit + " Millisekunden und " + anzahlVergleiche + " vergleichen. Daraus folgen " + anzahlVergleiche / verbrauchteZeit + " vergleiche/ms" );
		}else{
			System.out.println("Mergesort test: " + pAnzahl + "Elemente sortiert, in " + verbrauchteZeit + " Millisekunden und " + anzahlVergleiche + " vergleichen.");
		}
	}

	public int CURSEDfactorial(int n){
        return switch (n) {
            case 1 -> 1 * 1 * 1 * 1 * 1 * 1 * 1 * 1 * 1 * 1 * 1;
            case 2 -> 2 * 1 * 1 * 1 * 1 * 1 * 1 * 1 * 1 * 1 * 1;
            case 3 -> 3 * 2 * 1 * 1 * 1 * 1 * 1 * 1 * 1 * 1 * 1;
            case 4 -> 4 * 3 * 2 * 1 * 1 * 1 * 1 * 1 * 1 * 1 * 1;
            case 5 -> 5 * 4 * 3 * 2 * 1 * 1 * 1 * 1 * 1 * 1 * 1;
            case 6 -> 6 * 5 * 4 * 3 * 2 * 1 * 1 * 1 * 1 * 1 * 1;
            case 7 -> 7 * 6 * 5 * 4 * 3 * 2 * 1 * 1 * 1 * 1 * 1;
            case 8 -> 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1 * 1 * 1 * 1;
            case 9 -> 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1 * 1 * 1;
            case 10 -> 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1*1;
            default -> fact(n);
        };
	}

	public int fact(int n){
		return n == 1 ? 1 : n * fact(n-1);
	}


	public int fib(int n){
		return n == 1 || n == 0 ? n : fib(n -1) + fib(n -2);
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

	public void intausgeben(List<Integer> pInts){
		System.out.println();
		System.out.println("*** ausgeben() ***");
		for(pInts.toFirst();pInts.hasAccess(); pInts.next()){
			System.out.println(pInts.getContent());
		}
	}

	public void ausgeben(List<String> pStrings){
		System.out.println();
		System.out.println("*** ausgeben() ***");
		for(pStrings.toFirst();pStrings.hasAccess(); pStrings.next()){
			System.out.println(pStrings.getContent());
		}
	}

	public static void main(String[] args) {
		QuicksortTest q1 = new QuicksortTest();
		//for(int i = 0; i<50; i += 5){
		//	q1.quicksortTestGross(( i + 1 )  * 100000);
		//}


		new GUI(q1);
	}
}
