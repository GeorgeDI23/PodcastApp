package ml.georgedi23.ion_casts.models;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DownloadedEpisodeTest {

    DownloadedEpisode defaultEpisode;
    DownloadedEpisode modifiedEpisode;

    @Before
    public void setUp() throws Exception {
        modifiedEpisode = new DownloadedEpisode();
        defaultEpisode = new DownloadedEpisode();
        defaultEpisode.setEpisode_id(42L);
        Podcast givenPodcast = new Podcast();
        givenPodcast.setTitle("Given Podcast Title");
        defaultEpisode.setPodcast(givenPodcast);
        defaultEpisode.setTitle("DefaultTitle");
        defaultEpisode.setDescription("A Test Description");
        defaultEpisode.setLink("http://www.test.com");
        defaultEpisode.setPub_date("12/31/20");
        defaultEpisode.setGuid("1234ABCD");
        defaultEpisode.setAudio_key("givenAudioKey");
        defaultEpisode.setAudioData("StringOfAudioData");
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

    @Test
    public void getAudio_key() {
        //Given - default Episode
        String expected = "givenAudioKey";

        //When
        String actual = defaultEpisode.getAudio_key();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void setAudio_key() {
        //Given
        String expected = "expectedAudioKey";

        //When
        modifiedEpisode.setAudio_key(expected);
        String actual = modifiedEpisode.getAudio_key();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void getAudioData() {
        //Given - default Episode
        String expected = "StringOfAudioData";

        //When
        String actual = defaultEpisode.getAudioData();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void setAudioData() {
        //Given
        String expected = "expectedStringOfAudioData";

        //When
        modifiedEpisode.setAudioData(expected);
        String actual = modifiedEpisode.getAudioData();

        //Then
        assertEquals(expected, actual);
    }
}