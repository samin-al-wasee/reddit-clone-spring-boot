package saw.rafeed.redditclonespringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import saw.rafeed.redditclonespringboot.model.Post;
import java.util.List;
import saw.rafeed.redditclonespringboot.model.Subreddit;
import saw.rafeed.redditclonespringboot.model.User;



public interface PostRepository extends JpaRepository<Post, Long>{
    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findAllByUser(User user);
}
