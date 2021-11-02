package ru.itis.repositories;

import ru.itis.forms.ClientForm;
import ru.itis.models.ClientModel;

import java.util.List;

public interface ClientsRepository {
    void saveClient(ClientForm clientForm, int creatorId);

    List<ClientModel> findClientsByCreatorId(Integer userId);

    boolean isClientExists(int idVal);

    boolean isClientConnectedWithUser(int idVal, int userId);

    ClientModel findClientById(Integer clientId);

    void deleteClientById(int clientId);

    void updateClient(ClientModel clientModel);

    void connectClientWithUsers(List<Integer> collect, int cid);
}
