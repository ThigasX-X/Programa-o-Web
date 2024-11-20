package controller;

import dao.FilmeDAO;
import model.Filme;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/filme")
public class FilmeServlet extends HttpServlet {
    private FilmeDAO filmeDAO;

    public void init() {
        filmeDAO = new FilmeDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertFilme(request, response);
                    break;
                case "delete":
                    deleteFilme(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateFilme(request, response);
                    break;
                case "search":
                	searchFilmeByNome(request, response);
                	break;
                default:
                    listFilme(request, response);
                    break;
                
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listFilme(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Filme> listFilme = filmeDAO.selectAllFilmes();
        request.setAttribute("listFilme", listFilme);
        request.getRequestDispatcher("FilmeList.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("FilmeForm.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Filme existingFilme = filmeDAO.selectFilme(id);
        request.setAttribute("filme", existingFilme);
        request.getRequestDispatcher("FilmeForm.jsp").forward(request, response);
    }

    private void insertFilme(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String data_inclusao = request.getParameter("data_inclusao");
        Filme novoFilme = new Filme(nome, descricao, data_inclusao);
        filmeDAO.insertFilme(novoFilme);
        response.sendRedirect("filme?action=list");
    }

    private void updateFilme(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String data_inclusao = request.getParameter("data_inclusao");

        Filme filme = new Filme(nome, descricao, data_inclusao);
        filme.setId(id);
        filmeDAO.updateFilme(filme);
        response.sendRedirect("filme?action=list");
    }

    private void deleteFilme(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        filmeDAO.deleteFilme(id);
        response.sendRedirect("filme?action=list");
    }
    
    private void searchFilmeByNome(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	String query = request.getParameter("query");
    	List<Filme> filmes = filmeDAO.searchFilmeByNome(query);
        request.setAttribute("listFilme", filmes);
        request.getRequestDispatcher("FilmeList.jsp").forward(request, response);
    }
}

