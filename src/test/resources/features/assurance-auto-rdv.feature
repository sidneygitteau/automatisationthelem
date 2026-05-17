Feature: Souscription d'un contrat d'assurance automobile

  Background:
    Given je suis sur la page automobile de Thelem Assurances

  Scenario Outline: Remplir le formulaire de rendez-vous sans le soumettre
    When je sélectionne le type de véhicule "Berline"
    And je saisis le code postal "<codepostal>"
    And la liste des agences s'affiche
    And je choisis une agence dans la liste
    And je clique sur "Demander un rendez-vous"
    And je remplis le formulaire avec "<civilite>","<nom>", "<prenom>", "<naissance>", "<adresse>", "<codepostal>", "<ville>", "<telephone>", "<email>" et "<pays>"
    Then le formulaire est affiché complété mais non soumis

    Examples:
      | civilite | nom    | prenom | naissance  | adresse          | codepostal | ville                | telephone  | email                 | pays   |
      | M        | Dupont | Jean   | 2001-11-01 | 12 rue de checy  | 45290      | Chatillon-Coligny    | 0612345678 | jean.dupont@test.fr   | France |
      | Mme      | Martin | Sophie | 2000-01-01 | 5 avenue du foot | 45290      | Nogent-sur-Vernisson | 0623456789 | sophie.martin@test.fr | France |

  Scenario: Demander un devis en agence
    When je sélectionne le type de véhicule "Berline"
    And je saisis le code postal "45290"
    And la liste des agences s'affiche
    And je choisis une agence dans la liste
    And je clique sur "Demander un devis"
    Then la page de la carte des agences s'affiche

  # c'est un scenario théorique : le site n'affiche pas de message code postal invalide
  Scenario: Saisir un code postal invalide
    When je sélectionne le type de véhicule "Berline"
    And je saisis le code postal "00000"
    Then un message d'erreur indique que le code postal est invalide
