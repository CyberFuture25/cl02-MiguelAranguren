package pe.edu.cibertec.cl02MiguelAranguren.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
//No poner @Table innecesariamente si la clase tiene el mismo nombre que la tabla de la bd
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer filmId; //se traduce a film_id por JPA
    private String title;
    private String description;
    private Integer releaseYear;

    //private Integer languageId; Lo sacamos, fk representada abajo

    private Integer originalLanguageId;
    private Integer rentalDuration;
    private Double rentalRate;
    private Integer length;
    private Double replacementCost;
    private String rating; //En la bd era enum, simplemente ponerle string aquí
    private String specialFeatures;
    private Date lastUpdate;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

}
