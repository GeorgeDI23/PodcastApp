package ml.georgedi23.ion_casts.controllers;

import ml.georgedi23.ion_casts.services.PodcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ioncast")
public class PodcastController {

    PodcastService podcastService;

    @Autowired
    public PodcastController(PodcastService podcastService) {
    }
}
