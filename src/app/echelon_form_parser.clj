(ns app.echelon-form-parser
  (:require [app.eliminating-current-column-in-other-rows-adapter :as eliminating-current-column-in-other-rows-adapter]
            [app.reduced-row-normalizer :as reduced-row-normalizer]))

(defn get-parsed-matrix [matrix-to-be-parsed]
  (let [matrix-in-reduced-echelon-form (transient (mapv vec matrix-to-be-parsed))
        number-of-rows-on-matrix (count matrix-in-reduced-echelon-form)]
    (loop [current-row-being-reduced 0
           current-column-being-reduced-index 0]
      (when (< current-row-being-reduced number-of-rows-on-matrix)
        (let [current-column-with-largest-row-value current-row-being-reduced]
          (let [temp (get matrix-in-reduced-echelon-form current-row-being-reduced)]
            (assoc! matrix-in-reduced-echelon-form
                    current-row-being-reduced
                    (get matrix-in-reduced-echelon-form current-column-with-largest-row-value))
            (assoc! matrix-in-reduced-echelon-form
                    current-column-with-largest-row-value
                    temp))

          (reduced-row-normalizer/normalize-reduced-row current-column-being-reduced-index
                                                        current-row-being-reduced
                                                        matrix-in-reduced-echelon-form)

          (eliminating-current-column-in-other-rows-adapter/execute number-of-rows-on-matrix
                                                                    current-row-being-reduced
                                                                    current-column-being-reduced-index
                                                                    matrix-in-reduced-echelon-form)

          (recur (inc current-row-being-reduced)
                 (inc current-column-being-reduced-index)))))
    (persistent! matrix-in-reduced-echelon-form)))

