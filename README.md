## Restaurant Project

This project is a restaurant management webapp, it will allow you to manage the tables & orders of the customers in your restaurant.

In order to be able to use this project you will need :

- To create a db.properties file inside core/src/main/ressources, containing the url to your database and your login/password. You can refere to db.properties.example (in the same folder) for help.

- To pass an environment variable when you launch the application : PROJECT_DIR with value the directory where the project is contained. For example : PROJECT_DIR = "D:/Gauthier/ProjetJEE".
This is needed for the application to log in the correct file. 

Once the db.properties setup is done you can run Application.main() of the data module in order to populate you database with values. 

For trying the project I recommend using the data module and then using the restaurant "DEMO" to log in.