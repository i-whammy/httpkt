(ns simple-clojure-http-server.core
  (:require [clojure.java.io :as io]
            [simple-clojure-http-server.response-handler :as response-handler]
            [simple-clojure-http-server.request-handler :as request-handler]))

(defn -main
  "Launch a simple HTTP server."
  []
  (let [server-socket (new java.net.ServerSocket 8000)]
    (while true
      (let [socket (.accept server-socket)
            input (.getInputStream socket)
            output (.getOutputStream socket)]
        (with-open [reader (io/reader input)]
          (let [content (request-handler/handle reader)]
            (response-handler/handle output content)))))))
