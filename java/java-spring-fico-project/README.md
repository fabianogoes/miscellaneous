# Fico

[![Run Status](https://api.shippable.com/projects/577d64723be4f4faa56bfb60/badge?branch=master)](https://app.shippable.com/projects/577d64723be4f4faa56bfb60)

| Title            | Fico                       |
|------------------|----------------------------|
| **Description**  | Financial Control System   |
| **Author:**      | [Fabiano Góes][23]         |
| **Date:**        | Saturday, July 01, 2016    |
| **Locale:**      | Guarulhos, SP, Brasil      |

![Fico][4]

### Setup Development  

### Database
```sql
CREATE DATABASE fico CHARACTER SET utf8 COLLATE utf8_bin;

use FIco;

create table users (username varchar(50) not null primary key, password varchar(255) not null,    enabled boolean not null) engine = InnoDb;create table authorities (    username varchar(50) not null,    authority varchar(50) not null,    foreign key (username) references users (username),    unique index authorities_idx_1 (username, authority)) engine = InnoDb;
```   

1. Run App   

2. Populate Data Basic   
```sql
insert into category(name) values('Alimentação');
insert into category(name) values('Lazer');
insert into category(name) values('Moradia');
insert into category(name) values('Salário');
insert into category(name) values('Saúde');
insert into category(name) values('Transporte');
insert into category(name) values('Combustível');
insert into category(name) values('Outros');

insert into bank(code, name) values('001', 'Banco do Brasil');
insert into bank(code, name) values('033', 'Banco Santander');
insert into bank(code, name) values('104', 'Caixa Econômica Federal');
insert into bank(code, name) values('237', 'Bradesco');
insert into bank(code, name) values('341', 'Itaú');
insert into bank(code, name) values('399', 'HSBC');
```   


Libraries / Tools
---------
* [Java][21]
* [SpringBoot][15]
* [Spring IoC][16]
* [Spring Data][17]
* [Spring Security][18]
* [JPA][19]
* [Hibernate][20]
* [HTML][22]
* [Thymeleaf][14]
* [MySQL][13]
* [Apache Tomcat][12]
* [Apache Maven][11]
* [Twitter Bootstrap][9]
* [Font-Awesome][10]
* [JQuery][7]
* [JQueryUI][8]
* [AngularJS][6]
* [SweetAlert][5]
* [Shippable CI][24]

License
-------
[![MIT][0]][1]   
Licensed under an [MIT-style permissive license][0].   

Open Source
-----------
[![Open Source][2]][3]  

[0]: https://raw.githubusercontent.com/fabianogoes/Fico/master/src/main/resources/static/img/mit-license.png
[1]: https://raw.githubusercontent.com/fabianogoes/Fico/master/LICENSE
[2]: https://raw.githubusercontent.com/fabianogoes/Fico/master/src/main/resources/static/img/opensource-iniciative.png
[3]: https://en.wikipedia.org/wiki/Open_Source_Initiative
[4]: https://raw.githubusercontent.com/fabianogoes/Fico/master/src/main/resources/static/img/print-dashboard.png
[5]: http://t4t5.github.io/sweetalert/
[6]: https://angularjs.org/
[7]: http://jquery.com/
[8]: https://jqueryui.com/
[9]: http://getbootstrap.com/
[10]: http://fontawesome.io/
[11]: http://maven.apache.org/
[12]: http://tomcat.apache.org/
[13]: https://www.mysql.com/
[14]: http://www.thymeleaf.org/
[15]: http://projects.spring.io/spring-boot/
[16]: http://docs.spring.io/spring/docs/current/spring-framework-reference/html/beans.html
[17]: http://projects.spring.io/spring-data/
[18]: http://projects.spring.io/spring-security/
[19]: https://pt.wikipedia.org/wiki/Java_Persistence_API
[20]: http://hibernate.org/
[21]: http://www.oracle.com/technetwork/pt/java/javase/downloads/index.html
[22]: https://pt.wikipedia.org/wiki/HTML
[23]: http://fabianogoes.github.io/
[24]: shippable.com
