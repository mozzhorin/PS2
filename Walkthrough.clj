;; gorilla-repl.fileformat = 1

;; **
;;; # Gorilla REPL
;;; 
;;; Welcome to gorilla :-)
;;; 
;;; Shift + enter evaluates code. Hit alt+g twice in quick succession or click the menu icon (upper-right corner) for more commands ...
;;; 
;;; It's a good habit to run each worksheet in its own namespace: feel free to use the declaration we've provided below if you'd like.
;; **

;; @@
(ns clojure-walkthrough
  (:require [clojure.repl :refer :all]))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; # Clojure Walkthrough
;; **

;; **
;;; ## Clojure
;;; 
;;; Clojure ist ein Lisp-Dialekt für die Java-Virtual-Machine (JVM):
;;; 
;;; * Clojure ist ein Lisp-Dialekt mit einem starken Fokus auf funktionale Programmierung
;;; * Clojure ist eine kompilierte Sprache, benötigt für die Ausführung allerdings die JVM. Dadurch muss es sich zwar einerseits an einige Einschränkungen/Konventionen halten, z.B. den **classpath**, gleichzeitig hat man aber auch Zugriff auf zahlreiche Java-Bibliotheken und Clojure nutzt diesen Vorteil, indem es eine direkte Interaktion mit Java erlaubt.
;;; 
;;; Clojure wurde 2007 von Rich Hickey entwickelt und ist mittlerweile in Version 1.8 verfuegbar.
;; **

;; **
;;; Clojure-Projekte lassen sich einfach erstellen und verwalten ueber **Leiningen**:
;;; 
;;; * Installieren Sie dazu zunaechst Leiningen https://leiningen.org/
;;; * Erstellen Sie dann ein neues Projekt: `lein new app ps2`
;;; 
;;;   Dies erstellt ein Verzeichnis *ps2* sowie verschiedene Unterverzeichnisse
;;; * Editieren Sie nun die Datei *profile.clj* und fuegen Sie die folgenden *:dependencies* und *:plugins* ein:
;;; 
;;; `  :dependencies [[org.clojure/clojure "1.8.0"] [org.clojure/core.async "0.2.391"] [seesaw "1.4.5"] [criterium "0.4.4"] [nightlight "1.6.1"]]`
;;; 
;;; `  :plugins [[cider/cider-nrepl "0.13.0"] [lein-gorilla "0.4.0"] [nightlight/lein-nightlight "1.6.1"]]`
;; **

;; **
;;; ### REPL
;;; 
;;; Der REPL (Read-Eval-Print Loop) erlaubt es Clojure-Code interaktiv auszufuehren. Sie starten einen REPL wie folgt:
;;; 
;;; * Wechseln Sie in das Verzeichnis Ihres Projektes *ps2*
;;; * Starten Sie dort *lein repl*
;;; 
;;; Alternativ koennen Sie auch einen *notebook* Viewer mittels `lein gorilla` starten oder den interierten *Nightlight* Editor mittels `lein nightlight`
;;; 
;;; Im REPL koennen Dokumentationen abgefragt und gesucht werden:
;; **

;; @@
(doc println)

(apropos "print")
;; @@

;; **
;;; #### Hallo, Welt!
;; **

;; @@
"Hallo, Welt!"
;; @@

;; **
;;; Sie haben soeben Ihr erstes Clojure-Programm geschrieben!
;;; 
;;; Beispiele fuer weitere einfache Programme ...
;; **

;; @@
(+ 1 2)
(println "Tada")
(reverse "Hallo, Welt!")
(+ 1 (* 2 3))
;; @@

;; **
;;; ### Syntax
;;; 
;;; Clojure ist ein Lisp-Dialekt mit einer einheitlichen Syntax. Jeder *Ausdruck* hat die Form
;;; * `<expr>` oder
;;; * `( <fn-expr> <expr1> ... <expr> )`
;;; 
;;; Die obigen Beispiele sind von dieser Form:
;;; * `"Hallo, Welt!"` als Ausdruck fuer einen String 
;;; * Funktion `+` mit den Ausdruecken 1 und 2 als Argumente
;;; * Funktion `println` mit Argument `"Tada"`
;;; * etc.
;;; 
;;; ### Semantik
;;; 
;;; Clojure unterscheidet drei Phasen der Auswertung:
;;; * Read-time: Einlesen und parsen des Quellcode
;;; * Compile-time: Uebersetzen des Programms
;;; 
;;;   Hier werden u.a. Makros expandiert
;;; * Run-time: Ausfuehren des Programms
;;; 
;;;   Hierbei werden zunaechst alle Argumente ausgewertet (von links nach rechts) und die resultierenden Werte dann an die Funktion uebergeben
;; **

