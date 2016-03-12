(ns a-shit-twitter.core
  (:require [clojure.string :as str]))

;; The CMU pronunciation dictionary has a machine readable pronunciation key that
;; we can use to search words for syllable counts. Let's write a filter for every
;; word containing n syllables.

(def pron-dict (slurp "resources/cmudict-0.7b"))

(def a-hash-token-thats-that-shit-i-dont-like
  (filter #(re-find #"^[^#]" %)))

(def a-word-splitter-thats-that-shit-i-dont-like
  (map (fn [line] (str/split line #" " 2))))

(def a-syll-counter-thats-that-shit-i-dont-like
  (map (fn [[word pron]] [word (filter #(#{\0 \1 \2} %) pron)])))

(defn a-count-filter-thats-that-shit-i-dont-like [n]
  (filter (fn [[word syll]] (= n (count syll)))))

(defn a-transducer-thats-that-shit-i-dont-like [n]
  (comp
   a-hash-token-thats-that-shit-i-dont-like
   a-word-splitter-thats-that-shit-i-dont-like
   a-syll-counter-thats-that-shit-i-dont-like
   (a-count-filter-thats-that-shit-i-dont-like n)))

(defn get-words-by-syll [n]
  (let [lines (str/split-lines pron-dict)]
    (into [] (a-transducer-thats-that-shit-i-dont-like n) lines)))

(get-words-by-syll 1)

(defn dont-like []
  (println "A" (first (rand-nth (get-words-by-syll 1))) (first (rand-nth (get-words-by-syll 2))) "THATS THAT SHIT I DON'T LIKE"))

