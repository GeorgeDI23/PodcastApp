package ml.georgedi23.ion_casts.models;

import javax.persistence.*;

@Entity
@Table(name="downloaded_episodes")
public class DownloadedEpisode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "episode_id")
    private Long episode_id;

    @ManyToOne()
    @JoinColumn(name="podcast_id", referencedColumnName = "podcast_id")
    private Podcast podcast;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "link")
    private String link;

    @Column(name = "pub_date")
    private String pub_date;

    @Column(name = "guid")
    private String guid;

    // Ties to S3 bucket
    @Column(name = "audio_key")
    private String audio_key;

    @Transient
    private String audioData;

    public Long getEpisode_id() {
        return episode_id;
    }

    public void setEpisode_id(Long episode_id) {
        this.episode_id = episode_id;
    }

    public Podcast getPodcast() {
        return podcast;
    }

    public void setPodcast(Podcast podcast) {
        this.podcast = podcast;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPub_date() {
        return pub_date;
    }

    public void setPub_date(String pub_date) {
        this.pub_date = pub_date;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getAudio_key() {
        return audio_key;
    }

    public void setAudio_key(String audio_key) {
        this.audio_key = audio_key;
    }

    public String getAudioData() {
        return audioData;
    }

    public void setAudioData(String audioData) {
        this.audioData = audioData;
    }
}