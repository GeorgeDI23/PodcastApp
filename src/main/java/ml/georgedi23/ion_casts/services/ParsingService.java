package ml.georgedi23.ion_casts.services;

import ml.georgedi23.ion_casts.models.Podcast;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParsingService {

    private final Logger logger;

    public ParsingService() {
        this.logger = Logger.getLogger(ParsingService.class.getName());
    }

    // Gets data for a new podcast
    public Podcast parseNewPodcast(String podcastUrl){
        Podcast podcast = new Podcast();
        if(convertToURL(podcastUrl) != null){
            try {
                XMLEventReader eventReader = createXMLReader(convertToURL(podcastUrl));
                podcast = getPodcastDetails(eventReader);
                podcast.setLink(podcastUrl);
            } catch(XMLStreamException | IOException e){
                logger.log(Level.WARNING,"Error parsing XML; Exception: " + e.toString());
                return null;
            }
        }
        return podcast;
    }

    public URL convertToURL(String podcastUrl){
        try {
            return new URL(podcastUrl);
        } catch (MalformedURLException e) {
            logger.log(Level.WARNING,"Unable to parse URL; Exception: " + e.toString());
            return null;
        }
    }

    public XMLEventReader createXMLReader(URL podcastUrl) throws XMLStreamException, IOException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        InputStream inputStream = podcastUrl.openStream();
        return inputFactory.createXMLEventReader(inputStream);
    }

    public Podcast getPodcastDetails(XMLEventReader eventReader) throws XMLStreamException {
        Podcast podcast = new Podcast();
        xmlLoop: while(eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();
            if(event.isStartElement()){
                String localPart = event.asStartElement().getName().getLocalPart();
                switch (localPart) {
                    case "title":
                        podcast.setTitle(getCharacterData(eventReader));
                        break;
                    case "url":
                        podcast.setImage(getCharacterData(eventReader));
                        break;
                    case "description":
                        podcast.setDescription(getCharacterData(eventReader));
                        break;
                    case "item":
                        break xmlLoop;
                }
            }
        }
        return podcast;
    }

    public String getCharacterData(XMLEventReader eventReader) throws XMLStreamException {
        String result = "";
        XMLEvent event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }
}

