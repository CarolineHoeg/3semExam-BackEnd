package dto;

import java.util.Objects;

/**
 *
 * @author carol
 */
public class ImdbRatedMovieDTO {
    
    private MovieDTO movie;
    private double imdbRating;
    private double imdbVotes;
    
    public ImdbRatedMovieDTO(MovieDTO movie) {
        this.movie = movie;
    }
    
    public double getImdbRating() {
        return imdbRating;
    }
    
    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public double getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(double imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.movie);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.imdbRating) ^ (Double.doubleToLongBits(this.imdbRating) >>> 32));
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.imdbVotes) ^ (Double.doubleToLongBits(this.imdbVotes) >>> 32));
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
        final ImdbRatedMovieDTO other = (ImdbRatedMovieDTO) obj;
        if (Double.doubleToLongBits(this.imdbRating) != Double.doubleToLongBits(other.imdbRating)) {
            return false;
        }
        if (Double.doubleToLongBits(this.imdbVotes) != Double.doubleToLongBits(other.imdbVotes)) {
            return false;
        }
        if (!Objects.equals(this.movie, other.movie)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RatedMovieDTO{" + "movie=" + movie + ", imdbRating=" + imdbRating + ", imdbVotes=" + imdbVotes + '}';
    }
}
