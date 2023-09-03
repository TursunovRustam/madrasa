package com.example.backend.Controller;

import com.example.backend.DTO.GroupDTO;
import com.example.backend.Services.GroupService.GroupService;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService service;
    @GetMapping
    public HttpEntity<?> getGroups() {
        return service.getGroups();
    }
    @GetMapping("/{groupId}")
    public HttpEntity<?> getGroup(@PathVariable UUID groupId){
        return ResponseEntity.ok(service.findByGroupId(groupId));
    }
    @PostMapping
    public HttpEntity<?> addGroup(@RequestBody GroupDTO groupDTO) {
      return service.addGroups(groupDTO);
    };
    @PutMapping("/{id}")
    public HttpEntity<?> editGroup(@PathVariable UUID id,@RequestBody GroupDTO dto) {
        return service.editGroup(id,dto);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deletebyId(@PathVariable UUID id) {
      return service.deleteGroupById(id);
    };
}
