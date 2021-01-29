java -jar ./application.jar \
  -server \
  -Xtune:virtualized \
  -Xshareclasses \
  -Xscmx100M \
  -Dquarkus.http.host=0.0.0.0