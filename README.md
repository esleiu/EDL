# Guia prático — **DEQUE** com SLL (`nosl`) e DLL (`nodl`) — passo a passo

> Formato didático: operações (a), (b), (c)… com **trecho real do código** que executa e o **estado final** após cada operação.
>
> Inclui a definição dos **nós `nosl` e `nodl`**, classes `DequeSL` (lista simplesmente encadeada) e `DequeDL` (lista duplamente encadeada), e uma tabela de complexidades.

---

## 0) Nós que estruturam as listas

```java
// nosl — nó de lista simplesmente encadeada (SLL)
class nosl {
    private Object elemento;
    private nosl next;

    nosl(Object elemento) { this.elemento = elemento; }

    Object getelemento() { return elemento; }
    void setelemento(Object elemento) { this.elemento = elemento; }
    nosl getnext() { return next; }
    void setnext(nosl next) { this.next = next; }
}
```

```java
// nodl — nó de lista duplamente encadeada (DLL)
class nodl {
    private Object elemento;
    private nodl next;
    private nodl prev;

    nodl(Object elemento) { this.elemento = elemento; }

    Object getelemento() { return elemento; }
    void setelemento(Object elemento) { this.elemento = elemento; }
    nodl getnext() { return next; }
    void setnext(nodl next) { this.next = next; }
    nodl getprev() { return prev; }
    void setprev(nodl prev) { this.prev = prev; }
}
```

Exceção útil para operações inválidas:
```java
class edequevazio extends RuntimeException {
    public edequevazio(String msg) { super(msg); }
}
```

---

## 1) `DequeSL` — Deque com **lista simplesmente encadeada (SLL)**

### Classe (núcleo)
```java
class DequeSL {
    private nosl inicio, fim;
    private int tamanho;

    public boolean estavazia() { return tamanho == 0; }
    public int tamanho() { return tamanho; }

    public void inseririnicio(Object elemento) {
        nosl novo = new nosl(elemento);
        if (estavazia()) {
            inicio = fim = novo;
        } else {
            novo.setnext(inicio);
            inicio = novo;
        }
        tamanho++;
    }

    public void inserirfim(Object elemento) {
        nosl novo = new nosl(elemento);
        if (estavazia()) {
            inicio = fim = novo;
        } else {
            fim.setnext(novo);
            fim = novo;
        }
        tamanho++;
    }

    public Object removerinicio() {
        if (estavazia()) throw new edequevazio("deque vazio");
        Object e = inicio.getelemento();
        inicio = inicio.getnext();
        if (inicio == null) fim = null;
        tamanho--;
        return e;
    }

    // O(n): precisa achar o penúltimo nó
    public Object removerfim() {
        if (estavazia()) throw new edequevazio("deque vazio");
        Object e = fim.getelemento();
        if (tamanho == 1) {
            inicio = fim = null;
        } else {
            nosl cur = inicio;
            while (cur.getnext() != fim) cur = cur.getnext();
            cur.setnext(null);
            fim = cur;
        }
        tamanho--;
        return e;
    }

    public Object primeiro() {
        if (estavazia()) throw new edequevazio("deque vazio");
        return inicio.getelemento();
    }

    public Object ultimo() {
        if (estavazia()) throw new edequevazio("deque vazio");
        return fim.getelemento();
    }
}
```

### Estado inicial
```
inicio = null, fim = null, tamanho = 0
```

### (a) `inseririnicio("B")`
**trecho que executa**
```java
nosl novo = new nosl("B");
if (estavazia()) { inicio = fim = novo; }
tamanho++;
```
**estado:** `inicio → ["B"] → null ; fim=["B"] ; tamanho=1`

### (b) `inserirfim("C")`
**trecho que executa**
```java
nosl novo = new nosl("C");
fim.setnext(novo);
fim = novo;
tamanho++;
```
**estado:** `["B"] → ["C"] → null ; inicio=["B"] ; fim=["C"] ; tamanho=2`

### (c) `inseririnicio("A")`
**trecho que executa**
```java
nosl novo = new nosl("A");
novo.setnext(inicio);
inicio = novo;
tamanho++;
```
**estado:** `["A"] → ["B"] → ["C"] → null ; fim=["C"] ; tamanho=3`

