package org.twin.application.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMangaRequest {
    private String title;
    private String description;
    private Integer chapter;
}
