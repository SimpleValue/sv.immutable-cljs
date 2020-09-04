(ns sv.immutable-cljs.core
  (:require ["immutable" :as immutable]))

;; TODO: regrettably, ClojureScript does not have
;;       `clojure.core/extend` that would make it possible to avoid
;;       duplicated implementation code here. There also seems to be
;;       no common super type of `immutable/Map` and
;;       `immutable/Record`.

(extend-type immutable/Map

  ILookup
  (-lookup
    ([o k]
     (.get o
           k))
    ([o k not-found]
     (.get o
           k
           not-found)))

  ISeqable
  (-seq [o]
    (map (fn [[k v]]
           (MapEntry. k v nil))
         (.toSeq o)))

  IAssociative
  (-assoc [o k v]
    (.set o
          k
          v))
  (-contains-key? [o k]
    (.has o
          k))

  )

(extend-type immutable/Record

  ILookup
  (-lookup
    ([o k]
     (.get o
           k))
    ([o k not-found]
     (.get o
           k
           not-found)))

  ISeqable
  (-seq [o]
    (map (fn [[k v]]
           (MapEntry. k v nil))
         (.toSeq o)))

  IAssociative
  (-assoc [o k v]
    (.set o
          k
          v))
  (-contains-key? [o k]
    (.has o
          k))

  )
