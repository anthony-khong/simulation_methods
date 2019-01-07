(ns intro-to-sim-methods
  (:require [clojure.pprint :as pp]
            [incanter.charts :as charts]
            [incanter.core :as core]
            [incanter.distributions :as dists]
            [incanter.stats :as stats]
            [tools]))

; Question 1
(defn sample-single-average [n-rows]
  (stats/mean (stats/sample-uniform n-rows)))

(defn sample-averages [n-sims n-rows]
  (take n-sims (repeatedly #(sample-single-average n-rows))))

; TODO

(defn expectation-of-average [n-rows] 0.5)
(defn variance-of-average [n-rows] (/ 1/12 n-rows))

(defn expectations [n-rows] {:n-rows n-rows
                             :mean (expectation-of-average n-rows)
                             :var (variance-of-average n-rows)})

; TODO

; Question 2
(defn sample-sigle-delta [n-x n-y]
  (let [mean-x (mean (sample-exp n-x))
        mean-y (mean (sample-gamma n-y :shape 3 :scale 2))]
    (- mean-x mean-y)))

(defn sample-deltas [n-sims & {:keys [n-x n-y]}]
  (take n-sims (repeatedly #(sample-sigle-delta n-x n-y))))

; TODO

(defn expectation-of-delta [n-x n-y]
  {:mean (- 1 (* 3 2))
   :std-error (let [var-x (/ 1 n-x)
                    var-y (/ (* 2 (* 3 3)) n-y)]
                (Math/sqrt (+ var-x var-y)))})
(expectation-of-delta 100 100)

; TODO
