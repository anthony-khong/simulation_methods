(use '(incanter core stats charts io))

(view (histogram (sample-normal 1000)))

; function that returns dataset containing weather in London for given month in 2012
(defn weather-for-month [month]
  (-> (format "https://www.wunderground.com/history/airport/EGLL/2012/%d/10/MonthlyHistory.html?format=1" month)
      (read-dataset :header true)))

; get weather data for each month in 2012 and build single dataset
(def data (->> (range 1 13) (map weather-for-month) (apply conj-rows)))

; view dataset in a table and view histogram of mean temperature
(view data)
; wunderground.com formats temperature depending on locale/location/whatever 
; so you might need to use "Mean TemperatureF" otherwise you'll get NullPointerException.
(view (histogram "Mean TemperatureC" :nbins 100 :data data))

; function that given month "2012-9-20" extracts month and returns 9
(defn month [date] (Integer/parseInt (second (.split date "-"))))

; dataset that contains 2 columns: month and mean temperature for that month
; don't forget to change to "Mean TemperatureF" if you did so few steps back.
(def grouped-by-month
  (->> (map (fn [date temp] {:month (month date) :temp temp})
            ($ "GMT" data) ($ "Mean TemperatureC" data))
       to-dataset
       ($rollup :mean :temp :month)
       ($order :month :asc)))

; view line chart that shows that August was the warmest month
(view (line-chart :month :temp :data grouped-by-month))<Paste>
