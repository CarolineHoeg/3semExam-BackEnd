package facades;

import com.google.gson.Gson;
import dto.FullyRatedMovieDTO;
import dto.MovieDTO;
import dto.ImdbRatedMovieDTO;
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
        //fetch movie info data
        String MovieInfoJSON = HttpUtils.fetchData("https://ex2-0-2-0.mydemos.dk/movieInfo/" + search);
        //use fetched data
        MovieDTO movie = GSON.fromJson(MovieInfoJSON, MovieDTO.class);
        //fetch movie poster data
        String MoviePosterJSON = HttpUtils.fetchData("https://ex2-0-2-0.mydemos.dk/moviePoster/" + search);
        //deconstruct poster data
        JSONObject object = GSON.fromJson(MoviePosterJSON, JSONObject.class);
        //use fetched data
        movie.setPoster(object.get("poster").toString());
        return movie;
    }

    public ImdbRatedMovieDTO getMovieWithImdbByTitle(String title) throws IOException {
        String search = fixSearchStringFormat(title);
        MovieDTO mdto = getMovieByTitle(search);
        //fetch imdb rating data
        String JSON = HttpUtils.fetchData("https://ex2-0-2-0.mydemos.dk/ratings/" + search + "/i");
        //deconstruct rating data
        JSONObject object = GSON.fromJson(JSON, JSONObject.class);
        String imdbJSON = object.get("imdb").toString();
        JSONObject imdb = GSON.fromJson(imdbJSON, JSONObject.class);
        //use fetched data
        ImdbRatedMovieDTO movie = new ImdbRatedMovieDTO(mdto);
        movie.setImdbRating(Double.parseDouble(imdb.get("imdbRating").toString()));
        movie.setImdbVotes(Double.parseDouble(imdb.get("imdbVotes").toString()));
        return movie;
    }

    public FullyRatedMovieDTO getMovieWithAllRatingsByTitle(String title) throws IOException {
        String search = fixSearchStringFormat(title);
        ImdbRatedMovieDTO mdto = getMovieWithImdbByTitle(search);
        FullyRatedMovieDTO movie = new FullyRatedMovieDTO(mdto);
        //fetch metacritics rating data
        String metacriticsJSON = HttpUtils.fetchData("https://ex2-0-2-0.mydemos.dk/ratings/" + search + "/m");
        //deconstruct rating data
        JSONObject metacritics = GSON.fromJson(metacriticsJSON, JSONObject.class);
        //use fetched data
//        movie.setMetacriticRating(Double.parseDouble(metacritics.get("metacritic").toString()));
        //fetch tomato rating data
        String tomatoJSON = HttpUtils.fetchData("https://ex2-0-2-0.mydemos.dk/ratings/" + search + "/t");
        //deconstruct rating data
        JSONObject object = GSON.fromJson(tomatoJSON, JSONObject.class);
        String tomatoesJSON = object.get("tomatoes").toString();
        JSONObject tomatoes = GSON.fromJson(tomatoesJSON, JSONObject.class);
        String viewerJSON = tomatoes.get("viewer").toString();
        JSONObject viewer = GSON.fromJson(viewerJSON, JSONObject.class);
        String criticJSON = tomatoes.get("critic").toString();
        JSONObject critic = GSON.fromJson(criticJSON, JSONObject.class);
        //use fteched data
        movie.setRottenTomatoViewerRating(Double.parseDouble(viewer.get("rating").toString()));
        movie.setRottenTomatoCriticRating(Double.parseDouble(critic.get("rating").toString()));
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
