package at.htlkaindorf.animelist.controller;

import at.htlkaindorf.animelist.data.DataController;
import at.htlkaindorf.animelist.enums.AnimeState;
import at.htlkaindorf.animelist.pojos.Anime;
import at.htlkaindorf.animelist.pojos.AnimeMovie;
import at.htlkaindorf.animelist.pojos.AnimeShow;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class AnimeController {
    @FXML
    private ListView<Anime> lvAnimeList;

    @FXML
    private ImageView ivImage;

    @FXML
    private Label laTitle;

    @FXML
    private Label laStudio;

    @FXML
    private Label laAdditionalInfo;

    @FXML
    private Button btNotStarted;

    @FXML
    private Button btCurrentlyWatching;

    @FXML
    private Button btFinished;

    @FXML
    private ProgressBar prStatus;

    private DataController dataController;

    public void initialize() {
        this.dataController = new DataController();
        this.ivImage.setSmooth(false);
        this.lvAnimeList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        try {
            this.dataController.loadAnime();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Critical Error!");
            alert.setContentText("Could not load data from the file!\n\n" + e.getMessage());
            alert.showAndWait();
        }

        this.lvAnimeList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Anime>() {
            @Override
            public void changed(ObservableValue<? extends Anime> observableValue, Anime oldValue, Anime newValue) {
                ivImage.setImage(newValue.getImage());

                laTitle.setText("Title: " + newValue.getTitle());
                laStudio.setText("Studio: " + newValue.getStudio());
                prStatus.setProgress(newValue.getState().getValue());

                if (newValue instanceof AnimeShow show) {
                    laAdditionalInfo.setText("Episodes: " + show.getEpisodes());
                } else if (newValue instanceof AnimeMovie movie) {
                    laAdditionalInfo.setText(
                            String.format("Movie: %dh %dmin", movie.getLength() / 60, movie.getLength() % 60)
                    );
                }

            }
        });

        this.lvAnimeList.setItems(dataController.getList());

        if (!dataController.getList().isEmpty()) {
            this.lvAnimeList.getSelectionModel().select(0);
        }

        this.btFinished.setOnAction((e) -> setCurrentState(AnimeState.FINISHED));
        this.btCurrentlyWatching.setOnAction((e) -> setCurrentState(AnimeState.CURRENTLY_WATCHING));
        this.btNotStarted.setOnAction((e) -> setCurrentState(AnimeState.NOT_STARTED));
    }

    private void setCurrentState(AnimeState state) {
        Anime anime = this.lvAnimeList.getSelectionModel().getSelectedItem();

        if (anime != null) {
            anime.setState(state);
            this.prStatus.setProgress(state.getValue());
        }

        try {
            this.dataController.saveAnime();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Critical Error!");
            alert.setContentText("Could not save data");
            alert.showAndWait();
        }
    }
}