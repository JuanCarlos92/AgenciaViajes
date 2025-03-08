package org.juancarlos.security.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Servicio para la generación, validación y extracción de información de tokens JWT.
 */
@Service
public class JwtService {

    /**
     * Clave secreta para firmar los tokens JWT, obtenida de las propiedades de configuración.
     */
    @Value("${security.jwt.secret-key}")
    private String secretKey;

    /**
     * Tiempo de expiración del token JWT en milisegundos, definido en la configuración.
     */
    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    /**
     * Genera un token JWT sin reclamos adicionales.
     *
     * @param userDetails Detalles del usuario para el cual generar el token.
     * @return Token JWT generado.
     */
    public String generateToken(UserDetails userDetails, String role) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", role);
        return generateToken(extraClaims, userDetails);
    }

    /**
     * Genera un token JWT con reclamos adicionales.
     *
     * @param extraClaims Mapa de reclamos adicionales a incluir en el token.
     * @param userDetails Detalles del usuario para el cual generar el token.
     * @return Token JWT generado.
     */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    /**
     * Obtiene el tiempo de expiración configurado para los tokens JWT.
     *
     * @return Tiempo de expiración en milisegundos.
     */
    public long getExpirationTime() {
        return jwtExpiration;
    }

    /**
     * Construye un token JWT con los parámetros proporcionados.
     *
     * @param extraClaims Reclamos adicionales a incluir en el token.
     * @param userDetails Detalles del usuario asociado al token.
     * @param expiration  Tiempo de expiración del token en milisegundos.
     * @return Token JWT generado.
     */
    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Obtiene la clave secreta utilizada para firmar los tokens JWT.
     *
     * @return Clave secreta en formato {@link Key}.
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}