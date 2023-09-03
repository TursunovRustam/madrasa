package com.example.backend.Services.GroupService;

import com.example.backend.DTO.GroupDTO;
import org.springframework.http.HttpEntity;

import java.util.UUID;

public interface GroupService {
    HttpEntity<?> getGroups();
    HttpEntity<?> addGroups(GroupDTO DTO);

    HttpEntity<?> editGroup(UUID id, GroupDTO dto);
     HttpEntity<?> deleteGroupById(UUID id);

    Object findByGroupId(UUID groupId);
}
