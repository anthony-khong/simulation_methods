(use '(incanter core stats charts io))

; Question 1
(defn sample-single-average [n-rows]
  (mean (sample-uniform n-rows)))

(defn sample-averages [n-sims n-rows]
  (take n-sims (repeatedly #(sample-single-average n-rows))))

(for [i (range 1 15 3)]
  (let [n-rows (int (Math/pow 2 i))
        plot-path (str "figures/uniform-averages-" n-rows ".png")]
    (save (histogram (sample-averages 5000 n-rows)) plot-path)))

(defn expectation-of-average [n-rows] 0.5)

(defn variance-of-average [n-rows] (/ 1/12 n-rows))

; Question 2