;; **
;;; ### Uebung:
;;; 
;;; Uebersetze die folgenden arithmetischen Ausdruecke in Clojure-Syntax und fuehre sie aus:
;;; * `(1+2*3 - (5+6)/(7-8))*2`
;;; * `12^2/(4-2)-9^2`
;; **

;; @@
(* 2 (- (+ 1 (* 2 3)) (/ (+ 5 6) (- 7 8))))
(- (/ (* 12 12) (- 4 2)) (* 9 9))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-long'>-9</span>","value":"-9"}
;; <=

;; **
;;; ### Datentypen
;;; 
;;; Clojure kennt viele verschiedene Datentypen:
;;; 
;;; | Datentyp | Kurz-Syntax | Funktion |
;;; |----------|-------------|----------|
;;; | Char     | \a \tab     | `(char <ASCII code>)` |
;;; | String   | "Hallo"     | `(str \H \a \l \l \o)` |
;;; | Number   | 123 4.56    |          |
;;; | Symbol   | 'a 'Huhu    | `(symbol "Huhu")` |
;;; | Keyword  | :a :Huhu    | `(keyword "Huhu")` |
;;; | List     |             | `(list 1 2 :a)` |
;;; | Vector   | [1 2 :a]    | `(vector 1 2 :a)` |
;;; | Set      | #{1 2 :a}   | `(hash-set 1 2 :a)` |
;;; | Map      | {:a 1 "b" 2} | `(hash-map :a 1 "b" 2)` |
;;; | Function | #(+ % 1)    | `(fn [x] (+ x 1))`
;;; 
;;; Clojure-Code (zur Compile-time) ist ebenfalls als Datenstruktur dargestellt:
;;; * `(+ 1 2)` ==> `(list '+ 1 2)`
;;; * `(println "Tada")` ==> `(list 'println "Tada")`
;;; Clojure ist case-sensitiv, d.h. die Symbole `println` und `PrintLn` sind verschieden.
;;; 
;;; Mittels der speziellen Form `(quote <expr>)` oder in Kurz-Syntax `'<expr>` laesst sich die Auswertung verhindern:
;;; * Wir haben die Verwendung der Quote `'` schon bei der Eingabe von Symbolen gesehen
;;; * Einige Datentypen, wie z.B. String oder Number, sind selbstauswertend, d.h. `(quote 1)` ist gleich `1`
;;; 
;;; Da Clojure-Code als Liste eingelesen wird, koennen wir die Liste `(list 1 2 "Hallo")` auch eingeben als `'(1 2 "Hallo")`
;;; 
;;; Achtung: Beachte den Unterschied zwischen `(list '+ 1 2)` und `'('+ 1 2)`!
;;; 
;;; ### Uebung:
;;; 
;;; Geben Sie die obigen "Programme" `(+ 1 2)` bis `(+ 1 (* 2 3))` als Clojure Datenstruktur ein und fuehren Sie dann
;;; mittels `eval` aus.
;; **

