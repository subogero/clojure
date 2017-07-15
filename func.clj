(defn fruit
  ([x] x)
  ([] (fruit "banana")))
(println (fruit "apple"))
(println (fruit "peach"))
(println (fruit))

(defn huzza [x] (str "Tiplizz inet " x "!"))
(println (huzza "Tibi"))

(defn huzzatok [& xs] (doall (map huzza xs)))
(println (huzzatok "Tibi" "Klára"))
