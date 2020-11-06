Step-by-Step - SpringMVC and JNDI
=================================

Step-by-Step for create and configuration a project Spring MVC with JNDI Pool Connection

- Create Spring Project / Spring MVC Project, Name: **SpringJNDI**
- Modify the property **java-version** at pom.xml file for **1.7** and use the property in build block:
- Right click at project and Maven > **Update Project...**
 Create file [context.xml](context.xml) at directory **webapp/META-INF/**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Context path="/SpringJNDI" docBase="SpringJNDI">
 
    <Resource name="jdbc/SpringJNDI_Pool"
              global="jdbc/SpringJNDI_Pool"
              auth="Container"
              type="javax.sql.DataSource"
              driverClassName="com.mysql.jdbc.Driver"
              url="jdbc:mysql://192.9.200.20:3306/test"
              username="root"
              password="root"
               
              maxActive="100"
              maxIdle="20"
              minIdle="5"
              maxWait="10000"/>                  
</Context>
```

- Modify **web.xml** file and add code:
```xml
<resource-ref>
    <res-ref-name>jdbc/SpringJNDI_Pool</res-ref-name>
    <res-type>javax.sql.DataSource</res-type>
    <res-auth>Container</res-auth>
</resource-ref> 
```  

- Add bean DataSource at file **servlet-context.xml**  
```xml
<!-- Create DataSource Bean -->
<beans:bean id="dbDataSource" class="org.springframework.jndi.JndiObjectFactoryBean">
        <beans:property name="jndiName" value="java:comp/env/jdbc/SpringJNDI_Pool" />
</beans:bean>	
```  
 
- Inject DataSource at class DAO:
```java
@Repository("exemploDAO")
public class ExemploDAO {

	@Autowired
	private DataSource dsExemplo;

	public void setDsExemplo(DataSource dsExemplo) {
		this.dsExemplo = dsExemplo;
	}
	
}
```  

- Add dependencies at file **pom.xml**  
```xml
<!-- Dependencies JNDI -->
<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
	<version>5.1.31</version>
</dependency>	
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-jdbc</artifactId>
	<version>${org.springframework-version}</version>
</dependency>
<dependency>
	<groupId>commons-dbcp</groupId>
	<artifactId>commons-dbcp</artifactId>
	<version>1.2.2</version>
</dependency>	
```

- Example of the use:  

**class DAO:** ExemploDAO.java 
```java
package com.eprogramar.springjndi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.eprogramar.springjndi.model.Pessoa;

@Repository("exemploDAO")
public class ExemploDAO {

	@Autowired
	@Qualifier("dbDataSource")
	private DataSource dsExemplo;

	public void setDsExemplo(DataSource dsExemplo) {
		this.dsExemplo = dsExemplo;
	}
	
	public List<Pessoa> getPessoas() throws SQLException{
		List<Pessoa> lista = new ArrayList<Pessoa>();
		PreparedStatement pstm = this.dsExemplo.getConnection().prepareStatement("select * from Pessoa");
		ResultSet rs = pstm.executeQuery();
		while(rs.next()){
			lista.add( new Pessoa(rs.getInt(1), rs.getString(2)) );
		}
		return lista;
	}
	
}
```

**class Service:** ExemploService.Java  
```java
package com.eprogramar.springjndi.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.eprogramar.springjndi.dao.ExemploDAO;
import com.eprogramar.springjndi.model.Pessoa;

@Service("exemploService")
public class ExemploService {

	@Autowired
	@Qualifier("exemploDAO")
	private ExemploDAO exemploDAO;

	public void setExemploDAO(ExemploDAO exemploDAO) {
		this.exemploDAO = exemploDAO;
	}
	
	public List<Pessoa> getPessoas() throws SQLException{
		return this.exemploDAO.getPessoas();
	}
	
}
```

**class Controller:** ExemploController.java  
```java
package com.eprogramar.springjndi;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eprogramar.springjndi.service.ExemploService;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	@Qualifier("exemploService")
	private ExemploService exemploService;
	
	public void setExemploDAO(ExemploService exemploService) {
		this.exemploService = exemploService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() throws SQLException {
		logger.info("use example...");
		System.out.println( this.exemploService.getPessoas() );
		return "home";
	}
	
}
```  
