package at.htlkaindorf.animelist.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AnimeState {
    NOT_STARTED(0),
    CURRENTLY_WATCHING(0.5),
    FINISHED(1);

    private final double value;
}
