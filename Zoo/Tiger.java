package Zoo;

public class Tiger extends Raubkatze{
    private int anzahlStreifen;
    public Tiger(double pGewicht, String pName, boolean pIsWeiblich, double pPortionsgroesse, int pDanger) {
        super(pGewicht, pName, pIsWeiblich, pPortionsgroesse, pDanger);
    }

    @Override
    public void fuettern(){
        //setGewicht(getGewicht() * 0,02);
    }

    public int getAnzahlStreifen() {
        return anzahlStreifen;
    }

    public void setAnzahlStreifen(int anzahlStreifen) {
        this.anzahlStreifen = anzahlStreifen;
    }
}
