;; To serve our pasted, we need a server component in chanrge of serving HTTP
;; -URL to which to paste a new paste to
;; -URL with which to read pastes by identifier 
;; -Form to present the form 

;; In its simplest form that component wraps the aleph webserver 

(ns hastepaste.server
(:require [com.stuartsierra.component :as component]
          [bidi.ring :refer [make-handler]]
          [aleph.http :as http]
          [rng.util.response :as res]
          [ring.utl.request :as req]
          [ring.middleware.params :refer [wrap-params]]
          [hastepaste.view :as view]
          [hastepaste.store :as store]))
        
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



