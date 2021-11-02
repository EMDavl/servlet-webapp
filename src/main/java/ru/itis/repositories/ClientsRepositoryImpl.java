package ru.itis.repositories;

import ru.itis.forms.ClientForm;
import ru.itis.models.ClientModel;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ClientsRepositoryImpl implements ClientsRepository {

    private Connection connection;

    public ClientsRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    //language=SQL
    private String SQL_SAVE_CLIENT =
            "INSERT INTO clients(" +
                    "name, " +
                    "surname, " +
                    "email, " +
                    "birth_date, " +
                    "mobile_num, " +
                    "info, " +
                    "created_at) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?) RETURNING id";
    //language=SQL
    private String SQL_UPDATE_CLIENT = "UPDATE " +
            "clients " +
            "SET " +
            "   name=?, " +
            "   surname=?, " +
            "   email=?, " +
            "   birth_date=?, " +
            "   mobile_num=?, " +
            "   info=? " +
            "WHERE " +
            "   id=?";

    //language=SQL
    private String SQL_CONNECT_WITH_USER =
            "INSERT INTO " +
                    "users_clients(id_u, id_c, edited_at) " +
                    "VALUES(?, ?, ?)";

    //language=SQL
    private String SQL_FIND_CLIENTS_BY_CREATOR_ID =
            "SELECT " +
                    "id, " +
                    "name, " +
                    "surname, " +
                    "email, " +
                    "birth_date, " +
                    "mobile_num, " +
                    "info " +
                    "FROM clients INNER JOIN users_clients uc on clients.id = uc.id_c and id_u = ?";

    //language=SQL
    private String SQL_FIND_CLIENT_BY_ID =
            "SELECT " +
            "id, " +
            "name, " +
            "surname, " +
            "email, " +
            "birth_date, " +
            "mobile_num, " +
            "info " +
            "FROM clients WHERE id=?";

    //language=SQL
    private String SQL_FIND_ROW_IN_USERS_CLIENTS =
            "SELECT * FROM users_clients WHERE id_u = ? and id_c = ?";

//language=SQL
    private String SQL_DELETE_CLIENT_BY_ID =
            "DELETE FROM clients WHERE id=?";
    //language=SQL
    private String SQL_DELETE_CONNECTION_WITH_USER =
            "DELETE FROM users_clients WHERE id_c = ?";


    @Override
    public void saveClient(ClientForm clientForm, int creatorId) {
        try {
            int clientId = saveClient(clientForm);
            connectClientWithCreator(clientId, creatorId);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<ClientModel> findClientsByCreatorId(Integer userId) {
        List<ClientModel> clientModels = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_CLIENTS_BY_CREATOR_ID);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                clientModels.add(getModelFromRS(resultSet));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return clientModels;
    }

    @Override
    public boolean isClientExists(int idVal) {
        try{
            PreparedStatement statement =
                    connection.prepareStatement(SQL_FIND_CLIENT_BY_ID);
            statement.setInt(1, idVal);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isClientConnectedWithUser(int idVal, int userId) {
        try{
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ROW_IN_USERS_CLIENTS);
            statement.setInt(1, userId);
            statement.setInt(2, idVal);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ClientModel findClientById(Integer clientId) {
        ClientModel model = null;
        try{
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_CLIENT_BY_ID);
            statement.setInt(1, clientId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) model = getModelFromRS(resultSet);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return model;
    }

    @Override
    public void deleteClientById(int clientId) {
        try{
            deleteConnectionWithUser(clientId);
            deleteClient(clientId);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateClient(ClientModel clientModel) {
        try{
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_CLIENT);
            statement.setString(1, clientModel.getFirstName());
            statement.setString(2, clientModel.getLastName());
            statement.setString(3, clientModel.getEmail());
            statement.setDate(4, Date.valueOf(clientModel.getBirthDate()));
            statement.setString(5, clientModel.getMobileNum());
            statement.setLong(6, clientModel.getId());

            statement.executeUpdate();
        }catch (SQLException e){
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void connectClientWithUsers(List<Integer> collect, int cid) {
        for (int userId: collect) {
            try{
                connectClientWithCreator(cid, userId);
            }catch (SQLException e){
                throw new IllegalArgumentException(e);
            }
        }
    }

    private void deleteConnectionWithUser(int clientId) throws SQLException{
        PreparedStatement statement =
                connection.prepareStatement(SQL_DELETE_CONNECTION_WITH_USER);
        statement.setInt(1, clientId);
        statement.execute();
    }

    private void deleteClient(int clientId) throws SQLException {
        PreparedStatement statement =
                connection.prepareStatement(SQL_DELETE_CLIENT_BY_ID);
        statement.setInt(1, clientId);
        statement.execute();
    }

    private void connectClientWithCreator(int clientId, int creatorId) throws SQLException {
        PreparedStatement statement =
                connection.prepareStatement(SQL_CONNECT_WITH_USER);
        statement.setInt(1, creatorId);
        statement.setInt(2, clientId);
        statement.setTimestamp(3,
                Timestamp.valueOf(LocalDateTime.now()));
        statement.execute();
    }

    private int saveClient(ClientForm clientForm) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(SQL_SAVE_CLIENT);
        statement.setString(1, clientForm.getName());
        statement.setString(2, clientForm.getSurname());
        statement.setString(3, clientForm.getEmail());
        statement.setDate(4, Date.valueOf(clientForm.getBirthDate()));
        statement.setString(5, clientForm.getMobileNum());
        statement.setString(6, clientForm.getDescription());
        statement.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));

        ResultSet resultSet = statement.executeQuery();
        resultSet.next();

        return resultSet.getInt("id");
    }

    private ClientModel getModelFromRS(ResultSet set) throws SQLException {
        String email = set.getString("email");
        String firstName =
                set.getString("name");
        String lastName = set.getString("surname");
        String description = set.getString("info");
        String mobileNum = set.getString("mobile_num");
        LocalDate birthDate = set.getDate("birth_date").toLocalDate();
        int id = set.getInt("id");
        return new ClientModel(id,
                email,
                firstName,
                lastName,
                mobileNum,
                description,
                birthDate);
    }
}
