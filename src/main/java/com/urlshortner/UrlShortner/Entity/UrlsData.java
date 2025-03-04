package com.urlshortner.UrlShortner.Entity;

import com.mongodb.lang.NonNull;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "url_data")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrlsData {
    @Id
    private ObjectId Id;
    @NonNull
    private String url;
    private String hashedUrl;
}
