package ee.mihkel.rentalstore.dto;

import ee.mihkel.rentalstore.entity.FilmType;

public record FilmSaveDto(
        String title,
        FilmType type
) {
}
