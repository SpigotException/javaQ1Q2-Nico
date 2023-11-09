package Zoo;

public class Zoo {
    private Tier[] tiere;

    public Zoo(int pTiere){
        tiere = new Tier[pTiere];
        initTiere();
    }

    public void initTiere(){
        //pass
    }

    public void alleFuettern(){
        for(Tier tier: tiere){
            tier.fuettern();
        }
    }

    public void alleBuersten(){
        for(Tier tier: tiere){
            tier.buersten();
        }
    }

    public void raubisBruellenLassen(){
        for(Tier tier: tiere){
            if (tier instanceof Raubkatze){
                ((Raubkatze)tier).bruellen();
            }

        }
    }
}
