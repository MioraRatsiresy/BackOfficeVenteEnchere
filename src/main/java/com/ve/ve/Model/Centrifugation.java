package com.vehicule.vehicule.Model;

@SyncAnnotation(sync = true)
public class Centrifugation {
    private String id;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    private String idpoint;
    public String getIdpoint() {
        return idpoint;
    }
    public void setIdpoint(String idpoint) {
        this.idpoint = idpoint;
    }
    private double quantiteVato;
    public double getQuantiteVato() {
        return quantiteVato;
    }
    public void setQuantiteVato(double quantiteVato) {
        this.quantiteVato = quantiteVato;
    }
    private double quantiteVolamena;
    public double getQuantiteVolamena() {
        return quantiteVolamena;
    }
    public void setQuantiteVolamena(double quantiteVolamena) {
        this.quantiteVolamena = quantiteVolamena;
    }
    private String dateheuredebut;
    public String getDateheuredebut() {
        return dateheuredebut;
    }
    public void setDateheuredebut(String dateheuredebut) {
        this.dateheuredebut = dateheuredebut;
    }
    private String dateheurefin;
    public String getDateheurefin() {
        return dateheurefin;
    }
    public void setDateheurefin(String dateheurefin) {
        this.dateheurefin = dateheurefin;
    }
    private int etat;
    public int getEtat() {
        return etat;
    }
    public void setEtat(int etat) {
        this.etat = etat;
    }
    private int envoyeur1;
    public int getEnvoyeur1() {
        return envoyeur1;
    }
    public void setEnvoyeur1(int envoyeur1) {
        this.envoyeur1 = envoyeur1;
    }
    private int envoyeur2;
    public int getEnvoyeur2() {
        return envoyeur2;
    }
    public void setEnvoyeur2(int envoyeur2) {
        this.envoyeur2 = envoyeur2;
    }
}
