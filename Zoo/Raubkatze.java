package Zoo;

public abstract class Raubkatze extends Tier{

    protected int danger;

    public Raubkatze(double pGewicht, String pName, boolean pIsWeiblich, double pPortionsgroesse, int pDanger) {
        super(pGewicht, pName, pIsWeiblich, pPortionsgroesse);
        this.danger = pDanger;
    }

    public int getDanger() {
        return danger;
    }

    public void setDanger(int danger) {
        this.danger = danger;
    }

    @Override
    public void buersten() {
        System.out.println("purrpurrpurr");
    }

    public void bruellen(){
        System.out.println(this.name +  " Rawr");
    }

}
