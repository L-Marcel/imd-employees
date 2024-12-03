# Atividade: SGBD para funcionários

Uma avaliação da disciplina de LP 2 que tem como objetivo desenvolver um gerenciador de funcionários de uma empresa fictícia com dados persistentes. 

# Etapas para execução no VSCode

Você precisará no mínimo do `Java JDK 21`. Porém, salve engano, estou utilizando o `Java JDK 23` na minha máquina principal. Além disso, no `VSCode` é importante ter as extensões principais do `Extension Pack for Java`.

Com as extensões instaladas, basta acessar pelo `VSCode` o arquivo `Main.java`, localizado na raíz do projeto, esperar as extensões carregarem completamente o projeto e clicar em `Run`, um botão que aparecerá acima do método `main`.

Houve também uma ocasião em um computador do `IMD` que precisei também adicionar a `flag` de execução `--enable-preview` a extensão que executa o código. Ele representa 1 dos 3 computadores em que testei o código.

# Documentação

## Páginas 

Você perceberá que existe uma pasta [`src/pages`](/src/pages/) que contém todas as simulações de telas para a interface do terminal. As classes desta pasta implementam a interface `Page`, que fornece um método para ser sobrescrito que repassa as instâncias dos objetos `Menu` e `Router`. Ambos são abstrações da biblioteca que desenvolvi, a [`JPretty`](https://github.com/L-Marcel/jpretty).

Toda a documentação do `JPretty` está disponível: https://jpretty.vercel.app/

Dito isso, não me aprofundarei mais sobre essa biblioteca aqui, mas procurei deixar o nome dos métodos o mais intuitível possível.

## Armazenamento

Coloquei na pasta [`src/storage`](/src/storage/) as classes responsáveis pelo armazenamento dos dados persistentes da aplicação. A principal é a [`Storage.java`](/src/storage/Storage.java), que na verdade se trata de uma `Thread` que recebe as solicitações de armazenamento dos dados, unifica as solicitações dentro de um pequeno intervalo de tempo e, por fim, guarda as versões mais recentes dos objetos que precisam ser armazenados.

A comunicação com o [`Storage.java`](/src/storage/Storage.java) é abstraída por meio das classes abstratas [`StorableList.java](/src/storage/StorableList.java) e [`StorableMap.java`]. Desenvolvimdas para o armazenamento do `LinkedList` e `LinkedHashMap`, respectivamente.

As classes [`src/persons/Disciplines.java`](/src/persons/Disciplines.java), [`src/persons/Employees.java](/src/persons/Employees.java) e [`src/persons/Persons.java`](/src/persons/Persons.java) herdam essas clases abstratas.

Falarei delas mais a frente.

## Modelos e objetos comuns

Na pasta [`src/persons/common`] guardei a classe [`Address.java`](src/persons/common/Address.java)

# Justificativas

Aqui deixarei as minhas justificativas de algumas decisões que tomei no desenvolvimento:

- Por que usar `Thread` para salvar os dados em segundo plano?
    - De fato, como se trata de um processo simples eu poderia só deixar para salvar os dados persistentes ao encerrar a aplicação (se o foco é performance) ou a cada alteração neles (se o foco é não perder os dados). Só que eu estou estutando `Threads`, e com uma `Thread` a perda de performance é relativamente pequena e consigo manter os dados atualizados sempre que necessário também (o bom dos dois mundos).
- Por que separa a interface gráfica em outra `lib`?
    - Não era a intenção inicial, mas eu achei que a parte gráfica ficou tão bem feita e generalizada que eu quis separar em uma biblioteca própria, que chamei de [`jpretty`](https://github.com/l-marcel/jpretty). Inicialmente era um submódulo, mas mudei de ideia.