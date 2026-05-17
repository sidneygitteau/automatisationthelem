package fr.thelem.automation.steps;

import fr.thelem.automation.hooks.PlaywrightHooks;
import fr.thelem.automation.pages.AssuranceAutoPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AssuranceAutoSteps {

    private final AssuranceAutoPage page;

    public AssuranceAutoSteps() {
        this.page = new AssuranceAutoPage(PlaywrightHooks.page);
    }

    @Given("je suis sur la page automobile de Thelem Assurances")
    public void jeSuisSurLaPageAuto() {
        page.allerSurAuto();
    }

    @When("je sélectionne le type de véhicule {string}")
    public void jeSelectionneLeTypeDeVehicule(String type) {
        page.selectionnerTypeVehicule(type);
    }

    @When("je saisis le code postal {string}")
    public void jeSaisisLeCodePostal(String codePostal) {
        page.saisirCodePostal(codePostal);
    }

    @When("la liste des agences s'affiche")
    public void laListeDesAgencesSAffiche() {
        page.attendreListeAgences();
    }

    @When("je choisis une agence dans la liste")
    public void jeChoisisUneAgenceDansLaListe() {
        page.choisirUneAgence();
    }

    @When("je clique sur {string}")
    public void jeCliqueSur(String texte) {
        if (texte.contains("Demander un rendez-vous")) {
            page.cliquerDemanderRendezVous();
        } else if (texte.contains("Demander un devis")) {
            page.cliquerDemanderDevis();
        }
    }

    @When("je remplis le formulaire avec {string},{string}, {string}, {string}, {string}, {string}, {string}, {string}, {string} et {string}")
    public void jeRemplisLeFormulaire(String civilite, String nom, String prenom, String naissance, String adresse, String codepostal, String ville, String telephone, String email, String pays) {
        page.remplirFormulaireRdv(civilite, nom, prenom, naissance, adresse, codepostal, ville, telephone, email, pays);
    }

    @Then("le formulaire est affiché complété mais non soumis")
    public void leFormulaireEstAfficheCompleteMaisNonSoumis() {
        assert page.formulaireRdvEstAfficheEtComplete() :
                "Le formulaire de RDV n'est pas affiché ou non complété";
        System.out.println("Formulaire de rendez-vous rempli avec succès (non soumis)");
    }

    @Then("la page de la carte des agences s'affiche")
    public void laPageDeLaCarteDesAgencesSAffiche() {
        assert page.pageCarteAgencesEstAffiche() :
                "La page de la carte des agences n'est pas affichée";
        System.out.println("Page carte des agences affichée");
    }

    @Then("un message d'erreur indique que le code postal est invalide")
    public void unMessageDErreurIndiqueQueLeCodePostalEstInvalide() {
        boolean erreurVisible = page.messageErreurCodePostalEstVisible();
        System.out.println("Scénario code postal invalide - Message d'erreur visible : " + erreurVisible);
    }
}
