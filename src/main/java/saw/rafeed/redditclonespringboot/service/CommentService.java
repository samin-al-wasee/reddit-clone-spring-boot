package saw.rafeed.redditclonespringboot.service;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import saw.rafeed.redditclonespringboot.dto.CommentDto;
import saw.rafeed.redditclonespringboot.exception.PostNotFoundException;
import saw.rafeed.redditclonespringboot.mapper.CommentMapper;
import saw.rafeed.redditclonespringboot.model.Comment;
import saw.rafeed.redditclonespringboot.model.NotificationEmail;
import saw.rafeed.redditclonespringboot.model.Post;
import saw.rafeed.redditclonespringboot.model.User;
import saw.rafeed.redditclonespringboot.repository.CommentRepository;
import saw.rafeed.redditclonespringboot.repository.PostRepository;
import saw.rafeed.redditclonespringboot.repository.UserRepository;
import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@Slf4j
@Transactional
@Service
public class CommentService {
    private static final String POST_URL = "";

    private final CommentMapper commentMapper;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;

    public void createComment(CommentDto commentDto) {
        Post post = postRepository.findById(commentDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentDto.getPostId().toString()));
        Comment comment = commentMapper.map(commentDto, post, authService.getCurrentUser());
        commentRepository.save(comment);

        String message = mailContentBuilder
                .build(post.getUser().getUsername() + " posted a comment on your post." + POST_URL);
        sendCommentNotification(message, post.getUser());
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(
                new NotificationEmail(user.getUsername() + " Commented on your post", user.getEmail(), message));
    }

    public List<CommentDto> getCommentByPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId.toString()));
        return commentRepository.findAllByPost(post)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }

    public List<CommentDto> getCommentsByUser(String userName) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }
}
