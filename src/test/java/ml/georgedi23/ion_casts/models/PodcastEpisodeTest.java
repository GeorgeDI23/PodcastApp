package ml.georgedi23.ion_casts.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PodcastEpisodeTest {

    PodcastEpisode defaultEpisode;
    PodcastEpisode modifiedEpisode;

    @Before
    public void setUp() throws Exception {
        modifiedEpisode = new PodcastEpisode();
        defaultEpisode = new PodcastEpisode();
        defaultEpisode.setEpisode_id(42L);
        Podcast givenPodcast = new Podcast();
        givenPodcast.setTitle("Given Podcast Title");
        defaultEpisode.setPodcast(givenPodcast);
        defaultEpisode.setTitle("DefaultTitle");
        defaultEpisode.setDescription("A Test Description");
        defaultEpisode.setLink("http://www.test.com");
        defaultEpisode.setPub_date("12/31/20");
        defaultEpisode.setGuid("1234ABCD");
    }

    @Test
    public void getEpisode_id() {
        //Given - default Episode
        Long expected = 42l;

        //When
        Long actual = defaultEpisode.getEpisode_id();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void setEpisode_id() {
        //Given
        Long expected = 56L;

        //When
        modifiedEpisode.setEpisode_id(expected);
        Long actual = modifiedEpisode.getEpisode_id();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void getPodcast() {
        //Given - default Episode
        String expected = "Given Podcast Title";

        //When
        String actual = defaultEpisode.getPodcast().getTitle();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void setPodcast() {
        //Given
        Podcast expected = new Podcast();

        //When
        modifiedEpisode.setPodcast(expected);
        Podcast actual = modifiedEpisode.getPodcast();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void getTitle() {
        //Given - default Episode
        String expected = "DefaultTitle";

        //When
        String actual = defaultEpisode.getTitle();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void setTitle() {
        //Given
        String expected = "expected Title";

        //When
        modifiedEpisode.setTitle(expected);
        String actual = modifiedEpisode.getTitle();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void getDescription() {
        //Given - default Episode
        String expected = "A Test Description";

        //When
        String actual = defaultEpisode.getDescription();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void setDescription() {
        //Given
        String expected = "expected Description";

        //When
        modifiedEpisode.setDescription(expected);
        String actual = modifiedEpisode.getDescription();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void getLink() {
        //Given - default Episode
        String expected = "http://www.test.com";

        //When
        String actual = defaultEpisode.getLink();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void setLink() {
        //Given
        String expected = "http://www.expectedLink.com";

        //When
        modifiedEpisode.setLink(expected);
        String actual = modifiedEpisode.getLink();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void getPub_date() {
        //Given - default Episode
        String expected = "12/31/20";

        //When
        String actual = defaultEpisode.getPub_date();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void setPub_date() {
        //Given
        String expected = "10/31/20";

        //When
        modifiedEpisode.setPub_date(expected);
        String actual = modifiedEpisode.getPub_date();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void getGuid() {
        //Given - default Episode
        String expected = "1234ABCD";

        //When
        String actual = defaultEpisode.getGuid();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void setGuid() {
        //Given
        String expected = "ABC123";

        //When
        modifiedEpisode.setGuid(expected);
        String actual = modifiedEpisode.getGuid();

        //Then
        assertEquals(expected, actual);
    }
}