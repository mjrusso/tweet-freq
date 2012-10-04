(ns tweet-freq.core-test
  (:use midje.sweet
        tweet-freq.core))

(fact
 (get-tweet-authors [{:user {:screen_name "foo"}}])
 =>
 (just "foo"))

(fact
 (get-tweet-authors [{:user {:screen_name "foo"}} {:user {:screen_name "bar"}}])
 =>
 (just "foo" "bar"))

(fact
 (frequencies ["foo" "bar" "foo" "foo"])
 =>
 (just {"foo" 3 "bar" 1}))

(fact
 (sort-by-frequency {"foo" 9 "bar" 3})
 =>
 (just [["foo" 9] ["bar" 3]]))
