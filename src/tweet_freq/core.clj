(ns tweet-freq.core
  (:require [clojure.pprint])
  (:use [twitter.oauth]
        [twitter.callbacks]
        [twitter.callbacks.handlers]
        [twitter.api.restful]))

(def ^:dynamic *creds* (make-oauth-creds *app-consumer-key*
                                         *app-consumer-secret*
                                         *user-access-token*
                                         *user-access-token-secret*))

;; The maximum number of tweets that can be requested with a single API call.
;; https://dev.twitter.com/docs/api/1.1/get/statuses/home_timeline
(def max-tweets-per-request 200)

;; The maximum number of tweets that can be requested over multiple API calls.
;; https://dev.twitter.com/docs/api/1.1/get/statuses/home_timeline
(def max-tweet-history 800)

;; The total number of API calls that can be made to retrieve tweets from the
;; user's home timeline.
(def total-api-calls (/ max-tweet-history max-tweets-per-request))

(defn get-home-timeline [max-id]
  (let [params {:count max-tweets-per-request}
        params (if max-id (assoc params :max-id max-id) params)]
  (:body (home-timeline :oauth-creds *creds* :params params))))

(defn get-tweet-data []
  (loop [all-tweets []
         max-id     nil
         x          0]
    (let [tweets (get-home-timeline max-id)
          max-id (:id (last tweets))]
      (if (< x total-api-calls)
        (recur (into all-tweets tweets) max-id (inc x))
        all-tweets))))

(defn get-tweet-authors [tweets]
  (let [get-tweet-author #(-> %1 :user :screen_name)]
    (map get-tweet-author tweets)))

(defn sort-by-frequency [frequencies]
  (sort-by val > frequencies))

(defn calculate-frequency-of-tweets-by-author []
  (-> (get-tweet-data)
      (get-tweet-authors)
      (frequencies)
      (sort-by-frequency)))

(defn print-tweet-frequencies-as-table [tweet-frequencies]
  (-> (map
       (fn [[username frequency]] {"Username" username "Frequency" frequency})
       tweet-frequencies)
      (clojure.pprint/print-table)))

(defn -main [& m]
  (-> (calculate-frequency-of-tweets-by-author)
      (print-tweet-frequencies-as-table))
  (System/exit 0))
