(use '(incanter core stats charts io))

; Utility Functions
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

; Question 1
(defn sample-single-average [n-rows]
  (mean (sample-uniform n-rows)))

(defn sample-averages [n-sims n-rows]
  (take n-sims (repeatedly #(sample-single-average n-rows))))

(for [i (range 1 14 4)
      :let [n-rows (int (Math/pow 2 i))]]
  (-> (sample-averages 5000 n-rows)
      (summarise)
      (assoc :n-rows n-rows)
      (pprint)))


(defn expectation-of-average [n-rows] 0.5)
(defn variance-of-average [n-rows] (/ 1/12 n-rows))

(defn expectations [n-rows] {:n-rows n-rows
                             :mean (expectation-of-average n-rows)
                             :var (variance-of-average n-rows)})

(for [i (range 1 15 3)
      :let [n-rows (int (Math/pow 2 i))]]
  (pprint (expectations n-rows)))

; Question 2
(defn sample-sigle-delta [n-x n-y]
  (let [mean-x (mean (sample-exp n-x))
        mean-y (mean (sample-gamma n-y :shape 3 :scale 2))]
    (- mean-x mean-y)))

(defn sample-deltas [n-sims & {:keys [n-x n-y]}]
  (take n-sims (repeatedly #(sample-sigle-delta n-x n-y))))

(for [i (range 1 14 4)
      :let [n-rows (int (Math/pow 2 i))]]
  (-> (sample-deltas 5000 :n-x n-rows :n-y n-rows)
      (summarise)
      (assoc :n-rows n-rows)
      (pprint)))

(defn expectation-of-delta [n-x n-y]
  {:mean (- 1 (* 3 2))
   :std-error (let [var-x (/ 1 n-x)
                    var-y (/ (* 2 (* 3 3)) n-y)]
                (Math/sqrt (+ var-x var-y)))})
(expectation-of-delta 100 100)

(for [i (range 1 14 4)
      :let [n-rows (int (Math/pow 2 i))]]
  (-> (expectation-of-delta n-rows n-rows)
      (assoc :n-rows n-rows)
      (pprint)))
