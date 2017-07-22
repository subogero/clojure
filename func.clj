(use 'clojure.java.io)

(defn huzza [x] (str "Tiplizz inet " x "!"))
(defn huzzatok [xs] (mapv huzza xs))
(defn ls [dir] (seq (.list (file dir))))
(defn shufsort
  ([n xs] (shufsort 0 n xs))
  ([i n xs]
    (loop [ii i nn n xss xs]
      (if (= ii nn)
        xss
        (recur (inc ii) nn (sort (shuffle xss)))))))

(def files (time (ls "/usr/bin")))
(def sorted (time (huzzatok (sort files))))
(def sortedk (time (huzzatok (shufsort 1000 files))))
(time (run! println sortedk))
