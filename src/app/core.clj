(ns app.core
  (:gen-class)
  (:require [app.echelon-form-parser :as echelon-form-parsing-adapter]))

(defn -main
  [& _]
  (let [matrix_to_be_parsed [[1 2 -1 -4]
                             [2 3 -1 -11]
                             [-2 0 -3 22]]]
    (println "Matrix Multiplication:")
    (println "")
    (println "Matrix To Be Parsed:")
    (println "")
    (doseq [current_matrix_row matrix_to_be_parsed]
      (println (vec current_matrix_row)))
    (println "")
    (let [matrix_of_parsed_reduced_echelon (echelon-form-parsing-adapter/get-parsed-matrix matrix_to_be_parsed)]
      (println "Parsed Matrix:")
      (println "")
      (doseq [current_matrix_row matrix_of_parsed_reduced_echelon]
        (println (vec current_matrix_row)))
      )))