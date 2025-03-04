package org.juancarlos.security.controller;

import org.juancarlos.security.service.JwtService;
import org.juancarlos.security.entity.UserEntity;
import org.juancarlos.security.dto.LoginDTO;
import org.juancarlos.security.dto.LoginResponse;
import org.juancarlos.security.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador encargado de gestionar las operaciones de autenticación de usuarios.
 * Proporciona un endpoint para el login y la generación de tokens JWT.
 */
@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    /**
     * Constructor que inyecta los servicios necesarios para la autenticación y generación de JWT.
     *
     * @param jwtService servicio encargado de la generación y validación de tokens JWT
     * @param authenticationService servicio encargado de autenticar al usuario
     */
    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    /**
     * Endpoint para autenticar a un usuario y generar un token JWT.
     * Recibe las credenciales de inicio de sesión, las valida, y responde con un token JWT válido y su tiempo de expiración.
     *
     * @param loginUserDto objeto que contiene las credenciales de inicio de sesión (usuario y contraseña)
     * @return una respuesta con el token JWT y su tiempo de expiración en formato JSON
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginDTO loginUserDto) {

        // Autenticar al usuario con las credenciales proporcionadas
        UserEntity authenticatedUser = authenticationService.authenticate(loginUserDto);

        // Generar el token JWT para el usuario autenticado
        String jwtToken = jwtService.generateToken(authenticatedUser);

        // Crear la respuesta con el token JWT y su tiempo de expiración
        LoginResponse loginResponse = LoginResponse.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime()).build();

        // Retornar la respuesta con código 200 OK y el objeto LoginResponse
        return ResponseEntity.ok(loginResponse);
    }
}
