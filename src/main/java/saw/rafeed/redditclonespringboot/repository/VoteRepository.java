package saw.rafeed.redditclonespringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import saw.rafeed.redditclonespringboot.model.Vote;
import java.util.Optional;

import saw.rafeed.redditclonespringboot.model.User;
import saw.rafeed.redditclonespringboot.model.Post;


public interface VoteRepository extends JpaRepository<Vote, Long>{
    Optional<Vote> findTopByPostAndUserOrderByVoteId(Post post, User user);
}