### (d) `removerfim()` ← **O(n)**
**trecho que executa**
```java
Object e = fim.getelemento();
nosl cur = inicio;
while (cur.getnext() != fim) cur = cur.getnext();
cur.setnext(null);
fim = cur;
tamanho--;
```
**estado:** `["A"] → ["B"] → null ; inicio=["A"] ; fim=["B"] ; tamanho=2`

### (e) `removerinicio()`
**trecho que executa**
```java
Object e = inicio.getelemento();
inicio = inicio.getnext();
if (inicio == null) fim = null;
tamanho--;
```
**estado:** `["B"] → null ; inicio=fim=["B"] ; tamanho=1`

**Por que `removerfim` é O(n)?** Sem `prev`, a SLL precisa **encontrar o penúltimo** nó com um laço `while`.

---

## 2) `DequeDL` — Deque com **lista duplamente encadeada (DLL)**

### Classe (núcleo)
```java
class DequeDL {
    private nodl inicio, fim;
    private int tamanho;

    public boolean estavazia() { return tamanho == 0; }
    public int tamanho() { return tamanho; }

    public void inseririnicio(Object elemento) {
        nodl novo = new nodl(elemento);
        if (estavazia()) {
            inicio = fim = novo;
        } else {
            novo.setnext(inicio);
            inicio.setprev(novo);
            inicio = novo;
        }
        tamanho++;
    }

    public void inserirfim(Object elemento) {
        nodl novo = new nodl(elemento);
        if (estavazia()) {
            inicio = fim = novo;
        } else {
            novo.setprev(fim);
            fim.setnext(novo);
            fim = novo;
        }
        tamanho++;
    }

    public Object removerinicio() {
        if (estavazia()) throw new edequevazio("deque vazio");
        Object e = inicio.getelemento();
        inicio = inicio.getnext();
        if (inicio != null) inicio.setprev(null); else fim = null;
        tamanho--;
        return e;
    }

    public Object removerfim() {
        if (estavazia()) throw new edequevazio("deque vazio");
        Object e = fim.getelemento();
        fim = fim.getprev();
        if (fim != null) fim.setnext(null); else inicio = null;
        tamanho--;
        return e;
    }

    public Object primeiro() {
        if (estavazia()) throw new edequevazio("deque vazio");
        return inicio.getelemento();
    }

    public Object ultimo() {
        if (estavazia()) throw new edequevazio("deque vazio");
        return fim.getelemento();
    }
}
```

### Estado inicial
```
inicio = null, fim = null, tamanho = 0
```

### (a) `inseririnicio("B")`
**trecho que executa**
```java
nodl novo = new nodl("B");
if (estavazia()) { inicio = fim = novo; } 
else { novo.setnext(inicio); inicio.setprev(novo); inicio = novo; }
tamanho++;
```
**estado:** `null ← ["B"] → null ; inicio=fim=["B"] ; tamanho=1`

### (b) `inserirfim("C")`
**trecho que executa**
```java
nodl novo = new nodl("C");
novo.setprev(fim);
fim.setnext(novo);
fim = novo;
tamanho++;
```
**estado:** `["B"] ⇄ ["C"] ; inicio=["B"] ; fim=["C"] ; tamanho=2`

### (c) `inseririnicio("A")`
**trecho que executa**
```java
nodl novo = new nodl("A");
novo.setnext(inicio);
inicio.setprev(novo);
inicio = novo;
tamanho++;
```
**estado:** `["A"] ⇄ ["B"] ⇄ ["C"] ; inicio=["A"] ; fim=["C"] ; tamanho=3`

### (d) `removerfim()` ← **O(1)**
**trecho que executa**
```java
Object e = fim.getelemento();
fim = fim.getprev();
if (fim != null) fim.setnext(null); else inicio = null;
tamanho--;
```
**estado:** `["A"] ⇄ ["B"] ; inicio=["A"] ; fim=["B"] ; tamanho=2`

