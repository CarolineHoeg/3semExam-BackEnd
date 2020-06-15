package facades;

import dto.MovieDTO;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author carol
 */
public class FetchMovieFacadeTest {

    private static final FetchMovieFacade FACADE = FetchMovieFacade.getFacade();

    @Test
    public void testGetMovieByTitle() throws IOException {
        String title = "Die Hard";
        MovieDTO expMovie = new MovieDTO("Die Hard", 1988, "John McClane, officer of the NYPD, "
                + "tries to save wife Holly Gennaro and several others, taken hostage by German "
                + "terrorist Hans Gruber during a Christmas party at the Nakatomi Plaza in Los Angeles.",
                "John McTiernan", "Action,Thriller", "Bruce Willis,Bonnie Bedelia,"
                + "Reginald VelJohnson,Paul Gleason");
        expMovie.setPoster("https://m.media-amazon.com/images/M/MV5BZjRlNDUxZjAtOGQ4OC00OTNlLTgxNmQtYTBmMDgwZmNmNjkxXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_SX677_AL_.jpg");
        MovieDTO movie = FACADE.getMovieByTitle(title);
        assertEquals(expMovie, movie);
    }

}
