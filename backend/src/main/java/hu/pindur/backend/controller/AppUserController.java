package hu.pindur.backend.controller;

import hu.pindur.backend.dto.*;
import hu.pindur.backend.service.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/updateAppUser/{appuserId}")
    public ResponseEntity<AppUserInfo> updateAppUserByIdAsUser(@PathVariable("appuserId") Long id, @Valid @RequestBody AppUserUpdateCommand command)  {
        log.info("Http request, PUT /api/users/{appuserId} body: " + command.toString() + " with variable: " + id);
        AppUserInfo appUserInfo = appUserService.updateAppUserByIdAsUser(id, command);
        return new ResponseEntity<>(appUserInfo, HttpStatus.OK);
    }





}
