(ns simple-clojure-http-server.response-handler)

(def sp " ")

(def crlf "\r\n")

(defn handle [output {:keys [status body]}]
  (let [header (str
                "HTTP/1.1" sp status sp "TODO" crlf
                "Date:" (str (new java.util.Date)) crlf
                "Content-Length" "0" crlf
                "Content-Type:" "html" crlf
                "Connection: Close" crlf
                crlf)]
    (doto output
      (.write (.getBytes header))
      (.write body)
      (.write (.getBytes crlf))
      (.flush))))

