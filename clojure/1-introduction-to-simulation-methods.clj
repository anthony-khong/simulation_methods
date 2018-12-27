(use '(incanter core stats charts io))

(def xs (sample-normal 5000))

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
     :kurtosis (kurtosis xs)
     :skewness (skewness xs)}))

; Question 1
(defn sample-single-average [n-rows]
  (mean (sample-uniform n-rows)))

(defn sample-averages [n-sims n-rows]
  (take n-sims (repeatedly #(sample-single-average n-rows))))

(for [i (range 1 15 3)
      :let [n-rows (int (Math/pow 2 i))]]
  (-> (sample-averages 5000 n-rows)
      (summarise)
      (assoc :n-rows n-rows)
      (pprint)))


(defn expectation-of-average [n-rows] 0.5)
(defn variance-of-average [n-rows] (/ 1/12 n-rows))

(defn summarise [n-rows] {:n-rows n-rows 
                          :mean (expectation-of-average n-rows)
                          :var ( variance-of-average n-rows)})

(pprint
  (let [n-rows-grid (map #(Math/pow 2 %) (range 1 10))]
    (map summarise n-rows-grid)))
   
; Question 2
