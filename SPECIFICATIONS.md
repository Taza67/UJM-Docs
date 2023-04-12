# Cahier des charges [WebProject]

## Créateur(s)

- **Nom(s) :** AKIL, BAYAZID, ROMAIN, RIEU
- **Prénom(s) :** Mourtaza, Hany, Bruno, Valentin
- **Projet :** WebProject
- **Dernière modification :** 10/04/23

## Descriptif

Notre projet consistera à réaliser une plateforme d'édition collaborative de documents textes riches (style word, google docs).

## Fonctionnalités

Voici une liste des fonctionnalités qu'on implémentera initialement :

- **Création de documents** : un utilisateur pourra utiliser la plateforme pour créer un nouveau document et indiquer les caractéristiques de celui-ci. Pour une première version, il n'aura pas un large éventail de choix. Mais pour la version finale, il aura la possibilité de choisir parmi ces fonctionnalités et éventuellement d'autres (qu'on pense implémenter) :

  - Choix sur la police
  - Génération de structures déjà faites
  - Table des matières dynamique

- **Téléchargement**: tout document, du moment qu'il puisse l'être, pourra ếtre téléchargé au format pdf (au moins). On tentera également d'implémenter la possibilité de le télécharger au format utilisé par la plateforme.

- **Organisation de sessions de travail autour d'un document** : un utilisateur pourra décider de mettre en partage ses documents, c'est-à-dire l'option d'organiser des sessions de collaboration auxquelles d'autres utilisateurs pourront demander l'accès (par un lien par exemple) pour qu'ils puissent lire/modifier un document simultanément.
  - `Système de statuts` : tous les utilisateurs connectés auront un ensemble de statuts et en fonction de ces status, ils auront l'accès à certaines options et services. Tous les utilisateurs connectés à la session seront par exemple considérés comme des ***collaborateurs***.
  - `Options` : les collaborateurs auront l'accès à une panoplie d'options en fonction de leur status. Par exemple, tout ***collaborateur*** (collaborateur étant un statut) aura au moins la possibilité de lire en temps réel le document et un accès permanent à la messagerie de la session.

- **Messagerie** : un utilisateur connecté pourra envoyer des messages à tout autre utilisateur lorsqu'ils sont soient connectés à une même session de travail, soient membres de la même équipe de travail. On pense également à implémenter un système de liens dynamiques qui permettra de référencer une section du document dans le message.

## Fonctionnalités supplémentaires

Voici certaines des fonctionnalités de travail qu'on ajoutera aux fonctionnalités principales :

- **Mise en place d'équipes de travail** : un utilisateur pourra créer une équipe de travail dans laquelle il accordera des status aux membres de l'équipe. La différence avec les sessions de travail se trouve dans l'idée qu'une équipe de travail est permanente alors qu'une session de travail aura une durée de vie. De plus, dans une équipe de travail, il y aura également un système d'hiérarchie plus complexe que celui des sessions de travail.

  Les membres d'une équipe aura accès à tous les documents réalisés dans le cadre du "projet" pour lequel l'équipe existe.

## Architecture

En terme d'architecture, on reprendra exactement ce qui est demandé dans le sujet :
  
- un serveur qui hébergera les documents, qui servira d'intermédiaire de communication entre les clients que ce soit pour la messagerie ou l'écriture collorative de documents. Il stockera également toutes les informations sur les utilisateurs, équipes, sessions de travail.

- le client léger/lourd permettra de se connecter, ou non (pour la réalisation de documents à court terme), et ensuite de travailler sur des documents personnels ou des documents collaboratifs (uniquement si identifié) :

  - Le client léger aura plus ou moins les mêmes options que le client lourd, mais avec probablement un peu moins d'ergonomie.
  
  - Le client lourd permettra un peu plus d'ergonomie, et peut-être plus de rapidité.