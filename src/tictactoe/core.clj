(ns tictactoe.core)

(def board-size 3)

(def X "X")
(def O "O")

(defn get-cell
  [board coord]
  {:pre  [(< (first coord) board-size)]
   :post [(#{X O} %)]}
  (let [[row col] coord]
    (-> board
        (nth row)
        (nth col))))

(defn get-horizontal-wins
  []
  (partition board-size
             (for [row (range board-size) col (range board-size)]
               [row col])))

(defn get-vertical-wins
  []
  (partition board-size
             (for [row (range board-size) col (range board-size)]
               [col row])))

(defn get-diagonal-wins
  []
  (list (for [i (range 3)]
      [i i])
   (for [i (range 3)]
     [(- 2 i) i])))

(defn winning-slice?
  [board user slice]
  (every? true? (map #(= (get-cell board %) user) slice)))

(defn winner?
  [board user]
  (some true? (map (fn [l] (some true? l))
                   (map #(map (partial winning-slice? board user) %)
                        (list (get-horizontal-wins)
                              (get-vertical-wins)
                              (get-diagonal-wins))))))
