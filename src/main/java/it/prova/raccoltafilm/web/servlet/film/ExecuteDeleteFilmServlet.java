package it.prova.raccoltafilm.web.servlet.film;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.raccoltafilm.model.Film;
import it.prova.raccoltafilm.service.MyServiceFactory;

@WebServlet("/ExecuteDeleteFilmServlet")
public class ExecuteDeleteFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteDeleteFilmServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String idFilmParameter = request.getParameter("idDeleteInput");


		if (!NumberUtils.isCreatable(idFilmParameter)) {

			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/film/delete.jsp").forward(request, response);
			return;
		}

		try {

			Film filmInstance = MyServiceFactory.getFilmServiceInstance().caricaSingoloElementoEager(Long.parseLong(idFilmParameter));
			MyServiceFactory.getFilmServiceInstance().rimuovi(filmInstance);
			request.setAttribute("film_list_attribute", MyServiceFactory.getFilmServiceInstance().listAllElements());
			request.setAttribute("successMessage", "Operazione eseguita con successo!");

		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;

		}

		response.sendRedirect("ExecuteListFilmServlet?operationResult=SUCCESS");
	}

}

