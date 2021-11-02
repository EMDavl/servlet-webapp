package ru.itis.services;

import ru.itis.forms.ClientForm;
import ru.itis.models.ClientModel;
import ru.itis.repositories.ClientsRepository;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientServiceImpl implements ClientService {

    private ClientsRepository repository;

    public ClientServiceImpl(ClientsRepository repository) {
        this.repository = repository;
    }

    @Override
    public ClientForm getFormFromRequest(HttpServletRequest req) {
        ClientForm clientForm = new ClientForm();
        clientForm.setName(req.getParameter("name"));
        clientForm.setSurname(req.getParameter("surname"));
        clientForm.setDescription(req.getParameter("description"));
        clientForm.setEmail(req.getParameter("email"));
        clientForm.setMobileNum(req.getParameter("mobileNum"));
        clientForm.setBirthDate
                (LocalDate.parse(
                        req.getParameter("birthDate")));

        return clientForm;
    }



    @Override
    public List<ClientModel> getClientsList(Integer userId) {
        return repository.findClientsByCreatorId(userId);
    }

    @Override
    public void saveClient(ClientForm clientForm, int creatorId) {
        repository.saveClient(clientForm, creatorId);
    }

    @Override
    public boolean isClientIdValid(String clientId) {
        if(!clientId.matches("[0-9]+")){
            return false;
        }

        int idVal = -1;

        try {
            idVal = Integer.parseInt(clientId);
        }catch (NumberFormatException e){
            return false;
        }

        return repository.isClientExists(idVal);
    }

    @Override
    public boolean isClientConnectedWithUser(String clientId, int userId) {
        int idVal = Integer.parseInt(clientId);
        return repository.isClientConnectedWithUser(idVal, userId);
    }

    @Override
    public ClientModel getClient(Integer clientId) {
        return repository.findClientById(clientId);
    }

    @Override
    public void deleteClient(int clientId) {
        repository.deleteClientById(clientId);
    }

    @Override
    public ClientModel getModelFromRequest(HttpServletRequest request) {
        ClientModel model = new ClientModel(
                Integer.parseInt(request.getParameter("cid")),
                request.getParameter("clientEmail"),
                request.getParameter("clientFirstName"),
                request.getParameter("clientLastName"),
                request.getParameter("mobileNum"),
                request.getParameter("description"),
                LocalDate.parse(request.getParameter("birthDate"))
        );
        return model;
    }

    @Override
    public void updateClient(ClientModel model) {
        repository.updateClient(model);
    }

    @Override
    public void connectUsers(Set<String> parameters, int cid) {
        List<Integer> collect = parameters.stream().map(Integer::parseInt).collect(Collectors.toList());
        repository.connectClientWithUsers(collect, cid);
    }
}
