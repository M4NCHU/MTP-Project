package demo.dto;

public class FilmDTO {
    private Long id;
    private String tytul;
    private String opis;
    private int rokWydania;
    private int dlugosc;
    private String plakatURL;
    private String nazwaGatunku;

    // Constructor
    public FilmDTO(Long id, String tytul, String opis, int rokWydania, int dlugosc, String plakatURL, String nazwaGatunku) {
        this.id = id;
        this.tytul = tytul;
        this.opis = opis;
        this.rokWydania = rokWydania;
        this.dlugosc = dlugosc;
        this.plakatURL = plakatURL;
        this.nazwaGatunku = nazwaGatunku;
    }

    // Getters and setters
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

    public String getNazwaGatunku() {
        return nazwaGatunku;
    }

    public void setNazwaGatunku(String nazwaGatunku) {
        this.nazwaGatunku = nazwaGatunku;
    }
}
