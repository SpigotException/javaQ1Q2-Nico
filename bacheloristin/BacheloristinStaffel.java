package bacheloristin;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;

import gui.GUI;
import linear.List;
import linear.ListWithViewer;

/**
 * @author Manuel Grifka
 * @version v0.2 2019-10-28
 */

public class BacheloristinStaffel {

	 // Attribute
	 private int jahr;
	 private Kandidat bacheloristin;
	 private ListWithViewer<Kandidat> jungsListe;
	 private Kandidat testkandidat;

	 // Attribut testkandidat dient nur zum Testen der Methoden, die einen
	 // Kandidat-Objekt als Parameter brauchen


	 /**
	 * Konstruktor für Objekte der Klasse BacheloristinStaffel
	 */

	public BacheloristinStaffel(int pJahr)
	{
		jahr = pJahr;		
		jungsListe = new ListWithViewer<Kandidat>();

		initJungsListeTestkandidatBacheloristin(pJahr);
	}

	public ListWithViewer<Kandidat> getJungsListe(){
		return jungsListe;
	}

	// TODO: Teil 2: Aufgabe a) rausschmeissen
	public void rausschmeissen(String pName){
		for(jungsListe.toFirst();jungsListe.hasAccess();jungsListe.next()){
			if(jungsListe.getContent().getName().equals(pName)){
				jungsListe.getContent().fliegtRaus();
			}
		}
	}

	// TODO: Teil 2: Aufgabe b) alleResetten
	public void alleResetten(){
		for(jungsListe.toFirst();jungsListe.hasAccess();jungsListe.next()){
			Kandidat curr = jungsListe.getContent();
			curr.resetQuotenPunkte();
		}
	}

	// TODO: Teil 2: Aufgabe c) anzahlAusgeschiedener
	public int anzahlAusgeschiedener(){
		int result = 0;
		for(jungsListe.toFirst();jungsListe.hasAccess();jungsListe.next()){
			Kandidat curr = jungsListe.getContent();
			if(!curr.isNochDabei()){
				result++;
			}
		}
		return result;
	}


	// TODO: Teil 2: Aufgabe d) hatGelaestert

	public void hatGelaestert(String pNameWer, String pNameUeberWen){
		for(jungsListe.toFirst();jungsListe.hasAccess();jungsListe.next()){
			Kandidat curr = jungsListe.getContent();
			if(curr.getName().equals(pNameWer)){
				curr.erhoeheQuotenPunkte(20);
			} else if (curr.getName().equals(pNameUeberWen)) {
				curr.senkeQuotenPunkte(10);
			}
		}
	}

	// TODO: Teil 2: Aufgabe e) knutschtBacheloristin
	public void knutschBacheloristin(Kandidat pKandidat){
        for(jungsListe.toFirst();jungsListe.hasAccess();jungsListe.next()){
            Kandidat curr = jungsListe.getContent();
            if(curr.getName().equals(pKandidat.getName())){
                curr.erhoeheQuotenPunkte(50);
            }
        }
    }

	// TODO: Teil 2: Aufgabe f) gibQuotenLetzten
    public Kandidat gibQuotenLetzten(){
        jungsListe.toFirst();
        Kandidat min = jungsListe.getContent();
        for(jungsListe.toFirst();jungsListe.hasAccess();jungsListe.next()){
            Kandidat curr = jungsListe.getContent();
            if(curr.getQuotenPunkte()<min.getQuotenPunkte()){
                min = curr;
            }
        }
        return min;
    }

	// TODO: Teil 2: Aufgabe g) gibRentnerListe

	public int getDate(){
		long millis=System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String strDate = date.toString();
		String[] parts = strDate.split("-");
		String result = String.join("", parts);
		return Integer.parseInt(result);
	}

	public int getDateminus30(){
		long millis=System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String strDate = date.toString();
		String[] parts = strDate.split("-");
		parts[0] = Integer.toString(Integer.parseInt(parts[0]) - 30);
		String result = String.join("", parts);
		return Integer.parseInt(result);
	}

