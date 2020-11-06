<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:if test="${list eq null}">
	<section id="usuario-form" class="well">
		
		<form action="${pageContext.request.contextPath}/usuario/save" method="post" id="id_form_usuario">
			<br>
			<div class="row">
	        	<div class="col-xs-1">
	        		<div class="form-group">
	        			<label for="id_usuario_id" class="text-info">ID</label>
	            		<input type="text" name="id" id="id_usuario_id" value="${model.id}" class="form-control text-center" readonly>
	            	</div>
	            </div>
	        	<div class="col-xs-5">
	        		<div class="form-group">
	        			<label for="id_usuario_login" class="text-info">Login</label>
	            		<input type="text" name="login" id="id_usuario_login" value="${model.login}" class="form-control text-center" required placeholder="Digite o nome do ${model_name} aqui...">
	            	</div>
	            </div>
				<div class="col-xs-6">
					<div class="form-group">
	        			<label for="id_usuario_senha" class="text-info">Senha</label>
						<input type="password" name="senha" id="id_usuario_senha" value="${model.senha}" class="form-control text-center" placeholder="Digite a Senha aqui...">
					</div>
				</div> 		            
	        </div>
			<br/>
			<a href="${pageContext.request.contextPath}/usuario/list" class="btn btn-default" 
				data-toggle="tooltip" data-placement="bottom" title="Cancelar Cadastro...">&nbsp;<span data-icon="&#xf00d;" aria-hidden="true"></span>&nbsp;</a>
			<button type="submit" class="btn btn-info pull-right" 
				data-toggle="tooltip" data-placement="bottom" title="Salvar novo Cadastro...">&nbsp;<span data-icon="&#xf0c7;" aria-hidden="true"></span>&nbsp;</button>
	    </form>
			    
	    
	    
	</section>
</c:if>

<c:if test="${list != null}">
	<section id="usuario-table">
		<table class="table table-striped table-condensed table-hover table-bordered">
		   	<thead>
		       	<tr>
		        	<th class="text-center">ID</th>
		            <th>Login</th>
			        <th>Senha</th>
		            <th class="text-center"> <span data-icon="&#xf085;" aria-hidden="true"></span> </th>
		        </tr>
		    </thead>
		      	<tbody>
		      		<c:if test="${list != null}">
		      			<c:forEach var="model" items="${list}">
				        	<tr>
					        	<td class="text-center">${model.id}</td>
					            <td>${model.login}</td>
						        <td>${model.senha}</td>
					            <td class="text-center">
					            	<a href="${pageContext.request.contextPath}/usuario/edit/${model.id}"
					            		data-toggle="tooltip" data-placement="left" title="Editar Registro...">
					            		<span class="text-info" data-icon="&#xf044;" aria-hidden="true" title="Editar Registro"></span>
					            	</a> 
					            	<a href="${pageContext.request.contextPath}/usuario/delete/${model.id}"
					            		data-toggle="tooltip" data-placement="bottom" title="Deletar Registro...">
					            		<span class="text-danger" data-icon="&#xf014;" aria-hidden="true" title="Deletar Registro"></span>
					            	</a>
					            </td>
				        	</tr>
				        </c:forEach>
			        </c:if>
		      	</tbody>
		</table>
	</section>
</c:if>