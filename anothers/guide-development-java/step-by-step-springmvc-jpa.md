Step-by-Step - Spring MVC and JPA
=================================

*Step-by-Step for create and configuration a project Spring MVC with JPA and Hibernate*

**IDE:** STS - Spring Tool Suite  
**Java Version:** 1.7  
**Spring Version:** 3.1.1.RELEASE  

---------------------------------
1. Create Spring Project / Spring MVC Project, Name: **SpringJPA**  

2. modify the property **java-version** at [pom.xml](pom.xml) file for **1.7** and use the property in build block:     

3. right click at project and **Maven > Update Project...**  

4. Input dependencies JPA and Hibernate at [pom.xml](pom.xml) file:  

5. Configuration [servlet-context.xml](servlet-context.xml) file:  

6. Configuration [persistence.xml](persistence.xml) file:

7. Create model class [Person](Person.java) with JPA annottations:    

8. Create Generic class [GenericDAO](GenericDAO.java):    

9. Create class [PersonDAOImpl](PersonDAOImpl.java):    

10. Create class [PersonService](PersonService.java):  

11. Create class [PersonController](PersonController.java):  

12. Create View [person.jsp](person.jsp):  

