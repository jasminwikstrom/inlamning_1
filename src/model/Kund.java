package model;

import java.math.BigDecimal;

public class Kund {

    private int id;
    private String fornamn;
    private String efternamn;
    private BigDecimal totaltBelopp;

    public Kund(int id, String fornamn, String efternamn, BigDecimal totaltBelopp){
        this.id = id;
        this.fornamn = fornamn;
        this.efternamn = efternamn;
        this.totaltBelopp = totaltBelopp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFornamn() {
        return fornamn;
    }

    public void setFornamn(String fornamn) {
        this.fornamn = fornamn;
    }

    public String getEfternamn() {
        return efternamn;
    }

    public void setEfternamn(String efternamn) {
        this.efternamn = efternamn;
    }

    public BigDecimal getTotaltBelopp() {
        return totaltBelopp;
    }

    public void setTotaltBelopp(BigDecimal totaltBelopp) {
        this.totaltBelopp = totaltBelopp;
    }

    @Override
    public String toString() {
        return "Kund{" +
                "fornamn='" + fornamn + '\'' +
                ", efternamn='" + efternamn + '\'' +
                ", totaltBelopp=" + totaltBelopp +
                '}';
    }
}