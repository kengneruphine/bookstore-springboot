package com.ruphine.bookstore.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum BookGenre {
    FICTION,
    BIOGRAPHY,
    FANTASY,
    SCIENCE,
    ROMANCE,
    NON_FICTION,
    MYSTERY,
    UNKNOWN;


    // This method is called when deserializing JSON to Enum
    @JsonCreator
    public static BookGenre fromValue(String value) {
        if (value == null) {
            return UNKNOWN;
        }
        for (BookGenre genre : values()) {
            if (genre.name().equalsIgnoreCase(value)) {  // Case-insensitive check
                return genre;
            }
        }
        return UNKNOWN;
    }

    // This method is called when serializing Enum to JSON
    @JsonValue
    public String toValue() {
        return this.name();
    }

}
