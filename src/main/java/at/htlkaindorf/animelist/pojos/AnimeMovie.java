package at.htlkaindorf.animelist.pojos;

import lombok.*;

import java.io.FileNotFoundException;


@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AnimeMovie extends Anime {
    private int length;

    public AnimeMovie(String csv) throws FileNotFoundException {
        super(csv);

        String[] tokens = csv.split(";");
        this.length = Integer.parseInt(tokens[6]);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String toCSV() {
        return String.format(
                "MOVIE;%s;%s;%s;%s;0;%d",
                this.title, this.studio, this.image.getUrl(), this.state.name(), this.length
        );
    }
}
