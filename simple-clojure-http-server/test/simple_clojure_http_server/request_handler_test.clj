(ns simple-clojure-http-server.request-handler-test
  (:require [simple-clojure-http-server.request-handler :as sut]
            [clojure.test :as t]
            [clojure.java.io :as io]))

(t/deftest test-normalize
  (t/testing "add public/ when it is not a directory"
    (t/is (= "public/index.html"
             (sut/normalize "/index.html"))))

  (t/testing "return public/index.html when it is a directory"
    (t/is (= "public/index.html"
             (sut/normalize "/")))))

(t/deftest test-handle-not-found
  (t/testing "return status code and specific page path"
    (t/is (= 404
             (:status (sut/handle-not-found))))))
