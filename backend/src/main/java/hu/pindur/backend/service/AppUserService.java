package hu.pindur.backend.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import hu.pindur.backend.domain.AppUser;
import hu.pindur.backend.domain.UserRole;
import hu.pindur.backend.dto.*;
import hu.pindur.backend.repository.AppUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AppUserService implements UserDetailsService {

    private ModelMapper modelMapper;
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Value("${security.jwt.secret}")
    private String SECRET_KEY;

    @Value("${security.jwt.expiration}")
    private Long ACCESS_TOKEN_EXPIRATION_TIME;

    @Value("${security.jwt.refresh}")
    private Long REFRESH_TOKEN_EXPIRATION_TIME;

    @Autowired
    public AppUserService(ModelMapper modelMapper, AppUserRepository appUserRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.modelMapper = modelMapper;
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public AppUserInfo saveAppUser(AppUserCreateCommand command) {
        AppUser appUser = new AppUser();
        appUser.setFirstName(command.getFirstName());
        appUser.setLastName(command.getLastName());
        appUser.setPassword(command.getPassword());
        appUser.setCreationDate(LocalDateTime.now());
        appUser.setEmail(command.getEmail());

        appUserRepository.save(appUser);
        return modelMapper.map(appUser, AppUserInfo.class);
    }

    public AppUserInfo updateAppUserByIdAsUser(Long id, AppUserUpdateCommand command) {
        AppUser appUser = findUserById(id);
        modelMapper.map(command, appUser);
        appUserRepository.save(appUser);
        return modelMapper.map(appUser, AppUserInfo.class);

    }

    private AppUser findUserById(Long id) {
        Optional<AppUser> optionalAppUser = appUserRepository.findById(id);
        if (optionalAppUser.isEmpty()) {
//            throw new UserNotFoundException(id);
        }
        return optionalAppUser.get();
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = findUserByEmail(email);

        String[] roles = appUser.getRoles().stream()
                .map(Enum::toString)
                .toArray(String[]::new);

        return User
                .withUsername(appUser.getEmail())
                .authorities(AuthorityUtils.createAuthorityList(roles))
                .password(appUser.getPassword())
                .build();
    }

    public AppUser findUserByEmail(String email) {
        Optional<AppUser> optionalAppUser = appUserRepository.findByEmail(email);
        if (optionalAppUser.isEmpty()) {
//            throw new UserEmailNotFoundException(email);
        }
        return optionalAppUser.get();
    }

    public AuthenticationResponseDto login(AuthenticationRequestDto authenticationRequestDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authenticationRequestDto.getEmail(), authenticationRequestDto.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        User user = (User) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());
        String accessToken = JWT.create()
                // Unique data about user
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME))
                // Company name or company url
                .withIssuer("http://localhost:8080/api/users/login")
                // Roles
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
        String refreshToken = JWT.create()
                // Unique data about user
                .withSubject(user.getUsername())
                // Hosszabb idő kell, bármennyi lehet
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME))
                // Company name or company url
                .withIssuer("http://localhost:8080/api/users/login")
                .sign(algorithm);
        return new AuthenticationResponseDto(accessToken, refreshToken, createAuthenticatedUserInfo(authenticationRequestDto));
    }

    private AuthenticatedUserInfo createAuthenticatedUserInfo(AuthenticationRequestDto authenticationRequestDto) {
        AuthenticatedUserInfo authenticatedUserInfoJWT = new AuthenticatedUserInfo();
        authenticatedUserInfoJWT.setEmail(authenticationRequestDto.getEmail());
        authenticatedUserInfoJWT.setRoles(parseUserRoles(loadUserByUsername(authenticationRequestDto.getEmail())));
        return authenticatedUserInfoJWT;
    }

    private List<UserRole> parseUserRoles(UserDetails user) {
        return user.getAuthorities()
                .stream()
                .map(authority -> UserRole.valueOf(authority.getAuthority()))
                .collect(Collectors.toList());
    }

    public RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String refreshToken = refreshTokenRequest.getRefreshToken();
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(refreshToken);
        String username = decodedJWT.getSubject();
        AppUser user = findByUsername(username);

        String accessToken = JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME))
                .withIssuer("http://localhost:8080/api/users/token/refresh")
                .withClaim("roles", user.getRoles().stream().map(UserRole::getRole).collect(Collectors.toList()))
                .sign(algorithm);

        return new RefreshTokenResponse(accessToken);
    }

    public AppUser findByUsername(String username) {
        Optional<AppUser> appUserOptional = appUserRepository.findByEmail(username);
        if (appUserOptional.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return appUserOptional.get();
    }

}

