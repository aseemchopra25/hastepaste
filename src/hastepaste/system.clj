;; defining namespaces, I don't understand this, I'm following the exopaste tutorial at https://www.exoscale.com/syslog/clojure-application-tutorial/ 
;; I'll get back to understanding this concept later, seems like importing libraries like in C++ like std
(ns hastepaste.system
(:require [com.stuartsierra.component :as component]
          [hastepaste.server :as server]
          [hastepaste.store :as store]
          [clojure.tools.logging :refer [error]]))

;; Not sure what this is either 
(def ^:redef system
    "Holds our system"
    nil)

;; Here we tell the component system which components to actually use 
;; This helps us to build very simple componenets first like the in memory store which we can later swap 
;; for a real database for a backend 
;; system-map describes the list of components we have 
;; system-using describes the relationship between them 
;; so we have componenets server and store and using describes the relationship between them 

;; A component is conceptually a map 
(defn build-system
    "Defines our system map"
    []
    (try
        (-> (component/system-map
                :server(server/make-server)
                :store(store/make-store))
                (component/system-using {:server [:store]}))
            (catch Exception e 
                (error "Failed to build system" e))))

    )
)
;; Atomically alters the root binding of variable by mechanically changing the value of a variable and sets it to  new value 
;; system initialisation function 
(defn init-system
    []
    (let [sys (build-system)]
        (alter-var-root #'system (constantly sys))))

(defn stop!
    "Stop System"
    []
    (alter-var-root #'system component/stop-system))

(defn start! 
    "Start System"
    []
    (alter-var-root #'system component/start-system)
    (println "System Started"))

