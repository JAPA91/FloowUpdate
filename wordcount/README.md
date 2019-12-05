WordCount

Project

This project aims to analyse a file with a large body of text and to count number of occurance of the words. This Application counts the words in a file and saves the counts to a MongoDB server.

Project

This project will accept txt as input and import the source and count values to MongoDB Server.
Created wordcount.jar and cmd line argument to execute this application through commands .
wordcount.jar is availble in bin folder.
Keep the (dump.txt)files in resource path and execute the application.
Hava to do further development process for Aggregate Framework and Map Reduce for word counts and to get the most and least used words in large text file.
Executing the Jar: Example: java -jar D:\Priyanka\txttoMongodb\bin\wordcount.jar Priyanka\txttoMongodb\src\main\resources\dump.txt -source Priyanka\txttoMongodb\src\main\resources\dump.txt

show dbs
use mydb
show collections
 txtomongodb 
 db.txtmongodb.drop()