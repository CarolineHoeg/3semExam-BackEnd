package dto;

import java.util.Objects;

/**
 *
 * @author carol
 */
public class MovieDTO {
    
    private final String title;
    private final int year;
    private final String plot;
    private final String directors;
    private final String genres;
    private final String cast;
    private String poster;
    
    public MovieDTO(String title, int year, String plot, String directors, String genres, String cast) {
        this.title = title;
        this.year = year;
        this.plot = plot;
        this.directors = directors;
        this.genres = genres;
        this.cast = cast;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getPlot() {
        return plot;
    }

    public String getDirectors() {
        return directors;
    }

    public String getGenres() {
        return genres;
    }

    public String getCast() {
        return cast;
    }

    public String getPoster() {
        return poster;
    }
    
    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.title);
        hash = 31 * hash + this.year;
        hash = 31 * hash + Objects.hashCode(this.plot);
        hash = 31 * hash + Objects.hashCode(this.directors);
        hash = 31 * hash + Objects.hashCode(this.genres);
        hash = 31 * hash + Objects.hashCode(this.cast);
        hash = 31 * hash + Objects.hashCode(this.poster);
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
        final MovieDTO other = (MovieDTO) obj;
        if (this.year != other.year) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.plot, other.plot)) {
            return false;
        }
        if (!Objects.equals(this.directors, other.directors)) {
            return false;
        }
        if (!Objects.equals(this.genres, other.genres)) {
            return false;
        }
        if (!Objects.equals(this.cast, other.cast)) {
            return false;
        }
        if (!Objects.equals(this.poster, other.poster)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MovieDTO{" + "title=" + title + ", year=" + year + ", plot=" + plot + ", directors=" + directors + ", genres=" + genres + ", cast=" + cast + ", poster=" + poster + '}';
    }

}