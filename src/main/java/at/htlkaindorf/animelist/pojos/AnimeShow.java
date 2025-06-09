package at.htlkaindorf.animelist.pojos;

import lombok.*;

import java.io.FileNotFoundException;


@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AnimeShow extends Anime {
    private int episodes;

    public AnimeShow(String csv) throws FileNotFoundException {
        super(csv);

        String[] tokens = csv.split(";");
        this.episodes = Integer.parseInt(tokens[5]);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String toCSV() {
        return String.format(
                "SHOW;%s;%s;%s;%s;%d;0",
                this.title, this.studio, this.image.getUrl(), this.state.name(), this.episodes
        );
    }
}
