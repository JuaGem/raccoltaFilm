<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

	<meta charset="ISO-8859-1">
	
	<jsp:include page="../header.jsp" />
	<link href="./assets/css/global.css" rel="stylesheet">

	<title>Modifica Regista</title>

</head>

<body>

	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
			<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
			  ${errorMessage}
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
			
			<div class='card'>
			    <div class='card-header'>
			        <h5>Inserisci elemento da modificare</h5> 
			    </div>
			    <div class='card-body'>
	
					<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
	
						<form method="post" action="ExecuteModificaRegistaServlet" novalidate="novalidate">
						
							<div class="form-row">
								<div class="form-group col-md-6">
								
									<label>Nome<span class="text-danger">*</span></label>
									<input type="text" name="nome" id="nome" class="form-control" 
									value="${edit_regista_attribute.nome}" placeholder="Inserire il nome da modificare" required autofocus>
								
								</div>
								
								<div class="form-group col-md-6">
								
									<label>Cognome<span class="text-danger">*</span></label>
									<input type="text" name="cognome" id="cognome" class="form-control" 
									value="${edit_regista_attribute.cognome}" placeholder="Inserire il cognome da modificare" required autofocus>
								
								</div>
							</div>
							
							<div class="form-row">	
								 
								 <div class="form-group col-md-6">
								
									<label>NickName<span class="text-danger">*</span></label>
									<input type="text" name="nickName" id="nickName" class="form-control" 
									value="${edit_regista_attribute.nickName}" placeholder="Inserire il nickName da modificare" required autofocus>
								
								</div>
								
								<div class="form-group col-md-3">
									<fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${edit_regista_attribute.dataDiNascita}" var="dataNascitaParsed"/>
									<label>Data Nascita<span class="text-danger">*</span></label>
		                     		<input class="form-control" id="dataDiNascita" type="date" placeholder="dd/MM/yy" 
		                         	title="formato : gg/mm/aaaa" value="${dataNascitaParsed}" name="dataDiNascita" required>
		                         	
								</div>
								
								<div class="form-group col-md-3">
								<label for="sesso">Sesso <span class="text-danger">*</span></label>
							    <select class="form-control" id="sesso" name="sesso" required>
							    	<option value="" selected> - Selezionare - </option>
							      	<option value="MASCHIO" ${insert_regista_attr.sesso == MASCHIO?'selected':''} >M</option>
							      	<option value="FEMMINA" ${insert_regista_attr.sesso == FEMMINA?'selected':''} >F</option>
							    </select>
							</div>
								
							</div>
							
						 
						
			      			<input type="hidden" name="idEditInput" value="${edit_regista_attribute.id}">
						
							<a href="ExecuteListRegistaServlet" class='btn btn-outline-secondary' style='width:7em;'>
			           			<i class='fa fa-chevron-left'></i> Indietro
			      			</a>
							<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary" style='width:7em;'>Modifica</button>
						
						</form>
			    
				<!-- end card-body -->			   
			    </div>
			</div>	
	
	
	<!-- end container -->	
	</main>
				
	<jsp:include page="../footer.jsp" />	
	
</body>

</html>