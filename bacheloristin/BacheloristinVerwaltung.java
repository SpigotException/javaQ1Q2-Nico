package bacheloristin;

import gui.GUI;
import linear.List;
import linear.ListWithViewer;

public class BacheloristinVerwaltung {
    private ListWithViewer<BacheloristinStaffel> staffelList;
    private ListWithViewer<Kandidat> bewerberList;
    private ListWithViewer<Location> locationList;

    public BacheloristinVerwaltung(){
        staffelList = new ListWithViewer<>();
        staffelList.append(new BacheloristinStaffel(2017));
        staffelList.append(new BacheloristinStaffel(2018));
        staffelList.append(new BacheloristinStaffel(2019));

        bewerberList = new ListWithViewer<>();
        initBewerberList();

        locationList = new ListWithViewer<>();
        initLocationList();
    }

    private void initLocationList() {
        Location l1 = new Location("Bahamas", "Yacht");
        l1.setWochenMiete(200);
        locationList.append(l1);
        Location l2 = new Location("Bad Honnef", "Sibi");
        l2.setWochenMiete(10);
        locationList.append(l2);
        Location l3 = new Location("Sydney", "Villa");
        l3.setWochenMiete(500);
        locationList.append(l3);
        Location l4 = new Location("Singapur", "Hotel");
        l4.setWochenMiete(800);
        locationList.append(l4);
    }

    private void initBewerberList(){
        Kandidat b1 = new Kandidat("Hans Ernst", 19981012, false);
        bewerberList.append(b1);
        Kandidat b2 = new Kandidat("Erwin Rüddel", 19781012, false);
        bewerberList.append(b2);
        Kandidat b3 = new Kandidat("Jak Pie", 19981012, false);
        bewerberList.append(b3);
        Kandidat b4 = new Kandidat("George Coolknee", 19610506, false);
        bewerberList.append(b4);
        Kandidat b5 = new Kandidat("Ruediger nichtSo Tieeeef", 19881012, false);
        bewerberList.append(b5);
    }

    public void testBewerber(){
        for(bewerberList.toFirst();bewerberList.hasAccess();bewerberList.next()){
            Kandidat currBewerber = bewerberList.getContent();
            for(staffelList.toFirst();staffelList.hasAccess();staffelList.next()){
                BacheloristinStaffel currStaffel = staffelList.getContent();
                for(currStaffel.getJungsListe().toFirst();currStaffel.getJungsListe().hasAccess();currStaffel.getJungsListe().next()){
                    Kandidat currKandidat = currStaffel.getJungsListe().getContent();
                    if(currBewerber.getName().equals(currKandidat.getName())){
                        bewerberList.remove();
                    }
                }
            }
        }
    }

    public void insertCorrect(ListWithViewer<Location> pList, Location pLocation){
        if(pList == null || pLocation == null){
            System.out.println("Debug List null or Location Null");
            return;
        }
        if(pList.isEmpty()){
            pList.append(pLocation);
            System.out.println("Debug pList Empty");
            return;
        }
        for(pList.toFirst();pList.hasAccess();pList.next()){
            if(pList.getContent().getWochenMiete() > pLocation.getWochenMiete()) {
                pList.insert(pLocation);
                System.out.println("Debug Stelle gefunden");
                break;
            }
        }
        pList.toLast();
        if(pLocation.getWochenMiete()>pList.getContent().getWochenMiete()) {
            pList.append(pLocation);
        }
    }

    public void sortiereLocationsNachPreis(){
        ListWithViewer<Location> helpList = new ListWithViewer<>();
        for(locationList.toFirst();locationList.hasAccess();){
            insertCorrect(helpList, locationList.getContent());
            locationList.remove();
        }
        for(helpList.toFirst();helpList.hasAccess();helpList.next()){
            locationList.append(helpList.getContent());
            //helpList.remove();
        }
    }

    public static void main(String[] args) {
        BacheloristinVerwaltung bv = new BacheloristinVerwaltung();
        new GUI(bv);
    }
}
