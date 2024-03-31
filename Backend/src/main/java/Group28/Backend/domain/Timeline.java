package Group28.Backend.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Timeline
{
    @Id
    private String id;

    private Client client;
}
