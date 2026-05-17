# Tests automatisés pour Thelem Assurances

Proof of Concept, réalisé avec Java, Playwright et Cucumber, avec 3 scenarios testés n'allant pas jusqu'au bout car il s'agit de leur site de production

3 scénarios sur le parcours assurance auto :

1. Remplir le formulaire de rendez-vous en agence sans le soumettre 
2. Accéder à la page de demande de devis qui emmene sur la carte des agences
3. Saisir un code postal invalide (un scénario fictif avec une théorie de presence de message d'erreur)

## Stack technique

- Java 17
- Gherkin
- Maven
- Playwright
- Cucumber

## Lancer les tests

```bash
mvn test
```
