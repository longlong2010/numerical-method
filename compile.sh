find . -name "*.java" | xargs javac
jar cvf `find . -name "*.java"` `find . -name "*.class"` numerical-method.jar
