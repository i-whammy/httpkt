(ns simple-clojure-http-server.request-parser)

(defn parse [reader]
  (->> reader
      (.readLine)
      (re-find #"(.+) (.+) (.+)")
      (rest)
      (zipmap [:method :path :version])))
