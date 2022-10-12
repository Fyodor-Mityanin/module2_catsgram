package ru.yandex.practicum.catsgram.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.catsgram.model.FriendsParams;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PostFeedController {

    private final PostService postService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public PostFeedController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(value = "/feed/friends")
    public List<Post> lastPosts(@RequestBody String request) throws JsonProcessingException {
        String cleanRequest = objectMapper.readValue(request, String.class);
        FriendsParams params = objectMapper.readValue(cleanRequest, FriendsParams.class);
        List<Post> result = new ArrayList<>();
        for (String friend : params.getFriends()) {
            result.addAll(postService.findAllByUserEmail(friend, params.getSize(), params.getSort()));
        }
        return result;
    }
}
