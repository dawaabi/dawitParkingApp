package com.parkingApp.service;

import com.parkingApp.dto.AuthorizationRequest;
import com.parkingApp.dto.PostNewAttendant;
import com.parkingApp.dto.UserDTO;

public interface UserCredentialService {
    UserDTO createAttendant(PostNewAttendant postNewAttendant);
    String login(AuthorizationRequest request);
}
