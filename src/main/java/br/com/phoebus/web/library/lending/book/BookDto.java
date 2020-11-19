package br.com.phoebus.web.library.lending.book;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BookDto {

    private String uuid;

    private String title;

    private String summary;

    private String lendingID;
}
