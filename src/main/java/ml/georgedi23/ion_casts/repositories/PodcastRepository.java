package ml.georgedi23.ion_casts.repositories;

import ml.georgedi23.ion_casts.models.Podcast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PodcastRepository extends JpaRepository<Podcast, Long>{

    public Boolean existsByTitle(String title);

    public Podcast getByTitle(String title);

}
