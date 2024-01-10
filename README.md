# 5CIA-Progetto-4-Lanini-Server

#### Realizzare un server web con i socket. Il server deve essere in grado di gestire le richieste del client (browser), in particolare:

- deve restituire la pagina HTML e/o la risorsa richiesta dal client;
- deve restituire un messaggio (o pagina web) di errore nel caso in cui la pagina richiesta non sia disponibile.

La comunicazione tra client e server deve rispettare il protocollo HTTP: il server, prima di rispondere con la pagina web richiesta dal client, deve inviare lo status code e gli headers previsti dal protocollo. Inoltre, tra gli headers e il body il server deve inviare un messaggio contenente una riga vuota
