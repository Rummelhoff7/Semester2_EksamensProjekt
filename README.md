READ.ME fil for 2. Semester Eksamensprojekt for Gruppe 7.

GitHub Repository Link: https://github.com/Rummelhoff7/Semester2_EksamensProjekt

Deployment URL (Azure): https://bilabonnementinterngruppe7-a5dxejh7b8e6dbb6.germanywestcentral-01.azurewebsites.net/

Database Adgang (Azure MySQL):
URL: mysqlbilabonnementintern.mysql.database.azure.com

Softwaremæssige forudsætninger:
Dette er en dynamisk hjemmeside, hvor man har fuld adgang til koden via dette GitHub repository. Der kræver ingen yderligere installation af software for at køre denne applikation.

Applikationen starter med en log-in side hvor vi har disse brugere:
Tilgængelige bruger: 
  demo (admin)
  Mads (Dataregistration) 
  Güney (AutoRepair)
  Thamied (BusinessDeveloper)

Når man logger ind som en af disse brugere, så bliver man sendt til deres egen side.

Password: 
  demo - demo
  Andre brugere - 1234
  
Admin bruger (System Login):
  Brugerid: demo
  Password: demo



System opbygning:
Admin:
Når man logger ind som admin, så har man adgang til hele hjemmesiden. En admin kan alt hvad alle medarbejdere kan gøre, men kan ikke sine egne ekstra features.


Dataregistration:
Hvis man logger ind som admin eller data registreringsmedarbejder, så kan man oprette, opdatere og slette leasinger.
Det er også her man kan lave forhåndssalg, hvis en kunde ønsker at købe leaset bil. Man kan også se hvilke biler, der er blevet solgt med forhåndssalg.


Autorepair:
Hvis man logger ind som admin eller skade- og udbedringsmedarbejder så kan man oprette, opdatere og slette skadesrapporter.
På disse skadesrapporter kan man tilføje skader på bilen 


BusinessDeveloper:
Hvis man logger ind som admin eller forretningsudvikler, så kan man se alle ledige og udlejede biler. Vi har sørget for al nødvendig information er vist samt taget højde for om bilerne er elbiler.
Man kan også se antallet af udlejedede biler samt indtjeningen på dem.

  
