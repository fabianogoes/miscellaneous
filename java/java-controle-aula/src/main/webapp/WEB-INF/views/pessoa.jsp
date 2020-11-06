<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:if test="${list eq null}">
	<section id="pessoa-form" class="well">
		<form action="${pageContext.request.contextPath}/pessoa/save" method="post" id="id_form_pessoa">
			<br>			
				<div class="row">
					<div class="col-xs-3">
						<div class="form-group">
							<label for="id_pessoa_id" class="text-info">id</label>
							<input type="text" name="id" id="id_pessoa_id" value="${model.id}" class="form-control text-center" readonly="readonly">
						</div>
					</div>
					<div class="col-xs-3">
						<div class="form-group">
							<label for="id_pessoa_cpf" class="text-info">cpf</label>
							<input type="text" name="cpf" id="id_pessoa_cpf" value="${model.cpf}" class="form-control text-center">
						</div>
					</div>					
					<div class="col-xs-6"></div>
				</div>

				<div class="row">
					<div class="col-xs-12">
						<div class="form-group">
							<label for="id_pessoa_nome" class="text-info">nome</label>
							<input type="text" name="nome" id="id_pessoa_nome" value="${model.nome}" class="form-control text-center">
						</div>
					</div>
				</div>

				<input type="hidden" name="ativo" id="id_pessoa_aitov" value="S" class="form-control text-center">

			<br/>			<a href="${pageContext.request.contextPath}/pessoa/list" class="btn btn-default"				data-toggle="tooltip" data-placement="bottom" title="Cancelar Cadastro...">&nbsp;<span data-icon="&#xf00d;" aria-hidden="true"></span>&nbsp;</a>
			<button type="submit" class="btn btn-info pull-right"				data-toggle="tooltip" data-placement="bottom" title="Salvar novo Cadastro...">&nbsp;<span data-icon="&#xf0c7;" aria-hidden="true"></span>&nbsp;</button>
	    </form>
	</section>
</c:if><c:if test="${list != null}">
	<section id="pessoa-table">
		<table class="table table-striped table-condensed table-hover table-bordered">
		   	<thead>
		       	<tr>
					<th>id</th>
<th>nome</th>
<th>cpf</th>
<th>ativo</th>
<th class="text-center"> <span data-icon="&#xf085;" aria-hidden="true"></span></th>		        </tr>
		    </thead>
	      	<tbody>
   			<c:if test="${list != null}">
				<c:forEach var="model" items="${list}">
	        		<tr>
						<td>${model.id}</td>
<td>${model.nome}</td>
<td>${model.cpf}</td>
<td>${model.ativo}</td>
<td class="text-center">		<a href="${pageContext.request.contextPath}/pessoa/edit/${model.id}"     		data-toggle="tooltip" data-placement="left" title="Editar Registro...">    		<span class="text-info" data-icon="&#xf044;" aria-hidden="true" title="Editar Registro"></span>    	</a>    	<a href="${pageContext.request.contextPath}/pessoa/delete/${model.id}"     		data-toggle="tooltip" data-placement="bottom" title="Deletar Registro...">    		<span class="text-danger" data-icon="&#xf014;" aria-hidden="true" title="Deletar Registro"></span>    	</a></td>	        		</tr>
	        	</c:forEach>
        	</c:if>
      		</tbody>
		</table>
	</section>
</c:if>

