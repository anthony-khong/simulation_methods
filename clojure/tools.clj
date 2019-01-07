(ns tools)

(defn standardise [xs]
  (let [mu (mean xs)
        sigma (sd xs)]
    (->> xs
         (map #(- % mu))
         (map #(/ % sigma)))))

(defn quantile-map [xs probs]
  (zipmap probs (quantile xs :probs probs)))

(defn summarise [xs]
  (let [std-xs (standardise xs)]
    {:std-quantiles (quantile-map std-xs [0.025, 0.15, 0.5, 0.85, 0.975])
     :mean (mean xs)
     :var (variance xs)
     :sd (sd xs)
     :kurtosis (kurtosis xs)
     :skewness (skewness xs)}))

(defn linspace [min max size]
  (let [step (/ (- max min) (dec size))]
    (range min (+ max (/ step 2)) step)))
