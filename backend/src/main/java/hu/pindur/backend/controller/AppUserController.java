package hu.pindur.backend.controller;

import hu.pindur.backend.dto.AppUserCreateCommand;
import hu.pindur.backend.dto.AppUserInfo;
import hu.pindur.backend.service.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/appusers")
@Slf4j
public class AppUserController {
    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }


    @PostMapping("/saveAppUser")
    public ResponseEntity<AppUserInfo> saveAppUser(@Valid @RequestBody AppUserCreateCommand command)  {
        log.info("Http request, POST / /api/users/saveAppUser");
        AppUserInfo appUserInfo = appUserService.saveAppUser(command);
        return new ResponseEntity<>(appUserInfo, HttpStatus.OK);
    }


}
