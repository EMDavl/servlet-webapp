package ru.itis.listeners;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.repositories.*;
import ru.itis.services.*;
import ru.itis.utils.ConnectionManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        TasksRepository tasksRepository = new TasksRepositoryImpl(ConnectionManager.getConnection());
        ProfileRepository profileRepository = new ProfileRepositoryImpl(ConnectionManager.getConnection());
        ClientsRepository clientRepository = new ClientsRepositoryImpl(ConnectionManager.getConnection());

        ServletContext servletContext = sce.getServletContext();

        TaskService taskService = createTaskService(tasksRepository);
        ProfileService profileService = createProfileService(profileRepository);
        ProfileEditService profileEditService = createProfileEditService(profileRepository, passwordEncoder);
        LogoutService logoutService = createLogoutService();
        SignInService signInService = createSignInService(profileRepository, passwordEncoder);
        SignUpService signUpService = createSignUpService(profileRepository, passwordEncoder);
        ClientService clientService = createClientService(clientRepository);

        servletContext.setAttribute("taskService", taskService);
        servletContext.setAttribute("profileService", profileService);
        servletContext.setAttribute("profileEditService", profileEditService);
        servletContext.setAttribute("clientService", clientService);
        servletContext.setAttribute("logoutService", logoutService);
        servletContext.setAttribute("signInService", signInService);
        servletContext.setAttribute("signUpService", signUpService);
    }

    private ClientService createClientService(ClientsRepository clientRepository) {
        return new ClientServiceImpl(clientRepository);
    }

    private ProfileEditService createProfileEditService(ProfileRepository profileRepository, PasswordEncoder passwordEncoder) {
        return new ProfileEditServiceImpl(passwordEncoder, profileRepository);
    }

    private SignUpService createSignUpService(ProfileRepository profileRepository, PasswordEncoder passwordEncoder) {
        return new SignUpServiceImpl(profileRepository, passwordEncoder);
    }

    private SignInService createSignInService(ProfileRepository profileRepository, PasswordEncoder passwordEncoder) {
        return new SignInServiceImpl(profileRepository, passwordEncoder);
    }

    private LogoutService createLogoutService() {
        return new LogoutServiceImpl();
    }

    private ProfileService createProfileService(ProfileRepository repository) {
        return new ProfileServiceImpl(repository);
    }

    private TaskService createTaskService(TasksRepository repository) {
        return new TaskServiceImpl(repository);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionManager.closeConnections();
    }

}
