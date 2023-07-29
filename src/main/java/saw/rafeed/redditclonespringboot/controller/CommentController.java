package saw.rafeed.redditclonespringboot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import saw.rafeed.redditclonespringboot.dto.CommentDto;
import saw.rafeed.redditclonespringboot.service.CommentService;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentDto commentDto) {
        commentService.createComment(commentDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "/by-post/{postId}")
    public ResponseEntity<List<CommentDto>> getAllCommentsForPost(@PathVariable Long postId) {
        return new ResponseEntity<>(commentService.getCommentByPost(postId), HttpStatus.OK);
    }

    @GetMapping(path = "/by-user/{userName}")
    public ResponseEntity<List<CommentDto>> getAllCommentsByUser(@PathVariable String userName) {
        return new ResponseEntity<>(commentService.getCommentsByUser(userName), HttpStatus.OK);
    }
}
