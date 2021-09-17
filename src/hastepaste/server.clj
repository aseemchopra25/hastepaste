;; To serve our pasted, we need a server component in chanrge of serving HTTP
;; -URL to which to paste a new paste to
;; -URL with which to read pastes by identifier 
;; -Form to present the form 

;; In its simplest form that component wraps the aleph webserver 

(ns hastepaste.server
(:require [com.stuartsierra.component :as component]
          [bidi.ring :refer [make-handler]]
          [aleph.http :as http]
          [ring.util.response :as res]
          [ring.util.request :as req]
          [ring.middleware.params :refer [wrap-params]]
          [hastepaste.view :as view]
          [hastepaste.store :as store]))
;; This function defines how a new paste is created and directs the screen to /uuid as the identifier 
(defn handle-post
    "This handles creating a new paste, based on the POST data"
    [store request]
    (let [content (get (:form-params request) "content")
        uuid (store/add-new-paste store content)]
    (res/redirect (str "/" uuid) :see-other)))

(defn handle-index
    "We get there when we are displaying the index page, prompting for a new paste"
    [request]
    (res/response (view/render-form)))
;; Defines the type of request by the user and the following actions 
(defn index-handler
    "Handle request sent to our root URL.
    GET requests while looking at the form or POST to post a new paste"
    [store request]
    (if (=(:request-method request) :post)
        (handle-post store request)
        (handle-index request)))
(defn paste-handler 
    "Get the handler function of our routes"
    [store]
    (make-handler ["/" {"" (partial index-handler store)
                        [:uuid] (partial paste-handler store)}]))

;; We pass the result of the handler function that builds the  bidi handler. The handler maps the URLs to your users 
;; The handler function also receives the store as an entry into the server component map. 
;; This lets us forward a reference to the store to our paste handler 
(defn handler
    "Get the handler function for our routes"
    [store]
    (make-handler ["/" {"" (partial index-handler store)}]))
    
(defn app
    [store]
    (-> (handler store)
        wrap-params))

(defrecord HttpServer [server]
    component/Lifecycle 
    
    (start [this]
        (assoc this :server (http/start-server (app (:store this)) {:port 8080})))
    (stop [this]
        (dissoc this :erver)))


(defn make-server
    []
    (map->HttpServer {}))
