package at.htlkaindorf.animelist.io;

import at.htlkaindorf.animelist.pojos.Anime;
import at.htlkaindorf.animelist.pojos.AnimeMovie;
import at.htlkaindorf.animelist.pojos.AnimeShow;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileAccess {
    private static final Path CSV_PATH = Path.of(
            System.getProperty("user.dir"),
            "src",
            "main",
            "resources",
            "anime.csv"
    );


    public static List<Anime> loadAnime() throws IOException {
        List<Anime> anime = new ArrayList<>();
        List<String> lines = Files.readAllLines(CSV_PATH);

        for (String line : lines) {
            String[] tokens = line.split(";");
            String type = tokens[0];

            if (type.equals("SHOW")) {
                anime.add(new AnimeShow(line));
            } else if (type.equals("MOVIE")) {
                anime.add(new AnimeMovie(line));
            }
        }

        return anime;
    }

    public static void saveAnime(List<Anime> anime) throws IOException {
        // type;title;studio;image;state;episodes;length

        BufferedWriter writer = Files.newBufferedWriter(CSV_PATH);
        writer.write("");
        writer.flush();

        writer.write("type;title;studio;image;state;episodes;length\n");
        for (Anime a : anime) {
            writer.write(a.toCSV() + "\n");
        }

        writer.close();
    }

}
