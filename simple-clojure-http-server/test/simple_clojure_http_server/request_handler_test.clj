(ns simple-clojure-http-server.request-handler-test
  (:require [simple-clojure-http-server.request-handler :as sut]
            [clojure.test :as t]
            [clojure.java.io :as io]))

(t/deftest test-handler
  (t/testing "receive reader and return map of content"
    (with-open [reader (io/reader (char-array "GET /hoge.html HTTP/1.1"))]
      (t/is (= {:method "GET"
                :path "/hoge.html"
                :version "HTTP/1.1"}
               (sut/handle reader))))))
