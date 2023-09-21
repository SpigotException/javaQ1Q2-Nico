package bacheloristin;

public class Kandidat {


    private String name;
    private int gebDatum;
    private boolean weiblich;
    private int quotenPunkte;
    private boolean nochDabei;

    public Kandidat(String pName, int pGebDatum, boolean pWeiblich){
        name = pName;
        gebDatum = pGebDatum;
        weiblich = pWeiblich;
        nochDabei = true;
        quotenPunkte = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGebDatum() {
        return gebDatum;
    }

    public void setGebDatum(int gebDatum) {
        this.gebDatum = gebDatum;
    }

    public boolean isWeiblich() {
        return weiblich;
    }

    public void setWeiblich(boolean weiblich) {
        this.weiblich = weiblich;
    }

    public int getQuotenPunkte() {
        return quotenPunkte;
    }

    public void setQuotenPunkte(int quotenPunkte) {
        this.quotenPunkte = quotenPunkte;
    }

    public boolean isNochDabei() {
        return nochDabei;
    }

    public void setNochDabei(boolean nochDabei) {
        this.nochDabei = nochDabei;
    }

    // Methoden

    public void erhoeheQuotenPunkte(int pErhoehung){
        if(pErhoehung>=0) {
            quotenPunkte += pErhoehung;
        }
    }

    public void senkeQuotenPunkte(int pSenkung){
        if(pSenkung >= 0){
            if(pSenkung<=quotenPunkte) {
                quotenPunkte -= pSenkung;
            }else{
                quotenPunkte = 0;
            }
        }

    }

    public void resetQuotenPunkte(){
        quotenPunkte = 0;
    }

    public void fliegtRaus(){
        nochDabei = false;
    }

    public boolean istAelterAls(Kandidat pAnderer){
        if(this.getGebDatum() < pAnderer.getGebDatum()){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", punkte: " + quotenPunkte;
    }
}
