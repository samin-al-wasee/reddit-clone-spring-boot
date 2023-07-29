package saw.rafeed.redditclonespringboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import saw.rafeed.redditclonespringboot.dto.CommentDto;
import saw.rafeed.redditclonespringboot.model.Comment;
import saw.rafeed.redditclonespringboot.model.Post;
import saw.rafeed.redditclonespringboot.model.User;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", source = "commentsDto.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "post", source = "post")
    Comment map(CommentDto commentsDto, Post post, User user);

    @Mapping(target = "postId", expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "userName", expression = "java(comment.getUser().getUsername())")
    CommentDto mapToDto(Comment comment);
}
