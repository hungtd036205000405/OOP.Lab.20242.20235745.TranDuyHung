package com.example.shoestore.service;
import java.text.ParseException;
import java.util.logging.Logger;
import com.example.shoestore.dto.request.AuthenticationRequest;
import com.example.shoestore.dto.request.IntrospectRequest;
import com.example.shoestore.dto.response.AuthenticationResponse;
import com.example.shoestore.dto.response.IntrospectResponse;
import com.example.shoestore.exception.AppException;
import com.example.shoestore.exception.ErrrorCode;
import com.example.shoestore.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
@Slf4j
@Service
@RequiredArgsConstructor
@Data// Lombok will generate getters, setters, toString, equals, and hashCode methods
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;

    @NonFinal
    // Chú ý đến việc bảo mật khóa bí mật này, không nên để lộ ra ngoài
    // Chú ý Key không đủ dài cho thuật toán HS512 (cần ít nhất 64 bytes)
    // @Value("${jwt.signerKey}")
    @Value("${jwt.signerKey}")
    protected String SINGER_KEY;


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrrorCode.USER_NOT_EXISTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);// tạo để dùng hàm match kiểm tra 2 passwwork
        boolean authenticated =  passwordEncoder.matches(request.getPassword(), user.getPassword());

        if(!authenticated) {
            throw new AppException(ErrrorCode.UNCATEGORIZED_EXCEPTION);
        }
        // Nếu xác thực thành công, tạo JWT token
        var token = generateToken(request.getUsername());
        return AuthenticationResponse.builder()
                .token(token)
                .isAuthenticated(true)
                .build();
    }

    private String generateToken(String username) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("Shoestore")
                .issueTime(new Date())// Thời gian phát hành token
                .expirationTime(new Date(System.currentTimeMillis() + 3600 * 1000)) // Token có hiệu lực trong 1 giờ
                .claim("roles", "USER") // Thêm các claim khác nếu cần
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);
        try{
            jwsObject.sign(new MACSigner(SINGER_KEY.getBytes()));
            return jwsObject.serialize(); // Trả về token đã ký
        }catch (JOSEException e){
            log.error("Error signing JWT", e);
            throw new AppException(ErrrorCode.USER_NOT_EXISTED);
        }

    }

    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();

        JWSVerifier verifier = new MACVerifier(SINGER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expityTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        return IntrospectResponse.builder()
                .valid(verified && expityTime.after(new Date()))// Kiểm tra xem token đã hết hạn hay chưa
                .build();

    }
}
