(ns app.eliminating-current-column-in-other-rows-adapter)

(defn execute [number-of-rows-on-matrix current-row-being-reduced
               current-column-being-reduced-index matrix-in-reduced-echelon-form]
  (doseq [current-row-of-column-being-eliminated (range number-of-rows-on-matrix)]
    (when (not= current-row-of-column-being-eliminated current-row-being-reduced)
      (let [current-value-to-be-reduced-factor (get-in matrix-in-reduced-echelon-form
                                                       [current-row-of-column-being-eliminated
                                                        current-column-being-reduced-index])]
        (assoc! matrix-in-reduced-echelon-form
                current-row-of-column-being-eliminated
                (mapv #(- % (* current-value-to-be-reduced-factor
                               (nth (get matrix-in-reduced-echelon-form current-row-being-reduced) %2)))
                      (get matrix-in-reduced-echelon-form current-row-of-column-being-eliminated)
                      (range)))))))
