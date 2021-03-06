(defproject org.roman01la/prum "0.10.8-11"
  :description "ClojureScript wrapper for Preact"
  :license {:name "Eclipse"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :url "https://github.com/roman01la/prum"

  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.9.946"]
                 [org.roman01la/cljss "1.5.13"]
                 [sablono "0.8.1"
                  :exclusions [org.clojure/clojure
                               org.omcljs/om cljsjs/react
                               cljsjs/react-dom
                               cljsjs/react-dom-server]]
                 [hicada "0.1"]]

  :global-vars
  {*warn-on-reflection* true}

  :plugins [[lein-cljsbuild "1.1.7"]]

  :profiles {:dev  {:source-paths ["examples"]
                    :dependencies [[clj-diffmatchpatch "0.0.9.3" :exclusions [org.clojure/clojure]]]}
             :perf {:source-paths ["perf"]
                    :dependencies
                                  [[enlive "1.1.6"]
                                   [criterium "0.4.4"]
                                   [hiccup "1.0.5"]]}}

  :aliases {"package" ["do" ["clean"] ["test"] ["clean"] ["cljsbuild" "once" "advanced"] ["run" "-m" "rum.examples-page"]]
            "perf"    ["with-profile" "perf" "run" "-m" "rum.perf"]}


  :cljsbuild
  {:builds
   [{:id           "advanced"
     :source-paths ["src" "examples" "test"]
     :compiler
                   {:main           prum.examples
                    :output-to      "target/main.js"
                    :optimizations  :advanced
                    :source-map     "target/main.js.map"
                    :pretty-print   false
                    :compiler-stats true
                    :parallel-build true
                    :install-deps   true
                    :npm-deps       {"prum-preact"             "8.2.5"
                                     "preact-render-to-string" "3.6.3"}}}

    {:id           "none"
     :source-paths ["src" "examples" "test"]
     :compiler
                   {:main           prum.examples
                    :output-to      "target/main.js"
                    :output-dir     "target/none"
                    :asset-path     "target/none"
                    :optimizations  :none
                    :source-map     true
                    :compiler-stats true
                    :parallel-build true
                    :install-deps   true
                    :npm-deps       {"prum-preact"             "8.2.5"
                                     "preact-render-to-string" "3.6.3"}}}

    {:id           "test"
     :source-paths ["src" "test"]
     :compiler
                   {:main           prum.test.server-render
                    :output-to      "target/test.js"
                    :output-dir     "target/test"
                    :asset-path     "target/test"
                    :optimizations  :advanced
                    :pretty-print   true
                    :pseudo-names   true
                    :parallel-build true
                    :install-deps   true
                    :npm-deps       {"prum-preact"             "8.2.5"
                                     "preact-render-to-string" "3.6.3"}}}]})

