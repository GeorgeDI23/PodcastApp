package ml.georgedi23.ion_casts.services;

import ml.georgedi23.ion_casts.models.Podcast;
import ml.georgedi23.ion_casts.models.PodcastEpisode;

import java.util.List;
import java.util.logging.Logger;

public class ParsingService {

    private Logger logger;

    public ParsingService() {
        this.logger = Logger.getLogger(ParsingService.class.getName());
    }

    // Gets data for a new podcast
    public Podcast parseNewPodcast(String podcastUrl){
        Podcast podcast = new Podcast();
        return podcast;
    }

    // returns all episodes of the podcast
    public List<PodcastEpisode> getEpisodes(Podcast podcast){
        return null;
    }
}

