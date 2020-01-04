(ns simple-clojure-http-server.response-handler)

(def sp " ")

(def crlf "\r\n")

(defn handle [output content]
  (let [header (str
                "HTTP/1.1" sp "201" sp "TODO" crlf
                "Date:" "04-01-2020" crlf
                "Content-Length:" 0 crlf
                "Content-Type:" "html" crlf
                "Connection: Close" crlf
                crlf)]
    (doto output
      (.write (.getBytes header))
         (.write (.getBytes (str "method: " (first (vals content)))))
         (.flush))))
