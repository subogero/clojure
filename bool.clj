;; define named func manualy
(def truthy (fn [x] (if x true false)))
;; same with defn
(defn printbool [x]
  (println x ":" (truthy x)))
(printbool nil)
(printbool false)
(printbool true)
(printbool 0)
(printbool 1)
(printbool "")
(printbool "0")
(printbool "text")
(printbool truthy)
(printbool (= 1 1))
(printbool (= "1" 1))
(printbool (or false nil))
(printbool (or "foo" false))
(printbool (and "foo" false))
(printbool (and "foo" 1))
(printbool (and 2 "foo"))