;; @@
(eval '(+ 1 2))
(eval '(+ 1 (* 2 3)))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-long'>7</span>","value":"7"}
;; <=

;; **
;;; 
;;; ### Variablen und Funktionen
;;; 
;;; Bisher haben wir nur *Werte* verschiedener Datentypen erzeugt. 
;;; Um denselben Wert mehrfach verwenden zu koennen, sollten wir diesem einen *Namen* geben:
;;; 
;;; In Clojure werden (globale) Namen durch die spezielle Form `def` vergeben.
;; **

;; @@
(def x 10)

(+ x 10)
(* x x)
;; @@

;; **
;;; Achtung: `def` sollte nicht als Zuweisung verstanden und benutzt werden!
;;; * `def` steht fuer *Definition* und fuehrt einen neuen Namen ein
;;; * Schlechter Stil: `(def x (+ x 1))`
;;; 
;;;   Clojure erlaubt auch Zuweisungen, aber nur in kontrollierter Form (siehe spaeter: Nebenlaeufigkeit)
;;; 
;;; Auf die gleiche Weise werden auch Funktionen definiert:
;; **

;; @@
(def my-inc (fn [x] (+ x 1)))

(my-inc x)

(defn my-inc [x]
  (+ x 1))
;; @@

;; **
;;; Die spezielle Form `defn` erlaubt eine etwas kompaktere Definition von Funktionen:
;;; * `(defn <name> [<arg1> ... <argn>] <body>)` entspricht im Wesentlichen `(def <name> (fn [<arg1> ... <argn>] <body>))`
;;; * `defn` erzeugt zusaetzliche Meta-Information, etwa zur Dokumentation, und drueckt syntaktisch klarer aus, das eine Funktion definiert wurde
;;; 
;;; In Clojure hat jeder Ausdruck einen Wert:
;;; * Der Rueckgabewert einer Funktion entspricht einfach dem Wert des letzten Ausdrucks in der Funktionsdefinition.
;;; * Sollen mehrere Ausdruecke sequentiell ausgefuehrt werden, so kann die spezielle Form `do` verwendet werden:
;; **

;; @@
(do (println "Hallo") (+ 1 2) (+ 2 3))
;; @@

;; **
;;; `do` gibt dabei nur den Wert des letzten Ausdrucks zurueck. Vorherige Ausdruecke sind nur sichtbar durch ihre *Seiteneffekte*, 
;;; z.B. indem sie etwas auf dem Bildschirm ausgeben.
;; **

;; **
;;; ### Uebung:
;;; 
;;; * Definiere eine Funktion `quadrat`, die `x^2` berechnet
;;; * Uebersetze `12^2/(4-2)-9^2` in Clojure-Syntax unter Verwendung von `quadrat`
;; **

;; @@
(defn quadrat [x] (* x x))
(- (/ (quadrat 12) (- 4 2)) (quadrat 9))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-long'>-9</span>","value":"-9"}
;; <=

;; **
;;; ### Bindungen
;;; Wir haben schon zwei Formen von Bindungen kennengelernt:
;;; * `def` zur Definition globaler Namen
;;; * `(fn [<arg1> ... <argn>] <body>)` fuehrt neue Namen fuer die Argumente ein, welche innerhalb der Funktion verwendet werden koennen
;;; 
;;; Die spezielle Form `(let [<var1> <expr1> ... <varn> <exprn>] <body>)` fuehrt lokale Bindungen ein:
;; **

;; @@
(let [i 10]
  (+ i 2))
  
(let [i 10
      j 20]
  (println i)
  (let [i 25]
    (println i))
  (+ i j))
;; @@

;; **
;;; `let` ist wiederum keine Zuweisung, sondern fuehrt Namen ein die innerhalb des `let`-Blocks verfuegbar sind.
;;; Wird dabei ein schon vorhandener Name "ueberschrieben" so verdeckt dieser lediglich die schon vorhandene Bindung. 
;;; Durch verlassen des `let`-Blocks wird dieser dann wieder sichtbar (siehe Beispiel).
;; **

;; **
;;; ### Bedingungen
;;; 
;;; Clojure ermoeglicht bedingte Auswertungen durch
;;; * `(if <test-expr> <then-expr> <else-expr>)`
;;; * `(cond <test1> <expr1> ... <testn> <exprn>)`
;;; `<test>` bezeichnet dabei einen Ausdruck, der einen Wahrheitswert hat. In Clojure wird *falsch* durch den Boolean `false` 
;;; oder die leere Liste `nil` dargestellt. Alle anderen Werte gelten als `true`.
;; **

;; @@
(if (< 1 2) :a :b)

(cond (> x 0) :pos
      (< x 0) :neg
    :otherwise :zero)
;; @@

;; **
;;; ### Schleifen
;;; 
;;; Die einfachste Form, um Schleifen aehnlich wie in anderen Programmiersprachen auszudruecken, ist loop.
;;; 
;;; Damit koennen wir z.B. die Summe der Zahlen `1` bis `n` wie folgt berechnen:
;; **

;; @@
(defn sum-to-n [n]
  (loop [i 0, sum 0] ;; Initialisierung
    (if (> i n)
      sum
     (recur (+ i 1) (+ sum i))))) ;; Werte (von i und sum) fuer naechste Iteration

(sum-to-n 10)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-long'>55</span>","value":"55"}
;; <=

;; **
;;; 
;;; Schleifen werden in Clojure meist indirekt ausgedrueckt, ueber *Rekursion* oder mittels *Sequenzen*
;;; 
;;; #### Rekursion
;;; 
;;; Funktionen koennen sich rekursiv aufrufen, wie z.B. von der Fakultaetsfunktion bekannt. 
;;; * Tail-call: Tritt der rekursive Aufruf als sogenannter *tail-call* auf, d.h. der Wert des rekursiven Aufrufs ist gleichzeitig auch der Rueckgabewert der Funktion,
;;;   so kann Clojure diesen Aufruf optimieren, wenn statt des Funktionsnamens `recur` verwendet wird (vergleiche `loop`)
;;; 
;;; #### Sequenzen
;;; 
;;; Containerdatentypen, z.B. Listen, Vektoren oder auch Maps, koennen als (lazy) Sequenzen betrachtet werden. Diese lassen sich dann mit Hilfe
;;; von Funktionen hoeherer Ordnung oder `for`-Comprehensions verarbeiten:
;;; * `(seq <coll>)` wandelt sein Argument in eine Sequenz um
;;; * `(first <seq>)` extrahiert das erste Element der Sequenz
;;; * `(rest <seq>)` verkuerzt die Sequenz um das erste Element. `rest` der leeren Sequenz gibt `nil` zurueck.
;;; * `(cons <expr> <seq>)` verlaengert die Sequenz um ein neues erstes Element
;; **

;; @@
(seq [1 2 3])

(first [1 2 3]) ;; implizit wie (first (seq [1 2 3]))

(rest [1 2 3])
;; @@

;; **
;;; Viele Funktionen hoeherer Ordnung sind in Clojure verfuegbar, um Sequenzen zu durchsuchen, transformieren etc.
;;; 
;;; | Funktion | Beschreibung |
;;; |----------|--------------|
;;; | `(take n seq)` | Behalte nur die ersten n Elemente der Sequenz |
;;; | `(drop n seq)` | Entferne die ersten n Elemente der Sequenz |
;;; | `(map f seq)` | Wendet f auf jedes Element der Sequenz an |
;;; | `(filter pred seq)` | Behalte nur die Elemente `x`, fuer die `(pred x)` wahr ist |
;;; | `(remove pred seq)` | Wie `(filter (complement pred) seq)` |
;;; | `(reduce f val seq)` | Kombiniere alle Elemente der Sequenz mit f, z.B. `(reduce + [1 2 3])`. Der Startwert val ist optional |
;; **

;; @@
(map (fn [x] (* x x)) [1 2 3])

(filter even? [1 2 3 4])

(reduce + (range (inc 10))) ;; Clojure's Antwort auf sum-to-n
;; @@

;; **
;;; 
;;; `for`-Comprehensions sind syntaktischer Zucker, um Sequenzen einfacher erzeugen zu koennen.
;;; 
;;; Vergleiche
;; **

;; @@
(for [i (range 10)
      :when (even? i)
      k [10 20]]
  (+ i k))
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-lazy-seq'>(</span>","close":"<span class='clj-lazy-seq'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>10</span>","value":"10"},{"type":"html","content":"<span class='clj-long'>20</span>","value":"20"},{"type":"html","content":"<span class='clj-long'>12</span>","value":"12"},{"type":"html","content":"<span class='clj-long'>22</span>","value":"22"},{"type":"html","content":"<span class='clj-long'>14</span>","value":"14"},{"type":"html","content":"<span class='clj-long'>24</span>","value":"24"},{"type":"html","content":"<span class='clj-long'>16</span>","value":"16"},{"type":"html","content":"<span class='clj-long'>26</span>","value":"26"},{"type":"html","content":"<span class='clj-long'>18</span>","value":"18"},{"type":"html","content":"<span class='clj-long'>28</span>","value":"28"}],"value":"(10 20 12 22 14 24 16 26 18 28)"}
;; <=

;; **
;;; mit dem mathematischen Asudruck:
;;; 
;;; $$ \\{ i + k | i \in \\{0, ..., 9\\}, i \; \mathtt{even}, k \in \\{10,20\\} \\} $$
;; **

;; **
;;; ### Uebung
;;; * Implementiere die Fakultaetsfunktion, rekursiv und mittels `loop`
;;; * Schreibe die obige `for`-Comprehension mittels `map, filter` und `mapcat`
;;; * Implementiere `reverse` mittels `reduce`
;;; * Uebersetze die folgende Schleife aus Python nach Clojure
;; **

;; @@
;; max_so_far = -1000 # Bad practice!
;; for x in [1,3,2,6,5,4,1]:
;;   if (x%2 == 1):
;;     max_so_far = max(max_so_far, x*x)
;; return max_so_far
;; @@

;; **
;;; Verwende dabei
;;; * einmal `reduce` oder
;;; * eine Kombination aus `map, filter` und `reduce`
;; **

;; @@
(defn factorial [n]
  (loop [i n, prod 1] 
    (if (< i 1)
      prod
     (recur (- i 1) (* prod i)))))

(factorial 5)


(defn ! [n]
  (if (< n 2)
    1
    (* n (! (dec n)))))

(! 5)


(mapcat (fn [i]
       		(map (partial + i) [10 20]))
     	(filter even? (range 10)))


(reduce (fn [so-far x]
          (if (odd? x)
            (max so-far (* x x))
            so-far))
        -1000
        [1 3 2 6 5 4 1])


(->> [1 3 2 6 5 4 1]
     (filter odd?)
     (map (fn [x] (* x x)))
     (reduce max))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-long'>25</span>","value":"25"}
;; <=

;; **
;;; 
;; **

;; @@
(def even-nats
  (filter even? (range)))
;; @@

;; **
;;; `(range)` erzeugt (formal) eine unendliche Sequenz. Wir koennen nun gezielt einen Teil davon anfordern und ausfuehren:
;; **

;; @@
(doall (take 10 even-nats))
;; @@

;; **
;;; Folgende Funktionen veranlassen die Auswertung von lazy Sequenzen:
;;; 
;;; | | Behalte Resultate | Nur Seiteneffekte (kein Resultat) |
;;; |-|------------------|-----------------------------------|
;;; | Einzelne Sequenz | `doall` | `dorun` |
;;; | Mehrere Sequenzen (`for`-Comprehension Syntax) | N/A | `doseq` |
;;; 
;; **

;; **
;;; ### Immutability
;;; Datenstrukturen in Clojure sind *immutable*, also unveraenderbar. Funktionen, 
;;; die auf Datenstrukturen operieren veraendern diese demnach nicht,
;;; sondern geben eine neue geaenderte Version zurueck:
;; **

;; @@
(def v [1 2 3 4])

(rest v)

v

(conj v 4)

v
;; @@

;; **
;;; Die folgende Tabelle gibt einen (unvollstaendigen) Ueberblick:
;;; 
;;; |           | List/Lazy seq | Vector | Set | Map |
;;; |-----------|------|--------|-----|-----|
;;; | Test      | `(some #(= % x) l)` | `(contains? v i)` | `(contains? s x)` | `(contains? m k)` |
;;; | Zugriff   | `(nth l i)` | `(get v i)` | `(get s x)` | `(get m k)` |
;;; | Anhaengen | `(cons x l)` | `(conj v x)` | `(conj s x)` | `(conj m [k v])` |
;;; | Entfernen | N/A | N/A | `(disj s x)` | `(dissoc m k)`
;;; | Aendern   | N/A | `(assoc v i x)` | N/A | `(assoc m k v)`
;;; |           | N/A | `(update v i f)` | N/A | `(update m k f)`
;;; 
;;; Praktisch ist auch, dass Vektoren, Sets und Maps, als Funktionen betrachtet werden koennen:
;;; * `(v i)` wie `(get v i)`
;;; * `(s x)` wie `(get s x)`
;;; * `(m k)` wie `(get m k)`
;;; 
;;; Zusaetzlich fungieren auch Keywords, da sie haeufig als Keys von Maps eingesetzt werden, als Funktionen, die
;;; sich selbst in einer Map nachschauen. Dies ist vor allem bei geschachtelten Maps (Objekten) praktisch:
;; **

;; @@
(def person
  {:name "Peter"
   :address {:street "Ganglienweg"
             :city   "Metropolis"}})
       
(:name person)

(:city (:address person))

(update-in person [:address :city] clojure.string/upper-case)
;; @@

;; **
;;; ### Makros
;;; 
;;; Makros erlauben syntaktische Erweiterungen. Dazu koennen Code-Transformationen definiert werden, die zur Compile-time ausgefuehrt werden.
;;; Hier werden wir Makros nicht im Detail behandeln, aber viele spezielle Formen in Clojure sind eigentlich Makros, z.B `cond` oder `for`:
;; **

;; @@
(clojure.pprint/pprint
 (macroexpand
  '(cond (> x 0) :pos
         (< x 0) :neg
       :otherwise :zero)))
     
(clojure.pprint/pprint
 (macroexpand
  '(for [i (range 10)
         :when (even? i)
         k [10 20]]
     (+ i k))))
