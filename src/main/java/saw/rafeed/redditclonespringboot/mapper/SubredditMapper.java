package saw.rafeed.redditclonespringboot.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import saw.rafeed.redditclonespringboot.dto.SubredditDto;
import saw.rafeed.redditclonespringboot.model.Post;
import saw.rafeed.redditclonespringboot.model.Subreddit;

@Mapper(componentModel = "spring")
public interface SubredditMapper {
    @Mapping(target = "postCount", expression = "java(mapPosts(subreddit.getPosts()))")
    SubredditDto mapSubredditToDto(Subreddit subreddit);

    default Integer mapPosts(List<Post> postCount) {
        return postCount.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Subreddit mapDtoToSubreddit(SubredditDto subreddit);
}
