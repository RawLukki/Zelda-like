package application.modele.objet.objetDivers;



public class LegendaryRing extends ObjetDivers {

    private int bonusAtt;
    private int bonusDef;
    private int bonusHealth;



    public LegendaryRing(String nom){
        super(nom, "LegendaryRing");
        this.bonusAtt = 40;
        this.bonusDef = 20;
        this.bonusHealth = 200;
    }



    public int getBonusAtt(){
        return this.bonusAtt;
    }



    public int getBonusDef(){
        return this.bonusDef;
    }



    public int getBonusHealth(){
        return this.bonusHealth;
    }



    public void setBonusAtt(int bonusAtt) {
        this.bonusAtt = bonusAtt;
    }



    public void setBonusDef(int bonusDef) {
        this.bonusDef = bonusDef;
    }



    public void setBonusHealth(int bonusHealth) {
        this.bonusHealth = bonusHealth;
    }



    public String toString(){
        return super.toString() + "la bague légendaire";
    }


}
