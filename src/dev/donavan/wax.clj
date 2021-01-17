(ns dev.donavan.wax
  (:require [com.rpl.specter :as sp]))

(def document-example-2
  {:length 7
   :type :document
   :selection [1 1]
   :script
   [{:word "foo"
     :type :word
     :length 3}
    {:word " "
     :type :word
     :length 1}
    {:word "bar"
     :type :word
     :length 3}]})

(def action-example-1
  {:type :key
   :key "a"
   :length 1})

(defn action->transformation
  [{action-length :length :as action}]
  (sp/terminal
   (fn [{node-length :length :as node}]
     (if (< #p action-length
            #p node-length)
       (do
         ;; (prn (:length node))
         ;; (prn (:key action))
         ;; (prn node)
         ;; (prn )
         #p (document->index node)
         node)
       node))))

(defn document->string
  [document]
  (prn '--------------------------------------------------)
  (sp/multi-transform
   (sp/multi-path (action->transformation action-example-1))
   document))
