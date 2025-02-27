package com.parkingApp.service;

import com.parkingApp.dto.AuthorizationRequest;
import com.parkingApp.dto.PostNewAttendant;
import com.parkingApp.dto.UserDTO;
import com.parkingApp.entities.ParkingAttendant;
import com.parkingApp.exception.InvalidRoleException;
import com.parkingApp.exception.UsernameTakenException;
import com.parkingApp.repositories.ParkingAttendantRepository;
import com.parkingApp.config.JwtService;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCredentialServiceImpl implements UserCredentialService {

    private final ParkingAttendantRepository attendantRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public UserCredentialServiceImpl(ParkingAttendantRepository attendantRepository,
                                     PasswordEncoder passwordEncoder,
                                     AuthenticationManager authenticationManager,
                                     JwtService jwtService) {
        this.attendantRepository = attendantRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public UserDTO createAttendant(PostNewAttendant postNewAttendant) {
        try {
            ParkingAttendant attendant = new ParkingAttendant(
                    postNewAttendant.username().toLowerCase(),
                    passwordEncoder.encode(postNewAttendant.password()),
                    "ATTENDANT"
            );
            attendantRepository.save(attendant);
            return new UserDTO(attendant.getUsername(), attendant.getRole());
        } catch (DataAccessException e) {
            throw new RuntimeException("Database connection failed: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Registration failed: " + e.getMessage(), e);
        }
    }

    @Override
    public String login(AuthorizationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        return jwtService.generateToken(request.username());
    }
}

