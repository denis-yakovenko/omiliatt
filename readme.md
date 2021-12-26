Build & run:  
mvn clean install  
java -jar target/omiliatt-1.0-SNAPSHOT.jar 0 0 30 69 700 24 1 3 50 2  

Or just run an existing jar:  
java -jar target/omiliatt-1.0-SNAPSHOT.jar 0 0 30 69 700 24 1 3 50 2  

Expected results:  
*** Base Level Validation ***  
input: 0 0 30 69 700 24 1 3 50 2, output: 0030697002413502 [phone number: INVALID]  
*** Advanced Level Validation ***  
Interpretation 0: 0030697241352 [phone number: INVALID]  
Interpretation 1: 00306972413502 [phone number: VALID]  
Interpretation 2: 00306972041352 [phone number: VALID]  
Interpretation 3: 003069720413502 [phone number: INVALID]  
Interpretation 4: 003069700241352 [phone number: INVALID]  
Interpretation 5: 0030697002413502 [phone number: INVALID]  
Interpretation 6: 0030697002041352 [phone number: INVALID]  
Interpretation 7: 00306970020413502 [phone number: INVALID]  
Interpretation 8: 00306097241352 [phone number: INVALID]  
Interpretation 9: 003060972413502 [phone number: INVALID]  
Interpretation 10: 003060972041352 [phone number: INVALID]  
Interpretation 11: 0030609720413502 [phone number: INVALID]  
Interpretation 12: 0030609700241352 [phone number: INVALID]  
Interpretation 13: 00306097002413502 [phone number: INVALID]  
Interpretation 14: 00306097002041352 [phone number: INVALID]  
Interpretation 15: 003060970020413502 [phone number: INVALID]  
