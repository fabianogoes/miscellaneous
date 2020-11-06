<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="utf-8">
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Controle de Aulas</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=no">
	<meta name="description" content="Controle de Aulas">
	<meta name="author" content="Fabiano Góes/Codeforse">

    <link rel="icon" href="${pageContext.request.contextPath}/static/img/favicon.ico">

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/icon-fonts.css" rel="stylesheet"/>

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/static/css/dashboard.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="${pageContext.request.contextPath}/static/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="${pageContext.request.contextPath}/static/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->	
</head>
<body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>          
          <img class="navbar-brand" src="${pageContext.request.contextPath}/static/img/control-panel.png" width="50px" height="50px" alt="ControleServ Logo" />
          <a class="navbar-brand" href="${pageContext.request.contextPath}/">
          	Controle de Aulas
          </a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="${pageContext.request.contextPath}/" data-toggle="tooltip" data-placement="left" title="Pagina Principal...">&nbsp;<span data-icon="&#xf015;" aria-hidden="true"></span>&nbsp;</a></li>
            <li><a href="#" data-toggle="tooltip" data-placement="bottom" title="Configurações do Sistema...">&nbsp;<span data-icon="&#xf0ad;" aria-hidden="true"></span>&nbsp;</a></li>
            <li><a href="#" data-toggle="tooltip" data-placement="bottom" title="Pefil do Usuário...">&nbsp;<span data-icon="&#xf007;" aria-hidden="true"></span>&nbsp;</a></li>
            <li><a href="#" data-toggle="tooltip" data-placement="bottom" title="Sobre o Sistema...">&nbsp;<span data-icon="&#xf05a;" aria-hidden="true"></span>&nbsp;</a></li>
            <li><a href="#" data-toggle="tooltip" data-placement="bottom" title="Ajuda sobre o sistema...">&nbsp;<span data-icon="&#xe040;" aria-hidden="true"></span>&nbsp;</a></li>
          </ul>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li><a href="${pageContext.request.contextPath}/aula/list/" data-toggle="tooltip" data-placement="bottom" title="Cadastro de Aulas..."><span data-icon="&#xe03c;" aria-hidden="true" ></span> Aulas</a></li>
            <li><a href="${pageContext.request.contextPath}/curso/list/" data-toggle="tooltip" data-placement="bottom" title="Cadastro de Cursos..."><span data-icon="&#xe025;" aria-hidden="true"></span> Cursos</a></li>
            <li><a href="${pageContext.request.contextPath}/professor/list/" data-toggle="tooltip" data-placement="bottom" title="Cadastro de Professores..."><span data-icon="&#xe07a;" aria-hidden="true"></span> Professores</a></li>
            <li><a href="${pageContext.request.contextPath}/aluno/list/"  data-toggle="tooltip" data-placement="bottom" title="Cadastro de Alunos..."><span data-icon="&#xe075;" aria-hidden="true"></span> Alunos</a></li>
            <li><a href="${pageContext.request.contextPath}/usuario/list"  data-toggle="tooltip" data-placement="bottom" title="Cadastro de Usuários..."><span data-icon="&#xf0c0;" aria-hidden="true"></span> Usuários</a></li>
          </ul>
          <hr/>
          <ul class="nav nav-sidebar">
            <li><a href="#" data-toggle="tooltip" data-placement="bottom" title="Configurações do Sistema..."><span data-icon="&#xf0ad;" aria-hidden="true"></span> Configurações</a></li>
            <li><a href="#" data-toggle="tooltip" data-placement="bottom" title="Sobre o Sistema..."><span data-icon="&#xf05a;" aria-hidden="true"></span> Sobre</a></li>
            <li><a href="#" data-toggle="tooltip" data-placement="bottom" title="Ajuda sobre o Sistema..."><span data-icon="&#xe040;" aria-hidden="true"></span> Ajuda</a></li>
          </ul>
          <hr/>
          <div class="text-center">
          	<img src="${pageContext.request.contextPath}/static/img/control-panel.png" width="50px" height="50px" alt="ControleServ Logo" />
          	<p class="text-info">Controle de Aulas <br/> versão: ${initParam.version}</p>
          </div>
        </div>
        
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        
         <section id="content_page">
          	<div style="width:100%; height:500px; overflow: auto;" class="table-responsive">
          	
          		<c:if test="${content_data != null && content_data != ''}">
				<div class="panel panel-default">
				  	<div class="panel-heading">
				  			<span data-icon="&#xe03c;" aria-hidden="true"></span>&nbsp; 
							${content_title}&nbsp;
				  			
				  			<c:if test="${list != null}">
								<a href="${pageContext.request.contextPath}${request_mapping_add}" class="btn btn-sm btn-default pull-right" 
									 data-toggle="tooltip" data-placement="bottom" title="Cadastrar um novo ${model_name}...">							
									&nbsp;&nbsp;<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;&nbsp;
								</a>
							</c:if>				  		
				  	</div>
				  	<div class="panel-body" style="width:100%; height:430px; overflow: auto;" class="table-responsive">
			            	<c:import url="${content_data}"></c:import>
				  	</div>
				</div>          	
	            </c:if>
          	
	        </div>            
	      
	        <section class="text-center" id="footer">
	          2015 <span class="glyphicon glyphicon-copyright-mark"></span>  Fabiano Góes by Codeforse.  |  versão: 0.1
	        </section>	      
	        
         </section>
	      
	      
        </div>
        
        
      </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/docs.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="${pageContext.request.contextPath}/static/js/ie10-viewport-bug-workaround.js"></script>
    <script type="text/javascript">
	    $(function () {
	    	  $('[data-toggle="tooltip"]').tooltip()
	    })
    </script>
</body>
</html>
