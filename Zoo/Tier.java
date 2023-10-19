package Zoo;

public abstract class Tier {
    protected double gewicht;
    protected String name;
    protected boolean isWeiblich;
    protected double portionsGroesse;

    public Tier(double pGewicht, String pName, boolean pIsWeiblich, double pPortionsgroesse){
        this.gewicht = pGewicht;
        this.name = pName;
        this.isWeiblich = pIsWeiblich;
        this.portionsGroesse = pPortionsgroesse;
    }

    public void fuettern(){
        this.gewicht += this.portionsGroesse;
    }

    public void buersten(){
        System.out.println(this.name + " wird gebürstet.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWeiblich() {
        return isWeiblich;
    }

    public void setWeiblich(boolean weiblich) {
        isWeiblich = weiblich;
    }

    public double getPortionsGroesse() {
        return portionsGroesse;
    }

    public void setPortionsGroesse(double portionsGroesse) {
        this.portionsGroesse = portionsGroesse;
    }

    public double getGewicht() {
        return gewicht;
    }

    public void setGewicht(double gewicht) {
        this.gewicht = gewicht;
    }
}
