# Thymeleaf Layout


* Criar a pasta **layout** dentro de **src/main/resources/**.   
* Criar o arquivo **LayoutBase.html** dentro da pasta **src/main/resources/layout**.   


> LayoutBase.html   

```html
<!DOCTYPE html>
<html lang="pt"
	xmlns="http://www.w3.org/1999/xhtml"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
	xmlns:th="http://www.thymeleaf.org">
  <head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    
    <meta name="description" content="Thymeleaf Layput Sample"/>
    <meta name="author" content="Fabiano Góes / e-Programar"/>
    <title>Thymeleaf Layout Base</title>
  </head>
<body>

    <section layout:fragment="header">
      <h1>Thymeleaf Layout Base</h1>
    </section >
        
    <section layout:fragment="content">
      <p>Content Page</p>	
    </section>
        
    <section layout:fragment="footer">
      <p>Footer Page</p>	
    </section>
        
  </body>
</html>
```   

* Criar o arquivo **index.html** dentro da pasta **src/main/resources/**.   

> index.html   

```html
<!DOCTYPE html>
<html lang="pt" xmlns="http://www.w3.org/1999/xhtml" 
	xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
	layout:decorator="layout/LayoutBase">	
<head>
	<title>Title Overrided</title>
</head>
<body>
	<section layout:fragment="content">
         <p>Content Overrided</p>     
	</section>
</body>
</html>	
```   

> IndexController.java   

```java
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {

	@RequestMapping
	public String index(){
		return "index";
	}
}
```   

