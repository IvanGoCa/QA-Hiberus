Feature: Validar cerrar sesion

  Background:
    Given me encuentro en la pagina de login
    When realizo el login con el user "standard_user" y el pass "secret_sauce"
    Then me encuentro en la home de la app

  Scenario: Validar cerrar sesion
    When cierro sesion desde el inventario
    Then la app me redirige a la pagina de login