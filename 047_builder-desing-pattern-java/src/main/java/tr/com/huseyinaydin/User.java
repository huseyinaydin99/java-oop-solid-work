package tr.com.huseyinaydin;

public class User {

    private final String username;
    private final String email;
    private final String phone;
    private final String address;
    private final boolean active;

    private User(Builder builder) {
        this.username = builder.username;
        this.email = builder.email;
        this.phone = builder.phone;
        this.address = builder.address;
        this.active = builder.active;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(String username, String email) {
        return new Builder(username, email);
    }

    public static class Builder {

        private String username;
        private String email;
        private String phone;
        private String address;
        private boolean active = true;

        public Builder(String username, String email) {
            this.username = username;
            this.email = email;
        }

        public Builder() {

        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder active(boolean active) {
            this.active = active;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}