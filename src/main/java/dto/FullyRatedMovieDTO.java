package dto;

import java.util.Objects;

/**
 *
 * @author carol
 */
public class FullyRatedMovieDTO {
    
    private ImdbRatedMovieDTO imdb;
    private double metacriticRating;
    private double rottenTomatoViewerRating;
    private double rottenTomatoCriticRating;
    
    public FullyRatedMovieDTO(ImdbRatedMovieDTO imdb) {
        this.imdb = imdb;
    }

    public double getMetacriticRating() {
        return metacriticRating;
    }

    public void setMetacriticRating(double metacriticRating) {
        this.metacriticRating = metacriticRating;
    }

    public double getRottenTomatoViewerRating() {
        return rottenTomatoViewerRating;
    }

    public void setRottenTomatoViewerRating(double rottenTomatoViewerRating) {
        this.rottenTomatoViewerRating = rottenTomatoViewerRating;
    }

    public double getRottenTomatoCriticRating() {
        return rottenTomatoCriticRating;
    }

    public void setRottenTomatoCriticRating(double rottenTomatoCriticRating) {
        this.rottenTomatoCriticRating = rottenTomatoCriticRating;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.imdb);
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.metacriticRating) ^ (Double.doubleToLongBits(this.metacriticRating) >>> 32));
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.rottenTomatoViewerRating) ^ (Double.doubleToLongBits(this.rottenTomatoViewerRating) >>> 32));
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.rottenTomatoCriticRating) ^ (Double.doubleToLongBits(this.rottenTomatoCriticRating) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FullyRatedMovieDTO other = (FullyRatedMovieDTO) obj;
        if (Double.doubleToLongBits(this.metacriticRating) != Double.doubleToLongBits(other.metacriticRating)) {
            return false;
        }
        if (Double.doubleToLongBits(this.rottenTomatoViewerRating) != Double.doubleToLongBits(other.rottenTomatoViewerRating)) {
            return false;
        }
        if (Double.doubleToLongBits(this.rottenTomatoCriticRating) != Double.doubleToLongBits(other.rottenTomatoCriticRating)) {
            return false;
        }
        if (!Objects.equals(this.imdb, other.imdb)) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "FullyRatedMovieDTO{" + "imdb=" + imdb + ", metacriticRating=" + metacriticRating + ", rottenTomatoViewerRating=" + rottenTomatoViewerRating + ", rottenTomatoCriticRating=" + rottenTomatoCriticRating + '}';
    }
    
}
