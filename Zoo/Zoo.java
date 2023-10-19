package Zoo;

public class Zoo {
    private Tier[] tiere;

    public Zoo(int pTiere){
        tiere = new Tier[pTiere];
        initTiere();
    }

    public void initTiere(){
        for(int i=0;i< tiere.length;i++){

        }
    }

    public void alleFuettern(){
        for(int i=0;i<tiere.length;i++){
            tiere[i].fuettern();
        }
    }

    public void alleBuersten(){
        for(int i=0;i<tiere.length;i++){
            tiere[i].buersten();
        }
    }

    public void raubisBruellenLassen(){
        for(int i=0;i<tiere.length;i++){
            if (tiere[i] instanceof Raubkatze){
                (Raubkatze)tiere[i].bruellen();
            }

        }
    }
}
