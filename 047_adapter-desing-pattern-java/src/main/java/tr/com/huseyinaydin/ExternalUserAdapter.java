package tr.com.huseyinaydin;

import tr.com.huseyinaydin.external.ExternalUserClient;
import tr.com.huseyinaydin.external.ExternalUserResponse;

public class ExternalUserAdapter implements UserService {

    private final ExternalUserClient client;

    public ExternalUserAdapter(ExternalUserClient client) {
        this.client = client;
    }

    @Override
    public User getUser(Long id) {
        ExternalUserResponse response = client.fetchUser(id);

        return new User(
                response.userId(),
                response.fullName(),
                response.emailAddress()
        );
    }
}