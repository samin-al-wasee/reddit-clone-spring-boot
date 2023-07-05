package saw.rafeed.redditclonespringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import saw.rafeed.redditclonespringboot.model.Comment;
import java.util.List;
import saw.rafeed.redditclonespringboot.model.Post;
import saw.rafeed.redditclonespringboot.model.User;



public interface CommentRepository extends JpaRepository<Comment, Long>{
    List<Comment> findAllByPost(Post post);

    List<Comment> findAllByUser(User user);
}
