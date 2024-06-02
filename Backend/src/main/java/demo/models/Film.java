package demo.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tytul;
    private String opis;
    private int rokWydania;
    private int dlugosc;
    private String plakatURL;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gatunek_id")
    private Gatunek gatunek;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Ocena> opinie = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getRokWydania() {
        return rokWydania;
    }

    public void setRokWydania(int rokWydania) {
        this.rokWydania = rokWydania;
    }

    public int getDlugosc() {
        return dlugosc;
    }

    public void setDlugosc(int dlugosc) {
        this.dlugosc = dlugosc;
    }

    public String getPlakatURL() {
        return plakatURL;
    }

    public void setPlakatURL(String plakatURL) {
        this.plakatURL = plakatURL;
    }

    public Gatunek getGatunek() {
        return gatunek;
    }

    public void setGatunek(Gatunek gatunek) {
        this.gatunek = gatunek;
    }

    public Set<Ocena> getOpinie() {
        return opinie;
    }

    public void setOpinie(Set<Ocena> opinie) {
        this.opinie = opinie;
    }

    public String getNazwaGatunku() {
        return gatunek != null ? gatunek.getNazwa() : "";
    }
}
