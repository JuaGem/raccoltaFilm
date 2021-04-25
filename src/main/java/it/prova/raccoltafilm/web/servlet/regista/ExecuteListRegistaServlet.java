package it.prova.raccoltafilm.web.servlet.regista;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import it.prova.raccoltafilm.service.MyServiceFactory;

@WebServlet("/ExecuteListRegistaServlet")
public class ExecuteListRegistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
		
			String operationResult = request.getParameter("operationResult");
			if(StringUtils.isNotBlank(operationResult) && operationResult.equalsIgnoreCase("SUCCESS"))
				request.setAttribute("successMessage", "Operazione effettuata con successo");
			if (StringUtils.isNotBlank(operationResult) && operationResult.equalsIgnoreCase("ERROR"))
				request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			if (StringUtils.isNotBlank(operationResult) && operationResult.equalsIgnoreCase("NOT_FOUND"))
				request.setAttribute("notFoundMessage", "Elemento non trovato.");
			
			request.setAttribute("registi_list_attribute",
					MyServiceFactory.getRegistaServiceInstance().listAllElements());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/regista/search.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/regista/list.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operationResult = request.getParameter("operationResult");
		if (StringUtils.isNotBlank(operationResult) && operationResult.equalsIgnoreCase("SUCCESS"))
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		if (StringUtils.isNotBlank(operationResult) && operationResult.equalsIgnoreCase("ERROR"))
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
		if (StringUtils.isNotBlank(operationResult) && operationResult.equalsIgnoreCase("NOT_FOUND"))
			request.setAttribute("errorMessage", "Elemento non trovato.");
		
		
		try {
		 
			request.setAttribute("registi_list_attribute",
					MyServiceFactory.getRegistaServiceInstance().listAllElements());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/regista/search.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/regista/list.jsp").forward(request, response);
	}

}
