(defproject tweet-freq "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [twitter-api "0.6.11"]]
  :profiles {:dev {:dependencies [[midje "1.4.0"]]
                   :plugins      [[lein-midje "2.0.0-SNAPSHOT"]]}}
  :main tweet-freq.core)
