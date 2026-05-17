# Tests automatisés pour Thelem Assurances

Proof of Concept, réalisé avec Java, Playwright et Cucumber, avec 3 scenarios testés pas jusqu'au bout car il s'agit du site de production

3 scénarios sur le parcours assurance auto :

1. Remplir le formulaire de rendez-vous en agence sans le soumettre
2. Accéder à la page de demande de devis
3. Saisir un code postal invalide, un scénario exploratoire avec une théorie de presence de message d'erreur

## Stack technique

- Java 17
- Maven
- Playwright (automatisation navigateur)
- Cucumber + JUnit 4 (BDD)

## Lancer les tests

```bash
mvn test
```
