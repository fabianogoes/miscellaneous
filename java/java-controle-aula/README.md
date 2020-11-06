# Sistema para Controle de Aulas

Para facilitar o desenvolvimento de **CRUD**, foi criado dentro do próprio sistema um *micro Framework*,   
tudo referente ao *micro Framework* está dentro do *Package*: **br.com.controleaula.framework**:

* BaseModel
* BaseDAO
* BaseService
* BaseController
* GenerateView

O fluxo de desenvolvimento para um CRUD segue assim:
----------------------------------------------------

1. Criar uma classe Model herança de BaseModel, exemplo **Usuario**
2. Criar uma classe DAO herança de **BaseDAO**, exemplo **UsuarioDAO** e apenas anotar como: **@Repository("UsuarioDAO")**
2. Criar uma classe Service herança de **BaseService**, exemplo **UsuarioService** e apens anotar como: **@Service("UsuarioService")**
3. Criar uma classe Controller herança de **BaseController**, exemplo **UsuarioController** e anotar como: **@Controller e @RequestMapping("/usuario")**, criar um construtor passando por set() a Classe Model: **super.setClazz(Usuario.class);**
4. Criar uma View com o mesm nome da Model como lower case exemplo **usuario.jsp**

> Por convenção o Controller ira sempre redirecionar para uma View com o mesmo nome da Model com a escrita toda em Lower Case


Arquivos de Exemplo
-------------------

**Exemplo da classe UsuarioDAO:** [UsuarioDAO.java][0]

**Exemplo da classe Service:** [UsuarioService.java][1]

**Exemplo da Classe Controller:** [UsuarioController.java][2]

> Perceba que as classes DAO e Service não possui nenhuma implementação, apenas com as heranças a implementação do CRUD baseado na classe Model já será automaticamente criado pelo "micro framework", apenas a classe Controller deve receber uma pequena implementação, criando um Construtor que passe por set() qual a classe Model.

**Exemplo da View Usuario:** [usuario.jsp][3]

> Na view deve existir uma regra que quando no contexto a variavel "list" estiver nula significa que deve ser exibido apenas o Formulário de cadastro, e quando a varivael "list" não estiver nula significa que deve ser exibido a Table/GRID.

[>>> Ver DER do Sistema][7]

Como Contribuir
---------------

* Faça um fork desse repositório, clicando no botão [![Fork][4]][5], na parte superior direita da pagina do Github
* Clone seu fork:

    ``git clone --recursive https://github.com/SEU_USUARIO_DO_GITHUB/controleaula.git``

* Após criar ou editar seu artigo faça um pull-request para que sua implementação entre em produção.

Veja o video que explica o processo de fork, clone, push e pull-request : http://pythonclub.com.br/como-fazer-fork-clone-push-pull-request-no-github.html
 
Sincronizando seu fork
----------------------

Caso você já tenha feito fork a algum tempo você tem duas opções para garantir que
estará trabalhando com as ultimas alterações, que pode ser simplesmente deletar
seu fork e fazer um novo ou sincronizar seu fork com o repositório de origem
usando as [instruções contidas na wiki](https://gist.github.com/55ed9eed0664d2f90f9c.git)

License
-------
Licensed under an [MIT-style permissive license][6].

[0]: https://gist.github.com/fabianogoes/57a0da2a4c13eeca2866
[1]: https://gist.github.com/fabianogoes/03f4860ad4e44066890c
[2]: https://gist.github.com/fabianogoes/8a1896224f879914e725
[3]: https://gist.github.com/fabianogoes/546cca80763b8404484d
[4]: https://github.com/fabianogoes/controleaula/blob/master/src/main/webapp/WEB-INF/static/img/github-fork-btn.png
[5]: https://github.com/fabianogoes/controleaula/fork
[6]: https://github.com/fabianogoes/controleaula/blob/master/LICENSE
[7]: https://www.dropbox.com/s/lieab7wk2qcls3r/controleaula-der.pdf
