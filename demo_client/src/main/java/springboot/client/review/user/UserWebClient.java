package springboot.client.review.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

public class UserWebClient implements UserClient {

    private static final String URI_EXT = "/api/v1/users";

    private WebClient webClient;

    public UserWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public List<User> getUsers() {
        return webClient.get().uri(builder -> builder.path(URI_EXT).build()).retrieve().bodyToFlux(User.class)
                .collectList().block();
    }

    @Override
    public User getUser(Long id) {
        return webClient.get().uri(builder -> builder.path(URI_EXT + "/{id}").build(id)).retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals, ClientResponse::createException).bodyToMono(User.class)
                .block();
    }

    @Override
    public User createUser(User user) {
        return webClient.post().uri(builder -> builder.path(URI_EXT).build()).bodyValue(user).retrieve()
                .bodyToMono(User.class).block();
    }

    @Override
    public User updateUser(User user, Long id) {
        return webClient.put().uri(builder -> builder.path(URI_EXT + "/{id}").build(id)).bodyValue(user)
                .retrieve().bodyToMono(User.class).block();
    }

    @Override
    public void deleteUser(Long id) {
        webClient.delete().uri(builder -> builder.path(URI_EXT + "/{id}").build(id)).retrieve()
                .toBodilessEntity().block();
    }
}
