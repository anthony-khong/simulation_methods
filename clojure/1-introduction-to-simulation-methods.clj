(ns intro-to-sim-methods
  (:require [clojure.pprint :as pp]
            [incanter.charts :as charts]
            [incanter.core :as core]
            [incanter.distributions :as dists]
            [incanter.stats :as stats]))

(clojure.main/load-script "tools.clj")

(defn n-rows-grid [max-power]
  (map #(int (Math/pow 2 %)) (range 0 (+ max-power 1) 2)))

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

(for [n-rows (n-rows-grid 10)
      :let [xs (sample-averages 10000 n-rows)
            pdf (partial pdf-of-average n-rows)]]
  (-> (charts/histogram xs :nbins 25 :density true :title (str "N=" n-rows))
      (charts/add-function pdf 0.0 1.0)
      (core/save (str "figures/ex1-q1-uniform-n" n-rows ".png"))))

; Question 2
(defn sample-sigle-delta [n-x n-y]
  (let [mean-x (stats/mean (stats/sample-exp n-x))
        mean-y (stats/mean (stats/sample-gamma n-y :shape 3 :scale 2))]
    (- mean-x mean-y)))

(defn sample-deltas [n-sims n-x n-y]
  (take n-sims (repeatedly #(sample-sigle-delta n-x n-y))))

(defn expectation-of-delta [n-x n-y]
  (- 1 (* 3 2)))

(defn variance-of-delta [n-x n-y]
  (let [var-x (/ 1 n-x)
        var-y (/ (* 2 (* 3 3)) n-y)]
    (+ var-x var-y)))

(defn pdf-of-delta [n-x n-y xs]
  (let [mu (expectation-of-delta n-x n-y)
        sigma (Math/sqrt (variance-of-delta n-x n-y))]
    (stats/pdf-normal xs :mean mu :sd sigma)))

; When n-x and n-y grow large
(for [n-rows (n-rows-grid 10)
      :let [xs (sample-deltas 10000 n-rows n-rows)
            pdf (partial pdf-of-delta n-rows n-rows)]]
  (-> (charts/histogram xs :nbins 25 :density true :title (str "Nx=" n-rows ", Ny=" n-rows))
      (charts/add-function pdf -10.0 0.0)
      (core/save (str "figures/ex1-q2-delta-nx" n-rows "-ny-" n-rows ".png"))))

; When only n-y grows large
(for [n-rows (n-rows-grid 10)
      :let [xs (sample-deltas 10000 2 n-rows)
            pdf (partial pdf-of-delta 2 n-rows)]]
  (-> (charts/histogram xs :nbins 25 :density true :title (str "Nx=" 2 ", Ny=" n-rows))
      (charts/add-function pdf -10.0 0.0)
      (core/save (str "figures/ex1-q2-delta-nx" 2 "-ny-" n-rows ".png"))))

; Question 3
(defn sample-cauchy [n-rows]
  (let [xs (stats/sample-t n-rows :df 1)]
    (if (= n-rows 1) (list xs) xs)))

(defn sample-cauchy-averages [n-sims n-rows]
  (take n-sims (repeatedly #(stats/mean (sample-cauchy n-rows)))))

(defn sample-cauchy-medians [n-sims n-rows]
  (take n-sims (repeatedly #(stats/median (sample-cauchy n-rows)))))

; Cauchy means
(for [n-rows (n-rows-grid 16)
      :let [xs (sample-cauchy-averages 1000 n-rows)]]
  (-> (charts/histogram xs :nbins 25 :density true :title (str "N=" n-rows))
      (core/save (str "figures/ex1-q3-cauchy-means-n-" n-rows ".png"))))

; Cauchy medians
(for [n-rows (n-rows-grid 10)
      :let [xs (sample-cauchy-medians 1000 n-rows)]]
  (-> (charts/histogram xs :nbins 25 :density true :title (str "N=" n-rows))
      (core/save (str "figures/ex1-q3-cauchy-medians-n-" n-rows ".png"))))
