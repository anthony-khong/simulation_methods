(ns figures
  (:require [incanter.charts :as charts]
            [incanter.core :as core]
            [incanter.distributions :as dists]
            [incanter.stats :as stats]
            [tools]))

(def smooth-grid (tools/linspace 0.0 5.0 1000))
(def rough-grid (tools/linspace 0.0 5.0 25))
(def centered-grid
  (let [step (- (second rough-grid) (first rough-grid))]
    (drop-last (core/plus rough-grid (/ step 2)))))

(def frequencies
  (let [densities (stats/pdf-exp centered-grid)
        normed-ds (map #(/ % (core/sum densities)) densities)
        freqs (map #(Math/round (* % 10000)) normed-ds)]
    freqs))

(defn pseudo-samples [grid freqs]
  (loop [output []
         rec-grid grid
         rec-freqs freqs]
    (if (and rec-grid rec-freqs)
      (recur (concat output (repeat (first rec-freqs) (first rec-grid)))
             (next rec-grid)
             (next rec-freqs))
      output)))

(stats/mean (pseudo-samples centered-grid frequencies))

(next (range 1 10))
(round 1)

(let [] rough-grid)
(let [] frequencies)


(-> (charts/function-plot stats/pdf-exp 0.0 5.0
                          :x-label "x"
                          :y-label "PDF of Exp(1)")
    core/view)

(charts/add-polygon)