### (e) `removerinicio()` ← **O(1)**
**trecho que executa**
```java
Object e = inicio.getelemento();
inicio = inicio.getnext();
if (inicio != null) inicio.setprev(null); else fim = null;
tamanho--;
```
**estado:** `["B"] ; inicio=fim=["B"] ; tamanho=1`

**Por que tudo é O(1)?** Com `prev` **e** `next`, a DLL **não precisa percorrer** para cortar/inserir nas pontas.

---

## 3) Tabela de complexidades

| Operação              | DequeDL (DLL) | DequeSL (SLL) | Motivo |
|---|---:|---:|---|
| `inseririnicio`       | O(1) | O(1) | liga/desliga ponteiros da cabeça |
| `removerinicio`       | O(1) | O(1) | acesso direto ao início |
| `inserirfim`          | O(1) | O(1) | ponteiro `fim` é mantido |
| `removerfim`          | **O(1)** | **O(n)** | DLL usa `prev`; SLL precisa achar penúltimo |
| `primeiro` / `ultimo` | O(1) | O(1) | ponteiros diretos |
| `tamanho/estavazia`   | O(1) | O(1) | contador mantido |

---

## 4) Dicas de teste rápido

```java
DequeSL sll = new DequeSL();
sll.inseririnicio("B");
sll.inserirfim("C");
sll.inseririnicio("A");   // A B C
System.out.println(sll.removerfim());   // C (O(n))
System.out.println(sll.removerinicio()); // A

DequeDL dll = new DequeDL();
dll.inseririnicio("B");
dll.inserirfim("C");
dll.inseririnicio("A");   // A B C
System.out.println(dll.removerfim());   // C (O(1))
System.out.println(dll.removerinicio()); // A
``
# EDL[guia_duas_pilhas_e_nos.md](https://github.com/user-attachments/files/23055366/guia_duas_pilhas_e_nos.md)
# Guia prático — **Duas Pilhas em um único array** + **nós `nosl` e `nodl`**

> Formato didático: operações (a), (b), (c)… com **trecho real do código** que executa e o **estado final** após cada operação.
>
> Este guia está alinhado ao seu código `duaspilhas` enviado (com `CAP_MIN = 12`, duplicação ao encostar e redução para metade quando o uso total ≤ `cap/3`).

---

## 0) Nós de lista — `nosl` (SLL) e `nodl` (DLL)

Para os deques, os nós que você pediu para explicitar ficam assim (com *getters/setters* usados no seu pseudo/código didático):

```java
// nosl — nó de lista simplesmente encadeada (SLL)
class nosl {
    private Object elemento;
    private nosl next;

    nosl(Object elemento) { this.elemento = elemento; }

    Object getelemento() { return elemento; }
    void setelemento(Object elemento) { this.elemento = elemento; }
    nosl getnext() { return next; }
    void setnext(nosl next) { this.next = next; }
}
```

```java
// nodl — nó de lista duplamente encadeada (DLL)
class nodl {
    private Object elemento;
    private nodl next;
    private nodl prev;

    nodl(Object elemento) { this.elemento = elemento; }

    Object getelemento() { return elemento; }
    void setelemento(Object elemento) { this.elemento = elemento; }
    nodl getnext() { return next; }
    void setnext(nodl next) { this.next = next; }
    nodl getprev() { return prev; }
    void setprev(nodl prev) { this.prev = prev; }
}
```

- **SLL (`nosl`)**: só tem `next` ⇒ operações na ponta do *fim* podem precisar percorrer a lista (ex.: `removerfim()` é O(n)).  
- **DLL (`nodl`)**: tem `next` e `prev` ⇒ cortar/inserir nas pontas é O(1) (acesso direto ao anterior e ao próximo).

---

## 1) Duas pilhas no mesmo array — visão geral

**Ideia:** um único vetor `lista` de capacidade `cap`.  
- **Pilha vermelha** cresce da **esquerda → direita**. Índice do topo: `topover` (inicia em `-1`).  
- **Pilha preta** cresce da **direita → esquerda**. Índice do topo: `topopre` (inicia em `cap`).  
- **Redimensionamento** via `verredim()`:
  - **Duplica** (`cap * 2`) quando **encostam**: `topover + 1 == topopre`.
  - **Reduz** (`cap / 2`) quando **pouco uso**: `tamtotal() <= cap/3` **e** `cap > CAP_MIN`.
- Invariantes:
  - Elementos válidos da **vermelha** ocupam `lista[0..topover]`.
  - Elementos válidos da **preta** ocupam `lista[topopre..cap-1]`.
  - **Nunca** há sobreposição: sempre `topover < topopre`.

### Assinatura principal

```java
public class duaspilhas {
    private static final int CAP_MIN = 12;
    private Object[] lista;
    private int topover; // -1 no início
    private int topopre; // cap no início
    private int cap;

