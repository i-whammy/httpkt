(ns simple-clojure-http-server.request-handler)

(defn handle [reader]
  (->> reader
      (.readLine)
      (re-find #"(.+) (.+) (.+)")
      (rest)
      (zipmap [:method :path :version])))
