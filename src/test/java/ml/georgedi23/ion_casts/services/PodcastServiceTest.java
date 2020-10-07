package ml.georgedi23.ion_casts.services;

import ml.georgedi23.ion_casts.models.Podcast;
import ml.georgedi23.ion_casts.models.PodcastEpisode;
import ml.georgedi23.ion_casts.repositories.PodcastRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class PodcastServiceTest {

    @Mock
    PodcastRepository podcastRepository;

    @Mock
    ParsingService parsingService;

    @InjectMocks
    PodcastService podcastService;

    @Test
    public void addPodcastTest() throws MalformedURLException {
        //Given
        String givenUrl = "http://www.test.com";
        Mockito.when(podcastRepository.save(any(Podcast.class))).thenReturn(stubPodcast());
        Mockito.when(podcastRepository.existsByTitle(any(String.class))).thenReturn(false);
        Mockito.when(parsingService.parseNewPodcast(any(String.class))).thenReturn(stubPodcast());

        //When
        Podcast podcast = podcastService.addPodcast(givenUrl);

        //Then
        Mockito.verify(podcastRepository, Mockito.times(1)).save(any(Podcast.class));
        Mockito.verify(podcastRepository, Mockito.times(1)).existsByTitle(any(String.class));
        Mockito.verify(parsingService, Mockito.times(1)).parseNewPodcast(any(String.class));
    }

    public Podcast stubPodcast(){
        Podcast podcast = new Podcast();
        podcast.setTitle("testTitle");
        return podcast;
    }

    @Test
    public void addPodcastExistsTest() throws MalformedURLException {
        //Given
        String givenUrl = "http://www.test.com";
        Mockito.when(podcastRepository.existsByTitle(any(String.class))).thenReturn(true);
        Mockito.when(podcastRepository.getByTitle(any(String.class))).thenReturn(stubPodcast());
        Mockito.when(parsingService.parseNewPodcast(any(String.class))).thenReturn(stubPodcast());

        //When
        Podcast podcast = podcastService.addPodcast(givenUrl);

        //Then
        Mockito.verify(podcastRepository, Mockito.times(0)).save(any(Podcast.class));
        Mockito.verify(podcastRepository, Mockito.times(1)).existsByTitle(any(String.class));
        Mockito.verify(podcastRepository, Mockito.times(1)).getByTitle(any(String.class));
    }

    @Test
    public void addPodcastURLFailTest() throws MalformedURLException {
        //Given
        String givenUrl = "http://www.test.com";
        Mockito.when(parsingService.parseNewPodcast(any(String.class))).thenReturn(null);

        //When
        try{
            podcastService.addPodcast(givenUrl);
        } catch (Exception ignored){
        }

        //Then
        Mockito.verify(podcastRepository, Mockito.times(0)).save(any(Podcast.class));
        Mockito.verify(podcastRepository, Mockito.times(0)).existsByTitle(any(String.class));
        Mockito.verify(parsingService, Mockito.times(1)).parseNewPodcast(any(String.class));
    }

    @Test(expected=MalformedURLException.class)
    public void addPodcastURLFailExceptionTest() throws MalformedURLException {
        //Given
        String givenUrl = "http://www.test.com";
        Mockito.when(parsingService.parseNewPodcast(any(String.class))).thenReturn(null);

        //When
        podcastService.addPodcast(givenUrl);

        //Then
        //Exception caught by test
    }

    @Test
    public void getAllPodcastsTest() {
        //Given
        int expected = 2;
        Mockito.when(podcastRepository.findAll()).thenReturn(stubPodcastList());

        //When
        List<Podcast> actualList = podcastService.getAllPodcasts();
        int actual = actualList.size();

        //Then
        assertEquals(expected, actual);
    }

    public List<Podcast> stubPodcastList(){
        Podcast podcast0 = new Podcast();
        podcast0.setTitle("testTitle1");
        Podcast podcast1 = new Podcast();
        podcast1.setTitle("testTitle2");
        List<Podcast> podList = new ArrayList<>();
        podList.add(podcast0);
        podList.add(podcast1);
        return podList;
    }

    @Test
    public void getAllPodcastsCallTest() {
        //Given
        Mockito.when(podcastRepository.findAll()).thenReturn(stubPodcastList());

        //When
        List<Podcast> actualList = podcastService.getAllPodcasts();

        //Then
        Mockito.verify(podcastRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void getAllPodcastsNullTest() {
        //Given
        Mockito.when(podcastRepository.findAll()).thenReturn(null);

        //When
        List<Podcast> actualList = podcastService.getAllPodcasts();

        //Then
        assertNull(actualList);
    }

    @Test
    public void getAllEpisodesTest() throws MalformedURLException {
        //Given
        Podcast podcast = new Podcast();
        Mockito.when(parsingService.getEpisodes(any(Podcast.class))).thenReturn(stubPodcastEpisodeList());
        Mockito.when(podcastRepository.save(any(Podcast.class))).thenReturn(podcast);

        //When
        List<PodcastEpisode> episodes = podcastService.getAllEpisodes(podcast);

        //Then
        Mockito.verify(podcastRepository, Mockito.times(1)).save(any(Podcast.class));
        Mockito.verify(parsingService, Mockito.times(1)).getEpisodes(any(Podcast.class));
    }

    public List<PodcastEpisode> stubPodcastEpisodeList(){
        PodcastEpisode podcast0 = new PodcastEpisode();
        podcast0.setTitle("testTitle1");
        PodcastEpisode podcast1 = new PodcastEpisode();
        podcast1.setTitle("testTitle2");
        List<PodcastEpisode> podList = new ArrayList<>();
        podList.add(podcast0);
        podList.add(podcast1);
        return podList;
    }

    @Test
    public void getAllEpisodesUpdateTest() throws MalformedURLException {
        //Given
        int expected = 2;
        Podcast podcast = new Podcast();
        Mockito.when(parsingService.getEpisodes(any(Podcast.class))).thenReturn(stubPodcastEpisodeList());
        Mockito.when(podcastRepository.save(any(Podcast.class))).thenReturn(podcast);

        //When
        podcastService.getAllEpisodes(podcast);
        List<PodcastEpisode> episodes = podcast.getPodcastEpisodes();
        int actual = episodes.size();

        //Then
        assertEquals(expected, actual);
    }

    @Test(expected=MalformedURLException.class)
    public void getAllEpisodesFailExceptionTest() throws MalformedURLException {
        //Given
        Podcast podcast = new Podcast();
        Mockito.when(parsingService.getEpisodes(any(Podcast.class))).thenReturn(null);

        //When
        podcastService.getAllEpisodes(podcast);

        //Then
        //Exception caught by test
    }

    @Test
    public void getAllEpisodesFailTest() throws MalformedURLException {
        //Given
        Podcast podcast = new Podcast();
        Mockito.when(parsingService.getEpisodes(any(Podcast.class))).thenReturn(null);

        //When
        try{
            podcastService.getAllEpisodes(podcast);
        } catch (Exception ignored){
        }

        //Then
        Mockito.verify(podcastRepository, Mockito.times(0)).save(any(Podcast.class));
        Mockito.verify(parsingService, Mockito.times(1)).getEpisodes(any(Podcast.class));
    }
}