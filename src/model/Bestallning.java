package model;

import java.time.LocalDate;

public class Bestallning {

    private int id;
    private LocalDate bestallningsDatum;

    public Bestallning(int id, LocalDate bestallningsDatum) {
        this.id = id;
        this.bestallningsDatum = bestallningsDatum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getBestallningsDatum() {
        return bestallningsDatum;
    }

    public void setBestallningsDatum(LocalDate bestallningsDatum) {
        this.bestallningsDatum = bestallningsDatum;
    }
}
