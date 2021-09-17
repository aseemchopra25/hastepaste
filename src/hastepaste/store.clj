;; We nneed a place to store the data to our hastepaste server. We'll build a simple storage component tht uses clojure atom.
;; Closure atom is a clojure primitive wrapping arbitrary state - an in memory data store 
;; During initialisation we set its data field to a fresh new atom
;; IMPORTANT: If we had a database connection, we should also need to close it at the program shutdown in the stop function
