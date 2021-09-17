;; full source code can be found here at https://github.com/aseemchopra25/hastepaste 
(defproject hastepaste "0.1.0-SNAPSHOT"
  :description "A pastebin clone made using Clojure, Component, Bidi, Aleph and Hiccup"
  :url "https://github.com/aseemchopra25/hastepaste"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  ;; Closure is a dynamic general purpose programming language, it's a compiled language but remains dynamic 
  ;; Aleph is for the webserver itself. It has a convenient HTTP client which can leverage the same thread pool
  ;; Bidi is for the routing library, it's birectional 
  ;; Component fits everything together that allows us to write decoupled applications for a robust backend 
  ;; Hiccup is for html templating that allows us to define HTML code as normal closure datastructures


  ;; Components needed: 
  ;; webserver to handle aleph startup and bidi routes 
  ;; in memory store for pasted data to live - component instead of cuntion to save in real database
  :main hastepaste.main
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [com.stuartsierra/component "0.3.2"]
                 [aleph "0.4.6"]
                 [hiccup "1.0.5"]
                 [bidi "2.1.3"]])

