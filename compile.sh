find . -name "*.java" | xargs javac
jar cvf `find . -name "*.class"` numerical-method.jar
