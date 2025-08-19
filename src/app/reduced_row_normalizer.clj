(ns app.reduced-row-normalizer)

(defn normalize-reduced-row [current-column-being-reduced-index current-row-being-reduced
                             matrix-in-reduced-echelon-form]
  (let [current-value-begin-reduced (get-in matrix-in-reduced-echelon-form
                                            [current-row-being-reduced current-column-being-reduced-index])]
    (assoc! matrix-in-reduced-echelon-form
            current-row-being-reduced
            (mapv #(/ % current-value-begin-reduced)
                  (get matrix-in-reduced-echelon-form current-row-being-reduced)))))