	public ListWithViewer<Kandidat> gibRentnerListe(){
		int currDateminus30 = getDateminus30();
		ListWithViewer<Kandidat> rentnerListe = new ListWithViewer<>();
		for(jungsListe.toFirst();jungsListe.hasAccess();jungsListe.next()){
			Kandidat curr = jungsListe.getContent();
			if(curr.getGebDatum()<currDateminus30){
				rentnerListe.append(curr);
			}
		}
		return rentnerListe;
	}


	/*
	 * -------------------------------------------------------------------------------------------
	 * ab hier nur noch init und main
	 * -------------------------------------------------------------------------------------------
	 */

	private void initJungsListeTestkandidatBacheloristin(int pJahr) {
		switch (pJahr) {
		case 2019:
			bacheloristin = new Kandidat("Fiona Diaz", 19900106, true);
			
			jungsListe.append(new Kandidat("Diego de Deus", 19601030, false)); 
			jungsListe.append(new Kandidat("George Coolknee", 19610506, false));
			jungsListe.append(new Kandidat("Shria Ekstroem", 19900102, false));
			jungsListe.append(new Kandidat("Edward Heran", 19910217, false));
			jungsListe.append(new Kandidat("Adam Douglas", 19520311, false));
			jungsListe.append(new Kandidat("Wolfgang Howowitz", 19810229, false));
			jungsListe.append(new Kandidat("Stefan Falking", 19420108, false));
			jungsListe.append(new Kandidat("Thomas Enis", 19980906, false));

			// Attribut testkandidat zum Testen von Methoden, die ein Kandidat-Objekt als Parameter erhalten
			testkandidat = new Kandidat("Don Dump", 19460614, false);
			jungsListe.append(testkandidat);

			jungsListe.append(new Kandidat("Christian Ronald", 19850205, false));
			break;

		case 2018:
			bacheloristin = new Kandidat("Bibi Botox", 19910909, true);
			
			jungsListe.append(new Kandidat("Paul Poser", 19860107, false)); 
			jungsListe.append(new Kandidat("Leo Lackaffe", 19891117, false));
			jungsListe.append(new Kandidat("Alex Honey", 19920102, false));

			// Attribut testkandidat zum Testen von Methoden, die ein Kandidat-Objekt als Parameter erhalten
			testkandidat = new Kandidat("Anton Donis", 19821223, false);
			jungsListe.append(testkandidat);

			jungsListe.append(new Kandidat("Robert Geissbock", 19720429, false));
			jungsListe.append(new Kandidat("Magnus Dautit", 19890802, false));
			jungsListe.append(new Kandidat("Thomas Hayopai", 19711109, false));
			jungsListe.append(new Kandidat("Shawn Schoenling", 19990107, false));
			jungsListe.append(new Kandidat("Gustave Giro", 20000505, false));
			jungsListe.append(new Kandidat("Alfons Bergmeier", 19990431, false));
			break;
			
		case 2017:
			bacheloristin = new Kandidat("Anna Krohn-Ismus", 19900722, true);
			
			jungsListe.append(new Kandidat("Kai Pirinha", 19970324, false)); 
			jungsListe.append(new Kandidat("Bill Dung", 19791009, false));
			jungsListe.append(new Kandidat("Jim Panse", 20000315, false));
			jungsListe.append(new Kandidat("Dieter Moskanne", 19670419, false));

			// Attribut testkandidat zum Testen von Methoden, die ein Kandidat-Objekt als Parameter erhalten
			testkandidat = new Kandidat("Ernst Haft", 19990101, false);
			jungsListe.append(testkandidat);

			jungsListe.append(new Kandidat("Lars Vegas", 19930602, false));
			jungsListe.append(new Kandidat("Andi Arbeit", 19890501, false));
			jungsListe.append(new Kandidat("Gerd Raenkaux-Tomate", 19871223, false));
			jungsListe.append(new Kandidat("Ismael Lieba", 19871030, false));
			jungsListe.append(new Kandidat("Karl Auer", 19931111, false));
			break;

		}
	}
	

	@Override
	public String toString() {
		return "Jahr " + jahr + ", Bacheloristin: " + bacheloristin;
	}


	public static void main(String[] args) {
		BacheloristinStaffel bs = new BacheloristinStaffel(2019);
		new GUI(bs);
	}

}
