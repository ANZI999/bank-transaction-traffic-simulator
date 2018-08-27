The main aim of the project is to learn about thread safety in JAVA.
Also I will write the whole project in a TDD manner meaning for every functionality I write a failing test first.

1) I will write an application which will simulate simplified transactional traffic between banks and clients also while doing so I will follow TDD principles. At the same time I will not put any thought into thread safety. [STEP I AM AT NOW]

2) I will add logging support to make the next point easier.

3) I will run the simulation with many clients acting at the same time 
which will hopefully cause many discrepancies due to the application not being thread safe in the final bookkeeping. I will rewrite the parts which are culprits in a more thread safe manner. Hopefully following TDD principles will make rewriting parts of the application easier.