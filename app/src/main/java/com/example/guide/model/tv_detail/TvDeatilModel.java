package com.example.guide.model.tv_detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvDeatilModel {
    @SerializedName("backdrop_path")
    @Expose
    public Object backdropPath;
    @SerializedName("created_by")
    @Expose
    public List<Object> createdBy = null;
    @SerializedName("episode_run_time")
    @Expose
    public List<Integer> episodeRunTime = null;
    @SerializedName("first_air_date")
    @Expose
    public String firstAirDate;
    @SerializedName("genres")
    @Expose
    public List<Genre> genres = null;
    @SerializedName("homepage")
    @Expose
    public String homepage;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("in_production")
    @Expose
    public Boolean inProduction;
    @SerializedName("languages")
    @Expose
    public List<String> languages = null;
    @SerializedName("last_air_date")
    @Expose
    public String lastAirDate;
    @SerializedName("last_episode_to_air")
    @Expose
    public List<LastEpisodeToAir> lastEpisodeToAir = null;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("next_episode_to_air")
    @Expose
    public Object nextEpisodeToAir;
    @SerializedName("networks")
    @Expose
    public List<Network> networks = null;
    @SerializedName("number_of_episodes")
    @Expose
    public Integer numberOfEpisodes;
    @SerializedName("number_of_seasons")
    @Expose
    public Integer numberOfSeasons;
    @SerializedName("origin_country")
    @Expose
    public List<String> originCountry = null;
    @SerializedName("original_language")
    @Expose
    public String originalLanguage;
    @SerializedName("original_name")
    @Expose
    public String originalName;
    @SerializedName("overview")
    @Expose
    public String overview;
    @SerializedName("popularity")
    @Expose
    public Double popularity;
    @SerializedName("poster_path")
    @Expose
    public Object posterPath;
    @SerializedName("production_companies")
    @Expose
    public List<ProductionCompany> productionCompanies = null;
    @SerializedName("production_countries")
    @Expose
    public List<Object> productionCountries = null;
    @SerializedName("seasons")
    @Expose
    public List<Season> seasons = null;
    @SerializedName("spoken_languages")
    @Expose
    public List<SpokenLanguage> spokenLanguages = null;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("tagline")
    @Expose
    public String tagline;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("vote_average")
    @Expose
    public Double voteAverage;
    @SerializedName("vote_count")
    @Expose
    public Integer voteCount;

    public class Genre {

        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("name")
        @Expose
        public String name;
    }

    public class LastEpisodeToAir {

        @SerializedName("air_date")
        @Expose
        public String airDate;
        @SerializedName("episode_number")
        @Expose
        public Integer episodeNumber;
        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("overview")
        @Expose
        public String overview;
        @SerializedName("production_code")
        @Expose
        public String productionCode;
        @SerializedName("season_number")
        @Expose
        public Integer seasonNumber;
        @SerializedName("still_path")
        @Expose
        public Object stillPath;
        @SerializedName("vote_average")
        @Expose
        public Double voteAverage;
        @SerializedName("vote_count")
        @Expose
        public Integer voteCount;
    }

    public class Network {

        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("logo_path")
        @Expose
        public String logoPath;
        @SerializedName("origin_country")
        @Expose
        public String originCountry;

    }
    public class ProductionCompany {

        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("logo_path")
        @Expose
        public Object logoPath;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("origin_country")
        @Expose
        public String originCountry;
    }
    public class Season {

        @SerializedName("air_date")
        @Expose
        public Object airDate;
        @SerializedName("episode_count")
        @Expose
        public Integer episodeCount;
        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("overview")
        @Expose
        public String overview;
        @SerializedName("poster_path")
        @Expose
        public Object posterPath;
        @SerializedName("season_number")
        @Expose
        public Integer seasonNumber;
    }
    public class SpokenLanguage {

        @SerializedName("english_name")
        @Expose
        public String englishName;
        @SerializedName("iso_639_1")
        @Expose
        public String iso6391;
        @SerializedName("name")
        @Expose
        public String name;
    }
}
