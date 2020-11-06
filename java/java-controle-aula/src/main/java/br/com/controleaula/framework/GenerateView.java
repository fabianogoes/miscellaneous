package br.com.controleaula.framework;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

public class GenerateView {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o Nome da Model:\t");
        String className = scanner.nextLine(); 
        scanner.close();
		
		GenerateView generateView = new GenerateView();

		// Se a classe não for encontrada no package model aborto		
		if ( generateView.existClassInPackageModel(className) == false ){
			System.out.println( "Classe [ "+className+" ] não localizada no package: [ "+generateView.getPackageModelName()+" ]"  );
			return;
		}
		
		String formInputs = generateView.getInputs(className);
		String tableHead = generateView.getTableHead(className);
		String tableColumns = generateView.getTableColumns(className);
		
		String template = generateView.getTemplateModelView(className);
		template = template.replace("$form_inputs$", formInputs);
		template = template.replace("$table_head$", tableHead);
		template = template.replace("$table_columns$", tableColumns);
		
		String current = new java.io.File( "." ).getCanonicalPath();
		String dirViews = current + "/src/main/webapp/WEB-INF/views/";
                
        String fileViewName = dirViews+generateView.getClassSimpleName(className).toLowerCase()+".jsp";
		FileWriter fw = new FileWriter( fileViewName );
		PrintWriter pw = new PrintWriter( fw );
		pw.println( template );
		pw.flush();
		pw.close();
		
