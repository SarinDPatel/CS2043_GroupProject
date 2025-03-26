package org.project.gsm.src.Controllers;

import java.util.ArrayList;
import org.project.gsm.src.Manager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/managers")
public class ManagerController {

    @GetMapping
    public ResponseEntity<ArrayList<Manager>> getAllManagers() {
        ArrayList<Manager> managers = getManagersFromDB();
        return ResponseEntity.ok(managers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manager> getManagerById(@PathVariable String id) {
        Manager manager = findManagerById(id);
        if (manager != null) {
            return ResponseEntity.ok(manager);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping
    public ResponseEntity<String> createManager(@RequestBody Manager manager) {
        boolean success = saveManager(manager);
        if (success) {
            return ResponseEntity.ok("Manager created successfully");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to create manager");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateManager(@PathVariable String id, @RequestBody Manager manager) {
        boolean success = updateManagerInDB(id, manager);
        if (success) {
            return ResponseEntity.ok("Manager updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Manager not found");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteManager(@PathVariable String id) {
        boolean success = deleteManagerFromDB(id);
        if (success) {
            return ResponseEntity.ok("Manager deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Manager not found");
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity<ArrayList<String>> getManagerEmployees(@PathVariable String id) {
        Manager manager = findManagerById(id);
        if (manager != null) {
            return ResponseEntity.ok(manager.getEmployeeIds());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    /* Utilities and Stubs */
    private ArrayList<Manager> getManagersFromDB() {
        // TODO: Implement database fetch
        return new ArrayList<>();
    }

    private Manager findManagerById(String id) {
        // TODO: Implement database fetch
        return null;
    }

    private boolean saveManager(Manager manager) {
        // TODO: Implement database save
        return true;
    }

    private boolean updateManagerInDB(String id, Manager manager) {
        // TODO: Implement database update
        return true;
    }

    private boolean deleteManagerFromDB(String id) {
        // TODO: Implement database delete
        return true;
    }
} 