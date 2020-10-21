## About
This is the java implementation of the Mountain Snowpack app.  

For the deployed version visit www.mountainsnowpack.com

For the React front end see: https://github.com/StephenHanzlik/Front_End

## Front End Deploy:
1) Replace the `src` directory in the `frontend` directory with the latest version of the front end.
2) Run `mvn clean install` to create a production react build and inside of a new `EnosJava.war` file.
3) Run `heroku war:deploy ./target/EnosJava.war --app <app_name>` to deploy to heroku.
