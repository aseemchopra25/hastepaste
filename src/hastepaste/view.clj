;; Hiccup clojure HTML templating and rendering 
;; So our pastebin needs to render actual HTML on the browser. 
;; We need a new module that leverages the hiccup module to render HTML 
;; Hiccup mmakes it convenient to create HTML with Clojure syntax 
;; Most of what i write is data and clojure is powerful that way 

;; We just need two view
;; One to render a paste 
;; One to display the paste form to our users That's it!

(ns hastepaste.view 
    (:require [hiccup.page :refer [html5 include-js include-css]]
              [hiccup.form :refer [form-to text-area submit-button]]))

;; HTML webpage to render the paste 

(defn render-paste 
    "Given a map as a paste, return HTML string for the display or output of the paste"
    [paste]
    (html5 [:head 
        (include-js "https://sos-de-fra-1.exo.io/highlight.js/9.12.0/highlight.min.js")
        (include-js "https://sos-de-fra-1.exo.io/highlight.js/9.12.0/go.min.js")
        (include-js "https://sos-de-fra-1.exo.io/highlight.js/9.12.0/clojure.min.js")
        (include-js "https://sos-de-fra-1.exo.io/highlight.js/9.12.0/yaml.min.js")
        (include-css "https://sos-de-fra-1.exo.io/highlight.js/9.12.0/default.min.css")
        [:meta {:charset "UTF-8"}]
        [:script "hljs.initHightingOnLoad();"]]

        [:body 
            [:pre [:code (:content paste)]]]))

;; HTML  webpage to show the form 
(defn render-form 
    "Render a simple HTML form page"
    []
    (html5 [:head
            [:meta {:charset "UTF-8"}]]
            [:body
                (form-to [:post "/"]
                    (text-area {:cols 80 
                                :row 15} "content")
                                [:div]
                                (submit-button "HastePaste!"))]))