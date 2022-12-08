# Progetto-IngSw

Progetto per l'esame di Ingegneria del Software I

Traccia: Ratatouille23

Ratatouille23 è un sistema finalizzato al supporto alla gestione e all’operatività di attività di ristorazione. Il sistema consiste in un’applicazione performante e affidabile, attraverso cui gli utenti possono fruire delle funzionalità del sistema in modo intuitivo, rapido e piacevole.
Le principali funzionalità offerte da Ratatouille23 sono indicate di seguito:
1. Un amministratore può creare utenze per i propri dipendenti (sia addetti alla sala, che addetti alla cucina, che supervisori). Al primo accesso, ogni utente deve re-impostare la password inserita dall’amministratore, scegliendo una password diversa.
2. Un amministratore può personalizzare i dettagli della propria attività di ristorazione, specificando nome, numero di telefono, indirizzo, e opzionalmente caricando un logo.
3. Un amministratore (o un supervisore) può personalizzare il menù dell’attività di ristorazione. In particolare, l’utente può creare e/o eliminare elementi dal menù. Ciascun elemento è caratterizzato da un nome, un costo, una descrizione, e un elenco di allergeni comuni. Inoltre, è possibile organizzare gli elementi del menù in categorie personalizzabili (e.g.: primi, dessert, primi di pesce, bibite, etc.), e definire l’ordine con cui gli elementi compaiono nel menù. In fase di inserimento, è richiesto l’autocompletamento di alcuni prodotti (e.g.: bibite opreconfezionati) utilizzando open data come quelli disponibili in https://it.openfoodfacts.org/data.
4. È possibile, per ristoranti che operano in località turistiche, specificare il nome e la descrizione di ciascun elemento del menù in una seconda lingua.
5. Un addetto alla sala può registrare ordinazioni indicando l’identificativo del tavolo e gli elementi del menù desiderati.
6. Un supervisore può visualizzare (in formato PDF) il conto di ciascun tavolo in un determinato momento. Inoltre, quando gli avventori seduti ad un certo tavolo vanno via, il supervisore può chiudere il conto e visualizzare la versione finale da sottoporre al cliente.
7. Un supervisore o un amministratore può inserire nel sistema degli avvisi, che vengono visualizzati da tutti i dipendenti. Ciascun dipendente può marcare un avviso come “visualizzato” e nasconderlo.
8. Un amministratore può visualizzare statistiche sugli introiti dell’attività di ristorazione. In particolare, dato un certo intervallo di tempo personalizzabile, è possibile visualizzare l’incasso medio, il valore medio di ciascun conto, l’incasso complessivo. È apprezzata la presenza di grafici interattivi.

In particolare, le funzionalità riservate al personale di sala e di cucina saranno fruite su tablet, mentre le funzionalità destinate ad amministratori e supervisori saranno fruite principalmente attraverso notebook o PC. Dal momento che l’hardware (tablet e notebook) non è stato ancora acquistato, si può assumere che su entrambi sia installato un sistema operativo a scelta dei contraenti (e.g.: Linux, Windows, Android, iOS, etc.).
