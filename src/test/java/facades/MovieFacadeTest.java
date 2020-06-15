package facades;

import dto.MovieDTO;
import dto.ImdbRatedMovieDTO;
import java.io.IOException;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MovieFacadeTest {

    private static EntityManagerFactory emf;
    private static MovieFacade facade;

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        facade = MovieFacade.getFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
//    @BeforeEach
//    public void setUp() {
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//    }
    @Test
    public void testGetMovieByTitle() throws IOException {
        String title = "Die Hard";
        MovieDTO expMovie = new MovieDTO("Die Hard", 1988, "John McClane, officer of the NYPD, "
                + "tries to save wife Holly Gennaro and several others, taken hostage by German "
                + "terrorist Hans Gruber during a Christmas party at the Nakatomi Plaza in Los Angeles.",
                "John McTiernan", "Action,Thriller", "Bruce Willis,Bonnie Bedelia,"
                + "Reginald VelJohnson,Paul Gleason");
        expMovie.setPoster("https://m.media-amazon.com/images/M/MV5BZjRlNDUxZjAtOGQ4OC00OTNlLTgxNmQtYTBmMDgwZmNmNjkxXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_SX677_AL_.jpg");
        MovieDTO movie = facade.getMovieByTitle(title);
        assertEquals(expMovie, movie);
    }
    
    @Test
    public void testGetMovieWithImdbByTitle() throws IOException {
        String title = "Die Hard";
        MovieDTO mdto = new MovieDTO("Die Hard", 1988, "John McClane, officer of the NYPD, "
                + "tries to save wife Holly Gennaro and several others, taken hostage by German "
                + "terrorist Hans Gruber during a Christmas party at the Nakatomi Plaza in Los Angeles.",
                "John McTiernan", "Action,Thriller", "Bruce Willis,Bonnie Bedelia,"
                + "Reginald VelJohnson,Paul Gleason");
        mdto.setPoster("https://m.media-amazon.com/images/M/MV5BZjRlNDUxZjAtOGQ4OC00OTNlLTgxNmQtYTBmMDgwZmNmNjkxXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_SX677_AL_.jpg");
        ImdbRatedMovieDTO expMovie = new ImdbRatedMovieDTO(mdto);
        expMovie.setImdbRating(8.3);
        expMovie.setImdbVotes(535036);
        ImdbRatedMovieDTO movie = facade.getMovieWithImdbByTitle(title);
        assertEquals(expMovie, movie);
    }

}
