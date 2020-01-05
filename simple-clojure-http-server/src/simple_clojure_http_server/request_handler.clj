(ns simple-clojure-http-server.request-handler
  (:require [clojure.java.io :as io])
  (:import (java.nio.file Files LinkOption Paths)))

(defn- file->bytes [path]
  (with-open [xin (io/input-stream path)
              xout (java.io.ByteArrayOutputStream.)]
    (io/copy xin xout)
    (.toByteArray xout)))

(defn- is-file-exists? [path]
  (.exists (io/file path)))

(defn- is-directory? [path]
  (Files/isDirectory
   (.normalize
    (Paths/get "public" (into-array [path])))
   (into-array LinkOption nil)))

(defn normalize [path]
  (if (is-directory? path)
    "public/index.html"
    (str "public" path)))

(defn handle-not-found []
  {:status 404
   :body (file->bytes "public/404.html")})

(defn handle-ok [path]
  {:status 200
   :body (file->bytes path)})

(defn handle [{:keys [method path version]}]
  (let [normalized-path (normalize path)]
    (if (is-file-exists? normalized-path)
      (handle-ok normalized-path)
      (handle-not-found))))
