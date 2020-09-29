package ml.georgedi23.ion_casts.services;

import ml.georgedi23.ion_casts.models.Podcast;
import ml.georgedi23.ion_casts.repositories.PodcastRepository;

public class PodcastService {

    ParsingService parser;
    PodcastRepository podcastRepo;

    public PodcastService(ParsingService parser, PodcastRepository podcastRepo) {
        this.parser = parser;
        this.podcastRepo = podcastRepo;
    }


    public Podcast addNewPodcast(String podcastUrl){
        return null;
    }

    //public get all episodes
        // on angular end, have display 10 at a time

    // get downloaded episode list

}

