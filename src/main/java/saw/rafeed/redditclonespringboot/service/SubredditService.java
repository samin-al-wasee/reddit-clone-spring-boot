package saw.rafeed.redditclonespringboot.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import saw.rafeed.redditclonespringboot.dto.SubredditDto;
import saw.rafeed.redditclonespringboot.exception.SubredditNotFoundException;
import saw.rafeed.redditclonespringboot.model.Subreddit;
import saw.rafeed.redditclonespringboot.repository.SubredditRepository;

import static java.util.stream.Collectors.toList;

import java.time.Instant;

@AllArgsConstructor
@Service
public class SubredditService {
    private final SubredditRepository subredditRepository;
    private final AuthService authService;

    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public SubredditDto getSubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new SubredditNotFoundException("Subreddit not found with id -" + id));
        return mapToDto(subreddit);
    }

    @Transactional
    public SubredditDto save(@Valid SubredditDto subredditDto) {
        Subreddit subreddit = subredditRepository.save(mapToSubreddit(subredditDto));
        subredditDto.setId(subreddit.getId());
        return subredditDto;
    }

    private Subreddit mapToSubreddit(@Valid SubredditDto subredditDto) {
        return Subreddit.builder().name("/r/" + subredditDto.getName())
                .description(subredditDto.getDescription())
                .user(authService.getCurrentUser())
                .createdDate(Instant.now()).build();
    }

    private SubredditDto mapToDto(Subreddit subreddit) {
        return SubredditDto.builder().name(subreddit.getName())
                .id(subreddit.getId())
                .postCount(subreddit.getPosts().size())
                .build();
    }
    
}
