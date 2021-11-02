package ru.itis.services;

import ru.itis.models.SimpleUserModel;
import ru.itis.models.UserModel;
import ru.itis.repositories.ProfileRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

public class ProfileServiceImpl implements ProfileService {

    private ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public UserModel getUserModel(HttpSession session) {

        String email = (String) session.getAttribute("email");
        return profileRepository.findByEmail(email);

    }

    @Override
    public List<SimpleUserModel> getAllUsersExceptAlreadyAddedToClient(int cid) {
        return profileRepository.findAllExceptAlreadyAddedToClient(cid);
    }
}
