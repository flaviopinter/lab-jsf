/*
 * Copyright (C) 2021 - Flavio Freitas Pinter
 */

package dev.pinter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class GameService {
    private final DAO dao = new DAO();

    public GameService() {
    }

    public List<Game> getGameList() {
        List<Game> games = dao.selectGame();
        for (Game g : games) {
            File file = new File(System.getProperty("user.dir") + "/JsfGamesImages/" + g.getId() + ".png");
            if (file.isFile()) {
                try {
                    InputStream is = Files.newInputStream(file.toPath(), StandardOpenOption.READ);
                    g.setImg(is.readAllBytes());
                } catch (IOException ignored) {

                }
            }
        }
        return games;
    }

    public void createGame(int id, String jogo) throws DataAccessException {
        dao.insertGame(id, jogo);
    }
}
