package ml.georgedi23.ion_casts.controllers;

import ml.georgedi23.ion_casts.models.Podcast;
import ml.georgedi23.ion_casts.models.PodcastEpisode;
import ml.georgedi23.ion_casts.services.PodcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/ioncast")
public class PodcastController {

    PodcastService podcastService;
    Logger logger;

    @Autowired
    public PodcastController(PodcastService podcastService) {
        logger = Logger.getLogger(PodcastController.class.getName());
        this.podcastService = podcastService;
    }

    @PostMapping("/new")
    public @ResponseBody
    ResponseEntity<Podcast> addNewPodcast(@RequestBody String requestRssUrl){
        Podcast response = null;
        try{
            response = podcastService.addPodcast(requestRssUrl);
            logger.info("Successful call PodcastController::addNewPodcast");
        } catch(Exception e){
            logger.log(Level.SEVERE,"Exception in PodcastController::addNewPodcast: " + e.fillInStackTrace());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public @ResponseBody
    ResponseEntity<List<Podcast>> getPodcasts(){
        List<Podcast> responsePodcasts = new ArrayList<>();
        try{
            responsePodcasts = podcastService.getAllPodcasts();
            logger.info("Successful call PodcastController::getPodcasts");
        } catch (Exception e){
            logger.log(Level.SEVERE,"Exception in PodcastController::getPodcasts: " + e.fillInStackTrace());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(responsePodcasts,HttpStatus.OK);
    }

    @GetMapping("/allEpisodes")
    public @ResponseBody
    ResponseEntity<List<PodcastEpisode>> getEpisodes(@RequestBody Podcast podcast){
        List<PodcastEpisode> responseEpisodes = new ArrayList<>();
        try{
            responseEpisodes = podcastService.getAllEpisodes(podcast);
            logger.info("Successful call PodcastController::getEpisodes");
        } catch (Exception e){
            logger.log(Level.SEVERE,"Exception in PodcastController::getEpisodes: " + e.fillInStackTrace());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(responseEpisodes,HttpStatus.OK);
    }
}