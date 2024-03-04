package hu.pindur.backend.service;

import hu.pindur.backend.domain.AppUser;
import hu.pindur.backend.dto.AppUserCreateCommand;
import hu.pindur.backend.dto.AppUserInfo;
import hu.pindur.backend.repository.AppUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class AppUserService {

    private ModelMapper modelMapper;
    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserService(ModelMapper modelMapper, AppUserRepository appUserRepository) {
        this.modelMapper = modelMapper;
        this.appUserRepository = appUserRepository;
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
}
