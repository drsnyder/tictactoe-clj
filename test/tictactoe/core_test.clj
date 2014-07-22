(ns tictactoe.core-test
  (:require [midje.sweet :refer :all]
            [tictactoe.core :refer :all]))

(def sample-board-x [[X O X]
                     [O X X]
                     [X O O]])

(facts :get-cell
  (get-cell sample-board-x [0 0]) => X
  (get-cell sample-board-x [1 1]) => X
  (get-cell sample-board-x [2 2]) => O
  (get-cell sample-board-x [3 0]) => (throws AssertionError))

(facts :get-horizontal-wins
  (get-horizontal-wins) => (list '([0 0] [0 1] [0 2])
                                 '([1 0] [1 1] [1 2])
                                 '([2 0] [2 1] [2 2])))

(facts :get-vertical-wins
  (get-vertical-wins) => (list '([0 0] [1 0] [2 0])
                               '([0 1] [1 1] [2 1])
                               '([0 2] [1 2] [2 2])))

(facts :get-diagonal-wins
  (get-diagonal-wins) => (list '([0 0] [1 1] [2 2])
                               '([2 0] [1 1] [0 2])))

(facts :winning-slice
  (winning-slice? sample-board-x X '([0 0] [0 1] [0 2])) => falsey
  (winning-slice? sample-board-x X '([2 0] [1 1] [0 2])) => truthy
  (winning-slice? sample-board-x O '([2 0] [1 1] [0 2])) => falsey)

(facts :winner
  (winner? sample-board-x X) => truthy
  (winner? sample-board-x O) => falsey)
