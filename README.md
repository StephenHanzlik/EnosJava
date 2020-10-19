## About
This is the java implementation of the Mountain Snowpack app.  DNS mapping for www.mountainsnowpack.com coming soon...

For the React front end see: https://github.com/StephenHanzlik/Front_End

For the deployed beta version see: https://safe-reef-75908.herokuapp.com/


## Front End Deploy:
1) Replace the `src` directory in the `frontend` directory with the latest version of the front end.
2) Run `mvn clean install` to create a production react build and inside of a new `EnosJava.war` file.
3) Run `heroku war:deploy ./target/EnosJava.war --app <app_name>` to deploy to heroku.
