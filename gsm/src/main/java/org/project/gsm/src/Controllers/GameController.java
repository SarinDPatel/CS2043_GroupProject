package org.project.gsm.src.Controllers;

import java.util.ArrayList;
import org.project.gsm.src.Game;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/games")
public class GameController {

    @GetMapping
    public ResponseEntity<ArrayList<Game>> getAllGames() {
        ArrayList<Game> games = getGamesFromDB();
        return ResponseEntity.ok(games);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable String id) {
        Game game = findGameById(id);
        if (game != null) {
            return ResponseEntity.ok(game);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping
    public ResponseEntity<String> createGame(@RequestBody Game game) {
        boolean success = saveGame(game);
        if (success) {
            return ResponseEntity.ok("Game created successfully");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to create game");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateGame(@PathVariable String id, @RequestBody Game game) {
        boolean success = updateGameInDB(id, game);
        if (success) {
            return ResponseEntity.ok("Game updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Game not found");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGame(@PathVariable String id) {
        boolean success = deleteGameFromDB(id);
        if (success) {
            return ResponseEntity.ok("Game deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Game not found");
    }

    @GetMapping("/search")
    public ResponseEntity<ArrayList<Game>> searchGames(@RequestParam String query) {
        ArrayList<Game> results = searchGamesInDB(query);
        return ResponseEntity.ok(results);
    }

    /* Utilities and Stubs */
    private ArrayList<Game> getGamesFromDB() {
        // TODO: Implement database fetch
        return new ArrayList<>();
    }

    private Game findGameById(String id) {
        // TODO: Implement database fetch
        return null;
    }

    private boolean saveGame(Game game) {
        // TODO: Implement database save
        return true;
    }

    private boolean updateGameInDB(String id, Game game) {
        // TODO: Implement database update
        return true;
    }

    private boolean deleteGameFromDB(String id) {
        // TODO: Implement database delete
        return true;
    }

    private ArrayList<Game> searchGamesInDB(String query) {
        // TODO: Implement database search
        return new ArrayList<>();
    }
} 