		System.out.println( "*****************************************************************************************" );
		System.out.println( "View criado com sucesso em: "+fileViewName );
		System.out.println( "*****************************************************************************************" );
	}

	
	public String getInputs(String className) throws ClassNotFoundException{
		StringBuilder sbInputs = new StringBuilder();

		// Se a classe não for encontrada no package model aborto		
		if ( this.existClassInPackageModel(className) == false ){
			System.out.println( "Classe [ "+className+" ] não localizada no package: [ "+this.getPackageModelName()+" ]"  );
			return null;
		}
		
				
		Field[] fields = this.getField(className);  
		for (Field field : fields) {
			if ( java.lang.reflect.Modifier.isStatic(field.getModifiers()) == false ) {
						
				// crio a label
				String idComponente = "id_"+this.getClassSimpleName(className).toLowerCase()+"_"+field.getName().toLowerCase();
				String labelComponenteName = field.getName();
				String label = String.format( "<label for=\"%s\" class=\"text-info\">%s</label>", idComponente, labelComponenteName );				

				String input = String.format( "<input type=\"text\" name=\"%s\" id=\"%s\" value=\"${model.%s}\" class=\"form-control text-center\">", 
						field.getName().toLowerCase(), idComponente, field.getName().toLowerCase());
				
				// monto o componente
				sbInputs.append( "<div class=\"row\">\n" );
				sbInputs.append( "\t<div class=\"col-xs-1\"></div>\n" );
				sbInputs.append( "\t<div class=\"col-xs-10\">\n" );
				sbInputs.append( "\t\t<div class=\"form-group\">\n" );
				sbInputs.append( "\t\t\t"+label+"\n" );
				sbInputs.append( "\t\t\t"+input+"\n" );
				sbInputs.append( "\t\t</div>\n" );
				sbInputs.append( "\t</div>\n" );
				sbInputs.append( "\t<div class=\"col-xs-1\"></div>\n" );
				sbInputs.append( "</div>\n\n" );
						
			}
		}
		return sbInputs.toString();
	}

	
	public String getTableHead(String className) throws ClassNotFoundException{
		StringBuilder sbTableHead = new StringBuilder();

		Field[] fields = this.getField(className); 
		for (Field field : fields) {
			if ( java.lang.reflect.Modifier.isStatic(field.getModifiers()) == false ) {
				sbTableHead.append( String.format( "<th>%s</th>\n", field.getName()) );
			}
		}
        sbTableHead.append( "<th class=\"text-center\"> <span data-icon=\"&#xf085;\" aria-hidden=\"true\"></span></th>" );
		return sbTableHead.toString();
	}	
	
	public String getTableColumns(String className) throws ClassNotFoundException{
		StringBuilder sbColumns = new StringBuilder();

		Field[] fields = this.getField(className); 
		for (Field field : fields) {
			if ( java.lang.reflect.Modifier.isStatic(field.getModifiers()) == false ) {
				sbColumns.append( String.format( "<td>${model.%s}</td>\n", field.getName() ) );
			}
		}
		
		String simpleName = this.getClassSimpleName(className);
		
		sbColumns.append( "<td class=\"text-center\">" );
		sbColumns.append( String.format("		<a href=\"${pageContext.request.contextPath}/%s/edit/${model.id}\" ", simpleName.toLowerCase() ));
		sbColumns.append( "    		data-toggle=\"tooltip\" data-placement=\"left\" title=\"Editar Registro...\">" );
		sbColumns.append( "    		<span class=\"text-info\" data-icon=\"&#xf044;\" aria-hidden=\"true\" title=\"Editar Registro\"></span>" );
		sbColumns.append( "    	</a>" );
		sbColumns.append( String.format("    	<a href=\"${pageContext.request.contextPath}/%s/delete/${model.id}\" ", simpleName.toLowerCase() ));
		sbColumns.append( "    		data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Deletar Registro...\">" );
		sbColumns.append( "    		<span class=\"text-danger\" data-icon=\"&#xf014;\" aria-hidden=\"true\" title=\"Deletar Registro\"></span>" );
		sbColumns.append( "    	</a>" );
		sbColumns.append( "</td>" );
        
		return sbColumns.toString();
	}	
	
	public Class<?> getClass( String className ) throws ClassNotFoundException{
		List<Class<?>> classesFromPackage = this.getClassesFromPackage( this.getPackageModelName() );
		for (Class<?> clazz : classesFromPackage) {
			if( className.equalsIgnoreCase( clazz.getSimpleName() ) || className.equalsIgnoreCase( clazz.getName() ) ){
				return clazz;
			}
		}
		return null;
	}
	
	public List<Class<?>> getClassesFromPackage( String packageName ) throws ClassNotFoundException{
		List<Class<?>> classesPackage = new ArrayList<Class<?>>();
		
		ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
		provider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*")));
		
		String packageModel = this.getThisPackage().replace("framework", "model");
		
		Set<BeanDefinition> classes = provider.findCandidateComponents( packageModel );
		for (BeanDefinition beanDefinition : classes) {
			Class<?> clazz = Class.forName(beanDefinition.getBeanClassName());
			classesPackage.add( clazz );
		}
		
		return classesPackage;
	}
	
	public Field[] getField( String className ) throws SecurityException, ClassNotFoundException{
		Class<?> clazz = this.getClass(className);
		Field[] fields = clazz.getDeclaredFields();
		return fields;
	}
	
	public String getClassSimpleName( String className ) throws ClassNotFoundException{
		return this.getClass(className).getSimpleName();
	}
	
	public String getPackageModelName(){
		return this.getThisPackage().replace("framework", "model");
	}
	
	public boolean existClassInPackageModel( String className ) throws ClassNotFoundException{
		List<Class<?>> classesFromPackage = this.getClassesFromPackage( this.getPackageModelName() );
		for (Class<?> clazz : classesFromPackage) {
			if( className.equalsIgnoreCase( clazz.getSimpleName() ) || className.equalsIgnoreCase( clazz.getName() ) ){
				return true;
			}
		}
		return false;
	}
	
	public String getThisPackage(){
		return this.getClass().getPackage().getName(); 
	}
	
	public String getTemplateModelView(String className) throws ClassNotFoundException{
		StringBuilder sb = new StringBuilder(); 
		sb.append( "<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\" %>\n" );
		sb.append( "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>\n" );
		
		sb.append( "<c:if test=\"${list eq null}\">\n" );
		sb.append( String.format("	<section id=\"%s-form\" class=\"well\">\n", this.getClass(className).getSimpleName().toLowerCase()) );
		sb.append( "		<form action=\"${pageContext.request.contextPath}/"+this.getClass(className).getSimpleName().toLowerCase()+"/save\" method=\"post\" id=\"id_form_"+this.getClass(className).getSimpleName().toLowerCase()+"\">\n" );
		sb.append( "			<br>" );
		sb.append( "			$form_inputs$" );
		sb.append( "			<br/>" );
		sb.append( "			<a href=\"${pageContext.request.contextPath}/"+this.getClass(className).getSimpleName().toLowerCase()+"/list\" class=\"btn btn-default\"");
		sb.append( "				data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Cancelar Cadastro...\">&nbsp;<span data-icon=\"&#xf00d;\" aria-hidden=\"true\"></span>&nbsp;</a>\n" );
		sb.append( "			<button type=\"submit\" class=\"btn btn-info pull-right\"" );
		sb.append( "				data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Salvar novo Cadastro...\">&nbsp;<span data-icon=\"&#xf0c7;\" aria-hidden=\"true\"></span>&nbsp;</button>\n" );
		sb.append( "	    </form>\n" );
		sb.append( "	</section>\n" );
		sb.append( "</c:if>");

		sb.append( "<c:if test=\"${list != null}\">\n" );
		sb.append( String.format("	<section id=\"%s-table\">\n", this.getClass(className).getSimpleName().toLowerCase()) );
		sb.append( "		<table class=\"table table-striped table-condensed table-hover table-bordered\">\n" );
		sb.append( "		   	<thead>\n" );
		sb.append( "		       	<tr>\n" );
		sb.append( "					$table_head$" );
		sb.append( "		        </tr>\n" );
		sb.append( "		    </thead>\n" );
		sb.append( "	      	<tbody>\n" );
		sb.append( "   			<c:if test=\"${list != null}\">\n" );
		sb.append( "				<c:forEach var=\"model\" items=\"${list}\">\n" );
		sb.append( "	        		<tr>\n" );
		sb.append( "						$table_columns$" );
		sb.append( "	        		</tr>\n" );
		sb.append( "	        	</c:forEach>\n" );
		sb.append( "        	</c:if>\n" );
		sb.append( "      		</tbody>\n" );
		sb.append( "		</table>\n" );
		sb.append( "	</section>\n" );
		sb.append( "</c:if>\n" );		
		return sb.toString();
	}
}
