package ml.georgedi23.ion_casts.controllers;

import ml.georgedi23.ion_casts.models.Podcast;
import ml.georgedi23.ion_casts.repositories.PodcastRepository;
import ml.georgedi23.ion_casts.services.PodcastService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(value = PodcastController.class)
@ContextConfiguration(classes = {PodcastService.class, PodcastRepository.class})
public class PodcastControllerTest {

    @Mock
    PodcastService podcastService;

    @Mock
    PodcastRepository podcastRepository;

    @InjectMocks
    PodcastController podcastController;

    private MockMvc mockMvc;

    private String givenPodcastJson;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(podcastController).build();
        this.givenPodcastJson = "{\n" +
                "\"podcast_id\": 229,\n" +
                "\"title\": \"Accidental Tech Podcast\",\n" +
                "\"link\": \"https://atp.fm/rss\",\n" +
                "\"description\": \"Three nerds discussing tech, Apple, programming, and loosely related matters.\",\n" +
                "\"image\": null,\n" +
                "\"podcastEpisodes\": null,\n" +
                "\"downloadedEpisodes\": null\n" +
                "}";
    }

    @Test
    public void addNewPodcastTest() throws Exception {
        //Given
        String givenUrl = "http://www.testrss.com/";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/ioncast/new")
                .contentType(MediaType.TEXT_PLAIN).content(givenUrl)
                .accept(MediaType.TEXT_PLAIN);

        //When
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        //Then
        assertEquals(HttpStatus.CREATED.value(),response.getStatus());
    }

    @Test
    public void addNewPodcastServiceCallTest() throws Exception {
        //Given
        String givenUrl = "http://www.testrss.com/";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/ioncast/new")
                .contentType(MediaType.TEXT_PLAIN).content(givenUrl)
                .accept(MediaType.TEXT_PLAIN);

        //When
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //Then
        Mockito.verify(podcastService, Mockito.times(1)).addPodcast(givenUrl);
    }

    @Test
    public void getPodcastsTest() throws Exception {
        //Given
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ioncast/all");

        //When
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        //Then
        assertEquals(HttpStatus.OK.value(),response.getStatus());
    }

    @Test
    public void getPodcastsServiceCallTest() throws Exception {
        //Given
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ioncast/all");

        //When
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //Then
        Mockito.verify(podcastService, Mockito.times(1)).getAllPodcasts();
    }

    @Test
    public void getEpisodesTest() throws Exception {
        //Given
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ioncast/allEpisodes")
                .contentType(MediaType.APPLICATION_JSON).content(givenPodcastJson)
                .accept(MediaType.APPLICATION_JSON);

        //When
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        //Then
        assertEquals(HttpStatus.OK.value(),response.getStatus());
    }

    @Test
    public void getEpisodesServiceCallTest() throws Exception {
        //Given
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ioncast/allEpisodes")
                .contentType(MediaType.APPLICATION_JSON).content(givenPodcastJson)
                .accept(MediaType.APPLICATION_JSON);

        //When
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //Then
        Mockito.verify(podcastService, Mockito.times(1)).getAllEpisodes(any(Podcast.class));
    }
}