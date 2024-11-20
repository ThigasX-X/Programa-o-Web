package dao;

import model.Filme;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmeDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/locadora";
    private String jdbcUsername = "root";  
    private String jdbcPassword = "12345";  

    private static final String INSERT_FILME_SQL = "INSERT INTO filmes (nome, descricao, data_inclusao) VALUES (?, ?, ?)";
    private static final String SELECT_FILME_BY_ID = "SELECT id, nome, descricao, data_inclusao FROM filmes WHERE id = ?";
    private static final String SELECT_ALL_FILMES = "SELECT * FROM filmes";
    private static final String DELETE_FILME_SQL = "DELETE FROM filmes WHERE id = ?";
    private static final String UPDATE_FILME_SQL = "UPDATE filmes SET nome = ?, descricao= ?, data_inclusao =? WHERE id = ?";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }


    public void insertFilme(Filme filme) throws SQLException {
        try (Connection connection = getConnection(); 
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FILME_SQL)) {
            preparedStatement.setString(1, filme.getNome());
            preparedStatement.setString(2, filme.getDescricao());
            preparedStatement.setString(3, filme.getData_inclusao());
            preparedStatement.executeUpdate();
        }
    }

    public Filme selectFilme(int id) {
        Filme filme = null;
        try (Connection connection = getConnection(); 
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FILME_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                String data_inclusao = rs.getString("data_inclusao");
                filme = new Filme(nome, descricao, data_inclusao);
                filme.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filme;
    }
    
    public List<Filme> searchFilmeByNome(String query) {
    	List<Filme> filmes = new ArrayList<>();
    	 String searchQuery = "SELECT * FROM filmes WHERE nome LIKE ?";
    	try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {
               preparedStatement.setString(1, "%" + query + "%");
               ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
            	rs.getInt ("id");
            	String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                String data_inclusao = rs.getString("data_inclusao");
                Filme filme = new Filme(nome, descricao, data_inclusao);
                filme.setId(rs.getInt("id"));
                filmes.add(filme);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filmes;
    }

    public List<Filme> selectAllFilmes() {
        List<Filme> filmes = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FILMES)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                String data_inclusao = rs.getString("data_inclusao");
                Filme filme = new Filme(nome, descricao, data_inclusao);
                filme.setId(id);
                filmes.add(filme);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filmes;
    }

    public boolean updateFilme(Filme filme) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_FILME_SQL)) {
            statement.setString(1, filme.getNome());
            statement.setString(2, filme.getDescricao());
            statement.setString(3, filme.getData_inclusao());
            statement.setInt(4, filme.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean deleteFilme(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_FILME_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
}