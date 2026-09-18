package tr.com.huseyinaydin;

class Request {
    private String username;
    private String role;

    public Request(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() { return username; }
    public String getRole() { return role; }
}