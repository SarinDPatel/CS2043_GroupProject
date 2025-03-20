# CS2043_GroupProject
The Repository for The Group Project (Team 17) 
you need to have Apache Maven install and in the folder above CS2043_GroupPrject
for example mine is:
C:\Users\gavin\Documents\2043\apache-maven-3.9.9

secondarily, make sure that there is a pom.xml file within the GameStoreManagement folder
for example mine is:
C:\Users\gavin\Documents\2043\CS2043_GroupProject\GameStoreManagement\pom.xml

from there make sure that the pom is referncing:
<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
< dependency>
    < groupId>mysql< /groupId>
    < artifactId>mysql-connector-java< /artifactId>
    < version>8.0.33< /version>
< /dependency>
(without the spaces)
AND make sure that the you connect to the database and alter the user and password as seen fit.
however the Database should be game_store_db. to keep it consistant.
I did:

    1. launch MySQLWorkbench

    2. have the database already created.

    3. click connection on the top banner.

    4. click connect to a database

    5. enter the password that was use within the code for password i have it set to my current password

    6. wait for it to close on you, then you should be connected.



