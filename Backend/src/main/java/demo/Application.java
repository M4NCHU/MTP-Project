package demo;

import demo.models.Film;
import demo.models.Gatunek;
import demo.models.Ocena;
import demo.service.FilmService;
import demo.service.GatunekService;
import demo.service.OcenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import java.util.Date;

@SpringBootApplication
public class Application implements CommandLineRunner, ApplicationListener<ContextClosedEvent> {

    @Autowired
    private FilmService filmService;

    @Autowired
    private GatunekService gatunekService;

    @Autowired
    private OcenaService ocenaService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Tworzenie i dodawanie gatunków
        /*Gatunek gatunek1 = new Gatunek();
        gatunek1.setNazwa("Komedia");
        gatunek1.setOpis("Filmy pełne humoru");
        gatunekService.save(gatunek1);

        // Tworzenie i dodawanie filmów
        Film film1 = new Film();
        film1.setTytul("Przykładowy film");
        film1.setOpis("To jest przykład opisu filmu.");
        film1.setRokWydania(2020);
        film1.setDlugosc(120);
        film1.setGatunek(gatunek1);
        filmService.save(film1);

        // Tworzenie i dodawanie ocen
        Ocena ocena1 = new Ocena();
        ocena1.setOcena(5);
        ocena1.setTresc("Świetny film!");
        ocena1.setDataDodania(new Date());
        ocena1.setFilm(film1);
        ocenaService.save(ocena1);*/

        System.out.println("Rozpoczęto działanie.");
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        
        System.out.println("Zakończono działanie.");
    }
}
