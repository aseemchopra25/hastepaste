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


