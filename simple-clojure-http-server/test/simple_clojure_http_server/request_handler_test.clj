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

(t/deftest test-handle-not-ok
  (t/testing "case when not found"
    (let [result (sut/handle-not-ok 404)]
      (t/is (= 404
               (:status result)))
      (t/is (= "Not Found"
               (:reason result)))))
  (t/testing "case when forbidden"
    (let [result (sut/handle-not-ok 403)]
      (t/is (= 403
               (:status result)))
      (t/is (= "Forbidden"
               (:reason result))))))
