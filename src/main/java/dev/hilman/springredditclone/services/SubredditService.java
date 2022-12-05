package dev.hilman.springredditclone.services;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.hilman.springredditclone.dto.SubredditDto;
import dev.hilman.springredditclone.exceptions.SpringRedditException;
import dev.hilman.springredditclone.mapper.SubredditMapper;
import dev.hilman.springredditclone.model.Subreddit;
import dev.hilman.springredditclone.repository.SubredditRepository;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class SubredditService {
    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto){
        Subreddit save = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));
        subredditDto.setId(save.getId());
        return subredditDto;
    }


    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
       return subredditRepository.findAll()
            .stream()
            .map(subredditMapper::mapSubredditToDto)
            .collect(Collectors.toList());
        
    }

    public SubredditDto getSubreddit(Long id){
        Subreddit subreddit = subredditRepository.findById(id).orElseThrow(() -> new SpringRedditException("No Subreddit Found with id " + id));
        return subredditMapper.mapSubredditToDto(subreddit);
      }
}
