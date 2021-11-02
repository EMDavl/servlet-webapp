package ru.itis.services;

import ru.itis.forms.ClientForm;
import ru.itis.models.ClientModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

public interface ClientService {

    ClientForm getFormFromRequest(HttpServletRequest req);

    List<ClientModel> getClientsList(Integer userId);

    void saveClient(ClientForm clientForm, int creatorId);

    boolean isClientIdValid(String clientId);

    boolean isClientConnectedWithUser(String clientId, int userId);

    ClientModel getClient(Integer valueOf);

    void deleteClient(int parseInt);

    ClientModel getModelFromRequest(HttpServletRequest request);

    void updateClient(ClientModel model);

    void connectUsers(Set<String> parameters, int cid);
}

