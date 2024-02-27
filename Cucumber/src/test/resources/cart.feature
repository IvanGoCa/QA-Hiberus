Feature: Validacion funcionalidad carrito

  Background:
    Given me encuentro en la pagina de login
    When realizo el login con el user "standard_user" y el pass "secret_sauce"
    Then me encuentro en la home de la app

  Scenario: Validar eliminar producto desde el carrito
    Given me encuentro en la pagina del carrito