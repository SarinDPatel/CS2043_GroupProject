package org.project.gsm.src.Controllers;

import java.util.ArrayList;
import org.project.gsm.src.Playware;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/playware")
public class PlaywareController {

	@GetMapping
	public ResponseEntity<ArrayList<Playware>> getAllPlayware() {
		ArrayList<Playware> playware = getPlaywareFromDB();
		return ResponseEntity.ok(playware);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Playware> getPlaywareById(@PathVariable String id) {
		Playware item = findPlaywareById(id);
		if (item != null) {
			return ResponseEntity.ok(item);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@PostMapping
	public ResponseEntity<String> createPlayware(@RequestBody Playware playware) {
		boolean success = savePlayware(playware);
		if (success) {
			return ResponseEntity.ok("Playware item created successfully");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Failed to create playware item");
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updatePlayware(@PathVariable String id, @RequestBody Playware playware) {
		boolean success = updatePlaywareInDB(id, playware);
		if (success) {
			return ResponseEntity.ok("Playware item updated successfully");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("Playware item not found");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePlayware(@PathVariable String id) {
		boolean success = deletePlaywareFromDB(id);
		if (success) {
			return ResponseEntity.ok("Playware item deleted successfully");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("Playware item not found");
	}

	@GetMapping("/search")
	public ResponseEntity<ArrayList<Playware>> searchPlayware(@RequestParam String query) {
		ArrayList<Playware> results = searchPlaywareInDB(query);
		return ResponseEntity.ok(results);
	}

	@GetMapping("/category/{category}")
	public ResponseEntity<ArrayList<Playware>> getPlaywareByCategory(@PathVariable String category) {
		ArrayList<Playware> results = getPlaywareByCategoryFromDB(category);
		return ResponseEntity.ok(results);
	}

	/* Utilities and Stubs */
	private ArrayList<Playware> getPlaywareFromDB() {
		// TODO: Implement database fetch
		return new ArrayList<>();
	}

	private Playware findPlaywareById(String id) {
		// TODO: Implement database fetch
		return null;
	}

	private boolean savePlayware(Playware playware) {
		// TODO: Implement database save
		return true;
	}

	private boolean updatePlaywareInDB(String id, Playware playware) {
		// TODO: Implement database update
		return true;
	}

	private boolean deletePlaywareFromDB(String id) {
		// TODO: Implement database delete
		return true;
	}

	private ArrayList<Playware> searchPlaywareInDB(String query) {
		// TODO: Implement database search
		return new ArrayList<>();
	}

	private ArrayList<Playware> getPlaywareByCategoryFromDB(String category) {
		// TODO: Implement database category filter
		return new ArrayList<>();
	}
}
