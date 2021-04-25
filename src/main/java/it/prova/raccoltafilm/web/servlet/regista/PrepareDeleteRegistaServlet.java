package it.prova.raccoltafilm.web.servlet.regista;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.raccoltafilm.model.Regista;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.service.RegistaService;

@WebServlet("/PrepareDeleteRegistaServlet")
public class PrepareDeleteRegistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrepareDeleteRegistaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idParameter = request.getParameter("idRegista");

		RegistaService registaInstance = MyServiceFactory.getRegistaServiceInstance();
		Regista result = null;

		if (!NumberUtils.isCreatable(idParameter)) {

			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/regista/list.jsp").forward(request, response);
			return;
		}

		try {

			if (!registaInstance.caricaSingoloElementoConFilms(Long.parseLong(idParameter)).getFilms().isEmpty()) {
				request.setAttribute("errorMessage",
						"Attenzione impossibile rimuovere un regista con dei film associati.");
				request.setAttribute("registi_list_attribute",
						MyServiceFactory.getRegistaServiceInstance().listAllElements());

				response.sendRedirect("ExecuteListRegistaServlet?operationResult=ERROR");
				return;
			}

			result = registaInstance.caricaSingoloElementoConFilms(Long.parseLong(idParameter));

		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/regista/search.jsp").forward(request, response);
			return;
		}

		request.setAttribute("regista_delete", result);

		request.getRequestDispatcher("/regista/delete.jsp").forward(request, response);
	}

}
