package ml.georgedi23.ion_casts.models;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PodcastTest {

    Podcast defaultPodcast;
    Podcast modifiedPodcast;

    @Before
    public void setUp() throws Exception {
        modifiedPodcast = new Podcast();
        defaultPodcast = new Podcast();
        defaultPodcast.setPodcast_id(42L);
        defaultPodcast.setTitle("DefaultTitle");
        defaultPodcast.setLink("http://www.test.com");
        defaultPodcast.setDescription("A Test Description");
        defaultPodcast.setImage("http://www.linkToImage.com");
        PodcastEpisode episode = new PodcastEpisode();
        episode.setTitle("TEST");
        ArrayList<PodcastEpisode> episodeList = new ArrayList<>();
        episodeList.add(episode);
        defaultPodcast.setPodcastEpisodes(episodeList);
        DownloadedEpisode downloadedEpisode = new DownloadedEpisode();
        downloadedEpisode.setTitle("DOWNLOAD TEST");
        ArrayList<DownloadedEpisode> downloadedList = new ArrayList<>();
        downloadedList.add(downloadedEpisode);
        defaultPodcast.setDownloadedEpisodes(downloadedList);
    }

    @Test
    public void getPodcast_id() {
        //Given - default Podcast
        Long expected = 42l;

        //When
        Long actual = defaultPodcast.getPodcast_id();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void setPodcast_id() {
        //Given
        Long expected = 72l;

        //When
        modifiedPodcast.setPodcast_id(expected);
        Long actual = modifiedPodcast.getPodcast_id();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void getTitle() {
        //Given - default Podcast
        String expected = "DefaultTitle";

        //When
        String actual = defaultPodcast.getTitle();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void setTitle() {
        //Given
        String expected = "aTitle";

        //When
        modifiedPodcast.setTitle(expected);
        String actual = modifiedPodcast.getTitle();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void getLink() {
        //Given - default Podcast
        String expected = "http://www.test.com";

        //When
        String actual = defaultPodcast.getLink();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void setLink() {
        //Given
        String expected = "http://www.google.com";

        //When
        modifiedPodcast.setLink(expected);
        String actual = modifiedPodcast.getLink();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void getDescription() {
        //Given - default Podcast
        String expected = "A Test Description";

        //When
        String actual = defaultPodcast.getDescription();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void setDescription() {
        //Given
        String expected = "Description here";

        //When
        modifiedPodcast.setDescription(expected);
        String actual = modifiedPodcast.getDescription();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void getImage() {
        //Given - default Podcast
        String expected = "http://www.linkToImage.com";

        //When
        String actual = defaultPodcast.getImage();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void setImage() {
        //Given
        String expected = "http://www.googleimages.com/image.jpg";

        //When
        modifiedPodcast.setImage(expected);
        String actual = modifiedPodcast.getImage();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void getPodcastEpisodes() {
        //Given - default Podcast
        String expected = "TEST";

        //When
        ArrayList<PodcastEpisode> actualPodcastEpisodes = defaultPodcast.getPodcastEpisodes();
        String actual = actualPodcastEpisodes.get(0).getTitle();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void setPodcastEpisodes() {
        //Given
        PodcastEpisode expected = new PodcastEpisode();
        ArrayList<PodcastEpisode> givenPodcastEpisodes = new ArrayList<>();
        givenPodcastEpisodes.add(expected);

        //When
        modifiedPodcast.setPodcastEpisodes(givenPodcastEpisodes);
        PodcastEpisode actual = modifiedPodcast.getPodcastEpisodes().get(0);

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void getDownloadedEpisodes() {
        //Given - default Podcast
        String expected = "DOWNLOAD TEST";

        //When
        ArrayList<DownloadedEpisode> actualDownloadedPodcastEpisodes = defaultPodcast.getDownloadedEpisodes();
        String actual = actualDownloadedPodcastEpisodes.get(0).getTitle();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void setDownloadedEpisodes() {
        //Given
        DownloadedEpisode expected = new DownloadedEpisode();
        ArrayList<DownloadedEpisode> givenDownloadedEpisodes = new ArrayList<>();
        givenDownloadedEpisodes.add(expected);

        //When
        modifiedPodcast.setDownloadedEpisodes(givenDownloadedEpisodes);
        DownloadedEpisode actual = modifiedPodcast.getDownloadedEpisodes().get(0);

        //Then
        assertEquals(expected, actual);
    }
}