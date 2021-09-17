(ns hastepaste.main 
    (:require [hastepaste.system :refer [init-system start!]]))

(defn -main [& args]
    (init-system)
    (start!))