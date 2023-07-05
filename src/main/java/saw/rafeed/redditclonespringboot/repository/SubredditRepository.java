package saw.rafeed.redditclonespringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import saw.rafeed.redditclonespringboot.model.Subreddit;
// import java.util.List;
// import saw.rafeed.redditclonespringboot.model.User;
import java.util.Optional;


public interface SubredditRepository extends JpaRepository<Subreddit, Long>{
    // List<Subreddit> findAllByUser(User user);

    Optional<Subreddit> findAllByName(String name);
}
