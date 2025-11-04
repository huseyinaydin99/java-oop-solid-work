package tr.com.huseyinaydin.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data // ====> @Setter  +  @Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Document(collection = "tutorials")
public class Tutorial {

    @Id
    private String id;

    private String title;
    private String description;
    private boolean published;

    public Tutorial(String title, String description, boolean published) {
        this.title = title;
        this.description = description;
        this.published = published;
    }
}