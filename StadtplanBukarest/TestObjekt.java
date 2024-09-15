package StadtplanBukarest;

public class TestObjekt {

    int gewicht;
    String vorgägnger;
    String id;


    public TestObjekt(int gewicht, String vorgägnger , String pId) {
        this.gewicht = gewicht;
        this.vorgägnger = vorgägnger;
        this.id = pId;
    }

    public int getGewicht() {
        return gewicht;
    }

    public void setGewicht(int gewicht) {
        this.gewicht = gewicht;
    }

    public String getVorgägnger() {
        return vorgägnger;
    }

    @Override
    public String toString() {
        return "TestObjekt{" +
                "gewicht=" + gewicht +
                ", vorgägnger='" + vorgägnger + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setVorgägnger(String vorgägnger) {
        this.vorgägnger = vorgägnger;
    }
}
