# Cyclic-Redundancy-Check-in-data-transmission
Implementing forward error detection mechanism using Cyclic Redundancy Check (CRC). Get the input data from the user for performing CRC. Based on the generator calculate the CRC bits to be added to the data at sender side. Append the redundant bit with the message. At the receiver side, check for errors in the message during transmission.


The sample output is as follows:
Sender Side:
Enter the length of the data: 6
Enter the data: 100100
Enter the length of the Generator (Divisor): 4
Enter the Generator (Divisor) : 1101
Number of 0's to be appended: 3
Message after appending 0's :100100000
CRC bits: 001
Transmitted Data: 100100001
Receiver side:
Received Data: 100100001
Remainder: 000
Since remainder is 0, hence the message has received correctly!!! 
