package com.vehicule.vehicule.Model;

@SyncAnnotation(sync = true)
public class PointCarriere {
    private String id;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    private double xposition;
    public double getXposition() {
        return xposition;
    }
    public void setXposition(double xposition) {
        this.xposition = xposition;
    }
    private double yposition;
    public double getYposition() {
        return yposition;
    }
    public void setYposition(double yposition) {
        this.yposition = yposition;
    }
    private String nompoint;
    public String getNompoint() {
        return nompoint;
    }
    public void setNompoint(String nompoint) {
        this.nompoint = nompoint;
    }
    private  int idcarriere;
    public int getIdcarriere() {
        return idcarriere;
    }
    public void setIdcarriere(int idcarriere) {
        this.idcarriere = idcarriere;
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
