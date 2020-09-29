package ml.georgedi23.ion_casts.models;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name="podcasts")
public class Podcast {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long podcast_id;

    @Column(name = "title")
    private String title;

    @Column(name = "link")
    private String link;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "podcast_id", cascade = {CascadeType.ALL})
    private ArrayList<PodcastEpisode> podcastEpisodes;

    @OneToMany(mappedBy = "podcast_id", cascade = {CascadeType.ALL})
    private ArrayList<DownloadedEpisode> downloadedEpisodes;

    public Long getPodcast_id() {
        return podcast_id;
    }

    public void setPodcast_id(Long podcast_id) {
        this.podcast_id = podcast_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<PodcastEpisode> getPodcastEpisodes() {
        return podcastEpisodes;
    }

    public void setPodcastEpisodes(ArrayList<PodcastEpisode> podcastEpisodes) {
        this.podcastEpisodes = podcastEpisodes;
    }

    public ArrayList<DownloadedEpisode> getDownloadedEpisodes() {
        return downloadedEpisodes;
    }

    public void setDownloadedEpisodes(ArrayList<DownloadedEpisode> downloadedEpisodes) {
        this.downloadedEpisodes = downloadedEpisodes;
    }
}