;; @@

;; **
;;; Hauefig verwendet werden die *chaining*-Makros `->` und `->>`. Diese uebersetzen eine Sequenz von Funktionsaufrufen in geschachtelte Funktionsanwendungen
;;; indem sie das vorherige Resultat jeweils als erstes oder letztes Argument einsetzen:
;; **

;; @@
(clojure.pprint/pprint
 (macroexpand
  '(-> 1 (+ 2) (* 3 4) even?)))
  
(clojure.pprint/pprint
 (macroexpand
  '(->> 1 (+ 2) (* 3 4) even?)))  
;; @@

;; **
;;; `->>` harmoniert gut mit Sequenz-Funktionen, welche die Sequenz als letztes Argument erwarten, und fuehrt zu gut lesbaren *Prozess-Pipelines*:
;; **

;; @@
(->> (range 10)
   (filter even?)
   (map (fn [x] (* x x)))
   (reduce +))
;; @@

;; **
;;; Funktionale Programme sind letztlich immer Kombinationen von Funktionen, die auf eine Eingabedatenstruktur angewandt werden,
;;; um daraus eine Ausgabe zu berechnen ...
;; **

;; **
;;; ### Java
;;; 
;;; Clojure ist gut mit Java integriert und erlaubt es direkt auf Java Objekte zuzugreifen:
;;; 
;;; | Syntax | Kurzform | Beschreibung |
;;; |--------|----------|--------------|
;;; | `(new Object)` | `(Object.)` | Erzeugt neue Instanz einer Klasse |
;;; | `(. obj getClass)` | `(.getClass obj)`  | Aufruf der Methode `o.getClass()` |
;;; | `(. Math abs -2)` | `(Math/abs -2)` | Aufruf der statischen Methode `Math.abs(-2)` |
;; **

;; @@
(import '(java.util Calendar GregorianCalendar))

(def feb Calendar/FEBRUARY) ; Zugriff auf statisches Feld

(def today (new GregorianCalendar 2017 feb 27)) ; Erzeuge neues Objekt

(. today add Calendar/MONTH 2)

(. today get Calendar/MONTH)

(.add today Calendar/MONTH 2)

(.get today Calendar/MONTH)
;; @@

;; **
;;; Achtung: Java Objekte sind meist nicht immutable!
;;; 
;;; Das `doto` Makro erlaubt es mehrere Methoden (mit Seiteneffekten) auf das gleiche Objekt anzuwenden:
;; **

;; @@
(doto today
  (.add Calendar/MONTH 1)
  (.add Calendar/DAY_OF_YEAR 10)
  .toString) ; Gibt Objekt zurueck

(.toString today)
;; @@

;; **
;;; #### Threads
;;; 
;;; Die JVM unterstuetzt *Threads*, die unabhaengig ausgefuehrt werden koennen. 
;;; Wir koennen diese von Clojure aus direkt verwenden:
;; **

;; @@
(def task (Thread. 
           (fn [] (doseq [_ (range 5)]
                    (Thread/sleep 1000) ; warte 1 Sekunde
                    (println "Task running")))))

(.start task)
;; @@

;; **
;;; Clojure bietet jedoch viele weitere (und meist bessere) Moeglichkeiten zur nebenlaeufigen Programmierung.
;;; Dazu spaeter mehr ...
;; **
