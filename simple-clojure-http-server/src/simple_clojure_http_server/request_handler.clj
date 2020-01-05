(ns simple-clojure-http-server.request-handler
  (:require [clojure.java.io :as io])
  (:import (java.nio.file Files LinkOption Paths)))

(def reasons
  {403 {:reason "Forbidden"
        :path "public/403.html"}
   404 {:reason "Not Found"
        :path "public/404.html"}})

(get reasons 404)

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

(defn handle-not-ok [status-code]
  (let [{:keys [reason path]} (get reasons status-code)]
    {:status status-code
     :reason reason
     :body (file->bytes path)}))

(defn handle-ok [path]
  {:status 200
   :body (file->bytes path)})

(defn handle [{:keys [method path version]}]
  (let [normalized-path (normalize path)]
    (cond
      (not (.startsWith normalized-path "public")) (handle-not-ok 403)
      (not (is-file-exists? normalized-path)) (handle-not-ok 404)
      :else (handle-ok normalized-path))))
