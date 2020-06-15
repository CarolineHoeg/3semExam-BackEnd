package facades;

import dto.FullyRatedMovieDTO;
import dto.MovieDTO;
import dto.ImdbRatedMovieDTO;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author carol
 */
public class FetchMovieFacadeTest {

    private static final FetchMovieFacade FACADE = FetchMovieFacade.getFacade();
    private MovieDTO mdto = new MovieDTO("Die Hard", 1988, "John McClane, officer of the NYPD, "
                + "tries to save wife Holly Gennaro and several others, taken hostage by German "
                + "terrorist Hans Gruber during a Christmas party at the Nakatomi Plaza in Los Angeles.",
                "John McTiernan", "Action,Thriller", "Bruce Willis,Bonnie Bedelia,"
                + "Reginald VelJohnson,Paul Gleason");

    @Test
    public void testGetMovieByTitle() throws IOException {
        String title = "Die Hard";
        MovieDTO expMovie = mdto;
        expMovie.setPoster("https://m.media-amazon.com/images/M/MV5BZjRlNDUxZjAtOGQ4OC00OTNlLTgxNmQtYTBmMDgwZmNmNjkxXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_SX677_AL_.jpg");
        MovieDTO movie = FACADE.getMovieByTitle(title);
        assertEquals(expMovie, movie);
    }
    
    @Test
    public void testGetMovieWithImdbByTitle() throws IOException {
        String title = "Die Hard";
        mdto.setPoster("https://m.media-amazon.com/images/M/MV5BZjRlNDUxZjAtOGQ4OC00OTNlLTgxNmQtYTBmMDgwZmNmNjkxXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_SX677_AL_.jpg");
        ImdbRatedMovieDTO expMovie = new ImdbRatedMovieDTO(mdto);
        expMovie.setImdbRating(8.3);
        expMovie.setImdbVotes(535036);
        ImdbRatedMovieDTO movie = FACADE.getMovieWithImdbByTitle(title);
        assertEquals(expMovie, movie);
    }
    
    @Test 
    public void testGetMovieWithAllRatingsByTitle() throws IOException {
        String title = "Die Hard";
        mdto.setPoster("https://m.media-amazon.com/images/M/MV5BZjRlNDUxZjAtOGQ4OC00OTNlLTgxNmQtYTBmMDgwZmNmNjkxXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_SX677_AL_.jpg");
        ImdbRatedMovieDTO mImdb = new ImdbRatedMovieDTO(mdto);
        mImdb.setImdbRating(8.3);
        mImdb.setImdbVotes(535036);
        FullyRatedMovieDTO expMovie = new FullyRatedMovieDTO(mImdb);
        expMovie.setMetacriticRating(0);
        expMovie.setRottenTomatoViewerRating(3.9);
        expMovie.setRottenTomatoCriticRating(8.4);
        FullyRatedMovieDTO movie = FACADE.getMovieWithAllRatingsByTitle(title);
        assertEquals(expMovie, movie);
    }

}
