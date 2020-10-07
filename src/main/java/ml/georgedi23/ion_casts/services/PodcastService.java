package ml.georgedi23.ion_casts.services;

import ml.georgedi23.ion_casts.models.Podcast;
import ml.georgedi23.ion_casts.models.PodcastEpisode;
import ml.georgedi23.ion_casts.repositories.PodcastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.List;


@Service
public class PodcastService {

    ParsingService parser;
    PodcastRepository podcastRepo;

    @Autowired
    public PodcastService(ParsingService parser, PodcastRepository podcastRepo) {
        this.parser = parser;
        this.podcastRepo = podcastRepo;
    }

    public Podcast addPodcast(String podcastUrl) throws MalformedURLException {
        Podcast podcast = parser.parseNewPodcast(podcastUrl);
        if(podcast == null){
            throw new MalformedURLException();
        }
        if(podcastRepo.existsByTitle(podcast.getTitle())){
            return podcastRepo.getByTitle(podcast.getTitle());
        }
        podcastRepo.save(podcast);
        return podcast;
    }

    public List<Podcast> getAllPodcasts(){
        return podcastRepo.findAll();
    }

    public List<PodcastEpisode> getAllEpisodes(Podcast podcast) throws MalformedURLException {
        List<PodcastEpisode> episodes = parser.getEpisodes(podcast);
        if(episodes == null){
            throw new MalformedURLException();
        }
        podcast.setPodcastEpisodes(episodes);
        podcastRepo.save(podcast);
        return episodes;
    }

    //public List<PodcastEpisode> getNewEpisodes(String podcastUrl){}

    // get downloaded episode list

}

