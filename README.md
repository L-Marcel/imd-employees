# IMD Employees

Um projeto avaliativo da disciplina de `LP 2` que tem como objetivo desenvolver um gerenciador de funcionários de uma empresa chamada `IMDCorp` com dados persistentes. Essa disciplina foi ofertada no `IMD` da `UFRN`.

# Sumário
- [1. Etapas para execução no VSCode](#1-etapas-para-execução-no-vscode);
- [2. Documentação](#2-documentação):
    - [2.1. Operações chaves](#21-operações-chaves);
    - [2.2. Páginas](#22-páginas);
    - [2.3. Armazenamento](#23-armazenamento);
    - [2.4. Entidades](#24-entidades):
        - [2.4.1 Disciplinas](#241-disciplinas).
    - [2.5. Classes de utilidades](#25-classes-de-utilidades);
    - [2.6. Logs](#26-logs);
    - [2.7. Gancho de encerramento](#27-gacho-de-encerramento);
- [3. Justificativas](#3-justificativas):
    - [3.1. Por que os métodos de remoção não removem pela matrícula?](#31-por-que-os-métodos-de-remoção-não-removem-pela-matrícula);
    - [3.2. Por que não usar um `set` ou `list` para armazenar as diciplinas?](#32-por-que-não-usar-um-set-ou-list-para-armazenar-as-disciplinas);
    - [3.3. Por que não há métodos para atualizar os funcionários?](#33-por-que-não-há-métodos-para-atualizar-os-funcionários);
    - [3.4. Por que usar `Thread` para salvar os dados em segundo plano?](#34-por-que-usar-thread-para-salvar-os-dados-em-segundo-plano);
    - [3.5 Por que separar a interface do terminal em outra biblioteca?](#35-por-que-separa-a-interface-do-terminal-em-outra-biblioteca).


# 1. Etapas para execução no VSCode

Você precisará no mínimo do `Java JDK 21`. Porém, salve engano, estou utilizando o `Java JDK 23` na minha máquina principal. Além disso, no `VSCode` é importante ter as extensões principais do `Extension Pack for Java`.

Com as extensões instaladas, basta acessar pelo `VSCode` o arquivo `Main.java`, localizado na raíz do projeto, esperar as extensões carregarem completamente o projeto e clicar em `Run`, um botão que aparecerá acima do método `main`.

Houve também uma ocasião em um computador do `IMD` que precisei também adicionar a `flag` de execução `--enable-preview` a extensão que executa o código. Ele representa 1 dos 3 computadores em que testei o código.

# 2. Documentação

Boa parte dos métodos e classes estão parcialmente ou completamente comentados, no geral usando a `syntax` do `javadoc` e divisões por `#region`, um recurso bastate útil do `VSCode`. Ainda assim, para facilitar o entendimento, e também sendo parte de uma das exigências da atividade, deixo abaixo seções documentando de forma sintetizada o funcionamento de cada classe da aplicação.

## 2.1. Operações chaves

Embora todas operações chaves, exceto a de calcular os salários, sejam combinações dos métodos abstraídos pelas classes do [`2.3. Armazenamento`](#23-armazenamento), resolvi as concentrar em uma só classe, a [`Employees.java`](src/persons/Employees.java). A única grande diferença do que foi solicitado é que os métodos de remoção não estão removendo por matrícula. Deixo a justicativa, se for de interesse, em [`3.1. Por que os métodos de remoção não removem pela matrícula?`](#31-por-que-os-métodos-de-remoção-não-removem-pela-matrícula).

## 2.2. Páginas 

Você perceberá que existe uma pasta [`src/pages`](src/pages/) que contém todas as simulações de telas para a interface do terminal. As classes desta pasta implementam a interface `Page`, que fornece um método para ser sobrescrito que repassa as instâncias dos objetos `Menu` e `Router`. Ambos são abstrações da biblioteca que desenvolvi, chamada [`JPretty`](https://github.com/L-Marcel/jpretty).

Vale destacar que o `JLine` e `JColor` estão presentes aqui como dependências do `JPretty`.

Toda a documentação do `JPretty` está disponível em: https://jpretty.vercel.app/

Dito isso, não me aprofundarei mais sobre essa biblioteca aqui, mas procurei deixar o nome dos métodos o mais intuitível possível.

## 2.3. Armazenamento

Coloquei na pasta [`src/storage`](src/storage/) as classes responsáveis pelo armazenamento dos dados persistentes da aplicação. A principal é a [`Storage.java`](src/storage/Storage.java), que na verdade se trata de uma `Thread` que recebe as solicitações de armazenamento dos dados, unifica as solicitações dentro de um pequeno intervalo de tempo e, por fim, guarda as versões mais recentes dos objetos que precisam ser armazenados.

A comunicação com o [`Storage.java`](src/storage/Storage.java) é abstraída por meio das classes abstratas [`StorableList.java`](src/storage/StorableList.java) e [`StorableMap.java`](src/storage/StorableMap.java). Desenvolvimdas para o armazenamento de `LinkedList` e `LinkedHashMap`, respectivamente.

As classes [`Disciplines.java`](src/persons/Disciplines.java), [`Employees.java`](src/persons/Employees.java) e [`Persons.java`](src/persons/Persons.java) herdam essas clases abstratas.

## 2.4. Entidades

Na pasta [`src/persons/models`](src/persons/models/) guardei as classes que representam as entidades fortes da aplicação, isto é, o [`Teacher.java`](src/persons/models/Teacher.java) e [`AdministrativeTechinician.java`](src/persons/models/AdministrativeTechnician.java).

Na pasta [`src/persons/enums`](src/persons/enums/) guardei o [`Genrer.java`](src/persons/enums/Genrer.java), [`Graduation.java`](src/persons/enums/Graduation.java) e o [`Level.java`](src/persons/enums/Level.java), isto é, os `enums` utilizados pelas entidades [`Teacher.java`](src/persons/models/Teacher.java) e [`AdministrativeTechinician.java`](src/persons/models/AdministrativeTechnician.java).

Na pasta [`src/persons/common`](src/persons/common/) guardei a classe [`Address.java`](src/persons/common/Address.java), já que ela é uma entidade fraca e é armazenada com um atributo de [`Person.java`](src/persons/Persons.java), que é a classe abstrata pai de [`Teacher.java`](src/persons/models/Teacher.java) e [`AdministrativeTechinician.java`](src/persons/models/AdministrativeTechnician.java) e também é a classe referência para o armazenamento abstraído por [`StorableList.java`](src/storage/StorableList.java) utilizado em [`Persons.java`](src/persons/Persons.java).

E, sendo uma exigência da atividade, deixei nessa pasta também a interface [`Employee.java`](src/persons/common/Employee.java). Que contém alguns métodos que são implementados em [`Teacher.java`](src/persons/models/Teacher.java) e [`AdministrativeTechinician.java`](src/persons/models/AdministrativeTechnician.java).

### 2.4.1 Disciplinas

A classe [`Disciplines.java`](src/persons/Disciplines.java) faz uso da abstração do [`StorableMap.java`](src/storage/StorableMap.java) para armazenar um `Map` cujas chaves são `Strings` (o nome da disciplina) e o valor são `Integers` (o número de professores que lecionam essa disciplina).

Mas, efetivamente, o que a entidade [`Teacher.java`](src/persons/models/Teacher.java) armazena é uma lista de professores.

## 2.5. Classes de utilidades

Na pasta [`src/utils`](src/utils/) deixei uma série bem grande de classes para formatação e validação de dados, todas com nomes autosugestivos.

## 2.6. Logs

Na pasta [`src/logs`](src/log/) deixei uma única classe, a [`Log.java`](src/log/Log.java) que é uma `Thread` que tem como único objetivo armazenar em segundo plano os `logs` da aplicação em [`debug.log`](data/debug.log), como o nome sugere.

Nota: os `logs` ficaram mais bonitos se você estiver com a extensão [`Log File Highlighter`](https://marketplace.visualstudio.com/items?itemName=emilast.LogFileHighlighter) instalada.

## 2.7. Gacho de encerramento

A classe [`ShutdownHook.java`](src/ShutdownHook.java) é uma `Thread` que será executada quando a aplicação for encerrada, mesmo que através de um `Ctrl + C`. Como també está sendo feito uso de `Threads` para armazenar os dados e os `logs` da aplicação, é de suma importância que se a aplicação for encerrada, os dados que estão em fila para serem salvos sejam salvos. Este é o papel desssa classe.

# 3. Justificativas

Aqui deixarei as minhas justificativas de algumas decisões que tomei no desenvolvimento.

## 3.1 Por que os métodos de remoção não removem pela matrícula?

Embora a atividade inicialmente peça para que seja assim, como eu optei por adicionar
uma confirmação de execlusão, isso seria mais custoso. O que acontece é:

- O usuário digita a matrícula;
- É verificado se algum professor ou técnico administrativo faz uso dela, se sim, a instância do funcionário é retornada. Portanto o custo até aqui `O(n)`;
- O usuário confirma se quer ou não remover o funcinário;
- Se realmente quiser, o funcionário é removido pela instância. Como o custo para remover pela instância em uma lista ligada é `O(n)`, até aqui temos `O(2n)`, que não deixa de ser `O(n)`.

Caso realmente a remoção fosse feita pela matrícula, o que aconteceria é:

- O usuário digita a matrícula;
- É verificado se algum professor ou técnico administrativo faz uso dela. Portanto o custo até aqui `O(n)`;
- O usuário confirma se quer ou não remover o funcinário;
- Se realmente quiser, é feita uma busca da instância ou índice do funcionário na lista. Portanto o custo até aqui é `O(2n)`;
- Depois o funcionário é removido pela instância ou índice. Como o custo para remover pela instância ou índice em uma lista ligada é `O(n)`, até aqui temos `O(3n)`, que não deixa de ser `O(n)`, mas é pior que `O(2n)`.

## 3.2. Por que não usar um `Set` ou `List` para armazenar as disciplinas?

Existem alguns pontos da aplicação que se faz necessário percorrer as disciplinas dos professores e essa relação é de `n` para `n`. Porém, na atividade a disciplina não é bem uma entidade, apenas uma `String` qualquer e no uso mais frequente que fiz dela eu estava interessado apenas em saber se ela existe ou não na aplicação. Logo optei por uma estrutura de dados que tem essa operação como constante, falo do `Map`;

Por que não usar um `Set`? Bom, suponha que você removou um professor que leciona a disciplina de `LP II`, porém outro professor também leciona essa disciplina, logo seria preciso verificar se outro professor tem essa discplina antes de remové-la de um `Set`, o que teria custo quadrático.

## 3.3. Por que não há métodos para atualizar os funcionários?

Basicamente, não é uma exigência da avaliação. Mas, convenhamos, o código já ficou relativamente grande sem isso.

## 3.4. Por que usar `Thread` para salvar os dados em segundo plano?

De fato, como se trata de um processo simples eu poderia só deixar para salvar os dados persistentes ao encerrar a aplicação (se o foco é performance) ou a cada alteração neles (se o foco é não perder os dados). Só que eu estou estutando `Threads`, e com uma `Thread` a perda de performance é relativamente pequena e consigo manter os dados atualizados sempre que necessário também (o bom dos dois mundos).

## 3.5. Por que separa a interface do terminal em outra biblioteca?

Não era a intenção inicial, mas eu achei que a parte da interface ficou tão bem feita e generalizada que eu quis separar em uma biblioteca própria, que chamei de [`jpretty`](https://github.com/l-marcel/jpretty). Inicialmente era um submódulo, mas mudei de ideia.