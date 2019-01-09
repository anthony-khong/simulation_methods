(ns intro-to-sim-methods
  (:require [incanter.charts :as charts]
            [incanter.core :as core]
            [incanter.distributions :as dists]
            [incanter.stats :as stats]))

(defn standardise [xs]
  (let [mu (stats/mean xs)
        sigma (stats/sd xs)]
    (->> xs
         (map #(- % mu))
         (map #(/ % sigma)))))

(defn quantile-map [xs probs]
  (zipmap probs (stats/quantile xs :probs probs)))

(defn summarise [xs]
  (let [std-xs (standardise xs)]
    {:std-quantiles (quantile-map std-xs [0.025, 0.15, 0.5, 0.85, 0.975])
     :mean (stats/mean xs)
     :var (stats/variance xs)
     :sd (stats/sd xs)
     :kurtosis (stats/kurtosis xs)
     :skewness (stats/skewness xs)}))

(defn linspace [min max size]
  (let [step (/ (- max min) (dec size))]
    (range min (+ max (/ step 2)) step)))
