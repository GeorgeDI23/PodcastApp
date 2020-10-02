package ml.georgedi23.ion_casts.services;

import ml.georgedi23.ion_casts.models.Podcast;
import ml.georgedi23.ion_casts.models.PodcastEpisode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ParsingServiceTest {

    ParsingService parsingService;
    @Mock
    private URL testUrl = Mockito.mock(URL.class);

    @Before
    public void setUp(){
        parsingService = new ParsingService();
    }

    @Test
    public void convertToUrlTest() {
        //Given
        String givenUrl = "https://www.google.com";

        //When
        URL actual =  parsingService.convertToURL(givenUrl);

        //Then
        assertEquals(givenUrl, actual.toString());
    }

    @Test
    public void convertToUrlExceptionTest() {
        //Given
        String givenUrl = "www.google.com";

        //When
        URL actual =  parsingService.convertToURL(givenUrl);

        //Then
        assertNull(actual);
    }

    @Test
    public void createXMLReaderTest() throws IOException, XMLStreamException {
        // Given
        String expected = "<title>";
        Mockito.when(testUrl.openStream()).thenReturn(stubXML());

        // When
        XMLEventReader reader = parsingService.createXMLReader(testUrl);
        reader.nextEvent();
        reader.nextEvent();
        reader.nextEvent();
        reader.nextEvent();
        String actual = reader.nextTag().toString();

        // Then
        assertEquals(expected, actual);
    }

    public InputStream stubXML(){
        String initialString = "<rss xmlns:media=\"http://search.yahoo.com/mrss/\" xmlns:content=\"http://purl.org/rss/1.0/modules/content/\" version=\"2.0\">\n" +
                "<channel>\n" +
                "<title>Podcast Title</title>\n" +
                "<description>" +
                "Podcast description was here." +
                "</description>\n" +
                "<image>\n" +
                "<url>" +
                "https://images.test.fm/test.jpg" +
                "</url>\n" +
                "</image>\n" +
                "<item>\n" +
                "<title>75: Title is Here</title>\n" +
                "<link>https://test.com/episode/75</link>\n" +
                "<description>" +
                "Episode description was here." +
                "</description>\n" +
                "<pubDate>Tue, 29 Sep 2020 07:00:00 -0000</pubDate>\n" +
                "<guid isPermaLink=\"false\">\n" +
                "</guid>\n" +
                "<enclosure url=\"https://www.podtrac.com/something.mp3\" length=\"0\" type=\"audio/mpeg\"/>\n" +
                "</item>"+
                "</channel></rss>";
        return new ByteArrayInputStream(initialString.getBytes());
    }

    @Test
    public void getCharacterDataTest() throws IOException, XMLStreamException {
        // Given
        String expected = "Podcast Title";
        Mockito.when(testUrl.openStream()).thenReturn(stubXML());

        // When
        XMLEventReader reader = parsingService.createXMLReader(testUrl);
        for(int i = 0; i < 6; i++) reader.nextEvent();
        String actual = parsingService.getCharacterData(reader);

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void getPodcastDetailsTest() throws IOException, XMLStreamException {
        // Given
        String expected = "Podcast Title\nPodcast description was here.\nhttps://images.test.fm/test.jpg";
        Mockito.when(testUrl.openStream()).thenReturn(stubXML());

        // When
        XMLEventReader reader = parsingService.createXMLReader(testUrl);
        Podcast actualPodcast = parsingService.getPodcastDetails(reader);
        String actual = actualPodcast.getTitle();
        actual = actual.concat("\n" + actualPodcast.getDescription() + "\n");
        actual = actual.concat(actualPodcast.getImage());

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void updateEpisodeWithTagTest() throws IOException, XMLStreamException {
        //Given
        String expected = "Podcast Title";
        PodcastEpisode episode = new PodcastEpisode();
        Mockito.when(testUrl.openStream()).thenReturn(stubXML());

        //When
        XMLEventReader reader = parsingService.createXMLReader(testUrl);
        for(int i = 0; i < 5; i++) reader.nextEvent();
        parsingService.updateEpisodeWithTag(episode, reader);
        String actual = episode.getTitle();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void parseSingleEpisodeTest() throws IOException, XMLStreamException {
        //Given
        String expected = "Episode description was here.;75: Title is Here;https://www.podtrac.com/something.mp3;\n" +
                ";Tue, 29 Sep 2020 07:00:00 -0000";
        Mockito.when(testUrl.openStream()).thenReturn(stubXML());

        //When
        XMLEventReader reader = parsingService.createXMLReader(testUrl);
        PodcastEpisode episode = parsingService.parseSingleEpisode(reader);
        String actual = episode.getDescription() + ";" + episode.getTitle() + ";" + episode.getLink() +
                ";" + episode.getGuid() + ";" + episode.getPub_date();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void readEpisodeFeedTest() throws IOException, XMLStreamException {
        //Given
        String expected = "Episode description was here.;75: Title is Here;https://www.podtrac.com/something.mp3;\n" +
                ";Tue, 29 Sep 2020 07:00:00 -0000";
        Mockito.when(testUrl.openStream()).thenReturn(stubXML());

        //When
        XMLEventReader reader = parsingService.createXMLReader(testUrl);
        List<PodcastEpisode> episodes = parsingService.readEpisodeFeed(reader);
        PodcastEpisode episode = episodes.get(0);
        String actual = episode.getDescription() + ";" + episode.getTitle() + ";" + episode.getLink() +
                ";" + episode.getGuid() + ";" + episode.getPub_date();

        //Then
        assertEquals(actual, expected);
    }
    //TODO - add tests for edge cases as well as code mitigating
}