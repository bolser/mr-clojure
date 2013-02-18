(ns leiningen.new.cljskel
  (:use [leiningen.new.templates :only [renderer name-to-path ->files year]]))

(def render (renderer "cljskel"))

(defn cap [s]
  (str (.toUpperCase (subs s 0 1)) (subs s 1)))

(defn cljskel
  "Skeleton Clojure project"
  [name]
  (let [data {:name name
              :upper-name (cap name)
              :sanitized (name-to-path name)
              :year (year)}]
    (->files data
             ["project.clj" (render "project.clj" data)]
             ["all" (render "all" data)]
             ["test_helper.sh" (render "test_helper.sh" data)]
             ["acceptance" (render "acceptance" data)]
             ["integration" (render "integration" data)]
             [".gitignore" (render ".gitignore" data)]
             ["src/{{sanitized}}/setup.clj" (render "setup.clj" data)]
             ["src/{{sanitized}}/web.clj" (render "web.clj" data)]
             ["test/{{sanitized}}/web_unit.clj" (render "web_unit.clj" data)]
             ["resources/logback.xml" (render "logback.xml" data)]

             ["test/{{sanitized}}/acceptance.clj" (render "acceptance.clj" data)]
             ["test/{{sanitized}}/integration.clj" (render "integration.clj" data)]


             ["configuration/ComponentManifest.xml" (render "ComponentManifest.xml" data)]
             ["configuration/configuration.xml" (render "configuration.xml" data)]

             ["scripts/bin/start.sh" (render "start.sh" data)]
             ["scripts/bin/stop.sh" (render "stop.sh" data)]

             ["scripts/dmt/post_install" (render "post_install" data)]

             ["scripts/rpm/postinstall.sh" (render "postinstall.sh" data)]
             ["scripts/rpm/postremove.sh" (render "postremove.sh" data)]
             ["scripts/rpm/preinstall.sh" (render "preinstall.sh" data)]
             ["scripts/rpm/preremove.sh" (render "preremove.sh" data)]

             ["scripts/service/jetty" (render "jetty") data])))
