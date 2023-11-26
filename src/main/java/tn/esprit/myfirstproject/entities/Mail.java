package tn.esprit.myfirstproject.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Mail {
    String from;
    String to;
    String subject;
    String content;
}
