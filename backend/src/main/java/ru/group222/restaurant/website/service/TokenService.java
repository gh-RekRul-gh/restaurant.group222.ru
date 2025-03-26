package ru.group222.restaurant.website.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.group222.restaurant.website.exception.InvalidCodeException;
import ru.group222.restaurant.website.model.Token;
import ru.group222.restaurant.website.model.User;
import ru.group222.restaurant.website.repository.TokenRepository;
import ru.group222.restaurant.website.service.util.CodeGenerator;

import java.time.Instant;


@Service
@RequiredArgsConstructor
@Log4j2
public class TokenService {
    private final TokenRepository tokenRepository;

    public static final String PASSWORD_RESET_MESSAGE = """
                Hello there,
                Your password reset code is:
                
                %s
                
                This code will expire in 15 minutes""";

    public Token findTokenByCodeOrElseThrow(String code) {
        return tokenRepository.findByCode(code)
                .orElseThrow(() -> {
                    String message = String.format("Token not found with code: %s", code);
                    log.warn(message);
                    return new InvalidCodeException(message);
                });
    }

    public void deleteToken(Token token) {
        tokenRepository.delete(token);
        log.info("Token deleted with code: {}", token.getCode());
    }

    public void checkTokenExpiration(Token token) {
        if (token.getExpiresAt().isBefore(Instant.now())) {
            String message = String.format("Token is expired with code: %s", token.getCode());
            log.warn(message);

            deleteToken(token);

            throw new InvalidCodeException(message);
        }
    }

    public Token createUserToken(User user) {
        Token token = new Token();

        String code = CodeGenerator.generateCode(8);
        while (tokenRepository.existsByCode(code)) {
            code = CodeGenerator.generateCode(8);
        }

        token.setUser(user);
        token.setCode(code);
        token.setExpiresAt(Instant.now().plusSeconds(900));
        tokenRepository.save(token);

        log.info("Token is created for user with email: {}", user.getEmail());

        return token;
    }
}
