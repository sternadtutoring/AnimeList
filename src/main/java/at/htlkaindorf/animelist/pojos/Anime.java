package at.htlkaindorf.animelist.pojos;

import at.htlkaindorf.animelist.enums.AnimeState;
import at.htlkaindorf.animelist.interfaces.CsvSerializable;
import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.FileNotFoundException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Anime implements CsvSerializable {
    protected String title;
    protected String studio;
    protected Image image;
    protected AnimeState state;

    public Anime(String csv) throws FileNotFoundException {
        String[] tokens = csv.split(";");

        this.title = tokens[1];
        this.studio = tokens[2];
        this.image = new Image(tokens[3], 210.0, 300.0, false, false, false);
        this.state = AnimeState.valueOf(tokens[4]);
    }

    @Override
    public String toString() {
        return this.title;
    }

    @Override
    public String toCSV() {
        // type;title;studio;image;state;episodes;length
        //SHOW;Dandadan;Science SARU Inc.;https://m.media-amazon.com/images/I/81cUBwUTL+L.jpg;FINISHED;12;0

        return String.format(
                "TYPE;%s;%s;%s;%s;0;0",
                this.title, this.studio, this.image.getUrl(), this.state.name()
        );
    }
}