    public duaspilhas(int capinicial) {
        if (capinicial < 2) throw new IllegalArgumentException("capacidade inicial deve ser no minimo 2");
        this.cap = capinicial;
        this.lista = new Object[this.cap];
        this.topover = -1;
        this.topopre = this.cap;
    }
    // ... métodos abaixo ...
}
```

---

## 2) Operações e estados — passo a passo

### Estado inicial (ex.: `cap = 12`)
```
lista   = [ _ _ _ _ _ _ _ _ _ _ _ _ ]
topover = -1        // vermelha vazia (cresce 0→+)
topopre = 12        // preta vazia (cresce 11→-)
cap     = 12
```

### (a) `pushver("A")`
**trecho que executa**
```java
public void pushver(Object elem) {
    verredim();          // há espaço; não redimensiona
    lista[++topover] = elem;  // -1 -> 0
}
```
**estado:**  
```
[ A _ _ _ _ _ _ _ _ _ _ ] ; topover=0 ; topopre=12 ; tamver=1 ; tampre=0
```

### (b) `pushpre(1)`
**trecho que executa**
```java
public void pushpre(Object elem) {
    verredim();          // há espaço; não redimensiona
    lista[--topopre] = elem; // 12 -> 11
}
```
**estado:**  
```
[ A _ _ _ _ _ _ _ _ _ _ 1 ] ; topover=0 ; topopre=11 ; tamver=1 ; tampre=1
```

### (c) `pushver("B")`
```java
verredim();
lista[++topover] = "B";   // 0 -> 1
```
**estado:**  
```
[ A B _ _ _ _ _ _ _ _ _ 1 ] ; topover=1 ; topopre=11
```

### (d) `poppre()` → retorna `1`
```java
public Object poppre() {
    if (vaziapre()) throw new pilhavaziaexcecao("a pilha preta esta vazia");
    Object elem = lista[topopre];
    lista[topopre] = null;
    topopre++;            // 11 -> 12
    verredim();           // checa redução
    return elem;
}
```
**estado:**  
```
[ A B _ _ _ _ _ _ _ _ _ _ ] ; topover=1 ; topopre=12 ; tampre=0
```

### (e) `popver()` → retorna `"B"`
```java
public Object popver() {
    if (vaziaver()) throw new pilhavaziaexcecao("a pilha vermelha esta vazia");
    Object elem = lista[topover];
    lista[topover] = null;
    topover--;            // 1 -> 0
    verredim();           // checa redução
    return elem;
}
```
**estado:**  
```
[ A _ _ _ _ _ _ _ _ _ _ _ ] ; topover=0 ; topopre=12 ; tamver=1
```

---

## 3) **Quando fica “cheio”** (e duplica)?

A condição de “cheio” é quando **o próximo `push` encostaria** as pilhas:  
```java
if (topover + 1 == topopre) {
    redim(cap * 2);  // DUPLICA
}
```

### O que `redim` faz na duplicação

```java
private void redim(int novacap) {
    Object[] novalista = new Object[novacap];

    // 1) Copia a pilha vermelha para o começo (0..topover)
    for (int i = 0; i <= topover; i++) {
        novalista[i] = lista[i];
    }

    // 2) Copia a pilha preta colando à direita da NOVA capacidade
    int novoidxpre = novacap;
    for (int i = cap - 1; i >= topopre; i--) { // varre os elementos pretos antigos
        novoidxpre--;                          // anda de trás para frente na nova lista
        novalista[novoidxpre] = lista[i];     // preserva a ordem da pilha preta
    }

    // 3) Atualiza os campos
    this.lista = novalista;
    this.cap = novacap;
    this.topopre = novoidxpre; // topo da preta aponta para o novo bloco da direita
}
```

**Intuição visual (antes `cap=12`, depois `novacap=24`):**
```
ANTES:   [ V V V _ _ _ _ _ _ _ P P ]    (topover=2, topopre=10)

