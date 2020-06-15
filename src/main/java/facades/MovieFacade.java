package facades;

import dto.MovieDTO;
import dto.RatedMovieDTO;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;
    private final FetchMovieFacade fetchFacade = FetchMovieFacade.getFacade();

    //Private Constructor to ensure Singleton
    private MovieFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public MovieDTO getMovieByTitle(String title) throws IOException {
        return fetchFacade.getMovieByTitle(title);
    }

    public RatedMovieDTO getMovieWithImdbByTitle(String title) throws IOException {
        return fetchFacade.getMovieWithImdbByTitle(title);
    }

}
