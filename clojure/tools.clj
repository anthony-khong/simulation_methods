(ns tools)

(defn linspace [min max size]
  (let [step (/ (- max min) (- size 1)]
    (loop [output []
           rec-min min]
      (if (< rec-min max)
        (recur (conj output rec-min) (+ rec-min step))
        output))))

(count (linspace 0.0 1.0 33))

(pos-int? 1.0)
