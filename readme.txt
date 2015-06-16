Hello,

This is my solution for the atm test. It is written as a maven project, and can be run via the mvn clean install command from the command line.


To test using the sample data supplied in the specification, the following command can be used

mvn test -Dtest=UkBankTest#testProcessTransactions_customFile -DfileName=sampleTestData


To test using custom data, please append the file customTestData.text in test/resources and run the following command

mvn test -Dtest=UkBankTest#testProcessTransactions_customFile -DfileName=customTestData

note - you can use any correctly formatted text file placed in the same folder in the last test, just change the fileName property 


Thanks,
Matt Brooks