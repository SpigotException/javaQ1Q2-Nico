package hotelSzenario;

public class Hotel {
    private String name;
    private String anschrift;
    private String hotelkette;
    private double bewertungszahl;
    private int anzahlBewertungen;

    public Hotel(String name, String hotelkette, double bewertungszahl, int anzahlBewertungen) {
        this.name = name;
        this.anschrift = anschrift;
        this.hotelkette = hotelkette;
        this.bewertungszahl = bewertungszahl;
        this.anzahlBewertungen = anzahlBewertungen;
    }

    public Hotel(String name, String hotelkette) {
        this.name = name;
        this.hotelkette = hotelkette;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ", anschrift='" + anschrift + '\'' +
                ", hotelkette='" + hotelkette + '\'' +
                ", bewertungszahl=" + bewertungszahl +
                ", anzahlBewertungen=" + anzahlBewertungen +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getAnschrift() {
        return anschrift;
    }

    public void setAnschrift(String anschrift) {
        this.anschrift = anschrift;
    }

    public String getHotelkette() {
        return hotelkette;
    }

    public double getBewertungszahl() {
        return bewertungszahl;
    }

    public void bewerte(int pNeueBewertung) {
        this.bewertungszahl += pNeueBewertung;
        this.anzahlBewertungen++;
    }

    public float getBewertung(){
        return (float) this.bewertungszahl / this.anzahlBewertungen;
    }
    public int getAnzahlBewertungen() {
        return anzahlBewertungen;
    }

}
