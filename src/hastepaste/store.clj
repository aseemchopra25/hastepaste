;; We nneed a place to store the data to our hastepaste server. We'll build a simple storage component tht uses clojure atom.
;; Closure atom is a clojure primitive wrapping arbitrary state - an in memory data store 
;; During initialisation we set its data field to a fresh new atom
;; IMPORTANT: If we had a database connection, we should also need to close it at the program shutdown in the stop function

(ns hastepaste.store
    (:require [com.stuartsierra.component :as component]))

(defn add-new-paste
    "Insert a new paste in the database, then return its UUID"
    [store content]
    (let [uuid (.toString (java.util.UUID/randomUUID))]
        (swap! (:data store) assoc (keyword uuid) {:content content})
        uuid))

(defn get-paste-by-uuid
    "Find the paste corresponding to passed-in uuid, then return it."
    [store uuid]
    ((keyword uuid) @(:data store)))
(defrecord InMemoryStore [data]

    component/Lifecycle 
    (start [this]
        (assoc this :data (atom {})))
    (stop [this] this))

(defn make-store

    []
    (map->InMemoryStore {}))
