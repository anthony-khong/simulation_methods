(ns intro-to-sim-methods
  (:require [clojure.pprint :as pp]
            [incanter.charts :as charts]
            [incanter.core :as core]
            [incanter.distributions :as dists]
            [incanter.stats :as stats]))

(clojure.main/load-script "tools.clj")

; Question 1
(defn sample-single-average [n-rows]
  (stats/mean (stats/sample-uniform n-rows)))

(defn sample-averages [n-sims n-rows]
  (repeatedly n-sims #(sample-single-average n-rows)))

(defn expectation-of-average [n-rows] 0.5)

(defn variance-of-average [n-rows] (/ 1/12 n-rows))

(defn pdf-of-average [n-rows xs]
  (let [mu (expectation-of-average n-rows)
        sigma (Math/sqrt (variance-of-average n-rows))]
    (stats/pdf-normal xs :mean mu :sd sigma)))

(for [i (range 0 10 2)
      :let [n-rows (int (Math/pow 2 i))
            xs (sample-averages 10000 n-rows)
            pdf (partial pdf-of-average n-rows)]]
  (-> (charts/histogram xs :nbins 25 :density true)
      (charts/add-function pdf 0.0 1.0)
      (core/save (str "figures/ex1-uniform-n" n-rows ".png"))))

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
