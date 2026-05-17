package fr.thelem.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AssuranceAutoPage {

    private final Page page;

    public AssuranceAutoPage(Page page) {
        this.page = page;
    }

    public void allerSurAuto() {
        page.navigate("https://www.thelem-assurances.fr/assurance-auto/");
        page.waitForLoadState();
        // on clique au milieu de la page pour déclencher les cookies
        page.mouse().click(640, 300);
        page.waitForTimeout(800);
        Locator cookieButton = page.locator("#didomi-notice-agree-button");
        if (cookieButton.isVisible()) {
            cookieButton.click();
            cookieButton.waitFor(new Locator.WaitForOptions()
                    .setState(com.microsoft.playwright.options.WaitForSelectorState.HIDDEN));
        }
    }

    public void selectionnerTypeVehicule(String type) {
        page.locator("#select__auto").selectOption(
                new com.microsoft.playwright.options.SelectOption().setLabel(type)
        );
    }

    public void saisirCodePostal(String codePostal) {
        Locator champCodePostal = page.locator("#codepostalauto");

        champCodePostal.scrollIntoViewIfNeeded();

        page.waitForTimeout(300);
        // press sequentially car insert n'est pas reconnu et nous permet pas de proposer les agences
        champCodePostal.pressSequentially(codePostal, new Locator.PressSequentiallyOptions().setDelay(100));
    }

    public void attendreListeAgences() {
        page.locator(".agences__list__choice").first().waitFor(
                new Locator.WaitForOptions().setTimeout(10000));
    }

    public void choisirUneAgence() {
        page.locator(".agences__list__choice").first().click();
    }

    public void cliquerDemanderRendezVous() {
        page.getByText("Demander un rendez-vous").click();
        page.waitForTimeout(1000);
        page.locator("input[name='nom'], input[name='prenom'], input[type='text']").first().waitFor(
                new Locator.WaitForOptions().setTimeout(5000));
    }

    public void cliquerDemanderDevis() {
        page.locator("a.demande__devis[data-type='auto']").click();
        page.waitForLoadState();
    }

    public void remplirFormulaireRdv(String civilite, String nom, String prenom, String naissance, String adresse, String codepostal, String ville, String telephone, String email, String pays) {
        page.locator("#form-prise-rdv").scrollIntoViewIfNeeded();
        page.waitForTimeout(500);

        if (civilite.contains("Mme")) {
            page.locator("#gender-mme").check();
        } else {
            page.locator("#gender-m").check();
        }

        page.locator("#nom").fill(nom);
        page.locator("#prenom").fill(prenom);
        page.locator("#rdv__naissance").fill(naissance);
        page.locator("#adress").fill(adresse);
        page.locator("#cp").fill(codepostal);
        page.locator("#city").fill(ville);
        page.locator("#phone").fill(telephone);
        page.locator("#mail").fill(email);
        page.locator("#country").fill(pays);
    }

    public boolean formulaireRdvEstAfficheEtComplete() {
        // verif formulaire visible + champs non vides donc bien remplis
        return page.locator("#form-prise-rdv").isVisible()
                && !page.locator("#nom").inputValue().isEmpty()
                && !page.locator("#mail").inputValue().isEmpty();
    }

    public boolean pageCarteAgencesEstAffiche() {
        // on verifie qu'on a bien ete redirigé sur la bonne page
        return page.url().contains("devis") || page.url().contains("agences");
    }

    public boolean messageErreurCodePostalEstVisible() {
        return page.getByText("invalide").or(page.getByText("erreur")).isVisible();
    }
}
