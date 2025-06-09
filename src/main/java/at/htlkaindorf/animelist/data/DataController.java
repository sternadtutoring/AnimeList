package at.htlkaindorf.animelist.data;

import at.htlkaindorf.animelist.io.FileAccess;
import at.htlkaindorf.animelist.pojos.Anime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.io.IOException;
import java.util.List;

@Getter
public class DataController {
    private ObservableList<Anime> list;

    public DataController() {
        this.list = FXCollections.observableArrayList();
    }

    public void loadAnime() throws IOException {
        List<Anime> anime = FileAccess.loadAnime();
        this.list.addAll(anime);
    }

    public void saveAnime() throws IOException {
       FileAccess.saveAnime(this.list);
    }

}