DEPOIS:  [ V V V _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ P P ]
           ^ vermelha copiada 0..2                           ^ preta recomposta à direita
           topover=2                                         topopre=22 (por ex.)
```

> A pilha **vermelha** mantém índices baixos; a pilha **preta** é “reancorada” no fim do novo vetor, preservando a ordem de topos.

---

## 4) **Quando reduz?** (capacidade / 2)

Após `pop` (ou mesmo `push`), `verredim()` também verifica **pouco uso**:  
```java
else if (tamtotal() <= cap / 3 && cap > CAP_MIN) {
    redim(cap / 2);  // REDUZ (até o mínimo)
}
```
- **Gatilho:** soma de elementos das duas pilhas `<= cap/3`.  
- **Barreira:** **nunca** reduz abaixo de `CAP_MIN` (12 no teu código).  
- **Por quê?** Para equilibrar memória e desempenho: reduzir demais aumenta a chance de duplicar logo em seguida.

**Redução reposiciona os elementos** com a *mesma lógica* da duplicação:  
- vermelha continua colada à esquerda (`0..topover`),  
- preta é reancorada no final da **nova** capacidade (`topopre` atualizado).

---

## 5) Complexidade e invariantes

- `pushver`, `pushpre`, `popver`, `poppre` ⇒ **O(1) amortizado** (apenas movem índices e tocam 1 célula).  
- `redim` (duplicar/reduzir) ⇒ **O(n)**, mas acontece ocasionalmente.  
- Consultas (`topver`, `toppre`, `vaziaver`, `vaziapre`, `tamver`, `tampre`, `tamtotal`, `getcap`) ⇒ **O(1)**.

**Invariantes cruciais:**
- `-1 ≤ topover < topopre ≤ cap`  
- elementos válidos **não se sobrepõem**.  
- após `redim`, a **ordem dos elementos de cada pilha é preservada**.

---

## 6) Erros e utilitários

A classe usa exceções ao tentar `pop`/`top` de pilha vazia. Ex.:

```java
class pilhavaziaexcecao extends RuntimeException {
    pilhavaziaexcecao(String msg) { super(msg); }
}
```

`toString()` e `listar()` ajudam na depuração/visualização:

```java
@Override
public String toString() {
    return "duaspilhas [cap=" + cap +
            ", ver=" + tamver() +
            ", pre=" + tampre() +
            ", total=" + tamtotal() + "]";
}

public void listar() {
    for (int i = 0; i < cap; i++) {
        System.out.print(lista[i] != null ? lista[i] + " " : " - ");
    }
    System.out.println();
}
```

---

## 7) Mini-roteiro de teste (sugestão)

```java
duaspilhas pilhas = new duaspilhas(12);
pilhas.pushver("A");     // [A _ _ ...]
pilhas.pushver("B");     // [A B _ ...]
pilhas.pushpre(1);       // [... 1]
pilhas.pushpre(2);       // [... 2 1]
pilhas.listar();
System.out.println(pilhas);   // ver cap/ver/pre/total

System.out.println(pilhas.poppre()); // 2
System.out.println(pilhas.popver()); // B
pilhas.listar();
System.out.println(pilhas);
```

Isso permite ver a movimentação de `topover/topopre` e quando a redução dispara.

---

### Conclusões rápidas
- **Cheio**: quando `topover + 1 == topopre` ⇒ duplica e reancora a pilha preta no fim.  
- **Pouco uso**: quando `tamtotal() <= cap/3` e `cap > CAP_MIN` ⇒ reduz pela metade.  
- **Amortizado O(1)** para operações usuais; `redim` é raro.

---

**Pronto!** Este arquivo está pronto para ser usado no seu material. Se quiser, posso complementar com diagramas ASCII para um caso de **duplicação real** (com índices antes/depois) usando a sua sequência de operações.
