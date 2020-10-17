This is the java implementation of the back end for the Mountain Snowpack app.

Front End Deploy:
1) Replace the `src` directory in the `frontend` directory with the latest version of the front end.
2) Run `mvn clean install` to create a production react build and inside of a new `EnosJava.war` file.
3) Run `heroku war:deploy ./target/EnosJava.war --app <app_name>` to deploy to heroku.
