package facades;

import com.google.gson.Gson;
import dto.MovieDTO;
import dto.RatedMovieDTO;
import java.io.IOException;
import net.minidev.json.JSONObject;
import utils.HttpUtils;

/**
 *
 * @author carol
 */
public class FetchMovieFacade {

    private static FetchMovieFacade instance;
    private static final Gson GSON = new Gson();

    //Private Constructor to ensure Singleton
    private FetchMovieFacade() {
    }

    public static FetchMovieFacade getFacade() {
        if (instance == null) {
            instance = new FetchMovieFacade();
        }
        return instance;
    }

    public MovieDTO getMovieByTitle(String title) throws IOException {
        String search = fixSearchStringFormat(title);
        String MovieInfoJSON = HttpUtils.fetchData("https://ex2-0-2-0.mydemos.dk/movieInfo/" + search);
        MovieDTO movie = GSON.fromJson(MovieInfoJSON, MovieDTO.class);
        String MoviePosterJSON = HttpUtils.fetchData("https://ex2-0-2-0.mydemos.dk/moviePoster/" + search);
        JSONObject object = GSON.fromJson(MoviePosterJSON, JSONObject.class);
        movie.setPoster(object.get("poster").toString());
        return movie;
    }

    public RatedMovieDTO getMovieWithImdbByTitle(String title) throws IOException {
        String search = fixSearchStringFormat(title);
        MovieDTO mdto = getMovieByTitle(search);
        String JSON = HttpUtils.fetchData("https://ex2-0-2-0.mydemos.dk/ratings/" + search + "/i");
        JSONObject object = GSON.fromJson(JSON, JSONObject.class);
        String imdbJSON = object.get("imdb").toString();
        JSONObject imdb = GSON.fromJson(imdbJSON, JSONObject.class);
        RatedMovieDTO movie = new RatedMovieDTO(mdto);
        movie.setImdbRating(Double.parseDouble(imdb.get("imdbRating").toString()));
        movie.setImdbVotes(Double.parseDouble(imdb.get("imdbVotes").toString()));
        return movie;
    }

    private String fixSearchStringFormat(String title) {
        if (title.contains(" ")) {
            return title.replaceAll(" ", "%20");
        } else {
            return title;
        }
    }
    
}
