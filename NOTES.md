# Notes

## Serveur

Le serveur tourne sur un projet séparé du projet swing pour éviter des incohérences et conflits inutiles.

### TCP - Client lourd

La commmunication entre le client lourd et le serveur se fait sur un protocole TCP. Le serveur et le client s'envoient des codes pour indiquer les actions à réaliser et agissent en conséquence.

Le serveur de données initialise d'abord un serveur TCP (`SocketServer`), il va ensuite attendre des connexions.

#### Authentification (TCP - Client lourd)

Pour l'authentification, le client tente d'abord une connexion au serveur (TCP) :

    socket = new Socket(SERVEUR_HOTE, PORT);

 et demande ensuite une authentification au serveur de données avec un pseudo et un mot de passe. Le serveur répond par un code (**0** signifiant un refus) pour indiquer sa décision. Si un refus a été reçu par le client, il indique par message à l'utilisateur l'échec de connection et lui propose de tente à nouveau.

### WebSocket - Client léger

La communication entre le client léger et le serveur se fait via protocole WebSocket.

#### Authentification (WebSocket - Client léger)

***VALENTIN - BRUNO*** -> À COMPLÉTER

### Structuration des documents

La base de données ne servira qu'à stocker les informations sur les utilisateurs, les informations sur les documents et les association entre eux. Les documents, en eux-mêmes, seront stockés localement sur la machine sur laquelle tourne le serveur de données.

Le contenu des documents sera traité de façon assez particulière par le serveur de données. Une méthode qui nous semble apporter de la cohérence, une bonne synchronisation et une meilleure optimisation de nos programmes.

Le contenu d'un document sera représenté sous forme de `LinkedList`qui va "chaîner" les mots du document entre eux et en même temps associer un identifiant (indice) à chaque mot et chaque ponctuation. Cela va permettre :

- de repérer plus rapidement les collaborateurs dans un document
- de mieux gérer les incohérences -> deux utilisateurs ne pourront pas modifier simultanément un même mot
- de permettre une "super-structure" qui est celle des **pages** et d'optimiser encore mieux la communication
- de permettre une gestion de la communication beaucoup plus aisée
