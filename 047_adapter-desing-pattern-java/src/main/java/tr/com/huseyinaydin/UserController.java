package tr.com.huseyinaydin;

import tr.com.huseyinaydin.external.ExternalUserClient;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public User getUser(Long id) {
        return userService.getUser(id);
    }

    public static void main(String[] args) {
        UserController userController = new UserController(new ExternalUserAdapter(new ExternalUserClient()));
        User user = userController.getUser(1L);
        System.out.println(user);
    }
}