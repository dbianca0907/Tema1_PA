#### Dumitru Bianca-Andreea 322CA

# 1. Feribot

### _Implementare_

* Ideea de la care am plecat pentru implementarea solutiei a fost faptul ca sigur
cea mai mica valoare posibila a costului maxim trebuie sa fie un numar cuprins intre
valoarea maxima a greutatii unei masini si suma tuturor greutatilor masinilor.

* Plecand de la aceasta idee, am construit solutia prezentata astfel:
    * am sortat crescator o copie a listei greutatilor masinilor, pentru a determina
  cea mai grea masina
    * m-am folosit de algoritmul binary search pentru a gasi costul cerut
    * cu ajutorul functiei "num_intervals", am iterat prin lista de masini si am numarat
  de cate feriboturi este nevoie pentru a transporta masinile, pentru o gretutate maxima primita
    * in cazul in care numarul de feriboturi determinat anterior este mai mic decat numarul primit
  inseamna ca greutatea gasita este prea mare, deci miscoram intervalul de cautare al numarului

### _Complexitate_

* Functia "num_intervals()" are o complexitate de O(N), unde N este numarul de masini.
* In functia "fin_min_cost", functia "Collections.sort()" are O(N * logN), iar algoritmul
binary search are O(logN).


# 2. Nostory

### _Implementare_

#### *Task 1*
* Pentru taskul 1, m-am gandit ca avand un numar infinit de permutari intre numere, cel mai simplu
ar fi sa sa concatenez sirurile si sa le ordonez descrescator. Astfel, suma maxima este formata din
primele N numere, care sunt cele mai mari.

#### *Task 2*
* Pentru a ma asigura ca o sa calculez suma maxima, in cazul in care am un numar k de mutari, am facut
urmatoarele:
  * Am retinut intr-un vector "maxArr" cele mai mari valori din fiecare pereche, pe care l-am ordonat
  crscator.
  * Am stocat in vectorul "allNumbers" ambele siruri concatenate si ordonate crscator.
  * Urmatorul pas a constat in compararea celor mai mari numere din "allNumbers" cu numerele prezente in
  "maxArr", dupa urmatorul exemplu:
  ``` text
    - pentru: N = 5, moves = 2
              a[] = {3, 6, 10, 9, 5}
              b[] = {1, 8, 4, 7, 2}
    - voi avea: maxArr[] = {3, 5, 8, 9, 10}, iar allNumbers[] = {1, 2, 3, 4, ..., 10}
    - pornesc de la sfarsitul vectorului allNumbers si verific daca numarul se afla deja in maxArr,
    adica voi observa ca pana la numarul 7 sunt aceleasi pozitii maxime in ambii vectori
    - se inlocuieste numarul gasit cu minimul din vector, in cazul acesta maxArr[0], astfel vectorul
    devine maxArr[] = {7, 5, 8, 9, 10}
    - continui sa repet pasii acestia pana cand mi se termina numarul de mutari sau nu mai am ce permutari
    sa fac (j == cnt)
  ```
* Suma cautata va fi suma elementelor din maxArr[].

### _Complexitate_
* In cazul taskului 1, sortarea are o complexitate de O(N * log N), iar modul in care se construieste suma
O(N / 2), deci task-ul 1 are o complexitate de O(N * logN).
* In cazul taskului 2, la fel ca la taskul 1, sortarea are O(N * log N), iar cele 2 for-uri au O(N), deci
complexitatea este tot de O(N * logN).


## 3. Sushi
