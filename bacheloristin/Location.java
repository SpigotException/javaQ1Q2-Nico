package bacheloristin;

public class Location {
    private String ort;
    private String name;
    private int wochenMiete;
    private String ansprechPartner;

    public Location(String ort, String name) {
        this.ort = ort;
        this.name = name;
        this.wochenMiete = 0;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWochenMiete() {
        return wochenMiete;
    }

    public void setWochenMiete(int wochenMiete) {
        this.wochenMiete = wochenMiete;
    }

    public String getAnsprechPartner() {
        return ansprechPartner;
    }

    public void setAnsprechPartner(String ansprechPartner) {
        this.ansprechPartner = ansprechPartner;
    }

    public int getMonatsMiete(){
        return wochenMiete*4;
    }

    @Override
    public String toString() {
        return "Ort: " + ort + ", Preis: " + wochenMiete;
    }
}
